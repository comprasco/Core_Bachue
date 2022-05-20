/**
 * BS_SDI_CB_AlertaTierras_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1;

public class BS_SDI_CB_AlertaTierras_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_PortType, org.apache.axis.wsdl.Skeleton {
    private co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_PortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "entradaActivarAlertaTierras"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "tipoEntradaActivarAlertaTierras"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoEntradaActivarAlertaTierras.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("activarAlertaTierras", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "salidaActivarAlertaTierras"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "tipoSalidaActivarAlertaTierras"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ActivarAlertaTierras"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ActivarAlertaTierras");
        _myOperationsList.add(_oper);
        if (_myOperations.get("activarAlertaTierras") == null) {
            _myOperations.put("activarAlertaTierras", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("activarAlertaTierras")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "entradaAgregarMatriculaAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "tipoEntradaAgregarMatriculaAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoEntradaAgregarMatriculaAlerta.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("agregarMatriculaAlerta", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "salidaAgregarMatriculaAlerta"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "tipoSalidaAgregarMatriculaAlerta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "AgregarMatriculaAlerta"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/AgregarMatriculaAlerta");
        _myOperationsList.add(_oper);
        if (_myOperations.get("agregarMatriculaAlerta") == null) {
            _myOperations.put("agregarMatriculaAlerta", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("agregarMatriculaAlerta")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "entradaCancelarIngresoAlertaTierras"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "tipoEntradaCancelarIngresoAlertaTierras"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoEntradaCancelarIngresoAlertaTierras.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("cancelarIngresoAlertaTierras", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "salidaCancelarIngresoAlertaTierras"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "tipoSalidaCancelarIngresoAlertaTierras"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "CancelarIngresoAlertaTierras"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/CancelarIngresoAlertaTierras");
        _myOperationsList.add(_oper);
        if (_myOperations.get("cancelarIngresoAlertaTierras") == null) {
            _myOperations.put("cancelarIngresoAlertaTierras", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("cancelarIngresoAlertaTierras")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "entradaConsultarAlertas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "tipoEntradaConsultarAlertas"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertas.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarAlertas", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "salidaConsultarAlertas"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "tipoSalidaConsultarAlertas"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarAlertas"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarAlertas");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarAlertas") == null) {
            _myOperations.put("consultarAlertas", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarAlertas")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "entradaConsultarDetalleAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "tipoEntradaConsultarDetalleAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoEntradaConsultarDetalleAlerta.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarDetalleAlerta", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "salidaConsultarDetalleAlerta"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "tipoSalidaConsultarDetalleAlerta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarDetalleAlerta"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarDetalleAlerta");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarDetalleAlerta") == null) {
            _myOperations.put("consultarDetalleAlerta", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarDetalleAlerta")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "entradaConsultarDocumentoAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "tipoEntradaConsultarDocumentoAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoEntradaConsultarDocumentoAlerta.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarDocumentoAlerta", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "salidaConsultarDocumentoAlerta"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "tipoSalidaConsultarDocumentoAlerta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarDocumentoAlerta"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarDocumentoAlerta");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarDocumentoAlerta") == null) {
            _myOperations.put("consultarDocumentoAlerta", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarDocumentoAlerta")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", "entradaConsultarEntidadesJ_A"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", ">entradaConsultarEntidadesJ_A"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.EntradaConsultarEntidadesJ_A.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarEntidadesJ_A", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", "salidaConsultarEntidadesJ_A"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", "tipoSalidaConsultarEntidadesJ_A"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarEntidadesJ_A"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarEntidadesJ_A");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarEntidadesJ_A") == null) {
            _myOperations.put("consultarEntidadesJ_A", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarEntidadesJ_A")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "entradaConsultarListaMatriculas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "tipoEntradaConsultarListaMatriculas"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculas.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarListaMatriculas", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "salidaConsultarListaMatriculas"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "tipoSalidaConsultarListaMatriculas"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarListaMatriculas"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarListaMatriculas");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarListaMatriculas") == null) {
            _myOperations.put("consultarListaMatriculas", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarListaMatriculas")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "entradaConsultarMatricula"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "tipoEntradaConsultarMatricula"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoEntradaConsultarMatricula.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarMatricula", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "salidaConsultarMatricula"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "tipoSalidaConsultarMatricula"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarMatricula"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarMatricula");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarMatricula") == null) {
            _myOperations.put("consultarMatricula", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarMatricula")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "entradaConsultarMatriculaAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "tipoEntradaConsultarMatriculaAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoEntradaConsultarMatriculaAlerta.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarMatriculaAlerta", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "salidaConsultarMatriculaAlerta"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "tipoSalidaConsultarMatriculaAlerta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarMatriculaAlerta"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarMatriculaAlerta");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarMatriculaAlerta") == null) {
            _myOperations.put("consultarMatriculaAlerta", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarMatriculaAlerta")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "entradaConsultarMatriculaFiltrosTierras"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "tipoEntradaConsultarMatriculaFiltrosTierras"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoEntradaConsultarMatriculaFiltrosTierras.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarMatriculaFiltrosTierras", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "salidaConsultarMatriculaFiltrosTierras"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "tipoSalidaConsultarMatriculaFiltrosTierras"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarMatriculaFiltrosTierras"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarMatriculaFiltrosTierras");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarMatriculaFiltrosTierras") == null) {
            _myOperations.put("consultarMatriculaFiltrosTierras", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarMatriculaFiltrosTierras")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "entradaConsultarMatriculaInfoCatastral"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "tipoEntradaConsultarMatriculaInfoCatastral"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoEntradaConsultarMatriculaInfoCatastral.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarMatriculaInfoCatastral", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "salidaConsultarMatriculaInfoCatastral"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "tipoSalidaConsultarMatriculaICatastral"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarMatriculaInfoCatastral"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarMatriculaInfoCatastral");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarMatriculaInfoCatastral") == null) {
            _myOperations.put("consultarMatriculaInfoCatastral", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarMatriculaInfoCatastral")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "entradaConsultarOficinasOrigenTipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "tipoEntradaConsultarOficinasOrigenTipo"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoEntradaConsultarOficinasOrigenTipo.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("consultarOficinasOrigenTipo", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "salidaConsultarOficinasOrigenTipo"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "tipoSalidaConsultarOficinasOrigenTipo"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarOficinasOrigenTipo"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarOficinasOrigenTipo");
        _myOperationsList.add(_oper);
        if (_myOperations.get("consultarOficinasOrigenTipo") == null) {
            _myOperations.put("consultarOficinasOrigenTipo", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("consultarOficinasOrigenTipo")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "entradaInactivarAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "tipoEntradaInactivarAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlerta.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("inactivarAlerta", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "salidaInactivarAlerta"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "tipoSalidaInactivarAlerta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "InactivarAlerta"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/InactivarAlerta");
        _myOperationsList.add(_oper);
        if (_myOperations.get("inactivarAlerta") == null) {
            _myOperations.put("inactivarAlerta", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("inactivarAlerta")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "entradaInscribirAlertaCabecera"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "tipoEntradaInscribirAlertaCabecera"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoEntradaInscribirAlertaCabecera.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("inscribirAlertaCabecera", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "salidaInscribirAlertaCabecera"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "tipoSalidaInscribirAlertaCabecera"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "InscribirAlertaCabecera"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/InscribirAlertaCabecera");
        _myOperationsList.add(_oper);
        if (_myOperations.get("inscribirAlertaCabecera") == null) {
            _myOperations.put("inscribirAlertaCabecera", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("inscribirAlertaCabecera")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "entradaRechazarCorreccionAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "tipoEntradaRechazarCorreccionAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoEntradaRechazarCorreccionAlerta.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("rechazarCorreccionAlerta", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "salidaRechazarCorreccionAlerta"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "tipoSalidaRechazarCorreccionAlerta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "RechazarCorreccionAlerta"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/RechazarCorreccionAlerta");
        _myOperationsList.add(_oper);
        if (_myOperations.get("rechazarCorreccionAlerta") == null) {
            _myOperations.put("rechazarCorreccionAlerta", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("rechazarCorreccionAlerta")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "entradaRemoverMatriculaAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "tipoEntradaRemoverMatriculaAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoEntradaRemoverMatriculaAlerta.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("removerMatriculaAlerta", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "salidaRemoverMatriculaAlerta"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "tipoSalidaRemoverMatriculaAlerta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "RemoverMatriculaAlerta"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/RemoverMatriculaAlerta");
        _myOperationsList.add(_oper);
        if (_myOperations.get("removerMatriculaAlerta") == null) {
            _myOperations.put("removerMatriculaAlerta", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("removerMatriculaAlerta")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "entradaCrearProcAntiguoSistemaTierras"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "tipoEntradaCrearProcAntiguoSistemaTierras"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierras.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("crearProcAntiguoSistemaTierras", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "salidaCrearProcAntiguoSistemaTierras"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "tipoSalidaCrearProcAntiguoSistemaTierras"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "CrearProcAntiguoSistemaTierras"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/CrearProcAntiguoSistemaTierras");
        _myOperationsList.add(_oper);
        if (_myOperations.get("crearProcAntiguoSistemaTierras") == null) {
            _myOperations.put("crearProcAntiguoSistemaTierras", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("crearProcAntiguoSistemaTierras")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "entradaAgregarListaMatriculasAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "tipoEntradaAgregarListaMatriculasAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlerta.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("agregarListaMatriculasAlerta", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "salidaAgregarListaMatriculasAlerta"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "tipoSalidaAgregarListaMatriculasAlerta"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "AgregarListaMatriculasAlerta"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/AgregarListaMatriculasAlerta");
        _myOperationsList.add(_oper);
        if (_myOperations.get("agregarListaMatriculasAlerta") == null) {
            _myOperations.put("agregarListaMatriculasAlerta", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("agregarListaMatriculasAlerta")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "entradaListarProcAntiguoSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "tipoEntradaListarProcAntiguoSistema"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoEntradaListarProcAntiguoSistema.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listarProcAntiguoSistema", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "salidaListarProcAntiguoSistema"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "tipoSalidaListarProcAntiguoSistema"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ListarProcAntiguoSistema"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ListarProcAntiguoSistema");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listarProcAntiguoSistema") == null) {
            _myOperations.put("listarProcAntiguoSistema", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listarProcAntiguoSistema")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "entradaEliminarProcAntiguoSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "tipoEntradaEliminarProcAntiguoSistema"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoEntradaEliminarProcAntiguoSistema.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("eliminarProcAntiguoSistema", _params, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "salidaEliminarProcAntiguoSistema"));
        _oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "tipoSalidaEliminarProcAntiguoSistema"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "EliminarProcAntiguoSistema"));
        _oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/EliminarProcAntiguoSistema");
        _myOperationsList.add(_oper);
        if (_myOperations.get("eliminarProcAntiguoSistema") == null) {
            _myOperations.put("eliminarProcAntiguoSistema", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("eliminarProcAntiguoSistema")).add(_oper);
    }

    public BS_SDI_CB_AlertaTierras_BindingSkeleton() {
        this.impl = new co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_BindingImpl();
    }

    public BS_SDI_CB_AlertaTierras_BindingSkeleton(co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_PortType impl) {
        this.impl = impl;
    }
    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras activarAlertaTierras(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoEntradaActivarAlertaTierras entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras ret = impl.activarAlertaTierras(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta agregarMatriculaAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoEntradaAgregarMatriculaAlerta entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta ret = impl.agregarMatriculaAlerta(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras cancelarIngresoAlertaTierras(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoEntradaCancelarIngresoAlertaTierras entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras ret = impl.cancelarIngresoAlertaTierras(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas consultarAlertas(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertas entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas ret = impl.consultarAlertas(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta consultarDetalleAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoEntradaConsultarDetalleAlerta entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta ret = impl.consultarDetalleAlerta(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta consultarDocumentoAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoEntradaConsultarDocumentoAlerta entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta ret = impl.consultarDocumentoAlerta(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A consultarEntidadesJ_A(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.EntradaConsultarEntidadesJ_A entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A ret = impl.consultarEntidadesJ_A(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas consultarListaMatriculas(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculas entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas ret = impl.consultarListaMatriculas(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula consultarMatricula(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoEntradaConsultarMatricula entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula ret = impl.consultarMatricula(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta consultarMatriculaAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoEntradaConsultarMatriculaAlerta entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta ret = impl.consultarMatriculaAlerta(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras consultarMatriculaFiltrosTierras(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoEntradaConsultarMatriculaFiltrosTierras entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras ret = impl.consultarMatriculaFiltrosTierras(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral consultarMatriculaInfoCatastral(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoEntradaConsultarMatriculaInfoCatastral entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral ret = impl.consultarMatriculaInfoCatastral(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo consultarOficinasOrigenTipo(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoEntradaConsultarOficinasOrigenTipo entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo ret = impl.consultarOficinasOrigenTipo(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta inactivarAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlerta entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta ret = impl.inactivarAlerta(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera inscribirAlertaCabecera(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoEntradaInscribirAlertaCabecera entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera ret = impl.inscribirAlertaCabecera(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta rechazarCorreccionAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoEntradaRechazarCorreccionAlerta entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta ret = impl.rechazarCorreccionAlerta(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta removerMatriculaAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoEntradaRemoverMatriculaAlerta entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta ret = impl.removerMatriculaAlerta(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras crearProcAntiguoSistemaTierras(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierras entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras ret = impl.crearProcAntiguoSistemaTierras(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta agregarListaMatriculasAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlerta entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta ret = impl.agregarListaMatriculasAlerta(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema listarProcAntiguoSistema(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoEntradaListarProcAntiguoSistema entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema ret = impl.listarProcAntiguoSistema(entrada);
        return ret;
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema eliminarProcAntiguoSistema(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoEntradaEliminarProcAntiguoSistema entrada) throws java.rmi.RemoteException
    {
        co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema ret = impl.eliminarProcAntiguoSistema(entrada);
        return ret;
    }

}
