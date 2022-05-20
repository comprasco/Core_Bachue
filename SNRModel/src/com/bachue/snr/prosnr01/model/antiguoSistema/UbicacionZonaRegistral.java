package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades UbicacionZonaRegistral.
 *
 * @author Julian Vaca
 */
public class UbicacionZonaRegistral extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8455965821595784288L;

	/** Propiedad icr circulo registral. */
	private CirculoRegistral icr_circuloRegistral;

	/** Propiedad id departamento. */
	private Departamento id_departamento;

	/** Propiedad iep estado predio. */
	private EstadoPredio iep_estadoPredio;

	/** Propiedad im municipio. */
	private Municipio im_municipio;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is nupre. */
	private String is_nupre;

	/** Propiedad iv vereda. */
	private Vereda iv_vereda;

	/** Propiedad izr zona registral. */
	private ZonaRegistral izr_zonaRegistral;

	/**
	 * Modifica el valor de circulo registral.
	 *
	 * @param acr_cr asigna el valor a la propiedad circulo registral
	 */
	public void setCirculoRegistral(CirculoRegistral acr_cr)
	{
		icr_circuloRegistral = acr_cr;
	}

	/**
	 * Retorna el valor de circulo registral.
	 *
	 * @return el valor de circulo registral
	 */
	public CirculoRegistral getCirculoRegistral()
	{
		if(icr_circuloRegistral == null)
			icr_circuloRegistral = new CirculoRegistral();

		return icr_circuloRegistral;
	}

	/**
	 * Modifica el valor de departamento.
	 *
	 * @param ad_d asigna el valor a la propiedad departamento
	 */
	public void setDepartamento(Departamento ad_d)
	{
		id_departamento = ad_d;
	}

	/**
	 * Retorna el valor de departamento.
	 *
	 * @return el valor de departamento
	 */
	public Departamento getDepartamento()
	{
		if(id_departamento == null)
			id_departamento = new Departamento();

		return id_departamento;
	}

	/**
	 * Modifica el valor de estado predio.
	 *
	 * @param aep_ep asigna el valor a la propiedad estado predio
	 */
	public void setEstadoPredio(EstadoPredio aep_ep)
	{
		iep_estadoPredio = aep_ep;
	}

	/**
	 * Retorna el valor de estado predio.
	 *
	 * @return el valor de estado predio
	 */
	public EstadoPredio getEstadoPredio()
	{
		if(iep_estadoPredio == null)
			iep_estadoPredio = new EstadoPredio();

		return iep_estadoPredio;
	}

	/**
	 * Modifica el valor de id pais.
	 *
	 * @param as_s asigna el valor a la propiedad id pais
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna el valor de id pais.
	 *
	 * @return el valor de id pais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de municipio.
	 *
	 * @param am_m asigna el valor a la propiedad municipio
	 */
	public void setMunicipio(Municipio am_m)
	{
		im_municipio = am_m;
	}

	/**
	 * Retorna el valor de municipio.
	 *
	 * @return el valor de municipio
	 */
	public Municipio getMunicipio()
	{
		if(im_municipio == null)
			im_municipio = new Municipio();

		return im_municipio;
	}

	/**
	 * Modifica el valor de nupre.
	 *
	 * @param as_s asigna el valor a la propiedad nupre
	 */
	public void setNupre(String as_s)
	{
		is_nupre = as_s;
	}

	/**
	 * Retorna el valor de nupre.
	 *
	 * @return el valor de nupre
	 */
	public String getNupre()
	{
		return is_nupre;
	}

	/**
	 * Modifica el valor de vereda.
	 *
	 * @param av_v asigna el valor a la propiedad vereda
	 */
	public void setVereda(Vereda av_v)
	{
		iv_vereda = av_v;
	}

	/**
	 * Retorna el valor de vereda.
	 *
	 * @return el valor de vereda
	 */
	public Vereda getVereda()
	{
		if(iv_vereda == null)
			iv_vereda = new Vereda();

		return iv_vereda;
	}

	/**
	 * Modifica el valor de zona registral.
	 *
	 * @param azr_zr asigna el valor a la propiedad zona registral
	 */
	public void setZonaRegistral(ZonaRegistral azr_zr)
	{
		izr_zonaRegistral = azr_zr;
	}

	/**
	 * Retorna el valor de zona registral.
	 *
	 * @return el valor de zona registral
	 */
	public ZonaRegistral getZonaRegistral()
	{
		if(izr_zonaRegistral == null)
			izr_zonaRegistral = new ZonaRegistral();

		return izr_zonaRegistral;
	}
}
