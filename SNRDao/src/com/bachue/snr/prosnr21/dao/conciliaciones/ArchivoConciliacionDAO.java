package com.bachue.snr.prosnr21.dao.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr21.model.conciliaciones.ArchivoConciliacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_ARCHIVO del módulo de conciliaciones
 *
 * @author Edgar Prieto
 */
public class ArchivoConciliacionDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Sentencia SQL para la inserción de un registro */
	private static final String cs_INSERTAR = "INSERT INTO SDB_CON_ARCHIVO(ID_ARCHIVO,ID_CUENTA,ID_PERIODO_CORTE,"
		+ "VERSION_ARCHIVO,TIPO_ARCHIVO,NOMBRE_ARCHIVO,ESTADO_ARCHIVO,ID_USUARIO_CREACION,IP_CREACION,ARCHIVO,"
		+ "FECHA_CREACION)VALUES(?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP)";

	/** Sentencia SQL para obtener un registro por llave primaria */
	private static final String cs_ENCONTRAR_POR_ID = "SELECT ID_ARCHIVO,ID_CUENTA,ID_PERIODO_CORTE,VERSION_ARCHIVO,"
		+ "TIPO_ARCHIVO,NOMBRE_ARCHIVO,ESTADO_ARCHIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,"
		+ "ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_CON_ARCHIVO WHERE ID_ARCHIVO=?";

	/** Sentencia SQL para determinar el siguiente valor de la secuencia que genera la llave primara  */
	private static final String cs_SECUENCIA = "SELECT SEC_CON_ARCHIVO_ID_ARCHIVO.NEXTVAL FROM DUAL";

	/** Sentencia SQL para determianr el valor de versión de un archivo */
	private static final String cs_VERSION = "SELECT NVL(MAX(VERSION_ARCHIVO),0)+1 FROM SDB_CON_ARCHIVO WHERE "
		+ "ID_CUENTA=? AND ID_PERIODO_CORTE=? AND TIPO_ARCHIVO=?";

	/**
	 * Metodo para encontrar un registro de la tabla SDB_CON_ARCHIVO por llave primaria
	 * @param as_id Llave primaria del registro a encontrar
	 * @return registro de la tabla SDB_CON_ARCHIVO que coincide con la llave primaria
	 * @throws B2BException
	 */
	public ArchivoConciliacion encontrarPorId(String as_id)
	    throws B2BException
	{
		ArchivoConciliacion lac_respuesta;

		lac_respuesta = null;

		if(com.b2bsg.common.util.StringUtils.isValidString(as_id))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_ENCONTRAR_POR_ID);

				lps_ps.setString(1, as_id);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lac_respuesta = obtener(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "encontrarPorId", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lac_respuesta;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_CON_ARCHIVO
	 *  @param aa_archivo Registro a insertar en la base de datos
	 */
	public ArchivoConciliacion insertar(ArchivoConciliacion aa_archivo)
	    throws B2BException
	{
		ArchivoConciliacion lac_respuesta;

		lac_respuesta = null;

		if(aa_archivo != null)
		{
			PreparedStatement lps_insercion;
			PreparedStatement lps_secuencia;
			PreparedStatement lps_version;
			ResultSet         lrs_secuencia;
			ResultSet         lrs_version;

			lps_insercion     = null;
			lps_secuencia     = null;
			lps_version       = null;
			lrs_secuencia     = null;
			lrs_version       = null;

			try
			{
				java.sql.Connection lc_connection;
				int                 li_column;
				long                ll_version;
				String              ls_id;
				String              ls_idCuenta;
				String              ls_idPeriodoCorte;
				String              ls_tipoArchivo;

				li_column             = 1;
				lc_connection         = getConnection();
				lps_insercion         = lc_connection.prepareStatement(cs_INSERTAR);
				lps_secuencia         = lc_connection.prepareStatement(cs_SECUENCIA);
				lps_version           = lc_connection.prepareStatement(cs_VERSION);
				lrs_secuencia         = lps_secuencia.executeQuery();
				ls_id                 = null;
				ls_idCuenta           = aa_archivo.getIdCuenta();
				ls_idPeriodoCorte     = aa_archivo.getIdPeriodoCorte();
				ls_tipoArchivo        = aa_archivo.getTipoArchivo();

				if(lrs_secuencia.next())
				{
					Object lo_o;

					lo_o = lrs_secuencia.getObject(1);

					if((lo_o != null) && lo_o instanceof java.math.BigDecimal)
						ls_id = lo_o.toString();
					else
						throw new B2BException(ErrorKeys.CON_ARCHIVO_SEQUENCE);
				}

				lps_version.setString(li_column++, ls_idCuenta);
				lps_version.setString(li_column++, ls_idPeriodoCorte);
				lps_version.setString(li_column++, ls_tipoArchivo);

				lrs_version     = lps_version.executeQuery();
				ll_version      = lrs_version.next() ? lrs_version.getLong(1) : 1L;
				li_column       = 1;

				lps_insercion.setString(li_column++, ls_id);
				lps_insercion.setString(li_column++, ls_idCuenta);
				lps_insercion.setString(li_column++, ls_idPeriodoCorte);

				lps_insercion.setLong(li_column++, ll_version);

				lps_insercion.setString(li_column++, ls_tipoArchivo);
				lps_insercion.setString(li_column++, aa_archivo.getNombreArchivo());
				lps_insercion.setString(li_column++, aa_archivo.getEstadoArchivo());
				lps_insercion.setString(li_column++, aa_archivo.getIdUsuarioCreacion());
				lps_insercion.setString(li_column++, aa_archivo.getIpCreacion());

				{
					byte[] lba_archivo;

					lba_archivo = aa_archivo.getArchivo();

					if(lba_archivo != null)
					{
						java.io.InputStream lis_archivo;

						lis_archivo = new java.io.ByteArrayInputStream(lba_archivo);

						lps_insercion.setBinaryStream(li_column++, lis_archivo);

						lis_archivo.close();
					}
				}

				lps_insercion.executeUpdate();

				lac_respuesta = encontrarPorId(ls_id);
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertar", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			catch(java.io.IOException lie_e)
			{
				throw new B2BException(SQL_ERROR, lie_e);
			}
			finally
			{
				close(lrs_secuencia);
				close(lrs_version);

				close(lps_insercion);
				close(lps_secuencia);
				close(lps_version);
			}
		}

		return lac_respuesta;
	}

	/**
	 * Metodo que se encarga de llenar un objeto de tipo Archivo con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto Archivo
	 * @return Objeto de tipo Archivo con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private ArchivoConciliacion obtener(ResultSet ars_rs)
	    throws SQLException
	{
		ArchivoConciliacion lac_archivo;

		lac_archivo = new ArchivoConciliacion();

		lac_archivo.setEstadoArchivo(ars_rs.getString("ESTADO_ARCHIVO"));
		lac_archivo.setId(ars_rs.getString("ID_ARCHIVO"));
		lac_archivo.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lac_archivo.setIdPeriodoCorte(ars_rs.getString("ID_PERIODO_CORTE"));
		lac_archivo.setNombreArchivo(ars_rs.getString("NOMBRE_ARCHIVO"));
		lac_archivo.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
		lac_archivo.setVersion(getLong(ars_rs, "VERSION_ARCHIVO"));

		fillAuditoria(ars_rs, lac_archivo);

		return lac_archivo;
	}
}
