package com.bachue.snr.prosnr01.dao.consulta.solicitud;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.consulta.solicitud.Persona;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Predio;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Segregacion;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Tramite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas relacionadas a la tabla SDB_ACC_PERSONA_DIRECCION
 *
 * @author ccalderon
 */
public class ConsultaSolicitudDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = "SELECT P.ID_PERSONA, P.NUMERO_DOCUMENTO, P.ID_TIPO_PERSONA, P.PRIMER_NOMBRE, P.SEGUNDO_NOMBRE, P.PRIMER_APELLIDO, P.SEGUNDO_APELLIDO, P.RAZON_SOCIAL, P.ID_PAIS,P.ID_NATURAL_GENERO, P.ID_USUARIO_CREACION, S.ID_CORREO_ELECTRONICO, S.ID_DIRECCION, S.ID_CORREO_ELECTRONICO_COMUNICACION, S.ID_DIRECCION_COMUNICACION, S.ID_AUTORIZACION_COMUNICACION, S.ID_AUTORIZACION_NOTIFICACION, S.FECHA_CREACION, SCIDT.DESCRIPCION FROM SDB_ACC_PERSONA P JOIN SDB_ACC_SOLICITUD S ON P.ID_PERSONA = S.ID_PERSONA JOIN SDB_COL_INTERESADO_DOCUMENTO_TIPO SCIDT ON P.ID_DOCUMENTO_TIPO = SCIDT.ID_DOCUMENTO_TIPO WHERE S.ID_SOLICITUD=?";

	/** Constante cs_FIND_BY_SOLICITUD_INT. */
	private static final String cs_FIND_BY_SOLICITUD_INT = "SELECT P.ID_PERSONA, P.NUMERO_DOCUMENTO, P.ID_TIPO_PERSONA, P.PRIMER_NOMBRE, P.SEGUNDO_NOMBRE, P.PRIMER_APELLIDO, P.SEGUNDO_APELLIDO, P.RAZON_SOCIAL, P.ID_PAIS,P.ID_NATURAL_GENERO, P.ID_USUARIO_CREACION, S.ID_CORREO_ELECTRONICO, S.ID_DIRECCION, S.ID_CORREO_ELECTRONICO_COMUNICACION, S.ID_DIRECCION_COMUNICACION, S.ID_AUTORIZACION_COMUNICACION, S.ID_AUTORIZACION_NOTIFICACION, S.FECHA_CREACION, SCIDT.DESCRIPCION FROM SDB_ACC_PERSONA P JOIN SDB_ACC_SOLICITUD_INTERVINIENTE S ON P.ID_PERSONA = S.ID_PERSONA JOIN SDB_COL_INTERESADO_DOCUMENTO_TIPO SCIDT ON P.ID_DOCUMENTO_TIPO = SCIDT.ID_DOCUMENTO_TIPO WHERE S.ID_SOLICITUD=?";

	/** Constante cs_FIND_BY_CORREO. */
	private static final String cs_FIND_BY_CORREO = "SELECT CORREO_ELECTRONICO FROM SDB_ACC_PERSONA_CORREO_ELECTRONICO WHERE ID_PERSONA=? AND ID_CORREO_ELECTRONICO=?";

	/** Constante cs_FIND_BY_DIRECCION. */
	private static final String cs_FIND_BY_DIRECCION = "SELECT PD.DIRECCION_COMPLETA DIRECCION_COMPLETA "
		+ " FROM SDB_ACC_PERSONA_DIRECCION PD WHERE PD.ID_PERSONA = ? AND PD.ID_DIRECCION = ? ";

	/** Constante cs_FIND_TELEFONO_BY_IDPERSONA. */
	private static final String cs_FIND_TELEFONO_BY_IDPERSONA = "SELECT '+'||ID_PAIS || ' ' || TELEFONO TELEFONO FROM SDB_ACC_PERSONA_TELEFONO WHERE TIPO_TELEFONO='M' AND ID_PERSONA=?";

	/** Constante cs_FIND_BY_TIPO. */
	private static final String cs_FIND_BY_TIPO = "SELECT NOMBRE FROM SDB_ACC_TIPO_RECEPCION WHERE ID_TIPO_RECEPCION=?";

	/** Constante cs_FIND_TRAMITE_BY_ID. */
	private static final String cs_FIND_TRAMITE_BY_ID = "SELECT S3.NOMBRE, S.RADICADO_PQRS, ltrim(TO_CHAR(S.FECHA_RADICADO_PQRS,'DD/MM/YYYY'),'0')  FECHA_RADICADO_PQRS,S.DERECHO_PETICION , S4.NOMBRE NOMBRES, S.ID_TURNO_ANT, S.NIR FROM SDB_ACC_SOLICITUD S JOIN SDB_ACC_PERSONA S2 on S.ID_PERSONA = S2.ID_PERSONA"
		+ " JOIN SDB_ACC_TIPO_RECEPCION S3 on S.ID_TIPO_RECEPCION= S3.ID_TIPO_RECEPCION JOIN  SDB_ACC_SUBPROCESO S4 ON S.ID_PROCESO=S4.ID_PROCESO AND S.ID_SUBPROCESO=S4.ID_SUBPROCESO WHERE S.ID_SOLICITUD=?";

	/** Constante cs_FIND_TRAMITE_BY_ID_SIN_PROCEDENCIA. */
	private static final String cs_FIND_TRAMITE_BY_ID_SIN_PROCEDENCIA = "SELECT S.RADICADO_PQRS, ltrim(TO_CHAR(S.FECHA_RADICADO_PQRS,'DD/MM/YYYY'),'0') FECHA_RADICADO_PQRS,S.DERECHO_PETICION , S4.NOMBRE NOMBRES, S.ID_TURNO_ANT, S.NIR FROM SDB_ACC_SOLICITUD S JOIN SDB_ACC_PERSONA S2 on S.ID_PERSONA = S2.ID_PERSONA"
		+ " JOIN  SDB_ACC_SUBPROCESO S4 ON S.ID_PROCESO=S4.ID_PROCESO AND S.ID_SUBPROCESO=S4.ID_SUBPROCESO WHERE S.ID_SOLICITUD=?";

	/** Constante cs_FIND_BY_ID_ACTO. */
	private static final String cs_FIND_BY_ID_ACTO = "   SELECT  SAA.ID_ACTO ,SAA.ID_TIPO_ACTO,SAA.ID_SOLICITUD,SAA.CUANTIA,SAA.VALOR_AVALUO, "
		+ " SAA.CANTIDAD_ACTOS,SAA.FECHA_VENCIMIENTO,SAA.ID_TIPO_ADQUISICION ,STA.ACTO_SIN_CUANTIA, STA.NOMBRE "
		+ " FROM  SDB_ACC_ACTO SAA JOIN SDB_PGN_TIPO_ACTO STA ON SAA.ID_TIPO_ACTO=STA.ID_TIPO_ACTO WHERE SAA.ID_SOLICITUD = ? ";

	/** Constante cs_FIND_MATRICULA_BY_SOLICITUD. */
	private static final String cs_FIND_MATRICULA_BY_SOLICITUD = "SELECT S1.ID_CIRCULO || '-' || S1.ID_MATRICULA AS MATRICULA, "
		+ "NOMBRE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO S1 JOIN SDB_ACC_ACTO S ON S1.ID_ACTO = S.ID_ACTO JOIN SDB_PGN_TIPO_ACTO t ON t.ID_TIPO_ACTO=S.ID_TIPO_ACTO WHERE S1.ID_SOLICITUD=? ORDER BY MATRICULA";

	/** Constante cs_FIND_AGREGACION_BY_SOLICITUD. */
	private static final String cs_FIND_AGREGACION_BY_SOLICITUD = "SELECT NVL(NOMBRE_PREDIO,'') NOMBRE,NVL(COMPLEMENTO_DIRECCION,'') DIRECCION,AREA_TERRENO,AREA_CONSTRUIDA,AREA_PRIVADA,COEFICIENTE FROM SDB_ACC_MATRICULA_SEGREGACION WHERE ID_SOLICITUD=?";

	/**
	 * Instancia un nuevo objeto consulta solicitud DAO.
	 */
	public ConsultaSolicitudDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Acto
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection de Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_object;
		PreparedStatement                                       lps_ps;
		ResultSet                                               lrs_rs;

		lca_object     = new ArrayList<com.bachue.snr.prosnr01.model.registro.Acto>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACTO);

			lps_ps.setString(1, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lca_object.add(getObjetFromResultSetActo(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lca_object))
			lca_object = null;

		return lca_object;
	}

	/**
	 * Retorna el valor del nombre consultado por id
	 *
	 * @param as_idTipo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string del nombre
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findByTipo(String as_idTipo)
	    throws B2BException
	{
		String            ls_nombre;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_nombre     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_TIPO);

			lps_ps.setString(1, as_idTipo);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_nombre = getNombre(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByTipo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_nombre;
	}

	/**
	 * Retorna el valor del correo electronico por el id persona y id correo
	 *
	 * @param as_idPersona correspondiente al valor del tipo de objeto String
	 * @param as_idCorreo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findCorroByPersonaAndIdCorreo(String as_idPersona, String as_idCorreo)
	    throws B2BException
	{
		String            ls_correo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_correo     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CORREO);

			lps_ps.setString(1, as_idPersona);

			lps_ps.setString(2, as_idCorreo);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_correo = getCorreo(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findCorroByPersonaAndIdDireccion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_correo;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Tramite consultado por ID del tramite
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Tramite> findDatosDelTramiteByidSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<Tramite> lct_tramites;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lct_tramites     = new ArrayList<Tramite>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_TRAMITE_BY_ID);

			lps_ps.setString(1, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lct_tramites.add(getTramite(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDatosDelTramiteByidSolicitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lct_tramites))
			lct_tramites = null;

		return lct_tramites;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Tramite consultado sin procedencia
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Tramite> findDatosDelTramiteByidSolicitudSinProcedencia(String as_idSolicitud)
	    throws B2BException
	{
		Collection<Tramite> lct_tramites;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lct_tramites     = new ArrayList<Tramite>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_TRAMITE_BY_ID_SIN_PROCEDENCIA);

			lps_ps.setString(1, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lct_tramites.add(getTramiteSinProcedencia(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDatosDelTramiteByidSolicitudSinProcedencia", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lct_tramites))
			lct_tramites = null;

		return lct_tramites;
	}

	/**
	 * Retorna el valor del objeto de tipo direccion consultado por direccion
	 *
	 * @param as_idPersona correspondiente al valor del tipo de objeto String
	 * @param as_idDireccion correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findDireccionByPersonaAndIdDireccion(String as_idPersona, String as_idDireccion)
	    throws B2BException
	{
		String            ls_direccion;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_direccion     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_DIRECCION);

			lps_ps.setString(1, as_idPersona);

			lps_ps.setString(2, as_idDireccion);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_direccion = getDireccion(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDireccionbyPersonaAndIdDireccion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_direccion;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Predio consultado por el id matricula solicitud
	 *
	 * @param as_solicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Predio
	 */
	public Collection<Predio> findMatriculaBySolicitud(String as_solicitud)
	    throws B2BException
	{
		Collection<Predio> lcp_predios;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcp_predios     = new ArrayList<Predio>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_MATRICULA_BY_SOLICITUD);

			lps_ps.setString(1, as_solicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcp_predios.add(getPredio(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMatriculaBySolicitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcp_predios))
			lcp_predios = null;

		return lcp_predios;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Persona consultado por solicitud
	 *
	 * @param as_solicitud correspondiente al valor del tipo de objeto String
	 * @param as_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection  de Persona
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Collection<Persona> findPersonaBySolicitud(String as_solicitud, boolean as_b)
	    throws B2BException
	{
		Collection<Persona> lcp_persona;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcp_persona     = new ArrayList<Persona>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(as_b)
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD);
			else
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD_INT);

			lps_ps.setString(1, as_solicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcp_persona.add(getPersona(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcp_persona))
			lcp_persona = null;

		return lcp_persona;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Segregacion consultado por id solicitud
	 *
	 * @param as_idTipo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection de Segregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Segregacion
	 */
	public Collection<Segregacion> findSegregacion(String as_idTipo)
	    throws B2BException
	{
		Collection<Segregacion> lcs_segregacion;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcs_segregacion     = new ArrayList<Segregacion>();
		lps_ps              = null;
		lrs_rs              = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_AGREGACION_BY_SOLICITUD);

			lps_ps.setString(1, as_idTipo);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcs_segregacion.add(getSegregacion(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSegregacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcs_segregacion;
	}

	/**
	 * Retorna el valor del objeto de tipo String correspondiente al telefono de la persona consultado por el ID persona
	 *
	 * @param as_idPersona correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findTelefonoByidPersona(String as_idPersona)
	    throws B2BException
	{
		String            ls_telefono;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_telefono     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_TELEFONO_BY_IDPERSONA);

			lps_ps.setString(1, as_idPersona);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_telefono = getTelefono(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByTipo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_telefono;
	}

	/**
	 * Retorna el valor de correo electronico.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de correo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private String getCorreo(ResultSet ars_rs)
	    throws SQLException
	{
		String ls_direccion;

		ls_direccion = ars_rs.getString("CORREO_ELECTRONICO");

		return ls_direccion;
	}

	/**
	 * Retorna el valor de direccion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de direccion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private String getDireccion(ResultSet ars_rs)
	    throws SQLException
	{
		String ls_direccion;

		ls_direccion = ars_rs.getString("DIRECCION_COMPLETA");

		return ls_direccion;
	}

	/**
	 * Retorna el valor de nombre.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de nombre
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private String getNombre(ResultSet ars_rs)
	    throws SQLException
	{
		String ls_direccion;

		ls_direccion = ars_rs.getString("NOMBRE");

		return ls_direccion;
	}

	/**
	 * Retorna el valor de Acto
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de objet from result set acto
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Acto
	 */
	private com.bachue.snr.prosnr01.model.registro.Acto getObjetFromResultSetActo(ResultSet ars_rs)
	    throws SQLException
	{
		com.bachue.snr.prosnr01.model.registro.Acto ls_tipoActo;

		ls_tipoActo = new com.bachue.snr.prosnr01.model.registro.Acto();

		ls_tipoActo.setIdActoDb(ars_rs.getString("ID_ACTO"));
		ls_tipoActo.setCodigo(ars_rs.getString("ID_TIPO_ACTO"));
		ls_tipoActo.setEspecificacion(ars_rs.getString("NOMBRE"));
		ls_tipoActo.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		ls_tipoActo.setCuantiaActo(ars_rs.getBigDecimal("CUANTIA"));
		ls_tipoActo.setValorAvaluo(ars_rs.getBigDecimal("VALOR_AVALUO"));
		ls_tipoActo.setCantidadActos(getInteger(ars_rs, "CANTIDAD_ACTOS"));
		ls_tipoActo.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		ls_tipoActo.setTipoAdquisicion(ars_rs.getString("ID_TIPO_ADQUISICION"));
		ls_tipoActo.setActoConCuantia(ars_rs.getString("ACTO_SIN_CUANTIA"));

		//ls_tipoActo.setEjecutora(ars_rs.getString("CAMPO_EJECUTORIA"));
		//ls_tipoActo.setTiempo(getLong(ars_rs, "TIEMPO_VENCIMIENTO"));
		return ls_tipoActo;
	}

	/**
	 * Retorna el valor de persona.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de persona
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Persona
	 */
	private Persona getPersona(ResultSet ars_rs)
	    throws SQLException
	{
		Persona lp_persona;

		lp_persona = new Persona();

		lp_persona.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lp_persona.setIdDocumentoTipo(ars_rs.getString("DESCRIPCION"));
		lp_persona.setNumeroDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		lp_persona.setIdTipoPersona(ars_rs.getString("ID_TIPO_PERSONA"));
		lp_persona.setPrimerNombre(ars_rs.getString("PRIMER_NOMBRE"));
		lp_persona.setSegundoNombre(ars_rs.getString("SEGUNDO_NOMBRE"));
		lp_persona.setPrimerApellido(ars_rs.getString("PRIMER_APELLIDO"));
		lp_persona.setSegundoApellido(ars_rs.getString("SEGUNDO_APELLIDO"));
		lp_persona.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
		lp_persona.setIdPais(ars_rs.getString("ID_PAIS"));

		if(
		    (ars_rs.getString("ID_NATURAL_GENERO") != null)
			    && ars_rs.getString("ID_NATURAL_GENERO").equalsIgnoreCase("M")
		)
			lp_persona.setIdNaturalGenero("MASCULINO");
		else if(
		    (ars_rs.getString("ID_NATURAL_GENERO") != null)
			    && ars_rs.getString("ID_NATURAL_GENERO").equalsIgnoreCase("F")
		)
			lp_persona.setIdNaturalGenero("FEMENINO");

		lp_persona.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lp_persona.setIdCorreo(ars_rs.getString("ID_CORREO_ELECTRONICO"));
		lp_persona.setIdDireccion(ars_rs.getString("ID_DIRECCION"));
		lp_persona.setIdCorreoComunicacion(ars_rs.getString("ID_CORREO_ELECTRONICO_COMUNICACION"));
		lp_persona.setIdDireccionComunicacion(ars_rs.getString("ID_DIRECCION_COMUNICACION"));
		lp_persona.setTipoMedioComunicacion(ars_rs.getString("ID_AUTORIZACION_COMUNICACION"));
		lp_persona.setTipoMedioNotificacion(ars_rs.getString("ID_AUTORIZACION_NOTIFICACION"));
		lp_persona.setFechaCreacion(DateUtils.getDate(ars_rs.getTimestamp("FECHA_CREACION")));

		return lp_persona;
	}

	/**
	 * Retorna el valor de predio.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de predio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Predio
	 */
	private Predio getPredio(ResultSet ars_rs)
	    throws SQLException
	{
		Predio lp_predio;

		lp_predio = new Predio();
		lp_predio.setMatricula(ars_rs.getString("MATRICULA"));
		lp_predio.setActo(ars_rs.getString("NOMBRE").toUpperCase());

		return lp_predio;
	}

	/**
	 * Retorna el valor de segregacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de segregacion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Segregacion
	 */
	private Segregacion getSegregacion(ResultSet ars_rs)
	    throws SQLException
	{
		Segregacion ls_segregacion;
		String      ls_m2;
		ls_m2              = "m²";
		ls_segregacion     = new Segregacion();

		ls_segregacion.setNombrePredio(ars_rs.getString("NOMBRE"));
		ls_segregacion.setDireccionPredio(ars_rs.getString("DIRECCION"));
		ls_segregacion.setAreaTerreno(StringUtils.getString(ars_rs.getDouble("AREA_tERRENO")) + " " + ls_m2);
		ls_segregacion.setAreaConstruccion(StringUtils.getString(ars_rs.getDouble("AREA_CONSTRUIDA")) + " " + ls_m2);
		ls_segregacion.setAreaPrivada(StringUtils.getString(ars_rs.getDouble("AREA_PRIVADA")) + " " + ls_m2);
		ls_segregacion.setCoeficiente(StringUtils.getString(ars_rs.getDouble("COEFICIENTE")) + " %");

		return ls_segregacion;
	}

	/**
	 * Retorna el valor de telefono.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de telefono
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private String getTelefono(ResultSet ars_rs)
	    throws SQLException
	{
		String ls_telefono;

		ls_telefono = ars_rs.getString("TELEFONO");

		return ls_telefono;
	}

	/**
	 * Retorna el valor de tramite.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de tramite
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Tramite
	 */
	private Tramite getTramite(ResultSet ars_rs)
	    throws SQLException
	{
		Tramite lt_tramite;

		lt_tramite = new Tramite();
		lt_tramite.setNombreProcedencia(ars_rs.getString("NOMBRE"));
		lt_tramite.setIs_radicado(ars_rs.getString("RADICADO_PQRS"));
		lt_tramite.setFechaRadicado(ars_rs.getString("FECHA_RADICADO_PQRS"));
		lt_tramite.setDerechoPetcicion(ars_rs.getString("DERECHO_PETICION"));
		lt_tramite.setNombreTramite(ars_rs.getString("NOMBRES"));
		lt_tramite.setTurnoAnterior("ID_TURNO_ANT");
		lt_tramite.setNir(ars_rs.getString("NIR"));

		return lt_tramite;
	}

	/**
	 * Retorna el valor de tramite sin procedencia.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de tramite sin procedencia
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Tramite
	 */
	private Tramite getTramiteSinProcedencia(ResultSet ars_rs)
	    throws SQLException
	{
		Tramite lt_tramite;

		lt_tramite = new Tramite();
		lt_tramite.setIs_radicado(ars_rs.getString("RADICADO_PQRS"));
		lt_tramite.setFechaRadicado(ars_rs.getString("FECHA_RADICADO_PQRS"));
		lt_tramite.setDerechoPetcicion(ars_rs.getString("DERECHO_PETICION"));
		lt_tramite.setNombreTramite(ars_rs.getString("NOMBRES"));
		lt_tramite.setTurnoAnterior("ID_TURNO_ANT");
		lt_tramite.setNir(ars_rs.getString("NIR"));

		return lt_tramite;
	}
}
