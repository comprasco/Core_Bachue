package com.bachue.snr.prosnr01.model.sdb.bng;

import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertas;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertasCodigoEstado;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoEntradaConsultarDocumentoAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoEntradaInscribirAlertaCabecera;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;


/**
 * Clase que contiene todos las propiedades AlertaTierras.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class AlertaTierras extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2728234993631620003L;

	/** Propiedad ic fecha inscripcion. */
	private Calendar ic_fechaInscripcionCalendar;

	/** Propiedad id documento fecha. */
	private Date id_documentoFecha;

	/** Propiedad id fecha fin alerta. */
	private Date id_fechaFinAlerta;

	/** Propiedad id fecha inscripcion. */
	private Date id_fechaInscripcion;

	/** Propiedad id fecha radicado. */
	private Date id_fechaRadicado;

	/** Propiedad is creado SNR. */
	private String is_creadoSNR;

	/** Propiedad is documento doc name. */
	private String is_documentoDocName;

	/** Propiedad is documento id. */
	private String is_documentoId;

	/** Propiedad is documento numero. */
	private String is_documentoNumero;

	/** Propiedad is documento oficina origen. */
	private String is_documentoOficinaOrigen;

	/** Propiedad is documento tipo. */
	private String is_documentoTipo;

	/** Propiedad is estado alerta. */
	private String is_estadoAlerta;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nom comunidad etnica. */
	private String is_nomComunidadEtnica;

	/** Propiedad is nom entidad. */
	private String is_nomEntidad;

	/** Propiedad is nom oficina origen. */
	private String is_nomOficinaOrigen;

	/** Propiedad is nom tipo documento publico. */
	private String is_nomTipoDocumentoPublico;

	/** Propiedad is numero radicado. */
	private String is_numeroRadicado;

	/** Propiedad is observacion. */
	private String is_observacion;

	/** Propiedad is tipo alerta. */
	private String is_tipoAlerta;

	/** Propiedad il id alerta tierras. */
	private long il_idAlertaTierras;

	/** Propiedad il id entidad. */
	private long il_idEntidad;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/**
	 * Instancia un nuevo objeto alerta tierras.
	 */
	public AlertaTierras()
	{
	}

	/**
	 * Instancia un nuevo objeto alerta tierras.
	 *
	 * @param ateiac_entrada de ateiac entrada
	 */
	public AlertaTierras(TipoEntradaInscribirAlertaCabecera ateiac_entrada)
	{
		if(ateiac_entrada != null)
		{
			is_creadoSNR                  = ateiac_entrada.getCreadoSNR();
			is_documentoDocName           = ateiac_entrada.getDocNameSGD();
			is_documentoId                = ateiac_entrada.getDocIdSGD();
			is_documentoNumero            = ateiac_entrada.getDocNumero();
			is_documentoOficinaOrigen     = ateiac_entrada.getOficinaOrigen();
			is_documentoTipo              = ateiac_entrada.getCodTipoDocumentoPublico();
			is_numeroRadicado             = ateiac_entrada.getProcesoNroRadicado();
			is_tipoAlerta                 = ateiac_entrada.getTipoAlerta();
			il_idEntidad                  = ateiac_entrada.getIdEntidad();
		}
	}

	/**
	 * Instancia un nuevo objeto alerta tierras.
	 *
	 * @param atecda_entrada de atecda entrada
	 */
	public AlertaTierras(TipoEntradaConsultarDocumentoAlerta atecda_entrada)
	{
		if(atecda_entrada != null)
		{
			is_documentoOficinaOrigen     = atecda_entrada.getOficinaOrigen();
			is_documentoTipo              = atecda_entrada.getCodTipoDocumentoPublico();
			is_documentoNumero            = atecda_entrada.getDocNumero();
		}
	}

	/**
	 * Instancia un nuevo objeto alerta tierras.
	 *
	 * @param ateca_entrada de ateca entrada
	 */
	public AlertaTierras(TipoEntradaConsultarAlertas ateca_entrada)
	{
		if(ateca_entrada != null)
		{
			is_creadoSNR                    = ateca_entrada.getEsSNR();
			il_idEntidad                    = NumericUtils.getLong(ateca_entrada.getIdEntidad());
			ic_fechaInscripcionCalendar     = ateca_entrada.getFechaInscripcion();
			is_documentoTipo                = ateca_entrada.getCodTipoDocumentoPublico();
			is_idCirculo                    = ateca_entrada.getCodCirculoRegistral();
			il_idMatricula                  = NumericUtils.getLong(ateca_entrada.getNumMatriculaInmobiliaria());
			is_nomComunidadEtnica           = ateca_entrada.getNombreComunidadEtnica();

			{
				TipoEntradaConsultarAlertasCodigoEstado ltecalce_codigoEstado;

				ltecalce_codigoEstado = ateca_entrada.getCodigoEstado();

				if(ltecalce_codigoEstado != null)
					is_estadoAlerta = ltecalce_codigoEstado.getValue();
			}
		}
	}

	/**
	 * Retorna Objeto o variable de valor tipo alerta.
	 *
	 * @return Retorna el valor de la propiedad tipoAlerta
	 */
	public String getTipoAlerta()
	{
		return is_tipoAlerta;
	}

	/**
	 * Modifica el valor de TipoAlerta.
	 *
	 * @param as_s de as s
	 */
	public void setTipoAlerta(String as_s)
	{
		is_tipoAlerta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero radicado.
	 *
	 * @return Retorna el valor de la propiedad numeroRadicado
	 */
	public String getNumeroRadicado()
	{
		return is_numeroRadicado;
	}

	/**
	 * Modifica el valor de NumeroRadicado.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroRadicado(String as_s)
	{
		is_numeroRadicado = as_s;
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
	 * Retorna Objeto o variable de valor documento id.
	 *
	 * @return Retorna el valor de la propiedad documentoId
	 */
	public String getDocumentoId()
	{
		return is_documentoId;
	}

	/**
	 * Modifica el valor de DocumentoId.
	 *
	 * @param as_s de as s
	 */
	public void setDocumentoId(String as_s)
	{
		is_documentoId = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor documento doc name.
	 *
	 * @return Retorna el valor de la propiedad documentoDocName
	 */
	public String getDocumentoDocName()
	{
		return is_documentoDocName;
	}

	/**
	 * Modifica el valor de DocumentoDocName.
	 *
	 * @param as_s de as s
	 */
	public void setDocumentoDocName(String as_s)
	{
		is_documentoDocName = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado alerta.
	 *
	 * @return Retorna el valor de la propiedad estadoAlerta
	 */
	public String getEstadoAlerta()
	{
		return is_estadoAlerta;
	}

	/**
	 * Modifica el valor de EstadoAlerta.
	 *
	 * @param as_s de as s
	 */
	public void setEstadoAlerta(String as_s)
	{
		is_estadoAlerta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor creado SNR.
	 *
	 * @return Retorna el valor de la propiedad creadoSNR
	 */
	public String getCreadoSNR()
	{
		return is_creadoSNR;
	}

	/**
	 * Modifica el valor de CreadoSNR.
	 *
	 * @param as_s de as s
	 */
	public void setCreadoSNR(String as_s)
	{
		is_creadoSNR = as_s;
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
	 * Modifica el valor de Nir.
	 *
	 * @param as_s de as s
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observacion.
	 *
	 * @return Retorna el valor de la propiedad observacion
	 */
	public String getObservacion()
	{
		return is_observacion;
	}

	/**
	 * Modifica el valor de Observacion.
	 *
	 * @param as_s de as s
	 */
	public void setObservacion(String as_s)
	{
		is_observacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha radicado.
	 *
	 * @return Retorna el valor de la propiedad fechaRadicado
	 */
	public Date getFechaRadicado()
	{
		return id_fechaRadicado;
	}

	/**
	 * Modifica el valor de FechaRadicado.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaRadicado(Date ad_d)
	{
		id_fechaRadicado = ad_d;
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
	 * Retorna Objeto o variable de valor fecha inscripcion.
	 *
	 * @return el valor de fecha inscripcion
	 */
	public Calendar getFechaInscripcionCalendar()
	{
		return ic_fechaInscripcionCalendar;
	}

	/**
	 * Modifica el valor de FechaInscripcion.
	 *
	 * @param ac_c de ac c
	 */
	public void setFechaInscripcionCalendar(Calendar ac_c)
	{
		ic_fechaInscripcionCalendar = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor fecha fin alerta.
	 *
	 * @return Retorna el valor de la propiedad fechaFinAlerta
	 */
	public Date getFechaFinAlerta()
	{
		return id_fechaFinAlerta;
	}

	/**
	 * Modifica el valor de FechaFinAlerta.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaFinAlerta(Date ad_d)
	{
		id_fechaFinAlerta = ad_d;
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
	 * @param al_ de al
	 */
	public void setIdAlertaTierras(long al_)
	{
		il_idAlertaTierras = al_;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad.
	 *
	 * @return Retorna el valor de la propiedad idEntidad
	 */
	public long getIdEntidad()
	{
		return il_idEntidad;
	}

	/**
	 * Modifica el valor de IdEntidad.
	 *
	 * @param al_ de al
	 */
	public void setIdEntidad(long al_)
	{
		il_idEntidad = al_;
	}

	/**
	 * Retorna Objeto o variable de valor nom entidad.
	 *
	 * @return Retorna el valor de la propiedad nomEntidad
	 */
	public String getNomEntidad()
	{
		return is_nomEntidad;
	}

	/**
	 * Modifica el valor de NomEntidad.
	 *
	 * @param as_s de as s
	 */
	public void setNomEntidad(String as_s)
	{
		is_nomEntidad = as_s;
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
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return Retorna el valor de la propiedad idMatricula
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
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
	 * Retorna Objeto o variable de valor nom comunidad etnica.
	 *
	 * @return Retorna el valor de la propiedad nomComunidadEtnica
	 */
	public String getNomComunidadEtnica()
	{
		return is_nomComunidadEtnica;
	}

	/**
	 * Modifica el valor de NomComunidadEtnica.
	 *
	 * @param as_s de as s
	 */
	public void setNomComunidadEtnica(String as_s)
	{
		is_nomComunidadEtnica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inscripcion.
	 *
	 * @return Retorna el valor de la propiedad fechaInscripcion
	 */
	public Date getFechaInscripcion()
	{
		return id_fechaInscripcion;
	}

	/**
	 * Modifica el valor de FechaInscripcion.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaInscripcion(Date ad_d)
	{
		id_fechaInscripcion = ad_d;
	}
}
