package com.bachue.snr.prosnr01.model.consulta.trazabilidad;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTurnoTramite;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades InformacionDocumento.
 *
 * @author ccalderon
 */
public class InformacionDocumento extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5387433184034831880L;

	/** Propiedad icat info alertas. */
	private Collection<AlertaTurnoTramite> icat_infoAlertas;

	/** Propiedad id departamento. */
	private Departamento id_departamento;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad im municipio. */
	private Municipio im_municipio;

	/** Propiedad ioo oficina origen. */
	private OficinaOrigen ioo_oficinaOrigen;

	/** Propiedad ip pais. */
	private Pais ip_pais;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad itdp tipo documento publico. */
	private TipoDocumentoPublico itdp_tipoDocumentoPublico;

	/** Propiedad ite tipo entidad. */
	private TipoEntidad ite_tipoEntidad;

	/** Propiedad ito tipo oficina. */
	private TipoOficina ito_tipoOficina;

	/**
	 * Modifica el valor de departamento.
	 *
	 * @param ad_d asigna el valor a la propiedad departamento
	 */
	public void setDepartamento(Departamento ad_d)
	{
		id_departamento = ad_d;
	}

	/**
	 * Retorna el valor de departamento.
	 *
	 * @return el valor de departamento
	 */
	public Departamento getDepartamento()
	{
		return id_departamento;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ad_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		return id_documento;
	}

	/**
	 * Modifica el valor de info alertas.
	 *
	 * @param acat_cat asigna el valor a la propiedad info alertas
	 */
	public void setInfoAlertas(Collection<AlertaTurnoTramite> acat_cat)
	{
		icat_infoAlertas = acat_cat;
	}

	/**
	 * Retorna el valor de info alertas.
	 *
	 * @return el valor de info alertas
	 */
	public Collection<AlertaTurnoTramite> getInfoAlertas()
	{
		return icat_infoAlertas;
	}

	/**
	 * Modifica el valor de municipio.
	 *
	 * @param am_m asigna el valor a la propiedad municipio
	 */
	public void setMunicipio(Municipio am_m)
	{
		im_municipio = am_m;
	}

	/**
	 * Retorna el valor de municipio.
	 *
	 * @return el valor de municipio
	 */
	public Municipio getMunicipio()
	{
		return im_municipio;
	}

	/**
	 * Modifica el valor de oficina origen.
	 *
	 * @param aof_of asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(OficinaOrigen aof_of)
	{
		ioo_oficinaOrigen = aof_of;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public OficinaOrigen getOficinaOrigen()
	{
		return ioo_oficinaOrigen;
	}

	/**
	 * Modifica el valor de pais.
	 *
	 * @param ap_p asigna el valor a la propiedad pais
	 */
	public void setPais(Pais ap_p)
	{
		ip_pais = ap_p;
	}

	/**
	 * Retorna el valor de pais.
	 *
	 * @return el valor de pais
	 */
	public Pais getPais()
	{
		return ip_pais;
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

	/**
	 * Modifica el valor de tipo documento publico.
	 *
	 * @param atdp_tdp asigna el valor a la propiedad tipo documento publico
	 */
	public void setTipoDocumentoPublico(TipoDocumentoPublico atdp_tdp)
	{
		itdp_tipoDocumentoPublico = atdp_tdp;
	}

	/**
	 * Retorna el valor de tipo documento publico.
	 *
	 * @return el valor de tipo documento publico
	 */
	public TipoDocumentoPublico getTipoDocumentoPublico()
	{
		return itdp_tipoDocumentoPublico;
	}

	/**
	 * Modifica el valor de tipo entidad.
	 *
	 * @param ate_te asigna el valor a la propiedad tipo entidad
	 */
	public void setTipoEntidad(TipoEntidad ate_te)
	{
		ite_tipoEntidad = ate_te;
	}

	/**
	 * Retorna el valor de tipo entidad.
	 *
	 * @return el valor de tipo entidad
	 */
	public TipoEntidad getTipoEntidad()
	{
		return ite_tipoEntidad;
	}

	/**
	 * Modifica el valor de tipo oficina.
	 *
	 * @param ato_to asigna el valor a la propiedad tipo oficina
	 */
	public void setTipoOficina(TipoOficina ato_to)
	{
		ito_tipoOficina = ato_to;
	}

	/**
	 * Retorna el valor de tipo oficina.
	 *
	 * @return el valor de tipo oficina
	 */
	public TipoOficina getTipoOficina()
	{
		return ito_tipoOficina;
	}
}
