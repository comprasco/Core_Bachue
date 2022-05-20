package com.bachue.snr.prosnr01.dao.view;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.model.pgn.CRPSCabecera;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;


/**
 * Clase encargada de las interacciones con base de datos para la vista SDB_VW_CRPS_ENC_CONCILIAR
 *
 * @author Edgar Prieto
 */
public class CRPSCabeceraDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Sentencia SQL para la consulta de registros por numero referencia */
	private static final String cs_FIND_BY_REFERENCIA = "SELECT COUNT(*) FROM SDB_CON_CRPS WHERE NUMERO_REFERENCIA=?";

	/** Sentencia SQL para la consulta de registros por fecha */
	private static final String cs_CONSULTAR_PARA_ENVIO_CONCILIACION = "SELECT ID_REGISTRO_PAGO,ID_SOLICITUD,"
		+ "FECHA_SOLICITUD,NIR,ID_PROCESO,NOMBRE_PROCESO,ID_SUBPROCESO,NOMBRE_SUBPROCESO,DIGITALIZADA,"
		+ "ID_USUARIO_CREACION,ID_DOCUMENTO_TIPO,NUMERO_DOCUMENTO,ID_TIPO_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,"
		+ "PRIMER_APELLIDO,SEGUNDO_APELLIDO,RAZON_SOCIAL,ID_LIQUIDACION,CONSECUTIVO_LIQUIDACION,FECHA_LIQUIDACION"
		+ ",VALOR_LIQ,VALOR_IMPUESTO_LIQ,VALOR_TOTAL_LIQ,VALOR_CONSERVACION_DOCUMENTAL,NUMERO_REFERENCIA,ID_TIPO_CANAL,"
		+ "ID_ENTIDAD_RECAUDO,ID_SUCURSAL_RECAUDO,NUMERO_CUENTA_RECAUDO,ID_TIPO_RECAUDO,FECHA_BANCARIA,"
		+ "MONTO_TRANSACCION,CODIGO_TRANSACCION_RECAUDADOR,FECHA_TRANSACCION,NUMERO_RECIBO_CAJA,FECHA_"
		+ "GENERACION_RECIBO,ESTADO_PAGO FROM SDB_VW_CRPS_ENC_CONCILIAR ORDER BY NUMERO_REFERENCIA";

	/** Sentencia SQL para actualizar los datos de extracción de informacipon CRPS para envio a Conciliaciones */
	private static final String cs_ACTUALIZAR_ENVIADO_CONCILIACION = "UPDATE SDB_ACC_REGISTRO_PAGO SET "
		+ "FECHA_ENVIADO_CONCILIACION=SYSTIMESTAMP,ENVIADO_CONCILIACION='S',ID_USUARIO_MODIFICACION=?,"
		+ "IP_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP WHERE ENVIADO_CONCILIACION='X'";

	/**
	 * Sentencia SQL para marcar los regisros que deben ser extraidos para la generación del archivo CRPS para
	 * conciliaciones
	 */
	private static final String cs_ACTUALIZAR_PARA_ENVIO_CONCILIACION = "UPDATE SDB_ACC_REGISTRO_PAGO SET "
		+ "ENVIADO_CONCILIACION='X',ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP WHERE "
		+ "TRUNC(FECHA_BANCARIA)=TO_DATE(?,'YYYY/MM/DD') AND NVL(ENVIADO_CONCILIACION,'N')='N' AND "
		+ "FECHA_ENVIADO_CONCILIACION IS NULL";

	/** Sentencia SQL para la inserción de un registro */
	private static final String cs_INSERTAR = "INSERT INTO SDB_CON_CRPS(ID_ARCHIVO,ID_REGISTRO,ID_REGISTRO_PAGO,"
		+ "ID_SOLICITUD,FECHA_SOLICITUD,NIR,ID_PROCESO_SOLICITUD,NOMBRE_PROCESO,ID_SUBPROCESO_SOLICITUD,"
		+ "NOMBRE_SUBPROCESO,DIGITALIZADA,USUARIO_CREACION_SOLICITUD,ID_DOCUMENTO_TIPO,NUMERO_DOCUMENTO,"
		+ "ID_TIPO_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,RAZON_SOCIAL,ID_LIQUIDACION,"
		+ "CONSECUTIVO_LIQUIDACION,FECHA_LIQUIDACION,VALOR,VALOR_IMPUESTOS,VALOR_TOTAL,VALOR_CONSERVACION_DOCUMENTAL,"
		+ "NUMERO_REFERENCIA,ID_TIPO_CANAL,ID_ENTIDAD_RECAUDO,ID_SUCURSAL_RECAUDO,NUMERO_CUENTA_RECAUDO,"
		+ "ID_TIPO_RECAUDO,FECHA_BANCARIA,MONTO_TRANSACCION,CODIGO_TRANSACCION_RECAUDADOR,FECHA_TRANSACCION,"
		+ "NUMERO_RECIBO_CAJA,FECHA_GENERACION_RECIBO,ESTADO_PAGO,ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION)"
		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP)";

	/**
	 * Método que marca los registros como enviados los registros extraidos en CRPS para envio a conciliaciones
	 *
	 * @param as_idUsuarioModificacion Identificador del usuario que ejecuta la actualización
	 * @param as_ipModificacion IP desde donde se realiza la modificación
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarEnviadoConciliacion(String as_idUsuarioModificacion, String as_ipModificacion)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idUsuarioModificacion) && StringUtils.isValidString(as_ipModificacion))
		{
			int               li_columna;
			PreparedStatement lps_ps;

			li_columna     = 1;
			lps_ps         = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_ACTUALIZAR_ENVIADO_CONCILIACION);

				lps_ps.setString(li_columna++, as_idUsuarioModificacion);
				lps_ps.setString(li_columna++, as_ipModificacion);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarEnviadoConciliacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método que marca los registros pendientes de extracción en CRPS para envio a conciliaciones
	 *
	 * @param ad_fechaBancaria Fecha bancaria de los registros a obtener
	 * @param as_idUsuarioModificacion Identificador del usuario que ejecuta la actualización
	 * @param as_ipModificacion IP desde donde se realiza la modificación
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarParaEnvioConciliacion(
	    java.util.Date ad_fechaBancaria, String as_idUsuarioModificacion, String as_ipModificacion
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idUsuarioModificacion) && StringUtils.isValidString(as_ipModificacion))
		{
			int               li_columna;
			PreparedStatement lps_ps;

			li_columna     = 1;
			lps_ps         = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_ACTUALIZAR_PARA_ENVIO_CONCILIACION);

				lps_ps.setString(li_columna++, as_idUsuarioModificacion);
				lps_ps.setString(li_columna++, as_ipModificacion);
				lps_ps.setString(li_columna, StringUtils.getString(ad_fechaBancaria, "yyyy/MM/dd"));

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarParaEnvioConciliacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Obtiene los registros que se deben de servicios facturados y pagados que se deben llevar al módulo de
	 * conciliaciones
	 */
	public Collection<CRPSCabecera> consultarParaEnvioConciliacio()
	    throws B2BException
	{
		Collection<CRPSCabecera> lccrpsd_respuesta;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lccrpsd_respuesta     = null;
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			lps_ps                = getConnection().prepareStatement(cs_CONSULTAR_PARA_ENVIO_CONCILIACION);
			lrs_rs                = lps_ps.executeQuery();
			lccrpsd_respuesta     = new java.util.ArrayList<CRPSCabecera>();

			while(lrs_rs.next())
				lccrpsd_respuesta.add(obtener(lrs_rs));

			if(lccrpsd_respuesta.isEmpty())
				lccrpsd_respuesta = null;
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultarParaEnvioConciliacio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lccrpsd_respuesta;
	}

	/**
	 * Find by referencia.
	 *
	 * @param as_numeroReferencia the as numero referencia
	 * @return the CRPS cabecera
	 * @throws B2BException the b 2 B exception
	 */
	public int findByReferencia(String as_numeroReferencia)
	    throws B2BException
	{
		int               li_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_return     = 0;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_REFERENCIA);

			lps_ps.setString(1, as_numeroReferencia);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_return = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByReferencia", lse_e);

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
	 * Metodo para insertar registros en la base de datos de la tabla SDB_CON_CRPS
	 *  @param accrpsc_archivo Registros a insertar en la base de datos
	 */
	public void insertar(java.util.Collection<CRPSCabecera> accrpsc_archivo)
	    throws B2BException
	{
		if(com.b2bsg.common.util.CollectionUtils.isValidCollection(accrpsc_archivo))
		{
			java.sql.PreparedStatement lps_insercion;

			lps_insercion = null;

			try
			{
				lps_insercion = getConnection().prepareStatement(cs_INSERTAR);

				for(CRPSCabecera lcrpsc_linea : accrpsc_archivo)
				{
					if(lcrpsc_linea != null)
					{
						int li_column;

						li_column = 1;

						lps_insercion.clearParameters();

						lps_insercion.setString(li_column++, lcrpsc_linea.getIdArchivo());

						lps_insercion.setInt(li_column++, lcrpsc_linea.getRegistro());

						lps_insercion.setString(li_column++, lcrpsc_linea.getIdRegistroPago());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdSolicitud());

						setDate(lps_insercion, lcrpsc_linea.getFechaSolicitud(), li_column++);

						lps_insercion.setString(li_column++, lcrpsc_linea.getNir());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdProceso());
						lps_insercion.setString(li_column++, lcrpsc_linea.getNombreProceso());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdSubproceso());
						lps_insercion.setString(li_column++, lcrpsc_linea.getNombreSubproceso());
						lps_insercion.setString(li_column++, lcrpsc_linea.getDigitalizada());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdUsuarioCreacionSolicitud());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdDocumentoTipo());
						lps_insercion.setString(li_column++, lcrpsc_linea.getNumeroDocumento());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdTipoPersona());
						lps_insercion.setString(li_column++, lcrpsc_linea.getPrimerNombre());
						lps_insercion.setString(li_column++, lcrpsc_linea.getSegundoNombre());
						lps_insercion.setString(li_column++, lcrpsc_linea.getPrimerApellido());
						lps_insercion.setString(li_column++, lcrpsc_linea.getSegundoApellido());
						lps_insercion.setString(li_column++, lcrpsc_linea.getRazonSocial());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdLiquidacion());

						setInteger(lps_insercion, lcrpsc_linea.getConsecutivoLiquidacion(), li_column++);

						setDate(lps_insercion, lcrpsc_linea.getFechaLiquidacion(), li_column++);

						setDouble(lps_insercion, lcrpsc_linea.getValorLiquidacion(), li_column++);
						setDouble(lps_insercion, lcrpsc_linea.getValorImpuestoLiquidacion(), li_column++);
						setDouble(lps_insercion, lcrpsc_linea.getValorTotalLiquidacion(), li_column++);
						setDouble(lps_insercion, lcrpsc_linea.getValorConservacionDocumental(), li_column++);

						lps_insercion.setString(li_column++, lcrpsc_linea.getNumeroReferencia());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdTipoCanal());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdEntidadRecaudo());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdSucursalRecaudo());
						lps_insercion.setString(li_column++, lcrpsc_linea.getNumeroCuentaRecaudo());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIdTipoRecaudo());

						setDate(lps_insercion, lcrpsc_linea.getFechaBancaria(), li_column++);

						setDouble(lps_insercion, lcrpsc_linea.getMontoTransaccion(), li_column++);

						lps_insercion.setString(li_column++, lcrpsc_linea.getCodigoTransaccionRecaudador());

						setDate(lps_insercion, lcrpsc_linea.getFechaTransaccion(), li_column++);

						lps_insercion.setString(li_column++, lcrpsc_linea.getNumeroReciboCaja());

						setDate(lps_insercion, lcrpsc_linea.getFechaGeneracionRecibo(), li_column++);

						lps_insercion.setString(li_column++, lcrpsc_linea.getEstadoPago());

						lps_insercion.setString(li_column++, lcrpsc_linea.getIdUsuarioCreacion());
						lps_insercion.setString(li_column++, lcrpsc_linea.getIpCreacion());

						lps_insercion.executeUpdate();
					}
				}
			}
			catch(java.sql.SQLException lse_e)
			{
				logError(this, "insertar", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_insercion);
			}
		}
	}

	/**
	 * Metodo que se encarga de llenar un objeto de tipo CRPSCabecera con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto MulticashCabecera
	 * @return Objeto de tipo Archivo con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CRPSCabecera obtener(ResultSet ars_rs)
	    throws SQLException
	{
		CRPSCabecera lcrpsd_registro;

		lcrpsd_registro = new CRPSCabecera();

		lcrpsd_registro.setCodigoTransaccionRecaudador(ars_rs.getString("CODIGO_TRANSACCION_RECAUDADOR"));
		lcrpsd_registro.setConsecutivoLiquidacion(getInteger(ars_rs, "CONSECUTIVO_LIQUIDACION"));
		lcrpsd_registro.setDigitalizada(ars_rs.getString("DIGITALIZADA"));
		lcrpsd_registro.setEstadoPago(ars_rs.getString("ESTADO_PAGO"));
		lcrpsd_registro.setFechaBancaria(ars_rs.getDate("FECHA_BANCARIA"));
		lcrpsd_registro.setFechaGeneracionRecibo(ars_rs.getDate("FECHA_GENERACION_RECIBO"));
		lcrpsd_registro.setFechaLiquidacion(ars_rs.getDate("FECHA_LIQUIDACION"));
		lcrpsd_registro.setFechaSolicitud(ars_rs.getDate("FECHA_SOLICITUD"));
		lcrpsd_registro.setFechaTransaccion(ars_rs.getDate("FECHA_TRANSACCION"));
		lcrpsd_registro.setIdDocumentoTipo(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		lcrpsd_registro.setIdEntidadRecaudo(ars_rs.getString("ID_ENTIDAD_RECAUDO"));
		lcrpsd_registro.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		lcrpsd_registro.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lcrpsd_registro.setIdRegistroPago(ars_rs.getString("ID_REGISTRO_PAGO"));
		lcrpsd_registro.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lcrpsd_registro.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		lcrpsd_registro.setIdSucursalRecaudo(ars_rs.getString("ID_SUCURSAL_RECAUDO"));
		lcrpsd_registro.setIdTipoCanal(ars_rs.getString("ID_TIPO_CANAL"));
		lcrpsd_registro.setIdTipoPersona(ars_rs.getString("ID_TIPO_PERSONA"));
		lcrpsd_registro.setIdTipoRecaudo(ars_rs.getString("ID_TIPO_RECAUDO"));
		lcrpsd_registro.setIdUsuarioCreacionSolicitud(ars_rs.getString("ID_USUARIO_CREACION"));
		lcrpsd_registro.setMontoTransaccion(getDouble(ars_rs, "MONTO_TRANSACCION"));
		lcrpsd_registro.setNir(ars_rs.getString("NIR"));
		lcrpsd_registro.setNombreProceso(ars_rs.getString("NOMBRE_PROCESO"));
		lcrpsd_registro.setNombreSubproceso(ars_rs.getString("NOMBRE_SUBPROCESO"));
		lcrpsd_registro.setNumeroCuentaRecaudo(ars_rs.getString("NUMERO_CUENTA_RECAUDO"));
		lcrpsd_registro.setNumeroDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		lcrpsd_registro.setNumeroReciboCaja(ars_rs.getString("NUMERO_RECIBO_CAJA"));
		lcrpsd_registro.setNumeroReferencia(ars_rs.getString("NUMERO_REFERENCIA"));
		lcrpsd_registro.setPrimerApellido(ars_rs.getString("PRIMER_APELLIDO"));
		lcrpsd_registro.setPrimerNombre(ars_rs.getString("PRIMER_NOMBRE"));
		lcrpsd_registro.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
		lcrpsd_registro.setSegundoApellido(ars_rs.getString("SEGUNDO_APELLIDO"));
		lcrpsd_registro.setSegundoNombre(ars_rs.getString("SEGUNDO_NOMBRE"));
		lcrpsd_registro.setValorConservacionDocumental(getDouble(ars_rs, "VALOR_CONSERVACION_DOCUMENTAL"));
		lcrpsd_registro.setValorImpuestoLiquidacion(getDouble(ars_rs, "VALOR_IMPUESTO_LIQ"));
		lcrpsd_registro.setValorLiquidacion(getDouble(ars_rs, "VALOR_LIQ"));
		lcrpsd_registro.setValorTotalLiquidacion(getDouble(ars_rs, "VALOR_TOTAL_LIQ"));

		return lcrpsd_registro;
	}
}
