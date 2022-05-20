package com.bachue.prosnr01.integracion.cliente.arcgis.json;

import com.b2bsg.common.logging.LoggerHandler;

import java.io.IOException;
import java.io.InputStream;


/**
 * Clase que contiene todos las propiedades ImagenPdf.
 *
 * @author  Julian Vaca
 * Fecha de Creacion: 20/02/2019
 */
public class ImagenPdf
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ImagenPdf.class);

	/**
	 * Test.
	 *
	 * @param imageIp Objeto de tipo InputStream
	 * @return Objeto de tipo String con la imagen resultante
	 */
	public static String test(InputStream imageIp)
	{
		StringBuilder sb = new StringBuilder();

		sb.append("{\\pict");
		sb.append(" \\picw");
		sb.append(200);
		sb.append(" \\pich");
		sb.append(150);
		sb.append(" \\pngblip");    //   for PNG images, use pngblip

		sb.append(
		    "{\\pict{\\*\\picprop\\shplid1025{\\sp{\\sn shapeType}{\\sv 75}}{\\sp{\\sn fFlipH}{\\sv 0}}{\\sp{\\sn fFlipV}{\\sv 0}}{\\sp{\\sn fLockAspectRatio}{\\sv 1}}{\\sp{\\sn fLockPosition}{\\sv 0}}{\\sp{\\sn fLockAgainstSelect}{\\sv 0}}{\\sp{\\sn fLockAgainstGrouping}{\\sv 0}}\r\n"
		    + "{\\sp{\\sn pictureGray}{\\sv 0}}{\\sp{\\sn pictureBiLevel}{\\sv 0}}{\\sp{\\sn fFilled}{\\sv 0}}{\\sp{\\sn fLine}{\\sv 0}}{\\sp{\\sn wzName}{\\sv Imagen 1}}{\\sp{\\sn dhgt}{\\sv 251658240}}{\\sp{\\sn fHidden}{\\sv 0}}{\\sp{\\sn fLayoutInCell}{\\sv 1}}}\r\n"
		    + "\\picscalex45\\picscaley45\\piccropl0\\piccropr0\\piccropt0\\piccropb0\\picw27940\\pich21590\\picwgoal15840\\pichgoal12240\\pngblip\\bliptag-125196188{\\*\\blipuid f889a864730dd76fd34e145c95897876}"
		);

		int count = 0;

		while(true)
		{
			try
			{
				int i = imageIp.read();

				if(i == -1)
					break;

				String hexStr = Integer.toHexString(i);

				if(hexStr.length() == 1)
					hexStr = "0" + hexStr;

				count += 2;

				sb.append(hexStr);

				if(count == 64)
				{
					count = 0;
					sb.append("n");
				}
			}
			catch(IOException lioe_e)
			{
				clh_LOGGER.error("test", lioe_e);
			}
		}

		sb.append("}");

		return sb.toString();
	}
}
