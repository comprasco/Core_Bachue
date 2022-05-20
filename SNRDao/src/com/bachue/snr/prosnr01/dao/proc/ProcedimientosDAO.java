package com.bachue.snr.prosnr01.dao.proc;

import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.procedimientos.RespuestaConsultaTarifa;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.PagoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.InstanciaConsulta;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Clase que contiene todos los procedimientos almacenados del sistema para ser ejecutados.
 *
 * @author Julian Vaca
 */
public class ProcedimientosDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_PROC_CREAR_MATRICULA_INFORMACION. */
	private static final String cs_PROC_CREAR_MATRICULA_INFORMACION = "BEGIN PKG_REGISTRO.PROC_CREAR_MATRICULA_INFORMACION(?,?,?,?,?); END;";

	/** Constante cs_PROC_CREAR_MATRICULA_INFORMACION_ANOTACION. */
	private static final String cs_PROC_CREAR_MATRICULA_INFORMACION_ANOTACION = "BEGIN PKG_REGISTRO.PROC_CREAR_MATRICULA_INFORMACION_ANOTACION(?,?,?,?,?,?,?,?,?,?); END;";

	/** Constante cs_PROC_LLA_CREAR_ETAPA_TRG. */
	private static final String cs_PROC_LLA_CREAR_ETAPA_TRG = "BEGIN PKG_TRANSVERSALES.PROC_LLA_CREAR_ETAPA_TRG(?,?,?,?,?,?,?,?); END;";

	/** Constante PROC_MENSAJE_COMUNICACION. */
	private static final String cs_PROC_MENSAJE_COMUNICACION = "BEGIN PKG_TRANSVERSALES.PROC_MENSAJE_COMUNICACION(?,?,?,?,?); END;";

	/** Constante cs_PROC_CREAR_SOLICITUD_DESDE_OTRA. */
	private static final String cs_PROC_CREAR_SOLICITUD_DESDE_OTRA = "BEGIN PKG_REGISTRO.PROC_CREAR_SOLICITUD_DESDE_OTRA(?,?,?,?,?,?,?); END;";

	/** Constante cs_PROC_TAREA_NOCTURNA. */
	private static final String cs_PROC_TAREA_NOCTURNA = "BEGIN PKG_REGISTRO.PROC_TAREA_NOCTURNA(); END;";

	/** Constante cs_PROC_CERRAR_FOLIO. */
	private static final String cs_PROC_CERRAR_FOLIO = "BEGIN PKG_REGISTRO.PROC_CERRAR_FOLIO(?,?,?,?,?); END;";

	/** Constante cs_PROC_CORRECCION. */
	private static final String cs_PROC_CORRECCION = "BEGIN PKG_REGISTRO.PROC_CORRECCION(?,?,?,?,?); END;";

	/** Constante cs_PROC_CREAR_COMUNICACION_NOTIFICACIONES. */
	private static final String cs_PROC_CREAR_COMUNICACION_NOTIFICACIONES = "BEGIN PKG_REGISTRO.PROC_CREAR_COMUNICACION_NOTIFICACIONES(?,?,?,?,?); END;";

	/** Constante cs_PROC_VALIDA_NOTIFICACION_TERCEROS. */
	private static final String cs_PROC_VALIDA_NOTIFICACION_TERCEROS = "BEGIN PKG_REGISTRO.PROC_VALIDA_NOTIFICACION_TERCEROS(?,?,?,?,?); END;";

	/** Constante cs_PROC_CONSULTA_TARIFA. */
	private static final String cs_PROC_CONSULTA_TARIFA = "BEGIN PKG_FINANCIEROS.PROC_CONSULTA_TARIFA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";

	/** Constante cs_PROC_CONSULTAS. */
	private static final String cs_PROC_CONSULTAS = "BEGIN PKG_CONSULTAS.PROC_CONSULTAS(?,?,?,?,?); END;";

	/** Constante cs_PROC_CREAR_MATRICULA_ANT_SISTEMA. */
	private static final String cs_PROC_CREAR_MATRICULA_ANT_SISTEMA = "BEGIN PKG_REGISTRO.PROC_CREAR_MATRICULA_ANT_SISTEMA(?,?,?,?,?); END;";

	/** Constante cs_PROC_CREAR_TURNO. */
	private static final String cs_PROC_CREAR_TURNO = "BEGIN PKG_REGISTRO.PROC_CREAR_TURNO(?,?,?,?,?,?); END;";

	/** Constante cs_PROC_DESBLOQUEO_MATRICULAS. */
	private static final String cs_PROC_DESBLOQUEO_MATRICULAS = "BEGIN PKG_REGISTRO.PROC_DESBLOQUEO_MATRICULAS(?,?,?,?,?); END;";

	/** Constante cs_PROC_LIQUIDACION. */
	private static final String cs_PROC_LIQUIDACION = "BEGIN PKG_FINANCIEROS.PROC_LIQUIDACION(?,?,?,?,?,?,?,?); END;";

	/** Constante cs_PROC_REALIZAR_REGISTRO. */
	private static final String cs_PROC_REALIZAR_REGISTRO = "BEGIN PKG_REGISTRO.PROC_REALIZAR_REGISTRO(?,?,?,?,?); END;";

	/** Constante cs_PROC_RECEPCION_DOCUMENTOS. */
	private static final String cs_PROC_RECEPCION_DOCUMENTOS = "BEGIN PKG_REGISTRO.PROC_RECEPCION_DOCUMENTOS(?,?,?,?,?,?); END;";

	/** Constante cs_PROC_COPIA_ANOTACION_TEMPORAL. */
	private static final String cs_PROC_COPIA_ANOTACION_TEMPORAL = "BEGIN PKG_REGISTRO.PROC_COPIA_ANOTACION_TEMPORAL(?,?,?,?,?,?,?,?); END;";

	/** Constante cs_PROC_CREAR_ANOTACION_PREDIO. */
	private static final String cs_PROC_CREAR_ANOTACION_PREDIO = "BEGIN PKG_REGISTRO.PROC_CREAR_ANOTACION_PREDIO(?,?,?,?,?,?); END;";

	/** Constante cs_PROC_RPT_DIARIO_RADICADOR. */
	private static final String cs_PROC_RPT_DIARIO_RADICADOR = "BEGIN PKG_REPORTES.PROC_RPT_DIARIO_RADICADOR(?,?,?,?,?,?); END;";

	/** Constante cs_PROC_CREA_ETAPA. */
	private static final String cs_PROC_CREA_ETAPA = "BEGIN PKG_WORKFLOW.PROC_CREA_ETAPA(?,?,?,?,?,?,?,?,?,?); END;";

	/** Constante cs_PROC_REASIGNA_TURNO. */
	private static final String cs_PROC_REASIGNA_TURNO = "BEGIN PKG_REGISTRO.PROC_REASIGNA_TURNO(?,?,?,?,?,?,?,?,?,?,?); END;";

	/** Constante cs_PROC_REASIGNACION_APOYO_NACIONAL. */
	private static final String cs_PROC_REASIGNACION_APOYO_NACIONAL = "BEGIN PKG_TRANSVERSALES.PROC_REASIGNACION_APOYO_NACIONAL(?,?,?,?,?); END;";

	/** Constante cs_PROC_PAGAR_CUENTA_CUPO. */
	private static final String cs_PROC_PAGAR_CUENTA_CUPO = "BEGIN PKG_FINANCIEROS.PROC_PAGAR_CUENTA_CUPO(?,?,?,?,?,?,?,?,?,?); END;";

	/** Constante cs_PROC_COPIA_DEFINITIVA_TEMPORAL. */
	private static final String cs_PROC_COPIA_DEFINITIVA_TEMPORAL = "BEGIN PKG_REGISTRO.PROC_COPIA_DEFINITIVA_TEMPORAL(?,?,?,?,?); END;";

	/** Constante cs_PROC_EJECUTAR_TRASLADO. */
	private static final String cs_PROC_EJECUTAR_TRASLADO = "BEGIN PKG_TRANSVERSALES.PROC_EJECUTAR_TRASLADO(?,?,?,?,?); END;";

	/** Constante cs_PROC_ASIGNAR_MATRICULA_TRASLADO. */
	private static final String cs_PROC_ASIGNAR_MATRICULA_TRASLADO = "BEGIN PKG_TRANSVERSALES.PROC_ASIGNAR_MATRICULA_TRASLADO(?,?,?,?,?); END;";

	/** Constante cs_PROC_ELIMINA_MATRICULA_ANT_SISTEMA. */
	private static final String cs_PROC_ELIMINA_MATRICULA_ANT_SISTEMA = "BEGIN PKG_REGISTRO.PROC_ELIMINA_MATRICULA_ANT_SISTEMA(?,?,?,?,?,?); END;";

	/** Constante cs_PROC_VERIFICA_BITACORA_BLQ. */
	private static final String cs_PROC_VERIFICA_BITACORA_BLQ = "BEGIN PKG_TRANSVERSALES.PROC_VERIFICA_BITACORA_BLQ(?,?,?,?,?); END;";

	/** Constante cs_PROC_APRUEBA_FLUJO. */
	private static final String cs_PROC_APRUEBA_FLUJO = "BEGIN PKG_WORKFLOW.PROC_APRUEBA_FLUJO(?,?,?,?,?,?,?); END;";

	/** Constante cs_PROC_CRUCE_MU_CRPS. */
	private static final String cs_PROC_CRUCE_MU_CRPS = "BEGIN PKG_CONFRONTACION.PROC_CRUCE_MU_CRPS(?,?,?,?,?,?); END;";

	/**
	 * Instancia un nuevo objeto procedimientos DAO.
	 */
	public ProcedimientosDAO()
	{
	}

	/**
	 * Crear mariculas informacion.
	 *
	 * @param aore_datos correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void crearMariculasInformacion(RegistroCalificacion aore_datos)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		try
		{
			int    li_i;
			int    li_return;
			String ls_error;

			li_i          = 1;
			li_return     = 0;
			lcs_cs        = getConnection().prepareCall(cs_PROC_CREAR_MATRICULA_INFORMACION);

			setLong(lcs_cs, aore_datos.getIdTurnoHistoria(), li_i++);
			lcs_cs.setString(li_i++, aore_datos.getIdUsuarioModificacion());
			lcs_cs.setString(li_i++, aore_datos.getIpModificacion());
			lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_return     = lcs_cs.getInt(4);
			ls_error      = lcs_cs.getString(5);

			if(li_return < 0)
				throw new B2BException(
				    "Error en procedimiento PROC_CREAR_MATRICULA_INFORMACION :" + li_return + " :: " + ls_error
				);
		}
		catch(SQLException lse_e)
		{
			logError(this, "crearMariculasInformacion", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Crear mariculas informacion a partir de una anotación.
	 *
	 * @param aore_datos correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void crearMariculasInformacionAnotacion(RegistroCalificacion aore_datos)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		try
		{
			int    li_i;
			int    li_return;
			String ls_error;

			li_i          = 1;
			li_return     = 0;
			lcs_cs        = getConnection().prepareCall(cs_PROC_CREAR_MATRICULA_INFORMACION_ANOTACION);

			setLong(lcs_cs, aore_datos.getIdTurnoHistoria(), li_i++);
			lcs_cs.setString(li_i++, aore_datos.getIdSolicitud());
			lcs_cs.setString(li_i++, aore_datos.getIdAnotacionApertura());
			lcs_cs.setString(li_i++, aore_datos.getIdTurno());
			lcs_cs.setString(li_i++, aore_datos.getIdCirculo());
			setLong(lcs_cs, aore_datos.getIdMatricula(), li_i++);
			lcs_cs.setString(li_i++, aore_datos.getIdUsuarioModificacion());
			lcs_cs.setString(li_i++, aore_datos.getIpModificacion());
			lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_return     = lcs_cs.getInt(9);
			ls_error      = lcs_cs.getString(10);

			if(li_return < 0)
				throw new B2BException(
				    "Error en procedimiento PROC_CREAR_MATRICULA_INFORMACION_ANOTACION :" + li_return + " :: "
				    + ls_error
				);
		}
		catch(SQLException lse_e)
		{
			logError(this, "crearMariculasInformacionAnotacion", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Retorna el valor del objeto de tipo Crear solicitud desde otra.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto Solicitud
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String crearSolicitudDesdeOtra(Solicitud as_s)
	    throws B2BException
	{
		CallableStatement lcs_cs;
		String            ls_idSolicitud;

		ls_idSolicitud     = null;
		lcs_cs             = null;

		try
		{
			int li_i;
			int li_return;
			li_i = 1;

			String ls_error;

			li_return     = 0;

			lcs_cs = getConnection().prepareCall(cs_PROC_CREAR_SOLICITUD_DESDE_OTRA);

			lcs_cs.setString(li_i++, as_s.getIdSolicitud());
			lcs_cs.setString(li_i++, as_s.getIndVinculacion());
			lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
			lcs_cs.setString(li_i++, as_s.getIdUsuarioCreacion());
			lcs_cs.setString(li_i++, as_s.getIpCreacion());
			lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			ls_idSolicitud     = lcs_cs.getString(3);
			li_return          = lcs_cs.getInt(6);
			ls_error           = lcs_cs.getString(7);

			if(li_return < 0)
				throw new B2BException(
				    "Error en procedimiento PROC_CREAR_SOLICITUD_DESDE_OTRA :" + li_return + " :: " + ls_error
				);
		}
		catch(SQLException lse_e)
		{
			logError(this, "crearSolicitudDesdeOtra", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}

		return ls_idSolicitud;
	}

	/**
	 * Ejecutar tarea nocturna.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void ejecutarTareaNocturna()
	    throws B2BException
	{
		CallableStatement lcs_cs = null;

		try
		{
			lcs_cs = getConnection().prepareCall(cs_PROC_TAREA_NOCTURNA);

			lcs_cs.execute();
		}
		catch(SQLException lse_e)
		{
			logError(this, "ejecutarTareaNocturna", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Metódo de invocación al PROC_APRUEBA_FLUJO.
	 *
	 * @param as_idProceso <code>String</code> correspondiente al id proceso del flujo que será aprobado
	 * @param as_idSubProceso <code>String</code> correspondiente al id subProceso del flujo que será aprobado
	 * @param ai_version <code>int</code> correspondiente a la versión del flujo que será aprobado
	 * @param as_userId <code>String</code> correspondiente al usuario que realiza la acción
	 * @param as_remoteIp <code>String</code> correspondiente a la dirección ip del usuario que realiza la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procApruebaFlujo(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		CallableStatement lcs_cs = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso)
				    && (ai_version > 0) && StringUtils.isValidString(as_userId)
				    && StringUtils.isValidString(as_remoteIp)
			)
			{
				int li_i;
				int li_return;
				li_i = 1;

				String ls_error;

				li_return     = 0;

				lcs_cs = getConnection().prepareCall(cs_PROC_APRUEBA_FLUJO);

				lcs_cs.setString(li_i++, as_idProceso);
				lcs_cs.setString(li_i++, as_idSubProceso);
				lcs_cs.setInt(li_i++, ai_version);
				lcs_cs.setString(li_i++, as_userId);
				lcs_cs.setString(li_i++, as_remoteIp);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(6);
				ls_error      = lcs_cs.getString(7);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento PROC_APRUEBA_FLUJO :" + li_return + " :: " + ls_error
					);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "procApruebaFlujo", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Metodo encargado de invocar el procedimiento cs_PROC_ASIGNAR_MATRICULA_TRASLADO.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene los argumentos necesarios para invocar el procedimiento.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procAsignarMatriculaTraslado(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		try
		{
			if(ath_turnoHistoria != null)
			{
				int li_i;
				int li_return;
				li_i = 1;

				String ls_error;

				li_return     = 0;

				lcs_cs = getConnection().prepareCall(cs_PROC_ASIGNAR_MATRICULA_TRASLADO);

				setLong(lcs_cs, ath_turnoHistoria.getIdTurnoHistoria(), li_i++);
				lcs_cs.setString(li_i++, ath_turnoHistoria.getIdUsuarioModificacion());
				lcs_cs.setString(li_i++, ath_turnoHistoria.getIpModificacion());

				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(4);
				ls_error      = lcs_cs.getString(5);

				if(li_return < 0)
					throw new B2BException(li_return + " :: " + ls_error);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "procAsignarMatriculaTraslado", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Retorna el valor del objeto de tipo Proc cerrar folio.
	 *
	 * @param ath_arg correspondiente al valor del tipo de objeto TurnoHistoria
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int procCerrarFolio(TurnoHistoria ath_arg)
	    throws B2BException
	{
		int           li_return;
		LoggerHandler llh_logger;
		String        ls_error;

		li_return      = 0;
		llh_logger     = getLog();

		if(llh_logger.isDebugEnabled())
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append("procCerrarFolio: ");
			lsb_sb.append(ath_arg.getIdTurnoHistoria());

			llh_logger.debug(lsb_sb);
		}

		CallableStatement lcs_cs;
		ResultSet         lrs_rs;
		int               li_contador;

		lcs_cs          = null;
		lrs_rs          = null;
		li_contador     = 1;

		try
		{
			lcs_cs = getConnection().prepareCall(cs_PROC_CERRAR_FOLIO);

			setLong(lcs_cs, ath_arg.getIdTurnoHistoria(), li_contador++);
			lcs_cs.setString(li_contador++, ath_arg.getIdUsuarioCreacion());
			lcs_cs.setString(li_contador++, ath_arg.getIpCreacion());
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_return     = lcs_cs.getInt(4);
			ls_error      = lcs_cs.getString(5);

			if(li_return < 0)
				throw new B2BException("Error en procedimiento PROC_CERRAR_FOLIO : " + li_return + " :: " + ls_error);
		}
		catch(SQLException lse_e)
		{
			llh_logger.error("procCerrarFolio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lcs_cs);
		}

		return li_return;
	}

	/**
	 * Metódo de invocación al PROC_APRUEBA_FLUJO.
	 *
	 * @param as_idArchivo <code>String</code> correspondiente al id archivo que se enviará al procedimiento
	 * @param as_tipoProceso <code>String</code> correspondiente al tipo de proceso MANUAL o AUTOMATICO
	 * @param as_userId <code>String</code> correspondiente al usuario que realiza la acción
	 * @param as_remoteIp <code>String</code> correspondiente a LA IP DEL USUARIO QUE REALIZA LA ACCIÓN
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int procConfrontacionMulticashCPRS(
	    String as_idArchivo, String as_tipoProceso, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		int               li_return;
		CallableStatement lcs_cs;

		li_return     = -1;
		lcs_cs        = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idArchivo) && StringUtils.isValidString(as_tipoProceso)
				    && StringUtils.isValidString(as_userId) && StringUtils.isValidString(as_remoteIp)
			)
			{
				int    li_i;
				String ls_error;

				li_i       = 1;
				lcs_cs     = getConnection().prepareCall(cs_PROC_CRUCE_MU_CRPS);

				lcs_cs.setString(li_i++, as_idArchivo);
				lcs_cs.setString(li_i++, as_tipoProceso);
				lcs_cs.setString(li_i++, as_userId);
				lcs_cs.setString(li_i++, as_remoteIp);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(5);
				ls_error      = lcs_cs.getString(6);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento PROC_CRUCE_MU_CRPS :" + li_return + " :: " + ls_error
					);

				System.out.println("CONFRONTADO");
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "procConfrontacionMulticashCPRS", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}

		return li_return;
	}

	/**
	 * Invoca el PROC_CONSULTA_CATALOGOS.
	 *
	 * @param atecc_tecc Objeto contenedor de los parametros a utilizar como filtro en la busqueda
	 * @param as_nombreProc Nombre del procedimiento a llamar para la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TiposCatalogos> procConsultaCatalogos(
	    TipoEntradaConsultarCatalogos atecc_tecc, String as_nombreProc
	)
	    throws B2BException
	{
		Collection<TiposCatalogos> lctc_return;

		lctc_return = new LinkedList<TiposCatalogos>();

		if((atecc_tecc != null) && StringUtils.isValidString(as_nombreProc))
		{
			CallableStatement lcs_cs;
			ResultSet         lrs_data;

			lcs_cs       = null;
			lrs_data     = null;

			try
			{
				int           li_i;
				int           li_return;
				String        ls_error;
				StringBuilder lsb_query;

				li_i          = 1;
				li_return     = 0;
				lsb_query     = new StringBuilder("BEGIN ");

				lsb_query.append(as_nombreProc);
				lsb_query.append(" (?,?,?,?,?); END;");

				lcs_cs = getConnection().prepareCall(lsb_query.toString());

				lcs_cs.setString(li_i++, atecc_tecc.getCatalogo());
				lcs_cs.setString(li_i++, atecc_tecc.getParametro());
				lcs_cs.registerOutParameter(li_i++, OracleTypes.CURSOR);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(4);
				ls_error      = lcs_cs.getString(5);

				if(li_return < 0)
					throw new B2BException(ls_error);

				lrs_data = (ResultSet)lcs_cs.getObject(3);

				while(lrs_data.next())
					lctc_return.add(obtenerTipoCatalogo(lrs_data));
			}
			catch(SQLException lse_e)
			{
				logError(this, "procConsultaCatalogos", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);

				if(lrs_data != null)
					close(lrs_data);
			}
		}

		if(lctc_return.isEmpty())
			lctc_return = null;

		return lctc_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Proc consulta tarifa.
	 *
	 * @param as_idProceso correspondiente al valor del tipo de objeto String
	 * @param as_idSubProceso correspondiente al valor del tipo de objeto String
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param as_cedulaCatastral de as cedula catastral
	 * @param as_idUsuarioAccion correspondiente al valor del tipo de objeto String
	 * @param as_ipAccion correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto respuesta consulta tarifa
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RespuestaConsultaTarifa
	 */
	public RespuestaConsultaTarifa procConsultaTarifa(
	    String as_idProceso, String as_idSubProceso, String as_idCirculo, Long al_idMatricula, String as_cedulaCatastral,
	    String as_idUsuarioAccion, String as_ipAccion
	)
	    throws B2BException
	{
		CallableStatement       lcs_cs;
		RespuestaConsultaTarifa lrct_return;

		lcs_cs          = null;
		lrct_return     = null;

		try
		{
			int    li_i;
			int    li_retorno;
			String ls_mensaje;

			li_i           = 1;
			li_retorno     = 0;
			lcs_cs         = getConnection().prepareCall(cs_PROC_CONSULTA_TARIFA);

			lcs_cs.setString(li_i++, as_idProceso);
			lcs_cs.setString(li_i++, as_idSubProceso);
			lcs_cs.setString(li_i++, as_idCirculo);
			setLong(lcs_cs, al_idMatricula, li_i++);
			lcs_cs.setString(li_i++, as_cedulaCatastral);
			lcs_cs.setString(li_i++, null);
			setLong(lcs_cs, null, li_i++);
			setLong(lcs_cs, null, li_i++);
			setLong(lcs_cs, null, li_i++);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.NUMBER);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
			lcs_cs.setString(li_i++, as_idUsuarioAccion);
			lcs_cs.setString(li_i++, as_ipAccion);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.INTEGER);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_retorno     = lcs_cs.getInt(15);
			ls_mensaje     = lcs_cs.getString(16);

			lrct_return = new RespuestaConsultaTarifa(
				    lcs_cs.getBigDecimal(10), lcs_cs.getString(11), lcs_cs.getString(12),
				    NumericUtils.getInteger(li_retorno), ls_mensaje
				);
		}
		catch(SQLException lse_e)
		{
			logError(this, "procConsultaTarifa", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}

		return lrct_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Proc consultas.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int procConsultas(String as_idSolicitud, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		int           li_return;
		LoggerHandler llh_logger;
		String        ls_error;

		li_return      = 0;
		llh_logger     = getLog();

		if(llh_logger.isDebugEnabled())
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append("procConsultas: ");
			lsb_sb.append(as_idSolicitud);

			llh_logger.debug(lsb_sb);
		}

		CallableStatement lcs_cs;
		ResultSet         lrs_rs;
		int               li_contador;

		lcs_cs          = null;
		lrs_rs          = null;
		li_contador     = 1;

		try
		{
			lcs_cs = getConnection().prepareCall(cs_PROC_CONSULTAS);

			lcs_cs.setString(li_contador++, as_idSolicitud);
			lcs_cs.setString(li_contador++, as_userId);
			lcs_cs.setString(li_contador++, as_remoteIp);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);

			lcs_cs.execute();

			li_return     = lcs_cs.getInt(4);
			ls_error      = lcs_cs.getString(5);

			if(li_return < 0)
				throw new B2BException("Error en procedimiento PROC_CONSULTAS : " + li_return + " :: " + ls_error);
		}
		catch(SQLException lse_e)
		{
			llh_logger.error("procConsultas", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lcs_cs);
		}

		return li_return;
	}

	/**
	 * Metodo encargado de invocar el procedimiento PROC_COPIA_DEFINITIVO_TEMPORAL.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene los
	 * argumentos necesarios para invocar el procedimiento.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procCopiaDefinitivoTemporal(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		try
		{
			if(ath_turnoHistoria != null)
			{
				int li_i;
				int li_return;
				li_i = 1;

				String ls_error;

				li_return     = 0;

				lcs_cs = getConnection().prepareCall(cs_PROC_COPIA_DEFINITIVA_TEMPORAL);

				setLong(lcs_cs, ath_turnoHistoria.getIdTurnoHistoria(), li_i++);
				lcs_cs.setString(li_i++, ath_turnoHistoria.getIdUsuarioModificacion());
				lcs_cs.setString(li_i++, ath_turnoHistoria.getIpModificacion());

				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(li_i - 2);
				ls_error      = lcs_cs.getString(li_i - 1);

				if(li_return < 0)
					throw new B2BException(li_return + " :: " + ls_error);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "procCopiaDefinitivoTemporal", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Invoca a PROC_CORRECCION.
	 *
	 * @param al_idTurnoHistoria Objeto contenedor de los parametros a utilizar en el procedimiento
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return número del estado de la ejecución del procedimiento
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int procCorreccion(Long al_idTurnoHistoria, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		int li_return;

		li_return = 0;

		if(
		    NumericUtils.isValidLong(al_idTurnoHistoria) && StringUtils.isValidString(as_userId)
			    && StringUtils.isValidString(as_remoteIp)
		)
		{
			LoggerHandler llh_logger;
			String        ls_error;

			llh_logger = getLog();

			if(llh_logger.isDebugEnabled())
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder();

				lsb_sb.append("procCorreccion: ");
				lsb_sb.append(al_idTurnoHistoria);

				llh_logger.debug(lsb_sb);
			}

			CallableStatement lcs_cs;
			int               li_contador;

			lcs_cs          = null;
			li_contador     = 1;

			try
			{
				lcs_cs = getConnection().prepareCall(cs_PROC_CORRECCION);

				setLong(lcs_cs, al_idTurnoHistoria, li_contador++);
				lcs_cs.setString(li_contador++, as_userId);
				lcs_cs.setString(li_contador++, as_remoteIp);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(4);
				ls_error      = lcs_cs.getString(5);

				if(li_return < 0)
					throw new B2BException("Error en procedimiento PROC_CORRECCION : " + li_return + " :: " + ls_error);
			}
			catch(SQLException lse_e)
			{
				llh_logger.error("procCorreccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}

		return li_return;
	}

	/**
	 * Proc crear comunicacion notificacion.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de int
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int procCrearComunicacionNotificacion(Long al_idTurnoHistoria, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		int li_return;

		li_return = 0;

		if(
		    NumericUtils.isValidLong(al_idTurnoHistoria) && StringUtils.isValidString(as_userId)
			    && StringUtils.isValidString(as_remoteIp)
		)
		{
			LoggerHandler llh_logger;
			String        ls_error;

			llh_logger = getLog();

			if(llh_logger.isDebugEnabled())
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder();

				lsb_sb.append("procCrearComunicacionNotificacion: ");
				lsb_sb.append(al_idTurnoHistoria);

				llh_logger.debug(lsb_sb);
			}

			CallableStatement lcs_cs;
			int               li_contador;

			lcs_cs          = null;
			li_contador     = 1;

			try
			{
				lcs_cs = getConnection().prepareCall(cs_PROC_CREAR_COMUNICACION_NOTIFICACIONES);

				setLong(lcs_cs, al_idTurnoHistoria, li_contador++);
				lcs_cs.setString(li_contador++, as_userId);
				lcs_cs.setString(li_contador++, as_remoteIp);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(4);
				ls_error      = lcs_cs.getString(5);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento PROC_CREAR_COMUNICACION_NOTIFICACIONES : " + li_return + " :: "
					    + ls_error
					);
			}
			catch(SQLException lse_e)
			{
				llh_logger.error("procCrearComunicacionNotificacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}

		return li_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Proc crear matricula ant sistema.
	 *
	 * @param al_turnoHistoria correspondiente al valor del tipo de objeto Long
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipRemote Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int procCrearMatriculaAntSistema(Long al_turnoHistoria, String as_usuario, String as_ipRemote)
	    throws B2BException
	{
		int           li_return;
		LoggerHandler llh_logger;
		String        ls_error;

		li_return      = 0;
		llh_logger     = getLog();

		if(llh_logger.isDebugEnabled())
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append("procCrearMatriculaAntSistema: ");
			lsb_sb.append(al_turnoHistoria);
			lsb_sb.append(as_usuario);

			llh_logger.debug(lsb_sb);
		}

		CallableStatement lcs_cs;
		ResultSet         lrs_rs;
		int               li_contador;

		lcs_cs          = null;
		lrs_rs          = null;
		li_contador     = 1;

		try
		{
			lcs_cs = getConnection().prepareCall(cs_PROC_CREAR_MATRICULA_ANT_SISTEMA);

			setLong(lcs_cs, al_turnoHistoria, li_contador++);
			lcs_cs.setString(li_contador++, as_usuario);
			lcs_cs.setString(li_contador++, as_ipRemote);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_return     = lcs_cs.getInt(4);
			ls_error      = lcs_cs.getString(5);

			if(li_return < 0)
				throw new B2BException(
				    "Error en procedimiento PROC_CREAR_MATRICULA_ANT_SISTEMA :" + li_return + " :: " + ls_error
				);
		}
		catch(SQLException lse_e)
		{
			llh_logger.error("procCrearMatriculaAntSistema", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lcs_cs);
		}

		return li_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Proc crear turno.
	 *
	 * @param al_idSolicitud correspondiente al valor del tipo de objeto Long
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipRemote Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String procCrearTurno(Long al_idSolicitud, Long al_idTurnoHistoria, String as_userId, String as_ipRemote)
	    throws B2BException
	{
		int           li_return;
		String        ls_error;
		String        ls_errorReturn;
		LoggerHandler llh_logger;

		li_return          = 0;
		ls_errorReturn     = null;
		llh_logger         = getLog();

		if(llh_logger.isDebugEnabled())
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append("procCrearTurno: ");
			lsb_sb.append(al_idSolicitud);

			llh_logger.debug(lsb_sb);
		}

		CallableStatement lcs_cs;
		ResultSet         lrs_rs;
		int               li_contador;

		lcs_cs          = null;
		lrs_rs          = null;
		li_contador     = 1;

		try
		{
			lcs_cs = getConnection().prepareCall(cs_PROC_CREAR_TURNO);
			setLong(lcs_cs, al_idSolicitud, li_contador++);
			setLong(lcs_cs, al_idTurnoHistoria, li_contador++);
			lcs_cs.setString(li_contador++, as_userId);
			lcs_cs.setString(li_contador++, as_ipRemote);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_return     = lcs_cs.getInt(5);
			ls_error      = lcs_cs.getString(6);

			if(li_return != 0)
			{
				ls_errorReturn = "Error en procedimiento PROC_CREAR_TURNO :" + li_return + " :: " + ls_error;

				if(li_return < 0)
					throw new B2BException(ls_errorReturn);
			}
		}
		catch(SQLException lse_e)
		{
			llh_logger.error("procCrearTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lcs_cs);
		}

		return ls_errorReturn;
	}

	/**
	 * Retorna el valor del objeto de tipo Proc desbloqueo matriculas.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipRemote Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int procDesbloqueoMatriculas(String as_idTurno, String as_userId, String as_ipRemote)
	    throws B2BException
	{
		int           li_return;
		LoggerHandler llh_logger;
		String        ls_error;

		li_return      = 0;
		llh_logger     = getLog();

		if(llh_logger.isDebugEnabled())
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append("procDesbloqueoMatriculas: ");
			lsb_sb.append(as_idTurno);

			llh_logger.debug(lsb_sb);
		}

		CallableStatement lcs_cs;
		ResultSet         lrs_rs;
		int               li_contador;

		lcs_cs          = null;
		lrs_rs          = null;
		li_contador     = 1;

		try
		{
			lcs_cs = getConnection().prepareCall(cs_PROC_DESBLOQUEO_MATRICULAS);

			lcs_cs.setString(li_contador++, as_idTurno);
			lcs_cs.setString(li_contador++, as_userId);
			lcs_cs.setString(li_contador++, as_ipRemote);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_return     = lcs_cs.getInt(4);
			ls_error      = lcs_cs.getString(5);

			if(li_return < 0)
				throw new B2BException(
				    "Error en procedimiento PROC_DESBLOQUEO_MATRICULAS: " + li_return + " :: " + ls_error
				);
		}
		catch(SQLException lse_e)
		{
			llh_logger.error("procDesbloqueoMatriculas", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lcs_cs);
		}

		return li_return;
	}

	/**
	 * Metodo encargado de invocar el procedimiento PROC_EJECUTAR_TRASLADO.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene los argumentos necesarios para invocar el procedimiento.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procEjecutarTraslado(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		try
		{
			if(ath_turnoHistoria != null)
			{
				int li_i;
				int li_return;
				li_i = 1;

				String ls_error;

				li_return     = 0;

				lcs_cs = getConnection().prepareCall(cs_PROC_EJECUTAR_TRASLADO);

				setLong(lcs_cs, ath_turnoHistoria.getIdTurnoHistoria(), li_i++);
				lcs_cs.setString(li_i++, ath_turnoHistoria.getIdUsuarioModificacion());
				lcs_cs.setString(li_i++, ath_turnoHistoria.getIpModificacion());

				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(li_i - 2);
				ls_error      = lcs_cs.getString(li_i - 1);

				if(li_return < 0)
					throw new B2BException(li_return + " :: " + ls_error);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "procEjecutarTraslado", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Invoca al procedimiento almacenado proc elimina matricula an sistema.
	 *
	 * @param as_idTurno id del turno desde el cual se invoca el procedimiento
	 * @param as_idDatosAntSistema id del predio de antiguo sistema, si existe
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipRemota Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procEliminaMatriculaAntSistema(
	    String as_idTurno, String as_idDatosAntSistema, String as_idUsuario, String as_ipRemota
	)
	    throws B2BException
	{
		if(
		    StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idUsuario)
			    && StringUtils.isValidString(as_ipRemota)
		)
		{
			CallableStatement lcs_cs;

			lcs_cs = null;

			try
			{
				int    li_i;
				int    li_return;
				String ls_error;

				li_i          = 1;
				li_return     = 0;

				lcs_cs = getConnection().prepareCall(cs_PROC_ELIMINA_MATRICULA_ANT_SISTEMA);

				lcs_cs.setString(li_i++, as_idTurno);
				lcs_cs.setString(li_i++, as_idDatosAntSistema);
				lcs_cs.setString(li_i++, as_idUsuario);
				lcs_cs.setString(li_i++, as_ipRemota);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(5);
				ls_error      = lcs_cs.getString(6);

				if(li_return < 0)
					throw new B2BException(ls_error);
			}
			catch(SQLException lse_e)
			{
				logError(this, "procEliminaMatriculaAntSistema", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de tipo Proc liquidacion.
	 *
	 * @param al_liquidacion correspondiente al valor del tipo de objeto Liquidacion
	 * @return devuelve el valor del objeto liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public synchronized Liquidacion procLiquidacion(Liquidacion al_liquidacion)
	    throws B2BException
	{
		if(al_liquidacion != null)
		{
			CallableStatement lcs_cs;

			lcs_cs = null;

			try
			{
				int li_i;

				li_i     = 1;

				lcs_cs = getConnection().prepareCall(cs_PROC_LIQUIDACION);

				lcs_cs.setString(li_i++, al_liquidacion.getIdSolicitud());
				lcs_cs.setString(li_i++, al_liquidacion.getIdCondicion());
				lcs_cs.setString(li_i++, al_liquidacion.getIdTipoMayorValor());
				lcs_cs.setString(li_i++, al_liquidacion.getIdTurno());
				lcs_cs.setString(li_i++, al_liquidacion.getIdUsuarioCreacion());
				lcs_cs.setString(li_i++, al_liquidacion.getIpCreacion());
				lcs_cs.registerOutParameter(li_i++, OracleTypes.INTEGER);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);

				lcs_cs.execute();

				al_liquidacion.setCodigoRespuesta(lcs_cs.getString(li_i - 2));
				al_liquidacion.setRespuestaLiquidacion(lcs_cs.getString(li_i - 1));
			}
			catch(SQLException lse_e)
			{
				logError(this, "procLiquidacion", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}

		return al_liquidacion;
	}

	/**
	 * Proc lla crear etapa trg.
	 *
	 * @param as_solicitud de as solicitud
	 * @param as_numeroReferencia de as numero referencia
	 * @param as_objetoLlamado de as objeto llamado
	 * @param al_etapaLlamado de al etapa llamado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void procLlaCrearEtapaTrg(
	    Solicitud as_solicitud, String as_numeroReferencia, String as_objetoLlamado, Long al_etapaLlamado
	)
	    throws B2BException
	{
		CallableStatement lcs_cs = null;

		try
		{
			if((as_solicitud != null) && NumericUtils.isValidLong(al_etapaLlamado))
			{
				int li_contador;
				int li_return;

				li_return       = 0;
				li_contador     = 1;

				lcs_cs = getConnection().prepareCall(cs_PROC_LLA_CREAR_ETAPA_TRG);

				lcs_cs.setString(li_contador++, as_solicitud.getIdSolicitud());
				lcs_cs.setString(li_contador++, as_numeroReferencia);
				lcs_cs.setString(li_contador++, as_objetoLlamado);
				setLong(lcs_cs, al_etapaLlamado, li_contador++);
				lcs_cs.setString(li_contador++, as_solicitud.getIdUsuarioModificacion());
				lcs_cs.setString(li_contador++, as_solicitud.getIpModificacion());
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return = lcs_cs.getInt(7);

				if(li_return < 0)
				{
					String ls_error;

					ls_error = lcs_cs.getString(8);

					throw new B2BException(
					    "Error en procedimiento PROC_LLA_CREAR_ETAPA_TRG :" + li_return + " :: " + ls_error
					);
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "procLlaCrearEtapaTrg", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Metodo encargado de invocar el procedimiento almacenado PROC_LLA_CREAR_ETAPA_TRG.
	 *
	 * @param al_idTurnoHistoria Argumento de tipo <code>Long</code> que contiene los criterios necesarios para realizar la invocación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procMensajeComunicacion(Long al_idTurnoHistoria, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		CallableStatement lcs_cs = null;

		try
		{
			if(NumericUtils.isValidLong(al_idTurnoHistoria))
			{
				int li_contador;
				int li_return;

				li_return       = 0;
				li_contador     = 1;

				lcs_cs = getConnection().prepareCall(cs_PROC_MENSAJE_COMUNICACION);

				setLong(lcs_cs, al_idTurnoHistoria, li_contador++);
				lcs_cs.setString(li_contador++, as_userId);
				lcs_cs.setString(li_contador++, as_remoteIp);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return = lcs_cs.getInt(4);

				if(li_return < 0)
				{
					String ls_error;

					ls_error = lcs_cs.getString(5);

					throw new B2BException(
					    "Error en procedimiento PROC_MENSAJE_COMUNICACION :" + li_return + " :: " + ls_error
					);
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "procMensajeComunicacion", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Invoca a PROC_PAGAR_CUENTA_CUPO.
	 *
	 * @param apcc_datos Objeto contenedor de la informaión a utilizar para la invocación del procedimiento
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de string
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public String procPagarCuentaCupo(PagoCuentaCupo apcc_datos, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		String ls_idMovimiento;

		ls_idMovimiento = null;

		if(apcc_datos != null)
		{
			CallableStatement lcs_cs;

			lcs_cs = null;

			try
			{
				int    li_i;
				int    li_codigoRespuesta;
				String ls_descripcionRespuesta;

				li_i     = 1;

				lcs_cs = getConnection().prepareCall(cs_PROC_PAGAR_CUENTA_CUPO);

				lcs_cs.setString(li_i++, apcc_datos.getIdCuentaCupo());
				lcs_cs.setString(li_i++, apcc_datos.getReferenciaPago());
				lcs_cs.setBigDecimal(li_i++, apcc_datos.getValor());
				lcs_cs.setString(li_i++, apcc_datos.getIdUsuarioCuentaCupo());
				lcs_cs.setObject(li_i++, apcc_datos.getFechaPago(), OracleTypes.DATE);
				lcs_cs.setString(li_i++, as_userId);
				lcs_cs.setString(li_i++, as_remoteIp);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);

				lcs_cs.execute();

				li_codigoRespuesta          = lcs_cs.getInt(8);
				ls_descripcionRespuesta     = lcs_cs.getString(9);

				if(li_codigoRespuesta < 0)
					throw new B2BException(ls_descripcionRespuesta);

				ls_idMovimiento = lcs_cs.getString(10);
			}
			catch(SQLException lse_e)
			{
				logError(this, "procPagarCuentaCupo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}

		return ls_idMovimiento;
	}

	/**
	 * Retorna el valor del objeto de tipo Proc realizar registro.
	 *
	 * @param al_turnoHistoria correspondiente al valor del tipo de objeto Long
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipRemote Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int procRealizarRegistro(Long al_turnoHistoria, String as_userId, String as_ipRemote)
	    throws B2BException
	{
		int           li_return;
		LoggerHandler llh_logger;
		String        ls_error;

		li_return      = 0;
		llh_logger     = getLog();

		if(llh_logger.isDebugEnabled())
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append("procRealizarRegistro: ");
			lsb_sb.append(al_turnoHistoria);

			llh_logger.debug(lsb_sb);
		}

		CallableStatement lcs_cs;
		ResultSet         lrs_rs;
		int               li_contador;

		lcs_cs          = null;
		lrs_rs          = null;
		li_contador     = 1;

		try
		{
			lcs_cs = getConnection().prepareCall(cs_PROC_REALIZAR_REGISTRO);

			setLong(lcs_cs, al_turnoHistoria, li_contador++);
			lcs_cs.setString(li_contador++, as_userId);
			lcs_cs.setString(li_contador++, as_ipRemote);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_return     = lcs_cs.getInt(4);
			ls_error      = lcs_cs.getString(5);

			if(li_return < 0)
				throw new B2BException(
				    "Error en procedimiento PROC_REALIZAR_REGISTRO : " + li_return + " :: " + ls_error
				);
		}
		catch(SQLException lse_e)
		{
			llh_logger.error("procRealizarRegistro", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lcs_cs);
		}

		return li_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Proc recepcion documentos.
	 *
	 * @param ar_arg correspondiente al valor del tipo de objeto Registro
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int procRecepcionDocumentos(Registro ar_arg)
	    throws B2BException
	{
		int li_return;
		li_return = -1;

		if(ar_arg != null)
		{
			LoggerHandler llh_logger;
			String        ls_error;
			String        ls_idSolicitud;

			llh_logger         = getLog();
			ls_idSolicitud     = ar_arg.getSolicitud().getIdSolicitud();

			if(llh_logger.isDebugEnabled())
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder();

				lsb_sb.append("procRecepcionDocumentos: ");
				lsb_sb.append(ls_idSolicitud);

				llh_logger.debug(lsb_sb);
			}

			CallableStatement lcs_cs;
			ResultSet         lrs_rs;
			int               li_contador;

			lcs_cs          = null;
			lrs_rs          = null;
			li_contador     = 1;

			try
			{
				lcs_cs = getConnection().prepareCall(cs_PROC_RECEPCION_DOCUMENTOS);

				lcs_cs.setString(li_contador++, ls_idSolicitud);
				lcs_cs.setString(li_contador++, ar_arg.getIdTurno());
				lcs_cs.setString(li_contador++, ar_arg.getIdUsuarioCreacion());
				lcs_cs.setString(li_contador++, ar_arg.getIpCreacion());
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(5);
				ls_error      = lcs_cs.getString(6);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento PROC_RECEPCION_DOCUMENTOS : " + li_return + " :: " + ls_error
					);
			}
			catch(SQLException lse_e)
			{
				llh_logger.error("procRecepcionDocumentos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lcs_cs);
			}
		}

		return li_return;
	}

	/**
	 * Proc valida notificacion terceros.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de int
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int procValidaNotificacionTerceros(Long al_idTurnoHistoria, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		int li_return;

		li_return = 0;

		if(
		    NumericUtils.isValidLong(al_idTurnoHistoria) && StringUtils.isValidString(as_userId)
			    && StringUtils.isValidString(as_remoteIp)
		)
		{
			LoggerHandler llh_logger;
			String        ls_error;

			llh_logger = getLog();

			if(llh_logger.isDebugEnabled())
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder();

				lsb_sb.append("procValidaNotificacionTerceros: ");
				lsb_sb.append(al_idTurnoHistoria);

				llh_logger.debug(lsb_sb);
			}

			CallableStatement lcs_cs;
			int               li_contador;

			lcs_cs          = null;
			li_contador     = 1;

			try
			{
				lcs_cs = getConnection().prepareCall(cs_PROC_VALIDA_NOTIFICACION_TERCEROS);

				setLong(lcs_cs, al_idTurnoHistoria, li_contador++);
				lcs_cs.setString(li_contador++, as_userId);
				lcs_cs.setString(li_contador++, as_remoteIp);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_contador++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(4);
				ls_error      = lcs_cs.getString(5);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento PROC_VALIDA_NOTIFICACION_TERCEROS : " + li_return + " :: " + ls_error
					);
			}
			catch(SQLException lse_e)
			{
				llh_logger.error("procCrearComunicacionNotificacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}

		return li_return;
	}

	/**
	 * Método de invocación del procedimiento de verificación de bitácora bloqueo
	 * @param as_idTurno con el turno historia del turno en etapa 100
	 * @param as_idUsuario con el usuario de la transacción
	 * @param as_ipRemota con la ip del usuario de la transacción
	 * @throws B2BException
	 */
	public void procVerificaBitacoraBloqueo(String as_idTurno, String as_idUsuario, String as_ipRemota)
	    throws B2BException
	{
		if(
		    StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idUsuario)
			    && StringUtils.isValidString(as_ipRemota)
		)
		{
			CallableStatement lcs_cs;

			lcs_cs = null;

			try
			{
				int    li_i;
				int    li_return;
				String ls_error;

				li_i          = 1;
				li_return     = 0;

				lcs_cs = getConnection().prepareCall(cs_PROC_VERIFICA_BITACORA_BLQ);

				lcs_cs.setString(li_i++, as_idTurno);
				lcs_cs.setString(li_i++, as_idUsuario);
				lcs_cs.setString(li_i++, as_ipRemota);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(4);
				ls_error      = lcs_cs.getString(5);

				if(li_return < 0)
					throw new B2BException(ls_error);
			}
			catch(SQLException lse_e)
			{
				logError(this, "procVerificaBitacoraBloqueo", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}
	}

	/**
	 * Proce copiar anotacion temporal.
	 *
	 * @param are_datos correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void proceCopiarAnotacionTemporal(RegistroCalificacion are_datos)
	    throws B2BException
	{
		CallableStatement lcs_cs = null;

		try
		{
			if(are_datos != null)
			{
				int li_i;
				int li_return;
				li_i = 1;

				String ls_error;

				li_return     = 0;

				lcs_cs = getConnection().prepareCall(cs_PROC_COPIA_ANOTACION_TEMPORAL);

				lcs_cs.setString(li_i++, are_datos.getIdAnotacionPredio());
				lcs_cs.setString(li_i++, are_datos.getTurno());
				lcs_cs.setString(li_i++, are_datos.getIdCirculo());
				setLong(lcs_cs, are_datos.getIdMatricula(), li_i++);
				lcs_cs.setString(li_i++, are_datos.getIdUsuarioModificacion());
				lcs_cs.setString(li_i++, are_datos.getIpModificacion());

				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(7);
				ls_error      = lcs_cs.getString(8);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento PROC_COPIA_ANOTACION_TEMPORAL :" + li_return + " :: " + ls_error
					);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "proceCopiarAnotacionTemporal", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Retorna el valor del objeto de tipo Registro calificacion.
	 *
	 * @param aore_datos correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param ab_paramPreCalificar correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto turno historia
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public TurnoHistoria registroCalificacion(TurnoHistoria aore_datos, boolean ab_paramPreCalificar)
	    throws B2BException
	{
		CallableStatement lcs_cs;
		TurnoHistoria     lore_retorno;

		lcs_cs           = null;
		lore_retorno     = null;

		try
		{
			int li_i;
			li_i = 1;

			String ls_codigoRespuesta;
			int    li_retorno;

			lcs_cs = getConnection().prepareCall(cs_PROC_CREAR_ANOTACION_PREDIO);
			setLong(lcs_cs, aore_datos.getIdTurnoHistoria(), li_i++);

			if(!ab_paramPreCalificar)
				lcs_cs.setString(li_i++, IdentificadoresCommon.CALIFICAR);
			else
				lcs_cs.setString(li_i++, IdentificadoresCommon.PRECALIFICAR);

			lcs_cs.setString(li_i++, aore_datos.getIdUsuarioModificacion());
			lcs_cs.setString(li_i++, aore_datos.getIpModificacion());
			lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_retorno             = (lcs_cs.getInt(5));
			ls_codigoRespuesta     = (lcs_cs.getString(6));

			if(li_retorno < 0)
				throw new B2BException(
				    "Error en procedimiento PROC_CREAR_ANOTACION_PREDIO : " + li_retorno + " :: " + ls_codigoRespuesta
				);
		}
		catch(SQLException lse_e)
		{
			logError(this, "registroCalificacion", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}

		return lore_retorno;
	}

	/**
	 * Retorna el valor del objeto de tipo Reporte diario radicador.
	 *
	 * @param aoic_ic correspondiente al valor del tipo de objeto InstanciaConsulta
	 * @return devuelve el valor del objeto instancia consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InstanciaConsulta
	 */
	public InstanciaConsulta reporteDiarioRadicador(InstanciaConsulta aoic_ic)
	    throws B2BException
	{
		Collection<Map<String, Object>> lcmso_dataColumns;

		lcmso_dataColumns = new ArrayList<Map<String, Object>>();

		if(aoic_ic != null)
		{
			CallableStatement lcs_cs;
			ResultSet         lrs_data;

			lcs_cs       = null;
			lrs_data     = null;

			try
			{
				int    li_i;
				int    li_return;
				String ls_error;

				li_i          = 1;
				li_return     = 0;

				lcs_cs = getConnection().prepareCall(cs_PROC_RPT_DIARIO_RADICADOR);

				lcs_cs.setLong(li_i++, aoic_ic.getIdInstancia());
				lcs_cs.registerOutParameter(li_i++, OracleTypes.CURSOR);
				lcs_cs.setString(li_i++, aoic_ic.getIdUsuarioCreacion());
				lcs_cs.setString(li_i++, aoic_ic.getIpCreacion());
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(5);
				ls_error      = lcs_cs.getString(6);

				if(li_return == -20)
					throw new B2BException(ls_error);
				else
				{
					int               li_columns;
					ResultSetMetaData lrmd_columns;

					li_columns       = 0;
					lrmd_columns     = null;
					lrs_data         = (ResultSet)lcs_cs.getObject(2);
					lrmd_columns     = lrs_data.getMetaData();
					li_columns       = lrmd_columns.getColumnCount();

					if(lrmd_columns != null)
					{
						while(lrs_data.next())
						{
							Map<String, Object> lhmso_row;

							lhmso_row = new LinkedHashMap();

							for(int li_tmp = 1; li_tmp <= li_columns; li_tmp++)
								lhmso_row.put(lrmd_columns.getColumnName(li_tmp), lrs_data.getObject(li_tmp));

							lcmso_dataColumns.add(lhmso_row);
						}
					}

					if(CollectionUtils.isValidCollection(lcmso_dataColumns))
						aoic_ic.setDataColumns(lcmso_dataColumns);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "reporteDiarioRadicador", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);

				if(lrs_data != null)
					close(lrs_data);
			}
		}

		return aoic_ic;
	}

	/**
	 * Retorna el valor del objeto de tipo Sp create stage.
	 *
	 * @param ath_datos correspondiente al valor del tipo de objeto TurnoHistoria
	 * @return devuelve el valor del objeto turno historia
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public TurnoHistoria spCreateStage(TurnoHistoria ath_datos)
	    throws B2BException
	{
		TurnoHistoria lth_retorno;

		lth_retorno = new TurnoHistoria();

		if(ath_datos != null)
		{
			CallableStatement lcs_cs;

			lcs_cs = null;

			try
			{
				int     li_i;
				Integer li_codigoRespuesta;
				String  ls_descripcionRespuesta;

				li_i       = 1;
				lcs_cs     = getConnection().prepareCall(cs_PROC_CREA_ETAPA);

				setLong(lcs_cs, ath_datos.getIdTurnoHistoria(), li_i++);
				lcs_cs.setString(li_i++, ath_datos.getIdTurno());
				lcs_cs.setString(li_i++, ath_datos.getIdSolicitud());
				lcs_cs.setString(li_i++, ath_datos.getIdUsuarioAsignacion());
				lcs_cs.setString(li_i++, ath_datos.getIdCirculo());
				lcs_cs.setString(li_i++, ath_datos.getIdUsuarioModificacion());
				lcs_cs.setString(li_i++, ath_datos.getIpModificacion());
				lcs_cs.registerOutParameter(li_i++, OracleTypes.NUMBER);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);

				lcs_cs.execute();

				lth_retorno                 = new TurnoHistoria();
				li_codigoRespuesta          = NumericUtils.getInteger(lcs_cs.getInt(9));
				ls_descripcionRespuesta     = (lcs_cs.getString(10));

				if(!(NumericUtils.getInt(li_codigoRespuesta) >= 0))
					lth_retorno.setTurnoHist(li_codigoRespuesta);

				if(StringUtils.isValidString(ls_descripcionRespuesta))
					lth_retorno.setCalificacion(ls_descripcionRespuesta);

				lth_retorno.setIdTurnoHistoria(NumericUtils.getLongWrapper(lcs_cs.getLong(8)));
			}
			catch(SQLException lse_e)
			{
				logError(this, "spCreateStage", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}

		return lth_retorno;
	}

	/**
	 * Retorna el valor del objeto de tipo Sp reasigna turno.
	 *
	 * @param ath_datos correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param ab_paramAutomatico correspondiente al valor del tipo de objeto boolean
	 * @param ab_justificacionReasignadoEspecial de ab justificacion reasignado especial
	 * @return devuelve el valor del objeto turno historia
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public TurnoHistoria spReasignaTurno(
	    TurnoHistoria ath_datos, boolean ab_paramAutomatico, boolean ab_justificacionReasignadoEspecial
	)
	    throws B2BException
	{
		TurnoHistoria lth_retorno;

		lth_retorno = null;

		if(ath_datos != null)
		{
			CallableStatement lcs_cs;

			lcs_cs = null;

			try
			{
				int li_i;
				li_i = 1;

				String  ls_descripcionRespuesta;
				Integer li_codigoRespuesta;
				Long    li_idTurnoHistoria;

				lcs_cs = getConnection().prepareCall(cs_PROC_REASIGNA_TURNO);

				setLong(lcs_cs, ath_datos.getIdTurnoHistoria(), li_i++);

				if(ab_paramAutomatico)
					lcs_cs.setString(li_i++, EstadoCommon.AUTOMATICO);
				else
					lcs_cs.setString(li_i++, ath_datos.getIdUsuarioAsignacion());

				lcs_cs.setString(li_i++, ath_datos.getIdCirculo());
				lcs_cs.setString(li_i++, ath_datos.getObservaciones());

				if(ab_justificacionReasignadoEspecial)
					lcs_cs.setString(li_i++, EstadoCommon.S);
				else
					lcs_cs.setString(li_i++, EstadoCommon.N);

				lcs_cs.registerOutParameter(li_i++, OracleTypes.NUMBER);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.NUMBER);
				lcs_cs.setString(li_i++, ath_datos.getIdUsuarioModificacion());
				lcs_cs.setString(li_i++, ath_datos.getIpModificacion());
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);

				lcs_cs.execute();

				lth_retorno     = new TurnoHistoria();

				li_idTurnoHistoria          = NumericUtils.getLongWrapper(lcs_cs.getLong(6));
				li_codigoRespuesta          = NumericUtils.getInteger(lcs_cs.getInt(10));
				ls_descripcionRespuesta     = lcs_cs.getString(11);

				if(NumericUtils.isValidLong(li_idTurnoHistoria))
					lth_retorno.setIdTurnoHistoria(li_idTurnoHistoria);

				lth_retorno.setCodigoCalificacion(NumericUtils.getInt(li_codigoRespuesta));

				if(StringUtils.isValidString(ls_descripcionRespuesta))
					lth_retorno.setCalificacion(ls_descripcionRespuesta);
			}
			catch(SQLException lse_e)
			{
				logError(this, "spReasignaTurno", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}

		return lth_retorno;
	}

	/**
	 * procedimiento reasignacion apoyo nacional.
	 *
	 * @param ath_datos de ath datos
	 * @return el valor de turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria spReasignacionApoyoNacional(TurnoHistoria ath_datos)
	    throws B2BException
	{
		if(ath_datos != null)
		{
			CallableStatement lcs_cs;

			lcs_cs = null;

			try
			{
				int li_i;
				li_i = 1;

				String ls_descripcionRespuesta;
				int    li_codigoRespuesta;

				lcs_cs = getConnection().prepareCall(cs_PROC_REASIGNACION_APOYO_NACIONAL);

				lcs_cs.setString(li_i++, ath_datos.getIdSolicitud());

				lcs_cs.setString(li_i++, ath_datos.getIdUsuarioModificacion());
				lcs_cs.setString(li_i++, ath_datos.getIpModificacion());
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);

				lcs_cs.execute();

				li_codigoRespuesta          = lcs_cs.getInt(4);
				ls_descripcionRespuesta     = lcs_cs.getString(5);

				if(li_codigoRespuesta < 0)
					throw new B2BException(
					    "Error en procedimiento PROC_REASIGNACION_APOYO_NACIONAL : " + li_codigoRespuesta + " :: "
					    + ls_descripcionRespuesta
					);

				ath_datos.setCodigoCalificacion(NumericUtils.getInt(li_codigoRespuesta));

				if(StringUtils.isValidString(ls_descripcionRespuesta))
					ath_datos.setCalificacion(ls_descripcionRespuesta);
			}
			catch(SQLException lse_e)
			{
				logError(this, "spReasignacionApoyoNacional", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}

		return ath_datos;
	}

	/**
	 * Extrae los resultados de la consulta de tipos catalogos.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return TiposCatalogos resultante de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TiposCatalogos
	 */
	private TiposCatalogos obtenerTipoCatalogo(ResultSet ars_rs)
	    throws SQLException
	{
		TiposCatalogos ltc_tipoCatalogo;

		ltc_tipoCatalogo = new TiposCatalogos();

		ltc_tipoCatalogo.setCodigo(ars_rs.getString(1));
		ltc_tipoCatalogo.setNombre(ars_rs.getString(2));

		return ltc_tipoCatalogo;
	}
}
