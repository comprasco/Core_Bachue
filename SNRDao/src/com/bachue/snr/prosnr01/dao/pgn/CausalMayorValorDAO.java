package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.CausalMayorValor;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de acceso a datos que permite administrar los datos de la tabla SDB_PGN_CAUSAL_MAYOR_VALOR.
 *
 * @author hcastaneda
 */
public class CausalMayorValorDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL_BY_STATE. */
	private static final String cs_FIND_ALL_BY_STATE = "SELECT * FROM SDB_PGN_CAUSAL_MAYOR_VALOR WHERE ACTIVO=?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CAUSAL_MAYOR_VALOR ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CAUSAL_MAYOR_VALOR WHERE ID_CAUSAL_MAYOR_VALOR = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAUSAL_MAYOR_VALOR(ID_CAUSAL_MAYOR_VALOR, NOMBRE, DESCRIPCION, ACTIVO, ACCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAUSAL_MAYOR_VALOR SET NOMBRE=?, DESCRIPCION=?, ACTIVO=?, ACCION=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_CAUSAL_MAYOR_VALOR=?";

	/** Constante cs_SECUENCE_CMV. */
	private static final String cs_SECUENCE_CMV = "SELECT SEC_PGN_CAUSAL_MAYOR_VALOR_ID_CAUSAL_MAYOR_VALOR.NEXTVAL FROM DUAL";

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_CAUSAL_MAYOR_VALOR
	 * @return
	 * @throws B2BException
	 */
	public Collection<CausalMayorValor> findAll()
	    throws B2BException
	{
		Collection<CausalMayorValor> lccmv_cmv;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lccmv_cmv     = new ArrayList<CausalMayorValor>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccmv_cmv.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lccmv_cmv))
			lccmv_cmv = null;

		return lccmv_cmv;
	}

	/**
	 * Metodo que se encarga de consultar todos los registros de la tabla SDB_PGN_CAUSAL_MAYOR_VALOR con un estado en particular.
	 *
	 * @param as_estado Argumento que determina el estado a consultar, puede ser S o N
	 * @return Colección de datos de la tabla SDB_PGN_CAUSAL_MAYOR_VALOR que cumple con las condiciones enviadas como argumento.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CausalMayorValor> findAllByState(String as_estado)
	    throws B2BException
	{
		Collection<CausalMayorValor> lccmv_datos;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lccmv_datos     = new ArrayList<CausalMayorValor>();
		lps_ps          = null;
		lrs_rs          = null;

		if(StringUtils.isValidString(as_estado))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_STATE);

				lps_ps.setString(1, as_estado);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccmv_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByState", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccmv_datos.isEmpty())
			lccmv_datos = null;

		return lccmv_datos;
	}

	/**
	 *Metodo para encontrar  los registros existentes en la tabla SDB_PGN_CAUSAL_MAYOR_VALOR buscados por ID_CAUSAL_MAYOR_VALOR
	 * @param atd_param
	 * @return
	 * @throws B2BException
	 */
	public CausalMayorValor findById(CausalMayorValor atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdCausalMayorValor()) : null);
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @return CausalMayorValor resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CausalMayorValor findById(long as_param)
	    throws B2BException
	{
		CausalMayorValor ltd_return;
		ltd_return = null;

		if(as_param != 0)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, String.valueOf(as_param));

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
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_CAUSAL_MAYOR_VALOR
	 */
	public void insert(CausalMayorValor atd_param)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_SECUENCE_CMV);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							atd_param.setIdCausalMayorValor(NumericUtils.getLong(lo_o.toString()));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}
				}

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setLong(li_column++, atd_param.getIdCausalMayorValor());
				lps_ps.setString(li_column++, atd_param.getNombre());
				lps_ps.setString(li_column++, atd_param.getDescripcion());
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getAccion());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, atd_param.getIpCreacion());

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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_CAUSAL_MAYOR_VALOR
	 */
	public void update(CausalMayorValor atd_param)
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

				lps_ps.setString(li_column++, atd_param.getNombre());
				lps_ps.setString(li_column++, atd_param.getDescripcion());
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getAccion());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());
				lps_ps.setLong(li_column++, atd_param.getIdCausalMayorValor());

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
	 * Metodo que se encarga de llenar un objeto de tipo CausalMayorValor con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto CausalMayorValor
	 * @return Objeto de tipo CausalMayorValor con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CausalMayorValor getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CausalMayorValor lc_datos;

		lc_datos = new CausalMayorValor();

		lc_datos.setIdCausalMayorValor(ars_rs.getLong("ID_CAUSAL_MAYOR_VALOR"));
		lc_datos.setNombre(ars_rs.getString("NOMBRE"));
		lc_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));
		lc_datos.setAccion(ars_rs.getString("ACCION"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
