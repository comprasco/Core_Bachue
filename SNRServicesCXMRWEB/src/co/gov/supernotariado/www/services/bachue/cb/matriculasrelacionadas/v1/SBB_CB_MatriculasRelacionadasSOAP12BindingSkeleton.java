/**
 * SBB_CB_MatriculasRelacionadasSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.v1;

public class SBB_CB_MatriculasRelacionadasSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.v1.SBB_CB_Matriculas_PortType, org.apache.axis.wsdl.Skeleton {
    private co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.v1.SBB_CB_Matriculas_PortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1", "entradaDatosMatriculasDerivadas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1", "tipoEntradaDatosMatriculasDerivadas"), co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoEntradaDatosMatriculasDerivadas.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarMatriculasDerivadas", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1", "salidaDatosMatriculasDerivadas"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1", "tipoSalidaDatosMatriculasDerivadas"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "consultarMatriculasDerivadas"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/v1/consultarMatriculasDerivadas");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarMatriculasDerivadas") == null) {
            _myOperations.put("consultarMatriculasDerivadas", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarMatriculasDerivadas")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasMatriz/v1", "entradaDatosMatriculasMatriz"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasMatriz/v1", "tipoEntradaDatosMatriculasMatriz"), co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoEntradaDatosMatriculasMatriz.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarMatriculasMatriz", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasMatriz/v1", "salidaDatosMatriculasMatriz"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasMatriz/v1", "tipoSalidaDatosMatriculasMatriz"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "consultarMatriculasMatriz"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/v1/consultarMatriculasMatriz");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarMatriculasMatriz") == null) {
            _myOperations.put("consultarMatriculasMatriz", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarMatriculasMatriz")).add(_oper);
    }

    public SBB_CB_MatriculasRelacionadasSOAP12BindingSkeleton() {
        this.impl = new co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.v1.SBB_CB_MatriculasRelacionadasSOAP12BindingImpl();
    }

    public SBB_CB_MatriculasRelacionadasSOAP12BindingSkeleton(co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.v1.SBB_CB_Matriculas_PortType impl) {
        this.impl = impl;
    }
    public co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoSalidaDatosMatriculasDerivadas consultarMatriculasDerivadas(co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoEntradaDatosMatriculasDerivadas entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoSalidaDatosMatriculasDerivadas ret = impl.consultarMatriculasDerivadas(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoSalidaDatosMatriculasMatriz consultarMatriculasMatriz(co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoEntradaDatosMatriculasMatriz entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoSalidaDatosMatriculasMatriz ret = impl.consultarMatriculasMatriz(entrada);
        return ret;
    }

}
