/**
 * SUT_CO_CreadorTurno_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.co.creadorturno.v1;

public class SUT_CO_CreadorTurno_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurno_Service {

    public SUT_CO_CreadorTurno_ServiceLocator() {
    }


    public SUT_CO_CreadorTurno_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SUT_CO_CreadorTurno_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SUT_CO_CreadorTurnoPort
    private java.lang.String SUT_CO_CreadorTurnoPort_address = "http://www.example.com";

    public java.lang.String getSUT_CO_CreadorTurnoPortAddress() {
        return SUT_CO_CreadorTurnoPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SUT_CO_CreadorTurnoPortWSDDServiceName = "SUT_CO_CreadorTurnoPort";

    public java.lang.String getSUT_CO_CreadorTurnoPortWSDDServiceName() {
        return SUT_CO_CreadorTurnoPortWSDDServiceName;
    }

    public void setSUT_CO_CreadorTurnoPortWSDDServiceName(java.lang.String name) {
        SUT_CO_CreadorTurnoPortWSDDServiceName = name;
    }

    public co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurno_PortType getSUT_CO_CreadorTurnoPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SUT_CO_CreadorTurnoPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSUT_CO_CreadorTurnoPort(endpoint);
    }

    public co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurno_PortType getSUT_CO_CreadorTurnoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurnoSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurnoSOAP12BindingStub(portAddress, this);
            _stub.setPortName(getSUT_CO_CreadorTurnoPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSUT_CO_CreadorTurnoPortEndpointAddress(java.lang.String address) {
        SUT_CO_CreadorTurnoPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurno_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurnoSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurnoSOAP12BindingStub(new java.net.URL(SUT_CO_CreadorTurnoPort_address), this);
                _stub.setPortName(getSUT_CO_CreadorTurnoPortWSDDServiceName());
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
        if ("SUT_CO_CreadorTurnoPort".equals(inputPortName)) {
            return getSUT_CO_CreadorTurnoPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/co/creadorturno/v1", "SUT_CO_CreadorTurno");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/co/creadorturno/v1", "SUT_CO_CreadorTurnoPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SUT_CO_CreadorTurnoPort".equals(portName)) {
            setSUT_CO_CreadorTurnoPortEndpointAddress(address);
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
