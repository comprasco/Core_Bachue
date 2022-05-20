package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.NotaCreditoCuentaCupo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_NOTA_CREDITO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 *
 */
public class NotaCreditoCuentaCupoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_LATEST_BY_ID_CUENTA_CUPO. */
	private static final String cs_FIND_LATEST_BY_ID_CUENTA_CUPO = "SELECT * FROM SDB_ACC_NOTA_CREDITO_CUENTA_CUPO WHERE FECHA = (SELECT MAX(FECHA) FROM SDB_ACC_NOTA_CREDITO_CUENTA_CUPO WHERE ID_CUENTA_CUPO = ?) AND ID_CUENTA_CUPO = ?";

	/** Constante cs_FIND_ALL_BY_ID_CUENTA_CUPO. */
	private static final String cs_FIND_ALL_BY_ID_CUENTA_CUPO = "SELECT * FROM SDB_ACC_NOTA_CREDITO_CUENTA_CUPO WHERE ID_CUENTA_CUPO = ?";

	/** Constante cs_FIND_OLDEST_BY_ID_CUENTA_CUPO. */
	private static final String cs_FIND_OLDEST_BY_ID_CUENTA_CUPO = "SELECT * FROM SDB_ACC_NOTA_CREDITO_CUENTA_CUPO WHERE FECHA = (SELECT MIN(FECHA) FROM SDB_ACC_NOTA_CREDITO_CUENTA_CUPO WHERE ID_CUENTA_CUPO = ?) AND ID_CUENTA_CUPO = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_NOTA_CREDITO_CUENTA_CUPO WHERE ID_NOTA_CREDITO = ?";

	/** Constante cs_FIND_BY_CODIGO. */
	private static final String cs_FIND_BY_CODIGO = "SELECT * FROM SDB_ACC_NOTA_CREDITO_CUENTA_CUPO WHERE CODIGO = ? ORDER BY FECHA_CREACION FETCH FIRST 1 ROW ONLY";

	/** Constante cs_UPDATE_SALDO. */
	private static final String cs_UPDATE_SALDO = "UPDATE SDB_ACC_NOTA_CREDITO_CUENTA_CUPO SET SALDO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_NOTA_CREDITO = ?";

	/**
	 * Actualiza un registro de la tabla asociado a un id especifico.
	 *
	 * @param ammcc_notaCreditoCuentaCupo Objeto contenedor de los datos a actualizar
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public void updateSaldo(NotaCreditoCuentaCupo ammcc_notaCreditoCuentaCupo)
	    throws B2BException
	{
		if(ammcc_notaCreditoCuentaCupo != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_SALDO);

				lps_ps.setBigDecimal(li_column++, ammcc_notaCreditoCuentaCupo.getSaldo());
				lps_ps.setString(li_column++, ammcc_notaCreditoCuentaCupo.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ammcc_notaCreditoCuentaCupo.getIpModificacion());
				lps_ps.setString(li_column++, ammcc_notaCreditoCuentaCupo.getIdNotaCredito());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateSaldo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Busca la nota credito por el id nota credito asociado.
	 *
	 * @param as_idNotaCredito id de nota credito a utilizar como filtro en la busqueda
	 * @return NotaCreditoCuentaCupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public NotaCreditoCuentaCupo findById(String as_idNotaCredito)
	    throws B2BException
	{
		NotaCreditoCuentaCupo lnccc_return;

		lnccc_return = null;

		if(StringUtils.isValidString(as_idNotaCredito))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idNotaCredito);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lnccc_return = getObjectFromResultSet(lrs_rs);
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

		return lnccc_return;
	}

	/**
	 * Find by codigo.
	 *
	 * @param as_codigo de as codigo
	 * @return el valor de nota credito cuenta cupo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NotaCreditoCuentaCupo findByCodigo(String as_codigo)
	    throws B2BException
	{
		NotaCreditoCuentaCupo lnccc_return;

		lnccc_return = null;

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
					lnccc_return = getObjectFromResultSet(lrs_rs);
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

		return lnccc_return;
	}

	/**
	 * Busca la nota credito más reciente por el id cuenta cupo.
	 *
	 * @param as_idCuentaCupo id de cuenta cupo a utilizar como filtro en la busqueda
	 * @return NotaCreditoCuentaCupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public NotaCreditoCuentaCupo findLatestOrOldestByIdCuentaCupo(String as_idCuentaCupo)
	    throws B2BException
	{
		return findLatestOrOldestByIdCuentaCupo(as_idCuentaCupo, true);
	}

	/**
	 * Busca la nota credito más reciente por el id cuenta cupo.
	 *
	 * @param as_idCuentaCupo id de cuenta cupo a utilizar como filtro en la busqueda
	 * @param ab_latest true para buscar el registro más reciente, false para el más antiguo
	 * @return NotaCreditoCuentaCupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public NotaCreditoCuentaCupo findLatestOrOldestByIdCuentaCupo(String as_idCuentaCupo, boolean ab_latest)
	    throws B2BException
	{
		NotaCreditoCuentaCupo lnccc_return;

		lnccc_return = null;

		if(StringUtils.isValidString(as_idCuentaCupo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection()
						         .prepareStatement(
						    ab_latest ? cs_FIND_LATEST_BY_ID_CUENTA_CUPO : cs_FIND_OLDEST_BY_ID_CUENTA_CUPO
						);

				lps_ps.setString(1, as_idCuentaCupo);
				lps_ps.setString(2, as_idCuentaCupo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lnccc_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findLatestOrOldestByIdCuentaCupo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lnccc_return;
	}

	/**
	 * Find all by id cuenta cupo.
	 *
	 * @param as_idCuentaCupo de as id cuenta cupo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<NotaCreditoCuentaCupo> findAllByIdCuentaCupo(String as_idCuentaCupo)
	    throws B2BException
	{
		Collection<NotaCreditoCuentaCupo> lcnccc_return;

		lcnccc_return = new ArrayList<NotaCreditoCuentaCupo>();

		if(StringUtils.isValidString(as_idCuentaCupo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_ID_CUENTA_CUPO);

				lps_ps.setString(1, as_idCuentaCupo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcnccc_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdCuentaCupo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcnccc_return.isEmpty())
			lcnccc_return = null;

		return lcnccc_return;
	}

	/**
	 * Extrae los resultados de la consulta y los almacena en un objeto NotaCreditoCuentaCupo.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Usuario cuenta cupo resultante de la consulta
	 * @throws SQLException Si ocurre un error en la extracción de la información
	 */
	private NotaCreditoCuentaCupo getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		NotaCreditoCuentaCupo lnccc_notaCreditoCuentaCupo;

		lnccc_notaCreditoCuentaCupo = new NotaCreditoCuentaCupo();

		lnccc_notaCreditoCuentaCupo.setIdNotaCredito(ars_rs.getString("ID_NOTA_CREDITO"));
		lnccc_notaCreditoCuentaCupo.setIdCuentaCupo(ars_rs.getString("ID_CUENTA_CUPO"));
		lnccc_notaCreditoCuentaCupo.setCodigo(ars_rs.getString("CODIGO"));
		lnccc_notaCreditoCuentaCupo.setValorRecarga(ars_rs.getBigDecimal("VALOR_RECARGA"));
		lnccc_notaCreditoCuentaCupo.setSaldo(ars_rs.getBigDecimal("SALDO"));
		lnccc_notaCreditoCuentaCupo.setFecha(ars_rs.getTimestamp("FECHA"));

		fillAuditoria(ars_rs, lnccc_notaCreditoCuentaCupo);

		return lnccc_notaCreditoCuentaCupo;
	}
}
