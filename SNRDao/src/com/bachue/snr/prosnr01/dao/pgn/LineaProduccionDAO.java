package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_LINEA_PRODUCCION
 *
 * @author jpatino
 */
public class LineaProduccionDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_LINEA_PRODUCCION WHERE ID_LINEA_PRODUCCION = ?";

	/** Constante cs_FIND_BY_ID_ETAPA. */
	private static final String cs_FIND_BY_ID_ETAPA = "SELECT * FROM SDB_PGN_LINEA_PRODUCCION WHERE ID_ETAPA = ?";

	/** Constante cs_FIND_BY_ID_NOMENCLATURA. */
	private static final String cs_FIND_BY_ID_NOMENCLATURA = "SELECT * FROM SDB_PGN_LINEA_PRODUCCION WHERE NOMENCLATURA = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_LINEA_PRODUCCION ORDER BY LENGTH(ID_LINEA_PRODUCCION), ID_LINEA_PRODUCCION";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_LINEA_PRODUCCION_ID_LINEA_PRODUCCION.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_LINEA_PRODUCCION (ID_LINEA_PRODUCCION,"
		+ "NOMBRE,PESO,EQUIVALENCIA_TURNO,ID_ETAPA,NOMENCLATURA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES "
		+ " (?,?,?,?,?,?,?,SYSTIMESTAMP,?) ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_LINEA_PRODUCCION SET "
		+ "NOMBRE = ?,PESO = ?,EQUIVALENCIA_TURNO = ?,ID_ETAPA = ?,NOMENCLATURA=?,ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ?"
		+ " WHERE ID_LINEA_PRODUCCION = ?";

	/**
	 * Instancia un nuevo objeto linea produccion DAO.
	 */
	public LineaProduccionDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de LineaProduccion con todos los registros
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LineaProduccion
	 */
	public Collection<LineaProduccion> findAll()
	    throws B2BException
	{
		Collection<LineaProduccion> lp_pais;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lp_pais     = new ArrayList<LineaProduccion>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_pais.add(getLineaProduccion(lrs_rs));
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

		if(lp_pais.isEmpty())
			lp_pais = null;

		return lp_pais;
	}

	/**
	 * Retorna el valor del objeto de tipo LineaProduccion
	 *
	 * @param alp_parametros correspondiente al valor del tipo de objeto LineaProduccion
	 * @return devuelve el valor del objeto linea produccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LineaProduccion
	 */
	public LineaProduccion findById(LineaProduccion alp_parametros)
	    throws B2BException
	{
		LineaProduccion   lp_pais;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lp_pais     = null;
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setObject(li_contador++, alp_parametros.getIdLineaProduccion());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lp_pais = getLineaProduccion(lrs_rs);
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

		return lp_pais;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de LineaProduccion con todos los registros para una etapa.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LineaProduccion
	 */
	public Collection<LineaProduccion> findByIdEtapa(long ai_etapa)
	    throws B2BException
	{
		Collection<LineaProduccion> lp_pais;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lp_pais     = new ArrayList<LineaProduccion>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ETAPA);

			lps_ps.setLong(1, ai_etapa);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_pais.add(getLineaProduccion(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdEtapa", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lp_pais.isEmpty())
			lp_pais = null;

		return lp_pais;
	}

	/**
	 * Retorna el valor del objeto de tipo LineaProduccion con el registro para una nomenclatura.
	 *
	 * @return devuelve el valor del objeto LineaProduccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LineaProduccion
	 */
	public LineaProduccion findByNomenclatura(String as_nomenclatura)
	    throws B2BException
	{
		LineaProduccion   lp_pais;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lp_pais     = null;
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_NOMENCLATURA);

			lps_ps.setString(1, as_nomenclatura);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lp_pais = getLineaProduccion(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByNomenclatura", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lp_pais;
	}

	/**
	 * Inserta o actualiza el registro en la tabla
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto LineaProduccion
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public LineaProduccion insertOrUpdate(LineaProduccion ac_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ac_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ac_parametros.setIdLineaProduccion(((BigDecimal)lo_o).toString());
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}

					lps_ps.setString(li_column++, ac_parametros.getIdLineaProduccion());
				}

				lps_ps.setString(li_column++, ac_parametros.getNombre());
				lps_ps.setLong(li_column++, ac_parametros.getPeso());
				lps_ps.setLong(li_column++, ac_parametros.getEquivalenciaTurno());
				setLong(lps_ps, ac_parametros.getIdEtapa(), li_column++);
				lps_ps.setString(li_column++, ac_parametros.getNomenclatura());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIdLineaProduccion());
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

				if(ab_query)
				{
					close(lps_secuencia);
					close(lrs_rs);
				}
			}
		}

		return ac_parametros;
	}

	/**
	 * Retorna el valor de linea produccion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de linea produccion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private LineaProduccion getLineaProduccion(ResultSet ars_rs)
	    throws SQLException
	{
		LineaProduccion llp_datos;

		llp_datos = new LineaProduccion();

		llp_datos.setIdLineaProduccion(ars_rs.getString("ID_LINEA_PRODUCCION"));
		llp_datos.setNombre(ars_rs.getString("NOMBRE"));
		llp_datos.setPeso(ars_rs.getLong("PESO"));
		llp_datos.setEquivalenciaTurno(ars_rs.getLong("EQUIVALENCIA_TURNO"));
		llp_datos.setNomenclatura(ars_rs.getString("NOMENCLATURA"));
		llp_datos.setIdEtapa(getLong(ars_rs, "ID_ETAPA"));
		llp_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		llp_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		llp_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		llp_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		llp_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		llp_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));

		return llp_datos;
	}
}
