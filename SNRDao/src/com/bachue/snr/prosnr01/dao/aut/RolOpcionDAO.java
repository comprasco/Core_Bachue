package com.bachue.snr.prosnr01.dao.aut;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.RolOpcion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * DAO de la clase RolOpcion
 * @author Duvan Beltrán
 *
 */
public class RolOpcionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_AUT_ROL_OPCION";

	/** Constante cs_FIND_BY_ROL. */
	private static final String cs_FIND_BY_ROL = "SELECT * FROM SDB_AUT_ROL_OPCION WHERE ID_ROL=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_AUT_ROL_OPCION (ID_ROL,ID_OPCION,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)VALUES(?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_AUT_ROL_OPCION WHERE ID_ROL=? AND ID_OPCION=?";

	/** Constante cs_FIND_OPCION_BY_ID_ROL. */
	private static final String cs_FIND_OPCION_BY_ID_ROL = "SELECT O.ID_OPCION_PADRE,O.ID_OPCION,RO.ACTIVO,"
		+ "O.DESCRIPCION,RO.FECHA_DESDE,RO.FECHA_HASTA,O.OPCION,O.TIPO,O.URL FROM SDB_AUT_ROL_OPCION RO INNER JOIN "
		+ "SDB_AUT_OPCION O ON(RO.ID_ROL=? AND O.ID_OPCION=RO.ID_OPCION AND NVL(O.ACTIVO,'S')='S')ORDER BY "
		+ "NVL(O.ID_OPCION_PADRE,-1),O.ID_OPCION";

	/** Constante cs_FIND_BY_ID_ROL. */
	private static final String cs_FIND_BY_ID_ROL = "SELECT RO.*, OPC.OPCION FROM SDB_AUT_ROL_OPCION RO INNER JOIN SDB_AUT_OPCION OPC ON RO.ID_OPCION = OPC.ID_OPCION WHERE RO.ID_ROL = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_AUT_ROL_OPCION SET FECHA_DESDE=?, FECHA_HASTA=?, ACTIVO=?, IP_MODIFICACION=?, ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP WHERE ID_ROL=? AND ID_OPCION=?";

	/** Constante cs_UPDATE_ACTIVO. */
	private static final String cs_UPDATE_ACTIVO = "UPDATE SDB_AUT_ROL_OPCION SET ACTIVO='S', IP_MODIFICACION=?, ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP WHERE ID_ROL=? AND ID_OPCION=?";

	/** Constante cs_UPDATE_INACTIVO. */
	private static final String cs_UPDATE_INACTIVO = "UPDATE SDB_AUT_ROL_OPCION SET ACTIVO='N', IP_MODIFICACION=?, ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP WHERE ID_ROL=?";

	/**
	 * Método para consultar todos los registro de la tabla SDB_AUT_ROL_OPCION
	 *
	 * @return Collection<RolOpcion> Colección de RolOpcion que contiene los criterios de búsqueda
	 * @throws B2BException
	 */
	public Collection<RolOpcion> findAll()
	    throws B2BException
	{
		Collection<RolOpcion> lcro_datos;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcro_datos     = new ArrayList<RolOpcion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcro_datos.add(getRolOpcion(lrs_rs));
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
	 * Método para encontrar los datos de la tabla SDB_AUT_ROL_OPCION por medio del rol
	 * @param as_idRol String contenedor del filtro de busqueda
	 * @return
	 * @throws B2BException
	 */
	public Collection<RolOpcion> findByIdRol(String as_idRol)
	    throws B2BException
	{
		Collection<RolOpcion> lcro_rolOpcion;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcro_rolOpcion     = new ArrayList<RolOpcion>();
		lps_ps             = null;
		lrs_rs             = null;

		try
		{
			if(StringUtils.isValidString(as_idRol))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ROL);

				lps_ps.setString(1, as_idRol);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcro_rolOpcion.add(getRolOpcion(lrs_rs, true));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdRol", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcro_rolOpcion;
	}

	/**
	 * Método para consultar todos los registro de la tabla SDB_AUT_ROL_OPCION por medio del ID_ROL
	 *
	 * @param as_idRol
	 * @return Collection<RolOpcion> Colección de RolOpcion que contiene los criterios de búsqueda
	 * @throws B2BException
	 */
	public Collection<RolOpcion> findByRol(String as_s)
	    throws B2BException
	{
		Collection<RolOpcion> lcro_datos;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcro_datos     = new ArrayList<RolOpcion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ROL);

			lps_ps.setString(1, as_s);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcro_datos.add(getRolOpcion(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByRol", lse_e);

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
	 * Find rol opcion by id rol.
	 *
	 * @param as_idRol de as id rol
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Opcion> findOpcionByIdRol(String as_idRol)
	    throws B2BException
	{
		Collection<Opcion> lo_opcion;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lo_opcion     = new ArrayList<Opcion>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(StringUtils.isValidString(as_idRol))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_OPCION_BY_ID_ROL);

				lps_ps.setString(1, as_idRol);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lo_opcion.add(getOpcion(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findOpcionByIdRol", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lo_opcion;
	}

	/**
	 * Método para encontrar los datos de la tabla SDB_AUT_ROL_OPCION por medio de su llave primaria
	 * @param as_idRol
	 * @param as_idOpcion
	 * @return
	 * @throws B2BException
	 */
	public RolOpcion findRolOpcionById(String as_idRol, String as_idOpcion)
	    throws B2BException
	{
		RolOpcion         lro_rolOpcion;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lro_rolOpcion     = null;
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			if(StringUtils.isValidString(as_idRol) && StringUtils.isValidString(as_idOpcion))
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idRol);
				lps_ps.setString(li_contador++, as_idOpcion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lro_rolOpcion = getRolOpcion(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findRolOpcionById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lro_rolOpcion;
	}

	/**
	 * Método para inserción de datos en la tabla SDB_AUT_ROL_OPCION
	 * @param aro_param
	 * @throws B2BException
	 */
	public void insert(RolOpcion aro_param)
	    throws B2BException
	{
		if(aro_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, aro_param.getIdRol());
				lps_ps.setString(li_column++, aro_param.getIdOpcion());

				lps_ps.setDate(li_column++, DateUtils.getSQLDate(aro_param.getFechaDesde()));
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(aro_param.getFechaHasta()));

				lps_ps.setString(li_column++, aro_param.getActivo());

				lps_ps.setString(li_column++, aro_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aro_param.getIpCreacion());

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
			}
		}
	}

	/**
	 * Método para modificar datos de la tabla SDB_AUT_ROL_OPCION
	 * @param aro_param
	 * @throws B2BException
	 */
	public void update(RolOpcion aro_param)
	    throws B2BException
	{
		if(aro_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setDate(li_column++, DateUtils.getSQLDate(aro_param.getFechaDesde()));
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(aro_param.getFechaHasta()));
				lps_ps.setString(li_column++, aro_param.getActivo());

				lps_ps.setString(li_column++, aro_param.getIpModificacion());
				lps_ps.setString(li_column++, aro_param.getIdUsuarioModificacion());

				lps_ps.setString(li_column++, aro_param.getIdRol());
				lps_ps.setString(li_column++, aro_param.getIdOpcion());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para modificar el campo ACTIVO de la tabla SDB_AUT_ROL_OPCION
	 * @param aro_param
	 * @throws B2BException
	 */
	public void updateActivo(RolOpcion aro_param)
	    throws B2BException
	{
		if(aro_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ACTIVO);

				lps_ps.setString(li_column++, aro_param.getIpModificacion());
				lps_ps.setString(li_column++, aro_param.getIdUsuarioModificacion());

				lps_ps.setString(li_column++, aro_param.getIdRol());
				lps_ps.setString(li_column++, aro_param.getIdOpcion());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para modificar el campo ACTIVO de la tabla SDB_AUT_ROL_OPCION
	 * @param aro_param
	 * @throws B2BException
	 */
	public void updateInactivo(RolOpcion aro_param)
	    throws B2BException
	{
		if(aro_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_INACTIVO);

				lps_ps.setString(li_column++, aro_param.getIpModificacion());
				lps_ps.setString(li_column++, aro_param.getIdUsuarioModificacion());

				lps_ps.setString(li_column++, aro_param.getIdRol());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateInactivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para obtener un objeto de Rol Opcion
	 * @param ars_rs un objeto que representa un resultado de una sentencia sql de la base de datos
	 * @param ab_b
	 * @return
	 * @throws SQLException
	 */
	private Opcion getOpcion(ResultSet ars_rs)
	    throws SQLException
	{
		Opcion lo_o;
		lo_o = new Opcion();

		lo_o.setActivo(ars_rs.getString("ACTIVO"));
		lo_o.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lo_o.setFechaDesde(ars_rs.getDate("FECHA_DESDE"));
		lo_o.setFechaHasta(ars_rs.getDate("FECHA_HASTA"));
		lo_o.setIdOpcion(ars_rs.getString("ID_OPCION"));
		lo_o.setOpcion(ars_rs.getString("OPCION"));
		lo_o.setIdOpcionPadre(ars_rs.getString("ID_OPCION_PADRE"));
		lo_o.setTipo(ars_rs.getString("TIPO"));
		lo_o.setUrl(ars_rs.getString("URL"));

		return lo_o;
	}

	/**
	 * Método para obtener un objeto de Rol Opcion
	 * @param ars_rs un objeto que representa un resultado de una sentencia sql de la base de datos
	 * @param ab_b
	 * @return
	 * @throws SQLException
	 */
	private RolOpcion getRolOpcion(ResultSet ars_rs)
	    throws SQLException
	{
		return getRolOpcion(ars_rs, false);
	}

	/**
	 * Método para obtener un objeto de Rol Opcion
	 * @param ars_rs un objeto que representa un resultado de una sentencia sql de la base de datos
	 * @param ab_b
	 * @return
	 * @throws SQLException
	 */
	private RolOpcion getRolOpcion(ResultSet ars_rs, boolean ab_opcion)
	    throws SQLException
	{
		RolOpcion lrp_c;
		lrp_c = new RolOpcion();

		lrp_c.setIdRol(ars_rs.getString("ID_ROL"));
		lrp_c.setIdOpcion(ars_rs.getString("ID_OPCION"));
		lrp_c.setFechaDesde(ars_rs.getDate("FECHA_DESDE"));
		lrp_c.setFechaHasta(ars_rs.getDate("FECHA_HASTA"));
		lrp_c.setActivo(ars_rs.getString("ACTIVO"));

		if(ab_opcion)
			lrp_c.setOpcion(ars_rs.getString("OPCION"));

		fillAuditoria(ars_rs, lrp_c);

		return lrp_c;
	}
}
