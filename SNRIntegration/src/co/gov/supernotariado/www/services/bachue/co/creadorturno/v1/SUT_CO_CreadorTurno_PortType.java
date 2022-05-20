/**
 * SUT_CO_CreadorTurno_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.creadorturno.v1;


/**
 * Interface que contiene todos las propiedades SUT_CO_CreadorTurno_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public interface SUT_CO_CreadorTurno_PortType extends java.rmi.Remote
{
	/**
	 * Crear turno.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.co.creadorturno.crearturno.v 1 . tipo salida crear turno
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.co.creadorturno.crearturno.v1.TipoSalidaCrearTurno crearTurno(
	    co.gov.supernotariado.www.schemas.bachue.co.creadorturno.crearturno.v1.TipoEntradaCrearTurno entrada
	)
	    throws java.rmi.RemoteException;
}
