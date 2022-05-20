package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_ACC_TIPO_ADQUISICION
 * @author Nicolas Guaneme
 *
 */
public class TipoAdquisicionDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TIPO_ADQUISICION, NOMBRE,ACTIVO,ID_USUARIO_CREACION FROM SDB_ACC_TIPO_ADQUISICION  WHERE ID_TIPO_ADQUISICION = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_TIPO_ADQUISICION, NOMBRE,ACTIVO,ID_USUARIO_CREACION FROM SDB_ACC_TIPO_ADQUISICION";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_TIPO_ADQUISICION SET  NOMBRE=?, ACTIVO=?,ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP ,IP_MODIFICACION = ? WHERE ID_TIPO_ADQUISICION = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_TIPO_ADQUISICION(ID_TIPO_ADQUISICION, NOMBRE,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES(?, ?, ?, ?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_TIPO_ADQUISICION, NOMBRE,ACTIVO,ID_USUARIO_CREACION FROM SDB_ACC_TIPO_ADQUISICION WHERE ACTIVO = ? ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_TIPO_ADQUISICION_ID_TIPO_ADQUISICION.NEXTVAL FROM DUAL";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TIPO_ADQUISICION
	 *
	 * @return Collection de TipoAdquisicion encontrada
	 * @throws B2BException
	 */
	public Collection<TipoAdquisicion> findAll()
	    throws B2BException
	{
		return findAll(null);
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TIPO_ADQUISICION
	 * que se encuentren activos
	 *
	 * @param as_estado de String con determinando si debe buscar los registros por un parametro especifico.
	 * @return Collection de TipoAdquisicion encontrada
	 * @throws B2BException
	 */
	public Collection<TipoAdquisicion> findAll(String as_estado)
	    throws B2BException
	{
		Collection<TipoAdquisicion> ls_object;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;
		boolean                     lb_activo;

		lps_ps        = null;
		lrs_rs        = null;
		ls_object     = new ArrayList<TipoAdquisicion>();
		lb_activo     = StringUtils.isValidString(as_estado);

		try
		{
			lps_ps = getConnection().prepareStatement(lb_activo ? cs_FIND_ALL_ACTIVO : cs_FIND_ALL);

			if(lb_activo)
				lps_ps.setString(1, as_estado);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
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

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_ADQUISICION por un id especifico
	 *
	 * @param at_param objeto del cual se extrae el id para consultar en la base de datos
	 * @param ab_b indica si se desea que la consulta coíncida con un ID_TIPO_ADQUISICION específico
	 * @return TipoAdquisicion encontrado
	 * @throws B2BException
	 */
	public TipoAdquisicion findById(TipoAdquisicion at_param, boolean ab_b)
	    throws B2BException
	{
		TipoAdquisicion             lota_object;
		Collection<TipoAdquisicion> lcta_ta;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;
		StringBuilder               lsb_sb;
		int                         li_column;

		li_column       = 1;
		lcta_ta         = null;
		lps_ps          = null;
		lrs_rs          = null;
		lsb_sb          = new StringBuilder();
		lota_object     = new TipoAdquisicion();
		lcta_ta         = new ArrayList<TipoAdquisicion>();

		try
		{
			lsb_sb.append(cs_FIND_ALL);

			if(ab_b)
				lsb_sb.append(" WHERE ID_TIPO_ADQUISICION = ?");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			if(ab_b)
				lps_ps.setString(li_column++, at_param.getIdTipoAdquisicion());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_ta.add(getObjetFromResultSet(lrs_rs));

			if(CollectionUtils.isValidCollection(lcta_ta))
				lota_object.setInfoAll(lcta_ta);
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

		return lota_object;
	}

	/**
	 * Método para encontrar por la llave un registro de la tabla SDB_PGN_TIPO_ADQUISICION
	 * @param as_param objeto del cual se extrae el id para consultar en la base de datos
	 * @return
	 * @throws B2BException
	 */
	public TipoAdquisicion findById(String as_param)
	    throws B2BException
	{
		TipoAdquisicion   lta_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps         = null;
		lrs_rs         = null;
		lta_object     = null;

		if(StringUtils.isValidString(as_param))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lta_object = (getObjetFromResultSet(lrs_rs));
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

		return lta_object;
	}

/**
 * Método para insertar o actualizar registros en la base de datos para la tabla SDB_ACC_TIPO_ADQUISICION
 * @param at_param objeto a insertar o modificar
 * @param ab_query indica si se desea insertar o actualizar(true inserta, false modifica)
 * @throws B2BException
 */
	public void insertOrUpdate(TipoAdquisicion at_param, boolean ab_query)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		PreparedStatement lps_secuencia;
		ResultSet         lrs_rs;

		lps_ps            = null;
		lps_secuencia     = null;
		lrs_rs            = null;

		if(at_param != null)
		{
			try
			{
				int        li_column;
				Connection lc_c;

				li_column     = 1;
				lc_c          = getConnection();
				lps_ps        = lc_c.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							at_param.setIdTipoAdquisicion(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, at_param.getIdTipoAdquisicion());
						}
						else
							throw new B2BException(ErrorKeys.SIN_SECUENCIA_TIPO_ADQUISICION);
					}
				}

				lps_ps.setString(li_column++, at_param.getNombre());
				lps_ps.setString(li_column++, at_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
				}

				else
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setString(li_column++, at_param.getIdTipoAdquisicion());
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
 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de tipo adquisición
 * @param ars_rs objeto que recoge el resultado de la consulta
 * @return
 * @throws SQLException
 */
	private TipoAdquisicion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoAdquisicion ls_tipoAdquisicion;

		ls_tipoAdquisicion = new TipoAdquisicion();

		ls_tipoAdquisicion.setIdTipoAdquisicion(ars_rs.getString("ID_TIPO_ADQUISICION"));
		ls_tipoAdquisicion.setNombre(ars_rs.getString("NOMBRE"));
		ls_tipoAdquisicion.setActivo(ars_rs.getString("ACTIVO"));
		ls_tipoAdquisicion.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));

		return ls_tipoAdquisicion;
	}
}
