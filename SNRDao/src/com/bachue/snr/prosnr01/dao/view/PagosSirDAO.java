package com.bachue.snr.prosnr01.dao.view;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.PagosSir;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades PagosSirDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 18/06/2020
 */
public class PagosSirDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL_BY_TURNO_ACTUALIZADO. */
	private static final String cs_FIND_ALL_BY_TURNO_ACTUALIZADO = "SELECT * FROM SDB_VW_PAGOS_SIR WHERE TURNO_ACTUALIZADO = ?";

	/**
	 * Find all by turno actualizado.
	 *
	 * @param as_turnoActualizado de as turno actualizado
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PagosSir> findAllByTurnoActualizado(String as_turnoActualizado)
	    throws B2BException
	{
		Collection<PagosSir> lcps_admHomologacion;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcps_admHomologacion     = new ArrayList<PagosSir>();
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
					lcps_admHomologacion.add(getObjectFromResultSet(lrs_rs));
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

		if(lcps_admHomologacion.isEmpty())
			lcps_admHomologacion = null;

		return lcps_admHomologacion;
	}

	/**
	 * Retorna Objeto o variable de valor object from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private PagosSir getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		PagosSir lpf_pagoFolio;

		lpf_pagoFolio = new PagosSir();

		lpf_pagoFolio.setAprobacion(ars_rs.getString("APROBACION"));
		lpf_pagoFolio.setBanco(ars_rs.getString("BANCO"));
		lpf_pagoFolio.setCanal(getLong(ars_rs, "CANAL"));
		lpf_pagoFolio.setCheque(ars_rs.getString("CHEQUE"));
		lpf_pagoFolio.setConcepto(ars_rs.getString("CONCEPTO"));
		lpf_pagoFolio.setCus(ars_rs.getString("CUS"));
		lpf_pagoFolio.setDescripcionCanal(ars_rs.getString("DESCRIPCION_CANAL"));
		lpf_pagoFolio.setDescripcionConcepto(ars_rs.getString("DESCRIPCION_CONCEPTO"));
		lpf_pagoFolio.setDescripcionFormaPago(ars_rs.getString("DESCRIPCION_FORMA_PAGO"));
		lpf_pagoFolio.setFecha(ars_rs.getTimestamp("FECHA"));
		lpf_pagoFolio.setForma(ars_rs.getString("FORMA"));
		lpf_pagoFolio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lpf_pagoFolio.setIdDocumentoPago(ars_rs.getString("ID_DOCUMENTO_PAGO"));
		lpf_pagoFolio.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		lpf_pagoFolio.setNombreBanco(ars_rs.getString("NOMBRE_BANCO"));
		lpf_pagoFolio.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));
		lpf_pagoFolio.setNroRadica(ars_rs.getString("NRORADICA"));
		lpf_pagoFolio.setRecibo(ars_rs.getString("RECIBO"));
		lpf_pagoFolio.setTurnoActualizado(ars_rs.getString("TURNO_ACTUALIZADO"));
		lpf_pagoFolio.setTurnoActualizadoAsociado(ars_rs.getString("TURNO_ACTUALIZADO_ASOCIADO"));
		lpf_pagoFolio.setValor(ars_rs.getDouble("VALOR"));
		lpf_pagoFolio.setValorConservacionDocumental(ars_rs.getDouble("VALOR_CONSERVACION_DOCUMENTAL"));

		return lpf_pagoFolio;
	}
}
