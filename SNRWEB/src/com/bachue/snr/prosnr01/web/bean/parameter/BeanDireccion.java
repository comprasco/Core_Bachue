package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudDireccionCertificado;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de las direcciones en la capa web
 * @author garias
 *
 */
@ManagedBean(name = "beanDireccion")
@SessionScoped
public class BeanDireccion extends BaseBean implements Serializable
{
	private static final long              serialVersionUID                      = -7327679216926309774L;
	private static final String            is_formPredioAgregar                  = ":idFormDireccionPredioAgregarInclude";
	private static final String            is_formPredioAgregarAnotacion         = ":idFormDireccionPredioAgregarAnotacionInclude";
	private static final String            is_formPredio                         = ":idFormDireccionPredioInclude";
	private static final String            is_formCertificado                    = "idFormDireccionCertificadoInclude";
	private static final String            is_formResidencia                     = ":idFormDireccionResidenciaInclude";
	private static final String            is_formResidenciaInter                = ":idFormDireccionResidenciaInterInclude";
	private static final String            is_formCorrespondencia                = ":idFormDireccionCorrespondenciaInclude";
	private static final String            is_formCorrespondenciaInter           = ":idFormDireccionCorrespondenciaInterInclude";
	private Collection<Direccion>          icd_direccionesPredio;
	private Collection<DireccionDelPredio> icddp_direccionesPredio2;
	private Collection<DireccionDelPredio> icddp_direccionesTemporales;
	private Collection<TipoEje>            icte_tipoEjeSecundarioCorrespondencia;
	private Collection<TipoEje>            icte_tipoEjeSecundarioPredio;
	private Collection<TipoEje>            icte_tipoEjeSecundarioResidencia;
	private Direccion                      id_direccionPredio;
	@EJB
	private ParameterRemote                ipr_parameterRemote;
	private PersonaDireccion               ipd_direccionCorrespondencia;
	private PersonaDireccion               ipd_direccionResidencia;
	@EJB
	private ReferenceRemote                irr_referenceRemote;
	private SolicitudDireccionCertificado  isdc_direccionCertificado;
	private String                         is_datoParaValidar;
	private String                         is_form;
	private String                         is_mismaDireccionCorrespondencia;
	private boolean                        ib_agregarDireccionPredio;
	private boolean                        ib_anotacion;
	private boolean                        ib_bloquearCamposCertificados;
	private boolean                        ib_deshabilitarCorrespondencia;
	private boolean                        ib_deshabilitarDatosUbicacion;
	private boolean                        ib_deshabilitarResidencia;
	private boolean                        ib_deshanbilitarTipoPredio;
	private boolean                        ib_habilitadoPorConsulta;
	private boolean                        ib_modificarDireccionPredio;

	/**
	 * Modifica el valor de agregar direccion predio
	 *
	 * @param ab_b asigna el valor a la propiedad agregar direccion predio
	 */
	public void setAgregarDireccionPredio(boolean ab_b)
	{
		ib_agregarDireccionPredio                                                = ab_b;
	}

	/**
	 * Retorna el valor de agregar direccion predio
	 *
	 * @return el valor de agregar direccion predio
	 */
	public boolean isAgregarDireccionPredio()
	{
		return ib_agregarDireccionPredio;
	}

	/**
	 * @param Modifica el valor de la propiedad anotacion por anotacion
	 */
	public void setAnotacion(boolean ab_b)
	{
		ib_anotacion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad anotacion
	 */
	public boolean isAnotacion()
	{
		return ib_anotacion;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de al propiedad
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setBloquearCamposCertificados(boolean ab_b)
	{
		ib_bloquearCamposCertificados = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad
	 */
	public boolean isBloquearCamposCertificados()
	{
		return ib_bloquearCamposCertificados;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad deshabilitarCorrespondencia
	 */
	public void setDeshabilitarCorrespondencia(boolean ab_b)
	{
		ib_deshabilitarCorrespondencia = ab_b;
	}

	/**
	 * @return el valor de deshabilitarCorrespondencia
	 */
	public boolean isDeshabilitarCorrespondencia()
	{
		return ib_deshabilitarCorrespondencia;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad deshabilitarDatosUbicacion
	 */
	public void setDeshabilitarDatosUbicacion(boolean ab_b)
	{
		ib_deshabilitarDatosUbicacion = ab_b;
	}

	/**
	 * @return el valor de deshabilitarDatosUbicacion
	 */
	public boolean isDeshabilitarDatosUbicacion()
	{
		return ib_deshabilitarDatosUbicacion;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad deshabilitarResidencia
	 */
	public void setDeshabilitarResidencia(boolean ab_b)
	{
		ib_deshabilitarResidencia = ab_b;
	}

	/**
	 * @return el valor de deshabilitarResidencia
	 */
	public boolean isDeshabilitarResidencia()
	{
		return ib_deshabilitarResidencia;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad deshanbilitarTipoPredio
	 */
	public void setDeshabilitarTipoPredio(boolean ab_b)
	{
		ib_deshanbilitarTipoPredio = ab_b;
	}

	/**
	 * @return el valor de deshanbilitarTipoPredio
	 */
	public boolean isDeshabilitarTipoPredio()
	{
		return ib_deshanbilitarTipoPredio;
	}

	/**
	 * @param asdc_sdc asigna el valor a la propiedad.
	 */
	public void setDireccionCertificado(SolicitudDireccionCertificado asdc_sdc)
	{
		isdc_direccionCertificado = asdc_sdc;
	}

	/**
	 * @return retorna el valor de la propiedad.
	 */
	public SolicitudDireccionCertificado getDireccionCertificado()
	{
		if(isdc_direccionCertificado == null)
		{
			isdc_direccionCertificado = new SolicitudDireccionCertificado();

			isdc_direccionCertificado.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return isdc_direccionCertificado;
	}

	/**
	 * Modifica el valor de dirección correspondencia
	 *
	 * @param adp_pd asigna el valor a la propiedad dirección correspondencia
	 */
	public void setDireccionCorrespondencia(PersonaDireccion adp_pd)
	{
		ipd_direccionCorrespondencia = adp_pd;
	}

	/**
	 * Retorna el valor de direccion correspondencia
	 *
	 * @return el valor de direccion correspondencia
	 */
	public PersonaDireccion getDireccionCorrespondencia()
	{
		if(ipd_direccionCorrespondencia == null)
		{
			ipd_direccionCorrespondencia = new PersonaDireccion();
			ipd_direccionCorrespondencia.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			ipd_direccionCorrespondencia.setIdTipoEjeSecundario(IdentificadoresCommon.NU);
		}

		ipd_direccionCorrespondencia.setTipoDireccion(EstadoCommon.C);

		{
			String ls_idPais;
			ls_idPais = ipd_direccionCorrespondencia.getIdPais();

			if(!StringUtils.isValidString(ls_idPais))
				ipd_direccionCorrespondencia.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ipd_direccionCorrespondencia;
	}

	/**
	 * Modifica el valor de dirección predio
	 *
	 * @param ad_d asigna el valor a la propiedad dirección predio
	 */
	public void setDireccionPredio(Direccion ad_d)
	{
		id_direccionPredio = ad_d;
	}

	/**
	 * Retorna el valor de dirección predio
	 *
	 * @return el valor de dirección predio
	 */
	public Direccion getDireccionPredio()
	{
		if(id_direccionPredio == null)
		{
			id_direccionPredio = new Direccion();
			id_direccionPredio.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			id_direccionPredio.setIdTipoEjeSecundario(IdentificadoresCommon.NU);
		}

		return id_direccionPredio;
	}

	/**
	 * Modifica el valor de direccion residencia
	 *
	 * @param adp_pd asigna el valor a la propiedad direccion residencia
	 */
	public void setDireccionResidencia(PersonaDireccion adp_pd)
	{
		ipd_direccionResidencia = adp_pd;
	}

	/**
	 * Retorna el valor de direccion residencia
	 *
	 * @return el valor de direccion residenciaa
	 */
	public PersonaDireccion getDireccionResidencia()
	{
		if(ipd_direccionResidencia == null)
		{
			ipd_direccionResidencia = new PersonaDireccion();
			ipd_direccionResidencia.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			ipd_direccionResidencia.setIdTipoEjeSecundario(IdentificadoresCommon.NU);
		}

		ipd_direccionResidencia.setTipoDireccion(EstadoCommon.R);

		{
			String ls_idPais;
			ls_idPais = ipd_direccionResidencia.getIdPais();

			if(!StringUtils.isValidString(ls_idPais))
				ipd_direccionResidencia.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ipd_direccionResidencia;
	}

	/**
	 * Modifica el valor de direcciones predio
	 *
	 * @param acd_cd asigna el valor a la propiedad direcciones predio
	 */
	public void setDireccionesPredio(Collection<Direccion> acd_cd)
	{
		icd_direccionesPredio = acd_cd;
	}

	/**
	 * Retorna el valor de direcciones predio
	 *
	 * @return el valor de direcciones predio
	 */
	public Collection<Direccion> getDireccionesPredio()
	{
		return icd_direccionesPredio;
	}

	/**
	 * @param acddp_cddp Modifica el valor de la propiedad direccionesPredio2 por direccionesPredio2
	 */
	public void setDireccionesPredio2(Collection<DireccionDelPredio> acddp_cddp)
	{
		icddp_direccionesPredio2 = acddp_cddp;
	}

	/**
	 * @return Retorna el valor de la propiedad direccionesPredio2
	 */
	public Collection<DireccionDelPredio> getDireccionesPredio2()
	{
		return icddp_direccionesPredio2;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param acddp_cddp correspondiente al valor de acddp cddp
	 */
	public void setDireccionesTemporales(Collection<DireccionDelPredio> acddp_cddp)
	{
		icddp_direccionesTemporales = acddp_cddp;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de direcciones temporales
	 */
	public Collection<DireccionDelPredio> getDireccionesTemporales()
	{
		if(!CollectionUtils.isValidCollection(icddp_direccionesTemporales))
			icddp_direccionesTemporales = new LinkedList<DireccionDelPredio>();

		return icddp_direccionesTemporales;
	}

	/**
	 * @param as_s String as_s asigna el valor a la propiedad form
	 */
	public void setForm(String as_s)
	{
		is_form = as_s;
	}

	/**
	 * @return el valor de form
	 */
	public String getForm()
	{
		return is_form;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad habilitadoPorConsulta
	 */
	public void setHabilitadoPorConsulta(boolean ab_b)
	{
		ib_habilitadoPorConsulta = ab_b;
	}

	/**
	 * @return el valor de habilitadoPorConsulta
	 */
	public boolean isHabilitadoPorConsulta()
	{
		return ib_habilitadoPorConsulta;
	}

	/**
	 * @param as_s asigna el valor a la propiedad mismaDireccionCorrespondencia
	 */
	public void setMismaDireccionCorrespondencia(String as_s)
	{
		is_mismaDireccionCorrespondencia = as_s;
	}

	/**
	 * @return el valor de mismaDireccionCorrespondencia
	 */
	public String getMismaDireccionCorrespondencia()
	{
		return is_mismaDireccionCorrespondencia;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad modificarDireccionPredio
	 */
	public void setModificarDireccionPredio(boolean ab_b)
	{
		ib_modificarDireccionPredio = ab_b;
	}

	/**
	 * @return el valor de modificarDireccionPredio
	 */
	public boolean isModificarDireccionPredio()
	{
		return ib_modificarDireccionPredio;
	}

	/**
	 * Modifica el valor de direcciones predio
	 *
	 * @param acte_cte asigna el valor a la propiedad direcciones predio
	 */
	public void setTipoEjeSecundarioCorrespondencia(Collection<TipoEje> acte_cte)
	{
		icte_tipoEjeSecundarioCorrespondencia = acte_cte;
	}

	/**
	 * Retorna el valor de ejes secundarios de direcciones de correspondencia
	 *
	 * @return el valor de ejes secundarios de direcciones de correspondencia
	 * @throws B2BException
	 */
	public Collection<TipoEje> getTipoEjeSecundarioCorrespondencia()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(icte_tipoEjeSecundarioCorrespondencia))
		{
			String ls_tipoPredio;

			ls_tipoPredio = null;

			{
				Direccion ld_direccion;

				ld_direccion = getDireccionPredio();

				if(ld_direccion != null)
					ls_tipoPredio = ld_direccion.getTipoPredio();
			}

			icte_tipoEjeSecundarioCorrespondencia = irr_referenceRemote.findTipoEjeByTipoPredio(ls_tipoPredio);
		}

		return icte_tipoEjeSecundarioCorrespondencia;
	}

	/**
	 * Modifica el valor de tipo eje secundario
	 *
	 * @param acte_cte asigna el valor a la propiedad tipo eje secundario
	 */
	public void setTipoEjeSecundarioPredio(Collection<TipoEje> acte_cte)
	{
		icte_tipoEjeSecundarioPredio = acte_cte;
	}

	/**
	 * Retorna el valor de tipo eje secundario
	 *
	 * @return el valor de tipo eje secundario
	 * @throws B2BException
	 */
	public Collection<TipoEje> getTipoEjeSecundarioPredio()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(icte_tipoEjeSecundarioPredio))
		{
			String ls_tipoPredio;

			ls_tipoPredio = null;

			{
				Direccion ld_direccion;

				ld_direccion = getDireccionPredio();

				if(ld_direccion != null)
					ls_tipoPredio = ld_direccion.getTipoPredio();
			}

			icte_tipoEjeSecundarioPredio = irr_referenceRemote.findTipoEjeByTipoPredio(ls_tipoPredio);
		}

		return icte_tipoEjeSecundarioPredio;
	}

	/**
	 * Modifica el valor de tipo ejes secundarios residencia
	 *
	 * @param acte_cte asigna el valor a la propiedad tipo ejes secundarios residencia
	 */
	public void setTipoEjeSecundarioResidencia(Collection<TipoEje> acte_cte)
	{
		icte_tipoEjeSecundarioResidencia = acte_cte;
	}

	/**
	 * Retorna el valor de direcciones predio
	 *
	 * @return el valor de direcciones predio
	 * @throws B2BException
	 */
	public Collection<TipoEje> getTipoEjeSecundarioResidencia()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(icte_tipoEjeSecundarioResidencia))
		{
			String ls_tipoPredio;

			ls_tipoPredio = null;

			{
				Direccion ld_direccion;

				ld_direccion = getDireccionPredio();

				if(ld_direccion != null)
					ls_tipoPredio = ld_direccion.getTipoPredio();
			}

			icte_tipoEjeSecundarioResidencia = irr_referenceRemote.findTipoEjeByTipoPredio(ls_tipoPredio);
		}

		return icte_tipoEjeSecundarioResidencia;
	}

	/**
	 * Método encargado de actualizar la dirección completa.
	 *
	 * @param ab_predio correspondiente al valor de ab predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarDireccion(boolean ab_predio)
	    throws B2BException
	{
		Direccion ld_direccion;

		ld_direccion = ab_predio ? getDireccionPredio() : getDireccionCertificado();

		if(ld_direccion != null)
		{
			Map<String, Map<String, String>> lmsmss_data;

			lmsmss_data = getDatosParametricosDireccion();

			if(CollectionUtils.isValidCollection(lmsmss_data))
			{
				boolean             lb_coordenada;
				boolean             lb_eje;
				boolean             lb_letra;
				Map<String, String> lmss_coordenada;
				Map<String, String> lmss_eje;
				Map<String, String> lmss_letra;

				lmss_coordenada     = lmsmss_data.get(IdentificadoresCommon.COORDENADA);
				lmss_eje            = lmsmss_data.get(IdentificadoresCommon.EJE);
				lmss_letra          = lmsmss_data.get(IdentificadoresCommon.LETRA);
				lb_coordenada       = CollectionUtils.isValidCollection(lmss_coordenada);
				lb_eje              = CollectionUtils.isValidCollection(lmss_eje);
				lb_letra            = CollectionUtils.isValidCollection(lmss_letra);

				if(lb_coordenada || lb_eje || lb_letra)
				{
					StringBuilder lsb_direccionCompleta;
					String        ls_espacio;

					lsb_direccionCompleta     = new StringBuilder();
					ls_espacio                = IdentificadoresCommon.ESPACIO_VACIO;

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdTipoEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getDatoEjePrincipal();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdLetraEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdComplementoEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdCoordenadaEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdTipoEjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getDatoEjeSecundario();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdLetra1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdComplemento1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdCoordenada1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getDato2EjeSecundario();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdLetra2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdComplemento2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdCoordenada2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getIdComplementoDireccion();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ld_direccion.getComplementoDireccion();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					ld_direccion.setDireccion(lsb_direccionCompleta.toString().toUpperCase());
				}
			}
		}
	}

	/**
	 * Método encargado de actualizar la dirección de certificado.
	 *
	 * @throws B2BException
	 */
	public void actualizarDireccionCertificadoCompleta()
	    throws B2BException
	{
		actualizarDireccion(false);
	}

	/**
	 * Método encargado de actualizar la dirección predio de la persona
	 *
	 * @throws B2BException
	 */
	public void actualizarDireccionCompleta(boolean ab_residencia)
	    throws B2BException
	{
		PersonaDireccion lpd_direccion;

		if(ab_residencia)
			lpd_direccion = getDireccionResidencia();
		else
			lpd_direccion = getDireccionCorrespondencia();

		if(lpd_direccion != null)
		{
			Map<String, Map<String, String>> lmsmss_data;

			lmsmss_data = getDatosParametricosDireccion();

			if(CollectionUtils.isValidCollection(lmsmss_data))
			{
				boolean             lb_coordenada;
				boolean             lb_eje;
				boolean             lb_letra;
				Map<String, String> lmss_coordenada;
				Map<String, String> lmss_eje;
				Map<String, String> lmss_letra;

				lmss_coordenada     = lmsmss_data.get(IdentificadoresCommon.COORDENADA);
				lmss_eje            = lmsmss_data.get(IdentificadoresCommon.EJE);
				lmss_letra          = lmsmss_data.get(IdentificadoresCommon.LETRA);
				lb_coordenada       = CollectionUtils.isValidCollection(lmss_coordenada);
				lb_eje              = CollectionUtils.isValidCollection(lmss_eje);
				lb_letra            = CollectionUtils.isValidCollection(lmss_letra);

				if(lb_coordenada || lb_eje || lb_letra)
				{
					StringBuilder lsb_direccionCompleta;
					String        ls_espacio;

					lsb_direccionCompleta     = new StringBuilder();
					ls_espacio                = IdentificadoresCommon.ESPACIO_VACIO;

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdTipoEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getDatoEjePrincipal();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdLetraEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdComplementoEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdCoordenadaEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdTipoEjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getDatoEjeSecundario();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdLetra1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdComplemento1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdCoordenada1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getDato2EjeSecundario();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdLetra2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdComplemento2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdCoordenada2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getIdComplementoDireccion();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = lpd_direccion.getComplementoDireccion();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					lpd_direccion.setDireccion(lsb_direccionCompleta.toString().toUpperCase());
				}
			}
		}
	}

	/**
	 * Método encargado de actualizar la dirección del predio.
	 *
	 * @throws B2BException
	 */
	public void actualizarDireccionPredioCompleta()
	    throws B2BException
	{
		actualizarDireccion(true);
	}

	/**
	 * Método encargado de actualizar el eje secundario con base a la elección del eje principal
	 *
	 * @param as_tipoEje Variable de tipo String que contiene el id tipo eje del eje principal
	 * @throws B2BException
	 */
	public void actualizarEjeSecundario(String as_tipoEje, boolean ab_residencia)
	{
		try
		{
			String ls_tipoPredio;

			ls_tipoPredio = null;

			{
				PersonaDireccion ld_direccion;

				ld_direccion = ab_residencia ? getDireccionResidencia() : getDireccionCorrespondencia();

				if(ld_direccion != null)
					ls_tipoPredio = ld_direccion.getIdTipoPredio();
			}

			if(ab_residencia)
				setTipoEjeSecundarioResidencia(obtenerEjeSecundario(as_tipoEje, ls_tipoPredio));
			else
				setTipoEjeSecundarioCorrespondencia(obtenerEjeSecundario(as_tipoEje, ls_tipoPredio));
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de actualizar el eje secundario con base a la elección del eje principal
	 *
	 * @throws B2BException
	 */
	public void actualizarEjeSecundario(boolean ab_residencia)
	    throws B2BException
	{
		String ls_tipoEje;

		ls_tipoEje = null;

		{
			PersonaDireccion lpd_direccion;

			lpd_direccion = ab_residencia ? getDireccionResidencia() : getDireccionCorrespondencia();

			if(lpd_direccion != null)
				ls_tipoEje = lpd_direccion.getIdTipoEjePrincipal();
		}

		actualizarEjeSecundario(ls_tipoEje, ab_residencia);
	}

	/**
	 * Método encargado de actualizar el eje secundario con base a la elección del eje principal
	 *
	 * @throws B2BException
	 */
	public void actualizarEjeSecundarioPredio()
	    throws B2BException
	{
		try
		{
			String ls_tipoEje;

			ls_tipoEje = null;

			{
				Direccion ld_direccion;

				ld_direccion = getDireccionPredio();

				if(ld_direccion != null)
					ls_tipoEje = ld_direccion.getIdTipoEjePrincipal();
			}

			actualizarEjeSecundarioPredio(ls_tipoEje);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de actualizar el eje secundario con base a la elección del eje principal
	 *
	 * @param as_tipoEje Variable de tipo String que contiene el id tipo eje del eje principal
	 * @throws B2BException
	 */
	public void actualizarEjeSecundarioPredio(String as_tipoEje)
	    throws B2BException
	{
		try
		{
			String ls_tipoPredio;

			ls_tipoPredio = null;

			{
				Direccion ld_direccion;

				ld_direccion = getDireccionPredio();

				if(ld_direccion != null)
					ls_tipoPredio = ld_direccion.getTipoPredio();
			}

			setTipoEjeSecundarioPredio(obtenerEjeSecundario(as_tipoEje, ls_tipoPredio));
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de agregar uan dirección a la colección de direcciones del predio.
	 */
	public void agregarDireccion()
	    throws B2BException
	{
		try
		{
			Direccion ld_direccion;

			ld_direccion = getDireccionPredio();

			if(ld_direccion != null)
			{
				Collection<Direccion> lcd_direcciones;

				lcd_direcciones = getDireccionesPredio();

				if(!CollectionUtils.isValidCollection(lcd_direcciones))
					lcd_direcciones = new ArrayList<Direccion>();

				validarCamposDireccionPredio(ld_direccion);

				lcd_direcciones.add(ld_direccion);

				setDireccionesPredio(lcd_direcciones);
				setDireccionPredio(new Direccion(ld_direccion));
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de agregar uan dirección a la colección de direcciones del predio.
	 */
	public void agregarDireccionApertura()
	    throws B2BException
	{
		try
		{
			Direccion ld_direccion;

			ld_direccion = getDireccionPredio();

			if(ld_direccion != null)
			{
				Collection<DireccionDelPredio> lcd_direcciones;
				DireccionDelPredio             lddp_direccion;
				DireccionPredioAcc             ldpa_direccion;

				lcd_direcciones     = getDireccionesPredio2();
				lddp_direccion      = new DireccionDelPredio();

				if(!CollectionUtils.isValidCollection(lcd_direcciones))
					lcd_direcciones = new ArrayList<DireccionDelPredio>();

				validarCamposDireccionPredio(ld_direccion);

				ldpa_direccion = new DireccionPredioAcc(ld_direccion);

				lddp_direccion.setDireccionPredio(ldpa_direccion);

				{
					DatosAntSistema ldas_data;

					ldas_data = new DatosAntSistema();

					ldas_data.setIdTipoPredio(ldpa_direccion.getIdTipoPredio());
					lddp_direccion.setDatosAntiguoSistema(ldas_data);
				}

				lcd_direcciones.add(lddp_direccion);

				setDireccionesPredio2(lcd_direcciones);
				setDireccionesTemporales(getDireccionesPredio2());
				setDireccionPredio(new Direccion(ld_direccion));
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de cambiar la dirección de correspondencia.
	 *
	 * @throws B2BException
	 */
	public void cambiarDireccionCorrespondencia()
	    throws B2BException
	{
		PersonaDireccion ld_direccion;
		PersonaDireccion ld_direccionCorr;

		ld_direccion         = getDireccionResidencia();
		ld_direccionCorr     = getDireccionCorrespondencia();

		if((ld_direccion != null) && (ld_direccionCorr != null))
		{
			String ls_mismaDir;

			ls_mismaDir = getMismaDireccionCorrespondencia();

			if(StringUtils.isValidString(ls_mismaDir) && ls_mismaDir.equalsIgnoreCase(EstadoCommon.S))
			{
				ld_direccionCorr.setIdTipoPredio(ld_direccion.getIdTipoPredio());
				ld_direccionCorr.setDireccion(ld_direccion.getDireccion());
				ld_direccionCorr.setIdPais(ld_direccion.getIdPais());
				ld_direccionCorr.setIdDepartamento(ld_direccion.getIdDepartamento());
				ld_direccionCorr.setIdMunicipio(ld_direccion.getIdMunicipio());
				ld_direccionCorr.setIdTipoEjePrincipal(ld_direccion.getIdTipoEjePrincipal());
				ld_direccionCorr.setDatoEjePrincipal(
				    StringUtils.isValidString(ld_direccion.getDatoEjePrincipal())
				    ? ld_direccion.getDatoEjePrincipal().toUpperCase() : null
				);
				ld_direccionCorr.setIdLetraEjePrincipal(ld_direccion.getIdLetraEjePrincipal());
				ld_direccionCorr.setIdComplementoEjePrincipal(ld_direccion.getIdComplementoEjePrincipal());
				ld_direccionCorr.setIdCoordenadaEjePrincipal(ld_direccion.getIdCoordenadaEjePrincipal());
				ld_direccionCorr.setIdTipoEjeSecundario(ld_direccion.getIdTipoEjeSecundario());
				ld_direccionCorr.setDatoEjeSecundario(
				    StringUtils.isValidString(ld_direccion.getDatoEjeSecundario())
				    ? ld_direccion.getDatoEjeSecundario().toUpperCase() : null
				);
				ld_direccionCorr.setIdLetra1EjeSecundario(ld_direccion.getIdLetra1EjeSecundario());
				ld_direccionCorr.setIdComplemento1EjeSecundario(ld_direccion.getIdComplemento1EjeSecundario());
				ld_direccionCorr.setIdCoordenada1EjeSecundario(ld_direccion.getIdCoordenada1EjeSecundario());
				ld_direccionCorr.setDato2EjeSecundario(
				    StringUtils.isValidString(ld_direccion.getDato2EjeSecundario())
				    ? ld_direccion.getDato2EjeSecundario().toUpperCase() : null
				);
				ld_direccionCorr.setIdLetra2EjeSecundario(ld_direccion.getIdLetra2EjeSecundario());
				ld_direccionCorr.setIdComplemento2EjeSecundario(ld_direccion.getIdComplemento2EjeSecundario());
				ld_direccionCorr.setIdCoordenada2EjeSecundario(ld_direccion.getIdCoordenada2EjeSecundario());
				ld_direccionCorr.setIdComplementoDireccion(ld_direccion.getIdComplementoDireccion());
				ld_direccionCorr.setComplementoDireccion(
				    StringUtils.isValidString(ld_direccion.getComplementoDireccion())
				    ? ld_direccion.getComplementoDireccion().toUpperCase() : null
				);
				ld_direccionCorr.setTipoDireccion(EstadoCommon.C);
				setDeshabilitarCorrespondencia(true);
			}
			else
			{
				setDeshabilitarCorrespondencia(false);
				setDireccionCorrespondencia(null);
			}
		}
	}

	/**
	 * Método encargado de cargar los datos de la dirección del predio a partir de datos de ant sistema.
	 *
	 * @param adas_datos Objeto que contiene la información para cargar los datos de la dirección.
	 */
	public void cargarDatosDireccionPredio(DatosAntSistema adas_datos)
	{
		setDireccionPredio(cargarDatosDireccionPredio(adas_datos, getDireccionPredio()));
	}

	/**
	 * Método encargado de cargar los datos de la dirección del predio a partir de datos de ant sistema.
	 *
	 * @param adas_datos Objeto que contiene la información para cargar los datos de la dirección.
	 * @param ad_direccion Objeto que contiene la información de la dirección.
	 * @return Objeto que contiene la información de la dirección.
	 */
	public Direccion cargarDatosDireccionPredio(DatosAntSistema adas_datos, Direccion ad_direccion)
	{
		if((adas_datos != null) && (ad_direccion != null))
		{
			ad_direccion.setIdTipoPredio(adas_datos.getIdTipoPredio());
			ad_direccion.setIdPais(adas_datos.getIdPais());
			ad_direccion.setIdDepartamento(adas_datos.getIdDepartamento());
			ad_direccion.setIdMunicipio(adas_datos.getIdMunicipio());
		}

		return ad_direccion;
	}

	/**
	 * Método encargado de cargar los datos de la dirección del predio.
	 *
	 * @param adb_datosBasicos Contiene la información de los datos básicos del predio.
	 * @param ad_direccion Contiene la información de la dirección del predio a cargar.
	 * @return Retorna el objeto dirección con la data cargada del predio.
	 */
	public Direccion cargarDatosDireccionPredio(DatosBasicos adb_datosBasicos, Direccion ad_direccion)
	{
		if((adb_datosBasicos != null) && (ad_direccion != null))
		{
			{
				AccPredioRegistro lapr_predio;

				lapr_predio = adb_datosBasicos.getAccPredioRegistro();

				if(lapr_predio != null)
					ad_direccion.setIdTipoPredio(lapr_predio.getIdTipoPredio());
			}

			{
				UbicacionZonaRegistral luzr_zonaRegistral;

				luzr_zonaRegistral = adb_datosBasicos.getUbicacion();

				if(luzr_zonaRegistral != null)
				{
					ad_direccion.setIdPais(luzr_zonaRegistral.getIdPais());

					{
						Departamento ld_departamento;

						ld_departamento = luzr_zonaRegistral.getDepartamento();

						if(ld_departamento != null)
							ad_direccion.setIdDepartamento(ld_departamento.getIdDepartamento());
					}

					{
						Municipio lm_municipio;

						lm_municipio = luzr_zonaRegistral.getMunicipio();

						if(lm_municipio != null)
							ad_direccion.setIdMunicipio(lm_municipio.getIdMunicipio());
					}
				}
			}
		}

		return ad_direccion;
	}

	/**
	 * Método encargado de cargar los datos de la dirección del predio a partir de datos básicos.
	 *
	 * @param adb_datosBasicos Objeto que contiene la información para cargar los datos de la dirección.
	 */
	public void cargarDatosDireccionPredio(DatosBasicos adb_datosBasicos)
	{
		setDireccionPredio(cargarDatosDireccionPredio(adb_datosBasicos, getDireccionPredio()));
	}

	/**
	 * Método encargado de eliminar una dirección de la colección de direcciones del predio.
	 *
	 * @param ad_direccion
	 */
	public void eliminarDireccion(Direccion ad_direccion)
	{
		Collection<Direccion> lcd_direcciones;

		lcd_direcciones = getDireccionesPredio();

		if(CollectionUtils.isValidCollection(lcd_direcciones))
			lcd_direcciones.remove(ad_direccion);

		setDireccionesPredio(lcd_direcciones);
	}

	/**
	 * Método encargado de limpiar los atributos del bean
	 * @throws B2BException
	 */
	public void limpiarBeanDireccion()
	    throws B2BException
	{
		setDireccionCorrespondencia(new PersonaDireccion());
		setDireccionResidencia(new PersonaDireccion());
		setDireccionPredio(new Direccion());
		setTipoEjeSecundarioCorrespondencia(null);
		setTipoEjeSecundarioResidencia(null);
		setTipoEjeSecundarioPredio(null);
		setDireccionesPredio(null);
		setAgregarDireccionPredio(false);
		setDeshabilitarCorrespondencia(false);
		setDeshabilitarResidencia(false);
		setDeshabilitarTipoPredio(false);
		setDeshabilitarDatosUbicacion(false);
		setHabilitadoPorConsulta(false);
		setModificarDireccionPredio(true);
		setForm(null);
		setMismaDireccionCorrespondencia(null);
		setBloquearCamposCertificados(false);
		setDatosParametricosDireccion(null);
		setAnotacion(false);

		cargarDatosParametricosDireccion();
	}

	/**
	 * Método encargado de limpiar los campos en rojo para las direcciones.
	 *
	 * @param ab_residencia Variable que indica si la dirección es de residencia o correspondencia.
	 */
	public void limpiarDirecciones(boolean ab_residencia)
	{
		limpiarDirecciones(ab_residencia, false, true);
	}

	/**
	 * Método encargado de limpiar los campos en rojo para las direcciones.
	 *
	 * @param ab_residencia Variable que indica si la dirección es de residencia o correspondencia.
	 * @param ab_mismaDireccion Variable de tipo boolean que indica si se debe limpiar el campo misma dirección.
	 */
	public void limpiarDirecciones(boolean ab_residencia, boolean ab_mismaDireccion)
	{
		limpiarDirecciones(ab_residencia, ab_residencia, true);
	}

	/**
	 * Método encargado de limpiar los campos en rojo para las direcciones.
	 *
	 * @param ab_residencia Variable de tipo boolean que indica si la dirección es de residencia o correspondencia.
	 * @param ab_mismaDireccion Variable de tipo boolean que indica si se debe limpiar el campo misma dirección.
	 * @param ab_interesado Variable de tipo boolean que valida si la dirección es de interesado o interviniente.
	 */
	public void limpiarDirecciones(boolean ab_residencia, boolean ab_mismaDireccion, boolean ab_interesado)
	{
		boolean      lb_focus;
		FacesContext lfc_context;
		String       ls_form;

		lfc_context            = FacesContext.getCurrentInstance();
		lb_focus               = true;
		is_datoParaValidar     = IdentificadoresCommon.DATO_VALIDO;
		ls_form                = getForm()
			+ (ab_residencia ? (ab_interesado ? is_formResidencia : is_formResidenciaInter)
			                 : (ab_interesado ? is_formCorrespondencia : is_formCorrespondenciaInter));

		if(!ab_residencia && ab_mismaDireccion)
			lb_focus = validateStyles(
				    ls_form + ":direccionCorrespondenciaIdMismaDir", lfc_context, is_datoParaValidar, lb_focus
				);

		lb_focus     = validateStyles(
			    ls_form
			    + (ab_residencia ? ":direccionResidenciaIdTipoPredio" : ":direccionCorrespondenciaIdTipoPredio"),
			    lfc_context, is_datoParaValidar, lb_focus
			);
		lb_focus     = validateStyles(
			    ls_form + (ab_residencia ? ":direccionResidenciaIdPais" : ":direccionCorrespondenciaIdPais"),
			    lfc_context, is_datoParaValidar, lb_focus
			);
		lb_focus     = validateStyles(
			    ls_form
			    + (ab_residencia ? ":direccionResidenciaIdDepartamento" : ":direccionCorrespondenciaIdDepartamento"),
			    lfc_context, is_datoParaValidar, lb_focus
			);
		lb_focus     = validateStyles(
			    ls_form + (ab_residencia ? ":direccionResidenciaIdMunicipio" : ":direccionCorrespondenciaIdMunicipio"),
			    lfc_context, is_datoParaValidar, lb_focus
			);
		lb_focus     = validateStyles(
			    ls_form
			    + (ab_residencia ? ":direccionResidenciaIdTipoEjePrincipal"
			                     : ":direccionCorrespondenciaIdTipoEjePrincipal"), lfc_context, is_datoParaValidar,
			    lb_focus
			);
		lb_focus     = validateStyles(
			    ls_form
			    + (ab_residencia ? ":direccionResidenciaIdDatoEjePrincipal"
			                     : ":direccionCorrespondenciaIdDatoEjePrincipal"), lfc_context, is_datoParaValidar,
			    lb_focus
			);
	}

	/**
	 * Método encargado de validar los datos para una dirección.
	 *
	 * @param lpd_direccion Objeto que contiene la información de la dirección.
	 * @param ab_residencia Variable de tipo boolean que valida si la dirección corresponde a residencia o correspondencia.
	 * @param ab_mismaDireccion Variable de tipo boolean que valida si incluye el campo misma dirección.
	 * @param ab_interesado Variable de tipo boolean que valida si es dirección para interesado o interviniente.
	 * @throws B2BException
	 */
	public void validarCamposDireccion(
	    PersonaDireccion lpd_direccion, boolean ab_residencia, boolean ab_mismaDireccion, boolean ab_interesado
	)
	    throws B2BException
	{
		if(lpd_direccion != null)
		{
			boolean          lb_focus;
			ExpresionRegular ler_expresionRegular;
			FacesContext     lfc_context;
			String           ls_form;

			lb_focus                 = true;
			ler_expresionRegular     = ExpresionRegular.getExpresionRegular();
			lfc_context              = FacesContext.getCurrentInstance();
			ls_form                  = getForm()
				+ (ab_residencia ? (ab_interesado ? is_formResidencia : is_formResidenciaInter)
				                 : (ab_interesado ? is_formCorrespondencia : is_formCorrespondenciaInter));

			if(!ab_residencia && ab_mismaDireccion)
			{
				is_datoParaValidar     = getMismaDireccionCorrespondencia();
				lb_focus               = validateStyles(
					    ls_form + ":direccionCorrespondenciaIdMismaDir", lfc_context, is_datoParaValidar, lb_focus
					);
			}

			is_datoParaValidar     = lpd_direccion.getIdTipoPredio();
			lb_focus               = validateStyles(
				    ls_form
				    + (ab_residencia ? ":direccionResidenciaIdTipoPredio" : ":direccionCorrespondenciaIdTipoPredio"),
				    lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(
				    ab_residencia ? ErrorKeys.ERROR_TIPO_PREDIO_DIR_RESIDENCIA
				                  : ErrorKeys.ERROR_TIPO_PREDIO_DIR_CORRESPONDENCIA
				);

			is_datoParaValidar     = lpd_direccion.getIdPais();
			lb_focus               = validateStyles(
				    ls_form + (ab_residencia ? ":direccionResidenciaIdPais" : ":direccionCorrespondenciaIdPais"),
				    lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(
				    ab_residencia ? ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA
				                  : ErrorKeys.DEBE_SELECCIONAR_PAIS_CORRESPONDENCIA
				);

			is_datoParaValidar     = lpd_direccion.getIdDepartamento();
			lb_focus               = validateStyles(
				    ls_form
				    + (ab_residencia ? ":direccionResidenciaIdDepartamento" : ":direccionCorrespondenciaIdDepartamento"),
				    lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(
				    ab_residencia ? ErrorKeys.DEBE_SELECCIONAR_DEP_RESIDENCIA
				                  : ErrorKeys.DEBE_SELECCIONAR_DEP_CORRESPONDENCIA
				);

			is_datoParaValidar     = lpd_direccion.getIdMunicipio();
			lb_focus               = validateStyles(
				    ls_form
				    + (ab_residencia ? ":direccionResidenciaIdMunicipio" : ":direccionCorrespondenciaIdMunicipio"),
				    lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(
				    ab_residencia ? ErrorKeys.DEBE_SELECCIONAR_MUN_RESIDENCIA
				                  : ErrorKeys.DEBE_SELECCIONAR_MUN_CORRESPONDENCIA
				);

			is_datoParaValidar     = lpd_direccion.getIdTipoEjePrincipal();
			lb_focus               = validateStyles(
				    ls_form
				    + (ab_residencia ? ":direccionResidenciaIdTipoEjePrincipal"
				                     : ":direccionCorrespondenciaIdTipoEjePrincipal"), lfc_context, is_datoParaValidar,
				    lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(
				    ab_residencia ? ErrorKeys.DEBE_SELECCIONAR_EJE_PRINCIPAL
				                  : ErrorKeys.DEBE_SELECCIONAR_EJE_PRINCIPAL_COR
				);

			is_datoParaValidar     = lpd_direccion.getDatoEjePrincipal();
			lb_focus               = validateStyles(
				    ls_form
				    + (ab_residencia ? ":direccionResidenciaIdDatoEjePrincipal"
				                     : ":direccionCorrespondenciaIdDatoEjePrincipal"), lfc_context, is_datoParaValidar,
				    lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_EJE_PRINCIPAL);
			else
			{
				if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
				{
					Object[] aoa_messageArgs = new String[1];

					aoa_messageArgs[0]     = StringUtils.getString(IdentificadoresCommon.NUMERO);
					lb_focus               = validateStyles(
						    ls_form
						    + (ab_residencia ? ":direccionResidenciaIdDatoEjePrincipal"
						                     : ":direccionCorrespondenciaIdDatoEjePrincipal"), lfc_context,
						    IdentificadoresCommon.DATO_INVALIDO, lb_focus
						);

					throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
				}
				else
					lb_focus = validateStyles(
						    ls_form
						    + (ab_residencia ? ":direccionResidenciaIdDatoEjePrincipal"
						                     : ":direccionCorrespondenciaIdDatoEjePrincipal"), lfc_context,
						    is_datoParaValidar, lb_focus
						);
			}

			{
				is_datoParaValidar = lpd_direccion.getDatoEjeSecundario();

				if(StringUtils.isValidString(is_datoParaValidar))
				{
					if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
					{
						Object[] aoa_messageArgs = new String[1];

						aoa_messageArgs[0]     = StringUtils.getString(IdentificadoresCommon.NUMERO_1);
						lb_focus               = validateStyles(
							    ls_form
							    + (ab_residencia ? ":direccionResidenciaIdDatoEjeSecundario"
							                     : ":direccionCorrespondenciaIdDatoEjeSecundario"), lfc_context,
							    IdentificadoresCommon.DATO_INVALIDO, lb_focus
							);

						throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
					}
					else
						lb_focus = validateStyles(
							    ls_form
							    + (ab_residencia ? ":direccionResidenciaIdDatoEjeSecundario"
							                     : ":direccionCorrespondenciaIdDatoEjeSecundario"), lfc_context,
							    is_datoParaValidar, lb_focus
							);
				}
			}

			{
				is_datoParaValidar = lpd_direccion.getDato2EjeSecundario();

				if(StringUtils.isValidString(is_datoParaValidar))
				{
					if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
					{
						Object[] aoa_messageArgs = new String[1];

						aoa_messageArgs[0]     = StringUtils.getString(IdentificadoresCommon.NUMERO_2);
						lb_focus               = validateStyles(
							    ls_form
							    + (ab_residencia ? ":direccionResidenciaIdNumero2EjeSecundario"
							                     : ":direccionCorrespondenciaIdNumero2EjeSecundario"), lfc_context,
							    IdentificadoresCommon.DATO_INVALIDO, lb_focus
							);

						throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
					}
					else
						lb_focus = validateStyles(
							    ls_form
							    + (ab_residencia ? ":direccionResidenciaIdNumero2EjeSecundario"
							                     : ":direccionCorrespondenciaIdNumero2EjeSecundario"), lfc_context,
							    is_datoParaValidar, lb_focus
							);
				}
			}

			{
				is_datoParaValidar = lpd_direccion.getComplementoDireccion();

				if(StringUtils.isValidString(is_datoParaValidar))
				{
					if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
					{
						Object[] aoa_messageArgs = new String[1];

						aoa_messageArgs[0]     = StringUtils.getString(IdentificadoresCommon.DESCRIPCION_COMPLEMENTO);
						lb_focus               = validateStyles(
							    ls_form
							    + (ab_residencia ? ":direccionResidenciaIdDescripcionDireccion"
							                     : ":direccionCorrespondenciaIdDescripcionDireccion"), lfc_context,
							    IdentificadoresCommon.DATO_INVALIDO, lb_focus
							);

						throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
					}
					else
						lb_focus = validateStyles(
							    ls_form
							    + (ab_residencia ? ":direccionResidenciaIdDescripcionDireccion"
							                     : ":direccionCorrespondenciaIdDescripcionDireccion"), lfc_context,
							    is_datoParaValidar, lb_focus
							);
				}
			}
		}
		else
			throw new B2BException(
			    ab_residencia ? ErrorKeys.DEBE_DIGITAR_EJE_PRINCIPAL : ErrorKeys.DEBE_DIGITAR_EJE_PRINCIPAL_COR
			);
	}

	/**
	 * Método encargado de validar los campos de la dirección para certificados.
	 *
	 * @return Retorna el objeto que contiene la información de la dirección que se valido.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public SolicitudDireccionCertificado validarCamposDireccionCertificado()
	    throws B2BException
	{
		SolicitudDireccionCertificado lsdc_return;
		SolicitudDireccionCertificado lsdc_direccion;

		lsdc_return        = null;
		lsdc_direccion     = getDireccionCertificado();

		if(lsdc_direccion != null)
		{
			String  ls_idTipoPredio;
			String  ls_tipoEje;
			String  ls_ejePrincipal;
			String  ls_idDepartamento;
			String  ls_idMunicipio;
			boolean lb_tipoPredioValido;
			boolean lb_tipoEjeValido;
			boolean lb_ejePrincipalValido;
			boolean lb_departamento;
			boolean lb_municipio;

			ls_idTipoPredio           = lsdc_direccion.getIdTipoPredio();
			ls_tipoEje                = lsdc_direccion.getIdTipoEjePrincipal();
			ls_ejePrincipal           = lsdc_direccion.getDatoEjePrincipal();
			ls_idDepartamento         = lsdc_direccion.getIdDepartamento();
			ls_idMunicipio            = lsdc_direccion.getIdMunicipio();
			lb_tipoPredioValido       = StringUtils.isValidString(ls_idTipoPredio);
			lb_tipoEjeValido          = StringUtils.isValidString(ls_tipoEje);
			lb_ejePrincipalValido     = StringUtils.isValidString(ls_ejePrincipal);
			lb_departamento           = StringUtils.isValidString(ls_idDepartamento);
			lb_municipio              = StringUtils.isValidString(ls_idMunicipio);

			if(lb_tipoPredioValido || lb_tipoEjeValido || lb_ejePrincipalValido || lb_departamento || lb_municipio)
			{
				boolean          lb_focus;
				ExpresionRegular ler_expresionRegular;
				FacesContext     lfc_context;
				String           ls_form;

				lb_focus                 = true;
				ler_expresionRegular     = ExpresionRegular.getExpresionRegular();
				lfc_context              = FacesContext.getCurrentInstance();
				ls_form                  = getForm() + is_formCertificado;

				validateStyles(
				    ls_form + ":direccionCertificadoIdDepartamento", lfc_context, ls_idDepartamento, lb_focus
				);

				if(!lb_departamento)
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);

				validateStyles(ls_form + ":direccionCertificadoIdMunicipio", lfc_context, ls_idMunicipio, lb_focus);

				if(!lb_municipio)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

				validateStyles(ls_form + ":direccionCertificadoIdTipoPredio", lfc_context, ls_idTipoPredio, lb_focus);

				if(!lb_tipoPredioValido)
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);

				validateStyles(ls_form + ":direccionCertificadoIdTipoEjePrincipal", lfc_context, ls_tipoEje, lb_focus);

				if(!lb_tipoEjeValido)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE);

				validateStyles(
				    ls_form + ":direccionCertificadoIdDatoEjePrincipal", lfc_context, ls_ejePrincipal, lb_focus
				);

				if(!lb_ejePrincipalValido)
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_EJE_PRINCIPAL);
				else
				{
					is_datoParaValidar = ls_ejePrincipal;

					if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
					{
						Object[] aoa_messageArgs = new String[1];

						aoa_messageArgs[0] = StringUtils.getString(IdentificadoresCommon.NUMERO);

						validateStyles(
						    ls_form + ":direccionCertificadoIdDatoEjePrincipal", lfc_context,
						    IdentificadoresCommon.DATO_INVALIDO, lb_focus
						);

						throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
					}
					else
						validateStyles(
						    ls_form + ":direccionCertificadoIdDatoEjePrincipal", lfc_context, is_datoParaValidar,
						    lb_focus
						);
				}

				{
					is_datoParaValidar = lsdc_direccion.getDatoEjeSecundario();

					if(StringUtils.isValidString(is_datoParaValidar))
					{
						if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
						{
							Object[] aoa_messageArgs = new String[1];

							aoa_messageArgs[0] = StringUtils.getString(IdentificadoresCommon.NUMERO_1);

							validateStyles(
							    ls_form + ":direccionCertificadoIdDatoEjeSecundario", lfc_context,
							    IdentificadoresCommon.DATO_INVALIDO, lb_focus
							);

							throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
						}
						else
							validateStyles(
							    ls_form + ":direccionCertificadoIdDatoEjeSecundario", lfc_context, is_datoParaValidar,
							    lb_focus
							);
					}
				}

				{
					is_datoParaValidar = lsdc_direccion.getDato2EjeSecundario();

					if(StringUtils.isValidString(is_datoParaValidar))
					{
						if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
						{
							Object[] aoa_messageArgs = new String[1];

							aoa_messageArgs[0] = StringUtils.getString(IdentificadoresCommon.NUMERO_2);

							validateStyles(
							    ls_form + ":direccionCertificadoIdNumero2EjeSecundario", lfc_context,
							    IdentificadoresCommon.DATO_INVALIDO, lb_focus
							);

							throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
						}
						else
							validateStyles(
							    ls_form + ":direccionCertificadoIdNumero2EjeSecundario", lfc_context, is_datoParaValidar,
							    lb_focus
							);
					}
				}

				{
					is_datoParaValidar = lsdc_direccion.getComplementoDireccion();

					if(StringUtils.isValidString(is_datoParaValidar))
					{
						if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
						{
							Object[] aoa_messageArgs = new String[1];

							aoa_messageArgs[0] = StringUtils.getString(IdentificadoresCommon.DESCRIPCION_COMPLEMENTO);

							validateStyles(
							    ls_form + ":direccionCertificadoIdDescripcionDireccion", lfc_context,
							    IdentificadoresCommon.DATO_INVALIDO, lb_focus
							);

							throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
						}
						else
							validateStyles(
							    ls_form + ":direccionCertificadoIdDescripcionDireccion", lfc_context, is_datoParaValidar,
							    lb_focus
							);
					}
				}

				lsdc_return = lsdc_direccion;
			}
		}
		else
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		return lsdc_return;
	}

	/**
	 * Método encargado de validar los datos para la dirección de correspondencia para un interesado.
	 *
	 * @param ab_mismaDireccion correspondiente al valor de ab misma direccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarCamposDireccionCorrespondencia(boolean ab_mismaDireccion)
	    throws B2BException
	{
		validarCamposDireccion(getDireccionCorrespondencia(), false, ab_mismaDireccion, true);
	}

	/**
	 * Método encargado de validar los datos para la dirección de correspondencia para un interviniente.
	 *
	 * @param ab_mismaDireccion correspondiente al valor de ab misma direccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarCamposDireccionCorrespondenciaInter(boolean ab_mismaDireccion)
	    throws B2BException
	{
		validarCamposDireccion(getDireccionCorrespondencia(), false, ab_mismaDireccion, false);
	}

	/**
	 * Método encargado de validar los datos una dirección del predio.
	 *
	 * @param ab_agregar Variable de tipo boolean que identifica si la pantalla agrega direcciones.
	 * @throws B2BException
	 */
	public void validarCamposDireccionPredio(boolean ab_agregar)
	    throws B2BException
	{
		validarCamposDireccionPredio(getDireccionPredio(), ab_agregar);
	}

	/**
	 * Método encargado de validar los datos una dirección del predio.
	 *
	 * @throws B2BException
	 */
	public void validarCamposDireccionPredio()
	    throws B2BException
	{
		validarCamposDireccionPredio(getDireccionPredio(), true);
	}

	/**
	 * Método encargado de validar los datos una dirección del predio.
	 *
	 * @param ld_direccion Objeto que contiene la información de la dirección.
	 * @throws B2BException
	 */
	public void validarCamposDireccionPredio(Direccion ld_direccion)
	    throws B2BException
	{
		validarCamposDireccionPredio(ld_direccion, true);
	}

	/**
	 * Método encargado de validar los datos una dirección del predio.
	 *
	 * @param ld_direccion Objeto que contiene la información de la dirección.
	 * @param ab_agregar Variable de tipo boolean que identifica si la pantalla agrega direcciones.
	 * @throws B2BException
	 */
	public void validarCamposDireccionPredio(Direccion ld_direccion, boolean ab_agregar)
	    throws B2BException
	{
		if(ld_direccion != null)
		{
			boolean          lb_focus;
			ExpresionRegular ler_expresionRegular;
			FacesContext     lfc_context;
			String           ls_form;

			lfc_context              = FacesContext.getCurrentInstance();
			ler_expresionRegular     = ExpresionRegular.getExpresionRegular();
			lb_focus                 = true;
			ls_form                  = getForm()
				+ (ab_agregar ? (isAnotacion() ? is_formPredioAgregarAnotacion : is_formPredioAgregar) : is_formPredio);

			is_datoParaValidar     = ld_direccion.getIdTipoPredio();
			lb_focus               = validateStyles(
				    ls_form + ":direccionPredioIdTipoPredio", lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(ErrorKeys.ERROR_TIPO_PREDIO);

			is_datoParaValidar     = ld_direccion.getIdPais();
			lb_focus               = validateStyles(
				    ls_form + ":direccionPredioIdPais", lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

			is_datoParaValidar     = ld_direccion.getIdDepartamento();
			lb_focus               = validateStyles(
				    ls_form + ":direccionPredioIdDepartamento", lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);

			is_datoParaValidar     = ld_direccion.getIdMunicipio();
			lb_focus               = validateStyles(
				    ls_form + ":direccionPredioIdMunicipio", lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

			is_datoParaValidar     = ld_direccion.getIdTipoEjePrincipal();
			lb_focus               = validateStyles(
				    ls_form + ":direccionPredioIdTipoEjePrincipal", lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(ErrorKeys.ERROR_SIN_EJE_PRINCIPAL);

			is_datoParaValidar     = ld_direccion.getDatoEjePrincipal();
			lb_focus               = validateStyles(
				    ls_form + ":direccionPredioIdDatoEjePrincipal", lfc_context, is_datoParaValidar, lb_focus
				);

			if(!StringUtils.isValidString(is_datoParaValidar))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_EJE_PRINCIPAL);
			else
			{
				if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
				{
					Object[] aoa_messageArgs = new String[1];

					aoa_messageArgs[0]     = StringUtils.getString(IdentificadoresCommon.NUMERO);
					lb_focus               = validateStyles(
						    ls_form + ":direccionPredioIdDatoEjePrincipal", lfc_context,
						    IdentificadoresCommon.DATO_INVALIDO, lb_focus
						);

					throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
				}
			}

			{
				is_datoParaValidar = ld_direccion.getDatoEjeSecundario();

				if(StringUtils.isValidString(is_datoParaValidar))
				{
					if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
					{
						Object[] aoa_messageArgs = new String[1];

						aoa_messageArgs[0] = StringUtils.getString(IdentificadoresCommon.NUMERO_1);

						validateStyles(
						    ls_form + ":direccionPredioIdDatoEjeSecundario", lfc_context,
						    IdentificadoresCommon.DATO_INVALIDO, lb_focus
						);

						throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
					}
					else
						validateStyles(
						    ls_form + ":direccionPredioIdDatoEjeSecundario", lfc_context, is_datoParaValidar, lb_focus
						);
				}
			}

			{
				is_datoParaValidar = ld_direccion.getDato2EjeSecundario();

				if(StringUtils.isValidString(is_datoParaValidar))
				{
					if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
					{
						Object[] aoa_messageArgs = new String[1];

						aoa_messageArgs[0] = StringUtils.getString(IdentificadoresCommon.NUMERO_2);

						validateStyles(
						    ls_form + ":direccionPredioIdNumero2EjeSecundario", lfc_context,
						    IdentificadoresCommon.DATO_INVALIDO, lb_focus
						);

						throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
					}
					else
						validateStyles(
						    ls_form + ":direccionPredioIdNumero2EjeSecundario", lfc_context, is_datoParaValidar,
						    lb_focus
						);
				}
			}

			{
				is_datoParaValidar = ld_direccion.getComplementoDireccion();

				if(StringUtils.isValidString(is_datoParaValidar))
				{
					if(ler_expresionRegular.contieneCaracteresEspeciales(is_datoParaValidar))
					{
						Object[] aoa_messageArgs = new String[1];

						aoa_messageArgs[0] = StringUtils.getString(IdentificadoresCommon.DESCRIPCION_COMPLEMENTO);

						validateStyles(
						    ls_form + ":direccionPredioIdDescripcionDireccion", lfc_context,
						    IdentificadoresCommon.DATO_INVALIDO, lb_focus
						);

						throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
					}
					else
						validateStyles(
						    ls_form + ":direccionPredioIdDescripcionDireccion", lfc_context, is_datoParaValidar,
						    lb_focus
						);
				}
			}
		}
		else
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
	}

	/**
	 * Método encargado de validar los datos para la dirección de residencia para un interesado.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarCamposDireccionResidencia()
	    throws B2BException
	{
		validarCamposDireccion(getDireccionResidencia(), true, false, true);
	}

	/**
	 * Método encargado de validar los datos para la dirección de residencia para un interviniente.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarCamposDireccionResidenciaInter()
	    throws B2BException
	{
		validarCamposDireccion(getDireccionResidencia(), true, false, false);
	}

	/**
	 * Método encargado de obtener los ejes secundarios.
	 *
	 * @param as_tipoEje Variable de tipo String que contiene el tipo de eje.
	 * @param as_tipoPredio Variable de tipo String que contiene el tipo de predio.
	 * @return Colección que contiene los tipo eje consultados.
	 * @throws B2BException
	 */
	protected Collection<TipoEje> obtenerEjeSecundario(String as_tipoEje, String as_tipoPredio)
	    throws B2BException
	{
		Collection<TipoEje> lcte_datos;

		lcte_datos = irr_referenceRemote.findTipoEjeByTipoPredio(as_tipoPredio);

		if(StringUtils.isValidString(as_tipoEje) && CollectionUtils.isValidCollection(lcte_datos))
		{
			boolean           lb_borrado;
			Iterator<TipoEje> lite_iterator;

			lb_borrado        = false;
			lite_iterator     = lcte_datos.iterator();

			while(lite_iterator.hasNext() && !lb_borrado)
			{
				TipoEje lte_tipoEje;

				lte_tipoEje = lite_iterator.next();

				if(lte_tipoEje != null)
				{
					String ls_idTipoEje;

					ls_idTipoEje = lte_tipoEje.getIdTipoEje();

					if(StringUtils.isValidString(ls_idTipoEje) && ls_idTipoEje.equalsIgnoreCase(as_tipoEje))
					{
						lcte_datos.remove(lte_tipoEje);
						lb_borrado = true;
					}
				}
			}
		}

		return lcte_datos;
	}
}
