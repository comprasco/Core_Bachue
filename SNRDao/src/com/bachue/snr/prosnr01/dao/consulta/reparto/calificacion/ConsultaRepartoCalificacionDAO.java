package com.bachue.snr.prosnr01.dao.consulta.reparto.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.model.consulta.reparto.calificacion.ConsultaRepartoCalificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas para el reparto calificacion
 *
 * @author Julian Vaca
 */
public class ConsultaRepartoCalificacionDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY. */
	private static final String cs_FIND_BY = "SELECT sas.NIR,sath.ID_TURNO,spcr.NOMBRE,sath.FECHA_CREACION,sath.FECHA_REPARTO,sath.ESTADO_ACTIVIDAD "
		+ "FROM SDB_ACC_TURNO_HISTORIA sath INNER JOIN SDB_ACC_SOLICITUD sas ON (sas.ID_SOLICITUD=sath.ID_SOLICITUD)  "
		+ "INNER JOIN SDB_PGN_CIRCULO_REGISTRAL spcr ON (spcr.ID_CIRCULO = sath.ID_CIRCULO_USUARIO) "
		+ "WHERE sath.ID_USUARIO = ? AND sath.ESTADO_ACTIVIDAD IN ('ASG','BLQ') ORDER BY 2 ASC ";

	/** Constante cs_FIND_BY_ORIP. */
	private static final String cs_FIND_BY_ORIP = "SELECT sau.PRIMER_NOMBRE,sau.SEGUNDO_NOMBRE,sau.PRIMER_APELLIDO,sau.SEGUNDO_APELLIDO,sau.ID_USUARIO "
		+ "FROM SDB_AUT_USUARIO_CIRCULO saus "
		+ "INNER JOIN SDB_AUT_USUARIO_ROL saur ON (saur.ID_USUARIO=saus.ID_USUARIO AND saur.ID_ROL=61 AND saur.ACTIVO='S') "
		+ "INNER JOIN SDB_AUT_USUARIO sau ON(saus.ID_USUARIO=sau.ID_USUARIO) "
		+ "WHERE saus.ID_CIRCULO = (SELECT ID_CIRCULO FROM SDB_AUT_USUARIO_CIRCULO " + "where ID_USUARIO = ? )";

	/**
	 * Instancia un nuevo objeto consulta reparto calificacion DAO.
	 */
	public ConsultaRepartoCalificacionDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Usuario consultado por el ORIP
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
			logError(this, "findByORIP", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcu_usuarios))
			lcu_usuarios = null;

		return lcu_usuarios;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de ConsultaRepartoCalificacion consultado por ID
	 *
	 * @param au_usuario correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor del objeto collection de ConsultaRepartoCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConsultaRepartoCalificacion
	 */
	public Collection<ConsultaRepartoCalificacion> findByUser(Usuario au_usuario)
	    throws B2BException
	{
		PreparedStatement                       lps_ps;
		ResultSet                               lrs_rs;
		Collection<ConsultaRepartoCalificacion> lccrc_consulta;

		lccrc_consulta     = new ArrayList<ConsultaRepartoCalificacion>();
		lps_ps             = null;
		lrs_rs             = null;

		try
		{
			int contador;
			contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY);

			String ls_usuario;
			ls_usuario = au_usuario.getIdUsuarioCreacion();

			lps_ps.setString(contador++, ls_usuario);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccrc_consulta.add(getConsultaRepartoCalificacion(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lccrc_consulta))
			return null;

		return lccrc_consulta;
	}

	/**
	 * Retorna el valor de consulta reparto calificacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de consulta reparto calificacion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ConsultaRepartoCalificacion
	 */
	private ConsultaRepartoCalificacion getConsultaRepartoCalificacion(ResultSet ars_rs)
	    throws SQLException
	{
		ConsultaRepartoCalificacion lcrc_return;
		CirculoRegistral            lcr_circuloRegistral;
		Solicitud                   ls_solicitud;
		TurnoHistoria               lth_turnoHistoria;

		lcrc_return              = new ConsultaRepartoCalificacion();
		lcr_circuloRegistral     = new CirculoRegistral();
		ls_solicitud             = new Solicitud();
		lth_turnoHistoria        = new TurnoHistoria();

		ls_solicitud.setNir(ars_rs.getString("NIR"));
		lth_turnoHistoria.setIdTurno(ars_rs.getString("ID_TURNO"));
		lcr_circuloRegistral.setNombre(ars_rs.getString("NOMBRE"));
		lth_turnoHistoria.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lth_turnoHistoria.setFechaReparto(ars_rs.getTimestamp("FECHA_REPARTO"));
		lth_turnoHistoria.setEstadoActividad(ars_rs.getString("ESTADO_ACTIVIDAD"));

		lcrc_return.setCirculoRegistral(lcr_circuloRegistral);
		lcrc_return.setSolicitud(ls_solicitud);
		lcrc_return.setTurnoHistoria(lth_turnoHistoria);

		return lcrc_return;
	}

	/**
	 * Retorna el valor de usuarios ORIP.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de usuarios ORIP
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Usuario
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
		lu_usuario.setIdUsuario(ars_rs.getString("ID_USUARIO"));

		return lu_usuario;
	}
}
