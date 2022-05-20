package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de eventos e interacciones con la capa de presentación de la pantalla
 * paramétrica de círculos registrales.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanCirculosRegistrales")
@SessionScoped
public class BeanCirculosRegistrales extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6182868662927005293L;

	/** Propiedad icr circulo. */
	private CirculoRegistral icr_circulo;

	/** Propiedad iccr data circulos. */
	private Collection<CirculoRegistral> iccr_dataCirculos;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de circulo.
	 *
	 * @param acr_cr asigna el valor a la propiedad circulo
	 */
	public void setCirculo(CirculoRegistral acr_cr)
	{
		icr_circulo = acr_cr;
	}

	/**
	 * Retorna el valor de circulo.
	 *
	 * @return el valor de circulo
	 */
	public CirculoRegistral getCirculo()
	{
		return icr_circulo;
	}

	/**
	 * Modifica el valor de data circulos.
	 *
	 * @param accr_ccr asigna el valor a la propiedad data circulos
	 */
	public void setDataCirculos(Collection<CirculoRegistral> accr_ccr)
	{
		iccr_dataCirculos = accr_ccr;
	}

	/**
	 * Retorna el valor de data circulos.
	 *
	 * @return el valor de data circulos
	 */
	public Collection<CirculoRegistral> getDataCirculos()
	{
		if(iccr_dataCirculos == null)
			iccr_dataCirculos = new LinkedList<CirculoRegistral>();

		return iccr_dataCirculos;
	}

	/**
	 * Modifica el valor de insert.
	 *
	 * @param ab_b asigna el valor a la propiedad insert
	 */
	public void setInsert(boolean ab_b)
	{
		this.ib_insert = ab_b;
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
	 * Metodo para el manejo del jsf circulosRegistralesDetalle.
	 *
	 * @param acr_circuloSeleccionado es el objeto que se desea insertar o actualizar
	 * @param ab_proceso indica si la presentación de la página es de inserción o actualización
	 * @return Cadena de caracteres con el enlace a la página de detalle de círculo registral
	 */
	public String botonInsertar(CirculoRegistral acr_circuloSeleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			acr_circuloSeleccionado = new CirculoRegistral();

			setCirculo(acr_circuloSeleccionado);
			setInsert(true);
		}
		else
		{
			setCirculo(acr_circuloSeleccionado);
			setInsert(false);
		}

		ls_return = NavegacionCommon.CIRCULOS_REGISTRALES_DETALLE;

		return ls_return;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de círculos registrales.
	 *
	 * @return Colección de círculos registrales resultante de la consulta
	 */
	public Collection<CirculoRegistral> cargarCirculos()
	{
		Collection<CirculoRegistral> lccr_circulos;
		lccr_circulos = new LinkedList<CirculoRegistral>();

		try
		{
			lccr_circulos = ipr_parameterRemote.findAllCirculos(
				    false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataCirculos(lccr_circulos);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_circulos;
	}

	/**
	 * Metodo para traer las oficinas origen de la base de datos.
	 *
	 * @return Colección de oficinas origen resultante de la consulta
	 */
	public Collection<OficinaOrigen> cargarOficinasOrigen()
	{
		Collection<OficinaOrigen> lcoo_oficinas;
		lcoo_oficinas = new ArrayList<OficinaOrigen>();

		try
		{
			lcoo_oficinas = irr_referenceRemote.findAllOficinasOrigen(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcoo_oficinas;
	}

	/**
	 * Método que instancia los atributos circulo e insert.
	 */
	public void iniciar()
	{
		icr_circulo = new CirculoRegistral();
		setInsert(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de circulos registrales.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de círculos registrales.
	 */
	public String insertUpdateCirculo(boolean ab_insertar)
	{
		FacesContext     lfc_context;
		CirculoRegistral lcr_circuloInsertUpdate;
		boolean          lb_focus;

		lcr_circuloInsertUpdate     = getCirculo();
		lfc_context                 = FacesContext.getCurrentInstance();
		lb_focus                    = true;

		try
		{
			{
				String ls_idCirculo;
				ls_idCirculo     = lcr_circuloInsertUpdate.getIdCirculo();

				lb_focus = validateStyles(
					    ":fCirculosRegistralesDetalle:idItIdCirculo", lfc_context, ls_idCirculo, lb_focus
					);

				if(!StringUtils.isValidString(ls_idCirculo))
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_CIRCULO);
			}

			{
				String ls_nombreCirculo;
				ls_nombreCirculo     = lcr_circuloInsertUpdate.getNombre();

				lb_focus = validateStyles(
					    ":fCirculosRegistralesDetalle:idItNombreCirculo", lfc_context, ls_nombreCirculo, lb_focus
					);

				if(!StringUtils.isValidString(ls_nombreCirculo))
					throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_CIRCULO);
			}

			{
				String ls_cobraImpuesto;
				ls_cobraImpuesto     = lcr_circuloInsertUpdate.getCobraImpuesto();

				lb_focus = validateStyles(
					    ":fCirculosRegistralesDetalle:idItCobraImpuesto", lfc_context, ls_cobraImpuesto, lb_focus
					);

				if(!StringUtils.isValidString(ls_cobraImpuesto))
					throw new B2BException(ErrorKeys.ERROR_SIN_COBRA_IMPUESTO);
			}

			{
				BigDecimal lbd_ultimoIdMatricula;
				lbd_ultimoIdMatricula = lcr_circuloInsertUpdate.getUltimoIdMatricula();

				if(!NumericUtils.isValidBigDecimal(lbd_ultimoIdMatricula))
				{
					lb_focus = validateStyles(
						    ":fCirculosRegistralesDetalle:idItUltimoIdMatricula", lfc_context, "", lb_focus
						);
					throw new B2BException(ErrorKeys.ERROR_SIN_ULTIMO_ID_MATRICULA);
				}
			}

			{
				String ls_activo;
				ls_activo     = lcr_circuloInsertUpdate.getActivo();

				lb_focus = validateStyles(":fCirculosRegistralesDetalle:idItActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.insertUpdateCirculo(
			    lcr_circuloInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				BeanReference lbr_beanReference;
				lbr_beanReference = getBeanReference();

				lbr_beanReference.setCirculoRegistralActivoUsuario(null);
				lbr_beanReference.setCirculosRegistralesOrigenDestinoActivos(null);
				lbr_beanReference.setCirculoRegistral(null);
				lbr_beanReference.setTodosCirculosRegistrales(null);
			}

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fCirculosRegistrales:gCirculosMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			return null;
		}

		return NavegacionCommon.CIRCULOS_REGISTRALES;
	}
}
