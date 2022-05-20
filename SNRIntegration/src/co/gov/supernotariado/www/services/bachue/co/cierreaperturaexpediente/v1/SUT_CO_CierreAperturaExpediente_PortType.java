/**
 * SUT_CO_CierreAperturaExpediente_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1;


/**
 * Interface que contiene todos las propiedades SUT_CO_CierreAperturaExpediente_PortType.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 4/05/2020
 */
public interface SUT_CO_CierreAperturaExpediente_PortType extends java.rmi.Remote
{
	/**
	 * Apertura turno.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v 1 . tipo salida apertura turno
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.TipoSalidaAperturaTurno aperturaTurno(
	    co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.TipoEntradaAperturaTurno entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Cierre turno.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v 1 . tipo salida cierre turno
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.TipoSalidaCierreTurno cierreTurno(
	    co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.TipoEntradaCierreTurno entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener indice electronico.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v 1 . tipo salida obtener indice electronico
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoSalidaObtenerIndiceElectronico obtenerIndiceElectronico(
	    co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoEntradaObtenerIndiceElectronico entrada
	)
	    throws java.rmi.RemoteException;
}
