/**
 * SUT_CO_EnvioDocumentos_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1;

public interface SUT_CO_EnvioDocumentos_PortType extends java.rmi.Remote
{
	public co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoSalidaEnviarDocumento enviarDocumento(
	    co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumento entrada
	)
	    throws java.rmi.RemoteException;
}
