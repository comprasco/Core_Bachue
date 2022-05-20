/**
 * SDI_CB_SolicitudDeCorreccion_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.ee.solicituddecorreccion.v1;

public interface SDI_CB_SolicitudDeCorreccion_PortType extends java.rmi.Remote {
    public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccion obtenerCausalesCorreccion(co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoObtenerCausalesCorreccion entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoSalidaIngresoSolicitud ingresoSolicitud(co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitud entrada) throws java.rmi.RemoteException;
}
