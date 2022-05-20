/**
 * SBB_EE_IndicePropietarios_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1;


/**
 * Interface que contiene todos las propiedades SBB_EE_IndicePropietarios_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/07/2020
 */
public interface SBB_EE_IndicePropietarios_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SB B E E indice propietarios port.
	 *
	 * @return el valor de SB B E E indice propietarios port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_PortType getSBB_EE_IndicePropietariosPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Modifica el valor de Password.
	 *
	 * @param as_s de as s
	 */
	public void setPassword(String as_s);

	/**
	 * Modifica el valor de Username.
	 *
	 * @param as_s de as s
	 */
	public void setUsername(String as_s);

	/**
	 * Retorna Objeto o variable de valor SB B E E indice propietarios port.
	 *
	 * @param portAddress correspondiente al valor de port address
	 * @return el valor de SB B E E indice propietarios port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_PortType getSBB_EE_IndicePropietariosPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SB B E E indice propietarios port address.
	 *
	 * @return el valor de SB B E E indice propietarios port address
	 */
	public java.lang.String getSBB_EE_IndicePropietariosPortAddress();
}
