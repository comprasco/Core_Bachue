package com.bachue.snr.prosnr01.web.bean.mayorValor;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MayorValorCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.OperadorCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.aprobacion.AprobacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.liquidacion.LiquidacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.registro.Acto;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroMayorValor;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroPagoExceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalMayorValor;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanTurnos;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;
import com.bachue.snr.prosnr01.web.util.ColumnModel;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;

import org.primefaces.PrimeFaces;

import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import java.math.BigDecimal;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades del bean de BeanMayorValor.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanMayorValor")
public class BeanMayorValor extends BeanRegistro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7569653732807659504L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanMayorValor.class);

	/** Constante cs_ID_GROWL. */
	private static final String cs_ID_GROWL = "fMayorValorId:idGrowl";

	/** Constante cs_ID_FORM. */
	private static final String cs_ID_FORM = "fMayorValorId";

	/** Propiedad iasr Antiguo Sistema Remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr aprobacion remote. */
	@EJB
	private AprobacionRemote irr_aprobacionRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ica actos modificados. */
	private Collection<Acto> ica_actosModificados;

	/** Propiedad iccmv causales mayor valor. */
	private Collection<CausalMayorValor> iccmv_causalesMayorValor;

	/** Propiedad icdl detalle liquidacion. */
	private Collection<Liquidacion> icdl_detalleLiquidacion;

	/** Propiedad icl resumen cobro mayor valor. */
	private Collection<Liquidacion> icl_resumenCobroMayorValor;

	/** Propiedad icsm solicitud matricula salvar. */
	private Collection<SolicitudMatricula> icsm_solicitudMatriculaSalvar;

	/** Propiedad icsm turnos certificados. */
	private Collection<SolicitudMatricula> icsm_turnosCertificados;

	/** Propiedad icsma solicitud matricula acto. */
	private Collection<SolicitudMatriculaActo> icsma_solicitudMatriculaActo;

	/** Propiedad ier entrega remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad ilr liquidacion remote. */
	@EJB
	private LiquidacionRemote ilr_liquidacionRemote;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip persona seleccionada. */
	private Persona ip_personaSeleccionada;

	/** Propiedad irpe registro pago exceso. */
	private RegistroPagoExceso irpe_registroPagoExceso;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc zip documentos generados descarga. */
	private StreamedContent isc_zipDocumentosGeneradosDescarga;

	/** Propiedad is concepto cobro mayor valor. */
	private String is_conceptoCobroMayorValor;

	/** Propiedad is id Proceso. */
	private String is_idProceso;

	/** Propiedad is id registro mayor valor. */
	private String is_idRegistroMayorValor;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno correcciones. */
	private String is_idTurnoCorrecciones;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is total mayor valor. */
	private String is_totalMayorValor;

	/** Propiedad is total saldo favor. */
	private String is_totalSaldoFavor;

	/** Propiedad is turno Registro Correcciones. */
	private String is_turnoRegistroCorrecciones;

	/** Propiedad iba zip documentos generados. */
	private byte[] iba_zipDocumentosGenerados;

	/** Propiedad ib cumple Validaciones Correcciones. */
	private boolean ib_cumpleValidacionesCorrecciones;

	/** Propiedad ib documentos enviados. */
	private boolean ib_documentosEnviados;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** Propiedad ib inserto acto. */
	private boolean ib_insertoActo;

	/** Propiedad ib migrado. */
	private boolean ib_migrado;

	/** Propiedad ib modificar. */
	private boolean ib_modificar;

	/** Propiedad ib modifico acto. */
	private boolean ib_modificoActo;

	/** Propiedad ib proceso correcciones. */
	private boolean ib_mostrarBotonGenerarDocumentos;

	/** Propiedad ib mostrar continuar. */
	private boolean ib_mostrarContinuar;

	/** Propiedad ib mostrar documentos. */
	private boolean ib_mostrarDocumentos;

	/** Propiedad ib mostrar enviar al aprobador. */
	private boolean ib_mostrarEnviarAlAprobador;

	/** Propiedad ib mostrar generar documentos. */
	private boolean ib_mostrarGenerarDocumentos;

	/** Propiedad ib mostrar liquidar. */
	private boolean ib_mostrarLiquidar;

	/** Propiedad ib mostrar preliquidar. */
	private boolean ib_mostrarPreliquidar;

	/** Propiedad ib mostrar visualizar documentos. */
	private boolean ib_mostrarVisualizarDocumentos;

	/** Propiedad ib ocultar regresar detalle. */
	private boolean ib_ocultarRegresarDetalle;

	/** Propiedad ib pago exceso. */
	private boolean ib_pagoExceso;

	/** Propiedad ib proceso correcciones. */
	private boolean ib_procesoCorrecciones;

	/** Propiedad ib turno Migrado Sin Homologar. */
	private boolean ib_turnoMigradoSinHomologar;

	/**
	 * Modifica el valor de actos modificados.
	 *
	 * @param aca_ca asigna el valor a la propiedad actos modificados
	 */
	public void setActosModificados(Collection<Acto> aca_ca)
	{
		ica_actosModificados = aca_ca;
	}

	/**
	 * Retorna el valor de actos modificados.
	 *
	 * @return el valor de actos modificados
	 */
	public Collection<Acto> getActosModificados()
	{
		return ica_actosModificados;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de causales mayor valor.
	 *
	 * @param accmv_ccmv asigna el valor a la propiedad causales mayor valor
	 */
	public void setCausalesMayorValor(Collection<CausalMayorValor> accmv_ccmv)
	{
		iccmv_causalesMayorValor = accmv_ccmv;
	}

	/**
	 * Retorna el valor de causales mayor valor.
	 *
	 * @return el valor de causales mayor valor
	 */
	public Collection<CausalMayorValor> getCausalesMayorValor()
	{
		return iccmv_causalesMayorValor;
	}

	/**
	 * Modifica el valor de concepto cobro mayor valor.
	 *
	 * @param as_s asigna el valor a la propiedad concepto cobro mayor valor
	 */
	public void setConceptoCobroMayorValor(String as_s)
	{
		is_conceptoCobroMayorValor = as_s;
	}

	/**
	 * Retorna el valor de concepto cobro mayor valor.
	 *
	 * @return el valor de concepto cobro mayor valor
	 */
	public String getConceptoCobroMayorValor()
	{
		return is_conceptoCobroMayorValor;
	}

	/**
	 * Método que modifíca el valor de la propiedad.
	 *
	 * @param ab_b modifíca el valor de la propiedad.
	 */
	public void setCumpleValidacionesCorrecciones(boolean ab_b)
	{
		ib_cumpleValidacionesCorrecciones = ab_b;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isCumpleValidacionesCorrecciones()
	{
		return ib_cumpleValidacionesCorrecciones;
	}

	/**
	 * Modifica el valor de detalle liquidacion.
	 *
	 * @param acl_cl asigna el valor a la propiedad detalle liquidacion
	 */
	public void setDetalleLiquidacion(Collection<Liquidacion> acl_cl)
	{
		icdl_detalleLiquidacion = acl_cl;
	}

	/**
	 * Retorna el valor de detalle liquidacion.
	 *
	 * @return el valor de detalle liquidacion
	 */
	public Collection<Liquidacion> getDetalleLiquidacion()
	{
		return icdl_detalleLiquidacion;
	}

	/**
	 * Método que modifíca el valor de la propiedad.
	 *
	 * @param ab_b modifíca el valor de la propiedad.
	 */
	public void setDocumentosEnviados(boolean ab_b)
	{
		ib_documentosEnviados = ab_b;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isDocumentosEnviados()
	{
		return ib_documentosEnviados;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 *
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de id registro mayor valor.
	 *
	 * @param as_s asigna el valor a la propiedad id registro mayor valor
	 */
	public void setIdRegistroMayorValor(String as_s)
	{
		is_idRegistroMayorValor = as_s;
	}

	/**
	 * Retorna el valor de id registro mayor valor.
	 *
	 * @return el valor de id registro mayor valor
	 */
	public String getIdRegistroMayorValor()
	{
		return is_idRegistroMayorValor;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdTurnoCorrecciones(String as_s)
	{
		is_idTurnoCorrecciones = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurnoCorrecciones()
	{
		return is_idTurnoCorrecciones;
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
	 * Modifica el valor de inserto acto.
	 *
	 * @param ab_b asigna el valor a la propiedad inserto acto
	 */
	public void setInsertoActo(boolean ab_b)
	{
		ib_insertoActo = ab_b;
	}

	/**
	 * Valida la propiedad inserto acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en inserto acto
	 */
	public boolean isInsertoActo()
	{
		return ib_insertoActo;
	}

	/**
	 * Retorna el valor de listar columnas.
	 *
	 * @return el valor de listar columnas
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<ColumnModel> getListarColumnas()
	    throws B2BException
	{
		Collection<TipoActo> lca_actosSolicitud;
		List<ColumnModel>    llcm_columns;
		String               ls_solicitud;

		llcm_columns     = new ArrayList<ColumnModel>();
		ls_solicitud     = getSolicitud().getIdSolicitud();

		if(StringUtils.isValidString(ls_solicitud))
		{
			com.bachue.snr.prosnr01.model.sdb.acc.Acto la_param;

			la_param = new com.bachue.snr.prosnr01.model.sdb.acc.Acto();

			la_param.setIdSolicitud(ls_solicitud);
			la_param.setIdCirculo(getIdCirculo());

			lca_actosSolicitud = findActosById(la_param, getUserId(), true);

			if(CollectionUtils.isValidCollection(lca_actosSolicitud))
			{
				Iterator<TipoActo> lia_actos;

				lia_actos = lca_actosSolicitud.iterator();

				while(lia_actos.hasNext())
				{
					TipoActo la_actos;

					la_actos = lia_actos.next();

					if(la_actos != null)
					{
						String ls_indMayorValor;
						ls_indMayorValor = la_actos.getIndMayorValor();

						if(StringUtils.isValidString(ls_indMayorValor))
						{
							if(ls_indMayorValor.equalsIgnoreCase(EstadoCommon.I))
							{
								setInsertoActo(true);

								llcm_columns.add(
								    new ColumnModel(
								        la_actos.getIdTipoActo(), new Boolean(false), la_actos.getNombre(),
								        conversionCientifica(
								            NumericUtils.getDoubleWrapper(la_actos.getIdTipoCalculo())
								        ), la_actos.getIdUsuarioCreacion(), la_actos.isValidarArea()
								    )
								);
							}
						}
					}
				}
			}
		}

		return llcm_columns;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ab_b Argumento que modifica el valor de la propiedad.
	 */
	public void setMigrado(boolean ab_b)
	{
		ib_migrado = ab_b;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public boolean isMigrado()
	{
		return ib_migrado;
	}

	/**
	 * Modifica el valor de modificar.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar
	 */
	public void setModificar(boolean ab_b)
	{
		ib_modificar = ab_b;
	}

	/**
	 * Valida la propiedad modificar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar
	 */
	public boolean isModificar()
	{
		return ib_modificar;
	}

	/**
	 * Modifica el valor de modifico acto.
	 *
	 * @param ab_b asigna el valor a la propiedad modifico acto
	 */
	public void setModificoActo(boolean ab_b)
	{
		ib_modificoActo = ab_b;
	}

	/**
	 * Valida la propiedad modifico acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modifico acto
	 */
	public boolean isModificoActo()
	{
		return ib_modificoActo;
	}

	/**
	 * Asigna el valor de la propiedad Mostrar botón generar documentos.
	 * @param ab_a con el valor  a asignar
	 */
	public void setMostrarBotonGenerarDocumentos(boolean ab_a)
	{
		ib_mostrarBotonGenerarDocumentos = ab_a;
	}

	/**
	 * Valida la propiedad Mostrar botón generar documentos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en Mostrar Botón generar Documentos
	 */
	public boolean isMostrarBotonGenerarDocumentos()
	{
		return ib_mostrarBotonGenerarDocumentos;
	}

	/**
	 * Modifica el valor de mostrar continuar.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar continuar
	 */
	public void setMostrarContinuar(boolean ab_b)
	{
		ib_mostrarContinuar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar continuar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar continuar
	 */
	public boolean isMostrarContinuar()
	{
		return ib_mostrarContinuar;
	}

	/**
	 * Modifica el valor de mostrar documentos.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar documentos
	 */
	public void setMostrarDocumentos(boolean ab_b)
	{
		ib_mostrarDocumentos = ab_b;
	}

	/**
	 * Valida la propiedad mostrar documentos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar documentos
	 */
	public boolean isMostrarDocumentos()
	{
		return ib_mostrarDocumentos;
	}

	/**
	 * Modifica el valor de mostrar enviar al aprobador.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar enviar al aprobador
	 */
	public void setMostrarEnviarAlAprobador(boolean ab_b)
	{
		ib_mostrarEnviarAlAprobador = ab_b;
	}

	/**
	 * Valida la propiedad mostrar enviar al aprobador.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar enviar al aprobador
	 */
	public boolean isMostrarEnviarAlAprobador()
	{
		return ib_mostrarEnviarAlAprobador;
	}

	/**
	 * Modifica el valor de mostrar generar documentos.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar generar documentos
	 */
	public void setMostrarGenerarDocumentos(boolean ab_b)
	{
		ib_mostrarGenerarDocumentos = ab_b;
	}

	/**
	 * Valida la propiedad mostrar generar documentos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar generar documentos
	 */
	public boolean isMostrarGenerarDocumentos()
	{
		return ib_mostrarGenerarDocumentos;
	}

	/**
	 * Modifica el valor de mostrar liquidar.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar liquidar
	 */
	public void setMostrarLiquidar(boolean ab_b)
	{
		ib_mostrarLiquidar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar liquidar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar liquidar
	 */
	public boolean isMostrarLiquidar()
	{
		return ib_mostrarLiquidar;
	}

	/**
	 * Modifica el valor de mostrar preliquidar.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar preliquidar
	 */
	public void setMostrarPreliquidar(boolean ab_b)
	{
		ib_mostrarPreliquidar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar preliquidar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar preliquidar
	 */
	public boolean isMostrarPreliquidar()
	{
		return ib_mostrarPreliquidar;
	}

	/**
	 * Modifica el valor de mostrar visualizar documentos.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar visualizar documentos
	 */
	public void setMostrarVisualizarDocumentos(boolean ab_b)
	{
		ib_mostrarVisualizarDocumentos = ab_b;
	}

	/**
	 * Valida la propiedad mostrar visualizar documentos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar visualizar documentos
	 */
	public boolean isMostrarVisualizarDocumentos()
	{
		return ib_mostrarVisualizarDocumentos;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de ocultar regresar detalle.
	 *
	 * @param ab_b asigna el valor a la propiedad ocultar regresar detalle
	 */
	public void setOcultarRegresarDetalle(boolean ab_b)
	{
		ib_ocultarRegresarDetalle = ab_b;
	}

	/**
	 * Valida la propiedad ocultar regresar detalle.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ocultar regresar detalle
	 */
	public boolean isOcultarRegresarDetalle()
	{
		return ib_ocultarRegresarDetalle;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad.
	 */
	public void setPagoExceso(boolean ab_b)
	{
		ib_pagoExceso = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isPagoExceso()
	{
		return ib_pagoExceso;
	}

	/**
	 * Modifica el valor de persona seleccionada.
	 *
	 * @param ap_p asigna el valor a la propiedad persona seleccionada
	 */
	public void setPersonaSeleccionada(Persona ap_p)
	{
		ip_personaSeleccionada = ap_p;
	}

	/**
	 * Retorna el valor de persona seleccionada.
	 *
	 * @return el valor de persona seleccionada
	 */
	public Persona getPersonaSeleccionada()
	{
		return ip_personaSeleccionada;
	}

	/**
	 * Modifica el valor de proceso correcciones.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso correcciones
	 */
	public void setProcesoCorrecciones(boolean ab_b)
	{
		ib_procesoCorrecciones = ab_b;
	}

	/**
	 * Valida la propiedad proceso correcciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso correcciones
	 */
	public boolean isProcesoCorrecciones()
	{
		return ib_procesoCorrecciones;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param arpe_rpe Argumento que modifica el valor de la propiedad.
	 */
	public void setRegistroPagoExceso(RegistroPagoExceso arpe_rpe)
	{
		irpe_registroPagoExceso = arpe_rpe;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public RegistroPagoExceso getRegistroPagoExceso()
	{
		if(irpe_registroPagoExceso == null)
			irpe_registroPagoExceso = new RegistroPagoExceso();

		return irpe_registroPagoExceso;
	}

	/**
	 * Modifica el valor de resumen cobro mayor valor.
	 *
	 * @param acl_cl asigna el valor a la propiedad resumen cobro mayor valor
	 */
	public void setResumenCobroMayorValor(Collection<Liquidacion> acl_cl)
	{
		icl_resumenCobroMayorValor = acl_cl;
	}

	/**
	 * Retorna el valor de resumen cobro mayor valor.
	 *
	 * @return el valor de resumen cobro mayor valor
	 */
	public Collection<Liquidacion> getResumenCobroMayorValor()
	{
		return icl_resumenCobroMayorValor;
	}

	/**
	 * Modifica el valor de solicitud matricula acto.
	 *
	 * @param acsma_csma asigna el valor a la propiedad solicitud matricula acto
	 */
	public void setSolicitudMatriculaActo(Collection<SolicitudMatriculaActo> acsma_csma)
	{
		icsma_solicitudMatriculaActo = acsma_csma;
	}

	/**
	 * Retorna el valor de solicitud matricula acto.
	 *
	 * @return el valor de solicitud matricula acto
	 */
	public Collection<SolicitudMatriculaActo> getSolicitudMatriculaActo()
	{
		return icsma_solicitudMatriculaActo;
	}

	/**
	 * Modifica el valor de solicitud matricula salvar.
	 *
	 * @param acsm_csm asigna el valor a la propiedad solicitud matricula salvar
	 */
	public void setSolicitudMatriculaSalvar(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_solicitudMatriculaSalvar = acsm_csm;
	}

	/**
	 * Retorna el valor de solicitud matricula salvar.
	 *
	 * @return el valor de solicitud matricula salvar
	 */
	public Collection<SolicitudMatricula> getSolicitudMatriculaSalvar()
	{
		return icsm_solicitudMatriculaSalvar;
	}

	/**
	 * Retorna el valor de tabs circulos.
	 *
	 * @return el valor de tabs circulos
	 */
	public Collection<Liquidacion> getTabsCirculos()
	{
		try
		{
			Solicitud ls_solicitud;

			ls_solicitud = getSolicitud();

			ls_solicitud.setMayorValor(true);

			icl_tabsCirculos = ilr_liquidacionRemote.findCondicionesLiquidacion(ls_solicitud);
		}
		catch(B2BException e)
		{
			clh_LOGGER.error("getDatosLiquidacion", e);
		}

		return icl_tabsCirculos;
	}

	/**
	 * Modifica el valor de total mayor valor.
	 *
	 * @param as_s asigna el valor a la propiedad total mayor valor
	 */
	public void setTotalMayorValor(String as_s)
	{
		is_totalMayorValor = as_s;
	}

	/**
	 * Retorna el valor de total mayor valor.
	 *
	 * @return el valor de total mayor valor
	 */
	public String getTotalMayorValor()
	{
		return is_totalMayorValor;
	}

	/**
	 * Modifica el valor de total saldo favor.
	 *
	 * @param as_s asigna el valor a la propiedad total saldo favor
	 */
	public void setTotalSaldoFavor(String as_s)
	{
		is_totalSaldoFavor = as_s;
	}

	/**
	 * Retorna el valor de total saldo favor.
	 *
	 * @return el valor de total saldo favor
	 */
	public String getTotalSaldoFavor()
	{
		return is_totalSaldoFavor;
	}

	/**
	 * Método que modifíca el valor de la propiedad.
	 *
	 * @param ab_b modifíca el valor de la propiedad.
	 */
	public void setTurnoMigradoSinHomologar(boolean ab_b)
	{
		ib_turnoMigradoSinHomologar = ab_b;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isTurnoMigradoSinHomologar()
	{
		return ib_turnoMigradoSinHomologar;
	}

	/**
	 * Método que modifíca el valor de la propiedad.
	 *
	 * @param as_s modifíca el valor de la propiedad.
	 */
	public void setTurnoRegistroCorrecciones(String as_s)
	{
		is_turnoRegistroCorrecciones = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTurnoRegistroCorrecciones()
	{
		return is_turnoRegistroCorrecciones;
	}

	/**
	 * Modifica el valor de turnos certificados.
	 *
	 * @param acsm_csm asigna el valor a la propiedad turnos certificados
	 */
	public void setTurnosCertificados(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_turnosCertificados = acsm_csm;
	}

	/**
	 * Retorna el valor de turnos certificados.
	 *
	 * @return el valor de turnos certificados
	 */
	public Collection<SolicitudMatricula> getTurnosCertificados()
	{
		return icsm_turnosCertificados;
	}

	/**
	 * Modifica el valor de zip documentos generados.
	 *
	 * @param aba_ba asigna el valor a la propiedad zip documentos generados
	 */
	public void setZipDocumentosGenerados(byte[] aba_ba)
	{
		iba_zipDocumentosGenerados = aba_ba;
	}

	/**
	 * Retorna el valor de zip documentos generados.
	 *
	 * @return el valor de zip documentos generados
	 */
	public byte[] getZipDocumentosGenerados()
	{
		return iba_zipDocumentosGenerados;
	}

	/**
	 * Modifica el valor de zip documentos generados descarga.
	 *
	 * @param asc_sc asigna el valor a la propiedad zip documentos generados descarga
	 */
	public void setZipDocumentosGeneradosDescarga(StreamedContent asc_sc)
	{
		isc_zipDocumentosGeneradosDescarga = asc_sc;
	}

	/**
	 * Retorna el valor de zip documentos generados descarga.
	 *
	 * @return el valor de zip documentos generados descarga
	 */
	public StreamedContent getZipDocumentosGeneradosDescarga()
	{
		return isc_zipDocumentosGeneradosDescarga;
	}

	/**
	 * Toma los datos ingresados en pantalla para un acto y los almacena en un
	 * registro en la base de datos en la tabla sdb_acc_acto.
	 *
	 * @param ab_actoVIS correspondiente al valor del tipo de objeto boolean
	 */
	public void agregarActosMayorValor(boolean ab_actoVIS)
	{
		try
		{
			agregarActos(ab_actoVIS, cs_ID_FORM);
			consultarActosPorIdTurno(getIdTurno());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarActosMayorValor", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de salvar los registros enviados en solicitud matricula acto.
	 *
	 * @param acpau_matriculas Argumento de tipo Collection<PredioActoIU> que contiene
	 * las matriculas a asociar con los actos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void asociarMatriculasActos(Collection<PredioActoIU> acpau_matriculas)
	    throws B2BException
	{
		Collection<SolicitudMatriculaActo> lcsma_solicitudMatriculaActo;
		Collection<SolicitudMatricula>     lcsm_solicitudMatricula;

		lcsma_solicitudMatriculaActo     = new ArrayList<SolicitudMatriculaActo>();
		lcsm_solicitudMatricula          = new ArrayList<SolicitudMatricula>();

		try
		{
			if(CollectionUtils.isValidCollection(acpau_matriculas))
			{
				Map<String, Boolean> lmsb_mapaActos;
				lmsb_mapaActos = new HashMap<String, Boolean>();

				Iterator<PredioActoIU> licpa_iterator;
				licpa_iterator = acpau_matriculas.iterator();

				if(licpa_iterator.hasNext())
				{
					PredioActoIU lpa_tmp;
					lpa_tmp = licpa_iterator.next();

					List<ColumnModel> lcm_lcm;

					lcm_lcm = getColumns(getIdCirculo());

					if(CollectionUtils.isValidCollection(lcm_lcm))
					{
						Map<String, Boolean> lmsb_actos;
						lmsb_actos = lpa_tmp.getActos();

						if(CollectionUtils.isValidCollection(lmsb_actos))
						{
							for(String ls_actoTMP : lmsb_actos.keySet())
							{
								if(StringUtils.isValidString(ls_actoTMP))
									lmsb_mapaActos.put(ls_actoTMP, Boolean.FALSE);
							}
						}
					}
				}

				for(PredioActoIU lpau_iterador : acpau_matriculas)
				{
					if(lpau_iterador != null)
					{
						List<ColumnModel> lcm_lcm;
						Long              ll_idMatricula;

						lcm_lcm            = getColumns(getIdCirculo());
						ll_idMatricula     = lpau_iterador.getIdMatricula();

						if(CollectionUtils.isValidCollection(lcm_lcm))
						{
							Map<String, Boolean> lmsb_actos;
							boolean              lb_ActoAsociado;

							lb_ActoAsociado     = false;

							lmsb_actos = lpau_iterador.getActos();

							for(ColumnModel lcm_tmp : lcm_lcm)
							{
								if(lcm_tmp != null)
								{
									boolean lb_actoAsociado;
									String  ls_idActo;

									lb_actoAsociado     = false;
									ls_idActo           = lcm_tmp.getIdActo();

									if(StringUtils.isValidString(ls_idActo))
										lb_actoAsociado = BooleanUtils.getBooleanValue(lmsb_actos.get(ls_idActo));

									if(lb_actoAsociado)
									{
										SolicitudMatriculaActo lsma_matriculaActo;

										lsma_matriculaActo = new SolicitudMatriculaActo();

										lsma_matriculaActo.setIdSolicitud(getIdSolicitud());
										lsma_matriculaActo.setIdCirculo(getIdCirculo());
										lsma_matriculaActo.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
										lsma_matriculaActo.setIdActo(lcm_tmp.getIdActo());
										lsma_matriculaActo.setIdTurno(getIdTurno());
										lsma_matriculaActo.setEstado(EstadoCommon.A);
										lsma_matriculaActo.setUsuario(getUserId());
										lsma_matriculaActo.setIdUsuarioCreacion(getUserId());
										lsma_matriculaActo.setIdUsuarioModificacion(getUserId());
										lsma_matriculaActo.setIpCreacion(getRemoteIpAddress());
										lsma_matriculaActo.setIpModificacion(getRemoteIpAddress());

										lcsma_solicitudMatriculaActo.add(lsma_matriculaActo);

										lb_ActoAsociado = true;

										lmsb_mapaActos.put(ls_idActo, Boolean.TRUE);
									}
								}
							}

							if(!lb_ActoAsociado)
							{
								Object[] aoa_messageArgs = new String[1];

								aoa_messageArgs[0] = getIdCirculo() + "-" + ll_idMatricula;

								throw new B2BException(
								    ErrorKeys.DEBE_DIGITAR_ACTOS_ASOCIADOS_MATRICULA, aoa_messageArgs
								);
							}
						}

						{
							SolicitudMatricula lsm_solicitudMatricula;

							lsm_solicitudMatricula = new SolicitudMatricula();

							lsm_solicitudMatricula.setIdSolicitud(getIdSolicitud());
							lsm_solicitudMatricula.setIdCirculo(getIdCirculo());
							lsm_solicitudMatricula.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
							lsm_solicitudMatricula.setExpedirCertificado(
							    lpau_iterador.isCertificadoAsociado() ? EstadoCommon.S : EstadoCommon.N
							);
							lsm_solicitudMatricula.setCantidadCertificados(
							    NumericUtils.getBigDecimal(NumericUtils.getLong(lpau_iterador.getCantidadAperturar()))
							);
							lsm_solicitudMatricula.setEstado(EstadoCommon.A);

							lcsm_solicitudMatricula.add(lsm_solicitudMatricula);
						}
					}
				}

				if(CollectionUtils.isValidCollection(lmsb_mapaActos))
				{
					for(String ls_actoTMP : lmsb_mapaActos.keySet())
					{
						Boolean lb_bool;
						lb_bool = lmsb_mapaActos.get(ls_actoTMP);

						if(lb_bool == Boolean.FALSE)
							throw new B2BException(ErrorKeys.ASOCIAR_AL_MENOS_UN_ACTO_A_UNA_MATRICULA);
					}
				}
			}

			if(lcsma_solicitudMatriculaActo.isEmpty())
				lcsma_solicitudMatriculaActo = null;

			if(lcsm_solicitudMatricula.isEmpty())
				lcsm_solicitudMatricula = null;

			setSolicitudMatriculaSalvar(lcsm_solicitudMatricula);
			setSolicitudMatriculaActo(lcsma_solicitudMatriculaActo);
		}
		catch(B2BException lb2be_e)
		{
			lcsma_solicitudMatriculaActo = null;
			clh_LOGGER.error("asociarMatriculasActos", lb2be_e);
			addMessage(lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de buscar las personas de una solicitud interviniente.
	 */
	public void buscarPersonasPorSolicitudInterviniente()
	{
		try
		{
			limpiarIntervinientesConsulta();
			limpiarMedioNoInter();
			limpiarMedioCoInter();

			setRenderedInterviniente(false);
			setMostrarDatosConsultaInter(false);
			setPersonaConsultadaInter(true);
			setMostrarDatosBasicosInterviniente(false);

			{
				Registro lr_registro;

				lr_registro = new Registro();
				lr_registro.setSolicitud(getSolicitud());

				lr_registro = irr_registroRemote.buscarPersonasPorSolicitudInterviniente(
					    lr_registro, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				llenarDatosIntervinientes(lr_registro, false);
				colapsarPanelesDatosInterviniente();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("buscarPersonasPorSolicitudInterviniente", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método encargado de cambiar la respuesta de la liquidación.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios a cambiar.
	 */
	public void cambiarRespuestaLiquidacion(Liquidacion al_liquidacion)
	{
		if(al_liquidacion != null)
		{
			if(al_liquidacion.isRespuestaBoolean())
				al_liquidacion.setRespuesta(EstadoCommon.S);
		}
	}

	/**
	 * Metodo encargado de cargar los datos de la tabla Actos modificados desde mayor valor.
	 * @param aa_acto Argumento de tipo <code>Acto</code> que contiene los datos del acto modificado.
	 */
	public void cargarActosModificadosPorMayorValor(Acto aa_acto)
	{
		try
		{
			if(aa_acto != null)
			{
				Collection<Acto> lca_datosActo;
				String           ls_idActoDb;

				lca_datosActo     = getDatosActo();
				ls_idActoDb       = StringUtils.getStringNotNull(aa_acto.getIdActoDb());

				if(CollectionUtils.isValidCollection(lca_datosActo))
				{
					{
						Iterator<Acto> lia_iterator;
						boolean        lb_encontrado;

						lia_iterator      = lca_datosActo.iterator();
						lb_encontrado     = false;

						while(lia_iterator.hasNext() && !lb_encontrado)
						{
							Acto la_iterador;

							la_iterador = lia_iterator.next();

							if(la_iterador != null)
							{
								String ls_idActoIterado;

								ls_idActoIterado = la_iterador.getIdActoDb();

								if(StringUtils.isValidString(ls_idActoIterado))
								{
									lb_encontrado = ls_idActoDb.equalsIgnoreCase(ls_idActoIterado);

									if(lb_encontrado)
									{
										{
											Integer lI_cantidadActos;
											Integer lI_cantidadActosIterados;
											int     li_cantidadActos;
											int     li_cantidadActosIterados;

											lI_cantidadActos             = aa_acto.getCantidadActos();
											lI_cantidadActosIterados     = la_iterador.getCantidadActos();
											li_cantidadActos             = NumericUtils.getInt(lI_cantidadActos);
											li_cantidadActosIterados     = NumericUtils.getInt(
												    lI_cantidadActosIterados
												);

											aa_acto.setCantidadModificada(li_cantidadActos != li_cantidadActosIterados);
										}

										{
											BigDecimal lbd_cuantiaActo;
											BigDecimal lbd_cuantiaActoIterado;

											lbd_cuantiaActo            = aa_acto.getCuantiaActo();
											lbd_cuantiaActoIterado     = la_iterador.getCuantiaActo();

											aa_acto.setCuantiaModificada(
											    (lbd_cuantiaActo != null) && (lbd_cuantiaActoIterado != null)
												    && (lbd_cuantiaActo.compareTo(lbd_cuantiaActoIterado) != 0)
											);
										}

										{
											BigDecimal lbd_valorAvaluo;
											BigDecimal lbd_valorAvaluoIterado;

											lbd_valorAvaluo            = aa_acto.getValorAvaluo();
											lbd_valorAvaluoIterado     = la_iterador.getValorAvaluo();

											aa_acto.setValorAvaluoModificado(
											    (lbd_valorAvaluo != null) && (lbd_valorAvaluoIterado != null)
												    && (lbd_valorAvaluo.compareTo(lbd_valorAvaluoIterado) != 0)
											);
										}

										{
											String ls_idTipoAdquisicion;
											String ls_idTipoAdquisicionIterado;

											ls_idTipoAdquisicion            = StringUtils.getStringNotNull(
												    aa_acto.getTipoAdquisicion()
												);
											ls_idTipoAdquisicionIterado     = StringUtils.getStringNotNull(
												    la_iterador.getTipoAdquisicion()
												);

											aa_acto.setIdTipoAdquisicionModificada(
											    !ls_idTipoAdquisicion.equalsIgnoreCase(ls_idTipoAdquisicionIterado)
											);

											{
												TipoAdquisicion lta_tipoAdquisicion;

												lta_tipoAdquisicion = new TipoAdquisicion();

												lta_tipoAdquisicion.setIdTipoAdquisicion(ls_idTipoAdquisicion);

												lta_tipoAdquisicion = ipr_parameterRemote.findTiposAdquisicion(
													    lta_tipoAdquisicion, true, getUserId(), getLocalIpAddress(),
													    getRemoteIpAddress()
													);

												if(lta_tipoAdquisicion != null)
												{
													Collection<TipoAdquisicion> lcta_tipoAdquisicion;

													lcta_tipoAdquisicion = lta_tipoAdquisicion.getInfoAll();

													if(CollectionUtils.isValidCollection(lcta_tipoAdquisicion))
													{
														TipoAdquisicion lta_tmp;

														lta_tmp = lcta_tipoAdquisicion.iterator().next();

														if(lta_tmp != null)
															aa_acto.setNombreTipoAdquisicion(lta_tmp.getNombre());
													}
												}
											}
										}

										{
											Double ld_areaActo;
											Double ld_areaActoIterado;

											ld_areaActo            = aa_acto.getAreaActo();
											ld_areaActoIterado     = la_iterador.getAreaActo();

											aa_acto.setAreaActoModificada(
											    (ld_areaActo != null) && (ld_areaActoIterado != null)
												    && (ld_areaActo.compareTo(ld_areaActoIterado) != 0)
											);
										}

										{
											Double ld_areaTotal;
											Double ld_areaTotalIterado;

											ld_areaTotal            = aa_acto.getAreaTotal();
											ld_areaTotalIterado     = la_iterador.getAreaTotal();

											aa_acto.setAreaTotalModificada(
											    (ld_areaTotal != null) && (ld_areaTotalIterado != null)
												    && (ld_areaTotal.compareTo(ld_areaTotalIterado) != 0)
											);
										}

										{
											String ls_garantiaHipotecaria;
											String ls_garantiaHipotecariaIterado;

											ls_garantiaHipotecaria            = StringUtils.getStringNotNull(
												    aa_acto.getGarantiaHipotecaria()
												);
											ls_garantiaHipotecariaIterado     = StringUtils.getStringNotNull(
												    la_iterador.getGarantiaHipotecaria()
												);

											aa_acto.setGarantiaHipotecariaModificada(
											    !ls_garantiaHipotecaria.equalsIgnoreCase(ls_garantiaHipotecariaIterado)
											);
										}

										{
											String ls_hijuelaPasivo;
											String ls_hijuelaPasivoIterado;

											ls_hijuelaPasivo            = StringUtils.getStringNotNull(
												    aa_acto.getHijuelaPasivo()
												);
											ls_hijuelaPasivoIterado     = StringUtils.getStringNotNull(
												    la_iterador.getHijuelaPasivo()
												);

											aa_acto.setHijuelaPasivoModificada(
											    !ls_hijuelaPasivo.equalsIgnoreCase(ls_hijuelaPasivoIterado)
											);
										}

										{
											String ls_idTipoTarifa;
											String ls_idTipoTarifaIterado;

											ls_idTipoTarifa            = StringUtils.getStringNotNull(
												    aa_acto.getIdTipoTarifaRegistral()
												);
											ls_idTipoTarifaIterado     = StringUtils.getStringNotNull(
												    la_iterador.getIdTipoTarifaRegistral()
												);

											aa_acto.setIdTipoTarifaRegistralModificada(
											    !ls_idTipoTarifa.equalsIgnoreCase(ls_idTipoTarifaIterado)
											);
										}

										{
											long ll_version;
											long ll_versionIterador;

											ll_version             = aa_acto.getVersion();
											ll_versionIterador     = la_iterador.getVersion();

											aa_acto.setVersionModificada(ll_version != ll_versionIterador);
										}

										{
											String ls_organismoInternacional;
											String ls_organismoInternacionalIterado;

											ls_organismoInternacional            = StringUtils.getStringNotNull(
												    aa_acto.getTipoTarifa()
												);
											ls_organismoInternacionalIterado     = StringUtils.getStringNotNull(
												    la_iterador.getTipoTarifa()
												);

											aa_acto.setOrganismoInternacionalModificada(
											    !ls_organismoInternacional.equalsIgnoreCase(
											        ls_organismoInternacionalIterado
											    )
											);
										}

										{
											Long ld_cantidadCertiAntSistema;
											Long ld_cantidadCertiAntSistemaIterado;

											ld_cantidadCertiAntSistema            = aa_acto.getCantidadCertifAntSistema();
											ld_cantidadCertiAntSistemaIterado     = la_iterador
													.getCantidadCertifAntSistema();

											aa_acto.setCantidadCertAntSistemaModificada(
											    (ld_cantidadCertiAntSistema != null)
												    && (ld_cantidadCertiAntSistemaIterado != null)
												    && (ld_cantidadCertiAntSistema.compareTo(
												        ld_cantidadCertiAntSistemaIterado
												    ) != 0)
											);
										}

										{
											String ls_madreCabeza;
											String ls_madreCabezaIterado;

											ls_madreCabeza            = StringUtils.getStringNotNull(
												    aa_acto.getTipoTarifa()
												);
											ls_madreCabezaIterado     = StringUtils.getStringNotNull(
												    la_iterador.getTipoTarifa()
												);

											aa_acto.setMadreCabezaModificada(
											    !ls_madreCabeza.equalsIgnoreCase(ls_madreCabezaIterado)
											);
										}

										{
											Double ld_porcentajeTransferencia;
											Double ld_porcentajeTransferenciaIterado;

											ld_porcentajeTransferencia            = aa_acto.getPorcentajeTransferencia();
											ld_porcentajeTransferenciaIterado     = la_iterador
													.getPorcentajeTransferencia();

											aa_acto.setPorcentajeTransferenciaModificada(
											    (ld_porcentajeTransferencia != null)
												    && (ld_porcentajeTransferenciaIterado != null)
												    && (ld_porcentajeTransferencia.compareTo(
												        ld_porcentajeTransferenciaIterado
												    ) != 0)
											);
										}

										{
											Double ld_areaTransferir;
											Double ld_areaTransferirIterado;

											ld_areaTransferir            = aa_acto.getAreaTransferir();
											ld_areaTransferirIterado     = la_iterador.getAreaTransferir();

											aa_acto.setAreaTransferirModificada(
											    (ld_areaTransferir != null) && (ld_areaTransferirIterado != null)
												    && (ld_areaTransferir.compareTo(ld_areaTransferirIterado) != 0)
											);
										}

										{
											Double ld_areaTotalInmueble;
											Double ld_areaTotalInmuebleIterado;

											ld_areaTotalInmueble            = aa_acto.getAreaTotalInmueble();
											ld_areaTotalInmuebleIterado     = la_iterador.getAreaTotalInmueble();

											aa_acto.setAreaTotalInmuebleModificada(
											    (ld_areaTotalInmueble != null) && (ld_areaTotalInmuebleIterado != null)
												    && (ld_areaTotalInmueble.compareTo(ld_areaTotalInmuebleIterado) != 0)
											);
										}

										{
											String ls_respuestaLey1943;
											String ls_respuestaLey1943Iterado;

											ls_respuestaLey1943            = StringUtils.getStringNotNull(
												    aa_acto.getRespuestaLey1943()
												);
											ls_respuestaLey1943Iterado     = StringUtils.getStringNotNull(
												    la_iterador.getRespuestaLey1943()
												);

											aa_acto.setRespuestaLey1943Modificada(
											    !ls_respuestaLey1943.equalsIgnoreCase(ls_respuestaLey1943Iterado)
											);
										}

										{
											String ls_periodicidadCuantia;
											String ls_periodicidadCuantiaIterado;

											ls_periodicidadCuantia            = StringUtils.getStringNotNull(
												    aa_acto.getPeriodicidadCuantia()
												);
											ls_periodicidadCuantiaIterado     = StringUtils.getStringNotNull(
												    la_iterador.getPeriodicidadCuantia()
												);

											aa_acto.setPeriodicidadCuantiaModificada(
											    !ls_periodicidadCuantia.equalsIgnoreCase(ls_periodicidadCuantiaIterado)
											);
										}

										{
											Long ll_tiempoCanon;
											Long ll_tiempoCanonIterado;

											ll_tiempoCanon            = aa_acto.getTiempoCanon();
											ll_tiempoCanonIterado     = la_iterador.getTiempoCanon();

											aa_acto.setTiempoCanonModificada(
											    (ll_tiempoCanon != null) && (ll_tiempoCanonIterado != null)
												    && (ll_tiempoCanon.compareTo(ll_tiempoCanonIterado) != 0)
											);
										}

										la_iterador.setIndMayorValor(EstadoCommon.M);
									}
								}
							}
						}
					}

					{
						TipoActo ltp_tipoActo;

						ltp_tipoActo = irr_calificacionRemote.findTipoActoById(aa_acto.getCodigo());

						if(ltp_tipoActo != null)
							aa_acto.setEspecificacion(ltp_tipoActo.getNombre());
					}

					{
						Collection<Acto> lca_actosModificados;
						boolean          lb_encontrado;

						lca_actosModificados     = getActosModificados();
						lb_encontrado            = false;

						if(CollectionUtils.isValidCollection(lca_actosModificados))
						{
							Iterator<Acto> lia_iterator;

							lia_iterator = lca_actosModificados.iterator();

							while(lia_iterator.hasNext() && !lb_encontrado)
							{
								Acto la_iterador;

								la_iterador = lia_iterator.next();

								if(la_iterador != null)
								{
									String ls_idActoIterado;

									ls_idActoIterado = la_iterador.getIdActoDb();

									if(StringUtils.isValidString(ls_idActoIterado))
									{
										lb_encontrado = ls_idActoDb.equalsIgnoreCase(ls_idActoIterado);

										if(lb_encontrado)
										{
											la_iterador.setIdActoDb(aa_acto.getIdActoDb());
											la_iterador.setIdSolicitud(aa_acto.getIdSolicitud());
											la_iterador.setIdCirculo(aa_acto.getIdCirculo());
											la_iterador.setValorAvaluo(aa_acto.getValorAvaluo());
											la_iterador.setFechaVencimiento(aa_acto.getFechaVencimiento());
											la_iterador.setActivoPrecalificacion(aa_acto.getActivoPrecalificacion());
											la_iterador.setReferencia(aa_acto.getReferencia());
											la_iterador.setNumeroProceso(aa_acto.getNumeroProceso());
											la_iterador.setIdActoPrincipal(aa_acto.getIdActoPrincipal());
											la_iterador.setCodigo(aa_acto.getCodigo());
											la_iterador.setCuantiaActo(aa_acto.getCuantiaActo());
											la_iterador.setVersion(aa_acto.getVersion());
											la_iterador.setIdProceso(aa_acto.getIdProceso());
											la_iterador.setIdSubProceso(aa_acto.getIdSubProceso());
											la_iterador.setIndMayorValor(aa_acto.getIndMayorValor());
											la_iterador.setCantidadActos(aa_acto.getCantidadActos());
											la_iterador.setEspecificacion(aa_acto.getEspecificacion());
											la_iterador.setActoSinCuantia(aa_acto.getActoSinCuantia());
											la_iterador.setCantidadModificada(aa_acto.isCantidadModificada());
											la_iterador.setCuantiaModificada(aa_acto.isCuantiaModificada());
											la_iterador.setValorAvaluoModificado(aa_acto.isValorAvaluoModificado());
											la_iterador.setNombreTipoAdquisicion(aa_acto.getNombreTipoAdquisicion());
										}
									}
								}
							}
						}

						if(!lb_encontrado)
						{
							if(!CollectionUtils.isValidCollection(lca_actosModificados))
								lca_actosModificados = new ArrayList<Acto>();

							lca_actosModificados.add(aa_acto);

							setActosModificados(lca_actosModificados);
						}
					}

					setActo(null);
					setModifyActo(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarActosModificadosPorMayorValor", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar los datos del botón continuar.
	 */
	public void cargarDatosContinuar()
	{
		cargarDatosContinuar(null);
	}

	/**
	 * Metodo encargado de cargar los datos del botón continuar.
	 * @param armv_registroMayorValor Argumento de tipo <code>RegistroMayorValor</code> que contiene el concepto del mayor valor.
	 */
	public void cargarDatosContinuar(RegistroMayorValor armv_registroMayorValor)
	{
		try
		{
			findActosByIdTurnoIndMayorValor();
			detalleLiquidacion();

			if(isInsertar())
			{
				consultarActosPorIdTurno(getIdTurno());
				getDatosLiquidacion();
			}

			setMostrarContinuar(false);
			setMostrarPreliquidar(true);

			PrimeFaces.current().ajax().update("fMayorValorId:idRegresarDetalleButton");

			if(armv_registroMayorValor != null)
				setConceptoCobroMayorValor(armv_registroMayorValor.getConceptoCobroMayorValor());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosContinuar", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar los datos del botón liquidar.
	 */
	public void cargarDatosLiquidar()
	{
		cargarDatosLiquidar(null);
	}

	/**
	 * Metodo encargado de cargar los datos del botón liquidar.
	 * @param armv_registroMayorValor Argumento de tipo <code>RegistroMayorValor</code> que contiene el concepto del mayor valor.
	 */
	public void cargarDatosLiquidar(RegistroMayorValor armv_registroMayorValor)
	{
		try
		{
			detalleLiquidacion();

			setMostrarContinuar(false);
			setMostrarPreliquidar(false);
			setMostrarLiquidar(false);
			setMostrarGenerarDocumentos(true);
			setMostrarDatosBasicosInterviniente(true);

			consultaActoLiquidacionItem(1);

			setOcultarRegresarDetalle(true);

			{
				BeanDireccion lbd_beanDireccion;

				lbd_beanDireccion = getBeanDireccion();

				lbd_beanDireccion.limpiarBeanDireccion();
				lbd_beanDireccion.setHabilitadoPorConsulta(isHabilitarTurnoYnir());
				lbd_beanDireccion.setDeshabilitarCorrespondencia(isDeshabilitarCorrespondenciaInter());
				lbd_beanDireccion.setDeshabilitarResidencia(isDeshabilitarResidenciaInter());
				lbd_beanDireccion.setMismaDireccionCorrespondencia(getMismaDireccionCorrespondenciaInter());
				lbd_beanDireccion.setForm(cs_ID_FORM);
			}

			if(armv_registroMayorValor != null)
				setConceptoCobroMayorValor(armv_registroMayorValor.getConceptoCobroMayorValor());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosLiquidar", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar los datos precargados de un proceso de mayor valor.
	 */
	public void cargarDatosMayorValor()
	{
		try
		{
			consultarActosPorIdTurno(getIdTurno());

			Collection<Acto> lca_actos;

			lca_actos = getDatosActo();

			if(CollectionUtils.isValidCollection(lca_actos))
			{
				Iterator<Acto>    lia_iterator;
				boolean           lb_insertar;
				boolean           lb_modificar;
				Map<String, Acto> lmsa_actosModificados;

				lia_iterator              = lca_actos.iterator();
				lb_insertar               = false;
				lb_modificar              = false;
				lmsa_actosModificados     = new HashMap<String, Acto>();

				while(lia_iterator.hasNext())
				{
					Acto la_acto;

					la_acto = lia_iterator.next();

					if(la_acto != null)
					{
						String ls_indMayorValor;

						ls_indMayorValor = la_acto.getIndMayorValor();

						if(StringUtils.isValidString(ls_indMayorValor))
						{
							lb_insertar      = ls_indMayorValor.equalsIgnoreCase(EstadoCommon.I);
							lb_modificar     = ls_indMayorValor.equalsIgnoreCase(EstadoCommon.M);

							if(lb_modificar)
								lmsa_actosModificados.put(la_acto.getIdActoDb(), la_acto);
						}
					}
				}

				{
					Collection<CausalMayorValor> lccmv_causales;

					lccmv_causales = getCausalesMayorValor();

					if(CollectionUtils.isValidCollection(lccmv_causales))
					{
						for(CausalMayorValor lcmv_iterador : lccmv_causales)
						{
							if(lcmv_iterador != null)
							{
								String ls_accion;

								ls_accion = lcmv_iterador.getAccion();

								if(StringUtils.isValidString(ls_accion))
								{
									boolean lb_seleccionado;

									lb_seleccionado = (ls_accion.equalsIgnoreCase(EstadoCommon.I) && lb_insertar)
											|| (ls_accion.equalsIgnoreCase(EstadoCommon.M) && lb_modificar);

									lcmv_iterador.setSeleccionado(lb_seleccionado);
								}
							}
						}
					}
				}

				causalSeleccionado();

				{
					Collection<RegistroMayorValor> lcrmv_registroMayorValor;

					lcrmv_registroMayorValor = ilr_liquidacionRemote.consultarRegistroMayorValor(
						    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(CollectionUtils.isValidCollection(lcrmv_registroMayorValor))
					{
						Iterator<RegistroMayorValor> lirmv_iterator;

						lirmv_iterator = lcrmv_registroMayorValor.iterator();

						while(lirmv_iterator.hasNext())
						{
							RegistroMayorValor lrmv_registroMayorValor;

							lrmv_registroMayorValor = lirmv_iterator.next();

							if(lrmv_registroMayorValor != null)
							{
								if(CollectionUtils.isValidCollection(lmsa_actosModificados))
								{
									String ls_idActo;

									ls_idActo = lrmv_registroMayorValor.getIdActo();

									if(StringUtils.isValidString(ls_idActo))
									{
										Acto la_actoModificado;

										la_actoModificado = lmsa_actosModificados.get(ls_idActo);

										if(la_actoModificado != null)
										{
											la_actoModificado.setCantidadActos(
											    NumericUtils.getInteger(lrmv_registroMayorValor.getCantidadActos())
											);
											la_actoModificado.setCuantiaActo(lrmv_registroMayorValor.getCuantia());
											la_actoModificado.setValorAvaluo(lrmv_registroMayorValor.getValorAvaluo());

											cargarActosModificadosPorMayorValor(la_actoModificado);

											cambiarCirculo(getIdCirculo());
											setModificoActo(true);
										}
									}
								}

								{
									Long ll_consecutivo;

									ll_consecutivo = lrmv_registroMayorValor.getConsecutivo();

									if(ll_consecutivo == null)
										cargarDatosContinuar(lrmv_registroMayorValor);
									else if(ll_consecutivo.compareTo(NumericUtils.getLongWrapper(0L)) == 0L)
										cargarDatosPreliquidar(lrmv_registroMayorValor);
									else
										cargarDatosLiquidar(lrmv_registroMayorValor);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosMayorValor", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar los datos del interviniente seleccionado.
	 * @param ap_persona Argumento de tipo <code>Persona</code> que contiene los datos a cargar.
	 */
	public void cargarDatosPersonalesInterMayorValor(Persona ap_persona)
	{
		cargarDatosPersonalesInter(ap_persona);
		setPersonaSeleccionada(getPersonaInter());
		setMostrarDatosBasicosInterviniente(false);
	}

	/**
	 * Metodo encargado de cargar los datos del botón preliquidar.
	 */
	public void cargarDatosPreliquidar()
	{
		cargarDatosPreliquidar(null);
	}

	/**
	 * Metodo encargado de cargar los datos del botón preliquidar.
	 * @param armv_registroMayorValor Argumento de tipo <code>RegistroMayorValor</code> que contiene el concepto del mayor valor.
	 */
	public void cargarDatosPreliquidar(RegistroMayorValor armv_registroMayorValor)
	{
		try
		{
			consultaActoLiquidacionItem(0);

			setMostrarContinuar(false);
			setMostrarPreliquidar(true);
			setMostrarLiquidar(true);
			setOcultarRegresarDetalle(true);

			if(armv_registroMayorValor != null)
				setConceptoCobroMayorValor(armv_registroMayorValor.getConceptoCobroMayorValor());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosPreliquidar", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de verificar si la acción de las causales de mayor valor es insertar o modificar.
	 */
	public void causalSeleccionado()
	{
		try
		{
			Collection<CausalMayorValor> lccmv_causales;

			lccmv_causales = getCausalesMayorValor();

			if(CollectionUtils.isValidCollection(lccmv_causales))
			{
				Iterator<CausalMayorValor> licmv_iterator;

				licmv_iterator = lccmv_causales.iterator();

				if(licmv_iterator != null)
				{
					boolean lb_insertar;
					boolean lb_modificar;

					lb_modificar     = false;
					lb_insertar      = false;

					while(licmv_iterator.hasNext() && (!lb_insertar || !lb_modificar))
					{
						CausalMayorValor lcmv_causal;

						lcmv_causal = licmv_iterator.next();

						if(lcmv_causal != null)
						{
							String ls_accion;

							ls_accion = lcmv_causal.getAccion();

							if(StringUtils.isValidString(ls_accion) && lcmv_causal.isSeleccionado())
							{
								lb_insertar      = (!lb_insertar) ? ls_accion.equalsIgnoreCase(EstadoCommon.I)
									                              : lb_insertar;
								lb_modificar     = (!lb_modificar) ? ls_accion.equalsIgnoreCase(EstadoCommon.M)
									                               : lb_modificar;
							}
						}
					}

					if(lb_insertar || lb_modificar)
					{
						findTiposActos();
						consultarActosPorIdTurno(getIdTurno());

						if(lb_insertar)
							consultaMatriculasBySolicitudCirculo();
					}

					setInsertar(lb_insertar);
					setModificar(lb_modificar);
					setModifyActo(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("causalSeleccionado", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de colapsar los paneles de los datos del interviniente.
	 */
	public void colapsarPanelesDatosInterviniente()
	{
		setMostrarDatosBasicosInterviniente(true);
		setHabilitarPanelDirResidenciaInter(false);
		setHabilitarPanelDirCorrespondenciaInter(false);
		setHabilitarPanelDatosContactoInter(false);

		{
			PrimeFaces lpf_current;

			lpf_current = PrimeFaces.current();

			lpf_current.executeScript("PF('wvPanelDatosBasicosInter').collapse();");
			lpf_current.executeScript("PF('wvPanelDireccionRInter').collapse();");
			lpf_current.executeScript("PF('wvPanelDireccionCInter').collapse();");
			lpf_current.executeScript("PF('wvPanelDatosCInter').collapse();");
		}
	}

	/**
	 * Metodo encargado de consultar los actos y el mayor valor de liquidación item.
	 *
	 * @param li_consecutivo Argumento de tipo <code>int</code> que contiene el consecutivo a consultar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaActoLiquidacionItem(int li_consecutivo)
	    throws B2BException
	{
		try
		{
			Liquidacion             ll_liquidacion;
			Collection<Liquidacion> lcl_resumenCobroMayorValor;

			ll_liquidacion = new Liquidacion();

			ll_liquidacion.setIdTurno(isProcesoCorrecciones() ? getIdTurnoCorrecciones() : getIdTurno());
			ll_liquidacion.setConsecutivo(li_consecutivo);
			ll_liquidacion.setMayorValorModificado(true);
			ll_liquidacion.setIdTipoMayorValor(isProcesoCorrecciones() ? EstadoCommon.R : EstadoCommon.M);

			lcl_resumenCobroMayorValor = ilr_liquidacionRemote.buscarLiquidacionItem(
				    ll_liquidacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcl_resumenCobroMayorValor))
			{
				BigDecimal lbd_totalMayorValor;
				BigDecimal lbd_totalSaldoFavor;

				lbd_totalMayorValor     = BigDecimal.ZERO;
				lbd_totalSaldoFavor     = BigDecimal.ZERO;

				for(Liquidacion ll_iterador : lcl_resumenCobroMayorValor)
				{
					if(ll_iterador != null)
					{
						String ls_indMayorValor;
						ls_indMayorValor = ll_iterador.getIndMayorValor();

						if(StringUtils.isValidString(ls_indMayorValor))
						{
							BigDecimal lbd_valorMayorValor;
							BigDecimal lbd_valorSaldoFavor;

							lbd_valorMayorValor     = ll_iterador.getValorTotal();
							lbd_valorSaldoFavor     = ll_iterador.getValorSaldoFavor();

							if(lbd_valorMayorValor != null)
								lbd_totalMayorValor = lbd_totalMayorValor.add(lbd_valorMayorValor);

							if(lbd_valorSaldoFavor != null)
								lbd_totalSaldoFavor = lbd_totalSaldoFavor.add(lbd_valorSaldoFavor);
						}
					}
				}

				setTotalMayorValor(
				    NumericUtils.conversionCientifica(
				        NumericUtils.getDoubleWrapper(lbd_totalMayorValor), new DecimalFormat("¤#,##0.00")
				    )
				);
				setTotalSaldoFavor(
				    NumericUtils.conversionCientifica(
				        NumericUtils.getDoubleWrapper(lbd_totalSaldoFavor), new DecimalFormat("¤#,##0.00")
				    )
				);

				{
					boolean lb_pagoExceso;
					String  ls_totalSaldoFavor;
					Double  ld_totalSaldoFavor;

					ls_totalSaldoFavor     = getTotalSaldoFavor();
					ls_totalSaldoFavor     = ls_totalSaldoFavor.replace(IdentificadoresCommon.PUNTO, "");
					ls_totalSaldoFavor     = ls_totalSaldoFavor.replace(IdentificadoresCommon.SIGNO_PESOS, "");
					ls_totalSaldoFavor     = ls_totalSaldoFavor.replace(
						    IdentificadoresCommon.SIMBOLO_COMA_TXT, IdentificadoresCommon.PUNTO
						);
					ld_totalSaldoFavor     = NumericUtils.getDoubleWrapper(ls_totalSaldoFavor);
					lb_pagoExceso          = NumericUtils.isValidDouble(ld_totalSaldoFavor, 1D);

					if(lb_pagoExceso)
						addMessage(MessagesKeys.DEBE_GENERAR_NOTA_INFORMATIVA_PAGO_EXCESO);

					setPagoExceso(lb_pagoExceso);
					setMostrarEnviarAlAprobador(!lb_pagoExceso && isMostrarGenerarDocumentos());
					setOcultarRegresarDetalle(!lb_pagoExceso);
				}
			}

			setResumenCobroMayorValor(lcl_resumenCobroMayorValor);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaActoLiquidacionItem", lb2be_e);
			addMessage(lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar todas las solicitud matricula de un circulo y una solicitud.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaMatriculasBySolicitudCirculo()
	    throws B2BException
	{
		try
		{
			SolicitudMatricula lsm_solicitudMatricula;

			lsm_solicitudMatricula = new SolicitudMatricula();

			lsm_solicitudMatricula.setIdCirculo(getIdCirculo());
			lsm_solicitudMatricula.setIdSolicitud(getIdSolicitud());

			{
				SolicitudMatriculaActo lsma_solicitudMatriculaActo;

				lsma_solicitudMatriculaActo = new SolicitudMatriculaActo();

				lsma_solicitudMatriculaActo.setIdTurno(getIdTurno());

				lsm_solicitudMatricula.setSolicitudMatriculaActo(lsma_solicitudMatriculaActo);
			}

			{
				Collection<SolicitudMatricula> lcsm_solicitudMatricula;

				lcsm_solicitudMatricula = ier_entregaRemote.findMatriculasBySolicitudCirculoTurno(
					    lsm_solicitudMatricula, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lcsm_solicitudMatricula != null)
				{
					Collection<PredioActoIU> lcpaui_tmp;

					lcpaui_tmp = new ArrayList<PredioActoIU>();

					for(SolicitudMatricula lsm_iterador : lcsm_solicitudMatricula)
					{
						if(lsm_iterador != null)
						{
							PredioActoIU lpaui_nuevo;
							int          li_consecutivo;

							lpaui_nuevo        = new PredioActoIU(getListarColumnas());
							li_consecutivo     = 1;

							lpaui_nuevo.setIdCirculo(lsm_iterador.getIdCirculo());
							lpaui_nuevo.setIdMatricula(NumericUtils.getLongWrapper(lsm_iterador.getIdMatricula()));
							lpaui_nuevo.setIdTurnoCertificado(lsm_iterador.getIdTurnoCertificado());
							lpaui_nuevo.setCertificadoAsociado(false);
							lpaui_nuevo.setHabilitarCert(false);
							lpaui_nuevo.setIngresoNuevo(true);
							lpaui_nuevo.setConsecutivo(li_consecutivo++);

							lcpaui_tmp.add(lpaui_nuevo);
						}
					}

					setActosAsociadosGeneral(lcpaui_tmp);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaMatriculasBySolicitudCirculo", lb2be_e);
			addMessage(lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar todos los actos de un turno.
	 * @param as_idTurno Argumento de tipo String que contiene el id_turno para realizar la consulta.
	 */
	public void consultarActosPorIdTurno(String as_idTurno)
	{
		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				Collection<Acto> lca_actos;

				lca_actos = irr_calificacionRemote.findActosByIdTurno(
					    as_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lca_actos))
				{
					Acto la_acto;

					la_acto = lca_actos.iterator().next();

					if(la_acto != null)
						setIdSolicitud(la_acto.getIdSolicitud());

					setDatosActo(lca_actos);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("consultarActosPorIdTurno", lb2be_e);
				addMessage(lb2be_e);
			}
			finally
			{
				actualizarComponente(cs_ID_GROWL);
			}
		}
	}

	/**
	 * Metodo que se encarga de consultar todos los registros de causales mayor valor con estado S.
	 */
	public void consultarCausalesMayorValor()
	{
		try
		{
			setCausalesMayorValor(
			    ipr_parameterRemote.findCausalesMayorValorPorEstado(
			        EstadoCommon.S, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarCausalesMayorValor", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar los turnos certificados de una solicitud.
	 */
	public void consultarSolicitudMatriculaPorSolicitud()
	{
		try
		{
			SolicitudMatricula lsm_solicitudMatricula;

			lsm_solicitudMatricula = new SolicitudMatricula();
			lsm_solicitudMatricula.setIdSolicitud(getIdSolicitud());

			setTurnosCertificados(irr_calificacionRemote.findSolicitudMatricula(lsm_solicitudMatricula));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarSolicitudMatriculaPorSolicitud", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargardo de consultar una solicitud por turno.
	 */
	public void consultarSolicitudPorIdTurno()
	{
		try
		{
			Solicitud ls_solicitud;

			ls_solicitud = irr_calificacionRemote.findSolicitudByIdTurno(
				    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ls_solicitud != null)
			{
				setIdSolicitud(ls_solicitud.getIdSolicitud());
				setSolicitud(ls_solicitud);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarSolicitudPorIdTurno", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de ejecutar el metodo de liquidación cuando se oprime el botón continuar.
	 */
	public void continuar()
	{
		try
		{
			liquidarMayorValor(IdentificadoresCommon.CONDICIONES, getActosModificados(), getActosAsociadosGeneral());
			cargarDatosContinuar();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("continuar", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar el detalle de la liquidación para una solicitud.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void detalleLiquidacion()
	    throws B2BException
	{
		try
		{
			Liquidacion ll_liquidacion;

			ll_liquidacion = new Liquidacion();

			ll_liquidacion.setIdSolicitud(getIdSolicitud());
			ll_liquidacion.setIdTurno(getIdTurno());
			ll_liquidacion.setIdTipoMayorValor(MayorValorCommon.MAYOR_VALOR);

			setDetalleLiquidacion(
			    ilr_liquidacionRemote.detalleLiquidacion(
			        ll_liquidacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("detalleLiquidacion", lb2be_e);
			addMessage(lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC
	 */
	public void documentosEnviadosOWCC()
	{
		try
		{
			boolean lb_enviados;

			lb_enviados = validarEnvioDocumentosOWCC(getIdTurnoHistoria());

			setDocumentosEnviados(lb_enviados);
			setMostrarEnviarAlAprobador(true);

			if(lb_enviados)
				PrimeFaces.current().executeScript("PF('wvPoll').stop();");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
			actualizarComponente(cs_ID_FORM + ":opPanelActos");
		}
	}

	/**
	 * Método que se encarga de eliminar un acto de los actos insertados en mayor valor.
	 *
	 * @param aa_acto Argumento de tipo <code>Acto</code> que contiene los criterios necesarios para eliminar el registro de la tabla de actos insertados.
	 */
	public void eliminarActoInsertado(Acto aa_acto)
	{
		try
		{
			if(aa_acto != null)
			{
				String ls_idActoDb;

				ls_idActoDb = aa_acto.getIdActoDb();

				if(StringUtils.isValidString(ls_idActoDb))
				{
					ilr_liquidacionRemote.eliminarActoMayorValor(
					    ls_idActoDb, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
					consultarActosPorIdTurno(getIdTurno());

					setOcultarRegresarDetalle(false);
					setMostrarContinuar(true);
					setMostrarPreliquidar(false);
					setMostrarLiquidar(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("eliminarActoInsertado", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo que se encarga de eliminar un acto de los actos modificados.
	 * @param aa_acto Argumento de tipo <code>Acto</code> que contiene los
	 * criterios necesarios para eliminar el registro de la tabla de actos modificados.
	 */
	public void eliminarActoModificado(Acto aa_acto)
	{
		if(aa_acto != null)
		{
			Collection<Acto> lca_actosModificados;
			String           ls_idActoDb;

			lca_actosModificados     = getActosModificados();
			ls_idActoDb              = aa_acto.getIdActoDb();

			if(CollectionUtils.isValidCollection(lca_actosModificados) && StringUtils.isValidString(ls_idActoDb))
			{
				Collection<Acto> lca_actosModificadosFinal;

				lca_actosModificadosFinal = new ArrayList<Acto>();

				for(Acto la_iterador : lca_actosModificados)
				{
					if(la_iterador != null)
					{
						String ls_idActoIterado;

						ls_idActoIterado = la_iterador.getIdActoDb();

						if(
						    StringUtils.isValidString(ls_idActoIterado)
							    && !ls_idActoDb.equalsIgnoreCase(ls_idActoIterado)
						)
							lca_actosModificadosFinal.add(la_iterador);
					}
				}

				if(!CollectionUtils.isValidCollection(lca_actosModificadosFinal))
					lca_actosModificadosFinal = null;

				setActosModificados(lca_actosModificadosFinal);
			}
		}
	}

	/**
	 * Metodo encargado de terminar el proceso y enviar el caso a aprobación.
	 *
	 * @return devuelve el valor de String
	 */
	public String enviarAlAprobador()
	{
		String ls_retorno;

		ls_retorno = null;

		try
		{
			boolean lb_migrado;
			String  ls_justificacion;

			lb_migrado           = isMigrado();
			ls_justificacion     = getObservaciones();

			if(lb_migrado && !StringUtils.isValidString(ls_justificacion))
			{
				FacesContext lfc_context;
				String       ls_idJustificacion;

				lfc_context            = FacesContext.getCurrentInstance();
				ls_idJustificacion     = cs_ID_FORM + ":idITAJustificacion";

				validateStyles(ls_idJustificacion, lfc_context, ls_justificacion, true);
				actualizarComponente(ls_idJustificacion);

				throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
			}

			{
				TurnoHistoria lth_turnoHistoria;
				MotivoTramite lmt_motivoTramite;
				boolean       lb_procesoCorrecciones;

				lth_turnoHistoria          = new TurnoHistoria();
				lmt_motivoTramite          = new MotivoTramite();
				lb_procesoCorrecciones     = isProcesoCorrecciones();

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lth_turnoHistoria.setObservaciones(ls_justificacion);

				lmt_motivoTramite.setIdEtapaOrigen(
				    lb_procesoCorrecciones ? EtapaCommon.ID_ETAPA_CORRECCIONES : EtapaCommon.ID_ETAPA_CALIFICACION
				);
				lmt_motivoTramite.setIdMotivo(
				    isMigrado() ? MotivoTramiteCommon.HOMOLOGACION_ACTOS_LIQUIDACION_MAYOR_VALOR
				                : (lb_procesoCorrecciones ? MotivoTramiteCommon.COBRO_POR_MAYOR_VALOR_CORRECCIONES
				                                          : MotivoTramiteCommon.COBRO_POR_MAYOR_VALOR)
				);

				irr_aprobacionRemote.actualizarEtapaYCrearSiguiente(
				    lth_turnoHistoria, lmt_motivoTramite, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}

			limpiarMayorValor();

			{
				FacesContext lfc_context;
				BeanTurnos   lbt_bean;

				lfc_context     = FacesContext.getCurrentInstance();

				lbt_bean = (BeanTurnos)lfc_context.getApplication()
						                              .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TURNOS, BeanTurnos.class
						);

				lbt_bean.setNirConsulta(null);
				lbt_bean.setIdTurnoConsulta(null);
				lbt_bean.generarData();
			}

			ls_retorno = NavegacionCommon.TURNOS;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarAlAprobador", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}

		return ls_retorno;
	}

	/**
	 * Metodo encargado de validar si se insertó o se modificó un acto para mayor valor.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void findActosByIdTurnoIndMayorValor()
	    throws B2BException
	{
		try
		{
			Collection<Acto> lca_actos;

			lca_actos = irr_calificacionRemote.findActosByIdTurnoIndMayorValor(
				    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(!CollectionUtils.isValidCollection(lca_actos))
			{
				boolean lb_insertar;
				boolean lb_modificar;
				String  ls_error;

				lb_insertar      = isInsertar();
				lb_modificar     = isModificar();
				ls_error         = null;

				if(!lb_insertar && lb_modificar)
					ls_error = ErrorKeys.DEBE_MODIFICAR_ACTO_MAYOR_VALOR;
				else if(lb_insertar && !lb_modificar)
					ls_error = ErrorKeys.DEBE_AGREGAR_ACTO_MAYOR_VALOR;
				else
					ls_error = ErrorKeys.DEBE_MODIFICAR_AGREGAR_ACTO_MAYOR_VALOR;

				throw new B2BException(ls_error);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findActosByIdTurnoIndMayorValor", lb2be_e);
			addMessage(lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de generar los documentos de mayor valor.
	 */
	public void generarDocumentos()
	{
		try
		{
			Collection<ZipEntryUtil> lczeu_zip;

			lczeu_zip = new ArrayList<ZipEntryUtil>();

			{
				TurnoHistoria lth_turnoHistoria;
				byte[]        lba_notaInformativa;
				byte[]        lba_cobroMayorValor;
				boolean       lb_pagoExceso;

				lth_turnoHistoria       = new TurnoHistoria();
				lba_notaInformativa     = null;
				lba_cobroMayorValor     = null;

				lth_turnoHistoria.setIdSolicitud(getIdSolicitud());
				lth_turnoHistoria.setIdTurno(getIdTurno());
				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lth_turnoHistoria.setPersona(getPersonaSeleccionada());
				lth_turnoHistoria.setObservaciones(getObservaciones());
				lb_pagoExceso = false;

				{
					String ls_userId;
					String ls_localIp;
					String ls_remoteIp;
					String ls_totalSaldoFavor;
					Double ld_totalSaldoFavor;

					ls_userId              = getUserId();
					ls_localIp             = getLocalIpAddress();
					ls_remoteIp            = getRemoteIpAddress();
					ls_totalSaldoFavor     = getTotalSaldoFavor();
					ls_totalSaldoFavor     = ls_totalSaldoFavor.replace(IdentificadoresCommon.PUNTO, "");
					ls_totalSaldoFavor     = ls_totalSaldoFavor.replace(IdentificadoresCommon.SIGNO_PESOS, "");
					ls_totalSaldoFavor     = ls_totalSaldoFavor.replace(
						    IdentificadoresCommon.SIMBOLO_COMA_TXT, IdentificadoresCommon.PUNTO
						);
					ld_totalSaldoFavor     = NumericUtils.getDoubleWrapper(ls_totalSaldoFavor);
					lb_pagoExceso          = NumericUtils.isValidDouble(ld_totalSaldoFavor, 1D);

					if(lb_pagoExceso)
						lba_notaInformativa = ilr_liquidacionRemote.generarPDFNotaInformativaPorPagoEnExceso(
							    lth_turnoHistoria, ls_userId, ls_localIp, ls_remoteIp
							);
					else
						lba_cobroMayorValor = ilr_liquidacionRemote.generarPDFCobroMayorValor(
							    lth_turnoHistoria, ls_userId, ls_localIp, ls_remoteIp
							);
				}

				if(lba_notaInformativa != null)
				{
					ZipEntryUtil lzeu_pdf;

					lzeu_pdf = new ZipEntryUtil("NOTA_INFORMATIVA.pdf", new ByteArrayInputStream(lba_notaInformativa));

					lczeu_zip.add(lzeu_pdf);
				}

				if(lba_cobroMayorValor != null)
				{
					ZipEntryUtil lzeu_pdf;

					lzeu_pdf = new ZipEntryUtil("COBRO_MAYOR_VALOR.pdf", new ByteArrayInputStream(lba_cobroMayorValor));

					lczeu_zip.add(lzeu_pdf);
				}

				if(!lb_pagoExceso)
				{
					DocumentosSalida lds_documentosSalida;

					lds_documentosSalida = new DocumentosSalida();

					lds_documentosSalida.setIdSolicitud(getIdSolicitud());
					lds_documentosSalida.setIdTurnoHistoria(NumericUtils.getInteger(getIdTurnoHistoria()));
					lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.RECIBO_LIQUIDACION);
					lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);

					{
						Imagenes li_imagenes;

						li_imagenes = ilr_liquidacionRemote.consultarImagenDocumento(
							    lds_documentosSalida, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(li_imagenes != null)
						{
							byte[] lba_pdfLiquidacion;

							lba_pdfLiquidacion = li_imagenes.getImagenBlob();

							if(lba_pdfLiquidacion != null)
							{
								ZipEntryUtil lzeu_pdf;

								lzeu_pdf = new ZipEntryUtil(
									    "LIQUIDACION.pdf", new ByteArrayInputStream(lba_pdfLiquidacion)
									);

								lczeu_zip.add(lzeu_pdf);
							}
						}
					}
				}
			}

			if(CollectionUtils.isValidCollection(lczeu_zip))
			{
				PrimeFaces.current().executeScript("PF('wvPoll').start();");

				setMostrarVisualizarDocumentos(true);

				{
					boolean lb_pagoExceso;

					lb_pagoExceso = isPagoExceso();

					setOcultarRegresarDetalle(!lb_pagoExceso);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentos", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de guardar información de intervinientes de mayor valor.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarIntervinienteMayorValor()
	    throws B2BException
	{
		try
		{
			agregarInterviniente(cs_ID_FORM, true, MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE_GENERE_DOCS);
			setMostrarBotonGenerarDocumentos(true);
			buscarPersonasPorSolicitudInterviniente();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarIntervinienteMayorValor", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de limpiar variables en sesión.
	 */
	public void limpiarMayorValor()
	{
		setCausalesMayorValor(null);
		setDetalleLiquidacion(null);
		setResumenCobroMayorValor(null);
		setSolicitudMatriculaSalvar(null);
		setTurnosCertificados(null);
		setSolicitudMatriculaActo(null);
		setZipDocumentosGeneradosDescarga(null);
		setConceptoCobroMayorValor(null);
		setIdRegistroMayorValor(null);
		setIdSolicitud(null);
		setObservaciones(null);
		setTotalMayorValor(null);
		setTotalSaldoFavor(null);
		setZipDocumentosGenerados(null);
		setInsertar(false);
		setModificar(false);
		setMostrarContinuar(false);
		setMostrarDocumentos(false);
		setMostrarEnviarAlAprobador(false);
		setMostrarGenerarDocumentos(false);
		setMostrarBotonGenerarDocumentos(false);
		setMostrarLiquidar(false);
		setMostrarPreliquidar(false);
		setMostrarVisualizarDocumentos(false);
		setOcultarRegresarDetalle(false);
		setMigrado(false);
		setActosModificados(null);
		setPersonaSeleccionada(null);
		setInsertoActo(false);
		setModificoActo(false);
		setTotalMayorValor(null);
		setTotalSaldoFavor(null);
		setPagoExceso(false);
		setTurnoRegistroCorrecciones(null);
		setTurnoMigradoSinHomologar(false);
		setCumpleValidacionesCorrecciones(false);
		setProcesoCorrecciones(false);
		setIdProceso(null);
	}

	/**
	 * Metodo encargado de ejecutar el metodo de liquidación cuando se oprime el botón liquidar.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void liquidar()
	    throws B2BException
	{
		try
		{
			liquidarMayorValor(IdentificadoresCommon.LIQUIDAR);
			cargarDatosLiquidar();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("liquidar", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de liquidar proceso mayor valor.
	 *
	 * @param as_condicion correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void liquidarMayorValor(String as_condicion)
	    throws B2BException
	{
		try
		{
			liquidarMayorValor(as_condicion, null);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("liquidarMayorValor", lb2be_e);
			addMessage(lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de liquidar proceso mayor valor.
	 *
	 * @param as_condicion Argumento de tipo String que contiene la condición con la que se ejecuta la liquidación.
	 * @param aca_actosRegistoMayorValor Argumento de tipo Collection que contiene los actos que se insertarán en
	 * registro mayor valor, cuando el parametro llego null no se inserta ningún registro.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void liquidarMayorValor(String as_condicion, Collection<Acto> aca_actosRegistoMayorValor)
	    throws B2BException
	{
		liquidarMayorValor(as_condicion, aca_actosRegistoMayorValor, null);
	}

	/**
	 * Liquidar mayor valor.
	 *
	 * @param as_condicion correspondiente al valor del tipo de objeto String
	 * @param aca_actosRegistoMayorValor correspondiente al valor del tipo de objeto Collection<Acto>
	 * @param acpai_solicitudMatriculaActo correspondiente al valor del tipo de objeto Collection<PredioActoIU>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void liquidarMayorValor(
	    String as_condicion, Collection<Acto> aca_actosRegistoMayorValor,
	    Collection<PredioActoIU> acpai_solicitudMatriculaActo
	)
	    throws B2BException
	{
		liquidarMayorValor(as_condicion, aca_actosRegistoMayorValor, acpai_solicitudMatriculaActo, null);
	}

	/**
	 * Metodo encargado de liquidar proceso mayor valor.
	 *
	 * @param as_condicion Argumento de tipo String que contiene la condición con la que se ejecuta la liquidación.
	 * @param aca_actosRegistoMayorValor Argumento de tipo Collection que contiene los actos que se insertarán en
	 * registro mayor valor, cuando el parametro llego null no se inserta ningún registro.
	 * @param acpai_solicitudMatriculaActo Argumento de tipo Collection<PredioActoIU> que contiene
	 * las matriculas a asociar con los actos.
	 * @param acl_condiciones correspondiente al valor del tipo de objeto Collection<Liquidacion>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void liquidarMayorValor(
	    String as_condicion, Collection<Acto> aca_actosRegistoMayorValor,
	    Collection<PredioActoIU> acpai_solicitudMatriculaActo, Collection<Liquidacion> acl_condiciones
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_condicion))
		{
			try
			{
				boolean lb_procesoCorrecciones;

				lb_procesoCorrecciones = isProcesoCorrecciones();

				if(isInsertar() && !isInsertoActo())
					throw new B2BException(ErrorKeys.DEBE_AGREGAR_ACTO_MAYOR_VALOR);

				if(isModificar() && !isModificoActo())
					throw new B2BException(ErrorKeys.DEBE_MODIFICAR_ACTO_MAYOR_VALOR);

				String      ls_tipoMayorValor;
				Liquidacion ll_liquidacion;

				ls_tipoMayorValor     = lb_procesoCorrecciones ? MayorValorCommon.CORRECION_R
					                                           : MayorValorCommon.MAYOR_VALOR;
				ll_liquidacion        = new Liquidacion();

				ll_liquidacion.setIdSolicitud(getIdSolicitud());
				ll_liquidacion.setIdTurno(getIdTurno());
				ll_liquidacion.setIdTurnoCorrecciones(getIdTurnoCorrecciones());
				ll_liquidacion.setIdTurnoHistoria(getIdTurnoHistoria());
				ll_liquidacion.setIdTipoMayorValor(ls_tipoMayorValor);
				ll_liquidacion.setIdCondicion(as_condicion);
				ll_liquidacion.setActosRegistroMayorValor(aca_actosRegistoMayorValor);

				if(isInsertar())
					asociarMatriculasActos(acpai_solicitudMatriculaActo);

				ll_liquidacion.setSolicitudMatricula(getSolicitudMatriculaSalvar());
				ll_liquidacion.setSolicitudMatriculaActo(getSolicitudMatriculaActo());
				ll_liquidacion.setCondiciones(acl_condiciones);
				ll_liquidacion.setLiquidar(
				    as_condicion.equalsIgnoreCase(IdentificadoresCommon.CONDICIONES) ? isInsertar() : true
				);

				if(lb_procesoCorrecciones)
				{
					Solicitud ls_solicitud;

					ls_solicitud = new Solicitud();

					ls_solicitud.setIdTurnoAnt(getTurnoRegistroCorrecciones());

					ll_liquidacion.setSolicitud(ls_solicitud);
				}

				{
					RegistroMayorValor lrmv_registroMayorValor;

					lrmv_registroMayorValor = new RegistroMayorValor();

					lrmv_registroMayorValor.setIdRegistroMayorValor(getIdRegistroMayorValor());
					lrmv_registroMayorValor.setIdTipoMayorValor(ls_tipoMayorValor);

					{
						FacesContext lfc_context;

						lfc_context = FacesContext.getCurrentInstance();

						{
							String ls_conceptoMayorValor;

							ls_conceptoMayorValor = getConceptoCobroMayorValor();

							validateStyles(cs_ID_FORM + ":idITAConcepto", lfc_context, ls_conceptoMayorValor, true);

							if(!StringUtils.isValidString(ls_conceptoMayorValor))
								throw new B2BException(ErrorKeys.ERROR_SIN_CONCEPTO);

							lrmv_registroMayorValor.setConceptoCobroMayorValor(ls_conceptoMayorValor);
						}

						{
							String ls_totalSaldoFavor;

							ls_totalSaldoFavor = getTotalSaldoFavor();

							if(StringUtils.isValidString(ls_totalSaldoFavor))
							{
								BigDecimal lbd_totalSaldoAFavor;

								lbd_totalSaldoAFavor = NumericUtils.getBigDecimal(ls_totalSaldoFavor);

								if(NumericUtils.isValidBigDecimal(lbd_totalSaldoAFavor, BigDecimal.valueOf(1D)))
								{
									RegistroPagoExceso lrpe_registroPagoExceso;

									lrpe_registroPagoExceso = getRegistroPagoExceso();

									if(lrpe_registroPagoExceso != null)
									{
										String ls_conceptoPagoExceso;

										ls_conceptoPagoExceso = lrpe_registroPagoExceso.getConceptoPagoExceso();

										validateStyles(
										    cs_ID_FORM + ":idITAConceptoPagoExceso", lfc_context, ls_conceptoPagoExceso,
										    true
										);

										if(!StringUtils.isValidString(ls_conceptoPagoExceso))
											throw new B2BException(ErrorKeys.ERROR_SIN_CONCEPTO);

										ll_liquidacion.setRegistroPagoExceso(lrpe_registroPagoExceso);
									}
								}
							}
						}
					}

					lrmv_registroMayorValor.setEstadoMayorValor(EstadoCommon.I);

					ll_liquidacion.setRegistroMayorValor(lrmv_registroMayorValor);
				}

				ll_liquidacion = ilr_liquidacionRemote.liquidar(
					    ll_liquidacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(ll_liquidacion != null)
				{
					String   ls_respuesta;
					Registro lr_registro;

					ls_respuesta     = ll_liquidacion.getRespuestaLiquidacion();
					lr_registro      = ll_liquidacion.getRegistro();

					if(StringUtils.isValidString(ls_respuesta))
						addMessage(ls_respuesta);

					if(lr_registro != null)
						setPdfLiquidacion(lr_registro.getPdf());
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("liquidarMayorValor", lb2be_e);
				addMessage(lb2be_e);
				throw lb2be_e;
			}
			finally
			{
				actualizarComponente(cs_ID_GROWL);
			}
		}
	}

	/**
	 * Metodo que se encarga de insertar o modificar los actos de un turno dependiendo del arguento enviado
	 * si es true entonces insertar un nuevo acto de lo contrario modifica un acto.
	 * @param ab_insertar Argumento de tipo boolean que determina si se debe insertar (true) o modificar (false)
	 * un acto.
	 */
	public void modificarInsertarActoMayorValor(boolean ab_insertar)
	{
		try
		{
			Acto la_acto;

			la_acto = getActo();

			if(la_acto != null)
			{
				validarCamposActo(cs_ID_FORM);

				{
					String ls_circulo;

					ls_circulo = la_acto.getIdCirculo();

					if(!StringUtils.isValidString(ls_circulo))
						throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_ACTO);
				}

				{
					String ls_codigoActo;

					ls_codigoActo = la_acto.getCodigo();

					if(!StringUtils.isValidString(ls_codigoActo) || ls_codigoActo.equalsIgnoreCase("-1"))
						throw new B2BException(ErrorKeys.DEBE_ESCOGER_CODIGO_ACTO);
				}

				la_acto.setIndMayorValor(ab_insertar ? EstadoCommon.I : EstadoCommon.M);

				if(ab_insertar)
					validarRecibos(cs_ID_FORM);
				else
					cargarActosModificadosPorMayorValor(la_acto);

				cambiarCirculo(getIdCirculo());

				if(ab_insertar)
				{
					consultarActosPorIdTurno(getIdTurno());
					setInsertoActo(true);
				}
				else
					setModificoActo(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("modificarInsertarActoMayorValor", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de ejecutar el metodo de liquidación cuando se oprime el botón preliquidar.
	 */
	public void preLiquidar()
	{
		try
		{
			liquidarMayorValor(IdentificadoresCommon.PRELIQUIDAR, null, null, isInsertar() ? getTabsCirculos() : null);
			cargarDatosPreliquidar();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("saveInfoTiposDocMayorValor", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de crear acto de división material.
	 */
	public void preguntaDivisionMaterialMayorValor()
	{
		try
		{
			preguntaDivisionMaterial(cs_ID_FORM);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("preguntaDivisionMaterial", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de recibir la respuesta del usuario por pantalla para determinar
	 * si el acto corresponde a un cementerio.
	 *
	 * @param ab_noCementerio correspondiente al valor del tipo de objeto boolean
	 */
	public void respuestaCementerioMayorValor(boolean ab_noCementerio)
	{
		try
		{
			respuestaCementerio(ab_noCementerio, cs_ID_FORM);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("respuestaCementerioMayorValor", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de insertar los tipos documentales asociados a un acto.
	 *
	 * @param aa_acto Argumento de tipo Acto que contiene los datos necesarios para insertar los
	 * tipos documentales.
	 */
	public void saveInfoTiposDocMayorValor(Acto aa_acto)
	{
		try
		{
			saveInfoTiposDoc(aa_acto, cs_ID_FORM, isProcesoCorrecciones());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("saveInfoTiposDocMayorValor", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de insertar los tipos documentales asociados a un acto.
	 *
	 * @param aa_acto Argumento de tipo Acto que contiene los datos necesarios para insertar los
	 * tipos documentales.
	 */
	public void tiposDocMayorValor(Acto aa_acto)
	{
		try
		{
			tiposDoc(aa_acto, cs_ID_FORM, isProcesoCorrecciones());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("tiposDocMayorValor", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método encargado de validar el turno ingresado para correcciones mayor valor.
	 */
	public String validacionesTurnoRegistroCorrecciones()
	{
		String ls_return;

		ls_return = null;

		try
		{
			boolean lb_turnoMigradoSinHomologar;

			lb_turnoMigradoSinHomologar = ilr_liquidacionRemote.validacionesTurnoRegistroCorrecciones(
				    getTurnoRegistroCorrecciones(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setTurnoMigradoSinHomologar(lb_turnoMigradoSinHomologar);
			setCumpleValidacionesCorrecciones(!lb_turnoMigradoSinHomologar);

			iniciarMayorValorCorrecciones();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validacionesTurnoRegistroCorrecciones", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}

		return ls_return;
	}

	/**
	 * Método encargado de validar si se insertó un acto desde mayor valor.
	 *
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public void validarActosMayorValor()
	    throws B2BException
	{
		Solicitud ls_solicitud;

		ls_solicitud = getSolicitud();

		if(ls_solicitud != null)
		{
			String ls_idSolicitud;

			ls_idSolicitud = ls_solicitud.getIdSolicitud();

			if(StringUtils.isValidString(ls_idSolicitud) && !isInsertoActo())
				setInsertoActo(irr_registroRemote.validarActosMayorValor(ls_idSolicitud));
		}
	}

	/**
	 * Metodo encargado de visualizar los documentos de mayor valor.
	 *
	 * @return Retorna la regla de navegación encargada de mostrar los documentos del gestor documental.
	 */
	public String visualizarDocumentos()
	{
		String ls_retorno;

		ls_retorno = null;

		try
		{
			BeanPredioDocumentoActo lbpda_bean;

			lbpda_bean = (BeanPredioDocumentoActo)obtenerInstanciaBean(
				    BeanPredioDocumentoActo.class, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO
				);

			{
				Solicitud ls_solicitud;

				ls_solicitud = getSolicitud();

				if(ls_solicitud != null)
				{
					lbpda_bean.setNir(ls_solicitud.getNir());
					lbpda_bean.setIdTurno(getIdTurno());
				}
			}

			ls_retorno = lbpda_bean.consultaSGD(OperadorCommon.OR);

			{
				boolean lb_pagoExceso;

				lb_pagoExceso = isPagoExceso();

				setOcultarRegresarDetalle(!lb_pagoExceso);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("visualizarDocumentos", lb2be_e);
			setMostrarEnviarAlAprobador(false);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
			actualizarComponente(cs_ID_FORM + ":opPanelActos");
		}

		return ls_retorno;
	}

	/**
	 * Método encargado de iniciar mayor valor para el proceso de correcciones.
	 */
	private void iniciarMayorValorCorrecciones()
	{
		try
		{
			boolean lb_turnoMigrado;
			String  ls_idTurno;
			String  ls_idTurnoHistoria;

			lb_turnoMigrado        = false;
			ls_idTurno             = getTurnoRegistroCorrecciones();
			ls_idTurnoHistoria     = getIdTurnoHistoria();

			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();
				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));
				lth_turnoHistoria.setIdTurno(ls_idTurno);

				lb_turnoMigrado = irr_calificacionRemote.validarTurnoMigrado(
					    lth_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}

			if(lb_turnoMigrado)
			{
				setMigrado(lb_turnoMigrado);

				addMessage(MessagesKeys.TURNO_MIGRADO);
			}
			else
			{
				Turno  lt_turno;
				String ls_idCirculo;

				lt_turno         = new Turno();
				ls_idCirculo     = null;

				lt_turno.setIdTurno(ls_idTurno);

				lt_turno = iasr_antiguoSistemaRemote.findIdCirculoOrigen(lt_turno);

				if(lt_turno != null)
					ls_idCirculo = lt_turno.getIdCirculo();

				setIdCirculo(ls_idCirculo);
				setIdTurnoCorrecciones(getIdTurno());
				setIdTurno(ls_idTurno);

				if(StringUtils.isValidString(ls_idCirculo))
				{
					Acto la_acto;

					la_acto = getActo();

					if(la_acto != null)
						la_acto.setIdCirculo(ls_idCirculo);
				}
			}

			setMostrarContinuar(true);
			setIdTurnoHistoria(getIdTurnoHistoria());
			consultarSolicitudPorIdTurno();
			consultarCausalesMayorValor();
			consultarSolicitudMatriculaPorSolicitud();
			setProcesoCorrecciones(true);
			cargarMediosNotComInter(false);
			buscarPersonasPorSolicitudInterviniente();
			validarActosMayorValor();
			cargarDatosMayorValor();
			documentosEnviadosOWCC();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("iniciarMayorValorCorrecciones", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}
}
