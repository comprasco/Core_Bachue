package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PNG_TIPO_EJE.
 *
 * @author Heiner Castañeda
 */
public class TipoEjeDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<TipoEje>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TIPO_EJE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO, TIPO_PREDIO, COMPLEMENTO FROM SDB_PNG_TIPO_EJE WHERE ID_TIPO_EJE = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_TIPO_EJE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO, TIPO_PREDIO, COMPLEMENTO FROM SDB_PNG_TIPO_EJE ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_TIPO_EJE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO, TIPO_PREDIO, COMPLEMENTO FROM SDB_PNG_TIPO_EJE WHERE ACTIVO = 'S' ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL_ACTIVO_MIXTO. */
	private static final String cs_FIND_ALL_ACTIVO_MIXTO = "SELECT ID_TIPO_EJE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO, TIPO_PREDIO, COMPLEMENTO FROM SDB_PNG_TIPO_EJE WHERE ACTIVO = 'S' AND TIPO_PREDIO = 'M' ";

	/** Constante cs_FIND_ALL_ACTIVO_BY_TIPO_PREDIO. */
	private static final String cs_FIND_ALL_ACTIVO_BY_TIPO_PREDIO = "SELECT ID_TIPO_EJE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO, TIPO_PREDIO, COMPLEMENTO FROM SDB_PNG_TIPO_EJE WHERE ACTIVO = 'S' AND TIPO_PREDIO IN ('M',?) ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_TIPO_EJE SET NOMBRE = ?, ACTIVO = ?, TIPO_PREDIO = ?, COMPLEMENTO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ " WHERE ID_TIPO_EJE = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_TIPO_EJE (ID_TIPO_EJE,NOMBRE,ACTIVO,TIPO_PREDIO,COMPLEMENTO,"
		+ " ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION) VALUES (?,?,?,?,?,?,?,SYSTIMESTAMP)";

	/**
	 * Consulta todos los registros de la tabla.
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEje> findAll()
	    throws B2BException
	{
		Collection<TipoEje> lcte_cte;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcte_cte     = new ArrayList<TipoEje>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcte_cte.add(getTipoEje(lrs_rs));
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

		if(lcte_cte.isEmpty())
			lcte_cte = null;

		return lcte_cte;
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo.
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEje> findAllActivo()
	    throws B2BException
	{
		Collection<TipoEje> lcte_cte;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcte_cte     = new ArrayList<TipoEje>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcte_cte.add(getTipoEje(lrs_rs));
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

		if(lcte_cte.isEmpty())
			lcte_cte = null;

		return lcte_cte;
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
		Map<String, String> lcte_cte;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcte_cte     = new HashMap<String, String>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				TipoEje lte_tipoEje;

				lte_tipoEje = getTipoEje(lrs_rs);

				if(lte_tipoEje != null)
					lcte_cte.put(lte_tipoEje.getIdTipoEje(), lte_tipoEje.getNombre());
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

		if(lcte_cte.isEmpty())
			lcte_cte = null;

		return lcte_cte;
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo y sean de tipo predio Mixto.
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEje> findAllActivoMixto()
	    throws B2BException
	{
		return findAllActivoMixto(false);
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo y sean de tipo predio Mixto.
	 *
	 * @param ab_complemento Variable que valida si el eje es complemento
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEje> findAllActivoMixto(boolean ab_complemento)
	    throws B2BException
	{
		Collection<TipoEje> lcte_cte;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcte_cte     = new ArrayList<TipoEje>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append(cs_FIND_ALL_ACTIVO_MIXTO);

			if(ab_complemento)
				lsb_sb.append(" AND COMPLEMENTO = 'S' ");

			lsb_sb.append(" ORDER BY NOMBRE ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcte_cte.add(getTipoEje(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivoMixto", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcte_cte.isEmpty())
			lcte_cte = null;

		return lcte_cte;
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo, tengan complemento en S, sean de tipo predio Mixto y tipo predio definido por el usuario.
	 *
	 * @param as_idTipoPredio de as id tipo predio
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEje> findAllByTipoPredio(String as_idTipoPredio)
	    throws B2BException
	{
		return findAllByTipoPredio(as_idTipoPredio, false);
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo, tengan complemento en S, sean de tipo predio Mixto y tipo predio definido por el usuario.
	 *
	 * @param as_idTipoPredio as_idTipoPredio Variable de tipo String que contiene el id tipo predio.
	 * @param ab_complemento Variable que valida si el eje es complemento
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEje> findAllByTipoPredio(String as_idTipoPredio, boolean ab_complemento)
	    throws B2BException
	{
		Collection<TipoEje> lcte_cte;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcte_cte     = new ArrayList<TipoEje>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int           li_column;
			StringBuilder lsb_sb;

			li_column     = 1;
			lsb_sb        = new StringBuilder();

			lsb_sb.append(cs_FIND_ALL_ACTIVO_BY_TIPO_PREDIO);

			if(ab_complemento)
				lsb_sb.append(" AND COMPLEMENTO = 'S' ");

			lsb_sb.append(" ORDER BY NOMBRE ASC ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_column++, as_idTipoPredio);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcte_cte.add(getTipoEje(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByTipoPredio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcte_cte.isEmpty())
			lcte_cte = null;

		return lcte_cte;
	}

	/**
	 * Consulta todos los registros relacionados con un id de tipo eje.
	 *
	 * @param ate_parametros de ate parametros
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoEje findById(TipoEje ate_parametros)
	    throws B2BException
	{
		return (ate_parametros != null) ? findById(ate_parametros.getIdTipoEje()) : null;
	}

	/**
	 * Consulta todos los registros relacionados con un id de tipo eje.
	 *
	 * @param as_idTipoEje Objeto contenedor del id tipo eje que será utilizado
	 * como filtro en la consulta
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoEje findById(String as_idTipoEje)
	    throws B2BException
	{
		TipoEje lte_te;

		lte_te = null;

		if(StringUtils.isValidString(as_idTipoEje))
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

				lps_ps.setString(li_contador++, as_idTipoEje);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lte_te = getTipoEje(lrs_rs);
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

		return lte_te;
	}

	/**
	 * Calcula la secuencia de un nuevo registro e inserta el registro en la tabla, o actualiza un registro ya
	 * existente.
	 *
	 * @param ate_parametros de ate parametros
	 * @param ab_query Indicador de si se va a insertar o actualizar; true para insertar, false para actualizar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public void insertOrUpdate(TipoEje ate_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ate_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, ate_parametros.getIdTipoEje());

				lps_ps.setString(li_column++, ate_parametros.getNombre());
				lps_ps.setString(li_column++, ate_parametros.getActivo());
				lps_ps.setString(li_column++, ate_parametros.getTipoPredio());
				lps_ps.setString(li_column++, ate_parametros.getComplementoString());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, ate_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ate_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ate_parametros.getIdTipoEje());
				}
				else
				{
					lps_ps.setString(li_column++, ate_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ate_parametros.getIpCreacion());
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
	private TipoEje getTipoEje(ResultSet ars_rs)
	    throws SQLException
	{
		TipoEje lte_tipoEje;
		String  ls_complemento;

		lte_tipoEje        = new TipoEje();
		ls_complemento     = ars_rs.getString("COMPLEMENTO");

		lte_tipoEje.setIdTipoEje(ars_rs.getString("ID_TIPO_EJE"));
		lte_tipoEje.setNombre(ars_rs.getString("NOMBRE"));
		lte_tipoEje.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lte_tipoEje.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lte_tipoEje.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lte_tipoEje.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lte_tipoEje.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lte_tipoEje.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lte_tipoEje.setActivo(ars_rs.getString("ACTIVO"));
		lte_tipoEje.setTipoPredio(ars_rs.getString("TIPO_PREDIO"));
		lte_tipoEje.setComplementoString(ars_rs.getString("COMPLEMENTO"));

		if(StringUtils.isValidString(ls_complemento) && ls_complemento.equalsIgnoreCase(EstadoCommon.S))
			lte_tipoEje.setComplemento(true);

		return lte_tipoEje;
	}
}
