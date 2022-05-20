package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Gobernacion;

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
 * Clase que contiene todos las propiedades y acciones de BeanGobernacion.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanGobernacion")
@SessionScoped
public class BeanGobernacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5698642351073254265L;

	/** Propiedad ig parametros. */
	private Gobernacion ig_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

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
	public void setParametros(Gobernacion ac_c)
	{
		ig_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Gobernacion getParametros()
	{
		if(ig_parametros == null)
			ig_parametros = new Gobernacion();

		return ig_parametros;
	}

/**
 * Metodo para  cambiar estado con el fin de saber si se desea insertar una gobernacion.
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
			Gobernacion ld_parametros;
			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				String ls_id_gobernacion;

				ls_id_gobernacion = FacesUtils.getStringFacesParameter("idGobernacion");

				if(StringUtils.isValidString(ls_id_gobernacion))
				{
					ld_parametros.setIdGobernacion(ls_id_gobernacion);
					setParametros(ipr_parameterRemote.findGobernacionById(ld_parametros));
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
	 * Metodo para traer todos los registros de la tabla SDB_PGN_GOBERNACION.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Gobernacion> findAllGobernacion()
	{
		Collection<Gobernacion> lcg_constantes;
		lcg_constantes = null;

		try
		{
			lcg_constantes = ipr_parameterRemote.findAllGobernacion(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcg_constantes;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			if(getParametros().getIdPais() != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(getParametros().getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Pais.
	 */
	public void pais()
	{
		getParametros().setIdDepartamento(null);
		findDepartamentos();
	}

	/**
	 * Metodo para salvar una gobernacion ya sea para modificar o insertar registros.
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
			Gobernacion lg_parametros;

			lg_parametros = getParametros();

			if(lg_parametros != null)
			{
				{
					String ls_pais;

					ls_pais      = lg_parametros.getIdPais();
					lb_focus     = validateStyles(":fGobernacionDetalle:idPais", lfc_context, ls_pais, lb_focus);

					if(!StringUtils.isValidString(ls_pais))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
				}

				{
					String ls_departamento;

					ls_departamento     = lg_parametros.getIdDepartamento();
					lb_focus            = validateStyles(
						    ":fGobernacionDetalle:idDepartamento", lfc_context, ls_departamento, lb_focus
						);

					if(!StringUtils.isValidString(ls_departamento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					String ls_nombreGobernacion;

					ls_nombreGobernacion     = lg_parametros.getNombreGobernacion();
					lb_focus                 = validateStyles(
						    ":fGobernacionDetalle:idNombre", lfc_context, ls_nombreGobernacion, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombreGobernacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_GOBERNACION);
				}

				{
					String ls_tipoIntegracionGobernacion;

					ls_tipoIntegracionGobernacion     = lg_parametros.getTipoIntegracionGobernacion();
					lb_focus                          = validateStyles(
						    ":fGobernacionDetalle:idTipoIntegracion", lfc_context, ls_tipoIntegracionGobernacion,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_tipoIntegracionGobernacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_INTEGRACION);
				}

				{
					String ls_activo;

					ls_activo     = lg_parametros.getActivo();
					lb_focus      = validateStyles(":fGobernacionDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarGobernacion(
				    lg_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);
			}

			setParametros(null);

			ls_result = NavegacionCommon.GOBERNACION;

			if(isInsertar())
			{
				addMessage(MessagesKeys.INSERCION_EXITOSA);
				PrimeFaces.current().ajax().update("fGobernacion");
				PrimeFaces.current().ajax().update("fGobernacionDetalle");
			}
			else
			{
				addMessage(MessagesKeys.MODIFICACION_EXITOSA);
				PrimeFaces.current().ajax().update("fGobernacion");
				PrimeFaces.current().ajax().update("fGobernacionDetalle");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fGobernacionDetalle");
		}

		return ls_result;
	}
}
