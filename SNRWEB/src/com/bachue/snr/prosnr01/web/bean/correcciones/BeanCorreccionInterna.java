package com.bachue.snr.prosnr01.web.bean.correcciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.grabacion.GrabacionRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;

import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCorreccionInterna.
 *
 * @author garias
 */
@ManagedBean(name = "beanCorreccionInterna")
@SessionScoped
public class BeanCorreccionInterna extends BeanRegistro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3064308299591704353L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fCorreccionInterna:idGrowl";

	/** Constante is_idForm. */
	private static final String is_idForm = "fCorreccionInterna:";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCorreccionInterna.class);

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad iccc correcciones solicitud calificacion. */
	private Collection<CausalCorreccion> iccc_correccionesSolicitudCalificacion;

	/** Propiedad igr grabacion remote. */
	@EJB
	private GrabacionRemote igr_grabacionRemote;

	/** Propiedad ism matricula correccion calificacion. */
	private SolicitudMatricula ism_matriculaCorreccionCalificacion;

	/**
	 * Modifica el valor de correcciones solicitud calificacion.
	 *
	 * @param accc_csc asigna el valor a la propiedad correcciones solicitud calificacion
	 */
	public void setCorreccionesSolicitudCalificacion(Collection<CausalCorreccion> accc_csc)
	{
		iccc_correccionesSolicitudCalificacion = accc_csc;
	}

	/**
	 * Retorna el valor de correcciones solicitud calificacion.
	 *
	 * @return el valor de correcciones solicitud calificacion
	 */
	public Collection<CausalCorreccion> getCorreccionesSolicitudCalificacion()
	{
		if(!CollectionUtils.isValidCollection(iccc_correccionesSolicitudCalificacion))
			iccc_correccionesSolicitudCalificacion = new LinkedList<CausalCorreccion>();

		return iccc_correccionesSolicitudCalificacion;
	}

	/**
	 * Consulta la información del usuario calificador y lo almacena en la solicitud.
	 *
	 */
	public void getDatosUsuarioCalificador()
	{
		try
		{
			Persona lp_persona;

			lp_persona = irr_calificacionRemote.getDatosUsuarioCalificador(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lp_persona == null)
				lp_persona = new Persona();

			setPersona(lp_persona);

			TurnoHistoria lth_temp;
			lth_temp = new TurnoHistoria();

			lth_temp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			Solicitud ls_temp;
			ls_temp = irr_calificacionRemote.findSolicitudByTH(
				    lth_temp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ls_temp != null)
			{
				setSolicitud(ls_temp);
				salvarInteresado();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("getDatosUsuarioCalificador", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Modifica el valor de matricula correccion calificacion.
	 *
	 * @param asm_sm asigna el valor a la propiedad matricula correccion calificacion
	 */
	public void setMatriculaCorreccionCalificacion(SolicitudMatricula asm_sm)
	{
		ism_matriculaCorreccionCalificacion = asm_sm;
	}

	/**
	 * Retorna el valor de matricula correccion calificacion.
	 *
	 * @return el valor de matricula correccion calificacion
	 */
	public SolicitudMatricula getMatriculaCorreccionCalificacion()
	{
		if(ism_matriculaCorreccionCalificacion == null)
			ism_matriculaCorreccionCalificacion = new SolicitudMatricula();

		return ism_matriculaCorreccionCalificacion;
	}

	/**
	 * Retorna al detalle del turno.
	 *
	 * @return Enlace a la pantalla de detalle
	 */
	public String accionRegresarCalificacion()
	{
		String ls_return;

		if(isCorreccionesParaGrabacion())
			ls_return = NavegacionCommon.DETALLE_GRABACION;
		else
			ls_return = NavegacionCommon.DETALLE_ACTO;

		return ls_return;
	}

	/**
	 * Aplica los cambios de correcciones seleccionados a una matrícula, a las demas matrículas que tenga relacionadas
	 * el turno en tramite.
	 */
	public void aplicarCorreccionesGeneral()
	{
		try
		{
			Collection<SolicitudMatricula> lcsm_matriculas;
			Solicitud                      ls_solicitudNueva;

			lcsm_matriculas       = findMatriculasCorreccion();
			ls_solicitudNueva     = getSolicitudCorreccionCalificacion();

			if(CollectionUtils.isValidCollection(lcsm_matriculas) && (ls_solicitudNueva != null))
			{
				SolicitudMatricula           lsm_solMat;
				String                       ls_idSolicitud;
				Collection<CausalCorreccion> lccc_causales;
				String                       ls_idCirculoMatYaInsertada;
				long                         ll_idMatriculaYaInsertada;

				lsm_solMat                     = getMatriculaCorreccionCalificacion();
				ls_idSolicitud                 = ls_solicitudNueva.getIdSolicitud();
				lccc_causales                  = getCorreccionesSolicitudCalificacion();
				ls_idCirculoMatYaInsertada     = null;
				ll_idMatriculaYaInsertada      = 0L;

				if(lsm_solMat != null)
				{
					ls_idCirculoMatYaInsertada     = StringUtils.getStringNotNull(lsm_solMat.getIdCirculo());
					ll_idMatriculaYaInsertada      = lsm_solMat.getIdMatricula();
				}

				for(SolicitudMatricula lsm_matricula : lcsm_matriculas)
				{
					if(lsm_matricula != null)
					{
						String ls_idCirTmp;
						long   ll_idMatTmp;

						ls_idCirTmp     = StringUtils.getStringNotNull(lsm_matricula.getIdCirculo());
						ll_idMatTmp     = lsm_matricula.getIdMatricula();

						if(
						    !ls_idCirTmp.equals(ls_idCirculoMatYaInsertada)
							    || (ll_idMatriculaYaInsertada != ll_idMatTmp)
						)
						{
							lsm_matricula.setIdSolicitud(ls_idSolicitud);

							irr_calificacionRemote.saveCausalesMatriculaCorreccion(
							    lccc_causales, lsm_matricula, getIdTurno(), getUserId(), getLocalIpAddress(),
							    getRemoteIpAddress()
							);
						}
					}
				}

				addMessage(MessagesKeys.CAMBIOS_APLICADOS_CORRECCIONES);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Carga los tipos de causal de correccion para una matrícula específica.
	 *
	 * @param asm_solMat Objeto contenedor de la matrícula a cargar sus detalles
	 */
	public void cargarTablaCorrecciones(SolicitudMatricula asm_solMat)
	{
		if(asm_solMat != null)
		{
			try
			{
				Solicitud ls_solicitud;
				String    ls_solicitudNueva;

				ls_solicitud          = getSolicitudCorreccionCalificacion();
				ls_solicitudNueva     = null;

				if(ls_solicitud != null)
					ls_solicitudNueva = ls_solicitud.getIdSolicitud();

				asm_solMat.setIdSolicitud(ls_solicitudNueva);

				setMatriculaCorreccionCalificacion(asm_solMat);

				setCorreccionesSolicitudCalificacion(
				    irr_calificacionRemote.findCausalesCorreccionCalificacion(
				        asm_solMat, ls_solicitudNueva, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);

				PrimeFaces.current().executeScript("PF('causalesCorreccion').show();");
				PrimeFaces.current().ajax().update("idDCausalesCorreccion");
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Limpia las variables de instancia de la clase.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void clean()
	    throws B2BException
	{
		setCorreccionesSolicitudCalificacion(null);
		setMatriculaCorreccionCalificacion(null);
		limpiarRegistro();
	}

	/**
	 * Consulta las matriculas asociadas a la solciitud para el proceso de correcciones.
	 *
	 * @return Colección de matrículas resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<SolicitudMatricula> findMatriculasCorreccion()
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_matriculas;

		lcsm_matriculas = null;

		try
		{
			String ls_idTurnoHistoria;

			ls_idTurnoHistoria = getIdTurnoHistoria();

			if(StringUtils.isValidString(ls_idTurnoHistoria))
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = NumericUtils.getLongWrapper(ls_idTurnoHistoria);

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria lth_temp;

					lth_temp = new TurnoHistoria();

					lth_temp.setIdTurnoHistoria(ll_idTurnoHistoria);

					if(isCorreccionesParaGrabacion())
					{
						DatosAntSistema ldas_datosMatr;

						ldas_datosMatr = igr_grabacionRemote.consultarPredioGrabar(
							    lth_temp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(ldas_datosMatr != null)
						{
							SolicitudMatricula lsm_solMat;

							lsm_solMat          = new SolicitudMatricula();
							lcsm_matriculas     = new LinkedList<SolicitudMatricula>();

							lsm_solMat.setIdSolicitud(ldas_datosMatr.getIdSolicitud());
							lsm_solMat.setIdDatosAntSistema(ldas_datosMatr.getIdDatosAntSistema());
							lsm_solMat.setIdCirculo(ldas_datosMatr.getIdCirculoGrabacion());
							lsm_solMat.setIdMatricula(NumericUtils.getLong(ldas_datosMatr.getIdMatriculaGrabacion()));

							lcsm_matriculas.add(lsm_solMat);
						}
					}
					else
						lcsm_matriculas = irr_calificacionRemote.findSolicitudMatriculasTH(
							    lth_temp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			throw lb2be_e;
		}

		return lcsm_matriculas;
	}

	/**
	 * Guarda en base de datos las selecciones aplicadas a los tipos de causal corrección disponibles.
	 */
	public void guardarCausalesMatriculaCorreccion()
	{
		try
		{
			Collection<CausalCorreccion> lccc_causales;

			lccc_causales = getCorreccionesSolicitudCalificacion();

			if(CollectionUtils.isValidCollection(lccc_causales))
			{
				SolicitudMatricula lsm_matriculaSeleccionada;
				Solicitud          ls_solicitudNueva;

				lsm_matriculaSeleccionada     = getMatriculaCorreccionCalificacion();
				ls_solicitudNueva             = getSolicitudCorreccionCalificacion();

				if((lsm_matriculaSeleccionada != null) && (ls_solicitudNueva != null))
				{
					boolean                    lb_validacionChecks;
					Collection<String>         lcs_causales;
					Iterator<CausalCorreccion> licc_iterador;
					PrimeFaces                 lpf_primeFaces;
					Ajax                       la_ajax;

					lb_validacionChecks     = false;
					lcs_causales            = new ArrayList<String>();
					licc_iterador           = lccc_causales.iterator();
					lpf_primeFaces          = PrimeFaces.current();
					la_ajax                 = lpf_primeFaces.ajax();

					while(licc_iterador.hasNext())
					{
						CausalCorreccion lcc_data;

						lcc_data = licc_iterador.next();

						if((lcc_data != null) && lcc_data.isSeleccionado())
						{
							String ls_observaciones;

							ls_observaciones = lcc_data.getObservaciones();

							if(!StringUtils.isValidString(ls_observaciones))
								lcs_causales.add(lcc_data.getNombre());
							else
								lb_validacionChecks = true;
						}
					}

					if(CollectionUtils.isValidCollection(lcs_causales))
					{
						Iterator<String> lis_terator;
						StringBuilder    lsb_sb;

						lis_terator     = lcs_causales.iterator();
						lsb_sb          = new StringBuilder();

						while(lis_terator.hasNext())
						{
							String ls_iterador;

							ls_iterador = lis_terator.next();

							if(StringUtils.isValidString(ls_iterador))
							{
								lsb_sb.append(IdentificadoresCommon.ESPACIO_VACIO);
								lsb_sb.append(ls_iterador);

								if(lis_terator.hasNext())
									lsb_sb.append(IdentificadoresCommon.SIMBOLO_COMA);
							}
						}

						Object[] loa_messageArgs = new String[1];
						loa_messageArgs[0] = lsb_sb.toString();

						throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_CAUSAL, loa_messageArgs);
					}

					if(!lb_validacionChecks)
						throw new B2BException(ErrorKeys.ERROR_SIN_CAUSAL_CORRECCION_MATRICULA);

					lsm_matriculaSeleccionada.setIdSolicitud(ls_solicitudNueva.getIdSolicitud());

					irr_calificacionRemote.saveCausalesMatriculaCorreccion(
					    getCorreccionesSolicitudCalificacion(), lsm_matriculaSeleccionada, getIdTurno(), getUserId(),
					    getLocalIpAddress(), getRemoteIpAddress()
					);

					lpf_primeFaces.executeScript("PF('causalesCorreccion').hide();");
					la_ajax.update("idDCausalesCorreccion");

					addMessage(MessagesKeys.PROCESO_COMPLETADO);
					la_ajax.update(is_messageIdGrowl);

					setValidacionCorreccionSeleccionada(true);

					verificarCantidadMatriculas();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Termina el proceso en tramite.
	 */
	public void terminarProcesoCorrecciones()
	{
		try
		{
			if(isCorreccionesParaGrabacion())
			{
				String ls_observaciones;

				ls_observaciones = getObservacionesProcesoCorrecciones();

				validateStyles(
				    is_idForm + "idITAObservaciones", FacesContext.getCurrentInstance(), ls_observaciones, true
				);

				if(!StringUtils.isValidString(ls_observaciones))
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_CALIFICADOR);
			}

			terminarProcesos();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("terminarProceso", lb2be_e);
			addMessage(lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("terminarProceso", le_e);
			addMessage(new B2BException(le_e.getMessage()));
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);
	}

	/**
	 * Revisa si la cantidad de matrículas relacionadas al turno es mayor a una, y de cumplirse esto,
	 * muestra la opción de permitir aplicar los mismos cambios a las demas matrículas.
	 *
	 * @throws B2BException Si ocurre un error en base de datos al consultar las matrículas
	 */
	private void verificarCantidadMatriculas()
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_matriculas;

		lcsm_matriculas = findMatriculasCorreccion();

		if(
		    CollectionUtils.isValidCollection(lcsm_matriculas) && (lcsm_matriculas.size() > 1)
			    && !isValidAplicacionCorreccionesGeneral()
		)
		{
			setValidAplicacionCorreccionesGeneral(true);
			PrimeFaces.current().executeScript("PF('preguntaCorreccionGeneral').show();");
			PrimeFaces.current().ajax().update("idDPreguntaCorreccionGeneral");
		}
	}
}
