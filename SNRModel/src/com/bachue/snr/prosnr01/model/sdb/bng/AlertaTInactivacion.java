package com.bachue.snr.prosnr01.model.sdb.bng;

import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlerta;

import com.b2bsg.common.util.DateUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase que contiene todos las propiedades AlertaTInactivacion.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 2/04/2020
 */
public class AlertaTInactivacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8652403563711611055L;

	/** Propiedad id documento fecha. */
	private Date id_documentoFecha;

	/** Propiedad is documento numero. */
	private String is_documentoNumero;

	/** Propiedad is documento oficina origen. */
	private String is_documentoOficinaOrigen;

	/** Propiedad is documento SGD doc name. */
	private String is_documentoSGDDocName;

	/** Propiedad is documento SGD id. */
	private String is_documentoSGDId;

	/** Propiedad is documento tipo. */
	private String is_documentoTipo;

	/** Propiedad is motivo inactivacion. */
	private String is_motivoInactivacion;

	/** Propiedad is nom oficina origen. */
	private String is_nomOficinaOrigen;

	/** Propiedad is nom tipo documento publico. */
	private String is_nomTipoDocumentoPublico;

	/** Propiedad is texto inactivacion. */
	private String is_textoInactivacion;

	/** Propiedad il id alerta tierras. */
	private long il_idAlertaTierras;

	/**
	 * Instancia un nuevo objeto alerta T inactivacion.
	 */
	public AlertaTInactivacion()
	{
	}

	/**
	 * Instancia un nuevo objeto alerta T inactivacion.
	 *
	 * @param ateia_entrada de ateia entrada
	 */
	public AlertaTInactivacion(TipoEntradaInactivarAlerta ateia_entrada)
	{
		if(ateia_entrada != null)
		{
			id_documentoFecha             = DateUtils.getDate(
				    DateUtils.getTimestamp(ateia_entrada.getFechaDocumento())
				);
			is_documentoNumero            = ateia_entrada.getDocNumero();
			is_documentoOficinaOrigen     = ateia_entrada.getOficinaOrigen();
			is_documentoSGDDocName        = ateia_entrada.getDocNameSGD();
			is_documentoSGDId             = ateia_entrada.getDocIdSGD();
			is_documentoTipo              = ateia_entrada.getCodTipoDocumentoPublico();
			is_motivoInactivacion         = ateia_entrada.getIdMotivo().getValue();
			is_textoInactivacion          = ateia_entrada.getTextoInactivacion();
			il_idAlertaTierras            = ateia_entrada.getIdAlerta();
		}
	}

	/**
	 * Retorna Objeto o variable de valor id alerta tierras.
	 *
	 * @return Retorna el valor de la propiedad idAlertaTierras
	 */
	public long getIdAlertaTierras()
	{
		return il_idAlertaTierras;
	}

	/**
	 * Modifica el valor de IdAlertaTierras.
	 *
	 * @param al_l de al l
	 */
	public void setIdAlertaTierras(long al_l)
	{
		il_idAlertaTierras = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor motivo inactivacion.
	 *
	 * @return Retorna el valor de la propiedad motivoInactivacion
	 */
	public String getMotivoInactivacion()
	{
		return is_motivoInactivacion;
	}

	/**
	 * Modifica el valor de MotivoInactivacion.
	 *
	 * @param as_s de as s
	 */
	public void setMotivoInactivacion(String as_s)
	{
		is_motivoInactivacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor documento oficina origen.
	 *
	 * @return Retorna el valor de la propiedad documentoOficinaOrigen
	 */
	public String getDocumentoOficinaOrigen()
	{
		return is_documentoOficinaOrigen;
	}

	/**
	 * Modifica el valor de DocumentoOficinaOrigen.
	 *
	 * @param as_s de as s
	 */
	public void setDocumentoOficinaOrigen(String as_s)
	{
		is_documentoOficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor documento tipo.
	 *
	 * @return Retorna el valor de la propiedad documentoTipo
	 */
	public String getDocumentoTipo()
	{
		return is_documentoTipo;
	}

	/**
	 * Modifica el valor de DocumentoTipo.
	 *
	 * @param as_s de as s
	 */
	public void setDocumentoTipo(String as_s)
	{
		is_documentoTipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor documento fecha.
	 *
	 * @return Retorna el valor de la propiedad documentoFecha
	 */
	public Date getDocumentoFecha()
	{
		return id_documentoFecha;
	}

	/**
	 * Modifica el valor de DocumentoFecha.
	 *
	 * @param ad_d de ad d
	 */
	public void setDocumentoFecha(Date ad_d)
	{
		id_documentoFecha = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor documento numero.
	 *
	 * @return Retorna el valor de la propiedad documentoNumero
	 */
	public String getDocumentoNumero()
	{
		return is_documentoNumero;
	}

	/**
	 * Modifica el valor de DocumentoNumero.
	 *
	 * @param as_s de as s
	 */
	public void setDocumentoNumero(String as_s)
	{
		is_documentoNumero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor documento SGD id.
	 *
	 * @return Retorna el valor de la propiedad documentoSGDId
	 */
	public String getDocumentoSGDId()
	{
		return is_documentoSGDId;
	}

	/**
	 * Modifica el valor de DocumentoSGDId.
	 *
	 * @param as_s de as s
	 */
	public void setDocumentoSGDId(String as_s)
	{
		is_documentoSGDId = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor documento SGD doc name.
	 *
	 * @return Retorna el valor de la propiedad documentoSGDDocName
	 */
	public String getDocumentoSGDDocName()
	{
		return is_documentoSGDDocName;
	}

	/**
	 * Modifica el valor de DocumentoSGDDocName.
	 *
	 * @param as_s de as s
	 */
	public void setDocumentoSGDDocName(String as_s)
	{
		is_documentoSGDDocName = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor texto inactivacion.
	 *
	 * @return Retorna el valor de la propiedad textoInactivacion
	 */
	public String getTextoInactivacion()
	{
		return is_textoInactivacion;
	}

	/**
	 * Modifica el valor de TextoInactivacion.
	 *
	 * @param as_s de as s
	 */
	public void setTextoInactivacion(String as_s)
	{
		is_textoInactivacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nom oficina origen.
	 *
	 * @return Retorna el valor de la propiedad nomOficinaOrigen
	 */
	public String getNomOficinaOrigen()
	{
		return is_nomOficinaOrigen;
	}

	/**
	 * Modifica el valor de NomOficinaOrigen.
	 *
	 * @param as_s de as s
	 */
	public void setNomOficinaOrigen(String as_s)
	{
		is_nomOficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nom tipo documento publico.
	 *
	 * @return Retorna el valor de la propiedad nomTipoDocumentoPublico
	 */
	public String getNomTipoDocumentoPublico()
	{
		return is_nomTipoDocumentoPublico;
	}

	/**
	 * Modifica el valor de NomTipoDocumentoPublico.
	 *
	 * @param as_s de as s
	 */
	public void setNomTipoDocumentoPublico(String as_s)
	{
		is_nomTipoDocumentoPublico = as_s;
	}
}
