/**
 * BS_SDI_CB_AlertaTierras_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1;

public class BS_SDI_CB_AlertaTierras_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_Service {

    public BS_SDI_CB_AlertaTierras_ServiceLocator() {
    }


    public BS_SDI_CB_AlertaTierras_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BS_SDI_CB_AlertaTierras_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BS_SDI_CB_AlertaTierrasPort
    private java.lang.String BS_SDI_CB_AlertaTierrasPort_address = "http://www.example.com";

    public java.lang.String getBS_SDI_CB_AlertaTierrasPortAddress() {
        return BS_SDI_CB_AlertaTierrasPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BS_SDI_CB_AlertaTierrasPortWSDDServiceName = "BS_SDI_CB_AlertaTierrasPort";

    public java.lang.String getBS_SDI_CB_AlertaTierrasPortWSDDServiceName() {
        return BS_SDI_CB_AlertaTierrasPortWSDDServiceName;
    }

    public void setBS_SDI_CB_AlertaTierrasPortWSDDServiceName(java.lang.String name) {
        BS_SDI_CB_AlertaTierrasPortWSDDServiceName = name;
    }

    public co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_PortType getBS_SDI_CB_AlertaTierrasPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BS_SDI_CB_AlertaTierrasPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBS_SDI_CB_AlertaTierrasPort(endpoint);
    }

    public co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_PortType getBS_SDI_CB_AlertaTierrasPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_BindingStub _stub = new co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_BindingStub(portAddress, this);
            _stub.setPortName(getBS_SDI_CB_AlertaTierrasPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBS_SDI_CB_AlertaTierrasPortEndpointAddress(java.lang.String address) {
        BS_SDI_CB_AlertaTierrasPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_BindingStub _stub = new co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_BindingStub(new java.net.URL(BS_SDI_CB_AlertaTierrasPort_address), this);
                _stub.setPortName(getBS_SDI_CB_AlertaTierrasPortWSDDServiceName());
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
        if ("BS_SDI_CB_AlertaTierrasPort".equals(inputPortName)) {
            return getBS_SDI_CB_AlertaTierrasPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1", "BS_SDI_CB_AlertaTierras");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1", "BS_SDI_CB_AlertaTierrasPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BS_SDI_CB_AlertaTierrasPort".equals(portName)) {
            setBS_SDI_CB_AlertaTierrasPortEndpointAddress(address);
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
