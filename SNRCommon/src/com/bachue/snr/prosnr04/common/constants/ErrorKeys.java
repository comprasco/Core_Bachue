package com.bachue.snr.prosnr04.common.constants;


/**
 * Interface que contiene todos las llaves de los errores del sistema.
 *
 * @author Edgar Prieto
 */
public interface ErrorKeys
{
	/** Constante SQL_ERROR. */
	public static final String SQL_ERROR = "errorSql";

	/** Propiedad circulo registral as no encontrado. */
	public final String CIRCULO_REGISTRAL_AS_NO_ENCONTRADO = "errorCirculoRegistralASNoEncontrado";

	/** Propiedad circulo registral no encontrado. */
	public final String CIRCULO_REGISTRAL_NO_ENCONTRADO = "errorCirculoRegistralNoEncontrado";

	/** Propiedad debe digitar id tipo persona. */
	public final String DEBE_DIGITAR_ID_TIPO_PERSONA = "errorSinIdTipoPersona";

	/** Propiedad error canal origen no parametrizado. */
	public final String ERROR_CANAL_ORIGEN_NO_PARAMETRIZADO = "errorCanalOrigenNoValido";

	/** Propiedad error canal origen no valido. */
	public final String ERROR_CANAL_ORIGEN_NO_VALIDO = "errorCodigoConvenioNoParametrizado";

	/** Propiedad error cantidad servicio invalido.*/
	public final String ERROR_CANTIDAD_SERVICIO_INVALIDO = "errorCantidadServicioInvalido";

	/** Propiedad error certificado no expedir 150 anotaciones. */
	public final String ERROR_CERTIFICADO_NO_EXPEDIR_150_ANOTACIONES = "errorCertificadoNoSePuedeExpedirPorqueFolioTieneMasDe150Anotaciones";

	/** Propiedad error no expedir anulado. */
	public final String ERROR_CERTIFICADO_NO_EXPEDIR_ANULADO = "errorCertificadoNoSePuedeExpedirPorqueFolioSeEncuentraAnulado";

	/** Propiedad error no expedir inconsistente. */
	public final String ERROR_CERTIFICADO_NO_EXPEDIR_INCONSISTENTE = "errorCertificadoNoSePuedeExpedirPorqueFolioSeEncuentraInconsistente";

	/** Propiedad error no expedir trasladado. */
	public final String ERROR_CERTIFICADO_NO_EXPEDIR_TRASLADADO = "errorCertificadoNoSePuedeExpedirPorqueFolioSeEncuentraTrasladado";

	/** Propiedad error clave pdf no generada. */
	public final String ERROR_CLAVE_PDF_NO_GENERADA = "errorClavePdfNoGenerada";

	/** Propiedad error codigo convenio no valido. */
	public final String ERROR_CODIGO_CONVENIO_NO_VALIDO = "errorCodigoConvenioNoValido";

	/** Propiedad error criterios no valido. */
	public final String ERROR_CODIGO_CRITERIO_PROCESO_SUBPROCESO_NO_VALIDO = "errorCodigoCriteriosNovalidos";

	/** Propiedad error codigo punto recaudo no permitido. */
	public final String ERROR_CODIGO_PUNTO_RECAUDO_NO_PERMITIDO = "errorCodigoPuntoRecaudoNoPermitido";

	/** Propiedad error codigo punto recaudo no valido. */
	public final String ERROR_CODIGO_PUNTO_RECAUDO_NO_VALIDO = "errorCodigoPuntoRecaudoNoValido";

	/** Propiedad error codigo recaudadora no permitido. */
	public final String ERROR_CODIGO_RECAUDADORA_NO_PERMITIDO = "errorCodigoEntidadRecaudadoraNoPermitido";

	/** Propiedad error codigo recaudadora no valido. */
	public final String ERROR_CODIGO_RECAUDADORA_NO_VALIDO = "errorCodigoEntidadRecaudadoraNoValido";

	/** Propiedad error codigo servicio solicitado invalido. */
	public final String ERROR_CODIGO_SERVICIO_SOLICITADO_INVALIDO = "errorCodigoServicioSocilitadoInvalido";

	/** Propiedad error codigo servicio solicitado no existente. */
	public final String ERROR_CODIGO_SERVICIO_SOLICITADO_NO_EXISTENTE = "errorCodigoServicioSocilitadoNoExiste";

	/** Propiedad error codigo subservicios no permitidos. */
	public final String ERROR_CODIGO_SUBSERVICIOS_NO_PERMITIDOS = "errorCodigoSubServiciosNoPermitidos";

	/** Propiedad error codigo subservicio solicitado invalido. */
	public final String ERROR_CODIGO_SUBSERVICIO_SOLICITADO_INVALIDO = "errorCodigoSubServicioSocilitadoInvalido";

	/** Propiedad error codigo subservicio solicitado no existe. */
	public final String ERROR_CODIGO_SUBSERVICIO_SOLICITADO_NO_EXISTE = "errorCodigoSubServicioSocilitadoNoExiste";

	/** Propiedad error codigo tipo recaudo no valido. */
	public final String ERROR_CODIGO_TIPO_RECAUDO_NO_VALIDO = "errorCodigoTipoRecaudoNoValido";

	/** Propiedad error codigo transaccion recaudo no valido. */
	public final String ERROR_CODIGO_TRANSACCION_RECAUDO_NO_VALIDO = "errorCodigoTransaccionRecaudadorNoValido";

	/** Propiedad error creando solicitud. */
	public final String ERROR_CREANDO_SOLICITUD = "errorCreandoSolicitud";

	/** Propiedad error criterios no valido. */
	public final String ERROR_CRITERIOS_NO_VALIDO = "errorCriteriosNovalidos";

	/** Propiedad error criterios no valido existentes. */
	public final String ERROR_CRITERIOS_NO_VALIDO_EXISTENTES = "errorCriteriosNovalidosExistentes";

	/** Propiedad error datos solicitante no existentes. */
	public final String ERROR_DATOS_SOLICITANTE_NO_EXISTENTES = "errorDatoSolicitanteNoExistentes";

	/** Propiedad error debe existir un servicio a consultar. */
	public final String ERROR_DEBE_EXISTIR_UN_SERVICIO_A_CONSULTAR = "errorDebeExistirUnServicioAConsultar";

	/** Propiedad error fecha bancaria no permitida. */
	public final String ERROR_FECHA_BANCARIA_NO_PERMITIDA = "errorFechaBancariaNoPermitida";

	/** Propiedad error fecha bancaria no valida. */
	public final String ERROR_FECHA_BANCARIA_NO_VALIDA = "errorFechaBancariaNoValida";

	/** Propiedad error fecha liquidacion no permitida. */
	public final String ERROR_FECHA_LIQUIDACION_NO_PERMITIDA = "errorFechaLiquidacionNoPermitida";

	/** Propiedad error fecha liquidacion post fecha vencimiento. */
	public final String ERROR_FECHA_LIQUIDACION_POST_FECHA_VENCIMIENTO = "errorFechaLiquidacionPosteriorFechaVencimiento";

	/** Propiedad error fecha recibo caja no valida. */
	public final String ERROR_FECHA_RECIBO_CAJA_NO_VALIDA = "errorFechaReciboCajaNoValida";

	/** Propiedad error fecha transaccion no permitida. */
	public final String ERROR_FECHA_TRANSACCION_NO_PERMITIDA = "errorFechaTransaccionNoPermitida";

	/** Propiedad error fecha transaccion no valida. */
	public final String ERROR_FECHA_TRANSACCION_NO_VALIDA = "errorFechaTransaccionNoValida";

	/** Propiedad error fecha vencimiento no valida. */
	public final String ERROR_FECHA_VENCIMIENTO_NO_VALIDA = "errorFechaVencimientoNoValida";

	/** Propiedad error indica canal origen. */
	public final String ERROR_INDICA_CANAL_ORIGEN = "errorIndicaCanalOrigen";

	/** Propiedad error libro antiguo sistema no valido. */
	public final String ERROR_LIBRO_ANTIGUO_SISTEMA_NO_VALIDO = "errorLibroAntiguoSistemaNoValido";

	/** Propiedad error monto mayor transaccion cero. */
	public final String ERROR_MONTO_MAYOR_TRANSACCION_CERO = "errorMontoTransaccionMayorCero";

	/** Propiedad error monto transaccion dif monto bachue. */
	public final String ERROR_MONTO_TRANSACCION_DIF_MONTO_BACHUE = "errorMontoTransaccionDiferenteMontoBachue";

	/** Propiedad error monto transaccion dif monto liquidado. */
	public final String ERROR_MONTO_TRANSACCION_DIF_MONTO_LIQUIDADO = "errorMontoTransaccionDiferenteMontoLiquidado";

	/** Propiedad error nir no existe. */
	public final String ERROR_NIR_NO_EXISTE = "errorNirNoExiste";

	/** Propiedad error nir no valido. */
	public final String ERROR_NIR_NO_VALIDO = "errorNirNoValido";

	/** Propiedad error nir obligatorio. */
	public final String ERROR_NIR_OBLIGATORIO = "errorNirObligatorio";

	/** Propiedad error no es posible cambiar estado liquidacion. */
	public final String ERROR_NO_ES_POSIBLE_CAMBIAR_ESTADO_LIQUIDACION = "errorNoEsPosibleCambiarEstadoLiquidacion";

	/** Propiedad error no existen constantes para criterios. */
	public final String ERROR_NO_EXISTEN_CONSTANTES_PARA_CRITERIOS = "errorNoExistenConstantesParaCriterios";

	/** Propiedad error no se encontro archivo de la constante. */
	public final String ERROR_NO_SE_ENCONTRO_ARCHIVO_DE_LA_CONSTANTE = "errorNoSeEncontroArchivoDeLaConstante";

	/** Propiedad error no se encontro la constante. */
	public final String ERROR_NO_SE_ENCONTRO_LA_CONSTANTE = "errorNoSeEncontroLaConstante";

	/** Propiedad error no se encontro persona asociada a numero referencia. */
	public final String ERROR_NO_SE_ENCONTRO_PERSONA_ASOCIADA_A_NUMERO_REFERENCIA = "errorNoSeEncontroPersonaAsociadaANumeroReferencia";

	/** Propiedad error numero doc solicitante no valido. */
	public final String ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO = "errorNumeroDocumentoSolicitanteNoValido";

	/** Propiedad error numero orip solicitud. */
	public final String ERROR_NUMERO_ORIP_SOLICITUD = "errorNumeroOripSolicitud";

	/** Propiedad error numero referencia existente. */
	public final String ERROR_NUMERO_REFERENCIA_EXISTENTE = "errorNumeroReferenciaExistente";

	/** Propiedad error numero referencia no disponible. */
	public final String ERROR_NUMERO_REFERENCIA_NO_DISPONIBLE = "errorReciboLiquidacionNoDisponible";

	/** Propiedad error numero referencia no existe. */
	public final String ERROR_NUMERO_REFERENCIA_NO_EXISTE = "errorNumeroReferenciaNoExiste";

	/** Propiedad error numero referencia no valido. */
	public final String ERROR_NUMERO_REFERENCIA_NO_VALIDO = "errorNumeroReferenciaNoValido";

	/** Propiedad error numero referencia obligatorio. */
	public final String ERROR_NUMERO_REFERENCIA_OBLIGATORIO = "errorNumeroReferenciaObligatorio";

	/** Propiedad error numero referencia vencido. */
	public final String ERROR_NUMERO_REFERENCIA_VENCIDO = "errorNumeroReferenciaVencido";

	/** Propiedad error numero servicios a consultar no valido. */
	public final String ERROR_NUMERO_SERVICIOS_A_CONSULTAR_NO_VALIDO = "errorNumeroServiciosAConsultarNoValido";

	/** Propiedad error operacion no exitosa. */
	public final String ERROR_OPERACION_NO_EXITOSA = "errorOperacionNoExitosa";

	/** Propiedad error parametrizacion relacion procesos criterios. */
	public final String ERROR_PARAMETRIZACION_RELACION_PROCESOS_CRITERIOS = "errorParametrizacionIncorrectaRelacionProcesoCriterios";

	/** Propiedad error parametros no validos. */
	public final String ERROR_PARAMETROS_NO_VALIDOS = "errorParametrosNoValidos";

	/** Propiedad error primer apellido no valido. */
	public final String ERROR_PRIMER_APELLIDO_NO_VALIDO = "errorPrimerApellidoNoValido";

	/** Propiedad error primer nombre apellido no valido. */
	public final String ERROR_PRIMER_NOMBRE_APELLIDO_NO_VALIDO = "errorPrimerNombreApellidoNoValido";

	/** Propiedad error primer nombre no valido. */
	public final String ERROR_PRIMER_NOMBRE_NO_VALIDO = "errorPrimerNombreNoValido";

	/** Propiedad error procedimiento liquidacion. */
	public final String ERROR_PROCEDIMIENTO_LIQUIDACION = "errorProcedimientoLiquidacion";

	/** Propiedad error razon social no valido. */
	public final String ERROR_RAZON_SOCIAL_NO_VALIDO = "errorRazonSocialNoValido";

	/** Propiedad error recibo liquidacion notificado. */
	public final String ERROR_RECIBO_LIQUIDACION_NOTIFICADO = "errorReciboLiquidacionYaNotificado";

	/** Propiedad error recibo liquidacion no existe. */
	public final String ERROR_RECIBO_LIQUIDACION_NO_EXISTE = "errorReciboLiquidacionNoExiste";

	/** Propiedad error recibo liquidacion pagado. */
	public final String ERROR_RECIBO_LIQUIDACION_PAGADO = "errorReciboLiquidacionYaPagado";

	/** Propiedad error recibo liquidacion vencido. */
	public final String ERROR_RECIBO_LIQUIDACION_VENCIDO = "errorReciboLiquidacionVencido";

	/** Propiedad error referencia ingresada tiene pago realizado. */
	public final String ERROR_REFERENCIA_INGRESADA_TIENE_PAGO_REALIZADO = "errorReferenciaIngresadaTienePagoRealizado";

	/** Propiedad error servicio subservicio criterio duplicados. */
	public final String ERROR_SERVICIO_SUBSERVICIO_CRITERIO_DUPLICADOS = "errorServicioSubServicioCriteriosDuplicados";

	/** Propiedad error servicio subservicio criterio formato fecha valido. */
	public final String ERROR_SERVICIO_SUBSERVICIO_CRITERIO_FORMATO_FECHA_VALIDO = "errorServicioSubServicioCriteriosFormatoFechaValido";

	/** Propiedad error servicio subservicio criterio formato no valido. */
	public final String ERROR_SERVICIO_SUBSERVICIO_CRITERIO_FORMATO_NO_VALIDO = "errorServicioSubServicioCriteriosFormatoNoValido";

	/** Propiedad error servicio subservicio criterio formato valido. */
	public final String ERROR_SERVICIO_SUBSERVICIO_CRITERIO_FORMATO_VALIDO = "errorServicioSubServicioCriteriosFormatoValido";

	/** Propiedad error servicio subservicio criterio invalido. */
	public final String ERROR_SERVICIO_SUBSERVICIO_CRITERIO_INVALIDO = "errorServicioSubServicioCriteriosFormatoInvalido";

	/** Propiedad error servicio subservicio criterio longitud invalida. */
	public final String ERROR_SERVICIO_SUBSERVICIO_CRITERIO_LONGITUD_INVALIDA = "errorServicioSubServicioCriteriosLongitudInvalida";

	/** Propiedad error servicio subservicio criterio obligatorio. */
	public final String ERROR_SERVICIO_SUBSERVICIO_CRITERIO_OBLIGATORIO = "errorServicioSubServicioCriteriosObligatorios";

	/** Propiedad error servicio subservicio criterio valor invalido. */
	public final String ERROR_SERVICIO_SUBSERVICIO_CRITERIO_VALOR_INVALIDO = "errorServicioSubServicioCriteriosValorInvalido";

	/** Propiedad error servicio subservicio criterio valor invalido alertas. */
	public final String ERROR_SERVICIO_SUBSERVICIO_CRITERIO_VALOR_INVALIDO_ALERTAS = "errorServicioSubServicioCriteriosValorInvalidoAlertas";

	/** Propiedad error servicio subservicio parametrizacion no valida. */
	public final String ERROR_SERVICIO_SUBSERVICIO_PARAMETRIZACION_NO_VALIDA = "errorServicioSubServicioNoValido";

	/** Propiedad error sin valor recarga.*/
	public final String ERROR_SIN_VALOR_RECARGA = "errorSinValorRecarga";

	/** Propiedad error solo permite un sub tipo servicio. */
	public final String ERROR_SOLO_PERMITE_UN_SUB_TIPO_SERVICIO = "errorSoloPermiteUnSubTipoServicio";

	/** Propiedad error solo permite un tipo servicio. */
	public final String ERROR_SOLO_PERMITE_UN_TIPO_SERVICIO = "errorSoloPermiteUnTipoServicio";

	/** Propiedad error sucursal canal origen no parametrizado. */
	public final String ERROR_SUCURSAL_CANAL_ORIGEN_NO_PARAMETRIZADO = "errorCodigoSucursalCanalNoParametrizado";

	/** Propiedad error tipo doc solicitante no existe. */
	public final String ERROR_TIPO_DOC_SOLICITANTE_NO_EXISTE = "errorTipoDocumentoSolicitanteNoExiste";

	/** Propiedad error tipo doc solicitante no valido. */
	public final String ERROR_TIPO_DOC_SOLICITANTE_NO_VALIDO = "errorTipoDocumentoSolicitanteNoValido";

	/** Propiedad error tipo persona solicitante no valido. */
	public final String ERROR_TIPO_PERSONA_SOLICITANTE_NO_VALIDO = "errorTipoPersonaSolicitanteNoValido";

	/** Propiedad error tipo predio no valido. */
	public final String ERROR_TIPO_PREDIO_NO_VALIDO = "errorTipoPredioNoValido";

	/** Propiedad error tipo recaudo punto recaudo no valido. */
	public final String ERROR_TIPO_RECAUDO_PUNTO_RECAUDO_NO_VALIDO = "errorTipoRecaudoPuntoRecaudoNoValido";

	/** Propiedad error tipo y numero documento no diligenciados. */
	public final String ERROR_TIPO_Y_NUMERO_DOCUMENTO_NO_DILIGENCIADOS = "errorTipoYNumeroDocumentoNoDiligenciados";

	/** Propiedad error usuario debe actualizar datos personales. */
	public final String ERROR_USUARIO_DEBE_ACTUALIZAR_DATOS_PERSONALES = "errorUsuarioDebeActualizarDatosPersonales";

	/** Propiedad error valores min max cuenta cupo no validos. */
	public final String ERROR_VALORES_MIN_MAX_CUENTA_CUPO_NO_VALIDOS = "errorValoresMinMaxCuentaCupoNoValidos";

	/** Propiedad error criterio cantidad no valida. */
	public final String ERROR_VALOR_CRITERIO_CANTIDAD_NO_VALIDO = "errorValorCriterioCantidadNoValido";

	/** Propiedad error criterio generico no valida. */
	public final String ERROR_VALOR_CRITERIO_GENERICO_NO_VALIDO = "errorValorCriterioGenericoNoValido";

	/** Propiedad error criterios no valido. */
	public final String ERROR_VALOR_CRITERIO_PROCESO_SUBPROCESO_NO_VALIDO = "errorValorCriteriosNovalidos";

	/** Propiedad error valor recarga diferente valor servicio. */
	public final String ERROR_VALOR_RECARGA_DIFERENTE_VALOR_SERVICIO = "errorValorRecargaDiferenteValorServicio";

	/** Propiedad error valor recarga no valido.*/
	public final String ERROR_VALOR_RECARGA_NO_VALIDO = "errorValorRecargaNoValido";

	/** Propiedad error valor servicio diferente a bachue.*/
	public final String ERROR_VALOR_SERVICIO_DIFERENTE_A_BACHUE = "errorValorServicioDiferenteValorBachue";

	/** Propiedad error valor servicio invalido.*/
	public final String ERROR_VALOR_SERVICIO_INVALIDO = "errorValorServicioInvalido";

	/** Propiedad error valor servicio mayor cero. */
	public final String ERROR_VALOR_SERVICIO_MAYOR_CERO = "errorValorTotalServicioMayorCero";

	/** Propiedad error valor servicio no válido.*/
	public final String ERROR_VALOR_SERVICIO_NO_VALIDO = "errorValorServicioNoValido";

	/** Propiedad error ya existen matriculas alertas persona. */
	public final String ERROR_YA_EXISTEN_MATRICULAS_ALERTAS_PERSONA = "errorYaExistenMatriculasAlertasPersona";

	/** Propiedad matricula inmobiliaria no encontrada. */
	public final String MATRICULA_INMOBILIARIA_NO_ENCONTRADA = "errorMatriculaInmoviliariaNoEncontrada";

	/** Propiedad numero referencia anulada. */
	public final String NUMERO_REFERENCIA_ANULADA = "errorNumeroReferenciaAnulada";

	/** Propiedad numero referencia pagado. */
	public final String NUMERO_REFERENCIA_PAGADO = "errorNumeroReferenciaPagado";

	/** Propiedad numero referencia recibo caja generado. */
	public final String NUMERO_REFERENCIA_RECIBO_CAJA_GENERADO = "errorNumeroReferenciaReciboDeCajaGenerado";

	/** Propiedad numero referencia vencido. */
	public final String NUMERO_REFERENCIA_VENCIDO = "errorNumeroReferenciaVencido";
}
