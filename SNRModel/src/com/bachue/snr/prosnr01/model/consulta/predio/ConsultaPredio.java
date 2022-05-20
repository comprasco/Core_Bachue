package com.bachue.snr.prosnr01.model.consulta.predio;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.AreaPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ConsultaSalvedad;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.LinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.bng.SalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import java.io.Serializable;

import java.util.Collection;


/**
 * Class que contiene todos las propiedades ConsultaPredio.
 *
 * @author garias
 */
public class ConsultaPredio implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -9201181081726267479L;

	/** Propiedad iaaui area UI. */
	private AccAreaUI iaaui_areaUI;

	/** Propiedad ioan info alertas. */
	private AlertaNaturalezaJuridica ioan_infoAlertas;

	/** Propiedad ia anotacion. */
	private Anotacion ia_anotacion;

	/** Propiedad iap area predio. */
	private AreaPredio iap_AreaPredio;

	/** Propiedad icep cambio estado predio. */
	private CambioEstadoPredio icep_cambioEstadoPredio;

	/** Propiedad icap datos area predio. */
	private Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> icap_datosAreaPredio;

	/** Propiedad iccp data consulta. */
	private Collection<ConsultaPredio> iccp_dataConsulta;

	/** Propiedad iccs salvedades. */
	private Collection<ConsultaSalvedad> iccs_salvedades;

	/** Propiedad icdas detalles ant sistema. */
	private Collection<DetalleAntSistema> icdas_detallesAntSistema;

	/** Propiedad icddp direcciones del predio. */
	private Collection<DireccionDelPredio> icddp_direccionesDelPredio;

	/** Propiedad icps predios segregados. */
	private Collection<PredioSegregado> icps_prediosSegregados;

	/** Propiedad icsp salvedades predio. */
	private Collection<AccSalvedadPredio> icsp_salvedadesPredio;

	/** Propiedad icsp salvedades predio bng. */
	private Collection<SalvedadPredio> icsp_salvedadesPredioBng;

	/** Propiedad ioc complementacion predio. */
	private ComplementacionPredio ioc_complementacionPredio;

	/** Propiedad ioas antiguo sistema data. */
	private DatosAntSistema ioas_antiguoSistemaData;

	/** Propiedad idb datosbasicos. */
	private DatosBasicos idb_datosbasicos;

	/** Propiedad iod documento criterio. */
	private Documento iod_documentoCriterio;

	/** Propiedad iolp lindero predio. */
	private LinderoPredio iolp_linderoPredio;

	/** Propiedad il id anotacion. */
	private Long il_idAnotacion;

	/** Propiedad is id matricula. */
	private Long il_idMatricula;

	/** Propiedad iop persona criterio. */
	private Persona iop_personaCriterio;

	/** Propiedad ipr predio registro. */
	private PredioRegistro ipr_predioRegistro;

	/** Propiedad iorc anotaciones. */
	private RegistroCalificacion iorc_anotaciones;

	/** Propiedad ism solicitud matricula. */
	private SolicitudMatricula ism_solicitudMatricula;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is estado predio. */
	private String is_estadoPredio;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id naturaleza. */
	private String is_idNaturaleza;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is proceso. */
	private String is_proceso;

	/** Propiedad ib anotacione criterio. */
	private boolean ib_anotacioneCriterio;

	/** Propiedad ib cierre folio. */
	private boolean ib_cierreFolio;

	/** Propiedad ib consulta total. */
	private boolean ib_consultaTotal;

	/** Propiedad ib datos ant sistema valid. */
	private boolean ib_datosAntSistemaValid;

	/** Propiedad ib info documento criterio. */
	private boolean ib_infoDocumentoCriterio;

	/** Propiedad ib info persona criterio. */
	private boolean ib_infoPersonaCriterio;

	/** Propiedad ib naturaleza criterio. */
	private boolean ib_naturalezaCriterio;

	/** Propiedad ii total anotaciones. */
	private int ii_totalAnotaciones;

/**
 * Método constructor por defecto.
 */
	public ConsultaPredio()
	{
	}

/**
 * Método constructor secundario que crea una instancia con circulo y matrícula.
 *
 * @param as_s <code>String</code> que contiene el círculo a asignar
 * @param al_l <code>Long</code> que contiene la matrícula a asignar
 */
	public ConsultaPredio(String as_s, Long al_l)
	{
		is_idCirculo       = as_s;
		il_idMatricula     = al_l;
	}

	/**
	 * Modifica el valor de anotacion.
	 *
	 * @param aa_a asigna el valor a la propiedad anotacion
	 */
	public void setAnotacion(Anotacion aa_a)
	{
		ia_anotacion = aa_a;
	}

	/**
	 * Retorna el valor de anotacion.
	 *
	 * @return el valor de anotacion
	 */
	public Anotacion getAnotacion()
	{
		return ia_anotacion;
	}

	/**
	 * Modifica el valor de anotacione criterio.
	 *
	 * @param ab_b asigna el valor a la propiedad anotacione criterio
	 */
	public void setAnotacioneCriterio(boolean ab_b)
	{
		ib_anotacioneCriterio = ab_b;
	}

	/**
	 * Valida la propiedad anotacione criterio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en anotacione criterio
	 */
	public boolean isAnotacioneCriterio()
	{
		return ib_anotacioneCriterio;
	}

	/**
	 * Modifica el valor de anotaciones.
	 *
	 * @param aorc_rc asigna el valor a la propiedad anotaciones
	 */
	public void setAnotaciones(RegistroCalificacion aorc_rc)
	{
		iorc_anotaciones = aorc_rc;
	}

	/**
	 * Retorna el valor de anotaciones.
	 *
	 * @return el valor de anotaciones
	 */
	public RegistroCalificacion getAnotaciones()
	{
		if(iorc_anotaciones == null)
			iorc_anotaciones = new RegistroCalificacion();

		return iorc_anotaciones;
	}

	/**
	 * Modifica el valor de antiguo sistema data.
	 *
	 * @param aoas_as asigna el valor a la propiedad antiguo sistema data
	 */
	public void setAntiguoSistemaData(DatosAntSistema aoas_as)
	{
		ioas_antiguoSistemaData = aoas_as;
	}

	/**
	 * Retorna el valor de antiguo sistema data.
	 *
	 * @return el valor de antiguo sistema data
	 */
	public DatosAntSistema getAntiguoSistemaData()
	{
		return ioas_antiguoSistemaData;
	}

	/**
	 * Modifica el valor de area predio.
	 *
	 * @param aap_ap asigna el valor a la propiedad area predio
	 */
	public void setAreaPredio(AreaPredio aap_ap)
	{
		iap_AreaPredio = aap_ap;
	}

	/**
	 * Retorna el valor de area predio.
	 *
	 * @return el valor de area predio
	 */
	public AreaPredio getAreaPredio()
	{
		return iap_AreaPredio;
	}

	/**
	 * Modifica el valor de area UI.
	 *
	 * @param aaaui_aaaui asigna el valor a la propiedad area UI
	 */
	public void setAreaUI(AccAreaUI aaaui_aaaui)
	{
		iaaui_areaUI = aaaui_aaaui;
	}

	/**
	 * Retorna el valor de area UI.
	 *
	 * @return el valor de area UI
	 */
	public AccAreaUI getAreaUI()
	{
		return iaaui_areaUI;
	}

	/**
	 * Modifica el valor de CambioEstadoPredio.
	 *
	 * @param acep_cep de acep cep
	 */
	public void setCambioEstadoPredio(CambioEstadoPredio acep_cep)
	{
		icep_cambioEstadoPredio = acep_cep;
	}

	/**
	 * Retorna Objeto o variable de valor cambio estado predio.
	 *
	 * @return Retorna el valor de la propiedad cambioEstadoPredio
	 */
	public CambioEstadoPredio getCambioEstadoPredio()
	{
		return icep_cambioEstadoPredio;
	}

	/**
	 * Modifica el valor de CierreFolio.
	 *
	 * @param ab_b de ab b
	 */
	public void setCierreFolio(boolean ab_b)
	{
		ib_cierreFolio = ab_b;
	}

	/**
	 * Valida la propiedad cierre folio.
	 *
	 * @return Retorna el valor de la propiedad cierreFolio
	 */
	public boolean isCierreFolio()
	{
		return ib_cierreFolio;
	}

	/**
	 * Modifica el valor de complementacion predio.
	 *
	 * @param aoc_oc asigna el valor a la propiedad complementacion predio
	 */
	public void setComplementacionPredio(ComplementacionPredio aoc_oc)
	{
		ioc_complementacionPredio = aoc_oc;
	}

	/**
	 * Retorna el valor de complementacion predio.
	 *
	 * @return el valor de complementacion predio
	 */
	public ComplementacionPredio getComplementacionPredio()
	{
		if(ioc_complementacionPredio == null)
			ioc_complementacionPredio = new ComplementacionPredio();

		return ioc_complementacionPredio;
	}

	/**
	 * Modifica el valor de consulta total.
	 *
	 * @param ab_b asigna el valor a la propiedad consulta total
	 */
	public void setConsultaTotal(boolean ab_b)
	{
		ib_consultaTotal = ab_b;
	}

	/**
	 * Valida la propiedad consulta total.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en consulta total
	 */
	public boolean isConsultaTotal()
	{
		return ib_consultaTotal;
	}

	/**
	 * Modifica el valor de data consulta.
	 *
	 * @param accp_cp asigna el valor a la propiedad data consulta
	 */
	public void setDataConsulta(Collection<ConsultaPredio> accp_cp)
	{
		iccp_dataConsulta = accp_cp;
	}

	/**
	 * Retorna el valor de data consulta.
	 *
	 * @return el valor de data consulta
	 */
	public Collection<ConsultaPredio> getDataConsulta()
	{
		return iccp_dataConsulta;
	}

	/**
	 * Modifica el valor de datos ant sistema valid.
	 *
	 * @param ab_b asigna el valor a la propiedad datos ant sistema valid
	 */
	public void setDatosAntSistemaValid(boolean ab_b)
	{
		ib_datosAntSistemaValid = ab_b;
	}

	/**
	 * Valida la propiedad datos ant sistema valid.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos ant sistema valid
	 */
	public boolean isDatosAntSistemaValid()
	{
		return ib_datosAntSistemaValid;
	}

	/**
	 * Modifica el valor de datos area predio.
	 *
	 * @param acap_cap asigna el valor a la propiedad datos area predio
	 */
	public void setDatosAreaPredio(Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> acap_cap)
	{
		icap_datosAreaPredio = acap_cap;
	}

	/**
	 * Retorna el valor de datos area predio.
	 *
	 * @return el valor de datos area predio
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> getDatosAreaPredio()
	{
		return icap_datosAreaPredio;
	}

	/**
	 * Modifica el valor de datosbasicos.
	 *
	 * @param adp_dp asigna el valor a la propiedad datosbasicos
	 */
	public void setDatosbasicos(DatosBasicos adp_dp)
	{
		idb_datosbasicos = adp_dp;
	}

	/**
	 * Retorna el valor de datosbasicos.
	 *
	 * @return el valor de datosbasicos
	 */
	public DatosBasicos getDatosbasicos()
	{
		if(idb_datosbasicos == null)
			idb_datosbasicos = new DatosBasicos();

		return idb_datosbasicos;
	}

	/**
	 * Modifica el valor de detalles ant sistema.
	 *
	 * @param acdas_cdas asigna el valor a la propiedad detalles ant sistema
	 */
	public void setDetallesAntSistema(Collection<DetalleAntSistema> acdas_cdas)
	{
		icdas_detallesAntSistema = acdas_cdas;
	}

	/**
	 * Retorna el valor de detalles ant sistema.
	 *
	 * @return el valor de detalles ant sistema
	 */
	public Collection<DetalleAntSistema> getDetallesAntSistema()
	{
		return icdas_detallesAntSistema;
	}

	/**
	 * Modifica el valor de direcciones del predio.
	 *
	 * @param addp_ddp asigna el valor a la propiedad direcciones del predio
	 */
	public void setDireccionesDelPredio(Collection<DireccionDelPredio> addp_ddp)
	{
		icddp_direccionesDelPredio = addp_ddp;
	}

	/**
	 * Retorna el valor de direcciones del predio.
	 *
	 * @return el valor de direcciones del predio
	 */
	public Collection<DireccionDelPredio> getDireccionesDelPredio()
	{
		return icddp_direccionesDelPredio;
	}

	/**
	 * Modifica el valor de documento criterio.
	 *
	 * @param aod_od asigna el valor a la propiedad documento criterio
	 */
	public void setDocumentoCriterio(Documento aod_od)
	{
		iod_documentoCriterio = aod_od;
	}

	/**
	 * Retorna el valor de documento criterio.
	 *
	 * @return el valor de documento criterio
	 */
	public Documento getDocumentoCriterio()
	{
		return iod_documentoCriterio;
	}

	/**
	 * Modifica el valor de estado.
	 *
	 * @param as_s asigna el valor a la propiedad estado
	 */
	public void setEstado(String as_s)
	{
		this.is_estado = as_s;
	}

	/**
	 * Retorna el valor de estado.
	 *
	 * @return el valor de estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de estado predio.
	 *
	 * @param as_s asigna el valor a la propiedad estado predio
	 */
	public void setEstadoPredio(String as_s)
	{
		is_estadoPredio = as_s;
	}

	/**
	 * Retorna el valor de estado predio.
	 *
	 * @return el valor de estado predio
	 */
	public String getEstadoPredio()
	{
		return is_estadoPredio;
	}

	/**
	 * Modifica el valor de id anotacion.
	 *
	 * @param as_s asigna el valor a la propiedad id anotacion
	 */
	public void setIdAnotacion(Long as_s)
	{
		il_idAnotacion = as_s;
	}

	/**
	 * Retorna el valor de id anotacion.
	 *
	 * @return el valor de id anotacion
	 */
	public Long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de id matricula.
	 *
	 * @param al_l asigna el valor a la propiedad id matricula
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna el valor de id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de id naturaleza.
	 *
	 * @param as_s asigna el valor a la propiedad id naturaleza
	 */
	public void setIdNaturaleza(String as_s)
	{
		is_idNaturaleza = as_s;
	}

	/**
	 * Retorna el valor de id naturaleza.
	 *
	 * @return el valor de id naturaleza
	 */
	public String getIdNaturaleza()
	{
		return is_idNaturaleza;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de info alertas.
	 *
	 * @param aoanj_nj asigna el valor a la propiedad info alertas
	 */
	public void setInfoAlertas(AlertaNaturalezaJuridica aoanj_nj)
	{
		ioan_infoAlertas = aoanj_nj;
	}

	/**
	 * Retorna el valor de info alertas.
	 *
	 * @return el valor de info alertas
	 */
	public AlertaNaturalezaJuridica getInfoAlertas()
	{
		if(ioan_infoAlertas == null)
			ioan_infoAlertas = new AlertaNaturalezaJuridica();

		return ioan_infoAlertas;
	}

	/**
	 * Modifica el valor de info documento criterio.
	 *
	 * @param ab_b asigna el valor a la propiedad info documento criterio
	 */
	public void setInfoDocumentoCriterio(boolean ab_b)
	{
		ib_infoDocumentoCriterio = ab_b;
	}

	/**
	 * Valida la propiedad info documento criterio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en info documento criterio
	 */
	public boolean isInfoDocumentoCriterio()
	{
		return ib_infoDocumentoCriterio;
	}

	/**
	 * Modifica el valor de info persona criterio.
	 *
	 * @param ab_b asigna el valor a la propiedad info persona criterio
	 */
	public void setInfoPersonaCriterio(boolean ab_b)
	{
		ib_infoPersonaCriterio = ab_b;
	}

	/**
	 * Valida la propiedad info persona criterio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en info persona criterio
	 */
	public boolean isInfoPersonaCriterio()
	{
		return ib_infoPersonaCriterio;
	}

	/**
	 * Modifica el valor de lindero predio.
	 *
	 * @param alp_lp asigna el valor a la propiedad lindero predio
	 */
	public void setLinderoPredio(LinderoPredio alp_lp)
	{
		iolp_linderoPredio = alp_lp;
	}

	/**
	 * Retorna el valor de lindero predio.
	 *
	 * @return el valor de lindero predio
	 */
	public LinderoPredio getLinderoPredio()
	{
		if(iolp_linderoPredio == null)
			iolp_linderoPredio = new LinderoPredio();

		return iolp_linderoPredio;
	}

	/**
	 * Modifica el valor de naturaleza criterio.
	 *
	 * @param ab_b asigna el valor a la propiedad naturaleza criterio
	 */
	public void setNaturalezaCriterio(boolean ab_b)
	{
		ib_naturalezaCriterio = ab_b;
	}

	/**
	 * Valida la propiedad naturaleza criterio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en naturaleza criterio
	 */
	public boolean isNaturalezaCriterio()
	{
		return ib_naturalezaCriterio;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de persona criterio.
	 *
	 * @param aop_op asigna el valor a la propiedad persona criterio
	 */
	public void setPersonaCriterio(Persona aop_op)
	{
		iop_personaCriterio = aop_op;
	}

	/**
	 * Retorna el valor de persona criterio.
	 *
	 * @return el valor de persona criterio
	 */
	public Persona getPersonaCriterio()
	{
		return iop_personaCriterio;
	}

	/**
	 * Modifica el valor de predio registro.
	 *
	 * @param apr_pr asigna el valor a la propiedad predio registro
	 */
	public void setPredioRegistro(PredioRegistro apr_pr)
	{
		ipr_predioRegistro = apr_pr;
	}

	/**
	 * Retorna el valor de predio registro.
	 *
	 * @return el valor de predio registro
	 */
	public PredioRegistro getPredioRegistro()
	{
		return ipr_predioRegistro;
	}

	/**
	 * Modifica el valor de predios segregados.
	 *
	 * @param acps_cps asigna el valor a la propiedad predios segregados
	 */
	public void setPrediosSegregados(Collection<PredioSegregado> acps_cps)
	{
		icps_prediosSegregados = acps_cps;
	}

	/**
	 * Retorna el valor de predios segregados.
	 *
	 * @return el valor de predios segregados
	 */
	public Collection<PredioSegregado> getPrediosSegregados()
	{
		return icps_prediosSegregados;
	}

	/**
	 * Modifica el valor de proceso.
	 *
	 * @param as_s asigna el valor a la propiedad proceso
	 */
	public void setProceso(String as_s)
	{
		is_proceso = as_s;
	}

	/**
	 * Retorna el valor de proceso.
	 *
	 * @return el valor de proceso
	 */
	public String getProceso()
	{
		return is_proceso;
	}

	/**
	 * Modifica el valor de salvedades.
	 *
	 * @param accs_ccs asigna el valor a la propiedad salvedades
	 */
	public void setSalvedades(Collection<ConsultaSalvedad> accs_ccs)
	{
		iccs_salvedades = accs_ccs;
	}

	/**
	 * Retorna el valor de salvedades.
	 *
	 * @return el valor de salvedades
	 */
	public Collection<ConsultaSalvedad> getSalvedades()
	{
		return iccs_salvedades;
	}

	/**
	 * Modifica el valor de salvedades predio.
	 *
	 * @param acsp_csp asigna el valor a la propiedad salvedades predio
	 */
	public void setSalvedadesPredio(Collection<AccSalvedadPredio> acsp_csp)
	{
		icsp_salvedadesPredio = acsp_csp;
	}

	/**
	 * Retorna el valor de salvedades predio.
	 *
	 * @return el valor de salvedades predio
	 */
	public Collection<AccSalvedadPredio> getSalvedadesPredio()
	{
		return icsp_salvedadesPredio;
	}

	/**
	 * Modifica el valor de salvedades predio bng.
	 *
	 * @param acsp_csp asigna el valor a la propiedad salvedades predio bng
	 */
	public void setSalvedadesPredioBng(Collection<SalvedadPredio> acsp_csp)
	{
		icsp_salvedadesPredioBng = acsp_csp;
	}

	/**
	 * Retorna el valor de salvedades predio bng.
	 *
	 * @return el valor de salvedades predio bng
	 */
	public Collection<SalvedadPredio> getSalvedadesPredioBng()
	{
		return icsp_salvedadesPredioBng;
	}

	/**
	 * Modifica el valor de solicitud matricula.
	 *
	 * @param asm_sm asigna el valor a la propiedad solicitud matricula
	 */
	public void setSolicitudMatricula(SolicitudMatricula asm_sm)
	{
		ism_solicitudMatricula = asm_sm;
	}

	/**
	 * Retorna el valor de solicitud matricula.
	 *
	 * @return el valor de solicitud matricula
	 */
	public SolicitudMatricula getSolicitudMatricula()
	{
		return ism_solicitudMatricula;
	}

	/**
	 * Modifica el valor de total anotaciones.
	 *
	 * @param ai_i asigna el valor a la propiedad total anotaciones
	 */
	public void setTotalAnotaciones(int ai_i)
	{
		ii_totalAnotaciones = ai_i;
	}

	/**
	 * Retorna el valor de total anotaciones.
	 *
	 * @return el valor de total anotaciones
	 */
	public int getTotalAnotaciones()
	{
		return ii_totalAnotaciones;
	}
}
