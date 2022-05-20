package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;

import com.bachue.snr.prosnr21.model.pgn.ConImagenes;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_IMAGENES.
 *
 * @author Gabriel Arias
 */
public class ConImagenesDAO extends AuditoriaDao
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ImagenesDAO.class);

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_CON_IMAGENES_ID_IMAGEN.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_CON_IMAGENES SET IMAGEN_BLOB = ?,TIPO_ARCHIVO = ?, ESTADO=?, TAMANO = ?,ID_TURNO = ?,CODIGO_VERIFICACION = ?,GENERA_QR = ? ,FECHA_MODIFICACION = SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ?,IP_MODIFICACION=? WHERE ID_IMAGEN = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_CON_IMAGENES(ID_IMAGEN,IMAGEN_BLOB,TIPO_ARCHIVO,ESTADO,TAMANO,ID_TURNO,CODIGO_VERIFICACION,GENERA_QR,FECHA_CREACION,ID_USUARIO_CREACION,IP_CREACION)VALUES(?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_CON_IMAGENES WHERE ID_IMAGEN=?";

	/**
	 * Find by id.
	 *
	 * @param al_idImagen the al id imagen
	 * @return the con imagenes
	 * @throws B2BException the b 2 B exception
	 */
	public ConImagenes findById(long al_idImagen)
	    throws B2BException
	{
		ConImagenes lci_imagenes;

		lci_imagenes = null;

		if(al_idImagen > 0)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(1, al_idImagen);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lci_imagenes = getObjetFromResultSet(lrs_rs);
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

		return lci_imagenes;
	}

	/**
	 * Retorna el valor del objeto de tipo Insert or update.
	 *
	 * @param aci_param correspondiente al valor del tipo de objeto Imagenes
	 * @param ab_insert correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insertOrUpdate(ConImagenes aci_param, boolean ab_insert)
	    throws B2BException
	{
		return insertOrUpdate(aci_param, ab_insert, false, false);
	}

	/**
	 * Retorna el valor del objeto de tipo Insert or update.
	 *
	 * @param aci_param correspondiente al valor del tipo de objeto Imagenes
	 * @param ab_insert correspondiente al valor del tipo de objeto boolean
	 * @param ab_generarQR correspondiente al valor del tipo de objeto boolean
	 * @param ab_firma correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insertOrUpdate(ConImagenes aci_param, boolean ab_insert, boolean ab_generarQR, boolean ab_firma)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(aci_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;

				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(ab_insert ? cs_INSERT : cs_UPDATE);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				if(ab_insert)
				{
					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						ll_secuencia = lrs_rs.getLong(1);

						lps_ps.setLong(li_column++, ll_secuencia);
					}
				}

				{
					byte[] lba_imagenBlob;

					lba_imagenBlob = aci_param.getImagenBlob();

					if(lba_imagenBlob != null)
					{
						InputStream lis_in;

						lis_in = new ByteArrayInputStream(lba_imagenBlob);

						lps_ps.setBinaryStream(li_column++, lis_in);
						lps_ps.setString(li_column++, aci_param.getTipoArchivo());
						lps_ps.setString(li_column++, aci_param.getEstado());
						setLong(lps_ps, aci_param.getTamano(), li_column++);
						lps_ps.setString(li_column++, aci_param.getIdTurno());
						lps_ps.setString(li_column++, aci_param.getCodigoVerificacion());
						lps_ps.setString(
						    li_column++,
						    aci_param.isGeneraQR() ? com.bachue.snr.prosnr21.common.constants.EstadoCommon.S
						                           : com.bachue.snr.prosnr21.common.constants.EstadoCommon.N
						);

						lis_in.close();

						if(ab_insert)
						{
							lps_ps.setString(li_column++, aci_param.getIdUsuarioCreacion());
							lps_ps.setString(li_column++, aci_param.getIpCreacion());
						}
						else
						{
							lps_ps.setString(li_column++, aci_param.getIdUsuarioModificacion());
							lps_ps.setString(li_column++, aci_param.getIpModificacion());

							lps_ps.setLong(li_column++, aci_param.getIdImagen());
						}

						lps_ps.executeUpdate();
					}
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			catch(IOException lie_e)
			{
				throw new B2BException(SQL_ERROR, lie_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return ll_secuencia;
	}

	/**
	 * Retorna el valor de Imagenes.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Imagenes
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private ConImagenes getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ConImagenes li_imagenes;
		byte[]      lba_blob;
		Blob        lb_blob;

		li_imagenes     = new ConImagenes();
		lb_blob         = ars_rs.getBlob("IMAGEN_BLOB");

		try
		{
			lba_blob = IOUtils.toByteArray(lb_blob.getBinaryStream());
		}
		catch(Exception e)
		{
			lba_blob = null;
		}

		li_imagenes.setImagenBlob(lba_blob);
		li_imagenes.setIdImagen(ars_rs.getLong("ID_IMAGEN"));
		li_imagenes.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
		li_imagenes.setIdTurno(ars_rs.getString("ID_TURNO"));
		li_imagenes.setCodigoVerificacion(ars_rs.getString("CODIGO_VERIFICACION"));
		li_imagenes.setGeneraQR(BooleanUtils.getBooleanValue(ars_rs.getString("GENERA_QR")));

		return li_imagenes;
	}
}
