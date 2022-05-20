package com.bachue.snr.prosnr01.business.utilidades;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.PdfSaveOptions;
import com.aspose.words.SaveFormat;
import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;


/**
 * Clase para el manejo del negocio de PDFgenerator para poder convertir de un archivo rtf a pdf
 * @author Alejandro Guayambuco
 *
 */
public class PDFGenerator
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(PDFGenerator.class);

	/**
	 * Método para realizar la conversión de archivo rtf a pdf.
	 *
	 * @param aba_rtf byte[] arreglo de bytes en rtf
	 * @param adm_DAOManager Objeto que se encarga de conectar con la capa de los dao para hacer conexiones con la base de datos
	 * @return devuelve el valor de byte[] que representa el PDF
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@SuppressWarnings("deprecation")
	public synchronized byte[] convertirRTFToPDF(byte[] aba_rtf, DAOManager adm_DAOManager)
	    throws B2BException
	{
		byte[] lba_return;
		lba_return = null;

		System.setProperty("java.awt.headless", "true");

		if((aba_rtf != null))
		{
			try
			{
				{
					String ls_licencia;
					ls_licencia = DaoCreator.getConstantesDAO(adm_DAOManager).findString(ConstanteCommon.RUTA_ASPOSE);

					if(StringUtils.isValidString(ls_licencia))
					{
						File lf_archivo;
						lf_archivo = new File(ls_licencia);

						if(lf_archivo.exists())
						{
							License ll_licencia;
							ll_licencia = new License();

							ll_licencia.setLicense(ls_licencia);
						}
					}
				}

				{
					ByteArrayOutputStream lbaos_return;
					Document              ld_asposeDoc;
					PdfSaveOptions        lpso_opciones;

					ld_asposeDoc      = new Document(new ByteArrayInputStream(aba_rtf));
					lpso_opciones     = new PdfSaveOptions();
					lbaos_return      = new ByteArrayOutputStream();

					lpso_opciones.setCompliance(com.aspose.words.PdfCompliance.PDF_A_1_A);
					lpso_opciones.setBookmarksOutlineLevel(1);
					lpso_opciones.setSaveFormat(SaveFormat.PDF);

					ld_asposeDoc.save(lbaos_return, lpso_opciones);

					lba_return = lbaos_return.toByteArray();
				}
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("convertirRTFToPDF", le_e);
			}
		}

		return lba_return;
	}
}
