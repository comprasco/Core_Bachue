/**
 * BS_SDI_CB_AlertaTierras_BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1;

public class BS_SDI_CB_AlertaTierras_BindingStub extends org.apache.axis.client.Stub implements co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1.BS_SDI_CB_AlertaTierras_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[21];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ActivarAlertaTierras");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "entradaActivarAlertaTierras"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "tipoEntradaActivarAlertaTierras"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoEntradaActivarAlertaTierras.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "tipoSalidaActivarAlertaTierras"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "salidaActivarAlertaTierras"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AgregarMatriculaAlerta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "entradaAgregarMatriculaAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "tipoEntradaAgregarMatriculaAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoEntradaAgregarMatriculaAlerta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "tipoSalidaAgregarMatriculaAlerta"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "salidaAgregarMatriculaAlerta"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CancelarIngresoAlertaTierras");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "entradaCancelarIngresoAlertaTierras"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "tipoEntradaCancelarIngresoAlertaTierras"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoEntradaCancelarIngresoAlertaTierras.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "tipoSalidaCancelarIngresoAlertaTierras"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "salidaCancelarIngresoAlertaTierras"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarAlertas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "entradaConsultarAlertas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "tipoEntradaConsultarAlertas"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertas.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "tipoSalidaConsultarAlertas"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "salidaConsultarAlertas"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarDetalleAlerta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "entradaConsultarDetalleAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "tipoEntradaConsultarDetalleAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoEntradaConsultarDetalleAlerta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "tipoSalidaConsultarDetalleAlerta"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "salidaConsultarDetalleAlerta"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarDocumentoAlerta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "entradaConsultarDocumentoAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "tipoEntradaConsultarDocumentoAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoEntradaConsultarDocumentoAlerta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "tipoSalidaConsultarDocumentoAlerta"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "salidaConsultarDocumentoAlerta"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarEntidadesJ_A");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", "entradaConsultarEntidadesJ_A"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", ">entradaConsultarEntidadesJ_A"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.EntradaConsultarEntidadesJ_A.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", "tipoSalidaConsultarEntidadesJ_A"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", "salidaConsultarEntidadesJ_A"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarListaMatriculas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "entradaConsultarListaMatriculas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "tipoEntradaConsultarListaMatriculas"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculas.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "tipoSalidaConsultarListaMatriculas"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "salidaConsultarListaMatriculas"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarMatricula");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "entradaConsultarMatricula"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "tipoEntradaConsultarMatricula"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoEntradaConsultarMatricula.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "tipoSalidaConsultarMatricula"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "salidaConsultarMatricula"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarMatriculaAlerta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "entradaConsultarMatriculaAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "tipoEntradaConsultarMatriculaAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoEntradaConsultarMatriculaAlerta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "tipoSalidaConsultarMatriculaAlerta"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "salidaConsultarMatriculaAlerta"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarMatriculaFiltrosTierras");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "entradaConsultarMatriculaFiltrosTierras"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "tipoEntradaConsultarMatriculaFiltrosTierras"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoEntradaConsultarMatriculaFiltrosTierras.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "tipoSalidaConsultarMatriculaFiltrosTierras"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "salidaConsultarMatriculaFiltrosTierras"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarMatriculaInfoCatastral");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "entradaConsultarMatriculaInfoCatastral"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "tipoEntradaConsultarMatriculaInfoCatastral"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoEntradaConsultarMatriculaInfoCatastral.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "tipoSalidaConsultarMatriculaICatastral"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "salidaConsultarMatriculaInfoCatastral"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarOficinasOrigenTipo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "entradaConsultarOficinasOrigenTipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "tipoEntradaConsultarOficinasOrigenTipo"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoEntradaConsultarOficinasOrigenTipo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "tipoSalidaConsultarOficinasOrigenTipo"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "salidaConsultarOficinasOrigenTipo"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InactivarAlerta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "entradaInactivarAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "tipoEntradaInactivarAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlerta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "tipoSalidaInactivarAlerta"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "salidaInactivarAlerta"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InscribirAlertaCabecera");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "entradaInscribirAlertaCabecera"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "tipoEntradaInscribirAlertaCabecera"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoEntradaInscribirAlertaCabecera.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "tipoSalidaInscribirAlertaCabecera"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "salidaInscribirAlertaCabecera"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RechazarCorreccionAlerta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "entradaRechazarCorreccionAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "tipoEntradaRechazarCorreccionAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoEntradaRechazarCorreccionAlerta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "tipoSalidaRechazarCorreccionAlerta"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "salidaRechazarCorreccionAlerta"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RemoverMatriculaAlerta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "entradaRemoverMatriculaAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "tipoEntradaRemoverMatriculaAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoEntradaRemoverMatriculaAlerta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "tipoSalidaRemoverMatriculaAlerta"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "salidaRemoverMatriculaAlerta"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CrearProcAntiguoSistemaTierras");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "entradaCrearProcAntiguoSistemaTierras"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "tipoEntradaCrearProcAntiguoSistemaTierras"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierras.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "tipoSalidaCrearProcAntiguoSistemaTierras"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "salidaCrearProcAntiguoSistemaTierras"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AgregarListaMatriculasAlerta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "entradaAgregarListaMatriculasAlerta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "tipoEntradaAgregarListaMatriculasAlerta"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlerta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "tipoSalidaAgregarListaMatriculasAlerta"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "salidaAgregarListaMatriculasAlerta"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ListarProcAntiguoSistema");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "entradaListarProcAntiguoSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "tipoEntradaListarProcAntiguoSistema"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoEntradaListarProcAntiguoSistema.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "tipoSalidaListarProcAntiguoSistema"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "salidaListarProcAntiguoSistema"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("EliminarProcAntiguoSistema");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "entradaEliminarProcAntiguoSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "tipoEntradaEliminarProcAntiguoSistema"), co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoEntradaEliminarProcAntiguoSistema.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "tipoSalidaEliminarProcAntiguoSistema"));
        oper.setReturnClass(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "salidaEliminarProcAntiguoSistema"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

    }

    public BS_SDI_CB_AlertaTierras_BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public BS_SDI_CB_AlertaTierras_BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public BS_SDI_CB_AlertaTierras_BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "tipoEntradaActivarAlertaTierras");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoEntradaActivarAlertaTierras.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "tipoSalidaActivarAlertaTierras");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", ">>tipoEntradaAgregarListaMatriculasAlerta>listaMatriculas>matricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", ">tipoEntradaAgregarListaMatriculasAlerta>listaMatriculas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", ">>tipoEntradaAgregarListaMatriculasAlerta>listaMatriculas>matricula");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "matricula");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "tipoEntradaAgregarListaMatriculasAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1", "tipoSalidaAgregarListaMatriculasAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "tipoEntradaAgregarMatriculaAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoEntradaAgregarMatriculaAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1", "tipoSalidaAgregarMatriculaAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "tipoEntradaCancelarIngresoAlertaTierras");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoEntradaCancelarIngresoAlertaTierras.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/cancelarIngresoAlertaTierras/v1", "tipoSalidaCancelarIngresoAlertaTierras");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", ">>tipoSalidaConsultarAlertas>listaAlertas>alerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertasListaAlertasAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", ">tipoEntradaConsultarAlertas>codigoEstado");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertasCodigoEstado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", ">tipoSalidaConsultarAlertas>listaAlertas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertasListaAlertasAlerta[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", ">>tipoSalidaConsultarAlertas>listaAlertas>alerta");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "alerta");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "tipoEntradaConsultarAlertas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertas.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "tipoSalidaConsultarAlertas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">>tipoSalidaConsultarDetalleAlerta>listaAlertasGeneradas>alertaGenerada");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">>tipoSalidaConsultarDetalleAlerta>listaMatriculas>matricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">>tipoSalidaConsultarDetalleAlerta>listaProcesosAS>procesoAS");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">tipoSalidaConsultarDetalleAlerta>inactivacion");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaInactivacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">tipoSalidaConsultarDetalleAlerta>listaAlertasGeneradas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">>tipoSalidaConsultarDetalleAlerta>listaAlertasGeneradas>alertaGenerada");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "alertaGenerada");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">tipoSalidaConsultarDetalleAlerta>listaMatriculas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">>tipoSalidaConsultarDetalleAlerta>listaMatriculas>matricula");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "matricula");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">tipoSalidaConsultarDetalleAlerta>listaProcesosAS");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", ">>tipoSalidaConsultarDetalleAlerta>listaProcesosAS>procesoAS");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "procesoAS");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "tipoEntradaConsultarDetalleAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoEntradaConsultarDetalleAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "tipoSalidaConsultarDetalleAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "tipoEntradaConsultarDocumentoAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoEntradaConsultarDocumentoAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "tipoSalidaConsultarDocumentoAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", ">>tipoSalidaConsultarEntidadesJ_A>listaAutoridadesJ_A>autoridad_A");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", ">entradaConsultarEntidadesJ_A");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.EntradaConsultarEntidadesJ_A.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", ">tipoSalidaConsultarEntidadesJ_A>listaAutoridadesJ_A");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", ">>tipoSalidaConsultarEntidadesJ_A>listaAutoridadesJ_A>autoridad_A");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", "autoridad_A");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1", "tipoSalidaConsultarEntidadesJ_A");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", ">>tipoEntradaConsultarListaMatriculas>listaMatricula>matricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculasListaMatriculaMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", ">>tipoSalidaConsultarListaMatriculas>listaMatriculas>matricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", ">tipoEntradaConsultarListaMatriculas>listaMatricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculasListaMatriculaMatricula[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", ">>tipoEntradaConsultarListaMatriculas>listaMatricula>matricula");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "matricula");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", ">tipoSalidaConsultarListaMatriculas>listaMatriculas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculasListaMatriculasMatricula[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", ">>tipoSalidaConsultarListaMatriculas>listaMatriculas>matricula");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "matricula");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "tipoEntradaConsultarListaMatriculas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculas.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1", "tipoSalidaConsultarListaMatriculas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", ">>tipoSalidaConsultarMatricula>listaMatriculas>matricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatriculaListaMatriculasMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", ">tipoSalidaConsultarMatricula>listaMatriculas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatriculaListaMatriculasMatricula[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", ">>tipoSalidaConsultarMatricula>listaMatriculas>matricula");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "matricula");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "tipoEntradaConsultarMatricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoEntradaConsultarMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "tipoSalidaConsultarMatricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", ">>tipoSalidaConsultarMatriculaAlerta>listaMatriculas>matricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlertaListaMatriculasMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", ">tipoSalidaConsultarMatriculaAlerta>listaMatriculas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlertaListaMatriculasMatricula[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", ">>tipoSalidaConsultarMatriculaAlerta>listaMatriculas>matricula");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "matricula");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "tipoEntradaConsultarMatriculaAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoEntradaConsultarMatriculaAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaAlerta/v1", "tipoSalidaConsultarMatriculaAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", ">>tipoSalidaConsultarMatriculaFiltrosTierras>listaMatriculas>matricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierrasListaMatriculasMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", ">tipoSalidaConsultarMatriculaFiltrosTierras>listaMatriculas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierrasListaMatriculasMatricula[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", ">>tipoSalidaConsultarMatriculaFiltrosTierras>listaMatriculas>matricula");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "matricula");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "tipoEntradaConsultarMatriculaFiltrosTierras");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoEntradaConsultarMatriculaFiltrosTierras.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1", "tipoSalidaConsultarMatriculaFiltrosTierras");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", ">>tipoSalidaConsultarMatriculaICatastral>listaMatriculas>matricula");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", ">tipoSalidaConsultarMatriculaICatastral>listaMatriculas");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastralListaMatriculasMatricula[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", ">>tipoSalidaConsultarMatriculaICatastral>listaMatriculas>matricula");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "matricula");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "tipoEntradaConsultarMatriculaInfoCatastral");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoEntradaConsultarMatriculaInfoCatastral.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1", "tipoSalidaConsultarMatriculaICatastral");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", ">>tipoSalidaConsultarOficinasOrigenTipo>listaOficinasOrigen>oficinaOrigen");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipoListaOficinasOrigenOficinaOrigen.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", ">tipoSalidaConsultarOficinasOrigenTipo>listaOficinasOrigen");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipoListaOficinasOrigenOficinaOrigen[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", ">>tipoSalidaConsultarOficinasOrigenTipo>listaOficinasOrigen>oficinaOrigen");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "oficinaOrigen");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "tipoEntradaConsultarOficinasOrigenTipo");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoEntradaConsultarOficinasOrigenTipo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1", "tipoSalidaConsultarOficinasOrigenTipo");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", ">tipoEntradaCrearProcAntiguoSistemaTierras>idTipoPartida");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasIdTipoPartida.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", ">tipoEntradaCrearProcAntiguoSistemaTierras>tipoPredio");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasTipoPredio.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "anio");
            cachedSerQNames.add(qName);
            cls = java.math.BigInteger.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "tipoEntradaCrearProcAntiguoSistemaTierras");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierras.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1", "tipoSalidaCrearProcAntiguoSistemaTierras");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "tipoEntradaEliminarProcAntiguoSistema");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoEntradaEliminarProcAntiguoSistema.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "tipoSalidaEliminarProcAntiguoSistema");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", ">tipoEntradaInactivarAlerta>idMotivo");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlertaIdMotivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "tipoEntradaInactivarAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "tipoSalidaInactivarAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "tipoEntradaInscribirAlertaCabecera");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoEntradaInscribirAlertaCabecera.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1", "tipoSalidaInscribirAlertaCabecera");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", ">>tipoSalidaListarProcAntiguoSistema>listaProcesosAS>procesoAS");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", ">tipoSalidaListarProcAntiguoSistema>listaProcesosAS");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", ">>tipoSalidaListarProcAntiguoSistema>listaProcesosAS>procesoAS");
            qName2 = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "procesoAS");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "tipoEntradaListarProcAntiguoSistema");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoEntradaListarProcAntiguoSistema.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "tipoSalidaListarProcAntiguoSistema");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "tipoEntradaRechazarCorreccionAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoEntradaRechazarCorreccionAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/rechazarCorreccionAlerta/v1", "tipoSalidaRechazarCorreccionAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "tipoEntradaRemoverMatriculaAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoEntradaRemoverMatriculaAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/removerMatriculaAlerta/v1", "tipoSalidaRemoverMatriculaAlerta");
            cachedSerQNames.add(qName);
            cls = co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras activarAlertaTierras(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoEntradaActivarAlertaTierras entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ActivarAlertaTierras");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ActivarAlertaTierras"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta agregarMatriculaAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoEntradaAgregarMatriculaAlerta entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/AgregarMatriculaAlerta");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "AgregarMatriculaAlerta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras cancelarIngresoAlertaTierras(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoEntradaCancelarIngresoAlertaTierras entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/CancelarIngresoAlertaTierras");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CancelarIngresoAlertaTierras"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas consultarAlertas(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertas entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarAlertas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarAlertas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta consultarDetalleAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoEntradaConsultarDetalleAlerta entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarDetalleAlerta");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarDetalleAlerta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta consultarDocumentoAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoEntradaConsultarDocumentoAlerta entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarDocumentoAlerta");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarDocumentoAlerta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A consultarEntidadesJ_A(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.EntradaConsultarEntidadesJ_A entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarEntidadesJ_A");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarEntidadesJ_A"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas consultarListaMatriculas(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculas entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarListaMatriculas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarListaMatriculas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula consultarMatricula(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoEntradaConsultarMatricula entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarMatricula");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarMatricula"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta consultarMatriculaAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoEntradaConsultarMatriculaAlerta entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarMatriculaAlerta");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarMatriculaAlerta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras consultarMatriculaFiltrosTierras(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoEntradaConsultarMatriculaFiltrosTierras entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarMatriculaFiltrosTierras");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarMatriculaFiltrosTierras"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral consultarMatriculaInfoCatastral(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoEntradaConsultarMatriculaInfoCatastral entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarMatriculaInfoCatastral");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarMatriculaInfoCatastral"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo consultarOficinasOrigenTipo(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoEntradaConsultarOficinasOrigenTipo entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ConsultarOficinasOrigenTipo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ConsultarOficinasOrigenTipo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta inactivarAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlerta entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/InactivarAlerta");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "InactivarAlerta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera inscribirAlertaCabecera(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoEntradaInscribirAlertaCabecera entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/InscribirAlertaCabecera");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "InscribirAlertaCabecera"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta rechazarCorreccionAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoEntradaRechazarCorreccionAlerta entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/RechazarCorreccionAlerta");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "RechazarCorreccionAlerta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta removerMatriculaAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoEntradaRemoverMatriculaAlerta entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/RemoverMatriculaAlerta");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "RemoverMatriculaAlerta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras crearProcAntiguoSistemaTierras(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierras entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/CrearProcAntiguoSistemaTierras");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CrearProcAntiguoSistemaTierras"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta agregarListaMatriculasAlerta(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlerta entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/AgregarListaMatriculasAlerta");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "AgregarListaMatriculasAlerta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema listarProcAntiguoSistema(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoEntradaListarProcAntiguoSistema entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/ListarProcAntiguoSistema");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ListarProcAntiguoSistema"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema eliminarProcAntiguoSistema(co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoEntradaEliminarProcAntiguoSistema entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/ee/alertaTierras/v1/EliminarProcAntiguoSistema");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "EliminarProcAntiguoSistema"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema) org.apache.axis.utils.JavaUtils.convert(_resp, co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
