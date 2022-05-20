/**
 * SBB_EF_OperacionesFinancieras_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2;

public class SBB_EF_OperacionesFinancieras_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancieras_Service {

    public SBB_EF_OperacionesFinancieras_ServiceLocator() {
    }


    public SBB_EF_OperacionesFinancieras_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SBB_EF_OperacionesFinancieras_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SBB_EF_OperacionesFinancierasPort
    private java.lang.String SBB_EF_OperacionesFinancierasPort_address = "http://www.example.com";

    public java.lang.String getSBB_EF_OperacionesFinancierasPortAddress() {
        return SBB_EF_OperacionesFinancierasPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SBB_EF_OperacionesFinancierasPortWSDDServiceName = "SBB_EF_OperacionesFinancierasPort";

    public java.lang.String getSBB_EF_OperacionesFinancierasPortWSDDServiceName() {
        return SBB_EF_OperacionesFinancierasPortWSDDServiceName;
    }

    public void setSBB_EF_OperacionesFinancierasPortWSDDServiceName(java.lang.String name) {
        SBB_EF_OperacionesFinancierasPortWSDDServiceName = name;
    }

    public co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancieras_PortType getSBB_EF_OperacionesFinancierasPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SBB_EF_OperacionesFinancierasPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSBB_EF_OperacionesFinancierasPort(endpoint);
    }

    public co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancieras_PortType getSBB_EF_OperacionesFinancierasPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancierasSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancierasSOAP12BindingStub(portAddress, this);
            _stub.setPortName(getSBB_EF_OperacionesFinancierasPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSBB_EF_OperacionesFinancierasPortEndpointAddress(java.lang.String address) {
        SBB_EF_OperacionesFinancierasPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancieras_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancierasSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancierasSOAP12BindingStub(new java.net.URL(SBB_EF_OperacionesFinancierasPort_address), this);
                _stub.setPortName(getSBB_EF_OperacionesFinancierasPortWSDDServiceName());
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
        if ("SBB_EF_OperacionesFinancierasPort".equals(inputPortName)) {
            return getSBB_EF_OperacionesFinancierasPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/ef/operacionesfinancieras/v2", "SBB_EF_OperacionesFinancieras");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/ef/operacionesfinancieras/v2", "SBB_EF_OperacionesFinancierasPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SBB_EF_OperacionesFinancierasPort".equals(portName)) {
            setSBB_EF_OperacionesFinancierasPortEndpointAddress(address);
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
