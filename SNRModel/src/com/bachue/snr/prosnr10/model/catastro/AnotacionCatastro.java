package com.bachue.snr.prosnr10.model.catastro;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades AnotacionCatastro.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/03/2020
 */
public class AnotacionCatastro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8389058840477589604L;

	/** Propiedad ic fecha anotacion. */
	private Calendar ic_fechaAnotacion;

	/** Propiedad ic fecha documento. */
	private Calendar ic_fechaDocumento;

	/** Propiedad icis intervinientes catastro. */
	private Collection<PropietarioCatastro> icis_intervinientesCatastro;

	/** Propiedad icpc propietarios catastro. */
	private Collection<PropietarioCatastro> icpc_propietariosCatastro;

	/** Propiedad id valor acto. */
	private Double id_valorActo;

	/** Propiedad is cod dominio acto juridico. */
	private String is_codDominioActoJuridico;

	/** Propiedad is cod genero. */
	private String is_codGenero;

	/** Propiedad is cod naturaleza juridica folio. */
	private String is_codNaturalezaJuridicaFolio;

	/** Propiedad is cod tipo documento publico. */
	private String is_codTipoDocumentoPublico;

	/** Propiedad is codigo acto. */
	private String is_codigoActo;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is descripcion acto. */
	private String is_descripcionActo;

	/** Propiedad is direccion. */
	private String is_direccion;

	/** Propiedad is dominio DRR. */
	private String is_dominioDRR;

	/** Propiedad is especificacion. */
	private String is_especificacion;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id estado anotacion. */
	private String is_idEstadoAnotacion;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nom documento publico. */
	private String is_nomDocumentoPublico;

	/** Propiedad is nom dominio acto juridico. */
	private String is_nomDominioActoJuridico;

	/** Propiedad is nom naturaleza juridica folio. */
	private String is_nomNaturalezaJuridicaFolio;

	/** Propiedad is nombre acto. */
	private String is_nombreActo;

	/** Propiedad is num anotacion. */
	private String is_numAnotacion;

	/** Propiedad is orden. */
	private String is_orden;

	/** Propiedad is radicacion. */
	private String is_radicacion;

	/** Propiedad is tipo anotacion. */
	private String is_tipoAnotacion;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/** Propiedad version cod naturaleza juridica folio. */
	private long versionCodNaturalezaJuridicaFolio;

	/**
	 * Instancia un nuevo objeto anotacion catastro.
	 */
	public AnotacionCatastro()
	{
	}

	/**
	 * Instancia un nuevo objeto anotacion catastro.
	 *
	 * @param as_numAnotacion de as num anotacion
	 * @param as_comentario de as comentario
	 * @param ac_fechaAnotacion de ac fecha anotacion
	 * @param as_codNaturalezaJuridicaFolio de as cod naturaleza juridica folio
	 * @param as_nomNaturalezaJuridicaFolio de as nom naturaleza juridica folio
	 * @param as_dominioDRR de as dominio DRR
	 * @param acic_intervinientes de acic intervinientes
	 * @param as_radicacion de as radicacion
	 * @param as_nir de as nir
	 * @param as_idEstadoAnotacion de as id estado anotacion
	 * @param as_especificacion de as especificacion
	 * @param as_codDominioActoJuridico de as cod dominio acto juridico
	 * @param as_nomDominioActoJuridico de as nom dominio acto juridico
	 * @param ls_codTipoDocumentoPublico de ls cod tipo documento publico
	 * @param ls_nomDocumentoPublico de ls nom documento publico
	 * @param lc_fechaDocumento de lc fecha documento
	 */
	public AnotacionCatastro(
	    String as_numAnotacion, String as_comentario, Calendar ac_fechaAnotacion, String as_codNaturalezaJuridicaFolio,
	    String as_nomNaturalezaJuridicaFolio, String as_dominioDRR, Collection<PropietarioCatastro> acic_intervinientes,
	    String as_radicacion, String as_nir, String as_idEstadoAnotacion, String as_especificacion,
	    String as_codDominioActoJuridico, String as_nomDominioActoJuridico, String ls_codTipoDocumentoPublico,
	    String ls_nomDocumentoPublico, Calendar lc_fechaDocumento
	)
	{
		is_numAnotacion                   = as_numAnotacion;
		is_comentario                     = as_comentario;
		ic_fechaAnotacion                 = ac_fechaAnotacion;
		is_codNaturalezaJuridicaFolio     = as_codNaturalezaJuridicaFolio;
		is_nomNaturalezaJuridicaFolio     = as_nomNaturalezaJuridicaFolio;
		is_dominioDRR                     = as_dominioDRR;
		icis_intervinientesCatastro       = acic_intervinientes;
		is_radicacion                     = as_radicacion;
		is_nir                            = as_nir;
		is_idEstadoAnotacion              = as_idEstadoAnotacion;
		is_especificacion                 = as_especificacion;
		is_codDominioActoJuridico         = as_codDominioActoJuridico;
		is_nomDominioActoJuridico         = as_nomDominioActoJuridico;
		is_codTipoDocumentoPublico        = ls_codTipoDocumentoPublico;
		is_nomDocumentoPublico            = ls_nomDocumentoPublico;
		ic_fechaDocumento                 = lc_fechaDocumento;
	}

	/**
	 * Instancia un nuevo objeto anotacion catastro.
	 *
	 * @param acic_intervinientes de acic intervinientes
	 */
	public AnotacionCatastro(Collection<PropietarioCatastro> acic_intervinientes)
	{
		icis_intervinientesCatastro = acic_intervinientes;
	}

	/**
	 * Modifica el valor de CodDominioActoJuridico.
	 *
	 * @param as_s de cod dominio acto juridico
	 */
	public void setCodDominioActoJuridico(String as_s)
	{
		is_codDominioActoJuridico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cod dominio acto juridico.
	 *
	 * @return Retorna el valor de la propiedad codDominioActoJuridico
	 */
	public String getCodDominioActoJuridico()
	{
		return is_codDominioActoJuridico;
	}

	/**
	 * Modifica el valor de CodGenero.
	 *
	 * @param as_s de as s
	 */
	public void setCodGenero(String as_s)
	{
		is_codGenero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cod genero.
	 *
	 * @return Retorna el valor de la propiedad codGenero
	 */
	public String getCodGenero()
	{
		return is_codGenero;
	}

	/**
	 * Modifica el valor de CodNaturalezaJuridicaFolio.
	 *
	 * @param as_s de as s
	 */
	public void setCodNaturalezaJuridicaFolio(String as_s)
	{
		is_codNaturalezaJuridicaFolio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cod naturaleza juridica folio.
	 *
	 * @return el valor de cod naturaleza juridica folio
	 */
	public String getCodNaturalezaJuridicaFolio()
	{
		return is_codNaturalezaJuridicaFolio;
	}

	/**
	 * Modifica el valor de CodTipoDocumentoPublico.
	 *
	 * @param as_s de as s
	 */
	public void setCodTipoDocumentoPublico(String as_s)
	{
		is_codTipoDocumentoPublico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cod tipo documento publico.
	 *
	 * @return Retorna el valor de la propiedad codTipoDocumentoPublico
	 */
	public String getCodTipoDocumentoPublico()
	{
		return is_codTipoDocumentoPublico;
	}

	/**
	 * Modifica el valor de CodigoActo.
	 *
	 * @param as_s de as s
	 */
	public void setCodigoActo(String as_s)
	{
		is_codigoActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo acto.
	 *
	 * @return el valor de codigo acto
	 */
	public String getCodigoActo()
	{
		return is_codigoActo;
	}

	/**
	 * Modifica el valor de Comentario.
	 *
	 * @param as_s de as s
	 */
	public void setComentario(String as_s)
	{
		is_comentario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor comentario.
	 *
	 * @return el valor de comentario
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de DescripcionActo.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionActo(String as_s)
	{
		is_descripcionActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion acto.
	 *
	 * @return el valor de descripcion acto
	 */
	public String getDescripcionActo()
	{
		return is_descripcionActo;
	}

	/**
	 * Modifica el valor de Direccion.
	 *
	 * @param as_s de as s
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion.
	 *
	 * @return el valor de direccion
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Modifica el valor de DominioDRR.
	 *
	 * @param as_s de as s
	 */
	public void setDominioDRR(String as_s)
	{
		is_dominioDRR = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor dominio DRR.
	 *
	 * @return el valor de dominio DRR
	 */
	public String getDominioDRR()
	{
		return is_dominioDRR;
	}

	/**
	 * Modifica el valor de Especificacion.
	 *
	 * @param as_s de as s
	 */
	public void setEspecificacion(String as_s)
	{
		is_especificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor especificacion.
	 *
	 * @return Retorna el valor de la propiedad especificacion
	 */
	public String getEspecificacion()
	{
		return is_especificacion;
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
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return el valor de estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de FechaAnotacion.
	 *
	 * @param ac_c de ac c
	 */
	public void setFechaAnotacion(Calendar ac_c)
	{
		ic_fechaAnotacion = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor fecha anotacion.
	 *
	 * @return el valor de fecha anotacion
	 */
	public Calendar getFechaAnotacion()
	{
		return ic_fechaAnotacion;
	}

	/**
	 * Modifica el valor de FechaDocumento.
	 *
	 * @param ac_c de ac c
	 */
	public void setFechaDocumento(Calendar ac_c)
	{
		ic_fechaDocumento = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor fecha documento.
	 *
	 * @return Retorna el valor de la propiedad fechaDocumento
	 */
	public Calendar getFechaDocumento()
	{
		return ic_fechaDocumento;
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
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdEstadoAnotacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdEstadoAnotacion(String as_s)
	{
		is_idEstadoAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado anotacion.
	 *
	 * @return Retorna el valor de la propiedad idEstadoAnotacion
	 */
	public String getIdEstadoAnotacion()
	{
		return is_idEstadoAnotacion;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l de al l
	 */
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IntervinientesCatastro.
	 *
	 * @param acic_cid de acic cid
	 */
	public void setIntervinientesCatastro(Collection<PropietarioCatastro> acic_cid)
	{
		icis_intervinientesCatastro = acic_cid;
	}

	/**
	 * Retorna Objeto o variable de valor intervinientes catastro.
	 *
	 * @return el valor de intervinientes catastro
	 */
	public Collection<PropietarioCatastro> getIntervinientesCatastro()
	{
		return icis_intervinientesCatastro;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s de as s
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return Retorna el valor de la propiedad nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NomDocumentoPublico.
	 *
	 * @param as_s de as s
	 */
	public void setNomDocumentoPublico(String as_s)
	{
		is_nomDocumentoPublico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nom documento publico.
	 *
	 * @return Retorna el valor de la propiedad nomDocumentoPublico
	 */
	public String getNomDocumentoPublico()
	{
		return is_nomDocumentoPublico;
	}

	/**
	 * Modifica el valor de NomDominioActoJuridico.
	 *
	 * @param as_s de nom dominio acto juridico
	 */
	public void setNomDominioActoJuridico(String as_s)
	{
		is_nomDominioActoJuridico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nom dominio acto juridico.
	 *
	 * @return Retorna el valor de la propiedad nomDominioActoJuridico
	 */
	public String getNomDominioActoJuridico()
	{
		return is_nomDominioActoJuridico;
	}

	/**
	 * Modifica el valor de NomNaturalezaJuridicaFolio.
	 *
	 * @param as_s de as s
	 */
	public void setNomNaturalezaJuridicaFolio(String as_s)
	{
		is_nomNaturalezaJuridicaFolio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nom naturaleza juridica folio.
	 *
	 * @return el valor de nom naturaleza juridica folio
	 */
	public String getNomNaturalezaJuridicaFolio()
	{
		return is_nomNaturalezaJuridicaFolio;
	}

	/**
	 * Modifica el valor de NombreActo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreActo(String as_s)
	{
		is_nombreActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre acto.
	 *
	 * @return el valor de nombre acto
	 */
	public String getNombreActo()
	{
		return is_nombreActo;
	}

	/**
	 * Modifica el valor de NumAnotacion.
	 *
	 * @param as_s de as s
	 */
	public void setNumAnotacion(String as_s)
	{
		is_numAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor num anotacion.
	 *
	 * @return el valor de num anotacion
	 */
	public String getNumAnotacion()
	{
		return is_numAnotacion;
	}

	/**
	 * Modifica el valor de Orden.
	 *
	 * @param as_s de as s
	 */
	public void setOrden(String as_s)
	{
		is_orden = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return el valor de orden
	 */
	public String getOrden()
	{
		return is_orden;
	}

	/**
	 * Modifica el valor de PropietariosCatastro.
	 *
	 * @param acpc_cpc de acpc cpc
	 */
	public void setPropietariosCatastro(Collection<PropietarioCatastro> acpc_cpc)
	{
		icpc_propietariosCatastro = acpc_cpc;
	}

	/**
	 * Retorna Objeto o variable de valor propietarios catastro.
	 *
	 * @return el valor de propietarios catastro
	 */
	public Collection<PropietarioCatastro> getPropietariosCatastro()
	{
		return icpc_propietariosCatastro;
	}

	/**
	 * Modifica el valor de Radicacion.
	 *
	 * @param as_s de as s
	 */
	public void setRadicacion(String as_s)
	{
		is_radicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor radicacion.
	 *
	 * @return Retorna el valor de la propiedad radicacion
	 */
	public String getRadicacion()
	{
		return is_radicacion;
	}

	/**
	 * Modifica el valor de TipoAnotacion.
	 *
	 * @param as_s de as s
	 */
	public void setTipoAnotacion(String as_s)
	{
		is_tipoAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo anotacion.
	 *
	 * @return el valor de tipo anotacion
	 */
	public String getTipoAnotacion()
	{
		return is_tipoAnotacion;
	}

	/**
	 * Modifica el valor de ValorActo.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorActo(Double ad_d)
	{
		id_valorActo = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor acto.
	 *
	 * @return el valor de valor acto
	 */
	public Double getValorActo()
	{
		return id_valorActo;
	}

	/**
	 * @param al_l Modifica el valor de la propiedad versionCodNaturalezaJuridicaFolio por versionCodNaturalezaJuridicaFolio
	 */
	public void setVersionCodNaturalezaJuridicaFolio(long al_l)
	{
		versionCodNaturalezaJuridicaFolio = al_l;
	}

	/**
	 * @return Retorna el valor de la propiedad versionCodNaturalezaJuridicaFolio
	 */
	public long getVersionCodNaturalezaJuridicaFolio()
	{
		return versionCodNaturalezaJuridicaFolio;
	}
}
