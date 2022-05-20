package com.bachue.snr.prosnr01.business.registro;

import com.aspose.words.CellMerge;
import com.aspose.words.CellVerticalAlignment;
import com.aspose.words.ControlChar;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.PreferredWidth;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import com.aspose.words.TableAlignment;

import com.b2bsg.common.dataAccess.oracle.ClobUtils;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.file.excel.ExcelUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;
import com.bachue.snr.prosnr01.business.parameter.ParameterBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.AlertaTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.LibroAntSistemaCommon;
import com.bachue.snr.prosnr01.common.constants.MayorValorCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcedenciaCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoActoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoPersonaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRequiereMatriculaCommon;
import com.bachue.snr.prosnr01.common.constants.UnidadMedidaAreaCommon;
import com.bachue.snr.prosnr01.common.utils.Convertidor;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccCompletitudDocumentalDAO;
import com.bachue.snr.prosnr01.dao.acc.AccDetalleAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccEntidadExternaDAO;
import com.bachue.snr.prosnr01.dao.acc.ActoDAO;
import com.bachue.snr.prosnr01.dao.acc.AlertaTurnoTramiteDAO;
import com.bachue.snr.prosnr01.dao.acc.AnotacionCancelacionDAO;
import com.bachue.snr.prosnr01.dao.acc.AntSistemaActoDAO;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DatosAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DetalleAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DevolucionDineroDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionItemDAO;
import com.bachue.snr.prosnr01.dao.acc.MatriculaSegregacionDAO;
import com.bachue.snr.prosnr01.dao.acc.OficiosTextoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDireccionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.ProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.RecursoDAO;
import com.bachue.snr.prosnr01.dao.acc.RegistroAnotacionProhibicionDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudAsociadaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudCorreccionDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudIntervinienteDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaActoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudReproduccionDAO;
import com.bachue.snr.prosnr01.dao.acc.SoporteTrasladoDAO;
import com.bachue.snr.prosnr01.dao.acc.SubprocesoDAO;
import com.bachue.snr.prosnr01.dao.acc.TestamentoDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoRecepcionDAO;
import com.bachue.snr.prosnr01.dao.acc.TrasladoMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDerivadoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.AreaPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.DetalleAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoDocumentoTipoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalCorreccionDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConsultasDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentalDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoRequiereMatriculaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ZonaRegistralDAO;
import com.bachue.snr.prosnr01.dao.png.GrupoNaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.png.NaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;
import com.bachue.snr.prosnr01.dao.util.UtilDAO;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.copias.SolicitudCopias;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.registro.DataReproduccionConstancia;
import com.bachue.snr.prosnr01.model.registro.DatosPredioRegistro;
import com.bachue.snr.prosnr01.model.registro.GravamenPendiente;
import com.bachue.snr.prosnr01.model.registro.NuevaEntrada;
import com.bachue.snr.prosnr01.model.registro.ProhibicionVPM;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.registro.SolicitudReproduccion;
import com.bachue.snr.prosnr01.model.registro.SolicitudTestamento;
import com.bachue.snr.prosnr01.model.registro.TramiteSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTurnoTramite;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AntSistemaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Recurso;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroAnotacionProhibicion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.SoporteTraslado;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.ValidacionDocumento;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoCanalOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoTestamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import com.bachue.snr.prosnr04.dao.pgn.EntidadRecaudadoraDAO;
import com.bachue.snr.prosnr04.dao.pgn.TipoRecaudoDAO;

import com.bachue.snr.prosnr04.model.npa.RegistroPago;
import com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora;
import com.bachue.snr.prosnr04.model.pgn.TipoRecaudo;

import net.sourceforge.jbarcodebean.JBarcodeBean;

import net.sourceforge.jbarcodebean.model.Code128;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.Clob;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;


/**
 * Clase que contiene todos la funcionalidad para el manejo de registro.
 *
 * @author garias
 */
public class RegistroBusiness extends EnvioGestorDocumentalBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RegistroBusiness.class);

	/**
	 * Método encargado de actualizar Tipo Reproducción  de una solicitud.
	 *
	 * @param ar_registro objeto contenedor de parametros
	 * @param as_userId usuario logueado en la aplicación
	 * @return el valor de solicitud
	 * @throws B2BException error si ocurre una excepción
	 * @see Registro
	 */
	public synchronized Solicitud actualizaTipoReproduccion(Solicitud ar_registro, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_registro != null)
				DaoCreator.getSolicitudDAO(ldm_manager).actualizaTipoReproduccion(ar_registro);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarDescripcionSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Método encargado de actualizar acto para antiguo sistema.
	 *
	 * @param aa_acto objeto necesario para realizar la actualización
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException error si ocurre una excepción
	 */
	public synchronized void actualizarActoParaAntiguoSistema(Acto aa_acto, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_acto != null)
			{
				String ls_idCirculo;

				ls_idCirculo = aa_acto.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					ActoDAO          la_DAO;
					Collection<Acto> lca_actos;

					la_DAO        = DaoCreator.getActoDAO(ldm_manager);
					lca_actos     = la_DAO.findByIdSolicitud(aa_acto);

					if(CollectionUtils.isValidCollection(lca_actos))
					{
						for(Acto la_iterador : lca_actos)
						{
							if(la_iterador != null)
							{
								String ls_idCriculoActo;

								ls_idCriculoActo = la_iterador.getIdCirculo();

								if(
								    StringUtils.isValidString(ls_idCriculoActo)
									    && ls_idCriculoActo.contentEquals(ls_idCirculo)
								)
								{
									la_iterador.setCantidadCertificadosAntSistema(
									    aa_acto.getCantidadCertificadosAntSistema()
									);
									la_iterador.setIdUsuarioModificacion(as_userId);
									la_iterador.setIpModificacion(as_remoteIp);

									la_DAO.updateCertificadosAntSistemaActo(la_iterador);
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

			clh_LOGGER.error("actualizarActoParaAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de actualizar la descripcion de una solicitud.
	 *
	 * @param ar_registro objeto contenedor de parametros
	 * @param as_userId usuario logueado en la aplicación
	 * @return Registro objeto que se retorna como una respuesta post actualizacion
	 * @throws B2BException error si ocurre una excepción
	 * @see Registro
	 */
	public synchronized Registro actualizarDescripcionSolicitud(
	    Registro ar_registro, boolean ab_0463, String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_registro != null)
			{
				Solicitud lso_solicitud;
				lso_solicitud = ar_registro.getSolicitud();

				if(lso_solicitud != null)
				{
					if(ab_0463)
					{
						String        ls_observaciones;
						String        ls_actoProhibicion;
						ConstantesDAO lcd_constant;

						ls_observaciones       = lso_solicitud.getDescripcion();
						lcd_constant           = DaoCreator.getConstantesDAO(ldm_manager);
						ls_actoProhibicion     = obtenerConstanteCaracter(
							    lcd_constant, ConstanteCommon.COMENTARIO_PROHIBICION_JUDICIAL_ENTREGA_DOCUMENTACION
							);

						if(StringUtils.isValidString(ls_observaciones))
						{
							if(
							    StringUtils.isValidString(ls_actoProhibicion)
								    && !ls_observaciones.contains(ls_actoProhibicion)
							)
							{
								StringBuilder lsb_observaciones;

								lsb_observaciones = new StringBuilder(ls_observaciones);

								lsb_observaciones.append(" ");
								lsb_observaciones.append(ls_actoProhibicion);
								ls_observaciones = lsb_observaciones.toString();
								lso_solicitud.setDescripcion(ls_observaciones);
							}
						}
						else if(StringUtils.isValidString(ls_actoProhibicion))
							lso_solicitud.setDescripcion(ls_actoProhibicion);
					}

					DaoCreator.getSolicitudDAO(ldm_manager).updateDescripcionSolicitud(lso_solicitud);

					ar_registro.setSolicitud(lso_solicitud);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarDescripcionSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Método encargado de actualizar la solicitud para nueva entrada.
	 *
	 * @param as_solicitud objeto contenedor de parametros
	 * @param at_turno objeto contenedor de parametros
	 * @return Solicitud objeto que se retorna como una respuesta post actualizacion
	 * @throws B2BException error si ocurre una excepción
	 * @see Solicitud
	 */
	public synchronized Solicitud actualizarSolicitudNuevaEntrada(Solicitud as_solicitud, Turno at_turno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((as_solicitud != null) && (at_turno != null))
			{
				TurnoDAO lt_DAO;

				lt_DAO       = DaoCreator.getTurnoDAO(ldm_manager);
				at_turno     = lt_DAO.findById(at_turno);

				if(at_turno != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = at_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						SolicitudDAO ls_DAO;
						Solicitud    ls_solicitud;

						ls_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);
						ls_solicitud     = ls_DAO.findById(ls_idSolicitud);

						if(ls_solicitud != null)
						{
							String ls_nir;

							ls_nir = ls_solicitud.getNir();

							if(StringUtils.isValidString(ls_nir))
								as_solicitud.setNirAsociado(ls_nir);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarSolicitudNuevaEntrada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return as_solicitud;
	}

	/**
	 * Método encargado de actualizar el sub-proceso para una solicitud.
	 *
	 * @param as_solicitud objeto contenedor de parametros
	 * @param as_userId usuario logueado en la aplicacion
	 * @throws B2BException error si ocurre una excepción
	 */
	public synchronized void actualizarSubProcesoSolicitud(Solicitud as_solicitud, String as_userId)
	    throws B2BException
	{
		SolicitudDAO ls_DAO;
		DAOManager   ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);

		try
		{
			if(as_solicitud != null)
				ls_DAO.updateSubprocesoSolicitud(as_solicitud, true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarSubProcesoSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de actualizar una solicitud con un comentario específico.
	 *
	 * @param as_solicitud objeto contenedor de parametros
	 * @throws B2BException error si ocurre una excepción
	 */
	public synchronized void agregarComentario(Solicitud as_solicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_solicitud != null)
			{
				{
					String ls_idSolicitud;
					String ls_comentario;
					ls_comentario      = as_solicitud.getComentario();
					ls_idSolicitud     = as_solicitud.getIdSolicitud();

					if(!StringUtils.isValidString(ls_idSolicitud))
						throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);

					if(!StringUtils.isValidString(ls_comentario))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_COMENTARIO);

					SolicitudDAO lsd_DAO;
					Solicitud    ls_datosSolicitud;

					lsd_DAO               = DaoCreator.getSolicitudDAO(ldm_manager);
					ls_datosSolicitud     = lsd_DAO.findById(as_solicitud);

					if(ls_datosSolicitud != null)
					{
						ls_datosSolicitud.setComentario(ls_comentario);
						lsd_DAO.insertOrUpdate(ls_datosSolicitud, false);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("agregarComentario", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de consultar el área total del terreno.
	 *
	 * @param ar_registro Objeto que contiene la información para realizar la consulta.
	 * @return Variable de tipo double que contiene el área total del terreno.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized double areaTotalTerreno(Registro ar_registro)
	    throws B2BException
	{
		DAOManager ldm_manager;
		double     ld_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ld_return       = 0;

		if(ar_registro != null)
		{
			try
			{
				AreaPredio lap_ap;
				String     ls_idCirculo;
				Long       ll_idMatricula;

				lap_ap             = new AreaPredio();
				ls_idCirculo       = ar_registro.getIdCirculo();
				ll_idMatricula     = ar_registro.getIdMatricula();

				lap_ap.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
				lap_ap.setIdCirculo(ls_idCirculo);

				ld_return = DaoCreator.getAreaPredioDAO(ldm_manager).areaTotalTerreno(lap_ap);

				if(!NumericUtils.isValidDouble(NumericUtils.getDoubleWrapper(ld_return), 1L))
				{
					AccAreaPredio lacap_acp;
					lacap_acp = new AccAreaPredio();

					lacap_acp.setIdCirculo(ls_idCirculo);
					lacap_acp.setIdMatricula(ll_idMatricula);

					ld_return = DaoCreator.getAccAreaPredioDAO(ldm_manager).areaTotalTerreno(lacap_acp);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("areaTotalTerreno", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ld_return;
	}

	/**
	 * Elimina de base de datos todos los predios que hayan sido agregados a los diferentes círculos
	 * registrales que pueda haber en un tramite.
	 *
	 * @param accdas_datosBorrar Colección de colecciones de datos ant sistema a ser borradas
	 * @param as_idSolicitud Id solicitud del proceso para eliminar las asociaciones de los predios con
	 * los actos agregados
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void borrarDatosDetallesAntSistema(
	    Collection<Collection<DatosAntSistema>> accdas_datosBorrar, String as_idSolicitud
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(accdas_datosBorrar == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			DaoCreator.getAntSistemaActoDAO(ldm_manager).deleteBySolicitud(as_idSolicitud);

			DatosAntSistemaDAO   ldas_datosDAO;
			DetalleAntSistemaDAO ldas_detalleDAO;

			ldas_datosDAO       = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
			ldas_detalleDAO     = DaoCreator.getDetalleAntSistemaDAO(ldm_manager);

			for(Collection<DatosAntSistema> lcdas_iterador : accdas_datosBorrar)
			{
				if(lcdas_iterador != null)
				{
					for(DatosAntSistema ldas_data : lcdas_iterador)
					{
						if(ldas_data != null)
						{
							DetalleAntSistema ldas_detalleAntSis;

							ldas_detalleAntSis = new DetalleAntSistema();

							ldas_detalleAntSis.setIdDatosAntSistema(ldas_data.getIdDatosAntSistema());

							ldas_detalleDAO.deleteByDatosAntSis(ldas_detalleAntSis);
							ldas_datosDAO.deleteBySolicitud(ldas_data);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("borrarDatosDetallesAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Buscar persona usuario logueado.
	 *
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Persona buscarPersonaUsuarioLogueado(String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Persona    lp_persona;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lp_persona      = null;

		try
		{
			Usuario lu_usuario;
			lu_usuario = new Usuario();

			lu_usuario.setIdUsuario(as_userId);
			lu_usuario = DaoCreator.getUsuarioDAO(ldm_manager).findById(lu_usuario);

			if(lu_usuario != null)
			{
				lp_persona = new Persona();

				lp_persona.setIdDocumentoTipo(lu_usuario.getIdDocumentoTipo());
				lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());

				lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findByMaxIdDocNum(lp_persona);

				if(lp_persona == null)
				{
					lp_persona = new Persona();

					lp_persona.setIdDocumentoTipo(lu_usuario.getIdDocumentoTipo());
					lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());
					lp_persona.setPrimerNombre(lu_usuario.getPrimerNombre());
					lp_persona.setSegundoNombre(lu_usuario.getSegundoNombre());
					lp_persona.setPrimerApellido(lu_usuario.getPrimerApellido());
					lp_persona.setSegundoApellido(lu_usuario.getSegundoApellido());
					lp_persona.setCorreoElectronico(lu_usuario.getCorreoElectronicoCorporativo());
					lp_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					lp_persona.setIdTipoPersona(IdentificadoresCommon.N);
					lp_persona.setOrigenBachue(IdentificadoresCommon.S);

					lp_persona.setIdUsuarioCreacion(as_userId);
					lp_persona.setIpCreacion(as_remoteIp);

					lp_persona = DaoCreator.getPersonaDAO(ldm_manager).insertOrUpdate(lp_persona, true);

					int                      li_idCorreoElectronico;
					PersonaCorreoElectronico lpce_personaCorreoElectronico;

					lpce_personaCorreoElectronico = new PersonaCorreoElectronico();

					lpce_personaCorreoElectronico.setIdPersona(lp_persona.getIdPersona());

					li_idCorreoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
							                               .findIdCorreoElectronico(lpce_personaCorreoElectronico);

					if(li_idCorreoElectronico <= 0)
					{
						PersonaCorreoElectronico lpce_personaCorreo;

						lpce_personaCorreo = new PersonaCorreoElectronico();

						lpce_personaCorreo.setIdPersona(lp_persona.getIdPersona());
						lpce_personaCorreo.setCorreoElectronico(lu_usuario.getCorreoElectronico());
						lpce_personaCorreo.setFechaDesde(new Date());
						lpce_personaCorreo.setIdUsuarioCreacion(as_userId);
						lpce_personaCorreo.setIpCreacion(as_remoteIp);

						li_idCorreoElectronico = NumericUtils.getInt(
							    DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
								              .insertOrUpdate(lpce_personaCorreo, true)
							);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarPersonaUsuarioLogueado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lp_persona;
	}

	/**
	 * Metodo encargado de buscar las personas de una solicitud interviniente.
	 *
	 * @param ar_parametros Argumento de tipo <code>Registro</code> que contiene los datos de las personas a llenar.
	 * @return Objeto que contiene los datos de la persona que coincide con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro buscarPersonasPorSolicitudInterviniente(Registro ar_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_datos        = null;

		try
		{
			if(ar_parametros != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = ar_parametros.getSolicitud();

				if(ls_solicitud != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = ls_solicitud.getIdSolicitud();

					if(!StringUtils.isValidString(ls_idSolicitud))
						throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);

					{
						Collection<Persona> lcp_personas;

						lcp_personas     = DaoCreator.getPersonaDAO(ldm_manager)
								                         .buscarPersonasPorSolicitudInterviniente(ls_idSolicitud);

						lr_datos = consultarInfoPersonas(lcp_personas, ldm_manager);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarPersonasPorSolicitudInterviniente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Método encargado de pre-cargar los circulos de ACC_ACTO a partir de una solicitud.
	 *
	 * @param as_solicitud objeto contenedor de parametros
	 * @return Collection<String> coleccion de circulos consultados
	 * @throws B2BException error si ocurre una excepción
	 *
	 */
	public synchronized Collection<String> cargarDatosPredioActo(Solicitud as_solicitud)
	    throws B2BException
	{
		Collection<String> lcs_return;
		DAOManager         ldm_manager;

		lcs_return      = new ArrayList<String>();
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_solicitud != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = as_solicitud.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					Acto             la_acto;
					ActoDAO          la_DAO;
					Collection<Acto> lca_actosCircuclos;

					la_acto     = new Acto();
					la_DAO      = DaoCreator.getActoDAO(ldm_manager);

					la_acto.setIdSolicitud(ls_idSolicitud);

					lca_actosCircuclos = la_DAO.findActosCirculosBySolicitid(la_acto);

					if(CollectionUtils.isValidCollection(lca_actosCircuclos))
					{
						for(Acto la_iterador : lca_actosCircuclos)
						{
							if(la_iterador != null)
							{
								String ls_idCirculo;

								ls_idCirculo = la_iterador.getIdCirculo();

								if(StringUtils.isValidString(ls_idCirculo))
									lcs_return.add(ls_idCirculo);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDatosPredioActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_return;
	}

	/**
	 * Método encargado de cargar los documentos de la solicitud.
	 *
	 * @param as_proceso de as proceso
	 * @param as_idSolicitud Variable que contiene el id de la solicitud.
	 * @param as_idUsuario Variable que contiene el id del usuario.
	 * @param as_ipRemota Variable que contiene la ip del usuario.
	 * @return Colección con los documentos consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DocumentosSalida> cargarDocumentosSolicitud(
	    String as_proceso, String as_idSolicitud, String as_idUsuario, String as_ipRemota
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_return;
		DAOManager                   ldm_manager;
		DAOManager                   ldm_bitacora;

		lcds_return      = null;
		ldm_manager      = DaoManagerFactory.getDAOManager();
		ldm_bitacora     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_proceso))
			{
				//Se utiliza metodo findByIdSolicitudEstadoImpresion en lugar de findByIdSolicitudEstadoImpresionTipo por mantis 2468
				lcds_return = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
						                    .findByIdSolicitudEstadoImpresion(as_idSolicitud);

				{
					SolicitudAsociada lsa_solAsociada;

					lsa_solAsociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager).findByIdSol1(
						    as_idSolicitud, true
						);

					if(lsa_solAsociada != null)
					{
						String ls_idSolicitudAsociada;

						ls_idSolicitudAsociada = lsa_solAsociada.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitudAsociada))
						{
							Collection<DocumentosSalida> lcds_data;

							//Se utiliza metodo findByIdSolicitudEstadoImpresion en lugar de findByIdSolicitudEstadoImpresionTipo por mantis 2468
							lcds_data = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
									                  .findByIdSolicitudEstadoImpresion(ls_idSolicitudAsociada);

							if(CollectionUtils.isValidCollection(lcds_data))
								lcds_return.addAll(lcds_data);
						}
					}
				}

				if(CollectionUtils.isValidCollection(lcds_return))
				{
					String ls_endpoint;

					ls_endpoint = DaoCreator.getConstantesDAO(ldm_manager)
							                    .findString(ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT);

					if(StringUtils.isValidString(ls_endpoint))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(DocumentosSalida lds_iterador : lcds_return)
						{
							if(lds_iterador != null)
							{
								if(!lds_iterador.isEnviadoOwcc())
								{
									enviarGestorDocumental(
									    lds_iterador, lbpd_bitacora, ls_endpoint, as_idUsuario, as_ipRemota, ldm_manager
									);

									if(!lds_iterador.isEnviadoOwcc())
										throw new B2BException(ErrorKeys.ERROR_ENVIO_OWCC);
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_bitacora.setRollbackOnly();
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDocumentosSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
			ldm_bitacora.close();
		}

		return lcds_return;
	}

	/**
	 * Método encargado de consultar la dirección para la persona de la solicitud.
	 *
	 * @param as_solicitud Variable de tipo String que contiene el id de la solicitud.
	 * @return Objeto que contiene las direcciones consultadas.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Registro cargarPersonaDireccion(String as_solicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_return       = null;

		try
		{
			if(StringUtils.isValidString(as_solicitud))
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_solicitud);

				if(ls_solicitud != null)
				{
					String ls_idPersona;

					ls_idPersona = ls_solicitud.getIdPersona();

					if(StringUtils.isValidString(ls_idPersona))
					{
						PersonaDireccionDAO lpd_DAO;
						PersonaDireccion    lpd_dirResidencia;
						PersonaDireccion    lpd_dirCorrespondencia;
						String              ls_idDireccionNot;
						String              ls_idDireccionCom;

						lpd_DAO                    = DaoCreator.getPersonaDireccionDAO(ldm_manager);
						lpd_dirResidencia          = null;
						lpd_dirCorrespondencia     = null;
						ls_idDireccionNot          = ls_solicitud.getIdDireccion();
						ls_idDireccionCom          = ls_solicitud.getIdDireccionComunicacion();
						lr_return                  = new Registro();

						if(StringUtils.isValidString(ls_idDireccionNot))
						{
							PersonaDireccion lpd_temp;

							lpd_temp = lpd_DAO.findById(ls_idPersona, ls_idDireccionNot);

							if(lpd_temp != null)
							{
								String ls_idTipoDireccion;

								ls_idTipoDireccion = lpd_temp.getTipoDireccion();

								if(StringUtils.isValidString(ls_idTipoDireccion))
								{
									if(ls_idTipoDireccion.equalsIgnoreCase(EstadoCommon.R))
										lpd_dirResidencia = lpd_temp;
									else if(ls_idTipoDireccion.equalsIgnoreCase(EstadoCommon.C))
										lpd_dirCorrespondencia = lpd_temp;
								}
							}
						}

						if(StringUtils.isValidString(ls_idDireccionCom))
						{
							PersonaDireccion lpd_temp;

							lpd_temp = lpd_DAO.findById(ls_idPersona, ls_idDireccionCom);

							if(lpd_temp != null)
							{
								String ls_idTipoDireccion;

								ls_idTipoDireccion = lpd_temp.getTipoDireccion();

								if(StringUtils.isValidString(ls_idTipoDireccion))
								{
									if(ls_idTipoDireccion.equalsIgnoreCase(EstadoCommon.R))
										lpd_dirResidencia = lpd_temp;
									else if(ls_idTipoDireccion.equalsIgnoreCase(EstadoCommon.C))
										lpd_dirCorrespondencia = lpd_temp;
								}
							}
						}

						lr_return.setDireccionCorrespondencia(lpd_dirCorrespondencia);
						lr_return.setDireccionResidencia(lpd_dirResidencia);
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

			clh_LOGGER.error("cargarPersonaDireccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_return;
	}

	/**
	 * Compare areas.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param ll_idMatricula de ll id matricula
	 * @throws B2BException
	 */
	public synchronized void compareAreas(String as_idCirculo, Long al_idMatricula, double ad_sumAreas)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				AreaPredio lap_areaPredioBng;

				lap_areaPredioBng = DaoCreator.getAreaPredioDAO(ldm_manager)
						                          .findById(as_idCirculo, NumericUtils.getLong(al_idMatricula));

				if(lap_areaPredioBng != null)
				{
					Collection<DetalleAreaPredio> lcdap_detalleAreaPredioBng;

					lcdap_detalleAreaPredioBng = DaoCreator.getDetalleAreaPredioDAO(ldm_manager)
							                                   .findByIdAreaPredio(
							    StringUtils.getString(lap_areaPredioBng.getIdArea()), as_idCirculo,
							    NumericUtils.getLong(al_idMatricula)
							);

					if(CollectionUtils.isValidCollection(lcdap_detalleAreaPredioBng))
					{
						Iterator<DetalleAreaPredio> lidap_detalle;

						lidap_detalle = lcdap_detalleAreaPredioBng.iterator();

						if(lidap_detalle != null)
						{
							DetalleAreaPredio ldap_detalleArea;

							ldap_detalleArea = lidap_detalle.next();

							if(ldap_detalleArea != null)
							{
								double ld_area;

								ld_area = NumericUtils.getDouble(ldap_detalleArea.getArea());

								if(ad_sumAreas > ld_area)
									throw new B2BException(ErrorKeys.ERROR_SUMA_AREAS);
							}
						}
					}
				}
				else
				{
					AccAreaPredio laap_areaPredioAcc;

					laap_areaPredioAcc = DaoCreator.getAccAreaPredioDAO(ldm_manager)
							                           .findByCirculoMatricula(as_idCirculo, al_idMatricula);

					if(laap_areaPredioAcc != null)
					{
						Collection<com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio> lcdap_detalleAreaPredioBng;

						lcdap_detalleAreaPredioBng = DaoCreator.getAccDetalleAreaPredioDAO(ldm_manager)
								                                   .findAllByIdCirculoIdMatricula(
								    as_idCirculo, al_idMatricula
								);

						if(CollectionUtils.isValidCollection(lcdap_detalleAreaPredioBng))
						{
							Iterator<DetalleAreaPredio> lidap_detalle;

							lidap_detalle = lcdap_detalleAreaPredioBng.iterator();

							if(lidap_detalle != null)
							{
								DetalleAreaPredio ldap_detalleArea;

								ldap_detalleArea = lidap_detalle.next();

								if(ldap_detalleArea != null)
								{
									double ld_area;

									ld_area = NumericUtils.getDouble(ldap_detalleArea.getArea());

									if(ad_sumAreas > ld_area)
										throw new B2BException(ErrorKeys.ERROR_SUMA_AREAS);
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();
				clh_LOGGER.error("compareAreas", lb2be_e);
				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método encargado de consultar las matrículas asociadas para un id solicitud determinado.
	 *
	 * @param as_solicitud objeto contenedor de parametros
	 * @return SolicitudMatricula objeto que se retorna como una respuesta post actualizacion
	 * @throws B2BException error si ocurre una excepción
	 * @see SolicitudMatricula
	 */
	public synchronized SolicitudMatricula consulaMatriculasAsociadas(Solicitud as_solicitud)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		SolicitudMatricula lsm_sm;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lsm_sm          = null;

		try
		{
			if(as_solicitud != null)
			{
				lsm_sm = DaoCreator.getSolicitudMatriculaDAO(ldm_manager).consultaMatriculasAsociadas(as_solicitud);

				if(lsm_sm != null)
				{
					SolicitudAsociada lsa_sa;

					lsa_sa = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
							               .findByIdProceso(as_solicitud.getIdSolicitud(), false);

					if(lsa_sa != null)
						lsm_sm.setNirAsociado(lsa_sa.getNirSolicitud1());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consulaMatriculasAsociadas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lsm_sm;
	}

	/**
	 * Método encargado de consultar el area de un predio.
	 *
	 * @param aaap_areaPredioArg Objeto contenedor de parametros
	 * @param as_userId usuario logueado en la aplicación
	 * @param ab_accion flag que decide metodo de consulta
	 * @return AccAreaPredio Objeto respuesta a la consulta
	 * @throws B2BException error si ocurre una excepción
	 * @see AccAreaUI
	 */
	public synchronized AccAreaUI consultaAreaPredio(
	    AccAreaPredio aaap_areaPredioArg, String as_userId, boolean ab_accion
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		AccAreaUI  laaui_return;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		laaui_return     = new AccAreaUI();

		try
		{
			if(aaap_areaPredioArg != null)
			{
				AccAreaPredio    laap_areaPredio;
				AccAreaPredioDAO laap_DAO;
				DocumentoDAO     ld_DAO;
				Long             ll_idMatricula;
				String           ls_idCirculo;

				laap_DAO           = DaoCreator.getAccAreaPredioDAO(ldm_manager);
				ld_DAO             = DaoCreator.getDocumentoDAO(ldm_manager);
				ll_idMatricula     = aaap_areaPredioArg.getIdMatricula();
				ls_idCirculo       = aaap_areaPredioArg.getIdCirculo();

				if(ab_accion)
				{
					laap_areaPredio = ld_DAO.consultaAreaPredio(aaap_areaPredioArg);

					if(laap_areaPredio != null)
					{
						String ls_idAreaPredio;

						ls_idAreaPredio = laap_areaPredio.getIdArea();

						laaui_return.setConsulta(true);

						if(StringUtils.isValidString(ls_idAreaPredio))
						{
							DetalleAreaPredio    ldap_param;
							DetalleAreaPredioDAO ldap_DAO;

							ldap_param     = new DetalleAreaPredio();
							ldap_DAO       = DaoCreator.getDetalleAreaPredioDAO(ldm_manager);

							ldap_param.setIdCirculo(ls_idCirculo);
							ldap_param.setIdMatricula(ll_idMatricula);
							ldap_param.setIdAreaPredio(ls_idAreaPredio);

							ldap_param.setIdTipoArea(TipoAreaCommon.TERRENO);
							laaui_return.setAreasTerreno(ldap_DAO.findAllByIdAreaPredioTipo(ldap_param));

							ldap_param.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
							laaui_return.setDetalleAreaConstruida(ldap_DAO.findByIdAreaPredioTipo(ldap_param));

							ldap_param.setIdTipoArea(TipoAreaCommon.PRIVADA);
							laaui_return.setDetalleAreaPrivada(ldap_DAO.findByIdAreaPredioTipo(ldap_param));

							laaui_return.setIdDetalleAreaPredio(ldap_DAO.findByMaxIdDetalleAreaPredio(ldap_param));
							laaui_return.setAreaPredio(laap_areaPredio);
							laaui_return.setIdCirculo(ls_idCirculo);
							laaui_return.setIdMatricula(ll_idMatricula);
						}
					}
				}
				else
				{
					String ls_idTurno;

					laap_areaPredio     = new AccAreaPredio();
					ls_idTurno          = aaap_areaPredioArg.getIdTurno();

					laap_areaPredio.setIdCirculo(ls_idCirculo);
					laap_areaPredio.setIdMatricula(ll_idMatricula);

					if(StringUtils.isValidString(ls_idTurno))
					{
						laap_areaPredio.setIdTurno(ls_idTurno);
						laap_areaPredio = laap_DAO.findByCirculoMatriculaTurno(laap_areaPredio);
					}
					else
						laap_areaPredio = laap_DAO.findByCirculoMatricula(laap_areaPredio);

					if(laap_areaPredio != null)
					{
						String ls_idAreaPredio;

						ls_idAreaPredio = laap_areaPredio.getIdArea();

						laaui_return.setConsulta(true);

						if(StringUtils.isValidString(ls_idAreaPredio))
						{
							DetalleAreaPredio       ldap_param;
							AccDetalleAreaPredioDAO ladap_DAO;

							ldap_param     = new DetalleAreaPredio();
							ladap_DAO      = DaoCreator.getAccDetalleAreaPredioDAO(ldm_manager);

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
							laaui_return.setIdCirculo(ls_idCirculo);
							laaui_return.setIdMatricula(ll_idMatricula);
						}
					}
				}

				laaui_return.setAreaPredio(laap_areaPredio);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultaAreaPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laaui_return;
	}

	/**
	 * Consulta en tabla.
	 *
	 * @param as_codigoActo correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean consultaEnTabla(String as_codigoActo)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_codigoActo))
			{
				int li_cantidadTabla;

				li_cantidadTabla = DaoCreator.getTipoActoDAO(ldm_manager).conteoTablaPorActo(as_codigoActo);

				if(li_cantidadTabla > 0)
					lb_return = true;
			}
		}
		catch(Exception lb2be_e)
		{
			clh_LOGGER.error("consultaEnTabla", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de consultar la informacion de la pantalla de retomar solicitud apartir de un documento y tipo documento.
	 *
	 * @param as_tipoDocumento tipo documento necesario parea la consulta
	 * @param as_numeroDocumento número documento necesario parea la consulta
	 * @param as_userId usuario logueado en la aplicación
	 * @return Collection<TramiteSolicitud> objeto en respuesta que contiene toda la de Retomar Solicitud
	 * @throws B2BException error si ocurre una excepción
	 * @throws SQLException error si ocurre una excepción SQL
	 */
	public synchronized Collection<TramiteSolicitud> consultaRetomarSolicitud(
	    String as_tipoDocumento, String as_numeroDocumento, String as_userId
	)
	    throws B2BException, SQLException
	{
		DAOManager                   ldm_manager;
		Collection<TramiteSolicitud> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_datos       = null;

		try
		{
			{
				int                       ll_count;
				StringBuilder             lsb_consulta;
				Map<Integer, Object>      llhm_criterios;
				List<Map<String, Object>> lll_response;

				ll_count           = 1;
				llhm_criterios     = new LinkedHashMap<Integer, Object>();
				lsb_consulta       = new StringBuilder(ConsultasUtilidades.CS_CONSULTA_RETOMAR_SOLICITUD);

				if(StringUtils.isValidString(as_numeroDocumento))
				{
					lsb_consulta.append("AND P.NUMERO_DOCUMENTO = ? ");
					llhm_criterios.put(Integer.valueOf(ll_count++), as_numeroDocumento);
				}

				if(StringUtils.isValidString(as_tipoDocumento))
				{
					lsb_consulta.append("AND TIPOD.ID_DOCUMENTO_TIPO = ?");
					llhm_criterios.put(Integer.valueOf(ll_count++), as_tipoDocumento);
				}

				lsb_consulta.append(" ORDER BY S.FECHA_CREACION DESC");
				lll_response = DaoCreator.getUtilDAO(ldm_manager).getCustonQuery(
					    lsb_consulta.toString(), llhm_criterios
					);

				if(lll_response != null)
				{
					lcp_datos = new ArrayList<TramiteSolicitud>();

					for(Map<String, Object> llhm_actual : lll_response)
					{
						if(llhm_actual != null)
						{
							TramiteSolicitud la_tmp;

							la_tmp = new TramiteSolicitud();

							{
								String ls_tmp;

								ls_tmp = llhm_actual.get(IdentificadoresCommon.ID_SOLICITUD).toString();

								if(StringUtils.isValidString(ls_tmp))
									la_tmp.setIdSolicitud(ls_tmp);
							}

							{
								String ls_tmp;

								ls_tmp = StringUtils.getString(
									    (Date)llhm_actual.get(IdentificadoresCommon.FECHA_CREACION),
									    FormatoFechaCommon.DIA_MES_ANIO
									);

								if(StringUtils.isValidString(ls_tmp))
									la_tmp.setFechaCreacion(DateUtils.getDate(ls_tmp, FormatoFechaCommon.DIA_MES_ANIO));
							}

							{
								String ls_tmp;

								ls_tmp = (llhm_actual.get(IdentificadoresCommon.NOMBRE_PROCESO) != null)
									? llhm_actual.get(IdentificadoresCommon.NOMBRE_PROCESO).toString() : null;

								if(StringUtils.isValidString(ls_tmp))
									la_tmp.setNombreProceso(ls_tmp);
							}

							{
								String ls_tmp;

								ls_tmp = (llhm_actual.get(IdentificadoresCommon.NOMBRE_SUBPROCESO) != null)
									? llhm_actual.get(IdentificadoresCommon.NOMBRE_SUBPROCESO).toString() : null;

								if(StringUtils.isValidString(ls_tmp))
									la_tmp.setNombreSubProceso(ls_tmp);
							}

							{
								String ls_tmp;

								ls_tmp = (llhm_actual.get(IdentificadoresCommon.TIPO_DOCUMENTO) != null)
									? llhm_actual.get(IdentificadoresCommon.TIPO_DOCUMENTO).toString() : null;

								if(StringUtils.isValidString(ls_tmp))
									la_tmp.setTipoDocumento(ls_tmp);
							}

							{
								String ls_tmp;

								ls_tmp = (llhm_actual.get(IdentificadoresCommon.NUMERO_DOCUMENTO) != null)
									? llhm_actual.get(IdentificadoresCommon.NUMERO_DOCUMENTO).toString() : null;

								if(StringUtils.isValidString(ls_tmp))
									la_tmp.setNumeroDocumento(ls_tmp);
							}

							{
								Clob   lc_clob;
								String ls_tmp;

								lc_clob = (Clob)llhm_actual.get(IdentificadoresCommon.COMENTARIO);

								if(lc_clob != null)
								{
									ls_tmp = ClobUtils.clobToString(lc_clob);

									if(StringUtils.isValidString(ls_tmp))
										la_tmp.setComentario(ls_tmp);
								}
							}

							lcp_datos.add(la_tmp);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultaRetomarSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Consultar info persona entidad exenta.
	 *
	 * @param ap_persona de ap persona
	 * @return el valor de registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Registro consultarInfoPersonaEntidadExenta(Persona ap_persona)
	    throws B2BException
	{
		Registro lr_registro;

		lr_registro = null;

		if(ap_persona != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Collection<Persona> lcp_persona;

				lcp_persona = new ArrayList<Persona>(1);

				lcp_persona.add(ap_persona);

				lr_registro = consultarInfoPersonas(lcp_persona, ldm_manager);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultarInfoPersonaEntidadExenta", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lr_registro;
	}

	/**
	 * Consulta en base de datos las matrículas que ya se encuentran relacionadas a un detalle de
	 * antiguo sistema.
	 *
	 * @param adas_detalle Objeto contenedor de la información de detalle ant sistema
	 * @return Colección con matrículas resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<String> consultarMatriculasParaDetalle(DetalleAntSistema adas_detalle)
	    throws B2BException
	{
		Collection<String> lcs_matriculas;
		DAOManager         ldm_manager;

		lcs_matriculas     = new LinkedList<String>();
		ldm_manager        = DaoManagerFactory.getDAOManager();

		try
		{
			if(adas_detalle == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			Collection<DetalleAntSistema> lcdas_detallesIguales;

			lcdas_detallesIguales = DaoCreator.getDetalleAntSistemaDAO(ldm_manager).findDetalleRegistro(adas_detalle);

			if(CollectionUtils.isValidCollection(lcdas_detallesIguales))
			{
				DatosAntSistemaDAO ldas_DAO;

				ldas_DAO = DaoCreator.getDatosAntSistemaDAO(ldm_manager);

				for(DetalleAntSistema ldas_detalle : lcdas_detallesIguales)
				{
					if(ldas_detalle != null)
					{
						DatosAntSistema ldas_datos;

						ldas_datos = new DatosAntSistema();

						ldas_datos.setIdDatosAntSistema(ldas_detalle.getIdDatosAntSistema());

						ldas_datos = ldas_DAO.findById(ldas_datos);

						if(ldas_datos != null)
						{
							String ls_idCirculo;
							Long   ll_idMatricula;

							ls_idCirculo       = ldas_datos.getIdCirculo();
							ll_idMatricula     = ldas_datos.getIdMatricula();

							if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
								lcs_matriculas.add(ls_idCirculo + "-" + ll_idMatricula);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarMatriculasParaDetalle", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lcs_matriculas.isEmpty())
			lcs_matriculas = null;

		return lcs_matriculas;
	}

	/**
	 * Método de consulta de testamentos.
	 *
	 * @param at_testamento de at testamento
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Testamento> consultarTestamentos(Testamento at_testamento)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Testamento> lct_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lct_datos       = null;

		try
		{
			if(at_testamento != null)
				lct_datos = DaoCreator.getTestamentoDAO(ldm_manager).findTestamentos(at_testamento);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lct_datos;
	}

	/**
	 * Retorna el valor del objeto de byte[].
	 *
	 * @param ar_registro correspondiente al valor del tipo de objeto Registro
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de byte[]
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public synchronized byte[] crearPdfCorrecciones(Registro ar_registro, String as_usuario, DAOManager adm_manager)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			if(ar_registro != null)
			{
				byte[]        lba_plantilla;
				Constantes    lc_constante;
				ConstantesDAO lc_DAO;
				String        ls_plantilla;

				lc_constante     = new Constantes();
				lc_DAO           = DaoCreator.getConstantesDAO(adm_manager);

				lc_constante.setIdConstante(ConstanteCommon.PLANTILLA_CORRECCIONES);

				lc_constante = lc_DAO.findImagen(lc_constante);

				if(lc_constante != null)
				{
					lba_plantilla = lc_constante.getImagenes();

					if(lba_plantilla != null)
					{
						Solicitud    ls_solicitud;
						SolicitudDAO ls_DAO;

						ls_DAO           = DaoCreator.getSolicitudDAO(adm_manager);
						ls_solicitud     = ls_DAO.findById(ar_registro.getSolicitud());
						ls_plantilla     = new String(lba_plantilla);

						if(ls_solicitud != null)
						{
							Map<String, String> lmss_datos;
							String              ls_idSolicitud;

							lmss_datos         = null;
							ls_idSolicitud     = ls_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(as_usuario) && ls_plantilla.contains("<TAG_USUARIO>"))
								ls_plantilla = ls_plantilla.replace("<TAG_USUARIO>", as_usuario);

							{
								Date       ld_fechaActual;
								DateFormat ldf_dateFormat;
								String     ls_fecha;

								ld_fechaActual     = new Date();
								ldf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
								ls_fecha           = ldf_dateFormat.format(ld_fechaActual);

								if(ls_plantilla.contains("<TAG_FECHA>"))
									ls_plantilla = ls_plantilla.replace("<TAG_FECHA>", ls_fecha);
							}

							{
								String ls_nir;

								ls_nir = ls_solicitud.getNir();

								if(StringUtils.isValidString(ls_nir) && ls_plantilla.contains("<TAG_NIR>"))
									ls_plantilla = ls_plantilla.replace("<TAG_NIR>", ls_nir);
							}

							String ls_tag;

							ls_tag = "<TAG_TURNO>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_turno;

								ls_turno = obtenerTurnoDeSolicitud(ls_idSolicitud, adm_manager);

								if(StringUtils.isValidString(ls_turno))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_turno);
							}

							{
								String ls_idPersona;

								ls_idPersona = ls_solicitud.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona))
								{
									Persona    lp_persona;
									PersonaDAO lp_DAO;

									lp_persona     = new Persona();
									lp_DAO         = DaoCreator.getPersonaDAO(adm_manager);

									lp_persona.setIdPersona(ls_idPersona);

									lp_persona = lp_DAO.findById(lp_persona);

									if(lp_persona != null)
									{
										if(ls_plantilla.contains("<TAG_ID_PERSONA>"))
										{
											StringBuilder lsb_nombreCompleto;

											lsb_nombreCompleto = new StringBuilder();

											String ls_primerApellido;
											String ls_primerNombre;
											String ls_segundoApellido;
											String ls_segundoNombre;

											ls_primerApellido      = lp_persona.getPrimerApellido();
											ls_primerNombre        = lp_persona.getPrimerNombre();
											ls_segundoApellido     = lp_persona.getSegundoApellido();
											ls_segundoNombre       = lp_persona.getSegundoNombre();

											if(StringUtils.isValidString(ls_primerNombre))
												lsb_nombreCompleto.append(ls_primerNombre);

											if(StringUtils.isValidString(ls_segundoNombre))
												lsb_nombreCompleto.append(" " + ls_segundoNombre);

											if(StringUtils.isValidString(ls_primerApellido))
												lsb_nombreCompleto.append(" " + ls_primerApellido);

											if(StringUtils.isValidString(ls_segundoApellido))
												lsb_nombreCompleto.append(" " + ls_segundoApellido);

											ls_plantilla = ls_plantilla.replace(
												    "<TAG_ID_PERSONA>", lsb_nombreCompleto.toString()
												);
										}

										if(ls_plantilla.contains("<TAG_TIPO_DOCUMENTO>"))
										{
											String ls_idTipoDoc;

											ls_idTipoDoc = lp_persona.getIdDocumentoTipo();

											if(StringUtils.isValidString(ls_idTipoDoc))
											{
												InteresadoDocumentoTipo    lidt_tipoDocumento;
												InteresadoDocumentoTipoDAO lidt_DAO;

												lidt_tipoDocumento     = new InteresadoDocumentoTipo();
												lidt_DAO               = DaoCreator.getInteresadoDocumentoTipoDAO(
													    adm_manager
													);

												lidt_tipoDocumento.setIdDocumentoTipo(ls_idTipoDoc);

												lidt_tipoDocumento = lidt_DAO.findById(lidt_tipoDocumento);

												if(lidt_tipoDocumento != null)
												{
													String ls_descripcion;

													ls_descripcion = lidt_tipoDocumento.getDescripcion();

													if(StringUtils.isValidString(ls_descripcion))
														ls_plantilla = ls_plantilla.replace(
															    "<TAG_TIPO_DOCUMENTO>", ls_descripcion
															);
												}
											}
										}

										{
											String ls_numeroDocumento;

											ls_numeroDocumento = lp_persona.getNumeroDocumento();

											if(StringUtils.isValidString(ls_numeroDocumento))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_NUM_DOC>", ls_numeroDocumento
													);
										}

										if(ls_plantilla.contains("<TAG_TELEFONO>"))
										{
											String ls_idTelefono;

											ls_idTelefono = ls_solicitud.getIdTelefono();

											if(StringUtils.isValidString(ls_idTelefono))
											{
												PersonaTelefono    lpt_telefono;
												PersonaTelefonoDAO lpt_DAO;

												lpt_telefono     = new PersonaTelefono();
												lpt_DAO          = DaoCreator.getPersonaTelefonoDAO(adm_manager);

												lpt_telefono.setIdPersona(ls_idPersona);
												lpt_telefono.setIdTelefono(ls_idTelefono);

												lpt_telefono = lpt_DAO.findById(lpt_telefono);

												if(lpt_telefono != null)
												{
													String ls_telefono;

													ls_telefono = lpt_telefono.getTelefono();

													if(StringUtils.isValidString(ls_telefono))
														ls_plantilla = ls_plantilla.replace(
															    "<TAG_TELEFONO>", ls_telefono
															);
												}
											}
										}

										if(ls_plantilla.contains("<TAG_DIRECCION>"))
										{
											String ls_idDireccion;

											ls_idDireccion = ls_solicitud.getIdDireccion();

											if(StringUtils.isValidString(ls_idDireccion))
											{
												PersonaDireccion    lpd_direccion;
												PersonaDireccionDAO lpd_DAO;

												lpd_direccion     = new PersonaDireccion();
												lpd_DAO           = DaoCreator.getPersonaDireccionDAO(adm_manager);

												lpd_direccion.setIdPersona(ls_idPersona);
												lpd_direccion.setIdDireccion(ls_idDireccion);

												lpd_direccion = lpd_DAO.findById(lpd_direccion);

												if(lpd_direccion != null)
												{
													StringBuilder lsb_direccionCompleta;
													String        ls_direccionCompleta;

													lsb_direccionCompleta     = new StringBuilder();
													ls_direccionCompleta      = null;

													{
														String ls_tipoEje;
														ls_tipoEje = lpd_direccion.getIdTipoEjePrincipal();

														if(StringUtils.isValidString(ls_tipoEje))
														{
															TipoEje lte_tmp;
															lte_tmp = new TipoEje();
															lte_tmp.setIdTipoEje(ls_tipoEje);

															lte_tmp = DaoCreator.getTipoEjeDAO(adm_manager)
																	                .findById(lte_tmp);

															if(lte_tmp != null)
																lsb_direccionCompleta.append(
																    StringUtils.getStringNotNull(lte_tmp.getNombre())
																    + " "
																);
														}
													}

													lsb_direccionCompleta.append(
													    StringUtils.getStringNotNull(
													        lpd_direccion.getDatoEjePrincipal()
													    ) + " "
													);

													{
														String ls_tipoEje1;
														ls_tipoEje1 = lpd_direccion.getIdTipoEjeSecundario();

														if(StringUtils.isValidString(ls_tipoEje1))
														{
															TipoEje lte_tmp;
															lte_tmp = new TipoEje();
															lte_tmp.setIdTipoEje(ls_tipoEje1);

															lte_tmp = DaoCreator.getTipoEjeDAO(adm_manager)
																	                .findById(lte_tmp);

															if(lte_tmp != null)
																lsb_direccionCompleta.append(
																    StringUtils.getStringNotNull(lte_tmp.getNombre())
																    + " "
																);
														}
													}

													lsb_direccionCompleta.append(
													    StringUtils.getStringNotNull(
													        lpd_direccion.getDatoEjeSecundario()
													    ) + " "
													);
													lsb_direccionCompleta.append(
													    StringUtils.getStringNotNull(
													        lpd_direccion.getComplementoDireccion()
													    ) + " "
													);

													{
														String ls_tmp;

														ls_tmp = lsb_direccionCompleta.toString();

														if(StringUtils.isValidString(ls_tmp))
														{
															ls_direccionCompleta     = ls_tmp.trim();

															ls_plantilla = ls_plantilla.replace(
																    "<TAG_DIRECCION>", ls_direccionCompleta
																);
														}
													}
												}
											}
										}

										if(ls_plantilla.contains("<TAG_CORREO_ELECTRONICO>"))
										{
											String ls_idCorreo;

											ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

											if(StringUtils.isValidString(ls_idCorreo))
											{
												PersonaCorreoElectronico    lpce_correo;
												PersonaCorreoElectronicoDAO lpce_DAO;

												lpce_correo     = new PersonaCorreoElectronico();
												lpce_DAO        = DaoCreator.getPersonaCorreoElectronicoDAO(
													    adm_manager
													);

												lpce_correo.setIdPersona(ls_idPersona);
												lpce_correo.setIdCorreoElectronico(ls_idCorreo);

												lpce_correo = lpce_DAO.findById(lpce_correo);

												if(lpce_correo != null)
												{
													String ls_correoElectronico;

													ls_correoElectronico = lpce_correo.getCorreoElectronico();

													if(StringUtils.isValidString(ls_correoElectronico))
														ls_plantilla = ls_plantilla.replace(
															    "<TAG_CORREO_ELECTRONICO>", ls_correoElectronico
															);
												}
											}
										}

										if(ls_plantilla.contains("<TAG_PROCEDENCIA>"))
										{
											String ls_idRecepcion;

											ls_idRecepcion = ls_solicitud.getIdTipoRecepcion();

											if(StringUtils.isValidString(ls_idRecepcion))
											{
												TipoRecepcion    ltr_recepcion;
												TipoRecepcionDAO ltr_DAO;

												ltr_recepcion     = new TipoRecepcion();
												ltr_DAO           = DaoCreator.getTipoRecepcionDAO(adm_manager);

												ltr_recepcion.setIdTipoRecepcion(ls_idRecepcion);

												ltr_recepcion = ltr_DAO.findById(ltr_recepcion);

												if(ltr_recepcion != null)
												{
													String ls_nombreProcedencia;

													ls_nombreProcedencia = ltr_recepcion.getNombre();

													if(StringUtils.isValidString(ls_nombreProcedencia))
														ls_plantilla = ls_plantilla.replace(
															    "<TAG_PROCEDENCIA>", ls_nombreProcedencia
															);
												}
											}
										}
									}
								}
							}

							lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

							{
								ByteArrayInputStream           lbais_docInStream;
								ByteArrayOutputStream          lbaos_docOutStream;
								Collection<SolicitudMatricula> lcsm_matriculas;
								Collection<CausalCorreccion>   lccc_causales;
								CausalCorreccionDAO            lcc_DAO;
								com.aspose.words.Document      ld_doc;
								DocumentBuilder                ldb_builder;
								SolicitudMatricula             lsm_solicitudMatricula;
								SolicitudMatriculaDAO          lsm_DAO;
								Table                          lt_table;

								lbais_docInStream          = new ByteArrayInputStream(ls_plantilla.getBytes());
								lbaos_docOutStream         = new ByteArrayOutputStream();
								lcc_DAO                    = DaoCreator.getCausalCorreccionDAO(adm_manager);
								ld_doc                     = new com.aspose.words.Document(lbais_docInStream);
								lsm_solicitudMatricula     = new SolicitudMatricula();
								lsm_DAO                    = DaoCreator.getSolicitudMatriculaDAO(adm_manager);
								ldb_builder                = new DocumentBuilder(ld_doc);

								ldb_builder.moveToDocumentEnd();

								lsm_solicitudMatricula.setIdSolicitud(ls_idSolicitud);

								lccc_causales       = lcc_DAO.findAll();
								lcsm_matriculas     = lsm_DAO.findByIdSolicitud(lsm_solicitudMatricula);

								ldb_builder.writeln(ControlChar.LINE_BREAK);

								if(
								    CollectionUtils.isValidCollection(lcsm_matriculas)
									    && CollectionUtils.isValidCollection(lccc_causales)
								)
								{
									SolicitudCorreccionDAO lsc_DAO;

									lsc_DAO      = DaoCreator.getSolicitudCorreccionDAO(adm_manager);
									lt_table     = ldb_builder.startTable();

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);

									ldb_builder.getFont().setColor(Color.black);
									ldb_builder.getFont().setSize(15);
									ldb_builder.getFont().setName("Arial");
									ldb_builder.getFont().setBold(true);

									ldb_builder.write("CORRECCIONES");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

									ldb_builder.endRow();

									for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
									{
										if(lsm_iterador != null)
										{
											Collection<SolicitudCorreccion> lcsc_correcciones;
											SolicitudCorreccion             lsc_correccion;
											String                          ls_matriculaCumpleta;
											String                          ls_idCirculo;
											BigInteger                      lbi_idMatricula;

											lsc_correccion           = new SolicitudCorreccion();
											ls_idCirculo             = lsm_iterador.getIdCirculo();
											lbi_idMatricula          = NumericUtils.getBigInteger(
												    lsm_iterador.getIdMatricula()
												);
											ls_matriculaCumpleta     = ls_idCirculo + "-" + lbi_idMatricula;

											lsc_correccion.setIdCirculo(ls_idCirculo);
											lsc_correccion.setIdMatricula(lbi_idMatricula);
											lsc_correccion.setIdSolicitud(ls_idSolicitud);

											lcsc_correcciones = lsc_DAO.findBySolicitudCirculoMatricula(lsc_correccion);

											if(CollectionUtils.isValidCollection(lcsc_correcciones))
											{
												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);

												ldb_builder.getFont().setSize(15);
												ldb_builder.getFont().setBold(true);

												ldb_builder.write(ls_matriculaCumpleta);

												ldb_builder.insertCell();
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

												ldb_builder.endRow();

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(40));

												ldb_builder.getFont().setSize(12);
												ldb_builder.write("CORRECCIÓN");

												ldb_builder.insertCell();
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(60));
												ldb_builder.getFont().setSize(12);
												ldb_builder.write("OBSERVACIÓN");

												ldb_builder.endRow();

												for(SolicitudCorreccion lsc_iterador : lcsc_correcciones)
												{
													if(lsc_iterador != null)
													{
														String ls_seleccionado;

														ls_seleccionado = lsc_iterador.getSeleccionado();

														if(
														    StringUtils.isValidString(ls_seleccionado)
															    && ls_seleccionado.equalsIgnoreCase(EstadoCommon.S)
														)
														{
															CausalCorreccion lcc_causal;

															lcc_causal = new CausalCorreccion();

															lcc_causal.setIdCausalCorreccion(
															    lsc_iterador.getIdCausalCorreccion()
															);

															lcc_causal = lcc_DAO.findById(lcc_causal);

															if(lcc_causal != null)
															{
																ldb_builder.insertCell();
																ldb_builder.getCellFormat()
																	           .setHorizontalMerge(CellMerge.NONE);
																ldb_builder.getCellFormat()
																	           .setPreferredWidth(
																	    PreferredWidth.fromPercent(40)
																	);
																ldb_builder.getFont().setSize(10);
																ldb_builder.getFont().setBold(false);
																ldb_builder.write(lcc_causal.getNombre());

																ldb_builder.insertCell();
																ldb_builder.getCellFormat()
																	           .setHorizontalMerge(CellMerge.NONE);
																ldb_builder.getCellFormat()
																	           .setPreferredWidth(
																	    PreferredWidth.fromPercent(60)
																	);
																ldb_builder.getFont().setSize(10);
																ldb_builder.getFont().setBold(false);
																ldb_builder.write(
																    StringUtils.getStringNotNull(
																        lsc_iterador.getObservacion()
																    )
																);

																ldb_builder.endRow();
															}
														}
													}
												}
											}
										}
									}

									lt_table.setAlignment(TableAlignment.CENTER);
									ldb_builder.endTable();
								}

								ldb_builder.writeln(ControlChar.LINE_BREAK);

								{
									lt_table = ldb_builder.startTable();

									AccCompletitudDocumentalDAO          lacd_DAO;
									Collection<AccCompletitudDocumental> lcacd_documentales;

									lacd_DAO     = DaoCreator.getAccCompletitudDocumentalDAO(adm_manager);

									lcacd_documentales = lacd_DAO.findAllByIdSolicitud(ls_solicitud.getIdSolicitud());

									if(CollectionUtils.isValidCollection(lcacd_documentales))
									{
										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(70));
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);

										ldb_builder.getFont().setColor(Color.black);
										ldb_builder.getFont().setSize(15);
										ldb_builder.getFont().setName("Arial");
										ldb_builder.getFont().setBold(true);

										ldb_builder.write("DOCUMENTOS ANEXOS");

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

										ldb_builder.endRow();

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
										ldb_builder.getFont().setSize(15);
										ldb_builder.write("TIPO DOCUMENTAL");

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(50));
										ldb_builder.getFont().setSize(15);
										ldb_builder.write("OBSERVACIONES");

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(30));
										ldb_builder.getFont().setSize(15);
										ldb_builder.write("TURNO RELACIONADO");

										ldb_builder.endRow();

										for(AccCompletitudDocumental lacd_iterador : lcacd_documentales)
										{
											if(lacd_iterador != null)
											{
												ldb_builder.insertCell();
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(20));
												ldb_builder.getFont().setSize(10);
												ldb_builder.getFont().setBold(false);
												ldb_builder.write(lacd_iterador.getIdTipoDocumental());

												ldb_builder.insertCell();
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(50));
												ldb_builder.getFont().setSize(10);
												ldb_builder.getFont().setBold(false);
												ldb_builder.write(
												    StringUtils.getStringNotNull(lacd_iterador.getObservaciones())
												);

												ldb_builder.insertCell();
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(30));
												ldb_builder.getFont().setSize(10);
												ldb_builder.getFont().setBold(false);

												String ls_idTurnoCertificado;

												ls_idTurnoCertificado = StringUtils.isValidString(
													    lacd_iterador.getIdTurnoCertificadoCorrecciones()
													) ? lacd_iterador.getIdTurnoCertificadoCorrecciones() : "";

												ldb_builder.write(ls_idTurnoCertificado);

												ldb_builder.endRow();
											}
										}
									}

									if(lt_table.getFirstRow() != null)
										lt_table.setAlignment(TableAlignment.CENTER);

									ldb_builder.endTable();
								}

								ld_doc.save(lbaos_docOutStream, SaveFormat.DOC);

								byte[] docBytes = lbaos_docOutStream.toByteArray();

								lba_return = new PDFGenerator().convertirRTFToPDF(docBytes, adm_manager);
							}
						}
					}
				}
			}
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("crearPdfCorrecciones", le_e);
			clh_LOGGER.error("crearPdfCorrecciones", lb2be_e);

			throw lb2be_e;
		}

		return lba_return;
	}

	/**
	 * Metodo encargado de generar PDF al finalizar el proceso de solicitud de registro.
	 *
	 * @param ar_parametros objeto contenedor de informacion necesaria para la generacion de PDF
	 * @param as_userId usuario logueado en la aplciación
	 * @return byte[] documento PDF ya generado
	 * @throws B2BException error si ocurre una excepción
	 */
	public synchronized byte[] crearPdfRegistro(Registro ar_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_pdf;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_pdf         = null;

		try
		{
			lba_pdf = crearPdfRegistro(ar_parametros, as_userId, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("crearPdfRegistro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_pdf;
	}

	/**
	 * Metodo encargado de generar PDF al finalizar el proceso de solicitud de registro.
	 *
	 * @param ar_parametros objeto contenedor de informacion necesaria para la generacion de PDF
	 * @param as_userId usuario logueado en la aplciación
	 * @param adm_manager Objeto con una conexion a la BD ya establecida
	 * @return byte[] documento PDF ya generado
	 * @throws B2BException error si ocurre una excepción
	 */
	public synchronized byte[] crearPdfRegistro(Registro ar_parametros, String as_userId, DAOManager adm_manager)
	    throws B2BException
	{
		byte[] lba_pdf;

		lba_pdf = null;

		try
		{
			byte[]                    lba_plantilla;
			Constantes                lc_plantilla;
			String                    ls_plantilla;
			ConstantesDAO             lc_DAO;
			UtilDAO                   lud_utilDao;
			List<Map<String, Object>> llmso_datos;

			lud_utilDao       = DaoCreator.getUtilDAO(adm_manager);
			lc_DAO            = DaoCreator.getConstantesDAO(adm_manager);
			lc_plantilla      = null;
			ls_plantilla      = null;
			lba_plantilla     = null;

			if(ar_parametros != null)
			{
				String                         ls_solicitud;
				String                         ls_tag;
				LinkedHashMap<Integer, Object> lhmpCriterios;

				ls_solicitud      = ar_parametros.getSolicitud().getIdSolicitud();
				lhmpCriterios     = new LinkedHashMap<Integer, Object>();
				lhmpCriterios.put(Integer.valueOf(1), ls_solicitud);
				llmso_datos     = lud_utilDao.getCustonQuery(
					    ConsultasUtilidades.CS_CONSULTA_NIR_SOLICITUD, lhmpCriterios
					);

				ls_tag = null;

				boolean lb_procesoCertificados;
				boolean lb_esGrabacionMatricula;
				boolean lb_esProrrogaDocumentacion;
				boolean lb_esSuspensionEntregaDocumentacion;
				boolean lb_esProcesoDesistimiento;
				boolean lb_esProrrogaMayorValor;
				boolean lb_esSuspensionArticulo;
				boolean lb_esRestitucion;
				boolean lb_esProcesoCorrecciones;
				boolean lb_procesoSinLiquidacion;
				boolean lb_esImpuestoRegistro;
				boolean lb_esRecepcionRecursos;
				boolean lb_esSegundaInstancia;
				boolean lb_esCreacionMatriculaOficio;
				String  ls_tituloRecepcion;

				lb_procesoCertificados                  = ar_parametros.isProcesoCertificados();
				lb_esProrrogaDocumentacion              = ar_parametros.isEsProrrogaDocumentacion();
				lb_esGrabacionMatricula                 = ar_parametros.isEsGrabacionMatriculas();
				lb_esSuspensionEntregaDocumentacion     = ar_parametros.isEsSuspensionEntregaDocumentacion();
				lb_esProcesoDesistimiento               = ar_parametros.isEsProcesoDesistimiento();
				lb_esProrrogaMayorValor                 = ar_parametros.isEsProrrogaMayorValor();
				lb_esSuspensionArticulo                 = ar_parametros.isEsArticuloSuspension();
				lb_esRestitucion                        = ar_parametros.isEsRestitucion();
				lb_esProcesoCorrecciones                = ar_parametros.isProcesoCorrecciones();
				lb_esImpuestoRegistro                   = ar_parametros.isEsImpuestoRegistroGob();
				lb_esRecepcionRecursos                  = ar_parametros.isEsRecepcionRecursos();
				lb_esSegundaInstancia                   = ar_parametros.isEsSegundaInstancia();
				lb_esCreacionMatriculaOficio            = ar_parametros.isEsCreacionMatriculaOficio();
				lb_procesoSinLiquidacion                = lb_esProcesoCorrecciones || lb_esGrabacionMatricula;

				if(lb_esRecepcionRecursos || lb_esSegundaInstancia)
					lc_plantilla = lc_DAO.findImagen(
						    new Constantes(ConstanteCommon.PLANTILLA_INGRESO_SOLICITUD_REGISTRO_RECEPCION_DOCUMENTO)
						);
				else if(lb_procesoSinLiquidacion)
					lc_plantilla = lc_DAO.findImagen(
						    new Constantes(ConstanteCommon.PLANTILLA_INGRESO_SOLICITUD_REGISTRO_SIN_LIQUIDACION)
						);
				else
					lc_plantilla = lc_DAO.findImagen(
						    new Constantes(ConstanteCommon.PLANTILLA_INGRESO_SOLICITUD_REGISTRO_CON_LIQUIDACION)
						);

				if(lc_plantilla != null)
					lba_plantilla = lc_plantilla.getImagenes();

				if(lba_plantilla != null)
					ls_plantilla = new String(lba_plantilla);

				if(!StringUtils.isValidString(ls_plantilla))
					throw new B2BException("No se encontró plantilla de ingreso de solicitud");

				ls_tituloRecepcion = "SOLICITUD RECEPCIÓN DE DOCUMENTOS";

				if(lb_esCreacionMatriculaOficio)
					ls_tituloRecepcion = "SOLICITUD CREACIÓN MATRÍCULAS DE OFICIO";

				if(lb_esRecepcionRecursos || lb_esSegundaInstancia)
				{
					String ls_turnoAnt;

					ls_turnoAnt = ar_parametros.getIdTurno();

					{
						ls_tag = "<TAG_TITULO>";

						if(ls_plantilla.contains(ls_tag))
						{
							if(StringUtils.isValidString(ls_tituloRecepcion))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_tituloRecepcion);
						}
					}

					{
						Date   ld_fechaCreacion;
						String ls_fechaCreacion;

						ld_fechaCreacion     = ar_parametros.getFechaCreacion();
						ls_fechaCreacion     = "";

						if(ld_fechaCreacion != null)
							ls_fechaCreacion = StringUtils.getString(ld_fechaCreacion, FormatoFechaCommon.DIA_MES_ANIO);

						ls_tag = "<TAG_FECHA_CREACION>";

						if(ls_plantilla.contains(ls_tag))
						{
							if(StringUtils.isValidString(ls_fechaCreacion))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaCreacion);
						}
					}

					{
						String ls_nir;

						ls_nir     = ar_parametros.getNirProceso();

						ls_tag = "<TAG_NIR>";

						if(ls_plantilla.contains(ls_tag))
						{
							if(StringUtils.isValidString(ls_nir))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
						}
					}

					{
						ls_tag = "<TAG_TURNO>";

						if(ls_plantilla.contains(ls_tag))
						{
							if(StringUtils.isValidString(ls_turnoAnt))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_turnoAnt);
						}
					}

					{
						Constantes lc_constante;
						String     ls_descripcion;

						lc_constante       = DaoCreator.getConstantesDAO(adm_manager)
								                           .findById(ConstanteCommon.ORDENACION_TRAMITE_RECURSO);
						ls_descripcion     = (lc_constante != null) ? lc_constante.getCaracter() : null;
						ls_tag             = "<TAG_ORDENACION_TRAMITE_RECURSO>";

						if(ls_plantilla.contains(ls_tag))
						{
							if(StringUtils.isValidString(ls_descripcion))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_descripcion);
						}
					}

					if(StringUtils.isValidString(ls_solicitud))
					{
						Solicitud ls_solicitudTmp;

						ls_solicitudTmp = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_solicitud);

						if(ls_solicitudTmp != null)
						{
							String ls_idPersona;

							ls_idPersona = ls_solicitudTmp.getIdPersona();

							if(StringUtils.isValidString(ls_idPersona))
							{
								Persona lp_persona;

								lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);

								if(lp_persona != null)
								{
									{
										String ls_nombreCompleto;

										ls_nombreCompleto     = lp_persona.getNombreCompleto();

										ls_tag = "<TAG_NOMBRE>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_nombreCompleto))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreCompleto);
										}
									}

									{
										InteresadoDocumentoTipo lidt_interesadoDocumentoTipo;
										String                  ls_tipoDocumento;

										lidt_interesadoDocumentoTipo     = DaoCreator.getInteresadoDocumentoTipoDAO(
											    adm_manager
											).findById(lp_persona.getIdDocumentoTipo());

										ls_tipoDocumento     = (lidt_interesadoDocumentoTipo != null)
											? lidt_interesadoDocumentoTipo.getDescripcion() : "";

										ls_tag = "<TAG_TIPO_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_tipoDocumento))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoDocumento);
										}
									}

									{
										String ls_numeroDocumento;

										ls_numeroDocumento     = lp_persona.getNumeroDocumento();

										ls_tag = "<TAG_NUMERO_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_numeroDocumento))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_numeroDocumento);
										}
									}

									{
										PersonaTelefono lpt_personaTelefono;
										String          ls_telefono;

										lpt_personaTelefono     = DaoCreator.getPersonaTelefonoDAO(adm_manager)
												                                .findById(
												    ls_idPersona, ls_solicitudTmp.getIdTelefono()
												);

										ls_telefono     = (lpt_personaTelefono != null)
											? lpt_personaTelefono.getTelefono() : "";

										ls_tag = "<TAG_CONTACTO>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_telefono))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_telefono);
										}
									}

									{
										PersonaDireccion lpd_personaDireccion;
										String           ls_direccion;

										lpd_personaDireccion     = DaoCreator.getPersonaDireccionDAO(adm_manager)
												                                 .findById(
												    ls_idPersona, ls_solicitudTmp.getIdDireccion()
												);

										ls_direccion     = (lpd_personaDireccion != null)
											? lpd_personaDireccion.getDireccion() : "";

										ls_tag = "<TAG_DIRECCION>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_direccion))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_direccion);
										}
									}

									{
										PersonaCorreoElectronico lpce_personaCorreo;
										String                   ls_correo;

										lpce_personaCorreo     = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
												                               .findById(
												    ls_idPersona, ls_solicitudTmp.getIdCorreoElectronico()
												);

										ls_correo     = (lpce_personaCorreo != null)
											? lpce_personaCorreo.getCorreoElectronico() : "";

										ls_tag = "<TAG_EMAIL>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_correo))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_correo);
										}
									}

									{
										Turno  lt_turno;
										String ls_circulo;

										lt_turno       = DaoCreator.getTurnoDAO(adm_manager).findById(ls_turnoAnt);
										ls_circulo     = "";

										if(lt_turno != null)
										{
											String ls_idCirculo;

											ls_idCirculo = lt_turno.getIdCirculo();

											if(StringUtils.isValidString(ls_idCirculo))
											{
												CirculoRegistral lcr_circuloRegistral;

												lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
														                             .findById(ls_idCirculo);

												if(lcr_circuloRegistral != null)
													ls_circulo = lcr_circuloRegistral.getNombre();
											}
										}

										ls_tag = "<TAG_PROCEDENCIA_RECURSO>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_circulo))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_circulo);
										}
									}

									{
										Subproceso lsp_subProceso;
										String     ls_subProceso;

										lsp_subProceso     = DaoCreator.getSubprocesoDAO(adm_manager)
												                           .findById(
												    ls_solicitudTmp.getIdProceso(), ls_solicitudTmp.getIdSubproceso()
												);

										ls_subProceso     = (lsp_subProceso != null) ? lsp_subProceso.getNombre() : "";
										ls_tag            = "<TAG_RECURSO>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_subProceso))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_subProceso);
										}
									}
								}
							}
						}
					}
				}
				else
				{
					{
						if(lb_esGrabacionMatricula)
							ls_tituloRecepcion = "SOLICITUD GRABACIÓN MATRÍCULAS";
						else if(
						    !lb_esProrrogaDocumentacion || !lb_esSuspensionEntregaDocumentacion
							    || !lb_esProcesoDesistimiento || !lb_esProrrogaMayorValor || !lb_esSuspensionArticulo
							    || !lb_esRestitucion || !lb_esImpuestoRegistro || !lb_esRecepcionRecursos
							    || !lb_esSegundaInstancia || !lb_esCreacionMatriculaOficio
						)
							ls_tituloRecepcion = "RESUMEN DE INGRESO DE SOLICITUD";

						ls_tag = "<TAG_TITULO>";

						if(ls_plantilla.contains(ls_tag))
						{
							if(StringUtils.isValidString(ls_tituloRecepcion))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_tituloRecepcion);
						}
					}

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
								boolean lb_recepcionDocumentos;

								lb_recepcionDocumentos = ar_parametros.isRecepcionDocumentos();

								{
									String ls_nir;

									if(lb_recepcionDocumentos)
										ls_nir = ar_parametros.getNirProceso();
									else
										ls_nir = llhm_actual.get(IdentificadoresCommon.NIR).toString();

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

									if(lb_recepcionDocumentos)
									{
										Date ld_fechaCreacion;

										ld_fechaCreacion = ar_parametros.getFechaCreacion();

										if(ld_fechaCreacion != null)
											ls_fechaCreacion = StringUtils.getString(ld_fechaCreacion, ls_formato);
									}
									else
									{
										Object lo_fechaCreacion;

										lo_fechaCreacion = llhm_actual.get("FECHA_CREACION");

										if(lo_fechaCreacion != null)
											ls_fechaCreacion = StringUtils.getString(
												    (Date)lo_fechaCreacion, ls_formato
												);
									}

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

									ls_s = ar_parametros.getSolicitud();

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

								if(lb_procesoSinLiquidacion)
								{
									String ls_turno;

									ls_turno     = obtenerTurnoDeSolicitud(ls_solicitud, adm_manager);

									ls_tag = "<TAG_TURNO>";

									if(ls_plantilla.contains(ls_tag))
									{
										if(StringUtils.isValidString(ls_turno))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_turno);
									}
								}
							}
						}
					}
				}

				{
					ls_tag = "<TAG_TABLAS_DINAMICAS>";

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

							ls_tagNew     = "{\\*\\bkmkstart TAG_TABLAS_DINAMICAS}{\\*\\bkmkend TAG_TABLAS_DINAMICAS} \\line "
								+ ls_tag;

							ls_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
								+ IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
						}
					}

					ls_plantilla     = limpiarTags(ls_plantilla);
					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);
				}

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
				li_tamanoLetra          = 9;

				ldb_builder.moveToBookmark("TAG_TABLAS_DINAMICAS");

				if(
				    (!lb_esProrrogaDocumentacion || !lb_esSuspensionEntregaDocumentacion || !lb_esProcesoDesistimiento
					    || !lb_esProrrogaMayorValor || !lb_esSuspensionArticulo || !lb_esRestitucion
					    || !lb_esImpuestoRegistro || !lb_esRecepcionRecursos || !lb_esSegundaInstancia)
					    && !lb_esCreacionMatriculaOficio
				)
				{
					Table lt_table;

					ldb_builder.writeln(ControlChar.LINE_BREAK);

					lt_table = ldb_builder.startTable();

					{
						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("MATRÍCULA INMOBILIARIA");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("DATOS DEL PREDIO");

						for(int i = 0; i < 2; i++)
						{
							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
						}

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("TIPO PREDIO");

						ldb_builder.endRow();
					}

					if(!lb_esGrabacionMatricula)
					{
						{
							lhmpCriterios = new LinkedHashMap<Integer, Object>();
							lhmpCriterios.put(Integer.valueOf(1), ls_solicitud);
							llmso_datos = lud_utilDao.getCustonQuery(
								    ConsultasUtilidades.CS_MATRICULA_PREDIO, lhmpCriterios
								);

							if(CollectionUtils.isValidCollection(llmso_datos))
							{
								Iterator<Map<String, Object>> li_consultaDatosPredio;

								li_consultaDatosPredio = llmso_datos.iterator();

								while(li_consultaDatosPredio.hasNext())
								{
									Map<String, Object> llhm_actual;

									llhm_actual = li_consultaDatosPredio.next();

									if(llhm_actual != null)
									{
										{
											String ls_matriculaPredio;

											ls_matriculaPredio = llhm_actual.get("MATRICULA_INMOBILIARIA").toString();

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Courier New");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_matriculaPredio) ? ls_matriculaPredio : ""
											);
										}

										{
											String ls_datosPredio;

											ls_datosPredio = (llhm_actual.get("DIRECCION") != null)
												? llhm_actual.get("DIRECCION").toString() : null;

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Courier New");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_datosPredio) ? ls_datosPredio : ""
											);

											for(int i = 0; i < 2; i++)
											{
												ldb_builder.insertCell();
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
											}
										}

										{
											String ls_tipoPredio;

											ls_tipoPredio = (llhm_actual.get("TIPO_PREDIO") != null)
												? llhm_actual.get("TIPO_PREDIO").toString() : null;

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Courier New");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_tipoPredio) ? ls_tipoPredio : ""
											);
										}

										ldb_builder.endRow();
									}
								}
							}
						}
					}
					else
					{
						Solicitud ls_solicitudTMP;
						ls_solicitudTMP = new Solicitud();

						ls_solicitudTMP.setIdSolicitud(ls_solicitud);

						ls_solicitudTMP = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_solicitudTMP);

						if(ls_solicitudTMP != null)
						{
							DatosAntSistema ldas_datosAntSistema;
							ldas_datosAntSistema = DaoCreator.getDatosAntSistemaDAO(adm_manager)
									                             .findByIdSolicitudOne(
									    ls_solicitudTMP.getIdSolicitud()
									);

							if(ldas_datosAntSistema != null)
							{
								{
									String ls_matriculaPredio;

									ls_matriculaPredio = StringUtils.getString(
										    ldas_datosAntSistema.getIdMatriculaGrabacion()
										);

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write(
									    StringUtils.isValidString(ls_matriculaPredio) ? ls_matriculaPredio : ""
									);
								}

								{
									String ls_datosPredio;

									Departamento ld_departamento;
									ld_departamento = new Departamento();
									ld_departamento.setIdPais(ldas_datosAntSistema.getIdPais());
									ld_departamento.setIdDepartamento(ldas_datosAntSistema.getIdDepartamento());

									ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
											                        .findById(ld_departamento);

									Municipio lm_municipio;
									lm_municipio = new Municipio();

									lm_municipio.setIdPais(ldas_datosAntSistema.getIdPais());
									lm_municipio.setIdDepartamento(ldas_datosAntSistema.getIdDepartamento());
									lm_municipio.setIdMunicipio(ldas_datosAntSistema.getIdMunicipio());

									lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findById(lm_municipio);

									if((lm_municipio != null) && (ld_departamento != null))
									{
										ls_datosPredio = lm_municipio.getNombre() + " " + ld_departamento.getNombre()
											+ " " + ldas_datosAntSistema.getNombrePredio();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_datosPredio) ? ls_datosPredio : ""
										);

										for(int i = 0; i < 2; i++)
										{
											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
										}
									}
								}

								{
									String ls_tipoPredio;

									PredioTipo lpt_tipoPredio;
									lpt_tipoPredio = new PredioTipo();
									lpt_tipoPredio.setIdTipoPredio(ldas_datosAntSistema.getIdTipoPredio());

									lpt_tipoPredio = DaoCreator.getPredioTipoDao(adm_manager).findById(lpt_tipoPredio);

									if(lpt_tipoPredio != null)
									{
										ls_tipoPredio = lpt_tipoPredio.getDescripcion();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_tipoPredio) ? ls_tipoPredio : ""
										);
									}
								}

								ldb_builder.endRow();
							}
						}
					}

					lt_table.setAlignment(TableAlignment.CENTER);
					ldb_builder.endTable();
				}

				boolean lb_grabacionOProrrogaOSuspensionODesistimiento;

				lb_grabacionOProrrogaOSuspensionODesistimiento = lb_esGrabacionMatricula || lb_esProrrogaDocumentacion
						|| lb_esSuspensionEntregaDocumentacion || lb_esProcesoDesistimiento || lb_esProrrogaMayorValor
						|| lb_esSuspensionArticulo || lb_esRestitucion || lb_esImpuestoRegistro
						|| lb_esRecepcionRecursos || lb_esSegundaInstancia;

				if(
				    !lb_grabacionOProrrogaOSuspensionODesistimiento && !lb_procesoCertificados
					    && !lb_esCreacionMatriculaOficio
				)
				{
					Table lt_table;

					ldb_builder.writeln(ControlChar.LINE_BREAK);

					lt_table = ldb_builder.startTable();

					{
						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("APERTURA DE MATRÍCULAS");

						for(int i = 0; i < 6; i++)
						{
							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
						}

						ldb_builder.endRow();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("UNIDAD");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("NOMBRE DEL PREDIO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("DIRECCIÓN DEL PREDIO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("AREA TERRENO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("AREA PRIVADA");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("AREA CONSTRUIDA");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("COEFICIENTE");

						ldb_builder.endRow();

						int    li_i;
						String ls_m2;
						li_i      = 1;
						ls_m2     = "m²";

						lhmpCriterios = new LinkedHashMap<Integer, Object>();
						lhmpCriterios.put(Integer.valueOf(1), ls_solicitud);
						llmso_datos = lud_utilDao.getCustonQuery(
							    ConsultasUtilidades.CS_MATRICULAS_APERTURA, lhmpCriterios
							);

						if(CollectionUtils.isValidCollection(llmso_datos))
						{
							Iterator<Map<String, Object>> li_datosDocumento;

							li_datosDocumento = llmso_datos.iterator();

							while(li_datosDocumento.hasNext())
							{
								Map<String, Object> llhm_actual;

								llhm_actual = li_datosDocumento.next();

								if(llhm_actual != null)
								{
									{
										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(String.valueOf(li_i++));
									}

									{
										String ls_predio;

										ls_predio = llhm_actual.get("NOMBRE_PREDIO").toString();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_predio) ? ls_predio : "");
									}

									{
										String ls_direccion;

										ls_direccion = (llhm_actual.get("DIRECCION") != null)
											? llhm_actual.get("DIRECCION").toString() : null;

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_direccion) ? ls_direccion : "");
									}

									{
										String ls_areaTerreno;

										ls_areaTerreno = (llhm_actual.get("AREA_TERRENO") != null)
											? llhm_actual.get("AREA_TERRENO").toString() : null;

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_areaTerreno) ? (ls_areaTerreno + " " + ls_m2)
										                                              : ""
										);
									}

									{
										String ls_areaPrivada;

										ls_areaPrivada = (llhm_actual.get("AREA_PRIVADA") != null)
											? llhm_actual.get("AREA_PRIVADA").toString() : null;

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_areaPrivada) ? (ls_areaPrivada + " " + ls_m2)
										                                              : ""
										);
									}

									{
										String ls_areaConstruida;

										ls_areaConstruida = null;

										if(llhm_actual.get("AREA_CONSTRUIDA") != null)
											ls_areaConstruida = llhm_actual.get("AREA_CONSTRUIDA").toString();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_areaConstruida)
										    ? (ls_areaConstruida + " " + ls_m2) : ""
										);
									}

									{
										String ls_coeficiente;

										ls_coeficiente = null;

										if(llhm_actual.get("COEFICIENTE") != null)
											ls_coeficiente = llhm_actual.get("COEFICIENTE").toString();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_coeficiente) ? (ls_coeficiente + " %") : ""
										);
									}

									ldb_builder.endRow();
								}
							}
						}
					}

					lt_table.setAlignment(TableAlignment.CENTER);
					ldb_builder.endTable();
				}

				if(lb_esImpuestoRegistro)
				{
					Table lt_table;

					ldb_builder.writeln(ControlChar.LINE_BREAK);

					lt_table = ldb_builder.startTable();

					{
						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("DOCUMENTO");

						for(int i = 0; i < 4; i++)
						{
							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
						}

						ldb_builder.endRow();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("CLASE");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("NÚMERO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("FECHA");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("OFICINA DE ORIGEN");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("RECEPCIÓN");

						ldb_builder.endRow();

						Collection<Turno> lct_turnosImpuesto;
						lct_turnosImpuesto = ar_parametros.getSolicitud().getInfoTurnosImpuestosGobernacion();

						if(CollectionUtils.isValidCollection(lct_turnosImpuesto))
						{
							for(Turno lt_tmp : lct_turnosImpuesto)
							{
								if(lt_tmp != null)
								{
									Collection<TipoDocumental> lctd_actosUI;

									lctd_actosUI = lt_tmp.getActosui();

									if(CollectionUtils.isValidCollection(lctd_actosUI))
									{
										Iterator<TipoDocumental> litd_tiposDoc;

										litd_tiposDoc = lctd_actosUI.iterator();

										if(litd_tiposDoc.hasNext())
										{
											TipoDocumental ltd_tipoDoc;
											ltd_tipoDoc = litd_tiposDoc.next();

											if(ltd_tipoDoc != null)
											{
												{
													String ls_idTipoDoc;

													ls_idTipoDoc = ltd_tipoDoc.getIdTipoDocumental();

													if(
													    (StringUtils.isValidString(ls_idTipoDoc)
														    && ls_idTipoDoc.equalsIgnoreCase(
														        TipoDocumentalCommon.BOLETA_FISCAL
														    ))
														    || ls_idTipoDoc.equalsIgnoreCase(
														        TipoDocumentalCommon.BOLETA_FISCAL_EXTEMPORANEA
														    )
													)
													{
														TipoDocumental ltd_tipoDocTMP;
														ltd_tipoDocTMP = DaoCreator.getTipoDocumentalDAO(adm_manager)
																                       .findById(ls_idTipoDoc);

														if(ltd_tipoDocTMP != null)
														{
															String ls_clase;

															ls_clase = ltd_tipoDocTMP.getNombre();

															ldb_builder.insertCell();
															ldb_builder.getParagraphFormat()
																           .setAlignment(ParagraphAlignment.CENTER);
															ldb_builder.setBold(true);
															ldb_builder.getFont().setName("Courier New");
															ldb_builder.getFont().setUnderline(0);
															ldb_builder.getFont().setSize(li_tamanoLetra);
															ldb_builder.getCellFormat()
																           .setPreferredWidth(
																    PreferredWidth.fromPercent(ll_porcentajetablas)
																);
															ldb_builder.getCellFormat()
																           .setHorizontalMerge(CellMerge.NONE);
															ldb_builder.getCellFormat()
																           .setVerticalAlignment(
																    CellVerticalAlignment.CENTER
																);
															ldb_builder.write(
															    StringUtils.isValidString(ls_clase) ? ls_clase : ""
															);
														}

														{
															String ls_numero;

															ls_numero = null;

															if(
															    ls_idTipoDoc.equalsIgnoreCase(
																        TipoDocumentalCommon.BOLETA_FISCAL
																    )
															)
																ls_numero = ltd_tipoDoc.getNumeroBoletaFiscal();
															else if(
															    ls_idTipoDoc.equalsIgnoreCase(
																        TipoDocumentalCommon.BOLETA_FISCAL_EXTEMPORANEA
																    )
															)
																ls_numero = ltd_tipoDoc.getNumeroBoletaFiscalExt();

															ldb_builder.insertCell();
															ldb_builder.getParagraphFormat()
																           .setAlignment(ParagraphAlignment.CENTER);
															ldb_builder.setBold(true);
															ldb_builder.getFont().setName("Courier New");
															ldb_builder.getFont().setUnderline(0);
															ldb_builder.getFont().setSize(li_tamanoLetra);
															ldb_builder.getCellFormat()
																           .setPreferredWidth(
																    PreferredWidth.fromPercent(ll_porcentajetablas)
																);
															ldb_builder.getCellFormat()
																           .setHorizontalMerge(CellMerge.NONE);
															ldb_builder.getCellFormat()
																           .setVerticalAlignment(
																    CellVerticalAlignment.CENTER
																);
															ldb_builder.write(
															    StringUtils.isValidString(ls_numero) ? ls_numero : ""
															);
														}

														{
															String ls_fecha;

															ls_fecha = null;

															if(
															    ls_idTipoDoc.equalsIgnoreCase(
																        TipoDocumentalCommon.BOLETA_FISCAL
																    )
															)
																ls_fecha = StringUtils.getString(
																	    (new SimpleDateFormat(
																	        FormatoFechaCommon.DIA_MES_ANIO
																	    ).format(ltd_tipoDoc.getFechaPagoImpuesto()))
																	);

															else if(
															    ls_idTipoDoc.equalsIgnoreCase(
																        TipoDocumentalCommon.BOLETA_FISCAL_EXTEMPORANEA
																    )
															)
																ls_fecha = StringUtils.getString(
																	    (new SimpleDateFormat(
																	        FormatoFechaCommon.DIA_MES_ANIO
																	    ).format(
																	        ltd_tipoDoc.getFechaPagoImpuestoExtemporaneo()
																	    ))
																	);

															ldb_builder.insertCell();
															ldb_builder.getParagraphFormat()
																           .setAlignment(ParagraphAlignment.CENTER);
															ldb_builder.setBold(true);
															ldb_builder.getFont().setName("Courier New");
															ldb_builder.getFont().setUnderline(0);
															ldb_builder.getFont().setSize(li_tamanoLetra);
															ldb_builder.getCellFormat()
																           .setPreferredWidth(
																    PreferredWidth.fromPercent(ll_porcentajetablas)
																);
															ldb_builder.getCellFormat()
																           .setHorizontalMerge(CellMerge.NONE);
															ldb_builder.getCellFormat()
																           .setVerticalAlignment(
																    CellVerticalAlignment.CENTER
																);
															ldb_builder.write(
															    StringUtils.isValidString(ls_fecha) ? ls_fecha : ""
															);
														}

														{
															String           ls_circulo;
															CirculoRegistral lcr_circuloRegistral;

															ls_circulo               = lt_tmp.getIdCirculo();
															lcr_circuloRegistral     = DaoCreator.getCirculoRegistralDAO(
																    adm_manager
																).findById(ls_circulo);

															if(lcr_circuloRegistral != null)
															{
																ls_circulo = ls_circulo
																	+ IdentificadoresCommon.SIMBOLO_GUION
																	+ lcr_circuloRegistral.getNombre();

																ldb_builder.insertCell();
																ldb_builder.getParagraphFormat()
																	           .setAlignment(ParagraphAlignment.CENTER);
																ldb_builder.setBold(true);
																ldb_builder.getFont().setName("Courier New");
																ldb_builder.getFont().setUnderline(0);
																ldb_builder.getFont().setSize(li_tamanoLetra);
																ldb_builder.getCellFormat()
																	           .setPreferredWidth(
																	    PreferredWidth.fromPercent(ll_porcentajetablas)
																	);
																ldb_builder.getCellFormat()
																	           .setHorizontalMerge(CellMerge.NONE);
																ldb_builder.getCellFormat()
																	           .setVerticalAlignment(
																	    CellVerticalAlignment.CENTER
																	);
																ldb_builder.write(
																    StringUtils.isValidString(ls_circulo) ? ls_circulo
																                                          : ""
																);
															}
														}

														{
															String ls_medioRecepcion;

															ls_medioRecepcion = ltd_tipoDoc.getMedioRecepcion();

															ldb_builder.insertCell();
															ldb_builder.getParagraphFormat()
																           .setAlignment(ParagraphAlignment.CENTER);
															ldb_builder.setBold(true);
															ldb_builder.getFont().setName("Courier New");
															ldb_builder.getFont().setUnderline(0);
															ldb_builder.getFont().setSize(li_tamanoLetra);
															ldb_builder.getCellFormat()
																           .setPreferredWidth(
																    PreferredWidth.fromPercent(ll_porcentajetablas)
																);
															ldb_builder.getCellFormat()
																           .setHorizontalMerge(CellMerge.NONE);
															ldb_builder.getCellFormat()
																           .setVerticalAlignment(
																    CellVerticalAlignment.CENTER
																);
															ldb_builder.write(
															    StringUtils.isValidString(ls_medioRecepcion)
															    ? ls_medioRecepcion : ""
															);
														}

														ldb_builder.endRow();
													}
												}
											}
										}
									}
								}
							}
						}
					}

					lt_table.setAlignment(TableAlignment.CENTER);
					ldb_builder.endTable();
				}

				if(lb_esCreacionMatriculaOficio)
				{
					Table lt_table;

					ldb_builder.writeln(ControlChar.LINE_BREAK);

					lt_table = ldb_builder.startTable();

					{
						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("DATOS ANTIGUO SISTEMA");

						for(int i = 0; i < 4; i++)
						{
							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
						}

						ldb_builder.endRow();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("LIBRO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("TOMO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("FOLIO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("PARTIDA");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("NUMERO DE PARTIDA");

						ldb_builder.endRow();

						DatosAntSistema             ldas_datosAntSistemaTmp;
						Collection<DatosAntSistema> lcdas_tmp;

						ldas_datosAntSistemaTmp = new DatosAntSistema();
						ldas_datosAntSistemaTmp.setIdSolicitud(ls_solicitud);

						lcdas_tmp = DaoCreator.getDatosAntSistemaDAO(adm_manager)
								                  .findByIdSolicitud(ldas_datosAntSistemaTmp, false);

						if(CollectionUtils.isValidCollection(lcdas_tmp))
						{
							for(DatosAntSistema ldas_tmp : lcdas_tmp)
							{
								Collection<DetalleAntSistema> lcdas_detalleAntSistema;
								lcdas_detalleAntSistema = DaoCreator.getDetalleAntSistemaDAO(adm_manager)
										                                .findByDatosAntSis(
										    ldas_tmp.getIdDatosAntSistema()
										);

								if(CollectionUtils.isValidCollection(lcdas_detalleAntSistema))
								{
									for(DetalleAntSistema ldas_detalleTMP : lcdas_detalleAntSistema)
									{
										if(ldas_detalleTMP != null)
										{
											{
												String ls_nombreLibro;
												ls_nombreLibro = ldas_detalleTMP.getNombreLibro();

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.isValidString(ls_nombreLibro) ? ls_nombreLibro : ""
												);
											}

											{
												String ls_tomo;

												ls_tomo = ldas_detalleTMP.getTomo();

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(StringUtils.isValidString(ls_tomo) ? ls_tomo : "");
											}

											{
												String ls_folio;

												ls_folio = ldas_detalleTMP.getFolio();

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(StringUtils.isValidString(ls_folio) ? ls_folio : "");
											}

											{
												String ls_texto;

												ls_texto = ldas_detalleTMP.getPartida();

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(StringUtils.isValidString(ls_texto) ? ls_texto : "");
											}

											{
												String ls_numeroPartida;

												ls_numeroPartida = StringUtils.getString(
													    ldas_detalleTMP.getNumeroPartida()
													);

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.isValidString(ls_numeroPartida) ? ls_numeroPartida : ""
												);
											}

											ldb_builder.endRow();
										}
									}
								}
							}
						}

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("AÑO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("MUNICIPIO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("CIRCULO REGISTRAL");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("NOMBRE DE PREDIO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("TIPO PREDIO");

						ldb_builder.endRow();

						if(CollectionUtils.isValidCollection(lcdas_tmp))
						{
							for(DatosAntSistema ldas_tmp : lcdas_tmp)
							{
								Collection<DetalleAntSistema> lcdas_detalleAntSistema;
								lcdas_detalleAntSistema = DaoCreator.getDetalleAntSistemaDAO(adm_manager)
										                                .findByDatosAntSis(
										    ldas_tmp.getIdDatosAntSistema()
										);

								if(CollectionUtils.isValidCollection(lcdas_detalleAntSistema))
								{
									for(DetalleAntSistema ldas_detalleTMP : lcdas_detalleAntSistema)
									{
										if(ldas_detalleTMP != null)
										{
											{
												String ls_anio;

												ls_anio = ldas_detalleTMP.getAnio();

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(StringUtils.isValidString(ls_anio) ? ls_anio : "");
											}

											{
												String    ls_nombreMunicipio;
												Municipio lm_municipio;

												lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
														                     .findById(
														    ldas_tmp.getIdPais(), ldas_tmp.getIdDepartamento(),
														    ldas_tmp.getIdMunicipio()
														);

												if(lm_municipio != null)
												{
													ls_nombreMunicipio = lm_municipio.getNombre();

													ldb_builder.insertCell();
													ldb_builder.getParagraphFormat()
														           .setAlignment(ParagraphAlignment.CENTER);
													ldb_builder.setBold(true);
													ldb_builder.getFont().setName("Courier New");
													ldb_builder.getFont().setUnderline(0);
													ldb_builder.getFont().setSize(li_tamanoLetra);
													ldb_builder.getCellFormat()
														           .setPreferredWidth(
														    PreferredWidth.fromPercent(ll_porcentajetablas)
														);
													ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
													ldb_builder.getCellFormat()
														           .setVerticalAlignment(CellVerticalAlignment.CENTER);
													ldb_builder.write(
													    StringUtils.isValidString(ls_nombreMunicipio)
													    ? ls_nombreMunicipio : ""
													);
												}
											}

											{
												String ls_idCirculo;

												ls_idCirculo = ldas_tmp.getIdCirculo();

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.isValidString(ls_idCirculo) ? ls_idCirculo : ""
												);
											}

											{
												String ls_nombrePredio;

												ls_nombrePredio = ldas_tmp.getNombrePredio();

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.isValidString(ls_nombrePredio) ? ls_nombrePredio : ""
												);
											}

											{
												String     ls_nombreTipoPredio;
												PredioTipo lpt_predioTipo;

												lpt_predioTipo = DaoCreator.getPredioTipoDao(adm_manager)
														                       .findById(ldas_tmp.getIdTipoPredio());

												if(lpt_predioTipo != null)
												{
													ls_nombreTipoPredio = lpt_predioTipo.getDescripcion();

													ldb_builder.insertCell();
													ldb_builder.getParagraphFormat()
														           .setAlignment(ParagraphAlignment.CENTER);
													ldb_builder.setBold(true);
													ldb_builder.getFont().setName("Courier New");
													ldb_builder.getFont().setUnderline(0);
													ldb_builder.getFont().setSize(li_tamanoLetra);
													ldb_builder.getCellFormat()
														           .setPreferredWidth(
														    PreferredWidth.fromPercent(ll_porcentajetablas)
														);
													ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
													ldb_builder.getCellFormat()
														           .setVerticalAlignment(CellVerticalAlignment.CENTER);
													ldb_builder.write(
													    StringUtils.isValidString(ls_nombreTipoPredio)
													    ? ls_nombreTipoPredio : ""
													);
												}
											}

											ldb_builder.endRow();
										}
									}
								}
							}
						}
					}

					lt_table.setAlignment(TableAlignment.CENTER);
					ldb_builder.endTable();
				}

				if(!lb_procesoCertificados && !lb_esImpuestoRegistro)
				{
					Table lt_table;

					ldb_builder.writeln(ControlChar.LINE_BREAK);

					lt_table = ldb_builder.startTable();

					{
						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("DOCUMENTO");

						for(int i = 0; i < 4; i++)
						{
							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
						}

						ldb_builder.endRow();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("CLASE");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("NÚMERO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("FECHA");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("OFICINA DE ORIGEN");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("RECEPCIÓN");

						ldb_builder.endRow();

						lhmpCriterios = new LinkedHashMap<Integer, Object>();
						lhmpCriterios.put(Integer.valueOf(1), ls_solicitud);
						llmso_datos = lud_utilDao.getCustonQuery(ConsultasUtilidades.CS_DATOS_DOCUMENTO, lhmpCriterios);

						if(CollectionUtils.isValidCollection(llmso_datos))
						{
							Iterator<Map<String, Object>> li_datosDocumento;

							li_datosDocumento = llmso_datos.iterator();

							if(li_datosDocumento.hasNext())
							{
								Map<String, Object> llhm_actual;

								llhm_actual = li_datosDocumento.next();

								if(llhm_actual != null)
								{
									{
										String ls_clase;

										ls_clase = llhm_actual.get("CLASE").toString();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_clase) ? ls_clase : "");
									}

									{
										String ls_numero;

										ls_numero = llhm_actual.get("NUMERO").toString();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_numero) ? ls_numero : "");
									}

									{
										String ls_fecha;

										ls_fecha = llhm_actual.get("FECHA").toString();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_fecha) ? ls_fecha : "");
									}

									{
										String ls_oficinaOrigen;

										ls_oficinaOrigen = llhm_actual.get("OFICINA_ORIGEN").toString();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_oficinaOrigen) ? ls_oficinaOrigen : ""
										);
									}

									{
										String ls_ciudad;

										ls_ciudad = llhm_actual.get("CIUDAD").toString();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_ciudad) ? ls_ciudad : "");
									}

									ldb_builder.endRow();
								}
							}
						}
					}

					lt_table.setAlignment(TableAlignment.CENTER);
					ldb_builder.endTable();
				}

				if(lb_grabacionOProrrogaOSuspensionODesistimiento || lb_esCreacionMatriculaOficio)
				{
					Collection<AccCompletitudDocumental> lcacd_completitudDocumentales;

					lcacd_completitudDocumentales = (lb_esRecepcionRecursos || lb_esSegundaInstancia
							|| lb_esCreacionMatriculaOficio)
						? DaoCreator.getAccCompletitudDocumentalDAO(adm_manager).findByIdSolicitudNombres(ls_solicitud)
						: DaoCreator.getAccCompletitudDocumentalDAO(adm_manager)
							            .findByIdTurno(ar_parametros.getSolicitud().getIdTurnoAnt());

					if(CollectionUtils.isValidCollection(lcacd_completitudDocumentales))
					{
						Table lt_table;

						ldb_builder.writeln(ControlChar.LINE_BREAK);

						lt_table = ldb_builder.startTable();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write(
						    (lb_esRecepcionRecursos || lb_esSegundaInstancia) ? "DOCUMENTOS APORTADOS"
						                                                      : "SOLICITUD DOCUMENTACIÓN"
						);

						for(int i = 0; i < 2; i++)
						{
							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
						}

						ldb_builder.endRow();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("TIPO DOCUMENTAL");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("OBSERVACIONES");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("MEDIO RECEPCIÓN");

						ldb_builder.endRow();

						for(AccCompletitudDocumental lacd_tmp : lcacd_completitudDocumentales)
						{
							if(lacd_tmp != null)
							{
								String ls_entregado;
								String ls_solicitudVinculada;

								ls_entregado              = lacd_tmp.getEntregado();
								ls_solicitudVinculada     = lacd_tmp.getIdSolicitudVinculada();

								if(
								    (StringUtils.isValidString(ls_solicitudVinculada)
									    && StringUtils.isValidString(ls_solicitud)
									    && StringUtils.isValidString(ls_entregado)
									    && ls_entregado.equalsIgnoreCase(EstadoCommon.S)
									    && ls_solicitud.equalsIgnoreCase(ls_solicitudVinculada))
									    || lb_esRecepcionRecursos || lb_esSegundaInstancia
									    || lb_esCreacionMatriculaOficio
								)
								{
									{
										String ls_tipoDoc;

										ls_tipoDoc = lacd_tmp.getNombreTipoDocumental();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_tipoDoc) ? ls_tipoDoc : "");
									}

									{
										String ls_observacionesRecepcion;

										ls_observacionesRecepcion = lb_esRecepcionRecursos
											? lacd_tmp.getObservaciones() : lacd_tmp.getObservacionesRecepcion();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_observacionesRecepcion)
										    ? ls_observacionesRecepcion : ""
										);
									}

									{
										String ls_medioRecepcion;
										ls_medioRecepcion = lacd_tmp.getMedioRecepcion();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_medioRecepcion) ? ls_medioRecepcion : ""
										);
									}

									ldb_builder.endRow();
								}
							}
						}

						lt_table.setAlignment(TableAlignment.CENTER);
						ldb_builder.endTable();
					}
				}

				if(
				    lb_esProrrogaDocumentacion || lb_esSuspensionEntregaDocumentacion || lb_esProcesoDesistimiento
					    || lb_esProrrogaMayorValor || lb_esSuspensionArticulo || lb_esRestitucion
					    || lb_esImpuestoRegistro || lb_esRecepcionRecursos || lb_esSegundaInstancia
				)
				{
					Table lt_table;

					ldb_builder.writeln(ControlChar.LINE_BREAK);

					lt_table = ldb_builder.startTable();

					{
						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("DATOS SOLICITUD");

						for(int i = 0; i < 4; i++)
						{
							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
						}

						ldb_builder.endRow();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("NIR");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("TURNO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("FASE ACTUAL DEL TURNO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("PROCESO DEL TURNO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("DATOS DEL DOCUMENTO");

						ldb_builder.endRow();

						Collection<Turno> lct_turnos;
						lct_turnos = DaoCreator.getTurnoDAO(adm_manager)
								                   .consultaDetalleTurnosPDF(
								    ar_parametros.getSolicitud().getIdTurnoAnt(),
								    lb_esRestitucion || lb_esRecepcionRecursos || lb_esSegundaInstancia
								    || lb_esProcesoDesistimiento
								);

						if(CollectionUtils.isValidCollection(lct_turnos))
						{
							for(Turno lt_tmp : lct_turnos)
							{
								if(lct_turnos != null)
								{
									{
										String ls_nir;

										ls_nir = lt_tmp.getNir();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_nir) ? ls_nir : "");
									}

									{
										String ls_idTurno;

										ls_idTurno = lt_tmp.getIdTurno();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_idTurno) ? ls_idTurno : "");
									}

									{
										String ls_idFase;

										ls_idFase = lt_tmp.getIdFase();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(StringUtils.isValidString(ls_idFase) ? ls_idFase : "");
									}

									{
										String ls_nombreProceso;

										ls_nombreProceso = lt_tmp.getNombreProceso();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_nombreProceso) ? ls_nombreProceso : ""
										);
									}

									{
										String ls_infoDocumento;

										ls_infoDocumento = lt_tmp.getInfoDocumento();

										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(
										    StringUtils.isValidString(ls_infoDocumento) ? ls_infoDocumento : ""
										);
									}

									ldb_builder.endRow();
								}
							}
						}
					}

					lt_table.setAlignment(TableAlignment.CENTER);
					ldb_builder.endTable();
				}

				if(
				    !lb_grabacionOProrrogaOSuspensionODesistimiento && !lb_procesoCertificados
					    && !lb_esCreacionMatriculaOficio
				)
				{
					{
						Table lt_table;

						ldb_builder.writeln(ControlChar.LINE_BREAK);

						lt_table = ldb_builder.startTable();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("NATURALEZA JURÍDICA DEL ACTO");

						for(int i = 0; i < 2; i++)
						{
							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
						}

						ldb_builder.endRow();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("CÓDIGO REGISTRAL");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("ESPECIFICACIÓN");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra - 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("CUANTÍA DEL ACTO");

						ldb_builder.endRow();

						lhmpCriterios = new LinkedHashMap<Integer, Object>();
						lhmpCriterios.put(Integer.valueOf(1), ls_solicitud);

						llmso_datos = lud_utilDao.getCustonQuery(ConsultasUtilidades.CS_NATURALEZA_ACTO, lhmpCriterios);

						if(CollectionUtils.isValidCollection(llmso_datos))
						{
							for(Map<String, Object> map : llmso_datos)
							{
								ldb_builder.insertCell();
								ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
								ldb_builder.setBold(true);
								ldb_builder.getFont().setName("Courier New");
								ldb_builder.getFont().setUnderline(0);
								ldb_builder.getFont().setSize(li_tamanoLetra);
								ldb_builder.getCellFormat()
									           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
								ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
								ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
								ldb_builder.write(map.get("ID_TIPO_ACTO").toString());

								ldb_builder.insertCell();
								ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
								ldb_builder.setBold(true);
								ldb_builder.getFont().setName("Courier New");
								ldb_builder.getFont().setUnderline(0);
								ldb_builder.getFont().setSize(li_tamanoLetra);
								ldb_builder.getCellFormat()
									           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
								ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
								ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
								ldb_builder.write(map.get("NOMBRE").toString());

								Object lo_object;
								lo_object = map.get("CUANTIA");

								ldb_builder.insertCell();
								ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
								ldb_builder.setBold(true);
								ldb_builder.getFont().setName("Courier New");
								ldb_builder.getFont().setUnderline(0);
								ldb_builder.getFont().setSize(li_tamanoLetra);
								ldb_builder.getCellFormat()
									           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
								ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
								ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
								ldb_builder.write(
								    (lo_object != null)
								    ? ("$" + conversionCientifica(NumericUtils.getDoubleWrapper(lo_object.toString())))
								    : "SIN INFORMACIÓN"
								);
								ldb_builder.endRow();
							}
						}

						lt_table.setAlignment(TableAlignment.CENTER);
						ldb_builder.endTable();
					}

					{
						Table lt_table;

						ldb_builder.writeln(ControlChar.LINE_BREAK);

						lt_table = ldb_builder.startTable();

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("PERSONAS QUE INTERVIENEN EN EL ACTO");

						ldb_builder.insertCell();
						ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
						ldb_builder.setBold(true);
						ldb_builder.getFont().setName("Courier New");
						ldb_builder.getFont().setUnderline(0);
						ldb_builder.getFont().setSize(li_tamanoLetra + 1);
						ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
						ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
						ldb_builder.write("NÚMERO DE IDENTIFICACIÓN");

						ldb_builder.endRow();

						lhmpCriterios = new LinkedHashMap<Integer, Object>();
						lhmpCriterios.put(Integer.valueOf(1), ls_solicitud);

						llmso_datos = lud_utilDao.getCustonQuery(
							    ConsultasUtilidades.CS_INTERVINIENTE_ACTO, lhmpCriterios
							);

						if(CollectionUtils.isValidCollection(llmso_datos))
						{
							for(Map<String, Object> map : llmso_datos)
							{
								if(map != null)
								{
									Object lo_dato;

									lo_dato = map.get("NOMBRE");

									if(lo_dato != null)
									{
										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(lo_dato.toString());
									}

									lo_dato = map.get("NUMERO_DOCUMENTO");

									if(lo_dato != null)
									{
										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.setBold(true);
										ldb_builder.getFont().setName("Courier New");
										ldb_builder.getFont().setUnderline(0);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
										ldb_builder.write(lo_dato.toString());
									}

									ldb_builder.endRow();
								}
							}
						}

						lt_table.setAlignment(TableAlignment.CENTER);
						ldb_builder.endTable();
					}

					DatosAntSistema             ldas_datosAnt;
					Collection<DatosAntSistema> lcdas_datos;
					DatosAntSistemaDAO          ldas_datosDAO;
					ConsultasDAO                lcd_DAO;

					ldas_datosAnt     = new DatosAntSistema();
					ldas_datosDAO     = DaoCreator.getDatosAntSistemaDAO(adm_manager);
					lcd_DAO           = DaoCreator.getConsultasPgnDAO(adm_manager);

					ldas_datosAnt.setIdSolicitud(ls_solicitud);

					lcdas_datos = ldas_datosDAO.findByIdSolicitud(ldas_datosAnt);

					if(CollectionUtils.isValidCollection(lcdas_datos))
					{
						Collection<String> lcs_circulos;
						lcs_circulos = new LinkedList<String>();

						for(DatosAntSistema ldas_data : lcdas_datos)
						{
							if(ldas_data != null)
							{
								String ls_cir;
								ls_cir = StringUtils.getStringNotNull(ldas_data.getIdCirculo());

								if(lcs_circulos.isEmpty())
									lcs_circulos.add(ls_cir);
								else
								{
									boolean lb_repetido;
									lb_repetido = false;

									for(String ls_circuloAgregado : lcs_circulos)
									{
										if(StringUtils.isValidString(ls_circuloAgregado))
										{
											if(ls_cir.equalsIgnoreCase(ls_circuloAgregado))
											{
												lb_repetido = true;

												break;
											}
										}
									}

									if(!lb_repetido)
										lcs_circulos.add(ls_cir);
								}
							}
						}

						if(CollectionUtils.isValidCollection(lcs_circulos))
						{
							for(String ls_idCirculo : lcs_circulos)
							{
								if(StringUtils.isValidString(ls_idCirculo))
								{
									Table lt_table;

									ldb_builder.writeln(ControlChar.LINE_BREAK);

									lt_table = ldb_builder.startTable();

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra + 1);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write("DATOS ANTIGUO SISTEMA - " + ls_idCirculo);

									for(int i = 0; i < 5; i++)
									{
										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
									}

									ldb_builder.endRow();

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra - 1);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write("CIRCULO REGISTRAL");

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra - 1);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write("TIPO DE PREDIO");

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra - 1);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write("NOMBRE DEL PREDIO");

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra - 1);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write("PAÍS");

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra - 1);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write("DEPARTAMENTO");

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra - 1);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write("MUNICIPIO");

									ldb_builder.endRow();

									Collection<DatosAntSistema> ldas_datosPdf;

									ldas_datosPdf = lcd_DAO.findDatosAntSistemaRegistro(ls_solicitud, ls_idCirculo);

									if(CollectionUtils.isValidCollection(ldas_datosPdf))
									{
										for(DatosAntSistema ldas_iterator : ldas_datosPdf)
										{
											if(ldas_iterator != null)
											{
												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.getStringNotNull(ldas_iterator.getIdCirculo())
												);

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.getStringNotNull(ldas_iterator.getIdTipoPredio())
												);

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.getStringNotNull(ldas_iterator.getNombrePredio())
												);

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.getStringNotNull(ldas_iterator.getIdPais())
												);

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.getStringNotNull(ldas_iterator.getIdDepartamento())
												);

												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(
												    StringUtils.getStringNotNull(ldas_iterator.getIdMunicipio())
												);

												ldb_builder.endRow();
											}
										}
									}

									lt_table.setAlignment(TableAlignment.CENTER);
									ldb_builder.endTable();
								}
							}
						}
					}
				}
				else
				{
					if(!lb_procesoCertificados && !lb_esCreacionMatriculaOficio)
					{
						Table lt_table;

						ldb_builder.writeln(ControlChar.LINE_BREAK);

						lt_table = ldb_builder.startTable();

						{
							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Courier New");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("DATOS DEL INTERESADO");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Courier New");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("NÚMERO DE IDENTIFICACIÓN");

							ldb_builder.endRow();

							Solicitud ls_solicitudTMP;
							ls_solicitudTMP = new Solicitud();

							ls_solicitudTMP.setIdSolicitud(ls_solicitud);

							ls_solicitudTMP = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_solicitudTMP);

							if(ls_solicitudTMP != null)
							{
								Persona lp_persona;
								lp_persona = new Persona();
								lp_persona.setIdPersona(ls_solicitudTMP.getIdPersona());

								lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(lp_persona);

								if(lp_persona != null)
								{
									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write(StringUtils.getStringNotNull(lp_persona.getNombreCompleto()));

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write(StringUtils.getStringNotNull(lp_persona.getNumeroDocumento()));

									ldb_builder.endRow();
								}
							}
						}

						lt_table.setAlignment(TableAlignment.CENTER);
						ldb_builder.endTable();
					}
				}

				ldb_builder.writeln(ControlChar.LINE_BREAK);

				ld_doc.save(lbaos_docOutStream, SaveFormat.RTF);

				lba_pdf = new PDFGenerator().convertirRTFToPDF(lbaos_docOutStream.toByteArray(), adm_manager);

				if(lba_pdf == null)
					throw new B2BException(ErrorKeys.ARCHIVO_GENERANDO_ARCHIVO_POR_SOLICITUD);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("crearPdfRegistro", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("crearPdfRegistro", le_e);

			throw new B2BException(le_e.getMessage());
		}

		return lba_pdf;
	}

	/**
	 * Método encargado de crear PDF de registro para Constancia de reproducción.
	 *
	 * @param ar_parametros Objeto que contiene los datos para crear el pdf.
	 * @param as_userId ariable de tipo String que contiene el usuario que está ejecutando la acción.
	 * @param adm_manager DAOManager que es enviado por otro método de la clase.
	 * @return Arreglo de bytes que contiene el pdf generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized byte[] crearPdfRegistroConstanciaRep(
	    Registro ar_parametros, String as_userId, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_pdf;

		lba_pdf = null;

		try
		{
			Constantes    lc_plantilla;
			ConstantesDAO lc_DAO;

			UtilDAO                         lud_utilDao;
			String                          ls_solicitud;
			Collection<Map<String, Object>> ll_datos;

			lud_utilDao      = DaoCreator.getUtilDAO(adm_manager);
			lc_DAO           = DaoCreator.getConstantesDAO(adm_manager);
			lc_plantilla     = lc_DAO.findImagen(
				    new Constantes(ConstanteCommon.PLANTILLA_INGRESO_SOLICITUD_REGISTRO_REP_CONSTANCIA)
				);

			if((ar_parametros != null) && (lc_plantilla != null))
			{
				byte[] lba_plantilla;

				lba_plantilla     = lc_plantilla.getImagenes();
				ls_solicitud      = ar_parametros.getSolicitud().getIdSolicitud();

				if(lba_plantilla != null)
				{
					String                         ls_plantilla;
					LinkedHashMap<Integer, Object> lhmp_criterios;
					String                         ls_tag;

					ls_tag             = null;
					ls_plantilla       = new String(lba_plantilla);
					lhmp_criterios     = new LinkedHashMap<Integer, Object>();
					lhmp_criterios.put(Integer.valueOf(1), ls_solicitud);
					ll_datos = lud_utilDao.getCustonQuery(
						    ConsultasUtilidades.CS_CONSULTA_NIR_SOLICITUD, lhmp_criterios
						);

					if(StringUtils.isValidString(ls_plantilla))
					{
						if(CollectionUtils.isValidCollection(ll_datos))
						{
							Iterator<Map<String, Object>> li_consultaNir;

							li_consultaNir = ll_datos.iterator();

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

										ls_fechaCreacion     = StringUtils.getString(
											    (Date)llhm_actual.get("FECHA_CREACION"), FormatoFechaCommon.DIA_MES_ANIO
											);

										ls_tag = "<TAG_FECHA_CREACION>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_fechaCreacion))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaCreacion);
										}
									}
								}
							}
						}
					}

					{
						ls_tag = "<TAG_TABLA_MATRICULA_PREDIO>";

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

								ls_tagNew = "{\\*\\bkmkstart TAG_TABLA_MATRICULA_PREDIO}{\\*\\bkmkend TAG_TABLA_MATRICULA_PREDIO} \\line "
									+ ls_tag;

								{
									StringBuilder lsb_sb;

									lsb_sb = new StringBuilder();

									lsb_sb.append(ls_textoMitadSuperior);
									lsb_sb.append(IdentificadoresCommon.ESPACIO_VACIO);
									lsb_sb.append(ls_tagNew);
									lsb_sb.append(IdentificadoresCommon.ESPACIO_VACIO);
									lsb_sb.append(ls_textoMitadInferior);

									ls_plantilla = lsb_sb.toString();
								}
							}
						}
					}

					{
						ls_tag = "<TAG_TABLA_MATRICULAS_APERTURA>";

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

								ls_tagNew = "{\\*\\bkmkstart TAG_TABLA_MATRICULAS_APERTURA}{\\*\\bkmkend TAG_TABLA_MATRICULAS_APERTURA} \\line "
									+ ls_tag;

								{
									StringBuilder lsb_sb;

									lsb_sb = new StringBuilder();

									lsb_sb.append(ls_textoMitadSuperior);
									lsb_sb.append(IdentificadoresCommon.ESPACIO_VACIO);
									lsb_sb.append(ls_tagNew);
									lsb_sb.append(IdentificadoresCommon.ESPACIO_VACIO);
									lsb_sb.append(ls_textoMitadInferior);

									ls_plantilla = lsb_sb.toString();
								}
							}
						}
					}

					{
						lhmp_criterios = new LinkedHashMap<Integer, Object>();
						lhmp_criterios.put(Integer.valueOf(1), ls_solicitud);
						ll_datos = lud_utilDao.getCustonQuery(ConsultasUtilidades.CS_DATOS_DOCUMENTO, lhmp_criterios);

						if(CollectionUtils.isValidCollection(ll_datos))
						{
							Iterator<Map<String, Object>> li_datosDocumento;

							li_datosDocumento = ll_datos.iterator();

							if(li_datosDocumento.hasNext())
							{
								Map<String, Object> llhm_actual;

								llhm_actual = li_datosDocumento.next();

								if(llhm_actual != null)
								{
									{
										String ls_clase;

										ls_clase     = llhm_actual.get("CLASE").toString();

										ls_tag = "<TAG_CLASE>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_clase))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_clase);
									}

									{
										String ls_numero;

										ls_numero     = llhm_actual.get("NUMERO").toString();

										ls_tag = "<TAG_NUMERO>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_numero))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_numero);
									}

									{
										String ls_fecha;

										ls_fecha     = llhm_actual.get("FECHA").toString();

										ls_tag = "<TAG_FECHA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_fecha))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_fecha);
									}

									{
										String ls_oficinaOrigen;

										ls_oficinaOrigen     = llhm_actual.get("OFICINA_ORIGEN").toString();

										ls_tag = "<TAG_OFICINA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_oficinaOrigen))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_oficinaOrigen);
									}

									{
										String ls_ciudad;

										ls_ciudad     = llhm_actual.get("CIUDAD").toString();

										ls_tag = "<TAG_CIUDAD>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_ciudad))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_ciudad);
									}
								}
							}
						}
					}

					{
						ls_tag = "<TAG_TABLA_NATURALEZA_ACTO>";

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

								ls_tagNew = "{\\*\\bkmkstart TAG_TABLA_NATURALEZA_ACTO}{\\*\\bkmkend TAG_TABLA_NATURALEZA_ACTO} \\line "
									+ ls_tag;

								{
									StringBuilder lsb_sb;

									lsb_sb = new StringBuilder();

									lsb_sb.append(ls_textoMitadSuperior);
									lsb_sb.append(IdentificadoresCommon.ESPACIO_VACIO);
									lsb_sb.append(ls_tagNew);
									lsb_sb.append(IdentificadoresCommon.ESPACIO_VACIO);
									lsb_sb.append(ls_textoMitadInferior);

									ls_plantilla = lsb_sb.toString();
								}
							}
						}

						ls_plantilla = limpiarTags(ls_plantilla);
					}

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
						li_tamanoLetra          = 10;

						ldb_builder.moveToBookmark("TAG_TABLA_MATRICULA_PREDIO");

						lhmp_criterios = new LinkedHashMap<Integer, Object>();
						lhmp_criterios.put(Integer.valueOf(1), ls_solicitud);
						ll_datos = lud_utilDao.getCustonQuery(ConsultasUtilidades.CS_MATRICULA_PREDIO, lhmp_criterios);

						{
							Table lt_table;

							ldb_builder.writeln(ControlChar.LINE_BREAK);

							lt_table = ldb_builder.startTable();

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 2);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("MATRÍCULA INMOBILIARIA");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 2);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("DATOS DEL PREDIO");

							for(int i = 0; i < 2; i++)
							{
								ldb_builder.insertCell();
								ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
							}

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 2);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("TIPO PREDIO");

							ldb_builder.endRow();

							if(CollectionUtils.isValidCollection(ll_datos))
							{
								Iterator<Map<String, Object>> li_consultaDatosPredio;

								li_consultaDatosPredio = ll_datos.iterator();

								while(li_consultaDatosPredio.hasNext())
								{
									Map<String, Object> llhm_actual;

									llhm_actual = li_consultaDatosPredio.next();

									if(llhm_actual != null)
									{
										{
											String ls_matriculaPredio;

											ls_matriculaPredio = llhm_actual.get("MATRICULA_INMOBILIARIA").toString();

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_matriculaPredio) ? ls_matriculaPredio : ""
											);
										}

										{
											String ls_datosPredio;

											ls_datosPredio = (llhm_actual.get("DIRECCION") != null)
												? llhm_actual.get("DIRECCION").toString() : null;

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_datosPredio) ? ls_datosPredio : ""
											);

											for(int i = 0; i < 2; i++)
											{
												ldb_builder.insertCell();
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
											}
										}

										{
											String ls_tipoPredio;

											ls_tipoPredio = StringUtils.getString(llhm_actual.get("TIPO_PREDIO"));

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_tipoPredio) ? ls_tipoPredio : ""
											);
										}

										ldb_builder.endRow();
									}
								}
							}

							lt_table.setAlignment(TableAlignment.CENTER);
							ldb_builder.endTable();
						}

						ldb_builder.moveToBookmark("TAG_TABLA_MATRICULAS_APERTURA");

						{
							Table lt_table;

							ldb_builder.writeln(ControlChar.LINE_BREAK);

							lt_table = ldb_builder.startTable();

							int    li_i;
							String ls_m2;
							li_i      = 1;
							ls_m2     = "m²";

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 2);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("APERTURA DE MATRÍCULAS");

							for(int i = 0; i < 6; i++)
							{
								ldb_builder.insertCell();
								ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
							}

							ldb_builder.endRow();

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("UNIDAD");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("NOMBRE DEL PREDIO");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("DIRECCIÓN DEL PREDIO");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("AREA TERRENO");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("AREA PRIVADA");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("AREA CONSTRUIDA");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("COEFICIENTE");

							ldb_builder.endRow();

							lhmp_criterios = new LinkedHashMap<Integer, Object>();
							lhmp_criterios.put(Integer.valueOf(1), ls_solicitud);
							ll_datos = lud_utilDao.getCustonQuery(
								    ConsultasUtilidades.CS_MATRICULAS_APERTURA, lhmp_criterios
								);

							if(CollectionUtils.isValidCollection(ll_datos))
							{
								Iterator<Map<String, Object>> li_datosDocumento;

								li_datosDocumento = ll_datos.iterator();

								while(li_datosDocumento.hasNext())
								{
									Map<String, Object> llhm_actual;

									llhm_actual = li_datosDocumento.next();

									if(llhm_actual != null)
									{
										{
											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(String.valueOf(li_i++));
										}

										{
											String ls_predio;

											ls_predio = llhm_actual.get("NOMBRE_PREDIO").toString();

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(StringUtils.isValidString(ls_predio) ? ls_predio : "");
										}

										{
											String ls_direccion;

											ls_direccion = (llhm_actual.get("DIRECCION") != null)
												? llhm_actual.get("DIRECCION").toString() : null;

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_direccion) ? ls_direccion : ""
											);
										}

										{
											String ls_areaTerreno;

											ls_areaTerreno = (llhm_actual.get("AREA_TERRENO") != null)
												? llhm_actual.get("AREA_TERRENO").toString() : null;

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_areaTerreno)
											    ? (ls_areaTerreno + " " + ls_m2) : ""
											);
										}

										{
											String ls_areaPrivada;

											ls_areaPrivada = (llhm_actual.get("AREA_PRIVADA") != null)
												? llhm_actual.get("AREA_PRIVADA").toString() : null;

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_areaPrivada)
											    ? (ls_areaPrivada + " " + ls_m2) : ""
											);
										}

										{
											String ls_areaConstruida;

											ls_areaConstruida = null;

											if(llhm_actual.get("AREA_CONSTRUIDA") != null)
												ls_areaConstruida = llhm_actual.get("AREA_CONSTRUIDA").toString();

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_areaConstruida)
											    ? (ls_areaConstruida + " " + ls_m2) : ""
											);
										}

										{
											String ls_coeficiente;

											ls_coeficiente = null;

											if(llhm_actual.get("COEFICIENTE") != null)
												ls_coeficiente = llhm_actual.get("COEFICIENTE").toString();

											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
											ldb_builder.setBold(true);
											ldb_builder.getFont().setName("Arial");
											ldb_builder.getFont().setUnderline(0);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setVerticalAlignment(CellVerticalAlignment.CENTER);
											ldb_builder.write(
											    StringUtils.isValidString(ls_coeficiente) ? (ls_coeficiente + " %") : ""
											);
										}

										ldb_builder.endRow();
									}
								}
							}

							lt_table.setAlignment(TableAlignment.CENTER);
							ldb_builder.endTable();
						}

						ldb_builder.moveToBookmark("TAG_TABLA_NATURALEZA_ACTO");

						{
							Table lt_table;

							ldb_builder.writeln(ControlChar.LINE_BREAK);

							lt_table = ldb_builder.startTable();

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 2);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("NATURALEZA JURÍDICA DEL ACTO");

							for(int i = 0; i < 2; i++)
							{
								ldb_builder.insertCell();
								ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
							}

							ldb_builder.endRow();

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("CÓDIGO REGISTRAL");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("ESPECIFICACIÓN");

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("Arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
							ldb_builder.write("CUANTÍA DEL ACTO");

							ldb_builder.endRow();

							lhmp_criterios = new LinkedHashMap<Integer, Object>();
							lhmp_criterios.put(Integer.valueOf(1), ls_solicitud);

							ll_datos = lud_utilDao.getCustonQuery(
								    ConsultasUtilidades.CS_NATURALEZA_ACTO, lhmp_criterios
								);

							if(CollectionUtils.isValidCollection(ll_datos))
							{
								for(Map<String, Object> map : ll_datos)
								{
									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write(map.get("ID_TIPO_ACTO").toString());

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write(map.get("NOMBRE").toString());

									Object lo_object;
									lo_object = map.get("CUANTIA");

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.setBold(true);
									ldb_builder.getFont().setName("Courier New");
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
									ldb_builder.write(
									    (lo_object != null)
									    ? ("$"
									    + conversionCientifica(NumericUtils.getDoubleWrapper(lo_object.toString())))
									    : "SIN INFORMACIÓN"
									);

									ldb_builder.endRow();
								}
							}

							lt_table.setAlignment(TableAlignment.CENTER);
							ldb_builder.endTable();
						}

						ldb_builder.writeln(ControlChar.LINE_BREAK);

						ld_doc.save(lbaos_docOutStream, SaveFormat.RTF);

						lba_pdf = new PDFGenerator().convertirRTFToPDF(lbaos_docOutStream.toByteArray(), adm_manager);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("crearPdfRegistroConstanciaRep", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("crearPdfRegistroConstanciaRep", le_e);

			throw new B2BException(le_e.getMessage());
		}

		return lba_pdf;
	}

	/**
	 * Método encargado de crear PDF de registro para Constancia de reproducción.
	 *
	 * @param ar_parametros Objeto que contiene los datos para crear el pdf.
	 * @param as_userId Variable de tipo String que contiene el usuario que está ejecutando la acción.
	 * @return Arreglo de bytes que contiene el pdf generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized byte[] crearPdfRegistroConstanciaRep(Registro ar_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_pdf;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_pdf         = null;

		try
		{
			lba_pdf = crearPdfRegistroConstanciaRep(ar_parametros, as_userId, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("crearPdfRegistroConstanciaRep", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_pdf;
	}

	/**
	 * Retorna el valor del objeto de byte[].
	 *
	 * @param ar_registro correspondiente al valor del tipo de objeto Registro
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @param ab_individual valida si es un traslado individual o masivo.
	 * @return devuelve el valor de byte[]
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public synchronized byte[] crearPdfTraslado(
	    Registro ar_registro, String as_usuario, DAOManager adm_manager, boolean ab_individual
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			if(ar_registro != null)
			{
				byte[]        lba_plantilla;
				Constantes    lc_constante;
				ConstantesDAO lc_DAO;
				String        ls_plantilla;

				lc_constante     = new Constantes();
				lc_DAO           = DaoCreator.getConstantesDAO(adm_manager);

				if(ab_individual)
					lc_constante.setIdConstante(ConstanteCommon.PLANTILLA_TRASLADO_INDIVIDUAL);
				else
					lc_constante.setIdConstante(ConstanteCommon.PLANTILLA_TRASLADO_MASIVO);

				lc_constante = lc_DAO.findImagen(lc_constante);

				if(lc_constante != null)
				{
					lba_plantilla = lc_constante.getImagenes();

					if(lba_plantilla != null)
					{
						Solicitud    ls_solicitud;
						SolicitudDAO ls_DAO;

						ls_DAO           = DaoCreator.getSolicitudDAO(adm_manager);
						ls_solicitud     = ls_DAO.findById(ar_registro.getSolicitud());
						ls_plantilla     = new String(lba_plantilla);

						if(ls_solicitud != null)
						{
							Map<String, String> lmss_datos;
							String              ls_idSolicitud;

							lmss_datos         = null;
							ls_idSolicitud     = ls_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(as_usuario) && ls_plantilla.contains("<TAG_USUARIO>"))
								ls_plantilla = ls_plantilla.replace("<TAG_USUARIO>", as_usuario);

							{
								Date       ld_fechaActual;
								DateFormat ldf_dateFormat;
								String     ls_fecha;

								ld_fechaActual     = new Date();
								ldf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
								ls_fecha           = ldf_dateFormat.format(ld_fechaActual);

								if(ls_plantilla.contains("<TAG_FECHA>"))
									ls_plantilla = ls_plantilla.replace("<TAG_FECHA>", ls_fecha);
							}

							{
								String ls_nir;

								ls_nir = ls_solicitud.getNir();

								if(StringUtils.isValidString(ls_nir) && ls_plantilla.contains("<TAG_NIR>"))
									ls_plantilla = ls_plantilla.replace("<TAG_NIR>", ls_nir);
							}

							String ls_tag;

							ls_tag = "<TAG_TURNO>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_turno;

								ls_turno = obtenerTurnoDeSolicitud(ls_idSolicitud, adm_manager);

								if(StringUtils.isValidString(ls_turno))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_turno);
							}

							{
								String ls_idPersona;

								ls_idPersona = ls_solicitud.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona))
								{
									Persona    lp_persona;
									PersonaDAO lp_DAO;

									lp_persona     = new Persona();
									lp_DAO         = DaoCreator.getPersonaDAO(adm_manager);

									lp_persona.setIdPersona(ls_idPersona);

									lp_persona = lp_DAO.findById(lp_persona);

									if(lp_persona != null)
									{
										if(ls_plantilla.contains("<TAG_ID_PERSONA>"))
										{
											StringBuilder lsb_nombreCompleto;

											lsb_nombreCompleto = new StringBuilder();

											String ls_primerApellido;
											String ls_primerNombre;
											String ls_segundoApellido;
											String ls_segundoNombre;

											ls_primerApellido      = lp_persona.getPrimerApellido();
											ls_primerNombre        = lp_persona.getPrimerNombre();
											ls_segundoApellido     = lp_persona.getSegundoApellido();
											ls_segundoNombre       = lp_persona.getSegundoNombre();

											if(StringUtils.isValidString(ls_primerNombre))
												lsb_nombreCompleto.append(ls_primerNombre);

											if(StringUtils.isValidString(ls_segundoNombre))
												lsb_nombreCompleto.append(" " + ls_segundoNombre);

											if(StringUtils.isValidString(ls_primerApellido))
												lsb_nombreCompleto.append(" " + ls_primerApellido);

											if(StringUtils.isValidString(ls_segundoApellido))
												lsb_nombreCompleto.append(" " + ls_segundoApellido);

											ls_plantilla = ls_plantilla.replace(
												    "<TAG_ID_PERSONA>", lsb_nombreCompleto.toString()
												);
										}

										if(ls_plantilla.contains("<TAG_TIPO_DOCUMENTO>"))
										{
											String ls_idTipoDoc;

											ls_idTipoDoc = lp_persona.getIdDocumentoTipo();

											if(StringUtils.isValidString(ls_idTipoDoc))
											{
												InteresadoDocumentoTipo    lidt_tipoDocumento;
												InteresadoDocumentoTipoDAO lidt_DAO;

												lidt_tipoDocumento     = new InteresadoDocumentoTipo();
												lidt_DAO               = DaoCreator.getInteresadoDocumentoTipoDAO(
													    adm_manager
													);

												lidt_tipoDocumento.setIdDocumentoTipo(ls_idTipoDoc);

												lidt_tipoDocumento = lidt_DAO.findById(lidt_tipoDocumento);

												if(lidt_tipoDocumento != null)
												{
													String ls_descripcion;

													ls_descripcion = lidt_tipoDocumento.getDescripcion();

													if(StringUtils.isValidString(ls_descripcion))
														ls_plantilla = ls_plantilla.replace(
															    "<TAG_TIPO_DOCUMENTO>", ls_descripcion
															);
												}
											}
										}

										{
											String ls_numeroDocumento;

											ls_numeroDocumento = lp_persona.getNumeroDocumento();

											if(StringUtils.isValidString(ls_numeroDocumento))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_NUM_DOC>", ls_numeroDocumento
													);
										}

										if(ls_plantilla.contains("<TAG_TELEFONO>"))
										{
											String ls_idTelefono;

											ls_idTelefono = ls_solicitud.getIdTelefono();

											if(StringUtils.isValidString(ls_idTelefono))
											{
												PersonaTelefono    lpt_telefono;
												PersonaTelefonoDAO lpt_DAO;

												lpt_telefono     = new PersonaTelefono();
												lpt_DAO          = DaoCreator.getPersonaTelefonoDAO(adm_manager);

												lpt_telefono.setIdPersona(ls_idPersona);
												lpt_telefono.setIdTelefono(ls_idTelefono);

												lpt_telefono = lpt_DAO.findById(lpt_telefono);

												if(lpt_telefono != null)
												{
													String ls_telefono;

													ls_telefono = lpt_telefono.getTelefono();

													if(StringUtils.isValidString(ls_telefono))
														ls_plantilla = ls_plantilla.replace(
															    "<TAG_TELEFONO>", ls_telefono
															);
												}
											}
										}

										if(ls_plantilla.contains("<TAG_DIRECCION>"))
										{
											String ls_idDireccion;

											ls_idDireccion = ls_solicitud.getIdDireccion();

											if(StringUtils.isValidString(ls_idDireccion))
											{
												PersonaDireccion    lpd_direccion;
												PersonaDireccionDAO lpd_DAO;

												lpd_direccion     = new PersonaDireccion();
												lpd_DAO           = DaoCreator.getPersonaDireccionDAO(adm_manager);

												lpd_direccion.setIdPersona(ls_idPersona);
												lpd_direccion.setIdDireccion(ls_idDireccion);

												lpd_direccion = lpd_DAO.findById(lpd_direccion);

												if(lpd_direccion != null)
												{
													StringBuilder lsb_direccionCompleta;
													String        ls_direccionCompleta;

													lsb_direccionCompleta     = new StringBuilder();
													ls_direccionCompleta      = null;

													{
														String ls_tipoEje;
														ls_tipoEje = lpd_direccion.getIdTipoEjePrincipal();

														if(StringUtils.isValidString(ls_tipoEje))
														{
															TipoEje lte_tmp;
															lte_tmp = new TipoEje();
															lte_tmp.setIdTipoEje(ls_tipoEje);

															lte_tmp = DaoCreator.getTipoEjeDAO(adm_manager)
																	                .findById(lte_tmp);

															if(lte_tmp != null)
																lsb_direccionCompleta.append(
																    StringUtils.getStringNotNull(lte_tmp.getNombre())
																    + " "
																);
														}
													}

													lsb_direccionCompleta.append(
													    StringUtils.getStringNotNull(
													        lpd_direccion.getDatoEjePrincipal()
													    ) + " "
													);

													{
														String ls_tipoEje1;
														ls_tipoEje1 = lpd_direccion.getIdTipoEjeSecundario();

														if(StringUtils.isValidString(ls_tipoEje1))
														{
															TipoEje lte_tmp;
															lte_tmp = new TipoEje();
															lte_tmp.setIdTipoEje(ls_tipoEje1);

															lte_tmp = DaoCreator.getTipoEjeDAO(adm_manager)
																	                .findById(lte_tmp);

															if(lte_tmp != null)
																lsb_direccionCompleta.append(
																    StringUtils.getStringNotNull(lte_tmp.getNombre())
																    + " "
																);
														}
													}

													lsb_direccionCompleta.append(
													    StringUtils.getStringNotNull(
													        lpd_direccion.getDatoEjeSecundario()
													    ) + " "
													);
													lsb_direccionCompleta.append(
													    StringUtils.getStringNotNull(
													        lpd_direccion.getComplementoDireccion()
													    ) + " "
													);

													{
														String ls_tmp;

														ls_tmp = lsb_direccionCompleta.toString();

														if(StringUtils.isValidString(ls_tmp))
														{
															ls_direccionCompleta     = ls_tmp.trim();

															ls_plantilla = ls_plantilla.replace(
																    "<TAG_DIRECCION>", ls_direccionCompleta
																);
														}
													}
												}
											}
										}

										if(ls_plantilla.contains("<TAG_CORREO_ELECTRONICO>"))
										{
											String ls_idCorreo;

											ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

											if(StringUtils.isValidString(ls_idCorreo))
											{
												PersonaCorreoElectronico    lpce_correo;
												PersonaCorreoElectronicoDAO lpce_DAO;

												lpce_correo     = new PersonaCorreoElectronico();
												lpce_DAO        = DaoCreator.getPersonaCorreoElectronicoDAO(
													    adm_manager
													);

												lpce_correo.setIdPersona(ls_idPersona);
												lpce_correo.setIdCorreoElectronico(ls_idCorreo);

												lpce_correo = lpce_DAO.findById(lpce_correo);

												if(lpce_correo != null)
												{
													String ls_correoElectronico;

													ls_correoElectronico = lpce_correo.getCorreoElectronico();

													if(StringUtils.isValidString(ls_correoElectronico))
														ls_plantilla = ls_plantilla.replace(
															    "<TAG_CORREO_ELECTRONICO>", ls_correoElectronico
															);
												}
											}
										}

										if(ls_plantilla.contains("<TAG_PROCEDENCIA>"))
										{
											String ls_idRecepcion;

											ls_idRecepcion = ls_solicitud.getIdTipoRecepcion();

											if(StringUtils.isValidString(ls_idRecepcion))
											{
												TipoRecepcion    ltr_recepcion;
												TipoRecepcionDAO ltr_DAO;

												ltr_recepcion     = new TipoRecepcion();
												ltr_DAO           = DaoCreator.getTipoRecepcionDAO(adm_manager);

												ltr_recepcion.setIdTipoRecepcion(ls_idRecepcion);

												ltr_recepcion = ltr_DAO.findById(ltr_recepcion);

												if(ltr_recepcion != null)
												{
													String ls_nombreProcedencia;

													ls_nombreProcedencia = ltr_recepcion.getNombre();

													if(StringUtils.isValidString(ls_nombreProcedencia))
														ls_plantilla = ls_plantilla.replace(
															    "<TAG_PROCEDENCIA>", ls_nombreProcedencia
															);
												}
											}
										}
									}
								}
							}

							lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

							{
								ByteArrayInputStream          lbais_docInStream;
								ByteArrayOutputStream         lbaos_docOutStream;
								Collection<TrasladoMatricula> lctm_matriculas;
								TrasladoMatriculaDAO          ltm_DAO;
								com.aspose.words.Document     ld_doc;
								DocumentBuilder               ldb_builder;
								Table                         lt_table;

								lbais_docInStream      = new ByteArrayInputStream(ls_plantilla.getBytes());
								lbaos_docOutStream     = new ByteArrayOutputStream();
								ld_doc                 = new com.aspose.words.Document(lbais_docInStream);
								ltm_DAO                = DaoCreator.getTrasladoMatriculaDAO(adm_manager);
								ldb_builder            = new DocumentBuilder(ld_doc);

								ldb_builder.moveToDocumentEnd();

								lctm_matriculas = ltm_DAO.findByIdSolicitud(ls_idSolicitud);

								ldb_builder.writeln(ControlChar.LINE_BREAK);

								if(CollectionUtils.isValidCollection(lctm_matriculas))
								{
									lt_table = ldb_builder.startTable();

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);

									ldb_builder.getFont().setColor(Color.black);
									ldb_builder.getFont().setSize(15);
									ldb_builder.getFont().setName("Arial");
									ldb_builder.getFont().setBold(true);

									ldb_builder.write("MATRICULAS A TRASLADAR");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

									ldb_builder.endRow();

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(40));

									ldb_builder.getFont().setSize(12);
									ldb_builder.write("CÍRCULO REGISTRAL ORIGEN");

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(40));

									ldb_builder.getFont().setSize(12);
									ldb_builder.write("CÍRCULO REGISTRAL DESTINO");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(60));
									ldb_builder.getFont().setSize(12);
									ldb_builder.write("MATRÍCULA");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(60));
									ldb_builder.getFont().setSize(12);
									ldb_builder.write("DIRECCIÓN DEL PREDIO");

									ldb_builder.endRow();

									for(TrasladoMatricula ltm_iterador : lctm_matriculas)
									{
										if(ltm_iterador != null)
										{
											Map<String, String> lmss_data;

											lmss_data = ltm_DAO.findDataPredio(
												    ltm_iterador.getIdCirculoOrigen(),
												    ltm_iterador.getIdMatriculaOrigen(), ltm_iterador.getIdSolicitud()
												);

											if(lmss_data != null)
											{
												ldb_builder.insertCell();
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(40));
												ldb_builder.getFont().setSize(10);
												ldb_builder.getFont().setBold(false);
												ldb_builder.write(lmss_data.get("CIRCULO_ORIGEN"));

												ldb_builder.insertCell();
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(60));
												ldb_builder.getFont().setSize(10);
												ldb_builder.getFont().setBold(false);
												ldb_builder.write(lmss_data.get("CIRCULO_DESTINO"));

												ldb_builder.insertCell();
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(60));
												ldb_builder.getFont().setSize(10);
												ldb_builder.getFont().setBold(false);
												ldb_builder.write(lmss_data.get("MATRICULA"));

												ldb_builder.insertCell();
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(60));
												ldb_builder.getFont().setSize(10);
												ldb_builder.getFont().setBold(false);
												ldb_builder.write(lmss_data.get("DIRECCION_COMPLETA"));

												ldb_builder.endRow();
											}
										}
									}

									lt_table.setAlignment(TableAlignment.CENTER);
									ldb_builder.endTable();
								}

								ldb_builder.writeln(ControlChar.LINE_BREAK);

								{
									AccCompletitudDocumentalDAO          lacd_DAO;
									Collection<AccCompletitudDocumental> lcacd_documentales;
									TipoDocumentalDAO                    ltd_DAO;

									lt_table     = ldb_builder.startTable();
									lacd_DAO     = DaoCreator.getAccCompletitudDocumentalDAO(adm_manager);
									ltd_DAO      = DaoCreator.getTipoDocumentalDAO(adm_manager);

									lcacd_documentales = lacd_DAO.findAllByIdSolicitud(ls_solicitud.getIdSolicitud());

									if(CollectionUtils.isValidCollection(lcacd_documentales))
									{
										ldb_builder.insertCell();
										ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
										ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(70));
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);

										ldb_builder.getFont().setColor(Color.black);
										ldb_builder.getFont().setSize(15);
										ldb_builder.getFont().setName("Arial");
										ldb_builder.getFont().setBold(true);

										ldb_builder.write("DOCUMENTOS ANEXOS");

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

										ldb_builder.endRow();

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
										ldb_builder.getFont().setSize(15);
										ldb_builder.write("TIPO DOCUMENTAL");

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(50));
										ldb_builder.getFont().setSize(15);
										ldb_builder.write("OBSERVACIONES");

										ldb_builder.endRow();

										for(AccCompletitudDocumental lacd_iterador : lcacd_documentales)
										{
											if(lacd_iterador != null)
											{
												TipoDocumental ltd_tipoDocumental;

												ltd_tipoDocumental = ltd_DAO.findById(
													    lacd_iterador.getIdTipoDocumental()
													);

												ldb_builder.insertCell();
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(20));
												ldb_builder.getFont().setSize(10);
												ldb_builder.getFont().setBold(false);
												ldb_builder.write(
												    (ltd_tipoDocumental != null) ? ltd_tipoDocumental.getNombre()
												                                 : lacd_iterador.getIdTipoDocumental()
												);

												ldb_builder.insertCell();
												ldb_builder.getCellFormat()
													           .setPreferredWidth(PreferredWidth.fromPercent(50));
												ldb_builder.getFont().setSize(10);
												ldb_builder.getFont().setBold(false);
												ldb_builder.write(
												    StringUtils.getStringNotNull(lacd_iterador.getObservaciones())
												);

												ldb_builder.endRow();
											}
										}
									}

									if(lt_table.getFirstRow() != null)
										lt_table.setAlignment(TableAlignment.CENTER);

									ldb_builder.endTable();
								}

								ld_doc.save(lbaos_docOutStream, SaveFormat.DOC);

								byte[] docBytes = lbaos_docOutStream.toByteArray();

								lba_return = new PDFGenerator().convertirRTFToPDF(docBytes, adm_manager);
							}
						}
					}
				}
			}
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("crearPdfTraslado", le_e);

			clh_LOGGER.error("crearPdfTraslado", lb2be_e);

			throw lb2be_e;
		}

		return lba_return;
	}

	/**
	 * Método encargado de eliminar Acto.
	 *
	 * @param asi_parametros Objeto que contiene la información del acto que se va a eliminar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void deleteActo(com.bachue.snr.prosnr01.model.registro.Acto asi_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asi_parametros != null)
			{
				String ls_idActo;

				ls_idActo = asi_parametros.getIdActoDb();

				if(StringUtils.isValidString(ls_idActo))
				{
					Acto                   la_acto;
					ActoDAO                la_DAO;
					SolicitudMatriculaActo lsm_solicitudMatricula;

					la_acto                    = new Acto();
					la_DAO                     = DaoCreator.getActoDAO(ldm_manager);
					lsm_solicitudMatricula     = new SolicitudMatriculaActo();

					la_acto.setIdActo(ls_idActo);
					lsm_solicitudMatricula.setIdActo(ls_idActo);

					la_acto = la_DAO.findById(la_acto);

					if(la_acto != null)
					{
						String ls_idSolicitud;
						String ls_idTipoActo;

						ls_idSolicitud     = la_acto.getIdSolicitud();
						ls_idTipoActo      = la_acto.getIdTipoActo();

						if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_idTipoActo))
							DaoCreator.getTipoActoDAO(ldm_manager)
								          .deleteActoCompletitud(ls_idSolicitud, ls_idActo, ls_idTipoActo);

						DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager).deleteByIdActo(lsm_solicitudMatricula);
						la_DAO.delete(la_acto);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_ACTO_INGRESADO);
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
	 * Método encargado de eliminar los actos asociados de un acto principal.
	 *
	 * @param acc_parametros Objeto que contiene la información del acto al cual se le van a eliminar los actos asociados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void deleteActosHijos(com.bachue.snr.prosnr01.model.registro.Acto acc_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			ActoDAO lad_DAO;
			lad_DAO = DaoCreator.getActoDAO(ldm_manager);

			if(acc_parametros != null)
				lad_DAO.deleteActosHijos(acc_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("deleteActosHijos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de eliminar la solicitudMatricula, matriculaSegregacion y solicitudMatriculaActo para una solicitud idcirculo y idmatricula.
	 *
	 * @param asm_sm Objeto que contiene la información de la solicitud circulo y matricula sobre la cual se está trabajando.
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void deleteBySolicitudCirculoMatricula(
	    SolicitudMatricula asm_sm, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asm_sm != null)
			{
				if(
				    StringUtils.isValidString(asm_sm.getIdSolicitud())
					    && StringUtils.isValidString(asm_sm.getIdCirculo()) && (asm_sm.getIdMatricula() > 0)
				)
				{
					SolicitudMatricula    lsm_solicitudMatricula;
					SolicitudMatriculaDAO lsm_DAO;

					lsm_DAO                    = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
					lsm_solicitudMatricula     = lsm_DAO.findById(asm_sm);

					if(lsm_solicitudMatricula != null)
					{
						{
							SolicitudMatriculaActoDAO          lsma_DAO;
							SolicitudMatriculaActo             lsma_sma;
							Collection<SolicitudMatriculaActo> lcsma_solicitudMatriculaActo;

							lsma_DAO                         = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);
							lsma_sma                         = new SolicitudMatriculaActo(lsm_solicitudMatricula);
							lcsma_solicitudMatriculaActo     = lsma_DAO.findAllByIdSolicitud(lsma_sma, true);

							if(CollectionUtils.isValidCollection(lcsma_solicitudMatriculaActo))
								lsma_DAO.deleteBySolicitudCirculoMatricula(lsma_sma);
						}

						{
							Collection<MatriculaSegregacion> lcms_cms;
							MatriculaSegregacionDAO          lms_DAO;
							MatriculaSegregacion             lms_ms;

							lms_DAO      = DaoCreator.getMatriculaSegregacionDAO(ldm_manager);
							lms_ms       = new MatriculaSegregacion(lsm_solicitudMatricula);
							lcms_cms     = lms_DAO.findByIdSolicitud(lms_ms, false);

							if(CollectionUtils.isValidCollection(lcms_cms))
								lms_DAO.deleteBySolicitudCirculoMatricula(lms_ms);
						}

						lsm_DAO.deleteBySolicitudCirculoMatricula(lsm_solicitudMatricula);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("deleteBySolicitudCirculoMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de eliminar los registros de Solicitud Corrección para el circulo registral, matricula y solicitud especificos.
	 *
	 * @param asc_solicitudCorreccion Objeto que contiene los datos de Solicitud Corrección a eliminar.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	public synchronized void deleteCausalesCorreccion(SolicitudCorreccion asc_solicitudCorreccion)
	    throws B2BException, SQLException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asc_solicitudCorreccion != null)
				DaoCreator.getSolicitudCorreccionDAO(ldm_manager).deleteAll(asc_solicitudCorreccion);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("deleteCausalesCorreccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de eliminar las Matriculas segregación.
	 *
	 * @param ams_matriculaSegregacion Objeto que contiene la información de las matriculas a borrar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void deleteMatriculaSegregacion(MatriculaSegregacion ams_matriculaSegregacion)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ams_matriculaSegregacion != null)
				DaoCreator.getMatriculaSegregacionDAO(ldm_manager)
					          .deleteBySolicitudCirculoMatricula(ams_matriculaSegregacion);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("deleteMatriculaSegregacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de consultar una constante dependiendo del tipo criterio
	 * búsqueda de un <code>CamposConsulta</code>.
	 *
	 * @param acc_camposConsulta            <code>CamposConsulta</code> con el cual se realizará la búsqueda
	 *            de la constante.
	 * @return <code>Constantes</code> lleno con la infromación de la BD.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes descargarPlantillaCargue(CamposConsulta acc_camposConsulta)
	    throws B2BException
	{
		Constantes lct_datos;

		lct_datos = null;

		if(acc_camposConsulta != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_tipoCriterioBusqueda;

				ls_tipoCriterioBusqueda = acc_camposConsulta.getIdTipoCriterioBusqueda();

				if(StringUtils.isValidString(ls_tipoCriterioBusqueda))
				{
					String ls_idConstante;

					ls_idConstante = null;

					switch(ls_tipoCriterioBusqueda)
					{
						case TipoCriterioBusquedaCommon.IDENTIFICACION:
							ls_idConstante = ConstanteCommon.PLANTILLA_CARGUE_CONSULTAS_IDENTIFICACION;

							break;

						case TipoCriterioBusquedaCommon.DOCUMENTO:
							ls_idConstante = ConstanteCommon.PLANTILLA_CARGUE_CONSULTAS_DOCUMENTO;

							break;

						case TipoCriterioBusquedaCommon.DIRECCION:
							ls_idConstante = ConstanteCommon.PLANTILLA_CARGUE_CONSULTAS_DIRECCION;

							break;

						case TipoCriterioBusquedaCommon.CEDULA_CATASTRAL:
							ls_idConstante = ConstanteCommon.PLANTILLA_CARGUE_CONSULTAS_CEDULA_CATASTRAL;

							break;

						case TipoCriterioBusquedaCommon.MATRICULA:
							ls_idConstante = ConstanteCommon.PLANTILLA_CARGUE_CONSULTAS_MATRICULA;

							break;

						case TipoCriterioBusquedaCommon.NUPRE:
							ls_idConstante = ConstanteCommon.PLANTILLA_CARGUE_CONSULTAS_NUPRE;

							break;

						case TipoCriterioBusquedaCommon.DOCUMENTO_COPIAS:
							ls_idConstante = ConstanteCommon.PLANTILLA_CARGUE_COPIAS_DOCUMENTO;

							break;

						default:
							break;
					}

					if(StringUtils.isValidString(ls_idConstante))
						lct_datos = DaoCreator.getConstantesDAO(ldm_manager)
								                  .findByIdWithImage(new Constantes(ls_idConstante));
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("descargarPlantillaCargue", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lct_datos;
	}

	/**
	 * Método encargado de buscar el detalle de los turnos de recepcion de documentos.
	 *
	 * @param ad_parametros correspondiente al valor del tipo de objeto Documento
	 * @param ab_consultaDocumento correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<Turno> detalleTurnosRecepcion(
	    Documento ad_parametros, boolean ab_consultaDocumento, String as_userId
	)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Turno> lct_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lct_datos       = new ArrayList<Turno>();

		try
		{
			if(ad_parametros != null)
			{
				String  ls_idProcesoRecepcion;
				String  ls_idSubProcesoRecepcion;
				boolean lb_esSubprocesoRevocatoriaDirecta;

				ls_idProcesoRecepcion                 = ad_parametros.getIdProcesoRecepcion();
				ls_idSubProcesoRecepcion              = ad_parametros.getIdSubProcesoRecepcion();
				lb_esSubprocesoRevocatoriaDirecta     = StringUtils.isValidString(ls_idSubProcesoRecepcion)
						&& ls_idSubProcesoRecepcion.equalsIgnoreCase(SubProcesoCommon.REVOCATORIA_DIRECTA);

				if(StringUtils.isValidString(ls_idProcesoRecepcion))
				{
					ConstantesDAO lcd_DAO;
					Proceso       lp_proceso;

					lcd_DAO        = DaoCreator.getConstantesDAO(ldm_manager);
					lp_proceso     = new Proceso();

					lp_proceso.setIdProceso(ls_idProcesoRecepcion);

					lp_proceso = DaoCreator.getProcesoDAO(ldm_manager).findById(lp_proceso);

					if(lp_proceso == null)
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] = ls_idProcesoRecepcion;

						throw new B2BException(ErrorKeys.PROCESO_NO_EXISTE, loa_messageArgs);
					}

					String ls_idEtapas;

					ls_idEtapas = null;

					if(ls_idProcesoRecepcion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43))
					{
						String ls_idConstante;

						ls_idConstante     = ConstanteCommon.ETAPAS_VALIDAS_RESTITUCION_TURNOS;

						ls_idEtapas = lcd_DAO.findString(ls_idConstante);

						if(!StringUtils.isValidString(ls_idEtapas))
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ls_idConstante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						if(
						    getDevolucionDineroBusiness()
							        .validacionTurnoDerivadoDevolucion(ad_parametros.getIdTurno(), ldm_manager)
						)
							throw new B2BException(ErrorKeys.ERROR_TURNO_PROCESO_DEVOLUCION_DINERO_CONSULTADO);
					}

					if(ad_parametros.isAccion())
					{
						DocumentoConstancia             ldc_dc;
						Collection<DocumentoConstancia> lcdc_dc;

						ldc_dc = new DocumentoConstancia();

						ldc_dc.setFechaDocumento(ad_parametros.getFechaDocumento());
						ldc_dc.setNumero(ad_parametros.getNumero());
						ldc_dc.setIdTipoDocumento(ad_parametros.getIdTipoDocumento());
						ldc_dc.setIdOficinaOrigen(ad_parametros.getIdOficinaOrigen());

						lcdc_dc = DaoCreator.getDocumentoDAO(ldm_manager).consultaMaxDocConstancia(ldc_dc);

						if(CollectionUtils.isValidCollection(lcdc_dc))
						{
							Iterator<DocumentoConstancia> lidc_dc;
							lidc_dc = lcdc_dc.iterator();

							while(lidc_dc.hasNext())
							{
								DocumentoConstancia ldc_tmp;
								ldc_tmp = lidc_dc.next();

								if(ldc_tmp != null)
								{
									Collection<Turno> lct_tmp;

									ad_parametros.setIdDocumento(ldc_tmp.getIdDocumento());
									ad_parametros.setVersionDocumento(ldc_tmp.getVersionDocumento());

									lct_tmp = DaoCreator.getTurnoDAO(ldm_manager)
											                .consultaDetalleTurnos(ad_parametros, ls_idEtapas);

									if(CollectionUtils.isValidCollection(lct_tmp))
										lct_datos.addAll(lct_tmp);
									else
									{
										if(!ab_consultaDocumento)
											erroresDinamicosConsultaDetalleTurnos(ad_parametros);
									}
								}
							}
						}
					}
					else
					{
						boolean lb_proceso39;
						boolean lb_proceso40;
						boolean lb_proceso41;
						boolean lb_proceso42;
						boolean lb_mensajeProceso40Y41TER;
						boolean lb_proceso46;
						boolean lb_proceso47;
						boolean lb_proceso48;

						lb_proceso39                  = ls_idProcesoRecepcion.equalsIgnoreCase(
							    ProcesoCommon.ID_PROCESO_39
							);
						lb_proceso40                  = ls_idProcesoRecepcion.equalsIgnoreCase(
							    ProcesoCommon.ID_PROCESO_40
							);
						lb_proceso41                  = ls_idProcesoRecepcion.equalsIgnoreCase(
							    ProcesoCommon.ID_PROCESO_41
							);
						lb_proceso42                  = ls_idProcesoRecepcion.equalsIgnoreCase(
							    ProcesoCommon.ID_PROCESO_42
							);
						lb_mensajeProceso40Y41TER     = false;
						lb_proceso46                  = ls_idProcesoRecepcion.equalsIgnoreCase(
							    ProcesoCommon.ID_PROCESO_46
							);
						lb_proceso47                  = ls_idProcesoRecepcion.equalsIgnoreCase(
							    ProcesoCommon.ID_PROCESO_47
							);
						lb_proceso48                  = ls_idProcesoRecepcion.equalsIgnoreCase(
							    ProcesoCommon.ID_PROCESO_48
							);

						if(lb_proceso40 || lb_proceso42 || lb_proceso41 || lb_proceso47 || lb_proceso48)
						{
							Collection<TurnoHistoria> lcth_turnosHistorias;
							TurnoHistoria             lth_tmp;
							String                    ls_idTurno;

							lth_tmp                  = new TurnoHistoria();
							ls_idTurno               = ad_parametros.getIdTurno();
							lcth_turnosHistorias     = null;

							if(StringUtils.isValidString(ls_idTurno))
							{
								Turno lt_turno;
								lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

								if(lt_turno == null)
									throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);

								lth_tmp.setIdTurno(ls_idTurno);

								if(lb_proceso40)
								{
									boolean    lb_segundRecepcion;
									Constantes lc_subProcesos;
									String     ls_subProcesos;

									lc_subProcesos         = lcd_DAO.findById(
										    ConstanteCommon.SUBPROCESOS_VALIDOS_CERTIFICADOS
										);
									lb_segundRecepcion     = lc_subProcesos != null;
									ls_subProcesos         = null;

									if(lb_segundRecepcion)
										ls_subProcesos = lc_subProcesos.getCaracter();

									lcth_turnosHistorias = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
											                             .findByTurnoSuspensionTramiteEntregaDoc(
											    lth_tmp, lb_segundRecepcion, ls_subProcesos
											);
								}
								else if(lb_proceso41)
									lcth_turnosHistorias = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
											                             .findByTurnoSuspensionTramiteProrrogaEntregaDoc(
											    lth_tmp
											);
								else if(lb_proceso42)
									lcth_turnosHistorias = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
											                             .findByTurnoProrrogaMayorValor(lth_tmp);
								else if(lb_proceso47 || lb_proceso48)
									lcth_turnosHistorias = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
											                             .findByTurnoSegundaInstancia(lth_tmp);

								if(lb_proceso42)
								{
									TurnoDerivado lt_turnoDerivado;
									lt_turnoDerivado = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
											                         .findByIdTurnoMayorProrroga(ls_idTurno);

									if(lt_turnoDerivado != null)
										throw new B2BException(ErrorKeys.ERROR_TURNO_YA_SOLICITO_PRORROGA_MAYOR_VALOR);
								}

								if(lb_proceso40 || lb_proceso42 || lb_proceso41 || lb_proceso47 || lb_proceso48)
								{
									if(CollectionUtils.isValidCollection(lcth_turnosHistorias))
									{
										for(TurnoHistoria lth_turnoHistTMP : lcth_turnosHistorias)
										{
											if(lth_turnoHistTMP != null)
											{
												long   ll_idEtapa;
												String ls_estadoActividad;

												ll_idEtapa             = NumericUtils.getLong(
													    lth_turnoHistTMP.getIdEtapa()
													);
												ls_estadoActividad     = lth_turnoHistTMP.getEstadoActividad();

												if(
												    ((ll_idEtapa == EtapaCommon.ID_ETAPA_MAYOR_VALOR_VENCIDO)
													    || (ll_idEtapa == EtapaCommon.ID_ETAPA_PENDIENTE_PAGO_MAYOR_VALOR_REGISTRO))
													    && lb_proceso42
												)
												{
													if(StringUtils.isValidString(ls_estadoActividad))
													{
														if(ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA))
															throw new B2BException(
															    ErrorKeys.ERROR_TURNO_SELECCIONADO_ESTA_ETAPA_FINAL_PRORROGA
															);
													}
												}

												if(lb_proceso47 || lb_proceso48)
													ad_parametros.setIdEtapa(ll_idEtapa);

												if(lb_proceso40 || lb_proceso41)
												{
													if(ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA))
													{
														lb_mensajeProceso40Y41TER = true;
														ad_parametros.setIdEtapa(ll_idEtapa);
													}
													else if(
													    ls_estadoActividad.equalsIgnoreCase(EstadoCommon.AUTOMATICA)
													)
													{
														ad_parametros.setIdEtapa(ll_idEtapa);

														if(
														    (ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_SUSPENSION_DE_TERMINOS)
															    || (ll_idEtapa == EtapaCommon.ID_ETAPA_SUSPENSION_TRAMITE)
															    || (ll_idEtapa == EtapaCommon.ID_ETAPA_PENDIENTE_COMPLETITUD_DOCUMENTAL_CORRECIONES)
															    || (ll_idEtapa == EtapaCommon.ID_ETAPA_SUSPENSION_PRORROGA_PARA_APORTAR_DOCUMENTACION)
															    || (ll_idEtapa == EtapaCommon.PENDIENTE_APORTAR_PRUEBAS_ACTUACION_ADMINISTRATIVA)
															    || (ll_idEtapa == EtapaCommon.SUSPENSION_PRORROGA_PARA_APORTAR_DOCUMENTACION)
														)
														{
															lb_mensajeProceso40Y41TER = false;

															break;
														}
													}
												}
											}
										}
									}
								}

								if(lb_proceso48)
								{
									if(
									    CollectionUtils.isValidCollection(lcth_turnosHistorias)
										    && lb_esSubprocesoRevocatoriaDirecta
									)
									{
										for(TurnoHistoria lth_tmp2 : lcth_turnosHistorias)
										{
											String ls_idSolicitud;
											ls_idSolicitud = lth_tmp2.getIdSolicitud();

											if(StringUtils.isValidString(ls_idSolicitud))
											{
												Collection<SolicitudAsociada> lcs_solicitudAsociada;

												lcs_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
														                              .findAllByIdSolicitud(
														    ls_idSolicitud, false
														);

												if(CollectionUtils.isValidCollection(lcs_solicitudAsociada))
												{
													SolicitudDAO lsd_DAO;

													lsd_DAO = DaoCreator.getSolicitudDAO(ldm_manager);

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
																		    && (ls_idProceso.equalsIgnoreCase(
																		        ProcesoCommon.ID_PROCESO_47
																		    )
																		    || ls_idProceso.equalsIgnoreCase(
																		        ProcesoCommon.ID_PROCESO_48
																		    ))
																	)
																		throw new B2BException(
																		    ErrorKeys.ERROR_EL_TURNO_O_NIR_CONSULTADO_INTERPUSO_RECURSO
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

								if(CollectionUtils.isValidCollection(lcth_turnosHistorias))
									lct_datos = DaoCreator.getTurnoDAO(ldm_manager)
											                  .consultaDetalleTurnos(ad_parametros, ls_idEtapas);
								else
								{
									if(lb_proceso40)
										throw new B2BException(
										    ErrorKeys.ERROR_TURNO_INGRESADO_NO_SE_ENCUENTRA_EN_SUSPENSION
										);
									else if(lb_proceso42)
										throw new B2BException(
										    ErrorKeys.ERROR_TURNO_SELECCIONADO_NO_TRAMITE_PRORROGA_MAYOR_V
										);
									else if(lb_proceso41)
									{
										Object[] loa_object;

										loa_object        = new String[1];
										loa_object[0]     = ad_parametros.getIdTurno();

										throw new B2BException(
										    ErrorKeys.ERROR_TURNO_SELECCIONADO_NO_TRAMITE_PRORROGA_ENTREGA_DOC,
										    loa_object
										);
									}
								}
							}
						}
						else if(lb_proceso46)
							lct_datos = DaoCreator.getTurnoDAO(ldm_manager).consultarNirEtapa18(ad_parametros.getNir());
						else
							lct_datos = DaoCreator.getTurnoDAO(ldm_manager)
									                  .consultaDetalleTurnos(ad_parametros, ls_idEtapas);

						if(!CollectionUtils.isValidCollection(lct_datos))
						{
							if(lb_proceso46)
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);

							if(lb_proceso47)
							{
								Object[] loa_object;

								loa_object        = new String[1];
								loa_object[0]     = ad_parametros.getIdTurno();

								throw new B2BException(
								    ErrorKeys.ERROR_TURNO_NO_SE_ENCUENTRA_EN_ETAPA_RECURSOS, loa_object
								);
							}

							if(!ab_consultaDocumento)
								erroresDinamicosConsultaDetalleTurnos(ad_parametros);
						}
						else
						{
							if(lb_proceso39)
							{
								Turno lt_turno;
								lt_turno = DaoCreator.getTurnoDAO(ldm_manager)
										                 .findByTurnoTipoActoPermuta(ad_parametros.getIdTurno());

								if(lt_turno != null)
								{
									Collection<Turno> lct_turnos;
									lct_turnos = DaoCreator.getTurnoDAO(ldm_manager)
											                   .findByTurnosDiferenteCirculo(lt_turno);

									if(CollectionUtils.isValidCollection(lct_turnos))
									{
										for(Turno lt_turnoTMP : lct_turnos)
										{
											if(lt_turnoTMP != null)
											{
												Turno lt_turnoActo;
												lt_turnoActo = DaoCreator.getTurnoDAO(ldm_manager)
														                     .findByTurnoTipoActoPermuta(
														    lt_turnoTMP.getIdTurno()
														);

												if(lt_turnoActo != null)
												{
													for(Turno lt_turnoTMP2 : lct_datos)
													{
														if(lt_turnoTMP2 != null)
															lt_turnoTMP2.setTurnoTipoActoPermuta(true);
													}
												}
											}
										}
									}
								}
							}

							if((lb_proceso40 || lb_proceso41) && lb_mensajeProceso40Y41TER)
							{
								if(lb_mensajeProceso40Y41TER)
								{
									for(Turno lt_turnoTMP2 : lct_datos)
									{
										if(lt_turnoTMP2 != null)
											lt_turnoTMP2.setTurnoSuspensionConEtapaTER(true);
									}
								}
							}

							if(lb_proceso46)
							{
								for(Turno lt_turnoTMP : lct_datos)
								{
									if(lt_turnoTMP != null)
									{
										Collection<Acto> lca_actos;
										Acto             la_acto;
										la_acto = new Acto();

										la_acto.setIdSolicitud(lt_turnoTMP.getIdSolicitud());
										la_acto.setIdCirculo(lt_turnoTMP.getIdCirculo());

										lca_actos = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudCirculo(
											    la_acto
											);

										if(CollectionUtils.isValidCollection(lca_actos))
										{
											String ls_tiposActoPorCirculo;
											ls_tiposActoPorCirculo = null;

											for(Acto la_actoTMP : lca_actos)
											{
												if(la_actoTMP != null)
												{
													String ls_idTipoActo;
													ls_idTipoActo = la_actoTMP.getIdTipoActo();

													if(StringUtils.isValidString(ls_idTipoActo))
													{
														TipoActo lta_tipoActo;
														lta_tipoActo = DaoCreator.getTipoActoDAO(ldm_manager)
																                     .findByIdAndIsImpuestoRegistro(
																    ls_idTipoActo
																);

														if(lta_tipoActo != null)
														{
															if(!StringUtils.isValidString(ls_tiposActoPorCirculo))
																ls_tiposActoPorCirculo = ls_idTipoActo
																	+ IdentificadoresCommon.SIMBOLO_GUION
																	+ lta_tipoActo.getNombre();
															else
																ls_tiposActoPorCirculo = ls_tiposActoPorCirculo
																	+ IdentificadoresCommon.SIMBOLO_COMA
																	+ IdentificadoresCommon.ESPACIO_VACIO
																	+ ls_idTipoActo
																	+ IdentificadoresCommon.SIMBOLO_GUION
																	+ lta_tipoActo.getNombre();
														}
													}
												}
											}

											lt_turnoTMP.setTiposActoPorCirculo(ls_tiposActoPorCirculo);
										}

										Collection<SolicitudMatricula> lcsm_solicitudesMatriculas;

										lcsm_solicitudesMatriculas = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
												                                   .findByIdSolicitudCirculo(
												    lt_turnoTMP.getIdSolicitud(), lt_turnoTMP.getIdCirculo()
												);

										if(CollectionUtils.isValidCollection(lcsm_solicitudesMatriculas))
										{
											String ls_circuloMatriculas;
											ls_circuloMatriculas = null;

											for(SolicitudMatricula lsm_tmp : lcsm_solicitudesMatriculas)
											{
												if(lsm_tmp != null)
												{
													if(!StringUtils.isValidString(ls_circuloMatriculas))
														ls_circuloMatriculas = lsm_tmp.getIdCirculo()
															+ IdentificadoresCommon.SIMBOLO_GUION
															+ lsm_tmp.getIdMatricula();
													else
														ls_circuloMatriculas = ls_circuloMatriculas
															+ IdentificadoresCommon.SIMBOLO_COMA
															+ IdentificadoresCommon.ESPACIO_VACIO
															+ lsm_tmp.getIdCirculo()
															+ IdentificadoresCommon.SIMBOLO_GUION
															+ lsm_tmp.getIdMatricula();
												}
											}

											lt_turnoTMP.setIdCirculosYMatriculas(ls_circuloMatriculas);
										}
									}
								}
							}

							if(!ab_consultaDocumento && !lb_proceso46)
							{
								Turno lt_turno;

								lt_turno = new Turno();

								lt_turno.setIdTurno(ad_parametros.getIdTurno());
								lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

								if(lt_turno != null)
								{
									boolean lb_procesoProrrogas;
									String  ls_proceso;

									ls_proceso              = lt_turno.getIdProceso();
									lb_procesoProrrogas     = ls_idProcesoRecepcion.equalsIgnoreCase(
										    ProcesoCommon.ID_PROCESO_41
										)
											&& !((NumericUtils.getLong(lt_turno.getIdEtapaActual()) == EtapaCommon.ID_ETAPA_PENDIENTE_COMPLETITUD_DOCUMENTAL_CORRECIONES)
											|| (NumericUtils.getLong(lt_turno.getIdEtapaActual()) == EtapaCommon.PENDIENTE_APORTAR_PRUEBAS_ACTUACION_ADMINISTRATIVA))
											&& ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3);

									if(lb_procesoProrrogas)
									{
										Object[] loa_messageArgs = new String[1];

										loa_messageArgs[0] = StringUtils.getStringLowerCase(lt_turno.getIdTurno());

										throw new B2BException(
										    ErrorKeys.ERROR_TURNO_PRORROGA_DOCUMENTACION, loa_messageArgs
										);
									}
									else if(!lb_proceso47 && !lb_proceso48)
									{
										Subproceso lsp_sp;
										lsp_sp = new Subproceso();
										lsp_sp.setIdProceso(ls_idProcesoRecepcion);
										lsp_sp.setIdSubproceso(ls_proceso);
										lsp_sp = DaoCreator.getSubprocesoDAO(ldm_manager).findById(lsp_sp);

										if(lsp_sp == null)
										{
											Object[] loa_messageArgs    = new String[3];
											Proceso  lp_procesoPantalla;

											lp_procesoPantalla          = new Proceso();

											lp_procesoPantalla.setIdProceso(ls_idProcesoRecepcion);
											lp_procesoPantalla = DaoCreator.getProcesoDAO(ldm_manager)
													                           .findById(lp_procesoPantalla);

											if(lp_procesoPantalla != null)
											{
												Proceso lp_procesoTurno;
												lp_procesoTurno = new Proceso();

												lp_procesoTurno.setIdProceso(lt_turno.getIdProceso());
												lp_procesoTurno = DaoCreator.getProcesoDAO(ldm_manager)
														                        .findById(lp_procesoTurno);

												if(lp_procesoTurno != null)
												{
													loa_messageArgs[0]     = lt_turno.getIdTurno();
													loa_messageArgs[1]     = StringUtils.getStringLowerCase(
														    lp_procesoTurno.getNombre()
														);
													loa_messageArgs[2]     = StringUtils.getStringLowerCase(
														    lp_procesoPantalla.getNombre()
														);
													throw new B2BException(
													    ErrorKeys.TURNO_PROCESO_TIPO_TRAMITE_INVALIDO, loa_messageArgs
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
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("detalleTurnosRecepcion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lct_datos;
	}

	/**
	 * Método encargado de traer la información del documento para el proceso nueva entrada.
	 *
	 * @param ar_registro Objeto que contiene la información del documento a consultar.
	 * @return Objeto que contiene el documento consultado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro documentoNuevaEntrada(Registro ar_registro)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_registro != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = ar_registro.getSolicitud();

				if(ls_solicitud != null)
				{
					String ls_turnoRelacionado;

					ls_turnoRelacionado = ls_solicitud.getIdTurnoAnt();

					if(StringUtils.isValidString(ls_turnoRelacionado))
					{
						Turno    lt_turno;
						TurnoDAO lt_DAO;

						lt_turno     = new Turno();
						lt_DAO       = DaoCreator.getTurnoDAO(ldm_manager);

						lt_turno.setIdTurno(ls_turnoRelacionado);

						lt_turno = lt_DAO.findById(lt_turno);

						if(lt_turno != null)
						{
							String ls_idSolicitud;

							ls_idSolicitud = lt_turno.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud    ls_solicitudRelacionada;
								SolicitudDAO ls_DAO;

								ls_solicitudRelacionada     = new Solicitud();
								ls_DAO                      = DaoCreator.getSolicitudDAO(ldm_manager);

								ls_solicitudRelacionada.setIdSolicitud(ls_idSolicitud);

								ls_solicitudRelacionada = ls_DAO.findById(ls_solicitudRelacionada);

								if(ls_solicitudRelacionada != null)
								{
									String ls_idDocumento;

									ls_idDocumento = ls_solicitudRelacionada.getIdDocumento();

									if(StringUtils.isValidString(ls_idDocumento))
									{
										Documento    ld_documento;
										DocumentoDAO ld_DAO;
										Long         ll_versionDocumento;

										ld_documento     = new Documento();
										ld_DAO           = DaoCreator.getDocumentoDAO(ldm_manager);

										ld_documento.setIdDocumento(ls_idDocumento);
										ls_solicitud.setIdDocumento(ls_idDocumento);

										ll_versionDocumento = ld_DAO.findMaxVersionDocumentoByIdDocumento(ld_documento);

										ld_documento.setVersionDocumento(ll_versionDocumento);

										ld_documento = ld_DAO.findByIdDocumentoVersion(ld_documento);

										if(ld_documento != null)
										{
											String     ls_idOficinaOrigen;
											BigDecimal lbd_version;

											ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
											lbd_version            = ld_documento.getVersion();

											if(StringUtils.isValidString(ls_idOficinaOrigen) && (lbd_version != null))
											{
												OficinaOrigen    loo_oficinaOrigen;
												OficinaOrigenDAO loo_DAO;

												loo_oficinaOrigen     = new OficinaOrigen();
												loo_DAO               = DaoCreator.getOficinaOrigenDAO(ldm_manager);

												loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
												loo_oficinaOrigen.setVersion(lbd_version);

												loo_oficinaOrigen = loo_DAO.findById(loo_oficinaOrigen);

												if(loo_oficinaOrigen != null)
													ar_registro.setOficinaOrigen(loo_oficinaOrigen);
											}

											ar_registro.setBgnDocumento(ld_documento);
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

			clh_LOGGER.error("documentoNuevaEntrada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Método encargado de eliminar los actos asociados a una solicitud en el proceso de reproducción  de constancia.
	 *
	 * @param ar_registro objeto contenedor de parametros
	 * @param as_userId usuario logueado en la aplicación
	 * @return Registro objeto que se retorna como una respuesta post a la eliminación
	 * @throws B2BException error si ocurre una excepción
	 * @see Registro
	 */
	public synchronized Registro eliminarActosReproduccionConstancia(Registro ar_registro, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_registro != null)
			{
				Solicitud lso_solicitud;
				lso_solicitud = ar_registro.getSolicitud();

				if(lso_solicitud != null)
				{
					ActoDAO          la_DAO;
					Acto             lo_acto;
					Collection<Acto> lca_actos;
					String           ls_idSolicitud;

					lo_acto            = new Acto();
					ls_idSolicitud     = lso_solicitud.getIdSolicitud();
					lo_acto.setIdSolicitud(ls_idSolicitud);

					la_DAO        = DaoCreator.getActoDAO(ldm_manager);
					lca_actos     = la_DAO.findByIdSolicitud(lo_acto);

					if(CollectionUtils.isValidCollection(lca_actos))
					{
						SolicitudMatriculaActoDAO losm_DAO;
						losm_DAO = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);

						{
							LiquidacionItemDAO      lol_DAO;
							Liquidacion             lol_liquidacion;
							Collection<Liquidacion> lcl_liquidacion;

							lol_DAO             = DaoCreator.getAccLiquidacionItemDAO(ldm_manager);
							lol_liquidacion     = new Liquidacion();

							lol_liquidacion.setIdSolicitud(ls_idSolicitud);

							lcl_liquidacion = lol_DAO.findByIdSolicitud(lol_liquidacion);

							if(CollectionUtils.isValidCollection(lcl_liquidacion))
							{
								for(Liquidacion lol_tmp : lcl_liquidacion)
								{
									if(lol_tmp != null)
										lol_DAO.deleteLiquidacionItem(lol_tmp);
								}
							}
						}

						for(Acto loa_tmp : lca_actos)
						{
							if(loa_tmp != null)
							{
								String ls_idActo;

								ls_idActo = loa_tmp.getIdActo();

								if(StringUtils.isValidString(ls_idActo))
								{
									SolicitudMatriculaActo lsm_solicitudMatricula;
									lsm_solicitudMatricula = new SolicitudMatriculaActo();

									lsm_solicitudMatricula.setIdActo(ls_idActo);

									losm_DAO.deleteByIdActo(lsm_solicitudMatricula);
									la_DAO.delete(loa_tmp);
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

			clh_LOGGER.error("eliminarActosReproduccionConstancia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Elimina los datos de antiguo sistema de un predio por medio de su id, ademas de los
	 * detalles de antiguo sistema que se encuentren asociados a este id.
	 *
	 * @param adas_datos Objeto contenedor del id a utilizar como filtro para la eliminación
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<DatosAntSistema> eliminarDatosAntSistema(
	    DatosAntSistema adas_datos, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<DatosAntSistema> lcdas_datosSolicitud;

		ldm_manager              = DaoManagerFactory.getDAOManager();
		lcdas_datosSolicitud     = null;

		try
		{
			if(adas_datos == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			DetalleAntSistema  ldas_detalle;
			String             ls_idDatosAnt;
			DatosAntSistemaDAO ldasd_datosAntSisDAO;

			ldas_detalle             = new DetalleAntSistema();
			ls_idDatosAnt            = adas_datos.getIdDatosAntSistema();
			ldasd_datosAntSisDAO     = DaoCreator.getDatosAntSistemaDAO(ldm_manager);

			ldas_detalle.setIdDatosAntSistema(ls_idDatosAnt);

			DaoCreator.getDetalleAntSistemaDAO(ldm_manager).deleteByDatosAntSis(ldas_detalle);

			DaoCreator.getAntSistemaActoDAO(ldm_manager).deleteByIdDatosAntSis(ls_idDatosAnt);

			ldasd_datosAntSisDAO.delete(adas_datos);

			lcdas_datosSolicitud = ldasd_datosAntSisDAO.findByIdSolicitud(adas_datos, true);

			if(CollectionUtils.isValidCollection(lcdas_datosSolicitud))
			{
				int li_cont;

				li_cont = 1;

				for(DatosAntSistema ldas_dato : lcdas_datosSolicitud)
				{
					if(ldas_dato != null)
					{
						ldas_dato.setConsecutivoPredioAntSistema(StringUtils.getString(li_cont));
						ldas_dato.setIdUsuarioModificacion(as_userId);
						ldas_dato.setIpModificacion(as_remoteIp);

						ldasd_datosAntSisDAO.updateConsecutivo(ldas_dato);

						li_cont++;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("eliminarDatosAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdas_datosSolicitud;
	}

	/**
	 * Elimina los detalles de antiguo sistema de un predio por medio de su id.
	 *
	 * @param adas_detalle Objeto contenedor del id a utilizar como filtro para la eliminación
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void eliminarDetalleAntSistema(DetalleAntSistema adas_detalle)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adas_detalle != null)
				DaoCreator.getDetalleAntSistemaDAO(ldm_manager)
					          .deleteByIdDetalleAntSistemaUI(
					    adas_detalle.getIdDatosAntSistema(), adas_detalle.getIdDetalleAntSistema()
					);
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
	 * Método encargado de eliminar los intervinientes.
	 *
	 * @param asi_parametros Objeto que contiene la información de los intervinientes a eliminar.
	 * @param as_userId Variable de tipo String que contiene el usuario que está ejecutando la acción.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void eliminarInterviniente(SolicitudInterviniente asi_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asi_parametros != null)
				DaoCreator.getSolicitudIntervinienteDAO(ldm_manager).delete(asi_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("eliminarInterviniente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Elimina los detalles de antiguo sistema de un predio por medio de su id.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void eliminarMatriculaSegregacionPorSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				MatriculaSegregacionDAO          lmsd_DAO;
				Collection<MatriculaSegregacion> lcms_matriculasActuales;
				MatriculaSegregacion             lms_ms;

				lmsd_DAO     = DaoCreator.getMatriculaSegregacionDAO(ldm_manager);
				lms_ms       = new MatriculaSegregacion();
				lms_ms.setIdSolicitud(as_idSolicitud);

				lcms_matriculasActuales = lmsd_DAO.findByIdSolicitud(lms_ms, true);

				if(CollectionUtils.isValidCollection(lcms_matriculasActuales))
					lmsd_DAO.deleteBySolicitud(lms_ms);
			}
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
	 * Metodo que verifica el interviniente ingresado contra los actuales usando un mapa.
	 *
	 * @param acsi_intervinientesActuales <Code>Collection<SolicitudInterviniente></Code> Colección con los intervinientes actuales
	 * @param asi_intervinienteIngresado SolicitudInterviniente con el interviniente a comparar
	 * @return  Collection<SolicitudInterviniente> con colección verificada
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<SolicitudInterviniente> enlistarPorMapaIntervinientes(
	    Collection<SolicitudInterviniente> acsi_intervinientesActuales,
	    SolicitudInterviniente             asi_intervinienteIngresado, long al_etapaActual
	)
	    throws B2BException
	{
		Collection<SolicitudInterviniente> lcsi_return;

		lcsi_return = new ArrayList<SolicitudInterviniente>();

		if(CollectionUtils.isValidCollection(acsi_intervinientesActuales) && (asi_intervinienteIngresado != null))
		{
			Persona lp_personaIngresada;

			lp_personaIngresada = asi_intervinienteIngresado.getPersona();

			if(lp_personaIngresada != null)
			{
				String ls_rolPersonaIngresado;
				String ls_tipoDocumentoIngresado;
				String ls_documentoIngresado;

				ls_rolPersonaIngresado        = asi_intervinienteIngresado.getRolPersona();
				ls_tipoDocumentoIngresado     = lp_personaIngresada.getIdDocumentoTipo();
				ls_documentoIngresado         = lp_personaIngresada.getNumeroDocumento();

				if(
				    StringUtils.isValidString(ls_rolPersonaIngresado)
					    && StringUtils.isValidString(ls_tipoDocumentoIngresado)
					    && StringUtils.isValidString(ls_documentoIngresado)
				)
				{
					StringBuilder lsb_llaveIntervinienteIngresado;
					String        ls_llaveIntervinienteIngresado;

					lsb_llaveIntervinienteIngresado = new StringBuilder();

					lsb_llaveIntervinienteIngresado.append(ls_rolPersonaIngresado.toUpperCase());
					lsb_llaveIntervinienteIngresado.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
					lsb_llaveIntervinienteIngresado.append(ls_tipoDocumentoIngresado.toUpperCase());
					lsb_llaveIntervinienteIngresado.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
					lsb_llaveIntervinienteIngresado.append(ls_documentoIngresado.toUpperCase());

					ls_llaveIntervinienteIngresado = lsb_llaveIntervinienteIngresado.toString();

					if(StringUtils.isValidString(ls_llaveIntervinienteIngresado))
					{
						Map<String, SolicitudInterviniente> lmssi_solicitudIntervinienteActual;

						lmssi_solicitudIntervinienteActual = new HashMap<String, SolicitudInterviniente>();

						for(SolicitudInterviniente lsi_solicitudIntervinienteActual : acsi_intervinientesActuales)
						{
							if(lsi_solicitudIntervinienteActual != null)
							{
								Persona lp_personaActual;

								lp_personaActual = lsi_solicitudIntervinienteActual.getPersona();

								if(lp_personaActual != null)
								{
									String ls_rolPersonaActual;
									String ls_tipoDocumentoActual;
									String ls_documentoActual;

									ls_rolPersonaActual        = lsi_solicitudIntervinienteActual.getRolPersona();
									ls_tipoDocumentoActual     = lp_personaActual.getIdDocumentoTipo();
									ls_documentoActual         = lp_personaActual.getNumeroDocumento();

									if(
									    StringUtils.isValidString(ls_rolPersonaActual)
										    && StringUtils.isValidString(ls_tipoDocumentoActual)
										    && StringUtils.isValidString(ls_documentoActual)
									)
									{
										StringBuilder lsb_llaveIntervinienteActual;
										String        ls_llaveIntervinienteActual;

										lsb_llaveIntervinienteActual = new StringBuilder();

										lsb_llaveIntervinienteActual.append(ls_rolPersonaActual.toUpperCase());
										lsb_llaveIntervinienteActual.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
										lsb_llaveIntervinienteActual.append(ls_tipoDocumentoActual.toUpperCase());
										lsb_llaveIntervinienteActual.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
										lsb_llaveIntervinienteActual.append(ls_documentoActual.toUpperCase());

										ls_llaveIntervinienteActual = lsb_llaveIntervinienteActual.toString();

										if(StringUtils.isValidString(ls_llaveIntervinienteActual))
											lmssi_solicitudIntervinienteActual.put(
											    ls_llaveIntervinienteActual, lsi_solicitudIntervinienteActual
											);
									}
								}
							}
						}

						if(CollectionUtils.isValidCollection(lmssi_solicitudIntervinienteActual))
						{
							boolean lb_validacionInterviniente;

							lb_validacionInterviniente = lmssi_solicitudIntervinienteActual.containsKey(
								    ls_llaveIntervinienteIngresado
								);

							if(!lb_validacionInterviniente || (al_etapaActual == EtapaCommon.ID_ETAPA_30))
							{
								acsi_intervinientesActuales.add(asi_intervinienteIngresado);
								lcsi_return = acsi_intervinientesActuales;
							}
							else
								throw new B2BException(ErrorKeys.INTERVINIENTE_YA_ENLISTADO);
						}
					}
				}
			}
		}

		return lcsi_return;
	}

	/**
	 * Método encargado de validar los datos enviados.
	 *
	 * @param ad_documento correspondiente al valor del tipo de objeto Documento
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void erroresDinamicosConsultaDetalleTurnos(Documento ad_documento)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ad_documento != null)
			{
				String ls_turno;

				ls_turno = ad_documento.getIdTurno();

				if(StringUtils.isValidString(ls_turno))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_turno);

					if(lt_turno != null)
					{
						String ls_solicitudTurno;

						ls_solicitudTurno = lt_turno.getIdSolicitud();

						if(StringUtils.isValidString(ls_solicitudTurno))
						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitudTurno);

							if(ls_solicitud != null)
							{
								String ls_nirTurno;
								String ls_nir;
								String ls_procesoPantalla;
								String ls_proceso;

								ls_proceso             = lt_turno.getIdProceso();
								ls_nirTurno            = ls_solicitud.getNir();
								ls_nir                 = ad_documento.getNir();
								ls_procesoPantalla     = ad_documento.getIdProcesoRecepcion();

								if(
								    StringUtils.isValidString(ls_nirTurno) && StringUtils.isValidString(ls_nir)
									    && !ls_nir.equalsIgnoreCase(ls_nirTurno)
								)
									throw new B2BException(ErrorKeys.ERROR_NIR_NO_CORRESPONDE_CON_TURNO);

								if(StringUtils.isValidString(ls_proceso))
								{
									ProcesoDAO lpd_DAO;
									Proceso    lp_proceso;

									lpd_DAO        = DaoCreator.getProcesoDAO(ldm_manager);
									lp_proceso     = lpd_DAO.findById(ls_proceso);

									if(lp_proceso != null)
									{
										String ls_idDocumentoSolicitud;

										ls_idDocumentoSolicitud = ls_solicitud.getIdDocumento();

										if(StringUtils.isValidString(ls_idDocumentoSolicitud))
										{
											Documento    ld_documento;
											DocumentoDAO ldd_DAO;

											ld_documento     = new Documento();
											ldd_DAO          = DaoCreator.getDocumentoDAO(ldm_manager);

											ld_documento.setIdDocumento(ls_idDocumentoSolicitud);
											ld_documento = ldd_DAO.findById(ld_documento);

											if(ld_documento != null)
											{
												String ls_idDocumento;
												ls_idDocumento = ld_documento.getIdDocumento();

												if(StringUtils.isValidString(ls_idDocumento))
												{
													Long ls_versionDocumentoSolicitud;
													ls_versionDocumentoSolicitud = ls_solicitud.getVersionDocumento();

													if(NumericUtils.isValidLong(ls_versionDocumentoSolicitud))
													{
														ld_documento.setVersionDocumento(ls_versionDocumentoSolicitud);
														ld_documento = ldd_DAO.findByIdDocumentoVersion(ld_documento);

														if(ld_documento != null)
														{
															String ls_idTipoDocumento;
															ls_idTipoDocumento = ld_documento.getIdTipoDocumento();

															if(StringUtils.isValidString(ls_idTipoDocumento))
															{
																TipoDocumentoPublico ltdp_tipoDocumentoPublico;

																ltdp_tipoDocumentoPublico = new TipoDocumentoPublico();

																ltdp_tipoDocumentoPublico.setIdTipoDocumento(
																    ls_idTipoDocumento
																);
																ltdp_tipoDocumentoPublico = DaoCreator.getTipoDocumentoPublicoDAO(
																	    ldm_manager
																	).findById(ltdp_tipoDocumentoPublico);

																if(ltdp_tipoDocumentoPublico == null)
																	throw new B2BException(
																	    ErrorKeys.ERROR_NO_EXISTE_TIPO_DOCUMENTO_PUBLICO_TURNO
																	);
																else
																{
																	String ls_circulo;
																	ls_circulo = lt_turno.getIdCirculo();

																	if(StringUtils.isValidString(ls_circulo))
																	{
																		Collection<SolicitudMatricula> lcsm_solicitudesMatriculas;

																		lcsm_solicitudesMatriculas = DaoCreator.getSolicitudMatriculaDAO(
																			    ldm_manager
																			)
																				                                   .findByIdSolicitudCirculo(
																				    ls_solicitudTurno, ls_circulo
																				);

																		if(
																		    CollectionUtils.isValidCollection(
																			        lcsm_solicitudesMatriculas
																			    )
																		)
																		{
																			{
																				String  ll_matricula;
																				boolean lb_encontro;

																				ll_matricula     = StringUtils.getString(
																					    ad_documento.getIdMatricula()
																					);
																				lb_encontro      = false;

																				for(SolicitudMatricula lsm_temp : lcsm_solicitudesMatriculas)
																				{
																					if(
																					    (lsm_temp != null)
																						    && StringUtils.isValidString(
																						        ll_matricula
																						    )
																						    && StringUtils.getString(
																						        lsm_temp.getIdMatricula()
																						    ).equals(ll_matricula)
																					)
																						lb_encontro = true;
																				}

																				if(
																				    NumericUtils.isValidLong(
																					        ad_documento.getIdMatricula()
																					    ) && !lb_encontro
																				)
																					throw new B2BException(
																					    ErrorKeys.ERROR_MATRICULA_INGRESADA_TURNO
																					);
																			}

																			{
																				Subproceso lsp_sp;

																				lsp_sp = DaoCreator.getSubprocesoDAO(
																					    ldm_manager
																					)
																						               .findById(
																						    ls_procesoPantalla,
																						    ls_proceso
																						);

																				if(lsp_sp == null)
																				{
																					Object[] loa_messageArgs    = new String[3];
																					Proceso  lp_procesoPantalla;

																					lp_procesoPantalla          = new Proceso();

																					lp_procesoPantalla.setIdProceso(
																					    ls_procesoPantalla
																					);
																					lp_procesoPantalla = lpd_DAO
																							.findById(
																							    lp_procesoPantalla
																							);

																					if(lp_procesoPantalla != null)
																					{
																						Proceso lp_procesoTurno;
																						lp_procesoTurno = new Proceso();

																						lp_procesoTurno.setIdProceso(
																						    lt_turno.getIdProceso()
																						);
																						lp_procesoTurno = lpd_DAO
																								.findById(
																								    lp_procesoTurno
																								);

																						if(lp_procesoTurno != null)
																						{
																							loa_messageArgs[0]     = ls_turno;
																							loa_messageArgs[1]     = StringUtils
																									.getStringLowerCase(
																									    lp_procesoTurno
																									    .getNombre()
																									);
																							loa_messageArgs[2]     = StringUtils
																									.getStringLowerCase(
																									    lp_procesoPantalla
																									    .getNombre()
																									);
																							throw new B2BException(
																							    ErrorKeys.TURNO_PROCESO_TIPO_TRAMITE_INVALIDO,
																							    loa_messageArgs
																							);
																						}
																					}
																				}
																				else
																				{
																					Proceso  lp_procesoPantalla;
																					Object[] loa_messageArgs = new String[1];

																					lp_procesoPantalla = new Proceso();

																					lp_procesoPantalla.setIdProceso(
																					    ls_procesoPantalla
																					);
																					lp_procesoPantalla = lpd_DAO
																							.findById(
																							    lp_procesoPantalla
																							);

																					if(
																					    (lp_procesoPantalla != null)
																						    && !ls_procesoPantalla
																						    .equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40)
																					)
																					{
																						loa_messageArgs[0] = StringUtils
																								.getStringLowerCase(
																								    lp_procesoPantalla
																								    .getNombre()
																								);
																						throw new B2BException(
																						    ErrorKeys.ERROR_TURNO_EN_ETAPAS_INVALIDAS,
																						    loa_messageArgs
																						);
																					}
																				}
																			}
																		}
																		else
																			throw new B2BException(
																			    ErrorKeys.ERROR_TURNO_SIN_MATRICULAS
																			);
																	}
																	else
																		throw new B2BException(
																		    ErrorKeys.ERROR_TURNO_SIN_CIRCULO
																		);
																}
															}
														}
														else
															throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_DOCUMENTO);
													}
													else
														throw new B2BException(
														    ErrorKeys.ERROR_NO_EXISTE_VERSION_DE_DOCUMENTO
														);
												}
												else
													throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SIN_ID);
											}
											else
												throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_DOCUMENTO);
										}
										else
										{
											if(ls_procesoPantalla.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41))
												throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
											else
												throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_DOCUMENTO);
										}
									}
									else
										throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_PROCESO);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_PROCESO);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_SOLICITUD);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("erroresDinamicosConsultaDetalleTurnos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de consultar el área de un predio temporal.
	 *
	 * @param aaap_accAreaPredio Objeto que contiene la información del área a consultar.
	 * @return Objeto que contiene el área consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	public synchronized AccAreaPredio findAccAreaPredio(AccAreaPredio aaap_accAreaPredio)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		AccAreaPredio laap_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		laap_return     = null;

		try
		{
			if(aaap_accAreaPredio != null)
				laap_return = DaoCreator.getAccAreaPredioDAO(ldm_manager).findByCirculoMatricula(aaap_accAreaPredio);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAccAreaPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laap_return;
	}

	/**
	 * Método encargado de consultar el acto por la llave enviada.
	 *
	 * @param aa_acto correspondiente al valor del tipo de objeto Acto
	 * @return devuelve el valor de Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public synchronized Acto findActoBy(Acto aa_acto)
	    throws B2BException
	{
		Acto       la_return;
		DAOManager ldm_manager;

		la_return       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_acto != null)
				la_return = DaoCreator.getActoDAO(ldm_manager).findById(aa_acto);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActoBy", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return la_return;
	}

	/**
	 * Método encargado de consultar el acto por la llave enviada para el proceso de englobes.
	 *
	 * @param aa_acto correspondiente al valor del tipo de objeto Acto
	 * @param ahmss_codigos correspondiente al valor del tipo de objeto HashMap<String,String>
	 * @return devuelve el valor de Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public synchronized Acto findActoEnglobes(Acto aa_acto, Map<String, String> ahmss_codigos)
	    throws B2BException
	{
		Acto       la_return;
		DAOManager ldm_manager;

		la_return       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((aa_acto != null) && CollectionUtils.isValidCollection(ahmss_codigos))
			{
				ActoDAO la_DAO;

				la_DAO = DaoCreator.getActoDAO(ldm_manager);

				for(Map.Entry<String, String> iterador : ahmss_codigos.entrySet())
				{
					if(iterador != null)
					{
						aa_acto.setIdTipoActo(iterador.getKey());

						la_return = la_DAO.findBySolicitudIdCirculoTipoActo(aa_acto);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActoEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return la_return;
	}

	/**
	 * Método encargado de consultar los actos por la el id_circulo y id_solicitud enviados.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto Acto
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param ab_validarEstado correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<TipoActo> findActosById(
	    Acto ap_parametros, String as_userId, boolean ab_accion, boolean ab_validarEstado
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoActo> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_datos       = null;

		try
		{
			if(ap_parametros != null)
			{
				boolean                         lb_actosSegregacion;
				String                          ls_idSolicitud;
				StringBuilder                   lsb_sentencia;
				Map<Integer, Object>            llhm_criterios;
				Collection<Map<String, Object>> lll_response;

				lb_actosSegregacion     = false;
				llhm_criterios          = new LinkedHashMap<Integer, Object>();
				ls_idSolicitud          = ap_parametros.getIdSolicitud();
				lsb_sentencia           = new StringBuilder();

				lsb_sentencia.append(ConsultasUtilidades.CS_NATURALEZA_ACTO);

				if(ab_accion)
					lsb_sentencia.append(" AND A.ID_CIRCULO = ? ");

				lsb_sentencia.append(" ORDER BY A.ID_CIRCULO ASC, A.ID_ACTO ASC");

				if(!StringUtils.isValidString(ls_idSolicitud))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

				llhm_criterios.put(new Integer(1), ls_idSolicitud);

				if(ab_accion)
					llhm_criterios.put(new Integer(2), ap_parametros.getIdCirculo());

				lll_response = DaoCreator.getUtilDAO(ldm_manager)
						                     .getCustonQuery(lsb_sentencia.toString(), llhm_criterios);

				if(lll_response != null)
				{
					lcp_datos = new ArrayList<TipoActo>();

					for(Map<String, Object> llhm_actual : lll_response)
					{
						if(llhm_actual != null)
						{
							TipoActo la_tmp;

							la_tmp = new TipoActo();

							{
								Object lo_object;
								lo_object = llhm_actual.get("ID_TIPO_ACTO");

								if(lo_object != null)
									la_tmp.setIdTipoActo(lo_object.toString());
							}

							{
								Object lo_object;
								lo_object = llhm_actual.get("IND_MAYOR_VALOR");

								if(lo_object != null)
									la_tmp.setIndMayorValor(lo_object.toString());
							}

							{
								Object lo_object;
								lo_object = llhm_actual.get("NOMBRE");

								if(lo_object != null)
									la_tmp.setNombre(lo_object.toString());
							}

							{
								Object lo_object;
								lo_object = llhm_actual.get("CUANTIA");

								if(lo_object != null)
									la_tmp.setIdTipoCalculo(lo_object.toString());
							}

							{
								Object lo_object;
								lo_object = llhm_actual.get("ID_ACTO");

								if(lo_object != null)
									la_tmp.setIdUsuarioCreacion(lo_object.toString());
							}

							{
								Object lo_object;
								lo_object = llhm_actual.get("VALIDAR_AREA");

								if(lo_object != null)
								{
									if(lo_object.toString().equalsIgnoreCase(EstadoCommon.S))
										la_tmp.setValidarArea(true);
									else
										la_tmp.setValidarArea(false);
								}
							}

							{
								Object lo_object;
								lo_object = llhm_actual.get("ID_CIRCULO");

								if(lo_object != null)
									la_tmp.setIdCirculo(lo_object.toString());
							}

							{
								Object lo_object;
								Object lo_object1;
								Object lo_object2;

								lo_object      = llhm_actual.get("APERTURA_MATRICULA");
								lo_object1     = llhm_actual.get("INSCRIPCION_ADICIONAL");
								lo_object2     = llhm_actual.get("GENERA_SEGREGACION");

								if((lo_object != null) && (lo_object1 != null) && (lo_object2 != null))
								{
									if(!lb_actosSegregacion)
									{
										if(
										    lo_object.toString().equalsIgnoreCase(EstadoCommon.S)
											    || lo_object1.toString().equalsIgnoreCase(EstadoCommon.S)
											    || lo_object2.toString().equalsIgnoreCase(EstadoCommon.S)
										)
											lb_actosSegregacion = true;
									}
								}
							}

							la_tmp.setActoSegregacion(lb_actosSegregacion);

							{
								Object lo_object;
								lo_object = llhm_actual.get("CERTIFICADO_OBLIGATORIO");

								if(lo_object != null)
								{
									String ls_string;
									ls_string = lo_object.toString();

									la_tmp.setCertificadoObligatorio(
									    StringUtils.isValidString(ls_string)
										    && ls_string.equalsIgnoreCase(EstadoCommon.S)
									);
								}
							}

							if(ab_validarEstado)
							{
								boolean            li_validacionEstado;
								Acto               la_acto;
								Collection<Acto>   lca_coleccionActos;
								Collection<String> lcs_actosInhabilitados;

								li_validacionEstado        = true;
								la_acto                    = new Acto();
								lca_coleccionActos         = new LinkedList<Acto>();
								lcs_actosInhabilitados     = new LinkedList<String>();

								if(ap_parametros.getIdSolicitud() != null)
									la_acto.setIdSolicitud(ap_parametros.getIdSolicitud());

								lca_coleccionActos = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitud(la_acto);

								if(CollectionUtils.isValidCollection(lca_coleccionActos))
								{
									SolicitudMatriculaActoDAO lsmaDAO_sma;
									lsmaDAO_sma = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);

									for(Acto la_datos : lca_coleccionActos)
									{
										if(la_datos != null)
										{
											String                 ls_idActo;
											SolicitudMatriculaActo lsma_SolMatActo;

											ls_idActo           = la_datos.getIdActo();
											lsma_SolMatActo     = new SolicitudMatriculaActo();

											lsma_SolMatActo.setIdActo(ls_idActo);

											lsma_SolMatActo = lsmaDAO_sma.findByIdActo(lsma_SolMatActo);

											if(lsma_SolMatActo != null)
											{
												String ls_estado;
												ls_estado = lsma_SolMatActo.getEstado();

												if(
												    StringUtils.isValidString(ls_estado)
													    && ls_estado.equalsIgnoreCase("I")
												)
													lcs_actosInhabilitados.add(ls_idActo);
											}
										}
									}
								}

								for(String ls_acto : lcs_actosInhabilitados)
								{
									String ls_idUsuario;
									ls_idUsuario = la_tmp.getIdUsuarioCreacion();

									if(
									    StringUtils.isValidString(ls_idUsuario)
										    && ls_idUsuario.equalsIgnoreCase(ls_acto)
									)
									{
										li_validacionEstado = false;

										break;
									}
								}

								if(li_validacionEstado || ap_parametros.isVieneDeAntiguoSistema())
									lcp_datos.add(la_tmp);
							}
							else
								lcp_datos.add(la_tmp);
						}
					}
				}

				if(!CollectionUtils.isValidCollection(lcp_datos))
					lcp_datos = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActosById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Método encargado de consultar los actos por la el id_solicitud enviados.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosBySolicitud(
	    String as_idSolicitud
	)
	    throws B2BException
	{
		DAOManager                                              ldm_manager;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
				ldp_datos = DaoCreator.getTipoActoDAO(ldm_manager).findActosBySolicitud(as_idSolicitud);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActosBySolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar actos con base a la solicitud y el circulo del turno.
	 *
	 * @param as_idSolicitud Variable de contiene el id de la solicitud
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @return Colección que contiene los datos obtenidos de la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosBySolicitudCirculoTurno(
	    String as_idSolicitud, String as_idTurno
	)
	    throws B2BException
	{
		DAOManager                                              ldm_manager;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idTurno))
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

				if(lt_turno != null)
					ldp_datos = DaoCreator.getTipoActoDAO(ldm_manager)
							                  .findActosBySolicitud(as_idSolicitud, lt_turno.getIdCirculo());
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActosBySolicitudCirculoTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Metodo encargado de consultar los actos de un ID_SOLICITUD o ID_CIRCULO.
	 *
	 * @param as_idSolicitud Argumento de tipo String que contiene el ID_SOLICITUD a consultar.
	 * @param as_idCirculo Argumento de tipo String que contiene el ID_CIRCULO a consultar.
	 * @return Collección de Actos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosBySolicitudIdCirculo(
	    String as_idSolicitud, String as_idCirculo
	)
	    throws B2BException
	{
		DAOManager                                              ldm_manager;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
				ldp_datos = DaoCreator.getTipoActoDAO(ldm_manager).findActosBySolicitud(as_idSolicitud, as_idCirculo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActosBySolicitudIdCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar los actos hijos por la el id_acto padre enviado.
	 *
	 * @param aa_acto correspondiente al valor del tipo de objeto Acto
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosHijosById(
	    com.bachue.snr.prosnr01.model.registro.Acto aa_acto
	)
	    throws B2BException
	{
		DAOManager                                              ldm_manager;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lca_datos       = null;

		try
		{
			if(aa_acto != null)
				lca_datos = DaoCreator.getActoDAO(ldm_manager).findHijosById(aa_acto);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActosHijosById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_datos;
	}

	/**
	 * Método encargado de consultar los actos que requieren validar area del terreno mediante constante.
	 *
	 * @return devuelve el valor de LinkedHashMap
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Map<String, Boolean> findActosValidarAreaTerreno()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Map<String, Boolean> llhm_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		llhm_return     = new LinkedHashMap<String, Boolean>();

		try
		{
			Constantes    lc_constante;
			ConstantesDAO lc_DAO;

			lc_constante     = new Constantes();
			lc_DAO           = DaoCreator.getConstantesDAO(ldm_manager);

			lc_constante.setIdConstante(ConstanteCommon.CODIGOS_VALIDAR_AREAS_TERRENO);

			lc_constante = lc_DAO.findById(lc_constante);

			if(lc_constante != null)
			{
				String ls_caracter;

				ls_caracter = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracter))
				{
					int      li_tam;
					String[] lsa_codigos;

					lsa_codigos     = ls_caracter.split(",");
					li_tam          = lsa_codigos.length;

					if(li_tam > 0)
					{
						for(int li_count = 0; li_count < li_tam; li_count++)
						{
							String ls_codigo;

							ls_codigo = lsa_codigos[li_count];

							if(StringUtils.isValidString(ls_codigo))
								llhm_return.put(ls_codigo, Boolean.TRUE);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActosValidarAreaTerreno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(llhm_return.isEmpty())
			llhm_return = null;

		return llhm_return;
	}

	/**
	 * Método encargado de consultar las causales correción por la llave id_solicitud, id_circulo y id_matricula.
	 *
	 * @param asc_solicitudCorreccion correspondiente al valor del tipo de objeto SolicitudCorreccion
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<CausalCorreccion> findAllCausales(SolicitudCorreccion asc_solicitudCorreccion)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CausalCorreccion> lccc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccc_datos      = null;

		try
		{
			if(asc_solicitudCorreccion != null)
			{
				CausalCorreccionDAO    lcc_DAO;
				SolicitudCorreccionDAO lsc_DAO;

				lcc_DAO        = DaoCreator.getCausalCorreccionDAO(ldm_manager);
				lsc_DAO        = DaoCreator.getSolicitudCorreccionDAO(ldm_manager);
				lccc_datos     = lcc_DAO.findAllActivo();

				if(CollectionUtils.isValidCollection(lccc_datos))
				{
					Map<BigInteger, SolicitudCorreccion> lcsc_solicitudCorreccion;

					lcsc_solicitudCorreccion = lsc_DAO.findCheckBySolicitudCirculoMatricula(asc_solicitudCorreccion);

					if(CollectionUtils.isValidCollection(lcsc_solicitudCorreccion))
					{
						for(CausalCorreccion lcc_cc : lccc_datos)
						{
							BigInteger lbi_idCausal;

							lbi_idCausal = lcc_cc.getIdCausalCorreccion();

							if(
							    NumericUtils.isValidBigInteger(lbi_idCausal)
								    && lcsc_solicitudCorreccion.containsKey(lbi_idCausal)
							)
							{
								SolicitudCorreccion lsc_correccion;

								lsc_correccion = lcsc_solicitudCorreccion.get(lbi_idCausal);

								if(lsc_correccion != null)
								{
									String ls_activo;

									ls_activo = lsc_correccion.getSeleccionado();

									if(
									    StringUtils.isValidString(ls_activo)
										    && ls_activo.equalsIgnoreCase(EstadoCommon.S)
									)
									{
										lcc_cc.setSeleccionado(true);
										lcc_cc.setObservaciones(lsc_correccion.getObservacion());
									}
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_FIND_CAUSAL_CORRECCION_ACTIVO);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_datos;
	}

	/**
	 * Método encargado de consultar los tipos documentales por la llave id_solicitud, id_tipo_acto y id_acto enviados.
	 *
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @param as_codigo correspondiente al valor del tipo de objeto String
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<TipoDocumental> findAllDocumentales(
	    String as_idActo, String as_codigo, boolean ab_b, String as_idSolicitud
	)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<TipoDocumental> lctd_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctd_datos      = null;

		try
		{
			Collection<TipoDocumental> lctd_completitud;
			boolean                    lb_actos;

			lctd_completitud     = DaoCreator.getTipoActoDAO(ldm_manager)
					                             .documentalesCompletitud(as_idActo, as_codigo, as_idSolicitud, true);
			lb_actos             = false;

			if(CollectionUtils.isValidCollection(lctd_completitud))
			{
				Collection<TipoDocumental> lctd_docActo;

				lctd_docActo = DaoCreator.getTipoActoDAO(ldm_manager)
						                     .documentalesCompletitud(as_idActo, as_codigo, as_idSolicitud, false);

				if(CollectionUtils.isValidCollection(lctd_docActo))
				{
					Iterator<TipoDocumental> litd_iteradorActo;

					litd_iteradorActo = lctd_docActo.iterator();

					while(litd_iteradorActo.hasNext() && !lb_actos)
					{
						TipoDocumental ltd_tipoDocActo;

						ltd_tipoDocActo = litd_iteradorActo.next();

						if(ltd_tipoDocActo != null)
						{
							Iterator<TipoDocumental> litd_iteradorCompl;

							litd_iteradorCompl = lctd_completitud.iterator();

							while(litd_iteradorCompl.hasNext() && !lb_actos)
							{
								TipoDocumental ltd_tipoDocCompl;

								ltd_tipoDocCompl = litd_iteradorCompl.next();

								if((ltd_tipoDocCompl != null))
								{
									String ls_idTipoDocActo;
									String ls_idTipoDocCompl;

									ls_idTipoDocActo      = StringUtils.getStringNotNull(
										    ltd_tipoDocActo.getIdTipoDocumental()
										);
									ls_idTipoDocCompl     = StringUtils.getStringNotNull(
										    ltd_tipoDocCompl.getIdTipoDocumental()
										);

									if(ls_idTipoDocActo.equalsIgnoreCase(ls_idTipoDocCompl))
										lb_actos = true;
								}
							}
						}
					}
				}
				else
					lb_actos = true;
			}

			lctd_datos = DaoCreator.getTipoActoDAO(ldm_manager)
					                   .findAllDocumentales(as_idActo, as_codigo, ab_b, as_idSolicitud, lb_actos);

			if(!lb_actos && CollectionUtils.isValidCollection(lctd_datos))
			{
				Iterator<TipoDocumental> litd_iteradorActo;

				litd_iteradorActo = lctd_datos.iterator();

				while(litd_iteradorActo.hasNext() && !lb_actos)
				{
					TipoDocumental ltd_tipoDocActo;

					ltd_tipoDocActo = litd_iteradorActo.next();

					if(ltd_tipoDocActo != null)
					{
						String ls_idTipoDoc;
						ls_idTipoDoc = ltd_tipoDocActo.getIdTipoDocumental();

						if(StringUtils.isValidString(ls_idTipoDoc))
						{
							if(ls_idTipoDoc.equalsIgnoreCase(TipoDocumentalCommon.BOLETA_FISCAL))
							{
								Acto la_acto;
								la_acto = DaoCreator.getActoDAO(ldm_manager).findByIdActoImpuestoRegistro(as_idActo);

								if(la_acto != null)
								{
									String ls_boletaFiscal;
									Date   ld_fechaPago;

									ls_boletaFiscal     = la_acto.getNumeroBoletaFiscal();
									ld_fechaPago        = la_acto.getFechaPagoImpuesto();

									if(!StringUtils.isValidString(ls_boletaFiscal) || (ld_fechaPago == null))
										ltd_tipoDocActo.setObservaciones(EstadoCommon.NO_APORTO_RECIBO_PAGO);

									ltd_tipoDocActo.setSeleccionado(true);
									ltd_tipoDocActo.setEsBoletaFiscal(true);
								}
							}
							else if(ls_idTipoDoc.equalsIgnoreCase(TipoDocumentalCommon.BOLETA_FISCAL_EXTEMPORANEA))
							{
								Solicitud ls_solicitud;
								Acto      la_acto;

								la_acto          = DaoCreator.getActoDAO(ldm_manager)
										                         .findByIdActoImpuestoRegistro(as_idActo);
								ls_solicitud     = new Solicitud();
								ls_solicitud.setIdSolicitud(as_idSolicitud);

								ls_solicitud = recibosImpuesto(ls_solicitud);

								if((ls_solicitud != null) && ls_solicitud.isReciboImpuestos() && (la_acto != null))
								{
									String ls_boletaFiscal;
									Date   ld_fechaPago;

									ls_boletaFiscal     = la_acto.getNumeroBoletaFiscal();
									ld_fechaPago        = la_acto.getFechaPagoImpuesto();

									if(!StringUtils.isValidString(ls_boletaFiscal) || (ld_fechaPago == null))
										ltd_tipoDocActo.setObservaciones(EstadoCommon.NO_APORTO_RECIBO_PAGO);

									ltd_tipoDocActo.setSeleccionado(true);
									ltd_tipoDocActo.setEsBoletaFiscal(true);
								}
							}
							else if(ls_idTipoDoc.equalsIgnoreCase(TipoDocumentalCommon.ORDEN_PROHIBICION_JUDICIAL))
								ltd_tipoDocActo.setSeleccionado(true);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllDocumentales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctd_datos;
	}

	/**
	 * Consulta todos los registros de anotación prohibición asociados a un id círculo e id matrícula.
	 *
	 * @param as_s Id de la solicitud asociada a los registros
	 * @param as_idCirculo id del círculo asociado a los registros
	 * @param al_idMatricula id de la matrícula asociado a los registros
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp dirección IP del cliente que ejecuta la acción
	 * @return Colección resultante de la consulta
	 * @throws B2BException Si ocurre un error en la conexión o consulta en base de datos
	 */
	public synchronized Collection<RegistroAnotacionProhibicion> findAllRegistroAnotacionProhibicionByCirMat(
	    Solicitud as_s, String as_idCirculo, long al_idMatricula, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                               ldm_manager;
		Collection<RegistroAnotacionProhibicion> lcrap_registros;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			lcrap_registros = DaoCreator.getRegistroAnotacionProhibicionDao(ldm_manager)
					                        .findAllByCirculoMatricula(as_idCirculo, al_idMatricula, true);

			if(CollectionUtils.isValidCollection(lcrap_registros))
			{
				for(RegistroAnotacionProhibicion lrap_data : lcrap_registros)
				{
					if(lrap_data != null)
						findRegistroAnotacionProhibicionByCirculoMatricula(
						    as_s, lrap_data, as_userId, as_remoteIp, ldm_manager
						);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRegistroAnotacionProhibicionByCirMat", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrap_registros;
	}

	/**
	 * Método encargado de consultar las zonas registrales.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<ZonaRegistral> findAllZonaRegistral()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<ZonaRegistral> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getZonaRegistralDAO(ldm_manager).findAll(false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllZonaRegistral", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar la bandeja de reproduccion de constancia por un TIPO_DOCUMENTO, NUMERO_DOCUMENTO Y FECHA_DOCUMENTO.
	 *
	 * @param asi_parametros Objeto contenedor de parametros necesarios para la consulta
	 * @param adc_documento Objeto contenedor de parametros necesarios para la consulta
	 * @return información necesaria para visualizar en labandeja de reproducción de constancia
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<AnotacionPredioDireccion> findAnotacionPredioByRadicacion(
	    AnotacionPredioDireccion asi_parametros, DocumentoConstancia adc_documento
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<AnotacionPredioDireccion> lcsi_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcsi_datos      = null;

		try
		{
			if((asi_parametros != null) && (adc_documento != null))
				lcsi_datos = DaoCreator.getAnotacionPredioDAO(ldm_manager)
						                   .findByRadicacion(asi_parametros, adc_documento);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAnotacionPredioByRadicacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsi_datos;
	}

	/**
	 * Método encargado de consultar la bandeja de reproduccion de constancia por un ID_TURNO.
	 *
	 * @param aapd_parametros Objeto contenedor de parametros necesarios para la consulta
	 * @return información necesaria para visualizar en labandeja de reproducción de constancia
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<DataReproduccionConstancia> findAnotacionPredioByRadicacion(
	    AnotacionPredioDireccion aapd_parametros
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		Collection<DataReproduccionConstancia> lcdrc_return;

		lcdrc_return = new ArrayList<DataReproduccionConstancia>();

		try
		{
			AnotacionPredioDireccion             lapd_temp;
			Collection<AnotacionPredioDireccion> lcsi_datos;
			DataReproduccionConstancia           ldrc_datarepCons;
			Collection<AnotacionPredioDireccion> lcap_colFinal;
			String                               ls_circuloTMP;
			lcap_colFinal     = new ArrayList<AnotacionPredioDireccion>();

			lcsi_datos           = null;
			lapd_temp            = null;
			ldrc_datarepCons     = new DataReproduccionConstancia();
			ls_circuloTMP        = null;

			if((aapd_parametros != null))
			{
				ls_circuloTMP = aapd_parametros.getIdCirculo();
				aapd_parametros.setIdCirculo(null);
				lcsi_datos = DaoCreator.getAnotacionPredioDAO(ldm_manager).findByRadicacion(aapd_parametros, null);
			}

			if(CollectionUtils.isValidCollection(lcsi_datos))
			{
				aapd_parametros.setIdCirculo(ls_circuloTMP);
				lcsi_datos = DaoCreator.getAnotacionPredioDAO(ldm_manager).findByRadicacion(aapd_parametros, null);

				if(CollectionUtils.isValidCollection(lcsi_datos))
				{
					String ls_anotaciones;

					ls_anotaciones = null;

					for(AnotacionPredioDireccion lap_iterador : lcsi_datos)
					{
						if(lap_iterador != null)
						{
							String ls_circulo;
							long   ll_matricula;

							ll_matricula     = lap_iterador.getIdMatricula();
							ls_circulo       = lap_iterador.getIdCirculo();

							if(!StringUtils.isValidString(ls_circulo) || (ll_matricula <= 0))
							{
								Object[] aoa_messageArgs = new String[1];
								aoa_messageArgs[0] = ls_circulo + "-" + ll_matricula;
								throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
							}

							DireccionPredio ldp_direccionPredio;

							ldp_direccionPredio = new DireccionPredio();

							ldp_direccionPredio.setIdCirculo(ls_circulo);
							ldp_direccionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_matricula));

							ldp_direccionPredio = DaoCreator.getDireccionPredioDAO(ldm_manager)
									                            .findById(ldp_direccionPredio);

							if(ldp_direccionPredio != null)
								lap_iterador.setDireccionPredio(ldp_direccionPredio.getDireccion());

							if(lapd_temp == null)
							{
								lapd_temp          = lap_iterador;
								ls_anotaciones     = String.valueOf(lapd_temp.getIdAnotacion());
							}
							else if(lapd_temp.getIdMatricula() == lap_iterador.getIdMatricula())
								ls_anotaciones = ls_anotaciones + "-" + lap_iterador.getIdAnotacion();
							else if(
							    (lapd_temp.getIdMatricula() != lap_iterador.getIdMatricula())
								    || !lapd_temp.getIdDocumento().equals(lap_iterador.getIdDocumento())
							)
							{
								lapd_temp.setAnotaciones(ls_anotaciones);
								lcap_colFinal.add(lapd_temp);
								ls_anotaciones     = String.valueOf(lap_iterador.getIdAnotacion());
								lapd_temp          = lap_iterador;
							}
							else if(lapd_temp.getIdDocumento().equals(lap_iterador.getIdDocumento()))
								ls_anotaciones = ls_anotaciones + "-" + lap_iterador.getIdAnotacion();

							{
								DocumentoConstancia ld_documento;

								ld_documento = new DocumentoConstancia();

								ld_documento.setIdDocumento(lap_iterador.getIdDocumento());
								ld_documento.setVersionDocumento(
								    NumericUtils.getLongWrapper(lap_iterador.getVersionDocumento())
								);

								ld_documento = DaoCreator.getDocumentoDAO(ldm_manager)
										                     .consultaMaxDocConstanciaById(ld_documento);

								if(ld_documento != null)
									ldrc_datarepCons.setDocumento(ld_documento);
							}
						}

						lapd_temp.setSeleccionado(true);
					}

					if(lapd_temp != null)
					{
						lapd_temp.setAnotaciones(ls_anotaciones);
						lcap_colFinal.add(lapd_temp);
						ls_anotaciones     = null;
						lapd_temp          = null;
					}

					ldrc_datarepCons.setAnotacionPredioDireccion(lcap_colFinal);

					lcdrc_return.add(ldrc_datarepCons);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CIRCULO_REGISTRAL_NO_COINCIDE_CON_EL_TURNO);
			}
			else
			{
				Turno lt_turno;
				lt_turno = new Turno();
				lt_turno.setIdTurno(aapd_parametros.getRadicacion());
				lt_turno.setIdCirculo(ls_circuloTMP);

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findByIdCirculo(lt_turno);

				if(lt_turno != null)
				{
					String ls_idProceso;
					String ls_idSubproceso;
					ls_idProceso        = lt_turno.getIdProceso();
					ls_idSubproceso     = lt_turno.getIdSubProceso();

					if(StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubproceso))
					{
						if(ls_idProceso.equals("6") && ls_idSubproceso.equals("6"))
						{
							Testamento lt_testamento;
							lt_testamento = new Testamento();
							lt_testamento.setIdTurno(lt_turno.getIdTurno());
							lt_testamento = DaoCreator.getTestamentoDAO(ldm_manager).findByTurno(lt_testamento);

							if(lt_testamento != null)
							{
								Solicitud ls_solicitud;
								ls_solicitud     = null;
								ls_solicitud     = DaoCreator.getSolicitudDAO(ldm_manager)
										                         .findByIdTestamento(lt_testamento.getIdTestamento());

								if(ls_solicitud != null)
								{
									Documento ld_documento;

									ld_documento = new Documento();

									ld_documento.setIdDocumento(ls_solicitud.getIdDocumento());

									Long ll_versionDocumento;

									ll_versionDocumento = DaoCreator.getDocumentoDAO(ldm_manager)
											                            .findMaxVersionByIdDocumento(ld_documento);

									if(NumericUtils.isValidLong(ll_versionDocumento))
									{
										{
											DocumentoConstancia ld_documentoConstancia;

											ld_documentoConstancia = new DocumentoConstancia();

											ld_documentoConstancia.setIdDocumento(ld_documento.getIdDocumento());
											ld_documentoConstancia.setVersionDocumento(ll_versionDocumento);

											ld_documentoConstancia = DaoCreator.getDocumentoDAO(ldm_manager)
													                               .consultaMaxDocConstanciaById(
													    ld_documentoConstancia
													);

											if(ld_documentoConstancia != null)
												ldrc_datarepCons.setDocumento(ld_documentoConstancia);
										}
									}
								}

								{
									TipoTestamento ltt_tipoTestamento;
									ltt_tipoTestamento = new TipoTestamento();
									ltt_tipoTestamento.setIdTipoTestamento(lt_testamento.getIdTipoTestamento());
									ltt_tipoTestamento = DaoCreator.getTipoTestamentoDAO(ldm_manager)
											                           .findById(ltt_tipoTestamento);

									if(ltt_tipoTestamento != null)
									{
										String ls_nombreTipoTestamento;
										ls_nombreTipoTestamento = ltt_tipoTestamento.getNombre();

										if(StringUtils.isValidString(ls_nombreTipoTestamento))
											lt_testamento.setNombreTipoTestamento(ls_nombreTipoTestamento);

										lapd_temp = new AnotacionPredioDireccion();
										lapd_temp.setTestamento(lt_testamento);
										lapd_temp.setIdCirculo(ls_circuloTMP);
										lapd_temp.setRadicacion(lt_turno.getIdTurno());
										lcap_colFinal.add(lapd_temp);
										lapd_temp.setSeleccionado(true);
										ldrc_datarepCons.setAnotacionPredioDireccion(lcap_colFinal);
									}
								}
							}
						}

						lcdrc_return.add(ldrc_datarepCons);
					}
				}
				else
				{
					Turno lt_turnoTmp;
					lt_turnoTmp = new Turno();
					lt_turnoTmp.setIdTurno(aapd_parametros.getRadicacion());
					lt_turnoTmp = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turnoTmp);

					Object[] aoa_messageArgs = new String[1];
					aoa_messageArgs[0] = aapd_parametros.getRadicacion();
					throw new B2BException(ErrorKeys.DEBE_REPRODUCCION_CONSTANCIA_TURNO, aoa_messageArgs);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAnotacionPredioByRadicacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdrc_return;
	}

	/**
	 * Método encargado de consultar datos de antiguo sistema por la solicitud enviada.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de DatosAntSistema
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	public synchronized DatosAntSistema findAntSistema(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		DatosAntSistema ldas_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldas_datos      = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
				ldas_datos = DaoCreator.getAntiguoSistemaDAO(ldm_manager).findAntSistema(as_idSolicitud);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldas_datos;
	}

	/**
	 * Método encargado de consultar el area del predio para un id_circulo y id_matricula enviado.
	 *
	 * @param aap_areaPredio correspondiente al valor del tipo de objeto AreaPredio
	 * @return devuelve el valor de AreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AreaPredio
	 */
	public synchronized AreaPredio findAreaPredio(AreaPredio aap_areaPredio)
	    throws B2BException
	{
		DAOManager ldm_manager;
		AreaPredio lap_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lap_return      = null;

		try
		{
			lap_return = DaoCreator.getAreaPredioDAO(ldm_manager).findById(aap_areaPredio);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAreaPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lap_return;
	}

	/**
	 * Método encargado de consultar el area del terreno para una matricula concatenada enviada (AAA-BBBBBBB).
	 *
	 * @param as_matricula correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de double
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized double findAreaTerrenoByMatricula(String as_matricula)
	    throws B2BException
	{
		DAOManager ldm_manager;
		double     ld_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ld_return       = 0.0;

		try
		{
			if(StringUtils.isValidString(as_matricula))
			{
				String[] lsa_matricula;

				lsa_matricula = as_matricula.split("-");

				if(lsa_matricula.length > 1)
				{
					AreaPredio lap_areaPredio;
					long       ll_idMarticula;
					String     ls_idCirculo;

					lap_areaPredio     = new AreaPredio();
					ll_idMarticula     = NumericUtils.getLong(lsa_matricula[1]);
					ls_idCirculo       = lsa_matricula[0];

					if((ll_idMarticula > 0.0) && StringUtils.isValidString(ls_idCirculo))
					{
						AreaPredioDAO lap_DAO;

						lap_DAO = DaoCreator.getAreaPredioDAO(ldm_manager);

						lap_areaPredio.setIdMatricula(ll_idMarticula);
						lap_areaPredio.setIdCirculo(ls_idCirculo);

						lap_areaPredio = lap_DAO.findById(lap_areaPredio);

						if(lap_areaPredio != null)
						{
							DetalleAreaPredio ldap_detalle;

							ldap_detalle = new DetalleAreaPredio();

							ldap_detalle.setIdAreaPredio(StringUtils.getString(lap_areaPredio.getIdArea()));
							ldap_detalle.setIdCirculo(ls_idCirculo);
							ldap_detalle.setIdMatricula(NumericUtils.getLongWrapper(ll_idMarticula));
							ldap_detalle.setIdTipoArea(TipoAreaCommon.TERRENO);

							ldap_detalle = DaoCreator.getDetalleAreaPredioDAO(ldm_manager)
									                     .findByIdAreaPredioTipo(ldap_detalle);

							if(ldap_detalle != null)
								ld_return = NumericUtils.getDouble(ldap_detalle.getArea());
						}
						else
						{
							AccAreaPredio    laap_areaPredio;
							AccAreaPredioDAO laap_DAO;

							laap_areaPredio     = new AccAreaPredio();
							laap_DAO            = DaoCreator.getAccAreaPredioDAO(ldm_manager);

							laap_areaPredio.setIdCirculo(ls_idCirculo);
							laap_areaPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMarticula));

							laap_areaPredio = laap_DAO.findByCirculoMatricula(laap_areaPredio);

							if(laap_areaPredio != null)
							{
								DetalleAreaPredio ldap_detalle;

								ldap_detalle = new DetalleAreaPredio();

								ldap_detalle.setIdAreaPredio(StringUtils.getString(laap_areaPredio.getIdArea()));
								ldap_detalle.setIdCirculo(ls_idCirculo);
								ldap_detalle.setIdMatricula(NumericUtils.getLongWrapper(ll_idMarticula));
								ldap_detalle.setIdTipoArea(TipoAreaCommon.TERRENO);

								ldap_detalle = DaoCreator.getAccDetalleAreaPredioDAO(ldm_manager)
										                     .findByIdAreaPredioTipo(ldap_detalle);

								if(ldap_detalle != null)
									ld_return = NumericUtils.getDouble(ldap_detalle.getArea());
							}
							else
							{
								Object[] loa_messageArgs = new String[1];
								loa_messageArgs[0] = as_matricula;
								throw new B2BException(ErrorKeys.ERROR_DESCRIPCION_AREA, loa_messageArgs);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAreaTerrenoByMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ld_return;
	}

	/**
	 * Metodo encargado de consultar los datosantsistema con una matricula.
	 *
	 * @param adas_datos correspondiente al valor del tipo de objeto DatosAntSistema
	 * @return Collección de Actos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<DatosAntSistema> findByIdMatriculaGrabacion(DatosAntSistema adas_datos)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<DatosAntSistema> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			if(adas_datos != null)
				ldp_datos = DaoCreator.getDatosAntSistemaDAO(ldm_manager).findByIdMatriculaGrabacion(adas_datos);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByIdMatriculaGrabacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar la zona registral del ultimo acto insertado para el id_solicitud enviado.
	 *
	 * @param as_solicitud correspondiente al valor del tipo de objeto Solicitud
	 * @return devuelve el valor de ZonaRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public synchronized ZonaRegistral findCirculoRegistralActoAntiguoSistema(Solicitud as_solicitud)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		ZonaRegistral lzr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lzr_return      = null;

		try
		{
			if(as_solicitud != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = as_solicitud.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					Acto             la_acto;
					ActoDAO          la_DAO;
					Collection<Acto> lca_actos;
					String           ls_idCirculo;

					la_acto          = new Acto();
					la_DAO           = DaoCreator.getActoDAO(ldm_manager);
					ls_idCirculo     = null;

					la_acto.setIdSolicitud(ls_idSolicitud);

					lca_actos = la_DAO.findByIdSolicitud(la_acto);

					if(CollectionUtils.isValidCollection(lca_actos))
					{
						for(Acto la_iterador : lca_actos)
						{
							if(la_iterador != null)
							{
								String ls_idCirculoActo;

								ls_idCirculoActo = la_iterador.getIdCirculo();

								if(StringUtils.isValidString(ls_idCirculoActo))
									ls_idCirculo = ls_idCirculoActo;
							}
						}
					}
					else
					{
						Turno             lt_turno;
						Collection<Turno> lct_turnos;

						lt_turno = new Turno();

						lt_turno.setIdSolicitud(ls_idSolicitud);

						lct_turnos = DaoCreator.getTurnoDAO(ldm_manager).findByIdSolicitud(lt_turno);

						if(CollectionUtils.isValidCollection(lct_turnos))
						{
							boolean         lb_encontro;
							Iterator<Turno> lit_iterador;

							lb_encontro      = false;
							lit_iterador     = lct_turnos.iterator();

							while(lit_iterador.hasNext() && !lb_encontro)
							{
								Turno lt_data;

								lt_data = lit_iterador.next();

								if(lt_data != null)
								{
									String ls_idCirculoTurno;

									ls_idCirculoTurno = lt_data.getIdCirculo();

									if(StringUtils.isValidString(ls_idCirculoTurno))
									{
										ls_idCirculo     = ls_idCirculoTurno;

										lb_encontro = true;
									}
								}
							}
						}
					}

					if(StringUtils.isValidString(ls_idCirculo))
					{
						ZonaRegistral    lzr_zonaRegistral;
						ZonaRegistralDAO lzr_DAO;

						lzr_zonaRegistral     = new ZonaRegistral();
						lzr_DAO               = DaoCreator.getZonaRegistralDAO(ldm_manager);

						lzr_zonaRegistral.setIdCirculo(ls_idCirculo);

						lzr_zonaRegistral = lzr_DAO.findByIdCirculo(lzr_zonaRegistral);

						if(lzr_zonaRegistral != null)
							lzr_return = lzr_zonaRegistral;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCirculoRegistralActoAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lzr_return;
	}

	/**
	 * Método encargado de consultar el circulo registral para el id_circulo enviado.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto CirculoRegistral
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de CirculoRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CirculoRegistral
	 */
	public synchronized CirculoRegistral findCirculoRegistralById(CirculoRegistral ap_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		CirculoRegistral lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			if(ap_parametros != null)
			{
				String ls_circulo;

				ls_circulo = ap_parametros.getIdCirculo();

				if(!StringUtils.isValidString(ls_circulo))
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_INVALIDO);

				lcr_datos = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(ap_parametros);

				if(lcr_datos == null)
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_INVALIDO);
			}
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

		return lcr_datos;
	}

	/**
	 * Método encargado de consultar los AccCompletitudDocumental con un id Turno.
	 *
	 * @param as_idTurno Argumento de tipo String que contiene las completitudes a consultar.
	 * @return Colección de AccCompletitudDocumental que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<AccCompletitudDocumental> findCompletitudDocumentalByIdTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<AccCompletitudDocumental> lcacd_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcacd_datos     = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
				lcacd_datos = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager).findByIdTurno(as_idTurno);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCompletitudDocumentalByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcacd_datos;
	}

	/**
	 * Método encargado de consultar los AccCompletitudDocumental con un id Turno.
	 *
	 * @param as_idTurno Argumento de tipo String que contiene las completitudes a consultar.
	 * @return Colección de AccCompletitudDocumental que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<AccCompletitudDocumental> findCompletitudDocumentalByIdTurnoSolicitud(
	    String as_idTurno
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<AccCompletitudDocumental> lcacd_datos;
		String                               ls_solicitud;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcacd_datos      = null;
		ls_solicitud     = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findIdSolicitudByIdTurno(as_idTurno);

			if(StringUtils.isValidString(ls_solicitud))
				lcacd_datos = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager).findAllByIdSolicitud(ls_solicitud);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCompletitudDocumentalByIdTurnoSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcacd_datos;
	}

	/**
	 * Método encargado de consultar una constante para un id_constante enviado.
	 *
	 * @param ac_constante correspondiente al valor del tipo de objeto Constantes
	 * @return devuelve el valor de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes findConstante(Constantes ac_constante)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			if(ac_constante != null)
				lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findById(ac_constante);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 *     Método encargado de consultar el detalle de un id constante determinado.
	 *
	 * @param as_param Variable de tipo String  que contiene un id constante determinado.
	 * @return Retorna  un objeto de tipo Constantes que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
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
	 * Método encargado de consultar una persona correo electronico para un id_persona enviado.
	 *
	 * @param apce_correo correspondiente al valor del tipo de objeto PersonaCorreoElectronico
	 * @return devuelve el valor de PersonaCorreoElectronico
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaCorreoElectronico
	 */
	public synchronized PersonaCorreoElectronico findCorreoByIdPersona(PersonaCorreoElectronico apce_correo)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		PersonaCorreoElectronico lpce_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpce_datos      = null;

		try
		{
			if(apce_correo != null)
				lpce_datos = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager).findCorreoByIdPersona(apce_correo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCorreoByIdPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpce_datos;
	}

	/**
	 * Método encargado de consultar una zona registral para un id_circulo enviado.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de ZonaRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public synchronized ZonaRegistral findDatosZonaRegistralByCirculo(String as_idCirculo)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		ZonaRegistral lzr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lzr_return      = new ZonaRegistral();

		try
		{
			lzr_return.setIdCirculo(as_idCirculo);

			lzr_return = DaoCreator.getZonaRegistralDAO(ldm_manager).findByIdCirculo(lzr_return);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDatosZonaRegistralByCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lzr_return;
	}

	/**
	 * Método encargado de consultar el detalle de un acto para un id_acto enviado.
	 *
	 * @param as_idActoDb correspondiente al valor del tipo de objeto String
	 * @param aoa_datosActo correspondiente al valor del tipo de objeto Acto
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de com.bachue.snr.prosnr01.model.registro.Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr01.model.registro.Acto
	 */
	public synchronized com.bachue.snr.prosnr01.model.registro.Acto findDetalleActo(
	    String as_idActoDb, com.bachue.snr.prosnr01.model.registro.Acto aoa_datosActo, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                                  ldm_manager;
		TipoActoDAO                                 lotad_tad;
		com.bachue.snr.prosnr01.model.registro.Acto ldp_datos;
		Acto                                        ldp_tempDatos;
		ActoDAO                                     la_DAO;

		ldp_tempDatos     = new Acto();
		ldm_manager       = DaoManagerFactory.getDAOManager();
		ldp_datos         = null;
		lotad_tad         = DaoCreator.getTipoActoDAO(ldm_manager);
		la_DAO            = DaoCreator.getActoDAO(ldm_manager);

		try
		{
			if(StringUtils.isValidString(as_idActoDb))
			{
				ldp_datos = lotad_tad.findByIdActo(as_idActoDb);

				if(ldp_datos == null)
					ldp_datos = new com.bachue.snr.prosnr01.model.registro.Acto();

				String     ls_idSolicitud;
				BigDecimal lbd_bd;

				ls_idSolicitud = ldp_datos.getIdSolicitud();

				ldp_tempDatos.setIdSolicitud(ls_idSolicitud);
				ldp_tempDatos.setIdTipoActo("19");

				ldp_tempDatos = la_DAO.findBySolicitudTipoActo(ldp_tempDatos);

				if(ldp_tempDatos != null)
				{
					lbd_bd = ldp_tempDatos.getCantidadActos();

					if(NumericUtils.isValidBigDecimal(lbd_bd))
						ldp_datos.setCantidadMatriculas(NumericUtils.getInteger(lbd_bd.intValue()));
				}

				ldp_tempDatos = new Acto();

				ldp_tempDatos.setIdSolicitud(ls_idSolicitud);
				ldp_tempDatos.setIdTipoActo("99");

				ldp_tempDatos = la_DAO.findBySolicitudTipoActo(ldp_tempDatos);

				if(ldp_tempDatos != null)
				{
					lbd_bd = ldp_tempDatos.getCantidadActos();

					if(NumericUtils.isValidBigDecimal(lbd_bd))
						ldp_datos.setCantidadMatriculasInclr(NumericUtils.getInteger(lbd_bd.intValue()));
				}
			}
			else if(aoa_datosActo != null)
			{
				String ls_actoCuantia;
				ls_actoCuantia = StringUtils.getStringNotNull(aoa_datosActo.getActoConCuantia());

				if(ls_actoCuantia.equals(IdentificadoresCommon.SIN_CUANTIA))
					ls_actoCuantia = EstadoCommon.S;
				else
					ls_actoCuantia = EstadoCommon.N;

				if(ls_actoCuantia.equalsIgnoreCase(EstadoCommon.N))
				{
					if(aoa_datosActo.getCuantiaActo() != null)
					{
						if(!NumericUtils.isValidBigDecimal(aoa_datosActo.getCuantiaActo()))
							throw new B2BException(ErrorKeys.ERROR_CUANTIA_NO_NUMERICO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_CUANTIA_ACTO);
				}
				else
				{
					if(aoa_datosActo.getCantidadActos() != null)
					{
						if(!NumericUtils.isValidInteger(aoa_datosActo.getCantidadActos()))
							throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS_NO_NUMERICO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS);
				}

				if(StringUtils.getStringNotNull(aoa_datosActo.getAvaluo()).equalsIgnoreCase(EstadoCommon.S))
				{
					if(aoa_datosActo.getValorAvaluo() != null)
					{
						if(!NumericUtils.isValidBigDecimal(aoa_datosActo.getValorAvaluo()))
							throw new B2BException(ErrorKeys.ERROR_VALOR_AVALUO_NO_NUMERICO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_VALOR_AVALUO);
				}

				if(aoa_datosActo.getCuantiaActo() != null)
				{
					if(!NumericUtils.isValidBigDecimal(aoa_datosActo.getCuantiaActo()))
						throw new B2BException(ErrorKeys.ERROR_CUANTIA_NO_NUMERICO);
				}

				if(aoa_datosActo.getCantidadActos() != null)
				{
					if(!NumericUtils.isValidInteger(aoa_datosActo.getCantidadActos()))
						throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS);
				}

				if(aoa_datosActo.getIdCirculo() != null)
				{
					if(!StringUtils.isValidString(aoa_datosActo.getIdCirculo()))
						throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_ACTO);
				}

				{
					int li_i;

					li_i = lotad_tad.validateTipoAdqui(aoa_datosActo.getCodigo());

					if(li_i > 0)
					{
						String ls_requiereTipoAdquisicion;
						ls_requiereTipoAdquisicion = aoa_datosActo.getRequiereTipoAdquisicion();

						if(
						    !StringUtils.isValidString(aoa_datosActo.getTipoAdquisicion())
							    && (StringUtils.isValidString(ls_requiereTipoAdquisicion)
							    && ls_requiereTipoAdquisicion.equalsIgnoreCase(EstadoCommon.S))
						)
							throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_ADQUISICION);
					}
				}

				aoa_datosActo.setSecuence(aoa_datosActo.getIdActoDb());

				aoa_datosActo.setIdUsuarioModificacion(as_userId);
				aoa_datosActo.setUserId(as_userId);
				aoa_datosActo.setIpModificacion(as_remoteIp);

				la_DAO.insertOrUpdate(aoa_datosActo, false);

				ldp_tempDatos.setIdSolicitud(aoa_datosActo.getIdSolicitud());
				ldp_tempDatos.setIdTipoActo("19");

				ldp_tempDatos = la_DAO.findBySolicitudTipoActo(ldp_tempDatos);

				Integer li_cantMatr;
				li_cantMatr = aoa_datosActo.getCantidadMatriculas();

				if(ldp_tempDatos != null)
				{
					if(!NumericUtils.isValidInteger(li_cantMatr))
						throw new B2BException(ErrorKeys.ERROR_CANTIDAD_MATRICULAS);

					ldp_tempDatos.setCantidadActos(NumericUtils.getBigDecimal(li_cantMatr.intValue()));
					ldp_tempDatos.setIdUsuarioModificacion(as_userId);
					ldp_tempDatos.setIpModificacion(as_remoteIp);

					la_DAO.updateCantidadActos(ldp_tempDatos, true);
				}
				else
				{
					ldp_tempDatos = new Acto();
					ldp_tempDatos.setIdUsuarioModificacion(as_userId);
					ldp_tempDatos.setIpModificacion(as_remoteIp);
				}

				ldp_tempDatos.setIdSolicitud(aoa_datosActo.getIdSolicitud());
				ldp_tempDatos.setIdTipoActo("99");

				ldp_tempDatos = la_DAO.findBySolicitudTipoActo(ldp_tempDatos);

				if(ldp_tempDatos != null)
				{
					Integer li_cantMatInclr;
					li_cantMatInclr = aoa_datosActo.getCantidadMatriculasInclr();

					if(!NumericUtils.isValidInteger(li_cantMatInclr))
						throw new B2BException(ErrorKeys.ERROR_CANTIDAD_INCLR);

					if(
					    NumericUtils.isValidInteger(li_cantMatr)
						    && (li_cantMatInclr.intValue() != li_cantMatr.intValue())
					)
						throw new B2BException(ErrorKeys.ERROR_CANTIDAD_MATRICULAS_INCLR_Y_MATRICULAS);

					ldp_tempDatos.setCantidadActos(
					    NumericUtils.getBigDecimal(aoa_datosActo.getCantidadMatriculasInclr().intValue())
					);

					ldp_tempDatos.setIdUsuarioModificacion(as_userId);
					ldp_tempDatos.setIpModificacion(as_remoteIp);

					la_DAO.updateCantidadActos(ldp_tempDatos, true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetalleActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar los detalles de antiguo sistema para un id_datos_ant_sistema.
	 *
	 * @param as_idDatosAntSis correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<DetalleAntSistema> findDetallesAntSistema(String as_idDatosAntSis)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<DetalleAntSistema> lcdas_return;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcdas_return     = null;

		try
		{
			if(!StringUtils.isValidString(as_idDatosAntSis))
				throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_ANT_SISTEMA);

			lcdas_return = DaoCreator.getDetalleAntSistemaDAO(ldm_manager).findByDatosAntSis(as_idDatosAntSis);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetallesAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdas_return;
	}

	/**
	 * Método encargado de consultar una direccion persona para un id_persona y un tipo_direccion enviados.
	 *
	 * @param apd_pd correspondiente al valor del tipo de objeto PersonaDireccion
	 * @param as_tipoDir correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de PersonaDireccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaDireccion
	 */
	public synchronized PersonaDireccion findDireccionByIdPersona(PersonaDireccion apd_pd, String as_tipoDir)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		PersonaDireccion ldp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_return      = null;

		try
		{
			if((apd_pd != null) && StringUtils.isValidString(as_tipoDir))
				ldp_return = DaoCreator.getPersonaDireccionDAO(ldm_manager).findDireccionByIdPersona(
					    apd_pd, as_tipoDir
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDireccionByIdPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_return;
	}

	/**
	 * Método encargado de consultar una direccion predio para un id_circulo y un id_matricula enviados.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto DireccionPredio
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de DireccionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredio
	 */
	public synchronized DireccionPredio findDireccionPredioById(DireccionPredio ap_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		DireccionPredio ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			if(ap_parametros != null)
			{
				{
					String ls_circulo;
					long   ll_matricula;

					ll_matricula     = NumericUtils.getLong(ap_parametros.getIdMatricula());
					ls_circulo       = ap_parametros.getIdCirculo();

					if(!StringUtils.isValidString(ls_circulo) || (ll_matricula <= 0))
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = ls_circulo + "-" + ll_matricula;
						throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
					}
				}

				ldp_datos = DaoCreator.getDireccionPredioDAO(ldm_manager).findById(ap_parametros);
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

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar los tipos documentales de correcciones para el id_proceso 3 y el is_subproceso 1.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<TipoDocumental> findDocumentalesCorrecciones()
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_return;
		DAOManager                 ldm_manager;

		lctd_return     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			TipoDocumental ltd_tipoDocumental;

			ltd_tipoDocumental = new TipoDocumental();

			ltd_tipoDocumental.setIdProceso(ProcesoCommon.ID_PROCESO_3);
			ltd_tipoDocumental.setIdSubproceso(ProcesoCommon.ID_SUBPROCESO_1);

			lctd_return = DaoCreator.getTipoDocumentalActoDAO(ldm_manager).findBySubproceso(ltd_tipoDocumental);

			if(CollectionUtils.isValidCollection(lctd_return))
			{
				Iterator<TipoDocumental> litd_iterator;
				Map<String, String>      lmss_codigosTurno;

				litd_iterator         = lctd_return.iterator();
				lmss_codigosTurno     = findCodigosTurnoCertificadoCorrecciones(ldm_manager);

				while(litd_iterator.hasNext())
				{
					TipoDocumental ltd_temp;

					ltd_temp = litd_iterator.next();

					if((ltd_temp != null) && CollectionUtils.isValidCollection(lmss_codigosTurno))
						ltd_temp.setAgregarTurno(lmss_codigosTurno.containsKey(ltd_temp.getIdTipoDocumental()));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDocumentalesCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctd_return;
	}

	/**
	 * Método encargado de consultar el documento asociado para un id_solicitud enviado.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Documento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	public synchronized Documento findDocumento(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Documento  ld_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ld_datos        = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
				ld_datos = DaoCreator.getDocumentoDAO(ldm_manager).findDocumentoByIdSolicitud(as_idSolicitud);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ld_datos;
	}

	/**
	 * Método encargado de consultar los documentos de constancia de rep para un id_tipo_documento, fecha_documento y numero documento enviados.
	 *
	 * @param adc_parametros correspondiente al valor del tipo de objeto DocumentoConstancia
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DocumentoConstancia> findDocumentosConstancia(DocumentoConstancia adc_parametros)
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<DocumentoConstancia> lcdc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcdc_datos      = null;

		try
		{
			if(adc_parametros != null)
				lcdc_datos = DaoCreator.getDocumentoDAO(ldm_manager).consultaMaxDocConstancia(adc_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDocumentosConstancia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdc_datos;
	}

	/**
	 * Find estado predio by id.
	 *
	 * @param aaee_param Objeto que contiene la información para realizar la consulta.
	 * @param as_userId Variable que contiene el id del usuario que realiza la consulta.
	 * @param as_remoteIp Variable que contiene la ip del usuario que realiza la consulta.
	 * @return Objeto que contiene los datos consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Registro findEntidadExterna(AccEntidadExterna aaee_param, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_return       = null;

		try
		{
			if(aaee_param != null)
			{
				String ls_idEntidad;

				ls_idEntidad = aaee_param.getIdEntidadExterna();

				if(StringUtils.isValidString(ls_idEntidad))
				{
					AccEntidadExternaDAO laee_DAO;

					laee_DAO       = DaoCreator.getAccEntidadExternaDAO(ldm_manager);
					aaee_param     = laee_DAO.findByIdAccEntidadExterna(ls_idEntidad);

					if(aaee_param != null)
					{
						Persona    lp_persona;
						PersonaDAO lp_DAO;
						String     ls_razonSocial;

						ls_razonSocial     = aaee_param.getNombre();
						lp_DAO             = DaoCreator.getPersonaDAO(ldm_manager);
						lp_persona         = lp_DAO.findByDocumentoAndTipoDocumentoRazonSocial(
							    aaee_param.getNumeroDocumento(), aaee_param.getIdDocumentoTipo(), ls_razonSocial
							);

						if(lp_persona == null)
						{
							Constantes    lc_constante;
							ConstantesDAO lc_DAO;

							lc_constante     = new Constantes();
							lc_DAO           = DaoCreator.getConstantesDAO(ldm_manager);

							lc_constante.setIdConstante(ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA);

							lc_constante = lc_DAO.findById(lc_constante);

							if(lc_constante != null)
							{
								BigInteger lbi_secuencia;
								String     ls_telefono;
								String     ls_correo;
								String     ls_pais;
								String     ls_secuencia;
								String     ls_departamento;

								lbi_secuencia       = lc_constante.getEntero().add(BigInteger.valueOf(1));
								lp_persona          = new Persona();
								ls_correo           = aaee_param.getCorreoElectronico();
								ls_telefono         = aaee_param.getTelefono();
								ls_pais             = aaee_param.getIdPais();
								ls_departamento     = aaee_param.getIdDepartamento();
								ls_secuencia        = StringUtils.getString(lbi_secuencia);

								lc_constante.setEntero(lbi_secuencia);

								lc_DAO.insertOrUpdate(lc_constante, false);

								lp_persona.setTipoDocIdentidad(EstadoCommon.SE);
								lp_persona.setIdDocumentoTipo(EstadoCommon.SE);
								lp_persona.setNumeroDocumento(ls_secuencia);
								lp_persona.setRazonSocial(ls_razonSocial);
								lp_persona.setIdEntidadExterna(ls_idEntidad);
								lp_persona.setIdTipoPersona(TipoPersonaCommon.JURIDICA);
								lp_persona.setIdPais(ls_pais);
								lp_persona.setIdUsuarioCreacion(as_userId);
								lp_persona.setIpCreacion(as_remoteIp);

								lp_persona = lp_DAO.insertOrUpdate(lp_persona, true);

								if(lp_persona != null)
								{
									String ls_idPersona;

									ls_idPersona = lp_persona.getIdPersona();

									if(StringUtils.isValidString(ls_idPersona))
									{
										if(StringUtils.isValidString(ls_correo))
										{
											PersonaCorreoElectronico lpce_correo;

											lpce_correo = new PersonaCorreoElectronico();

											lpce_correo.setIdPersona(ls_idPersona);
											lpce_correo.setCorreoElectronico(ls_correo);
											lpce_correo.setFechaDesde(new Date());
											lpce_correo.setIdUsuarioCreacion(as_userId);
											lpce_correo.setIpCreacion(as_remoteIp);

											DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
												          .insertOrUpdate(lpce_correo, true);
										}

										if(StringUtils.isValidString(ls_telefono))
										{
											PersonaTelefono lpt_telefono;

											lpt_telefono = new PersonaTelefono();

											lpt_telefono.setIdPersona(ls_idPersona);
											lpt_telefono.setIdPais(ls_pais);
											lpt_telefono.setIdDepartamento(ls_departamento);
											lpt_telefono.setTelefono(ls_telefono);
											lpt_telefono.setFechaDesde(new Date());
											lpt_telefono.setTipoTelefono(EstadoCommon.F);
											lpt_telefono.setIdUsuarioCreacion(as_userId);
											lpt_telefono.setIpCreacion(as_remoteIp);

											DaoCreator.getPersonaTelefonoDAO(ldm_manager)
												          .insertOrUpdate(lpt_telefono, true);
										}
									}
								}

								aaee_param.setIdUsuarioModificacion(as_userId);
								aaee_param.setIpModificacion(as_remoteIp);
								aaee_param.setNumeroDocumento(ls_secuencia);
								aaee_param.setIdDocumentoTipo(EstadoCommon.SE);

								laee_DAO.update(aaee_param);
							}
						}
						else
						{
							String ls_idEntidadExterna;

							ls_idEntidadExterna = lp_persona.getIdEntidadExterna();

							if(!StringUtils.isValidString(ls_idEntidadExterna))
							{
								lp_persona.setIdEntidadExterna(ls_idEntidad);
								lp_persona.setIdUsuarioModificacion(as_userId);
								lp_persona.setIpModificacion(as_remoteIp);

								lp_DAO.insertOrUpdate(lp_persona, false);
							}
						}

						Collection<Persona> lcp_personas;

						lcp_personas = new ArrayList<Persona>();

						lcp_personas.add(lp_persona);

						if(CollectionUtils.isValidCollection(lcp_personas))
						{
							lr_return = llenarDatosPersona(lp_persona, lr_return, ldm_manager);

							lr_return.setListadoPersona(lcp_personas);
							lr_return.setPersona(lp_persona);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEntidadExterna", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_return;
	}

	/**
	 * Método encargado de consultar el estado del predio para un id_estado_predio enviado.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto EstadoPredio
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de EstadoPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoPredio
	 */
	public synchronized EstadoPredio findEstadoPredioById(EstadoPredio ap_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		EstadoPredio lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			if((ap_parametros != null) && StringUtils.isValidString(ap_parametros.getIdEstadoPredio()))
				lcr_datos = DaoCreator.getEstadoPredioDao(ldm_manager).findById(ap_parametros);
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

		return lcr_datos;
	}

	/**
	 * Método encargado de consultar los gravamenes pendientes para un id_circulo y id_matricula enviados.
	 *
	 * @param as_circulo correspondiente al valor del tipo de objeto String
	 * @param as_matricula correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<GravamenPendiente> findGravamenPendiente(String as_circulo, String as_matricula)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<GravamenPendiente> lcgp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcgp_return     = null;

		try
		{
			if(StringUtils.isValidString(as_matricula) && StringUtils.isValidString(as_circulo))
				lcgp_return = DaoCreator.getRegistroCalificacionDAO(ldm_manager)
						                    .findGravamenPendiente(as_circulo, as_matricula);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findGravamenPendiente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcgp_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_nirGenerado correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String findIdCirculo(String as_nirGenerado)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_idCirculo;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ls_idCirculo     = null;

		try
		{
			if(StringUtils.isValidString(as_nirGenerado))
			{
				ls_idCirculo = DaoCreator.getConsultasDAO(ldm_manager).findIdCirculo(as_nirGenerado);

				if(!StringUtils.isValidString(ls_idCirculo))
					ls_idCirculo = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findIdCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_idCirculo;
	}

	/**
	 * Método encargado de consultar la solicitud en base a id_turno enviado.
	 *
	 * @param as_idTurno Objeto que contiene id_turno.
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String findIdSolicitudByIdTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_idSolicitud;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		ls_idSolicitud     = null;

		try
		{
			ls_idSolicitud = DaoCreator.getSolicitudDAO(ldm_manager).findIdSolicitudByIdTurno(as_idTurno);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findIdSolicitudByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_idSolicitud;
	}

	/**
	 * Método encargado de consultar las matriculas segregacion para un id_solicitud, id_circulo y id_matricula enviados.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param as_idMatricula correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MatriculaSegregacion> findMatriculaSegregacionByIdSolicitud(
	    String as_idSolicitud, String as_idMatricula
	)
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		MatriculaSegregacionDAO          lmsd_DAO;
		Collection<MatriculaSegregacion> lcms_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcms_datos      = null;
		lmsd_DAO        = DaoCreator.getMatriculaSegregacionDAO(ldm_manager);

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				MatriculaSegregacion lms_ms;
				boolean              lb_b;

				lms_ms     = new MatriculaSegregacion();
				lb_b       = StringUtils.isValidString(as_idMatricula);

				if(lb_b && as_idMatricula.contains("-"))
				{
					String[] las_as;
					las_as = as_idMatricula.split("-");

					if(las_as != null)
					{
						lms_ms.setIdCirculoMatriz(las_as[0]);
						lms_ms.setMatriculaMatriz(NumericUtils.getLongWrapper(las_as[1]));
					}
				}
				else
					lb_b = false;

				lms_ms.setIdSolicitud(as_idSolicitud);
				lcms_datos = lmsd_DAO.findByIdSolicitudWithUnidadMedida(
					    as_idSolicitud, lms_ms.getIdCirculoMatriz(), lms_ms.getMatriculaMatriz()
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMatriculaSegregacionByIdSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcms_datos;
	}

	/**
	 * Método encargado de consultar la ofician origen para un id_oficina_origen enviado.
	 *
	 * @param aoo_oo correspondiente al valor del tipo de objeto OficinaOrigen
	 * @return devuelve el valor de OficinaOrigen
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficinaOrigen
	 */
	public synchronized OficinaOrigen findOficinaOrigen(OficinaOrigen aoo_oo)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		OficinaOrigen loo_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		loo_datos       = null;

		try
		{
			if(aoo_oo != null)
				loo_datos = DaoCreator.getOficinaOrigenDAO(ldm_manager).findById(aoo_oo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findOficinaOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return loo_datos;
	}

	/**
	 * Método encargado de consultar los datos telefonos, direcciones, correos de una persona para un id_tipo_documento y numero_documento enviados.
	 *
	 * @param ar_parametros correspondiente al valor del tipo de objeto Registro
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro findPersonByDocument(Registro ar_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_datos        = null;

		try
		{
			if(ar_parametros != null)
			{
				Persona lp_persona;

				lp_persona = ar_parametros.getPersona();

				if(lp_persona != null)
				{
					String ls_documento;
					String ls_tmp;

					ls_documento     = lp_persona.getNumeroDocumento();
					ls_tmp           = lp_persona.getTipoDocIdentidad();

					if(!StringUtils.isValidString(ls_documento))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);

					{
						Constantes lc_constante;
						String     ls_caracter;

						lc_constante     = new Constantes();
						ls_caracter      = ConstanteCommon.CARACTERES_ESPECIALES;

						lc_constante.setIdConstante(ls_caracter);

						lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_constante);

						if(lc_constante != null)
						{
							String  ls_constantes;
							boolean lb_valido;

							ls_constantes     = lc_constante.getCaracter();
							lb_valido         = false;

							if(!StringUtils.isValidString(ls_constantes))
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ls_caracter;

								throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
							}

							lb_valido = StringUtils.isValidCharacters(ls_documento, ls_constantes);

							if(!lb_valido)
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_DOC_SIN_CARACTERES);
						}
					}

					{
						Collection<Persona> lcp_personas;

						lcp_personas = listarPersonaConFlag(ldm_manager, lp_persona);

						if(CollectionUtils.isValidCollection(lcp_personas))
						{
							lr_datos = consultarInfoPersonas(lcp_personas, ldm_manager);

							if(lr_datos == null)
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);

							lr_datos.setListadoPersona(lcp_personas);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPersonByDocument", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Método encargado de consultar los datos de una persona para un id_tipo_documento y numero_documento enviados.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto Persona
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Persona> findPersonByDocument(Persona ap_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Persona> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_datos       = null;

		try
		{
			if(ap_parametros != null)
			{
				String ls_documento;
				ls_documento = ap_parametros.getNumeroDocumento();

				if(!StringUtils.isValidString(ls_documento))
					throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

				lcp_datos = DaoCreator.getPersonaDAO(ldm_manager).findByDocument(ap_parametros);

				if(!CollectionUtils.isValidCollection(lcp_datos))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPersonByDocument", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Método encargado de consultar los datos de una persona para un id_persona enviado.
	 *
	 * @param ap_param correspondiente al valor del tipo de objeto Persona
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Persona
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public synchronized Persona findPersonaByIdPersona(Persona ap_param, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Persona    ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			if(ap_param != null)
				ldp_datos = DaoCreator.getPersonaDAO(ldm_manager).findById(ap_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPersonaByIdPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar el predio registro para un id_circulo y matricula enviado.
	 *
	 * @param apr_pr correspondiente al valor del tipo de objeto PredioRegistro
	 * @return devuelve el valor de PredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public synchronized PredioRegistro findPredioRegistroByCirculoMatricula(PredioRegistro apr_pr)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		PredioRegistro lpr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpr_datos       = null;

		try
		{
			if(apr_pr != null)
				lpr_datos = DaoCreator.getPredioRegistroDAO(ldm_manager).findByCirculoMatricula(apr_pr);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPredioRegistroByCirculoMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpr_datos;
	}

	/**
	 * Método encargado de consultar un predio registro para un id_circulo y un id_matricula o un nupre enviados.
	 *
	 * @param apr_parametros correspondiente al valor del tipo de objeto PredioRegistro
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de PredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public synchronized PredioRegistro findPredioRegistroById(PredioRegistro apr_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		PredioRegistro ldp_datos;
		boolean        lb_args;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;
		lb_args         = false;

		try
		{
			if(apr_parametros != null)
			{
				String  ls_circulo;
				long    ll_matricula;
				boolean lb_nupre;
				boolean lb_matricula;

				lb_nupre         = apr_parametros.isValidNupre();
				lb_matricula     = apr_parametros.isValidMatricula();

				if(lb_matricula)
				{
					ll_matricula     = apr_parametros.getIdMatricula();
					ls_circulo       = apr_parametros.getIdCirculo();

					if(!StringUtils.isValidString(ls_circulo) || (ll_matricula <= 0))
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = ls_circulo + "-" + ll_matricula;
						throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
					}
				}

				if(lb_nupre && lb_matricula)
					lb_args = true;

				apr_parametros.setArgs(lb_args);

				ldp_datos = DaoCreator.getPredioRegistroDAO(ldm_manager).findById(apr_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPredioRegistroById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de buscar las prohibiciones VPM para un círculo y matrícula.
	 *
	 * @param apv_pv Objeto de tipo ProhibicionVPM del cúal se extraerán los atributos necesarios para realizar la búsqueda de prohibiciones VPM en la BD si es que existen.
	 * @return Collection<ProhibicionVPM> llena con los datos que se encuentran en la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ProhibicionVPM> findProhibicionesVPM(ProhibicionVPM apv_pv)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<ProhibicionVPM> lcpv_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcpv_return     = null;

		try
		{
			if(apv_pv != null)
			{
				RegistroAnotacionProhibicionDAO lrapd_DAO;
				RegistroAnotacionProhibicion    lrap_anotacionProhibicion;

				lrapd_DAO     = DaoCreator.getRegistroAnotacionProhibicionDao(ldm_manager);

				lrap_anotacionProhibicion = lrapd_DAO.findByCirculoMatricula(
					    apv_pv.getIdCirculoPredio(), NumericUtils.getLong(apv_pv.getIdMatriculaPredio()), true
					);

				if(lrap_anotacionProhibicion != null)
				{
					String ls_tipoAdquisicion;

					ls_tipoAdquisicion = lrap_anotacionProhibicion.getTipoAdquisicion();

					if(StringUtils.isValidString(ls_tipoAdquisicion) && ls_tipoAdquisicion.equalsIgnoreCase("3"))
					{
						AnotacionPredioDAO lapd_DAO;
						ProhibicionVPM     lpv_prohibicion;

						lapd_DAO            = DaoCreator.getAnotacionPredioDAO(ldm_manager);
						lpv_prohibicion     = new ProhibicionVPM();

						lpv_prohibicion.setIdCirculoPredio(lrap_anotacionProhibicion.getIdCirculo());
						lpv_prohibicion.setIdMatriculaPredio(
						    StringUtils.getString(lrap_anotacionProhibicion.getIdMatricula())
						);
						lpv_prohibicion.setIdAnotacion(lrap_anotacionProhibicion.getIdAnotacion());
						lcpv_return = lapd_DAO.findProhibicionesVPM(lpv_prohibicion);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProhibicionesVPM", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpv_return;
	}

	/**
	 * Retorna el valor del objeto de RegistroAnotacionProhibicion.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto Solicitud
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto long
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de RegistroAnotacionProhibicion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroAnotacionProhibicion
	 */
	public synchronized RegistroAnotacionProhibicion findRegistroAnotacionProhibicionByCirMat(
	    Solicitud as_s, String as_idCirculo, long al_idMatricula, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		RegistroAnotacionProhibicion lrap_rap;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lrap_rap        = null;

		try
		{
			lrap_rap = DaoCreator.getRegistroAnotacionProhibicionDao(ldm_manager)
					                 .findByCirculoMatricula(as_idCirculo, al_idMatricula, true);

			findRegistroAnotacionProhibicionByCirculoMatricula(as_s, lrap_rap, as_userId, as_remoteIp, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRegistroAnotacionProhibicionByCirMat", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrap_rap;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param at_turno correspondiente al valor del tipo de objeto Turno
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<RegistroAnotacionProhibicion> findRegistroAnotacionProhibicionByTurno(
	    Turno at_turno, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<RegistroAnotacionProhibicion> lcrap_crap;
		DAOManager                               ldm_manager;

		lcrap_crap      = new ArrayList<RegistroAnotacionProhibicion>();
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(at_turno != null)
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(at_turno);

				if(lt_turno != null)
				{
					Solicitud ls_solicitud;

					ls_solicitud = new Solicitud();

					ls_solicitud.setIdSolicitud(lt_turno.getIdSolicitud());

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

					if(ls_solicitud != null)
					{
						Collection<SolicitudMatricula> lcsm_csm;
						SolicitudMatricula             lsm_sm;
						String                         ls_idSolicitud;

						lcsm_csm           = new ArrayList<SolicitudMatricula>();
						lsm_sm             = new SolicitudMatricula();
						ls_idSolicitud     = ls_solicitud.getIdSolicitud();

						lsm_sm.setIdSolicitud(ls_idSolicitud);
						lsm_sm.setIdCirculo(lt_turno.getIdCirculo());

						lcsm_csm = DaoCreator.getSolicitudMatriculaDAO(ldm_manager).findByIdSolicitudCirculo(lsm_sm);

						if(CollectionUtils.isValidCollection(lcsm_csm))
						{
							RegistroAnotacionProhibicionDAO lrapd_registroAnotProhibDAO;

							lrapd_registroAnotProhibDAO = DaoCreator.getRegistroAnotacionProhibicionDao(ldm_manager);

							for(SolicitudMatricula lsm_item : lcsm_csm)
							{
								if(lsm_item != null)
								{
									RegistroAnotacionProhibicion lrap_datos;

									lrap_datos = lrapd_registroAnotProhibDAO.findByCirculoMatricula(
										    lsm_item.getIdCirculo(), lsm_item.getIdMatricula(), true
										);

									if(lrap_datos != null)
										lcrap_crap.add(
										    findRegistroAnotacionProhibicionByCirculoMatricula(
										        ls_solicitud, lrap_datos, as_userId, as_remoteIp, ldm_manager
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
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRegistroAnotacionProhibicionByTurno", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lcrap_crap.isEmpty())
			lcrap_crap = null;

		return lcrap_crap;
	}

	/**
	 * Método encargado de consultar una solicitud asociada con base a un id solicitud.
	 *
	 * @param as_solicitud Objeto que contiene la información de la solicitud a consultar.
	 * @return Objeto que contiene la información de la solicitud consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudAsociada
	 */
	public synchronized SolicitudAsociada findSolicitudAsociada(Solicitud as_solicitud)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		SolicitudAsociada lsa_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lsa_return      = null;

		try
		{
			if(as_solicitud != null)
			{
				SolicitudAsociadaDAO lsa_DAO;

				lsa_DAO     = DaoCreator.getSolicitudAsociadaDAO(ldm_manager);

				lsa_return = new SolicitudAsociada();

				lsa_return.setIdSolicitud(as_solicitud.getIdSolicitud());

				lsa_return = lsa_DAO.findByIdSolicitud(lsa_return);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudAsociada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lsa_return;
	}

	/**
	 * Método encargado de consultar una solicitud asociada con base a un id solicitud, proceso y subproceso.
	 *
	 * @param as_solicitud Objeto que contiene la información de la solicitud a consultar.
	 * @return Objeto que contiene la información de la solicitud consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudAsociada
	 */
	public synchronized SolicitudAsociada findSolicitudAsociadaProcesoSubProceso(Solicitud as_solicitud)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		SolicitudAsociada lsa_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lsa_return      = null;

		try
		{
			if(as_solicitud != null)
			{
				lsa_return = new SolicitudAsociada();

				lsa_return.setIdSolicitud(as_solicitud.getIdSolicitud());
				lsa_return.setIdProceso(as_solicitud.getIdProceso());
				lsa_return.setIdSubProceso(as_solicitud.getIdSubproceso());

				lsa_return = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
						                   .findByIdSolicitudProcesoSubProceso(lsa_return);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudAsociadaProcesoSubProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lsa_return;
	}

	/**
	 * Método encargado de consultar una solicitud y su detalle, con base  a un id solicitud.
	 *
	 * @param as_parametros Objeto que contiene la información de la solicitud a consultar.
	 * @return Objeto que contiene la información de la solicitud consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public synchronized Solicitud findSolicitudById(Solicitud as_parametros)
	    throws B2BException
	{
		Solicitud lcp_datos;

		lcp_datos = null;

		if(as_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idSolicitud;

				ls_idSolicitud = as_parametros.getIdSolicitud();

				if(!StringUtils.isValidString(ls_idSolicitud))
					throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

				lcp_datos = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_parametros);

				if(as_parametros.isDocumento() && (lcp_datos != null))
				{
					Documento lod_datos;

					lod_datos = new Documento();

					lod_datos.setIdDocumento(lcp_datos.getIdDocumento());

					lod_datos = DaoCreator.getDocumentoDAO(ldm_manager).findById(lod_datos);

					if(lod_datos != null)
						lcp_datos.setFechaDocumento(lod_datos.getFechaDocumento());

					lcp_datos.setActosDetails(DaoCreator.getTipoActoDAO(ldm_manager).findDetailsActos());
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findSolicitudById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcp_datos;
	}

	/**
	 * Método encargado de consultar los intervinientes de una solicitud con base al id de la solicitud.
	 *
	 * @param asi_parametros Objeto que contiene los datos de la solicitud para realizar la consulta.
	 * @return Colección que contiene los intervinientes consultados.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<SolicitudInterviniente> findSolicitudIntervinienteBySolicitud(
	    SolicitudInterviniente asi_parametros
	)
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		Collection<SolicitudInterviniente> lcsi_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcsi_datos      = null;

		try
		{
			if(asi_parametros != null)
			{
				String ls_idSolicitud;
				ls_idSolicitud = asi_parametros.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
					lcsi_datos = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager)
							                   .findBySolicitudPersona(asi_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsi_datos;
	}

	/**
	 * Método encargado de consultar el telefono de una persona con base al id de la persona.
	 *
	 * @param lpt_telFijo Objeto que contiene la información de la persona que se desea consultar.
	 * @param as_tipoTel Variable de tipo String que contiene el tipo de telefono que se desea consultar.
	 * @return Objeto que contienie la información del telefono de la persona consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public synchronized PersonaTelefono findTelefonoByIdPersona(PersonaTelefono lpt_telFijo, String as_tipoTel)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		PersonaTelefono lpt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpt_datos       = null;

		try
		{
			if((lpt_telFijo != null) && StringUtils.isValidString(as_tipoTel))
				lpt_datos = DaoCreator.getPersonaTelefonoDAO(ldm_manager)
						                  .findTelefonoByIdPersona(lpt_telFijo, as_tipoTel);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTelefonoByIdPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpt_datos;
	}

	/**
	 * Metodo encargado de consultar los datosantsistema con una matricula.
	 *
	 * @param adc_param correspondiente al valor del tipo de objeto DocumentoConstancia
	 * @return Collección de Actos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Testamento findTestamentoByDocumento(DocumentoConstancia adc_param)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Testamento ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			if(adc_param != null)
			{
				String ls_idDocumento;
				ls_idDocumento = adc_param.getIdDocumento();

				if(StringUtils.isValidString(ls_idDocumento))
				{
					Solicitud ls_solicitud;
					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByIdDocumento(ls_idDocumento);

					if(ls_solicitud != null)
					{
						Testamento lt_testamento;
						lt_testamento = new Testamento();
						lt_testamento.setIdTestamento(ls_solicitud.getIdTestamento());
						ldp_datos = DaoCreator.getTestamentoDAO(ldm_manager).findById(lt_testamento);

						if(ldp_datos != null)
						{
							TipoTestamento ltt_tipoTestamento;
							ltt_tipoTestamento = new TipoTestamento();
							ltt_tipoTestamento.setIdTipoTestamento(ldp_datos.getIdTipoTestamento());
							ltt_tipoTestamento = DaoCreator.getTipoTestamentoDAO(ldm_manager)
									                           .findById(ltt_tipoTestamento);

							if(ltt_tipoTestamento != null)
							{
								String ls_nombreTipoTestamento;
								ls_nombreTipoTestamento = ltt_tipoTestamento.getNombre();

								if(StringUtils.isValidString(ls_nombreTipoTestamento))
									ldp_datos.setNombreTipoTestamento(ls_nombreTipoTestamento);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByIdMatriculaGrabacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar el detalle de un registro determinado de la tabla SDB_PGN_TIPO_ACTO.
	 *
	 * @param aps_param Objeto de tipo TipoActo que contiene los datos necesarios para realizar la consulta.
	 * @return Retorna  un objeto  de Tipo  Acto
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
	 * Método que consulta todos los tipo adquisicion de la tabla SDB_ACC_TIPO_ADQUISICION.
	 *
	 * @return Colección que contiene los tipo adquisición consultados.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoAdquisicion> findTipoAdqui()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TipoAdquisicion> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getTipoActoDAO(ldm_manager).findTipoAdqui();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoAdqui", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoDocumentoPublico> findTipoDocumentoPublicoByConstante()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<TipoDocumentoPublico> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = new ArrayList<TipoDocumentoPublico>();

		try
		{
			Constantes    lc_constante;
			ConstantesDAO lc_DAO;

			lc_constante     = new Constantes();
			lc_DAO           = DaoCreator.getConstantesDAO(ldm_manager);

			lc_constante.setIdConstante(ConstanteCommon.TIPO_DOC_REQ_GRABACION_MATRICULAS);

			lc_constante = lc_DAO.findById(lc_constante);

			if(lc_constante != null)
			{
				String ls_caracter;

				ls_caracter = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracter))
				{
					String[] lsa_codigos;

					lsa_codigos = ls_caracter.split(",");

					if(lsa_codigos != null)
					{
						for(String ls_tmp : lsa_codigos)
						{
							if(StringUtils.isValidString(ls_tmp))
							{
								TipoDocumentoPublico ltdp_tipoDoc;
								ltdp_tipoDoc = new TipoDocumentoPublico();
								ltdp_tipoDoc.setIdTipoDocumento(ls_tmp);

								ltdp_tipoDoc = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).findById(
									    ltdp_tipoDoc
									);

								if(ltdp_tipoDoc != null)
									lcidt_datos.add(ltdp_tipoDoc);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoDocumentoPublicoByConstante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método encargado de buscar los tipos documento para traslado.
	 *
	 * @return Colección de documentos.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoDocumentoPublico> findTipoDocumentoPublicoTraslado()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<TipoDocumentoPublico> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = new ArrayList<TipoDocumentoPublico>();

		try
		{
			Map<String, String> lmms_datos;

			lmms_datos = generarCodigos(ConstanteCommon.TIPO_DOC_REQ_TRASLADO_MATRICULAS, ldm_manager);

			if(CollectionUtils.isValidCollection(lmms_datos))
			{
				for(Map.Entry<String, String> lmss_iterador : lmms_datos.entrySet())
				{
					if(lmss_iterador != null)
					{
						TipoDocumentoPublico ltdp_tipoDoc;
						ltdp_tipoDoc = new TipoDocumentoPublico();
						ltdp_tipoDoc.setIdTipoDocumento(lmss_iterador.getValue());

						ltdp_tipoDoc = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).findById(ltdp_tipoDoc);

						if(ltdp_tipoDoc != null)
							lcidt_datos.add(ltdp_tipoDoc);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoDocumentoPublicoByConstante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método encargado de consultar los turnos por el nir enviado.
	 *
	 * @param at_turno correspondiente al valor del tipo de objeto Turno
	 * @return coleccion de turnos
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Turno> findTurnoByNir(Turno at_turno)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Turno> lct_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lct_return      = new ArrayList<Turno>();

		try
		{
			Collection<Turno> lct_datos;

			lct_datos = null;

			if(at_turno != null)
				lct_datos = DaoCreator.getTurnoDAO(ldm_manager).findByNir(at_turno);

			if(CollectionUtils.isValidCollection(lct_datos))
				lct_return.addAll(lct_datos);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoByNir", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(!CollectionUtils.isValidCollection(lct_return))
			lct_return = null;

		return lct_return;
	}

	/**
	 * Método encargado de consultar todos los turno bloqueo de un predio.
	 *
	 * @param apr_predioR Objeto qeu contiene los datos para realizar la consulta (circulo y matricula del predio)
	 * @return Colección que contiene variables de tipo String donde se guarda el id del turno bloqueo del predio consultado.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<String> findTurnosBloqueoPredio(PredioRegistro apr_predioR)
	    throws B2BException
	{
		Collection<String> lcs_return;
		DAOManager         ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcs_return      = null;

		try
		{
			lcs_return = DaoCreator.getBitacoraBloqueoDAO(ldm_manager)
					                   .findTurnoBloqueoRegistroByCirculoMatricula(apr_predioR, true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnosBloqueoPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_return;
	}

	/**
	 * Método encargado de consultar todos los turno bloqueo de un predio.
	 *
	 * @param apr_predioR Objeto qeu contiene los datos para realizar la consulta (circulo y matricula del predio)
	 * @return Colección que contiene variables de tipo String donde se guarda el id del turno bloqueo del predio consultado.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<String> findTurnosBloqueoPredio(DatosAntSistema apr_predioR)
	    throws B2BException
	{
		Collection<String> lcs_return;
		DAOManager         ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcs_return      = null;

		try
		{
			lcs_return = DaoCreator.getBitacoraBloqueoDAO(ldm_manager)
					                   .findTurnoBloqueoRegistroByCirculoMatricula(apr_predioR, true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnosBloqueoPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_return;
	}

	/**
	 * Método encargado de consultar una zona registra con base a su id.
	 *
	 * @param ap_parametros Objeto que contiene la información para realizar la consulta (id de la zona registral)
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @return Objeto que contiene la información de la zona registral consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public synchronized ZonaRegistral findZonaRegistralById(ZonaRegistral ap_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		ZonaRegistral lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			if(ap_parametros != null)
			{
				{
					String ls_circulo;
					ls_circulo = ap_parametros.getIdCirculo();

					if(!StringUtils.isValidString(ls_circulo))
						throw new B2BException(ErrorKeys.ZONA_REGISTRAL_INVALIDO);
				}

				lcr_datos = DaoCreator.getZonaRegistralDAO(ldm_manager).findByIdCirculo(ap_parametros);

				if(lcr_datos == null)
					throw new B2BException(ErrorKeys.ZONA_REGISTRAL_INVALIDO);
			}
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

		return lcr_datos;
	}

	/**
	 * Generar comunicado respuesta solicitud exenta.
	 *
	 * @param adm_manager de adm manager
	 * @param as_solicitud de as solicitud
	 * @param ath_historia de as id turno
	 * @param ab_resolverReferenciaSalida de ab resolver referencia salida
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarComunicadoRespuestaSolicitudExenta(
	    DAOManager adm_manager, Solicitud as_solicitud, TurnoHistoria ath_historia, boolean ab_resolverReferenciaSalida,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[] lba_documento;

		lba_documento = null;

		try
		{
			Constantes lc_constante;
			String     ls_plantilla;

			lc_constante = DaoCreator.getConstantesDAO(adm_manager)
					                     .findByIdWithImage(
					    ConstanteCommon.PLANTILLA_COMUNICADO_RESPUESTA_SOLICITUD_EXENTA
					);

			if(
			    (lc_constante != null) && (as_solicitud != null) && (ath_historia != null)
				    && validarDatosAuditoria(as_userId, as_remoteIp)
			)
				;

			{
				byte[] lba_imagen;
				String ls_idTurno;
				String ls_idCirculo;
				String ls_idPersona;
				String ls_idCorreo;
				String ls_consecutivoOficio;

				lba_imagen               = lc_constante.getImagenes();
				ls_idTurno               = ath_historia.getIdTurno();
				ls_idCirculo             = null;
				ls_idPersona             = as_solicitud.getIdPersona();
				ls_idCorreo              = as_solicitud.getIdCorreoElectronico();
				ls_consecutivoOficio     = null;
				ls_plantilla             = new String(lba_imagen);

				if(StringUtils.isValidString(ls_plantilla))
				{
					String ls_tag;

					ls_tag = TagCommon.TAG_PROCESO;

					if(ls_plantilla.contains(ls_tag))
					{
						String ls_proceso;

						ls_proceso = as_solicitud.getIdProceso();

						if(!StringUtils.isValidString(ls_proceso))
							throw new B2BException(ErrorKeys.ERROR_SIN_ID_PROCESO);

						ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_proceso);
					}

					ls_tag = TagCommon.TAG_MOTIVO_CONSULTA;

					if(ls_plantilla.contains(ls_tag))
					{
						String ls_motivoConsulta;

						ls_motivoConsulta     = as_solicitud.getMotivoConsulta();

						ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_motivoConsulta);
					}

					ls_tag = TagCommon.TAG_REFERENCIA_MOTIVO_CONSULTA;

					if(ls_plantilla.contains(ls_tag))
					{
						String ls_motivoConsulta;

						ls_motivoConsulta     = as_solicitud.getMotivoConsulta();

						ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_motivoConsulta);
					}

					ls_tag = TagCommon.TAG_ACC_TUR_ID_TURNO;

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_idTurno);

					ls_tag = TagCommon.TAG_NOMBRE_ORIP;

					if(ls_plantilla.contains(ls_tag))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

						if(lt_turno != null)
						{
							CirculoRegistral lcr_circulo;

							lcr_circulo = DaoCreator.getCirculoRegistralDAO(adm_manager)
									                    .findById(lt_turno.getIdCirculo());

							if(lcr_circulo != null)
							{
								ls_idCirculo     = lcr_circulo.getIdCirculo();
								ls_plantilla     = reemplazarTagEnPlantilla(
									    ls_plantilla, TagCommon.TAG_NOMBRE_ORIP, lcr_circulo.getNombre()
									);
							}
						}
					}

					ls_tag = TagCommon.TAG_MUN_OFI_ORIGEN;

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
								ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_munOficinaOrigen);
						}
					}

					ls_tag = TagCommon.TAG_USUARIO;

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, TagCommon.TAG_USUARIO, as_userId);

					ls_tag = TagCommon.TAG_NIR;

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, TagCommon.TAG_NIR, as_solicitud.getNir());

					ls_tag = TagCommon.TAG_FECHA_LARGA;

					if(ls_plantilla.contains(ls_tag))
					{
						Date   ld_fecha;
						String ls_fechaActual;

						ld_fecha           = new Date();
						ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fecha);

						if(StringUtils.isValidString(ls_fechaActual))
							ls_plantilla = ls_plantilla.replace(
								    TagCommon.TAG_FECHA_LARGA, ls_fechaActual.toUpperCase()
								);
					}

					ls_tag = TagCommon.TAG_NOMBRE_INTERESADO;

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_NOMBRE_INTERESADO,
							    obtenerNombrePersona(ls_idPersona, adm_manager)
							);

					ls_tag = TagCommon.TAG_CORREO_ELECTRONICO;

					if(ls_plantilla.contains(ls_tag))
					{
						if(StringUtils.isValidString(ls_idCorreo))
							ls_plantilla = reemplazarTagEnPlantilla(
								    ls_plantilla, TagCommon.TAG_CORREO_ELECTRONICO,
								    obtenerCorreoPersona(ls_idPersona, ls_idCorreo, adm_manager)
								);
					}

					ls_tag = TagCommon.TAG_OFICIO;

					if(ls_plantilla.contains(ls_tag))
					{
						NumeracionOficiosCirculo lnoc_numeracionOficios;

						lnoc_numeracionOficios = findNumeracionOficiosCirculo(
							    ath_historia, adm_manager, as_userId, as_remoteIp
							);

						if(lnoc_numeracionOficios != null)
						{
							ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

							if(StringUtils.isValidString(ls_consecutivoOficio))
								ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_consecutivoOficio);
							else
								throw new B2BException(ErrorKeys.CONSECUTIVO_OFICIO_INVALIDO);
						}
					}

					ls_tag = TagCommon.TAG_REFERENCIA_SALIDA;

					if(ls_plantilla.contains(ls_tag) && ab_resolverReferenciaSalida)
					{
						String ls_referenciaSalida;

						ls_referenciaSalida = generarIdCorrespondencia(ath_historia, adm_manager, false);

						if(StringUtils.isValidString(ls_referenciaSalida))
							ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);
					}

					lba_documento = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

					if(lba_documento != null)
					{
						Long             ll_idTurnoHistoria;
						DocumentosSalida lds_documento;
						long             ll_idDocumentosSalida;

						ll_idTurnoHistoria        = ath_historia.getIdTurnoHistoria();
						lds_documento             = DaoCreator.getDocumentosSalidaDAO(adm_manager)
								                                  .findByIdTurnoHistoriaTipoDocumental(
								    NumericUtils.getInteger(ll_idTurnoHistoria), TipoDocumentalCommon.OFICIO
								);
						ll_idDocumentosSalida     = 0L;

						if(lds_documento != null)
						{
							Long ll_idImagen;

							ll_idImagen               = lds_documento.getIdImagen();
							ll_idDocumentosSalida     = lds_documento.getIdDocumentoSalida();

							if(NumericUtils.isValidLong(ll_idImagen))
							{
								ImagenesDAO lid_DAO;
								Imagenes    li_imagen;

								lid_DAO       = DaoCreator.getImagenesDAO(adm_manager);
								li_imagen     = lid_DAO.findById(ll_idImagen.longValue());

								if(li_imagen != null)
								{
									li_imagen.setImagenBlob(lba_documento);
									lid_DAO.insertOrUpdate(li_imagen, false);
								}
							}
						}
						else
						{
							lds_documento = new DocumentosSalida();
							lds_documento.setIdTurnoHistoria(NumericUtils.getInteger(ll_idTurnoHistoria));
							lds_documento.setIdTipoDocumental(TipoDocumentalCommon.OFICIO);
							lds_documento.setEstado(EstadoCommon.ACTIVO);
							lds_documento.setRepositorio(RepositorioCommon.TEMPORAL);
							lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							lds_documento.setTipo(TipoArchivoCommon.COMUNICADO_EXENTA);
							lds_documento.setIdSolicitud(as_solicitud.getIdSolicitud());
							lds_documento.setDestinatario(obtenerNombrePersona(ls_idPersona, adm_manager));
							lds_documento.setDocumentoAutomatico(IdentificadoresCommon.S);
							lds_documento.setDocumentoFinal(IdentificadoresCommon.N);
							lds_documento.setConsecutivoOficio(ls_consecutivoOficio);
							lds_documento.setFechaOficio(new Date());
							lds_documento.setCorreoElectronico(
							    obtenerCorreoPersona(ls_idPersona, ls_idCorreo, adm_manager)
							);
							lds_documento.setIdTurno(ls_idTurno);

							{
								String ls_telefono;

								ls_telefono = null;

								{
									PersonaTelefono lpt_telefono;

									lpt_telefono = DaoCreator.getPersonaTelefonoDAO(adm_manager)
											                     .findById(ls_idPersona, as_solicitud.getIdTelefono());

									if(lpt_telefono != null)
										ls_telefono = lpt_telefono.getTelefono();
								}

								if(StringUtils.isValidString(ls_telefono))
									lds_documento.setTelefono(ls_telefono);
							}

							ll_idDocumentosSalida = insertarDocumentoSalida(
								    lba_documento, lds_documento, as_userId, as_remoteIp, adm_manager
								);
						}

						if(ll_idDocumentosSalida > 0L)
						{
							DAOManager ldm_bitacora;

							ldm_bitacora = DaoManagerFactory.getDAOManager();

							try
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

									{
										enviarGestorDocumental(
										    lds_documento, lbpd_bitacora, ls_endpoint, as_userId, as_remoteIp,
										    adm_manager
										);

										if(!lds_documento.isEnviadoOwcc())
											throw new B2BException(
											    "Ocurrió un error enviando el documento al OWCC, intentelo nuevamente"
											);
									}
								}
								else
								{
									Object[] loa_params;

									loa_params        = new String[1];
									loa_params[0]     = ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT;

									throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_params);
								}
							}
							catch(B2BException lb2be_e)
							{
								ldm_bitacora.setRollbackOnly();

								clh_LOGGER.error("generarComunicadoRespuestaSolicitudExenta", lb2be_e);
								throw lb2be_e;
							}
							finally
							{
								ldm_bitacora.close();
							}
						}
					}
				}
				else
				{
					Object[] loa_args;

					loa_args        = new String[1];
					loa_args[0]     = ConstanteCommon.PLANTILLA_COMUNICADO_RESPUESTA_SOLICITUD_EXENTA;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarComunicadoRespuestaSolicitudExenta", lb2be_e);
			throw lb2be_e;
		}

		return lba_documento;
	}

	/**
	 * Genera recibo de caja y lo guarda en la base de datos.
	 *
	 * @param ar_parametros Objeto contenedor de la información que debe ir en el pdf
	 * @param ab_b Indica si el pdf debe llevar o no el numero de referencia
	 * @param as_userId id del usuario que ejecuta el proceso
	 * @param as_localIp Dirección IP del servidor donde se recibe la petición
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @return Objeto registro contenedor del pdf generado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro generarReciboCaja(
	    Registro ar_parametros, boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Registro   lr_r;
		DAOManager ldm_manager;

		lr_r            = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_parametros != null)
			{
				String ls_nir;
				ls_nir = ar_parametros.getNirProceso();

				if(StringUtils.isValidString(ls_nir))
				{
					Solicitud ls_s;
					ls_s = new Solicitud();

					ls_s.setNir(ls_nir);

					ls_s = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(ls_s);

					if(ls_s != null)
					{
						Collection<DocumentosSalida> ldp_datos;
						byte[]                       lba_zipFinal;
						ImagenesDAO                  loid_id;

						loid_id          = DaoCreator.getImagenesDAO(ldm_manager);
						lba_zipFinal     = null;
						ldp_datos        = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
								                         .findAllDocumentBySolicitudTipoEstado(
								    new DocumentosSalida(
								        ls_s.getIdSolicitud(), TipoArchivoCommon.RECIBO_CAJA, EstadoCommon.ACTIVO
								    )
								);

						if(CollectionUtils.isValidCollection(ldp_datos))
						{
							Collection<ZipEntryUtil> lczeu_zip;
							int                      li_contador;

							lczeu_zip       = new ArrayList<ZipEntryUtil>();
							li_contador     = 1;

							for(DocumentosSalida lds_actual : ldp_datos)
							{
								Imagenes li_detalleImage;

								li_detalleImage = new Imagenes();

								li_detalleImage.setIdImagen(NumericUtils.getInt(lds_actual.getIdImagen()));

								li_detalleImage = loid_id.findById(li_detalleImage);

								if(li_detalleImage != null)
								{
									byte[] lba_imagenBlob;

									lba_imagenBlob = li_detalleImage.getImagenBlob();

									if(lba_imagenBlob != null)
									{
										ZipEntryUtil lzeu_pdf;

										lzeu_pdf = new ZipEntryUtil(
											    lds_actual.getIdTurno() + IdentificadoresCommon.SIMBOLO_GUION_BAJO
											    + lds_actual.getTipo() + IdentificadoresCommon.SIMBOLO_GUION_BAJO
											    + (li_contador++) + IdentificadoresCommon.PUNTO
											    + li_detalleImage.getTipoArchivo(),
											    new ByteArrayInputStream(lba_imagenBlob)
											);
										lczeu_zip.add(lzeu_pdf);
									}
								}
							}

							if(CollectionUtils.isValidCollection(lczeu_zip))
								lba_zipFinal = ZipUtils.generateZip(lczeu_zip);
						}

						if(lba_zipFinal != null)
						{
							lr_r = new Registro();

							lr_r.setPdf(lba_zipFinal);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarReciboCaja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_r;
	}

	/**
	 * Genera pdf de liquidación y lo guarda en la base de datos.
	 *
	 * @param ar_parametros Objeto contenedor de la información que debe ir en el pdf
	 * @param ab_b Indica si el pdf debe llevar o no el numero de referencia
	 * @param as_userId id del usuario que ejecuta el proceso
	 * @param as_localIp Dirección IP del servidor donde se recibe la petición
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @param adm_manager Objeto para manipular la conexión con la base de datos
	 * @return Objeto registro contenedor del pdf generado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro generarReciboLiquidacion(
	    Registro ar_parametros, boolean ab_b, String as_userId, String as_localIp, String as_remoteIp,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return generarReciboLiquidacion(ar_parametros, ab_b, as_userId, as_localIp, as_remoteIp, adm_manager, false);
	}

	/**
	 * Genera pdf de liquidación y lo guarda en la base de datos.
	 *
	 * @param ar_parametros Objeto contenedor de la información que debe ir en el pdf
	 * @param ab_b Indica si el pdf debe llevar o no el numero de referencia
	 * @param as_userId id del usuario que ejecuta el proceso
	 * @param as_localIp Dirección IP del servidor donde se recibe la petición
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @param adm_manager Objeto para manipular la conexión con la base de datos
	 * @return Objeto registro contenedor del pdf generado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro generarReciboLiquidacion(
	    Registro ar_parametros, boolean ab_b, String as_userId, String as_localIp, String as_remoteIp,
	    DAOManager adm_manager, boolean ab_a
	)
	    throws B2BException
	{
		Registro lr_r;

		lr_r = new Registro();

		if(ar_parametros != null)
		{
			try
			{
				byte[]         lba_plantilla;
				String         ls_idConstante;
				Constantes     lc_datos;
				Constantes     lc_plantilla;
				byte[]         lba_reciboLiquidacion;
				String         ls_plantilla;
				String         ls_tag;
				String         ls_idTipoMayorValorNE;
				Solicitud      ls_s;
				String         ls_idSolicitud;
				LiquidacionDAO lld_DAO;
				boolean        lb_tipoRecibo;
				boolean        lb_condicion;
				ConstantesDAO  lc_DAO;

				lc_datos                  = new Constantes();
				lba_reciboLiquidacion     = null;
				lc_plantilla              = new Constantes();
				ls_tag                    = null;
				ls_s                      = null;
				ls_idSolicitud            = null;
				lld_DAO                   = DaoCreator.getAccLiquidacionDAO(adm_manager);
				lb_tipoRecibo             = StringUtils.getStringNotNull(ar_parametros.getTipoRecibo())
						                                   .equalsIgnoreCase(IdentificadoresCommon.RECIBO_LIQUIDACION);
				lb_condicion              = StringUtils.getStringNotNull(ar_parametros.getIdCondicion())
						                                   .equalsIgnoreCase(IdentificadoresCommon.LIQUIDAR);
				ls_idConstante            = lb_tipoRecibo ? ConstanteCommon.PLANTILLA_RECIBO_LIQUIDACION
					                                      : ConstanteCommon.PLANTILLA_RECIBO_CAJA;

				lc_DAO = DaoCreator.getConstantesDAO(adm_manager);

				lc_datos.setIdConstante(ls_idConstante);
				lc_plantilla     = lc_DAO.findImagen(lc_datos);

				ls_s                      = ar_parametros.getSolicitud();
				ls_idTipoMayorValorNE     = ar_parametros.getIdTipoMayorValor();

				if((lc_plantilla != null) && (ls_s != null))
				{
					lba_plantilla = lc_plantilla.getImagenes();

					if(lba_plantilla != null)
					{
						SolicitudMatricula             losm_sm;
						Solicitud                      ls_solicitud;
						Collection<SolicitudMatricula> lcsm_infoCirculos;
						String                         ls_consecutivoGenerado;
						String                         ls_fechaVencimiento;
						String                         ls_numeroReferencia;
						String                         ls_idProcesoSolicitud;
						boolean                        lb_validProceso;

						ls_fechaVencimiento        = null;
						ls_solicitud               = new Solicitud();
						ls_plantilla               = new String(lba_plantilla);
						losm_sm                    = new SolicitudMatricula();
						ls_idSolicitud             = ls_s.getIdSolicitud();
						ls_consecutivoGenerado     = null;
						ls_numeroReferencia        = null;
						ls_idProcesoSolicitud      = ls_s.getIdProceso();
						lb_validProceso            = StringUtils.isValidString(ls_idProcesoSolicitud);

						ls_solicitud.setIdSolicitud(ls_idSolicitud);

						ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_solicitud);

						losm_sm.setIdSolicitud(ls_idSolicitud);

						lcsm_infoCirculos = DaoCreator.getSolicitudMatriculaDAO(adm_manager).findBySolicitud(losm_sm);

						if(StringUtils.isValidString(ls_plantilla))
						{
							Constantes          lc_c;
							Map<String, String> lmss_datos;

							lc_c           = new Constantes();
							lmss_datos     = null;

							lc_c.setIdUsuarioCreacion(as_userId);
							lc_c.setIpCreacion(as_remoteIp);

							ls_plantilla     = resolverTagsSNRReciboLiquidacion(adm_manager, ls_plantilla);

							ls_tag = TagCommon.TAG_VENCIMIENTO_TERMINO_COPIAS;

							if(
							    ls_plantilla.contains(ls_tag) && lb_validProceso
								    && ls_idProcesoSolicitud.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5)
							)
							{
								String ls_textoVencimientoCopias;

								ls_textoVencimientoCopias = lc_DAO.findString(
									    ConstanteCommon.TEXTO_VENCIMIENTO_TERMINOS_COPIAS
									);

								if(StringUtils.isValidString(ls_textoVencimientoCopias))
								{
									StringBuilder lsb_cadenaFinal;

									lsb_cadenaFinal = new StringBuilder(
										    "{\\rtlch\\fcs1 \\ab\\af0 \\ltrch\\fcs0 \\b Nota:\\line \\line }"
										);

									lsb_cadenaFinal.append("{\\rtlch\\fcs1 \\af0 \\ltrch\\fcs0 \\ul ");
									lsb_cadenaFinal.append(ls_textoVencimientoCopias);
									lsb_cadenaFinal.append("}");

									ls_plantilla = reemplazarTagEnPlantilla(
										    ls_plantilla, ls_tag, lsb_cadenaFinal.toString()
										);
								}
							}

							ls_tag = "<TAG_TITULO_RECIBO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(
									    ls_tag,
									    lb_condicion ? IdentificadoresCommon.TITULO_RECIBO_LIQUIDACION
									                 : IdentificadoresCommon.TITULO_RECIBO_PRELIQUIDACION
									);

							ls_tag = "<TAG_NUMERO_REFERENCIA>";

							if(ab_b)
							{
								if(ls_plantilla.contains(ls_tag))
								{
									if(lb_condicion)
									{
										lc_c.setIdConstante(ConstanteCommon.CONSECUTIVO_REFERENCIA);
										lc_c.setIdProceso(ls_s.getIdProceso());

										ls_numeroReferencia = generacionNumerosConsecutivos(
											    lc_c, adm_manager, false, false
											);

										lr_r.setTipoRecibo(ls_numeroReferencia);

										if(StringUtils.isValidString(ls_numeroReferencia))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_numeroReferencia);
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, " ");
									}

									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}
							else
							{
								if(ls_plantilla.contains(ls_tag))
								{
									Liquidacion ll_liquidacion;

									ll_liquidacion = new Liquidacion();

									ll_liquidacion.setIdSolicitud(ls_idSolicitud);
									ll_liquidacion.setConsecutivo(1);

									ll_liquidacion = DaoCreator.getAccLiquidacionDAO(adm_manager)
											                       .findById(ll_liquidacion, true);

									if(ll_liquidacion != null)
									{
										ls_numeroReferencia = ll_liquidacion.getNumeroReferencia();

										if(StringUtils.isValidString(ls_numeroReferencia))
										{
											lr_r.setTipoRecibo(ls_numeroReferencia);

											if(StringUtils.isValidString(ls_numeroReferencia))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_numeroReferencia);
											else
												ls_plantilla = ls_plantilla.replace(ls_tag, " ");
										}
									}
								}
							}

							ls_tag = "<TAG_NUMERO_CONSECUTIVO>";

							if(ls_plantilla.contains(ls_tag))
							{
								lc_c.setIdConstante(
								    lb_tipoRecibo ? ConstanteCommon.CONSECUTIVO_REC_LIQUIDACION
								                  : ConstanteCommon.CONSECUTIVO_RECIBO_CAJA
								);

								if(!lb_tipoRecibo || lb_condicion)
									ls_consecutivoGenerado = generacionNumerosConsecutivos(
										    lc_c, adm_manager, true, lb_tipoRecibo
										);

								if(StringUtils.isValidString(ls_consecutivoGenerado))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoGenerado);
								else
									ls_plantilla = ls_plantilla.replace(ls_tag, " ");
							}

							ls_tag = "<TAG_FECHA_EXPEDICION>";

							if(ls_plantilla.contains(ls_tag))
							{
								Date ld_fechaActual;
								ld_fechaActual     = new Date();

								ls_plantilla = ls_plantilla.replace(
									    ls_tag, StringUtils.getString(ld_fechaActual, FormatoFechaCommon.DIA_MES_ANIO)
									);
							}

							ls_tag = "<TAG_NIR>";

							if(ls_plantilla.contains(ls_tag))
							{
								if(ls_solicitud != null)
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(ls_solicitud.getNir())
										);
								else
									ls_plantilla = ls_plantilla.replace(ls_tag, " ");

								String ls_tagOrip;
								ls_tag         = "<TAG_NUMERO_TURNO>";
								ls_tagOrip     = "<TAG_INFO_ORIP>";

								if(ls_plantilla.contains(ls_tag))
								{
									if(ls_solicitud != null)
									{
										String            ls_idCirculo;
										String            ls_orip;
										Collection<Turno> lct_turno;
										Turno             lt_turno;

										lct_turno        = new ArrayList<Turno>();
										lt_turno         = new Turno();
										ls_orip          = null;
										ls_idCirculo     = "";

										lt_turno.setIdSolicitud(ls_solicitud.getIdSolicitud());

										lct_turno = DaoCreator.getTurnoDAO(adm_manager).findByIdSolicitud(lt_turno);

										if(CollectionUtils.isValidCollection(lct_turno))
										{
											String ls_turnos;

											ls_turnos = null;

											for(Turno lt_tmp : lct_turno)
											{
												if(lt_tmp != null)
												{
													String ls_turnoTMP;
													String ls_idCirculoTMP;

													ls_turnoTMP         = lt_tmp.getIdTurno();
													ls_idCirculoTMP     = lt_tmp.getIdCirculo();

													if(StringUtils.isValidString(ls_turnoTMP))
													{
														if(ls_turnos != null)
															ls_turnos = ls_turnos + ", " + ls_turnoTMP;
														else
															ls_turnos = ls_turnoTMP;
													}

													if(ls_plantilla.contains(ls_tagOrip))
													{
														if(StringUtils.isValidString(ls_idCirculoTMP))
														{
															if(!ls_idCirculo.equalsIgnoreCase(ls_idCirculoTMP))
															{
																CirculoRegistral lcr_circuloRegistral;
																lcr_circuloRegistral = new CirculoRegistral();
																lcr_circuloRegistral.setIdCirculo(ls_idCirculoTMP);

																lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(
																	    adm_manager
																	).findById(lcr_circuloRegistral);

																if(lcr_circuloRegistral != null)
																{
																	if(ls_orip != null)
																		ls_orip = ls_orip + ", "
																			+ lcr_circuloRegistral.getIdCirculo() + "-"
																			+ lcr_circuloRegistral.getNombre();
																	else
																		ls_orip = lcr_circuloRegistral.getIdCirculo()
																			+ "-" + lcr_circuloRegistral.getNombre();
																}

																ls_idCirculo = ls_idCirculoTMP;
															}
														}
													}
												}
											}

											if(StringUtils.isValidString(ls_turnos))
												ls_plantilla = ls_plantilla.replace(
													    ls_tag, StringUtils.getStringNotNull(ls_turnos)
													);
											else
												ls_plantilla = ls_plantilla.replace(ls_tag, " ");

											if(StringUtils.isValidString(ls_orip))
												ls_plantilla = ls_plantilla.replace(
													    ls_tagOrip, StringUtils.getStringNotNull(ls_orip)
													);
											else
												ls_plantilla = ls_plantilla.replace(ls_tagOrip, " ");
										}
									}
								}
							}
							//Sección datos del solicitante
							{
								String ls_tagNombres;
								String ls_tagIdentificacion;
								String ls_tagNumeroIdentificacion;

								ls_tagNombres                  = "<TAG_NOMBRES>";
								ls_tagIdentificacion           = "<TAG_IDENTIFICACION>";
								ls_tagNumeroIdentificacion     = "<TAG_NUMERO_IDENTIFICACION>";

								if(
								    ls_plantilla.contains(ls_tagNombres) && ls_plantilla.contains(ls_tagIdentificacion)
									    && ls_plantilla.contains(ls_tagNumeroIdentificacion)
								)
								{
									String  ls_nombres;
									String  ls_tipoDoc;
									String  ls_doc;
									Persona lp_persona;

									ls_nombres     = null;
									ls_tipoDoc     = null;
									ls_doc         = null;
									lp_persona     = DaoCreator.getPersonaDAO(adm_manager)
											                       .findById(ls_solicitud.getIdPersona());

									if(lp_persona != null)
									{
										ls_tipoDoc = StringUtils.getStringNotNull(lp_persona.getIdDocumentoTipo());

										if(
										    StringUtils.isValidString(ls_tipoDoc)
											    && ls_tipoDoc.equalsIgnoreCase(IdentificadoresCommon.NIT)
										)
											ls_nombres = StringUtils.getStringNotNull(lp_persona.getRazonSocial());
										else
											ls_nombres = StringUtils.getStringNotNull(lp_persona.getNombreCompleto());

										ls_doc = StringUtils.getStringNotNull(lp_persona.getNumeroDocumento());
									}

									ls_plantilla     = ls_plantilla.replace(ls_tagNombres, ls_nombres);
									ls_plantilla     = ls_plantilla.replace(ls_tagIdentificacion, ls_tipoDoc);
									ls_plantilla     = ls_plantilla.replace(ls_tagNumeroIdentificacion, ls_doc);
								}
							}

							{
								String ls_tagNumeroDoc;
								String ls_tagFechaExpedicion;
								String ls_tagNombreOficina;

								ls_tagNumeroDoc           = "<TAG_NUMERO_DOCUMENTO>";
								ls_tagFechaExpedicion     = "<TAG_FECHA_EXPEDICION_DOCUMENTO>";
								ls_tagNombreOficina       = "<TAG_NOMBRE_OFICINA_ORIGEN>";

								if(
								    ls_plantilla.contains(ls_tagNumeroDoc)
									    && ls_plantilla.contains(ls_tagFechaExpedicion)
									    && ls_plantilla.contains(ls_tagNombreOficina)
								)
								{
									String  ls_idDocumento;
									boolean lb_cleanTags;
									String  ls_tagTipoDocumento;
									boolean lb_tipoDocumento;

									ls_idDocumento          = ls_s.getIdDocumento();
									lb_cleanTags            = false;
									ls_tagTipoDocumento     = "<TAG_TIPO_DOCUMENTO>";
									lb_tipoDocumento        = ls_plantilla.contains(ls_tagTipoDocumento);

									if(!StringUtils.isValidString(ls_idDocumento))
									{
										ls_s = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_s);

										if(ls_s != null)
											ls_idDocumento = ls_s.getIdDocumento();
									}

									if(StringUtils.isValidString(ls_idDocumento))
									{
										RegistroCalificacion lod_tmp;
										lod_tmp     = new RegistroCalificacion();

										lod_tmp = DaoCreator.getDocumentoDAO(adm_manager)
												                .detalleDocumento(ls_idDocumento);

										if(lod_tmp != null)
										{
											ls_plantilla     = ls_plantilla.replace(
												    ls_tagNumeroDoc,
												    StringUtils.getStringNotNull(lod_tmp.getCodDocumento())
												);
											ls_plantilla     = ls_plantilla.replace(
												    ls_tagFechaExpedicion,
												    StringUtils.getString(
												        lod_tmp.getFechaDocumento(), FormatoFechaCommon.DIA_MES_ANIO
												    )
												);
											ls_plantilla     = ls_plantilla.replace(
												    ls_tagNombreOficina,
												    StringUtils.getStringNotNull(lod_tmp.getNombreOficina()) + " DE "
												    + StringUtils.getStringNotNull(lod_tmp.getNombreMunicipio())
												);

											if(lb_tipoDocumento)
												ls_plantilla = ls_plantilla.replace(
													    ls_tagTipoDocumento,
													    StringUtils.getStringNotNull(lod_tmp.getNombreTipoDoc())
													);
										}
										else
											lb_cleanTags = true;
									}
									else
										lb_cleanTags = true;

									if(lb_cleanTags)
									{
										ls_plantilla     = ls_plantilla.replace(ls_tagNumeroDoc, " ");
										ls_plantilla     = ls_plantilla.replace(ls_tagFechaExpedicion, " ");
										ls_plantilla     = ls_plantilla.replace(ls_tagNombreOficina, " ");

										if(lb_tipoDocumento)
											ls_plantilla = ls_plantilla.replace(ls_tagTipoDocumento, " ");
									}
								}

								String ls_tagMatriculas;

								ls_tagMatriculas = "<TAG_MATRICULAS>";

								if(ls_plantilla.contains(ls_tagMatriculas))
								{
									String ls_idProceso;
									String ls_matriculas;

									ls_idProceso      = (ls_solicitud != null) ? ls_solicitud.getIdProceso() : null;
									ls_matriculas     = null;

									if(
									    StringUtils.isValidString(ls_idProceso)
										    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5)
									)
									{
										Collection<SolicitudCopias> lcsc_solicitudCopias;

										lcsc_solicitudCopias = DaoCreator.getSolicitudCopiasDAO(adm_manager)
												                             .findByIdSolicitud(ls_idSolicitud);

										if(CollectionUtils.isValidCollection(lcsc_solicitudCopias))
										{
											SolicitudCopias lsc_solicitudCopias;

											lsc_solicitudCopias = lcsc_solicitudCopias.iterator().next();

											if(lsc_solicitudCopias != null)
											{
												ls_matriculas = StringUtils.getStringNotNull(
													    lsc_solicitudCopias.getMatriculaOwcc()
													);

												if(StringUtils.isValidString(ls_matriculas))
													ls_matriculas = ls_matriculas.replace(";", ", ");
											}
										}
									}
									else
									{
										Collection<SolicitudMatricula> lcsm_cllSolicitudMatricula;
										StringBuilder                  lsb_sb;

										lcsm_cllSolicitudMatricula     = DaoCreator.getSolicitudMatriculaDAO(
											    adm_manager
											).findByIdSolicitud(ls_idSolicitud, false);
										lsb_sb                         = new StringBuilder();

										if(CollectionUtils.isValidCollection(lcsm_cllSolicitudMatricula))
										{
											int li_count;
											int li_countColeccion;

											li_count              = 0;
											li_countColeccion     = lcsm_cllSolicitudMatricula.size();

											for(SolicitudMatricula lsm_solicitudMatricula : lcsm_cllSolicitudMatricula)
											{
												if(lsm_solicitudMatricula != null)
												{
													long ll_idMatricula;

													ll_idMatricula = lsm_solicitudMatricula.getIdMatricula();

													li_count++;

													if(ll_idMatricula > 0)
													{
														if(!(li_count == li_countColeccion))
														{
															lsb_sb.append(ll_idMatricula);
															lsb_sb.append(", ");
														}
														else
															lsb_sb.append(ll_idMatricula);
													}
												}
											}

											ls_matriculas = StringUtils.getStringNotNull(lsb_sb.toString());
										}
										else
											ls_matriculas = " ";
									}

									ls_plantilla = ls_plantilla.replace(ls_tagMatriculas, ls_matriculas);
								}
							}

							{
								String  ls_tagEntidad;
								String  ls_tagNit;
								boolean lb_tagNit;
								boolean lb_tagEntidad;
								boolean lb_tag;
								String  ls_caracter;

								ls_tag            = "<TAG_INFO_SNR>";
								ls_tagNit         = "<TAG_NIT_SNR>";
								ls_tagEntidad     = "<TAG_NOMBRE_ENTIDAD>";
								lb_tag            = ls_plantilla.contains(ls_tag);
								lb_tagEntidad     = ls_plantilla.contains(ls_tagEntidad);
								lb_tagNit         = ls_plantilla.contains(ls_tagNit);
								ls_caracter       = null;

								if(lb_tag || (lb_tagEntidad && lb_tagNit))
								{
									lc_c = new Constantes();
									lc_c.setIdConstante(ConstanteCommon.INFORMACION_SNR);

									lc_c = lc_DAO.findById(lc_c);

									if(lc_c != null)
										ls_caracter = lc_c.getCaracter();
								}

								if(lb_tag)
								{
									if(StringUtils.isValidString(ls_caracter))
									{
										ls_caracter     = ls_caracter.substring(ls_caracter.indexOf(",") + 1);

										ls_plantilla = ls_plantilla.replace(ls_tag, ls_caracter);
									}
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
								else if(lb_tagEntidad && lb_tagNit)
								{
									boolean lb_cleanTags;
									lb_cleanTags = false;

									if(StringUtils.isValidString(ls_caracter))
									{
										String[] las_as;
										las_as = ls_caracter.split(",");

										if(CollectionUtils.isValidCollection(las_as))
										{
											ls_plantilla     = ls_plantilla.replace(ls_tagEntidad, las_as[0]);
											ls_plantilla     = ls_plantilla.replace(ls_tagNit, las_as[1]);
										}
										else
											lb_cleanTags = true;
									}
									else
										lb_cleanTags = true;

									if(lb_cleanTags)
									{
										ls_plantilla     = ls_plantilla.replace(ls_tagNit, " ");
										ls_plantilla     = ls_plantilla.replace(ls_tagEntidad, " ");
									}
								}
							}

							String ls_tagCorreoElectronico;

							ls_tagCorreoElectronico = "<TAG_CORREO_ELECTRONICO>";

							if(ls_plantilla.contains(ls_tagCorreoElectronico))
							{
								PersonaCorreoElectronico lpce_pce;

								lpce_pce = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
										                 .findByPersonaSolicitud(ls_s.getIdPersona(), ls_idSolicitud);

								if(lpce_pce != null)
								{
									String ls_correoElectronico;

									ls_correoElectronico     = StringUtils.getStringNotNull(
										    lpce_pce.getCorreoElectronico()
										);

									ls_plantilla = ls_plantilla.replace(ls_tagCorreoElectronico, ls_correoElectronico);
								}
							}

							if(lb_tipoRecibo)
							{
								ls_tag = "<TAG_NUMERO_REFERENCIA>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, " ");

								ls_tag = "<TAG_DOCUMENTO_EQUIVALENTE>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, " ");

								ls_tag = "<TAG_PUNTO_EXPEDICION>";

								if(ls_plantilla.contains(ls_tag))
								{
									String          ls_puntoExpedicion;
									TipoCanalOrigen ltco_tco;

									ls_puntoExpedicion     = IdentificadoresCommon.ORIP;

									ltco_tco = DaoCreator.getTipoCanalOrigenDAO(adm_manager)
											                 .findByDescripcionCanalOrigen(ls_puntoExpedicion);

									if(ltco_tco != null)
										ls_plantilla = ls_plantilla.replace(
											    ls_tag,
											    StringUtils.getStringNotNull(ltco_tco.getDescripcionCanalOrigen())
											);

									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}

								ls_tag = "<TAG_FECHA_VENCIMIENTO>";

								if(ls_plantilla.contains(ls_tag))
								{
									Date   ld_fechaVencimiento;
									String ls_date;

									ld_fechaVencimiento     = null;
									ls_date                 = null;

									{
										Liquidacion ll_liquidacion;

										ll_liquidacion = lld_DAO.findByIdSolicitudOne(ls_idSolicitud);

										if(ll_liquidacion != null)
										{
											SimpleDateFormat lsdf_formatter;

											lsdf_formatter     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);

											ld_fechaVencimiento     = ll_liquidacion.getFechaVencimiento();
											ls_date                 = StringUtils.getStringNotNull(
												    lsdf_formatter.format(ld_fechaVencimiento)
												);
											ls_fechaVencimiento     = StringUtils.getString(
												    ld_fechaVencimiento, FormatoFechaCommon.ANIO_YYYY_MES_DIA
												);
										}
									}

									ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_date);
								}

								ls_tag = "<TAG_CIRCULOS_REGISTRALES>";

								if(ls_plantilla.contains(ls_tag))
								{
									StringBuilder ls_infoCirculos;
									ls_infoCirculos = new StringBuilder();

									if(CollectionUtils.isValidCollection(lcsm_infoCirculos))
									{
										String ls_coma;
										int    li_size;

										ls_coma     = " ";
										li_size     = lcsm_infoCirculos.size();

										if(li_size > 1)
											ls_coma = ",";

										for(SolicitudMatricula losm_tmp : lcsm_infoCirculos)
										{
											if(losm_tmp != null)
												ls_infoCirculos = ls_infoCirculos.append(
													    losm_tmp.getIdCirculo() + ls_coma
													);
										}

										if(StringUtils.isValidString(ls_infoCirculos.toString()))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_infoCirculos.toString());
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, " ");
									}
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}

								ls_tag = "<TAG_VALOR_TOTAL>";

								if(ls_plantilla.contains(ls_tag))
								{
									String      ls_idTipoMayorValor;
									boolean     lb_idTipoMayorValor;
									Liquidacion ll_liquidacion;

									ls_idTipoMayorValor     = ar_parametros.getIdTipoMayorValor();
									lb_idTipoMayorValor     = StringUtils.isValidString(ls_idTipoMayorValor);
									ll_liquidacion          = lld_DAO.findByIdTipoMayorValor(
										    ls_idSolicitud, !lb_idTipoMayorValor
										);

									if(ll_liquidacion != null)
									{
										BigDecimal lbd_valorTotal;

										lbd_valorTotal = lb_idTipoMayorValor ? ll_liquidacion.getTotalMayorValor()
											                                 : ll_liquidacion.getValorTotal();

										if(NumericUtils.isValidBigDecimal(lbd_valorTotal))
										{
											{
												String ls_tagValorLetras;

												ls_tagValorLetras     = "<TAG_RESUMEN_LIQUIDADO>";

												ls_plantilla = ls_plantilla.replace(
													    ls_tag,
													    conversionCientifica(
													        NumericUtils.getDoubleWrapper(lbd_valorTotal)
													    )
													);

												if(ls_plantilla.contains(ls_tagValorLetras))
												{
													String ls_valorEnLetras;

													ls_valorEnLetras     = NumericUtils.convertirLetras(
														    NumericUtils.getDouble(lbd_valorTotal, 0D), false
														);

													ls_plantilla = ls_plantilla.replace(
														    ls_tagValorLetras,
														    StringUtils.getStringNotNull(ls_valorEnLetras)
														    + IdentificadoresCommon.PESOS_MONEDA_CORRIENTE
														);
												}
											}

											{
												String ls_tagNotaMayorValor;

												ls_tagNotaMayorValor = TagCommon.TAG_NOTA_MAYOR_VALOR;

												if(lb_idTipoMayorValor && ls_plantilla.contains(ls_tagNotaMayorValor))
												{
													StringBuilder lsb_sb;

													lsb_sb = new StringBuilder();

													{
														Constantes lc_constante;

														lc_constante = DaoCreator.getConstantesDAO(adm_manager)
																                     .findById(
																    ConstanteCommon.NOTA_MAYOR_VALOR
																);

														if(lc_constante != null)
															lsb_sb.append(lc_constante.getCaracter());
													}

													{
														Liquidacion ll_liquidacionInicial;

														ll_liquidacionInicial = DaoCreator.getAccLiquidacionDAO(
															    adm_manager
															)
																                              .findById(
																    ll_liquidacion.getIdLiquidacionAsociada(),
																    NumericUtils.getInt(
																        ll_liquidacion.getConsecutivoAsociada()
																    )
																);

														if(ll_liquidacionInicial != null)
															lsb_sb.append(ll_liquidacionInicial.getNumeroReferencia());
													}

													{
														String ls_texto;

														ls_texto     = lsb_sb.toString();

														ls_plantilla = ls_plantilla.replace(
															    ls_tagNotaMayorValor,
															    StringUtils.isValidString(ls_texto) ? ls_texto : " "
															);
													}
												}
											}
										}
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, " ");
									}
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}

								ls_tag = "<TAG_BARCODE>";

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
										ls_textoMitadInferior     = ls_plantilla.substring(
											    li_finalTag + ls_tag.length()
											);

										ls_tagNew     = "{\\*\\bkmkstart CODIGO_DE_BARRAS}{\\*\\bkmkend CODIGO_DE_BARRAS} \\line "
											+ ls_tag;

										ls_plantilla = ls_textoMitadSuperior + " " + ls_tagNew + " "
											+ ls_textoMitadInferior;
									}
								}
							}
							else
							{
								ls_plantilla     = resolverTagsDatosPago(ls_plantilla, ls_idSolicitud, adm_manager);

								ls_tag = "<TAG_DETALLE_RECAUDO>";

								if(ls_plantilla.contains(ls_tag))
								{
									Collection<Liquidacion> lcl_detalleRecibo;
									Liquidacion             ll_l;

									ll_l = new Liquidacion();
									ll_l.setIdSolicitud(ls_idSolicitud);

									lcl_detalleRecibo = lld_DAO.detalleLiquidacion(ll_l, false);

									if(CollectionUtils.isValidCollection(lcl_detalleRecibo))
									{
										String        ls_table;
										StringBuilder lsb_sbf;

										ls_table     = "{\\trowd\\trqc\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\cellx1701\\"
											+ "clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\cellx3402\\"
											+ "clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\cellx5103\\"
											+ "clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\cellx6804\\"
											+ "clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\cellx8505\\"
											+ "clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\cellx10206\\"
											+ "clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\cellx11907\\"
											+ "clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\cellx13608\\"
											+ "clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\cellx15309"
											+ "\\intbl\\itap2\\qc\\fs12\\b{TURNO DE RADICACIÓN}\\cell"
											+ "\\intbl\\itap2\\qc{CÍRCULO- ORIP}"
											+ "\\cell\\intbl\\itap2\\qc{CODIGO}\\cell"
											+ "\\intbl\\itap2\\qc{DESCRIPCION}" + "\\cell" + "\\intbl\\itap2"
											+ "\\qc{CUANTÍA/CANTIDAD}" + "\\cell" + "\\intbl\\itap2"
											+ "\\qc{TIPO DE TARIFA}" + "\\cell\\intbl\\itap2"
											+ "\\qc{CANTIDAD DE CERTIFICADOS ASOCIADOS}" + "\\cell" + "\\intbl\\itap2"
											+ "\\qc{VALOR PAGADO}" + "\\cell\\b0" + "\\row";

										lsb_sbf = new StringBuilder(ls_table);

										for(Liquidacion lol_tmp : lcl_detalleRecibo)
										{
											if(lol_tmp != null)
											{
												String ls_valor;
												Double ld_valorFinal;

												ld_valorFinal     = lol_tmp.getValorFinal();
												ls_valor          = NumericUtils.isValidDouble(ld_valorFinal)
													? ld_valorFinal.toString() : "0";

												ls_table     = "\\intbl\\itap2" + "\\qc{"
													+ StringUtils.getStringNotNull(lol_tmp.getIdTurno()) + "}"
													+ "\\cell" + "\\intbl\\itap2" + "\\qc{"
													+ StringUtils.getStringNotNull(lol_tmp.getCirculosRegistrales())
													+ "}" + "\\cell" + "\\intbl\\itap2" + "\\qc{" + lol_tmp.getIdActo()
													+ "}" + "\\cell" + "\\intbl\\itap2" + "\\qc{"
													+ StringUtils.getStringNotNull(lol_tmp.getDescripcionActo()) + "}"
													+ "\\cell" + "\\intbl\\itap2" + "\\qc{" + lol_tmp.getCuantiaActo()
													+ "}" + "\\cell" + "\\intbl\\itap2" + "\\qc{" + lol_tmp.getTarifa()
													+ "}" + "\\cell" + "\\intbl\\itap2" + "\\qc{"
													+ lol_tmp.getCertificadosAsociados() + "}" + "\\cell\\intbl\\itap2"
													+ "\\qc{" + conversionCientifica(new Double(ls_valor)) + "}"
													+ "\\cell" + "\\row";

												lsb_sbf = lsb_sbf.append(ls_table);
											}
										}

										ls_table     = "\\pard\\itap0" + "}" + "";
										lsb_sbf      = lsb_sbf.append(ls_table);

										if(StringUtils.isValidString(lsb_sbf.toString()))
											ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sbf.toString());
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, " ");
									}
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}

								ls_tag = "<TAG_VALOR_TOTAL>";

								if(ls_plantilla.contains(ls_tag))
								{
									String      ls_idTipoCriterioBusqueda;
									Liquidacion ll_l;

									ls_idTipoCriterioBusqueda     = ar_parametros.getIdTipoMayorValor();
									ll_l                          = lld_DAO.findByIdTipoMayorValor(
										    ls_idSolicitud, !StringUtils.isValidString(ls_idTipoCriterioBusqueda)
										);

									if(ll_l != null)
									{
										BigDecimal lbd_bd;
										lbd_bd = ll_l.getValorTotal();

										if(NumericUtils.isValidBigDecimal(lbd_bd))
										{
											int li_i;
											li_i     = NumericUtils.getInt(lbd_bd);

											ls_plantilla = ls_plantilla.replace(
												    ls_tag, conversionCientifica(new Double(li_i))
												);

											String ls_tagTotal;
											ls_tagTotal = "<TAG_VALOR_TOTAL_LETRAS>";

											if(ls_plantilla.contains(ls_tagTotal))
											{
												String ls_valorEnLetras;
												ls_valorEnLetras     = Convertidor.convertirLetras(li_i);

												ls_plantilla = ls_plantilla.replace(
													    ls_tagTotal,
													    "Valor total :" + conversionCientifica(new Double(li_i)) + "("
													    + StringUtils.getStringNotNull(ls_valorEnLetras) + ")"
													);
											}
										}
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, " ");
									}
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							ls_tag = "<TAG_DETALLE_LIQUIDADO>";

							if(ls_plantilla.contains(ls_tag))
							{
								Collection<Liquidacion> lcl_detalleRecibo;
								Liquidacion             ll_l;
								ll_l = new Liquidacion();
								ll_l.setIdSolicitud(ls_idSolicitud);
								ll_l.setIdTurno(ar_parametros.getIdTurno());
								ll_l.setIdTipoMayorValor(
								    StringUtils.isValidString(ls_idTipoMayorValorNE) ? ls_idTipoMayorValorNE
								                                                     : ar_parametros.getIdTipoMayorValor()
								);

								lcl_detalleRecibo = lld_DAO.detalleLiquidacion(ll_l, true);

								if(CollectionUtils.isValidCollection(lcl_detalleRecibo))
								{
									StringBuilder       lsb_tablaDetalle;
									StringBuilder       lsb_tablaResumen;
									StringBuilder       lsb_filaSubtotales;
									Map<String, Double> lmsd_valoresResumen;
									double              ld_valorTotalDerechos;
									double              ld_valorTotalConservacion;
									double              ld_valorTotalLiquidado;

									lsb_tablaResumen        = new StringBuilder(
										    "{\\trowd\\trgaph144\\trqc\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth2500\\cellx1\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth2500\\cellx2\\intbl\\itap2\\qc\\tcelld\\fs12{CÍRCULO REGISTRAL}\\cell\\intbl\\itap2\\qc\\tcelld{TOTAL LIQUIDADO}\\cell\\b0  \\row"
										);
									lmsd_valoresResumen     = new LinkedHashMap<String, Double>();

									lsb_tablaDetalle              = new StringBuilder(
										    "{\\trowd\\trgaph144\\trqc\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx1\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx2\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx3\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx4\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx5\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx6\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx7\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx8\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx9\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx10\\intbl\\itap2\\qc\\tcelld\\fs12\\b{Identificador\\line del proceso}\\cell\\intbl\\itap2\\qc\\tcelld{Círculo Registral}\\cell\\intbl\\itap2\\qc\\tcelld{Nro. Matrícula o NUPRE por ORIP}\\cell\\intbl\\itap2\\qc\\tcelld{Trámite\\line o Concepto}\\cell\\intbl\\itap2\\qc\\tcelld{Tarifa}\\cell\\intbl\\itap2\\qc\\tcelld{Cuantía acto\\line / Cant. actos}\\cell\\intbl\\itap2\\qc\\tcelld{Avaluó}\\cell\\intbl\\itap2\\qc\\tcelld{Valor\\line Derechos}\\cell\\intbl\\itap2\\qc\\tcelld{Conservación\\line Documental}\\cell\\intbl\\itap2\\qc\\tcelld{Total\\line Liquidado}\\cell\\b0\\row"
										);
									lsb_filaSubtotales            = new StringBuilder(
										    "{\\trowd\\trgaph144\\trqc\\clftsWidth2\\clwWidth500\\cellx1\\clftsWidth2\\clwWidth500\\cellx2\\clftsWidth2\\clwWidth500\\cellx3\\clftsWidth2\\clwWidth500\\cellx4\\clftsWidth2\\clwWidth500\\cellx5\\clftsWidth2\\clwWidth500\\cellx6\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx7\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx8\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx9\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs\\clftsWidth2\\clwWidth500\\cellx10\\intbl\\itap2\\qc\\fs12\\b{}\\cell\\intbl\\itap2\\qc{}\\cell\\intbl\\itap2\\qc{}\\cell\\intbl\\itap2\\qc{}\\cell\\intbl\\itap2\\qc{}\\cell\\intbl\\itap2\\qc{}\\cell\\intbl\\itap2\\qc{SUBTOTALES}\\cell\\intbl\\itap2\\qc{$"
										);
									ld_valorTotalDerechos         = 0D;
									ld_valorTotalConservacion     = 0D;
									ld_valorTotalLiquidado        = 0D;

									for(Liquidacion lol_tmp : lcl_detalleRecibo)
									{
										if(lol_tmp != null)
										{
											double ld_valorConservacion;
											String ls_valorFinal;
											String ls_cuantia;
											String ls_circuloRegistral;
											String ls_idProceso;
											String ls_valorAvaluo;
											String ls_cero;
											Double ld_valorTotal;

											ls_cero     = "0";

											ls_circuloRegistral     = StringUtils.getStringNotNull(
												    lol_tmp.getCirculosRegistrales()
												);
											ls_idProceso            = StringUtils.getStringNotNull(
												    lol_tmp.getIdProceso()
												);

											{
												BigDecimal lbd_avaluo;

												lbd_avaluo     = lol_tmp.getValorAvaluo();

												ls_valorAvaluo = NumericUtils.isValidBigDecimal(lbd_avaluo)
													? lbd_avaluo.toString() : ls_cero;
											}

											{
												Double ld_valorFinal;

												ld_valorFinal = lol_tmp.getValorFinal();

												if(NumericUtils.isValidDouble(ld_valorFinal))
												{
													double ld_valorFinalP;

													ls_valorFinal     = ld_valorFinal.toString();

													ld_valorFinalP = NumericUtils.isValidDouble(ld_valorFinal)
														? ld_valorFinal.doubleValue() : 0D;

													ld_valorTotalDerechos += ld_valorFinalP;
												}
												else
													ls_valorFinal = ls_cero;
											}

											{
												BigDecimal lbd_valorDocumental;

												lbd_valorDocumental = lol_tmp.getValorDocumental();

												if(NumericUtils.isValidBigDecimal(lbd_valorDocumental))
												{
													ld_valorConservacion = lbd_valorDocumental.doubleValue();

													ld_valorTotalConservacion += ld_valorConservacion;
												}
												else
													ld_valorConservacion = 0D;
											}

											{
												BigDecimal lbd_valorTotalIterado;

												lbd_valorTotalIterado = lol_tmp.getValorTotal();

												if(NumericUtils.isValidBigDecimal(lbd_valorTotalIterado))
												{
													ld_valorTotal = NumericUtils.getDoubleWrapper(
														    lbd_valorTotalIterado
														);

													ld_valorTotalLiquidado += lbd_valorTotalIterado.doubleValue();
												}
												else
													ld_valorTotal = NumericUtils.getDoubleWrapper(0D);
											}

											ls_cuantia = lol_tmp.getCuantiaActo();

											if(!StringUtils.isValidString(ls_cuantia))
												ls_cuantia = ls_cero;

											lsb_tablaDetalle.append("\\intbl\\itap2\\fs10\\qc{");
											lsb_tablaDetalle.append(ls_idProceso);
											lsb_tablaDetalle.append(IdentificadoresCommon.FORMATO_CELDA_RECIBO_LIQ);
											lsb_tablaDetalle.append(ls_circuloRegistral);
											lsb_tablaDetalle.append("}\\cell\\intbl\\itap2\\q\\fs10\\qc{");
											lsb_tablaDetalle.append(lol_tmp.getNumeroMatriculas());
											lsb_tablaDetalle.append(IdentificadoresCommon.FORMATO_CELDA_RECIBO_LIQ);
											lsb_tablaDetalle.append(lol_tmp.getTramite());
											lsb_tablaDetalle.append(IdentificadoresCommon.FORMATO_CELDA_RECIBO_LIQ);
											lsb_tablaDetalle.append(lol_tmp.getTarifa());
											lsb_tablaDetalle.append(IdentificadoresCommon.FORMATO_CELDA_RECIBO_LIQ);

											if(
											    ls_idProceso.equalsIgnoreCase(
												        IdentificadoresCommon.PROCESO_CERTIFICADOS
												    )
											)
												lsb_tablaDetalle.append(lol_tmp.getCertificadosAsociados());
											else
											{
												Long ll_cantidad;
												ll_cantidad = lol_tmp.getCantidadActos();

												if(NumericUtils.getLong(ll_cantidad) <= 0)
													ll_cantidad = lol_tmp.getCertificadosAsociados();

												lsb_tablaDetalle.append(
												    (StringUtils.getStringNotNull(lol_tmp.getRequiereCuantia())
													                .equals(EstadoCommon.N)) ? ll_cantidad
												                                             : ("$"
												    + conversionCientifica(NumericUtils.getDoubleWrapper(ls_cuantia)))
												);
											}

											lsb_tablaDetalle.append(
											    IdentificadoresCommon.FORMATO_CELDA_PESOS_RECIBO_LIQ
											);
											lsb_tablaDetalle.append(
											    conversionCientifica(NumericUtils.getDoubleWrapper(ls_valorAvaluo))
											);
											lsb_tablaDetalle.append(
											    IdentificadoresCommon.FORMATO_CELDA_PESOS_RECIBO_LIQ
											);
											lsb_tablaDetalle.append(
											    conversionCientifica(NumericUtils.getDoubleWrapper(ls_valorFinal))
											);
											lsb_tablaDetalle.append(
											    IdentificadoresCommon.FORMATO_CELDA_PESOS_RECIBO_LIQ
											);
											lsb_tablaDetalle.append(
											    conversionCientifica(
											        NumericUtils.getDoubleWrapper(ld_valorConservacion)
											    )
											);
											lsb_tablaDetalle.append(
											    IdentificadoresCommon.FORMATO_CELDA_PESOS_RECIBO_LIQ
											);
											lsb_tablaDetalle.append(conversionCientifica(ld_valorTotal));
											lsb_tablaDetalle.append("}\\cell\\row");

											if(lmsd_valoresResumen.containsKey(ls_circuloRegistral))
											{
												double ld_valorTmp;
												double ld_valorTotalP;

												ld_valorTmp        = NumericUtils.getDouble(
													    lmsd_valoresResumen.get(ls_circuloRegistral)
													);
												ld_valorTotalP     = NumericUtils.getDouble(ld_valorTotal);

												lmsd_valoresResumen.replace(
												    ls_circuloRegistral,
												    NumericUtils.getDoubleWrapper(ld_valorTmp + ld_valorTotalP)
												);
											}
											else
												lmsd_valoresResumen.put(ls_circuloRegistral, ld_valorTotal);
										}
									}

									{
										String ls_tagResumen;

										ls_tagResumen = "<TAG_RESUMEN_ORIP>";

										if(ls_plantilla.contains(ls_tagResumen))
										{
											if(CollectionUtils.isValidCollection(lmsd_valoresResumen))
											{
												for(Map.Entry<String, Double> lmesd_data : lmsd_valoresResumen.entrySet())
												{
													if(lmesd_data != null)
													{
														lsb_tablaResumen.append("\\intbl\\itap2\\fs10\\qc{");
														lsb_tablaResumen.append(lmesd_data.getKey());
														lsb_tablaResumen.append(
														    IdentificadoresCommon.FORMATO_CELDA_PESOS_RECIBO_LIQ
														);
														lsb_tablaResumen.append(
														    conversionCientifica(lmesd_data.getValue())
														);
														lsb_tablaResumen.append("}\\cell\\row");
													}
												}
											}

											lsb_tablaResumen.append("\\pard\\itap0}");

											ls_plantilla = ls_plantilla.replace(
												    ls_tagResumen, lsb_tablaResumen.toString()
												);
										}
									}

									lsb_tablaDetalle.append("\\pard\\itap0}");

									lsb_filaSubtotales.append(
									    conversionCientifica(NumericUtils.getDoubleWrapper(ld_valorTotalDerechos))
									);
									lsb_filaSubtotales.append("}\\cell\\intbl\\itap2\\qc{$");
									lsb_filaSubtotales.append(
									    conversionCientifica(NumericUtils.getDoubleWrapper(ld_valorTotalConservacion))
									);
									lsb_filaSubtotales.append("}\\cell\\intbl\\itap2\\qc{$");
									lsb_filaSubtotales.append(
									    conversionCientifica(NumericUtils.getDoubleWrapper(ld_valorTotalLiquidado))
									);
									lsb_filaSubtotales.append("}\\cell\\b0\\row");
									lsb_filaSubtotales.append("\\pard\\itap0}");

									lsb_tablaDetalle.append(lsb_filaSubtotales.toString());

									ls_plantilla = ls_plantilla.replace(ls_tag, lsb_tablaDetalle.toString());
								}
							}

							lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

							if(StringUtils.isValidString(ls_plantilla))
							{
								String ls_code;
								ls_code                   = " ";
								lba_reciboLiquidacion     = new PDFGenerator().convertirRTFToPDF(
									    ls_plantilla.getBytes(), adm_manager
									);

								String ls_error;
								ls_error                  = lb_tipoRecibo
									? ErrorKeys.ERROR_GENERANDO_RECIBO_LIQUIDACION : ErrorKeys.ERROR_GENERANDO_RECIBO_CAJA;

								if(lba_reciboLiquidacion == null)
									throw new B2BException(ls_error);

								if(lb_tipoRecibo)
								{
									if(lb_condicion)
									{
										lc_c = new Constantes();
										lc_c.setIdConstante(ConstanteCommon.CODIGO_RECAUDO_GS1);

										lc_c = lc_DAO.findById(lc_c);

										if(lc_c != null)
										{
											Liquidacion ll_l;
											ll_l = new Liquidacion();
											ll_l.setIdSolicitud(ls_idSolicitud);
											ll_l.setConsecutivo(as_userId.equalsIgnoreCase("CORE_BACHUE") ? 1 : 0);

											ll_l = lld_DAO.findById(ll_l, true);

											if(
											    (ll_l != null) && StringUtils.isValidString(ls_fechaVencimiento)
												    && StringUtils.isValidString(ls_numeroReferencia)
											)
												ls_code = lc_c.getCaracter() + ls_numeroReferencia
													+ ll_l.getValorTotal() + ls_fechaVencimiento;
										}
									}

									{
										byte[]        lb_imageInByte;
										int           li_width;
										int           li_height;
										BufferedImage lobi_bufferedImage;
										JBarcodeBean  lbc_barcode;

										lbc_barcode     = new JBarcodeBean();
										li_width        = 150;
										li_height       = 50;

										lbc_barcode.setCodeType(new Code128());
										lbc_barcode.setCode(ls_code);
										lbc_barcode.setCheckDigit(false);

										lc_c = new Constantes();
										lc_c.setIdConstante(ConstanteCommon.TAMANIO_CODIGO_BARRAS_LIQUIDACION);

										lc_c = lc_DAO.findById(lc_c);

										if(lc_c != null)
										{
											String ls_caracter;

											ls_caracter = lc_c.getCaracter();

											if(StringUtils.isValidString(ls_caracter))
											{
												if(ls_caracter.contains(IdentificadoresCommon.SIMBOLO_COMA_TXT))
												{
													String[] las_as;

													las_as = ls_caracter.split(IdentificadoresCommon.SIMBOLO_COMA_TXT);

													if(las_as != null)
													{
														li_width      = NumericUtils.getInt(las_as[0]);
														li_height     = NumericUtils.getInt(las_as[1]);
													}
												}
											}
										}

										lobi_bufferedImage = lbc_barcode.draw(
											    new BufferedImage(li_width, li_height, BufferedImage.TYPE_INT_RGB)
											);

										ByteArrayOutputStream lbop_baos = new ByteArrayOutputStream();
										ImageIO.write(lobi_bufferedImage, ExtensionCommon.PNG, lbop_baos);
										lbop_baos.flush();
										lb_imageInByte = lbop_baos.toByteArray();
										lbop_baos.close();

										lba_reciboLiquidacion = getFirmaMasivaBusiness()
												                        .reemplazarBookmarsFirma(
												    lba_reciboLiquidacion, lb_imageInByte, 10, -10
												);
									}
								}

								lr_r.setPdf(lba_reciboLiquidacion);
								lr_r.setNumeroReciboCaja(ls_consecutivoGenerado);

								if(ab_b && (lba_reciboLiquidacion != null))
								{
									Imagenes li_imagen;
									long     ll_idImagen;

									li_imagen = new Imagenes();

									li_imagen.setImagenBlob(lba_reciboLiquidacion);
									li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									li_imagen.setTamano(NumericUtils.getLongWrapper(lba_reciboLiquidacion.length));
									li_imagen.setIdUsuarioCreacion(as_userId);
									li_imagen.setIpCreacion(as_remoteIp);
									li_imagen.setCodigoVerificacion(
									    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
									);

									ll_idImagen = DaoCreator.getImagenesDAO(adm_manager).insertOrUpdate(
										    li_imagen, true
										);

									if(ll_idImagen > 0)
									{
										DocumentosSalida    lds_documentoInsert;
										DocumentosSalidaDAO lds_DAO;

										lds_documentoInsert     = new DocumentosSalida();
										lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);

										lds_documentoInsert.setIdSolicitud(ls_idSolicitud);
										lds_documentoInsert.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));

										lds_documentoInsert.setIdEcm(null);

										if(!lb_tipoRecibo)
											lds_documentoInsert.setTipo(TipoArchivoCommon.RECIBO_CAJA);
										else
											lds_documentoInsert.setTipo(
											    lb_condicion ? TipoArchivoCommon.RECIBO_LIQUIDACION
											                 : TipoArchivoCommon.PRE_LIQUIDACION
											);

										if(!lb_tipoRecibo)
											lds_documentoInsert.setIdTipoDocumental(TipoDocumentalCommon.RECIBO_CAJA);
										else
											lds_documentoInsert.setIdTipoDocumental(
											    lb_condicion ? TipoDocumentalCommon.RECIBO_LIQUIDACION : null
											);

										lds_documentoInsert.setEstado(EstadoCommon.ACTIVO);
										lds_documentoInsert.setDocumentoFinal(
										    lb_tipoRecibo ? EstadoCommon.N : EstadoCommon.S
										);

										String ls_idTipoMayorValor;

										ls_idTipoMayorValor = ar_parametros.getIdTipoMayorValor();

										if(StringUtils.isValidString(ls_idTipoMayorValor))
											lds_documentoInsert.setIdTurno(ar_parametros.getIdTurno());

										if(!lb_tipoRecibo)
											lds_documentoInsert.setRepositorio(RepositorioCommon.FINAL);
										else
											lds_documentoInsert.setRepositorio(
											    lb_condicion ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
											);

										{
											DocumentosSalida lds_docTMP;

											ls_idTipoMayorValor     = ar_parametros.getIdTipoMayorValor();
											lds_docTMP              = null;

											if(
											    StringUtils.isValidString(ls_idTipoMayorValor)
												    && ls_idTipoMayorValor.equalsIgnoreCase(
												        MayorValorCommon.MAYOR_VALOR
												    )
											)
											{
												lds_documentoInsert.setEstado(EstadoCommon.ACTIVO);
												lds_documentoInsert.setIdTipoDocumental(
												    TipoDocumentalCommon.RECIBO_LIQUIDACION
												);

												{
													String ls_idTurno;

													ls_idTurno = ar_parametros.getIdTurno();

													if(StringUtils.isValidString(ls_idTurno))
														lds_documentoInsert.setIdTurno(ls_idTurno);
												}

												{
													Integer li_idTurnoHistoria;

													li_idTurnoHistoria = NumericUtils.getInteger(
														    ar_parametros.getIdTurnoHistoria()
														);

													if(NumericUtils.isValidInteger(li_idTurnoHistoria))
														lds_documentoInsert.setIdTurnoHistoria(li_idTurnoHistoria);
												}

												{
													Collection<DocumentosSalida> lcds_documentosSalida;

													lcds_documentosSalida = lds_DAO
															.findAllDocumentByTurnoHistoriaTipoDocEstado(
															    lds_documentoInsert
															);

													if(CollectionUtils.isValidCollection(lcds_documentosSalida))
														lds_docTMP = lcds_documentosSalida.iterator().next();
												}
											}
											else
												lds_docTMP = lds_DAO.findByIdSolicitudTipoDocYEstado(
													    lds_documentoInsert
													);

											if(lds_docTMP != null)
											{
												lds_documentoInsert.setIdDocumentoSalida(
												    lds_docTMP.getIdDocumentoSalida()
												);
												lds_documentoInsert.setIdUsuarioModificacion(as_userId);
												lds_documentoInsert.setIpModificacion(as_remoteIp);

												lds_DAO.insertOrUpdate(lds_documentoInsert, false);
											}
											else
											{
												long ll_idDocumentoSalida;

												lds_documentoInsert.setIdUsuarioCreacion(as_userId);
												lds_documentoInsert.setIpCreacion(as_remoteIp);

												ll_idDocumentoSalida = lds_DAO.insertOrUpdate(
													    lds_documentoInsert, true
													);

												lds_documentoInsert.setIdDocumentoSalida(ll_idDocumentoSalida);

												lr_r.setDocumentoSalida(lds_documentoInsert);
											}
										}

										if(StringUtils.isValidString(ls_consecutivoGenerado) && lb_condicion)
										{
											Liquidacion ll_liquidacion;
											boolean     lb_mayorValor;

											ll_liquidacion     = new Liquidacion();
											lb_mayorValor      = StringUtils.isValidString(ls_idTipoMayorValor)
													&& ls_idTipoMayorValor.equalsIgnoreCase(EstadoCommon.M);

											ll_liquidacion.setIdUsuarioModificacion(as_userId);
											ll_liquidacion.setIpModificacion(as_remoteIp);
											ll_liquidacion.setNumeroReferencia(ls_numeroReferencia);
											ll_liquidacion.setNumeroReciboLiquidacion(ls_consecutivoGenerado);
											ll_liquidacion.setIdSolicitud(ls_idSolicitud);

											{
												int li_consecutivo;
												int li_consectivoLiquidacion;

												li_consecutivo               = 1;
												li_consectivoLiquidacion     = ar_parametros.getConsecutivoLiquidacion();

												if(lb_mayorValor)
												{
													ll_liquidacion.setIdTurno(ar_parametros.getIdTurno());
													ll_liquidacion.setIdTipoMayorValor(ls_idTipoMayorValor);

													li_consecutivo = lld_DAO.buscarMaxConsecutivoSoliTurno(
														    ll_liquidacion
														);
												}
												else if(li_consectivoLiquidacion > 0)
													li_consecutivo = li_consectivoLiquidacion;

												ll_liquidacion.setConsecutivo(li_consecutivo);
											}

											ll_liquidacion.setConsecutivoBarcode(ls_code);

											lld_DAO.actualizarDatosReciboLiquidacion(ll_liquidacion, lb_mayorValor);
										}
									}
								}
							}
						}
					}
					else
					{
						Object[] loa_messageArgs = new String[1];
						loa_messageArgs[0] = ls_idConstante;

						throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
					}
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_idConstante;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("generarReciboLiquidacion", lb2be_e);

				throw lb2be_e;
			}
			catch(IOException lie_e)
			{
				clh_LOGGER.error("generarReciboLiquidacion", lie_e);

				throw new B2BException(ErrorKeys.CONTAINER_ERROR, lie_e);
			}
		}

		return lr_r;
	}

	/**
	 * Genera pdf de liquidación y lo guarda en la base de datos.
	 *
	 * @param ar_parametros Objeto contenedor de la información que debe ir en el pdf
	 * @param ab_b Indica si el pdf debe llevar o no el numero de referencia
	 * @param as_userId id del usuario que ejecuta el proceso
	 * @param as_localIp Dirección IP del servidor donde se recibe la petición
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @return Objeto registro contenedor del pdf generado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro generarReciboLiquidacion(
	    Registro ar_parametros, boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Registro   lr_r;
		DAOManager ldm_manager;

		lr_r            = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_parametros != null)
			{
				ar_parametros.setTipoRecibo(IdentificadoresCommon.RECIBO_LIQUIDACION);
				lr_r = generarReciboLiquidacion(ar_parametros, ab_b, as_userId, as_localIp, as_remoteIp, ldm_manager);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarReciboLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarReciboLiquidacion", le_e);
			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_r;
	}

	/**
	 * Método encargado de generar la solicitud de grabación de matrículas para correcciones.
	 *
	 * @param ar_registro Objeto que contiene la información del proceso.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return la información de la solicitud creada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public synchronized Registro generarSolicitudGrabacionMatriculasCorrecciones(
	    Registro ar_registro, String as_userId, String as_remoteIp
	)
	    throws B2BException, IOException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_registro != null)
			{
				Usuario lu_usuario;

				lu_usuario = new Usuario();

				lu_usuario.setIdUsuario(as_userId);

				lu_usuario = DaoCreator.getUsuarioDAO(ldm_manager).findById(lu_usuario);

				if(lu_usuario != null)
				{
					String ls_idTurnoHistoria;

					ls_idTurnoHistoria = ar_registro.getIdTurnoHistoria();

					if(StringUtils.isValidString(ls_idTurnoHistoria))
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
								                          .findById(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

						if(lth_turnoHistoria != null)
						{
							Persona    lp_persona;
							Persona    lp_personaTemp;
							PersonaDAO lp_DAO;
							String     ls_idPersona;

							lp_persona       = new Persona();
							lp_DAO           = DaoCreator.getPersonaDAO(ldm_manager);
							ls_idPersona     = null;

							lp_persona.setPrimerNombre(lu_usuario.getPrimerNombre());
							lp_persona.setSegundoNombre(lu_usuario.getSegundoNombre());
							lp_persona.setPrimerApellido(lu_usuario.getPrimerApellido());
							lp_persona.setSegundoApellido(lu_usuario.getSegundoApellido());
							lp_persona.setIdDocumentoTipo(lu_usuario.getIdDocumentoTipo());
							lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());

							lp_personaTemp = lp_DAO.findByDocNameMaxId(lp_persona);

							if(lp_personaTemp != null)
								ls_idPersona = lp_personaTemp.getIdPersona();
							else
							{
								lp_persona.setIdUsuarioCreacion(as_userId);
								lp_persona.setIpCreacion(as_remoteIp);

								lp_persona = lp_DAO.insertOrUpdate(lp_persona, true);

								if(lp_persona != null)
									ls_idPersona = lp_persona.getIdPersona();
							}

							if(StringUtils.isValidString(ls_idPersona))
							{
								DatosAntSistema ldas_das;

								ldas_das = ar_registro.getDatosAntSistema();

								if(ldas_das != null)
								{
									Solicitud ls_solicitud;

									ls_solicitud = new Solicitud();

									ls_solicitud.setIdProceso(ProcesoCommon.ID_PROCESO_37);
									ls_solicitud.setIdSubproceso(ProcesoCommon.ID_SUBPROCESO_3);
									ls_solicitud.setIdProcedencia(ProcedenciaCommon.ORIP_DE_ORIGEN);
									ls_solicitud.setIdAutorizacionNotificacion(MedioNotificarCommon.ORIP);
									ls_solicitud.setIdAutorizacionComunicacion(MedioNotificarCommon.ORIP);
									ls_solicitud.setIdCalidadSolicitante(CalidadSolicitanteCommon.INTERESADO);
									ls_solicitud.setParticipaInterviniente(EstadoCommon.N);
									ls_solicitud.setComentario(ar_registro.getObservacionesProceso());
									ls_solicitud.setIdTurnoAnt(lth_turnoHistoria.getIdTurno());

									ar_registro.setPersona(lp_DAO.findById(ls_idPersona));
									ar_registro.setSolicitud(ls_solicitud);
									ar_registro.setGrabacionCorrecciones(true);

									salvarInteresado(ldm_manager, ar_registro, as_userId, true);

									ldas_das.setIdSolicitud(ls_solicitud.getIdSolicitud());

									insertarDatosAntSistemaGrabacionMatricula(
									    ar_registro.getDatosAntSistema(), as_userId, as_remoteIp, ldm_manager
									);
									ar_registro = terminarProcesoGrabacionMatriculas(
										    ar_registro, false, as_userId, as_remoteIp, ldm_manager
										);

									{
										String ls_idSolicitud1;
										String ls_idSolicitud;

										ls_idSolicitud1     = ls_solicitud.getIdSolicitud();
										ls_idSolicitud      = lth_turnoHistoria.getIdSolicitud();

										if(
										    StringUtils.isValidString(ls_idSolicitud1)
											    && StringUtils.isValidString(ls_idSolicitud)
										)
										{
											SolicitudAsociada lsa_param;

											lsa_param = new SolicitudAsociada();

											lsa_param.setIdSolicitud(ls_idSolicitud);
											lsa_param.setIdSolicitud1(ls_idSolicitud1);
											lsa_param.setIdUsuarioCreacion(as_userId);
											lsa_param.setIpCreacion(as_remoteIp);

											DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
												          .insertOrUpdate(lsa_param, true);
										}
										else
											throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

										terminarTurnoHistoriaYCrearEtapa(
										    lth_turnoHistoria, ldm_manager,
										    new MotivoTramite(
										        EtapaCommon.ID_ETAPA_CORRECCIONES,
										        MotivoTramiteCommon.ENVIAR_A_GRABACION_MATRICULAS
										    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
										);
									}
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarSolicitudGrabacionMatriculasCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Guarda en base de datos la información de un predio de antiguo sistema.
	 *
	 * @param adas_param Objeto contenedor de la información a guardar
	 * @param as_userId Id del usuario que ejecuta el proceso
	 * @param as_remoteIp Dirección IP del cliente que ejecuta el proceso
	 * @param ab_guardar Flag que indica si se va a guardar o a actualizar un registro ya existente.
	 * true, para insertar y false para actualizar
	 * @return Objeto datos ant sistema resultante de la inserción o actualización en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	public synchronized DatosAntSistema guardarDatosAntSistema(
	    DatosAntSistema adas_param, String as_userId, String as_remoteIp, boolean ab_guardar
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adas_param == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(adas_param.getIdSolicitud() == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			long               ll_secuencia;
			String             ls_temp;
			String             ls_adquisicionPredio;
			DatosAntSistemaDAO ldas_DAO;

			ls_adquisicionPredio     = adas_param.getAdquisicionPredio();
			ldas_DAO                 = DaoCreator.getDatosAntSistemaDAO(ldm_manager);

			if(!StringUtils.isValidString(ls_adquisicionPredio))
				throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_ADQUISICION);

			if(ls_adquisicionPredio.equalsIgnoreCase(EstadoCommon.S))
				adas_param.setAdquisicionPredio(IdentificadoresCommon.ADQUISICION_ANT_SISTEMA);
			else if(ls_adquisicionPredio.equalsIgnoreCase(EstadoCommon.O))
				adas_param.setAdquisicionPredio(IdentificadoresCommon.ADQUISICION_DATOS_PREDIO);

			ls_temp = adas_param.getIdTipoPredio();

			if(!StringUtils.isValidString(ls_temp))
				throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);

			if(ls_adquisicionPredio.equals(EstadoCommon.O))
			{
				ls_temp = adas_param.getNombrePredio();

				if(!StringUtils.isValidString(ls_temp))
					throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_PREDIO);

				if(ls_temp.length() > 400)
					throw new B2BException(ErrorKeys.ERROR_NOMBRE_PREDIO_TAM);
			}

			ls_temp = adas_param.getIdCirculo();

			if(!StringUtils.isValidString(ls_temp))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_CIRCULO);

			ls_temp = adas_param.getIdPais();

			if(!StringUtils.isValidString(ls_temp))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

			ls_temp = adas_param.getIdDepartamento();

			if(!StringUtils.isValidString(ls_temp))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);

			ls_temp = adas_param.getIdMunicipio();

			if(!StringUtils.isValidString(ls_temp))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

			if(ab_guardar)
			{
				Collection<DatosAntSistema> lcdas_registrosInsertados;

				lcdas_registrosInsertados = ldas_DAO.findByIdSolicitudCirculo(adas_param);

				if(CollectionUtils.isValidCollection(lcdas_registrosInsertados))
					adas_param.setConsecutivoPredioAntSistema(
					    StringUtils.getString(lcdas_registrosInsertados.size() + 1)
					);
				else
					adas_param.setConsecutivoPredioAntSistema(IdentificadoresCommon.POSICION_INICIAL);

				adas_param.setIdUsuarioCreacion(as_userId);
				adas_param.setIpCreacion(as_remoteIp);

				ll_secuencia = ldas_DAO.insertUpdate(adas_param, ab_guardar);

				if(ll_secuencia <= 0)
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				adas_param.setIdDatosAntSistema(StringUtils.getString(ll_secuencia));
			}
			else
			{
				adas_param.setIdUsuarioModificacion(as_userId);
				adas_param.setIpModificacion(as_remoteIp);

				ldas_DAO.insertUpdate(adas_param, ab_guardar);

				DetalleAntSistemaDAO          ldas_detalleDAO;
				DetalleAntSistema             ldas_detalle;
				Collection<DetalleAntSistema> lcdas_detalles;

				ldas_detalleDAO     = DaoCreator.getDetalleAntSistemaDAO(ldm_manager);
				ldas_detalle        = new DetalleAntSistema();

				ldas_detalle.setIdDatosAntSistema(adas_param.getIdDatosAntSistema());

				lcdas_detalles = ldas_detalleDAO.findByDatosAntSis(ldas_detalle);

				if(CollectionUtils.isValidCollection(lcdas_detalles))
				{
					for(DetalleAntSistema ldas_data : lcdas_detalles)
					{
						if(ldas_data != null)
						{
							ldas_data.setIdMunicipioTomo(adas_param.getIdMunicipio());

							ldas_detalleDAO.insertOrUpdate(ldas_data, false);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarDatosAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adas_param;
	}

	/**
	 * Guarda en base de datos la información del detalle de un predio de antiguo sistema.
	 *
	 * @param adas_param Objeto contenedor de la información a guardar
	 * @param as_userId Id del usuario que ejecuta el proceso
	 * @param as_remoteIp Dirección IP del cliente que ejecuta el proceso
	 * @param ab_guardar Flag que indica si se va a guardar o a actualizar un registro ya existente.
	 * true, para insertar y false para actualizar
	 * @return Objeto datos ant sistema resultante de la inserción o actualización en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public synchronized DetalleAntSistema guardarDetallesAntSistema(
	    DetalleAntSistema adas_param, String as_userId, String as_remoteIp, boolean ab_guardar
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adas_param != null)
			{
				Long                 ll_idLibro;
				Long                 ll_numeroPartida;
				Long                 ll_datoValidar;
				String               ls_idLibroStr;
				String               ls_datoValidar;
				DetalleAntSistemaDAO ldas_DAO;
				DatosAntSistema      ldas_datosAnt;
				Solicitud            ls_solicitud;

				ldas_DAO          = DaoCreator.getDetalleAntSistemaDAO(ldm_manager);
				ll_idLibro        = adas_param.getIdLibroAntSistema();
				ldas_datosAnt     = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
						                          .findById(adas_param.getIdDatosAntSistema());

				if(ldas_datosAnt == null)
					throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_ANT_SISTEMA);

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ldas_datosAnt.getIdSolicitud());

				if(ls_solicitud == null)
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

				if(!NumericUtils.isValidLong(ll_idLibro, 1))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);

				ls_idLibroStr     = StringUtils.getString(ll_idLibro);

				ls_datoValidar = adas_param.getIdMunicipioTomo();

				if(!StringUtils.isValidString(ls_datoValidar))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

				ls_datoValidar = adas_param.getTomo();

				if(!StringUtils.isValidString(ls_datoValidar))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TOMO);

				ls_datoValidar = adas_param.getFolio();

				if(!StringUtils.isValidString(ls_datoValidar))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_FOLIO);

				ll_numeroPartida = adas_param.getNumeroPartida();

				if(!NumericUtils.isValidLong(ll_numeroPartida, 1))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_PARTIDA);

				if(ls_idLibroStr.equals(LibroAntSistemaCommon.LIBRO_DE_MATRICULAS))
				{
					ll_datoValidar = adas_param.getIdMatricula();

					if(!NumericUtils.isValidLong(ll_datoValidar, 1))
						throw new B2BException(ErrorKeys.ERROR_INGRESE_MATRICULA);
				}
				else
				{
					String ls_idProceso;

					ls_idProceso       = StringUtils.getStringNotNull(ls_solicitud.getIdProceso());
					ls_datoValidar     = adas_param.getPartida();

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PARTIDA);

					if(
					    ls_idProceso.equals(ProcesoCommon.ID_PROCESO_1)
						    || ls_idProceso.equals(ProcesoCommon.ID_PROCESO_6)
					)
					{
						long ll_numPartida;

						ll_numPartida = NumericUtils.getLong(ll_numeroPartida);

						if(
						    (ls_datoValidar.equals(EstadoCommon.IMPAR) && ((ll_numPartida % 2) == 0))
							    || (ls_datoValidar.equals(EstadoCommon.PAR) && ((ll_numPartida % 2) != 0))
						)
							throw new B2BException(ErrorKeys.NUMERO_PARTIDA_INVALIDO);
					}

					ls_datoValidar = adas_param.getAnio();

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ANIO);
				}

				ls_datoValidar = adas_param.getIdDatosAntSistema();

				if(!StringUtils.isValidString(ls_datoValidar))
					throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_PREDIO_REGISTRO);

				adas_param.setActivo(EstadoCommon.SI);

				if(NumericUtils.getLong(ll_idLibro) == NumericUtils.getLong(LibroAntSistemaCommon.LIBRO_SEGUNDO))
				{
					Documento ld_documentoTmp;

					{
						Documento ld_docValidaciones;

						ld_docValidaciones = adas_param.getDocumento();

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

						if(!StringUtils.isValidString(ld_docValidaciones.getIdOficinaOrigen()))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);

						ld_documentoTmp = salvarDocumento(
							    ld_docValidaciones, ls_solicitud, as_userId, as_remoteIp, ldm_manager
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

						adas_param.setDocumento(ld_documentoTmp);
						adas_param.setIdDocumentoTradicion(ls_idDocumento);
						adas_param.setVersionDocumentoTradicion(StringUtils.getString(ll_versionDoc));
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);
				}

				if(ab_guardar)
				{
					Collection<DetalleAntSistema> lcdas_detalles;
					int                           li_secuencia;

					lcdas_detalles     = ldas_DAO.findByDatosAntSis(adas_param);
					li_secuencia       = 1;

					if(CollectionUtils.isValidCollection(lcdas_detalles))
						li_secuencia += lcdas_detalles.size();

					adas_param.setIdDetalleAntSistema(StringUtils.getString(li_secuencia));
					adas_param.setIdUsuarioCreacion(as_userId);
					adas_param.setIpCreacion(as_remoteIp);

					ldas_DAO.insertOrUpdate(adas_param, ab_guardar);
				}
				else
				{
					adas_param.setIdUsuarioModificacion(as_userId);
					adas_param.setIpModificacion(as_remoteIp);

					ldas_DAO.insertOrUpdate(adas_param, ab_guardar);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarDetallesAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adas_param;
	}

	/**
	 * Guardar fecha ejecutoria acto.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param ad_fechaEjecutoria de ad fecha ejecutoria
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void guardarFechaEjecutoriaActo(
	    String as_idSolicitud, Date ad_fechaEjecutoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			guardarFechaEjecutoriaActo(ldm_manager, as_idSolicitud, ad_fechaEjecutoria, as_userId, as_remoteIp);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarFechaEjecutoriaActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de guardar las matriculas y soportes de traslado.
	 *
	 * @param actm_matriculas Contiene la información de las matrículas.
	 * @param acst_soportes Contiene la información de los soportes.
	 * @param as_solicitud Contiene la información de la solicitud.
	 * @param as_userId Contiene el id del usuario.
	 * @param as_ipRemota Contiene la ip del usuario.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public synchronized void guardarMatriculasTraslado(
	    Collection<TrasladoMatricula> actm_matriculas, Collection<SoporteTraslado> acst_soportes, Solicitud as_solicitud,
	    String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			String ls_idSubproceso;

			ls_idSubproceso = as_solicitud.getIdSubproceso();

			if(StringUtils.isValidString(ls_idSubproceso))
			{
				boolean lb_proyectarResolucion;

				lb_proyectarResolucion = ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3);

				if(CollectionUtils.isValidCollection(actm_matriculas) || lb_proyectarResolucion)
				{
					SolicitudDAO ls_DAO;
					String       ls_comentario;

					ls_DAO            = DaoCreator.getSolicitudDAO(ldm_manager);
					ls_comentario     = as_solicitud.getComentario();
					as_solicitud      = ls_DAO.findById(as_solicitud);

					if(as_solicitud != null)
					{
						TrasladoMatriculaDAO ltm_DAO;
						String               ls_idSolicitud;

						ltm_DAO            = DaoCreator.getTrasladoMatriculaDAO(ldm_manager);
						ls_idSolicitud     = as_solicitud.getIdSolicitud();

						as_solicitud.setIdSubproceso(ls_idSubproceso);
						as_solicitud.setComentario(ls_comentario);

						ls_DAO.update(as_solicitud);
						ltm_DAO.deleteByIdSolicitud(ls_idSolicitud);

						if(CollectionUtils.isValidCollection(actm_matriculas))
						{
							for(TrasladoMatricula ltm_iterador : actm_matriculas)
							{
								if(ltm_iterador != null)
								{
									Long   ll_idMatricula;
									String ls_idCirculoOrigen;
									String ls_idCirculoDestino;

									ll_idMatricula          = ltm_iterador.getIdMatriculaOrigen();
									ls_idCirculoOrigen      = ltm_iterador.getIdCirculoOrigen();
									ls_idCirculoDestino     = ltm_iterador.getIdCirculoDestino();

									if(!StringUtils.isValidString(ls_idCirculoOrigen))
										throw new B2BException(ErrorKeys.ERROR_CIRCULO_ORIGEN);

									if(!StringUtils.isValidString(ls_idCirculoDestino))
										throw new B2BException(ErrorKeys.ERROR_CIRCULO_DESTINO);

									if(!NumericUtils.isValidLong(ll_idMatricula))
										throw new B2BException(ErrorKeys.ERROR_NUMERO_MATRICULA_NO_VALIDO);

									ltm_iterador.setIdSolicitud(ls_idSolicitud);
									ltm_iterador.setIdUsuarioCreacion(as_userId);
									ltm_iterador.setIpCreacion(as_ipRemota);
									ltm_iterador.setActivo(EstadoCommon.S);

									ltm_DAO.insert(ltm_iterador);
								}
							}
						}

						if(CollectionUtils.isValidCollection(acst_soportes))
						{
							Iterator<SoporteTraslado> list_iterator;

							list_iterator = acst_soportes.iterator();

							if(list_iterator != null)
							{
								boolean            lb_continuar;
								SoporteTrasladoDAO lst_DAO;

								lb_continuar     = true;
								lst_DAO          = DaoCreator.getSoporteTrasladoDAO(ldm_manager);

								lst_DAO.deleteByIdSolicitud(ls_idSolicitud);

								while(list_iterator.hasNext() && lb_continuar)
								{
									SoporteTraslado lst_iterador;

									lst_iterador = list_iterator.next();

									if(lst_iterador != null)
									{
										Documento ld_documento;

										ld_documento = new Documento();

										ld_documento.setNumero(lst_iterador.getNumero());
										ld_documento.setIdTipoDocumento(lst_iterador.getIdTipoDocumento());
										ld_documento.setFechaDocumento(lst_iterador.getFechaDocumento());
										ld_documento.setIdTipoOficina(lst_iterador.getIdTipoOficina());
										ld_documento.setIdOficinaOrigen(lst_iterador.getIdOficinaOrigen());
										ld_documento.setIdPais(lst_iterador.getIdPais());
										ld_documento.setIdDepartamento(lst_iterador.getIdDepartamento());
										ld_documento.setIdMunicipio(lst_iterador.getIdMunicipio());

										salvarDocumento(
										    ld_documento, as_solicitud, as_userId, as_ipRemota, ldm_manager
										);

										lst_iterador.setIdSolicitud(ls_idSolicitud);
										lst_iterador.setIdUsuarioCreacion(as_userId);
										lst_iterador.setIpCreacion(as_ipRemota);

										lst_DAO.insert(lst_iterador);

										lb_continuar = false;
									}
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_MATRICULAS_TRASLADO);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarMatriculasTraslado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para guardar Testamento.
	 *
	 * @param at_testamento Testamento a guardar en la tabla SDB_ACC_TESTAMENTO
	 * @param as_userId con el id de la modificacion o la insercion
	 * @param as_ipRemota con el id de la ip remota de la actualizaciónj o inserción
	 * @return el valor de testamento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Testamento guardarTestamento(
	    SolicitudTestamento at_testamento, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Testamento lt_testamentoDev;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		lt_testamentoDev     = null;

		try
		{
			if(at_testamento != null)
			{
				TestamentoDAO ldd_DAO;
				Testamento    lt_testamento;

				ldd_DAO           = DaoCreator.getTestamentoDAO(ldm_manager);
				lt_testamento     = at_testamento.getTestamento();

				if(lt_testamento != null)
				{
					String ls_idTestamento;

					lt_testamento.setIdUsuarioCreacion(as_userId);
					lt_testamento.setIpCreacion(as_ipRemota);

					ls_idTestamento = StringUtils.getString(ldd_DAO.insert(lt_testamento));

					if(StringUtils.isValidString(ls_idTestamento))
					{
						Solicitud ls_solicitud;
						Documento ld_documento;

						ls_solicitud = at_testamento.getSolicitud();
						ls_solicitud.setIdTestamento(ls_idTestamento);

						ld_documento = at_testamento.getDocumento();

						if((ls_solicitud != null) && (ld_documento != null))
							salvarDocumento(ld_documento, ls_solicitud, as_userId, as_ipRemota, ldm_manager);
					}
				}

				lt_testamentoDev = lt_testamento;
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarTestamento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lt_testamentoDev;
	}

	/**
	 * Método encargado de guardar los tipos documentales por cada acto de un circulo y solicitud especificada.
	 *
	 * @param at_turno Variable de tipo Turno con la información a guardar
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_remoteIp de as remote ip
	 * @return el valor de turno
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Turno guardarTiposDocImpuestoGobernacion(Turno at_turno, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(at_turno != null)
			{
				Collection<TipoDocumental> lctd_tiposDoc;

				lctd_tiposDoc = at_turno.getActosui();

				if(CollectionUtils.isValidCollection(lctd_tiposDoc))
				{
					Acto             la_actoTMP;
					Collection<Acto> lca_actosAValidar;

					la_actoTMP = new Acto();
					la_actoTMP.setIdSolicitud(at_turno.getIdSolicitud());
					la_actoTMP.setIdCirculo(at_turno.getIdCirculo());

					lca_actosAValidar = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudCirculo(la_actoTMP);

					if(CollectionUtils.isValidCollection(lca_actosAValidar))
					{
						for(Acto la_iterador : lca_actosAValidar)
						{
							if(la_iterador != null)
							{
								for(TipoDocumental ltd_tipoDoc : lctd_tiposDoc)
								{
									if(ltd_tipoDoc != null)
									{
										String  ls_medioRecepcion;
										boolean lb_seleccionado;
										String  ls_idTipoDoc;

										lb_seleccionado       = ltd_tipoDoc.isSeleccionado();
										ls_medioRecepcion     = ltd_tipoDoc.getMedioRecepcion();
										ls_idTipoDoc          = ltd_tipoDoc.getIdTipoDocumental();

										if(StringUtils.isValidString(ls_idTipoDoc))
										{
											if(lb_seleccionado && !StringUtils.isValidString(ls_medioRecepcion))
												throw new B2BException(ErrorKeys.ERROR_SIN_MEDIO_RECEPCION);

											if(ls_idTipoDoc.equalsIgnoreCase(TipoDocumentalCommon.BOLETA_FISCAL))
											{
												la_iterador.setNumeroBoletaFiscal(ltd_tipoDoc.getNumeroBoletaFiscal());
												la_iterador.setNumeroBoletaFiscalExt(
												    ltd_tipoDoc.getNumeroBoletaFiscalExt()
												);
												la_iterador.setFechaPagoImpuesto(ltd_tipoDoc.getFechaPagoImpuesto());
												la_iterador.setFechaPagoImpuestoExtemporaneo(
												    ltd_tipoDoc.getFechaPagoImpuestoExtemporaneo()
												);

												la_iterador.setIdUsuarioModificacion(as_userId);
												la_iterador.setIpModificacion(as_remoteIp);

												DaoCreator.getActoDAO(ldm_manager).update(la_iterador);
											}

											{
												AccCompletitudDocumental lacd_documental;
												String                   ls_idTurnoCertificado;
												String                   ls_observaciones;
												String                   ls_idSolicitud;
												String                   ls_idTipoDocumental;

												lacd_documental           = new AccCompletitudDocumental();
												ls_idTurnoCertificado     = ltd_tipoDoc
														.getIdTurnoCertificadoCorrecciones();
												ls_observaciones          = ltd_tipoDoc.getObservaciones();
												ls_idSolicitud            = la_iterador.getIdSolicitud();
												ls_idTipoDocumental       = ltd_tipoDoc.getIdTipoDocumental();

												if(StringUtils.isValidString(ls_idTurnoCertificado))
												{
													if(StringUtils.isValidString(ls_idSolicitud))
														turnoCertificado(
														    ls_idTurnoCertificado, ls_idSolicitud, true, ldm_manager
														);
													else
														throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);
												}

												if(StringUtils.isValidString(ls_observaciones))
												{
													if(ls_observaciones.length() > 200)
														throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM);
												}

												lacd_documental.setIdSolicitud(ls_idSolicitud);
												lacd_documental.setIdActo(la_iterador.getIdActo());
												lacd_documental.setIdTipoActo(la_iterador.getIdTipoActo());
												lacd_documental.setIdTipoDocumental(ls_idTipoDocumental);
												lacd_documental.setIdTurnoCertificadoCorrecciones(
												    ls_idTurnoCertificado
												);
												lacd_documental.setObservaciones(ls_observaciones);
												lacd_documental.setObligatorio(
												    ltd_tipoDoc.isSeleccionado() ? EstadoCommon.S : EstadoCommon.N
												);
												lacd_documental.setObligatorioTipoDocumental(EstadoCommon.S);
												lacd_documental.setIdUsuarioCreacion(as_userId);
												lacd_documental.setIpCreacion(as_remoteIp);
												lacd_documental.setIdUsuarioModificacion(as_userId);
												lacd_documental.setIpModificacion(as_remoteIp);

												if(ls_idTipoDoc.equalsIgnoreCase(TipoDocumentalCommon.BOLETA_FISCAL))
												{
													Collection<AccCompletitudDocumental> lcacd_completitud;
													AccCompletitudDocumental             lacd_completitud;
													lacd_completitud = new AccCompletitudDocumental();

													lacd_completitud.setIdSolicitud(ls_idSolicitud);
													lacd_completitud.setIdActo(la_iterador.getIdActo());
													lacd_completitud.setIdTipoActo(la_iterador.getIdTipoActo());
													lacd_completitud.setIdTipoDocumental(ls_idTipoDoc);

													lcacd_completitud = DaoCreator.getAccCompletitudDocumentalDAO(
														    ldm_manager
														).findByActoSolicitudTipoDocumental(lacd_completitud);

													if(CollectionUtils.isValidCollection(lcacd_completitud))
													{
														Iterator<AccCompletitudDocumental> liacd_tmp;
														liacd_tmp = lcacd_completitud.iterator();

														if(liacd_tmp.hasNext())
														{
															AccCompletitudDocumental lacd_completitudTMP;
															lacd_completitudTMP = liacd_tmp.next();

															lacd_documental.setIdCompletitud(
															    lacd_completitudTMP.getIdCompletitud()
															);

															DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
																          .update(lacd_documental);
														}
													}
												}
												else
												{
													lacd_documental.setDigitalizado(EstadoCommon.N);

													DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
														          .insert(lacd_documental);
												}
											}
										}
									}
								}
							}
						}
					}
				}

				at_turno.setEsBloquearTiposDoc(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarTiposDocImpuestoGobernacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return at_turno;
	}

	/**
	 * Método encargado de guardar los tipos documentales.
	 *
	 * @param as_idSolicitud Variable de tipo String que contiene el id de la solicitud.
	 * @param acctd_td Colección que contiene los tipos documentales que se van a guardar.
	 * @param alid_acto Variable de tipo String que contiene el id del acto.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_codigo Variable de tipo String que contiene el codigo del acto.
	 * @param ab_procesoCorrecciones Argumento de tipo <code>boolean</code> que determina si es un proceso de correcciones.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarTiposDocumentales(
	    String as_idSolicitud, Collection<TipoDocumental> acctd_td, String alid_acto, String as_userId, String as_codigo,
	    boolean ab_procesoCorrecciones
	)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		TipoActoDAO lotad_tad;
		boolean     lb_b;
		lb_b     = false;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acctd_td) && StringUtils.isValidString(as_idSolicitud))
			{
				int ls_secuence;
				lotad_tad = DaoCreator.getTipoActoDAO(ldm_manager);

				/* DELETE TIPOS DOCUMENTALES ASOCIADOS AL ACTO */
				lotad_tad.deleteActoCompletitud(as_idSolicitud, alid_acto, as_codigo);

				for(TipoDocumental lotd_td : acctd_td)
				{
					String ls_medioRecepcion;
					ls_medioRecepcion = lotd_td.getMedioRecepcion();

					boolean lb_noAportoImpuestoGob;
					lb_noAportoImpuestoGob = false;

					if(StringUtils.isValidString(alid_acto) && StringUtils.isValidString(as_codigo))
					{
						TipoActo lta_tipoActo;
						lta_tipoActo = DaoCreator.getTipoActoDAO(ldm_manager).findById(as_codigo);

						if(lta_tipoActo != null)
						{
							String ls_impuestoRegistro;
							ls_impuestoRegistro = lta_tipoActo.getImpuestoRegistro();

							if(
							    StringUtils.isValidString(ls_impuestoRegistro)
								    && ls_impuestoRegistro.equalsIgnoreCase(EstadoCommon.S)
							)
							{
								Acto la_acto;
								la_acto = DaoCreator.getActoDAO(ldm_manager).findById(alid_acto);

								if(la_acto != null)
								{
									String ls_numeroBoletaFiscal;
									ls_numeroBoletaFiscal = la_acto.getNumeroBoletaFiscal();

									if(ls_numeroBoletaFiscal == null)
										lb_noAportoImpuestoGob = true;
								}
							}
						}
					}

					if(!lotd_td.isSeleccionado())
					{
						lb_b = lotad_tad.findObligatoriedad(lotd_td.getIdTipoDocumental(), as_codigo);

						if(
						    (lb_b && !StringUtils.isValidString(lotd_td.getObservaciones()))
							    || !StringUtils.isValidString(lotd_td.getObservaciones())
						)
							throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_DOCUMENTO_FALTANTE);
					}
					else if(
					    !StringUtils.isValidString(ls_medioRecepcion) && !lb_noAportoImpuestoGob
						    && !ab_procesoCorrecciones
					)
						throw new B2BException(ErrorKeys.ERROR_SIN_MEDIO_RECEPCION);

					ls_secuence = lotad_tad.findSecuenceCompletitud();

					if((ls_secuence > 0))
					{
						lotd_td.setSecuence(StringUtils.getString(ls_secuence));
						lotd_td.setIdUsuarioCreacion(as_userId);
						lotad_tad.guardarTiposDocumentales(as_idSolicitud, lotd_td, alid_acto, as_codigo);
					}
				}
			}

			else
				throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_VALIDOS_INSERCION);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarTiposDocumentales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

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
	public synchronized void guardarTiposDocumentales(
	    Collection<AccCompletitudDocumental> aci_completitudDocumental, Solicitud aso_solicitud,
	    String as_constanteApoderado, String as_constanteTercero, String as_localIp, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			guardarTiposDocumentales(
			    ldm_manager, aci_completitudDocumental, aso_solicitud, null, null, as_constanteApoderado,
			    as_constanteTercero, as_localIp, as_remoteIp, as_userId
			);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarTiposDocumentales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Ingresar intervinientes solicitud masivo.
	 *
	 * @param acsi_intervinientes correspondiente al valor del tipo de objeto Collection<SolicitudInterviniente>
	 * @param as_solicitud correspondiente al valor del tipo de objeto Solicitud
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void ingresarIntervinientesSolicitudMasivo(
	    Collection<SolicitudInterviniente> acsi_intervinientes, Solicitud as_solicitud, String as_userId,
	    String                             as_localIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			SolicitudIntervinienteDAO lsd_solicitudDAO;
			lsd_solicitudDAO = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager);

			if(as_solicitud != null)
			{
				for(SolicitudInterviniente lsi_actual : acsi_intervinientes)
				{
					if(lsi_actual != null)
					{
						Persona lp_persona;

						lp_persona = lsi_actual.getPersona();

						if(lp_persona != null)
						{
							String ls_idPersona;
							String ls_idSolicitud;
							String ls_rol;

							ls_idPersona       = lp_persona.getIdPersona();
							ls_idSolicitud     = as_solicitud.getIdSolicitud();
							ls_rol             = lsi_actual.getRolPersona();

							if(
							    StringUtils.isValidString(ls_idPersona) && StringUtils.isValidString(ls_idSolicitud)
								    && StringUtils.isValidString(ls_rol)
							)
							{
								if(ls_rol.equalsIgnoreCase(RolCommon.DE))
									ls_rol = EstadoCommon.De;
								else if(ls_rol.equalsIgnoreCase(RolCommon.A))
									ls_rol = RolCommon.A;

								lsi_actual.setIdPersona(ls_idPersona);
								lsi_actual.setIdSolicitud(ls_idSolicitud);
								lsi_actual.setRolPersona(ls_rol);
								lsi_actual.setIdUsuarioCreacion(as_userId);
								lsi_actual.setIpCreacion(as_localIp);

								if(lsd_solicitudDAO.findBySolicitudPersonaRol(lsi_actual) == null)
									lsd_solicitudDAO.insertOrUpdate(lsi_actual, true);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarActoParaAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de insertar un DatosAntSistema en la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 *
	 * @param ldas_datosAntSistema Objeto que contiene la información del registro que se va a insertar.
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_ipRemota correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertarDatosAntSistemaGrabacionMatricula(
	    DatosAntSistema ldas_datosAntSistema, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			insertarDatosAntSistemaGrabacionMatricula(ldas_datosAntSistema, as_userId, as_ipRemota, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertarDatosAntSistemaGrabacionMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Retorna el valor del objeto de Registro.
	 *
	 * @param aci_imagenes correspondiente al valor del tipo de objeto Collection<Imagenes>
	 * @param aso_solicitud correspondiente al valor del tipo de objeto Solicitud
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro insertarImagenesConsultaIndices(
	    Collection<Imagenes> aci_imagenes, Solicitud aso_solicitud, String as_localIp, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_registro;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_registro     = new Registro();

		try
		{
			if((aso_solicitud != null) && CollectionUtils.isValidCollection(aci_imagenes))
			{
				String                      ls_idSolicitud;
				DocumentosSalidaDAO         lds_DAO;
				ImagenesDAO                 li_DAO;
				AccCompletitudDocumentalDAO lacdd_DAO;

				ls_idSolicitud     = aso_solicitud.getIdSolicitud();
				lds_DAO            = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
				li_DAO             = DaoCreator.getImagenesDAO(ldm_manager);
				lacdd_DAO          = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);

				{
					AccCompletitudDocumental lacd_completitudDocumental;

					lacd_completitudDocumental = new AccCompletitudDocumental();

					lacd_completitudDocumental.setIdSolicitud(ls_idSolicitud);

					lacdd_DAO.deleteByIdSolicitud(lacd_completitudDocumental);
				}

				{
					DocumentosSalida             lds_documentosSalida;
					Collection<DocumentosSalida> lcds_documentosPorSolicitud;

					lds_documentosSalida = new DocumentosSalida();

					lds_documentosSalida.setIdSolicitud(ls_idSolicitud);
					lds_documentosSalida.setEstado(EstadoCommon.ENTREGA);

					lcds_documentosPorSolicitud = lds_DAO.findByIdSolicitudEstado(lds_documentosSalida);

					if(CollectionUtils.isValidCollection(lcds_documentosPorSolicitud))
					{
						for(DocumentosSalida lds_iterador : lcds_documentosPorSolicitud)
						{
							if(lds_iterador != null)
							{
								Imagenes li_imagenes;

								li_imagenes = new Imagenes();

								li_imagenes.setIdImagen(NumericUtils.getInt(lds_iterador.getIdImagen()));

								lds_DAO.deleteByIdImagen(lds_iterador);
								li_DAO.deleteById(li_imagenes);
							}
						}
					}
				}

				for(Imagenes lim_iterador : aci_imagenes)
				{
					if(lim_iterador != null)
					{
						byte[] lba_archivo;
						String ls_tipoDocumental;
						String ls_observaciones;
						long   ll_idDocumentoSalida;

						lba_archivo              = lim_iterador.getImagenBlob();
						ls_tipoDocumental        = lim_iterador.getTipoDocumental();
						ls_observaciones         = lim_iterador.getObservaciones();
						ll_idDocumentoSalida     = 0;

						if(lba_archivo != null)
						{
							long ll_idImagen;

							lim_iterador.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							lim_iterador.setIdUsuarioCreacion(as_userId);
							lim_iterador.setIpCreacion(as_remoteIp);

							ll_idImagen = li_DAO.insertOrUpdate(lim_iterador, true);

							if(ll_idImagen <= 0L)
								throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

							{
								DocumentosSalida lds_documento;

								lds_documento = new DocumentosSalida();

								lds_documento.setIdSolicitud(ls_idSolicitud);
								lds_documento.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
								lds_documento.setObservaciones(ls_observaciones);
								lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								lds_documento.setTipo(TipoArchivoCommon.SOPORTE_CONSULTA);
								lds_documento.setIdTipoDocumental(ls_tipoDocumental);
								lds_documento.setEstado(EstadoCommon.ENTREGA);
								lds_documento.setIdUsuarioCreacion(as_userId);
								lds_documento.setIpCreacion(as_remoteIp);

								ll_idDocumentoSalida = lds_DAO.insertOrUpdate(lds_documento, true);

								if(ll_idDocumentoSalida <= 0)
									throw new B2BException(ErrorKeys.SIN_SECUENCIA_DIRECCION_PREDIO);
							}
						}

						{
							AccCompletitudDocumental lacd_documental;

							lacd_documental = new AccCompletitudDocumental();

							lacd_documental.setIdSolicitud(ls_idSolicitud);
							lacd_documental.setIdTipoDocumental(ls_tipoDocumental);
							lacd_documental.setObservaciones(ls_observaciones);
							lacd_documental.setObligatorioTipoDocumental(EstadoCommon.N);
							lacd_documental.setIdDocumentoSalida(
							    (ll_idDocumentoSalida > 0) ? NumericUtils.getBigInteger(ll_idDocumentoSalida) : null
							);
							lacd_documental.setIdUsuarioCreacion(as_userId);
							lacd_documental.setIpCreacion(as_remoteIp);
							lacd_documental.setDigitalizado(EstadoCommon.N);

							lacdd_DAO.insert(lacd_documental);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("terminarProcesoRecepcion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_registro;
	}

	/**
	 * Método encargado de insertar un interviniente en la tabla SDB_ACC_SOLICITUD_INTERVINIENTE.
	 *
	 * @param acsi_interviniente Objeto que contiene la información del interviniente que se va a insertar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertarIntervinienteCopia(SolicitudInterviniente acsi_interviniente)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acsi_interviniente != null)
			{
				SolicitudIntervinienteDAO lsi_DAO;

				lsi_DAO = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager);

				lsi_DAO.insertOrUpdate(acsi_interviniente, true);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertarIntervinienteCopia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de limpiar los registros ingresados en la pantalla de registro/datos del predio.
	 *
	 * @param as_solicitud Objeto que contiene la información de la solicitud sobre la cual se está trabajando.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void limpiarDatosDelPredio(Solicitud as_solicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_solicitud != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = as_solicitud.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					SolicitudMatricula        lsm_solicitudMatricula;
					SolicitudMatriculaDAO     lsm_DAO;
					SolicitudMatriculaActo    lsma_solicitudMatriculaActo;
					SolicitudMatriculaActoDAO lsma_DAO;
					MatriculaSegregacion      lms_matriculaSegregacion;
					MatriculaSegregacionDAO   lms_DAO;

					lsm_solicitudMatricula          = new SolicitudMatricula();
					lsm_DAO                         = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
					lsma_solicitudMatriculaActo     = new SolicitudMatriculaActo();
					lsma_DAO                        = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);
					lms_matriculaSegregacion        = new MatriculaSegregacion();
					lms_DAO                         = DaoCreator.getMatriculaSegregacionDAO(ldm_manager);

					lsm_solicitudMatricula.setIdSolicitud(ls_idSolicitud);
					lsma_solicitudMatriculaActo.setIdSolicitud(ls_idSolicitud);
					lms_matriculaSegregacion.setIdSolicitud(ls_idSolicitud);

					lsma_DAO.deleteBySolicitud(lsma_solicitudMatriculaActo);
					lsm_DAO.deleteBySolicitud(lsm_solicitudMatricula);
					lms_DAO.deleteBySolicitud(lms_matriculaSegregacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("limpiarDatosDelPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de validar si se debe mostrar mensaje de embargo vigente.
	 *
	 * @param as_idActoColumn Variable de tipo String que contiene el idActo del acto en trámite.
	 * @param as_idCirculoPrincipal Variable de tipo String que contiene el idCirculo del predio en trámite.
	 * @param al_idMatriculaPrincipal Variable de tipo Long que contiene la matrícula del predio en trámite.
	 * @param ab_anotacionCancelada de ab anotacion cancelada
	 * @return Variable de tipo boolean que valida si se debe mostrar mensaje de embargo vigente.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean mensajeEmbargoVigente(
	    String as_idActoColumn, String as_idCirculoPrincipal, Long al_idMatriculaPrincipal,
	    boolean ab_anotacionCancelada
	)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(
			    StringUtils.isValidString(as_idActoColumn) && StringUtils.isValidString(as_idCirculoPrincipal)
				    && NumericUtils.isValidLong(al_idMatriculaPrincipal)
			)
			{
				Acto la_acto;

				la_acto = new Acto();

				la_acto.setIdActo(as_idActoColumn);

				la_acto = DaoCreator.getActoDAO(ldm_manager).findById(la_acto);

				if(la_acto != null)
				{
					String ls_tipoActo;

					ls_tipoActo = la_acto.getIdTipoActo();

					if(StringUtils.isValidString(ls_tipoActo))
					{
						Constantes lc_codigos;

						lc_codigos = DaoCreator.getConstantesDAO(ldm_manager)
								                   .findById(ConstanteCommon.CODIGOS_ADJUDICACION_PARA_EMBARGO_VIGENTE);

						if(lc_codigos != null)
						{
							String ls_caracter;

							ls_caracter = lc_codigos.getCaracter();

							if(StringUtils.isValidString(ls_caracter))
							{
								Map<String, String> lmss_mss;

								lmss_mss = ListadoCodigosConstantes.generarCodigos(ls_caracter);

								if(CollectionUtils.isValidCollection(lmss_mss) && lmss_mss.containsKey(ls_tipoActo))
								{
									AnotacionPredio lap_anotacion;

									lap_anotacion = new AnotacionPredio();

									lap_anotacion.setIdCirculo(as_idCirculoPrincipal);
									lap_anotacion.setIdMatricula(al_idMatriculaPrincipal);

									lb_return = DaoCreator.getAnotacionPredioDAO(ldm_manager)
											                  .consultarCancelaciones(
											    lap_anotacion, ab_anotacionCancelada
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

			clh_LOGGER.error("mensajeEmbargoVigente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Metodo encargado de validar si se muestra el cargue de intervinientes a partir de id naturaleza juridica.
	 *
	 * @param as_idNaturalezaJuridica Argumento de tipo <code>String</code> que contiene el id naturaleza juridica.
	 * @return Variable de tipo <code>boolean</code> que determina si se muestra el cargue de intervinientes <code>true</code> de lo contrario <code>false</code> no.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean mostrarCargueIntervinientes(String as_idNaturalezaJuridica)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_codigoConVariosIntervinientes;

		ldm_manager                          = DaoManagerFactory.getDAOManager();
		lb_codigoConVariosIntervinientes     = false;

		try
		{
			if(StringUtils.isValidString(as_idNaturalezaJuridica))
			{
				if(as_idNaturalezaJuridica.contains("-"))
				{
					String[] lsa_datos;

					lsa_datos     = as_idNaturalezaJuridica.split("-");

					as_idNaturalezaJuridica = (CollectionUtils.isValidCollection(lsa_datos) && (lsa_datos.length > 0))
						? lsa_datos[0] : null;
				}

				if(StringUtils.isValidString(as_idNaturalezaJuridica))
				{
					Collection<String> lcs_constantes;

					lcs_constantes = new ArrayList<String>();

					lcs_constantes.add(ConstanteCommon.ACTOS_ADJUDICACION_LIQUIDACION_JUDICIAL);
					lcs_constantes.add(ConstanteCommon.CODIGO_DE_ACTO_CON_MAS_DE_UN_INTERVINIENTE);
					lcs_constantes.add(ConstanteCommon.ACTOS_ADJUDICACION_LIQUIDACION_JUDICIAL);

					{
						Iterator<String> lis_iterador;

						lis_iterador = lcs_constantes.iterator();

						while(lis_iterador.hasNext() && !lb_codigoConVariosIntervinientes)
						{
							String ls_constante;

							ls_constante = lis_iterador.next();

							if(StringUtils.isValidString(ls_constante))
							{
								Map<String, String> lmss_constantes;

								lmss_constantes     = generarCodigos(ls_constante, ldm_manager);

								lb_codigoConVariosIntervinientes = CollectionUtils.isValidCollection(lmss_constantes)
										&& lmss_constantes.containsKey(as_idNaturalezaJuridica);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("mostrarCargueIntervinientes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_codigoConVariosIntervinientes;
	}

	/**
	 * Método de seleccion del testamento anterior en el registro de la solicitud.
	 *
	 * @param at_testamento de at testamento
	 * @return el valor de testamento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Testamento obtenerTestamentoAnterior(Collection<Testamento> at_testamento)
	    throws B2BException
	{
		Testamento lct_datos;

		lct_datos = null;

		try
		{
			if(CollectionUtils.isValidCollection(at_testamento))
			{
				for(Testamento it_testamento : at_testamento)
				{
					if(it_testamento.isSeleccionado())
					{
						lct_datos = it_testamento;

						break;
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_DEBE_REALIZAR_CONSULTA_TESTAMENTO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerTestamentoAnterior", lb2be_e);

			throw lb2be_e;
		}

		return lct_datos;
	}

	/**
	 * Método encargado de validar si el acto pertenece a un acto de cancelación.
	 *
	 * @param as_idActo Variable de tipo String que contiene el id del acto a validar.
	 * @return Variable de booleana que valida si el acto pertenece a un acto de cancelación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean perteneceActoCancelacion(String as_idActo)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		boolean lb_pertenece;
		lb_pertenece = false;

		try
		{
			NaturalezaJuridicaDAO lnj_DAO;
			lnj_DAO = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);

			NaturalezaJuridica lcnj_tiposActoGrupo700;

			lcnj_tiposActoGrupo700 = lnj_DAO.findNatCancelacion(as_idActo);

			if(lcnj_tiposActoGrupo700 != null)
				lb_pertenece = true;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("perteneceActoCancelacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_pertenece;
	}

	/**
	 * Método encargado de validar si el acto pertenece a un acto de medida cautelar.
	 *
	 * @param as_idActo Variable de tipo String que contiene el id del acto a validar.
	 * @return Variable de booleana que valida si el acto pertenece a un acto de medida cautelar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean perteneceActoMedidaCautelar(String as_idActo)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		boolean lb_pertenece;
		lb_pertenece = false;

		try
		{
			NaturalezaJuridicaDAO lnj_DAO;
			lnj_DAO = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);

			NaturalezaJuridica lcnj_tiposActoGrupo400;
			lcnj_tiposActoGrupo400 = new NaturalezaJuridica();

			String     ls_idConstante;
			Constantes lc_constante;

			ls_idConstante     = ConstanteCommon.GRUPO_NATURALEZA_JURIDICA_MEDIDA_CAUTELAR;
			lc_constante       = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_idConstante);

			if(lc_constante == null)
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = ls_idConstante;

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
			}

			lcnj_tiposActoGrupo400.setIdGrupoNatJur(lc_constante.getCaracter());
			lcnj_tiposActoGrupo400.setIdNaturalezaJuridica(as_idActo);

			lcnj_tiposActoGrupo400 = lnj_DAO.findNatMedidaCautelar(lcnj_tiposActoGrupo400);

			if(lcnj_tiposActoGrupo400 != null)
				lb_pertenece = true;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("perteneceActoMedidaCautelar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_pertenece;
	}

	/**
	 * Método encargado de comparar la fecha creación de la solicitud con la fecha del documento registrado a una solicitud determinada.
	 *
	 * @param as_solicitud Objeto de tipo solicitud que contiene los datos necesarios para consultar las fechas a validar.
	 * @return Objeto de tipo  solicitud  que contiene la validacion final para presentar o no campos determinados para los recibos de pago.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public synchronized Solicitud recibosImpuesto(Solicitud as_solicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_solicitud != null)
			{
				SolicitudDAO los_DAO;
				los_DAO     = DaoCreator.getSolicitudDAO(ldm_manager);

				as_solicitud = los_DAO.findById(as_solicitud);

				if(as_solicitud != null)
				{
					DocumentoDAO         lod_DAO;
					RegistroCalificacion lorc_rc;
					lod_DAO     = DaoCreator.getDocumentoDAO(ldm_manager);
					lorc_rc     = new RegistroCalificacion();

					lorc_rc = lod_DAO.detalleDocumento(as_solicitud.getIdDocumento());

					if(lorc_rc != null)
					{
						Date       ld_fechaCreacion;
						Date       ld_fechaDocumento;
						Calendar   lc_fechaCreacion;
						Calendar   lc_fechaDocumento;
						Constantes lc_constante;

						lc_fechaCreacion      = new GregorianCalendar();
						lc_fechaDocumento     = new GregorianCalendar();

						lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
								                     .findById(ConstanteCommon.MESES_RECIBO_IMPUESTOS);

						if(lc_constante != null)
						{
							int li_mesesCo;
							int li_mesesDiffCo;

							li_mesesCo         = NumericUtils.getInt(lc_constante.getCaracter());
							li_mesesDiffCo     = NumericUtils.getInt(lc_constante.getDescripcion());

							ld_fechaCreacion      = as_solicitud.getFechaCreacion();
							ld_fechaDocumento     = lorc_rc.getFechaDocumento();
							lc_fechaCreacion.setTime(ld_fechaCreacion);
							lc_fechaDocumento.setTime(ld_fechaDocumento);

							int li_diffAnho = lc_fechaCreacion.get(Calendar.YEAR)
								- lc_fechaDocumento.get(Calendar.YEAR);

							int li_diffMes = ((li_diffAnho * 12) + (lc_fechaCreacion.get(Calendar.MONTH) + 1))
								- (lc_fechaDocumento.get(Calendar.MONTH) + 1);

							if(
							    StringUtils.getStringNotNull(lorc_rc.getIdPais())
								               .equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
							)
							{
								if(li_diffMes > li_mesesCo)
									as_solicitud.setReciboImpuestos(true);
							}
							else
							{
								if(li_diffMes > li_mesesDiffCo)
									as_solicitud.setReciboImpuestos(true);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("recibosImpuesto", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return as_solicitud;
	}

	/**
	 * Método encargado de validar si el acto requiere cancelación para un embargo.
	 *
	 * @param aap_ap Objeto d de tipo AnotacionPredio que contiene los datos necesarios para validar.
	 * @return Variable de booleana que valida si el acto requiere cancelación para un embargo.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean requiereCancelacionEmbargo(AnotacionPredio aap_ap)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_b;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_b            = false;

		try
		{
			if(aap_ap != null)
			{
				Constantes          lc_constante;
				Map<String, String> lmss_mss;

				lmss_mss         = new HashMap<String, String>();
				lc_constante     = new Constantes();
				lc_constante.setIdConstante(ConstanteCommon.ACTOS_ADJUDICACION_REMATE);

				lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_constante);

				if(lc_constante != null)
				{
					String ls_caracter;

					ls_caracter = lc_constante.getCaracter();

					if(StringUtils.isValidString(ls_caracter))
					{
						String[] lsa_codigos;

						lsa_codigos = ls_caracter.split(",");

						if(CollectionUtils.isValidCollection(lsa_codigos))
						{
							for(String ls_iterador : lsa_codigos)
							{
								if(StringUtils.isValidString(ls_iterador))
									lmss_mss.put(ls_iterador, ls_iterador);
							}

							if(lmss_mss.containsKey(StringUtils.getStringNotNull(aap_ap.getIdNaturalezaJuridica())))
								lb_b = DaoCreator.getAnotacionPredioDAO(ldm_manager)
										             .consultarCancelaciones(aap_ap, false);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("requiereCancelacionEmbargo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_b;
	}

	/**
	 * Metodo encargado de validar el desistimiento para un  turno seleccionado.
	 *
	 * @param act_turnos colección  contenedor de informacion necesaria para la validacion de desistimiento
	 * @param as_idProceso correspondiente al valor del tipo de objeto String
	 * @param as_userId usuario logueado en la aplciación
	 * @param as_remoteIp de as remote ip
	 * @param as_solicitud correspondiente al valor del tipo de objeto Solicitud
	 * @return as_idTipoEntidad tipo de proceso a comparar para comprobar el desistimiento
	 * @throws B2BException error si ocurre una excepción
	 * @see String
	 */
	public synchronized String revisionDesistimiento(
	    Collection<Turno> act_turnos, String as_idProceso, String as_userId, String as_remoteIp, Solicitud as_solicitud
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			if(as_solicitud != null)
			{
				if(!CollectionUtils.isValidCollection(act_turnos) || !StringUtils.isValidString(as_idProceso))
					throw new B2BException(ErrorKeys.ERROR_ACTO_INGRESADO);
				else
				{
					Proceso lp_p;

					lp_p = new Proceso();
					lp_p.setIdProceso(as_idProceso);

					lp_p = DaoCreator.getProcesoDAO(ldm_manager).findById(lp_p);

					if(lp_p != null)
					{
						ConstantesDAO lcd_DAO;
						Subproceso    lsp_sp;
						SubprocesoDAO lspd_DAO;
						SolicitudDAO  lsd_DAO;
						RecursoDAO    lrd_DAO;
						ConstantesDAO lcd_constant;

						lcd_DAO          = DaoCreator.getConstantesDAO(ldm_manager);
						lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
						lspd_DAO         = DaoCreator.getSubprocesoDAO(ldm_manager);
						lrd_DAO          = DaoCreator.getRecursoDAO(ldm_manager);
						lcd_constant     = DaoCreator.getConstantesDAO(ldm_manager);

						lsp_sp = new Subproceso();
						lsp_sp.setIdProceso(as_idProceso);

						for(Turno lt_iterador : act_turnos)
						{
							if(lt_iterador != null)
							{
								if(lt_iterador.isTurnoSeleccionado())
								{
									boolean lb_proceso40;
									boolean lb_proceso42;
									boolean lb_proceso47;
									boolean lb_proceso48;
									String  ls_idTurno;

									lb_proceso40     = as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40);
									lb_proceso42     = as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_42);
									lb_proceso47     = as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47);
									lb_proceso48     = as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48);
									ls_idTurno       = lt_iterador.getIdTurno();

									lsp_sp.setIdSubproceso(
									    (lb_proceso47 || lb_proceso48) ? as_solicitud.getIdSubproceso()
									                                   : lt_iterador.getIdProceso()
									);
									lsp_sp = lspd_DAO.findById(lsp_sp);

									if(lsp_sp == null)
									{
										Object[] loa_messageArgs = new String[1];

										loa_messageArgs[0] = lp_p.getNombre();

										throw new B2BException(ErrorKeys.PROCESO_TIPO_TRAMITE, loa_messageArgs);
									}

									if(lb_proceso40 || lb_proceso42)
									{
										Collection<TurnoHistoria> lcth_turnosHistorias;
										TurnoHistoria             lth_tmp;

										lth_tmp                  = new TurnoHistoria();
										lcth_turnosHistorias     = null;

										if(StringUtils.isValidString(ls_idTurno))
										{
											lth_tmp.setIdTurno(ls_idTurno);

											if(lb_proceso40)
											{
												boolean    lb_segundRecepcion;
												Constantes lc_subProcesos;
												String     ls_subProcesos;

												lc_subProcesos         = lcd_DAO.findById(
													    ConstanteCommon.SUBPROCESOS_VALIDOS_CERTIFICADOS
													);
												lb_segundRecepcion     = lc_subProcesos != null;
												ls_subProcesos         = null;

												if(lb_segundRecepcion)
													ls_subProcesos = lc_subProcesos.getCaracter();

												lcth_turnosHistorias = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
														                             .findByTurnoSuspensionTramiteEntregaDoc(
														    lth_tmp, true, ls_subProcesos
														);
											}
											else if(lb_proceso42)
												lcth_turnosHistorias = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
														                             .findByTurnoProrrogaMayorValor(
														    lth_tmp
														);

											if(lb_proceso42)
											{
												TurnoDerivado lt_turnoDerivado;
												lt_turnoDerivado = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
														                         .findByIdTurnoMayorProrroga(
														    ls_idTurno
														);

												if(lt_turnoDerivado != null)
													throw new B2BException(
													    ErrorKeys.ERROR_TURNO_YA_SOLICITO_PRORROGA_MAYOR_VALOR
													);
											}

											if(lb_proceso40 || lb_proceso42)
											{
												if(CollectionUtils.isValidCollection(lcth_turnosHistorias))
												{
													for(TurnoHistoria lth_turnoHistTMP : lcth_turnosHistorias)
													{
														if(lth_turnoHistTMP != null)
														{
															long   ll_idEtapa;
															String ls_estadoActividad;

															ll_idEtapa             = NumericUtils.getLong(
																    lth_turnoHistTMP.getIdEtapa()
																);
															ls_estadoActividad     = lth_turnoHistTMP.getEstadoActividad();

															if(
															    (ll_idEtapa == EtapaCommon.ID_ETAPA_MAYOR_VALOR_VENCIDO)
																    && lb_proceso42
															)
															{
																if(StringUtils.isValidString(ls_estadoActividad))
																{
																	if(
																	    ls_estadoActividad.equalsIgnoreCase(
																		        EstadoCommon.TERMINADA
																		    )
																	)
																		throw new B2BException(
																		    ErrorKeys.ERROR_TURNO_SELECCIONADO_ESTA_ETAPA_FINAL_PRORROGA
																		);
																}
															}
														}
													}
												}
												else if(lb_proceso40)
													throw new B2BException(
													    ErrorKeys.ERROR_TURNO_INGRESADO_NO_SE_ENCUENTRA_EN_SUSPENSION
													);
												else if(lb_proceso42)
													throw new B2BException(
													    ErrorKeys.ERROR_TURNO_SELECCIONADO_NO_TRAMITE_PRORROGA_MAYOR_V
													);
											}
										}

										if(lb_proceso40)
											as_solicitud.setDescripcion(
											    obtenerConstanteCaracter(
											        lcd_constant,
											        ConstanteCommon.COMENTARIO_PROHIBICION_JUDICIAL_ENTREGA_DOCUMENTACION
											    )
											);
									}

									if(
									    as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40)
										    || as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_42)
									)
									{
										Collection<TurnoHistoria> lcth_turnosHistorias;
										TurnoHistoria             lth_tmp;

										lth_tmp                  = new TurnoHistoria();
										lcth_turnosHistorias     = null;

										if(StringUtils.isValidString(ls_idTurno))
										{
											lth_tmp.setIdTurno(ls_idTurno);

											if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40))
											{
												boolean    lb_segundRecepcion;
												Constantes lc_subProcesos;
												String     ls_subProcesos;

												lc_subProcesos         = lcd_DAO.findById(
													    ConstanteCommon.SUBPROCESOS_VALIDOS_CERTIFICADOS
													);
												lb_segundRecepcion     = lc_subProcesos != null;
												ls_subProcesos         = null;

												if(lb_segundRecepcion)
													ls_subProcesos = lc_subProcesos.getCaracter();

												lcth_turnosHistorias = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
														                             .findByTurnoSuspensionTramiteEntregaDoc(
														    lth_tmp, true, ls_subProcesos
														);
											}
											else if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_42))
												lcth_turnosHistorias = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
														                             .findByTurnoProrrogaMayorValor(
														    lth_tmp
														);

											if(!CollectionUtils.isValidCollection(lcth_turnosHistorias))
												throw new B2BException(
												    ErrorKeys.ERROR_TURNO_INGRESADO_NO_SE_ENCUENTRA_EN_SUSPENSION
												);
										}
									}

									as_solicitud.setIdProceso(lsp_sp.getIdProceso());
									as_solicitud.setIdSubproceso(lsp_sp.getIdSubproceso());
									as_solicitud.setIdDocumento(lt_iterador.getIdDocumento());
									as_solicitud.setIdUsuarioModificacion(as_userId);
									as_solicitud.setIpModificacion(as_remoteIp);

									lsd_DAO.updateSubprocesoSolicitud(as_solicitud, false);

									if(lb_proceso47)
									{
										Recurso lr_recurso;
										String  ls_solicitudTMP;
										ls_solicitudTMP     = null;

										lr_recurso = new Recurso();

										lr_recurso.setIdTurno(ls_idTurno);
										lr_recurso.setFechaRecurso(new Date());
										lr_recurso.setDigitalizado(EstadoCommon.N);

										if(StringUtils.isValidString(ls_idTurno))
											ls_solicitudTMP = DaoCreator.getSolicitudDAO(ldm_manager)
													                        .findIdSolicitudByIdTurno(ls_idTurno);

										lr_recurso.setIdSolicitud(as_solicitud.getIdSolicitud());
										lr_recurso.setIdSolicitudRecurso(ls_solicitudTMP);
										lr_recurso.setIdUsuarioCreacion(as_userId);
										lr_recurso.setIpCreacion(as_remoteIp);

										lrd_DAO.insertOrUpdate(lr_recurso, true);
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

			clh_LOGGER.error("revisionDesistimiento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método encargado de salvar las causales del proceso de Correcciones.
	 *
	 * @param acsc_correcciones Colección que contiene las causales que se desean insertar
	 * @param as_idSolicitud Variable de tipo String que contiene el id de la solicitud en tramite
	 * @param as_matricula Variable de tipo String que contiene le id de la matricula del proceso.
	 * @param ab_segregacion de ab segregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCausalesCorreccion(
	    Collection<SolicitudCorreccion> acsc_correcciones, String as_idSolicitud, String as_matricula,
	    boolean ab_segregacion
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_matricula) && StringUtils.isValidString(as_idSolicitud))
			{
				BigInteger                           lbi_idMatricula;
				boolean                              lb_query;
				String                               ls_idCirculo;
				String[]                             lsa_matricula;
				Map<BigInteger, SolicitudCorreccion> llhm_correcciones;
				SolicitudCorreccion                  lsc_correccion;
				SolicitudCorreccionDAO               lsc_DAO;

				lbi_idMatricula       = null;
				lb_query              = true;
				llhm_correcciones     = null;
				ls_idCirculo          = null;
				lsa_matricula         = as_matricula.split(IdentificadoresCommon.SIMBOLO_GUION);
				lsc_correccion        = new SolicitudCorreccion();
				lsc_DAO               = DaoCreator.getSolicitudCorreccionDAO(ldm_manager);

				if(lsa_matricula.length > 1)
				{
					ls_idCirculo        = lsa_matricula[0];
					lbi_idMatricula     = new BigInteger(lsa_matricula[1]);

					if(StringUtils.isValidString(ls_idCirculo))
						lsc_correccion.setIdCirculo(ls_idCirculo);

					if(NumericUtils.isValidBigInteger(lbi_idMatricula))
						lsc_correccion.setIdMatricula(lbi_idMatricula);

					lsc_correccion.setIdSolicitud(as_idSolicitud);
					lsc_correccion.setFindOnlySolicitud(false);
				}

				llhm_correcciones = lsc_DAO.findCheckBySolicitudCirculoMatricula(lsc_correccion);

				if(CollectionUtils.isValidCollection(acsc_correcciones))
				{
					if(CollectionUtils.isValidCollection(llhm_correcciones))
					{
						for(SolicitudCorreccion lsc_iterador : acsc_correcciones)
						{
							if(lsc_iterador != null)
							{
								BigInteger lbi_idCausal;

								lbi_idCausal = lsc_iterador.getIdCausalCorreccion();

								if(NumericUtils.isValidBigInteger(lbi_idCausal))
								{
									if(
									    CausalCorreccionCommon.DATOS_BASICOS.equalsIgnoreCase(
										        StringUtils.getString(lbi_idCausal)
										    ) && ab_segregacion
									)
										lsc_iterador.setSeleccionado(EstadoCommon.S);

									if(llhm_correcciones.containsKey(lbi_idCausal))
									{
										SolicitudCorreccion lsc_correccionUpdate;

										lb_query                 = false;
										lsc_correccionUpdate     = llhm_correcciones.get(lbi_idCausal);

										lsc_iterador.setIdSolicitudCorreccion(
										    lsc_correccionUpdate.getIdSolicitudCorreccion()
										);
										lsc_DAO.insertOrUpdate(lsc_iterador, lb_query);
									}
									else
									{
										lb_query = true;

										lsc_iterador.setIdUsuarioModificacion(null);
										lsc_iterador.setIpModificacion(null);
										lsc_DAO.insertOrUpdate(lsc_iterador, lb_query);
									}
								}
							}
						}
					}
					else
					{
						lb_query = true;

						for(SolicitudCorreccion lsc_iterador : acsc_correcciones)
						{
							if(lsc_iterador != null)
							{
								lsc_iterador.setIdUsuarioModificacion(null);
								lsc_iterador.setIpModificacion(null);
								lsc_DAO.insertOrUpdate(lsc_iterador, lb_query);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCausalesCorreccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar los datos de copias para una solicitud.
	 *
	 * @param acc_camposConsulta de acc campos consulta
	 * @param as_solicitud objeto necesario para realizar la operación de copias para una solicitud.
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException error si ocurre una excepción
	 */
	public synchronized boolean salvarDatosCopias(
	    CamposConsulta acc_camposConsulta, Solicitud as_solicitud, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_sinInformacion;

		ldm_manager           = DaoManagerFactory.getDAOManager();
		lb_sinInformacion     = false;

		try
		{
			if((acc_camposConsulta != null) && (as_solicitud != null))
			{
				Collection<DocumentoOWCC>              lcdo_documentosOWCC;
				Collection<DataReproduccionConstancia> lcdrc_dataReproduccionConstancia;
				String                                 ls_idSolicitud;
				boolean                                lb_documentosOWCC;
				boolean                                lb_dataReproduccionConstancia;
				boolean                                lb_sinDocumentos;

				lcdo_documentosOWCC                  = acc_camposConsulta.getDocumentosOWCC();
				lcdrc_dataReproduccionConstancia     = acc_camposConsulta.getDataReproduccionConstancia();
				ls_idSolicitud                       = as_solicitud.getIdSolicitud();
				lb_documentosOWCC                    = CollectionUtils.isValidCollection(lcdo_documentosOWCC);
				lb_dataReproduccionConstancia        = CollectionUtils.isValidCollection(
					    lcdrc_dataReproduccionConstancia
					);
				lb_sinDocumentos                     = !lb_documentosOWCC
						&& !CollectionUtils.isValidCollection(lcdrc_dataReproduccionConstancia);

				{
					Solicitud    ls_solicitudUpdate;
					SolicitudDAO lsd_DAO;

					ls_solicitudUpdate     = new Solicitud();
					lsd_DAO                = DaoCreator.getSolicitudDAO(ldm_manager);

					ls_solicitudUpdate.setIdSolicitud(ls_idSolicitud);

					ls_solicitudUpdate = lsd_DAO.findById(ls_solicitudUpdate);

					if(ls_solicitudUpdate != null)
					{
						String  ls_exenta;
						boolean lb_exenta;

						ls_exenta     = ls_solicitudUpdate.getEntidadExenta();
						lb_exenta     = StringUtils.isValidString(ls_exenta)
								&& ls_exenta.equalsIgnoreCase(EstadoCommon.S);

						lb_sinInformacion = !lb_documentosOWCC;

						if(!lb_exenta && !lb_sinInformacion)
						{
							Iterator<DocumentoOWCC> lido_iterator;

							lido_iterator = lcdo_documentosOWCC.iterator();

							while(lido_iterator.hasNext() && !lb_sinInformacion)
							{
								DocumentoOWCC ldo_documentoOWCC;

								ldo_documentoOWCC = lido_iterator.next();

								if((ldo_documentoOWCC != null) && ldo_documentoOWCC.isSeleccione())
									lb_sinInformacion = NumericUtils.getLong(ldo_documentoOWCC.getnumeroFolios()) <= 0;
							}
						}
					}
				}

				DaoCreator.getSolicitudCopiasDAO(ldm_manager).deleteByIdSolicitud(ls_idSolicitud);

				if(lb_sinDocumentos)
					salvarDatosCopiasSolicitud(ldm_manager, as_solicitud, as_userId, as_remoteIp);
				else if(lb_documentosOWCC && !lb_dataReproduccionConstancia)
					salvarSolicitudCopias(ldm_manager, lcdo_documentosOWCC, ls_idSolicitud, as_userId, as_remoteIp);
				else if(lb_dataReproduccionConstancia && !lb_documentosOWCC)
					salvarDatosCopiasBng(
					    ldm_manager, lcdrc_dataReproduccionConstancia, ls_idSolicitud, as_userId, as_remoteIp
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDatosCopias", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_sinInformacion;
	}

	/**
	 * Método encargado de salvar un documento en la tabla SDB_BGN_DOCUMENTO.
	 *
	 * @param ad_documento Objeto que contiene la información del documento que se desea insertar.
	 * @param aso_solicitud Variable de tipo String que contiene el id de la solicitud en tramite.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_ipRemota Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @return Objeto que contiene la información del documento insertado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	public synchronized Documento salvarDocumento(
	    Documento ad_documento, Solicitud aso_solicitud, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Documento  ld_documento;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ld_documento     = null;

		try
		{
			if(ad_documento != null)
			{
				DocumentoDAO ldd_DAO;
				SolicitudDAO lsd_DAO;

				ldd_DAO          = DaoCreator.getDocumentoDAO(ldm_manager);
				lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
				ld_documento     = ldd_DAO.consultaDocumento(ad_documento);

				if(ld_documento == null)
				{
					int li_secuencia;

					li_secuencia = DaoCreator.getUtilDAO(ldm_manager)
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
							ld_documento.setNumero(ad_documento.getNumero());
							ld_documento.setIdTipoDocumento(ad_documento.getIdTipoDocumento());
							ld_documento.setOficinaInternacional(EstadoCommon.N);
							ld_documento.setFechaDocumento(ad_documento.getFechaDocumento());
							ld_documento.setFechaEjecutoria(ad_documento.getFechaEjecutoria());
							ld_documento.setIdTipoOficina(ad_documento.getIdTipoOficina());

							{
								String ls_idOficinaOrigen;

								ls_idOficinaOrigen = ad_documento.getIdOficinaOrigen();

								ld_documento.setIdOficinaOrigen(ls_idOficinaOrigen);
								ld_documento.setVersion(
								    obtenerVersionMaximaOficinaOrigen(ls_idOficinaOrigen, ldm_manager)
								);
							}

							ld_documento.setIdUsuarioCreacion(as_userId);
							ld_documento.setIpCreacion(as_ipRemota);
							ld_documento.setIdPais(ad_documento.getIdPais());
							ld_documento.setIdDepartamento(ad_documento.getIdDepartamento());
							ld_documento.setIdMunicipio(ad_documento.getIdMunicipio());

							ldd_DAO.insertOrUpdate(ld_documento, true);
						}
					}
				}

				if(ld_documento != null)
				{
					{
						Solicitud ls_lsTemp;

						ls_lsTemp = lsd_DAO.findByIdDocumento(ld_documento.getIdDocumento());

						if(ls_lsTemp != null)
						{
							long ll_estadoSolicitud;

							ll_estadoSolicitud = NumericUtils.getLong(ls_lsTemp.getEstadoSolicitud());

							if(ll_estadoSolicitud == 1)
							{
								ld_documento.setOficinaInternacional("N");
								ld_documento.setFechaEjecutoria(ad_documento.getFechaEjecutoria());
								ld_documento.setIdUsuarioModificacion(as_userId);
								ld_documento.setIpModificacion(as_ipRemota);

								ldd_DAO.insertOrUpdate(ld_documento, false);
							}
							else
							{
								if(
								    (aso_solicitud != null)
									    && StringUtils.getStringNotNull(aso_solicitud.getIdProceso())
									                      .equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
									    && StringUtils.getStringNotNull(aso_solicitud.getIdSubproceso())
									                      .equalsIgnoreCase(SubProcesoCommon.ORDINARIA)
								)
									throw new B2BException(ErrorKeys.EL_ESTADO_DE_SOLICITUD_DIFERENTE_GENERADO);
							}
						}
					}

					Solicitud lso_solicitud;

					lso_solicitud = lsd_DAO.findById(aso_solicitud);

					if(lso_solicitud != null)
					{
						String ls_idDocumento;
						Long   ll_versionDocumento;

						ls_idDocumento          = ld_documento.getIdDocumento();
						ll_versionDocumento     = ld_documento.getVersionDocumento();

						lso_solicitud.setIdDocumento(ls_idDocumento);
						lso_solicitud.setVersionDocumento(ll_versionDocumento);
						lso_solicitud.setVersionDocumentoInicial(ll_versionDocumento);
						lso_solicitud.setIdUsuarioModificacion(as_userId);
						lso_solicitud.setIpModificacion(as_ipRemota);

						lsd_DAO.update(lso_solicitud);

						{
							String ls_idProceso;

							ls_idProceso = lso_solicitud.getIdProceso();

							if(
							    StringUtils.isValidString(ls_idProceso)
								    && (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
								    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48))
							)
							{
								Recurso    lr_recurso;
								RecursoDAO lrd_DAO;

								lr_recurso     = new Recurso();
								lrd_DAO        = DaoCreator.getRecursoDAO(ldm_manager);

								lr_recurso.setIdSolicitud(aso_solicitud.getIdSolicitud());

								lr_recurso = lrd_DAO.findByIdSolicitud(lr_recurso);

								if(lr_recurso != null)
								{
									lr_recurso.setIdDocumento(ls_idDocumento);
									lr_recurso.setVersionDocumento(ll_versionDocumento);
									lr_recurso.setIdUsuarioModificacion(as_userId);
									lr_recurso.setIpModificacion(as_ipRemota);

									lrd_DAO.insertOrUpdate(lr_recurso, false);
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ld_documento;
	}

	/**
	 * Método encargado de guardar la información del interesado de una solicitud.
	 *
	 * @param ar_registro Objeto que contiene los datos del interesado que se desea insertar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @return Objeto que contiene la información del interesado inserado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro salvarInteresado(Registro ar_registro, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_registro != null)
			{
				salvarInteresado(ldm_manager, ar_registro, as_userId, true);

				{
					Persona    lp_parametros;
					Solicitud  lso_solicitud;
					PersonaDAO lpd_DAO;

					lp_parametros     = ar_registro.getPersona();
					lso_solicitud     = ar_registro.getSolicitud();
					lpd_DAO           = DaoCreator.getPersonaDAO(ldm_manager);

					if((lp_parametros != null) && (lso_solicitud != null))
					{
						String ls_idPersona;

						ls_idPersona = marcarFlagPersona(
							    ldm_manager, lp_parametros, as_userId, ar_registro.getIpCreacion()
							);

						if(ar_registro.isPersonaCargada())
						{
							Persona lp_temp;

							lp_temp = lpd_DAO.findDataCalificador(lp_parametros);

							if(lp_temp != null)
							{
								lp_temp     = lpd_DAO.findById(lp_temp);

								ls_idPersona = lp_temp.getIdPersona();
							}
						}

						if(ar_registro.isProcesoDevoluciones())
							salvarProcesoDevolucionesDinero(
							    ar_registro, lso_solicitud.getIdSolicitud(), ls_idPersona, as_userId, ldm_manager
							);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarInteresado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Método encargado de guardar la información del interviniente de una solicitud.
	 *
	 * @param ar_registro Objeto que contiene los datos del interviniente que se desea insertar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param ab_siNotificaCorr Variable de tipo booleana que valida si el medio a notificar es correo.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @param ab_vieneDeRegistro Variable de tipo booleana que valida si el proceso proviene de registro.
	 * @return Objeto que contiene la información del interviniente insertado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro salvarInterviniente(
	    Registro ar_registro, String as_userId, boolean ab_siNotificaCorr, String as_remoteIp,
	    boolean ab_vieneDeRegistro
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_registro != null)
			{
				Persona                lp_parametros;
				SolicitudInterviniente lsi_solicitudInter;
				PersonaDAO             lpd_DAO;
				Solicitud              lso_solicitud;
				OficiosTextoDAO        lotd_DAO;
				OficiosTexto           lot_oficiosTextoSol;

				lso_solicitud           = ar_registro.getSolicitud();
				lp_parametros           = ar_registro.getPersona();
				lsi_solicitudInter      = ar_registro.getSolicitudInterviniente();
				lpd_DAO                 = DaoCreator.getPersonaDAO(ldm_manager);
				lotd_DAO                = DaoCreator.getOficiosTextoDAO(ldm_manager);
				lot_oficiosTextoSol     = new OficiosTexto();

				if((lp_parametros != null) && (lsi_solicitudInter != null))
				{
					boolean lb_notifOrip;
					String  ls_tipoPersona;
					String  ls_tipoDocumento;
					String  ls_idAutorizacionNotificacion;
					String  ls_idAutorizacionComunicacion;
					String  ls_idCorreoCo;
					String  ls_idCorreoNo;
					String  ls_idDireccionCo;
					String  ls_idDireccionNo;
					String  ls_idTelefonoCo;
					String  ls_idTelefonoNo;

					lb_notifOrip                      = false;
					ls_tipoPersona                    = lp_parametros.getIdTipoPersona();
					ls_tipoDocumento                  = lp_parametros.getIdDocumentoTipo();
					ls_idAutorizacionNotificacion     = StringUtils.getStringNotNull(
						    lsi_solicitudInter.getIdAutorizacionNotificacion()
						);
					ls_idAutorizacionComunicacion     = StringUtils.getStringNotNull(
						    lsi_solicitudInter.getIdAutorizacionComunicacion()
						);
					ls_idCorreoCo                     = null;
					ls_idCorreoNo                     = null;
					ls_idDireccionCo                  = null;
					ls_idDireccionNo                  = null;
					ls_idTelefonoCo                   = null;
					ls_idTelefonoNo                   = null;

					lp_parametros.setIdUsuarioCreacion(as_userId);

					if(
					    ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.ORIP)
						    || ls_idAutorizacionComunicacion.equals(MedioNotificarCommon.ORIP)
					)
						lb_notifOrip = true;

					if(!ab_vieneDeRegistro)
					{
						ls_idAutorizacionNotificacion     = MedioNotificarCommon.ORIP;
						ls_idAutorizacionComunicacion     = MedioNotificarCommon.ORIP;
					}

					String                   ls_idProceso;
					String                   ls_idSubproceso;
					PersonaDireccion         lpd_dirCor;
					PersonaDireccion         lpd_dirRe;
					PersonaTelefono          lpt_telFi;
					PersonaTelefono          lpt_telMo;
					PersonaCorreoElectronico lpc_correo;
					String                   ls_dirCor;
					String                   ls_dirRe;
					String                   ls_telFi;
					String                   ls_telMo;
					String                   ls_correo1;
					String                   ls_idSolicitudInter;

					lpd_dirCor              = ar_registro.getDireccionCorrespondencia();
					lpd_dirRe               = ar_registro.getDireccionResidencia();
					ls_dirCor               = lpd_dirCor.getDireccion();
					ls_dirRe                = lpd_dirRe.getDireccion();
					lpt_telFi               = ar_registro.getTelefonoFijo();
					lpt_telMo               = ar_registro.getTelefonoMovil();
					lpc_correo              = ar_registro.getPersonaCorreoElectronico();
					ls_telFi                = lpt_telFi.getTelefono();
					ls_telMo                = lpt_telMo.getTelefono();
					ls_correo1              = lpc_correo.getCorreoElectronico();
					ls_idSolicitudInter     = lsi_solicitudInter.getIdSolicitud();

					if(lso_solicitud != null)
					{
						ls_idProceso        = StringUtils.getStringNotNull(lso_solicitud.getIdProceso());
						ls_idSubproceso     = StringUtils.getStringNotNull(lso_solicitud.getIdSubproceso());
					}
					else
					{
						ls_idProceso        = "";
						ls_idSubproceso     = "";
					}

					if(
					    !StringUtils.isValidString(ls_dirCor) && !StringUtils.isValidString(ls_dirRe)
						    && !StringUtils.isValidString(ls_telFi) && !StringUtils.isValidString(ls_telMo)
						    && !StringUtils.isValidString(ls_correo1) && !lb_notifOrip && ab_vieneDeRegistro
						    && !(ls_idProceso.equalsIgnoreCase("3") && ls_idSubproceso.equalsIgnoreCase("2"))
					)
						throw new B2BException(ErrorKeys.DEBE_DILIGENCIAR_FORMULARIO);

					if(StringUtils.isValidString(ls_dirRe))
						validarDireccion(lpd_dirRe, true);

					if(StringUtils.isValidString(ls_dirCor))
						validarDireccion(lpd_dirCor, false);

					if(!StringUtils.isValidString(ls_tipoPersona))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);

					if(!StringUtils.isValidString(ls_tipoDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

					{
						String ls_documento;
						ls_documento = lp_parametros.getNumeroDocumento();

						if(!StringUtils.isValidString(ls_documento))
							throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

						{
							Constantes lc_constante;
							String     ls_caracter;

							lc_constante     = new Constantes();
							ls_caracter      = ConstanteCommon.CARACTERES_ESPECIALES;
							lc_constante.setIdConstante(ls_caracter);

							lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_constante);

							if(lc_constante != null)
							{
								String  ls_constantes;
								boolean lb_valido;

								ls_constantes     = lc_constante.getCaracter();
								lb_valido         = false;

								if(!StringUtils.isValidString(ls_constantes))
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = ls_caracter;

									throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
								}

								lb_valido = StringUtils.isValidCharacters(ls_documento, ls_constantes);

								if(!lb_valido)
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_DOC_SIN_CARACTERES);
							}
						}
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

							{
								String ls_genero;
								ls_genero = lp_parametros.getIdNaturalGenero();

								if(!StringUtils.isValidString(ls_genero))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
							}
						}
						else
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURAL);
					}

					if(StringUtils.getStringNotNull(ls_tipoPersona).equalsIgnoreCase(EstadoCommon.J))
					{
						if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
						{
							{
								String ls_nacionalidad;
								ls_nacionalidad = lp_parametros.getIdPais();

								if(!StringUtils.isValidString(ls_nacionalidad))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
							}

							{
								String ls_razonSocial;
								ls_razonSocial = lp_parametros.getRazonSocial();

								if(!StringUtils.isValidString(ls_razonSocial))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
							}
						}
						else if(!ls_tipoDocumento.equalsIgnoreCase(EstadoCommon.SE))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_JURIDICO);
					}

					if(!ar_registro.isProcesoDevoluciones())
					{
						SolicitudInterviniente lsi_parametros;
						lsi_parametros = ar_registro.getSolicitudInterviniente();

						if(lsi_parametros != null)
						{
							String ls_rol;
							ls_rol = lsi_parametros.getRolPersona();

							if(!StringUtils.isValidString(ls_rol))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
						}
					}

					if(!StringUtils.isValidString(ls_idAutorizacionNotificacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_NOTIFICAR);

					if(!StringUtils.isValidString(ls_idAutorizacionComunicacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_COMUNICAR);

					if(ab_vieneDeRegistro)
					{
						if(
						    ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)
							    || ls_idAutorizacionComunicacion.equalsIgnoreCase(
							        MedioNotificarCommon.DIRECCION_RESIDENCIA
							    )
						)
						{
							if(lpd_dirRe != null)
							{
								String ls_direccionResidencia;
								ls_direccionResidencia = lpd_dirRe.getDireccion();

								if(!StringUtils.isValidString(ls_direccionResidencia))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_DIRECCION_RESIDENCIA);

								if(
								    ls_idAutorizacionNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ls_idDireccionNo = lpd_dirRe.getIdDireccion();

								if(
								    ls_idAutorizacionComunicacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ls_idDireccionCo = lpd_dirRe.getIdDireccion();
							}
						}
						else if(
						    ls_idAutorizacionNotificacion.equalsIgnoreCase(
							        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
							    )
						)
						{
							if(lpd_dirCor != null)
							{
								String ls_direccionCorrespondencia;
								ls_direccionCorrespondencia = lpd_dirCor.getDireccion();

								if(!StringUtils.isValidString(ls_direccionCorrespondencia))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_DIRECCION_CORRESPONDENCIA);

								ls_idDireccionNo = lpd_dirCor.getIdDireccion();
							}
						}

						if(
						    ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
							    || ls_idAutorizacionComunicacion.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
						)
						{
							if(lpd_dirRe != null)
							{
								String ls_direccionCompleta;
								ls_direccionCompleta = lpd_dirRe.getDireccion();

								if(
								    (StringUtils.isValidString(ls_idAutorizacionNotificacion)
									    && ls_idAutorizacionNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    ))
									    || (StringUtils.isValidString(ls_idAutorizacionComunicacion)
									    && ls_idAutorizacionComunicacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )) || StringUtils.isValidString(ls_direccionCompleta)
								)
								{
									if(
									    (StringUtils.isValidString(ls_idAutorizacionNotificacion)
										    && ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_RESIDENCIA
										    ))
									)
										ls_idDireccionNo = lpd_dirRe.getIdDireccion();

									if(
									    (StringUtils.isValidString(ls_idAutorizacionComunicacion)
										    && ls_idAutorizacionComunicacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_RESIDENCIA
										    ))
									)
										ls_idDireccionCo = lpd_dirRe.getIdDireccion();

									validarDireccion(lpd_dirRe, true);
								}
							}
						}

						if(ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA))
						{
							if(lpd_dirCor != null)
							{
								String ls_direccionCompleta;
								ls_direccionCompleta = lpd_dirCor.getDireccion();

								if(
								    (StringUtils.isValidString(ls_idAutorizacionNotificacion)
									    && ls_idAutorizacionNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    ))
									    || (StringUtils.isValidString(ls_idAutorizacionComunicacion)
									    && ls_idAutorizacionComunicacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )) || StringUtils.isValidString(ls_direccionCompleta)
								)
								{
									if(
									    (StringUtils.isValidString(ls_idAutorizacionNotificacion)
										    && ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
										    ))
									)
										ls_idDireccionNo = lpd_dirCor.getIdDireccion();

									if(
									    (StringUtils.isValidString(ls_idAutorizacionComunicacion)
										    && ls_idAutorizacionComunicacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
										    ))
									)
										ls_idDireccionCo = lpd_dirCor.getIdDireccion();

									validarDireccion(lpd_dirCor, false);
								}
							}
						}

						if(lb_notifOrip)
						{
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
									if(ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
									{
										ls_idCorreoNo       = lpc_correo.getIdCorreoElectronico();
										ls_idTelefonoNo     = lpt_telMo.getIdTelefono();
									}

									if(ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
									{
										ls_idCorreoCo       = lpc_correo.getIdCorreoElectronico();
										ls_idTelefonoCo     = lpt_telMo.getIdTelefono();
									}

									if(StringUtils.isValidString(ls_numFijo))
										validarTelefono(ldm_manager, lpt_telFi, true);

									if(StringUtils.isValidString(ls_numMovil))
										validarTelefono(ldm_manager, lpt_telMo, false);

									if(
									    StringUtils.isValidString(ls_correoElec)
										    && !ExpresionRegular.getExpresionRegular().esCorreoElectronico(
										        ls_correoElec
										    )
									)
										throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
						}
						else
						{
							if(lpt_telFi != null)
							{
								String ls_telefono;
								ls_telefono = lpt_telFi.getTelefono();

								if(StringUtils.isValidString(ls_telefono))
									validarTelefono(ldm_manager, lpt_telFi, true);
							}

							if(lpt_telMo != null)
							{
								String ls_telefono;
								ls_telefono = lpt_telMo.getTelefono();

								if(
								    (StringUtils.isValidString(ls_idAutorizacionNotificacion)
									    && ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.SMS))
									    || (StringUtils.isValidString(ls_idAutorizacionComunicacion)
									    && ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.SMS))
									    || StringUtils.isValidString(ls_telefono)
								)
								{
									if(
									    (StringUtils.isValidString(ls_idAutorizacionNotificacion)
										    && ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.SMS))
									)
										ls_idTelefonoNo = lpt_telMo.getIdTelefono();

									if(
									    (StringUtils.isValidString(ls_idAutorizacionComunicacion)
										    && ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.SMS))
									)
										ls_idTelefonoCo = lpt_telMo.getIdTelefono();

									validarTelefono(ldm_manager, lpt_telMo, false);
								}
							}

							if(lpc_correo != null)
							{
								String ls_correo;

								ls_correo = lpc_correo.getCorreoElectronico();

								if(
								    StringUtils.isValidString(ls_correo)
									    && !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correo)
								)
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
							}

							{
								if(
								    ls_idAutorizacionNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.CORREO_ELECTRONICO
									    )
									    || ls_idAutorizacionComunicacion.equalsIgnoreCase(
									        MedioNotificarCommon.CORREO_ELECTRONICO
									    )
								)
								{
									if(
									    !ExpresionRegular.getExpresionRegular()
										                     .esCorreoElectronico(lpc_correo.getCorreoElectronico())
									)
										throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);

									if(
									    ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.CORREO_ELECTRONICO
										    )
									)
										ls_idCorreoNo = lpc_correo.getIdCorreoElectronico();

									if(
									    ls_idAutorizacionComunicacion.equalsIgnoreCase(
										        MedioNotificarCommon.CORREO_ELECTRONICO
										    )
									)
										ls_idCorreoCo = lpc_correo.getIdCorreoElectronico();
								}

								if(ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.SMS))
								{
									PersonaTelefono lpt_movil;
									lpt_movil = ar_registro.getTelefonoMovil();

									if(lpt_movil != null)
									{
										String ls_telefonoMovil;
										ls_telefonoMovil = lpt_movil.getTelefono();

										if(!StringUtils.isValidString(ls_telefonoMovil))
											throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL);

										ls_idTelefonoCo = lpt_movil.getIdTelefono();
									}
								}
							}
						}

						{
							String ls_idPersona;

							ls_idPersona = marcarFlagPersona(ldm_manager, lp_parametros, as_userId, as_remoteIp);

							if(StringUtils.isValidString(ls_idPersona))
								lp_parametros = lpd_DAO.findById(ls_idPersona);

							if(lp_parametros.getIdDocumentoTipo().equalsIgnoreCase(EstadoCommon.SE))
							{
								Constantes lc_constante;

								lc_constante = new Constantes();

								lc_constante.setIdConstante(ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA);

								lc_constante = findConstante(lc_constante);

								if(lc_constante != null)
								{
									BigInteger lbi_secuencia;

									lbi_secuencia = lc_constante.getEntero().add(BigInteger.valueOf(1));

									lc_constante.setEntero(lbi_secuencia);
									DaoCreator.getConstantesDAO(ldm_manager).insertOrUpdate(lc_constante, false);

									lp_parametros.setNumeroDocumento(StringUtils.getString(lbi_secuencia));
								}
							}

							if(ar_registro.isPersonaCargada())
							{
								Persona lp_temp;

								lp_temp     = new Persona();

								lp_temp = lpd_DAO.findDataCalificador(lp_parametros);

								if(lp_temp != null)
								{
									lp_temp     = lpd_DAO.findById(lp_temp);

									ls_idPersona = lp_temp.getIdPersona();
								}

								ar_registro.setIdPersona(ls_idPersona);
							}
							else
								ar_registro.setIdPersona(ls_idPersona);

							{
								PersonaDireccion lpd_direccionResidencia;
								lpd_direccionResidencia = ar_registro.getDireccionResidencia();

								if(
								    (lpd_direccionResidencia != null)
									    && StringUtils.isValidString(lpd_direccionResidencia.getDireccion())
								)
								{
									PersonaDireccionDAO lpdd_DAO;
									PersonaDireccion    lpd_direccion;
									boolean             lb_crearDireccion;

									lpdd_DAO              = DaoCreator.getPersonaDireccionDAO(ldm_manager);
									lpd_direccion         = null;
									lb_crearDireccion     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpd_direccionResidencia.getIdDireccion())
									)
									{
										lpd_direccionResidencia.setIdPersona(ls_idPersona);

										lpd_direccion = lpdd_DAO.findById(lpd_direccionResidencia);
									}

									if(lpd_direccion == null)
									{
										long ll_max;

										lb_crearDireccion = true;

										{
											String ls_idTipoPredio;

											ls_idTipoPredio = lpd_direccionResidencia.getIdTipoPredio();

											if(!StringUtils.isValidString(ls_idTipoPredio))
												throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
										}

										lpd_direccionResidencia.setIdPersona(ls_idPersona);
										lpd_direccionResidencia.setFechaDesde(new Date());
										lpd_direccionResidencia.setIdUsuarioCreacion(as_userId);
										lpd_direccionResidencia.setIpCreacion(as_remoteIp);

										ll_max = lpdd_DAO.insertOrUpdate(lpd_direccionResidencia, lb_crearDireccion);

										if(ll_max > 0)
											lpd_direccionResidencia.setIdDireccion(StringUtils.getString(ll_max));
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
									}
								}
							}

							{
								PersonaDireccion lpd_direccionCorrespondencia;
								lpd_direccionCorrespondencia = ar_registro.getDireccionCorrespondencia();

								if(
								    (lpd_direccionCorrespondencia != null)
									    && StringUtils.isValidString(lpd_direccionCorrespondencia.getDireccion())
								)
								{
									PersonaDireccionDAO lpdd_DAO;
									PersonaDireccion    lpd_direccion;
									boolean             lb_crearDireccion;

									lpdd_DAO              = DaoCreator.getPersonaDireccionDAO(ldm_manager);
									lpd_direccion         = null;
									lb_crearDireccion     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpd_direccionCorrespondencia.getIdDireccion())
									)
									{
										lpd_direccionCorrespondencia.setIdPersona(ls_idPersona);

										lpd_direccion = lpdd_DAO.findById(lpd_direccionCorrespondencia);
									}

									if(lpd_direccion == null)
									{
										long ll_max;

										lb_crearDireccion = true;

										{
											String ls_idTipoPredio;

											ls_idTipoPredio = lpd_direccionCorrespondencia.getIdTipoPredio();

											if(!StringUtils.isValidString(ls_idTipoPredio))
												throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
										}

										lpd_direccionCorrespondencia.setIdPersona(ls_idPersona);
										lpd_direccionCorrespondencia.setFechaDesde(new Date());
										lpd_direccionCorrespondencia.setIdUsuarioCreacion(as_userId);
										lpd_direccionCorrespondencia.setIdUsuarioModificacion(as_userId);
										lpd_direccionCorrespondencia.setIpCreacion(as_remoteIp);
										lpd_direccionCorrespondencia.setIpModificacion(as_remoteIp);

										ll_max = lpdd_DAO.insertOrUpdate(
											    lpd_direccionCorrespondencia, lb_crearDireccion
											);

										if(ll_max > 0)
											lpd_direccionCorrespondencia.setIdDireccion(StringUtils.getString(ll_max));
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
									}
								}
							}

							{
								if((lpt_telFi != null) && StringUtils.isValidString(lpt_telFi.getTelefono()))
								{
									PersonaTelefonoDAO lptd_DAO;
									PersonaTelefono    lpt_telefono;
									boolean            lb_crearTelefono;

									lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(ldm_manager);
									lpt_telefono         = null;
									lb_crearTelefono     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpt_telFi.getIdTelefono())
									)
									{
										lpt_telFi.setIdPersona(ls_idPersona);

										lpt_telefono = lptd_DAO.findById(lpt_telFi);
									}

									if(lpt_telefono == null)
									{
										long ll_max;

										lb_crearTelefono = true;
										lpt_telFi.setIdPersona(ls_idPersona);
										lpt_telFi.setTipoTelefono("F");
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
							}

							{
								if((lpt_telMo != null) && StringUtils.isValidString(lpt_telMo.getTelefono()))
								{
									PersonaTelefonoDAO lptd_DAO;
									PersonaTelefono    lpt_telefono;
									boolean            lb_crearTelefono;

									lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(ldm_manager);
									lpt_telefono         = null;
									lb_crearTelefono     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpt_telMo.getIdTelefono())
									)
									{
										lpt_telMo.setIdPersona(ls_idPersona);

										lpt_telefono = lptd_DAO.findById(lpt_telMo);
									}

									if(lpt_telefono == null)
									{
										long   ll_max;
										String ls_idDepartamentoTelFi;
										String ls_idDepartamentoTelMo;

										lb_crearTelefono = true;
										lpt_telMo.setIdPersona(ls_idPersona);
										lpt_telMo.setTipoTelefono("M");
										lpt_telMo.setFechaDesde(new Date());
										lpt_telMo.setIdUsuarioCreacion(as_userId);
										lpt_telMo.setIpCreacion(as_remoteIp);
										ls_idDepartamentoTelMo = lpt_telMo.getIdDepartamento();

										if((lpt_telFi != null) && !(StringUtils.isValidString(ls_idDepartamentoTelMo)))
										{
											ls_idDepartamentoTelFi = lpt_telFi.getIdDepartamento();

											if(StringUtils.isValidString(ls_idDepartamentoTelFi))
												lpt_telMo.setIdDepartamento(ls_idDepartamentoTelFi);
										}

										ll_max = lptd_DAO.insertOrUpdate(lpt_telMo, lb_crearTelefono);

										if(ll_max > 0)
											lpt_telMo.setIdTelefono(StringUtils.getString(ll_max));
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
									}
								}
							}

							{
								if((lpc_correo != null) && StringUtils.isValidString(lpc_correo.getCorreoElectronico()))
								{
									PersonaCorreoElectronicoDAO lpced_DAO;
									PersonaCorreoElectronico    lpc_datos;
									boolean                     lb_crearCorreo;

									lpced_DAO          = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
									lpc_datos          = null;
									lb_crearCorreo     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpc_correo.getIdCorreoElectronico())
									)
									{
										lpc_correo.setIdPersona(ls_idPersona);

										lpc_datos = lpced_DAO.findById(lpc_correo);
									}

									if(lpc_datos == null)
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

							if(ar_registro.isProcesoDevoluciones())
							{
								String ls_idSolicitud;

								ls_idSolicitud = lso_solicitud.getIdSolicitud();

								if(StringUtils.isValidString(ls_idSolicitud))
								{
									DevolucionDineroDAO dd_DAO;
									DevolucionDinero    ldd_devolucionDinero;

									dd_DAO                   = DaoCreator.getDevolucionDineroDAO(ldm_manager);
									ldd_devolucionDinero     = dd_DAO.findByIdSolicitud(ls_idSolicitud);

									if(ldd_devolucionDinero != null)
									{
										DevolucionDinero ldd_devDineroTmp;

										ldd_devDineroTmp = ar_registro.getDevolucionDinero();

										if(ldd_devDineroTmp != null)
										{
											ldd_devolucionDinero.setIdPersonaTitularCuenta(ls_idPersona);
											ldd_devolucionDinero.setIdEntidadRecaudadora(
											    ldd_devDineroTmp.getIdEntidadRecaudadora()
											);
											ldd_devolucionDinero.setTipoCuenta(ldd_devDineroTmp.getTipoCuenta());
											ldd_devolucionDinero.setNumeroCuenta(ldd_devDineroTmp.getNumeroCuenta());
											ldd_devolucionDinero.setIdUsuarioModificacion(as_userId);
											ldd_devolucionDinero.setIpModificacion(as_remoteIp);

											dd_DAO.update(ldd_devolucionDinero);
										}
									}
								}
							}
							else
							{
								SolicitudIntervinienteDAO lsid_DAO;
								SolicitudInterviniente    lso_datosSol;
								boolean                   lb_insertar;

								lsid_DAO         = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager);
								lso_datosSol     = null;
								lb_insertar      = false;

								if(
								    StringUtils.isValidString(ls_idPersona)
									    && StringUtils.isValidString(lsi_solicitudInter.getIdPersona())
								)
								{
									lsi_solicitudInter.setIdPersona(ls_idPersona);

									lso_datosSol = lsid_DAO.findById(lsi_solicitudInter);
								}

								if(lso_datosSol == null)
								{
									String ls_rol;
									ls_rol = "";

									if(lso_solicitud != null)
									{
										lsi_solicitudInter.setIdPersona(ls_idPersona);
										lsi_solicitudInter.setIdSolicitud(lso_solicitud.getIdSolicitud());
									}

									lso_datosSol = lsid_DAO.findById(lsi_solicitudInter);

									if(lso_datosSol != null)
									{
										SolicitudInterviniente lsi_parametros;
										lsi_parametros = ar_registro.getSolicitudInterviniente();

										if((lsi_parametros != null) && !ar_registro.isCalificacion())
										{
											ls_rol = lsi_parametros.getRolPersona();

											lsi_solicitudInter.setRolPersona(ls_rol);

											lso_datosSol = lsid_DAO.findBySolicitudPersonaRol(lsi_solicitudInter);

											if(lso_datosSol != null)
											{
												Object[] loa_messageArgs = new String[1];
												loa_messageArgs[0] = ls_rol;

												throw new B2BException(
												    ErrorKeys.ERROR_INTERVINIENTE_REPETIDO, loa_messageArgs
												);
											}
										}

										lb_insertar = false;
									}
									else
										lb_insertar = true;
								}

								if(StringUtils.isValidString(ls_idAutorizacionNotificacion))
								{
									if(
									    ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.CORREO_ELECTRONICO
										    )
									)
									{
										if(lpc_correo != null)
											ls_idCorreoNo = lpc_correo.getIdCorreoElectronico();
									}
									else if(
									    ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_RESIDENCIA
										    )
									)
									{
										PersonaDireccion lpd_dirRes;
										lpd_dirRes = ar_registro.getDireccionResidencia();

										if(lpd_dirRes != null)
											ls_idDireccionNo = lpd_dirRes.getIdDireccion();
									}
									else if(
									    ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
										    )
									)
									{
										PersonaDireccion lpd_dirCorr;
										lpd_dirCorr = ar_registro.getDireccionCorrespondencia();

										if(lpd_dirCorr != null)
											ls_idDireccionNo = lpd_dirCorr.getIdDireccion();
									}
								}

								if(ab_siNotificaCorr)
								{
									lsi_solicitudInter.setIdAutorizacionNotificacion(
									    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									);
									lsi_solicitudInter.setIdAutorizacionComunicacion(
									    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									);
								}

								if(lb_insertar)
								{
									lsi_solicitudInter.setIdUsuarioCreacion(as_userId);
									lsi_solicitudInter.setIpCreacion(as_remoteIp);
								}
								else
								{
									lsi_solicitudInter.setIdUsuarioModificacion(as_userId);
									lsi_solicitudInter.setIpModificacion(as_remoteIp);
								}

								lsi_solicitudInter.setIdCorreoElectronico(ls_idCorreoNo);
								lsi_solicitudInter.setIdDireccion(ls_idDireccionNo);
								lsi_solicitudInter.setIdTelefono(ls_idTelefonoNo);
								lsi_solicitudInter.setIdCorreoElectronicoComunicacion(ls_idCorreoCo);
								lsi_solicitudInter.setIdDireccionComunicacion(ls_idDireccionCo);
								lsi_solicitudInter.setIdTelefonoComunicacion(ls_idTelefonoCo);

								lot_oficiosTextoSol.setIdSolicitud(ls_idSolicitudInter);
								lot_oficiosTextoSol.setIdUsuarioCreacion(as_userId);
								lot_oficiosTextoSol.setIpCreacion(as_remoteIp);
								lot_oficiosTextoSol.setIdPersonaNotificar(lpc_correo.getIdPersona());

								lsid_DAO.insertOrUpdate(lsi_solicitudInter, lb_insertar);
								lotd_DAO.insertOrUpdate(lot_oficiosTextoSol, true);
							}
						}
					}
					else
					{
						if(StringUtils.isValidString(ls_dirRe))
							validarDireccion(lpd_dirRe, true);

						if(StringUtils.isValidString(ls_dirCor))
							validarDireccion(lpd_dirCor, false);

						if((lpt_telMo != null) && (lpt_telFi != null) && (lpc_correo != null))
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
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

						{
							String ls_idPersona;

							ls_idPersona = marcarFlagPersona(
								    ldm_manager, lp_parametros, as_userId, ar_registro.getIpCreacion()
								);

							if(lp_parametros.getIdDocumentoTipo().equalsIgnoreCase(EstadoCommon.SE))
							{
								Constantes lc_constante;

								lc_constante = new Constantes();

								lc_constante.setIdConstante(ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA);
								lc_constante = findConstante(lc_constante);

								if(lc_constante != null)
								{
									BigInteger lbi_secuencia;
									lbi_secuencia = lc_constante.getEntero().add(BigInteger.valueOf(1));

									lc_constante.setEntero(lbi_secuencia);
									DaoCreator.getConstantesDAO(ldm_manager).insertOrUpdate(lc_constante, false);

									lp_parametros.setNumeroDocumento(StringUtils.getString(lbi_secuencia));
								}
							}

							if(ar_registro.isPersonaCargada())
							{
								Persona lp_temp;

								lp_temp     = new Persona();

								lp_temp = lpd_DAO.findDataCalificador(lp_parametros);

								if(lp_temp != null)
								{
									lp_temp     = lpd_DAO.findById(lp_temp);

									ls_idPersona = lp_temp.getIdPersona();
								}

								ar_registro.setIdPersona(ls_idPersona);
							}
							else
								ar_registro.setIdPersona(ls_idPersona);

							{
								PersonaDireccion lpd_direccionResidencia;
								lpd_direccionResidencia = ar_registro.getDireccionResidencia();

								if(
								    (lpd_direccionResidencia != null)
									    && StringUtils.isValidString(lpd_direccionResidencia.getDireccion())
								)
								{
									PersonaDireccionDAO lpdd_DAO;
									PersonaDireccion    lpd_direccion;
									boolean             lb_crearDireccion;

									lpdd_DAO              = DaoCreator.getPersonaDireccionDAO(ldm_manager);
									lpd_direccion         = null;
									lb_crearDireccion     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpd_direccionResidencia.getIdDireccion())
									)
									{
										lpd_direccionResidencia.setIdPersona(ls_idPersona);

										lpd_direccion = lpdd_DAO.findById(lpd_direccionResidencia);
									}

									if(lpd_direccion == null)
									{
										long ll_max;

										lb_crearDireccion = true;
										lpd_direccionResidencia.setIdPersona(ls_idPersona);
										lpd_direccionResidencia.setFechaDesde(new Date());

										if(lb_crearDireccion)
										{
											String ls_idTipoPredio;

											ls_idTipoPredio = lpd_direccionResidencia.getIdTipoPredio();

											if(!StringUtils.isValidString(ls_idTipoPredio))
												throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);

											lpd_direccionResidencia.setIdUsuarioCreacion(as_userId);
											lpd_direccionResidencia.setIpCreacion(as_remoteIp);
										}
										else
										{
											lpd_direccionResidencia.setIdUsuarioModificacion(as_userId);
											lpd_direccionResidencia.setIpModificacion(as_remoteIp);
										}

										ll_max = lpdd_DAO.insertOrUpdate(lpd_direccionResidencia, lb_crearDireccion);

										if(ll_max > 0)
											lpd_direccionResidencia.setIdDireccion(StringUtils.getString(ll_max));
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
									}
								}
							}

							{
								PersonaDireccion lpd_direccionCorrespondencia;
								lpd_direccionCorrespondencia = ar_registro.getDireccionCorrespondencia();

								if(
								    (lpd_direccionCorrespondencia != null)
									    && StringUtils.isValidString(lpd_direccionCorrespondencia.getDireccion())
								)
								{
									PersonaDireccionDAO lpdd_DAO;
									PersonaDireccion    lpd_direccion;
									boolean             lb_crearDireccion;

									lpdd_DAO              = DaoCreator.getPersonaDireccionDAO(ldm_manager);
									lpd_direccion         = null;
									lb_crearDireccion     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpd_direccionCorrespondencia.getIdDireccion())
									)
									{
										lpd_direccionCorrespondencia.setIdPersona(ls_idPersona);

										lpd_direccion = lpdd_DAO.findById(lpd_direccionCorrespondencia);
									}

									if(lpd_direccion == null)
									{
										long ll_max;

										lb_crearDireccion = true;

										lpd_direccionCorrespondencia.setIdPersona(ls_idPersona);
										lpd_direccionCorrespondencia.setFechaDesde(new Date());

										if(lb_crearDireccion)
										{
											String ls_idTipoPredio;

											ls_idTipoPredio = lpd_direccionCorrespondencia.getIdTipoPredio();

											if(!StringUtils.isValidString(ls_idTipoPredio))
												throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);

											lpd_direccionCorrespondencia.setIdUsuarioCreacion(as_userId);
											lpd_direccionCorrespondencia.setIpCreacion(as_remoteIp);
										}
										else
										{
											lpd_direccionCorrespondencia.setIdUsuarioModificacion(as_userId);
											lpd_direccionCorrespondencia.setIpModificacion(as_remoteIp);
										}

										ll_max = lpdd_DAO.insertOrUpdate(
											    lpd_direccionCorrespondencia, lb_crearDireccion
											);

										if(ll_max > 0)
											lpd_direccionCorrespondencia.setIdDireccion(StringUtils.getString(ll_max));
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
									}
								}
							}

							{
								if((lpt_telFi != null) && StringUtils.isValidString(lpt_telFi.getTelefono()))
								{
									PersonaTelefonoDAO lptd_DAO;
									PersonaTelefono    lpt_telefono;
									boolean            lb_crearTelefono;

									lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(ldm_manager);
									lpt_telefono         = null;
									lb_crearTelefono     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpt_telFi.getIdTelefono())
									)
									{
										lpt_telFi.setIdPersona(ls_idPersona);

										lpt_telefono = lptd_DAO.findById(lpt_telFi);
									}

									if(lpt_telefono == null)
									{
										long ll_max;

										lb_crearTelefono = true;
										lpt_telFi.setIdPersona(ls_idPersona);
										lpt_telFi.setTipoTelefono("F");
										lpt_telFi.setFechaDesde(new Date());

										if(lb_crearTelefono)
										{
											lpt_telFi.setIdUsuarioCreacion(as_userId);
											lpt_telFi.setIpCreacion(as_remoteIp);
										}
										else
										{
											lpt_telFi.setIdUsuarioModificacion(as_userId);
											lpt_telFi.setIpModificacion(as_remoteIp);
										}

										ll_max = lptd_DAO.insertOrUpdate(lpt_telFi, lb_crearTelefono);

										if(ll_max > 0)
											lpt_telFi.setIdTelefono(StringUtils.getString(ll_max));
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
									}
								}
							}

							{
								if((lpt_telMo != null) && StringUtils.isValidString(lpt_telMo.getTelefono()))
								{
									PersonaTelefonoDAO lptd_DAO;
									PersonaTelefono    lpt_telefono;
									boolean            lb_crearTelefono;

									lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(ldm_manager);
									lpt_telefono         = null;
									lb_crearTelefono     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpt_telMo.getIdTelefono())
									)
									{
										lpt_telMo.setIdPersona(ls_idPersona);

										lpt_telefono = lptd_DAO.findById(lpt_telMo);
									}

									if(lpt_telefono == null)
									{
										long ll_max;

										lb_crearTelefono = true;
										lpt_telMo.setIdPersona(ls_idPersona);
										lpt_telMo.setTipoTelefono("M");
										lpt_telMo.setFechaDesde(new Date());

										if(lb_crearTelefono)
										{
											lpt_telMo.setIdUsuarioCreacion(as_userId);
											lpt_telMo.setIpCreacion(as_remoteIp);
										}
										else
										{
											lpt_telMo.setIdUsuarioModificacion(as_userId);
											lpt_telMo.setIpModificacion(as_remoteIp);
										}

										ll_max = lptd_DAO.insertOrUpdate(lpt_telMo, lb_crearTelefono);

										if(ll_max > 0)
											lpt_telMo.setIdTelefono(StringUtils.getString(ll_max));
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
									}
								}
							}

							{
								if((lpc_correo != null) && StringUtils.isValidString(lpc_correo.getCorreoElectronico()))
								{
									PersonaCorreoElectronicoDAO lpced_DAO;
									PersonaCorreoElectronico    lpc_datos;
									boolean                     lb_crearCorreo;

									lpced_DAO          = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
									lpc_datos          = null;
									lb_crearCorreo     = false;

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(lpc_correo.getIdCorreoElectronico())
									)
									{
										lpc_correo.setIdPersona(ls_idPersona);

										lpc_datos = lpced_DAO.findById(lpc_correo);
									}

									if(lpc_datos == null)
									{
										long ll_max;

										lb_crearCorreo = true;
										lpc_correo.setIdPersona(ls_idPersona);

										if(lb_crearCorreo)
										{
											lpc_correo.setIdUsuarioCreacion(as_userId);
											lpc_correo.setIpCreacion(as_remoteIp);
										}
										else
										{
											lpc_correo.setIdUsuarioModificacion(as_userId);
											lpc_correo.setIpModificacion(as_remoteIp);
										}

										lpc_correo.setFechaDesde(new Date());

										ll_max = lpced_DAO.insertOrUpdate(lpc_correo, lb_crearCorreo);

										if(ll_max > 0)
											lpc_correo.setIdCorreoElectronico(StringUtils.getString(ll_max));
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
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

			clh_LOGGER.error("salvarInterviniente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Método encargado de salvar la información de los predios y su relación con los actos de la solicitud.
	 *
	 * @param acdpr_predioRegistro Colección que contiene la información de los predio registro que se desean guardar.
	 * @param ab_validacionActos700 Variable de tipo booleana que valida si los actos pertenecen al grupo 700.
	 * @param acgp_gravamenes Colección que contiene la información de los gravamenes pendientes si existen para el proceso.
	 * @param acpv_prohibiciones Colección que contiene la información de las prohibiciones de VPM si existen para el proceso.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_solicitud Variable de tipo String que contiene el id de la solicitud en tramite.
	 * @param amss_actosParciales Colección que contiene la información de los actos para registro parcial.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarPrediosConTramite(
	    Collection<DatosPredioRegistro> acdpr_predioRegistro, boolean ab_validacionActos700,
	    Collection<GravamenPendiente> acgp_gravamenes, Collection<ProhibicionVPM> acpv_prohibiciones, String as_userId,
	    Solicitud as_solicitud, Map<String, String> amss_actosParciales, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		SolicitudMatriculaActoDAO lsma_DAO;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(!CollectionUtils.isValidCollection(acdpr_predioRegistro))
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(as_solicitud == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			String ls_solicitud;
			ls_solicitud = StringUtils.getStringNotNull(as_solicitud.getIdSolicitud());

			if(ab_validacionActos700)
			{
				if(
				    !CollectionUtils.isValidCollection(acgp_gravamenes)
					    && !CollectionUtils.isValidCollection(acpv_prohibiciones)
				)
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

				AnotacionCancelacionDAO lac_DAO;
				lac_DAO = DaoCreator.getAnotacionCancelacionDAO(ldm_manager);

				if(
				    CollectionUtils.isValidCollection(acgp_gravamenes)
					    && CollectionUtils.isValidCollection(acpv_prohibiciones)
				)
				{
					for(GravamenPendiente lgp_gravamen : acgp_gravamenes)
					{
						if(lgp_gravamen != null)
						{
							for(ProhibicionVPM lpv_temp : acpv_prohibiciones)
							{
								if(lpv_temp != null)
								{
									if(lgp_gravamen.getCodigoActo().equalsIgnoreCase(lpv_temp.getCodigoActo()))
									{
										if(lgp_gravamen.isSeleccionado())
											lpv_temp.setSeleccionado(true);
									}
								}
							}
						}
					}
				}

				if(CollectionUtils.isValidCollection(acgp_gravamenes))
				{
					for(GravamenPendiente lgp_gravamen : acgp_gravamenes)
					{
						if(lgp_gravamen != null)
						{
							AnotacionCancelacion lac_temp;
							lac_temp = new AnotacionCancelacion();

							lac_temp.setIdCirculo(lgp_gravamen.getIdCirculoPredio());
							lac_temp.setIdMatricula(NumericUtils.getLong(lgp_gravamen.getIdMatriculaPredio()));
							lac_temp.setIdAnotacion1(NumericUtils.getLongWrapper(lgp_gravamen.getIdAnotacion()));
							lac_temp.setIdSolicitud(ls_solicitud);

							AnotacionCancelacion lac_anotacion;
							lac_anotacion     = new AnotacionCancelacion();

							lac_anotacion = lac_DAO.findByCirMatAnotSol(lac_temp);

							if((lac_anotacion == null) && lgp_gravamen.isSeleccionado())
							{
								lac_temp.setIdUsuarioCreacion(as_userId);
								lac_temp.setIpCreacion(as_remoteIp);

								lac_DAO.insert(lac_temp);
							}

							if((lac_anotacion != null) && !lgp_gravamen.isSeleccionado())
								lac_DAO.delete(lac_anotacion);
						}
					}
				}

				if(CollectionUtils.isValidCollection(acpv_prohibiciones))
				{
					for(ProhibicionVPM lpv_temp : acpv_prohibiciones)
					{
						if(lpv_temp != null)
						{
							AnotacionCancelacion lac_temp;
							lac_temp = new AnotacionCancelacion();

							lac_temp.setIdCirculo(lpv_temp.getIdCirculoPredio());
							lac_temp.setIdMatricula(NumericUtils.getLong(lpv_temp.getIdMatriculaPredio()));
							lac_temp.setIdAnotacion1(NumericUtils.getLongWrapper(lpv_temp.getIdAnotacion()));
							lac_temp.setIdSolicitud(ls_solicitud);

							AnotacionCancelacion lac_anotacion;
							lac_anotacion     = new AnotacionCancelacion();

							lac_anotacion = lac_DAO.findByCirMatAnotSol(lac_temp);

							if((lac_anotacion == null) && lpv_temp.isSeleccionado())
							{
								lac_temp.setIdUsuarioCreacion(as_userId);
								lac_temp.setIpCreacion(as_remoteIp);

								lac_DAO.insert(lac_temp);
							}

							if((lac_anotacion != null) && !lpv_temp.isSeleccionado())
								lac_DAO.delete(lac_anotacion);
						}
					}
				}
			}

			ActoDAO                  la_DAO;
			TipoRequiereMatriculaDAO ltrm_DAO;

			la_DAO       = DaoCreator.getActoDAO(ldm_manager);
			ltrm_DAO     = DaoCreator.getTipoRequiereMatriculaDAO(ldm_manager);

			for(DatosPredioRegistro ldpr_data : acdpr_predioRegistro)
			{
				if(ldpr_data != null)
				{
					Collection<String>          lcs_idActos;
					String                      ls_tipoReqMat;
					TipoRequiereMatricula       ltrm_tipoSeleccion;
					Collection<DatosAntSistema> lcdas_prediosAgregados;

					lcs_idActos                = ldpr_data.getIdActos();
					ls_tipoReqMat              = StringUtils.getStringNotNull(ldpr_data.getTipoRequiereMatricula());
					lcdas_prediosAgregados     = ldpr_data.getDatosAntSistemaAgregados();

					if(CollectionUtils.isValidCollection(lcdas_prediosAgregados))
					{
						AntSistemaActoDAO lasa_DAO;

						lasa_DAO = DaoCreator.getAntSistemaActoDAO(ldm_manager);

						lasa_DAO.deleteBySolicitud(ls_solicitud);

						for(DatosAntSistema ldas_datos : lcdas_prediosAgregados)
						{
							if(ldas_datos != null)
							{
								Map<String, Boolean> lmsb_relacionActoPredio;

								lmsb_relacionActoPredio = ldas_datos.getActos();

								if(CollectionUtils.isValidCollection(lmsb_relacionActoPredio))
								{
									boolean lb_actoSeleccionado;

									lb_actoSeleccionado = false;

									for(Map.Entry<String, Boolean> lmesb_data : lmsb_relacionActoPredio.entrySet())
									{
										if(lmesb_data != null)
										{
											boolean lb_checked;

											lb_checked = BooleanUtils.getBooleanValue(lmesb_data.getValue());

											if(lb_checked)
											{
												lb_actoSeleccionado = true;

												AntSistemaActo lasa_relacionActoPredioInsert;

												lasa_relacionActoPredioInsert = new AntSistemaActo();

												lasa_relacionActoPredioInsert.setIdSolicitud(ls_solicitud);
												lasa_relacionActoPredioInsert.setIdCirculo(ldas_datos.getIdCirculo());
												;
												lasa_relacionActoPredioInsert.setIdDatosAntSistema(
												    ldas_datos.getIdDatosAntSistema()
												);
												lasa_relacionActoPredioInsert.setIdActo(lmesb_data.getKey());
												lasa_relacionActoPredioInsert.setActivo(EstadoCommon.S);
												lasa_relacionActoPredioInsert.setIdUsuarioCreacion(as_userId);
												lasa_relacionActoPredioInsert.setIpCreacion(as_remoteIp);

												lasa_DAO.insertOrUpdate(lasa_relacionActoPredioInsert, true);
											}
										}
									}

									if(!lb_actoSeleccionado)
									{
										Object[]         loa_args;
										String           ls_idCirculo;
										String           ls_nombreCirculo;
										CirculoRegistral lcr_circulo;

										loa_args             = new String[4];
										ls_idCirculo         = ldas_datos.getIdCirculo();
										ls_nombreCirculo     = " ";
										lcr_circulo          = new CirculoRegistral();

										lcr_circulo.setIdCirculo(ls_idCirculo);

										lcr_circulo = DaoCreator.getCirculoRegistralDAO(ldm_manager)
												                    .findById(lcr_circulo);

										if(lcr_circulo != null)
											ls_nombreCirculo = lcr_circulo.getNombre();

										loa_args[0]     = ldas_datos.getConsecutivoPredioAntSistema();
										loa_args[1]     = ldas_datos.getNombrePredio();
										loa_args[2]     = ls_idCirculo;
										loa_args[3]     = ls_nombreCirculo;

										throw new B2BException(ErrorKeys.ERROR_SIN_RELACIONAR_PREDIO_ACTO, loa_args);
									}
								}

								if(ldpr_data.isEsTipoActoBaldio())
								{
									MatriculaSegregacion lms_matriculaSegregacion;
									lms_matriculaSegregacion = ldas_datos.getMatriculaSegregacion();

									if(lms_matriculaSegregacion != null)
									{
										String ls_temp;
										ls_temp = lms_matriculaSegregacion.getAreaTerrenoLectura();

										if(!StringUtils.isValidString(ls_temp))
											throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);

										lms_matriculaSegregacion.setIdSolicitud(ldas_datos.getIdSolicitud());
										lms_matriculaSegregacion.setNombrePredio(ldas_datos.getNombrePredio());
										lms_matriculaSegregacion.setCantidadCertificados(
										    ldas_datos.getCantidadCertificados()
										);
										lms_matriculaSegregacion.setNombrePredio(
										    lms_matriculaSegregacion.getNombrePredio()
										);
										lms_matriculaSegregacion.setAreaPrivada(
										    NumericUtils.getDoubleWrapper(
										        lms_matriculaSegregacion.getAreaPrivadaLectura()
										    )
										);
										lms_matriculaSegregacion.setAreaConstruida(
										    NumericUtils.getDoubleWrapper(
										        lms_matriculaSegregacion.getAreaConstruidaLectura()
										    )
										);
										lms_matriculaSegregacion.setAreaTerreno(
										    NumericUtils.getDoubleWrapper(
										        lms_matriculaSegregacion.getAreaTerrenoLectura()
										    )
										);
										lms_matriculaSegregacion.setIdUsuarioCreacion(as_userId);
										lms_matriculaSegregacion.setIpCreacion(as_remoteIp);

										DaoCreator.getMatriculaSegregacionDAO(ldm_manager)
											          .insert(lms_matriculaSegregacion, as_userId);
									}
								}
							}
						}
					}

					ltrm_tipoSeleccion = new TipoRequiereMatricula();

					ltrm_tipoSeleccion.setIdTipoRequiereMatricula(ls_tipoReqMat);

					ltrm_tipoSeleccion = ltrm_DAO.findById(ltrm_tipoSeleccion);

					if(ltrm_tipoSeleccion == null)
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

					if(CollectionUtils.isValidCollection(lcs_idActos) && StringUtils.isValidString(ls_tipoReqMat))
					{
						for(String ls_id : lcs_idActos)
						{
							if(StringUtils.isValidString(ls_id))
							{
								Acto                                                    la_acto;
								com.bachue.snr.prosnr01.model.registro.Acto             la_actoParaHijos;
								Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actosHijos;
								String                                                  ls_idTipoReqMatr;
								String                                                  ls_idProceso;
								String                                                  ls_idSubproceso;

								la_acto              = new Acto();
								la_actoParaHijos     = new com.bachue.snr.prosnr01.model.registro.Acto();
								lca_actosHijos       = null;
								ls_idTipoReqMatr     = ltrm_tipoSeleccion.getIdTipoRequiereMatricula();
								ls_idProceso         = ltrm_tipoSeleccion.getIdProceso();
								ls_idSubproceso      = ltrm_tipoSeleccion.getIdSubproceso();

								la_acto.setIdActo(ls_id);

								la_acto = la_DAO.findById(la_acto);

								if(la_acto == null)
									throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

								la_acto.setIpModificacion(as_remoteIp);
								la_acto.setIdUsuarioModificacion(as_userId);
								la_acto.setIdTipoRequiereMatricula(ls_idTipoReqMatr);
								la_acto.setIdProceso(ls_idProceso);
								la_acto.setIdSubproceso(ls_idSubproceso);

								la_DAO.updateSub(la_acto);
								la_actoParaHijos.setIdSolicitud(ls_solicitud);
								la_actoParaHijos.setIdActoDb(ls_id);

								lca_actosHijos = la_DAO.findHijosById(la_actoParaHijos);

								if(CollectionUtils.isValidCollection(lca_actosHijos))
								{
									for(com.bachue.snr.prosnr01.model.registro.Acto la_data : lca_actosHijos)
									{
										if(la_data != null)
										{
											Acto la_actoHijo;
											la_actoHijo = new Acto();

											la_actoHijo.setIdActo(la_data.getIdActoDb());

											la_actoHijo = la_DAO.findById(la_actoHijo);

											if(la_actoHijo != null)
											{
												la_actoHijo.setIpModificacion(as_remoteIp);
												la_actoHijo.setIdUsuarioModificacion(as_userId);
												la_actoHijo.setIdTipoRequiereMatricula(ls_idTipoReqMatr);
												la_actoHijo.setIdProceso(ls_idProceso);
												la_actoHijo.setIdSubproceso(ls_idSubproceso);

												la_DAO.update(la_actoHijo);
											}
										}
									}
								}
							}
						}
					}

					if(
					    ls_tipoReqMat.equals(TipoRequiereMatriculaCommon.CON_MATRICULA)
						    || ls_tipoReqMat.equals(TipoRequiereMatriculaCommon.DATOS_CON_MATRICULA_Y_SIN_MATRICULA)
					)
					{
						Collection<SolicitudMatricula> lcsm_solicitudMatricula;

						lcsm_solicitudMatricula = ldpr_data.getDatosBandejaPredios();

						if(CollectionUtils.isValidCollection(lcsm_solicitudMatricula))
						{
							long                  ll_cantidadMatriculas;
							SolicitudMatriculaDAO lsm_DAO;

							lsm_DAO      = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
							lsma_DAO     = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);

							ll_cantidadMatriculas = 0L;

							for(SolicitudMatricula lsm_actual : lcsm_solicitudMatricula)
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
										SolicitudMatricula lsm_matriculaInsert;

										lsm_matriculaInsert = new SolicitudMatricula();

										{
											lsm_matriculaInsert.setIdSolicitud(lsm_actual.getIdSolicitud());
											lsm_matriculaInsert.setIdMatricula(ll_matricula);
											lsm_matriculaInsert.setIdCirculo(ls_circuloRegistral);
											lsm_matriculaInsert.setExpedirCertificado(
											    lsm_actual.getExpedirCertificado()
											);
											lsm_matriculaInsert.setCantidadCertificados(
											    lsm_actual.getCantidadCertificados()
											);
											lsm_matriculaInsert.setIdUsuarioCreacion(lsm_actual.getIdUsuarioCreacion());
											lsm_matriculaInsert.setCantidadAperturar(lsm_actual.getCantidadAperturar());
											lsm_matriculaInsert.setCementerio(lsm_actual.getCementerio());

											{
												SolicitudMatricula lsm_matriculaExist;

												lsm_matriculaExist = lsm_DAO.findById(lsm_matriculaInsert);

												if(lsm_matriculaExist != null)
													lsm_DAO.insertOrUpdate(lsm_matriculaInsert, false);
												else
													lsm_DAO.insertOrUpdate(lsm_matriculaInsert, true);

												ll_cantidadMatriculas++;
											}
										}

										{
											Solicitud ls_solicitudTemp;

											ls_solicitudTemp = new Solicitud();

											ls_solicitudTemp.setIdSolicitud(ls_idSolicitud);

											ls_solicitudTemp = DaoCreator.getSolicitudDAO(ldm_manager)
													                         .findById(ls_solicitudTemp);

											if(ls_solicitudTemp != null)
											{
												ls_solicitudTemp.setCantidadMatriculas(
												    NumericUtils.getDoubleWrapper(ll_cantidadMatriculas)
												);
												ls_solicitudTemp.setIdUsuarioModificacion(as_userId);

												DaoCreator.getSolicitudDAO(ldm_manager)
													          .insertOrUpdate(ls_solicitudTemp, false);
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

								lcsma_matriculasActos = ldpr_data.getDatosBandejaActos();

								if(CollectionUtils.isValidCollection(lcsma_matriculasActos))
								{
									for(SolicitudMatriculaActo lsma_actual : lcsma_matriculasActos)
									{
										if(lsma_actual != null)
										{
											SolicitudMatriculaActo lsma_matriculaActoExists;
											String                 ls_idActo;

											lsma_actual.setEstado(EstadoCommon.A);

											ls_idActo = lsma_actual.getIdActo();

											if(CollectionUtils.isValidCollection(amss_actosParciales))
											{
												if(amss_actosParciales.containsKey(ls_idActo))
													lsma_actual.setIdActo(amss_actosParciales.get(ls_idActo));
											}

											lsma_matriculaActoExists = lsma_DAO.findById(lsma_actual);

											if(lsma_matriculaActoExists != null)
											{
												lsma_actual.setIdUsuarioModificacion(as_userId);
												lsma_actual.setIpModificacion(as_remoteIp);
												lsma_DAO.insertOrUpdate(lsma_actual, false);
											}
											else
											{
												lsma_actual.setIdUsuarioCreacion(as_userId);
												lsma_actual.setIpCreacion(as_remoteIp);
												lsma_DAO.insertOrUpdate(lsma_actual, true);
											}
										}
									}
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SIN_PREDIOS_CON_ACTOS);

						Collection<MatriculaSegregacion> lcms_matriculasSegregacion;

						lcms_matriculasSegregacion = ldpr_data.getMatriculasSegregacion();

						if(CollectionUtils.isValidCollection(lcms_matriculasSegregacion))
						{
							MatriculaSegregacionDAO lmsd_DAO;

							lmsd_DAO = DaoCreator.getMatriculaSegregacionDAO(ldm_manager);

							if(StringUtils.isValidString(ls_solicitud))
							{
								Collection<MatriculaSegregacion> lcms_matriculasActuales;
								MatriculaSegregacion             lms_ms;

								lms_ms = new MatriculaSegregacion();

								lms_ms.setIdSolicitud(ls_solicitud);

								lcms_matriculasActuales = lmsd_DAO.findByIdSolicitud(lms_ms, true);

								if(CollectionUtils.isValidCollection(lcms_matriculasActuales))
									lmsd_DAO.deleteBySolicitud(lms_ms);
							}

							for(MatriculaSegregacion loms_tmp : lcms_matriculasSegregacion)
							{
								loms_tmp.setIpCreacion(as_remoteIp);

								loms_tmp.setIdSolicitud(ls_solicitud);

								lmsd_DAO.insert(loms_tmp, as_userId);
							}
						}

						MatriculaSegregacion lms_ms;

						lms_ms = ldpr_data.getMatriculaSegregacion();

						if(lms_ms != null)
						{
							String ls_nombrePredio;

							ls_nombrePredio = lms_ms.getNombrePredio();

							if(StringUtils.isValidString(ls_nombrePredio))
							{
								Constantes              lc_constante;
								MatriculaSegregacion    lms_matriculaSegregacion;
								MatriculaSegregacionDAO lms_DAO;

								lc_constante                 = new Constantes();
								lms_matriculaSegregacion     = new MatriculaSegregacion();
								lms_DAO                      = DaoCreator.getMatriculaSegregacionDAO(ldm_manager);

								lc_constante.setIdConstante(ConstanteCommon.CARACTERES_ESPECIALES);

								lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_constante);

								if(lc_constante != null)
								{
									String ls_caracter;

									ls_caracter = lc_constante.getCaracter();

									if(!StringUtils.isValidString(ls_caracter))
									{
										Object[] loa_messageArgs;

										loa_messageArgs        = new String[1];
										loa_messageArgs[0]     = ConstanteCommon.CARACTERES_ESPECIALES;

										throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
									}

									if(!StringUtils.isValidCharacters(ls_nombrePredio, ls_caracter))
										throw new B2BException(ErrorKeys.ERROR_NOMBRE_PREDIO_CARACTERES);
								}

								lms_ms.setIdSolicitud(ls_solicitud);
								lms_ms.setIpCreacion(as_remoteIp);

								lms_matriculaSegregacion = lms_DAO.findByIdSolicitudCirculo(lms_ms);

								if(lms_matriculaSegregacion != null)
								{
									lms_ms.setMatriculaSegregacion(lms_matriculaSegregacion.getMatriculaSegregacion());
									lms_ms.setIdUsuarioModificacion(as_userId);
									lms_ms.setIpModificacion(as_remoteIp);
									lms_DAO.updateById(lms_ms);
								}
								else
									lms_DAO.insert(lms_ms, as_userId);
							}
							else
							{
								Object[] lao_messageArgs = new String[1];
								lao_messageArgs[0] = lms_ms.getIdCirculoMatriz();
								throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_PREDIO_CIRCULO, lao_messageArgs);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarPrediosConTramite", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * *
	 *
	 * Método encargado de guardar toda la información asociada al proceso de reproducción de constancia.
	 *
	 * @param adrc_parametros Objeto de tipo DataReproduccionConstancia que contiene los datos necesarios para insertar la información requerida.
	 * @param as_usuario  usuario logueado en la aplicacion
	 * @param as_ipRemota  Ip de usuario en sesión
	 * @return  objeto  de tipo DataReproduccionConstancia con los datos resultantes determinados
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DataReproduccionConstancia
	 */
	public synchronized DataReproduccionConstancia salvarReproduccionConstancia(
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
				Solicitud                              lso_solicitud;
				String                                 ls_idSolicitud;
				boolean                                lb_sinConstanciaReproduccion;
				boolean                                lb_sinConstanciaReproduccionTurno;
				SolicitudDAO                           lsd_DAO;
				Documento                              lod_datosDocumento;

				lod_datosDocumento                    = adrc_parametros.getDatosDocumento();
				lcdrc_dataReproduccionConstancia      = adrc_parametros.getDataReproduccionConstancia();
				lcapd_anotacionTemp                   = new ArrayList<AnotacionPredioDireccion>();
				lso_solicitud                         = adrc_parametros.getSolicitud();
				lb_sinConstanciaReproduccion          = adrc_parametros.isSinConstanciaReproduccion();
				lb_sinConstanciaReproduccionTurno     = adrc_parametros.isSinConstanciaReproduccionTurno();

				lsd_DAO     = DaoCreator.getSolicitudDAO(ldm_manager);

				lso_solicitud = lsd_DAO.findById(lso_solicitud);

				if(lso_solicitud == null)
					throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_PARA_LA_SOLICITUD);

				ls_idSolicitud = lso_solicitud.getIdSolicitud();

				if(!StringUtils.isValidString(ls_idSolicitud))
					throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);

				if(lb_sinConstanciaReproduccion || lb_sinConstanciaReproduccionTurno)
				{
					if(lod_datosDocumento != null)
					{
						if(lb_sinConstanciaReproduccion)
						{
							DocumentoDAO          ldd_DAO;
							Documento             ld_documento;
							Collection<Documento> lcd_documentos;

							//pendiente confirmacion de actualizar acc solicitud
							ldd_DAO            = DaoCreator.getDocumentoDAO(ldm_manager);
							lcd_documentos     = ldd_DAO.consultaDocumentosAntSistema(lod_datosDocumento);

							if(!CollectionUtils.isValidCollection(lcd_documentos))
							{
								int li_secuencia;
								li_secuencia = DaoCreator.getUtilDAO(ldm_manager)
										                     .findSecuence(ConsultasUtilidades.CS_BGN_DOCUMENTO_SEC);

								if(li_secuencia > 0)
								{
									Integer li_tmp;
									li_tmp = NumericUtils.getInteger(li_secuencia);

									if(li_tmp != null)
									{
										String ls_idDocumento;
										ld_documento       = new Documento();
										ls_idDocumento     = li_tmp.toString();

										ld_documento.setIdDocumento(ls_idDocumento);
										ld_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));
										ld_documento.setNumero(lod_datosDocumento.getNumero());
										ld_documento.setIdTipoDocumento(lod_datosDocumento.getIdTipoDocumento());
										ld_documento.setFechaDocumento(lod_datosDocumento.getFechaDocumento());

										{
											String ls_idOficinaOrigen;

											ls_idOficinaOrigen = ld_documento.getIdOficinaOrigen();

											ld_documento.setIdOficinaOrigen(ls_idOficinaOrigen);
											ld_documento.setVersion(
											    obtenerVersionMaximaOficinaOrigen(ls_idOficinaOrigen, ldm_manager)
											);
										}

										ld_documento.setIdUsuarioCreacion(as_usuario);
										ld_documento.setIpCreacion(as_ipRemota);

										ldd_DAO.insertOrUpdate(ld_documento, true);

										/*Actualizacion de nuevo id documento en la solicitud*/
										lso_solicitud.setIdDocumento(ls_idDocumento);
										lso_solicitud.setIdUsuarioModificacion(as_usuario);
										lso_solicitud.setIpModificacion(as_ipRemota);

										lsd_DAO.insertOrUpdate(lso_solicitud, false);
									}

									{
										Acto la_acto;
										la_acto = new Acto();

										la_acto.setIdCirculo(lod_datosDocumento.getIdCirculo());
										la_acto.setIdUsuarioCreacion(as_usuario);
										la_acto.setIpCreacion(as_ipRemota);
										la_acto.setIdSolicitud(ls_idSolicitud);

										insertarActoReproduccionConstacia(
										    la_acto, ldm_manager, true, as_usuario, as_ipRemota
										);
									}
								}
							}
						}
						else if(lb_sinConstanciaReproduccionTurno)
						{
							String ls_idTurno;
							ls_idTurno = lod_datosDocumento.getIdTurno();

							if(StringUtils.isValidString(ls_idTurno))
							{
								lso_solicitud.setIdUsuarioModificacion(as_usuario);
								lso_solicitud.setIpModificacion(as_ipRemota);
								lso_solicitud.setTurnoReproduccion(ls_idTurno);

								lsd_DAO.insertOrUpdate(lso_solicitud, false);

								Acto la_acto;
								la_acto = new Acto();

								la_acto.setIdCirculo(lod_datosDocumento.getIdCirculo());
								la_acto.setIdUsuarioCreacion(as_usuario);
								la_acto.setIpCreacion(as_ipRemota);
								la_acto.setIdSolicitud(ls_idSolicitud);

								insertarActoReproduccionConstacia(la_acto, ldm_manager, true, as_usuario, as_ipRemota);
							}
						}
					}
				}

				else
				{
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
							ls_idPersona                   = lso_solicitud.getIdPersona();

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
											lso_solicitud.setIdDocumento(ls_idDocumento);
											lso_solicitud.setIdUsuarioModificacion(as_usuario);
											lso_solicitud.setIpModificacion(as_ipRemota);

											lsd_DAO.insertOrUpdate(lso_solicitud, false);
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

												lI_cantidadCopiasReproducir     = lapd_iterador
														.getCantidadCopiasReproducir();
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
						Map<String, Boolean>                 lcmsb_circulosAgregados;
						Map<String, Integer>                 lcmsb_circulos;

						lcapd_tmp                   = new ArrayList<AnotacionPredioDireccion>();
						lsmd_DAO                    = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
						lsmad_DAO                   = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);
						lsrd_DAO                    = DaoCreator.getConsultaSolicitudReproduccionDAO(ldm_manager);
						lcmsb_circulosAgregados     = new HashMap<String, Boolean>();
						lcmsb_circulos              = new HashMap<String, Integer>();

						ls_idActo = null;

						for(AnotacionPredioDireccion lapd_iterador : lcapd_anotacionTemp)
						{
							if(lapd_iterador != null)
							{
								String     ls_idCirculo;
								String     ls_radicacion;
								long       ll_idMatricula;
								Integer    lI_cantidadCopiasReproducir;
								int        li_cantidadCopiasReproducir;
								BigDecimal lbd_cantidadCopiasReproducir;

								ls_idCirculo                     = lapd_iterador.getIdCirculo();
								ls_radicacion                    = lapd_iterador.getRadicacion();
								ll_idMatricula                   = lapd_iterador.getIdMatricula();
								lI_cantidadCopiasReproducir      = lapd_iterador.getCantidadCopiasReproducir();
								li_cantidadCopiasReproducir      = NumericUtils.getInt(lI_cantidadCopiasReproducir);
								lbd_cantidadCopiasReproducir     = NumericUtils.getBigDecimal(
									    li_cantidadCopiasReproducir
									);

								if(lso_solicitud != null)
								{
									if(!BooleanUtils.getBooleanValue(lcmsb_circulosAgregados.get(ls_idCirculo)))
									{
										Acto la_acto;
										la_acto = new Acto();

										la_acto.setIdSolicitud(ls_idSolicitud);
										la_acto.setCantidadActos(lbd_cantidadCopiasReproducir);
										la_acto.setIdCirculo(ls_idCirculo);
										la_acto.setIdUsuarioCreacion(as_usuario);
										la_acto.setIpCreacion(as_ipRemota);

										lcmsb_circulosAgregados.put(ls_idCirculo, Boolean.TRUE);
										lcmsb_circulos.put(ls_idCirculo, lI_cantidadCopiasReproducir);

										ls_idActo = insertarActoReproduccionConstacia(
											    la_acto, ldm_manager, false, as_usuario, as_ipRemota
											);
									}

									lcapd_tmp.add(lapd_iterador);

									String ls_reproduccionTestamento;
									ls_reproduccionTestamento = lso_solicitud.getReproduccionTestamento();

									if(ls_reproduccionTestamento.equals("N"))
									{
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
											lsm_solicitudMatricula.setIdUsuarioCreacion(as_usuario);
											lsm_solicitudMatricula.setIpCreacion(as_ipRemota);

											lb_solicitudMatricula = lsmd_DAO.findById(lsm_solicitudMatricula) == null;

											lsmd_DAO.insertOrUpdate(lsm_solicitudMatricula, lb_solicitudMatricula);
										}

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

											lsmad_DAO.insertOrUpdate(
											    lsma_solicitudMatriculaActo, lb_solicitudMatriculaActo
											);
										}
									}

									{
										SolicitudReproduccion lsr_solicitudReproduccion;
										lsr_solicitudReproduccion = new SolicitudReproduccion();

										lsr_solicitudReproduccion.setIdSolicitud(ls_idSolicitud);
										lsr_solicitudReproduccion.setIdTurnoReproduccion(ls_radicacion);
										lsr_solicitudReproduccion.setIdCirculo(ls_idCirculo);
										lsr_solicitudReproduccion.setIdUsuarioCreacion(as_usuario);
										lsr_solicitudReproduccion.setIpCreacion(as_ipRemota);

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
								ActoDAO lad_DAO;

								lad_DAO = DaoCreator.getActoDAO(ldm_manager);

								for(Map.Entry<String, Integer> lmsi_entry : lcmsb_infoSeleccionada.entrySet())
								{
									Acto loa_tmp;
									loa_tmp = new Acto();

									loa_tmp.setIdSolicitud(ls_idSolicitud);
									loa_tmp.setIdCirculo(lmsi_entry.getKey());
									loa_tmp.setCantidadActos(BigDecimal.valueOf(lmsi_entry.getValue().longValue()));

									loa_tmp.setIpModificacion(as_ipRemota);
									loa_tmp.setIdUsuarioModificacion(as_usuario);

									lad_DAO.updateCantidadActos(loa_tmp, false);
								}
							}
						}
					}
					else
					{
						if(lod_datosDocumento != null)
						{
							Acto   la_acto;
							String ls_idCirculo;
							la_acto     = new Acto();

							ls_idCirculo = lod_datosDocumento.getIdCirculo();
							la_acto.setIdCirculo(ls_idCirculo);
							la_acto.setIdSolicitud(ls_idSolicitud);
							la_acto.setIdUsuarioCreacion(as_usuario);
							la_acto.setIpCreacion(as_ipRemota);

							insertarActoReproduccionConstacia(la_acto, ldm_manager, false, as_usuario, as_ipRemota);
						}
					}
				}

				Collection<Liquidacion> lol_infoLiquidacion;

				lol_infoLiquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager)
						                            .liquidacionReproduccionConstancia(
						    ls_idSolicitud, adrc_parametros.isDetalleReproduccionConstancia()
						);

				if(lol_infoLiquidacion != null)
					adrc_parametros.setLiquidacion(lol_infoLiquidacion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarReproduccionConstancia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adrc_parametros;
	}

	/**
	 * Método encargado de salvar la información de la matricula ingresada en la solicitud.
	 *
	 * @param asm_parametros Objeto que contiene la información de la matricula que se desea insertar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_localIpAddress Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @param as_remoteIpAddress correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarSolicitudMatricula(
	    SolicitudMatricula asm_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asm_parametros != null)
			{
				boolean               ab_query;
				SolicitudMatricula    lsm_solicitudMatriculaTemp;
				SolicitudMatriculaDAO lsm_DAO;

				lsm_solicitudMatriculaTemp     = new SolicitudMatricula();
				lsm_DAO                        = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);

				lsm_solicitudMatriculaTemp.setIdCirculo(asm_parametros.getIdCirculo());
				lsm_solicitudMatriculaTemp.setIdSolicitud(asm_parametros.getIdSolicitud());
				lsm_solicitudMatriculaTemp.setIdMatricula(asm_parametros.getIdMatricula());

				lsm_solicitudMatriculaTemp     = lsm_DAO.findById(asm_parametros);

				ab_query = (lsm_solicitudMatriculaTemp != null) ? false : true;

				if(ab_query)
				{
					asm_parametros.setIdUsuarioCreacion(as_userId);
					asm_parametros.setIpCreacion(as_remoteIpAddress);
				}
				else
				{
					asm_parametros.setIdUsuarioModificacion(as_userId);
					asm_parametros.setIpModificacion(as_remoteIpAddress);
				}

				lsm_DAO.insertOrUpdate(asm_parametros, ab_query);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarSolicitudMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar los tipo documentales para el proceso de correcciones.
	 *
	 * @param acacd_documentalesCorrecciones Colección que contiene la información de los tipos documentales que se desean insertar.
	 * @param as_solicitud Objeto que contiene la información de la solicitud.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTiposDocumentalesCorrecciones(
	    Collection<TipoDocumental> acacd_documentalesCorrecciones, Solicitud as_solicitud, String as_userId,
	    String                     as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acacd_documentalesCorrecciones))
			{
				AccCompletitudDocumentalDAO lacd_DAO;

				lacd_DAO = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);

				for(TipoDocumental lacd_iterador : acacd_documentalesCorrecciones)
				{
					if(lacd_iterador != null)
					{
						AccCompletitudDocumental lacd_documental;
						String                   ls_idTurnoCertificado;
						String                   ls_observaciones;
						String                   ls_idSolicitud;
						String                   ls_idTipoDocumental;

						lacd_documental           = new AccCompletitudDocumental();
						ls_idTurnoCertificado     = lacd_iterador.getIdTurnoCertificadoCorrecciones();
						ls_observaciones          = lacd_iterador.getObservaciones();
						ls_idSolicitud            = as_solicitud.getIdSolicitud();
						ls_idTipoDocumental       = lacd_iterador.getIdTipoDocumental();

						if(StringUtils.isValidString(ls_idTurnoCertificado))
						{
							if(StringUtils.isValidString(ls_idSolicitud))
								turnoCertificado(ls_idTurnoCertificado, ls_idSolicitud, true, ldm_manager);
							else
								throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);
						}

						if(StringUtils.isValidString(ls_observaciones))
						{
							if(ls_observaciones.length() > 200)
								throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM);
						}

						lacd_documental.setIdSolicitud(ls_idSolicitud);
						lacd_documental.setIdTipoDocumental(ls_idTipoDocumental);
						lacd_documental.setIdTurnoCertificadoCorrecciones(ls_idTurnoCertificado);
						lacd_documental.setObservaciones(ls_observaciones);
						lacd_documental.setObligatorioTipoDocumental(EstadoCommon.S);
						lacd_documental.setIdUsuarioCreacion(as_userId);
						lacd_documental.setIpCreacion(as_remoteIp);
						lacd_documental.setDigitalizado(EstadoCommon.N);

						lacd_DAO.insert(lacd_documental);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTiposDocumentalesCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Retorna el valor del objeto de Registro.
	 *
	 * @param ar_registro correspondiente al valor del tipo de objeto Registro
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_ipLocal correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro salvarTramite(Registro ar_registro, String as_userId, String as_ipLocal)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_registro != null)
			{
				Solicitud         lso_solicitud;
				SolicitudAsociada lsa_solicitudAsociada;

				lso_solicitud             = ar_registro.getSolicitud();
				lsa_solicitudAsociada     = null;

				if(lso_solicitud != null)
				{
					if(!StringUtils.isValidString(lso_solicitud.getIdSubproceso()))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_TRAMITE);

					String ls_idTipoRecepcion;
					ls_idTipoRecepcion = lso_solicitud.getIdTipoRecepcion();

					if(!StringUtils.isValidString(ls_idTipoRecepcion))
					{
						if(StringUtils.isValidString(lso_solicitud.getIdSubproceso()))
						{
							if(!lso_solicitud.getIdSubproceso().equalsIgnoreCase("4"))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PROCEDENCIA);
						}
					}

					if(StringUtils.isValidString(ls_idTipoRecepcion))
					{
						if(ls_idTipoRecepcion.equalsIgnoreCase("6"))
						{
							{
								String ls_radicadoPQRS;
								ls_radicadoPQRS = lso_solicitud.getRadicadoPQRS();

								if(!StringUtils.isValidString(ls_radicadoPQRS))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_RADICADO_PQRS);
							}

							{
								String ls_derechoPeticion;
								ls_derechoPeticion = lso_solicitud.getDerechoPeticion();

								if(StringUtils.isValidString(ls_derechoPeticion))
								{
									if(ls_derechoPeticion.equalsIgnoreCase("true"))
										lso_solicitud.setDerechoPeticion(EstadoCommon.S);
									else
										lso_solicitud.setDerechoPeticion(EstadoCommon.N);
								}
							}
						}
					}

					String ls_idSubproceso;
					ls_idSubproceso = lso_solicitud.getIdSubproceso();

					if(ls_idSubproceso.equalsIgnoreCase("3") || ls_idSubproceso.equalsIgnoreCase("5"))
					{
						String ls_idTurnoAnt;
						String ls_nirAsociado;

						lsa_solicitudAsociada     = new SolicitudAsociada();
						ls_idTurnoAnt             = lso_solicitud.getIdTurnoAnt();
						ls_nirAsociado            = lso_solicitud.getNirAsociado();

						if(!StringUtils.isValidString(ls_idTurnoAnt))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_TURNO_ANTERIOR);

						Turno lt_turnoAnt;

						lt_turnoAnt = new Turno();

						lt_turnoAnt.setIdTurno(ls_idTurnoAnt);

						lt_turnoAnt = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turnoAnt);

						if(lt_turnoAnt != null)
						{
							String    ls_idSolicitudTurno;
							Solicitud ls_solicitud;

							ls_idSolicitudTurno     = lt_turnoAnt.getIdSolicitud();
							ls_solicitud            = null;

							if(StringUtils.isValidString(ls_nirAsociado))
							{
								lso_solicitud.setNir(ls_nirAsociado);

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(lso_solicitud);

								if(ls_solicitud != null)
								{
									Solicitud ls_solicitudTemp;
									String    ls_nirSolicitudTurno;

									ls_solicitudTemp         = new Solicitud();
									ls_nirSolicitudTurno     = null;

									ls_solicitudTemp.setIdSolicitud(ls_idSolicitudTurno);

									ls_solicitudTemp = DaoCreator.getSolicitudDAO(ldm_manager).findById(
										    ls_solicitudTemp
										);

									if(ls_solicitudTemp != null)
										ls_nirSolicitudTurno = ls_solicitudTemp.getNir();

									if(
									    StringUtils.isValidString(ls_nirAsociado)
										    && StringUtils.isValidString(ls_nirSolicitudTurno)
									)
									{
										if(ls_nirAsociado.equalsIgnoreCase(ls_nirSolicitudTurno))
										{
											lsa_solicitudAsociada.setIdSolicitud1(ls_solicitud.getIdSolicitud());
											lsa_solicitudAsociada.setIdSolicitud(lso_solicitud.getIdSolicitud());
										}
										else
											throw new B2BException(ErrorKeys.ERROR_NIR_TURNO_RELACION);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_NIR_TURNO_INVALIDO);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_NIR_INVALIDO);
							}
							else
							{
								ls_solicitud = new Solicitud();

								ls_solicitud.setIdSolicitud(ls_idSolicitudTurno);

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

								if(ls_solicitud != null)
								{
									String ls_idSolicitudNir;

									ls_idSolicitudNir = ls_solicitud.getIdSolicitud();

									if(StringUtils.isValidString(ls_idSolicitudNir))
									{
										lsa_solicitudAsociada.setIdSolicitud1(ls_idSolicitudNir);
										lsa_solicitudAsociada.setIdSolicitud(lso_solicitud.getIdSolicitud());
									}
									else
										throw new B2BException(ErrorKeys.ERROR_SIN_NIR_PARA_TURNO);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_SIN_NIR_PARA_TURNO);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_TURNO_ANT_INVALIDO);

						{
							String           ls_idTurno;
							TurnoHistoria    lth_turnoHistoria;
							TurnoHistoriaDAO lth_DAO;

							ls_idTurno            = lt_turnoAnt.getIdTurno();
							lth_turnoHistoria     = new TurnoHistoria();
							lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

							if(StringUtils.isValidString(ls_idTurno))
								lth_turnoHistoria.setIdTurno(ls_idTurno);

							lth_turnoHistoria = lth_DAO.findByIdTurnoDesc(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								BigDecimal lbd_idEtapa;

								lbd_idEtapa = lth_turnoHistoria.getIdEtapa();

								if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
								{
									long ll_idEtapaActual;

									ll_idEtapaActual = NumericUtils.getLong(lbd_idEtapa);

									if(
									    !((ll_idEtapaActual == EtapaCommon.FINALIZACION_PROCESO_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA)
										    || (ll_idEtapaActual == EtapaCommon.FINALIZACION_PROCESO_DE_REGISTRO_PARCIAL))
									)
										throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA_501_502);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
							}
							else
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
						}
					}

					{
						SolicitudDAO ls_DAO;
						Solicitud    ls_update;

						ls_DAO     = DaoCreator.getSolicitudDAO(ldm_manager);

						ls_update = ls_DAO.findById(lso_solicitud);

						if(ls_update != null)
						{
							ls_update.setIdSubproceso(lso_solicitud.getIdSubproceso());
							ls_update.setIdTipoRecepcion(lso_solicitud.getIdTipoRecepcion());
							ls_update.setRadicadoPQRS(lso_solicitud.getRadicadoPQRS());
							ls_update.setDerechoPeticion(lso_solicitud.getDerechoPeticion());
							ls_update.setIdTurnoAnt(lso_solicitud.getIdTurnoAnt());
							ls_update.setIdUsuarioCreacion(as_userId);
							ls_update.setIdUsuarioModificacion(as_userId);

							ls_DAO.insertOrUpdate(ls_update, false);
						}

						if(lsa_solicitudAsociada != null)
						{
							SolicitudAsociadaDAO lsa_DAO;
							SolicitudAsociada    lsa_temp;
							boolean              lb_query;

							lsa_DAO      = DaoCreator.getSolicitudAsociadaDAO(ldm_manager);
							lsa_temp     = lsa_DAO.findByIdSolicitud(lsa_solicitudAsociada);

							if(lsa_temp != null)
							{
								lb_query = false;
								lsa_solicitudAsociada.setIdUsuarioModificacion(as_userId);
								lsa_solicitudAsociada.setIpModificacion(as_ipLocal);
							}
							else
							{
								lb_query = true;
								lsa_solicitudAsociada.setIdUsuarioCreacion(as_userId);
								lsa_solicitudAsociada.setIpCreacion(as_ipLocal);
							}

							lsa_DAO.insertOrUpdate(lsa_solicitudAsociada, lb_query);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTramite", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param aoa_oa correspondiente al valor del tipo de objeto Acto
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String saveActoConstancia(
	    com.bachue.snr.prosnr01.model.registro.Acto aoa_oa, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		ActoDAO     la_dao;
		TipoActoDAO lotad_tad;
		String      ls_idActoBd;
		boolean     lb_b;
		Object[]    aoa_messageArgs;
		boolean     lb_aperturaMatricula;
		boolean     lb_inscripcionAdicional;
		boolean     lb_segregacion;

		aoa_messageArgs             = new String[1];
		aoa_messageArgs[0]          = " ejecutoria ";
		ldm_manager                 = DaoManagerFactory.getDAOManager();
		lb_b                        = true;
		ls_idActoBd                 = null;
		lb_aperturaMatricula        = false;
		lb_inscripcionAdicional     = false;
		lb_segregacion              = false;

		try
		{
			if(aoa_oa != null)
			{
				lotad_tad     = DaoCreator.getTipoActoDAO(ldm_manager);
				la_dao        = DaoCreator.getActoDAO(ldm_manager);

				if(!aoa_oa.isMatriculaLiquidacion())
				{
					if(StringUtils.getStringNotNull(aoa_oa.getActoConCuantia()).equalsIgnoreCase(EstadoCommon.N))
					{
						if(aoa_oa.getCuantiaActo() != null)
						{
							if(!NumericUtils.isValidBigDecimal(aoa_oa.getCuantiaActo()))
								throw new B2BException(ErrorKeys.ERROR_CUANTIA_NO_NUMERICO);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SIN_CUANTIA_ACTO);
					}
					else
					{
						if(aoa_oa.getCantidadActos() != null)
						{
							if(!NumericUtils.isValidInteger(aoa_oa.getCantidadActos()))
								throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS_NO_NUMERICO);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS);
					}
				}

				{
					lb_aperturaMatricula        = StringUtils.getStringNotNull(aoa_oa.getAperturaMatricula())
							                                     .equalsIgnoreCase(EstadoCommon.S);
					lb_inscripcionAdicional     = StringUtils.getStringNotNull(aoa_oa.getInscripcionAdicional())
							                                     .equalsIgnoreCase(EstadoCommon.S);
					lb_segregacion              = StringUtils.getStringNotNull(aoa_oa.getGeneraSegregacion())
							                                     .equalsIgnoreCase(EstadoCommon.S);

					if(lb_segregacion)
					{
						if(lb_aperturaMatricula && lb_inscripcionAdicional)
						{
							if(
							    NumericUtils.getInt(aoa_oa.getCantidadMatriculasInclr()) != NumericUtils.getInt(
								        aoa_oa.getCantidadMatriculas()
								    )
							)
								throw new B2BException(ErrorKeys.ERROR_CANTIDAD_MATRICULAS_INCLR_Y_MATRICULAS);
						}
					}
				}

				if(aoa_oa.getCantidadActos() != null)
				{
					if(!NumericUtils.isValidInteger(aoa_oa.getCantidadActos()))
						throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS_NO_NUMERICO);
				}

				{
					int li_i;

					li_i = lotad_tad.validateTipoAdqui(aoa_oa.getCodigo());

					if(li_i > 0)
					{
						if(
						    !StringUtils.isValidString(aoa_oa.getTipoAdquisicion())
							    || aoa_oa.getTipoAdquisicion().equals("-1")
						)
							throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_ADQUISICION);
					}
				}

				{
					int li_secuence;
					li_secuence = lotad_tad.findSecuence();

					if(li_secuence > 0)
					{
						int li_total;
						ls_idActoBd = StringUtils.getString(li_secuence);
						aoa_oa.setSecuence(ls_idActoBd);

						aoa_oa.setUserId(as_userId);

						li_total = lotad_tad.findActo(aoa_oa);

						if(li_total > 0)
						{
							lb_b = false;

							aoa_oa.setIpModificacion(as_remoteIp);
						}
						else
							aoa_oa.setIpCreacion(as_remoteIp);

						la_dao.insertOrUpdate(aoa_oa, lb_b);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_SECUENCIA_ACTO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("saveActoConstancia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_idActoBd;
	}

	/**
	 * Guarda en base de datos un acto.
	 *
	 * @param aa_acto Objeto contenedor de la información del acto a guardar
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp dirección IP del cliente que ejecuta la acción
	 * @return Acto con la información almacenada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr01.model.registro.Acto
	 */
	public synchronized com.bachue.snr.prosnr01.model.registro.Acto saveInfoAll(
	    com.bachue.snr.prosnr01.model.registro.Acto aa_acto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                                  ldm_manager;
		com.bachue.snr.prosnr01.model.registro.Acto la_actoReturn;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		la_actoReturn     = new com.bachue.snr.prosnr01.model.registro.Acto();

		try
		{
			if(aa_acto != null)
			{
				String      ls_idSolicitud;
				ActoDAO     la_dao;
				TipoActoDAO lotad_tad;

				ls_idSolicitud     = aa_acto.getIdSolicitud();
				la_dao             = DaoCreator.getActoDAO(ldm_manager);
				lotad_tad          = DaoCreator.getTipoActoDAO(ldm_manager);

				validarTipoAdquisicionVPM(ldm_manager, aa_acto, ls_idSolicitud);

				guardarFechaEjecutoriaActo(
				    ldm_manager, ls_idSolicitud, aa_acto.getFechaEjecutoria(), as_userId, as_remoteIp
				);

				if(!aa_acto.isNuevaEntrada())
				{
					if(!aa_acto.isMatriculaLiquidacion())
					{
						String ls_actoConCuantia;
						String ls_actoCuantia;

						ls_actoConCuantia = StringUtils.getStringNotNull(aa_acto.getActoConCuantia());

						if(
						    ls_actoConCuantia.equalsIgnoreCase(EstadoCommon.S)
							    || ls_actoConCuantia.equalsIgnoreCase(EstadoCommon.N)
						)
							ls_actoCuantia = ls_actoConCuantia;
						else
							ls_actoCuantia = ls_actoConCuantia.equalsIgnoreCase(IdentificadoresCommon.SIN_CUANTIA)
								? EstadoCommon.S : EstadoCommon.N;

						if(StringUtils.getStringNotNull(ls_actoCuantia).equalsIgnoreCase(EstadoCommon.N))
						{
							if(!aa_acto.isEsCambioTipoCuantia() || !aa_acto.isNoRequiereCuantiaActo())
								validarCuantiaActo(aa_acto.getCuantiaActo());
						}
						else
						{
							Integer li_cantidadActos;

							li_cantidadActos = aa_acto.getCantidadActos();

							if(li_cantidadActos != null)
							{
								if(!NumericUtils.isValidInteger(li_cantidadActos))
									throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS_NO_NUMERICO);
							}
							else if(
							    !aa_acto.isEsCambioTipoCuantia()
								    && !tipoVisActoPadre(aa_acto.getIdActoPrincipal(), ldm_manager)
							)
								throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS);
							else if(aa_acto.isNoRequiereCuantiaActo())
								throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS);
						}
					}

					if(
					    StringUtils.getStringNotNull(aa_acto.getGeneraSegregacion()).equalsIgnoreCase(EstadoCommon.S)
						    && StringUtils.getStringNotNull(aa_acto.getAperturaMatricula())
						                      .equalsIgnoreCase(EstadoCommon.S)
						    && StringUtils.getStringNotNull(aa_acto.getInscripcionAdicional())
						                      .equalsIgnoreCase(EstadoCommon.S)
						    && (NumericUtils.getInt(aa_acto.getCantidadMatriculasInclr()) != NumericUtils.getInt(
						        aa_acto.getCantidadMatriculas()
						    ))
					)
						throw new B2BException(ErrorKeys.ERROR_CANTIDAD_MATRICULAS_INCLR_Y_MATRICULAS);

					if(
					    StringUtils.getStringNotNull(aa_acto.getAvaluo()).equalsIgnoreCase(EstadoCommon.S)
						    && (!aa_acto.isEsCambioTipoCuantia() || !aa_acto.isNoRequiereCuantiaActo())
					)
						validarValorAvaluo(aa_acto.getValorAvaluo());

					{
						int li_i;

						li_i = lotad_tad.validateTipoAdqui(aa_acto.getCodigo());

						if(li_i > 0)
						{
							String ls_requiereTipoAdquisicion;
							ls_requiereTipoAdquisicion = aa_acto.getRequiereTipoAdquisicion();

							if(
							    !StringUtils.isValidString(aa_acto.getTipoAdquisicion())
								    && (StringUtils.isValidString(ls_requiereTipoAdquisicion)
								    && ls_requiereTipoAdquisicion.equalsIgnoreCase(EstadoCommon.S))
							)
								throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_ADQUISICION);
						}
					}
				}

				{
					int li_secuence;
					li_secuence = lotad_tad.findSecuence();

					if(li_secuence > 0)
					{
						int    li_total;
						String ls_idActoDb;

						ls_idActoDb = StringUtils.getString(li_secuence);

						aa_acto.setSecuence(ls_idActoDb);
						aa_acto.setUserId(as_userId);
						aa_acto.setIpModificacion(as_remoteIp);
						aa_acto.setIpCreacion(as_remoteIp);

						li_total = lotad_tad.findActo(aa_acto);

						{
							String ls_codigo;

							ls_codigo = aa_acto.getCodigo();

							if(
							    StringUtils.isValidString(ls_codigo)
								    && StringUtils.getStringNotNull(ls_codigo)
								                      .equalsIgnoreCase(TipoActoCommon.DECLARACION_PARTE_RESTANTE)
							)
							{
								Acto la_acto0126;

								la_acto0126     = new Acto(ls_idSolicitud, null, TipoActoCommon.COMPRAVENTA_PARCIAL);
								la_acto0126     = la_dao.findByIdSolicitudTipoActo(la_acto0126);

								if(la_acto0126 != null)
								{
									String ls_idActoPrincipal0126;

									ls_idActoPrincipal0126 = la_acto0126.getIdActo();

									if(StringUtils.isValidString(ls_idActoPrincipal0126))
										aa_acto.setIdActoPrincipal(ls_idActoPrincipal0126);
								}
							}
						}

						la_dao.insertOrUpdate(aa_acto, (li_total <= 0));

						la_actoReturn.setIdActoDb(ls_idActoDb);
						la_actoReturn.setIdActoDerivado(aa_acto.getIdActoDb());

						if(aa_acto.isNuevaEntrada())
						{
							AccCompletitudDocumental             lacd_completitudDocumental;
							AccCompletitudDocumentalDAO          lacd_DAO;
							Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

							lacd_completitudDocumental     = new AccCompletitudDocumental();
							lacd_DAO                       = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);

							lacd_completitudDocumental.setIdSolicitud(aa_acto.getIdSolicitudAsociada());
							lacd_completitudDocumental.setIdActo(aa_acto.getIdActoDb());
							lacd_completitudDocumental.setIdTipoActo(aa_acto.getCodigo());

							lcacd_completitudDocumental = lacd_DAO.findByActoSolicitud(lacd_completitudDocumental);

							if(CollectionUtils.isValidCollection(lcacd_completitudDocumental))
							{
								for(AccCompletitudDocumental lacd_iterador : lcacd_completitudDocumental)
								{
									if(lacd_iterador != null)
									{
										lacd_iterador.setIdUsuarioCreacion(as_userId);
										lacd_iterador.setIdSolicitud(ls_idSolicitud);
										lacd_iterador.setIdActo(ls_idActoDb);
										lacd_iterador.setIpCreacion(as_remoteIp);
										lacd_iterador.setDigitalizado(EstadoCommon.N);

										lacd_DAO.insert(lacd_iterador);
									}
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_SECUENCIA_ACTO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("saveInfoAll", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return la_actoReturn;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aca_ca correspondiente al valor del tipo de objeto Collection<com.bachue.snr.prosnr01.model.registro.Acto>
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @param ab_embargos correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<com.bachue.snr.prosnr01.model.registro.Acto> saveInfoAll(
	    Collection<com.bachue.snr.prosnr01.model.registro.Acto> aca_ca, String as_userId, String as_remoteIp,
	    boolean                                                 ab_embargos
	)
	    throws B2BException
	{
		DAOManager                                              ldm_manager;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lca_return      = new ArrayList<com.bachue.snr.prosnr01.model.registro.Acto>();

		try
		{
			if(CollectionUtils.isValidCollection(aca_ca))
			{
				Iterator<com.bachue.snr.prosnr01.model.registro.Acto> lia_ia;
				String                                                ls_idActoPrincipal;

				lia_ia                 = aca_ca.iterator();
				ls_idActoPrincipal     = null;

				while(lia_ia.hasNext())
				{
					com.bachue.snr.prosnr01.model.registro.Acto la_acto;

					la_acto = lia_ia.next();

					if(la_acto != null)
					{
						ActoDAO     la_DAO;
						TipoActoDAO ltad_DAO;

						la_DAO       = DaoCreator.getActoDAO(ldm_manager);
						ltad_DAO     = DaoCreator.getTipoActoDAO(ldm_manager);

						validarTipoAdquisicionVPM(ldm_manager, la_acto, la_acto.getIdSolicitud());

						if(!la_acto.isNuevaEntrada())
						{
							if(!la_acto.isMatriculaLiquidacion())
							{
								String ls_actoCuantia;

								ls_actoCuantia = StringUtils.getStringNotNull(la_acto.getActoConCuantia())
										                        .equalsIgnoreCase(IdentificadoresCommon.SIN_CUANTIA)
									? EstadoCommon.S : EstadoCommon.N;

								if(StringUtils.getStringNotNull(ls_actoCuantia).equalsIgnoreCase(EstadoCommon.N))
								{
									BigDecimal lbd_cuantiaActo;

									lbd_cuantiaActo = la_acto.getCuantiaActo();

									if(lbd_cuantiaActo != null)
									{
										if(lbd_cuantiaActo.compareTo(BigDecimal.ZERO) == 0)
											throw new B2BException(ErrorKeys.ERROR_CUANTIA_MAYOR_CERO);

										if(!NumericUtils.isValidBigDecimal(lbd_cuantiaActo))
											throw new B2BException(ErrorKeys.ERROR_CUANTIA_NO_NUMERICO);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_SIN_CUANTIA_ACTO);
								}
								else
								{
									Integer li_cantidadActos;

									li_cantidadActos = la_acto.getCantidadActos();

									if(li_cantidadActos != null)
									{
										if(!NumericUtils.isValidInteger(li_cantidadActos))
											throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS_NO_NUMERICO);
									}
									else if(!ab_embargos)
										throw new B2BException(ErrorKeys.ERROR_CANTIDAD_ACTOS);
								}
							}

							if(
							    StringUtils.getStringNotNull(la_acto.getGeneraSegregacion())
								               .equalsIgnoreCase(EstadoCommon.S)
							)
							{
								if(
								    StringUtils.getStringNotNull(la_acto.getAperturaMatricula())
									               .equalsIgnoreCase(EstadoCommon.S)
									    && StringUtils.getStringNotNull(la_acto.getInscripcionAdicional())
									                      .equalsIgnoreCase(EstadoCommon.S)
								)
								{
									if(
									    NumericUtils.getInt(la_acto.getCantidadMatriculasInclr()) != NumericUtils.getInt(
										        la_acto.getCantidadMatriculas()
										    )
									)
										throw new B2BException(ErrorKeys.ERROR_CANTIDAD_MATRICULAS_INCLR_Y_MATRICULAS);
								}
							}

							if(StringUtils.getStringNotNull(la_acto.getAvaluo()).equalsIgnoreCase(EstadoCommon.S))
							{
								BigDecimal lbd_valorAvaluo;

								lbd_valorAvaluo = la_acto.getValorAvaluo();

								if(lbd_valorAvaluo != null)
								{
									if(lbd_valorAvaluo.compareTo(BigDecimal.ZERO) == 0)
										throw new B2BException(ErrorKeys.ERROR_VALOR_AVALUO_MAYOR_CERO);

									if(!NumericUtils.isValidBigDecimal(lbd_valorAvaluo))
										throw new B2BException(ErrorKeys.ERROR_VALOR_AVALUO_NO_NUMERICO);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_VALOR_AVALUO);
							}

							{
								int li_i;

								li_i = ltad_DAO.validateTipoAdqui(la_acto.getCodigo());

								if(li_i > 0)
								{
									String ls_requiereTipoAdquisicion;
									ls_requiereTipoAdquisicion = la_acto.getRequiereTipoAdquisicion();

									if(
									    !StringUtils.isValidString(la_acto.getTipoAdquisicion())
										    && (StringUtils.isValidString(ls_requiereTipoAdquisicion)
										    && ls_requiereTipoAdquisicion.equalsIgnoreCase(EstadoCommon.S))
									)
										throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_ADQUISICION);
								}
							}
						}

						{
							int li_secuence;
							li_secuence = ltad_DAO.findSecuence();

							if(li_secuence > 0)
							{
								int    li_total;
								String ls_idActoDb;

								ls_idActoDb            = StringUtils.getString(li_secuence);
								ls_idActoPrincipal     = ((!la_acto.isVis())
										&& !StringUtils.isValidString(ls_idActoPrincipal)) ? ls_idActoDb
									                                                       : ls_idActoPrincipal;

								la_acto.setSecuence(ls_idActoDb);
								la_acto.setIdActoPrincipal(
								    la_acto.isVis() ? ls_idActoPrincipal : la_acto.getIdActoPrincipal()
								);
								la_acto.setUserId(as_userId);
								la_acto.setIpModificacion(as_remoteIp);
								la_acto.setIpCreacion(as_remoteIp);

								li_total = ltad_DAO.findActo(la_acto);

								la_DAO.insertOrUpdate(la_acto, !(li_total > 0));

								if(la_acto.isNuevaEntrada())
								{
									AccCompletitudDocumental             lacd_completitudDocumental;
									AccCompletitudDocumentalDAO          lacd_DAO;
									Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

									lacd_completitudDocumental     = new AccCompletitudDocumental();
									lacd_DAO                       = DaoCreator.getAccCompletitudDocumentalDAO(
										    ldm_manager
										);

									lacd_completitudDocumental.setIdSolicitud(la_acto.getIdSolicitudAsociada());
									lacd_completitudDocumental.setIdActo(la_acto.getIdActoDb());
									lacd_completitudDocumental.setIdTipoActo(la_acto.getCodigo());

									lcacd_completitudDocumental = lacd_DAO.findByActoSolicitud(
										    lacd_completitudDocumental
										);

									if(CollectionUtils.isValidCollection(lcacd_completitudDocumental))
									{
										for(AccCompletitudDocumental lacd_iterador : lcacd_completitudDocumental)
										{
											if(lacd_iterador != null)
											{
												lacd_iterador.setIdUsuarioCreacion(as_userId);
												lacd_iterador.setIdSolicitud(la_acto.getIdSolicitud());
												lacd_iterador.setIdActo(ls_idActoDb);
												lacd_iterador.setIpCreacion(as_remoteIp);
												lacd_iterador.setDigitalizado(EstadoCommon.N);

												lacd_DAO.insert(lacd_iterador);
											}
										}
									}
								}

								{
									com.bachue.snr.prosnr01.model.registro.Acto la_actoReturn;

									la_actoReturn = la_acto;

									la_actoReturn.setIdActoDb(ls_idActoDb);
									la_actoReturn.setIdActoDerivado(la_acto.getIdActoDb());

									lca_return.add(la_actoReturn);
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_SIN_SECUENCIA_ACTO);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("saveInfoAll", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_return;
	}

	/**
	 * Método encargado de terminar el proceso de solicitud, generando el NIR, turno y pdf final de la solicitud.
	 *
	 * @param ar_registro Objeto que contiene la información del registro que se desea insertar.
	 * @param as_solicitudCorreccion Objeto que contiene la información del registro en caso de ser una corrección.
	 * @param as_usuario Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param ab_notificarCorrespondencia Variable de tipo boolean que validar si el medio a notificar es correspondencia.
	 * @param as_isConstanciaRep Variable de tipo boolean que valida si la solicitud es una constancia de reproducción.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @return Objeto que contiene la información del proceso finalizado y el pdf final.
	 * @throws Exception Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro terminarProceso(
	    Registro ar_registro, Solicitud as_solicitudCorreccion, String as_usuario, boolean ab_notificarCorrespondencia,
	    boolean as_isConstanciaRep, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			actualizarConstanteBloqueo(
			    ldm_manager, as_ipRemote, as_usuario, ConstanteCommon.CONSTANTE_BLOQUEO_TERMINAR_PROCESO
			);

			if(ar_registro != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = (as_solicitudCorreccion != null) ? as_solicitudCorreccion : ar_registro.getSolicitud();

				if(ls_solicitud != null)
				{
					String            ls_nir;
					String            ls_idProceso;
					String            ls_idSubProceso;
					String            ls_numeroSolicitud;
					TurnoHistoriaDAO  lthd_DAO;
					boolean           lb_procesoCorrecciones;
					ProcedimientosDAO lpd_procedimientosDAO;
					String            ls_turnoPadre;

					ls_idProceso               = StringUtils.getStringNotNull(ls_solicitud.getIdProceso());
					ls_idSubProceso            = StringUtils.getStringNotNull(ls_solicitud.getIdSubproceso());
					ls_numeroSolicitud         = StringUtils.getStringNotNull(ls_solicitud.getIdSolicitud());
					lthd_DAO                   = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
					lb_procesoCorrecciones     = ar_registro.isProcesoCorrecciones();
					lpd_procedimientosDAO      = DaoCreator.getProcedimientosDAO(ldm_manager);
					ls_turnoPadre              = null;

					if(lb_procesoCorrecciones)
					{
						TurnoHistoria lth_turnoExistente;
						String        ls_idCirculo;

						lth_turnoExistente     = new TurnoHistoria();
						ls_idCirculo           = null;

						lth_turnoExistente.setIdTurnoHistoria(
						    NumericUtils.getLongWrapper(ar_registro.getIdTurnoHistoria())
						);

						lth_turnoExistente = lthd_DAO.findById(lth_turnoExistente);

						if(lth_turnoExistente != null)
						{
							MotivoTramite lmt_motivoTramite;
							Turno         lt_turno;

							lmt_motivoTramite     = new MotivoTramite();
							lt_turno              = DaoCreator.getTurnoDAO(ldm_manager)
									                              .findById(lth_turnoExistente.getIdTurno());

							lmt_motivoTramite.setIdEtapaOrigen(NumericUtils.getLong(lth_turnoExistente.getIdEtapa()));

							if(ar_registro.isEsGrabacionMatriculas())
								lmt_motivoTramite.setIdMotivo(MotivoTramiteCommon.ENVIAR_A_CORRECIONES);
							else
								lmt_motivoTramite.setIdMotivo(MotivoTramiteCommon.CORRECCIONES);

							lmt_motivoTramite = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivoTramite);

							if(lmt_motivoTramite == null)
								throw new B2BException(ErrorKeys.ERROR_MOTIVO_INVALIDO);

							if(lt_turno != null)
								ls_idCirculo = lt_turno.getIdCirculo();

							lth_turnoExistente.setMotivo(lmt_motivoTramite.getDescripcion());
							lth_turnoExistente.setIdMotivo(
							    NumericUtils.getLongWrapper(lmt_motivoTramite.getIdMotivo())
							);
							lth_turnoExistente.setIdUsuarioModificacion(as_usuario);
							lth_turnoExistente.setIpModificacion(as_ipRemote);
							lth_turnoExistente.setObservaciones(ar_registro.getObservacionesProceso());
							lth_turnoExistente.setEstadoActividad(EstadoCommon.TERMINADA);

							lthd_DAO.insertOrUpdate(lth_turnoExistente, false);

							{
								TurnoHistoria lth_turnoHistoriaParam;

								lth_turnoHistoriaParam = new TurnoHistoria();

								lth_turnoHistoriaParam.setIdTurnoHistoria(lth_turnoExistente.getIdTurnoHistoria());
								lth_turnoHistoriaParam.setIdUsuarioModificacion(as_usuario);
								lth_turnoHistoriaParam.setIdUsuarioAsignacion(as_usuario);
								lth_turnoHistoriaParam.setIpModificacion(as_ipRemote);

								lpd_procedimientosDAO.spCreateStage(lth_turnoHistoriaParam);
							}

							ls_turnoPadre = lth_turnoExistente.getIdTurno();
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

						Solicitud ls_solicitudPadre;
						String    ls_solicitudPadreString;

						ls_solicitudPadre           = ar_registro.getSolicitud();
						ls_solicitudPadreString     = null;

						if(ls_solicitudPadre != null)
						{
							ls_solicitudPadreString = ls_solicitudPadre.getIdSolicitud();

							ls_solicitud.setIdSolicitudPrevioCorreccion(ls_solicitudPadreString);

							ar_registro.setSolicitud(ls_solicitud);
						}

						if(StringUtils.isValidString(ls_solicitudPadreString))
						{
							Collection<SolicitudMatricula> lcsm_matriculas;
							SolicitudMatriculaDAO          lsm_DAO;

							lsm_DAO             = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
							lcsm_matriculas     = lsm_DAO.findByIdSolicitudCirculo(
								    ls_solicitudPadreString, ls_idCirculo
								);

							if(CollectionUtils.isValidCollection(lcsm_matriculas))
							{
								for(SolicitudMatricula lsm_data : lcsm_matriculas)
								{
									if(lsm_data != null)
									{
										SolicitudMatricula lsm_tmp;

										lsm_data.setIdSolicitud(ls_numeroSolicitud);

										lsm_tmp = lsm_DAO.findById(lsm_data);

										if(lsm_tmp == null)
										{
											lsm_data.setIdUsuarioCreacion(as_usuario);
											lsm_data.setIpCreacion(as_ipRemote);
											lsm_data.setCantidadCertificados(BigDecimal.ZERO);
											lsm_data.setExpedirCertificado(EstadoCommon.N);

											lsm_DAO.insertOrUpdate(lsm_data, true);
										}
									}
								}
							}
							else
							{
								DatosAntSistema ldas_datosAntSistema;

								ldas_datosAntSistema = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
										                             .findByIdSolicitudOne(ls_solicitudPadreString);

								if(ldas_datosAntSistema != null)
								{
									Long ll_idMatricula;

									ls_idCirculo       = ldas_datosAntSistema.getIdCirculoGrabacion();
									ll_idMatricula     = ldas_datosAntSistema.getIdMatriculaGrabacion();

									if(
									    StringUtils.isValidString(ls_idCirculo)
										    && NumericUtils.isValidLong(ll_idMatricula)
									)
									{
										SolicitudMatricula lsm_matricula;

										lsm_matricula = new SolicitudMatricula();

										lsm_matricula.setIdSolicitud(ls_numeroSolicitud);
										lsm_matricula.setIdCirculo(ls_idCirculo);
										lsm_matricula.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
										lsm_matricula.setExpedirCertificado(EstadoCommon.N);
										lsm_matricula.setEstado(EstadoCommon.A);
										lsm_matricula.setIdUsuarioCreacion(as_usuario);
										lsm_matricula.setIpCreacion(as_ipRemote);

										lsm_DAO.insertOrUpdate(lsm_matricula, true);
									}
								}
							}
						}
					}

					ls_nir = crearNir(as_usuario, as_ipRemote, ldm_manager);

					if(StringUtils.isValidString(ls_nir))
					{
						Solicitud    lso_solicitud;
						SolicitudDAO lsd_DAO;

						lsd_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);
						lso_solicitud     = lsd_DAO.findById(ls_solicitud);

						ar_registro.setNirProceso(ls_nir);
						lso_solicitud.setNir(ls_nir);
						lso_solicitud.setDescripcion(ls_solicitud.getDescripcion());

						if(ab_notificarCorrespondencia && (!lb_procesoCorrecciones))
						{
							OficinaOrigen loo_oficinaOrigen;
							loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
									                          .findBySolicitud(lso_solicitud.getIdSolicitud());

							if(loo_oficinaOrigen != null)
							{
								String ls_notificarCorrespondencia = loo_oficinaOrigen.getNotificarCorrespondencia();

								if(ls_notificarCorrespondencia.equalsIgnoreCase(EstadoCommon.S))
								{
									lso_solicitud.setIdAutorizacionNotificacion(
									    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									);
									lso_solicitud.setIdAutorizacionComunicacion(MedioNotificarCommon.ORIP);
								}
								else
								{
									lso_solicitud.setIdAutorizacionNotificacion(
									    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									);
									lso_solicitud.setIdAutorizacionComunicacion(
									    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									);
								}
							}
						}

						if(
						    StringUtils.isValidString(ls_idProceso)
							    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
						)
							lso_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.ORIP_DE_ORIGEN);

						lso_solicitud.setFechaSolicitud(new Date());

						lso_solicitud.setIdUsuarioModificacion(as_usuario);
						lsd_DAO.insertOrUpdate(lso_solicitud, false);

						{
							Liquidacion ll_liquidacion;

							ll_liquidacion = new Liquidacion();

							ll_liquidacion.setIdSolicitud(ls_solicitud.getIdSolicitud());
							ll_liquidacion.setNir(ls_nir);

							DaoCreator.getAccLiquidacionDAO(ldm_manager).actualizarNirLiquidacion(ll_liquidacion);
						}
					}
					else
						throw new B2BException(ErrorKeys.NO_GENERO_NIR);

					{
						String ls_indCondicion;

						ls_indCondicion = StringUtils.getStringNotNull(ar_registro.getIndCondicion());

						if(ls_indCondicion.equalsIgnoreCase(IdentificadoresCommon.LIQUIDAR) && !lb_procesoCorrecciones)
						{
							Registro lr_parametros;

							lr_parametros = new Registro();

							lr_parametros.setIdUsuarioCreacion(as_usuario);
							lr_parametros.setSolicitud(ls_solicitud);
							lr_parametros.setIdCondicion(ls_indCondicion);
							lr_parametros.setTipoRecibo(IdentificadoresCommon.RECIBO_LIQUIDACION);
							lr_parametros.setProcesoCertificados(ar_registro.isProcesoCertificados());
							lr_parametros.setIdTipoMayorValor(
							    ((ar_registro.isProcesoCertificados()
								    && StringUtils.isValidString(ar_registro.getIdTurnoNuevaEntrada()))
							    ? IdentificadoresCommon.N : null)
							);

							lr_parametros = generarReciboLiquidacion(
								    lr_parametros, true, as_usuario, null, null, ldm_manager
								);

							if(lr_parametros != null)
								ar_registro.setPdfLiquidacion(lr_parametros.getPdf());
						}
					}

					if(lb_procesoCorrecciones)
					{
						Turno    lth_turnoNuevo;
						TurnoDAO ltd_turnoDAO;

						lth_turnoNuevo = new Turno();

						lth_turnoNuevo.setIdSolicitud(ls_solicitud.getIdSolicitud());
						lth_turnoNuevo.setIdProceso(ls_idProceso);

						ltd_turnoDAO       = DaoCreator.getTurnoDAO(ldm_manager);
						lth_turnoNuevo     = ltd_turnoDAO.findByIdSolicitudProceso(lth_turnoNuevo);

						if(lth_turnoNuevo != null)
						{
							if(StringUtils.isValidString(ls_turnoPadre))
							{
								Turno lt_turnoPadreObj;

								lt_turnoPadreObj = ltd_turnoDAO.findById(ls_turnoPadre);

								if(lt_turnoPadreObj != null)
								{
									TurnoDerivado ltd_turnoDerivado;

									ltd_turnoDerivado = new TurnoDerivado();

									ltd_turnoDerivado.setIdTurnoPadre(ls_turnoPadre);
									ltd_turnoDerivado.setIdTurnoHijo(lth_turnoNuevo.getIdTurno());
									ltd_turnoDerivado.setAnioPadre(lt_turnoPadreObj.getAnio());
									ltd_turnoDerivado.setIdCirculoPadre(lt_turnoPadreObj.getIdCirculo());
									ltd_turnoDerivado.setIdProcesoPadre(lt_turnoPadreObj.getIdProceso());
									ltd_turnoDerivado.setAnioHijo(lth_turnoNuevo.getAnio());
									ltd_turnoDerivado.setIdCirculoHijo(lth_turnoNuevo.getIdCirculo());
									ltd_turnoDerivado.setIdProcesoHijo(lth_turnoNuevo.getIdProceso());
									ltd_turnoDerivado.setIdUsuarioCreacion(as_usuario);
									ltd_turnoDerivado.setIpCreacion(as_ipRemote);

									DaoCreator.getTurnoDerivadoDAO(ldm_manager).insert(ltd_turnoDerivado);
								}
							}

							{
								SolicitudMatricula             lsm_solMat;
								Collection<SolicitudMatricula> lcsm_matriculas;
								String                         ls_turno;

								lsm_solMat     = new SolicitudMatricula();
								ls_turno       = StringUtils.getStringNotNull(lth_turnoNuevo.getIdTurno());

								if(!StringUtils.isValidString(ls_turno))
									throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

								lsm_solMat.setIdSolicitud(ls_numeroSolicitud);

								lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
										                        .findBySolicitud(lsm_solMat);

								if(CollectionUtils.isValidCollection(lcsm_matriculas))
								{
									PredioRegistroDAO lpr_DAO;

									lpr_DAO = DaoCreator.getPredioRegistroDAO(ldm_manager);

									for(SolicitudMatricula lsm_data : lcsm_matriculas)
									{
										if(lsm_data != null)
										{
											PredioRegistro lpr_predioRegistro;

											lpr_predioRegistro = new PredioRegistro();

											lpr_predioRegistro.setIdCirculo(lsm_data.getIdCirculo());
											lpr_predioRegistro.setIdMatricula(lsm_data.getIdMatricula());
											lpr_predioRegistro.setValidMatricula(true);

											lpr_predioRegistro = lpr_DAO.findById(lpr_predioRegistro);

											if(lpr_predioRegistro != null)
											{
												lpr_predioRegistro.setTurnoBloqueo(ls_turno);
												lpr_predioRegistro.setIdUsuario(as_usuario);
												lpr_predioRegistro.setIdUsuarioModificacion(as_usuario);
												lpr_predioRegistro.setIpModificacion(as_ipRemote);

												lpr_DAO.insertOrUpdate(lpr_predioRegistro, false);
											}
										}
									}
								}
							}
						}
					}

					boolean lb_acto0463;

					lb_acto0463 = false;

					if(ls_solicitud != null)
					{
						Collection<Acto> lcca_actos;
						lcca_actos = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitud(
							    ls_solicitud.getIdSolicitud()
							);

						if(CollectionUtils.isValidCollection(lcca_actos))
						{
							String ls_naturalezaJuridica;
							ls_naturalezaJuridica = null;

							for(Acto lia_iterador : lcca_actos)
							{
								if(lia_iterador != null)
								{
									ls_naturalezaJuridica = lia_iterador.getIdTipoActo();

									if(
									    StringUtils.isValidString(ls_naturalezaJuridica)
										    && ls_naturalezaJuridica.equals("0463")
									)
										lb_acto0463 = true;
								}
							}
						}
					}

					if(lb_acto0463)
					{
						SolicitudMatricula             lsm_solMat;
						SolicitudMatriculaDAO          lsm_dao;
						Collection<SolicitudMatricula> lcsm_matriculas;
						TurnoDAO                       ltd_dao;
						AlertaTurnoTramiteDAO          atd_dao;

						lsm_solMat = new SolicitudMatricula();
						lsm_solMat.setIdSolicitud(ls_numeroSolicitud);

						lsm_dao             = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
						lcsm_matriculas     = lsm_dao.findByIdSolicitud(lsm_solMat, false);
						ltd_dao             = DaoCreator.getTurnoDAO(ldm_manager);
						atd_dao             = DaoCreator.getAlertaTurnoTramiteDAO(ldm_manager);

						if(CollectionUtils.isValidCollection(lcsm_matriculas))
						{
							for(SolicitudMatricula lism_iterador : lcsm_matriculas)
							{
								if(lism_iterador != null)
								{
									Collection<SolicitudMatricula> lcsm_matriculasMatricula;

									lcsm_matriculasMatricula = lsm_dao.findByCirculoMatricula(
										    lism_iterador.getIdCirculo(), lism_iterador.getIdMatricula()
										);

									if(CollectionUtils.isValidCollection(lcsm_matriculasMatricula))
										for(SolicitudMatricula lism_iterador2 : lcsm_matriculasMatricula)
										{
											if(lism_iterador2 != null)
											{
												Collection<Turno> lct_turnos;

												lct_turnos = ltd_dao.findByIdSolicitud(lism_iterador2.getIdSolicitud());

												if(CollectionUtils.isValidCollection(lct_turnos))
													for(Turno lit_iterador : lct_turnos)
													{
														if(lit_iterador != null)
														{
															AlertaTurnoTramite latt_att;

															latt_att = new AlertaTurnoTramite();

															latt_att.setIdTurnoAfectado(lit_iterador.getIdTurno());
															latt_att.setIdSolicitud(lit_iterador.getIdSolicitud());
															latt_att.setIdSolicitudVinculada(ls_numeroSolicitud);
															latt_att.setActivo(EstadoCommon.SI);
															latt_att.setIpCreacion(as_ipRemote);
															latt_att.setIdUsuarioCreacion(as_usuario);
															latt_att.setIdAlertaTramite(
															    AlertaTramiteCommon.TRAMITE_PROHIBICION_JUDICIAL
															);

															atd_dao.insertarAlerta(latt_att);
														}
													}
											}
										}
								}
							}
						}
					}

					{
						TurnoHistoria lth_turnoHistoriaParam;

						lth_turnoHistoriaParam = new TurnoHistoria();

						lth_turnoHistoriaParam.setIdSolicitud(ls_solicitud.getIdSolicitud());
						lth_turnoHistoriaParam.setIdUsuarioModificacion(as_usuario);
						lth_turnoHistoriaParam.setIpModificacion(as_ipRemote);

						lpd_procedimientosDAO.spCreateStage(lth_turnoHistoriaParam);
					}

					if(
					    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3)
						    && ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CORRECCION_EXTERNA)
					)
					{
						Solicitud ls_solicitudProc;

						ls_solicitudProc = new Solicitud();

						ls_solicitudProc.setIdSolicitud(ls_solicitud.getIdSolicitud());
						ls_solicitudProc.setIdUsuarioModificacion(as_usuario);
						ls_solicitudProc.setIpModificacion(as_ipRemote);

						lpd_procedimientosDAO.procLlaCrearEtapaTrg(
						    ls_solicitudProc, null, IdentificadoresCommon.CORRECCION_EXTERNA,
						    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_DIGITALIZACION)
						);
					}

					{
						clh_LOGGER.error("terminarProceso", "se inicia creación del documento Resumen de la solicitud");

						byte[] lba_pdf;

						lba_pdf = null;

						ar_registro.setRecepcionDocumentos(false);

						if(as_isConstanciaRep)
							lba_pdf = crearPdfRegistroConstanciaRep(ar_registro, as_usuario, ldm_manager);
						else
						{
							if(
							    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
								    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
							)
								lba_pdf = crearPdfRegistro(ar_registro, as_usuario, ldm_manager);
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3))
								lba_pdf = crearPdfCorrecciones(ar_registro, as_usuario, ldm_manager);
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38))
								lba_pdf = crearPdfTraslado(
									    ar_registro, as_usuario, ldm_manager,
									    ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
									);
						}

						clh_LOGGER.error(
						    "terminarProceso", "se termina creación del documento Resumen de la solicitud"
						);

						if(lba_pdf != null)
						{
							Imagenes         li_imagenes;
							DocumentosSalida lds_documentosSalida;
							long             ll_secuencia;

							li_imagenes              = new Imagenes();
							lds_documentosSalida     = new DocumentosSalida();

							li_imagenes.setImagenBlob(lba_pdf);

							li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							li_imagenes.setIdUsuarioCreacion(as_usuario);
							li_imagenes.setIpCreacion(as_ipRemote);

							ll_secuencia = DaoCreator.getImagenesDAO(ldm_manager).insertOrUpdate(li_imagenes, true);

							if(ll_secuencia <= 0)
								throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

							lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));

							String ls_tipoArchivo = ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3)
								? TipoArchivoCommon.SOLICITUD_CORRECCION
								: (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38)
								? TipoArchivoCommon.SOLICITUD_TRASLADO : TipoArchivoCommon.SOLICITUD);

							lds_documentosSalida.setTipo(ls_tipoArchivo);
							lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);
							lds_documentosSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
							lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD);
							lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
							lds_documentosSalida.setIdUsuarioCreacion(as_usuario);
							lds_documentosSalida.setIpCreacion(as_ipRemote);

							DaoCreator.getDocumentosSalidaDAO(ldm_manager).insertOrUpdate(lds_documentosSalida, true);

							ar_registro.setPdf(lba_pdf);
						}
					}

					if(ls_solicitud.isNuevaSolicitud() && !ls_solicitud.isCreacionSolicitud())
					{
						ls_solicitud.setIndVinculacion(EstadoCommon.S);
						ls_solicitud.setIdUsuarioCreacion(as_usuario);
						ls_solicitud.setIpCreacion(as_ipRemote);

						String ls_idSolicitud;

						ls_idSolicitud = lpd_procedimientosDAO.crearSolicitudDesdeOtra(ls_solicitud);

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Solicitud ls_s;

							ls_s = new Solicitud();

							ls_s.setIdSolicitud(ls_idSolicitud);

							ls_s = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_s);

							if(ls_s != null)
							{
								ls_s.setNuevaSolicitud(ls_solicitud.isNuevaSolicitud());
								ls_s.setCreacionSolicitud(true);
								ar_registro.setSolicitud(ls_s);
							}
						}
					}
					else
						ar_registro.setSolicitud(null);

					//URL Capture
					if(
					    ar_registro.isProcesoCertificados() || (as_solicitudCorreccion != null)
						    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38)
					)
					{
						String ls_urlCapture;

						ls_urlCapture = getDigitalizacionBusiness().construirUrlCapture(ls_nir, null, ldm_manager);

						if(StringUtils.isValidString(ls_urlCapture))
							ar_registro.setUrlCapture(ls_urlCapture);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("terminarProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Retorna el valor del objeto de Registro.
	 *
	 * @param ar_registro correspondiente al valor del tipo de objeto Registro
	 * @param ab_notificarCorrespondencia correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @param as_ipRemote correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 * @see Registro
	 */
	public synchronized Registro terminarProcesoGrabacionMatriculas(
	    Registro ar_registro, boolean ab_notificarCorrespondencia, String as_usuario, String as_ipRemote
	)
	    throws B2BException, IOException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			ar_registro = terminarProcesoGrabacionMatriculas(
				    ar_registro, ab_notificarCorrespondencia, as_usuario, as_ipRemote, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("terminarProcesoGrabacionMatriculas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_registro;
	}

	/**
	 * Metodo que se encarga de terminar el proceso de recepción de documento.
	 *
	 * @param aci_completitudDocumental Colección de imagenes que contienen los tipos documentales que deben ser salvadas en las tablas de negocio.
	 * @param aso_solicitud Objeto que contiene los datos de la solicitud creada.
	 * @param as_localIp Ip publica del equipo donde se ejecuta el proceso.
	 * @param as_remoteIp Ip del equipo donde se ejecuta el proceso.
	 * @param as_userId Usuario que realiza las acciones en la aplicación.
	 * @return El metodo retorna un objeto Registro que contiene valores que son mostrados en la pantalla cuando finaliza el proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro terminarProcesoRecepcion(
	    Collection<AccCompletitudDocumental> aci_completitudDocumental, Solicitud aso_solicitud, String as_localIp,
	    String                               as_remoteIp, String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_registro;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_registro     = new Registro();
		lr_registro.setSolicitud(new Solicitud());

		try
		{
			if(aso_solicitud != null)
			{
				String ls_idSolicitud;
				String ls_idTurnoAnt;
				String ls_idProceso;
				String ls_solicitudAnt;
				String ls_nirAnterior;
				String ls_nir;

				boolean lb_idProceso;
				boolean lb_proceso39;
				boolean lb_proceso40;
				boolean lb_proceso41;
				boolean lb_proceso42;
				boolean lb_proceso43;
				boolean lb_proceso44;
				boolean lb_proceso45;
				boolean lb_proceso46;
				boolean lb_proceso47;
				boolean lb_proceso48;

				ls_nirAnterior     = aso_solicitud.getNir();
				ls_idProceso       = aso_solicitud.getIdProceso();
				ls_idSolicitud     = aso_solicitud.getIdSolicitud();
				ls_idTurnoAnt      = aso_solicitud.getIdTurnoAnt();

				ls_solicitudAnt     = null;

				lb_idProceso     = StringUtils.isValidString(ls_idProceso);
				lb_proceso39     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39);
				lb_proceso40     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40);
				lb_proceso41     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41);
				lb_proceso42     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_42);
				lb_proceso43     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43);
				lb_proceso44     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_44);
				lb_proceso45     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_45);
				lb_proceso46     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_46);
				lb_proceso47     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47);
				lb_proceso48     = lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48);

				{
					ls_nir = crearNir(as_userId, as_remoteIp, ldm_manager);

					if(StringUtils.isValidString(ls_nir))
					{
						aso_solicitud.setNir(ls_nir);
						lr_registro.setNirProceso(ls_nir);
						lr_registro.setFechaCreacion(new Date());

						{
							Solicitud    lso_solicitud;
							SolicitudDAO lsd_DAO;

							lsd_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);
							lso_solicitud     = lsd_DAO.findById(aso_solicitud);

							if(lso_solicitud != null)
							{
								lso_solicitud.setNir(ls_nir);
								lso_solicitud.setFechaSolicitud(new Date());
								lso_solicitud.setIdUsuarioModificacion(as_userId);
								lso_solicitud.setIpModificacion(as_remoteIp);
								lsd_DAO.insertOrUpdate(lso_solicitud, false);
							}
						}

						{
							DocumentosSalidaDAO lds_DAO;
							ImagenesDAO         li_DAO;
							ProcedimientosDAO   lp_DAO;
							Map<String, String> lmss_tiposDocumentalesRestitucion;
							Turno               lt_turno;
							Solicitud           ls_solicitudAnterior;
							String              ls_idSolicitudAnt;

							lt_turno                              = null;
							ls_solicitudAnterior                  = null;
							ls_idSolicitudAnt                     = null;
							lmss_tiposDocumentalesRestitucion     = aso_solicitud.getTiposDocumentalesRestitucion();
							lds_DAO                               = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
							li_DAO                                = DaoCreator.getImagenesDAO(ldm_manager);
							lp_DAO                                = DaoCreator.getProcedimientosDAO(ldm_manager);

							if(lb_proceso46)
								ls_solicitudAnterior = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(
									    ls_nirAnterior
									);

							lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurnoAnt);

							if(lt_turno != null)
								ls_idSolicitudAnt = lt_turno.getIdSolicitud();

							if(((lt_turno != null) || (ls_solicitudAnterior != null)) && !lb_proceso44)
							{
								if(lb_proceso46)
									ls_solicitudAnt = ls_solicitudAnterior.getIdSolicitud();
								else
									ls_solicitudAnt = lt_turno.getIdSolicitud();

								if(StringUtils.isValidString(ls_solicitudAnt))
								{
									SolicitudAsociada lsa_solicitudNueva;

									lsa_solicitudNueva = new SolicitudAsociada();

									lsa_solicitudNueva.setIdSolicitud(ls_solicitudAnt);
									lsa_solicitudNueva.setIdSolicitud1(ls_idSolicitud);
									lsa_solicitudNueva.setIdUsuarioCreacion(as_userId);
									lsa_solicitudNueva.setIpCreacion(as_remoteIp);

									DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
										          .insertOrUpdate(lsa_solicitudNueva, true);
								}
							}

							Solicitud lso_solicitud;
							lso_solicitud = new Solicitud();

							lso_solicitud.setIdSolicitud(ls_idSolicitud);

							lso_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lso_solicitud);

							if(lso_solicitud != null)
								lr_registro.setSolicitud(lso_solicitud);

							if(lb_proceso40 || lb_proceso44)
							{
								Collection<AccCompletitudDocumental> lcacd_completitudDoc;
								lcacd_completitudDoc = aso_solicitud.getCompletitudDocumental();

								if(CollectionUtils.isValidCollection(lcacd_completitudDoc))
								{
									for(AccCompletitudDocumental lacd_completitudTMP : lcacd_completitudDoc)
									{
										if(lacd_completitudTMP != null)
										{
											lacd_completitudTMP.setIdSolicitud(aso_solicitud.getIdSolicitud());
											lacd_completitudTMP.setIdTurno(ls_idTurnoAnt);
											lacd_completitudTMP.setIdSolicitudPrincipal(ls_idSolicitudAnt);
											lacd_completitudTMP.setIdProceso(aso_solicitud.getIdProceso());
											lacd_completitudTMP.setIdSubproceso(aso_solicitud.getIdSubproceso());

											String ls_entregado;
											ls_entregado = lacd_completitudTMP.getEntregado();

											if(StringUtils.isValidString(ls_entregado))
											{
												String ls_solicitudVinculada;
												ls_solicitudVinculada = lacd_completitudTMP.getIdSolicitudVinculada();

												if(!StringUtils.isValidString(ls_solicitudVinculada))
												{
													if(ls_entregado.equalsIgnoreCase(EstadoCommon.S))
														lacd_completitudTMP.setIdSolicitudVinculada(ls_idSolicitud);
												}
											}

											lacd_completitudTMP.setIdUsuarioCreacion(as_userId);
											lacd_completitudTMP.setIpCreacion(as_remoteIp);

											String ls_idCompletitud;
											ls_idCompletitud = lacd_completitudTMP.getIdCompletitud();

											if(StringUtils.isValidString(ls_idCompletitud))
											{
												AccCompletitudDocumental lacd_tmp;
												lacd_tmp = new AccCompletitudDocumental();

												lacd_tmp.setIdCompletitud(ls_idCompletitud);

												lacd_tmp = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
														                 .findById(lacd_tmp);

												if(lacd_tmp != null)
												{
													lacd_completitudTMP.setIdUsuarioModificacion(as_userId);
													lacd_completitudTMP.setIpModificacion(as_remoteIp);
													DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
														          .update(lacd_completitudTMP);
												}
												else
												{
													lacd_completitudTMP.setDigitalizado(EstadoCommon.N);

													DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
														          .insert(lacd_completitudTMP);
												}
											}
											else
											{
												lacd_completitudTMP.setDigitalizado(EstadoCommon.N);

												DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
													          .insert(lacd_completitudTMP);
											}
										}
									}
								}
							}

							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = new TurnoHistoria();

							lth_turnoHistoria.setIdSolicitud(ls_idSolicitud);
							lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							lth_turnoHistoria = lp_DAO.spCreateStage(lth_turnoHistoria);

							{
								boolean lb_validMap;

								lb_validMap = CollectionUtils.isValidCollection(lmss_tiposDocumentalesRestitucion);

								if(lb_proceso39 || lb_proceso45)
								{
									if(lb_proceso45)
										lmss_tiposDocumentalesRestitucion = new HashMap<String, String>();

									String ls_idCalidadSolicitante;

									ls_idCalidadSolicitante = aso_solicitud.getIdCalidadSolicitante();

									if(
									    (StringUtils.isValidString(ls_idCalidadSolicitante)
										    && ls_idCalidadSolicitante.equalsIgnoreCase(
										        CalidadSolicitanteCommon.APODERADO
										    )) || lb_proceso45
									)
									{
										Constantes lc_constante;

										if(
										    !ls_idCalidadSolicitante.equalsIgnoreCase(
											        CalidadSolicitanteCommon.APODERADO
											    )
										)
										{
											if(lb_proceso45)
												lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
														                     .findById(
														    ConstanteCommon.TIPOS_DOC_ART19_INTERESADO
														);
											else
												lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
														                     .findById(
														    ConstanteCommon.TIPO_DOC_REQ_DESISTIMIENTO
														);
										}

										else
										{
											if(lb_proceso45)
												lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
														                     .findById(
														    ConstanteCommon.TIPOS_DOC_ART19_APODERADO
														);
											else
												lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
														                     .findById(
														    ConstanteCommon.TIPO_DOC_APOD_REQ_DESISTIMIENTO
														);
										}

										if(lc_constante != null)
										{
											String ls_caracter;

											ls_caracter = lc_constante.getCaracter();

											if(StringUtils.isValidString(ls_caracter))
											{
												String[] lsa_data;

												lsa_data = ls_caracter.split(",");

												if(CollectionUtils.isValidCollection(lsa_data))
												{
													for(int li_count = 0; li_count < lsa_data.length; li_count++)
														lmss_tiposDocumentalesRestitucion.put(
														    lsa_data[li_count], EstadoCommon.N
														);
												}
											}
										}
									}
								}

								if(CollectionUtils.isValidCollection(aci_completitudDocumental))
								{
									lds_DAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
									li_DAO      = DaoCreator.getImagenesDAO(ldm_manager);

									{
										DocumentosSalida             lds_documentosSalida;
										Collection<DocumentosSalida> lcds_documentosPorSolicitud;

										lds_documentosSalida = new DocumentosSalida();

										lds_documentosSalida.setIdSolicitud(ls_idSolicitud);
										lds_documentosSalida.setEstado(EstadoCommon.ENTREGA);

										lcds_documentosPorSolicitud = lds_DAO.findByIdSolicitudEstado(
											    lds_documentosSalida
											);

										if(CollectionUtils.isValidCollection(lcds_documentosPorSolicitud))
										{
											for(DocumentosSalida lds_iterador : lcds_documentosPorSolicitud)
											{
												if(lds_iterador != null)
												{
													Imagenes li_imagenes;

													li_imagenes = new Imagenes();

													li_imagenes.setIdImagen(
													    NumericUtils.getInt(lds_iterador.getIdImagen())
													);

													lds_DAO.deleteByIdImagen(lds_iterador);
													li_DAO.deleteById(li_imagenes);
												}
											}
										}
									}

									guardarTiposDocumentales(
									    ldm_manager, aci_completitudDocumental, aso_solicitud, ls_idTurnoAnt,
									    ls_idSolicitudAnt, null, null, as_localIp, as_remoteIp, as_userId
									);
								}

								if(lb_validMap || lb_proceso45)
								{
									TipoDocumentalDAO ltdd_DAO;
									StringBuilder     lsb_sb;

									ltdd_DAO     = DaoCreator.getTipoDocumentalDAO(ldm_manager);
									lsb_sb       = new StringBuilder();

									for(Map.Entry<String, String> lmss_entry : lmss_tiposDocumentalesRestitucion
										    .entrySet())
									{
										if(!BooleanUtils.getBooleanValue(lmss_entry.getValue()))
										{
											String ls_idTipoDocumental;

											ls_idTipoDocumental = lmss_entry.getKey();

											if(StringUtils.isValidString(ls_idTipoDocumental))
											{
												com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental ltd_td;
												boolean                                              lb_faltaPorAgregar;

												ltd_td                 = new com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental();
												lb_faltaPorAgregar     = false;

												ltd_td.setIdTipoDocumental(ls_idTipoDocumental);

												ltd_td = ltdd_DAO.findById(ltd_td);

												if(CollectionUtils.isValidCollection(aci_completitudDocumental))
												{
													for(AccCompletitudDocumental lacd_iterador : aci_completitudDocumental)
													{
														if(lacd_iterador != null)
														{
															String ls_tipoDocumental;

															ls_tipoDocumental = lacd_iterador.getIdTipoDocumental();

															if(StringUtils.isValidString(ls_tipoDocumental))
															{
																if(
																    ls_tipoDocumental.equalsIgnoreCase(
																	        ls_idTipoDocumental
																	    )
																)
																	lb_faltaPorAgregar = true;
															}
														}
													}
												}

												if((ltd_td != null) && !lb_faltaPorAgregar)
												{
													if(lsb_sb.length() > NumericUtils.DEFAULT_INT_VALUE)
														lsb_sb.append(IdentificadoresCommon.SIMBOLO_COMA);

													lsb_sb.append(ltd_td.getNombre());
												}
											}
										}
									}

									String ls_s;

									ls_s = lsb_sb.toString();

									if(StringUtils.isValidString(ls_s))
									{
										Object[] loa_messageArgs = new String[1];

										loa_messageArgs[0] = ls_s;

										throw new B2BException(
										    ErrorKeys.ERROR_TIPOS_DOCUMENTALES_OBLIGATORIOS, loa_messageArgs
										);
									}
								}
							}

							{
								byte[] lba_pdf;

								lr_registro.setRecepcionDocumentos(true);
								lr_registro.setEsProrrogaDocumentacion(lb_proceso41);
								lr_registro.setEsSuspensionEntregaDocumentacion(lb_proceso40);
								lr_registro.setEsProcesoDesistimiento(lb_proceso39);
								lr_registro.setEsProrrogaMayorValor(lb_proceso42);
								lr_registro.setEsRestitucion(lb_proceso43);
								lr_registro.setEsCreacionMatriculaOficio(lb_proceso44);
								lr_registro.setEsArticuloSuspension(lb_proceso45);
								lr_registro.setEsImpuestoRegistroGob(lb_proceso46);
								lr_registro.setEsRecepcionRecursos(lb_proceso47);
								lr_registro.setEsSegundaInstancia(lb_proceso48);
								lr_registro.setIdTurno(ls_idTurnoAnt);

								{
									Solicitud ls_solicitud;
									Solicitud ls_solicitudRegistro;

									ls_solicitudRegistro = lr_registro.getSolicitud();

									if(lb_proceso46 && (ls_solicitudRegistro != null))
										ls_solicitudRegistro.setInfoTurnosImpuestosGobernacion(
										    aso_solicitud.getInfoTurnosImpuestosGobernacion()
										);

									ls_solicitud = ls_solicitudRegistro;

									if(ls_solicitud != null)
										ls_solicitud.setIdProceso(ls_idProceso);
								}

								lba_pdf = crearPdfRegistro(lr_registro, as_userId, ldm_manager);

								if(lba_pdf != null)
								{
									Imagenes         li_imagenes;
									DocumentosSalida lds_documentosSalida;
									long             ll_secuencia;

									li_imagenes              = new Imagenes();
									lds_documentosSalida     = new DocumentosSalida();

									li_imagenes.setImagenBlob(lba_pdf);

									li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									li_imagenes.setIdUsuarioCreacion(as_userId);
									li_imagenes.setIpCreacion(as_remoteIp);

									ll_secuencia = DaoCreator.getImagenesDAO(ldm_manager)
											                     .insertOrUpdate(li_imagenes, true);

									if(ll_secuencia <= 0)
										throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

									lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));

									lds_documentosSalida.setTipo(TipoArchivoCommon.SOLICITUD);

									if(lb_proceso42)
										lds_documentosSalida.setEstado(EstadoCommon.E);
									else
										lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);

									lds_documentosSalida.setIdSolicitud(aso_solicitud.getIdSolicitud());

									if(!lb_proceso42)
										lds_documentosSalida.setIdTurno(
										    (lt_turno != null) ? lt_turno.getIdTurno() : null
										);

									lds_documentosSalida.setIdTipoDocumental(
									    TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD
									);
									lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
									lds_documentosSalida.setIdUsuarioCreacion(as_userId);
									lds_documentosSalida.setIpCreacion(as_remoteIp);

									DaoCreator.getDocumentosSalidaDAO(ldm_manager)
										          .insertOrUpdate(lds_documentosSalida, true);

									lr_registro.setPdf(lba_pdf);
									lr_registro.setNirProceso(ls_nir);
								}

								if(lb_proceso42)
								{
									byte[]    lba_pdfProrrogaMayorValor;
									Solicitud ls_solicitud;

									ls_solicitud = lr_registro.getSolicitud();

									if(ls_solicitud != null)
									{
										DocumentosSalida lds_documentosSalida;
										lds_documentosSalida     = new DocumentosSalida();

										lba_pdfProrrogaMayorValor = generarPlantillaNiegaProrrogaMayorValor(
											    lth_turnoHistoria, lds_documentosSalida, ldm_manager,
											    ls_solicitud.getIdSolicitud(), ls_solicitud.getIdTurnoAnt(), as_userId,
											    as_remoteIp
											);

										if(lba_pdfProrrogaMayorValor != null)
										{
											Imagenes li_imagenes;
											long     ll_secuencia;

											li_imagenes = new Imagenes();

											li_imagenes.setImagenBlob(lba_pdfProrrogaMayorValor);

											li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
											li_imagenes.setIdUsuarioCreacion(as_userId);
											li_imagenes.setIpCreacion(as_remoteIp);

											ll_secuencia = DaoCreator.getImagenesDAO(ldm_manager)
													                     .insertOrUpdate(li_imagenes, true);

											if(ll_secuencia <= 0)
												throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

											lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));

											lds_documentosSalida.setTipo(TipoArchivoCommon.COMUNICADO);
											lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);
											lds_documentosSalida.setIdSolicitud(aso_solicitud.getIdSolicitud());
											lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.COMUNICACION);
											lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
											lds_documentosSalida.setIdTurnoHistoria(
											    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
											);
											lds_documentosSalida.setIdUsuarioCreacion(as_userId);
											lds_documentosSalida.setIpCreacion(as_remoteIp);

											DaoCreator.getDocumentosSalidaDAO(ldm_manager)
												          .insertOrUpdate(lds_documentosSalida, true);

											lr_registro.setPdfProrrogaMayorValor(lba_pdfProrrogaMayorValor);
										}
									}
								}
							}
						}
					}
				}

				if(StringUtils.isValidString(ls_idProceso))
				{
					AlertaTurnoTramite latt_att;
					boolean            lb_insertar;

					latt_att        = new AlertaTurnoTramite();
					lb_insertar     = false;

					latt_att.setIdTurnoAfectado(ls_idTurnoAnt);
					latt_att.setIdSolicitud(ls_solicitudAnt);
					latt_att.setIdSolicitudVinculada(ls_idSolicitud);
					latt_att.setActivo(EstadoCommon.SI);
					latt_att.setIpCreacion(as_remoteIp);
					latt_att.setIdUsuarioCreacion(as_userId);

					if(lb_proceso45)
					{
						latt_att.setIdAlertaTramite(AlertaTramiteCommon.EN_TRAMITE_DE_SUSPENSION);
						lb_insertar = true;
					}
					else if(lb_proceso39)
					{
						latt_att.setIdAlertaTramite(AlertaTramiteCommon.EN_TRAMITE_DE_DESISTIMIENTO);
						lb_insertar = true;
					}
					else if(lb_proceso41 || lb_proceso42)
					{
						latt_att.setIdAlertaTramite(AlertaTramiteCommon.EN_TRAMITE_DE_PRORROGA);
						lb_insertar = true;
					}
					else if(lb_proceso43)
					{
						latt_att.setIdAlertaTramite(AlertaTramiteCommon.EN_TRAMITE_DE_RESTITUCION);
						lb_insertar = true;
					}
					else if(lb_proceso40)
					{
						latt_att.setIdAlertaTramite(AlertaTramiteCommon.TRAMITE_DE_ENTREGA_DE_DOCUMENTOS);
						lb_insertar = true;
					}
					else if(lb_proceso47)
					{
						latt_att.setIdAlertaTramite(AlertaTramiteCommon.EN_TRAMITE_DE_INTERPOSICION_DE_RECURSO);
						lb_insertar = true;
					}
					else if(lb_proceso48)
					{
						latt_att.setIdAlertaTramite(
						    AlertaTramiteCommon.TRAMITE_DE_ENTREGA_DE_DOCUMENTOS_SEGUNDA_INSTANCIA
						);
						lb_insertar = true;
					}

					Collection<Acto> ls_solicitud0463;
					ls_solicitud0463 = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitud(ls_solicitudAnt);

					if(CollectionUtils.isValidCollection(ls_solicitud0463))
					{
						for(Acto ia_iterador : ls_solicitud0463)
						{
							if(ia_iterador != null)
							{
								String ls_naturalezaActo;
								ls_naturalezaActo = ia_iterador.getIdTipoActo();

								if(StringUtils.isValidString(ls_naturalezaActo) && ls_naturalezaActo.equals("0463"))
								{
									latt_att.setIdAlertaTramite(AlertaTramiteCommon.TRAMITE_PROHIBICION_JUDICIAL);
									lb_insertar = true;
								}
							}
						}
					}

					if(lb_insertar)
					{
						Collection<String> lcs_turnosAsociados;

						lcs_turnosAsociados = obtenerColeccionTurnosBitacoraBloqueo(ls_idTurnoAnt, ldm_manager);

						if(CollectionUtils.isValidCollection(lcs_turnosAsociados))
						{
							for(String ls_turnoAsociado : lcs_turnosAsociados)
							{
								if(StringUtils.isValidString(ls_turnoAsociado))
								{
									latt_att.setIdTurnoAsociado(ls_turnoAsociado);

									DaoCreator.getAlertaTurnoTramiteDAO(ldm_manager).insertarAlerta(latt_att);
								}
							}
						}
						else
						{
							latt_att.setIdTurnoAsociado(null);

							DaoCreator.getAlertaTurnoTramiteDAO(ldm_manager).insertarAlerta(latt_att);
						}
					}
				}

				//URL Capture
				if(lb_proceso46 || lb_proceso47 || lb_proceso48 || lb_proceso40 || lb_proceso41 || lb_proceso44)
				{
					String ls_urlCapture;

					ls_urlCapture = getDigitalizacionBusiness().construirUrlCapture(ls_nir, null, ldm_manager);

					if(StringUtils.isValidString(ls_urlCapture))
						lr_registro.setUrlCapture(ls_urlCapture);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("terminarProcesoRecepcion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_registro;
	}

	/**
	 * Método para saber si un acto de la tabla SDB_ACC_ACTO tiene segregación en la tabla SDB_PGN_TIPO_ACTO.
	 *
	 * @param la_a correspondiente al valor del tipo de objeto Acto
	 * @return booleano para saber si tiene o no tiene segregación
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean tieneSegregacion(Acto la_a)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_tieneSegregacion;

		lb_tieneSegregacion     = false;
		ldm_manager             = DaoManagerFactory.getDAOManager();

		try
		{
			la_a = DaoCreator.getActoDAO(ldm_manager).findById(la_a);

			if(la_a != null)
			{
				String idTipoActo;

				idTipoActo = la_a.getIdTipoActo();

				if(StringUtils.isValidString(idTipoActo))
				{
					TipoActo lta_ta;

					lta_ta = new TipoActo();

					lta_ta.setIdTipoActo(idTipoActo);

					lta_ta = DaoCreator.getTipoActoDAO(ldm_manager).findActoDetailsByTipoActo(lta_ta);

					if(lta_ta != null)
					{
						if(lta_ta.getGeneraSegregacion().equalsIgnoreCase(EstadoCommon.S))
							lb_tieneSegregacion = true;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("tieneSegregacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_tieneSegregacion;
	}

	/**
	 * Método encargado de actualizar los turnos certificados de una solicitud matricula.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id del turno.
	 * @param as_idSolicitud Variable de tipo String que contiene el id de la solicitud.
	 * @param ab_accion Variable de tipo boolean que valida si se debe actualizar el turno certificado.
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return Variable de tipo boolean que valida si la matricula tiene el turno certificado asociado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean turnoCertificado(
	    String as_idTurno, String as_idSolicitud, boolean ab_accion, DAOManager adm_manager
	)
	    throws B2BException
	{
		boolean               lb_return;
		SolicitudMatriculaDAO lsm_DAO;
		TurnoDAO              lt_DAO;

		lb_return     = false;
		lsm_DAO       = DaoCreator.getSolicitudMatriculaDAO(adm_manager);
		lt_DAO        = DaoCreator.getTurnoDAO(adm_manager);

		try
		{
			if(StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idSolicitud))
			{
				Turno lt_turno;

				lt_turno = new Turno();

				lt_turno.setIdTurno(as_idTurno);

				lt_turno = lt_DAO.findById(lt_turno);

				if(lt_turno != null)
				{
					Map<String, SolicitudMatricula> llhm_matriculas;
					SolicitudMatricula              lsm_solicitudMatricula;

					lsm_solicitudMatricula = new SolicitudMatricula();

					lsm_solicitudMatricula.setIdTurnoCertificado(as_idTurno);
					lsm_solicitudMatricula.setIdSolicitud(as_idSolicitud);

					llhm_matriculas = lsm_DAO.findAllByTurnoCertificado(lsm_solicitudMatricula);

					if(CollectionUtils.isValidCollection(llhm_matriculas))
					{
						Collection<SolicitudMatricula> lcsm_matriculas;

						lcsm_matriculas = lsm_DAO.findByIdSolicitud(lsm_solicitudMatricula);

						if(CollectionUtils.isValidCollection(lcsm_matriculas))
						{
							for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
							{
								if(lsm_iterador != null)
								{
									String ls_matricula;

									ls_matricula = lsm_iterador.getIdCirculo() + "-" + lsm_iterador.getIdMatricula();

									if(llhm_matriculas.containsKey(ls_matricula))
									{
										lb_return = true;

										if(ab_accion)
										{
											lsm_iterador.setExpedirCertificado(EstadoCommon.S);
											lsm_iterador.setCantidadCertificados(new BigDecimal("1"));

											lsm_DAO.insertOrUpdate(lsm_iterador, false);
										}
									}
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.MATRICULA_TURNO_FIND);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("turnoCertificado", lb2be_e);

			throw lb2be_e;
		}

		return lb_return;
	}

	/**
	 * Método encargado de actualizar la información de un acto derivado.
	 *
	 * @param aa_acto Objeto que contiene la información del acto que se desea actualizar.
	 * @param as_userId Variable de tipo String que contiene le id del usuario que está realizando el proceso.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void updateActoHijo(
	    com.bachue.snr.prosnr01.model.registro.Acto aa_acto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_acto != null)
			{
				ActoDAO la_DAO;

				la_DAO = DaoCreator.getActoDAO(ldm_manager);

				aa_acto.setUserId(as_userId);
				aa_acto.setIpModificacion(as_remoteIp);

				la_DAO.insertOrUpdate(aa_acto, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("updateActoHijo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de actualizar el id circulo de los actos hijos de un acto principal.
	 *
	 * @param aa_actoUdate Objeto que contiene la información del acto.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void updateCirculoActosHijos(Acto aa_actoUdate, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_actoUdate != null)
			{
				aa_actoUdate.setIdUsuarioModificacion(as_userId);
				aa_actoUdate.setIpModificacion(as_remoteIp);

				DaoCreator.getActoDAO(ldm_manager).updateCirculoActosHijos(aa_actoUdate);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("updateCirculoActosHijos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de actualizar la información de la solicitud en tramite.
	 *
	 * @param ap_parametros Objeto que contiene la información de la solicitud que se desea actualizar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void updateSolicitud(Solicitud ap_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ap_parametros != null)
			{
				SolicitudDAO dao = null;
				dao = DaoCreator.getSolicitudDAO(ldm_manager);

				ap_parametros.setIdUsuarioModificacion(as_userId);

				dao.insertOrUpdate(ap_parametros, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("updateSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para actualizar un  Testamento.
	 *
	 * @param at_testamento Testamento a guardar en la tabla SDB_ACC_TESTAMENTO
	 * @param as_userId con el id de la modificacion o la insercion
	 * @param as_ipRemota con el id de la ip remota de la actualizaciónj o inserción
	 * @return el valor de testamento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Testamento updateTestamento(Testamento at_testamento, String as_userId, String as_ipRemota)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Testamento lt_testamentoDev;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		lt_testamentoDev     = null;

		try
		{
			if(at_testamento != null)
			{
				TestamentoDAO ldd_DAO;

				ldd_DAO = DaoCreator.getTestamentoDAO(ldm_manager);

				ldd_DAO.update(at_testamento);
				lt_testamentoDev = at_testamento;
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("updateTestamento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lt_testamentoDev;
	}

	/**
	 * Método encargado de validar el id tipo oficina para un id documento para el proceso de cabeza de familia.
	 *
	 * @param as_s Objeto de tipo solicitud que contiene los datos necesarios para realizar la validación.
	 * @param as_userId usuario encargado de ejecutar el proceso
	 * @return variable de tipo boolean indicando si el id documento tiene un tipo oficina en notarias, alcaldías o inspecciones.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validacionActo0315(Solicitud as_s, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_b;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_b            = false;

		try
		{
			if(as_s != null)
			{
				as_s = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_s);

				if(as_s != null)
					lb_b = DaoCreator.getDocumentoDAO(ldm_manager).validarTipoOficina(as_s.getIdDocumento());
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validacionActo0315", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_b;
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
	 * Validar acto grupo cancelacion.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarActoGrupoCancelacion(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_return       = false;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				Acto             la_acto;
				Collection<Acto> lca_ca;

				la_acto = new Acto();

				la_acto.setIdSolicitud(as_idSolicitud);

				lca_ca = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitud(la_acto);

				if(CollectionUtils.isValidCollection(lca_ca))
				{
					Constantes lc_constante;

					lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
							                     .findById(ConstanteCommon.CODIGO_DE_ACTO_CON_MAS_DE_UN_INTERVINIENTE);

					if(lc_constante != null)
					{
						NaturalezaJuridicaDAO      lnj_DAO;
						GrupoNaturalezaJuridicaDAO lgnj_DAO;

						lnj_DAO      = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
						lgnj_DAO     = DaoCreator.getGrupoNaturalezaJuridicaDAO(ldm_manager);

						for(Acto la_actoTmp : lca_ca)
						{
							if(la_actoTmp != null)
							{
								String ls_idTipoActo;

								ls_idTipoActo = la_actoTmp.getIdTipoActo();

								if(StringUtils.isValidString(ls_idTipoActo))
								{
									NaturalezaJuridica lnj_natJuridica;

									lnj_natJuridica = lnj_DAO.findByIdMaxVersion(new NaturalezaJuridica(ls_idTipoActo));

									if(lnj_natJuridica != null)
									{
										GrupoNaturalezaJuridica lgnj_grupoNatJuridica;

										lgnj_grupoNatJuridica = lgnj_DAO.findById(lnj_natJuridica.getIdGrupoNatJur());

										if(lgnj_grupoNatJuridica != null)
										{
											String ls_idGrupoNatJuridica;

											ls_idGrupoNatJuridica = lgnj_grupoNatJuridica.getIdGrupoNatJuridica();

											if(
											    StringUtils.isValidString(ls_idGrupoNatJuridica)
												    && ls_idGrupoNatJuridica.equalsIgnoreCase("0400")
											)
											{
												String ls_caracter;

												ls_caracter = lc_constante.getCaracter();

												if(StringUtils.isValidString(ls_caracter))
												{
													Map<String, String> lmss_temp;

													lmss_temp = ListadoCodigosConstantes.generarCodigos(ls_caracter);

													if(CollectionUtils.isValidCollection(lmss_temp))
													{
														for(Map.Entry<String, String> lme_code : lmss_temp.entrySet())
														{
															if(lme_code != null)
															{
																String ls_key;

																ls_key = lme_code.getKey();

																if(StringUtils.isValidString(ls_key))
																{
																	if(ls_key.equalsIgnoreCase(ls_idTipoActo))
																		lb_return = true;
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
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarActoGrupoCancelacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Validar acto nat juridica acto migrado.
	 *
	 * @param as_idTipoActo de as id tipo acto
	 * @param as_cuantia de as cuantia
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarActoNatJuridicaActoMigrado(String as_idTipoActo, String as_cuantia)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(StringUtils.isValidString(as_idTipoActo) && StringUtils.isValidString(as_cuantia))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TipoActo lta_tipoActo;

				lta_tipoActo = DaoCreator.getTipoActoDAO(ldm_manager).findById(as_idTipoActo);

				if(lta_tipoActo != null)
				{
					String ls_actoSinCuantia;

					ls_actoSinCuantia = lta_tipoActo.getActoSinCuantia();

					if(StringUtils.isValidString(ls_actoSinCuantia))
					{
						if(
						    (as_cuantia.equalsIgnoreCase("ACTO CON CUANTIA")
							    && ls_actoSinCuantia.equalsIgnoreCase(EstadoCommon.N))
							    || (as_cuantia.equalsIgnoreCase("ACTO SIN CUANTIA")
							    && ls_actoSinCuantia.equalsIgnoreCase(EstadoCommon.S))
						)
							lb_return = true;
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultaAreaPredio", lb2be_e);

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
	 * Método que recibe una colección de actos y los valida contra la constante CODIGOS_CANCELACION_PROVIDENCIA.
	 *
	 * @param as_acto String de acto a validar contra constante
	 * @param ll_idMatricula Long de matrícula a consultar
	 * @param as_idCirculo String de circulo a consultar
	 * @return String con matricula de acto ingresada si se encontro en constante
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String validarActosCancelacionProvidencia(
	    String as_acto, Long ll_idMatricula, String as_idCirculo
	)
	    throws B2BException
	{
		String     lb_return;
		DAOManager ldm_manager;

		lb_return       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(
			    StringUtils.isValidString(as_acto) && NumericUtils.isValidLong(ll_idMatricula)
				    && StringUtils.isValidString(as_idCirculo)
			)
			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
						                     .findById(ConstanteCommon.CODIGOS_CANCELACION_PROVIDENCIA);

				if(lc_constante != null)
				{
					String ls_caracter;

					ls_caracter = lc_constante.getCaracter();

					if(StringUtils.isValidString(ls_caracter))
					{
						Map<String, String> lmss_caracter;

						lmss_caracter = ListadoCodigosConstantes.generarCodigos(ls_caracter);

						if(CollectionUtils.isValidCollection(lmss_caracter))
						{
							if(lmss_caracter.containsKey(as_acto))
							{
								Collection<String> lcs_gruposNaturalezaJuridica;

								lcs_gruposNaturalezaJuridica = DaoCreator.getAnotacionPredioDAO(ldm_manager)
										                                     .consultaAdjudicacionEmbargoVigente(
										    as_idCirculo, ll_idMatricula
										);

								if(CollectionUtils.isValidCollection(lcs_gruposNaturalezaJuridica))
								{
									Constantes lc_constanteMedidaCautelar;

									lc_constanteMedidaCautelar = DaoCreator.getConstantesDAO(ldm_manager)
											                                   .findById(
											    ConstanteCommon.GRUPO_NATURALEZA_JURIDICA_MEDIDA_CAUTELAR
											);

									if(lc_constanteMedidaCautelar != null)
									{
										String ls_caracterConstanteMedidaCautelar;

										ls_caracterConstanteMedidaCautelar = lc_constanteMedidaCautelar.getCaracter();

										if(StringUtils.isValidString(ls_caracterConstanteMedidaCautelar))
										{
											for(String ls_tmp : lcs_gruposNaturalezaJuridica)
											{
												if(StringUtils.isValidString(ls_tmp))
												{
													if(ls_tmp.equalsIgnoreCase(ls_caracterConstanteMedidaCautelar))
														lb_return = ll_idMatricula.toString();
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

			clh_LOGGER.error("validarActosCancelacionProvidencia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Valida que los actos activos para un turno específico sean medidas cautelares.
	 *
	 * @param as_idTurno id turno a utilizar como filtro en la consulta
	 * @return true si existen actos pertenecientes a medidas cautelares
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarActosDeMedidaCautelar(String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_pertenece;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lb_pertenece     = false;

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.TURNO_INVALIDO);

			Turno lt_turno;

			lt_turno = new Turno();

			lt_turno.setIdTurno(as_idTurno);

			lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

			if(lt_turno == null)
				throw new B2BException(ErrorKeys.TURNO_INVALIDO);

			String                             ls_idCirculo;
			String                             ls_idSolicitud;
			SolicitudMatriculaActo             lsma_temp;
			Collection<SolicitudMatriculaActo> lcsma_matrActos;

			ls_idCirculo       = StringUtils.getStringNotNull(lt_turno.getIdCirculo());
			ls_idSolicitud     = StringUtils.getStringNotNull(lt_turno.getIdSolicitud());
			lsma_temp          = new SolicitudMatriculaActo();

			lsma_temp.setIdCirculo(ls_idCirculo);
			lsma_temp.setIdSolicitud(ls_idSolicitud);

			lcsma_matrActos = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager)
					                        .findByIdSolicitudCirculo(lsma_temp, true);

			if(CollectionUtils.isValidCollection(lcsma_matrActos))
			{
				Iterator<SolicitudMatriculaActo> lisma_iterador;
				ActoDAO                          la_DAO;

				lisma_iterador     = lcsma_matrActos.iterator();
				la_DAO             = DaoCreator.getActoDAO(ldm_manager);

				while(lisma_iterador.hasNext() && !lb_pertenece)
				{
					SolicitudMatriculaActo lsma_solMatAct;

					lsma_solMatAct = lisma_iterador.next();

					if(lsma_solMatAct != null)
					{
						Acto la_acto;

						la_acto = new Acto();

						la_acto.setIdActo(lsma_solMatAct.getIdActo());

						la_acto = la_DAO.findById(la_acto);

						if(la_acto != null)
							lb_pertenece = perteneceActoMedidaCautelar(la_acto.getIdTipoActo());
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarActosDeMedidaCautelar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_pertenece;
	}

	/**
	 * Método encargado de validar si ya se han ingresado actos para mayor valor.
	 *
	 * @param as_idSolicitud Variable de tipo String que contiene el id de la solicitud.
	 * @return Boolean que valida si ya se han ingresado actos para mayor valor.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean validarActosMayorValor(String as_idSolicitud)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
				lb_return = CollectionUtils.isValidCollection(
					    DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudMayorValor(as_idSolicitud)
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarActosMayorValor", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de validar los actos para el proceso de registro parcial.
	 *
	 * @param as_solicitud Variable de tipo String que contiene la información de la solicitud para validar los actos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void validarActosParcial(Solicitud as_solicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_solicitud != null)
			{
				AccCompletitudDocumentalDAO          acd_DAO;
				Collection<AccCompletitudDocumental> lcacd_completidudDocumental;
				Constantes                           lc_constante;
				ConstantesDAO                        lc_DAO;
				String                               ls_idSolicitud;

				acd_DAO                         = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);
				lcacd_completidudDocumental     = null;
				ls_idSolicitud                  = as_solicitud.getIdSolicitud();
				lc_constante                    = new Constantes();
				lc_DAO                          = DaoCreator.getConstantesDAO(ldm_manager);

				if(StringUtils.isValidString(ls_idSolicitud))
					lcacd_completidudDocumental = acd_DAO.findAllByIdSolicitud(ls_idSolicitud);

				if(CollectionUtils.isValidCollection(lcacd_completidudDocumental))
				{
					boolean lb_accion;

					lb_accion = false;

					for(AccCompletitudDocumental lacd_completitud : lcacd_completidudDocumental)
					{
						if(lacd_completitud != null)
						{
							String ls_idTipoDocumental;

							lc_constante.setIdConstante(ConstanteCommon.OFICIO_COMPLETITUD_REGISTRO_PARCIAL);

							ls_idTipoDocumental     = lacd_completitud.getIdTipoDocumental();
							lc_constante            = lc_DAO.findById(lc_constante);

							if(lc_constante != null)
							{
								String ls_caracter;

								ls_caracter = lc_constante.getCaracter();

								if(StringUtils.isValidString(ls_caracter))
								{
									if(
									    StringUtils.isValidString(ls_idTipoDocumental)
										    && ls_idTipoDocumental.equalsIgnoreCase(ls_caracter)
									)
										lb_accion = true;
								}
								else
									throw new B2BException(ErrorKeys.DEBE_AGREGAR_ACTO_OFICIO);
							}
							else
								throw new B2BException(ErrorKeys.DEBE_AGREGAR_ACTO_OFICIO);
						}
					}

					if(!lb_accion)
						throw new B2BException(ErrorKeys.DEBE_AGREGAR_ACTO_OFICIO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarActosParcial", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para validar si el acto tiene vencimiento.
	 *
	 * @param as_codigoActo correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarActosVencimientoDiasHabiles(String as_codigoActo)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_codigoActo))
			{
				Constantes lc_constante;

				lc_constante = new Constantes();

				lc_constante.setIdConstante(ConstanteCommon.ACTOS_VENCIMIENTO_DIAS_HABILES);

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
							lb_return = lmss_listado.containsKey(as_codigoActo);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarActosVencimientoDiasHabiles", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Validar anexos.
	 *
	 * @param aa_acto correspondiente al valor del tipo de objeto Acto
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarAnexos(com.bachue.snr.prosnr01.model.registro.Acto aa_acto)
	    throws B2BException
	{
		boolean    lb_actos;
		DAOManager ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_actos        = false;

		try
		{
			if(aa_acto != null)
			{
				boolean                    lb_validar;
				Collection<TipoDocumental> lctd_completitud;
				Collection<TipoDocumental> lctd_obligatorio;
				String                     ls_idActo;
				String                     ls_idActoPrincipal;
				String                     ls_codigo;
				String                     ls_idSolicitud;

				lb_validar             = true;
				ls_idActo              = aa_acto.getIdActoDb();
				ls_idActoPrincipal     = aa_acto.getIdActoPrincipal();
				ls_codigo              = aa_acto.getCodigo();
				ls_idSolicitud         = aa_acto.getIdSolicitud();

				if(StringUtils.isValidString(ls_idActoPrincipal))
				{
					Acto la_acto;

					la_acto = new Acto();

					la_acto.setIdActo(ls_idActoPrincipal);
					la_acto.setIdSolicitud(ls_idSolicitud);

					la_acto        = DaoCreator.getActoDAO(ldm_manager).findByIdAndSolicitud(la_acto);
					lb_validar     = la_acto != null;
				}

				if(lb_validar)
				{
					lctd_completitud     = DaoCreator.getTipoActoDAO(ldm_manager)
							                             .documentalesCompletitud(
							    ls_idActo, ls_codigo, ls_idSolicitud, true
							);

					lctd_obligatorio = DaoCreator.getTipoActoDAO(ldm_manager)
							                         .documentalesCompletitud(
							    ls_idActo, ls_codigo, ls_idSolicitud, false
							);

					if(
					    !CollectionUtils.isValidCollection(lctd_completitud)
						    && CollectionUtils.isValidCollection(lctd_obligatorio)
					)
						lb_actos = true;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarAnexos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_actos;
	}

	/**
	 * Método encargado de validar si existe una anotación para el proceso de loteo.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación que se desea valdiar.
	 * @return Variable de tipo booleana que valida si existe una anotación para el acto de loteo.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarAnotacionLoteo(AnotacionPredio aap_anotacionPredio)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(aap_anotacionPredio != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				AnotacionPredio lap_temp;

				lap_temp      = DaoCreator.getAnotacionPredioDAO(ldm_manager)
						                      .findByNaturalezaJuridicaPredio(aap_anotacionPredio);
				lb_return     = lap_temp != null;
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("validarAnotacionLoteo", lb2be_e);

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
	 * Valida si el certificado es obligatorio para un tipo de acto.
	 *
	 * @param as_idActo id del acto a validar
	 * @return true si es obligatorio la cantidad de certificados
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarCertificadoObligatorio(String as_idActo)
	    throws B2BException
	{
		DAOManager                                 ldm_manager;
		com.bachue.snr.prosnr01.model.sdb.acc.Acto la_acto;
		boolean                                    ab_return;

		la_acto         = new Acto();
		ldm_manager     = DaoManagerFactory.getDAOManager();
		ab_return       = false;

		try
		{
			if(StringUtils.isValidString(as_idActo))
			{
				la_acto.setIdActo(as_idActo);

				la_acto = DaoCreator.getActoDAO(ldm_manager).findById(la_acto);

				if(la_acto != null)
				{
					TipoActo lta_datos;
					lta_datos = new TipoActo();

					lta_datos.setIdTipoActo(la_acto.getIdTipoActo());

					lta_datos = DaoCreator.getTipoActoDAO(ldm_manager).findById(lta_datos);

					if(lta_datos == null)
						lta_datos = new TipoActo();

					String ls_certificadoObl;
					ls_certificadoObl = lta_datos.getCertificadoObligatorioString();

					if(
					    StringUtils.isValidString(ls_certificadoObl)
						    && ls_certificadoObl.equalsIgnoreCase(EstadoCommon.S)
					)
						ab_return = true;
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
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

		return ab_return;
	}

	/**
	 * Método de validación del circulo del usuario.
	 *
	 * @param as_userId con el usuario a buscar
	 * @return de tipo boolean con la respuesta a la validación
	 * @throws B2BException en caso de error
	 */
	public synchronized boolean validarCirculoSNRUsuario(String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_usuarioSNR;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		lb_usuarioSNR     = false;

		try
		{
			if(StringUtils.isValidString(as_userId))
			{
				Collection<UsuarioCirculo> lc_usuariosCirculo;
				lc_usuariosCirculo = DaoCreator.getUsuarioCirculoDAO(ldm_manager).findByUsuarioActive(as_userId);

				if(CollectionUtils.isValidCollection(lc_usuariosCirculo))
				{
					for(UsuarioCirculo iuc_iterador : lc_usuariosCirculo)
					{
						if(iuc_iterador != null)
						{
							String ls_idCirculo;
							ls_idCirculo = iuc_iterador.getIdCirculo();

							if(
							    StringUtils.isValidString(ls_idCirculo)
								    && ls_idCirculo.equals(ConstanteCommon.CIRCULO_CENTRAL_SNR)
							)
								lb_usuarioSNR = true;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarCirculoSNRUsuario", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_usuarioSNR;
	}

	/**
	 * Método encargado de validar el excel de cargue masivo para intervinientes.
	 *
	 * @param aba_archivo Archivo excel a validar
	 * @param as_solicitud String solicitud
	 * @param as_nombreFile String con el nombre del archivo
	 * @param as_userID String con el id del usuario
	 * @param as_ipLocal String con la IP local
	 * @return Collection<SolicitudInterviniente> con los intervinientes validados
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 *
	 */
	public synchronized Collection<SolicitudInterviniente> validarExcelIntervinientesMasivos(
	    byte[] aba_archivo, String as_solicitud, String as_nombreFile, String as_userID, String as_ipLocal
	)
	    throws B2BException, IOException
	{
		DAOManager                         ldm_manager;
		Collection<SolicitudInterviniente> lcsi_intervinientesIngresados;

		ldm_manager                       = DaoManagerFactory.getDAOManager();
		lcsi_intervinientesIngresados     = new ArrayList<SolicitudInterviniente>();

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
					boolean    lb_exitoso;
					int        ll_numcol;
					Constantes lc_constante;
					int        li_countSecuencia;

					lb_exitoso            = true;
					ll_numcol             = 9;
					lc_constante          = new Constantes();
					li_countSecuencia     = 0;

					lc_constante.setIdConstante(ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA);

					lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_constante);

					for(int ii_fila = 0; ii_fila <= li_ultimaFila; ii_fila++)
					{
						if(ii_fila != 0)
						{
							String  ls_temp;
							Long    ll_temp;
							boolean lb_esNit;
							boolean lb_esSecuencia;

							SolicitudInterviniente li_interviniente;
							Persona                lp_persona;
							Row                    lr_filaActual;
							StringBuilder          lsb_mensaje;

							lb_esNit             = false;
							lb_esSecuencia       = false;
							li_interviniente     = new SolicitudInterviniente();
							lp_persona           = new Persona();
							lr_filaActual        = lsh_hoja.getRow(ii_fila);
							lsb_mensaje          = new StringBuilder();

							try
							{
								ParameterBusiness lpb_parameterBusiness;

								lpb_parameterBusiness     = getParameterBusiness();

								ls_temp = validarStringCeldaExcel(lr_filaActual, ii_fila, 1, "Tipo Documento", true);

								if(lpb_parameterBusiness.validarTipoDocumento(ls_temp))
								{
									if(
									    StringUtils.isValidString(ls_temp)
										    && ls_temp.equalsIgnoreCase(IdentificadoresCommon.NIT)
									)
										lb_esNit = true;
								}
								else
								{
									if(
									    StringUtils.isValidString(ls_temp)
										    && ls_temp.equalsIgnoreCase(IdentificadoresCommon.SE)
									)
										lb_esSecuencia = true;
									else
									{
										lb_exitoso = false;

										lsb_mensaje.append(ErrorKeys.ERROR_ATTR_USUARIO_E1);
									}
								}

								lp_persona.setIdDocumentoTipo(ls_temp);
							}
							catch(B2BException ab2be_e)
							{
								lb_exitoso = false;
								lsb_mensaje.append(ab2be_e.getMessage());
							}

							for(int li_celda = 0; li_celda < ll_numcol; li_celda++)
							{
								try
								{
									if(li_celda == 0)
									{
										ls_temp = validarStringCeldaExcel(
											    lr_filaActual, ii_fila, li_celda, "ROL", true
											);

										if(
										    StringUtils.isValidString(ls_temp)
											    && (ls_temp.equalsIgnoreCase(EstadoCommon.De)
											    || ls_temp.equalsIgnoreCase(EstadoCommon.A))
										)
										{
											if(ls_temp.equalsIgnoreCase(EstadoCommon.De))
												ls_temp = EstadoCommon.De;
											else if(ls_temp.equalsIgnoreCase(EstadoCommon.A))
												ls_temp = EstadoCommon.A;

											li_interviniente.setRolPersona(ls_temp);
										}
										else
											throw new B2BException(ErrorKeys.ERROR_CARGUE_INTERVINIENTES_MASIVO_ROL);
									}

									if(li_celda == 2)
									{
										if(lb_esNit)
										{
											String ls_numDocumento;

											ls_numDocumento = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "NIT", true
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

										else if(lb_esSecuencia)
										{
											if(lc_constante != null)
											{
												BigInteger lbi_actualConstante;

												lbi_actualConstante = lc_constante.getEntero()
														                              .add(
														    NumericUtils.getBigInteger(1)
														);

												if(NumericUtils.isValidBigInteger(lbi_actualConstante))
													lp_persona.setNumeroDocumento(
													    StringUtils.getString(
													        lbi_actualConstante.add(
													            NumericUtils.getBigInteger(li_countSecuencia++)
													        )
													    )
													);
												else
													throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
											}
										}
										else
										{
											ll_temp = ExcelUtils.validarLongCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "DOCUMENTO", true
												);

											if(NumericUtils.isValidLong(ll_temp))
												lp_persona.setNumeroDocumento(StringUtils.getString(ll_temp));
											else
												throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
										}
									}

									if(lb_esNit)
									{
										if(li_celda == 3)
										{
											ls_temp = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "PRIMER NOMBRE", false
												);

											lp_persona.setPrimerNombre(
											    StringUtils.isValidString(ls_temp) ? ls_temp : ""
											);
										}
										else if(li_celda == 4)
										{
											ls_temp = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "SEGUNDO NOMBRE", false
												);

											lp_persona.setSegundoNombre(
											    StringUtils.isValidString(ls_temp) ? ls_temp : ""
											);
										}
										else if(li_celda == 5)
										{
											ls_temp = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "PRIMER APELLIDO", false
												);

											lp_persona.setPrimerApellido(
											    StringUtils.isValidString(ls_temp) ? ls_temp : ""
											);
										}
										else if(li_celda == 6)
										{
											ls_temp = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "SEGUNDO APELLIDO", false
												);

											lp_persona.setSegundoApellido(
											    StringUtils.isValidString(ls_temp) ? ls_temp : ""
											);
										}

										if(li_celda == 7)
										{
											ls_temp = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "RAZÓN SOCIAL", true
												);

											lp_persona.setRazonSocial(
											    StringUtils.isValidString(ls_temp) ? ls_temp : ""
											);
										}
									}
									else
									{
										if(li_celda == 3)
										{
											ls_temp = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "PRIMER NOMBRE", true
												);

											lp_persona.setPrimerNombre(
											    StringUtils.isValidString(ls_temp) ? ls_temp : ""
											);
										}
										else if(li_celda == 4)
										{
											ls_temp = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "SEGUNDO NOMBRE", false
												);
											lp_persona.setSegundoNombre(
											    StringUtils.isValidString(ls_temp) ? ls_temp : ""
											);
										}
										else if(li_celda == 5)
										{
											ls_temp = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "PRIMER APELLIDO", true
												);
											lp_persona.setPrimerApellido(
											    StringUtils.isValidString(ls_temp) ? ls_temp : ""
											);
										}
										else if(li_celda == 6)
										{
											ls_temp = validarStringCeldaExcel(
												    lr_filaActual, ii_fila, li_celda, "SEGUNDO APELLIDO", false
												);
											lp_persona.setSegundoApellido(
											    StringUtils.isValidString(ls_temp) ? ls_temp : ""
											);
										}
									}

									if(li_celda == 8)
									{
										ls_temp = validarPorcentajeCeldaExcel(
											    lr_filaActual, ii_fila, li_celda, "PARTICIPACION"
											);
										li_interviniente.setPorcentaje(ls_temp);
									}

									li_interviniente.setPersona(lp_persona);
								}
								catch(B2BException ab2be_e)
								{
									lb_exitoso = false;
									lsb_mensaje.append(ab2be_e.getMessage());
								}
							}

							if(StringUtils.isValidString(lsb_mensaje.toString()))
								li_interviniente.setErrorIntervinienteMasivo(lsb_mensaje.toString());

							lcsi_intervinientesIngresados.add(li_interviniente);
						}
					}

					if(lb_exitoso && CollectionUtils.isValidCollection(lcsi_intervinientesIngresados))
					{
						Collection<SolicitudInterviniente> csi_exitosos;
						String                             ls_secuenciaActualizada;

						csi_exitosos                = new ArrayList<SolicitudInterviniente>();
						ls_secuenciaActualizada     = null;

						for(SolicitudInterviniente lsi_actual : lcsi_intervinientesIngresados)
						{
							if(lsi_actual != null)
							{
								Persona lp_personaTemp;

								lp_personaTemp = lsi_actual.getPersona();

								if(lp_personaTemp != null)
								{
									String ls_tipoDocumento;

									ls_tipoDocumento = lp_personaTemp.getIdDocumentoTipo();

									if(StringUtils.isValidString(ls_tipoDocumento))
									{
										String ls_idPersona;

										if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
											lp_personaTemp.setIdTipoPersona(EstadoCommon.J);
										else
											lp_personaTemp.setIdTipoPersona(EstadoCommon.N);

										ls_idPersona = marcarFlagPersona(
											    ldm_manager, lp_personaTemp, as_userID, as_ipLocal
											);

										if(StringUtils.isValidString(ls_idPersona))
										{
											lp_personaTemp.setIdPersona(ls_idPersona);

											lp_personaTemp = DaoCreator.getPersonaDAO(ldm_manager)
													                       .findById(lp_personaTemp);

											if(lp_personaTemp != null)
											{
												lsi_actual.setPersona(lp_personaTemp);

												csi_exitosos.add(lsi_actual);
											}
											else
												throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

											if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.SE))
												ls_secuenciaActualizada = lp_personaTemp.getNumeroDocumento();
										}
									}
								}
							}
						}

						if(StringUtils.isValidString(ls_secuenciaActualizada))
						{
							Constantes lc_constanteActualizada;

							lc_constanteActualizada = new Constantes();

							lc_constanteActualizada.setIdConstante(
							    ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA
							);
							lc_constanteActualizada.setEntero(NumericUtils.getBigInteger(ls_secuenciaActualizada));
							lc_constanteActualizada.setIdUsuarioModificacion(as_userID);
							lc_constanteActualizada.setIpModificacion(as_ipLocal);

							DaoCreator.getConstantesDAO(ldm_manager).insertOrUpdate(lc_constanteActualizada, false);
						}

						lcsi_intervinientesIngresados = csi_exitosos;
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

		if(lcsi_intervinientesIngresados.isEmpty())
			lcsi_intervinientesIngresados = null;

		return lcsi_intervinientesIngresados;
	}

	/**
	 * Método encargado de validar si ya existe un documento con los datos ingresados.
	 *
	 * @param ad_parametroDoc Objeto que contiene la información del documento que se desea consultar.
	 * @return Objeto que contiene la información del documento validado, como los nir asosiados al documento en caso de existir.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ValidacionDocumento
	 */
	public synchronized ValidacionDocumento validarExistenciaDocumento(Documento ad_parametroDoc)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		ValidacionDocumento lvd_valida;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lvd_valida      = new ValidacionDocumento();

		try
		{
			if(ad_parametroDoc != null)
			{
				Collection<Documento> lcd_dataDocumento;
				Collection<String>    lcs_nirs;

				lcd_dataDocumento     = DaoCreator.getDocumentoDAO(ldm_manager).consultaDocumentos(ad_parametroDoc);
				lcs_nirs              = new ArrayList<String>();

				if(CollectionUtils.isValidCollection(lcd_dataDocumento))
				{
					lvd_valida.setValidacion(true);

					for(Documento ld_iterador : lcd_dataDocumento)
					{
						if(ld_iterador != null)
						{
							String ls_idDocumento;

							ls_idDocumento = ld_iterador.getIdDocumento();

							if(StringUtils.isValidString(ls_idDocumento))
							{
								Collection<Solicitud> lcs_datosSolicitud;

								lcs_datosSolicitud = DaoCreator.getSolicitudDAO(ldm_manager)
										                           .findAllByIdDocumento(ls_idDocumento);

								lvd_valida.setDocumento(ld_iterador);

								if(CollectionUtils.isValidCollection(lcs_datosSolicitud))
								{
									for(Solicitud ls_iterador : lcs_datosSolicitud)
									{
										if(ls_iterador != null)
										{
											Long   ll_estadoSolicitud;
											String ls_nir;

											ll_estadoSolicitud     = ls_iterador.getEstadoSolicitud();
											ls_nir                 = ls_iterador.getNir();

											if(
											    StringUtils.isValidString(ls_nir)
												    && NumericUtils.isValidLong(ll_estadoSolicitud)
											)
											{
												if(ll_estadoSolicitud.compareTo(NumericUtils.getLongWrapper(5L)) != 0)
												{
													lcs_nirs.add(ls_nir);
													lvd_valida.setNir(ls_nir);
												}
											}
										}
									}
								}
							}
						}
					}

					if(CollectionUtils.isValidCollection(lcs_nirs))
						lvd_valida.setNirs(lcs_nirs);
					else
						lvd_valida.setValidacion(false);
				}
				else
					lvd_valida.setValidacion(false);
			}
			else
				lvd_valida.setValidacion(false);
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

		return lvd_valida;
	}

	/**
	 * Retorna el valor del objeto de ValidacionDocumento.
	 *
	 * @param ap_parametrodoc correspondiente al valor del tipo de objeto DocumentoConstancia
	 * @return devuelve el valor de ValidacionDocumento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ValidacionDocumento
	 */
	public synchronized ValidacionDocumento validarExistenciaDocumentoConstancia(DocumentoConstancia ap_parametrodoc)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		DocumentoConstancia ld_documento;
		ValidacionDocumento lvd_valida;
		String              ls_idDocumento;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		ld_documento       = null;
		ls_idDocumento     = null;
		lvd_valida         = new ValidacionDocumento();
		;

		try
		{
			if(ap_parametrodoc != null)
			{
				ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).consultaDocumentoValidar(ap_parametrodoc);

				if(ld_documento != null)
				{
					ls_idDocumento = ld_documento.getIdDocumento();

					if(StringUtils.isValidString(ls_idDocumento))
						lvd_valida.setValidacion(true);
				}
				else
					lvd_valida.setValidacion(false);
			}
			else
				lvd_valida.setValidacion(false);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarExistenciaDocumentoConstancia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lvd_valida;
	}

	/**
	 * Método para validar si el documento registrado existe y si la solicitud es diferente de generado.
	 *
	 * @param ad_parametroDoc correspondiente al valor del tipo de objeto Documento
	 * @return devuelve el valor de Documento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	public synchronized Documento validarExistenciaDocumentoEjecutoria(Documento ad_parametroDoc)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_valida;
		Documento  ld_final;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_valida       = false;
		ld_final        = new Documento();

		try
		{
			if(ad_parametroDoc != null)
			{
				Collection<Documento> lcd_dataDocumento;

				lcd_dataDocumento = DaoCreator.getDocumentoDAO(ldm_manager).consultaDocumentos(ad_parametroDoc);

				if(CollectionUtils.isValidCollection(lcd_dataDocumento))
				{
					for(Documento ld_iterador : lcd_dataDocumento)
					{
						if(ld_iterador != null)
						{
							String ls_idDocumento;

							ls_idDocumento = ld_iterador.getIdDocumento();

							if(StringUtils.isValidString(ls_idDocumento))
							{
								Collection<Solicitud> lcs_datosSolicitud;

								lcs_datosSolicitud = DaoCreator.getSolicitudDAO(ldm_manager)
										                           .findAllByIdDocumento(ls_idDocumento);

								if(CollectionUtils.isValidCollection(lcs_datosSolicitud))
								{
									for(Solicitud ls_iterador : lcs_datosSolicitud)
									{
										if(ls_iterador != null)
										{
											Long ll_estadoSolicitud;

											ll_estadoSolicitud = ls_iterador.getEstadoSolicitud();

											if(NumericUtils.isValidLong(ll_estadoSolicitud))
											{
												if(NumericUtils.getLong(ll_estadoSolicitud) != 1)
												{
													ld_final      = ld_iterador;
													lb_valida     = true;
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
					lb_valida = true;
			}

			ld_final.setExistenciaDocumentoEjecutoria(lb_valida);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("ValidacionDocumentoEjecutoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ld_final;
	}

	/**
	 * Método encargado de validar si existen documentos en la tabla de completitud documental.
	 *
	 * @param as_solicitud Variable de tipo String que contiene la información de la solicitud para validar.
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarInvocacionCapture(Solicitud as_solicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_invocarCapture;

		ldm_manager           = DaoManagerFactory.getDAOManager();
		lb_invocarCapture     = false;

		try
		{
			if(as_solicitud != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = as_solicitud.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
					lb_invocarCapture = CollectionUtils.isValidCollection(
						    DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager).findAllByIdSolicitud(ls_idSolicitud)
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarInvocacionCapture", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_invocarCapture;
	}

	/**
	 * Validar notificar correspondencia.
	 *
	 * @param as_solicitud correspondiente al valor del tipo de objeto Solicitud
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarNotificarCorrespondencia(Solicitud as_solicitud)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(as_solicitud != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_solicitud.getIdSolicitud());

				if(ls_solicitud != null)
				{
					Documento ld_documento;

					ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ls_solicitud.getIdDocumento());

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

							ls_notificarCorrespondencia     = StringUtils.getStringNotNull(
								    loo_oficinaOrigen.getNotificarCorrespondencia()
								);

							lb_return = ls_notificarCorrespondencia.equalsIgnoreCase(EstadoCommon.S);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("validarNotificarCorrespondencia", lb2be_e);

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
	 * Método encargado de validar los datos para el tramite de nueva entrada.
	 *
	 * @param ane_nuevaEntrada Objeto que contiene la información del proceso nueva entrada que se desea validar.
	 * @return Objeto que contiene la información de la nueva entrada validada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NuevaEntrada
	 */
	public synchronized NuevaEntrada validarNuevaEntrada(NuevaEntrada ane_nuevaEntrada)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		if(ane_nuevaEntrada != null)
		{
			Solicitud ls_solicitudNuevaEntrada;

			ls_solicitudNuevaEntrada = ane_nuevaEntrada.getSolicitud();

			if(ls_solicitudNuevaEntrada != null)
			{
				try
				{
					String ls_idTurnoAnt;
					String ls_nirAsociado;

					ls_idTurnoAnt      = ls_solicitudNuevaEntrada.getIdTurnoAnt();
					ls_nirAsociado     = ls_solicitudNuevaEntrada.getNirAsociado();

					if(!StringUtils.isValidString(ls_idTurnoAnt))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_TURNO_ANTERIOR);

					Turno lt_turnoAnt;

					lt_turnoAnt = new Turno();

					lt_turnoAnt.setIdTurno(ls_idTurnoAnt);

					lt_turnoAnt = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turnoAnt);

					if(lt_turnoAnt != null)
					{
						String ls_anulado;
						String ls_idSolicitudTurno;

						ls_anulado = lt_turnoAnt.getAnulado();

						if(StringUtils.isValidString(ls_anulado) && ls_anulado.equalsIgnoreCase(EstadoCommon.S))
							throw new B2BException(ErrorKeys.ERROR_TURNO_ANULADO);

						ls_idSolicitudTurno = lt_turnoAnt.getIdSolicitud();

						Solicitud ls_solicitud;

						ls_solicitud = null;

						if(StringUtils.isValidString(ls_nirAsociado))
						{
							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(ls_solicitudNuevaEntrada);

							if(ls_solicitud != null)
							{
								String ls_idSolicitudNir;

								ls_idSolicitudNir = ls_solicitud.getIdSolicitud();

								if(
								    StringUtils.isValidString(ls_idSolicitudNir)
									    && StringUtils.isValidString(ls_idSolicitudTurno)
								)
								{
									if(ls_idSolicitudNir.equalsIgnoreCase(ls_idSolicitudTurno))
										ls_solicitudNuevaEntrada.setNirAsociado(ls_nirAsociado);
									else
										throw new B2BException(ErrorKeys.ERROR_NIR_TURNO_RELACION);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_NIR_TURNO_INVALIDO);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_NIR_INVALIDO);
						}
						else
						{
							ls_solicitud = new Solicitud();

							ls_solicitud.setIdSolicitud(ls_idSolicitudTurno);

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

							if(ls_solicitud != null)
							{
								ls_nirAsociado = ls_solicitud.getNir();

								if(StringUtils.isValidString(ls_nirAsociado))
									ls_solicitudNuevaEntrada.setNirAsociado(ls_nirAsociado);
								else
									throw new B2BException(ErrorKeys.ERROR_SIN_NIR_PARA_TURNO);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_SIN_NIR_PARA_TURNO);
						}

						{
							Collection<Turno> lct_turnos;
							Long              ll_idEtapa;
							Turno             lt_turno;

							lct_turnos     = null;
							ll_idEtapa     = lt_turnoAnt.getIdEtapaActual();
							lt_turno       = new Turno();

							lt_turno.setIdTurno(ls_idTurnoAnt);

							if(NumericUtils.isValidLong(ll_idEtapa))
							{
								long ll_idEtapaActual;

								ll_idEtapaActual = NumericUtils.getLong(ll_idEtapa);

								if(
								    (ll_idEtapaActual == EtapaCommon.FINALIZACION_PROCESO_DE_REGISTRO_PARCIAL)
									    || (ll_idEtapaActual == EtapaCommon.FINALIZACION_PROCESO_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA)
								)
								{
									lct_turnos = DaoCreator.getTurnoDAO(ldm_manager).findTurnosRecurrente(lt_turno);

									{
										String ls_migrado;

										ls_migrado = lt_turnoAnt.getMigrado();

										if(
										    StringUtils.isValidString(ls_migrado)
											    && ls_migrado.equalsIgnoreCase(EstadoCommon.S)
										)
										{
											if(CollectionUtils.isValidCollection(lct_turnos))
											{
												for(Turno lt_tmp : lct_turnos)
												{
													if(lt_tmp != null)
													{
														String ls_idTurnoOriginal;

														ls_idTurnoOriginal = lt_tmp.getIdTurnoAnt();

														if(StringUtils.isValidString(ls_idTurnoOriginal))
														{
															String ls_idTurnoAsociado;

															ls_idTurnoAsociado = lt_tmp.getIdTurno();

															if(StringUtils.isValidString(ls_idTurnoAsociado))
															{
																Turno lt_turnoAsociado;

																lt_turnoAsociado = DaoCreator.getTurnoDAO(ldm_manager)
																		                         .findById(
																		    ls_idTurnoAsociado
																		);

																if(lt_turnoAsociado != null)
																{
																	String ls_turnoAsociadoMigrado;

																	ls_turnoAsociadoMigrado = lt_turnoAsociado
																			.getMigrado();

																	if(
																	    StringUtils.isValidString(
																		        ls_turnoAsociadoMigrado
																		    )
																		    && ls_turnoAsociadoMigrado.equalsIgnoreCase(
																		        EstadoCommon.S
																		    )
																	)
																	{
																		lt_tmp.setMigrado(EstadoCommon.S);

																		String ls_idLiquidacion;
																		long   ll_consecutivoLiquidacion;

																		ls_idLiquidacion              = lt_turnoAsociado
																				.getIdLiquidacion();
																		ll_consecutivoLiquidacion     = lt_turnoAsociado
																				.getConsecutivoLiquidacion();

																		if(
																		    !StringUtils.isValidString(
																			        ls_idLiquidacion
																			    ) && (ll_consecutivoLiquidacion <= 0)
																		)
																			throw new B2BException(
																			    ErrorKeys.ERROR_LIQUIDACIONES_TURNOS_MIGRADOS
																			);
																	}
																}
															}
														}
														else
														{
															String ls_turnoOriginal;

															ls_turnoOriginal = lt_tmp.getIdTurno();

															if(StringUtils.isValidString(ls_turnoOriginal))
															{
																Turno lt_turnoOriginal;

																lt_turnoOriginal = DaoCreator.getTurnoDAO(ldm_manager)
																		                         .findById(
																		    ls_turnoOriginal
																		);

																if(lt_turnoOriginal != null)
																{
																	String ls_turnoOriginalMigrado;

																	ls_turnoOriginalMigrado = lt_turnoOriginal
																			.getMigrado();

																	if(
																	    StringUtils.isValidString(
																		        ls_turnoOriginalMigrado
																		    )
																		    && ls_turnoOriginalMigrado.equalsIgnoreCase(
																		        EstadoCommon.S
																		    )
																	)
																	{
																		lt_tmp.setMigrado(EstadoCommon.S);

																		String ls_idLiquidacion;
																		long   ll_consecutivoLiquidacion;

																		ls_idLiquidacion              = lt_tmp
																				.getIdLiquidacion();
																		ll_consecutivoLiquidacion     = lt_tmp
																				.getConsecutivoLiquidacion();

																		if(
																		    !StringUtils.isValidString(
																			        ls_idLiquidacion
																			    ) && (ll_consecutivoLiquidacion <= 0)
																		)
																		{
																			lt_tmp.setIdTurnoAnt(lt_tmp.getIdTurno());
																			lt_tmp.setIdTurno(null);
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
									ane_nuevaEntrada.setNoEtapa501o502(true);
							}

							ane_nuevaEntrada.setTurnosRecurrencia(lct_turnos);
						}

						if(getDevolucionDineroBusiness().validacionTurnoDerivadoDevolucion(ls_idTurnoAnt, ldm_manager))
							throw new B2BException(ErrorKeys.ERROR_TURNO_PROCESO_DEVOLUCION_DINERO_NUEVA_ENTRADA);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TURNO_ANT_INVALIDO);
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("validarNuevaEntrada", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}

		return ane_nuevaEntrada;
	}

	/**
	 * Validar gravamenes pendientes para validación de roles en intervinientes.
	 *
	 * @param acgp_gravamenesPendientes Colección de gravamenes pendientes a validar
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public synchronized boolean validarRolGravamenesPendientes(Collection<GravamenPendiente> acgp_gravamenesPendientes)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_return       = false;

		try
		{
			if(CollectionUtils.isValidCollection(acgp_gravamenesPendientes))
			{
				NaturalezaJuridicaDAO       lnj_DAO;
				GrupoNaturalezaJuridicaDAO  lgnj_DAO;
				Iterator<GravamenPendiente> ligp_iterador;

				lnj_DAO           = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
				lgnj_DAO          = DaoCreator.getGrupoNaturalezaJuridicaDAO(ldm_manager);
				ligp_iterador     = acgp_gravamenesPendientes.iterator();

				while(ligp_iterador.hasNext() && !lb_return)
				{
					GravamenPendiente lgp_gravamenPendiente;

					lgp_gravamenPendiente = ligp_iterador.next();

					if(lgp_gravamenPendiente != null)
					{
						String ls_codigoActo;

						ls_codigoActo = lgp_gravamenPendiente.getCodigoActo();

						if(StringUtils.isValidString(ls_codigoActo))
						{
							NaturalezaJuridica lnj_natJuridica;

							lnj_natJuridica = lnj_DAO.findByIdMaxVersion(new NaturalezaJuridica(ls_codigoActo));

							if(lnj_natJuridica != null)
							{
								GrupoNaturalezaJuridica lgnj_grupoNatJuridica;

								lgnj_grupoNatJuridica = lgnj_DAO.findById(lnj_natJuridica.getIdGrupoNatJur());

								if(lgnj_grupoNatJuridica != null)
								{
									String ls_idGrupoNatJuridica;

									ls_idGrupoNatJuridica = lgnj_grupoNatJuridica.getIdGrupoNatJuridica();

									if(
									    StringUtils.isValidString(ls_idGrupoNatJuridica)
										    && ls_idGrupoNatJuridica.equalsIgnoreCase("0400")
									)
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

			clh_LOGGER.error("validarRolGravamenesPendientes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Validar segunda recepcion documentos.
	 *
	 * @param act_turnos de act turnos
	 * @param as_idProceso de as id proceso
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param as_solicitud de as solicitud
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String validarSegundaRecepcionDocumentos(
	    Collection<Turno> act_turnos, String as_idProceso, String as_userId, String as_remoteIp, Solicitud as_solicitud
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39))
				ls_return = validarSegundaRecepcionDocumentos2(
					    act_turnos, as_idProceso, as_userId, as_remoteIp, as_solicitud, ProcesoCommon.ID_PROCESO_39,
					    IdentificadoresCommon.NUM6
					);

			if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41))
			{
				lsb_sb.append(IdentificadoresCommon.NUM6);
				lsb_sb.append(IdentificadoresCommon.SIMBOLO_COMA_TXT);
				lsb_sb.append(IdentificadoresCommon.NUM3);
				ls_return = validarSegundaRecepcionDocumentos2(
					    act_turnos, as_idProceso, as_userId, as_remoteIp, as_solicitud, ProcesoCommon.ID_PROCESO_41,
					    lsb_sb.toString()
					);
			}

			if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_42))
			{
				lsb_sb.append(IdentificadoresCommon.NUM6);
				lsb_sb.append(IdentificadoresCommon.SIMBOLO_COMA_TXT);
				lsb_sb.append(IdentificadoresCommon.NUM3);
				lsb_sb.append(IdentificadoresCommon.SIMBOLO_COMA_TXT);
				lsb_sb.append(IdentificadoresCommon.NUM2);
				lsb_sb.append(IdentificadoresCommon.SIMBOLO_COMA_TXT);
				lsb_sb.append(IdentificadoresCommon.NUM1);
				ls_return = validarSegundaRecepcionDocumentos2(
					    act_turnos, as_idProceso, as_userId, as_remoteIp, as_solicitud, ProcesoCommon.ID_PROCESO_42,
					    lsb_sb.toString()
					);
			}

			if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43))
				ls_return = validarSegundaRecepcionDocumentos2(
					    act_turnos, as_idProceso, as_userId, as_remoteIp, as_solicitud, ProcesoCommon.ID_PROCESO_43,
					    IdentificadoresCommon.NUM6
					);

			if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40))
			{
				Constantes    lc_constante;
				ConstantesDAO lc_DAO;

				lc_DAO           = DaoCreator.getConstantesDAO(ldm_manager);
				lc_constante     = lc_DAO.findByIdWithImage(ConstanteCommon.SUBPROCESOS_VALIDOS_CERTIFICADOS);

				if(lc_constante != null)
					ls_return = validarSegundaRecepcionDocumentos2(
						    act_turnos, as_idProceso, as_userId, as_remoteIp, as_solicitud, ProcesoCommon.ID_PROCESO_40,
						    lc_constante.getCaracter()
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSegundaRecepcionDocumentos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Validar sub proceso.
	 *
	 * @param idSolicitud correspondiente al valor de id solicitud
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException
	 */
	public boolean validarSubProceso(String idSolicitud)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(idSolicitud))
			{
				Solicitud    ls_solicitud;
				SolicitudDAO solicitudDAO;

				solicitudDAO     = DaoCreator.getSolicitudDAO(ldm_manager);
				ls_solicitud     = solicitudDAO.findById(idSolicitud);

				if(ls_solicitud != null)
				{
					if(
					    (ls_solicitud.getIdSubproceso().equals(ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_SERVIDUMBRE))
						    || (ls_solicitud.getIdSubproceso()
						                        .equals(ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_PERTENENCIA))
						    || (ls_solicitud.getIdSubproceso().equals(
						        ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_AMPLIACION
						    )) || (ls_solicitud.getIdSubproceso().equals(ProcesoCommon.ID_SUBPROCESO_14))
					)
						lb_return = true;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSubProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Validar suma areas.
	 *
	 * @param acs_matriculas correspondiente al valor del tipo de objeto Collection<String>
	 * @param ad_area correspondiente al valor del tipo de objeto Double
	 * @param ab_sumatoria correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarSumaAreas(
	    Collection<String> acs_matriculas, Double ad_area, boolean ab_sumatoria
	)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = true;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acs_matriculas) && NumericUtils.isValidDouble(ad_area))
			{
				AreaPredioDAO           lap_DAO;
				DetalleAreaPredioDAO    ldap_DAO;
				AccAreaPredioDAO        laap_DAO;
				AccDetalleAreaPredioDAO ladap_DAO;
				double                  ld_area;
				double                  ld_areaTotal;
				Iterator<String>        lis_iterator;

				ld_area          = NumericUtils.getDouble(ad_area);
				ld_areaTotal     = 0;
				lis_iterator     = acs_matriculas.iterator();
				lap_DAO          = DaoCreator.getAreaPredioDAO(ldm_manager);
				ldap_DAO         = DaoCreator.getDetalleAreaPredioDAO(ldm_manager);
				laap_DAO         = DaoCreator.getAccAreaPredioDAO(ldm_manager);
				ladap_DAO        = DaoCreator.getAccDetalleAreaPredioDAO(ldm_manager);

				while(lis_iterator.hasNext() && lb_return)
				{
					String ls_matricula;
					ls_matricula = lis_iterator.next();

					if(StringUtils.isValidString(ls_matricula))
					{
						Long   ll_idMatricula;
						String ls_idCirculo;

						ll_idMatricula     = getIdMatricula(ls_matricula);
						ls_idCirculo       = getIdCirculo(ls_matricula);

						if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
						{
							AreaPredio lap_areaPredio;

							lap_areaPredio = lap_DAO.findById(ls_idCirculo, NumericUtils.getLong(ll_idMatricula));

							if(lap_areaPredio != null)
							{
								long ll_idArea;

								ll_idArea = lap_areaPredio.getIdArea();

								if(ll_idArea > 0)
								{
									DetalleAreaPredio ldap_detalle;

									ldap_detalle = ldap_DAO.findAllByIdAreaPredioTipoUnidad(
										    StringUtils.getString(ll_idArea), ls_idCirculo, ll_idMatricula,
										    TipoAreaCommon.TERRENO, UnidadMedidaAreaCommon.METROS_CUADRADOS
										);

									if(ldap_detalle != null)
										ld_areaTotal += NumericUtils.getDouble(ldap_detalle.getArea());
									else
										lb_return = false;
								}
								else
									throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
							}
							else
							{
								AccAreaPredio laap_areaPredio;

								laap_areaPredio = laap_DAO.findByCirculoMatricula(ls_idCirculo, ll_idMatricula);

								if(laap_areaPredio != null)
								{
									String ls_idArea;

									ls_idArea = laap_areaPredio.getIdArea();

									if(StringUtils.isValidString(ls_idArea))
									{
										DetalleAreaPredio ldap_detalle;
										ldap_detalle = ladap_DAO.findAllByIdAreaPredioTipoUnidad(
											    ls_idArea, ls_idCirculo, ll_idMatricula, TipoAreaCommon.TERRENO,
											    UnidadMedidaAreaCommon.METROS_CUADRADOS
											);

										if(ldap_detalle != null)
											ld_areaTotal += NumericUtils.getDouble(ldap_detalle.getArea());
										else
											lb_return = false;
									}
									else
										throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
								}
								else
								{
									Object[] loa_arg;

									loa_arg        = new String[2];
									loa_arg[0]     = ls_idCirculo;
									loa_arg[1]     = StringUtils.getString(ll_idMatricula);

									throw new B2BException(ErrorKeys.ERROR_AREA_PREDIO_MATRICULA_MATRIZ_SUMA, loa_arg);
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);
				}

				if((ld_areaTotal < ld_area) && lb_return)
					throw new B2BException(ErrorKeys.ERROR_SUMA_AREAS);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSumaAreas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método para validar si el TIPO ACTO es de Baldío.
	 *
	 * @param as_tipoActo correspondiente al valor del tipo de objeto String
	 * @return booleana
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarTipoActoBaldio(String as_tipoActo)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_tipoActo))
			{
				Map<String, String> lmss_listado;

				lmss_listado = generarCodigos(ConstanteCommon.TIPOS_ACTOS_BALDIOS, ldm_manager);

				if(CollectionUtils.isValidCollection(lmss_listado))
					lb_return = lmss_listado.containsKey(as_tipoActo);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarTipoActoBaldio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Validar turno certificado.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarTurnoCertificado(String as_idTurno, String as_idSolicitud, boolean ab_accion)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lb_return = turnoCertificado(as_idTurno, as_idSolicitud, ab_accion, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarTurnoCertificado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de validar si el turno ingresado para nueva entrada es valido.
	 *
	 * @param ane_nuevaEntrada Objeto que contiene la información del tramite nueva entrada para valdiar.
	 * @return Objeto que contiene la información de la nueva entrada validada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NuevaEntrada
	 */
	public synchronized NuevaEntrada validarVerificarTurno(NuevaEntrada ane_nuevaEntrada)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ane_nuevaEntrada != null)
			{
				Collection<TurnoDerivado> lctd_turnosDerivados;
				Solicitud                 ls_solicitudNuevaEntrada;
				TurnoDerivado             ltd_turnoDerivado;
				TurnoDerivadoDAO          ltd_DAO;
				TurnoHistoriaDAO          lth_DAO;

				ls_solicitudNuevaEntrada     = ane_nuevaEntrada.getSolicitud();
				ltd_turnoDerivado            = new TurnoDerivado();
				ltd_DAO                      = DaoCreator.getTurnoDerivadoDAO(ldm_manager);
				lth_DAO                      = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				if(ls_solicitudNuevaEntrada != null)
				{
					String ls_idTurnoAnt;

					ls_idTurnoAnt = ls_solicitudNuevaEntrada.getIdTurnoAnt();

					ltd_turnoDerivado.setIdTurnoPadre(ls_idTurnoAnt);

					lctd_turnosDerivados = ltd_DAO.findByIdTurnoPadre(ltd_turnoDerivado);

					if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
					{
						ane_nuevaEntrada.setTurnosDerivados(lctd_turnosDerivados);

						for(TurnoDerivado ltd_hijo : lctd_turnosDerivados)
						{
							if(ltd_hijo != null)
							{
								String ls_idTurnoHijo;

								ls_idTurnoHijo = ltd_hijo.getIdTurnoHijo();

								if(StringUtils.isValidString(ls_idTurnoHijo))
								{
									TurnoHistoria lth_turnoHistoriaHijo;

									lth_turnoHistoriaHijo = new TurnoHistoria();

									lth_turnoHistoriaHijo.setIdTurno(ls_idTurnoHijo);

									lth_turnoHistoriaHijo = lth_DAO.findByIdTurno(lth_turnoHistoriaHijo, true);

									if(lth_turnoHistoriaHijo != null)
									{
										BigDecimal ldb_idEtapaHijo;

										ldb_idEtapaHijo = lth_turnoHistoriaHijo.getIdEtapa();

										if(NumericUtils.isValidBigDecimal(ldb_idEtapaHijo))
										{
											long ll_idEtapaHijo;

											ll_idEtapaHijo = NumericUtils.getLong(ldb_idEtapaHijo);

											if(
											    ll_idEtapaHijo != EtapaCommon.CERTIFICADO_DEVUELTO_AL_PUBLICO_POR_NOTA_DEVOLUTIVA
											)
												ane_nuevaEntrada.setNoEtapa31(true);
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

			clh_LOGGER.error("validarVerificarTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ane_nuevaEntrada;
	}

	/**
	 * Método encargado de validar si el acto se puede trabajar desde antiguo sistema.
	 *
	 * @param acs_actos Colección de variable de tipo String que contiene el id del acto a validar.
	 * @return Variable de booleana que valida si el acto tiene la posibilidad de ser trabajado en antiguo sistema.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean verificarSiActoNoValidoParaAntSistema(Collection<String> acs_actos)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_noValido;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_noValido     = false;

		try
		{
			if(CollectionUtils.isValidCollection(acs_actos))
			{
				Iterator<String> lis_iterador;

				lis_iterador = acs_actos.iterator();

				while(lis_iterador.hasNext() && !lb_noValido)
				{
					String ls_idActo;

					ls_idActo = lis_iterador.next();

					if(StringUtils.isValidString(ls_idActo))
					{
						Acto la_acto;

						la_acto = new Acto();

						la_acto.setIdActo(ls_idActo);

						la_acto = DaoCreator.getActoDAO(ldm_manager).findById(la_acto);

						if(la_acto != null)
						{
							TipoActo lta_tipoActo;

							lta_tipoActo = DaoCreator.getTipoActoDAO(ldm_manager).findById(la_acto.getIdTipoActo());

							if(lta_tipoActo != null)
							{
								String ls_generaSegregacion;
								String ls_aperturaMatricula;

								ls_generaSegregacion     = StringUtils.getStringNotNull(
									    lta_tipoActo.getGeneraSegregacion()
									);
								ls_aperturaMatricula     = StringUtils.getStringNotNull(
									    lta_tipoActo.getAperturaMatricula()
									);

								if(
								    ls_generaSegregacion.equalsIgnoreCase(EstadoCommon.S)
									    || ls_aperturaMatricula.equalsIgnoreCase(EstadoCommon.S)
								)
									lb_noValido = true;
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("verificarSiActoNoValidoParaAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_noValido;
	}

	/**
	 * Agrega un String a un StringBuilder y despues agrega un espacio vacio.
	 *
	 * @param asb_stringBuilder StringBuilder a agregar una cadena
	 * @param as_cadena Cadena a agregar
	 */
	private synchronized void agregarCadenaConEspacioAStringBuilder(StringBuilder asb_stringBuilder, String as_cadena)
	{
		if((asb_stringBuilder != null) && StringUtils.isValidString(as_cadena))
		{
			asb_stringBuilder.append(as_cadena);
			asb_stringBuilder.append(IdentificadoresCommon.ESPACIO_VACIO);
		}
	}

	/**
	 * Método que consulta la informacion de una persona.
	 *
	 * @param acp_personas <code>Collection</code> llena de <code>Persona</code>
	 * @param adm_manager <code>DAOManager</code> que controla las transacciones con la base de datos
	 * @return <code>Registro</code> lleno de información
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Registro consultarInfoPersonas(Collection<Persona> acp_personas, DAOManager adm_manager)
	    throws B2BException
	{
		Registro ar_datos;

		ar_datos = new Registro();

		PersonaCorreoElectronicoDAO lpced_DAO;
		PersonaDireccionDAO         lpdd_DAO;
		PersonaTelefonoDAO          lptd_DAO;

		lpced_DAO     = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager);
		lpdd_DAO      = DaoCreator.getPersonaDireccionDAO(adm_manager);
		lptd_DAO      = DaoCreator.getPersonaTelefonoDAO(adm_manager);

		ar_datos.setListadoPersona(acp_personas);

		if(CollectionUtils.isValidCollection(acp_personas))
		{
			for(Persona lp_iterador : acp_personas)
			{
				if(lp_iterador != null)
				{
					String ls_idPersona;
					ls_idPersona = lp_iterador.getIdPersona();

					if(StringUtils.isValidString(ls_idPersona))
					{
						{
							PersonaTelefono lpt_telefono;

							Collection<PersonaTelefono> lcpt_datosCargados;
							Collection<PersonaTelefono> lcpt_datosConsultados;

							lpt_telefono = new PersonaTelefono();
							lpt_telefono.setIdPersona(ls_idPersona);
							lpt_telefono.setTipoTelefono("F");

							lcpt_datosCargados        = ar_datos.getListadoTelefonoFijo();
							lcpt_datosConsultados     = lptd_DAO.findByIdPersonaTipo(lpt_telefono);

							if(
							    CollectionUtils.isValidCollection(lcpt_datosConsultados)
								    && CollectionUtils.isValidCollection(lcpt_datosCargados)
							)
							{
								Collection<PersonaTelefono> lcpt_final;
								lcpt_final = new ArrayList<PersonaTelefono>();

								lcpt_datosCargados.addAll(lcpt_datosConsultados);

								for(PersonaTelefono lpt_telefonoFijo1 : lcpt_datosCargados)
								{
									if(lpt_telefonoFijo1 != null)
									{
										if(lcpt_final.isEmpty())
											lcpt_final.add(lpt_telefonoFijo1);
										else
										{
											String  ls_telefonoFijo1;
											boolean lb_repetido;

											ls_telefonoFijo1     = StringUtils.getStringNotNull(
												    lpt_telefonoFijo1.getTelefono()
												);
											lb_repetido          = false;

											for(PersonaTelefono lpt_telefonoFijo2 : lcpt_final)
											{
												if(lpt_telefonoFijo2 != null)
												{
													String ls_telefonoFijo2;

													ls_telefonoFijo2 = StringUtils.getStringNotNull(
														    lpt_telefonoFijo2.getTelefono()
														);

													if(ls_telefonoFijo1.equalsIgnoreCase(ls_telefonoFijo2))
													{
														lb_repetido = true;

														break;
													}
												}
											}

											if(!lb_repetido)
												lcpt_final.add(lpt_telefonoFijo1);
										}
									}
								}

								ar_datos.setListadoTelefonoFijo(lcpt_final);
							}
							else if(CollectionUtils.isValidCollection(lcpt_datosConsultados))
								ar_datos.setListadoTelefonoFijo(lcpt_datosConsultados);
							else if(CollectionUtils.isValidCollection(lcpt_datosCargados))
								ar_datos.setListadoTelefonoFijo(lcpt_datosCargados);
						}

						{
							PersonaTelefono lpt_telefono;

							Collection<PersonaTelefono> lcpt_datosCargados;
							Collection<PersonaTelefono> lcpt_datosConsultados;

							lpt_telefono = new PersonaTelefono();
							lpt_telefono.setIdPersona(ls_idPersona);
							lpt_telefono.setTipoTelefono("M");

							lcpt_datosCargados        = ar_datos.getListadoTelefonoMovil();
							lcpt_datosConsultados     = lptd_DAO.findByIdPersonaTipo(lpt_telefono);

							if(
							    CollectionUtils.isValidCollection(lcpt_datosConsultados)
								    && CollectionUtils.isValidCollection(lcpt_datosCargados)
							)
							{
								Collection<PersonaTelefono> lcpt_final;
								lcpt_final = new ArrayList<PersonaTelefono>();

								lcpt_datosCargados.addAll(lcpt_datosConsultados);

								for(PersonaTelefono lpt_telefonoMovil1 : lcpt_datosCargados)
								{
									if(lpt_telefonoMovil1 != null)
									{
										if(lcpt_final.isEmpty())
											lcpt_final.add(lpt_telefonoMovil1);
										else
										{
											String  ls_telefonoMovil1;
											boolean lb_repetido;

											ls_telefonoMovil1     = StringUtils.getStringNotNull(
												    lpt_telefonoMovil1.getTelefono()
												);
											lb_repetido           = false;

											for(PersonaTelefono lpt_telefonoMovil2 : lcpt_final)
											{
												if(lpt_telefonoMovil2 != null)
												{
													String ls_telefonoMovil2;

													ls_telefonoMovil2 = StringUtils.getStringNotNull(
														    lpt_telefonoMovil2.getTelefono()
														);

													if(ls_telefonoMovil1.equalsIgnoreCase(ls_telefonoMovil2))
													{
														lb_repetido = true;

														break;
													}
												}
											}

											if(!lb_repetido)
												lcpt_final.add(lpt_telefonoMovil1);
										}
									}
								}

								ar_datos.setListadoTelefonoMovil(lcpt_final);
							}
							else if(CollectionUtils.isValidCollection(lcpt_datosConsultados))
								ar_datos.setListadoTelefonoMovil(lcpt_datosConsultados);
							else if(CollectionUtils.isValidCollection(lcpt_datosCargados))
								ar_datos.setListadoTelefonoMovil(lcpt_datosCargados);
						}

						{
							Collection<PersonaDireccion> lcpd_datosCargados;
							Collection<PersonaDireccion> lcpd_datosConsultados;

							lcpd_datosCargados        = ar_datos.getListadoDireccionResidencia();
							lcpd_datosConsultados     = lpdd_DAO.findByIdPersona(ls_idPersona, true);

							if(
							    CollectionUtils.isValidCollection(lcpd_datosConsultados)
								    && CollectionUtils.isValidCollection(lcpd_datosCargados)
							)
							{
								Collection<PersonaDireccion> lcpd_dataFinal;
								lcpd_dataFinal = new LinkedList<PersonaDireccion>();

								lcpd_datosCargados.addAll(lcpd_datosConsultados);

								for(PersonaDireccion lpd_direccion1 : lcpd_datosCargados)
								{
									if(lpd_direccion1 != null)
									{
										if(lcpd_dataFinal.isEmpty())
											lcpd_dataFinal.add(lpd_direccion1);
										else
										{
											String  ls_direccion1;
											boolean lb_repetido;

											ls_direccion1     = StringUtils.getStringNotNull(
												    lpd_direccion1.getDireccion()
												);
											lb_repetido       = false;

											for(PersonaDireccion lpd_direccion2 : lcpd_dataFinal)
											{
												if(lpd_direccion2 != null)
												{
													String ls_direccion2;

													ls_direccion2 = StringUtils.getStringNotNull(
														    lpd_direccion2.getDireccion()
														);

													if(ls_direccion1.equalsIgnoreCase(ls_direccion2))
													{
														lb_repetido = true;

														break;
													}
												}
											}

											if(!lb_repetido)
												lcpd_dataFinal.add(lpd_direccion1);
										}
									}
								}

								ar_datos.setListadoDireccionResidencia(lcpd_dataFinal);
							}
							else if(CollectionUtils.isValidCollection(lcpd_datosConsultados))
								ar_datos.setListadoDireccionResidencia(lcpd_datosConsultados);
							else if(CollectionUtils.isValidCollection(lcpd_datosCargados))
								ar_datos.setListadoDireccionResidencia(lcpd_datosCargados);
						}

						{
							Collection<PersonaDireccion> lcpd_datosCargados;
							Collection<PersonaDireccion> lcpd_datosConsultados;

							lcpd_datosCargados        = ar_datos.getListadoDireccionCorrespondencia();
							lcpd_datosConsultados     = lpdd_DAO.findByIdPersona(ls_idPersona, false);

							if(
							    CollectionUtils.isValidCollection(lcpd_datosConsultados)
								    && CollectionUtils.isValidCollection(lcpd_datosCargados)
							)
							{
								Collection<PersonaDireccion> lcpd_dataFinal;
								lcpd_dataFinal = new LinkedList<PersonaDireccion>();

								lcpd_datosCargados.addAll(lcpd_datosConsultados);

								for(PersonaDireccion lpd_direccion1 : lcpd_datosCargados)
								{
									if(lpd_direccion1 != null)
									{
										if(lcpd_dataFinal.isEmpty())
											lcpd_dataFinal.add(lpd_direccion1);
										else
										{
											String  ls_direccion1;
											boolean lb_repetido;

											ls_direccion1     = StringUtils.getStringNotNull(
												    lpd_direccion1.getDireccion()
												);
											lb_repetido       = false;

											for(PersonaDireccion lpd_direccion2 : lcpd_dataFinal)
											{
												if(lpd_direccion2 != null)
												{
													String ls_direccion2;

													ls_direccion2 = StringUtils.getStringNotNull(
														    lpd_direccion2.getDireccion()
														);

													if(ls_direccion1.equalsIgnoreCase(ls_direccion2))
													{
														lb_repetido = true;

														break;
													}
												}
											}

											if(!lb_repetido)
												lcpd_dataFinal.add(lpd_direccion1);
										}
									}
								}

								ar_datos.setListadoDireccionCorrespondencia(lcpd_dataFinal);
							}
							else if(CollectionUtils.isValidCollection(lcpd_datosConsultados))
								ar_datos.setListadoDireccionCorrespondencia(lcpd_datosConsultados);
							else if(CollectionUtils.isValidCollection(lcpd_datosCargados))
								ar_datos.setListadoDireccionCorrespondencia(lcpd_datosCargados);
						}

						{
							Collection<PersonaCorreoElectronico> lcpce_datosCargados;
							Collection<PersonaCorreoElectronico> lcpce_datosConsultados;

							lcpce_datosCargados        = ar_datos.getListadoCorreoElectronico();
							lcpce_datosConsultados     = lpced_DAO.findByIdPersona(ls_idPersona);

							if(
							    CollectionUtils.isValidCollection(lcpce_datosConsultados)
								    && CollectionUtils.isValidCollection(lcpce_datosCargados)
							)
							{
								Collection<PersonaCorreoElectronico> lcpd_final;
								lcpd_final = new ArrayList<PersonaCorreoElectronico>();

								lcpce_datosCargados.addAll(lcpce_datosConsultados);

								for(PersonaCorreoElectronico lpce_correo1 : lcpce_datosCargados)
								{
									if(lpce_correo1 != null)
									{
										if(lcpd_final.isEmpty())
											lcpd_final.add(lpce_correo1);
										else
										{
											String  ls_correo1;
											boolean lb_repetido;

											ls_correo1      = StringUtils.getStringNotNull(
												    lpce_correo1.getCorreoElectronico()
												);
											lb_repetido     = false;

											for(PersonaCorreoElectronico lpce_correo2 : lcpd_final)
											{
												if(lpce_correo2 != null)
												{
													String ls_correo2;

													ls_correo2 = StringUtils.getStringNotNull(
														    lpce_correo2.getCorreoElectronico()
														);

													if(ls_correo1.equalsIgnoreCase(ls_correo2))
													{
														lb_repetido = true;

														break;
													}
												}
											}

											if(!lb_repetido)
												lcpd_final.add(lpce_correo1);
										}
									}
								}

								ar_datos.setListadoCorreoElectronico(lcpd_final);
							}
							else if(CollectionUtils.isValidCollection(lcpce_datosConsultados))
								ar_datos.setListadoCorreoElectronico(lcpce_datosConsultados);
							else if(CollectionUtils.isValidCollection(lcpce_datosCargados))
								ar_datos.setListadoCorreoElectronico(lcpce_datosCargados);
						}
					}
				}
			}
		}

		{
			Collection<PersonaTelefono>          lpt_fijo;
			Collection<PersonaTelefono>          lpt_movil;
			Collection<PersonaDireccion>         lpd_direccionResidencia;
			Collection<PersonaDireccion>         lpd_direccionCorrespondencia;
			Collection<PersonaCorreoElectronico> lpce_correo;

			lpt_fijo                         = ar_datos.getListadoTelefonoFijo();
			lpt_movil                        = ar_datos.getListadoTelefonoMovil();
			lpd_direccionResidencia          = ar_datos.getListadoDireccionResidencia();
			lpd_direccionCorrespondencia     = ar_datos.getListadoDireccionCorrespondencia();
			lpce_correo                      = ar_datos.getListadoCorreoElectronico();

			if(lpt_fijo != null)
			{
				Collection<PersonaTelefono> lpd_tmp;
				String                      ls_dirtmp;

				lpd_tmp       = new ArrayList<PersonaTelefono>();
				ls_dirtmp     = "";

				for(PersonaTelefono lpt_iterador : lpt_fijo)
				{
					if(!ls_dirtmp.equalsIgnoreCase(lpt_iterador.getTelefono()))
					{
						lpd_tmp.add(lpt_iterador);
						ls_dirtmp = lpt_iterador.getTelefono();
					}
				}

				ar_datos.setListadoTelefonoFijo(lpd_tmp);
			}

			if(lpt_movil != null)
			{
				Collection<PersonaTelefono> lpd_tmp;
				String                      ls_dirtmp;

				lpd_tmp       = new ArrayList<PersonaTelefono>();
				ls_dirtmp     = "";

				for(PersonaTelefono lpt_iterador : lpt_movil)
				{
					if(!ls_dirtmp.equalsIgnoreCase(lpt_iterador.getTelefono()))
					{
						lpd_tmp.add(lpt_iterador);
						ls_dirtmp = lpt_iterador.getTelefono();
					}
				}

				ar_datos.setListadoTelefonoMovil(lpd_tmp);
			}

			if(lpd_direccionResidencia != null)
			{
				Collection<PersonaDireccion> lpd_tmp;

				lpd_tmp = new ArrayList<PersonaDireccion>();

				for(PersonaDireccion lpd_iterador : lpd_direccionResidencia)
				{
					if(lpd_iterador != null)
						lpd_tmp.add(lpd_iterador);
				}

				ar_datos.setListadoDireccionResidencia(lpd_tmp);
			}

			if(lpd_direccionCorrespondencia != null)
			{
				Collection<PersonaDireccion> lpd_tmp;

				lpd_tmp = new ArrayList<PersonaDireccion>();

				for(PersonaDireccion lpd_iterador : lpd_direccionCorrespondencia)
				{
					if(lpd_iterador != null)
						lpd_tmp.add(lpd_iterador);
				}

				ar_datos.setListadoDireccionCorrespondencia(lpd_tmp);
			}

			if(lpce_correo != null)
			{
				Collection<PersonaCorreoElectronico> lpd_tmp;
				String                               ls_dirtmp;

				lpd_tmp       = new ArrayList<PersonaCorreoElectronico>();
				ls_dirtmp     = "";

				for(PersonaCorreoElectronico lpce_iterador : lpce_correo)
				{
					if(!ls_dirtmp.equalsIgnoreCase(lpce_iterador.getCorreoElectronico()))
					{
						lpd_tmp.add(lpce_iterador);
						ls_dirtmp = lpce_iterador.getCorreoElectronico();
					}
				}

				ar_datos.setListadoCorreoElectronico(lpd_tmp);
			}
		}

		return ar_datos;
	}

	/**
	 * Retorna el valor del objeto de Map.
	 *
	 * @param ldm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized Map<String, String> findCodigosTurnoCertificadoCorrecciones(DAOManager ldm_manager)
	    throws B2BException
	{
		Constantes          lc_constante;
		ConstantesDAO       lc_DAO;
		Map<String, String> lmss_datos;

		lc_constante     = new Constantes();
		lc_DAO           = DaoCreator.getConstantesDAO(ldm_manager);
		lmss_datos       = new HashMap<String, String>();

		lc_constante.setIdConstante(ConstanteCommon.CODIGOS_TURNO_CERTIFICADO_CORRECCIONES);

		lc_constante = lc_DAO.findById(lc_constante);

		if(lc_constante != null)
		{
			String ls_caracter;

			ls_caracter = lc_constante.getCaracter();

			if(StringUtils.isValidString(ls_caracter))
			{
				int      li_tamano;
				String[] lsa_codigos;

				lsa_codigos     = ls_caracter.split(",");
				li_tamano       = lsa_codigos.length;

				if(li_tamano > 0)
				{
					for(int li_count = 0; li_count < li_tamano; li_count++)
						lmss_datos.put(lsa_codigos[li_count], StringUtils.getString(li_count));
				}
			}
		}

		if(!CollectionUtils.isValidCollection(lmss_datos))
			lmss_datos = null;

		return lmss_datos;
	}

	/**
	 * Retorna el valor del objeto de RegistroAnotacionProhibicion.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto Solicitud
	 * @param arap_registro correspondiente al valor de RegistroAnotacionProhibicion
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de RegistroAnotacionProhibicion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroAnotacionProhibicion
	 */
	private synchronized RegistroAnotacionProhibicion findRegistroAnotacionProhibicionByCirculoMatricula(
	    Solicitud as_s, RegistroAnotacionProhibicion arap_registro, String as_userId, String as_remoteIp,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		if(arap_registro != null)
		{
			Solicitud ls_s;

			ls_s = DaoCreator.getSolicitudDAO(adm_manager).findById(as_s);

			if(ls_s != null)
			{
				Date ld_fechaSolicitud;
				Date ld_fechaVencimiento;

				ld_fechaSolicitud       = ls_s.getFechaCreacion();
				ld_fechaVencimiento     = arap_registro.getFechaVencimiento();

				if((ld_fechaSolicitud != null) && (ld_fechaVencimiento != null))
				{
					Object[] aoa_messageArgs;
					String   ls_errorKey;
					String   ls_tipoAdquisicion;

					aoa_messageArgs        = new String[3];
					ls_errorKey            = null;
					ls_tipoAdquisicion     = arap_registro.getTipoAdquisicion();

					if(ld_fechaVencimiento.before(ld_fechaSolicitud))
						ls_errorKey = MessagesKeys.MATRICULA_PROHIBICION_VENCIDA;
					else if(ld_fechaVencimiento.after(ld_fechaSolicitud))
						ls_errorKey = MessagesKeys.MATRICULA_PROHIBICION_VIGENTE;

					aoa_messageArgs[0]     = arap_registro.getIdCirculo() + "-" + arap_registro.getIdMatricula();
					aoa_messageArgs[1]     = StringUtils.getString(
						    ld_fechaVencimiento, FormatoFechaCommon.DIA_MES_ANIO
						);
					aoa_messageArgs[2]     = StringUtils.isValidString(ls_tipoAdquisicion) ? ls_tipoAdquisicion
						                                                                   : IdentificadoresCommon.DATO_INVALIDO;

					{
						TipoAdquisicion lta_ta;

						lta_ta = DaoCreator.getTipoAdquisicionDAO(adm_manager).findById(ls_tipoAdquisicion);

						if(lta_ta != null)
						{
							String ls_nombreTipoAdquisicion;

							ls_nombreTipoAdquisicion = lta_ta.getNombre();

							arap_registro.setTipoAdquisicion(ls_nombreTipoAdquisicion);

							aoa_messageArgs[2] = ls_nombreTipoAdquisicion;
						}
					}

					arap_registro.setMessageArgs(aoa_messageArgs);
					arap_registro.setErrorKey(ls_errorKey);
					arap_registro.setAlerta(true);
				}
			}
		}

		return arap_registro;
	}

	/**
	 * Método encargado de generar los consectuvios respectivos para el recibo de liquidación.
	 *
	 * @param ac_c objeto de tipo constante que contiene el id constante determiniado para consultar
	 * @param adm_dm objeto de tipo dao manager que contiene la conexion hacia la base de datos
	 * @param ab_b variable de tipo boolean que indica el tipo de consecutivo a generar
	 * @param ab_tipoRecibo variable de tipo boolean que indica el tipo de consecutivo a utilizar
	 * @return retorna el consecutivo solicitado dependiendo de la constante a consultar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized String generacionNumerosConsecutivos(
	    Constantes ac_c, DAOManager adm_dm, boolean ab_b, boolean ab_tipoRecibo
	)
	    throws B2BException
	{
		String ls_consecutivo;
		ls_consecutivo = null;

		if((ac_c != null) && (adm_dm != null))
		{
			ConstantesDAO lcd_DAO;
			Constantes    lc_constantes;
			String        ls_usuario;
			String        ls_ip;
			boolean       lb_insert;
			BigInteger    lbi_consecutivo;
			int           li_anio;
			String        ls_idConstante;

			String ls_inicio;
			ls_inicio     = ab_tipoRecibo ? "RL" : "RC";

			li_anio        = Calendar.getInstance().get(Calendar.YEAR);
			ls_usuario     = ac_c.getIdUsuarioCreacion();
			ls_ip          = ac_c.getIpCreacion();
			lcd_DAO        = DaoCreator.getConstantesDAO(adm_dm);

			ls_idConstante = ac_c.getIdConstante() + IdentificadoresCommon.SIMBOLO_GUION_BAJO + li_anio;
			ac_c.setIdConstante(ls_idConstante);

			lc_constantes = lcd_DAO.findById(ac_c);

			if(lc_constantes != null)
			{
				lb_insert     = false;

				lbi_consecutivo = lc_constantes.getEntero();
				lc_constantes.setIdUsuarioModificacion(ls_usuario);
				lc_constantes.setIpModificacion(ls_ip);
			}
			else
			{
				lc_constantes       = new Constantes();
				lb_insert           = true;
				lbi_consecutivo     = null;

				lc_constantes.setIdUsuarioCreacion(ls_usuario);
				lc_constantes.setIpCreacion(ls_ip);
				lc_constantes.setIdConstante(ls_idConstante);
			}

			lc_constantes.setCaracter(ls_inicio);

			if(lbi_consecutivo == null)
				lbi_consecutivo = new BigInteger("0");

			lbi_consecutivo = new BigInteger(StringUtils.getString(lbi_consecutivo.add(new BigInteger("1"))));

			lc_constantes.setEntero(lbi_consecutivo);

			lcd_DAO.insertOrUpdate(lc_constantes, lb_insert);

			if(ab_b)
				ls_consecutivo = ls_inicio + li_anio + String.format("%08d", lbi_consecutivo);
			else
				ls_consecutivo = li_anio + StringUtils.getStringNotNull(ac_c.getIdProceso())
					+ String.format("%08d", lbi_consecutivo);
		}

		return ls_consecutivo;
	}

	/**
	 * Construye la plantilla entidad corrección si procede.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param ads_documentoSalida de ads documento salida
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idSolicitud id de la solicitud del tramite
	 * @param as_idTurno id del turno del tramite
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp de as remote ip
	 * @return Arreglo de bytes contenedor de la imagen de la plantilla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized byte[] generarPlantillaNiegaProrrogaMayorValor(
	    TurnoHistoria ath_turnoHistoria, DocumentosSalida ads_documentoSalida, DAOManager adm_manager,
	    String as_idSolicitud, String as_idTurno, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[] lba_return;
		String ls_plantilla;

		lba_return       = null;
		ls_plantilla     = obtenerPlantillaDeConstante(
			    adm_manager, ConstanteCommon.PLANTILLA_NIEGA_PRORROGA_MAYOR_VALOR
			);

		if(StringUtils.isValidString(ls_plantilla))
		{
			Map<String, String> lmss_datos;
			String              ls_tag;
			String              ls_idCirculo;

			lmss_datos       = null;
			ls_plantilla     = escribirTagFechaLarga(ls_plantilla);
			ls_idCirculo     = null;

			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(as_idSolicitud);

				if(ls_solicitud != null)
				{
					MunicipioDAO lmd_municipioDAO;

					lmd_municipioDAO     = DaoCreator.getMunicipioDAO(adm_manager);

					ls_tag = "<TAG_MUN_OFI_ORIGEN>";

					if(ls_plantilla.contains(ls_tag))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(as_idTurno);

						if(lt_turno != null)
						{
							String ls_idCirculo2;
							ls_idCirculo2 = lt_turno.getIdCirculo();

							if(StringUtils.isValidString(ls_idCirculo2))
							{
								Municipio lm_municipio;

								lm_municipio     = new Municipio();

								lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findByIdCirculo(ls_idCirculo2);

								if(lm_municipio != null)
								{
									String ls_munOficinaOrigen;
									ls_munOficinaOrigen = lm_municipio.getNombre();

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_munOficinaOrigen))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
								}
							}
						}
					}

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
										ls_plantilla = saltoDeCarroDespues(
											    ls_plantilla, ls_tag,
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

										ls_plantilla     = saltoDeCarroDespues(
											    ls_plantilla, ls_tag,
											    StringUtils.getStringNotNull(lpd_direccion.getDireccion())
											);

										ls_tag = "<TAG_DEPAR_MUNICIPIO_INTERESADO>";

										if(ls_plantilla.contains(ls_tag))
										{
											Departamento ld_departamento;
											Municipio    lm_municipio;

											ld_departamento     = new Departamento();
											lm_municipio        = new Municipio();

											ld_departamento.setIdPais(lpd_direccion.getIdPais());
											ld_departamento.setIdDepartamento(lpd_direccion.getIdDepartamento());
											lm_municipio.setIdPais(lpd_direccion.getIdPais());
											lm_municipio.setIdDepartamento(lpd_direccion.getIdDepartamento());
											lm_municipio.setIdMunicipio(lpd_direccion.getIdMunicipio());

											lm_municipio     = DaoCreator.getMunicipioDAO(adm_manager)
													                         .findById(lm_municipio);

											ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
													                        .findById(ld_departamento);
											ld_departamento.setNombreDepartamento(ld_departamento.getNombre());

											if(
											    (StringUtils.isValidString(ld_departamento.getNombreDepartamento()))
												    && (StringUtils.isValidString(lm_municipio.getNombre()))
											)
												ls_plantilla = escribirDepartamentoMunicipioInteresadoUnTag(
													    ls_plantilla, lm_municipio.getNombre(), ls_tag,
													    ld_departamento.getNombreDepartamento()
													);
										}

										ls_tag = "<TAG_DEPAR_INTERESADO>";

										if(ls_plantilla.contains(ls_tag))
										{
											Departamento ld_departamento;
											ld_departamento = new Departamento();
											ld_departamento.setIdPais(lpd_direccion.getIdPais());
											ld_departamento.setIdDepartamento(lpd_direccion.getIdDepartamento());
											ld_departamento     = DaoCreator.getDepartamentoDAO(adm_manager)
													                            .findById(ls_idPais, ls_idDepartamento);
											ld_departamento     = DaoCreator.getDepartamentoDAO(adm_manager)
													                            .findById(ld_departamento);
											ld_departamento.setNombreDepartamento(ld_departamento.getNombre());

											if(ld_departamento != null)
												ls_plantilla = ls_plantilla.replace(
													    ls_tag,
													    StringUtils.getStringNotNull(
													        ld_departamento.getNombreDepartamento()
													    )
													);
										}

										ls_tag = "<TAG_MUNICIPIO_INTERESADO>";

										if(ls_plantilla.contains(ls_tag))
										{
											Municipio lm_municipio;
											lm_municipio = new Municipio();
											lm_municipio.setIdPais(lpd_direccion.getIdPais());
											lm_municipio.setIdDepartamento(lpd_direccion.getIdDepartamento());
											lm_municipio.setIdMunicipio(lpd_direccion.getIdMunicipio());
											lm_municipio     = lmd_municipioDAO.findById(
												    ls_idPais, ls_idDepartamento, ls_idMunicipio
												);
											lm_municipio     = DaoCreator.getMunicipioDAO(adm_manager)
													                         .findById(lm_municipio);
											ls_plantilla     = ls_plantilla.replace(
												    ls_tag, StringUtils.getStringNotNull(lm_municipio.getNombre())
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
				String ls_consecutivoOficio;
				String ls_referenciaSalida;
				Date   ld_fechaOficio;

				ls_consecutivoOficio     = null;
				ls_referenciaSalida      = null;
				ld_fechaOficio           = null;

				{
					ls_tag = "<TAG_OFICIO>";

					if(ls_plantilla.contains(ls_tag))
					{
						NumeracionOficiosCirculo lnoc_numeracionOficios;
						lnoc_numeracionOficios = findNumeracionOficiosCirculo(
							    ath_turnoHistoria, adm_manager, as_userId, as_remoteIp, true
							);

						if(lnoc_numeracionOficios != null)
						{
							ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

							if(StringUtils.isValidString(ls_consecutivoOficio))
							{
								ls_plantilla     = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);

								ld_fechaOficio = new Date();
								ads_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);

								ads_documentoSalida.setFechaOficio(ld_fechaOficio);
							}
						}
					}
				}

				{
					ls_tag = "<TAG_REFERENCIA_SALIDA>";

					if(ls_plantilla.contains(ls_tag))
					{
						ls_referenciaSalida     = generarIdCorrespondencia(ath_turnoHistoria, adm_manager, false);

						ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);

						ads_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
					}
				}
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

					ls_tag = "<TAG_FECHA_INICIAL_ETAPA_282>";

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = ls_plantilla.replace(
							    ls_tag, StringUtils.getStringNotNull(StringUtils.getString(lt_turno.getFechaInicio()))
							);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);
			}

			ls_tag = "<TAG_USUARIO>";

			if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(as_userId))
				ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

			{
				Constantes ls_constanteFirma;

				ls_constanteFirma     = DaoCreator.getConstantesDAO(adm_manager)
						                              .findByIdWithImage(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

				ls_plantilla = getFirmaMasivaBusiness()
						               .reemplazarUsuarioFirmaCargo(
						    ls_plantilla, ls_constanteFirma, "<TAG_USUARIO_FIRMA_SUSPENSION>",
						    "<TAG_CARGO_FIRMA_SUSPENSION>"
						);
			}

			lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
			ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
			lba_return       = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

			if(lba_return == null)
				throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);
		}

		return lba_return;
	}

	/**
	 * Guarda la fecha de ejecutoria en el documento asociado a una solicitud.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idSolicitud id de la solicitud
	 * @param ad_fechaEjecutoria nuevo valor a dar a la fecha de ejecutoria del documento
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized void guardarFechaEjecutoriaActo(
	    DAOManager adm_manager, String as_idSolicitud, Date ad_fechaEjecutoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idSolicitud))
		{
			Solicitud ls_solicitud;

			ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(as_idSolicitud);

			if(ls_solicitud != null)
			{
				DocumentoDAO ldd_documentoDAO;
				Documento    ld_documento;

				ldd_documentoDAO     = DaoCreator.getDocumentoDAO(adm_manager);
				ld_documento         = ldd_documentoDAO.findByIdDocumentoVersion(
					    ls_solicitud.getIdDocumento(), ls_solicitud.getVersionDocumento()
					);

				if(ld_documento != null)
				{
					ld_documento.setFechaEjecutoria(ad_fechaEjecutoria);
					ld_documento.setIdUsuarioModificacion(as_userId);
					ld_documento.setIpModificacion(as_remoteIp);

					ldd_documentoDAO.insertOrUpdate(ld_documento, false);
				}
			}
		}
	}

	/**
	 * Metodo que se encarga de guardar los tipos documentales enviados como argumento.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite realizar la conexión a la base de datos.
	 * @param aci_completitudDocumental Colección de imagenes que contienen los tipos documentales que deben ser salvadas en las tablas de negocio.
	 * @param aso_solicitud Objeto que contiene los datos de la solicitud creada.
	 * @param ls_idTurnoAnt correspondiente al valor de ls id turno ant
	 * @param ls_idSolicitudAnt de ls id solicitud ant
	 * @param as_constanteApoderado correspondiente al valor de as constante apoderado
	 * @param as_constanteTercero correspondiente al valor de as constante tercero
	 * @param as_localIp Ip publica del equipo donde se ejecuta el proceso.
	 * @param as_remoteIp Ip del equipo donde se ejecuta el proceso.
	 * @param as_userId Usuario que realiza las acciones en la aplicación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	private synchronized void guardarTiposDocumentales(
	    DAOManager adm_manager, Collection<AccCompletitudDocumental> aci_completitudDocumental, Solicitud aso_solicitud,
	    String ls_idTurnoAnt, String ls_idSolicitudAnt, String as_constanteApoderado, String as_constanteTercero,
	    String as_localIp, String as_remoteIp, String as_userId
	)
	    throws B2BException
	{
		try
		{
			if(CollectionUtils.isValidCollection(aci_completitudDocumental) && (aso_solicitud != null))
			{
				AccCompletitudDocumentalDAO lacdd_DAO;
				String                      ls_idSolicitud;

				lacdd_DAO          = DaoCreator.getAccCompletitudDocumentalDAO(adm_manager);
				ls_idSolicitud     = aso_solicitud.getIdSolicitud();

				{
					String ls_idCalidadSolicitante;

					ls_idCalidadSolicitante = aso_solicitud.getIdCalidadSolicitante();

					if(
					    StringUtils.isValidString(ls_idCalidadSolicitante)
						    && ((ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.APODERADO)
						    && StringUtils.isValidString(as_constanteApoderado))
						    || (ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.TERCERO)
						    && StringUtils.isValidString(as_constanteApoderado)))
					)
					{
						Collection<com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental> lctd_tipoDocumental;

						lctd_tipoDocumental = actualizarTiposDocumentales(
							    adm_manager, aso_solicitud, null, as_constanteApoderado, as_constanteTercero
							);

						if(
						    CollectionUtils.isValidCollection(aci_completitudDocumental)
							    && CollectionUtils.isValidCollection(lctd_tipoDocumental)
						)
						{
							Map<String, AccCompletitudDocumental> lmcd_completitudDocumental;

							lmcd_completitudDocumental = new HashMap<String, AccCompletitudDocumental>();

							for(AccCompletitudDocumental lacd_iterador : aci_completitudDocumental)
							{
								if(lacd_iterador != null)
									lmcd_completitudDocumental.put(lacd_iterador.getIdTipoDocumental(), lacd_iterador);
							}

							for(com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental ltd_iterador : lctd_tipoDocumental)
							{
								if(ltd_iterador != null)
								{
									String                   ls_idTipoDocumental;
									AccCompletitudDocumental lacd_tmp;

									ls_idTipoDocumental     = ltd_iterador.getIdTipoDocumental();
									lacd_tmp                = lmcd_completitudDocumental.get(ls_idTipoDocumental);

									if(lacd_tmp == null)
									{
										Object[] loa_object;

										loa_object     = new String[2];

										loa_object[0]     = ltd_iterador.getNombre();
										loa_object[1]     = ltd_iterador.getIdTipoDocumental();

										throw new B2BException(ErrorKeys.TIPO_DOCUMENTAL_OBLIGATORIO, loa_object);
									}
								}
							}
						}
					}
				}

				{
					AccCompletitudDocumental lacd_completitudDocumental;

					lacd_completitudDocumental = new AccCompletitudDocumental();

					lacd_completitudDocumental.setIdSolicitud(ls_idSolicitud);

					lacdd_DAO.deleteByIdSolicitud(lacd_completitudDocumental);
				}

				for(AccCompletitudDocumental lacd_iterador : aci_completitudDocumental)
				{
					if(lacd_iterador != null)
					{
						{
							String ls_tipoDocumental;

							ls_tipoDocumental = lacd_iterador.getIdTipoDocumental();

							if(!StringUtils.isValidString(ls_tipoDocumental))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOCUMENTAL);

							String ls_medioRecepcion;
							ls_medioRecepcion = lacd_iterador.getMedioRecepcion();

							if(!StringUtils.isValidString(ls_medioRecepcion) && lacd_iterador.isSeleccionado())
								throw new B2BException(ErrorKeys.ERROR_SIN_MEDIO_RECEPCION);
						}

						lacd_iterador.setIdSolicitud(ls_idSolicitud);
						lacd_iterador.setIdSolicitudPrincipal(ls_idSolicitudAnt);
						lacd_iterador.setIdTurno(ls_idTurnoAnt);
						lacd_iterador.setObligatorioTipoDocumental(
						    lacd_iterador.isSeleccionado() ? EstadoCommon.S : EstadoCommon.N
						);
						lacd_iterador.setIdUsuarioCreacion(as_userId);
						lacd_iterador.setIpCreacion(as_remoteIp);
						lacd_iterador.setDigitalizado(EstadoCommon.N);

						lacdd_DAO.insert(lacd_iterador);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarTiposDocumentales", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param la_acto correspondiente al valor del tipo de objeto Acto
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @param ab_validExistencia correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	private synchronized String insertarActoReproduccionConstacia(
	    Acto la_acto, DAOManager adm_manager, boolean ab_validExistencia, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		String ls_idActo;
		ls_idActo = null;

		try
		{
			if(la_acto != null)
			{
				TipoActoDAO lota_DAO;
				String      ls_idTipoActo;
				int         li_version;

				ActoDAO lad_DAO;

				ls_idTipoActo     = TipoActoCommon.REPRODUCCION_CONSTANCIA_DE_INSCRIPCION;
				lota_DAO          = DaoCreator.getTipoActoDAO(adm_manager);
				li_version        = lota_DAO.findMaxVersion(ls_idTipoActo);
				lad_DAO           = DaoCreator.getActoDAO(adm_manager);

				la_acto.setVersion(li_version);
				la_acto.setIdTipoActo(ls_idTipoActo);
				la_acto.setOrganismoInternacional(EstadoCommon.NO);
				la_acto.setHijuelaPasivo(EstadoCommon.NO);
				la_acto.setGarantiaHipoteca(EstadoCommon.NO);
				la_acto.setOrganismoInternacional(EstadoCommon.NO);
				la_acto.setIdTipoTarifaRegistral(EstadoCommon.NORMAL);
				la_acto.setIpCreacion(as_remoteIp);
				la_acto.setIdUsuarioCreacion(as_userId);

				if(ab_validExistencia)
				{
					Acto la_actoExist;

					la_actoExist = lad_DAO.findByIdSolicitudTipoActo(la_acto);

					if(la_actoExist == null)
						ls_idActo = lad_DAO.insert(la_acto);
				}

				else
					ls_idActo = lad_DAO.insert(la_acto);
			}
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}

		return ls_idActo;
	}

	/**
	 * Método encargado de insertar un DatosAntSistema en la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 *
	 * @param ldas_datosAntSistema Objeto que contiene la información del registro que se va a insertar.
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_ipRemota correspondiente al valor del tipo de objeto String
	 * @param adm_manager DAOManager que controla la conexión a la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized void insertarDatosAntSistemaGrabacionMatricula(
	    DatosAntSistema ldas_datosAntSistema, String as_userId, String as_ipRemota, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(ldas_datosAntSistema != null)
		{
			DatosAntSistemaDAO ldas_DAO;

			ldas_DAO = DaoCreator.getDatosAntSistemaDAO(adm_manager);

			ldas_datosAntSistema.setAdquisicionPredio(IdentificadoresCommon.NO_APLICA);
			ldas_datosAntSistema.setIdCirculo(ldas_datosAntSistema.getIdCirculoGrabacion());
			ldas_datosAntSistema.setIdUsuarioCreacion(as_userId);
			ldas_datosAntSistema.setIpCreacion(as_ipRemota);

			ldas_DAO.insertUpdate(ldas_datosAntSistema, true);
		}
	}

	/**
	 * Metodo encargado de llenar los datos de la persona.
	 *
	 * @param ap_persona correspondiente al valor de Persona
	 * @param ar_datos Argumento de tipo <code>Registro</code> que trasnporta los datos de la persona a otras capas.
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que realiza la conexión a la base de datos.
	 * @return devuelve el valor de Registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	private synchronized Registro llenarDatosPersona(Persona ap_persona, Registro ar_datos, DAOManager adm_manager)
	    throws B2BException
	{
		return llenarDatosPersona(ap_persona, ar_datos, adm_manager, false);
	}

	/**
	 * Metodo encargado de llenar los datos de la persona.
	 *
	 * @param ap_persona correspondiente al valor de ap persona
	 * @param ar_datos Argumento de tipo <code>Registro</code> que trasnporta los datos de la persona a otras capas.
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que realiza la conexión a la base de datos.
	 * @param ab_origenBachue correspondiente al valor de origen bachue
	 * @return devuelve el valor de Registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	private synchronized Registro llenarDatosPersona(
	    Persona ap_persona, Registro ar_datos, DAOManager adm_manager, boolean ab_origenBachue
	)
	    throws B2BException
	{
		try
		{
			Collection<Persona> lcp_personas;

			lcp_personas = DaoCreator.getPersonaDAO(adm_manager).findByDocument(ap_persona, ab_origenBachue);

			if(CollectionUtils.isValidCollection(lcp_personas))
				ar_datos = consultarInfoPersonas(lcp_personas, adm_manager);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("llenarDatosPersona", lb2be_e);

			throw lb2be_e;
		}

		return ar_datos;
	}

	/**
	 * Resuleve los tags de información y contacto de la snr en el recibo de liquidación.
	 *
	 * @param as_plantilla Objeto contenedor de la información de la plantilla
	 * @param as_idSolicitud correspondiente al valor de id solicitud
	 * @param adm_manager correspondiente al valor de DAOManager
	 * @return Plantilla resultante
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	private synchronized String resolverTagsDatosPago(
	    String as_plantilla, String as_idSolicitud, DAOManager adm_manager
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			if(StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_idSolicitud))
			{
				String  ls_tagFormaPago;
				String  ls_tagBanco;
				String  ls_tagFechaPago;
				String  ls_tagReferenciaPago;
				boolean lb_cleanTags;

				ls_tagFormaPago          = "<TAG_FORMA_PAGO>";
				ls_tagBanco              = "<TAG_BANCO>";
				ls_tagFechaPago          = "<TAG_FECHA_PAGO>";
				ls_tagReferenciaPago     = "<TAG_REFERENCIA_PAGO>";
				lb_cleanTags             = false;

				if(
				    as_plantilla.contains(ls_tagFormaPago) && as_plantilla.contains(ls_tagBanco)
					    && as_plantilla.contains(ls_tagFechaPago) && as_plantilla.contains(ls_tagReferenciaPago)
				)
				{
					RegistroPago lrp_registroPago;

					lrp_registroPago = DaoCreator.getRegistroPagoDAO(adm_manager).findByIdSolicitud(as_idSolicitud);

					if(lrp_registroPago != null)
					{
						EntidadRecaudadoraDAO ler_DAO;
						TipoRecaudoDAO        ltr_DAO;

						ler_DAO     = DaoCreator.getEntidadRecaudadoraDAO(ldm_manager);
						ltr_DAO     = DaoCreator.getTipoRecaudoDAO(ldm_manager);

						{
							EntidadRecaudadora ler_entidadRecaudadora;

							ler_entidadRecaudadora = ler_DAO.findById(lrp_registroPago.getIdEntidadRecaudo());

							if(ler_entidadRecaudadora != null)
								as_plantilla = as_plantilla.replace(
									    ls_tagBanco,
									    StringUtils.getStringNotNull(
									        ler_entidadRecaudadora.getNombreEntidadRecaudadora()
									    )
									);
						}

						{
							TipoRecaudo ltr_tiporecaudo;

							ltr_tiporecaudo = ltr_DAO.findById(lrp_registroPago.getIdTipoRecaudo());

							if(ltr_tiporecaudo != null)
								as_plantilla = as_plantilla.replace(
									    ls_tagFormaPago,
									    StringUtils.getStringNotNull(ltr_tiporecaudo.getNombreTipoRecaudo())
									);
						}

						{
							String ls_fechaBancaria;

							ls_fechaBancaria     = StringUtils.getString(
								    lrp_registroPago.getFechaBancaria(), "dd/MM/yyyy"
								);

							as_plantilla = as_plantilla.replace(
								    ls_tagFechaPago, StringUtils.getStringNotNull(ls_fechaBancaria)
								);
						}

						as_plantilla = as_plantilla.replace(
							    ls_tagReferenciaPago,
							    StringUtils.getStringNotNull(lrp_registroPago.getNumeroReferencia())
							);
					}
					else
						lb_cleanTags = true;

					if(lb_cleanTags)
					{
						as_plantilla     = as_plantilla.replace(ls_tagFormaPago, " ");

						as_plantilla     = as_plantilla.replace(ls_tagBanco, " ");

						as_plantilla     = as_plantilla.replace(ls_tagFechaPago, " ");

						as_plantilla = as_plantilla.replace(ls_tagReferenciaPago, " ");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("resolverTagsDatosPago", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return as_plantilla;
	}

	/**
	 * Método encargado de salvar los datos para solicitud de copias desde bng documentos.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param acdrc_parametros objeto necesario para realizar la operación de solicitud de copias.
	 * @param as_idSolicitud objeto que contiene el id_solicitud para solicitud de copias.
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException error si ocurre una excepción
	 */
	private synchronized void salvarDatosCopiasBng(
	    DAOManager adm_manager, Collection<DataReproduccionConstancia> acdrc_parametros, String as_idSolicitud,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			if(CollectionUtils.isValidCollection(acdrc_parametros))
			{
				SolicitudDAO lsd_DAO;

				lsd_DAO = DaoCreator.getSolicitudDAO(adm_manager);

				for(DataReproduccionConstancia ldrc_iterador : acdrc_parametros)
				{
					if(ldrc_iterador != null)
					{
						DocumentoConstancia ldc_documentoConstancia;

						ldc_documentoConstancia = ldrc_iterador.getDocumento();

						if((ldc_documentoConstancia != null) && ldc_documentoConstancia.isSeleccione())
						{
							Solicitud ls_solicitud;

							ls_solicitud = lsd_DAO.findById(as_idSolicitud);

							if(ls_solicitud != null)
							{
								ls_solicitud.setNumeroCopias(ldc_documentoConstancia.getNumeroCopias());

								salvarDatosCopiasSolicitud(adm_manager, ls_solicitud, as_userId, as_remoteIp);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDatosCopiasBng", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de salvar los datos de copias para una solicitud.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param as_solicitud objeto necesario para realizar la operación de copias para una solicitud.
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException error si ocurre una excepción
	 */
	private synchronized void salvarDatosCopiasSolicitud(
	    DAOManager adm_manager, Solicitud as_solicitud, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			if(as_solicitud != null)
			{
				SolicitudDAO lsd_DAO;
				Solicitud    ls_tmp;

				lsd_DAO     = DaoCreator.getSolicitudDAO(adm_manager);

				ls_tmp = lsd_DAO.findById(as_solicitud);

				if(ls_tmp != null)
				{
					{
						Long ll_numeroCopias;

						ll_numeroCopias = as_solicitud.getNumeroCopias();

						if(!NumericUtils.isValidLong(ll_numeroCopias))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_UN_NUMERO_DE_COPIAS_MAYOR_CERO_SOLICITUD);
					}

					as_solicitud.setIdUsuarioModificacion(as_userId);
					as_solicitud.setIpModificacion(as_remoteIp);

					lsd_DAO.updateNumeroCopias(as_solicitud);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDatosCopiasSolicitud", lb2be_e);

			throw lb2be_e;
		}
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
	private synchronized Documento salvarDocumento(
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

			lb_insert        = false;
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

						{
							String ls_idOficinaOrigen;

							ls_idOficinaOrigen = ad_documento.getIdOficinaOrigen();

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
					lso_solicitud.setIdTestamento(aso_solicitud.getIdTestamento());

					lsd_DAO.update(lso_solicitud);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);
		}

		return ld_documento;
	}

	/**
	 * Método para acutalizar en el registro en la tabla SDB_ACC_DEVOLUCION_DINERO.
	 *
	 * @param ar_registro Objeto que contiene los datos del interesado que se desea insertar.
	 * @param as_idSolicitud String de id Solicitud
	 * @param as_idPersona String de id Persona
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param adm_manager de adm manager
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarProcesoDevolucionesDinero(
	    Registro ar_registro, String as_idSolicitud, String as_idPersona, String as_userId, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(
		    (ar_registro != null) && StringUtils.isValidString(as_idSolicitud)
			    && StringUtils.isValidString(as_idPersona)
		)
		{
			DevolucionDineroDAO ldd_DAO;
			DevolucionDinero    ldd_devDinero;

			ldd_DAO           = DaoCreator.getDevolucionDineroDAO(adm_manager);
			ldd_devDinero     = ldd_DAO.findByIdSolicitud(as_idSolicitud);

			if(ldd_devDinero != null)
			{
				DevolucionDinero ldd_devDineroFromRegistro;

				ldd_devDineroFromRegistro = ar_registro.getDevolucionDinero();

				if(ldd_devDineroFromRegistro != null)
				{
					String ls_intervieneTramite;

					ls_intervieneTramite = ldd_devDineroFromRegistro.getIntervieneTramite();

					if(StringUtils.isValidString(ls_intervieneTramite))
					{
						ldd_devDinero.setIntervieneTramite(ls_intervieneTramite);

						if(ls_intervieneTramite.equalsIgnoreCase(EstadoCommon.S))
							ldd_devDinero.setIdPersonaInterviniente(as_idPersona);
						else if(ls_intervieneTramite.equalsIgnoreCase(EstadoCommon.N))
							ldd_devDinero.setIdPersonaInterviniente(
							    ldd_devDineroFromRegistro.getIdPersonaInterviniente()
							);

						{
							String ls_titularCuenta;

							ls_titularCuenta = ldd_devDineroFromRegistro.getTitularCuenta();

							if(StringUtils.isValidString(ls_titularCuenta))
							{
								if(ls_titularCuenta.equalsIgnoreCase(EstadoCommon.S))
									ldd_devDinero.setIdPersonaTitularCuenta(as_idPersona);

								ldd_devDinero.setTitularCuenta(ls_titularCuenta);
							}
						}

						ldd_devDinero.setIdUsuarioModificacion(as_userId);
						ldd_devDinero.setIpModificacion(ar_registro.getIpCreacion());

						ldd_DAO.update(ldd_devDinero);
					}
				}
			}
		}
	}

	/**
	 * Retorna el valor del objeto de Registro.
	 *
	 * @param ar_registro correspondiente al valor del tipo de objeto Registro
	 * @param ab_notificarCorrespondencia correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @param as_ipRemote correspondiente al valor del tipo de objeto String
	 * @param adm_manager Controla la conexión a la base de datos
	 * @return devuelve el valor de Registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 * @see Registro
	 */
	private synchronized Registro terminarProcesoGrabacionMatriculas(
	    Registro ar_registro, boolean ab_notificarCorrespondencia, String as_usuario, String as_ipRemote,
	    DAOManager adm_manager
	)
	    throws B2BException, IOException
	{
		if(ar_registro != null)
		{
			Solicitud ls_solicitud;

			ls_solicitud = ar_registro.getSolicitud();

			if(ls_solicitud != null)
			{
				String ls_nir;

				ls_nir = crearNir(as_usuario, as_ipRemote, adm_manager);

				if(StringUtils.isValidString(ls_nir))
				{
					Solicitud    lso_solicitud;
					SolicitudDAO lsd_DAO;

					lsd_DAO           = DaoCreator.getSolicitudDAO(adm_manager);
					lso_solicitud     = lsd_DAO.findById(ls_solicitud);

					ar_registro.setNirProceso(ls_nir);
					lso_solicitud.setNir(ls_nir);

					if(ab_notificarCorrespondencia)
					{
						lso_solicitud.setIdAutorizacionNotificacion(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA);
						lso_solicitud.setIdAutorizacionComunicacion(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA);
					}

					lso_solicitud.setFechaSolicitud(new Date());

					lso_solicitud.setIdUsuarioModificacion(as_usuario);
					lsd_DAO.insertOrUpdate(lso_solicitud, false);
				}
				else
					throw new B2BException(ErrorKeys.NO_GENERO_NIR);

				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = new TurnoHistoria();

					lth_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());
					lth_turnoHistoria.setIdUsuarioModificacion(as_usuario);
					lth_turnoHistoria.setIpModificacion(as_ipRemote);

					DaoCreator.getProcedimientosDAO(adm_manager).spCreateStage(lth_turnoHistoria);
				}

				{
					byte[] lba_pdf;

					lba_pdf = null;

					ar_registro.setRecepcionDocumentos(false);
					ar_registro.setEsGrabacionMatriculas(true);

					lba_pdf = crearPdfRegistro(ar_registro, as_usuario, adm_manager);

					if(lba_pdf != null)
					{
						Imagenes         li_imagenes;
						DocumentosSalida lds_documentosSalida;
						long             ll_secuencia;

						li_imagenes              = new Imagenes();
						lds_documentosSalida     = new DocumentosSalida();

						li_imagenes.setImagenBlob(lba_pdf);

						li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
						li_imagenes.setIdUsuarioCreacion(as_usuario);
						li_imagenes.setIpCreacion(as_ipRemote);

						ll_secuencia = DaoCreator.getImagenesDAO(adm_manager).insertOrUpdate(li_imagenes, true);

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

						DaoCreator.getDocumentosSalidaDAO(adm_manager).insertOrUpdate(lds_documentosSalida, true);

						ar_registro.setPdf(lba_pdf);
					}
				}
			}
		}

		return ar_registro;
	}

	/**
	 * Tipo vis acto padre.
	 *
	 * @param as_idActoPrincipal the as id acto principal
	 * @param adm_manager the adm manager
	 * @return true, if successful
	 * @throws B2BException the b 2 B exception
	 */
	private synchronized boolean tipoVisActoPadre(String as_idActoPrincipal, DAOManager adm_manager)
	    throws B2BException
	{
		boolean lb_tipoVis;

		lb_tipoVis = false;

		try
		{
			if(StringUtils.isValidString(as_idActoPrincipal))
			{
				Acto     la_acto;
				TipoActo lta_tipoActo;

				la_acto          = DaoCreator.getActoDAO(adm_manager).findById(as_idActoPrincipal);
				lta_tipoActo     = la_acto.getTipoActo();

				if(lta_tipoActo != null)
				{
					String ls_idTipoActo;
					ls_idTipoActo = lta_tipoActo.getIdTipoActo();

					if(StringUtils.isValidString(ls_idTipoActo))
					{
						if(ls_idTipoActo.equalsIgnoreCase(TipoActoCommon.COMPRAVENTA))
							lb_tipoVis = true;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			adm_manager.setRollbackOnly();

			clh_LOGGER.error("tipoVisActoPadre", lb2be_e);

			throw lb2be_e;
		}

		return lb_tipoVis;
	}

	/**
	 * Verifica que la cuantia de un acto sea correcta.
	 *
	 * @param abd_cuantia Objeto contenedor de la cuantia del acto
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized void validarCuantiaActo(BigDecimal abd_cuantia)
	    throws B2BException
	{
		if(abd_cuantia != null)
		{
			if(!NumericUtils.isValidBigDecimal(abd_cuantia))
				throw new B2BException(ErrorKeys.ERROR_CUANTIA_NO_NUMERICO);

			if(abd_cuantia.compareTo(BigDecimal.ZERO) == 0)
				throw new B2BException(ErrorKeys.ERROR_CUANTIA_MAYOR_CERO);
		}
		else
			throw new B2BException(ErrorKeys.ERROR_SIN_CUANTIA_ACTO);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ar_registro correspondiente al valor del tipo de objeto Row
	 * @param ai_fila correspondiente al valor del tipo de objeto int
	 * @param ai_celda correspondiente al valor del tipo de objeto int
	 * @param as_nombreCelda correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized String validarPorcentajeCeldaExcel(
	    Row ar_registro, int ai_fila, int ai_celda, String as_nombreCelda
	)
	    throws B2BException
	{
		String ls_tmp;

		ls_tmp = null;

		if(ar_registro != null)
		{
			Cell lc_tmp;

			lc_tmp = ar_registro.getCell(ai_celda);

			if(
			    (lc_tmp != null)
				    && ((lc_tmp.getCellType() == Cell.CELL_TYPE_NUMERIC)
				    || (lc_tmp.getCellType() == Cell.CELL_TYPE_FORMULA))
			)
			{
				double ld_celda;

				ld_celda = lc_tmp.getNumericCellValue();

				if(NumericUtils.isValidDouble(NumericUtils.getDoubleWrapper(ld_celda)))
				{
					long   iPart = (long)ld_celda;
					double fPart = ld_celda - iPart;

					if((iPart > 100) || (iPart < 0))
						throw new B2BException(
						    "El campo " + as_nombreCelda + " en la fila Nº " + ai_fila
						    + " no corresponde a un porcentaje valido."
						);
					else if(fPart > 0.9999999999)
						throw new B2BException(
						    "El campo " + as_nombreCelda + " en la fila Nº " + ai_fila
						    + " no corresponde a un porcentaje valido (mas de 10 decimales)."
						);
				}

				ls_tmp = "" + ld_celda;
			}
		}
		else
			throw new B2BException(
			    "El campo " + as_nombreCelda + " en la fila Nº " + ai_fila
			    + ", tiene un valor; en caso de no verlo, seleccione toda la fila y borre los datos o eliminela."
			);

		return ls_tmp;
	}

	/**
	 * Validar segunda recepcion documentos 2.
	 *
	 * @param act_turnos de act turnos
	 * @param as_idProceso de as id proceso
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param as_solicitud de as solicitud
	 * @param as_proceso de as proceso
	 * @param as_subproceso de as subproceso
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized String validarSegundaRecepcionDocumentos2(
	    Collection<Turno> act_turnos, String as_idProceso, String as_userId, String as_remoteIp, Solicitud as_solicitud,
	    String as_proceso, String as_subproceso
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			SolicitudAsociadaDAO          lsa_DAO;
			SolicitudDAO                  lsd_DAO;
			SolicitudAsociada             lsa_solicitudAsociada;
			Solicitud                     ls_result;
			Collection<SolicitudAsociada> lcsa_solicitudAsociada = null;

			lsa_DAO                   = DaoCreator.getSolicitudAsociadaDAO(ldm_manager);
			lsd_DAO                   = DaoCreator.getSolicitudDAO(ldm_manager);
			lsa_solicitudAsociada     = new SolicitudAsociada();

			for(Turno lt_solicitud : act_turnos)
			{
				ls_result = lsd_DAO.findByNir(lt_solicitud.getNir());

				if(ls_result != null)
				{
					lsa_solicitudAsociada.setIdSolicitud(ls_result.getIdSolicitud());
					lcsa_solicitudAsociada = lsa_DAO.findAllByIdSolicitud(lsa_solicitudAsociada, false);

					String ls_subprocesoCase40 = ls_result.getIdSubproceso();

					Collection<TurnoHistoria> lcth_turnosHistorias;
					TurnoHistoria lth_tmp      = new TurnoHistoria();

					lth_tmp.setIdTurno(as_solicitud.getIdTurnoAnt());
					lcth_turnosHistorias     = null;

					lcth_turnosHistorias = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
							                             .findByTurnoSuspensionTramiteEntregaDoc(lth_tmp, false, null);

					if(CollectionUtils.isValidCollection(lcth_turnosHistorias))
					{
						for(TurnoHistoria lth_obj : lcth_turnosHistorias)
						{
							String ls_estadoActividad = lth_obj.getEstadoActividad();
							String ls_idSubproceso    = lth_obj.getIdSubproceso();

							if(ls_estadoActividad.equalsIgnoreCase(IdentificadoresCommon.AUT))
							{
								switch(ls_idSubproceso)
								{
									case IdentificadoresCommon.NUM1:
										ls_return = IdentificadoresCommon.ESPACIO_VACIO;

										break;

									case IdentificadoresCommon.NUM3:
										ls_return = IdentificadoresCommon.ESPACIO_VACIO;

										break;

									case IdentificadoresCommon.NUM4:
										ls_return = IdentificadoresCommon.ESPACIO_VACIO;

										break;

									case IdentificadoresCommon.NUM6:
										ls_return = IdentificadoresCommon.ESPACIO_VACIO;

										break;

									default:
										break;
								}
							}
						}
					}

					if((ls_return == null) || !ls_return.equalsIgnoreCase(IdentificadoresCommon.ESPACIO_VACIO))
					{
						if(CollectionUtils.isValidCollection(lcsa_solicitudAsociada))
						{
							for(SolicitudAsociada solicitudAsociada : lcsa_solicitudAsociada)
							{
								solicitudAsociada.getIdSolicitud1();

								if(solicitudAsociada != null)
								{
									String[] lsa_subproceso;

									lsa_subproceso = as_subproceso.split(IdentificadoresCommon.SIMBOLO_COMA_TXT);

									if(lsa_subproceso.length > 1)
									{
										for(String ls_validar : lsa_subproceso)
										{
											if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40))
											{
												if(ls_subprocesoCase40.equalsIgnoreCase(ls_validar))
												{
													ls_result = lsd_DAO.findByIdAndProcessAndSubprocess(
														    solicitudAsociada.getIdSolicitud1(), as_proceso,
														    lt_solicitud.getIdProceso()
														);

													break;
												}
												else
													ls_result = null;
											}
											else if(lt_solicitud.getIdProceso().equalsIgnoreCase(ls_validar))
											{
												ls_result = lsd_DAO.findByIdAndProcessAndSubprocess(
													    solicitudAsociada.getIdSolicitud1(), as_proceso,
													    lt_solicitud.getIdProceso()
													);

												break;
											}
											else
												ls_result = null;
										}
									}
									else
									{
										if(lt_solicitud.getIdProceso().equalsIgnoreCase(as_subproceso))
											ls_result = lsd_DAO.findByIdAndProcessAndSubprocess(
												    solicitudAsociada.getIdSolicitud1(), as_proceso,
												    lt_solicitud.getIdProceso()
												);
										else
											ls_result = null;
									}

									if(ls_result != null)
									{
										Date ld_date;
										ld_date = ls_result.getFechaCreacion();

										if(ld_date != null)
											ls_return = StringUtils.getString(ld_date, "MMMM dd/yyyy");
									}

									if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40))
									{
										if(ls_result == null)
											ls_return = null;
										else
											ls_return = IdentificadoresCommon.ESPACIO_VACIO;
									}
								}
							}
						}
						else if(as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40))
						{
							if(ls_result != null)
								ls_return = IdentificadoresCommon.ESPACIO_VACIO;
						}
						else
							ls_result = null;
					}
					else
						ls_return = IdentificadoresCommon.ESPACIO_VACIO;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSegundaRecepcionDocumentos2", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
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
					throw new B2BException(
					    "El campo " + as_nombreCelda + " en la fila Nº " + ai_fila + " no debe estar vacío."
					);
			}
			else if(ab_requerido)
				throw new B2BException(
				    "El campo " + as_nombreCelda + " en la fila Nº " + ai_fila + " no debe estar vacío."
				);
		}
		else
			throw new B2BException(
			    "El campo " + as_nombreCelda + " en la fila Nº " + ai_fila
			    + ", tiene un valor; en caso de no verlo, seleccione toda la fila y borre los datos o eliminela."
			);

		return ls_tmp;
	}

	/**
	 * Verifica que el acto que se va a almacenar en base de datos no tenga restricciones con el tipo adquisición vpm.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param aa_acto Objeto contenedor de la información del acto a ingresar
	 * @param as_idSolicitud id de la solicitud del tramite en proceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void validarTipoAdquisicionVPM(
	    DAOManager adm_manager, com.bachue.snr.prosnr01.model.registro.Acto aa_acto, String as_idSolicitud
	)
	    throws B2BException
	{
		if(aa_acto != null)
		{
			String ls_idTipoAdquisicionVpm;
			String ls_codigoActoResticcion;
			String ls_codigoActoParam;
			String ls_tipoAdquisicionParam;

			{
				ConstantesDAO lcd_constantesDAO;
				String        ls_idConstanteVpm;
				String        ls_idConstante0365;
				Constantes    lc_constanteVpm;
				Constantes    lc_constante0365;

				lcd_constantesDAO      = DaoCreator.getConstantesDAO(adm_manager);
				ls_idConstanteVpm      = ConstanteCommon.TIPO_ADQUISICION_VIVIENDA_MILITAR;
				ls_idConstante0365     = ConstanteCommon.ACTO_RESTRICCION_TIPO_VIVIENDA_MILITAR;
				lc_constanteVpm        = lcd_constantesDAO.findById(ls_idConstanteVpm);
				lc_constante0365       = lcd_constantesDAO.findById(ls_idConstante0365);

				if(lc_constanteVpm == null)
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_idConstanteVpm;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}

				ls_idTipoAdquisicionVpm = StringUtils.getStringNotNull(lc_constanteVpm.getCaracter());

				if(lc_constante0365 == null)
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_idConstante0365;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}

				ls_codigoActoResticcion = StringUtils.getStringNotNull(lc_constante0365.getCaracter());
			}

			ls_codigoActoParam          = StringUtils.getStringNotNull(aa_acto.getCodigo());
			ls_tipoAdquisicionParam     = StringUtils.getStringNotNull(aa_acto.getTipoAdquisicion());

			if(ls_codigoActoParam.equals(ls_codigoActoResticcion))
			{
				if(ls_tipoAdquisicionParam.equals(ls_idTipoAdquisicionVpm))
					throw new B2BException(ErrorKeys.ERROR_ADQUISICION_VPM_Y_ACTO_0362);

				Collection<Acto> lca_actos;

				lca_actos = DaoCreator.getActoDAO(adm_manager).findByIdSolicitud(as_idSolicitud);

				if(CollectionUtils.isValidCollection(lca_actos))
				{
					Iterator<Acto> lia_iterador;
					boolean        lb_found;

					lia_iterador     = lca_actos.iterator();
					lb_found         = false;

					while(lia_iterador.hasNext() && !lb_found)
					{
						Acto la_acto;

						la_acto = lia_iterador.next();

						if(la_acto != null)
						{
							String ls_tipoAdquisicion;

							ls_tipoAdquisicion     = StringUtils.getStringNotNull(la_acto.getIdTipoAdquisicion());

							lb_found = ls_tipoAdquisicion.equals(ls_idTipoAdquisicionVpm);
						}
					}

					if(lb_found)
						throw new B2BException(ErrorKeys.ERROR_ADQUISICION_VPM_Y_ACTO_0362);
				}
			}
			else if(ls_tipoAdquisicionParam.equals(ls_idTipoAdquisicionVpm))
			{
				Acto la_acto;

				la_acto = DaoCreator.getActoDAO(adm_manager)
						                .findBySolicitudTipoActo(as_idSolicitud, ls_codigoActoResticcion);

				if(la_acto != null)
					throw new B2BException(ErrorKeys.ERROR_ACTO_0362_Y_ADQUISICION_VPM);
			}
		}
	}

	/**
	 * Verifica que el valor de un avaluo sea correcto.
	 *
	 * @param abd_avaluo Objeto contenedor del valor del avaluo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized void validarValorAvaluo(BigDecimal abd_avaluo)
	    throws B2BException
	{
		if(abd_avaluo != null)
		{
			if(!NumericUtils.isValidBigDecimal(abd_avaluo))
				throw new B2BException(ErrorKeys.ERROR_VALOR_AVALUO_NO_NUMERICO);

			if(abd_avaluo.compareTo(BigDecimal.ZERO) == 0)
				throw new B2BException(ErrorKeys.ERROR_VALOR_AVALUO_MAYOR_CERO);
		}
		else
			throw new B2BException(ErrorKeys.ERROR_VALOR_AVALUO);
	}
}
