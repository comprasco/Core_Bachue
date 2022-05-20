package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.PersonaEntrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Su objetivo es hacer peticiones y retornar resultados producto de estas, a la tabla
 * SDB_ACC_PERSONA_ENTREGA
 *
 * @author mblanco
 *
 */
public class PersonaEntregaDAO extends BaseDAO
{
	private static final String cs_INSERT    = "INSERT INTO SDB_ACC_PERSONA_ENTREGA (ID_PERSONA_ENTREGA, ID_SOLICITUD, ID_TURNO, ID_PERSONA, ID_PERSONA_TERCERO, ID_CALIDAD_PERSONA_ENTREGA, FECHA_ENTREGA, ID_AUTORIZACION_COMUNICACION, ID_AUTORIZACION_NOTIFICACION, ID_DIRECCION_PERSONA_ENTREGA, ID_TELEFONO_PERSONA_ENTREGA, ID_CORREO_ELECTRONICO_PERSONA_ENTREGA, ID_DIRECCION_PERSONA_ENTREGA_COMUNICACION, ID_TELEFONO_PERSONA_ENTREGA_COMUNICACION, ID_CORREO_ELECTRONICO_PERSONA_ENTREGA_COMUNICACION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";
	private static final String cs_SECUENCIA = "SELECT SEC_ACC_PERSONA_ENTREGA_ID_PERSONA_ENTREGA.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_BY_TURNO_SOLICITUD. */
	private static final String cs_FIND_BY_TURNO_SOLICITUD = "SELECT * FROM SDB_ACC_PERSONA_ENTREGA WHERE ID_SOLICITUD = ? AND ID_TURNO = ? ORDER BY FECHA_CREACION DESC FETCH FIRST ROW ONLY";
	private static final String cs_FIND_BY_SOLICITUD = "SELECT * FROM SDB_ACC_PERSONA_ENTREGA WHERE ID_SOLICITUD = ?";
	private static final String cs_FIND_BY_TURNO     = "SELECT * FROM SDB_ACC_PERSONA_ENTREGA WHERE ID_TURNO = ? ORDER BY FECHA_CREACION DESC";
	private static final String cs_DELETE            = "DELETE FROM SDB_ACC_PERSONA_ENTREGA WHERE ID_SOLICITUD = ?";

	/**
	 * Elimina los registros de una solicitud especifica
	 *
	 * @param ape_param Objeto contenedor de la solicitud a eliminar de la tabla
	 * @throws B2BException
	 */
	public void delete(PersonaEntrega ape_param)
	    throws B2BException
	{
		if(ape_param != null)
		{
			PreparedStatement lps_ps;
			Connection        lc_connection;

			lps_ps            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = lc_connection.prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, ape_param.getIdSolicitud());

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
	}

	/**
	 * Metodo para la Busqueda de  registros en la tabla SDB_ACC_PERSONA_ENTREGA que
	 * coincidan con un id_solicitud especifico
	 */
	public Collection<PersonaEntrega> findBySolicitud(PersonaEntrega ape_param)
	    throws B2BException
	{
		Collection<PersonaEntrega> lcpe_cpe;

		lcpe_cpe = new ArrayList<PersonaEntrega>();

		if(ape_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD);

				lps_ps.setString(1, ape_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpe_cpe.add(getObjectFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcpe_cpe))
			lcpe_cpe = null;

		return lcpe_cpe;
	}

	/**
	 * Metodo para la Busqueda de  registros en la tabla SDB_ACC_PERSONA_ENTREGA que
	 * coincidan con un id_turno especifico.
	 *
	 * @param as_idTurno <code>String</code> con el cual se realizarán las búsquedas
	 * @return <code>PersonaEntrega</code> llena con la información de la BD.
	 * @throws <code>B2BException</code>
	 */
	public PersonaEntrega findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		PersonaEntrega lpe_pe;

		lpe_pe = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpe_pe = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpe_pe;
	}

	/**
	 * Metodo para la Busqueda de  registros en la tabla SDB_ACC_PERSONA_ENTREGA que
	 * coincidan con un id_solicitud y id_turno especifico
	 *
	 * @param String del idSolicitud
	 * @param String del idTurno
	 * @return Objeto de tipo PersonaEntrega
	 * @throws B2BException
	 */
	public PersonaEntrega findByTurnoSolicitud(String as_idSolicitud, String as_idTurno)
	    throws B2BException
	{
		PersonaEntrega lpe_pe;

		lpe_pe = null;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idTurno))
		{
			int               li_column;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);
				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpe_pe = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpe_pe;
	}

	/**
	 * Inserta un registro en la tabla SDB_ACC_PERSONA_ENTREGA
	 *
	 * @param ape_param Objeto contenedor de la información a insertar en el registro
	 * @throws B2BException
	 */
	public long insert(PersonaEntrega ape_param)
	    throws B2BException
	{
		long ll_secuencia;
		ll_secuencia = 0;

		if(ape_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			Connection        lc_connection;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lc_connection     = getConnection();
			lrs_rs            = null;

			try
			{
				int li_column;

				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuencia = lrs_rs.getLong(1);
					lps_ps.setLong(li_column++, ll_secuencia);
				}

				lps_ps.setString(li_column++, ape_param.getIdSolicitud());
				lps_ps.setString(li_column++, ape_param.getIdTurno());
				lps_ps.setString(li_column++, ape_param.getIdPersona());
				lps_ps.setString(li_column++, ape_param.getIdPersonaTercero());
				lps_ps.setString(li_column++, ape_param.getIdCalidadPersonaEntrega());
				lps_ps.setString(li_column++, ape_param.getIdAutorizacionComunicacion());
				lps_ps.setString(li_column++, ape_param.getIdAutorizacionNotificacion());
				lps_ps.setString(li_column++, ape_param.getIdDireccionPersonaEntrega());
				lps_ps.setString(li_column++, ape_param.getIdTelefonoPersonaEntrega());
				lps_ps.setString(li_column++, ape_param.getIdCorreoElectronicoPersonaEntrega());
				lps_ps.setString(li_column++, ape_param.getIdDireccionPersonaEntregaComunicacion());
				lps_ps.setString(li_column++, ape_param.getIdTelefonoPersonaEntregaComunicacion());
				lps_ps.setString(li_column++, ape_param.getIdCorreoElectronicoPersonaEntregaComunicacion());
				lps_ps.setString(li_column++, ape_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ape_param.getIpCreacion());

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

		return ll_secuencia;
	}

	/**
	 * Metodo de obtencion del objeto persona entrega dependiendo el valor
	 * de la sentencia realizada
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return
	 * @throws SQLException
	 */
	private PersonaEntrega getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		PersonaEntrega lpe_datos;

		lpe_datos = new PersonaEntrega();

		lpe_datos.setIdPersonaEntrega(ars_rs.getString("ID_PERSONA_ENTREGA"));
		lpe_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lpe_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lpe_datos.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lpe_datos.setIdPersonaTercero(ars_rs.getString("ID_PERSONA_TERCERO"));
		lpe_datos.setIdCalidadPersonaEntrega(ars_rs.getString("ID_CALIDAD_PERSONA_ENTREGA"));
		lpe_datos.setFechaEntrega(ars_rs.getDate("FECHA_ENTREGA"));
		lpe_datos.setIdAutorizacionComunicacion(ars_rs.getString("ID_AUTORIZACION_COMUNICACION"));
		lpe_datos.setIdAutorizacionNotificacion(ars_rs.getString("ID_AUTORIZACION_NOTIFICACION"));
		lpe_datos.setIdDireccionPersonaEntrega(ars_rs.getString("ID_DIRECCION_PERSONA_ENTREGA"));
		lpe_datos.setIdTelefonoPersonaEntrega(ars_rs.getString("ID_TELEFONO_PERSONA_ENTREGA"));
		lpe_datos.setIdCorreoElectronicoPersonaEntrega(ars_rs.getString("ID_CORREO_ELECTRONICO_PERSONA_ENTREGA"));
		lpe_datos.setIdDireccionPersonaEntregaComunicacion(
		    ars_rs.getString("ID_DIRECCION_PERSONA_ENTREGA_COMUNICACION")
		);
		lpe_datos.setIdTelefonoPersonaEntregaComunicacion(ars_rs.getString("ID_TELEFONO_PERSONA_ENTREGA_COMUNICACION"));
		lpe_datos.setIdCorreoElectronicoPersonaEntregaComunicacion(
		    ars_rs.getString("ID_CORREO_ELECTRONICO_PERSONA_ENTREGA_COMUNICACION")
		);
		lpe_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lpe_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lpe_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lpe_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lpe_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lpe_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lpe_datos;
	}
}
