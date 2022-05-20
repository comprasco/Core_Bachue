package com.bachue.snr.prosnr01.model.copias;

import com.bachue.snr.prosnr01.model.antiguoSistema.BuscarAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;

import java.io.Serializable;
import java.util.Collection;



/**
 * Clase que contiene las propiedades de digitalizar copias.
 *
 * @author hcastaneda
 *
 */
public class DigitalizacionCopias extends BuscarAntiguoSistema implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5383151471564390448L;
	
	/** Propiedad iccc campos consulta. */
	private Collection<CamposConsulta> iccc_camposConsulta;
	
	/**
	 * Retorna Objeto o variable de valor campos consulta.
	 *
	 * @return el valor de campos consulta
	 */
	public Collection<CamposConsulta> getCamposConsulta() {
		return iccc_camposConsulta;
	}
	
	/**
	 * Modifica el valor de CamposConsulta.
	 *
	 * @param acc_cc de acc cc
	 */
	public void setCamposConsulta(Collection<CamposConsulta> acc_cc) {
		iccc_camposConsulta = acc_cc;
	}
}
