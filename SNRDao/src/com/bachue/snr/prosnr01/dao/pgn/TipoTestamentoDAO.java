package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoTestamento;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_TIPO_TESTAMENTO
 *
 * @author Manuel Blanco
 *
 */
public class TipoTestamentoDAO extends BaseDAO
{
	private static final String cs_FIND_ALL      = "SELECT ID_TIPO_TESTAMENTO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_PGN_TIPO_TESTAMENTO";
	private static final String cs_FIND_BY_ID    = "SELECT ID_TIPO_TESTAMENTO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_PGN_TIPO_TESTAMENTO WHERE ID_TIPO_TESTAMENTO=?";
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_TIPO_TESTAMENTO_ID_TIPO_TESTAMENTO.NEXTVAL FROM DUAL";
	private static final String cs_INSERT        = "INSERT INTO SDB_PGN_TIPO_TESTAMENTO (ID_TIPO_TESTAMENTO, NOMBRE, ACTIVO, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP) ";
	private static final String cs_UPDATE        = "UPDATE SDB_PGN_TIPO_TESTAMENTO SET NOMBRE=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_TIPO_TESTAMENTO=?";

	/**
	 * Consulta todos los registros de la tabla
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException
	 */
	public Collection<TipoTestamento> findAll()
	    throws B2BException
	{
		Collection<TipoTestamento> lctt_tiposTestamento;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lctt_tiposTestamento     = new ArrayList<TipoTestamento>();
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctt_tiposTestamento.add(getObjectFromResultSet(lrs_rs));
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

		if(lctt_tiposTestamento.isEmpty())
			lctt_tiposTestamento = null;

		return lctt_tiposTestamento;
	}

	/**
	 * Consulta todos los registros relacionados con un id de tipo testamento
	 *
	 * @param att_parametros Objeto contenedor del id tipo testamento que será utilizado
	 * como filtro en la consulta
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException
	 */
	public TipoTestamento findById(TipoTestamento att_parametros)
	    throws B2BException
	{
		TipoTestamento    ltt_tipoTestamento;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ltt_tipoTestamento     = null;
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, att_parametros.getIdTipoTestamento());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ltt_tipoTestamento = getObjectFromResultSet(lrs_rs);
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

		return ltt_tipoTestamento;
	}

	/**
	 * Calcula la secuencia de un nuevo registro e inserta el registro en la tabla, o actualiza un registro ya
	 * existente
	 *
	 * @param att_parametros Objeto contenedor de la información que se pretende insertar o actualizar
	 * @param ab_query Indicador de si se va a insertar o actualizar; true para insertar, false para actualizar
	 * @throws B2BException
	 */
	public void insertOrUpdate(TipoTestamento att_parametros, boolean ab_query)
	    throws B2BException
	{
		if(att_parametros != null)
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
							att_parametros.setIdTipoTestamento(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, att_parametros.getIdTipoTestamento());
						}
						else
							throw new B2BException(ErrorKeys.PGN_TIPO_TESTAMENTO_SEQUENCE);
					}
				}

				lps_ps.setString(li_column++, att_parametros.getNombre());
				lps_ps.setString(li_column++, att_parametros.getActivo());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, att_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, att_parametros.getIpModificacion());
					lps_ps.setString(li_column++, att_parametros.getIdTipoTestamento());
				}
				else
				{
					lps_ps.setString(li_column++, att_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, att_parametros.getIpCreacion());
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
	 * @return Objeto tipo testamento con la información recuperada de la base de datos
	 * @throws SQLException
	 */
	private TipoTestamento getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoTestamento ltt_tipoTestamento;

		ltt_tipoTestamento = new TipoTestamento();

		ltt_tipoTestamento.setIdTipoTestamento(ars_rs.getString("ID_TIPO_TESTAMENTO"));
		ltt_tipoTestamento.setNombre(ars_rs.getString("NOMBRE"));
		ltt_tipoTestamento.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ltt_tipoTestamento.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ltt_tipoTestamento.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ltt_tipoTestamento.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ltt_tipoTestamento.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ltt_tipoTestamento.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ltt_tipoTestamento.setActivo(ars_rs.getString("ACTIVO"));

		return ltt_tipoTestamento;
	}
}
