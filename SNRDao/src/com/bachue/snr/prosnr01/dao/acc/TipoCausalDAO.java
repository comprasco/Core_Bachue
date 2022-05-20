package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_TIPO_CAUSAL
 *
 * @author garias
 */
public class TipoCausalDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_CAUSAL, ID_PROCESO, DESCRIPCION, ESTADO_CAUSAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_SUBPROCESO, PLANTILLA, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_ACC_TIPO_CAUSAL WHERE ESTADO_CAUSAL = 'A' ORDER BY PLANTILLA DESC";

	/** Constante cs_FIND_SPECIFIC_ACTIVO. */
	private static final String cs_FIND_SPECIFIC_ACTIVO = "SELECT ID_CAUSAL, ID_PROCESO, DESCRIPCION, ESTADO_CAUSAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_SUBPROCESO, PLANTILLA, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_ACC_TIPO_CAUSAL WHERE ID_SUBPROCESO=2 AND ESTADO_CAUSAL = 'A' ORDER BY PLANTILLA DESC";

	/** Constante cs_FIND_BY_PROC_SUBPROC. */
	private static final String cs_FIND_BY_PROC_SUBPROC = "SELECT ID_CAUSAL, ID_PROCESO, DESCRIPCION, ESTADO_CAUSAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_SUBPROCESO, PLANTILLA, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_ACC_TIPO_CAUSAL WHERE ID_PROCESO = ? AND ID_SUBPROCESO = ? AND ESTADO_CAUSAL = 'A'";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_CAUSAL, ID_PROCESO, DESCRIPCION, ESTADO_CAUSAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_SUBPROCESO, PLANTILLA, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_ACC_TIPO_CAUSAL ORDER BY PLANTILLA DESC";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_CAUSAL, ID_PROCESO, DESCRIPCION, ESTADO_CAUSAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_SUBPROCESO, PLANTILLA, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_ACC_TIPO_CAUSAL WHERE ID_CAUSAL=?";

	/** Constante cs_UPDATE_TIPO_CAUSAL. */
	private static final String cs_UPDATE_TIPO_CAUSAL = "UPDATE SDB_ACC_TIPO_CAUSAL SET ID_PROCESO=?, DESCRIPCION=?, ESTADO_CAUSAL=?, VERSION=?, ID_SUBPROCESO=?, PLANTILLA=?,  ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?  WHERE ID_CAUSAL=?";

	/** Constante cs_INSERT_TIPO_CAUSAL. */
	private static final String cs_INSERT_TIPO_CAUSAL = "INSERT INTO SDB_ACC_TIPO_CAUSAL (ID_CAUSAL, ID_PROCESO, DESCRIPCION, ESTADO_CAUSAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_SUBPROCESO, PLANTILLA, IP_CREACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/**
	 * Retorna el valor del objeto de Collection de TipoCausal.
	 *
	 * @return devuelve el valor de Collection de TipoCausal
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<TipoCausal> findAllTiposCausales()
	    throws B2BException
	{
		Collection<TipoCausal> lctc_tiposCausales;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lctc_tiposCausales     = new ArrayList<TipoCausal>();
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctc_tiposCausales.add(findTiposCausales(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllTiposCausales", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lctc_tiposCausales.isEmpty())
			lctc_tiposCausales = null;

		return lctc_tiposCausales;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoCausal.
	 *
	 * @param ab_bandera correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de TipoCausal
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<TipoCausal> findAllTiposCausalesActivos(boolean ab_bandera)
	    throws B2BException
	{
		Collection<TipoCausal> lctc_tiposCausales;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lctc_tiposCausales     = new ArrayList<TipoCausal>();
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			if(ab_bandera)
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);
			else
				lps_ps = getConnection().prepareStatement(cs_FIND_SPECIFIC_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctc_tiposCausales.add(findTiposCausales(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllTiposCausalesActivos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lctc_tiposCausales.isEmpty())
			lctc_tiposCausales = null;

		return lctc_tiposCausales;
	}

	/**
	 * Consulta todos los tipos causales asociados a un id proceso y subproceso específicos.
	 *
	 * @param as_idProceso id del proceso asociado al causal
	 * @param as_idSubproceso id del subproceso asociado al causal
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoCausal
	 */
	public Collection<TipoCausal> findAllTiposCausalesActivosByProcSubproc(String as_idProceso, String as_idSubproceso)
	    throws B2BException
	{
		Collection<TipoCausal> lctc_tiposCausales;

		lctc_tiposCausales = new ArrayList<TipoCausal>();

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubproceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PROC_SUBPROC);

				lps_ps.setString(li_cont++, as_idProceso);
				lps_ps.setString(li_cont++, as_idSubproceso);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctc_tiposCausales.add(findTiposCausales(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllTiposCausalesActivosByProcSubproc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lctc_tiposCausales.isEmpty())
			lctc_tiposCausales = null;

		return lctc_tiposCausales;
	}

	/**
	 * Retorna el valor del objeto de TipoCausal.
	 *
	 * @param atc_parametros correspondiente al valor del tipo de objeto TipoCausal
	 * @return devuelve el valor de TipoCausal
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoCausal
	 */
	public TipoCausal findById(TipoCausal atc_parametros)
	    throws B2BException
	{
		return (atc_parametros != null) ? findById(atc_parametros.getIdTipoCausal()) : null;
	}

	/**
	 * Retorna el valor del objeto de TipoCausal.
	 *
	 * @param as_parametros correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de TipoCausal
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoCausal
	 */
	public TipoCausal findById(String as_parametros)
	    throws B2BException
	{
		TipoCausal ltc_tipoCausal;

		ltc_tipoCausal = null;

		if(StringUtils.isValidString(as_parametros))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_parametros);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltc_tipoCausal = findTiposCausales(lrs_rs);
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

		return ltc_tipoCausal;
	}

	/**
	 * Inserta o actualiza el registro
	 *
	 * @param apt_parametros correspondiente al valor del tipo de objeto TipoCausal
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(TipoCausal apt_parametros, boolean ab_query)
	    throws B2BException
	{
		if(apt_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection()
						                .prepareStatement(ab_query ? cs_INSERT_TIPO_CAUSAL : cs_UPDATE_TIPO_CAUSAL);

				if(ab_query)
					lps_ps.setString(li_column++, apt_parametros.getIdTipoCausal());

				lps_ps.setString(li_column++, apt_parametros.getIdProceso());
				lps_ps.setString(li_column++, apt_parametros.getNombre());
				lps_ps.setString(li_column++, apt_parametros.getEstadoCausal());
				lps_ps.setLong(li_column++, apt_parametros.getVersion());

				if(ab_query)
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioCreacion());

				lps_ps.setString(li_column++, apt_parametros.getIdSubProceso());
				lps_ps.setString(li_column++, apt_parametros.getPlantilla());

				if(ab_query)
					lps_ps.setString(li_column++, apt_parametros.getIpCreacion());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIpModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIdTipoCausal());
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

	/**
	 * Retorna el valor del objeto de TipoCausal.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return devuelve el valor de TipoCausal
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoCausal
	 */
	private TipoCausal findTiposCausales(ResultSet ars_rs)
	    throws SQLException
	{
		TipoCausal ls_tipoActo;

		ls_tipoActo = new TipoCausal();

		ls_tipoActo.setIdTipoCausal(ars_rs.getString("ID_CAUSAL"));
		ls_tipoActo.setIdProceso(ars_rs.getString("ID_PROCESO"));
		ls_tipoActo.setNombre(ars_rs.getString("DESCRIPCION"));
		ls_tipoActo.setEstadoCausal(ars_rs.getString("ESTADO_CAUSAL"));
		ls_tipoActo.setVersion(ars_rs.getLong("VERSION"));
		ls_tipoActo.setUserId(ars_rs.getString("ID_USUARIO_CREACION"));
		ls_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ls_tipoActo.setIdSubProceso(ars_rs.getString("ID_SUBPROCESO"));
		ls_tipoActo.setPlantilla(ars_rs.getString("PLANTILLA"));
		ls_tipoActo.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ls_tipoActo.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_tipoActo.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_tipoActo.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ls_tipoActo;
	}
}
