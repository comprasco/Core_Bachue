package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaPersona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_ENTIDAD_EXTERNA_PERSONA.
 *
 * @author Sebastian Sanchez
 *
 */
public class AccEntidadExternaPersonaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA_PERSONA WHERE ID_ENTIDAD_EXTERNA = ? AND ID_PERSONA = ?";
	
	/** Constante cs_FIND_BY_ID_ENTIDAD_EXTERNA_PERSONA. */
	private static final String cs_FIND_BY_ID_ENTIDAD_EXTERNA_PERSONA = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA_PERSONA WHERE ID_ENTIDAD_EXTERNA_PERSONA = ?";

	/** Constante cs_FIND_BY_ID_ENTIDAD_EXTERNA. */
	private static final String cs_FIND_BY_ID_ENTIDAD_EXTERNA = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA_PERSONA WHERE ID_ENTIDAD_EXTERNA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ENTIDAD_EXTERNA_PERSONA(ID_ENTIDAD_EXTERNA_PERSONA, ID_ENTIDAD_EXTERNA, ID_PERSONA, REPRESENTANTE_LEGAL, USUARIO, ACTIVO,  ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,'S',?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_ENTIDAD_EXTERNA_PERSONA_ID_ENTIDAD_EXTERNA_PERSONA.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE_INACTIVO. */
	private static final String cs_UPDATE_INACTIVO = "UPDATE SDB_ACC_ENTIDAD_EXTERNA_PERSONA SET ACTIVO='N', IP_MODIFICACION=?, ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP WHERE ID_ENTIDAD_EXTERNA=?";

	/** Constante cs_UPDATE_ACTIVO. */
	private static final String cs_UPDATE_ACTIVO = "UPDATE SDB_ACC_ENTIDAD_EXTERNA_PERSONA SET ACTIVO='S', REPRESENTANTE_LEGAL = ?, IP_MODIFICACION=?, ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP WHERE ID_ENTIDAD_EXTERNA = ? AND ID_PERSONA = ?";

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_idEntidad Objeto contenedor de los filtros a usar en la consulta
	 * @param as_idPersona Objeto contenedor de los filtros a usar en la consulta
	 * @return AccEntidadExternaPersona resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccEntidadExternaPersona findById(String as_idEntidad, String as_idPersona)
	    throws B2BException
	{
		AccEntidadExternaPersona laeep_entidadExternaPersona;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		laeep_entidadExternaPersona     = null;
		lps_ps                          = null;
		lrs_rs                          = null;

		try
		{
			if(StringUtils.isValidString(as_idEntidad) && StringUtils.isValidString(as_idPersona))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idEntidad);
				lps_ps.setString(2, as_idPersona);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laeep_entidadExternaPersona = getObjetFromResultSet(lrs_rs);
			}
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

		return laeep_entidadExternaPersona;
	}
	
	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_idEntidadExternaPersona Objeto contenedor de los filtros a usar en la consulta
	 * @return AccEntidadExternaPersona resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccEntidadExternaPersona findByIdEntidadExternaPersona(String as_idEntidadExternaPersona)
	    throws B2BException
	{
		AccEntidadExternaPersona laeep_entidadExternaPersona;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		laeep_entidadExternaPersona     = null;
		lps_ps                          = null;
		lrs_rs                          = null;

		try
		{
			if(StringUtils.isValidString(as_idEntidadExternaPersona))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ENTIDAD_EXTERNA_PERSONA);

				lps_ps.setString(1, as_idEntidadExternaPersona);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laeep_entidadExternaPersona = getObjetFromResultSet(lrs_rs);
			}
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

		return laeep_entidadExternaPersona;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @param ab_accion Objeto contenedor de los filtros a usar en la consulta
	 * @return Collection de AccEntidadExternaPersona resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExternaPersona> findByIdEntidadExterna(String as_param, boolean ab_accion)
	    throws B2BException
	{
		Collection<AccEntidadExternaPersona> lcaeep_entidadExternaPersona;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcaeep_entidadExternaPersona     = new ArrayList<AccEntidadExternaPersona>();
		lps_ps                           = null;
		lrs_rs                           = null;

		try
		{
			if(StringUtils.isValidString(as_param))
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder(cs_FIND_BY_ID_ENTIDAD_EXTERNA);

				if(ab_accion)
					lsb_sb.append(" AND ACTIVO ='S' ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaeep_entidadExternaPersona.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdEntidadExterna", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcaeep_entidadExternaPersona.isEmpty())
			lcaeep_entidadExternaPersona = null;

		return lcaeep_entidadExternaPersona;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param aaeep_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(AccEntidadExternaPersona aaeep_param)
	    throws B2BException
	{
		if(aaeep_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_C;

				lc_C       = getConnection();
				lps_ps     = lc_C.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				{
					lps_ps.setString(li_column++, aaeep_param.getIdEntidadExterna());
					lps_ps.setString(li_column++, aaeep_param.getIdPersona());
					lps_ps.setString(li_column++, aaeep_param.getRepresentanteLegal());
					lps_ps.setString(li_column++, aaeep_param.getUsuario());
					lps_ps.setString(li_column++, aaeep_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aaeep_param.getIpCreacion());
				}

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
				close(lrs_rs);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Método para modificar el campo ACTIVO de la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO
	 * @param as_ipModificacion parametro contenedor de la ip
	 * @param as_usuarioModificacion parametro contenedor del usuario
	 * @throws B2BException
	 */
	public void updateInactivo(String as_ipModificacion, String as_usuarioModificacion, String as_idEntidadExterna)
	    throws B2BException
	{
		if(
		    StringUtils.isValidString(as_ipModificacion) && StringUtils.isValidString(as_usuarioModificacion)
			    && StringUtils.isValidString(as_idEntidadExterna)
		)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_INACTIVO);

				lps_ps.setString(li_column++, as_ipModificacion);
				lps_ps.setString(li_column++, as_usuarioModificacion);

				lps_ps.setString(li_column++, as_idEntidadExterna);

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateInactivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para modificar el campo ACTIVO de la tabla SDB_ACC_ENTIDAD_EXTERNA_PERSONA
	 * @param aaee_entidadExternaPersona Objeto AccEntidadExternaPersona contenedor de los parametros
	 * @throws B2BException
	 */
	public void updateActivo(AccEntidadExternaPersona aaee_entidadExternaPersona)
	    throws B2BException
	{
		if(aaee_entidadExternaPersona != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ACTIVO);

				lps_ps.setString(li_column++, aaee_entidadExternaPersona.getRepresentanteLegal());
				lps_ps.setString(li_column++, aaee_entidadExternaPersona.getIpModificacion());
				lps_ps.setString(li_column++, aaee_entidadExternaPersona.getIdUsuarioModificacion());

				lps_ps.setString(li_column++, aaee_entidadExternaPersona.getIdEntidadExterna());
				lps_ps.setString(li_column++, aaee_entidadExternaPersona.getIdPersona());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateActivo", lse_e);

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
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AccEntidadExternaPersona getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		AccEntidadExternaPersona ls_solicitud;

		ls_solicitud = new AccEntidadExternaPersona();

		ls_solicitud.setIdEntidadExternaPersona(lrs_rs.getString("ID_ENTIDAD_EXTERNA_PERSONA"));
		ls_solicitud.setIdEntidadExterna(lrs_rs.getString("ID_ENTIDAD_EXTERNA"));
		ls_solicitud.setIdPersona(lrs_rs.getString("ID_PERSONA"));
		ls_solicitud.setRepresentanteLegal(lrs_rs.getString("REPRESENTANTE_LEGAL"));
		ls_solicitud.setUsuario(lrs_rs.getString("USUARIO"));
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
