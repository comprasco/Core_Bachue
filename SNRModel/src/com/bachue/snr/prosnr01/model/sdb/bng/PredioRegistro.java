package com.bachue.snr.prosnr01.model.sdb.bng;

import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoEntradaConsultarMatriculaInfoCatastral;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;

import java.io.Serializable;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_BNG_PREDIO_REGISTRO.
 *
 * @author garias
 */
public class PredioRegistro extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4781396527782085048L;

	/** Propiedad ibd id complementacion. */
	private BigDecimal ibd_idComplementacion;

	/** Propiedad ibd valor avaluo. */
	private BigDecimal ibd_valorAvaluo;

	/** Propiedad ibd version documento. */
	private BigDecimal ibd_versionDocumento;

	/** Propiedad id fecha apertura. */
	private Date id_fechaApertura;

	/** Propiedad id fecha avaluo. */
	private Date id_fechaAvaluo;

	/** Propiedad ildt fecha avaluo service. */
	private LocalDateTime ildt_fechaAvaluoService;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad is anotacion cierre. */
	private String is_anotacionCierre;

	/** Propiedad is comentario. */
	private String is_comentario;

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

	/** Propiedad is id tipo predio. */
	private String is_idTipoPredio;

	/** Propiedad is id tipo uso suelo. */
	private String is_idTipoUsoSuelo;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is id zona registral. */
	private String is_idZonaRegistral;

	/** Propiedad is nombre estado. */
	private String is_nombreEstado;

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

	/** Propiedad is turno bloqueo. */
	private String is_turnoBloqueo;

	/** Propiedad ib args. */
	private boolean ib_args;

	/** Propiedad ib consulta predio. */
	private boolean ib_consultaPredio;

	/** Propiedad ib valid matricula. */
	private boolean ib_validMatricula;

	/** Propiedad ib valid nupre. */
	private boolean ib_validNupre;

	/** Propiedad il etapa. */
	private long il_etapa;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/**
	 * Constructor por defecto.
	 */
	public PredioRegistro()
	{
	}

	/**
	 * Constructor que crea el objeto con un id círculo e id matrícula.
	 *
	 * @param as_idCirculo id del círculo a asignar
	 * @param al_idMatricula id de la matrícula
	 */
	public PredioRegistro(String as_idCirculo, long al_idMatricula)
	{
		is_idCirculo       = as_idCirculo;
		il_idMatricula     = al_idMatricula;
	}

	/**
	 * Constructor que recibe como argumento un objeto de AccPredioRegistro.
	 *
	 * @param aapr_apr objeto de AccPredioRegistro
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro
	 */
	public PredioRegistro(AccPredioRegistro aapr_apr)
	{
		if(aapr_apr != null)
		{
			is_anotacionCierre         = NumericUtils.isValidBigDecimal(aapr_apr.getAnotacionCierre())
				? aapr_apr.getAnotacionCierre().toString() : "";
			ibd_idComplementacion      = NumericUtils.getBigDecimal(
				    NumericUtils.getLong(aapr_apr.getIdComplementacion())
				);
			ibd_versionDocumento       = aapr_apr.getVersionDocumento();
			id_fechaApertura           = aapr_apr.getFechaApertura();
			is_comentario              = aapr_apr.getComentario();
			is_idAntiguoSistema        = aapr_apr.getIdAntiguoSistema();
			is_idCirculo               = aapr_apr.getIdCirculo();
			is_idDocumento             = aapr_apr.getIdDocumento();
			is_idEstadoNupre           = aapr_apr.getIdEstadoNupre();
			is_idEstadoPredio          = aapr_apr.getEstadoPredio();
			is_idTipoPredio            = aapr_apr.getIdTipoPredio();
			is_idTipoUsoSuelo          = aapr_apr.getIdTipoUsoSuelo();
			is_idZonaRegistral         = aapr_apr.getIdZonaRegistral();
			is_numeroPredial           = aapr_apr.getNumeroPredial();
			is_numeroPredialAnt        = aapr_apr.getNumeroPredialAnt();
			is_nupre                   = aapr_apr.getNupre();
			is_predioDefinitivo        = aapr_apr.getPredioDefinitivo();
			is_predioInconsistente     = aapr_apr.getPredioInconsistente();
			is_radicacion              = aapr_apr.getRadicacion();
			is_turnoBloqueo            = aapr_apr.getTurnoBloqueo();
			il_idMatricula             = NumericUtils.getLong(aapr_apr.getIdMatricula());
		}
	}

	/**
	 * Instancia un nuevo objeto predio registro.
	 *
	 * @param atecmic_entrada de atecmic entrada
	 */
	public PredioRegistro(TipoEntradaConsultarMatriculaInfoCatastral atecmic_entrada)
	{
		if(atecmic_entrada != null)
		{
			is_nupre                = atecmic_entrada.getNUPRE();
			is_numeroPredial        = atecmic_entrada.getNumPredial();
			is_numeroPredialAnt     = atecmic_entrada.getCHIP();
		}
	}

	/**
	 * Modifica el valor de AnotacionCierre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnotacionCierre(String as_s)
	{
		is_anotacionCierre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anotacion cierre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnotacionCierre()
	{
		return is_anotacionCierre;
	}

	/**
	 * Modifica el valor de Args.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setArgs(boolean ab_b)
	{
		ib_args = ab_b;
	}

	/**
	 * Valida la propiedad args.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isArgs()
	{
		return ib_args;
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
	 * Modifica el valor de ConsultaPredio.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setConsultaPredio(boolean ab_b)
	{
		ib_consultaPredio = ab_b;
	}

	/**
	 * Valida la propiedad consulta predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isConsultaPredio()
	{
		return ib_consultaPredio;
	}

	/**
	 * Modifica el valor de Etapa.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setEtapa(long al_l)
	{
		il_etapa = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor etapa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getEtapa()
	{
		return il_etapa;
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
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdComplementacion(BigDecimal abd_bd)
	{
		ibd_idComplementacion = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdComplementacion()
	{
		return ibd_idComplementacion;
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
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
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
	 * Modifica el valor de NombreEstado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreEstado(String as_s)
	{
		is_nombreEstado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreEstado()
	{
		return is_nombreEstado;
	}

	/**
	 * Modifica el valor de NombrePredio.
	 *
	 * @param as_s de as s
	 */
	public void setNombrePredio(String as_s)
	{
		is_nombrePredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre predio.
	 *
	 * @return Retorna el valor de la propiedad nombrePredio
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
	 * Modifica el valor de ValidMatricula.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidMatricula(boolean ab_b)
	{
		ib_validMatricula = ab_b;
	}

	/**
	 * Valida la propiedad valid matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidMatricula()
	{
		ib_validMatricula = NumericUtils.isValidLong(NumericUtils.getLongWrapper(getIdMatricula()), 1);

		return ib_validMatricula;
	}

	/**
	 * Modifica el valor de ValidNupre.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidNupre(boolean ab_b)
	{
		ib_validNupre = ab_b;
	}

	/**
	 * Valida la propiedad valid nupre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidNupre()
	{
		ib_validNupre = StringUtils.isValidString(getNupre());

		return ib_validNupre;
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
	 * Retorna Objeto o variable de valor fecha avaluo.
	 *
	 * @return Retorna el valor de la propiedad fechaAvaluo
	 */
	public Date getFechaAvaluo()
	{
		return id_fechaAvaluo;
	}

	/**
	 * Modifica el valor de FechaAvaluo.
	 *
	 * @param fechaAvaluo de fecha avaluo
	 */
	public void setFechaAvaluo(Date fechaAvaluo)
	{
		this.id_fechaAvaluo = fechaAvaluo;
	}

	/**
	 * Retorna Objeto o variable de valor fecha avaluo service.
	 *
	 * @return Retorna el valor de la propiedad fechaAvaluoService
	 */
	public LocalDateTime getFechaAvaluoService()
	{
		return ildt_fechaAvaluoService;
	}

	/**
	 * Modifica el valor de FechaAvaluoService.
	 *
	 * @param aldt_ldt de aldt ldt
	 */
	public void setFechaAvaluoService(LocalDateTime aldt_ldt)
	{
		ildt_fechaAvaluoService = aldt_ldt;
	}

	/**
	 * Retorna Objeto o variable de valor valor avaluo.
	 *
	 * @return Retorna el valor de la propiedad valorAvaluo
	 */
	public BigDecimal getValorAvaluo()
	{
		return ibd_valorAvaluo;
	}

	/**
	 * Modifica el valor de ValorAvaluo.
	 *
	 * @param abd_bd de abd bd
	 */
	public void setValorAvaluo(BigDecimal abd_bd)
	{
		ibd_valorAvaluo = abd_bd;
	}
}
