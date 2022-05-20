package com.bachue.snr.prosnr01.dao.util;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.entrega.TramiteTurno;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.Map.Entry;


/**
 * Clase que contiene todos las propiedades UtilDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 27/05/2020
 */
public class UtilDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_DATOS_BANDEJA_REIMPRESION. */
	private static final String cs_DATOS_BANDEJA_REIMPRESION = "SELECT TH.ID_TURNO, S.NIR, TH.FECHA_CREACION AS FECHA_ASIGNACION, TH.ID_TURNO_HISTORIA, TH.ESTADO_ACTIVIDAD, TH.MOTIVO_NO_TRAMITE, FA.NOMBRE, TH.ID_ETAPA "
		+ "FROM SDB_ACC_TURNO_HISTORIA TH INNER JOIN SDB_ACC_SOLICITUD S ON TH.ID_SOLICITUD = S.ID_SOLICITUD INNER JOIN SDB_ACC_TURNO T ON TH.ID_TURNO = T.ID_TURNO LEFT JOIN SDB_PGN_ETAPA F "
		+ "ON T.ID_ETAPA_ACTUAL = F.ID_ETAPA INNER JOIN SDB_PGN_FASES FA ON FA.ID_FASE = F.ID_FASE WHERE TH.ID_ETAPA >= ? AND TH.ID_ETAPA < ? AND TH.ESTADO_ACTIVIDAD = 'TER' ";

	/** Constante cs_PROC_REPORTES_CONCILIACIONES_DINAMICO_1. */
	private static final String cs_PROC_REPORTES_CONCILIACIONES_DINAMICO_1 = "BEGIN ";

	/** Constante cs_PROC_REPORTES_CONCILIACIONES_DINAMICO_2. */
	private static final String cs_PROC_REPORTES_CONCILIACIONES_DINAMICO_2 = "(?,?,?,?,?); END;";

	/**
	 * Retorna una lista de una consulta
	 *
	 * @param as_consulta query a ejecutar en base de datos
	 * @param amio_criterios LinkedList<Integer, Object> donde la primera clave es el primer parámetro para establecer en la consulta, no puede ser nulo
	 * @return lllhmpRet LinkedList con los resultados de la consulta, si la consulta no tenía filas devuelve nulo
	 * @throws B2BException en sql error
	 */
	public List<Map<String, Object>> getCustonQuery(String as_consulta, Map<Integer, Object> amio_criterios)
	    throws B2BException
	{
		List<Map<String, Object>> lcmso_result;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcmso_result     = new LinkedList<Map<String, Object>>();
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_consulta))
		{
			try
			{
				ResultSetMetaData lrsmd_md;
				int               li_columns;

				lps_ps = getConnection().prepareStatement(as_consulta);

				if(CollectionUtils.isValidCollection(amio_criterios))
				{
					for(Map.Entry<Integer, Object> lmeio_criterios : amio_criterios.entrySet())
					{
						if(lmeio_criterios != null)
							lps_ps.setObject(NumericUtils.getInt(lmeio_criterios.getKey()), lmeio_criterios.getValue());
					}

					lrs_rs         = lps_ps.executeQuery();
					lrsmd_md       = lrs_rs.getMetaData();
					li_columns     = lrsmd_md.getColumnCount();

					while(lrs_rs.next())
					{
						Map<String, Object> lmso_mso;

						lmso_mso = new LinkedHashMap<String, Object>(li_columns);

						for(int i = 1; i <= li_columns; ++i)
							lmso_mso.put(lrsmd_md.getColumnLabel(i), lrs_rs.getObject(i));

						lcmso_result.add(lmso_mso);
					}

					if(lcmso_result.isEmpty())
						lcmso_result = null;
				}
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
		}

		return lcmso_result;
	}

	/**
	 * Retorna Objeto o variable de valor custon query calificacion.
	 *
	 * @param as_consulta de as consulta
	 * @param as_idTurno de as id turno
	 * @param as_idSolicitud de as id solicitud
	 * @param lhmpCriterios de lhmp criterios
	 * @return el valor de custon query calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> getCustonQueryCalificacion(
	    String as_consulta, String as_idTurno, String as_idSolicitud, Map<Integer, Object> lhmpCriterios
	)
	    throws B2BException
	{
		List<Map<String, Object>> lllhmpRet = new LinkedList<Map<String, Object>>();
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;
		StringBuilder             lsb_sb;

		lps_ps                              = null;
		lrs_rs                              = null;
		lsb_sb                              = new StringBuilder();

		try
		{
			lsb_sb = lsb_sb.append(as_consulta);

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
				Map<String, Object> row = new LinkedHashMap<String, Object>(columns);

				for(int i = 1; i <= columns; ++i)
					row.put(md.getColumnLabel(i), lrs_rs.getObject(i));

				lllhmpRet.add(row);
			}

			if(lllhmpRet.isEmpty())
				lllhmpRet = null;
		}
		catch(SQLException lse_e)
		{
			logError(this, "getCustonQueryCalificacion", lse_e);

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
	 * Retorna Objeto o variable de valor custon query entrega.
	 *
	 * @param as_consulta de as consulta
	 * @param as_idTurno de as id turno
	 * @param as_idSolicitud de as id solicitud
	 * @param amio_criterios de amio criterios
	 * @return el valor de custon query entrega
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> getCustonQueryEntrega(
	    String as_consulta, String as_idTurno, String as_idSolicitud, Map<Integer, Object> amio_criterios
	)
	    throws B2BException
	{
		List<Map<String, Object>> llmso_return;

		llmso_return = null;

		if(CollectionUtils.isValidCollection(amio_criterios))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_contador;

				lsb_sb          = new StringBuilder(as_consulta);
				li_contador     = 2;

				if(StringUtils.isValidString(as_idTurno))
				{
					lsb_sb = lsb_sb.append("AND TH.ID_TURNO = ? ");
					amio_criterios.put(NumericUtils.getInteger(li_contador++), as_idTurno);

					if(StringUtils.isValidString(as_idSolicitud))
					{
						lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? GROUP BY TH.ID_ETAPA ");
						amio_criterios.put(NumericUtils.getInteger(li_contador++), as_idSolicitud);
					}
					else
						lsb_sb = lsb_sb.append(" GROUP BY TH.ID_ETAPA ");
				}
				else
				{
					if(StringUtils.isValidString(as_idSolicitud))
					{
						lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? GROUP BY TH.ID_ETAPA ");
						amio_criterios.put(NumericUtils.getInteger(li_contador++), as_idSolicitud);
					}
					else
						lsb_sb = lsb_sb.append("GROUP BY TH.ID_ETAPA ");
				}

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				for(Map.Entry<Integer, Object> leio_iterador : amio_criterios.entrySet())
				{
					if(leio_iterador != null)
						lps_ps.setObject(NumericUtils.getInt(leio_iterador.getKey()), leio_iterador.getValue());
				}

				lrs_rs = lps_ps.executeQuery();

				{
					ResultSetMetaData lrsmd_metaData;

					lrsmd_metaData = lrs_rs.getMetaData();

					if(lrsmd_metaData != null)
					{
						int li_columns;

						li_columns       = lrsmd_metaData.getColumnCount();
						llmso_return     = new LinkedList<Map<String, Object>>();

						while(lrs_rs.next())
						{
							Map<String, Object> lmso_row;

							lmso_row = new LinkedHashMap<String, Object>(li_columns);

							for(int li_iterador = 1; li_iterador <= li_columns; ++li_iterador)
								lmso_row.put(lrsmd_metaData.getColumnLabel(li_iterador), lrs_rs.getObject(li_iterador));

							llmso_return.add(lmso_row);
						}
					}
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "getCustonQueryEntrega", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return llmso_return;
	}

	/**
	 * Retorna Objeto o variable de valor custon query turnos.
	 *
	 * @param as_consulta de as consulta
	 * @param as_idTurno de as id turno
	 * @param as_idSolicitud de as id solicitud
	 * @param lhmpCriterios de lhmp criterios
	 * @return el valor de custon query turnos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> getCustonQueryTurnos(
	    String as_consulta, String as_idTurno, String as_idSolicitud, Map<Integer, Object> lhmpCriterios
	)
	    throws B2BException
	{
		List<Map<String, Object>> lllhmpRet = new LinkedList<Map<String, Object>>();
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;
		StringBuilder             lsb_sb;

		lps_ps                              = null;
		lrs_rs                              = null;
		lsb_sb                              = new StringBuilder();

		try
		{
			lsb_sb = lsb_sb.append(as_consulta);

			if(StringUtils.isValidString(as_idTurno))
			{
				lsb_sb = lsb_sb.append("AND TH.ID_TURNO = ? ");
				lhmpCriterios.put(NumericUtils.getInteger(3), as_idTurno);

				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append(
						    "AND TH.ID_SOLICITUD = ? AND TH.ESTADO_ACTIVIDAD IN ('ASG','BLQ') ORDER BY TH.ID_TURNO ASC, TH.FECHA_INICIAL,TH.FECHA_CREACION DESC "
						);
					lhmpCriterios.put(NumericUtils.getInteger(4), as_idSolicitud);
				}
				else
					lsb_sb = lsb_sb.append(
						    "AND TH.ESTADO_ACTIVIDAD IN ('ASG','BLQ') ORDER BY TH.ID_TURNO ASC, TH.FECHA_INICIAL,TH.FECHA_CREACION DESC "
						);
			}
			else
			{
				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append(
						    "AND TH.ID_SOLICITUD = ? AND TH.ESTADO_ACTIVIDAD IN ('ASG','BLQ') ORDER BY TH.ID_TURNO ASC, TH.FECHA_INICIAL,TH.FECHA_CREACION DESC "
						);
					lhmpCriterios.put(NumericUtils.getInteger(3), as_idSolicitud);
				}
				else
					lsb_sb = lsb_sb.append(
						    "AND TH.ESTADO_ACTIVIDAD IN ('ASG','BLQ') ORDER BY TH.ID_TURNO ASC, TH.FECHA_INICIAL,TH.FECHA_CREACION DESC "
						);
			}

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			if(CollectionUtils.isValidCollection(lhmpCriterios))
			{
				for(Map.Entry<Integer, Object> criterios : lhmpCriterios.entrySet())
					lps_ps.setObject(NumericUtils.getInt(criterios.getKey()), criterios.getValue());

				lrs_rs = lps_ps.executeQuery();

				ResultSetMetaData md      = lrs_rs.getMetaData();
				int               columns = md.getColumnCount();

				while(lrs_rs.next())
				{
					Map<String, Object> row = new LinkedHashMap<String, Object>(columns);

					for(int i = 1; i <= columns; ++i)
						row.put(md.getColumnLabel(i), lrs_rs.getObject(i));

					lllhmpRet.add(row);
				}

				if(lllhmpRet.isEmpty())
					lllhmpRet = null;
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "getCustonQueryTurnos", lse_e);

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
	 * Consultar orip principal.
	 *
	 * @param as_userId de as id solicitud
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String consultarOripPrincipal(String as_userId)
	    throws B2BException
	{
		String ls_orip;

		ls_orip = null;

		if(StringUtils.isValidString(as_userId))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_con;
				li_con     = 1;

				lps_ps = getConnection()
						         .prepareStatement(
						    "SELECT PKG_TRANSVERSALES.FUNC_ORIP_PPAL_USUARIO(?) ORIP_PRINCIPAL FROM DUAL"
						);

				lps_ps.setString(li_con++, as_userId);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_orip = lrs_rs.getString("ORIP_PRINCIPAL");
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultarOripPrincipal", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_orip;
	}

	/**
	 * Custom insert.
	 *
	 * @param amso_entidad de amso entidad
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean customInsert(Map<String, Object> amso_entidad)
	    throws B2BException
	{
		boolean lb_exito;

		lb_exito = false;

		if(CollectionUtils.isValidCollection(amso_entidad))
		{
			PreparedStatement lps_ps;
			SimpleDateFormat  lsdf_fecha;

			lps_ps         = null;
			lsdf_fecha     = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			try
			{
				Iterator<Entry<String, Object>> litr_actual;
				Object[]                        lao_valores = new Object[255];

				{
					Collection<Map.Entry<String, Object>> lcmeso_entidad;

					lcmeso_entidad     = amso_entidad.entrySet();
					litr_actual        = CollectionUtils.isValidCollection(lcmeso_entidad) ? lcmeso_entidad.iterator()
						                                                                   : null;
				}

				if(litr_actual != null)
				{
					int           li_i           = 0;
					StringBuilder lsb_insert;
					StringBuilder lsb_valores;
					String        ls_nombreTabla;
					ls_nombreTabla               = StringUtils.getString(amso_entidad.get("TABLABD"));

					lsb_insert = new StringBuilder("INSERT INTO ");

					lsb_insert.append(ls_nombreTabla);
					lsb_insert.append("(");

					lsb_valores = new StringBuilder();

					while(litr_actual.hasNext())
					{
						Map.Entry<String, Object> lmeso_entidad;

						lmeso_entidad = litr_actual.next();

						if(lmeso_entidad != null)
						{
							String ls_llave;

							ls_llave = lmeso_entidad.getKey();

							if((ls_llave != null) && !ls_llave.equalsIgnoreCase("TABLABD"))
							{
								Object lo_valor;

								lo_valor = lmeso_entidad.getValue();

								if(lo_valor != null)
								{
									lsb_insert.append(ls_llave);
									lsb_insert.append(",");

									String ls_clase = lo_valor.getClass().getCanonicalName().toUpperCase();

									if(lsb_valores.length() > 0)
										lsb_valores.append(",");

									if((ls_clase.indexOf("DATE") > -1) || (ls_clase.indexOf("TIME") > -1))
									{
										lsb_valores.append("TO_TIMESTAMP(?,'DD/MM/YYYY HH24:MI:SS')");
										lao_valores[li_i] = lsdf_fecha.format(lo_valor);
									}
									else
									{
										lsb_valores.append("?");
										lao_valores[li_i] = lo_valor;
									}

									li_i++;
								}
							}
						}
					}

					lsb_insert.deleteCharAt(lsb_insert.length() - 1);

					if(
					    StringUtils.isValidString(ls_nombreTabla)
						    && ls_nombreTabla.equals("SDB_ACC_SOLICITUD_MATRICULA")
					)
						lsb_insert.append(",ESTADO");

					lsb_insert.append(")VALUES(");
					lsb_insert.append(lsb_valores);

					if(
					    StringUtils.isValidString(ls_nombreTabla)
						    && ls_nombreTabla.equals("SDB_ACC_SOLICITUD_MATRICULA")
					)
						lsb_insert.append(",'A'");

					lsb_insert.append(")");

					lps_ps = getConnection().prepareStatement(lsb_insert.toString());

					for(int li_j = 0; li_j < li_i; li_j++)
						lps_ps.setObject(li_j + 1, lao_valores[li_j]);

					lps_ps.executeUpdate();
				}

				lb_exito = true;
			}
			catch(SQLException lse_e)
			{
				logError(this, "customInsert", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}

		return lb_exito;
	}

	/**
	 * Execute query.
	 *
	 * @param as_consulta de as consulta
	 * @param lhmpCriterios de lhmp criterios
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void executeQuery(String as_consulta, Map<Integer, Object> lhmpCriterios)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(as_consulta);

			if(CollectionUtils.isValidCollection(lhmpCriterios))
			{
				for(Map.Entry<Integer, Object> criterios : lhmpCriterios.entrySet())
				{
					if(criterios != null)
						lps_ps.setObject(NumericUtils.getInt(criterios.getKey()), criterios.getValue());
				}
			}

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "executeQuery", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}
	}

	/**
	 * Find reimpresion doc.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_nir de as nir
	 * @param al_etapaRangoInicio de al etapa rango inicio
	 * @param al_etapaRangoFin de al etapa rango fin
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteTurno> findReimpresionDoc(
	    String as_idTurno, String as_nir, Long al_etapaRangoInicio, Long al_etapaRangoFin, boolean ab_b
	)
	    throws B2BException
	{
		Collection<TramiteTurno> lcds_reimpresion;

		lcds_reimpresion = new ArrayList<TramiteTurno>();

		if(NumericUtils.isValidLong(al_etapaRangoInicio) && NumericUtils.isValidLong(al_etapaRangoFin))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;
			StringBuilder     lsb_sb;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;
			lsb_sb        = new StringBuilder(cs_DATOS_BANDEJA_REIMPRESION);

			if(ab_b)
				lsb_sb.append(" AND S.NIR = ? ");
			else
				lsb_sb.append(" AND T.ID_TURNO =? ");

			try
			{
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				setLong(lps_ps, al_etapaRangoInicio, li_column++);
				setLong(lps_ps, al_etapaRangoFin, li_column++);

				if(ab_b)
					lps_ps.setString(li_column++, as_nir);
				else
					lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_reimpresion.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findReimpresionDoc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcds_reimpresion;
	}

	/**
	 * Find secuence.
	 *
	 * @param as_consulta de as consulta
	 * @return el valor de int
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int findSecuence(String as_consulta)
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(as_consulta);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/**
	 * Funcion calcular dias fechas.
	 *
	 * @param afvtui_arg de afvtui arg
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long funcionCalcularDiasFechas(FechaVenceTerminosUI afvtui_arg)
	    throws B2BException
	{
		long              ll_long;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ll_long     = 0;
		lps_ps      = null;
		lrs_rs      = null;

		if(afvtui_arg != null)
		{
			try
			{
				int li_con;
				li_con     = 1;

				lps_ps = getConnection()
						         .prepareStatement(
						    "SELECT PKG_TRANSVERSALES.FUNC_DIAS_FECHAS(SYSDATE,?,?,?) DIAS_VENCIMIENTO FROM DUAL "
						);

				setDate(lps_ps, afvtui_arg.getFechaFinal(), li_con++);
				lps_ps.setString(li_con++, afvtui_arg.getTipoCalendario());
				lps_ps.setString(li_con++, afvtui_arg.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ll_long = lrs_rs.getLong("DIAS_VENCIMIENTO");
			}
			catch(SQLException lse_e)
			{
				logError(this, "funcionFechaVenceTerminos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ll_long;
	}

	/**
	 * Funcion fecha vence terminos.
	 *
	 * @param afvtui_arg de afvtui arg
	 * @return el valor de date
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Date funcionFechaVenceTerminos(FechaVenceTerminosUI afvtui_arg)
	    throws B2BException
	{
		Date              ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(afvtui_arg != null)
		{
			try
			{
				int li_con;
				li_con     = 1;

				lps_ps = getConnection()
						         .prepareStatement(
						    "SELECT PKG_TRANSVERSALES.FUNC_FECHA_VENCE_TERMINOS(?,?,?,?) FECHA FROM DUAL "
						);

				setDate(lps_ps, afvtui_arg.getFechaInicial(), li_con++);
				lps_ps.setString(li_con++, afvtui_arg.getTipoCalendario());
				lps_ps.setString(li_con++, afvtui_arg.getIdCirculo());
				setInteger(lps_ps, afvtui_arg.getDiasCalcularFecha(), li_con++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = lrs_rs.getDate("FECHA");
			}
			catch(SQLException lse_e)
			{
				logError(this, "funcionFechaVenceTerminos", lse_e);

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
	 * Funcion verifica propiedad.
	 *
	 * @param as_idPersona de as id persona
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String funcionVerificaPropiedad(String as_idPersona, String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		String            ls_personaValida;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_personaValida     = null;
		lps_ps               = null;
		lrs_rs               = null;

		try
		{
			int li_con;
			li_con     = 1;

			lps_ps = getConnection()
					         .prepareStatement(
					    "SELECT PKG_REGISTRO.FUNC_VERIFICA_PROPIEDAD(?,?,?) PERSONA_VALIDA FROM DUAL "
					);

			lps_ps.setString(li_con++, as_idPersona);
			lps_ps.setString(li_con++, as_idCirculo);
			setLong(lps_ps, NumericUtils.getLongWrapper(al_idMatricula), li_con++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_personaValida = lrs_rs.getString("PERSONA_VALIDA");
		}
		catch(SQLException lse_e)
		{
			logError(this, "funcionVerificaPropiedad", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_personaValida;
	}

	/**
	 * Proc reportes conciliaciones dinamico.
	 *
	 * @param as_procedimiento the as procedimiento
	 * @param as_date the as date
	 * @param as_usuario the as usuario
	 * @param as_ip the as ip
	 * @throws B2BException the b 2 B exception
	 */
	public void procReportesConciliacionesDinamico(
	    String as_procedimiento, String as_date, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		if(
		    StringUtils.isValidString(as_procedimiento) && StringUtils.isValidString(as_date)
			    && StringUtils.isValidString(as_usuario) && StringUtils.isValidString(as_ip)
		)
		{
			try
			{
				int           li_i;
				int           li_return;
				String        ls_error;
				StringBuilder lsb_sb;

				li_i          = 1;
				li_return     = 0;
				lsb_sb        = new StringBuilder(cs_PROC_REPORTES_CONCILIACIONES_DINAMICO_1);

				lsb_sb.append(as_procedimiento);
				lsb_sb.append(cs_PROC_REPORTES_CONCILIACIONES_DINAMICO_2);

				lcs_cs = getConnection().prepareCall(lsb_sb.toString());

				lcs_cs.setString(li_i++, as_date);
				lcs_cs.setString(li_i++, as_usuario);
				lcs_cs.setString(li_i++, as_ip);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(4);
				ls_error      = lcs_cs.getString(5);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento " + as_procedimiento + " :" + li_return + " :: " + ls_error
					);
			}
			catch(SQLException lse_e)
			{
				logError(this, "procReportesConciliacionesDinamico", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}
	}

	/**
	 * Query reportes diario.
	 *
	 * @param as_query the as query
	 * @param as_fechaInicial the ad fecha
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<String> queryReportes(
	    String as_query, String as_fechaInicial, String as_fechaFinal, String as_idCuenta, String as_proc
	)
	    throws B2BException
	{
		Collection<String> lcs_return;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcs_return     = new ArrayList<String>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			boolean lb_titulos;
			int     li_columnCount;

			lb_titulos         = false;
			li_columnCount     = 1;
			lps_ps             = getConnection().prepareStatement(as_query);

			lps_ps.setString(li_columnCount++, as_fechaInicial);
			lps_ps.setString(li_columnCount++, as_fechaFinal);

			if(StringUtils.isValidString(as_proc))
			{
				lps_ps.setString(li_columnCount++, as_fechaInicial);
				lps_ps.setString(li_columnCount++, as_fechaFinal);
			}

			if(StringUtils.isValidString(as_idCuenta))
				lps_ps.setString(li_columnCount++, as_idCuenta);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				ResultSetMetaData lrsmd_data;

				lrsmd_data = lrs_rs.getMetaData();

				if(lrsmd_data != null)
				{
					int           li_columnas;
					StringBuilder lsb_data;

					li_columnas     = lrsmd_data.getColumnCount();
					lsb_data        = new StringBuilder();

					if(!lb_titulos)
					{
						StringBuilder lsb_titulos;

						lsb_titulos = new StringBuilder();

						for(int li_count = 1; li_count <= li_columnas; li_count++)
						{
							lsb_titulos.append(StringUtils.getTituloQuery(lrsmd_data.getColumnLabel(li_count)));
							lsb_titulos.append(IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
						}

						lcs_return.add(lsb_titulos.toString());

						lb_titulos = true;
					}

					for(int li_count = 1; li_count <= li_columnas; li_count++)
					{
						Object lo_data;
						String ls_dato;

						lo_data     = lrs_rs.getObject(li_count);
						ls_dato     = null;

						if(lo_data != null)
							ls_dato = lo_data.toString();

						lsb_data.append(
						    StringUtils.isValidString(ls_dato) ? ls_dato : IdentificadoresCommon.ESPACIO_VACIO
						);
						lsb_data.append(IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					}

					lcs_return.add(lsb_data.toString());
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "queryReportes", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcs_return.isEmpty())
			lcs_return = null;

		return lcs_return;
	}

	/**
	 * Retorna Objeto o variable de valor object from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private TramiteTurno getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TramiteTurno lth_datos;
		lth_datos = new TramiteTurno();

		lth_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lth_datos.setFechaAsignacion(ars_rs.getDate("FECHA_ASIGNACION"));
		lth_datos.setNir(ars_rs.getString("NIR"));
		lth_datos.setMotivoNoTramite(ars_rs.getString("MOTIVO_NO_TRAMITE"));
		lth_datos.setNombre(ars_rs.getString("NOMBRE"));
		lth_datos.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lth_datos.setIdEtapa(getLong(ars_rs, "ID_ETAPA"));

		return lth_datos;
	}
}
