/**
 * SUT_CR_EnvioCorrespondenciaFisica_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1;


/**
 * Interface que contiene todos las propiedades SUT_CR_EnvioCorrespondenciaFisica_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public interface SUT_CR_EnvioCorrespondenciaFisica_PortType extends java.rmi.Remote
{
	/**
	 * Enviar correspondencia fisica.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cr.enviocorrespondenciafisica.enviarcorrespondenciafisica.v 1 . tipo salida enviar correspondencia fisica
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cr.enviocorrespondenciafisica.enviarcorrespondenciafisica.v1.TipoSalidaEnviarCorrespondenciaFisica enviarCorrespondenciaFisica(
	    co.gov.supernotariado.www.schemas.bachue.cr.enviocorrespondenciafisica.enviarcorrespondenciafisica.v1.TipoEntradaEnviarCorrespondenciaFisica entrada
	)
	    throws java.rmi.RemoteException;
}
