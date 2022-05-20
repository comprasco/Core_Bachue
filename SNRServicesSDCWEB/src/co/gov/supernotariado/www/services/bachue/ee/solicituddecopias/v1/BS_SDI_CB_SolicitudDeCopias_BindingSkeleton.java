/**
 * BS_SDI_CB_SolicitudDeCopias_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.ee.solicituddecopias.v1;

public class BS_SDI_CB_SolicitudDeCopias_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ee.solicituddecopias.v1.BS_SDI_CB_SolicitudDeCopias_PortType, org.apache.axis.wsdl.Skeleton {
    private co.gov.supernotariado.www.services.bachue.ee.solicituddecopias.v1.BS_SDI_CB_SolicitudDeCopias_PortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1", "entradaIngresoSolicitud"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1", "tipoEntradaIngresoSolicitud"), co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitud.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("ingresoSolicitud", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1", "salidaIngresoSolicitud"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1", "tipoSalidaIngresoSolicitud"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "IngresoSolicitud"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1");
        _myOperationsList.add(_oper);
        if (_myOperations.get("ingresoSolicitud") == null) {
            _myOperations.put("ingresoSolicitud", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("ingresoSolicitud")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "entradaConsultarSolicitud"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "tipoEntradaConsultarSolicitud"), co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoEntradaConsultarSolicitud.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarSolicitud", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "salidaConsultarSolicitud"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "tipoSalidaConsultarSolicitud"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarSolicitud"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarSolicitud") == null) {
            _myOperations.put("consultarSolicitud", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarSolicitud")).add(_oper);
    }

    public BS_SDI_CB_SolicitudDeCopias_BindingSkeleton() {
        this.impl = new co.gov.supernotariado.www.services.bachue.ee.solicituddecopias.v1.BS_SDI_CB_SolicitudDeCopias_BindingImpl();
    }

    public BS_SDI_CB_SolicitudDeCopias_BindingSkeleton(co.gov.supernotariado.www.services.bachue.ee.solicituddecopias.v1.BS_SDI_CB_SolicitudDeCopias_PortType impl) {
        this.impl = impl;
    }
    public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitud ingresoSolicitud(co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitud entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitud ret = impl.ingresoSolicitud(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitud consultarSolicitud(co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoEntradaConsultarSolicitud entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitud ret = impl.consultarSolicitud(entrada);
        return ret;
    }

}
