package com.bachue.snr.prosnr01.business.utilidades;

import com.b2bsg.common.logging.LoggerHandler;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;


/**
 * Clase que contiene todos la lógica para generar marca de agua en documentos PDF
 *
 * @author hcastaneda
 */
public class MarcaDeAguaPdf extends PdfPageEventHelper
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MarcaDeAguaPdf.class);

	/** Propiedad ii image. */
	protected Image ii_image;

	/** Propiedad ipcb pdf Content Byte. */
	protected PdfContentByte ipcb_pdfContentByte;

	/**
	 * Instancia un nuevo objeto MarcaDeAguaPdf.
	 *
	 * @param abs_imagen correspondiente al valor del tipo de objeto byte[]
	 */
	public MarcaDeAguaPdf(byte[] abs_imagen)
	{
		try
		{
			ii_image = Image.getInstance(abs_imagen);
		}
		catch(IOException lioe_e)
		{
			clh_LOGGER.error("MarcaDeAguaPdf", lioe_e);
		}
		catch(DocumentException ade_e)
		{
			clh_LOGGER.error("MarcaDeAguaPdf", ade_e);
		}
	}

	/**
	 * Instancia un nuevo objeto encabezado pdf.
	 *
	 * @param apw_writer correspondiente al valor del tipo de objeto PdfWriter
	 * @param ad_document correspondiente al valor del tipo de objeto Document
	 */
	public MarcaDeAguaPdf(PdfWriter apw_writer, Document ad_document)
	{
	}

	/** {@inheritdoc} */
	public void onEndPage(PdfWriter apw_writer, Document ad_document)
	{
		try
		{
			ipcb_pdfContentByte = apw_writer.getDirectContentUnder();

			ii_image.scaleToFit(150, 150);
			ii_image.setAbsolutePosition(50, 720);

			ipcb_pdfContentByte.addImage(ii_image);
		}
		catch(Exception ae_e)
		{
			clh_LOGGER.error("onEndPage", ae_e);
		}
	}
}
