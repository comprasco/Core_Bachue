package com.bachue.snr.prosnr01.model.sdb.acc;

import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoRepresentanteLegalAEI;

import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedades;
import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios;
import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoDocumentoPersona;
import co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.PropietarioDTO;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.DocumentoTipoIPEECommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_PERSONA.
 *
 * @author Julian Vaca
 */
public class Persona extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4224365365526884419L;

	/** Propiedad ica intervinientes. */
	private Collection<AnotacionPredioCiudadano> ica_intervinientes;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad isi solicitud interviniente. */
	private SolicitudInterviniente isi_solicitudInterviniente;

	/** Propiedad is calidad solicitante. */
	private String is_calidadSolicitante;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is entidad exenta. */
	private String is_entidadExenta;

	/** Propiedad is error persona masiva. */
	private String is_errorPersonaMasiva;

	/** Propiedad is id documento tipo. */
	private String is_idDocumentoTipo;

	/** Propiedad is id entidad externa. */
	private String is_idEntidadExterna;

	/** Propiedad is id natural genero. */
	private String is_idNaturalGenero;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id persona. */
	private String is_idPersona;

	/** Propiedad is id tipo persona. */
	private String is_idTipoPersona;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is medio notificacion. */
	private String is_medioNotificacion;

	/** Propiedad is nombre completo. */
	private String is_nombreCompleto;

	/** Propiedad is nombre tipo persona. */
	private String is_nombreTipoPersona;

	/** Propiedad is numero documento. */
	private String is_numeroDocumento;

	/** Propiedad is origen bachue. */
	private String is_origenBachue;

	/** Propiedad is primer apellido. */
	private String is_primerApellido;

	/** Propiedad is primer nombre. */
	private String is_primerNombre;

	/** Propiedad is razon social. */
	private String is_razonSocial;

	/** Propiedad is representante legal. */
	private String is_representanteLegal;

	/** Propiedad is segundo apellido. */
	private String is_segundoApellido;

	/** Propiedad is segundo nombre. */
	private String is_segundoNombre;

	/** Propiedad is tipo doc identidad. */
	private String is_tipoDocIdentidad;

	/** Propiedad is usuario. */
	private String is_usuario;

	/** Propiedad is usuario cargue. */
	private String is_usuarioCargue;

	/** Propiedad ib error cargue. */
	private boolean ib_errorCargue;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Constructor por defecto.
	 */
	public Persona()
	{
	}

	/**
	 * Instancia un nuevo objeto persona.
	 *
	 * @param as_tipoDocumento de as tipo documento
	 * @param as_numeroDocumento de as numero documento
	 */
	public Persona(String as_tipoDocumento, String as_numeroDocumento)
	{
		is_idDocumentoTipo     = as_tipoDocumento;
		is_numeroDocumento     = as_numeroDocumento;
	}

	/**
	 * Instancia un nuevo objeto persona.
	 *
	 * @param as_tipoDocumento correspondiente al valor de tipo documento
	 * @param as_numeroDocumento correspondiente al valor de numero documento
	 * @param as_tipoPersona correspondiente al valor de tipo persona
	 */
	public Persona(String as_tipoDocumento, String as_numeroDocumento, String as_tipoPersona)
	{
		is_idDocumentoTipo     = as_tipoDocumento;
		is_numeroDocumento     = as_numeroDocumento;
		is_idTipoPersona       = as_tipoPersona;
	}

	/**
	 * Constructor que recibe como parametro el id de la persona.
	 *
	 * @param as_idPersona id persona
	 */
	public Persona(String as_idPersona)
	{
		is_idPersona = as_idPersona;
	}

	/**
	 * Constructor que recibe como parametro un objeto tipo Persona.
	 *
	 * @param ap_p persona
	 */
	public Persona(Persona ap_p)
	{
		if(ap_p != null)
		{
			ica_intervinientes        = ap_p.getIntervinientes();
			is_idDocumentoTipo        = ap_p.getIdDocumentoTipo();
			is_idNaturalGenero        = ap_p.getIdNaturalGenero();
			is_idPais                 = ap_p.getIdPais();
			is_idPersona              = ap_p.getIdPersona();
			is_idTipoPersona          = ap_p.getIdTipoPersona();
			is_idUsuario              = ap_p.getIdUsuario();
			is_nombreCompleto         = ap_p.getNombreCompleto();
			is_numeroDocumento        = ap_p.getNumeroDocumento();
			is_primerApellido         = ap_p.getPrimerApellido();
			is_primerNombre           = ap_p.getPrimerNombre();
			is_razonSocial            = ap_p.getRazonSocial();
			is_segundoApellido        = ap_p.getSegundoApellido();
			is_segundoNombre          = ap_p.getSegundoNombre();
			is_tipoDocIdentidad       = ap_p.getTipoDocIdentidad();
			ib_seleccionado           = ap_p.isSeleccionado();
			is_medioNotificacion      = ap_p.getMedioNotificacion();
			is_calidadSolicitante     = ap_p.getCalidadSolicitante();
		}
	}

	/**
	 * Constructor para inicializar el objeto a partir de un objeto TipoRepresentanteLegalAEI.
	 *
	 * @param atrlaei_representante Objeto contenedor de los valores a asociar a la instancia de Persona
	 */
	public Persona(TipoRepresentanteLegalAEI atrlaei_representante)
	{
		if(atrlaei_representante != null)
		{
			String ls_tipoDocumento;

			ls_tipoDocumento     = StringUtils.getStringNotNull(
				    atrlaei_representante.getTipoDocumentoRepresentanteLegal()
				);

			is_idDocumentoTipo     = ls_tipoDocumento;
			is_numeroDocumento     = atrlaei_representante.getNumeroDocumentoRepresentanteLegal();
			is_idTipoPersona       = ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT) ? EstadoCommon.J
				                                                                                  : EstadoCommon.N;
			is_primerApellido      = atrlaei_representante.getPrimerApellido();
			is_primerNombre        = atrlaei_representante.getPrimerNombre();
			is_segundoApellido     = atrlaei_representante.getSegundoApellido();
			is_segundoNombre       = atrlaei_representante.getSegundoNombre();
		}
	}

	/**
	 * Instancia un nuevo objeto persona.
	 *
	 * @param ateip_param de ateip param
	 */
	public Persona(TipoEntradaIndicePropietarios ateip_param)
	{
		if(ateip_param != null)
		{
			TipoEntradaIndicePropietariosTipoDocumentoPersona ls_tipoDocumentoPersona;

			ls_tipoDocumentoPersona = ateip_param.getTipoDocumentoPersona();

			if(ls_tipoDocumentoPersona != null)
			{
				is_tipoDocIdentidad     = ls_tipoDocumentoPersona.getValue();
				is_numeroDocumento      = ateip_param.getNumDocumentoPersona();
			}
			else
			{
				is_tipoDocIdentidad     = IdentificadoresCommon.NIT;
				is_numeroDocumento      = ateip_param.getNumNIT();
			}

			is_primerApellido      = ateip_param.getPrimerApellido();
			is_primerNombre        = ateip_param.getPrimerNombre();
			is_segundoApellido     = ateip_param.getSegundoApellido();
			is_segundoNombre       = ateip_param.getSegundoNombre();
			is_razonSocial         = ateip_param.getRazonSocial();
		}
	}

	/**
	 * Instancia un nuevo objeto persona.
	 *
	 * @param atechp_entrada de atechp entrada
	 * @param lb_busquedaNIT de lb busqueda NIT
	 */
	public Persona(TipoEntradaConsultarHistoricoPropiedades atechp_entrada, boolean lb_busquedaNIT)
	{
		if(atechp_entrada != null)
		{
			if(lb_busquedaNIT)
			{
				is_numeroDocumento     = atechp_entrada.getNumNIT();
				is_razonSocial         = atechp_entrada.getRazonSocial();
			}
			else
			{
				is_idDocumentoTipo     = atechp_entrada.getTipoDocumentoPersona().getValue();
				is_numeroDocumento     = atechp_entrada.getNumDocumentoPersona();
				is_primerApellido      = atechp_entrada.getPrimerApellido();
				is_primerNombre        = atechp_entrada.getPrimerNombre();
				is_segundoApellido     = atechp_entrada.getSegundoApellido();
				is_segundoNombre       = atechp_entrada.getSegundoNombre();
			}
		}
	}

	/**
	 * Instancia un nuevo objeto persona.
	 *
	 * @param lpdto_tmp de lpdto tmp
	 */
	public Persona(PropietarioDTO lpdto_tmp)
	{
		if(lpdto_tmp != null)
		{
			String ls_numNIT;

			ls_numNIT     = lpdto_tmp.getNumNIT();

			is_numeroDocumento = lpdto_tmp.getNumDocumentoPersona();

			if(StringUtils.isValidString(ls_numNIT))
			{
				is_razonSocial          = lpdto_tmp.getNomPropietario();
				is_idDocumentoTipo      = IdentificadoresCommon.NIT;
				is_tipoDocIdentidad     = IdentificadoresCommon.NIT;
				is_idTipoPersona        = EstadoCommon.J;
			}
			else
			{
				String ls_nombreTipoDocumento;

				ls_nombreTipoDocumento     = lpdto_tmp.getNomTipoDocumento();
				is_idTipoPersona           = EstadoCommon.N;

				if(StringUtils.isValidString(ls_nombreTipoDocumento))
				{
					if(ls_nombreTipoDocumento.equalsIgnoreCase(DocumentoTipoIPEECommon.CC))
						ls_nombreTipoDocumento = DocumentoTipoIPEECommon.CC_BACHUE;
					else if(ls_nombreTipoDocumento.equalsIgnoreCase(DocumentoTipoIPEECommon.CE))
						ls_nombreTipoDocumento = DocumentoTipoIPEECommon.CE_BACHUE;
					else if(ls_nombreTipoDocumento.equalsIgnoreCase(DocumentoTipoIPEECommon.NUIP))
						ls_nombreTipoDocumento = DocumentoTipoIPEECommon.NUIP;
					else if(ls_nombreTipoDocumento.equalsIgnoreCase(DocumentoTipoIPEECommon.PA))
						ls_nombreTipoDocumento = DocumentoTipoIPEECommon.PA_BACHUE;
					else if(ls_nombreTipoDocumento.equalsIgnoreCase(DocumentoTipoIPEECommon.TI))
						ls_nombreTipoDocumento = DocumentoTipoIPEECommon.TI_BACHUE;

					is_idDocumentoTipo      = ls_nombreTipoDocumento;
					is_tipoDocIdentidad     = ls_nombreTipoDocumento;
				}

				{
					String ls_nombreCompleto;
					String ls_primerNombre;
					String ls_segundoNombre;
					String ls_primerApellido;
					String ls_segundoApellido;

					ls_nombreCompleto      = lpdto_tmp.getNomPropietario();
					ls_primerNombre        = null;
					ls_segundoNombre       = null;
					ls_primerApellido      = null;
					ls_segundoApellido     = null;

					if(StringUtils.isValidString(ls_nombreCompleto))
					{
						String[] ls_cadenaDatos = ls_nombreCompleto.split(" ");

						for(String ls_tmp : ls_cadenaDatos)
						{
							if(StringUtils.isValidString(ls_tmp))
							{
								if(ls_primerNombre == null)
									ls_primerNombre = ls_tmp;
								else if(ls_segundoNombre == null)
									ls_segundoNombre = ls_tmp;
								else if(ls_primerApellido == null)
									ls_primerApellido = ls_tmp;
								else if(ls_segundoApellido == null)
									ls_segundoApellido = ls_tmp;
							}
						}
					}

					is_primerNombre        = ls_primerNombre;
					is_segundoNombre       = ls_segundoNombre;
					is_primerApellido      = ls_primerApellido;
					is_segundoApellido     = ls_segundoApellido;
				}
			}
		}
	}

	/**
	 * Modifica el valor de CalidadSolicitante.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCalidadSolicitante(String as_s)
	{
		is_calidadSolicitante = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor calidad solicitante.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCalidadSolicitante()
	{
		return is_calidadSolicitante;
	}

	/**
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de EntidadExenta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEntidadExenta(String as_s)
	{
		is_entidadExenta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor entidad exenta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEntidadExenta()
	{
		return is_entidadExenta;
	}

	/**
	 * Modifica el valor de ErrorCargue.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setErrorCargue(boolean ab_b)
	{
		ib_errorCargue = ab_b;
	}

	/**
	 * Valida la propiedad error cargue.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isErrorCargue()
	{
		return ib_errorCargue;
	}

	/**
	 * Modifica el valor de ErrorPersonaMasiva.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setErrorPersonaMasiva(String as_s)
	{
		is_errorPersonaMasiva = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor error persona masiva.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getErrorPersonaMasiva()
	{
		return is_errorPersonaMasiva;
	}

	/**
	 * Modifica el valor de IdDocumentoTipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumentoTipo(String as_s)
	{
		is_idDocumentoTipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumentoTipo()
	{
		return is_idDocumentoTipo;
	}

	/**
	 * Modifica el valor de IdEntidadExterna.
	 *
	 * @param as_s de as s
	 */
	public void setIdEntidadExterna(String as_s)
	{
		is_idEntidadExterna = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad externa.
	 *
	 * @return Retorna el valor de la propiedad idEntidadExterna
	 */
	public String getIdEntidadExterna()
	{
		return is_idEntidadExterna;
	}

	/**
	 * Modifica el valor de IdNaturalGenero.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdNaturalGenero(String as_s)
	{
		is_idNaturalGenero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id natural genero.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdNaturalGenero()
	{
		return is_idNaturalGenero;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de IdPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de IdTipoPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoPersona(String as_s)
	{
		is_idTipoPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPersona()
	{
		return is_idTipoPersona;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de Intervinientes.
	 *
	 * @param aca_ca asigna el valor a la propiedad
	 */
	public void setIntervinientes(Collection<AnotacionPredioCiudadano> aca_ca)
	{
		ica_intervinientes = aca_ca;
	}

	/**
	 * Retorna Objeto o variable de valor intervinientes.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AnotacionPredioCiudadano> getIntervinientes()
	{
		return ica_intervinientes;
	}

	/**
	 * Modifica el valor de MedioNotificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMedioNotificacion(String as_s)
	{
		is_medioNotificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor medio notificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMedioNotificacion()
	{
		return is_medioNotificacion;
	}

	/**
	 * Modifica el valor de NombreCompleto.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreCompleto(String as_s)
	{
		is_nombreCompleto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre completo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCompleto()
	{
		return is_nombreCompleto;
	}

	/**
	 * Modifica el valor de NombreTipoPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreTipoPersona(String as_s)
	{
		is_nombreTipoPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre tipo persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoPersona()
	{
		return is_nombreTipoPersona;
	}

	/**
	 * Modifica el valor de NumeroDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
	}

	/**
	 * Modifica el valor de OrigenBachue.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOrigenBachue(String as_s)
	{
		is_origenBachue = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor origen bachue.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOrigenBachue()
	{
		return is_origenBachue;
	}

	/**
	 * Modifica el valor de PrimerApellido.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPrimerApellido(String as_s)
	{
		is_primerApellido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor primer apellido.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPrimerApellido()
	{
		return is_primerApellido;
	}

	/**
	 * Modifica el valor de PrimerNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPrimerNombre(String as_s)
	{
		is_primerNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor primer nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPrimerNombre()
	{
		return is_primerNombre;
	}

	/**
	 * Modifica el valor de RazonSocial.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRazonSocial(String as_s)
	{
		is_razonSocial = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor razon social.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRazonSocial()
	{
		return is_razonSocial;
	}

	/**
	 * Modifica el valor de representante legal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRepresentanteLegal(String as_s)
	{
		is_representanteLegal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor representante legal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRepresentanteLegal()
	{
		return is_representanteLegal;
	}

	/**
	 * Modifica el valor de SegundoApellido.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSegundoApellido(String as_s)
	{
		is_segundoApellido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segundo apellido.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSegundoApellido()
	{
		return is_segundoApellido;
	}

	/**
	 * Modifica el valor de SegundoNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSegundoNombre(String as_s)
	{
		is_segundoNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segundo nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSegundoNombre()
	{
		return is_segundoNombre;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Modifica el valor de SolicitudInterviniente.
	 *
	 * @param asi_si asigna el valor a la propiedad
	 */
	public void setSolicitudInterviniente(SolicitudInterviniente asi_si)
	{
		isi_solicitudInterviniente = asi_si;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud interviniente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public SolicitudInterviniente getSolicitudInterviniente()
	{
		return isi_solicitudInterviniente;
	}

	/**
	 * Modifica el valor de TipoDocIdentidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoDocIdentidad(String as_s)
	{
		is_tipoDocIdentidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo doc identidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoDocIdentidad()
	{
		return is_tipoDocIdentidad;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuario()
	{
		return is_usuario;
	}

	/**
	 * Modifica el valor de UsuarioCargue.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuarioCargue(String as_s)
	{
		is_usuarioCargue = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario cargue.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuarioCargue()
	{
		return is_usuarioCargue;
	}
}
