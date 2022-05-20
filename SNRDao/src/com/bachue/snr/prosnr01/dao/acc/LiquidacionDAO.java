package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import java.math.BigInteger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_LIQUIDACION
 *
 * @author Manuel Blanco
 */
public class LiquidacionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_LIQUIDACION ";

	/** Constante cs_FIND_RELIQUIDACION. */
	private static final String cs_FIND_RELIQUIDACION = "SELECT * FROM SDB_ACC_LIQUIDACION WHERE NUMERO_REFERENCIA IS NULL AND NUMERO_RECIBO_LIQUIDACION IS NULL AND CONSECUTIVO > 1 AND NIR IS NOT NULL AND NVL(INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA,0) < ? ORDER BY FECHA_LIQUIDACION ASC";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_LIQUIDACION WHERE ID_SOLICITUD = ? ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT * FROM SDB_ACC_LIQUIDACION WHERE ID_TURNO = ? ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_ID_SOLICITUD_CONSECUTIVO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_CONSECUTIVO = "SELECT * FROM SDB_ACC_LIQUIDACION WHERE CONSECUTIVO = '1' AND ID_SOLICITUD = ? ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_NO_PAGADA = "SELECT * FROM SDB_ACC_LIQUIDACION WHERE ID_TIPO_ESTADO_LIQUIDACION = '1' AND NUMERO_REFERENCIA IS NOT NULL ";

	/** Constante cs_FIND_BY_NUMERO_REFERENCIA. */
	private static final String cs_FIND_BY_NUMERO_REFERENCIA = "SELECT * FROM SDB_ACC_LIQUIDACION WHERE NUMERO_REFERENCIA = ? ";

	/** Constante cs_FIND_NUMERO_REFERENCIA. */
	private static final String cs_FIND_NUMERO_REFERENCIA = "SELECT * FROM SDB_ACC_LIQUIDACION WHERE ID_SOLICITUD = ? AND NUMERO_REFERENCIA IS NOT NULL";

	/** Constante cs_FIND_SETTLEMENT_TO_SEND. */
	private static final String cs_FIND_SETTLEMENT_TO_SEND = "SELECT * FROM SDB_ACC_LIQUIDACION WHERE NVL(ENVIADO_NOTIFICACION_PAGO,'N') = 'N' AND CONSECUTIVO > 0 AND NUMERO_REFERENCIA IS NOT NULL AND VALOR_TOTAL >= 0 AND NVL(INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA,0) < ? ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_LIQUIDACION SET ID_SOLICITUD=?, NIR=?, NUMERO_REFERENCIA=?, VALOR=?, VALOR_IMPUESTOS=?, VALOR_TOTAL=?, FECHA_PAGO=?, PAGADA=? , MOTIVO_MAYOR_VALOR=?, VALOR_MAYOR_VALOR=?, VALOR_IMPUESTO_MAYOR_VALOR=?, TOTAL_MAYOR_VALOR=?, MOTIVO_SALDO_FAVOR=?, VALOR_SALDO_FAVOR=?, VALOR_IMPUESTO_SALDO_FAVOR=?, TOTAL_SALDO_FAVOR=?, ACTIVO=?, ID_TURNO=?, ID_TIPO_CANAL_ORIGEN = ?, ID_TIPO_ESTADO_LIQUIDACION = ?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP , IP_MODIFICACION=?"
		+ "WHERE ID_LIQUIDACION=? AND CONSECUTIVO= ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_LIQUIDACION_ID_LIQUIDACION.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE_INTENTO_ENVIOS. */
	private static final String cs_UPDATE_INTENTO_ENVIOS = "UPDATE SDB_ACC_LIQUIDACION SET INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA = ?, FECHA_INTENTO_EJECUCION_AUTOMATICA = SYSTIMESTAMP, FECHA_EXITO_EJECUCION_AUTOMATICA = ?, RESPUESTAS_EJECUCION_AUTOMATICA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_LIQUIDACION = ?";

	/** Constante cs_SIMULAR_LIQUIDACION. */
	private static final String cs_SIMULAR_LIQUIDACION = "UPDATE SDB_ACC_LIQUIDACION SET ID_TIPO_ESTADO_LIQUIDACION = '4', ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_SOLICITUD = ? AND CONSECUTIVO = '0'";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_LIQUIDACION (ID_LIQUIDACION, CONSECUTIVO, FECHA_LIQUIDACION, ID_SOLICITUD, NIR, NUMERO_REFERENCIA, VALOR, VALOR_IMPUESTOS, VALOR_TOTAL, FECHA_PAGO, PAGADA, MOTIVO_MAYOR_VALOR, VALOR_MAYOR_VALOR, VALOR_IMPUESTO_MAYOR_VALOR, TOTAL_MAYOR_VALOR, MOTIVO_SALDO_FAVOR, VALOR_SALDO_FAVOR,VALOR_IMPUESTO_SALDO_FAVOR, TOTAL_SALDO_FAVOR, ACTIVO,ID_TURNO, ID_TIPO_CANAL_ORIGEN, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_DETALLE_LIQUIDADO. */
	private static final String cs_DETALLE_LIQUIDADO = "SELECT LIQ.ID_LIQUIDACION, LIQ.CONSECUTIVO, ITEM.ID_ITEM, ITEM.ID_PROCESO ||'.'|| PROC.NOMBRE  PROCESO, SOL.ID_DOCUMENTO ||'. '||TDOC.NOMBRE TIPO_DOCUMENTO"
		+ ", DOC.NUMERO, TO_CHAR(DOC.FECHA_DOCUMENTO,'DD/MM/YYYY') FECHA_DOCUMENTO, DOC.ID_OFICINA_ORIGEN, ITEM.ID_CIRCULO ||'.'||NVL(CIRC.NOMBRE,'') CIRCULO_REGISTRAL, NVL(NVL(MAT_ACTO.ID_MATRICULA,CERT.ID_MATRICULA) || CASE WHEN NVL(MAT_ACTO.CONT_MAT_ACTO,CERT.CONT_MAT_ACTO) > 1 THEN 'Y OTRAS (' || NVL(MAT_ACTO.CONT_MAT_ACTO,CERT.CONT_MAT_ACTO) || ')' ELSE ' ' END,' ') MATRICULAS, CASE WHEN ITEM.ID_PROCESO <> '1' THEN ITEM.ID_ACTO ||'-'||ITEM.ID_TIPO_ACTO || '.' ||TO_CHAR(ITEM.VERSION) ||'. ' ||TACT.NOMBRE ELSE ' ' END TRAMITE,TACT.NOMBRE NOMBRE_ACTO, ITEM.ID_ACTO, NVL((CASE WHEN ITEM.ID_CONDICION_TARIFA IS NOT NULL AND COND.ID_TIPO_TARIFA_REGISTRAL IS NOT NULL THEN COND.ID_TIPO_TARIFA_REGISTRAL "
		+ "  WHEN ITEM.ID_CONDICION_TARIFA IS NOT NULL AND COND.ID_TARIFA_FIJA IS NOT NULL THEN COND.ID_TARIFA_FIJA  WHEN ITEM.ID_TIPO_TARIFA_REGISTRAL IS NOT NULL THEN ITEM.ID_TIPO_TARIFA_REGISTRAL "
		+ " WHEN ITEM.ID_TARIFA_FIJA IS NOT NULL THEN ITEM.ID_TARIFA_FIJA ELSE ' ' END),' ') TARIFA, NVL(TACT.REQUIERE_CUANTIA,'N') REQUIERE_CUANTIA,"
		+ " NVL(MV.CUANTIA,NVL(MAT_ACTO.CUANTIA,0)) CUANTIA, NVL(MV.VALOR_AVALUO,NVL(MAT_ACTO.VALOR_AVALUO,0) ) VALOR_AVALUO, NVL(MV.CANTIDAD_ACTOS,NVL(MAT_ACTO.CANTIDAD_ACTOS,0)) CANTIDAD_ACTOS, NVL(ITEM.CANTIDAD,0) CANTIDAD, NVL(ITEM.VALOR_FINAL,0) VALOR_FINAL, NVL(ITEM.VALOR_IMPUESTOS_FINAL,0) VALOR_IMPUESTOS_FINAL, NVL(ITEM.VALOR_CONSERV_DOCUMENTAL,0)VALOR_CONSERV_DOCUMENTAL,"
		+ " NVL(ITEM.VALOR_TOTAL,0) VALOR_TOTAL ";

	/** Constante cs_RESUMEN_LIQUIDADO. */
	private static final String cs_RESUMEN_LIQUIDADO = "SELECT ITEM.ID_CIRCULO ||'. '||NVL(CIRC.NOMBRE,'') CIRCULO_REGISTRAL,NVL(sum(ITEM.VALOR_TOTAL),0) TOTAL_LIQUIDADO"
		+ " FROM SDB_ACC_LIQUIDACION LIQ INNER JOIN SDB_ACC_LIQUIDACION_ITEM ITEM on LIQ.ID_LIQUIDACION = ITEM.ID_LIQUIDACION AND LIQ.CONSECUTIVO = ITEM.CONSECUTIVO"
		+ " LEFT JOIN SDB_PGN_CIRCULO_REGISTRAL CIRC ON CIRC.ID_CIRCULO = ITEM.ID_CIRCULO  "
		+ "  INNER JOIN (SELECT ID_SOLICITUD, MAX(ID_LIQUIDACION) ID_LIQUIDACION, MAX(CONSECUTIVO) CONSECUTIVO FROM SDB_ACC_LIQUIDACION WHERE SDB_ACC_LIQUIDACION.ID_SOLICITUD = ?"
		+ "  GROUP BY ID_SOLICITUD) FILTRO ON FILTRO.ID_SOLICITUD = LIQ.ID_SOLICITUD AND FILTRO.ID_LIQUIDACION = LIQ.ID_LIQUIDACION AND FILTRO.CONSECUTIVO = LIQ.CONSECUTIVO GROUP BY ITEM.ID_CIRCULO ||'. '||NVL(CIRC.NOMBRE,'')";

	/** Constante CS_COND_LIQUIDACION. */
	private static final String CS_COND_LIQUIDACION = "SELECT SOL.ID_SOLICITUD,SOL.NIR,ITEM.ID_LIQUIDACION,ITEM.CONSECUTIVO,ITEM.ID_ITEM,ITEM.ID_ACTO,ITEM.ID_CIRCULO,CR.NOMBRE NOMBRE_CIRCULO,ITEM.ID_TIPO_ACTO,TPOACT.NOMBRE,ITEM.VERSION,ITEM.ID_PROCESO,ITEM.ID_SUBPROCESO,COND.ID_TIPO_ACTO_CONDICION,COND.ACTIVO,COND.ID_USUARIO_MODIFICACION,COND.FECHA_MODIFICACION,COND.IP_MODIFICACION,COND.RESPUESTA,COND.AUTOMATICO,TPOACTCOND.ID_CONDICION FROM SDB_ACC_LIQUIDACION SOL INNER JOIN SDB_ACC_LIQUIDACION_ITEM ITEM ON SOL.ID_LIQUIDACION = ITEM.ID_LIQUIDACION AND SOL.CONSECUTIVO = ITEM.CONSECUTIVO INNER JOIN SDB_ACC_LIQUIDACION_ITEM_CONDICION COND ON COND.ID_LIQUIDACION = ITEM.ID_LIQUIDACION AND COND.CONSECUTIVO = ITEM.CONSECUTIVO AND COND.ID_ITEM = ITEM.ID_ITEM INNER JOIN SDB_PGN_TIPO_ACTO_CONDICION TPOACTCOND ON COND.ID_TIPO_ACTO_CONDICION = TPOACTCOND.ID_TIPO_ACTO_CONDICION INNER JOIN SDB_PGN_TIPO_ACTO TPOACT ON TPOACT.ID_TIPO_ACTO = TPOACTCOND.ID_TIPO_ACTO AND TPOACT.VERSION = TPOACTCOND.VERSION LEFT JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON ITEM.ID_CIRCULO = CR.ID_CIRCULO WHERE SOL.ID_SOLICITUD = ? AND COND.ACTIVO = 'S' AND ITEM.ACTIVO = 'S'";

	/** Constante CS_COND_LIQUIDACION MAYOR VALOR. */
	private static final String CS_COND_LIQUIDACION_MAYOR_VALOR = "SELECT SOL.ID_SOLICITUD,SOL.NIR,ITEM.ID_LIQUIDACION,ITEM.CONSECUTIVO,ITEM.ID_ITEM,ITEM.ID_ACTO,ITEM.ID_CIRCULO,CR.NOMBRE NOMBRE_CIRCULO,ITEM.ID_TIPO_ACTO,TPOACT.NOMBRE,ITEM.VERSION,ITEM.ID_PROCESO,ITEM.ID_SUBPROCESO,COND.ID_TIPO_ACTO_CONDICION,COND.ACTIVO,COND.ID_USUARIO_MODIFICACION,COND.FECHA_MODIFICACION,COND.IP_MODIFICACION,COND.RESPUESTA,COND.AUTOMATICO,TPOACTCOND.ID_CONDICION FROM SDB_ACC_LIQUIDACION SOL INNER JOIN SDB_ACC_LIQUIDACION_ITEM ITEM ON SOL.ID_LIQUIDACION = ITEM.ID_LIQUIDACION AND SOL.CONSECUTIVO = ITEM.CONSECUTIVO INNER JOIN SDB_ACC_LIQUIDACION_ITEM_CONDICION COND ON COND.ID_LIQUIDACION = ITEM.ID_LIQUIDACION AND COND.CONSECUTIVO = ITEM.CONSECUTIVO AND COND.ID_ITEM = ITEM.ID_ITEM INNER JOIN SDB_PGN_TIPO_ACTO_CONDICION TPOACTCOND ON COND.ID_TIPO_ACTO_CONDICION = TPOACTCOND.ID_TIPO_ACTO_CONDICION INNER JOIN SDB_PGN_TIPO_ACTO TPOACT ON TPOACT.ID_TIPO_ACTO = TPOACTCOND.ID_TIPO_ACTO AND TPOACT.VERSION = TPOACTCOND.VERSION LEFT JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON ITEM.ID_CIRCULO = CR.ID_CIRCULO INNER JOIN SDB_ACC_ACTO AC ON AC.ID_ACTO = ITEM.ID_ACTO WHERE SOL.ID_SOLICITUD = ? AND COND.ACTIVO = 'S' AND ITEM.ACTIVO = 'S' AND AC.IND_MAYOR_VALOR IS NOT NULL";

	/** Constante CS_UPDATE_CONDICIONES. */
	private static final String CS_UPDATE_CONDICIONES = "UPDATE SDB_ACC_LIQUIDACION_ITEM_CONDICION SET RESPUESTA = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_LIQUIDACION = ? AND CONSECUTIVO = ? AND ID_ITEM = ? AND ID_TIPO_ACTO_CONDICION = ?";

	/** Constante cs_FIND_DATA_LIQUIDACION. */
	private static final String cs_FIND_DATA_LIQUIDACION = "SELECT PCR.ID_CIRCULO || '-'||PCR.NOMBRE NOMBRE_CIRCULO, TF.ID_TARIFA_FIJA,TF.VALOR,SAA.CANTIDAD_ACTOS ,'NORMAL' TIPO_TARIFA,AL.VALOR_TOTAL,SAA.ID_ACTO,AL.ID_LIQUIDACION ,AL.CONSECUTIVO"
		+ " FROM SDB_ACC_ACTO  SAA  INNER JOIN SDB_PGN_TARIFA_FIJA TF ON TF.ID_TIPO_ACTO = SAA.ID_TIPO_ACTO AND TF.VERSION_ACTO = SAA.VERSION "
		+ " LEFT JOIN SDB_ACC_LIQUIDACION AL ON AL.ID_SOLICITUD = SAA.ID_SOLICITUD INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PCR ON PCR.ID_CIRCULO = SAA.ID_CIRCULO AND PCR.ACTIVO = 'S' WHERE SAA.ID_SOLICITUD = ? ";

	/** Constante cs_FIND_DATA_LIQUIDACION_TERMINAR. */
	private static final String cs_FIND_DATA_LIQUIDACION_TERMINAR = " SELECT PCR.ID_CIRCULO || '-'||PCR.NOMBRE NOMBRE_CIRCULO, TF.ID_TARIFA_FIJA,TF.VALOR,SAA.CANTIDAD_ACTOS ,'NORMAL' TIPO_TARIFA,AL.VALOR_TOTAL,SAA.ID_ACTO,AL.ID_LIQUIDACION ,AL.CONSECUTIVO"
		+ " FROM SDB_ACC_ACTO  SAA  INNER JOIN SDB_PGN_TARIFA_FIJA TF ON TF.ID_TIPO_ACTO = SAA.ID_TIPO_ACTO AND TF.VERSION_ACTO = SAA.VERSION "
		+ " LEFT JOIN SDB_ACC_LIQUIDACION AL ON AL.ID_SOLICITUD = SAA.ID_SOLICITUD INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PCR ON PCR.ID_CIRCULO = SAA.ID_CIRCULO AND PCR.ACTIVO = 'S' WHERE SAA.ID_SOLICITUD = ? AND AL.CONSECUTIVO = '1'";

	/** Constante cs_ACTUALIZAR_DATOS_RECIBO_LIQUIDACION. */
	private static final String cs_ACTUALIZAR_DATOS_RECIBO_LIQUIDACION = "UPDATE SDB_ACC_LIQUIDACION SET NUMERO_REFERENCIA = ?, NUMERO_RECIBO_LIQUIDACION = ? , ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP ,CODIGO_BARRAS = ? WHERE ID_SOLICITUD = ?  AND CONSECUTIVO = ?";

	/** Constante cs_ACTUALIZAR_LIQUIDACION_MAYOR_VALOR. */
	private static final String cs_ACTUALIZAR_LIQUIDACION_MAYOR_VALOR = "UPDATE SDB_ACC_LIQUIDACION SET NUMERO_REFERENCIA = ?, NUMERO_RECIBO_LIQUIDACION = ? , ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP ,CODIGO_BARRAS = ? WHERE ID_SOLICITUD = ? AND CONSECUTIVO = ? AND ID_TURNO = ? AND ID_TIPO_MAYOR_VALOR = ?";

	/** Constante cs_ACTUALIZAR_ENVIO_NOTIFICACION_PAGO. */
	private static final String cs_ACTUALIZAR_ENVIO_NOTIFICACION_PAGO = "UPDATE SDB_ACC_LIQUIDACION SET ENVIADO_NOTIFICACION_PAGO = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_LIQUIDACION = ?  AND CONSECUTIVO = ?";

	/** Constante cs_BUSCAR_MAX_CONSECUTIVO_SOLI_TURNO. */
	private static final String cs_BUSCAR_MAX_CONSECUTIVO_SOLI_TURNO = "SELECT MAX(CONSECUTIVO) FROM SDB_ACC_LIQUIDACION WHERE ID_SOLICITUD = ? AND ID_TURNO = ? AND ID_TIPO_MAYOR_VALOR = ?";

	/** Constante cs_ACTUALIZAR_NIR_LIQUIDACION. */
	private static final String cs_ACTUALIZAR_NIR_LIQUIDACION = "UPDATE SDB_ACC_LIQUIDACION SET NIR= ? WHERE ID_SOLICITUD = ?  AND CONSECUTIVO = 1";

	/**
	 * Actualiza condiciones.
	 *
	 * @param ac_liquidacion correspondiente al valor del tipo de objeto Collection<Liquidacion>
	 * @param ab_bool correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizaCondiciones(
	    Collection<Liquidacion> ac_liquidacion, boolean ab_bool, String as_userId, String as_ipRemote
	)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(ac_liquidacion))
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(CS_UPDATE_CONDICIONES);

				for(Liquidacion ll_liq : ac_liquidacion)
				{
					String ls_automatico;

					ls_automatico = ll_liq.getAutomatico();

					if(StringUtils.isValidString(ls_automatico) && ls_automatico.equals("M"))
					{
						if(ab_bool)
						{
							String ls_selected;

							ls_selected = StringUtils.getStringNotNull(ll_liq.getSelected());

							ll_liq.setRespuesta(ls_selected.equals("1") ? "S" : "N");
						}

						lps_ps.setString(li_column++, ll_liq.getRespuesta());
						lps_ps.setString(li_column++, as_userId);
						lps_ps.setString(li_column++, as_ipRemote);
						lps_ps.setString(li_column++, ll_liq.getIdLiquidacion());
						lps_ps.setInt(li_column++, NumericUtils.getInt(ll_liq.getConsecutivo()));
						lps_ps.setInt(li_column++, NumericUtils.getInt(ll_liq.getIdItem()));
						lps_ps.setInt(li_column++, NumericUtils.getInt(ll_liq.getIdTipoActoCondicion()));

						lps_ps.executeUpdate();

						li_column = 1;
					}
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizaCondiciones", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de actualizar los datos necesarios para generar el recibo de liquidación.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los datos a actualizar.
	 * @param ab_idTipoMayorValor Argumento de tipo <code>boolean</code> que determina si se actualiza la liquidación
	 * para mayor valor <code>true</code> de lo contrario <code>false</code>.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarDatosReciboLiquidacion(Liquidacion al_liquidacion, boolean ab_idTipoMayorValor)
	    throws B2BException
	{
		if(al_liquidacion != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection()
						                .prepareStatement(
						    ab_idTipoMayorValor ? cs_ACTUALIZAR_LIQUIDACION_MAYOR_VALOR
						                            : cs_ACTUALIZAR_DATOS_RECIBO_LIQUIDACION
						);

				lps_ps.setString(li_column++, al_liquidacion.getNumeroReferencia());
				lps_ps.setString(li_column++, al_liquidacion.getNumeroReciboLiquidacion());
				lps_ps.setString(li_column++, al_liquidacion.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, al_liquidacion.getIpModificacion());
				lps_ps.setString(li_column++, al_liquidacion.getConsecutivoBarcode());

				lps_ps.setString(li_column++, al_liquidacion.getIdSolicitud());
				lps_ps.setInt(li_column++, NumericUtils.getInt(al_liquidacion.getConsecutivo()));

				if(ab_idTipoMayorValor)
				{
					lps_ps.setString(li_column++, al_liquidacion.getIdTurno());
					lps_ps.setString(li_column++, al_liquidacion.getIdTipoMayorValor());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarDatosReciboLiquidacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualizar envio notificacion pago.
	 *
	 * @param al_param correspondiente al valor del tipo de objeto Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarEnvioNotificacionPago(Liquidacion al_param)
	    throws B2BException
	{
		if(al_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_ACTUALIZAR_ENVIO_NOTIFICACION_PAGO);

				lps_ps.setString(li_column++, al_param.getEnviadoNotificacionPago());
				lps_ps.setString(li_column++, al_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, al_param.getIpModificacion());

				lps_ps.setString(li_column++, al_param.getIdLiquidacion());
				lps_ps.setInt(li_column++, NumericUtils.getInt(al_param.getConsecutivo()));

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarEnvioNotificacionPago", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de actualizar el nir necesario para la liquidación.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los datos a actualizar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarNirLiquidacion(Liquidacion al_liquidacion)
	    throws B2BException
	{
		if(al_liquidacion != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_ACTUALIZAR_NIR_LIQUIDACION);

				lps_ps.setString(li_column++, al_liquidacion.getNir());
				lps_ps.setString(li_column++, al_liquidacion.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarNirLiquidacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de consultar el máximo consecutivo de una solicitud, turno y tipo mayor valor.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios de búsqueda.
	 * @return Variable de tipo <code>int</code> que contiene el consecutivo consultado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int buscarMaxConsecutivoSoliTurno(Liquidacion al_liquidacion)
	    throws B2BException
	{
		int li_consecutivo;
		li_consecutivo = 0;

		if(al_liquidacion != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_BUSCAR_MAX_CONSECUTIVO_SOLI_TURNO);

				lps_ps.setString(li_column++, al_liquidacion.getIdSolicitud());
				lps_ps.setString(li_column++, al_liquidacion.getIdTurno());
				lps_ps.setString(li_column++, al_liquidacion.getIdTipoMayorValor());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_consecutivo = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarMaxConsecutivoSoliTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_consecutivo;
	}

	/**
	 * Retorna el valor del objeto de Collection de Liquidacion.
	 *
	 * @param al_l correspondiente al valor del tipo de objeto Liquidacion
	 * @param ab_tipoRecibo correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> detalleLiquidacion(Liquidacion al_l, boolean ab_tipoRecibo)
	    throws B2BException
	{
		Collection<Liquidacion> ls_object;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;
		int                     li_column;
		StringBuilder           lsb_sb;

		li_column     = 1;
		ls_object     = new ArrayList<Liquidacion>();
		lps_ps        = null;
		lrs_rs        = null;

		if(al_l != null)
		{
			try
			{
				lsb_sb = new StringBuilder(cs_DETALLE_LIQUIDADO);

				if(!ab_tipoRecibo)
					lsb_sb.append(
					    " ,PAGO.NUMERO_REFERENCIA,PAGO.ID_TIPO_CANAL,CANAL.DESCRIPCION_CANAL_ORIGEN,PAGO.ID_TIPO_RECAUDO,TIPO_PAGO.DESCRIPCION,"
					    + " TO_CHAR(PAGO.FECHA_BANCARIA,'DD/MM/YYYY HH24:MI:SS') FECHA_BANCARIA,PAGO.MONTO_TRANSACCION,PAGO.NUMERO_RECIBO_CAJA,"
					    + " TO_CHAR(PAGO.FECHA_GENERACION_RECIBO,'DD/MM/YYYY HH24:MI:SS') FECHA_GENERACION_RECIBO"
					);

				lsb_sb.append(
				    "  FROM SDB_ACC_LIQUIDACION LIQ INNER JOIN SDB_ACC_SOLICITUD SOL ON SOL.ID_SOLICITUD = LIQ.ID_SOLICITUD "
				    + " LEFT JOIN SDB_BGN_DOCUMENTO DOC ON DOC.ID_DOCUMENTO = SOL.ID_DOCUMENTO AND DOC.VERSION_DOCUMENTO = SOL.VERSION_DOCUMENTO "
				    + " LEFT JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDOC ON TDOC.ID_TIPO_DOCUMENTO = DOC.ID_TIPO_DOCUMENTO "
				    + " INNER JOIN SDB_ACC_LIQUIDACION_ITEM ITEM ON LIQ.ID_LIQUIDACION = ITEM.ID_LIQUIDACION AND LIQ.CONSECUTIVO = ITEM.CONSECUTIVO "
				    + "LEFT JOIN (SELECT ID_LIQUIDACION, CONSECUTIVO, ID_ITEM, MAX(ID_CIRCULO || '-' || TO_CHAR(ID_MATRICULA) ) ID_MATRICULA, "
				    + "COUNT(DISTINCT ID_CIRCULO || '-' || TO_CHAR(ID_MATRICULA) ) CONT_MAT_ACTO FROM SDB_ACC_LIQUIDACION_ITEM_CERTIFICADO "
				    + "GROUP BY ID_LIQUIDACION, CONSECUTIVO, ID_ITEM) CERT ON CERT.ID_LIQUIDACION = ITEM.ID_LIQUIDACION AND "
				    + "CERT.CONSECUTIVO = ITEM.CONSECUTIVO AND CERT.ID_ITEM = ITEM.ID_ITEM"
				);

				if(!ab_tipoRecibo)
					lsb_sb.append(
					    " INNER JOIN SDB_ACC_REGISTRO_PAGO PAGO ON PAGO.NUMERO_REFERENCIA = LIQ.NUMERO_REFERENCIA"
					    + " INNER JOIN SDB_PGN_TIPO_CANAL_ORIGEN CANAL ON CANAL.ID_TIPO_CANAL_ORIGEN = PAGO.ID_TIPO_CANAL"
					    + " INNER JOIN SDB_ACC_TIPO_PAGO TIPO_PAGO ON TIPO_PAGO.ID_TIPO_PAGO = PAGO.ID_TIPO_RECAUDO"
					);

				lsb_sb.append(
				    " LEFT JOIN SDB_ACC_REGISTRO_MAYOR_VALOR MV ON ITEM.ID_LIQUIDACION = MV.ID_LIQUIDACION AND ITEM.CONSECUTIVO = MV.CONSECUTIVO AND ITEM.ID_ACTO = MV.ID_ACTO "
				    + " LEFT JOIN SDB_PGN_TIPO_ACTO TACT ON TACT.ID_TIPO_ACTO = ITEM.ID_TIPO_ACTO AND TACT.VERSION = ITEM.VERSION INNER JOIN SDB_ACC_PROCESO PROC ON ITEM.ID_PROCESO = PROC.ID_PROCESO "
				    + " LEFT JOIN SDB_PGN_CIRCULO_REGISTRAL CIRC ON CIRC.ID_CIRCULO = ITEM.ID_CIRCULO LEFT JOIN (SELECT ACT.ID_SOLICITUD, ACT.ID_ACTO, ACT.ID_TIPO_ACTO, ACT.CUANTIA, ACT.VALOR_AVALUO, ACT.VERSION, ACT.CANTIDAD_ACTOS, MAX(MACT.ID_CIRCULO ||'-'||TO_CHAR(MACT.ID_MATRICULA))"
				    + " ID_MATRICULA, COUNT(DISTINCT MACT.ID_CIRCULO||'-'||TO_CHAR(MACT.ID_MATRICULA)) CONT_MAT_ACTO FROM SDB_ACC_ACTO ACT INNER JOIN SDB_ACC_SOLICITUD_MATRICULA_ACTO MACT ON MACT.ID_SOLICITUD = ACT.ID_SOLICITUD AND MACT.ID_ACTO = ACT.ID_ACTO"
				    + " GROUP BY ACT.ID_SOLICITUD, ACT.ID_ACTO, ACT.ID_TIPO_ACTO, ACT.CUANTIA, ACT.VALOR_AVALUO, ACT.VERSION, ACT.CANTIDAD_ACTOS) MAT_ACTO ON MAT_ACTO.ID_SOLICITUD = LIQ.ID_SOLICITUD AND MAT_ACTO.ID_ACTO = ITEM.ID_ACTO AND MAT_ACTO.ID_TIPO_ACTO = ITEM.ID_TIPO_ACTO AND MAT_ACTO.VERSION = ITEM.VERSION"
				    + " LEFT JOIN (SELECT TAC.ID_TIPO_ACTO_CONDICION , CT.ID_TIPO_TARIFA_REGISTRAL, CT.VERSION_TARIFA_REGISTRAL, CT.ID_TARIFA_FIJA, CT.VERSION_TARIFA_FIJA FROM SDB_PGN_TIPO_ACTO_CONDICION TAC"
				    + " INNER JOIN SDB_PGN_CONDICION_TARIFA CT ON ID_CONDICION = ID_CONDICION_TARIFA) COND ON COND.ID_TIPO_ACTO_CONDICION = ITEM.ID_CONDICION_TARIFA"
				    + " INNER JOIN (SELECT ID_SOLICITUD, MAX(ID_LIQUIDACION) ID_LIQUIDACION, MAX(CONSECUTIVO) CONSECUTIVO  FROM SDB_ACC_LIQUIDACION "
				    + " WHERE SDB_ACC_LIQUIDACION.ID_SOLICITUD = ? AND NVL(SDB_ACC_LIQUIDACION.ID_TIPO_MAYOR_VALOR,' ') = NVL(?,' ')"
				);

				String  ls_idTurno;
				boolean lb_turnoValido;

				ls_idTurno         = al_l.getIdTurno();
				lb_turnoValido     = StringUtils.isValidString(ls_idTurno);

				if(lb_turnoValido)
					lsb_sb.append(" AND NVL(SDB_ACC_LIQUIDACION.ID_TURNO,'NULL') = NVL(?,'NULL') ");

				lsb_sb.append(
				    " GROUP BY ID_SOLICITUD) FILTRO ON FILTRO.ID_SOLICITUD = LIQ.ID_SOLICITUD AND FILTRO.ID_LIQUIDACION = LIQ.ID_LIQUIDACION AND FILTRO.CONSECUTIVO = LIQ.CONSECUTIVO ORDER BY TO_NUMBER(ID_ITEM) ASC"
				);

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, al_l.getIdSolicitud());
				lps_ps.setString(li_column++, al_l.getIdTipoMayorValor());

				if(lb_turnoValido)
					lps_ps.setString(li_column++, ls_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(detalleInfoLiquidacion(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "detalleLiquidacion", lse_e);

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
	 * Retorna el valor del objeto de Liquidacion.
	 *
	 * @param al_param correspondiente al valor del tipo de objeto Liquidacion
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Liquidacion findById(Liquidacion al_param, boolean ab_b)
	    throws B2BException
	{
		Liquidacion ls_object;
		ls_object = null;

		if(al_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;
			StringBuilder     lsb_sb;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;
			lsb_sb        = new StringBuilder(cs_FIND_BY_ID);

			if(ab_b)
				lsb_sb.append(" WHERE ID_SOLICITUD=? AND CONSECUTIVO= ? ORDER BY FECHA_CREACION DESC ");
			else
				lsb_sb.append(
				    " LIQ INNER JOIN (SELECT ID_SOLICITUD, MAX(ID_LIQUIDACION) ID_LIQUIDACION, MAX(CONSECUTIVO) CONSECUTIVO "
				    + " FROM SDB_ACC_LIQUIDACION WHERE SDB_ACC_LIQUIDACION.ID_SOLICITUD = ?  GROUP BY ID_SOLICITUD) FILTRO ON FILTRO.ID_SOLICITUD = LIQ.ID_SOLICITUD "
				    + " AND FILTRO.ID_LIQUIDACION = LIQ.ID_LIQUIDACION AND FILTRO.CONSECUTIVO = LIQ.CONSECUTIVO"
				);

			try
			{
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, al_param.getIdSolicitud());

				if(ab_b)
					lps_ps.setInt(li_column++, al_param.getConsecutivo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
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
	 * Metodo encargado de buscar en la tabla de liquidacion por llave primaria.
	 *
	 * @param as_idLiquidacion Argumento de tipo <code>String</code> que contiene el idLiquidacion para realizar la consulta.
	 * @param ai_consecutivo Argumento de tipo <code>int</code> que contiene el consecutivo para realizar la consulta.
	 * @return Objeto de tipo <code>Liquidacion</code> que contiene el registro que coincida con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Liquidacion findById(String as_idLiquidacion, int ai_consecutivo)
	    throws B2BException
	{
		Liquidacion ll_datos;

		ll_datos = null;

		if(StringUtils.isValidString(as_idLiquidacion) && (ai_consecutivo > 0))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder(cs_FIND_BY_ID);

				lsb_sb.append(" WHERE ID_LIQUIDACION=? AND CONSECUTIVO= ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idLiquidacion);
				lps_ps.setInt(li_column++, ai_consecutivo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ll_datos = getObjetFromResultSet(lrs_rs);
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

		return ll_datos;
	}

	/**
	 * Metodo encargado de consultar todos los registros de liquidación de una solicitud.
	 *
	 * @param al_param Argumento de tipo <code>Liquidacion</code> que contiene los criterios de búsqueda para realizar la consulta.
	 * @return Colección de liquidaciones que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */

	/**
	 * Método encargado de consultar la liquidación para una solicitud con consecutivo 1.
	 *
	 * @param as_idSolicitud Variable que contiene el id de la solicitud.
	 * @return Objeto que contiene la información de la liquidación.
	 * @throws B2BException
	 */
	public Liquidacion findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Liquidacion lcl_datos;

		lcl_datos = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcl_datos = getObjetFromResultSet(lrs_rs);
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

		return lcl_datos;
	}

	/**
	 * Metodo encargado de consultar todos los registros de liquidación de una solicitud y un consecutivo.
	 *
	 * @param al_param Argumento de tipo <code>Liquidacion</code> que contiene los criterios de búsqueda para realizar la consulta.
	 * @return Colección de liquidaciones que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> findByIdSolicitudConsecutivo(Liquidacion al_param)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_datos;

		lcl_datos = new ArrayList<Liquidacion>();

		if(al_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				StringBuilder lsb_sb;
				String        ls_tipoMayorValor;
				boolean       lb_tipoMayorValor;

				lsb_sb                = new StringBuilder(cs_FIND_BY_ID);
				ls_tipoMayorValor     = al_param.getIdTipoMayorValor();
				lb_tipoMayorValor     = StringUtils.isValidString(ls_tipoMayorValor);

				lsb_sb.append(" WHERE ID_SOLICITUD=? AND CONSECUTIVO= ?");

				if(lb_tipoMayorValor)
					lsb_sb.append(" AND ID_TIPO_MAYOR_VALOR IS NOT NULL");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, al_param.getIdSolicitud());
				lps_ps.setInt(li_column++, al_param.getConsecutivo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcl_datos.add(getObjetFromResultSet(lrs_rs));
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

			if(lcl_datos.isEmpty())
				lcl_datos = null;
		}

		return lcl_datos;
	}

	/**
	 * Método encargado de consultar la liquidación para una solicitud con consecutivo 1.
	 *
	 * @param as_idSolicitud Variable que contiene el id de la solicitud.
	 * @param ai_consecutivo Variable que contiene el consecutivo de la liquidacón.
	 * @return Objeto que contiene la información de la liquidación.
	 * @throws B2BException
	 */
	public Liquidacion findByIdSolicitudOne(String as_idSolicitud)
	    throws B2BException
	{
		Liquidacion lcl_datos;

		lcl_datos = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_CONSECUTIVO);

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcl_datos = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudOne", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcl_datos;
	}

	/**
	 * Retorna el valor del objeto de Liquidacion.
	 *
	 * @param as_idSolicitud correspondiente al valor de la solicitud.
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param ab_etapa21 correspondiente al valor del tipo de objeto boolean que determina si el metodo es invocado desde etapa 21
	 * @return devuelve el valor de Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Liquidacion findByIdTipoMayorValor(String as_idSolicitud, boolean ab_etapa21)
	    throws B2BException
	{
		Liquidacion ls_object;
		ls_object = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;
			StringBuilder     lsb_sb;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;
			lsb_sb        = new StringBuilder(cs_FIND_BY_ID);

			lsb_sb.append(
			    " LIQ INNER JOIN (SELECT ID_SOLICITUD, MAX(ID_LIQUIDACION) ID_LIQUIDACION, MAX(CONSECUTIVO) CONSECUTIVO "
			    + " FROM SDB_ACC_LIQUIDACION WHERE SDB_ACC_LIQUIDACION.ID_SOLICITUD = ? AND SDB_ACC_LIQUIDACION.ID_TIPO_MAYOR_VALOR "
			);
			lsb_sb.append(ab_etapa21 ? " IS NULL " : " IS NOT NULL ");
			lsb_sb.append(
			    " GROUP BY ID_SOLICITUD) FILTRO ON FILTRO.ID_SOLICITUD = LIQ.ID_SOLICITUD "
			    + " AND FILTRO.ID_LIQUIDACION = LIQ.ID_LIQUIDACION AND FILTRO.CONSECUTIVO = LIQ.CONSECUTIVO"
			);

			try
			{
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTipoMayorValor", lse_e);

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
	 * Método encargado de consultar los registros de liquidación para un id turno.
	 *
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @return Objeto que contiene la información de la liquidación.
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	public Collection<Liquidacion> findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_datos;

		lcl_datos = new ArrayList<Liquidacion>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcl_datos.add(getObjetFromResultSet(lrs_rs));
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

		return lcl_datos;
	}

	/**
	 * Retorna el valor del objeto de Liquidacion.
	 *
	 * @param as_numeroReferencia correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Liquidacion findByNumeroReferencia(String as_numeroReferencia)
	    throws B2BException
	{
		Liquidacion ls_object;
		ls_object = null;

		if(as_numeroReferencia != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_NUMERO_REFERENCIA);

				lps_ps.setString(li_column++, as_numeroReferencia);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNumeroReferencia", lse_e);

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
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_solicitud correspondiente al valor del tipo de objeto Solicitud
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> findCondicionesLiquidacion(Solicitud as_solicitud)
	    throws B2BException
	{
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;
		Collection<Liquidacion> lc_liquidacion;

		lps_ps             = null;
		lrs_rs             = null;
		lc_liquidacion     = new ArrayList<Liquidacion>();

		if(as_solicitud != null)
		{
			try
			{
				if(as_solicitud.isMayorValor())
					lps_ps = getConnection().prepareStatement(CS_COND_LIQUIDACION_MAYOR_VALOR);
				else
					lps_ps = getConnection().prepareStatement(CS_COND_LIQUIDACION);

				lps_ps.setString(1, as_solicitud.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					Liquidacion ll_liq;

					ll_liq = new Liquidacion();

					ll_liq.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
					ll_liq.setNir(lrs_rs.getString("NIR"));
					ll_liq.setIdLiquidacion(lrs_rs.getString("ID_LIQUIDACION"));
					ll_liq.setConsecutivo(lrs_rs.getInt("CONSECUTIVO"));
					ll_liq.setIdItem(lrs_rs.getString("ID_ITEM"));
					ll_liq.setIdActo(lrs_rs.getString("ID_ACTO"));
					ll_liq.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
					ll_liq.setNombreCirculo(lrs_rs.getString("NOMBRE_CIRCULO"));
					ll_liq.setIdTipoActo(lrs_rs.getString("ID_TIPO_ACTO"));
					ll_liq.setNombre(lrs_rs.getString("NOMBRE"));
					ll_liq.setVersion(lrs_rs.getString("VERSION"));
					ll_liq.setIdProceso(lrs_rs.getString("ID_PROCESO"));
					ll_liq.setSubProceso(lrs_rs.getString("ID_SUBPROCESO"));
					ll_liq.setIdTipoActoCondicion(lrs_rs.getString("ID_TIPO_ACTO_CONDICION"));
					ll_liq.setActivo(lrs_rs.getString("ACTIVO"));
					ll_liq.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
					ll_liq.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
					ll_liq.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));
					ll_liq.setRespuesta(lrs_rs.getString("RESPUESTA"));
					ll_liq.setAutomatico(lrs_rs.getString("AUTOMATICO"));
					ll_liq.setIdCondicion(lrs_rs.getString("ID_CONDICION"));
					lc_liquidacion.add(ll_liq);

					{
						String ls_automatico;

						ls_automatico = ll_liq.getAutomatico();

						if(StringUtils.isValidString(ls_automatico))
						{
							if(ls_automatico.equalsIgnoreCase(EstadoCommon.A))
								ll_liq.setSelected("1");
						}

						String ls_respuesta;

						ls_respuesta = ll_liq.getRespuesta();

						if(StringUtils.isValidString(ls_respuesta))
						{
							if(ls_respuesta.equalsIgnoreCase(EstadoCommon.S))
								ll_liq.setRespuestaBoolean(true);
						}
					}
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findCondicionesLiquidacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lc_liquidacion;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_numeroReferencia correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> findNoPagada(String as_numeroReferencia)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_return;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcl_return     = new ArrayList<Liquidacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			boolean       ab_referencia;
			StringBuilder lsb_sb;

			ab_referencia     = StringUtils.isValidString(as_numeroReferencia);
			lsb_sb            = new StringBuilder(cs_FIND_NO_PAGADA);

			if(ab_referencia)
				lsb_sb.append(" AND NUMERO_REFERENCIA = ? ");

			lsb_sb.append(" ORDER BY FECHA_CREACION DESC ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			if(ab_referencia)
				lps_ps.setString(1, as_numeroReferencia);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcl_return.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findNoPagada", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcl_return;
	}

	/**
	 * Retorna el valor del objeto de Liquidacion.
	 *
	 * @param as_numeroReferencia correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Liquidacion findNumeroReferencia(String as_idSolicitud)
	    throws B2BException
	{
		Liquidacion ll_return;

		ll_return = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_NUMERO_REFERENCIA);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ll_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findNumeroReferencia", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ll_return;
	}

	public Collection<Liquidacion> findReliquidacion(long al_cantidadIntentos)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_return;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcl_return     = new ArrayList<Liquidacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_RELIQUIDACION);

			lps_ps.setLong(li_column++, al_cantidadIntentos);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcl_return.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findReliquidacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcl_return.isEmpty())
			lcl_return = null;

		return lcl_return;
	}

	/**
	 * Retorna el valor del objeto de Collection de Liquidacion.
	 *
	 * @return devuelve el valor de Collection de Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> findSettlementToSend(long al_limiteIntentos)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_liquidaciones;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcl_liquidaciones     = new ArrayList<Liquidacion>();
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_SETTLEMENT_TO_SEND);

			lps_ps.setLong(1, al_limiteIntentos);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcl_liquidaciones.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSettlementToSend", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcl_liquidaciones.isEmpty())
			lcl_liquidaciones = null;

		return lcl_liquidaciones;
	}

	/**
	 * Retorna el valor del objeto de BigInteger.
	 *
	 * @param al_parametros correspondiente al valor del tipo de objeto Liquidacion
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de BigInteger
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public BigInteger insertOrUpdate(Liquidacion al_parametros, boolean ab_query)
	    throws B2BException
	{
		BigInteger lbi_secuencia;

		lbi_secuencia = BigInteger.ZERO;

		if(al_parametros != null)
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
						lbi_secuencia = getBigInteger(lrs_rs, 1);

						lps_ps.setString(li_column++, StringUtils.getString(lbi_secuencia));
						lps_ps.setInt(li_column++, al_parametros.getConsecutivo());
						setDate(lps_ps, al_parametros.getFechaLiquidacion(), li_column++);
					}
				}

				lps_ps.setString(li_column++, al_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, al_parametros.getNir());
				lps_ps.setString(li_column++, al_parametros.getNumeroReferencia());
				lps_ps.setBigDecimal(li_column++, al_parametros.getValor());
				lps_ps.setBigDecimal(li_column++, al_parametros.getValorImpuestos());
				lps_ps.setBigDecimal(li_column++, al_parametros.getValorTotal());
				setDate(lps_ps, al_parametros.getFechaPago(), li_column++);
				lps_ps.setString(li_column++, al_parametros.getPagada());
				lps_ps.setString(li_column++, al_parametros.getMotivoMayorValor());
				lps_ps.setBigDecimal(li_column++, al_parametros.getValorMayorValor());
				lps_ps.setBigDecimal(li_column++, al_parametros.getValorImpuestoMayorValor());
				lps_ps.setBigDecimal(li_column++, al_parametros.getTotalMayorValor());
				lps_ps.setString(li_column++, al_parametros.getMotivoSaldoFavor());
				lps_ps.setBigDecimal(li_column++, al_parametros.getValorSaldoFavor());
				lps_ps.setBigDecimal(li_column++, al_parametros.getValorImpuestoSaldoFavor());
				lps_ps.setBigDecimal(li_column++, al_parametros.getTotalSaldoFavor());
				lps_ps.setString(li_column++, al_parametros.getActivo());
				lps_ps.setString(li_column++, al_parametros.getIdTurno());
				lps_ps.setString(li_column++, al_parametros.getIdTipoCanalOrigen());

				if(ab_query)
				{
					lps_ps.setString(li_column++, al_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, al_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, al_parametros.getIdTipoEstadoLiquidacion());
					lps_ps.setString(li_column++, al_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, al_parametros.getIpModificacion());
					lps_ps.setString(li_column++, al_parametros.getIdLiquidacion());
					lps_ps.setInt(li_column++, al_parametros.getConsecutivo());
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
				close(lrs_rs);

				close(lps_ps);
				close(lps_secuencia);
			}
		}

		return lbi_secuencia;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> liquidacionReproduccionConstancia(String as_idSolicitud, boolean ab_b)
	    throws B2BException
	{
		Collection<Liquidacion> ls_object;
		ls_object = new ArrayList<Liquidacion>();

		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_DATA_LIQUIDACION);

			lps_ps.setString(li_column++, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getDataLiquidacionRepConstacia(lrs_rs, ab_b));
		}
		catch(SQLException lse_e)
		{
			logError(this, "liquidacionReproduccionConstancia", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de Collection de Liquidacion.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> liquidacionReproduccionConstanciaTerminar(String as_idSolicitud, boolean ab_b)
	    throws B2BException
	{
		Collection<Liquidacion> ls_object;
		ls_object = new ArrayList<Liquidacion>();

		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_DATA_LIQUIDACION_TERMINAR);

			lps_ps.setString(li_column++, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getDataLiquidacionRepConstacia(lrs_rs, ab_b));
		}
		catch(SQLException lse_e)
		{
			logError(this, "liquidacionReproduccionConstanciaTerminar", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de Collection de Liquidacion.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> resumenLiquidacion(String as_idSolicitud)
	    throws B2BException
	{
		Collection<Liquidacion> ls_object;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;
		int                     li_column;

		li_column     = 1;

		ls_object     = new ArrayList<Liquidacion>();
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_RESUMEN_LIQUIDADO);

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(detalleInfoLiquidacion(lrs_rs, false));
			}
			catch(SQLException lse_e)
			{
				logError(this, "resumenLiquidacion", lse_e);

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
	 * Simular liquidacion.
	 *
	 * @param ls_idSolicitud the ls id solicitud
	 * @throws B2BException the b 2 B exception
	 */
	public void simularLiquidacion(Liquidacion al_liquidacion)
	    throws B2BException
	{
		if(al_liquidacion != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_SIMULAR_LIQUIDACION);

				lps_ps.setString(li_column++, al_liquidacion.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, al_liquidacion.getIpModificacion());
				lps_ps.setString(li_column++, al_liquidacion.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "simularLiquidacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update intento envios.
	 *
	 * @param al_parametros de al parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateIntentoEnvios(Liquidacion al_parametros)
	    throws B2BException
	{
		if(al_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_INTENTO_ENVIOS);

				lps_ps.setLong(li_column++, al_parametros.getIntentosFallidosEjecucionAutomatica());
				setDate(lps_ps, al_parametros.getFechaExitoEjecucionAutomatica(), li_column++);
				lps_ps.setString(li_column++, al_parametros.getRespuestaEjecucionAutomatica());
				lps_ps.setString(li_column++, al_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, al_parametros.getIpModificacion());
				lps_ps.setString(li_column++, al_parametros.getIdLiquidacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateIntentoEnvios", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de data liquidacion rep constacia.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return el valor de data liquidacion rep constacia
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Liquidacion getDataLiquidacionRepConstacia(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		Liquidacion lp_liquidacion;

		lp_liquidacion = new Liquidacion();

		lp_liquidacion.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));
		lp_liquidacion.setIdTarifa(ars_rs.getString("ID_TARIFA_FIJA"));
		lp_liquidacion.setValor(getBigDecimal(ars_rs, "VALOR"));
		lp_liquidacion.setCantidadActos(getLong(ars_rs, "CANTIDAD_ACTOS"));
		lp_liquidacion.setConsecutivo(ars_rs.getInt("CONSECUTIVO"));
		lp_liquidacion.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		lp_liquidacion.setTarifa(ars_rs.getString("TIPO_TARIFA"));
		lp_liquidacion.setValorTotal(getBigDecimal(ars_rs, "VALOR_TOTAL"));
		lp_liquidacion.setIdActo(ars_rs.getString("ID_ACTO"));
		lp_liquidacion.setDetalleReproduccionConstancia(ab_b);
		lp_liquidacion.setSinCantidadActos(ab_b);

		return lp_liquidacion;
	}

	/**
	 * Retorna el valor de Liquidacion
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Liquidacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Liquidacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Liquidacion lp_liquidacion;

		lp_liquidacion = new Liquidacion();

		lp_liquidacion.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		lp_liquidacion.setConsecutivo(ars_rs.getInt("CONSECUTIVO"));
		lp_liquidacion.setFechaLiquidacion(ars_rs.getTimestamp("FECHA_LIQUIDACION"));
		lp_liquidacion.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		lp_liquidacion.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lp_liquidacion.setNir(ars_rs.getString("NIR"));
		lp_liquidacion.setNumeroReferencia(ars_rs.getString("NUMERO_REFERENCIA"));
		lp_liquidacion.setValor(getBigDecimal(ars_rs, "VALOR"));
		lp_liquidacion.setValorImpuestos(getBigDecimal(ars_rs, "VALOR_IMPUESTOS"));
		lp_liquidacion.setValorTotal(getBigDecimal(ars_rs, "VALOR_TOTAL"));
		lp_liquidacion.setFechaPago(ars_rs.getTimestamp("FECHA_PAGO"));
		lp_liquidacion.setPagada(ars_rs.getString("PAGADA"));
		lp_liquidacion.setMotivoMayorValor(ars_rs.getString("MOTIVO_MAYOR_VALOR"));
		lp_liquidacion.setValorMayorValor(getBigDecimal(ars_rs, "VALOR_MAYOR_VALOR"));
		lp_liquidacion.setValorImpuestoMayorValor(getBigDecimal(ars_rs, "VALOR_IMPUESTO_MAYOR_VALOR"));
		lp_liquidacion.setTotalMayorValor(getBigDecimal(ars_rs, "TOTAL_MAYOR_VALOR"));
		lp_liquidacion.setMotivoSaldoFavor(ars_rs.getString("MOTIVO_SALDO_FAVOR"));
		lp_liquidacion.setValorSaldoFavor(getBigDecimal(ars_rs, "VALOR_SALDO_FAVOR"));
		lp_liquidacion.setValorImpuestoSaldoFavor(getBigDecimal(ars_rs, "VALOR_IMPUESTO_SALDO_FAVOR"));
		lp_liquidacion.setTotalSaldoFavor(getBigDecimal(ars_rs, "TOTAL_SALDO_FAVOR"));
		lp_liquidacion.setActivo(ars_rs.getString("ACTIVO"));
		lp_liquidacion.setIdLiquidacionAsociada(ars_rs.getString("ID_LIQUIDACION_ASOCIADA"));
		lp_liquidacion.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		lp_liquidacion.setNumeroReciboLiquidacion(ars_rs.getString("NUMERO_RECIBO_LIQUIDACION"));    //
		lp_liquidacion.setIdTipoEstadoLiquidacion(ars_rs.getString("ID_TIPO_ESTADO_LIQUIDACION"));
		lp_liquidacion.setIdTipoCanalOrigen(ars_rs.getString("ID_TIPO_CANAL_ORIGEN"));
		lp_liquidacion.setConsecutivoAsociada(getInteger(ars_rs, "CONSECUTIVO_ASOCIADA"));
		lp_liquidacion.setIdTipoMayorValor(ars_rs.getString("ID_TIPO_MAYOR_VALOR"));
		lp_liquidacion.setIdTurno(ars_rs.getString("ID_TURNO"));
//		lp_liquidacion.setConsecutivoBarcode(ars_rs.getString("CODIGO_BARRAS"));
//		lp_liquidacion.setEnviadoNotificacionPago(ars_rs.getString("ENVIADO_NOTIFICACION_PAGO"));
//		lp_liquidacion.setValorConservacionDocumental(ars_rs.getLong("VALOR_CONSERVACION_DOCUMENTAL"));
		lp_liquidacion.setIntentosFallidosEjecucionAutomatica(ars_rs.getLong("INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA"));
		lp_liquidacion.setFechaIntentoEjecucionAutomatica(ars_rs.getTimestamp("FECHA_INTENTO_EJECUCION_AUTOMATICA"));
		lp_liquidacion.setFechaExitoEjecucionAutomatica(ars_rs.getTimestamp("FECHA_EXITO_EJECUCION_AUTOMATICA"));
		lp_liquidacion.setRespuestaEjecucionAutomatica(ars_rs.getString("RESPUESTAS_EJECUCION_AUTOMATICA"));

		fillAuditoria(ars_rs, lp_liquidacion);

		return lp_liquidacion;
	}

	/**
	 * Retorna el valor del objeto de Liquidacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Liquidacion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	private Liquidacion detalleInfoLiquidacion(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		Liquidacion lp_liquidacion;

		lp_liquidacion = new Liquidacion();

		if(ab_b)
		{
			lp_liquidacion.setIdProceso(ars_rs.getString("PROCESO"));
			lp_liquidacion.setCirculosRegistrales(ars_rs.getString("CIRCULO_REGISTRAL"));
			lp_liquidacion.setNumeroMatriculas(ars_rs.getString("MATRICULAS"));
			lp_liquidacion.setTramite(ars_rs.getString("TRAMITE"));
			lp_liquidacion.setTarifa(ars_rs.getString("TARIFA"));
			lp_liquidacion.setRequiereCuantia(ars_rs.getString("REQUIERE_CUANTIA"));
			lp_liquidacion.setCuantiaActo(ars_rs.getString("CUANTIA"));
			lp_liquidacion.setCantidadActos(getLong(ars_rs, "CANTIDAD_ACTOS"));
			lp_liquidacion.setNumeroCuantiaActo(getBigDecimal(ars_rs, "CUANTIA"));
			lp_liquidacion.setValorAvaluo(getBigDecimal(ars_rs, "VALOR_AVALUO"));
			lp_liquidacion.setValorFinal(getDouble(ars_rs, "VALOR_FINAL"));
			lp_liquidacion.setValorDocumental(getBigDecimal(ars_rs, "VALOR_CONSERV_DOCUMENTAL"));
			lp_liquidacion.setValorImpuestoFinal(getBigDecimal(ars_rs, "VALOR_IMPUESTOS_FINAL"));
			lp_liquidacion.setValorTotal(getBigDecimal(ars_rs, "VALOR_TOTAL"));
			lp_liquidacion.setDescripcionActo(ars_rs.getString("NOMBRE_ACTO"));
			lp_liquidacion.setIdActo(ars_rs.getString("ID_ACTO"));
			lp_liquidacion.setCertificadosAsociados(getLong(ars_rs, "CANTIDAD"));
		}
		else
		{
			lp_liquidacion.setNombreCirculo(ars_rs.getString("CIRCULO_REGISTRAL"));
			lp_liquidacion.setValorTotal(getBigDecimal(ars_rs, "TOTAL_LIQUIDADO"));
		}

		return lp_liquidacion;
	}
}
