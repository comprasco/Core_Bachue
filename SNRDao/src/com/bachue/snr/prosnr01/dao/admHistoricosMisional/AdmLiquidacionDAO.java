package com.bachue.snr.prosnr01.dao.admHistoricosMisional;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmActos;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmHomologacion;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmLiquidaciones;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmPagos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase encargada de administrar todas las sentencias sql que se ejecuten en la tabla ACTOS de ADM_HISTORICOS_MISIONAL.
 * @author hcastaneda
 *
 */
public class AdmLiquidacionDAO extends BaseDAO
{
	private static final String cs_CONSULTA_HOMOLOGACION_ACTOS_LIQUIDACION = "SELECT L.ID_CIRCULO, L.NRORADICA, L.TURNO_ACTUALIZADO, L.ACTO, A.DESCRIPCION, "
		+ "A.CUANTIA, L.VALOR, L.TIPOLIQ, L.LIQUIDACION,P.CONCEPTO, P.FORMA, P.VALOR VALOR_PAGO, P.FECHA, P.RECIBO "
		+ "FROM LIQUIDACIONES L, PAGOS P, ACTOS A WHERE L.NRORADICA = P.NRORADICA AND L.TURNO_ACTUALIZADO(+) = ? "
		+ "AND P.CONCEPTO = 'D' AND A.CODIGO = L.ACTO";

	/**
	 * Metodo encargado de consultar los datos de homologación de actos liquidación que cumplan con los criterios de búsqueda.
	 * @param as_turnoActualizado Argumento de tipo String que contiene el turno actualizado para realizar la consulta.
	 * @return Colección de AdmHomologacion que contiene los datos que cumplen con los criterios de búsqueda.
	 * @throws B2BException
	 */
	public Collection<AdmHomologacion> consultarAdmHomologacion(String as_turnoActualizado)
	    throws B2BException
	{
		Collection<AdmHomologacion> lcah_admHomologacion;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcah_admHomologacion     = new ArrayList<AdmHomologacion>();
		lps_ps                   = null;
		lrs_rs                   = null;

		if(StringUtils.isValidString(as_turnoActualizado))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CONSULTA_HOMOLOGACION_ACTOS_LIQUIDACION);

				lps_ps.setString(1, as_turnoActualizado);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcah_admHomologacion.add(getAdmHomologacion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultarAdmHomologacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lcah_admHomologacion.isEmpty())
			lcah_admHomologacion = null;

		return lcah_admHomologacion;
	}

	/**
	 * Metodo encargado de asignar los resultados de una consulta a los atributos de un objeto AdmActos.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene de los datos resultantes de la consulta.
	 * @return Objeto AdmActos al cual se le asignan los valores resultante de la consulta.
	 * @throws SQLException
	 */
	private AdmHomologacion getAdmHomologacion(ResultSet ars_rs)
	    throws SQLException
	{
		AdmHomologacion lah_admHomologacion;

		lah_admHomologacion = new AdmHomologacion();

		{
			AdmLiquidaciones lal_admLiquidaciones;

			lal_admLiquidaciones = new AdmLiquidaciones();

			lal_admLiquidaciones.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
			lal_admLiquidaciones.setNroradica(ars_rs.getString("NRORADICA"));
			lal_admLiquidaciones.setTurnoActualizado(ars_rs.getString("TURNO_ACTUALIZADO"));
			lal_admLiquidaciones.setActo(ars_rs.getString("ACTO"));
			lal_admLiquidaciones.setValor(ars_rs.getDouble("VALOR"));
			lal_admLiquidaciones.setTipoLiq(ars_rs.getString("TIPOLIQ"));

			lah_admHomologacion.setAdmLiquidaciones(lal_admLiquidaciones);
		}

		{
			AdmPagos lap_admPagos;

			lap_admPagos = new AdmPagos();

			lap_admPagos.setConcepto(ars_rs.getString("CONCEPTO"));
			lap_admPagos.setForma(ars_rs.getString("FORMA"));
			lap_admPagos.setValor(ars_rs.getDouble("VALOR_PAGO"));
			lap_admPagos.setFecha(ars_rs.getTimestamp("FECHA"));
			lap_admPagos.setRecibo(getBigInteger(ars_rs, "RECIBO"));

			lah_admHomologacion.setAdmPagos(lap_admPagos);
		}

		{
			AdmActos laa_admActos;

			laa_admActos = new AdmActos();

			laa_admActos.setDescripcion(ars_rs.getString("DESCRIPCION"));
			laa_admActos.setCuantia(ars_rs.getString("CUANTIA"));

			lah_admHomologacion.setAdmActos(laa_admActos);
		}

		return lah_admHomologacion;
	}
}
