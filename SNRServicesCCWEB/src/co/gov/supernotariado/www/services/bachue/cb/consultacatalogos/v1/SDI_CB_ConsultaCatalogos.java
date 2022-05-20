/**
 * SDI_CB_ConsultaCatalogos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultacatalogos.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos;


public interface SDI_CB_ConsultaCatalogos extends java.rmi.Remote
{
	public TipoSalidaConsultarCatalogos consultarCatalogos(TipoEntradaConsultarCatalogos atecc_tecc)
	    throws java.rmi.RemoteException;
}
