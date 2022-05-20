/**
 * SUT_CO_BusquedaDocumentos_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.busquedadocumentos.v1;

import java.net.URL;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;


/**
 * Interface que contiene todos las propiedades SUT_CO_BusquedaDocumentos_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public interface SUT_CO_BusquedaDocumentos_Service extends Service
{
	/**
	 * Retorna Objeto o variable de valor SU T C O busqueda documentos port.
	 *
	 * @return el valor de SU T C O busqueda documentos port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SUT_CO_BusquedaDocumentos_PortType getSUT_CO_BusquedaDocumentosPort()
	    throws ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C O busqueda documentos port.
	 *
	 * @param au_portAddress correspondiente al valor de au port address
	 * @return el valor de SU T C O busqueda documentos port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SUT_CO_BusquedaDocumentos_PortType getSUT_CO_BusquedaDocumentosPort(URL au_portAddress)
	    throws ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C O busqueda documentos port address.
	 *
	 * @return el valor de SU T C O busqueda documentos port address
	 */
	public String getSUT_CO_BusquedaDocumentosPortAddress();
}
