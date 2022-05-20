package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades PersonaTelefonoDAO.
 *
 * @author  hcastaneda
 * Fecha de Creacion: 24/08/2020
 */
public class PersonaTelefonoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_PERSONA,ID_TELEFONO,TIPO_TELEFONO,"
		+ " ID_PAIS,ID_DEPARTAMENTO,TELEFONO,FECHA_DESDE,FECHA_HASTA,ID_USUARIO_CREACION,FECHA_CREACION "
		+ " FROM SDB_ACC_PERSONA_TELEFONO WHERE ID_PERSONA = ? AND ID_TELEFONO = ?";

	/** Constante cs_FIND_BY_ID_PERSONA. */
	private static final String cs_FIND_BY_ID_PERSONA = "SELECT * FROM SDB_ACC_PERSONA_TELEFONO "
		+ " WHERE ID_PERSONA = ?";

	/** Constante cs_FIND_MAX_BY_ID_PERSONA. */
	private static final String cs_FIND_MAX_BY_ID_PERSONA = "SELECT * FROM SDB_ACC_PERSONA_TELEFONO WHERE ID_TELEFONO = (SELECT MAX(TO_NUMBER(ID_TELEFONO)) FROM SDB_ACC_PERSONA_TELEFONO WHERE ID_PERSONA = ?) AND ID_PERSONA = ? ";

	/** Constante cs_FIND_BY_ID_PERSONA_TELEFONO. */
	private static final String cs_FIND_BY_ID_PERSONA_TELEFONO = "SELECT * FROM SDB_ACC_PERSONA_TELEFONO WHERE ID_PERSONA = ? AND TELEFONO = ? AND ACTIVO = 'S' ORDER BY TO_NUMBER(ID_TELEFONO) DESC";

	/** Constante cs_FIND_BY_PERSONA_TELEFONO. */
	private static final String cs_FIND_BY_PERSONA_TELEFONO = "SELECT * FROM SDB_ACC_PERSONA_TELEFONO "
		+ "WHERE ID_PERSONA = ? AND TIPO_TELEFONO = ? ORDER BY ID_TELEFONO DESC";

	/** Constante cs_FIND_BY_ID_PERSONA_TIPO. */
	private static final String cs_FIND_BY_ID_PERSONA_TIPO = "SELECT * FROM SDB_ACC_PERSONA_TELEFONO "
		+ " WHERE ID_PERSONA = ? AND TIPO_TELEFONO = ? AND ACTIVO = 'S' ";

	/** Constante cs_FIND_MAX_ID_TELEFONO. */
	private static final String cs_FIND_MAX_ID_TELEFONO = "SELECT MAX(TO_NUMBER(ID_TELEFONO))"
		+ " FROM SDB_ACC_PERSONA_TELEFONO WHERE ID_PERSONA = ?";

	/** Constante cs_FIND_MAX_BY_ID_TELEFONO_TIPO_TELEFONO. */
	private static final String cs_FIND_MAX_BY_ID_TELEFONO_TIPO_TELEFONO = "SELECT MAX(TO_NUMBER(ID_TELEFONO))"
		+ " FROM SDB_ACC_PERSONA_TELEFONO WHERE ID_PERSONA = ? AND TIPO_TELEFONO = ?";

	/** Constante cs_FIND_MAX_BY_ID_TELEFONO_TIPO_TELEFONO_TELEFONO. */
	private static final String cs_FIND_MAX_BY_ID_TELEFONO_TIPO_TELEFONO_TELEFONO = "SELECT * FROM SDB_ACC_PERSONA_TELEFONO WHERE ID_PERSONA = ? AND TIPO_TELEFONO = ? AND TELEFONO = ? ORDER BY ID_TELEFONO DESC FETCH FIRST 1 ROW ONLY";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_PERSONA_TELEFONO SET "
		+ " TIPO_TELEFONO = ?,ID_PAIS = ?,ID_DEPARTAMENTO = ?,TELEFONO = ?,FECHA_DESDE = SYSTIMESTAMP,FECHA_HASTA = ?,"
		+ " ID_USUARIO_MODIFICACION = ? ,FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_PERSONA = ? AND ID_TELEFONO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PERSONA_TELEFONO (ID_PERSONA,"
		+ " ID_TELEFONO,TIPO_TELEFONO,ID_PAIS,ID_DEPARTAMENTO,TELEFONO,FECHA_DESDE,FECHA_HASTA,ID_USUARIO_CREACION,"
		+ " FECHA_CREACION,IP_CREACION) " + " VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?,?,SYSTIMESTAMP,?)";

	/**
	 * Instancia un nuevo objeto persona telefono DAO.
	 */
	public PersonaTelefonoDAO()
	{
	}

	/**
	 * Consulta un registro en la tabla por medio de su identificador unico.
	 *
	 * @param apt_parametros Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return PersonaTelefono resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public PersonaTelefono findById(PersonaTelefono apt_parametros)
	    throws B2BException
	{
		return (apt_parametros != null) ? findById(apt_parametros.getIdPersona(), apt_parametros.getIdTelefono()) : null;
	}

	/**
	 * Consulta un registro en la tabla por medio de su identificador unico.
	 *
	 * @param as_idPersona Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param as_idTelefono Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return PersonaTelefono resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public PersonaTelefono findById(String as_idPersona, String as_idTelefono)
	    throws B2BException
	{
		PersonaTelefono lpt_telefono;

		lpt_telefono = null;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_idTelefono))
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
				lps_ps.setString(li_contador++, as_idTelefono);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpt_telefono = getPersonaTelefono(lrs_rs);
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

		return lpt_telefono;
	}

	/**
	 * Retorna el valor del objeto de Collection de PersonaTelefono filtrado por ID persona.
	 *
	 * @param as_idPersona correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de PersonaTelefono
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public Collection<PersonaTelefono> findByIdPersona(String as_idPersona)
	    throws B2BException
	{
		Collection<PersonaTelefono> lpce_datos;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lpce_datos     = new ArrayList<PersonaTelefono>();
		lps_ps         = null;
		lrs_rs         = null;

		if(StringUtils.isValidString(as_idPersona))
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_PERSONA);

				lps_ps.setString(li_contador++, as_idPersona);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lpce_datos.add(getPersonaTelefono(lrs_rs));
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

		if(lpce_datos.isEmpty())
			lpce_datos = null;

		return lpce_datos;
	}

	/**
	 * Consulta el máximo teléfono por el id persona y el número de teléfono.
	 *
	 * @param as_idPersona Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param as_telefono Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return PersonaTelefono resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public PersonaTelefono findByIdPersonaTelefonoMax(String as_idPersona, String as_telefono)
	    throws B2BException
	{
		PersonaTelefono lpt_telefono;

		lpt_telefono = null;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_telefono))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_PERSONA_TELEFONO);

				lps_ps.setString(li_contador++, as_idPersona);
				lps_ps.setString(li_contador++, as_telefono);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpt_telefono = getPersonaTelefono(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPersonaTelefono", lse_e);

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
	 * Consulta todos los telefonos asociados a una persona de un tipo específico.
	 *
	 * @param apt_parametros Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public Collection<PersonaTelefono> findByIdPersonaTipo(PersonaTelefono apt_parametros)
	    throws B2BException
	{
		Collection<PersonaTelefono> lpce_datos;

		lpce_datos = new ArrayList<PersonaTelefono>();

		if(apt_parametros != null)
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
				lsb_query       = new StringBuilder(cs_FIND_BY_ID_PERSONA_TIPO);

				lsb_query.append(" ORDER BY TELEFONO ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, apt_parametros.getIdPersona());
				lps_ps.setString(li_contador++, apt_parametros.getTipoTelefono());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lpce_datos.add(getPersonaTelefono(lrs_rs));
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

		if(lpce_datos.isEmpty())
			lpce_datos = null;

		return lpce_datos;
	}

	/**
	 * Consulta todos los registros asociados a una persona que coincidan con un número de teléfono específico.
	 *
	 * @param as_idPersona Id de la persona
	 * @param as_tipoTelefono Indicador de teléfono movil y fijo
	 * @param as_telefono número de telefono a buscar
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public Collection<PersonaTelefono> findByIdPersonaTipoTelefono(
	    String as_idPersona, String as_tipoTelefono, String as_telefono
	)
	    throws B2BException
	{
		Collection<PersonaTelefono> lpce_datos;

		lpce_datos = new ArrayList<PersonaTelefono>();

		if(
		    StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_tipoTelefono)
			    && StringUtils.isValidString(as_telefono)
		)
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
				lsb_query       = new StringBuilder(cs_FIND_BY_ID_PERSONA_TIPO);

				lsb_query.append(" AND TELEFONO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_idPersona);
				lps_ps.setString(li_contador++, as_tipoTelefono);
				lps_ps.setString(li_contador++, as_telefono);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lpce_datos.add(getPersonaTelefono(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPersonaTipoTelefono", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lpce_datos.isEmpty())
			lpce_datos = null;

		return lpce_datos;
	}

	/**
	 * Retorna el valor del objeto de int con el maximo id del telefono.
	 *
	 * @param apd_parametros correspondiente al valor del tipo de objeto PersonaTelefono
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findIdTelefono(PersonaTelefono apd_parametros)
	    throws B2BException
	{
		int               li_idTelefono;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_idTelefono     = 0;
		lps_ps            = null;
		lrs_rs            = null;

		if(apd_parametros != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_ID_TELEFONO);

				lps_ps.setString(1, apd_parametros.getIdPersona());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_idTelefono = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findIdTelefono", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_idTelefono;
	}

	/**
	 * Consulta el máximo registro en la tabla para un id persona por medio de su identificador unico.
	 *
	 * @param as_idPersona Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return PersonaTelefono resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public PersonaTelefono findMaxByIdPersona(String as_idPersona)
	    throws B2BException
	{
		PersonaTelefono lpt_telefono;

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
					lpt_telefono = getPersonaTelefono(lrs_rs);
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
	 * Find max by id telefono tipo telefono telefono.
	 *
	 * @param as_idPersona de as id persona
	 * @param as_tipoTelefono de as tipo telefono
	 * @param as_telefono de as telefono
	 * @return el valor de persona telefono
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PersonaTelefono findMaxByIdTelefonoTipoTelefonoTelefono(
	    String as_idPersona, String as_tipoTelefono, String as_telefono
	)
	    throws B2BException
	{
		PersonaTelefono lpt_personaTelefono;

		lpt_personaTelefono = null;

		if(
		    StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_tipoTelefono)
			    && StringUtils.isValidString(as_telefono)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_BY_ID_TELEFONO_TIPO_TELEFONO_TELEFONO);

				lps_ps.setString(1, as_idPersona);
				lps_ps.setString(2, as_tipoTelefono);
				lps_ps.setString(3, as_telefono);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpt_personaTelefono = getPersonaTelefono(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxByIdTelefonoTipoTelefonoTelefono", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpt_personaTelefono;
	}

	/**
	 * Retorna el valor del objeto de int con el maximo id del telefono.
	 *
	 * @param apd_parametros correspondiente al valor del tipo de objeto PersonaTelefono
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findMaxIdTelefonoByTipoTel(PersonaTelefono apd_parametros)
	    throws B2BException
	{
		int               li_idTelefono;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_idTelefono     = 0;
		lps_ps            = null;
		lrs_rs            = null;

		if(apd_parametros != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_BY_ID_TELEFONO_TIPO_TELEFONO);

				lps_ps.setString(1, apd_parametros.getIdPersona());
				lps_ps.setString(2, apd_parametros.getTipoTelefono());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_idTelefono = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxIdTelefonoByTipoTel", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_idTelefono;
	}

	/**
	 * Retorna el valor del objeto de PersonaTelefono.
	 *
	 * @param apt_pt correspondiente al valor del tipo de objeto PersonaTelefono
	 * @param as_tipoTel correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de PersonaTelefono
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaTelefono
	 */
	public PersonaTelefono findTelefonoByIdPersona(PersonaTelefono apt_pt, String as_tipoTel)
	    throws B2BException
	{
		PersonaTelefono   lpt_datos;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpt_datos     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if((apt_pt != null) && StringUtils.isValidString(as_tipoTel))
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PERSONA_TELEFONO);

				lps_ps.setString(li_contador++, apt_pt.getIdPersona());
				lps_ps.setString(li_contador++, as_tipoTel);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpt_datos = getPersonaTelefono(lrs_rs);
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

		return lpt_datos;
	}

	/**
	 * Retorna el valor del objeto de long con el ID del registro.
	 *
	 * @param apt_parametros correspondiente al valor del tipo de objeto PersonaTelefono
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insertOrUpdate(PersonaTelefono apt_parametros, boolean ab_query)
	    throws B2BException
	{
		long ll_max;

		ll_max = 0;

		if(apt_parametros != null)
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

				li_column         = 1;
				lc_connection     = getConnection();
				lps_ps            = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
				lps_max           = lc_connection.prepareStatement(cs_FIND_MAX_ID_TELEFONO);

				if(ab_query)
				{
					String ls_idPersona;

					ls_idPersona = apt_parametros.getIdPersona();

					lps_ps.setString(li_column++, ls_idPersona);
					lps_max.setString(1, ls_idPersona);

					lrs_rs = lps_max.executeQuery();

					if(lrs_rs.next())
					{
						ll_max     = lrs_rs.getLong(1);
						ll_max     = ll_max + 1;

						lps_ps.setString(li_column++, StringUtils.getString(ll_max));
					}
				}

				lps_ps.setString(li_column++, apt_parametros.getTipoTelefono());
				lps_ps.setString(li_column++, apt_parametros.getIdPais());
				lps_ps.setString(li_column++, apt_parametros.getIdDepartamento());
				lps_ps.setString(li_column++, apt_parametros.getTelefono());
//				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(apt_parametros.getFechaDesde()));
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(apt_parametros.getFechaHasta()));

				if(ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apt_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIpModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIdPersona());
					lps_ps.setString(li_column++, apt_parametros.getIdTelefono());
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

		return ll_max;
	}

	/**
	 * Retorna el valor de persona telefono.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de persona telefono
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PersonaTelefono getPersonaTelefono(ResultSet ars_rs)
	    throws SQLException
	{
		PersonaTelefono lpce_correo;
		lpce_correo = new PersonaTelefono();

		lpce_correo.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lpce_correo.setIdTelefono(ars_rs.getString("ID_TELEFONO"));
		lpce_correo.setTipoTelefono(ars_rs.getString("TIPO_TELEFONO"));
		lpce_correo.setIdPais(ars_rs.getString("ID_PAIS"));
		lpce_correo.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lpce_correo.setTelefono(ars_rs.getString("TELEFONO"));
		lpce_correo.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		lpce_correo.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));
		lpce_correo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lpce_correo.setFechaHasta(ars_rs.getTimestamp("FECHA_CREACION"));

		return lpce_correo;
	}
}
