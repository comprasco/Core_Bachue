package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.png.Coordenada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PNG_COORDENADA
 *
 * @author Carlos Calderón
 *
 */
public class CoordenadaDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<Coordenada>
{
	private static final String cs_FIND_BY_ID      = "SELECT ID_COORDENADA, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PNG_COORDENADA WHERE ID_COORDENADA = ?";
	private static final String cs_FIND_ALL        = "SELECT ID_COORDENADA, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PNG_COORDENADA ORDER BY NOMBRE ASC";
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_COORDENADA, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PNG_COORDENADA WHERE ACTIVO = 'S' ORDER BY NOMBRE ASC";
	private static final String cs_UPDATE          = "UPDATE SDB_PNG_COORDENADA SET NOMBRE = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ " WHERE ID_COORDENADA = ?";
	private static final String cs_INSERT          = "INSERT INTO SDB_PNG_COORDENADA (ID_COORDENADA,NOMBRE,ACTIVO,"
		+ " ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP)";

	/**
	 * Consulta todos los registros de la tabla
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException
	 */
	public Collection<Coordenada> findAll()
	    throws B2BException
	{
		Collection<Coordenada> lcc_cc;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcc_cc     = new ArrayList<Coordenada>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcc_cc.add(getCoordenada(lrs_rs));
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

		if(lcc_cc.isEmpty())
			lcc_cc = null;

		return lcc_cc;
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException
	 */
	public Collection<Coordenada> findAllActivo()
	    throws B2BException
	{
		Collection<Coordenada> lcc_cc;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcc_cc     = new ArrayList<Coordenada>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcc_cc.add(getCoordenada(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcc_cc.isEmpty())
			lcc_cc = null;

		return lcc_cc;
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException
	 */
	public Map<String, String> findAllActivoMap()
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
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				Coordenada lc_coordenada;

				lc_coordenada = getCoordenada(lrs_rs);

				if(lc_coordenada != null)
					lmss_return.put(lc_coordenada.getIdCoordenada(), lc_coordenada.getNombre());
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivoMap", lse_e);

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
	 * Consulta todos los registros relacionados con un id de tipo eje
	 *
	 * @param att_parametros Objeto contenedor del id tipo eje que será utilizado
	 * como filtro en la consulta
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException
	 */
	public Coordenada findById(Coordenada ac_parametros)
	    throws B2BException
	{
		return (ac_parametros != null) ? findById(ac_parametros.getIdCoordenada()) : null;
	}

	/**
	 * Consulta todos los registros relacionados con un id de tipo eje
	 *
	 * @param as_idCoordenada Objeto contenedor del id tipo eje que será utilizado
	 * como filtro en la consulta
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException
	 */
	public Coordenada findById(String as_idCoordenada)
	    throws B2BException
	{
		Coordenada lc_c;

		lc_c = null;

		if(StringUtils.isValidString(as_idCoordenada))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idCoordenada);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lc_c = getCoordenada(lrs_rs);
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

		return lc_c;
	}

	/**
	 * Calcula la secuencia de un nuevo registro e inserta el registro en la tabla, o actualiza un registro ya
	 * existente
	 *
	 * @param att_parametros Objeto contenedor de la información que se pretende insertar o actualizar
	 * @param ab_query Indicador de si se va a insertar o actualizar; true para insertar, false para actualizar
	 * @throws B2BException
	 */
	@Override
	public void insertOrUpdate(Coordenada ac_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ac_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, ac_parametros.getIdCoordenada());

				lps_ps.setString(li_column++, ac_parametros.getNombre());
				lps_ps.setString(li_column++, ac_parametros.getActivo());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIdCoordenada());
				}
				else
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
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
	 * Extrae la información de un registro de base de datos, la asigna a un objeto y la retorna
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Objeto tipo eje con la información recuperada de la base de datos
	 * @throws SQLException
	 */
	private Coordenada getCoordenada(ResultSet ars_rs)
	    throws SQLException
	{
		Coordenada lc_coordenada;

		lc_coordenada = new Coordenada();

		lc_coordenada.setIdCoordenada(ars_rs.getString("ID_COORDENADA"));
		lc_coordenada.setNombre(ars_rs.getString("NOMBRE"));
		lc_coordenada.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lc_coordenada.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lc_coordenada.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lc_coordenada.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lc_coordenada.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lc_coordenada.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lc_coordenada.setActivo(ars_rs.getString("ACTIVO"));

		return lc_coordenada;
	}
}
