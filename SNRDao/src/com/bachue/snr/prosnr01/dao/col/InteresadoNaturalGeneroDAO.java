package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.col.InteresadoNaturalGenero;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos de la tabla SDB_COL_INTERESADO_NATURAL_GENERO
 * @author Nicolas Guaneme
 *
 */
public class InteresadoNaturalGeneroDAO extends BaseDAO implements IBase<InteresadoNaturalGenero>
{
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_COL_INTERESADO_NATURAL_GENERO WHERE ID_NATURAL_GENERO=?";
	private static final String cs_UPDATE     = "UPDATE SDB_COL_INTERESADO_NATURAL_GENERO SET ILICODE=?, "
		+ "DESCRIPCION=?, ACTIVO=?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION=SYSTIMESTAMP, "
		+ "IP_MODIFICACION = ? WHERE ID_NATURAL_GENERO=?";
	private static final String cs_INSERT     = "INSERT INTO SDB_COL_INTERESADO_NATURAL_GENERO(ID_NATURAL_GENERO, "
		+ "ILICODE, DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION)VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";
	private static final String cs_FIND_ALL   = "SELECT * FROM SDB_COL_INTERESADO_NATURAL_GENERO";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_COL_INTERESADO_NATURAL_GENERO
	 * @param ab_b indica si se debe traer los generos activos (true trae solo los activos, false trae todos los registros)
	 * @return
	 * @throws B2BException
	 */
	public Collection<InteresadoNaturalGenero> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<InteresadoNaturalGenero> lcing_cing;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;
		StringBuilder                       lsb_sb;

		lcing_cing     = new ArrayList<InteresadoNaturalGenero>();
		lps_ps         = null;
		lrs_rs         = null;
		lsb_sb         = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sb.append(" WHERE ACTIVO = 'S' ");

			lsb_sb.append(" ORDER BY ILICODE ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcing_cing.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcing_cing))
			lcing_cing = null;

		return lcing_cing;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan con un ID_NATURAL_GENERO especifico
	 */
	@Override
	public InteresadoNaturalGenero findById(InteresadoNaturalGenero aing_param)
	    throws B2BException
	{
		InteresadoNaturalGenero ling_ing;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		ling_ing     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, aing_param.getIdNaturalGenero());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ling_ing = getObjetFromResultSet(lrs_rs);
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

		return ling_ing;
	}

	/**
	 * Metodo para insertar o acualizar en la base de datos para la tabla SDB_COL_INTERESADO_NATURAL_GENERO
	 * @ param aing_param objeto a insertar o modificar
	 * @ param ab_query indica si se va a ainsertar o modificar en la base de datos
	 */
	@Override
	public void insertOrUpdate(InteresadoNaturalGenero aing_param, boolean ab_query)
	    throws B2BException
	{
		if(aing_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, aing_param.getIdNaturalGenero());

				lps_ps.setString(li_column++, aing_param.getIlicode());
				lps_ps.setString(li_column++, aing_param.getDescripcion());
				lps_ps.setString(li_column++, aing_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aing_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aing_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aing_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aing_param.getIpModificacion());
					lps_ps.setString(li_column++, aing_param.getIdNaturalGenero());
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
	 * Metodo para obtener la respuesta de la sentecia sql indicada
	 * @param lrs_rs  objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return
	 * @throws SQLException
	 */
	private InteresadoNaturalGenero getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		InteresadoNaturalGenero ling_ing;

		ling_ing = new InteresadoNaturalGenero();

		ling_ing.setIdNaturalGenero(lrs_rs.getString("ID_NATURAL_GENERO"));
		ling_ing.setIlicode(lrs_rs.getString("ILICODE"));
		ling_ing.setDescripcion(lrs_rs.getString("DESCRIPCION"));
		ling_ing.setActivo(lrs_rs.getString("ACTIVO"));
		ling_ing.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ling_ing.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ling_ing.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ling_ing.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ling_ing.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ling_ing.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ling_ing;
	}
}
