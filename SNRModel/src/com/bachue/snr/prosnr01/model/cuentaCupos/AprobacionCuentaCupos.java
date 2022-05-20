package com.bachue.snr.prosnr01.model.cuentaCupos;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Map;


/**
 * Clase para el manejo de presentación del proceso de aprobación de cuenta cupos.
 *
 * @author Manuel Blanco
 */
public class AprobacionCuentaCupos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6105364948591917102L;

	/** Propiedad ibd valor maximo. */
	private BigDecimal ibd_valorMaximo;

	/** Propiedad ibd valor maximo modificación. */
	private BigDecimal ibd_valorMaximoModificacion;

	/** Propiedad ibd valor minimo. */
	private BigDecimal ibd_valorMinimo;

	/** Propiedad ibd valor minimo modificación. */
	private BigDecimal ibd_valorMinimoModificacion;

	/** Propiedad id fecha solicitud. */
	private Date id_fechaSolicitud;

	/** Propiedad id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad imsba archivos cargados. */
	private Map<String, byte[]> imsba_archivosCargados;

	/** Propiedad is ciudad. */
	private String is_ciudad;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id cuenta cupo. */
	private String is_idCuentaCupo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nit. */
	private String is_nit;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is razon social. */
	private String is_razonSocial;

	/** Propiedad is representante legal. */
	private String is_representanteLegal;

	/** Propiedad is telefono. */
	private String is_telefono;

	/** Propiedad is tipo solicitud. */
	private String is_tipoSolicitud;

	/** Propiedad ib aprobar. */
	private boolean ib_aprobar;

	/**
	 * Obtiene el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Cambia el valor de nir.
	 *
	 * @param as_s el nuevo valor de nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Obtiene el valor de razon social.
	 *
	 * @return el valor de razon social
	 */
	public String getRazonSocial()
	{
		return is_razonSocial;
	}

	/**
	 * Cambia el valor de razon social.
	 *
	 * @param as_s el nuevo valor de razon social
	 */
	public void setRazonSocial(String as_s)
	{
		is_razonSocial = as_s;
	}

	/**
	 * Obtiene el valor de nit.
	 *
	 * @return el valor de nit
	 */
	public String getNit()
	{
		return is_nit;
	}

	/**
	 * Cambia el valor de nit.
	 *
	 * @param as_s el nuevo valor de nit
	 */
	public void setNit(String as_s)
	{
		is_nit = as_s;
	}

	/**
	 * Obtiene el valor de representante legal.
	 *
	 * @return el valor de representante legal
	 */
	public String getRepresentanteLegal()
	{
		return is_representanteLegal;
	}

	/**
	 * Cambia el valor de representante legal.
	 *
	 * @param as_s el nuevo valor de representante legal
	 */
	public void setRepresentanteLegal(String as_s)
	{
		is_representanteLegal = as_s;
	}

	/**
	 * Obtiene el valor de ciudad.
	 *
	 * @return el valor de ciudad
	 */
	public String getCiudad()
	{
		return is_ciudad;
	}

	/**
	 * Cambia el valor de ciudad.
	 *
	 * @param as_s el nuevo valor de ciudad
	 */
	public void setCiudad(String as_s)
	{
		is_ciudad = as_s;
	}

	/**
	 * Obtiene el valor de telefono.
	 *
	 * @return el valor de telefono
	 */
	public String getTelefono()
	{
		return is_telefono;
	}

	/**
	 * Cambia el valor de telefono.
	 *
	 * @param as_s el nuevo valor de telefono
	 */
	public void setTelefono(String as_s)
	{
		is_telefono = as_s;
	}

	/**
	 * Obtiene el valor de correo electronico.
	 *
	 * @return el valor de correo electronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Cambia el valor de correo electronico.
	 *
	 * @param as_s el nuevo valor de correo electronico
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Obtiene el valor de fecha solicitud.
	 *
	 * @return el valor de fecha solicitud
	 */
	public Date getFechaSolicitud()
	{
		return id_fechaSolicitud;
	}

	/**
	 * Cambia el valor de fecha solicitud.
	 *
	 * @param ad_d el nuevo valor de fecha solicitud
	 */
	public void setFechaSolicitud(Date ad_d)
	{
		id_fechaSolicitud = ad_d;
	}

	/**
	 * Obtiene el valor de tipo solicitud.
	 *
	 * @return el valor de tipo solicitud
	 */
	public String getTipoSolicitud()
	{
		return is_tipoSolicitud;
	}

	/**
	 * Cambia el valor de tipo solicitud.
	 *
	 * @param as_s el nuevo valor de tipo solicitud
	 */
	public void setTipoSolicitud(String as_s)
	{
		is_tipoSolicitud = as_s;
	}

	/**
	 * Obtiene el valor de estado.
	 *
	 * @return el valor de estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Cambia el valor de estado.
	 *
	 * @param as_s el nuevo valor de estado
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Obtiene el valor de valor minimo.
	 *
	 * @return el valor de valor minimo
	 */
	public BigDecimal getValorMinimo()
	{
		return ibd_valorMinimo;
	}

	/**
	 * Cambia el valor de valor minimo.
	 *
	 * @param ab_d el nuevo valor de valor minimo
	 */
	public void setValorMinimo(BigDecimal ab_d)
	{
		ibd_valorMinimo = ab_d;
	}

	/**
	 * Obtiene el valor de valor maximo.
	 *
	 * @return el valor de valor maximo
	 */
	public BigDecimal getValorMaximo()
	{
		return ibd_valorMaximo;
	}

	/**
	 * Cambia el valor de valor maximo.
	 *
	 * @param ab_d el nuevo valor de valor maximo
	 */
	public void setValorMaximo(BigDecimal ab_d)
	{
		ibd_valorMaximo = ab_d;
	}

	/**
	 * Obtiene el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Cambia el valor de id solicitud.
	 *
	 * @param as_s el nuevo valor de id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Obtiene el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Cambia el valor de observaciones.
	 *
	 * @param as_s el nuevo valor de observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Obtiene el valor de id cuenta cupo.
	 *
	 * @return el valor de id cuenta cupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Cambia el valor de id cuenta cupo.
	 *
	 * @param as_s el nuevo valor de id cuenta cupo
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Obtiene el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Cambia el valor de id turno historia.
	 *
	 * @param al_l el nuevo valor de id turno historia
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Obtiene el valor de aprobar.
	 *
	 * @return el valor de aprobar
	 */
	public boolean isAprobar()
	{
		return ib_aprobar;
	}

	/**
	 * Cambia el valor de aprobar.
	 *
	 * @param ab_b el nuevo valor de aprobar
	 */
	public void setAprobar(boolean ab_b)
	{
		ib_aprobar = ab_b;
	}

	/**
	 * Obtiene el valor de archivos cargados.
	 *
	 * @return el valor de archivos cargados
	 */
	public Map<String, byte[]> getArchivosCargados()
	{
		return imsba_archivosCargados;
	}

	/**
	 * Cambia el valor de archivos cargados.
	 *
	 * @param amsba_msba el valor de amsba msba
	 */
	public void setArchivosCargados(Map<String, byte[]> amsba_msba)
	{
		imsba_archivosCargados = amsba_msba;
	}

	/**
	 * Obtiene el valor de ibd_valorMaximoModificacion.
	 *
	 * @return el valor de ibd_valorMaximoModificacion
	 */
	public BigDecimal getValorMaximoModificacion()
	{
		return ibd_valorMaximoModificacion;
	}

	/**
	 * Cambia el valor de ibd_valorMaximoModificacion.
	 *
	 * @param abd_bd el valor de abd_bd
	 */
	public void setValorMaximoModificacion(BigDecimal abd_bd)
	{
		ibd_valorMaximoModificacion = abd_bd;
	}

	/**
	 * Obtiene el valor de ibd_valorMinimoModificacion.
	 *
	 * @return el valor de ibd_valorMinimoModificacion
	 */
	public BigDecimal getValorMinimoModificacion()
	{
		return ibd_valorMinimoModificacion;
	}

	/**
	 * Cambia el valor de ibd_valorMinimoModificacion.
	 *
	 * @param abd_bd el valor de abd_bd
	 */
	public void setValorMinimoModificacion(BigDecimal abd_bd)
	{
		ibd_valorMinimoModificacion = abd_bd;
	}
}
