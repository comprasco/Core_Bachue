package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.png.TarifaAlerta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase encargada del manejo de transacciones relacionadas a la tabla SDB_PNG_TARIFA_ALERTA.
 *
 * @author Manuel Blanco
 */
public class TarifaAlertaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_TARIFA, CANTIDAD_INICIAL_MATRICULAS, CANTIDAD_FINAL_MATRICULAS, VALOR_TARIFA, VERSION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PNG_TARIFA_ALERTA";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_TARIFA, CANTIDAD_INICIAL_MATRICULAS, CANTIDAD_FINAL_MATRICULAS, VALOR_TARIFA, VERSION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PNG_TARIFA_ALERTA WHERE ACTIVO = 'S' ORDER BY CANTIDAD_INICIAL_MATRICULAS ASC";

	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID = "DELETE FROM SDB_PNG_TARIFA_ALERTA WHERE ID_TARIFA = ?";

	/** Constante cs_BUSCAR_RANGO. */
	private static final String cs_BUSCAR_RANGO = "SELECT * FROM SDB_PNG_TARIFA_ALERTA"
		+ " WHERE CANTIDAD_INICIAL_MATRICULAS <= ? AND CANTIDAD_FINAL_MATRICULAS >= ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_TARIFA_ALERTA WHERE ID_TARIFA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_TARIFA_ALERTA SET CANTIDAD_INICIAL_MATRICULAS = ?, CANTIDAD_FINAL_MATRICULAS = ?, VALOR_TARIFA = ?, VERSION = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TARIFA = ?";

	/** Constante cs_UPDATE_ID_TARIFA_VERSION. */
	private static final String cs_UPDATE_ID_TARIFA_VERSION = "UPDATE SDB_PNG_TARIFA_ALERTA SET CANTIDAD_INICIAL_MATRICULAS = ?, CANTIDAD_FINAL_MATRICULAS = ?, VALOR_TARIFA = ?, VERSION = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TARIFA = ? AND VERSION = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_TARIFA_ALERTA (ID_TARIFA, CANTIDAD_INICIAL_MATRICULAS, CANTIDAD_FINAL_MATRICULAS, VALOR_TARIFA, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Metodo encargado de buscar una cantidad en un rango de tarifas.
	 *
	 * @param al_cantidad Argumento de tipo <code>long</code> que contiene la cantidad de tarifas a consultar.
	 * @return Objeto de tipo <code>TarifaAlerta</code> que contiene los resultados que coincidieron con los
	 * criterios de consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TarifaAlerta buscarRango(long al_cantidad)
	    throws B2BException
	{
		TarifaAlerta      lta_datos;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lta_datos     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_contador;

			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_BUSCAR_RANGO);

			lps_ps.setLong(li_contador++, al_cantidad);
			lps_ps.setLong(li_contador++, al_cantidad);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lta_datos = getObjectFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarRango", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lta_datos;
	}

	/**
	 * Busca todos los registros de la tabla
	 * Sobrecargado por orden establecido.
	 *
	 * @return Colección resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TarifaAlerta> findAll()
	    throws B2BException
	{
		return findAll(false);
	}

	/**
	 * Busca todos los registros de la tabla.
	 *
	 * @param ab_orden de ab orden
	 * @return Colección resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TarifaAlerta> findAll(boolean ab_orden)
	    throws B2BException
	{
		Collection<TarifaAlerta> lcta_return;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcta_return     = new LinkedList<TarifaAlerta>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append(cs_FIND_ALL);

			if(!ab_orden)
				lsb_sb.append(" ORDER BY CANTIDAD_INICIAL_MATRICULAS ASC");
			else
				lsb_sb.append(" ORDER BY VERSION ASC");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_return.add(getObjectFromResultSet(lrs_rs));
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

		if(lcta_return.isEmpty())
			lcta_return = null;

		return lcta_return;
	}

	/**
	 * Busca todos los registros de la tabla marcados como activo.
	 *
	 * @return Colección resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TarifaAlerta> findAllActivo()
	    throws B2BException
	{
		Collection<TarifaAlerta> lcta_return;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcta_return     = new LinkedList<TarifaAlerta>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_return.add(getObjectFromResultSet(lrs_rs));
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

		if(lcta_return.isEmpty())
			lcta_return = null;

		return lcta_return;
	}

	/**
	 * Elimina los registros de la tabla que coincidan con el parámetro ingresado.
	 *
	 * @param as_idTarifa de as id tarifa
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void deleteById(String as_idTarifa)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(StringUtils.isValidString(as_idTarifa))
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(1, as_idTarifa);

				lrs_rs = lps_ps.executeQuery();
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "deleteById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan con un codigoMensaje específico de la tabla SDB_PNG_TARIFA_ALERTA.
	 *
	 * @param as_s String con el identificador del objeto a consultar
	 * @return Objeto TarifaAlerta con los datos consultados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TarifaAlerta findById(String as_s)
	    throws B2BException
	{
		TarifaAlerta      ltpa_tarifaParaAlerta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ltpa_tarifaParaAlerta     = null;
		lps_ps                    = null;
		lrs_rs                    = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_s);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ltpa_tarifaParaAlerta = getObjectFromResultSet(lrs_rs);
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

		return ltpa_tarifaParaAlerta;
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PNG_TARIFA_ALERTA.
	 *
	 * @param atpa_param Objeto TarifaAlerta con la información a actualizar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateIdTarifaVersion(TarifaAlerta atpa_param)
	    throws B2BException
	{
		if(atpa_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_UPDATE_ID_TARIFA_VERSION);

				lps_ps.setLong(li_column++, atpa_param.getCantidadInicialMatriculas());
				lps_ps.setLong(li_column++, atpa_param.getCantidadFinalMatriculas());
				lps_ps.setDouble(li_column++, atpa_param.getValorTarifa());
				lps_ps.setLong(li_column++, atpa_param.getVersion());
				lps_ps.setString(li_column++, atpa_param.getActivo());
				lps_ps.setString(li_column++, atpa_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atpa_param.getIpModificacion());
				lps_ps.setString(li_column++, atpa_param.getIdTarifa());
				lps_ps.setLong(li_column++, atpa_param.getVersion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateIdTarifaVersion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PNG_TARIFA_ALERTA.
	 *
	 * @param atpa_param Objeto CanalOrigenServicio con la información a actualizar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(TarifaAlerta atpa_param)
	    throws B2BException
	{
		if(atpa_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_UPDATE);

				lps_ps.setLong(li_column++, atpa_param.getCantidadInicialMatriculas());
				lps_ps.setLong(li_column++, atpa_param.getCantidadFinalMatriculas());
				lps_ps.setDouble(li_column++, atpa_param.getValorTarifa());
				lps_ps.setLong(li_column++, atpa_param.getVersion());
				lps_ps.setString(li_column++, atpa_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atpa_param.getIpModificacion());
				lps_ps.setString(li_column++, atpa_param.getIdTarifa());

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
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param atpa_param Objeto CanalOrigenServicio con la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(TarifaAlerta atpa_param)
	    throws B2BException
	{
		if(atpa_param != null)
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

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, atpa_param.getIdTarifa());
				lps_ps.setLong(li_column++, atpa_param.getCantidadInicialMatriculas());
				lps_ps.setLong(li_column++, atpa_param.getCantidadFinalMatriculas());
				lps_ps.setDouble(li_column++, atpa_param.getValorTarifa());
				lps_ps.setLong(li_column++, atpa_param.getVersion());
				lps_ps.setString(li_column++, atpa_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, atpa_param.getIpCreacion());

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
	 * Obtiene los datos resultantes de una consulta.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de una consulta
	 * @return Tarifa alerta resultante de la consulta
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private TarifaAlerta getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TarifaAlerta lta_tarifaAlerta;

		lta_tarifaAlerta = new TarifaAlerta();

		lta_tarifaAlerta.setIdTarifa(ars_rs.getString("ID_TARIFA"));
		lta_tarifaAlerta.setCantidadInicialMatriculas(ars_rs.getLong("CANTIDAD_INICIAL_MATRICULAS"));
		lta_tarifaAlerta.setCantidadFinalMatriculas(ars_rs.getLong("CANTIDAD_FINAL_MATRICULAS"));
		lta_tarifaAlerta.setValorTarifa(ars_rs.getLong("VALOR_TARIFA"));
		lta_tarifaAlerta.setVersion(ars_rs.getLong("VERSION"));
		lta_tarifaAlerta.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lta_tarifaAlerta);

		return lta_tarifaAlerta;
	}
}
