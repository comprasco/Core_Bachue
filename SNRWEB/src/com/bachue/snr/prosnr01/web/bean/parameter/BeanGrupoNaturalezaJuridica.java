package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Grupo Naturaleza Juridica.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanGrupoNaturalezaJuridica")
@SessionScoped
public class BeanGrupoNaturalezaJuridica extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2141411476531268664L;

	/** Propiedad icgnj datos auditoria. */
	private Collection<GrupoNaturalezaJuridica> icgnj_datosAuditoria;

	/** Propiedad ignj grupo naturaleza juridica. */
	private GrupoNaturalezaJuridica ignj_grupoNaturalezaJuridica;

	/** Propiedad ignj parametros. */
	private GrupoNaturalezaJuridica ignj_parametros;

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
	 * Modifica el valor de datos auditoria.
	 *
	 * @param acgnj_cgnj asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<GrupoNaturalezaJuridica> acgnj_cgnj)
	{
		icgnj_datosAuditoria = acgnj_cgnj;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<GrupoNaturalezaJuridica> getDatosAuditoria()
	{
		return icgnj_datosAuditoria;
	}

	/**
	 * Modifica el valor de grupo naturaleza juridica.
	 *
	 * @param agnj_gnj asigna el valor a la propiedad grupo naturaleza juridica
	 */
	public void setGrupoNaturalezaJuridica(GrupoNaturalezaJuridica agnj_gnj)
	{
		ignj_grupoNaturalezaJuridica = agnj_gnj;
	}

	/**
	 * Retorna el valor de grupo naturaleza juridica.
	 *
	 * @return el valor de grupo naturaleza juridica
	 */
	public GrupoNaturalezaJuridica getGrupoNaturalezaJuridica()
	{
		return ignj_grupoNaturalezaJuridica;
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
	 * @param agnj_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(GrupoNaturalezaJuridica agnj_param)
	{
		ignj_parametros = agnj_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public GrupoNaturalezaJuridica getParametros()
	{
		if(ignj_parametros == null)
			ignj_parametros = new GrupoNaturalezaJuridica();

		return ignj_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Grupo Naturaleza Juridica.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setGrupoNaturalezaJuridica(new GrupoNaturalezaJuridica());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarGrupoNaturalezaJuridica");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Metodo que se encarga de reiniciar variables.
	 */
	public void clear()
	{
		setParametros(null);
		setInsertar(false);
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @param agnj_gnj correspondiente al valor del tipo de objeto GrupoNaturalezaJuridica
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(GrupoNaturalezaJuridica agnj_gnj)
	    throws B2BException
	{
		if(agnj_gnj != null)
		{
			GrupoNaturalezaJuridica lgnj_dato;

			lgnj_dato     = null;

			lgnj_dato = ipr_parameterRemote.findGrupoNaturalezaJuridicaById(agnj_gnj);

			if(lgnj_dato != null)
			{
				Collection<GrupoNaturalezaJuridica> lcgnj_gnj;
				lcgnj_gnj = new ArrayList<GrupoNaturalezaJuridica>();

				lcgnj_gnj.add(lgnj_dato);
				setGrupoNaturalezaJuridica(lgnj_dato);

				setDatosAuditoria(lcgnj_gnj);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<GrupoNaturalezaJuridica> findAllGrupoNaturalezaJuridica()
	{
		Collection<GrupoNaturalezaJuridica> lcgnj_ta;
		lcgnj_ta = null;

		try
		{
			lcgnj_ta = ipr_parameterRemote.findAllGrupoNaturalezaJuridica();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcgnj_ta;
	}

	/**
	 * Método para salvar la inserción o actualización de la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @return devuelve el valor de String
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
			GrupoNaturalezaJuridica lgnj_parametros;

			lgnj_parametros = getGrupoNaturalezaJuridica();

			if(lgnj_parametros != null)
			{
				{
					String ls_descripcion;
					ls_descripcion     = lgnj_parametros.getNombre();

					lb_focus = validateStyles(
						    ":fGrupoNaturalezaJuridicaDetalle:idNombre", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo     = lgnj_parametros.getActivo();

					lb_focus = validateStyles(
						    ":fGrupoNaturalezaJuridicaDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarGrupoNaturalezaJuridica(
				    lgnj_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setGrupoNaturalezaJuridica(null);

				ls_result = NavegacionCommon.GRUPO_NATURALEZA_JURIDICA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fGrupoNaturalezaJuridicaDetalle");
		}

		return ls_result;
	}
}
