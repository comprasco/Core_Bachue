package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.Coordenada;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
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
 * Clase para el manejo de la capa web para Coordenada
 * @author Sebastian Sanchez
 *
 */
@ManagedBean(name = "beanCoordenada")
@SessionScoped
public class BeanCoordenada extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8964868385904883092L;

	/** Propiedad icc datos auditoria. */
	private Collection<Coordenada> icc_datosAuditoria;

	/** Propiedad ic coordenada. */
	private Coordenada ic_coordenada;

	/** Propiedad ic parametros. */
	private Coordenada ic_parametros;

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
	 * Modifica el valor de coordenada.
	 *
	 * @param ac_c asigna el valor a la propiedad coordenada
	 */
	public void setCoordenada(Coordenada ac_c)
	{
		ic_coordenada = ac_c;
	}

	/**
	 * Retorna el valor de coordenada.
	 *
	 * @return el valor de coordenada
	 */
	public Coordenada getCoordenada()
	{
		return ic_coordenada;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param datosAuditoria asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<Coordenada> datosAuditoria)
	{
		icc_datosAuditoria = datosAuditoria;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<Coordenada> getDatosAuditoria()
	{
		return icc_datosAuditoria;
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
	 * @param ar_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(Coordenada ar_c)
	{
		ic_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Coordenada getParametros()
	{
		if(ic_parametros == null)
			ic_parametros = new Coordenada();

		return ic_parametros;
	}

/**
 * Método para cambiar estado para saber si se desea insertar una nueva
 * Coordenada
 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCoordenada(new Coordenada());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCoordenada");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

/**
 * Método para consultar en la base de datos la tabla SDB_PNG_COORDENADA
 * @param ac_c Objeto de la clase Coordenada
 * @throws B2BException
 */
	public void consultaDetallada(Coordenada ac_c)
	    throws B2BException
	{
		if(ac_c != null)
		{
			Coordenada lcad_datos;
			String     ls_s;

			ls_s           = ac_c.getIdCoordenada();
			lcad_datos     = null;

			if(StringUtils.isValidString(ls_s))
				lcad_datos = ipr_parameterRemote.findCoordenadaById(ls_s);

			if(lcad_datos != null)
			{
				Collection<Coordenada> lcc_c;
				lcc_c = new ArrayList<Coordenada>();

				lcc_c.add(lcad_datos);
				setCoordenada(lcad_datos);

				setDatosAuditoria(lcc_c);
			}

			setInsertar(false);
		}
	}

/**
 * Método para encontrar todos los registros de la tabla SDB_PNG_COORDENADA
 * que coincida con un id específico
 */
	public void findCoordenadaById()
	{
		try
		{
			Coordenada lc_parametros;

			lc_parametros = getCoordenada();

			if(lc_parametros != null)
			{
				String ls_s;

				ls_s = lc_parametros.getIdCoordenada();

				if(StringUtils.isValidString(ls_s))
				{
					lc_parametros.setIdCoordenada(lc_parametros.getIdCoordenada());
					setParametros(ipr_parameterRemote.findCoordenadaById(ls_s));
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
 *  Método para encontrar todos los registros de la tabla SDB_PNG_COORDENADA
 * @return
 */
	public Collection<Coordenada> findAllCoordenada()
	{
		Collection<Coordenada> lc_constantes;
		lc_constantes = null;

		try
		{
			lc_constantes = ipr_parameterRemote.findAllCoordenada();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lc_constantes;
	}

/**
 * Método para salvar la inserción o actualización
 * @return
 */
	public void salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			Coordenada lr_parametros;

			lr_parametros = getCoordenada();

			{
				String ls_idCoordenada;
				ls_idCoordenada     = lr_parametros.getIdCoordenada();

				lb_focus = validateStyles(":fCoordenadaDetalle:idCoordenada", lfc_context, ls_idCoordenada, lb_focus);

				if(!StringUtils.isValidString(ls_idCoordenada))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_COORDENADA);
			}

			{
				String ls_nombre;
				ls_nombre     = lr_parametros.getNombre();

				lb_focus = validateStyles(":fCoordenadaDetalle:nombre", lfc_context, ls_nombre, lb_focus);

				if(!StringUtils.isValidString(ls_nombre))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
			}

			{
				String ls_activo;
				ls_activo     = lr_parametros.getActivo();

				lb_focus = validateStyles(":fCoordenadaDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarCoordenada(
			    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
			
			{
				BeanReference lbr_beanReference;
				lbr_beanReference = getBeanReference();
				
				lbr_beanReference.setCoordenadaActivo(null);
				lbr_beanReference.setCoordenada(null);
			}
			
			

			setParametros(null);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fCoordenadaDetalle:globalMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCoordenadaDetalle:globalMsg");
		}
	}
}
