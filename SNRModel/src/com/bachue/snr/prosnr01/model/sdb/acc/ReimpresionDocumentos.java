package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_REIMPRESION_DOCUMENTOS.
 *
 * @author ssanchez
 */
public class ReimpresionDocumentos extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3356103145956614106L;

	/** Propiedad is doc name. */
	private String is_docName;

	/** Propiedad is id ecm. */
	private String is_idECM;

	/** Propiedad is solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id tipo documental. */
	private String is_idTipoDocumental;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is impresion exitosa. */
	private String is_impresionExistosa;

	/** Propiedad il id documento salida. */
	private long il_idDocumentoSalida;

	/**
	 * Constructor por defecto.
	 */
	public ReimpresionDocumentos()
	{
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
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
	 * Modifica el valor de IdTipoDocumental.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDocumental(String as_s)
	{
		is_idTipoDocumental = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDocumental()
	{
		return is_idTipoDocumental;
	}

	/**
	 * Retorna Objeto o variable de valor id documento salida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdDocumentoSalida()
	{
		return il_idDocumentoSalida;
	}

	/**
	 * Modifica el valor de IdDocumentoSalida.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdDocumentoSalida(long al_l)
	{
		il_idDocumentoSalida = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id ECM.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdECM()
	{
		return is_idECM;
	}

	/**
	 * Modifica el valor de IdECM.
	 *
	 * @param as_s de as s
	 */
	public void setIdECM(String as_s)
	{
		is_idECM = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor doc name.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDocName()
	{
		return is_docName;
	}

	/**
	 * Modifica el valor de DocName.
	 *
	 * @param as_s de as s
	 */
	public void setDocName(String as_s)
	{
		is_docName = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor impresion exitosa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getImpresionExitosa()
	{
		return is_impresionExistosa;
	}

	/**
	 * Modifica el valor de ImpresionExitosa.
	 *
	 * @param as_s de as s
	 */
	public void setImpresionExitosa(String as_s)
	{
		is_impresionExistosa = as_s;
	}
}
