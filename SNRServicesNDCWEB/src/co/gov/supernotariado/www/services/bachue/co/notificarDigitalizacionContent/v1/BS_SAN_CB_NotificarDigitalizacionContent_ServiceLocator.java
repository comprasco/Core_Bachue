/**
 * BS_SAN_CB_NotificarDigitalizacionContent_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1;

public class BS_SAN_CB_NotificarDigitalizacionContent_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_Service {

/**
 * OSB Service
 */

    public BS_SAN_CB_NotificarDigitalizacionContent_ServiceLocator() {
    }


    public BS_SAN_CB_NotificarDigitalizacionContent_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BS_SAN_CB_NotificarDigitalizacionContent_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BS_SAN_CB_NotificarDigitalizacionContentPort
    private java.lang.String BS_SAN_CB_NotificarDigitalizacionContentPort_address = "http://localhost:7001/BS_SAN_CB_NotificarDigitalizacionContent";

    public java.lang.String getBS_SAN_CB_NotificarDigitalizacionContentPortAddress() {
        return BS_SAN_CB_NotificarDigitalizacionContentPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BS_SAN_CB_NotificarDigitalizacionContentPortWSDDServiceName = "BS_SAN_CB_NotificarDigitalizacionContentPort";

    public java.lang.String getBS_SAN_CB_NotificarDigitalizacionContentPortWSDDServiceName() {
        return BS_SAN_CB_NotificarDigitalizacionContentPortWSDDServiceName;
    }

    public void setBS_SAN_CB_NotificarDigitalizacionContentPortWSDDServiceName(java.lang.String name) {
        BS_SAN_CB_NotificarDigitalizacionContentPortWSDDServiceName = name;
    }

    public co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_PortType getBS_SAN_CB_NotificarDigitalizacionContentPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BS_SAN_CB_NotificarDigitalizacionContentPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBS_SAN_CB_NotificarDigitalizacionContentPort(endpoint);
    }

    public co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_PortType getBS_SAN_CB_NotificarDigitalizacionContentPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_BindingStub _stub = new co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_BindingStub(portAddress, this);
            _stub.setPortName(getBS_SAN_CB_NotificarDigitalizacionContentPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBS_SAN_CB_NotificarDigitalizacionContentPortEndpointAddress(java.lang.String address) {
        BS_SAN_CB_NotificarDigitalizacionContentPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_BindingStub _stub = new co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_BindingStub(new java.net.URL(BS_SAN_CB_NotificarDigitalizacionContentPort_address), this);
                _stub.setPortName(getBS_SAN_CB_NotificarDigitalizacionContentPortWSDDServiceName());
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
        if ("BS_SAN_CB_NotificarDigitalizacionContentPort".equals(inputPortName)) {
            return getBS_SAN_CB_NotificarDigitalizacionContentPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/co/notificarDigitalizacionContent/v1", "BS_SAN_CB_NotificarDigitalizacionContent");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/co/notificarDigitalizacionContent/v1", "BS_SAN_CB_NotificarDigitalizacionContentPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BS_SAN_CB_NotificarDigitalizacionContentPort".equals(portName)) {
            setBS_SAN_CB_NotificarDigitalizacionContentPortEndpointAddress(address);
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
