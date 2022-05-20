package com.bachue.snr.prosnr21.dao.png;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.b2bsg.common.exception.B2BException;
import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;
import com.bachue.snr.prosnr21.model.pgn.ConSiifMaestro;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_SIIF_MAESTRO.
 *
 * @author Kevin Pulido G
 */
public class ConSiifMaestroDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_DIA_CORTE = "SELECT M.* FROM SDB_CON_SIIF_MAESTRO M INNER JOIN SDB_PGN_PERIODO_CORTE C ON M.ID_PERIODO_CORTE = C.ID_PERIODO_CORTE WHERE TO_DATE(C.DIA_CORTE, 'DD/MM/YY') = TO_DATE(?, 'DD/MM/YY')";

	/**
	 * Find all.
	 *
	 * @param acpa_param de acpa param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConSiifMaestro> findByDiaCorte(Date ad_date)
	    throws B2BException
	{
		Collection<ConSiifMaestro> lccpa_return;

		lccpa_return = new ArrayList<ConSiifMaestro>();

		if(ad_date != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_DIA_CORTE);

				setDate(lps_ps, ad_date, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccpa_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDiaCorte", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccpa_return.isEmpty())
			lccpa_return = null;

		return lccpa_return;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ConSiifMaestro getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ConSiifMaestro lcpa_datos;

		lcpa_datos = new ConSiifMaestro();

		lcpa_datos.setIdSiifMaestro(ars_rs.getString("ID_SIIF_MAESTRO"));
		lcpa_datos.setIdPeriodoCorte(ars_rs.getString("ID_PERIODO_CORTE"));
		lcpa_datos.setConsecutivoMaestro(getLong(ars_rs,"CONSECUTIVO_MAESTRO"));
		lcpa_datos.setPciConexion(ars_rs.getString("PCI_CONEXION"));
		lcpa_datos.setDocumentoRecaudo(getLong(ars_rs,"DOCUMENTO_RECAUDO"));
		lcpa_datos.setTipoDocumento(ars_rs.getString("TIPO_DOCUMENTO"));
		lcpa_datos.setPciOrigenDocumento(ars_rs.getString("PCI_ORIGEN_DOCUMENTO"));
		lcpa_datos.setAnioObligacion(getLong(ars_rs,"ANIO_OBLIGACION"));
		lcpa_datos.setFechaRegistro(ars_rs.getDate("FECHA_REGISTRO"));
		lcpa_datos.setCodigoTipoDocTercero(getLong(ars_rs,"CODIGO_TIPO_DOC_TERCERO"));
		lcpa_datos.setNumeroDocTercero(ars_rs.getString("NUMERO_DOC_TERCERO"));
		lcpa_datos.setRelacionMoneda(ars_rs.getString("RELACION_MONEDA"));
		lcpa_datos.setValorTotalMonedaExtranjera(ars_rs.getDouble("VALOR_TOTAL_MONEDA_EXTRANJERA"));
		lcpa_datos.setValorTotalPesos(ars_rs.getDouble("VALOR_TOTAL_PESOS"));
		lcpa_datos.setFuenteFinanciacion(ars_rs.getString("FUENTE_FINANCIACION"));
		lcpa_datos.setSituacionFondo(ars_rs.getString("SITUACION_FONDO"));
		lcpa_datos.setTipoDocumentoSoporte(ars_rs.getString("TIPO_DOCUMENTO_SOPORTE"));
		lcpa_datos.setNumeroDocumentoSoporte(getLong(ars_rs,"NUMERO_DOCUMENTO_SOPORTE"));
		lcpa_datos.setFechaSolicitud(ars_rs.getDate("FECHA_SOLICITUD"));
		lcpa_datos.setExpedidor(getLong(ars_rs,"EXPEDIDOR"));
		lcpa_datos.setNombreFuncionario(ars_rs.getString("NOMBRE_FUNCIONARIO"));
		lcpa_datos.setCargoFuncionario(ars_rs.getString("CARGO_FUNCIONARIO"));
		lcpa_datos.setObservacion(ars_rs.getString("OBSERVACION"));
		lcpa_datos.setUrlDocumentoSoporte(ars_rs.getString("URL_DOCUMENTO_SOPORTE"));
		lcpa_datos.setUrlDescripcionDocumentoTercero(ars_rs.getString("URL_DESCRIPCION_DOCUMENTO_TERCERO"));
		
		fillAuditoria(ars_rs, lcpa_datos);

		return lcpa_datos;
	}
}
