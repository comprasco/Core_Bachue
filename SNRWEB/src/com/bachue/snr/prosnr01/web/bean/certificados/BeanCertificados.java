package com.bachue.snr.prosnr01.web.bean.certificados;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.LibroAntSistemaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRequiereMatriculaCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.ejb.session.stateless.certificados.CertificadosRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.certificados.Certificados;
import com.bachue.snr.prosnr01.model.registro.NuevaEntrada;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudDireccionCertificado;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;

import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;
import com.bachue.snr.prosnr01.web.util.DatosDelPredio;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;

import org.primefaces.PrimeFaces;

import org.primefaces.component.tabview.TabView;

import org.primefaces.event.FlowEvent;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades BeanCertificados.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 30/07/2020
 */
@ManagedBean(name = "beanCertificados")
@SessionScoped
public class BeanCertificados extends BeanRegistro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1902639635381084312L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCertificados.class);

	/** Constante is_idForm. */
	public static final String is_idForm = "fCertificados:";

	/** Constante is_idTabDatosPredio. */
	private static final String is_idTabDatosPredio = "idTVDatosPredio:";

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fCertificados:globalMsg";

	/** Propiedad icr certificados remote. */
	@EJB
	private CertificadosRemote icr_certificadosRemote;

	/** Propiedad icm municipios. */
	private Collection<Municipio> icm_municipios;

	/** Propiedad ics subprocesos certificados. */
	private Collection<Subproceso> ics_subprocesosCertificados;

	/** Propiedad icth turnos relacionados nueva entrada. */
	private Collection<TurnoHistoria> icth_turnosRelacionadosNuevaEntrada;

	/** Propiedad icv veredas. */
	private Collection<Vereda> icv_veredas;

	/** Propiedad ip datos identificacion. */
	private Persona ip_datosIdentificacion;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isdc direccion certificado. */
	private SolicitudDireccionCertificado isdc_direccionCertificado;

	/** Propiedad is nombre tooltip subproceso. */
	private String is_nombreTooltipSubproceso;

	/** Propiedad is respuesta nueva entrada. */
	private String is_respuestaNuevaEntrada;

	/** Propiedad ib bloqueos nueva entrada. */
	private boolean ib_bloqueosNuevaEntrada;

	/** Propiedad ib circulo matricula obligatorio. */
	private boolean ib_circuloMatriculaObligatorio;

	/** Propiedad ib habilitar razon social. */
	private boolean ib_habilitarRazonSocial;

	/** Propiedad ib requiere codigo catastral en solicitud. */
	private boolean ib_requiereCodigoCatastralEnSolicitud;

	/** Propiedad ib requiere datos ant sistema. */
	private boolean ib_requiereDatosAntSistema;

	/**
	 * Modifica el valor de BloqueosNuevaEntrada.
	 *
	 * @param ab_b asigna el valor a la propiedad BloqueosNuevaEntrada
	 */
	public void setBloqueosNuevaEntrada(boolean ab_b)
	{
		ib_bloqueosNuevaEntrada = ab_b;
	}

	/**
	 * Valida la propiedad BloqueosNuevaEntrada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en BloqueosNuevaEntrada
	 */
	public boolean isBloqueosNuevaEntrada()
	{
		return ib_bloqueosNuevaEntrada;
	}

	/**
	 * Modifica el valor de circulo matricula obligatorio.
	 *
	 * @param ab_b asigna el valor a la propiedad circulo matricula obligatorio
	 */
	public void setCirculoMatriculaObligatorio(boolean ab_b)
	{
		ib_circuloMatriculaObligatorio = ab_b;
	}

	/**
	 * Valida la propiedad circulo matricula obligatorio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en circulo matricula obligatorio
	 */
	public boolean isCirculoMatriculaObligatorio()
	{
		return ib_circuloMatriculaObligatorio;
	}

	/**
	 * Modifica el valor de datos identificacion.
	 *
	 * @param ap_p asigna el valor a la propiedad datos identificacion
	 */
	public void setDatosIdentificacion(Persona ap_p)
	{
		ip_datosIdentificacion = ap_p;
	}

	/**
	 * Retorna el valor de datos identificacion.
	 *
	 * @return el valor de datos identificacion
	 */
	public Persona getDatosIdentificacion()
	{
		if(ip_datosIdentificacion == null)
			ip_datosIdentificacion = new Persona();

		return ip_datosIdentificacion;
	}

	/**
	 * Modifica el valor de direccion certificado.
	 *
	 * @param asdc_sdc asigna el valor a la propiedad direccion certificado
	 */
	public void setDireccionCertificado(SolicitudDireccionCertificado asdc_sdc)
	{
		isdc_direccionCertificado = asdc_sdc;
	}

	/**
	 * Retorna el valor de direccion certificado.
	 *
	 * @return el valor de direccion certificado
	 */
	public SolicitudDireccionCertificado getDireccionCertificado()
	{
		if(isdc_direccionCertificado == null)
		{
			isdc_direccionCertificado = new SolicitudDireccionCertificado();

			isdc_direccionCertificado.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return isdc_direccionCertificado;
	}

	/**
	 * Modifica el valor de habilitar razon social.
	 *
	 * @param habilitarRazonSocial asigna el valor a la propiedad habilitar razon social
	 */
	public void setHabilitarRazonSocial(boolean habilitarRazonSocial)
	{
		this.ib_habilitarRazonSocial = habilitarRazonSocial;
	}

	/**
	 * Valida la propiedad habilitar razon social.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar razon social
	 */
	public boolean isHabilitarRazonSocial()
	{
		return ib_habilitarRazonSocial;
	}

	/**
	 * Modifica el valor de municipios.
	 *
	 * @param acm_cm asigna el valor a la propiedad municipios
	 */
	public void setMunicipios(Collection<Municipio> acm_cm)
	{
		icm_municipios = acm_cm;
	}

	/**
	 * Retorna el valor de municipios.
	 *
	 * @return el valor de municipios
	 */
	public Collection<Municipio> getMunicipios()
	{
		if(icm_municipios == null)
			icm_municipios = new LinkedList<Municipio>();

		return icm_municipios;
	}

	/**
	 * Modifica el valor de nombre tooltip subproceso.
	 *
	 * @param as_s asigna el valor a la propiedad nombre tooltip subproceso
	 */
	public void setNombreTooltipSubproceso(String as_s)
	{
		is_nombreTooltipSubproceso = as_s;
	}

	/**
	 * Retorna el valor de nombre tooltip subproceso.
	 *
	 * @return el valor de nombre tooltip subproceso
	 */
	public String getNombreTooltipSubproceso()
	{
		if(!StringUtils.isValidString(is_nombreTooltipSubproceso))
			is_nombreTooltipSubproceso = ConstanteCommon.SELECCIONE;

		return is_nombreTooltipSubproceso;
	}

	/**
	 * Modifica el valor de requiere codigo catastral en solicitud.
	 *
	 * @param ab_b asigna el valor a la propiedad requiere codigo catastral en solicitud
	 */
	public void setRequiereCodigoCatastralEnSolicitud(boolean ab_b)
	{
		ib_requiereCodigoCatastralEnSolicitud = ab_b;
	}

	/**
	 * Valida la propiedad requiere codigo catastral en solicitud.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en requiere codigo catastral en solicitud
	 */
	public boolean isRequiereCodigoCatastralEnSolicitud()
	{
		return ib_requiereCodigoCatastralEnSolicitud;
	}

	/**
	 * Modifica el valor de requiere datos ant sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad requiere datos ant sistema
	 */
	public void setRequiereDatosAntSistema(boolean ab_b)
	{
		ib_requiereDatosAntSistema = ab_b;
	}

	/**
	 * Valida la propiedad requiere datos ant sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en requiere datos ant sistema
	 */
	public boolean isRequiereDatosAntSistema()
	{
		return ib_requiereDatosAntSistema;
	}

	/**
	 * Modifica el valor de respuesta nueva entrada.
	 *
	 * @param as_s asigna el valor a la propiedad respuesta nueva entrada
	 */
	public void setRespuestaNuevaEntrada(String as_s)
	{
		is_respuestaNuevaEntrada = as_s;
	}

	/**
	 * Retorna el valor de respuesta nueva entrada.
	 *
	 * @return el valor de respuesta nueva entrada
	 */
	public String getRespuestaNuevaEntrada()
	{
		return is_respuestaNuevaEntrada;
	}

	/**
	 * Modifica el valor de subprocesos certificados.
	 *
	 * @param ics_cs asigna el valor a la propiedad subprocesos certificados
	 */
	public void setSubprocesosCertificados(Collection<Subproceso> ics_cs)
	{
		ics_subprocesosCertificados = ics_cs;
	}

	/**
	 * Retorna el valor de subprocesos certificados.
	 *
	 * @return el valor de subprocesos certificados
	 */
	public Collection<Subproceso> getSubprocesosCertificados()
	{
		return ics_subprocesosCertificados;
	}

	/**
	 * Modifica el valor de turnos relacionados nueva entrada.
	 *
	 * @param acth_cth asigna el valor a la propiedad turnos relacionados nueva entrada
	 */
	public void setTurnosRelacionadosNuevaEntrada(Collection<TurnoHistoria> acth_cth)
	{
		icth_turnosRelacionadosNuevaEntrada = acth_cth;
	}

	/**
	 * Retorna el valor de turnos relacionados nueva entrada.
	 *
	 * @return el valor de turnos relacionados nueva entrada
	 */
	public Collection<TurnoHistoria> getTurnosRelacionadosNuevaEntrada()
	{
		return icth_turnosRelacionadosNuevaEntrada;
	}

	/**
	 * Modifica el valor de veredas.
	 *
	 * @param acv_cv asigna el valor a la propiedad veredas
	 */
	public void setVeredas(Collection<Vereda> acv_cv)
	{
		icv_veredas = acv_cv;
	}

	/**
	 * Retorna el valor de veredas.
	 *
	 * @return el valor de veredas
	 */
	public Collection<Vereda> getVeredas()
	{
		if(icv_veredas == null)
			icv_veredas = new LinkedList<Vereda>();

		return icv_veredas;
	}

	/**
	 * Elimina las matrículas que pudieran haber sido agregadas a un proceso específico.
	 */
	public void actualizarMatriculasAgregadas()
	{
		DatosDelPredio lddp_datosPredio;

		lddp_datosPredio = getDatosMatricula();

		if(lddp_datosPredio != null)
		{
			lddp_datosPredio.setActosAsociadosGeneral(null);
			lddp_datosPredio.setFile(null);
		}
	}

	/**
	 * Método para agregar un completitud documental.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void addCompletitudDocumental()
	    throws B2BException
	{
		addCompletitudDocumental(is_messageIdGrowl);
	}

	/**
	 * Consulta los departamentos dependiendo de la selección previa del país.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> buscarDepartamentosDireccion()
	{
		return null;
	}

	/**
	 * Consulta los municipios dependiendo de la selección previa del país y departamento.
	 */
	public void buscarMunicipiosDireccion()
	{
	}

	/**
	 * Busca sí el número predial ingresado existe, y si existe, trae el círculo y la matrícula asociados a este.
	 *
	 * @param apaiu_param Objeto contenedor del número predial ingresado en pantalla
	 */
	public void buscarPorNumeroPredial(PredioActoIU apaiu_param)
	{
		if(apaiu_param != null)
		{
			try
			{
				String ls_numeroPredial;

				ls_numeroPredial = apaiu_param.getNumeroPredial();

				if(StringUtils.isValidString(ls_numeroPredial))
				{
					PredioRegistro lpr_predioReg;

					lpr_predioReg = icr_certificadosRemote.buscarMatriculaPorNumeroPredial(
						    ls_numeroPredial, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lpr_predioReg != null)
					{
						String ls_idCirculo;
						Long   ll_idMatricula;

						ls_idCirculo       = lpr_predioReg.getIdCirculo();
						ll_idMatricula     = NumericUtils.getLongWrapper(lpr_predioReg.getIdMatricula());

						apaiu_param.setIdCirculo(ls_idCirculo);
						apaiu_param.setIdMatricula(ll_idMatricula);

						cargarDireccionPredioActo(ll_idMatricula, ls_idCirculo, null, getDatosMatricula());
					}
					else
						addMessageInfo("W", MessagesKeys.NO_SE_ENCONTRARON_REGISTROS);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Consulta las veredas dependiendo de la selección previa del país, departamento y municipio.
	 */
	public void buscarVeredasDireccion()
	{
	}

	/**
	 * Metodo encargado del cargue de la informacion para datos del tramite de nueva entrada.
	 *
	 * @param at_turno Objeto contenedor del turno ingresado en pantalla
	 */
	public void cargarDatosMatriculaNuevaEntrada(Turno at_turno)
	{
		try
		{
			NuevaEntrada lne_nuevaEntrada;

			lne_nuevaEntrada = icr_certificadosRemote.cargarDatosMatriculaNuevaEntrada(
				    at_turno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lne_nuevaEntrada != null)
			{
				Collection<SolicitudMatricula> lcsm_solicitudMatricula;
				DatosDelPredio                 ldp_datosMatriculas;

				lcsm_solicitudMatricula     = lne_nuevaEntrada.getSolicitudMatriculaNuevaEntrada();
				ldp_datosMatriculas         = getDatosMatricula();

				if(ldp_datosMatriculas == null)
					ldp_datosMatriculas = new DatosDelPredio();

				if(CollectionUtils.isValidCollection(lcsm_solicitudMatricula))
				{
					Collection<PredioActoIU> lcpaiu_actosAsociados;
					Collection<PredioActoIU> lcpaiu_actosAsociadosActuales;
					boolean                  lb_cargados;
					int                      li_consecutivo;

					lcpaiu_actosAsociados             = new ArrayList<PredioActoIU>();
					lcpaiu_actosAsociadosActuales     = ldp_datosMatriculas.getActosAsociadosGeneral();
					lb_cargados                       = CollectionUtils.isValidCollection(
						    lcpaiu_actosAsociadosActuales
						);
					li_consecutivo                    = NumericUtils.DEFAULT_INT_VALUE;

					for(SolicitudMatricula lsc_tmp : lcsm_solicitudMatricula)
					{
						if(lsc_tmp != null)
						{
							PredioActoIU lpaiu_predioActo;

							lpaiu_predioActo = new PredioActoIU();

							lpaiu_predioActo.setIdCirculo(lsc_tmp.getIdCirculo());
							lpaiu_predioActo.setIdMatricula(NumericUtils.getLongWrapper(lsc_tmp.getIdMatricula()));
							lpaiu_predioActo.setNupre(lsc_tmp.getNumeroPredial());
							lpaiu_predioActo.setNumeroPredial(lsc_tmp.getCedulaCatastral());
							lpaiu_predioActo.setCantidad(
							    NumericUtils.getLongWrapper(lsc_tmp.getCantidadCertificados())
							);

							{
								int li_consecutivoActual;

								li_consecutivoActual = lpaiu_predioActo.getConsecutivo();

								if(li_consecutivoActual == NumericUtils.DEFAULT_INT_VALUE)
								{
									li_consecutivo++;

									li_consecutivoActual = li_consecutivo;

									lpaiu_predioActo.setConsecutivo(li_consecutivoActual);
								}
							}

							if(lb_cargados)
								lcpaiu_actosAsociadosActuales.add(lpaiu_predioActo);
							else
								lcpaiu_actosAsociados.add(lpaiu_predioActo);
						}
					}

					ldp_datosMatriculas.setActosAsociadosGeneral(
					    CollectionUtils.isValidCollection(lcpaiu_actosAsociadosActuales)
					    ? lcpaiu_actosAsociadosActuales : lcpaiu_actosAsociados
					);
				}

				{
					DatosAntSistema ldas_datosAntSistema;

					ldas_datosAntSistema = lne_nuevaEntrada.getDatosAntSistemaNuevaEntrada();

					if(ldas_datosAntSistema != null)
					{
						String ls_adquisicionPredio;

						ls_adquisicionPredio = ldas_datosAntSistema.getAdquisicionPredio();

						ldp_datosMatriculas.setAntiguoSistema1932(
						    (StringUtils.isValidString(ls_adquisicionPredio)
							    && ls_adquisicionPredio.equalsIgnoreCase("Antes de 1978")) ? IdentificadoresCommon.S
						                                                                   : IdentificadoresCommon.O
						);
						ldp_datosMatriculas.setDetallesAntSisAgregados(
						    ldas_datosAntSistema.getDetalleAntSistemaNuevaEntrada()
						);
						ldp_datosMatriculas.setEleccionRequiereMatricula(
						    TipoRequiereMatriculaCommon.DATOS_ANTIGUO_SISTEMA
						);
						ldp_datosMatriculas.setTiposMatricula(cargarTextosDatosPredio(false, true, false));
						ldp_datosMatriculas.setDatosAntiguoSistema(ldas_datosAntSistema);
						cambiarTabProceso(true);
					}
				}

				{
					BeanDireccion lbd_beanDireccion;

					lbd_beanDireccion = getBeanDireccion();

					if(lbd_beanDireccion != null)
					{
						SolicitudDireccionCertificado lsdc_sdc;

						lsdc_sdc = lne_nuevaEntrada.getSolicitudDireccionCertificadoNuevaEntrada();

						if(lsdc_sdc != null)
						{
							lbd_beanDireccion.setDireccionCertificado(lsdc_sdc);
							lbd_beanDireccion.setBloquearCamposCertificados(true);
						}
					}
				}

				setDatosMatricula(ldp_datosMatriculas);
				setDatosIdentificacion(lne_nuevaEntrada.getPersonaNuevaEntrada());

				setBloqueosNuevaEntrada(true);
				setIdTurno(at_turno.getIdTurno());

				PrimeFaces.current().ajax().update("fCertificados");
				PrimeFaces.current().ajax().update("fCertificados:idPMatricula");
				PrimeFaces.current().ajax().update("fCertificados:idTVDatosPredio");
				PrimeFaces.current().ajax().update("fCertificados:idTabDatosAntSistema");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosMatriculaNuevaEntrada", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Ajusta las validaciones en pantalla según el subproceso seleccionado.
	 */
	public void cambiarSubproceso()
	{
		Solicitud ls_solicitud;

		ls_solicitud = getSolicitud();

		if(ls_solicitud != null)
		{
			actualizarNombreTooltipTipoCertificado(ls_solicitud);

			String         ls_idSubproceso;
			DatosDelPredio lddp_datosMatricula;

			ls_idSubproceso         = ls_solicitud.getIdSubproceso();
			lddp_datosMatricula     = getDatosMatricula();

			if(StringUtils.isValidString(ls_idSubproceso) && (lddp_datosMatricula != null))
			{
				try
				{
					{
						boolean lb_antSistema;

						lb_antSistema = ls_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_12)
								|| ls_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_14);

						setRequiereDatosAntSistema(lb_antSistema);

						lddp_datosMatricula.setTiposMatricula(
						    cargarTextosDatosPredio(false, lb_antSistema, !lb_antSistema)
						);

						lddp_datosMatricula.setEleccionRequiereMatricula(
						    lb_antSistema ? TipoRequiereMatriculaCommon.DATOS_ANTIGUO_SISTEMA
						                  : TipoRequiereMatriculaCommon.CON_MATRICULA
						);

						cambiarTabProceso(lb_antSistema);
					}

					{
						Constantes lc_certificadosMasivos;

						lc_certificadosMasivos = irr_referenceRemote.findConstantesById(
							    ConstanteCommon.SUBPROCESO_CERTIFICADOS_MASIVOS
							);

						if(lc_certificadosMasivos != null)
						{
							Map<String, String> lmss_caracter;

							lmss_caracter = ListadoCodigosConstantes.generarCodigos(
								    lc_certificadosMasivos.getCaracter()
								);

							if(
							    CollectionUtils.isValidCollection(lmss_caracter)
								    && lmss_caracter.containsKey(ls_idSubproceso)
							)
							{
								lddp_datosMatricula.setEleccionRequiereMatricula(
								    TipoRequiereMatriculaCommon.CON_MATRICULA
								);

								setCertificadosMasivos(true);
							}
							else
							{
								lddp_datosMatricula.setSeccionTipoMatricula(EstadoCommon.S);

								setCertificadosMasivos(false);
							}
						}
					}

					{
						Constantes lc_certificadosCirculoMatrObligatorio;

						lc_certificadosCirculoMatrObligatorio = irr_referenceRemote.findConstantesById(
							    ConstanteCommon.CERTIFICADOS_CIRCULO_MATRICULA_OBLIGATORIO
							);

						if(lc_certificadosCirculoMatrObligatorio != null)
						{
							Map<String, String> lmss_caracter;

							lmss_caracter = ListadoCodigosConstantes.generarCodigos(
								    lc_certificadosCirculoMatrObligatorio.getCaracter()
								);

							if(
							    CollectionUtils.isValidCollection(lmss_caracter)
								    && lmss_caracter.containsKey(ls_idSubproceso)
							)
								setCirculoMatriculaObligatorio(true);
							else
								setCirculoMatriculaObligatorio(false);
						}
					}

					{
						Constantes lc_certificadosConCodigoCatastral;

						lc_certificadosConCodigoCatastral = irr_referenceRemote.findConstantesById(
							    ConstanteCommon.CERTIFICADOS_CON_CODIGO_CATASTRAL
							);

						if(lc_certificadosConCodigoCatastral != null)
						{
							Map<String, String> lmss_caracter;

							lmss_caracter = ListadoCodigosConstantes.generarCodigos(
								    lc_certificadosConCodigoCatastral.getCaracter()
								);

							if(
							    CollectionUtils.isValidCollection(lmss_caracter)
								    && lmss_caracter.containsKey(ls_idSubproceso)
							)
								setRequiereCodigoCatastralEnSolicitud(true);
							else
								setRequiereCodigoCatastralEnSolicitud(false);
						}
					}

					{
						Collection<TipoRequiereMatricula> lctrm_tiposReqMatr;

						lctrm_tiposReqMatr = lddp_datosMatricula.getTiposMatricula();

						if(CollectionUtils.isValidCollection(lctrm_tiposReqMatr))
						{
							boolean lb_certificadosMasivos;

							lb_certificadosMasivos = isCertificadosMasivos();

							for(TipoRequiereMatricula ltrm_data : lctrm_tiposReqMatr)
							{
								if(ltrm_data != null)
								{
									String ls_idTipoRequiere;

									ls_idTipoRequiere = StringUtils.getStringNotNull(
										    ltrm_data.getIdTipoRequiereMatricula()
										);

									if(
									    lb_certificadosMasivos
										    && !ls_idTipoRequiere.equals(TipoRequiereMatriculaCommon.CON_MATRICULA)
									)
										ltrm_data.setDesactivar(true);
									else
										ltrm_data.setDesactivar(false);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					clh_LOGGER.error(lb2be_e);
					addMessage(lb2be_e);
				}
			}
			else
				setCertificadosMasivos(false);
		}
	}

	/**
	 * Cambia el estado de los campos de nueva entrada segun la elección a si el tramite es de este proceso.
	 */
	public void cambiarTurnoNirNuevaEntrada()
	    throws B2BException
	{
		String ls_respuesta;

		ls_respuesta = StringUtils.getStringNotNull(getRespuestaNuevaEntrada());

		if(!ls_respuesta.equals(EstadoCommon.S))
		{
			setTurnosRelacionadosNuevaEntrada(null);

			Solicitud ls_solicitud;

			ls_solicitud = getSolicitud();

			if(ls_solicitud != null)
			{
				ls_solicitud.setIdTurnoAnt(null);
				ls_solicitud.setNirAsociado(null);
			}

			setMostrarLimpiarDatos(true);
			setBloqueosNuevaEntrada(false);
			setNuevaEntrada(false);
			setDatosIdentificacion(null);

			{
				DatosDelPredio lddp_datos;

				lddp_datos = getDatosMatricula();

				if(lddp_datos != null)
				{
					lddp_datos.setActosAsociadosGeneral(null);
					lddp_datos.setDatosAntiguoSistema(null);
					ls_solicitud.setIdSubproceso(null);
				}
			}

			{
				BeanDireccion lbd_beanDireccion;

				lbd_beanDireccion = getBeanDireccion();

				if(lbd_beanDireccion != null)
				{
					lbd_beanDireccion.setDireccionCertificado(null);

					lbd_beanDireccion.setBloquearCamposCertificados(false);
				}
			}
		}

		PrimeFaces.current().ajax().update("fCertificados");
	}

	/**
	 * Activa o desactiva el flag de nueva entrada si el turno seleccionado es valido.
	 */
	public void cambiarTurnoNuevaEntrada()
	{
		try
		{
			Solicitud ls_solicitud;

			ls_solicitud = getSolicitud();

			if(ls_solicitud != null)
			{
				String ls_turnoAnterior;

				ls_turnoAnterior = ls_solicitud.getIdTurnoAnt();

				if(StringUtils.isValidString(ls_turnoAnterior))
				{
					Collection<TurnoHistoria> lcth_turnos;

					lcth_turnos = getTurnosRelacionadosNuevaEntrada();

					if(CollectionUtils.isValidCollection(lcth_turnos))
					{
						Iterator<TurnoHistoria> lith_iterador;
						boolean                 lb_found;
						Turno                   lt_turnoEncontrado;

						lith_iterador          = lcth_turnos.iterator();
						lb_found               = false;
						lt_turnoEncontrado     = null;

						while(lith_iterador.hasNext() && !lb_found)
						{
							TurnoHistoria lth_data;

							lth_data = lith_iterador.next();

							if(lth_data != null)
							{
								String ls_idTurno;

								ls_idTurno = StringUtils.getStringNotNull(lth_data.getIdTurno());

								if(ls_idTurno.equals(ls_turnoAnterior))
								{
									Turno lt_turno;

									lt_turno = icr_certificadosRemote.consultarTurno(
										    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
										);

									if(lt_turno != null)
									{
										String         ls_idCirculo;
										DatosDelPredio lddp_matriculas;

										ls_idCirculo        = lt_turno.getIdCirculo();
										lddp_matriculas     = getDatosMatricula();

										if(lddp_matriculas != null)
											lddp_matriculas.setIdCirculo(ls_idCirculo);

										lt_turnoEncontrado = lt_turno;

										setIdCirculoNuevaEntrada(ls_idCirculo);

										{
											String ls_idSubproceso;

											ls_idSubproceso = lt_turno.getIdSubProceso();

											if(StringUtils.isValidString(ls_idSubproceso))
												ls_solicitud.setIdSubproceso(ls_idSubproceso);
										}

										setNuevaEntrada(true);

										lb_found = true;
									}
								}
							}
						}

						if(lt_turnoEncontrado != null)
							cargarDatosMatriculaNuevaEntrada(lt_turnoEncontrado);
					}
				}
				else
					setNuevaEntrada(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cambiarTurnoNuevaEntrada", lb2be_e);
			addMessage(lb2be_e);
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
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		limpiarRegistro();
		setRespuestaNuevaEntrada(null);
		setTurnosRelacionadosNuevaEntrada(null);
		setSubprocesosCertificados(null);
		setDatosMatricula(null);
		setNombreTooltipSubproceso(null);
		setDatosIdentificacion(null);
		lbd_beanDireccion.setDireccionCertificado(null);
		setDireccionCertificado(null);
		setCirculoMatriculaObligatorio(false);
	}

	/**
	 * Verifica si para un NIR ingresado existen turnos asociados.
	 */
	public void consultarNirNuevaEntrada()
	{
		try
		{
			Solicitud                 ls_solicitud;
			Collection<TurnoHistoria> lcth_turnos;

			ls_solicitud = getSolicitud();

			if(ls_solicitud == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			lcth_turnos = icr_certificadosRemote.consultarTurnosAsociadosNuevaEntrada(
				    ls_solicitud.getNirAsociado(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(!CollectionUtils.isValidCollection(lcth_turnos))
				addMessageInfo(EstadoCommon.W, MessagesKeys.NO_SE_ENCONTRARON_TURNOS_CERTIFICADOS);
			else
				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);

			setTurnosRelacionadosNuevaEntrada(lcth_turnos);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarNirNuevaEntrada", lb2be_e);
			addMessage(lb2be_e);

			setTurnosRelacionadosNuevaEntrada(null);
		}
	}

	/**
	 * Verifica si para un Turno asociado existen NIR asociados.
	 */
	public void consultarTurnoNuevaEntrada()
	{
		try
		{
			Solicitud ls_solicitud;
			Solicitud ls_solicitudConsultada;
			Turno     lt_turnoIngresado;

			ls_solicitud          = getSolicitud();
			lt_turnoIngresado     = null;

			if(ls_solicitud == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			ls_solicitudConsultada = icr_certificadosRemote.consultarNirAsociadoNuevaEntrada(
				    ls_solicitud.getIdTurnoAnt(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ls_solicitudConsultada != null)
			{
				String                    ls_nirAsociado;
				Collection<TurnoHistoria> lcth_turnos;

				ls_nirAsociado     = ls_solicitudConsultada.getNir();

				lcth_turnos = icr_certificadosRemote.consultarTurnosAsociadosNuevaEntrada(
					    ls_nirAsociado, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lcth_turnos))
				{
					Iterator<TurnoHistoria> lith_iterador;
					boolean                 lb_found;

					lith_iterador     = lcth_turnos.iterator();
					lb_found          = false;

					while(lith_iterador.hasNext() && !lb_found)
					{
						TurnoHistoria lth_data;

						lth_data = lith_iterador.next();

						if(lth_data != null)
						{
							String ls_idTurno;

							ls_idTurno = lth_data.getIdTurno();

							if(StringUtils.isValidString(ls_idTurno))
							{
								Turno lt_turno;

								lt_turno = icr_certificadosRemote.consultarTurno(
									    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

								if(lt_turno != null)
								{
									String         ls_idCirculo;
									DatosDelPredio lddp_matriculas;

									ls_idCirculo        = lt_turno.getIdCirculo();
									lddp_matriculas     = getDatosMatricula();

									if(lddp_matriculas != null)
										lddp_matriculas.setIdCirculo(ls_idCirculo);

									setIdCirculoNuevaEntrada(ls_idCirculo);

									ls_solicitud.setNirAsociado(ls_nirAsociado);

									{
										String ls_idSubproceso;

										ls_idSubproceso = lt_turno.getIdSubProceso();

										if(StringUtils.isValidString(ls_idSubproceso))
											ls_solicitud.setIdSubproceso(ls_idSubproceso);
									}

									lt_turnoIngresado = lt_turno;

									setNuevaEntrada(true);

									lb_found = true;
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

				if(lt_turnoIngresado != null)
					cargarDatosMatriculaNuevaEntrada(lt_turnoIngresado);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			setIdCirculoNuevaEntrada(null);
			setNuevaEntrada(false);
			setBloqueosNuevaEntrada(false);
			clh_LOGGER.error("consultarNirNuevaEntrada", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Controla el flujo entre tabs en la pantalla.
	 *
	 * @param afe_event Objeto contenedor de los eventos que ocurren en pantalla
	 * @return Nombre de tab a mostrar en pantalla
	 */
	public String flowListener(FlowEvent afe_event)
	{
		String ls_return;

		ls_return = afe_event.getNewStep();

		try
		{
			String ls_oldStep;
			String ls_newStep;

			ls_oldStep     = afe_event.getOldStep();
			ls_newStep     = afe_event.getNewStep();

			if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_newStep))
			{
				String ls_datosInteresadoTab;
				String ls_datosTramiteTab;
				String ls_documentosSoporte;
				String ls_datosLiquidacion;
				String ls_impresion;

				ls_datosInteresadoTab     = "datosInteresado_id";
				ls_datosTramiteTab        = "datosTramite_id";
				ls_documentosSoporte      = "documentosSoporte_id";
				ls_datosLiquidacion       = "liquidacion_id";
				ls_impresion              = "impresion_id";

				if(ls_oldStep.equalsIgnoreCase(ls_datosInteresadoTab))
				{
					setMostrarLimpiarDatos(false);
					setTurnoDesistimiento(null);

					{
						FacesContext lfc_context;
						boolean      lb_focus;

						lfc_context     = FacesContext.getCurrentInstance();
						lb_focus        = true;

						limpiarEstilosDatosInteresado(is_idForm, lfc_context, lb_focus);
					}

					salvarInteresado();
					setMostrarAtras(true);

					limpiarTabDatosTramite();
					cargarTiposCertificado();

					{
						DatosDelPredio lddp_datosMatricula;

						lddp_datosMatricula = getDatosMatricula();

						if(lddp_datosMatricula != null)
							lddp_datosMatricula.setTiposMatricula(cargarTextosDatosPredio(false, true, true));

						cambiarSubproceso();
					}
				}
				else if(
				    ls_newStep.equalsIgnoreCase(ls_datosInteresadoTab)
					    && ls_oldStep.equalsIgnoreCase(ls_datosTramiteTab)
				)
				{
					setMostrarLimpiarDatos(true);
					cargarDireccion(true);
					setBloqueosNuevaEntrada(false);
					setDatosIdentificacion(null);

					{
						DatosDelPredio lddp_datos;

						lddp_datos = getDatosMatricula();

						if(lddp_datos != null)
						{
							lddp_datos.setActosAsociadosGeneral(null);
							lddp_datos.setDatosAntiguoSistema(null);
						}
					}

					{
						BeanDireccion lbd_beanDireccion;

						lbd_beanDireccion = getBeanDireccion();

						if(lbd_beanDireccion != null)
						{
							lbd_beanDireccion.setDireccionCertificado(null);

							lbd_beanDireccion.setBloquearCamposCertificados(false);
						}
					}

					{
						Solicitud ls_solicitud;

						ls_solicitud = getSolicitud();

						if(ls_solicitud != null)
							ls_solicitud.setIdSubproceso(null);
					}
				}
				else if(
				    ls_newStep.equalsIgnoreCase(ls_datosLiquidacion) && ls_oldStep.equalsIgnoreCase(ls_datosTramiteTab)
				)
				{
					guardarDatosTramite();
					preLiquidar();
				}
				else if(
				    ls_newStep.equalsIgnoreCase(ls_documentosSoporte)
					    && ls_oldStep.equalsIgnoreCase(ls_datosTramiteTab)
				)
					guardarDatosTramite();
				else if(
				    ls_newStep.equalsIgnoreCase(ls_datosLiquidacion)
					    && ls_oldStep.equalsIgnoreCase(ls_documentosSoporte)
				)
				{
					Collection<AccCompletitudDocumental> lcacd_completitudDocumental;
					Solicitud                            ls_solicitud;

					ls_solicitud                    = getSolicitud();
					lcacd_completitudDocumental     = getTiposCompletitudDocumental();

					if(CollectionUtils.isValidCollection(lcacd_completitudDocumental))
						irr_registroRemote.guardarTiposDocumentales(
						    lcacd_completitudDocumental, ls_solicitud, null, null, getLocalIpAddress(),
						    getRemoteIpAddress(), getUserId()
						);
					else
					{
						ExternalContext lec_externalContext;

						lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

						addMessage(MessagesKeys.SIN_DOCUMENTOS_SOPORTE);

						if(lec_externalContext != null)
						{
							Flash lf_flash;

							lf_flash = lec_externalContext.getFlash();

							if(lf_flash != null)
							{
								lf_flash.setKeepMessages(true);
								PrimeFaces.current().ajax().update(is_idForm);
							}
						}
					}

					preLiquidar();
				}
				else if(ls_newStep.equalsIgnoreCase(ls_impresion) && ls_oldStep.equalsIgnoreCase(ls_datosLiquidacion))
				{
					if(isProcesoTerminado())
					{
						Solicitud lso_solicitud;

						lso_solicitud = getSolicitud();

						if(lso_solicitud != null)
						{
							ii_indiceImpresion = 0;

							setDocumentosImprimir(
							    irr_registroRemote.cargarDocumentosSolicitud(
							        IdentificadoresCommon.CERTIFICADOS, lso_solicitud.getIdSolicitud(), getUserId(),
							        getLocalIpAddress(), getRemoteIpAddress()
							    )
							);
							setOcultarSiguiente(true);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TERMINAR_PROCESO);

					setOcultarSiguiente(true);
				}
				else if(
				    ls_newStep.equalsIgnoreCase(ls_datosTramiteTab) && ls_oldStep.equalsIgnoreCase(ls_datosLiquidacion)
				)
					setOcultarSiguiente(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			ls_return = afe_event.getOldStep();
		}

		return ls_return;
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(String as_pantalla)
	{
		imprimirDocumentos(false, as_pantalla);
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param ab_boton boolean que indica si se ejecutó mediante la pantalla.
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(boolean ab_boton, String as_pantalla)
	{
		imprimirDocumentos(ab_boton, as_pantalla, getNirGenerado(), ":fCertificadosSubmit:botonFinalizar");
	}

	/**
	 * Pre liquidar certificados.
	 */
	public void preLiquidarCertificados()
	{
		try
		{
			preLiquidar();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("preLiquidarCertificados", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(is_messageIdGrowl);
			actualizarComponente("fCertificados:idOPDetalleLiquidacion");
		}
	}

	/**
	 * Terminar proceso certificados.
	 *
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public void terminarProcesoCertificados()
	    throws Exception
	{
		generacionSolicitudes(true);
		buscarDetalleLiquidacion();
	}

	/**
	 * Valida los campos de datos del predio para antiguo sistema en la pestaña de datos del tramite.
	 */
	public void validarCamposDatosAnt()
	{
		DatosDelPredio lddp_datosPredio;

		lddp_datosPredio = getDatosMatricula();
		lddp_datosPredio.getDetalleAntSistema().getPartida();

		if(lddp_datosPredio != null)
		{
			DatosAntSistema ldas_datosAnt;

			ldas_datosAnt = lddp_datosPredio.getDatosAntiguoSistema();

			if(ldas_datosAnt != null)
			{
				FacesContext lfc_faces;
				boolean      lb_focus;

				lfc_faces     = FacesContext.getCurrentInstance();
				lb_focus      = true;

				validateStyles(
				    is_idForm + is_idTabDatosPredio + "idSOMCirculoRegistralAntSistema", lfc_faces,
				    ldas_datosAnt.getIdCirculo(), lb_focus
				);
				validateStyles(
				    is_idForm + is_idTabDatosPredio + "idSOMTipoPredioAntSistema", lfc_faces,
				    ldas_datosAnt.getIdTipoPredio(), lb_focus
				);

				{
					String ls_tipoAdquisicion;

					ls_tipoAdquisicion = StringUtils.getStringNotNull(lddp_datosPredio.getAntiguoSistema1932());

					if(ls_tipoAdquisicion.equals(EstadoCommon.O))
						validateStyles(
						    is_idForm + is_idTabDatosPredio + "idItNomAntSistema", lfc_faces,
						    ldas_datosAnt.getNombrePredio(), lb_focus
						);
				}

				validateStyles(
				    is_idForm + is_idTabDatosPredio + "idSOMDepAntSistema", lfc_faces, ldas_datosAnt.getIdDepartamento(),
				    lb_focus
				);
				validateStyles(
				    is_idForm + is_idTabDatosPredio + "idSOMMunAntSistema", lfc_faces, ldas_datosAnt.getIdMunicipio(),
				    lb_focus
				);
			}
		}
	}

	/**
	 * Valida los campos de detalles del predio para antiguo sistema en la pestaña de datos del tramite.
	 */
	public void validarCamposDetalleAnt()
	{
		DatosDelPredio lddp_datosPredio;

		lddp_datosPredio = getDatosMatricula();

		if(lddp_datosPredio != null)
		{
			DetalleAntSistema ldas_detalleAnt;

			ldas_detalleAnt = lddp_datosPredio.getDetalleAntSistema();

			if(ldas_detalleAnt != null)
			{
				FacesContext lfc_faces;
				boolean      lb_focus;
				Long         ll_idLibro;

				lfc_faces      = FacesContext.getCurrentInstance();
				lb_focus       = true;
				ll_idLibro     = ldas_detalleAnt.getIdLibroAntSistema();

				if(ldas_detalleAnt.isError())
				{
					validateStyles(is_idForm + is_idTabDatosPredio + "idSOMLibro", lfc_faces, ll_idLibro, lb_focus);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMTomo", lfc_faces, ldas_detalleAnt.getTomo(), lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMFolio", lfc_faces, ldas_detalleAnt.getFolio(), lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idItNumeroPartida", lfc_faces,
					    ldas_detalleAnt.getNumeroPartida(), lb_focus
					);

					if(NumericUtils.isValidLong(ll_idLibro))
					{
						String ls_idLibroSeleccionado;

						ls_idLibroSeleccionado = StringUtils.getString(ll_idLibro);

						if(ls_idLibroSeleccionado.equals(LibroAntSistemaCommon.LIBRO_DE_MATRICULAS))
							validateStyles(
							    is_idForm + is_idTabDatosPredio + "datosPredioIdMatricula", lfc_faces,
							    ldas_detalleAnt.getIdMatricula(), lb_focus
							);
						else
						{
							validateStyles(
							    is_idForm + is_idTabDatosPredio + "idSOMPartida", lfc_faces,
							    ldas_detalleAnt.getPartida(), lb_focus
							);
							validateStyles(
							    is_idForm + is_idTabDatosPredio + "idSOMAno", lfc_faces, ldas_detalleAnt.getAnio(),
							    lb_focus
							);
						}

						if(ls_idLibroSeleccionado.equals(LibroAntSistemaCommon.LIBRO_SEGUNDO))
						{
							Documento ld_documento;

							ld_documento = ldas_detalleAnt.getDocumento();

							if(ld_documento != null)
							{
								validateStyles(
								    is_idForm + is_idTabDatosPredio + "idSOMEscrituraAntSis", lfc_faces,
								    ld_documento.getIdTipoDocumento(), lb_focus
								);
								validateStyles(
								    is_idForm + is_idTabDatosPredio + "idItDocuActoAntSis", lfc_faces,
								    ld_documento.getNumero(), lb_focus
								);
								validateStyles(
								    is_idForm + is_idTabDatosPredio + "idCalFechaDocAntSis", lfc_faces,
								    ld_documento.getFechaDocumento(), lb_focus
								);
								validateStyles(
								    is_idForm + is_idTabDatosPredio + "idSOMTipoOficinaAntSis", lfc_faces,
								    ld_documento.getIdTipoOficina(), lb_focus
								);
								validateStyles(
								    is_idForm + is_idTabDatosPredio + "idPaisDocumentoAntSis", lfc_faces,
								    ld_documento.getIdPais(), lb_focus
								);
								validateStyles(
								    is_idForm + is_idTabDatosPredio + "idSOMDepartamentoAntSis", lfc_faces,
								    ld_documento.getIdDepartamento(), lb_focus
								);
								validateStyles(
								    is_idForm + is_idTabDatosPredio + "idSOMMunicipioAntSis", lfc_faces,
								    ld_documento.getIdMunicipio(), lb_focus
								);
								validateStyles(
								    is_idForm + is_idTabDatosPredio + "idSOMOficinaOrigenAntSis", lfc_faces,
								    ld_documento.getIdOficinaOrigen(), lb_focus
								);
							}
						}
					}

					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMPrediosAgregados", lfc_faces,
					    ldas_detalleAnt.getIdDatosAntSistema(), lb_focus
					);
				}
				else
				{
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMLibro", lfc_faces, IdentificadoresCommon.DATO_VALIDO,
					    lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMTomo", lfc_faces, IdentificadoresCommon.DATO_VALIDO,
					    lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMFolio", lfc_faces, IdentificadoresCommon.DATO_VALIDO,
					    lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idItNumeroPartida", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "datosPredioIdMatricula", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMPartida", lfc_faces, IdentificadoresCommon.DATO_VALIDO,
					    lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMAno", lfc_faces, IdentificadoresCommon.DATO_VALIDO,
					    lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMEscrituraAntSis", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idItDocuActoAntSis", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idCalFechaDocAntSis", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMTipoOficinaAntSis", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idPaisDocumentoAntSis", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMDepartamentoAntSis", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMMunicipioAntSis", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMOficinaOrigenAntSis", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
					validateStyles(
					    is_idForm + is_idTabDatosPredio + "idSOMPrediosAgregados", lfc_faces,
					    IdentificadoresCommon.DATO_VALIDO, lb_focus
					);
				}
			}
		}
	}

	/**
	 * Cambia la obligatoriedad de los campos de información de la persona dependiendo del
	 * tipo de documento seleccionado.
	 */
	public void validarTipoDocumentoSeleccionado()
	{
		Persona lp_persona;

		lp_persona = getDatosIdentificacion();

		if(lp_persona != null)
		{
			String ls_idTipoDoc;

			ls_idTipoDoc = lp_persona.getIdDocumentoTipo();

			if(StringUtils.isValidString(ls_idTipoDoc))
			{
				if(ls_idTipoDoc.equals(IdentificadoresCommon.NIT))
				{
					setHabilitarRazonSocial(true);

					lp_persona.setPrimerApellido(null);
					lp_persona.setPrimerNombre(null);
					lp_persona.setSegundoApellido(null);
					lp_persona.setSegundoNombre(null);
				}
				else
				{
					setHabilitarRazonSocial(false);

					lp_persona.setRazonSocial(null);
				}
			}
		}
	}

	/**
	 * Método que verifica si el número
	 * ingresado en certificados es par o impar dependiendo del tipo de partida ingresado.
	 * @throws B2BException Si el tipo de partida y el número de partida no corresponde.
	 */
	public void verificarParImpar()
	    throws B2BException
	{
		try
		{
			DatosDelPredio lddp_datoMatricula;

			lddp_datoMatricula = getDatosMatricula();

			if(lddp_datoMatricula != null)
			{
				Long           ll_numeroPartida;
				DatosDelPredio ldm_datosMatricula;
				ldm_datosMatricula = getDatosMatricula();

				String            ls_tipoPartida;
				DetalleAntSistema ldas_detalleAntSistema;

				if(ldm_datosMatricula != null)
				{
					ldas_detalleAntSistema = ldm_datosMatricula.getDetalleAntSistema();

					if(ldas_detalleAntSistema != null)
					{
						ls_tipoPartida       = ldas_detalleAntSistema.getPartida();
						ll_numeroPartida     = ldas_detalleAntSistema.getNumeroPartida();

						if(NumericUtils.isValidLong(ll_numeroPartida) && StringUtils.isValidString(ls_tipoPartida))
						{
							boolean lb_verificacion;

							lb_verificacion = icr_certificadosRemote.verificarParImpar(
								    ls_tipoPartida, ll_numeroPartida, getUserId(), getLocalIpAddress(),
								    getRemoteIpAddress()
								);

							if(!lb_verificacion)
								throw new B2BException(ErrorKeys.NUMERO_PARTIDA_INVALIDO);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("verificarParImpar", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(is_messageIdGrowl);
		}
	}

	/**
	 * Busca el nombre del tipo de certificado seleccionado y actualiza el tooltip en pantalla.
	 *
	 * @param as_solicitud correspondiente al valor del tipo de objeto Solicitud
	 */
	private void actualizarNombreTooltipTipoCertificado(Solicitud as_solicitud)
	{
		Collection<Subproceso> lcs_subprocesos;

		lcs_subprocesos = getSubprocesosCertificados();

		if(CollectionUtils.isValidCollection(lcs_subprocesos) && (as_solicitud != null))
		{
			Iterator<Subproceso> lis_iterador;
			boolean              lb_encontro;
			String               ls_idSubproceso;
			String               ls_nombre;

			lis_iterador        = lcs_subprocesos.iterator();
			lb_encontro         = false;
			ls_idSubproceso     = StringUtils.getStringNotNull(as_solicitud.getIdSubproceso());
			ls_nombre           = null;

			while(lis_iterador.hasNext() && !lb_encontro)
			{
				Subproceso ls_subTmp;

				ls_subTmp = lis_iterador.next();

				if(ls_subTmp != null)
				{
					String ls_idSubTmp;

					ls_idSubTmp = StringUtils.getStringNotNull(ls_subTmp.getIdSubproceso());

					if(ls_idSubTmp.equals(ls_idSubproceso))
					{
						ls_nombre     = ls_subTmp.getNombre();

						lb_encontro = true;
					}
				}
			}

			setNombreTooltipSubproceso(ls_nombre);
		}
	}

	/**
	 * Cambia el tab de proceso segun el subproceso seleccionado.
	 *
	 * @param ab_antSistema indicador del proceso seleccionado
	 */
	private void cambiarTabProceso(boolean ab_antSistema)
	{
		TabView ltv_tabView;

		ltv_tabView = (TabView)FacesContext.getCurrentInstance().getViewRoot()
				                               .findComponent("fCertificados:idTVDatosPredio");

		if((ltv_tabView != null))
		{
			if(ab_antSistema)
				ltv_tabView.setActiveIndex(1);
			else
				ltv_tabView.setActiveIndex(0);
		}
	}

	/**
	 * Obtiene los tipos de certificado para el proceso de certificados.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarTiposCertificado()
	    throws B2BException
	{
		Subproceso ls_subproceso;

		ls_subproceso = new Subproceso();

		ls_subproceso.setIdProceso(ProcesoCommon.ID_PROCESO_1);
		ls_subproceso.setSolicitudCertificado(EstadoCommon.S);

		setSubprocesosCertificados(
		    irr_referenceRemote.findSubprocesosByProcesoSolicitudCert(
		        ls_subproceso, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
		    )
		);
	}

	/**
	 * Valída la información ingresada en la pestaña de datos del trámite y la guarda en base de datos.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void guardarDatosTramite()
	    throws B2BException
	{
		Solicitud ls_solicitud;

		ls_solicitud = getSolicitud();

		if(ls_solicitud != null)
		{
			Certificados lc_certificado;
			FacesContext lfc_faces;
			boolean      lb_focus;
			boolean      lb_matriculasValidas;

			lc_certificado           = new Certificados();
			lfc_faces                = FacesContext.getCurrentInstance();
			lb_focus                 = true;
			lb_matriculasValidas     = false;

			{
				String ls_nuevaEntrada;
				String ls_idSubproceso;

				ls_nuevaEntrada     = getRespuestaNuevaEntrada();
				ls_idSubproceso     = ls_solicitud.getIdSubproceso();

				validateStyles(is_idForm + "idSOMNuevaEntrada", lfc_faces, ls_nuevaEntrada, lb_focus);

				if(StringUtils.isValidString(ls_nuevaEntrada))
				{
					if(ls_nuevaEntrada.equals(EstadoCommon.S))
					{
						String ls_turnoAnt;
						String ls_nirAnt;

						ls_turnoAnt     = ls_solicitud.getIdTurnoAnt();
						ls_nirAnt       = ls_solicitud.getNirAsociado();

						validateStyles(is_idForm + "idOlTurnoAnterior", lfc_faces, ls_turnoAnt, lb_focus);
						validateStyles(is_idForm + "idSOMTurnosAnteriores", lfc_faces, ls_turnoAnt, lb_focus);

						if(!StringUtils.isValidString(ls_turnoAnt))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_TURNO_ANTERIOR);

						validateStyles(is_idForm + "idOlNirRelacionado", lfc_faces, ls_nirAnt, lb_focus);

						if(!StringUtils.isValidString(ls_nirAnt))
							throw new B2BException(ErrorKeys.ERROR_SIN_NIR_ASOCIADO);
					}
					else
					{
						validateStyles(
						    is_idForm + "idOlTurnoAnterior", lfc_faces, IdentificadoresCommon.DATO_VALIDO, lb_focus
						);
						validateStyles(
						    is_idForm + "idSOMTurnosAnteriores", lfc_faces, IdentificadoresCommon.DATO_VALIDO, lb_focus
						);
						validateStyles(
						    is_idForm + "idOlNirRelacionado", lfc_faces, IdentificadoresCommon.DATO_VALIDO, lb_focus
						);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_RESPUESTA_NUEVA_ENTRADA);

				validateStyles(is_idForm + "idSOMTiposCertificado", lfc_faces, ls_idSubproceso, lb_focus);

				if(!StringUtils.isValidString(ls_idSubproceso))
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_CERTIFICADO);
			}

			{
				DatosDelPredio                 lddp_datosMatricula;
				Collection<SolicitudMatricula> lcsm_matriculasFinal;
				Collection<PredioActoIU>       lpaiu_matriculas;
				Collection<DatosAntSistema>    lcdas_datosAntAgregados;
				boolean                        lb_cirMatrOblig;
				String                         ls_idTipoRequiereMatricula;

				lddp_datosMatricula = getDatosMatricula();

				if(lddp_datosMatricula == null)
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

				lcsm_matriculasFinal           = new LinkedList<SolicitudMatricula>();
				lpaiu_matriculas               = lddp_datosMatricula.getActosAsociadosGeneral();
				lcdas_datosAntAgregados        = lddp_datosMatricula.getDatosAntSisAgregados();
				lb_cirMatrOblig                = isCirculoMatriculaObligatorio();
				ls_idTipoRequiereMatricula     = lddp_datosMatricula.getEleccionRequiereMatricula();

				if(!StringUtils.isValidString(ls_idTipoRequiereMatricula))
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_REQUIERE_MATRICULA);

				{
					String ls_tipoRegistroCertificado;

					ls_tipoRegistroCertificado = StringUtils.getStringNotNull(
						    lddp_datosMatricula.getSeccionTipoMatricula()
						);

					if(ls_tipoRegistroCertificado.equals(EstadoCommon.N))
						ls_solicitud.setTipoRegistroCertificado(EstadoCommon.M);
					else
						ls_solicitud.setTipoRegistroCertificado(EstadoCommon.I);
				}

				if(CollectionUtils.isValidCollection(lpaiu_matriculas))
				{
					for(PredioActoIU lpaiu_data : lpaiu_matriculas)
					{
						if(lpaiu_data != null)
						{
							String             ls_idCirculo;
							Long               ll_idMatricula;
							SolicitudMatricula lsm_solMat;
							long               ll_cantidad;

							ls_idCirculo       = lpaiu_data.getIdCirculo();
							ll_idMatricula     = lpaiu_data.getIdMatricula();
							lsm_solMat         = new SolicitudMatricula();
							ll_cantidad        = NumericUtils.getLong(lpaiu_data.getCantidad());

							if(!StringUtils.isValidString(ls_idCirculo))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

							if(!NumericUtils.isValidLong(ll_idMatricula))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);

							if(ll_cantidad < 1)
							{
								Object[] loa_args;

								loa_args     = new String[2];

								loa_args[0]     = ls_idCirculo;
								loa_args[1]     = StringUtils.getString(ll_idMatricula);

								throw new B2BException(
								    ErrorKeys.ERROR_SIN_CANTIDAD_CERTIFICADOS_CIRCULO_MATRICULA, loa_args
								);
							}

							if(isRequiereCodigoCatastralEnSolicitud())
								lsm_solMat.setCedulaCatastral(lpaiu_data.getNumeroPredial());

							lsm_solMat.setIdCirculo(ls_idCirculo);
							lsm_solMat.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
							lsm_solMat.setCantidadCertificados(NumericUtils.getBigDecimal(ll_cantidad));

							lcsm_matriculasFinal.add(lsm_solMat);
						}
					}

					lb_matriculasValidas = true;
				}
				else if(lb_cirMatrOblig)
					throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA);

				if(isRequiereDatosAntSistema() && !CollectionUtils.isValidCollection(lcdas_datosAntAgregados))
					throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_ANT_SISTEMA_SIN_CIRCULO);

				if(lcsm_matriculasFinal.isEmpty())
					lcsm_matriculasFinal = null;

				lc_certificado.setDatosAntiguoSistema(lcdas_datosAntAgregados);
				lc_certificado.setMatriculas(lcsm_matriculasFinal);
				lc_certificado.setIdTipoRequiereMatricula(ls_idTipoRequiereMatricula);
				lc_certificado.setDatosAntiguoSistemaNuevaEntrada(lddp_datosMatricula.getDatosAntiguoSistema());
				lc_certificado.setDetalleAntSistemaNuevaEntrada(lddp_datosMatricula.getDetallesAntSisAgregados());
			}

			{
				Persona lp_persona;

				lp_persona = getDatosIdentificacion();

				if(lp_persona != null)
				{
					String ls_idTipoDoc;

					ls_idTipoDoc = lp_persona.getIdDocumentoTipo();

					if(StringUtils.isValidString(ls_idTipoDoc))
					{
						String ls_documento;

						ls_documento = lp_persona.getNumeroDocumento();

						validateStyles(is_idForm + "idITDocumentoDatosId", lfc_faces, ls_documento, lb_focus);

						if(!StringUtils.isValidString(ls_documento))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_DOC);

						if(ls_idTipoDoc.equals(IdentificadoresCommon.NIT))
						{
							String ls_razonSocial;

							ls_razonSocial = lp_persona.getRazonSocial();

							validateStyles(is_idForm + "idITRazonSocialDatosId", lfc_faces, ls_razonSocial, lb_focus);

							if(!StringUtils.isValidString(ls_razonSocial))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
						}
						else
						{
							String ls_primerNombre;
							String ls_primerApellido;

							ls_primerNombre       = lp_persona.getPrimerNombre();
							ls_primerApellido     = lp_persona.getPrimerApellido();

							validateStyles(is_idForm + "idITPNombreDatosId", lfc_faces, ls_primerNombre, lb_focus);

							if(!StringUtils.isValidString(ls_primerNombre))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);

							validateStyles(is_idForm + "idITPApellidoDatosId", lfc_faces, ls_primerApellido, lb_focus);

							if(!StringUtils.isValidString(ls_primerApellido))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
						}

						lc_certificado.setPersona(lp_persona);
					}
				}
			}

			{
				BeanDireccion                 lbd_beanDireccion;
				SolicitudDireccionCertificado lsdc_direccion;

				lbd_beanDireccion     = getBeanDireccion();
				lsdc_direccion        = lbd_beanDireccion.validarCamposDireccionCertificado();

				lc_certificado.setDireccion(lsdc_direccion);
			}

			lc_certificado.setSolicitud(ls_solicitud);
			lc_certificado.setEsCertificadosMasivos(isCertificadosMasivos());

			icr_certificadosRemote.guardarDatosTramite(
			    lc_certificado, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			if(lb_matriculasValidas)
				validarNumeroAnotaciones(lc_certificado.getMatriculas());
		}
		else
			throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
	}

	/**
	 * Limpia la información que pudiera estar contenida en el tab de datos del tramite.
	 */
	private void limpiarTabDatosTramite()
	{
		{
			Solicitud ls_solicitud;

			ls_solicitud = getSolicitud();

			if(ls_solicitud != null)
			{
				ls_solicitud.setIdTurnoAnt(null);
				ls_solicitud.setNirAsociado(null);
			}

			setRespuestaNuevaEntrada(null);
			setTurnosRelacionadosNuevaEntrada(null);
		}
	}

	/**
	 * Verifica si para las matrículas agregadas existen más de 150 anotaciones asociadas.
	 *
	 * @param acsm_matricula Colección de matrículas a verificar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarNumeroAnotaciones(Collection<SolicitudMatricula> acsm_matricula)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(acsm_matricula))
		{
			for(SolicitudMatricula lsm_data : acsm_matricula)
			{
				if(lsm_data != null)
				{
					String ls_idCirculo;
					Long   ll_idMatricula;

					ls_idCirculo       = lsm_data.getIdCirculo();
					ll_idMatricula     = NumericUtils.getLongWrapper(lsm_data.getIdMatricula());

					if(
					    icr_certificadosRemote.validarNumeroAnotacionesPredioInconsistente(
						        ls_idCirculo, ll_idMatricula, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						    )
					)
					{
						Object[]      loa_args;
						StringBuilder lsb_matricula;

						loa_args          = new String[1];
						lsb_matricula     = new StringBuilder();

						lsb_matricula.append(ls_idCirculo);
						lsb_matricula.append(IdentificadoresCommon.SIMBOLO_GUION);
						lsb_matricula.append(ll_idMatricula);

						loa_args[0] = lsb_matricula.toString();

						addMessage(MessagesKeys.MENSAJE_CERTIFICADO_EXTENSO, loa_args);

						actualizarComponente(is_messageIdGrowl);
					}
				}
			}
		}
	}
}
