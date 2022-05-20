package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.col.InteresadoNaturalGenero;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanInteresadoNaturalGenero.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanInteresadoNaturalGenero")
@SessionScoped
public class BeanInteresadoNaturalGenero extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8355320736310160275L;

	/** Propiedad icing datos auditoria. */
	private Collection<InteresadoNaturalGenero> icing_datosAuditoria;

	/** Propiedad iing parametros. */
	private InteresadoNaturalGenero iing_parametros;

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
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<InteresadoNaturalGenero> datosAuditoria)
	{
		icing_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<InteresadoNaturalGenero> getDatosAuditoria()
	{
		return icing_datosAuditoria;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param aing_ing asigna el valor a la propiedad parametros
	 */
	public void setParametros(InteresadoNaturalGenero aing_ing)
	{
		iing_parametros = aing_ing;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public InteresadoNaturalGenero getParametros()
	{
		if(iing_parametros == null)
			iing_parametros = new InteresadoNaturalGenero();

		return iing_parametros;
	}

	/**
	 * Metodo que indica si se desea insertar un interesado natural genero.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		if(lb_parametro != null)
		{
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
			setParametros(null);
		}
	}

	/**
	 * Metodo que se encarga de reinicair variables.
	 */
	public void clear()
	{
		setParametros(null);
		setInsertar(false);
	}

	/**
	 * Metodo para traer de la base de datos todos los registros que correspondan a un ID_NATURAL_GENERO
	 * específico.
	 */
	public void consultaDetallada()
	{
		try
		{
			InteresadoNaturalGenero ltp_parametros;
			ltp_parametros = getParametros();

			if(ltp_parametros != null)
			{
				String ls_idInteresadoNaturalGenero;
				ls_idInteresadoNaturalGenero = FacesUtils.getStringFacesParameter("idNaturalGenero");

				if(StringUtils.isValidString(ls_idInteresadoNaturalGenero))
				{
					Collection<InteresadoNaturalGenero> lcing_cing;

					lcing_cing = new ArrayList<InteresadoNaturalGenero>();

					ltp_parametros.setIdNaturalGenero(ls_idInteresadoNaturalGenero);

					setParametros(ipr_parameterRemote.findInteresadoNaturalGeneroById(ltp_parametros));
					lcing_cing.add(getParametros());

					setDatosAuditoria(lcing_cing);
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
	 * Metodo para traer de la base de datos todos los registros encontrados en la tabla
	 * SDB_COL_INTERESADO_NATURAL_GENERO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<InteresadoNaturalGenero> findAllInteresadoNaturalGenero()
	{
		Collection<InteresadoNaturalGenero> llp_datos;
		llp_datos = null;

		try
		{
			llp_datos = ipr_parameterRemote.findAllInteresadoNaturalGenero();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return llp_datos;
	}

	/**
	 * Metodo para salvar ya sea la modificación o la inserción.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String  ls_return;
		boolean lb_focus;
		ls_return     = NavegacionCommon.INTERESADO_NATURAL_GENERO;
		lb_focus      = true;

		try
		{
			InteresadoNaturalGenero ltp_parametros;
			FacesContext            lfc_context;

			ltp_parametros     = getParametros();
			lfc_context        = FacesContext.getCurrentInstance();

			if(ltp_parametros != null)
			{
				{
					String ls_idNaturalGenero;
					ls_idNaturalGenero     = ltp_parametros.getIdNaturalGenero();

					lb_focus = validateStyles(
						    ":fInteresadoNaturalGeneroDetalle:idNaturalGenero", lfc_context, ls_idNaturalGenero,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_idNaturalGenero))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_GENERO);
				}

				{
					String ls_ilicode;
					ls_ilicode     = ltp_parametros.getIlicode();

					lb_focus = validateStyles(
						    ":fInteresadoNaturalGeneroDetalle:idIlicode", lfc_context, ls_ilicode, lb_focus
						);

					if(!StringUtils.isValidString(ls_ilicode))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ILICODE);
				}

				{
					String ls_activo;
					ls_activo     = ltp_parametros.getActivo();

					lb_focus = validateStyles(
						    ":fInteresadoNaturalGeneroDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					String ls_descripcion;
					ls_descripcion     = ltp_parametros.getDescripcion();

					lb_focus = validateStyles(
						    ":fInteresadoNaturalGeneroDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				ipr_parameterRemote.salvarInteresadoNaturalGenero(
				    ltp_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				
				getBeanReference().setGeneros(null);

				setParametros(null);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}
}
