/**
 * SUT_CO_RelacionesDocumento_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.relacionesdocumento.v1;

import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoEntradaRelacionarDocumento;
import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoSalidaRelacionarDocumento;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SUT_CO_RelacionesDocumento_PortType extends Remote
{
	public TipoSalidaRelacionarDocumento relacionarDocumento(TipoEntradaRelacionarDocumento aterd_entrada)
	    throws RemoteException;
}
