/**
 * BiometriaControllerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bachue.snr.biometrico.servicios.ws;

public class BiometriaControllerLocator extends org.apache.axis.client.Service implements com.bachue.snr.biometrico.servicios.ws.BiometriaController {

    public BiometriaControllerLocator() {
    }


    public BiometriaControllerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BiometriaControllerLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BiometriaWSPort
    private java.lang.String BiometriaWSPort_address = "http://snrappdev06.eastus.cloudapp.azure.com:20187/BiometriaWS/BiometriaController";

    public java.lang.String getBiometriaWSPortAddress() {
        return BiometriaWSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BiometriaWSPortWSDDServiceName = "BiometriaWSPort";

    public java.lang.String getBiometriaWSPortWSDDServiceName() {
        return BiometriaWSPortWSDDServiceName;
    }

    public void setBiometriaWSPortWSDDServiceName(java.lang.String name) {
        BiometriaWSPortWSDDServiceName = name;
    }

    public com.bachue.snr.biometrico.servicios.ws.BiometriaWS getBiometriaWSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BiometriaWSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBiometriaWSPort(endpoint);
    }

    public com.bachue.snr.biometrico.servicios.ws.BiometriaWS getBiometriaWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bachue.snr.biometrico.servicios.ws.BiometriaWSPortBindingStub _stub = new com.bachue.snr.biometrico.servicios.ws.BiometriaWSPortBindingStub(portAddress, this);
            _stub.setPortName(getBiometriaWSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBiometriaWSPortEndpointAddress(java.lang.String address) {
        BiometriaWSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.bachue.snr.biometrico.servicios.ws.BiometriaWS.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bachue.snr.biometrico.servicios.ws.BiometriaWSPortBindingStub _stub = new com.bachue.snr.biometrico.servicios.ws.BiometriaWSPortBindingStub(new java.net.URL(BiometriaWSPort_address), this);
                _stub.setPortName(getBiometriaWSPortWSDDServiceName());
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
        if ("BiometriaWSPort".equals(inputPortName)) {
            return getBiometriaWSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "BiometriaController");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "BiometriaWSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BiometriaWSPort".equals(portName)) {
            setBiometriaWSPortEndpointAddress(address);
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
