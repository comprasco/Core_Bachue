/**
 * BS_SDI_CB_SolicitudDeCopias_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.ee.solicituddecopias.v1;

public interface BS_SDI_CB_SolicitudDeCopias_PortType extends java.rmi.Remote {
    public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitud ingresoSolicitud(co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitud entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitud consultarSolicitud(co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoEntradaConsultarSolicitud entrada) throws java.rmi.RemoteException;
}
