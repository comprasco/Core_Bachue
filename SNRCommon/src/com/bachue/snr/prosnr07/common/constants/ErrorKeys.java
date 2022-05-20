package com.bachue.snr.prosnr07.common.constants;


/**
 * Interface que contiene todos las llaves de error del sistema.
 *
 * @author Gabriel Arias
 */
public interface ErrorKeys
{
	/** Constante SQL_ERROR. */
	public static final String SQL_ERROR = "errorSql";
	
	/** Propiedad error modulo no permitido. */
	public final String ERROR_MODULO_NO_PERMITIDO                                 = "errorModuloNoPermitido";
	
	/** Propiedad error modulo no valido. */
	public final String ERROR_MODULO_NO_VALIDO                                    = "errorModuloNoValido";
	
	/** Propiedad error servicio no permitido. */
	public final String ERROR_SERVICIO_NO_PERMITIDO                               = "errorServicioNoPermitido";
	
	/** Propiedad error servicio no valido. */
	public final String ERROR_SERVICIO_NO_VALIDO                                  = "errorServicioNoValido";
	
	/** Propiedad error fecha desde. */
	public final String ERROR_FECHA_DESDE                                         = "errorFechaDesdeNoValida";
	
	/** Propiedad error fecha hasta. */
	public final String ERROR_FECHA_HASTA                                         = "errorFechaHastaNoValida";
	
	/** Propiedad fecha desde superior. */
	public final String FECHA_DESDE_SUPERIOR                                      = "errorFechaDesdeSuperior";
	
	/** Propiedad error tipo persona solicitante no permitido. */
	public final String ERROR_TIPO_PERSONA_SOLICITANTE_NO_PERMITIDO               = "errorTipoPersonaSolicitanteNoPermitido";
	
	/** Propiedad error tipo persona solicitante no valido. */
	public final String ERROR_TIPO_PERSONA_SOLICITANTE_NO_VALIDO                  = "errorTipoPersonaSolicitanteNoValido";
	
	/** Propiedad error tipo doc solicitante no valido. */
	public final String ERROR_TIPO_DOC_SOLICITANTE_NO_VALIDO                      = "errorTipoDocumentoSolicitanteNoValido";
	
	/** Propiedad error numero doc solicitante no valido. */
	public final String ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO                    = "errorNumeroDocumentoSolicitanteNoValido";
	
	/** Propiedad no se encontraron registros. */
	public final String NO_SE_ENCONTRARON_REGISTROS                               = "errorSinRegistros";
	
	/** Propiedad error parametros no validos. */
	public final String ERROR_PARAMETROS_NO_VALIDOS                               = "errorParametrosNoValidos";
	
	/** Propiedad error nir invalido. */
	public final String ERROR_NIR_INVALIDO                                        = "errorNirNoValido";
}
