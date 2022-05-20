package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.LetraEje;

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
@ManagedBean(name = "beanLetraEje")
@SessionScoped
public class BeanLetraEje extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1691448649015723231L;

	/** Propiedad icle datos auditoria. */
	private Collection<LetraEje> icle_datosAuditoria;

	/** Propiedad ile letra eje. */
	private LetraEje ile_letraEje;

	/** Propiedad ile parametros. */
	private LetraEje ile_parametros;

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
	 * @param datosAuditoria asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<LetraEje> datosAuditoria)
	{
		icle_datosAuditoria = datosAuditoria;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<LetraEje> getDatosAuditoria()
	{
		return icle_datosAuditoria;
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
	 * Modifica el valor de letra eje.
	 *
	 * @param ale_le asigna el valor a la propiedad letra eje
	 */
	public void setLetraEje(LetraEje ale_le)
	{
		ile_letraEje = ale_le;
	}

	/**
	 * Retorna el valor de letra eje.
	 *
	 * @return el valor de letra eje
	 */
	public LetraEje getLetraEje()
	{
		return ile_letraEje;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param ale_le asigna el valor a la propiedad parametros
	 */
	public void setParametros(LetraEje ale_le)
	{
		ile_parametros = ale_le;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public LetraEje getParametros()
	{
		if(ile_parametros == null)
			ile_parametros = new LetraEje();

		return ile_parametros;
	}

/**
 * Método para cambiar estado para saber si se desea insertar una nueva
 * Tipo Eje
 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setLetraEje(new LetraEje());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarLetraEje");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

/**
 * Método para consultar en la base de datos la tabla SDB_PNG_LETRA_EJE
 * @param ale_le Objeto de la clase Letra Eje
 * @throws B2BException
 */
	public void consultaDetallada(LetraEje ale_le)
	    throws B2BException
	{
		if(ale_le != null)
		{
			LetraEje lcad_datos;
			String   ls_s;

			ls_s           = ale_le.getIdLetra();
			lcad_datos     = null;

			if(StringUtils.isValidString(ls_s))
				lcad_datos = ipr_parameterRemote.findLetraEjeById(ls_s);

			if(lcad_datos != null)
			{
				Collection<LetraEje> lcle_le;
				lcle_le = new ArrayList<LetraEje>();

				lcle_le.add(lcad_datos);
				setLetraEje(lcad_datos);

				setDatosAuditoria(lcle_le);
			}

			setInsertar(false);
		}
	}

/**
 *  Método para encontrar todos los registros de la tabla SDB_PNG_LETRA_EJE
 * @return
 */
	public Collection<LetraEje> findAllLetraEje()
	{
		Collection<LetraEje> lle_constantes;
		lle_constantes = null;

		try
		{
			lle_constantes = ipr_parameterRemote.findAllLetraEje();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lle_constantes;
	}

/**
 * Método para encontrar todos los registros de la tabla SDB_PNG_LETRA_EJE
 * que coincida con un id específico
 */
	public void findLetraEjeById()
	{
		try
		{
			LetraEje lle_parametros;

			lle_parametros = getLetraEje();

			if(lle_parametros != null)
			{
				String ls_s;

				ls_s = lle_parametros.getIdLetra();

				if(StringUtils.isValidString(ls_s))
				{
					lle_parametros.setIdLetra(lle_parametros.getIdLetra());
					setParametros(ipr_parameterRemote.findLetraEjeById(ls_s));
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
 * Método para salvar la inserción o actualización
 * @return
 */
	public String salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;
		String       ls_return;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();
		ls_return       = null;

		try
		{
			LetraEje lr_parametros;

			lr_parametros = getLetraEje();

			{
				String ls_idLetra;
				ls_idLetra     = lr_parametros.getIdLetra();

				lb_focus = validateStyles(":fLetraEjeDetalle:idLetra", lfc_context, ls_idLetra, lb_focus);

				if(!StringUtils.isValidString(ls_idLetra))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_LETRA);
			}

			{
				String ls_letra;
				ls_letra     = lr_parametros.getLetra();

				lb_focus = validateStyles(":fLetraEjeDetalle:letra", lfc_context, ls_letra, lb_focus);

				if(!StringUtils.isValidString(ls_letra))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_LETRA);
			}

			{
				String ls_activo;
				ls_activo     = lr_parametros.getActivo();

				lb_focus = validateStyles(":fLetraEjeDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarLetraEje(
			    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				BeanReference lbr_beanReference;

				lbr_beanReference = getBeanReference();

				lbr_beanReference.setLetraEjeActivo(null);
				lbr_beanReference.setLetraEje(null);
			}

			setParametros(null);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fLetraEjeDetalle:globalMsg");
			ls_return = NavegacionCommon.LETRA_EJE;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fLetraEjeDetalle:globalMsg");
		}

		return ls_return;
	}
}
