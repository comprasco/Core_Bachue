/**
 * SDI_CB_SolicitudDeCorreccion_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.ee.solicituddecorreccion.v1;

public class SDI_CB_SolicitudDeCorreccion_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ee.solicituddecorreccion.v1.SDI_CB_SolicitudDeCorreccion_PortType, org.apache.axis.wsdl.Skeleton {
    private co.gov.supernotariado.www.services.bachue.ee.solicituddecorreccion.v1.SDI_CB_SolicitudDeCorreccion_PortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1", "entradaObtenerCausalesCorreccion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1", "tipoObtenerCausalesCorreccion"), co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoObtenerCausalesCorreccion.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("obtenerCausalesCorreccion", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1", "salidaObtenerCausalesCorreccion"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1", "tipoSalidaObtenerCausalesCorreccion"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerCausalesCorreccion"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1");
        _myOperationsList.add(_oper);
        if (_myOperations.get("obtenerCausalesCorreccion") == null) {
            _myOperations.put("obtenerCausalesCorreccion", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("obtenerCausalesCorreccion")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1", "entradaIngresoSolicitud"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1", "tipoEntradaIngresoSolicitud"), co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitud.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("ingresoSolicitud", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1", "salidaIngresoSolicitud"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1", "tipoSalidaIngresoSolicitud"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "IngresoSolicitud"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1");
        _myOperationsList.add(_oper);
        if (_myOperations.get("ingresoSolicitud") == null) {
            _myOperations.put("ingresoSolicitud", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("ingresoSolicitud")).add(_oper);
    }

    public SDI_CB_SolicitudDeCorreccion_BindingSkeleton() {
        this.impl = new co.gov.supernotariado.www.services.bachue.ee.solicituddecorreccion.v1.SDI_CB_SolicitudDeCorreccion_BindingImpl();
    }

    public SDI_CB_SolicitudDeCorreccion_BindingSkeleton(co.gov.supernotariado.www.services.bachue.ee.solicituddecorreccion.v1.SDI_CB_SolicitudDeCorreccion_PortType impl) {
        this.impl = impl;
    }
    public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccion obtenerCausalesCorreccion(co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoObtenerCausalesCorreccion entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccion ret = impl.obtenerCausalesCorreccion(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoSalidaIngresoSolicitud ingresoSolicitud(co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitud entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoSalidaIngresoSolicitud ret = impl.ingresoSolicitud(entrada);
        return ret;
    }

}
