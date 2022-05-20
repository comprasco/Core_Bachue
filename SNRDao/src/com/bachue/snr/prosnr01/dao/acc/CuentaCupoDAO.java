package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 *
 */
public class CuentaCupoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_CUENTA_CUPO WHERE ID_CUENTA_CUPO = ?";

	/** Constante cs_FIND_MORE_DATA_BY_ID. */
	private static final String cs_FIND_MORE_DATA_BY_ID = "SELECT CC.*, SOL.FECHA_SOLICITUD FROM SDB_ACC_CUENTA_CUPO CC INNER JOIN SDB_ACC_SOLICITUD SOL ON CC.ID_SOLICITUD = SOL.ID_SOLICITUD WHERE CC.ID_CUENTA_CUPO = ?";

	/** Constante cs_FIND_BY_CODIGO. */
	private static final String cs_FIND_BY_CODIGO = "SELECT * FROM SDB_ACC_CUENTA_CUPO WHERE CODIGO = ? ORDER BY FECHA_CREACION FETCH FIRST 1 ROW ONLY";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_CUENTA_CUPO WHERE ID_SOLICITUD = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_CUENTA_CUPO(ID_CUENTA_CUPO, ID_ENTIDAD_EXTERNA, CODIGO, ESTADO, VALOR_MAXIMO, VALOR_MINIMO, SALDO, MIGRADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, MONTO_SOLICITADO, DESCRIPCION_FRECUENCIA_RECARGAS, DESCRIPCION_NECESIDAD_DE_CUENTA_CUPO, DESCRIPCION_ORIGEN_RECURSOS, ID_SOLICITUD, REPRESENTANTE_LEGAL_OCUPACION_PROFESION) VALUES (?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_CUENTA_CUPO SET ID_ENTIDAD_EXTERNA=?, CODIGO=?, ESTADO=?, VALOR_MAXIMO=?, VALOR_MINIMO=?, SALDO=?, MIGRADO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, MONTO_SOLICITADO=?, DESCRIPCION_FRECUENCIA_RECARGAS=?, DESCRIPCION_NECESIDAD_DE_CUENTA_CUPO=?, DESCRIPCION_ORIGEN_RECURSOS=?, ID_SOLICITUD=?, REPRESENTANTE_LEGAL_OCUPACION_PROFESION=? WHERE ID_CUENTA_CUPO = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_CUENTA_CUPO_ID_CUENTA_CUPO.NEXTVAL FROM DUAL";

	/**
	 * Busca una cuenta cupo por el id.
	 *
	 * @param as_idCuentaCupo id del cuenta cupo a utilizar como filtro en la busqueda
	 * @return Cuenta cupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public CuentaCupo findById(String as_idCuentaCupo)
	    throws B2BException
	{
		CuentaCupo lcc_return;

		lcc_return = null;

		if(StringUtils.isValidString(as_idCuentaCupo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idCuentaCupo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_return = getObjectFromResultSet(lrs_rs);
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

		return lcc_return;
	}

	/**
	 * Find more data by id.
	 *
	 * @param as_idCuentaCupo de as id cuenta cupo
	 * @return el valor de cuenta cupo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CuentaCupo findMoreDataById(String as_idCuentaCupo)
	    throws B2BException
	{
		CuentaCupo lcc_return;

		lcc_return = null;

		if(StringUtils.isValidString(as_idCuentaCupo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_MORE_DATA_BY_ID);

				lps_ps.setString(1, as_idCuentaCupo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
				{
					lcc_return = getObjectFromResultSet(lrs_rs);

					lcc_return.setFechaSolicitud(lrs_rs.getDate("FECHA_SOLICITUD"));
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMoreDataById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcc_return;
	}

	/**
	 * Find by codigo.
	 *
	 * @param as_codigo de as codigo
	 * @return el valor de cuenta cupo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CuentaCupo findByCodigo(String as_codigo)
	    throws B2BException
	{
		CuentaCupo lcc_return;

		lcc_return = null;

		if(StringUtils.isValidString(as_codigo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CODIGO);

				lps_ps.setString(1, as_codigo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCodigo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcc_return;
	}

	/**
	 * Busca una cuenta cupo por el id solicitud.
	 *
	 * @param as_idSolicitud id de la solicitud a utilizar como filtro en la busqueda
	 * @return Cuenta cupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public CuentaCupo findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		CuentaCupo lcc_return;

		lcc_return = null;

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
					lcc_return = getObjectFromResultSet(lrs_rs);
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

		return lcc_return;
	}

	/**
	 * Inserta un registro en la tabla.
	 *
	 * @param acc_cuentaCupo Objeto contenedor de los datos a insertar en el registro
	 * @return Secuencia generada para el registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long insert(CuentaCupo acc_cuentaCupo)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(acc_cuentaCupo != null)
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

				li_column     = 1;

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuencia = lrs_rs.getLong(1);

					lps_ps.setString(li_column++, StringUtils.getString(ll_secuencia));
				}

				lps_ps.setString(li_column++, acc_cuentaCupo.getIdEntidadExterna());
				lps_ps.setString(li_column++, acc_cuentaCupo.getCodigo());
				lps_ps.setString(li_column++, acc_cuentaCupo.getEstado());
				lps_ps.setBigDecimal(li_column++, acc_cuentaCupo.getValorMaximo());
				lps_ps.setBigDecimal(li_column++, acc_cuentaCupo.getValorMinimo());
				lps_ps.setBigDecimal(li_column++, acc_cuentaCupo.getSaldo());
				lps_ps.setString(li_column++, acc_cuentaCupo.getMigrado());
				lps_ps.setString(li_column++, acc_cuentaCupo.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, acc_cuentaCupo.getIpCreacion());
				lps_ps.setBigDecimal(li_column++, acc_cuentaCupo.getMontoSolicitado());
				lps_ps.setString(li_column++, acc_cuentaCupo.getDescripcionFrecuenciaRecargas());
				lps_ps.setString(li_column++, acc_cuentaCupo.getDescripcionNecesidadDeCuentaCupo());
				lps_ps.setString(li_column++, acc_cuentaCupo.getDescripcionOrigenRecursos());
				lps_ps.setString(li_column++, acc_cuentaCupo.getIdSolicitud());
				lps_ps.setString(li_column++, acc_cuentaCupo.getRepresentanteLegalOcupacionProfesion());

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
	 * Actualiza un registro asociado a un id específico.
	 *
	 * @param acc_cuentaCupo Objeto contenedor de los datos a actualizar
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public void update(CuentaCupo acc_cuentaCupo)
	    throws B2BException
	{
		if(acc_cuentaCupo != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, acc_cuentaCupo.getIdEntidadExterna());
				lps_ps.setString(li_column++, acc_cuentaCupo.getCodigo());
				lps_ps.setString(li_column++, acc_cuentaCupo.getEstado());
				lps_ps.setBigDecimal(li_column++, acc_cuentaCupo.getValorMaximo());
				lps_ps.setBigDecimal(li_column++, acc_cuentaCupo.getValorMinimo());
				lps_ps.setBigDecimal(li_column++, acc_cuentaCupo.getSaldo());
				lps_ps.setString(li_column++, acc_cuentaCupo.getMigrado());
				lps_ps.setString(li_column++, acc_cuentaCupo.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, acc_cuentaCupo.getIpModificacion());
				lps_ps.setBigDecimal(li_column++, acc_cuentaCupo.getMontoSolicitado());
				lps_ps.setString(li_column++, acc_cuentaCupo.getDescripcionFrecuenciaRecargas());
				lps_ps.setString(li_column++, acc_cuentaCupo.getDescripcionNecesidadDeCuentaCupo());
				lps_ps.setString(li_column++, acc_cuentaCupo.getDescripcionOrigenRecursos());
				lps_ps.setString(li_column++, acc_cuentaCupo.getIdSolicitud());
				lps_ps.setString(li_column++, acc_cuentaCupo.getRepresentanteLegalOcupacionProfesion());
				lps_ps.setString(li_column++, acc_cuentaCupo.getIdCuentaCupo());

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
	 * Extrae los resultados de la consulta y los almacena en un objeto CuentaCupo.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Cuenta cupo resultante de la consulta
	 * @throws SQLException Si ocurre un error en la extracción de la información
	 */
	private CuentaCupo getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CuentaCupo lcc_cuentaCupo;

		lcc_cuentaCupo = new CuentaCupo();

		lcc_cuentaCupo.setIdCuentaCupo(ars_rs.getString("ID_CUENTA_CUPO"));
		lcc_cuentaCupo.setIdEntidadExterna(ars_rs.getString("ID_ENTIDAD_EXTERNA"));
		lcc_cuentaCupo.setCodigo(ars_rs.getString("CODIGO"));
		lcc_cuentaCupo.setEstado(ars_rs.getString("ESTADO"));
		lcc_cuentaCupo.setValorMaximo(ars_rs.getBigDecimal("VALOR_MAXIMO"));
		lcc_cuentaCupo.setValorMinimo(ars_rs.getBigDecimal("VALOR_MINIMO"));
		lcc_cuentaCupo.setSaldo(ars_rs.getBigDecimal("SALDO"));
		lcc_cuentaCupo.setMigrado(ars_rs.getString("MIGRADO"));
		lcc_cuentaCupo.setMontoSolicitado(ars_rs.getBigDecimal("MONTO_SOLICITADO"));
		lcc_cuentaCupo.setDescripcionFrecuenciaRecargas(ars_rs.getString("DESCRIPCION_FRECUENCIA_RECARGAS"));
		lcc_cuentaCupo.setDescripcionNecesidadDeCuentaCupo(ars_rs.getString("DESCRIPCION_NECESIDAD_DE_CUENTA_CUPO"));
		lcc_cuentaCupo.setDescripcionOrigenRecursos(ars_rs.getString("DESCRIPCION_ORIGEN_RECURSOS"));
		lcc_cuentaCupo.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lcc_cuentaCupo.setRepresentanteLegalOcupacionProfesion(
		    ars_rs.getString("REPRESENTANTE_LEGAL_OCUPACION_PROFESION")
		);

		fillAuditoria(ars_rs, lcc_cuentaCupo);

		return lcc_cuentaCupo;
	}
}
