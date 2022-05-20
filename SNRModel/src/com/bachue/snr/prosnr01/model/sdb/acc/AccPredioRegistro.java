package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_PREDIO_REGISTRO.
 *
 * @author ccalderon
 */
public class AccPredioRegistro extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6196413945903055674L;

	/** Propiedad ilp lindero predio. */
	private AccLinderoPredio ilp_linderoPredio;

	/** Propiedad ibd anotacion cierre. */
	private BigDecimal ibd_anotacionCierre;

	/** Propiedad ibd version documento. */
	private BigDecimal ibd_versionDocumento;

	/** Propiedad icap info matriculas. */
	private Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> icap_infoMatriculas;

	/** Propiedad icdp direcciones predio. */
	private Collection<DireccionDelPredio> icdp_direccionesPredio;

	/** Propiedad icpr info mastriculas. */
	private Collection<AccPredioRegistro> icpr_infoMastriculas;

	/** Propiedad icp info complementacion. */
	private Complementacion icp_infoComplementacion;

	/** Propiedad id fecha apertura. */
	private Date id_fechaApertura;

	/** Propiedad il id complementacion. */
	private Long il_idComplementacion;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is estado grabacion. */
	private String is_estadoGrabacion;

	/** Propiedad is estado predio. */
	private String is_estadoPredio;

	/** Propiedad is folio grabacion. */
	private String is_folioGrabacion;

	/** Propiedad is id antiguo sistema. */
	private String is_idAntiguoSistema;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id datos ant sistema. */
	private String is_idDatosAntSistema;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is id estado nupre. */
	private String is_idEstadoNupre;

	/** Propiedad is id estado predio. */
	private String is_idEstadoPredio;

	/** Propiedad is id predio registro. */
	private String is_idPredioRegistro;

	/** Propiedad is id tipo predio. */
	private String is_idTipoPredio;

	/** Propiedad is id tipo uso suelo. */
	private String is_idTipoUsoSuelo;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id zona registral. */
	private String is_idZonaRegistral;

	/** Propiedad is nombre predio. */
	private String is_nombrePredio;

	/** Propiedad is numero predial. */
	private String is_numeroPredial;

	/** Propiedad is numero predial ant. */
	private String is_numeroPredialAnt;

	/** Propiedad is nupre. */
	private String is_nupre;

	/** Propiedad is predio definitivo. */
	private String is_predioDefinitivo;

	/** Propiedad is predio inconsistente. */
	private String is_predioInconsistente;

	/** Propiedad is radicacion. */
	private String is_radicacion;

	/** Propiedad is revision. */
	private String is_revision;

	/** Propiedad is tipo complementacion. */
	private String is_tipoComplementacion;

	/** Propiedad is turno bloqueo. */
	private String is_turnoBloqueo;

	/** Propiedad ib calificacion. */
	private boolean ib_calificacion;

	/** Propiedad ib consultar predio. */
	private boolean ib_consultarPredio;

	/** Propiedad ib devolucion. */
	private boolean ib_devolucion;

	/** Propiedad ib estado anotaciones. */
	private boolean ib_estadoAnotaciones;

	/** Propiedad ib proceso division material. */
	private boolean ib_procesoDivisionMaterial;

	/** Propiedad ib proceso loteo. */
	private boolean ib_procesoLoteo;

	/** Propiedad ib proceso reloteo. */
	private boolean ib_procesoReloteo;

	/** Propiedad ib propiedad horizontal. */
	private boolean ib_propiedadHorizontal;

	/** Propiedad ii id proceso. */
	private int ii_idProceso;

	/**
	 * Modifica el valor de AnotacionCierre.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setAnotacionCierre(BigDecimal abd_bd)
	{
		ibd_anotacionCierre = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor anotacion cierre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getAnotacionCierre()
	{
		return ibd_anotacionCierre;
	}

	/**
	 * Modifica el valor de Comentario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComentario(String as_s)
	{
		is_comentario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor comentario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de ConsultarPredio.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setConsultarPredio(boolean ab_b)
	{
		ib_consultarPredio = ab_b;
	}

	/**
	 * Valida la propiedad consultar predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isConsultarPredio()
	{
		return ib_consultarPredio;
	}

	/**
	 * Modifica el valor de Devolucion.
	 *
	 * @param ab_b de ab b
	 */
	public void setDevolucion(boolean ab_b)
	{
		ib_devolucion = ab_b;
	}

	/**
	 * Valida la propiedad devolucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDevolucion()
	{
		return ib_devolucion;
	}

	/**
	 * Modifica el valor de DireccionesPredio.
	 *
	 * @param acdp_cdp asigna el valor a la propiedad
	 */
	public void setDireccionesPredio(Collection<DireccionDelPredio> acdp_cdp)
	{
		icdp_direccionesPredio = acdp_cdp;
	}

	/**
	 * Retorna Objeto o variable de valor direcciones predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<DireccionDelPredio> getDireccionesPredio()
	{
		return icdp_direccionesPredio;
	}

	/**
	 * Modifica el valor de EstadoAnotaciones.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEstadoAnotaciones(boolean ab_b)
	{
		ib_estadoAnotaciones = ab_b;
	}

	/**
	 * Valida la propiedad estado anotaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isEstadoAnotaciones()
	{
		return ib_estadoAnotaciones;
	}

	/**
	 * Modifica el valor de EstadoGrabacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoGrabacion(String as_s)
	{
		is_estadoGrabacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado grabacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoGrabacion()
	{
		return is_estadoGrabacion;
	}

	/**
	 * Modifica el valor de EstadoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoPredio(String as_s)
	{
		is_estadoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoPredio()
	{
		return is_estadoPredio;
	}

	/**
	 * Modifica el valor de FechaApertura.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaApertura(Date ad_d)
	{
		id_fechaApertura = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha apertura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaApertura()
	{
		return id_fechaApertura;
	}

	/**
	 * Modifica el valor de FolioGrabacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setFolioGrabacion(String as_s)
	{
		is_folioGrabacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor folio grabacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getFolioGrabacion()
	{
		return is_folioGrabacion;
	}

	/**
	 * Modifica el valor de IdAntiguoSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAntiguoSistema(String as_s)
	{
		is_idAntiguoSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAntiguoSistema()
	{
		return is_idAntiguoSistema;
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
	 * Modifica el valor de IdComplementacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdComplementacion(Long al_l)
	{
		il_idComplementacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdComplementacion()
	{
		return il_idComplementacion;
	}

	/**
	 * Modifica el valor de IdDatosAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de IdDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de IdEstadoNupre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstadoNupre(String as_s)
	{
		is_idEstadoNupre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado nupre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoNupre()
	{
		return is_idEstadoNupre;
	}

	/**
	 * Modifica el valor de IdEstadoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstadoPredio(String as_s)
	{
		is_idEstadoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoPredio()
	{
		return is_idEstadoPredio;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdPredioRegistro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPredioRegistro(String as_s)
	{
		is_idPredioRegistro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id predio registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPredioRegistro()
	{
		return is_idPredioRegistro;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setIdProceso(int ai_i)
	{
		ii_idProceso = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getIdProceso()
	{
		return ii_idProceso;
	}

	/**
	 * Modifica el valor de IdTipoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoPredio(String as_s)
	{
		is_idTipoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPredio()
	{
		return is_idTipoPredio;
	}

	/**
	 * Modifica el valor de IdTipoUsoSuelo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoUsoSuelo(String as_s)
	{
		is_idTipoUsoSuelo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo uso suelo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoUsoSuelo()
	{
		return is_idTipoUsoSuelo;
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
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de IdZonaRegistral.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdZonaRegistral(String as_s)
	{
		is_idZonaRegistral = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id zona registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdZonaRegistral()
	{
		return is_idZonaRegistral;
	}

	/**
	 * Modifica el valor de InfoComplementacion.
	 *
	 * @param acp_cp asigna el valor a la propiedad
	 */
	public void setInfoComplementacion(Complementacion acp_cp)
	{
		icp_infoComplementacion = acp_cp;
	}

	/**
	 * Retorna Objeto o variable de valor info complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Complementacion getInfoComplementacion()
	{
		if(icp_infoComplementacion == null)
			icp_infoComplementacion = new Complementacion();

		return icp_infoComplementacion;
	}

	/**
	 * Modifica el valor de InfoMastriculas.
	 *
	 * @param acpr_cpr asigna el valor a la propiedad
	 */
	public void setInfoMastriculas(Collection<AccPredioRegistro> acpr_cpr)
	{
		icpr_infoMastriculas = acpr_cpr;
	}

	/**
	 * Retorna Objeto o variable de valor info mastriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AccPredioRegistro> getInfoMastriculas()
	{
		return icpr_infoMastriculas;
	}

	/**
	 * Modifica el valor de InfoMatriculas.
	 *
	 * @param acap_cap asigna el valor a la propiedad
	 */
	public void setInfoMatriculas(Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> acap_cap)
	{
		icap_infoMatriculas = acap_cap;
	}

	/**
	 * Retorna Objeto o variable de valor info matriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> getInfoMatriculas()
	{
		return icap_infoMatriculas;
	}

	/**
	 * Modifica el valor de LinderoPredio.
	 *
	 * @param alp_lp asigna el valor a la propiedad
	 */
	public void setLinderoPredio(AccLinderoPredio alp_lp)
	{
		ilp_linderoPredio = alp_lp;
	}

	/**
	 * Retorna Objeto o variable de valor lindero predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public AccLinderoPredio getLinderoPredio()
	{
		if(ilp_linderoPredio == null)
			ilp_linderoPredio = new AccLinderoPredio();

		return ilp_linderoPredio;
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
	 * Modifica el valor de NumeroPredial.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroPredial(String as_s)
	{
		is_numeroPredial = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero predial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroPredial()
	{
		return is_numeroPredial;
	}

	/**
	 * Modifica el valor de NumeroPredialAnt.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroPredialAnt(String as_s)
	{
		is_numeroPredialAnt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero predial ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroPredialAnt()
	{
		return is_numeroPredialAnt;
	}

	/**
	 * Modifica el valor de Nupre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNupre(String as_s)
	{
		is_nupre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nupre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNupre()
	{
		return is_nupre;
	}

	/**
	 * Modifica el valor de PredioDefinitivo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPredioDefinitivo(String as_s)
	{
		is_predioDefinitivo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor predio definitivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPredioDefinitivo()
	{
		return is_predioDefinitivo;
	}

	/**
	 * Modifica el valor de PredioInconsistente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPredioInconsistente(String as_s)
	{
		is_predioInconsistente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor predio inconsistente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPredioInconsistente()
	{
		return is_predioInconsistente;
	}

	/**
	 * Modifica el valor de ProcesoDivisionMaterial.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setProcesoDivisionMaterial(boolean ab_b)
	{
		ib_procesoDivisionMaterial = ab_b;
	}

	/**
	 * Valida la propiedad proceso division material.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isProcesoDivisionMaterial()
	{
		return ib_procesoDivisionMaterial;
	}

	/**
	 * Modifica el valor de ProcesoLoteo.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setProcesoLoteo(boolean ab_b)
	{
		ib_procesoLoteo = ab_b;
	}

	/**
	 * Valida la propiedad proceso loteo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isProcesoLoteo()
	{
		return ib_procesoLoteo;
	}

	/**
	 * Modifica el valor de ProcesoReloteo.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setProcesoReloteo(boolean ab_b)
	{
		ib_procesoReloteo = ab_b;
	}

	/**
	 * Valida la propiedad proceso reloteo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isProcesoReloteo()
	{
		return ib_procesoReloteo;
	}

	/**
	 * Modifica el valor de PropiedadHorizontal.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setPropiedadHorizontal(boolean ab_b)
	{
		ib_propiedadHorizontal = ab_b;
	}

	/**
	 * Valida la propiedad propiedad horizontal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isPropiedadHorizontal()
	{
		return ib_propiedadHorizontal;
	}

	/**
	 * Modifica el valor de Radicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRadicacion(String as_s)
	{
		is_radicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor radicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRadicacion()
	{
		return is_radicacion;
	}

	/**
	 * Modifica el valor de Revision.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRevision(String as_s)
	{
		is_revision = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor revision.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRevision()
	{
		return is_revision;
	}

	/**
	 * Modifica el valor de TipoComplementacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoComplementacion(String as_s)
	{
		is_tipoComplementacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoComplementacion()
	{
		return is_tipoComplementacion;
	}

	/**
	 * Modifica el valor de TurnoBloqueo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTurnoBloqueo(String as_s)
	{
		is_turnoBloqueo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno bloqueo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTurnoBloqueo()
	{
		return is_turnoBloqueo;
	}

	/**
	 * Modifica el valor de ValidDummy.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidDummy(boolean ab_b)
	{
	}

	/**
	 * Valida la propiedad valid dummy.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidDummy()
	{
		return StringUtils.isValidString(getIdCirculo()) && (NumericUtils.isValidLong(getIdMatricula()));
	}

	/**
	 * Modifica el valor de VersionDocumento.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setVersionDocumento(BigDecimal abd_bd)
	{
		ibd_versionDocumento = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor version documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getVersionDocumento()
	{
		return ibd_versionDocumento;
	}

	/**
	 * Valida la propiedad calificacion.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en calificacion
	 */
	public boolean isCalificacion()
	{
		return ib_calificacion;
	}

	/**
	 * Modifica el valor de Calificacion.
	 *
	 * @param ab_b de ib calificacion
	 */
	public void setCalificacion(boolean ab_b)
	{
		ib_calificacion = ab_b;
	}
}
