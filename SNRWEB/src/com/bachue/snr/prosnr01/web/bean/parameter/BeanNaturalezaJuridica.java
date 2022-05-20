package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;
import com.bachue.snr.prosnr01.model.sdb.png.DominioNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanNaturalezaJuridica.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanNaturalezaJuridica")
@SessionScoped
public class BeanNaturalezaJuridica extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -9045046226298877076L;

	/** Propiedad icnj datos auditoria. */
	private Collection<NaturalezaJuridica> icnj_datosAuditoria;

	/** Propiedad inj naturaleza juridica. */
	private NaturalezaJuridica inj_naturalezaJuridica;

	/** Propiedad inj parametros. */
	private NaturalezaJuridica inj_parametros;

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
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<NaturalezaJuridica> datosAuditoria)
	{
		icnj_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<NaturalezaJuridica> getDatosAuditoria()
	{
		return icnj_datosAuditoria;
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
	 * @param anj_nj asigna el valor a la propiedad
	 */
	public void setNaturalezaJuridica(NaturalezaJuridica anj_nj)
	{
		inj_naturalezaJuridica = anj_nj;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public NaturalezaJuridica getNaturalezaJuridica()
	{
		return inj_naturalezaJuridica;
	}

	/**
	 * @param parametros asigna el valor a la propiedad
	 */
	public void setParametros(NaturalezaJuridica parametros)
	{
		inj_parametros = parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public NaturalezaJuridica getParametros()
	{
		if(inj_parametros == null)
			inj_parametros = new NaturalezaJuridica();

		return inj_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Naturaleza Juridica
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setNaturalezaJuridica(new NaturalezaJuridica());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarNaturalezaJuridica");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Metodo para traer los procesos de la base de datos
	 *
	 * @return Colección de procesos resultante de la consulta a la tabla SDB_PNG_DOMINIO_NATURALEZA_JURIDICA
	 */
	public Collection<DominioNaturalezaJuridica> cargarDominioNaturalezaJuridica()
	{
		Collection<DominioNaturalezaJuridica> ln_dominioNaturalezaJuridica;
		ln_dominioNaturalezaJuridica = new LinkedList<DominioNaturalezaJuridica>();

		try
		{
			ln_dominioNaturalezaJuridica = irr_referenceRemote.findAllDominioNaturalezaJuridicaActivos(
				    true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ln_dominioNaturalezaJuridica;
	}

	/**
	 * Metodo para traer los procesos de la base de datos
	 *
	 * @return Colección de procesos resultante de la consulta a la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA
	 */
	public Collection<GrupoNaturalezaJuridica> cargarGrupoNaturalezaJuridica()
	{
		Collection<GrupoNaturalezaJuridica> ln_grupoNaturalezaJuridica;
		ln_grupoNaturalezaJuridica = new LinkedList<GrupoNaturalezaJuridica>();

		try
		{
			ln_grupoNaturalezaJuridica = irr_referenceRemote.findAllGrupoNaturalezaJuridicaActivos(
				    false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ln_grupoNaturalezaJuridica;
	}

	/**
	 * Metodo para traer los procesos de la base de datos
	 *
	 * @return Colección de procesos resultante de la consulta a la tabla SDB_COL_TIPO_RRR
	 */
	public Collection<TipoRrr> cargarTipoRrr()
	{
		Collection<TipoRrr> lt_tipoRrr;
		lt_tipoRrr = new LinkedList<TipoRrr>();

		try
		{
			lt_tipoRrr = irr_referenceRemote.findAllTipoRrrActivos(
				    true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lt_tipoRrr;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PNG_NATURALEZA_JURIDICA
	 * @param anj_nj
	 * @throws B2BException
	 */
	public void consultaDetallada(NaturalezaJuridica anj_nj)
	    throws B2BException
	{
		if(anj_nj != null)
		{
			NaturalezaJuridica lcnj_datos;
			lcnj_datos = ipr_parameterRemote.findNaturalezaJuridicaById(anj_nj);

			if(lcnj_datos != null)
			{
				Collection<NaturalezaJuridica> lccnj_ccnj;
				lccnj_ccnj = new ArrayList<NaturalezaJuridica>();

				lccnj_ccnj.add(lcnj_datos);
				setNaturalezaJuridica(lcnj_datos);

				setDatosAuditoria(lccnj_ccnj);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PNG_NATURALEZA_JURIDICA
	 *
	 * @return Collection<NaturalezaJuridica> Colección con objetos tipo NaturalezaJuridica
	 */
	public Collection<NaturalezaJuridica> findNaturalezaJuridica()
	{
		Collection<NaturalezaJuridica> lcnj_naturalezaJuridica;
		lcnj_naturalezaJuridica = null;

		try
		{
			lcnj_naturalezaJuridica = ipr_parameterRemote.findAllNaturalezaJuridica();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcnj_naturalezaJuridica;
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
			NaturalezaJuridica lnj_parametros;

			lnj_parametros = getNaturalezaJuridica();

			if(lnj_parametros != null)
			{
				{
					String ls_idGrupoNatJur;
					ls_idGrupoNatJur     = lnj_parametros.getIdGrupoNatJur();

					lb_focus = validateStyles(
						    ":fNaturalezaJurDetalle:idGrupoNatJur", lfc_context, ls_idGrupoNatJur, lb_focus
						);

					if(!StringUtils.isValidString(ls_idGrupoNatJur))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_GRUPO_NATURALEZA_JURIDICA);
				}

				{
					String ls_idNaturalezaJuridica;
					ls_idNaturalezaJuridica     = lnj_parametros.getIdNaturalezaJuridica();

					lb_focus = validateStyles(
						    ":fNaturalezaJurDetalle:idNaturalezaJuridica", lfc_context, ls_idNaturalezaJuridica,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_idNaturalezaJuridica))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_NATURALEZA_JURIDICA);
				}

				{
					long ll_version;
					ll_version     = lnj_parametros.getVersion();

					lb_focus = validateStyles(
						    ":fNaturalezaJurDetalle:versionNaturalezaJuridica", lfc_context,
						    NumericUtils.getLongWrapper(ll_version), lb_focus
						);

					if(ll_version <= 0)
					{
						lb_focus = validateStyles(
							    ":fNaturalezaJurDetallee:versionNaturalezaJuridica", lfc_context, "", lb_focus
							);
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_VERSION);
					}
				}

				{
					String ls_nombre;
					ls_nombre     = lnj_parametros.getNombre();

					lb_focus = validateStyles(":fNaturalezaJurDetalle:nombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_habilitadaCalificacion;
					ls_habilitadaCalificacion     = lnj_parametros.getHabilitadaCalificacion();

					lb_focus = validateStyles(
						    ":fNaturalezaJurDetalle:habilitadaCalificacion", lfc_context, ls_habilitadaCalificacion,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_habilitadaCalificacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_HABILITADA_CALIFICACION);
				}

				{
					String ls_activo;
					ls_activo     = lnj_parametros.getActivo();

					lb_focus = validateStyles(":fNaturalezaJurDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarNaturalezaJuridica(
				    lnj_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				{
					BeanReference lbr_beanReference;
					lbr_beanReference = getBeanReference();

					lbr_beanReference.setNaturalezaJuridica0841and0842(null);
					lbr_beanReference.setMaxVersionNaturalezaJuridica(null);
					lbr_beanReference.setNaturalezaJuridica(null);
				}

				setParametros(null);

				ls_return = NavegacionCommon.NATURALEZA_JURIDICA;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fNaturalezaJurDetalle");
				PrimeFaces.current().ajax().update("fNaturalezaJuridica");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fNaturalezaJurDetalle:globalMsg");
		}

		return ls_return;
	}
}
