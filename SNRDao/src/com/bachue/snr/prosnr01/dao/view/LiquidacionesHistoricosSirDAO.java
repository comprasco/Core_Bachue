package com.bachue.snr.prosnr01.dao.view;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmActos;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmHomologacion;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmLiquidaciones;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmPagos;
import com.bachue.snr.prosnr01.model.view.LiquidacionesHistoricosSir;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las propiedades LiquidacionesHistoricosSirDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 8/06/2020
 */
public class LiquidacionesHistoricosSirDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL_BY_TURNO_ACTUALIZADO. */
	private static final String cs_FIND_ALL_BY_TURNO_ACTUALIZADO = "SELECT * FROM SDB_VW_LIQUIDACIONES_HISTORICOS_SIR WHERE TURNO_ACTUALIZADO = ? ORDER BY ID_TIPO_ACTO DESC";

	/**
	 * Find all by turno actualizado.
	 *
	 * @param as_turnoActualizado de as turno actualizado
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AdmHomologacion> findAllByTurnoActualizado(String as_turnoActualizado)
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
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_TURNO_ACTUALIZADO);

				lps_ps.setString(1, as_turnoActualizado);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcah_admHomologacion.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByTurnoActualizado", lse_e);

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
	 * Find all by turno actualizado sir.
	 *
	 * @param as_turnoActualizado de as turno actualizado
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<LiquidacionesHistoricosSir> findAllByTurnoActualizadoSir(String as_turnoActualizado)
	    throws B2BException
	{
		Collection<LiquidacionesHistoricosSir> lclhs_liquidacionesHistoricos;
		PreparedStatement                      lps_ps;
		ResultSet                              lrs_rs;

		lclhs_liquidacionesHistoricos     = new ArrayList<LiquidacionesHistoricosSir>();
		lps_ps                            = null;
		lrs_rs                            = null;

		if(StringUtils.isValidString(as_turnoActualizado))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_TURNO_ACTUALIZADO);

				lps_ps.setString(1, as_turnoActualizado);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lclhs_liquidacionesHistoricos.add(getLiquidacionHistoricosSir(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByTurnoActualizadoSir", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lclhs_liquidacionesHistoricos.isEmpty())
			lclhs_liquidacionesHistoricos = null;

		return lclhs_liquidacionesHistoricos;
	}

	/**
	 * Retorna Objeto o variable de valor object from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AdmHomologacion getObjectFromResultSet(ResultSet ars_rs)
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
			lal_admLiquidaciones.setActo(ars_rs.getString("ID_TIPO_ACTO"));
			lal_admLiquidaciones.setValor(ars_rs.getDouble("VALOR_ACTO"));
			lal_admLiquidaciones.setTipoLiq(ars_rs.getString("TIPOLIQ"));

			lah_admHomologacion.setAdmLiquidaciones(lal_admLiquidaciones);
		}

		{
			AdmPagos lap_admPagos;

			lap_admPagos = new AdmPagos();

			//lap_admPagos.setConcepto(ars_rs.getString("CONCEPTO"));
			//lap_admPagos.setForma(ars_rs.getString("FORMA"));
			lap_admPagos.setValor(ars_rs.getDouble("VALOR_LIQUIDACION"));
			lap_admPagos.setFecha(new Date());
//			lap_admPagos.setRecibo(getBigInteger(ars_rs, "RECIBO"));
			lah_admHomologacion.setAdmPagos(lap_admPagos);
		}

		{
			AdmActos laa_admActos;

			laa_admActos = new AdmActos();

			laa_admActos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
			laa_admActos.setDescripcion(ars_rs.getString("NOMBRE_ACTO"));
			laa_admActos.setCuantia(ars_rs.getString("CUANTIA"));

			lah_admHomologacion.setAdmActos(laa_admActos);
		}

		return lah_admHomologacion;
	}

	/**
	 * Retorna Objeto o variable de valor object from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private LiquidacionesHistoricosSir getLiquidacionHistoricosSir(ResultSet ars_rs)
	    throws SQLException
	{
		LiquidacionesHistoricosSir lr_datos;

		lr_datos = new LiquidacionesHistoricosSir();

		lr_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lr_datos.setNombre(ars_rs.getString("NOMBRE"));
		lr_datos.setTurnoActualizado(ars_rs.getString("TURNO_ACTUALIZADO"));
		lr_datos.setTurnoActualizadoAsociado(ars_rs.getString("TURNO_ACTUALIZADO_ASOCIADO"));
		lr_datos.setNroRadica(ars_rs.getString("NRORADICA"));
		lr_datos.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lr_datos.setNombreActo(ars_rs.getString("NOMBRE_ACTO"));
		lr_datos.setCuantia(ars_rs.getString("CUANTIA"));
		lr_datos.setValorActo(ars_rs.getDouble("VALOR_ACTO"));
		lr_datos.setTipoLiq(ars_rs.getString("TIPOLIQ"));
		lr_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lr_datos.setValorLiquidacion(ars_rs.getDouble("VALOR_LIQUIDACION"));
		lr_datos.setValorConservacionDocumental(ars_rs.getDouble("VALOR_CONSERVACION_DOCUMENTAL"));

		return lr_datos;
	}
}
