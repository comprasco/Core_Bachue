package com.bachue.snr.prosnr04.dao.npa;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.npa.DatosLiquidacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase DAO que extrae todos los atributos usados en la tabla SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO.
 *
 * @author Julian Vaca
 */
public class DatosLiquidacionDAO extends AuditoriaDao
{
	/** Constante cs_ACTUALIZAR_DATOS_SOLICITANTE. */
	private static final String cs_ACTUALIZAR_DATOS_SOLICITANTE = "UPDATE SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO SET ID_TIPO_PERSONA = ?, ID_DOCUMENTO_TIPO = ?, NUMERO_DOCUMENTO = ?, PRIMER_NOMBRE = ?, SEGUNDO_NOMBRE = ?, PRIMER_APELLIDO = ?, SEGUNDO_APELLIDO = ?, RAZON_SOCIAL = ?,ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_NUMERO_REFERENCIA = ?";

	/** Constante cs_ACTUALIZAR_ESTADO_RECIBO_CAJA. */
	private static final String cs_ACTUALIZAR_ESTADO_RECIBO_CAJA = "UPDATE SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO SET FECHA_RECIBO_CAJA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_NUMERO_REFERENCIA = ?";

	/** Constante cs_BUSCAR_POR_LLAVE. */
	private static final String cs_BUSCAR_POR_LLAVE = "SELECT * FROM SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO WHERE ID_NUMERO_REFERENCIA = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO WHERE ID_SOLICITUD = ?";

	/** Constante cs_INSERTAR_DATOS_LIQUIDACION. */
	private static final String cs_INSERTAR_DATOS_LIQUIDACION = "INSERT INTO SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO(ID_NUMERO_REFERENCIA, CODIGO_CONVENIO, ID_TIPO_CANAL_ORIGEN, CODIGO_SUCURSAL_CANAL, ID_CIRCULO, ID_TIPO_PERSONA, ID_DOCUMENTO_TIPO, NUMERO_DOCUMENTO, PRIMER_NOMBRE, SEGUNDO_NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, RAZON_SOCIAL, ID_SOLICITUD, ID_DOCUMENTO_SALIDA, FECHA_LIQUIDACION, FECHA_VENCIMIENTO, VALOR_TOTAL_SERVICIO,  ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,SYSTIMESTAMP,?) ";

	/**
	 * Find by id solicitud.
	 *
	 * @param ls_solicitud de ls solicitud
	 * @return el valor de datos liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosLiquidacion findByIdSolicitud(String ls_solicitud)
	    throws B2BException
	{
		DatosLiquidacion ldl_return;

		ldl_return = null;

		if(StringUtils.isValidString(ls_solicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(li_contador++, ls_solicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldl_return = getDatosLiquidacion(lrs_rs);
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

		return ldl_return;
	}

	/**
	 * Método que actualiza los datos del solicitante de la tabla SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO por la llave primaria de la tabla.
	 *
	 * @param adl_parametros Objeto de tipo DatosLiquidacion que contiene los parametros para realizar la actualizacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarDatosSolicitante(DatosLiquidacion adl_parametros)
	    throws B2BException
	{
		if(adl_parametros != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_ACTUALIZAR_DATOS_SOLICITANTE);

				lps_ps.setString(li_cont++, adl_parametros.getIdTipoPersona());
				lps_ps.setString(li_cont++, adl_parametros.getIdDocumentoTipo());
				lps_ps.setString(li_cont++, adl_parametros.getNumeroDocumento());
				lps_ps.setString(li_cont++, adl_parametros.getPrimerNombre());
				lps_ps.setString(li_cont++, adl_parametros.getSegundoNombre());
				lps_ps.setString(li_cont++, adl_parametros.getPrimerApellido());
				lps_ps.setString(li_cont++, adl_parametros.getSegundoApellido());
				lps_ps.setString(li_cont++, adl_parametros.getRazonSocial());
				lps_ps.setString(li_cont++, adl_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, adl_parametros.getIpModificacion());
				lps_ps.setString(li_cont++, adl_parametros.getIdNumeroReferencia());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarDatosSolicitante", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método que actualiza el estado del recibo de caja en la tabla SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO por la llave primaria de la tabla.
	 *
	 * @param adl_parametros Objeto de tipo DatosLiquidacion que contiene los parametros para realizar la actualizacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarEstadoReciboCaja(DatosLiquidacion adl_parametros)
	    throws B2BException
	{
		if(adl_parametros != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_ACTUALIZAR_ESTADO_RECIBO_CAJA);

				setDate(lps_ps, adl_parametros.getFechaReciboCaja(), li_cont++);
				lps_ps.setString(li_cont++, adl_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, adl_parametros.getIpModificacion());
				lps_ps.setString(li_cont++, adl_parametros.getIdNumeroReferencia());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarEstadoReciboCaja", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método que consulta SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO por la llave primaria de la tabla.
	 *
	 * @param adl_parametros Objeto de tipo DatosLiquidacion que contiene la llave primaria a consultar
	 * @return el valor de datos liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosLiquidacion findById(DatosLiquidacion adl_parametros)
	    throws B2BException
	{
		DatosLiquidacion  ldl_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ldl_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_BUSCAR_POR_LLAVE);

			lps_ps.setString(li_contador++, adl_parametros.getIdNumeroReferencia());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ldl_return = getDatosLiquidacion(lrs_rs);
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

		return ldl_return;
	}

	/**
	 * Método que inserta registro en la tabla SDB_ACC_DATOS_LIQUIDACION_NOTIFICACION_PAGO.
	 *
	 * @param adl_parametros Objeto de tipo DatosLiquidacion que contiene los parametros para realizar la insercion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertarDatosLiquidacion(DatosLiquidacion adl_parametros)
	    throws B2BException
	{
		if(adl_parametros != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERTAR_DATOS_LIQUIDACION);

				lps_ps.setString(li_cont++, adl_parametros.getIdNumeroReferencia());
				lps_ps.setString(li_cont++, adl_parametros.getCodigoConvenio());
				lps_ps.setString(li_cont++, adl_parametros.getIdTipoCanalOrigen());
				setLong(lps_ps, adl_parametros.getCodigoSucursalCanal(), li_cont++);
				lps_ps.setString(li_cont++, adl_parametros.getIdCirculo());
				lps_ps.setString(li_cont++, adl_parametros.getIdTipoPersona());
				lps_ps.setString(li_cont++, adl_parametros.getIdDocumentoTipo());
				lps_ps.setString(li_cont++, adl_parametros.getNumeroDocumento());
				lps_ps.setString(li_cont++, adl_parametros.getPrimerNombre());
				lps_ps.setString(li_cont++, adl_parametros.getSegundoNombre());
				lps_ps.setString(li_cont++, adl_parametros.getPrimerApellido());
				lps_ps.setString(li_cont++, adl_parametros.getSegundoApellido());
				lps_ps.setString(li_cont++, adl_parametros.getRazonSocial());
				lps_ps.setString(li_cont++, adl_parametros.getIdSolicitud());
				lps_ps.setLong(li_cont++, adl_parametros.getIdDocumentoSalida());
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(adl_parametros.getFechaVencimiento()));
				lps_ps.setDouble(li_cont++, adl_parametros.getValorTotalServicio());
				lps_ps.setString(li_cont++, adl_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, adl_parametros.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertarDatosLiquidacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna Objeto o variable de valor datos liquidacion.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de datos liquidacion
	 * @throws SQLException cuando se produce algun error en el proceso
	 */

	/* Retorna una nueva instancia del objeto DatosLiquidacion a partir de un ResultSet que se recibe como parametros.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de un objeto DatosLiquidacion
	 * @throws SQLException si hay algun error en la consulta
	 * @see com.bachue.snr.prosnr04.model.npa.DatosLiquidacion
	 */
	private DatosLiquidacion getDatosLiquidacion(ResultSet ars_rs)
	    throws SQLException
	{
		DatosLiquidacion ldl_datos;

		ldl_datos = new DatosLiquidacion();

		ldl_datos.setIdNumeroReferencia(ars_rs.getString("ID_NUMERO_REFERENCIA"));
		ldl_datos.setCodigoConvenio(ars_rs.getString("CODIGO_CONVENIO"));
		ldl_datos.setIdTipoCanalOrigen(ars_rs.getString("ID_TIPO_CANAL_ORIGEN"));
		ldl_datos.setCodigoSucursalCanal(getLong(ars_rs, "CODIGO_SUCURSAL_CANAL"));
		ldl_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ldl_datos.setIdTipoPersona(ars_rs.getString("ID_TIPO_PERSONA"));
		ldl_datos.setIdDocumentoTipo(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		ldl_datos.setNumeroDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		ldl_datos.setPrimerNombre(ars_rs.getString("PRIMER_NOMBRE"));
		ldl_datos.setSegundoNombre(ars_rs.getString("SEGUNDO_NOMBRE"));
		ldl_datos.setPrimerApellido(ars_rs.getString("PRIMER_APELLIDO"));
		ldl_datos.setSegundoApellido(ars_rs.getString("SEGUNDO_APELLIDO"));
		ldl_datos.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
		ldl_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		ldl_datos.setIdDocumentoSalida(ars_rs.getLong("ID_DOCUMENTO_SALIDA"));
		ldl_datos.setFechaLiquidacion(ars_rs.getTimestamp("FECHA_LIQUIDACION"));
		ldl_datos.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		ldl_datos.setFechaAnulacion(ars_rs.getTimestamp("FECHA_ANULACION"));
		ldl_datos.setFechaReciboCaja(ars_rs.getTimestamp("FECHA_RECIBO_CAJA"));
		ldl_datos.setValorTotalServicio(ars_rs.getDouble("VALOR_TOTAL_SERVICIO"));

		fillAuditoria(ars_rs, ldl_datos);

		return ldl_datos;
	}
}
