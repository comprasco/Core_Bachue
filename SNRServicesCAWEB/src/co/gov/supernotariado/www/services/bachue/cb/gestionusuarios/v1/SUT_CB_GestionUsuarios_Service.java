/**
 * SUT_CB_GestionUsuarios_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1;


/**
 * Interface que contiene todos las propiedades SUT_CB_GestionUsuarios_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SUT_CB_GestionUsuarios_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SU T C B gestion usuarios port.
	 *
	 * @return el valor de SU T C B gestion usuarios port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1.SUT_CB_GestionUsuarios_PortType getSUT_CB_GestionUsuariosPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C B gestion usuarios port.
	 *
	 * @param portAddress de port address
	 * @return el valor de SU T C B gestion usuarios port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1.SUT_CB_GestionUsuarios_PortType getSUT_CB_GestionUsuariosPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C B gestion usuarios port address.
	 *
	 * @return el valor de SU T C B gestion usuarios port address
	 */
	public java.lang.String getSUT_CB_GestionUsuariosPortAddress();
}
