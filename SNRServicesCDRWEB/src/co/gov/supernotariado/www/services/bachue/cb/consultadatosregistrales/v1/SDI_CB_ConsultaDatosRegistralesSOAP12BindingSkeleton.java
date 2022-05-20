/**
 * SDI_CB_ConsultaDatosRegistralesSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1;

public class SDI_CB_ConsultaDatosRegistralesSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistrales_PortType, org.apache.axis.wsdl.Skeleton {
    private co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistrales_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1", "entradaConsultarDatosRegistrales"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1", "tipoEntradaConsultarDatosRegistrales"), co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoEntradaConsultarDatosRegistrales.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarDatosRegistrales", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1", "salidaConsultarDatosRegistrales"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1", "tipoSalidaConsultarDatosRegistrales"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarDatosRegistrales"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/consultadatosregistrales/v1/ConsultarDatosRegistrales");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarDatosRegistrales") == null) {
            _myOperations.put("consultarDatosRegistrales", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarDatosRegistrales")).add(_oper);
    }

    public SDI_CB_ConsultaDatosRegistralesSOAP12BindingSkeleton() {
        this.impl = new co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistralesSOAP12BindingImpl();
    }

    public SDI_CB_ConsultaDatosRegistralesSOAP12BindingSkeleton(co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistrales_PortType impl) {
        this.impl = impl;
    }
    public co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistrales consultarDatosRegistrales(co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoEntradaConsultarDatosRegistrales entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistrales ret = impl.consultarDatosRegistrales(entrada);
        return ret;
    }

}
