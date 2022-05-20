package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoPago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades de TipoPagoDAO.
 *
 * @author Julian Vaca
 */
public class TipoPagoDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_TIPO_PAGO ";

	/** Constante cs_INSERT_TIPO_PAGO. */
	private static final String cs_INSERT_TIPO_PAGO = " INSERT INTO SDB_ACC_TIPO_PAGO  (ID_TIPO_PAGO,DESCRIPCION,ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION)  VALUES (?,?,?,?,SYSTIMESTAMP)";

	/** Constante cs_MODIFY_BY_ID. */
	private static final String cs_MODIFY_BY_ID = " UPDATE SDB_ACC_TIPO_PAGO SET DESCRIPCION = ? ,ID_USUARIO_MODIFICACION = ? ,FECHA_MODIFICACION = SYSTIMESTAMP ,IP_MODIFICACION = ?  WHERE ID_TIPO_PAGO = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_TIPO_PAGO_ID_TIPO_PAGO.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de TipoPago.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto TipoPago
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de TipoPago
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoPago
	 */
	public TipoPago findById(TipoPago aap_param, boolean ab_b)
	    throws B2BException
	{
		Collection<TipoPago> lpr_Predio;
		TipoPago             lotp_return;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;
		StringBuilder        lsb_sb;

		lpr_Predio      = new ArrayList<TipoPago>();
		lps_ps          = null;
		lrs_rs          = null;
		lsb_sb          = new StringBuilder();
		lotp_return     = new TipoPago();

		try
		{
			int li_contador;
			li_contador = 1;

			lsb_sb.append(cs_FIND_ALL);

			if(ab_b)
				lsb_sb.append(" WHERE ID_TIPO_PAGO = ? ");
			else
				lsb_sb.append(" ORDER BY LENGTH(ID_TIPO_PAGO),ID_TIPO_PAGO ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			if(ab_b)
				lps_ps.setString(li_contador++, aap_param.getIdTipoPago());

			lrs_rs = lps_ps.executeQuery();

			if(!ab_b)
			{
				while(lrs_rs.next())
					lpr_Predio.add(getObjetFromResultSet(lrs_rs));

				if(CollectionUtils.isValidCollection(lpr_Predio))
					lotp_return.setInfoAll(lpr_Predio);
			}
			else
			{
				while(lrs_rs.next())
					lotp_return = getObjetFromResultSet(lrs_rs);
			}
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

		return lotp_return;
	}

	/**
	 * Retorna el valor del objeto de TipoPago una vez se haya insertado el registro
	 *
	 * @param atp_tipoPago correspondiente al valor del tipo de objeto TipoPago
	 * @return devuelve el valor de TipoPago
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoPago
	 */
	public TipoPago insertTipoPago(TipoPago atp_tipoPago)
	    throws B2BException
	{
		if(atp_tipoPago != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_c;
				int        li_column;

				lc_c              = getConnection();
				li_column         = 1;
				lps_ps            = lc_c.prepareStatement(cs_INSERT_TIPO_PAGO);
				lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);
				lrs_rs            = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				lps_ps.setString(li_column++, atp_tipoPago.getDescripcion());
				lps_ps.setString(li_column++, atp_tipoPago.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, atp_tipoPago.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				getLog().error("insertTipoPago", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return atp_tipoPago;
	}

	/**
	 * Actualiza el registro por id.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto TipoPago
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modifyById(TipoPago aap_param)
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

				lps_ps.setString(li_column++, aap_param.getDescripcion());
				lps_ps.setString(li_column++, aap_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_param.getIpModificacion());
				lps_ps.setString(li_column++, aap_param.getIdTipoPago());

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
	 * Retorna el valor de TipoPago
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoPago
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoPago getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoPago lotp_tp;

		lotp_tp = new TipoPago();

		lotp_tp.setIdTipoPago(ars_rs.getString("ID_TIPO_PAGO"));
		lotp_tp.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lotp_tp.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lotp_tp.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return lotp_tp;
	}
}
