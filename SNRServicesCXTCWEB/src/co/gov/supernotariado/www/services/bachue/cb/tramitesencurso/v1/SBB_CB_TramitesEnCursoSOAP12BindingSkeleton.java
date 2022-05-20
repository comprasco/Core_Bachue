/**
 * SBB_CB_TramitesEnCursoSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1;

public class SBB_CB_TramitesEnCursoSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCurso_PortType, org.apache.axis.wsdl.Skeleton {
    private co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCurso_PortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1", "entradaDatosTramitesEnCurso"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1", "tipoEntradaDatosTramites"), co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoEntradaDatosTramites.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarTramites", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1", "salidaDatosTramitesEnCurso"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1", "tipoSalidaDatosTramites"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "consultarTramites"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/v1/consultarTramites");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarTramites") == null) {
            _myOperations.put("consultarTramites", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarTramites")).add(_oper);
    }

    public SBB_CB_TramitesEnCursoSOAP12BindingSkeleton() {
        this.impl = new co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCursoSOAP12BindingImpl();
    }

    public SBB_CB_TramitesEnCursoSOAP12BindingSkeleton(co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1.SBB_CB_TramitesEnCurso_PortType impl) {
        this.impl = impl;
    }
    public co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoSalidaDatosTramites consultarTramites(co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoEntradaDatosTramites entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoSalidaDatosTramites ret = impl.consultarTramites(entrada);
        return ret;
    }

}
