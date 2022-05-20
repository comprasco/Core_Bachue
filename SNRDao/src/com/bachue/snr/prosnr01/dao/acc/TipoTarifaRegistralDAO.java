package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarifaRegistral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultasd de la tabla SDB_PGN_TIPO_TARIFA_REGISTRAL
 *
 * @author ccalderon
 */
public class TipoTarifaRegistralDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_TIPO_TARIFA_REGISTRAL ";

	/** Constante cs_INSERT_TIPO_TARIFA. */
	private static final String cs_INSERT_TIPO_TARIFA = "  INSERT INTO SDB_PGN_TIPO_TARIFA_REGISTRAL (ID_TIPO_TARIFA_REGISTRAL,VERSION,NOMBRE,PORCENTAJE,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)"
		+ " VALUES (?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_MODIFY_BY_ID. */
	private static final String cs_MODIFY_BY_ID = "  UPDATE SDB_PGN_TIPO_TARIFA_REGISTRAL SET NOMBRE = ? ,PORCENTAJE = ? ,FECHA_DESDE = ? ,FECHA_HASTA = ? , ACTIVO = ?,ID_USUARIO_MODIFICACION = ? ,FECHA_MODIFICACION = SYSTIMESTAMP ,IP_MODIFICACION = ? WHERE  ID_TIPO_TARIFA_REGISTRAL = ? AND VERSION = ? ";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = " SELECT * FROM SDB_PGN_TIPO_TARIFA_REGISTRAL WHERE ACTIVO='S' ORDER BY NOMBRE ASC";

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoTarifaRegistral con todos los registros
	 *
	 * @return devuelve el valor del objeto collection de TipoTarifaRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoTarifaRegistral
	 */
	public Collection<TipoTarifaRegistral> findAllActivo()
	    throws B2BException
	{
		Collection<TipoTarifaRegistral> lccr_ccr;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccr_ccr     = new ArrayList<TipoTarifaRegistral>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_ccr.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Retorna el valor del objeto de tipo TipoTarifaRegistral por id.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto TipoTarifaRegistral
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto tipo tarifa registral
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TipoTarifaRegistral findById(TipoTarifaRegistral aap_param, boolean ab_b)
	    throws B2BException
	{
		Collection<TipoTarifaRegistral> lttr_tipoTarifa;
		TipoTarifaRegistral             lotfr_return;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;
		StringBuilder                    lsbf_sbf;
		boolean                         lb_version;
		boolean                         lb_id;
		int                             li_contador;

		lttr_tipoTarifa     = new ArrayList<TipoTarifaRegistral>();
		lps_ps              = null;
		lrs_rs              = null;
		lsbf_sbf            = new StringBuilder();
		lb_version          = false;
		lb_id               = false;
		lotfr_return        = new TipoTarifaRegistral();

		try
		{
			li_contador = 1;
			lsbf_sbf.append(cs_FIND_BY_ID);

			if(ab_b)
			{
				lb_id          = aap_param.isValidTipoId();
				lb_version     = aap_param.isValidversion();

				if(lb_id)
					lsbf_sbf.append(" WHERE ID_TIPO_TARIFA_REGISTRAL = ? ");

				if(lb_version)
				{
					if(lb_id)
						lsbf_sbf.append(" AND VERSION = ?");
					else
						lsbf_sbf.append(" WHERE  VERSION = ?");
				}
			}

			lps_ps = getConnection().prepareStatement(lsbf_sbf.toString());

			if(ab_b)
			{
				if(lb_id)
					lps_ps.setString(li_contador++, aap_param.getIdTipoTarifa());

				if(lb_version)
					setLong(lps_ps, aap_param.getVersion(), li_contador++);
			}

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lttr_tipoTarifa.add(getObjetFromResultSet(lrs_rs));

			if(CollectionUtils.isValidCollection(lttr_tipoTarifa))
				lotfr_return.setInfoAll(lttr_tipoTarifa);
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

		return lotfr_return;
	}

	/**
	 * Retorna el valor del objeto de tipo TipoTarifaRegistral cuando se inserta un nuevo registro
	 *
	 * @param attr_tipoTarifa correspondiente al valor del tipo de objeto TipoTarifaRegistral
	 * @return devuelve el valor del objeto tipo tarifa registral
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TipoTarifaRegistral insertTipoTarifa(TipoTarifaRegistral attr_tipoTarifa)
	    throws B2BException
	{
		if(attr_tipoTarifa != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT_TIPO_TARIFA);

				lps_ps.setString(li_column++, attr_tipoTarifa.getIdTipoTarifa());
				setLong(lps_ps, attr_tipoTarifa.getVersion(), li_column++);
				lps_ps.setString(li_column++, attr_tipoTarifa.getNombre());
				setDouble(lps_ps, attr_tipoTarifa.getPorcentaje(), li_column++);
				setDate(lps_ps, attr_tipoTarifa.getFechaDesde(), li_column++);
				setDate(lps_ps, attr_tipoTarifa.getFechaHasta(), li_column++);
				lps_ps.setString(li_column++, attr_tipoTarifa.getActivo());
				lps_ps.setString(li_column++, attr_tipoTarifa.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, attr_tipoTarifa.getIp());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				getLog().error("insertTipoTarifa", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		return attr_tipoTarifa;
	}

	/**
	 * Modifica el registro por id.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto TipoTarifaRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modifyById(TipoTarifaRegistral aap_param)
	    throws B2BException
	{
		if(aap_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_MODIFY_BY_ID);

				lps_ps.setString(li_column++, aap_param.getNombre());
				setDouble(lps_ps, aap_param.getPorcentaje(), li_column++);
				setDate(lps_ps, aap_param.getFechaDesde(), li_column++);
				setDate(lps_ps, aap_param.getFechaHasta(), li_column++);
				lps_ps.setString(li_column++, aap_param.getActivo());
				lps_ps.setString(li_column++, aap_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aap_param.getIp());
				lps_ps.setString(li_column++, aap_param.getIdTipoTarifa());
				setLong(lps_ps, aap_param.getVersion(), li_column++);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "modifyById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de TipoTarifaRegistral
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoTarifaRegistral
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoTarifaRegistral getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoTarifaRegistral lttr_ttr;

		lttr_ttr = new TipoTarifaRegistral();

		lttr_ttr.setIdTipoTarifa(ars_rs.getString("ID_TIPO_TARIFA_REGISTRAL"));
		lttr_ttr.setVersion(getLong(ars_rs, "VERSION"));
		lttr_ttr.setNombre(ars_rs.getString("NOMBRE"));
		lttr_ttr.setPorcentaje(getDouble(ars_rs, "PORCENTAJE"));
		lttr_ttr.setActivo(ars_rs.getString("ACTIVO"));
		lttr_ttr.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lttr_ttr.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		lttr_ttr.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));
		lttr_ttr.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lttr_ttr.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lttr_ttr.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lttr_ttr.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lttr_ttr.setIpModificacion((ars_rs.getString("IP_MODIFICACION")));

		return lttr_ttr;
	}
}
