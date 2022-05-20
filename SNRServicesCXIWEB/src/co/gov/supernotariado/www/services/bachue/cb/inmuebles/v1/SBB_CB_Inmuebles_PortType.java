/**
 * SBB_CB_Inmuebles_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1;


/**
 * Interface que contiene todos las propiedades SBB_CB_Inmuebles_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SBB_CB_Inmuebles_PortType extends java.rmi.Remote
{
	/**
	 * Consultar datos basicos.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultar datos basicos.v 1 . tipo salida datos basicos matriculas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas consultarDatosBasicos(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consultar datos inmueble.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultar datos imueble.v 1 . tipo salida datos inmueble
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble consultarDatosInmueble(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consultar direcciones anteriores.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultar direcciones anteriores.v 1 . tipo salida direcciones anteriores
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores consultarDireccionesAnteriores(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consultar matriculas.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultar matriculas.v 1 . tipo salida consulta matriculas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas consultarMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consultar propietarios.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultar propietarios.v 1 . tipo salida datos propietario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario consultarPropietarios(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario entrada
	)
	    throws java.rmi.RemoteException;
}
