package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.OpcionUI;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanOpcion.
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanOpcion")
@SessionScoped
public class BeanOpcion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 556673593177994691L;

	/** Propiedad icp datos auditoria. */
	private Collection<Opcion> ictd_datosAuditoria;

	/** Propiedad io opcion. */
	private Opcion io_opcion;

	/** Propiedad ioui opcion etapa. */
	private OpcionUI ioui_opcionEtapa;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Opcion> actd_datosAuditoria)
	{
		ictd_datosAuditoria = actd_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Opcion> getDatosAuditoria()
	{
		return ictd_datosAuditoria;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_a)
	{
		ib_insertar = ab_a;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOpcion(Opcion atd_td)
	{
		io_opcion = atd_td;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Opcion getOpcion()
	{
		return io_opcion;
	}

	/**
	 * @param aoui_oui asigna el valor a la propiedad
	 */
	public void setOpcionEtapa(OpcionUI aoui_oui)
	{
		ioui_opcionEtapa = aoui_oui;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public OpcionUI getOpcionEtapa()
	{
		return ioui_opcionEtapa;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Campo
	 * Criterio Busqueda
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setOpcion((new Opcion()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarOpcion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para cargar etapas
	 */
	public void cargarEtapas()
	{
		try
		{
			Opcion lo_opcionEtapas;
			Opcion lo_opcion;

			lo_opcion           = getOpcion();
			lo_opcionEtapas     = null;

			if(lo_opcion != null)
			{
				lo_opcionEtapas = ipr_parameterRemote.findAllEtapaOpciones(lo_opcion);

				if(lo_opcionEtapas != null)
				{
					OpcionUI loui_opcionEtapa;

					loui_opcionEtapa = new OpcionUI(lo_opcionEtapas);

					setOpcionEtapa(loui_opcionEtapa);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setOpcion(null);
		setOpcionEtapa(null);
		setInsertar(false);
	}

	/**
	 *método de consulta detalada de Opcion por medio de su indicativo
	 * @param ao_opcion indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(Opcion ao_opcion)
	    throws B2BException
	{
		if(ao_opcion != null)
		{
			String ls_idOpcion;

			ls_idOpcion = ao_opcion.getIdOpcion();

			if(StringUtils.isValidString(ls_idOpcion))
			{
				ao_opcion = ipr_parameterRemote.findOpcionById(ls_idOpcion);

				if(ao_opcion != null)
				{
					Collection<Opcion> lctd_td;

					lctd_td = new ArrayList<Opcion>();

					lctd_td.add(ao_opcion);
					setOpcion(ao_opcion);

					setDatosAuditoria(lctd_td);
				}

				setInsertar(false);
				cargarEtapas();
			}
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_AUT_COMPONENTE
	 * @return
	 */
	public Collection<Componente> findAdministracionComponentes()
	{
		Collection<Componente> lcac_constantes;

		lcac_constantes = null;

		try
		{
			lcac_constantes = ipr_parameterRemote.findAllAdministracionComponentes();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcac_constantes;
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_AUT_OPCION
	 * @return
	 */
	public Collection<Opcion> findAllOpcion()
	{
		return findAllOpcion(false);
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_AUT_OPCION
	 * @return
	 */
	public Collection<Opcion> findAllOpcion(boolean ab_b)
	{
		Collection<Opcion> lco_o;

		lco_o = null;

		try
		{
			lco_o = ipr_parameterRemote.findAllOpcion(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lco_o;
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_AUT_OPCION que son opciones padre
	 * @return
	 */
	public Collection<Opcion> findAllOpcionPadre()
	{
		Collection<Opcion> lco_o;

		lco_o = null;

		try
		{
			lco_o = ipr_parameterRemote.findAllOpcion(false);

			if(CollectionUtils.isValidCollection(lco_o))
			{
				Collection<Opcion> lco_opcion;

				lco_opcion = new ArrayList<Opcion>();

				for(Opcion lo_temp : lco_o)
				{
					if(lo_temp != null)
					{
						String ls_menu;

						ls_menu = lo_temp.getTipo();

						if(
						    StringUtils.isValidString(ls_menu)
							    && (ls_menu.equalsIgnoreCase(IdentificadoresCommon.MENU)
							    || ls_menu.equalsIgnoreCase(IdentificadoresCommon.SUBMENU))
						)
							lco_opcion.add(lo_temp);
					}
				}

				lco_o = lco_opcion;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lco_o;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.OPCION;

		clear();

		return ls_return;
	}

	/**
	 * Método para salvar la inserción o actualización de Opcion
	 *
	 * @return
	 */
	public String salvar()
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			Opcion            lo_opcion;
			Collection<Etapa> lce_etapaOrigen;

			lo_opcion           = getOpcion();
			lce_etapaOrigen     = null;

			if(lo_opcion != null)
			{
				String ls_validador;

				{
					ls_validador     = lo_opcion.getOpcion();

					lb_focus = validateStyles(":fOpcionDetalle:idOpcion", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_OPCION);
				}

				{
					ls_validador     = lo_opcion.getDescripcion();

					lb_focus = validateStyles(":fOpcionDetalle:idDescripcion", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					ls_validador     = lo_opcion.getTipo();

					lb_focus = validateStyles(":fOpcionDetalle:idTipo", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.ERROR_DEBE_ELEGIR_TIPO);
				}

				{
					ls_validador     = lo_opcion.getIdOpcionPadre();

					lb_focus = validateStyles(":fOpcionDetalle:idOpcionPadre", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.ERROR_DEBE_ELEGIR_OPCION_PADRE);
				}

				{
					Date ld_fechaDesde;

					ld_fechaDesde     = lo_opcion.getFechaDesde();

					lb_focus = validateStyles(":fOpcionDetalle:idFechaDesde", lfc_context, ld_fechaDesde, lb_focus);

					if(ld_fechaDesde == null)
						throw new B2BException(ErrorKeys.ERROR_FECHA_DESDE);
				}

				{
					ls_validador     = lo_opcion.getIdComponente();

					lb_focus = validateStyles(":fOpcionDetalle:idComponente", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.ERROR_DEBE_ELEGIR_COMPONENTE);
				}

				{
					ls_validador     = lo_opcion.getActivo();

					lb_focus = validateStyles(":fOpcionDetalle:idActivo", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				if(isInsertar())
					ipr_parameterRemote.salvarOpcionInsertar(
					    lo_opcion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				else
				{
					OpcionUI lo_opcionEtapa;

					lo_opcionEtapa = getOpcionEtapa();

					if(lo_opcionEtapa != null)
						lce_etapaOrigen = lo_opcionEtapa.getEtapas().getTarget();

					ipr_parameterRemote.salvarOpcionModificar(
					    lo_opcion, lce_etapaOrigen, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				}

				getBeanReference().setOpcion(null);

				clear();

				ls_result = NavegacionCommon.OPCION;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				{
					ExternalContext lec_externalContext;

					lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fOpcionDetalle:globalMsg");
		}

		return ls_result;
	}
}
