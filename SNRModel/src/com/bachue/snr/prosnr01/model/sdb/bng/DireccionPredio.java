package com.bachue.snr.prosnr01.model.sdb.bng;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.registro.Direccion;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_BNG_DIRECCION_PREDIO.
 *
 * @author garias
 */
public class DireccionPredio extends Direccion implements Serializable
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = -6822602351360446731L;

	/**
	 * Propiedad icdp direcciones predio.
	 */
	private Collection<DireccionPredio> icdp_direccionesPredio;

	/**
	 * Propiedad is departamento.
	 */
	private String is_departamento;

	/**
	 * Propiedad is municipio.
	 */
	private String is_municipio;

	/**
	 * Propiedad is pais.
	 */
	private String is_pais;

	/**
	 * Propiedad ib agregado.
	 */
	private boolean ib_agregado;

	/**
	 * Propiedad ib consulta predio.
	 */
	private boolean ib_consultaPredio;

	/**
	 * Instancia un nuevo objeto direccion predio.
	 */
	public DireccionPredio()
	{
	}

	/**
	 * Instancia un nuevo objeto direccion predio.
	 *
	 * @param ad_direccion de ad direccion
	 */
	public DireccionPredio(Direccion ad_direccion, boolean ab_complemento)
	{
		if(ad_direccion != null)
		{
			setIdTipoEjePrincipal(ad_direccion.getIdTipoEjePrincipal());
			setIdTipoEjeSecundario(ad_direccion.getIdTipoEjeSecundario());
			setDatoEjePrincipal(StringUtils.getStringUpperCase(ad_direccion.getDatoEjePrincipal()));
			setDatoEjeSecundario(StringUtils.getStringUpperCase(ad_direccion.getDatoEjeSecundario()));
			setComplementoDireccion(StringUtils.getStringUpperCase(ad_direccion.getComplementoDireccion()));
			setIdComplementoDireccion(ad_direccion.getIdComplementoDireccion());
			setDireccion(ad_direccion.getDireccion());
		}
	}

	/**
	 * Instancia un nuevo objeto direccion predio.
	 *
	 * @param ad_direccion de ad direccion
	 */
	public DireccionPredio(Direccion ad_direccion)
	{
		new DireccionPredio(ad_direccion, false);
	}

	/**
	 * Modifica el valor de Agregado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAgregado(boolean ab_b)
	{
		ib_agregado = ab_b;
	}

	/**
	 * Valida la propiedad agregado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAgregado()
	{
		return ib_agregado;
	}

	/**
	 * Modifica el valor de Consulta predio.
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
	 * Modifica el valor de Departamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDepartamento(String as_s)
	{
		is_departamento = as_s;
	}

	/**
	 * Get departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDepartamento()
	{
		return is_departamento;
	}

	/**
	 * Modifica el valor de Direcciones predio.
	 *
	 * @param acdp_dp asigna el valor a la propiedad
	 */
	public void setDireccionesPredio(Collection<DireccionPredio> acdp_dp)
	{
		icdp_direccionesPredio = acdp_dp;
	}

	/**
	 * Get direcciones predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<DireccionPredio> getDireccionesPredio()
	{
		return icdp_direccionesPredio;
	}

	/**
	 * Valida la propiedad matricula valida.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en matricula valida
	 */
	public boolean isMatriculaValida()
	{
		return StringUtils.isValidString(getDireccion());
	}

	/**
	 * Modifica el valor de Municipio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMunicipio(String as_s)
	{
		is_municipio = as_s;
	}

	/**
	 * Get municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMunicipio()
	{
		return is_municipio;
	}

	/**
	 * Modifica el valor de Pais.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPais(String as_s)
	{
		is_pais = as_s;
	}

	/**
	 * Get pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPais()
	{
		return is_pais;
	}
}
