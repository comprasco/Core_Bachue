/**
 * SDI_CB_ConsultaTrazabilidad_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1;


/**
 * Interface que contiene todos las propiedades SDI_CB_ConsultaTrazabilidad_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/09/2020
 */
public interface SDI_CB_ConsultaTrazabilidad_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SD I C B consulta trazabilidad port.
	 *
	 * @return el valor de SD I C B consulta trazabilidad port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1.SDI_CB_ConsultaTrazabilidad_PortType getSDI_CB_ConsultaTrazabilidadPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SD I C B consulta trazabilidad port.
	 *
	 * @param portAddress correspondiente al valor de port address
	 * @return el valor de SD I C B consulta trazabilidad port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1.SDI_CB_ConsultaTrazabilidad_PortType getSDI_CB_ConsultaTrazabilidadPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SD I C B consulta trazabilidad port address.
	 *
	 * @return el valor de SD I C B consulta trazabilidad port address
	 */
	public java.lang.String getSDI_CB_ConsultaTrazabilidadPortAddress();
}
