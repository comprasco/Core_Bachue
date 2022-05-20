package com.bachue.snr.prosnr01.business;

import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.logging.LoggerHandler;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bea.core.repackaged.aspectj.weaver.NewConstructorTypeMunger;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import org.apache.commons.text.WordUtils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;


/**
 * Clase que contiene todos la fucionalidades para la base de todos los business.
 *
 * @author hcastaneda
 */
public class MarcaAgua {
    /** Constante clh_LOGGER. */
    private static final LoggerHandler clh_LOGGER = (LoggerHandler) com.bachue.snr.prosnr01.common.utils.LoggingHelper.getLogger(MarcaAgua.class);

    /**
     *
     * Metodo encargado de crear una imagen con el texto enviado como argumento.
     *
     * @param as_texto Argumento de tipo <code>String</code> que contiene el texto para crear la imagen.
     * @param ai_width Argumento de tipo <code>int</code> que contiene el ancho que tendrá la imagen.
     * @param ai_height Argumento de tipo <code>int</code> que contiene el alto que tendrá la imagen.
     * @param ai_fontSize Argumento de tipo <code>int</code> que contiene el tamaño del texto.
     * @return Arreglo de bytes que contiene la imagen creada.
     * @throws B2BException Señala que se ha generado una excepción
     */
    public static byte[] crearImagenConTexto(String as_texto, int ai_width,
        int ai_height, int ai_fontSize) throws B2BException {
        byte[] lba_imagen;

        lba_imagen = null;

        try {
            String ls_texto;
            Font lf_font;
            BufferedImage lbi_bufferedImage;
            Graphics2D lg_graphics;

            ls_texto = (StringUtils.isValidString(as_texto))
                ? as_texto.toUpperCase() : new String();
            lf_font = new Font("Calibri", Font.TYPE1_FONT, ai_fontSize);
            lbi_bufferedImage = new BufferedImage(ai_width, ai_height,
                    BufferedImage.TYPE_INT_RGB);
            lg_graphics = (Graphics2D) lbi_bufferedImage.getGraphics();

            lg_graphics.setFont(lf_font);
            lg_graphics.setColor(Color.WHITE);
            lg_graphics.fillRect(0, 0, ai_width, ai_height);
            lg_graphics.setColor(Color.BLACK);

            if (StringUtils.isValidString(as_texto)) {
                ByteArrayOutputStream lbaos_baos;

                lbaos_baos = new ByteArrayOutputStream();

                lg_graphics.drawString(ls_texto, 4,
                    lg_graphics.getFontMetrics().getHeight() - 4);
                ImageIO.write(lbi_bufferedImage, "jpg", lbaos_baos);
                lbaos_baos.flush();

                lba_imagen = lbaos_baos.toByteArray();

                lbaos_baos.close();
            }
        } catch (Exception le_e) {
            clh_LOGGER.error("crearImagenConTexto", le_e);

            throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
        }

        return lba_imagen;
    }

    /**
     * Metodo encargado de estampar marca de agua en un pdf.
     *
     * @param aba_archivo Argumento de tipo <code>byte[]</code> que contiene los bytes del pdf.
     * @param as_texto Argumento de tipo <code>String</code> que contiene el texto que se debe mostrar como marca de agua.
     * @param ai_width de ai width
     * @param ai_height de ai height
     * @return el valor de byte[]
     * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
     */
    public static byte[] estamparMarcaDeAgua(byte[] aba_archivo,
        String as_texto, int ai_width, int ai_height) throws B2BException {
        byte[] lba_archivo;

        lba_archivo = aba_archivo;

        try {
            if (aba_archivo != null) {
                byte[] lba_imagenTexto;

                lba_imagenTexto = crearImagenConTextoMarca(as_texto, ai_width,
                        ai_height);

                if (lba_imagenTexto != null) {
                    PdfReader lpr_reader;
                    int li_pagina;

                    lpr_reader = new PdfReader(aba_archivo);
                    li_pagina = lpr_reader.getNumberOfPages();

                    if (li_pagina > 0) {
                        OutputStream los_out;
                        PdfStamper lps_pdfStamper;
                        com.itextpdf.text.Image li_image;

                        los_out = new ByteArrayOutputStream();
                        lps_pdfStamper = new PdfStamper(lpr_reader, los_out);
                        li_image = com.itextpdf.text.Image.getInstance(lba_imagenTexto);

                        for (int li_contador = 1; li_contador <= li_pagina;
                                li_contador++) {
                            PdfContentByte lpc_content;
                            lpc_content = lps_pdfStamper.getOverContent(li_contador);

                            li_image.setAbsolutePosition(0, 0);
                            lpc_content.addImage(li_image);
                        }

                        lps_pdfStamper.close();
                        lpr_reader.close();

                        lba_archivo = ((ByteArrayOutputStream) los_out).toByteArray();

                        los_out.flush();
                        los_out.close();
                    }
                }
            }
        } catch (Exception le_e) {
            clh_LOGGER.error("estamparMarcaDeAgua", le_e);

            throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
        }

        return lba_archivo;
    }

    /**
     *
     * Metodo encargado de crear una imagen con el texto enviado como argumento.
     *
     * @param as_texto Argumento de tipo <code>String</code> que contiene el texto para crear la imagen.
     * @param ai_width Argumento de tipo <code>int</code> que contiene el ancho que tendrá la imagen.
     * @param ai_height Argumento de tipo <code>int</code> que contiene el alto que tendrá la imagen.
     * @return Arreglo de bytes que contiene la imagen creada.
     * @throws B2BException Señala que se ha generado una excepción
     */
    private static byte[] crearImagenConTexto(String as_texto, int ai_width,
        int ai_height) throws B2BException {
        byte[] lba_imagen;

        lba_imagen = null;

        try {
            String ls_texto;
            Font lf_font;
            BufferedImage lbi_bufferedImage;
            Graphics2D lg_graphics;

            ls_texto = (as_texto != null) ? as_texto.toUpperCase() : new String();
            lf_font = new Font("Arial Narrow", Font.BOLD, 35);
            lbi_bufferedImage = new BufferedImage(ai_width, ai_height,
                    BufferedImage.TYPE_INT_RGB);
            lg_graphics = (Graphics2D) lbi_bufferedImage.getGraphics();

            lg_graphics.setFont(lf_font);
            lg_graphics.fillRect(0, 0, ai_width, ai_height);
            lg_graphics.setColor(Color.BLACK);

            if (ls_texto != null) {
                {
                    AffineTransform lat_affineTransform;

                    lat_affineTransform = new AffineTransform();

                    lat_affineTransform.setToRotation(-Math.PI / 4);

                    lg_graphics.setTransform(lat_affineTransform);
                }

                {
                    int li_y;
                    int lineHeight;
                    String ls_tmp;

                    li_y = 350;
                    lineHeight = lg_graphics.getFontMetrics().getHeight();
                    ls_tmp = WordUtils.wrap(ls_texto, 30);

                    for (String line : ls_tmp.split("\n")) {
                        lg_graphics.drawString(line, -300, li_y);

                        li_y += lineHeight;
                    }
                }
            }

            {
                ByteArrayOutputStream lbaos_baos;

                lbaos_baos = new ByteArrayOutputStream();

                ImageIO.write(lbi_bufferedImage, "jpg", lbaos_baos);

                lbaos_baos.flush();

                lba_imagen = lbaos_baos.toByteArray();

                lbaos_baos.close();
            }
        } catch (Exception le_e) {
            clh_LOGGER.error("crearImagenConTexto", le_e);

            throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
        }

        return lba_imagen;
    }

    /**
     *
     * Metodo encargado de crear una imagen con el texto enviado como argumento.
     *
     * @param as_texto Argumento de tipo <code>String</code> que contiene el texto para crear la imagen.
     * @param ai_width Argumento de tipo <code>int</code> que contiene el ancho que tendrá la imagen.
     * @param ai_height Argumento de tipo <code>int</code> que contiene el alto que tendrá la imagen.
     * @return Arreglo de bytes que contiene la imagen creada.
     * @throws B2BException Señala que se ha generado una excepción
     */
    private static byte[] crearImagenConTextoMarca(String as_texto,
        int ai_width, int ai_height) throws B2BException {
        byte[] lba_imagen;

        lba_imagen = null;

        try {
            String ls_texto;
            Font lf_font;
            BufferedImage lbi_bufferedImage;
            Graphics2D lg_graphics;

            ls_texto = (as_texto != null) ? as_texto.toUpperCase() : new String();
            lf_font = new Font("Arial Narrow", Font.BOLD, 27);
            lbi_bufferedImage = new BufferedImage(ai_width, ai_height,
                    BufferedImage.TYPE_INT_ARGB);

            lg_graphics = (Graphics2D) lbi_bufferedImage.createGraphics();
            lg_graphics.setFont(lf_font);
            lg_graphics.setColor(Color.BLACK);
            lg_graphics.fillRect(0, 0, ai_width, ai_height);
            lg_graphics.setColor(Color.lightGray);

            {
                ByteArrayOutputStream lbaos_baos;

                lbaos_baos = new ByteArrayOutputStream();

                lbi_bufferedImage = makeTransparentImage(lbi_bufferedImage,
                        ai_width, ai_height);

                if (ls_texto != null) {
                    {
                        AffineTransform lat_affineTransform;

                        lat_affineTransform = new AffineTransform();

                        lat_affineTransform.setToRotation(-Math.PI / 4);

                        lg_graphics.setTransform(lat_affineTransform);
                    }

                    {
                        int li_y;
                        int lineHeight;
                        String ls_tmp;

                        li_y = 350;
                        lineHeight = lg_graphics.getFontMetrics().getHeight();
                        ls_tmp = WordUtils.wrap(ls_texto, 30);

                        for (String line : ls_tmp.split("\n")) {
                            lg_graphics.drawString(line, -300, li_y);

                            li_y += lineHeight;
                        }
                    }
                }

                ImageIO.write(lbi_bufferedImage, "PNG", lbaos_baos);

                lbaos_baos.flush();

                lba_imagen = lbaos_baos.toByteArray();

                lbaos_baos.close();
            }
        } catch (Exception le_e) {
            clh_LOGGER.error("crearImagenConTexto", le_e);

            throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
        }

        return lba_imagen;
    }

    /**
     * método para hacer el color blanco y negro en una imagen transparente
     * @param abr_br bufferedImagen a transformar
     * @param ai_width ancho de la imagen
     * @param ai_height alto de la imagen
     * @return
     */
    public static BufferedImage makeTransparentImage(BufferedImage abr_br,
        int ai_width, int ai_height) {
        for (int i = 0; i < ai_height; i++) {
            for (int j = 0; j < ai_width; j++) {
                Color ac_color = new Color(abr_br.getRGB(j, i));
                int lir_red = ac_color.getRed();
                int lib_blue = ac_color.getBlue();
                int lig_green = ac_color.getGreen();

                if (((lir_red == 255) && (lib_blue == 255) && (lig_green == 255))) {
                    abr_br.setRGB(j, i, 0x00000000);
                }

                if (((lir_red == 0) && (lib_blue == 0) && (lig_green == 0))) {
                    abr_br.setRGB(j, i, 0x00000000);
                }
            }
        }

        return abr_br;
    }
}
