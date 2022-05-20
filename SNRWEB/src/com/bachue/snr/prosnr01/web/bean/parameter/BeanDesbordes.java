package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Desborde;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Regional;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase para el manejo de eventos e interacciones con la capa de presentación de la pantalla
 * paramétrica de administración de desbordes.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanDesbordes")
@SessionScoped
public class BeanDesbordes extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6815973587710877132L;

	/** Propiedad icr circulo seleccionado. */
	private CirculoRegistral icr_circuloSeleccionado;

	/** Propiedad iccr circulos. */
	private Collection<CirculoRegistral> iccr_circulos;

	/** Propiedad iccr circulos por regional. */
	private Collection<CirculoRegistral> iccr_circulosPorRegional;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ir regional. */
	private Regional ir_regional;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de circulo seleccionado.
	 *
	 * @param acr_cr asigna el valor a la propiedad circulo seleccionado
	 */
	public void setCirculoSeleccionado(CirculoRegistral acr_cr)
	{
		icr_circuloSeleccionado = acr_cr;
	}

	/**
	 * Retorna el valor de circulo seleccionado.
	 *
	 * @return el valor de circulo seleccionado
	 */
	public CirculoRegistral getCirculoSeleccionado()
	{
		if(icr_circuloSeleccionado == null)
			icr_circuloSeleccionado = new CirculoRegistral();

		return icr_circuloSeleccionado;
	}

	/**
	 * Modifica el valor de circulos.
	 *
	 * @param accr_ccr asigna el valor a la propiedad circulos
	 */
	public void setCirculos(Collection<CirculoRegistral> accr_ccr)
	{
		iccr_circulos = accr_ccr;
	}

	/**
	 * Retorna el valor de circulos.
	 *
	 * @return el valor de circulos
	 */
	public Collection<CirculoRegistral> getCirculos()
	{
		return iccr_circulos;
	}

	/**
	 * Modifica el valor de circulos por regional.
	 *
	 * @param accr_ccr asigna el valor a la propiedad circulos por regional
	 */
	public void setCirculosPorRegional(Collection<CirculoRegistral> accr_ccr)
	{
		iccr_circulosPorRegional = accr_ccr;
	}

	/**
	 * Retorna el valor de circulos por regional.
	 *
	 * @return el valor de circulos por regional
	 */
	public Collection<CirculoRegistral> getCirculosPorRegional()
	{
		return iccr_circulosPorRegional;
	}

	/**
	 * Modifica el valor de regional.
	 *
	 * @param ar_r asigna el valor a la propiedad regional
	 */
	public void setRegional(Regional ar_r)
	{
		ir_regional = ar_r;
	}

	/**
	 * Retorna el valor de regional.
	 *
	 * @return el valor de regional
	 */
	public Regional getRegional()
	{
		return ir_regional;
	}

	/**
	 * Busca en la base de datos todos los círculos registrales que se encuentren activos.
	 */
	public void cargarCirculosRegistrales()
	{
		try
		{
			setCirculos(
			    irr_referenceRemote.findAllCirculosRegistralesActivos(
			        true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Obtiene el regional de un círculo registral seleccionado, ademas de buscar todos los círculos
	 * registrales que se encuentren relacionados a ese regional.
	 */
	public void cargarRegional()
	{
		try
		{
			CirculoRegistral lcr_circuloRegistral;

			lcr_circuloRegistral     = getCirculoSeleccionado();

			lcr_circuloRegistral = ipr_parameterRemote.findCirculoRegistralById(
				    lcr_circuloRegistral, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lcr_circuloRegistral != null)
			{
				setCirculoSeleccionado(lcr_circuloRegistral);

				Regional lr_regional;
				lr_regional = new Regional();

				lr_regional.setIdRegional(lcr_circuloRegistral.getIdRegional());

				setRegional(
				    irr_referenceRemote.findRegionalById(
				        lr_regional, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);

				setCirculosPorRegional(
				    irr_referenceRemote.findAllCirculosRegistralesByRegional(
				        lcr_circuloRegistral, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Hace un reset a las variables involucradas en el proceso de administración de desbordes.
	 */
	public void clean()
	{
		setCirculoSeleccionado(null);
		setCirculosPorRegional(null);
		setRegional(null);
	}

	/**
	 * Crea una colección de objetos desborde en base a las modificaciones realizadas en pantalla a los círculos
	 * registrales y las guarda en la base de datos.
	 */
	public void salvar()
	{
		try
		{
			Collection<Desborde>         lcd_desbordes;
			Collection<CirculoRegistral> lccr_circulos;
			CirculoRegistral             lcr_circuloSeleccionado;

			lcd_desbordes               = new LinkedList<Desborde>();
			lccr_circulos               = getCirculosPorRegional();
			lcr_circuloSeleccionado     = getCirculoSeleccionado();

			if((lcr_circuloSeleccionado != null) && CollectionUtils.isValidCollection(lccr_circulos))
			{
				Desborde ld_desborde;

				for(CirculoRegistral lcr_data : lccr_circulos)
				{
					ld_desborde = new Desborde();

					ld_desborde.setIdCirculoOrigen(lcr_circuloSeleccionado.getIdCirculo());
					ld_desborde.setIdCirculoDesborde(lcr_data.getIdCirculo());
					ld_desborde.setOrden(NumericUtils.getBigDecimal(NumericUtils.getLong(lcr_data.getOrden())));
					ld_desborde.setHabilitada(lcr_data.getHabilitado());
					ld_desborde.setObservaciones(lcr_data.getObservaciones());

					lcd_desbordes.add(ld_desborde);
				}
			}

			ipr_parameterRemote.insertUpdateDesbordes(
			    lcd_desbordes, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			clean();

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fDesbordes:gDesbordesMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}
}
