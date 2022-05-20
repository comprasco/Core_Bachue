package com.bachue.snr.prosnr01.dao.bgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;

import com.google.zxing.EncodeHintType;

import com.google.zxing.qrcode.QRCodeWriter;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import org.apache.commons.io.IOUtils;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;


/**
 * Clase que contiene todos las consultas de la tabla SDB_BGN_IMAGENES
 *
 * @author hcastaneda
 */
public class ImagenesDAO extends BaseDAO
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ImagenesDAO.class);

	/** Constante cs_FIND_ID. */
	private static final String cs_FIND_ID = "SELECT * FROM SDB_BGN_IMAGENES WHERE ID_IMAGEN=?";

	/** Constante cs_DELETE_IMAGEN. */
	private static final String cs_DELETE_IMAGEN = "DELETE FROM SDB_BGN_IMAGENES WHERE ID_IMAGEN=?";

	/** Constante cs_FIND_BY_CODIGO_VERIFICACION. */
	private static final String cs_FIND_BY_CODIGO_VERIFICACION = "SELECT * FROM SDB_BGN_IMAGENES SBI INNER JOIN SDB_ACC_DOCUMENTOS_SALIDA SADS ON NVL(SADS.ESTADO,'A') <> 'I' AND SADS.ID_IMAGEN = SBI.ID_IMAGEN WHERE SBI.CODIGO_VERIFICACION = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_BGN_IMAGENES_ID_IMAGEN.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_URL. */
	private static final String cs_FIND_URL = "SELECT CARACTER FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE=?";

	/** Constante cs_FIND_ORA_HASH. */
	private static final String cs_FIND_ORA_HASH = "SELECT SUBSTR('00000000' || TO_CHAR(ORA_HASH(?,'99999999')),-8) CODIGO FROM DUAL";

	/** Constante cs_DELETE_CODIGO_VERIFICACION. */
	private static final String cs_DELETE_CODIGO_VERIFICACION = "UPDATE SDB_BGN_IMAGENES SET CODIGO_VERIFICACION = ? WHERE ID_IMAGEN = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_BGN_IMAGENES SET IMAGEN_BLOB = ?,TIPO_ARCHIVO = ?,"
		+ "TAMANO = ?,ID_TURNO = ?,CODIGO_VERIFICACION = ?,GENERA_QR = ? ,FECHA_MODIFICACION = SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ?,IP_MODIFICACION=? WHERE ID_IMAGEN = ?";

	/** Constante cs_UPDATE_FILE. */
	private static final String cs_UPDATE_FILE = "UPDATE SDB_BGN_IMAGENES SET IMAGEN_BLOB = NULL,"
		+ "FECHA_MODIFICACION = SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ?,IP_MODIFICACION=? WHERE ID_IMAGEN = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_BGN_IMAGENES(ID_IMAGEN,IMAGEN_BLOB,TIPO_ARCHIVO,"
		+ "TAMANO,ID_TURNO,CODIGO_VERIFICACION,GENERA_QR,FECHA_CREACION,ID_USUARIO_CREACION,IP_CREACION)VALUES(?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/**
	 * Retorna el valor del objeto de tipo Imagenes, borrando el registro por ID
	 *
	 * @param ai_param correspondiente al valor del tipo de objeto Imagenes
	 * @return devuelve el valor del objeto imagenes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Imagenes
	 */
	public Imagenes deleteById(Imagenes ai_param)
	    throws B2BException
	{
		Imagenes          ld_Imagenes;
		PreparedStatement lps_ps;

		ld_Imagenes     = null;
		lps_ps          = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_DELETE_IMAGEN);

			lps_ps.setLong(1, ai_param.getIdImagen());

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "findById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}

		return ld_Imagenes;
	}

	/**
	 * Método encargado de eliminar el código de verificación.
	 *
	 * @param al_idImagen correspondiente al valor del tipo de objeto Long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void eliminarCodigoVerificacion(Long al_idImagen)
	    throws B2BException
	{
		if(NumericUtils.isValidLong(al_idImagen))
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_CODIGO_VERIFICACION);

				lps_ps.setString(li_column++, null);
				setLong(lps_ps, al_idImagen, li_column++);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "eliminarCodigoVerificacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 *  Metodo encargado de buscar de la tabla SDB_ACC_IMAGENES un registro por un CODIGO_VERIFICACION.
	 *
	 * @param as_parametro Objeto de tipo String que contiene el codigo verificacion a buscar
	 * @return Objeto de tipo DocuemntosSalida con la informacion consultada
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Imagenes findByCodigoVerificacion(String as_parametro)
	    throws B2BException
	{
		Imagenes lds_return;

		lds_return = null;

		if(StringUtils.isValidString(as_parametro))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CODIGO_VERIFICACION);

				lps_ps.setString(li_contador++, as_parametro);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCodigoVerificacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_return;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param ai_param Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Imagenes resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Imagenes findById(Imagenes ai_param)
	    throws B2BException
	{
		return (ai_param != null) ? findById(ai_param.getIdImagen()) : null;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param al_idImagen Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Imagenes resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Imagenes findById(long al_idImagen)
	    throws B2BException
	{
		Imagenes li_imagenes;

		li_imagenes = null;

		if(al_idImagen > 0)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ID);

				lps_ps.setLong(1, al_idImagen);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_imagenes = getObjetFromResultSet(lrs_rs);
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

		return li_imagenes;
	}

	/**
	 * Retorna el valor la secuencia de la tabla
	 *
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuence()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/**
	 * Método encargado de generar codigo de convenio para código QR.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene el turno historia del proceso para generar el código QR
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String generarCodigoConvenio(Long al_idTurnoHistoria)
	    throws B2BException
	{
		PreparedStatement lps_oraHash;
		ResultSet         lrs_oraHash;
		String            ls_return;

		lps_oraHash     = null;
		lrs_oraHash     = null;
		ls_return       = null;

		try
		{
			Date   ld_fecha;
			String ls_fecha;
			String ls_codigoOraHash;

			ld_fecha             = new Date();
			ls_fecha             = StringUtils.getString(new Date(), FormatoFechaCommon.ANIO_MES_DIA);
			lps_oraHash          = getConnection().prepareStatement(cs_FIND_ORA_HASH);
			ls_codigoOraHash     = null;

			lps_oraHash.setString(1, IdentificadoresCommon.DATO_INVALIDO + al_idTurnoHistoria + ld_fecha.getTime());

			lrs_oraHash = lps_oraHash.executeQuery();

			if(lrs_oraHash.next())
				ls_codigoOraHash = lrs_oraHash.getString(1);

			if(StringUtils.isValidString(ls_codigoOraHash))
				ls_return = ls_fecha + ConstanteCommon.CODIGO_CONVENIO_QR + ls_codigoOraHash;

			if(!StringUtils.isValidString(ls_return))
				throw new B2BException(ErrorKeys.ERROR_CODIGO_CONVENIO_QR);
		}
		catch(SQLException lse_e)
		{
			logError(this, "generarCodigoConvenio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_oraHash);
			close(lrs_oraHash);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Insert or update.
	 *
	 * @param ai_param correspondiente al valor del tipo de objeto Imagenes
	 * @param ab_insert correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insertOrUpdate(Imagenes ai_param, boolean ab_insert)
	    throws B2BException
	{
		return insertOrUpdate(ai_param, ab_insert, false, false);
	}

	/**
	 * Retorna el valor del objeto de tipo Insert or update.
	 *
	 * @param ai_param correspondiente al valor del tipo de objeto Imagenes
	 * @param ab_insert correspondiente al valor del tipo de objeto boolean
	 * @param ab_generarQR correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insertOrUpdate(Imagenes ai_param, boolean ab_insert, boolean ab_generarQR)
	    throws B2BException
	{
		return insertOrUpdate(ai_param, ab_insert, ab_generarQR, false);
	}

	/**
	 * Retorna el valor del objeto de tipo Insert or update.
	 *
	 * @param ai_param correspondiente al valor del tipo de objeto Imagenes
	 * @param ab_insert correspondiente al valor del tipo de objeto boolean
	 * @param ab_generarQR correspondiente al valor del tipo de objeto boolean
	 * @param ab_firma correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insertOrUpdate(Imagenes ai_param, boolean ab_insert, boolean ab_generarQR, boolean ab_firma)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(ai_param != null)
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

					lba_imagenBlob = ai_param.getImagenBlob();

					if(lba_imagenBlob != null)
					{
						InputStream lis_in;

						if(ab_generarQR)
						{
							StringBuilder lsb_sb;
							String        ls_urlQR;

							lsb_sb       = new StringBuilder();
							ls_urlQR     = null;

							{
								PreparedStatement lps_qr;
								ResultSet         lrs_qr;

								lps_qr     = null;
								lrs_qr     = null;

								try
								{
									lps_qr = lc_connection.prepareStatement(cs_FIND_URL);

									lps_qr.setString(1, ConstanteCommon.URL_QR);

									lrs_qr = lps_qr.executeQuery();

									if(lrs_qr.next())
										ls_urlQR = lrs_qr.getString(1);
								}
								catch(SQLException lse_e)
								{
									logError(this, "consultarURLQr", lse_e);

									throw new B2BException(SQL_ERROR, lse_e);
								}
								finally
								{
									close(lps_qr);
									close(lrs_qr);
								}
							}

							if(!StringUtils.isValidString(ls_urlQR))
								throw new B2BException(ErrorKeys.ERROR_URL_QR);

							{
								lsb_sb.append(ls_urlQR);
								lsb_sb.append(IdentificadoresCommon.SIMBOLO_PREGUNTA_CIERRE);
								lsb_sb.append(IdentificadoresCommon.PARAMETRO_CODIGO_VERIFICACION);
								lsb_sb.append(ai_param.getCodigoVerificacion());
								lsb_sb.append(IdentificadoresCommon.SIMBOLO_AMPERSAND);
								lsb_sb.append(IdentificadoresCommon.PARAMETRO_TURNO);
								lsb_sb.append(ai_param.getIdTurno());

								ai_param.setGeneraQR(true);
							}

							if(!StringUtils.isValidString(lsb_sb.toString()))
								throw new B2BException(ErrorKeys.ERROR_URL_QR);

							lba_imagenBlob = generateQR(lba_imagenBlob, lsb_sb.toString());
						}

						lis_in = new ByteArrayInputStream(lba_imagenBlob);

						lps_ps.setBinaryStream(li_column++, lis_in);

						lis_in.close();
					}
				}

				lps_ps.setString(li_column++, ai_param.getTipoArchivo());
				setLong(lps_ps, ai_param.getTamano(), li_column++);
				lps_ps.setString(li_column++, ai_param.getIdTurno());
				lps_ps.setString(li_column++, ai_param.getCodigoVerificacion());
				lps_ps.setString(li_column++, ai_param.isGeneraQR() ? EstadoCommon.S : EstadoCommon.N);

				if(ab_insert)
				{
					lps_ps.setString(li_column++, ai_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ai_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ai_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ai_param.getIpModificacion());

					lps_ps.setLong(li_column++, ai_param.getIdImagen());
				}

				lps_ps.executeUpdate();
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
			catch(DocumentException lde_e)
			{
				throw new B2BException(SQL_ERROR, lde_e);
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
	 * Método encargado de limpiar el byte[] del campo IMAGEN_BLOB a NULL si el indicador de documento salida esta en S.
	 *
	 * @param ai_param Objeto que contiene la llave para realizar la limpieza del campo IMAGEN_BLOB
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateFile(Imagenes ai_param)
	    throws B2BException
	{
		if(ai_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_FILE);

				lps_ps.setString(li_column++, ai_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ai_param.getIpModificacion());

				lps_ps.setLong(li_column++, ai_param.getIdImagen());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateFile", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de Imagenes
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Imagenes
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Imagenes getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Imagenes li_imagenes;
		byte[]   lba_blob;
		Blob     lb_blob;

		li_imagenes     = new Imagenes();
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

	/**
	 * Retorna el valor del objeto de tipo BufferedImage correspondiente a la imagen
	 *
	 * @param al_id correspondiente al valor del tipo de objeto long
	 * @param as_url correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto buffered image
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private static BufferedImage generateQR(long al_id, String as_url)
	    throws B2BException
	{
		BufferedImage lbi_image;
		int           li_margin;
		int           li_size;

		lbi_image     = null;
		li_margin     = 1;
		li_size       = 39;

		System.setProperty("java.awt.headless", "true");

		try
		{
			com.google.zxing.common.BitMatrix lbm_matrix;
			Graphics2D                        lg2d_graphics;
			int                               li_width;
			QRCodeWriter                      lqrcw_writer;

			lqrcw_writer = new QRCodeWriter();

			{
				Map<EncodeHintType, Object> lmehyo_properties;

				lmehyo_properties = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);

				lmehyo_properties.put(EncodeHintType.CHARACTER_SET, "UTF-8");
				lmehyo_properties.put(EncodeHintType.MARGIN, NumericUtils.getInteger(li_margin));

				lmehyo_properties.put(
				    EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L
				);

				lbm_matrix = lqrcw_writer.encode(
					    as_url, com.google.zxing.BarcodeFormat.QR_CODE, li_size, li_size, lmehyo_properties
					);
			}

			li_width      = lbm_matrix.getWidth();
			lbi_image     = new BufferedImage(li_width, li_width, BufferedImage.TYPE_INT_RGB);

			lbi_image.createGraphics();

			lg2d_graphics = (Graphics2D)lbi_image.getGraphics();

			lg2d_graphics.setColor(Color.WHITE);
			lg2d_graphics.fillRect(0, 0, li_width, li_width);

			lg2d_graphics.setColor(Color.BLACK);

			for(int li_x = 0; li_x < li_width; li_x++)
			{
				for(int li_y = 0; li_y < li_width; li_y++)
				{
					if(lbm_matrix.get(li_x, li_y))
						lg2d_graphics.fillRect(li_x, li_y, 1, 1);
				}
			}
		}
		catch(com.google.zxing.WriterException lwe_e)
		{
			clh_LOGGER.error("generateQR", lwe_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, lwe_e);
		}

		return lbi_image;
	}

	/**
	 * Retorna el valor de <code>byte[]</code> correspondiente al codigo QR
	 *
	 * @param aba_image correspondiente al valor del tipo de objeto byte[]
	 * @param as_url correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto byte[]
	 * @throws DocumentException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private byte[] generateQR(byte[] aba_image, String as_url)
	    throws DocumentException, IOException, B2BException
	{
		byte[] lba_pdf;

		lba_pdf = null;

		if(aba_image != null)
		{
			BufferedImage lbi_qrCode;

			lbi_qrCode = generateQR(new Date().getTime(), as_url);

			if(lbi_qrCode != null)
			{
				byte[]    lba_oldPdf;
				float     lf_x;
				float     lf_y;
				PdfReader lpr_oldPdf;

				lba_oldPdf     = aba_image;
				lpr_oldPdf     = new PdfReader(lba_oldPdf);

				{
					Rectangle lr_page;

					lr_page     = lpr_oldPdf.getPageSize(1);
					lf_x        = lr_page.getWidth() - NumericUtils.getFloat(lbi_qrCode.getWidth());
					lf_y        = lr_page.getHeight() - NumericUtils.getFloat(lbi_qrCode.getHeight()) - 0f;
				}

				{
					ByteArrayOutputStream lbaos_newPdf;
					ByteArrayOutputStream lbaos_qrCode;
					PdfStamper            lps_oldPdf;

					lbaos_newPdf     = new ByteArrayOutputStream();
					lbaos_qrCode     = new ByteArrayOutputStream();
					lps_oldPdf       = new PdfStamper(lpr_oldPdf, lbaos_newPdf);

					ImageIO.write(lbi_qrCode, ExtensionCommon.PNG, lbaos_qrCode);

					{
						Image li_qrCode;

						li_qrCode = Image.getInstance(lbaos_qrCode.toByteArray());

						li_qrCode.setAbsolutePosition(lf_x, lf_y);

						lps_oldPdf.getOverContent(1).addImage(li_qrCode);
					}

					lbaos_qrCode.flush();

					lbaos_qrCode.close();

					lps_oldPdf.close();
					lpr_oldPdf.close();

					lba_pdf = lbaos_newPdf.toByteArray();

					lbaos_newPdf.flush();
					lbaos_newPdf.close();
				}

				lbi_qrCode.flush();
			}
		}

		return lba_pdf;
	}
}
