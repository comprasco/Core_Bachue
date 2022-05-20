package com.bachue.snr.prosnr01.web.bean.digitalizacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.digitalizacion.DigitalizacionRemote;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanTurnos;

import org.primefaces.PrimeFaces;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanLineaDeProduccion.
 *
 * @author hcastaneda
 */
@ManagedBean(name = "beanLineaDeProduccion")
@SessionScoped
public class BeanLineaDeProduccion extends BeanTurnos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5665657374824226223L;

	/** Propiedad ictc datos digitalizacion. */
	private Collection<TramiteCantidad> ictc_datosDigitalizacion;

	/** Propiedad idr digitalizacion remote. */
	@EJB
	private DigitalizacionRemote idr_digitalizacionRemote;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir consulta. */
	private String is_nirConsulta;

	/**
	 * Modifica el valor de datos digitalizacion.
	 *
	 * @param actc_tc asigna el valor a la propiedad datos digitalizacion
	 */
	public void setDatosDigitalizacion(Collection<TramiteCantidad> actc_tc)
	{
		ictc_datosDigitalizacion = actc_tc;
	}

	/**
	 * Retorna el valor de datos digitalizacion.
	 *
	 * @return el valor de datos digitalizacion
	 */
	public Collection<TramiteCantidad> getDatosDigitalizacion()
	{
		return ictc_datosDigitalizacion;
	}

	/** {@inheritdoc} */
	public void setIdTurnoConsulta(String as_s)
	{
		is_idTurnoConsulta = as_s;
	}

	/** {@inheritdoc} */
	public String getIdTurnoConsulta()
	{
		return is_idTurnoConsulta;
	}

	/** {@inheritdoc} */
	public void setNirConsulta(String as_s)
	{
		is_nirConsulta = as_s;
	}

	/** {@inheritdoc} */
	public String getNirConsulta()
	{
		return is_nirConsulta;
	}

	/**
	 * Metodo encargado de actualizar el estado de la digitalización y de abrir el hipervínculo de CAPTURE.
	 * @param atc_parametros
	 */
	public void actualizarEstadoDigitalizacion(TramiteCantidad atc_parametros)
	{
		if(atc_parametros != null)
		{
			try
			{
				PrimeFaces.current().executeScript(
				    "window.open(href ='" + atc_parametros.getUrlDigitalizacion() + "')"
				);

				{
					TurnoHistoria lth_parametros;

					lth_parametros = new TurnoHistoria();

					lth_parametros.setIdTurnoHistoria(NumericUtils.getLongWrapper(atc_parametros.getIdTurnoHistoria()));
					lth_parametros.setCalificacion(EstadoCommon.EN_PROCESO);

					idr_digitalizacionRemote.actualizarEstadoDigitalizacion(
					    lth_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					realizarConsulta();
					actualizarComponente("fLineaDeProduccion:idDtTurnos");
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				actualizarComponente("fLineaDeProduccion:idGrowl");
			}
		}
	}

	/**
	 * Metodo para limpiar variables.
	 */
	public void clean()
	{
		setIdTurnoConsulta(null);
		setNirConsulta(null);
	}

	/**
	 * Meotodo para consultar la informacion y llenar la tabla de digitalizacion.
	 */
	public void realizarConsulta()
	{
		try
		{
			TramiteCantidad ltc_tc;
			Turno           lt_t;

			lt_t = new Turno();

			lt_t.setNir(getNirConsulta());
			lt_t.setIdUsuarioCreacion(getUserId());
			lt_t.setIdTurno(getIdTurnoConsulta());
			lt_t.setIdEtapaActual(NumericUtils.getLongWrapper(getIdEtapa()));

			ltc_tc = idr_digitalizacionRemote.findTurnos(lt_t,getUserId(),getLocalIpAddress(),getRemoteIpAddress());

			if(ltc_tc != null)
			{
				setDatosDigitalizacion(ltc_tc.getTurnos());

				if(!CollectionUtils.isValidCollection(getDatosDigitalizacion()))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
				else
				{
					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
					PrimeFaces.current().ajax().update("fLineaDeProduccion:idGrowl");
				}
			}
			else
			{
				setDatosDigitalizacion(null);
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fLineaDeProduccion:idGrowl");
		}
	}
	
	/**
	 * Generar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PieChartModel generarTorta()
	    throws B2BException
	{
		return getModeloTorta();
	}

	/**
	 * Mostrar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean mostrarTorta()
	    throws B2BException
	{
		return isMostrarGrafica();
	}

	/**
	 * Item seleccionado.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSeleccionado(ItemSelectEvent event)
	{
		itemSelect(event);
	}
}
