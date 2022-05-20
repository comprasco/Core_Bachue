package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades de PersonaCorreoElectronicoDAO.
 *
 * @author hcastaneda
 */
public class PersonaCorreoElectronicoDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_PERSONA,ID_CORREO_ELECTRONICO,CORREO_ELECTRONICO,"
		+ " FECHA_DESDE,FECHA_HASTA,ID_USUARIO_CREACION,FECHA_CREACION "
		+ " FROM SDB_ACC_PERSONA_CORREO_ELECTRONICO WHERE ID_PERSONA = ? AND ID_CORREO_ELECTRONICO = ?";

	/** Constante cs_FIND_BY_ID_PERSONA_CORREO. */
	private static final String cs_FIND_BY_ID_PERSONA_CORREO = "SELECT * FROM SDB_ACC_PERSONA_CORREO_ELECTRONICO WHERE ID_PERSONA = ? AND CORREO_ELECTRONICO = ? AND ACTIVO = 'S' ORDER BY TO_NUMBER(ID_CORREO_ELECTRONICO) DESC";

	/** Constante cs_FIND_BY_ID_PERSONA. */
	private static final String cs_FIND_BY_ID_PERSONA = "SELECT * FROM SDB_ACC_PERSONA_CORREO_ELECTRONICO "
		+ " WHERE ID_PERSONA = ? AND ACTIVO = 'S' ";

	/** Constante cs_FIND_BY_PERSONA_CORREO. */
	private static final String cs_FIND_BY_PERSONA_CORREO = "SELECT * FROM SDB_ACC_PERSONA_CORREO_ELECTRONICO "
		+ "WHERE ID_PERSONA = ? ORDER BY ID_CORREO_ELECTRONICO DESC";

	/** Constante cs_FIND_BY_PERSONA_SOLICITUD. */
	private static final String cs_FIND_BY_PERSONA_SOLICITUD = "SELECT CE.* FROM SDB_ACC_PERSONA_CORREO_ELECTRONICO CE INNER JOIN SDB_ACC_SOLICITUD_INTERVINIENTE SI ON CE.ID_PERSONA = SI.ID_PERSONA AND CE.ID_CORREO_ELECTRONICO = SI.ID_CORREO_ELECTRONICO WHERE SI.ID_PERSONA = ? AND SI.ID_SOLICITUD = ?";

	/** Constante cs_FIND_MAX_ID_CORREO. */
	private static final String cs_FIND_MAX_ID_CORREO = "SELECT MAX(TO_NUMBER(ID_CORREO_ELECTRONICO))"
		+ " FROM SDB_ACC_PERSONA_CORREO_ELECTRONICO WHERE ID_PERSONA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_PERSONA_CORREO_ELECTRONICO SET "
		+ " CORREO_ELECTRONICO = ?,FECHA_DESDE = ?,FECHA_HASTA = ?,ID_USUARIO_MODIFICACION = ? ,FECHA_MODIFICACION = SYSTIMESTAMP , IP_MODIFICACION = ? "
		+ " WHERE ID_PERSONA = ? AND ID_CORREO_ELECTRONICO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PERSONA_CORREO_ELECTRONICO (ID_PERSONA,"
		+ " ID_CORREO_ELECTRONICO,CORREO_ELECTRONICO,FECHA_DESDE,FECHA_HASTA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) "
		+ " VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_MAX_BY_ID_PERSONA. */
	private static final String cs_FIND_MAX_BY_ID_PERSONA = "SELECT * FROM SDB_ACC_PERSONA_CORREO_ELECTRONICO WHERE ID_CORREO_ELECTRONICO = (SELECT MAX(TO_NUMBER(ID_CORREO_ELECTRONICO)) FROM SDB_ACC_PERSONA_CORREO_ELECTRONICO WHERE ID_PERSONA = ?) AND ID_PERSONA = ? ";

	/**
	 * Instancia un nuevo objeto persona correo electronico DAO.
	 */
	public PersonaCorreoElectronicoDAO()
	{
	}

	/**
	 * Busca un persona correo electrónico asociado a un id específico.
	 *
	 * @param apce_parametros Objeto contenedor de los parametros a utilizar como filtro en la busqueda
	 * @return Persona correo electrónico resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaCorreoElectronico
	 */
	public PersonaCorreoElectronico findById(PersonaCorreoElectronico apce_parametros)
	    throws B2BException
	{
		return (apce_parametros != null)
		? findById(apce_parametros.getIdPersona(), apce_parametros.getIdCorreoElectronico()) : null;
	}

	/**
	 * Busca un persona correo electrónico asociado a un id específico.
	 *
	 * @param as_idPersona id de la persona asociado al correo
	 * @param as_idCorreoElectronico id del correo
	 * @return Persona correo electrónico resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaCorreoElectronico
	 */
	public PersonaCorreoElectronico findById(String as_idPersona, String as_idCorreoElectronico)
	    throws B2BException
	{
		PersonaCorreoElectronico lpce_correo;

		lpce_correo = null;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_idCorreoElectronico))
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

				lps_ps.setString(li_contador++, as_idPersona);
				lps_ps.setString(li_contador++, as_idCorreoElectronico);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getPersonaCorreoElectronico(lrs_rs);
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

		return lpce_correo;
	}

	/**
	 * Consulta todos los correos electrónicos asociados a un id persona.
	 *
	 * @param as_idPersona Objeto contenedor del id persona a utilizar como filtro en la busqueda
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaCorreoElectronico
	 */
	public Collection<PersonaCorreoElectronico> findByIdPersona(String as_idPersona)
	    throws B2BException
	{
		Collection<PersonaCorreoElectronico> lpce_correo;

		lpce_correo = new ArrayList<PersonaCorreoElectronico>();

		if(StringUtils.isValidString(as_idPersona))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder(cs_FIND_BY_ID_PERSONA);

				lsb_query.append(" ORDER BY CORREO_ELECTRONICO ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_idPersona);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lpce_correo.add(getPersonaCorreoElectronico(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lpce_correo.isEmpty())
			lpce_correo = null;

		return lpce_correo;
	}

	/**
	 * Consulta todos los correos electrónicos asociados a un id persona que coincidan con un correo específico.
	 *
	 * @param as_idPersona Objeto contenedor del id persona a utilizar como filtro en la busqueda
	 * @param as_correo Objeto contenedor del correo electrónico a utilizar como filtro en la busqueda
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaCorreoElectronico
	 */
	public Collection<PersonaCorreoElectronico> findByIdPersonaCorreo(String as_idPersona, String as_correo)
	    throws B2BException
	{
		Collection<PersonaCorreoElectronico> lpce_correo;

		lpce_correo = new ArrayList<PersonaCorreoElectronico>();

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_correo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder(cs_FIND_BY_ID_PERSONA);

				lsb_query.append(" AND CORREO_ELECTRONICO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_idPersona);
				lps_ps.setString(li_contador++, as_correo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lpce_correo.add(getPersonaCorreoElectronico(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPersonaCorreo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lpce_correo.isEmpty())
			lpce_correo = null;

		return lpce_correo;
	}

	/**
	 * Busca un persona correo electrónico asociado a un id específico.
	 *
	 * @param as_idPersona id de la persona asociado al correo
	 * @param as_correoElectronico id del correo
	 * @return Persona correo electrónico resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaCorreoElectronico
	 */
	public PersonaCorreoElectronico findByIdPersonaCorreoMax(String as_idPersona, String as_correoElectronico)
	    throws B2BException
	{
		PersonaCorreoElectronico lpce_correo;

		lpce_correo = null;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_correoElectronico))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_PERSONA_CORREO);

				lps_ps.setString(li_contador++, as_idPersona);
				lps_ps.setString(li_contador++, as_correoElectronico);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getPersonaCorreoElectronico(lrs_rs);
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

		return lpce_correo;
	}

	/**
	 * Busca un persona correo electrónico asociado a un id persona y un id solicitud.
	 *
	 * @param as_idPersona id de la persona asociado al correo
	 * @param as_idSolicitud id de la solicitud
	 * @return Persona correo electrónico resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaCorreoElectronico
	 */
	public PersonaCorreoElectronico findByPersonaSolicitud(String as_idPersona, String as_idSolicitud)
	    throws B2BException
	{
		PersonaCorreoElectronico lpce_correo;

		lpce_correo = null;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PERSONA_SOLICITUD);

				lps_ps.setString(li_contador++, as_idPersona);
				lps_ps.setString(li_contador++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getPersonaCorreoElectronico(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByPersonaSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpce_correo;
	}

	/**
	 * Retorna el valor del objeto de PersonaCorreoElectronico.
	 *
	 * @param alpce_correo correspondiente al valor del tipo de objeto PersonaCorreoElectronico
	 * @return devuelve el valor de PersonaCorreoElectronico
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaCorreoElectronico
	 */
	public PersonaCorreoElectronico findCorreoByIdPersona(PersonaCorreoElectronico alpce_correo)
	    throws B2BException
	{
		PersonaCorreoElectronico lpce_correo;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lpce_correo     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(alpce_correo != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PERSONA_CORREO);

				lps_ps.setString(li_contador++, alpce_correo.getIdPersona());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getPersonaCorreoElectronico(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpce_correo;
	}

	/**
	 * Retorna el valor del objeto de int.
	 *
	 * @param ace_parametros correspondiente al valor del tipo de objeto PersonaCorreoElectronico
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findIdCorreoElectronico(PersonaCorreoElectronico ace_parametros)
	    throws B2BException
	{
		int               li_idCorreo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_idCorreo     = 0;
		lps_ps          = null;
		lrs_rs          = null;

		if(ace_parametros != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_ID_CORREO);

				lps_ps.setString(1, ace_parametros.getIdPersona());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_idCorreo = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findIdCorreoElectronico", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_idCorreo;
	}

	/**
	 * Consulta el máximo registro en la tabla para un id persona por medio de su identificador unico.
	 *
	 * @param as_idPersona Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return PersonaCorreoElectronico resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public PersonaCorreoElectronico findMaxByIdPersona(String as_idPersona)
	    throws B2BException
	{
		PersonaCorreoElectronico lpt_telefono;

		lpt_telefono = null;

		if(StringUtils.isValidString(as_idPersona))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_BY_ID_PERSONA);

				lps_ps.setString(li_contador++, as_idPersona);
				lps_ps.setString(li_contador++, as_idPersona);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpt_telefono = getPersonaCorreoElectronico(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxByIdPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpt_telefono;
	}

	/**
	 * Retorna el valor del objeto de long.
	 *
	 * @param apce_parametros correspondiente al valor del tipo de objeto PersonaCorreoElectronico
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insertOrUpdate(PersonaCorreoElectronico apce_parametros, boolean ab_query)
	    throws B2BException
	{
		long ll_maxCorreo;

		ll_maxCorreo = 0;

		if(apce_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_max;
			ResultSet         lrs_rs;

			lps_ps      = null;
			lps_max     = null;
			lrs_rs      = null;

			try
			{
				int        li_column;
				Connection lc_connection;
				String     ls_query;

				li_column         = 1;
				lc_connection     = getConnection();
				ls_query          = ab_query ? cs_INSERT : cs_UPDATE;
				lps_ps            = lc_connection.prepareStatement(ls_query);
				lps_max           = lc_connection.prepareStatement(cs_FIND_MAX_ID_CORREO);

				if(ab_query)
				{
					String ls_idPersona;

					ls_idPersona = apce_parametros.getIdPersona();

					lps_ps.setString(li_column++, ls_idPersona);
					lps_max.setString(1, ls_idPersona);

					lrs_rs = lps_max.executeQuery();

					if(lrs_rs.next())
					{
						ll_maxCorreo     = lrs_rs.getLong(1);
						ll_maxCorreo     = ll_maxCorreo + 1;

						apce_parametros.setIdCorreoElectronico("" + ll_maxCorreo);
						lps_ps.setString(li_column++, StringUtils.getString(ll_maxCorreo));
					}
				}

				lps_ps.setString(li_column++, apce_parametros.getCorreoElectronico());
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(apce_parametros.getFechaDesde()));
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(apce_parametros.getFechaHasta()));

				if(ab_query)
				{
					lps_ps.setString(li_column++, apce_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apce_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, apce_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apce_parametros.getIpModificacion());
					lps_ps.setString(li_column++, apce_parametros.getIdPersona());
					lps_ps.setString(li_column++, apce_parametros.getIdCorreoElectronico());
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
				close(lps_max);
				close(lrs_rs);
			}
		}

		return ll_maxCorreo;
	}

	/**
	 * Retorna el valor de persona correo electronico.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de persona correo electronico
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PersonaCorreoElectronico getPersonaCorreoElectronico(ResultSet ars_rs)
	    throws SQLException
	{
		PersonaCorreoElectronico lpce_correo;
		lpce_correo = new PersonaCorreoElectronico();

		lpce_correo.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lpce_correo.setIdCorreoElectronico(ars_rs.getString("ID_CORREO_ELECTRONICO"));
		lpce_correo.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));
		lpce_correo.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		lpce_correo.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));
		lpce_correo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lpce_correo.setFechaHasta(ars_rs.getTimestamp("FECHA_CREACION"));

		return lpce_correo;
	}
}
