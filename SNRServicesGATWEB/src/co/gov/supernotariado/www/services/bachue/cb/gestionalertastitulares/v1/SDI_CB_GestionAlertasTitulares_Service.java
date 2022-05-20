/**
 * SDI_CB_GestionAlertasTitulares_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1;

import java.net.URL;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;


/**
 * Interface que contiene todos las propiedades SDI_CB_GestionAlertasTitulares_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SDI_CB_GestionAlertasTitulares_Service extends Service
{
	/**
	 * Retorna Objeto o variable de valor SD I C B gestion alertas titulares port.
	 *
	 * @return el valor de SD I C B gestion alertas titulares port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SDI_CB_GestionAlertasTitulares_PortType getSDI_CB_GestionAlertasTitularesPort()
	    throws ServiceException;

	/**
	 * Retorna Objeto o variable de valor SD I C B gestion alertas titulares port.
	 *
	 * @param au_portAddress de au port address
	 * @return el valor de SD I C B gestion alertas titulares port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SDI_CB_GestionAlertasTitulares_PortType getSDI_CB_GestionAlertasTitularesPort(URL au_portAddress)
	    throws ServiceException;

	/**
	 * Retorna Objeto o variable de valor SD I C B gestion alertas titulares port address.
	 *
	 * @return el valor de SD I C B gestion alertas titulares port address
	 */
	public String getSDI_CB_GestionAlertasTitularesPortAddress();
}
