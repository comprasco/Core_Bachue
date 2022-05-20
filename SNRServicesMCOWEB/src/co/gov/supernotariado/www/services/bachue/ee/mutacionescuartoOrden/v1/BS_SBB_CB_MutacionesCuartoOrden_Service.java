/**
 * BS_SBB_CB_MutacionesCuartoOrden_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1;


/**
 * Interface que contiene todos las propiedades BS_SBB_CB_MutacionesCuartoOrden_Service.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 7/05/2020
 */
public interface BS_SBB_CB_MutacionesCuartoOrden_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor b S SB B C B mutaciones cuarto orden port address.
	 *
	 * @return el valor de b S SB B C B mutaciones cuarto orden port address
	 */
	public java.lang.String getBS_SBB_CB_MutacionesCuartoOrdenPortAddress();

	/**
	 * Retorna Objeto o variable de valor b S SB B C B mutaciones cuarto orden port.
	 *
	 * @return el valor de b S SB B C B mutaciones cuarto orden port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1.BS_SBB_CB_MutacionesCuartoOrden_PortType getBS_SBB_CB_MutacionesCuartoOrdenPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor b S SB B C B mutaciones cuarto orden port.
	 *
	 * @param portAddress de port address
	 * @return el valor de b S SB B C B mutaciones cuarto orden port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1.BS_SBB_CB_MutacionesCuartoOrden_PortType getBS_SBB_CB_MutacionesCuartoOrdenPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;
}
