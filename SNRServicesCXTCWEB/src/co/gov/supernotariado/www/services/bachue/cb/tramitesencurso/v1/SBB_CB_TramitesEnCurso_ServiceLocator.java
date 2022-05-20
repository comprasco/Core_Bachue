/**
 * SBB_CB_TramitesEnCurso_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1;

public class SBB_CB_TramitesEnCurso_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCurso_Service {

    public SBB_CB_TramitesEnCurso_ServiceLocator() {
    }


    public SBB_CB_TramitesEnCurso_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SBB_CB_TramitesEnCurso_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SBB_CB_TramitesEnCursoPort
    private java.lang.String SBB_CB_TramitesEnCursoPort_address = "http://www.example.com";

    public java.lang.String getSBB_CB_TramitesEnCursoPortAddress() {
        return SBB_CB_TramitesEnCursoPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SBB_CB_TramitesEnCursoPortWSDDServiceName = "SBB_CB_TramitesEnCursoPort";

    public java.lang.String getSBB_CB_TramitesEnCursoPortWSDDServiceName() {
        return SBB_CB_TramitesEnCursoPortWSDDServiceName;
    }

    public void setSBB_CB_TramitesEnCursoPortWSDDServiceName(java.lang.String name) {
        SBB_CB_TramitesEnCursoPortWSDDServiceName = name;
    }

    public co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCurso_PortType getSBB_CB_TramitesEnCursoPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SBB_CB_TramitesEnCursoPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSBB_CB_TramitesEnCursoPort(endpoint);
    }

    public co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCurso_PortType getSBB_CB_TramitesEnCursoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCursoSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCursoSOAP12BindingStub(portAddress, this);
            _stub.setPortName(getSBB_CB_TramitesEnCursoPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSBB_CB_TramitesEnCursoPortEndpointAddress(java.lang.String address) {
        SBB_CB_TramitesEnCursoPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCurso_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCursoSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCursoSOAP12BindingStub(new java.net.URL(SBB_CB_TramitesEnCursoPort_address), this);
                _stub.setPortName(getSBB_CB_TramitesEnCursoPortWSDDServiceName());
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
        if ("SBB_CB_TramitesEnCursoPort".equals(inputPortName)) {
            return getSBB_CB_TramitesEnCursoPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/v1", "SBB_CB_TramitesEnCurso");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/v1", "SBB_CB_TramitesEnCursoPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SBB_CB_TramitesEnCursoPort".equals(portName)) {
            setSBB_CB_TramitesEnCursoPortEndpointAddress(address);
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
