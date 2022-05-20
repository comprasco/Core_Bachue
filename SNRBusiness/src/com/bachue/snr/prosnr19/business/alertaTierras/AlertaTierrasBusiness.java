package com.bachue.snr.prosnr19.business.alertaTierras;

import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoEntradaActivarAlertaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoEntradaAgregarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoEntradaCancelarIngresoAlertaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertas;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertasListaAlertasAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoEntradaConsultarDetalleAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaInactivacion;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoEntradaConsultarDocumentoAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.EntradaConsultarEntidadesJ_A;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculasListaMatriculaMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculasListaMatriculasMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoEntradaConsultarMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatriculaListaMatriculasMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoEntradaConsultarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlertaListaMatriculasMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoEntradaConsultarMatriculaFiltrosTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoEntradaConsultarMatriculaInfoCatastral;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoEntradaConsultarOficinasOrigenTipo;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipoListaOficinasOrigenOficinaOrigen;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoEntradaEliminarProcAntiguoSistema;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlertaIdMotivo;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoEntradaInscribirAlertaCabecera;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoEntradaListarProcAntiguoSistema;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoEntradaRechazarCorreccionAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoEntradaRemoverMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.bgn.AlertaTierrasDAO;
import com.bachue.snr.prosnr01.dao.bng.AlertaTComunidadDAO;
import com.bachue.snr.prosnr01.dao.bng.AlertaTPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.SolicitudCorreccionDAO;

import com.bachue.snr.prosnr01.model.sdb.bng.AlertaGenerada;
import com.bachue.snr.prosnr01.model.sdb.bng.AlertaTComunidad;
import com.bachue.snr.prosnr01.model.sdb.bng.AlertaTInactivacion;
import com.bachue.snr.prosnr01.model.sdb.bng.AlertaTPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AlertaTierras;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.bng.ProcAntSistemaAT;
import com.bachue.snr.prosnr01.model.sdb.bng.SolicitudCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.png.ComunidadesEtnicas;
import com.bachue.snr.prosnr01.model.view.EntidadesEspeciales;

import com.bachue.snr.prosnr19.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.time.LocalDateTime;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades AlertaTierrasBusiness.
 * Operación ConsultarMapaPredioInfoCatastral excluida
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class AlertaTierrasBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AlertaTierrasBusiness.class, ProyectosCommon.ALERTA_TIERRAS_19);

	/**
	 * Activar alerta tierras.
	 *
	 * @param ateaat_entrada de ateaat entrada
	 * @param as_as_userId de as as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida activar alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaActivarAlertaTierras activarAlertaTierras(
	    TipoEntradaActivarAlertaTierras ateaat_entrada, String as_as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(ateaat_entrada != null)
			{
				//TODO Pendiente validación ly
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("activarAlertaTierras", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("activarAlertaTierras", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaActivarAlertaTierras ltsaat_return;

			ltsaat_return = new TipoSalidaActivarAlertaTierras(lbi_codigoMensaje.toString(), ls_descripcionMensaje);

			return ltsaat_return;
		}
	}

	/**
	 * Agregar matricula alerta.
	 *
	 * @param ateama_entrada de ateama entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida agregar matricula alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaAgregarMatriculaAlerta agregarMatriculaAlerta(
	    TipoEntradaAgregarMatriculaAlerta ateama_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "OK";

		try
		{
			if(ateama_entrada != null)
			{
				AlertaTPredio latp_alertaTPredio;

				latp_alertaTPredio = new AlertaTPredio(ateama_entrada);

				if(latp_alertaTPredio != null)
				{
					AlertaTPredioDAO latp_DAO;
					AlertaTPredio    latp_alertaTPredioTmp;

					latp_DAO                  = DaoCreator.getAlertaTPredioDAO(ldm_manager);
					latp_alertaTPredioTmp     = latp_DAO.findById(
						    latp_alertaTPredio.getIdAlertaTierras(), latp_alertaTPredio.getIdCirculo(),
						    latp_alertaTPredio.getIdMatricula()
						);

					if(latp_alertaTPredioTmp == null)
					{
						latp_alertaTPredio.setEstado(EstadoCommon.INGRESANDO);
						latp_alertaTPredio.setIdUsuarioCreacion(as_userId);
						latp_alertaTPredio.setIpCreacion(as_remoteIp);

						latp_DAO.insert(latp_alertaTPredio);
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_MATRICULA_EXISTENTE_ALERTA));
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("agregarMatriculaAlerta", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("agregarMatriculaAlerta", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaAgregarMatriculaAlerta ltsama_return;

			ltsama_return = new TipoSalidaAgregarMatriculaAlerta(lbi_codigoMensaje.toString(), ls_descripcionMensaje);

			return ltsama_return;
		}
	}

	/**
	 * Cancelar ingreso alerta tierras.
	 *
	 * @param ateciat_entrada de ateciat entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida cancelar ingreso alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaCancelarIngresoAlertaTierras cancelarIngresoAlertaTierras(
	    TipoEntradaCancelarIngresoAlertaTierras ateciat_entrada, String as_userId, String localIpAddress,
	    String                                  as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "OK";

		try
		{
			if(ateciat_entrada != null)
			{
				AlertaTierrasDAO lat_DAO;
				int              li_idAlerta;
				AlertaTierras    lat_alertaTierras;

				lat_DAO               = DaoCreator.getAlertaTierrasDAO(ldm_manager);
				li_idAlerta           = ateciat_entrada.getIdAlerta();
				lat_alertaTierras     = lat_DAO.findById(li_idAlerta);

				if(lat_alertaTierras != null)
				{
					String ls_estado;

					ls_estado = lat_alertaTierras.getEstadoAlerta();

					if(StringUtils.isValidString(ls_estado) && ls_estado.equalsIgnoreCase(EstadoCommon.INGRESANDO))
					{
						DaoCreator.getAlertaTPredioDAO(ldm_manager).deleteByIdAlertaTierras(li_idAlerta);
						DaoCreator.getAlertaGeneradaDAO(ldm_manager).deleteByIdAlertaTierras(li_idAlerta);
						DaoCreator.getProcAntSistemaATDAO(ldm_manager).deleteByIdAlertaTierras(li_idAlerta);
						DaoCreator.getAlertaMSGDAO(ldm_manager).deleteByIdAlertaTierras(li_idAlerta);
						DaoCreator.getAlertaTInactivacionDAO(ldm_manager).deleteByIdAlertaTierras(li_idAlerta);
						DaoCreator.getSolicitudCorreccionBNGDAO(ldm_manager).deleteByIdAlertaTierras(li_idAlerta);
						DaoCreator.getAlertaTComunidadDAO(ldm_manager).deleteByIdAlertaTierras(li_idAlerta);
						lat_DAO.delete(lat_alertaTierras);
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_ALERTA_NO_EXISTE_ESTADO_INGRESANDO));
				}
				else
					throw new B2BException(addErrorAT(ErrorKeys.ERROR_ALERTA_NO_EXISTE));
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("cancelarIngresoAlertaTierras", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("cancelarIngresoAlertaTierras", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaCancelarIngresoAlertaTierras ltsciat_return;

			ltsciat_return = new TipoSalidaCancelarIngresoAlertaTierras(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje
				);

			return ltsciat_return;
		}
	}

	/**
	 * Consultar alertas.
	 *
	 * @param ateca_entrada de ateca entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar alertas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarAlertas consultarAlertas(
	    TipoEntradaConsultarAlertas ateca_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		BigInteger                 lbi_codigoMensaje;
		String                     ls_descripcionMensaje;
		TipoSalidaConsultarAlertas ltsciat_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltsciat_return            = null;

		try
		{
			if(ateca_entrada != null)
			{
				AlertaTierras lat_alertaTierras;

				lat_alertaTierras = new AlertaTierras(ateca_entrada);

				if(lat_alertaTierras != null)
				{
					String ls_creadoSNR;

					ls_creadoSNR = lat_alertaTierras.getCreadoSNR();

					if(StringUtils.isValidString(ls_creadoSNR))
					{
						{
							String ls_codigoEstado;

							ls_codigoEstado = lat_alertaTierras.getEstadoAlerta();

							if(
							    StringUtils.isValidString(ls_codigoEstado)
								    && !(ls_codigoEstado.equalsIgnoreCase(EstadoCommon.ACTIVA)
								    || ls_codigoEstado.equalsIgnoreCase(EstadoCommon.INGRESANDO)
								    || ls_codigoEstado.equalsIgnoreCase(EstadoCommon.REVISION))
							)
								throw new B2BException(addErrorAT(ErrorKeys.ERROR_ESTADO_NO_VALIDO));
						}

						{
							if(ls_creadoSNR.equalsIgnoreCase(EstadoCommon.NO_TXT))
							{
								EntidadesEspeciales les_entidadEspecial;

								les_entidadEspecial = DaoCreator.getEntidadesEspecialesDAO(ldm_manager)
										                            .findById(lat_alertaTierras.getIdEntidad());

								if(les_entidadEspecial == null)
									throw new B2BException(addErrorAT(ErrorKeys.ERROR_ENTIDAD_NO_VALIDA));
							}
						}

						Collection<AlertaTierras> lcat_cllAlertaTierras;
						LocalDateTime             lldt_fechaInscripcion;

						lldt_fechaInscripcion     = obtenerLocalDateTime(
							    lat_alertaTierras.getFechaInscripcionCalendar()
							);
						lcat_cllAlertaTierras     = DaoCreator.getAlertaTierrasDAO(ldm_manager)
								                                  .findAllFilterSNR(
								    lat_alertaTierras, obtenerDateDesdeLocalDateTime(lldt_fechaInscripcion)
								);

						if(CollectionUtils.isValidCollection(lcat_cllAlertaTierras))
						{
							TipoSalidaConsultarAlertasListaAlertasAlerta[] ltscalaa_listaAlerta;
							int                                            li_count;

							ltscalaa_listaAlerta     = new TipoSalidaConsultarAlertasListaAlertasAlerta[lcat_cllAlertaTierras
									.size()];
							li_count                 = 0;

							for(AlertaTierras lat_tmp : lcat_cllAlertaTierras)
							{
								if(lat_tmp != null)
								{
									ltscalaa_listaAlerta[li_count] = new TipoSalidaConsultarAlertasListaAlertasAlerta(
										    NumericUtils.getInt(lat_tmp.getIdAlertaTierras()), lat_tmp.getEstadoAlerta(),
										    lat_tmp.getNomEntidad(),
										    obtenerCalendarDesdeDate(lat_tmp.getFechaInscripcion()),
										    lat_tmp.getDocumentoTipo(), lat_tmp.getNomComunidadEtnica()
										);

									li_count++;
								}
							}

							ltsciat_return = new TipoSalidaConsultarAlertas(
								    lbi_codigoMensaje.toString(), "OK", ltscalaa_listaAlerta
								);
						}
						else
							throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTROS_CRITERIOS));
					}
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarAlertas", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltsciat_return            = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarAlertas", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltsciat_return            = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsciat_return == null)
		{
			TipoSalidaConsultarAlertasListaAlertasAlerta[] ltscalaa_listaAlerta;

			ltscalaa_listaAlerta     = new TipoSalidaConsultarAlertasListaAlertasAlerta[1];

			ltscalaa_listaAlerta[0]     = new TipoSalidaConsultarAlertasListaAlertasAlerta(
				    0, "", "", Calendar.getInstance(), "", ""
				);

			ltsciat_return = new TipoSalidaConsultarAlertas(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, ltscalaa_listaAlerta
				);
		}

		return ltsciat_return;
	}

	/**
	 * Consultar detalle alerta.
	 *
	 * @param atecda_entrada de atecda entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar detalle alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarDetalleAlerta consultarDetalleAlerta(
	    TipoEntradaConsultarDetalleAlerta atecda_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		BigInteger                       lbi_codigoMensaje;
		String                           ls_descripcionMensaje;
		TipoSalidaConsultarDetalleAlerta ltscda_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltscda_return             = null;

		try
		{
			if(atecda_entrada != null)
			{
				long          ll_idAlerta;
				AlertaTierras lat_alertaTierras;

				ll_idAlerta           = atecda_entrada.getIdAlerta();
				lat_alertaTierras     = DaoCreator.getAlertaTierrasDAO(ldm_manager).findMoreParametersById(ll_idAlerta);

				if(lat_alertaTierras != null)
				{
					//Lista de matrículas
					TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula[] ltsdalmm_listaMatriculas;

					ltsdalmm_listaMatriculas = null;

					{
						Collection<AlertaTPredio> lcatp_alertaTPredio;

						lcatp_alertaTPredio = DaoCreator.getAlertaTPredioDAO(ldm_manager)
								                            .findAllByIdAlertaTierras(ll_idAlerta);

						if(CollectionUtils.isValidCollection(lcatp_alertaTPredio))
						{
							int li_countMatricula;

							ltsdalmm_listaMatriculas     = new TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula[lcatp_alertaTPredio
									.size()];
							li_countMatricula            = 0;

							for(AlertaTPredio latp_tmp : lcatp_alertaTPredio)
							{
								if(latp_tmp != null)
								{
									ltsdalmm_listaMatriculas[li_countMatricula] = new TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula(
										    latp_tmp.getNombreCirculo(),
										    StringUtils.getString(latp_tmp.getIdMatricula()),
										    latp_tmp.getNumeroPredial()
										);

									li_countMatricula++;
								}
							}
						}
					}

					//Objeto inactivación
					TipoSalidaConsultarDetalleAlertaInactivacion ltscdai_inactivacion;

					ltscdai_inactivacion = null;

					{
						AlertaTInactivacion latti_alertaTInactivacion;

						latti_alertaTInactivacion = DaoCreator.getAlertaTInactivacionDAO(ldm_manager)
								                                  .findById(ll_idAlerta);

						if(latti_alertaTInactivacion != null)
							ltscdai_inactivacion = new TipoSalidaConsultarDetalleAlertaInactivacion(
								    latti_alertaTInactivacion.getMotivoInactivacion(),
								    latti_alertaTInactivacion.getNomOficinaOrigen(),
								    latti_alertaTInactivacion.getNomTipoDocumentoPublico(),
								    obtenerCalendarDesdeDate(latti_alertaTInactivacion.getDocumentoFecha()),
								    latti_alertaTInactivacion.getDocumentoNumero(),
								    latti_alertaTInactivacion.getDocumentoSGDId(),
								    latti_alertaTInactivacion.getDocumentoSGDDocName(),
								    latti_alertaTInactivacion.getTextoInactivacion()
								);
					}

					//Lista alerta generada
					TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada[] ltscdalafag_listaAlertasGeneradas;

					ltscdalafag_listaAlertasGeneradas = null;

					{
						Collection<AlertaGenerada> lcatg_alertaGenerada;

						lcatg_alertaGenerada = DaoCreator.getAlertaGeneradaDAO(ldm_manager)
								                             .findByIdAlertaTierras(ll_idAlerta);

						if(CollectionUtils.isValidCollection(lcatg_alertaGenerada))
						{
							int li_countAlertaGenerada;

							ltscdalafag_listaAlertasGeneradas     = new TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada[lcatg_alertaGenerada
									.size()];
							li_countAlertaGenerada                = 0;

							for(AlertaGenerada lag_tmp : lcatg_alertaGenerada)
							{
								if(lag_tmp != null)
								{
									ltscdalafag_listaAlertasGeneradas[li_countAlertaGenerada] = new TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada(
										    lag_tmp.getNombreCirculo(), NumericUtils.getInt(lag_tmp.getIdMatricula()),
										    lag_tmp.getIdTurno(), lag_tmp.getMensaje(), lag_tmp.getUsuarioOrigina()
										);

									li_countAlertaGenerada++;
								}
							}
						}
					}

					//Lista procesos AS
					TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS[] ltscdalpaspas_listaProcesosAS;

					ltscdalpaspas_listaProcesosAS = null;

					{
						Collection<ProcAntSistemaAT> lcpat_ProcAntSistemaAT;

						lcpat_ProcAntSistemaAT = DaoCreator.getProcAntSistemaATDAO(ldm_manager)
								                               .findAllByIdAlertaTierra(ll_idAlerta);

						if(CollectionUtils.isValidCollection(lcpat_ProcAntSistemaAT))
						{
							int li_countProcesosAS;

							ltscdalpaspas_listaProcesosAS     = new TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS[lcpat_ProcAntSistemaAT
									.size()];
							li_countProcesosAS                = 0;

							for(ProcAntSistemaAT lpat_tmp : lcpat_ProcAntSistemaAT)
							{
								if(lpat_tmp != null)
								{
									ltscdalpaspas_listaProcesosAS[li_countProcesosAS] = new TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS(
										    NumericUtils.getInt(lpat_tmp.getIdAlertaTierra()),
										    lpat_tmp.getNomCirculoRegistral(), lpat_tmp.getNomPais(),
										    lpat_tmp.getNomDepartamento(), lpat_tmp.getNomMunicipio(),
										    lpat_tmp.getTipoPredio(), NumericUtils.getInt(lpat_tmp.getNumeroLibro()),
										    NumericUtils.getInt(lpat_tmp.getNumeroTomo()), lpat_tmp.getTipoPartida(),
										    NumericUtils.getInt(lpat_tmp.getNumeroPartida()),
										    NumericUtils.getInt(lpat_tmp.getNumeroFolio()),
										    NumericUtils.getInt(lpat_tmp.getAnio()), lpat_tmp.getNombrePredio(),
										    NumericUtils.getInteger(lpat_tmp.getNumeroPredio())
										);

									li_countProcesosAS++;
								}
							}
						}
					}

					//nitComunidadEtnica
					String ls_nitComunidadEtnica;

					ls_nitComunidadEtnica = null;

					{
						AlertaTComunidad latc_alertaTComunidad;

						latc_alertaTComunidad = DaoCreator.getAlertaTComunidadDAO(ldm_manager)
								                              .findFirstByIdAlertaTierra(ll_idAlerta);

						if(latc_alertaTComunidad != null)
						{
							ComunidadesEtnicas lce_comunidadesEtnicas;

							lce_comunidadesEtnicas = DaoCreator.getComunidadesEtnicasDAO(ldm_manager)
									                               .findById(
									    NumericUtils.getInt(latc_alertaTComunidad.getIdComunidadEtnica())
									);

							if(lce_comunidadesEtnicas != null)
								ls_nitComunidadEtnica = lce_comunidadesEtnicas.getNumeroDocumento();
						}
					}

					if(
					    CollectionUtils.isValidCollection(ltsdalmm_listaMatriculas)
						    && CollectionUtils.isValidCollection(ltscdalafag_listaAlertasGeneradas)
						    && CollectionUtils.isValidCollection(ltscdalpaspas_listaProcesosAS)
					)
						ltscda_return = new TipoSalidaConsultarDetalleAlerta(
							    lbi_codigoMensaje.toString(), "OK", lat_alertaTierras.getTipoAlerta(),
							    lat_alertaTierras.getNomEntidad(), lat_alertaTierras.getNumeroRadicado(),
							    obtenerCalendarDesdeDate(lat_alertaTierras.getFechaRadicado()),
							    lat_alertaTierras.getNomOficinaOrigen(), lat_alertaTierras.getNomTipoDocumentoPublico(),
							    obtenerCalendarDesdeDate(lat_alertaTierras.getDocumentoFecha()),
							    lat_alertaTierras.getDocumentoNumero(), lat_alertaTierras.getDocumentoId(),
							    lat_alertaTierras.getDocumentoDocName(), ls_nitComunidadEtnica,
							    lat_alertaTierras.getEstadoAlerta(),
							    obtenerCalendarDesdeDate(lat_alertaTierras.getFechaFinAlerta()),
							    lat_alertaTierras.getCreadoSNR(), ltsdalmm_listaMatriculas, ltscdai_inactivacion,
							    ltscdalafag_listaAlertasGeneradas, ltscdalpaspas_listaProcesosAS
							);
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTROS_CRITERIOS));
				}
				else
					throw new B2BException(addErrorAT(ErrorKeys.ERROR_ALERTA_NO_EXISTE));
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarDetalleAlerta", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltscda_return             = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarDetalleAlerta", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltscda_return             = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscda_return == null)
		{
			TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula[]            ltsdalmm_listaMatriculas;
			TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada[] ltscdalafag_listaAlertasGeneradas;
			TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS[]            ltscdalpaspas_listaProcesosAS;

			ltsdalmm_listaMatriculas              = new TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula[1];
			ltscdalafag_listaAlertasGeneradas     = new TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada[1];
			ltscdalpaspas_listaProcesosAS         = new TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS[1];

			ltsdalmm_listaMatriculas[0]              = new TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula(
				    "", "", null
				);
			ltscdalafag_listaAlertasGeneradas[0]     = new TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada(
				    "", 0, "", null, null
				);
			ltscdalpaspas_listaProcesosAS[0]         = new TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS(
				    0, "", "", "", "", "", 0, 0, "", 0, 0, 0, null, null
				);

			ltscda_return = new TipoSalidaConsultarDetalleAlerta(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, "", "", "", Calendar.getInstance(), "", "",
				    Calendar.getInstance(), "", "", "", null, "", null, "", ltsdalmm_listaMatriculas, null,
				    ltscdalafag_listaAlertasGeneradas, ltscdalpaspas_listaProcesosAS
				);
		}

		return ltscda_return;
	}

	/**
	 * Consultar documento alerta.
	 *
	 * @param atecda_entrada de atecda entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar documento alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarDocumentoAlerta consultarDocumentoAlerta(
	    TipoEntradaConsultarDocumentoAlerta atecda_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "OK, el documento no pertenece a ninguna alerta activa";

		try
		{
			if(atecda_entrada != null)
			{
				AlertaTierras lat_alertaTierras;

				lat_alertaTierras = new AlertaTierras(atecda_entrada);

				lat_alertaTierras.setDocumentoFecha(obtenerDateDesdeCalendar(atecda_entrada.getFechaDocumento()));

				if(lat_alertaTierras != null)
				{
					lat_alertaTierras = DaoCreator.getAlertaTierrasDAO(ldm_manager)
							                          .findByDocumento(lat_alertaTierras, true);

					if(lat_alertaTierras != null)
					{
						String ls_estadoAlerta;

						ls_estadoAlerta = lat_alertaTierras.getEstadoAlerta();

						if(
						    StringUtils.isValidString(ls_estadoAlerta)
							    && ls_estadoAlerta.equalsIgnoreCase(EstadoCommon.ACTIVA)
						)
							throw new B2BException(addErrorAT(ErrorKeys.ERROR_DOCUMENTO_PERTENECE_ALERTA));
					}
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarDocumentoAlerta", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarDocumentoAlerta", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaConsultarDocumentoAlerta ltscda_return;

			ltscda_return = new TipoSalidaConsultarDocumentoAlerta(lbi_codigoMensaje.toString(), ls_descripcionMensaje);

			return ltscda_return;
		}
	}

	/**
	 * Consultar entidades J A.
	 *
	 * @param aece_entrada de aece entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar entidades J A
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarEntidadesJ_A consultarEntidadesJ_A(
	    EntradaConsultarEntidadesJ_A aece_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		BigInteger                      lbi_codigoMensaje;
		String                          ls_descripcionMensaje;
		TipoSalidaConsultarEntidadesJ_A ltsce_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltsce_return              = null;

		try
		{
			Collection<EntidadesEspeciales> lcee_entidadEspecial;

			lcee_entidadEspecial = DaoCreator.getEntidadesEspecialesDAO(ldm_manager).findAll();

			if((lcee_entidadEspecial != null) && (lcee_entidadEspecial.size() > NumericUtils.DEFAULT_INT_VALUE))
			{
				String ls_documentoEntidadesAgenciaNacionalTierras;

				ls_documentoEntidadesAgenciaNacionalTierras = DaoCreator.getConstantesDAO(ldm_manager)
						                                                    .findString(
						    ConstanteCommon.DOCUMENTO_ENTIDADES_AGENCIA_NACIONAL_TIERRAS
						);

				if(StringUtils.isValidString(ls_documentoEntidadesAgenciaNacionalTierras))
				{
					Collection<String> lcs_coleccionDocumentoEntidadesAgenciaNacionalTierras;

					lcs_coleccionDocumentoEntidadesAgenciaNacionalTierras = separarPorSimboloComa(
						    ls_documentoEntidadesAgenciaNacionalTierras, true
						);

					{
						TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A[] ltscejalajaaa_listaAutoridadesJ_A;
						int                                                             li_count;

						ltscejalajaaa_listaAutoridadesJ_A     = new TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A[lcee_entidadEspecial
								.size()];
						li_count                              = 0;

						for(EntidadesEspeciales laee_tmp : lcee_entidadEspecial)
						{
							if(laee_tmp != null)
							{
								String ls_numDocumentoEntidadExterna;
								String ls_esAgenciaNacionalTierras;

								ls_numDocumentoEntidadExterna     = laee_tmp.getNumeroDocumentoId();

								ls_esAgenciaNacionalTierras     = (CollectionUtils.isValidCollection(
									    lcs_coleccionDocumentoEntidadesAgenciaNacionalTierras
									)
										&& lcs_coleccionDocumentoEntidadesAgenciaNacionalTierras.contains(
										    ls_numDocumentoEntidadExterna
										)) ? EstadoCommon.SI_TXT : EstadoCommon.NO_TXT;

								ltscejalajaaa_listaAutoridadesJ_A[li_count] = new TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A(
									    StringUtils.getString(laee_tmp.getIdEntidad()), laee_tmp.getNombre(),
									    ls_esAgenciaNacionalTierras
									);

								li_count++;
							}
						}

						ltsce_return = new TipoSalidaConsultarEntidadesJ_A(
							    lbi_codigoMensaje.toString(), "OK", ltscejalajaaa_listaAutoridadesJ_A
							);
					}
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_CONSULTA_AUTORIDADES));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarEntidadesJ_A", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltsce_return              = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarEntidadesJ_A", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltsce_return              = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsce_return == null)
			ltsce_return = new TipoSalidaConsultarEntidadesJ_A(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, null
				);

		return ltsce_return;
	}

	/**
	 * Consultar lista matriculas.
	 *
	 * @param ateclm_entrada de ateclm entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar lista matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarListaMatriculas consultarListaMatriculas(
	    TipoEntradaConsultarListaMatriculas ateclm_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		BigInteger                         lbi_codigoMensaje;
		String                             ls_descripcionMensaje;
		TipoSalidaConsultarListaMatriculas ltsclm_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltsclm_return             = null;

		try
		{
			if(ateclm_entrada != null)
			{
				TipoEntradaConsultarListaMatriculasListaMatriculaMatricula[] ltelmlmm_listaMatricula;

				ltelmlmm_listaMatricula = ateclm_entrada.getListaMatricula();

				if(CollectionUtils.isValidCollection(ltelmlmm_listaMatricula))
				{
					Collection<TipoSalidaConsultarListaMatriculasListaMatriculasMatricula> ltsclmlmm_listaMatriculasCompleta;

					ltsclmlmm_listaMatriculasCompleta = new LinkedList<TipoSalidaConsultarListaMatriculasListaMatriculasMatricula>();

					for(TipoEntradaConsultarListaMatriculasListaMatriculaMatricula lteclmlmm_tmp : ltelmlmm_listaMatricula)
					{
						if(lteclmlmm_tmp != null)
						{
							String   ls_idCirculo;
							long     ll_idMatricula;
							Object[] loa_messageArgs;

							ls_idCirculo        = lteclmlmm_tmp.getCodCirculoRegistral();
							ll_idMatricula      = lteclmlmm_tmp.getNumMatriculaInmobiliaria();
							loa_messageArgs     = new String[1];

							loa_messageArgs[0] = StringUtils.getString(ll_idMatricula);

							if(StringUtils.isValidString(ls_idCirculo))
							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
										                             .findById(ls_idCirculo);

								if(lcr_circuloRegistral == null)
									throw new B2BException(
									    addErrorAT(
									        ErrorKeys.ERROR_CIRCULO_REGISTRAL_MATRICULA_NO_EXISTE, loa_messageArgs
									    )
									);
							}
							else
								throw new B2BException(
								    addErrorAT(ErrorKeys.ERROR_CIRCULO_REGISTRAL_MATRICULA_INVALIDO, loa_messageArgs)
								);

							PredioRegistro lp_predioRegistro;

							lp_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
									                          .findByCirculoMatricula(ls_idCirculo, ll_idMatricula);

							if(lp_predioRegistro != null)
							{
								String ls_numeroPredial;

								ls_numeroPredial = lp_predioRegistro.getNumeroPredial();

								if(!StringUtils.isValidString(ls_numeroPredial))
									propagarErrorNumeroPredial(ls_idCirculo, ll_idMatricula, false);

								ltsclmlmm_listaMatriculasCompleta.add(
								    new TipoSalidaConsultarListaMatriculasListaMatriculasMatricula(
								        ls_idCirculo, NumericUtils.getInt(ll_idMatricula), EstadoCommon.NO,
								        ls_numeroPredial
								    )
								);

								{
									Collection<PredioSegregado> lcps_predioSegregado;

									lcps_predioSegregado = DaoCreator.getPredioSegregadoDAO(ldm_manager)
											                             .findNumPredialByPadre(
											    ls_idCirculo, ll_idMatricula
											);

									if(CollectionUtils.isValidCollection(lcps_predioSegregado))
									{
										for(PredioSegregado lps_tmp : lcps_predioSegregado)
										{
											if(lps_tmp != null)
											{
												String ls_idCirculoMatDerivada;
												long   ll_idMatriculaDerivada;
												String ls_numeroPredialMatDerivada;

												ls_idCirculoMatDerivada         = lps_tmp.getIdCirculo1();
												ll_idMatriculaDerivada          = NumericUtils.getLong(
													    lps_tmp.getIdMatricula1()
													);
												ls_numeroPredialMatDerivada     = lps_tmp.getNumeroPredial();

												if(!StringUtils.isValidString(ls_numeroPredialMatDerivada))
													propagarErrorNumeroPredial(
													    ls_idCirculoMatDerivada, ll_idMatriculaDerivada, true
													);

												ltsclmlmm_listaMatriculasCompleta.add(
												    new TipoSalidaConsultarListaMatriculasListaMatriculasMatricula(
												        ls_idCirculoMatDerivada,
												        NumericUtils.getInt(ll_idMatriculaDerivada), EstadoCommon.SI,
												        ls_numeroPredialMatDerivada
												    )
												);
											}
										}
									}
								}
							}
							else
								throw new B2BException(addErrorAT(ErrorKeys.ERROR_CONSULTA_TODAS_MATRICULAS));
						}
					}

					if(CollectionUtils.isValidCollection(ltsclmlmm_listaMatriculasCompleta))
					{
						TipoSalidaConsultarListaMatriculasListaMatriculasMatricula[] ltsclmlmm_listaMatriculas;
						int                                                          li_count;

						ltsclmlmm_listaMatriculas     = new TipoSalidaConsultarListaMatriculasListaMatriculasMatricula[ltsclmlmm_listaMatriculasCompleta
								.size()];
						li_count                      = 0;

						for(TipoSalidaConsultarListaMatriculasListaMatriculasMatricula ltsclmlmm_tmp : ltsclmlmm_listaMatriculasCompleta)
						{
							if(ltsclmlmm_tmp != null)
							{
								ltsclmlmm_listaMatriculas[li_count] = new TipoSalidaConsultarListaMatriculasListaMatriculasMatricula(
									    ltsclmlmm_tmp.getCodCirculoRegistral(),
									    ltsclmlmm_tmp.getNumMatriculaInmobiliaria(), ltsclmlmm_tmp.getEsDerivada(),
									    ltsclmlmm_tmp.getNumPredial()
									);

								li_count++;
							}
						}

						ltsclm_return = new TipoSalidaConsultarListaMatriculas(
							    lbi_codigoMensaje.toString(), "OK", ltsclmlmm_listaMatriculas
							);
					}
				}
				else
					throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarListaMatriculas", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltsclm_return             = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarListaMatriculas", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltsclm_return             = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsclm_return == null)
		{
			TipoSalidaConsultarListaMatriculasListaMatriculasMatricula[] ltsclmlmm_listaMatriculas;

			ltsclmlmm_listaMatriculas     = new TipoSalidaConsultarListaMatriculasListaMatriculasMatricula[1];

			ltsclmlmm_listaMatriculas[0]     = new TipoSalidaConsultarListaMatriculasListaMatriculasMatricula(
				    "", 0, "", ""
				);

			ltsclm_return = new TipoSalidaConsultarListaMatriculas(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, ltsclmlmm_listaMatriculas
				);
		}

		return ltsclm_return;
	}

	/**
	 * Propagar error numero predial.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param ab_matriculaDerivada de ab matricula derivada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void propagarErrorNumeroPredial(String as_idCirculo, long al_idMatricula, boolean ab_matriculaDerivada)
	    throws B2BException
	{
		Object[] loa_messageArgs;

		loa_messageArgs     = new String[2];

		loa_messageArgs[0]     = StringUtils.getString(al_idMatricula);
		loa_messageArgs[1]     = StringUtils.getString(as_idCirculo);

		if(!ab_matriculaDerivada)
			throw new B2BException(addErrorAT(ErrorKeys.ERROR_NUMERO_PREDIAL_MATRICULA_CIRCULO, loa_messageArgs));
		else
			throw new B2BException(
			    addErrorAT(ErrorKeys.ERROR_NUMERO_PREDIAL_MATRICULA_DERIVADA_CIRCULO, loa_messageArgs)
			);
	}

	/**
	 * Consultar matricula.
	 *
	 * @param atecm_entrada de atecm entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar matricula
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarMatricula consultarMatricula(
	    TipoEntradaConsultarMatricula atecm_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		BigInteger                   lbi_codigoMensaje;
		String                       ls_descripcionMensaje;
		TipoSalidaConsultarMatricula ltscm_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltscm_return              = null;

		try
		{
			if(atecm_entrada != null)
			{
				String   ls_idCirculo;
				long     ll_idMatricula;
				Object[] loa_messageArgs;

				ls_idCirculo        = atecm_entrada.getCodCirculoRegistral();
				ll_idMatricula      = atecm_entrada.getNumMatriculaInmobiliaria();
				loa_messageArgs     = new String[1];

				loa_messageArgs[0] = StringUtils.getString(ll_idMatricula);

				if(StringUtils.isValidString(ls_idCirculo))
				{
					CirculoRegistral lcr_circuloRegistral;

					lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(ls_idCirculo);

					if(lcr_circuloRegistral == null)
						throw new B2BException(
						    addErrorAT(ErrorKeys.ERROR_CIRCULO_REGISTRAL_MATRICULA_NO_EXISTE, loa_messageArgs)
						);
				}
				else
					throw new B2BException(
					    addErrorAT(ErrorKeys.ERROR_CIRCULO_REGISTRAL_MATRICULA_INVALIDO, loa_messageArgs)
					);

				{
					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
							                           .findByCirculoMatricula(ls_idCirculo, ll_idMatricula);

					if(lpr_predioRegistro != null)
					{
						Collection<TipoSalidaConsultarMatriculaListaMatriculasMatricula> lctscmlm_collection;

						lctscmlm_collection = new LinkedList<TipoSalidaConsultarMatriculaListaMatriculasMatricula>();

						{
							String ls_numeroPredial;

							ls_numeroPredial = lpr_predioRegistro.getNumeroPredial();

							if(!StringUtils.isValidString(ls_numeroPredial))
								propagarErrorNumeroPredial(ls_idCirculo, ll_idMatricula, false);

							lctscmlm_collection.add(
							    new TipoSalidaConsultarMatriculaListaMatriculasMatricula(
							        ls_idCirculo, NumericUtils.getInt(ll_idMatricula), EstadoCommon.NO, ls_numeroPredial
							    )
							);
						}

						{
							Collection<PredioSegregado> lcps_predioSegregado;

							lcps_predioSegregado = DaoCreator.getPredioSegregadoDAO(ldm_manager)
									                             .findNumPredialByPadre(ls_idCirculo, ll_idMatricula);

							if(CollectionUtils.isValidCollection(lcps_predioSegregado))
							{
								for(PredioSegregado lp_tmp : lcps_predioSegregado)
								{
									if(lp_tmp != null)
									{
										String ls_idCirculoMatDerivada;
										long   ll_idMatriculaDerivada;
										String ls_numeroPredialMatDerivada;

										ls_idCirculoMatDerivada         = lp_tmp.getIdCirculo1();
										ll_idMatriculaDerivada          = NumericUtils.getLong(
											    lp_tmp.getIdMatricula1()
											);
										ls_numeroPredialMatDerivada     = lp_tmp.getNumeroPredial();

										if(!StringUtils.isValidString(ls_numeroPredialMatDerivada))
											propagarErrorNumeroPredial(
											    ls_idCirculoMatDerivada, ll_idMatriculaDerivada, true
											);

										lctscmlm_collection.add(
										    new TipoSalidaConsultarMatriculaListaMatriculasMatricula(
										        ls_idCirculoMatDerivada, NumericUtils.getInt(ll_idMatriculaDerivada),
										        EstadoCommon.SI, ls_numeroPredialMatDerivada
										    )
										);
									}
								}
							}

							if(CollectionUtils.isValidCollection(lctscmlm_collection))
							{
								TipoSalidaConsultarMatriculaListaMatriculasMatricula[] ltscmlmm_listaMatriculas;
								int                                                    li_count;

								ltscmlmm_listaMatriculas     = new TipoSalidaConsultarMatriculaListaMatriculasMatricula[lctscmlm_collection
										.size()];
								li_count                     = 0;

								for(TipoSalidaConsultarMatriculaListaMatriculasMatricula ltscmlmm_tmp : lctscmlm_collection)
								{
									if(ltscmlmm_tmp != null)
									{
										ltscmlmm_listaMatriculas[li_count] = new TipoSalidaConsultarMatriculaListaMatriculasMatricula(
											    ltscmlmm_tmp.getCodCirculoRegistral(),
											    ltscmlmm_tmp.getNumMatriculaInmobiliaria(), ltscmlmm_tmp.getEsDerivada(),
											    ltscmlmm_tmp.getNumPredial()
											);

										li_count++;
									}
								}

								ltscm_return = new TipoSalidaConsultarMatricula(
									    lbi_codigoMensaje.toString(), "OK", ltscmlmm_listaMatriculas
									);
							}
						}
					}

					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_CONSULTA_MATRICULA));
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatricula", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltscm_return              = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatricula", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltscm_return              = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscm_return == null)
			ltscm_return = new TipoSalidaConsultarMatricula(lbi_codigoMensaje.toString(), ls_descripcionMensaje, null);

		return ltscm_return;
	}

	/**
	 * Consultar matricula alerta.
	 *
	 * @param atecma_entrada de atecma entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar matricula alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarMatriculaAlerta consultarMatriculaAlerta(
	    TipoEntradaConsultarMatriculaAlerta atecma_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		BigInteger                         lbi_codigoMensaje;
		String                             ls_descripcionMensaje;
		TipoSalidaConsultarMatriculaAlerta ltscma_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltscma_return             = null;

		try
		{
			if(atecma_entrada != null)
			{
				AlertaTPredio latp_alertaTPredio;

				latp_alertaTPredio = new AlertaTPredio(atecma_entrada);

				if(latp_alertaTPredio != null)
				{
					long   ll_idAlertaTierras;
					long   ll_numMatriculaInmobiliaria;
					String ls_idCirculo;

					ll_idAlertaTierras              = latp_alertaTPredio.getIdAlertaTierras();
					ll_numMatriculaInmobiliaria     = atecma_entrada.getNumMatriculaInmobiliaria();
					ls_idCirculo                    = latp_alertaTPredio.getIdCirculo();

					if(
					    (ll_idAlertaTierras > 0) && (ll_numMatriculaInmobiliaria > 0)
						    && StringUtils.isValidString(ls_idCirculo)
					)
					{
						Collection<AlertaTPredio> lcatp_alertaTPredio;

						lcatp_alertaTPredio = DaoCreator.getAlertaTPredioDAO(ldm_manager)
								                            .findAllByIdAlertaTierras(ll_idAlertaTierras, ls_idCirculo);

						if(CollectionUtils.isValidCollection(lcatp_alertaTPredio))
						{
							Collection<AlertaTPredio> lcatp_listaFiltrada;
							int                       li_count;

							lcatp_listaFiltrada     = new LinkedList<AlertaTPredio>();
							li_count                = 0;

							{
								for(AlertaTPredio atp_tmp : lcatp_alertaTPredio)
								{
									if(atp_tmp != null)
									{
										if(
										    (atp_tmp.getIdMatricula() > latp_alertaTPredio.getIdMatricula())
											    && (li_count < ll_numMatriculaInmobiliaria)
										)
										{
											lcatp_listaFiltrada.add(atp_tmp);

											li_count++;
										}
									}
								}
							}

							if(CollectionUtils.isValidCollection(lcatp_listaFiltrada))
							{
								TipoSalidaConsultarMatriculaAlertaListaMatriculasMatricula[] ltpcmalmm_listaMatriculas;
								int                                                          li_countListaMatriculas;

								ltpcmalmm_listaMatriculas     = new TipoSalidaConsultarMatriculaAlertaListaMatriculasMatricula[lcatp_listaFiltrada
										.size()];
								li_countListaMatriculas       = 0;

								for(AlertaTPredio latp_tmp : lcatp_listaFiltrada)
								{
									if(latp_tmp != null)
									{
										String ls_idCirculoConsultado;
										String ls_numeroPredialConsultado;
										long   ll_idMatriculaConsultado;

										ls_idCirculoConsultado         = latp_tmp.getIdCirculo();
										ll_idMatriculaConsultado       = latp_tmp.getIdMatricula();
										ls_numeroPredialConsultado     = latp_tmp.getNumeroPredial();

										if(!StringUtils.isValidString(ls_numeroPredialConsultado))
											propagarErrorNumeroPredial(
											    ls_idCirculoConsultado, ll_idMatriculaConsultado, false
											);

										ltpcmalmm_listaMatriculas[li_countListaMatriculas] = new TipoSalidaConsultarMatriculaAlertaListaMatriculasMatricula(
											    ls_idCirculoConsultado, NumericUtils.getInt(ll_idMatriculaConsultado),
											    ls_numeroPredialConsultado
											);

										li_countListaMatriculas++;
									}
								}

								ltscma_return = new TipoSalidaConsultarMatriculaAlerta(
									    lbi_codigoMensaje.toString(), "OK", ltpcmalmm_listaMatriculas
									);
							}
							else
								throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTROS_CRITERIOS));
						}
						else
							throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTROS_CRITERIOS));
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculaAlerta", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltscma_return             = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculaAlerta", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltscma_return             = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscma_return == null)
			ltscma_return = new TipoSalidaConsultarMatriculaAlerta(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, null
				);

		return ltscma_return;
	}

	/**
	 * Consultar matricula filtros tierras.
	 *
	 * @param atecmft_entrada de atecmft entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar matricula filtros tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarMatriculaFiltrosTierras consultarMatriculaFiltrosTierras(
	    TipoEntradaConsultarMatriculaFiltrosTierras atecmft_entrada, String as_userId, String localIpAddress,
	    String                                      as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                                 ldm_manager;
		BigInteger                                 lbi_codigoMensaje;
		String                                     ls_descripcionMensaje;
		TipoSalidaConsultarMatriculaFiltrosTierras ltscmft_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltscmft_return            = null;

		try
		{
			if(atecmft_entrada != null)
			{
				//TODO Pendiente validación ly
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculaFiltrosTierras", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltscmft_return            = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculaFiltrosTierras", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltscmft_return            = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscmft_return == null)
			ltscmft_return = new TipoSalidaConsultarMatriculaFiltrosTierras(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, null
				);

		return ltscmft_return;
	}

	/**
	 * Consultar matricula info catastral.
	 *
	 * @param atecmic_entrada de atecmic entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar matricula I catastral
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarMatriculaICatastral consultarMatriculaInfoCatastral(
	    TipoEntradaConsultarMatriculaInfoCatastral atecmic_entrada, String as_userId, String localIpAddress,
	    String                                     as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		BigInteger                             lbi_codigoMensaje;
		String                                 ls_descripcionMensaje;
		TipoSalidaConsultarMatriculaICatastral ltscmic_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltscmic_return            = null;

		try
		{
			if(atecmic_entrada != null)
			{
				PredioRegistro lpr_predioRegistro;

				lpr_predioRegistro = new PredioRegistro(atecmic_entrada);

				if(lpr_predioRegistro != null)
				{
					Collection<PredioRegistro> lcpr_cllPredioRegistro;

					lcpr_cllPredioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
							                               .findByNumeroPredialNupre(lpr_predioRegistro);

					if(CollectionUtils.isValidCollection(lcpr_cllPredioRegistro))
					{
						Collection<TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula> lctscmlm_collection;

						lctscmlm_collection = new LinkedList<TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula>();

						for(PredioRegistro lp_tmp : lcpr_cllPredioRegistro)
						{
							if(lp_tmp != null)
							{
								long   ll_idMatriculaPrincipal;
								String ls_idCirculoPrincipal;
								String ls_numeroPredialPrincipal;

								ll_idMatriculaPrincipal       = lp_tmp.getIdMatricula();
								ls_idCirculoPrincipal         = lp_tmp.getIdCirculo();
								ls_numeroPredialPrincipal     = lp_tmp.getNumeroPredial();

								if(!StringUtils.isValidString(ls_numeroPredialPrincipal))
									propagarErrorNumeroPredial(ls_idCirculoPrincipal, ll_idMatriculaPrincipal, false);

								lctscmlm_collection.add(
								    new TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula(
								        ls_idCirculoPrincipal, NumericUtils.getInt(ll_idMatriculaPrincipal),
								        EstadoCommon.NO, ls_numeroPredialPrincipal
								    )
								);

								{
									Collection<PredioSegregado> lcps_predioSegregado;

									lcps_predioSegregado = DaoCreator.getPredioSegregadoDAO(ldm_manager)
											                             .findNumPredialByPadre(
											    lp_tmp.getIdCirculo(), lp_tmp.getIdMatricula()
											);

									if(CollectionUtils.isValidCollection(lcps_predioSegregado))
									{
										for(PredioSegregado lps_tmp : lcps_predioSegregado)
										{
											if(lps_tmp != null)
											{
												long   ll_idMatriculaDerivada;
												String ls_idCirculoDerivada;
												String ls_numeroPredialDerivada;

												ll_idMatriculaDerivada       = NumericUtils.getLong(
													    lps_tmp.getIdMatricula1()
													);
												ls_idCirculoDerivada         = lps_tmp.getIdCirculo1();
												ls_numeroPredialDerivada     = lps_tmp.getNumeroPredial();

												if(!StringUtils.isValidString(ls_numeroPredialDerivada))
													propagarErrorNumeroPredial(
													    ls_idCirculoDerivada, ll_idMatriculaDerivada, true
													);

												lctscmlm_collection.add(
												    new TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula(
												        ls_idCirculoDerivada,
												        NumericUtils.getInt(ll_idMatriculaDerivada), EstadoCommon.SI,
												        ls_numeroPredialDerivada
												    )
												);
											}
										}
									}
								}
							}
						}

						if(CollectionUtils.isValidCollection(lctscmlm_collection))
						{
							TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula[] ltscmlmm_listaMatriculas;
							int                                                              li_count;

							ltscmlmm_listaMatriculas     = new TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula[lctscmlm_collection
									.size()];
							li_count                     = 0;

							for(TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula ltscmlmm_tmp : lctscmlm_collection)
							{
								if(ltscmlmm_tmp != null)
								{
									ltscmlmm_listaMatriculas[li_count] = new TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula(
										    ltscmlmm_tmp.getCodCirculoRegistral(),
										    ltscmlmm_tmp.getNumMatriculaInmobiliaria(), ltscmlmm_tmp.getEsDerivada(),
										    ltscmlmm_tmp.getNumPredial()
										);

									li_count++;
								}
							}

							ltscmic_return = new TipoSalidaConsultarMatriculaICatastral(
								    lbi_codigoMensaje.toString(), "OK", ltscmlmm_listaMatriculas
								);
						}
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_CONSULTA_TODAS_MATRICULAS));
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculaInfoCatastral", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltscmic_return            = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculaInfoCatastral", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltscmic_return            = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscmic_return == null)
			ltscmic_return = new TipoSalidaConsultarMatriculaICatastral(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, null
				);

		return ltscmic_return;
	}

	/**
	 * Consultar oficinas origen tipo.
	 *
	 * @param atecoot_entrada de atecoot entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar oficinas origen tipo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarOficinasOrigenTipo consultarOficinasOrigenTipo(
	    TipoEntradaConsultarOficinasOrigenTipo atecoot_entrada, String as_userId, String localIpAddress,
	    String                                 as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                            ldm_manager;
		BigInteger                            lbi_codigoMensaje;
		String                                ls_descripcionMensaje;
		TipoSalidaConsultarOficinasOrigenTipo ltscoot_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltscoot_return            = null;

		try
		{
			if(atecoot_entrada != null)
			{
				OficinaOrigen loo_oficinaOrigen;

				loo_oficinaOrigen = new OficinaOrigen(atecoot_entrada);

				if(loo_oficinaOrigen != null)
				{
					Collection<OficinaOrigen> lcoo_orifinasOrigen;

					lcoo_orifinasOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
							                            .findByIdOficinaTipoNombreParcial(loo_oficinaOrigen);

					if(CollectionUtils.isValidCollection(lcoo_orifinasOrigen))
					{
						TipoSalidaConsultarOficinasOrigenTipoListaOficinasOrigenOficinaOrigen[] ltscootloooo_listaOficinasOrigen;
						int                                                                     li_countOficinaOrigen;

						ltscootloooo_listaOficinasOrigen     = new TipoSalidaConsultarOficinasOrigenTipoListaOficinasOrigenOficinaOrigen[lcoo_orifinasOrigen
								.size()];
						li_countOficinaOrigen                = 0;

						for(OficinaOrigen loo_tmp : lcoo_orifinasOrigen)
						{
							if(loo_tmp != null)
							{
								ltscootloooo_listaOficinasOrigen[li_countOficinaOrigen] = new TipoSalidaConsultarOficinasOrigenTipoListaOficinasOrigenOficinaOrigen(
									    loo_tmp.getIdOficinaOrigen(), loo_tmp.getNombre()
									);

								li_countOficinaOrigen++;
							}
						}

						ltscoot_return = new TipoSalidaConsultarOficinasOrigenTipo(
							    lbi_codigoMensaje.toString(), "OK", ltscootloooo_listaOficinasOrigen
							);
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_OFICINA_ORIGEN));
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarOficinasOrigenTipo", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltscoot_return            = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarOficinasOrigenTipo", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltscoot_return            = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscoot_return == null)
			ltscoot_return = new TipoSalidaConsultarOficinasOrigenTipo(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, null
				);

		return ltscoot_return;
	}

	/**
	 * Inactivar alerta.
	 *
	 * @param ateia_entrada de ateia entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida inactivar alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaInactivarAlerta inactivarAlerta(
	    TipoEntradaInactivarAlerta ateia_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "OK";

		try
		{
			if(ateia_entrada != null)
			{
				AlertaTInactivacion lati_alertaTInactivacion;

				lati_alertaTInactivacion = new AlertaTInactivacion(ateia_entrada);

				if(lati_alertaTInactivacion != null)
				{
					String ls_motivo;

					ls_motivo = lati_alertaTInactivacion.getMotivoInactivacion();

					if(StringUtils.isValidString(ls_motivo))
					{
						if(ls_motivo.equalsIgnoreCase(TipoEntradaInactivarAlertaIdMotivo._EQUIVOCACION))
						{
							if(!StringUtils.isValidString(lati_alertaTInactivacion.getTextoInactivacion()))
								throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTRANDO_ACTIVACION));
						}
						else if(ls_motivo.equalsIgnoreCase(TipoEntradaInactivarAlertaIdMotivo._TERMINACION))
						{
							if(
							    !StringUtils.isValidString(lati_alertaTInactivacion.getDocumentoTipo())
								    || (lati_alertaTInactivacion.getDocumentoFecha() == null)
								    || !StringUtils.isValidString(lati_alertaTInactivacion.getDocumentoNumero())
								    || !StringUtils.isValidString(lati_alertaTInactivacion.getDocumentoSGDId())
								    || !StringUtils.isValidString(lati_alertaTInactivacion.getDocumentoSGDDocName())
							)
								throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTRANDO_ACTIVACION));
						}

						{
							lati_alertaTInactivacion.setIdUsuarioCreacion(as_userId);
							lati_alertaTInactivacion.setIpCreacion(as_remoteIp);

							DaoCreator.getAlertaTInactivacionDAO(ldm_manager).insert(lati_alertaTInactivacion);

							AlertaTierras lat_alertaTierras;

							lat_alertaTierras = DaoCreator.getAlertaTierrasDAO(ldm_manager)
									                          .findById(lati_alertaTInactivacion.getIdAlertaTierras());

							if(lat_alertaTierras != null)
							{
								lat_alertaTierras.setEstadoAlerta(EstadoCommon.INACTIVA);
								lat_alertaTierras.setFechaFinAlerta(obtenerDateDesdeCalendar(Calendar.getInstance()));
								lat_alertaTierras.setIdUsuarioModificacion(as_userId);
								lat_alertaTierras.setIdUsuarioModificacion(as_remoteIp);

								DaoCreator.getAlertaTierrasDAO(ldm_manager).update(lat_alertaTierras);
							}
							else
								throw new B2BException(addErrorAT(ErrorKeys.ERROR_ALERTA_NO_EXISTE));
						}
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTRANDO_ACTIVACION));
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("inactivarAlerta", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("inactivarAlerta", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaInactivarAlerta ltsia_return;

			ltsia_return = new TipoSalidaInactivarAlerta(lbi_codigoMensaje.toString(), ls_descripcionMensaje);

			return ltsia_return;
		}
	}

	/**
	 * Inscribir alerta cabecera.
	 *
	 * @param ateiac_entrada de ateiac entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida inscribir alerta cabecera
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaInscribirAlertaCabecera inscribirAlertaCabecera(
	    TipoEntradaInscribirAlertaCabecera ateiac_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                        ldm_manager;
		BigInteger                        lbi_codigoMensaje;
		String                            ls_descripcionMensaje;
		TipoSalidaInscribirAlertaCabecera ltsiac_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltsiac_return             = null;

		try
		{
			if(ateiac_entrada != null)
			{
				AlertaTierras lat_alertaTierras;

				lat_alertaTierras = new AlertaTierras(ateiac_entrada);

				{
					lat_alertaTierras.setDocumentoFecha(obtenerDateDesdeCalendar(ateiac_entrada.getFechaDocumento()));
					lat_alertaTierras.setFechaRadicado(
					    obtenerDateDesdeCalendar(ateiac_entrada.getProcesoFechaRadicado())
					);
				}

				if(lat_alertaTierras != null)
				{
					AlertaTierrasDAO lat_DAO;
					AlertaTierras    lat_alertaTierrasByDocNumero;

					lat_DAO                          = DaoCreator.getAlertaTierrasDAO(ldm_manager);
					lat_alertaTierrasByDocNumero     = lat_DAO.findByDocumento(lat_alertaTierras, false);

					if(lat_alertaTierrasByDocNumero == null)
					{
						{
							EntidadesEspeciales les_entidadEspecial;

							les_entidadEspecial = DaoCreator.getEntidadesEspecialesDAO(ldm_manager)
									                            .findById(lat_alertaTierras.getIdEntidad());

							if(les_entidadEspecial == null)
								throw new B2BException(addErrorAT(ErrorKeys.ERROR_ENTIDAD_NO_VALIDA));
						}

						long ll_idAlertaTierra;

						lat_alertaTierras.setEstadoAlerta(EstadoCommon.INGRESANDO);
						lat_alertaTierras.setIdUsuarioCreacion(ateiac_entrada.getIdUsuario());
						lat_alertaTierras.setIpCreacion(localIpAddress);

						ll_idAlertaTierra = lat_DAO.insert(lat_alertaTierras);

						{
							String ls_nitComunidadEtnica;

							ls_nitComunidadEtnica = ateiac_entrada.getNitComunidadEtnica();

							if(StringUtils.isValidString(ls_nitComunidadEtnica))
							{
								ComunidadesEtnicas lce_comunidadEtnica;

								lce_comunidadEtnica = DaoCreator.getComunidadesEtnicasDAO(ldm_manager)
										                            .findByTipoNumeroDoc(
										    IdentificadoresCommon.NIT, ls_nitComunidadEtnica
										);

								if(lce_comunidadEtnica != null)
								{
									long                ll_idComunidadEtnica;
									AlertaTComunidadDAO latc_DAO;
									AlertaTComunidad    latc_alertaTComunidad;

									ll_idComunidadEtnica      = lce_comunidadEtnica.getIdComunidad();
									latc_DAO                  = DaoCreator.getAlertaTComunidadDAO(ldm_manager);
									latc_alertaTComunidad     = latc_DAO.findById(
										    ll_idAlertaTierra, ll_idComunidadEtnica
										);

									if(latc_alertaTComunidad == null)
									{
										latc_alertaTComunidad = new AlertaTComunidad();

										latc_alertaTComunidad.setIdAlertaTierra(ll_idAlertaTierra);
										latc_alertaTComunidad.setIdComunidadEtnica(ll_idComunidadEtnica);
										latc_alertaTComunidad.setIdUsuarioCreacion(ateiac_entrada.getIdUsuario());
										latc_alertaTComunidad.setIpCreacion(as_remoteIp);

										latc_DAO.insert(latc_alertaTComunidad);
									}
								}
							}
						}

						if(ll_idAlertaTierra > 0)
							ltsiac_return = new TipoSalidaInscribirAlertaCabecera(
								    lbi_codigoMensaje.toString(), "OK", NumericUtils.getInt(ll_idAlertaTierra)
								);
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_DOCUMENTO_ALERTA_ACTIVA));
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("inscribirAlertaCabecera", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltsiac_return             = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("inscribirAlertaCabecera", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltsiac_return             = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsiac_return == null)
			ltsiac_return = new TipoSalidaInscribirAlertaCabecera(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, 0
				);

		return ltsiac_return;
	}

	/**
	 * Rechazar correccion alerta.
	 *
	 * @param aterca_entrada de aterca entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida rechazar correccion alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaRechazarCorreccionAlerta rechazarCorreccionAlerta(
	    TipoEntradaRechazarCorreccionAlerta aterca_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "OK";

		try
		{
			if(aterca_entrada != null)
			{
				long ll_idAlertaTierra;

				ll_idAlertaTierra = aterca_entrada.getIdAlerta();

				{
					AlertaTierrasDAO lat_DAO;
					AlertaTierras    lat_alertaTierras;

					lat_DAO               = DaoCreator.getAlertaTierrasDAO(ldm_manager);
					lat_alertaTierras     = lat_DAO.findById(ll_idAlertaTierra);

					if(lat_alertaTierras != null)
					{
						{
							lat_alertaTierras.setEstadoAlerta(EstadoCommon.ACTIVO_TXT);
							lat_alertaTierras.setIdUsuarioModificacion(aterca_entrada.getIdUsuario());
							lat_alertaTierras.setIpModificacion(as_remoteIp);

							lat_DAO.update(lat_alertaTierras);
						}

						{
							SolicitudCorreccionDAO lsc_DAO;
							SolicitudCorreccion    lsc_solicitudCorreccion;

							lsc_DAO                     = DaoCreator.getSolicitudCorreccionBNGDAO(ldm_manager);
							lsc_solicitudCorreccion     = lsc_DAO.findById(ll_idAlertaTierra);

							if(lsc_solicitudCorreccion != null)
							{
								lsc_solicitudCorreccion.setEstado(EstadoCommon.REVISADA);
								lsc_solicitudCorreccion.setTexto(aterca_entrada.getTexto());
								lsc_solicitudCorreccion.setIdUsuarioModificacion(aterca_entrada.getIdUsuario());
								lsc_solicitudCorreccion.setIpModificacion(as_remoteIp);

								lsc_DAO.update(lsc_solicitudCorreccion);
							}
							else
								throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTRO_RECHAZO_CORRECCION_ALERTA));
						}
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTRO_RECHAZO_CORRECCION_ALERTA));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("rechazarCorreccionAlerta", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("rechazarCorreccionAlerta", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaRechazarCorreccionAlerta ltsrca_return;

			ltsrca_return = new TipoSalidaRechazarCorreccionAlerta(lbi_codigoMensaje.toString(), ls_descripcionMensaje);

			return ltsrca_return;
		}
	}

	/**
	 * Remover matricula alerta.
	 *
	 * @param aterma_entrada de aterma entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida remover matricula alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaRemoverMatriculaAlerta removerMatriculaAlerta(
	    TipoEntradaRemoverMatriculaAlerta aterma_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "OK";

		try
		{
			if(aterma_entrada != null)
			{
				AlertaTPredio latp_alertaTPredio;

				latp_alertaTPredio = new AlertaTPredio(aterma_entrada);

				if(latp_alertaTPredio != null)
				{
					AlertaTPredioDAO latp_DAO;

					latp_DAO     = DaoCreator.getAlertaTPredioDAO(ldm_manager);

					latp_alertaTPredio = latp_DAO.findById(
						    latp_alertaTPredio.getIdAlertaTierras(), latp_alertaTPredio.getIdCirculo(),
						    latp_alertaTPredio.getIdMatricula()
						);

					if(latp_alertaTPredio != null)
					{
						String ls_estado;

						ls_estado = latp_alertaTPredio.getEstado();

						if(StringUtils.isValidString(ls_estado) && ls_estado.equalsIgnoreCase(EstadoCommon.INGRESANDO))
							latp_DAO.delete(latp_alertaTPredio);
						else
							throw new B2BException(addErrorAT(ErrorKeys.ERROR_REMOVER_MATRICULA));
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_REMOVER_MATRICULA));
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("removerMatriculaAlerta", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("removerMatriculaAlerta", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaRemoverMatriculaAlerta ltsrca_return;

			ltsrca_return = new TipoSalidaRemoverMatriculaAlerta(lbi_codigoMensaje.toString(), ls_descripcionMensaje);

			return ltsrca_return;
		}
	}

	/**
	 * Crear proc antiguo sistema tierras.
	 *
	 * @param atecpast_entrada de atecpast entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida crear proc antiguo sistema tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaCrearProcAntiguoSistemaTierras crearProcAntiguoSistemaTierras(
	    TipoEntradaCrearProcAntiguoSistemaTierras atecpast_entrada, String as_userId, String localIpAddress,
	    String                                    as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                               ldm_manager;
		BigInteger                               lbi_codigoMensaje;
		String                                   ls_descripcionMensaje;
		TipoSalidaCrearProcAntiguoSistemaTierras ltscpast_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltscpast_return           = null;

		try
		{
			if(atecpast_entrada != null)
			{
				ProcAntSistemaAT lpasat_procAntSistemaAT;

				lpasat_procAntSistemaAT = new ProcAntSistemaAT(atecpast_entrada);

				if(lpasat_procAntSistemaAT != null)
				{
					long ll_anio;

					ll_anio = lpasat_procAntSistemaAT.getAnio();

					if((ll_anio >= 1942) && (ll_anio <= 1980))
					{
						long ll_idAntSistema;

						{
							ProcAntSistemaAT lcpasat_tmp;

							lcpasat_tmp = DaoCreator.getProcAntSistemaATDAO(ldm_manager)
									                    .findLastByIdAlertaTierra(
									    lpasat_procAntSistemaAT.getIdAlertaTierra()
									);

							if(lcpasat_tmp != null)
								ll_idAntSistema = lcpasat_tmp.getIdAntSistema() + 1;
							else
								ll_idAntSistema = 1;
						}

						lpasat_procAntSistemaAT.setIdAntSistema(ll_idAntSistema);
						//Se inserta identificador de turno temporalmente porque en BD está el campo obligatorio y no debería ser así
						lpasat_procAntSistemaAT.setIdTurno(IdentificadoresCommon.TURNO);
						lpasat_procAntSistemaAT.setEstadoProceso(IdentificadoresCommon.ENTRAMITE);
						lpasat_procAntSistemaAT.setIdUsuarioCreacion(as_userId);
						lpasat_procAntSistemaAT.setIpCreacion(as_remoteIp);

						DaoCreator.getProcAntSistemaATDAO(ldm_manager).insert(lpasat_procAntSistemaAT);

						if(ll_idAntSistema > 0)
							ltscpast_return = new TipoSalidaCrearProcAntiguoSistemaTierras(
								    lbi_codigoMensaje.toString(), "OK", NumericUtils.getInt(ll_idAntSistema)
								);
					}
					else
						throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("crearProcAntiguoSistemaTierras", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltscpast_return           = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("crearProcAntiguoSistemaTierras", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltscpast_return           = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscpast_return == null)
			ltscpast_return = new TipoSalidaCrearProcAntiguoSistemaTierras(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, 0
				);

		return ltscpast_return;
	}

	/**
	 * Agregar lista matriculas alerta.
	 *
	 * @param atealma_entrada de atealma entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida agregar lista matriculas alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaAgregarListaMatriculasAlerta agregarListaMatriculasAlerta(
	    TipoEntradaAgregarListaMatriculasAlerta atealma_entrada, String as_userId, String localIpAddress,
	    String                                  as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "OK";

		try
		{
			if(atealma_entrada != null)
			{
				int li_idAlertaTierras;

				li_idAlertaTierras = atealma_entrada.getIdAlerta();

				{
					TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula[] ltealmalmm_listaMatriculas;
					AlertaTPredio                                                     latp_alertaTPredio;

					ltealmalmm_listaMatriculas     = atealma_entrada.getListaMatriculas();
					latp_alertaTPredio             = null;

					if(CollectionUtils.isValidCollection(ltealmalmm_listaMatriculas))
					{
						AlertaTPredioDAO latp_DAO;

						latp_DAO = DaoCreator.getAlertaTPredioDAO(ldm_manager);

						for(TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula ltealmalmm_tmp : ltealmalmm_listaMatriculas)
						{
							if(ltealmalmm_tmp != null)
							{
								latp_alertaTPredio = new AlertaTPredio(ltealmalmm_tmp);

								if(latp_alertaTPredio != null)
								{
									latp_alertaTPredio.setIdAlertaTierras(li_idAlertaTierras);

									if(latp_DAO.findById(latp_alertaTPredio) == null)
									{
										latp_alertaTPredio.setIdUsuarioCreacion(as_userId);
										latp_alertaTPredio.setIpCreacion(as_remoteIp);

										latp_DAO.insert(latp_alertaTPredio);
									}
								}
							}
						}
					}
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("agregarListaMatriculasAlerta", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("agregarListaMatriculasAlerta", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaAgregarListaMatriculasAlerta ltsalma_return;

			ltsalma_return = new TipoSalidaAgregarListaMatriculasAlerta(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje
				);

			return ltsalma_return;
		}
	}

	/**
	 * Listar proc antiguo sistema.
	 *
	 * @param atelpas_entrada de atelpas entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida listar proc antiguo sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaListarProcAntiguoSistema listarProcAntiguoSistema(
	    TipoEntradaListarProcAntiguoSistema atelpas_entrada, String as_userId, String localIpAddress, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		BigInteger                         lbi_codigoMensaje;
		String                             ls_descripcionMensaje;
		TipoSalidaListarProcAntiguoSistema ltslpas_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltslpas_return            = null;

		try
		{
			if(atelpas_entrada != null)
			{
				Collection<ProcAntSistemaAT> lcpat_cllProcAntSistemaAT;

				lcpat_cllProcAntSistemaAT = DaoCreator.getProcAntSistemaATDAO(ldm_manager)
						                                  .findAllByIdAlertaTierra(atelpas_entrada.getIdAlerta());

				if(CollectionUtils.isValidCollection(lcpat_cllProcAntSistemaAT))
				{
					TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS[] ltslpaslpaspas_listaProcesosAS;
					int                                                          li_count;

					ltslpaslpaspas_listaProcesosAS     = new TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS[lcpat_cllProcAntSistemaAT
							.size()];
					li_count                           = 0;

					for(ProcAntSistemaAT lpat_tmp : lcpat_cllProcAntSistemaAT)
					{
						if(lpat_tmp != null)
						{
							long ll_anio;

							ll_anio = lpat_tmp.getAnio();

							if((ll_anio >= 1492) && (ll_anio <= 1980))
								ltslpaslpaspas_listaProcesosAS[li_count] = new TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS(
									    NumericUtils.getInt(lpat_tmp.getIdAlertaTierra()),
									    lpat_tmp.getNomCirculoRegistral(), lpat_tmp.getNomPais(),
									    lpat_tmp.getNomDepartamento(), lpat_tmp.getNomMunicipio(),
									    lpat_tmp.getTipoPredio(), NumericUtils.getInt(lpat_tmp.getNumeroLibro()),
									    NumericUtils.getInt(lpat_tmp.getNumeroTomo()), lpat_tmp.getTipoPartida(),
									    NumericUtils.getInt(lpat_tmp.getNumeroPartida()),
									    NumericUtils.getInt(lpat_tmp.getNumeroFolio()), NumericUtils.getInt(ll_anio),
									    lpat_tmp.getNombrePredio(), NumericUtils.getInteger(lpat_tmp.getNumeroPredio())
									);
						}

						li_count++;
					}

					ltslpas_return = new TipoSalidaListarProcAntiguoSistema(
						    lbi_codigoMensaje.toString(), "OK", ltslpaslpaspas_listaProcesosAS
						);
				}
				else
					throw new B2BException(addErrorAT(ErrorKeys.ERROR_REGISTROS_CRITERIOS));
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("listarProcAntiguoSistema", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltslpas_return            = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("listarProcAntiguoSistema", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltslpas_return            = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltslpas_return == null)
		{
			TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS[] ltslpaslpaspas_listaProcesosAS;

			ltslpaslpaspas_listaProcesosAS     = new TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS[1];

			ltslpaslpaspas_listaProcesosAS[0]     = new TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS(
				    0, "", "", "", "", "", 0, 0, "", 0, 0, 0, null, null
				);

			ltslpas_return = new TipoSalidaListarProcAntiguoSistema(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje, ltslpaslpaspas_listaProcesosAS
				);
		}

		return ltslpas_return;
	}

	/**
	 * Eliminar proc antiguo sistema.
	 *
	 * @param ateepas_entrada de ateepas entrada
	 * @param as_userId de as user id
	 * @param localIpAddress de local ip address
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida eliminar proc antiguo sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaEliminarProcAntiguoSistema eliminarProcAntiguoSistema(
	    TipoEntradaEliminarProcAntiguoSistema ateepas_entrada, String as_userId, String localIpAddress,
	    String                                as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "OK";

		try
		{
			if(ateepas_entrada != null)
			{
				AlertaTierras lat_alertaTierras;

				lat_alertaTierras = DaoCreator.getAlertaTierrasDAO(ldm_manager).findById(ateepas_entrada.getIdAlerta());

				if(lat_alertaTierras != null)
				{
					String ls_estado;

					ls_estado = lat_alertaTierras.getEstadoAlerta();

					if(StringUtils.isValidString(ls_estado) && ls_estado.equalsIgnoreCase(EstadoCommon.INGRESANDO))
					{
						ProcAntSistemaAT lpasat_procAntSistemaAT;

						lpasat_procAntSistemaAT = new ProcAntSistemaAT(ateepas_entrada);

						if(lpasat_procAntSistemaAT != null)
							DaoCreator.getProcAntSistemaATDAO(ldm_manager).delete(lpasat_procAntSistemaAT);
					}
				}
			}
			else
				throw new B2BException(addErrorAT(ErrorKeys.ERROR_PARAMETRO_INVALIDO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("eliminarProcAntiguoSistema", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("eliminarProcAntiguoSistema", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaEliminarProcAntiguoSistema ltsepas_return;

			ltsepas_return = new TipoSalidaEliminarProcAntiguoSistema(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje
				);

			return ltsepas_return;
		}
	}
}
