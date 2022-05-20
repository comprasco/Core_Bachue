package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.LiquidacionItemCertificado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_LIQUIDACION_ITEM_CERTIFICADO.
 *
 * @author ccalderon
 */
public class LiquidacionItemCertificadoDAO extends AuditoriaDao
{
	/**  Constante cs_FIND_BY_ID_TURNO_CERTIFICADO. */
	private static final String cs_FIND_BY_ID_TURNO_CERTIFICADO = "SELECT * FROM SDB_ACC_LIQUIDACION_ITEM_CERTIFICADO WHERE ID_TURNO_CERTIFICADO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_LIQUIDACION_ITEM_CERTIFICADO (ID_LIQUIDACION, CONSECUTIVO, ID_ITEM, ID_CIRCULO, ID_MATRICULA, ID_DATOS_ANT_SISTEMA, "
		+ "CANTIDAD, VALOR, ID_TURNO_CERTIFICADO, ID_CERTIFICADO, ID_MATRICULA_SEGREGACION, ID_CONDICION_TARIFA, ID_TARIFA_FIJA, VERSION_TARIFA_FIJA, ID_TIPO_TARIFA_REGISTRAL, "
		+ "VERSION_TARIFA_REGISTRAL, VALOR_FINAL, CERTIFICADO_OBLIGATORIO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Busca un registro asociado a una solicitud específica.
	 *
	 * @param as_idTurnoCertificado id del turno a utilizar como filtro en la busqueda
	 * @return DevolucionDinero resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public LiquidacionItemCertificado findByIdTurnoCertificado(String as_idTurnoCertificado)
	    throws B2BException
	{
		LiquidacionItemCertificado ldd_return;

		ldd_return = null;

		if(StringUtils.isValidString(as_idTurnoCertificado))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_CERTIFICADO);

				lps_ps.setString(1, as_idTurnoCertificado);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldd_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoCertificado", lse_e);

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
	 * Insert.
	 *
	 * @param alic_param de alic param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(LiquidacionItemCertificado alic_param)
	    throws B2BException
	{
		if(alic_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, alic_param.getIdLiquidacion());
				lps_ps.setLong(li_column++, alic_param.getConsecutivo());
				lps_ps.setLong(li_column++, alic_param.getIdItem());
				lps_ps.setString(li_column++, alic_param.getIdCirculo());
				lps_ps.setLong(li_column++, alic_param.getIdMatricula());
				lps_ps.setString(li_column++, alic_param.getIdDatosAntSistema());
				lps_ps.setLong(li_column++, alic_param.getCantidad());
				lps_ps.setLong(li_column++, alic_param.getValor());
				lps_ps.setString(li_column++, alic_param.getIdTurnoCertificado());
				lps_ps.setLong(li_column++, alic_param.getIdCertificado());
				lps_ps.setString(li_column++, alic_param.getIdMatriculaSegregacion());
				lps_ps.setString(li_column++, alic_param.getIdCondicionTarifa());
				lps_ps.setString(li_column++, alic_param.getIdTarifaFija());
				lps_ps.setLong(li_column++, alic_param.getVersionTarifaFija());
				lps_ps.setString(li_column++, alic_param.getIdTipoTarifaRegistral());
				lps_ps.setLong(li_column++, alic_param.getVersionTarifaRegistral());
				lps_ps.setLong(li_column++, alic_param.getValorFinal());
				lps_ps.setString(li_column++, alic_param.getCertificadoObligatorio());
				lps_ps.setString(li_column++, alic_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, alic_param.getIpCreacion());

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
	 * Extrae los resultados de una consulta en un objeto LiquidacionItemCertificado.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return DevolucionDinero con los datos obtenidos por la consulta
	 * @throws SQLException Si ocurre un error en la extracción de los datos de la consulta
	 */
	private LiquidacionItemCertificado getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		LiquidacionItemCertificado llic_liquidacionItemCertificado;

		llic_liquidacionItemCertificado = new LiquidacionItemCertificado();

		llic_liquidacionItemCertificado.setCantidad(ars_rs.getLong("CANTIDAD"));
		llic_liquidacionItemCertificado.setCertificadoObligatorio(ars_rs.getString("CERTIFICADO_OBLIGATORIO"));
		llic_liquidacionItemCertificado.setConsecutivo(ars_rs.getLong("CONSECUTIVO"));
		llic_liquidacionItemCertificado.setIdCertificado(ars_rs.getLong("ID_CERTIFICADO"));
		llic_liquidacionItemCertificado.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		llic_liquidacionItemCertificado.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		llic_liquidacionItemCertificado.setIdItem(ars_rs.getLong("ID_ITEM"));
		llic_liquidacionItemCertificado.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		llic_liquidacionItemCertificado.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		llic_liquidacionItemCertificado.setIdMatriculaSegregacion(ars_rs.getString("ID_MATRICULA_SEGREGACION"));
		llic_liquidacionItemCertificado.setIdTurnoCertificado(ars_rs.getString("ID_TURNO_CERTIFICADO"));
		llic_liquidacionItemCertificado.setValor(ars_rs.getLong("VALOR"));
		llic_liquidacionItemCertificado.setValorFinal(ars_rs.getLong("VALOR_FINAL"));
		llic_liquidacionItemCertificado.setIdTarifaFija(ars_rs.getString("ID_TARIFA_FIJA"));

		fillAuditoria(ars_rs, llic_liquidacionItemCertificado);

		return llic_liquidacionItemCertificado;
	}
}
