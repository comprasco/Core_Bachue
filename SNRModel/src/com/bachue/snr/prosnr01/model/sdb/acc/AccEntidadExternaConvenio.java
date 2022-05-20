package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Clase para la abstracción de la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO.
 *
 * @author Sebastian Sanchez
 */
public class AccEntidadExternaConvenio extends Auditoria implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = -3367757517652385400L;

	/**  Propiedad iczr zona registral. */
	private Collection<ZonaRegistral> iczr_zonaRegistral;

	/** Propiedad id fecha creacion convenio. */
	private Date id_fechaCreacionConvenio;

	/** Propiedad id fecha vencimiento. */
	private Date id_fechaVencimiento;

	/**  Propiedad is activo. */
	private String is_activo;

	/**  Propiedad is documento aprobador. */
	private String is_documentoAprobador;

	/**  Propiedad is id circulo. */
	private String is_idCirculo;

	/**  Propiedad is id entidad externa. */
	private String is_idEntidadExterna;

	/**  Propiedad is id tipo entidad. */
	private String is_idTipoEntidad;

	/**  Propiedad is id zona registral. */
	private String is_idZonaRegistral;

	/**  Propiedad is identificador documento aprobador. */
	private String is_identificadorDocumentoAprobador;

	/**  Propiedad is nombre convenio. */
	private String is_nombreConvenio;

	/**  Propiedad is nombre entidad. */
	private String is_nombreEntidad;

	/**  Propiedad is numero convenio. */
	private String is_numeroConvenio;
	
	/**  Propiedad is numero documento renovacion. */
	private String is_numeroDocumentoRenovacion;
	
	/**  Propiedad is usuario asignado. */
	private String is_usuarioAsignado;

	/**
	 * Constructor por defecto.
	 */
	public AccEntidadExternaConvenio()
	{
	}
	
	/**
	 * Constructor que recibe como parametro el id de la entidad externa y el id de la entidad convenio.
	 *
	 * @param as_idEntidadExterna id entidad externa
	 * @param as_idEntidadExternaConvenio id entidad externa convenio
	 */
	public AccEntidadExternaConvenio(String as_idEntidadExterna, String as_idEntidadExternaConvenio)
	{
		setIdEntidadExterna(as_idEntidadExterna);
		setNumeroConvenio(as_idEntidadExternaConvenio);
	}

	/**
	 * Modifica el valor de iczr_zonaRegistral.
	 *
	 * @param aczr_czr asigna el valor a la propiedad iczr_zonaRegistral
	 */
	public void setZonaRegistral(Collection<ZonaRegistral> aczr_czr)
	{
		iczr_zonaRegistral = aczr_czr;
	}

	/**
	 * Retorna el valor de iczr_zonaRegistral.
	 *
	 * @return el valor de iczr_zonaRegistral
	 */
	public Collection<ZonaRegistral> getZonaRegistral()
	{
		return iczr_zonaRegistral;
	}

	/**
	 * Modifica el valor de is_nombreEntidad.
	 *
	 * @param as_s asigna el valor a la propiedad is_nombreEntidad
	 */
	public void setNombreEntidad(String as_s)
	{
		is_nombreEntidad = as_s;
	}

	/**
	 * Retorna el valor de is_nombreEntidad.
	 *
	 * @return el valor de is_nombreEntidad
	 */
	public String getNombreEntidad()
	{
		return is_nombreEntidad;
	}

	/**
	 * Modifica el valor de is_activo.
	 *
	 * @param as_s asigna el valor a la propiedad is_activo
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna el valor de is_activo.
	 *
	 * @return el valor de is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de is_idCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad is_idCirculo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de is_idCirculo.
	 *
	 * @return el valor de is_idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de is_idZonaRegistral.
	 *
	 * @param as_s asigna el valor a la propiedad is_idZonaRegistral
	 */
	public void setIdZonaRegistral(String as_s)
	{
		is_idZonaRegistral = as_s;
	}

	/**
	 * Retorna el valor de is_idZonaRegistral.
	 *
	 * @return el valor de is_idZonaRegistral
	 */
	public String getIdZonaRegistral()
	{
		return is_idZonaRegistral;
	}

	/**
	 * Modifica el valor de is_idTipoEntidad.
	 *
	 * @param as_s asigna el valor a la propiedad is_idTipoEntidad
	 */
	public void setIdTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de is_idTipoEntidad.
	 *
	 * @return el valor de is_idTipoEntidad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Modifica el valor de is_idEntidadExterna.
	 *
	 * @param as_s asigna el valor a la propiedad is_idEntidadExterna
	 */
	public void setIdEntidadExterna(String as_s)
	{
		is_idEntidadExterna = as_s;
	}

	/**
	 * Retorna el valor de is_idEntidadExterna.
	 *
	 * @return el valor de is_idEntidadExterna
	 */
	public String getIdEntidadExterna()
	{
		return is_idEntidadExterna;
	}

	/**
	 * Modifica el valor de is_numeroConvenio.
	 *
	 * @param as_s asigna el valor a la propiedad is_numeroConvenio
	 */
	public void setNumeroConvenio(String as_s)
	{
		is_numeroConvenio = as_s;
	}

	/**
	 * Retorna el valor de is_numeroConvenio.
	 *
	 * @return el valor de is_numeroConvenio
	 */
	public String getNumeroConvenio()
	{
		return is_numeroConvenio;
	}

	/**
	 * Modifica el valor de is_numeroDocumentoRenovacion.
	 *
	 * @param as_s asigna el valor a la propiedad is_numeroDocumentoRenovacion
	 */
	public void setNumeroDocumentoRenovacion(String as_s)
	{
		is_numeroDocumentoRenovacion = as_s;
	}

	/**
	 * Retorna el valor de is_numeroDocumentoRenovacion.
	 *
	 * @return el valor de is_numeroDocumentoRenovacion
	 */
	public String getNumeroDocumentoRenovacion()
	{
		return is_numeroDocumentoRenovacion;
	}

	/**
	 * Modifica el valor de is_usuarioAsignado.
	 *
	 * @param as_s asigna el valor a la propiedad is_usuarioAsignado
	 */
	public void setUsuarioAsignado(String as_s)
	{
		is_usuarioAsignado = as_s;
	}

	/**
	 * Retorna el valor de is_usuarioAsignado.
	 *
	 * @return el valor de is_usuarioAsignado
	 */
	public String getUsuarioAsignado()
	{
		return is_usuarioAsignado;
	}
	
	/**
	 * Modifica el valor de is_nombreConvenio.
	 *
	 * @param as_s asigna el valor a la propiedad is_nombreConvenio
	 */
	public void setNombreConvenio(String as_s)
	{
		is_nombreConvenio = as_s;
	}

	/**
	 * Retorna el valor de is_nombreConvenio.
	 *
	 * @return el valor de is_nombreConvenio
	 */
	public String getNombreConvenio()
	{
		return is_nombreConvenio;
	}

	/**
	 * Modifica el valor de is_documentoAprobador.
	 *
	 * @param as_s asigna el valor a la propiedad is_documentoAprobador
	 */
	public void setDocumentoAprobador(String as_s)
	{
		is_documentoAprobador = as_s;
	}

	/**
	 * Retorna el valor de is_documentoAprobador.
	 *
	 * @return el valor de is_documentoAprobador
	 */
	public String getDocumentoAprobador()
	{
		return is_documentoAprobador;
	}

	/**
	 * Modifica el valor de is_documentoAprobador.
	 *
	 * @param as_s asigna el valor a la propiedad is_documentoAprobador
	 */
	public void setIdentificadorDocumentoAprobador(String as_s)
	{
		is_identificadorDocumentoAprobador = as_s;
	}

	/**
	 * Retorna el valor de is_documentoAprobador.
	 *
	 * @return el valor de is_documentoAprobador
	 */
	public String getIdentificadorDocumentoAprobador()
	{
		return is_identificadorDocumentoAprobador;
	}

	/**
	 * Modifica el valor de id_fechaCreacionConvenio.
	 *
	 * @param ad_d asigna el valor a la propiedad id_fechaCreacionConvenio
	 */
	public void setFechaCreacionConvenio(Date ad_d)
	{
		id_fechaCreacionConvenio = ad_d;
	}

	/**
	 * Retorna el valor de id_fechaCreacionConvenio.
	 *
	 * @return el valor de id_fechaCreacionConvenio
	 */
	public Date getFechaCreacionConvenio()
	{
		return id_fechaCreacionConvenio;
	}

	/**
	 * Modifica el valor de id_fechaVencimiento.
	 *
	 * @param ad_d asigna el valor a la propiedad id_fechaVencimiento
	 */
	public void setFechaVencimiento(Date ad_d)
	{
		id_fechaVencimiento = ad_d;
	}

	/**
	 * Retorna el valor de id_fechaVencimiento.
	 *
	 * @return el valor de id_fechaVencimiento
	 */
	public Date getFechaVencimiento()
	{
		return id_fechaVencimiento;
	}
}
