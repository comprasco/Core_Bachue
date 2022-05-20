package com.bachue.snr.prosnr03.common.constants;


/**
 * Interface que contiene todos las propiedades ErrorKeys.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/12/2019
 */
public interface ErrorKeys
{
	/** Constante SQL_ERROR. */
	public static final String SQL_ERROR = "errorSql";

	/** Propiedad circulo registral no encontrado. */
	public final String CIRCULO_REGISTRAL_NO_ENCONTRADO = "errorCirculoRegistralNoEncontrado";

	/** Propiedad error agrupacion no definida. */
	public final String ERROR_AGRUPACION_NO_DEFINIDA = "errorAgrupacionNoDefinida";

	/** Propiedad error attr usuario e1. */
	public final String ERROR_ATTR_USUARIO_E1 = "errorTipoDocumentoNoValido";

	/** Propiedad error attr usuario e2. */
	public final String ERROR_ATTR_USUARIO_E2 = "errorNumeroDocumentoNoValido";

	/** Propiedad error attr usuario e3. */
	public final String ERROR_ATTR_USUARIO_E3 = "errorCorreoElectronicoVacio";

	/** Propiedad error attr usuario e4. */
	public final String ERROR_ATTR_USUARIO_E4 = "errorNombreYApellidoNoValido";

	/** Propiedad error attr usuario e5. */
	public final String ERROR_ATTR_USUARIO_E5 = "errorCorreoElectronicoNoValido";

	/** Propiedad error attr usuario e6. */
	public final String ERROR_ATTR_USUARIO_E6 = "errorCorreoElectronicoCorporativoNoValido";

	/** Propiedad error attr usuario e7. */
	public final String ERROR_ATTR_USUARIO_E7 = "errorCorreoElectronicoDuplicado";

	/** Propiedad error cargo no valido. */
	public final String ERROR_CARGO_NO_VALIDO = "errorCargoNoValido";

	/** Propiedad error codigo invalido e2. */
	public final String ERROR_CODIGO_INVALIDO_E2 = "errorCodigoOripNoValido";

	/** Propiedad error codigo invalido e3. */
	public final String ERROR_CODIGO_INVALIDO_E3 = "errorCodigoConvenioNoValido";

	/** Propiedad error codigo invalido e4. */
	public final String ERROR_CODIGO_INVALIDO_E4 = "errorCodigoEntidadNoValido";

	/** Propiedad error codigo rol no valido. */
	public final String ERROR_CODIGO_ROL_NO_VALIDO = "errorCodigoRolNoValido";

	/** Propiedad error componente no valido. */
	public final String ERROR_COMPONENTE_NO_VALIDO = "errorComponenteIngresadoNoValido";

	/** Propiedad error entidad invalido e1. */
	public final String ERROR_ENTIDAD_INVALIDO_E1 = "errorNoExisteCodigoEntidadIngresado";

	/** Propiedad error estado usuario no valido. */
	public final String ERROR_ESTADO_USUARIO_NO_VALIDO = "errorEstadoUsuarioNoValido";

	/** Propiedad error fecha fin no valida. */
	public final String ERROR_FECHA_FIN_NO_VALIDA = "errorFechaFinNoValido";

	/** Propiedad error fecha fin vacia. */
	public final String ERROR_FECHA_FIN_VACIA = "errorFechaFinVacia";

	/** Propiedad error fecha inicio vacia. */
	public final String ERROR_FECHA_INICIO_VACIA = "errorFechaInicioVacia";

	/** Propiedad error fecha segunda clave. */
	public final String ERROR_FECHA_SEGUNDA_CLAVE = "errorFechaSegudaClaveNoValido";

	/** Propiedad error id politica no valido. */
	public final String ERROR_ID_POLITICA_NO_VALIDO = "errorIdPoliticaNoValido";

	/** Propiedad error justificacion cambio no valido. */
	public final String ERROR_JUSTIFICACION_CAMBIO_NO_VALIDO = "errorJustificacionCambioNoValido";

	/** Propiedad error neg funcionario e1. */
	public final String ERROR_NEG_FUNCIONARIO_E1 = "errorUsuarioExistente";

	/** Propiedad error neg funcionario e2. */
	public final String ERROR_NEG_FUNCIONARIO_E2 = "errorUsuarioInexistente";

	/** Propiedad error neg rol e1 e2. */
	public final String ERROR_NEG_ROL_E1_E2 = "errorAsociarRolOrip";

	/** Propiedad error numero solicitud no valido. */
	public final String ERROR_NUMERO_SOLICITUD_NO_VALIDO = "errorNumeroSolicitudNoValido";

	/** Propiedad error operacion no exitosa. */
	public final String ERROR_OPERACION_NO_EXITOSA = "errorOperacionNoExitosa";

	/** Propiedad error rol ingresado no encontrado. */
	public final String ERROR_ROL_INGRESADO_NO_ENCONTRADO = "errorRolIngresadoNoEncontrado";

	/** Propiedad error rol ingresado obligatorio. */
	public final String ERROR_ROL_INGRESADO_OBLIGATORIO = "errorRolIngresadoObligatorio";

	/** Propiedad error usuario inexistente e1. */
	public final String ERROR_USUARIO_INEXISTENTE_E1 = "errorUsuarioNoExiste";

	/** Propiedad error usuario no valido. */
	public final String ERROR_USUARIO_NO_VALIDO = "errorUsuarioNoValido";

	/** Propiedad error usuario sin orip rol. */
	public final String ERROR_USUARIO_SIN_ORIP_ROL = "errorUsuarioSinOripRol";
}
