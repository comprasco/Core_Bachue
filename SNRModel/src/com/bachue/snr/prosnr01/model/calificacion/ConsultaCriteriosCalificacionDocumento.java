package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades ConsultaCriteriosCalificacionDocumento.
 *
 * @author ccalderon
 */
public class ConsultaCriteriosCalificacionDocumento extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4106499551252207731L;

	/** Propiedad iap anotacion predio. */
	private AnotacionPredio iap_anotacionPredio;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad ib consulta vacia. */
	private boolean ib_consultaVacia;

	/** Propiedad ib consultado. */
	private boolean ib_consultado;

	/**
	 * Modifica el valor de anotacion predio.
	 *
	 * @param aap_ap asigna el valor a la propiedad anotacion predio
	 */
	public void setAnotacionPredio(AnotacionPredio aap_ap)
	{
		iap_anotacionPredio = aap_ap;
	}

	/**
	 * Retorna el valor de anotacion predio.
	 *
	 * @return el valor de anotacion predio
	 */
	public AnotacionPredio getAnotacionPredio()
	{
		if(iap_anotacionPredio == null)
			iap_anotacionPredio = new AnotacionPredio();

		return iap_anotacionPredio;
	}

	/**
	 * Modifica el valor de consulta vacia.
	 *
	 * @param ab_b asigna el valor a la propiedad consulta vacia
	 */
	public void setConsultaVacia(boolean ab_b)
	{
		ib_consultaVacia = ab_b;
	}

	/**
	 * Valida la propiedad consulta vacia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en consulta vacia
	 */
	public boolean isConsultaVacia()
	{
		return ib_consultaVacia;
	}

	/**
	 * Modifica el valor de consultado.
	 *
	 * @param ab_b asigna el valor a la propiedad consultado
	 */
	public void setConsultado(boolean ab_b)
	{
		ib_consultado = ab_b;
	}

	/**
	 * Valida la propiedad consultado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en consultado
	 */
	public boolean isConsultado()
	{
		return ib_consultado;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ad_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		if(id_documento == null)
		{
			id_documento = new Documento();

			id_documento.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return id_documento;
	}
}
