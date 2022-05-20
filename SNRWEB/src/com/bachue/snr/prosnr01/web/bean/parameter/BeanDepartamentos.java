package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;

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
 * Clase que contiene todos las propiedades y acciones de BeanDepartamentos.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanDepartamentos")
@SessionScoped
public class BeanDepartamentos extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6324178474438460922L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fDepartamentosDetalle";

	/** Propiedad icd datos auditoria. */
	private Collection<Departamento> icd_datosAuditoria;

	/** Propiedad id parametros. */
	private Departamento id_parametros;

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
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Departamento> datosAuditoria)
	{
		icd_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Departamento> getDatosAuditoria()
	{
		return icd_datosAuditoria;
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
	 * @param ad_d asigna el valor a la propiedad parametros
	 */
	public void setParametros(Departamento ad_d)
	{
		id_parametros = ad_d;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Departamento getParametros()
	{
		if(id_parametros == null)
			id_parametros = new Departamento();

		return id_parametros;
	}

/**
 * Metodo para  cambiar estado con el fin de saber si se desea insertar un departamento.
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
 * Metodo para traer los registros de los departamentos que coincidan con un id_departemento
 * especifico.
 */
	public void consultaDetallada()
	{
		try
		{
			Departamento ld_parametros;
			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				String ls_idPais;
				String ls_idDepartamento;

				ls_idPais             = FacesUtils.getStringFacesParameter("idPais");
				ls_idDepartamento     = FacesUtils.getStringFacesParameter("idDepartamento");

				if(StringUtils.isValidString(ls_idPais))
				{
					Collection<Departamento> lcd_cd;

					lcd_cd = new ArrayList<Departamento>();

					ld_parametros.setIdPais(ls_idPais);
					ld_parametros.setIdDepartamento(ls_idDepartamento);

					setParametros(ipr_parameterRemote.findDepartamentoById(ld_parametros));
					lcd_cd.add(getParametros());

					setDatosAuditoria(lcd_cd);
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
 * Metodo para traer todos los registros de la tabla SDB_PGN_DEPARTAMENTO.
 *
 * @return devuelve el valor de Collection
 */
	public Collection<Departamento> findAllDepartamentos()
	{
		Collection<Departamento> lcd_constantes;
		lcd_constantes = null;

		try
		{
			lcd_constantes = ipr_parameterRemote.findAllDepartamentos();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcd_constantes;
	}

/**
 * Metodo para salvar un Departamento ya sea para modificar o insertar registros.
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
			Departamento ld_parametros;

			ld_parametros = getParametros();

			{
				String ls_idPais;
				ls_idPais     = ld_parametros.getIdPais();

				lb_focus = validateStyles(":fDepartamentosDetalle:idDepIdPais", lfc_context, ls_idPais, lb_focus);

				if(!StringUtils.isValidString(ls_idPais))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
			}

			{
				String ls_departamento;
				ls_departamento     = ld_parametros.getIdDepartamento();

				lb_focus = validateStyles(
					    ":fDepartamentosDetalle:idDepIdDepartamento", lfc_context, ls_departamento, lb_focus
					);

				if(!StringUtils.isValidString(ls_departamento))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);
			}

			{
				String ls_nombre;
				ls_nombre     = ld_parametros.getNombre();

				lb_focus = validateStyles(":fDepartamentosDetalle:idDepNombre", lfc_context, ls_nombre, lb_focus);

				if(!StringUtils.isValidString(ls_nombre))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
			}

			{
				String ls_indicativoTelefono;
				ls_indicativoTelefono     = ld_parametros.getIndicativoTelefonico();

				lb_focus = validateStyles(
					    ":fDepartamentosDetalle:idDepIndTel", lfc_context, ls_indicativoTelefono, lb_focus
					);

				if(!StringUtils.isValidString(ls_indicativoTelefono))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_INDICATIVO_TELEFONICO);
			}

			{
				String ls_activo;
				ls_activo     = ld_parametros.getActivo();

				lb_focus = validateStyles(":fDepartamentosDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ld_parametros.setIdUsuarioCreacion(getUserId());
			ld_parametros.setIdUsuarioModificacion(getUserId());

			ipr_parameterRemote.salvarDepartamento(
			    ld_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
			);

			getBeanReference().setDepartamentos(null);

			setParametros(null);

			ls_result = NavegacionCommon.DEPARTAMENTOS;

			if(isInsertar())
				addMessage(MessagesKeys.DEPARTAMENTO_INSERTADO_DE_MANERA_EXITOSA);
			else
				addMessage(MessagesKeys.DEPARTAMENTO_MODIFICADO_DE_MANERA_EXITOSA);

			PrimeFaces.current().ajax().update("fDepartamento");
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_result;
	}
}
