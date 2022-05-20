package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.CausalNegacionCopias;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS
 *
 * @author Luis Chacón
 */
public class CausalNegacionCopiasDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CAUSAL_NEGACION_COPIAS WHERE ID_CAUSAL_NEGACION_COPIAS = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAUSAL_NEGACION_COPIAS (ID_CAUSAL_NEGACION_COPIAS, DESCRIPCION_CAUSAL_NEGACION_COPIAS, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO) VALUES(?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAUSAL_NEGACION_COPIAS SET DESCRIPCION_CAUSAL_NEGACION_COPIAS=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, ACTIVO=? WHERE ID_CAUSAL_NEGACION_COPIAS=?";

	/** Constante cs_SECUENCE. */
	private static final String cs_SECUENCE = "SELECT SEC_PGN_CAUSAL_NEGACION_COPIAS_ID_CAUSAL_NEGACION_COPIAS.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CAUSAL_NEGACION_COPIAS ORDER BY DESCRIPCION_CAUSAL_NEGACION_COPIAS ASC";

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS.
	 *
	 * @return devuelve el valor del objeto collection de CausalNegacionCopias
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CausalNegacionCopias
	 */
	public Collection<CausalNegacionCopias> findAll()
	    throws B2BException
	{
		Collection<CausalNegacionCopias> lctd_ctd;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lctd_ctd     = new ArrayList<CausalNegacionCopias>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctd_ctd.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lctd_ctd))
			lctd_ctd = null;

		return lctd_ctd;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS para un id_causal_negacion_copias específico.
	 *
	 * @param atd_param Objeto de tipo CausalNegacionCopias que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto tipo documental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CausalNegacionCopias
	 */
	public CausalNegacionCopias findById(CausalNegacionCopias atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdCausalNegacionCopias()) : null);
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS para un id_causal_negacion_copias específico.
	 *
	 * @param as_param Objeto de tipo String que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto tipo documental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CausalNegacionCopias
	 */
	public CausalNegacionCopias findById(String as_param)
	    throws B2BException
	{
		CausalNegacionCopias ltd_return;
		ltd_return = null;

		if(as_param != null)
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
					ltd_return = getObjetFromResultSet(lrs_rs);
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

		return ltd_return;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS
	 */
	public void insert(CausalNegacionCopias atd_param)
	    throws B2BException
	{
		if(atd_param != null)
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

				{
					lps_secuencia     = lc_connection.prepareStatement(cs_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							atd_param.setIdCausalNegacionCopias(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}
				}

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, atd_param.getIdCausalNegacionCopias());
				lps_ps.setString(li_column++, atd_param.getDescripcionCausalNegacionCopias());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, atd_param.getIpCreacion());
				lps_ps.setString(li_column++, atd_param.getActivo());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS
	 */
	public void update(CausalNegacionCopias atd_param)
	    throws B2BException
	{
		if(atd_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, atd_param.getDescripcionCausalNegacionCopias());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getIdCausalNegacionCopias());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo para la obtencion del objeto Tipo Documental a partir de un resulset.
	 *
	 * @param lrs_rs objeto contenedor de los resultados de la consulta
	 * @return Objeto de tipo CausalNegacionCopias intanciado con la informacion recuperada de base de datos
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private CausalNegacionCopias getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException, B2BException
	{
		CausalNegacionCopias lcnc_causalNegacionCopias;

		lcnc_causalNegacionCopias = new CausalNegacionCopias();

		lcnc_causalNegacionCopias.setIdCausalNegacionCopias(lrs_rs.getString("ID_CAUSAL_NEGACION_COPIAS"));
		lcnc_causalNegacionCopias.setDescripcionCausalNegacionCopias(lrs_rs.getString("DESCRIPCION_CAUSAL_NEGACION_COPIAS"));
		lcnc_causalNegacionCopias.setActivo(lrs_rs.getString("ACTIVO"));

		fillAuditoria(lrs_rs, lcnc_causalNegacionCopias);

		return lcnc_causalNegacionCopias;
	}
}
