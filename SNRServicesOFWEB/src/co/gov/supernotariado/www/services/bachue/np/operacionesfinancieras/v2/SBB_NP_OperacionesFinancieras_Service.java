/**
 * SBB_NP_OperacionesFinancieras_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2;


/**
 * Interface que contiene todos las propiedades SBB_NP_OperacionesFinancieras_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SBB_NP_OperacionesFinancieras_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SB B N P operaciones financieras port.
	 *
	 * @return el valor de SB B N P operaciones financieras port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2.SBB_NP_OperacionesFinancieras_PortType getSBB_NP_OperacionesFinancierasPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SB B N P operaciones financieras port.
	 *
	 * @param portAddress de port address
	 * @return el valor de SB B N P operaciones financieras port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2.SBB_NP_OperacionesFinancieras_PortType getSBB_NP_OperacionesFinancierasPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SB B N P operaciones financieras port address.
	 *
	 * @return el valor de SB B N P operaciones financieras port address
	 */
	public java.lang.String getSBB_NP_OperacionesFinancierasPortAddress();
}
