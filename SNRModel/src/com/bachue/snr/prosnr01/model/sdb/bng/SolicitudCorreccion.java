package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades SolicitudCorreccion.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 2/04/2020
 */
public class SolicitudCorreccion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2176388076617526286L;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is texto. */
	private String is_texto;

	/** Propiedad il id alerta tierra. */
	private long il_idAlertaTierra;

	/**
	 * Instancia un nuevo objeto solicitud correccion.
	 */
	public SolicitudCorreccion()
	{
	}

	/**
	 * Retorna Objeto o variable de valor id alerta tierra.
	 *
	 * @return Retorna el valor de la propiedad idAlertaTierra
	 */
	public long getIdAlertaTierra()
	{
		return il_idAlertaTierra;
	}

	/**
	 * Modifica el valor de IdAlertaTierra.
	 *
	 * @param al_l de al l
	 */
	public void setIdAlertaTierra(long al_l)
	{
		il_idAlertaTierra = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return Retorna el valor de la propiedad estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s de as s
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor texto.
	 *
	 * @return Retorna el valor de la propiedad texto
	 */
	public String getTexto()
	{
		return is_texto;
	}

	/**
	 * Modifica el valor de Texto.
	 *
	 * @param as_s de as s
	 */
	public void setTexto(String as_s)
	{
		is_texto = as_s;
	}
}
