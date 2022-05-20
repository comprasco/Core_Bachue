package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_SOLICITUD_CORRECCION
 *
 * @author hcastaneda
 */
public class SolicitudCorreccionDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_SOLICITUD_CORRECCION,ID_SOLICITUD,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_CAUSAL_CORRECCION,ID_TURNO,OBSERVACION,SELECCIONADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,"
		+ "FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_SOLICITUD_CORRECCION WHERE ID_SOLICITUD_CORRECCION = ?";

	/** Constante cs_FIND_BY_SOL_MAT_CIR_CAUSAL. */
	private static final String cs_FIND_BY_SOL_MAT_CIR_CAUSAL = "SELECT ID_SOLICITUD_CORRECCION,ID_SOLICITUD,ID_CIRCULO,ID_MATRICULA,ID_CAUSAL_CORRECCION,ID_TURNO,OBSERVACION,SELECCIONADO,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_SOLICITUD_CORRECCION WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? "
		+ "AND ID_MATRICULA = ? AND ID_CAUSAL_CORRECCION = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_CORRECCION (ID_SOLICITUD_CORRECCION,ID_SOLICITUD,ID_CIRCULO,ID_MATRICULA,ID_CAUSAL_CORRECCION,"
		+ "ID_TURNO,OBSERVACION,SELECCIONADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_CORRECCION SET ID_SOLICITUD=?,ID_CIRCULO=?,ID_MATRICULA=?,ID_CAUSAL_CORRECCION=?,ID_TURNO=?,"
		+ "OBSERVACION=?,SELECCIONADO=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_SOLICITUD_CORRECCION = ?";

	/** Constante cs_FIND_BY_SOLICITUD_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_SOLICITUD_CIRCULO_MATRICULA = "SELECT SC.* FROM SDB_ACC_SOLICITUD_CORRECCION SC INNER JOIN SDB_PGN_CAUSAL_CORRECCION CC ON CC.ID_CAUSAL_CORRECCION = SC.ID_CAUSAL_CORRECCION "
		+ " WHERE SC.ID_SOLICITUD = ? AND SC.ID_CIRCULO = ? AND SC.ID_MATRICULA = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_SOLICITUD_CORRECCION_ID_SOLICITUD_CORRECCION.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = "SELECT ID_SOLICITUD_CORRECCION,ID_SOLICITUD,ID_CIRCULO,ID_MATRICULA,ID_CAUSAL_CORRECCION,ID_TURNO,OBSERVACION,SELECCIONADO,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_SOLICITUD_CORRECCION WHERE ID_SOLICITUD = ?";

	/** Constante cs_DELETE_ALL. */
	private static final String cs_DELETE_ALL = "DELETE FROM SDB_ACC_SOLICITUD_CORRECCION WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_CIRCULO_MATRICULA_BY_SOLICITUD. */
	private static final String cs_FIND_CIRCULO_MATRICULA_BY_SOLICITUD = "SELECT ID_SOLICITUD,ID_CIRCULO,ID_MATRICULA FROM SDB_ACC_SOLICITUD_CORRECCION WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? GROUP BY ID_SOLICITUD,ID_CIRCULO,ID_MATRICULA";

	/**
	 * Retorna el valor de id causal.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de id causal
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	public BigInteger getIdCausal(ResultSet ars_rs)
	    throws SQLException
	{
		BigInteger lbi_return;
		Object     lo_o;

		lbi_return     = null;
		lo_o           = ars_rs.getObject("ID_CAUSAL_CORRECCION");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lbi_return = ((BigDecimal)lo_o).toBigInteger();

		return lbi_return;
	}

	/**
	 * Borra el registro por id solicitud, id circulo, id matricula
	 *
	 * @param asc_correccion correspondiente al valor del tipo de objeto SolicitudCorreccion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteAll(SolicitudCorreccion asc_correccion)
	    throws SQLException, B2BException
	{
		int               li_count;
		PreparedStatement lps_ps;

		li_count     = 1;
		lps_ps       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_DELETE_ALL);

			lps_ps.setString(li_count++, asc_correccion.getIdSolicitud());
			lps_ps.setString(li_count++, asc_correccion.getIdCirculo());
			lps_ps.setObject(li_count++, asc_correccion.getIdMatricula());

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "deleteAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Retorna el valor del objeto de SolicitudCorreccion.
	 *
	 * @param asc_param correspondiente al valor del tipo de objeto SolicitudCorreccion
	 * @return devuelve el valor de SolicitudCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudCorreccion
	 */
	public SolicitudCorreccion findById(SolicitudCorreccion asc_param)
	    throws B2BException
	{
		SolicitudCorreccion lsc_return;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lsc_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(asc_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setObject(1, asc_param.getIdSolicitudCorreccion());

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
	 * Retorna el valor del objeto de SolicitudCorreccion.
	 *
	 * @param asc_param correspondiente al valor del tipo de objeto SolicitudCorreccion
	 * @return devuelve el valor de SolicitudCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudCorreccion
	 */
	public SolicitudCorreccion findBySolCirMatCausal(SolicitudCorreccion asc_param)
	    throws B2BException
	{
		SolicitudCorreccion lsc_return;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lsc_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(asc_param != null)
		{
			try
			{
				int li_cont;
				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOL_MAT_CIR_CAUSAL);

				lps_ps.setString(li_cont++, asc_param.getIdSolicitud());
				lps_ps.setString(li_cont++, asc_param.getIdCirculo());
				lps_ps.setObject(li_cont++, asc_param.getIdMatricula());
				lps_ps.setObject(li_cont++, asc_param.getIdCausalCorreccion());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsc_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolCirMatCausal", lse_e);

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
	 * Retorna el valor del objeto de Collection de SolicitudCorreccion filtrado por solicitud.
	 *
	 * @param asc_param correspondiente al valor del tipo de objeto SolicitudCorreccion
	 * @return devuelve el valor de Collection de SolicitudCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudCorreccion
	 */
	public Collection<SolicitudCorreccion> findBySolicitud(SolicitudCorreccion asc_param)
	    throws B2BException
	{
		Collection<SolicitudCorreccion> lcsc_return;
		int                             li_count;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lcsc_return     = new ArrayList<SolicitudCorreccion>();
		li_count        = 1;
		lps_ps          = null;
		lrs_rs          = null;

		if(asc_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD);

				lps_ps.setString(li_count++, asc_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsc_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsc_return.isEmpty())
			lcsc_return = null;

		return lcsc_return;
	}

	/**
	 * Obtiene los causales de corrección asociados a un id círculo, id matrícula e id solicitud.
	 *
	 * @param asc_param Objeto contenedor de los datos a utilizar como filtro en la busqueda
	 * @param ab_seleccionado true para indicar que el causal de corrección a buscar fue seleccionado
	 * @param ab_grupoCausal true para buscar por un grupo causal específico
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudCorreccion
	 */
	public Collection<SolicitudCorreccion> findBySolicitudCirculoMatricula(
	    SolicitudCorreccion asc_param, boolean ab_seleccionado, boolean ab_grupoCausal
	)
	    throws B2BException
	{
		return (asc_param != null)
		? findBySolicitudCirculoMatricula(
		    asc_param.getIdSolicitud(), asc_param.getIdCirculo(), asc_param.getIdMatricula(), ab_seleccionado,
		    ab_grupoCausal
		) : null;
	}

	/**
	 * Obtiene los causales de corrección asociados a un id círculo, id matrícula e id solicitud.
	 *
	 * @param as_idSolicitud id de la solicitud a la cual estan asociados los registros
	 * @param as_idCirculo id del círculo al cual estan asociados los registros
	 * @param abi_idMatricula id de la matrícula a la cual estan asociados los registros
	 * @param ab_seleccionado true para indicar que el causal de corrección a buscar fue seleccionado
	 * @param ab_grupoCausal true para buscar por un grupo causal específico
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudCorreccion
	 */
	public Collection<SolicitudCorreccion> findBySolicitudCirculoMatricula(
	    String as_idSolicitud, String as_idCirculo, BigInteger abi_idMatricula, boolean ab_seleccionado,
	    boolean ab_grupoCausal
	)
	    throws B2BException
	{
		Collection<SolicitudCorreccion> lcsc_return;

		lcsc_return = new ArrayList<SolicitudCorreccion>();

		if(
		    StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo)
			    && NumericUtils.isValidBigInteger(abi_idMatricula)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_count;

				lsb_sb       = new StringBuilder(cs_FIND_BY_SOLICITUD_CIRCULO_MATRICULA);
				li_count     = 1;

				if(ab_seleccionado)
					lsb_sb.append(" AND NVL(SC.SELECCIONADO,'S') = 'S' ");

				if(ab_grupoCausal)
					lsb_sb.append(" AND CC.GRUPO_CAUSAL = 'ANOTACIONES' ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_idSolicitud);
				lps_ps.setString(li_count++, as_idCirculo);
				lps_ps.setObject(li_count++, abi_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsc_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitudCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsc_return.isEmpty())
			lcsc_return = null;

		return lcsc_return;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudCorreccion.
	 *
	 * @param asc_param correspondiente al valor del tipo de objeto SolicitudCorreccion
	 * @return devuelve el valor de Collection de SolicitudCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudCorreccion
	 */
	public Collection<SolicitudCorreccion> findBySolicitudCirculoMatricula(SolicitudCorreccion asc_param)
	    throws B2BException
	{
		return findBySolicitudCirculoMatricula(asc_param, false, false);
	}

	/**
	 * Retorna el valor del objeto de Map.
	 *
	 * @param asc_param correspondiente al valor del tipo de objeto SolicitudCorreccion
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Map<BigInteger, SolicitudCorreccion> findCheckBySolicitudCirculoMatricula(SolicitudCorreccion asc_param)
	    throws B2BException
	{
		Map<BigInteger, SolicitudCorreccion> llhm_return;
		int                                  li_count;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		llhm_return     = new LinkedHashMap<BigInteger, SolicitudCorreccion>();
		li_count        = 1;
		lps_ps          = null;
		lrs_rs          = null;

		if(asc_param != null)
		{
			try
			{
				boolean lb_onlySolicitud;
				String  ls_query;

				lb_onlySolicitud     = asc_param.isFindOnlySolicitud();
				ls_query             = (!lb_onlySolicitud) ? cs_FIND_BY_SOLICITUD_CIRCULO_MATRICULA : cs_FIND_BY_SOLICITUD;

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_count++, asc_param.getIdSolicitud());

				if(!lb_onlySolicitud)
				{
					lps_ps.setString(li_count++, asc_param.getIdCirculo());
					lps_ps.setObject(li_count++, asc_param.getIdMatricula());
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					BigInteger          lbi_dato;
					SolicitudCorreccion lsc_correccion;

					lbi_dato           = getIdCausal(lrs_rs);
					lsc_correccion     = getObjetFromResultSet(lrs_rs);

					if(NumericUtils.isValidBigInteger(lbi_dato))
						llhm_return.put(lbi_dato, lsc_correccion);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitudCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(llhm_return.isEmpty())
			llhm_return = null;

		return llhm_return;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudMatricula filtrado por id solicitud, id circulo.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de SolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public Collection<SolicitudMatricula> findCirculoMatriculaBySolicitud(String as_idSolicitud, String as_idCirculo)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsc_return;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcsc_return     = new ArrayList<SolicitudMatricula>();
		lps_ps          = null;
		lrs_rs          = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_CIRCULO_MATRICULA_BY_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);
				lps_ps.setString(li_column++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsc_return.add(getCirculoMatriculaFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findCirculoMatriculaBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsc_return.isEmpty())
			lcsc_return = null;

		return lcsc_return;
	}

	/**
	 * IInserta o actualiza el registro
	 *
	 * @param asc_param correspondiente al valor del tipo de objeto SolicitudCorreccion
	 * @param ab_insert correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(SolicitudCorreccion asc_param, boolean ab_insert)
	    throws B2BException
	{
		if(asc_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			li_cont           = 1;
			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;

				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_insert ? cs_INSERT : cs_UPDATE);

				if(ab_insert)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							asc_param.setIdSolicitudCorreccion(((BigDecimal)lo_o).toBigInteger());

							lps_ps.setObject(li_cont++, asc_param.getIdSolicitudCorreccion());
						}
					}
				}

				lps_ps.setString(li_cont++, asc_param.getIdSolicitud());
				lps_ps.setString(li_cont++, asc_param.getIdCirculo());
				lps_ps.setObject(li_cont++, asc_param.getIdMatricula());
				lps_ps.setObject(li_cont++, asc_param.getIdCausalCorreccion());
				lps_ps.setString(li_cont++, asc_param.getIdTurno());
				lps_ps.setString(li_cont++, asc_param.getObservacion());
				lps_ps.setString(li_cont++, asc_param.getSeleccionado());

				if(ab_insert)
				{
					lps_ps.setString(li_cont++, asc_param.getIdUsuarioCreacion());
					lps_ps.setString(li_cont++, asc_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_cont++, asc_param.getIdUsuarioModificacion());
					lps_ps.setString(li_cont++, asc_param.getIpModificacion());
				}

				if(!ab_insert)
					lps_ps.setObject(li_cont++, asc_param.getIdSolicitudCorreccion());

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
	 * Retorna el valor de circulo matricula
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de circulo matricula
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudMatricula getCirculoMatriculaFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudMatricula lsm_datos;
		lsm_datos = new SolicitudMatricula();

		lsm_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lsm_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsm_datos.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));

		return lsm_datos;
	}

	/**
	 * Retorna el valor de SolicitudCorreccion
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de SolicitudCorreccion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudCorreccion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudCorreccion lsc_datos;
		lsc_datos = new SolicitudCorreccion();

		Object lo_o;

		lo_o = ars_rs.getObject("ID_SOLICITUD_CORRECCION");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lsc_datos.setIdSolicitudCorreccion(((BigDecimal)lo_o).toBigInteger());

		lsc_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lsc_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));

		lo_o = ars_rs.getObject("ID_MATRICULA");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lsc_datos.setIdMatricula(((BigDecimal)lo_o).toBigInteger());

		lo_o = ars_rs.getObject("ID_CAUSAL_CORRECCION");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lsc_datos.setIdCausalCorreccion(((BigDecimal)lo_o).toBigInteger());

		lsc_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lsc_datos.setObservacion(ars_rs.getString("OBSERVACION"));
		lsc_datos.setSeleccionado(ars_rs.getString("SELECCIONADO"));
		lsc_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lsc_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lsc_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lsc_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lsc_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lsc_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lsc_datos;
	}
}
