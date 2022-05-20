package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_TURNO.
 *
 * @author jpatino
 */
public class TurnoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_TURNO WHERE ID_TURNO = ? ";

	/** Constante cs_FIND_BY_EXPEDIENTE. */
	private static final String cs_FIND_BY_EXPEDIENTE = "SELECT * FROM SDB_ACC_TURNO WHERE EXPEDIENTE = ? ";

	/** Constante cs_FIND_BY_ID_DATOS_ANT_SISTEMA. */
	private static final String cs_FIND_BY_ID_DATOS_ANT_SISTEMA = "SELECT * FROM SDB_ACC_TURNO WHERE ID_DATOS_ANT_SISTEMA = ? ";

	/** Constante cs_FIND_ALL_BY_CREAR_TURNO. */
	private static final String cs_FIND_ALL_BY_CREAR_TURNO = "SELECT TUR.*, CIRR.NOMBRE NOMBRE_CIRCULO, SAS.NIR FROM SDB_ACC_TURNO TUR INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CIRR ON (TUR.ID_CIRCULO = CIRR.ID_CIRCULO) INNER JOIN SDB_ACC_SOLICITUD SAS ON (TUR.ID_SOLICITUD = SAS.ID_SOLICITUD) WHERE TUR.CREAR_TURNO = ? AND NVL(TUR.INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA, 0) < ? AND SAS.NIR IS NOT NULL ORDER BY TUR.FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_SOLICITUD_DIF_CIRCULO. */
	private static final String cs_FIND_BY_SOLICITUD_DIF_CIRCULO = "SELECT * FROM SDB_ACC_TURNO WHERE ID_SOLICITUD = ? AND ID_CIRCULO <> ? AND ID_PROCESO <> 1";

	/** Constante cs_FIND_BY_NIR. */
	private static final String cs_FIND_BY_NIR = "SELECT SAT.* FROM SDB_ACC_SOLICITUD SAS INNER JOIN SDB_ACC_TURNO SAT ON SAT.ID_SOLICITUD = SAS.ID_SOLICITUD WHERE SAS.NIR = ? AND SAT.ID_PROCESO <> '1'";

	/** Constante cs_BUSCAR_NIR_POR_ID_TURNO. */
	private static final String cs_BUSCAR_NIR_POR_ID_TURNO = "SELECT S.NIR,T.* FROM  SDB_ACC_TURNO T "
		+ "INNER JOIN SDB_ACC_SOLICITUD S ON (S.ID_SOLICITUD = T.ID_SOLICITUD) WHERE T.ID_TURNO = ?";

	/** Constante cs_FIND_BY_TURNO_TIPO_ACTO_PERMUTA. */
	private static final String cs_FIND_BY_TURNO_TIPO_ACTO_PERMUTA = "SELECT SAT.*,SAC.ID_CIRCULO, SAC.ID_SOLICITUD, SAC.ID_TIPO_ACTO FROM SDB_ACC_TURNO SAT INNER JOIN SDB_ACC_ACTO SAC ON SAC.ID_SOLICITUD = SAT.ID_SOLICITUD "
		+ "AND SAC.ID_CIRCULO = SAT.ID_CIRCULO WHERE SAT.ID_TURNO = ? AND SAC.ID_TIPO_ACTO = '0144'";

	/** Constante cs_FIND_BY_ID_CIRCULO. */
	private static final String cs_FIND_BY_ID_CIRCULO = "SELECT * FROM SDB_ACC_TURNO WHERE ID_TURNO=? AND ID_CIRCULO = ?";

	/** Constante cs_FIND_NOMBRE_ETAPA_BY_ID. */
	private static final String cs_FIND_NOMBRE_ETAPA_BY_ID = "SELECT AT.*, PE.NOMBRE AS NOMBRE_ETAPA_ACTUAL FROM SDB_ACC_TURNO AT "
		+ "LEFT JOIN SDB_PGN_ETAPA PE ON PE.ID_ETAPA = AT.ID_ETAPA_ACTUAL WHERE ID_TURNO = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_PROCESO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_PROCESO = "SELECT * FROM SDB_ACC_TURNO WHERE ID_SOLICITUD=? AND ID_PROCESO = ? ";

	/** Constante cs_FIND_BY_ID_SOLICITUD_PROCESO_CIRCULO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_PROCESO_CIRCULO = "SELECT * FROM SDB_ACC_TURNO WHERE ID_SOLICITUD=? AND ID_PROCESO = ? AND ID_CIRCULO = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_TURNO WHERE ID_SOLICITUD=?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_ETAPA. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ETAPA = "SELECT * FROM SDB_ACC_TURNO WHERE ID_SOLICITUD=? AND (ID_ETAPA_ACTUAL>=? AND ID_ETAPA_ACTUAL<?)";

	/** Constante cs_CONSULTA_RECURRENTE_TURNOS. */
	private static final String cs_CONSULTA_RECURRENTE_TURNOS = "SELECT ID_TURNO, ID_TURNO_ANT FROM SDB_ACC_TURNO WHERE ANULADO != 'S' "
		+ "START WITH ID_TURNO = ? CONNECT BY PRIOR ID_TURNO = ID_TURNO_ANT";

	/** Constante cs_CONSULTA_DETALLE_TURNOS_APOYO_NACIONAL. */
	private static final String cs_CONSULTA_DETALLE_TURNOS_APOYO_NACIONAL = "SELECT SAS.ID_SOLICITUD, ATH.ID_TURNO_HISTORIA, SAS.NIR, ATH.ID_TURNO,"
		+ " (SELECT LISTAGG(SASM.ID_CIRCULO ||'-'|| SASM.ID_MATRICULA, ', ') WITHIN GROUP (ORDER BY SAS.ID_SOLICITUD) FROM SDB_ACC_SOLICITUD_MATRICULA SASM WHERE SASM.ID_SOLICITUD = SAS.ID_SOLICITUD AND SASM.ID_CIRCULO = ?) AS MATRICULAS,"
		+ " LISTAGG(SAA.ID_TIPO_ACTO ||'-'|| SPTA.NOMBRE, ', ') WITHIN GROUP (ORDER BY SAS.ID_SOLICITUD) AS ACTOS,"
		+ " SAT.FECHA_CREACION, SPTDP.NOMBRE ||' - '|| BD.NUMERO  ||' - '|| BD.FECHA_DOCUMENTO AS DOCUMENTO_PUBLICO, POR.NOMBRE AS OFICINA_ORIGEN "
		+ " FROM SDB_ACC_TURNO_HISTORIA ATH INNER JOIN SDB_ACC_SOLICITUD SAS ON SAS.ID_SOLICITUD = ATH.ID_SOLICITUD "
		+ " INNER JOIN SDB_ACC_TURNO SAT ON SAT.ID_TURNO = ATH.ID_TURNO "
		+ " INNER JOIN SDB_BGN_DOCUMENTO BD ON BD.ID_DOCUMENTO = SAS.ID_DOCUMENTO AND BD.VERSION_DOCUMENTO = SAS.VERSION_DOCUMENTO "
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO SPTDP ON SPTDP.ID_TIPO_DOCUMENTO = BD.ID_TIPO_DOCUMENTO INNER JOIN SDB_ACC_ACTO SAA ON SAA.ID_SOLICITUD = SAS.ID_SOLICITUD "
		+ " INNER JOIN SDB_PGN_TIPO_ACTO SPTA ON SPTA.ID_TIPO_ACTO = SAA.ID_TIPO_ACTO "
		+ " INNER JOIN SDB_PGN_OFICINA_ORIGEN POR ON POR.ID_OFICINA_ORIGEN = BD.ID_OFICINA_ORIGEN AND POR.VERSION = (SELECT MAX(VERSION) FROM SDB_PGN_OFICINA_ORIGEN POR WHERE ID_OFICINA_ORIGEN = BD.ID_OFICINA_ORIGEN) "
		+ " WHERE ATH.ID_ETAPA = '80' AND ATH.ESTADO_ACTIVIDAD = 'XRP' AND SAT.ID_CIRCULO = ? AND SPTA.APLICA_DESBORDE = 'S'"
		+ " AND (SAS.ID_PROCESO,SAS.ID_SUBPROCESO)  NOT IN (Select S.ID_PROCESO,S.ID_SUBPROCESO from  SDB_ACC_SOLICITUD S where S.ID_PROCESO='6' and S.ID_SUBPROCESO='3' and S.ID_SOLICITUD=SAS.ID_SOLICITUD) "
		+ " GROUP BY SAS.ID_SOLICITUD, ATH.ID_TURNO_HISTORIA, SAS.NIR, ATH.ID_TURNO, SAT.FECHA_CREACION, SPTDP.NOMBRE, BD.NUMERO, BD.FECHA_DOCUMENTO, POR.NOMBRE";

	/** Constante cs_CONSULTA_DETALLE_TURNOS. */
	private static final String cs_CONSULTA_DETALLE_TURNOS = "SELECT <MAX_TURNO_HISTORIA> SAS.NIR, SAT.ID_TURNO, SAT.ID_ETAPA_ACTUAL, SPF.NOMBRE FASE_ACTUAL"
		+ ", SAP.NOMBRE PROCESO_TURNO, SPTDP.NOMBRE||' ' ||BD.NUMERO||' - '||BD.FECHA_DOCUMENTO||' ' || NVL(SPOR.NOMBRE,' ') DOCUMENTO_INFO, SAT.ID_PROCESO, BD.ID_DOCUMENTO "
		+ "FROM SDB_ACC_TURNO SAT INNER JOIN SDB_ACC_SOLICITUD SAS ON (SAS.ID_SOLICITUD=SAT.ID_SOLICITUD) INNER JOIN SDB_ACC_PROCESO SAP ON SAT.ID_PROCESO = SAP.ID_PROCESO "
		+ "LEFT OUTER JOIN SDB_ACC_TURNO_HISTORIA ATH ON ATH.ID_TURNO= SAT.ID_TURNO AND ATH.ID_ETAPA = SAT.ID_ETAPA_ACTUAL LEFT OUTER JOIN SDB_PGN_ETAPA SPA ON SPA.ID_ETAPA=SAT.ID_ETAPA_ACTUAL "
		+ "LEFT OUTER JOIN SDB_PGN_FASES SPF ON SPF.ID_FASE=SPA.ID_FASE INNER JOIN SDB_BGN_DOCUMENTO BD ON BD.ID_DOCUMENTO = SAS.ID_DOCUMENTO AND BD.VERSION_DOCUMENTO = SAS.VERSION_DOCUMENTO "
		+ "INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO SPTDP ON SPTDP.ID_TIPO_DOCUMENTO = BD.ID_TIPO_DOCUMENTO LEFT JOIN SDB_PGN_OFICINA_ORIGEN SPOR ON SPOR.ID_OFICINA_ORIGEN = BD.ID_OFICINA_ORIGEN "
		+ "AND BD.VERSION = SPOR.VERSION ";

	/** Constante cs_CONSULTA_DETALLE_TURNOS_PRORROGA. */
	private static final String cs_CONSULTA_DETALLE_TURNOS_PRORROGA = "SELECT <MAX_TURNO_HISTORIA> SAS.NIR, SAT.ID_TURNO, SAT.ID_ETAPA_ACTUAL, SPF.NOMBRE FASE_ACTUAL"
		+ ", SAP.NOMBRE PROCESO_TURNO, SPTDP.NOMBRE||' ' ||BD.NUMERO||' - '||BD.FECHA_DOCUMENTO||' ' || NVL(SPOR.NOMBRE,' ') DOCUMENTO_INFO, SAT.ID_PROCESO, BD.ID_DOCUMENTO "
		+ "FROM SDB_ACC_TURNO SAT INNER JOIN SDB_ACC_SOLICITUD SAS ON (SAS.ID_SOLICITUD=SAT.ID_SOLICITUD) INNER JOIN SDB_ACC_PROCESO SAP ON SAT.ID_PROCESO = SAP.ID_PROCESO "
		+ "LEFT OUTER JOIN SDB_ACC_TURNO_HISTORIA ATH ON ATH.ID_TURNO= SAT.ID_TURNO AND ATH.ID_ETAPA = SAT.ID_ETAPA_ACTUAL LEFT OUTER JOIN SDB_PGN_ETAPA SPA ON SPA.ID_ETAPA=SAT.ID_ETAPA_ACTUAL "
		+ "LEFT OUTER JOIN SDB_PGN_FASES SPF ON SPF.ID_FASE=SPA.ID_FASE LEFT OUTER JOIN SDB_BGN_DOCUMENTO BD ON BD.ID_DOCUMENTO = SAS.ID_DOCUMENTO AND BD.VERSION_DOCUMENTO = SAS.VERSION_DOCUMENTO "
		+ "LEFT OUTER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO SPTDP ON SPTDP.ID_TIPO_DOCUMENTO = BD.ID_TIPO_DOCUMENTO LEFT JOIN SDB_PGN_OFICINA_ORIGEN SPOR ON SPOR.ID_OFICINA_ORIGEN = BD.ID_OFICINA_ORIGEN "
		+ "AND BD.VERSION = SPOR.VERSION ";

	/** Constante cs_CONSULTAR_TURNOS_NIR_ETAPA_ESTADO. */
	private static final String cs_CONSULTAR_TURNOS_NIR_ETAPA_ESTADO = "SELECT SAT.* FROM SDB_ACC_TURNO SAT "
		+ " INNER JOIN SDB_ACC_SOLICITUD SAS ON SAT.ID_SOLICITUD = SAS.ID_SOLICITUD "
		+ " INNER JOIN SDB_ACC_TURNO_HISTORIA SATH ON SAS.ID_SOLICITUD = SATH.ID_SOLICITUD WHERE SAS.NIR = ? AND SATH.ID_ETAPA < ? "
		+ " AND SATH.ESTADO_ACTIVIDAD = ?";

	/** Constante cs_UPDATE_INTERVIENE. */
	private static final String cs_UPDATE_INTERVIENE = "UPDATE SDB_ACC_TURNO SET INTERVIENE_ENTREGA = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_SOLICITUD_APOYO. */
	private static final String cs_UPDATE_SOLICITUD_APOYO = "UPDATE SDB_ACC_TURNO SET SOLICITUD_APOYO = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_CREAR_TURNO. */
	private static final String cs_UPDATE_CREAR_TURNO = "UPDATE SDB_ACC_TURNO SET CREAR_TURNO = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_CONSULTAR_NIR_ETAPA 18. */
	private static final String cs_CONSULTA_NIR_ETAPA_18 = "SELECT SAS.NIR, SAS.ID_SOLICITUD, SATH.*, PCR.NOMBRE AS NOMBRE_CIRCULO FROM SDB_ACC_SOLICITUD SAS "
		+ "INNER JOIN SDB_ACC_TURNO_HISTORIA SATH ON SAS.ID_SOLICITUD = SATH.ID_SOLICITUD "
		+ "INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PCR ON PCR.ID_CIRCULO = SATH.ID_CIRCULO_USUARIO "
		+ "WHERE SAS.NIR = ? AND SATH.ID_ETAPA = '18' AND SATH.ESTADO_ACTIVIDAD = 'AUT' ";

	/** Constante cs_UPDATE_DIGITALIZADO. */
	private static final String cs_UPDATE_DIGITALIZADO = "UPDATE SDB_ACC_TURNO SET DIGITALIZADO = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_LIQUIDACION_CONSECUTIVO. */
	private static final String cs_UPDATE_LIQUIDACION_CONSECUTIVO = "UPDATE SDB_ACC_TURNO SET ID_LIQUIDACION = ?, CONSECUTIVO_LIQUIDACION = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_TIPO_EXPEDIENTE_EXP. */
	private static final String cs_UPDATE_TIPO_EXPEDIENTE_EXP = "UPDATE SDB_ACC_TURNO SET TIPO_EXPEDIENTE = ?, TIPO_EXPEDIENTE_SI = ?, EXPEDIENTE = ?, EXPEDIENTE_SI = ?, NOMENCLATURA_EXPEDIENTE_AA = ?, NOMENCLATURA_EXPEDIENTE_SI = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_EXPEDIENTE. */
	private static final String cs_UPDATE_EXPEDIENTE = "UPDATE SDB_ACC_TURNO SET EXPEDIENTE = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_REASIGNADO_ESPECIAL. */
	private static final String cs_UPDATE_REASIGNADO_ESPECIAL = "UPDATE SDB_ACC_TURNO SET REASIGNADO_ESPECIAL = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_ACUSE_RECIBIDO. */
	private static final String cs_UPDATE_ACUSE_RECIBIDO = "UPDATE SDB_ACC_TURNO SET "
		+ "TIPO_NOTIFICACION = ?, FECHA_NOTIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, "
		+ "IP_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_ID_MATRICULA_CERTIFICADO. */
	private static final String cs_UPDATE_ID_MATRICULA_CERTIFICADO = "UPDATE SDB_ACC_TURNO SET ID_MATRICULA_CERTIFICADO = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_REASIGNADO_ESPECIAL. */
	private static final String cs_FIND_NUEVA_ENTRADA_SOL_DEVOLUCION = "SELECT TUR.* FROM SDB_ACC_SOLICITUD SOL INNER JOIN SDB_ACC_TURNO TUR ON SOL.ID_SOLICITUD = TUR.ID_SOLICITUD WHERE SOL.ID_TURNO_ANT = ? AND SOL.ID_PROCESO = '6' AND SOL.ID_SUBPROCESO = '3' AND TUR.ID_PROCESO = '6' AND TUR.ID_ETAPA_ACTUAL IS NOT NULL";

	/** Constante cs_UPDATE_INTENTO_ENVIOS. */
	private static final String cs_UPDATE_INTENTO_ENVIOS = "UPDATE SDB_ACC_TURNO SET INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA = ?, FECHA_INTENTO_EJECUCION_AUTOMATICA = SYSTIMESTAMP, FECHA_EXITO_EJECUCION_AUTOMATICA = ?, RESPUESTAS_EJECUCION_AUTOMATICA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TURNO = ?";

	/**
	 * Actualizar acuse recibido.
	 *
	 * @param at_parametros de at parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarAcuseRecibido(Turno at_parametros)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ACUSE_RECIBIDO);

				lps_ps.setString(li_count++, at_parametros.getIdTipoNotificacion());
				setDate(lps_ps, at_parametros.getFechaAcuseReciboAviso(), li_count++);

				lps_ps.setString(li_count++, at_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_count++, at_parametros.getIpModificacion());
				lps_ps.setString(li_count++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarAcuseRecibido", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el campo digitalizado.
	 *
	 * @param at_parametros Argumento de tipo <code>Turno</code> que contiene los parametros para realizar la actualización.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarDigitalizado(Turno at_parametros, String as_userId)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_DIGITALIZADO);

				lps_ps.setString(li_count++, at_parametros.getDigitalizado());
				lps_ps.setString(li_count++, as_userId);
				lps_ps.setString(li_count++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarDigitalizado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el expediente.
	 *
	 * @param as_expediente Variable que contiene el expediente.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarExpediente(String as_expediente, String as_userId, String as_idTurno)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_expediente) && StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_UPDATE_EXPEDIENTE);

				lps_ps.setString(li_count++, as_expediente);
				lps_ps.setString(li_count++, as_userId);
				lps_ps.setString(li_count++, as_idTurno);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarExpediente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el campo id matricula certificado.
	 *
	 * @param at_parametros Argumento de tipo <code>Turno</code> que contiene los parametros para realizar la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarIdMatriculaCertificado(Turno at_parametros)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ID_MATRICULA_CERTIFICADO);

				lps_ps.setString(li_count++, at_parametros.getIdMatriculaCertificado());
				lps_ps.setString(li_count++, at_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_count++, at_parametros.getIpModificacion());
				lps_ps.setString(li_count++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarIdMatriculaCertificado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el campo digitalizado.
	 *
	 * @param at_parametros Argumento de tipo <code>Turno</code> que contiene los parametros para realizar la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarInterviene(Turno at_parametros)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_INTERVIENE);

				lps_ps.setString(li_count++, at_parametros.getIntervieneEntrega());
				lps_ps.setString(li_count++, at_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_count++, at_parametros.getIpModificacion());
				lps_ps.setString(li_count++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarInterviene", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualizar liquidacion consecutivo.
	 *
	 * @param at_parametros de at parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarLiquidacionConsecutivo(Turno at_parametros)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_LIQUIDACION_CONSECUTIVO);

				lps_ps.setString(li_count++, at_parametros.getIdLiquidacion());
				lps_ps.setLong(li_count++, at_parametros.getConsecutivoLiquidacion());
				lps_ps.setString(li_count++, at_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_count++, at_parametros.getIpModificacion());
				lps_ps.setString(li_count++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarLiquidacionConsecutivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el campo reasignado especial.
	 *
	 * @param at_parametros Argumento de tipo <code>Turno</code> que contiene los parametros para realizar la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarReasignadoEspecial(Turno at_parametros)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_REASIGNADO_ESPECIAL);

				lps_ps.setString(li_count++, at_parametros.getReasignadoEspecial());
				lps_ps.setString(li_count++, at_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_count++, at_parametros.getIpModificacion());
				lps_ps.setString(li_count++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarReasignadoEspecial", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el campo solicitud de apoyo.
	 *
	 * @param at_parametros Argumento de tipo <code>Turno</code> que contiene los parametros para realizar la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarSolicitudApoyo(Turno at_parametros)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_SOLICITUD_APOYO);

				lps_ps.setString(li_count++, at_parametros.getSolicitudApoyo());
				lps_ps.setString(li_count++, at_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_count++, at_parametros.getIpModificacion());
				lps_ps.setString(li_count++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarSolicitudApoyo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar tipo expediente y expediente.
	 *
	 * @param at_parametros Argumento de tipo <code>Turno</code> que contiene los parametros para realizar la actualización.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarTipoExpedienteExp(Turno at_parametros, String as_userId)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_TIPO_EXPEDIENTE_EXP);

				lps_ps.setString(li_count++, at_parametros.getTipoExpediente());
				lps_ps.setString(li_count++, at_parametros.getTipoExpedienteSI());
				lps_ps.setString(li_count++, at_parametros.getExpediente());
				lps_ps.setString(li_count++, at_parametros.getExpedienteSI());
				lps_ps.setString(li_count++, at_parametros.getNomemclaturaExpedienteAA());
				lps_ps.setString(li_count++, at_parametros.getNomemclaturaExpedienteSI());
				lps_ps.setString(li_count++, as_userId);
				lps_ps.setString(li_count++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarTipoExpedienteExp", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Buscar nir por id turno.
	 *
	 * @param as_idTurno de is turno
	 * @return el valor de Turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Turno buscarNirPorIdTurno(String as_idTurno)
	    throws B2BException
	{
		Turno lt_turno;

		lt_turno = null;

		if(as_idTurno != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_BUSCAR_NIR_POR_ID_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lt_turno = getNirIdSolicitudIdTurno(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarNirPorIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lt_turno;
	}

	/**
	 * Método encargado de consultar los turnos de la tabla SDB_ACC_TURNO para un id
	 * documento determinado o un turno y matricula existente.
	 *
	 * @param ad_documento            objeto de tipo documento que contiene los datos necesarios para
	 *            ejecutar la consulta
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return Collection<Turno> colección de datos de tipo turnos con todos los
	 *         registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> consultaDetalleTurnos(Documento ad_documento, String as_param)
	    throws B2BException
	{
		Collection<Turno> ld_datosdocumentos;

		ld_datosdocumentos = new ArrayList<Turno>();

		if(ad_documento != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				boolean lb_accion;
				boolean lb_nir;
				boolean lb_procesoRecepcion;
				boolean lb_proceso39;
				boolean lb_proceso40;
				boolean lb_proceso41;
				boolean lb_proceso42;
				boolean lb_proceso43;
				boolean lb_proceso45;
				boolean lb_proceso47;
				boolean lb_proceso48;

				int           li_count;
				StringBuilder lsb_sb;

				String ls_idProcesoRecepcion;
				String ls_nir;

				lb_accion     = ad_documento.isAccion();
				lb_nir        = false;

				lsb_sb       = new StringBuilder(cs_CONSULTA_DETALLE_TURNOS);
				li_count     = 1;

				ls_idProcesoRecepcion     = ad_documento.getIdProcesoRecepcion();
				ls_nir                    = ad_documento.getNir();

				lb_procesoRecepcion     = StringUtils.isValidString(ls_idProcesoRecepcion);
				lb_proceso39            = lb_procesoRecepcion
						&& ls_idProcesoRecepcion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39);
				lb_proceso40            = lb_procesoRecepcion
						&& ls_idProcesoRecepcion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40);
				lb_proceso41            = lb_procesoRecepcion
						&& ls_idProcesoRecepcion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41);
				lb_proceso42            = lb_procesoRecepcion
						&& ls_idProcesoRecepcion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_42);
				lb_proceso43            = lb_procesoRecepcion
						&& ls_idProcesoRecepcion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43);
				lb_proceso45            = lb_procesoRecepcion
						&& ls_idProcesoRecepcion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_45);
				lb_proceso47            = lb_procesoRecepcion
						&& ls_idProcesoRecepcion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47);
				lb_proceso48            = lb_procesoRecepcion
						&& ls_idProcesoRecepcion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48);

				if(lb_proceso41)
					lsb_sb = new StringBuilder(cs_CONSULTA_DETALLE_TURNOS_PRORROGA);

				if(lb_proceso47 || lb_proceso48)
				{
					long ll_idEtapa;

					ll_idEtapa = ad_documento.getIdEtapa();

					if(
					    NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idEtapa))
						    && (ll_idEtapa == EtapaCommon.EN_ESPERA_INTERPOSICION_DE_RECURSOS_CORRECCIONES)
					)
						lsb_sb = new StringBuilder(cs_CONSULTA_DETALLE_TURNOS_PRORROGA);
				}

				if(lb_proceso40)
				{
					long ll_idEtapa;

					ll_idEtapa = ad_documento.getIdEtapa();

					if(
					    NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idEtapa))
						    && ((ll_idEtapa == EtapaCommon.ID_ETAPA_PENDIENTE_COMPLETITUD_DOCUMENTAL_CORRECIONES)
						    || (ll_idEtapa == EtapaCommon.ID_ETAPA_SUSPENSION_PRORROGA_PARA_APORTAR_DOCUMENTACION)
						    || (ll_idEtapa == EtapaCommon.PENDIENTE_APORTAR_PRUEBAS_ACTUACION_ADMINISTRATIVA)
						    || (ll_idEtapa == EtapaCommon.SUSPENSION_PRORROGA_PARA_APORTAR_DOCUMENTACION)
						    || (ll_idEtapa == EtapaCommon.ID_ETAPA_PENDIENTE_APORTAR_DOCUMENTACION_TRASLADOS)
						    || (ll_idEtapa == EtapaCommon.SUSPENSION_PRORROGA_PARA_APORTAR_DOCUMENTACION_RECURSOS_SI)
						    || (ll_idEtapa == EtapaCommon.SUSPENSION_PRORROGA_PARA_APORTAR_PRORROGA_SI)
						    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
						    || (ll_idEtapa == EtapaCommon.ID_ETAPA_APROBACION)
					))
						lsb_sb = new StringBuilder(cs_CONSULTA_DETALLE_TURNOS_PRORROGA);
				}

				if(!lb_accion)
				{
					lb_nir = StringUtils.isValidString(ls_nir);

					lsb_sb.append(" WHERE SAT.ID_TURNO = ? ");

					if(lb_nir)
						lsb_sb.append(" AND SAS.NIR = ?");
				}
				else
					lsb_sb.append(" WHERE SAS.VERSION_DOCUMENTO = ? AND SAS.ID_DOCUMENTO = ?");

				if(lb_proceso43)
					lsb_sb.append(
					    " AND SAT.ID_ETAPA_ACTUAL IN (SELECT ID_ETAPA FROM SDB_PGN_ETAPA WHERE ID_ETAPA IN (XXXXXXXXXXXXXX)) AND ATH.ESTADO_ACTIVIDAD = 'TER'"
					);
				else if(lb_proceso39)
					lsb_sb.append(
					    " GROUP BY ATH.ID_ETAPA, SAS.NIR, SAT.ID_TURNO, SAT.ID_ETAPA_ACTUAL, SPF.NOMBRE, SAP.NOMBRE, SPTDP.NOMBRE||' ' ||BD.NUMERO||' - '||BD.FECHA_DOCUMENTO||' ' || NVL(SPOR.NOMBRE,' '), SAT.ID_PROCESO, BD.ID_DOCUMENTO"
					);
				else if(lb_proceso40 || lb_proceso41 || lb_proceso42)
					lsb_sb.append(
					    " AND SAT.ID_ETAPA_ACTUAL IN (SELECT ID_ETAPA FROM SDB_PGN_ETAPA WHERE ID_ETAPA >= 10 AND ID_ETAPA <= 287 OR ID_ETAPA >= 290 AND ID_ETAPA <= 292 OR ID_ETAPA >= 400 AND ID_ETAPA< 500 ) AND ATH.ESTADO_ACTIVIDAD <> 'TER'"
					);
				else if(lb_proceso45)
					lsb_sb.append(
					    " AND SAT.ID_ETAPA_ACTUAL IN (SELECT ID_ETAPA FROM SDB_PGN_ETAPA WHERE ID_ETAPA >= 10 AND ID_ETAPA <= 199) AND ATH.ESTADO_ACTIVIDAD <> 'TER'"
					);
				else if(lb_proceso47 || lb_proceso48)
					lsb_sb.append(
					    " AND SAT.ID_ETAPA_ACTUAL IN (SELECT ID_ETAPA FROM SDB_PGN_ETAPA WHERE ID_ETAPA = 400 OR ID_ETAPA = 401 OR ID_ETAPA = 501)"
					);
				else
					lsb_sb.append(
					    " AND SAT.ID_ETAPA_ACTUAL IN (SELECT ID_ETAPA FROM SDB_PGN_ETAPA WHERE ID_ETAPA >= 10 AND ID_ETAPA <= 190 OR ID_ETAPA >= 400 AND ID_ETAPA< 500 ) AND ATH.ESTADO_ACTIVIDAD <> 'TER'"
					);

				String ls_consulta;

				ls_consulta = lsb_sb.toString();

				if(lb_proceso43)
					ls_consulta = ls_consulta.replace("XXXXXXXXXXXXXX", as_param);

				ls_consulta     = ls_consulta.replace(
					    "<MAX_TURNO_HISTORIA>", lb_proceso39 ? "MAX(ATH.ID_TURNO_HISTORIA)," : ""
					);

				lps_ps = getConnection().prepareStatement(ls_consulta);

				if(!lb_accion)
				{
					lps_ps.setString(li_count++, ad_documento.getIdTurno());

					if(lb_nir)
						lps_ps.setString(li_count++, ls_nir);
				}
				else
				{
					setLong(lps_ps, ad_documento.getVersionDocumento(), li_count++);
					lps_ps.setString(li_count++, ad_documento.getIdDocumento());
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ld_datosdocumentos.add(detalleTurno(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaDetalleTurnos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ld_datosdocumentos.isEmpty())
			ld_datosdocumentos = null;

		return ld_datosdocumentos;
	}

	/**
	 * Método encargado de consultar los turnos de la tabla SDB_ACC_TURNO para un id
	 * turno existente.
	 *
	 * @param as_turno            objeto de tipo string que contiene el dato necesario para ejecutar
	 *            la consulta
	 * @param ab_restitucion de ab restitucion
	 * @return Collection<Turno> colección de datos de tipo turnos con todos los
	 *         registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> consultaDetalleTurnosPDF(String as_turno, boolean ab_restitucion)
	    throws B2BException
	{
		Collection<Turno> ld_datosdocumentos;

		ld_datosdocumentos = new ArrayList<Turno>();

		if(as_turno != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_count;
				StringBuilder lsb_sb;

				lsb_sb       = new StringBuilder(cs_CONSULTA_DETALLE_TURNOS_PRORROGA);
				li_count     = 1;

				lsb_sb.append(" WHERE SAT.ID_TURNO = ? ");

				if(ab_restitucion)
					lsb_sb.append(
					    " AND SAT.ID_ETAPA_ACTUAL IN (SELECT ID_ETAPA FROM SDB_PGN_ETAPA WHERE ID_ETAPA >= 10 AND ID_ETAPA< 515 )"
					);
				else
					lsb_sb.append(
					    " AND SAT.ID_ETAPA_ACTUAL IN (SELECT ID_ETAPA FROM SDB_PGN_ETAPA WHERE ID_ETAPA >= 10 AND ID_ETAPA< 500 ) AND ATH.ESTADO_ACTIVIDAD <> 'TER'"
					);

				String ls_consulta;

				ls_consulta     = lsb_sb.toString();

				ls_consulta     = ls_consulta.replace("<MAX_TURNO_HISTORIA>", "");

				lps_ps = getConnection().prepareStatement(ls_consulta);

				lps_ps.setString(li_count++, as_turno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ld_datosdocumentos.add(detalleTurno(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaDetalleTurnosPDF", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ld_datosdocumentos.isEmpty())
			ld_datosdocumentos = null;

		return ld_datosdocumentos;
	}

	/**
	 * Método encargado de consultar los turno historia de la tabla SDB_ACC_TURNO para un nir que este en etapa 18.
	 *
	 * @param as_nir Argumento de tipo <code>String</code> que contiene el nir para realizar la consulta.
	 * @return Collection<Turno> Colección de datos de tipo turnos con todos los registros encontrados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Turno> consultarNirEtapa18(String as_nir)
	    throws B2BException
	{
		Collection<Turno> lct_turnos;

		lct_turnos = new ArrayList<Turno>();

		if(StringUtils.isValidString(as_nir))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_CONSULTA_NIR_ETAPA_18);

				lps_ps.setString(li_count++, as_nir);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_turnos.add(detalleNir(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultarNirEtapa18", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_turnos.isEmpty())
			lct_turnos = null;

		return lct_turnos;
	}

	/**
	 * Método encargado de consultar los turnos de la tabla SDB_ACC_TURNO para un nir y estado actividad de etapas menores a las ingresadas.
	 *
	 * @param as_nir Argumento de tipo <code>String</code> que contiene el nir para realizar la consulta.
	 * @param ai_etapa Argumento de tipo <code>int</code> que contiene la etapa para realizar la consulta.
	 * @param as_estadoActividad Argumento de tipo <code>String</code> que contiene el estado actividad para realizar la consulta.
	 * @return Collection<Turno> Colección de datos de tipo turnos con todos los registros encontrados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Turno> consultarTurnosNirEtapaEstado(String as_nir, int ai_etapa, String as_estadoActividad)
	    throws B2BException
	{
		Collection<Turno> lct_turnos;

		lct_turnos = new ArrayList<Turno>();

		if(StringUtils.isValidString(as_nir) && (ai_etapa > 0) && StringUtils.isValidString(as_estadoActividad))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_CONSULTAR_TURNOS_NIR_ETAPA_ESTADO);

				lps_ps.setString(li_count++, as_nir);
				lps_ps.setInt(li_count++, ai_etapa);
				lps_ps.setString(li_count++, as_estadoActividad);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_turnos.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultarTurnosNirEtapaEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_turnos.isEmpty())
			lct_turnos = null;

		return lct_turnos;
	}

	/**
	 * Encuentra todos los turnos de apoyo nacional por circulo.
	 *
	 * @param as_idCirculo de as id circulo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Turno> findAllApoyoNacionalByCirculo(String as_idCirculo)
	    throws B2BException
	{
		Collection<Turno> lct_datos;

		lct_datos = new LinkedList<Turno>();

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_CONSULTA_DETALLE_TURNOS_APOYO_NACIONAL);

				lps_ps.setString(li_cont++, as_idCirculo);
				lps_ps.setString(li_cont++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_datos.add(detalleTurnoApoyoNacional(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllApoyoNacionalByCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_datos.isEmpty())
			lct_datos = null;

		return lct_datos;
	}

	/**
	 * Find all by crear turno.
	 *
	 * @param as_crearTurno de as crear turno
	 * @param al_cantidadIntentosMax de al cantidad intentos max
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Turno> findAllByCrearTurno(String as_crearTurno, long al_cantidadIntentosMax)
	    throws B2BException
	{
		Collection<Turno> lct_datos;

		lct_datos = new LinkedList<Turno>();

		if(StringUtils.isValidString(as_crearTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_CREAR_TURNO);

				lps_ps.setString(li_cont++, as_crearTurno);
				lps_ps.setLong(li_cont++, al_cantidadIntentosMax);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_datos.add(detalleCrearTurno(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCrearTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_datos.isEmpty())
			lct_datos = null;

		return lct_datos;
	}

	/**
	 * Metodo que se encarga de buscar por id_solicitud y id_proceso el turno de la
	 * tabla SDB_ACC_TURNO.
	 *
	 * @param at_param            Objeto que contiene id_solicitud y id_proceso que se usa para
	 *            realizar la consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> findAllByIdSolicitudProceso(Turno at_param)
	    throws B2BException
	{
		Collection<Turno> lct_return;

		lct_return = new ArrayList<Turno>();

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_PROCESO);

				lps_ps.setString(1, at_param.getIdSolicitud());
				setLong(lps_ps, NumericUtils.getLongWrapper(at_param.getIdProceso()), 2);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdSolicitudProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lct_return;
	}

	/**
	 * Metodo que se encarga de buscar por id_solicitud,id_proceso y id_circulo el
	 * turno de la tabla SDB_ACC_TURNO.
	 *
	 * @param at_param            Objeto que contiene id_solicitud y id_proceso que se usa para
	 *            realizar la consulta
	 * @return <code>Turno</code> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> findAllByIdSolicitudProcesoCirculo(Turno at_param)
	    throws B2BException
	{
		Collection<Turno> lct_return;

		lct_return = new ArrayList<Turno>();

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_PROCESO_CIRCULO);

				lps_ps.setString(1, at_param.getIdSolicitud());
				setLong(lps_ps, NumericUtils.getLongWrapper(at_param.getIdProceso()), 2);
				lps_ps.setString(3, at_param.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdSolicitudProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lct_return;
	}

	/**
	 * Metodo que se encarga de buscar por llave primaria el turno de la tabla
	 * SDB_ACC_TURNO.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Turno
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno findById(Turno at_param)
	    throws B2BException
	{
		return (at_param != null) ? findById(at_param.getIdTurno()) : null;
	}

	/**
	 * Metodo que se encarga de buscar por llave primaria el turno de la tabla
	 * SDB_ACC_TURNO.
	 *
	 * @param as_param            Objeto que contiene la llave primaria que se usa para realizar la
	 *            consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno findById(String as_param)
	    throws B2BException
	{
		Turno lt_turno;

		lt_turno = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lt_turno = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lt_turno;
	}

	/**
	 * Metodo que se encarga de buscar por llave primaria el turno de la tabla
	 * SDB_ACC_TURNO.
	 *
	 * @param at_param            Objeto que contiene la llave primaria y el circulo que se usan
	 *            para realizar la consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno findByIdCirculo(Turno at_param)
	    throws B2BException
	{
		Turno lt_turno;

		lt_turno = null;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_CIRCULO);

				lps_ps.setString(1, at_param.getIdTurno());
				lps_ps.setString(2, at_param.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lt_turno = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lt_turno;
	}

	/**
	 * Busca un turno asociado a un id datos ant sistema específico.
	 *
	 * @param as_idDatosAntSistema Objeto contenedor del id datos ant sistema a utilizar como filtro en la consulta
	 * @return Turno resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno findByIdDatosAntSistema(String as_idDatosAntSistema)
	    throws B2BException
	{
		Turno lt_turno;

		lt_turno = null;

		if(StringUtils.isValidString(as_idDatosAntSistema))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_BY_ID_DATOS_ANT_SISTEMA);

				lsb_query.append(" AND ID_PROCESO = 1 ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_idDatosAntSistema);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lt_turno = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdDatosAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lt_turno;
	}

	/**
	 * Metodo que se encarga de buscar por id_solicitud el turno de la tabla
	 * SDB_ACC_TURNO.
	 *
	 * @param at_turno            Objeto que contiene id_solicitud que se usa para realizar la
	 *            consulta
	 * @return Collection<Turno> colección de datos de tipo turnos con todos los
	 *         registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> findByIdSolicitud(Turno at_turno)
	    throws B2BException
	{
		return (at_turno != null) ? findByIdSolicitud(at_turno.getIdSolicitud()) : null;
	}

	/**
	 * Metodo que se encarga de buscar por id_solicitud el turno de la tabla
	 * SDB_ACC_TURNO.
	 *
	 * @param as_idSolicitud            Objeto que contiene id_solicitud que se usa para realizar la
	 *            consulta
	 * @return Collection<Turno> colección de datos de tipo turnos con todos los
	 *         registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<Turno> ls_object;

		ls_object = new ArrayList<Turno>();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Metodo que se encarga de buscar por id_solicitud el turno de la tabla
	 * SDB_ACC_TURNO en dadas etapas.
	 *
	 * @param as_idSolicitud            Objeto que contiene id_solicitud que se usa para realizar la
	 *            consulta
	 * @param al_etapaRangoInicial            Objeto que contiene al_etapaRangoInicial que se usa para realizar la
	 *            consulta
	 * @param al_etapaRangoFinal            Objeto que contiene al_etapaRangoFinal que se usa para realizar la
	 *            consulta
	 * @return Collection<Turno> colección de datos de tipo turnos con todos los
	 *         registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> findByIdSolicitudEtapa(
	    String as_idSolicitud, Long al_etapaRangoInicial, Long al_etapaRangoFinal
	)
	    throws B2BException
	{
		Collection<Turno> lct_turno;

		lct_turno = new ArrayList<Turno>();

		if(
		    StringUtils.isValidString(as_idSolicitud) && NumericUtils.isValidLong(al_etapaRangoInicial)
			    && NumericUtils.isValidLong(al_etapaRangoFinal)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ETAPA);

				lps_ps.setString(li_count++, as_idSolicitud);
				setLong(lps_ps, al_etapaRangoInicial, li_count++);
				setLong(lps_ps, al_etapaRangoFinal, li_count++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_turno.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_turno.isEmpty())
			lct_turno = null;

		return lct_turno;
	}

	/**
	 * Metodo que se encarga de buscar por id_solicitud y id_proceso el turno de la
	 * tabla SDB_ACC_TURNO.
	 *
	 * @param at_param            Objeto que contiene id_solicitud y id_proceso que se usa para
	 *            realizar la consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno findByIdSolicitudProceso(Turno at_param)
	    throws B2BException
	{
		Turno ls_object;
		ls_object = null;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_PROCESO);

				lps_ps.setString(1, at_param.getIdSolicitud());
				setLong(lps_ps, NumericUtils.getLongWrapper(at_param.getIdProceso()), 2);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_object;
	}

	/**
	 * Busca los turnos relacionados a una solicitud e id proceso específicos omitiendo al turno desde el cual se hace la consulta.
	 *
	 * @param as_idSolicitud id de la solicitud a utilizar como filtro en la busqueda
	 * @param as_idProceso id del proceso a utilizar como filtro en la busqueda
	 * @param as_idTurno id del turno desde el cual se realiza la consulta
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public Collection<Turno> findByIdSolicitudProcesoDifTurno(
	    String as_idSolicitud, String as_idProceso, String as_idTurno
	)
	    throws B2BException
	{
		Collection<Turno> lct_return;

		lct_return = new LinkedList<Turno>();

		if(
		    StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idProceso)
			    && StringUtils.isValidString(as_idTurno)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_cont;
				StringBuilder lsb_query;

				li_cont       = 1;
				lsb_query     = new StringBuilder(cs_FIND_BY_ID_SOLICITUD_PROCESO);

				lsb_query.append(" AND ID_TURNO != ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, as_idSolicitud);
				lps_ps.setString(li_cont++, as_idProceso);
				lps_ps.setString(li_cont++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudProcesoDifTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_return.isEmpty())
			lct_return = null;

		return lct_return;
	}

	/**
	 * Método encargado de consultar el turno con base al id turno y/o expediente.
	 *
	 * @param as_idTurno Variable que contiene el id turno.
	 * @param as_expediente Variable que contiene el expediente.
	 * @return Objeto que contiene la información del turno.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Turno findByIdTurnoExpediente(String as_idTurno, String as_expediente)
	    throws B2BException
	{
		Turno lt_turno;

		lt_turno = null;

		if(StringUtils.isValidString(as_idTurno) || StringUtils.isValidString(as_expediente))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				boolean       lb_turno;
				boolean       lb_expediente;
				int           li_column;
				StringBuilder lsb_sb;

				lb_turno          = StringUtils.isValidString(as_idTurno);
				lb_expediente     = StringUtils.isValidString(as_expediente);
				li_column         = 1;
				lsb_sb            = new StringBuilder();

				if(lb_turno)
				{
					lsb_sb.append(cs_FIND_BY_ID);

					if(lb_expediente)
						lsb_sb.append(" AND EXPEDIENTE=? ");
				}
				else
					lsb_sb.append(cs_FIND_BY_EXPEDIENTE);

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(lb_turno)
					lps_ps.setString(li_column++, as_idTurno);

				if(lb_expediente)
					lps_ps.setString(li_column++, as_expediente);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lt_turno = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoExpediente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lt_turno;
	}

	/**
	 * Metodo que se encarga de buscar por el nir el turno de la tabla SDB_ACC_TURNO.
	 *
	 * @param at_param            Objeto que contiene la llave primaria que se usa para realizar la
	 *            consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> findByNir(Turno at_param)
	    throws B2BException
	{
		Collection<Turno> lct_turno;

		lct_turno = new ArrayList<Turno>();

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_NIR);

				lps_ps.setString(1, at_param.getNir());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_turno.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNir", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_turno.isEmpty())
			lct_turno = null;

		return lct_turno;
	}

	/**
	 * Metodo que se encarga de buscar por id_solicitud, id_proceso (6) y id_circulo
	 * el turno de la tabla SDB_ACC_TURNO.
	 *
	 * @param at_param            Objeto que contiene id_solicitud y id_circulo que se usa para
	 *            realizar la consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno findBySolicitudProceso(Turno at_param)
	    throws B2BException
	{
		Turno lt_return;

		lt_return = null;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_count;

				lsb_sb       = new StringBuilder();
				li_count     = 1;

				lsb_sb.append(cs_FIND_BY_ID_SOLICITUD);
				lsb_sb.append(" AND ID_PROCESO = 6 AND ID_CIRCULO = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, at_param.getIdSolicitud());
				lps_ps.setString(li_count++, at_param.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lt_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitudProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lt_return;
	}

	/**
	 * Metodo que se encarga de buscar por llave primaria el turno de la tabla
	 * SDB_ACC_TURNO.
	 *
	 * @param as_param            Objeto que contiene la llave primaria que se usa para realizar la
	 *            consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno findByTurnoTipoActoPermuta(String as_param)
	    throws B2BException
	{
		Turno lt_turno;

		lt_turno = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_TIPO_ACTO_PERMUTA);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lt_turno = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoTipoActoPermuta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lt_turno;
	}

	/**
	 * Metodo que se encarga de buscar todos los registros por la solicitud y
	 * circulo de la tabla SDB_ACC_TURNO.
	 *
	 * @param at_param            Objeto que contiene la llave primaria que se usa para realizar la
	 *            consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> findByTurnosDiferenteCirculo(Turno at_param)
	    throws B2BException
	{
		Collection<Turno> lct_turno;

		lct_turno = new ArrayList<Turno>();

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD_DIF_CIRCULO);

				lps_ps.setString(1, at_param.getIdSolicitud());
				lps_ps.setString(2, at_param.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_turno.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoTipoActoPermuta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_turno.isEmpty())
			lct_turno = null;

		return lct_turno;
	}

	/**
	 * Metodo que se encarga de buscar por llave primaria el turno de la tabla
	 * SDB_ACC_TURNO.
	 *
	 * @param at_param            Objeto que contiene la llave primaria que se usa para realizar la
	 *            consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno findNombreEtapaById(Turno at_param)
	    throws B2BException
	{
		return (at_param != null) ? findNombreEtapaById(at_param.getIdTurno()) : null;
	}

	/**
	 * Metodo que se encarga de buscar por llave primaria el turno de la tabla
	 * SDB_ACC_TURNO.
	 *
	 * @param as_idTurno            Objeto que contiene la llave primaria que se usa para realizar la
	 *            consulta
	 * @return <Turno> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno findNombreEtapaById(String as_idTurno)
	    throws B2BException
	{
		Turno lt_turno;

		lt_turno = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_NOMBRE_ETAPA_BY_ID);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lt_turno = getNombreEtapaActual(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findNombreEtapaById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lt_turno;
	}

	/**
	 * Find nueva entrad sol devolucion.
	 *
	 * @param as_idTurnoAnterior Parámetro como filtrop de búsqueda
	 * @return lct_return Colección de Turnos consultados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Turno> findNuevaEntradSolDevolucion(String as_idTurnoAnterior)
	    throws B2BException
	{
		Collection<Turno> lct_return;

		lct_return = new ArrayList<Turno>();

		if(StringUtils.isValidString(as_idTurnoAnterior))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_NUEVA_ENTRADA_SOL_DEVOLUCION);

				lps_ps.setString(1, as_idTurnoAnterior);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findNuevaEntradSolDevolucion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_return.isEmpty())
			lct_return = null;

		return lct_return;
	}

	/**
	 * Metodo que se encarga de buscar recurrentemente por id_turno el turno de la
	 * tabla SDB_ACC_TURNO.
	 *
	 * @param at_param            Objeto que contiene id_turno que se usa para realizar la consulta
	 * @return Collection<Turno> colección de datos de tipo turnos con todos los
	 *         registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Turno> findTurnosRecurrente(Turno at_param)
	    throws B2BException
	{
		Collection<Turno> lct_datos;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lct_datos     = new ArrayList<Turno>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_CONSULTA_RECURRENTE_TURNOS);

			lps_ps.setString(1, at_param.getIdTurno());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				Turno lt_tmp;
				lt_tmp = new Turno();

				lt_tmp.setIdTurno(lrs_rs.getString("ID_TURNO"));
				lt_tmp.setIdTurnoAnt(lrs_rs.getString("ID_TURNO_ANT"));

				lct_datos.add(lt_tmp);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findTurnosRecurrente", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lct_datos.isEmpty())
			lct_datos = null;

		return lct_datos;
	}

	/**
	 * Update crear turno.
	 *
	 * @param at_parametros de at parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateCrearTurno(Turno at_parametros)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_CREAR_TURNO);

				lps_ps.setString(li_count++, at_parametros.getCrearTurno());
				lps_ps.setString(li_count++, at_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_count++, at_parametros.getIpModificacion());
				lps_ps.setString(li_count++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateCrearTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update intento envios.
	 *
	 * @param at_parametros de at parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateIntentoEnvios(Turno at_parametros)
	    throws B2BException
	{
		if(at_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_INTENTO_ENVIOS);

				lps_ps.setLong(li_column++, at_parametros.getIntentosFallidosEjecucionAutomatica());
				setDate(lps_ps, at_parametros.getFechaExitoEjecucionAutomatica(), li_column++);
				lps_ps.setString(li_column++, at_parametros.getRespuestaEjecucionAutomatica());
				lps_ps.setString(li_column++, at_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, at_parametros.getIpModificacion());
				lps_ps.setString(li_column++, at_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "at_parametros", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna Objeto o variable de valor turno.
	 *
	 * @param ars_rs de ResultSet
	 * @return el valor de Turno
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Turno getNirIdSolicitudIdTurno(ResultSet ars_rs)
	    throws SQLException
	{
		Turno lt_turno;

		lt_turno = new Turno();

		lt_turno.setNir(ars_rs.getString("NIR"));
		lt_turno.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lt_turno.setIdTurno(ars_rs.getString("ID_TURNO"));

		return lt_turno;
	}

	/**
	 * Retorna Objeto o variable de valor nombre etapa actual.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de nombre etapa actual
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Turno getNombreEtapaActual(ResultSet ars_rs)
	    throws SQLException
	{
		Turno lt_turno;

		lt_turno = getObjectFromResultSet(ars_rs);

		lt_turno.setNombreEtapaActual(ars_rs.getString("NOMBRE_ETAPA_ACTUAL"));

		return lt_turno;
	}

	/**
	 * Metodo para obtener objeto de tipo <Turno>.
	 *
	 * @param ars_rs            objeto que representa el resultado de una sentencia sql en la base
	 *            de datos
	 * @return <Turno> instanciado con todos los registros encontrados.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Turno getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Turno lt_turno;
		lt_turno = getObjectFromResultSet(ars_rs, false);

		return lt_turno;
	}

	/**
	 * Metodo para obtener objeto de tipo <Turno>.
	 *
	 * @param ars_rs            objeto que representa el resultado de una sentencia sql en la base
	 *            de datos
	 * @param ab_nir de ab nir
	 * @return <Turno> instanciado con todos los registros encontrados.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Turno getObjectFromResultSet(ResultSet ars_rs, boolean ab_nir)
	    throws SQLException
	{
		Turno  lt_turno;
		Object lo_o;

		lt_turno = new Turno();

		if(ab_nir)
			lt_turno.setNir(ars_rs.getString("NIR"));

		lt_turno.setIdTurno(ars_rs.getString("ID_TURNO"));
		lt_turno.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lt_turno.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lt_turno.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lt_turno.setIdSubProceso(ars_rs.getString("ID_SUBPROCESO"));
		lt_turno.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lt_turno.setAnio(ars_rs.getString("ANIO"));
		lt_turno.setFechaInicio(ars_rs.getTimestamp("FECHA_INICIO"));
		lt_turno.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lt_turno.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		lt_turno.setTiempoVencimiento(NumericUtils.getLong(getLong(ars_rs, "TIEMPO_VENCIMIENTO")));
		lt_turno.setIdUnidadTiempo(ars_rs.getString("ID_UNIDAD_TIEMPO"));
		lt_turno.setIdUsuario(getBigDecimal(ars_rs, "ID_USUARIO"));
		lt_turno.setUsuarioAnt(ars_rs.getString("USUARIO_ANT"));
		lt_turno.setAnulado(ars_rs.getString("ANULADO"));
		lt_turno.setNroradica(ars_rs.getString("NRORADICA"));
		lt_turno.setActoPrincipal(ars_rs.getString("ACTO_PRINCIPAL"));
		lt_turno.setLineaProduccion(ars_rs.getString("LINEA_PRODUCCION"));
		lt_turno.setEquivalenciaTurnos(getBigDecimal(ars_rs, "EQUIVALENCIA_TURNOS"));
		lt_turno.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));

		lo_o = ars_rs.getObject("SECUENCIA");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lt_turno.setSecuencia(((BigDecimal)lo_o).toBigInteger());

		lt_turno.setIdMatriculaCertificado(ars_rs.getString("ID_MATRICULA_CERTIFICADO"));
		lt_turno.setIdEtapaActual(getLong(ars_rs, "ID_ETAPA_ACTUAL"));
		lt_turno.setConsecutivoTurno(getBigDecimal(ars_rs, "CONSECUTIVO_TURNO"));
		lt_turno.setIdTurnoAnt(ars_rs.getString("ID_TURNO_ANT"));
		lt_turno.setMigrado(ars_rs.getString("MIGRADO"));
		lt_turno.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lt_turno.setReasignadoEspecial(ars_rs.getString("REASIGNADO_ESPECIAL"));
		lt_turno.setTipoExpediente(ars_rs.getString("TIPO_EXPEDIENTE"));
		lt_turno.setTipoExpedienteSI(ars_rs.getString("TIPO_EXPEDIENTE_SI"));
		lt_turno.setExpediente(ars_rs.getString("EXPEDIENTE"));
		lt_turno.setExpedienteSI(ars_rs.getString("EXPEDIENTE_SI"));
		lt_turno.setNomemclaturaExpedienteAA(ars_rs.getString("NOMENCLATURA_EXPEDIENTE_AA"));
		lt_turno.setNomemclaturaExpedienteSI(ars_rs.getString("NOMENCLATURA_EXPEDIENTE_SI"));
		lt_turno.setFechaNotificacion(ars_rs.getTimestamp("FECHA_NOTIFICACION"));
		lt_turno.setIdTipoNotificacion(ars_rs.getString("TIPO_NOTIFICACION"));
		lt_turno.setDigitalizado(ars_rs.getString("DIGITALIZADO"));
		lt_turno.setConsecutivoLiquidacion(NumericUtils.getLong(getLong(ars_rs, "CONSECUTIVO_LIQUIDACION")));
		lt_turno.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));

		fillAuditoria(ars_rs, lt_turno);

		return lt_turno;
	}

	/**
	 * Detalle crear turno.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de turno
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Turno detalleCrearTurno(ResultSet ars_rs)
	    throws SQLException
	{
		Turno lt_turno;

		lt_turno = getObjectFromResultSet(ars_rs, true);

		lt_turno.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));

		return lt_turno;
	}

	/**
	 * Metodo para obtener objeto de tipo <Turno>.
	 *
	 * @param ars_rs            objeto que representa el resultado de una sentencia sql en la base
	 *            de datos
	 * @return <Turno> instanciado con todos los registros encontrados.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Turno detalleNir(ResultSet ars_rs)
	    throws SQLException
	{
		Turno lt_turno;

		lt_turno = new Turno();

		lt_turno.setNir(ars_rs.getString("NIR"));
		lt_turno.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lt_turno.setIdCirculo(ars_rs.getString("ID_CIRCULO_USUARIO"));
		lt_turno.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));
		lt_turno.setIdTurno(ars_rs.getString("ID_TURNO"));
		lt_turno.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lt_turno.setIdEtapaActual(NumericUtils.getLongWrapper(ars_rs.getString("ID_ETAPA")));

		return lt_turno;
	}

	/**
	 * Metodo para obtener objeto de tipo <Turno>.
	 *
	 * @param ars_rs            objeto que representa el resultado de una sentencia sql en la base
	 *            de datos
	 * @return <Turno> instanciado con todos los registros encontrados.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Turno detalleTurno(ResultSet ars_rs)
	    throws SQLException
	{
		Turno lt_turno;

		lt_turno = new Turno();

		lt_turno.setNir(ars_rs.getString("NIR"));
		lt_turno.setIdTurno(ars_rs.getString("ID_TURNO"));
		lt_turno.setIdFase(ars_rs.getString("FASE_ACTUAL"));
		lt_turno.setNombreProceso(ars_rs.getString("PROCESO_TURNO"));
		lt_turno.setInfoDocumento(ars_rs.getString("DOCUMENTO_INFO"));
		lt_turno.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lt_turno.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lt_turno.setIdEtapaActual(NumericUtils.getLongWrapper(ars_rs.getString("ID_ETAPA_ACTUAL")));

		return lt_turno;
	}

	/**
	 * Metodo para obtener objeto de tipo <Turno>.
	 *
	 * @param ars_rs            objeto que representa el resultado de una sentencia sql en la base
	 *            de datos
	 * @return <Turno> instanciado con todos los registros encontrados.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Turno detalleTurnoApoyoNacional(ResultSet ars_rs)
	    throws SQLException
	{
		Turno lt_turno;

		lt_turno = new Turno();

		lt_turno.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lt_turno.setIdTurnoHistoria(ars_rs.getString("ID_TURNO_HISTORIA"));
		lt_turno.setNir(ars_rs.getString("NIR"));
		lt_turno.setIdTurno(ars_rs.getString("ID_TURNO"));
		lt_turno.setMatriculasApoyoNacional(ars_rs.getString("MATRICULAS"));
		lt_turno.setActosApoyoNacional(ars_rs.getString("ACTOS"));
		lt_turno.setFechaCreacion(ars_rs.getDate("FECHA_CREACION"));
		lt_turno.setInfoDocumento(ars_rs.getString("DOCUMENTO_PUBLICO"));
		lt_turno.setNombreOficinaOrigen(ars_rs.getString("OFICINA_ORIGEN"));

		return lt_turno;
	}
}
