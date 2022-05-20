package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.model.pgn.MulticashDetalle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_MULTICASH_DETALLE del módulo de conciliaciones.
 *
 * @author Edgar Prieto
 */
public class MulticashDetalleDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/**  Sentencia SQL para la inserción de un registro. */
	private static final String cs_INSERTAR = "INSERT INTO SDB_CON_MULTICASH_DETALLE(ID_ARCHIVO,ID_REGISTRO,"
		+ "ID_ARCHIVO_CABECERA,ID_CUENTA,ID_PERIODO_CORTE,IDENTIFICADOR_CREDITO_CUENTA,CLAVE_TRANSACCION,NUMERO_CHEQUE,"
		+ "MONTO,FECHA_AFECTACION_SALDO,REFERENCIA_NIT,TIPO_RECAUDO,REFERENCIA,CAUSAL_RECHAZO,CODIGO_UNICO_TRANSACCION,"
		+ "FORMATO_CONSIGNACION,ESTADO,ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,"
		+ "?,?,?,?,?,?,SYSTIMESTAMP)";

	/**  Sentencia Sql para la consulta de registros para el subproceso de conciliacion de partidas tipo A. */
	private static final String cs_CONSULTA_PARTIDAS_TIPO_A = "SELECT ID_ARCHIVO, ID_REGISTRO, ID_CUENTA, ID_ARCHIVO_CABECERA, ID_PERIODO_CORTE, "
		+ "MONTO, REFERENCIA_NIT, REFERENCIA, AFECTACION, PRESTACION_SERVICIO, OBSERVACIONES_PARTIDAS_TIPO_A, CAMPO_TIPO_A, NUMERO_SIIF, FECHA_SIIF "
		+ "FROM SDB_CON_MULTICASH_DETALLE WHERE CONCILIADO_ANALISTA = 'N' AND ID_CUENTA = ? AND ID_PERIODO_CORTE = ?";

	/** Constante cs_FIND_VISTA. */
	private static final String cs_FIND_VISTA = "SELECT * FROM VW_TMP_MULTICASH_DETALLE WHERE TRUNC(FECHA_ORIGEN_MOVIMIENTO) = TRUNC(TO_DATE(?,'DD/MM/YY')) AND CLAVE_BANCO=? AND CUENTA_BANCARIA=?";

	/** Constante cs_FIND_BY_MU_CABECERA. */
	private static final String cs_FIND_BY_MU_CABECERA = "SELECT ID_ARCHIVO, ID_REGISTRO, ID_CUENTA, ID_ARCHIVO_CABECERA, ID_PERIODO_CORTE, MONTO, "
		+ "REFERENCIA_NIT, REFERENCIA, AFECTACION, PRESTACION_SERVICIO, OBSERVACIONES_PARTIDAS_TIPO_A, CAMPO_TIPO_A, NUMERO_SIIF, FECHA_SIIF "
		+ "FROM SDB_CON_MULTICASH_DETALLE WHERE ID_ARCHIVO_CABECERA=?";

	/**  Constante cs_CONSULTA_PARTIDAS_TIPO_A_BY_CUENTA_AND_FECHA. */
	private static final String cs_CONSULTA_PARTIDAS_TIPO_A_BY_CUENTA_AND_FECHA = "SELECT CMD.ID_ARCHIVO, CMD.ID_REGISTRO, CMD.ID_CUENTA, CMD.ID_ARCHIVO_CABECERA, CMD.ID_PERIODO_CORTE, CMD.MONTO, CMD.REFERENCIA_NIT, CMD.REFERENCIA, CMD.AFECTACION, CMD.PRESTACION_SERVICIO, CMD.OBSERVACIONES_PARTIDAS_TIPO_A, CMD.CAMPO_TIPO_A, CMD.NUMERO_SIIF, CMD.FECHA_SIIF FROM SDB_CON_MULTICASH_DETALLE CMD INNER JOIN SDB_PGN_PERIODO_CORTE PC ON PC.ID_PERIODO_CORTE = CMD.ID_PERIODO_CORTE WHERE CMD.CAMPO_TIPO_A = 'S' AND CMD.ID_CUENTA = ? AND TRUNC(PC.DIA_CORTE) = TRUNC(TO_DATE(?, 'DD/MM/YY'))";

	/**
	 * Método de consulta de las partidas tipo A de multicash detalle.
	 *
	 * @param as_idCuenta con el valor a la cuenta a consultar
	 * @param as_idPeriodoCorte  con el valor del periodo corte de consulta
	 * @return una colección de tipo Multicash Detalle con la data generada por la consulta
	 * @throws B2BException en caso de error
	 */
	public Collection<MulticashDetalle> findAllPartidasTipoAByCuentaPeriodo(
	    String as_idCuenta, String as_idPeriodoCorte
	)
	    throws B2BException
	{
		Collection<MulticashDetalle> lcmd_trazabilidad;

		lcmd_trazabilidad = new ArrayList<MulticashDetalle>();

		if(StringUtils.isValidString(as_idCuenta) && StringUtils.isValidString(as_idPeriodoCorte))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_sentencia;

				li_contador       = 1;
				lsb_sentencia     = new StringBuilder();

				lsb_sentencia.append(cs_CONSULTA_PARTIDAS_TIPO_A);

				lps_ps = getConnection().prepareStatement(StringUtils.getString(lsb_sentencia));

				lps_ps.setString(li_contador++, as_idCuenta);
				lps_ps.setString(li_contador++, as_idPeriodoCorte);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcmd_trazabilidad.add(getMulticashDetalle(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllPartidasTipoAByCuentaPeriodo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcmd_trazabilidad.isEmpty())
			lcmd_trazabilidad = null;

		return lcmd_trazabilidad;
	}

	/**
	 * Find by mu cabecera.
	 *
	 * @param as_idArchivo the as id archivo
	 * @return the multicash detalle
	 * @throws B2BException the b 2 B exception
	 */
	public MulticashDetalle findByMuCabecera(String as_idArchivo)
	    throws B2BException
	{
		MulticashDetalle lmd_return;

		lmd_return = null;

		if(StringUtils.isValidString(as_idArchivo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_MU_CABECERA);

				lps_ps.setString(1, as_idArchivo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmd_return = getMulticashDetalle(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByMuCabecera", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lmd_return;
	}

	/**
	 * Find multicash detalle by cuenta and fecha.
	 *
	 * @param as_idCuenta the as id cuenta
	 * @param ad_fecha the ad fecha
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<MulticashDetalle> findMulticashDetalleByCuentaAndFecha(String as_idCuenta, Date ad_fecha)
	    throws B2BException
	{
		Collection<MulticashDetalle> lcmd_datos;

		lcmd_datos = new ArrayList<MulticashDetalle>();

		if(StringUtils.isValidString(as_idCuenta) && (ad_fecha != null))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_sentencia;

				li_contador       = 1;
				lsb_sentencia     = new StringBuilder(cs_CONSULTA_PARTIDAS_TIPO_A_BY_CUENTA_AND_FECHA);

				lps_ps = getConnection().prepareStatement(StringUtils.getString(lsb_sentencia));

				lps_ps.setString(li_contador++, as_idCuenta);
				setDate(lps_ps, ad_fecha, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcmd_datos.add(getMulticashDetalle(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllPartidasTipoAByCuentaPeriodo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcmd_datos.isEmpty())
			lcmd_datos = null;

		return lcmd_datos;
	}

	/**
	 * Find vista.
	 *
	 * @param ad_fecha the ad fecha
	 * @param as_claveBanco the as clave banco
	 * @param as_cuenta the as cuenta
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<MulticashDetalle> findVista(Date ad_fecha, String as_claveBanco, String as_cuenta)
	    throws B2BException
	{
		Collection<MulticashDetalle> lcmd_trazabilidad;

		lcmd_trazabilidad = new ArrayList<MulticashDetalle>();

		if(StringUtils.isValidString(as_claveBanco) && StringUtils.isValidString(as_cuenta) && (ad_fecha != null))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_VISTA);

				setDate(lps_ps, ad_fecha, li_contador++);
				lps_ps.setString(li_contador++, as_claveBanco);
				lps_ps.setString(li_contador++, as_cuenta);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcmd_trazabilidad.add(getObjectFromResult(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findVista", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcmd_trazabilidad.isEmpty())
			lcmd_trazabilidad = null;

		return lcmd_trazabilidad;
	}

	/**
	 * Metodo para insertar registros en la base de datos de la tabla SDB_CON_MULTICASH_DETALLE.
	 *
	 * @param acmd_archivo Registros a insertar en la base de datos
	 * @throws B2BException the b 2 B exception
	 */
	public void insertar(java.util.Collection<MulticashDetalle> acmd_archivo)
	    throws B2BException
	{
		if(com.b2bsg.common.util.CollectionUtils.isValidCollection(acmd_archivo))
		{
			java.sql.PreparedStatement lps_insercion;

			lps_insercion = null;

			try
			{
				lps_insercion = getConnection().prepareStatement(cs_INSERTAR);

				for(MulticashDetalle lmd_linea : acmd_archivo)
				{
					if(lmd_linea != null)
					{
						int li_column;

						li_column = 1;

						lps_insercion.clearParameters();

						lps_insercion.setString(li_column++, lmd_linea.getIdArchivo());

						lps_insercion.setInt(li_column++, lmd_linea.getRegistro());

						lps_insercion.setString(li_column++, lmd_linea.getIdArchivoCabecera());
						lps_insercion.setString(li_column++, lmd_linea.getIdCuenta());
						lps_insercion.setString(li_column++, lmd_linea.getIdPeriodoCorte());
						lps_insercion.setString(li_column++, lmd_linea.getIdentificadorCredito());
						lps_insercion.setString(li_column++, lmd_linea.getClaveTransaccion());
						lps_insercion.setString(li_column++, lmd_linea.getNumeroCheque());

						setDouble(lps_insercion, lmd_linea.getMonto(), li_column++);

						setDate(lps_insercion, lmd_linea.getFechaAfectacionSaldo(), li_column++);

						lps_insercion.setString(li_column++, lmd_linea.getReferenciaNit());
						lps_insercion.setString(li_column++, lmd_linea.getTipoRecaudo());
						lps_insercion.setString(li_column++, lmd_linea.getReferencia());
						lps_insercion.setString(li_column++, lmd_linea.getCausalRechazo());
						lps_insercion.setString(li_column++, lmd_linea.getCodigoUnicoTransaccion());
						lps_insercion.setString(li_column++, lmd_linea.getFormatoConsignacion());
						lps_insercion.setString(li_column++, lmd_linea.getEstado());
						lps_insercion.setString(li_column++, lmd_linea.getIdUsuarioCreacion());
						lps_insercion.setString(li_column++, lmd_linea.getIpCreacion());

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
	 * Su función es obtener los datos que retornó una consulta y almacenarlos
	 * en los atributos de un objeto TurnoHistoria.
	 *
	 * @param ars_rs Objeto contenedor de los datos resultantes de una consulta
	 * determinada
	 * @return Objeto TurnoHistoria al cual se le asignará la información de la
	 * consulta en sus atributos
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private MulticashDetalle getMulticashDetalle(ResultSet ars_rs)
	    throws SQLException
	{
		MulticashDetalle lmcd_datos;

		lmcd_datos = new MulticashDetalle();

		lmcd_datos.setIdArchivo(ars_rs.getString("ID_ARCHIVO"));
		lmcd_datos.setRegistro(ars_rs.getInt("ID_REGISTRO"));
		lmcd_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lmcd_datos.setIdArchivoCabecera(ars_rs.getString("ID_ARCHIVO_CABECERA"));
		lmcd_datos.setIdPeriodoCorte(ars_rs.getString("ID_PERIODO_CORTE"));
		lmcd_datos.setMonto(getDouble(ars_rs, "MONTO"));
		lmcd_datos.setReferenciaNit(ars_rs.getString("REFERENCIA_NIT"));
		lmcd_datos.setReferencia(ars_rs.getString("REFERENCIA"));
		lmcd_datos.setAfectacion(ars_rs.getString("AFECTACION"));
		lmcd_datos.setPrestacionServicio(ars_rs.getString("PRESTACION_SERVICIO"));
		lmcd_datos.setObservacionesPartidasTipoA(ars_rs.getString("OBSERVACIONES_PARTIDAS_TIPO_A"));
		lmcd_datos.setCamposTipoA(ars_rs.getString("CAMPO_TIPO_A"));
		lmcd_datos.setNumeroSIIF(ars_rs.getString("NUMERO_SIIF"));
		lmcd_datos.setFechaSIIF(ars_rs.getDate("FECHA_SIIF"));

		return lmcd_datos;
	}

	/**
	 * Gets the object from result.
	 *
	 * @param ars_rs the ars rs
	 * @return the object from result
	 * @throws SQLException the SQL exception
	 */
	private MulticashDetalle getObjectFromResult(ResultSet ars_rs)
	    throws SQLException
	{
		MulticashDetalle lmd_return;

		lmd_return = new MulticashDetalle();

		lmd_return.setClaveBanco(ars_rs.getString("CLAVE_BANCO"));
		lmd_return.setCuentaBancaria(ars_rs.getString("CUENTA_BANCARIA"));
		lmd_return.setFechaOrigenMovimiento(ars_rs.getDate("FECHA_ORIGEN_MOVIMIENTO"));
		lmd_return.setFila(ars_rs.getString("FILA"));

		return lmd_return;
	}
}
