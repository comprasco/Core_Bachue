package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.Archivo;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_ARCHIVO
 * @author dbeltran
 *
 */
public class ArchivoDAO extends AuditoriaDao
{
	/**Constante cs_INSERT*/
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_RUBRO (ID_RUBRO, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**Constante cs_UPDATE*/
	private static final String cs_UPDATE = "UPDATE SDB_PGN_RUBRO SET NOMBRE = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_RUBRO = ?";

	/**Constante cs_FIND_ALL*/
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_RUBRO";

	/**Constante cs_FIND_BY_ID*/
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_RUBRO WHERE ID_RUBRO = ?";

	/**Constante cs_FIND_SECUENCE*/
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_RUBRO_ID_RUBRO.NEXTVAL FROM DUAL";

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_CON_ARCHIVO
	 */
	public void insert(Archivo atd_param)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							atd_param.setIdArchivo(lo_o.toString());
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}
				}

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, atd_param.getIdArchivo());
				lps_ps.setString(li_column++, atd_param.getEstadoArchivo());
				lps_ps.setString(li_column++, atd_param.getIdCuenta());
				lps_ps.setString(li_column++, atd_param.getIdPeriodoCorte());
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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_CON_ARCHIVO
	 */
	public void update(Archivo atd_param)
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

				lps_ps.setString(li_column++, atd_param.getIdArchivo());
				lps_ps.setString(li_column++, atd_param.getEstadoArchivo());
				lps_ps.setString(li_column++, atd_param.getIdCuenta());
				lps_ps.setString(li_column++, atd_param.getIdPeriodoCorte());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, atd_param.getIpCreacion());

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
	 *Metodo para encontrar  los registros existentes en la tabla SDB_CON_ARCHIVO buscados por ID_CODIGO
	 * @param atd_param
	 * @return
	 * @throws B2BException
	 */
	public Archivo findById(Archivo atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdArchivo()) : null);
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_CON_ARCHIVO buscados por ID_CODIGO
	 * @param as_param String con el valor del ID del registro a buscar
	 * @return de tipo Archivo con el valor del registro requerido
	 * @throws B2BException
	 */
	public Archivo findById(String as_param)
	    throws B2BException
	{
		Archivo ltd_return;
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
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_CON_ARCHIVO
	 * @return
	 * @throws B2BException
	 */
	public Collection<Archivo> findAll()
	    throws B2BException
	{
		Collection<Archivo> lcpr_cpr;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcpr_cpr     = new ArrayList<Archivo>();
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
	 * Metodo que se encarga de llenar un objeto de tipo Rubro con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto PeriodoCorte
	 * @return Objeto de tipo Archivo con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Archivo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Archivo lc_datos;

		lc_datos = new Archivo();

//		lc_datos.setIdCodigo(ars_rs.getString("ID_RUBRO"));
//		lc_datos.setNombre(ars_rs.getString("NOMBRE"));
//		lc_datos.setDescripcionCodigo(ars_rs.getString("NOMBRE"));
//		lc_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
