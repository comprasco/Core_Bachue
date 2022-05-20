package com.bachue.snr.prosnr01.web.bean;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanAnalistaCopias;
import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanCorreccion;
import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanDesistimiento;
import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanResponsableCorreccion;
import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanTurnosRecepcion;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.MigaPanCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcedenciaCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import com.bachue.snr.prosnr01.web.bean.actuacionesAdministrativas.BeanResponsableActuacionesAdmin;
import com.bachue.snr.prosnr01.web.bean.actuacionesAdministrativas.BeanSustanciadorActuacionesAdmin;
import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanAntiguoSistema;
import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanAprobacionLibroAntiguoSistema;
import com.bachue.snr.prosnr01.web.bean.apoyoNacional.BeanSolicitudApoyoNacional;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanAnalistaDeCertificadosEspeciales;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanAprobacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanBandejaConfrontacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanConsultasProcesos;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanGrabacion;
import com.bachue.snr.prosnr01.web.bean.certificados.BeanCertificados;
import com.bachue.snr.prosnr01.web.bean.consulta.SGD.BeanConsultaSGDBandeja;
import com.bachue.snr.prosnr01.web.bean.consulta.cuentaCupos.BeanConsultaCuentaCupos;
import com.bachue.snr.prosnr01.web.bean.consulta.documento.BeanConsultaDocumento;
import com.bachue.snr.prosnr01.web.bean.consulta.documento.publico.BeanConsultaDocumentoPublico;
import com.bachue.snr.prosnr01.web.bean.consulta.estado.predio.BeanConsultaEstadoPredio;
import com.bachue.snr.prosnr01.web.bean.consulta.estado.predio.BeanConsultaPredio;
import com.bachue.snr.prosnr01.web.bean.consulta.estado.predio.BeanGenerarReciboCaja;
import com.bachue.snr.prosnr01.web.bean.consulta.recibo.liquidacion.BeanConsultaReciboLiquidacion;
import com.bachue.snr.prosnr01.web.bean.consulta.reparto.calificacion.BeanConsultaRepartoCalificacion;
import com.bachue.snr.prosnr01.web.bean.consulta.reportes.BeanConsultaIndices;
import com.bachue.snr.prosnr01.web.bean.consulta.reportes.BeanConsultaReportes;
import com.bachue.snr.prosnr01.web.bean.consulta.solicitud.BeanConsultaSolicitud;
import com.bachue.snr.prosnr01.web.bean.consulta.trazabilidad.BeanConsultaTrazabilidad;
import com.bachue.snr.prosnr01.web.bean.copias.BeanExpedicionCopias;
import com.bachue.snr.prosnr01.web.bean.cuentaCupos.BeanCuentaCupos;
import com.bachue.snr.prosnr01.web.bean.cuentaCupos.BeanCuentaCuposInactivacion;
import com.bachue.snr.prosnr01.web.bean.devolucionDinero.BeanActoAdministrativos;
import com.bachue.snr.prosnr01.web.bean.devolucionDinero.BeanAnalistaDeDevolucion;
import com.bachue.snr.prosnr01.web.bean.devolucionDinero.BeanDevolucionDinero;
import com.bachue.snr.prosnr01.web.bean.digitador.masivo.BeanDigitadorMasivo;
import com.bachue.snr.prosnr01.web.bean.digitalizacion.BeanCoreBachue;
import com.bachue.snr.prosnr01.web.bean.digitalizacion.BeanLineaDeProduccion;
import com.bachue.snr.prosnr01.web.bean.entrega.BeanEntrega;
import com.bachue.snr.prosnr01.web.bean.entrega.BeanImpresionDocumentosCorrespondencia;
import com.bachue.snr.prosnr01.web.bean.entrega.BeanNotificacionPersonalOrip;
import com.bachue.snr.prosnr01.web.bean.entrega.BeanReimpresion;
import com.bachue.snr.prosnr01.web.bean.entrega.BeanReimpresionRecibos;
import com.bachue.snr.prosnr01.web.bean.homologacionActosLiquidacion.BeanAdministracionHomologacionActosLiquidacion;
import com.bachue.snr.prosnr01.web.bean.homologacionActosLiquidacion.BeanHomologacionActosLiquidacion;
import com.bachue.snr.prosnr01.web.bean.parameter.*;
import com.bachue.snr.prosnr01.web.bean.publicacionAvisosWeb.BeanPublicacionAvisosWeb;
import com.bachue.snr.prosnr01.web.bean.reanotacion.BeanReanotacion;
import com.bachue.snr.prosnr01.web.bean.reasignar.BeanReasignarTurno;
import com.bachue.snr.prosnr01.web.bean.recursos.BeanBandejaResolucionRecursos;
import com.bachue.snr.prosnr01.web.bean.recursos.BeanRecursos;
import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;
import com.bachue.snr.prosnr01.web.bean.registro.BeanRetomarSolicitud;
import com.bachue.snr.prosnr01.web.bean.testamentos.BeanTestamentos;
import com.bachue.snr.prosnr01.web.bean.trasladoMatriculas.BeanDetalleProyectaResolucion;
import com.bachue.snr.prosnr01.web.bean.trasladoMatriculas.BeanProyectaResolucion;
import com.bachue.snr.prosnr01.web.bean.traslados.BeanTraslados;
import com.bachue.snr.prosnr01.web.bean.visitas.BeanDetalleEjecucionVisitas;
import com.bachue.snr.prosnr01.web.bean.visitas.BeanSolicitudVisitas;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

import java.io.IOException;
import java.io.Serializable;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

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

	/** Propiedad imm menu. */
	private MenuModel imm_menuAyuda;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad URL catedra*/
	private String is_urlCatedra;

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
	 * Cambia el estilo de una opción seleccionada para identificarla como visitada
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
				// TODO FORMATO Comentar lambda antes de formatear
				lcdmi_items.stream().filter(
						ldmi_item -> ldmi_item.getValue() != null && ldmi_item.getValue().toString().equals(ls_opcion))
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
			case NavegacionCommon.PRINCIPAL:
				break;

			case NavegacionCommon.PRINCIPAL_HOME:
				break;

			case NavegacionCommon.CONSULTA_REPARTO_CALIFICACION:
			{
				BeanConsultaRepartoCalificacion lb_beanConsultaRepartoCalificacion;

				lb_beanConsultaRepartoCalificacion = (BeanConsultaRepartoCalificacion)lfc_context.getApplication()
						                                                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_REPARTO_CALIFICACION,
						    BeanConsultaRepartoCalificacion.class
						);

				lb_beanConsultaRepartoCalificacion.iniciar();

				break;
			}

			case NavegacionCommon.DETALLE_EJECUCION_VISITAS:
			{
				BeanDetalleEjecucionVisitas lb_beanConsultaRepartoCalificacion;

				lb_beanConsultaRepartoCalificacion = (BeanDetalleEjecucionVisitas)lfc_context.getApplication()
						                                                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DETALLE_EJECUCION_VISITAS,
						    BeanDetalleEjecucionVisitas.class
						);

				lb_beanConsultaRepartoCalificacion.clean();

				break;
			}

			case NavegacionCommon.CONSULTA_RECIBO_LIQUIDACION:
			{
				BeanConsultaReciboLiquidacion lb_consultaRecibloLiquidacion;

				lb_consultaRecibloLiquidacion = (BeanConsultaReciboLiquidacion)lfc_context.getApplication()
						                                                                      .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_RECIBO_LIQUIDACION,
						    BeanConsultaReciboLiquidacion.class
						);

				lb_consultaRecibloLiquidacion.clean();
				lb_consultaRecibloLiquidacion.consultar();

				break;
			}

			case NavegacionCommon.FIJACION_AVISOS_OFICINA_DESTINO:
			{
				BeanPublicacionAvisosWeb lbpaw_publicacionAvisosWeb;

				lbpaw_publicacionAvisosWeb = (BeanPublicacionAvisosWeb)lfc_context.getApplication()
						                                                              .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PUBLICACION_AVISOS_WEB, BeanPublicacionAvisosWeb.class
						);

				if(lbpaw_publicacionAvisosWeb != null)
				{
					lbpaw_publicacionAvisosWeb.clean();
					lbpaw_publicacionAvisosWeb.setIdEtapa(EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS);
					lbpaw_publicacionAvisosWeb.cargarCasos();
				}

				as_pagina = NavegacionCommon.FIJACION_AVISOS;

				break;
			}

			case NavegacionCommon.DESFIJACION_AVISOS:
			{
				BeanPublicacionAvisosWeb lbpaw_publicacionAvisosWeb;

				lbpaw_publicacionAvisosWeb = (BeanPublicacionAvisosWeb)lfc_context.getApplication()
						                                                              .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PUBLICACION_AVISOS_WEB, BeanPublicacionAvisosWeb.class
						);

				lbpaw_publicacionAvisosWeb.clean();
				lbpaw_publicacionAvisosWeb.setIdEtapa(EtapaCommon.EN_ESPERA_TERMINO_DE_PUBLICACION_AVISO_WEB);
				lbpaw_publicacionAvisosWeb.cargarCasos();

				as_pagina = NavegacionCommon.FIJACION_AVISOS;

				break;
			}

			case NavegacionCommon.TIPO_ESTADO_LIQUIDACION:
				break;

			case NavegacionCommon.ESTADO_ANOTACION:
				break;

			case NavegacionCommon.CUENTA_CUPOS:

				BeanCuentaCupos lbcc_beanCuentaCupos;
				lbcc_beanCuentaCupos = (BeanCuentaCupos)lfc_context.getApplication()
						                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CUENTA_CUPOS, BeanCuentaCupos.class
						);

				lbcc_beanCuentaCupos.clean();
				lbcc_beanCuentaCupos.obtenerBandejaEntrada();
				lbcc_beanCuentaCupos.generarGraficoDeTorta(EtapaCommon.ID_APROBACION_SOLICITUD_CUENTA_CUPO, false);

				break;

			case NavegacionCommon.CUENTA_CUPOS_INACTIVACION:

				BeanCuentaCuposInactivacion lbcc_beanCuentaCuposInactivacion;
				lbcc_beanCuentaCuposInactivacion = (BeanCuentaCuposInactivacion)lfc_context.getApplication()
						                                                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CUENTA_CUPOS_INACTIVACION,
						    BeanCuentaCuposInactivacion.class
						);

				lbcc_beanCuentaCuposInactivacion.clean();

				break;

			case NavegacionCommon.CONSULTA_CUENTA_CUPOS:

				BeanConsultaCuentaCupos lbcc_beanConsultaCuentaCupos;
				lbcc_beanConsultaCuentaCupos = (BeanConsultaCuentaCupos)lfc_context.getApplication()
						                                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_CUENTA_CUPOS, BeanConsultaCuentaCupos.class
						);

				lbcc_beanConsultaCuentaCupos.clean();

				break;

			case NavegacionCommon.BANDEJA_PROPIEDADES:

				BeanPropiedades lbp_beanPropiedades;
				lbp_beanPropiedades = (BeanPropiedades)obtenerInstanciaBean(
					    BeanPropiedades.class, BeanNameCommon.BEAN_PROPIEDADES
					);

				lbp_beanPropiedades.clean();
				lbp_beanPropiedades.cargarPropiedadesBeanReference();

				break;

			case NavegacionCommon.LINEA_DE_PRODUCCION:
			{
				BeanLineaDeProduccion lb_beanLineaDeProduccion;
				lb_beanLineaDeProduccion = (BeanLineaDeProduccion)lfc_context.getApplication()
						                                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_LINEA_DE_PRODUCCION, BeanLineaDeProduccion.class
						);

				lb_beanLineaDeProduccion.clean();
				lb_beanLineaDeProduccion.setIdEtapa(EtapaCommon.ID_ETAPA_DIGITALIZACION);
				lb_beanLineaDeProduccion.realizarConsulta();
				lb_beanLineaDeProduccion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_DIGITALIZACION, true);

				break;
			}

			case NavegacionCommon.COORDENADA:
				break;

			case NavegacionCommon.LETRA_EJE:
				break;

			case NavegacionCommon.CORE_BACHUE:
			{
				BeanCoreBachue lb_beanCoreBachue;
				lb_beanCoreBachue = (BeanCoreBachue)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CORE_BACHUE, BeanCoreBachue.class
						);

				lb_beanCoreBachue.clean();

				break;
			}

			case NavegacionCommon.ESTADO_REGISTRO:
				break;

			case NavegacionCommon.GRUPO_NATURALEZA_JURIDICA:
				break;

			case NavegacionCommon.CAUSAL_REIMPRESION:
				break;

			case NavegacionCommon.ESTADO_NUPRE:
				break;

			case NavegacionCommon.ESTADO_PREDIO:
				break;

			case NavegacionCommon.DETALLE_PROCESO_CONSULTA:
			{
				BeanDetalleProcesoConsulta lb_beanDetalleProcesoConsulta;
				lb_beanDetalleProcesoConsulta = (BeanDetalleProcesoConsulta)lfc_context.getApplication()
						                                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DETALLE_PROCESO_CONSULTA, BeanDetalleProcesoConsulta.class
						);

				lb_beanDetalleProcesoConsulta.clear();

				break;
			}

			case NavegacionCommon.SUBPROCESO:
			{
				BeanSubProceso lb_beanSubProceso;
				lb_beanSubProceso = (BeanSubProceso)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_SUBPROCESO, BeanSubProceso.class
						);

				lb_beanSubProceso.clear();

				break;
			}

			case NavegacionCommon.CALIDAD_SOLICITANTE:
			{
				BeanCalidadSolicitante lb_beanCalidadSolicitante;
				lb_beanCalidadSolicitante = (BeanCalidadSolicitante)lfc_context.getApplication()
						                                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CALIDAD_SOLICITANTE, BeanCalidadSolicitante.class
						);

				lb_beanCalidadSolicitante.clear();

				break;
			}

			case NavegacionCommon.TIPO_NOTIFICACION:
			{
				BeanTipoNotificacion lb_beanTipoNotificacion;
				lb_beanTipoNotificacion = (BeanTipoNotificacion)lfc_context.getApplication()
						                                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_NOTIFICACION, BeanTipoNotificacion.class
						);

				lb_beanTipoNotificacion.clear();

				break;
			}

			case NavegacionCommon.UNIDAD_TIEMPO_VENCIMIENTO:
			{
				BeanUnidadTiempoVencimiento lb_beanUnidadTiempoVencimiento;
				lb_beanUnidadTiempoVencimiento = (BeanUnidadTiempoVencimiento)lfc_context.getApplication()
						                                                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_UNIDAD_TIEMPO_VENIMIENTO, BeanUnidadTiempoVencimiento.class
						);

				lb_beanUnidadTiempoVencimiento.clear();

				break;
			}

			case NavegacionCommon.PROCESO_CONSULTA:
			{
				BeanProcesoConsulta lb_beanProcesoConsulta;
				lb_beanProcesoConsulta = (BeanProcesoConsulta)lfc_context.getApplication()
						                                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PROCESO_CONSULTA, BeanProcesoConsulta.class
						);

				lb_beanProcesoConsulta.clear();

				break;
			}

			case NavegacionCommon.ACC_ENTIDAD_EXTERNA_CONVENIO:
			{
				BeanAccEntidadExternaConvenio lb_beanAccEntidadExternaConvenio;
				lb_beanAccEntidadExternaConvenio = (BeanAccEntidadExternaConvenio)lfc_context.getApplication()
						                                                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ACC_ENTIDAD_EXTERNA_CONVENIO,
						    BeanAccEntidadExternaConvenio.class
						);

				if(lb_beanAccEntidadExternaConvenio != null)
				{
					lb_beanAccEntidadExternaConvenio.findAccEntidadesExternas();
					lb_beanAccEntidadExternaConvenio.clear();
				}

				break;
			}

			case NavegacionCommon.ENTIDADES:
			{
				BeanEntidades lb_beanEntidades;
				lb_beanEntidades = (BeanEntidades)lfc_context.getApplication()
						                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ENTIDADES, BeanEntidades.class
						);

				if(lb_beanEntidades != null)
					lb_beanEntidades.clear();

				break;
			}

			case NavegacionCommon.OPCION:
			{
				BeanOpcion lb_beanOpcion;
				lb_beanOpcion = (BeanOpcion)lfc_context.getApplication()
						                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_OPCION, BeanOpcion.class
						);

				if(lb_beanOpcion != null)
					lb_beanOpcion.clear();

				break;
			}

			case NavegacionCommon.NATURALEZA_JURIDICA:
			{
				BeanNaturalezaJuridica lb_beanNaturalezaJuridica;
				lb_beanNaturalezaJuridica = (BeanNaturalezaJuridica)lfc_context.getApplication()
						                                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_NATURALEZA_JURIDICA, BeanNaturalezaJuridica.class
						);

				lb_beanNaturalezaJuridica.clear();

				break;
			}

			case NavegacionCommon.TIPO_REQUIERE_MATRICULA:
				break;

			case NavegacionCommon.TIPO_ENTIDAD:
			{
				BeanTipoEntidad lb_beanTipoEntidad;
				lb_beanTipoEntidad = (BeanTipoEntidad)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_ENTIDAD, BeanTipoEntidad.class
						);

				if(lb_beanTipoEntidad != null)
					lb_beanTipoEntidad.setAllEntidades(null);

				break;
			}

			case NavegacionCommon.REANOTACION:
			{
				BeanReanotacion lbr_br;
				lbr_br = (BeanReanotacion)lfc_context.getApplication()
						                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REANOTACION, BeanReanotacion.class
						);
				lbr_br.clean();

				break;
			}

			case NavegacionCommon.CONSULTAS_PROCESOS:
			{
				BeanConsultasProcesos lbcp_bcp;
				lbcp_bcp = (BeanConsultasProcesos)lfc_context.getApplication()
						                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTAS_PROCESOS, BeanConsultasProcesos.class
						);
				lbcp_bcp.clear();
				lbcp_bcp.generarDatosBandeja(true);
				lbcp_bcp.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_41_ANALISTA_SOLICITUD_CONSULTAS_EXENTAS, false);

				break;
			}

			case NavegacionCommon.GENERAR_RECIBO_CAJA:
			{
				BeanGenerarReciboCaja lbgrc_bgrc;
				lbgrc_bgrc = (BeanGenerarReciboCaja)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_RECIBO_CAJA, BeanGenerarReciboCaja.class
						);
				lbgrc_bgrc.setEstado(false);
				lbgrc_bgrc.clean();

				break;
			}

			case NavegacionCommon.ANALISTA_DE_CERTIFICADOS_ESPECIALES:
			{
				BeanAnalistaDeCertificadosEspeciales lbadce_badce;
				lbadce_badce = (BeanAnalistaDeCertificadosEspeciales)lfc_context.getApplication()
						                                                            .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ANALISTA_DE_CERTIFICADOS_ESPECIALES,
						    BeanAnalistaDeCertificadosEspeciales.class
						);
				lbadce_badce.clear();
				lbadce_badce.generarDatosBandeja();
				lbadce_badce.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES, false);

				break;
			}

			case NavegacionCommon.TESTAMENTOS:
			{
				BeanTestamentos lbadce_badce;

				lbadce_badce = (BeanTestamentos)lfc_context.getApplication()
						                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TESTAMENTOS, BeanTestamentos.class
						);
				lbadce_badce.retornarBandeja();

				break;
			}

			case NavegacionCommon.ADMINISTRACION_USUARIOS:
			{
				BeanAdministracionUsuarios lbun_bun;
				lbun_bun = (BeanAdministracionUsuarios)lfc_context.getApplication()
						                                              .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ADMINISTRACION_USUARIOS, BeanAdministracionUsuarios.class
						);
				lbun_bun.iniciar();
				lbun_bun.clean();

				break;
			}

			case NavegacionCommon.CONSULTA_RUES:
				break;

			case NavegacionCommon.CONSULTA_SGD_BANDEJA:
			{
				BeanConsultaSGDBandeja lbun_bun;
				lbun_bun = (BeanConsultaSGDBandeja)obtenerInstanciaBean(
					    BeanConsultaSGDBandeja.class, BeanNameCommon.BEAN_CONSULTA_SGD_BANDEJA
					);

				lbun_bun.clear();

				break;
			}

			case NavegacionCommon.ALERTA_TRAMITE:
				break;

			case NavegacionCommon.DESCARGA_CONSTANTES:

				BeanConstantes lbc_constantes;
				lbc_constantes = (BeanConstantes)obtenerInstanciaBean(
					    BeanConstantes.class, BeanNameCommon.BEAN_CONSTANTES
					);

				if(lbc_constantes != null)
				{
					lbc_constantes.clean();
					lbc_constantes.cargarListaArchivos();
				}

				break;

			case NavegacionCommon.BANDEJA_ENTRADA:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacion(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_APROBACION, false);
				}

				break;
			}

			case NavegacionCommon.IMPRESION_DOCUMENTOS_ENTREGA_CORRESPONDENCIA:
			{
				BeanImpresionDocumentosCorrespondencia lbidc_bean;

				lbidc_bean = (BeanImpresionDocumentosCorrespondencia)lfc_context.getApplication()
						                                                            .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_IMPRESION_DOCUMENTOS_CORRESPONDENCIA,
						    BeanImpresionDocumentosCorrespondencia.class
						);

				lbidc_bean.clean();
				lbidc_bean.setIdEtapaCorrespondencia(
				    EtapaCommon.ID_ETAPA_IMPRESION_DOCUMENTOS_PARA_ENTREGA_CORRESPONDENCIA
				);
				lbidc_bean.generarDatosTramiteCantidad();

				break;
			}

			case NavegacionCommon.IMPRESION_DOCUMENTOS_CORRESPONDENCIA:
			{
				BeanImpresionDocumentosCorrespondencia lbidc_bean;

				lbidc_bean = (BeanImpresionDocumentosCorrespondencia)lfc_context.getApplication()
						                                                            .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_IMPRESION_DOCUMENTOS_CORRESPONDENCIA,
						    BeanImpresionDocumentosCorrespondencia.class
						);

				lbidc_bean.clean();
				lbidc_bean.setIdEtapaCorrespondencia(EtapaCommon.IMPRESION_DOCUMENTOS_CORRESPONDENCIA);
				lbidc_bean.generarDatosTramiteCantidad();

				break;
			}

			case NavegacionCommon.CONSULTA_TRAZABILIDAD:
			{
				BeanConsultaTrazabilidad lb_beanConsultaTrazabilidad;
				lb_beanConsultaTrazabilidad = (BeanConsultaTrazabilidad)lfc_context.getApplication()
						                                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_TRAZABILIDAD, BeanConsultaTrazabilidad.class
						);

				lb_beanConsultaTrazabilidad.clear();

				break;
			}

			case NavegacionCommon.CONSULTA_DOCUMENTO:
			{
				BeanConsultaDocumento lb_beanConsultaDocumento;
				lb_beanConsultaDocumento = (BeanConsultaDocumento)lfc_context.getApplication()
						                                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_DOCUMENTO, BeanConsultaDocumento.class
						);
				lb_beanConsultaDocumento.limpiarPantalla();

				break;
			}

			case NavegacionCommon.CONSULTA_DOCUMENTO_PUBLICO:
			{
				BeanConsultaDocumentoPublico lb_beanConsultaDocumento;
				lb_beanConsultaDocumento = (BeanConsultaDocumentoPublico)lfc_context.getApplication()
						                                                                .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_DOCUMENTO_PUBLICO,
						    BeanConsultaDocumentoPublico.class
						);

				lb_beanConsultaDocumento.clean();

				break;
			}

			case NavegacionCommon.CONSULTA_SOLICITUD:
			{
				BeanConsultaSolicitud lb_beanConsultaSolicitud;
				lb_beanConsultaSolicitud = (BeanConsultaSolicitud)lfc_context.getApplication()
						                                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_SOLICITUD, BeanConsultaSolicitud.class
						);

				lb_beanConsultaSolicitud.clear();

				break;
			}

			case NavegacionCommon.CONSULTA_REPORTES:
			{
				BeanConsultaReportes lb_beanConsultaReportes;
				lb_beanConsultaReportes = (BeanConsultaReportes)lfc_context.getApplication()
						                                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_REPORTES, BeanConsultaReportes.class
						);

				lb_beanConsultaReportes.iniciar();

				break;
			}

			case NavegacionCommon.CONSULTA_ESTADO_PREDIO:
			{
				BeanConsultaEstadoPredio lb_beanConsultaEstadoPredio;
				lb_beanConsultaEstadoPredio = (BeanConsultaEstadoPredio)lfc_context.getApplication()
						                                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_ESTADO_PREDIO, BeanConsultaEstadoPredio.class
						);

				lb_beanConsultaEstadoPredio.setEstado(false);

				break;
			}

			case NavegacionCommon.CONSULTA_PREDIO:
			{
				BeanConsultaPredio lb_beanConsultaPredio;
				lb_beanConsultaPredio = (BeanConsultaPredio)lfc_context.getApplication()
						                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_PREDIO, BeanConsultaPredio.class
						);

				lb_beanConsultaPredio.clean();

				break;
			}

			case NavegacionCommon.REGISTRO:
			{
				BeanRegistro lb_beanRegistro;

				lb_beanRegistro = inicializarBeanRegistro();

				lb_beanRegistro.setIdProceso(ProcesoCommon.ID_PROCESO_6);
				lb_beanRegistro.setLabelMigaPan(MigaPanCommon.REGISTRO);

				break;
			}
			
			case NavegacionCommon.SIMULADOR_LIQUIDACION:
			{
				BeanRegistro lb_beanRegistro;
				
				lb_beanRegistro = inicializarBeanRegistro();
				
				lb_beanRegistro.setIdProceso(ProcesoCommon.ID_PROCESO_6);
				lb_beanRegistro.setLabelMigaPan(MigaPanCommon.REGISTRO);
				lb_beanRegistro.setSimuladorLiquidacion(true);
				
				as_pagina = NavegacionCommon.REGISTRO;
				
				break;
			}

			case NavegacionCommon.SOLICITUD_RECEPCION_DOCUMENTOS:
			{
				BeanRegistro lb_beanRegistro;

				lb_beanRegistro = inicializarBeanRegistro();

				lb_beanRegistro.setIdProceso(ProcesoCommon.ID_PROCESO_10);
				lb_beanRegistro.setLabelMigaPan(MigaPanCommon.RECEPCION_DOCUMENTOS);

				as_pagina = NavegacionCommon.REGISTRO;

				break;
			}

			case NavegacionCommon.SOLICITUD_GRABACION_MATRICULAS:
			{
				BeanRegistro lb_beanRegistro;

				lb_beanRegistro = inicializarBeanRegistro();

				lb_beanRegistro.setIdSubproceso(ProcesoCommon.ID_SUBPROCESO_1);
				lb_beanRegistro.setIdProceso(ProcesoCommon.ID_PROCESO_37);
				lb_beanRegistro.setLabelMigaPan(MigaPanCommon.GRABACION_DE_MATRICULAS);
				lb_beanRegistro.setEsGrabacionMatriculas(true);

				{
					Solicitud ls_solicitud;
					ls_solicitud = lb_beanRegistro.getSolicitud();

					if(ls_solicitud != null)
						ls_solicitud.setIdTipoRecepcion(ProcedenciaCommon.ORIP_DE_ORIGEN);
				}

				as_pagina = NavegacionCommon.REGISTRO;

				break;
			}

			case NavegacionCommon.SOLICITUD_CREACION_MATRICULAS_OFICIO:
			{
				BeanRegistro lb_beanRegistro;

				lb_beanRegistro = inicializarBeanRegistro();

				lb_beanRegistro.setIdProceso(ProcesoCommon.ID_PROCESO_44);
				lb_beanRegistro.setIdSubproceso(ProcesoCommon.ID_SUBPROCESO_1);
				lb_beanRegistro.setLabelMigaPan(MigaPanCommon.CREACION_MATRICULAS_OFICIO);
				lb_beanRegistro.setEsCreacionMatriculasOficio(true);

				as_pagina = NavegacionCommon.REGISTRO;

				break;
			}

			case NavegacionCommon.SOLICITUD_CORRECCIONES:
			{
				BeanRegistro lb_beanRegistro;

				lb_beanRegistro = inicializarBeanRegistro();

				lb_beanRegistro.setIdProceso(ProcesoCommon.ID_PROCESO_3);
				lb_beanRegistro.setLabelMigaPan(MigaPanCommon.CORRECCIONES);
				lb_beanRegistro.setProcesoCorrecciones(true);

				as_pagina = NavegacionCommon.REGISTRO;

				break;
			}

			case NavegacionCommon.SOLICITUD_TRASLADO_MATRICULAS:
			{
				BeanRegistro lb_beanRegistro;

				lb_beanRegistro = inicializarBeanRegistro();

				lb_beanRegistro.setIdProceso(ProcesoCommon.ID_PROCESO_38);
				lb_beanRegistro.setLabelMigaPan(MigaPanCommon.TRASLADO_MATRICULAS);
				lb_beanRegistro.setProcesoCorrecciones(true);
				lb_beanRegistro.setProcesoTraslado(true);
				lb_beanRegistro.validarCirculoUsuario();

				as_pagina = NavegacionCommon.REGISTRO;

				break;
			}

			case NavegacionCommon.CERTIFICADOS:
			{
				BeanCertificados lbc_beanCertificados;
				BeanDireccion    lbd_beanDireccion;

				lbc_beanCertificados     = (BeanCertificados)lfc_context.getApplication()
						                                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CERTIFICADOS, BeanCertificados.class
						);
				lbd_beanDireccion        = getBeanDireccion();

				lbc_beanCertificados.clean();
				lbd_beanDireccion.limpiarBeanDireccion();
				lbd_beanDireccion.setForm(BeanCertificados.is_idForm);
				lbc_beanCertificados.setProcesoCertificados(true);

				break;
			}

			case NavegacionCommon.DEVOLUCIONES_DINERO:
			{
				BeanDevolucionDinero lb_beanDevolucionDinero;
				lb_beanDevolucionDinero = (BeanDevolucionDinero)lfc_context.getApplication()
						                                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DEVOLUCION_DINERO, BeanDevolucionDinero.class
						);

				lb_beanDevolucionDinero.clean();
				lb_beanDevolucionDinero.setPreguntaMasivoIntervinientes("ind");
				lb_beanDevolucionDinero.findSubprocesosDevDinero();

				break;
			}

			case NavegacionCommon.CONSULTA_INDICES:
			{
				BeanConsultaIndices lb_beanRegistro;
				BeanDireccion       lbd_beanDireccion;

				lb_beanRegistro       = (BeanConsultaIndices)lfc_context.getApplication()
						                                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_INDICES, BeanConsultaIndices.class
						);
				lbd_beanDireccion     = getBeanDireccion();

				lb_beanRegistro.limpiarRegistro();
				lb_beanRegistro.setMostrarLimpiarDatos(true);
				lb_beanRegistro.cargarMediosNotCom(false);
				lb_beanRegistro.cargarMediosNotComInter(false);
				lb_beanRegistro.esconderPanels();
				lb_beanRegistro.setIdProceso(ProcesoCommon.ID_PROCESO_2);
				lb_beanRegistro.setTiposDocumentalesBlob(null);
				lbd_beanDireccion.limpiarBeanDireccion();
				lbd_beanDireccion.setForm(BeanConsultaIndices.cs_ID_FORM);

				break;
			}

			case NavegacionCommon.EXPEDICION_COPIAS:
			{
				BeanExpedicionCopias lbec_beanExpedicionCopias;

				lbec_beanExpedicionCopias = (BeanExpedicionCopias)obtenerInstanciaBean(
					    BeanExpedicionCopias.class, BeanNameCommon.BEAN_EXPEDICION_COPIAS
					);

				lbec_beanExpedicionCopias.setMostrarObservaciones(true);
				lbec_beanExpedicionCopias.limpiarRegistro();
				lbec_beanExpedicionCopias.setMostrarLimpiarDatos(true);
				lbec_beanExpedicionCopias.cargarMediosNotComCopias(false);
				lbec_beanExpedicionCopias.esconderPanels();
				lbec_beanExpedicionCopias.setIdProceso(ProcesoCommon.ID_PROCESO_5);

				{
					Solicitud ls_solicitud;

					ls_solicitud = lbec_beanExpedicionCopias.getSolicitud();

					if(ls_solicitud != null)
						ls_solicitud.setIdCalidadSolicitante(CalidadSolicitanteCommon.INTERESADO);
				}

				{
					BeanDireccion lbd_beanDireccion;

					lbd_beanDireccion = getBeanDireccion();

					lbd_beanDireccion.limpiarBeanDireccion();
					lbd_beanDireccion.setForm(BeanExpedicionCopias.cs_ID_FORM);
				}

				break;
			}

			case NavegacionCommon.RETOMAR_SOLICITUD:
			{
				BeanRetomarSolicitud lb_beanRetomarSolicitud;
				lb_beanRetomarSolicitud = (BeanRetomarSolicitud)lfc_context.getApplication()
						                                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_RETOMAR_SOLICITUD, BeanRetomarSolicitud.class
						);

				lb_beanRetomarSolicitud.clear();

				break;
			}

			case NavegacionCommon.CALIFICACION:
			{
				BeanCalificacion lb_beanCalificacion;
				lb_beanCalificacion = (BeanCalificacion)lfc_context.getApplication()
						                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
						);

				lb_beanCalificacion.clear();
				lb_beanCalificacion.generarDatosTramiteCantidad();
				lb_beanCalificacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_CALIFICACION, false);

				break;
			}

			case NavegacionCommon.GRABACION:
			{
				BeanCalificacion lb_beanCalificacion;
				String           ls_idEtapa;

				lb_beanCalificacion     = (BeanCalificacion)lfc_context.getApplication()
						                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
						);
				ls_idEtapa              = StringUtils.getString(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS);

				lb_beanCalificacion.clear();
				lb_beanCalificacion.setIdEtapa(ls_idEtapa);
				lb_beanCalificacion.generarDatosTramiteCantidad(":fGrabacion", ls_idEtapa, true);
				lb_beanCalificacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS, false);

				break;
			}

			case NavegacionCommon.GRABACION_MATRICULAS:
			{
				BeanGrabacion lb_beanGrabacion;

				lb_beanGrabacion = (BeanGrabacion)lfc_context.getApplication()
						                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_GRABACION, BeanGrabacion.class
						);

				lb_beanGrabacion.clean(true);
				lb_beanGrabacion.generarDatosTramiteCantidad();
				lb_beanGrabacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS, false);

				break;
			}

			case NavegacionCommon.ENTREGA:
			{
				BeanEntrega lbe_bean;
				lbe_bean = (BeanEntrega)FacesUtils.obtenerInstanciaBean(BeanEntrega.class, BeanNameCommon.BEAN_ENTREGA);

				if(lbe_bean != null)
					lbe_bean.clean();

				break;
			}

			case NavegacionCommon.NOTIFICACION_PERSONAL:
			{
				BeanNotificacionPersonalOrip lnpo_bean;
				lnpo_bean = (BeanNotificacionPersonalOrip)FacesUtils.obtenerInstanciaBean(
					    BeanEntrega.class, BeanNameCommon.BEAN_NOTIFICACION_PERSONAL_ORIP
					);

				if(lnpo_bean != null)
					lnpo_bean.clean();

				break;
			}

			case NavegacionCommon.HOMOLOGACION_ACTOS_LIQUIDACION:
			{
				BeanHomologacionActosLiquidacion lbhal_beanHomologacionActosLiquidacion;
				lbhal_beanHomologacionActosLiquidacion = (BeanHomologacionActosLiquidacion)lfc_context.getApplication()
						                                                                                  .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_HOMOLOGACION_ACTOS_LIQUIDA,
						    BeanHomologacionActosLiquidacion.class
						);

				lbhal_beanHomologacionActosLiquidacion.clear();
				lbhal_beanHomologacionActosLiquidacion.generarDatosTramiteCantidad();

				break;
			}

			case NavegacionCommon.ADMINISTRACION_HOMOLOGACION_ACTOS_LIQUIDACION:
			{
				BeanAdministracionHomologacionActosLiquidacion lbahal_beanAdministracionHomologacionActosLiquidacion;
				lbahal_beanAdministracionHomologacionActosLiquidacion = (BeanAdministracionHomologacionActosLiquidacion)lfc_context.getApplication()
						                                                                                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ADMINISTRACION_HOMOLOGACION_ACTOS_LIQUIDACION,
						    BeanAdministracionHomologacionActosLiquidacion.class
						);

				lbahal_beanAdministracionHomologacionActosLiquidacion.clear();

				break;
			}

			case NavegacionCommon.TIPO_RECURSO:
			{
				BeanTipoRecurso lb_beanTipoRecurso;
				lb_beanTipoRecurso = (BeanTipoRecurso)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_RECURSO, BeanTipoRecurso.class
						);

				lb_beanTipoRecurso.clear();

				break;
			}

			case NavegacionCommon.TIPO_DECISION_RECURSO:
			{
				BeanTipoDecisionRecurso lb_beanTipoDecisionRecurso;
				lb_beanTipoDecisionRecurso = (BeanTipoDecisionRecurso)lfc_context.getApplication()
						                                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_DECISION_RECURSO, BeanTipoDecisionRecurso.class
						);

				lb_beanTipoDecisionRecurso.clear();

				break;
			}

			case NavegacionCommon.ANTIGUO_SISTEMA:
			{
				BeanAntiguoSistema lb_beanAntiguoSistema;
				lb_beanAntiguoSistema = (BeanAntiguoSistema)lfc_context.getApplication()
						                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ANTIGUO_SISTEMA, BeanAntiguoSistema.class
						);

				lb_beanAntiguoSistema.clear();
				lb_beanAntiguoSistema.generarDatosTramiteCantidad();
				lb_beanAntiguoSistema.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA, false);

				break;
			}

			case NavegacionCommon.ANALISTA_DE_CREACION_MATRICULAS_OFICIO:
			{
				BeanAntiguoSistema lb_beanAntiguoSistema;
				lb_beanAntiguoSistema = (BeanAntiguoSistema)lfc_context.getApplication()
						                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ANTIGUO_SISTEMA, BeanAntiguoSistema.class
						);

				lb_beanAntiguoSistema.clear();
				lb_beanAntiguoSistema.setIdEtapa(EtapaCommon.ID_ETAPA_CREACION_MATRICULAS_OFICIO);
				lb_beanAntiguoSistema.generarDatosTramiteCantidad();
				lb_beanAntiguoSistema.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_CREACION_MATRICULAS_OFICIO, false);

				break;
			}

			case NavegacionCommon.APROBACION_ANTIGUO_SISTEMA:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAntiguoSistema(true);
					lb_beanAprobacion.setVieneDeAutorizacionFirma(false);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_MAYOR_VALOR, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBACION_ANTIGUO_SISTEMA_OFI_DESTINO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeCompOtroCirculo(true);
					lb_beanAprobacion.setRegresarBandejaOfiDestino(true);
					lb_beanAprobacion.setVieneDeAutorizacionFirma(false);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.SOLICITAR_COMPLEMENTACION_OTRO_CICRCULO, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_OFI_DESTINO;

				break;
			}

			case NavegacionCommon.APROBACION_LIBRO_ANTIGUO_SISTEMA:
			{
				BeanAprobacionLibroAntiguoSistema lbalas_bean;

				lbalas_bean = (BeanAprobacionLibroAntiguoSistema)lfc_context.getApplication()
						                                                        .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION_LIBRO_ANTIGUO_SISTEMA,
						    BeanAprobacionLibroAntiguoSistema.class
						);

				lbalas_bean.clear();
				lbalas_bean.limpiarBandeja();
				lbalas_bean.setIdEtapa(StringUtils.getString(EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S));
				lbalas_bean.generarDatosTramiteCantidad();
				lbalas_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S, false);

				break;
			}

			case NavegacionCommon.APROBACION_CORRECCIONES:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeCorrecciones(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBACION_DELEGADO_REGISTRO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacionVisitas(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_DELEGADO_REGISTRO, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}
			case NavegacionCommon.APROBACION_SUPERINTENDENTE:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						.evaluateExpressionGet(
								lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
								);
				
				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacionVisitasSuperintendente(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE_845, false);
				}
				
				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;
				
				break;
			}

			case NavegacionCommon.APROBACION_EJECUCION:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacionEjecucionVisitas(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_APROBACION_EJECUCION, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.EJECUCION_VISITAS:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeEjecucionVisitas(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_830, false);
					lb_beanAprobacion.setVieneDeEjecucionVisitas(true);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.BANDEJA_FIJACION_AVISOS_OFICINA_ORIGEN:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeBandejaFijacionAvisosOficinaOrigen(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.BANDEJA_FIJACION_AVISOS_OFICINA_DESTINO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeBandejaFijacionAvisosOficinaDestino(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBACION_TRASLADOS_OFICINA_DESTINO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacionTrasladosOficinaDestino(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(
					    EtapaCommon.ID_ETAPA_APROBACION_DE_TRASLADOS_OFICINA_DESTINO, false
					);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBACION_DESPACHO_SUPERINTENDIENTE:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeDespachoSuperintendente(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(
					    EtapaCommon.ID_ETAPA_APROBACION_RESOLUCION_CREACION_SUPRESION_MODIFICACION, false
					);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBACION_SUPERINTENDIENTE:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacionSuperintendente(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBACION_ASESORIA_JURIDICA:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacionAsesoriaJuridica(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(
					    EtapaCommon.ID_ETAPA_REVISION_JURIDICA_FASE_TRASLADOS, false
					);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.ANALISIS_TRASLADO_OFICINA_DESTINO:
			{
				BeanTraslados lbt_bean;

				lbt_bean = (BeanTraslados)lfc_context.getApplication()
						                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TRASLADOS, BeanTraslados.class
						);

				lbt_bean.clear();
				lbt_bean.limpiarBandeja();
				lbt_bean.setVieneDeAnalisisTrasladosOficinaDestino(true);
				lbt_bean.setIdEtapa(StringUtils.getString(EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO));
				lbt_bean.generarDatosTramiteCantidad();
				lbt_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO, false);

				as_pagina = NavegacionCommon.ANALISIS_TRASLADOS;

				break;
			}

			case NavegacionCommon.APROBACION_DIRECCION_TECNICA_REGISTRO_OFICINA_DESTINO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacionDireccionTecnicaRegistro(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(
					    EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_DE_REGISTRO_OFICINA_DESTINO, false
					);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBACION_FIRMA_DESPACHO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacionFirmaDespacho(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_APROBACION_FIRMA_DESPACHO, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.EJECUCION_TRASLADO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeEjecucionTraslado(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_EJECUCION_TRASLADOS, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBACION_DIRECCION_TECNICA_REGISTRO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeDireccionTecnicaRegistro(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(
					    EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_REGISTRO, false
					);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.AUTORIZACION_RESPONSABLE_DEVOLUCIONES:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAutorizacionResponsableDevoluciones(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.AUTORIZACION_RESPONSABLE_DEVOLUCIONES, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBACION_SUBDIRECCION_APOYO_JURIDICO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeAprobacionSubDireccionApoyoJuridico(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_470, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.REVISION_RECURSOS_COORDINADOR_JURIDICO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeRevisionRecursosCoordinadorJuridico(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_465, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.APROBAR_SOLICITUD_APOYO_NACIONAL:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneAprobacionApoyoNacional(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_APROBADOR_APOYO_NACIONAL, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.REVISION_COORDINADOR_JURIDICO:
			{
				BeanAprobacion lb_beanAprobacion;
				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeCoordinadorJuridico(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.TIPO_OFICINA:
			{
				BeanTipoOficina lb_beanTipoOficina;
				lb_beanTipoOficina = (BeanTipoOficina)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_OFICINA, BeanTipoOficina.class
						);

				if(lb_beanTipoOficina != null)
					lb_beanTipoOficina.setAllOficinas(null);

				break;
			}

			case NavegacionCommon.CANAL:
				break;

			case NavegacionCommon.ESTADOS:
				break;

			case NavegacionCommon.ORIGEN:
				break;

			case NavegacionCommon.PLANTILLA:
				break;

			case NavegacionCommon.PLANTILLA_COMUNICACION:
				break;

			case NavegacionCommon.ENTIDAD_RECAUDADORA:
				break;

			case NavegacionCommon.CONSTANTES:
			{
				BeanConstantes lbc_bean;

				lbc_bean = (BeanConstantes)obtenerInstanciaBean(BeanConstantes.class, BeanNameCommon.BEAN_CONSTANTES);

				lbc_bean.setVieneNotificacionComunicaciones(false);

				break;
			}

			case NavegacionCommon.CONSTANTES_NOTIFICACIONES_COMUNICAICONES:
			{
				BeanConstantes lbc_bean;

				lbc_bean = (BeanConstantes)obtenerInstanciaBean(BeanConstantes.class, BeanNameCommon.BEAN_CONSTANTES);

				lbc_bean.setVieneNotificacionComunicaciones(true);

				as_pagina = NavegacionCommon.CONSTANTES;

				break;
			}

			case NavegacionCommon.TIPO_PERSONA:
			{
				BeanTipoPersona lb_beanTipoPersona;
				lb_beanTipoPersona = (BeanTipoPersona)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_PERSONA, BeanTipoPersona.class
						);

				lb_beanTipoPersona.clear();

				break;
			}

			case NavegacionCommon.TIPO_RRR:
			{
				BeanTipoPersona lb_beanTipoPersona;
				lb_beanTipoPersona = (BeanTipoPersona)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_PERSONA, BeanTipoPersona.class
						);

				lb_beanTipoPersona.clear();

				break;
			}

			case NavegacionCommon.BANDEJA_PROYECTA_RESOLUCION:
			{
				BeanProyectaResolucion lb_beanProyectaResolucion;
				lb_beanProyectaResolucion = (BeanProyectaResolucion)lfc_context.getApplication()
						                                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PROYECTA_RESOLUCION, BeanProyectaResolucion.class
						);

				lb_beanProyectaResolucion.clear();
				lb_beanProyectaResolucion.generarDatosTramiteCantidad();
				lb_beanProyectaResolucion.generarGraficoDeTorta(EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS, false);

				break;
			}

			case NavegacionCommon.BANDEJA_EJECUTOR_TRASLADOS:
			{
				BeanDetalleProyectaResolucion lbdpr_bean;
				lbdpr_bean = (BeanDetalleProyectaResolucion)lfc_context.getApplication()
						                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DETALLE_PROYECTA_RESOLUCION,
						    BeanDetalleProyectaResolucion.class
						);

				if(lbdpr_bean != null)
				{
					lbdpr_bean.setBandejaentrada(true);
					lbdpr_bean.setConsultaIndividual(false);
					lbdpr_bean.setData(null);
					lbdpr_bean.setIdEtapa(EtapaCommon.ID_ETAPA_EJECUTOR);

					lbdpr_bean.generarData();
				}

				as_pagina = NavegacionCommon.DETALLE_BANDEJA_PROYECTA_RESOLUCION_TURNOS;

				break;
			}

			case NavegacionCommon.USUARIOS:
			{
				BeanUsuarios lb_beanUsuarios;
				lb_beanUsuarios = (BeanUsuarios)lfc_context.getApplication()
						                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_USUARIOS, BeanUsuarios.class
						);
				lb_beanUsuarios.clean();
				lb_beanUsuarios.iniciar();

				break;
			}

			case NavegacionCommon.ROL:
				break;

			case NavegacionCommon.TIPORECEPCION:
				break;

			case NavegacionCommon.ALERTA_NATURALEZA_JURIDICA:
				break;

			case NavegacionCommon.CAUSAL_APROBACION_DEVOLUCION:
				break;

			case NavegacionCommon.CAUSAL_CORRECCION:
				break;

			case NavegacionCommon.CAUSAL_MAYOR_VALOR:
				break;

			case NavegacionCommon.CAMPOS_CONSULTA:
				break;

			case NavegacionCommon.CAMPOS_CRITERIO_BUSQUEDA:
			{
				BeanCampoCriterioBusqueda lb_beanCamposCristerioBusqueda;

				lb_beanCamposCristerioBusqueda = (BeanCampoCriterioBusqueda)lfc_context.getApplication()
						                                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CAMPO_CRITERIO_BUSQUEDA, BeanCampoCriterioBusqueda.class
						);

				lb_beanCamposCristerioBusqueda.clear();

				break;
			}

			case NavegacionCommon.CONDICION_TARIFA:
				break;

			case NavegacionCommon.ENTIDAD_EXTERNA_CONVENIO:
				break;

			case NavegacionCommon.PROCESO:
				break;

			case NavegacionCommon.PROCESO_CANAL:
				break;

			case NavegacionCommon.ROL_OPCION:
				break;

			case NavegacionCommon.TIPO_ACTO_PROHIBICION:
				break;

			case NavegacionCommon.TIPO_DOCUMENTAL:
				break;

			case NavegacionCommon.TIPO_DOCUMENTAL_ACTO:
				break;

			case NavegacionCommon.CONSULTAS:
				break;

			case NavegacionCommon.MEDIDA_AREA:
				break;

			case NavegacionCommon.PAISES:
				break;

			case NavegacionCommon.PARTE_INTERESADA:
				break;

			case NavegacionCommon.DEPARTAMENTOS:
				break;

			case NavegacionCommon.MOTIVO_TRAMITE:
				break;

			case NavegacionCommon.MUNICIPIOS:
				break;

			case NavegacionCommon.CIRCULO_FESTIVO:
				break;

			case NavegacionCommon.CIRCULOS_REGISTRALES:
			{
				BeanCirculosRegistrales lb_beanRol;
				lb_beanRol = (BeanCirculosRegistrales)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CIRCULOS_REGISTRALES, BeanCirculosRegistrales.class
						);
				lb_beanRol.iniciar();

				break;
			}

			case NavegacionCommon.DOMINIOS_NATURALEZA_JURIDICA:
			{
				BeanDominiosNaturalezaJuridica lb_beanRol;
				lb_beanRol = (BeanDominiosNaturalezaJuridica)lfc_context.getApplication()
						                                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DOMINIOS_NATURALEZA_JURIDICA,
						    BeanDominiosNaturalezaJuridica.class
						);
				lb_beanRol.iniciar();

				break;
			}

			case NavegacionCommon.DOMINIO_RRR:
				break;

			case NavegacionCommon.INTERESADO_DOCUMENTO_TIPOS:
			{
				BeanInteresadoDocumentoTipo lb_beanInteresadoDocumentoTipo;
				lb_beanInteresadoDocumentoTipo = (BeanInteresadoDocumentoTipo)lfc_context.getApplication()
						                                                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_INTERESADO_DOCUMENTO_TIPO,
						    BeanInteresadoDocumentoTipo.class
						);

				lb_beanInteresadoDocumentoTipo.iniciar();

				break;
			}

			case NavegacionCommon.INTERESADO_NATURAL_GENERO:
			{
				BeanInteresadoNaturalGenero lb_beanInteresadoNaturalGenero;
				lb_beanInteresadoNaturalGenero = (BeanInteresadoNaturalGenero)lfc_context.getApplication()
						                                                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_INTERESADO_NATURAL_GENERO,
						    BeanInteresadoNaturalGenero.class
						);

				lb_beanInteresadoNaturalGenero.clear();

				break;
			}

			case NavegacionCommon.REPORTES:
			{
				BeanReportes lb_beanReportes;
				lb_beanReportes = (BeanReportes)lfc_context.getApplication()
						                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REPORTES, BeanReportes.class
						);

				lb_beanReportes.clear();

				break;
			}

			case NavegacionCommon.OFICINA_ORIGEN:
				break;

			case NavegacionCommon.TIPO_PREDIO:
				break;

			case NavegacionCommon.TIPO_USO_SUELO:
				break;

			case NavegacionCommon.TIPOS_ACTO:
			{
				BeanTiposActo lb_beanTiposActo;
				lb_beanTiposActo = (BeanTiposActo)lfc_context.getApplication()
						                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPOS_ACTO, BeanTiposActo.class
						);
				lb_beanTiposActo.iniciar();

				break;
			}

			case NavegacionCommon.VEREDAS:
			{
				BeanVereda lb_beanVereda;
				lb_beanVereda = (BeanVereda)lfc_context.getApplication()
						                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_VEREDA, BeanVereda.class
						);

				lb_beanVereda.clear();

				break;
			}

			case NavegacionCommon.CIRCULO_ORIGEN_DESTINO:
			{
				BeanCirculoOrigenDestino lbcod_beanCirculoOrigenDestino;
				lbcod_beanCirculoOrigenDestino = (BeanCirculoOrigenDestino)lfc_context.getApplication()
						                                                                  .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CIRCULO_ORIGEN_DESTINO, BeanCirculoOrigenDestino.class
						);

				lbcod_beanCirculoOrigenDestino.clear();
				lbcod_beanCirculoOrigenDestino.findAll();

				break;
			}

			case NavegacionCommon.ZONA_REGISTRAL:
			{
				BeanZonaRegistral lb_zonaRegistral;
				lb_zonaRegistral = (BeanZonaRegistral)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ZONA_REGISTRAL, BeanZonaRegistral.class
						);
				lb_zonaRegistral.clear();

				break;
			}

			case NavegacionCommon.TIPOS_EJE:
			{
				BeanTiposEje lb_beanTiposEje;
				lb_beanTiposEje = (BeanTiposEje)lfc_context.getApplication()
						                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPOS_EJE, BeanTiposEje.class
						);
				lb_beanTiposEje.iniciar();

				break;
			}

			case NavegacionCommon.REGIONAL:
				break;

			case NavegacionCommon.TIPOS_CAUSAL:
			{
				BeanTiposCausal lb_beanTiposCausal;
				lb_beanTiposCausal = (BeanTiposCausal)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPOS_CAUSAL, BeanTiposCausal.class
						);
				lb_beanTiposCausal.iniciar();

				break;
			}

			case NavegacionCommon.TIPO_PAGO:
			{
				BeanTipoPago lbtp_tp;
				lbtp_tp = (BeanTipoPago)lfc_context.getApplication()
						                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_PAGO, BeanTipoPago.class
						);

				if(lbtp_tp != null)
					lbtp_tp.setAllPagos(null);

				break;
			}

			case NavegacionCommon.SALARIO_MINIMO:
			{
				BeanSalarioMinimo lbtp_tp;
				lbtp_tp = (BeanSalarioMinimo)lfc_context.getApplication()
						                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_SALARIO_MINIMO, BeanSalarioMinimo.class
						);

				if(lbtp_tp != null)
					lbtp_tp.setAllPagos(null);

				break;
			}

			case NavegacionCommon.TIPO_ADQUISICION:
			{
				BeanTipoAdquisicion lbtp_tp;
				lbtp_tp = (BeanTipoAdquisicion)lfc_context.getApplication()
						                                      .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_ADQUISICION, BeanTipoAdquisicion.class
						);

				if(lbtp_tp != null)
					lbtp_tp.setTipoAdquisicion(null);

				break;
			}

			case NavegacionCommon.TIPO_TARIFA:
			{
				BeanTipoTarifaRegistral lbtp_tp;
				lbtp_tp = (BeanTipoTarifaRegistral)lfc_context.getApplication()
						                                          .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_TARIFA_REGISTRAL, BeanTipoTarifaRegistral.class
						);

				if(lbtp_tp != null)
					lbtp_tp.setAllInfo(null);

				break;
			}

			case NavegacionCommon.TIPOS_ANOTACION:
			{
				BeanTiposAnotacion lbta_ta;
				lbta_ta = (BeanTiposAnotacion)lfc_context.getApplication()
						                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPOS_ANOTACION, BeanTiposAnotacion.class
						);
				lbta_ta.iniciar();

				break;
			}

			case NavegacionCommon.TIPOS_TESTAMENTO:
			{
				BeanTiposTestamento lbtt_tt;
				lbtt_tt = (BeanTiposTestamento)lfc_context.getApplication()
						                                      .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPOS_TESTAMENTO, BeanTiposTestamento.class
						);
				lbtt_tt.iniciar();

				break;
			}

			case NavegacionCommon.ETAPAS:
			{
				BeanEtapas lbe_e;
				lbe_e = (BeanEtapas)lfc_context.getApplication()
						                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ETAPAS, BeanEtapas.class
						);
				lbe_e.iniciar();

				break;
			}

			case NavegacionCommon.USUARIO_LINEAS:
			{
				BeanUsuarioLineas lbul_ul;
				lbul_ul = (BeanUsuarioLineas)lfc_context.getApplication()
						                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_USUARIO_LINEAS, BeanUsuarioLineas.class
						);

				lbul_ul.iniciar();

				break;
			}

			case NavegacionCommon.FASES:
			{
				BeanFases lbf_bf;
				lbf_bf = (BeanFases)lfc_context.getApplication()
						                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_FASES, BeanFases.class
						);
				lbf_bf.iniciar();

				break;
			}

			case NavegacionCommon.USUARIO_CIRCULOS:
			{
				BeanUsuarioCirculos lbuc_buc;
				lbuc_buc = (BeanUsuarioCirculos)lfc_context.getApplication()
						                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_USUARIO_CIRCULOS, BeanUsuarioCirculos.class
						);
				lbuc_buc.iniciar();
				lbuc_buc.consultarUsuarios();

				break;
			}

			case NavegacionCommon.USUARIO_ETAPAS:
			{
				BeanUsuarioEtapas lbue_bue;
				lbue_bue = (BeanUsuarioEtapas)lfc_context.getApplication()
						                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_USUARIO_ETAPAS, BeanUsuarioEtapas.class
						);
				lbue_bue.iniciar();
				lbue_bue.consultarUsuarios();

				break;
			}

			case NavegacionCommon.LINEA_PRODUCCION:
			{
				BeanLineaProduccion lblp_blp;
				lblp_blp = (BeanLineaProduccion)lfc_context.getApplication()
						                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_LINEA_PRODUCCION, BeanLineaProduccion.class
						);

				lblp_blp.clear();

				break;
			}

			case NavegacionCommon.DESBORDES:
			{
				BeanDesbordes lblp_blp;
				lblp_blp = (BeanDesbordes)lfc_context.getApplication()
						                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DESBORDES, BeanDesbordes.class
						);
				lblp_blp.cargarCirculosRegistrales();
				lblp_blp.clean();

				break;
			}

			case NavegacionCommon.CONFIGURACION_IDIOMA_PAIS:
			{
				BeanConfiguracionIdiomaPais lbcip_bcip;
				lbcip_bcip = (BeanConfiguracionIdiomaPais)lfc_context.getApplication()
						                                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONFIGURACION_IDIOMA_PAIS,
						    BeanConfiguracionIdiomaPais.class
						);
				lbcip_bcip.cargarDataConstanteMoneda(false);

				break;
			}

			case NavegacionCommon.TARIFA_FIJA:
			{
				BeanTarifaFija lbpa_bpa;
				lbpa_bpa = (BeanTarifaFija)lfc_context.getApplication()
						                                  .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TARIFA_FIJA, BeanTarifaFija.class
						);

				lbpa_bpa.clear();

				break;
			}

			case NavegacionCommon.TIPO_ACTO_CONDICION:
			{
				BeanTipoActoCondicion lbpa_bpa;
				lbpa_bpa = (BeanTipoActoCondicion)lfc_context.getApplication()
						                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_ACTO_CONDICION, BeanTipoActoCondicion.class
						);

				lbpa_bpa.clear();

				break;
			}

			case NavegacionCommon.TIPO_ACTO_EJECUTORIA:
			{
				BeanTipoActoEjecutoria lbpa_bpa;
				lbpa_bpa = (BeanTipoActoEjecutoria)lfc_context.getApplication()
						                                          .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_ACTO_EJECUTORIA, BeanTipoActoEjecutoria.class
						);

				lbpa_bpa.clear();

				break;
			}

			case NavegacionCommon.TARIFAS_PARA_ALERTAS:
			{
				BeanTarifasParaAlertas lbtpa_tpa;
				lbtpa_tpa = (BeanTarifasParaAlertas)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TARIFAS_PARA_ALERTAS, BeanTarifasParaAlertas.class
						);

				lbtpa_tpa.clear();
				lbtpa_tpa.cargarData();

				break;
			}

			case NavegacionCommon.ADMINISTRACION_COMUNIDADES_ETNICAS:
			{
				BeanComunidadesEtnicas lbce_ce;
				lbce_ce = (BeanComunidadesEtnicas)lfc_context.getApplication()
						                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_COMUNIDADES_ETNICAS, BeanComunidadesEtnicas.class
						);

				lbce_ce.clear();
				lbce_ce.cargarData();

				break;
			}

			case NavegacionCommon.CANAL_ORIGEN_SERVICIO:
			{
				BeanCanalOrigenServicio lbcos_cos;
				lbcos_cos = (BeanCanalOrigenServicio)lfc_context.getApplication()
						                                            .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CANAL_ORIGEN_SERVICIO, BeanCanalOrigenServicio.class
						);

				lbcos_cos.clear();

				break;
			}

			case NavegacionCommon.MEDIO_RECAUDO:
			{
				BeanMedioRecaudo lbmr_bmr;
				lbmr_bmr = (BeanMedioRecaudo)lfc_context.getApplication()
						                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_MEDIO_RECAUDO, BeanMedioRecaudo.class
						);

				lbmr_bmr.clear();

				break;
			}

			case NavegacionCommon.TIPO_AREA:
			{
				BeanTipoArea lbta_bpa;
				lbta_bpa = (BeanTipoArea)lfc_context.getApplication()
						                                .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_AREA, BeanTipoArea.class
						);

				lbta_bpa.clear();

				break;
			}

			case NavegacionCommon.PUNTO_RECAUDO:
			{
				BeanPuntoRecaudo lbpr_bpr;
				lbpr_bpr = (BeanPuntoRecaudo)lfc_context.getApplication()
						                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PUNTO_RECAUDO, BeanPuntoRecaudo.class
						);

				lbpr_bpr.clear();

				break;
			}

			case NavegacionCommon.MENSAJE_RESPUESTA:
			{
				BeanMensajeRespuesta lbmr_bmr;
				lbmr_bmr = (BeanMensajeRespuesta)lfc_context.getApplication()
						                                        .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_MENSAJE_RESPUESTA, BeanMensajeRespuesta.class
						);

				lbmr_bmr.clear();

				break;
			}

			case NavegacionCommon.TIPO_CANAL_ORIGEN:
			{
				BeanTipoCanalOrigen lbtco_btco;
				lbtco_btco = (BeanTipoCanalOrigen)lfc_context.getApplication()
						                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TIPO_CANAL_ORIGEN, BeanTipoCanalOrigen.class
						);

				lbtco_btco.clear();

				break;
			}

			case NavegacionCommon.TIPO_DOCUMENTO_PUBLICO:
				break;

			case NavegacionCommon.EJECUTAR_PROC_NOCTURNO:
				break;

			case NavegacionCommon.REASIGNAR_TURNOS:
			{
				BeanReasignarTurno lbrt_brt;
				lbrt_brt = (BeanReasignarTurno)lfc_context.getApplication()
						                                      .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REASIGNAR_TURNO, BeanReasignarTurno.class
						);
				lbrt_brt.iniciar();
				lbrt_brt.cargarConstanteTabla();
				lbrt_brt.cargarCirculos();

				break;
			}

			case NavegacionCommon.PROCESOS_AUTOMATICOS:
				break;

			case NavegacionCommon.RANGO_CUANTIA:
			{
				BeanRangoCuantia lbpa_bpa;
				lbpa_bpa = (BeanRangoCuantia)lfc_context.getApplication()
						                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_RANGO_CUANTIA, BeanRangoCuantia.class
						);

				lbpa_bpa.clear();

				break;
			}

			case NavegacionCommon.BITACORA_PROCESOS:
			{
				BeanBitacoraProcesos lbbp_bbp;
				lbbp_bbp = (BeanBitacoraProcesos)lfc_context.getApplication()
						                                        .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_BITACORA_PROCESOS, BeanBitacoraProcesos.class
						);

				lbbp_bbp.clear();

				break;
			}

			case NavegacionCommon.TIPO_INTEGRACION_GOBERNACION:
				break;

			case NavegacionCommon.GOBERNACION:
				break;

			case NavegacionCommon.FESTIVO_NACIONAL:
				break;

			case NavegacionCommon.ESTADO_ACTIVIDAD:
				break;

			case NavegacionCommon.INSTANCIA_CONSULTA:
				break;

			case NavegacionCommon.LIBRO_ANTIGUO_SISTEMA:
				break;

			case NavegacionCommon.BANDEJA_MOD_DATOS_BASICOS:
			{
				BeanBandejaConfrontacion lb_beanBandejaConfrontacion;

				lb_beanBandejaConfrontacion = (BeanBandejaConfrontacion)lfc_context.getApplication()
						                                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_BANDEJA_CONFRONTACION, BeanBandejaConfrontacion.class
						);

				lb_beanBandejaConfrontacion.setIdTurnoConsulta(null);
				lb_beanBandejaConfrontacion.setNirConsulta(null);
				lb_beanBandejaConfrontacion.generarDatosTramiteCantidad();
				lb_beanBandejaConfrontacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_CONFRONTACION_CORRECTIVA, false);

				break;
			}

			case NavegacionCommon.TURNOS_DIGITADOR_MASIVO:
			{
				BeanDigitadorMasivo lbdm_bean;

				lbdm_bean = (BeanDigitadorMasivo)lfc_context.getApplication()
						                                        .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DIGITADOR_MASIVO, BeanDigitadorMasivo.class
						);

				lbdm_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO));
				lbdm_bean.clear();
				lbdm_bean.generarData();
				lbdm_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO, false);

				break;
			}

			case NavegacionCommon.TURNOS_RESTITUCION:
			{
				BeanTurnosRecepcion lbtr_bean;

				lbtr_bean = (BeanTurnosRecepcion)lfc_context.getApplication()
						                                        .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TURNOS_RECEPCION, BeanTurnosRecepcion.class
						);

				lbtr_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS));
				lbtr_bean.clear();
				lbtr_bean.generarData();

				break;
			}

			case NavegacionCommon.DESISTIMIENTO:
			{
				BeanDesistimiento lbd_bean;

				lbd_bean = (BeanDesistimiento)lfc_context.getApplication()
						                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DESISTIMIENTO, BeanDesistimiento.class
						);

				lbd_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO));
				lbd_bean.clear();
				lbd_bean.generarData();

				break;
			}

			case NavegacionCommon.ANALISIS_CORRECCION:
			{
				BeanCorreccion lbc_bean;

				lbc_bean = (BeanCorreccion)lfc_context.getApplication()
						                                  .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CORRECCION, BeanCorreccion.class
						);

				lbc_bean.clear();
				lbc_bean.generarDatosTramiteCantidad();
				lbc_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_CORRECCIONES, false);

				break;
			}

			case NavegacionCommon.ANALISIS_TRASLADOS:
			{
				BeanTraslados lbt_bean;

				lbt_bean = (BeanTraslados)lfc_context.getApplication()
						                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TRASLADOS, BeanTraslados.class
						);

				lbt_bean.clear();
				lbt_bean.limpiarBandeja();
				lbt_bean.setVieneDeAnalisisTraslados(true);
				lbt_bean.setIdEtapa(StringUtils.getString(EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS));
				lbt_bean.generarDatosTramiteCantidad();
				lbt_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS, false);

				break;
			}

			case NavegacionCommon.ACTO_ADMINISTRATIVO_BANDEJA:
			{
				BeanActoAdministrativos lbt_bean;

				lbt_bean = (BeanActoAdministrativos)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ACTO_ADMINISTRATIVOS, BeanActoAdministrativos.class
						);

				lbt_bean.clear();
				lbt_bean.limpiarBandeja();
				lbt_bean.setVieneDeActoAdministrativoDD(true);
				lbt_bean.setIdEtapa(StringUtils.getString(EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO));
				lbt_bean.generarDatosTramiteCantidad();
				lbt_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO, false);

				as_pagina = NavegacionCommon.ACTO_ADMINISTRATIVO_BANDEJA;

				break;
			}

			case NavegacionCommon.PUBLICACION_RESOLUCION_PLANEACION:
			{
				BeanTraslados lbt_bean;

				lbt_bean = (BeanTraslados)lfc_context.getApplication()
						                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TRASLADOS, BeanTraslados.class
						);

				lbt_bean.clear();
				lbt_bean.limpiarBandeja();
				lbt_bean.setVieneDePublicacionResolucionPlaneacion(true);
				lbt_bean.setIdEtapa(
				    StringUtils.getString(EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION)
				);
				lbt_bean.generarDatosTramiteCantidad();
				lbt_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION, false);

				as_pagina = NavegacionCommon.ANALISIS_TRASLADOS;

				break;
			}

			case NavegacionCommon.RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS:
			{
				BeanResponsableActuacionesAdmin lbc_bean;

				lbc_bean = (BeanResponsableActuacionesAdmin)obtenerInstanciaBean(
					    BeanResponsableActuacionesAdmin.class, BeanNameCommon.BEAN_RESPONSABLE_ACTUACIONES_ADMIN
					);

				lbc_bean.clear();
				lbc_bean.generarDatosTramiteCantidad();
				lbc_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS, false);

				break;
			}

			case NavegacionCommon.SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS:
			{
				BeanSustanciadorActuacionesAdmin lbc_bean;

				lbc_bean = (BeanSustanciadorActuacionesAdmin)obtenerInstanciaBean(
					    BeanSustanciadorActuacionesAdmin.class, BeanNameCommon.BEAN_SUSTANCIADOR_ACTUACIONES_ADMIN
					);

				lbc_bean.clear();
				lbc_bean.generarDatosTramiteCantidad();
				lbc_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS, false);

				break;
			}

			case NavegacionCommon.BANDEJA_RESOLUCION_RECURSOS:
			{
				BeanBandejaResolucionRecursos lbbrr_bean;

				lbbrr_bean = (BeanBandejaResolucionRecursos)obtenerInstanciaBean(
					    BeanBandejaResolucionRecursos.class, BeanNameCommon.BEAN_BANDEJA_RESOLUCION_RECURSOS
					);

				lbbrr_bean.clear();
				lbbrr_bean.setVieneDeAnalisisSegundaInstancia(false);
				lbbrr_bean.generarDatosTramiteCantidad();
				lbbrr_bean.generarGraficoDeTorta(
				    EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS, false
				);

				break;
			}

			case NavegacionCommon.ANALISIS_ABOGADO_SUSTANCIADOR:
			{
				BeanBandejaResolucionRecursos lbbrr_bean;

				lbbrr_bean = (BeanBandejaResolucionRecursos)obtenerInstanciaBean(
					    BeanBandejaResolucionRecursos.class, BeanNameCommon.BEAN_BANDEJA_RESOLUCION_RECURSOS
					);

				lbbrr_bean.clear();
				lbbrr_bean.setEtapaABuscar(EtapaCommon.ID_ETAPA_430);
				lbbrr_bean.setVieneDeAnalisisSegundaInstancia(true);
				lbbrr_bean.generarDatosTramiteCantidadByEtapa();
				lbbrr_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_430, false);
				as_pagina = NavegacionCommon.BANDEJA_RESOLUCION_RECURSOS;

				break;
			}

			case NavegacionCommon.BANDEJA_RECURSOS:
			{
				BeanRecursos lbr_bean;

				lbr_bean = (BeanRecursos)obtenerInstanciaBean(BeanRecursos.class, BeanNameCommon.BEAN_RECURSOS);

				lbr_bean.clear();
				lbr_bean.generarDatosTramiteCantidad();
				lbr_bean.setVieneDeCoordinadorSegundaInstancia(false);
				lbr_bean.generarGraficoDeTorta(
				    EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS, false
				);

				break;
			}

			case NavegacionCommon.COORDINADOR_SEGUNDA_INSTANCIA:
			{
				BeanRecursos lbr_bean;

				lbr_bean = (BeanRecursos)obtenerInstanciaBean(BeanRecursos.class, BeanNameCommon.BEAN_RECURSOS);

				lbr_bean.clear();
				lbr_bean.setEtapaABuscar(EtapaCommon.ID_ETAPA_460);
				lbr_bean.generarDatosTramiteCantidadByEtapa();
				lbr_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_460, false);
				lbr_bean.setVieneDeCoordinadorSegundaInstancia(true);
				as_pagina = NavegacionCommon.BANDEJA_RECURSOS;

				break;
			}

			case NavegacionCommon.ANALISTA_DE_COPIAS:
			{
				BeanAnalistaCopias lbc_bean;

				lbc_bean = (BeanAnalistaCopias)lfc_context.getApplication()
						                                      .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ANALISTA_COPIAS, BeanAnalistaCopias.class
						);

				lbc_bean.clear();
				lbc_bean.generarDatosTramiteCantidad();

				break;
			}

			case NavegacionCommon.SOLICITUD_APOYO_NACIONAL:
			{
				BeanSolicitudApoyoNacional lbsapn_bean;

				lbsapn_bean = (BeanSolicitudApoyoNacional)lfc_context.getApplication()
						                                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_SOLICITUDA_APOYO_NACIONAL, BeanSolicitudApoyoNacional.class
						);

				lbsapn_bean.clear();

				break;
			}

			case NavegacionCommon.RESPONSABLE_TESORERIA:
			{
				BeanAprobacion lb_beanAprobacion;

				lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeResponsableTesoreria(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_380, false);
				}

				as_pagina = NavegacionCommon.BANDEJA_ENTRADA;

				break;
			}

			case NavegacionCommon.ANALISTA_DE_DEVOLUCIONES:
			{
				BeanAnalistaDeDevolucion lbc_bean;

				lbc_bean = (BeanAnalistaDeDevolucion)lfc_context.getApplication()
						                                            .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ANALISTA_DE_DEVOLUCION, BeanAnalistaDeDevolucion.class
						);

				lbc_bean.clear();
				lbc_bean.generarDatosTramiteCantidad();

				break;
			}

			case NavegacionCommon.REIMPRESION:
			{
				BeanReimpresion lbr_bean;

				lbr_bean = (BeanReimpresion)lfc_context.getApplication()
						                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REIMPRESION, BeanReimpresion.class
						);

				lbr_bean.clean();

				break;
			}

			case NavegacionCommon.REIMPRESION_RECIBOS:
			{
				BeanReimpresionRecibos lbc_bean;

				lbc_bean = (BeanReimpresionRecibos)lfc_context.getApplication()
						                                          .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REIMPRESION_RECIBOS, BeanReimpresionRecibos.class
						);

				lbc_bean.clean();

				break;
			}

			case NavegacionCommon.RESPONSABLE_CORRECCION:
			{
				BeanResponsableCorreccion lbrc_bean;

				lbrc_bean = (BeanResponsableCorreccion)lfc_context.getApplication()
						                                              .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_RESPONSABLE_CORRECCION, BeanResponsableCorreccion.class
						);

				lbrc_bean.clear();
				lbrc_bean.generarDatosTramiteCantidad();
				lbrc_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES, false);

				break;
			}

			case NavegacionCommon.SOLICITUD_VISITAS:
			{
				BeanSolicitudVisitas lbsv_bean;

				lbsv_bean = (BeanSolicitudVisitas)obtenerInstanciaBean(
					    BeanSolicitudVisitas.class, BeanNameCommon.BEAN_SOLICITUD_VISITAS
					);

				if(lbsv_bean != null)
				{
					lbsv_bean.clean();
					lbsv_bean.cargarDependencias();
				}
			}

			default:
				break;
		}

		if(StringUtils.isValidString(as_pagina))
			setUrlCatedra(
			    irr_referenceRemote.findUrlCatedra(as_pagina, getUserId(), getRemoteIpAddress(), getLocalIpAddress())
			);

		return as_pagina;
	}

	/**
	 * Hace un reset a las variables de instancia de la clase y las prepara para nuevos datos
	 *
	 * @return Objeto bean registro inicializado
	 * @throws B2BException Si ocurre un error en el llamado a los bean
	 */
	private BeanRegistro inicializarBeanRegistro()
	    throws B2BException
	{
		BeanRegistro  lb_beanRegistro;
		BeanDireccion lb_beanDireccion;

		lb_beanDireccion     = (BeanDireccion)obtenerInstanciaBean(BeanDireccion.class, BeanNameCommon.BEAN_DIRECCION);
		lb_beanRegistro      = (BeanRegistro)obtenerInstanciaBean(BeanRegistro.class, BeanNameCommon.BEAN_REGISTRO);
		
		lb_beanRegistro.iniciar();
		lb_beanDireccion.limpiarBeanDireccion();
		lb_beanDireccion.setForm(BeanRegistro.is_idForm);
		lb_beanRegistro.limpiarRegistro();
		lb_beanRegistro.setMostrarLimpiarDatos(true);
		lb_beanRegistro.cargarMediosNotCom(false);
		lb_beanRegistro.cargarMediosNotComInter(false);
		lb_beanRegistro.esconderPanels();

		return lb_beanRegistro;
	}

	/**
	 * Obtiene los item que componen el menú
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

	/**
	 * @return Retorna el valor de la propiedad is_urlCatedra
	 */
	public String getUrlCatedra()
	{
		if(!StringUtils.isValidString(is_urlCatedra))
			is_urlCatedra = "www.supernotariado.gov.co/";

		return is_urlCatedra;
	}

	/**
	 * @param Modifica el valor de la propiedad is_urlCatedra por is_urlCatedra
	 */
	public void setUrlCatedra(String as_urlCatedra)
	{
		is_urlCatedra = as_urlCatedra;
	}

	/**
	 * @return Retorna el valor de la propiedad imm_menuAyuda
	 */
	public MenuModel getMenuAyuda()
	{
		return imm_menuAyuda;
	}

	/**
	 * @param Modifica el valor de la propiedad imm_menuAyuda por amm_menuModel
	 */
	public void setMenuAyuda(MenuModel amm_menuModel)
	{
		imm_menuAyuda = amm_menuModel;
	}

	/**
	 * Método de redireccionamiento hacia catédra
	 * @throws IOException
	 */
	public void irACatedra()
	    throws IOException
	{
		StringBuilder lsb_command;

		lsb_command = new StringBuilder("window.open('");

		lsb_command.append("https://" + getUrlCatedra());
		lsb_command.append("');");

		PrimeFaces.current().executeScript(lsb_command.toString());
	}
}
