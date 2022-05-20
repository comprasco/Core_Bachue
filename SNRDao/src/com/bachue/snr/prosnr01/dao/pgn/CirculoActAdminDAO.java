package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoActAdmin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_CIRCULO_ACT_ADMIN.
 *
 * @author Gabriel Arias
 */
public class CirculoActAdminDAO extends BaseDAO implements IBase<CirculoActAdmin>
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CIRCULO_ACT_ADMIN";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CIRCULO_ACT_ADMIN WHERE ID_CIRCULO = ? AND TIPO_EXPEDIENTE = ? AND VIGENCIA = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CIRCULO_ACT_ADMIN (ID_CIRCULO,TIPO_EXPEDIENTE,VIGENCIA,CONSECUTIVO,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CIRCULO_ACT_ADMIN SET CONSECUTIVO = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CIRCULO = ? AND TIPO_EXPEDIENTE = ? AND VIGENCIA = ?";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_CIRCULO_ACT_ADMIN.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CirculoActAdmin> findAll()
	    throws B2BException
	{
		Collection<CirculoActAdmin> lccaa_caa;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lccaa_caa     = new ArrayList<CirculoActAdmin>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccaa_caa.add(getObjetFromResultSet(lrs_rs));
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

		if(lccaa_caa.isEmpty())
			lccaa_caa = null;

		return lccaa_caa;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param acaa_param Objeto contenedor de los filtros a usar en la consulta
	 * @return CirculoActAdmin resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CirculoActAdmin findById(CirculoActAdmin acaa_param)
	    throws B2BException
	{
		CirculoActAdmin lmt_data;

		lmt_data = null;

		if(acaa_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, acaa_param.getIdCirculo());
				lps_ps.setString(li_contador++, acaa_param.getTipoExpediente());
				lps_ps.setString(li_contador++, acaa_param.getVigencia());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmt_data = getObjetFromResultSet(lrs_rs);
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

		return lmt_data;
	}

	/**
	 * Inserta o Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param acaa_param objeto contenedor de la información a insertar
	 * @param ab_query objeto boolean indicador de la insercion o modificacion de la información
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertOrUpdate(CirculoActAdmin acaa_param, boolean ab_query)
	    throws B2BException
	{
		if(acaa_param != null)
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
					lps_ps.setString(li_column++, acaa_param.getIdCirculo());
					lps_ps.setString(li_column++, acaa_param.getTipoExpediente());
					lps_ps.setString(li_column++, acaa_param.getVigencia());
				}

				setLong(lps_ps, acaa_param.getConsecutivo(), li_column++);
				lps_ps.setString(li_column++, acaa_param.isActivo() ? EstadoCommon.S : EstadoCommon.N);

				if(!ab_query)
				{
					lps_ps.setString(li_column++, acaa_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, acaa_param.getIpModificacion());
					lps_ps.setString(li_column++, acaa_param.getIdCirculo());
					lps_ps.setString(li_column++, acaa_param.getTipoExpediente());
					lps_ps.setString(li_column++, acaa_param.getVigencia());
				}
				else
				{
					lps_ps.setString(li_column++, acaa_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, acaa_param.getIpCreacion());
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

	private CirculoActAdmin getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CirculoActAdmin lmt_datos;

		lmt_datos = new CirculoActAdmin();

		lmt_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lmt_datos.setTipoExpediente(ars_rs.getString("TIPO_EXPEDIENTE"));
		lmt_datos.setVigencia(ars_rs.getString("VIGENCIA"));
		lmt_datos.setConsecutivo(getLong(ars_rs, "CONSECUTIVO"));
		lmt_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lmt_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lmt_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lmt_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lmt_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lmt_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		{
			String ls_activo;

			ls_activo = ars_rs.getString("ACTIVO");

			lmt_datos.setActivo(StringUtils.isValidString(ls_activo) && BooleanUtils.getBooleanValue(ls_activo));
			lmt_datos.setActivoString(ls_activo);
		}

		return lmt_datos;
	}
}
