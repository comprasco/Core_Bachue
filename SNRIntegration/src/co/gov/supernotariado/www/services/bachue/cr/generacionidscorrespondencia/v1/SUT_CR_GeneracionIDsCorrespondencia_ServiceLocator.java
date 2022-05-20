/**
 * SUT_CR_GeneracionIDsCorrespondencia_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1;

public class SUT_CR_GeneracionIDsCorrespondencia_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondencia_Service {

    public SUT_CR_GeneracionIDsCorrespondencia_ServiceLocator() {
    }


    public SUT_CR_GeneracionIDsCorrespondencia_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SUT_CR_GeneracionIDsCorrespondencia_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SUT_CR_GeneracionIDsCorrespondenciaPort
    private java.lang.String SUT_CR_GeneracionIDsCorrespondenciaPort_address = "http://www.example.com";

    public java.lang.String getSUT_CR_GeneracionIDsCorrespondenciaPortAddress() {
        return SUT_CR_GeneracionIDsCorrespondenciaPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SUT_CR_GeneracionIDsCorrespondenciaPortWSDDServiceName = "SUT_CR_GeneracionIDsCorrespondenciaPort";

    public java.lang.String getSUT_CR_GeneracionIDsCorrespondenciaPortWSDDServiceName() {
        return SUT_CR_GeneracionIDsCorrespondenciaPortWSDDServiceName;
    }

    public void setSUT_CR_GeneracionIDsCorrespondenciaPortWSDDServiceName(java.lang.String name) {
        SUT_CR_GeneracionIDsCorrespondenciaPortWSDDServiceName = name;
    }

    public co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondencia_PortType getSUT_CR_GeneracionIDsCorrespondenciaPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SUT_CR_GeneracionIDsCorrespondenciaPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSUT_CR_GeneracionIDsCorrespondenciaPort(endpoint);
    }

    public co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondencia_PortType getSUT_CR_GeneracionIDsCorrespondenciaPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondenciaSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondenciaSOAP12BindingStub(portAddress, this);
            _stub.setPortName(getSUT_CR_GeneracionIDsCorrespondenciaPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSUT_CR_GeneracionIDsCorrespondenciaPortEndpointAddress(java.lang.String address) {
        SUT_CR_GeneracionIDsCorrespondenciaPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondencia_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondenciaSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondenciaSOAP12BindingStub(new java.net.URL(SUT_CR_GeneracionIDsCorrespondenciaPort_address), this);
                _stub.setPortName(getSUT_CR_GeneracionIDsCorrespondenciaPortWSDDServiceName());
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
        if ("SUT_CR_GeneracionIDsCorrespondenciaPort".equals(inputPortName)) {
            return getSUT_CR_GeneracionIDsCorrespondenciaPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cr/generacionidscorrespondencia/v1", "SUT_CR_GeneracionIDsCorrespondencia");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cr/generacionidscorrespondencia/v1", "SUT_CR_GeneracionIDsCorrespondenciaPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SUT_CR_GeneracionIDsCorrespondenciaPort".equals(portName)) {
            setSUT_CR_GeneracionIDsCorrespondenciaPortEndpointAddress(address);
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
