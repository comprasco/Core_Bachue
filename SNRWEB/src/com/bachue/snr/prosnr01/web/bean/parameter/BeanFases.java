package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de eventos e interacciones con la capa de presentación de la pantalla
 * paramétrica de fases.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanFases")
@SessionScoped
public class BeanFases extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4465214610155827248L;

	/** Propiedad iccr data fases. */
	private Collection<Fases> iccr_dataFases;

	/** Propiedad ie fases. */
	private Fases ie_fases;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de data fases.
	 *
	 * @param accr_ccr asigna el valor a la propiedad data fases
	 */
	public void setDataFases(Collection<Fases> accr_ccr)
	{
		iccr_dataFases = accr_ccr;
	}

	/**
	 * Retorna el valor de data fases.
	 *
	 * @return el valor de data fases
	 */
	public Collection<Fases> getDataFases()
	{
		if(iccr_dataFases == null)
			iccr_dataFases = new LinkedList<Fases>();

		return iccr_dataFases;
	}

	/**
	 * Modifica el valor de fases.
	 *
	 * @param acr_cr asigna el valor a la propiedad fases
	 */
	public void setFases(Fases acr_cr)
	{
		ie_fases = acr_cr;
	}

	/**
	 * Retorna el valor de fases.
	 *
	 * @return el valor de fases
	 */
	public Fases getFases()
	{
		return ie_fases;
	}

	/**
	 * Modifica el valor de insert.
	 *
	 * @param insert asigna el valor a la propiedad insert
	 */
	public void setInsert(boolean insert)
	{
		this.ib_insert = insert;
	}

	/**
	 * Valida la propiedad insert.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insert
	 */
	public boolean isInsert()
	{
		return ib_insert;
	}

	/**
	 * Metodo para  cambiar estado con el fin de saber si se desea insertar una fase.
	 *
	 * @param acr_Faseseleccionado es el objeto seleccionado
	 * @param ab_proceso indica si se va a insertar o a modificar
	 * @return Cadena de caracteres con el enlace a la página de detalle de fases
	 */
	public String botonInsertar(Fases acr_Faseseleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			acr_Faseseleccionado = new Fases();

			setFases(acr_Faseseleccionado);
			setInsert(true);
		}
		else
		{
			setFases(acr_Faseseleccionado);
			setInsert(false);
		}

		ls_return = NavegacionCommon.FASES_DETALLE;

		return ls_return;
	}

	/**
	 * Metodo para traer de la base de datos todos los registros existentes de la tabla SDB_PGN_FASES.
	 *
	 * @return Colección de fases resultante de la consulta
	 */
	public Collection<Fases> cargarFases()
	{
		Collection<Fases> lccr_Fases;
		lccr_Fases = new LinkedList<Fases>();

		try
		{
			lccr_Fases = ipr_parameterRemote.findAllFases(getUserId(), getLocalIpAddress(), getRemoteIpAddress());

			setDataFases(lccr_Fases);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_Fases;
	}

	/**
	 * Metodo para buscar todos los registros de la tabla SDB_PGN_FASES.
	 *
	 * @return Colección de fases resultante de la consulta
	 */
	public Collection<Fases> findAllFases()
	{
		Collection<Fases> lcf_datos;
		lcf_datos = null;

		try
		{
			lcf_datos = ipr_parameterRemote.findAllFases(getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcf_datos;
	}

	/**
	 * Método que instancia algunos atributos de la clase.
	 */
	public void iniciar()
	{
		ie_fases = new Fases();
		setInsert(false);
	}

	/**
	 * Metodo para insertar o modificar en la base de datos para la tabla SDB_PGN_FASES.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de fases.
	 */
	public String insertUpdateFases(boolean ab_insertar)
	{
		FacesContext lfc_context;
		boolean      lb_focus;
		Fases        lcr_FasesInsertUpdate;

		lcr_FasesInsertUpdate     = getFases();
		lfc_context               = FacesContext.getCurrentInstance();
		lb_focus                  = true;

		try
		{
			{
				long ls_idFase;
				ls_idFase = lcr_FasesInsertUpdate.getIdFase();

				if(ls_idFase <= 0)
				{
					lb_focus = validateStyles(":fFasesDetalle:idItIdFases", lfc_context, "", lb_focus);
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_FASE);
				}
				else
					lb_focus = validateStyles(
						    ":fFasesDetalle:idItIdFases", lfc_context, NumericUtils.getLongWrapper(ls_idFase), lb_focus
						);
			}

			{
				String ls_nombreFases;
				ls_nombreFases     = lcr_FasesInsertUpdate.getNombre();

				lb_focus = validateStyles(":fFasesDetalle:idItNombreFases", lfc_context, ls_nombreFases, lb_focus);

				if(!StringUtils.isValidString(ls_nombreFases))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
			}

			{
				String ls_descripcion;
				ls_descripcion     = lcr_FasesInsertUpdate.getDescripcion();

				lb_focus = validateStyles(":fFasesDetalle:idItDescripcion", lfc_context, ls_descripcion, lb_focus);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
			}

			{
				String ls_activo;
				ls_activo     = lcr_FasesInsertUpdate.getEstado();

				lb_focus = validateStyles(":fFasesDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.insertUpdateFase(
			    lcr_FasesInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fFases:gFasesMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			return null;
		}

		return NavegacionCommon.FASES;
	}
}
