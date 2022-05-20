/**
 * SBB_CB_IndicePropietarios_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.v1;


/**
 * Interface que contiene todos las propiedades SBB_CB_IndicePropietarios_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SBB_CB_IndicePropietarios_PortType extends java.rmi.Remote
{
	/**
	 * Consultar.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v 1 . tipo salida indice propietarios
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios consultar(
	    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios entrada
	)
	    throws java.rmi.RemoteException;
}
