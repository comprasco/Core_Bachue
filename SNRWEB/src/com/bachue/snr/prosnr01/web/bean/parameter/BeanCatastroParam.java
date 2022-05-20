package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Catastro;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCatastroParam.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanCatastroParam")
@SessionScoped
public class BeanCatastroParam extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCatastroParam.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1997768820613076853L;

	/** Propiedad ic catastro. */
	private Catastro ic_catastro;

	/** Propiedad icc datos auditoria. */
	private Collection<Catastro> icc_datosAuditoria;

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
	 * @param acc_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Catastro> acc_datosAuditoria)
	{
		icc_datosAuditoria = acc_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Catastro> getDatosAuditoria()
	{
		return icc_datosAuditoria;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setCatastro(Catastro ac_c)
	{
		ic_catastro = ac_c;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Catastro getCatastro()
	{
		return ic_catastro;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_CATASTRO
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCatastro((new Catastro()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCatastro");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_CATASTRO por medio de su indicativo
	 *
	 * @param ac_catastro indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(Catastro ac_catastro)
	    throws B2BException
	{
		if(ac_catastro != null)
		{
			Catastro lc_catastro;

			lc_catastro = ipr_parameterRemote.findCatastroById(
				    ac_catastro, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lc_catastro != null)
			{
				Collection<Catastro> lcc_cc;

				lcc_cc = new ArrayList<Catastro>();

				lcc_cc.add(lc_catastro);

				setCatastro(lc_catastro);
				setDatosAuditoria(lcc_cc);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CATASTRO
	 *
	 * @return Collection de Catastro resultante de la consulta
	 */
	public Collection<Catastro> findAllCatastro()
	{
		Collection<Catastro> lcc_catastro;

		lcc_catastro = null;

		try
		{
			lcc_catastro = ipr_parameterRemote.findAllCatastro();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcc_catastro;
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
			String ls_pais;

			ls_pais = getCatastro().getIdPais();

			if(StringUtils.isValidString(ls_pais))
			{
				Departamento ld_parametros;

				ld_parametros = new Departamento();

				ld_parametros.setIdPais(ls_pais);

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
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			Municipio lm_parametros;

			lm_parametros = new Municipio();

			Catastro lc_catastro;

			lc_catastro = getCatastro();

			if(lc_catastro != null)
			{
				lm_parametros.setIdPais(lc_catastro.getIdPais());
				lm_parametros.setIdDepartamento(lc_catastro.getIdDepartamento());

				if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_CATASTRO
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			Catastro     lc_catastro;
			boolean      lb_focus;
			FacesContext lfc_context;

			lb_focus        = true;
			lfc_context     = FacesContext.getCurrentInstance();
			lc_catastro     = getCatastro();

			if(lc_catastro != null)
			{
				String ls_validador;

				{
					ls_validador     = lc_catastro.getIdCatastro();

					lb_focus = validateStyles(":fCatastroDetalle:idCatastro", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CATASTRO);
				}

				{
					ls_validador     = lc_catastro.getCorreoElectronico();

					lb_focus = validateStyles(
						    ":fCatastroDetalle:idCorreoElectronicoInter", lfc_context, ls_validador, lb_focus
						);

					if(
					    StringUtils.isValidString(ls_validador)
						    && !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_validador)
					)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
				}

				ipr_parameterRemote.salvarCatastro(
				    lc_catastro, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.CATASTRO_PARAM;

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
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCatastroDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.CATASTRO_PARAM;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setCatastro(null);
		setInsertar(false);
	}
}
