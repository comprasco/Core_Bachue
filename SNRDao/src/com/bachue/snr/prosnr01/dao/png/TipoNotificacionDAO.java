package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.png.TipoNotificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PNG_TIPO_NOTIFICACION
 *
 * @author Sebastian Sanchez
 *
 */
public class TipoNotificacionDAO extends BaseDAO
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_TIPO_NOTIFICACION, TIPO_NOTIFICACION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PNG_TIPO_NOTIFICACION ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PNG_TIPO_NOTIFICACION WHERE ID_TIPO_NOTIFICACION = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_TIPO_NOTIFICACION(ID_TIPO_NOTIFICACION, TIPO_NOTIFICACION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_TIPO_NOTIFICACION SET TIPO_NOTIFICACION=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_TIPO_NOTIFICACION=?";

	/** Constante  cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PNG_TIPO_NOTIFICACION_ID_TIPO_NOTIFICACION.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros de la tabla SDB_PNG_TIPO_NOTIFICACION
	 *
	 * @return Colección de tipo notificacion con los resultados de la consulta
	 * @throws B2BException
	 */
	public Collection<TipoNotificacion> findAll()
	    throws B2BException
	{
		Collection<TipoNotificacion> lctn_object;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lctn_object     = new ArrayList<TipoNotificacion>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctn_object.add(getObjectFromResultSet(lrs_rs));
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

		if(lctn_object.isEmpty())
			lctn_object = null;

		return lctn_object;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador
	 *
	 * @param atn_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Registro resultante de la consulta
	 * @throws B2BException
	 */
	public TipoNotificacion findById(TipoNotificacion atn_param)
	    throws B2BException
	{
		TipoNotificacion ltn_object;

		ltn_object = null;

		if(atn_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, atn_param.getIdTipoNotificacion());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltn_object = getObjectFromResultSet(lrs_rs);
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

		return ltn_object;
	}

	/**
	 * Dependiendo del procedimiento seleccionado, inserta o actualiza un registro
	 * con la información de tipo notificacion suministrada.
	 *
	 * @param as_param objeto contenedor de la información a actualizar o insertar
	 * @param ab_query define el proceso seleccionado, true para insertar un nuevo
	 * registro, false para actualizar un registro existente.
	 * @throws B2BException
	 */
	public void insertOrUpdate(TipoNotificacion as_param, boolean ab_query)
	    throws B2BException
	{
		if(as_param != null)
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
				Connection lc_c;

				lc_c       = getConnection();
				lps_ps     = lc_c.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
				}

				if(ab_query)
				{
					lps_ps.setString(li_column++, as_param.getTipoNotificacion());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, as_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, as_param.getTipoNotificacion());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, as_param.getIpModificacion());
					lps_ps.setString(li_column++, as_param.getIdTipoNotificacion());
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
				close(lrs_rs);
				close(lps_secuencia);
				close(lps_ps);
			}
		}
	}

	/**
	 * Extrae la información de un registro de base de datos, la asigna a un objeto y la retorna
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Objeto tipo anotación con la información recuperada de la base de datos
	 * @throws SQLException
	 */
	private TipoNotificacion getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoNotificacion ltn_tipoNotificacion;

		ltn_tipoNotificacion = new TipoNotificacion();

		ltn_tipoNotificacion.setIdTipoNotificacion(ars_rs.getString("ID_TIPO_NOTIFICACION"));
		ltn_tipoNotificacion.setTipoNotificacion(ars_rs.getString("TIPO_NOTIFICACION"));
		ltn_tipoNotificacion.setActivo(ars_rs.getString("ACTIVO"));
		ltn_tipoNotificacion.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ltn_tipoNotificacion.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ltn_tipoNotificacion.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ltn_tipoNotificacion.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ltn_tipoNotificacion.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ltn_tipoNotificacion.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ltn_tipoNotificacion;
	}
}
