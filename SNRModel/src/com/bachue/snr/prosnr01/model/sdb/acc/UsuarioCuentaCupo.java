package com.bachue.snr.prosnr01.model.sdb.acc;

import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoUsuarioIUI;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase para la abstracción de la tabla SDB_ACC_USUARIO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 */
public class UsuarioCuentaCupo extends Auditoria implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 656407108374828699L;

	/**  Propiedad is_correoElectronico. */
	private String is_correoElectronico;

	/**  Propiedad is_estado. */
	private String is_estado;

	/**  Propiedad is_idCuentaCupo. */
	private String is_idCuentaCupo;

	/**  Propiedad is_idUsuario. */
	private String is_idUsuario;

	/**  Propiedad is_numeroDocumento. */
	private String is_numeroDocumento;

	/**  Propiedad is_primerApellido. */
	private String is_primerApellido;

	/**  Propiedad is_primerNombre. */
	private String is_primerNombre;

	/**  Propiedad is_razonSocial. */
	private String is_razonSocial;

	/**  Propiedad is_segundoApellido. */
	private String is_segundoApellido;

	/**  Propiedad is_segundoNombre. */
	private String is_segundoNombre;

	/**  Propiedad is_tipoDocumento. */
	private String is_tipoDocumento;

	/**  Propiedad is_tipoUsuario. */
	private String is_tipoUsuario;

	/**
	 * Constructor por defecto.
	 */
	public UsuarioCuentaCupo()
	{
	}

	/**
	 * Constructor para inicializar el objeto a partir de una insatncia de Persona.
	 *
	 * @param ap_persona Objeto contenedor de la información para inicializar el usuario
	 */
	public UsuarioCuentaCupo(Persona ap_persona)
	{
		if(ap_persona != null)
		{
			is_tipoDocumento       = ap_persona.getIdDocumentoTipo();
			is_numeroDocumento     = ap_persona.getNumeroDocumento();
			is_primerNombre        = ap_persona.getPrimerNombre();
			is_segundoNombre       = ap_persona.getSegundoNombre();
			is_primerApellido      = ap_persona.getPrimerApellido();
			is_segundoApellido     = ap_persona.getSegundoApellido();
			is_razonSocial         = ap_persona.getRazonSocial();
		}
	}

	/**
	 * Constructor que recibe un tipo y número de documento específicos.
	 *
	 * @param as_tipoDocumento Objeto contenedor del tipo de documento
	 * @param as_numeroDocumento Objeto contenedor del número de documento
	 */
	public UsuarioCuentaCupo(String as_tipoDocumento, String as_numeroDocumento)
	{
		is_tipoDocumento       = as_tipoDocumento;
		is_numeroDocumento     = as_numeroDocumento;
	}

	/**
	 * Construye una instancia a partir de un TipoUsuarioIUI.
	 *
	 * @param atuiui_usuario Objeto contenedor de los datos para instanciar el objeto
	 */
	public UsuarioCuentaCupo(TipoUsuarioIUI atuiui_usuario)
	{
		if(atuiui_usuario != null)
		{
			is_correoElectronico     = StringUtils.getStringUpperCase(
				    atuiui_usuario.getCorreoElectronicoCorporativoUsuario()
				);
			is_numeroDocumento       = atuiui_usuario.getNumDocUsuario();
			is_primerApellido        = atuiui_usuario.getPrimerApellidoUsuario();
			is_primerNombre          = atuiui_usuario.getPrimerNombreUsuario();
			is_segundoApellido       = atuiui_usuario.getSegundoApellidoUsuario();
			is_segundoNombre         = atuiui_usuario.getSegundoNombreUsuario();
			is_tipoDocumento         = atuiui_usuario.getTipoDocUsuario();
		}
	}

	/**
	 * Retorna el valor de is_idUsuario.
	 *
	 * @return el valor de is_idUsuario
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de is_idUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad is_idUsuario
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna el valor de is_idCuentaCupo.
	 *
	 * @return el valor de is_idCuentaCupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Modifica el valor de is_idCuentaCupo.
	 *
	 * @param as_s asigna el valor a la propiedad is_idCuentaCupo
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Retorna el valor de is_tipoDocumento.
	 *
	 * @return el valor de is_tipoDocumento
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}

	/**
	 * Modifica el valor de is_tipoDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad is_tipoDocumento
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	 * Retorna el valor de is_numeroDocumento.
	 *
	 * @return el valor de is_numeroDocumento
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
	}

	/**
	 * Modifica el valor de is_numeroDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad is_numeroDocumento
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = as_s;
	}

	/**
	 * Retorna el valor de is_primerNombre.
	 *
	 * @return el valor de is_primerNombre
	 */
	public String getPrimerNombre()
	{
		return is_primerNombre;
	}

	/**
	 * Modifica el valor de is_primerNombre.
	 *
	 * @param as_s asigna el valor a la propiedad is_primerNombre
	 */
	public void setPrimerNombre(String as_s)
	{
		is_primerNombre = as_s;
	}

	/**
	 * Retorna el valor de is_segundoNombre.
	 *
	 * @return el valor de is_segundoNombre
	 */
	public String getSegundoNombre()
	{
		return is_segundoNombre;
	}

	/**
	 * Modifica el valor de is_segundoNombre.
	 *
	 * @param as_s asigna el valor a la propiedad is_segundoNombre
	 */
	public void setSegundoNombre(String as_s)
	{
		is_segundoNombre = as_s;
	}

	/**
	 * Retorna el valor de is_primerApellido.
	 *
	 * @return el valor de is_primerApellido
	 */
	public String getPrimerApellido()
	{
		return is_primerApellido;
	}

	/**
	 * Modifica el valor de is_primerApellido.
	 *
	 * @param as_s asigna el valor a la propiedad is_primerApellido
	 */
	public void setPrimerApellido(String as_s)
	{
		is_primerApellido = as_s;
	}

	/**
	 * Retorna el valor de is_segundoApellido.
	 *
	 * @return el valor de is_segundoApellido
	 */
	public String getSegundoApellido()
	{
		return is_segundoApellido;
	}

	/**
	 * Modifica el valor de is_segundoApellido.
	 *
	 * @param as_s asigna el valor a la propiedad is_segundoApellido
	 */
	public void setSegundoApellido(String as_s)
	{
		is_segundoApellido = as_s;
	}

	/**
	 * Retorna el valor de is_razonSocial.
	 *
	 * @return el valor de is_razonSocial
	 */
	public String getRazonSocial()
	{
		return is_razonSocial;
	}

	/**
	 * Modifica el valor de is_razonSocial.
	 *
	 * @param as_s asigna el valor a la propiedad is_razonSocial
	 */
	public void setRazonSocial(String as_s)
	{
		is_razonSocial = as_s;
	}

	/**
	 * Retorna el valor de is_correoElectronico.
	 *
	 * @return el valor de is_correoElectronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de is_correoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad is_correoElectronico
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna el valor de is_estado.
	 *
	 * @return el valor de is_estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de is_estado.
	 *
	 * @param as_s asigna el valor a la propiedad is_estado
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Retorna el valor de is_tipoUsuario.
	 *
	 * @return el valor de is_tipoUsuario
	 */
	public String getTipoUsuario()
	{
		return is_tipoUsuario;
	}

	/**
	 * Modifica el valor de is_tipoUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad is_tipoUsuario
	 */
	public void setTipoUsuario(String as_s)
	{
		is_tipoUsuario = as_s;
	}
}
