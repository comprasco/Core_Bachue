package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr21.model.pgn.ACH;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_ACH del módulo de conciliaciones
 *
 * @author Edgar Prieto
 */
public class ACHDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Sentencia SQL para la inserción de un registro */
	private static final String cs_INSERTAR = "INSERT INTO SDB_CON_ACH(ID_ARCHIVO,ID_REGISTRO,ID_CUENTA,"
		+ "ID_PERIODO_CORTE,CUS,VALOR,COD_COMERCIO,NOMBRE_COMERCIO,ESTADO_COMERCIO,CODIGO_AUTORIZACION,FECHA_CREADA,"
		+ "FECHA_ULTIMO_ESTADO,IMPUESTO,TICKED_ID,CICLO_ORIGEN,CICLO_TRANSACCION,DESCRIPCION,FECHA_AUTORIZACION,"
		+ "CODIGO_SERVICIO,NOMBRE_SERVICIO,NIT_SERVICIO,TIPO_USUARIO,TIPO_TRANSACCION,BANCO_RECAUDADOR,"
		+ "MODALIDAD_VINCULACION,ID_FUNCIONALIDAD,NOMBRE_FUNCIONALIDAD,REFERENCIA1,REFERENCIA2,REFERENCIA3,"
		+ "BANCO_ORIGEN,FECHA_RESOLUCION,ID_TAQUILLA,NOMBRE_TAQUILLA,TIPO_AUTORIZACION,ID_USUARIO_CREACION,IP_CREACION,"
		+ "FECHA_CREACION)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
		+ "SYSTIMESTAMP)";

	/**
	 * Metodo para insertar registros en la base de datos de la tabla SDB_CON_MULTICASH_DETALLE
	 *  @param acach_archivo Registros a insertar en la base de datos
	 */
	public void insertar(java.util.Collection<ACH> acach_archivo)
	    throws B2BException
	{
		if(com.b2bsg.common.util.CollectionUtils.isValidCollection(acach_archivo))
		{
			java.sql.PreparedStatement lps_insercion;
			Long                       ll_cus;

			ll_cus            = null;
			lps_insercion     = null;

			try
			{
				lps_insercion = getConnection().prepareStatement(cs_INSERTAR);

				for(ACH lach_linea : acach_archivo)
				{
					if(lach_linea != null)
					{
						int li_column;

						li_column     = 1;
						ll_cus        = lach_linea.getCus();

						lps_insercion.clearParameters();

						lps_insercion.setString(li_column++, lach_linea.getIdArchivo());

						lps_insercion.setInt(li_column++, lach_linea.getRegistro());

						lps_insercion.setString(li_column++, lach_linea.getIdCuenta());
						lps_insercion.setString(li_column++, lach_linea.getIdPeriodoCorte());

						setLong(lps_insercion, lach_linea.getCus(), li_column++);

						setDouble(lps_insercion, lach_linea.getValor(), li_column++);

						lps_insercion.setString(li_column++, lach_linea.getCodigoComercio());
						lps_insercion.setString(li_column++, lach_linea.getNombreComercio());
						lps_insercion.setString(li_column++, lach_linea.getEstadoComercio());
						lps_insercion.setString(li_column++, lach_linea.getCodigoAutorizacion());

						setDate(lps_insercion, lach_linea.getFechaCreada(), li_column++);
						setDate(lps_insercion, lach_linea.getFechaUltimoEstado(), li_column++);

						setDouble(lps_insercion, lach_linea.getImpuesto(), li_column++);

						setLong(lps_insercion, lach_linea.getTicketId(), li_column++);
						setLong(lps_insercion, lach_linea.getCicloOrigen(), li_column++);
						setLong(lps_insercion, lach_linea.getCicloTransaccion(), li_column++);

						lps_insercion.setString(li_column++, lach_linea.getDescripcion());

						setDate(lps_insercion, lach_linea.getFechaAutorizacion(), li_column++);

						setLong(lps_insercion, lach_linea.getCodigoServicio(), li_column++);

						lps_insercion.setString(li_column++, lach_linea.getNombreServicio());
						lps_insercion.setString(li_column++, lach_linea.getNitServicio());
						lps_insercion.setString(li_column++, lach_linea.getTipoUsuario());
						lps_insercion.setString(li_column++, lach_linea.getTipoTransaccion());
						lps_insercion.setString(li_column++, lach_linea.getBancoRecaudador());
						lps_insercion.setString(li_column++, lach_linea.getModalidadVinculacion());
						lps_insercion.setString(li_column++, lach_linea.getIdFuncionalidad());
						lps_insercion.setString(li_column++, lach_linea.getNombreFuncionalidad());
						lps_insercion.setString(li_column++, lach_linea.getReferencia1());
						lps_insercion.setString(li_column++, lach_linea.getReferencia2());
						lps_insercion.setString(li_column++, lach_linea.getReferencia3());
						lps_insercion.setString(li_column++, lach_linea.getBancoOrigen());

						setDate(lps_insercion, lach_linea.getFechaUltimoEstado(), li_column++);

						lps_insercion.setString(li_column++, lach_linea.getIdTaquilla());
						lps_insercion.setString(li_column++, lach_linea.getNombreTaquilla());
						lps_insercion.setString(li_column++, lach_linea.getTipoAutorizacion());

						lps_insercion.setString(li_column++, lach_linea.getIdUsuarioCreacion());
						lps_insercion.setString(li_column++, lach_linea.getIpCreacion());

						lps_insercion.executeUpdate();
					}
				}
			}
			catch(java.sql.SQLException lse_e)
			{
				System.err.println(ll_cus);

				logError(this, "insertar", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_insercion);
			}
		}
	}
}
