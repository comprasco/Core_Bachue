package com.bachue.snr.prosnr01.dao.acc;

import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoTurno;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_SOLICITUD
 *
 * @author hcastaneda
 */
public class SolicitudDAO extends BaseDAO
{
	/** Constante cs_FIND_SOLICITUD_PAGADA. */
	private static final String cs_FIND_SOLICITUD_PAGADA = "SELECT S.NIR, P.NOMBRE PROCESO, SP.NOMBRE SUBPROCESO, L.FECHA_PAGO FECHA_MAX FROM SDB_ACC_SOLICITUD S "
		+ "INNER JOIN SDB_ACC_LIQUIDACION L ON L.ID_SOLICITUD = S.ID_SOLICITUD AND NVL(L.PAGADA,'N') = 'S' INNER JOIN SDB_ACC_PROCESO P ON P.ID_PROCESO = S.ID_PROCESO "
		+ "INNER JOIN SDB_ACC_SUBPROCESO SP ON SP.ID_PROCESO = P.ID_PROCESO AND SP.ID_SUBPROCESO = S.ID_SUBPROCESO INNER JOIN SDB_ACC_PERSONA AP ON AP.ID_PERSONA = S.ID_PERSONA "
		+ "INNER JOIN SDB_ACC_REGISTRO_PAGO RP ON RP.ID_SOLICITUD = S.ID_SOLICITUD AND RP.NUMERO_REFERENCIA = L.NUMERO_REFERENCIA LEFT JOIN (SELECT ID_SOLICITUD,MAX(DS.FECHA_CREACION) FECHA_MAX "
		+ "FROM SDB_ACC_DOCUMENTOS_SALIDA DS WHERE DS.ESTADO <> 'I' GROUP BY ID_SOLICITUD) FECHA ON FECHA.ID_SOLICITUD = S.ID_SOLICITUD WHERE S.NIR = ? AND S.ID_PROCESO = ? AND L.FECHA_PAGO IS NOT NULL ORDER BY L.FECHA_CREACION DESC";

	/** Constante cs_FIND_SOLICITUDES_PAGADAS_SOLICITANTE. */
	private static final String cs_FIND_SOLICITUDES_PAGADAS_SOLICITANTE = "SELECT S.NIR, P.NOMBRE PROCESO, SP.NOMBRE SUBPROCESO, L.FECHA_PAGO FECHA_MAX FROM SDB_ACC_SOLICITUD S "
		+ "INNER JOIN SDB_ACC_LIQUIDACION L ON L.ID_SOLICITUD = S.ID_SOLICITUD AND NVL(L.PAGADA,'N') = 'S' INNER JOIN SDB_ACC_PROCESO P ON P.ID_PROCESO = S.ID_PROCESO "
		+ "INNER JOIN SDB_ACC_SUBPROCESO SP ON SP.ID_PROCESO = P.ID_PROCESO AND SP.ID_SUBPROCESO = S.ID_SUBPROCESO INNER JOIN SDB_ACC_PERSONA AP ON AP.ID_PERSONA = S.ID_PERSONA "
		+ "INNER JOIN SDB_ACC_REGISTRO_PAGO RP ON RP.ID_SOLICITUD = S.ID_SOLICITUD AND RP.NUMERO_REFERENCIA = L.NUMERO_REFERENCIA LEFT JOIN (SELECT ID_SOLICITUD,MAX(DS.FECHA_CREACION) FECHA_MAX "
		+ "FROM SDB_ACC_DOCUMENTOS_SALIDA DS WHERE DS.ESTADO <> 'I' GROUP BY ID_SOLICITUD) FECHA ON FECHA.ID_SOLICITUD = S.ID_SOLICITUD WHERE AP.ID_DOCUMENTO_TIPO = ? AND AP.NUMERO_DOCUMENTO = ? "
		+ "AND FECHA.FECHA_MAX IS NOT NULL AND (S.FECHA_CREACION BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') AND TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')) AND S.ID_PROCESO = ? AND L.FECHA_PAGO IS NOT NULL ORDER BY L.FECHA_CREACION DESC";

	/** Constante cs_FIND_SOLICITUDES_TURNOS_PAGADOS_SOLICITANTE. */
	private static final String cs_FIND_SOLICITUDES_TURNOS_PAGADOS_SOLICITANTE = "SELECT T.ID_TURNO, S.NIR, P.NOMBRE PROCESO, SP.NOMBRE SUBPROCESO, L.FECHA_PAGO FECHA_MAX FROM SDB_ACC_SOLICITUD S "
		+ "INNER JOIN SDB_ACC_TURNO T ON T.ID_SOLICITUD = S.ID_SOLICITUD INNER JOIN SDB_ACC_LIQUIDACION L ON L.ID_SOLICITUD = S.ID_SOLICITUD AND NVL(L.PAGADA,'N') = 'S' "
		+ "INNER JOIN SDB_ACC_PROCESO P ON P.ID_PROCESO = S.ID_PROCESO INNER JOIN SDB_ACC_SUBPROCESO SP ON SP.ID_PROCESO = P.ID_PROCESO AND SP.ID_SUBPROCESO = S.ID_SUBPROCESO "
		+ "INNER JOIN SDB_ACC_PERSONA AP ON AP.ID_PERSONA = S.ID_PERSONA INNER JOIN SDB_ACC_REGISTRO_PAGO RP ON RP.ID_SOLICITUD = S.ID_SOLICITUD AND RP.NUMERO_REFERENCIA = L.NUMERO_REFERENCIA "
		+ "LEFT JOIN (SELECT ID_SOLICITUD,MAX(DS.FECHA_CREACION) FECHA_MAX FROM SDB_ACC_DOCUMENTOS_SALIDA DS WHERE DS.ESTADO <> 'I' GROUP BY ID_SOLICITUD) FECHA ON FECHA.ID_SOLICITUD = S.ID_SOLICITUD "
		+ "WHERE AP.ID_DOCUMENTO_TIPO = ? AND AP.NUMERO_DOCUMENTO = ? AND S.NIR = ? AND FECHA.FECHA_MAX IS NOT NULL AND L.FECHA_PAGO IS NOT NULL ORDER BY L.FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SOLICITUD WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_ID_PROCESO_AND_SUBPROCESO. */
	private static final String cs_FIND_BY_ID_PROCESO_AND_SUBPROCESO = "SELECT * FROM SDB_ACC_SOLICITUD WHERE ID_SOLICITUD = ? AND ID_PROCESO = ? AND ID_SUBPROCESO = ?";

	/** Constante cs_FIND_LAST_ASSOCIATE_BY_ID_SOLICITUD_PROCESO. */
	private static final String cs_FIND_ASSOCIATE_BY_ID_SOLICITUD_PROCESO = "SELECT SAS.* FROM SDB_ACC_SOLICITUD_ASOCIADA SASA INNER JOIN SDB_ACC_SOLICITUD SAS ON SASA.ID_SOLICITUD1 = SAS.ID_SOLICITUD WHERE SASA.ID_SOLICITUD = ? AND SAS.ID_PROCESO = ? ORDER BY SAS.FECHA_CREACION DESC ";

	/** Constante cs_FIND_BY_NIR. */
	private static final String cs_FIND_BY_NIR = "SELECT * FROM SDB_ACC_SOLICITUD WHERE NIR = ?";

	/** Constante cs_FIND_NIR_BY_TURNO. */
	private static final String cs_FIND_NIR_BY_TURNO = "SELECT SAS.NIR AS NIR FROM SDB_ACC_TURNO SAT INNER JOIN SDB_ACC_SOLICITUD SAS "
		+ "ON SAS.ID_SOLICITUD = SAT.ID_SOLICITUD WHERE SAT.ID_TURNO = ?";

	/** Constante cs_FIND_ID_SOLICITUD_BY_ID_TURNO. */
	private static final String cs_FIND_ID_SOLICITUD_BY_ID_TURNO = "SELECT SAT.ID_SOLICITUD AS ID_SOLICITUD FROM SDB_ACC_TURNO SAT INNER JOIN "
		+ "SDB_ACC_SOLICITUD SAS ON SAS.ID_SOLICITUD = SAT.ID_SOLICITUD WHERE SAT.ID_TURNO = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_SOLICITUD_ID_SOLICITUD.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD SET ID_PROCESO = ?,"
		+ "ID_SUBPROCESO = ?,FECHA_SOLICITUD = ?,ID_PERSONA = ?,ID_DOCUMENTO = ?,ENTIDAD_EXENTA = ?,"
		+ "ANIO_ANT = ?,ID_CIRCULO_ANT = ?,ID_PROCESO_ANT = ?,ID_TURNO_ANT = ?,USUARIO_ANT = ?,COMENTARIO = ?,"
		+ "DERECHO_PETICION = ?,DESCRIPCION = ?,ID_TIPO_RECEPCION = ?,ID_DATOS_ANT_SISTEMA = ?,ID_TESTAMENTO = ?,"
		+ "ID_CALIDAD_SOLICITANTE = ?,OBSERVACIONES_RESTITUCION = ?,CANTIDAD_MATRICULAS = ?,"
		+ "PARTICIPA_INTERVINIENTE = ?,ID_ESTADO_SOLICITUD = ?,FECHA_ULTIMO_ESTADO = ?,RADICADO_PQRS = ?,"
		+ "FECHA_RADICADO_PQRS = ?,ID_AUTORIZACION_COMUNICACION = ?,ID_AUTORIZACION_NOTIFICACION = ?,"
		+ "ID_DIRECCION = ?,ID_TELEFONO = ?,ID_CORREO_ELECTRONICO = ?,ID_DIRECCION_COMUNICACION = ?, ID_TELEFONO_COMUNICACION = ?, ID_CORREO_ELECTRONICO_COMUNICACION = ?, "
		+ "NIR = ?,IDENTIFICADOR_COPIAS_SE = ?,USUARIO_ORIP = ?,ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ?,DIGITALIZADA = ?,VERSION_DOCUMENTO = ?,ID_PROCEDENCIA = ?,"
		+ "ID_ETAPA_ACTUAL = ?,ID_PERSONA_ENTREGA = ?,ID_TURNO_REPRODUCCION = ?, ID_ENTIDAD_EXTERNA = ?, TIPO_REGISTRO_CERTIFICADO = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE_VERSION = "UPDATE SDB_ACC_SOLICITUD SET VERSION_SUBPROCESO = ?,ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_FUNCION_MAXIMA_VERSION. */
	private static final String cs_FUNCION_MAXIMA_VERSION = "SELECT PKG_WORKFLOW.FUNC_ULTIMA_VERSION(?, ?) FROM DUAL";

	/** Constante cs_UPDATE_REGISTRO. */
	private static final String cs_UPDATE_REGISTRO = "UPDATE SDB_ACC_SOLICITUD SET VERSION_DOCUMENTO_INICIAL = ?, ID_PROCESO = ?,"
		+ "ID_SUBPROCESO = ?,FECHA_SOLICITUD = ?,ID_PERSONA = ?,ID_DOCUMENTO = ?,ENTIDAD_EXENTA = ?,"
		+ "ANIO_ANT = ?,ID_CIRCULO_ANT = ?,ID_PROCESO_ANT = ?,ID_TURNO_ANT = ?,USUARIO_ANT = ?,COMENTARIO = ?,"
		+ "DERECHO_PETICION = ?,DESCRIPCION = ?,ID_TIPO_RECEPCION = ?,ID_DATOS_ANT_SISTEMA = ?,ID_TESTAMENTO = ?,"
		+ "ID_CALIDAD_SOLICITANTE = ?,OBSERVACIONES_RESTITUCION = ?,CANTIDAD_MATRICULAS = ?,"
		+ "PARTICIPA_INTERVINIENTE = ?,ID_ESTADO_SOLICITUD = ?,FECHA_ULTIMO_ESTADO = ?,RADICADO_PQRS = ?,"
		+ "FECHA_RADICADO_PQRS = ?,ID_AUTORIZACION_COMUNICACION = ?,ID_AUTORIZACION_NOTIFICACION = ?,"
		+ "ID_DIRECCION = ?,ID_TELEFONO = ?,ID_CORREO_ELECTRONICO = ?,ID_DIRECCION_COMUNICACION = ?, ID_TELEFONO_COMUNICACION = ?, ID_CORREO_ELECTRONICO_COMUNICACION = ?, "
		+ "NIR = ?,ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ?,DIGITALIZADA = ?,VERSION_DOCUMENTO = ?,ID_PROCEDENCIA = ?,"
		+ "ID_ETAPA_ACTUAL = ?,ID_PERSONA_ENTREGA = ?,ID_TURNO_REPRODUCCION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_UPDATE_NUMERO_COPIAS. */
	private static final String cs_UPDATE_NUMERO_COPIAS = "UPDATE SDB_ACC_SOLICITUD SET NUMERO_COPIAS = ?,"
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_UPDATE_DESCRIPCION. */
	private static final String cs_UPDATE_DESCRIPCION = "UPDATE SDB_ACC_SOLICITUD SET DESCRIPCION = ?,"
		+ "ID_USUARIO_MODIFICACION = ?,"
		+ "FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_UPDATE_DESCRIPCION. */
	private static final String cs_UPDATE_TIPO_REPRODUCCION = "UPDATE SDB_ACC_SOLICITUD SET REPRODUCCION_TESTAMENTO = ?,"
		+ "ID_USUARIO_MODIFICACION = ?,"
		+ "FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_UPDATE_SET. */
	private static final String cs_UPDATE_SET = "UPDATE SDB_ACC_SOLICITUD SET ";

	/** Constante cs_UPDATE_COMPLEMENT. */
	private static final String cs_UPDATE_COMPLEMENT = "ID_USUARIO_MODIFICACION = ?,"
		+ "FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_UPDATE_SUBPROCESO. */
	private static final String cs_UPDATE_SUBPROCESO = "UPDATE SDB_ACC_SOLICITUD SET ID_SUBPROCESO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?  WHERE ID_SOLICITUD = ?";

	/** Constante cs_UPDATE_SOLICITUD_RECEPCION. */
	private static final String cs_UPDATE_SOLICITUD_RECEPCION = " UPDATE SDB_ACC_SOLICITUD SET ID_PROCESO = ? , ID_TURNO_ANT = ? ,ID_DOCUMENTO = ?,ID_SUBPROCESO = ?, ID_USUARIO_MODIFICACION = ?, "
		+ " FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_UPDATE_ID_DATOS_ANT_SISTEMA. */
	private static final String cs_UPDATE_ID_DATOS_ANT_SISTEMA = "UPDATE SDB_ACC_SOLICITUD SET ID_DATOS_ANT_SISTEMA = ?, ID_USUARIO_MODIFICACION = ?, "
		+ "IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_SOLICITUD = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD (ID_SOLICITUD,ID_PROCESO,ID_SUBPROCESO,"
		+ "FECHA_SOLICITUD,ID_PERSONA,ID_DOCUMENTO,ENTIDAD_EXENTA,ANIO_ANT,ID_CIRCULO_ANT,ID_PROCESO_ANT,ID_TURNO_ANT,"
		+ "USUARIO_ANT,COMENTARIO,DERECHO_PETICION,DESCRIPCION,ID_TIPO_RECEPCION,ID_DATOS_ANT_SISTEMA,ID_TESTAMENTO,"
		+ "ID_CALIDAD_SOLICITANTE,OBSERVACIONES_RESTITUCION,CANTIDAD_MATRICULAS,PARTICIPA_INTERVINIENTE,ID_ESTADO_SOLICITUD,"
		+ "FECHA_ULTIMO_ESTADO,RADICADO_PQRS,FECHA_RADICADO_PQRS,ID_AUTORIZACION_COMUNICACION,ID_AUTORIZACION_NOTIFICACION,"
		+ "ID_DIRECCION,ID_TELEFONO,ID_CORREO_ELECTRONICO,ID_DIRECCION_COMUNICACION,ID_TELEFONO_COMUNICACION,ID_CORREO_ELECTRONICO_COMUNICACION,NIR,IDENTIFICADOR_COPIAS_SE,USUARIO_ORIP,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,DIGITALIZADA,VERSION_DOCUMENTO,ID_PROCEDENCIA,ID_ETAPA_ACTUAL,ID_PERSONA_ENTREGA,ID_TURNO_REPRODUCCION, ID_ENTIDAD_EXTERNA, MOTIVO_CONSULTA,REFERENCIA_MOTIVO_CONSULTA,TIPO_REGISTRO_CERTIFICADO, ID_CUENTA_CUPO, VALOR_RECARGA) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	/** Constante cs_UPDATE_DIGITALIZADA. */
	private static final String cs_UPDATE_DIGITALIZADA = "UPDATE SDB_ACC_SOLICITUD SET DIGITALIZADA = ?,"
		+ "ID_USUARIO_MODIFICACION = ?,"
		+ "FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_ID_DOCUMENTO. */
	private static final String cs_FIND_BY_ID_DOCUMENTO = "SELECT * FROM SDB_ACC_SOLICITUD WHERE ID_DOCUMENTO=?";

	/** Constante cs_FIND_BY_ID_DOCUMENTO. */
	private static final String cs_FIND_BY_ID_TESTAMENTO = "SELECT * FROM SDB_ACC_SOLICITUD WHERE ID_TESTAMENTO=?";

	/** Constante cs_FIND_BY_ID_DATOS_ANT_SISTEMA. */
	private static final String cs_FIND_BY_ID_DATOS_ANT_SISTEMA = "SELECT * FROM SDB_ACC_SOLICITUD WHERE ID_DATOS_ANT_SISTEMA=?";

	/** Constante cs_FIND_PERSONA_BY_DOCUMENTO_TIPO_DOCUMENTO. */
	private static final String cs_FIND_PERSONA_BY_DOCUMENTO_TIPO_DOCUMENTO = "SELECT MAX(ID_PERSONA)ID_PERSONA FROM SDB_ACC_PERSONA WHERE ID_DOCUMENTO_TIPO = LTRIM(RTRIM(?)) AND NUMERO_DOCUMENTO = LTRIM(RTRIM(?))";

	/** Constante cs_BUSCAR_SOLICITUD_BY_ID_TURNO. */
	private static final String cs_BUSCAR_SOLICITUD_BY_ID_TURNO = "SELECT SAS.* FROM SDB_ACC_TURNO SAT INNER JOIN "
		+ "SDB_ACC_SOLICITUD SAS ON SAS.ID_SOLICITUD = SAT.ID_SOLICITUD WHERE SAT.ID_TURNO = ?";

	/**
	 * Instancia un nuevo objeto solicitud DAO.
	 */
	public SolicitudDAO()
	{
	}

	/**
	 * Método encargado de actualizar la descripcion de solicitud a la tabla SDB_ACC_SOLICITUD para un id_solicitud determinado.
	 *
	 * @param as_parametros objeto de tipo <Solicitud> que contiene los datos para ejecutar la sentencia.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizaTipoReproduccion(Solicitud as_parametros)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_TIPO_REPRODUCCION);

				lps_ps.setString(li_column++, as_parametros.getReproduccionTestamento());
				lps_ps.setString(li_column++, as_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, as_parametros.getIpModificacion());
				lps_ps.setString(li_column++, as_parametros.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizaTipoReproduccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método sobrecargado para enviar los parametros de la persona a consultar.
	 *
	 * @param ap_persona Objeto Persona a extraer la información
	 * @return lp_return Objeto Persona con información consultada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Persona consultarInterviniente(Persona ap_persona)
	    throws B2BException
	{
		Persona lp_return;

		lp_return = null;

		if(ap_persona != null)
		{
			String as_idDocumentoTipo;
			String as_numeroDocumento;

			as_idDocumentoTipo     = ap_persona.getIdDocumentoTipo();
			as_numeroDocumento     = ap_persona.getNumeroDocumento();

			lp_return = consultarInterviniente(as_idDocumentoTipo, as_numeroDocumento);
		}

		return lp_return;
	}

	/**
	 * Método para consultar una persona/interviniente.
	 *
	 * @param as_idDocumentoTipo String con el tipo de documento a consultar
	 * @param as_numeroDocumento String con el número de documento a consultar
	 * @return lp_return Objeto Persona con información consultada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Persona
	 */
	public Persona consultarInterviniente(String as_idDocumentoTipo, String as_numeroDocumento)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		Persona           ll_idPersona;

		ll_idPersona     = null;
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_idDocumentoTipo) && StringUtils.isValidString(as_numeroDocumento))
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_PERSONA_BY_DOCUMENTO_TIPO_DOCUMENTO);

				lps_ps.setString(li_column++, as_idDocumentoTipo.toUpperCase());
				lps_ps.setString(li_column++, as_numeroDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
				{
					ll_idPersona = new Persona();

					ll_idPersona.setIdPersona(lrs_rs.getString("ID_PERSONA"));
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultarInterviniente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ll_idPersona;
	}

	/**
	 * Método encargado de consultar las solicitudes de la tabla SDB_ACC_SOLICITUD para un id documento determinado.
	 *
	 * @param as_idDocumento objeto de tipo <String> que contiene el dato para ejecutar la consulta.
	 * @return Collection<Solicitud> colección de datos de tipo solicitud con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Collection<Solicitud> findAllByIdDocumento(String as_idDocumento)
	    throws B2BException
	{
		Documento ld_documento;

		if(StringUtils.isValidString(as_idDocumento))
		{
			ld_documento = new Documento();

			ld_documento.setIdDocumento(as_idDocumento);
		}
		else
			ld_documento = null;

		return findAllByIdDocumento(ld_documento, false);
	}

	/**
	 * Método encargado de consultar las solicitudes de la tabla SDB_ACC_SOLICITUD para un id documento determinado.
	 *
	 * @param ad_idDocumento objeto de tipo <String> que contiene el dato para ejecutar la consulta.
	 * @param ab_conVersion correspondiente al valor del tipo de objeto boolean
	 * @return Collection<Solicitud> colección de datos de tipo solicitud con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Collection<Solicitud> findAllByIdDocumento(Documento ad_idDocumento, boolean ab_conVersion)
	    throws B2BException
	{
		Collection<Solicitud> lcs_solicitud;

		lcs_solicitud = new ArrayList<Solicitud>();

		if(ad_idDocumento != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder();

				lsb_query.append(cs_FIND_BY_ID_DOCUMENTO);

				if(ab_conVersion)
					lsb_query.append(" AND VERSION_DOCUMENTO = ?");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, ad_idDocumento.getIdDocumento());

				if(ab_conVersion)
					setLong(lps_ps, ad_idDocumento.getVersionDocumento(), 2);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcs_solicitud.add(getSolicitud(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdDocumento", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcs_solicitud))
			lcs_solicitud = null;

		return lcs_solicitud;
	}

	/**
	 * Método encargado de consultar las solicitudes de la tabla SDB_ACC_SOLICITUD para un proceso determinado
	 * @param as_idSolicitud tipo string para filtrar en la tabla
	 * @param as_idProceso tipo string para filtrar en la tabla
	 * @return colección de solicitudes
	 * @throws B2BException
	 */
	public Collection<Solicitud> findAssociateByIdSolicitudProceso(String as_idSolicitud, String as_idProceso)
	    throws B2BException
	{
		Collection<Solicitud> lcs_solicitudes;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcs_solicitudes     = null;
		lps_ps              = null;
		lrs_rs              = null;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idProceso))
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_ASSOCIATE_BY_ID_SOLICITUD_PROCESO);

				lps_ps.setString(li_contador++, as_idSolicitud);
				lps_ps.setString(li_contador++, as_idProceso);

				lrs_rs              = lps_ps.executeQuery();
				lcs_solicitudes     = new ArrayList<Solicitud>();

				while(lrs_rs.next())
					lcs_solicitudes.add(getSolicitud(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAssociateByIdSolicitudProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcs_solicitudes;
	}

	/**
	 * Método encargado de consultar la solicitud de la tabla SDB_ACC_SOLICITUD para un id_solicitud determinado.
	 *
	 * @param as_param objeto de tipo <Solicitud> que contiene los datos para ejecutar la consulta.
	 * @return objeto de tipo <Solicitud> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Solicitud findById(String as_param)
	    throws B2BException
	{
		Solicitud         ls_solicitud;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_solicitud     = null;
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_param))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_solicitud = getSolicitud(lrs_rs);
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

		return ls_solicitud;
	}
	
	
	public Solicitud findByIdAndProcessAndSubprocess(String as_idSolicitud, String as_proceso,String as_subproceso)
		    throws B2BException
		{
			Solicitud         ls_solicitud;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			ls_solicitud     = null;
			lps_ps           = null;
			lrs_rs           = null;

			if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_proceso) && StringUtils.isValidString(as_subproceso))
			{
				try
				{
					int li_contador;
					
					li_contador     = 1;
					
					lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_PROCESO_AND_SUBPROCESO);

					lps_ps.setString(li_contador++, as_idSolicitud);
					lps_ps.setString(li_contador++, as_proceso);
					lps_ps.setString(li_contador++, as_subproceso);

					lrs_rs = lps_ps.executeQuery();

					if(lrs_rs.next())
						ls_solicitud = getSolicitud(lrs_rs);
				}
				catch(SQLException lse_e)
				{
					logError(this, "findByIdAndProcessAndSubprocess", lse_e);

					throw new B2BException(SQL_ERROR, lse_e);
				}
				finally
				{
					close(lrs_rs);
					close(lps_ps);
				}
			}

			return ls_solicitud;
		}

	/**
	 * Método encargado de consultar la solicitud de la tabla SDB_ACC_SOLICITUD para un id_solicitud determinado.
	 *
	 * @param as_param objeto de tipo <Solicitud> que contiene los datos para ejecutar la consulta.
	 * @return objeto de tipo <Solicitud> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Solicitud findById(Solicitud as_param)
	    throws B2BException
	{
		return (as_param != null) ? findById(as_param.getIdSolicitud()) : null;
	}

	/**
	 * Método encargado de consultar la solicitud de la tabla SDB_ACC_SOLICITUD para un id_datos_ant_sistema determinado.
	 *
	 * @param as_idDatosAntSistema objeto de tipo <String> que contiene el dato para ejecutar la consulta.
	 * @return objeto de tipo <Solicitud> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Solicitud findByIdDatosAntSistema(String as_idDatosAntSistema)
	    throws B2BException
	{
		Solicitud         ls_solicitud;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_solicitud     = null;
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_idDatosAntSistema))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_DATOS_ANT_SISTEMA);

				lps_ps.setString(1, as_idDatosAntSistema);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_solicitud = getSolicitud(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdDatosAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_solicitud;
	}

	/**
	 * Método encargado de consultar la solicitud de la tabla SDB_ACC_SOLICITUD para un id_documento determinado.
	 *
	 * @param as_idDocumento objeto de tipo <String> que contiene el dato para ejecutar la consulta.
	 * @return objeto de tipo <Solicitud> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Solicitud findByIdDocumento(String as_idDocumento)
	    throws B2BException
	{
		Solicitud         ls_solicitud;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_solicitud     = null;
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_idDocumento))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_DOCUMENTO);

				lps_ps.setString(1, as_idDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_solicitud = getSolicitud(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdDocumento", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_solicitud;
	}

	/**
	 * Método encargado de consultar la solicitud de la tabla SDB_ACC_SOLICITUD para un id_documento determinado.
	 *
	 * @param as_idTestamento objeto de tipo <String> que contiene el dato para ejecutar la consulta.
	 * @return objeto de tipo <Solicitud> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Solicitud findByIdTestamento(String as_idTestamento)
	    throws B2BException
	{
		Solicitud         ls_solicitud;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_solicitud     = null;
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_idTestamento))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TESTAMENTO);

				lps_ps.setString(1, as_idTestamento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_solicitud = getSolicitud(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTestamento", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_solicitud;
	}

	/**
	 * Método encargado de consultar la solicitud de la tabla SDB_ACC_SOLICITUD para un NIR determinado.
	 *
	 * @param as_param objeto de tipo <Solicitud> que contiene los datos para ejecutar la consulta.
	 * @return objeto de tipo <Solicitud> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Solicitud findByNir(Solicitud as_param)
	    throws B2BException
	{
		return (as_param != null) ? findByNir(as_param.getNir()) : null;
	}

	/**
	 * Método encargado de consultar la solicitud de la tabla SDB_ACC_SOLICITUD para un NIR determinado.
	 *
	 * @param as_param objeto de tipo <Solicitud> que contiene los datos para ejecutar la consulta.
	 * @return objeto de tipo <Solicitud> con todos los registros encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Solicitud findByNir(String as_param)
	    throws B2BException
	{
		Solicitud ls_solicitud;

		ls_solicitud = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_NIR);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_solicitud = getSolicitud(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNir", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_solicitud;
	}

	/**
	 * Método encargado de consultar el ID_SOLICITUD de la solicitud a la tabla SDB_ACC_SOLICITUD para un id_turno determinado.
	 *
	 * @param as_idTurno objeto de tipo <String> que contiene el dato para ejecutar la consulta.
	 * @return objeto de tipo <String> con el ID_SOLICITUD encontrado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findIdSolicitudByIdTurno(String as_idTurno)
	    throws B2BException
	{
		String            ls_idSolicitud;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_idSolicitud     = null;
		lps_ps             = null;
		lrs_rs             = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ID_SOLICITUD_BY_ID_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_idSolicitud = lrs_rs.getString("ID_SOLICITUD");
			}
			catch(SQLException lse_e)
			{
				logError(this, "findIdSolicitudByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_idSolicitud;
	}

	/**
	 * Método encargado de consultar las solicitudes de la tabla SDB_ACC_SOLICITUD para un id documento determinado
	 *
	 * @param as_idProceso tipo string para filtrar en la tabla
	 * @param as_idSubProceso tipo string para filtrar en la tabla
	 * @return int
	 * @throws B2BException excepción
	 */
	public int findMaxVersion(String as_idProceso, String as_idSubProceso)
	    throws B2BException
	{
		int li_version;

		li_version = 0;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FUNCION_MAXIMA_VERSION);

				lps_ps.setString(1, as_idProceso);
				lps_ps.setString(2, as_idSubProceso);
				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_version = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxVersion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		return li_version;
	}

	/**
	 * Método encargado de consultar el NIR de la solicitud a la tabla SDB_ACC_SOLICITUD para un id_turno determinado.
	 *
	 * @param as_idTurno objeto de tipo <String> que contiene el dato para ejecutar la consulta.
	 * @return objeto de tipo <String> con el NIR encontrado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findNirByIdTurno(String as_idTurno)
	    throws B2BException
	{
		String            ls_nir;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_nir     = null;
		lps_ps     = null;
		lrs_rs     = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_NIR_BY_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_nir = lrs_rs.getString("NIR");
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNir", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_nir;
	}

	/**
	 * Método encargado de consultar la selcuencia de la tabla SDB_ACC_SOLICITUD.
	 *
	 * @return variable de tipo <int> con la secuencia consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuence()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/**
	 * Método encargado de consultar la solicitud a la tabla SDB_ACC_SOLICITUD para un id_turno determinado.
	 *
	 * @param as_idTurno objeto de tipo <String> que contiene el dato para ejecutar la consulta.
	 * @return objeto de tipo <Solicitud> encontrado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Solicitud findSolicitudByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Solicitud         ls_idSolicitud;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_idSolicitud     = null;
		lps_ps             = null;
		lrs_rs             = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_BUSCAR_SOLICITUD_BY_ID_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_idSolicitud = getSolicitud(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findSolicitudByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_idSolicitud;
	}

	/**
	 * Método encargado de consultar solicitudes pagadas con base a un NIR.
	 *
	 * @param as_nir Variable de tipo String que contiene el nir a consultar
	 * @param as_servicio correspondiente al valor del tipo de objeto String
	 * @return Solicitud encontrada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSolicitud
	 */
	public TipoSolicitud findSolicitudPagada(String as_nir, String as_servicio)
	    throws B2BException
	{
		TipoSolicitud     lts_solicitud;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lts_solicitud     = null;
		lps_ps            = null;
		lrs_rs            = null;

		if(StringUtils.isValidString(as_nir) && StringUtils.isValidString(as_servicio))
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_SOLICITUD_PAGADA);

				lps_ps.setString(li_column++, as_nir);
				lps_ps.setString(li_column++, as_servicio);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lts_solicitud = getSolicitudPagada(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findSolicitudPagada", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lts_solicitud;
	}

	/**
	 * Método encargado de consultar las solicitudes pagadas bajo los criterios de consulta.
	 *
	 * @param as_idTipoDocumento Variable de tipo String que contiene el id tipo documento del solicitante.
	 * @param as_numeroDocumento Variable de tipo String que contiene el número de documento del solicitante.
	 * @param as_servicio Variable de tipo String que contiene el id del proceso a consultar.
	 * @param ad_fechaDesde Variable de tipo Date que contiene la fecha de inicio.
	 * @param ad_fechaHasta Variable de tipo Date que contiene la fecha final.
	 * @return Colección que contiene las solicitudes consultadas.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSolicitud
	 */
	public Collection<TipoSolicitud> findSolicitudesPagadas(
	    String as_idTipoDocumento, String as_numeroDocumento, String as_servicio, Date ad_fechaDesde, Date ad_fechaHasta
	)
	    throws B2BException
	{
		Collection<TipoSolicitud> lcts_solicitudes;

		lcts_solicitudes = new ArrayList<TipoSolicitud>();

		if(
		    StringUtils.isValidString(as_idTipoDocumento) && StringUtils.isValidString(as_numeroDocumento)
			    && StringUtils.isValidString(as_servicio) && (ad_fechaDesde != null) && (ad_fechaHasta != null)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_SOLICITUDES_PAGADAS_SOLICITANTE);

				lps_ps.setString(li_column++, as_idTipoDocumento);
				lps_ps.setString(li_column++, as_numeroDocumento);
				lps_ps.setString(
				    li_column++,
				    StringUtils.getString(ad_fechaDesde, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO)
				);
				lps_ps.setString(
				    li_column++,
				    StringUtils.getString(ad_fechaHasta, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO)
				);
				lps_ps.setString(li_column++, as_servicio);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcts_solicitudes.add(getSolicitudPagada(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findSolicitudesPagadas", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcts_solicitudes))
			lcts_solicitudes = null;

		return lcts_solicitudes;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idTipoDocumento correspondiente al valor del tipo de objeto String
	 * @param as_numeroDocumento correspondiente al valor del tipo de objeto String
	 * @param as_nir correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoTurno
	 */
	public Collection<TipoTurno> findSolicitudesTurnoPagados(
	    String as_idTipoDocumento, String as_numeroDocumento, String as_nir
	)
	    throws B2BException
	{
		Collection<TipoTurno> lcts_solicitudes;

		lcts_solicitudes = new ArrayList<TipoTurno>();

		if(
		    StringUtils.isValidString(as_idTipoDocumento) && StringUtils.isValidString(as_numeroDocumento)
			    && StringUtils.isValidString(as_nir)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_SOLICITUDES_TURNOS_PAGADOS_SOLICITANTE);

				lps_ps.setString(li_column++, as_idTipoDocumento);
				lps_ps.setString(li_column++, as_numeroDocumento);
				lps_ps.setString(li_column++, as_nir);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcts_solicitudes.add(getSolicitudPagadaTurno(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findSolicitudesTurnoPagados", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcts_solicitudes))
			lcts_solicitudes = null;

		return lcts_solicitudes;
	}

	/**
	 * Método encargado de insertar o actualizar la tabla SDB_ACC_SOLICITUD.
	 *
	 * @param as_parametros objeto de tipo <Solicitud> que contiene los datos para ejecutar la sentancia.
	 * @param ab_query variable de tipo <boolean> donde TRUE inserta, FALSE actualiza
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Solicitud as_parametros, boolean ab_query)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int    li_column;
				String ls_entidadExenta;

				li_column            = 1;
				lps_ps               = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
				ls_entidadExenta     = as_parametros.getEntidadExenta();

				if(ab_query)
					lps_ps.setString(li_column++, as_parametros.getIdSolicitud());

				lps_ps.setString(li_column++, as_parametros.getIdProceso());
				lps_ps.setString(li_column++, as_parametros.getIdSubproceso());
				setDate(lps_ps, as_parametros.getFechaSolicitud(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getIdPersona());
				lps_ps.setString(li_column++, as_parametros.getIdDocumento());

				if(!StringUtils.isValidString(ls_entidadExenta))
					ls_entidadExenta = "N";

				lps_ps.setString(li_column++, ls_entidadExenta);
				lps_ps.setString(li_column++, as_parametros.getAnioAnt());
				lps_ps.setString(li_column++, as_parametros.getIdCirculoAnt());
				lps_ps.setString(li_column++, as_parametros.getIdProcesoAnt());
				lps_ps.setString(li_column++, as_parametros.getIdTurnoAnt());
				lps_ps.setString(li_column++, as_parametros.getUsuarioAnt());
				lps_ps.setString(li_column++, as_parametros.getComentario());
				lps_ps.setString(li_column++, as_parametros.getDerechoPeticion());
				lps_ps.setString(li_column++, as_parametros.getDescripcion());
				lps_ps.setString(li_column++, as_parametros.getIdTipoRecepcion());
				lps_ps.setString(li_column++, as_parametros.getIdDatosAntSistema());
				lps_ps.setString(li_column++, as_parametros.getIdTestamento());
				lps_ps.setString(li_column++, as_parametros.getIdCalidadSolicitante());
				lps_ps.setString(li_column++, as_parametros.getObservacionesRestitucion());
				setDouble(lps_ps, as_parametros.getCantidadMatriculas(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getParticipaInterviniente());
				setLong(lps_ps, as_parametros.getEstadoSolicitud(), li_column++);
				setDate(lps_ps, as_parametros.getFechaUltimoEstado(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getRadicadoPQRS());
				setDate(lps_ps, as_parametros.getFechaRadicadoPQRS(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getIdAutorizacionComunicacion());
				lps_ps.setString(li_column++, as_parametros.getIdAutorizacionNotificacion());
				lps_ps.setString(li_column++, as_parametros.getIdDireccion());
				lps_ps.setString(li_column++, as_parametros.getIdTelefono());
				lps_ps.setString(li_column++, as_parametros.getIdCorreoElectronico());
				lps_ps.setString(li_column++, as_parametros.getIdDireccionComunicacion());
				lps_ps.setString(li_column++, as_parametros.getIdTelefonoComunicacion());
				lps_ps.setString(li_column++, as_parametros.getIdCorreoElectronicoComunicacion());
				lps_ps.setString(li_column++, as_parametros.getNir());
				setLong(lps_ps, as_parametros.getIdentificadorCopiasSE(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getUsuarioOrip());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, as_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, as_parametros.getIpModificacion());
				}
				else
				{
					lps_ps.setString(li_column++, as_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, as_parametros.getIpCreacion());
				}

				lps_ps.setString(li_column++, as_parametros.getDigitalizada());
				setLong(lps_ps, as_parametros.getVersionDocumento(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getIdProcedencia());
				setLong(lps_ps, as_parametros.getIdEtapaActual(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getIdPersonaEntrega());
				lps_ps.setString(li_column++, as_parametros.getTurnoReproduccion());
				lps_ps.setString(li_column++, as_parametros.getIdEntidadExterna());

				//lps_ps.setString(li_column++, as_parametros.getReproduccionTestamento());
				if(ab_query)
				{
					lps_ps.setString(li_column++, as_parametros.getMotivoConsulta());
					lps_ps.setString(li_column++, as_parametros.getReferenciaMotivoConsulta());
					lps_ps.setString(li_column++, as_parametros.getTipoRegistroCertificado());
					lps_ps.setString(li_column++, as_parametros.getIdCuentaCupo());
					lps_ps.setBigDecimal(li_column++, as_parametros.getValorRecarga());
				}
				else
				{
					lps_ps.setString(li_column++, as_parametros.getTipoRegistroCertificado());
					lps_ps.setString(li_column++, as_parametros.getIdSolicitud());
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
	 * Método encargado de actualizar ID_DATOS_ANT_SISTEMA de la solicitud a la tabla SDB_ACC_SOLICITUD para un id_soliciutud determinado.
	 *
	 * @param as_parametros objeto de tipo <Solicitud> que contiene los datos para ejecutar la sentencia.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updataDatosAntSistema(Solicitud as_parametros)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ID_DATOS_ANT_SISTEMA);

				lps_ps.setString(li_column++, as_parametros.getIdDatosAntSistema());
				lps_ps.setString(li_column++, as_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, as_parametros.getIpModificacion());
				lps_ps.setString(li_column++, as_parametros.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updataDatosAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar la solicitud de la tabla SDB_ACC_SOLICITUD para un id_solicitud determinado.
	 *
	 * @param as_parametros objeto de tipo <Solicitud> que contiene los datos para ejecutar la sentancia.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(Solicitud as_parametros)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_REGISTRO);

				lps_ps.setLong(li_column++, NumericUtils.getLong(as_parametros.getVersionDocumentoInicial()));
				lps_ps.setString(li_column++, as_parametros.getIdProceso());
				lps_ps.setString(li_column++, as_parametros.getIdSubproceso());
				setDate(lps_ps, as_parametros.getFechaSolicitud(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getIdPersona());
				lps_ps.setString(li_column++, as_parametros.getIdDocumento());
				lps_ps.setString(li_column++, as_parametros.getEntidadExenta());
				lps_ps.setString(li_column++, as_parametros.getAnioAnt());
				lps_ps.setString(li_column++, as_parametros.getIdCirculoAnt());
				lps_ps.setString(li_column++, as_parametros.getIdProcesoAnt());
				lps_ps.setString(li_column++, as_parametros.getIdTurnoAnt());
				lps_ps.setString(li_column++, as_parametros.getUsuarioAnt());
				lps_ps.setString(li_column++, as_parametros.getComentario());
				lps_ps.setString(li_column++, as_parametros.getDerechoPeticion());
				lps_ps.setString(li_column++, as_parametros.getDescripcion());
				lps_ps.setString(li_column++, as_parametros.getIdTipoRecepcion());
				lps_ps.setString(li_column++, as_parametros.getIdDatosAntSistema());
				lps_ps.setString(li_column++, as_parametros.getIdTestamento());
				lps_ps.setString(li_column++, as_parametros.getIdCalidadSolicitante());
				lps_ps.setString(li_column++, as_parametros.getObservacionesRestitucion());
				setDouble(lps_ps, as_parametros.getCantidadMatriculas(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getParticipaInterviniente());
				setLong(lps_ps, as_parametros.getEstadoSolicitud(), li_column++);
				setDate(lps_ps, as_parametros.getFechaUltimoEstado(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getRadicadoPQRS());
				setDate(lps_ps, as_parametros.getFechaRadicadoPQRS(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getIdAutorizacionComunicacion());
				lps_ps.setString(li_column++, as_parametros.getIdAutorizacionNotificacion());
				lps_ps.setString(li_column++, as_parametros.getIdDireccion());
				lps_ps.setString(li_column++, as_parametros.getIdTelefono());
				lps_ps.setString(li_column++, as_parametros.getIdCorreoElectronico());
				lps_ps.setString(li_column++, as_parametros.getIdDireccionComunicacion());
				lps_ps.setString(li_column++, as_parametros.getIdTelefonoComunicacion());
				lps_ps.setString(li_column++, as_parametros.getIdCorreoElectronicoComunicacion());
				lps_ps.setString(li_column++, as_parametros.getNir());

				lps_ps.setString(li_column++, as_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, as_parametros.getIpModificacion());

				lps_ps.setString(li_column++, as_parametros.getDigitalizada());
				setLong(lps_ps, as_parametros.getVersionDocumento(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getIdProcedencia());
				setLong(lps_ps, as_parametros.getIdEtapaActual(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getIdPersonaEntrega());
				lps_ps.setString(li_column++, as_parametros.getTurnoReproduccion());

				lps_ps.setString(li_column++, as_parametros.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "Update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar la descripcion de solicitud a la tabla SDB_ACC_SOLICITUD para un id_solicitud determinado.
	 *
	 * @param as_parametros objeto de tipo <Solicitud> que contiene los datos para ejecutar la sentencia.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateDescripcionSolicitud(Solicitud as_parametros)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_DESCRIPCION);

				lps_ps.setString(li_column++, as_parametros.getDescripcion());
				lps_ps.setString(li_column++, as_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, as_parametros.getIpModificacion());
				lps_ps.setString(li_column++, as_parametros.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateDescripcionSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el campo DIGITALIZADA de la tabla SDB_ACC_SOLICITUD para un id_solicitud determinado.
	 *
	 * @param as_parametros Argumento de tipo <code>Solicitud</code> que contiene los criterios para realizar la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateDigitalizadaSolicitud(Solicitud as_parametros)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_DIGITALIZADA);

				lps_ps.setString(li_column++, as_parametros.getDigitalizada());
				lps_ps.setString(li_column++, as_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, as_parametros.getIpModificacion());
				lps_ps.setString(li_column++, as_parametros.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateDigitalizadaSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualizar medio notificar solicitud.
	 *
	 * @param as_medioNotificar correspondiente al valor de medio notificar
	 * @param as_idSolicitud correspondiente al valor de id solicitud
	 * @param as_userId correspondiente al valor de user id
	 * @param as_ipRemote correspondiente al valor de ip remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateMedioNotificarSolicitud(
	    String as_medioNotificar, String as_idSolicitud, String as_userId, String as_ipRemote
	)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if((as_medioNotificar != null) && (as_idSolicitud != null))
		{
			try
			{
				StringBuilder lsb_sb;
				int           li_count;

				lsb_sb       = new StringBuilder(cs_UPDATE_SET);
				li_count     = 1;

				lsb_sb.append(" ID_AUTORIZACION_NOTIFICACION = ?,");
				lsb_sb.append(cs_UPDATE_COMPLEMENT);

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_medioNotificar);
				lps_ps.setString(li_count++, as_userId);
				lps_ps.setString(li_count++, as_ipRemote);
				lps_ps.setString(li_count++, as_idSolicitud);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateMedioNotificarSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el número de copias de la tabla SDB_ACC_SOLICITUD para un id_solicitud determinado.
	 *
	 * @param as_parametros Argumento de tipo <code>Solicitud</code> que contiene los datos para ejecutar la sentancia.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateNumeroCopias(Solicitud as_parametros)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_NUMERO_COPIAS);

				setLong(lps_ps, as_parametros.getNumeroCopias(), li_column++);
				lps_ps.setString(li_column++, as_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, as_parametros.getIpModificacion());
				lps_ps.setString(li_column++, as_parametros.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateNumeroCopias", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar los datos de una persona para la solicitud de la tabla SDB_ACC_SOLICITUD.
	 *
	 * @param as_solicitud objeto de tipo <Solicitud> que contiene los datos para ejecutar la sentencia.
	 * @param ai_identificador variable de tipo <int> que parametriza sentencia a ejecutar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateSolicitud(Solicitud as_solicitud, int ai_identificador)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if((as_solicitud != null) && (ai_identificador > NumericUtils.DEFAULT_INT_VALUE))
		{
			try
			{
				StringBuilder lsb_sb;
				int           li_count;

				lsb_sb       = new StringBuilder(cs_UPDATE_SET);
				li_count     = 1;

				if(ai_identificador == 1)
					lsb_sb.append(
					    " ID_PERSONA = ?, ID_DIRECCION = ?, ID_TELEFONO = ?, ID_CORREO_ELECTRONICO = ?, ID_CALIDAD_SOLICITANTE = ?, "
					);
				else if(ai_identificador == 2)
					lsb_sb.append(" ID_DIRECCION = ?, ");
				else if(ai_identificador == 3)
					lsb_sb.append(" ID_TELEFONO = ?, ");
				else
					lsb_sb.append(" ID_CORREO_ELECTRONICO = ?, ");

				lsb_sb.append(cs_UPDATE_COMPLEMENT);

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(ai_identificador == 1)
				{
					lps_ps.setString(li_count++, as_solicitud.getIdPersona());
					lps_ps.setString(li_count++, as_solicitud.getIdDireccion());
					lps_ps.setString(li_count++, as_solicitud.getIdTelefono());
					lps_ps.setString(li_count++, as_solicitud.getIdCorreoElectronico());
					lps_ps.setString(li_count++, as_solicitud.getIdCalidadSolicitante());
				}
				else if(ai_identificador == 2)
					lps_ps.setString(li_count++, as_solicitud.getIdDireccion());
				else if(ai_identificador == 3)
					lps_ps.setString(li_count++, as_solicitud.getIdTelefono());
				else
					lps_ps.setString(li_count++, as_solicitud.getIdCorreoElectronico());

				lps_ps.setString(li_count++, as_solicitud.getIdUsuarioModificacion());
				lps_ps.setString(li_count++, as_solicitud.getIpModificacion());
				lps_ps.setString(li_count++, as_solicitud.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el subproceso de la solicitud a la tabla SDB_ACC_SOLICITUD para un id_solicitud determinado.
	 *
	 * @param as_parametros objeto de tipo <Solicitud> que contiene los datos para ejecutar la sentencia.
	 * @param ab_b variable de tipo <boolean> que parametriza la sentencia a ejecutar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateSubprocesoSolicitud(Solicitud as_parametros, boolean ab_b)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int    li_column;
				String ls_query;
				li_column     = 1;
				ls_query      = cs_UPDATE_SUBPROCESO;

				if(!ab_b)
					ls_query = cs_UPDATE_SOLICITUD_RECEPCION;

				lps_ps = getConnection().prepareStatement(ls_query);

				if(!ab_b)
				{
					lps_ps.setString(li_column++, as_parametros.getIdProceso());
					lps_ps.setString(li_column++, as_parametros.getIdTurnoAnt());
					lps_ps.setString(li_column++, as_parametros.getIdDocumento());
				}

				lps_ps.setString(li_column++, as_parametros.getIdSubproceso());
				lps_ps.setString(li_column++, as_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, as_parametros.getIpModificacion());
				lps_ps.setString(li_column++, as_parametros.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateSubprocesoSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el subproceso de la solicitud a la tabla SDB_ACC_SOLICITUD para un id_solicitud determinado.
	 *
	 * @param as_idProceso tipo string para filtrar en la tabla
	 * @param as_idSubProceso tipo string para filtrar en la tabla
	 * @param as_idSolicitud tipo string para filtrar en la tabla
	 * @param as_userId tipo string para filtrar en la tabla
	 * @param as_ipRemote
	 * @throws B2BException
	 */
	public void updateVersionSubproceso(
	    String as_idProceso, String as_idSubProceso, String as_idSolicitud, String as_userId, String as_ipRemote
	)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_UPDATE_VERSION);

			lps_ps.setInt(li_column++, findMaxVersion(as_idProceso, as_idSubProceso));
			lps_ps.setString(li_column++, as_userId);
			lps_ps.setString(li_column++, as_ipRemote);
			lps_ps.setString(li_column++, as_idSolicitud);
			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "updateVersionSubproceso", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Metodo para obtener objeto de tipo <Solicitud>.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return <Solicitud> instanciado con todos los registros encontrados.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Solicitud getSolicitud(ResultSet ars_rs)
	    throws SQLException
	{
		Solicitud ls_solicitud;

		ls_solicitud = new Solicitud();

		ls_solicitud.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		ls_solicitud.setIdProceso(ars_rs.getString("ID_PROCESO"));
		ls_solicitud.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		ls_solicitud.setFechaSolicitud(ars_rs.getTimestamp("FECHA_SOLICITUD"));
		ls_solicitud.setIdPersona(ars_rs.getString("ID_PERSONA"));
		ls_solicitud.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		ls_solicitud.setEntidadExenta(ars_rs.getString("ENTIDAD_EXENTA"));
		ls_solicitud.setAnioAnt(ars_rs.getString("ANIO_ANT"));
		ls_solicitud.setIdCirculoAnt(ars_rs.getString("ID_CIRCULO_ANT"));
		ls_solicitud.setIdProcesoAnt(ars_rs.getString("ID_PROCESO_ANT"));
		ls_solicitud.setIdTurnoAnt(ars_rs.getString("ID_TURNO_ANT"));
		ls_solicitud.setUsuarioAnt(ars_rs.getString("USUARIO_ANT"));
		ls_solicitud.setDerechoPeticion(ars_rs.getString("DERECHO_PETICION"));
		ls_solicitud.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ls_solicitud.setIdTipoRecepcion(ars_rs.getString("ID_TIPO_RECEPCION"));
		ls_solicitud.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		ls_solicitud.setIdTestamento(ars_rs.getString("ID_TESTAMENTO"));
		ls_solicitud.setIdCalidadSolicitante(ars_rs.getString("ID_CALIDAD_SOLICITANTE"));
		ls_solicitud.setObservacionesRestitucion(ars_rs.getString("OBSERVACIONES_RESTITUCION"));
		ls_solicitud.setCantidadMatriculas(getDouble(ars_rs, "CANTIDAD_MATRICULAS"));
		ls_solicitud.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaUltimoEstado(ars_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setParticipaInterviniente(ars_rs.getString("PARTICIPA_INTERVINIENTE"));
		ls_solicitud.setEstadoSolicitud(getLong(ars_rs, "ID_ESTADO_SOLICITUD"));
		ls_solicitud.setFechaUltimoEstado(ars_rs.getTimestamp("FECHA_ULTIMO_ESTADO"));
		ls_solicitud.setRadicadoPQRS(ars_rs.getString("RADICADO_PQRS"));
		ls_solicitud.setFechaUltimoEstado(ars_rs.getTimestamp("FECHA_RADICADO_PQRS"));
		ls_solicitud.setIdAutorizacionComunicacion(ars_rs.getString("ID_AUTORIZACION_COMUNICACION"));
		ls_solicitud.setIdAutorizacionNotificacion(ars_rs.getString("ID_AUTORIZACION_NOTIFICACION"));
		ls_solicitud.setIdDireccion(ars_rs.getString("ID_DIRECCION"));
		ls_solicitud.setIdTelefono(ars_rs.getString("ID_TELEFONO"));
		ls_solicitud.setIdCorreoElectronico(ars_rs.getString("ID_CORREO_ELECTRONICO"));
		ls_solicitud.setNir(ars_rs.getString("NIR"));
		ls_solicitud.setIdentificadorCopiasSE(getLong(ars_rs, "IDENTIFICADOR_COPIAS_SE"));
		ls_solicitud.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ls_solicitud.setDigitalizada(ars_rs.getString("DIGITALIZADA"));
		ls_solicitud.setVersionDocumento(getLong(ars_rs, "VERSION_DOCUMENTO"));
		ls_solicitud.setIdProcedencia(ars_rs.getString("ID_PROCEDENCIA"));
		ls_solicitud.setIdEtapaActual(getLong(ars_rs, "ID_ETAPA_ACTUAL"));
		ls_solicitud.setIdPersonaEntrega(ars_rs.getString("ID_PERSONA_ENTREGA"));
		ls_solicitud.setComentario(ars_rs.getString("COMENTARIO"));
		ls_solicitud.setTurnoReproduccion(ars_rs.getString("ID_TURNO_REPRODUCCION"));
		ls_solicitud.setIdCorreoElectronicoComunicacion(ars_rs.getString("ID_CORREO_ELECTRONICO_COMUNICACION"));
		ls_solicitud.setIdTelefonoComunicacion(ars_rs.getString("ID_TELEFONO_COMUNICACION"));
		ls_solicitud.setIdDireccionComunicacion(ars_rs.getString("ID_DIRECCION_COMUNICACION"));
		ls_solicitud.setVersionDocumentoInicial(getLong(ars_rs, "VERSION_DOCUMENTO_INICIAL"));
		ls_solicitud.setTipoRegistroCertificado(ars_rs.getString("TIPO_REGISTRO_CERTIFICADO"));
		ls_solicitud.setVersionSubProceso(getInteger(ars_rs, "VERSION_SUBPROCESO"));
		ls_solicitud.setReproduccionTestamento(ars_rs.getString("REPRODUCCION_TESTAMENTO"));
		ls_solicitud.setNumeroCopias(getLong(ars_rs, "NUMERO_COPIAS"));
		ls_solicitud.setUsuarioOrip(ars_rs.getString("USUARIO_ORIP"));

		return ls_solicitud;
	}

	/**
	 * Retorna el valor de solicitud pagada.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de solicitud pagada
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoSolicitud getSolicitudPagada(ResultSet ars_rs)
	    throws SQLException
	{
		TipoSolicitud lts_solicitud;

		lts_solicitud = new TipoSolicitud();

		lts_solicitud.setNir(ars_rs.getString("NIR"));
		lts_solicitud.setDescripcionServicio(ars_rs.getString("PROCESO"));
		lts_solicitud.setDescripcionSubServicio(ars_rs.getString("SUBPROCESO"));

		{
			lts_solicitud.setFechaServicio(
			    StringUtils.getString(
			        ars_rs.getTimestamp("FECHA_MAX"), FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO
			    )
			);
		}

		return lts_solicitud;
	}

	/**
	 * Retorna el valor de solicitud pagada turno.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de solicitud pagada turno
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoTurno getSolicitudPagadaTurno(ResultSet ars_rs)
	    throws SQLException
	{
		TipoTurno lts_solicitud;

		lts_solicitud = new TipoTurno();

		lts_solicitud.setNir(ars_rs.getString("NIR"));
		lts_solicitud.setDescripcionServicio(ars_rs.getString("PROCESO"));
		lts_solicitud.setDescricionSubServicio(ars_rs.getString("SUBPROCESO"));
		lts_solicitud.setFechaServicio(
		    StringUtils.getString(
		        ars_rs.getTimestamp("FECHA_MAX"), FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO
		    )
		);
		lts_solicitud.setTurno(ars_rs.getString("ID_TURNO"));

		return lts_solicitud;
	}
}
