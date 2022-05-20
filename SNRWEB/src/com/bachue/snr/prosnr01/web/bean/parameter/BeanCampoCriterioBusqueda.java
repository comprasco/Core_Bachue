package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.objectDataBase.UserObjects;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;

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
 * Clase para el manejo de la capa web para CampoCriterioBusqueda
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanCampoCriterioBusqueda")
@SessionScoped
public class BeanCampoCriterioBusqueda extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4607768610420459382L;

	/** Propiedad icc campo consulta. */
	private CamposConsulta icc_campoConsulta;

	/** Propiedad iccos datos auditoria. */
	private Collection<CamposConsulta> iccos_datosAuditoria;

	/** Propiedad icuo columnas tabla. */
	private Collection<UserObjects> icuo_columnasTabla;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is nombre campo. */
	private String is_nombreCampo;

	/** Propiedad is nombre tabla. */
	private String is_nombreTabla;

	/** Propiedad ic user objects. */
	private UserObjects ic_userObjects;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param acc_cc asigna el valor a la propiedad.
	 */
	public void setCampoConsulta(CamposConsulta acc_cc)
	{
		icc_campoConsulta = acc_cc;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad.
	 */
	public CamposConsulta getCampoConsulta()
	{
		return icc_campoConsulta;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param acuo_cuo asigna el valor a la propiedad.
	 */
	public void setColumnasTabla(Collection<UserObjects> acuo_cuo)
	{
		icuo_columnasTabla = acuo_cuo;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad.
	 */
	public Collection<UserObjects> getColumnasTabla()
	{
		return icuo_columnasTabla;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param acc_cc asigna el valor a la propiedad.
	 */
	public void setDatosAuditoria(Collection<CamposConsulta> acc_cc)
	{
		iccos_datosAuditoria = acc_cc;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad.
	 */
	public Collection<CamposConsulta> getDatosAuditoria()
	{
		return iccos_datosAuditoria;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad.
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad.
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param nombreCampo asigna el valor a la propiedad.
	 */
	public void setNombreCampo(String nombreCampo)
	{
		this.is_nombreCampo = nombreCampo;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad.
	 */
	public String getNombreCampo()
	{
		return is_nombreCampo;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad.
	 */
	public void setNombreTabla(String as_s)
	{
		is_nombreTabla = as_s;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad.
	 */
	public String getNombreTabla()
	{
		return is_nombreTabla;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param ic_userObjects asigna el valor a la propiedad.
	 */
	public void setUserObjects(UserObjects ic_userObjects)
	{
		this.ic_userObjects = ic_userObjects;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad.
	 */
	public UserObjects getUserObjects()
	{
		return ic_userObjects;
	}

	/**
	 * Método encargado de actualizar el campoCriterioBusqueda para una inserción dinámica
	 */
	public void actualizarCampoCriterioBusqueda()
	    throws B2BException
	{
		CamposConsulta lcc_temp;

		lcc_temp = getCampoConsulta();

		if(lcc_temp != null)
		{
			String ls_tipoCriterioBusquedaActual;

			ls_tipoCriterioBusquedaActual = lcc_temp.getIdTipoCriterioBusqueda();

			if(StringUtils.isValidString(ls_tipoCriterioBusquedaActual))
			{
				Collection<CamposConsulta> lccc_cllCamposConsulta;

				lccc_cllCamposConsulta = ipr_parameterRemote.findAllCamposCriteriosBusquedaByTipoCriterioBusqueda(
					    ls_tipoCriterioBusquedaActual
					);

				if(lccc_cllCamposConsulta != null)
				{
					long ll_idCampoCriterioBusquedaTmp;

					ll_idCampoCriterioBusquedaTmp = lccc_cllCamposConsulta.size() + 1L;

					lcc_temp.setIdCampoCriterioBusqueda(StringUtils.getString(ll_idCampoCriterioBusquedaTmp));
				}
			}
		}
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Campo
	 * Criterio Busqueda
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCampoConsulta((new CamposConsulta()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCampoCriterioBusqueda");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método que se encarga de reiniciar variables
	 */
	public void clear()
	{
		setCampoConsulta(null);
		setInsertar(false);
		setColumnasTabla(null);
		setNombreCampo(null);
		setNombreTabla(null);
	}

	/**
	 * Método encargado de consultar los datos de campo criterio busqueda.
	 *
	 * @param acc_cc Argumento de tipo <code>CamposConsulta</code> que contiene los criterios necesarios para realizar la consulta.
	 *
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	public void consultaDetallada(CamposConsulta acc_cc)
	    throws B2BException
	{
		if(acc_cc != null)
		{
			String ls_tipoCriterioBusqueda;
			String ls_campoCriterioBusqueda;

			ls_tipoCriterioBusqueda      = acc_cc.getIdTipoCriterioBusqueda();
			ls_campoCriterioBusqueda     = acc_cc.getIdCampoCriterioBusqueda();

			if(
			    StringUtils.isValidString(ls_tipoCriterioBusqueda)
				    && (StringUtils.isValidString(ls_campoCriterioBusqueda))
			)
			{
				acc_cc = ipr_parameterRemote.findCampoCriterioBusquedaById(
					    ls_tipoCriterioBusqueda, ls_campoCriterioBusqueda
					);

				if(acc_cc != null)
				{
					Collection<CamposConsulta> lccc_cc;

					lccc_cc = new ArrayList<CamposConsulta>();

					lccc_cc.add(acc_cc);
					setCampoConsulta(acc_cc);

					setDatosAuditoria(lccc_cc);
				}

				setInsertar(false);
			}
		}
	}

	/**
	 * Método para consultar todos los registros de CamposCriterioBusqueda´
	 */
	public Collection<CamposConsulta> findAllCamposCriterioBusqueda()
	{
		Collection<CamposConsulta> lccc_cc;
		lccc_cc = null;

		try
		{
			lccc_cc = ipr_parameterRemote.findAllCamposCriteriosBusqueda(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccc_cc;
	}

	/**
	 * Método para encontrar todos los nombres de las columnas de las tablas de la base de datos.
	 */
	public void findAllNombresColumnas()
	{
		Collection<UserObjects> lcuo_datos;
		String                  ls_nombreTabla;

		lcuo_datos         = null;
		ls_nombreTabla     = getNombreTabla();

		if(StringUtils.isValidString(ls_nombreTabla))
		{
			try
			{
				lcuo_datos = irr_referenceRemote.findAllNombresColumnas(
					    ls_nombreTabla, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		setColumnasTabla(lcuo_datos);
	}

	/**
	 * Método encargado de encontrar el nombre de todas las tablas de la base de datos.
	 *
	 * @return Colección con datos solicitados
	 */
	public Collection<UserObjects> findAllNombresTablas()
	{
		Collection<UserObjects> lcuo_datos;

		try
		{
			lcuo_datos = irr_referenceRemote.findAllNombresTablas(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcuo_datos = null;
		}

		return lcuo_datos;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return Regla de navegación que determina hacia donde debe redireccionar la pantalla.
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
			CamposConsulta icc_campoConsulta;

			icc_campoConsulta = getCampoConsulta();

			if(icc_campoConsulta != null)
			{
				{
					String ls_idTipoCriterioBusqueda;

					ls_idTipoCriterioBusqueda     = icc_campoConsulta.getIdTipoCriterioBusqueda();

					lb_focus = validateStyles(
						    ":fCampoCriterioBusquedaDetalle:idTipoCriterioBusqueda", lfc_context,
						    ls_idTipoCriterioBusqueda, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoCriterioBusqueda))
						throw new B2BException(ErrorKeys.ERROR_SELECCIONE_TIPO_CRITERIO_BUSQUEDA);
				}

				{
					String ls_idCampoCriterioBusqueda;

					ls_idCampoCriterioBusqueda     = icc_campoConsulta.getIdCampoCriterioBusqueda();

					lb_focus = validateStyles(
						    ":fCampoCriterioBusquedaDetalle:idCampoCriterioBusqueda", lfc_context,
						    ls_idCampoCriterioBusqueda, lb_focus
						);

					if(!StringUtils.isValidString(ls_idCampoCriterioBusqueda))
						throw new B2BException(ErrorKeys.ERROR_SIN_CAMPO_CRITERIO_BUSQUEDA);
				}

				{
					String ls_nombreTabla;

					ls_nombreTabla     = getNombreTabla();

					lb_focus = validateStyles(
						    ":fCampoCriterioBusquedaDetalle:idNombreTablas", lfc_context, ls_nombreTabla, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombreTabla))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_TABLA);
				}

				{
					String ls_nombreCampo;

					ls_nombreCampo     = icc_campoConsulta.getNombreCampo();

					lb_focus = validateStyles(
						    ":fCampoCriterioBusquedaDetalle:idNombreCampo", lfc_context, ls_nombreCampo, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombreCampo))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_CAMPO);
				}

				{
					String ls_etiquetaCampo;

					ls_etiquetaCampo     = icc_campoConsulta.getEtiquetaCampo();

					lb_focus = validateStyles(
						    ":fCampoCriterioBusquedaDetalle:idEtiquetaCampo", lfc_context, ls_etiquetaCampo, lb_focus
						);

					if(!StringUtils.isValidString(ls_etiquetaCampo))
						throw new B2BException(ErrorKeys.ERROR_SIN_ETIQUETA_CAMPO);
				}

				{
					String ls_obligatorio;

					ls_obligatorio     = icc_campoConsulta.getObligatorio();

					lb_focus = validateStyles(
						    ":fCampoCriterioBusquedaDetalle:idSelectobligatorio", lfc_context, ls_obligatorio, lb_focus
						);

					if(!StringUtils.isValidString(ls_obligatorio))
						throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_OBLIGATORIO);
				}

				{
					String ls_activo;

					ls_activo     = icc_campoConsulta.getActivo();

					lb_focus = validateStyles(
						    ":fCampoCriterioBusquedaDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.ERROR_SIN_ACTIVO);
				}

				ipr_parameterRemote.salvarCampoCriterioBusqueda(
				    icc_campoConsulta, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setCampoConsulta(null);

				ls_result = NavegacionCommon.CAMPOS_CRITERIO_BUSQUEDA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCampoCriterioBusquedaDetalle:globalMsg");
		}

		return ls_result;
	}
}
