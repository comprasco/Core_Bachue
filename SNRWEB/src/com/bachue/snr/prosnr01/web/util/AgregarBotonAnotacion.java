package com.bachue.snr.prosnr01.web.util;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanGestionIntervinientes;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanRegistroCalificacion;
import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;

import org.primefaces.component.commandbutton.CommandButton;

import java.io.IOException;
import java.io.Serializable;

import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;

import javax.faces.component.html.HtmlCommandButton;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;


/**
 * Clase que contiene todos las propiedades de Agregar Boton Anotacion.
 *
 * @author ccalderon
 */
public class AgregarBotonAnotacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6011869869003116897L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanRegistro.class);

	/** Propiedad bean registro calificacion. */
	private BeanRegistroCalificacion beanRegistroCalificacion;

	/**
	 * Retorna el valor de bean registro calificacion.
	 *
	 * @return el valor de bean registro calificacion
	 */
	public BeanRegistroCalificacion getBeanRegistroCalificacion()
	{
		if(beanRegistroCalificacion == null)
			beanRegistroCalificacion = new BeanRegistroCalificacion();

		return beanRegistroCalificacion;
	}

	/**
	 * Retorna el valor del objeto de CommandButton.
	 *
	 * @return devuelve el valor de CommandButton
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CommandButton agregarBotonModificacion()
	    throws B2BException
	{
		CommandButton lcb_commandButton;
		lcb_commandButton = new CommandButton();

		lcb_commandButton.setValue("Modificar interviniente");

		((UICommand)lcb_commandButton).addActionListener(
		    new ActionListener()
			{
				@Override
				public void processAction(ActionEvent aae_actionEvent)
				{
					if(aae_actionEvent != null)
					{
						String            ls_IdPanel;
						List<UIComponent> lui_ui;

						ls_IdPanel     = null;
						lui_ui = null;

						{
							Object lo_object;

							lo_object = aae_actionEvent.getSource();

							if((lo_object != null) && lo_object instanceof HtmlCommandButton)
							{
								HtmlCommandButton lhcb_htmlCommandButton;
								UIComponent       luc_component;

								lhcb_htmlCommandButton     = (HtmlCommandButton)lo_object;
								luc_component = lhcb_htmlCommandButton.getParent();

								if(luc_component != null)
								{
									lui_ui     = luc_component.getChildren();

									ls_IdPanel = luc_component.getId();
								}
							}
						}

						{
							BeanRegistroCalificacion lbrc_beanRegistroCalificacion;

							lbrc_beanRegistroCalificacion = getBeanRegistroCalificacion();

							if(lbrc_beanRegistroCalificacion != null)
							{
								try
								{
									lbrc_beanRegistroCalificacion.modificarAnotacion(lui_ui, ls_IdPanel, null);
								}
								catch(B2BException le_e)
								{
									clh_LOGGER.error("processAction", le_e);
								}

								ls_IdPanel = lbrc_beanRegistroCalificacion.generateId(ls_IdPanel);
							}
						}

						{
							BeanGestionIntervinientes lbrc_bean;
							FacesContext              lfc_context;

							lfc_context     = FacesUtils.getFacesContext();

							lbrc_bean = (BeanGestionIntervinientes)lfc_context.getApplication()
									                                              .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_GESTION_INTERVINIENTES,
									    BeanGestionIntervinientes.class
									);

							lbrc_bean.setIdAnotacionPredio(ls_IdPanel);
							lbrc_bean.setDatatable(true);

							try
							{
								lbrc_bean.limpiarIntervinientes();
							}
							catch(B2BException le_e)
							{
								clh_LOGGER.error("processAction", le_e);
							}

							lbrc_bean.setMostrarDesdeDetalle(true);
						}

						{
							ExternalContext lec_ec;

							lec_ec = FacesContext.getCurrentInstance().getExternalContext();

							try
							{
								lec_ec.redirect("gestionIntervinientes.jsf");
							}
							catch(IOException le_e)
							{
								clh_LOGGER.error("processAction", le_e);
							}
						}
					}
				}
			}
		);

		return lcb_commandButton;
	}
}
