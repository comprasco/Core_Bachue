package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Clase que contiene todos las consultasd de la tabla SDB_ACC_SOLICITUD_MATRICULA.
 *
 * @author hcastaneda
 */
public class SolicitudMatriculaDAO extends BaseDAO
{
	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_MATRICULA SET EXPEDIR_CERTIFICADO=?, "
		+ "CANTIDAD_CERTIFICADOS=?, ID_TURNO_CERTIFICADO=?, ESTADO=?, CEMENTERIO=?, FECHA_MODIFICACION=SYSTIMESTAMP, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?,MATRICULAS_APERTURAR = ?, ID_DATOS_ANT_SISTEMA = ?, CEDULA_CATASTRAL = ?, IND_MAYOR_VALOR = ?, INDICADOR_ENGLOBE_CORRECCIONES = ? "
		+ "WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_MATRICULA(ID_SOLICITUD, ID_CIRCULO, "
		+ "ID_MATRICULA, EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS, ID_TURNO_CERTIFICADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ESTADO, CEMENTERIO,MATRICULAS_APERTURAR,ID_DATOS_ANT_SISTEMA, CEDULA_CATASTRAL, IND_MAYOR_VALOR, INDICADOR_ENGLOBE_CORRECCIONES) "
		+ "VALUES(?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?)";

	/** Constante cs_DELETE_BY_SOLICITUD. */
	private static final String cs_DELETE_BY_SOLICITUD = "DELETE FROM SDB_ACC_SOLICITUD_MATRICULA WHERE ID_SOLICITUD = ? ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, "
		+ "EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS, ID_TURNO_CERTIFICADO, CEMENTERIO, ID_USUARIO_CREACION, FECHA_CREACION "
		+ "FROM SDB_ACC_SOLICITUD_MATRICULA WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=?";

	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = " SELECT  DISTINCT PCR.ID_CIRCULO || '-' || PCR.NOMBRE CIRCULO_REGISTRAL FROM "
		+ " SDB_ACC_SOLICITUD_MATRICULA ASM INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PCR ON PCR.ID_CIRCULO = ASM.ID_CIRCULO WHERE ASM.ID_SOLICITUD=? ";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, "
		+ "EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS,ID_TURNO_CERTIFICADO, CEMENTERIO, ID_USUARIO_CREACION, FECHA_CREACION "
		+ "FROM SDB_ACC_SOLICITUD_MATRICULA WHERE ID_CIRCULO=? AND ID_MATRICULA=?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, "
		+ "EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS, ID_TURNO_CERTIFICADO, CEMENTERIO, ESTADO, ID_USUARIO_CREACION, FECHA_CREACION "
		+ "FROM SDB_ACC_SOLICITUD_MATRICULA WHERE ID_SOLICITUD=? AND NVL(ESTADO,'A') <> 'I'";

	/** Constante cs_FIND_ALL_BY_ID_SOLICITUD_WITH_NOMBRE_CIRCULO_AND_DIRECCION. */
	private static final String cs_FIND_ALL_BY_ID_SOLICITUD_WITH_NOMBRE_CIRCULO_AND_DIRECCION = "SELECT PCR.ID_CIRCULO || ' - ' || PCR.NOMBRE AS NOMBRE_CIRCULO, NVL(DP.DIRECCION_COMPLETA,'SIN DIRECCION') AS DIRECCION_COMPLETA, SM.* FROM SDB_ACC_SOLICITUD_MATRICULA SM INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PCR ON SM.ID_CIRCULO = PCR.ID_CIRCULO LEFT OUTER JOIN SDB_BNG_DIRECCION_PREDIO DP ON SM.ID_CIRCULO = DP.ID_CIRCULO AND SM.ID_MATRICULA = DP.ID_MATRICULA WHERE SM.ID_SOLICITUD = ? AND NVL(SM.ESTADO,'A') <> 'I'";

	/** Constante cs_FIND_ENGLOBE_BY_ID_SOLICITUD_CIRCULO. */
	private static final String cs_FIND_ENGLOBE_BY_ID_SOLICITUD_CIRCULO = "SELECT * FROM SDB_ACC_SOLICITUD_MATRICULA WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? AND INDICADOR_ENGLOBE_CORRECCIONES = 'S' AND ESTADO = 'A'";

	/** Constante cs_FIND_BY_ID_SOLICITUD_ORDER_BY_CIRCULO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ORDER_BY_CIRCULO = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS, ID_TURNO_CERTIFICADO, CEMENTERIO, ID_USUARIO_CREACION, FECHA_CREACION FROM SDB_ACC_SOLICITUD_MATRICULA WHERE ID_SOLICITUD=? AND NVL(ESTADO,'A') <> 'I' ";

	/** Constante cs_FIND_BY_ID_SOLICITUD_CIRCULO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_CIRCULO = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA,ID_DATOS_ANT_SISTEMA, "
		+ "EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS, ID_TURNO_CERTIFICADO, CEMENTERIO, ID_USUARIO_CREACION, FECHA_CREACION "
		+ "FROM SDB_ACC_SOLICITUD_MATRICULA WHERE ID_SOLICITUD=? AND ID_CIRCULO = ? AND NVL(ESTADO,'A') <> 'I'";

	/** Constante cs_FIND_BY_ID_SOLICITUD_CIRCULO_ENTREGA. */
	private static final String cs_FIND_BY_ID_SOLICITUD_CIRCULO_ENTREGA = "SELECT SM.*,NVL(DP.DIRECCION_COMPLETA,'SIN DIRECCION') AS DIRECCION_COMPLETA FROM SDB_ACC_SOLICITUD_MATRICULA SM LEFT OUTER JOIN SDB_BNG_DIRECCION_PREDIO DP ON SM.ID_CIRCULO = DP.ID_CIRCULO AND SM.ID_MATRICULA = DP.ID_MATRICULA WHERE SM.ID_SOLICITUD=? AND SM.ID_CIRCULO = ? AND NVL(SM.ESTADO,'A') <> 'I'";

	/** Constante cs_FIND_BY_TURNO_CERTIFICADO. */
	private static final String cs_FIND_BY_TURNO_CERTIFICADO = "SELECT SM.*,NVL(DP.DIRECCION_COMPLETA,'SIN DIRECCION') AS DIRECCION_COMPLETA FROM SDB_ACC_SOLICITUD_MATRICULA SM LEFT OUTER JOIN SDB_BNG_DIRECCION_PREDIO DP ON SM.ID_CIRCULO = DP.ID_CIRCULO AND SM.ID_MATRICULA = DP.ID_MATRICULA WHERE SM.ID_TURNO_CERTIFICADO = ? ";

	/** Constante cs_FIND_MATRICULAS_BY_TURNO. */
	private static final String cs_FIND_MATRICULAS_BY_TURNO = " SELECT SM.ID_CIRCULO || '-' || SM.ID_MATRICULA MATRICULA  , UPPER (NVL(EP.NOMBRE,'ACTIVO')) ESTADO_PREDIO"
		+ " FROM SDB_ACC_SOLICITUD_MATRICULA SM  INNER JOIN SDB_BNG_PREDIO_REGISTRO PR ON PR.ID_CIRCULO = SM.ID_CIRCULO AND PR.ID_MATRICULA = SM.ID_MATRICULA"
		+ " LEFT JOIN SDB_PNG_ESTADO_PREDIO EP ON EP.ID_ESTADO_PREDIO = PR.ID_ESTADO_PREDIO  INNER JOIN SDB_ACC_SOLICITUD S ON SM.ID_SOLICITUD = S.ID_SOLICITUD WHERE NVL(SM.ESTADO,'A') != 'I'"
		+ " AND SM.ID_SOLICITUD = (SELECT ID_SOLICITUD FROM SDB_ACC_TURNO T WHERE T.ID_TURNO = ? AND T.ID_CIRCULO = SM.ID_CIRCULO)";

	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_TURNO = " SELECT SM.ID_SOLICITUD, SM.ID_CIRCULO, SM.ID_MATRICULA, SM.EXPEDIR_CERTIFICADO, SM.CANTIDAD_CERTIFICADOS, SM.ID_TURNO_CERTIFICADO, "
		+ "SM.CEMENTERIO, SM.ID_USUARIO_CREACION, SM.FECHA_CREACION, UPPER (NVL(EP.NOMBRE,'ACTIVO')) ESTADO "
		+ " FROM SDB_ACC_SOLICITUD_MATRICULA SM  INNER JOIN SDB_BNG_PREDIO_REGISTRO PR ON PR.ID_CIRCULO = SM.ID_CIRCULO AND PR.ID_MATRICULA = SM.ID_MATRICULA"
		+ " LEFT JOIN SDB_PNG_ESTADO_PREDIO EP ON EP.ID_ESTADO_PREDIO = PR.ID_ESTADO_PREDIO  INNER JOIN SDB_ACC_SOLICITUD S ON SM.ID_SOLICITUD = S.ID_SOLICITUD WHERE NVL(SM.ESTADO,'A') != 'I'"
		+ " AND SM.ID_SOLICITUD = (SELECT ID_SOLICITUD FROM SDB_ACC_TURNO T WHERE T.ID_TURNO = ? AND T.ID_CIRCULO = SM.ID_CIRCULO)";

	/** Constante cs_FIND_ALERT_BY_SOLICITUD_MATRICULA. */
	private static final String cs_FIND_ALERT_BY_SOLICITUD_MATRICULA = "SELECT CASE WHEN NVL(MAX(PANJ.ID_NATURALEZA_JURIDICA),0) != 0 AND NVL(MAX(PANJ.VERSION),0) != 0 THEN 'SI' ELSE 'NO' END AS ALERTA "
		+ "FROM SDB_ACC_SOLICITUD_MATRICULA SM LEFT JOIN SDB_BNG_ANOTACION_PREDIO AP ON AP.ID_CIRCULO = SM.ID_CIRCULO AND AP.ID_MATRICULA = SM.ID_MATRICULA "
		+ "LEFT OUTER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = AP.ID_NATURALEZA_JURIDICA AND  PNJ.VERSION = AP.VERSION "
		+ "LEFT OUTER JOIN SDB_PGN_ALERTA_NATURALEZA_JURIDICA PANJ ON PANJ.ID_NATURALEZA_JURIDICA = PNJ.ID_NATURALEZA_JURIDICA AND PANJ.VERSION = PNJ.VERSION "
		+ "WHERE ID_SOLICITUD = ? AND SM.ID_CIRCULO = ? AND SM.ID_MATRICULA = ?";

	/** Constante cs_FIND_MATRICULAS_BY_TURNO_ANT_SISTEMA. */
	private static final String cs_FIND_MATRICULAS_BY_TURNO_ANT_SISTEMA = "SELECT SM.* FROM SDB_ACC_SOLICITUD_MATRICULA SM INNER JOIN SDB_ACC_SOLICITUD S ON SM.ID_SOLICITUD = S.ID_SOLICITUD "
		+ "WHERE SM.ID_SOLICITUD = (SELECT ID_SOLICITUD FROM SDB_ACC_TURNO T WHERE T.ID_TURNO = ? AND T.ID_CIRCULO = SM.ID_CIRCULO) AND SM.ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_CONSULTA_MATRICULAS_ASOCIADAS. */
	private static final String cs_CONSULTA_MATRICULAS_ASOCIADAS = " SELECT ASM.ID_SOLICITUD, ASM.ID_CIRCULO, ASM.ID_MATRICULA, ASM.EXPEDIR_CERTIFICADO, ASM.CANTIDAD_CERTIFICADOS, ASM.ID_TURNO_CERTIFICADO,"
		+ " BDP.DIRECCION_COMPLETA DIRECCION_COMPLETA FROM SDB_ACC_SOLICITUD_MATRICULA ASM LEFT OUTER JOIN SDB_BNG_DIRECCION_PREDIO BDP ON BDP.ID_CIRCULO = ASM.ID_CIRCULO AND BDP.ID_MATRICULA = ASM.ID_MATRICULA"
		+ " WHERE ASM.ID_SOLICITUD=? AND NVL(ASM.ESTADO,'A') <> 'I' AND ROWNUM = 1 ORDER BY BDP.FECHA_CREACION DESC ";

	/** Constante cs_FIND_BY_ID_SOLICITUD_CIRCULO_TURNO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_CIRCULO_TURNO = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS, ID_TURNO_CERTIFICADO, CEMENTERIO, ID_USUARIO_CREACION, FECHA_CREACION "
		+ "FROM SDB_ACC_SOLICITUD_MATRICULA WHERE SDB_ACC_SOLICITUD_MATRICULA.ID_SOLICITUD=?"
		+ " AND EXISTS (SELECT 1 FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO "
		+ "              WHERE SDB_ACC_SOLICITUD_MATRICULA_ACTO.ID_TURNO = ?"
		+ "              AND SDB_ACC_SOLICITUD_MATRICULA.ID_SOLICITUD = SDB_ACC_SOLICITUD_MATRICULA_ACTO.ID_SOLICITUD"
		+ "              AND SDB_ACC_SOLICITUD_MATRICULA.ID_CIRCULO = SDB_ACC_SOLICITUD_MATRICULA_ACTO.ID_CIRCULO"
		+ "              AND SDB_ACC_SOLICITUD_MATRICULA.ID_MATRICULA = SDB_ACC_SOLICITUD_MATRICULA_ACTO.ID_MATRICULA"
		+ "              ) AND SDB_ACC_SOLICITUD_MATRICULA.ID_CIRCULO = ? AND NVL(ESTADO,'A') <> 'I'";

	/** Constante cs_FIND_MATRICULAS_FILTER_BY_VALIDACION_MEDIDA_CAUTELAR. */
	private static final String cs_FIND_MATRICULAS_FILTER_BY_VALIDACION_MEDIDA_CAUTELAR = "SELECT * FROM SDB_ACC_SOLICITUD_MATRICULA SM INNER JOIN SDB_ACC_SOLICITUD_MATRICULA_ACTO SMA ON SMA.ID_SOLICITUD = SM.ID_SOLICITUD AND SMA.ID_CIRCULO = SM.ID_CIRCULO AND SMA.ID_MATRICULA = SM.ID_MATRICULA INNER JOIN SDB_ACC_ACTO AA ON AA.ID_ACTO = SMA.ID_ACTO INNER JOIN SDB_PNG_NATURALEZA_JURIDICA NJ ON NJ.ID_NATURALEZA_JURIDICA = AA.ID_TIPO_ACTO AND NJ.VERSION = (SELECT MAX(VERSION) FROM SDB_PNG_NATURALEZA_JURIDICA PNJ WHERE PNJ.ID_NATURALEZA_JURIDICA = NJ.ID_NATURALEZA_JURIDICA) INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA GNJ ON GNJ.ID_GRUPO_NAT_JURIDICA = NJ.ID_GRUPO_NAT_JURIDICA AND GNJ.ID_GRUPO_NAT_JURIDICA = '0400' INNER JOIN SDB_ACC_TURNO_HISTORIA TH ON TH.ID_SOLICITUD = SM.ID_SOLICITUD AND TH.ESTADO_ACTIVIDAD = 'ESP' AND TH.ID_ETAPA = '20' AND TH.ID_TURNO_HISTORIA = (SELECT MAX(ID_TURNO_HISTORIA) FROM SDB_ACC_TURNO_HISTORIA ATH WHERE ATH.ID_SOLICITUD = TH.ID_SOLICITUD) INNER JOIN SDB_ACC_SOLICITUD SOL ON SM.ID_SOLICITUD = SOL.ID_SOLICITUD AND SOL.ID_SOLICITUD <> ? WHERE SM.ID_CIRCULO = ? AND SM.ID_MATRICULA = ?";

	/**
	 * Retorna el valor del objeto de SolicitudMatricula.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto Solicitud
	 * @return devuelve el valor de SolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public SolicitudMatricula consultaMatriculasAsociadas(Solicitud as_param)
	    throws B2BException
	{
		SolicitudMatricula ls_object;
		ls_object = null;

		if(as_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_CONSULTA_MATRICULAS_ASOCIADAS);

				lps_ps.setString(1, as_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaMatriculasAsociadas", lse_e);

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
	 * Borra solicitud.
	 *
	 * @param asm_solicitudMatricula correspondiente al valor del tipo de objeto SolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteBySolicitud(SolicitudMatricula asm_solicitudMatricula)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		li_column     = 1;

		lps_ps = null;

		if(asm_solicitudMatricula != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_SOLICITUD);

				lps_ps.setString(li_column++, asm_solicitudMatricula.getIdSolicitud());

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Borra solicitud circulo matricula.
	 *
	 * @param asm_solicitudMatricula correspondiente al valor del tipo de objeto SolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteBySolicitudCirculoMatricula(SolicitudMatricula asm_solicitudMatricula)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		li_column     = 1;

		lps_ps = null;

		if(asm_solicitudMatricula != null)
		{
			try
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder(cs_DELETE_BY_SOLICITUD);

				lsb_sb.append(" AND ID_CIRCULO = ? AND ID_MATRICULA = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, asm_solicitudMatricula.getIdSolicitud());
				lps_ps.setString(li_column++, asm_solicitudMatricula.getIdCirculo());
				lps_ps.setLong(li_column++, asm_solicitudMatricula.getIdMatricula());

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteBySolicitudCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Se identifica si un registro que coincida con el id solicitud, el id círculo y el id matrícula especificados tiene alertas.
	 *
	 * @param asm_param Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return Registro solicitud matricula resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public SolicitudMatricula findAlertByIdMatricula(SolicitudMatricula asm_param)
	    throws B2BException
	{
		if(asm_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALERT_BY_SOLICITUD_MATRICULA);

				lps_ps.setString(1, asm_param.getIdSolicitud());
				lps_ps.setString(2, asm_param.getIdCirculo());
				lps_ps.setLong(3, asm_param.getIdMatricula());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					asm_param.setAlerta(BooleanUtils.getBooleanValue(lrs_rs.getString("ALERTA")));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAlertByIdMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return asm_param;
	}

	/**
	 * Find all by id solicitud with circulo and direccion.
	 *
	 * @param as_param de as param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findAllByIdSolicitudWithCirculoAndDireccion(String as_param)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;

		lcsm_datos = new ArrayList<SolicitudMatricula>();

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(
					    cs_FIND_ALL_BY_ID_SOLICITUD_WITH_NOMBRE_CIRCULO_AND_DIRECCION
					);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsm_datos.add(getObjectFromResultSetWithNombreCirculoAndDireccion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdSolicitudWithCirculoAndDireccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Retorna el valor del objeto de Map.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudMatricula
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	public Map<String, SolicitudMatricula> findAllByTurnoCertificado(SolicitudMatricula at_param)
	    throws B2BException
	{
		Map<String, SolicitudMatricula> lmssm_return;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lmssm_return     = new LinkedHashMap<String, SolicitudMatricula>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_CERTIFICADO);

			lps_ps.setString(li_contador++, at_param.getIdTurnoCertificado());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				SolicitudMatricula lsm_solicitudMatricula;
				String             ls_matricula;

				lsm_solicitudMatricula     = getObjetFromResultSet(lrs_rs, true);
				ls_matricula               = null;

				if(lsm_solicitudMatricula != null)
				{
					ls_matricula = lsm_solicitudMatricula.getIdCirculo() + "-"
						+ lsm_solicitudMatricula.getIdMatricula();

					lmssm_return.put(ls_matricula, lsm_solicitudMatricula);
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByTurnoCertificado", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lmssm_return;
	}

	/**
	 * Find by circulo matricula.
	 *
	 * @param at_param de at param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findByCirculoMatricula(SolicitudMatricula at_param)
	    throws B2BException
	{
		if(at_param != null)
			return findByCirculoMatricula(at_param.getIdCirculo(), at_param.getIdMatricula());
		else

			return null;
	}

	/**
	 * Find by circulo matricula.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findByCirculoMatricula(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;

		lcsm_datos = new ArrayList<SolicitudMatricula>();

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_count++, as_idCirculo);
				lps_ps.setLong(li_count++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsm_datos.add(getObjetFromResultSet(lrs_rs, false));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Obtiene un registro que coincida con el id solicitud, el id círculo y el id matrícula especificados.
	 *
	 * @param asm_param Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return Registro solicitud matricula resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public SolicitudMatricula findById(SolicitudMatricula asm_param)
	    throws B2BException
	{
		return findById(asm_param, false);
	}

	/**
	 * Obtiene un registro que coincida con el id solicitud, el id círculo y el id matrícula especificados.
	 *
	 * @param asm_param Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param ab_activos si es true indica que solo se deben consultar los registros con estado activo
	 * @return Registro solicitud matricula resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public SolicitudMatricula findById(SolicitudMatricula asm_param, boolean ab_activos)
	    throws B2BException
	{
		return (asm_param != null)
		? findById(asm_param.getIdSolicitud(), asm_param.getIdCirculo(), asm_param.getIdMatricula(), ab_activos) : null;
	}

	/**
	 * Obtiene un registro que coincida con el id solicitud, el id círculo y el id matrícula especificados.
	 *
	 * @param as_idSolicitud Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param as_idCirculo Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param al_idMatricula Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param ab_activos si es true indica que solo se deben consultar los registros con estado activo
	 * @return Registro solicitud matricula resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public SolicitudMatricula findById(
	    String as_idSolicitud, String as_idCirculo, long al_idMatricula, boolean ab_activos
	)
	    throws B2BException
	{
		SolicitudMatricula lsm_object;

		lsm_object = null;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder();

				lsb_query.append(cs_FIND_BY_ID);

				if(ab_activos)
					lsb_query.append(" AND NVL(ESTADO,'A') <> 'I' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_idSolicitud);
				lps_ps.setString(2, as_idCirculo);
				lps_ps.setLong(3, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsm_object = getObjetFromResultSet(lrs_rs, false);
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

		return lsm_object;
	}

	/**
	 * Metodo encargado de consultar todos los registros de solicitud matricula para una solicitud.
	 *
	 * @param at_param Objeto de tipo SolicitudMatricula que contiene los argumentos para consultar.
	 * @return devuelve el valor de Collection de SolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findByIdSolicitud(SolicitudMatricula at_param)
	    throws B2BException
	{
		return findByIdSolicitud(at_param, false);
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudMatricula.
	 *
	 * @param asm_param correspondiente al valor del tipo de objeto SolicitudMatricula
	 * @param ab_estado correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de SolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findByIdSolicitud(SolicitudMatricula asm_param, boolean ab_estado)
	    throws B2BException
	{
		return (asm_param != null) ? findByIdSolicitud(asm_param.getIdSolicitud(), ab_estado) : null;
	}

	/**
	 * Metodo encargado de consultar todos los registros de solicitud matricula para una solicitud.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @param ab_estado bandera para agregar el estado
	 * @return devuelve el valor de Collection de SolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findByIdSolicitud(String as_param, boolean ab_estado)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;

		lcsm_datos = new ArrayList<SolicitudMatricula>();

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsm_datos.add(getObjetFromResultSet(lrs_rs, false, ab_estado));
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

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Busca todos los registros asociados a un id solicitud que pertenezcan a un círculo específico.
	 *
	 * @param asm_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección de solicitud matrícula resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findByIdSolicitudCirculo(SolicitudMatricula asm_param)
	    throws B2BException
	{
		return (asm_param != null) ? findByIdSolicitudCirculo(asm_param.getIdSolicitud(), asm_param.getIdCirculo()) : null;
	}

	/**
	 * Busca todos los registros asociados a un id solicitud que pertenezcan a un círculo específico.
	 *
	 * @param as_idSolicitud Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param as_idCirculo Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección de solicitud matrícula resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findByIdSolicitudCirculo(String as_idSolicitud, String as_idCirculo)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;

		lcsm_datos = new ArrayList<SolicitudMatricula>();

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_CIRCULO);

				lps_ps.setString(li_cont++, as_idSolicitud);
				lps_ps.setString(li_cont++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsm_datos.add(obtenerSolicitudMatricula(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Busca todos los registros asociados a un id solicitud que pertenezcan a un círculo específico.
	 *
	 * @param as_idSolicitud Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param as_idCirculo Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección de solicitud matrícula resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findByIdSolicitudCirculoEntrega(String as_idSolicitud, String as_idCirculo)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;

		lcsm_datos = new ArrayList<SolicitudMatricula>();

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_CIRCULO_ENTREGA);

				lps_ps.setString(li_cont++, as_idSolicitud);
				lps_ps.setString(li_cont++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsm_datos.add(getObjetFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudCirculoEntrega", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Metodo encargado de consultar en la tabla SDB_ACC_SOLICITUD_MATRICULA y
	 * SDB_ACC_SOLICITUD_MATRICULA_ACTO por ID_SOLICITUD, ID_TURNO y ID_CIRCULO.
	 *
	 * @param asm_parametros Argumento de tipo SolicitudMatricula que contiene los criterios necesarios
	 * para realizar la consulta.
	 * @return Collección de SolicitudMatricula que contiene los datos que cumplen con los criterios de
	 * búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findByIdSolicitudCirculoTurno(SolicitudMatricula asm_parametros)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcsm_datos     = new ArrayList<SolicitudMatricula>();
		lps_ps         = null;
		lrs_rs         = null;

		if(asm_parametros != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_CIRCULO_TURNO);

				lps_ps.setString(li_contador++, asm_parametros.getIdSolicitud());

				{
					SolicitudMatriculaActo lsma_solicitudMatriculaActo;

					lsma_solicitudMatriculaActo = asm_parametros.getSolicitudMatriculaActo();

					if(lsma_solicitudMatriculaActo != null)
						lps_ps.setString(li_contador++, lsma_solicitudMatriculaActo.getIdTurno());
				}

				lps_ps.setString(li_contador++, asm_parametros.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsm_datos.add(getObjetFromResultSet(lrs_rs, false));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudCirculoTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudMatricula filtrado por id solicitud.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudMatricula
	 * @param ab_circulo de ab circulo
	 * @return devuelve el valor de Collection de SolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findByIdSolicitudOrderCirculo(
	    SolicitudMatricula at_param, boolean ab_circulo
	)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcsm_datos     = new ArrayList<SolicitudMatricula>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int           li_column;
			StringBuilder lsb_sb;

			li_column     = 1;
			lsb_sb        = new StringBuilder();

			lsb_sb.append(cs_FIND_BY_ID_SOLICITUD_ORDER_BY_CIRCULO);

			if(ab_circulo)
				lsb_sb.append(" AND ID_CIRCULO = ? ");
			else
				lsb_sb.append(" ORDER BY ID_CIRCULO DESC ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_column++, at_param.getIdSolicitud());

			if(ab_circulo)
				lps_ps.setString(li_column++, at_param.getIdCirculo());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsm_datos.add(getObjetFromResultSet(lrs_rs, false));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdSolicitudOrderCirculo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudMatricula.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudMatricula
	 * @return devuelve el valor de Collection de SolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findBySolicitud(SolicitudMatricula at_param)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_sm;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;
		int                            li_contador;
		li_contador     = 1;

		lps_ps      = null;
		lrs_rs      = null;
		lcsm_sm     = new ArrayList<SolicitudMatricula>();

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD);

			lps_ps.setString(li_contador++, at_param.getIdSolicitud());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsm_sm.add(infoCirculoRegistral(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findBySolicitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcsm_sm;
	}

	/**
	 * Consulta un registro por turno certificado.
	 *
	 * @param asm_param Objeto contenedor del turno a consultar
	 * @return Resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public SolicitudMatricula findByTurnoCertificado(SolicitudMatricula asm_param)
	    throws B2BException
	{
		return findByTurnoCertificado(asm_param, true);
	}

	/**
	 * Consulta un registro por turno certificado.
	 *
	 * @param asm_param Objeto contenedor del turno a consultar
	 * @param ab_expedirCertificado indica si debe filtrar por el campo expedir certificado
	 * @return Resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public SolicitudMatricula findByTurnoCertificado(SolicitudMatricula asm_param, boolean ab_expedirCertificado)
	    throws B2BException
	{
		SolicitudMatricula ls_object;
		ls_object = null;

		if(asm_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_sb;

				li_contador     = 1;
				lsb_sb          = new StringBuilder();

				lsb_sb.append(cs_FIND_BY_TURNO_CERTIFICADO);

				if(ab_expedirCertificado)
					lsb_sb.append(" AND SM.EXPEDIR_CERTIFICADO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, asm_param.getIdTurnoCertificado());

				if(ab_expedirCertificado)
					lps_ps.setString(li_contador++, asm_param.getExpedirCertificado());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoCertificado", lse_e);

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
	 * Método encargado de consultar las matrículas para englobe de correcciones con base al circulo y la solicitud.
	 *
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param as_idSolicitud Variable que contiene el id de la solicitud.
	 * @return Colección que contiene las matrículas para englobe de correcciones.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<SolicitudMatricula> findEnglobesBySolicitudCirculo(String as_idCirculo, String as_idSolicitud)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_return;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcsm_return     = new ArrayList<SolicitudMatricula>();
		lps_ps          = null;
		lrs_rs          = null;

		if(StringUtils.isValidString(as_idCirculo) && StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_ENGLOBE_BY_ID_SOLICITUD_CIRCULO);

				lps_ps.setString(li_count++, as_idSolicitud);
				lps_ps.setString(li_count++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsm_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findEnglobesBySolicitudCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsm_return.isEmpty())
			lcsm_return = null;

		return lcsm_return;
	}

	/**
	 * Método encargado de consultar matrículas con base a un id turno.
	 *
	 * @param as_idTurno Variable de tipo String la cual contiene el id del turno en tramite
	 * @return Colección que contiene la información consultada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findMatriculasByturno(String as_idTurno)
	    throws B2BException
	{
		return findMatriculasByturno(as_idTurno, true);
	}

	/**
	 * Método encargado de consultar matrículas con base a un id turno.
	 *
	 * @param as_idTurno Variable de tipo String la cual contiene el id del turno en tramite
	 * @param ab_b Variable de tipo boolean la cual define la consulta y el resulset
	 * @return Colección que contiene la información consultada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findMatriculasByturno(String as_idTurno, boolean ab_b)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcsm_datos     = new ArrayList<SolicitudMatricula>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int           li_count;
			StringBuilder lsb_sb;

			lsb_sb       = new StringBuilder(ab_b ? cs_FIND_MATRICULAS_BY_TURNO : cs_FIND_BY_TURNO);
			li_count     = 1;

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_count++, as_idTurno);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsm_datos.add(ab_b ? getObjetResultSet(lrs_rs) : getObjetFromResultSet(lrs_rs, false, true));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMatriculasByturno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Método encargado de consultar matrículas con base a un id turno e id datos ant sistema.
	 *
	 * @param as_idTurno Variable de tipo String la cual contiene el id del turno en tramite
	 * @param as_idDatosAntSistema Vairiable de tipo String que contiene el id de los datos de antiguo sistema
	 * @return Colección que contiene la información consultada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findMatriculasByturnoAntSistema(
	    String as_idTurno, String as_idDatosAntSistema
	)
	    throws B2BException
	{
		return findMatriculasByturnoAntSistema(as_idTurno, as_idDatosAntSistema, false);
	}

	/**
	 * Método encargado de consultar matrículas con base a un id turno e id datos ant sistema.
	 *
	 * @param as_idTurno Variable de tipo String la cual contiene el id del turno en tramite
	 * @param as_idDatosAntSistema Vairiable de tipo String que contiene el id de los datos de antiguo sistema
	 * @param ab_activos correspondiente al valor del tipo de objeto boolean
	 * @return Colección que contiene la información consultada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findMatriculasByturnoAntSistema(
	    String as_idTurno, String as_idDatosAntSistema, boolean ab_activos
	)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcsm_datos     = new ArrayList<SolicitudMatricula>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int           li_count;
			StringBuilder lsb_query;

			li_count      = 1;
			lsb_query     = new StringBuilder();

			lsb_query.append(cs_FIND_MATRICULAS_BY_TURNO_ANT_SISTEMA);

			if(ab_activos)
				lsb_query.append(" AND NVL(SM.ESTADO,'A') <> 'I' ");

			lps_ps = getConnection().prepareStatement(lsb_query.toString());

			lps_ps.setString(li_count++, as_idTurno);
			lps_ps.setString(li_count++, as_idDatosAntSistema);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsm_datos.add(getObjetFromResultSet(lrs_rs, false));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMatriculasByturnoAntSistema", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Metodo encargado de insertar o actualizar un registro de la tabla SDB_ACC_SOLICITUD_MATRICULA.
	 *
	 * @param asm_arg Argumento de tipo <code>SolicitudMatricula</code> que contiene los criterios a ser insertados o actualizados.
	 * @param ab_query Argumento de tipo <code>boolean</code> que determina si se debe insertar <code>true</code> o actualizar <code>false</code>.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(SolicitudMatricula asm_arg, boolean ab_query)
	    throws B2BException
	{
		if(asm_arg != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					String ls_estado;
					ls_estado = asm_arg.getEstado();

					if(!StringUtils.isValidString(ls_estado))
						ls_estado = "A";

					lps_ps.setString(li_column++, asm_arg.getIdSolicitud());
					lps_ps.setString(li_column++, asm_arg.getIdCirculo());
					lps_ps.setLong(li_column++, asm_arg.getIdMatricula());
					lps_ps.setString(li_column++, asm_arg.getExpedirCertificado());
					lps_ps.setBigDecimal(li_column++, asm_arg.getCantidadCertificados());
					lps_ps.setString(li_column++, asm_arg.getIdTurnoCertificado());
					lps_ps.setString(li_column++, asm_arg.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, asm_arg.getIpCreacion());
					lps_ps.setString(li_column++, ls_estado);
					lps_ps.setString(li_column++, asm_arg.getCementerio());
					setLong(lps_ps, asm_arg.getCantidadAperturar(), li_column++);
					lps_ps.setString(li_column++, asm_arg.getIdDatosAntSistema());
					lps_ps.setString(li_column++, asm_arg.getCedulaCatastral());
					lps_ps.setString(li_column++, asm_arg.getIndMayorValor());
					lps_ps.setString(li_column++, asm_arg.isIndicadorEnglobeCorrecciones());
				}
				else
				{
					String ls_estado;
					ls_estado = asm_arg.getEstado();

					if(!StringUtils.isValidString(ls_estado))
						ls_estado = "A";

					lps_ps.setString(li_column++, asm_arg.getExpedirCertificado());
					lps_ps.setBigDecimal(li_column++, asm_arg.getCantidadCertificados());
					lps_ps.setString(li_column++, asm_arg.getIdTurnoCertificado());
					lps_ps.setString(li_column++, ls_estado);
					lps_ps.setString(li_column++, asm_arg.getCementerio());
					lps_ps.setString(li_column++, asm_arg.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, asm_arg.getIpModificacion());
					setLong(lps_ps, asm_arg.getCantidadAperturar(), li_column++);
					lps_ps.setString(li_column++, asm_arg.getIdDatosAntSistema());
					lps_ps.setString(li_column++, asm_arg.getCedulaCatastral());
					lps_ps.setString(li_column++, asm_arg.getIndMayorValor());
					lps_ps.setString(li_column++, asm_arg.isIndicadorEnglobeCorrecciones());
					lps_ps.setString(li_column++, asm_arg.getIdSolicitud());
					lps_ps.setString(li_column++, asm_arg.getIdCirculo());
					lps_ps.setLong(li_column++, asm_arg.getIdMatricula());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de validar si una matricula presenta una medida cautelar vigente.
	 *
	 * @param as_solicitud String con solicitud a consultar
	 * @param as_idCirculo String con circulo a consultar
	 * @param al_idMatricula Long con matrícula a consultar
	 * @return <code>Collection<SolicitudMatricula></code> con SolicitudMatricula resultado de consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> validacionMedidaCautelar(
	    String as_solicitud, String as_idCirculo, Long al_idMatricula
	)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_datos;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcsm_datos     = new ArrayList<SolicitudMatricula>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int    li_count;
			String ls_query;

			li_count     = 1;
			ls_query     = cs_FIND_MATRICULAS_FILTER_BY_VALIDACION_MEDIDA_CAUTELAR;

			lps_ps = getConnection().prepareStatement(ls_query);

			lps_ps.setString(li_count++, as_solicitud);
			lps_ps.setString(li_count++, as_idCirculo);
			setLong(lps_ps, al_idMatricula, li_count++);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsm_datos.add(getObjetFromResultSet(lrs_rs, false));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMatriculasByturnoAntSistema", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/**
	 * Retorna Objeto o variable de valor object from result set with nombre circulo and direccion.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from result set with nombre circulo and direccion
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private SolicitudMatricula getObjectFromResultSetWithNombreCirculoAndDireccion(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudMatricula lsm_solicitudMatricula;

		lsm_solicitudMatricula = getObjetFromResultSet(ars_rs, false, false);
		lsm_solicitudMatricula.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));
		lsm_solicitudMatricula.setNumeroPredial(ars_rs.getString("NUMERO_PREDIAL"));
		lsm_solicitudMatricula.setDireccionCompleta(ars_rs.getString("DIRECCION_COMPLETA"));

		return lsm_solicitudMatricula;
	}

	/**
	 * Retorna el valor de SolicitudMatricula.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return el valor de SolicitudMatricula
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudMatricula getObjetFromResultSet(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		return getObjetFromResultSet(ars_rs, ab_b, false);
	}

	/**
	 * Retorna el valor SolicitudMatricula.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param ab_estado correspondiente al valor del tipo de objeto boolean
	 * @return el valor de SolicitudMatricula
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	private SolicitudMatricula getObjetFromResultSet(ResultSet ars_rs, boolean ab_b, boolean ab_estado)
	    throws SQLException
	{
		SolicitudMatricula lsm_solicitudMatricula;

		lsm_solicitudMatricula = new SolicitudMatricula();

		lsm_solicitudMatricula.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lsm_solicitudMatricula.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsm_solicitudMatricula.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lsm_solicitudMatricula.setExpedirCertificado(ars_rs.getString("EXPEDIR_CERTIFICADO"));
		lsm_solicitudMatricula.setCantidadCertificados(getBigDecimal(ars_rs, "CANTIDAD_CERTIFICADOS"));
		lsm_solicitudMatricula.setIdTurnoCertificado(ars_rs.getString("ID_TURNO_CERTIFICADO"));

		lsm_solicitudMatricula.setMatriculaConcatenada(
		    StringUtils.getStringNotNull(lsm_solicitudMatricula.getIdCirculo()) + " - "
		    + lsm_solicitudMatricula.getIdMatricula()
		);

		if(ab_estado)
			lsm_solicitudMatricula.setEstado(ars_rs.getString("ESTADO"));

		if(!ab_b)
		{
			lsm_solicitudMatricula.setCementerio(ars_rs.getString("CEMENTERIO"));
			lsm_solicitudMatricula.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
			lsm_solicitudMatricula.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		}
		else
			lsm_solicitudMatricula.setDireccionCompleta(ars_rs.getString("DIRECCION_COMPLETA"));

		return lsm_solicitudMatricula;
	}

	/**
	 * Método encargado de retornar el objeto SolicitudMatricula a partir de la respuesta de la consulta.
	 *
	 * @param ars_rs Objeto ResultSet que contiene la respuesta de la consulta.
	 * @return Objeto que contiene la información de SolicitudMatricula.
	 * @throws SQLException Señala que se ha producido una excepción.
	 */
	private SolicitudMatricula getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudMatricula lsm_return;

		lsm_return = new SolicitudMatricula();

		lsm_return.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lsm_return.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsm_return.setIdMatricula(NumericUtils.getLong(getLong(ars_rs, "ID_MATRICULA")));
		lsm_return.setExpedirCertificado(ars_rs.getString("EXPEDIR_CERTIFICADO"));
		lsm_return.setCantidadCertificados(getBigDecimal(ars_rs, "CANTIDAD_CERTIFICADOS"));
		lsm_return.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lsm_return.setIdTurnoCertificado(ars_rs.getString("ID_TURNO_CERTIFICADO"));
		lsm_return.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lsm_return.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lsm_return.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lsm_return.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lsm_return.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lsm_return.setEstado(ars_rs.getString("ESTADO"));
		lsm_return.setCementerio(ars_rs.getString("CEMENTERIO"));
		lsm_return.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lsm_return.setNumeroPredial(ars_rs.getString("NUMERO_PREDIAL"));
		lsm_return.setCedulaCatastral(ars_rs.getString("CEDULA_CATASTRAL"));
		lsm_return.setIndMayorValor(ars_rs.getString("IND_MAYOR_VALOR"));
		lsm_return.setIndicadorEnglobeCorrecciones(ars_rs.getString("INDICADOR_ENGLOBE_CORRECCIONES"));

		return lsm_return;
	}

	/**
	 * Retorna el valor de SolicitudMatricula.
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de SolicitudMatricula
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudMatricula getObjetResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		SolicitudMatricula ls_solicitud;

		ls_solicitud = new SolicitudMatricula();

		ls_solicitud.setIdCirculo(lrs_rs.getString("MATRICULA"));
		ls_solicitud.setEstado(lrs_rs.getString("ESTADO_PREDIO"));

		return ls_solicitud;
	}

	/**
	 * Retorna el valor del SolicitudMatricula.
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return devuelve el valor de SolicitudMatricula
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	private SolicitudMatricula infoCirculoRegistral(ResultSet lrs_rs)
	    throws SQLException
	{
		SolicitudMatricula ls_solicitud;

		ls_solicitud = new SolicitudMatricula();

		ls_solicitud.setIdCirculo(lrs_rs.getString("CIRCULO_REGISTRAL"));

		return ls_solicitud;
	}

	/**
	 * Método para la obtención del valor de solicitud matricula de la consulta.
	 *
	 * @param ars_rs de tipo resultSet con los parametros de la consulta
	 * @return Solicitud Matricula con los datos requeridos
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private SolicitudMatricula obtenerSolicitudMatricula(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudMatricula lsm_solicitudMatricula;

		lsm_solicitudMatricula = new SolicitudMatricula();

		lsm_solicitudMatricula.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lsm_solicitudMatricula.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsm_solicitudMatricula.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lsm_solicitudMatricula.setExpedirCertificado(ars_rs.getString("EXPEDIR_CERTIFICADO"));
		lsm_solicitudMatricula.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lsm_solicitudMatricula.setCantidadCertificados(getBigDecimal(ars_rs, "CANTIDAD_CERTIFICADOS"));
		lsm_solicitudMatricula.setIdTurnoCertificado(ars_rs.getString("ID_TURNO_CERTIFICADO"));

		lsm_solicitudMatricula.setMatriculaConcatenada(
		    StringUtils.getStringNotNull(lsm_solicitudMatricula.getIdCirculo()) + " - "
		    + lsm_solicitudMatricula.getIdMatricula()
		);

		return lsm_solicitudMatricula;
	}
}
