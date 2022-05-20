/**
 * BS_SBB_CB_PartesInteresadas_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1;


/**
 * Interface que contiene todos las propiedades BS_SBB_CB_PartesInteresadas_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface BS_SBB_CB_PartesInteresadas_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor b S SB B C B partes interesadas port.
	 *
	 * @return el valor de b S SB B C B partes interesadas port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_PortType getBS_SBB_CB_PartesInteresadasPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor b S SB B C B partes interesadas port.
	 *
	 * @param portAddress de port address
	 * @return el valor de b S SB B C B partes interesadas port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_PortType getBS_SBB_CB_PartesInteresadasPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor b S SB B C B partes interesadas port address.
	 *
	 * @return el valor de b S SB B C B partes interesadas port address
	 */
	public java.lang.String getBS_SBB_CB_PartesInteresadasPortAddress();
}
