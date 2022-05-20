package com.bachue.snr.prosnr08.common.constants;


/**
 * Interface que contiene todos las llaves de error del sistema
 *
 * @author Edgar Prieto
 */
public interface ErrorKeys
{
	/** Constante SQL_ERROR. */
	public static final String SQL_ERROR = "errorSql";

	/** Propiedad error caso no esta en fase de entrega. */
	public final String ERROR_CASO_NO_ESTA_EN_FASE_DE_ENTREGA = "errorEntregaProuctoCasoConsultadoNoEstaEnFaseEntrega";

	/** Propiedad error cirterios de busqueda invalidos. */
	public final String ERROR_CIRTERIOS_DE_BUSQUEDA_INVALIDOS = "errorCriteriosDeBusquedaInvalidos";

	/** Propiedad error cirterios de busqueda invalidos recibo caja. */
	public final String ERROR_CIRTERIOS_DE_BUSQUEDA_INVALIDOS_RECIBO_CAJA = "errorCriteriosDeBusquedaInvalidosReciboCaja";

	/** Propiedad error codigo verificacion no valido. */
	public final String ERROR_CODIGO_VERIFICACION_NO_VALIDO = "errorCodigoVerificacionNoValido";

	/** Propiedad error codigo verificacion ya entregado. */
	public final String ERROR_CODIGO_VERIFICACION_YA_ENTREGADO = "errorCodigoVerificacionAsociadoAlDocumentoEntregado";

	/** Propiedad error codigo verificacion ya inactivo. */
	public final String ERROR_CODIGO_VERIFICACION_YA_INACTIVO = "errorCodigoVerificacionAsociadoAlDocumentoInactivo";

	/** Propiedad error documentos no generados. */
	public final String ERROR_DOCUMENTOS_NO_GENERADOS = "errorDocumentosNoGenerados";

	/** Propiedad error documento encontrado no asociado a turno. */
	public final String ERROR_DOCUMENTO_ENCONTRADO_NO_ASOCIADO_A_TURNO = "errorObtenerProductoDocumentoEncontradoNoAsociadoATurno";

	/** Propiedad error documento no autentico. */
	public final String ERROR_DOCUMENTO_NO_AUTENTICO = "errorDocumentoNoAutentico";

	/** Propiedad error documento no enviado al owcc. */
	public final String ERROR_DOCUMENTO_NO_ENVIADO_AL_OWCC = "errorEntregaProuctoDocumentoNoEnviadoOWCC";

	/** Propiedad error documento supero vigencia. */
	public final String ERROR_DOCUMENTO_SUPERO_VIGENCIA = "errorEntregaProuctoDocumentoSuperoVigencia";

	/** Propiedad error no encontraron turnos relacionados al nir. */
	public final String ERROR_NO_ENCONTRARON_TURNOS_RELACIONADOS_AL_NIR = "errorNoSeEncontraronTurnosRelacionadoAlNir";

	/** Propiedad error no se encontraron documentos activos. */
	public final String ERROR_NO_SE_ENCONTRARON_DOCUMENTOS_ACTIVOS = "errorNoSeEncontraronDocumentosActivos";

	/** Propiedad error no se encontraron turnos con documentos activos. */
	public final String ERROR_NO_SE_ENCONTRARON_TURNOS_CON_DOCUMENTOS_ACTIVOS = "errorNoSeEncontraronTurnosConDocumentosAsociadosActivos";

	/** Propiedad error no se encontro informacion para parametros ingresados. */
	public final String ERROR_NO_SE_ENCONTRO_INFORMACION_PARA_PARAMETROS_INGRESADOS = "errorObtenerProductoNoSeEncontroInformacionParaParametrosIngresados";

	/** Propiedad error no se encontro proceso numero referencia. */
	public final String ERROR_NO_SE_ENCONTRO_PROCESO_NUMERO_REFERENCIA = "errorNoSeEncontroProcesoValidoParaNumeroReferencia";

	/** Propiedad error no se encontro proceso valido para turno. */
	public final String ERROR_NO_SE_ENCONTRO_PROCESO_VALIDO_PARA_TURNO = "errorNoSeEncontroProcesoValidoParaTurno";

	/** Propiedad error no se puede obtener recibo de caja. */
	public final String ERROR_NO_SE_PUEDE_OBTENER_RECIBO_DE_CAJA = "errorNoSePuedeObtenerReciboCaja";

	/** Propiedad error numero referencia ingresado no valido. */
	public final String ERROR_NUMERO_REFERENCIA_INGRESADO_NO_VALIDO = "errorNumeroReferenciaIngresadoNoValido";

	/** Propiedad error parametros no validos. */
	public final String ERROR_PARAMETROS_NO_VALIDOS = "errorParametrosNoValidos";

	/** Propiedad error procedencia turno no es kiosko. */
	public final String ERROR_PROCEDENCIA_TURNO_NO_ES_KIOSKO = "errorProcedenciaTurnoNoEsKiosko";

	/** Propiedad error referencia pago no encontrado. */
	public final String ERROR_REFERENCIA_PAGO_NO_ENCONTRADO = "errorNumeroReferenciaIngresadoNoEncontrado";

	/** Propiedad error referencia pago no tiene asociado una solicitud. */
	public final String ERROR_REFERENCIA_PAGO_NO_TIENE_ASOCIADO_UNA_SOLICITUD = "errorEntregaProductoReferenciaPagoNoTieneAsociadoUnaSolicitud";

	/** Propiedad error referencia pago solo tiene un turno asociado. */
	public final String ERROR_REFERENCIA_PAGO_SOLO_TIENE_UN_TURNO_ASOCIADO = "errorEntregaProductoReferenciaPagoSoloTieneUnTurnoAsociado";

	/** Propiedad error referencia pago tiene mas de un turno asociado. */
	public final String ERROR_REFERENCIA_PAGO_TIENE_MAS_DE_UN_TURNO_ASOCIADO = "errorEntregaProductoReferenciaPagoTieneMasDeUnTurnoAsociado";

	/** Propiedad error solicitud id solicitud. */
	public final String ERROR_SOLICITUD_ID_SOLICITUD = "errorSolicitudIdSolicitud";

	/** Propiedad error turno codigo referencia sin productos. */
	public final String ERROR_TURNO_CODIGO_REFERENCIA_SIN_PRODUCTOS = "errorObtenerProductoTurnoCodigoOReferenciaSinProductos";
}
