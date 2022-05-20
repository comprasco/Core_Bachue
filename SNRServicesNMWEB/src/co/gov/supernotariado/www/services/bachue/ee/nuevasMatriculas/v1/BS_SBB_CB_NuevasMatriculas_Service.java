/**
 * BS_SBB_CB_NuevasMatriculas_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1;


/**
 * Interface que contiene todos las propiedades BS_SBB_CB_NuevasMatriculas_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface BS_SBB_CB_NuevasMatriculas_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor b S SB B C B nuevas matriculas port.
	 *
	 * @return el valor de b S SB B C B nuevas matriculas port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1.BS_SBB_CB_NuevasMatriculas_PortType getBS_SBB_CB_NuevasMatriculasPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor b S SB B C B nuevas matriculas port.
	 *
	 * @param portAddress de port address
	 * @return el valor de b S SB B C B nuevas matriculas port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1.BS_SBB_CB_NuevasMatriculas_PortType getBS_SBB_CB_NuevasMatriculasPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor b S SB B C B nuevas matriculas port address.
	 *
	 * @return el valor de b S SB B C B nuevas matriculas port address
	 */
	public java.lang.String getBS_SBB_CB_NuevasMatriculasPortAddress();
}
