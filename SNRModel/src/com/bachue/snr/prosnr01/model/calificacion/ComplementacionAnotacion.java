package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades ComplementacionAnotacion.
 *
 * @author Julian Vaca
 */
public class ComplementacionAnotacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -40791739141255742L;

	/** Propiedad iap anotacion predio. */
	private AnotacionPredio iap_anotacionPredio;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad inj naturaleza juridica. */
	private NaturalezaJuridica inj_naturalezaJuridica;

	/** Propiedad is complementacion. */
	private String is_complementacion;

	/** Propiedad is oficina origen. */
	private String is_oficinaOrigen;

	/** Propiedad is tipo documento. */
	private String is_tipoDocumento;

	/** Propiedad is tipo oficina. */
	private String is_tipoOficina;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

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
	 * Modifica el valor de complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad complementacion
	 */
	public void setComplementacion(String as_s)
	{
		is_complementacion = as_s;
	}

	/**
	 * Retorna el valor de complementacion.
	 *
	 * @return el valor de complementacion
	 */
	public String getComplementacion()
	{
		return is_complementacion;
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
			id_documento = new Documento();

		return id_documento;
	}

	/**
	 * Modifica el valor de naturaleza juridica.
	 *
	 * @param anj_nj asigna el valor a la propiedad naturaleza juridica
	 */
	public void setNaturalezaJuridica(NaturalezaJuridica anj_nj)
	{
		this.inj_naturalezaJuridica = anj_nj;
	}

	/**
	 * Retorna el valor de naturaleza juridica.
	 *
	 * @return el valor de naturaleza juridica
	 */
	public NaturalezaJuridica getNaturalezaJuridica()
	{
		if(inj_naturalezaJuridica == null)
			inj_naturalezaJuridica = new NaturalezaJuridica();

		return inj_naturalezaJuridica;
	}

	/**
	 * Modifica el valor de oficina origen.
	 *
	 * @param as_s asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(String as_s)
	{
		is_oficinaOrigen = as_s;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public String getOficinaOrigen()
	{
		return is_oficinaOrigen;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de tipo documento.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documento
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	 * Retorna el valor de tipo documento.
	 *
	 * @return el valor de tipo documento
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}

	/**
	 * Modifica el valor de tipo oficina.
	 *
	 * @param as_s asigna el valor a la propiedad tipo oficina
	 */
	public void setTipoOficina(String as_s)
	{
		is_tipoOficina = as_s;
	}

	/**
	 * Retorna el valor de tipo oficina.
	 *
	 * @return el valor de tipo oficina
	 */
	public String getTipoOficina()
	{
		return is_tipoOficina;
	}
}
