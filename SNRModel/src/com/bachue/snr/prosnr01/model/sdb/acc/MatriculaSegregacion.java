package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.UnidadMedidaAreaCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_MATRICULA_SEGREGACION.
 *
 * @author garias
 */
public class MatriculaSegregacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8756656916140370002L;

	/** Propiedad id area construida. */
	private Double id_areaConstruida;

	/** Propiedad id area privada. */
	private Double id_areaPrivada;

	/** Propiedad id area terreno. */
	private Double id_areaTerreno;

	/** Propiedad id coeficiente. */
	private Double id_coeficiente;

	/** Propiedad il cantidad certificados. */
	private Long il_cantidadCertificados;

	/** Propiedad il matricula matriz. */
	private Long il_matriculaMatriz;

	/** Propiedad il matricula temporal. */
	private Long il_matriculaTemporal;

	/** Propiedad il unidad. */
	private Long il_unidad;

	/** Propiedad is area construida lectura. */
	private String is_areaConstruidaLectura;

	/** Propiedad is area privada lectura. */
	private String is_areaPrivadaLectura;

	/** Propiedad is area terreno lectura. */
	private String is_areaTerrenoLectura;

	/** Propiedad is coeficiente lectura. */
	private String is_coeficienteLectura;

	/** Propiedad is complemento direccion. */
	private String is_complementoDireccion;

	/** Propiedad is direccion. */
	private String is_direccion;

	/** Propiedad id anotacion apertura. */
	private String is_idAnotacionApertura;

	/** Propiedad is id circulo matriz. */
	private String is_idCirculoMatriz;

	/** Propiedad is id circulo temporal. */
	private String is_idCirculoTemporal;

	/** Propiedad is id predio registro. */
	private String is_idPredioRegistro;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id user. */
	private String is_idUser;

	/** Propiedad is matricula segregacion. */
	private String is_matriculaSegregacion;

	/** Propiedad is nombre predio. */
	private String is_nombrePredio;

	/** Propiedad is tipo suelo. */
	private String is_tipoSuelo;

	/** Propiedad is turno certificado. */
	private String is_turnoCertificado;

	/** Propiedad is usuario gestion. */
	private String is_usuarioGestion;

	/** Propiedad ib cementerio. */
	private boolean ib_cementerio;

	/** Propiedad ib certificado asociado. */
	private boolean ib_certificadoAsociado;

	/** Propiedad ib modificacion. */
	private boolean ib_modificacion;

	/**
	 * Constructor predio registro.
	 *
	 * @param as_idPredioRegistro de as id predio registro
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param as_idSolicitud de as id solicitud
	 * @param as_usuario de as usuario
	 * @param as_ipRemota de as ip remota
	 */
	public MatriculaSegregacion(
	    String as_idPredioRegistro, String as_idCirculo, Long al_idMatricula, String as_idSolicitud, String as_usuario,
	    String as_ipRemota
	)
	{
		setIdPredioRegistro(as_idPredioRegistro);
		setIdCirculoTemporal(as_idCirculo);
		setMatriculaTemporal(al_idMatricula);
		setIdSolicitud(as_idSolicitud);
		setIdUsuarioModificacion(as_usuario);
		setIpModificacion(as_ipRemota);
	}

	/**
	 * Constructor por defecto.
	 */
	public MatriculaSegregacion()
	{
	}

	/**
	 * Constructor que recibe como parametro un objeto tipo SolicitudMatricula.
	 *
	 * @param asm_sm Solicitud Matricula
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula
	 */
	public MatriculaSegregacion(SolicitudMatricula asm_sm)
	{
		if(asm_sm != null)
		{
			setIdSolicitud(asm_sm.getIdSolicitud());
			setIdCirculoMatriz(asm_sm.getIdCirculo());
			setMatriculaMatriz(NumericUtils.getLongWrapper(asm_sm.getIdMatricula()));
		}
	}

	/**
	 * Modifica el valor de AreaConstruida.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setAreaConstruida(Double ad_d)
	{
		id_areaConstruida = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area construida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getAreaConstruida()
	{
		return id_areaConstruida;
	}

	/**
	 * Modifica el valor de AreaConstruidaLectura.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAreaConstruidaLectura(String as_s)
	{
		is_areaConstruidaLectura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor area construida lectura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAreaConstruidaLectura()
	{
		return is_areaConstruidaLectura;
	}

	/**
	 * Modifica el valor de AreaPrivada.
	 *
	 * @param ad_d de ad d
	 */
	public void setAreaPrivada(Double ad_d)
	{
		id_areaPrivada = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area privada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getAreaPrivada()
	{
		return id_areaPrivada;
	}

	/**
	 * Modifica el valor de AreaPrivadaLectura.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAreaPrivadaLectura(String as_s)
	{
		is_areaPrivadaLectura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor area privada lectura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAreaPrivadaLectura()
	{
		return is_areaPrivadaLectura;
	}

	/**
	 * Modifica el valor de AreaTerreno.
	 *
	 * @param ad_d de ad d
	 */
	public void setAreaTerreno(Double ad_d)
	{
		id_areaTerreno = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area terreno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getAreaTerreno()
	{
		return id_areaTerreno;
	}

	/**
	 * Modifica el valor de AreaTerrenoLectura.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAreaTerrenoLectura(String as_s)
	{
		is_areaTerrenoLectura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor area terreno lectura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAreaTerrenoLectura()
	{
		return is_areaTerrenoLectura;
	}

	/**
	 * Modifica el valor de CantidadCertificados.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCantidadCertificados(Long al_l)
	{
		il_cantidadCertificados = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad certificados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getCantidadCertificados()
	{
		return il_cantidadCertificados;
	}

	/**
	 * Modifica el valor de Cementerio.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCementerio(boolean ab_b)
	{
		ib_cementerio = ab_b;
	}

	/**
	 * Valida la propiedad cementerio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCementerio()
	{
		return ib_cementerio;
	}

	/**
	 * Modifica el valor de CertificadoAsociado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCertificadoAsociado(boolean ab_b)
	{
		ib_certificadoAsociado = ab_b;
	}

	/**
	 * Valida la propiedad certificado asociado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCertificadoAsociado()
	{
		return ib_certificadoAsociado;
	}

	/**
	 * Modifica el valor de Coeficiente.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setCoeficiente(Double ad_d)
	{
		id_coeficiente = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor coeficiente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getCoeficiente()
	{
		return id_coeficiente;
	}

	/**
	 * Modifica el valor de CoeficienteLectura.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCoeficienteLectura(String as_s)
	{
		is_coeficienteLectura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor coeficiente lectura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCoeficienteLectura()
	{
		return is_coeficienteLectura;
	}

	/**
	 * Modifica el valor de ComplementoDireccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComplementoDireccion(String as_s)
	{
		is_complementoDireccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor complemento direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComplementoDireccion()
	{
		return is_complementoDireccion;
	}

	/**
	 * Modifica el valor de Direccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * @param Modifica el valor de la propiedad idAnotacionApertura por idAnotacionApertura
	 */
	public void setIdAnotacionApertura(String as_s)
	{
		is_idAnotacionApertura = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idAnotacionApertura
	 */
	public String getIdAnotacionApertura()
	{
		return is_idAnotacionApertura;
	}

	/**
	 * Modifica el valor de IdCirculoMatriz.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoMatriz(String as_s)
	{
		is_idCirculoMatriz = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo matriz.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoMatriz()
	{
		return is_idCirculoMatriz;
	}

	/**
	 * Modifica el valor de IdCirculoTemporal.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculoTemporal(String as_s)
	{
		is_idCirculoTemporal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo temporal.
	 *
	 * @return el valor de id circulo temporal
	 */
	public String getIdCirculoTemporal()
	{
		return is_idCirculoTemporal;
	}

	/**
	 * Modifica el valor de IdPredioRegistro.
	 *
	 * @param as_s de as s
	 */
	public void setIdPredioRegistro(String as_s)
	{
		is_idPredioRegistro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id predio registro.
	 *
	 * @return el valor de id predio registro
	 */
	public String getIdPredioRegistro()
	{
		return is_idPredioRegistro;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
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
	 * @param Modifica el valor de la propiedad idTurno por idTurno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdUnidadMedida.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUnidadMedida(String as_s)
	{
	}

	/**
	 * Retorna Objeto o variable de valor id unidad medida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUnidadMedida()
	{
		return UnidadMedidaAreaCommon.METROS_CUADRADOS;
	}

	/**
	 * Modifica el valor de IdUser.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUser(String as_s)
	{
		is_idUser = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id user.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUser()
	{
		return is_idUser;
	}

	/**
	 * Modifica el valor de MatriculaMatriz.
	 *
	 * @param al_l de al l
	 */
	public void setMatriculaMatriz(Long al_l)
	{
		il_matriculaMatriz = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor matricula matriz.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getMatriculaMatriz()
	{
		return il_matriculaMatriz;
	}

	/**
	 * Modifica el valor de MatriculaSegregacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMatriculaSegregacion(String as_s)
	{
		is_matriculaSegregacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor matricula segregacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMatriculaSegregacion()
	{
		return is_matriculaSegregacion;
	}

	/**
	 * Modifica el valor de MatriculaTemporal.
	 *
	 * @param al_l de al l
	 */
	public void setMatriculaTemporal(Long al_l)
	{
		il_matriculaTemporal = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor matricula temporal.
	 *
	 * @return el valor de matricula temporal
	 */
	public Long getMatriculaTemporal()
	{
		return il_matriculaTemporal;
	}

	/**
	 * Modifica el valor de Modificacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setModificacion(boolean ab_b)
	{
		ib_modificacion = ab_b;
	}

	/**
	 * Valida la propiedad modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isModificacion()
	{
		return ib_modificacion;
	}

	/**
	 * Modifica el valor de NombrePredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombrePredio(String as_s)
	{
		is_nombrePredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePredio()
	{
		return is_nombrePredio;
	}

	/**
	 * Modifica el valor de TipoSuelo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoSuelo(String as_s)
	{
		is_tipoSuelo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo suelo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoSuelo()
	{
		return is_tipoSuelo;
	}

	/**
	 * Modifica el valor de TurnoCertificado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTurnoCertificado(String as_s)
	{
		is_turnoCertificado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTurnoCertificado()
	{
		return is_turnoCertificado;
	}

	/**
	 * Modifica el valor de Unidad.
	 *
	 * @param al_l de al l
	 */
	public void setUnidad(Long al_l)
	{
		il_unidad = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor unidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getUnidad()
	{
		return il_unidad;
	}

	/**
	 * Modifica el valor de UsuarioGestion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuarioGestion(String as_s)
	{
		is_usuarioGestion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario gestion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuarioGestion()
	{
		return is_usuarioGestion;
	}
}
