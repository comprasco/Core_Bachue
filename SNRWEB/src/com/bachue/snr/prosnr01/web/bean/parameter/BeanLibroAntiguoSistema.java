package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;

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
 * Clase que contiene todos las propiedades y acciones de BeanLibroAntiguoSistema.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanLibroAntiguoSistema")
@SessionScoped
public class BeanLibroAntiguoSistema extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7479832102894107809L;

	/** Propiedad id parametros. */
	private LibroAntiguoSistema id_parametros;

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
	 * Modifica el valor de parametros.
	 *
	 * @param ac_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(LibroAntiguoSistema ac_c)
	{
		id_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public LibroAntiguoSistema getParametros()
	{
		if(id_parametros == null)
			id_parametros = new LibroAntiguoSistema();

		return id_parametros;
	}

/**
 * Metodo para  cambiar estado con el fin de saber si se desea insertar un libro antiguo sistema.
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
 * Metodo para traer los registros del libro antiguo sistema que coincida con un id_libroAntiguoSistema
 * especifico.
 */
	public void consultaDetallada()
	{
		try
		{
			LibroAntiguoSistema ld_parametros;
			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				long ll_idLibroAntiguoSistema;

				ll_idLibroAntiguoSistema = FacesUtils.getLongFacesParameter("idLibroAntiguoSistema");

				if(NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idLibroAntiguoSistema)))
				{
					ld_parametros.setIdLibroAntiguoSistema(ll_idLibroAntiguoSistema);
					setParametros(ipr_parameterRemote.findLibroAntiguoSistemaById(ld_parametros));
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
	 * Metodo para traer todos los registros de la tabla SDB_PGN_LIBRO_ANT_SISTEMA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<LibroAntiguoSistema> findAllLibroAntiguoSistema()
	{
		Collection<LibroAntiguoSistema> lclas_libros;
		lclas_libros = null;

		try
		{
			lclas_libros = ipr_parameterRemote.findAllLibroAntiguoSistema(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lclas_libros;
	}

	/**
	 * Metodo para salvar un Libro antiguo sistema ya sea para modificar o insertar registros.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String              ls_result;
		LibroAntiguoSistema llas_parametros;
		FacesContext        lfc_context;
		boolean             lb_focus;

		ls_result           = null;
		llas_parametros     = getParametros();
		lfc_context         = FacesContext.getCurrentInstance();
		lb_focus            = true;

		{
			String ls_datoValidar;
			long   ls_datoParaValidar;

			{
				ls_datoParaValidar     = llas_parametros.getIdLibroAntiguoSistema();
				lb_focus               = validateStyles(
					    ":fLibroAntiguoSistemaDetalle:idLibroAntiguoSistema", lfc_context,
					    NumericUtils.getLongWrapper(ls_datoParaValidar), lb_focus
					);
			}

			{
				ls_datoValidar     = llas_parametros.getNombre();
				lb_focus           = validateStyles(
					    ":fLibroAntiguoSistemaDetalle:idNombre", lfc_context, ls_datoValidar, lb_focus
					);
			}

			{
				ls_datoValidar     = llas_parametros.getActivo();

				lb_focus = validateStyles(
					    ":fLibroAntiguoSistemaDetalle:idActivo", lfc_context, ls_datoValidar, lb_focus
					);
			}

			PrimeFaces.current().ajax().update("fLibroAntiguoSistemaDetalle");
		}

		try
		{
			llas_parametros.setIdUsuarioCreacion(getUserId());
			llas_parametros.setIdUsuarioModificacion(getUserId());

			ipr_parameterRemote.salvarLibroAntiguoSistema(
			    llas_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
			);
			
			getBeanReference().setLibroAntiguoSistema(null);

			setParametros(null);

			ls_result = NavegacionCommon.LIBRO_ANTIGUO_SISTEMA;

			if(isInsertar())
			{
				addMessage(MessagesKeys.INSERCION_EXITOSA);
				PrimeFaces.current().ajax().update("fLibroAntiguoSistema");
				PrimeFaces.current().ajax().update("fLibroAntiguoSistemaDetalle");
			}
			else
			{
				addMessage(MessagesKeys.MODIFICACION_EXITOSA);
				PrimeFaces.current().ajax().update("fLibroAntiguoSistema");
				PrimeFaces.current().ajax().update("fLibroAntiguoSistemaDetalle");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
