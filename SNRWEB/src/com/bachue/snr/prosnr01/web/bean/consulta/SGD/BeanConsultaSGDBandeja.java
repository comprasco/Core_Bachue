package com.bachue.snr.prosnr01.web.bean.consulta.SGD;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.OperadorCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.SGD.ConsultaSGDRemote;

import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;

import com.bachue.snr.prosnr01.web.bean.consulta.trazabilidad.BeanConsultaTrazabilidad;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase creada para el manejo de eventos en el proceso de consulta al SGD
 *
 * @author jpatino
 *
 */
@ManagedBean(name = "beanConsultaSGDBandeja")
@SessionScoped
public class BeanConsultaSGDBandeja extends BeanConsultaTrazabilidad implements Serializable
{
	/** Constante que determina el valor serializable */
	private static final long serialVersionUID = -2038438423669632075L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaSGDBandeja.class);

	/** Atributo irr_consultaSGDRemote */
	@EJB
	private ConsultaSGDRemote irr_consultaSGDRemote;

	/** Atributo il_matricula */
	private Long il_matricula;

	/** Atributo is_circulo */
	private String is_circulo;

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setCirculo(String as_s)
	{
		is_circulo = as_s;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public String getCirculo()
	{
		return is_circulo;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setMatricula(Long al_l)
	{
		il_matricula = al_l;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public Long getMatricula()
	{
		return il_matricula;
	}

	/**
	 * Método encargado de limpiar los datos ingresados en pantalla.
	 */
	public void clear()
	{
		setCirculo(null);
		setMatricula(null);
		super.clear();
	}

	/**
	 * Método encargado de consultar el gestor documental por nir.
	 *
	 * @param at_row Argumento de tipo <code>Trazabilidad</code> que contiene los criterios necesarios para realizar la consulta.
	 *
	 * @return Regla de navegación que determina hacia donde redirecciona la pantalla.
	 */
	public String consultaSGD(Trazabilidad at_row)
	{
		String ls_return;

		ls_return = null;

		if(at_row != null)
		{
			try
			{
				boolean lb_nir;

				lb_nir = StringUtils.isValidString(getNir());

				if(lb_nir)
				{
					Solicitud ls_solicitud;

					ls_solicitud = at_row.getSolicitud();

					setNir((ls_solicitud != null) ? ls_solicitud.getNir() : null);
				}
				else
				{
					Turno lt_turno;

					lt_turno = at_row.getTurno();

					setIdTurnoConsulta((lt_turno != null) ? lt_turno.getIdTurno() : null);
				}

				ls_return = consultaSGD(OperadorCommon.AND);

				{
					BeanConsultaSGD lbcs_bean;

					lbcs_bean = (BeanConsultaSGD)obtenerInstanciaBean(
						    BeanConsultaSGD.class, BeanNameCommon.BEAN_CONSULTA_SGD
						);

					if(lbcs_bean != null) {
						lbcs_bean.setPantallaAnterior(NavegacionCommon.CONSULTA_SGD_BANDEJA);
						lbcs_bean.setNewTab(false);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				clh_LOGGER.error("consultaSGD", lb2be_e);
			}
		}

		return ls_return;
	}

	/**
	 * Método encargado de consultar todos los registros de la trazabilidad por id matrícula y id círculo.
	 */
	public void findAll()
	{
		try
		{
			String       ls_idCirculo;
			Long         ll_idMatricula;
			FacesContext lfc_context;
			boolean      lb_focus;

			ls_idCirculo       = getCirculo();
			ll_idMatricula     = getMatricula();
			lfc_context        = FacesContext.getCurrentInstance();
			lb_focus           = false;

			validateStyles("fBandejaSGD:itMatricula", lfc_context, new Long(0), lb_focus);
			validateStyles("fBandejaSGD:itCirculo", lfc_context, IdentificadoresCommon.DATO_VALIDO, lb_focus);

			if(StringUtils.isValidString(ls_idCirculo))
			{
				if(NumericUtils.isValidLong(ll_idMatricula))
					setTrazabilidad(
					    irr_consultaSGDRemote.consultaPorMatriculaYCirculo(
					        new ConsultaPredio(ls_idCirculo, ll_idMatricula), getUserId(), getLocalIpAddress(),
					        getRemoteIpAddress()
					    )
					);
				else
				{
					validateStyles("fBandejaSGD:itMatricula", lfc_context, ll_idMatricula, lb_focus);
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);
				}
			}
			else if(NumericUtils.isValidLong(ll_idMatricula))
			{
				validateStyles("fBandejaSGD:itCirculo", lfc_context, ls_idCirculo, lb_focus);
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
			}
			else
				super.findAll();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("findAll", lb2be_e);
		}
	}
}
