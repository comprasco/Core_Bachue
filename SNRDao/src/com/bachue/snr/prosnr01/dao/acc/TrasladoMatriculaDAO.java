package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Clase para el manejo de datos para la tabla SDB_ACC_TRASLADO_MATRICULA.
 *
 * @author Gabriel Arias
 */
public class TrasladoMatriculaDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_TURNO = "SELECT * FROM SDB_ACC_TRASLADO_MATRICULA WHERE ID_TURNO = ?";

	/** Constante cs_FIND_DATA_PREDIO. */
	private static final String cs_FIND_DATA_PREDIO = "SELECT ATM.ID_CIRCULO_ORIGEN || ' - ' ||  PNCR.NOMBRE CIRCULO_ORIGEN,ATM.ID_CIRCULO_DESTINO || ' - ' ||  PNCR1.NOMBRE CIRCULO_DESTINO,ATM.ID_MATRICULA_ORIGEN MATRICULA,BDR.DIRECCION_COMPLETA FROM SDB_ACC_TRASLADO_MATRICULA ATM INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PNCR ON PNCR.ID_CIRCULO = ATM.ID_CIRCULO_ORIGEN INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PNCR1 ON PNCR1.ID_CIRCULO = ATM.ID_CIRCULO_DESTINO INNER JOIN SDB_BNG_DIRECCION_PREDIO BDR ON BDR.ID_CIRCULO = ATM.ID_CIRCULO_ORIGEN AND BDR.ID_MATRICULA = ATM.ID_MATRICULA_ORIGEN WHERE BDR.ID_CIRCULO = ? AND BDR.ID_MATRICULA = ? AND ATM.ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = "SELECT * FROM SDB_ACC_TRASLADO_MATRICULA WHERE ID_SOLICITUD = ? ";

	/** Constante cs_FIND_BY_SOLICITUD_ORDER_BY_FECHA_CREACION_DESC. */
	private static final String cs_FIND_BY_SOLICITUD_ORDER_BY_FECHA_CREACION_DESC = "SELECT * FROM SDB_ACC_TRASLADO_MATRICULA WHERE ID_SOLICITUD = ? ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_SOLICITUD_CIRCULO_MATRICULA_ORIGEN. */
	private static final String cs_FIND_BY_SOLICITUD_CIRCULO_MATRICULA_ORIGEN = "SELECT * FROM SDB_ACC_TRASLADO_MATRICULA WHERE ID_SOLICITUD = ? AND ID_CIRCULO_ORIGEN = ? AND ID_MATRICULA_ORIGEN = ?";

	/** Constante CS_CONSULTA_BANDEJA_TURNOS_PROYECTA_RESOLUCION. */
	private static final String CS_CONSULTA_BANDEJA_TURNOS_PROYECTA_RESOLUCION = "SELECT P.NOMBRE PROCESO, TH.ID_TURNO, TH.FECHA_REPARTO FECHA_ASIGNACION, S.NIR,SP.NOMBRE SUBPROCESO, TH.MOTIVO_NO_TRAMITE, "
		+ " THANT.OBSERVACIONES OBSERVACIONES, TH.ID_TURNO_HISTORIA, TH.ESTADO_ACTIVIDAD, TH.ID_MOTIVO FROM SDB_ACC_TURNO_HISTORIA TH INNER JOIN SDB_ACC_TURNO T ON TH.ID_TURNO = T.ID_TURNO "
		+ " INNER JOIN SDB_ACC_PROCESO P ON TH.ID_PROCESO = P.ID_PROCESO LEFT JOIN SDB_ACC_SOLICITUD S ON TH.ID_PROCESO = S.ID_PROCESO AND TH.ID_SOLICITUD = S.ID_SOLICITUD "
		+ " INNER JOIN SDB_ACC_SUBPROCESO SP ON S.ID_SUBPROCESO = SP.ID_SUBPROCESO"
		+ " LEFT JOIN SDB_ACC_TURNO_HISTORIA THANT ON TH.ID_ANTERIOR = THANT.ID_TURNO_HISTORIA WHERE TH.ID_ETAPA = ? AND TH.ID_USUARIO = ? AND TH.ESTADO_ACTIVIDAD IN ('ASG') ";

	/** Constante CS_CONSULTA_BANDEJA_TURNOS_PROYECTA_RESOLUCION SIN USUARIO. */
	private static final String CS_CONSULTA_BANDEJA_TURNOS_PROYECTA_RESOLUCION_SIN_USUARIO = "SELECT P.NOMBRE PROCESO, TH.ID_TURNO, TH.FECHA_REPARTO FECHA_ASIGNACION, TH.MOTIVO_NO_TRAMITE, S.NIR, TH.MOTIVO_NO_TRAMITE, "
		+ " THANT.OBSERVACIONES OBSERVACIONES, TH.ID_TURNO_HISTORIA, TH.ESTADO_ACTIVIDAD, TH.ID_MOTIVO FROM SDB_ACC_TURNO_HISTORIA TH INNER JOIN SDB_ACC_TURNO T ON TH.ID_TURNO = T.ID_TURNO "
		+ " INNER JOIN SDB_ACC_PROCESO P ON TH.ID_PROCESO = P.ID_PROCESO LEFT JOIN SDB_ACC_SOLICITUD S ON TH.ID_PROCESO = S.ID_PROCESO AND TH.ID_SOLICITUD = S.ID_SOLICITUD "
		+ " LEFT JOIN SDB_ACC_TURNO_HISTORIA THANT ON TH.ID_ANTERIOR = THANT.ID_TURNO_HISTORIA WHERE TH.ID_ETAPA = ? AND TH.ESTADO_ACTIVIDAD IN ('ASG') ";

	/** Constante CS_CANTIDAD_POR_PROYECTA_RESOLUCION. */
	private static final String CS_CANTIDAD_POR_PROYECTA_RESOLUCION = "SELECT TH.ID_ETAPA, COUNT(TH.ID_ETAPA) CANTIDAD FROM SDB_ACC_TURNO_HISTORIA TH WHERE TH.ESTADO_ACTIVIDAD IN ('ASG') AND TH.ID_ETAPA = ? AND TH.ID_USUARIO = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_TRASLADO_MATRICULA (ID_TRASLADO_MATRICULA,ID_TURNO,ID_SOLICITUD,ID_CIRCULO_ORIGEN,ID_MATRICULA_ORIGEN,ID_CIRCULO_DESTINO,ID_MATRICULA_DESTINO_TMP,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_ACTUALIZAR_ESTADO_N_BY_ID_TRASLADO_MATRICULA. */
	private static final String cs_ACTUALIZAR_ESTADO_N_BY_ID_TRASLADO_MATRICULA = "UPDATE SDB_ACC_TRASLADO_MATRICULA SET ACTIVO = 'N', ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TRASLADO_MATRICULA = ?";

	/** Constante cs_DELETE_BY_SOLICITUD. */
	private static final String cs_DELETE_BY_SOLICITUD = "DELETE FROM SDB_ACC_TRASLADO_MATRICULA WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_TRASLADO_MATRICULA_ID_TRASLADO_MATRICULA.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor de custon query detail.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param lhmpCriterios correspondiente al valor del tipo de objeto Map<Integer,Object>
	 * @return el valor de custon query detail
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getCustonQueryDetail(
	    String as_idTurno, String as_idSolicitud, Long al_idEtapa, Map<Integer, Object> lhmpCriterios
	)
	    throws B2BException
	{
		List<Map<String, Object>> lllhmpRet     = new LinkedList<Map<String, Object>>();
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;
		StringBuilder             lsb_sb;
		boolean                   lb_sinUsuario;

		lps_ps                                  = null;
		lrs_rs                                  = null;
		lsb_sb                                  = new StringBuilder();
		lb_sinUsuario                           = false;

		try
		{
			lb_sinUsuario = al_idEtapa.equals(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_EJECUTOR));

			if(lb_sinUsuario)
				lsb_sb = lsb_sb.append(CS_CONSULTA_BANDEJA_TURNOS_PROYECTA_RESOLUCION_SIN_USUARIO);
			else
				lsb_sb = lsb_sb.append(CS_CONSULTA_BANDEJA_TURNOS_PROYECTA_RESOLUCION);

			if(StringUtils.isValidString(as_idTurno))
			{
				lsb_sb = lsb_sb.append("AND TH.ID_TURNO = ? ");
				lhmpCriterios.put(NumericUtils.getInteger(3), as_idTurno);

				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? ");
					lhmpCriterios.put(NumericUtils.getInteger(4), as_idSolicitud);
				}
			}
			else
			{
				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? ");
					lhmpCriterios.put(NumericUtils.getInteger(3), as_idSolicitud);
				}
			}

			lsb_sb     = lsb_sb.append("ORDER BY TH.ID_TURNO ASC, TH.FECHA_INICIAL,TH.FECHA_CREACION DESC ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			for(Map.Entry<Integer, Object> criterios : lhmpCriterios.entrySet())
			{
				int li_key;

				li_key = NumericUtils.getInt(criterios.getKey());

				if(lb_sinUsuario)
				{
					if(li_key != 2)
					{
						if(li_key > 2)
							lps_ps.setObject(li_key - 1, criterios.getValue());
						else
							lps_ps.setObject(li_key, criterios.getValue());
					}
				}
				else
					lps_ps.setObject(li_key, criterios.getValue());
			}

			lrs_rs = lps_ps.executeQuery();

			ResultSetMetaData md      = lrs_rs.getMetaData();
			int               columns = md.getColumnCount();

			while(lrs_rs.next())
			{
				LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>(columns);

				for(int i = 1; i <= columns; ++i)
					row.put(md.getColumnLabel(i), lrs_rs.getObject(i));

				lllhmpRet.add(row);
			}

			if(lllhmpRet.isEmpty())
				lllhmpRet = null;
		}
		catch(SQLException lse_e)
		{
			logError(this, "getCustonQuery", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lllhmpRet;
	}

	/**
	  * Retorna el valor de custon query ibox.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param lhmpCriterios correspondiente al valor del tipo de objeto LinkedHashMap<Integer,Object>
	 * @return el valor de custon query ibox
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public LinkedList<LinkedHashMap<String, Object>> getCustonQueryIbox(
	    String as_idTurno, String as_idSolicitud, LinkedHashMap<Integer, Object> lhmpCriterios
	)
	    throws B2BException
	{
		LinkedList<LinkedHashMap<String, Object>> lllhmpRet = new LinkedList<LinkedHashMap<String, Object>>();
		PreparedStatement                         lps_ps;
		ResultSet                                 lrs_rs;
		StringBuilder                             lsb_sb;

		lps_ps                                              = null;
		lrs_rs                                              = null;
		lsb_sb                                              = new StringBuilder();

		try
		{
			lsb_sb = lsb_sb.append(CS_CANTIDAD_POR_PROYECTA_RESOLUCION);

			if(StringUtils.isValidString(as_idTurno))
			{
				lsb_sb = lsb_sb.append("AND TH.ID_TURNO = ? ");
				lhmpCriterios.put(NumericUtils.getInteger(3), as_idTurno);

				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? GROUP BY TH.ID_ETAPA ");
					lhmpCriterios.put(NumericUtils.getInteger(4), as_idSolicitud);
				}
				else
					lsb_sb = lsb_sb.append("GROUP BY TH.ID_ETAPA ");
			}
			else
			{
				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? GROUP BY TH.ID_ETAPA ");
					lhmpCriterios.put(NumericUtils.getInteger(3), as_idSolicitud);
				}
				else
					lsb_sb = lsb_sb.append("GROUP BY TH.ID_ETAPA ");
			}

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			for(Map.Entry<Integer, Object> criterios : lhmpCriterios.entrySet())
				lps_ps.setObject(NumericUtils.getInt(criterios.getKey()), criterios.getValue());

			lrs_rs = lps_ps.executeQuery();

			ResultSetMetaData md      = lrs_rs.getMetaData();
			int               columns = md.getColumnCount();

			while(lrs_rs.next())
			{
				LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>(columns);

				for(int i = 1; i <= columns; ++i)
					row.put(md.getColumnLabel(i), lrs_rs.getObject(i));

				lllhmpRet.add(row);
			}

			if(lllhmpRet.isEmpty())
				lllhmpRet = null;
		}
		catch(SQLException lse_e)
		{
			logError(this, "getCustonQuery", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lllhmpRet;
	}

	/**
	 * Actualiza un registro a un estado inactivo
	 * con la información suministrada.
	 *
	 * @param atm_param objeto contenedor de tipo TrasladoMatricula con la información a actualizar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarTrasladoMatriculaEstadoN(TrasladoMatricula atm_param)
	    throws B2BException
	{
		if(atm_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				Connection lc_C;
				int        li_column;

				li_column     = 1;
				lc_C          = getConnection();
				lps_ps        = lc_C.prepareStatement(cs_ACTUALIZAR_ESTADO_N_BY_ID_TRASLADO_MATRICULA);

				lps_ps.setString(li_column++, atm_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atm_param.getIpModificacion());
				lps_ps.setString(li_column++, atm_param.getIdTrasladoMatricula());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarTrasladoMatriculaEstadoN", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Buscar por id solicitud circulo matricula origen.
	 *
	 * @param as_idSolicitud correspondiente al valor de id solicitud
	 * @param as_idCirculo correspondiente al valor de id circulo
	 * @param al_idMatricula correspondiente al valor de id matricula
	 * @return el valor de traslado matricula
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TrasladoMatricula buscarPorIdSolicitudCirculoMatriculaOrigen(
	    String as_idSolicitud, String as_idCirculo, Long al_idMatricula
	)
	    throws B2BException
	{
		TrasladoMatricula lsp_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lsp_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo)
				    && NumericUtils.isValidLong(al_idMatricula)
			)
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD_CIRCULO_MATRICULA_ORIGEN);

				lps_ps.setString(li_column++, as_idSolicitud);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsp_return = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdSolicitudCirculoMatriculaOrigen", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lsp_return;
	}

	/**
	 * Método encargado de eliminar los registros para la solicitud.
	 *
	 * @param as_idSolicitud Contiene el id de la solicitud.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public void deleteByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de consultar por id solicitud.
	 *
	 * @param as_idSolicitud Contiene la información de la solicitud.
	 * @return Colección de objetos consultados.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public Collection<TrasladoMatricula> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		return findByIdSolicitud(as_idSolicitud, false);
	}

	/**
	 * Método encargado de consultar por id solicitud.
	 *
	 * @param as_idSolicitud Contiene la información de la solicitud.
	 * @return Colección de objetos consultados.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public Collection<TrasladoMatricula> findByIdSolicitud(String as_idSolicitud, boolean ab_activo)
	    throws B2BException
	{
		Collection<TrasladoMatricula> lcsp_return;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcsp_return     = new ArrayList<TrasladoMatricula>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_FIND_BY_SOLICITUD);

				if(ab_activo)
					lsb_query.append(" AND NVL(ACTIVO,'S') = 'S' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsp_return.add(getObjetFromResultSet(lrs_rs));
			}
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

		if(lcsp_return.isEmpty())
			lcsp_return = null;

		return lcsp_return;
	}

	/**
	 * Método encargado de consultar por id turno.
	 *
	 * @param as_idTurno Contiene la información del turno.
	 * @return Colección de objetos consultados.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public Collection<TrasladoMatricula> findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<TrasladoMatricula> lcsp_return;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcsp_return     = new ArrayList<TrasladoMatricula>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_TURNO);

				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsp_return.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcsp_return.isEmpty())
			lcsp_return = null;

		return lcsp_return;
	}

	/**
	 * Método encargado de consultar la información del predio.
	 *
	 * @param as_idCirculo Contiene la información del circulo.
	 * @param al_idMatricula Contiene la información de la matricula.
	 * @param ls_idSolicitud Contiene la información de la solicitud.
	 * @return Mapa de objetos consultados.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public Map<String, String> findDataPredio(String as_idCirculo, Long al_idMatricula, String ls_idSolicitud)
	    throws B2BException
	{
		Map<String, String> lmss_return;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lmss_return     = new HashMap<String, String>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
				    && StringUtils.isValidString(ls_idSolicitud)
			)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_DATA_PREDIO);

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);
				lps_ps.setString(li_column++, ls_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
				{
					lmss_return.put("CIRCULO_ORIGEN", lrs_rs.getString("CIRCULO_ORIGEN"));
					lmss_return.put("CIRCULO_DESTINO", lrs_rs.getString("CIRCULO_DESTINO"));
					lmss_return.put("MATRICULA", StringUtils.getString(getLong(lrs_rs, "MATRICULA")));
					lmss_return.put("DIRECCION_COMPLETA", lrs_rs.getString("DIRECCION_COMPLETA"));
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDataPredio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lmss_return.isEmpty())
			lmss_return = null;

		return lmss_return;
	}

	/**
	 * Find one by id solicitud.
	 *
	 * @param as_idSolicitud correspondiente al valor de as id solicitud
	 * @return el valor de traslado matricula
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TrasladoMatricula findOneByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		TrasladoMatricula lsp_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lsp_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD_ORDER_BY_FECHA_CREACION_DESC);

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsp_return = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findOneByIdSolicitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lsp_return;
	}

	/**
	 * Método encargado de insertar o actualizar un registro para traslado de matriculas.
	 *
	 * @param asp_param Objeto que contiene la información a insertar o actualizar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(TrasladoMatricula asp_param)
	    throws B2BException
	{
		if(asp_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_C;
				int        li_column;

				li_column         = 1;
				lc_C              = getConnection();
				lps_ps            = lc_C.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCIA);

				{
					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
				}

				lps_ps.setString(li_column++, asp_param.getIdTurno());
				lps_ps.setString(li_column++, asp_param.getIdSolicitud());
				lps_ps.setString(li_column++, asp_param.getIdCirculoOrigen());
				setLong(lps_ps, asp_param.getIdMatriculaOrigen(), li_column++);
				lps_ps.setString(li_column++, asp_param.getIdCirculoDestino());
				setLong(lps_ps, asp_param.getIdMatriculaDestinoTmp(), li_column++);
				lps_ps.setString(li_column++, asp_param.getActivo());
				lps_ps.setString(li_column++, asp_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, asp_param.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Método que construye el modelo de la tabla consultada.
	 *
	 * @param ars_rs ResultSet que contiene el resultado de la búsqueda.
	 * @return Objeto que contiene la información consultada.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TrasladoMatricula getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TrasladoMatricula atm_return;

		atm_return = new TrasladoMatricula();

		atm_return.setIdTrasladoMatricula(ars_rs.getString("ID_TRASLADO_MATRICULA"));
		atm_return.setIdTurno(ars_rs.getString("ID_TURNO"));
		atm_return.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		atm_return.setIdCirculoOrigen(ars_rs.getString("ID_CIRCULO_ORIGEN"));
		atm_return.setIdCirculoDestino(ars_rs.getString("ID_CIRCULO_DESTINO"));
		atm_return.setIdMatriculaOrigen(getLong(ars_rs, "ID_MATRICULA_ORIGEN"));
		atm_return.setIdMatriculaDestinoTmp(getLong(ars_rs, "ID_MATRICULA_DESTINO_TMP"));
		atm_return.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		atm_return.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		atm_return.setIpCreacion(ars_rs.getString("IP_CREACION"));
		atm_return.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		atm_return.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		atm_return.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return atm_return;
	}
}
