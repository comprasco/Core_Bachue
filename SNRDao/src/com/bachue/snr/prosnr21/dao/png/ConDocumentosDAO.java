package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.ConDocumentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_DOCUMENTO.
 *
 * @author Gabriel Arias
 */
public class ConDocumentosDAO extends AuditoriaDao
{
	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_CON_DOCUMENTO_ID_DOCUMENTO_SALIDA.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_CON_DOCUMENTO(ID_DOCUMENTO_SALIDA,ID_PARTIDA_A,ID_ARCHIVO,ID_IMAGEN,DESTINATARIO,DIRECCION,ID_PAIS,ID_DEPARTAMENTO,ID_MUNICIPIO, OBSERVACIONES,REFERENCIA_SALIDA,TIPO_ARCHIVO,ID_TIPO_DOCUMENTAL,TIPO,NUMERO_RESOL_ASOCIADA,FECHA_RESOL_ASOCIADA,ESTADO, GENERADO,ID_ECM,FECHA_RADICACION_ECM,FECHA_CREACION,ID_USUARIO_CREACION,IP_CREACION,DOCUMENTO_AUTOMATICO,CORREO_ELECTRONICO, TELEFONO,ID_NOMBRE_DOCUMENTO,REPOSITORIO,SALIDA_ASOCIADA,FECHA_ENVIO,INTENTO_ENVIO,FECHA_INTENTO_ENVIO, ESTADO_IMPRESION,NUMERO_COPIAS,CONSECUTIVO_RESOLUCION,FECHA_RESOLUCION,CLASIFICACION,ID_MENSAJE, FECHA_GUIA,NUMERO_GUIA,FECHA_PUBLICACION_AVISO_WEB,FECHA_DESFIJACION_AVISO_WEB,FECHA_NOTIFICACION,FECHA_RESPUESTA_MENSAJE, CONSECUTIVO_OFICIO,FECHA_CONSECUTIVO_OFICIO,TIPO_NOTIFICACION,ID_PERSONA_NOTIFICACION,FECHA_ENVIO_COMPONENTE, FECHA_ACUSE_RECIBO_COMPONENTE,MEDIO_PUBLICACION,FECHA_RECIBO_AVISO_WEB,RESPONSABLE_PUBLICACION_WEB) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_CON_DOCUMENTO SET ID_PARTIDA_A =?,ID_ARCHIVO =?,ID_IMAGEN =?,DESTINATARIO =?,DIRECCION =?,ID_PAIS =?,ID_DEPARTAMENTO =?,ID_MUNICIPIO =?,OBSERVACIONES =?,REFERENCIA_SALIDA =?,TIPO_ARCHIVO =?,ID_TIPO_DOCUMENTAL =?,TIPO =?,NUMERO_RESOL_ASOCIADA =?,FECHA_RESOL_ASOCIADA =?,ESTADO =?,GENERADO =?,ID_ECM =?,FECHA_RADICACION_ECM =?,FECHA_MODIFICACION = SYSTIMESTAMP,ID_USUARIO_MODIFICACION =?,IP_MODIFICACION =?,DOCUMENTO_AUTOMATICO =?,CORREO_ELECTRONICO =?,TELEFONO =?,ID_NOMBRE_DOCUMENTO =?,REPOSITORIO =?,SALIDA_ASOCIADA =?,ELIMINAR_ENVIO_OWCC =?,FECHA_ENVIO =?,INTENTO_ENVIO =?,FECHA_INTENTO_ENVIO =?,DOCUMENTO_FINAL =?,ESTADO_IMPRESION =?,NUMERO_COPIAS =?,REIMPRESION =?,CONSECUTIVO_RESOLUCION =?,FECHA_RESOLUCION =?,CLASIFICACION =?,ID_MENSAJE =?,FECHA_GUIA =?,NUMERO_GUIA =?,FECHA_PUBLICACION_AVISO_WEB =?,FECHA_DESFIJACION_AVISO_WEB =?,FECHA_NOTIFICACION =?,FECHA_RESPUESTA_MENSAJE =?,CONSECUTIVO_OFICIO =?,FECHA_CONSECUTIVO_OFICIO =?,TIPO_NOTIFICACION =?,ID_PERSONA_NOTIFICACION =?,FECHA_ENVIO_COMPONENTE =?,FECHA_ACUSE_RECIBO_COMPONENTE =?,MEDIO_PUBLICACION =?,FECHA_RECIBO_AVISO_WEB =?,RESPONSABLE_PUBLICACION_WEB =? WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_FIND_DOCUMENTS_ECM. */
	private static final String cs_FIND_DOCUMENTS_ECM = "SELECT CD.* FROM SDB_CON_DOCUMENTO CD INNER JOIN SDB_CON_IMAGENES I ON (I.ID_IMAGEN = CD.ID_IMAGEN) AND dbms_lob.getlength(I.IMAGEN_BLOB) > 0 WHERE CD.ID_ECM IS NULL AND CD.ID_NOMBRE_DOCUMENTO IS NULL AND CD.FECHA_ENVIO IS NULL AND NVL(CD.INTENTO_ENVIO,0) < (SELECT ENTERO FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE = '"
		+ ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_REINTENTOS + "') ORDER BY CD.FECHA_CREACION ASC";

	/** Constante cs_ACTUALIZAR_ENVIO_EXITOSO. */
	private static final String cs_ACTUALIZAR_ENVIO_EXITOSO = "UPDATE SDB_CON_DOCUMENTO SET ID_ECM = ?, "
		+ "ID_NOMBRE_DOCUMENTO = ?, FECHA_ENVIO = SYSTIMESTAMP, FECHA_RADICACION_ECM = SYSTIMESTAMP, ESTADO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_ACTUALIZAR_ENVIO_FALLIDO. */
	private static final String cs_ACTUALIZAR_ENVIO_FALLIDO = "UPDATE SDB_CON_DOCUMENTO SET INTENTO_ENVIO = ?, "
		+ "FECHA_INTENTO_ENVIO = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/**
	 * Metodo encargado de realizar la actualizacion de un registro en la tabla SDB_CON_DOCUMENTO cuando se realiza un envio exitoso al OWCC.
	 *
	 * @param apd_parametros Documento contenedor de datos para utilizar en la actualizacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarEnvioExitoso(ConDocumentos apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_ACTUALIZAR_ENVIO_EXITOSO);

				lps_ps.setString(li_column++, apd_parametros.getIdEcm());
				lps_ps.setString(li_column++, apd_parametros.getIdNombreDocumento());
				lps_ps.setString(li_column++, apd_parametros.getEstado());
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());

				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarEnvioExitoso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de realizar la actualizacion de un registro en la tabla SDB_CON_DOCUMENTO cuando se realiza un envio fallido al OWCC.
	 *
	 * @param apd_parametros Documento contenedor de datos para utilizar en la actualizacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarEnvioFallido(ConDocumentos apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_ACTUALIZAR_ENVIO_FALLIDO);

				lps_ps.setLong(li_column++, apd_parametros.getIntentoEnvio());
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());

				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarEnvioFallido", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Find documents ecm.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConDocumentos> findDocumentsEcm()
	    throws B2BException
	{
		Collection<ConDocumentos> lccd_collectionResult;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lccd_collectionResult     = new ArrayList<ConDocumentos>();
		lps_ps                    = null;
		lrs_rs                    = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_DOCUMENTS_ECM);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccd_collectionResult.add(getObjectResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDocumentsEcm", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lccd_collectionResult.isEmpty())
			lccd_collectionResult = null;

		return lccd_collectionResult;
	}

	/**
	 * Metodo encargado de realizar la insercion o actualizacion de un registro en la tabla SDB_ACC_DOCUMENTOS_SALIDA.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en insetcion o actualizacion
	 * @param ab_query flag que indica si se actualiza o se inserta
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see long
	 */
	public long insertOrUpdate(ConDocumentos ads_parametros, boolean ab_query)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(ads_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;

				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				if(ab_query)
				{
					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						ll_secuencia = lrs_rs.getLong(1);

						lps_ps.setLong(li_column++, ll_secuencia);
					}
				}

				lps_ps.setString(li_column++, ads_parametros.getIdPartidaA());
				lps_ps.setString(li_column++, ads_parametros.getIdArchivo());
				setLong(lps_ps, ads_parametros.getIdImagen(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getDestinatario());
				lps_ps.setString(li_column++, ads_parametros.getDireccion());
				lps_ps.setString(li_column++, ads_parametros.getIdPais());
				lps_ps.setString(li_column++, ads_parametros.getIdDepartamento());
				lps_ps.setString(li_column++, ads_parametros.getIdMunicipio());
				lps_ps.setString(li_column++, ads_parametros.getObservaciones());
				lps_ps.setString(li_column++, ads_parametros.getReferenciaSalida());
				lps_ps.setString(li_column++, ads_parametros.getTipoArchivo());
				lps_ps.setString(li_column++, ads_parametros.getIdTipoDocumental());
				lps_ps.setString(li_column++, ads_parametros.getTipo());
				setLong(lps_ps, ads_parametros.getNumeroResolAsociada(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaResolAsociada(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getEstado());
				lps_ps.setString(li_column++, ads_parametros.getGenerado());
				lps_ps.setString(li_column++, ads_parametros.getIdEcm());
				setDate(lps_ps, ads_parametros.getFechaRadicacionEcm(), li_column++);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ads_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ads_parametros.getIpCreacion());
					lps_ps.setString(li_column++, ads_parametros.getDocumentoAutomatico());
				}
				else
				{
					lps_ps.setString(li_column++, ads_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ads_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ads_parametros.getDocumentoAutomatico());
				}

				lps_ps.setString(li_column++, ads_parametros.getCorreoElectronico());
				lps_ps.setString(li_column++, ads_parametros.getTelefono());
				lps_ps.setString(li_column++, ads_parametros.getIdNombreDocumento());

				String ls_repositorio;
				ls_repositorio = ads_parametros.getRepositorio();

				if(StringUtils.isValidString(ls_repositorio))
					lps_ps.setString(li_column++, ls_repositorio);
				else
					lps_ps.setString(li_column++, RepositorioCommon.TEMPORAL);

				lps_ps.setString(li_column++, ads_parametros.getSalidaAsociada());
//				lps_ps.setString(li_column++, ads_parametros.getEliminarEnvioOwcc());
				setDate(lps_ps, ads_parametros.getFechaEnvio(), li_column++);
				lps_ps.setLong(li_column++, ads_parametros.getIntentoEnvio());
				setDate(lps_ps, ads_parametros.getFechaIntentoEnvio(), li_column++);
//				lps_ps.setString(li_column++, ads_parametros.getDocumentoFinal());
				lps_ps.setString(li_column++, ads_parametros.getEstadoImpresion());
				lps_ps.setLong(li_column++, ads_parametros.getNumeroCopias());
//				lps_ps.setLong(li_column++, ads_parametros.getReimpresion());
				setLong(lps_ps, ads_parametros.getConsecutivoResolucion(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaResolucion(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getClasificacion());
				lps_ps.setString(li_column++, ads_parametros.getIdMensaje());
				setDate(lps_ps, ads_parametros.getFechaGuia(), li_column++);
				lps_ps.setLong(li_column++, ads_parametros.getNumeroGuia());
				setDate(lps_ps, ads_parametros.getFechaPublicacionAvisoWeb(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaDesfijacionAvisoWeb(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaNotificacion(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaRespuestaMensaje(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getConsecutivoOficio());
				setDate(lps_ps, ads_parametros.getFechaConsecutivoOficio(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getTipoNotificacion());
				lps_ps.setString(li_column++, ads_parametros.getIdPersonaNotificacion());
				setDate(lps_ps, ads_parametros.getFechaEnvioComponente(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaAcuseReciboComponente(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getMedioPublicacion());
				setDate(lps_ps, ads_parametros.getFechaReciboAvisoWeb(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getResponsablePublicacionWeb());

				if(!ab_query)
					lps_ps.setLong(li_column++, ads_parametros.getIdDocumentoSalida());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return ll_secuencia;
	}

	/**
	 * Retorna Objeto o variable de valor object result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ConDocumentos getObjectResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ConDocumentos lds_conDocumentos;

		lds_conDocumentos = new ConDocumentos();

		lds_conDocumentos.setIdDocumentoSalida(ars_rs.getLong("ID_DOCUMENTO_SALIDA"));
		lds_conDocumentos.setIdPartidaA(ars_rs.getString("ID_PARTIDA_A"));
		lds_conDocumentos.setIdArchivo(ars_rs.getString("ID_ARCHIVO"));
		lds_conDocumentos.setIdImagen(getLong(ars_rs, "ID_IMAGEN"));
		lds_conDocumentos.setDestinatario(ars_rs.getString("DESTINATARIO"));
		lds_conDocumentos.setDireccion(ars_rs.getString("DIRECCION"));
		lds_conDocumentos.setIdPais(ars_rs.getString("ID_PAIS"));
		lds_conDocumentos.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lds_conDocumentos.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lds_conDocumentos.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lds_conDocumentos.setReferenciaSalida(ars_rs.getString("REFERENCIA_SALIDA"));
		lds_conDocumentos.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
		lds_conDocumentos.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		lds_conDocumentos.setTipo(ars_rs.getString("TIPO"));
		lds_conDocumentos.setNumeroResolAsociada(getLong(ars_rs, "NUMERO_RESOL_ASOCIADA"));
		lds_conDocumentos.setFechaResolAsociada(ars_rs.getDate("FECHA_RESOL_ASOCIADA"));
		lds_conDocumentos.setEstado(ars_rs.getString("ESTADO"));
		lds_conDocumentos.setGenerado(ars_rs.getString("GENERADO"));
		lds_conDocumentos.setIdEcm(ars_rs.getString("ID_ECM"));
		lds_conDocumentos.setFechaRadicacionEcm(ars_rs.getDate("FECHA_RADICACION_ECM"));
		lds_conDocumentos.setDocumentoAutomatico(ars_rs.getString("DOCUMENTO_AUTOMATICO"));
		lds_conDocumentos.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));
		lds_conDocumentos.setTelefono(ars_rs.getString("TELEFONO"));
		lds_conDocumentos.setIdNombreDocumento(ars_rs.getString("ID_NOMBRE_DOCUMENTO"));
		lds_conDocumentos.setRepositorio(ars_rs.getString("REPOSITORIO"));
		lds_conDocumentos.setSalidaAsociada(ars_rs.getString("SALIDA_ASOCIADA"));
		lds_conDocumentos.setEliminarEnvioOwcc(ars_rs.getString("ELIMINAR_ENVIO_OWCC"));
		lds_conDocumentos.setFechaEnvio(ars_rs.getDate("FECHA_ENVIO"));
		lds_conDocumentos.setIntentoEnvio(ars_rs.getLong("INTENTO_ENVIO"));
		lds_conDocumentos.setFechaIntentoEnvio(ars_rs.getDate("FECHA_INTENTO_ENVIO"));
		lds_conDocumentos.setDocumentoFinal(ars_rs.getString("DOCUMENTO_FINAL"));
		lds_conDocumentos.setEstadoImpresion(ars_rs.getString("ESTADO_IMPRESION"));
		lds_conDocumentos.setNumeroCopias(ars_rs.getLong("NUMERO_COPIAS"));
		lds_conDocumentos.setReimpresion(ars_rs.getLong("REIMPRESION"));
		lds_conDocumentos.setConsecutivoResolucion(getLong(ars_rs, "CONSECUTIVO_RESOLUCION"));
		lds_conDocumentos.setFechaResolucion(ars_rs.getDate("FECHA_RESOLUCION"));
		lds_conDocumentos.setClasificacion(ars_rs.getString("CLASIFICACION"));
		lds_conDocumentos.setIdMensaje(ars_rs.getString("ID_MENSAJE"));
		lds_conDocumentos.setFechaGuia(ars_rs.getDate("FECHA_GUIA"));
		lds_conDocumentos.setNumeroGuia(ars_rs.getLong("NUMERO_GUIA"));
		lds_conDocumentos.setFechaPublicacionAvisoWeb(ars_rs.getDate("FECHA_PUBLICACION_AVISO_WEB"));
		lds_conDocumentos.setFechaDesfijacionAvisoWeb(ars_rs.getDate("FECHA_DESFIJACION_AVISO_WEB"));
		lds_conDocumentos.setFechaNotificacion(ars_rs.getDate("FECHA_NOTIFICACION"));
		lds_conDocumentos.setFechaRespuestaMensaje(ars_rs.getDate("FECHA_RESPUESTA_MENSAJE"));
		lds_conDocumentos.setConsecutivoOficio(ars_rs.getString("CONSECUTIVO_OFICIO"));
		lds_conDocumentos.setFechaConsecutivoOficio(ars_rs.getDate("FECHA_CONSECUTIVO_OFICIO"));
		lds_conDocumentos.setTipoNotificacion(ars_rs.getString("TIPO_NOTIFICACION"));
		lds_conDocumentos.setIdPersonaNotificacion(ars_rs.getString("ID_PERSONA_NOTIFICACION"));
		lds_conDocumentos.setFechaEnvioComponente(ars_rs.getDate("FECHA_ENVIO_COMPONENTE"));
		lds_conDocumentos.setFechaAcuseReciboComponente(ars_rs.getDate("FECHA_ACUSE_RECIBO_COMPONENTE"));
		lds_conDocumentos.setMedioPublicacion(ars_rs.getString("MEDIO_PUBLICACION"));
		lds_conDocumentos.setFechaReciboAvisoWeb(ars_rs.getDate("FECHA_RECIBO_AVISO_WEB"));
		lds_conDocumentos.setResponsablePublicacionWeb(ars_rs.getString("RESPONSABLE_PUBLICACION_WEB"));

		fillAuditoria(ars_rs, lds_conDocumentos);

		return lds_conDocumentos;
	}
}
