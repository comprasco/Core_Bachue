package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.ModCuentaCupo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_MOD_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 *
 */
public class ModCuentaCupoDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_MOD_CUENTA_CUPO(ID_SOLICITUD, ID_SOLICITUD_CREACION, ID_CUENTA_CUPO, VALOR_MINIMO, VALOR_MAXIMO, ESTADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = "SELECT * FROM SDB_ACC_MOD_CUENTA_CUPO WHERE ID_SOLICITUD = ?";

	/**
	 * Busca una mod cuenta cupo por el id solicitud
	 *
	 * @param as_idSolicitud id de la solicitud a utilizar como filtro en la busqueda
	 * @return Mod cuenta cupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public ModCuentaCupo findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		ModCuentaCupo lcc_return;

		lcc_return = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD);

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
	 * Inserta un registro en la tabla
	 *
	 * @param amcc_modCuentaCupo Objeto contenedor de los datos a insertar en el registro
	 * @throws B2BException
	 */
	public void insert(ModCuentaCupo amcc_modCuentaCupo)
	    throws B2BException
	{
		if(amcc_modCuentaCupo != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, amcc_modCuentaCupo.getIdSolicitud());
				lps_ps.setString(li_column++, amcc_modCuentaCupo.getIdSolicitudCreacion());
				lps_ps.setString(li_column++, amcc_modCuentaCupo.getIdCuentaCupo());
				lps_ps.setBigDecimal(li_column++, amcc_modCuentaCupo.getValorMinimo());
				lps_ps.setBigDecimal(li_column++, amcc_modCuentaCupo.getValorMaximo());
				lps_ps.setString(li_column++, amcc_modCuentaCupo.getEstado());
				lps_ps.setString(li_column++, amcc_modCuentaCupo.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, amcc_modCuentaCupo.getIpCreacion());

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
				close(lrs_rs);
			}
		}
	}

	/**
	 * Extrae los resultados de la consulta y los almacena en un objeto ModCuentaCupo
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Mod cuenta cupo resultante de la consulta
	 * @throws SQLException Si ocurre un error en la extracción de la información
	 */
	private ModCuentaCupo getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ModCuentaCupo lcc_cuentaCupo;

		lcc_cuentaCupo = new ModCuentaCupo();

		lcc_cuentaCupo.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lcc_cuentaCupo.setIdSolicitudCreacion(ars_rs.getString("ID_SOLICITUD_CREACION"));
		lcc_cuentaCupo.setIdCuentaCupo(ars_rs.getString("ID_CUENTA_CUPO"));
		lcc_cuentaCupo.setValorMaximo(ars_rs.getBigDecimal("VALOR_MAXIMO"));
		lcc_cuentaCupo.setValorMinimo(ars_rs.getBigDecimal("VALOR_MINIMO"));
		lcc_cuentaCupo.setEstado(ars_rs.getString("ESTADO"));

		fillAuditoria(ars_rs, lcc_cuentaCupo);

		return lcc_cuentaCupo;
	}
}
