/**
 * SDI_CI_ConsultaCatalogos_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1;


/**
 * Interface que contiene todos las propiedades SDI_CI_ConsultaCatalogos_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public interface SDI_CI_ConsultaCatalogos_PortType extends java.rmi.Remote
{
	/**
	 * Consultar catalogos.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v 1 . tipo salida consultar catalogos
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos consultarCatalogos(
	    co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos entrada
	)
	    throws java.rmi.RemoteException;
}
