package com.bachue.snr.prosnr01.model.reimpresion;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase que contiene todos las propiedades Reimpresion Recibos.
 *
 * @author ssanchez
 */
public class ReimpresionRecibos extends DocumentosSalida implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8697040222919724514L;

	/** Propiedad id fecha liquidacion. */
	private Date id_fechaLiquidacion;

	/** Propiedad is estado impresion. */
	private String is_estadoImpresion;

	/** Propiedad is id causal reimpresion. */
	private String is_idCausalReimpresion;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is tipo documental. */
	private String is_idTipoDocumental;

	/** Propiedad is tipo documental nombre. */
	private String is_idTipoDocumentalNombre;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre proceso. */
	private String is_nombreProceso;

	/** Propiedad is numero referencia. */
	private String is_numeroReferencia;

	/** Propiedad il id documento salida reimpresion recibos. */
	private long il_idDocumentoSalidaReimpresionRecibos;
	
	/** Propiedad ib recibo boton. */
	private boolean ib_reciboBoton;

	/**
	 * Instancia un nuevo objeto reimpresion recibos.
	 */
	public ReimpresionRecibos()
	{
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Retorna el valor de fecha liquidacion.
	 *
	 * @return el valor de fecha liquidacion
	 */
	public Date getFechaLiquidacion()
	{
		return id_fechaLiquidacion;
	}

	/**
	 * Modifica el valor de fecha liquidacion.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha liquidacion
	 */
	public void setFechaLiquidacion(Date ad_d)
	{
		id_fechaLiquidacion = ad_d;
	}

	/**
	 * Retorna el valor de numero referencia.
	 *
	 * @return el valor de numero referencia
	 */
	public String getNumeroReferencia()
	{
		return is_numeroReferencia;
	}

	/**
	 * Modifica el valor de numero referencia.
	 *
	 * @param as_s asigna el valor a la propiedad numero referencia
	 */
	public void setNumeroReferencia(String as_s)
	{
		is_numeroReferencia = as_s;
	}

	/**
	 * Retorna el valor de tipo.
	 *
	 * @return el valor de tipo
	 */
	public String getTipo()
	{
		return is_idTipoDocumental;
	}

	/**
	 * Modifica el valor de tipo.
	 *
	 * @param as_s asigna el valor a la propiedad tipo
	 */
	public void setTipo(String as_s)
	{
		is_idTipoDocumental = as_s;
	}

	/**
	 * Retorna el valor de tipo documental nombre.
	 *
	 * @return el valor de tipo documental nombre
	 */
	public String getTipoDocumentalNombre()
	{
		return is_idTipoDocumentalNombre;
	}

	/**
	 * Modifica el valor de tipo documental nombre.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documental nombre
	 */
	public void setTipoDocumentalNombre(String as_s)
	{
		is_idTipoDocumentalNombre = as_s;
	}

	/**
	 * Retorna el valor de id documento salida.
	 *
	 * @return el valor de id documento salida
	 */
	public long getIdDocumentoSalidaReimpresion()
	{
		return il_idDocumentoSalidaReimpresionRecibos;
	}

	/**
	 * Modifica el valor de id documento salida.
	 *
	 * @param al_l asigna el valor a la propiedad id documento salida
	 */
	public void setIdDocumentoSalidaReimpresion(long al_l)
	{
		il_idDocumentoSalidaReimpresionRecibos = al_l;
	}

	/**
	 * Retorna el valor de proceso.
	 *
	 * @return el valor de proceso
	 */
	public String getProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de proceso.
	 *
	 * @param as_s asigna el valor a la propiedad proceso
	 */
	public void setProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Modifica el valor de NombreProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Retorna Objeto o variable de valor estado impresion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoImpresion()
	{
		return is_estadoImpresion;
	}

	/**
	 * Modifica el valor de EstadoImpresion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoImpresion(String as_s)
	{
		is_estadoImpresion = as_s;
	}
	
	/**
	 * Valida la propiedad recibo boton.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isReciboBoton()
	{
		return ib_reciboBoton;
	}

	/**
	 * Modifica el valor de ReciboBoton.
	 *
	 * @param ab_b de ab b
	 */
	public void setReciboBoton(boolean ab_b)
	{
		ib_reciboBoton = ab_b;
	}

	/**
	 * Modifica el valor de IdCausalReimpresion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCausalReimpresion(String as_s)
	{
		is_idCausalReimpresion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id causal reimpresion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCausalReimpresion()
	{
		return is_idCausalReimpresion;
	}
}
