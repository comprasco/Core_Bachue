package com.bachue.snr.prosnr01.dao.pgn;

import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionListaCausalesCausal;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_CAUSAL_CORRECCION.
 *
 * @author Gabriel Arias
 */
public class CausalCorreccionDAO extends BaseDAO implements IBase<CausalCorreccion>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_CAUSAL_CORRECCION,NOMBRE,DESCRIPCION,ACTIVO,GRUPO_CAUSAL,ID_USUARIO_CREACION,"
		+ "FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_CAUSAL_CORRECCION "
		+ "WHERE ID_CAUSAL_CORRECCION = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAUSAL_CORRECCION SET NOMBRE = ?, DESCRIPCION = ?, ACTIVO = ?, GRUPO_CAUSAL = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CAUSAL_CORRECCION = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAUSAL_CORRECCION (ID_CAUSAL_CORRECCION,NOMBRE,DESCRIPCION,"
		+ "ACTIVO,GRUPO_CAUSAL,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_CAUSAL_CORRECCION,NOMBRE,DESCRIPCION,ACTIVO,GRUPO_CAUSAL,ID_USUARIO_CREACION,"
		+ "FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_CAUSAL_CORRECCION "
		+ "ORDER BY ID_CAUSAL_CORRECCION ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_CAUSAL_CORRECCION,NOMBRE,DESCRIPCION,ACTIVO,GRUPO_CAUSAL,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,"
		+ "ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_CAUSAL_CORRECCION WHERE ACTIVO = 'S' ORDER BY ID_CAUSAL_CORRECCION ASC";

	/** Constante cs_FIND_SECUENCE_CAUSAL_CORRECCION. */
	private static final String cs_FIND_SECUENCE_CAUSAL_CORRECCION = "SELECT SEC_PGN_CAUSAL_CORRECCION_ID_CAUSAL_CORRECCION.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo Collection de CausalCorreccion con todos los registros
	 *
	 * @return devuelve el valor del objeto collection de CausalCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CausalCorreccion> findAll()
	    throws B2BException
	{
		Collection<CausalCorreccion> lc_data;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lc_data     = new ArrayList<CausalCorreccion>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
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

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de CausalCorreccion filtrado por registros activos
	 *
	 * @return devuelve el valor del objeto collection de CausalCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CausalCorreccion> findAllActivo()
	    throws B2BException
	{
		Collection<CausalCorreccion> lc_data;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lc_data     = new ArrayList<CausalCorreccion>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
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

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Retorna el valor del objeto de tipo mapa de CausalCorreccion filtrado por registros activos
	 *
	 * @return devuelve el valor del objeto mapa de CausalCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Map<String, SolicitudCorreccion> findAllActivoMap()
	    throws B2BException
	{
		Map<String, SolicitudCorreccion> lmssc_data;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lmssc_data     = new HashMap<String, SolicitudCorreccion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				String ls_idCausal;
				String ls_grupoCausal;

				ls_idCausal        = StringUtils.getString(getLong(lrs_rs, "ID_CAUSAL_CORRECCION"));
				ls_grupoCausal     = lrs_rs.getString("GRUPO_CAUSAL");

				if(StringUtils.isValidString(ls_idCausal) && StringUtils.isValidString(ls_grupoCausal))
				{
					SolicitudCorreccion lsc_data;

					lsc_data = new SolicitudCorreccion();

					lsc_data.setSeleccionado(EstadoCommon.N);
					lsc_data.setGrupoCausal(ls_grupoCausal);

					lmssc_data.put(ls_idCausal, lsc_data);
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivoMap", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lmssc_data.isEmpty())
			lmssc_data = null;

		return lmssc_data;
	}

	/**
	 * Método encargado de consultar la información de las cuasales corrección.
	 *
	 * @return Colección de datos que contiene la información de las causales corrección.
	 * @throws B2BException
	 */
	public Collection<TipoSalidaObtenerCausalesCorreccionListaCausalesCausal> findAllData()
	    throws B2BException
	{
		Collection<TipoSalidaObtenerCausalesCorreccionListaCausalesCausal> lctsocclcc_return;
		PreparedStatement                                                  lps_ps;
		ResultSet                                                          lrs_rs;

		lctsocclcc_return     = new ArrayList<TipoSalidaObtenerCausalesCorreccionListaCausalesCausal>();
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctsocclcc_return.add(getCausalCorreccion(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllData", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lctsocclcc_return.isEmpty())
			lctsocclcc_return = null;

		return lctsocclcc_return;
	}

	/**
	 * Retorna el valor del objeto de tipo CausalCorreccion filtrado por ID
	 *
	 * @param al_param correspondiente al valor del tipo de objeto Long
	 * @return devuelve el valor del objeto causal correccion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CausalCorreccion findById(Long al_param)
	    throws B2BException
	{
		return (NumericUtils.isValidLong(al_param)) ? findById(NumericUtils.getBigInteger(al_param)) : null;
	}

	/** {@inheritdoc} */
	public CausalCorreccion findById(CausalCorreccion acc_param)
	    throws B2BException
	{
		return (acc_param != null) ? findById(acc_param.getIdCausalCorreccion()) : null;
	}

	/**
	 * Retorna el valor del objeto de tipo Find by id.
	 *
	 * @param lbi_param correspondiente al valor del tipo de objeto BigInteger
	 * @return devuelve el valor del objeto causal correccion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CausalCorreccion findById(BigInteger lbi_param)
	    throws B2BException
	{
		CausalCorreccion  le_e;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		le_e       = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(NumericUtils.isValidBigInteger(lbi_param))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setObject(1, lbi_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					le_e = getObjetFromResultSet(lrs_rs);
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

		return le_e;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(CausalCorreccion acc_param, boolean ab_query)
	    throws B2BException
	{
		if(acc_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			li_cont           = 1;
			lrs_rs            = null;
			lps_ps            = null;
			lps_secuencia     = null;

			try
			{
				Connection lc_connection;

				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_CAUSAL_CORRECCION);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							int li_int;

							li_int = NumericUtils.getInt((BigDecimal)lo_o);

							acc_param.setIdCausalCorreccion(NumericUtils.getBigInteger(li_int));
						}

						else
							throw new B2BException(ErrorKeys.PGN_CAUSAL_APROBACION_DEVOLUCION);
					}

					lps_ps.setString(li_cont++, StringUtils.getString(acc_param.getIdCausalCorreccion()));
				}

				lps_ps.setString(li_cont++, acc_param.getNombre());
				lps_ps.setString(li_cont++, acc_param.getDescripcion());
				lps_ps.setString(li_cont++, acc_param.getActivo());
				lps_ps.setString(li_cont++, acc_param.getGrupoCausal());

				if(ab_query)
				{
					lps_ps.setString(li_cont++, acc_param.getIdUsuarioCreacion());
					lps_ps.setString(li_cont++, acc_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_cont++, acc_param.getIdUsuarioModificacion());
					lps_ps.setString(li_cont++, acc_param.getIpModificacion());
					lps_ps.setObject(li_cont++, acc_param.getIdCausalCorreccion());
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
	 * Retorna el valor de CausalCorreccion en el objeto TipoSalidaObtenerCausalesCorreccionListaCausalesCausal
	 *
	 * @param ars_rs orrespondiente al valor del tipo de objeto ResultSet
	 * @return el valor de CausalCorreccion en el objeto TipoSalidaObtenerCausalesCorreccionListaCausalesCausal
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoSalidaObtenerCausalesCorreccionListaCausalesCausal getCausalCorreccion(ResultSet ars_rs)
	    throws SQLException
	{
		TipoSalidaObtenerCausalesCorreccionListaCausalesCausal ltsocclcc_return;

		ltsocclcc_return = new TipoSalidaObtenerCausalesCorreccionListaCausalesCausal();

		ltsocclcc_return.setIdCausalCorreccion(StringUtils.getString(ars_rs.getLong("ID_CAUSAL_CORRECCION")));
		ltsocclcc_return.setIdGrupoCausal(ars_rs.getString("GRUPO_CAUSAL"));
		ltsocclcc_return.setDescCausalCorreccion(ars_rs.getString("NOMBRE"));
		ltsocclcc_return.setDescGrupoCausal(ars_rs.getString("GRUPO_CAUSAL"));
		ltsocclcc_return.setDescripcionCausal(ars_rs.getString("DESCRIPCION"));

		return ltsocclcc_return;
	}

	/**
	 * Retorna el valor de CausalCorreccion
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de CausalCorreccion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CausalCorreccion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CausalCorreccion lcc_datos;
		lcc_datos = new CausalCorreccion();

		lcc_datos.setIdCausalCorreccion(
		    NumericUtils.getBigInteger(NumericUtils.getLongWrapper((ars_rs.getLong("ID_CAUSAL_CORRECCION"))))
		);
		lcc_datos.setNombre(ars_rs.getString("NOMBRE"));
		lcc_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lcc_datos.setGrupoCausal(ars_rs.getString("GRUPO_CAUSAL"));
		lcc_datos.setActivo(ars_rs.getString("ACTIVO"));
		lcc_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcc_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcc_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcc_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcc_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcc_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcc_datos;
	}
}
