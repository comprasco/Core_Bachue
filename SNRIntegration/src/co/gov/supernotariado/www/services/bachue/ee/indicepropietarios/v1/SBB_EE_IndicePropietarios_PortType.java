/**
 * SBB_EE_IndicePropietarios_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1;


/**
 * Interface que contiene todos las propiedades SBB_EE_IndicePropietarios_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/07/2020
 */
public interface SBB_EE_IndicePropietarios_PortType extends java.rmi.Remote
{
	/**
	 * Modifica el valor de Password.
	 *
	 * @param as_s de as s
	 */
	public void setPassword(String as_s);

	/**
	 * Modifica el valor de Username.
	 *
	 * @param as_s de as s
	 */
	public void setUsername(String as_s);

	/**
	 * Consultar.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v 1 . tipo salida indice propietarios
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios consultar(
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios entrada
	)
	    throws java.rmi.RemoteException;
}
