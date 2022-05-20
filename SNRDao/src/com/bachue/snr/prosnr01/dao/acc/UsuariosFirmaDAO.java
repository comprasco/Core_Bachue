package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.UsuariosFirma;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_USUARIOS_FIRMA
 *
 * @author hcastaneda
 */
public class UsuariosFirmaDAO extends BaseDAO
{
	/** Constante cs_SEARCH_BY_USER. */
	private static final String cs_SEARCH_BY_USER = "SELECT * FROM SDB_ACC_USUARIOS_FIRMA WHERE ID_USUARIO = ?";

	/**
	 * Retorna el valor del objeto de tipo UsuariosFirma
	 *
	 * @param afuf_parametros correspondiente al valor del tipo de objeto UsuariosFirma
	 * @return devuelve el valor del objeto usuarios firma
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuariosFirma
	 */
	public UsuariosFirma searchByUser(UsuariosFirma afuf_parametros)
	    throws B2BException
	{
		UsuariosFirma lcfuf_datos;
		lcfuf_datos = null;

		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_SEARCH_BY_USER);

				lps_ps.setString(li_contador++, afuf_parametros.getIdUsuario());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcfuf_datos = getUsuariosFirma(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				getLog().error("searchByUser", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcfuf_datos;
	}

	/**
	 * Retorna el valor de usuarios firma.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de usuarios firma
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see UsuariosFirma
	 */
	private UsuariosFirma getUsuariosFirma(ResultSet ars_rs)
	    throws SQLException
	{
		UsuariosFirma lfuf_usuarios;
		lfuf_usuarios = new UsuariosFirma();

		lfuf_usuarios.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO"));
		lfuf_usuarios.setClave(ars_rs.getString("CLAVE"));
		lfuf_usuarios.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lfuf_usuarios.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lfuf_usuarios.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lfuf_usuarios.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));

		return lfuf_usuarios;
	}
}
