package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TESTAMENTO.
 *
 * @author Julian Vaca
 */
public class Testamento extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID           = 8589555668244416754L;
	
	/** Propiedad ibd id nciudadano. */
	private BigDecimal        ibd_idNciudadano;
	
	/** Propiedad ibd id usuario tmp. */
	private BigDecimal        ibd_idUsuarioTmp;
	
	/** Propiedad id fecha documento busqueda. */
	private Date              id_fechaDocumentoBusqueda;
	
	/** Propiedad id fecha registro. */
	private Date              id_fechaRegistro;
	
	/** Propiedad il ano testamento. */
	private Long              il_anoTestamento;
	
	/** Propiedad il folio. */
	private Long              il_folio;
	
	/** Propiedad il libro. */
	private Long              il_libro;
	
	/** Propiedad il tomo. */
	private Long              il_tomo;
	
	/** Propiedad is categoria. */
	private String            is_categoria;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id persona. */
	private String            is_idPersona;
	
	/** Propiedad is id persona busqueda. */
	private String            is_idPersonaBusqueda;
	
	/** Propiedad is id testamento. */
	private String            is_idTestamento;
	
	/** Propiedad is id testamento anterior. */
	private String            is_idTestamentoAnterior;
	
	/** Propiedad is id tipo testamento. */
	private String            is_idTipoTestamento;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is nombre testador. */
	private String            is_nombreTestador;
	
	/** Propiedad is nombre tipo testamento. */
	private String            is_nombreTipoTestamento;
	
	/** Propiedad is num radicacion. */
	private String            is_numRadicacion;
	
	/** Propiedad is numero anotaciones. */
	private String            is_numeroAnotaciones;
	
	/** Propiedad is numero copias. */
	private String            is_numeroCopias;
	
	/** Propiedad is numero documento busqueda. */
	private String            is_numeroDocumentoBusqueda;
	
	/** Propiedad is numero documento persona. */
	private String            is_numeroDocumentoPersona;
	
	/** Propiedad is observacion. */
	private String            is_observacion;
	
	/** Propiedad is primer apellido. */
	private String            is_primerApellido;
	
	/** Propiedad is primer nombre. */
	private String            is_primerNombre;
	
	/** Propiedad is revoca escritura. */
	private String            is_revocaEscritura;
	
	/** Propiedad is segundo apellido. */
	private String            is_segundoApellido;
	
	/** Propiedad is segundo nombre. */
	private String            is_segundoNombre;
	
	/** Propiedad is tipo busqueda. */
	private String            is_tipoBusqueda;
	
	/** Propiedad is tipo documento. */
	private String            is_tipoDocumento;
	
	/** Propiedad is turno. */
	private String            is_turno;
	
	/** Propiedad ib seleccionado. */
	private boolean           ib_seleccionado;

	/**
	 * Retorna Objeto o variable de valor nombre tipo testamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoTestamento()
	{
		return is_nombreTipoTestamento;
	}

	/**
	 * Modifica el valor de NombreTipoTestamento.
	 *
	 * @param as_s de as s
	 */
	public void setNombreTipoTestamento(String as_s)
	{
		is_nombreTipoTestamento                          = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTurno()
	{
		return is_turno;
	}

	/**
	 * Modifica el valor de Turno.
	 *
	 * @param as_s de as s
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
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
	 * Modifica el valor de PrimerNombre.
	 *
	 * @param as_S de as S
	 */
	public void setPrimerNombre(String as_S)
	{
		is_primerNombre = as_S;
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
	 * Modifica el valor de SegundoNombre.
	 *
	 * @param as_S de as S
	 */
	public void setSegundoNombre(String as_S)
	{
		is_segundoNombre = as_S;
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
	 * Modifica el valor de PrimerApellido.
	 *
	 * @param as_s de as s
	 */
	public void setPrimerApellido(String as_s)
	{
		is_primerApellido = as_s;
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
	 * Modifica el valor de SegundoApellido.
	 *
	 * @param as_s de as s
	 */
	public void setSegundoApellido(String as_s)
	{
		is_segundoApellido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero documento busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroDocumentoBusqueda()
	{
		return is_numeroDocumentoBusqueda;
	}

	/**
	 * Modifica el valor de NumeroDocumentoBusqueda.
	 *
	 * @param as_a de as a
	 */
	public void setNumeroDocumentoBusqueda(String as_a)
	{
		is_numeroDocumentoBusqueda = as_a;
	}

	/**
	 * Retorna Objeto o variable de valor fecha documento busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDocumentoBusqueda()
	{
		return id_fechaDocumentoBusqueda;
	}

	/**
	 * Modifica el valor de FechaDocumentoBusqueda.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDocumentoBusqueda(Date ad_d)
	{
		id_fechaDocumentoBusqueda = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor id persona busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersonaBusqueda()
	{
		return is_idPersonaBusqueda;
	}

	/**
	 * Modifica el valor de IdPersonaBusqueda.
	 *
	 * @param as_s de as s
	 */
	public void setIdPersonaBusqueda(String as_s)
	{
		is_idPersonaBusqueda = as_s;
	}

	/**
	 * Modifica el valor de FechaRegistro.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaRegistro(Date ad_d)
	{
		id_fechaRegistro = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
	}

	/**
	 * Modifica el valor de IdNciudadano.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdNciudadano(BigDecimal abd_bd)
	{
		ibd_idNciudadano = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id nciudadano.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdNciudadano()
	{
		return ibd_idNciudadano;
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
	 * Modifica el valor de IdTestamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTestamento(String as_s)
	{
		is_idTestamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id testamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTestamento()
	{
		return is_idTestamento;
	}

	/**
	 * Modifica el valor de IdTestamentoAnterior.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTestamentoAnterior(String as_s)
	{
		is_idTestamentoAnterior = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id testamento anterior.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTestamentoAnterior()
	{
		return is_idTestamentoAnterior;
	}

	/**
	 * Modifica el valor de IdTipoTestamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoTestamento(String as_s)
	{
		is_idTipoTestamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo testamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoTestamento()
	{
		return is_idTipoTestamento;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdUsuarioTmp.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdUsuarioTmp(BigDecimal abd_bd)
	{
		ibd_idUsuarioTmp = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario tmp.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdUsuarioTmp()
	{
		return ibd_idUsuarioTmp;
	}

	/**
	 * Modifica el valor de NumRadicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumRadicacion(String as_s)
	{
		is_numRadicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor num radicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumRadicacion()
	{
		return is_numRadicacion;
	}

	/**
	 * Modifica el valor de NumeroAnotaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroAnotaciones(String as_s)
	{
		is_numeroAnotaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero anotaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroAnotaciones()
	{
		return is_numeroAnotaciones;
	}

	/**
	 * Modifica el valor de NumeroCopias.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroCopias(String as_s)
	{
		is_numeroCopias = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero copias.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroCopias()
	{
		return is_numeroCopias;
	}

	/**
	 * Modifica el valor de Observacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacion(String as_s)
	{
		is_observacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacion()
	{
		return is_observacion;
	}

	/**
	 * Modifica el valor de RevocaEscritura.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRevocaEscritura(String as_s)
	{
		is_revocaEscritura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor revoca escritura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRevocaEscritura()
	{
		return is_revocaEscritura;
	}

	/**
	 * Modifica el valor de Tomo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTomo(Long as_s)
	{
		il_tomo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tomo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getTomo()
	{
		return il_tomo;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getCategoria()
	{
		return is_categoria;
	}

	/**
	 * Método de asignacion del valor de la propiedad.
	 *
	 * @param as_a con el valor a asignar
	 */
	public void setCategoria(String as_a)
	{
		is_categoria = as_a;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public Long getLibro()
	{
		return il_libro;
	}

	/**
	 * Método de asignacion del valor de la propiedad.
	 *
	 * @param as_s de as s
	 */
	public void setLibro(Long as_s)
	{
		il_libro = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public Long getFolio()
	{
		return il_folio;
	}

	/**
	 * Método de asignacion del valor de la propiedad.
	 *
	 * @param al_l de al l
	 */
	public void setFolio(Long al_l)
	{
		il_folio = al_l;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getTipoBusqueda()
	{
		return is_tipoBusqueda;
	}

	/**
	 * Método de asignacion del valor de la propiedad.
	 *
	 * @param as_s de as s
	 */
	public void setTipoBusqueda(String as_s)
	{
		is_tipoBusqueda = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getNombreTestador()
	{
		return is_nombreTestador;
	}

	/**
	 * Método de asignacion del valor de la propiedad.
	 *
	 * @param as_a con el valor a asignar
	 */
	public void setNombreTestador(String as_a)
	{
		is_nombreTestador = as_a;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}

	/**
	 * Método de asignacion del valor de la propiedad.
	 *
	 * @param as_s de as s
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Método de asignacion del valor de la propiedad.
	 *
	 * @param ab_b de ab b
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param as_s con el valor a asignar
	 */
	public void setIIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Método de obtención del  valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public Long getAnoTestamento()
	{
		return il_anoTestamento;
	}

	/**
	 * Méetodo de asignación del valor de la propiedad.
	 *
	 * @param al_l con el valor de la propiedad
	 */
	public void setAnoTestamento(Long al_l)
	{
		il_anoTestamento = al_l;
	}

	/**
	 * Método de obtención del valor de la propeidad  is_numeroDocumentoPersona.
	 *
	 * @return dee tipo String con el valor de la propiedad
	 */
	public String getNumeroDocumentoPersona()
	{
		return is_numeroDocumentoPersona;
	}

	/**
	 * Método de asignacion del valor de la propiedad is_numeroDocumentoPersona.
	 *
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setNumeroDocumentoPersona(String as_s)
	{
		is_numeroDocumentoPersona = as_s;
	}
}
