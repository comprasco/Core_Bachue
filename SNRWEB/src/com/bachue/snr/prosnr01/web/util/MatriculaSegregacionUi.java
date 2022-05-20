package com.bachue.snr.prosnr01.web.util;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades de MatriculaSegregacionUi.
 *
 * @author jpatino
 */
public class MatriculaSegregacionUi implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long    serialVersionUID        = -8756656916140370002L;
	
	/** Propiedad id area construida. */
	private Double               id_areaConstruida;
	
	/** Propiedad id area privada. */
	private Double               id_areaPrivada;
	
	/** Propiedad id area terreno. */
	private Double               id_areaTerreno;
	
	/** Propiedad id coeficiente. */
	private Double               id_coeficiente;
	
	/** Propiedad il cantidad. */
	private Long                 il_cantidad;
	
	/** Propiedad il cantidad certificados. */
	private Long                 il_cantidadCertificados;
	
	/** Propiedad il matricula matriz. */
	private Long                 il_matriculaMatriz;
	
	/** Propiedad il unidad. */
	private Long                 il_unidad;
	
	/** Propiedad imsb actos. */
	private Map<String, Boolean> imsb_actos;
	
	/** Propiedad is area construida str. */
	private String               is_areaConstruidaStr;
	
	/** Propiedad is area privada str. */
	private String               is_areaPrivadaStr;
	
	/** Propiedad is area terreno str. */
	private String               is_areaTerrenoStr;
	
	/** Propiedad is coeficiente str. */
	private String               is_coeficienteStr;
	
	/** Propiedad is complemento direccion. */
	private String               is_complementoDireccion;
	
	/** Propiedad is direccion. */
	private String               is_direccion;
	
	/** Propiedad is id circulo matriz. */
	private String               is_idCirculoMatriz;
	
	/** Propiedad is id solicitud. */
	private String               is_idSolicitud;
	
	/** Propiedad is ip creacion. */
	private String               is_ipCreacion;
	
	/** Propiedad is ip modificacion. */
	private String               is_ipModificacion;
	
	/** Propiedad is matricula segregacion. */
	private String               is_matriculaSegregacion;
	
	/** Propiedad is nombre predio. */
	private String               is_nombrePredio;
	
	/** Propiedad is turno certificado. */
	private String               is_turnoCertificado;
	
	/** Propiedad is usuario gestion. */
	private String               is_usuarioGestion;
	
	/** Propiedad ib omitir matricula. */
	private boolean              ib_omitirMatricula;
	
	/** Propiedad ib certificado asociado. */
	private boolean              ib_certificadoAsociado;

	/**
	 * Instancia un nuevo objeto matricula segregacion ui.
	 */
	public MatriculaSegregacionUi()
	{
	}

	/**
	 * Instancia un nuevo objeto matricula segregacion ui.
	 *
	 * @param accm_actos correspondiente al valor del tipo de objeto Collection<ColumnModel>
	 */
	public MatriculaSegregacionUi(Collection<ColumnModel> accm_actos)
	{
		if(CollectionUtils.isValidCollection(accm_actos))
		{
			Map<String, Boolean> lmsb_actos;

			lmsb_actos                                   = new HashMap<String, Boolean>();

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
		return imsb_actos;
	}

	/**
	 * Modifica el valor de omitir matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad omitir matricula
	 */
	public void setOmitirMatricula(boolean ab_b)
	{
		ib_omitirMatricula = ab_b;
	}

	/**
	 * Valida la propiedad omitir matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en omitir matricula
	 */
	public boolean isOmitirMatricula()
	{
		return ib_omitirMatricula;
	}

	/**
	 * Modifica el valor de area construida.
	 *
	 * @param ad_d asigna el valor a la propiedad area construida
	 */
	public void setAreaConstruida(Double ad_d)
	{
		id_areaConstruida = ad_d;
	}

	/**
	 * Retorna el valor de area construida.
	 *
	 * @return el valor de area construida
	 */
	public Double getAreaConstruida()
	{
		return id_areaConstruida;
	}

	/**
	 * Modifica el valor de area construida str.
	 *
	 * @param as_s asigna el valor a la propiedad area construida str
	 */
	public void setAreaConstruidaStr(String as_s)
	{
		is_areaConstruidaStr = as_s;
	}

	/**
	 * Retorna el valor de area construida str.
	 *
	 * @return el valor de area construida str
	 */
	public String getAreaConstruidaStr()
	{
		return is_areaConstruidaStr;
	}

	/**
	 * Modifica el valor de area privada.
	 *
	 * @param al_l asigna el valor a la propiedad area privada
	 */
	public void setAreaPrivada(Double al_l)
	{
		id_areaPrivada = al_l;
	}

	/**
	 * Retorna el valor de area privada.
	 *
	 * @return el valor de area privada
	 */
	public Double getAreaPrivada()
	{
		return id_areaPrivada;
	}

	/**
	 * Modifica el valor de area privada str.
	 *
	 * @param as_s asigna el valor a la propiedad area privada str
	 */
	public void setAreaPrivadaStr(String as_s)
	{
		is_areaPrivadaStr = as_s;
	}

	/**
	 * Retorna el valor de area privada str.
	 *
	 * @return el valor de area privada str
	 */
	public String getAreaPrivadaStr()
	{
		return is_areaPrivadaStr;
	}

	/**
	 * Modifica el valor de area terreno.
	 *
	 * @param ad_d asigna el valor a la propiedad area terreno
	 */
	public void setAreaTerreno(Double ad_d)
	{
		id_areaTerreno = ad_d;
	}

	/**
	 * Retorna el valor de area terreno.
	 *
	 * @return el valor de area terreno
	 */
	public Double getAreaTerreno()
	{
		return id_areaTerreno;
	}

	/**
	 * Modifica el valor de area terreno str.
	 *
	 * @param as_s asigna el valor a la propiedad area terreno str
	 */
	public void setAreaTerrenoStr(String as_s)
	{
		is_areaTerrenoStr = as_s;
	}

	/**
	 * Retorna el valor de area terreno str.
	 *
	 * @return el valor de area terreno str
	 */
	public String getAreaTerrenoStr()
	{
		return is_areaTerrenoStr;
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
	 * Modifica el valor de cantidad certificados.
	 *
	 * @param al_l asigna el valor a la propiedad cantidad certificados
	 */
	public void setCantidadCertificados(Long al_l)
	{
		il_cantidadCertificados = al_l;
	}

	/**
	 * Retorna el valor de cantidad certificados.
	 *
	 * @return el valor de cantidad certificados
	 */
	public Long getCantidadCertificados()
	{
		return il_cantidadCertificados;
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
	 * Modifica el valor de coeficiente.
	 *
	 * @param ad_d asigna el valor a la propiedad coeficiente
	 */
	public void setCoeficiente(Double ad_d)
	{
		id_coeficiente = ad_d;
	}

	/**
	 * Retorna el valor de coeficiente.
	 *
	 * @return el valor de coeficiente
	 */
	public Double getCoeficiente()
	{
		return id_coeficiente;
	}

	/**
	 * Modifica el valor de coeficiente str.
	 *
	 * @param as_s asigna el valor a la propiedad coeficiente str
	 */
	public void setCoeficienteStr(String as_s)
	{
		is_coeficienteStr = as_s;
	}

	/**
	 * Retorna el valor de coeficiente str.
	 *
	 * @return el valor de coeficiente str
	 */
	public String getCoeficienteStr()
	{
		return is_coeficienteStr;
	}

	/**
	 * Modifica el valor de complemento direccion.
	 *
	 * @param as_s asigna el valor a la propiedad complemento direccion
	 */
	public void setComplementoDireccion(String as_s)
	{
		is_complementoDireccion = as_s;
	}

	/**
	 * Retorna el valor de complemento direccion.
	 *
	 * @return el valor de complemento direccion
	 */
	public String getComplementoDireccion()
	{
		return is_complementoDireccion;
	}

	/**
	 * Modifica el valor de direccion.
	 *
	 * @param as_s asigna el valor a la propiedad direccion
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
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
	 * Modifica el valor de id circulo matriz.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo matriz
	 */
	public void setIdCirculoMatriz(String as_s)
	{
		is_idCirculoMatriz = as_s;
	}

	/**
	 * Retorna el valor de id circulo matriz.
	 *
	 * @return el valor de id circulo matriz
	 */
	public String getIdCirculoMatriz()
	{
		return is_idCirculoMatriz;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de ip creacion.
	 *
	 * @param as_s asigna el valor a la propiedad ip creacion
	 */
	public void setIpCreacion(String as_s)
	{
		is_ipCreacion = as_s;
	}

	/**
	 * Retorna el valor de ip creacion.
	 *
	 * @return el valor de ip creacion
	 */
	public String getIpCreacion()
	{
		return is_ipCreacion;
	}

	/**
	 * Modifica el valor de ip modificacion.
	 *
	 * @param as_s asigna el valor a la propiedad ip modificacion
	 */
	public void setIpModificacion(String as_s)
	{
		is_ipModificacion = as_s;
	}

	/**
	 * Retorna el valor de ip modificacion.
	 *
	 * @return el valor de ip modificacion
	 */
	public String getIpModificacion()
	{
		return is_ipModificacion;
	}

	/**
	 * Modifica el valor de matricula matriz.
	 *
	 * @param al_l asigna el valor a la propiedad matricula matriz
	 */
	public void setMatriculaMatriz(Long al_l)
	{
		il_matriculaMatriz = al_l;
	}

	/**
	 * Retorna el valor de matricula matriz.
	 *
	 * @return el valor de matricula matriz
	 */
	public Long getMatriculaMatriz()
	{
		return il_matriculaMatriz;
	}

	/**
	 * Modifica el valor de matricula segregacion.
	 *
	 * @param as_s asigna el valor a la propiedad matricula segregacion
	 */
	public void setMatriculaSegregacion(String as_s)
	{
		is_matriculaSegregacion = as_s;
	}

	/**
	 * Retorna el valor de matricula segregacion.
	 *
	 * @return el valor de matricula segregacion
	 */
	public String getMatriculaSegregacion()
	{
		return is_matriculaSegregacion;
	}

	/**
	 * Modifica el valor de nombre predio.
	 *
	 * @param as_s asigna el valor a la propiedad nombre predio
	 */
	public void setNombrePredio(String as_s)
	{
		is_nombrePredio = as_s;
	}

	/**
	 * Retorna el valor de nombre predio.
	 *
	 * @return el valor de nombre predio
	 */
	public String getNombrePredio()
	{
		return is_nombrePredio;
	}

	/**
	 * Retorna el valor de serialversionuid.
	 *
	 * @return el valor de serialversionuid
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	/**
	 * Modifica el valor de turno certificado.
	 *
	 * @param as_s asigna el valor a la propiedad turno certificado
	 */
	public void setTurnoCertificado(String as_s)
	{
		is_turnoCertificado = as_s;
	}

	/**
	 * Retorna el valor de turno certificado.
	 *
	 * @return el valor de turno certificado
	 */
	public String getTurnoCertificado()
	{
		return is_turnoCertificado;
	}

	/**
	 * Modifica el valor de unidad.
	 *
	 * @param al_l asigna el valor a la propiedad unidad
	 */
	public void setUnidad(Long al_l)
	{
		il_unidad = al_l;
	}

	/**
	 * Retorna el valor de unidad.
	 *
	 * @return el valor de unidad
	 */
	public Long getUnidad()
	{
		return il_unidad;
	}

	/**
	 * Modifica el valor de usuario gestion.
	 *
	 * @param as_s asigna el valor a la propiedad usuario gestion
	 */
	public void setUsuarioGestion(String as_s)
	{
		is_usuarioGestion = as_s;
	}

	/**
	 * Retorna el valor de usuario gestion.
	 *
	 * @return el valor de usuario gestion
	 */
	public String getUsuarioGestion()
	{
		return is_usuarioGestion;
	}
}
