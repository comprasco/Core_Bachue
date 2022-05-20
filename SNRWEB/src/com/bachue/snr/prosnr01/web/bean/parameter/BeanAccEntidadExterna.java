package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanAccEntidadExterna.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanAccEntidadExterna")
@SessionScoped
public class BeanAccEntidadExterna extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5668044028574562396L;

	/** Propiedad iaee entidadExterna. */
	private AccEntidadExterna iaee_entidadExterna;

	/** Propiedad iaee parametros. */
	private AccEntidadExterna iaee_parametros;

	/** Propiedad icaee datos auditoria. */
	private Collection<AccEntidadExterna> icaee_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ipr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acaee_caee asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<AccEntidadExterna> acaee_caee)
	{
		icaee_datosAuditoria = acaee_caee;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AccEntidadExterna> getDatosAuditoria()
	{
		return icaee_datosAuditoria;
	}

	/**
	 * @param aaee_aee asigna el valor a la propiedad
	 */
	public void setEntidadExterna(AccEntidadExterna aaee_aee)
	{
		iaee_entidadExterna = aaee_aee;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public AccEntidadExterna getEntidadExterna()
	{
		return iaee_entidadExterna;
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
	 * @param aaee_aee asigna el valor a la propiedad
	 */
	public void setParametros(AccEntidadExterna aaee_aee)
	{
		iaee_parametros = aaee_aee;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public AccEntidadExterna getParametros()
	{
		if(iaee_parametros == null)
			iaee_parametros = new AccEntidadExterna();

		return iaee_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * AccEntidadExterna
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEntidadExterna(new AccEntidadExterna());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEntidadExterna");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Metodo para traer los registros de la base de datos
	 *
	 * @return Colección de InteresadoDocumentoTipo resultante de la consulta
	 */
	public Collection<InteresadoDocumentoTipo> cargarInteresadoDocumentoTipo()
	{
		Collection<InteresadoDocumentoTipo> lcidt_interesadoDocumentoTipo;

		lcidt_interesadoDocumentoTipo = new ArrayList<InteresadoDocumentoTipo>();

		try
		{
			lcidt_interesadoDocumentoTipo = irr_referenceRemote.findTipoDocumentoActivo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcidt_interesadoDocumentoTipo;
	}

	/**
	 * Metodo para traer los registros de la base de datos
	 *
	 * @return Colección de InteresadoDocumentoTipo resultante de la consulta
	 */
	public Collection<TipoEntidad> cargarTipoEntidad()
	{
		Collection<TipoEntidad> lcte_tipoEntidad;

		lcte_tipoEntidad = new ArrayList<TipoEntidad>();

		try
		{
			lcte_tipoEntidad = irr_referenceRemote.findTipoEntidad(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcte_tipoEntidad;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_ACC_ENTIDAD_EXTERNA
	 * @param aaee_aee
	 * @throws B2BException
	 */
	public void consultaDetallada(AccEntidadExterna aaee_aee)
	    throws B2BException
	{
		if(aaee_aee != null)
		{
			AccEntidadExterna lc_canal;
			lc_canal = ipr_parameterRemote.findAccEntidadExternaById(aaee_aee);

			if(lc_canal != null)
			{
				Collection<AccEntidadExterna> lcaee_caee;

				lcaee_caee = new ArrayList<AccEntidadExterna>();

				lcaee_caee.add(lc_canal);
				setEntidadExterna(lc_canal);

				setDatosAuditoria(lcaee_caee);
			}

			setInsertar(false);
		}
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
			AccEntidadExterna laee_entidadExterna;

			laee_entidadExterna = getEntidadExterna();

			if(laee_entidadExterna != null)
			{
				String       ls_pais;
				Departamento ld_parametros;

				ld_parametros     = new Departamento();
				ls_pais           = laee_entidadExterna.getIdPais();

				if(StringUtils.isValidString(ls_pais))
				{
					ld_parametros.setIdPais(ls_pais);

					lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
				}
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
	 *  Método para encontrar todos los registros de la tabla SDB_ACC_ENTIDAD_EXTERNA
	 * @return Collection AccEntidadExterna
	 */
	public Collection<AccEntidadExterna> findEntidadExterna()
	{
		Collection<AccEntidadExterna> lcaee_entidadExterna;

		lcaee_entidadExterna = null;

		try
		{
			lcaee_entidadExterna = ipr_parameterRemote.findAllAccEntidadExternas();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcaee_entidadExterna;
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
			Municipio         lm_parametros;
			AccEntidadExterna laee_entidadExterna;

			laee_entidadExterna     = getEntidadExterna();
			lm_parametros           = new Municipio();

			if(laee_entidadExterna != null)
			{
				lm_parametros.setIdPais(laee_entidadExterna.getIdPais());
				lm_parametros.setIdDepartamento(laee_entidadExterna.getIdDepartamento());

				if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				else
					lcm_municipios = null;
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
			AccEntidadExterna laee_parametros;

			laee_parametros = getEntidadExterna();

			if(laee_parametros != null)
			{
				{
					String ls_nombre;

					ls_nombre     = laee_parametros.getNombre();

					lb_focus = validateStyles(":fAccEntidadExternaDetalle:nombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_idTipoOficina;

					ls_idTipoOficina     = laee_parametros.getIdTipoOficina();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaDetalle:idTipoOficina", lfc_context, ls_idTipoOficina, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoOficina))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);
				}

				{
					String ls_idTipoEntidad;

					ls_idTipoEntidad     = laee_parametros.getIdTipoEntidad();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaDetalle:idTipoEntidad", lfc_context, ls_idTipoEntidad, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoEntidad))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_ENTIDAD);
				}

				{
					String ls_activo;
					ls_activo     = laee_parametros.getActivo();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarAccEntidadExterna(
				    laee_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				{
					BeanReference lbr_beanReference;

					lbr_beanReference = getBeanReference();
					lbr_beanReference.setEntidadExterna(null);
					lbr_beanReference.setTipoEntidadExterna(null);
					lbr_beanReference.setTipoEntidadExternaActivo(null);
				}

				setParametros(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fAccEntidadExternaDetalle:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fAccEntidadExternaDetalle:globalMsg");
		}
	}
}
