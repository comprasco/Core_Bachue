package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.png.TipoAnotacion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PNG_TIPO_ANOTACION
 *
 * @author Manuel Blanco
 *
 */
public class TipoAnotacionDAO extends BaseDAO
{
	private static final String cs_FIND_ALL        = "SELECT ID_TIPO_ANOTACION, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_PNG_TIPO_ANOTACION";
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_TIPO_ANOTACION, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_PNG_TIPO_ANOTACION WHERE ACTIVO = 'S'";
	private static final String cs_FIND_BY_ID      = "SELECT ID_TIPO_ANOTACION, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_PNG_TIPO_ANOTACION WHERE ID_TIPO_ANOTACION=?";
	private static final String cs_FIND_SECUENCE   = "SELECT SEC_PNG_TIPO_ANOTACION_ID_TIPO_ANOTACION.NEXTVAL FROM DUAL";
	private static final String cs_INSERT          = "INSERT INTO SDB_PNG_TIPO_ANOTACION (ID_TIPO_ANOTACION, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,SYSTIMESTAMP,?) ";
	private static final String cs_UPDATE          = "UPDATE SDB_PNG_TIPO_ANOTACION SET NOMBRE=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_TIPO_ANOTACION=?";

	/**
	 * Consulta todos los registros de la tabla
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException
	 */
	public Collection<TipoAnotacion> findAll()
	    throws B2BException
	{
		Collection<TipoAnotacion> lcta_tiposAnotacion;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcta_tiposAnotacion     = new ArrayList<TipoAnotacion>();
		lps_ps                  = null;
		lrs_rs                  = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_tiposAnotacion.add(getObjectFromResultSet(lrs_rs));
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

		if(lcta_tiposAnotacion.isEmpty())
			lcta_tiposAnotacion = null;

		return lcta_tiposAnotacion;
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException
	 */
	public Collection<TipoAnotacion> findAllActivo()
	    throws B2BException
	{
		Collection<TipoAnotacion> lcta_tiposAnotacion;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcta_tiposAnotacion     = new ArrayList<TipoAnotacion>();
		lps_ps                  = null;
		lrs_rs                  = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_tiposAnotacion.add(getObjectFromResultSet(lrs_rs));
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

		if(lcta_tiposAnotacion.isEmpty())
			lcta_tiposAnotacion = null;

		return lcta_tiposAnotacion;
	}

	/**
	 * Consulta todos los registros relacionados con un id de tipo anotación
	 *
	 * @param att_parametros Objeto contenedor del id tipo anotación que será utilizado
	 * como filtro en la consulta
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException
	 */
	public TipoAnotacion findById(TipoAnotacion ata_parametros)
	    throws B2BException
	{
		TipoAnotacion     lta_tipoAnotacion;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lta_tipoAnotacion     = null;
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, ata_parametros.getIdTipoAnotacion());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lta_tipoAnotacion = getObjectFromResultSet(lrs_rs);
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

		return lta_tipoAnotacion;
	}

	/**
	 * Calcula la secuencia de un nuevo registro e inserta el registro en la tabla, o actualiza un registro ya
	 * existente
	 *
	 * @param att_parametros Objeto contenedor de la información que se pretende insertar o actualizar
	 * @param ab_query Indicador de si se va a insertar o actualizar; true para insertar, false para actualizar
	 * @throws B2BException
	 */
	public void insertOrUpdate(TipoAnotacion ata_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ata_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					Connection lc_connection;

					lc_connection     = getConnection();

					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							ata_parametros.setIdTipoAnotacion(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, ata_parametros.getIdTipoAnotacion());
						}
						else
							throw new B2BException(ErrorKeys.PNG_TIPO_ANOTACION_SEQUENCE);
					}
				}

				lps_ps.setString(li_column++, ata_parametros.getNombre());
				lps_ps.setString(li_column++, ata_parametros.getActivo());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, ata_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ata_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ata_parametros.getIdTipoAnotacion());
				}
				else
				{
					lps_ps.setString(li_column++, ata_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ata_parametros.getIpCreacion());
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
				close(lps_secuencia);
				close(lrs_rs);
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
	private TipoAnotacion getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoAnotacion lta_tipoAnotacion;

		lta_tipoAnotacion = new TipoAnotacion();

		lta_tipoAnotacion.setIdTipoAnotacion(ars_rs.getString("ID_TIPO_ANOTACION"));
		lta_tipoAnotacion.setNombre(ars_rs.getString("NOMBRE"));
		lta_tipoAnotacion.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lta_tipoAnotacion.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lta_tipoAnotacion.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lta_tipoAnotacion.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lta_tipoAnotacion.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lta_tipoAnotacion.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lta_tipoAnotacion.setActivo(ars_rs.getString("ACTIVO"));

		return lta_tipoAnotacion;
	}
}
