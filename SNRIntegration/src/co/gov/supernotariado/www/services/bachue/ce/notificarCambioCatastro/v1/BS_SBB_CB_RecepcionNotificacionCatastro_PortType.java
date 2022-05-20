/**
 * BS_SBB_CB_RecepcionNotificacionCatastro_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1;

/**
 * Interface que contiene todos las propiedades BS_SBB_CB_RecepcionNotificacionCatastro_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public interface BS_SBB_CB_RecepcionNotificacionCatastro_PortType extends java.rmi.Remote
{
	
	/**
	 * Notificar cambio catastro.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ce.recepcion notificacion catastro.notificar cambio catastro.v 1 . tipo salida notificar cambio catastro
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoSalidaNotificarCambioCatastro notificarCambioCatastro(
	    co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoEntradaNotificarCambioCatastro entrada
	)
	    throws java.rmi.RemoteException;
}
