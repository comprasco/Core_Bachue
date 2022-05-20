package com.bachue.snr.prosnr01.model.sdb.aut;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_AUT_USUARIO.
 *
 * @author Julian Vaca
 */
public class Usuario extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7424062662342101313L;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad id fecha vigencia segunda clave. */
	private Date id_fechaVigenciaSegundaClave;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is cargo. */
	private String is_cargo;

	/** Propiedad is circulos registrales. */
	private String is_circulosRegistrales;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is correo electronico corporativo. */
	private String is_correoElectronicoCorporativo;

	/** Propiedad is etapas. */
	private String is_etapas;

	/** Propiedad is id dependencia. */
	private String is_idDependencia;

	/** Propiedad is id documento tipo. */
	private String is_idDocumentoTipo;

	/** Propiedad is id entidad externa. */
	private String is_idEntidadExterna;

	/** Propiedad is id politica. */
	private String is_idPolitica;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is id usuario reasignacion. */
	private String is_idUsuarioReasignacion;

	/** Propiedad is justificacion cambio. */
	private String is_justificacionCambio;

	/** Propiedad is lineas produccion. */
	private String is_lineasProduccion;

	/** Propiedad is numero convenio. */
	private String is_numeroConvenio;

	/** Propiedad is numero documento. */
	private String is_numeroDocumento;

	/** Propiedad is numero radicado solicitud. */
	private String is_numeroRadicadoSolicitud;

	/** Propiedad is primer apellido. */
	private String is_primerApellido;

	/** Propiedad is primer nombre. */
	private String is_primerNombre;

	/** Propiedad is segunda clave. */
	private String is_segundaClave;

	/** Propiedad is segundo apellido. */
	private String is_segundoApellido;

	/** Propiedad is segundo factor autenticacion. */
	private String is_segundoFactorAutenticacion;

	/** Propiedad is segundo nombre. */
	private String is_segundoNombre;

	/** Propiedad iba minucia huella 1. */
	private byte[] iba_minuciaHuella1;

	/** Propiedad iba minucia huella 2. */
	private byte[] iba_minuciaHuella2;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad ib visitador principal. */
	private boolean ib_visitadorPrincipal;

	/**
	 * Constructor por defecto.
	 */
	public Usuario()
	{
	}

	/**
	 * Constructor que recibe como parametro el id de usuario.
	 *
	 * @param as_s de as id usuario
	 */
	public Usuario(String as_s)
	{
		setIdUsuario(as_s);
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Cargo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCargo(String as_s)
	{
		is_cargo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cargo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCargo()
	{
		return is_cargo;
	}

	/**
	 * Modifica el valor de CirculosRegistrales.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCirculosRegistrales(String as_s)
	{
		is_circulosRegistrales = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor circulos registrales.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCirculosRegistrales()
	{
		return is_circulosRegistrales;
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
	 * Modifica el valor de CorreoElectronicoCorporativo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCorreoElectronicoCorporativo(String as_s)
	{
		is_correoElectronicoCorporativo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico corporativo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCorreoElectronicoCorporativo()
	{
		return is_correoElectronicoCorporativo;
	}

	/**
	 * Modifica el valor de Etapas.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEtapas(String as_s)
	{
		is_etapas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor etapas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEtapas()
	{
		return is_etapas;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de FechaHasta.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de FechaVigenciaSegundaClave.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaVigenciaSegundaClave(Date ad_d)
	{
		id_fechaVigenciaSegundaClave = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha vigencia segunda clave.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaVigenciaSegundaClave()
	{
		return id_fechaVigenciaSegundaClave;
	}

	/**
	 * Modifica el valor de IdDependencia.
	 *
	 * @param as_s de as s
	 */
	public void setIdDependencia(String as_s)
	{
		is_idDependencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id dependencia.
	 *
	 * @return el valor de id dependencia
	 */
	public String getIdDependencia()
	{
		return is_idDependencia;
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
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEntidadExterna(String as_s)
	{
		is_idEntidadExterna = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad externa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEntidadExterna()
	{
		return is_idEntidadExterna;
	}

	/**
	 * Modifica el valor de IdPolitica.
	 *
	 * @param as_s de as s
	 */
	public void setIdPolitica(String as_s)
	{
		is_idPolitica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id politica.
	 *
	 * @return Retorna el valor de la propiedad idPolitica
	 */
	public String getIdPolitica()
	{
		return is_idPolitica;
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
	 * @param is_idUsuarioReasignacion Modifica el valor de la propiedad is_idUsuarioReasignacion
	 */
	public void setIdUsuarioReasignacion(String as_idUsuarioReasignacion)
	{
		is_idUsuarioReasignacion = as_idUsuarioReasignacion;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idUsuarioReasignacion
	 */
	public String getIdUsuarioReasignacion()
	{
		return is_idUsuarioReasignacion;
	}

	/**
	 * Modifica el valor de JustificacionCambio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setJustificacionCambio(String as_s)
	{
		is_justificacionCambio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor justificacion cambio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getJustificacionCambio()
	{
		return is_justificacionCambio;
	}

	/**
	 * Modifica el valor de LineasProduccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLineasProduccion(String as_s)
	{
		is_lineasProduccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor lineas produccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLineasProduccion()
	{
		return is_lineasProduccion;
	}

	/**
	 * Modifica el valor de MinuciaHuella1.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMinuciaHuella1(byte[] ab_b)
	{
		iba_minuciaHuella1 = ab_b;
	}

	/**
	 * Retorna Objeto o variable de valor minucia huella 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getMinuciaHuella1()
	{
		return iba_minuciaHuella1;
	}

	/**
	 * Modifica el valor de MinuciaHuella2.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMinuciaHuella2(byte[] ab_b)
	{
		iba_minuciaHuella2 = ab_b;
	}

	/**
	 * Retorna Objeto o variable de valor minucia huella 2.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getMinuciaHuella2()
	{
		return iba_minuciaHuella2;
	}

	/**
	 * Retorna Objeto o variable de valor nombre completo.
	 *
	 * @return el valor de nombre completo
	 */
	public String getNombreCompleto()
	{
		StringBuilder lsb_builder;

		lsb_builder = new StringBuilder(StringUtils.getStringNotNull(is_primerNombre));

		if(StringUtils.isValidString(is_segundoNombre))
		{
			lsb_builder.append(IdentificadoresCommon.ESPACIO_VACIO);
			lsb_builder.append(StringUtils.getStringNotNull(is_segundoNombre));
		}

		lsb_builder.append(IdentificadoresCommon.ESPACIO_VACIO);
		lsb_builder.append(StringUtils.getStringNotNull(is_primerApellido));

		if(StringUtils.isValidString(is_segundoApellido))
		{
			lsb_builder.append(IdentificadoresCommon.ESPACIO_VACIO);
			lsb_builder.append(StringUtils.getStringNotNull(is_segundoApellido));
		}

		return lsb_builder.toString();
	}

	/**
	 * Modifica el valor de NumeroConvenio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroConvenio(String as_s)
	{
		is_numeroConvenio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero convenio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroConvenio()
	{
		return is_numeroConvenio;
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
	 * Modifica el valor de NumeroRadicadoSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroRadicadoSolicitud(String as_s)
	{
		is_numeroRadicadoSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero radicado solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroRadicadoSolicitud()
	{
		return is_numeroRadicadoSolicitud;
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
	 * Modifica el valor de SegundaClave.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSegundaClave(String as_s)
	{
		is_segundaClave = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segunda clave.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSegundaClave()
	{
		return is_segundaClave;
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
	 * Modifica el valor de SegundoFactorAutenticacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSegundoFactorAutenticacion(String as_s)
	{
		is_segundoFactorAutenticacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segundo factor autenticacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSegundoFactorAutenticacion()
	{
		return is_segundoFactorAutenticacion;
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
	 * @param ab_b de ab b
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * @param ib_visitadorPrincipal Modifica el valor de la propiedad ib_visitadorPrincipal
	 */
	public void setVisitadorPrincipal(boolean ab_vp)
	{
		ib_visitadorPrincipal = ab_vp;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_visitadorPrincipal
	 */
	public boolean isVisitadorPrincipal()
	{
		return ib_visitadorPrincipal;
	}
}
