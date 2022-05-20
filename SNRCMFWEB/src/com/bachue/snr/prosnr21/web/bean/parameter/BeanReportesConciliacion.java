package com.bachue.snr.prosnr21.web.bean.parameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.logging.LoggerHandler;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;
import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.Expediente;
import com.bachue.snr.prosnr21.model.pgn.HoraEjecucionProceso;
import com.bachue.snr.prosnr21.model.pgn.Periodicidad;
import com.bachue.snr.prosnr21.model.pgn.RPTPrograma;
import com.bachue.snr.prosnr21.model.pgn.TipoDocumental;
import com.bachue.snr.prosnr21.web.bean.BaseBean;
import com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanPartidasTipoA;


// TODO: Auto-generated Javadoc
/**
 * Clase para el manejo de Proceso de Conciliación.
 *
 * @author Kevin Pulido
 */
@ManagedBean(name = "beanReportesConciliacion")
@SessionScoped
public class BeanReportesConciliacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1113247854737475199L;
	
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanPartidasTipoA.class);

	/** Constante CS_ID_FORM. */
	private static final String cs_ID_FORM = "fProcesoConciliacionDetalle";

	/** Constante CS_ID_GROWL. */
	private static final String cs_ID_GROWL = cs_ID_FORM + ":globalMsg";

	/** Propiedad ichep hora ejecucion automatica. */
	private Collection<HoraEjecucionProceso> ichep_horaEjecucionAutomatica;

	/** Propiedad icpc colección de procesos de conciliación. */
	private Collection<Periodicidad> icp_listPeriodicidad;
	
	/** Propiedad ictd list tipo documental. */
	private Collection<TipoDocumental> ictd_listTipoDocumental;

	/**
	 * Retorna Objeto o variable de valor list tipo documental.
	 *
	 * @return el valor de list tipo documental
	 */
	public Collection<TipoDocumental> getListTipoDocumental() {
		return ictd_listTipoDocumental;
	}

	/**
	 * Modifica el valor de ListTipoDocumental.
	 *
	 * @param actd_listTipoDocumental de actd list tipo documental
	 */
	public void setListTipoDocumental(Collection<TipoDocumental> actd_listTipoDocumental) {
		ictd_listTipoDocumental = actd_listTipoDocumental;
	}

	/** Propiedad icpc colección de procesos de conciliación. */
	private Collection<RPTPrograma> icpc_auditoria;

	/** Propiedad il hora. */
	private Long il_hora;
	
	/** Propiedad ics tipo archivo final. */
	private Collection<String> ics_tipoArchivo;
	
	/** Propiedad ice list expedientes. */
	private Collection<Expediente> ice_listExpedientes;
	

	/** Propiedad ics tipo archivo. */
	private Collection<String> ics_listTipoArchivo;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private RPTPrograma irptp_parametros;

	/** Propiedad ipc proceso conciliación. */
	private RPTPrograma rptp_reporteConciliacion;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}
	
	/**
	 * Retorna Objeto o variable de valor tipo archivo.
	 *
	 * @return el valor de tipo archivo
	 */
	public Collection<String> getListTipoArchivo() {
		
		if(ics_listTipoArchivo == null)
		{
			String ls_caracter;
			ls_caracter = null;

			try
			{
				ls_caracter = ipr_parameterRemote.obtenerCaracterConstante(ConstanteCommon.TIPOS_EXTENSION_ARCHIVO_REPORTES);
				
				if(StringUtils.isValidString(ls_caracter))
					ics_listTipoArchivo = convertStringToCollection(ls_caracter);
								
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("getTipoArchivo", lb2be_e);
				addMessage(lb2be_e);
				actualizarComponente(cs_ID_GROWL);
			}
		}

		return ics_listTipoArchivo;
	}

	/**
	 * Modifica el valor de TipoArchivo.
	 *
	 * @param acs_tipoArchivo de acs tipo archivo
	 */
	public void setListTipoArchivo(Collection<String> acs_tipoArchivo) {
		
		ics_listTipoArchivo = acs_tipoArchivo;
	}
	

	/**
	 * Modifica el valor de Auditoria.
	 *
	 * @param acpc_auditoria de acpc auditoria
	 */
	public void setAuditoria(Collection<RPTPrograma> acpc_auditoria)
	{
		icpc_auditoria = acpc_auditoria;
	}

	/**
	 * Retorna Objeto o variable de valor auditoria.
	 *
	 * @return Retorna el valor de la propiedad auditoria
	 */
	public Collection<RPTPrograma> getAuditoria()
	{
		return icpc_auditoria;
	}

	/**
	 * Modifica el valor de Hora.
	 *
	 * @param al_ahora de al ahora
	 */
	public void setHora(Long al_ahora)
	{
		il_hora = al_ahora;
	}

	/**
	 * Retorna Objeto o variable de valor hora.
	 *
	 * @return Retorna el valor de la propiedad hora
	 */
	public Long getHora()
	{
		return il_hora;
	}

	/**
	 * Modifica el valor de HoraEjecucionAutomatica.
	 *
	 * @param ahep_horaEjecucionAutomatica de ahep hora ejecucion automatica
	 */
	public void setHoraEjecucionAutomatica(Collection<HoraEjecucionProceso> ahep_horaEjecucionAutomatica)
	{
		ichep_horaEjecucionAutomatica = ahep_horaEjecucionAutomatica;
	}

	/**
	 * Retorna Objeto o variable de valor hora ejecucion automatica.
	 *
	 * @return Retorna el valor de la propiedad horaEjecucionAutomatica
	 */
	public Collection<HoraEjecucionProceso> getHoraEjecucionAutomatica()
	{
		return ichep_horaEjecucionAutomatica;
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
	 * Modifica el valor de ListPeriodicidad.
	 *
	 * @param acp_listPeriodicidad de acp list periodicidad
	 */
	public void setListPeriodicidad(Collection<Periodicidad> acp_listPeriodicidad)
	{
		icp_listPeriodicidad = acp_listPeriodicidad;
	}
	
	
	/**
	 * Lista periodicidad.
	 */
	public void listaPeriodicidad ()
	{
		
		Collection<Periodicidad> lcp_datos;
		lcp_datos = new ArrayList<>(0);
		
		try
		{
			lcp_datos = ipr_parameterRemote.buscarPeriodicidad(
					getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			
			setListPeriodicidad(lcp_datos);
			
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
		
		
	}

	/**
	 * Retorna Objeto o variable de valor list periodicidad.
	 *
	 * @return el valor de list periodicidad
	 */
	public Collection<Periodicidad> getListPeriodicidad()
	{
		return icp_listPeriodicidad;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param arptp_parametro asigna el valor a la propiedad parametros
	 */
	public void setParametros(RPTPrograma arptp_parametro)
	{
		irptp_parametros = arptp_parametro;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public RPTPrograma getParametros()
	{
		if(irptp_parametros == null)
			irptp_parametros = new RPTPrograma();

		return irptp_parametros;
	}

	/**
	 * Modifica el valor de tipo documento publico.
	 *
	 * @param apc_parametro asigna el valor a la propiedad tipo documento publico
	 */
	public void setReporteConciliacion(RPTPrograma apc_parametro)
	{
		rptp_reporteConciliacion = apc_parametro;
	}

	/**
	 * Retorna el valor de tipo documento publico.
	 *
	 * @return el valor de tipo documento publico
	 */
	public RPTPrograma getReporteConciliacion()
	{
		if(rptp_reporteConciliacion == null)
			rptp_reporteConciliacion = new RPTPrograma();

		return rptp_reporteConciliacion;
	}
	
	/**
	 * Retorna Objeto o variable de valor tipo archivo.
	 *
	 * @return el valor de tipo archivo
	 */
	public Collection<String> getTipoArchivo() {
		return ics_tipoArchivo;
	}

	/**
	 * Modifica el valor de TipoArchivo.
	 *
	 * @param acs_tipoArchivo de acs tipo archivo
	 */
	public void setTipoArchivo(Collection<String> acs_tipoArchivo) {
		ics_tipoArchivo = acs_tipoArchivo;
	}
	
	/**
	 * Retorna Objeto o variable de valor list expedientes.
	 *
	 * @return el valor de list expedientes
	 */
	public Collection<Expediente> getListExpedientes() {
				
			ice_listExpedientes=buscarExpediente();
		
		return ice_listExpedientes;
	}

	/**
	 * Modifica el valor de ListExpedientes.
	 *
	 * @param ace_listExpedientes de ace list expedientes
	 */
	public void setListExpedientes(Collection<Expediente> ace_listExpedientes) {
		ice_listExpedientes = ace_listExpedientes;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @param rptp_programaReportesConciliacion correspondiente al valor del tipo de objeto TipoDocumentoPublico
	 * @return devuelve el valor de String
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String botonModificar(RPTPrograma rptp_programaReportesConciliacion) throws B2BException
	{
		String ls_return;
		
		ls_return = null;
		
		listaPeriodicidad();

		if(rptp_programaReportesConciliacion != null)
		{
			Collection<RPTPrograma> lcrptp_auditoria;

			lcrptp_auditoria = new ArrayList<RPTPrograma>();

			lcrptp_auditoria.add(rptp_programaReportesConciliacion);

			setHora(NumericUtils.getLongWrapper(rptp_programaReportesConciliacion.getHoraProgramada()));
			calcularHoras();

			setAuditoria(lcrptp_auditoria);
			setReporteConciliacion(rptp_programaReportesConciliacion);
			setTipoArchivo(convertStringToCollection(rptp_programaReportesConciliacion.getTipoArchivo()));

			setInsertar(false);

			ls_return = NavegacionCommon.REPORTES_CONCILIACION_DETALLE;
		}

		return ls_return;
	}
	

	

	/**
	 *  Método que retorna todas las entidades recaudadoras de conciliación.
	 *
	 * Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcpc_procesoConciliacion;
		lcpc_procesoConciliacion = null;

		try
		{
			lcpc_procesoConciliacion = ipr_parameterRemote.buscarEntidadRecaudadoraConciliacion(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	
	/**
	 * Buscar tipo documental.
	 *
	 * @param as_param de as param
	 * @return el valor de collection
	 */
	public Collection<TipoDocumental> buscarTipoDocumental (String as_param){
		
		Collection<TipoDocumental> lctd_tipoDocumentales;
		lctd_tipoDocumentales = null;
		
		if(StringUtils.isValidString(as_param)) 
		{
			
			try
			{
				lctd_tipoDocumentales = ipr_parameterRemote.findTipoDocumentalByExpediente(as_param);
				setListTipoDocumental(lctd_tipoDocumentales);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
			
		}
		
		return lctd_tipoDocumentales;
		
	}
	
	/**
	 * Buscar expediente.
	 *
	 * @return el valor de collection
	 */
	public Collection<Expediente> buscarExpediente(){
		
		Collection<Expediente> lce_expedientes;
		lce_expedientes = null;

		try
		{
			lce_expedientes = ipr_parameterRemote.findExpedientes();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
		
		return lce_expedientes;
	}
	
	/**
	 * Méotodo que retorna una colección con todos los procesos de conciliación.
	 *
	 *@return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 */
	public Collection<RPTPrograma> buscarReporteProgramaConciliacion()
	{
		Collection<RPTPrograma> lcrptp_reporte;
		lcrptp_reporte = null;

		try
		{
			lcrptp_reporte = ipr_parameterRemote.buscarProcesosRptProgramas(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcrptp_reporte;
	}

	/**
	 * Método que calcula horas proceso conciliacion.
	 *
	 *
	 */
	public void calcularHoras()
	{
		try
		{
			setHoraEjecucionAutomatica(
			    ipr_parameterRemote.calcularHorasProcesoConciliacion(
			        Long.valueOf(0L), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método para  cambiar estado para saber si se desea insertar un nuevo Proceso Conciliación.
	 *
	 * @return Retorna un objeto de tipo <code>String </code> que contiene la regla de navegación correspondiente.
	 */
	public String cambiarEstado()
	{
		setInsertar(true);
		setReporteConciliacion(new RPTPrograma());
		listaPeriodicidad();

		try
		{
			setHoraEjecucionAutomatica(
			    ipr_parameterRemote.calcularHorasProcesoConciliacion(
			        Long.valueOf(0L), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return NavegacionCommon.REPORTES_CONCILIACION_DETALLE;
	}
	

	/**
	 * Método encargado de limpiar los atributos de la clase.
	 */
	public void clear()
	{
		setHoraEjecucionAutomatica(null);
		setHora(null);
		setAuditoria(null);
		setAuditoria(null);
		setReporteConciliacion(null);
		setParametros(null);
		setListPeriodicidad(null);
		setTipoArchivo(null);
		setListTipoArchivo(null);
	}
	
	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO.
	 *
	 * @param arptp_reportesConciliacion objeto del cual se edxtrae el id para la consulta en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(RPTPrograma arptp_reportesConciliacion)
	    throws B2BException
	{
		setReporteConciliacion(arptp_reportesConciliacion);
		setInsertar(false);
	}
	
	/**
	 * Convert string to collection.
	 *
	 * @param as_caracter de as caracter
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> convertStringToCollection (String as_caracter) 
			throws B2BException 
	{
		
		Collection<String> lcs_response;
		
		lcs_response = new ArrayList<>(0);
		
			if(StringUtils.isValidString(as_caracter))
			{
				String[] lsa_temp;
				String[] lsa_lista;

				as_caracter  	= as_caracter.replaceAll("\\.", "");
				lsa_temp        = as_caracter.split(IdentificadoresCommon.SIMBOLO_COMA_TXT);
				lsa_lista       = new String[lsa_temp.length];

				for(int i_iterador = 0; i_iterador < lsa_temp.length; i_iterador++)
					lsa_lista[i_iterador] = lsa_temp[i_iterador].trim();

				lcs_response = Arrays.asList(lsa_lista);
			}
			
		return lcs_response;
		
	}

	/**
	 * Método encargado de salvar proceso conciliacion.
	 *
	 * @return el valor de la regla de navegación a la cual se redirige elmétodo después de salvar.
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
			RPTPrograma lrptp_parametros;

			lrptp_parametros     = getReporteConciliacion();

			{
				String ls_nombre;

				ls_nombre     = lrptp_parametros.getNombre();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idNombre", lfc_context,
					    ls_nombre, lb_focus
					);
				
				if(!StringUtils.isValidString(ls_nombre))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
			}
			
			{
				String ls_sql;

				ls_sql     = lrptp_parametros.getSqlEjecucion();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idSql", lfc_context,
					    ls_sql, lb_focus
					);
				

				if(!StringUtils.isValidString(ls_sql))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_SQL);
			}
			
			{
				String ls_periodicidad;

				ls_periodicidad     = lrptp_parametros.getIdPeriodicidad();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idPeriodicidad", lfc_context,
					    ls_periodicidad, lb_focus
					);

				if(!StringUtils.isValidString(ls_periodicidad))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PERIODICIDAD);
			}
			
			{
				String ls_horaProceso;

				ls_horaProceso     = lrptp_parametros.getHoraProceso();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idHorarioEjecucion", lfc_context,
					    ls_horaProceso, lb_focus
					);

				if(!StringUtils.isValidString(ls_horaProceso))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_HORA_EJECUCION);
				
				{
					String[] lsa_horaProceso;

					lsa_horaProceso = ls_horaProceso.split(":");

					if((lsa_horaProceso != null) && (lsa_horaProceso.length == 2))
					{
						lrptp_parametros.setHoraProgramada(NumericUtils.getLong(lsa_horaProceso[0]));
						lrptp_parametros.setMinutosProgramada(NumericUtils.getLong(lsa_horaProceso[1]));
					}
				}
			}
			
			{
				int          li_validar;
				Date 		 ld_fecha;
				boolean 	 lb_focus1; 
				
				ld_fecha     = lrptp_parametros.getFechaProximaEjecucion();
				lb_focus1    = true;
				li_validar = 0;
				
				if(ld_fecha == null)
					lb_focus = validateStyles(
						    ":" + cs_ID_FORM + ":idFechaMovimiento", lfc_context,
						    ld_fecha, lb_focus
						);
				else if(ld_fecha.before(new Date())) {
					lb_focus1 = validateStyles(
						    ":" + cs_ID_FORM + ":idFechaMovimiento", lfc_context,
						    ld_fecha, lb_focus1
						);
					
					lb_focus1      = false;
					li_validar     = 1;
					
				}
				else
					lb_focus1 = validateStyles(
						    ":" + cs_ID_FORM + ":idFechaMovimiento", lfc_context,
						    ld_fecha, lb_focus1
						);
					
				if(li_validar == 0)
				{
					if(!lb_focus || !lb_focus1)
						throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);
				}
				else
				{
					throw new B2BException(MessagesKeys.ERROR_FECHA_MENOR_A_ACTUAL);
				}

			}
			
			{
				Collection<String> lbs_tipoArchivo;
				String ls_tipoArchivo;

				lbs_tipoArchivo     = getTipoArchivo();
				ls_tipoArchivo 		= null;
				
						
				if(CollectionUtils.isValidCollection(lbs_tipoArchivo))
				{
					StringBuilder lsb_temp = new StringBuilder();
					
					for (String string : lbs_tipoArchivo) {
						lsb_temp.append(string);
						lsb_temp.append(IdentificadoresCommon.SIMBOLO_COMA_TXT);
					}
					
					String ls_lastCharacter;
					
					ls_tipoArchivo 	 = lsb_temp.toString();
					ls_lastCharacter = ls_tipoArchivo.substring(ls_tipoArchivo.length()-1);
					
					if(ls_lastCharacter.equalsIgnoreCase(IdentificadoresCommon.SIMBOLO_COMA_TXT)) 
						ls_tipoArchivo = ls_tipoArchivo.substring(0, ls_tipoArchivo.length()-1);

					if(StringUtils.isValidString(ls_tipoArchivo))
						lrptp_parametros.setTipoArchivo(ls_tipoArchivo);
				}
				
				
				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idTipoArchivo", lfc_context,
					    ls_tipoArchivo, lb_focus
					);

				if(!StringUtils.isValidString(ls_tipoArchivo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_TIPO_ARCHIVO);
			}
			
			{
				String ls_expediente;

				ls_expediente     = lrptp_parametros.getExpedientes();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idExpediente", lfc_context,
					    ls_expediente, lb_focus
					);

				if(!StringUtils.isValidString(ls_expediente))
					throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);
			}
			
			{
				String ls_tipoDocumental;

				ls_tipoDocumental     = lrptp_parametros.getTiposDocumentales();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idtiposDoc", lfc_context,
					    ls_tipoDocumental, lb_focus
					);

				if(!StringUtils.isValidString(ls_tipoDocumental))
					throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);
			}
			
			{
				String ls_activo;

				ls_activo     = lrptp_parametros.getActivo();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idActivo", lfc_context,
					    ls_activo, lb_focus
					);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);
			}
			
			{
				String ls_solicitaCuenta;

				ls_solicitaCuenta     = lrptp_parametros.getSolicitaCTA();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idSolicitaCta", lfc_context,
					    ls_solicitaCuenta, lb_focus
					);

				if(!StringUtils.isValidString(ls_solicitaCuenta))
					throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);
			}

			
			lrptp_parametros = ipr_parameterRemote.salvarReportesConciliacion(
				    lrptp_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lrptp_parametros != null)
			{
				com.bachue.snr.prosnr21.web.listeners.scheduler.ConciliacionesScheduler lcs_agendador;

				lcs_agendador = com.bachue.snr.prosnr21.web.util.ContextHelper.getSchedulerContainer(
					    com.bachue.snr.prosnr21.web.util.FacesUtils.getServletContext()
					);

				if(lcs_agendador != null)
				{
					lcs_agendador.borrarJobReporte(lrptp_parametros);

					if(lrptp_parametros.getActivo().equals(com.bachue.snr.prosnr01.common.constants.EstadoCommon.S))
						lcs_agendador.iniciarJobReporte(lrptp_parametros, getLocalIpAddress());
				}
			}
			
			setParametros(null);

			setReporteConciliacion(null);

			ls_result = NavegacionCommon.REPORTES_CONCILIACION;

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
			clh_LOGGER.error("salvar", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
			ls_result = null;
		}

		return ls_result;
	}
	
	/**
	 * Regresar.
	 *
	 * @return el valor de string
	 */
	public String regresar () {
		
		clear();
		return "reportesConciliacion?faces-redirect=true";
		
	}
}
