package com.bachue.snr.prosnr01.model.registro;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase que contiene todos las propiedades GravamenPendiente.
 *
 * @author Julian Vaca
 */
public class GravamenPendiente implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1437653296306118092L;

	/** Propiedad id fecha radicacion. */
	private Date id_fechaRadicacion;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad id fecha documento. */
	private String id_fechaDocumento;

	/** Propiedad is codigo acto. */
	private String is_codigoActo;

	/** Propiedad is descripcion acto. */
	private String is_descripcionActo;

	/** Propiedad is estado anotacion. */
	private String is_estadoAnotacion;

	/** Propiedad is id circulo predio. */
	private String is_idCirculoPredio;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is id estado anotacion. */
	private String is_idEstadoAnotacion;

	/** Propiedad is id matricula predio. */
	private String is_idMatriculaPredio;

	/** Propiedad is id tipo anotacion. */
	private String is_idTipoAnotacion;

	/** Propiedad is nombre documento. */
	private String is_nombreDocumento;

	/** Propiedad is numero. */
	private String is_numero;

	/** Propiedad is oficina origen. */
	private String is_oficinaOrigen;

	/** Propiedad is radicacion. */
	private String is_radicacion;

	/** Propiedad is tipo oficina. */
	private String is_tipoOficina;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad il id anotacion. */
	private long il_idAnotacion;

	/**
	 * Modifica el valor de codigo acto.
	 *
	 * @param as_s asigna el valor a la propiedad codigo acto
	 */
	public void setCodigoActo(String as_s)
	{
		is_codigoActo = as_s;
	}

	/**
	 * Retorna el valor de codigo acto.
	 *
	 * @return el valor de codigo acto
	 */
	public String getCodigoActo()
	{
		return is_codigoActo;
	}

	/**
	 * Modifica el valor de descripcion acto.
	 *
	 * @param as_s asigna el valor a la propiedad descripcion acto
	 */
	public void setDescripcionActo(String as_s)
	{
		is_descripcionActo = as_s;
	}

	/**
	 * Retorna el valor de descripcion acto.
	 *
	 * @return el valor de descripcion acto
	 */
	public String getDescripcionActo()
	{
		return is_descripcionActo;
	}

	/**
	 * Modifica el valor de estado anotacion.
	 *
	 * @param as_s asigna el valor a la propiedad estado anotacion
	 */
	public void setEstadoAnotacion(String as_s)
	{
		is_estadoAnotacion = as_s;
	}

	/**
	 * Retorna el valor de estado anotacion.
	 *
	 * @return el valor de estado anotacion
	 */
	public String getEstadoAnotacion()
	{
		return is_estadoAnotacion;
	}

	/**
	 * Modifica el valor de fecha documento.
	 *
	 * @param as_s asigna el valor a la propiedad fecha documento
	 */
	public void setFechaDocumento(String as_s)
	{
		id_fechaDocumento = as_s;
	}

	/**
	 * Retorna el valor de fecha documento.
	 *
	 * @return el valor de fecha documento
	 */
	public String getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * Modifica el valor de fecha radicacion.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha radicacion
	 */
	public void setFechaRadicacion(Date ad_d)
	{
		id_fechaRadicacion = ad_d;
	}

	/**
	 * Retorna el valor de fecha radicacion.
	 *
	 * @return el valor de fecha radicacion
	 */
	public Date getFechaRadicacion()
	{
		return id_fechaRadicacion;
	}

	/**
	 * Modifica el valor de fecha registro.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha registro
	 */
	public void setFechaRegistro(Date ad_d)
	{
		id_fechaRegistro = ad_d;
	}

	/**
	 * Retorna el valor de fecha registro.
	 *
	 * @return el valor de fecha registro
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
	}

	/**
	 * Modifica el valor de id anotacion.
	 *
	 * @param al_l asigna el valor a la propiedad id anotacion
	 */
	public void setIdAnotacion(long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna el valor de id anotacion.
	 *
	 * @return el valor de id anotacion
	 */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de id circulo predio.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo predio
	 */
	public void setIdCirculoPredio(String as_s)
	{
		is_idCirculoPredio = as_s;
	}

	/**
	 * Retorna el valor de id circulo predio.
	 *
	 * @return el valor de id circulo predio
	 */
	public String getIdCirculoPredio()
	{
		return is_idCirculoPredio;
	}

	/**
	 * Modifica el valor de id documento.
	 *
	 * @param as_s asigna el valor a la propiedad id documento
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna el valor de id documento.
	 *
	 * @return el valor de id documento
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de id estado anotacion.
	 *
	 * @param as_s asigna el valor a la propiedad id estado anotacion
	 */
	public void setIdEstadoAnotacion(String as_s)
	{
		is_idEstadoAnotacion = as_s;
	}

	/**
	 * Retorna el valor de id estado anotacion.
	 *
	 * @return el valor de id estado anotacion
	 */
	public String getIdEstadoAnotacion()
	{
		return is_idEstadoAnotacion;
	}

	/**
	 * Modifica el valor de id matricula predio.
	 *
	 * @param as_s asigna el valor a la propiedad id matricula predio
	 */
	public void setIdMatriculaPredio(String as_s)
	{
		is_idMatriculaPredio = as_s;
	}

	/**
	 * Retorna el valor de id matricula predio.
	 *
	 * @return el valor de id matricula predio
	 */
	public String getIdMatriculaPredio()
	{
		return is_idMatriculaPredio;
	}

	/**
	 * Modifica el valor de id tipo anotacion.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo anotacion
	 */
	public void setIdTipoAnotacion(String as_s)
	{
		is_idTipoAnotacion = as_s;
	}

	/**
	 * Retorna el valor de id tipo anotacion.
	 *
	 * @return el valor de id tipo anotacion
	 */
	public String getIdTipoAnotacion()
	{
		return is_idTipoAnotacion;
	}

	/**
	 * Modifica el valor de nombre documento.
	 *
	 * @param as_s asigna el valor a la propiedad nombre documento
	 */
	public void setNombreDocumento(String as_s)
	{
		is_nombreDocumento = as_s;
	}

	/**
	 * Retorna el valor de nombre documento.
	 *
	 * @return el valor de nombre documento
	 */
	public String getNombreDocumento()
	{
		return is_nombreDocumento;
	}

	/**
	 * Modifica el valor de numero.
	 *
	 * @param as_s asigna el valor a la propiedad numero
	 */
	public void setNumero(String as_s)
	{
		is_numero = as_s;
	}

	/**
	 * Retorna el valor de numero.
	 *
	 * @return el valor de numero
	 */
	public String getNumero()
	{
		return is_numero;
	}

	/**
	 * Modifica el valor de oficina origen.
	 *
	 * @param as_s asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(String as_s)
	{
		is_oficinaOrigen = as_s;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public String getOficinaOrigen()
	{
		return is_oficinaOrigen;
	}

	/**
	 * Modifica el valor de radicacion.
	 *
	 * @param as_s asigna el valor a la propiedad radicacion
	 */
	public void setRadicacion(String as_s)
	{
		is_radicacion = as_s;
	}

	/**
	 * Retorna el valor de radicacion.
	 *
	 * @return el valor de radicacion
	 */
	public String getRadicacion()
	{
		return is_radicacion;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de tipo oficina.
	 *
	 * @param as_s asigna el valor a la propiedad tipo oficina
	 */
	public void setTipoOficina(String as_s)
	{
		is_tipoOficina = as_s;
	}

	/**
	 * Retorna el valor de tipo oficina.
	 *
	 * @return el valor de tipo oficina
	 */
	public String getTipoOficina()
	{
		return is_tipoOficina;
	}
}
