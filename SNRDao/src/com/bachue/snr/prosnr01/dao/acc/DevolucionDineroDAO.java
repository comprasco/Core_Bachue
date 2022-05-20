package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_DEVOLUCION_DINERO.
 *
 * @author ccalderon
 */
public class DevolucionDineroDAO extends AuditoriaDao
{
	/**  Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_DEVOLUCION_DINERO (ID_DEVOLUCION_DINERO, ID_SOLICITUD, ID_TURNO_DEVOLUCION, ID_TURNO, ID_PERSONA_INTERVINIENTE, INTERVIENE_TRAMITE,TITULAR_CUENTA, ID_ENTIDAD_RECAUDADORA, TIPO_CUENTA, NUMERO_CUENTA, ID_PERSONA_TITULAR_CUENTA, VALOR_TOTAL_ACTO_DEVOLUCION, TIPO_CONSIGNACION, NUMERO_CONSIGNACION_ERRADA, COD_ENTIDAD_CONSIGNACION_ERRADA, FECHA_CONSIGNACION_ERRADA, NUMERO_CUENTA_CONSIGNACION_ERRADA, VALOR_CONSIGNACION_ERRADA, TIPO_DEVOLUCION, CODIGO_CUENTA_CUPO, CODIGO_NOTA_CREDITO, TOKEN_VERIFICACION, EXTEMPORANEO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, TIPO_TELEFONO_FIJO_TITULAR, TIPO_TELEFONO_MOVIL_TITULAR) VALUES (?, ? ,? ,?, ? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?, ?)";

	/**  Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_DEVOLUCION_DINERO_ID_DEVOLUCION_DINERO.NEXTVAL FROM DUAL";

	/**  Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_DEVOLUCION_DINERO WHERE ID_SOLICITUD = ?";

	/**  Constante cs_FIND_BY_ID_TURNO_DEVOLUCION. */
	private static final String cs_FIND_BY_ID_TURNO_DEVOLUCION = "SELECT * FROM SDB_ACC_DEVOLUCION_DINERO WHERE ID_TURNO_DEVOLUCION = ?";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT * FROM SDB_ACC_DEVOLUCION_DINERO WHERE ID_TURNO = ?";

	/**  Constante cs_FIND_BY_NUMERO_CONSIGNACION_ERRADA. */
	private static final String cs_FIND_BY_NUMERO_CONSIGNACION_ERRADA = "SELECT * FROM SDB_ACC_DEVOLUCION_DINERO WHERE NUMERO_CONSIGNACION_ERRADA = ?";

	/** Constante cs_FIND_ALL_BY_NUMERO_CONSIGNACION_ERRADA. */
	private static final String cs_FIND_ALL_BY_NUMERO_CONSIGNACION_ERRADA = "SELECT * FROM SDB_ACC_DEVOLUCION_DINERO WHERE NUMERO_CONSIGNACION_ERRADA = ?";

	/**  Constante cs_UPDATE_BY_ID_SOLICITUD. */
	private static final String cs_UPDATE_BY_ID_SOLICITUD = "UPDATE SDB_ACC_DEVOLUCION_DINERO SET ID_DEVOLUCION_DINERO = ?, ID_TURNO_DEVOLUCION = ?, ID_TURNO = ?, ID_PERSONA_INTERVINIENTE = ?, INTERVIENE_TRAMITE = ?, TITULAR_CUENTA = ?, ID_ENTIDAD_RECAUDADORA = ?, TIPO_CUENTA = ?, NUMERO_CUENTA = ?, ID_PERSONA_TITULAR_CUENTA = ?, TIPO_CONSIGNACION = ?, NUMERO_CONSIGNACION_ERRADA = ?, COD_ENTIDAD_CONSIGNACION_ERRADA = ?, FECHA_CONSIGNACION_ERRADA = ?, NUMERO_CUENTA_CONSIGNACION_ERRADA = ?, VALOR_CONSIGNACION_ERRADA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, "
		+ " ID_CORREO_ELECTRONICO_TITULAR = ?, ID_TELEFONO_MOVIL_TITULAR = ?, ID_TELEFONO_FIJO_TITULAR = ? WHERE ID_SOLICITUD = ?";

	/**
	 * Método encargado de consultar todos los registros por número de consignacion errada.
	 *
	 * @param as_numeroConsignacionErrada de as numero consignacion errada
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DevolucionDinero> findAllByNumeroConsignacionErrada(String as_numeroConsignacionErrada)
	    throws B2BException
	{
		Collection<DevolucionDinero> ldd_return;

		ldd_return = new LinkedList<DevolucionDinero>();

		if(StringUtils.isValidString(as_numeroConsignacionErrada))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_NUMERO_CONSIGNACION_ERRADA);

				lps_ps.setString(1, as_numeroConsignacionErrada);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ldd_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByNumeroConsignacionErrada", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ldd_return.isEmpty())
			ldd_return = null;

		return ldd_return;
	}

	/**
	 * Busca un registro asociado a una solicitud específica.
	 *
	 * @param as_idSolicitud id de la solicitud a utilizar como filtro en la busqueda
	 * @return DevolucionDinero resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public DevolucionDinero findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		DevolucionDinero ldd_return;

		ldd_return = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldd_return = getObjectFromResultSet(lrs_rs);
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

		return ldd_return;
	}

	/**
	 * Busca un registro asociado a un TURNO específica.
	 *
	 * @param as_idTurno id del turno a utilizar como filtro en la busqueda
	 * @return DevolucionDinero resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public DevolucionDinero findByIdturno(String as_idTurno)
	    throws B2BException
	{
		DevolucionDinero ldd_return;

		ldd_return = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldd_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdturno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ldd_return;
	}

	/**
	 * Busca un registro asociado a un TURNO_DEVOLUCION específica.
	 *
	 * @param as_idTurnoDevolucion id de la solicitud a utilizar como filtro en la busqueda
	 * @return DevolucionDinero resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public DevolucionDinero findByIdturnoDevolucion(String as_idTurnoDevolucion)
	    throws B2BException
	{
		DevolucionDinero ldd_return;

		ldd_return = null;

		if(StringUtils.isValidString(as_idTurnoDevolucion))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_DEVOLUCION);

				lps_ps.setString(1, as_idTurnoDevolucion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldd_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdturnoDevolucion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ldd_return;
	}

	/**
	 * Busca todos los registros de devolución de dinero que coincidan con número de consignación.
	 *
	 * @param as_numeroConsignacionErrada Argumento de tipo <code>String</code> que contiene el número de consignación a consultar.
	 * @return Listado de registros que cumplen con los criterios de búsqueda.
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public Collection<DevolucionDinero> findByNumeroConsignacionErrada(String as_numeroConsignacionErrada)
	    throws B2BException
	{
		Collection<DevolucionDinero> lcdd_return;

		lcdd_return = new ArrayList<DevolucionDinero>();

		if(StringUtils.isValidString(as_numeroConsignacionErrada))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_NUMERO_CONSIGNACION_ERRADA);

				lps_ps.setString(1, as_numeroConsignacionErrada);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdd_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNumeroConsignacionErrada", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcdd_return.isEmpty())
			lcdd_return = null;

		return lcdd_return;
	}

	/**
	 * Método para insertar registros en la tabla.
	 *
	 * @param add_parametros Objeto a insertar
	 * @return String identificador del objeto insertado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String insert(DevolucionDinero add_parametros)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(add_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
				lrs_rs            = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;

					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
					{
						add_parametros.setIdDevolucionDinero(((BigDecimal)lo_o).toString());

						lps_ps.setString(li_column++, add_parametros.getIdDevolucionDinero());

						ls_return = add_parametros.getIdDevolucionDinero();
					}
					else
						throw new B2BException(ErrorKeys.ACC_DEVOLUCION_DINERO_SEQUENCE);
				}

				lps_ps.setString(li_column++, add_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, add_parametros.getIdTurnoDevolucion());
				lps_ps.setString(li_column++, add_parametros.getIdTurno());
				lps_ps.setString(li_column++, add_parametros.getIdPersonaInterviniente());
				lps_ps.setString(li_column++, add_parametros.getIntervieneTramite());
				lps_ps.setString(li_column++, add_parametros.getTitularCuenta());
				lps_ps.setString(li_column++, add_parametros.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, add_parametros.getTipoCuenta());
				lps_ps.setString(li_column++, add_parametros.getNumeroCuenta());
				lps_ps.setString(li_column++, add_parametros.getIdPersonaTitularCuenta());
				lps_ps.setLong(li_column++, add_parametros.getValorTotalActoDevolucion());
				lps_ps.setString(li_column++, add_parametros.getTipoConsignacion());
				lps_ps.setString(li_column++, add_parametros.getNumeroConsignacionErrada());
				lps_ps.setString(li_column++, add_parametros.getCodEntidadConsignacionErrada());
				setDate(lps_ps, add_parametros.getFechaConsignacionErrada(), li_column++);
				lps_ps.setString(li_column++, add_parametros.getNumeroCuentaConsignacionErrada());
				setDouble(lps_ps, add_parametros.getValorConsignacionErrada(), li_column++);
				lps_ps.setString(li_column++, add_parametros.getTipoDevolucion());
				lps_ps.setString(li_column++, add_parametros.getCodigoCuentaCupo());
				lps_ps.setString(li_column++, add_parametros.getCodigoNotaCredito());
				setInteger(lps_ps, add_parametros.getTokenVerificacion(), li_column++);
				lps_ps.setString(li_column++, add_parametros.getExtemporaneo());
				lps_ps.setString(li_column++, add_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, add_parametros.getIpCreacion());
				lps_ps.setString(li_column++, add_parametros.getTipoTelefonoFijoTitular());
				lps_ps.setString(li_column++, add_parametros.getTipoTelefonoMovilTitular());

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

		return ls_return;
	}

	/**
	 * Calcula la secuencia de un nuevo registro e inserta el registro en la tabla, o actualiza un registro ya
	 * existente.
	 *
	 * @param add_dd de add dd
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(DevolucionDinero add_dd)
	    throws B2BException
	{
		if(add_dd != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_BY_ID_SOLICITUD);

				lps_ps.setString(li_column++, add_dd.getIdDevolucionDinero());
				lps_ps.setString(li_column++, add_dd.getIdTurnoDevolucion());
				lps_ps.setString(li_column++, add_dd.getIdTurno());
				lps_ps.setString(li_column++, add_dd.getIdPersonaInterviniente());
				lps_ps.setString(li_column++, add_dd.getIntervieneTramite());
				lps_ps.setString(li_column++, add_dd.getTitularCuenta());
				lps_ps.setString(li_column++, add_dd.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, add_dd.getTipoCuenta());
				lps_ps.setString(li_column++, add_dd.getNumeroCuenta());
				lps_ps.setString(li_column++, add_dd.getIdPersonaTitularCuenta());
				lps_ps.setString(li_column++, add_dd.getTipoConsignacion());
				lps_ps.setString(li_column++, add_dd.getNumeroConsignacionErrada());
				lps_ps.setString(li_column++, add_dd.getCodEntidadConsignacionErrada());
				setDate(lps_ps, add_dd.getFechaConsignacionErrada(), li_column++);
				lps_ps.setString(li_column++, add_dd.getNumeroCuentaConsignacionErrada());
				setDouble(lps_ps, add_dd.getValorConsignacionErrada(), li_column++);
				lps_ps.setString(li_column++, add_dd.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, add_dd.getIpModificacion());
				lps_ps.setString(li_column++, add_dd.getIdCorreoElectronicoTitular());
				lps_ps.setString(li_column++, add_dd.getIdTelefonoMovilTitular());
				lps_ps.setString(li_column++, add_dd.getIdTelefonoFijoTitular());
				lps_ps.setString(li_column++, add_dd.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Extrae los resultados de una consulta en un objeto DevolucionDinero.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return DevolucionDinero con los datos obtenidos por la consulta
	 * @throws SQLException Si ocurre un error en la extracción de los datos de la consulta
	 */
	private DevolucionDinero getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DevolucionDinero ldd_devolucionDinero;

		ldd_devolucionDinero = new DevolucionDinero();

		ldd_devolucionDinero.setIdDevolucionDinero(ars_rs.getString("ID_DEVOLUCION_DINERO"));
		ldd_devolucionDinero.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		ldd_devolucionDinero.setIdTurnoDevolucion(ars_rs.getString("ID_TURNO_DEVOLUCION"));
		ldd_devolucionDinero.setIdTurno(ars_rs.getString("ID_TURNO"));
		ldd_devolucionDinero.setIdPersonaInterviniente(ars_rs.getString("ID_PERSONA_INTERVINIENTE"));
		ldd_devolucionDinero.setIntervieneTramite(ars_rs.getString("INTERVIENE_TRAMITE"));
		ldd_devolucionDinero.setTitularCuenta(ars_rs.getString("TITULAR_CUENTA"));
		ldd_devolucionDinero.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		ldd_devolucionDinero.setTipoCuenta(ars_rs.getString("TIPO_CUENTA"));
		ldd_devolucionDinero.setNumeroCuenta(ars_rs.getString("NUMERO_CUENTA"));
		ldd_devolucionDinero.setIdPersonaTitularCuenta(ars_rs.getString("ID_PERSONA_TITULAR_CUENTA"));
		ldd_devolucionDinero.setValorTotalActoDevolucion(ars_rs.getLong("VALOR_TOTAL_ACTO_DEVOLUCION"));
		ldd_devolucionDinero.setExtemporaneo(ars_rs.getString("EXTEMPORANEO"));
		ldd_devolucionDinero.setTipoConsignacion(ars_rs.getString("TIPO_CONSIGNACION"));
		ldd_devolucionDinero.setNumeroConsignacionErrada(ars_rs.getString("NUMERO_CONSIGNACION_ERRADA"));
		ldd_devolucionDinero.setCodEntidadConsignacionErrada(ars_rs.getString("COD_ENTIDAD_CONSIGNACION_ERRADA"));
		ldd_devolucionDinero.setFechaConsignacionErrada(ars_rs.getTimestamp("FECHA_CONSIGNACION_ERRADA"));
		ldd_devolucionDinero.setNumeroCuentaConsignacionErrada(ars_rs.getString("NUMERO_CUENTA_CONSIGNACION_ERRADA"));
		ldd_devolucionDinero.setValorConsignacionErrada(getDouble(ars_rs, "VALOR_CONSIGNACION_ERRADA"));
		ldd_devolucionDinero.setIdCorreoElectronicoTitular(ars_rs.getString("ID_CORREO_ELECTRONICO_TITULAR"));
		ldd_devolucionDinero.setIdTelefonoMovilTitular(ars_rs.getString("ID_TELEFONO_MOVIL_TITULAR"));
		ldd_devolucionDinero.setIdTelefonoFijoTitular(ars_rs.getString("ID_TELEFONO_FIJO_TITULAR"));
		ldd_devolucionDinero.setTipoTelefonoFijoTitular(ars_rs.getString("TIPO_TELEFONO_FIJO_TITULAR"));
		ldd_devolucionDinero.setTipoTelefonoMovilTitular(ars_rs.getString("TIPO_TELEFONO_MOVIL_TITULAR"));
		ldd_devolucionDinero.setTokenVerificacion(getInteger(ars_rs, "TOKEN_VERIFICACION"));

		fillAuditoria(ars_rs, ldd_devolucionDinero);

		return ldd_devolucionDinero;
	}
}
