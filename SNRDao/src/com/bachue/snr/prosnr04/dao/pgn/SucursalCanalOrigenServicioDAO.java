package com.bachue.snr.prosnr04.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.pgn.SucursalCanalOrigenServicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
 *
 * @author Julian Vaca
 */
public class SucursalCanalOrigenServicioDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO WHERE ID_SUCURSAL_CANAL_ORIGEN_SERVICIO = ?";

	/** Constante  cs_FIND_BY_CODIGO_ID. */
	private static final String cs_FIND_BY_CODIGO_ID = "SELECT * FROM SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO WHERE CODIGO_SUCURSAL_CANAL_ORIGEN_SERVICIO = ? AND ID_CANAL_ORIGEN_SERVICIO = ?";

	/** Constante cs_FIND_BY_CODIGO. */
	private static final String cs_FIND_BY_CODIGO = "SELECT * FROM SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO WHERE CODIGO_SUCURSAL_CANAL_ORIGEN_SERVICIO = ? ORDER BY FECHA_CREACION DESC";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO SET NOMBRE_SUCURSAL_CANAL_ORIGEN_SERVICIO = ?, CODIGO_SUCURSAL_CANAL_ORIGEN_SERVICIO = ?, ID_CANAL_ORIGEN_SERVICIO = ?, ACTIVO=?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_SUCURSAL_CANAL_ORIGEN_SERVICIO = ?";

	/** Constante  cs_UPDATE. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO (ID_SUCURSAL_CANAL_ORIGEN_SERVICIO, NOMBRE_SUCURSAL_CANAL_ORIGEN_SERVICIO, CODIGO_SUCURSAL_CANAL_ORIGEN_SERVICIO, ID_CANAL_ORIGEN_SERVICIO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constate cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO_ID_SUCURSAL_CANAL_ORIGEN_SERVICIO.NEXTVAL FROM DUAL";

	/**
	 * Metodo que busca todos los tipos de recaudo.
	 *
	 * @return Collection de objetos SucursalCanalOrigenServicio
	 * @throws B2BException cuando se genera un error en la ejecuion
	 * @see com.bachue.snr.prosnr04.model.pgn.SucursalCanalOrigenServicio
	 */
	public Collection<SucursalCanalOrigenServicio> findAll()
	    throws B2BException
	{
		Collection<SucursalCanalOrigenServicio> lcta_ta;
		PreparedStatement                       lps_ps;
		ResultSet                               lrs_rs;

		lcta_ta     = new ArrayList<SucursalCanalOrigenServicio>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_ta.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcta_ta))
			lcta_ta = null;

		return lcta_ta;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un idSucursalCanalOrigenServicio específico.
	 *
	 * @param ata_param de ata param
	 * @return el valor de sucursal canal origen servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SucursalCanalOrigenServicio findById(SucursalCanalOrigenServicio ata_param)
	    throws B2BException
	{
		SucursalCanalOrigenServicio ltr_pt;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		ltr_pt     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, ata_param.getIdSucursalCanalOrigenServicio());

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

		return ltr_pt;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un CODIGO_SUCURSAL_CANAL_ORIGEN_SERVICIO y ID_CANAL_ORIGEN_SERVICIO específico.
	 *
	 * @param ascos_param de ascos param
	 * @return el valor de sucursal canal origen servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SucursalCanalOrigenServicio findByIdCanalCodigoSucursal(SucursalCanalOrigenServicio ascos_param)
	    throws B2BException
	{
		SucursalCanalOrigenServicio ltr_pt;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		ltr_pt     = null;
		lps_ps     = null;
		lrs_rs     = null;

		if(ascos_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CODIGO_ID);

				lps_ps.setString(1, ascos_param.getCodigoSucursalCanalOrigenServicio());
				lps_ps.setString(2, ascos_param.getIdCanalOrigenServicio());

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
	 * Find by codigo.
	 *
	 * @param as_codigo de as codigo
	 * @return el valor de sucursal canal origen servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SucursalCanalOrigenServicio findByCodigo(String as_codigo)
	    throws B2BException
	{
		SucursalCanalOrigenServicio lscos_return;

		lscos_return = null;

		if(StringUtils.isValidString(as_codigo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CODIGO);

				lps_ps.setString(1, as_codigo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lscos_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCodigo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lscos_return;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param ata_param de ata param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(SucursalCanalOrigenServicio ata_param)
	    throws B2BException
	{
		if(ata_param != null)
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

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				lps_ps.setString(li_column++, ata_param.getNombreSucursalCanalOrigenServicio());
				lps_ps.setString(li_column++, ata_param.getCodigoSucursalCanalOrigenServicio());
				lps_ps.setString(li_column++, ata_param.getIdCanalOrigenServicio());
				lps_ps.setString(li_column++, ata_param.getActivo());

				lps_ps.setString(li_column++, ata_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ata_param.getIpCreacion());

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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param ata_param de ata param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(SucursalCanalOrigenServicio ata_param)
	    throws B2BException
	{
		if(ata_param != null)
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

				lps_ps = lc_connection.prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, ata_param.getNombreSucursalCanalOrigenServicio());
				lps_ps.setString(li_column++, ata_param.getCodigoSucursalCanalOrigenServicio());
				lps_ps.setString(li_column++, ata_param.getIdCanalOrigenServicio());
				lps_ps.setString(li_column++, ata_param.getActivo());

				lps_ps.setString(li_column++, ata_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ata_param.getIpModificacion());
				lps_ps.setString(li_column++, ata_param.getIdSucursalCanalOrigenServicio());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

/**
 * Método para la obtencion del objeto SucursalCanalOrigenServicio.
 *
 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
 * @return el valor de objet from result set
 * @throws SQLException cuando se produce algun error en el proceso
 */
	private SucursalCanalOrigenServicio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SucursalCanalOrigenServicio lscos_scos;

		lscos_scos = new SucursalCanalOrigenServicio();

		lscos_scos.setIdSucursalCanalOrigenServicio(ars_rs.getString("ID_SUCURSAL_CANAL_ORIGEN_SERVICIO"));
		lscos_scos.setNombreSucursalCanalOrigenServicio(ars_rs.getString("NOMBRE_SUCURSAL_CANAL_ORIGEN_SERVICIO"));
		lscos_scos.setCodigoSucursalCanalOrigenServicio(ars_rs.getString("CODIGO_SUCURSAL_CANAL_ORIGEN_SERVICIO"));
		lscos_scos.setIdCanalOrigenServicio(ars_rs.getString("ID_CANAL_ORIGEN_SERVICIO"));
		lscos_scos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lscos_scos);

		return lscos_scos;
	}
}
