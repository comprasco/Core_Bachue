/**
 * SDI_CB_ConsultaDatosRegistrales_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1;

public class SDI_CB_ConsultaDatosRegistrales_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistrales_Service {

    public SDI_CB_ConsultaDatosRegistrales_ServiceLocator() {
    }


    public SDI_CB_ConsultaDatosRegistrales_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SDI_CB_ConsultaDatosRegistrales_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SDI_CB_ConsultaDatosRegistralesPort
    private java.lang.String SDI_CB_ConsultaDatosRegistralesPort_address = "http://www.example.com";

    public java.lang.String getSDI_CB_ConsultaDatosRegistralesPortAddress() {
        return SDI_CB_ConsultaDatosRegistralesPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SDI_CB_ConsultaDatosRegistralesPortWSDDServiceName = "SDI_CB_ConsultaDatosRegistralesPort";

    public java.lang.String getSDI_CB_ConsultaDatosRegistralesPortWSDDServiceName() {
        return SDI_CB_ConsultaDatosRegistralesPortWSDDServiceName;
    }

    public void setSDI_CB_ConsultaDatosRegistralesPortWSDDServiceName(java.lang.String name) {
        SDI_CB_ConsultaDatosRegistralesPortWSDDServiceName = name;
    }

    public co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistrales_PortType getSDI_CB_ConsultaDatosRegistralesPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SDI_CB_ConsultaDatosRegistralesPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSDI_CB_ConsultaDatosRegistralesPort(endpoint);
    }

    public co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistrales_PortType getSDI_CB_ConsultaDatosRegistralesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistralesSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistralesSOAP12BindingStub(portAddress, this);
            _stub.setPortName(getSDI_CB_ConsultaDatosRegistralesPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSDI_CB_ConsultaDatosRegistralesPortEndpointAddress(java.lang.String address) {
        SDI_CB_ConsultaDatosRegistralesPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistrales_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistralesSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistralesSOAP12BindingStub(new java.net.URL(SDI_CB_ConsultaDatosRegistralesPort_address), this);
                _stub.setPortName(getSDI_CB_ConsultaDatosRegistralesPortWSDDServiceName());
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
        if ("SDI_CB_ConsultaDatosRegistralesPort".equals(inputPortName)) {
            return getSDI_CB_ConsultaDatosRegistralesPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultadatosregistrales/v1", "SDI_CB_ConsultaDatosRegistrales");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultadatosregistrales/v1", "SDI_CB_ConsultaDatosRegistralesPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SDI_CB_ConsultaDatosRegistralesPort".equals(portName)) {
            setSDI_CB_ConsultaDatosRegistralesPortEndpointAddress(address);
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
