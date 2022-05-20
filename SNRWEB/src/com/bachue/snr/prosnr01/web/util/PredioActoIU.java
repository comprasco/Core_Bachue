package com.bachue.snr.prosnr01.web.util;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatriculaActo;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades de PredioActoIU.
 *
 * @author ccalderon
 */
public class PredioActoIU implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1954500915097335220L;

	/** Propiedad il cantidad. */
	private Long il_cantidad;

	/** Propiedad il cantidad aperturar. */
	private Long il_cantidadAperturar;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad imsb actos. */
	private Map<String, Boolean> imsb_actos;

	/** Propiedad is direccion. */
	private String is_direccion;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id circulo destino. */
	private String is_idCirculoDestino;

	/** Propiedad is id turno certificado. */
	private String is_idTurnoCertificado;

	/** Propiedad is matricula. */
	private String is_matricula;

	/** Propiedad is numero predial. */
	private String is_numeroPredial;

	/** Propiedad is nupre. */
	private String is_nupre;

	/** Propiedad ib bloquear matricula. */
	private boolean ib_bloquearMatricula;

	/** Propiedad ib certificado asociado. */
	private boolean ib_certificadoAsociado;

	/** Propiedad ib correccion. */
	private boolean ib_correccion;

	/** Propiedad es predio inconsistente. */
	private boolean ib_esPredioInconsistente;

	/** Propiedad ib es tipo acto 700 valido. */
	private boolean ib_esTipoActo700Valido;

	/** Propiedad ib habilitar cert. */
	private boolean ib_habilitarCert;

	/** Propiedad ib habilitar certificados. */
	private boolean ib_habilitarCertificados;

	/** Propiedad ib habilitar detalle area. */
	private boolean ib_habilitarDetalleArea;

	/** Propiedad ib ingreso nuevo. */
	private boolean ib_ingresoNuevo;

	/** Propiedad ib matricula cargada. */
	private boolean ib_matriculaCargada;

	/** Propiedad ib registro parcial. */
	private boolean ib_registroParcial;

	/** Propiedad ib tipo acto 700. */
	private boolean ib_tipoActo700;

	/** Propiedad ib validar area. */
	private boolean ib_validarArea;

	/** Propiedad ii consecutivo. */
	private int ii_consecutivo;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Instancia un nuevo objeto predio acto IU.
	 */
	public PredioActoIU()
	{
	}

	/**
	 * Instancia un nuevo objeto predio acto IU.
	 *
	 * @param accm_actos correspondiente al valor del tipo de objeto Collection<ColumnModel>
	 */
	public PredioActoIU(Collection<ColumnModel> accm_actos)
	{
		if(CollectionUtils.isValidCollection(accm_actos))
		{
			Map<String, Boolean> lmsb_actos;

			lmsb_actos = new HashMap<String, Boolean>();

			for(ColumnModel lcm_tmp : accm_actos)
			{
				if(lcm_tmp != null)
				{
					String ls_idActo;

					ls_idActo = lcm_tmp.getIdActo();

					if(StringUtils.isValidString(ls_idActo))
						lmsb_actos.put(ls_idActo, new Boolean(false));
				}
			}

			setActos(lmsb_actos);
		}
	}

	/**
	 * Sets the actos.
	 *
	 * @param amsb_actos correspondiente al valor del tipo de objeto Map<String,Boolean>
	 */
	public void setActos(Map<String, Boolean> amsb_actos)
	{
		imsb_actos = amsb_actos;
	}

	/**
	 * Retorna el valor de actos.
	 *
	 * @return el valor de actos
	 */
	public Map<String, Boolean> getActos()
	{
		if(imsb_actos == null)
			imsb_actos = new HashMap<String, Boolean>();

		return imsb_actos;
	}

	/**
	 * Modifica el valor de bloquear matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad bloquear matricula
	 */
	public void setBloquearMatricula(boolean ab_b)
	{
		ib_bloquearMatricula = ab_b;
	}

	/**
	 * Valida la propiedad bloquear matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bloquear matricula
	 */
	public boolean isBloquearMatricula()
	{
		return ib_bloquearMatricula;
	}

	/**
	 * Modifica el valor de cantidad.
	 *
	 * @param al_l asigna el valor a la propiedad cantidad
	 */
	public void setCantidad(Long al_l)
	{
		il_cantidad = al_l;
	}

	/**
	 * Retorna el valor de cantidad.
	 *
	 * @return el valor de cantidad
	 */
	public Long getCantidad()
	{
		return il_cantidad;
	}

	/**
	 * Modifica el valor de cantidad aperturar.
	 *
	 * @param al_l asigna el valor a la propiedad cantidad aperturar
	 */
	public void setCantidadAperturar(Long al_l)
	{
		il_cantidadAperturar = al_l;
	}

	/**
	 * Retorna el valor de cantidad aperturar.
	 *
	 * @return el valor de cantidad aperturar
	 */
	public Long getCantidadAperturar()
	{
		return il_cantidadAperturar;
	}

	/**
	 * Modifica el valor de certificado asociado.
	 *
	 * @param ab_b asigna el valor a la propiedad certificado asociado
	 */
	public void setCertificadoAsociado(boolean ab_b)
	{
		ib_certificadoAsociado = ab_b;
	}

	/**
	 * Valida la propiedad certificado asociado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en certificado asociado
	 */
	public boolean isCertificadoAsociado()
	{
		return ib_certificadoAsociado;
	}

	/**
	 * Modifica el valor de consecutivo.
	 *
	 * @param ai_i asigna el valor a la propiedad consecutivo
	 */
	public void setConsecutivo(int ai_i)
	{
		ii_consecutivo = ai_i;
	}

	/**
	 * Retorna el valor de consecutivo.
	 *
	 * @return el valor de consecutivo
	 */
	public int getConsecutivo()
	{
		return ii_consecutivo;
	}

	/**
	 * Modifica el valor de correccion.
	 *
	 * @param ab_b asigna el valor a la propiedad correccion
	 */
	public void setCorreccion(boolean ab_b)
	{
		ib_correccion = ab_b;
	}

	/**
	 * Valida la propiedad correccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en correccion
	 */
	public boolean isCorreccion()
	{
		return ib_correccion;
	}

	/**
	 * Modifica el valor de direccion.
	 *
	 * @param as_s asigna el valor a la propiedad direccion
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = StringUtils.getStringUpperCase(as_s);
	}

	/**
	 * Retorna el valor de direccion.
	 *
	 * @return el valor de direccion
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * @param Modifica el valor de la propiedad esPredioInconsistente por esPredioInconsistente
	 */
	public void setEsPredioInconsistente(boolean ab_b)
	{
		ib_esPredioInconsistente = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esPredioInconsistente
	 */
	public boolean isEsPredioInconsistente()
	{
		return ib_esPredioInconsistente;
	}

	/**
	 * Modifica el valor de es tipo acto 700 valido.
	 *
	 * @param ab_b asigna el valor a la propiedad es tipo acto 700 valido
	 */
	public void setEsTipoActo700Valido(boolean ab_b)
	{
		ib_esTipoActo700Valido = ab_b;
	}

	/**
	 * Valida la propiedad es tipo acto 700 valido.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es tipo acto 700 valido
	 */
	public boolean isEsTipoActo700Valido()
	{
		return ib_esTipoActo700Valido;
	}

	/**
	 * Modifica el valor de estado.
	 *
	 * @param as_s asigna el valor a la propiedad estado
	 */
	public void setEstado(String as_s)
	{
		is_estado = StringUtils.getStringUpperCase(as_s);
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
	 * Modifica el valor de habilitar cert.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar cert
	 */
	public void setHabilitarCert(boolean ab_b)
	{
		ib_habilitarCert = ab_b;
	}

	/**
	 * Valida la propiedad habilitar cert.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar cert
	 */
	public boolean isHabilitarCert()
	{
		return ib_habilitarCert;
	}

	/**
	 * Modifica el valor de habilitar certificados.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar certificados
	 */
	public void setHabilitarCertificados(boolean ab_b)
	{
		ib_habilitarCertificados = ab_b;
	}

	/**
	 * Valida la propiedad habilitar certificados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar certificados
	 */
	public boolean isHabilitarCertificados()
	{
		return ib_habilitarCertificados;
	}

	/**
	 * Modifica el valor de habilitar detalle area.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar detalle area
	 */
	public void setHabilitarDetalleArea(boolean ab_b)
	{
		ib_habilitarDetalleArea = ab_b;
	}

	/**
	 * Valida la propiedad habilitar detalle area.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar detalle area
	 */
	public boolean isHabilitarDetalleArea()
	{
		return ib_habilitarDetalleArea;
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
	 * @param Modifica el valor de la propiedad idCirculoDestino por idCirculoDestino
	 */
	public void setIdCirculoDestino(String as_s)
	{
		is_idCirculoDestino = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idCirculoDestino
	 */
	public String getIdCirculoDestino()
	{
		return is_idCirculoDestino;
	}

	/**
	 * Metodo que modifica el valor de la propiedad.
	 *
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id matricula.
	 *
	 * @param as_s asigna el valor a la propiedad id matricula
	 */
	public void setIdMatricula(Long as_s)
	{
		il_idMatricula = as_s;
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
	 * Modifica el valor de id turno certificado.
	 *
	 * @param as_s asigna el valor a la propiedad id turno certificado
	 */
	public void setIdTurnoCertificado(String as_s)
	{
		is_idTurnoCertificado = as_s;
	}

	/**
	 * Retorna el valor de id turno certificado.
	 *
	 * @return el valor de id turno certificado
	 */
	public String getIdTurnoCertificado()
	{
		return is_idTurnoCertificado;
	}

	/**
	 * Modifica el valor de ingreso nuevo.
	 *
	 * @param ab_b asigna el valor a la propiedad ingreso nuevo
	 */
	public void setIngresoNuevo(boolean ab_b)
	{
		ib_ingresoNuevo = ab_b;
	}

	/**
	 * Valida la propiedad ingreso nuevo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ingreso nuevo
	 */
	public boolean isIngresoNuevo()
	{
		return ib_ingresoNuevo;
	}

	/**
	 * Modifica el valor de matricula.
	 *
	 * @param as_s asigna el valor a la propiedad matricula
	 */
	public void setMatricula(String as_s)
	{
		is_matricula = StringUtils.getStringUpperCase(as_s);
	}

	/**
	 * Retorna el valor de matricula.
	 *
	 * @return el valor de matricula
	 */
	public String getMatricula()
	{
		if(!StringUtils.isValidString(is_matricula))
			is_matricula = (StringUtils.isValidString(getIdCirculo()) && NumericUtils.isValidLong(getIdMatricula()))
				? (getIdCirculo() + "-" + getIdMatricula()) : null;

		return is_matricula;
	}

	/**
	 * Modifica el valor de matricula cargada.
	 *
	 * @param ab_b asigna el valor a la propiedad matricula cargada
	 */
	public void setMatriculaCargada(boolean ab_b)
	{
		ib_matriculaCargada = ab_b;
	}

	/**
	 * Valida la propiedad matricula cargada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en matricula cargada
	 */
	public boolean isMatriculaCargada()
	{
		return ib_matriculaCargada;
	}

	/**
	 * Modifica el valor de numero predial.
	 *
	 * @param as_s asigna el valor a la propiedad numero predial
	 */
	public void setNumeroPredial(String as_s)
	{
		is_numeroPredial = as_s;
	}

	/**
	 * Retorna el valor de numero predial.
	 *
	 * @return el valor de numero predial
	 */
	public String getNumeroPredial()
	{
		return is_numeroPredial;
	}

	/**
	 * Modifica el valor de nupre.
	 *
	 * @param as_s asigna el valor a la propiedad nupre
	 */
	public void setNupre(String as_s)
	{
		is_nupre = as_s;
	}

	/**
	 * Retorna el valor de nupre.
	 *
	 * @return el valor de nupre
	 */
	public String getNupre()
	{
		return is_nupre;
	}

	/**
	 * Modifica el valor de registro parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad registro parcial
	 */
	public void setRegistroParcial(boolean ab_b)
	{
		ib_registroParcial = ab_b;
	}

	/**
	 * Valida la propiedad registro parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en registro parcial
	 */
	public boolean isRegistroParcial()
	{
		return ib_registroParcial;
	}

	/**
	 * Modifica el valor de tipo acto 700.
	 *
	 * @param ab_b asigna el valor a la propiedad tipo acto 700
	 */
	public void setTipoActo700(boolean ab_b)
	{
		ib_tipoActo700 = ab_b;
	}

	/**
	 * Valida la propiedad tipo acto 700.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tipo acto 700
	 */
	public boolean isTipoActo700()
	{
		return ib_tipoActo700;
	}

	/**
	 * Modifica el valor de validar area.
	 *
	 * @param ab_b asigna el valor a la propiedad validar area
	 */
	public void setValidarArea(boolean ab_b)
	{
		ib_validarArea = ab_b;
	}

	/**
	 * Valida la propiedad validar area.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validar area
	 */
	public boolean isValidarArea()
	{
		return ib_validarArea;
	}

	/**
	 * Change acto IU.
	 *
	 * @param atsma_actos correspondiente al valor del tipo de objeto TmpSolicitudMatriculaActo
	 */
	public void changeActoIU(TmpSolicitudMatriculaActo atsma_actos)
	{
		Map<String, Boolean> lmsb_actos;

		lmsb_actos = new HashMap<String, Boolean>(getActos());

		if(atsma_actos != null)
		{
			String ls_idActo;

			ls_idActo = atsma_actos.getIdActo();

			if(StringUtils.isValidString(ls_idActo))
				lmsb_actos.put(ls_idActo, new Boolean(true));
		}

		setActos(lmsb_actos);
	}

	/**
	 * Change acto IU.
	 *
	 * @param atsma_actos correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 */
	public void changeActoIU(SolicitudMatriculaActo atsma_actos)
	{
		Map<String, Boolean> lmsb_actos;

		lmsb_actos = new HashMap<String, Boolean>(getActos());

		if(atsma_actos != null)
		{
			String ls_idActo;

			ls_idActo = atsma_actos.getIdActo();

			if(StringUtils.isValidString(ls_idActo))
				lmsb_actos.put(ls_idActo, new Boolean(true));
		}

		setActos(lmsb_actos);
	}
}
