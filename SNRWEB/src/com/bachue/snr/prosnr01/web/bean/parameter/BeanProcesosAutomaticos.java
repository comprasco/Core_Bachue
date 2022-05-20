package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoAutomatico;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades de BeanProcesosAutomaticos.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanProcesosAutomaticos")
@SessionScoped
public class BeanProcesosAutomaticos extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5698642351073254265L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad id parametros. */
	private ProcesoAutomatico id_parametros;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de insertar.
	 *
	 * @param ab_b asigna el valor a la propiedad insertar
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param ac_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(ProcesoAutomatico ac_c)
	{
		id_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public ProcesoAutomatico getParametros()
	{
		if(id_parametros == null)
			id_parametros = new ProcesoAutomatico();

		return id_parametros;
	}

	/**
	 * Metodo para  cambiar estado con el fin de saber si se desea insertar un proceso automatico.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));

		setParametros(null);
	}

	/**
	 * Metodo para traer los registros del proceso automatico que coincida con un id_procesoautomaticos
	 * especifico.
	 */
	public void consultaDetallada()
	{
		try
		{
			ProcesoAutomatico ld_parametros;
			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				String ls_idProcesoAutomatico;

				ls_idProcesoAutomatico = FacesUtils.getStringFacesParameter("idProcesoAutomatico");

				if(StringUtils.isValidString(ls_idProcesoAutomatico))
				{
					ld_parametros.setIdProcesoAutomatico(ls_idProcesoAutomatico);
					setParametros(ipr_parameterRemote.findProcesoAutomaticoById(ld_parametros));
					setInsertar(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo para traer todos los registros de la tabla SDB_PGN_PROCESO_AUTOMATICO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<ProcesoAutomatico> findAllProcesosAutomaticos()
	{
		Collection<ProcesoAutomatico> lcd_constantes;
		lcd_constantes = null;

		try
		{
			lcd_constantes = ipr_parameterRemote.findAllProcesosAutomaticos(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcd_constantes;
	}

	/**
	 * Metodo para salvar un Proceso automático ya sea para modificar o insertar registros.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String       ls_result;
		FacesContext lfc_context;
		boolean      lb_focus;

		ls_result       = null;
		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			ProcesoAutomatico ld_parametros;

			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				{
					{
						String ls_nombreProceso;

						ls_nombreProceso     = ld_parametros.getNombreProceso();
						lb_focus             = validateStyles(
							    ":fProcesoAutomaticoDetalle:idnombreProceso", lfc_context, ls_nombreProceso, lb_focus
							);

						if(!StringUtils.isValidString(ls_nombreProceso))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_PROCESO);
					}

					{
						String ls_descripcion;

						ls_descripcion     = ld_parametros.getDescripcion();
						lb_focus           = validateStyles(
							    ":fProcesoAutomaticoDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
							);

						if(!StringUtils.isValidString(ls_descripcion))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
					}

					{
						String ls_nombreProcAlmacenado;

						ls_nombreProcAlmacenado     = ld_parametros.getNombreProcAlmacenado();
						lb_focus                    = validateStyles(
							    ":fProcesoAutomaticoDetalle:idNombreProc", lfc_context, ls_nombreProcAlmacenado,
							    lb_focus
							);

						if(!StringUtils.isValidString(ls_nombreProcAlmacenado))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_PROC_ALMACENADO);
					}

					{
						String ls_estado;

						ls_estado     = ld_parametros.getEstado();
						lb_focus      = validateStyles(
							    ":fProcesoAutomaticoDetalle:idEstado", lfc_context, ls_estado, lb_focus
							);

						if(!StringUtils.isValidString(ls_estado))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO);
					}
				}

				ld_parametros.setIdUsuarioCreacion(getUserId());
				ld_parametros.setIdUsuarioModificacion(getUserId());

				ipr_parameterRemote.salvarProcesoAutomatico(
				    ld_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);
				
				getBeanReference().setProcesosAutomaticos(null);

				setParametros(null);

				ls_result = NavegacionCommon.PROCESOS_AUTOMATICOS;

				if(isInsertar())
				{
					addMessage(MessagesKeys.INSERCION_EXITOSA);
					PrimeFaces.current().ajax().update("fProcesoAutomatico");
					PrimeFaces.current().ajax().update("fProcesoAutomaticoDetalle");
				}
				else
				{
					addMessage(MessagesKeys.MODIFICACION_EXITOSA);
					PrimeFaces.current().ajax().update("fProcesoAutomatico");
					PrimeFaces.current().ajax().update("fProcesoAutomaticoDetalle");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fProcesoAutomaticoDetalle");
		}

		return ls_result;
	}
}
