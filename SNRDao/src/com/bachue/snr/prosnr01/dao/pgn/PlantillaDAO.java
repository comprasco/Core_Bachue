package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess.oracle.ClobUtils;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_PLANTILLA
 *
 * @author Sebastian Sanchez
 *
 */
public class PlantillaDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PLANTILLA ";

	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL_ACTIVO = cs_FIND_ALL
		+ "WHERE ACTIVO = 'S' ORDER BY LENGTH(ID_PLANTILLA),ID_PLANTILLA";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_PLANTILLA WHERE ID_PLANTILLA = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PLANTILLA(ID_PLANTILLA, ASUNTO, CUERPO, COMENTARIO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PLANTILLA SET ASUNTO=?, CUERPO=?, COMENTARIO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_PLANTILLA=?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_PLANTILLA_ID_PLANTILLA.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * @return Colección de Plantilla resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Plantilla> findAll()
	    throws B2BException
	{
		Collection<Plantilla> lcp_cp;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcp_cp     = new ArrayList<Plantilla>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcp_cp.add(getObjetFromResultSet(lrs_rs));
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

		if(lcp_cp.isEmpty())
			lcp_cp = null;

		return lcp_cp;
	}

	/**
	 * Consulta en base de datos todos los registros de plantilla que se encuentran activos
	 * @return Colección de tipo <code>Plantilla</code> resultante de la consulta
	 * @throws B2BException  Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Plantilla> findAllActivo()
	    throws B2BException
	{
		Collection<Plantilla> lcp_cp;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcp_cp     = new ArrayList<Plantilla>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL_ACTIVO);

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcp_cp.add(getObjetFromResultSet(lrs_rs));
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

		if(lcp_cp.isEmpty())
			lcp_cp = null;

		return lcp_cp;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador
	 *
	 * @param ap_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Plantilla resultante de la consulta
	 * @throws B2BException
	 */
	public Plantilla findById(Plantilla ap_param)
	    throws B2BException
	{
		return (ap_param != null) ? findById(ap_param.getIdPlantilla()) : null;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Plantilla resultante de la consulta
	 * @throws B2BException
	 */
	public Plantilla findById(String as_param)
	    throws B2BException
	{
		Plantilla lp_plantilla;

		lp_plantilla = null;

		if(as_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_plantilla = getObjetFromResultSet(lrs_rs);
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

		return lp_plantilla;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param ap_param objeto contenedor de la información a insertar
	 * @throws B2BException
	 */
	public void insert(Plantilla ap_param)
	    throws B2BException
	{
		if(ap_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_C;

				lc_C       = getConnection();
				lps_ps     = lc_C.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				{
					lps_ps.setString(li_column++, ap_param.getAsunto());
					lps_ps.setString(li_column++, ap_param.getCuerpo());
					lps_ps.setString(li_column++, ap_param.getComentario());
					lps_ps.setString(li_column++, ap_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ap_param.getIpCreacion());
				}

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
				close(lrs_rs);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param ap_param objeto contenedor de la información a insertar
	 * @throws B2BException
	 */
	public void update(Plantilla ap_param)
	    throws B2BException
	{
		if(ap_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				Connection    lc_conexion;
				int           li_column;
				StringBuilder lsb_query;

				lc_conexion     = getConnection();
				li_column       = 1;
				lsb_query       = new StringBuilder(cs_UPDATE);

				lps_ps = lc_conexion.prepareStatement(lsb_query.toString());

				{
					Clob lc_asunto;
					Clob lc_cuerpo;

					lc_asunto     = ClobUtils.stringToClob(ap_param.getAsunto(), lc_conexion.createClob());
					lc_cuerpo     = ClobUtils.stringToClob(ap_param.getCuerpo(), lc_conexion.createClob());

					lps_ps.setClob(li_column++, lc_asunto);
					lps_ps.setClob(li_column++, lc_cuerpo);
				}

				lps_ps.setString(li_column++, ap_param.getComentario());
				lps_ps.setString(li_column++, ap_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ap_param.getIpModificacion());
				lps_ps.setString(li_column++, ap_param.getIdPlantilla());

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
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException
	 */
	private Plantilla getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		Plantilla ls_solicitud;

		ls_solicitud = new Plantilla();

		ls_solicitud.setIdPlantilla(lrs_rs.getString("ID_PLANTILLA"));
		ls_solicitud.setAsunto(ClobUtils.clobToString(lrs_rs.getClob("ASUNTO")));
		ls_solicitud.setCuerpo(ClobUtils.clobToString(lrs_rs.getClob("CUERPO")));
		ls_solicitud.setComentario(lrs_rs.getString("COMENTARIO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
