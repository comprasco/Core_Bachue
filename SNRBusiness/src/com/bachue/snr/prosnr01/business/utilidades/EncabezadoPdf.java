package com.bachue.snr.prosnr01.business.utilidades;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;


/**
 * Clase que contiene todos la logica para generar encabezado para documentos PDF
 *
 * @author Edgar Prieto
 */
public class EncabezadoPdf extends PdfPageEventHelper
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EncabezadoPdf.class);

	/** Propiedad ii image 1. */
	protected Image ii_image1;

	/** Propiedad ii image 2. */
	protected Image ii_image2;

	/** Propiedad ii image 3. */
	protected Image ii_image3;

	/** Propiedad ipcb table. */
	protected PdfContentByte ipcb_table;

	/**
	 * Instancia un nuevo objeto encabezado pdf.
	 *
	 * @param abs_imageSnr correspondiente al valor del tipo de objeto byte[]
	 * @param abs_imageMinJusticia correspondiente al valor del tipo de objeto byte[]
	 * @param abs_imageProsperidad correspondiente al valor del tipo de objeto byte[]
	 */
	public EncabezadoPdf(byte[] abs_imageSnr, byte[] abs_imageMinJusticia, byte[] abs_imageProsperidad)
	{
		try
		{
			ii_image1     = Image.getInstance(abs_imageSnr);
			ii_image2     = Image.getInstance(abs_imageMinJusticia);
			ii_image3     = Image.getInstance(abs_imageProsperidad);
		}
		catch(IOException lioe_e)
		{
			clh_LOGGER.error("EncabezadoPdf", lioe_e);
		}
		catch(DocumentException ade_e)
		{
			clh_LOGGER.error("EncabezadoPdf", ade_e);
		}
	}

	/**
	 * Instancia un nuevo objeto encabezado pdf.
	 *
	 * @param apw_writer correspondiente al valor del tipo de objeto PdfWriter
	 * @param ad_document correspondiente al valor del tipo de objeto Document
	 */
	public EncabezadoPdf(PdfWriter apw_writer, Document ad_document)
	{
	}

	/**
	 * Agrega a un documento en construcción el encabezado con los logos del gobierno.
	 *
	 * @param apw_writer Objeto utilizado para escribir los logos en el documento
	 * @param ad_documento Objeto contenedor de la información del documento
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public static synchronized void construirEncabezadoDocumento(
	    PdfWriter apw_writer, Document ad_documento, DAOManager adm_manager
	)
	    throws B2BException
	{
		if((apw_writer != null) && (ad_documento != null))
		{
			byte[] lba_logoSNR;
			byte[] lba_logoMinjusticia;
			byte[] lba_logo3;

			lba_logoSNR             = null;
			lba_logoMinjusticia     = null;
			lba_logo3               = null;

			{
				ConstantesDAO lDAO_DAO;
				Constantes    lc_constante;

				lDAO_DAO = DaoCreator.getConstantesDAO(adm_manager);

				{
					lc_constante = new Constantes();

					lc_constante.setIdConstante(ConstanteCommon.IMAGEN_LOGO_SNR);

					lc_constante = lDAO_DAO.findImagen(lc_constante);

					if(lc_constante != null)
						lba_logoSNR = lc_constante.getImagenes();
				}

				{
					lc_constante = new Constantes();

					lc_constante.setIdConstante(ConstanteCommon.IMAGEN_LOGO_MINJUSTICIA);

					lc_constante = lDAO_DAO.findImagen(lc_constante);

					if(lc_constante != null)
						lba_logoMinjusticia = lc_constante.getImagenes();
				}

				{
					lc_constante = new Constantes();

					lc_constante.setIdConstante(ConstanteCommon.IMAGEN_LOGO_3);

					lc_constante = lDAO_DAO.findImagen(lc_constante);

					if(lc_constante != null)
						lba_logo3 = lc_constante.getImagenes();
				}
			}

			{
				EncabezadoPdf lep_encabezadoPdf;

				if((lba_logoSNR != null) && (lba_logoMinjusticia != null) && (lba_logo3 != null))
					lep_encabezadoPdf = new EncabezadoPdf(lba_logoSNR, lba_logoMinjusticia, lba_logo3);
				else
					lep_encabezadoPdf = new EncabezadoPdf(apw_writer, ad_documento);

				apw_writer.setPageEvent(lep_encabezadoPdf);
			}
		}
	}

	/**
	 *
	 * @param apw_writer correspondiente al valor del tipo de objeto PdfWriter
	 * @param ad_document correspondiente al valor del tipo de objeto Document
	 */
	public void onEndPage(PdfWriter apw_writer, Document ad_document)
	{
		try
		{
			ipcb_table = apw_writer.getDirectContentUnder();

			ii_image1.scaleToFit(150, 150);
			ii_image1.setAbsolutePosition(50, 720);

			ipcb_table.addImage(ii_image1);

			ii_image2.scaleToFit(90, 90);
			ii_image2.setAbsolutePosition(350, 750);

			ipcb_table.addImage(ii_image2);

			ii_image3.scaleToFit(90, 90);
			ii_image3.setAbsolutePosition(450, 750);

			ipcb_table.addImage(ii_image3);
		}
		catch(Exception ae_e)
		{
			clh_LOGGER.error("EncabezadoPdf", ae_e);
		}
	}
}
