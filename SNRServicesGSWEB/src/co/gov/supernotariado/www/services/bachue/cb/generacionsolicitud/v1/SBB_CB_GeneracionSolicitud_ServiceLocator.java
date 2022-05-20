/**
 * SBB_CB_GeneracionSolicitud_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1;

public class SBB_CB_GeneracionSolicitud_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitud_Service {

    public SBB_CB_GeneracionSolicitud_ServiceLocator() {
    }


    public SBB_CB_GeneracionSolicitud_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SBB_CB_GeneracionSolicitud_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SBB_CB_GeneracionSolicitudPort
    private java.lang.String SBB_CB_GeneracionSolicitudPort_address = "http://localhost:7001/SNRServicesGSWEB/services/SBB_CB_GeneracionSolicitudPort";

    public java.lang.String getSBB_CB_GeneracionSolicitudPortAddress() {
        return SBB_CB_GeneracionSolicitudPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SBB_CB_GeneracionSolicitudPortWSDDServiceName = "SBB_CB_GeneracionSolicitudPort";

    public java.lang.String getSBB_CB_GeneracionSolicitudPortWSDDServiceName() {
        return SBB_CB_GeneracionSolicitudPortWSDDServiceName;
    }

    public void setSBB_CB_GeneracionSolicitudPortWSDDServiceName(java.lang.String name) {
        SBB_CB_GeneracionSolicitudPortWSDDServiceName = name;
    }

    public co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitud_PortType getSBB_CB_GeneracionSolicitudPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SBB_CB_GeneracionSolicitudPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSBB_CB_GeneracionSolicitudPort(endpoint);
    }

    public co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitud_PortType getSBB_CB_GeneracionSolicitudPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitudSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitudSOAP12BindingStub(portAddress, this);
            _stub.setPortName(getSBB_CB_GeneracionSolicitudPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSBB_CB_GeneracionSolicitudPortEndpointAddress(java.lang.String address) {
        SBB_CB_GeneracionSolicitudPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitud_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitudSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitudSOAP12BindingStub(new java.net.URL(SBB_CB_GeneracionSolicitudPort_address), this);
                _stub.setPortName(getSBB_CB_GeneracionSolicitudPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SBB_CB_GeneracionSolicitudPort".equals(inputPortName)) {
            return getSBB_CB_GeneracionSolicitudPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/generacionsolicitud/v1", "SBB_CB_GeneracionSolicitud");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/generacionsolicitud/v1", "SBB_CB_GeneracionSolicitudPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SBB_CB_GeneracionSolicitudPort".equals(portName)) {
            setSBB_CB_GeneracionSolicitudPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
