package com.bachue.snr.prosnr16.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Abstraccion de tabla SDB_ACC_MENSAJE.
 *
 * @author Sebastian Sanchez
 */
public class Mensaje extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3823047527044234120L;

	/** Propiedad id fecha envio. */
	private Date id_fechaEnvio;

	/** Propiedad is clasificacion. */
	private String is_clasificacion;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is direccion correspondencia. */
	private String is_direccionCorrespondencia;

	/** Propiedad is id canal. */
	private String is_idCanal;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id estado. */
	private String is_idEstado;

	/** Propiedad is id mensaje. */
	private String is_idMensaje;

	/** Propiedad is id origen. */
	private String is_idOrigen;

	/** Propiedad is id plantilla. */
	private String is_idPlantilla;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is identificadorEE. */
	private String is_identificadorEE;

	/** Propiedad is identificador mensaje. */
	private String is_identificadorMensaje;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is numero celular. */
	private String is_numeroCelular;

	/** Propiedad ii intento envio. */
	private int ii_intentoEnvio;

	/**
	 * Constructor por defecto.
	 */
	public Mensaje()
	{
	}

	/**
	 * Instancia un nuevo objeto mensaje.
	 *
	 * @param as_idMensaje de as id mensaje
	 */
	public Mensaje(String as_idMensaje)
	{
		is_idMensaje = as_idMensaje;
	}

	/**
	 * Modifica el valor de Clasificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setClasificacion(String as_s)
	{
		is_clasificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor clasificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getClasificacion()
	{
		return is_clasificacion;
	}

	/**
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de DireccionCorrespondencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDireccionCorrespondencia(String as_s)
	{
		is_direccionCorrespondencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion correspondencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDireccionCorrespondencia()
	{
		return is_direccionCorrespondencia;
	}

	/**
	 * Modifica el valor de FechaEnvio.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaEnvio(Date ad_d)
	{
		id_fechaEnvio = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha envio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaEnvio()
	{
		return id_fechaEnvio;
	}

	/**
	 * Modifica el valor de IdCanal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCanal(String as_s)
	{
		is_idCanal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id canal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCanal()
	{
		return is_idCanal;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdEstado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstado(String as_s)
	{
		is_idEstado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstado()
	{
		return is_idEstado;
	}

	/**
	 * Modifica el valor de IdMensaje.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMensaje(String as_s)
	{
		is_idMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id mensaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMensaje()
	{
		return is_idMensaje;
	}

	/**
	 * Modifica el valor de IdOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdOrigen(String as_s)
	{
		is_idOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdOrigen()
	{
		return is_idOrigen;
	}

	/**
	 * Modifica el valor de IdPlantilla.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPlantilla(String as_s)
	{
		is_idPlantilla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id plantilla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPlantilla()
	{
		return is_idPlantilla;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdentificadorEE.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdentificadorEE(String as_s)
	{
		is_identificadorEE = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor identificador EE.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdentificadorEE()
	{
		return is_identificadorEE;
	}

	/**
	 * @param Modifica el valor de la propiedad identificadorMensaje por identificadorMensaje
	 */
	public void setIdentificadorMensaje(String as_identificadorMensaje)
	{
		is_identificadorMensaje = as_identificadorMensaje;
	}

	/**
	 * @return Retorna el valor de la propiedad identificadorMensaje
	 */
	public String getIdentificadorMensaje()
	{
		return is_identificadorMensaje;
	}

	/**
	 * Modifica el valor de IntentoEnvio.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setIntentoEnvio(int ai_i)
	{
		ii_intentoEnvio = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor intento envio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getIntentoEnvio()
	{
		return ii_intentoEnvio;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NumeroCelular.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroCelular(String as_s)
	{
		is_numeroCelular = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero celular.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroCelular()
	{
		return is_numeroCelular;
	}
}
