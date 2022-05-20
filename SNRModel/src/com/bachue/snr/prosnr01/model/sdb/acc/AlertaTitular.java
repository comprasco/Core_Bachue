package com.bachue.snr.prosnr01.model.sdb.acc;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoEntradaActualizarContactoAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoEntradaActualizarContactoAlertaTipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaEstado;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaTipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoEntradaConsultarTarifaAlertasTitulares;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlertaTipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoEntradaValidarSolicitudAlertaIndividual;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoEntradaValidarSolicitudAlertaIndividualTipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasiva;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasivaTipoDocumento;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_ALERTA_TITULAR.
 *
 * @author Julian Vaca
 */
public class AlertaTitular extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID      = 7324364990222804516L;
	
	/** Propiedad id fecha fin alerta. */
	private Date              id_fechaFinAlerta;
	
	/** Propiedad id fecha inscripcion. */
	private Date              id_fechaInscripcion;
	
	/** Propiedad ip persona. */
	private Persona           ip_persona;
	
	/** Propiedad is codigo. */
	private String            is_codigo;
	
	/** Propiedad is descripcion mensaje. */
	private String            is_descripcionMensaje;
	
	/** Propiedad is estado alerta. */
	private String            is_estadoAlerta;
	
	/** Propiedad is id alerta titular. */
	private String            is_idAlertaTitular;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id departamento. */
	private String            is_idDepartamento;
	
	/** Propiedad is id matricula. */
	private String            is_idMatricula;
	
	/** Propiedad is id municipio. */
	private String            is_idMunicipio;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is modulo. */
	private String            is_modulo;

	/**
	 * Constructor por defecto.
	 */
	public AlertaTitular()
	{
	}

	/**
	 * Constructor que recibe por parametro Tipo Entrada Validar Solicitud Alerta Masiva.
	 *
	 * @param atevsam_param objeto Tipo Entrada Validar Solicitud Alerta Masiva
	 * @see co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasiva
	 */
	public AlertaTitular(TipoEntradaValidarSolicitudAlertaMasiva atevsam_param)
	{
		if(atevsam_param != null)
		{
			Persona lp_persona;

			lp_persona                              = new Persona();

			{
				TipoEntradaValidarSolicitudAlertaMasivaTipoDocumento ltevsamtd_tipoDocumento;

				ltevsamtd_tipoDocumento = atevsam_param.getTipoDocumento();

				if(ltevsamtd_tipoDocumento != null)
					lp_persona.setIdDocumentoTipo(ltevsamtd_tipoDocumento.getValue());
			}

			lp_persona.setNumeroDocumento(atevsam_param.getNumeroDocumento());

			ip_persona       = lp_persona;
			is_idCirculo     = atevsam_param.getOripSecuencia();
		}
	}

	/**
	 * Constructor que recibe como parametro el modulo, id circulo, id matricula y persona.
	 *
	 * @param as_modulo modulo
	 * @param as_idCirculo id circulo
	 * @param as_idMatricula id matricula
	 * @param ap_persona objeto persona
	 */
	public AlertaTitular(String as_modulo, String as_idCirculo, String as_idMatricula, Persona ap_persona)
	{
		is_modulo          = as_modulo;
		is_idCirculo       = as_idCirculo;
		is_idMatricula     = as_idMatricula;
		ip_persona         = ap_persona;
	}

	/**
	 * Constructor que recibe por parametro Tipo Entrada Actualizar Contacto Alerta.
	 *
	 * @param ateaca_param objeto Tipo Entrada Actualizar Contacto Alerta
	 * @see co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoEntradaActualizarContactoAlerta
	 */
	public AlertaTitular(TipoEntradaActualizarContactoAlerta ateaca_param)
	{
		if(ateaca_param != null)
		{
			Persona lp_persona;

			lp_persona = new Persona();

			{
				TipoEntradaActualizarContactoAlertaTipoDocumento lteacatd_tipoDocumento;

				lteacatd_tipoDocumento = ateaca_param.getTipoDocumento();

				if(lteacatd_tipoDocumento != null)
					lp_persona.setIdDocumentoTipo(lteacatd_tipoDocumento.getValue());
			}

			lp_persona.setNumeroDocumento(ateaca_param.getNumeroDocumento());

			ip_persona = lp_persona;
		}
	}

	/**
	 * Constructor que recibe por parametro Tipo Entrada Consultar Alerta.
	 *
	 * @param ateca_parametros objeto Tipo Entrada Consultar Alerta
	 * @see co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlerta
	 */
	public AlertaTitular(TipoEntradaConsultarAlerta ateca_parametros)
	{
		if(ateca_parametros != null)
		{
			Persona lp_persona;

			lp_persona = new Persona();

			{
				TipoEntradaConsultarAlertaTipoDocumento ltecatd_tipoDoc;

				ltecatd_tipoDoc = ateca_parametros.getTipoDocumento();

				if(ltecatd_tipoDoc != null)
					lp_persona.setIdDocumentoTipo(ltecatd_tipoDoc.getValue());
			}

			lp_persona.setNumeroDocumento(ateca_parametros.getNumeroDocumento());

			ip_persona            = lp_persona;
			is_idCirculo          = ateca_parametros.getOrip();
			is_idMatricula        = ateca_parametros.getNumeroMatricula();
			is_idDepartamento     = ateca_parametros.getDepartamento();
			is_idMunicipio        = ateca_parametros.getMunicipio();

			{
				TipoEntradaConsultarAlertaEstado ltecae_alertaEstado;

				ltecae_alertaEstado = ateca_parametros.getEstado();

				if(ltecae_alertaEstado != null)
					is_estadoAlerta = ltecae_alertaEstado.getValue();
			}
		}
	}

	/**
	 * Constructor que recibe por parametro Tipo Entrada Consultar TarifaAlertas Titulares.
	 *
	 * @param atectat_parametros objeto Tipo Entrada Consultar TarifaAlertas Titulares
	 * @see co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoEntradaConsultarTarifaAlertasTitulares
	 */
	public AlertaTitular(TipoEntradaConsultarTarifaAlertasTitulares atectat_parametros)
	{
		if(atectat_parametros != null)
		{
			Persona lp_persona;

			lp_persona = new Persona();

			lp_persona.setIdDocumentoTipo(atectat_parametros.getTipoDocumento());
			lp_persona.setNumeroDocumento(atectat_parametros.getNumeroDocumento());

			ip_persona = lp_persona;
		}
	}

	/**
	 * Constructor que recibe por parametro Tipo Entrada Inactivar Alerta.
	 *
	 * @param ateia_param objeto Tipo Entrada Inactivar Alerta
	 * @see co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlerta
	 */
	public AlertaTitular(TipoEntradaInactivarAlerta ateia_param)
	{
		if(ateia_param != null)
		{
			Persona lp_persona;

			lp_persona = new Persona();

			{
				TipoEntradaInactivarAlertaTipoDocumento lteiatd_tipoDocumento;

				lteiatd_tipoDocumento = ateia_param.getTipoDocumento();

				if(lteiatd_tipoDocumento != null)
					lp_persona.setIdDocumentoTipo(lteiatd_tipoDocumento.getValue());
			}

			lp_persona.setNumeroDocumento(ateia_param.getNumeroDocumento());

			ip_persona             = lp_persona;
			is_idAlertaTitular     = ateia_param.getIdentificadorAlerta();
		}
	}

	/**
	 * Constructor que recibe por parametro Tipo Entrada Validar Solicitud Alerta Individual.
	 *
	 * @param atevsai_parametros objeto Tipo Entrada Validar Solicitud Alerta Individual
	 * @see co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoEntradaValidarSolicitudAlertaIndividual
	 */
	public AlertaTitular(TipoEntradaValidarSolicitudAlertaIndividual atevsai_parametros)
	{
		if(atevsai_parametros != null)
		{
			Persona lp_persona;

			lp_persona = new Persona();

			{
				TipoEntradaValidarSolicitudAlertaIndividualTipoDocumento ltevsaitd_tipoDocumento;

				ltevsaitd_tipoDocumento = atevsai_parametros.getTipoDocumento();

				if(ltevsaitd_tipoDocumento != null)
					lp_persona.setIdDocumentoTipo(ltevsaitd_tipoDocumento.getValue());
			}

			lp_persona.setNumeroDocumento(atevsai_parametros.getNumeroDocumento());

			ip_persona     = lp_persona;

			is_modulo          = atevsai_parametros.getModulo();
			is_idCirculo       = atevsai_parametros.getOrip();
			is_idMatricula     = atevsai_parametros.getNumeroMatricula();
		}
	}

	/**
	 * Modifica el valor de Codigo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigo(String as_s)
	{
		is_codigo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Modifica el valor de DescripcionMensaje.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionMensaje(String as_s)
	{
		is_descripcionMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion mensaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionMensaje()
	{
		return is_descripcionMensaje;
	}

	/**
	 * Modifica el valor de EstadoAlerta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoAlerta(String as_s)
	{
		is_estadoAlerta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado alerta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoAlerta()
	{
		return is_estadoAlerta;
	}

	/**
	 * Modifica el valor de FechaFinAlerta.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaFinAlerta(Date ad_d)
	{
		id_fechaFinAlerta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha fin alerta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaFinAlerta()
	{
		return id_fechaFinAlerta;
	}

	/**
	 * Modifica el valor de FechaInscripcion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaInscripcion(Date ad_d)
	{
		id_fechaInscripcion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inscripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaInscripcion()
	{
		return id_fechaInscripcion;
	}

	/**
	 * Modifica el valor de IdAlertaTitular.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAlertaTitular(String as_s)
	{
		is_idAlertaTitular = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id alerta titular.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAlertaTitular()
	{
		return is_idAlertaTitular;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMatricula(String as_s)
	{
		is_idMatricula = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMatricula()
	{
		return is_idMatricula;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de Modulo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setModulo(String as_s)
	{
		is_modulo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor modulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getModulo()
	{
		return is_modulo;
	}

	/**
	 * Modifica el valor de Persona.
	 *
	 * @param ap_p de ap p
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna Objeto o variable de valor persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Persona getPersona()
	{
		return ip_persona;
	}
}
