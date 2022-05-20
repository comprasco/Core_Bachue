package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanCorreccion;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.calificacion.Calificacion;
import com.bachue.snr.prosnr01.model.calificacion.ConfrontacionCorrectiva;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.ValidacionDocumento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;

import com.bachue.snr.prosnr01.web.util.ColumnModel;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCorrectivo.
 *
 * @author garias
 */
@SessionScoped
@ManagedBean(name = "correctivosBean")
public class BeanCorrectivo extends BeanConfrontacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -839561737497912368L;

	/** Constante is_idForm. */
	private static final String is_idForm = "confrontacion5";

	/** Constante is_idFormConfrontacion. */
	private static final String is_idFormConfrontacion = "confrontacion";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCorrectivo.class);

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icpaui cpaui. */
	private Collection<PredioActoIU> icpaui_cpaui;

	/** Propiedad icpaui_matriculasInsertar. */
	private Collection<PredioActoIU> icpaui_matriculasInsertar;

	/** Propiedad icsm reabrir matriculas. */
	private Collection<PredioActoIU> icsm_reabrirMatriculas;

	/** Propiedad icto tipo oficina documento. */
	private Collection<TipoOficina> icto_tipoOficinaDocumento;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad ilpa selected matriculas eliminar. */
	private List<PredioActoIU> ilpa_selectedMatriculasEliminar;

	/** Propiedad ilpa selected matriculas insertar. */
	private List<PredioActoIU> ilpa_selectedMatriculasInsertar;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad mensaje predio inconsistente. */
	private String is_mensajePredioInconsistente;

	/**
	 * Propiedad is observaciones temporales.
	 */
	private String is_observacionesTemporales;

	/** Propiedad ib es etapa correcciones 130. */
	private boolean ib_esEtapaCorrecciones130;

	/**
	 * Propiedad il etapa
	 */
	private long il_idEtapa;

	/**
	 * Instancia un nuevo objeto bean correctivo.
	 */
	public BeanCorrectivo()
	{
		clear();
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ad_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		if(id_documento == null)
		{
			id_documento = new Documento();
			id_documento.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return id_documento;
	}

	/**
	 * Modifica el valor de es etapa correcciones 130.
	 *
	 * @param ab_b asigna el valor a la propiedad es etapa correcciones 130
	 */
	public void setEsEtapaCorrecciones130(boolean ab_b)
	{
		ib_esEtapaCorrecciones130 = ab_b;
	}

	/**
	 * Valida la propiedad es etapa correcciones 130.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es etapa correcciones 130
	 */
	public boolean isEsEtapaCorrecciones130()
	{
		return ib_esEtapaCorrecciones130;
	}

	/**
	 * Metodo encargado de modificar el valor de la propiedad.
	 *
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id pais.
	 *
	 * @param as_s asigna el valor a la propiedad id pais
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna el valor de id pais.
	 *
	 * @return el valor de id pais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de matriculas eliminar.
	 *
	 * @param acpaiu_actos asigna el valor a la propiedad matriculas eliminar
	 */
	public void setMatriculasEliminar(Collection<PredioActoIU> acpaiu_actos)
	{
		icpaui_cpaui = acpaiu_actos;
	}

	/**
	 * Retorna el valor de matriculas eliminar.
	 *
	 * @return el valor de matriculas eliminar
	 */
	public Collection<PredioActoIU> getMatriculasEliminar()
	{
		if(!CollectionUtils.isValidCollection(icpaui_cpaui))
		{
			Collection<PredioActoIU> lcpaiu_paui;

			lcpaiu_paui = cargarTmp(EstadoCommon.ENTREGA);

			if(CollectionUtils.isValidCollection(lcpaiu_paui))
				icpaui_cpaui = lcpaiu_paui;
			else
				icpaui_cpaui = null;
		}

		return icpaui_cpaui;
	}

	/**
	 * Modifica el valor de matriculas insertar.
	 *
	 * @param acpaiu_actos asigna el valor a la propiedad matriculas insertar
	 */
	public void setMatriculasInsertar(Collection<PredioActoIU> acpaiu_actos)
	{
		icpaui_matriculasInsertar = acpaiu_actos;
	}

	/**
	 * Retorna el valor de matriculas insertar.
	 *
	 * @return el valor de matriculas insertar
	 */
	public Collection<PredioActoIU> getMatriculasInsertar()
	{
		if(!CollectionUtils.isValidCollection(icpaui_matriculasInsertar))
		{
			Collection<PredioActoIU> lcpaiu_paui;

			lcpaiu_paui = cargarTmp(EstadoCommon.INACTIVO);

			if(CollectionUtils.isValidCollection(lcpaiu_paui))
				icpaui_matriculasInsertar = lcpaiu_paui;
			else
				icpaui_matriculasInsertar = null;
		}

		return icpaui_matriculasInsertar;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad mensajePredioInconsistente por as_s
	 */
	public void setMensajePredioInconsistente(String as_s)
	{
		is_mensajePredioInconsistente = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad mensajePredioInconsistente
	 */
	public String getMensajePredioInconsistente()
	{
		return is_mensajePredioInconsistente;
	}

	/**
	 * Modifica el valor de Observaciones temporales.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad ObservacionesTemporales
	 */
	public void setObservacionesTemporales(String as_s)
	{
		is_observacionesTemporales = as_s;
	}

	/**
	 * Get observaciones temporales.
	 *
	 * @return el valor de string
	 */
	public String getObservacionesTemporales()
	{
		return is_observacionesTemporales;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen()
	{
		Collection<OficinaOrigen> lcidt_datos;
		Documento                 ld_documento;

		lcidt_datos      = null;
		ld_documento     = getDocumento();

		try
		{
			if(ld_documento != null)
			{
				OficinaOrigen loo_oficinaOrigen;
				loo_oficinaOrigen = new OficinaOrigen();

				loo_oficinaOrigen.setIdTipoOficina(ld_documento.getIdTipoOficina());
				loo_oficinaOrigen.setIdPais(ld_documento.getIdPais());
				loo_oficinaOrigen.setIdDepartamento(ld_documento.getIdDepartamento());
				loo_oficinaOrigen.setIdMunicipio(ld_documento.getIdMunicipio());

				lcidt_datos = irr_referenceRemote.findOficinaOrigen(loo_oficinaOrigen);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Retorna el valor de oficina origen apertura.
	 *
	 * @return el valor de oficina origen apertura
	 */
	public Collection<OficinaOrigen> getOficinaOrigenApertura()
	{
		Collection<OficinaOrigen> lcidt_datos;

		lcidt_datos = null;

		try
		{
			Apertura la_apertura;

			la_apertura = getApertura();

			if(la_apertura.getIdPais() != null)
			{
				OficinaOrigen oficina = new OficinaOrigen();
				oficina.setIdTipoOficina(la_apertura.getIdTipoOficina());
				oficina.setIdPais(la_apertura.getIdPais());
				oficina.setIdDepartamento(la_apertura.getIdDepartamento());
				oficina.setIdMunicipio(la_apertura.getIdMunicipio());
				lcidt_datos = irr_referenceRemote.findOficinaOrigen(oficina);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Modifica el valor de reabrir matriculas.
	 *
	 * @param acpaiu_cpaiu asigna el valor a la propiedad reabrir matriculas
	 */
	public void setReabrirMatriculas(Collection<PredioActoIU> acpaiu_cpaiu)
	{
		icsm_reabrirMatriculas = acpaiu_cpaiu;
	}

	/**
	 * Retorna el valor de reabrir matriculas.
	 *
	 * @return el valor de reabrir matriculas
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<PredioActoIU> getReabrirMatriculas()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(icsm_reabrirMatriculas))
		{
			Collection<SolicitudMatricula> lcsm_cm;
			SolicitudMatricula             lsm_sm;

			lsm_sm                     = new SolicitudMatricula();
			icsm_reabrirMatriculas     = new ArrayList<PredioActoIU>();

			lsm_sm.setIdSolicitud(getIdSolicitud());
			lsm_sm.setIdCirculo(getCirculo());

			lcsm_cm = irr_calificacionRemote.findSolicitudMatriculaEstado(lsm_sm);

			if(CollectionUtils.isValidCollection(lcsm_cm))
			{
				for(SolicitudMatricula lsm_actual : lcsm_cm)
				{
					if(lsm_actual != null)
					{
						PredioActoIU lpr_ui;

						lpr_ui = new PredioActoIU();

						lpr_ui.setMatricula(lsm_actual.getIdCirculo() + "-" + lsm_actual.getIdMatricula());
						lpr_ui.setDireccion(lsm_actual.getIdUsuarioCreacion());
						lpr_ui.setEstado(lsm_actual.getEstado());
						lpr_ui.setCertificadoAsociado(false);

						icsm_reabrirMatriculas.add(lpr_ui);
					}
				}
			}
		}

		return icsm_reabrirMatriculas;
	}

	/**
	 * Modifica el valor de selected matriculas eliminar.
	 *
	 * @param alpa_alpa asigna el valor a la propiedad selected matriculas eliminar
	 */
	public void setSelectedMatriculasEliminar(List<PredioActoIU> alpa_alpa)
	{
		ilpa_selectedMatriculasEliminar = alpa_alpa;
	}

	/**
	 * Retorna el valor de selected matriculas eliminar.
	 *
	 * @return el valor de selected matriculas eliminar
	 */
	public List<PredioActoIU> getSelectedMatriculasEliminar()
	{
		return ilpa_selectedMatriculasEliminar;
	}

	/**
	 * Modifica el valor de selected matriculas insertar.
	 *
	 * @param alpa_alpa asigna el valor a la propiedad selected matriculas insertar
	 */
	public void setSelectedMatriculasInsertar(List<PredioActoIU> alpa_alpa)
	{
		ilpa_selectedMatriculasInsertar = alpa_alpa;
	}

	/**
	 * Retorna el valor de selected matriculas insertar.
	 *
	 * @return el valor de selected matriculas insertar
	 */
	public List<PredioActoIU> getSelectedMatriculasInsertar()
	{
		return ilpa_selectedMatriculasInsertar;
	}

	/**
	 * Retorna el valor de tabs.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor del tipo de objeto String
	 */
	public void getTabs(String as_idTurnoHistoria)
	{
		Collection<Map<String, Object>> lcmso_indicadores;

		if(StringUtils.isValidString(as_idTurnoHistoria))
		{
			try
			{
				lcmso_indicadores = irr_calificacionRemote.getIndicadoresByIdTurno(as_idTurnoHistoria);
			}
			catch(B2BException e)
			{
				lcmso_indicadores = null;
			}

			if(CollectionUtils.isValidCollection(lcmso_indicadores))
			{
				for(Map<String, Object> lmso_mso : lcmso_indicadores)
				{
					for(Map.Entry<String, Object> lmeso_meso : lmso_mso.entrySet())
					{
						if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.DATOS_BASICOS))
							setDatosBasicos(true);
						else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.INSERTAR))
							setInsertaMatricula(true);
						else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.ELIMINAR))
							setEliminaMatricula(true);
						else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.FOLIO))
							setVerificaFolioCerrado(true);
					}
				}
			}
		}
	}

	/**
	 * Modifica el valor de tipo oficina documento.
	 *
	 * @param acto_to asigna el valor a la propiedad tipo oficina documento
	 */
	public void setTipoOficinaDocumento(Collection<TipoOficina> acto_to)
	{
		icto_tipoOficinaDocumento = acto_to;
	}

	/**
	 * Retorna el valor de tipo oficina documento.
	 *
	 * @return el valor de tipo oficina documento
	 */
	public Collection<TipoOficina> getTipoOficinaDocumento()
	{
		return icto_tipoOficinaDocumento;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolver()
	{
		clear();

		return NavegacionCommon.TURNOS;
	}

	/**
	 * Cargar mensaje predio inconsistente.
	 */
	public void cargarMensajePredioInconsistente()
	{
		String ls_matriculas;

		ls_matriculas = null;

		Collection<PredioActoIU> lcpaiu_prediosActosInsertar;
		lcpaiu_prediosActosInsertar     = getSelectedMatriculasInsertar();

		ls_matriculas = cargarMensajePredioInconsistentePredio(lcpaiu_prediosActosInsertar, ls_matriculas, true);

		if(StringUtils.isValidString(ls_matriculas))
		{
			Object[] aoa_messageArgs;

			aoa_messageArgs = new String[1];

			String ls_mensaje;

			aoa_messageArgs[0]     = ls_matriculas;

			ls_mensaje = getMessages()
					             .getString(
					    MessagesKeys.SOLICITUD_TIENE_MATRICULAS_EN_ESTADO_INCONSISTENTE, aoa_messageArgs
					);
			setMensajePredioInconsistente(ls_mensaje);

			addMessage(MessagesKeys.PREDIO_INCONSISTENTE, aoa_messageArgs);
			actualizarComponente(is_idForm);
		}
		else
			setMensajePredioInconsistente(null);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 */
	public Collection<PredioActoIU> cargarTmp(String as_s)
	{
		Collection<PredioActoIU> lcpaiu_paui;

		lcpaiu_paui = new ArrayList<PredioActoIU>();

		try
		{
			if(StringUtils.isValidString(as_s))
			{
				TmpSolicitudMatricula     ltsm_sm;
				TmpSolicitudMatriculaActo ltsma_sma;

				Collection<TmpSolicitudMatricula>     lcsm_smTmp;
				Collection<TmpSolicitudMatriculaActo> lcsma_smaTmp;

				ltsm_sm = new TmpSolicitudMatricula();

				ltsm_sm.setIdSolicitud(getIdSolicitud());
				ltsm_sm.setAccion(as_s);

				lcsm_smTmp = irr_calificacionRemote.findTmpSolicitudMatricula(ltsm_sm);

				if(CollectionUtils.isValidCollection(lcsm_smTmp))
				{
					for(TmpSolicitudMatricula ltmp_actual : lcsm_smTmp)
					{
						if(ltmp_actual != null)
						{
							if(ltmp_actual.getIdCirculo().equalsIgnoreCase(getCirculo()))
							{
								PredioActoIU lpaiu_paiu;
								String       ls_matriculaConcatenada;

								lpaiu_paiu     = new PredioActoIU(getColumns(isIndVinculado()));
								ltsma_sma      = new TmpSolicitudMatriculaActo();

								ltsma_sma.setAccion(ltmp_actual.getAccion());
								ltsma_sma.setIdSolicitud(ltmp_actual.getIdSolicitud());
								ltsma_sma.setIdCirculo(ltmp_actual.getIdCirculo());
								ltsma_sma.setIdMatricula(ltmp_actual.getIdMatricula());

								{
									ls_matriculaConcatenada = ltmp_actual.getIdCirculo() + "-"
										+ ltmp_actual.getIdMatricula();

									lpaiu_paiu.setMatricula(ls_matriculaConcatenada);

									{
										DireccionPredio ld_direccion;

										ld_direccion = new DireccionPredio();

										ld_direccion.setIdCirculo(ltmp_actual.getIdCirculo());
										ld_direccion.setIdMatricula(
										    NumericUtils.getLongWrapper(ltmp_actual.getIdMatricula())
										);

										ld_direccion = irr_registroRemote.findDireccionPredioById(
											    ld_direccion, getUserId()
											);

										if(ld_direccion != null)
											lpaiu_paiu.setDireccion(ld_direccion.getDireccion());
									}
								}

								lcsma_smaTmp = irr_calificacionRemote.findTmpSolicitudMatriculaActo(ltsma_sma);

								if(CollectionUtils.isValidCollection(lcsma_smaTmp))
								{
									for(TmpSolicitudMatriculaActo ltmpa_actual : lcsma_smaTmp)
									{
										if(ltmpa_actual != null)
											lpaiu_paiu.changeActoIU(ltmpa_actual);
									}
								}

								lpaiu_paiu.setEsPredioInconsistente(ltmp_actual.isEsPredioInconsistente());
								lcpaiu_paui.add(lpaiu_paiu);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be)
		{
			clh_LOGGER.error("cargarTmp", lb2be);
		}

		return lcpaiu_paui;
	}

	/** {@inheritdoc} */
	public void clear()
	{
		setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		setEsEtapaCorrecciones130(false);
		setMatriculasEliminar(null);
		setMatriculasInsertar(null);
		setReabrirMatriculas(null);
		setSelectedMatriculasEliminar(null);
		setSelectedMatriculasInsertar(null);
		setMensajePredioInconsistente(null);
		super.clear();
	}

	/**
	 * Departamento.
	 */
	public void departamento()
	{
		Apertura la_apertura;

		la_apertura = getApertura();

		la_apertura.setIdMunicipio(null);
		la_apertura.setIdOficinaOrigen(null);
		findMunicipio();
		getOficinaOrigen();
	}

	/** {@inheritdoc} */
	public void detalleTurnoSeleccionado(RegistroCalificacion arc_item)
	{
		try
		{
			if(arc_item != null)
			{
				String ls_idTurno;

				ls_idTurno = arc_item.getTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					setTurnoVinculado(ls_idTurno);
					setTurnoSeleccionado(true);
					setApertura(arc_item.getApertura());
					setCalificaciones(arc_item.getDataConfrontacion());
					tipoDocumentoValidar();
				}
				else
					throw new B2BException(ErrorKeys.TURNO_SELECCIONADO_INVALIDO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("detalleTurnoSeleccionado", lb2be_e);
			addMessage(lb2be_e);
			setTurnoSeleccionado(false);
			setTurnoVinculado(null);
			setApertura(null);
			setCalificaciones(null);
		}
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findActosByIdSolicitudCirculo(Acto ap_parametros, String as_userId)
	{
		Collection<TipoActo> lca_actos = null;

		try
		{
			lca_actos = irr_registroRemote.findActosById(ap_parametros, as_userId, true, false);
		}
		catch(B2BException lb2be_e)
		{
			lca_actos = null;
		}

		return lca_actos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			if(this.is_idPais != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(this.is_idPais);

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipio()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		try
		{
			String ls_idDepartamento;
			ls_idDepartamento = getApertura().getIdDepartamento();

			if(StringUtils.isValidString(ls_idDepartamento))
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(getIdPais());
				lm_parametros.setIdDepartamento(ls_idDepartamento);

				if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				else
					lcm_municipios = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioApertura()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		try
		{
			String ls_departamento;
			ls_departamento = getApertura().getIdDepartamento();

			if(ls_departamento != null)
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(getIdPais());
				lm_parametros.setIdDepartamento(ls_departamento);

				if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				else
					lcm_municipios = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Pais> findPaises()
	{
		Collection<Pais> lcp_paises;

		try
		{
			lcp_paises = irr_referenceRemote.findPaises(true, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcp_paises = null;
		}

		return lcp_paises;
	}

	/** {@inheritdoc} */
	public String findSolicitudByIdTurnoHistoria(String as_s)
	{
		String ls_solicitud;

		try
		{
			ls_solicitud = irr_calificacionRemote.findIdSolicitudByIdTurnoHistoria(as_s);
		}
		catch(B2BException lb2be_e)
		{
			ls_solicitud = null;
		}

		return ls_solicitud;
	}

	/**
	 * Find tipo entidad apertura.
	 */
	public void findTipoEntidadApertura()
	{
		Apertura la_apertura;

		la_apertura = getApertura();

		try
		{
			if(la_apertura != null)
			{
				TipoOficina lto_to;

				lto_to = new TipoOficina();

				lto_to.setIdTipoOficina(la_apertura.getIdTipoOficina());

				lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

				if(lto_to != null)
				{
					TipoEntidad lte_te;

					lte_te = new TipoEntidad();

					lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

					lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

					la_apertura.setIdTipoEntidad(lte_te.getIdTipoEntidad());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoOficina> findTipoOficina()
	{
		Collection<TipoOficina> lcto_datos;

		lcto_datos = new ArrayList<TipoOficina>();

		try
		{
			Documento ld_d;
			String    ls_idtipoOficina;
			Apertura  la_a;

			la_a     = getApertura();

			ld_d                 = la_a.getDocumento();
			ls_idtipoOficina     = null;

			lcto_datos = irr_referenceRemote.findTipoOficina(ld_d);

			if(CollectionUtils.isValidCollection(lcto_datos))
			{
				if(lcto_datos.size() == 1)
				{
					for(TipoOficina lto_tmp : lcto_datos)
					{
						if(lto_tmp != null)
							ls_idtipoOficina = lto_tmp.getIdTipoOficina();
					}
				}
				else
					ls_idtipoOficina = la_a.getIdTipoOficina();

				setTipoOficinaDocumento(lcto_datos);
				la_a.setIdTipoOficina(ls_idtipoOficina);

				setApertura(la_a);
			}
			else
			{
				TipoOficina ltf_seleccione;
				ltf_seleccione = new TipoOficina();
				ltf_seleccione.setNombre(ConstanteCommon.SELECCIONE);
				lcto_datos.add(ltf_seleccione);
				setTipoOficinaDocumento(lcto_datos);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			lcto_datos = null;
		}

		return lcto_datos;
	}

	/**
	 * Oficina origen apertura.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void oficinaOrigenApertura()
	    throws B2BException
	{
		try
		{
			Collection<OficinaOrigen> lc_oficina;
			lc_oficina = getOficinaOrigenApertura();

			if(lc_oficina == null)
				throw new B2BException(ErrorKeys.NO_OFICINA_ORIGEN);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion5:idGrowl");
		}
	}

	/**
	 * Pais.
	 */
	public void pais()
	{
		getApertura().setIdDepartamento(null);
		getApertura().setIdMunicipio(null);
		findDepartamentos();
		findMunicipio();
		getOficinaOrigen();
	}

	/**
	 * Retorno a la bandeja principal.
	 *
	 * @return String con la pagina a redireccionar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String returnBandeja()
	    throws B2BException
	{
		BeanBandejaConfrontacion lb_beanCalificacion;
		FacesContext             lfc_context;

		lfc_context             = FacesUtils.getFacesContext();
		lb_beanCalificacion     = (BeanBandejaConfrontacion)lfc_context.getApplication()
				                                                           .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_BANDEJA_CONFRONTACION, BeanBandejaConfrontacion.class
				);

		lb_beanCalificacion.clear();
		lb_beanCalificacion.generarDatosTramiteCantidad();

		clear();

		return NavegacionCommon.BANDEJA_MOD_DATOS_BASICOS;
	}

	/**
	 * Método para modificar segun el tramite a realizar por el usuario en la matrícula.
	 *
	 * @return String para saber a que pagina redireccionar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			boolean       lb_procesoTerminado;
			String        ls_observacionesTemp;
			TurnoHistoria lth_turnoHistoriaTemp;

			lb_procesoTerminado       = false;
			lth_turnoHistoriaTemp     = new TurnoHistoria();
			ls_observacionesTemp      = null;

			lth_turnoHistoriaTemp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			lth_turnoHistoriaTemp = irr_referenceRemote.findTurnoHistoriaByid(lth_turnoHistoriaTemp);

			if(lth_turnoHistoriaTemp != null)
			{
				String ls_etadoActividad;

				ls_etadoActividad = lth_turnoHistoriaTemp.getEstadoActividad();

				if(StringUtils.isValidString(ls_etadoActividad))
				{
					lb_procesoTerminado      = ls_etadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA);
					ls_observacionesTemp     = lth_turnoHistoriaTemp.getObservaciones();
				}
			}

			if(lb_procesoTerminado)
			{
				setObservacionesTemporales(ls_observacionesTemp);

				PrimeFaces.current().executeScript("PF('dlgSuspension').show();");
				PrimeFaces.current().ajax().update("fDialogSuspension");
			}
			else
			{
				boolean                             lb_vinculado;
				Collection<ConfrontacionCorrectiva> lccc_ccc;
				FacesContext                        lfc_context;

				lb_vinculado     = isIndVinculado();
				lccc_ccc         = new ArrayList<ConfrontacionCorrectiva>();
				lfc_context      = FacesContext.getCurrentInstance();

				if(lb_vinculado)
				{
					Collection<RegistroCalificacion> lcrc_crc;

					lcrc_crc = getInfoTurnos();

					if(CollectionUtils.isValidCollection(lcrc_crc))
					{
						for(RegistroCalificacion lrc_item : lcrc_crc)
						{
							if(lrc_item != null)
								lccc_ccc.add(
								    validarCorrectivo(
								        lrc_item.getIdTurnoHistoria(), lrc_item.getDataConfrontacion(),
								        lrc_item.getApertura(), "Vinculado", lfc_context
								    )
								);
						}
					}
				}
				else
					lccc_ccc.add(
					    validarCorrectivo(
					        NumericUtils.getLongWrapper(getIdTurnoHistoria()), getCalificaciones(), getApertura(), "",
					        lfc_context
					    )
					);

				if(CollectionUtils.isValidCollection(lccc_ccc))
				{
					irr_calificacionRemote.salvarConfrontacion(
					    lccc_ccc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					if(isEsCertificadosEspeciales())
					{
						String ls_mensajePredioInconsistente;

						ls_mensajePredioInconsistente = getMensajePredioInconsistente();

						if(StringUtils.isValidString(ls_mensajePredioInconsistente))
						{
							BeanAnalistaDeCertificadosEspeciales lbadce_badce;
							lbadce_badce = (BeanAnalistaDeCertificadosEspeciales)lfc_context.getApplication()
									                                                            .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_ANALISTA_DE_CERTIFICADOS_ESPECIALES,
									    BeanAnalistaDeCertificadosEspeciales.class
									);
							lbadce_badce.clear();
							lbadce_badce.generarDatosBandeja();
							lbadce_badce.generarGraficoDeTorta(
							    EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES, false
							);

							ls_result = NavegacionCommon.ANALISTA_DE_CERTIFICADOS_ESPECIALES;
						}
						else
							ls_result = NavegacionCommon.CERTIFICADOS_ESPECIALES;

						BeanDetalleAnalistaDeCertificadosEspeciales lbt_bean;

						lbt_bean = (BeanDetalleAnalistaDeCertificadosEspeciales)lfc_context.getApplication()
								                                                               .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_DETALLE_ANALISTA_CERTIFICADOS,
								    BeanDetalleAnalistaDeCertificadosEspeciales.class
								);

						if(lbt_bean != null)
						{
							lbt_bean.setConfrontacion(false);
							lbt_bean.setInsertaMatricula(false);
							lbt_bean.setEliminaMatricula(false);
						}
					}
					else if(isEsEtapaCorrecciones130())
					{
						if(isInsertaMatricula() || isEliminaMatricula())
						{
							long ll_idEtapa;

							ll_idEtapa = getIdEtapa();

							if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
								    || (ll_idEtapa == EtapaCommon.ID_ETAPA_460)
							)
								ls_result = NavegacionCommon.DETALLE_ACTO;
							else
							{
								BeanTurnos lbt_bean;

								lbt_bean = (BeanTurnos)lfc_context.getApplication()
										                              .evaluateExpressionGet(
										    lfc_context, BeanNameCommon.BEAN_TURNOS, BeanTurnos.class
										);

								lbt_bean.setNirConsulta(null);
								lbt_bean.setIdTurnoConsulta(null);
								lbt_bean.generarData();

								ls_result = NavegacionCommon.TURNOS;
							}
						}
						else if(isVerificaFolioCerrado())
						{
							BeanCorreccion lbc_bean;

							lbc_bean = (BeanCorreccion)lfc_context.getApplication()
									                                  .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_CORRECCION, BeanCorreccion.class
									);

							lbc_bean.setIdTurnoConsulta(null);
							lbc_bean.setNirConsulta(null);
							lbc_bean.generarDatosTramiteCantidad();

							ls_result = NavegacionCommon.ANALISIS_CORRECCION;
						}
					}
					else
					{
						BeanBandejaConfrontacion lbde_bean;

						lfc_context     = FacesUtils.getFacesContext();

						lbde_bean = (BeanBandejaConfrontacion)lfc_context.getApplication()
								                                             .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_BANDEJA_CONFRONTACION,
								    BeanBandejaConfrontacion.class
								);

						if(lbde_bean != null)
						{
							lbde_bean.setIdTurnoConsulta(null);
							lbde_bean.setNirConsulta(null);
							lbde_bean.generarDatosTramiteCantidad();
							ls_result = NavegacionCommon.BANDEJA_MOD_DATOS_BASICOS;
						}
					}

					clear();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_result = null;
			addMessage(lb2be_e);
		}

		return ls_result;
	}

	/** {@inheritdoc} */
	public void salvarTurnoSeleccionado()
	{
		salvarTurnoSeleccionado(getApertura());
	}

	/**
	 * Tipo documento validar.
	 */
	public void tipoDocumentoValidar()
	{
		try
		{
			validarExistenciaDocumento();
			validarActoEjecutoria();
			findTipoOficina();
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
			PrimeFaces.current().ajax().update(is_idFormConfrontacion + ":idGrowl");
		}
	}

	/**
	 * Tipo oficina.
	 */
	public void tipoOficina()
	{
		findTipoEntidadApertura();
		findPaises();
		findDepartamentos();
		findMunicipioApertura();
		getOficinaOrigen();
	}

	/** {@inheritdoc} */
	public void validarActoEjecutoria()
	{
		getApertura()
			    .setEsActoConEjecutoria(
			    validarActoEjecutoria(is_idForm, getApertura().getDocumento().getIdTipoDocumento())
			);
	}

	/**
	 * Validar existencia documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarExistenciaDocumento()
	    throws B2BException
	{
		try
		{
			ValidacionDocumento lvd_retorno;
			Documento           ld_documento;
			Date                ld_fechaDocumento;
			Date                ld_FechaEjecutoria;
			Date                ld_fechaActual;
			Date                ld_fechadosmeses;
			Apertura            la_apertura;
			Calendar            lc_calendar;
			int                 li_meses;

			la_apertura      = getApertura();
			ld_documento     = la_apertura.getDocumento();
			ld_documento.setIdTipoOficina(la_apertura.getIdTipoOficina());
			ld_documento.setIdOficinaOrigen(la_apertura.getIdOficinaOrigen());
			lc_calendar          = Calendar.getInstance();
			ld_fechaActual       = new Date();
			ld_fechadosmeses     = new Date();

			li_meses     = -3;

			lvd_retorno     = irr_registroRemote.validarExistenciaDocumento(ld_documento);

			ld_fechaDocumento      = ld_documento.getFechaDocumento();
			ld_FechaEjecutoria     = ld_documento.getFechaEjecutoria();

			if((ld_fechaDocumento != null) && ld_fechaDocumento.after(ld_fechaActual))
			{
				Object[] aoa_messageArgs = new String[1];
				aoa_messageArgs[0] = " del documento ";
				addMessage("W", MessagesKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
			}

			if((ld_FechaEjecutoria != null) && ld_FechaEjecutoria.after(ld_fechaActual))
			{
				Object[] aoa_messageArgs = new String[1];
				aoa_messageArgs[0] = " de ejecutoria ";
				addMessage("W", MessagesKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
			}

			if(
			    (ld_fechaDocumento != null) && (ld_FechaEjecutoria != null)
				    && ld_fechaDocumento.after(ld_FechaEjecutoria)
			)
			{
				Object[] aoa_messageArgs = new String[1];
				aoa_messageArgs[0] = " del documento ";
				addMessage("W", MessagesKeys.FECHA_SUPERIOR_EJECUTORIA, aoa_messageArgs);
			}

			lc_calendar.setTime(ld_fechaActual);
			lc_calendar.add(Calendar.MONTH, li_meses);
			ld_fechaActual = lc_calendar.getTime();

			if((ld_fechaDocumento != null) && ld_fechaDocumento.before(ld_fechaActual))
				addMessage(MessagesKeys.DOCUMENTO_HA_SUPERADO_TRES_MESES);
			else
			{
				li_meses = -2;
				lc_calendar.setTime(ld_fechadosmeses);
				lc_calendar.add(Calendar.MONTH, li_meses);
				ld_fechadosmeses = lc_calendar.getTime();

				if((ld_fechaDocumento != null) && ld_fechaDocumento.before(ld_fechadosmeses))
					addMessage(MessagesKeys.DOCUMENTO_HA_SUPERADO_DOS_MESES);
			}

			if((ld_FechaEjecutoria != null) && ld_FechaEjecutoria.before(ld_fechaActual))
				addMessage(MessagesKeys.DOCUMENTO_HA_SUPERADO_TRES_MESES);
			else
			{
				li_meses = -2;
				lc_calendar.setTime(ld_fechadosmeses);
				lc_calendar.add(Calendar.MONTH, li_meses);
				ld_fechadosmeses = lc_calendar.getTime();

				if((ld_FechaEjecutoria != null) && ld_FechaEjecutoria.before(ld_fechadosmeses))
					addMessage(MessagesKeys.DOCUMENTO_HA_SUPERADO_DOS_MESES);
			}

			if(
			    (lvd_retorno != null) && lvd_retorno.isValidacion()
				    && lvd_retorno.getNir().equals(ld_documento.getNir())
			)
			{
				Object[] aoa_messageArgs = new String[1];

				aoa_messageArgs[0] = lvd_retorno.getNir();

				throw new B2BException(ErrorKeys.ERROR_DATOS_DOCUMENTO_NIR, aoa_messageArgs);
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
			PrimeFaces.current().ajax().update(is_idForm + ":idGrowl");
		}
	}

	/**
	 * Método para la validación de las fechas del documento y de ejecutoría en el proceso datePicker.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarFecha()
	    throws B2BException
	{
		Documento ld_documento;
		Date      ld_fechaDocumento;
		Date      ld_FechaEjecutoria;
		boolean   lb_esFecha;
		ld_documento           = getApertura().getDocumento();
		ld_fechaDocumento      = ld_documento.getFechaDocumento();
		ld_FechaEjecutoria     = ld_documento.getFechaEjecutoria();

		lb_esFecha = false;

		if(!lb_esFecha)
			if(ld_fechaDocumento.after(new Date()))
			{
				Object[] aoa_messageArgs = new String[1];
				aoa_messageArgs[0] = " del documento ";
				addMessage(ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
			}

		if(ld_FechaEjecutoria != null)
		{
			lb_esFecha = true;

			if(ld_FechaEjecutoria.after(new Date()))
			{
				Object[] aoa_messageArgs = new String[1];
				aoa_messageArgs[0] = " de ejecutoria ";
				addMessage(ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
			}

			if(ld_fechaDocumento.after(ld_FechaEjecutoria))
			{
				Object[] aoa_messageArgs = new String[1];
				aoa_messageArgs[0] = " del documento ";
				addMessage(ErrorKeys.FECHA_SUPERIOR_EJECUTORIA, aoa_messageArgs);
			}
		}
	}

	/**
	 * Método para validar el tramite a realizar en la matricula.
	 *
	 * @param ll_turnoHistoria objeto long contenedor del idturnohistoria
	 * @param ac_calificacion objeto Calificacion con informacion a validar
	 * @param aa_apertura objeto Apertura con informacion a validar
	 * @param as_idVinculado String para saber si es un vinculado
	 * @param lfc_context Instancia del primefaces
	 * @return objeto confrontación correctiva
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private ConfrontacionCorrectiva validarCorrectivo(
	    Long ll_turnoHistoria, Calificacion ac_calificacion, Apertura aa_apertura, String as_idVinculado,
	    FacesContext lfc_context
	)
	    throws B2BException
	{
		ConfrontacionCorrectiva lcc_cc;

		try
		{
			Calificacion                       lc_data;
			Apertura                           la_apertura;
			Collection<PredioRegistro>         lcpr_datosBandejaReabrirMatricula;
			Collection<SolicitudMatricula>     lcsm_datosBandejaPrediosInsertar;
			Collection<SolicitudMatricula>     lcsm_datosBandejaPrediosEliminar;
			Collection<SolicitudMatriculaActo> lcsma_datosBandejaActosInsertar;
			Collection<SolicitudMatriculaActo> lcsma_datosBandejaActosEliminar;

			lc_data                               = ac_calificacion;
			la_apertura                           = aa_apertura;
			lcpr_datosBandejaReabrirMatricula     = new ArrayList<PredioRegistro>();
			lcsm_datosBandejaPrediosInsertar      = new ArrayList<SolicitudMatricula>();
			lcsm_datosBandejaPrediosEliminar      = new ArrayList<SolicitudMatricula>();
			lcsma_datosBandejaActosInsertar       = new ArrayList<SolicitudMatriculaActo>();
			lcsma_datosBandejaActosEliminar       = new ArrayList<SolicitudMatriculaActo>();

			{
				TurnoHistoria       lth_turno;
				Map<String, Object> lhm_tabs;
				long                ll_idEtapa;
				long                ll_responsableActuacionesAdmin;
				long                ll_sustanciadorActuacionesAdmin;
				long                ll_segundaInstancia;

				lhm_tabs                            = new HashMap<String, Object>();
				ll_idEtapa                          = getIdEtapa();
				ll_responsableActuacionesAdmin      = EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS;
				ll_sustanciadorActuacionesAdmin     = EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS;
				ll_segundaInstancia                 = EtapaCommon.ID_ETAPA_460;

				lth_turno = irr_calificacionRemote.findTurnoHistoria(
					    ll_turnoHistoria,
					    (ll_idEtapa == ll_responsableActuacionesAdmin) ? Long.valueOf(ll_responsableActuacionesAdmin)
					                                                   : ((ll_idEtapa == ll_sustanciadorActuacionesAdmin)
					    ? Long.valueOf(ll_sustanciadorActuacionesAdmin)
					    : ((ll_idEtapa == ll_segundaInstancia) ? Long.valueOf(ll_segundaInstancia)
					                                           : (isEsEtapaCorrecciones130()
					    ? Long.valueOf(EtapaCommon.ID_ETAPA_CORRECCIONES)
					    : (isEsCertificadosEspeciales()
					    ? Long.valueOf(EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
					    : Long.valueOf(EtapaCommon.ID_ETAPA_CONFRONTACION_CORRECTIVA)))))
					);

				{
					String ls_justificacion;

					ls_justificacion = getJustificacion();

					validateStyles(":confrontacion5:idJustificacion", lfc_context, ls_justificacion, true);

					if(!StringUtils.isValidString(ls_justificacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
					else
					{
						String ls_mensajePredioInconsistente;
						ls_mensajePredioInconsistente = getMensajePredioInconsistente();

						if(
						    StringUtils.isValidString(ls_mensajePredioInconsistente)
							    && !StringUtils.getStringNotNull(getJustificacion())
							                       .contains(ls_mensajePredioInconsistente)
						)
						{
							setJustificacion(
							    ls_mensajePredioInconsistente + IdentificadoresCommon.SALTO_LINEA + ls_justificacion
							);
							lc_data.setEsPredioInconsistente(true);
						}

						lc_data.setJustificacion(getJustificacion());
					}
				}

				if(isDatosBasicos())
				{
					if(la_apertura.isEsActoConEjecutoria())
					{
						if(la_apertura.getDocumento().getFechaEjecutoria() == null)
						{
							{
								boolean lb_focus;
								Date    ls_fechaValidar;

								lb_focus            = true;
								ls_fechaValidar     = null;
								lb_focus            = validateStyles(
									    ":confrontacion5:idCorregirDatBasicos:idCalFechaEjecutoria" + as_idVinculado,
									    lfc_context, ls_fechaValidar, lb_focus
									);
							}

							Object[] aoa_messageArgs = new String[1];
							aoa_messageArgs[0] = " ejecutoria ";
							throw new B2BException(ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO, aoa_messageArgs);
						}

						if(
						    la_apertura.getDocumento().getFechaDocumento()
							               .after(la_apertura.getDocumento().getFechaEjecutoria())
						)
						{
							{
								boolean lb_focus;
								Date    ls_fechaValidar;

								lb_focus            = true;
								ls_fechaValidar     = null;
								lb_focus            = validateStyles(
									    ":fRegistro:idCalFechaEjecutoria" + as_idVinculado, lfc_context, ls_fechaValidar,
									    lb_focus
									);
							}

							Object[] aoa_messageArgs = new String[1];
							aoa_messageArgs[0] = " de documento ";
							throw new B2BException(ErrorKeys.FECHA_SUPERIOR_EJECUTORIA, aoa_messageArgs);
						}

						if(la_apertura.getDocumento().getFechaEjecutoria().after(new Date()))
						{
							Object[] aoa_messageArgs = new String[1];
							aoa_messageArgs[0] = " de ejecutoria ";
							throw new B2BException(ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
						}
					}

					if((lc_data != null) && StringUtils.isValidString(getMotivoTramite()))
						lc_data.setIdTurnoHistoria(ll_turnoHistoria);

					lhm_tabs.put(IdentificadoresCommon.CORREGIR_DATOS_BASICOS, new Boolean(true));
				}

				boolean lb_correccionesOCertificadosEsp;
				boolean lb_insertarUnaMatriculaCertificados;

				lb_correccionesOCertificadosEsp         = isEsEtapaCorrecciones130() || isEsCertificadosEspeciales();
				lb_insertarUnaMatriculaCertificados     = false;

				if(isInsertaMatricula())
				{
					Collection<PredioActoIU> lcpaui_bandeja;

					lcpaui_bandeja = (!lb_correccionesOCertificadosEsp) ? getSelectedMatriculasInsertar()
						                                                : getActosAsociadosGeneral();

					if(CollectionUtils.isValidCollection(lcpaui_bandeja))
					{
						if(isEsCertificadosEspeciales())
						{
							List<PredioActoIU> llsma_bandeja;
							llsma_bandeja = getSelectedMatriculas();

							if(CollectionUtils.isValidCollection(llsma_bandeja))
							{
								if(getMatriculas().size() != llsma_bandeja.size())
									throw new B2BException(ErrorKeys.ERROR_DEBE_DESASOCIAR_MATRICULA);
							}
							else if(getMatriculas().size() >= 1)
								throw new B2BException(ErrorKeys.ERROR_DEBE_DESASOCIAR_MATRICULA);

							if(lcpaui_bandeja.size() >= 2)
								throw new B2BException(ErrorKeys.ERROR_SOLO_PUEDE_AGREGAR_UNA_MATRICULA);

							lb_insertarUnaMatriculaCertificados = true;
						}

						lhm_tabs.put(IdentificadoresCommon.INSERTAR_MATRICULA, new Boolean(true));

						String ls_estado;

						ls_estado = EstadoCommon.A;

						if(lth_turno != null)
						{
							String ls_solicitud;

							ls_solicitud = lth_turno.getIdSolicitud();

							if(!StringUtils.isValidString(ls_solicitud))
							{
								Object[] loa_messageArgs = new String[1];
								loa_messageArgs[0] = lth_turno.getIdTurno();
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD_TURNO, loa_messageArgs);
							}

							if(CollectionUtils.isValidCollection(lcpaui_bandeja))
							{
								for(PredioActoIU lpaui_actual : lcpaui_bandeja)
								{
									if(lpaui_actual != null)
									{
										SolicitudMatricula lsm_matricula;
										String             ls_matricula;
										String             ls_matriculaTemp;

										lsm_matricula        = new SolicitudMatricula();
										ls_matriculaTemp     = lpaui_actual.getMatricula();

										if(
										    lb_correccionesOCertificadosEsp
											    && !ls_matriculaTemp.contains(IdentificadoresCommon.SIMBOLO_GUION)
										)
											ls_matricula = getCirculo() + IdentificadoresCommon.SIMBOLO_GUION
												+ ls_matriculaTemp;
										else
											ls_matricula = ls_matriculaTemp;

										if(StringUtils.isValidString(ls_matricula))
										{
											String ls_circuloRegistral;
											long   ll_matricula;

											if(!ls_matricula.contains(IdentificadoresCommon.SIMBOLO_GUION))
												throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA);

											int li_inicio = 0;

											li_inicio     = ls_matricula.lastIndexOf(
												    IdentificadoresCommon.SIMBOLO_GUION
												);

											ll_matricula     = NumericUtils.getLong(
												    ls_matricula.substring(li_inicio + 1, ls_matricula.length())
												);

											ls_circuloRegistral = ls_matricula.substring(0, li_inicio);

											{
												boolean lb_certificadoAsociado;

												lb_certificadoAsociado = false;

												CirculoRegistral lcr_circulo;

												lcr_circulo = new CirculoRegistral();

												lcr_circulo.setIdCirculo(ls_circuloRegistral);

												lcr_circulo = irr_registroRemote.findCirculoRegistralById(
													    lcr_circulo, getUserId()
													);

												if(lcr_circulo != null)
												{
													PredioRegistro lpr_predio;

													lpr_predio = new PredioRegistro();

													lpr_predio.setIdCirculo(lcr_circulo.getIdCirculo());
													lpr_predio.setIdMatricula(NumericUtils.getLong(ll_matricula));

													lpr_predio = irr_registroRemote.findPredioRegistroById(
														    lpr_predio, getUserId()
														);

													if(lpr_predio != null)
													{
														lsm_matricula.setIdMatricula(ll_matricula);
														lsm_matricula.setIdCirculo(ls_circuloRegistral);
														lsm_matricula.setIdSolicitud(ls_solicitud);

														{
															lb_certificadoAsociado = lpaui_actual.isCertificadoAsociado();

															lsm_matricula.setExpedirCertificado(
															    lb_certificadoAsociado ? EstadoCommon.S : EstadoCommon.N
															);
														}

														lsm_matricula.setCantidadCertificados(
														    NumericUtils.getBigDecimal(0D)
														);
														lsm_matricula.setEstado(ls_estado);
														lsm_matricula.setIdUsuarioCreacion(getUserId());
														lsm_matricula.setIdUsuarioCreacion(getUserId());
														lsm_matricula.setIdUsuarioModificacion(getUserId());
													}
													else
													{
														Object[] aoa_messageArgs = new String[1];

														aoa_messageArgs[0] = ls_circuloRegistral
															+ IdentificadoresCommon.SIMBOLO_GUION + ll_matricula;

														throw new B2BException(
														    ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA,
														    aoa_messageArgs
														);
													}
												}
												else
												{
													Object[] aoa_messageArgs = new String[1];

													aoa_messageArgs[0] = ls_circuloRegistral;

													throw new B2BException(
													    ErrorKeys.CIRCULO_REGISTRAL_NO_ENCONTRADO, aoa_messageArgs
													);
												}
											}

											if(!lb_correccionesOCertificadosEsp)
											{
												List<ColumnModel> lcm_lcm;

												lcm_lcm = getColumns(isIndVinculado());

												if(CollectionUtils.isValidCollection(lcm_lcm))
												{
													Map<String, Boolean> lmsb_actos;

													lmsb_actos = lpaui_actual.getActos();

													for(ColumnModel lcm_tmp : lcm_lcm)
													{
														if(lcm_tmp != null)
														{
															if(ls_solicitud.equalsIgnoreCase(lcm_tmp.getIdSolicitud()))
															{
																boolean lb_actoAsociado;
																String  ls_idActo;

																lb_actoAsociado     = false;
																ls_idActo           = lcm_tmp.getIdActo();

																if(StringUtils.isValidString(ls_idActo))
																	lb_actoAsociado = BooleanUtils.getBooleanValue(
																		    lmsb_actos.get(ls_idActo)
																		);

																if(lb_actoAsociado)
																{
																	SolicitudMatriculaActo lsma_matriculaActo;

																	lsma_matriculaActo = new SolicitudMatriculaActo();

																	lsma_matriculaActo.setIdSolicitud(ls_solicitud);
																	lsma_matriculaActo.setIdCirculo(
																	    ls_circuloRegistral
																	);
																	lsma_matriculaActo.setIdMatricula(ll_matricula);
																	lsma_matriculaActo.setIdActo(lcm_tmp.getIdActo());
																	lsma_matriculaActo.setIdTurno(
																	    lth_turno.getIdTurno()
																	);
																	lsma_matriculaActo.setEstado(ls_estado);
																	lsma_matriculaActo.setUsuario(getUserId());
																	lsma_matriculaActo.setIdUsuarioCreacion(
																	    getUserId()
																	);
																	lsma_matriculaActo.setIdUsuarioModificacion(
																	    getUserId()
																	);

																	lcsma_datosBandejaActosInsertar.add(
																	    lsma_matriculaActo
																	);
																}
															}
														}
													}
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.MATRICULAS_FORMATO);

										if((lsm_matricula != null))
											lcsm_datosBandejaPrediosInsertar.add(lsm_matricula);
									}
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SIN_TURNO_INSERTAR_VALIDO);
					}
				}

				if(isEliminaMatricula())
				{
					List<PredioActoIU> llsma_bandeja;

					llsma_bandeja = (!lb_correccionesOCertificadosEsp) ? getSelectedMatriculasEliminar()
						                                               : getSelectedMatriculas();

					if(CollectionUtils.isValidCollection(llsma_bandeja))
					{
						if(lb_correccionesOCertificadosEsp)
						{
							if(getMatriculas().size() == llsma_bandeja.size())
							{
								if(isEsCertificadosEspeciales())
								{
									if(isInsertaMatricula())
									{
										if(!lb_insertarUnaMatriculaCertificados)
											throw new B2BException(
											    ErrorKeys.ERROR_NO_ELIMINAR_TODAS_LAS_MATRICULAS_DEBE_REMITIRSE_A_INSERTAR_MATRICULA
											);
									}
									else
										throw new B2BException(
										    ErrorKeys.ERROR_NO_ELIMINAR_TODAS_LAS_MATRICULAS_DEBE_REMITIRSE_A_INSERTAR_MATRICULA
										);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_NO_ELIMINAR_TODAS_LAS_MATRICULAS);
							}
						}

						lhm_tabs.put(IdentificadoresCommon.ELIMINAR_MATRICULA, new Boolean(true));

						String ls_estado;

						ls_estado = EstadoCommon.INACTIVO;

						if(CollectionUtils.isValidCollection(llsma_bandeja))
						{
							for(PredioActoIU lpaui_actual : llsma_bandeja)
							{
								if(lpaui_actual != null)
								{
									SolicitudMatricula lsm_matricula;
									String             ls_matricula;

									lsm_matricula     = new SolicitudMatricula();
									ls_matricula      = lpaui_actual.getMatricula();

									if(StringUtils.isValidString(ls_matricula))
									{
										String ls_circuloRegistral;
										long   ll_matricula;

										if(!ls_matricula.contains(IdentificadoresCommon.SIMBOLO_GUION))
										{
											Object[] aoa_messageArgs = new String[1];
											aoa_messageArgs[0] = ls_matricula;
											throw new B2BException(
											    ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs
											);
										}

										int li_inicio = 0;

										li_inicio     = ls_matricula.lastIndexOf(IdentificadoresCommon.SIMBOLO_GUION);

										ll_matricula     = NumericUtils.getLong(
											    ls_matricula.substring(li_inicio + 1, ls_matricula.length())
											);

										ls_circuloRegistral = ls_matricula.substring(0, li_inicio);

										{
											boolean lb_certificadoAsociado;

											lb_certificadoAsociado = false;

											lsm_matricula.setIdMatricula(ll_matricula);
											lsm_matricula.setIdCirculo(ls_circuloRegistral);
											lsm_matricula.setIdSolicitud(lth_turno.getIdSolicitud());

											{
												lb_certificadoAsociado = lpaui_actual.isCertificadoAsociado();

												lsm_matricula.setExpedirCertificado(
												    lb_certificadoAsociado ? EstadoCommon.S : EstadoCommon.N
												);
											}

											lsm_matricula.setCantidadCertificados(NumericUtils.getBigDecimal(0D));
											lsm_matricula.setEstado(ls_estado);
											lsm_matricula.setIdUsuarioCreacion(getUserId());
											lsm_matricula.setIdUsuarioModificacion(getUserId());
										}

										if(!lb_correccionesOCertificadosEsp)
										{
											List<ColumnModel> lcm_lcm;

											lcm_lcm = getColumns();

											if(CollectionUtils.isValidCollection(lcm_lcm))
											{
												Map<String, Boolean> lmsb_actos;

												lmsb_actos = lpaui_actual.getActos();

												for(ColumnModel lcm_tmp : lcm_lcm)
												{
													if(lcm_tmp != null)
													{
														boolean lb_actoAsociado;
														String  ls_idActo;

														lb_actoAsociado     = false;
														ls_idActo           = lcm_tmp.getIdActo();

														if(StringUtils.isValidString(ls_idActo))
															lb_actoAsociado = BooleanUtils.getBooleanValue(
																    lmsb_actos.get(ls_idActo)
																);

														if(lb_actoAsociado)
														{
															SolicitudMatriculaActo lsma_matriculaActo;

															lsma_matriculaActo = new SolicitudMatriculaActo();

															lsma_matriculaActo.setIdSolicitud(
															    lth_turno.getIdSolicitud()
															);
															lsma_matriculaActo.setIdCirculo(ls_circuloRegistral);
															lsma_matriculaActo.setIdMatricula(ll_matricula);
															lsma_matriculaActo.setIdActo(lcm_tmp.getIdActo());
															lsma_matriculaActo.setIdTurno(lth_turno.getIdTurno());
															lsma_matriculaActo.setEstado(ls_estado);
															lsma_matriculaActo.setUsuario(getUserId());
															lsma_matriculaActo.setIdUsuarioCreacion(getUserId());
															lsma_matriculaActo.setIdUsuarioModificacion(getUserId());

															lcsma_datosBandejaActosEliminar.add(lsma_matriculaActo);
														}
													}
												}
											}
										}
									}
									else
										throw new B2BException(ErrorKeys.MATRICULAS_FORMATO);

									if((lsm_matricula != null))
									{
										if(lb_correccionesOCertificadosEsp)
											lcsm_datosBandejaPrediosEliminar.add(lsm_matricula);
										else if(CollectionUtils.isValidCollection(lcsma_datosBandejaActosEliminar))
											lcsm_datosBandejaPrediosEliminar.add(lsm_matricula);
									}
								}
							}

							if(lth_turno == null)
								throw new B2BException(ErrorKeys.ERROR_SIN_TURNO_ELIMINAR_VALIDO);
						}
					}
				}

				if(isVerificaFolioCerrado())
				{
					Collection<PredioActoIU> llsma_bandeja;

					llsma_bandeja = getReabrirMatriculas();

					if(CollectionUtils.isValidCollection(llsma_bandeja))
					{
						lhm_tabs.put(IdentificadoresCommon.VERIFICAR_FOLIO, new Boolean(true));

						String ls_estado;

						ls_estado = EstadoCommon.S;

						for(PredioActoIU lpaui_actual : llsma_bandeja)
						{
							if(lpaui_actual != null)
							{
								PredioRegistro lsm_matricula;
								String         ls_matricula;

								lsm_matricula     = new PredioRegistro();
								ls_matricula      = lpaui_actual.getMatricula();

								if(StringUtils.isValidString(ls_matricula))
								{
									String ls_circuloRegistral;
									long   ll_matricula;

									if(!ls_matricula.contains(IdentificadoresCommon.SIMBOLO_GUION))
										throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA);

									int li_inicio = 0;

									li_inicio     = ls_matricula.lastIndexOf(IdentificadoresCommon.SIMBOLO_GUION);

									ll_matricula     = NumericUtils.getLong(
										    ls_matricula.substring(li_inicio + 1, ls_matricula.length())
										);

									ls_circuloRegistral = ls_matricula.substring(0, li_inicio);

									lsm_matricula.setIdMatricula(ll_matricula);
									lsm_matricula.setIdCirculo(ls_circuloRegistral);
									lsm_matricula.setIdEstadoPredio(ls_estado);
									lsm_matricula.setIdUsuario(getUserId());
								}
								else
									throw new B2BException(ErrorKeys.MATRICULAS_FORMATO);

								if((lsm_matricula != null) && lpaui_actual.isCertificadoAsociado())
									lcpr_datosBandejaReabrirMatricula.add(lsm_matricula);
							}
						}

						if(lth_turno == null)
							throw new B2BException(ErrorKeys.ERROR_SIN_TURNO_REABRIR_VALIDO);
					}
				}

				lc_data.setTabs(lhm_tabs);
			}

			lcc_cc = new ConfrontacionCorrectiva(
				    lcsm_datosBandejaPrediosInsertar, lcsma_datosBandejaActosInsertar, lcsm_datosBandejaPrediosEliminar,
				    lcsma_datosBandejaActosEliminar, lcpr_datosBandejaReabrirMatricula, lc_data, la_apertura,
				    getJustificacion(), ll_turnoHistoria
				);
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return lcc_cc;
	}
}
