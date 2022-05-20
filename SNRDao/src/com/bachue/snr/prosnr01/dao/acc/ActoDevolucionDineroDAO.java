package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.devolucion.ActoDevolucionDinero;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_ACTOS_DEVOLUCION_DINERO.
 *
 * @author ccalderon
 */
public class ActoDevolucionDineroDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ACTOS_DEVOLUCION_DINERO (ID_ACTO_DEVOLUCION_DINERO, VERSION_ACTO, ID_DEVOLUCION_DINERO, ID_SOLICITUD_INICIAL, ID_SOLICITUD, VALOR_ACTO_DEVOLUCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ? ,? ,?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_ACTOS_DEVOLUCION_DINERO SET ID_SOLICITUD_INICIAL = ?, ID_SOLICITUD = ?, VALOR_ACTO_DEVOLUCION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_ACTO_DEVOLUCION_DINERO = ? AND VERSION_ACTO = ? AND ID_DEVOLUCION_DINERO = ?";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_ACTOS_DEVOLUCION_DINERO WHERE ID_ACTO_DEVOLUCION_DINERO = ? AND VERSION_ACTO = ? AND ID_DEVOLUCION_DINERO = ? ";

	/** Constante cs_FIND_BY_ID_ACTO_ID_SOLICITUD_INICIAL. */
	private static final String cs_FIND_BY_ID_ACTO_ID_SOLICITUD_INICIAL = "SELECT * FROM SDB_ACC_ACTOS_DEVOLUCION_DINERO WHERE ID_ACTO_DEVOLUCION_DINERO = ? AND ID_SOLICITUD_INICIAL = ?";
	
	/** Constante cs_FIND_BY_ID_ACTI_ID_SOLICITUD_INICIAL. */
	private static final String cs_FIND_BY_ID_DEVOLUCION_DINERO = "SELECT * FROM SDB_ACC_ACTOS_DEVOLUCION_DINERO WHERE ID_DEVOLUCION_DINERO = ?";
	
	/** Constante cs_FIND_BY_ID_ACTO_ID_SOLICITUD_AND_DEVOLUCION. */
	private static final String cs_FIND_BY_ID_ACTO_ID_SOLICITUD_AND_DEVOLUCION = "SELECT * FROM SDB_ACC_ACTOS_DEVOLUCION_DINERO WHERE ID_ACTO_DEVOLUCION_DINERO = ? AND ID_DEVOLUCION_DINERO = ? AND ID_SOLICITUD_INICIAL = ? AND ID_SOLICITUD = ?";

	/**
	 * Método para eliminar un registro en la tabla.
	 *
	 * @param add_parametros Objeto a eliminar
	 * @return de string
	 * @throws B2BException de b 2 B exception
	 */
	public String delete(ActoDevolucionDinero add_parametros)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(add_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, add_parametros.getIdActoDevolucionDinero());
				lps_ps.setString(li_column++, add_parametros.getVersionActo());
				lps_ps.setString(li_column++, add_parametros.getIdDevolucionDinero());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "delete", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}

		return ls_return;
	}

	/**
	 * Find by id acto id solicitud and devolucion.
	 *
	 * @param aadd_actoDevolucionDinero de aadd acto devolucion dinero
	 * @return de acto devolucion dinero
	 * @throws B2BException de b 2 B exception
	 */
	public ActoDevolucionDinero findByIdActoIdSolicitudAndDevolucion(ActoDevolucionDinero aadd_actoDevolucionDinero)
	    throws B2BException
	{
		ActoDevolucionDinero ladd_return;

		ladd_return = null;

		if(aadd_actoDevolucionDinero != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACTO_ID_SOLICITUD_AND_DEVOLUCION);

				lps_ps.setString(li_contador++, aadd_actoDevolucionDinero.getIdActoDevolucionDinero());
				lps_ps.setString(li_contador++, aadd_actoDevolucionDinero.getIdDevolucionDinero());
				lps_ps.setString(li_contador++, aadd_actoDevolucionDinero.getIdSolicitudInicial());
				lps_ps.setString(li_contador++, aadd_actoDevolucionDinero.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ladd_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdActoIdSolicitudAndDevolucion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ladd_return;
	}

	/**
	 * Find by id acto id solicitud inicial.
	 *
	 * @param as_idActo de as id acto
	 * @param as_idSolicitudInicial de as id solicitud inicial
	 * @return de acto devolucion dinero
	 * @throws B2BException de b 2 B exception
	 */
	public ActoDevolucionDinero findByIdActoIdSolicitudInicial(String as_idActo, String as_idSolicitudInicial)
	    throws B2BException
	{
		ActoDevolucionDinero ladd_return;

		ladd_return = null;

		if(StringUtils.isValidString(as_idActo) && StringUtils.isValidString(as_idSolicitudInicial))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACTO_ID_SOLICITUD_INICIAL);

				lps_ps.setString(li_contador++, as_idActo);
				lps_ps.setString(li_contador++, as_idSolicitudInicial);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ladd_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdActoIdSolicitudInicial", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ladd_return;
	}
	
	/**
	 * Find by id devolucion dinero.
	 *
	 * @param as_idDevolucionDinero de as id devolucion dinero
	 * @return de acto devolucion dinero
	 * @throws B2BException de b 2 B exception
	 */
	public ActoDevolucionDinero findByIdDevoluciondinero(String as_idDevolucionDinero)
			throws B2BException
	{
		ActoDevolucionDinero ladd_return;
		
		ladd_return = null;
		
		if(StringUtils.isValidString(as_idDevolucionDinero))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			
			lps_ps     = null;
			lrs_rs     = null;
			
			try
			{
				int li_contador;
				
				li_contador     = 1;
				
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_DEVOLUCION_DINERO);
				
				lps_ps.setString(li_contador++, as_idDevolucionDinero);
				
				lrs_rs = lps_ps.executeQuery();
				
				if(lrs_rs.next())
					ladd_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdDevoluciondinero", lse_e);
				
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
		
		return ladd_return;
	}

	/**
	 * Método para insertar registros en la tabla.
	 *
	 * @param add_parametros Objeto a insertar
	 * @return de string
	 * @throws B2BException de b 2 B exception
	 */
	public String insert(ActoDevolucionDinero add_parametros)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(add_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, add_parametros.getIdActoDevolucionDinero());
				lps_ps.setString(li_column++, add_parametros.getVersionActo());
				lps_ps.setString(li_column++, add_parametros.getIdDevolucionDinero());
				lps_ps.setString(li_column++, add_parametros.getIdSolicitudInicial());
				lps_ps.setString(li_column++, add_parametros.getIdSolicitud());
				lps_ps.setLong(li_column++, add_parametros.getValorTotalLiquidado());
				lps_ps.setString(li_column++, add_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, add_parametros.getIpCreacion());

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
			}
		}

		return ls_return;
	}

	/**
	 * Método para actualizar un registro en la tabla.
	 *
	 * @param add_parametros Objeto a actualizar
	 * @return de string
	 * @throws B2BException de b 2 B exception
	 */
	public String update(ActoDevolucionDinero add_parametros)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(add_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, add_parametros.getIdSolicitudInicial());
				lps_ps.setString(li_column++, add_parametros.getIdSolicitud());
				lps_ps.setLong(li_column++, add_parametros.getValorTotalLiquidado());
				lps_ps.setString(li_column++, add_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, add_parametros.getIpModificacion());
				lps_ps.setString(li_column++, add_parametros.getIdActoDevolucionDinero());
				lps_ps.setString(li_column++, add_parametros.getVersionActo());
				lps_ps.setString(li_column++, add_parametros.getIdDevolucionDinero());

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

		return ls_return;
	}

	/**
	 * Gets de object from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return de object from result set
	 * @throws SQLException de SQL exception
	 */
	private ActoDevolucionDinero getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ActoDevolucionDinero ladd_actoDevolucionDinero;

		ladd_actoDevolucionDinero = new ActoDevolucionDinero();

		ladd_actoDevolucionDinero.setIdActoDevolucionDinero(ars_rs.getString("ID_ACTO_DEVOLUCION_DINERO"));
		ladd_actoDevolucionDinero.setVersionActo(ars_rs.getString("VERSION_ACTO"));
		ladd_actoDevolucionDinero.setIdDevolucionDinero(ars_rs.getString("ID_DEVOLUCION_DINERO"));
		ladd_actoDevolucionDinero.setIdSolicitudInicial(ars_rs.getString("ID_SOLICITUD_INICIAL"));
		ladd_actoDevolucionDinero.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		ladd_actoDevolucionDinero.setValorTotalLiquidado(ars_rs.getLong("VALOR_ACTO_DEVOLUCION"));

		fillAuditoria(ars_rs, ladd_actoDevolucionDinero);

		return ladd_actoDevolucionDinero;
	}
}
