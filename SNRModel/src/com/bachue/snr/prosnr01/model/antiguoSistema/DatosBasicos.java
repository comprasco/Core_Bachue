package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades DatosBasicos.
 *
 * @author itrujillo
 */
public class DatosBasicos extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2627496447480762270L;

	/** Propiedad iapr acc predio registro. */
	private AccPredioRegistro iapr_accPredioRegistro;

	/** Propiedad iap apertura. */
	private Apertura iap_apertura;

	/** Propiedad icdas detalles ant sistema. */
	private Collection<DetalleAntSistema> icdas_detallesAntSistema;

	/** Propiedad icps predio segregado. */
	private Collection<PredioSegregado> icps_predioSegregado;

	/** Propiedad icps predio segregado ACC. */
	private Collection<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> icps_predioSegregadoACC;

	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema idas_datosAntSistema;

	/** Propiedad imb matricula base. */
	private MatriculaBase imb_matriculaBase;

	/** Propiedad iopr predio registro. */
	private PredioRegistro iopr_predioRegistro;

	/** Propiedad is codigo catastral. */
	private String is_codigoCatastral;

	/** Propiedad is codigo catastral anterior. */
	private String is_codigoCatastralAnterior;

	/** Propiedad is id datos ant sistema. */
	private String is_idDatosAntSistema;

	/** Propiedad is mensaje. */
	private String is_mensaje;

	/** Propiedad it turno. */
	private Turno it_turno;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad iuzr ubicacion. */
	private UbicacionZonaRegistral iuzr_ubicacion;

	/** Propiedad ib matricula grabada. */
	private boolean ib_matriculaGrabada;

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
		if(iapr_accPredioRegistro == null)
			iapr_accPredioRegistro = new AccPredioRegistro();

		return iapr_accPredioRegistro;
	}

	/**
	 * Modifica el valor de apertura.
	 *
	 * @param aap_ap asigna el valor a la propiedad apertura
	 */
	public void setApertura(Apertura aap_ap)
	{
		iap_apertura = aap_ap;
	}

	/**
	 * Retorna el valor de apertura.
	 *
	 * @return el valor de apertura
	 */
	public Apertura getApertura()
	{
		if(iap_apertura == null)
			iap_apertura = new Apertura();

		return iap_apertura;
	}

	/**
	 * Modifica el valor de codigo catastral.
	 *
	 * @param as_s asigna el valor a la propiedad codigo catastral
	 */
	public void setCodigoCatastral(String as_s)
	{
		is_codigoCatastral = as_s;
	}

	/**
	 * Retorna el valor de codigo catastral.
	 *
	 * @return el valor de codigo catastral
	 */
	public String getCodigoCatastral()
	{
		return is_codigoCatastral;
	}

	/**
	 * Modifica el valor de codigo catastral anterior.
	 *
	 * @param as_s asigna el valor a la propiedad codigo catastral anterior
	 */
	public void setCodigoCatastralAnterior(String as_s)
	{
		is_codigoCatastralAnterior = as_s;
	}

	/**
	 * Retorna el valor de codigo catastral anterior.
	 *
	 * @return el valor de codigo catastral anterior
	 */
	public String getCodigoCatastralAnterior()
	{
		return is_codigoCatastralAnterior;
	}

	/**
	 * Modifica el valor de datos ant sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad datos ant sistema
	 */
	public void setDatosAntSistema(DatosAntSistema adas_das)
	{
		idas_datosAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de datos ant sistema.
	 *
	 * @return el valor de datos ant sistema
	 */
	public DatosAntSistema getDatosAntSistema()
	{
		return idas_datosAntSistema;
	}

	/**
	 * Modifica el valor de detalles ant sistema.
	 *
	 * @param icdas_cdas asigna el valor a la propiedad detalles ant sistema
	 */
	public void setDetallesAntSistema(Collection<DetalleAntSistema> icdas_cdas)
	{
		icdas_detallesAntSistema = icdas_cdas;
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
	 * Modifica el valor de id datos ant sistema.
	 *
	 * @param as_s asigna el valor a la propiedad id datos ant sistema
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna el valor de id datos ant sistema.
	 *
	 * @return el valor de id datos ant sistema
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de matricula base.
	 *
	 * @param amb_mb asigna el valor a la propiedad matricula base
	 */
	public void setMatriculaBase(MatriculaBase amb_mb)
	{
		imb_matriculaBase = amb_mb;
	}

	/**
	 * Retorna el valor de matricula base.
	 *
	 * @return el valor de matricula base
	 */
	public MatriculaBase getMatriculaBase()
	{
		if(imb_matriculaBase == null)
			imb_matriculaBase = new MatriculaBase();

		return imb_matriculaBase;
	}

	/**
	 * Modifica el valor de matricula grabada.
	 *
	 * @param ab_b asigna el valor a la propiedad matricula grabada
	 */
	public void setMatriculaGrabada(boolean ab_b)
	{
		ib_matriculaGrabada = ab_b;
	}

	/**
	 * Valida la propiedad matricula grabada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en matricula grabada
	 */
	public boolean isMatriculaGrabada()
	{
		return ib_matriculaGrabada;
	}

	/**
	 * Modifica el valor de mensaje.
	 *
	 * @param as_s asigna el valor a la propiedad mensaje
	 */
	public void setMensaje(String as_s)
	{
		is_mensaje = as_s;
	}

	/**
	 * Retorna el valor de mensaje.
	 *
	 * @return el valor de mensaje
	 */
	public String getMensaje()
	{
		return is_mensaje;
	}

	/**
	 * Modifica el valor de predio registro.
	 *
	 * @param aopr_pr asigna el valor a la propiedad predio registro
	 */
	public void setPredioRegistro(PredioRegistro aopr_pr)
	{
		iopr_predioRegistro = aopr_pr;
	}

	/**
	 * Retorna el valor de predio registro.
	 *
	 * @return el valor de predio registro
	 */
	public PredioRegistro getPredioRegistro()
	{
		if(iopr_predioRegistro == null)
			iopr_predioRegistro = new PredioRegistro();

		return iopr_predioRegistro;
	}

	/**
	 * Modifica el valor de predio segregado.
	 *
	 * @param acps_cps asigna el valor a la propiedad predio segregado
	 */
	public void setPredioSegregado(Collection<PredioSegregado> acps_cps)
	{
		icps_predioSegregado = acps_cps;
	}

	/**
	 * Retorna el valor de predio segregado.
	 *
	 * @return el valor de predio segregado
	 */
	public Collection<PredioSegregado> getPredioSegregado()
	{
		return icps_predioSegregado;
	}

	/**
	 * Modifica el valor de predio segregado ACC.
	 *
	 * @param acps_cps asigna el valor a la propiedad predio segregado ACC
	 */
	public void setPredioSegregadoACC(Collection<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> acps_cps)
	{
		icps_predioSegregadoACC = acps_cps;
	}

	/**
	 * Retorna el valor de predio segregado ACC.
	 *
	 * @return el valor de predio segregado ACC
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> getPredioSegregadoACC()
	{
		return icps_predioSegregadoACC;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param at_t asigna el valor a la propiedad turno
	 */
	public void setTurno(Turno at_t)
	{
		it_turno = at_t;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 */
	public Turno getTurno()
	{
		if(it_turno == null)
			it_turno = new Turno();

		return it_turno;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param ath_th asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
	}

	/**
	 * Retorna el valor de turno historia.
	 *
	 * @return el valor de turno historia
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		if(ith_turnoHistoria == null)
			ith_turnoHistoria = new TurnoHistoria();

		return ith_turnoHistoria;
	}

	/**
	 * Modifica el valor de ubicacion.
	 *
	 * @param auzr_uzr asigna el valor a la propiedad ubicacion
	 */
	public void setUbicacion(UbicacionZonaRegistral auzr_uzr)
	{
		iuzr_ubicacion = auzr_uzr;
	}

	/**
	 * Retorna el valor de ubicacion.
	 *
	 * @return el valor de ubicacion
	 */
	public UbicacionZonaRegistral getUbicacion()
	{
		if(iuzr_ubicacion == null)
			iuzr_ubicacion = new UbicacionZonaRegistral();

		return iuzr_ubicacion;
	}
}
