package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoCondicion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoActoCondicion.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoActoCondicion")
@SessionScoped
public class BeanTipoActoCondicion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8173258646971762186L;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is codigo sql. */
	private String is_codigoSql;

	/** Propiedad ir parametros. */
	private TipoActoCondicion ir_parametros;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de codigo sql.
	 *
	 * @param as_s asigna el valor a la propiedad codigo sql
	 */
	public void setCodigoSql(String as_s)
	{
		is_codigoSql = as_s;
	}

	/**
	 * Retorna el valor de codigo sql.
	 *
	 * @return el valor de codigo sql
	 */
	public String getCodigoSql()
	{
		return is_codigoSql;
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
	public void setParametros(TipoActoCondicion ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoActoCondicion getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new TipoActoCondicion();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de tipo acto condicion.
	 *
	 * @param acr_cr asigna el valor a la propiedad tipo acto condicion
	 */
	public void setTipoActoCondicion(TipoActoCondicion acr_cr)
	{
		ir_parametros = acr_cr;
	}

	/**
	 * Retorna el valor de tipo acto condicion.
	 *
	 * @return el valor de tipo acto condicion
	 */
	public TipoActoCondicion getTipoActoCondicion()
	{
		return ir_parametros;
	}

	/**
	 * Metodo que indica si se desea insertar un TipoActoCondicion.
	 *
	 * @param arc_tipoActoCondicion seleccionado
	 *            objeto el cual se va a insertar o modificar
	 * @param ab_proceso            indica si se va a insetar o se va a modificar
	 * @return devuelve el valor de String
	 */
	public String botonInsertar(TipoActoCondicion arc_tipoActoCondicion, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			arc_tipoActoCondicion = new TipoActoCondicion();

			setTipoActoCondicion(arc_tipoActoCondicion);
			setInsertar(true);
		}
		else
		{
			setTipoActoCondicion(arc_tipoActoCondicion);
			setInsertar(false);
		}

		ls_return = NavegacionCommon.TIPO_ACTO_CONDICION_DETALLE;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoActoCondicion> cargarTipoActoCondicion()
	{
		Collection<TipoActoCondicion> lrc_constantes;
		lrc_constantes = null;

		try
		{
			lrc_constantes = ipr_parameterRemote.findAllTipoActoCondicion(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lrc_constantes;
	}

	/**
	 * Metodo que se encarga de reiniciar variables.
	 */
	public void clear()
	{
		setParametros(null);
		setInsertar(false);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Proceso> findAllProcesos()
	{
		Collection<Proceso> lcp_datos;
		lcp_datos = null;

		try
		{
			lcp_datos = irr_referenceRemote.findAllProcesosActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcp_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Subproceso> findAllSubprocesos()
	{
		Collection<Subproceso> lcs_datos;
		lcs_datos = null;

		try
		{
			lcs_datos = irr_referenceRemote.findSubprocesos(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcs_datos;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de tipo acto.
	 *
	 * @return Colección de tipo acto resultante de la consulta
	 */
	public void cargarTiposActoVersion(String ls_idTipoActo)
	{
		if(StringUtils.isValidString(ls_idTipoActo))
		{
			try
			{
				TipoActo lta_tipoActo;

				lta_tipoActo = new TipoActo();

				lta_tipoActo.setIdTipoActo(ls_idTipoActo);

				lta_tipoActo = iasr_antiguoSistemaRemote.findTipoActoById(lta_tipoActo);

				if(lta_tipoActo != null)
				{
					TipoActoCondicion ltac_tipoActoCondicion;

					ltac_tipoActoCondicion = getParametros();

					if(ltac_tipoActoCondicion != null)
						ltac_tipoActoCondicion.setVersion(NumericUtils.getInteger(lta_tipoActo.getVersion()));
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de TipoActoCondicion.
	 *
	 * @param ab_insertar            indica si se desea insertar o actualizar dependiendo su valor, si
	 *            su valor es true el inserta un nuevo registro, en cambio si su
	 *            valor es false realiza una actualizacion en la base de datos.
	 * @return devuelve el valor de String
	 */
	public String insertUpdateTipoActoCondicion(boolean ab_insertar)
	{
		String            ls_result;
		FacesContext      lfc_context;
		TipoActoCondicion lcr_TipoActoCondicionInsertUpdate;
		boolean           lb_focus;

		lcr_TipoActoCondicionInsertUpdate     = getParametros();
		lfc_context                           = FacesContext.getCurrentInstance();
		lb_focus                              = true;
		ls_result                             = null;

		try
		{
			{
				String ls_activo;
				ls_activo     = lcr_TipoActoCondicionInsertUpdate.getActivo();

				lb_focus = validateStyles(":fsiTipoActoCondicionDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.insertUpdateTipoActoCondicion(
			    lcr_TipoActoCondicionInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			ls_result = NavegacionCommon.TIPO_ACTO_CONDICION;

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
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}

	/**
	 * Método para poder guardar el código SQL.
	 *
	 * @param atac_tac correspondiente al valor del tipo de objeto TipoActoCondicion
	 */
	public void mostrarCodigoSQL(TipoActoCondicion atac_tac)
	{
		if(StringUtils.isValidString(atac_tac.getCodigoSql()))
		{
			setCodigoSql(atac_tac.getCodigoSql());
			PrimeFaces.current().executeScript("PF('codigoSQL').show();");
			PrimeFaces.current().ajax().update("fDialog:id_codigoSQL");
		}
		else
			addMessage(MessagesKeys.SIN_CODIGO_SQL);

		PrimeFaces.current().ajax().update("fsitipoActoCondicion:gtipoActoCondicionMsg");
	}
}
