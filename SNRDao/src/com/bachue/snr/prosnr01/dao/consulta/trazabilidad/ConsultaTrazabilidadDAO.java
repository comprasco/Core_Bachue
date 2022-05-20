package com.bachue.snr.prosnr01.dao.consulta.trazabilidad;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.InformacionDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.ui.UsuarioActividadUI;

import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas relacionadas con Trazabilidad
 *
 * @author hcastaneda
 */
public class ConsultaTrazabilidadDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_NIR. */
	private static final String cs_FIND_BY_NIR = "SELECT ID_SOLICITUD FROM SDB_ACC_SOLICITUD WHERE NIR = ?";

	/** Constante cs_FIND_BY. */
	private static final String cs_FIND_BY = "SELECT sas.ID_SOLICITUD, sas.NIR, sat.ID_TURNO, sat.MIGRADO, sat.ID_CIRCULO, spf.NOMBRE, sas.DESCRIPCION, sap.NOMBRE AS PROCESO, sates.NOMBRE AS NOMBRE_ESTADO FROM SDB_ACC_TURNO sat "
		+ "INNER JOIN SDB_ACC_SOLICITUD sas ON (sas.ID_SOLICITUD=sat.ID_SOLICITUD) "
		+ "INNER JOIN SDB_ACC_PROCESO sap ON(sat.ID_PROCESO = sap.ID_PROCESO) "
		+ "LEFT OUTER JOIN SDB_PGN_ETAPA spa ON (spa.ID_ETAPA=sat.ID_ETAPA_ACTUAL) "
		+ "LEFT OUTER JOIN SDB_PGN_FASES spf ON (spf.ID_FASE=spa.ID_FASE) "
		+ "LEFT OUTER JOIN SDB_ACC_TIPO_ESTADO_SOLICITUD sates ON (sas.ID_ESTADO_SOLICITUD= sates.ID_ESTADO_SOLICITUD) WHERE ";

	/** Constante cs_FIND_BY_JUST_NIR. */
	private static final String cs_FIND_BY_JUST_NIR = "SELECT sas.ID_SOLICITUD, sas.NIR, sat.ID_TURNO, sat.MIGRADO, sat.ID_CIRCULO, spf.NOMBRE, sas.DESCRIPCION, sap.NOMBRE AS PROCESO, sates.NOMBRE AS NOMBRE_ESTADO "
		+ "FROM SDB_ACC_SOLICITUD sas " + "INNER JOIN SDB_ACC_PROCESO sap ON(sas.ID_PROCESO = sap.ID_PROCESO) "
		+ "LEFT OUTER JOIN SDB_ACC_TIPO_ESTADO_SOLICITUD sates ON (sas.ID_ESTADO_SOLICITUD= sates.ID_ESTADO_SOLICITUD) "
		+ "LEFT OUTER JOIN SDB_ACC_TURNO sat ON (sas.ID_SOLICITUD = sat.ID_SOLICITUD) "
		+ "LEFT OUTER JOIN SDB_PGN_ETAPA spa ON (spa.ID_ETAPA=sat.ID_ETAPA_ACTUAL) "
		+ "LEFT OUTER JOIN SDB_PGN_FASES spf ON (spf.ID_FASE=spa.ID_FASE) " + "WHERE sas.ID_SOLICITUD = ? "
		+ "ORDER BY 2 ASC ";

	/** Constante cs_FIND_BY. */
	private static final String cs_FIND_MONITOREO = "SELECT sas.ID_SOLICITUD, sas.NIR, sat.ID_TURNO, spf.NOMBRE, sas.DESCRIPCION, sap.NOMBRE AS PROCESO, sates.NOMBRE AS NOMBRE_ESTADO,SAS.ID_PROCESO,SAS.ID_SUBPROCESO,SAS.VERSION_SUBPROCESO FROM SDB_ACC_TURNO sat "
		+ "INNER JOIN SDB_ACC_SOLICITUD sas ON (sas.ID_SOLICITUD=sat.ID_SOLICITUD) "
		+ "INNER JOIN SDB_ACC_PROCESO sap ON(sat.ID_PROCESO = sap.ID_PROCESO) "
		+ "LEFT OUTER JOIN SDB_PGN_ETAPA spa ON (spa.ID_ETAPA=sat.ID_ETAPA_ACTUAL) "
		+ "LEFT OUTER JOIN SDB_PGN_FASES spf ON (spf.ID_FASE=spa.ID_FASE) "
		+ "LEFT OUTER JOIN SDB_ACC_TIPO_ESTADO_SOLICITUD sates ON (sas.ID_ESTADO_SOLICITUD= sates.ID_ESTADO_SOLICITUD) WHERE ";

	/** Constante cs_FIND_BY_JUST_NIR. */
	private static final String cs_FIND_MONITOREO_JUST_NIR = "SELECT sas.ID_SOLICITUD, sas.NIR, sat.ID_TURNO, spf.NOMBRE, sas.DESCRIPCION, sap.NOMBRE AS PROCESO, sates.NOMBRE AS NOMBRE_ESTADO,SAS.ID_PROCESO,SAS.ID_SUBPROCESO,SAS.VERSION_SUBPROCESO "
		+ "FROM SDB_ACC_SOLICITUD sas " + "INNER JOIN SDB_ACC_PROCESO sap ON(sas.ID_PROCESO = sap.ID_PROCESO) "
		+ "LEFT OUTER JOIN SDB_ACC_TIPO_ESTADO_SOLICITUD sates ON (sas.ID_ESTADO_SOLICITUD= sates.ID_ESTADO_SOLICITUD) "
		+ "LEFT OUTER JOIN SDB_ACC_TURNO sat ON (sas.ID_SOLICITUD = sat.ID_SOLICITUD) "
		+ "LEFT OUTER JOIN SDB_PGN_ETAPA spa ON (spa.ID_ETAPA=sat.ID_ETAPA_ACTUAL) "
		+ "LEFT OUTER JOIN SDB_PGN_FASES spf ON (spf.ID_FASE=spa.ID_FASE) " + "WHERE sas.ID_SOLICITUD = ? "
		+ "ORDER BY 2 ASC ";

	/** Constante cs_FIND_INFO_DOC. */
	private static final String cs_FIND_INFO_DOC = "SELECT sptdp.NOMBRE AS Tipo, "
		+ "sbd.NUMERO AS No_documento, sbd.FECHA_DOCUMENTO AS Fecha_documento, " + "spoo.NOMBRE AS Oficina_Origen, "
		+ "spto.NOMBRE AS Tipo_Oficina, " + "spp.NOMBRE AS Pais, " + "spd.NOMBRE AS Departamento, "
		+ "spm.NOMBRE AS Municipio, " + "pte.NOMBRE AS Tipo_Entidad " + "FROM SDB_BGN_DOCUMENTO sbd "
		+ "INNER JOIN SDB_ACC_SOLICITUD sas "
		+ "ON (sas.ID_DOCUMENTO=sbd.ID_DOCUMENTO AND sas.VERSION_DOCUMENTO = sbd.VERSION_DOCUMENTO) "
		+ "INNER JOIN SDB_ACC_TURNO sat ON (sat.ID_SOLICITUD=sas.ID_SOLICITUD AND sat.ID_TURNO = ?) "
		+ "INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO sptdp ON (sptdp.ID_TIPO_DOCUMENTO=sbd.ID_TIPO_DOCUMENTO) "
		+ "LEFT OUTER JOIN SDB_PGN_OFICINA_ORIGEN spoo ON (spoo.ID_OFICINA_ORIGEN=sbd.ID_OFICINA_ORIGEN) "
		+ "LEFT OUTER JOIN SDB_PGN_TIPO_OFICINA spto ON (spto.ID_TIPO_OFICINA=spoo.ID_TIPO_OFICINA) "
		+ "LEFT OUTER JOIN SDB_PGN_PAIS spp ON (spp.ID_PAIS=spoo.ID_PAIS) "
		+ "LEFT OUTER JOIN SDB_PGN_DEPARTAMENTO spd ON (spd.ID_PAIS=spoo.ID_PAIS AND spd.ID_DEPARTAMENTO=spoo.ID_DEPARTAMENTO) "
		+ "LEFT OUTER JOIN SDB_PGN_MUNICIPIO spm ON (spm.ID_PAIS=spoo.ID_PAIS AND spm.ID_DEPARTAMENTO=spoo.ID_DEPARTAMENTO AND spm.ID_MUNICIPIO=spoo.ID_MUNICIPIO) "
		+ "LEFT OUTER JOIN SDB_PGN_TIPO_ENTIDAD pte ON (pte.ID_TIPO_ENTIDAD = spto.ID_TIPO_ENTIDAD)";

	/** Constante cs_FIND_TRAZABILIDAD. */
	private static final String cs_FIND_TRAZABILIDAD = "SELECT SATH.ID_TURNO, SATH.ID_ETAPA, SPE.NOMBRE, SATH.ESTADO_ACTIVIDAD,SATH.ID_USUARIO,SATH.FECHA_CREACION, SATH.FECHA_REPARTO,SATH.FECHA_INICIAL,SATH.FECHA_FINAL,SATH.MOTIVO, REPLACE(SATH.MOTIVO_NO_TRAMITE,'_',' ') AS MOTIVO_NO_TRAMITE,SATH.OBSERVACIONES, SATH.OBSERVACIONES_NO_TRAMITE, SPMT.DESCRIPCION AS DESCRIPCION_MOTIVO FROM SDB_ACC_TURNO_HISTORIA SATH INNER JOIN SDB_PGN_ETAPA SPE ON (SPE.ID_ETAPA = SATH.ID_ETAPA) INNER JOIN SDB_ACC_TURNO T ON SATH.ID_TURNO = T.ID_TURNO LEFT JOIN SDB_PGN_MOTIVO_TRAMITE SPMT ON SPMT.ID_ETAPA_ORIGEN = SATH.ID_ETAPA AND SPMT.ID_MOTIVO = SATH.ID_MOTIVO AND T.ID_PROCESO = SPMT.ID_PROCESO AND T.ID_SUBPROCESO = SPMT.ID_SUBPROCESO AND SATH.VERSION_SUBPROCESO = SPMT.VERSION_SUBPROCESO AND SATH.MOTIVO = SPMT.DESCRIPCION WHERE SATH.ID_TURNO = ? ORDER BY SATH.ID_TURNO_HISTORIA DESC";

	/** Constante cs_FIND_TURNO_SOLICITUD. */
	private static final String cs_FIND_TURNO_SOLICITUD = "SELECT SAS.NIR, SATH.ID_ETAPA, SPE.NOMBRE, SATH.ESTADO_ACTIVIDAD, SATH.ID_USUARIO,SATH.FECHA_CREACION, SATH.FECHA_REPARTO,SATH.FECHA_INICIAL, SATH.FECHA_FINAL, SATH.MOTIVO, SATH.MOTIVO_NO_TRAMITE, SATH.OBSERVACIONES, SATH.OBSERVACIONES_NO_TRAMITE FROM SDB_ACC_TURNO_HISTORIA SATH INNER JOIN SDB_PGN_ETAPA SPE ON (SPE.ID_ETAPA=SATH.ID_ETAPA) INNER JOIN SDB_ACC_SOLICITUD SAS ON (SAS.ID_SOLICITUD=SATH.ID_SOLICITUD) WHERE SATH.ID_SOLICITUD = ? AND SATH.ID_TURNO IS NULL ORDER BY FECHA_CREACION,ID_ETAPA ASC";

	/** Constante cs_FIND_PROCESO. */
	private static final String cs_FIND_PROCESO = "SELECT sas.ID_SOLICITUD,sas.NIR,spf.NOMBRE,sates.NOMBRE AS NOMBRE_ESTADO,NVL( S1.NIR,' ')NIR_ASOCIADO,CASE WHEN ASA.INDICADOR_VINCULADO = 'S' THEN "
		+ "(SELECT PKG_REGISTRO.FUNC_DESCRIPCION_VINCULADO(ID_TURNO_PADRE) FROM SDB_ACC_TURNO_DERIVADO WHERE INDICADOR_VINCULADO = 'S' AND SDB_ACC_TURNO_DERIVADO.ID_TURNO_PADRE = sat.ID_TURNO) "
		+ "ELSE NVL(sap.NOMBRE,' ') END TRAMITE_ASOCIADO FROM SDB_ACC_TURNO sat INNER JOIN SDB_ACC_SOLICITUD sas ON (sas.ID_SOLICITUD=sat.ID_SOLICITUD) LEFT OUTER JOIN SDB_PGN_ETAPA spe "
		+ "ON (spe.ID_ETAPA=sat.ID_ETAPA_ACTUAL) LEFT OUTER JOIN SDB_PGN_FASES spf ON (spf.ID_FASE=spe.ID_FASE) INNER JOIN SDB_ACC_TIPO_ESTADO_SOLICITUD sates ON (sas.ID_ESTADO_SOLICITUD= "
		+ "sates.ID_ESTADO_SOLICITUD) LEFT JOIN SDB_ACC_SOLICITUD_ASOCIADA ASA ON ASA.ID_SOLICITUD = sas.ID_SOLICITUD LEFT JOIN SDB_ACC_SOLICITUD S1 ON S1.ID_SOLICITUD = ASA.ID_SOLICITUD1 "
		+ "LEFT JOIN SDB_ACC_PROCESO sap ON( sap.ID_PROCESO = S1.ID_PROCESO) WHERE ";

	/** Constante cs_CONSULTA_BANDEJA_TURNOS_CALIFICACION. */
	private static final String cs_CONSULTA_BANDEJA_TURNOS_CALIFICACION = "SELECT P.NOMBRE PROCESO, TH.ID_TURNO, TH.FECHA_REPARTO FECHA_ASIGNACION, S.NIR, TH.MOTIVO_NO_TRAMITE, "
		+ "THANT.OBSERVACIONES OBSERVACIONES, NVL(THANT.ID_ETAPA,0) ETAPA_ANTERIOR, TH.ID_TURNO_HISTORIA, TH.FECHA_CREACION FROM SDB_ACC_TURNO_HISTORIA TH INNER JOIN SDB_ACC_TURNO T "
		+ "ON TH.ID_TURNO = T.ID_TURNO INNER JOIN SDB_ACC_PROCESO P ON TH.ID_PROCESO = P.ID_PROCESO RIGHT JOIN SDB_ACC_SOLICITUD S ON TH.ID_PROCESO = S.ID_PROCESO "
		+ "LEFT JOIN  SDB_ACC_ACTO ASA ON ASA.ID_SOLICITUD = S.ID_SOLICITUD AND ASA.ID_TIPO_ACTO <> '0463'"
		+ "AND TH.ID_SOLICITUD = S.ID_SOLICITUD LEFT JOIN SDB_ACC_TURNO_HISTORIA THANT ON TH.ID_ANTERIOR = THANT.ID_TURNO_HISTORIA WHERE TH.ID_ETAPA = ? AND TH.ID_USUARIO = ? "
		+ "AND TH.ESTADO_ACTIVIDAD = 'ASG' ";

	/** Constante cs_CONSULTA_BANDEJA_TURNOS_CALIFICACION. */
	private static final String cs_CONSULTA_BANDEJA_TURNOS_CALIFICACION_SIN_USUARIO = "SELECT P.NOMBRE PROCESO, TH.ID_TURNO, TH.FECHA_REPARTO FECHA_ASIGNACION, S.NIR, TH.MOTIVO_NO_TRAMITE, "
		+ "THANT.OBSERVACIONES OBSERVACIONES, NVL(THANT.ID_ETAPA,0) ETAPA_ANTERIOR, TH.ID_TURNO_HISTORIA, TH.FECHA_CREACION FROM SDB_ACC_TURNO_HISTORIA TH INNER JOIN SDB_ACC_TURNO T "
		+ "ON TH.ID_TURNO = T.ID_TURNO INNER JOIN SDB_ACC_PROCESO P ON TH.ID_PROCESO = P.ID_PROCESO RIGHT JOIN SDB_ACC_SOLICITUD S ON TH.ID_PROCESO = S.ID_PROCESO "
		+ "AND TH.ID_SOLICITUD = S.ID_SOLICITUD LEFT JOIN SDB_ACC_TURNO_HISTORIA THANT ON TH.ID_ANTERIOR = THANT.ID_TURNO_HISTORIA WHERE TH.ID_ETAPA = ?"
		+ "AND TH.ESTADO_ACTIVIDAD = 'ASG' ";

	/** Constante cs_FIND_DATOS_REASIGNACION. */
	private static final String cs_FIND_DATOS_REASIGNACION = "SELECT THANT.ID_USUARIO AS USUARIO_ANTERIOR, THA.ID_USUARIO_CREACION AS USUARIO_REASIGNACION, THA.ID_USUARIO AS USUARIO_ACTUAL, THA.FECHA_CREACION AS FECHA_REASIGNACION, THANT.OBSERVACIONES, THANT.JUSTIFICACION FROM SDB_ACC_TURNO_HISTORIA THA INNER JOIN SDB_ACC_TURNO_HISTORIA THANT ON THA.ID_ANTERIOR = THANT.ID_TURNO_HISTORIA WHERE THA.ID_TURNO = ? ORDER BY THA.FECHA_CREACION DESC FETCH FIRST 1 ROWS ONLY";

	/**
	 * Instancia un nuevo objeto consulta trazabilidad DAO.
	 */
	public ConsultaTrazabilidadDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de tipo Collection con Trazabilidad
	 *
	 * @param at_argumentos correspondiente al valor del tipo de objeto Trazabilidad
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Trazabilidad
	 */
	public Collection<Trazabilidad> findAll(Trazabilidad at_argumentos)
	    throws B2BException
	{
		Collection<Trazabilidad> lt_trazabilidad;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;
		StringBuilder            lsb_sb;
		Solicitud                ls_s;
		Turno                    lt_t;

		lt_trazabilidad     = new ArrayList<Trazabilidad>();
		lps_ps              = null;
		lrs_rs              = null;
		lsb_sb              = new StringBuilder();
		ls_s                = at_argumentos.getSolicitud();
		lt_t                = at_argumentos.getTurno();

		try
		{
			int li_contador;
			li_contador = 1;

			String ls_it;
			String ls_is;

			ls_it     = lt_t.getIdTurno();
			ls_is     = ls_s.getIdSolicitud();

			if(StringUtils.isValidString(ls_it))
			{
				lsb_sb     = lsb_sb.append(cs_FIND_BY);

				lsb_sb = lsb_sb.append("sat.ID_TURNO = ? ");

				if(StringUtils.isValidString(ls_is))
					lsb_sb = lsb_sb.append("AND sat.ID_SOLICITUD = ? ");
			}
			else
				lsb_sb = lsb_sb.append(cs_FIND_BY_JUST_NIR);

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			if(StringUtils.isValidString(ls_it))
			{
				lps_ps.setString(li_contador++, ls_it);

				if(StringUtils.isValidString(ls_is))
					lps_ps.setString(li_contador++, ls_is);
			}
			else
			{
				if(StringUtils.isValidString(ls_is))
					lps_ps.setString(li_contador++, ls_is);
			}

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lt_trazabilidad.add(getTrazabilidad(lrs_rs, ls_is, true));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lt_trazabilidad))
			lt_trazabilidad = null;

		return lt_trazabilidad;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection con Trazabilidad
	 *
	 * @param at_argumentos correspondiente al valor del tipo de objeto Trazabilidad
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Trazabilidad
	 */
	public Collection<Trazabilidad> findAllMonitoreo(Trazabilidad at_argumentos)
	    throws B2BException
	{
		Collection<Trazabilidad> lt_trazabilidad;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;
		StringBuilder            lsb_sb;
		Solicitud                ls_s;
		Turno                    lt_t;

		lt_trazabilidad     = new ArrayList<Trazabilidad>();
		lps_ps              = null;
		lrs_rs              = null;
		lsb_sb              = new StringBuilder();
		ls_s                = at_argumentos.getSolicitud();
		lt_t                = at_argumentos.getTurno();

		try
		{
			int li_contador;
			li_contador = 1;

			String ls_it;
			String ls_is;

			ls_it     = lt_t.getIdTurno();
			ls_is     = ls_s.getIdSolicitud();

			if(StringUtils.isValidString(ls_it))
			{
				lsb_sb     = lsb_sb.append(cs_FIND_MONITOREO);

				lsb_sb = lsb_sb.append("sat.ID_TURNO = ? ");

				if(StringUtils.isValidString(ls_is))
					lsb_sb = lsb_sb.append("AND sat.ID_SOLICITUD = ? ");
			}
			else
				lsb_sb = lsb_sb.append(cs_FIND_MONITOREO_JUST_NIR);

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			if(StringUtils.isValidString(ls_it))
			{
				lps_ps.setString(li_contador++, ls_it);

				if(StringUtils.isValidString(ls_is))
					lps_ps.setString(li_contador++, ls_is);
			}
			else
			{
				if(StringUtils.isValidString(ls_is))
					lps_ps.setString(li_contador++, ls_is);
			}

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lt_trazabilidad.add(getMonitoreo(lrs_rs, ls_is));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lt_trazabilidad))
			lt_trazabilidad = null;

		return lt_trazabilidad;
	}

	/**
	 * Retorna el valor del objeto de tipo Solicitud consultado por NIR
	 *
	 * @param as_parametros correspondiente al valor del tipo de objeto Solicitud
	 * @return devuelve el valor del objeto solicitud
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Solicitud findByNIR(Solicitud as_parametros)
	    throws B2BException
	{
		Solicitud         ls_s;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_s       = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_NIR);

			lps_ps.setString(li_contador++, as_parametros.getNir());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
			{
				ls_s = new Solicitud();
				ls_s.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByNIR", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_s;
	}

	/**
	 * Consulta todos los registros de la tabla asociados a un id solicitud.
	 *
	 * @param as_idSolicitud Objeto contenedor del id solicitud a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<ConsultaTrazabilidad> findByTurnoSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<ConsultaTrazabilidad> lcct_datos;

		lcct_datos = new ArrayList<ConsultaTrazabilidad>();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_TURNO_SOLICITUD);

				lps_ps.setString(li_contador++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcct_datos.add(getConsultaTrazabilidadSolicitud(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcct_datos.isEmpty())
			lcct_datos = null;

		return lcct_datos;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de ConsultaTrazabilidad consultado por el Turno
	 *
	 * @param at_turno correspondiente al valor del tipo de objeto Turno
	 * @return devuelve el valor del objeto collection de ConsultaTrazabilidad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConsultaTrazabilidad
	 * @see Turno
	 */
	public Collection<ConsultaTrazabilidad> findConsultaTraza(Turno at_turno)
	    throws B2BException
	{
		Collection<ConsultaTrazabilidad> lcct_consultaTraza;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lcct_consultaTraza     = new ArrayList<ConsultaTrazabilidad>();
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_TRAZABILIDAD);
			lps_ps.setString(li_contador, at_turno.getIdTurno());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcct_consultaTraza.add(getConsultaTrazabilidad(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findConsultaTraza", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lcct_consultaTraza))
			lcct_consultaTraza = null;

		return lcct_consultaTraza;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de UsuarioActividadUI para la bandeja de turnos
	 * de calificacion
	 *
	 * @param auaui_usuarioActividad correspondiente al valor del tipo de objeto UsuarioActividadUI
	 * @return devuelve el valor del objeto Collection de UsuarioActividadUI
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioActividadUI
	 */
	public Collection<UsuarioActividadUI> findDatosBandeja(UsuarioActividadUI auaui_usuarioActividad)
	    throws B2BException
	{
		Collection<UsuarioActividadUI> lcuaui_return;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcuaui_return     = new ArrayList<UsuarioActividadUI>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			if(auaui_usuarioActividad != null)
			{
				int           li_count;
				String        ls_idSolicitud;
				StringBuilder lsb_sb;
				BigDecimal    lbd_idEtapa;
				boolean       lb_sinUsuario;

				li_count           = 1;
				ls_idSolicitud     = auaui_usuarioActividad.getIdSolicitud();
				lbd_idEtapa        = auaui_usuarioActividad.getIdEtapa();
				lb_sinUsuario      = lbd_idEtapa.equals(
					    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION)
					);

				if(lb_sinUsuario)
					lsb_sb = new StringBuilder(cs_CONSULTA_BANDEJA_TURNOS_CALIFICACION_SIN_USUARIO);
				else
					lsb_sb = new StringBuilder(cs_CONSULTA_BANDEJA_TURNOS_CALIFICACION);

				if(StringUtils.isValidString(ls_idSolicitud))
					lsb_sb.append("AND TH.ID_SOLICITUD = ? ");

				if(!lb_sinUsuario)
					lsb_sb.append("AND THANT.ID_MOTIVO  = ? AND THANT.ID_ETAPA = ?");

				lsb_sb.append("ORDER BY TH.FECHA_CREACION ASC ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setBigDecimal(li_count++, lbd_idEtapa);

				if(!lb_sinUsuario)
					lps_ps.setString(li_count++, auaui_usuarioActividad.getIdUsuario());

				if(StringUtils.isValidString(ls_idSolicitud))
					lps_ps.setString(li_count++, auaui_usuarioActividad.getIdSolicitud());

				if(!lb_sinUsuario)
				{
					setLong(lps_ps, auaui_usuarioActividad.getIdMotivo(), li_count++);
					lps_ps.setLong(li_count++, auaui_usuarioActividad.getIdEtapaAnt());
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcuaui_return.add(getUsuarioActividad(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDatosBandeja", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(lcuaui_return.isEmpty())
			lcuaui_return = null;

		return lcuaui_return;
	}

	/**
	 * Retorna el valor del objeto de tipo ReasignarTurnos consultados por reasignacion
	 *
	 * @param art_rt correspondiente al valor del tipo de objeto ReasignarTurnos
	 * @return devuelve el valor del objeto ReasignarTurnos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ReasignarTurnos
	 */
	public ReasignarTurnos findDatosReasignacion(ReasignarTurnos art_rt)
	    throws B2BException
	{
		ReasignarTurnos   lrt_temp;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lrt_temp     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_DATOS_REASIGNACION);
			lps_ps.setString(li_contador, art_rt.getIdTurno());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lrt_temp = getDatosReasignacion(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDatosReasignacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		return lrt_temp;
	}

	/**
	 * Retorna el valor del objeto de tipo InformacionDocumento consultado por el ID del turno
	 *
	 * @param at_turno correspondiente al valor del tipo de objeto Turno
	 * @return devuelve el valor del objeto informacion documento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InformacionDocumento
	 */
	public InformacionDocumento findInfoDoc(Turno at_turno)
	    throws B2BException
	{
		InformacionDocumento lid_infoDoc;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lid_infoDoc     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_INFO_DOC);
			lps_ps.setString(li_contador, at_turno.getIdTurno());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lid_infoDoc = getInformacionDocumento(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findInfoDoc", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		return lid_infoDoc;
	}

	/**
	 * Retorna el valor del objeto de tipo Trazabilidad consultado por solicitud y turno
	 *
	 * @param at_argumentos correspondiente al valor del tipo de objeto Trazabilidad
	 * @return devuelve el valor del objeto trazabilidad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Trazabilidad
	 */
	public Trazabilidad findProceso(Trazabilidad at_argumentos)
	    throws B2BException
	{
		Trazabilidad      lt_trazabilidad;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		StringBuilder     lsb_sb;
		Solicitud         ls_s;
		Turno             lt_t;

		lt_trazabilidad     = new Trazabilidad();
		lps_ps              = null;
		lrs_rs              = null;
		lsb_sb              = new StringBuilder();
		ls_s                = at_argumentos.getSolicitud();
		lt_t                = at_argumentos.getTurno();

		try
		{
			int li_contador;
			li_contador     = 1;

			lsb_sb = lsb_sb.append(cs_FIND_PROCESO);

			String ls_it;
			String ls_is;

			ls_it     = lt_t.getIdTurno();
			ls_is     = ls_s.getIdSolicitud();

			if(StringUtils.isValidString(ls_it))
			{
				lsb_sb = lsb_sb.append("sat.ID_TURNO = ? ");

				if(StringUtils.isValidString(ls_is))
					lsb_sb = lsb_sb.append("AND sat.ID_SOLICITUD = ? ");
			}
			else
			{
				if(StringUtils.isValidString(ls_is))
					lsb_sb = lsb_sb.append("sat.ID_SOLICITUD = ? ");
			}

			lsb_sb.append(" ORDER BY 6 ASC ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			if(StringUtils.isValidString(ls_it))
			{
				lps_ps.setString(li_contador++, ls_it);

				if(StringUtils.isValidString(ls_is))
					lps_ps.setString(li_contador++, ls_is);
			}
			else
			{
				if(StringUtils.isValidString(ls_is))
					lps_ps.setString(li_contador++, ls_is);
			}

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lt_trazabilidad = getProceso(lrs_rs, ls_is);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findProceso", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lt_trazabilidad;
	}

	/**
	 * Retorna el valor de consulta trazabilidad.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de consulta trazabilidad
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ConsultaTrazabilidad
	 */
	private ConsultaTrazabilidad getConsultaTrazabilidad(ResultSet ars_rs)
	    throws SQLException
	{
		ConsultaTrazabilidad lct_datos;
		Etapa                le_e;
		TurnoHistoria        lth_th;

		lct_datos     = new ConsultaTrazabilidad();
		le_e          = new Etapa();
		lth_th        = new TurnoHistoria();

		lth_th.setIdTurno(ars_rs.getString("ID_TURNO"));
		lth_th.setIdEtapa(ars_rs.getBigDecimal("ID_ETAPA"));
		le_e.setNombre(ars_rs.getString("NOMBRE"));
		lth_th.setEstadoActividad(ars_rs.getString("ESTADO_ACTIVIDAD"));
		lth_th.setIdUsuario(ars_rs.getString("ID_USUARIO"));
		lth_th.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lth_th.setFechaReparto(ars_rs.getTimestamp("FECHA_REPARTO"));
		lth_th.setFechaInicial(ars_rs.getTimestamp("FECHA_INICIAL"));
		lth_th.setFechaFinal(ars_rs.getTimestamp("FECHA_FINAL"));
		lth_th.setMotivo(ars_rs.getString("MOTIVO"));
		lth_th.setMotivoNoTramite(ars_rs.getString("MOTIVO_NO_TRAMITE"));
		lth_th.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lth_th.setObservacionesNoTramite(ars_rs.getString("OBSERVACIONES_NO_TRAMITE"));
		lth_th.setMotivo(ars_rs.getString("DESCRIPCION_MOTIVO"));

		lct_datos.setEtapa(le_e);
		lct_datos.setTurnoHistoria(lth_th);

		return lct_datos;
	}

	/**
	 * Retorna el valor de consulta trazabilidad.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de consulta trazabilidad
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ConsultaTrazabilidad
	 */
	private ConsultaTrazabilidad getConsultaTrazabilidadSolicitud(ResultSet ars_rs)
	    throws SQLException
	{
		ConsultaTrazabilidad lct_datos;
		Etapa                le_e;
		TurnoHistoria        lth_th;

		lct_datos     = new ConsultaTrazabilidad();
		le_e          = new Etapa();
		lth_th        = new TurnoHistoria();

		lth_th.setNir(ars_rs.getString("NIR"));
		lth_th.setIdEtapa(ars_rs.getBigDecimal("ID_ETAPA"));
		le_e.setNombre(ars_rs.getString("NOMBRE"));
		lth_th.setEstadoActividad(ars_rs.getString("ESTADO_ACTIVIDAD"));
		lth_th.setIdUsuario(ars_rs.getString("ID_USUARIO"));
		lth_th.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lth_th.setFechaReparto(ars_rs.getTimestamp("FECHA_REPARTO"));
		lth_th.setFechaInicial(ars_rs.getTimestamp("FECHA_INICIAL"));
		lth_th.setFechaFinal(ars_rs.getTimestamp("FECHA_FINAL"));
		lth_th.setMotivo(ars_rs.getString("MOTIVO"));
		lth_th.setMotivoNoTramite(ars_rs.getString("MOTIVO_NO_TRAMITE"));
		lth_th.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lth_th.setObservacionesNoTramite(ars_rs.getString("OBSERVACIONES_NO_TRAMITE"));

		lct_datos.setEtapa(le_e);
		lct_datos.setTurnoHistoria(lth_th);

		return lct_datos;
	}

	/**
	 * Retorna el valor de datos reasignacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de datos reasignacion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ReasignarTurnos
	 */
	private ReasignarTurnos getDatosReasignacion(ResultSet ars_rs)
	    throws SQLException
	{
		ReasignarTurnos lrt_rtTemp;

		lrt_rtTemp = new ReasignarTurnos();

		lrt_rtTemp.setUsuarioAnterior(ars_rs.getString("USUARIO_ANTERIOR"));
		lrt_rtTemp.setUsuarioReasignacion(ars_rs.getString("USUARIO_REASIGNACION"));
		lrt_rtTemp.setUsuarioActual(ars_rs.getString("USUARIO_ACTUAL"));
		lrt_rtTemp.setFechaReasignacion(ars_rs.getTimestamp("FECHA_REASIGNACION"));
		lrt_rtTemp.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lrt_rtTemp.setJustificacion(ars_rs.getString("JUSTIFICACION"));

		return lrt_rtTemp;
	}

	/**
	 * Retorna el valor de informacion documento.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de informacion documento
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see InformacionDocumento
	 */
	private InformacionDocumento getInformacionDocumento(ResultSet ars_rs)
	    throws SQLException
	{
		InformacionDocumento lid_datos;

		TipoDocumentoPublico ltdp_tipoDocumentoPublico;
		Documento            ld_documento;
		OficinaOrigen        loo_oficinaOrigen;
		TipoOficina          lto_tipoOficina;
		Pais                 lp_pais;
		Departamento         ld_departamento;
		Municipio            lm_municipio;
		TipoEntidad          lte_tipoEntidad;

		lid_datos     = new InformacionDocumento();

		ltdp_tipoDocumentoPublico     = new TipoDocumentoPublico();
		ld_documento                  = new Documento();
		loo_oficinaOrigen             = new OficinaOrigen();
		lto_tipoOficina               = new TipoOficina();
		lp_pais                       = new Pais();
		ld_departamento               = new Departamento();
		lm_municipio                  = new Municipio();
		lte_tipoEntidad               = new TipoEntidad();

		ltdp_tipoDocumentoPublico.setNombre(ars_rs.getString("TIPO"));
		ld_documento.setNumero((ars_rs.getString("NO_DOCUMENTO")));
		ld_documento.setFechaDocumento(ars_rs.getTimestamp("FECHA_DOCUMENTO"));
		loo_oficinaOrigen.setNombre(ars_rs.getString("OFICINA_ORIGEN"));
		lto_tipoOficina.setNombre(ars_rs.getString("TIPO_OFICINA"));
		lp_pais.setNombre(ars_rs.getString("PAIS"));
		ld_departamento.setNombre(ars_rs.getString("DEPARTAMENTO"));
		lm_municipio.setNombre(ars_rs.getString("MUNICIPIO"));
		lte_tipoEntidad.setNombre(ars_rs.getString("TIPO_ENTIDAD"));

		lid_datos.setTipoDocumentoPublico(ltdp_tipoDocumentoPublico);
		lid_datos.setDocumento(ld_documento);
		lid_datos.setOficinaOrigen(loo_oficinaOrigen);
		lid_datos.setTipoOficina(lto_tipoOficina);
		lid_datos.setPais(lp_pais);
		lid_datos.setDepartamento(ld_departamento);
		lid_datos.setMunicipio(lm_municipio);
		lid_datos.setTipoEntidad(lte_tipoEntidad);

		return lid_datos;
	}

	private Trazabilidad getMonitoreo(ResultSet ars_rs, String ls_is)
	    throws SQLException
	{
		Trazabilidad lt_return;

		lt_return = getTrazabilidad(ars_rs, ls_is, false);

		if(lt_return != null)
		{
			TurnoHistoria lth_historia;

			lth_historia = new TurnoHistoria();

			lth_historia.setIdProceso(ars_rs.getString("ID_PROCESO"));
			lth_historia.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
			lth_historia.setVersionSubproceso(NumericUtils.getLongWrapper(ars_rs.getLong("VERSION_SUBPROCESO")));
			lt_return.setTurnoHistoria(lth_historia);
		}

		return lt_return;
	}

	/**
	 * Retorna el valor de proceso de Trazabilidad.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return el valor de proceso
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Trazabilidad
	 */
	private Trazabilidad getProceso(ResultSet ars_rs, String as_s)
	    throws SQLException
	{
		Trazabilidad lt_datos;
		Solicitud    ls_s;
		Proceso      lp_p;

		lt_datos     = new Trazabilidad();
		ls_s         = new Solicitud();
		lp_p         = new Proceso();

		ls_s.setNir(ars_rs.getString("NIR"));
		ls_s.setNombreEstadoSolicitud(ars_rs.getString("NOMBRE_ESTADO"));
		ls_s.setNirAsociado(ars_rs.getString("NIR_ASOCIADO"));
		ls_s.setTramiteAsociado(ars_rs.getString("TRAMITE_ASOCIADO"));
		lp_p.setNombre(ars_rs.getString("NOMBRE"));

		if(StringUtils.isValidString(as_s))
			ls_s.setIdSolicitud(as_s);
		else
			ls_s.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));

		lt_datos.setSolicitud(ls_s);
		lt_datos.setProceso(lp_p);

		return lt_datos;
	}

	/**
	 * Retorna el valor de trazabilidad.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @param ab_migrado correspondiente al valor del tipo de objeto boolean
	 * @return el valor de trazabilidad
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Trazabilidad
	 */
	private Trazabilidad getTrazabilidad(ResultSet ars_rs, String as_s, boolean ab_migrado)
	    throws SQLException
	{
		Trazabilidad lt_datos;
		Solicitud    ls_s;
		Turno        lt_t;
		Fases        lf_f;
		Proceso      lp_p;

		lt_datos     = new Trazabilidad();
		ls_s         = new Solicitud();
		lt_t         = new Turno();
		lf_f         = new Fases();
		lp_p         = new Proceso();

		ls_s.setNir(ars_rs.getString("NIR"));
		ls_s.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ls_s.setNombreEstadoSolicitud(ars_rs.getString("NOMBRE_ESTADO"));
		lt_t.setIdTurno(ars_rs.getString("ID_TURNO"));
		lf_f.setNombre(ars_rs.getString("NOMBRE"));
		lp_p.setNombre(ars_rs.getString("PROCESO"));

		if(ab_migrado)
		{
			lt_t.setMigrado(ars_rs.getString("MIGRADO"));
			lt_t.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		}

		if(StringUtils.isValidString(as_s))
			ls_s.setIdSolicitud(as_s);
		else
			ls_s.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));

		lt_datos.setSolicitud(ls_s);
		lt_datos.setTurno(lt_t);
		lt_datos.setFases(lf_f);
		lt_datos.setProceso(lp_p);

		return lt_datos;
	}

	/**
	 * Retorna el valor de usuario actividad.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de usuario actividad
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see UsuarioActividadUI
	 */
	private UsuarioActividadUI getUsuarioActividad(ResultSet ars_rs)
	    throws SQLException
	{
		UsuarioActividadUI luaui_usuarioActividad;

		luaui_usuarioActividad = new UsuarioActividadUI();

		luaui_usuarioActividad.setProceso(ars_rs.getString("PROCESO"));
		luaui_usuarioActividad.setIdTurno(ars_rs.getString("ID_TURNO"));
		luaui_usuarioActividad.setFechaReparto(ars_rs.getTimestamp("FECHA_ASIGNACION"));
		luaui_usuarioActividad.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		luaui_usuarioActividad.setNir(ars_rs.getString("NIR"));
		luaui_usuarioActividad.setMotivoNoTramite(ars_rs.getString("MOTIVO_NO_TRAMITE"));
		luaui_usuarioActividad.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		luaui_usuarioActividad.setIdEtapa(getBigDecimal(ars_rs, "ETAPA_ANTERIOR"));
		luaui_usuarioActividad.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));

		return luaui_usuarioActividad;
	}
}
