package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.RegistroMayorValor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Su objetivo es hacer peticiones y retornar resuLtados producto de estas, a la tabla
 * SDB_ACC_REGISTRO_MAYOR_VALOR.
 *
 * @author jvaca
 */
public class RegistroMayorValorDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_TURNO = "SELECT * FROM SDB_ACC_REGISTRO_MAYOR_VALOR WHERE ID_TURNO = ?";

	/** Constante cs_FIND_BY_TURNO_OR_SOLICITUD. */
	private static final String cs_FIND_BY_TURNO_OR_SOLICITUD = "SELECT * FROM SDB_ACC_REGISTRO_MAYOR_VALOR WHERE ";

	/** Constante cs_FIND_BY_TURNO_ACTO_SOLICITUD. */
	private static final String cs_FIND_BY_TURNO_ACTO_SOLICITUD = "SELECT * FROM SDB_ACC_REGISTRO_MAYOR_VALOR WHERE ID_TURNO = ? AND ID_ACTO = ? AND ID_SOLICITUD = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_REGISTRO_MAYOR_VALOR_ID_REGISTRO_MAYOR_VALOR.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_REGISTRO_MAYOR_VALOR (ID_REGISTRO_MAYOR_VALOR,"
		+ "ID_TIPO_MAYOR_VALOR,ID_SOLICITUD,ID_ACTO,ID_TURNO,ID_LIQUIDACION,CONSECUTIVO,VALOR_LIQUIDADO,"
		+ "FECHA_REGISTRO,CONCEPTO_COBRO_MAYOR_VALOR,ESTADO_MAYOR_VALOR,FECHA_CAMBIO_ESTADO,CUANTIA,VALOR_AVALUO,CANTIDAD_ACTOS,ID_USUARIO_CREACION,FECHA_CREACION,"
		+ "IP_CREACION,ID_TIPO_ADQUISICION,AREA_ACTO,AREA_TOTAL,GARANTIA_HIPOTECARIA,HIJUELA_PASIVO,ID_TIPO_TARIFA_REGISTRAL,VERSION_TARIFA_REGISTRAL,ORGANISMO_INTERNACIONAL,"
		+ "CANTIDAD_CERTIF_ANT_SISTEMA,MADRE_CABEZA,PORCENTAJE_TRANSFERENCIA,AREA_TRANSFERIR,AREA_TOTAL_INMUEBLE,RESPUESTA_LEY1943,PERIODICIDAD_CUANTIA,TIEMPO_CANON) "
		+ "VALUES (?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,SYSTIMESTAMP,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

	/** Constante cs_UPDATE_ESTADO_MAYOR_VALOR. */
	private static final String cs_UPDATE_ESTADO_MAYOR_VALOR = "UPDATE SDB_ACC_REGISTRO_MAYOR_VALOR SET ESTADO_MAYOR_VALOR = ?, FECHA_CAMBIO_ESTADO = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_CONCEPTO_MAYOR_VALOR. */
	private static final String cs_UPDATE_CONCEPTO_MAYOR_VALOR = "UPDATE SDB_ACC_REGISTRO_MAYOR_VALOR SET CONCEPTO_COBRO_MAYOR_VALOR = ?, ID_USUARIO_MODIFICACION = ?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_REGISTRO_MAYOR_VALOR = ?";

	/** Constante cs_UPDATE_DATOS_ACTO_MAYOR_VALOR. */
	private static final String cs_UPDATE_DATOS_ACTO_MAYOR_VALOR = "UPDATE SDB_ACC_REGISTRO_MAYOR_VALOR SET CONCEPTO_COBRO_MAYOR_VALOR = ?, "
		+ "CUANTIA = ?, VALOR_AVALUO = ?, CANTIDAD_ACTOS = ?, ID_TIPO_ADQUISICION = ?, AREA_ACTO = ?, AREA_TOTAL = ?, GARANTIA_HIPOTECARIA = ?, HIJUELA_PASIVO = ?, ID_TIPO_TARIFA_REGISTRAL = ?, "
		+ "VERSION_TARIFA_REGISTRAL = ?, ORGANISMO_INTERNACIONAL = ?, CANTIDAD_CERTIF_ANT_SISTEMA = ?, MADRE_CABEZA = ?, PORCENTAJE_TRANSFERENCIA = ?, AREA_TRANSFERIR = ?, AREA_TOTAL_INMUEBLE = ?,"
		+ "RESPUESTA_LEY1943 = ?, PERIODICIDAD_CUANTIA = ?, TIEMPO_CANON = ?, ID_USUARIO_MODIFICACION = ?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_REGISTRO_MAYOR_VALOR = ?";

	/** Constante cs_FIND_BY_TURNO_ACTO. */
	private static final String cs_FIND_BY_TURNO_ACTO = "SELECT MAY.*, LIQ.ID_TIPO_ESTADO_LIQUIDACION "
		+ "FROM SDB_ACC_REGISTRO_MAYOR_VALOR MAY "
		+ "INNER JOIN SDB_ACC_LIQUIDACION LIQ ON LIQ.ID_LIQUIDACION = MAY.ID_LIQUIDACION AND LIQ.CONSECUTIVO = MAY.CONSECUTIVO AND LIQ.ID_TIPO_ESTADO_LIQUIDACION = 2";

	/** Constante cs_FIND_BY_TURNO_ACTO_TAG. */
	private static final String cs_FIND_BY_TURNO_ACTO_TAG = "SELECT MAY.*, LIQ.ID_TIPO_ESTADO_LIQUIDACION "
		+ "FROM SDB_ACC_REGISTRO_MAYOR_VALOR MAY "
		+ "INNER JOIN SDB_ACC_LIQUIDACION LIQ ON LIQ.ID_LIQUIDACION = MAY.ID_LIQUIDACION AND LIQ.CONSECUTIVO = MAY.CONSECUTIVO ";

	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID = "DELETE FROM SDB_ACC_REGISTRO_MAYOR_VALOR WHERE ID_REGISTRO_MAYOR_VALOR = ?";

	/** Constante cs_DELETE_BY_ID_ACTO. */
	private static final String cs_DELETE_BY_ID_ACTO = "DELETE FROM SDB_ACC_REGISTRO_MAYOR_VALOR WHERE ID_ACTO = ?";

	/**
	 * Metodo encargado de borrar de la tabla SDB_ACC_REGISTRO_MAYOR_VALOR por ID_REGISTRO_MAYOR_VALOR.
	 *
	 * @param as_idRegistroMayorValor Argumento de tipo <code>String</code> que contiene el ID_REGISTRO_MAYOR_VALOR a borrar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteById(String as_idRegistroMayorValor)
	    throws B2BException
	{
		if(as_idRegistroMayorValor != null)
		{
			PreparedStatement lps_ps;
			Connection        lc_connection;

			lps_ps            = null;
			lc_connection     = getConnection();

			try
			{
				lps_ps = lc_connection.prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(1, as_idRegistroMayorValor);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de borrar de la tabla SDB_ACC_REGISTRO_MAYOR_VALOR por ID_ACTO.
	 *
	 * @param as_idActo Argumento de tipo <code>String</code> que contiene el ID_REGISTRO_MAYOR_VALOR a borrar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByIdActo(String as_idActo)
	    throws B2BException
	{
		if(as_idActo != null)
		{
			PreparedStatement lps_ps;
			Connection        lc_connection;

			lps_ps            = null;
			lc_connection     = getConnection();

			try
			{
				lps_ps = lc_connection.prepareStatement(cs_DELETE_BY_ID_ACTO);

				lps_ps.setString(1, as_idActo);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de consultar los registros de la tabla SDB_ACC_REGISTRO_MAYOR_VALOR por el idTurno.
	 *
	 * @param as_idTurno Objeto de tipo String que contiene el idTruno a consultar
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroMayorValor
	 */
	public Collection<RegistroMayorValor> findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<RegistroMayorValor> lcrmv_crmv;

		lcrmv_crmv = new ArrayList<RegistroMayorValor>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			lps_ps            = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			try
			{
				lps_ps = lc_connection.prepareStatement(cs_FIND_BY_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcrmv_crmv.add(getRegistroMayorValor(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lcrmv_crmv.isEmpty())
			lcrmv_crmv = null;

		return lcrmv_crmv;
	}

	/**
	 * Método encargado de consultar los registros de la tabla SDB_ACC_REGISTRO_MAYOR_VALOR por el idTurno y idSolicitud.
	 *
	 * @param as_idSolicitud Objeto de tipo String que contiene el idTurno a consultar
	 * @param as_idTurno Objeto de tipo String que contiene el idSolicitud a consultar
	 * @return Collección de RegistroMayorValor que contiene los registros que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroMayorValor
	 */
	public Collection<RegistroMayorValor> findByIdTurnoAndSolicitud(String as_idSolicitud, String as_idTurno)
	    throws B2BException
	{
		Collection<RegistroMayorValor> lcrmv_crmv;

		lcrmv_crmv = new ArrayList<RegistroMayorValor>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			Connection        lc_connection;
			StringBuilder     lsb_sb;

			lps_ps            = null;
			lrs_rs            = null;
			lc_connection     = getConnection();
			lsb_sb            = new StringBuilder(cs_FIND_BY_TURNO_OR_SOLICITUD);

			try
			{
				int li_contador;

				li_contador = 1;

				lsb_sb.append(" ID_SOLICITUD = ? AND ID_TURNO = ?");

				lps_ps = lc_connection.prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, as_idSolicitud);
				lps_ps.setString(li_contador++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcrmv_crmv.add(getRegistroMayorValor(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoAndSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lcrmv_crmv.isEmpty())
			lcrmv_crmv = null;

		return lcrmv_crmv;
	}

	/**
	 * Método encargado de consultar los registros de la tabla SDB_ACC_REGISTRO_MAYOR_VALOR por el idTurno.
	 *
	 * @param as_as Objeto de tipo String que contiene el idTruno a consultar
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroMayorValor
	 */
	public Collection<RegistroMayorValor> findByIdTurnoOrSolicitud(String as_as, boolean ab_b)
	    throws B2BException
	{
		Collection<RegistroMayorValor> lcrmv_crmv;

		lcrmv_crmv = new ArrayList<RegistroMayorValor>();

		if(StringUtils.isValidString(as_as))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			Connection        lc_connection;
			StringBuilder     lsb_sb;

			lps_ps            = null;
			lrs_rs            = null;
			lc_connection     = getConnection();
			lsb_sb            = new StringBuilder(cs_FIND_BY_TURNO_OR_SOLICITUD);

			try
			{
				if(ab_b)
					lsb_sb.append(" ID_TURNO = ?");
				else
					lsb_sb.append(" ID_SOLICITUD = ?");

				lps_ps = lc_connection.prepareStatement(lsb_sb.toString());

				lps_ps.setString(1, as_as);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcrmv_crmv.add(getRegistroMayorValor(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoOrSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lcrmv_crmv.isEmpty())
			lcrmv_crmv = null;

		return lcrmv_crmv;
	}

	/**
	 * Metodo encargado de consultar en registro mayor valor por ID_TURNO, ID_ACTO.
	 *
	 * @param armv_parametros Argumento de tipo RegistroMayorValor que contiene los criterios necesarios para realizar la búsqueda.
	 * @return Objeto de tipo RegistroMayorValor que contiene los registros que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroMayorValor
	 */
	public RegistroMayorValor findByTurnoActo(RegistroMayorValor armv_parametros)
	    throws B2BException
	{
		return findByTurnoActo(armv_parametros, false);
	}

	/**
	 * Metodo encargado de consultar en registro mayor valor por ID_TURNO, ID_ACTO.
	 *
	 * @param armv_parametros Argumento de tipo RegistroMayorValor que contiene los criterios necesarios para realizar la búsqueda.
	 * @return Objeto de tipo RegistroMayorValor que contiene los registros que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroMayorValor
	 */
	public RegistroMayorValor findByTurnoActo(RegistroMayorValor armv_parametros, boolean ab_firma)
	    throws B2BException
	{
		RegistroMayorValor lrmv_rmv;

		lrmv_rmv = null;

		if(armv_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			StringBuilder     lsb_sb;

			lps_ps     = null;
			lrs_rs     = null;
			lsb_sb     = new StringBuilder();

			try
			{
				int li_contador;

				li_contador = 1;

				lsb_sb.append(cs_FIND_BY_TURNO_ACTO_TAG);
				lsb_sb.append("WHERE MAY.ID_TURNO = ? AND MAY.ID_ACTO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, armv_parametros.getIdTurno());
				lps_ps.setString(li_contador++, armv_parametros.getIdActo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lrmv_rmv = getRegistroMayorValor(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		return lrmv_rmv;
	}

	/**
	 * Metodo encargado de consultar en registro mayor valor por ID_TURNO, ID_ACTO y ID_SOLICITUD.
	 *
	 * @param armv_parametros Argumento de tipo RegistroMayorValor que contiene los criterios necesarios para realizar la búsqueda.
	 * @return Objeto de tipo RegistroMayorValor que contiene los registros que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroMayorValor
	 */
	public RegistroMayorValor findByTurnoActoSolicitud(RegistroMayorValor armv_parametros)
	    throws B2BException
	{
		RegistroMayorValor lrmv_rmv;

		lrmv_rmv = null;

		if(armv_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_ACTO_SOLICITUD);

				lps_ps.setString(li_contador++, armv_parametros.getIdTurno());
				lps_ps.setString(li_contador++, armv_parametros.getIdActo());
				lps_ps.setString(li_contador++, armv_parametros.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lrmv_rmv = getRegistroMayorValor(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoActoSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		return lrmv_rmv;
	}

	/**
	 * Método encargado de insertar un registro en la tabla SDB_ACC_REGISTRO_MAYOR_VALOR.
	 *
	 * @param armv_param Objeto contenedor de parametros a ser insertado en la tabla SDB_ACC_REGISTRO_MAYOR_VALOR
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insert(RegistroMayorValor armv_param)
	    throws B2BException
	{
		long ll_secuencia;
		ll_secuencia = 0;

		if(armv_param != null)
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
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuencia = lrs_rs.getLong(1);
					lps_ps.setLong(li_column++, ll_secuencia);
				}

				lps_ps.setString(li_column++, armv_param.getIdTipoMayorValor());
				lps_ps.setString(li_column++, armv_param.getIdSolicitud());
				lps_ps.setString(li_column++, armv_param.getIdActo());
				lps_ps.setString(li_column++, armv_param.getIdTurno());
				lps_ps.setString(li_column++, armv_param.getIdLiquidacion());
				setLong(lps_ps, armv_param.getConsecutivo(), li_column++);
				setDouble(lps_ps, armv_param.getValorLiquidado(), li_column++);
				lps_ps.setString(li_column++, armv_param.getConceptoCobroMayorValor());
				lps_ps.setString(li_column++, armv_param.getEstadoMayorValor());
				lps_ps.setBigDecimal(li_column++, NumericUtils.getBigDecimal(armv_param.getCuantia()));
				lps_ps.setBigDecimal(li_column++, NumericUtils.getBigDecimal(armv_param.getValorAvaluo()));
				setLong(lps_ps, armv_param.getCantidadActos(), li_column++);
				lps_ps.setString(li_column++, armv_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, armv_param.getIpCreacion());
				lps_ps.setString(li_column++, armv_param.getIdTipoAdquisicion());
				setDouble(lps_ps, armv_param.getAreaActo(), li_column++);
				setDouble(lps_ps, armv_param.getAreaTotal(), li_column++);
				lps_ps.setString(li_column++, armv_param.getGarantiaHipotecaria());
				lps_ps.setString(li_column++, armv_param.getHijuelaPasivo());
				lps_ps.setString(li_column++, armv_param.getIdTipoTarifaRegistral());
				lps_ps.setLong(li_column++, armv_param.getVersionTarifaRegistral());
				lps_ps.setString(li_column++, armv_param.getOrganismoInternacional());
				setLong(lps_ps, armv_param.getCantidadCertifAntSistema(), li_column++);
				lps_ps.setString(li_column++, armv_param.getMadreCabeza());
				setDouble(lps_ps, armv_param.getPorcentajeTransferencia(), li_column++);
				setDouble(lps_ps, armv_param.getAreaTransferir(), li_column++);
				setDouble(lps_ps, armv_param.getAreaTotalInmueble(), li_column++);
				lps_ps.setString(li_column++, armv_param.getRespuestaLey1943());
				lps_ps.setString(li_column++, armv_param.getPeriodicidadCuantia());
				setLong(lps_ps, armv_param.getTiempoCanon(), li_column++);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

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
	 * Metodo encargado de actualizar el campo CONCEPTO_COBRO_MAYOR_VALOR en la tabla SDB_ACC_REGISTRO_MAYOR_VALOR.
	 *
	 * @param armv_param Argumento de tipo RegistroMayorValor que contiene los criterios necesarios para realizar la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateConceptoMayorValor(RegistroMayorValor armv_param)
	    throws B2BException
	{
		if(armv_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_CONCEPTO_MAYOR_VALOR);

				lps_ps.setString(li_column++, armv_param.getConceptoCobroMayorValor());
				lps_ps.setString(li_column++, armv_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, armv_param.getIpModificacion());
				lps_ps.setString(li_column++, armv_param.getIdRegistroMayorValor());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateConceptoMayorValor", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de actualizar los datos del acto en la tabla SDB_ACC_REGISTRO_MAYOR_VALOR.
	 *
	 * @param armv_param Argumento de tipo <code>RegistroMayorValor</code> que contiene los criterios necesarios para realizar la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateDatosActoMayorValor(RegistroMayorValor armv_param)
	    throws B2BException
	{
		if(armv_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_DATOS_ACTO_MAYOR_VALOR);

				lps_ps.setString(li_column++, armv_param.getConceptoCobroMayorValor());
				lps_ps.setBigDecimal(li_column++, NumericUtils.getBigDecimal(armv_param.getCuantia()));
				lps_ps.setBigDecimal(li_column++, NumericUtils.getBigDecimal(armv_param.getValorAvaluo()));
				setLong(lps_ps, armv_param.getCantidadActos(), li_column++);
				lps_ps.setString(li_column++, armv_param.getIdTipoAdquisicion());
				setDouble(lps_ps, armv_param.getAreaActo(), li_column++);
				setDouble(lps_ps, armv_param.getAreaTotal(), li_column++);
				lps_ps.setString(li_column++, armv_param.getGarantiaHipotecaria());
				lps_ps.setString(li_column++, armv_param.getHijuelaPasivo());
				lps_ps.setString(li_column++, armv_param.getIdTipoTarifaRegistral());
				lps_ps.setLong(li_column++, armv_param.getVersionTarifaRegistral());
				lps_ps.setString(li_column++, armv_param.getOrganismoInternacional());
				setLong(lps_ps, armv_param.getCantidadCertifAntSistema(), li_column++);
				lps_ps.setString(li_column++, armv_param.getMadreCabeza());
				setDouble(lps_ps, armv_param.getPorcentajeTransferencia(), li_column++);
				setDouble(lps_ps, armv_param.getAreaTransferir(), li_column++);
				setDouble(lps_ps, armv_param.getAreaTotalInmueble(), li_column++);
				lps_ps.setString(li_column++, armv_param.getRespuestaLey1943());
				lps_ps.setString(li_column++, armv_param.getPeriodicidadCuantia());
				setLong(lps_ps, armv_param.getTiempoCanon(), li_column++);
				lps_ps.setString(li_column++, armv_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, armv_param.getIpModificacion());
				lps_ps.setString(li_column++, armv_param.getIdRegistroMayorValor());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateDatosActoMayorValor", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el estado de los registros de la tabla SDB_ACC_REGISTRO_MAYOR_VALOR para un idTurno.
	 *
	 * @param armv_param Objeto contenedor de parametros a ser actualizados
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateEstadoMayorValor(RegistroMayorValor armv_param)
	    throws B2BException
	{
		if(armv_param != null)
		{
			PreparedStatement lps_ps;
			Connection        lc_connection;

			lps_ps            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;
				li_column     = 1;
				lps_ps        = lc_connection.prepareStatement(cs_UPDATE_ESTADO_MAYOR_VALOR);

				lps_ps.setString(li_column++, armv_param.getEstadoMayorValor());
				lps_ps.setString(li_column++, armv_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, armv_param.getIpModificacion());
				lps_ps.setString(li_column++, armv_param.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateEstadoMayorValor", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Su función es obtener los datos que retornó una consulta y almacenarlos en los atributos de un objeto RegistroMayorValor.
	 *
	 * @param ars_rs Objeto contenedor de los datos resultantes de una consulta
	 * determinada
	 * @return el valor de registro mayor valor
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroMayorValor getRegistroMayorValor(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroMayorValor lrmv_rmv;

		lrmv_rmv = new RegistroMayorValor();

		lrmv_rmv.setIdRegistroMayorValor(ars_rs.getString("ID_REGISTRO_MAYOR_VALOR"));
		lrmv_rmv.setIdTipoMayorValor(ars_rs.getString("ID_TIPO_MAYOR_VALOR"));
		lrmv_rmv.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lrmv_rmv.setIdActo(ars_rs.getString("ID_ACTO"));
		lrmv_rmv.setIdTurno(ars_rs.getString("ID_TURNO"));
		lrmv_rmv.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		lrmv_rmv.setConsecutivo(getLong(ars_rs, "CONSECUTIVO"));
		lrmv_rmv.setValorLiquidado(getDouble(ars_rs, "VALOR_LIQUIDADO"));
		lrmv_rmv.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lrmv_rmv.setConceptoCobroMayorValor(ars_rs.getString("CONCEPTO_COBRO_MAYOR_VALOR"));
		lrmv_rmv.setEstadoMayorValor(ars_rs.getString("ESTADO_MAYOR_VALOR"));
		lrmv_rmv.setCuantia(ars_rs.getBigDecimal("CUANTIA"));
		lrmv_rmv.setValorAvaluo(ars_rs.getBigDecimal("VALOR_AVALUO"));
		lrmv_rmv.setCantidadActos(NumericUtils.getLongWrapper(ars_rs.getLong("CANTIDAD_ACTOS")));
		lrmv_rmv.setFechaCambioEstado(ars_rs.getTimestamp("FECHA_CAMBIO_ESTADO"));
		lrmv_rmv.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lrmv_rmv.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lrmv_rmv.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lrmv_rmv.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lrmv_rmv.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lrmv_rmv.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lrmv_rmv.setIdTipoAdquisicion(ars_rs.getString("ID_TIPO_ADQUISICION"));
		lrmv_rmv.setAreaActo(getDouble(ars_rs, "AREA_ACTO"));
		lrmv_rmv.setAreaTotal(getDouble(ars_rs, "AREA_TOTAL"));
		lrmv_rmv.setGarantiaHipotecaria(ars_rs.getString("GARANTIA_HIPOTECARIA"));
		lrmv_rmv.setHijuelaPasivo(ars_rs.getString("HIJUELA_PASIVO"));
		lrmv_rmv.setIdTipoTarifaRegistral(ars_rs.getString("ID_TIPO_TARIFA_REGISTRAL"));
		lrmv_rmv.setVersionTarifaRegistral(ars_rs.getLong("VERSION_TARIFA_REGISTRAL"));
		lrmv_rmv.setOrganismoInternacional(ars_rs.getString("ORGANISMO_INTERNACIONAL"));
		lrmv_rmv.setCantidadCertifAntSistema(getLong(ars_rs, "CANTIDAD_CERTIF_ANT_SISTEMA"));
		lrmv_rmv.setMadreCabeza(ars_rs.getString("MADRE_CABEZA"));
		lrmv_rmv.setPorcentajeTransferencia(getDouble(ars_rs, "PORCENTAJE_TRANSFERENCIA"));
		lrmv_rmv.setAreaTransferir(getDouble(ars_rs, "AREA_TRANSFERIR"));
		lrmv_rmv.setAreaTotalInmueble(getDouble(ars_rs, "AREA_TOTAL_INMUEBLE"));
		lrmv_rmv.setRespuestaLey1943(ars_rs.getString("RESPUESTA_LEY1943"));
		lrmv_rmv.setPeriodicidadCuantia(ars_rs.getString("PERIODICIDAD_CUANTIA"));
		lrmv_rmv.setTiempoCanon(getLong(ars_rs, "TIEMPO_CANON"));

		return lrmv_rmv;
	}
}
