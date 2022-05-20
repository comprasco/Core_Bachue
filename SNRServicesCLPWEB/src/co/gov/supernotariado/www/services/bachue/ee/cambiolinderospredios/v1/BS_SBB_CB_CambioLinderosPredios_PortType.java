/**
 * BS_SBB_CB_CambioLinderosPredios_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.cambiolinderospredios.v1;


/**
 * Interface que contiene todos las propiedades BS_SBB_CB_CambioLinderosPredios_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface BS_SBB_CB_CambioLinderosPredios_PortType extends java.rmi.Remote
{
	/**
	 * Consulta segregacion con cambio propietario.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ee.cambio linderos predios.consulta segregacion con cambio propietario.v 1 . tipo salida consulta segregacion con cambio propietario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaConsultaSegregacionConCambioPropietario consultaSegregacionConCambioPropietario(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoEntradaConsultaSegregacionConCambioPropietario entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consulta segregacion sin cambio propietario.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ee.cambio linderos predios.consulta segregacion sin cambio propietario.v 1 . tipo salida consulta segregacion sin cambio propietario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaConsultaSegregacionSinCambioPropietario consultaSegregacionSinCambioPropietario(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoEntradaConsultaSegregacionSinCambioPropietario entrada
	)
	    throws java.rmi.RemoteException;
}
