/**
 * SBB_CB_GeneracionSolicitudSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1;

public class SBB_CB_GeneracionSolicitudSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitud_PortType, org.apache.axis.wsdl.Skeleton {
    private co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitud_PortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1", "entradaGenerarSolicitud"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1", "tipoEntradaGenerarSolicitud"), co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoEntradaGenerarSolicitud.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("generarSolicitud", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1", "salidaGenerarSolicitud"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1", "tipoSalidaGenerarSolicitud"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "GenerarSolicitud"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/generacionsolicitud/v1/GenerarSolicitud");
        _myOperationsList.add(_oper);
        if (_myOperations.get("generarSolicitud") == null) {
            _myOperations.put("generarSolicitud", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("generarSolicitud")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1", "entradaConsultarEstadoSolicitud"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1", "tipoEntradaConsultarEstadoSolicitud"), co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoEntradaConsultarEstadoSolicitud.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarEstadoSolicitud", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1", "salidaConsultarEstadoSolicitud"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1", "tipoSalidaConsultarEstadoSolicitud"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarEstadoSolicitud"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/generacionsolicitud/v1/ConsultarEstadoSolicitud");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarEstadoSolicitud") == null) {
            _myOperations.put("consultarEstadoSolicitud", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarEstadoSolicitud")).add(_oper);
    }

    public SBB_CB_GeneracionSolicitudSOAP12BindingSkeleton() {
        this.impl = new co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitudSOAP12BindingImpl();
    }

    public SBB_CB_GeneracionSolicitudSOAP12BindingSkeleton(co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1.SBB_CB_GeneracionSolicitud_PortType impl) {
        this.impl = impl;
    }
    public co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSalidaGenerarSolicitud generarSolicitud(co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoEntradaGenerarSolicitud entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSalidaGenerarSolicitud ret = impl.generarSolicitud(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSalidaConsultarEstadoSolicitud consultarEstadoSolicitud(co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoEntradaConsultarEstadoSolicitud entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSalidaConsultarEstadoSolicitud ret = impl.consultarEstadoSolicitud(entrada);
        return ret;
    }

}
