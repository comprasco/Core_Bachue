package com.bachue.snr.prosnr01.model.registro;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades Direccion.
 *
 * @author  Julian David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
public class Direccion extends Auditoria implements Serializable
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = 5380765400127364291L;

	/**
	 * Propiedad al id matricula.
	 */
	private Long il_idMatricula;

	/**
	 * Propiedad is complemento direccion.
	 */
	private String is_complementoDireccion;

	/**
	 * Propiedad is dato 2 eje secundario.
	 */
	private String is_dato2EjeSecundario;

	/**
	 * Propiedad is dato eje principal.
	 */
	private String is_datoEjePrincipal;

	/**
	 * Propiedad is dato eje secundario.
	 */
	private String is_datoEjeSecundario;

	/**
	 * Propiedad is direccion.
	 */
	private String is_direccion;

	/**
	 * Propiedad is id circulo.
	 */
	private String is_idCirculo;

	/**
	 * Propiedad is id complemento 1 eje secundario.
	 */
	private String is_idComplemento1EjeSecundario;

	/**
	 * Propiedad is id complemento 1 eje secundario str.
	 */
	private String is_idComplemento1EjeSecundarioStr;

	/**
	 * Propiedad is id complemento 2 eje secundario.
	 */
	private String is_idComplemento2EjeSecundario;

	/**
	 * Propiedad is id complemento 2 eje secundario str.
	 */
	private String is_idComplemento2EjeSecundarioStr;

	/**
	 * Propiedad is id complemento direccion.
	 */
	private String is_idComplementoDireccion;

	/**
	 * Propiedad is id complemento direccion str.
	 */
	private String is_idComplementoDireccionStr;

	/**
	 * Propiedad is id complemento eje principal.
	 */
	private String is_idComplementoEjePrincipal;

	/**
	 * Propiedad is id complemento eje principal str.
	 */
	private String is_idComplementoEjePrincipalStr;

	/**
	 * Propiedad is id coordenada 1 eje secundario.
	 */
	private String is_idCoordenada1EjeSecundario;

	/**
	 * Propiedad is id coordenada 1 eje secundario str.
	 */
	private String is_idCoordenada1EjeSecundarioStr;

	/**
	 * Propiedad is id coordenada 2 eje secundario.
	 */
	private String is_idCoordenada2EjeSecundario;

	/**
	 * Propiedad is id coordenada 2 eje secundario str.
	 */
	private String is_idCoordenada2EjeSecundarioStr;

	/**
	 * Propiedad is id coordenada eje principal.
	 */
	private String is_idCoordenadaEjePrincipal;

	/**
	 * Propiedad is id coordenada eje principal str.
	 */
	private String is_idCoordenadaEjePrincipalStr;

	/**
	 * Propiedad is id departamento.
	 */
	private String is_idDepartamento;

	/**
	 * Propiedad is id direccion.
	 */
	private String is_idDireccion;

	/**
	 * Propiedad is id direccion predio.
	 */
	private String is_idDireccionPredio;

	/**
	 * Propiedad is id letra 1 eje secundario.
	 */
	private String is_idLetra1EjeSecundario;

	/**
	 * Propiedad is id letra 2 eje secundario.
	 */
	private String is_idLetra2EjeSecundario;

	/**
	 * Propiedad is id letra eje principal.
	 */
	private String is_idLetraEjePrincipal;

	/**
	 * Propiedad is id municipio.
	 */
	private String is_idMunicipio;

	/**
	 * Propiedad is id pais.
	 */
	private String is_idPais;

	/**
	 * Propiedad is id tipo eje principal.
	 */
	private String is_idTipoEjePrincipal;

	/**
	 * Propiedad is id tipo eje principal str.
	 */
	private String is_idTipoEjePrincipalStr;

	/**
	 * Propiedad is id tipo eje secundario.
	 */
	private String is_idTipoEjeSecundario;

	/**
	 * Propiedad is id tipo eje secundario str.
	 */
	private String is_idTipoEjeSecundarioStr;

	/**
	 * Propiedad is id tipo predio.
	 */
	private String is_idTipoPredio;

	/**
	 * Propiedad is id vereda.
	 */
	private String is_idVereda;

	/**
	 * Propiedad is tipo predio.
	 */
	private String is_tipoPredio;

	/**
	 * Propiedad ib eliminar.
	 */
	private boolean ib_eliminar;

	/**
	 * Propiedad ib seleccionado.
	 */
	private boolean ib_seleccionado;

	/**
	 * Instancia un nuevo objeto direccion.
	 *
	 * @param adpa_dp de adpa dp
	 */
	public Direccion(DireccionPredioAcc adpa_dp)
	{
		if(adpa_dp != null)
		{
			is_idCirculo                       = adpa_dp.getIdCirculo();
			il_idMatricula                     = adpa_dp.getIdMatricula();
			is_idDireccion                     = adpa_dp.getIdDireccion();
			is_idDireccionPredio               = adpa_dp.getIdDireccionPredio();
			is_idTipoPredio                    = adpa_dp.getIdTipoPredio();
			is_idTipoEjePrincipal              = adpa_dp.getIdTipoEjePrincipal();
			is_datoEjePrincipal                = adpa_dp.getDatoEjePrincipal();
			is_idLetraEjePrincipal             = adpa_dp.getIdLetraEjePrincipal();
			is_idComplementoEjePrincipal       = adpa_dp.getIdComplementoEjePrincipal();
			is_idCoordenadaEjePrincipal        = adpa_dp.getIdCoordenadaEjePrincipal();
			is_idTipoEjeSecundario             = adpa_dp.getIdTipoEjeSecundario();
			is_datoEjeSecundario               = adpa_dp.getDatoEjeSecundario();
			is_idLetra1EjeSecundario           = adpa_dp.getIdLetra1EjeSecundario();
			is_idComplemento1EjeSecundario     = adpa_dp.getIdComplemento1EjeSecundario();
			is_idCoordenada1EjeSecundario      = adpa_dp.getIdCoordenada1EjeSecundario();
			is_dato2EjeSecundario              = adpa_dp.getDato2EjeSecundario();
			is_idLetra2EjeSecundario           = adpa_dp.getIdLetra2EjeSecundario();
			is_idComplemento2EjeSecundario     = adpa_dp.getIdComplemento2EjeSecundario();
			is_idCoordenada2EjeSecundario      = adpa_dp.getIdCoordenada2EjeSecundario();
			is_idComplementoDireccion          = adpa_dp.getIdComplementoDireccion();
			is_complementoDireccion            = adpa_dp.getComplementoDireccion();
			is_direccion                       = adpa_dp.getDireccion();
			ib_seleccionado                    = adpa_dp.isSeleccionado();
			ib_eliminar                        = adpa_dp.isEliminar();
		}
	}

	/**
	 * Instancia un nuevo objeto direccion.
	 *
	 * @param ad_d de adp dp
	 */
	public Direccion(DireccionPredio ad_d)
	{
		if(ad_d != null)
		{
			is_idCirculo                       = ad_d.getIdCirculo();
			il_idMatricula                     = ad_d.getIdMatricula();
			is_idDireccion                     = ad_d.getIdDireccion();
			is_idTipoPredio                    = ad_d.getIdTipoPredio();
			is_idTipoEjePrincipal              = ad_d.getIdTipoEjePrincipal();
			is_datoEjePrincipal                = ad_d.getDatoEjePrincipal();
			is_idLetraEjePrincipal             = ad_d.getIdLetraEjePrincipal();
			is_idComplementoEjePrincipal       = ad_d.getIdComplementoEjePrincipal();
			is_idCoordenadaEjePrincipal        = ad_d.getIdCoordenadaEjePrincipal();
			is_idTipoEjeSecundario             = ad_d.getIdTipoEjeSecundario();
			is_datoEjeSecundario               = ad_d.getDatoEjeSecundario();
			is_idLetra1EjeSecundario           = ad_d.getIdLetra1EjeSecundario();
			is_idComplemento1EjeSecundario     = ad_d.getIdComplemento1EjeSecundario();
			is_idCoordenada1EjeSecundario      = ad_d.getIdCoordenada1EjeSecundario();
			is_dato2EjeSecundario              = ad_d.getDato2EjeSecundario();
			is_idLetra2EjeSecundario           = ad_d.getIdLetra2EjeSecundario();
			is_idComplemento2EjeSecundario     = ad_d.getIdComplemento2EjeSecundario();
			is_idCoordenada2EjeSecundario      = ad_d.getIdCoordenada2EjeSecundario();
			is_idComplementoDireccion          = ad_d.getIdComplementoDireccion();
			is_complementoDireccion            = ad_d.getComplementoDireccion();
			is_direccion                       = ad_d.getDireccion();
			ib_seleccionado                    = ad_d.isSeleccionado();
		}
	}

	/**
	 * Instancia un nuevo objeto direccion.
	 *
	 * @param apd_pd de apd_pd
	 */
	public Direccion(PersonaDireccion apd_pd)
	{
		if(apd_pd != null)
		{
			is_idDireccion                     = apd_pd.getIdDireccion();
			is_idTipoPredio                    = apd_pd.getIdTipoPredio();
			is_idTipoEjePrincipal              = apd_pd.getIdTipoEjePrincipal();
			is_datoEjePrincipal                = apd_pd.getDatoEjePrincipal();
			is_idLetraEjePrincipal             = apd_pd.getIdLetraEjePrincipal();
			is_idComplementoEjePrincipal       = apd_pd.getIdComplementoEjePrincipal();
			is_idCoordenadaEjePrincipal        = apd_pd.getIdCoordenadaEjePrincipal();
			is_idTipoEjeSecundario             = apd_pd.getIdTipoEjeSecundario();
			is_datoEjeSecundario               = apd_pd.getDatoEjeSecundario();
			is_idLetra1EjeSecundario           = apd_pd.getIdLetra1EjeSecundario();
			is_idComplemento1EjeSecundario     = apd_pd.getIdComplemento1EjeSecundario();
			is_idCoordenada1EjeSecundario      = apd_pd.getIdCoordenada1EjeSecundario();
			is_dato2EjeSecundario              = apd_pd.getDato2EjeSecundario();
			is_idLetra2EjeSecundario           = apd_pd.getIdLetra2EjeSecundario();
			is_idComplemento2EjeSecundario     = apd_pd.getIdComplemento2EjeSecundario();
			is_idCoordenada2EjeSecundario      = apd_pd.getIdCoordenada2EjeSecundario();
			is_idComplementoDireccion          = apd_pd.getIdComplementoDireccion();
			is_complementoDireccion            = apd_pd.getComplementoDireccion();
			is_direccion                       = apd_pd.getDireccion();
		}
	}

	/**
	 * Instancia un nuevo objeto direccion.
	 *
	 * @param ad_d de ad d
	 */
	public Direccion(Direccion ad_d)
	{
		if(ad_d != null)
		{
			is_idTipoPredio       = ad_d.getIdTipoPredio();
			is_idPais             = ad_d.getIdPais();
			is_idDepartamento     = ad_d.getIdDepartamento();
			is_idMunicipio        = ad_d.getIdMunicipio();
		}
	}

	/**
	 * Instancia un nuevo objeto direccion.
	 */
	public Direccion()
	{
	}

	/**
	 * Modifica el valor de Complemento direccion.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad ComplementoDireccion
	 */
	public void setComplementoDireccion(String as_s)
	{
		is_complementoDireccion = as_s;
	}

	/**
	 * Get complemento direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComplementoDireccion()
	{
		return is_complementoDireccion;
	}

	/**
	 * Modifica el valor de Dato 2 eje secundario.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad Dato2EjeSecundario
	 */
	public void setDato2EjeSecundario(String as_s)
	{
		is_dato2EjeSecundario = as_s;
	}

	/**
	 * Get dato 2 eje secundario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDato2EjeSecundario()
	{
		return is_dato2EjeSecundario;
	}

	/**
	 * Modifica el valor de Dato eje principal.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad DatoEjePrincipal
	 */
	public void setDatoEjePrincipal(String as_s)
	{
		is_datoEjePrincipal = as_s;
	}

	/**
	 * Get dato eje principal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDatoEjePrincipal()
	{
		return is_datoEjePrincipal;
	}

	/**
	 * Modifica el valor de Dato eje secundario.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad DatoEjeSecundario
	 */
	public void setDatoEjeSecundario(String as_s)
	{
		is_datoEjeSecundario = as_s;
	}

	/**
	 * Get dato eje secundario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDatoEjeSecundario()
	{
		return is_datoEjeSecundario;
	}

	/**
	 * Modifica el valor de Direccion.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad Direccion
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Get direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Retorna Objeto o variable de valor direccion predio.
	 *
	 * @return el valor de direccion predio
	 */
	public DireccionPredio getDireccionPredio()
	{
		DireccionPredio ldp_dp;

		ldp_dp = new DireccionPredio();

		poblar(ldp_dp);

		return ldp_dp;
	}

	/**
	 * Retorna Objeto o variable de valor direccion predio acc.
	 *
	 * @return el valor de direccion predio acc
	 */
	public DireccionPredioAcc getDireccionPredioAcc()
	{
		DireccionPredioAcc ldpa_dpa;

		ldpa_dpa = new DireccionPredioAcc();

		poblar(ldpa_dpa);

		return ldpa_dpa;
	}

	/**
	 * Modifica el valor de Eliminar.
	 *
	 * @param ab_b Objeto o Variable de tipo boolean que asigna un valor a la propiedad Eliminar
	 */
	public void setEliminar(boolean ab_b)
	{
		ib_eliminar = ab_b;
	}

	/**
	 * Valida la propiedad eliminar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isEliminar()
	{
		return ib_eliminar;
	}

	/**
	 * Modifica el valor de Id circulo.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad IdCirculo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Get id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de Id complemento 1 eje secundario.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad IdComplemento1EjeSecundario
	 */
	public void setIdComplemento1EjeSecundario(String as_s)
	{
		is_idComplemento1EjeSecundario = as_s;
	}

	/**
	 * Get id complemento 1 eje secundario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdComplemento1EjeSecundario()
	{
		return is_idComplemento1EjeSecundario;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param idComplemento1EjeSecundarioStr the idComplemento1EjeSecundarioStr to set
	 */
	public void setIdComplemento1EjeSecundarioStr(String idComplemento1EjeSecundarioStr)
	{
		is_idComplemento1EjeSecundarioStr = idComplemento1EjeSecundarioStr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return the idComplemento1EjeSecundarioStr
	 */
	public String getIdComplemento1EjeSecundarioStr()
	{
		return is_idComplemento1EjeSecundarioStr;
	}

	/**
	 * Modifica el valor de Id complemento 2 eje secundario.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad IdComplemento2EjeSecundario
	 */
	public void setIdComplemento2EjeSecundario(String as_s)
	{
		is_idComplemento2EjeSecundario = as_s;
	}

	/**
	 * Get id complemento 2 eje secundario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdComplemento2EjeSecundario()
	{
		return is_idComplemento2EjeSecundario;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param idComplemento2EjeSecundarioStr the idComplemento2EjeSecundarioStr to set
	 */
	public void setIdComplemento2EjeSecundarioStr(String idComplemento2EjeSecundarioStr)
	{
		is_idComplemento2EjeSecundarioStr = idComplemento2EjeSecundarioStr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return the idComplemento2EjeSecundarioStr
	 */
	public String getIdComplemento2EjeSecundarioStr()
	{
		return is_idComplemento2EjeSecundarioStr;
	}

	/**
	 * Modifica el valor de Id complemento direccion.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad IdComplementoDireccion
	 */
	public void setIdComplementoDireccion(String as_s)
	{
		is_idComplementoDireccion = as_s;
	}

	/**
	 * Get id complemento direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdComplementoDireccion()
	{
		return is_idComplementoDireccion;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param idComplementoDireccionStr the idComplementoDireccionStr to set
	 */
	public void setIdComplementoDireccionStr(String idComplementoDireccionStr)
	{
		is_idComplementoDireccionStr = idComplementoDireccionStr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return the idComplementoDireccionStr
	 */
	public String getIdComplementoDireccionStr()
	{
		return is_idComplementoDireccionStr;
	}

	/**
	 * Modifica el valor de Id complemento eje principal.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad IdComplementoEjePrincipal
	 */
	public void setIdComplementoEjePrincipal(String as_s)
	{
		is_idComplementoEjePrincipal = as_s;
	}

	/**
	 * Get id complemento eje principal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdComplementoEjePrincipal()
	{
		return is_idComplementoEjePrincipal;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param idComplementoEjePrincipalStr the idComplementoEjePrincipalStr to set
	 */
	public void setIdComplementoEjePrincipalStr(String idComplementoEjePrincipalStr)
	{
		is_idComplementoEjePrincipalStr = idComplementoEjePrincipalStr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return the idComplementoEjePrincipalStr
	 */
	public String getIdComplementoEjePrincipalStr()
	{
		return is_idComplementoEjePrincipalStr;
	}

	/**
	 * Modifica el valor de Id coordenada 1 eje secundario.
	 *
	 * @param idCoordenada1EjeSecundario Objeto o Variable de tipo String que asigna un valor a la propiedad IdCoordenada1EjeSecundario
	 */
	public void setIdCoordenada1EjeSecundario(String idCoordenada1EjeSecundario)
	{
		is_idCoordenada1EjeSecundario = idCoordenada1EjeSecundario;
	}

	/**
	 * Get id coordenada 1 eje secundario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCoordenada1EjeSecundario()
	{
		return is_idCoordenada1EjeSecundario;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param idCoordenada1EjeSecundarioStr the idCoordenada1EjeSecundarioStr to set
	 */
	public void setIdCoordenada1EjeSecundarioStr(String idCoordenada1EjeSecundarioStr)
	{
		is_idCoordenada1EjeSecundarioStr = idCoordenada1EjeSecundarioStr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return the idCoordenada1EjeSecundarioStr
	 */
	public String getIdCoordenada1EjeSecundarioStr()
	{
		return is_idCoordenada1EjeSecundarioStr;
	}

	/**
	 * Modifica el valor de Id coordenada 2 eje secundario.
	 *
	 * @param idCoordenada2EjeSecundario Objeto o Variable de tipo String que asigna un valor a la propiedad IdCoordenada2EjeSecundario
	 */
	public void setIdCoordenada2EjeSecundario(String idCoordenada2EjeSecundario)
	{
		is_idCoordenada2EjeSecundario = idCoordenada2EjeSecundario;
	}

	/**
	 * Get id coordenada 2 eje secundario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCoordenada2EjeSecundario()
	{
		return is_idCoordenada2EjeSecundario;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param idCoordenada2EjeSecundarioStr the idCoordenada2EjeSecundarioStr to set
	 */
	public void setIdCoordenada2EjeSecundarioStr(String idCoordenada2EjeSecundarioStr)
	{
		is_idCoordenada2EjeSecundarioStr = idCoordenada2EjeSecundarioStr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return the idCoordenada2EjeSecundarioStr
	 */
	public String getIdCoordenada2EjeSecundarioStr()
	{
		return is_idCoordenada2EjeSecundarioStr;
	}

	/**
	 * Modifica el valor de Id coordenada eje principal.
	 *
	 * @param idCoordenadaEjePrincipal Objeto o Variable de tipo String que asigna un valor a la propiedad IdCoordenadaEjePrincipal
	 */
	public void setIdCoordenadaEjePrincipal(String idCoordenadaEjePrincipal)
	{
		is_idCoordenadaEjePrincipal = idCoordenadaEjePrincipal;
	}

	/**
	 * Get id coordenada eje principal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCoordenadaEjePrincipal()
	{
		return is_idCoordenadaEjePrincipal;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param idCoordenadaEjePrincipalStr the idCoordenadaEjePrincipalStr to set
	 */
	public void setIdCoordenadaEjePrincipalStr(String idCoordenadaEjePrincipalStr)
	{
		is_idCoordenadaEjePrincipalStr = idCoordenadaEjePrincipalStr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return the idCoordenadaEjePrincipalStr
	 */
	public String getIdCoordenadaEjePrincipalStr()
	{
		return is_idCoordenadaEjePrincipalStr;
	}

	/**
	 * Modifica el valor de Id departamento.
	 *
	 * @param idDepartamento Objeto o Variable de tipo String que asigna un valor a la propiedad IdDepartamento
	 */
	public void setIdDepartamento(String idDepartamento)
	{
		is_idDepartamento = idDepartamento;
	}

	/**
	 * Get id departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de Id direccion.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad IdDireccion
	 */
	public void setIdDireccion(String as_s)
	{
		is_idDireccion = as_s;
	}

	/**
	 * Get id direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDireccion()
	{
		return is_idDireccion;
	}

	/**
	 * Modifica el valor de Id direccion predio.
	 *
	 * @param as_s the idDireccionPredio to set
	 */
	public void setIdDireccionPredio(String as_s)
	{
		is_idDireccionPredio = as_s;
	}

	/**
	 * Get id direccion predio.
	 *
	 * @return the idDireccionPredio
	 */
	public String getIdDireccionPredio()
	{
		return is_idDireccionPredio;
	}

	/**
	 * Modifica el valor de Id letra 1 eje secundario.
	 *
	 * @param idLetra1EjeSecundario Objeto o Variable de tipo String que asigna un valor a la propiedad IdLetra1EjeSecundario
	 */
	public void setIdLetra1EjeSecundario(String idLetra1EjeSecundario)
	{
		is_idLetra1EjeSecundario = idLetra1EjeSecundario;
	}

	/**
	 * Get id letra 1 eje secundario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdLetra1EjeSecundario()
	{
		return is_idLetra1EjeSecundario;
	}

	/**
	 * Modifica el valor de Id letra 2 eje secundario.
	 *
	 * @param idLetra2EjeSecundario Objeto o Variable de tipo String que asigna un valor a la propiedad IdLetra2EjeSecundario
	 */
	public void setIdLetra2EjeSecundario(String idLetra2EjeSecundario)
	{
		is_idLetra2EjeSecundario = idLetra2EjeSecundario;
	}

	/**
	 * Get id letra 2 eje secundario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdLetra2EjeSecundario()
	{
		return is_idLetra2EjeSecundario;
	}

	/**
	 * Modifica el valor de Id letra eje principal.
	 *
	 * @param idLetraEjePrincipal Objeto o Variable de tipo String que asigna un valor a la propiedad IdLetraEjePrincipal
	 */
	public void setIdLetraEjePrincipal(String idLetraEjePrincipal)
	{
		is_idLetraEjePrincipal = idLetraEjePrincipal;
	}

	/**
	 * Get id letra eje principal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdLetraEjePrincipal()
	{
		return is_idLetraEjePrincipal;
	}

	/**
	 * Modifica el valor de Id matricula.
	 *
	 * @param al_l Objeto o Variable de tipo Long que asigna un valor a la propiedad IdMatricula
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Get id matricula.
	 *
	 * @return the idMatricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de Id municipio.
	 *
	 * @param idMunicipio Objeto o Variable de tipo String que asigna un valor a la propiedad IdMunicipio
	 */
	public void setIdMunicipio(String idMunicipio)
	{
		is_idMunicipio = idMunicipio;
	}

	/**
	 * Get id municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de Id pais.
	 *
	 * @param idPais Objeto o Variable de tipo String que asigna un valor a la propiedad IdPais
	 */
	public void setIdPais(String idPais)
	{
		is_idPais = idPais;
	}

	/**
	 * Get id pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de Id tipo eje principal.
	 *
	 * @param idTipoEjePrincipal Objeto o Variable de tipo String que asigna un valor a la propiedad IdTipoEjePrincipal
	 */
	public void setIdTipoEjePrincipal(String idTipoEjePrincipal)
	{
		is_idTipoEjePrincipal = idTipoEjePrincipal;
	}

	/**
	 * Get id tipo eje principal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoEjePrincipal()
	{
		return is_idTipoEjePrincipal;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param idTipoEjePrincipalStr the idTipoEjePrincipalStr to set
	 */
	public void setIdTipoEjePrincipalStr(String idTipoEjePrincipalStr)
	{
		is_idTipoEjePrincipalStr = idTipoEjePrincipalStr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return the idTipoEjePrincipalStr
	 */
	public String getIdTipoEjePrincipalStr()
	{
		return is_idTipoEjePrincipalStr;
	}

	/**
	 * Modifica el valor de Id tipo eje secundario.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad IdTipoEjeSecundario
	 */
	public void setIdTipoEjeSecundario(String as_s)
	{
		is_idTipoEjeSecundario = as_s;
	}

	/**
	 * Get id tipo eje secundario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoEjeSecundario()
	{
		if(!StringUtils.isValidString(is_idTipoEjeSecundario))
			is_idTipoEjeSecundario = IdentificadoresCommon.NU;

		return is_idTipoEjeSecundario;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param idTipoEjeSecundarioStr the idTipoEjeSecundarioStr to set
	 */
	public void setIdTipoEjeSecundarioStr(String idTipoEjeSecundarioStr)
	{
		is_idTipoEjeSecundarioStr = idTipoEjeSecundarioStr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return the idTipoEjeSecundarioStr
	 */
	public String getIdTipoEjeSecundarioStr()
	{
		return is_idTipoEjeSecundarioStr;
	}

	/**
	 * Modifica el valor de Id tipo predio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoPredio(String as_s)
	{
		is_idTipoPredio = as_s;
	}

	/**
	 * Get id tipo predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPredio()
	{
		return is_idTipoPredio;
	}

	/**
	 * Modifica el valor de IdVereda.
	 *
	 * @param as_s the idVereda to set
	 */
	public void setIdVereda(String as_s)
	{
		is_idVereda = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id vereda.
	 *
	 * @return the idVereda
	 */
	public String getIdVereda()
	{
		return is_idVereda;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b Objeto o Variable de tipo boolean que asigna un valor a la propiedad Seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de Tipo predio.
	 *
	 * @param tipoDireccion Objeto o Variable de tipo String que asigna un valor a la propiedad TipoPredio
	 */
	public void setTipoPredio(String tipoDireccion)
	{
		is_tipoPredio = tipoDireccion;
	}

	/**
	 * Get tipo predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoPredio()
	{
		return is_tipoPredio;
	}

	/**
	 * Poblar.
	 *
	 * @param ad_d de ad d
	 */
	private void poblar(Direccion ad_d)
	{
		if(ad_d != null)
		{
			ad_d.setIdCirculo(getIdCirculo());
			ad_d.setIdMatricula(getIdMatricula());
			ad_d.setIdDireccion(getIdDireccion());
			ad_d.setIdDireccionPredio(getIdDireccionPredio());
			ad_d.setIdTipoPredio(getIdTipoPredio());
			ad_d.setIdTipoEjePrincipal(getIdTipoEjePrincipal());
			ad_d.setDatoEjePrincipal(getDatoEjePrincipal());
			ad_d.setIdLetraEjePrincipal(getIdLetraEjePrincipal());
			ad_d.setIdComplementoEjePrincipal(getIdComplementoEjePrincipal());
			ad_d.setIdCoordenadaEjePrincipal(getIdCoordenadaEjePrincipal());
			ad_d.setIdTipoEjeSecundario(getIdTipoEjeSecundario());
			ad_d.setDatoEjeSecundario(getDatoEjeSecundario());
			ad_d.setIdLetra1EjeSecundario(getIdLetra1EjeSecundario());
			ad_d.setIdComplemento1EjeSecundario(getIdComplemento1EjeSecundario());
			ad_d.setIdCoordenada1EjeSecundario(getIdCoordenada1EjeSecundario());
			ad_d.setDato2EjeSecundario(getDato2EjeSecundario());
			ad_d.setIdLetra2EjeSecundario(getIdLetra2EjeSecundario());
			ad_d.setIdComplemento2EjeSecundario(getIdComplemento2EjeSecundario());
			ad_d.setIdCoordenada2EjeSecundario(getIdCoordenada2EjeSecundario());
			ad_d.setIdComplementoDireccion(getIdComplementoDireccion());
			ad_d.setComplementoDireccion(getComplementoDireccion());
			ad_d.setDireccion(getDireccion());
			ad_d.setSeleccionado(isSeleccionado());
			ad_d.setEliminar(isEliminar());
		}
	}
}
