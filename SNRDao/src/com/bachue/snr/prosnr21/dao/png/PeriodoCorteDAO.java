package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr21.model.pgn.PeriodoCorte;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_PERIODO_CORTE
 * @author dbeltran
 *
 */
public class PeriodoCorteDAO extends AuditoriaDao
{
	/**Constante cs_INSERT*/
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PERIODO_CORTE(ID_PERIODO_CORTE,PERIODO,CORTE,DIA_CORTE,"
		+ "ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)VALUES(?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/**Constante cs_UPDATE*/
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PERIODO_CORTE SET PERIODO=?,CORTE=?,DIA_CORTE=?,ACTIVO=?,"
		+ "ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_PERIODO_CORTE=?";

	/**Constante cs_FIND_ALL*/
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PERIODO_CORTE ORDER BY PERIODO DESC, DIA_CORTE ASC";

	/**Constante cs_FIND_BY_DIA_CORTE */
	private static final String cs_FIND_BY_DIA_CORTE = "SELECT * FROM SDB_PGN_PERIODO_CORTE WHERE ACTIVO='S' AND TO_DATE(DIA_CORTE, 'DD/MM/YY') = TO_DATE(?, 'DD/MM/YY') ORDER BY ID_PERIODO_CORTE";

	/**Constante cs_FIND_BY_ID*/
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_PERIODO_CORTE WHERE ID_PERIODO_CORTE=?";

	/**Constante cs_FIND_BY_ID*/
	private static final String cs_FIND_BY_DATE = "SELECT * FROM SDB_PGN_PERIODO_CORTE WHERE TO_DATE(DIA_CORTE, 'DD/MM/YY') = TO_DATE(?, 'DD/MM/YY')";

	/**Constante cs_FIND_BY_PERIODO*/
	private static final String cs_FIND_BY_PERIODO = "SELECT * FROM SDB_PGN_PERIODO_CORTE WHERE PERIODO=?";

	/**Constante cs_FIND_SECUENCE*/
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_PERIODO_CORTE_ID_PERIODO_CORTE.NEXTVAL FROM DUAL";

	/**Constante cs_FIND_PERIODO_MES*/
	private static final String cs_FIND_PERIODO_MES = "SELECT DISTINCT EXTRACT(YEAR FROM DIA_CORTE) PERIODO, EXTRACT(MONTH FROM DIA_CORTE) MES FROM  SDB_PGN_PERIODO_CORTE ORDER BY EXTRACT(YEAR FROM DIA_CORTE) DESC, EXTRACT(MONTH FROM DIA_CORTE)";

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_PERIODO_CORTE
	 * @return
	 * @throws B2BException
	 */
	public Collection<PeriodoCorte> findAll()
	    throws B2BException
	{
		Collection<PeriodoCorte> lcpr_cpr;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcpr_cpr     = new ArrayList<PeriodoCorte>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpr_cpr.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcpr_cpr))
			lcpr_cpr = null;

		return lcpr_cpr;
	}

	/**
	 * Metodo para encontrar un registro por el dia de corte al que pertenece
	 * @param as_param String con el valor del ID del registro a buscar
	 * @return de tipo PeriodoCorte con el valor del registro requerido
	 * @throws B2BException
	 */
	public PeriodoCorte findByDiaCorte(Date ad_corte)
	    throws B2BException
	{
		PeriodoCorte lpc_retorno;

		lpc_retorno = null;

		if(ad_corte != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_DIA_CORTE);

				setDate(lps_ps, ad_corte, 1);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpc_retorno = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDiaCorte", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpc_retorno;
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_PERIODO_CORTE buscados por DIA_CORTE
	 * @param ad_param String con el valor del DIA_CORTE del registro a buscar
	 * @return de tipo PeriodoCorte con el valor del registro requerido
	 * @throws B2BException
	 */
	public PeriodoCorte findByFecha(Date ad_param)
	    throws B2BException
	{
		PeriodoCorte ltd_return;
		ltd_return = null;

		if(ad_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_DATE);

				setDate(lps_ps, ad_param, 1);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDate", lse_e);

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
	 *Metodo para encontrar  los registros existentes en la tabla SDB_PGN_PERIODO_CORTE buscados por ID_PERIODO_CORTE
	 * @param atd_param
	 * @return
	 * @throws B2BException
	 */
	public PeriodoCorte findById(PeriodoCorte atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdPeriodoCorte()) : null);
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_PERIODO_CORTE buscados por ID_PERIODO_CORTE
	 * @param as_param String con el valor del ID del registro a buscar
	 * @return de tipo PeriodoCorte con el valor del registro requerido
	 * @throws B2BException
	 */
	public PeriodoCorte findById(String as_param)
	    throws B2BException
	{
		PeriodoCorte ltd_return;
		ltd_return = null;

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
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_PERIODO_CORTE buscados por PERIODO
	 * @param as_param String con el valor del PERIODO del registro a buscar
	 * @return de tipo Collection<PeriodoCorte> con los registros requerido
	 * @throws B2BException
	 */
	public Collection<PeriodoCorte> findByPeriodo(String as_param)
	    throws B2BException
	{
		Collection<PeriodoCorte> lcpr_cpr;
		lcpr_cpr = new ArrayList<PeriodoCorte>();

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PERIODO);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_cpr.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByPeriodo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcpr_cpr))
			lcpr_cpr = null;

		return lcpr_cpr;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_PERIODO_CORTE
	 * @return
	 * @throws B2BException
	 */
	public Collection<PeriodoCorte> findPeriodoMes()
	    throws B2BException
	{
		Collection<PeriodoCorte> lcpr_cpr;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcpr_cpr     = new ArrayList<PeriodoCorte>(1);
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_PERIODO_MES);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpr_cpr.add(getObject(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findPeriodoMes", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lcpr_cpr))
			lcpr_cpr = null;

		return lcpr_cpr;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_PERIODO_CORTE
	 *  @param apc_corte Registro a insertar en la base de datos
	 */
	public void insert(PeriodoCorte apc_corte)
	    throws B2BException
	{
		if(apc_corte != null)
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
						apc_corte.setIdPeriodoCorte(lo_o.toString());
					else
						throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
				}

				lps_insercion.setString(li_column++, apc_corte.getIdPeriodoCorte());

				lps_insercion.setLong(li_column++, apc_corte.getPeriodo());
				lps_insercion.setLong(li_column++, apc_corte.getCorte());

				setDate(lps_insercion, apc_corte.getDiaCorte(), li_column++);

				lps_insercion.setString(li_column++, apc_corte.getActivo());
				lps_insercion.setString(li_column++, apc_corte.getIdUsuarioCreacion());
				lps_insercion.setString(li_column++, apc_corte.getIpCreacion());

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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_PERIODO_CORTE
	 */
	public void update(PeriodoCorte atd_param)
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

				lps_ps.setLong(li_column++, atd_param.getPeriodo());
				lps_ps.setLong(li_column++, atd_param.getCorte());
				setDate(lps_ps, atd_param.getDiaCorte(), li_column++);
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());
				lps_ps.setString(li_column++, atd_param.getIdPeriodoCorte());

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
	 * Gets the object.
	 *
	 * @param ars_rs the ars rs
	 * @return the objet
	 * @throws SQLException the SQL exception
	 */
	private PeriodoCorte getObject(ResultSet ars_rs)
	    throws SQLException
	{
		long         ll_periodo;
		long         ll_mes;
		PeriodoCorte lc_datos;

		ll_periodo     = ars_rs.getLong("PERIODO");
		ll_mes         = ars_rs.getLong("MES");
		lc_datos       = new PeriodoCorte();

		if((ll_mes > 0) && (ll_periodo > 0))
		{
			StringBuilder lsb_fechaStr;
			StringBuilder lsb_periodoAnioMes;

			lsb_fechaStr           = new StringBuilder(DateUtils.getMes(NumericUtils.getInt(ll_mes)));
			lsb_periodoAnioMes     = new StringBuilder();

			lsb_fechaStr.append(IdentificadoresCommon.ESPACIO_VACIO);
			lsb_fechaStr.append(IdentificadoresCommon.SLASH);
			lsb_fechaStr.append(IdentificadoresCommon.ESPACIO_VACIO);
			lsb_fechaStr.append(ll_periodo);
			lsb_periodoAnioMes.append(ll_periodo);

			if(ll_mes < 9)
				lsb_periodoAnioMes.append(0);

			lsb_periodoAnioMes.append(ll_mes);

			lc_datos.setPeriodoAnioMes(lsb_periodoAnioMes.toString());
			lc_datos.setFechaStr(lsb_fechaStr.toString());
		}

		lc_datos.setPeriodo(ll_periodo);
		lc_datos.setMes(ll_mes);

		return lc_datos;
	}

	/**
	 * Metodo que se encarga de llenar un objeto de tipo PeriodoCorte con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto PeriodoCorte
	 * @return Objeto de tipo PeriodoCorte con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PeriodoCorte getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		PeriodoCorte lc_datos;

		lc_datos = new PeriodoCorte();

		lc_datos.setIdPeriodoCorte(ars_rs.getString("ID_PERIODO_CORTE"));
		lc_datos.setPeriodo(ars_rs.getLong("PERIODO"));
		lc_datos.setCorte(ars_rs.getLong("CORTE"));
		lc_datos.setDiaCorte(ars_rs.getDate("DIA_CORTE"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
