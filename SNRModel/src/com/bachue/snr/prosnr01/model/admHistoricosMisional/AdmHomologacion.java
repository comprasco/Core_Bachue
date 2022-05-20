package com.bachue.snr.prosnr01.model.admHistoricosMisional;

import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import java.io.Serializable;

import java.util.Collection;


/**
 * Class que contiene todos las propiedades AdmHomologacion.
 *
 * @author lchacon
 */
public class AdmHomologacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1505004855661926226L;

	/** Propiedad ia acto. */
	private Acto ia_acto;

	/** Propiedad iaa adm actos. */
	private AdmActos iaa_admActos;

	/** Propiedad ial adm liquidaciones. */
	private AdmLiquidaciones ial_admLiquidaciones;

	/** Propiedad iap adm pagos. */
	private AdmPagos iap_admPagos;

	/** Propiedad icah actos homologados Y agregados. */
	private Collection<AdmHomologacion> icah_actosHomologadosYAgregados;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad ata tipo acto. */
	private TipoActo ata_tipoActo;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad ib certificado. */
	private boolean ib_certificado;

	/** Propiedad ib homologado. */
	private boolean ib_homologado;

	/** Propiedad ii codigo acto homologado. */
	private int ii_codigoActoHomologado;

	/**
	 * Modifica el valor de acto.
	 *
	 * @param aa_a asigna el valor a la propiedad acto
	 */
	public void setActo(Acto aa_a)
	{
		ia_acto = aa_a;
	}

	/**
	 * Retorna el valor de acto.
	 *
	 * @return el valor de acto
	 */
	public Acto getActo()
	{
		return ia_acto;
	}

	/**
	 * Modifica el valor de actos homologados Y agregados.
	 *
	 * @param aca_ca asigna el valor a la propiedad actos homologados Y agregados
	 */
	public void setActosHomologadosYAgregados(Collection<AdmHomologacion> aca_ca)
	{
		icah_actosHomologadosYAgregados = aca_ca;
	}

	/**
	 * Retorna el valor de actos homologados Y agregados.
	 *
	 * @return el valor de actos homologados Y agregados
	 */
	public Collection<AdmHomologacion> getActosHomologadosYAgregados()
	{
		return icah_actosHomologadosYAgregados;
	}

	/**
	 * Modifica el valor de adm actos.
	 *
	 * @param aaa_aaa asigna el valor a la propiedad adm actos
	 */
	public void setAdmActos(AdmActos aaa_aaa)
	{
		iaa_admActos = aaa_aaa;
	}

	/**
	 * Retorna el valor de adm actos.
	 *
	 * @return el valor de adm actos
	 */
	public AdmActos getAdmActos()
	{
		return iaa_admActos;
	}

	/**
	 * Modifica el valor de adm liquidaciones.
	 *
	 * @param aal_al asigna el valor a la propiedad adm liquidaciones
	 */
	public void setAdmLiquidaciones(AdmLiquidaciones aal_al)
	{
		ial_admLiquidaciones = aal_al;
	}

	/**
	 * Retorna el valor de adm liquidaciones.
	 *
	 * @return el valor de adm liquidaciones
	 */
	public AdmLiquidaciones getAdmLiquidaciones()
	{
		return ial_admLiquidaciones;
	}

	/**
	 * Modifica el valor de adm pagos.
	 *
	 * @param aap_ap asigna el valor a la propiedad adm pagos
	 */
	public void setAdmPagos(AdmPagos aap_ap)
	{
		iap_admPagos = aap_ap;
	}

	/**
	 * Retorna el valor de adm pagos.
	 *
	 * @return el valor de adm pagos
	 */
	public AdmPagos getAdmPagos()
	{
		return iap_admPagos;
	}

	/**
	 * Modifica el valor de codigo acto homologado.
	 *
	 * @param ai_i asigna el valor a la propiedad codigo acto homologado
	 */
	public void setCodigoActoHomologado(int ai_i)
	{
		ii_codigoActoHomologado = ai_i;
	}

	/**
	 * Retorna el valor de codigo acto homologado.
	 *
	 * @return el valor de codigo acto homologado
	 */
	public int getCodigoActoHomologado()
	{
		return ii_codigoActoHomologado;
	}

	/**
	 * Modifica el valor de homologado.
	 *
	 * @param ab_b asigna el valor a la propiedad homologado
	 */
	public void setHomologado(boolean ab_b)
	{
		ib_homologado = ab_b;
	}

	/**
	 * Valida la propiedad homologado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en homologado
	 */
	public boolean isHomologado()
	{
		return ib_homologado;
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
	 * Modifica el valor de solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Modifica el valor de tipo acto.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo acto
	 */
	public void setTipoActo(TipoActo ata_ta)
	{
		ata_tipoActo = ata_ta;
	}

	/**
	 * Retorna el valor de tipo acto.
	 *
	 * @return el valor de tipo acto
	 */
	public TipoActo getTipoActo()
	{
		return ata_tipoActo;
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
		return ith_turnoHistoria;
	}

	/**
	 * Valida la propiedad certificado.
	 *
	 * @return Retorna el valor de la propiedad certificado
	 */
	public boolean isCertificado()
	{
		return ib_certificado;
	}

	/**
	 * Modifica el valor de Certificado.
	 *
	 * @param ab_b de ab b
	 */
	public void setCertificado(boolean ab_b)
	{
		ib_certificado = ab_b;
	}
}
