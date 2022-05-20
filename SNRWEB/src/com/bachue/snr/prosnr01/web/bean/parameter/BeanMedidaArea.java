package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Medida Area.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanMedidaArea")
@SessionScoped
public class BeanMedidaArea extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6573575237467799977L;

	/** Propiedad icma datos auditoria. */
	private Collection<MedidaArea> icma_datosAuditoria;

	/** Propiedad ima medida area. */
	private MedidaArea ima_medidaArea;

	/** Propiedad ima parametros. */
	private MedidaArea ima_parametros;

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
	 * @param acma_cma asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<MedidaArea> acma_cma)
	{
		icma_datosAuditoria = acma_cma;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<MedidaArea> getDatosAuditoria()
	{
		return icma_datosAuditoria;
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
	 * @param parametros asigna el valor a la propiedad parametros
	 */
	public void setParametros(MedidaArea parametros)
	{
		ima_parametros = parametros;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public MedidaArea getParametros()
	{
		if(ima_parametros == null)
			ima_parametros = new MedidaArea();

		return ima_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Medida Area.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setmedidaArea(new MedidaArea());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarMedidaArea");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_MEDIDA_AREA.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto MedidaArea
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(MedidaArea acad_cad)
	    throws B2BException
	{
		if(acad_cad != null)
		{
			MedidaArea lcad_datos;
			String     ls_s;

			ls_s           = acad_cad.getIdMedidaArea();
			lcad_datos     = null;

			if(StringUtils.isValidString(ls_s))
				lcad_datos = ipr_parameterRemote.findMedidaAreaById(ls_s);

			if(lcad_datos != null)
			{
				Collection<MedidaArea> lccad_ccad;
				lccad_ccad = new ArrayList<MedidaArea>();

				lccad_ccad.add(lcad_datos);
				setmedidaArea(lcad_datos);

				setDatosAuditoria(lccad_ccad);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_MEDIDA_AREA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<MedidaArea> findAllMedidaArea()
	{
		Collection<MedidaArea> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllMedidaArea();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_MEDIDA_AREA
	 * que coincida con un id específico.
	 */
	public void findMedidaAreaById()
	{
		try
		{
			MedidaArea ld_parametros;

			ld_parametros = getmedidaArea();

			if(ld_parametros != null)
			{
				String ls_s;

				ls_s = ld_parametros.getCodigo();

				if(StringUtils.isValidString(ls_s))
				{
					ld_parametros.setIdMedidaArea(ld_parametros.getIdMedidaArea());

					setParametros(ipr_parameterRemote.findMedidaAreaById(ls_s));

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
	 * Retorna el valor de medida area.
	 *
	 * @return el valor de medida area
	 */
	public MedidaArea getmedidaArea()
	{
		return ima_medidaArea;
	}

	/**
	 * Método para salvar la inserción o actualización.
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
			MedidaArea lma_parametros;

			lma_parametros = getmedidaArea();

			if(lma_parametros != null)
			{
				{
					String ls_codigo;
					ls_codigo     = lma_parametros.getCodigo();

					lb_focus = validateStyles(":fMedidaDetalle:idCodigo", lfc_context, ls_codigo, lb_focus);

					if(!StringUtils.isValidString(ls_codigo))
						throw new B2BException(ErrorKeys.ERROR_CODIGO);
				}

				{
					String ls_descripcion;
					ls_descripcion     = lma_parametros.getDescripcion();

					// Añadir en la pantalla el campo descripciíon.
					lb_focus = validateStyles(":fMedidaDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.ERROR_CODIGO);
				}

				{
					String ls_estado;
					ls_estado     = lma_parametros.getEstado();

					lb_focus = validateStyles(":fMedidaDetalle:idEstado", lfc_context, ls_estado, lb_focus);

					if(!StringUtils.isValidString(ls_estado))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO);
				}

				ipr_parameterRemote.salvarMedidaArea(
				    lma_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				{
					BeanReference lbr_beanReference;
					lbr_beanReference = getBeanReference();

					lbr_beanReference.setMedidaAreaActivo(null);
					lbr_beanReference.setMedidaAreaById(null);
				}

				setParametros(null);
				setmedidaArea(null);

				ls_result = NavegacionCommon.MEDIDA_AREA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}

	/**
	 * Modifica el valor de medida area.
	 *
	 * @param ama_ma asigna el valor a la propiedad medida area
	 */
	public void setmedidaArea(MedidaArea ama_ma)
	{
		ima_medidaArea = ama_ma;
	}
}
