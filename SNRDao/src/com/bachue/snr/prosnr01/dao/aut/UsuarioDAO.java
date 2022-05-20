package com.bachue.snr.prosnr01.dao.aut;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas relacionadas con la tabla SDB_AUT_USUARIO.
 *
 * @author jpatino
 */
public class UsuarioDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_AUT_USUARIO WHERE ID_USUARIO = ?";

	/** Constante cs_FIND_USER_ACTIVE_BY_ID. */
	private static final String cs_FIND_USER_ACTIVE_BY_ID = "SELECT * FROM SDB_AUT_USUARIO WHERE ID_USUARIO = ? AND ACTIVO = 'S'";

	/** Constante cs_FIND_USERS_BY_CIRCULO_ORIP. */
	private static final String cs_FIND_USERS_BY_CIRCULO_ORIP = "SELECT U.* FROM SDB_AUT_USUARIO U INNER JOIN SDB_AUT_USUARIO_CIRCULO UC ON (UC.ID_USUARIO = U.ID_USUARIO AND NVL(UC.ACTIVO,'S') = 'S') INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON (CR.ID_CIRCULO = UC.ID_CIRCULO AND NVL(CR.ACTIVO,'S') = 'S') INNER JOIN SDB_AUT_USUARIO_ROL UR ON (UR.ID_USUARIO = U.ID_USUARIO AND NVL(UR.ACTIVO,'S') = 'S') INNER JOIN SDB_AUT_ROL R ON (R.ID_ROL = UR.ID_ROL AND NVL(R.ACTIVO,'S') = 'S') WHERE CR.ID_CIRCULO = ? AND R.ID_ROL = ?";
	private static final String cs_FIND_USERS_BY_CIRCULO_ORIP_APOYO_NACIONAL = "SELECT U.*, UE.ID_ETAPA FROM SDB_AUT_USUARIO U INNER JOIN SDB_AUT_USUARIO_CIRCULO UC ON (UC.ID_USUARIO = U.ID_USUARIO AND NVL(UC.ACTIVO,'S') = 'S') INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON (CR.ID_CIRCULO = UC.ID_CIRCULO AND NVL(CR.ACTIVO,'S') = 'S') INNER JOIN SDB_AUT_USUARIO_ROL UR ON (UR.ID_USUARIO = U.ID_USUARIO AND NVL(UR.ACTIVO,'S') = 'S') INNER JOIN SDB_AUT_ROL R ON (R.ID_ROL = UR.ID_ROL AND NVL(R.ACTIVO,'S') = 'S') INNER JOIN SDB_PNG_USUARIO_ETAPA UE ON UE.ID_USUARIO = U.ID_USUARIO WHERE CR.ID_CIRCULO = ? AND R.ID_ROL = ? AND UE.ID_ETAPA = '80' ";

	/** Constante cs_FIND_USERS_LINEA_PRODUCCION_BY_CIRCULO_ORIP. */
	private static final String cs_FIND_USERS_LINEA_PRODUCCION_BY_CIRCULO_ORIP = "SELECT AU.* FROM SDB_AUT_USUARIO AU INNER JOIN SDB_AUT_USUARIO_ROL AUR ON AUR.ID_USUARIO = AU.ID_USUARIO INNER JOIN SDB_AUT_USUARIO_CIRCULO AUC ON AUC.ID_USUARIO = AU.ID_USUARIO INNER JOIN SDB_PGN_USUARIO_LINEA PUL ON PUL.ID_USUARIO = AU.ID_USUARIO WHERE AUR.ID_ROL = ? AND ID_CIRCULO = ? AND PUL.ID_LINEA_PRODUCCION = ? AND AU.ACTIVO = 'S' AND AUR.ACTIVO = 'S' AND AUC.ACTIVO = 'S'";

	/** Constante cs_FIND_BY_DOC_TIPO. */
	private static final String cs_FIND_BY_DOC_TIPO = "SELECT * FROM SDB_AUT_USUARIO WHERE ID_DOCUMENTO_TIPO = ? AND NUMERO_DOCUMENTO = ?";

	/** Constante cs_FIND_ALL_USERS. */
	private static final String cs_FIND_ALL_USERS = "SELECT * FROM SDB_AUT_USUARIO";

	/** Constante cs_INSERT_USER. */
	private static final String cs_INSERT_USER = "INSERT INTO SDB_AUT_USUARIO (ID_USUARIO,ID_DOCUMENTO_TIPO,NUMERO_DOCUMENTO,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,"
		+ "SEGUNDO_APELLIDO,CORREO_ELECTRONICO,SEGUNDO_FACTOR_AUTENTICACION,ID_ENTIDAD_EXTERNA,NUMERO_CONVENIO,"
		+ "FECHA_DESDE,FECHA_HASTA,ACTIVO,NUMERO_RADICADO_SOLICITUD,CORREO_ELECTRONICO_CORPORATIVO,CARGO,JUSTIFICACION,VIGENCIA_SEGUNDA_CLAVE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_POLITICA) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, SYSTIMESTAMP,?,?)";

	/** Constante cs_UPDATE_USER. */
	private static final String cs_UPDATE_USER = "UPDATE SDB_AUT_USUARIO SET ID_DOCUMENTO_TIPO = ?, NUMERO_DOCUMENTO = ?, PRIMER_NOMBRE = ?, SEGUNDO_NOMBRE = ?, PRIMER_APELLIDO = ?, SEGUNDO_APELLIDO = ?, CORREO_ELECTRONICO = ?, SEGUNDO_FACTOR_AUTENTICACION = ?, ID_ENTIDAD_EXTERNA = ?, NUMERO_CONVENIO = ?, FECHA_DESDE = ?, FECHA_HASTA = ?, ACTIVO = ?, NUMERO_RADICADO_SOLICITUD=?, CORREO_ELECTRONICO_CORPORATIVO=?, CARGO=?, JUSTIFICACION=?, VIGENCIA_SEGUNDA_CLAVE=?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, ID_POLITICA = ? WHERE ID_USUARIO = ?";

	/** Constante cs_FIND_BY_ORIP. */
	private static final String cs_FIND_BY_ORIP = "SELECT sau.PRIMER_NOMBRE,sau.SEGUNDO_NOMBRE,sau.PRIMER_APELLIDO,sau.SEGUNDO_APELLIDO "
		+ "FROM SDB_AUT_USUARIO_CIRCULO saus "
		+ "INNER JOIN SDB_AUT_USUARIO_ROL saur ON (saur.ID_USUARIO=saus.ID_USUARIO AND saur.ID_ROL=25 AND saur.ACTIVO='S') "
		+ "INNER JOIN SDB_AUT_USUARIO sau ON(saus.ID_USUARIO=sau.ID_USUARIO) "
		+ "WHERE saus.ID_CIRCULO = (SELECT ID_CIRCULO FROM SDB_AUT_USUARIO_CIRCULO where ID_USUARIO = ? )";

	/** Constante cs_LINEA_PRODUCCION. */
	private static final String cs_LINEA_PRODUCCION = "SELECT LP.NOMBRE FROM ((SDB_AUT_USUARIO U INNER JOIN SDB_PGN_USUARIO_LINEA UL ON U.ID_USUARIO = UL.ID_USUARIO) INNER JOIN SDB_PGN_LINEA_PRODUCCION LP ON UL.ID_LINEA_PRODUCCION = LP.ID_LINEA_PRODUCCION) WHERE U.ID_USUARIO = ?";

	/** Constante cs_CIRCULOS_REGISTRALES. */
	private static final String cs_CIRCULOS_REGISTRALES = "SELECT CR.NOMBRE FROM ((SDB_AUT_USUARIO U INNER JOIN SDB_AUT_USUARIO_CIRCULO UC ON U.ID_USUARIO = UC.ID_USUARIO) INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON UC.ID_CIRCULO = CR.ID_CIRCULO) WHERE U.ID_USUARIO = ?";

	/** Constante cs_ETAPAS. */
	private static final String cs_ETAPAS = "SELECT E.NOMBRE FROM ((SDB_AUT_USUARIO U INNER JOIN SDB_PNG_USUARIO_ETAPA UE ON U.ID_USUARIO = UE.ID_USUARIO) INNER JOIN SDB_PGN_ETAPA E ON UE.ID_ETAPA = E.ID_ETAPA) WHERE U.ID_USUARIO = ?";

	/** Constante cs_FIND_USER_BY_ETAPA. */
	private static final String cs_FIND_USER_BY_ETAPA = "SELECT * FROM SDB_AUT_USUARIO WHERE ID_USUARIO IN (SELECT ID_USUARIO FROM SDB_PNG_USUARIO_ETAPA WHERE ID_ETAPA = ?)";

	/** Constante cs_FIND_BY_ETAPA_ROL_CIRCULO. */
	private static final String cs_FIND_BY_ETAPA_ROL_CIRCULO = "SELECT U.* FROM SDB_AUT_USUARIO U INNER JOIN SDB_AUT_USUARIO_ROL UR ON U.ID_USUARIO = UR.ID_USUARIO INNER JOIN SDB_AUT_USUARIO_CIRCULO UC ON U.ID_USUARIO = UC.ID_USUARIO INNER JOIN SDB_PNG_USUARIO_ETAPA UE ON U.ID_USUARIO = UE.ID_USUARIO WHERE UE.ID_ETAPA = ? AND UR.ID_ROL = ? AND UC.ID_CIRCULO = ?";

	/**
	 * Retorna el valor del objeto de tipo Collection de Usuario filtrado por ID usuario.
	 *
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection de Usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public Collection<Usuario> findAllUsers(String as_idUsuario)
	    throws B2BException
	{
		Collection<Usuario> lcu_usuarios;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;
		String              ls_temp;

		ls_temp                                                              = cs_FIND_ALL_USERS;
		lcu_usuarios                                                         = new ArrayList<Usuario>();
		lps_ps                                                               = null;
		lrs_rs                                                               = null;

		try
		{
			if(StringUtils.isValidString(as_idUsuario))
			{
				ls_temp += " WHERE ID_USUARIO = ? ORDER BY ID_USUARIO";

				lps_ps = getConnection().prepareStatement(ls_temp);

				lps_ps.setString(1, as_idUsuario);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcu_usuarios.add(getObjetFromResultSet(lrs_rs));
			}
			else
			{
				ls_temp += " ORDER BY ID_USUARIO";

				lps_ps     = getConnection().prepareStatement(ls_temp);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcu_usuarios.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllUsers", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcu_usuarios.isEmpty())
			return null;

		return lcu_usuarios;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Usuario filtrado por ID usuario, si el parametro as_idUsuario es null retorna todos los usuarios activos.
	 *
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection de Usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public Collection<Usuario> findAllUsersActivos(String as_idUsuario)
	    throws B2BException
	{
		Collection<Usuario> lcu_usuarios;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;
		String              ls_temp;

		ls_temp          = cs_FIND_ALL_USERS;
		lcu_usuarios     = new ArrayList<Usuario>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			if(StringUtils.isValidString(as_idUsuario))
			{
				ls_temp += " WHERE ID_USUARIO = ? ORDER BY ID_USUARIO";

				lps_ps = getConnection().prepareStatement(ls_temp);

				lps_ps.setString(1, as_idUsuario);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcu_usuarios.add(getObjetFromResultSet(lrs_rs));
			}
			else
			{
				ls_temp += " WHERE ACTIVO = 'S' ORDER BY ID_USUARIO";

				lps_ps     = getConnection().prepareStatement(ls_temp);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcu_usuarios.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllUsersActivos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcu_usuarios.isEmpty())
			return null;

		return lcu_usuarios;
	}

	/**
	 * Find all users by circulo rol.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param as_idRol de as id rol
	 * @param ab_apoyoNacional de ab apoyo nacional
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Usuario> findAllUsersByCirculoRol(String as_idCirculo, String as_idRol, boolean ab_apoyoNacional)
	    throws B2BException
	{
		Collection<Usuario> lcu_usuarios;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcu_usuarios     = new ArrayList<Usuario>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			if(StringUtils.isValidString(as_idCirculo) && StringUtils.isValidString(as_idRol))
			{
				if(ab_apoyoNacional)
					lps_ps = getConnection().prepareStatement(cs_FIND_USERS_BY_CIRCULO_ORIP_APOYO_NACIONAL);
				else
					lps_ps = getConnection().prepareStatement(cs_FIND_USERS_BY_CIRCULO_ORIP);

				lps_ps.setString(1, as_idCirculo);
				lps_ps.setString(2, as_idRol);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcu_usuarios.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllUsersByCirculoRol", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcu_usuarios.isEmpty())
			lcu_usuarios = null;

		return lcu_usuarios;
	}

	/**
	 * Busca todos los usuarios por etapa.
	 *
	 * @param as_etapa correspondiente al valor del tipo de objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void findAllUsersByEtapa(long as_etapa)
	    throws B2BException
	{
		findAllUsersByEtapa(as_etapa, true);
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Usuario filtrado por id etapa.
	 *
	 * @param as_etapa correspondiente al valor del tipo de objeto long
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de Usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public Collection<Usuario> findAllUsersByEtapa(long as_etapa, boolean ab_b)
	    throws B2BException
	{
		Collection<Usuario> lcu_usuarios;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcu_usuarios     = new ArrayList<Usuario>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append(cs_FIND_USER_BY_ETAPA);

			if(ab_b)
				lsb_sb.append(" AND ACTIVO = 'S'");

			lsb_sb.append(" ORDER BY PRIMER_NOMBRE, PRIMER_APELLIDO ASC");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setLong(1, as_etapa);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcu_usuarios.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllUsersByEtapa", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcu_usuarios.isEmpty())
			return null;

		return lcu_usuarios;
	}

	/**
	 * Find by etapa rol circulo.
	 *
	 * @param as_etapa de as etapa
	 * @param as_idRol de as id rol
	 * @param as_idCirculo de as id circulo
	 * @param lb_usuarioRolCirculoEtapaActivo de lb activo uusario circulo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Usuario> findByEtapaRolCirculo(
	    long as_etapa, String as_idRol, String as_idCirculo, boolean lb_usuarioRolCirculoEtapaActivo
	)
	    throws B2BException
	{
		Collection<Usuario> lcu_usuarios;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcu_usuarios     = new ArrayList<Usuario>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			if((as_etapa > 0) && StringUtils.isValidString(as_idRol) && StringUtils.isValidString(as_idCirculo))
			{
				int           li_cont;
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder();

				lsb_sb.append(cs_FIND_BY_ETAPA_ROL_CIRCULO);

				if(lb_usuarioRolCirculoEtapaActivo)
					lsb_sb.append("AND U.ACTIVO = 'S' AND UR.ACTIVO = 'S' AND UC.ACTIVO = 'S' AND UE.ACTIVO = 'S'");

				lsb_sb.append(" ORDER BY U.PRIMER_NOMBRE, U.PRIMER_APELLIDO ASC");

				lps_ps      = getConnection().prepareStatement(lsb_sb.toString());
				li_cont     = 1;

				lps_ps.setLong(li_cont++, as_etapa);
				lps_ps.setString(li_cont++, as_idRol);
				lps_ps.setString(li_cont++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcu_usuarios.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByEtapaRolCirculo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcu_usuarios.isEmpty())
			return null;

		return lcu_usuarios;
	}

	/**
	 * Find by id.
	 *
	 * @param at_param the at param
	 * @return the usuario
	 * @throws B2BException the b 2 B exception
	 */
	public Usuario findById(Usuario at_param)
	    throws B2BException
	{
		return findById((at_param != null) ? at_param.getIdUsuario() : null);
	}

	/**
	 * Retorna el valor Usuario.
	 *
	 * @param as_idUsuario correspondiente al valor del id usuario
	 * @return devuelve el valor del objeto usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public Usuario findById(String as_idUsuario)
	    throws B2BException
	{
		Usuario ls_object;
		ls_object = null;

		if(StringUtils.isValidString(as_idUsuario))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idUsuario);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Usuario filtrado por el ORIP.
	 *
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection de Usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public Collection<Usuario> findByORIP(String as_usuario)
	    throws B2BException
	{
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;
		Collection<Usuario> lcu_usuarios;

		lcu_usuarios     = new ArrayList<Usuario>();
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_usuario))
		{
			try
			{
				int contador;
				contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ORIP);

				lps_ps.setString(contador++, as_usuario);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcu_usuarios.add(getUsuariosORIP(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcu_usuarios))
			lcu_usuarios = null;

		return lcu_usuarios;
	}

	/**
	 * Retorna el valor del objeto de tipo Usuario filtrado por el id documento y numero documento.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor del objeto usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public Usuario findByTipoDoc(Usuario at_param)
	    throws B2BException
	{
		Usuario lu_object;
		lu_object = null;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;
				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_DOC_TIPO);

				lps_ps.setString(li_cont++, at_param.getIdDocumentoTipo());
				lps_ps.setString(li_cont++, at_param.getNumeroDocumento());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lu_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTipoDoc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lu_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Usuario filtrado por el id usuario y que sea activo S.
	 *
	 * @param au_param correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor del objeto usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public Usuario findUserActiveById(Usuario au_param)
	    throws B2BException
	{
		Usuario ls_object;
		ls_object = null;

		if(au_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_USER_ACTIVE_BY_ID);

				lps_ps.setString(1, au_param.getIdUsuario());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findUserActiveById", lse_e);

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
	 * Find users linea produccion by rol orip activos.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param as_idRol de as id rol
	 * @param ls_idLineaProduccion de ls id linea produccion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Usuario> findUsersLineaProduccionByRolOripActivos(
	    String as_idCirculo, String as_idRol, String ls_idLineaProduccion
	)
	    throws B2BException
	{
		Collection<Usuario> lcu_usuarios;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcu_usuarios     = new ArrayList<Usuario>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idCirculo) && StringUtils.isValidString(as_idRol)
				    && StringUtils.isValidString(ls_idLineaProduccion)
			)
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_USERS_LINEA_PRODUCCION_BY_CIRCULO_ORIP);

				lps_ps.setString(1, as_idRol);
				lps_ps.setString(2, as_idCirculo);
				lps_ps.setString(3, ls_idLineaProduccion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcu_usuarios.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findUsersLineaProduccionByRolOripActivos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcu_usuarios.isEmpty())
			lcu_usuarios = null;

		return lcu_usuarios;
	}

	/**
	 * Inserta un nuevo registro en la tabla.
	 *
	 * @param au_param correspondiente al valor del tipo de objeto Usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertUser(Usuario au_param)
	    throws B2BException
	{
		if(au_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT_USER);

				lps_ps.setString(li_cont++, au_param.getIdUsuario());
				lps_ps.setString(li_cont++, au_param.getIdDocumentoTipo());
				lps_ps.setString(li_cont++, au_param.getNumeroDocumento());
				lps_ps.setString(li_cont++, au_param.getPrimerNombre());
				lps_ps.setString(li_cont++, au_param.getSegundoNombre());
				lps_ps.setString(li_cont++, au_param.getPrimerApellido());
				lps_ps.setString(li_cont++, au_param.getSegundoApellido());
				lps_ps.setString(li_cont++, au_param.getCorreoElectronico());
				lps_ps.setString(li_cont++, au_param.getSegundoFactorAutenticacion());
				lps_ps.setString(li_cont++, au_param.getIdEntidadExterna());
				lps_ps.setString(li_cont++, au_param.getNumeroConvenio());
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(au_param.getFechaDesde()));
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(au_param.getFechaHasta()));
				lps_ps.setString(li_cont++, au_param.getActivo());
				lps_ps.setString(li_cont++, au_param.getNumeroRadicadoSolicitud());
				lps_ps.setString(li_cont++, au_param.getCorreoElectronicoCorporativo());
				lps_ps.setString(li_cont++, au_param.getCargo());
				lps_ps.setString(li_cont++, au_param.getJustificacionCambio());
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(au_param.getFechaVigenciaSegundaClave()));
				lps_ps.setString(li_cont++, au_param.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, au_param.getIpCreacion());
				lps_ps.setString(li_cont++, au_param.getIdPolitica());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertUser", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de tipo Obtener circulos registrales.
	 *
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string correspondientes a los circulos registrales
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String obtenerCirculosRegistrales(String as_idUsuario)
	    throws B2BException
	{
		String            lcs_circulos;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcs_circulos     = "";
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_idUsuario))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CIRCULOS_REGISTRALES);

				lps_ps.setString(1, as_idUsuario);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					lcs_circulos += obtenerDatosPorNombre(lrs_rs);

					lcs_circulos += ", ";
				}

				if(StringUtils.isValidString(lcs_circulos))
				{
					StringBuilder sb_return = new StringBuilder(lcs_circulos);

					sb_return.setCharAt(lcs_circulos.length() - 2, '.');

					lcs_circulos = sb_return.toString();
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "obtenerCirculosRegistrales", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcs_circulos;
	}

	/**
	 * Retorna el valor del nombre de las etapas.
	 *
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String obtenerEtapas(String as_idUsuario)
	    throws B2BException
	{
		String            ls_etapas;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_etapas     = "";
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idUsuario))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_ETAPAS);

				lps_ps.setString(1, as_idUsuario);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					ls_etapas += obtenerDatosPorNombre(lrs_rs);

					ls_etapas += ", ";
				}

				if(StringUtils.isValidString(ls_etapas))
				{
					StringBuilder sb_return = new StringBuilder(ls_etapas);

					sb_return.setCharAt(ls_etapas.length() - 2, '.');

					ls_etapas = sb_return.toString();
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "obtenerEtapas", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_etapas;
	}

	/**
	 * Retorna el valor de las lineas de produccion.
	 *
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String obtenerLineasProduccion(String as_idUsuario)
	    throws B2BException
	{
		String            lcs_lineas;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcs_lineas     = "";
		lps_ps         = null;
		lrs_rs         = null;

		if(StringUtils.isValidString(as_idUsuario))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_LINEA_PRODUCCION);

				lps_ps.setString(1, as_idUsuario);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					lcs_lineas += obtenerDatosPorNombre(lrs_rs);

					lcs_lineas += ", ";
				}

				if(StringUtils.isValidString(lcs_lineas))
				{
					StringBuilder sb_return = new StringBuilder(lcs_lineas);

					sb_return.setCharAt(lcs_lineas.length() - 2, '.');

					lcs_lineas = sb_return.toString();
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "obtenerLineasProduccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcs_lineas;
	}

	/**
	 * Actualiza el registro en la tabla.
	 *
	 * @param au_param correspondiente al valor del tipo de objeto Usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateUser(Usuario au_param)
	    throws B2BException
	{
		if(au_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_USER);

				lps_ps.setString(li_cont++, au_param.getIdDocumentoTipo());
				lps_ps.setString(li_cont++, au_param.getNumeroDocumento());
				lps_ps.setString(li_cont++, au_param.getPrimerNombre());
				lps_ps.setString(li_cont++, au_param.getSegundoNombre());
				lps_ps.setString(li_cont++, au_param.getPrimerApellido());
				lps_ps.setString(li_cont++, au_param.getSegundoApellido());
				lps_ps.setString(li_cont++, au_param.getCorreoElectronico());
				lps_ps.setString(li_cont++, au_param.getSegundoFactorAutenticacion());
				lps_ps.setString(li_cont++, au_param.getIdEntidadExterna());
				lps_ps.setString(li_cont++, au_param.getNumeroConvenio());
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(au_param.getFechaDesde()));
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(au_param.getFechaHasta()));
				lps_ps.setString(li_cont++, au_param.getActivo());
				lps_ps.setString(li_cont++, au_param.getNumeroRadicadoSolicitud());
				lps_ps.setString(li_cont++, au_param.getCorreoElectronicoCorporativo());
				lps_ps.setString(li_cont++, au_param.getCargo());
				lps_ps.setString(li_cont++, au_param.getJustificacionCambio());
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(au_param.getFechaVigenciaSegundaClave()));
				lps_ps.setString(li_cont++, au_param.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, au_param.getIpModificacion());
				lps_ps.setString(li_cont++, au_param.getIdPolitica());
				lps_ps.setString(li_cont++, au_param.getIdUsuario());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateUser", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de Usuario.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Usuario
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private Usuario getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		Usuario lu_usuario;

		lu_usuario = new Usuario();

		lu_usuario.setIdUsuario(ars_rs.getString("ID_USUARIO"));
		lu_usuario.setIdDocumentoTipo(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		lu_usuario.setNumeroDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		lu_usuario.setPrimerNombre(ars_rs.getString("PRIMER_NOMBRE"));
		lu_usuario.setSegundoNombre(ars_rs.getString("SEGUNDO_NOMBRE"));
		lu_usuario.setPrimerApellido(ars_rs.getString("PRIMER_APELLIDO"));
		lu_usuario.setSegundoApellido(ars_rs.getString("SEGUNDO_APELLIDO"));
		lu_usuario.setLineasProduccion(obtenerLineasProduccion(lu_usuario.getIdUsuario()));
		lu_usuario.setCirculosRegistrales(obtenerCirculosRegistrales(lu_usuario.getIdUsuario()));
		lu_usuario.setEtapas(obtenerEtapas(lu_usuario.getIdUsuario()));
		lu_usuario.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));
		lu_usuario.setSegundoFactorAutenticacion(ars_rs.getString("SEGUNDO_FACTOR_AUTENTICACION"));
		lu_usuario.setIdEntidadExterna(ars_rs.getString("ID_ENTIDAD_EXTERNA"));
		lu_usuario.setNumeroConvenio(ars_rs.getString("NUMERO_CONVENIO"));
		lu_usuario.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		lu_usuario.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));
		lu_usuario.setActivo(ars_rs.getString("ACTIVO"));
		lu_usuario.setSegundaClave(ars_rs.getString("SEGUNDA_CLAVE"));
		lu_usuario.setNumeroRadicadoSolicitud(ars_rs.getString("NUMERO_RADICADO_SOLICITUD"));
		lu_usuario.setCorreoElectronicoCorporativo(ars_rs.getString("CORREO_ELECTRONICO_CORPORATIVO"));
		lu_usuario.setCargo(ars_rs.getString("CARGO"));
		lu_usuario.setJustificacionCambio(ars_rs.getString("JUSTIFICACION"));
		lu_usuario.setFechaVigenciaSegundaClave(ars_rs.getTimestamp("VIGENCIA_SEGUNDA_CLAVE"));
		lu_usuario.setIdPolitica(ars_rs.getString("ID_POLITICA"));

		fillAuditoria(ars_rs, lu_usuario);

		return lu_usuario;
	}

	/**
	 * Retorna el valor de usuarios ORIP.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de usuarios ORIP
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Usuario getUsuariosORIP(ResultSet ars_rs)
	    throws SQLException
	{
		Usuario lu_usuario;
		lu_usuario = new Usuario();

		lu_usuario.setPrimerNombre(ars_rs.getString("PRIMER_NOMBRE"));
		lu_usuario.setSegundoNombre(ars_rs.getString("SEGUNDO_NOMBRE"));
		lu_usuario.setPrimerApellido(ars_rs.getString("PRIMER_APELLIDO"));
		lu_usuario.setSegundoApellido(ars_rs.getString("SEGUNDO_APELLIDO"));

		return lu_usuario;
	}

	/**
	 * Retorna el valor del objeto de tipo Obtener datos por nombre.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return devuelve el valor del objeto string
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private String obtenerDatosPorNombre(ResultSet ars_rs)
	    throws SQLException
	{
		String lcs_lineas;

		lcs_lineas = ars_rs.getString("NOMBRE");

		return lcs_lineas;
	}
}
