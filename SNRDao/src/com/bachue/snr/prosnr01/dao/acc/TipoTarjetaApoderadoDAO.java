package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarjetaApoderado;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_TIPO_RECEPCION
 *
 * @author Julian Vaca
 */
public class TipoTarjetaApoderadoDAO extends BaseDAO implements IBase<TipoTarjetaApoderado>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TIPO_TARJETA_APODERADO, DESCRIPCION, ACTIVO, "
		+ " ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_TIPO_RECEPCION "
		+ " WHERE ID_TIPO_TARJETA_APODERADO=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_TIPO_TARJETA_APODERADO SET DESCRIPCION=?, "
		+ " ACTIVO=?, ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=?,"
		+ " WHERE ID_TIPO_TARJETA_APODERADO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_TIPO_TARJETA_APODERADO("
		+ "ID_TIPO_TARJETA_APODERADO,DESCRIPCION,ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION,IP_CREACION)"
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_TIPO_TARJETA_APODERADO ";

	/** Constante cs_FIND_SECUENCETIPORECEPCION. */
	private static final String cs_FIND_SECUENCETIPORECEPCION = "SELECT SEC_ACC_TIPO_RECEPCION_ID_TIPO_RECEPCION.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoTarjetaApoderado con todos los registros
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoTarjetaApoderado
	 */
	public Collection<TipoTarjetaApoderado> findAll()
	    throws B2BException
	{
		Collection<TipoTarjetaApoderado> ltta_tipoTarjetaApoderado;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		ltta_tipoTarjetaApoderado     = new ArrayList<TipoTarjetaApoderado>();
		lps_ps                        = null;
		lrs_rs                        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ltta_tipoTarjetaApoderado.add(getTipoTarjetaApoderado(lrs_rs));
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

		if(ltta_tipoTarjetaApoderado.isEmpty())
			ltta_tipoTarjetaApoderado = null;

		return ltta_tipoTarjetaApoderado;
	}

	/** {@inheritdoc} */
	public TipoTarjetaApoderado findById(TipoTarjetaApoderado at_param)
	    throws B2BException
	{
		TipoTarjetaApoderado ls_object;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdTipoTarjetaApoderado());

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

		return ls_object;
	}

	/** {@inheritdoc} */
	public void insertOrUpdate(TipoTarjetaApoderado ac_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ac_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCETIPORECEPCION);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ac_parametros.setIdTipoTarjetaApoderado(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}

					lps_ps.setString(li_column++, ac_parametros.getIdTipoTarjetaApoderado());
				}

				lps_ps.setString(li_column++, ac_parametros.getDescripcion());
				lps_ps.setString(li_column++, ac_parametros.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setDate(li_column++, DateUtils.getCleanSQLDate(ac_parametros.getFechaModificacion()));
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
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

				if(ab_query)
				{
					close(lps_secuencia);
					close(lrs_rs);
				}
			}
		}
	}

	/**
	 * Retorna el valor de TipoTarjetaApoderado
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoTarjetaApoderado
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoTarjetaApoderado getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoTarjetaApoderado ltta_tarjetaApoderado;

		ltta_tarjetaApoderado = new TipoTarjetaApoderado();

		ltta_tarjetaApoderado.setIdTipoTarjetaApoderado(ars_rs.getString("ID_TIPO_APODERADO"));
		ltta_tarjetaApoderado.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ltta_tarjetaApoderado.setActivo(ars_rs.getString("ACTIVO"));
		ltta_tarjetaApoderado.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));

		ltta_tarjetaApoderado.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return ltta_tarjetaApoderado;
	}

	/**
	 * Retorna el valor de tipo tarjeta apoderado.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de tipo tarjeta apoderado
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoTarjetaApoderado getTipoTarjetaApoderado(ResultSet ars_rs)
	    throws SQLException
	{
		TipoTarjetaApoderado lr_datos;
		lr_datos = new TipoTarjetaApoderado();

		lr_datos.setIdTipoTarjetaApoderado(ars_rs.getString("ID_TIPO_TARJETA_APODERADO"));
		lr_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lr_datos.setActivo(ars_rs.getString("ACTIVO"));
		lr_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lr_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lr_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lr_datos.setIpCreacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lr_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lr_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lr_datos;
	}
}
