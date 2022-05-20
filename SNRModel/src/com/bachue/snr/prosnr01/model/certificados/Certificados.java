package com.bachue.snr.prosnr01.model.certificados;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudDireccionCertificado;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import java.io.Serializable;

import java.util.Collection;


/**
 * Class que contiene todos las propiedades Certificados.
 *
 * @author ccalderon
 */
public class Certificados extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5510281710625184027L;

	/** Propiedad icc plantillas por certificado. */
	private Collection<Constantes> icc_plantillasPorCertificado;

	/** Propiedad icdas datos antiguo sistema. */
	private Collection<DatosAntSistema> icdas_datosAntiguoSistema;

	/** Propiedad icdas detalle ant sistema nueva entrada.*/
	private Collection<DetalleAntSistema> icdas_detalleAntSistemaNuevaEntrada;

	/** Propiedad icsm matriculas. */
	private Collection<SolicitudMatricula> icsm_matriculas;

	/** Propiedad idas datos antiguo sistema nueva entrada. */
	private DatosAntSistema idas_datosAntiguoSistemaNuevaEntrada;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad isdc direccion. */
	private SolicitudDireccionCertificado isdc_direccion;

	/** Propiedad is id tipo requiere matricula. */
	private String is_idTipoRequiereMatricula;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is nombre documento. */
	private String is_nombreDocumento;

	/** Propiedad is nueva entrada. */
	private String is_nuevaEntrada;

	/** Propiedad is plantilla seleccionada. */
	private String is_plantillaSeleccionada;

	/** Propiedad is tag resuelve pantalla. */
	private String is_tagResuelvePantalla;

	/** Propiedad is texto tag. */
	private String is_textoTag;

	/** Propiedad is sub proceso. */
	private Subproceso is_subProceso;

	/** Propiedad iba documento. */
	private byte[] iba_documento;

	/** Propiedad icsm matriculas. */
	private boolean ib_esCertificadosMasivos;

	/**
	 * Modifica el valor de datos antiguo sistema.
	 *
	 * @param acdas_cdas asigna el valor a la propiedad datos antiguo sistema
	 */
	public void setDatosAntiguoSistema(Collection<DatosAntSistema> acdas_cdas)
	{
		icdas_datosAntiguoSistema = acdas_cdas;
	}

	/**
	 * Retorna el valor de datos antiguo sistema.
	 *
	 * @return el valor de datos antiguo sistema
	 */
	public Collection<DatosAntSistema> getDatosAntiguoSistema()
	{
		return icdas_datosAntiguoSistema;
	}

	/**
	 * Modifica el valor de datos antiguo sistema nueva entrada.
	 *
	 * @param acdas_cdas asigna el valor a la propiedad datos antiguo sistema nueva entrada
	 */
	public void setDatosAntiguoSistemaNuevaEntrada(DatosAntSistema adas_das)
	{
		idas_datosAntiguoSistemaNuevaEntrada = adas_das;
	}

	/**
	 * Retorna el valor de datos antiguo sistema nueva entrada.
	 *
	 * @return el valor de datos antiguo sistema nueva entrada
	 */
	public DatosAntSistema getDatosAntiguoSistemaNuevaEntrada()
	{
		return idas_datosAntiguoSistemaNuevaEntrada;
	}

	/**
	 * Modifica el valor de direccion.
	 *
	 * @param asdc_sdc asigna el valor a la propiedad direccion
	 */
	public void setDireccion(SolicitudDireccionCertificado asdc_sdc)
	{
		isdc_direccion = asdc_sdc;
	}

	/**
	 * Retorna el valor de direccion.
	 *
	 * @return el valor de direccion
	 */
	public SolicitudDireccionCertificado getDireccion()
	{
		return isdc_direccion;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param aba_ba asigna el valor a la propiedad documento
	 */
	public void setDocumento(byte[] aba_ba)
	{
		iba_documento = aba_ba;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public byte[] getDocumento()
	{
		return iba_documento;
	}

	/**
	 * Modifica el valor de EsCertificadosMasivos.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsCertificadosMasivos(boolean ab_b)
	{
		ib_esCertificadosMasivos = ab_b;
	}

	/**
	 * Valida la propiedad es certificados masivos.
	 *
	 * @return Retorna el valor de la propiedad esCertificadosMasivos
	 */
	public boolean isEsCertificadosMasivos()
	{
		return ib_esCertificadosMasivos;
	}

	/**
	 * Modifica el valor de id tipo requiere matricula.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo requiere matricula
	 */
	public void setIdTipoRequiereMatricula(String as_s)
	{
		is_idTipoRequiereMatricula = as_s;
	}

	/**
	 * Retorna el valor de id tipo requiere matricula.
	 *
	 * @return el valor de id tipo requiere matricula
	 */
	public String getIdTipoRequiereMatricula()
	{
		return is_idTipoRequiereMatricula;
	}

	/**
	 * Modifica el valor de DetalleAntSistemaNuevaEntrada
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setDetalleAntSistemaNuevaEntrada(Collection<DetalleAntSistema> ac_c)
	{
		icdas_detalleAntSistemaNuevaEntrada = ac_c;
	}

	/**
	 * Get DetalleAntSistemaNuevaEntrada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<DetalleAntSistema> getDetalleAntSistemaNuevaEntrada()
	{
		return icdas_detalleAntSistemaNuevaEntrada;
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
	 * Modifica el valor de id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de matriculas.
	 *
	 * @param acsm_csm asigna el valor a la propiedad matriculas
	 */
	public void setMatriculas(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculas = acsm_csm;
	}

	/**
	 * Retorna el valor de matriculas.
	 *
	 * @return el valor de matriculas
	 */
	public Collection<SolicitudMatricula> getMatriculas()
	{
		return icsm_matriculas;
	}

	/**
	 * Modifica el valor de nombre documento.
	 *
	 * @param as_s asigna el valor a la propiedad nombre documento
	 */
	public void setNombreDocumento(String as_s)
	{
		is_nombreDocumento = as_s;
	}

	/**
	 * Retorna el valor de nombre documento.
	 *
	 * @return el valor de nombre documento
	 */
	public String getNombreDocumento()
	{
		return is_nombreDocumento;
	}

	/**
	 * Modifica el valor de nueva entrada.
	 *
	 * @param as_s asigna el valor a la propiedad nueva entrada
	 */
	public void setNuevaEntrada(String as_s)
	{
		is_nuevaEntrada = as_s;
	}

	/**
	 * Retorna el valor de nueva entrada.
	 *
	 * @return el valor de nueva entrada
	 */
	public String getNuevaEntrada()
	{
		return is_nuevaEntrada;
	}

	/**
	 * Modifica el valor de persona.
	 *
	 * @param ap_p asigna el valor a la propiedad persona
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna el valor de persona.
	 *
	 * @return el valor de persona
	 */
	public Persona getPersona()
	{
		return ip_persona;
	}

	/**
	 * Modifica el valor de plantilla seleccionada.
	 *
	 * @param as_s asigna el valor a la propiedad plantilla seleccionada
	 */
	public void setPlantillaSeleccionada(String as_s)
	{
		is_plantillaSeleccionada = as_s;
	}

	/**
	 * Retorna el valor de plantilla seleccionada.
	 *
	 * @return el valor de plantilla seleccionada
	 */
	public String getPlantillaSeleccionada()
	{
		return is_plantillaSeleccionada;
	}

	/**
	 * Modifica el valor de plantillas por certificado.
	 *
	 * @param acc_cc asigna el valor a la propiedad plantillas por certificado
	 */
	public void setPlantillasPorCertificado(Collection<Constantes> acc_cc)
	{
		icc_plantillasPorCertificado = acc_cc;
	}

	/**
	 * Retorna el valor de plantillas por certificado.
	 *
	 * @return el valor de plantillas por certificado
	 */
	public Collection<Constantes> getPlantillasPorCertificado()
	{
		return icc_plantillasPorCertificado;
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
	 * Modifica el valor de sub proceso.
	 *
	 * @param asb_sb asigna el valor a la propiedad sub proceso
	 */
	public void setSubProceso(Subproceso asb_sb)
	{
		is_subProceso = asb_sb;
	}

	/**
	 * Retorna el valor de sub proceso.
	 *
	 * @return el valor de sub proceso
	 */
	public Subproceso getSubProceso()
	{
		return is_subProceso;
	}

	/**
	 * Modifica el valor de tag resuelve pantalla.
	 *
	 * @param as_s asigna el valor a la propiedad tag resuelve pantalla
	 */
	public void setTagResuelvePantalla(String as_s)
	{
		is_tagResuelvePantalla = as_s;
	}

	/**
	 * Retorna el valor de tag resuelve pantalla.
	 *
	 * @return el valor de tag resuelve pantalla
	 */
	public String getTagResuelvePantalla()
	{
		return is_tagResuelvePantalla;
	}

	/**
	 * Modifica el valor de texto tag.
	 *
	 * @param as_s asigna el valor a la propiedad texto tag
	 */
	public void setTextoTag(String as_s)
	{
		is_textoTag = as_s;
	}

	/**
	 * Retorna el valor de texto tag.
	 *
	 * @return el valor de texto tag
	 */
	public String getTextoTag()
	{
		return is_textoTag;
	}
}
