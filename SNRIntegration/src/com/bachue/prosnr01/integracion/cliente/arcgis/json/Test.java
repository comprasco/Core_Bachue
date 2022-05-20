package com.bachue.prosnr01.integracion.cliente.arcgis.json;

import com.b2bsg.common.util.StringUtils;

import java.io.InputStream;

import java.net.URL;


/**
 * Clase que contiene todos las propiedades Test.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 30/07/2020
 */
public final class Test
{
	/**
	 * Metodo principal.
	 *
	 * @param lsa_argumentos argumentos
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public static void main(String[] lsa_argumentos)
	    throws Exception
	{
		String ls_urlImagen;

		ls_urlImagen = ClienteJSON.getMap("187530001000000040024000000000", "", "", "", "");

		if(StringUtils.isValidString(ls_urlImagen))
		{
			InputStream lis_imagen;

			lis_imagen = new URL(ls_urlImagen).openStream();

			ImagenPdf.test(lis_imagen);

			/*los_imagen     = new FileOutputStream("C:\\development\\img.png");
			
			{
			    byte[] lba_imagen = new byte[2048];
			    int    li_tamano;
			
			    while((li_tamano = lis_imagen.read(lba_imagen)) != -1)
			        los_imagen.write(lba_imagen, 0, li_tamano);
			}
			
			lis_imagen.close();
			los_imagen.close();*/
		}
	}
}
