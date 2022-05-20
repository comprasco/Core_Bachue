package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA
 * @author dbeltran
 *
 */
public class EntidadRecaudadoraCuentaDAO extends AuditoriaDao
{
	/**Propiedad cs_FIND_SECUENCE*/
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_ENTIDAD_RECAUDADORA_CUENTA_ID_CUENTA.NEXTVAL FROM "
		+ "DUAL";

	/**Propiedad cs_FIND_ALL*/
	private static final String cs_FIND_ALL = "SELECT ERCC.NOMBRE_ENTIDAD_RECAUDADORA,ERC.* FROM SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA ERC \r\n"
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION ERCC ON ERC.ID_ENTIDAD_RECAUDADORA = ERCC.ID_ENTIDAD_RECAUDADORA\r\n"
		+ "ORDER BY ERC.ID_ENTIDAD_RECAUDADORA,ERC.NUMERO_CUENTA";

	/**Propiedad cs_FIND_ALL_ACTIVE*/
	private static final String cs_FIND_ALL_ACTIVE = "SELECT SPERC1.NOMBRE_ENTIDAD_RECAUDADORA, SPERC.* FROM SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA SPERC INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION SPERC1 ON SPERC.ID_ENTIDAD_RECAUDADORA = SPERC1.ID_ENTIDAD_RECAUDADORA WHERE SPERC1.ACTIVO = 'S' AND SPERC.ACTIVO = 'S'";

	/**Propiedad Lista todas las cuentas de una entidad recuadadora*/
	private static final String cs_FIND_ALL_BY_ENTIDAD_RECAUDADORA = "SELECT * FROM SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA "
		+ "WHERE ACTIVO = 'S' AND ID_ENTIDAD_RECAUDADORA=? ORDER BY NUMERO_CUENTA";

	/**Propiedad Lista todas las cuentas de un analista basados en un usuario y una entidad recaudadora*/
	private static final String cs_FIND_ALL_BY_USER_ID_BANCO_ID = "SELECT  ERC.* "
		+ "FROM SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA ERC " + "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION ER "
		+ "ON ER.ID_ENTIDAD_RECAUDADORA = ERC.ID_ENTIDAD_RECAUDADORA " + "INNER JOIN SDB_PGN_CUENTA_ANALISTA CA "
		+ "ON ERC.ID_CUENTA = CA.ID_CUENTA " + "WHERE CA.ID_USUARIO = ? AND ER.ID_ENTIDAD_RECAUDADORA = ?";

	/**Propiedad cs_FIND_BY_ID*/
	private static final String cs_FIND_BY_ID = "SELECT ERCC.NOMBRE_ENTIDAD_RECAUDADORA,ERC.* FROM SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA ERC \r\n"
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION ERCC ON ERC.ID_ENTIDAD_RECAUDADORA = ERCC.ID_ENTIDAD_RECAUDADORA\r\n  WHERE ERC.ID_CUENTA = ?";

	/**Propiedad cs_FIND_BY_ID_BANCO_NUM_CUENTA_TIPO*/
	private static final String cs_FIND_BY_ID_BANCO_NUM_CUENTA_TIPO = "SELECT * FROM SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA WHERE ID_ENTIDAD_RECAUDADORA = ? AND NUMERO_CUENTA = ? AND TIPO_CUENTA = ?";

	/**Propiedad cs_INSERT*/
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA (ID_CUENTA, ID_ENTIDAD_RECAUDADORA, NUMERO_CUENTA, TIPO_CUENTA, TIPO_ARCHIVO, NOMBRE_CONTACTO, NUMERO_CEL_CONTACTO, CORREO_ELECTRONICO_CONTACTO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**Propiedad cs_UPDATE*/
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA SET ID_ENTIDAD_RECAUDADORA = ?, NUMERO_CUENTA = ?, TIPO_CUENTA = ?, TIPO_ARCHIVO = ?, NOMBRE_CONTACTO = ?, NUMERO_CEL_CONTACTO = ?, CORREO_ELECTRONICO_CONTACTO = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CUENTA = ?";

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA
	 * @return
	 * @throws B2BException
	 */
	public Collection<EntidadRecaudadoraCuenta> findAll()
	    throws B2BException
	{
		Collection<EntidadRecaudadoraCuenta> lcerc_cerc;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcerc_cerc     = new ArrayList<EntidadRecaudadoraCuenta>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcerc_cerc.add(getObjetFromResultSetAll(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcerc_cerc))
			lcerc_cerc = null;

		return lcerc_cerc;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA
	 * @return
	 * @throws B2BException
	 */
	public Collection<EntidadRecaudadoraCuenta> findAllActive()
	    throws B2BException
	{
		Collection<EntidadRecaudadoraCuenta> lcerc_cerc;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcerc_cerc     = new ArrayList<EntidadRecaudadoraCuenta>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVE);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcerc_cerc.add(getObjetFromResultSetAll(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcerc_cerc))
			lcerc_cerc = null;

		return lcerc_cerc;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA
	 * @param as_idEntidadRecuadadora id entidad recaudadora de entidad recaudadora
	 * * @param as_idUsaurio id del usuario
	 * @return
	 * @throws B2BException
	 */
	public Collection<EntidadRecaudadoraCuenta> findAllByBancoAndUser(
	    String as_idEntidadRecuadadora, String as_idUsaurio
	)
	    throws B2BException
	{
		Collection<EntidadRecaudadoraCuenta> lcerc_cerc;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcerc_cerc     = new ArrayList<EntidadRecaudadoraCuenta>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_column;
			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_USER_ID_BANCO_ID);

			lps_ps.setString(li_column++, as_idUsaurio);
			lps_ps.setString(li_column++, as_idEntidadRecuadadora);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcerc_cerc.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByBancoAndUser", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lcerc_cerc))
			lcerc_cerc = null;

		return lcerc_cerc;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA
	 * @param as_idEntidadRecuadadora Codigo de entidad recaudadora
	 * @return
	 * @throws B2BException
	 */
	public Collection<EntidadRecaudadoraCuenta> findAllPorEntidadRecaudadora(String as_idEntidadRecuadadora)
	    throws B2BException
	{
		Collection<EntidadRecaudadoraCuenta> lcerc_cerc;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcerc_cerc     = new ArrayList<EntidadRecaudadoraCuenta>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_ENTIDAD_RECAUDADORA);

			lps_ps.setString(1, as_idEntidadRecuadadora);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcerc_cerc.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllPorEntidadRecaudadora", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lcerc_cerc))
			lcerc_cerc = null;

		return lcerc_cerc;
	}

	/**
	 *Metodo para encontrar  los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA buscados por ID_CUENTA
	 * @param atd_param
	 * @return
	 * @throws B2BException
	 */
	public EntidadRecaudadoraCuenta findById(EntidadRecaudadoraCuenta atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdCuenta()) : null);
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA buscados por ID_CUENTA
	 * @param as_param String con el valor del ID del registro a buscar
	 * @return de tipo PeriodoCorte con el valor del registro requerido
	 * @throws B2BException
	 */
	public EntidadRecaudadoraCuenta findById(String as_param)
	    throws B2BException
	{
		EntidadRecaudadoraCuenta ltd_return;
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
					ltd_return = getObjetFromResultSetAll(lrs_rs);
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
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA
	 * @param as_idEntidadRecuadadora id entidad recaudadora de entidad recaudadora
	 * * @param as_idUsaurio id del usuario
	 * @return
	 * @throws B2BException
	 */
	public EntidadRecaudadoraCuenta findByIdEntidadCuentaTipo(
	    String as_idEntidadRecuadadora, String as_numeroCuenta, String as_tipoCuenta
	)
	    throws B2BException
	{
		EntidadRecaudadoraCuenta lerc_erc;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lerc_erc     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int li_column;
			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_BANCO_NUM_CUENTA_TIPO);

			lps_ps.setString(li_column++, as_idEntidadRecuadadora);
			lps_ps.setString(li_column++, as_numeroCuenta);
			lps_ps.setString(li_column++, as_tipoCuenta);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lerc_erc = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdEntidadCuentaTipo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		return lerc_erc;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA
	 */
	public EntidadRecaudadoraCuenta insert(EntidadRecaudadoraCuenta atd_param)
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
							atd_param.setIdCuenta(lo_o.toString());
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}
				}

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, atd_param.getIdCuenta());
				lps_ps.setString(li_column++, atd_param.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, atd_param.getNumeroCuenta());
				lps_ps.setString(li_column++, atd_param.getTipoCuenta());
				lps_ps.setString(li_column++, atd_param.getTipoArchivo());
				lps_ps.setString(li_column++, atd_param.getNombreContacto());
				lps_ps.setString(li_column++, atd_param.getNumeroCelContacto());
				lps_ps.setString(li_column++, atd_param.getCorreoElectronicoContacto());
				lps_ps.setString(li_column++, atd_param.getActivo());
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

		return atd_param;
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA
	 */
	public void update(EntidadRecaudadoraCuenta atd_param)
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

				lps_ps.setString(li_column++, atd_param.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, atd_param.getNumeroCuenta());
				lps_ps.setString(li_column++, atd_param.getTipoCuenta());
				lps_ps.setString(li_column++, atd_param.getTipoArchivo());
				lps_ps.setString(li_column++, atd_param.getNombreContacto());
				lps_ps.setString(li_column++, atd_param.getNumeroCelContacto());
				lps_ps.setString(li_column++, atd_param.getCorreoElectronicoContacto());
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());
				lps_ps.setString(li_column++, atd_param.getIdCuenta());

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
	 * Metodo que se encarga de llenar un objeto de tipo PeriodoCorte con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto EntidadRecaudadoraCuenta
	 * @return Objeto de tipo EntidadRecaudadoraCuenta con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private EntidadRecaudadoraCuenta getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		EntidadRecaudadoraCuenta lc_datos;

		lc_datos = new EntidadRecaudadoraCuenta();

		lc_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lc_datos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		lc_datos.setNumeroCuenta(ars_rs.getString("NUMERO_CUENTA"));
		lc_datos.setTipoCuenta(ars_rs.getString("TIPO_CUENTA"));
		lc_datos.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
		lc_datos.setNombreContacto(ars_rs.getString("NOMBRE_CONTACTO"));
		lc_datos.setNumeroCelContacto(ars_rs.getString("NUMERO_CEL_CONTACTO"));
		lc_datos.setCorreoElectronicoContacto(ars_rs.getString("CORREO_ELECTRONICO_CONTACTO"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}

	/**
	 * Metodo que se encarga de llenar un objeto de tipo PeriodoCorte con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto EntidadRecaudadoraCuenta
	 * @return Objeto de tipo EntidadRecaudadoraCuenta con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private EntidadRecaudadoraCuenta getObjetFromResultSetAll(ResultSet ars_rs)
	    throws SQLException
	{
		EntidadRecaudadoraCuenta lc_datos;

		lc_datos = new EntidadRecaudadoraCuenta();

		lc_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lc_datos.setNombreEntidadRecaudadora(ars_rs.getString("NOMBRE_ENTIDAD_RECAUDADORA"));
		lc_datos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		lc_datos.setNumeroCuenta(ars_rs.getString("NUMERO_CUENTA"));
		lc_datos.setTipoCuenta(ars_rs.getString("TIPO_CUENTA"));
		lc_datos.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
		lc_datos.setNombreContacto(ars_rs.getString("NOMBRE_CONTACTO"));
		lc_datos.setNumeroCelContacto(ars_rs.getString("NUMERO_CEL_CONTACTO"));
		lc_datos.setCorreoElectronicoContacto(ars_rs.getString("CORREO_ELECTRONICO_CONTACTO"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
