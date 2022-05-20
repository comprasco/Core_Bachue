package com.bachue.snr.prosnr01.dao.view;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr21.model.pgn.CRPSDetalle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;


/**
 * Clase encargada de las interacciones con base de datos para la vista SDB_VW_CRPS_DET_CONCILIAR
 *
 * @author Edgar Prieto
 */
public class CRPSDetalleDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Sentencia SQL para consultar envio conciliacion */
	private static final String cs_CONSULTAR_PARA_ENVIO_CONCILIACION = "SELECT ID_REGISTRO_PAGO,ID_LIQUIDACION,"
		+ "CONSECUTIVO_LIQUIDACION,ID_ITEM,ID_ACTO,ID_CIRCULO,ID_TIPO_ACTO,VERSION,ID_PROCESO,NOMBRE_PROCESO,"
		+ "ID_SUBPROCESO,NOMBRE_SUBPROCESO,CANTIDAD,VALOR_LIQ,VALOR_IMPUESTO_LIQ,VALOR_TOTAL_LIQ,"
		+ "VALOR_CONSERV_DOCUMENTAL,ID_TURNO,ID_CIRCULO_TURNO,ID_MATRICULA_CERTIFICADO,ID_MATRICULA_SEGREGACION FROM "
		+ "SDB_VW_CRPS_DET_CONCILIAR ORDER BY ID_REGISTRO_PAGO,CONSECUTIVO_LIQUIDACION,ID_ITEM";

	/** Sentencia SQL para la inserción de un registro */
	private static final String cs_INSERTAR = "INSERT INTO SDB_CON_CRPS_DETALLE (ID_ARCHIVO,ID_ARCHIVO_CABECERA,ID_REGISTRO,ID_REGISTRO_CABECERA,ID_ITEM,"
		+ "ID_LIQUIDACION,CONSECUTIVO,ID_ACTO,ID_CIRCULO,ID_TIPO_ACTO,VERSION_ACTO,ID_PROCESO,NOMBRE_PROCESO,ID_SUBPROCESO,"
		+ "NOMBRE_SUBPROCESO,CANTIDAD,VALOR_FINAL,VALOR_IMPUESTOS_FINAL,VALOR_TOTAL,VALOR_CONSERV_DOCUMENTAL,ID_TURNO,ID_CIRCULO_MATRICULA,"
		+ "ID_MATRICULA,ID_MATRICULA_SEGREGACION,ID_RUBROS_HOMOLOGACION,ID_RUBRO_CONSERVACION_DOCUMENTAL,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";
	
	/** Sentencia SQL para la inserción de un registro */
	private static final String cs_FIND_BY_ID_LIQUIDACION = "SELECT ID_REGISTRO FROM SDB_CON_CRPS WHERE ID_LIQUIDACION=? AND ID_ARCHIVO=?";

	/**
	 * Obtiene los registros que se deben de servicios facturados y pagados que se deben llevar al módulo de
	 * conciliaciones
	 */
	public Collection<CRPSDetalle> consultarParaEnvioConciliacio()
	    throws B2BException
	{
		Collection<CRPSDetalle> lccrpsd_respuesta;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lccrpsd_respuesta     = null;
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			lps_ps                = getConnection().prepareStatement(cs_CONSULTAR_PARA_ENVIO_CONCILIACION);
			lrs_rs                = lps_ps.executeQuery();
			lccrpsd_respuesta     = new java.util.ArrayList<CRPSDetalle>();

			while(lrs_rs.next())
				lccrpsd_respuesta.add(obtener(lrs_rs));

			if(lccrpsd_respuesta.isEmpty())
				lccrpsd_respuesta = null;
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultarParaEnvioConciliacio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lccrpsd_respuesta;
	}

	/**
	 * Insertar.
	 *
	 * @param accrpsc_archivo the accrpsc archivo
	 * @throws B2BException the b 2 B exception
	 */
	public void insertar(java.util.Collection<CRPSDetalle> accrpsc_archivo)
	    throws B2BException
	{
		if(com.b2bsg.common.util.CollectionUtils.isValidCollection(accrpsc_archivo))
		{
			PreparedStatement lps_insercion;
			PreparedStatement lps_select;
			ResultSet         lrs_result;

			lps_insercion     = null;
			lps_select        = null;
			lrs_result        = null;

			try
			{
				lps_insercion     = getConnection().prepareStatement(cs_INSERTAR);
				lps_select        = getConnection().prepareStatement(cs_FIND_BY_ID_LIQUIDACION);

				for(CRPSDetalle lcrpsd_linea : accrpsc_archivo)
				{
					if(lcrpsd_linea != null)
					{
						int    li_column;
						String ls_idArchivoCabecera;
						String ls_idLiquidacion;

						li_column                = 1;
						ls_idArchivoCabecera     = lcrpsd_linea.getIdArchivoCabecera();
						ls_idLiquidacion         = lcrpsd_linea.getIdLiquidacion();

						{
							lps_select.setString(1, ls_idLiquidacion);
							lps_select.setString(2, ls_idArchivoCabecera);

							lrs_result = lps_select.executeQuery();

							if(lrs_result.next())
								lcrpsd_linea.setRegistroCabecera(lrs_result.getInt(1));
						}

						lps_insercion.clearParameters();
						lps_select.clearParameters();

						lps_insercion.setString(li_column++, lcrpsd_linea.getIdArchivo());
						lps_insercion.setString(li_column++, ls_idArchivoCabecera);
						lps_insercion.setInt(li_column++, lcrpsd_linea.getRegistro());
						lps_insercion.setInt(li_column++, lcrpsd_linea.getRegistroCabecera());
						setInteger(lps_insercion, lcrpsd_linea.getIdItem(), li_column++);
						lps_insercion.setString(li_column++, ls_idLiquidacion);
						setInteger(lps_insercion, lcrpsd_linea.getConsecutivoLiquidacion(), li_column++);
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdActo());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdCirculo());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdTipoActo());
						setInteger(lps_insercion, lcrpsd_linea.getVersion(), li_column++);
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdProceso());
						lps_insercion.setString(li_column++, lcrpsd_linea.getNombreProceso());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdSubproceso());
						lps_insercion.setString(li_column++, lcrpsd_linea.getNombreSubproceso());
						setInteger(lps_insercion, lcrpsd_linea.getCantidad(), li_column++);
						setDouble(lps_insercion, lcrpsd_linea.getValorLiquidacion(), li_column++);
						setDouble(lps_insercion, lcrpsd_linea.getValorImpuestoLiquidacion(), li_column++);
						setDouble(lps_insercion, lcrpsd_linea.getValorTotalLiquidacion(), li_column++);
						setDouble(lps_insercion, lcrpsd_linea.getValorConservacionDocumental(), li_column++);
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdTurno());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdCirculoTurno());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdMatriculaCertificado());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdMatriculaSegregacion());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdRubro());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdRubroConservacionDocumental());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIdUsuarioCreacion());
						lps_insercion.setString(li_column++, lcrpsd_linea.getIpCreacion());

						lps_insercion.executeUpdate();
					}
				}
			}
			catch(java.sql.SQLException lse_e)
			{
				logError(this, "insertar", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_insercion);
				close(lps_select);
				close(lrs_result);
			}
		}
	}

	/**
	 * Metodo que se encarga de llenar un objeto de tipo CRPSDetalle con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto MulticashCabecera
	 * @return Objeto de tipo Archivo con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CRPSDetalle obtener(ResultSet ars_rs)
	    throws SQLException
	{
		CRPSDetalle lcrpsd_registro;

		lcrpsd_registro = new CRPSDetalle();

		lcrpsd_registro.setIdRegistroPago(ars_rs.getString("ID_REGISTRO_PAGO"));
		lcrpsd_registro.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		lcrpsd_registro.setConsecutivoLiquidacion(getInteger(ars_rs, "CONSECUTIVO_LIQUIDACION"));
		lcrpsd_registro.setIdItem(getInteger(ars_rs, "ID_ITEM"));
		lcrpsd_registro.setIdActo(ars_rs.getString("ID_ACTO"));
		lcrpsd_registro.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lcrpsd_registro.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lcrpsd_registro.setVersion(getInteger(ars_rs, "VERSION"));
		lcrpsd_registro.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lcrpsd_registro.setNombreProceso(ars_rs.getString("NOMBRE_PROCESO"));
		lcrpsd_registro.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		lcrpsd_registro.setNombreSubproceso(ars_rs.getString("NOMBRE_SUBPROCESO"));
		lcrpsd_registro.setCantidad(getInteger(ars_rs, "CANTIDAD"));
		lcrpsd_registro.setValorLiquidacion(getDouble(ars_rs, "VALOR_LIQ"));
		lcrpsd_registro.setValorImpuestoLiquidacion(getDouble(ars_rs, "VALOR_IMPUESTO_LIQ"));
		lcrpsd_registro.setValorTotalLiquidacion(getDouble(ars_rs, "VALOR_TOTAL_LIQ"));
		lcrpsd_registro.setValorConservacionDocumental(getDouble(ars_rs, "VALOR_CONSERV_DOCUMENTAL"));
		lcrpsd_registro.setIdTurno(ars_rs.getString("ID_TURNO"));
		lcrpsd_registro.setIdCirculoTurno(ars_rs.getString("ID_CIRCULO_TURNO"));
		lcrpsd_registro.setIdMatriculaCertificado(ars_rs.getString("ID_MATRICULA_CERTIFICADO"));
		lcrpsd_registro.setIdMatriculaSegregacion(ars_rs.getString("ID_MATRICULA_SEGREGACION"));

		return lcrpsd_registro;
	}
}
