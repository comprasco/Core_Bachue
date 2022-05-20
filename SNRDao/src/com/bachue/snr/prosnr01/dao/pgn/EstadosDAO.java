package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr16.model.sdb.pgn.Estados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_ESTADOS
 *
 * @author Sebastian Sanchez
 *
 */
public class EstadosDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_ESTADOS ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_ESTADOS WHERE ID_ESTADO = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ESTADOS(ID_ESTADO, NOMBRE, DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ESTADOS SET NOMBRE=?, DESCRIPCION=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_ESTADO=?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_ESTADOS_ID_ESTADO.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * @return Colección de Estados resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Estados> findAll()
	    throws B2BException
	{
		Collection<Estados> lce_ce;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lce_ce     = new ArrayList<Estados>();
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
				lce_ce.add(getObjetFromResultSet(lrs_rs));
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

		if(lce_ce.isEmpty())
			lce_ce = null;

		return lce_ce;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador
	 *
	 * @param ae_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Estados resultante de la consulta
	 * @throws B2BException
	 */
	public Estados findById(Estados ae_param)
	    throws B2BException
	{
		Estados le_estados;

		le_estados = null;

		if(ae_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, ae_param.getIdEstado());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					le_estados = getObjetFromResultSet(lrs_rs);
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

		return le_estados;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param ae_param objeto contenedor de la información a insertar
	 * @throws B2BException
	 */
	public void insert(Estados ae_param)
	    throws B2BException
	{
		if(ae_param != null)
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
					lps_ps.setString(li_column++, ae_param.getNombre());
					lps_ps.setString(li_column++, ae_param.getDescripcion());
					lps_ps.setString(li_column++, ae_param.getActivo());
					lps_ps.setString(li_column++, ae_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ae_param.getIpCreacion());
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
	 * @param ae_param objeto contenedor de la información a insertar
	 * @throws B2BException
	 */
	public void update(Estados ae_param)
	    throws B2BException
	{
		if(ae_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_UPDATE);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, ae_param.getNombre());
				lps_ps.setString(li_column++, ae_param.getDescripcion());
				lps_ps.setString(li_column++, ae_param.getActivo());
				lps_ps.setString(li_column++, ae_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ae_param.getIpModificacion());
				lps_ps.setString(li_column++, ae_param.getIdEstado());

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
	private Estados getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		Estados ls_solicitud;

		ls_solicitud = new Estados();

		ls_solicitud.setIdEstado(lrs_rs.getString("ID_ESTADO"));
		ls_solicitud.setNombre(lrs_rs.getString("NOMBRE"));
		ls_solicitud.setDescripcion(lrs_rs.getString("DESCRIPCION"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
