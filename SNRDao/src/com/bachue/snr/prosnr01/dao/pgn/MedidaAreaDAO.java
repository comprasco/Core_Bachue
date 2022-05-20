package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_MEDIDA_AREA.
 *
 * @author Sebastian Tafur
 */
public class MedidaAreaDAO extends BaseDAO implements IBase<MedidaArea>
{
	/** Constante cs_FIND_BY_CODIGO. */
	private static final String cs_FIND_BY_CODIGO = "SELECT * FROM SDB_PGN_MEDIDA_AREA  WHERE CODIGO=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_MEDIDA_AREA  WHERE ID_MEDIDA_AREA=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_MEDIDA_AREA SET CODIGO=?,DESCRIPCION=?, "
		+ " ESTADO=?,ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP"
		+ " WHERE ID_MEDIDA_AREA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_MEDIDA_AREA("
		+ "ID_MEDIDA_AREA,CODIGO,DESCRIPCION,ESTADO,IP_CREACION,ID_USUARIO_CREACION,FECHA_CREACION)"
		+ "VALUES(?, ?, ?, ?, ?, ?,SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_MEDIDA_AREA";

	/** Constante cs_FIND_SECUENCE_MEDIDA_AREA. */
	private static final String cs_FIND_SECUENCE_MEDIDA_AREA = "SELECT SEC_PGN_MEDIDA_AREA_ID_MEDIDA_AREA.NEXTVAL FROM DUAL";

/**
 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_MEDIDA_AREA.
 *
 * @param ab_activo correspondiente al valor del tipo de objeto boolean
 * @return devuelve el valor del objeto collection
 * @throws B2BException Señala que se ha producido una excepción
 */
	public Collection<MedidaArea> findAll(boolean ab_activo)
	    throws B2BException
	{
		Collection<MedidaArea> lccad_ccad;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lccad_ccad     = new ArrayList<MedidaArea>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append(cs_FIND_ALL);

			if(ab_activo)
				lsb_sb.append(" WHERE ESTADO = 'A'");

			lsb_sb.append(" ORDER BY CODIGO ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccad_ccad.add(getObjetFromResultSet(lrs_rs));
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

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_MEDIDA_AREA
	 * que coíncida con un CODIGO específico.
	 *
	 * @param ama_param objeto del cual se extrae el id para realizar la consulta en la base de datos
	 * @return devuelve el valor del objeto medida area
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public MedidaArea findByCodigo(MedidaArea ama_param)
	    throws B2BException
	{
		MedidaArea        lma_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lma_object     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(ama_param != null)
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CODIGO);

				lps_ps.setString(1, ama_param.getDescripcion());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lma_object = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByCodigo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lma_object;
	}

	/** {@inheritdoc} */
	public MedidaArea findById(MedidaArea ama_param)
	    throws B2BException
	{
		return (ama_param != null) ? findById(ama_param.getIdMedidaArea()) : null;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_MEDIDA_AREA
	 * que coíncida con un ID_MEDIDA_AREA específico.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto medida area
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public MedidaArea findById(String as_param)
	    throws B2BException
	{
		MedidaArea        lma_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lma_object     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lma_object = getObjetFromResultSet(lrs_rs);
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

		return lma_object;
	}

	/**
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto MedidaArea
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(MedidaArea ac_parametros, boolean ab_query)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_MEDIDA_AREA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ac_parametros.setIdMedidaArea(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}

					lps_ps.setString(li_column++, ac_parametros.getIdMedidaArea());
				}

				lps_ps.setString(li_column++, ac_parametros.getCodigo());
				lps_ps.setString(li_column++, ac_parametros.getDescripcion());
				lps_ps.setString(li_column++, ac_parametros.getEstado());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIdMedidaArea());
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
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de medida area.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de objet from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private MedidaArea getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		MedidaArea lma_datos;

		lma_datos = new MedidaArea();

		lma_datos.setIdMedidaArea(ars_rs.getString("ID_MEDIDA_AREA"));
		lma_datos.setCodigo(ars_rs.getString("CODIGO"));
		lma_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lma_datos.setEstado(ars_rs.getString("ESTADO"));
		lma_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lma_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lma_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lma_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lma_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lma_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lma_datos;
	}
}
