package com.bachue.snr.prosnr15.ejb.session.stateless.consulta.datos.registrales;

import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoEntradaConsultarDatosRegistrales;
import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistrales;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaDatosRegistralesRemote.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 20/03/2020
 */
@Remote
public interface ConsultaDatosRegistralesRemote
{
	
	/**
	 * Método encargado de realizar la operación consultarDatosRegistrales.
	 *
	 * @param atecdr_entrada Objeto que contiene los parametros de entrada.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIpAddress Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar datos registrales
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarDatosRegistrales consultarDatosRegistrales(
	    TipoEntradaConsultarDatosRegistrales atecdr_entrada, String as_userId, String as_localIpAddress,
	    String                               as_remoteIpAddress
	)
	    throws B2BException;
}
