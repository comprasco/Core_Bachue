package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Campos Consulta.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanCamposConsulta")
@SessionScoped
public class BeanCamposConsulta extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5263333193198718879L;

	/** Propiedad ima campos consulta. */
	private CamposConsulta ima_CamposConsulta;

	/** Propiedad ima parametros. */
	private CamposConsulta ima_parametros;

	/** Propiedad icma datos auditoria. */
	private Collection<CamposConsulta> icma_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is id campo. */
	private String is_idCampo;

	/** Propiedad is id consulta. */
	private String is_idConsulta;

	/** Propiedad orden. */
	private String orden;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de campos consulta.
	 *
	 * @param ama_ma asigna el valor a la propiedad campos consulta
	 */
	public void setCamposConsulta(CamposConsulta ama_ma)
	{
		ima_CamposConsulta = ama_ma;
	}

	/**
	 * Retorna el valor de campos consulta.
	 *
	 * @return el valor de campos consulta
	 */
	public CamposConsulta getCamposConsulta()
	{
		return ima_CamposConsulta;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param acma_cma asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<CamposConsulta> acma_cma)
	{
		icma_datosAuditoria = acma_cma;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<CamposConsulta> getDatosAuditoria()
	{
		return icma_datosAuditoria;
	}

	/**
	 * Modifica el valor de id campo.
	 *
	 * @param as_s asigna el valor a la propiedad id campo
	 */
	public void setIdCampo(String as_s)
	{
		is_idCampo = as_s;
	}

	/**
	 * Retorna el valor de id campo.
	 *
	 * @return el valor de id campo
	 */
	public String getIdCampo()
	{
		return is_idCampo;
	}

	/**
	 * Modifica el valor de id consulta.
	 *
	 * @param as_s asigna el valor a la propiedad id consulta
	 */
	public void setIdConsulta(String as_s)
	{
		is_idConsulta = as_s;
	}

	/**
	 * Retorna el valor de id consulta.
	 *
	 * @return el valor de id consulta
	 */
	public String getIdConsulta()
	{
		return is_idConsulta;
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
	 * Modifica el valor de orden.
	 *
	 * @param as_s asigna el valor a la propiedad orden
	 */
	public void setOrden(String as_s)
	{
		orden = as_s;
	}

	/**
	 * Retorna el valor de orden.
	 *
	 * @return el valor de orden
	 */
	public String getOrden()
	{
		return orden;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param parametros asigna el valor a la propiedad parametros
	 */
	public void setParametros(CamposConsulta parametros)
	{
		ima_parametros = parametros;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public CamposConsulta getParametros()
	{
		if(ima_parametros == null)
			ima_parametros = new CamposConsulta();

		return ima_parametros;
	}

/**
 * Método para cambiar estado para saber si se desea insertar un nuevo
 * Causal Aprobación Devolución.
 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCamposConsulta(new CamposConsulta());

		setIdConsulta(null);
		setIdCampo(null);

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCamposConsulta");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

/**
 * Método para consultar en la base de datos la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
 *
 * @param acad_cad correspondiente al valor del tipo de objeto CamposConsulta
 * @throws B2BException Señala que se ha producido una excepción
 */
	public void consultaDetallada(CamposConsulta acad_cad)
	    throws B2BException
	{
		if(acad_cad != null)
		{
			CamposConsulta lcad_datos;
			lcad_datos = ipr_parameterRemote.findCamposConsultaById(acad_cad);

			if(lcad_datos != null)
			{
				Collection<CamposConsulta> lccad_ccad;
				lccad_ccad = new ArrayList<CamposConsulta>();

				lccad_ccad.add(lcad_datos);
				setCamposConsulta(lcad_datos);

				setIdConsulta(StringUtils.getString(lcad_datos.getIdConsulta()));
				setIdCampo(StringUtils.getString(lcad_datos.getIdCampo()));

				setDatosAuditoria(lccad_ccad);
			}

			setInsertar(false);
		}
	}

/**
 *  Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
 *
 * @return devuelve el valor de Collection
 */
	public Collection<CamposConsulta> findAllCamposConsulta()
	{
		Collection<CamposConsulta> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllCamposConsulta();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

/**
 * Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION
 * que coincida con un id específico.
 */
	public void findCamposConsultaById()
	{
		try
		{
			CamposConsulta ld_parametros;
			ld_parametros = getCamposConsulta();

			if(ld_parametros != null)
			{
				ld_parametros.setIdConsulta(ld_parametros.getIdConsulta());
				ld_parametros.setIdCampo(ld_parametros.getIdCampo());
				setParametros(ipr_parameterRemote.findCamposConsultaById(ld_parametros));
				setInsertar(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

/**
 * Metodo para traer las consultas de la base de datos
 *
 * @return Colección de Consultas resultante de la consulta
 */
	public Collection<Consultas> cargarConsultas()
	{
		Collection<Consultas> lcc_consultas;

		lcc_consultas = new LinkedList<Consultas>();

		try
		{
			lcc_consultas = irr_referenceRemote.cargarTipoConsulta(
				    getUserId(), true, getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcc_consultas;
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
			CamposConsulta lma_parametros;

			lma_parametros = getCamposConsulta();

			{
				String ls_idConsulta;
				ls_idConsulta     = getIdConsulta();

				lb_focus = validateStyles(":fCamposConsultaDetalle:idConsulta", lfc_context, ls_idConsulta, lb_focus);

				if(!StringUtils.isValidString(ls_idConsulta))
					throw new B2BException(ErrorKeys.ERROR_CAMPOS_CONSULTAS);

				Long ll_idConsulta;
				ll_idConsulta     = NumericUtils.getLongWrapper(ls_idConsulta, -1);

				lb_focus = validateStyles(":fCamposConsultaDetalle:idConsulta", lfc_context, ll_idConsulta, lb_focus);

				if(!NumericUtils.isValidLong(ll_idConsulta))
					throw new B2BException(ErrorKeys.ERROR_CAMPOS_CONSULTAS);

				lma_parametros.setIdConsulta(ll_idConsulta);
			}

			{
				String ls_idCampo;
				ls_idCampo     = getIdCampo();

				lb_focus = validateStyles(":fCamposConsultaDetalle:idCampo", lfc_context, ls_idCampo, lb_focus);

				if(!StringUtils.isValidString(ls_idCampo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_CAMPO);

				Long ll_idCampo;
				ll_idCampo     = NumericUtils.getLongWrapper(ls_idCampo, -1);

				lb_focus = validateStyles(":fCamposConsultaDetalle:idCampo", lfc_context, ll_idCampo, lb_focus);

				if(!NumericUtils.isValidLong(ll_idCampo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_CAMPO);

				lma_parametros.setIdCampo(ll_idCampo);
			}

			{
				String ls_tipoCampo;
				ls_tipoCampo     = lma_parametros.getTipoCampo();

				lb_focus = validateStyles(":fCamposConsultaDetalle:idTipoCampo", lfc_context, ls_tipoCampo, lb_focus);

				if(!StringUtils.isValidString(ls_tipoCampo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_TIPO_CAMPO);
			}

			{
				String ls_nombreCampo;
				ls_nombreCampo     = lma_parametros.getNombreCampo();

				lb_focus = validateStyles(
					    ":fCamposConsultaDetalle:idNombreCampo", lfc_context, ls_nombreCampo, lb_focus
					);

				if(!StringUtils.isValidString(ls_nombreCampo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_CAMPO);
			}

			{
				String ls_obligatorio;
				ls_obligatorio     = lma_parametros.getObligatorio();

				lb_focus = validateStyles(
					    ":fCamposConsultaDetalle:idObligatorio", lfc_context, ls_obligatorio, lb_focus
					);

				if(!StringUtils.isValidString(ls_obligatorio))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OBLIGATORIO);
			}

			{
				String ls_estado;
				ls_estado     = lma_parametros.getEstado();

				lb_focus = validateStyles(":fCamposConsultaDetalle:idEstado", lfc_context, ls_estado, lb_focus);

				if(!StringUtils.isValidString(ls_estado))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO);
			}

			{
				String ls_orden;
				ls_orden     = getOrden();

				lb_focus = validateStyles(":fCamposConsultaDetalle:idOrden", lfc_context, ls_orden, lb_focus);

				if(!StringUtils.isValidString(ls_orden))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ORDEN);

				Long ll_orden;
				ll_orden     = NumericUtils.getLongWrapper(ls_orden, -1);

				lb_focus = validateStyles(":fCamposConsultaDetalle:idOrden", lfc_context, ll_orden, lb_focus);

				if(!NumericUtils.isValidLong(ll_orden))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ORDEN);

				lma_parametros.setOrden(ll_orden);
			}

			ipr_parameterRemote.salvarCamposConsulta(
			    lma_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
			
			getBeanReference().setCamposConsulta(null);

			setParametros(null);

			setCamposConsulta(null);

			setIdConsulta(null);
			setIdCampo(null);

			ls_result = NavegacionCommon.CAMPOS_CONSULTA;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
