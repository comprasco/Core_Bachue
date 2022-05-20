package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.png.LetraEje;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PNG_LETRA_EJE.
 *
 * @author Carlos Calderón
 */
public class LetraEjeDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<LetraEje>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_LETRA, LETRA, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PNG_LETRA_EJE WHERE ID_LETRA = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_LETRA, LETRA, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PNG_LETRA_EJE ORDER BY LETRA ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_LETRA, LETRA, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PNG_LETRA_EJE WHERE ACTIVO = 'S' ORDER BY LETRA ASC";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_LETRA_EJE SET LETRA = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ " WHERE ID_LETRA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_LETRA_EJE (ID_LETRA,LETRA,ACTIVO,"
		+ " ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP)";

	/**
	 * Consulta todos los registros de la tabla.
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<LetraEje> findAll()
	    throws B2BException
	{
		Collection<LetraEje> lcle_cte;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcle_cte     = new ArrayList<LetraEje>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcle_cte.add(getLetraEje(lrs_rs));
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

		if(lcle_cte.isEmpty())
			lcle_cte = null;

		return lcle_cte;
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo.
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<LetraEje> findAllActivo()
	    throws B2BException
	{
		Collection<LetraEje> lcle_cle;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcle_cle     = new ArrayList<LetraEje>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcle_cle.add(getLetraEje(lrs_rs));
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

		if(lcle_cle.isEmpty())
			lcle_cle = null;

		return lcle_cle;
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo.
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
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
				LetraEje lle_letraEje;

				lle_letraEje = getLetraEje(lrs_rs);

				if(lle_letraEje != null)
					lmss_return.put(lle_letraEje.getIdLetra(), lle_letraEje.getLetra());
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
	 * Consulta todos los registros relacionados con un id de tipo eje.
	 *
	 * @param ale_parametros de ale parametros
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LetraEje findById(LetraEje ale_parametros)
	    throws B2BException
	{
		return (ale_parametros != null) ? findById(ale_parametros.getIdLetra()) : null;
	}

	/**
	 * Consulta todos los registros relacionados con un id de tipo eje.
	 *
	 * @param as_idLetra Objeto contenedor del id tipo eje que será utilizado
	 * como filtro en la consulta
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LetraEje findById(String as_idLetra)
	    throws B2BException
	{
		LetraEje lle_le;

		lle_le = null;

		if(StringUtils.isValidString(as_idLetra))
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

				lps_ps.setString(li_contador++, as_idLetra);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lle_le = getLetraEje(lrs_rs);
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

		return lle_le;
	}

	/**
	 * Calcula la secuencia de un nuevo registro e inserta el registro en la tabla, o actualiza un registro ya
	 * existente.
	 *
	 * @param ale_parametros de ale parametros
	 * @param ab_query Indicador de si se va a insertar o actualizar; true para insertar, false para actualizar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public void insertOrUpdate(LetraEje ale_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ale_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(!ab_query)
				{
					lps_ps.setString(li_column++, ale_parametros.getLetra());
					lps_ps.setString(li_column++, ale_parametros.getActivo());
					lps_ps.setString(li_column++, ale_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ale_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ale_parametros.getIdLetra());
				}
				else
				{
					lps_ps.setString(li_column++, ale_parametros.getIdLetra());
					lps_ps.setString(li_column++, ale_parametros.getLetra());
					lps_ps.setString(li_column++, ale_parametros.getActivo());
					lps_ps.setString(li_column++, ale_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ale_parametros.getIpCreacion());
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
	 * Extrae la información de un registro de base de datos, la asigna a un objeto y la retorna.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Objeto tipo eje con la información recuperada de la base de datos
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private LetraEje getLetraEje(ResultSet ars_rs)
	    throws SQLException
	{
		LetraEje lle_letraEje;

		lle_letraEje = new LetraEje();

		lle_letraEje.setIdLetra(ars_rs.getString("ID_LETRA"));
		lle_letraEje.setLetra(ars_rs.getString("LETRA"));
		lle_letraEje.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lle_letraEje.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lle_letraEje.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lle_letraEje.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lle_letraEje.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lle_letraEje.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lle_letraEje.setActivo(ars_rs.getString("ACTIVO"));

		return lle_letraEje;
	}
}
