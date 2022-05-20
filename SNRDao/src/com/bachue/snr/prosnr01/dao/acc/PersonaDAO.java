package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * Clase DAO que extrae todos los atributos y propiedades usados en la tabla SDB_ACC_PERSONA.
 *
 * @author Juian Vaca
 *
 */
public class PersonaDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_PERSONA WHERE ID_PERSONA = ?";

	/** Constante cs_FIND_BY_RAZON_SOCIAL. */
	private static final String cs_FIND_BY_RAZON_SOCIAL = "SELECT * FROM SDB_ACC_PERSONA WHERE RAZON_SOCIAL = ?";

	/** Constante cs_FIND_BY_DOC_NAME_MAX_ID. */
	private static final String cs_FIND_BY_DOC_NAME_MAX_ID = "SELECT * FROM SDB_ACC_PERSONA WHERE ID_DOCUMENTO_TIPO = ? AND NUMERO_DOCUMENTO = ? AND PRIMER_NOMBRE = ? ";

	/** Constante cs_FIND_BY_DOCUMENTO_TIPO_DOCUMENTO. */
	private static final String cs_FIND_BY_DOCUMENTO_TIPO_DOCUMENTO = "SELECT * FROM SDB_ACC_PERSONA WHERE NUMERO_DOCUMENTO = ? AND ID_DOCUMENTO_TIPO = ?";

	/** Constante cs_FIND_BY_PERSON_DATA. */
	private static final String cs_FIND_BY_PERSON_DATA = "SELECT * FROM SDB_ACC_PERSONA WHERE";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_PERSONA_ID_PERSONA.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_PERSONA SET ID_DOCUMENTO_TIPO = ?,"
		+ " NUMERO_DOCUMENTO = ?, ID_TIPO_PERSONA = ?, PRIMER_NOMBRE = ?, SEGUNDO_NOMBRE = ?,PRIMER_APELLIDO = ?,SEGUNDO_APELLIDO = ?,"
		+ " RAZON_SOCIAL = ?, ID_PAIS = ?, ID_NATURAL_GENERO = ?, ID_ENTIDAD_EXTERNA = ?, ORIGEN_BACHUE = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PERSONA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PERSONA (ID_PERSONA,ID_DOCUMENTO_TIPO,NUMERO_DOCUMENTO,ID_TIPO_PERSONA,"
		+ " PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,RAZON_SOCIAL,ID_PAIS,ID_NATURAL_GENERO,ID_ENTIDAD_EXTERNA,ORIGEN_BACHUE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) "
		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_BY_DOCUMENT. */
	private static final String cs_FIND_BY_DOCUMENT = "SELECT * FROM SDB_ACC_PERSONA WHERE NUMERO_DOCUMENTO = ?";

	/** Constante cs_FIND_DATA_CALIFICADOR. */
	private static final String cs_FIND_DATA_CALIFICADOR = "SELECT MAX(CAST(ID_PERSONA AS NUMBER)) ID_PERSONA FROM SDB_ACC_PERSONA WHERE NUMERO_DOCUMENTO = ? GROUP BY NUMERO_DOCUMENTO";

	/** Constante cs_FIND_NOMBRES_PERSONAS_TERCEROS. */
	private static final String cs_FIND_NOMBRES_PERSONAS_TERCEROS = "SELECT SAC.PRIMER_NOMBRE || ' ' || NVL (SAC.SEGUNDO_NOMBRE,' ') || ' ' || SAC.PRIMER_APELLIDO || ' ' || "
		+ "NVL(SAC.SEGUNDO_APELLIDO,' ') AS NOMBRE FROM SDB_ACC_PERSONA_ENTREGA APE1 INNER JOIN SDB_ACC_PERSONA_ENTREGA APE2 ON APE2.ID_CALIDAD_PERSONA_ENTREGA = '1' "
		+ "AND APE2.ID_TURNO = ? AND APE1.ID_PERSONA_TERCERO = ? INNER JOIN SDB_ACC_PERSONA SAC ON SAC.ID_PERSONA = APE1.ID_PERSONA WHERE APE1.ID_TURNO = ? "
		+ "AND APE1.ID_CALIDAD_PERSONA_ENTREGA != '1'";

	/** Constante cs_FIND_ALL_TO_COMPARISON_CALIDAD. */
	private static final String cs_FIND_ALL_TO_COMPARISON_CALIDAD = "SELECT SAPE.ID_TURNO, SAPE.ID_PERSONA AS ID_PERSONA_ENTREGA, SAPE.ID_PERSONA_TERCERO, SAC.ID_DOCUMENTO_TIPO, SAC.NUMERO_DOCUMENTO, "
		+ "SAC.PRIMER_NOMBRE, NVL (SAC.SEGUNDO_NOMBRE,' ')SEGUNDO_NOMBRE, "
		+ "SAC.PRIMER_APELLIDO, NVL(SAC.SEGUNDO_APELLIDO,' ')SEGUNDO_APELLIDO, NVL(SAC.RAZON_SOCIAL,' ')RAZON_SOCIAL, "
		+ "SAPE.FECHA_ENTREGA, SACS.NOMBRE AS NOMBRE_CALIDAD FROM SDB_ACC_PERSONA SAC "
		+ "INNER JOIN SDB_ACC_PERSONA_ENTREGA SAPE ON SAPE.ID_PERSONA = SAC.ID_PERSONA "
		+ "INNER JOIN SDB_ACC_CALIDAD_SOLICITANTE SACS ON SACS.ID_CALIDAD_SOLICITANTE = SAPE.ID_CALIDAD_PERSONA_ENTREGA "
		+ "WHERE SACS.ID_CALIDAD_SOLICITANTE in (1) AND SAPE.ID_SOLICITUD = ?";

	/** Constante cs_FIND_ALL_TO_COMPARISON_CALIDAD_ALL. */
	private static final String cs_FIND_ALL_TO_COMPARISON_CALIDAD_ALL = "SELECT SAPE.ID_TURNO, SAPE.ID_PERSONA AS ID_PERSONA_ENTREGA, SAPE.ID_PERSONA_TERCERO, SAC.ID_DOCUMENTO_TIPO, SAC.NUMERO_DOCUMENTO, "
		+ "SAC.PRIMER_NOMBRE, NVL (SAC.SEGUNDO_NOMBRE,' ')SEGUNDO_NOMBRE, "
		+ "SAC.PRIMER_APELLIDO, NVL(SAC.SEGUNDO_APELLIDO,' ')SEGUNDO_APELLIDO, NVL(SAC.RAZON_SOCIAL,' ')RAZON_SOCIAL, "
		+ "SAPE.FECHA_ENTREGA, SACS.NOMBRE AS NOMBRE_CALIDAD FROM SDB_ACC_PERSONA SAC "
		+ "INNER JOIN SDB_ACC_PERSONA_ENTREGA SAPE ON SAPE.ID_PERSONA = SAC.ID_PERSONA "
		+ "INNER JOIN SDB_ACC_CALIDAD_SOLICITANTE SACS ON SACS.ID_CALIDAD_SOLICITANTE = SAPE.ID_CALIDAD_PERSONA_ENTREGA "
		+ "WHERE SACS.ID_CALIDAD_SOLICITANTE in (1, 2, 3, 4, 5) AND SAPE.ID_SOLICITUD = ?";

	/** Constante cs_BUSCAR_PERSONAS_POR_SOLICITUD_INTERVINIENTE. */
	private static final String cs_BUSCAR_PERSONAS_POR_SOLICITUD_INTERVINIENTE = "SELECT SAP.*, SASI.ROL_PERSONA, "
		+ " SASI.ID_AUTORIZACION_COMUNICACION, SASI.ID_AUTORIZACION_NOTIFICACION FROM SDB_ACC_SOLICITUD_INTERVINIENTE SASI "
		+ " INNER JOIN SDB_ACC_SOLICITUD SAS ON SASI.ID_SOLICITUD = SAS.ID_SOLICITUD "
		+ " INNER JOIN SDB_ACC_PERSONA SAP ON SAP.ID_PERSONA = SASI.ID_PERSONA WHERE SAS.ID_SOLICITUD = ?";

	/** Constante cs_FIND_RAZON_SOCIAL_BY_DOCUMENTO. */
	private static final String cs_FIND_RAZON_SOCIAL_BY_DOCUMENTO = "SELECT * FROM SDB_ACC_PERSONA WHERE NUMERO_DOCUMENTO = ? AND ID_TIPO_PERSONA = 'J'";

	/**
	 * Metodo encargado de consultar todas las personas intervinientes de una solicitud.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene los datos de las personas a llenar.
	 * @return Colección que contiene las personas que coinciden con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Collection<Persona> buscarPersonasPorSolicitudInterviniente(String as_idSolicitud)
	    throws B2BException
	{
		Collection<Persona> lcp_persona;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcp_persona     = new ArrayList<Persona>();
		lps_ps          = null;
		lrs_rs          = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_BUSCAR_PERSONAS_POR_SOLICITUD_INTERVINIENTE);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_persona.add(getPersona(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarPersonasPorSolicitudInterviniente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_persona.isEmpty())
			lcp_persona = null;

		return lcp_persona;
	}

	/**
	 * Método encargado de consultar la información de una persona.
	 *
	 * @param as_primerNombre Variable que contiene el primer nombre.
	 * @param as_segundoNombre Variable que contiene el segundo nombre.
	 * @param as_primerApellido Variable que contiene el primer apellido.
	 * @param as_segundoApellido Variable que contiene el segundo apellido.
	 * @param as_tipoDoc Variable que contiene el tipo documento.
	 * @param as_numeroDoc Variable que contiene el número de documento.
	 * @param as_codGenero Variable que contiene el código genero.
	 * @return el valor de persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona findByData(
	    String as_primerNombre, String as_segundoNombre, String as_primerApellido, String as_segundoApellido,
	    String as_tipoDoc, String as_numeroDoc, String as_codGenero
	)
	    throws B2BException
	{
		Persona lp_return;

		lp_return = null;

		if(
		    StringUtils.isValidString(as_primerNombre) && StringUtils.isValidString(as_primerApellido)
			    && StringUtils.isValidString(as_tipoDoc) && StringUtils.isValidString(as_numeroDoc)
			    && StringUtils.isValidString(as_codGenero)
		)
		{
			boolean           lb_segundoNombre;
			boolean           lb_segundoApellido;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lb_segundoNombre       = StringUtils.isValidString(as_segundoNombre);
			lb_segundoApellido     = StringUtils.isValidString(as_segundoApellido);
			lps_ps                 = null;
			lrs_rs                 = null;

			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder(
					    "SELECT * FROM SDB_ACC_PERSONA WHERE ID_DOCUMENTO_TIPO = ? AND NUMERO_DOCUMENTO = ? AND ID_NATURAL_GENERO = ? AND PRIMER_NOMBRE = ? AND PRIMER_APELLIDO = ? "
					);

				if(lb_segundoNombre)
					lsb_sb.append(" AND SEGUNDO_NOMBRE = ? ");

				if(lb_segundoApellido)
					lsb_sb.append(" AND SEGUNDO_APELLIDO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_tipoDoc);
				lps_ps.setString(li_column++, as_numeroDoc);
				lps_ps.setString(li_column++, as_codGenero);
				lps_ps.setString(li_column++, as_primerNombre);
				lps_ps.setString(li_column++, as_primerApellido);

				if(lb_segundoNombre)
					lps_ps.setString(li_column++, as_segundoNombre);

				if(lb_segundoApellido)
					lps_ps.setString(li_column++, as_segundoApellido);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					getPersona(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByData", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lp_return;
	}

	/**
	 * Retorna el valor del objeto de Persona.
	 *
	 * @param ap_param de ap param
	 * @return devuelve el valor de Persona
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Persona findByDocNameMaxId(Persona ap_param)
	    throws B2BException
	{
		Persona lp_persona;

		lp_persona = null;

		if(ap_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				String        ls_segundoNombre;
				String        ls_segundoApellido;
				boolean       lb_tieneSegundoNombre;
				boolean       lb_tieneSegundoApellido;
				int           li_cont;

				lsb_query                   = new StringBuilder(cs_FIND_BY_DOC_NAME_MAX_ID);
				ls_segundoNombre            = ap_param.getSegundoNombre();
				ls_segundoApellido          = ap_param.getSegundoApellido();
				lb_tieneSegundoNombre       = StringUtils.isValidString(ls_segundoNombre);
				lb_tieneSegundoApellido     = StringUtils.isValidString(ls_segundoApellido);
				li_cont                     = 1;

				if(lb_tieneSegundoNombre)
					lsb_query.append(" AND SEGUNDO_NOMBRE = ? ");

				lsb_query.append(" AND PRIMER_APELLIDO = ? ");

				if(lb_tieneSegundoApellido)
					lsb_query.append(" AND SEGUNDO_APELLIDO = ? ");

				lsb_query.append(" ORDER BY TO_NUMBER(ID_PERSONA) DESC ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, ap_param.getIdDocumentoTipo());
				lps_ps.setString(li_cont++, ap_param.getNumeroDocumento());
				lps_ps.setString(li_cont++, ap_param.getPrimerNombre());

				if(lb_tieneSegundoNombre)
					lps_ps.setString(li_cont++, ls_segundoNombre);

				lps_ps.setString(li_cont++, ap_param.getPrimerApellido());

				if(lb_tieneSegundoApellido)
					lps_ps.setString(li_cont++, ls_segundoApellido);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_persona = getPersona(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDocNameMaxId", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lp_persona;
	}

	/**
	 * Retorna el valor del objeto de Collection de Persona filtrado por el id documento.
	 *
	 * @param ap_persona de ap persona
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Collection<Persona> findByDocument(Persona ap_persona)
	    throws B2BException
	{
		return (ap_persona != null) ? findByDocument(ap_persona, false) : null;
	}

	/**
	 * Retorna el valor del objeto de Collection de Persona filtrado por el id documento.
	 *
	 * @param ap_persona de ap persona
	 * @param ab_origenBachue correspondiente al valor de origen bachue
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Collection<Persona> findByDocument(Persona ap_persona, boolean ab_origenBachue)
	    throws B2BException
	{
		Collection<Persona> lcp_persona;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcp_persona     = new ArrayList<Persona>();
		lps_ps          = null;
		lrs_rs          = null;

		if(ap_persona != null)
		{
			try
			{
				StringBuilder lsb_sb;
				String        ls_tipoDocumento;

				ls_tipoDocumento     = ap_persona.getTipoDocIdentidad();
				lsb_sb               = new StringBuilder(cs_FIND_BY_DOCUMENT);

				if(ab_origenBachue)
					lsb_sb.append(" AND ORIGEN_BACHUE = 'S' ");

				if(StringUtils.isValidString(ls_tipoDocumento))
					lsb_sb.append(" AND ID_DOCUMENTO_TIPO = ? ORDER BY PRIMER_NOMBRE");
				else
					lsb_sb.append(" ORDER BY PRIMER_NOMBRE");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(1, ap_persona.getNumeroDocumento());

				if(StringUtils.isValidString(ls_tipoDocumento))
					lps_ps.setString(2, ls_tipoDocumento);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_persona.add(getPersona(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDocument", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_persona.isEmpty())
			lcp_persona = null;

		return lcp_persona;
	}

	/**
	 * Find by documento and tipo documento.
	 *
	 * @param as_documento de as documento
	 * @param as_documentoTipo de as documento tipo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Persona> findByDocumentoAndTipoDocumento(String as_documento, String as_documentoTipo)
	    throws B2BException
	{
		return findByDocumentoAndTipoDocumento(as_documento, as_documentoTipo, false);
	}

	/**
	 * Método encargado de consultar por número de documento y tipo de documento.
	 *
	 * @param as_documento de as documento
	 * @param as_documentoTipo de as documento tipo
	 * @param lb_orden de lb orden
	 * @return <code>Collection<Persona></code> de objetos tipo Persona con la información consultada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Persona> findByDocumentoAndTipoDocumento(
	    String as_documento, String as_documentoTipo, boolean lb_orden
	)
	    throws B2BException
	{
		Collection<Persona> lcp_cllPersonas;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcp_cllPersonas     = new ArrayList<Persona>();
		lps_ps              = null;
		lrs_rs              = null;

		try
		{
			if(StringUtils.isValidString(as_documentoTipo) && StringUtils.isValidString(as_documento))
			{
				StringBuilder lsb_sb;
				int           li_count;

				lsb_sb       = new StringBuilder(cs_FIND_BY_DOCUMENTO_TIPO_DOCUMENTO);
				li_count     = 1;

				if(lb_orden)
					lsb_sb.append(" ORDER BY TO_NUMBER(ID_PERSONA) DESC");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_documento);
				lps_ps.setString(li_count++, as_documentoTipo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_cllPersonas.add(getPersona(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByDocumentoAndTipoDocumento", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcp_cllPersonas.isEmpty())
			lcp_cllPersonas = null;

		return lcp_cllPersonas;
	}

	/**
	 * Método encargado de consultar con base al tipo documento, número documento y razón social.
	 *
	 * @param as_documento Variable que contiene el número documento.
	 * @param as_documentoTipo Variable que contiene el tipo documento.
	 * @param as_razonSocial Variable que contiene la razón social.
	 * @return Objeto que contiene la información consultada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona findByDocumentoAndTipoDocumentoRazonSocial(
	    String as_documento, String as_documentoTipo, String as_razonSocial
	)
	    throws B2BException
	{
		Persona           lcp_cllPersonas;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcp_cllPersonas     = null;
		lps_ps              = null;
		lrs_rs              = null;

		try
		{
			if(
			    StringUtils.isValidString(as_documentoTipo) && StringUtils.isValidString(as_documento)
				    && StringUtils.isValidString(as_razonSocial)
			)
			{
				int           li_count;
				StringBuilder lsb_sb;

				li_count     = 1;
				lsb_sb       = new StringBuilder(cs_FIND_BY_DOCUMENTO_TIPO_DOCUMENTO);

				lsb_sb.append(" AND RAZON_SOCIAL = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_documento);
				lps_ps.setString(li_count++, as_documentoTipo);
				lps_ps.setString(li_count++, as_razonSocial);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcp_cllPersonas = getPersona(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByDocumentoAndTipoDocumentoRazonSocial", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcp_cllPersonas;
	}

	/**
	 * Retorna el valor del objeto de Persona.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Persona
	 * @return devuelve el valor de Persona
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Persona findById(Persona at_param)
	    throws B2BException
	{
		return (at_param != null) ? findById(at_param.getIdPersona()) : null;
	}

	/**
	 * Retorna el valor del objeto de Persona.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Persona
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Persona findById(String as_param)
	    throws B2BException
	{
		Persona           lp_persona;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lp_persona     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(StringUtils.isValidString(as_param))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_persona = getPersona(lrs_rs);
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

		return lp_persona;
	}

	/**
	 * Retorna el valor del objeto de Persona.
	 *
	 * @param ap_param de ap param
	 * @return devuelve el valor de Persona
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Persona findByMaxIdDocNum(Persona ap_param)
	    throws B2BException
	{
		Persona lp_persona;

		lp_persona = null;

		if(ap_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;

				lsb_query     = new StringBuilder(cs_FIND_BY_DOCUMENTO_TIPO_DOCUMENTO);
				li_cont       = 1;

				lsb_query.append(" ORDER BY TO_NUMBER(ID_PERSONA) DESC ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, ap_param.getNumeroDocumento());
				lps_ps.setString(li_cont++, ap_param.getIdDocumentoTipo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_persona = getPersona(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByMaxIdDocNum", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lp_persona;
	}

	/**
	 * Método encargado de buscar personas con base a su nombre.
	 *
	 * @param as_primerNombre Variable que contiene el primer nombre
	 * @param as_segundoNombre Variable que contiene el segundo nombre
	 * @param as_primerApellido Variable que contiene el primer apellido
	 * @param as_segundoApellido Variable que contiene el segundo apellido
	 * @return Colección que contiene los datos consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Persona> findByNombres(
	    String as_primerNombre, String as_segundoNombre, String as_primerApellido, String as_segundoApellido
	)
	    throws B2BException
	{
		Collection<Persona> lcp_return;

		lcp_return = new ArrayList<Persona>();

		if(StringUtils.isValidString(as_primerNombre) && StringUtils.isValidString(as_primerApellido))
		{
			boolean           ab_segundoNombre;
			boolean           ab_primerApellido;
			boolean           ab_segundoApellido;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			ab_segundoNombre       = StringUtils.isValidString(as_segundoNombre);
			ab_primerApellido      = StringUtils.isValidString(as_primerApellido);
			ab_segundoApellido     = StringUtils.isValidString(as_segundoApellido);
			lps_ps                 = null;
			lrs_rs                 = null;

			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder("SELECT * FROM SDB_ACC_PERSONA WHERE PRIMER_NOMBRE = ? ");

				if(ab_segundoNombre)
					lsb_sb.append(" AND SEGUNDO_NOMBRE = ? ");

				if(ab_primerApellido)
					lsb_sb.append(" AND PRIMER_APELLIDO = ? ");

				if(ab_segundoApellido)
					lsb_sb.append(" AND SEGUNDO_APELLIDO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_primerNombre);

				if(ab_segundoNombre)
					lps_ps.setString(li_column++, as_segundoNombre);

				if(ab_primerApellido)
					lps_ps.setString(li_column++, as_primerApellido);

				if(ab_segundoApellido)
					lps_ps.setString(li_column++, as_segundoApellido);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_return.add(getPersona(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNombres", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_return.isEmpty())
			lcp_return = null;

		return lcp_return;
	}

	/**
	 * Find by num tipo doc origen bachue.
	 *
	 * @param as_numeroDocumento de as numero documento
	 * @param as_documentoTipo de as documento tipo
	 * @return el valor de persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona findByNumTipoDocOrigenBachue(String as_numeroDocumento, String as_documentoTipo)
	    throws B2BException
	{
		Persona lp_return;

		lp_return = null;

		if(StringUtils.isValidString(as_documentoTipo) && StringUtils.isValidString(as_numeroDocumento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_count;
				StringBuilder lsb_sb;

				li_count     = 1;
				lsb_sb       = new StringBuilder(cs_FIND_BY_DOCUMENTO_TIPO_DOCUMENTO);

				lsb_sb.append(" ORDER BY TO_NUMBER(ID_PERSONA) DESC");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_numeroDocumento);
				lps_ps.setString(li_count++, as_documentoTipo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_return = getPersona(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNumTipoDocOrigenBachue", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lp_return;
	}

	/**
	 * Find by person data.
	 *
	 * @param ap_param de ap param
	 * @param as_tipoConsulta de as tipo consulta
	 * @return el valor de persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona findByPersonData(Persona ap_param, String as_tipoConsulta)
	    throws B2BException
	{
		Persona lp_persona;

		lp_persona = null;

		if((ap_param != null) && StringUtils.isValidString(as_tipoConsulta))
		{
			Collection<Persona> lcp_persona;

			lcp_persona = findCollectionByPersonData(ap_param, as_tipoConsulta);

			if(CollectionUtils.isValidCollection(lcp_persona))
			{
				Iterator<Persona> lip_iterator;

				lip_iterator     = lcp_persona.iterator();
				lp_persona       = lip_iterator.next();
			}
		}

		return lp_persona;
	}

	/**
	 * Find collection by person data.
	 *
	 * @param ap_param de ap param
	 * @param as_tipoConsulta de as tipo consulta
	 * @return el valor de persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Persona> findCollectionByPersonData(Persona ap_param, String as_tipoConsulta)
	    throws B2BException
	{
		Collection<Persona> lcp_persona;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcp_persona     = new ArrayList<Persona>();
		lps_ps          = null;
		lrs_rs          = null;

		if(ap_param != null)
		{
			try
			{
				int           li_count;
				String        ls_parametro;
				String        ls_idDocumentoTipo;
				String        ls_numeroDocumento;
				String        ls_primerNombreSolicitante;
				String        ls_segundoNombreSolicitante;
				String        ls_primerApellidoSolicitante;
				String        ls_segundoApellidoSolicitante;
				String        ls_razonSocial;
				StringBuilder lsb_sb;

				li_count                          = 1;
				ls_parametro                      = " = ?";
				ls_idDocumentoTipo                = ap_param.getTipoDocIdentidad();
				ls_numeroDocumento                = ap_param.getNumeroDocumento();
				ls_primerNombreSolicitante        = ap_param.getPrimerNombre();
				ls_segundoNombreSolicitante       = ap_param.getSegundoNombre();
				ls_primerApellidoSolicitante      = ap_param.getPrimerApellido();
				ls_segundoApellidoSolicitante     = ap_param.getSegundoApellido();
				ls_razonSocial                    = ap_param.getRazonSocial();
				lsb_sb                            = new StringBuilder(cs_FIND_BY_PERSON_DATA);

				if(
				    as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC)
					    || as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC_NOMBRES)
					    || as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC_NIT)
				)
				{
					lsb_sb.append(" ID_DOCUMENTO_TIPO");

					if(StringUtils.isValidString(ls_idDocumentoTipo))
						lsb_sb.append(ls_parametro);

					lsb_sb.append(" AND NUMERO_DOCUMENTO");

					if(StringUtils.isValidString(ls_numeroDocumento))
						lsb_sb.append(ls_parametro);
				}

				if(
				    as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.NOMBRES)
					    || as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC_NOMBRES)
				)
				{
					if(StringUtils.isValidString(ls_primerNombreSolicitante))
					{
						lsb_sb.append((StringUtils.isValidString(ls_numeroDocumento) ? " AND" : "") + " PRIMER_NOMBRE");
						lsb_sb.append(ls_parametro);
					}

					if(StringUtils.isValidString(ls_segundoNombreSolicitante))
					{
						lsb_sb.append(
						    ((StringUtils.isValidString(ls_numeroDocumento)
							    || StringUtils.isValidString(ls_primerNombreSolicitante)) ? " AND" : "")
						    + " SEGUNDO_NOMBRE"
						);
						lsb_sb.append(ls_parametro);
					}

					if(StringUtils.isValidString(ls_primerApellidoSolicitante))
					{
						lsb_sb.append(
						    ((StringUtils.isValidString(ls_numeroDocumento)
							    || StringUtils.isValidString(ls_primerNombreSolicitante)
							    || StringUtils.isValidString(ls_segundoNombreSolicitante)) ? " AND" : "")
						    + " PRIMER_APELLIDO"
						);
						lsb_sb.append(ls_parametro);
					}

					if(StringUtils.isValidString(ls_segundoApellidoSolicitante))
					{
						lsb_sb.append(
						    ((StringUtils.isValidString(ls_numeroDocumento)
							    || StringUtils.isValidString(ls_primerNombreSolicitante)
							    || StringUtils.isValidString(ls_segundoNombreSolicitante)
							    || StringUtils.isValidString(ls_primerApellidoSolicitante)) ? " AND" : "")
						    + " SEGUNDO_APELLIDO"
						);
						lsb_sb.append(ls_parametro);
					}

					lsb_sb.append(" AND ID_TIPO_PERSONA <> 'J'");
				}

				if(
				    as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.RAZON_SOCIAL)
					    || as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC_NIT)
				)
				{
					if(StringUtils.isValidString(ls_razonSocial))
					{
						lsb_sb.append(
						    (as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC_NIT) ? " AND" : "")
						    + " RAZON_SOCIAL"
						);
						lsb_sb.append(" LIKE ?");
					}

					lsb_sb.append(" AND ID_TIPO_PERSONA = 'J'");
				}

				lsb_sb.append(" ORDER BY FECHA_CREACION DESC");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(
				    as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC)
					    || as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC_NOMBRES)
					    || as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC_NIT)
				)
				{
					if(StringUtils.isValidString(ls_idDocumentoTipo))
						lps_ps.setString(li_count++, ls_idDocumentoTipo);

					if(StringUtils.isValidString(ls_numeroDocumento))
						lps_ps.setString(li_count++, ls_numeroDocumento);
				}

				if(
				    as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.NOMBRES)
					    || as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC_NOMBRES)
				)
				{
					if(StringUtils.isValidString(ls_primerNombreSolicitante))
						lps_ps.setString(li_count++, ls_primerNombreSolicitante);

					if(StringUtils.isValidString(ls_segundoNombreSolicitante))
						lps_ps.setString(li_count++, ls_segundoNombreSolicitante);

					if(StringUtils.isValidString(ls_primerApellidoSolicitante))
						lps_ps.setString(li_count++, ls_primerApellidoSolicitante);

					if(StringUtils.isValidString(ls_segundoApellidoSolicitante))
						lps_ps.setString(li_count++, ls_segundoApellidoSolicitante);
				}

				if(
				    as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.RAZON_SOCIAL)
					    || as_tipoConsulta.equalsIgnoreCase(IdentificadoresCommon.TIPO_NUM_DOC_NIT)
				)
				{
					if(StringUtils.isValidString(ls_razonSocial))
						lps_ps.setString(li_count++, "%" + ls_razonSocial + "%");
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_persona.add(getPersona(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByPersonData", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_persona.isEmpty())
			lcp_persona = null;

		return lcp_persona;
	}

	/**
	 * Método encargado de buscar personas con base a su razón social -- RAZON_SOCIAL
	 *
	 * @param as_razonSocial Variable que contiene la razón social.
	 * @return Colección que contiene los datos consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Persona> findByRazonSocial(String as_razonSocial)
	    throws B2BException
	{
		Collection<Persona> lcp_return;

		lcp_return = new ArrayList<Persona>();

		if(StringUtils.isValidString(as_razonSocial))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_RAZON_SOCIAL);

				lps_ps.setString(li_column++, as_razonSocial);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_return.add(getPersona(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByRazonSocial", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_return.isEmpty())
			lcp_return = null;

		return lcp_return;
	}

	/**
	 * Retorna el valor del objeto de Persona.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Persona
	 * @return devuelve el valor de Persona
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Persona findDataCalificador(Persona at_param)
	    throws B2BException
	{
		Persona           lp_persona;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lp_persona     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_DATA_CALIFICADOR);

				lps_ps.setString(1, at_param.getNumeroDocumento());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_persona = getIdCalificador(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDataCalificador", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lp_persona;
	}

	/**
	 * Busca la persona con el máximo id asociado a un tipo y número de documento específicos.
	 *
	 * @param as_numeroDocumento número de documento a utilizar como filtro en la busqueda
	 * @param as_documentoTipo tipo de documento a utilizar como filtro en la busqueda
	 * @return Persona resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos.
	 */
	public Persona findMaxByNumTipoDoc(String as_numeroDocumento, String as_documentoTipo)
	    throws B2BException
	{
		Persona lp_return;

		lp_return = null;

		if(StringUtils.isValidString(as_documentoTipo) && StringUtils.isValidString(as_numeroDocumento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_count;
				StringBuilder lsb_sb;

				li_count     = 1;
				lsb_sb       = new StringBuilder(cs_FIND_BY_DOCUMENTO_TIPO_DOCUMENTO);

				lsb_sb.append(" ORDER BY TO_NUMBER(ID_PERSONA) DESC ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_numeroDocumento);
				lps_ps.setString(li_count++, as_documentoTipo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_return = getPersona(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxByNumTipoDoc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lp_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aps_arg correspondiente al valor del tipo de objeto PersonaSolicitud
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<String> findNombrePersonasTerceroByIdPersona(PersonaSolicitud aps_arg)
	    throws B2BException
	{
		Collection<String> lcps_personasSolicitud;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcps_personasSolicitud     = new ArrayList<String>();
		lps_ps                     = null;
		lrs_rs                     = null;

		if(aps_arg != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_NOMBRES_PERSONAS_TERCEROS);

				lps_ps.setString(li_column++, aps_arg.getTurno());
				lps_ps.setString(li_column++, aps_arg.getIdPersonaEntrega());
				lps_ps.setString(li_column++, aps_arg.getTurno());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcps_personasSolicitud.add(lrs_rs.getString("NOMBRE"));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findNombrePersonasTerceroByIdPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcps_personasSolicitud;
	}

	/**
	 * Método para encontrar de la tabla SDB_ACC_PERSONA todos los registros que coincidan con un ID_SOLICITUD específico.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaSolicitud
	 */
	public Collection<PersonaSolicitud> findPersonasAllByIdSolicitud(String as_s)
	    throws B2BException
	{
		Collection<PersonaSolicitud> lcps_personasSolicitud;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcps_personasSolicitud     = new ArrayList<PersonaSolicitud>();
		lps_ps                     = null;
		lrs_rs                     = null;

		if(as_s != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_TO_COMPARISON_CALIDAD_ALL);

				lps_ps.setString(1, as_s);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcps_personasSolicitud.add(getPersonaSolicitud(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findPersonasAllByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcps_personasSolicitud;
	}

	/**
	 * Método para encontrar de la tabla SDB_ACC_PERSONA todos los registros que coincidan con un ID_SOLICITUD específico.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de PersonaSolicitud
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaSolicitud
	 */
	public Collection<PersonaSolicitud> findPersonasByIdSolicitud(String as_s)
	    throws B2BException
	{
		Collection<PersonaSolicitud> lcps_personasSolicitud;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcps_personasSolicitud     = new ArrayList<PersonaSolicitud>();
		lps_ps                     = null;
		lrs_rs                     = null;

		if(as_s != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_TO_COMPARISON_CALIDAD);

				lps_ps.setString(1, as_s);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcps_personasSolicitud.add(getPersonaSolicitud(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findPersonaByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcps_personasSolicitud;
	}

	/**
	 * Find razon social by documento.
	 *
	 * @param as_documento de as documento
	 * @param as_razonSocial de as razon social
	 * @return el valor de persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona findRazonSocialByDocumento(String as_documento, String as_razonSocial)
	    throws B2BException
	{
		Persona lp_persona;

		lp_persona = null;

		if(StringUtils.isValidString(as_documento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_count;

				li_count     = 1;
				lsb_sb       = new StringBuilder(cs_FIND_RAZON_SOCIAL_BY_DOCUMENTO);

				if(StringUtils.isValidString(as_razonSocial))
					lsb_sb.append(" AND RAZON_SOCIAL = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_documento);

				if(StringUtils.isValidString(as_razonSocial))
					lps_ps.setString(li_count++, as_razonSocial);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_persona = getPersona(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findRazonSocialByDocumento", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lp_persona;
	}

	/**
	 * Inserta o actualiza un registro en la tabla.
	 *
	 * @param ap_parametros Objeto contenedor de la información a almacenar
	 * @param ab_query true para insertar, false para actualizar
	 * @return Objeto final insertado o actualizado en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Persona insertOrUpdate(Persona ap_parametros, boolean ab_query)
	    throws B2BException
	{
		Persona lp_return;

		lp_return = null;

		if(ap_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lrs_rs            = null;
			lps_secuencia     = null;
			lps_ps            = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					Connection lc_connection;

					lc_connection     = getConnection();

					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							ap_parametros.setIdPersona(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, ap_parametros.getIdPersona());
						}
						else
							throw new B2BException(ErrorKeys.SIN_SECUENCIA_PERSONA);
					}
				}

				String ls_origenBachue;

				ls_origenBachue = ap_parametros.getOrigenBachue();

				lps_ps.setString(li_column++, ap_parametros.getIdDocumentoTipo().toUpperCase());
				lps_ps.setString(li_column++, ap_parametros.getNumeroDocumento());
				lps_ps.setString(li_column++, ap_parametros.getIdTipoPersona());

				{
					String ls_primerNombre;

					ls_primerNombre = ap_parametros.getPrimerNombre();

					lps_ps.setString(
					    li_column++,
					    StringUtils.isValidString(ls_primerNombre) ? ls_primerNombre.trim()
					                                               : IdentificadoresCommon.DATO_INVALIDO
					);
				}

				{
					String ls_segundoNombre;

					ls_segundoNombre = ap_parametros.getSegundoNombre();

					lps_ps.setString(
					    li_column++,
					    StringUtils.isValidString(ls_segundoNombre) ? ls_segundoNombre.trim()
					                                                : IdentificadoresCommon.DATO_INVALIDO
					);
				}

				{
					String ls_primerApellido;

					ls_primerApellido = ap_parametros.getPrimerApellido();

					lps_ps.setString(
					    li_column++,
					    StringUtils.isValidString(ls_primerApellido) ? ls_primerApellido.trim()
					                                                 : IdentificadoresCommon.DATO_INVALIDO
					);
				}

				{
					String ls_segundoApellido;

					ls_segundoApellido = ap_parametros.getSegundoApellido();

					lps_ps.setString(
					    li_column++,
					    StringUtils.isValidString(ls_segundoApellido) ? ls_segundoApellido.trim()
					                                                  : IdentificadoresCommon.DATO_INVALIDO
					);
				}

				lps_ps.setString(li_column++, ap_parametros.getRazonSocial());
				lps_ps.setString(li_column++, ap_parametros.getIdPais());
				lps_ps.setString(li_column++, ap_parametros.getIdNaturalGenero());
				lps_ps.setString(li_column++, ap_parametros.getIdEntidadExterna());

				if(!StringUtils.isValidString(ls_origenBachue))
					ls_origenBachue = EstadoCommon.N;

				lps_ps.setString(li_column++, ls_origenBachue);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ap_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ap_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ap_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ap_parametros.getIpModificacion());
				}

				if(!ab_query)
					lps_ps.setString(li_column++, ap_parametros.getIdPersona());

				lps_ps.executeUpdate();

				lp_return = ap_parametros;
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return lp_return;
	}

	/**
	 * Retorna el valor de id calificador.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de id calificador
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Persona getIdCalificador(ResultSet ars_rs)
	    throws SQLException
	{
		Persona lp_persona;

		lp_persona = new Persona();

		lp_persona.setIdPersona(ars_rs.getString("ID_PERSONA"));

		return lp_persona;
	}

	/**
	 * Metodo encargado de llenar el objeto de <code>Persona</code> con los datos enviados en el <code>ResultSet</code>.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> que contiene los datos consultados.
	 * @return Objeto de tipo <code>Persona</code> con los datos enviados en el <code>ResultSet</code>
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Persona getPersona(ResultSet ars_rs)
	    throws SQLException
	{
		return getPersona(ars_rs, false);
	}

	/**
	 * Metodo encargado de llenar objetos de <code>Persona</code> y <code>SolicitudInterviniente</code> con los datos enviados en el <code>ResultSet</code>.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> que contiene los datos consultados.
	 * @param ab_interviniente Argumento de tipo <code>boolean</code> que determina si se llenan los datos de <code>SolicitudInterviniente</code>.
	 * @return Objeto de tipo <code>Persona</code> con los datos enviados en el <code>ResultSet</code>
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Persona getPersona(ResultSet ars_rs, boolean ab_interviniente)
	    throws SQLException
	{
		Persona lp_persona;
		String  ls_idPersona;

		lp_persona       = new Persona();
		ls_idPersona     = ars_rs.getString("ID_PERSONA");

		lp_persona.setIdPersona(ls_idPersona);
		lp_persona.setIdDocumentoTipo(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		lp_persona.setNumeroDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		lp_persona.setIdTipoPersona(ars_rs.getString("ID_TIPO_PERSONA"));

		{
			String        ls_primerNombre;
			String        ls_segundoNombre;
			String        ls_primerApellido;
			String        ls_segundoApellido;
			StringBuilder lsb_sb;

			ls_primerNombre        = StringUtils.isValidString(ars_rs.getString("PRIMER_NOMBRE"))
				? ars_rs.getString("PRIMER_NOMBRE") : " ";
			ls_segundoNombre       = StringUtils.isValidString(ars_rs.getString("SEGUNDO_NOMBRE"))
				? ars_rs.getString("SEGUNDO_NOMBRE") : " ";
			ls_primerApellido      = StringUtils.isValidString(ars_rs.getString("PRIMER_APELLIDO"))
				? ars_rs.getString("PRIMER_APELLIDO") : " ";
			ls_segundoApellido     = StringUtils.isValidString(ars_rs.getString("SEGUNDO_APELLIDO"))
				? ars_rs.getString("SEGUNDO_APELLIDO") : " ";
			lsb_sb                 = new StringBuilder();

			lp_persona.setPrimerNombre(ls_primerNombre);
			lp_persona.setSegundoNombre(ls_segundoNombre);
			lp_persona.setPrimerApellido(ls_primerApellido);
			lp_persona.setSegundoApellido(ls_segundoApellido);

			lsb_sb.append(ls_primerNombre + " ");
			lsb_sb.append(ls_segundoNombre + " ");
			lsb_sb.append(ls_primerApellido + " ");
			lsb_sb.append(ls_segundoApellido + " ");

			lp_persona.setNombreCompleto(lsb_sb.toString());
		}

		lp_persona.setOrigenBachue(ars_rs.getString("ORIGEN_BACHUE"));
		lp_persona.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
		lp_persona.setIdPais(ars_rs.getString("ID_PAIS"));
		lp_persona.setIdNaturalGenero(ars_rs.getString("ID_NATURAL_GENERO"));
		lp_persona.setIdEntidadExterna(ars_rs.getString("ID_ENTIDAD_EXTERNA"));
		lp_persona.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lp_persona.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		if(ab_interviniente)
		{
			SolicitudInterviniente lsi_solicitudInterviniente;

			lsi_solicitudInterviniente = new SolicitudInterviniente();

			lsi_solicitudInterviniente.setIdPersona(ls_idPersona);
			lsi_solicitudInterviniente.setRolPersona(ars_rs.getString("ROL_PERSONA"));
			lsi_solicitudInterviniente.setIdAutorizacionComunicacion(ars_rs.getString("ID_AUTORIZACION_COMUNICACION"));
			lsi_solicitudInterviniente.setIdAutorizacionNotificacion(ars_rs.getString("ID_AUTORIZACION_NOTIFICACION"));

			lp_persona.setSolicitudInterviniente(lsi_solicitudInterviniente);
		}

		return lp_persona;
	}

	/**
	 * Retorna el valor de persona solicitud.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de persona solicitud
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PersonaSolicitud getPersonaSolicitud(ResultSet ars_rs)
	    throws SQLException
	{
		PersonaSolicitud lps_ps;

		lps_ps = new PersonaSolicitud();

		lps_ps.setTurno(ars_rs.getString("ID_TURNO"));
		lps_ps.setIdPersonaEntrega(ars_rs.getString("ID_PERSONA_ENTREGA"));
		lps_ps.setIdDocumentoTipo(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		lps_ps.setNumeroDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		lps_ps.setPrimerNombre(ars_rs.getString("PRIMER_NOMBRE"));
		lps_ps.setSegundoNombre(ars_rs.getString("SEGUNDO_NOMBRE"));
		lps_ps.setPrimerApellido(ars_rs.getString("PRIMER_APELLIDO"));
		lps_ps.setSegundoApellido(ars_rs.getString("SEGUNDO_APELLIDO"));
		lps_ps.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
		lps_ps.setFechaEntrega(ars_rs.getTimestamp("FECHA_ENTREGA"));
		lps_ps.setNombreCalidad(ars_rs.getString("NOMBRE_CALIDAD"));
		lps_ps.setIdPersonaTercero(ars_rs.getString("ID_PERSONA_TERCERO"));

		return lps_ps;
	}
}
