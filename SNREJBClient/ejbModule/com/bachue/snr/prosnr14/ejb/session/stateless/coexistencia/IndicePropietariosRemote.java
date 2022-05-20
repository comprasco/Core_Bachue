package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios;
import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades IndicePropietariosRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
@Remote
public interface IndicePropietariosRemote
{
	/**
	 * Consultar.
	 *
	 * @param ateip_entrada de TipoEntradaIndicePropietarios
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida indice propietarios
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaIndicePropietarios consultar(
	    TipoEntradaIndicePropietarios ateip_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
