package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import org.primefaces.model.DefaultStreamedContent;

import java.util.Collection;


/**
 * Interface que contiene todos las propiedades ProyeccionDocumentoInterface.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 13/11/2021
 */
public interface ProyeccionDocumentoInterface
{
	/**
	 * Valida la propiedad mostrar boton.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna
	 *         <code>false</code> en mostrar boton
	 */
	public boolean isMostrarBotonSalvarProyeccionDocumento();

	/**
	 * Ocultar boton salvar proyeccion documento.
	 */
	public void ocultarBotonSalvarProyeccionDocumento();

	/**
	 * Modifica el valor de MostrarBoton.
	 *
	 * @param ab_b de ab b
	 */
	public void setMostrarBotonSalvarProyeccionDocumento(boolean ab_b);

	/**
	 * Retorna Objeto o variable de valor documento array.
	 *
	 * @return el valor de documento array
	 */
	public byte[] getDocumentoArray();

	/**
	 * Modifica el valor de DocumentoArray.
	 *
	 * @param aba_ba de aba ba
	 */
	public void setDocumentoArray(byte[] aba_ba);

	/**
	* Retorna Objeto o variable de valor campos pantalla.
	*
	* @return el valor de campos pantalla
	*/
	public Collection<TagPlantillaResolucion> getCamposPantalla();

	/**
	 * Modifica el valor de CamposPantalla.
	 *
	 * @param actpr_ctpr de actpr ctpr
	 */
	public void setCamposPantalla(Collection<TagPlantillaResolucion> actpr_ctpr);

	/**
	 * Clean proyeccion documento.
	 */
	public void cleanProyeccionDocumento();

	/**
	 * Retorna Objeto o variable de valor solucion tags.
	 *
	 * @return el valor de solucion tags
	 */
	public OficiosTexto getSolucionTags();

	/**
	 * Modifica el valor de SolucionTags.
	 *
	 * @param aot_ot de aot ot
	 */
	public void setSolucionTags(OficiosTexto aot_ot);

	/**
	 * Regresar proyeccion documento.
	 *
	 * @return el valor de string
	 */
	public String regresarProyeccionDocumento();

	/**
	* Modifica el valor de imagen documento.
	*
	* @param adsc_dsc asigna el valor a la propiedad imagen documento
	*/
	public void setImagenDocumento(DefaultStreamedContent adsc_dsc);

	/**
	 * Retorna el valor de imagen documento.
	 *
	 * @return el valor de imagen documento
	 */
	public DefaultStreamedContent getImagenDocumento();

	/**
	 * Generar documentos.
	 *
	 * @param aba_documento de aba documento
	 */
	public void generarDocumentos()
	    throws B2BException;
}
