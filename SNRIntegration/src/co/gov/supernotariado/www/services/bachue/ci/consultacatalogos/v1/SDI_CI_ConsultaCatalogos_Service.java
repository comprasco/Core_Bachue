/**
 * SDI_CI_ConsultaCatalogos_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1;


/**
 * Interface que contiene todos las propiedades SDI_CI_ConsultaCatalogos_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public interface SDI_CI_ConsultaCatalogos_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SD I C I consulta catalogos port.
	 *
	 * @return el valor de SD I C I consulta catalogos port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_PortType getSDI_CI_ConsultaCatalogosPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SD I C I consulta catalogos port.
	 *
	 * @param portAddress correspondiente al valor de port address
	 * @return el valor de SD I C I consulta catalogos port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_PortType getSDI_CI_ConsultaCatalogosPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

/**
 * OSB Service.
 *
 * @return el valor de SD I C I consulta catalogos port address
 */
	public java.lang.String getSDI_CI_ConsultaCatalogosPortAddress();
}
