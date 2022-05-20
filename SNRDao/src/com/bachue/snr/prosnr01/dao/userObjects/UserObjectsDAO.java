package com.bachue.snr.prosnr01.dao.userObjects;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.objectDataBase.UserObjects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase que contiene todos las consultas de tipo User Objects
 * @author dbeltran
 *
 */
public class UserObjectsDAO extends AuditoriaDao
{
	/**Constante Nombre Tablas*/
	private static final String cs_NOMBRESTABLAS = "select UO.OBJECT_NAME from USER_OBJECTS UO where UO.OBJECT_TYPE='TABLE' and UO.OBJECT_NAME like 'SDB_%' order by UO.OBJECT_NAME";

	/**Constante Nombre Columnas*/
	private static final String cs_NOMBRECOLUMNAS = "SELECT COLUMN_NAME from USER_TAB_COLUMNS where TABLE_NAME =? ORDER BY COLUMN_NAME";

	/**
	 * Método para encontrar todas las columnas asociadas a las tablas
	 * @return
	 * @throws B2BException
	 */
	public Collection<UserObjects> findAllNombresColumnas(String as_nombreTabla)
	    throws B2BException
	{
		Collection<UserObjects> lcro_datos;

		lcro_datos = null;

		if(StringUtils.isValidString(as_nombreTabla))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_NOMBRECOLUMNAS);
				lps_ps.setString(1, as_nombreTabla);

				lrs_rs         = lps_ps.executeQuery();
				lcro_datos     = new ArrayList<UserObjects>();

				while(lrs_rs.next())
					lcro_datos.add(getColumns(lrs_rs));
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
		}

		return lcro_datos;
	}

	/**
	 * Método para encontrar todas las tablas
	 * @return una collection de UserObjects
	 * @throws B2BException
	 */
	public Collection<UserObjects> findAllNombresTablas()
	    throws B2BException
	{
		Collection<UserObjects> lcro_datos;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcro_datos     = new ArrayList<UserObjects>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_NOMBRESTABLAS);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcro_datos.add(getUserObjects(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcro_datos))
			lcro_datos = null;

		return lcro_datos;
	}

	/**
	 * Método de obtención de User Objects
	 * @param ars_rs
	 * @return
	 * @throws SQLException
	 */
	private UserObjects getUserObjects(ResultSet ars_rs)
	    throws SQLException
	{
		UserObjects luo_c;
		luo_c = new UserObjects();

		luo_c.setObjectName(ars_rs.getString("OBJECT_NAME"));

		return luo_c;
	}

	/***
	 * Métodod e obtención de las columnas de la base de datos
	 * @param ars_rs de tipo resulset con el parametro de busqueda
	 * @return de tipo user Objects con el resultado
	 * @throws SQLException
	 */
	private UserObjects getColumns(ResultSet ars_rs)
	    throws SQLException
	{
		UserObjects luo_c;
		luo_c = new UserObjects();

		luo_c.setObjectName(ars_rs.getString("COLUMN_NAME"));

		return luo_c;
	}
}
