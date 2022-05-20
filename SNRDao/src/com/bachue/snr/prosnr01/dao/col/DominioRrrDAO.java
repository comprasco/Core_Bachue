package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.col.DominioRrr;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_COL_DOMINIO_RRR.
 *
 * @author Gabriel Arias
 */
public class DominioRrrDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_COL_DOMINIO_RRR";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_COL_DOMINIO_RRR WHERE ID_DOMINIO_RRR=?";

	/** Constante cs_FIND_BY_ID_TIPO. */
	private static final String cs_FIND_BY_ID_TIPO = "SELECT * FROM SDB_COL_DOMINIO_RRR WHERE ID_DOMINIO_RRR = ? AND ID_TIPO_RRR = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_COL_DOMINIO_RRR SET ID_TIPO_RRR=?, DESCRIPCION=?, "
		+ "ACTIVO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_DOMINIO_RRR=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_COL_DOMINIO_RRR (ID_DOMINIO_RRR, ID_TIPO_RRR, "
		+ "DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_COL_DOMINIO_RRR_ID_DOMINIO_RRR.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo Collection de DominioRrr consultando todo en la tabla
	 *
	 * @return devuelve el valor del objeto collection de DominioRrr
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DominioRrr
	 */
	public Collection<DominioRrr> findAll()
	    throws B2BException
	{
		Collection<DominioRrr> lcpt_pt;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcpt_pt     = new ArrayList<DominioRrr>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpt_pt.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcpt_pt))
			lcpt_pt = null;

		return lcpt_pt;
	}

	/**
	 * Find by id.
	 *
	 * @param atr_param de atr param
	 * @return de dominio rrr
	 * @throws B2BException de b 2 B exception
	 */
	public DominioRrr findById(DominioRrr atr_param)
	    throws B2BException
	{
		if(atr_param != null)
			return findById(atr_param.getIdDominioRrr());
		else

			return null;
	}

	/**
	 * Find by id.
	 *
	 * @param as_param de as param
	 * @return de dominio rrr
	 * @throws B2BException de b 2 B exception
	 */
	public DominioRrr findById(String as_param)
	    throws B2BException
	{
		DominioRrr ltr_pt;

		ltr_pt = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

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
		}

		return ltr_pt;
	}

	/**
	 * Find by id tipo.
	 *
	 * @param as_idDominio de as id dominio
	 * @param as_idTipo de as id tipo
	 * @return de dominio rrr
	 * @throws B2BException de b 2 B exception
	 */
	public DominioRrr findByIdTipo(String as_idDominio, String as_idTipo)
	    throws B2BException
	{
		DominioRrr ltr_pt;

		ltr_pt = null;

		if(StringUtils.isValidString(as_idDominio) && StringUtils.isValidString(as_idTipo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TIPO);

				lps_ps.setString(li_column++, as_idDominio);
				lps_ps.setString(li_column++, as_idTipo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltr_pt = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ltr_pt;
	}

	/** {@inheritdoc} */
	public void insertOrUpdate(DominioRrr adrrr_param, boolean ab_query)
	    throws B2BException
	{
		if(adrrr_param != null)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							adrrr_param.setIdDominioRrr(((BigDecimal)lo_o).toString());

						lps_ps.setString(li_column++, adrrr_param.getIdDominioRrr());
					}
				}

				lps_ps.setString(li_column++, adrrr_param.getIdTipoRrr());
				lps_ps.setString(li_column++, adrrr_param.getDescripcion());
				lps_ps.setString(li_column++, adrrr_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, adrrr_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, adrrr_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, adrrr_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, adrrr_param.getIpModificacion());
					lps_ps.setString(li_column++, adrrr_param.getIdDominioRrr());
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
	 * metodo para la obtencion del objeto Dominio RRR.
	 *
	 * @param lrs_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de DominioRrr
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see DominioRrr
	 */
	private DominioRrr getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		DominioRrr ldr_dr;

		ldr_dr = new DominioRrr();

		ldr_dr.setIdDominioRrr(lrs_rs.getString("ID_DOMINIO_RRR"));
		ldr_dr.setIdTipoRrr(lrs_rs.getString("ID_TIPO_RRR"));
		ldr_dr.setDescripcion(lrs_rs.getString("DESCRIPCION"));
		ldr_dr.setActivo(lrs_rs.getString("ACTIVO"));
		ldr_dr.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ldr_dr.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));

		fillAuditoria(lrs_rs, ldr_dr);

		return ldr_dr;
	}
}
