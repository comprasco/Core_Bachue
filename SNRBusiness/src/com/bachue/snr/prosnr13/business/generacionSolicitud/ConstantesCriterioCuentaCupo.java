package com.bachue.snr.prosnr13.business.generacionSolicitud;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Interfaz para almacenar los campos asociados a la creación de una cuenta cupo.
 *
 * @author Manuel Blanco
 */
public interface ConstantesCriterioCuentaCupo
{
	/** Constante descripcion actividad economica. */
	String DESCRIPCION_ACTIVIDAD_ECONOMICA = "DESCRIPCION_ACTIVIDAD_ECONOMICA";

	/** Constante descripcion frecuencia recargas. */
	String DESCRIPCION_FRECUENCIA_RECARGAS = "DESCRIPCION_FRECUENCIA_RECARGAS";

	/** Constante descripcion necesidad de cuenta cupo. */
	String DESCRIPCION_NECESIDAD_DE_CUENTA_CUPO = "DESCRIPCION_NECESIDAD_DE_CUENTA_CUPO";

	/** Constante descripcion origen recursos. */
	String DESCRIPCION_ORIGEN_RECURSOS = "DESCRIPCION_ORIGEN_RECURSOS";

	/** Constante empresa digito verificacion nit. */
	String EMPRESA_DIGITO_VERIFICACION_NIT = "EMPRESA_DIGITO_VERIFICACION_NIT";

	/** Constante empresa dirección. */
	String EMPRESA_DIRECCION = "EMPRESA_DIRECCION";

	/** Constante empresa numero documento. */
	String EMPRESA_NUMERO_DOCUMENTO = "EMPRESA_NUMERO_DOCUMENTO";

	/** Constante empresa tipo documento. */
	String EMPRESA_TIPO_DOCUMENTO = "EMPRESA_TIPO_DOCUMENTO";

	/** Constante fecha diligenciamiento. */
	String FECHA_DILIGENCIAMIENTO = "FECHA DILIGENCIAMIENTO";

	/** Constante id cuenta cupo. */
	String ID_CUENTA_CUPO = "ID_CUENTA_CUPO";

	/** Constante monto solicitado. */
	String MONTO_SOLICITADO = "MONTO_SOLICITADO";

	/** Constante motivo cancelacion. */
	String MOTIVO_CANCELACION = "MOTIVO_CANCELACION";

	/** Constante motivo solicitud. */
	String MOTIVO_SOLICITUD = "MOTIVO_SOLICITUD";

	/** Constante nuevo valor maximo. */
	String NUEVO_VALOR_MAXIMO = "NUEVO_VALOR_MAXIMO";

	/** Constante nuevo valor minimo. */
	String NUEVO_VALOR_MINIMO = "NUEVO_VALOR_MINIMO";

	/** Constante razon social. */
	String RAZON_SOCIAL = "RAZON_SOCIAL";

	/** Constante representante legal celular. */
	String REPRESENTANTE_LEGAL_CELULAR = "REPRESENTANTE_LEGAL_CELULAR";

	/** Constante representante legal correo electronico. */
	String REPRESENTANTE_LEGAL_CORREO_ELECTRONICO = "REPRESENTANTE_LEGAL_CORREO_ELECTRONICO";

	/** Constante representante legal numero documento. */
	String REPRESENTANTE_LEGAL_NUMERO_DOCUMENTO = "REPRESENTANTE_LEGAL_NUMERO_DOCUMENTO";

	/** Constante representante legal ocupacion profesion. */
	String REPRESENTANTE_LEGAL_OCUPACION_PROFESION = "REPRESENTANTE_LEGAL_OCUPACION_PROFESION";

	/** Constante representante legal primer apellido. */
	String REPRESENTANTE_LEGAL_PRIMER_APELLIDO = "REPRESENTANTE_LEGAL_PRIMER_APELLIDO";

	/** Constante representante legal primer nombre. */
	String REPRESENTANTE_LEGAL_PRIMER_NOMBRE = "REPRESENTANTE_LEGAL_PRIMER_NOMBRE";

	/** Constante representante legal segundo apellido. */
	String REPRESENTANTE_LEGAL_SEGUNDO_APELLIDO = "REPRESENTANTE_LEGAL_SEGUNDO_APELLIDO";

	/** Constante representante legal segundo nombre. */
	String REPRESENTANTE_LEGAL_SEGUNDO_NOMBRE = "REPRESENTANTE_LEGAL_SEGUNDO_NOMBRE";

	/** Constante representante legal tipo documento. */
	String REPRESENTANTE_LEGAL_TIPO_DOCUMENTO = "REPRESENTANTE_LEGAL_TIPO_DOCUMENTO";

	/** Constante tipo empresa. */
	String TIPO_EMPRESA = "TIPO_EMPRESA";

	/**
	 * Obtiene los campos qeu son obligatorios en la cancelación de una cuenta cupo.
	 *
	 * @return Colección resultante
	 */
	default Collection<String> obtenerConstantesCancelacion()
	{
		Collection<String> lcs_codigosObligatorios;
		
		lcs_codigosObligatorios = new LinkedList<String>();
		
		lcs_codigosObligatorios.add(ID_CUENTA_CUPO);
		lcs_codigosObligatorios.add(FECHA_DILIGENCIAMIENTO);
		lcs_codigosObligatorios.add(MOTIVO_CANCELACION);
		
		return lcs_codigosObligatorios;
	}
	
	/**
	 * Obtiene los campos qeu son obligatorios en la creación de una cuenta cupo
	 * 
	 * @param ab_todos true para indicar que se deben obtener todos los campos, false para traer solo los obligatorios
	 * @return Colección resultante
	 */
	default Collection<String> obtenerConstantesCreacion(boolean ab_todos)
	{
		Collection<String> lcs_codigosObligatorios;
		
		lcs_codigosObligatorios = new LinkedList<String>();
		
		lcs_codigosObligatorios.add(RAZON_SOCIAL);
		lcs_codigosObligatorios.add(FECHA_DILIGENCIAMIENTO);
		lcs_codigosObligatorios.add(EMPRESA_TIPO_DOCUMENTO);
		lcs_codigosObligatorios.add(EMPRESA_NUMERO_DOCUMENTO);
		lcs_codigosObligatorios.add(EMPRESA_DIGITO_VERIFICACION_NIT);
		lcs_codigosObligatorios.add(REPRESENTANTE_LEGAL_TIPO_DOCUMENTO);
		lcs_codigosObligatorios.add(REPRESENTANTE_LEGAL_NUMERO_DOCUMENTO);
		lcs_codigosObligatorios.add(REPRESENTANTE_LEGAL_PRIMER_NOMBRE);
		lcs_codigosObligatorios.add(REPRESENTANTE_LEGAL_PRIMER_APELLIDO);
		lcs_codigosObligatorios.add(EMPRESA_DIRECCION);
		lcs_codigosObligatorios.add(REPRESENTANTE_LEGAL_CELULAR);
		lcs_codigosObligatorios.add(REPRESENTANTE_LEGAL_CORREO_ELECTRONICO);
		lcs_codigosObligatorios.add(DESCRIPCION_ACTIVIDAD_ECONOMICA);
		lcs_codigosObligatorios.add(TIPO_EMPRESA);
		lcs_codigosObligatorios.add(MONTO_SOLICITADO);
		lcs_codigosObligatorios.add(DESCRIPCION_FRECUENCIA_RECARGAS);
		lcs_codigosObligatorios.add(DESCRIPCION_NECESIDAD_DE_CUENTA_CUPO);
		lcs_codigosObligatorios.add(DESCRIPCION_ORIGEN_RECURSOS);
		
		if(ab_todos)
		{
			lcs_codigosObligatorios.add(REPRESENTANTE_LEGAL_SEGUNDO_NOMBRE);
			lcs_codigosObligatorios.add(REPRESENTANTE_LEGAL_SEGUNDO_APELLIDO);
			lcs_codigosObligatorios.add(REPRESENTANTE_LEGAL_OCUPACION_PROFESION);
		}
		
		return lcs_codigosObligatorios;
	}
	
	/**
	 * Obtiene los campos qeu son obligatorios en la modificación de una cuenta cupo.
	 *
	 * @return Colección resultante
	 */
	default Collection<String> obtenerConstantesModificacion()
	{
		Collection<String> lcs_codigosObligatorios;
		
		lcs_codigosObligatorios = new LinkedList<String>();
		
		lcs_codigosObligatorios.add(ID_CUENTA_CUPO);
		lcs_codigosObligatorios.add(FECHA_DILIGENCIAMIENTO);
		lcs_codigosObligatorios.add(NUEVO_VALOR_MAXIMO);
		lcs_codigosObligatorios.add(NUEVO_VALOR_MINIMO);
		lcs_codigosObligatorios.add(MOTIVO_SOLICITUD);
		
		return lcs_codigosObligatorios;
	}
}
