package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.DetalleProcesoConsulta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_DETALLE_PROCESO_CONSULTA
 *
 * @author Sebastian Sanchez
 *
 */
public class DetalleProcesoConsultaDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_PROCESO_CONSULTA, ID_TIPO_CRITERIO_BUSQUEDA, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PGN_DETALLE_PROCESO_CONSULTA ORDER BY LENGTH(ID_PROCESO_CONSULTA), ID_PROCESO_CONSULTA,LENGTH(ID_TIPO_CRITERIO_BUSQUEDA), ID_TIPO_CRITERIO_BUSQUEDA ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_DETALLE_PROCESO_CONSULTA WHERE ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_DETALLE_PROCESO_CONSULTA(ID_PROCESO_CONSULTA, ID_TIPO_CRITERIO_BUSQUEDA, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_DETALLE_PROCESO_CONSULTA SET ACTIVO=?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_PROCESO_CONSULTA=? AND ID_TIPO_CRITERIO_BUSQUEDA=?";

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * @return Colección de Detalle Proceso Consulta resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<DetalleProcesoConsulta> findAll()
	    throws B2BException
	{
		Collection<DetalleProcesoConsulta> lcdpc_cdpc;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		lcdpc_cdpc     = new ArrayList<DetalleProcesoConsulta>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcdpc_cdpc.add(getObjetFromResultSet(lrs_rs));
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

		if(lcdpc_cdpc.isEmpty())
			lcdpc_cdpc = null;

		return lcdpc_cdpc;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador
	 *
	 * @param adpc_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Registro resultante de la consulta
	 * @throws B2BException
	 */
	public DetalleProcesoConsulta findById(DetalleProcesoConsulta adpc_param)
	    throws B2BException
	{
		DetalleProcesoConsulta lcs_object;

		lcs_object = null;

		if(adpc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, adpc_param.getIdProcesoConsulta());
				lps_ps.setString(2, adpc_param.getIdTipoCriterioBusqueda());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcs_object = getObjetFromResultSet(lrs_rs);
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

		return lcs_object;
	}

	/**
	 * Dependiendo del procedimiento seleccionado, inserta o actualiza un registro
	 * con la información del Detalle Proceso Consulta suministrada.
	 *
	 * @param as_param objeto contenedor de la información a actualizar o insertar
	 * @param ab_query define el proceso seleccionado, true para insertar un nuevo
	 * registro, false para actualizar un registro existente.
	 * @throws B2BException
	 */
	public void insertOrUpdate(DetalleProcesoConsulta as_param, boolean ab_query)
	    throws B2BException
	{
		if(as_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, as_param.getIdProcesoConsulta());
					lps_ps.setString(li_column++, as_param.getIdTipoCriterioBusqueda());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, as_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, as_param.getIpModificacion());
					lps_ps.setString(li_column++, as_param.getIdProcesoConsulta());
					lps_ps.setString(li_column++, as_param.getIdTipoCriterioBusqueda());
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
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException
	 */
	private DetalleProcesoConsulta getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		DetalleProcesoConsulta ls_solicitud;

		ls_solicitud = new DetalleProcesoConsulta();

		ls_solicitud.setIdProcesoConsulta(lrs_rs.getString("ID_PROCESO_CONSULTA"));
		ls_solicitud.setIdTipoCriterioBusqueda(lrs_rs.getString("ID_TIPO_CRITERIO_BUSQUEDA"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
