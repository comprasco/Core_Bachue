package com.bachue.snr.prosnr01.model.consulta.digitador.antiguo.sistema;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.AreaPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import java.io.Serializable;

import java.util.Collection;


/**
 * Class que contiene todos las propiedades DigitadorAntiguoSistema.
 *
 * @author garias
 */
public class DigitadorAntiguoSistema extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6201450554767935038L;

	/** Propiedad iaaui area UI. */
	private AccAreaUI iaaui_areaUI;

	/** Propiedad iacp acc complementacion predio. */
	private AccComplementacionPredio iacp_accComplementacionPredio;

	/** Propiedad ialp acc lindero predio. */
	private AccLinderoPredio ialp_accLinderoPredio;

	/** Propiedad iapr acc predio registro. */
	private AccPredioRegistro iapr_accPredioRegistro;

	/** Propiedad ioan info alertas. */
	private AlertaNaturalezaJuridica ioan_infoAlertas;

	/** Propiedad ia anotacion. */
	private Anotacion ia_anotacion;

	/** Propiedad iap area predio. */
	private AreaPredio iap_AreaPredio;

	/** Propiedad icep cambio estado predio. */
	private CambioEstadoPredio icep_cambioEstadoPredio;

	/** Propiedad icddp direcciones del predio. */
	private Collection<DireccionDelPredio> icddp_direccionesDelPredio;

	/** Propiedad icps predios segregados. */
	private Collection<PredioSegregado> icps_prediosSegregados;

	/** Propiedad icsp salvedades predio. */
	private Collection<AccSalvedadPredio> icsp_salvedadesPredio;

	/** Propiedad idb datosbasicos. */
	private DatosBasicos idb_datosbasicos;

	/** Propiedad iorc anotaciones. */
	private RegistroCalificacion iorc_anotaciones;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is motivo no tramite. */
	private String is_motivoNoTramite;

	/** Propiedad si texto linderos. */
	private String is_textoLinderos;

	/** Propiedad ib cierre folio. */
	private boolean ib_cierreFolio;

	/** Propiedad ib datos ant sistema valid. */
	private boolean ib_datosAntSistemaValid;

	/** Propiedad ii total anotaciones. */
	private int ii_totalAnotaciones;

	/**
	 * Modifica el valor de acc complementacion predio.
	 *
	 * @param aacp_cp asigna el valor a la propiedad acc complementacion predio
	 */
	public void setAccComplementacionPredio(AccComplementacionPredio aacp_cp)
	{
		iacp_accComplementacionPredio = aacp_cp;
	}

	/**
	 * Retorna el valor de acc complementacion predio.
	 *
	 * @return el valor de acc complementacion predio
	 */
	public AccComplementacionPredio getAccComplementacionPredio()
	{
		return iacp_accComplementacionPredio;
	}

	/**
	 * Modifica el valor de acc lindero predio.
	 *
	 * @param alp_lp asigna el valor a la propiedad acc lindero predio
	 */
	public void setAccLinderoPredio(AccLinderoPredio alp_lp)
	{
		ialp_accLinderoPredio = alp_lp;
	}

	/**
	 * Retorna el valor de acc lindero predio.
	 *
	 * @return el valor de acc lindero predio
	 */
	public AccLinderoPredio getAccLinderoPredio()
	{
		return ialp_accLinderoPredio;
	}

	/**
	 * Modifica el valor de acc predio registro.
	 *
	 * @param aapr_apr asigna el valor a la propiedad acc predio registro
	 */
	public void setAccPredioRegistro(AccPredioRegistro aapr_apr)
	{
		iapr_accPredioRegistro = aapr_apr;
	}

	/**
	 * Retorna el valor de acc predio registro.
	 *
	 * @return el valor de acc predio registro
	 */
	public AccPredioRegistro getAccPredioRegistro()
	{
		return iapr_accPredioRegistro;
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
	 * @param aaaui_aaui asigna el valor a la propiedad area UI
	 */
	public void setAreaUI(AccAreaUI aaaui_aaui)
	{
		iaaui_areaUI = aaaui_aaui;
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
		return idb_datosbasicos;
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
	 * Modifica el valor de motivo no tramite.
	 *
	 * @param as_s asigna el valor a la propiedad motivo no tramite
	 */
	public void setMotivoNoTramite(String as_s)
	{
		is_motivoNoTramite = as_s;
	}

	/**
	 * Retorna el valor de motivo no tramite.
	 *
	 * @return el valor de motivo no tramite
	 */
	public String getMotivoNoTramite()
	{
		return is_motivoNoTramite;
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
	 * @param Modifica el valor de la propiedad textoLinderos por textoLinderos
	 */
	public void setTextoLinderos(String as_s)
	{
		is_textoLinderos = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad textoLinderos
	 */
	public String getTextoLinderos()
	{
		return is_textoLinderos;
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
