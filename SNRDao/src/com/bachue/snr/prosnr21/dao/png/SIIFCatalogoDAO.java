package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.SIIFCatalogo;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_SIIF_CATALOGO.
 *
 * @author dbeltran
 */
public class SIIFCatalogoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_SIIF_CATALOGO";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_SIIF_CATALOGO WHERE ID_SIIF_CATALOGO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_SIIF_CATALOGO (ID_SIIF_CATALOGO, NEMOTECNICO, NOMBRE, CODIGO, DESCRIPCION, ARCHIVO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES (?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_SIIF_CATALOGO SET NEMOTECNICO=?, NOMBRE=?, CODIGO=?, DESCRIPCION=?, ARCHIVO=?, ACTIVO=?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_SIIF_CATALOGO=?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_SIIF_CATALOGO_ID_SIIF_CATALOGO.NEXTVAL FROM DUAL";

	/**
	 * Find all.
	 *
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<SIIFCatalogo> findAll()
	    throws B2BException
	{
		Collection<SIIFCatalogo> lcsc_cpr;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcsc_cpr     = new ArrayList<SIIFCatalogo>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsc_cpr.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lcsc_cpr))
			lcsc_cpr = null;

		return lcsc_cpr;
	}

	/**
	 * Find by id.
	 *
	 * @param as_param the as param
	 * @return the con archivo
	 * @throws B2BException the b 2 B exception
	 */
	public SIIFCatalogo findById(String as_param)
	    throws B2BException
	{
		SIIFCatalogo lsc_return;

		lsc_return = null;

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
					lsc_return = getObjetFromResultSet(lrs_rs);
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

		return lsc_return;
	}

	/**
	 * Insert.
	 *
	 * @param asc_param the apc corte
	 * @throws B2BException the b 2 B exception
	 */
	public void insert(SIIFCatalogo asc_param)
	    throws B2BException
	{
		if(asc_param != null)
		{
			PreparedStatement lps_insercion;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_secuencia;

			lps_insercion     = null;
			lps_secuencia     = null;
			lrs_secuencia     = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_insercion     = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
				lrs_secuencia     = lps_secuencia.executeQuery();

				if(lrs_secuencia.next())
				{
					Object lo_o;

					lo_o = lrs_secuencia.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
						asc_param.setIdSiifCatalogo(lo_o.toString());
					else
						throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
				}

				lps_insercion.setString(li_column++, asc_param.getNemotecnico());
				lps_insercion.setString(li_column++, asc_param.getNombre());
				lps_insercion.setString(li_column++, asc_param.getCodigo());
				lps_insercion.setString(li_column++, asc_param.getDescripcion());
				lps_insercion.setString(li_column++, asc_param.getArchivo());
				lps_insercion.setString(li_column++, asc_param.getActivo());
				lps_insercion.setString(li_column++, asc_param.getIdUsuarioCreacion());
				lps_insercion.setString(li_column++, asc_param.getIpCreacion());

				lps_insercion.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_insercion);
				close(lps_secuencia);
				close(lrs_secuencia);
			}
		}
	}

	/**
	 * Update.
	 *
	 * @param asc_param the apc corte
	 * @throws B2BException the b 2 B exception
	 */
	public void update(SIIFCatalogo asc_param)
	    throws B2BException
	{
		if(asc_param != null)
		{
			PreparedStatement lps_update;

			lps_update = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_update        = lc_connection.prepareStatement(cs_UPDATE);

				lps_update.setString(li_column++, asc_param.getNemotecnico());
				lps_update.setString(li_column++, asc_param.getNombre());
				lps_update.setString(li_column++, asc_param.getCodigo());
				lps_update.setString(li_column++, asc_param.getDescripcion());
				lps_update.setString(li_column++, asc_param.getArchivo());
				lps_update.setString(li_column++, asc_param.getActivo());
				lps_update.setString(li_column++, asc_param.getIdUsuarioModificacion());
				lps_update.setString(li_column++, asc_param.getIpModificacion());
				lps_update.setString(li_column++, asc_param.getIdSiifCatalogo());

				lps_update.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_update);
			}
		}
	}

	/**
	 * Gets the objet from result set.
	 *
	 * @param ars_rs the ars rs
	 * @return the objet from result set
	 * @throws SQLException the SQL exception
	 */
	private SIIFCatalogo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SIIFCatalogo lc_datos;

		lc_datos = new SIIFCatalogo();

		lc_datos.setIdSiifCatalogo(ars_rs.getString("ID_SIIF_CATALOGO"));
		lc_datos.setNemotecnico(ars_rs.getString("NEMOTECNICO"));
		lc_datos.setNombre(ars_rs.getString("NOMBRE"));
		lc_datos.setCodigo(ars_rs.getString("CODIGO"));
		lc_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lc_datos.setArchivo(ars_rs.getString("ARCHIVO"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
