package com.bachue.snr.prosnr01.web.bean.alertaTierras;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Componente;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCorreccionAlertaTierras.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanCorreccionAlertaTierras")
@SessionScoped
public class BeanCorreccionAlertaTierras extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6981209181370888740L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCorreccionAlertaTierras.class);

	/** Propiedad icac datos auditoria. */
	private Collection<Componente> icc_componentes;

	/** Propiedad icac parametros. */
	private Componente icac_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad is id alerta. */
	private String is_idAlerta;

	/** Propiedad is texto corregir. */
	private String is_textoCorregir;
	private boolean ib_alertaEncontrada;

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean getAlertaEncontrada()
	{
		return ib_alertaEncontrada;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAlertaEncontrada(boolean ab_b)
	{
		ib_alertaEncontrada = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Componente> getComponentes()
	{
		return icc_componentes;
	}

	/**
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setComponentes(Collection<Componente> ac_c)
	{
		icc_componentes = ac_c;
	}

	/**
	 * @param parametros asigna el valor a la propiedad
	 */
	public void setParametros(Componente parametros)
	{
		icac_parametros = parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Componente getParametros()
	{
		if(icac_parametros == null)
			icac_parametros = new Componente();

		return icac_parametros;
	}

	/**
	* Modifica el valor de id turno reimpresion.
	*
	* @param as_s
	*                 asigna el valor a la propiedad id turno reimpresion
	*/
	public void setIdAlerta(String as_s)
	{
		is_idAlerta = as_s;
	}

	/**
	* Retorna el valor de id turno reimpresion.
	*
	* @return el valor de id turno reimpresion
	*/
	public String getIdAlerta()
	{
		return is_idAlerta;
	}

	/**
	* Modifica el valor de id turno reimpresion.
	*
	* @param as_s
	*                 asigna el valor a la propiedad id turno reimpresion
	*/
	public void setTextoCorregir(String as_s)
	{
		is_textoCorregir = as_s;
	}

	/**
	* Retorna el valor de id turno reimpresion.
	*
	* @return el valor de id turno reimpresion
	*/
	public String getTextoCorregir()
	{
		return is_textoCorregir;
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_AUT_COMPONENTE.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultaDetallada()
	    throws B2BException
	{
		try
		{
			String ls_idAlerta;
			String ls_textoCorregir;

			ls_idAlerta          = getIdAlerta();
			ls_textoCorregir     = getTextoCorregir();

			if(StringUtils.isValidString(ls_idAlerta) && StringUtils.isValidString(ls_textoCorregir))
			{
				Componente lac_datos;
				Componente lc_busqueda;

				lac_datos = new Componente();
				lac_datos.setIdComponente(ls_idAlerta);

				lc_busqueda = ipr_parameterRemote.findAllAdministracionComponentesById(lac_datos);

				if(lc_busqueda != null)
				{
					Collection<Componente> lcc_componente;

					lcc_componente = new ArrayList<Componente>();

					lcc_componente.add(lc_busqueda);

					setComponentes(lcc_componente);
					setAlertaEncontrada(true);
				}
			}
			else if(!StringUtils.isValidString(ls_idAlerta) && !StringUtils.isValidString(ls_textoCorregir))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);

			PrimeFaces.current().ajax().update("fOpcionDetalle:globalMsg");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fOpcionDetalle:globalMsg");
		}
	}

	/**
	 * Método para salvar la inserción o actualización
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
			Componente lac_administracionComponentes;

			lac_administracionComponentes = getParametros();

			if(lac_administracionComponentes != null)
			{
				{
					String ls_nombre;

					ls_nombre     = lac_administracionComponentes.getNombre();

					lb_focus = validateStyles(
						    ":fAdministracionComponentesDetalle:nombre", lfc_context, ls_nombre, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;

					ls_activo     = lac_administracionComponentes.getActivo();

					lb_focus = validateStyles(
						    ":fAdministracionComponentesDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					Date ld_fechaDesde;

					ld_fechaDesde     = lac_administracionComponentes.getFechaDesde();

					lb_focus = validateStyles(
						    ":fAdministracionComponentesDetalle:idFechaDesde", lfc_context, ld_fechaDesde, lb_focus
						);

					if(!(ld_fechaDesde != null))
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}

				ipr_parameterRemote.salvarAdministracionComponentes(
				    lac_administracionComponentes, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				
				getBeanReference().setRol(null);

				setParametros(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fAdministracionComponentesDetalle:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fAdministracionComponentesDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}
}
