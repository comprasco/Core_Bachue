package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.registro.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TIPO_ACTO
 *
 * @author mblanco
 */
public class TipoActoDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL_TIPO_ACTO. */
	private static final String cs_FIND_ALL_TIPO_ACTO = "SELECT * FROM SDB_PGN_TIPO_ACTO";

	/** Constante cs_FIND_ALL_TIPO_ACTO_APLICA_DESBORDE. */
	private static final String cs_FIND_ALL_TIPO_ACTO_APLICA_DESBORDE = "SELECT * FROM SDB_PGN_TIPO_ACTO WHERE APLICA_DESBORDE = 'S' ";

	/** Constante cs_FIND_TIPO_ACTO_BY_ID. */
	private static final String cs_FIND_TIPO_ACTO_BY_ID = "SELECT * FROM SDB_PGN_TIPO_ACTO WHERE ID_TIPO_ACTO = ?";

	/** Constante cs_FIND_TIPO_ACTO_BY_LINEA_PRODUCCION. */
	private static final String cs_FIND_TIPO_ACTO_BY_LINEA_PRODUCCION = "SELECT * FROM SDB_PGN_TIPO_ACTO WHERE LINEA_PRODUCCION = ? ";

	/** Constante cs_FIND_TIPO_ACTO_BY_ID_MAX_VERSION. */
	private static final String cs_FIND_TIPO_ACTO_BY_ID_MAX_VERSION = "SELECT MAX(VERSION) FROM SDB_PGN_TIPO_ACTO WHERE ID_TIPO_ACTO = ?";

	/** Constante cs_FIND_BY_SOLICITUD_DEVOLUCION_DINERO. */
	private static final String cs_FIND_BY_SOLICITUD_DEVOLUCION_DINERO = "SELECT * FROM SDB_PGN_TIPO_ACTO SPTA INNER JOIN SDB_ACC_ACTO SAA ON SAA.ID_TIPO_ACTO = SPTA.ID_TIPO_ACTO AND SAA.VERSION = SPTA.VERSION INNER JOIN SDB_ACC_ACTOS_DEVOLUCION_DINERO SAADD ON SAADD.ID_ACTO_DEVOLUCION_DINERO = SAA.ID_ACTO AND SAADD.VERSION_ACTO = SAA.VERSION INNER JOIN SDB_ACC_SOLICITUD SAS ON SAS.ID_SOLICITUD = SAADD.ID_SOLICITUD WHERE SAS.ID_SOLICITUD = ? ";

	/** Constante cs_INSERT_TIPO_ACTO. */
	private static final String cs_INSERT_TIPO_ACTO = "INSERT INTO SDB_PGN_TIPO_ACTO"
		+ "(ID_TIPO_ACTO, VERSION, NOMBRE, ACTO_SIN_CUANTIA, TARIFA_EXENTA, REQUIERE_CUANTIA, AVALUO, REQUIERE_MATRICULA, VALIDAR_AREA, REQUIERE_TIPO_ADQUISICION, "
		+ "GENERA_SEGREGACION, APERTURA_MATRICULA, INSCRIPCION_ADICIONAL, CERTIFICADO_OBLIGATORIO, IMPUESTO_REGISTRO, APLICA_TARIFA_RETENCION_DOCUMENTAL, "
		+ "LINEA_PRODUCCION, APLICA_DESBORDE, SUMATORIA_COEFICIENTE, SUMATORIA_AREA, ID_TARIFA_FIJA, ID_ETAPA_INICIAL, HABILITA_SECUENCIA, CAMBIO_TIPO_CUANTIA, "
		+ "ACTIVO, ALERTA_TITULAR, CUOTAPARTE_DONACION, PRESTACIONES_PERIODICAS, FECHA_CREACION, ID_USUARIO_CREACION, IP_CREACION)"
		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_UPDATE_TIPO_ACTO. */
	private static final String cs_UPDATE_TIPO_ACTO = "UPDATE SDB_PGN_TIPO_ACTO"
		+ " SET NOMBRE=?, ACTO_SIN_CUANTIA=?, TARIFA_EXENTA=?, REQUIERE_CUANTIA=?, AVALUO=?, REQUIERE_MATRICULA=?, VALIDAR_AREA=?, REQUIERE_TIPO_ADQUISICION=?, GENERA_SEGREGACION=?, APERTURA_MATRICULA=?, INSCRIPCION_ADICIONAL=?, CERTIFICADO_OBLIGATORIO=?, IMPUESTO_REGISTRO=?, APLICA_TARIFA_RETENCION_DOCUMENTAL=? , LINEA_PRODUCCION=?, APLICA_DESBORDE=?, SUMATORIA_COEFICIENTE=?, SUMATORIA_AREA=?, ID_TARIFA_FIJA=?, ID_ETAPA_INICIAL=?, HABILITA_SECUENCIA=?, CAMBIO_TIPO_CUANTIA=?,  ACTIVO=?, ALERTA_TITULAR=?,  CUOTAPARTE_DONACION=?, PRESTACIONES_PERIODICAS=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?"
		+ " WHERE ID_TIPO_ACTO=? AND VERSION=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TIPO_ACTO, VERSION, NOMBRE, ACTO_SIN_CUANTIA, TARIFA_EXENTA, REQUIERE_CUANTIA, AVALUO, REQUIERE_MATRICULA, VALIDAR_AREA, REQUIERE_TIPO_ADQUISICION, GENERA_SEGREGACION, APERTURA_MATRICULA, INSCRIPCION_ADICIONAL, CERTIFICADO_OBLIGATORIO, IMPUESTO_REGISTRO, APLICA_TARIFA_RETENCION_DOCUMENTAL, LINEA_PRODUCCION, APLICA_DESBORDE, ACTIVO, FECHA_CREACION, ID_USUARIO_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HABILITA_SECUENCIA, CAMBIO_TIPO_CUANTIA, SUMATORIA_COEFICIENTE, SUMATORIA_AREA, ID_TARIFA_FIJA, ID_ETAPA_INICIAL, ALERTA_TITULAR, CUOTAPARTE_DONACION, PRESTACIONES_PERIODICAS  "
		+ "FROM SDB_PGN_TIPO_ACTO WHERE ID_TIPO_ACTO=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID_IMPUESTO_REGISTRO_S = "SELECT * FROM SDB_PGN_TIPO_ACTO WHERE ID_TIPO_ACTO = ? AND IMPUESTO_REGISTRO = 'S'";

	/** Constante cs_FIND_ALL_BY_ACTOS. */
	private static final String cs_FIND_ALL_BY_ACTOS = "SELECT ID_TIPO_ACTO, VERSION, NOMBRE, ACTO_SIN_CUANTIA, TARIFA_EXENTA, REQUIERE_CUANTIA, AVALUO, REQUIERE_MATRICULA, VALIDAR_AREA, REQUIERE_TIPO_ADQUISICION, GENERA_SEGREGACION, APERTURA_MATRICULA, INSCRIPCION_ADICIONAL, CERTIFICADO_OBLIGATORIO, IMPUESTO_REGISTRO, APLICA_TARIFA_RETENCION_DOCUMENTAL, LINEA_PRODUCCION, APLICA_DESBORDE, ACTIVO, FECHA_CREACION, ID_USUARIO_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HABILITA_SECUENCIA, CAMBIO_TIPO_CUANTIA, SUMATORIA_COEFICIENTE, SUMATORIA_AREA, ID_TARIFA_FIJA, ID_ETAPA_INICIAL, ALERTA_TITULAR, CUOTAPARTE_DONACION, PRESTACIONES_PERIODICAS "
		+ "FROM SDB_PGN_TIPO_ACTO WHERE ACTIVO = 'S' ";

	/** Constante cs_FIND_ALL_BY_ACTOS_4_Y_5_DIGITOS. */
	private static final String cs_FIND_ALL_BY_ACTOS_4_Y_5_DIGITOS = "SELECT ID_TIPO_ACTO, VERSION, NOMBRE, ACTO_SIN_CUANTIA, TARIFA_EXENTA, REQUIERE_CUANTIA, AVALUO, REQUIERE_MATRICULA, VALIDAR_AREA, REQUIERE_TIPO_ADQUISICION, GENERA_SEGREGACION, APERTURA_MATRICULA, INSCRIPCION_ADICIONAL, CERTIFICADO_OBLIGATORIO, IMPUESTO_REGISTRO, APLICA_TARIFA_RETENCION_DOCUMENTAL, LINEA_PRODUCCION, APLICA_DESBORDE, ACTIVO, FECHA_CREACION, ID_USUARIO_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HABILITA_SECUENCIA, CAMBIO_TIPO_CUANTIA, SUMATORIA_COEFICIENTE, SUMATORIA_AREA, ID_TARIFA_FIJA, ID_ETAPA_INICIAL, ALERTA_TITULAR, CUOTAPARTE_DONACION, PRESTACIONES_PERIODICAS "
		+ "FROM SDB_PGN_TIPO_ACTO WHERE ACTIVO = 'S' AND LENGTH(ID_TIPO_ACTO)=4 OR LENGTH(ID_TIPO_ACTO)=5 ";

	/** Constante cs_ACTOS_DETAILS. */
	private static final String cs_ACTOS_DETAILS = "SELECT ID_TIPO_ACTO, VERSION, NOMBRE, ACTO_SIN_CUANTIA, TARIFA_EXENTA, REQUIERE_CUANTIA, AVALUO, REQUIERE_MATRICULA, VALIDAR_AREA, REQUIERE_TIPO_ADQUISICION, GENERA_SEGREGACION, APERTURA_MATRICULA, INSCRIPCION_ADICIONAL, CERTIFICADO_OBLIGATORIO, IMPUESTO_REGISTRO, APLICA_TARIFA_RETENCION_DOCUMENTAL, LINEA_PRODUCCION, APLICA_DESBORDE, ACTIVO, FECHA_CREACION, ID_USUARIO_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HABILITA_SECUENCIA, CAMBIO_TIPO_CUANTIA, SUMATORIA_COEFICIENTE, SUMATORIA_AREA, ID_TARIFA_FIJA, ID_ETAPA_INICIAL, ALERTA_TITULAR, CUOTAPARTE_DONACION, PRESTACIONES_PERIODICAS "
		+ "FROM SDB_PGN_TIPO_ACTO WHERE ID_TIPO_ACTO IN (19,99,0903,0913,0362,0369,0315) ORDER BY NOMBRE";

	/** Constante cs_ACTOS_DETAILS_BY_ID_TIPO_ACTO. */
	private static final String cs_ACTOS_DETAILS_BY_ID_TIPO_ACTO = "SELECT ID_TIPO_ACTO, VERSION, NOMBRE, ACTO_SIN_CUANTIA, TARIFA_EXENTA, REQUIERE_CUANTIA, AVALUO, REQUIERE_MATRICULA, VALIDAR_AREA, REQUIERE_TIPO_ADQUISICION, GENERA_SEGREGACION, APERTURA_MATRICULA, INSCRIPCION_ADICIONAL, CERTIFICADO_OBLIGATORIO, IMPUESTO_REGISTRO, APLICA_TARIFA_RETENCION_DOCUMENTAL, LINEA_PRODUCCION, APLICA_DESBORDE, ACTIVO, FECHA_CREACION, ID_USUARIO_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HABILITA_SECUENCIA, CAMBIO_TIPO_CUANTIA, SUMATORIA_COEFICIENTE, SUMATORIA_AREA, ID_TARIFA_FIJA, ID_ETAPA_INICIAL, ALERTA_TITULAR, CUOTAPARTE_DONACION, PRESTACIONES_PERIODICAS "
		+ "FROM SDB_PGN_TIPO_ACTO WHERE ID_TIPO_ACTO = ?";

	/** Constante cs_FIND_TIPO_ADQUI. */
	private static final String cs_FIND_TIPO_ADQUI = " SELECT ID_TIPO_ADQUISICION , NOMBRE from SDB_ACC_TIPO_ADQUISICION ORDER BY NOMBRE";

	/** Constante cs_FIND_TIPOS_DOCUMENTALES. */
	private static final String cs_FIND_TIPOS_DOCUMENTALES = " SELECT * FROM (SELECT td.nombre ,tda.OBLIGATORIO ,tda.ID_TIPO_DOCUMENTAL, tda.ID_TIPO_DOCUMENTAL_ACTO "
		+ " FROM  SDB_PGN_TIPO_DOCUMENTAL_ACTO tda, SDB_PGN_TIPO_DOCUMENTAL td   WHERE tda.ID_TIPO_ACTO = ?  AND tda.ID_TIPO_DOCUMENTAL = td.ID_TIPO_DOCUMENTAL AND tda.ACTIVO = 'S'"
		+ "	UNION ALL SELECT TD.NOMBRE,ACD.OBLIGATORIO,ACD.ID_TIPO_DOCUMENTAL,tda.ID_TIPO_DOCUMENTAL_ACTO  FROM SDB_ACC_COMPLETITUD_DOCUMENTAL  ACD ,SDB_PGN_TIPO_DOCUMENTAL TD "
		+ "	INNER JOIN SDB_PGN_TIPO_DOCUMENTAL_ACTO tda ON tda.ID_TIPO_DOCUMENTAL = td.ID_TIPO_DOCUMENTAL "
		+ "	WHERE ACD.ID_ACTO = ? AND ACD.ID_TIPO_ACTO = ? AND ACD.ID_SOLICITUD = ? AND ACD.ID_TIPO_DOCUMENTAL = TD.ID_TIPO_DOCUMENTAL) "
		+ "	order by ID_TIPO_DOCUMENTAL_ACTO ASC";

	/** Constante cs_FIND_TIPOS_DOCUMENTALES_COMPLETITUD. */
	private static final String cs_FIND_TIPOS_DOCUMENTALES_COMPLETITUD = "   SELECT ACD.NUMERO_PAGO, ACD.OBLIGATORIO_TIPO_DOCUMENTAL, "
		+ "ACD.OBSERVACIONES, TD.NOMBRE, ACD.OBLIGATORIO, ACD.ID_TIPO_DOCUMENTAL, ACD.MEDIO_RECEPCION "
		+ "FROM SDB_ACC_COMPLETITUD_DOCUMENTAL  ACD ,SDB_PGN_TIPO_DOCUMENTAL TD"
		+ " WHERE ACD.ID_ACTO = ? AND ACD.ID_TIPO_ACTO = ? AND ACD.ID_SOLICITUD = ?  AND ACD.ID_TIPO_DOCUMENTAL = TD.ID_TIPO_DOCUMENTAL";

	/** Constante cs_FIND_SECUENCE_ACC_ACTO. */
	private static final String cs_FIND_SECUENCE_ACC_ACTO = "  SELECT SEC_ACC_ACTO_ID_ACTO.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_ACTO. */
	private static final String cs_FIND_ACTO = "SELECT COUNT(*) TOTAL FROM SDB_ACC_ACTO WHERE ID_ACTO = ? AND ID_TIPO_ACTO = ?  AND ID_SOLICITUD = ? ";

	/** Constante cs_FIND_SECUENCE_COMPLETITUD. */
	private static final String cs_FIND_SECUENCE_COMPLETITUD = "  SELECT SEC_ACC_COMPLETITUD_DOCUMENTAL_ID_COMPLETITUD.NEXTVAL FROM DUAL";

	/** Constante cs_SAVE_COMPLETITUD_TIPOSDOC. */
	private static final String cs_SAVE_COMPLETITUD_TIPOSDOC = "  INSERT INTO SDB_ACC_COMPLETITUD_DOCUMENTAL (ID_TIPO_ACTO,ID_ACTO,ID_SOLICITUD,ID_COMPLETITUD,ID_USUARIO_CREACION,OBSERVACIONES,MEDIO_RECEPCION,OBLIGATORIO,FECHA_CREACION,ID_TIPO_DOCUMENTAL,OBLIGATORIO_TIPO_DOCUMENTAL)"
		+ "VALUES  ( ?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_FIND_TIPOS_DOCUMENTALES_ALL. */
	private static final String cs_FIND_TIPOS_DOCUMENTALES_ALL = "  SELECT ID_TIPO_DOCUMENTAL, NOMBRE FROM  SDB_PGN_TIPO_DOCUMENTAL ORDER BY NOMBRE";

	/** Constante cs_DELETE_ACTOS. */
	private static final String cs_DELETE_ACTOS = "  DELETE FROM SDB_ACC_ACTO where ID_SOLICITUD = ?  AND ID_TIPO_ACTO = ? AND ID_ACTO = ? ";

	/** Constante cs_VALIDATE_TIPOADQUI. */
	private static final String cs_VALIDATE_TIPOADQUI = " SELECT COUNT(*) TOTAL FROM SDB_PNG_NATURALEZA_JURIDICA WHERE  ID_DOMINIO_NAT_JUR = 'X' AND ID_NATURALEZA_JURIDICA = ?";

	/** Constante cs_FIND_BY_ID_ACTO. */
	private static final String cs_FIND_BY_ID_ACTO = "   SELECT  SAA.ID_ACTO ,SAA.ID_TIPO_ACTO, STA.NOMBRE AS ESPECIFICACION, SAA.ID_SOLICITUD,SAA.CUANTIA,SAA.VALOR_AVALUO, "
		+ " SAA.CANTIDAD_ACTOS,SAA.FECHA_VENCIMIENTO,SAA.ID_TIPO_ADQUISICION ,STA.ACTO_SIN_CUANTIA, SAA.ID_CIRCULO, SAA.REFERENCIA, SAA.NUMERO_PROCESO, SAA.ID_ACTO_PRINCIPAL,"
		+ " SAA.NUMERO_BOLETA_FISCAL_EXT,SAA.NUMERO_BOLETA_FISCAL,STA.SUMATORIA_AREA, SAA.PORCENTAJE_TRANSFERENCIA, SAA.AREA_TRANSFERIR, SAA.AREA_TOTAL_INMUEBLE, "
		+ " SAA.RESPUESTA_LEY1943, SAA.PERIODICIDAD_CUANTIA, SAA.TIEMPO_CANON, SAA.AREA_ACTO, SAA.AREA_TOTAL, SAA.GARANTIA_HIPOTECARIA, SAA.HIJUELA_PASIVO, "
		+ " SAA.ID_TIPO_TARIFA_REGISTRAL, SAA.VERSION, SAA.ORGANISMO_INTERNACIONAL, SAA.CANTIDAD_CERTIF_ANT_SISTEMA, SAA.MADRE_CABEZA, SAA.FECHA_PAGO_IMPUESTO, SAA.FECHA_PAGO_IMPUESTO_EXT "
		+ " FROM   SDB_ACC_ACTO SAA, SDB_PGN_TIPO_ACTO STA "
		+ " WHERE SAA.ID_ACTO = ?  AND STA.ID_TIPO_ACTO = SAA.ID_TIPO_ACTO  ";

	/** Constante cs_FIND_DETALLE_TIPO_DOCUMENTAL. */
	private static final String cs_FIND_DETALLE_TIPO_DOCUMENTAL = "  SELECT OBLIGATORIO FROM SDB_PGN_TIPO_DOCUMENTAL_ACTO WHERE ID_TIPO_ACTO = ? AND ID_TIPO_DOCUMENTAL_ACTO = ?";

	/** Constante cs_DELETE_TIPOS_DOCUMENTALES_ACTP. */
	private static final String cs_DELETE_TIPOS_DOCUMENTALES_ACTP = "  DELETE FROM SDB_ACC_COMPLETITUD_DOCUMENTAL where ID_SOLICITUD = ?  AND ID_ACTO = ?  AND ID_TIPO_ACTO = ?  ";

	/** Constante cs_TIPOS_DOC_COMPLETITUD. */
	private static final String cs_TIPOS_DOC_COMPLETITUD = "  SELECT ID_TIPO_DOCUMENTAL,OBLIGATORIO  FROM SDB_ACC_COMPLETITUD_DOCUMENTAL WHERE ID_ACTO = ?  AND ID_SOLICITUD =  ? AND ID_TIPO_ACTO = ? ";

	/** Constante cs_TIPOS_DOC_ACTO. */
	private static final String cs_TIPOS_DOC_ACTO = "  SELECT  ID_TIPO_DOCUMENTAL  FROM  SDB_PGN_TIPO_DOCUMENTAL_ACTO  WHERE ID_TIPO_ACTO =  ? ";

	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = "SELECT ATA.NOMBRE, SAA.ID_ACTO, STA.NOMBRE AS ESPECIFICACION, SAA.ID_TIPO_ACTO, SAA.ID_SOLICITUD, SAA.CUANTIA, SAA.VALOR_AVALUO, "
		+ "SAA.CANTIDAD_ACTOS, SAA.FECHA_VENCIMIENTO, SAA.ID_TIPO_ADQUISICION, SAA.ID_ACTO_PRINCIPAL, STA.ACTO_SIN_CUANTIA, SAA.ID_CIRCULO, SAA.REFERENCIA, SAA.NUMERO_PROCESO,"
		+ "SAA.NUMERO_BOLETA_FISCAL_EXT,SAA.NUMERO_BOLETA_FISCAL,STA.SUMATORIA_AREA, SAA.PORCENTAJE_TRANSFERENCIA, SAA.AREA_TRANSFERIR, SAA.AREA_TOTAL_INMUEBLE, "
		+ "SAA.RESPUESTA_LEY1943, SAA.PERIODICIDAD_CUANTIA, SAA.TIEMPO_CANON, SAA.AREA_ACTO, SAA.AREA_TOTAL, SAA.GARANTIA_HIPOTECARIA, SAA.HIJUELA_PASIVO, "
		+ "SAA.ID_TIPO_TARIFA_REGISTRAL, SAA.VERSION, SAA.ORGANISMO_INTERNACIONAL, SAA.CANTIDAD_CERTIF_ANT_SISTEMA, SAA.MADRE_CABEZA, SAA.FECHA_PAGO_IMPUESTO, SAA.FECHA_PAGO_IMPUESTO_EXT "
		+ "FROM SDB_ACC_ACTO SAA "
		+ "INNER JOIN SDB_ACC_TIPO_ADQUISICION ATA ON ATA.ID_TIPO_ADQUISICION = SAA.ID_TIPO_ADQUISICION "
		+ "INNER JOIN SDB_PGN_TIPO_ACTO STA ON STA.ID_TIPO_ACTO = SAA.ID_TIPO_ACTO "
		+ "WHERE SAA.ID_SOLICITUD = ?  AND STA.ID_TIPO_ACTO = SAA.ID_TIPO_ACTO";

	/** Constante cs_CONTEO_CODIGO_ACTO. */
	private static final String cs_CONTEO_CODIGO_ACTO = "SELECT COUNT(0) CANTIDAD FROM SDB_PGN_TIPO_ACTO_EJECUTORIA WHERE ID_TIPO_ACTO= ?";

	/**
	 * Retorna el valor del objeto de int con el conteo del codigo del acto
	 *
	 * @param as_codigoActo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int conteoTablaPorActo(String as_codigoActo)
	    throws B2BException
	{
		int               li_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_return     = 0;
		li_column     = 1;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_CONTEO_CODIGO_ACTO);
			lps_ps.setString(li_column++, as_codigoActo);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_return = lrs_rs.getInt("CANTIDAD");
		}
		catch(SQLException lse_e)
		{
			logError(this, "conteoTablaPorActo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_return;
	}

	/**
	 * Borra el acto.
	 *
	 * @param aa_param correspondiente al valor del tipo de objeto Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteActo(com.bachue.snr.prosnr01.model.registro.Acto aa_param)
	    throws B2BException
	{
		if(aa_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_ACTOS);

				lps_ps.setString(li_column++, aa_param.getIdSolicitud());
				lps_ps.setString(li_column++, aa_param.getCodigo());
				lps_ps.setString(li_column++, aa_param.getIdActoDb());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Borra acto completitud.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @param as_codigo correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteActoCompletitud(String as_idSolicitud, String as_idActo, String as_codigo)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		lps_ps = null;

		try
		{
			int li_column;

			li_column     = 1;
			lps_ps        = getConnection().prepareStatement(cs_DELETE_TIPOS_DOCUMENTALES_ACTP);

			lps_ps.setString(li_column++, as_idSolicitud);
			lps_ps.setString(li_column++, as_idActo);
			lps_ps.setString(li_column++, as_codigo);

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "deleteActoCompletitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoDocumental filtrado por tipos documentos completitud o tipos de acto.
	 *
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @param as_codigo correspondiente al valor del tipo de objeto String
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de TipoDocumental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDocumental
	 */
	public Collection<TipoDocumental> documentalesCompletitud(
	    String as_idActo, String as_codigo, String as_idSolicitud, boolean ab_b
	)
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_ctd;
		PreparedStatement          lps_ps;
		TipoDocumental             lotd_td;
		ResultSet                  lrs_rs;
		int                        li_column;

		lctd_ctd      = new ArrayList<TipoDocumental>();
		lps_ps        = null;
		lrs_rs        = null;
		li_column     = 1;

		try
		{
			if(ab_b)
			{
				lps_ps = getConnection().prepareStatement(cs_TIPOS_DOC_COMPLETITUD);
				lps_ps.setString(li_column++, as_idActo);
				lps_ps.setString(li_column++, as_idSolicitud);
				lps_ps.setString(li_column++, as_codigo);
			}
			else
			{
				lps_ps = getConnection().prepareStatement(cs_TIPOS_DOC_ACTO);
				lps_ps.setString(li_column++, as_codigo);
			}

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				lotd_td = new TipoDocumental();

				lotd_td.setIdTipoDocumental(lrs_rs.getString("ID_TIPO_DOCUMENTAL"));

				lctd_ctd.add(lotd_td);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "documentalesCompletitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lctd_ctd.isEmpty())
			lctd_ctd = null;

		return lctd_ctd;
	}

	/**
	 * Retorna el valor del objeto de int con el conteo de la secuencia, codigo, id solicitud.
	 *
	 * @param aa_acto correspondiente al valor del tipo de objeto Acto
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findActo(Acto aa_acto)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;
		int               lp_total;

		li_column     = 1;
		lp_total      = 0;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_ACTO);

			lps_ps.setString(li_column++, aa_acto.getSecuence());
			lps_ps.setString(li_column++, aa_acto.getCodigo());
			lps_ps.setString(li_column++, aa_acto.getIdSolicitud());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lp_total = lrs_rs.getInt("TOTAL");
		}
		catch(SQLException lse_e)
		{
			logError(this, "findActo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lp_total;
	}

	/**
	 * Retorna el valor del objeto de TipoActo.
	 *
	 * @param ata_tipoActo correspondiente al valor del tipo de objeto TipoActo
	 * @return devuelve el valor de TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public TipoActo findActoDetailsByTipoActo(TipoActo ata_tipoActo)
	    throws B2BException
	{
		TipoActo          lta_tipoActo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lta_tipoActo     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_ACTOS_DETAILS_BY_ID_TIPO_ACTO);

			lps_ps.setString(1, ata_tipoActo.getIdTipoActo());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lta_tipoActo = getAllTiposActo(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findActoDetailsByTipoActo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lta_tipoActo;
	}

	/**
	 * Metodo encargado de consultar los actos de un ID_SOLICITUD o ID_CIRCULO.
	 *
	 * @param as_idSolicitud Argumento de tipo String que contiene el ID_SOLICITUD a consultar.
	 * @return Collección de Actos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<Acto> findActosBySolicitud(String as_idSolicitud)
	    throws B2BException
	{
		return findActosBySolicitud(as_idSolicitud, null);
	}

	/**
	 * Metodo encargado de consultar los actos de un ID_SOLICITUD o ID_CIRCULO.
	 *
	 * @param as_idSolicitud Argumento de tipo String que contiene el ID_SOLICITUD a consultar.
	 * @param as_idCirculo Argumento de tipo String que contiene el ID_CIRCULO a consultar.
	 * @return Collección de Actos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public Collection<Acto> findActosBySolicitud(String as_idSolicitud, String as_idCirculo)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		Collection<Acto>  lca_actos;

		lca_actos     = new ArrayList<Acto>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			StringBuilder lsb_sb;
			int           li_column;
			boolean       lb_idCirculo;

			lsb_sb           = new StringBuilder(cs_FIND_BY_SOLICITUD);
			li_column        = 1;
			lb_idCirculo     = StringUtils.isValidString(as_idCirculo);

			if(lb_idCirculo)
				lsb_sb.append(" AND SAA.ID_CIRCULO = ?");

			lsb_sb.append(" ORDER BY SAA.ID_CIRCULO, SAA.ID_ACTO DESC ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_column++, as_idSolicitud);

			if(lb_idCirculo)
				lps_ps.setString(li_column++, as_idCirculo);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lca_actos.add(getObjetFromResultSetActo(lrs_rs, true));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findActosBySolicitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lca_actos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_orderBy correspondiente al valor del tipo de objeto boolean
	 * @param as_codigos correspondiente al valor del tipo de objeto String
	 * @param ab_registro correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public Collection<TipoActo> findAllActosActivos(boolean ab_orderBy, String as_codigos, boolean ab_registro)
	    throws B2BException
	{
		Collection<TipoActo> lcta_cta;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;
		StringBuilder        lsb_sb;

		lcta_cta     = new ArrayList<TipoActo>();
		lps_ps       = null;
		lrs_rs       = null;
		lsb_sb       = new StringBuilder(cs_FIND_ALL_BY_ACTOS);

		try
		{
			boolean lb_b;
			lb_b = StringUtils.isValidString(as_codigos);

			if(lb_b)
				lsb_sb.append(" AND ID_TIPO_ACTO IN (" + as_codigos + ") ");

			if(ab_registro)
				lsb_sb.append(" AND LENGTH(ID_TIPO_ACTO)= 4 OR LENGTH(ID_TIPO_ACTO)= 5 OR ID_TIPO_ACTO = '64'");

			if(ab_orderBy)
				lsb_sb.append(" ORDER BY TRIM(NOMBRE)");
			else
				lsb_sb.append(" ORDER BY TO_NUMBER(ID_TIPO_ACTO) ASC");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_cta.add(getAllActos(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActosActivos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcta_cta.isEmpty())
			lcta_cta = null;

		return lcta_cta;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoActo filtrado por actod de 4 y 5 digitos.
	 *
	 * @param ab_orderBy correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public Collection<TipoActo> findAllActosActivos4Y5Digitos(boolean ab_orderBy)
	    throws B2BException
	{
		Collection<TipoActo> lcta_cta;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;
		StringBuilder        lsb_sb;

		lcta_cta     = new ArrayList<TipoActo>();
		lps_ps       = null;
		lrs_rs       = null;
		lsb_sb       = new StringBuilder(cs_FIND_ALL_BY_ACTOS_4_Y_5_DIGITOS);

		try
		{
			if(ab_orderBy)
				lsb_sb = lsb_sb.append(" ORDER BY TRIM(NOMBRE)");
			else
				lsb_sb = lsb_sb.append(" ORDER BY TO_NUMBER(ID_TIPO_ACTO) ASC");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_cta.add(getAllTiposActo(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActosActivos4Y5Digitos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcta_cta.isEmpty())
			lcta_cta = null;

		return lcta_cta;
	}

	/**
	 * Retorna el valor del objeto de Map con todos los actos.
	 *
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	public Map<String, TipoActo> findAllActosActivosData()
	    throws B2BException
	{
		Map<String, TipoActo> lmsta_return;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;
		StringBuilder         lsb_sb;

		lmsta_return     = new HashMap<String, TipoActo>();
		lps_ps           = null;
		lrs_rs           = null;
		lsb_sb           = new StringBuilder(cs_FIND_ALL_BY_ACTOS);

		try
		{
			lsb_sb     = lsb_sb.append(" ORDER BY TRIM(NOMBRE)");
			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				TipoActo lta_tipoActo;

				lta_tipoActo = getAllTiposActo(lrs_rs);

				if(lta_tipoActo != null)
					lmsta_return.put(lta_tipoActo.getIdTipoActo(), lta_tipoActo);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActosActivosData", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lmsta_return.isEmpty())
			lmsta_return = null;

		return lmsta_return;
	}

	/**
	 * Busca todos lso tipos acto asociados a una solicitud de devolucion de dinero
	 *
	 * @param as_idSolicitud Id de la solicitud a utilizar como filtro en la busqueda
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base datos
	 */
	public Collection<TipoActo> findAllBySolicitudDevolucionDinero(String as_idSolicitud)
	    throws B2BException
	{
		Collection<TipoActo> lcta_cta;

		lcta_cta = new ArrayList<TipoActo>();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD_DEVOLUCION_DINERO);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcta_cta.add(getAllActos(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllBySolicitudDevolucionDinero", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcta_cta.isEmpty())
			lcta_cta = null;

		return lcta_cta;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoDocumental filtrado por tipos documentales completitud y tipos documentales.
	 *
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @param as_codigo correspondiente al valor del tipo de objeto String
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param ab_actos correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de TipoDocumental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDocumental
	 */
	public Collection<TipoDocumental> findAllDocumentales(
	    String as_idActo, String as_codigo, boolean ab_b, String as_idSolicitud, boolean ab_actos
	)
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_tipoDocumental;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;
		int                        li_column;
		boolean                    lb_tiposDocumentales;

		lctd_tipoDocumental      = new ArrayList<TipoDocumental>();
		lps_ps                   = null;
		lrs_rs                   = null;
		li_column                = 1;
		lb_tiposDocumentales     = false;

		try
		{
			if(ab_b)
			{
				if(ab_actos)
					lps_ps = getConnection().prepareStatement(cs_FIND_TIPOS_DOCUMENTALES_COMPLETITUD);
				else
				{
					lps_ps = getConnection().prepareStatement(cs_FIND_TIPOS_DOCUMENTALES);
					lps_ps.setString(li_column++, as_codigo);
					lb_tiposDocumentales = true;
				}

				lps_ps.setString(li_column++, as_idActo);
				lps_ps.setString(li_column++, as_codigo);
				lps_ps.setString(li_column++, as_idSolicitud);
			}
			else
				lps_ps = getConnection().prepareStatement(cs_FIND_TIPOS_DOCUMENTALES_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctd_tipoDocumental.add(getAllDocumentales(lrs_rs, ab_b, ab_actos, lb_tiposDocumentales));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllDocumentales", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lctd_tipoDocumental.isEmpty())
			lctd_tipoDocumental = null;

		return lctd_tipoDocumental;
	}

	/**
	 * Método para encontrar todos los tipos acto por un determinado filtro.
	 *
	 * @return Collection<TipoActo>
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<TipoActo> findAllTiposActo()
	    throws B2BException
	{
		Collection<TipoActo> lcta_return;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcta_return     = new ArrayList<TipoActo>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_TIPO_ACTO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_return.add(getAllTiposActo(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllTiposActo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcta_return.isEmpty())
			lcta_return = null;

		return lcta_return;
	}

	/**
	 * Método para encontrar todos los tipos acto por un determinado filtro.
	 *
	 * @return Collection<TipoActo>
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<TipoActo> findAllTiposActoAplicaDesborde()
	    throws B2BException
	{
		Collection<TipoActo> lcta_return;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcta_return     = new ArrayList<TipoActo>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_TIPO_ACTO_APLICA_DESBORDE);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_return.add(getAllTiposActo(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllTiposActoAplicaDesborde", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcta_return.isEmpty())
			lcta_return = null;

		return lcta_return;
	}

	/**
	 * Retorna el valor del objeto de TipoActo.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public TipoActo findById(String as_param)
	    throws B2BException
	{
		TipoActo lta_return;

		lta_return = null;

		if(StringUtils.isValidString(as_param))
		{
			lta_return = new TipoActo();

			lta_return.setIdTipoActo(as_param);

			lta_return = findById(lta_return);
		}

		return lta_return;
	}

	/**
	 * Método para encontrar el tipo acto por un determinado filtro.
	 *
	 * @param ata_param correspondiente al valor del tipo de objeto TipoActo
	 * @return TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public TipoActo findById(TipoActo ata_param)
	    throws B2BException
	{
		TipoActo          lta_ta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lta_ta     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, ata_param.getIdTipoActo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lta_ta = getAllTiposActo(lrs_rs);
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

		return lta_ta;
	}

	/**
	 * Retorna el valor del objeto de com.bachue.snr.prosnr01.model.registro.Acto
	 *
	 * @param as_idActoDb correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de com.bachue.snr.prosnr01.model.registro.Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr01.model.registro.Acto
	 */
	public com.bachue.snr.prosnr01.model.registro.Acto findByIdActo(String as_idActoDb)
	    throws B2BException
	{
		com.bachue.snr.prosnr01.model.registro.Acto la_acto;
		PreparedStatement                           lps_ps;
		ResultSet                                   lrs_rs;

		la_acto     = null;
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACTO);

			lps_ps.setString(1, as_idActoDb);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				la_acto = getObjetFromResultSetActo(lrs_rs, false);
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

		return la_acto;
	}

	/**
	 * Método para encontrar el tipo acto por un determinado filtro.
	 *
	 * @param as_param correspondiente al valor del id TipoActo
	 * @return TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public TipoActo findByIdAndIsImpuestoRegistro(String as_param)
	    throws B2BException
	{
		TipoActo          lta_ta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lta_ta     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_IMPUESTO_REGISTRO_S);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lta_ta = getAllTiposActo(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdAndIsImpuestoRegistro", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lta_ta;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoActo filtrado por los detalles de los actos.
	 *
	 * @return devuelve el valor de Collection de TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public Collection<TipoActo> findDetailsActos()
	    throws B2BException
	{
		Collection<TipoActo> lcta_cta;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcta_cta     = new ArrayList<TipoActo>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_ACTOS_DETAILS);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_cta.add(getAllTiposActo(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDetailsActos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcta_cta.isEmpty())
			lcta_cta = null;

		return lcta_cta;
	}

	/**
	 * Retorna el valor del objeto de int con el maximo de la version
	 *
	 * @param as_idTipoActo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findMaxVersion(String as_idTipoActo)
	    throws B2BException
	{
		int               li_secuencia;
		int               li_column;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		li_column        = 1;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_TIPO_ACTO_BY_ID_MAX_VERSION);

			lps_ps.setString(li_column++, as_idTipoActo);

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
	 * Encuentra obligatoriedad.
	 *
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @param as_IdTipoDoc correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean findObligatoriedad(String as_idActo, String as_IdTipoDoc)
	    throws B2BException
	{
		boolean           lb_b;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;
		lb_b          = false;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_DETALLE_TIPO_DOCUMENTAL);

			lps_ps.setString(li_column++, as_IdTipoDoc);
			lps_ps.setString(li_column++, as_idActo);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
			{
				String ls_s;
				ls_s = lrs_rs.getString("OBLIGATORIO");

				if(StringUtils.getStringNotNull(ls_s).equalsIgnoreCase(EstadoCommon.S))
					lb_b = true;
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findObligatoriedad", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lb_b;
	}

	/**
	 * Retorna el valor del objeto de int con la secuencia del acto.
	 *
	 * @return devuelve el valor de int
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
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE_ACC_ACTO);

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
	 * Retorna el valor del objeto de int con la secuencia de completitud.
	 *
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuenceCompletitud()
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
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE_COMPLETITUD);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuenceCompletitud", lse_e);

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
	 * Metodo sobrecargado para consultar por el String idTipoActo.
	 *
	 * @param ata_param correspondiente al valor del tipo de objeto TipoActo
	 * @return devuelve el valor de TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public TipoActo findTipoActoById(TipoActo ata_param)
	    throws B2BException
	{
		TipoActo ltp_return;

		ltp_return = null;

		if(ata_param != null)
			ltp_return = findTipoActoById(ata_param.getIdTipoActo());

		return ltp_return;
	}

	/**
	 * Método para encontrar el tipo acto por un determinado filtro.
	 *
	 * @param as_idTipoActo correspondiente al valor del tipo de objeto String
	 * @return TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public TipoActo findTipoActoById(String as_idTipoActo)
	    throws B2BException
	{
		TipoActo          lta_ta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lta_ta     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(StringUtils.isValidString(as_idTipoActo))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_TIPO_ACTO_BY_ID);

				lps_ps.setString(1, as_idTipoActo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lta_ta = getAllTiposActo(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findTipoActoById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lta_ta;
	}

	/**
	 * Método para encontrar el tipo acto por un determinado filtro.
	 *
	 * @param as_lineaProduccion correspondiente al valor del tipo de objeto String
	 * @return TipoActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public Collection<TipoActo> findTipoActoByLineaProduccion(String as_lineaProduccion)
	    throws B2BException
	{
		Collection<TipoActo> lcta_ta;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcta_ta     = new ArrayList<TipoActo>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			if(StringUtils.isValidString(as_lineaProduccion))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_TIPO_ACTO_BY_LINEA_PRODUCCION);

				lps_ps.setString(1, as_lineaProduccion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcta_ta.add(getAllTiposActo(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findTipoActoByLineaProduccion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcta_ta;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoAdquisicion filtrado por tipo adquisicion.
	 *
	 * @return devuelve el valor de Collection de TipoAdquisicion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoAdquisicion
	 */
	public Collection<TipoAdquisicion> findTipoAdqui()
	    throws B2BException
	{
		Collection<TipoAdquisicion> lcta_cta;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcta_cta     = new ArrayList<TipoAdquisicion>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_TIPO_ADQUI);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_cta.add(findTipoAdqui(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findTipoAdqui", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcta_cta.isEmpty())
			lcta_cta = null;

		return lcta_cta;
	}

	/**
	 * Guardar tipos documentales.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param atd_td correspondiente al valor del tipo de objeto TipoDocumental
	 * @param as_acto correspondiente al valor del tipo de objeto String
	 * @param as_codigo correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarTiposDocumentales(
	    String as_idSolicitud, TipoDocumental atd_td, String as_acto, String as_codigo
	)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;
		String            ls_seleccionado;

		lps_ps              = null;
		lrs_rs              = null;
		li_column           = 1;
		ls_seleccionado     = EstadoCommon.S;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_SAVE_COMPLETITUD_TIPOSDOC);
			lps_ps.setString(li_column++, as_codigo);
			lps_ps.setString(li_column++, as_acto);
			lps_ps.setString(li_column++, as_idSolicitud);
			lps_ps.setString(li_column++, atd_td.getSecuence());
			lps_ps.setString(li_column++, atd_td.getIdUsuarioCreacion());
			lps_ps.setString(li_column++, atd_td.getObservaciones());
			lps_ps.setString(li_column++, atd_td.getMedioRecepcion());

			if(!atd_td.isSeleccionado())
				ls_seleccionado = EstadoCommon.N;

			lps_ps.setString(li_column++, ls_seleccionado);
			lps_ps.setString(li_column++, atd_td.getIdTipoDocumental());

			if(StringUtils.isValidString(atd_td.getObligatorio()))
				lps_ps.setString(li_column++, atd_td.getObligatorio());
			else if(StringUtils.isValidString(atd_td.getObligatorioC()))
				lps_ps.setString(li_column++, atd_td.getObligatorioC());
			else
				lps_ps.setString(li_column++, EstadoCommon.N);

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "guardarTiposDocumentales", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}
	}

	/**
	 * Método para insertar o actualizar un registro en la BD.
	 *
	 * @param ata_param Registro a insertar o modificar
	 * @param ab_query booleana para saber si actualizar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(TipoActo ata_param, boolean ab_query)
	    throws B2BException
	{
		if(ata_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int    li_column;
				String ls_query;

				li_column     = 1;
				ls_query      = ab_query ? cs_INSERT_TIPO_ACTO : cs_UPDATE_TIPO_ACTO;

				lps_ps = getConnection().prepareStatement(ls_query);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ata_param.getIdTipoActo());
					lps_ps.setLong(li_column++, ata_param.getVersion());
				}

				lps_ps.setString(li_column++, ata_param.getNombre());
				lps_ps.setString(li_column++, ata_param.getActoSinCuantia());
				lps_ps.setString(li_column++, ata_param.getTarifaExenta());
				lps_ps.setString(li_column++, ata_param.getRequiereCuantia());
				lps_ps.setString(li_column++, ata_param.getAvaluo());
				lps_ps.setString(li_column++, ata_param.getRequiereMatricula());
				lps_ps.setString(li_column++, ata_param.getValidarAreaString());
				lps_ps.setString(li_column++, ata_param.getRequiereTipoAdquisicion());
				lps_ps.setString(li_column++, ata_param.getGeneraSegregacion());
				lps_ps.setString(li_column++, ata_param.getAperturaMatricula());
				lps_ps.setString(li_column++, ata_param.getInscripcionAdicional());
				lps_ps.setString(li_column++, ata_param.getCertificadoObligatorioString());
				lps_ps.setString(li_column++, ata_param.getImpuestoRegistro());
				lps_ps.setString(li_column++, ata_param.getAplicaTarifaRetencionDocumental());
				lps_ps.setString(li_column++, ata_param.getLineaProduccion());
				lps_ps.setString(li_column++, ata_param.getAplicaDesborde());
				lps_ps.setString(li_column++, ata_param.getSumatoriaCoeficiente());
				lps_ps.setString(li_column++, ata_param.getSumatoriaArea());
				lps_ps.setString(li_column++, ata_param.getIdTarifaFija());
				setLong(lps_ps, ata_param.getIdEtapaInicial(), li_column++);
				lps_ps.setString(li_column++, ata_param.getHabilitarSecuencia());
				lps_ps.setString(li_column++, ata_param.getCambioTipoCuantia());
				lps_ps.setString(li_column++, ata_param.getActivo());
				lps_ps.setString(li_column++, ata_param.getAlertaTitular());
				lps_ps.setString(li_column++, ata_param.getCuotaparteDonacionString());
				lps_ps.setString(li_column++, ata_param.getPrestacionesPeriodicasString());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ata_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ata_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ata_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ata_param.getIpModificacion());
					lps_ps.setString(li_column++, ata_param.getIdTipoActo());
					lps_ps.setLong(li_column++, ata_param.getVersion());
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
	 * Retorna el valor del objeto de int con el conteo de tipo de adquisicion.
	 *
	 * @param ls_idActo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int validateTipoAdqui(String ls_idActo)
	    throws B2BException
	{
		int               lp_total;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lp_total     = 0;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_VALIDATE_TIPOADQUI);

			lps_ps.setString(li_column++, ls_idActo);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lp_total = lrs_rs.getInt("TOTAL");
		}
		catch(SQLException lse_e)
		{
			logError(this, "validateTipoAdqui", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lp_total;
	}

	/**
	 * Retorna el valor de TipoActo
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoActo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoActo getAllActos(ResultSet ars_rs)
	    throws SQLException
	{
		TipoActo lta_tipoActo;

		lta_tipoActo = new TipoActo();

		lta_tipoActo.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lta_tipoActo.setVersion(ars_rs.getLong("VERSION"));
		lta_tipoActo.setNombre(ars_rs.getString("NOMBRE"));
		lta_tipoActo.setActoSinCuantia(ars_rs.getString("ACTO_SIN_CUANTIA"));
		lta_tipoActo.setTarifaExenta(ars_rs.getString("TARIFA_EXENTA"));
		lta_tipoActo.setRequiereCuantia(ars_rs.getString("REQUIERE_CUANTIA"));
		lta_tipoActo.setAvaluo(ars_rs.getString("AVALUO"));
		lta_tipoActo.setRequiereMatricula(ars_rs.getString("REQUIERE_MATRICULA"));
		lta_tipoActo.setValidarAreaString(ars_rs.getString("VALIDAR_AREA"));
		lta_tipoActo.setRequiereTipoAdquisicion(ars_rs.getString("REQUIERE_TIPO_ADQUISICION"));
		lta_tipoActo.setGeneraSegregacion(ars_rs.getString("GENERA_SEGREGACION"));
		lta_tipoActo.setAperturaMatricula(ars_rs.getString("APERTURA_MATRICULA"));
		lta_tipoActo.setInscripcionAdicional(ars_rs.getString("INSCRIPCION_ADICIONAL"));
		lta_tipoActo.setCertificadoObligatorioString(ars_rs.getString("CERTIFICADO_OBLIGATORIO"));
		lta_tipoActo.setImpuestoRegistro(ars_rs.getString("IMPUESTO_REGISTRO"));
		lta_tipoActo.setAplicaTarifaRetencionDocumental(ars_rs.getString("APLICA_TARIFA_RETENCION_DOCUMENTAL"));
		lta_tipoActo.setLineaProduccion(ars_rs.getString("LINEA_PRODUCCION"));
		lta_tipoActo.setAplicaDesborde(ars_rs.getString("APLICA_DESBORDE"));
		lta_tipoActo.setActivo(ars_rs.getString("ACTIVO"));
		lta_tipoActo.setAlertaTitular(ars_rs.getString("ALERTA_TITULAR"));
		lta_tipoActo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lta_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lta_tipoActo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lta_tipoActo.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lta_tipoActo.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lta_tipoActo.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lta_tipoActo.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lta_tipoActo.setHabilitarSecuencia(ars_rs.getString("HABILITA_SECUENCIA"));
		lta_tipoActo.setCambioTipoCuantia(ars_rs.getString("CAMBIO_TIPO_CUANTIA"));
		lta_tipoActo.setSumatoriaCoeficiente(ars_rs.getString("SUMATORIA_COEFICIENTE"));
		lta_tipoActo.setSumatoriaArea(ars_rs.getString("SUMATORIA_AREA"));
		lta_tipoActo.setIdTarifaFija(ars_rs.getString("ID_TARIFA_FIJA"));
		lta_tipoActo.setIdEtapaInicial(getLong(ars_rs, "ID_ETAPA_INICIAL"));

		{
			String ls_cuotaparteDonacion;

			ls_cuotaparteDonacion = ars_rs.getString("CUOTAPARTE_DONACION");

			lta_tipoActo.setCuotaparteDonacion(
			    StringUtils.isValidString(ls_cuotaparteDonacion)
				    && ls_cuotaparteDonacion.equalsIgnoreCase(EstadoCommon.S)
			);
		}

		{
			String ls_prestacionesPeriodicas;

			ls_prestacionesPeriodicas = ars_rs.getString("PRESTACIONES_PERIODICAS");

			lta_tipoActo.setPrestacionesPeriodicas(
			    StringUtils.isValidString(ls_prestacionesPeriodicas)
				    && ls_prestacionesPeriodicas.equalsIgnoreCase(EstadoCommon.S)
			);
		}

		return lta_tipoActo;
	}

	/**
	 * Retorna el valor de TipoDocumental
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param ab_actos correspondiente al valor del tipo de objeto boolean
	 * @param ab_tiposDocumentales correspondiente al valor del tipo de objeto boolean
	 * @return el valor de TipoDocumental
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoDocumental getAllDocumentales(
	    ResultSet ars_rs, boolean ab_b, boolean ab_actos, boolean ab_tiposDocumentales
	)
	    throws SQLException
	{
		TipoDocumental ltd_tipoDocumental;
		boolean        lb_b;
		String         ls_id;
		lb_b     = false;

		ltd_tipoDocumental = new TipoDocumental();

		ltd_tipoDocumental.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		ltd_tipoDocumental.setTipoDocumental(ars_rs.getString("NOMBRE"));

		if(ab_b)
		{
			ls_id = ltd_tipoDocumental.getIdTipoDocumental();

			if(StringUtils.isValidString(ls_id) && ls_id.equalsIgnoreCase("19"))
				ltd_tipoDocumental.setMostrarBoletaFiscal(true);
		}
		else
			lb_b = true;

		if(ab_actos)
		{
			if(ars_rs.getString("OBLIGATORIO").equals(EstadoCommon.S))
				ltd_tipoDocumental.setSeleccionado(true);

			ltd_tipoDocumental.setMedioRecepcion(ars_rs.getString("MEDIO_RECEPCION"));
			ltd_tipoDocumental.setObservaciones(ars_rs.getString("OBSERVACIONES"));
			ltd_tipoDocumental.setBoletaFiscal(ars_rs.getString("NUMERO_PAGO"));
			ltd_tipoDocumental.setObligatorioC(ars_rs.getString("OBLIGATORIO_TIPO_DOCUMENTAL"));
		}
		else
		{
			if(ab_tiposDocumentales)
				ltd_tipoDocumental.setObligatorio(ars_rs.getString("OBLIGATORIO"));

			ltd_tipoDocumental.setSeleccionado(lb_b);
		}

		ltd_tipoDocumental.setActiveTiposDoc(ab_b);

		return ltd_tipoDocumental;
	}

	/**
	 * Método para con el resultado de la BD, guardar en un objeto la información.
	 *
	 * @param ars_rs Consulta de la BD
	 * @return TipoActo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoActo getAllTiposActo(ResultSet ars_rs)
	    throws SQLException
	{
		TipoActo lta_tipoActo;

		lta_tipoActo = new TipoActo();
		lta_tipoActo.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lta_tipoActo.setVersion(ars_rs.getLong("VERSION"));
		lta_tipoActo.setNombre(ars_rs.getString("NOMBRE"));
		lta_tipoActo.setActoSinCuantia(ars_rs.getString("ACTO_SIN_CUANTIA"));
		lta_tipoActo.setTarifaExenta(ars_rs.getString("TARIFA_EXENTA"));
		lta_tipoActo.setRequiereCuantia(ars_rs.getString("REQUIERE_CUANTIA"));
		lta_tipoActo.setAvaluo(ars_rs.getString("AVALUO"));
		lta_tipoActo.setRequiereMatricula(ars_rs.getString("REQUIERE_MATRICULA"));
		lta_tipoActo.setValidarAreaString(ars_rs.getString("VALIDAR_AREA"));
		lta_tipoActo.setRequiereTipoAdquisicion(ars_rs.getString("REQUIERE_TIPO_ADQUISICION"));
		lta_tipoActo.setGeneraSegregacion(ars_rs.getString("GENERA_SEGREGACION"));
		lta_tipoActo.setAperturaMatricula(ars_rs.getString("APERTURA_MATRICULA"));
		lta_tipoActo.setInscripcionAdicional(ars_rs.getString("INSCRIPCION_ADICIONAL"));
		lta_tipoActo.setCertificadoObligatorioString(ars_rs.getString("CERTIFICADO_OBLIGATORIO"));
		lta_tipoActo.setImpuestoRegistro(ars_rs.getString("IMPUESTO_REGISTRO"));
		lta_tipoActo.setAplicaTarifaRetencionDocumental(ars_rs.getString("APLICA_TARIFA_RETENCION_DOCUMENTAL"));
		lta_tipoActo.setLineaProduccion(ars_rs.getString("LINEA_PRODUCCION"));
		lta_tipoActo.setAplicaDesborde(ars_rs.getString("APLICA_DESBORDE"));
		lta_tipoActo.setActivo(ars_rs.getString("ACTIVO"));
		lta_tipoActo.setAlertaTitular(ars_rs.getString("ALERTA_TITULAR"));
		lta_tipoActo.setCuotaparteDonacionString(ars_rs.getString("CUOTAPARTE_DONACION"));
		lta_tipoActo.setPrestacionesPeriodicasString(ars_rs.getString("PRESTACIONES_PERIODICAS"));
		lta_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lta_tipoActo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lta_tipoActo.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lta_tipoActo.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lta_tipoActo.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lta_tipoActo.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lta_tipoActo.setActoSelected(false);
		lta_tipoActo.setHabilitarSecuencia(ars_rs.getString("HABILITA_SECUENCIA"));
		lta_tipoActo.setCambioTipoCuantia(ars_rs.getString("CAMBIO_TIPO_CUANTIA"));
		lta_tipoActo.setSumatoriaCoeficiente(ars_rs.getString("SUMATORIA_COEFICIENTE"));
		lta_tipoActo.setSumatoriaArea(ars_rs.getString("SUMATORIA_AREA"));
		lta_tipoActo.setIdTarifaFija(ars_rs.getString("ID_TARIFA_FIJA"));
		lta_tipoActo.setIdEtapaInicial(getLong(ars_rs, "ID_ETAPA_INICIAL"));

		return lta_tipoActo;
	}

	/**
	 * Retorna el valor de Acto
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_val Argumento de tipo <code>boolean</code> que determina si se debe capturar el nombre del tipo de adquisición.
	 * @return el valor de objet from result set acto
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Acto
	 */
	private com.bachue.snr.prosnr01.model.registro.Acto getObjetFromResultSetActo(ResultSet ars_rs, boolean ab_val)
	    throws SQLException
	{
		com.bachue.snr.prosnr01.model.registro.Acto la_acto;

		la_acto = new com.bachue.snr.prosnr01.model.registro.Acto();

		la_acto.setIdActoDb(ars_rs.getString("ID_ACTO"));
		la_acto.setCodigo(ars_rs.getString("ID_TIPO_ACTO"));
		la_acto.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		la_acto.setCuantiaActo(ars_rs.getBigDecimal("CUANTIA"));
		la_acto.setValorAvaluo(ars_rs.getBigDecimal("VALOR_AVALUO"));
		la_acto.setCantidadActos(getInteger(ars_rs, "CANTIDAD_ACTOS"));
		la_acto.setFechaVencimiento(ars_rs.getDate("FECHA_VENCIMIENTO"));
		la_acto.setTipoAdquisicion(ars_rs.getString("ID_TIPO_ADQUISICION"));
		la_acto.setActoConCuantia(ars_rs.getString("ACTO_SIN_CUANTIA"));
		la_acto.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		la_acto.setReferencia(ars_rs.getString("REFERENCIA"));
		la_acto.setNumeroProceso(ars_rs.getString("NUMERO_PROCESO"));
		la_acto.setIdActoPrincipal(ars_rs.getString("ID_ACTO_PRINCIPAL"));
		la_acto.setImpuestoRegistroExtemporaneo(ars_rs.getString("NUMERO_BOLETA_FISCAL_EXT"));
		la_acto.setImpuestoRegistro(ars_rs.getString("NUMERO_BOLETA_FISCAL"));
		la_acto.setFechaPagoImpuestoExtemporaneo(ars_rs.getTimestamp("FECHA_PAGO_IMPUESTO_EXT"));
		la_acto.setFechaPagoImpuesto(ars_rs.getTimestamp("FECHA_PAGO_IMPUESTO"));
		la_acto.setSumatoriaArea(ars_rs.getString("SUMATORIA_AREA"));
		la_acto.setPorcentajeTransferencia(getDouble(ars_rs, "PORCENTAJE_TRANSFERENCIA"));
		la_acto.setAreaTransferir(getDouble(ars_rs, "AREA_TRANSFERIR"));
		la_acto.setAreaTotalInmueble(getDouble(ars_rs, "AREA_TOTAL_INMUEBLE"));
		la_acto.setRespuestaLey1943(ars_rs.getString("RESPUESTA_LEY1943"));
		la_acto.setPeriodicidadCuantia(ars_rs.getString("PERIODICIDAD_CUANTIA"));
		la_acto.setTiempoCanon(getLong(ars_rs, "TIEMPO_CANON"));
		la_acto.setEspecificacion(ars_rs.getString("ESPECIFICACION"));
		la_acto.setAreaActo(getDouble(ars_rs, "AREA_ACTO"));
		la_acto.setAreaTotal(getDouble(ars_rs, "AREA_TOTAL"));
		la_acto.setGarantiaHipotecaria(ars_rs.getString("GARANTIA_HIPOTECARIA"));
		la_acto.setHijuelaPasivo(ars_rs.getString("HIJUELA_PASIVO"));
		la_acto.setIdTipoTarifaRegistral(ars_rs.getString("ID_TIPO_TARIFA_REGISTRAL"));
		la_acto.setVersion(ars_rs.getLong("VERSION"));
		la_acto.setOrganismoInternacional(ars_rs.getString("ORGANISMO_INTERNACIONAL"));
		la_acto.setCantidadCertifAntSistema(getLong(ars_rs, "CANTIDAD_CERTIF_ANT_SISTEMA"));
		la_acto.setMadreCabeza(ars_rs.getString("MADRE_CABEZA"));

		if(ab_val)
			la_acto.setNombreTipoAdquisicion(ars_rs.getString("NOMBRE"));

		return la_acto;
	}

	/**
	 * Retorna el valor del objeto de TipoAdquisicion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return devuelve el valor de TipoAdquisicion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoAdquisicion
	 */
	private TipoAdquisicion findTipoAdqui(ResultSet ars_rs)
	    throws SQLException
	{
		TipoAdquisicion ls_tipoActo;

		ls_tipoActo = new TipoAdquisicion();

		ls_tipoActo.setIdTipoAdquisicion(ars_rs.getString("ID_TIPO_ADQUISICION"));
		ls_tipoActo.setNombre(ars_rs.getString("NOMBRE"));

		return ls_tipoActo;
	}
}
