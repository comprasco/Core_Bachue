package com.bachue.snr.prosnr21.web.bean;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr21.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanAfectacionPrestacionServicio;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanArchivoDRXC;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanArchivoIngresosING;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanAvisosProcesoConfrontacionIngresos;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanConfrontacionManual;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanExtractoBancoMensual;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanExtractoDiario;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanExtractoMensual;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanGenerarReportesConciliaciones;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanNuevaSolicitudConfrontacion;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanPartidasTipoA;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanProcedimientosReportes;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanProcesosConciliaciones;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanAlertasProcesoConfrontacionIngresos;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanArchivo;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanCuentaAnalista;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanEntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanPeriodoCorte;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanProcesoConciliacion;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanReportesConciliacion;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanRubro;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanRubroHomologacion;
import com.bachue.snr.prosnr21.web.bean.parameter.BeanSIIFCatalogo;
import com.bachue.snr.prosnr21.web.util.FacesUtils;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

import java.io.IOException;
import java.io.Serializable;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase para el manejo del menu en la aplicacion.
 *
 * @author Gabriel Arias
 */
@ManagedBean(name = "beanMenu")
@SessionScoped
public class BeanMenu extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4074563807253988742L;

	/** Propiedad icdmi items del menu. */
	private Collection<DefaultMenuItem> icdmi_itemsDelMenu;

	/** Propiedad imm menu. */
	private MenuModel imm_menu;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Método para asignar un valor a la propiedad.
	 *
	 * @param acdmi_cdmi valor a asignar a la propiedad.
	 */
	public void setItemsDelMenu(Collection<DefaultMenuItem> acdmi_cdmi)
	{
		icdmi_itemsDelMenu = acdmi_cdmi;
	}

	/**
	 * Método para obtener el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public Collection<DefaultMenuItem> getItemsDelMenu()
	{
		return icdmi_itemsDelMenu;
	}

	/**
	 * Método para asignar un valor a la propiedad.
	 *
	 * @param amm_mm valor a asignar a la propiedad.
	 */
	public void setMenu(MenuModel amm_mm)
	{
		imm_menu = amm_mm;
	}

	/**
	 * Método para obtener el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public MenuModel getMenu()
	{
		return imm_menu;
	}

	/**
	 * Cambia el estilo de una opción seleccionada para identificarla como visitada.
	 */
	public void marcarOpcionVisitada()
	{
		String    ls_opcion;
		MenuModel lmm_menu;

		ls_opcion     = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getParameter("opcionSeleccionada");
		lmm_menu      = getMenu();

		if(StringUtils.isValidString(ls_opcion) && (lmm_menu != null))
		{
			Collection<DefaultMenuItem> lcdmi_items;

			lcdmi_items = getItemsDelMenu();

			if(!CollectionUtils.isValidCollection(lcdmi_items))
			{
				lcdmi_items = obtenerItemsMenu(lmm_menu.getElements());

				if(CollectionUtils.isValidCollection(lcdmi_items))
					setItemsDelMenu(lcdmi_items);
			}

			if(CollectionUtils.isValidCollection(lcdmi_items))
			{
				//FORMATO Comentar lambda antes de formatear
				lcdmi_items.stream()
					.filter(ldmi_item -> ldmi_item.getValue() != null && ldmi_item.getValue().toString().equals(ls_opcion))
					.forEach(ldmi_item -> ldmi_item.setStyleClass("estiloVisitado"));
			}
		}
	}

	/**
	 * Método que indica que accion se debe ejecutarr en cada una de las paginas del menú.
	 *
	 * @param as_pagina pagina que el usuario en sesion accede
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public String pages(String as_pagina)
	    throws B2BException, IOException
	{
		FacesContext lfc_context;

		lfc_context = FacesUtils.getFacesContext();

		marcarOpcionVisitada();

		switch(as_pagina)
		{
			case NavegacionCommon.RUBRO:
			{
				BeanRubro lb_beanRubro;

				lb_beanRubro = (BeanRubro)lfc_context.getApplication()
						                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_RUBRO, BeanRubro.class
						);

				lb_beanRubro.findAllRubro();

				break;
			}

			case NavegacionCommon.RUBRO_HOMOLOGACION:
			{
				BeanRubroHomologacion lb_beanRubroHomologacion;

				lb_beanRubroHomologacion = (BeanRubroHomologacion)lfc_context.getApplication()
						                                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_RUBRO_HOMOLOGACION, BeanRubroHomologacion.class
						);

				lb_beanRubroHomologacion.findAllRubroHomologacion();

				break;
			}
			
			case NavegacionCommon.REPORTES_CONCILIACION:
			{
				BeanReportesConciliacion lb_beanReportesConciliacion;

				lb_beanReportesConciliacion = (BeanReportesConciliacion)lfc_context.getApplication()
						                                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REPORTES_CONCILIACION, BeanReportesConciliacion.class
						);

				lb_beanReportesConciliacion.clear();

				as_pagina = NavegacionCommon.REPORTES_CONCILIACION;

				break;
			}

			case NavegacionCommon.CUENTA_ANALISTA:
			{
				BeanCuentaAnalista lb_beanCuentaAnalista;

				lb_beanCuentaAnalista = (BeanCuentaAnalista)lfc_context.getApplication()
						                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CUENTA_ANALISTA, BeanCuentaAnalista.class
						);

				lb_beanCuentaAnalista.limpiar();
				lb_beanCuentaAnalista.findAllCuentaAnalista();

				break;
			}

			case NavegacionCommon.ENTIDAD_RECAUDADORA_CONCILIACION:
			{
				BeanEntidadRecaudadoraConciliacion lb_beanEntidadRecaudadoraConciliacion;

				lb_beanEntidadRecaudadoraConciliacion = (BeanEntidadRecaudadoraConciliacion)lfc_context.getApplication()
						                                                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ENTIDAD_RECAUDADORA_CONCILIACION,
						    BeanEntidadRecaudadoraConciliacion.class
						);

				lb_beanEntidadRecaudadoraConciliacion.limpiar();
				lb_beanEntidadRecaudadoraConciliacion.findAllEntidadRecaudadoraConciliacion();

				break;
			}

			case NavegacionCommon.ALERTAS_PROCESO_CONFRONTACION_INGRESOS:
			{
				BeanAlertasProcesoConfrontacionIngresos lb_beanAlertasProcesoConfrontacionIngresos;

				lb_beanAlertasProcesoConfrontacionIngresos = (BeanAlertasProcesoConfrontacionIngresos)lfc_context.getApplication()
						                                                                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ALERTAS_PROCESO_CONFRONTACION_INGRESOS,
						    BeanAlertasProcesoConfrontacionIngresos.class
						);

				lb_beanAlertasProcesoConfrontacionIngresos.limpiar();

				break;
			}

			case NavegacionCommon.AVISOS_PROCESO_CONFRONTACION_INGRESOS:
			{
				BeanAvisosProcesoConfrontacionIngresos lb_beanAvisosProcesoConfrontacionIngresos;

				lb_beanAvisosProcesoConfrontacionIngresos = (BeanAvisosProcesoConfrontacionIngresos)lfc_context.getApplication()
						                                                                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_AVISOS_PROCESO_CONFRONTACION_INGRESOS,
						    BeanAvisosProcesoConfrontacionIngresos.class
						);

				lb_beanAvisosProcesoConfrontacionIngresos.limpiar();

				break;
			}

			case NavegacionCommon.CLASIFICACION_PARTIDAS_TIPO_A:
			{
				BeanPartidasTipoA lb_beanPartidasTipoA;

				lb_beanPartidasTipoA = (BeanPartidasTipoA)lfc_context.getApplication()
						                                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CLASIFICACION_PARTIDAS_TIPO_A, BeanPartidasTipoA.class
						);

				lb_beanPartidasTipoA.limpiar();

				break;
			}

			case NavegacionCommon.ENTIDAD_RECAUDADORA_CUENTA:
			{
				BeanRubro lb_beanRubro;

				lb_beanRubro = (BeanRubro)lfc_context.getApplication()
						                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_RUBRO, BeanRubro.class
						);

				lb_beanRubro.findAllRubro();

				break;
			}

			case NavegacionCommon.PERIODO_CORTE:
			{
				BeanPeriodoCorte lb_beanPeriodoCorte;

				lb_beanPeriodoCorte = (BeanPeriodoCorte)lfc_context.getApplication()
						                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PERIODO_CORTE, BeanPeriodoCorte.class
						);

				lb_beanPeriodoCorte.findAllPeriodoCorte();

				break;
			}

			case NavegacionCommon.PROCESO_CONCILIACION:
			{
				BeanProcesoConciliacion lbpc_beanProcesoConciliacion;

				lbpc_beanProcesoConciliacion = (BeanProcesoConciliacion)obtenerInstanciaBean(
					    BeanProcesoConciliacion.class, BeanNameCommon.BEAN_PROCESO_CONCILIACION
					);

				lbpc_beanProcesoConciliacion.clear();

				as_pagina = NavegacionCommon.PROCESO_CONCILIACION;

				break;
			}

			case NavegacionCommon.CONFRONTACION_MANUAL:
			{
				BeanConfrontacionManual lbpc_beanConfrontacionManual;

				lbpc_beanConfrontacionManual = (BeanConfrontacionManual)obtenerInstanciaBean(
					    BeanConfrontacionManual.class, BeanNameCommon.BEAN_CONFRONTACION_MANUAL
					);

				lbpc_beanConfrontacionManual.limpiar();

				as_pagina = NavegacionCommon.CONFRONTACION_MANUAL;

				break;
			}

			case NavegacionCommon.AFECTACION_PRESTACION_SERVICIOS:
			{
				BeanAfectacionPrestacionServicio lbpc_beanConfrontacionManual;

				lbpc_beanConfrontacionManual = (BeanAfectacionPrestacionServicio)obtenerInstanciaBean(
					    BeanAfectacionPrestacionServicio.class, BeanNameCommon.BEAN_AFECTACION_PRESTACION_SERVICIO
					);

				lbpc_beanConfrontacionManual.limpiar();

				as_pagina = NavegacionCommon.AFECTACION_PRESTACION_SERVICIOS;

				break;
			}

			case NavegacionCommon.ARCHIVO:
			{
				BeanArchivo lbpc_beanConfrontacionManual;

				lbpc_beanConfrontacionManual = (BeanArchivo)obtenerInstanciaBean(
					    BeanArchivo.class, BeanNameCommon.BEAN_ARCHIVO
					);

				lbpc_beanConfrontacionManual.limpiar();
				as_pagina = NavegacionCommon.ARCHIVO;

				break;
			}

			case NavegacionCommon.ARCHIVO_DRXC:
			{
				BeanArchivoDRXC lbadrxc_beanArchivoDRXC;

				lbadrxc_beanArchivoDRXC = (BeanArchivoDRXC)obtenerInstanciaBean(
					    BeanArchivoDRXC.class, BeanNameCommon.BEAN_ARCHIVO_DRXC
					);

				lbadrxc_beanArchivoDRXC.limpiar();
				as_pagina = NavegacionCommon.ARCHIVO_DRXC;

				break;
			}
			
			case NavegacionCommon.ARCHIVO_INGRESOS_ING:
			{
				BeanArchivoIngresosING lbadrxc_beanArchivoIngresosING;

				lbadrxc_beanArchivoIngresosING = (BeanArchivoIngresosING)obtenerInstanciaBean(
						BeanArchivoIngresosING.class, BeanNameCommon.BEAN_ARCHIVO_INGRESOS_ING
					);

				lbadrxc_beanArchivoIngresosING.limpiar();
				as_pagina = NavegacionCommon.ARCHIVO_INGRESOS_ING;

				break;
			}

			case NavegacionCommon.EXTRACTO_DIARIO:
			{
				BeanExtractoDiario lbed_beanExtractoDiario;

				lbed_beanExtractoDiario = (BeanExtractoDiario)obtenerInstanciaBean(
					    BeanExtractoDiario.class, BeanNameCommon.BEAN_EXTRACTO_DIARIO
					);

				lbed_beanExtractoDiario.limpiar();
				as_pagina = NavegacionCommon.EXTRACTO_DIARIO;

				break;
			}

			case NavegacionCommon.EXTRACTO_MENSUAL:
			{
				BeanExtractoMensual lbem_beanExtractoMensual;

				lbem_beanExtractoMensual = (BeanExtractoMensual)obtenerInstanciaBean(
					    BeanExtractoMensual.class, BeanNameCommon.BEAN_EXTRACTO_MENSUAL
					);

				lbem_beanExtractoMensual.limpiar();
				as_pagina = NavegacionCommon.EXTRACTO_MENSUAL;

				break;
			}

			case NavegacionCommon.EXTRACTO_BANCO_MENSUAL:
			{
				BeanExtractoBancoMensual lbebm_beanExtractoBancoMensual;

				lbebm_beanExtractoBancoMensual = (BeanExtractoBancoMensual)obtenerInstanciaBean(
					    BeanExtractoBancoMensual.class, BeanNameCommon.BEAN_EXTRACTO_BANCO_MENSUAL
					);

				lbebm_beanExtractoBancoMensual.limpiar();
				as_pagina = NavegacionCommon.EXTRACTO_BANCO_MENSUAL;

				break;
			}

			case NavegacionCommon.SIIF_CATALOGO:
			{
				BeanSIIFCatalogo lbpc_beanSiifCatalogo;

				lbpc_beanSiifCatalogo = (BeanSIIFCatalogo)obtenerInstanciaBean(
					    BeanSIIFCatalogo.class, BeanNameCommon.BEAN_SIIF_CATALOGO
					);

				lbpc_beanSiifCatalogo.limpiar();
				as_pagina = NavegacionCommon.SIIF_CATALOGO;

				break;
			}

			case NavegacionCommon.NUEVA_SOLICITUD_CONFRONTACION:
			{
				BeanNuevaSolicitudConfrontacion lbnsc_bean;

				lbnsc_bean = (BeanNuevaSolicitudConfrontacion)obtenerInstanciaBean(
					    BeanNuevaSolicitudConfrontacion.class, BeanNameCommon.BEAN_NUEVA_SOLICITUD_CONFRONTACION
					);

				lbnsc_bean.limpiar();
				as_pagina = NavegacionCommon.NUEVA_SOLICITUD_CONFRONTACION;

				break;
			}
			
			case NavegacionCommon.PROCESOS_CONCILIACIONES:
			{
				BeanProcesosConciliaciones lbpc_bean;
				
				lbpc_bean = (BeanProcesosConciliaciones)obtenerInstanciaBean(
						BeanProcesosConciliaciones.class, BeanNameCommon.BEAN_PROCESOS_CONCILIACIONES
						);
				
				lbpc_bean.limpiar();
				as_pagina = NavegacionCommon.PROCESOS_CONCILIACIONES;
				
				break;
			}
			
			case NavegacionCommon.GENERAR_REPORTES_CONCILIACIONES:
			{
				BeanGenerarReportesConciliaciones lbpc_bean;
				
				lbpc_bean = (BeanGenerarReportesConciliaciones)obtenerInstanciaBean(
						BeanGenerarReportesConciliaciones.class, BeanNameCommon.BEAN_GENERAR_REPORTES_CONCILIACIONES
						);
				
				lbpc_bean.limpiar();
				
				break;
			}
			
			case NavegacionCommon.PROCEDIMIENTOS_REPORTES:
			{
				BeanProcedimientosReportes lbpReportes_bean;
				
				lbpReportes_bean = (BeanProcedimientosReportes)obtenerInstanciaBean(
						BeanProcedimientosReportes.class, BeanNameCommon.BEAN_PROCEDIMIENTOS_REPORTES
						);
				
				lbpReportes_bean.limpiar();
				
				break;
			}

			default:
				break;
		}

		return as_pagina;
	}

	/**
	 * Obtiene los item que componen el menú.
	 *
	 * @param acme_elementos Colección de elementos contenedores de los items del menú
	 * @return Colección de items resultante de la busqueda
	 */
	private Collection<DefaultMenuItem> obtenerItemsMenu(Collection<MenuElement> acme_elementos)
	{
		Collection<DefaultMenuItem> lcdmi_items;

		lcdmi_items = new LinkedList<DefaultMenuItem>();

		if(CollectionUtils.isValidCollection(acme_elementos))
		{
			for(MenuElement lme_elemento : acme_elementos)
			{
				if(lme_elemento != null)
				{
					Object lo_object;

					lo_object = (Object)lme_elemento;

					if(lo_object instanceof DefaultMenuItem)
					{
						DefaultMenuItem ldmi_item;

						ldmi_item = (DefaultMenuItem)lo_object;

						if(ldmi_item != null)
							lcdmi_items.add(ldmi_item);
					}
					else if(lo_object instanceof DefaultSubMenu)
					{
						DefaultSubMenu ldsm_item;

						ldsm_item = (DefaultSubMenu)lo_object;

						if(ldsm_item != null)
						{
							Collection<DefaultMenuItem> lcdmi_itemsTmp;

							lcdmi_itemsTmp = obtenerItemsMenu(ldsm_item.getElements());

							if(CollectionUtils.isValidCollection(lcdmi_itemsTmp))
								lcdmi_items.addAll(lcdmi_itemsTmp);
						}
					}
				}
			}
		}

		return lcdmi_items;
	}
}
