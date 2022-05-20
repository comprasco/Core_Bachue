package com.bachue.snr.prosnr01.model.registro;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;



/**
 * Clase que contiene todos las propiedades DataReproduccionConstancia.
 *
 * @author ccalderon
 */
public class DataReproduccionConstancia implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 851212706349173434L;

	/** Propiedad capd anotacion predio direccion. */
	private Collection<AnotacionPredioDireccion> capd_anotacionPredioDireccion;

	/** Propiedad icdrc data reproduccion constancia. */
	private Collection<DataReproduccionConstancia> icdrc_dataReproduccionConstancia;

	/** Propiedad icl liquidacion. */
	private Collection<Liquidacion> icl_liquidacion;

	/** Propiedad iod datos documento. */
	private Documento iod_datosDocumento;

	/** Propiedad id documento. */
	private DocumentoConstancia id_documento;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad ib detalle reproduccion constancia. */
	private boolean ib_detalleReproduccionConstancia;

	/** Propiedad ib sin constancia reproduccion. */
	private boolean ib_sinConstanciaReproduccion;

	/** Propiedad ib sin constancia reproduccion turno. */
	private boolean ib_sinConstanciaReproduccionTurno;

	/**
	 * Modifica el valor de anotacion predio direccion.
	 *
	 * @param as_s asigna el valor a la propiedad anotacion predio direccion
	 */
	public void setAnotacionPredioDireccion(Collection<AnotacionPredioDireccion> as_s)
	{
		capd_anotacionPredioDireccion = as_s;
	}

	/**
	 * Retorna el valor de anotacion predio direccion.
	 *
	 * @return el valor de anotacion predio direccion
	 */
	public Collection<AnotacionPredioDireccion> getAnotacionPredioDireccion()
	{
		if(!CollectionUtils.isValidCollection(capd_anotacionPredioDireccion))
			capd_anotacionPredioDireccion = new ArrayList<AnotacionPredioDireccion>();

		return capd_anotacionPredioDireccion;
	}

	/**
	 * Modifica el valor de data reproduccion constancia.
	 *
	 * @param acdrc_cdrc asigna el valor a la propiedad data reproduccion constancia
	 */
	public void setDataReproduccionConstancia(Collection<DataReproduccionConstancia> acdrc_cdrc)
	{
		icdrc_dataReproduccionConstancia = acdrc_cdrc;
	}

	/**
	 * Retorna el valor de data reproduccion constancia.
	 *
	 * @return el valor de data reproduccion constancia
	 */
	public Collection<DataReproduccionConstancia> getDataReproduccionConstancia()
	{
		return icdrc_dataReproduccionConstancia;
	}

	/**
	 * Modifica el valor de datos documento.
	 *
	 * @param ad_d asigna el valor a la propiedad datos documento
	 */
	public void setDatosDocumento(Documento ad_d)
	{
		iod_datosDocumento = ad_d;
	}

	/**
	 * Retorna el valor de datos documento.
	 *
	 * @return el valor de datos documento
	 */
	public Documento getDatosDocumento()
	{
		return iod_datosDocumento;
	}

	/**
	 * Modifica el valor de detalle reproduccion constancia.
	 *
	 * @param ab_b asigna el valor a la propiedad detalle reproduccion constancia
	 */
	public void setDetalleReproduccionConstancia(boolean ab_b)
	{
		ib_detalleReproduccionConstancia = ab_b;
	}

	/**
	 * Valida la propiedad detalle reproduccion constancia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en detalle reproduccion constancia
	 */
	public boolean isDetalleReproduccionConstancia()
	{
		return ib_detalleReproduccionConstancia;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ld_doc asigna el valor a la propiedad documento
	 */
	public void setDocumento(DocumentoConstancia ld_doc)
	{
		this.id_documento = ld_doc;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public DocumentoConstancia getDocumento()
	{
		return id_documento;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de liquidacion.
	 *
	 * @param acl_l asigna el valor a la propiedad liquidacion
	 */
	public void setLiquidacion(Collection<Liquidacion> acl_l)
	{
		icl_liquidacion = acl_l;
	}

	/**
	 * Retorna el valor de liquidacion.
	 *
	 * @return el valor de liquidacion
	 */
	public Collection<Liquidacion> getLiquidacion()
	{
		return icl_liquidacion;
	}

	/**
	 * Modifica el valor de sin constancia reproduccion.
	 *
	 * @param ab_b asigna el valor a la propiedad sin constancia reproduccion
	 */
	public void setSinConstanciaReproduccion(boolean ab_b)
	{
		ib_sinConstanciaReproduccion = ab_b;
	}

	/**
	 * Valida la propiedad sin constancia reproduccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en sin constancia reproduccion
	 */
	public boolean isSinConstanciaReproduccion()
	{
		return ib_sinConstanciaReproduccion;
	}

	/**
	 * Modifica el valor de sin constancia reproduccion turno.
	 *
	 * @param ab_b asigna el valor a la propiedad sin constancia reproduccion turno
	 */
	public void setSinConstanciaReproduccionTurno(boolean ab_b)
	{
		ib_sinConstanciaReproduccionTurno = ab_b;
	}

	/**
	 * Valida la propiedad sin constancia reproduccion turno.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en sin constancia reproduccion turno
	 */
	public boolean isSinConstanciaReproduccionTurno()
	{
		return ib_sinConstanciaReproduccionTurno;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}
}
