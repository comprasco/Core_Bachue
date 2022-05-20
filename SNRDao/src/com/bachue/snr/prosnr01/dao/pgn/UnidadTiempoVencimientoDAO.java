package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO
 *
 * @author Sebastian Sanchez
 *
 */
public class UnidadTiempoVencimientoDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_UNIDAD_TIEMPO, DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO WHERE ID_UNIDAD_TIEMPO = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO(ID_UNIDAD_TIEMPO, DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO SET DESCRIPCION=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_UNIDAD_TIEMPO=?";

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * @return Colección de Unidad Tiempo Vencimiento resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<UnidadTiempoVencimiento> findAll()
	    throws B2BException
	{
		Collection<UnidadTiempoVencimiento> lcutv_cutv;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;

		lcutv_cutv     = new ArrayList<UnidadTiempoVencimiento>();
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
				lcutv_cutv.add(getObjetFromResultSet(lrs_rs));
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

		if(lcutv_cutv.isEmpty())
			lcutv_cutv = null;

		return lcutv_cutv;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador
	 *
	 * @param autv_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Registro resultante de la consulta
	 * @throws B2BException
	 */
	public UnidadTiempoVencimiento findById(UnidadTiempoVencimiento autv_param)
	    throws B2BException
	{
		UnidadTiempoVencimiento lcs_object;

		lcs_object = null;

		if(autv_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, autv_param.getIdUnidadTiempo());

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
	 * con la información de la unidad tiempo vemcimiento suministrada.
	 *
	 * @param as_param objeto contenedor de la información a actualizar o insertar
	 * @param ab_query define el proceso seleccionado, true para insertar un nuevo
	 * registro, false para actualizar un registro existente.
	 * @throws B2BException
	 */
	public void insertOrUpdate(UnidadTiempoVencimiento as_param, boolean ab_query)
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
					lps_ps.setString(li_column++, as_param.getIdUnidadTiempo());
					lps_ps.setString(li_column++, as_param.getDescripcion());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, as_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, as_param.getDescripcion());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, as_param.getIpModificacion());
					lps_ps.setString(li_column++, as_param.getIdUnidadTiempo());
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
	private UnidadTiempoVencimiento getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		UnidadTiempoVencimiento ls_solicitud;

		ls_solicitud = new UnidadTiempoVencimiento();

		ls_solicitud.setIdUnidadTiempo(lrs_rs.getString("ID_UNIDAD_TIEMPO"));
		ls_solicitud.setDescripcion(lrs_rs.getString("DESCRIPCION"));
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
