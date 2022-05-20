package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.Desborde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_DESBORDE.
 *
 * @author Manuel Blanco
 */
public class DesbordeDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_CIRCULO_ORIGEN, ID_CIRCULO_DESBORDE, ORDEN, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HABILITADA, OBSERVACIONES FROM SDB_ACC_DESBORDE";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_CIRCULO_ORIGEN, ID_CIRCULO_DESBORDE, ORDEN, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HABILITADA, OBSERVACIONES FROM SDB_ACC_DESBORDE WHERE ID_CIRCULO_ORIGEN = ? AND ID_CIRCULO_DESBORDE = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_DESBORDE (ID_CIRCULO_ORIGEN, ID_CIRCULO_DESBORDE, ORDEN, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, HABILITADA, OBSERVACIONES) VALUES (?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_DESBORDE SET ORDEN=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, HABILITADA=?, OBSERVACIONES=? WHERE ID_CIRCULO_ORIGEN=? AND ID_CIRCULO_DESBORDE=?";

	/**
	 * Consulta en la base de datos todos los registros existentes en la tabla.
	 *
	 * @return Colección de desbordes resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Desborde
	 */
	public Collection<Desborde> findAll()
	    throws B2BException
	{
		Collection<Desborde> lcd_object;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcd_object     = new LinkedList<Desborde>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcd_object.add(getObjetFromResultSet(lrs_rs));
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

		return lcd_object;
	}

	/**
	 * Consulta en la base de datos un registro que esté relacionada a un id círculo origen e id círculo
	 * desborde específicos.
	 *
	 * @param ad_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Desborde resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Desborde
	 */
	public Desborde findById(Desborde ad_param)
	    throws B2BException
	{
		Desborde ld_object;

		ld_object = null;

		if(ad_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;
				li_cont     = 1;
				lps_ps      = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_cont++, ad_param.getIdCirculoOrigen());
				lps_ps.setString(li_cont++, ad_param.getIdCirculoDesborde());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ld_object = getObjetFromResultSet(lrs_rs);
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

		return ld_object;
	}

	/**
	 * Inserta o actualiza un registro en la tabla.
	 *
	 * @param ad_param Objeto contenedor de la información a insertar o actualizar en un registro
	 * @param ab_query Si es tru, indica que se va a insertar un registro, false para actualizar por medio de un id
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Desborde ad_param, boolean ab_query)
	    throws B2BException
	{
		if(ad_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ad_param.getIdCirculoOrigen());
					lps_ps.setString(li_column++, ad_param.getIdCirculoDesborde());
				}

				lps_ps.setLong(li_column++, NumericUtils.getLong(ad_param.getOrden()));

				if(ab_query)
				{
					lps_ps.setString(li_column++, ad_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ad_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ad_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ad_param.getIpModificacion());
				}

				lps_ps.setString(li_column++, ad_param.getHabilitada());
				lps_ps.setString(li_column++, ad_param.getObservaciones());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, ad_param.getIdCirculoOrigen());
					lps_ps.setString(li_column++, ad_param.getIdCirculoDesborde());
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
			}
		}
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto Desborde.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return Desborde con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Desborde getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Desborde ld_desborde;

		ld_desborde = new Desborde();

		ld_desborde.setIdCirculoOrigen(ars_rs.getString("ID_CIRCULO_ORIGEN"));
		ld_desborde.setIdCirculoDesborde(ars_rs.getString("ID_CIRCULO_DESBORDE"));
		ld_desborde.setOrden(getBigDecimal(ars_rs, "ORDEN"));
		ld_desborde.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_desborde.setFechaCreacion(ars_rs.getDate("FECHA_CREACION"));
		ld_desborde.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_desborde.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_desborde.setFechaModificacion(ars_rs.getDate("FECHA_MODIFICACION"));
		ld_desborde.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ld_desborde.setHabilitada(ars_rs.getString("HABILITADA"));
		ld_desborde.setObservaciones(ars_rs.getString("OBSERVACIONES"));

		return ld_desborde;
	}
}
