package com.bachue.snr.prosnr01.dao.aut;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_AUT_USUARIO_ROL.
 *
 * @author Julian Vaca
 */
public class RolDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_ROL,NOMBRE,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,IP_MODIFICACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,ID_COMPONENTE "
		+ "FROM SDB_AUT_ROL WHERE ID_ROL=? ";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_ROL,NOMBRE,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,IP_MODIFICACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,ID_COMPONENTE "
		+ "FROM SDB_AUT_ROL ";

	/** Constante cs_FIND_ALL_RECURSOS. */
	private static final String cs_FIND_ALL_RECURSOS = "SELECT * FROM SDB_AUT_ROL WHERE ID_ROL = '30' OR ID_ROL = '199' ORDER BY NOMBRE ASC ";

	/** Constante cs_FIND_ALL_SEGUNDA_INSTANCIA. */
	private static final String cs_FIND_ALL_SEGUNDA_INSTANCIA = "SELECT * FROM SDB_AUT_ROL WHERE ID_ROL = '309' OR ID_ROL = '244' ORDER BY NOMBRE ASC ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_AUT_ROL_ID_ROL.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_AUT_ROL (ID_ROL,NOMBRE,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_COMPONENTE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_AUT_ROL SET NOMBRE = ?,FECHA_DESDE = ?,FECHA_HASTA = ?,ACTIVO = ?, ID_COMPONENTE=?, IP_MODIFICACION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_ROL = ?";

	/** Constante cs_FIND_ALL_BY_COMPONENTE. */
	private static final String cs_FIND_ALL_BY_COMPONENTE = "SELECT ID_ROL,NOMBRE,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,IP_MODIFICACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,ID_COMPONENTE FROM SDB_AUT_ROL WHERE ID_COMPONENTE = ? ";

	/** Constante cs_FIND_USUARIO_ROL. */
	private static final String cs_FIND_USUARIO_ROL = "SELECT R.ID_ROL, R.NOMBRE FROM  SDB_AUT_USUARIO U INNER JOIN SDB_AUT_USUARIO_ROL UR ON UR.ID_USUARIO = U.ID_USUARIO AND NVL(UR.ACTIVO,'S') = 'S' INNER JOIN SDB_AUT_ROL R ON R.ID_ROL = UR.ID_ROL AND NVL(R.ACTIVO,'S') = 'S' WHERE U.ID_USUARIO = ? AND R.ID_COMPONENTE= ? AND NVL(U.ACTIVO,'S') = 'S' ";

	/** Constante cs_FIND_ROL_COMPONENTE. */
	private static final String cs_FIND_ROL_COMPONENTE = "SELECT C.* FROM  SDB_AUT_ROL R INNER JOIN SDB_AUT_COMPONENTE C ON (C.ID_COMPONENTE = R.ID_COMPONENTE) WHERE R.ID_ROL = ?";

	/** Constante cs_FIND_USUARIO_ROL_ORIP. */
	private static final String cs_FIND_USUARIO_ROL_ORIP = "SELECT R.ID_ROL, R.NOMBRE " + "FROM SDB_AUT_ROL R "
		+ "INNER JOIN SDB_AUT_USUARIO_ROL UR ON UR.ID_ROL = R.ID_ROL AND NVL(UR.ACTIVO,'S') = 'S' "
		+ "INNER JOIN SDB_AUT_USUARIO U ON UR.ID_USUARIO = U.ID_USUARIO AND NVL(U.ACTIVO,'S') = 'S' "
		+ "INNER JOIN SDB_AUT_USUARIO_CIRCULO UC ON UC.ID_USUARIO = U.ID_USUARIO AND NVL(UC.ACTIVO,'S') = 'S' "
		+ "INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON CR.ID_CIRCULO = UC.ID_CIRCULO AND NVL(CR.ACTIVO,'S') = 'S' "
		+ "WHERE NVL(R.ACTIVO,'S') = 'S' AND CR.ID_CIRCULO = ? AND R.ID_ROL = ?";

	/** Constante cs_FIND_ID_COMPONENTE. */
	private static final String cs_FIND_ID_COMPONENTE = "SELECT DISTINCT(ID_COMPONENTE)AS ID_COMPONENTE FROM  SDB_AUT_ROL where ID_COMPONENTE  = ?";

	/** Constante cs_FIND_ALL_ROLES_REASIGNACION. */
	private static final String cs_FIND_ALL_ROLES_REASIGNACION = "SELECT DISTINCT ROL.* FROM SDB_AUT_ROL ROL INNER JOIN SDB_AUT_ROL_OPCION RO ON ROL.ID_ROL = RO.ID_ROL INNER JOIN SDB_AUT_OPCION_ETAPA OE ON RO.ID_OPCION = OE.ID_OPCION WHERE ROL.ACTIVO = 'S' ORDER BY ROL.NOMBRE ASC";

	/**
	 * Instancia un nuevo objeto rol DAO.
	 */
	public RolDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Rol filtrado por activo = S.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean, verdadero para filtrar los resultados por Activo = S, falso para obtener todos los registros
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public Collection<Rol> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<Rol>   lcr_cr;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		StringBuilder     lsb_sb;

		lcr_cr     = new ArrayList<Rol>();
		lps_ps     = null;
		lrs_rs     = null;
		lsb_sb     = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sb = lsb_sb.append("WHERE ACTIVO = 'S' ");

			lsb_sb     = lsb_sb.append("ORDER BY NOMBRE ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcr_cr.add(getRol(lrs_rs));
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

		if(lcr_cr.isEmpty())
			lcr_cr = null;

		return lcr_cr;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Rol filtrado por el tipo de componente.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto Rol
	 * @param ab_activos de ab activos, true para filtrar por registros activos, false para no filtrar por el estado
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public Collection<Rol> findAllByComponente(String as_param, boolean ab_activos)
	    throws B2BException
	{
		Collection<Rol>   lcr_cr;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcr_cr     = new ArrayList<Rol>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(StringUtils.isValidString(as_param))
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_ALL_BY_COMPONENTE);

				if(ab_activos)
					lsb_query.append(" AND ACTIVO = 'S' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcr_cr.add(getRol(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByComponente", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcr_cr.isEmpty())
			lcr_cr = null;

		return lcr_cr;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Roles de recursos.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public Collection<Rol> findAllRecursos()
	    throws B2BException
	{
		Collection<Rol>   lcr_cr;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		StringBuilder     lsb_sb;

		lcr_cr     = new ArrayList<Rol>();
		lps_ps     = null;
		lrs_rs     = null;
		lsb_sb     = new StringBuilder(cs_FIND_ALL_RECURSOS);

		try
		{
			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcr_cr.add(getRol(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllRecursos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcr_cr.isEmpty())
			lcr_cr = null;

		return lcr_cr;
	}

	/**
	 * Find all roles reasignacion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Rol> findAllRolesReasignacion()
	    throws B2BException
	{
		Collection<Rol>   lcr_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcr_return     = new ArrayList<Rol>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ROLES_REASIGNACION);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcr_return.add(getUsuarioRol(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllRolesReasignacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcr_return.isEmpty())
			lcr_return = null;

		return lcr_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Roles de segunda instancia.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public Collection<Rol> findAllSegundaInstancia()
	    throws B2BException
	{
		Collection<Rol>   lcr_cr;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcr_cr     = new ArrayList<Rol>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_SEGUNDA_INSTANCIA);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcr_cr.add(getRol(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllSegundaInstancia", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcr_cr.isEmpty())
			lcr_cr = null;

		return lcr_cr;
	}

	/**
	 * Retorna el valor del objeto de tipo Rol filtrado por ID.
	 *
	 * @param ar_parametros correspondiente al valor del tipo de objeto Rol
	 * @return devuelve el valor del objeto rol
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public Rol findById(Rol ar_parametros)
	    throws B2BException
	{
		return findById(ar_parametros.getIdRol(), false);
	}

	/**
	 * Retorna el valor del objeto de tipo Rol filtrado por ID.
	 *
	 * @param as_parametros correspondiente al valor del tipo de objeto Rol
	 * @param ab_activo de ab activo
	 * @return devuelve el valor del objeto rol
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public Rol findById(String as_parametros, boolean ab_activo)
	    throws B2BException
	{
		Rol               lr_rol;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lr_rol     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			StringBuilder lsb_query;

			lsb_query = new StringBuilder(cs_FIND_BY_ID);

			if(ab_activo)
				lsb_query.append(" AND ACTIVO = 'S' ");

			lps_ps = getConnection().prepareStatement(lsb_query.toString());

			lps_ps.setString(1, as_parametros);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lr_rol = getRol(lrs_rs);
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

		return lr_rol;
	}

	/**
	 * Find componente by id rol.
	 *
	 * @param as_parametros de as parametros
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Componente> findComponenteByIdRol(String as_parametros)
	    throws B2BException
	{
		Collection<Componente> lc_componente;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lc_componente     = new ArrayList<Componente>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_ROL_COMPONENTE);

			lps_ps.setString(1, as_parametros);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lc_componente.add(getComponente(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findComponenteByIdRol", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lc_componente;
	}

	/**
	 * Retorna el valor del objeto de tipo Rol filtrado por el ID del componente.
	 *
	 * @param as_componente correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto rol
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public Rol findComponenteRol(String as_componente)
	    throws B2BException
	{
		Rol               lr_rol;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lr_rol     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_ID_COMPONENTE);

			lps_ps.setString(li_contador++, as_componente);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
			{
				lr_rol = new Rol();

				lr_rol.setIdComponente(lrs_rs.getString("ID_COMPONENTE"));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findComponenteRol", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lr_rol;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Rol filtrado por el id de usuario y componente.
	 *
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @param as_componente correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public Collection<Rol> findUsuarioRol(String as_idUsuario, String as_componente)
	    throws B2BException
	{
		Collection<Rol>   lcr_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcr_return     = new ArrayList<Rol>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_USUARIO_ROL);

			lps_ps.setString(1, as_idUsuario);
			lps_ps.setString(2, as_componente);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcr_return.add(getUsuarioRol(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findUsuarioRol", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcr_return.isEmpty())
			lcr_return = null;

		return lcr_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Rol filtrado por el id circulo y id rol.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param as_idRol correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public Collection<Rol> findUsuarioRolOrip(String as_idCirculo, String as_idRol)
	    throws B2BException
	{
		Collection<Rol>   lcr_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcr_return     = new ArrayList<Rol>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_USUARIO_ROL_ORIP);

			lps_ps.setString(1, as_idCirculo);
			lps_ps.setString(2, as_idRol);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcr_return.add(getUsuarioRol(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findUsuarioRol", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcr_return.isEmpty())
			lcr_return = null;

		return lcr_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Insert or update.
	 *
	 * @param ar_parametros correspondiente al valor del tipo de objeto Rol
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Rol insertOrUpdate(Rol ar_parametros, boolean ab_query)
	    throws B2BException
	{
		long ll_secuencia;
		ll_secuencia = 0;

		if(ar_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			lps_ps            = null;
			lrs_rs            = null;
			lps_secuencia     = null;
			lc_connection     = getConnection();

			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column         = 1;
				lsb_sb            = new StringBuilder(ab_query ? cs_INSERT : cs_UPDATE);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lps_ps = lc_connection.prepareStatement(lsb_sb.toString());

				if(ab_query)
				{
					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						ll_secuencia = lrs_rs.getLong(1);

						lps_ps.setString(li_column++, StringUtils.getString(ll_secuencia));
						ar_parametros.setIdRol(StringUtils.getString(ll_secuencia));
					}
				}

				lps_ps.setString(li_column++, ar_parametros.getNombre());
				setDate(lps_ps, ar_parametros.getFechaDesde(), li_column++);
				setDate(lps_ps, ar_parametros.getFechaHasta(), li_column++);
				lps_ps.setString(li_column++, ar_parametros.getActivo());
				lps_ps.setString(li_column++, ar_parametros.getIdComponente());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ar_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ar_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ar_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ar_parametros.getIdUsuarioModificacion());
				}

				if(!ab_query)
					lps_ps.setString(li_column++, ar_parametros.getIdRol());

				lps_ps.executeUpdate();

				ar_parametros = findById(ar_parametros);
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return ar_parametros;
	}

	/**
	 * Retorna el valor de componente.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de componente
	 * @throws SQLException Señala que se ha producido una excepción SQl
	 */
	private Componente getComponente(ResultSet ars_rs)
	    throws SQLException
	{
		Componente lc_datos;

		lc_datos = new Componente();

		lc_datos.setIdComponente(ars_rs.getString("ID_COMPONENTE"));
		lc_datos.setNombre(ars_rs.getString("NOMBRE"));
		lc_datos.setFechaDesde(ars_rs.getDate("FECHA_DESDE"));
		lc_datos.setFechaHasta(ars_rs.getDate("FECHA_HASTA"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}

	/**
	 * Retorna el valor de rol.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de rol
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Rol getRol(ResultSet ars_rs)
	    throws SQLException
	{
		Rol lr_datos;

		lr_datos = new Rol();

		lr_datos.setIdRol(ars_rs.getString("ID_ROL"));
		lr_datos.setNombre(ars_rs.getString("NOMBRE"));
		lr_datos.setFechaDesde(ars_rs.getDate("FECHA_DESDE"));
		lr_datos.setFechaHasta(ars_rs.getDate("FECHA_HASTA"));
		lr_datos.setActivo(ars_rs.getString("ACTIVO"));
		lr_datos.setIdComponente(ars_rs.getString("ID_COMPONENTE"));

		fillAuditoria(ars_rs, lr_datos);

		return lr_datos;
	}

	/**
	 * Retorna el valor de usuario rol.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de usuario rol
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Rol getUsuarioRol(ResultSet ars_rs)
	    throws SQLException
	{
		Rol lur_usuarioRol;

		lur_usuarioRol = new Rol();

		lur_usuarioRol.setIdRol(ars_rs.getString("ID_ROL"));
		lur_usuarioRol.setNombre(ars_rs.getString("NOMBRE"));

		return lur_usuarioRol;
	}
}
