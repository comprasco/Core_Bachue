package com.bachue.snr.prosnr01.model.devolucion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Class que contiene los actos a mostrar en pantalla para devoluciones de dinero.
 *
 * @author ccalderon
 */
public class ActoDevolucionDinero extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2320013908208149749L;

	/** Propiedad is especificacion acto. */
	private String is_especificacionActo;

	/** Propiedad is id acto devolucion dinero. */
	private String is_idActoDevolucionDinero;

	/** Propiedad is id devolución dinero. */
	private String is_idDevolucionDinero;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud inicial. */
	private String is_idSolicitudInicial;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is tipo tarifa registral. */
	private String is_tipoTarifaRegistral;

	/** Propiedad is version acto. */
	private String is_versionActo;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad il cantidad actos. */
	private long il_cantidadActos;

	/** Propiedad il cuantia avaluo. */
	private long il_cuantiaActo;

	/** Propiedad il valor avaluo. */
	private long il_valorAvaluo;

	/** Propiedad il valor conservacion documental. */
	private long il_valorConservacionDocumental;

	/** Propiedad il valor derechos. */
	private long il_valorDerechos;

	/** Propiedad il valor total liquidado. */
	private long il_valorTotalLiquidado;

	/**
	 * Gets the id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Sets the id turno.
	 *
	 * @param as_s the new id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Gets the nir.
	 *
	 * @return Retorna el valor de la propiedad nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Sets the nir.
	 *
	 * @param as_s the new nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Gets the especificacion acto.
	 *
	 * @return Retorna el valor de la propiedad especificacionActo
	 */
	public String getEspecificacionActo()
	{
		return is_especificacionActo;
	}

	/**
	 * Sets the especificacion acto.
	 *
	 * @param as_s the new especificacion acto
	 */
	public void setEspecificacionActo(String as_s)
	{
		is_especificacionActo = as_s;
	}

	/**
	 * Gets the valor total liquidado.
	 *
	 * @return Retorna el valor de la propiedad valorTotalLiquidado
	 */
	public long getValorTotalLiquidado()
	{
		return il_valorTotalLiquidado;
	}

	/**
	 * Sets the valor total liquidado.
	 *
	 * @param al_l the new valor total liquidado
	 */
	public void setValorTotalLiquidado(long al_l)
	{
		il_valorTotalLiquidado = al_l;
	}

	/**
	 * Gets the cuantia acto.
	 *
	 * @return Retorna el valor de la propiedad cuantiaActo
	 */
	public long getCuantiaActo()
	{
		return il_cuantiaActo;
	}

	/**
	 * Sets the cuantia acto.
	 *
	 * @param al_l the new cuantia acto
	 */
	public void setCuantiaActo(long al_l)
	{
		il_cuantiaActo = al_l;
	}

	/**
	 * Gets the valor avaluo.
	 *
	 * @return Retorna el valor de la propiedad valorAvaluo
	 */
	public long getValorAvaluo()
	{
		return il_valorAvaluo;
	}

	/**
	 * Sets the valor avaluo.
	 *
	 * @param al_l the new valor avaluo
	 */
	public void setValorAvaluo(long al_l)
	{
		il_valorAvaluo = al_l;
	}

	/**
	 * Gets the cantidad actos.
	 *
	 * @return Retorna el valor de la propiedad cantidadActos
	 */
	public long getCantidadActos()
	{
		return il_cantidadActos;
	}

	/**
	 * Sets the cantidad actos.
	 *
	 * @param al_l the new cantidad actos
	 */
	public void setCantidadActos(long al_l)
	{
		il_cantidadActos = al_l;
	}

	/**
	 * Gets the valor derechos.
	 *
	 * @return Retorna el valor de la propiedad valorDerechos
	 */
	public long getValorDerechos()
	{
		return il_valorDerechos;
	}

	/**
	 * Sets the valor derechos.
	 *
	 * @param al_l the new valor derechos
	 */
	public void setValorDerechos(long al_l)
	{
		il_valorDerechos = al_l;
	}

	/**
	 * Gets the valor conservacion documental.
	 *
	 * @return Retorna el valor de la propiedad valorConservacionDocumental
	 */
	public long getValorConservacionDocumental()
	{
		return il_valorConservacionDocumental;
	}

	/**
	 * Sets the valor conservacion documental.
	 *
	 * @param al_l the new valor conservacion documental
	 */
	public void setValorConservacionDocumental(long al_l)
	{
		il_valorConservacionDocumental = al_l;
	}

	/**
	 * Gets the tipo tarifa registral.
	 *
	 * @return Retorna el valor de la propiedad tipoTarifaRegistral
	 */
	public String getTipoTarifaRegistral()
	{
		return is_tipoTarifaRegistral;
	}

	/**
	 * Sets the tipo tarifa registral.
	 *
	 * @param as_s the new tipo tarifa registral
	 */
	public void setTipoTarifaRegistral(String as_s)
	{
		is_tipoTarifaRegistral = as_s;
	}

	/**
	 * Checks if is seleccionado.
	 *
	 * @return Retorna el valor de la propiedad seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Sets the seleccionado.
	 *
	 * @param ab_b the new seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Gets the id devolucion dinero.
	 *
	 * @return Retorna el valor de la propiedad idDevolucionDinero
	 */
	public String getIdDevolucionDinero()
	{
		return is_idDevolucionDinero;
	}

	/**
	 * Sets the id devolucion dinero.
	 *
	 * @param as_s the new id devolucion dinero
	 */
	public void setIdDevolucionDinero(String as_s)
	{
		is_idDevolucionDinero = as_s;
	}

	/**
	 * Gets the id solicitud inicial.
	 *
	 * @return Retorna el valor de la propiedad idSolicitudInicial
	 */
	public String getIdSolicitudInicial()
	{
		return is_idSolicitudInicial;
	}

	/**
	 * Sets the id solicitud inicial.
	 *
	 * @param as_s the new id solicitud inicial
	 */
	public void setIdSolicitudInicial(String as_s)
	{
		is_idSolicitudInicial = as_s;
	}

	/**
	 * Gets the id solicitud.
	 *
	 * @return Retorna el valor de la propiedad idSolicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Sets the id solicitud.
	 *
	 * @param as_s the new id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Gets the id tipo acto.
	 *
	 * @return Retorna el valor de la propiedad idTipoActo
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Sets the id tipo acto.
	 *
	 * @param as_s the new id tipo acto
	 */
	public void setIdTipoActo(String as_s)
	{
		is_idTipoActo = as_s;
	}

	/**
	 * Gets the id acto devolucion dinero.
	 *
	 * @return Retorna el valor de la propiedad idActoDevolucionDinero
	 */
	public String getIdActoDevolucionDinero()
	{
		return is_idActoDevolucionDinero;
	}

	/**
	 * Sets the id acto devolucion dinero.
	 *
	 * @param as_s the new id acto devolucion dinero
	 */
	public void setIdActoDevolucionDinero(String as_s)
	{
		is_idActoDevolucionDinero = as_s;
	}

	/**
	 * Gets the version acto.
	 *
	 * @return Retorna el valor de la propiedad versionActo
	 */
	public String getVersionActo()
	{
		return is_versionActo;
	}

	/**
	 * Sets the version acto.
	 *
	 * @param as_s the new version acto
	 */
	public void setVersionActo(String as_s)
	{
		is_versionActo = as_s;
	}
}
