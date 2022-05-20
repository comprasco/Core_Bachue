/**
 * SUT_CO_BusquedaDocumentos_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.busquedadocumentos.v1;

import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoEntradaConsultar;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoSalidaConsultar;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoEntradaObtenerArchivo;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoSalidaObtenerArchivo;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Interface que contiene todos las propiedades SUT_CO_BusquedaDocumentos_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public interface SUT_CO_BusquedaDocumentos_PortType extends Remote
{
	/**
	 * Consultar.
	 *
	 * @param atec_entrada correspondiente al valor de atec entrada
	 * @return el valor de tipo salida consultar
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultar consultar(TipoEntradaConsultar atec_entrada)
	    throws RemoteException;

	/**
	 * Obtener archivo.
	 *
	 * @param ateoa_entrada correspondiente al valor de ateoa entrada
	 * @return el valor de tipo salida obtener archivo
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerArchivo obtenerArchivo(TipoEntradaObtenerArchivo ateoa_entrada)
	    throws RemoteException;
}
