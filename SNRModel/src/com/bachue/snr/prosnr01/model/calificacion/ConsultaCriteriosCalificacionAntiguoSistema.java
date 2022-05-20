package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades ConsultaCriteriosCalificacionAntiguoSistema.
 *
 * @author Julian Vaca
 */
public class ConsultaCriteriosCalificacionAntiguoSistema extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7171787200495165586L;

	/** Propiedad iap anotacion predio. */
	private AnotacionPredio iap_anotacionPredio;

	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema idas_datosAntSistema;

	/** Propiedad ib consulta vacia. */
	private boolean ib_consultaVacia;

	/** Propiedad ib consultado. */
	private boolean ib_consultado;

	/**
	 * Modifica el valor de anotacion predio.
	 *
	 * @param aap_anotacionPredio asigna el valor a la propiedad anotacion predio
	 */
	public void setAnotacionPredio(AnotacionPredio aap_anotacionPredio)
	{
		iap_anotacionPredio = aap_anotacionPredio;
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
	 * Modifica el valor de datos ant sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad datos ant sistema
	 */
	public void setDatosAntSistema(DatosAntSistema adas_das)
	{
		idas_datosAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de datos ant sistema.
	 *
	 * @return el valor de datos ant sistema
	 */
	public DatosAntSistema getDatosAntSistema()
	{
		if(idas_datosAntSistema == null)
			idas_datosAntSistema = new DatosAntSistema();

		return idas_datosAntSistema;
	}
}
