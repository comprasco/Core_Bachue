package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoArea;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_TIPO_AREA.
 *
 * @author Carlos Calderón
 */
public class TipoAreaDAO extends BaseDAO implements IBase<TipoArea>
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_AREA";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_AREA WHERE ID_TIPO_AREA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_AREA SET DESCRIPCION = ?, ACTIVO=?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TIPO_AREA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_AREA (ID_TIPO_AREA, DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_TIPO_AREA_ID_TIPO_AREA.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoArea consultando todo en la tabla
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoArea
	 */
	public Collection<TipoArea> findAll()
	    throws B2BException
	{
		Collection<TipoArea> lcta_ta;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcta_ta     = new ArrayList<TipoArea>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_ta.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcta_ta))
			lcta_ta = null;

		return lcta_ta;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un idTipoArea específico.
	 *
	 * @param ata_param correspondiente al valor del tipo de objeto TipoArea
	 * @return devuelve el valor del objeto tipo area
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public TipoArea findById(TipoArea ata_param)
	    throws B2BException
	{
		TipoArea          ltr_pt;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ltr_pt     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, ata_param.getIdTipoArea());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ltr_pt = getObjetFromResultSet(lrs_rs);
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

		return ltr_pt;
	}

	/**
	 * Metodo para insertar o actualizar un registro en la base de datos de la tabla SDB_PGN_TIPO_AREA.
	 *
	 * @param ata_param correspondiente al valor del tipo de objeto TipoArea
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(TipoArea ata_param, boolean ab_query)
	    throws B2BException
	{
		if(ata_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							ata_param.setIdTipoArea(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, ata_param.getIdTipoArea());
						}
						else
							throw new B2BException(ErrorKeys.PGN_TIPO_AREA_SEQUENCE);
					}
				}

				lps_ps.setString(li_column++, ata_param.getDescripcion());
				lps_ps.setString(li_column++, ata_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ata_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ata_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ata_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ata_param.getIpModificacion());
					lps_ps.setString(li_column++, ata_param.getIdTipoArea());
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
	 * Método para la obtencion del objeto TipoArea.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de TipoArea
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoArea
	 */
	private TipoArea getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoArea lta_ta;

		lta_ta = new TipoArea();

		lta_ta.setIdTipoArea(ars_rs.getString("ID_TIPO_AREA"));
		lta_ta.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lta_ta.setActivo(ars_rs.getString("ACTIVO"));
		lta_ta.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lta_ta.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lta_ta.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lta_ta.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lta_ta.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lta_ta.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lta_ta;
	}
}
