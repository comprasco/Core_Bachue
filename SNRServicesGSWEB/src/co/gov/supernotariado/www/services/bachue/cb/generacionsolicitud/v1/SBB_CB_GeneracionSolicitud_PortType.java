/**
 * SBB_CB_GeneracionSolicitud_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1;

public interface SBB_CB_GeneracionSolicitud_PortType extends java.rmi.Remote {
    public co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSalidaGenerarSolicitud generarSolicitud(co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoEntradaGenerarSolicitud entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSalidaConsultarEstadoSolicitud consultarEstadoSolicitud(co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoEntradaConsultarEstadoSolicitud entrada) throws java.rmi.RemoteException;
}
