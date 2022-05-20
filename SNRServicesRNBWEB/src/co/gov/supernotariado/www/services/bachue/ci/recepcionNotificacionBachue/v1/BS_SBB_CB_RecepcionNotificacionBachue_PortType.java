/**
 * BS_SBB_CB_RecepcionNotificacionBachue_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1;


/**
 * Interface que contiene todos las propiedades BS_SBB_CB_RecepcionNotificacionBachue_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/05/2020
 */
public interface BS_SBB_CB_RecepcionNotificacionBachue_PortType extends java.rmi.Remote
{
	/**
	 * Registrar reintento servicio.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ci.recepcion notificacion bachue.registrar reintento servicio.v 1 . tipo salida registrar reintento servicio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoSalidaRegistrarReintentoServicio registrarReintentoServicio(
	    co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoEntradaRegistrarReintentoServicio entrada
	)
	    throws java.rmi.RemoteException;
}
