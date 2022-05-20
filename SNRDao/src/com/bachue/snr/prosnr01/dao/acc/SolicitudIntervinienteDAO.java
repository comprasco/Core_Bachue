package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_SOLICITUD_INTERVINIENTE
 *
 * @author ccalderon
 */
public class SolicitudIntervinienteDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SOLICITUD_INTERVINIENTE "
		+ " WHERE ID_SOLICITUD=? AND ID_PERSONA=?";

	/** Constante cs_FIND_SOLICITUD_PERSONA_ROL. */
	private static final String cs_FIND_SOLICITUD_PERSONA_ROL = "SELECT * FROM SDB_ACC_SOLICITUD_INTERVINIENTE "
		+ " WHERE ID_SOLICITUD=? AND ID_PERSONA=? AND ROL_PERSONA=?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_SOLICITUD_INTERVINIENTE WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_ULTIMO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ULTIMO = "SELECT * FROM SDB_ACC_SOLICITUD_INTERVINIENTE WHERE ID_SOLICITUD = ? AND ROWNUM = 1  ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FINS_BY_ID_SOLICITUD_ULTIMO_MODIFICADO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ULTIMO_MODIFICADO = "SELECT * FROM SDB_ACC_SOLICITUD_INTERVINIENTE WHERE ID_SOLICITUD = ? AND FECHA_MODIFICACION IS NOT NULL ORDER BY FECHA_MODIFICACION DESC";

	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = "  SELECT  SAP.ID_DOCUMENTO_TIPO ,SAP.NUMERO_DOCUMENTO, "
		+ " NVL(TRIM(SAP.PRIMER_NOMBRE) || ' ' || TRIM(SAP.SEGUNDO_NOMBRE)|| ' ' || TRIM(SAP.PRIMER_APELLIDO)|| ' ' || TRIM(SAP.SEGUNDO_APELLIDO),SAP.RAZON_SOCIAL) NOMBRE"
		+ " FROM SDB_ACC_SOLICITUD_INTERVINIENTE ASI INNER JOIN SDB_ACC_PERSONA SAP ON SAP.ID_PERSONA = ASI.ID_PERSONA  WHERE ASI.ID_SOLICITUD=? AND TRIM(ASI.ROL_PERSONA) = 'A'";

	/** Constante cs_FIND_BY_SOLICITUD_PERSONA. */
	private static final String cs_FIND_BY_SOLICITUD_PERSONA = "SELECT SI.ID_SOLICITUD,SI.ID_PERSONA,SI.ROL_PERSONA,"
		+ " P.ID_DOCUMENTO_TIPO,P.NUMERO_DOCUMENTO,P.PRIMER_NOMBRE,P.SEGUNDO_NOMBRE,P.PRIMER_APELLIDO,P.SEGUNDO_APELLIDO,P.RAZON_SOCIAL, UPPER (NVL(TR.NOMBRE,'')) NOMBRE "
		+ " FROM SDB_ACC_SOLICITUD_INTERVINIENTE SI INNER JOIN SDB_ACC_SOLICITUD S ON (S.ID_SOLICITUD = SI.ID_SOLICITUD) INNER JOIN SDB_ACC_PERSONA P ON (SI.ID_PERSONA = P.ID_PERSONA)"
		+ " LEFT JOIN SDB_ACC_TIPO_RECEPCION TR ON  TR.ID_TIPO_RECEPCION = S.ID_AUTORIZACION_NOTIFICACION WHERE SI.ID_SOLICITUD = ?";

	/** Constante cs_CODIGO_CON_MAS_DE_UN_INTERVINIENTE. */
	private static final String cs_CODIGO_CON_MAS_DE_UN_INTERVINIENTE = "SELECT nvl(INSTR(cons.CARACTER, CONCAT(act.ID_TIPO_ACTO, ',' )),0) RESULTADO FROM SDB_PGN_CONSTANTES cons, SDB_ACC_ACTO act "
		+ "WHERE cons.id_constante = 'CODIGO_DE_ACTO_CON_MAS_DE_UN_INTERVINIENTE' AND act.id_solicitud = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_INTERVINIENTE SET ROL_PERSONA=?, "
		+ " ID_AUTORIZACION_COMUNICACION = ?, ID_AUTORIZACION_NOTIFICACION = ?, ID_DIRECCION = ?, "
		+ " ID_TELEFONO = ?, ID_CORREO_ELECTRONICO = ?, ID_DIRECCION_COMUNICACION = ?, ID_TELEFONO_COMUNICACION = ?, ID_CORREO_ELECTRONICO_COMUNICACION = ?, ID_ENTIDAD_EXTERNA = ?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP, PORCENTAJE_PARTICIPACION = ?, ID_DEPENDENCIA = ?, VISITADOR_PRINCIPAL = ?, REASIGNADO = ?, ID_USUARIO_REASIGNADO = ?  WHERE ID_SOLICITUD=? AND ID_PERSONA=?";

	/** Constante cs_UPDATE_ROL. */
	private static final String cs_UPDATE_ROL = "UPDATE SDB_ACC_SOLICITUD_INTERVINIENTE SET ROL_PERSONA=?, ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_SOLICITUD=? AND ID_PERSONA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_INTERVINIENTE (ID_SOLICITUD, "
		+ " ID_PERSONA, ROL_PERSONA, ID_AUTORIZACION_COMUNICACION, ID_AUTORIZACION_NOTIFICACION, "
		+ " ID_DIRECCION, ID_TELEFONO, ID_CORREO_ELECTRONICO, ID_DIRECCION_COMUNICACION, ID_TELEFONO_COMUNICACION, ID_CORREO_ELECTRONICO_COMUNICACION, ID_ENTIDAD_EXTERNA, ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION,PORCENTAJE_PARTICIPACION,ID_DEPENDENCIA,VISITADOR_PRINCIPAL, REASIGNADO, ID_USUARIO_REASIGNADO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?)";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_SOLICITUD_INTERVINIENTE "
		+ " WHERE ID_SOLICITUD = ? AND ID_PERSONA = ?";

	/** Constante cs_FIND_PERSONA_BY_ID_SOLICITUD. */
	private String cs_FIND_PERSONA_BY_ID_SOLICITUD = "SELECT DISTINCT(ID_PERSONA) FROM SDB_ACC_SOLICITUD_INTERVINIENTE WHERE ID_SOLICITUD = ?";

	/**
	 * Elimina el registro.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudInterviniente
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void delete(SolicitudInterviniente at_param)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, at_param.getIdSolicitud());
				lps_ps.setString(li_column++, at_param.getIdPersona());

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
	 * Find by id.
	 *
	 * @param asi_param de asi param
	 * @return el valor de solicitud interviniente
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudInterviniente findById(SolicitudInterviniente asi_param)
	    throws B2BException
	{
		return (asi_param != null) ? findById(asi_param.getIdSolicitud(), asi_param.getIdPersona()) : null;
	}

	/**
	 * Find by id.
	 *
	 * @param as_solicitud de as solicitud
	 * @param as_persona de as persona
	 * @return el valor de solicitud interviniente
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudInterviniente findById(String as_solicitud, String as_persona)
	    throws B2BException
	{
		SolicitudInterviniente ls_object;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if((as_solicitud != null) && (as_persona != null))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_solicitud);
				lps_ps.setString(2, as_persona);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs, true);
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

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudInterviniente filtrado por ID solicitud.
	 *
	 * @param asi_param correspondiente al valor del tipo de objeto SolicitudInterviniente
	 * @return devuelve el valor de Collection de SolicitudInterviniente
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudInterviniente
	 */
	public Collection<SolicitudInterviniente> findByIdSolicitud(SolicitudInterviniente asi_param)
	    throws B2BException
	{
		Collection<SolicitudInterviniente> lcssi_return;

		lcssi_return = null;

		if(asi_param != null)
			lcssi_return = findByIdSolicitud(asi_param.getIdSolicitud());

		return lcssi_return;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudInterviniente filtrado por ID solicitud.
	 *
	 * @param as_param correspondiente al valor String filtro de búsqueda
	 * @return devuelve el valor de Collection de SolicitudInterviniente
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudInterviniente
	 */
	public Collection<SolicitudInterviniente> findByIdSolicitud(String as_param)
	    throws B2BException
	{
		Collection<SolicitudInterviniente> ls_object;
		ls_object = new ArrayList<SolicitudInterviniente>();

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(getObjetFromSolicitudInterviniente(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Método para buscar el ultimo registro agregado en la tabla filtrado por la solicitud.
	 *
	 * @param asi_param SolicitudInterviniente con la información para filtrar en la BD
	 * @return devuelve el valor de SolicitudInterviniente
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudInterviniente
	 */
	public SolicitudInterviniente findByIdSolicitudUltimo(SolicitudInterviniente asi_param)
	    throws B2BException
	{
		SolicitudInterviniente lsi_object;
		lsi_object = null;

		if(asi_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ULTIMO);

				lps_ps.setString(1, asi_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsi_object = getObjetFromSolicitudInterviniente(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudUltimo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lsi_object;
	}

	/**
	 * Find by id solicitud ultimo modificado.
	 *
	 * @param asi_param correspondiente al valor de asi param
	 * @return el valor de solicitud interviniente
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudInterviniente findByIdSolicitudUltimoModificado(SolicitudInterviniente asi_param)
	    throws B2BException
	{
		SolicitudInterviniente lsi_object;
		lsi_object = null;

		if(asi_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ULTIMO_MODIFICADO);

				lps_ps.setString(1, asi_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsi_object = getObjetFromSolicitudInterviniente(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudUltimoModificado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lsi_object;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudInterviniente filtrado por solicitud.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudInterviniente
	 * @return devuelve el valor de Collection de SolicitudInterviniente
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudInterviniente
	 */
	public Collection<SolicitudInterviniente> findBySolicitud(SolicitudInterviniente at_param)
	    throws B2BException
	{
		Collection<SolicitudInterviniente> ls_object;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;
		int                                li_column;

		li_column     = 1;

		ls_object     = new ArrayList<SolicitudInterviniente>();
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD);

				lps_ps.setString(li_column++, at_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object.add(getObjetFromResultSet(lrs_rs, false));
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

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudInterviniente filtrado por solicitud persona.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudInterviniente
	 * @return devuelve el valor de Collection de SolicitudInterviniente
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudInterviniente
	 */
	public Collection<SolicitudInterviniente> findBySolicitudPersona(SolicitudInterviniente at_param)
	    throws B2BException
	{
		Collection<SolicitudInterviniente> ls_object;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		ls_object     = new ArrayList<SolicitudInterviniente>();
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD_PERSONA);

				lps_ps.setString(1, at_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(getSolicitudPersona(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitudPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Consulta en la base de datos los registros filtrando por id solicitud,
	 * id persona y rol persona.
	 *
	 * @param at_param Objeto contenedor de los datos que se van a utilizar
	 * como filtro
	 * @return SolicitudInterviniente con los datos resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudInterviniente
	 */
	public SolicitudInterviniente findBySolicitudPersonaRol(SolicitudInterviniente at_param)
	    throws B2BException
	{
		SolicitudInterviniente ls_object;

		ls_object = null;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;
				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_SOLICITUD_PERSONA_ROL);

				lps_ps.setString(li_cont++, at_param.getIdSolicitud());
				lps_ps.setString(li_cont++, at_param.getIdPersona());
				lps_ps.setString(li_cont++, at_param.getRolPersona());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitudPersonaRol", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_object;
	}

	/**
	 * Método para consultar las personas por idSolicitud
	 *
	 * @param as_idSolicitud String contenedor del filtro de búsqueda
	 * @return Colección de Persona con resultados de la consulta
	 * @throws B2BException
	 */
	public Collection<Persona> findPersonasByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<Persona> lcapc_datos;
		lcapc_datos = new ArrayList<Persona>();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_PERSONA_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					Persona lp_p;

					lp_p = new Persona();

					lp_p.setIdPersona(lrs_rs.getString("ID_PERSONA"));

					lcapc_datos.add(lp_p);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findPersonasByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapc_datos))
			lcapc_datos = null;

		return lcapc_datos;
	}

	/**
	 * Insertar o Actualizar.
	 *
	 * @param at_param de at param
	 * @param ab_query de ab query
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertOrUpdate(SolicitudInterviniente at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
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
					lps_ps.setString(li_column++, at_param.getIdSolicitud());
					lps_ps.setString(li_column++, at_param.getIdPersona());
				}

				String ls_rolPersona;

				ls_rolPersona = at_param.getRolPersona();

				if(StringUtils.isValidString(ls_rolPersona))
				{
					if(ls_rolPersona.equalsIgnoreCase(RolCommon.DE))
						ls_rolPersona = EstadoCommon.De;
					else if(ls_rolPersona.equalsIgnoreCase(RolCommon.A))
						ls_rolPersona = RolCommon.A;
					else if(ls_rolPersona.equalsIgnoreCase(RolCommon.TESTADOR))
						ls_rolPersona = RolCommon.TESTADOR;
				}

				lps_ps.setString(li_column++, ls_rolPersona);
				lps_ps.setString(li_column++, at_param.getIdAutorizacionComunicacion());
				lps_ps.setString(li_column++, at_param.getIdAutorizacionNotificacion());
				lps_ps.setString(li_column++, at_param.getIdDireccion());
				lps_ps.setString(li_column++, at_param.getIdTelefono());
				lps_ps.setString(li_column++, at_param.getIdCorreoElectronico());
				lps_ps.setString(li_column++, at_param.getIdDireccionComunicacion());
				lps_ps.setString(li_column++, at_param.getIdTelefonoComunicacion());
				lps_ps.setString(li_column++, at_param.getIdCorreoElectronicoComunicacion());
				lps_ps.setString(li_column++, at_param.getIdEntidadExterna());

				lps_ps.setString(
				    li_column++, ab_query ? at_param.getIdUsuarioCreacion() : at_param.getIdUsuarioModificacion()
				);
				lps_ps.setString(li_column++, ab_query ? at_param.getIpCreacion() : at_param.getIpModificacion());

				if(StringUtils.isValidString(at_param.getPorcentaje()))
				{
					String ls_temp;
					ls_temp = at_param.getPorcentaje().replaceAll(",", IdentificadoresCommon.PUNTO);
					lps_ps.setDouble(li_column++, NumericUtils.getDouble(ls_temp));
				}
				else
					lps_ps.setDouble(li_column++, 0.0);

				lps_ps.setString(li_column++, at_param.getIdDependencia());
				lps_ps.setString(li_column++, at_param.getVisitadorPrincipal());
				lps_ps.setString(li_column++, at_param.getReasignado());
				lps_ps.setString(li_column++, at_param.getIdUsuarioReasignado());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdSolicitud());
					lps_ps.setString(li_column++, at_param.getIdPersona());
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
	 * Retorna el valor del objeto de int con el codigo del interviniente.
	 *
	 * @param as_codigo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int solicitudConMasDeUnInterviniente(String as_codigo)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_resultado;

		lps_ps           = null;
		li_resultado     = -1;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_CODIGO_CON_MAS_DE_UN_INTERVINIENTE);

			lps_ps.setString(1, as_codigo);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_resultado = lrs_rs.getInt("RESULTADO");
		}
		catch(SQLException lse_e)
		{
			logError(this, "findBySolicitudPersona", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_resultado;
	}

	/**
	 * Ejecuta una actualización del campo rol de un registro especificado previamente
	 * mediante el id solicitud y el id persona.
	 *
	 * @param at_param Objeto contenedor de los datos a utilizar como filtro y de el
	 * nuevo valor para el campo rol
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateRol(SolicitudInterviniente at_param)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ROL);

				lps_ps.setString(li_column++, at_param.getRolPersona());
				lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, at_param.getIdSolicitud());
				lps_ps.setString(li_column++, at_param.getIdPersona());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateRol", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de SolicitudInterviniente
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return el valor de SolicitudInterviniente
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudInterviniente getObjetFromResultSet(ResultSet lrs_rs, boolean ab_b)
	    throws SQLException
	{
		SolicitudInterviniente ls_solicitud;

		ls_solicitud = new SolicitudInterviniente();

		if(ab_b)
		{
			ls_solicitud.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
			ls_solicitud.setIdPersona(lrs_rs.getString("ID_PERSONA"));
			ls_solicitud.setRolPersona(lrs_rs.getString("ROL_PERSONA"));
			ls_solicitud.setIdAutorizacionComunicacion(lrs_rs.getString("ID_AUTORIZACION_COMUNICACION"));
			ls_solicitud.setIdAutorizacionNotificacion(lrs_rs.getString("ID_AUTORIZACION_NOTIFICACION"));
			ls_solicitud.setIdDireccion(lrs_rs.getString("ID_DIRECCION"));
			ls_solicitud.setIdTelefono(lrs_rs.getString("ID_TELEFONO"));
			ls_solicitud.setIdCorreoElectronico(lrs_rs.getString("ID_CORREO_ELECTRONICO"));
			ls_solicitud.setIdDireccionComunicacion(lrs_rs.getString("ID_DIRECCION_COMUNICACION"));
			ls_solicitud.setIdTelefonoComunicacion(lrs_rs.getString("ID_TELEFONO_COMUNICACION"));
			ls_solicitud.setIdEntidadExterna(lrs_rs.getString("ID_ENTIDAD_EXTERNA"));
			ls_solicitud.setIdCorreoElectronicoComunicacion(lrs_rs.getString("ID_CORREO_ELECTRONICO_COMUNICACION"));
			ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
			ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		}
		else
		{
			ls_solicitud.setIdDocumentoTipo(lrs_rs.getString("ID_DOCUMENTO_TIPO"));
			ls_solicitud.setNumeroDocumento(lrs_rs.getString("NUMERO_DOCUMENTO"));
			ls_solicitud.setNombreInterviniente(lrs_rs.getString("NOMBRE"));
		}

		return ls_solicitud;
	}

	/**
	 * Retorna el valor de SolicitudInterviniente
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de objet from solicitud interviniente
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudInterviniente getObjetFromSolicitudInterviniente(ResultSet lrs_rs)
	    throws SQLException
	{
		SolicitudInterviniente ls_solicitud;

		ls_solicitud = new SolicitudInterviniente();

		ls_solicitud.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
		ls_solicitud.setIdPersona(lrs_rs.getString("ID_PERSONA"));
		ls_solicitud.setRolPersona(lrs_rs.getString("ROL_PERSONA"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIdAutorizacionComunicacion(lrs_rs.getString("ID_AUTORIZACION_COMUNICACION"));
		ls_solicitud.setIdAutorizacionNotificacion(lrs_rs.getString("ID_AUTORIZACION_NOTIFICACION"));
		ls_solicitud.setIdDireccion(lrs_rs.getString("ID_DIRECCION"));
		ls_solicitud.setIdTelefono(lrs_rs.getString("ID_TELEFONO"));
		ls_solicitud.setIdCorreoElectronico(lrs_rs.getString("ID_CORREO_ELECTRONICO"));
		ls_solicitud.setIdDependencia(lrs_rs.getString("ID_DEPENDENCIA"));
		ls_solicitud.setVisitadorPrincipal(lrs_rs.getString("VISITADOR_PRINCIPAL"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));
		ls_solicitud.setReasignado(lrs_rs.getString("REASIGNADO"));
		ls_solicitud.setIdUsuarioReasignado(lrs_rs.getString("ID_USUARIO_REASIGNADO"));

		return ls_solicitud;
	}

	/**
	 * Retorna el valor de solicitud persona.
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de solicitud persona
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudInterviniente getSolicitudPersona(ResultSet lrs_rs)
	    throws SQLException
	{
		SolicitudInterviniente ls_solicitudInterviniente;

		ls_solicitudInterviniente = new SolicitudInterviniente();

		ls_solicitudInterviniente.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
		ls_solicitudInterviniente.setIdPersona(lrs_rs.getString("ID_PERSONA"));
		ls_solicitudInterviniente.setRolPersona(lrs_rs.getString("ROL_PERSONA"));
		ls_solicitudInterviniente.setIdAutorizacionNotificacion(lrs_rs.getString("NOMBRE"));

		{
			Persona lp_persona;
			lp_persona = new Persona();

			lp_persona.setIdDocumentoTipo(lrs_rs.getString("ID_DOCUMENTO_TIPO"));
			lp_persona.setNumeroDocumento(lrs_rs.getString("NUMERO_DOCUMENTO"));
			lp_persona.setPrimerNombre(lrs_rs.getString("PRIMER_NOMBRE"));
			lp_persona.setSegundoNombre(lrs_rs.getString("SEGUNDO_NOMBRE"));
			lp_persona.setPrimerApellido(lrs_rs.getString("PRIMER_APELLIDO"));
			lp_persona.setSegundoApellido(lrs_rs.getString("SEGUNDO_APELLIDO"));
			lp_persona.setRazonSocial(lrs_rs.getString("RAZON_SOCIAL"));

			lp_persona.setNombreCompleto(
			    StringUtils.getStringNotNull(lp_persona.getPrimerNombre()) + " "
			    + StringUtils.getStringNotNull(lp_persona.getSegundoNombre()) + " "
			    + StringUtils.getStringNotNull(lp_persona.getPrimerApellido()) + " "
			    + StringUtils.getStringNotNull(lp_persona.getSegundoApellido())
			);

			ls_solicitudInterviniente.setPersona(lp_persona);
		}

		return ls_solicitudInterviniente;
	}
}
