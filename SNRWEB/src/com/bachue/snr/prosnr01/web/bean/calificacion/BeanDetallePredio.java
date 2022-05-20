package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio.ConsultaPredioRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.CabidaLinderos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.LinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;

import com.bachue.snr.prosnr01.web.bean.consulta.estado.predio.BeanConsultaPredio;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanDetallePredio.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanDetallePredio")
@SessionScoped
public class BeanDetallePredio extends BeanConsultaPredio implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4872785228571537090L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fConsultaEstadoPredio:globalMsg";

	/** Propiedad iopr acc predio registro. */
	private AccPredioRegistro iopr_accPredioRegistro;

	/** Propiedad aa aprobacion. */
	private Aprobacion aa_aprobacion;

	/** Propiedad icepe consulta predio remote. */
	@EJB
	private ConsultaPredioRemote icepe_consultaPredioRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is pantalla anterior*/
	private String is_pantallaAnterior;

	/** Propiedad is tipo matricula. */
	private String is_tipoMatricula;

	/** Propiedad ib viene de trazabilidad. */
	private boolean ib_vieneDeTrazabilidad;

	/** Propiedad is matricula definitiva. */
	private long is_matriculaDefinitiva;

	/**
	 * Modifica el valor de acc predio registro.
	 *
	 * @param aapr_apr asigna el valor a la propiedad acc predio registro
	 */
	public void setAccPredioRegistro(AccPredioRegistro aapr_apr)
	{
		iopr_accPredioRegistro = aapr_apr;
	}

	/**
	 * Retorna el valor de acc predio registro.
	 *
	 * @return el valor de acc predio registro
	 */
	public AccPredioRegistro getAccPredioRegistro()
	{
		if(iopr_accPredioRegistro == null)
			iopr_accPredioRegistro = new AccPredioRegistro();

		return iopr_accPredioRegistro;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de aprobacion.
	 *
	 * @param aa_a asigna el valor a la propiedad aprobacion
	 */
	public void setAprobacion(Aprobacion aa_a)
	{
		aa_aprobacion = aa_a;
	}

	/**
	 * Retorna el valor de aprobacion.
	 *
	 * @return el valor de aprobacion
	 */
	public Aprobacion getAprobacion()
	{
		return aa_aprobacion;
	}

	/**
	 * Valida la propiedad definitiva.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en definitiva
	 */
	public boolean isDefinitiva()
	{
		return getTipoMatricula().equalsIgnoreCase(IdentificadoresCommon.DEFINITIVA);
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		if(!StringUtils.isValidString(is_idTurno))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idTurno");

			if(StringUtils.isValidString(tmp))
				is_idTurno = tmp;
		}

		return is_idTurno;
	}

	/**
	 * Modifica el valor de matricula definitiva.
	 *
	 * @param al_l asigna el valor a la propiedad matricula definitiva
	 */
	public void setMatriculaDefinitiva(long al_l)
	{
		is_matriculaDefinitiva = al_l;
	}

	/**
	 * Retorna el valor de matricula definitiva.
	 *
	 * @return el valor de matricula definitiva
	 */
	public long getMatriculaDefinitiva()
	{
		return is_matriculaDefinitiva;
	}

	/**
	 * Modifica el valor de Pantalla Anterior.
	 *
	 * @param as_s asigna el valor a la propiedad PantallaAnterior
	 */
	public void setPantallaAnterior(String as_s)
	{
		is_pantallaAnterior = as_s;
	}

	/**
	 * Retorna el valor de Pantalla Anterior.
	 *
	 * @return el valor de Pantalla Anterior
	 */
	public String getPantallaAnterior()
	{
		return is_pantallaAnterior;
	}

	/**
	 * Valida la propiedad temporal.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en temporal
	 */
	public boolean isTemporal()
	{
		return getTipoMatricula().equalsIgnoreCase(IdentificadoresCommon.TEMPORAL);
	}

	/**
	 * Modifica el valor de tipo matricula.
	 *
	 * @param as_s asigna el valor a la propiedad tipo matricula
	 */
	public void setTipoMatricula(String as_s)
	{
		is_tipoMatricula = as_s;
	}

	/**
	 * Retorna el valor de tipo matricula.
	 *
	 * @return el valor de tipo matricula
	 */
	public String getTipoMatricula()
	{
		return StringUtils.getStringNotNull(is_tipoMatricula);
	}

	/**
	 * Modifica el valor de viene de trazabilidad.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de trazabilidad
	 */
	public void setVieneDeTrazabilidad(boolean ab_b)
	{
		ib_vieneDeTrazabilidad = ab_b;
	}

	/**
	 * Valida la propiedad viene de trazabilidad.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de trazabilidad
	 */
	public boolean isVieneDeTrazabilidad()
	{
		return ib_vieneDeTrazabilidad;
	}

	/**
	 * Regresa al detalle del turno en tramite.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolver()
	{
		String ls_navegacion;

		ls_navegacion = null;

		try
		{
			FacesContext lfc_context;
			Aprobacion   la_aprobacion;
			boolean      lb_traza;

			lfc_context       = FacesContext.getCurrentInstance();
			la_aprobacion     = getAprobacion();
			lb_traza          = isVieneDeTrazabilidad();

			if(la_aprobacion != null)
			{
				boolean lb_antiguoSistema;

				lb_antiguoSistema = la_aprobacion.isVieneDeAntiguoSistema();

				if(lb_traza)
					ls_navegacion = NavegacionCommon.DETALLE_TRAZABILIDAD;

				else if(lb_antiguoSistema || la_aprobacion.isVieneDeAprobacion())
				{
					BeanAprobacion lb_beanAprobacion;

					lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
							                                           .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
							);

					if(lb_beanAprobacion != null)
					{
						lb_beanAprobacion.clean();

						if(lb_antiguoSistema)
						{
							lb_beanAprobacion.setVieneDeAntiguoSistema(true);

							if(la_aprobacion.isVieneDeAutorizacionFirma())
								lb_beanAprobacion.setVieneDeAutorizacionFirma(true);
						}

						lb_beanAprobacion.abrirDetalleTurno(la_aprobacion);
					}
				}

				if(!lb_traza)
					ls_navegacion = NavegacionCommon.DETALLE_ACTO;
			}
			else
			{
				BeanRecepcionDocumentos lbsdt_beanRecepcionDocumentos;

				lbsdt_beanRecepcionDocumentos = (BeanRecepcionDocumentos)lfc_context.getApplication()
						                                                                .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS, BeanRecepcionDocumentos.class
						);

				if(lbsdt_beanRecepcionDocumentos != null)
				{
					String ls_proceso;

					lbsdt_beanRecepcionDocumentos.setModificar(false);

					ls_proceso = lbsdt_beanRecepcionDocumentos.getProceso();

					if(StringUtils.isValidString(ls_proceso))
						ls_navegacion = NavegacionCommon.RECEPCION_DOCUMENTOS;
				}
			}

			if(!lb_traza)
			{
				String ls_pantallaAnterior;

				ls_pantallaAnterior = getPantallaAnterior();

				if(StringUtils.isValidString(ls_pantallaAnterior))
					ls_navegacion = ls_pantallaAnterior;

				limpiarVariables();
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
		catch(Exception le_e)
		{
			addMessage(new B2BException(le_e.getMessage()));
		}

		return ls_navegacion;
	}

	/**
	 * Metodo encargado de dirigir la redirreción y cargar la informacion de la pantalla a la cual se redireccionará.
	 *
	 * @param as_tipoMatricula <code>String</code> que contiene el tipo de matricula
	 * @return <code>String</code> con la URL a la que se redireccionará
	 */
	public String cargarTabsDetalle(String as_tipoMatricula)
	{
		String ls_return;

		ls_return = NavegacionCommon.DETALLE_PREDIO;

		try
		{
			if(StringUtils.isValidString(as_tipoMatricula))
			{
				ConsultaPredio lcp_cp;
				Long           ll_matricula;
				PredioRegistro lopr_apr;
				String         ls_circulo;
				String         ls_idTurno;
				boolean        lb_trazabilidad;
				String         ls_pantallaAnterior;

				ll_matricula            = getIdMatricula();
				lopr_apr                = new PredioRegistro();
				ls_circulo              = getIdCirculo();
				ls_idTurno              = getIdTurno();
				lb_trazabilidad         = isVieneDeTrazabilidad();
				ls_pantallaAnterior     = getPantallaAnterior();

				if(!lb_trazabilidad)
					clean();

				setCirculoLink(ls_circulo);
				setMatriculaLink(ll_matricula);
				setPantallaAnterior(ls_pantallaAnterior);

				lopr_apr.setIdCirculo(ls_circulo);
				lopr_apr.setIdMatricula(NumericUtils.getLong(ll_matricula));
				lopr_apr.setTurnoBloqueo(ls_idTurno);
				lopr_apr.setIdTipoPredio(as_tipoMatricula);

				if(NumericUtils.isValidLong(ll_matricula))
				{
					if(StringUtils.isValidString(ls_circulo))
					{
						PredioSegregado lps_predioSegregado;

						lps_predioSegregado = new PredioSegregado();

						lps_predioSegregado.setIdMatricula1(NumericUtils.getLong(ll_matricula));
						lps_predioSegregado.setIdCirculo1(ls_circulo);

						lps_predioSegregado = irr_referenceRemote.findMatriculaDefinitiva(lps_predioSegregado);

						if(lps_predioSegregado != null)
						{
							long ll_matriculaFinal;

							ll_matriculaFinal = lps_predioSegregado.getIdMatricula();

							setMatriculaDefinitiva(ll_matriculaFinal);
						}
					}
				}

				lcp_cp = icepe_consultaPredioRemote.findInfoTabs(lopr_apr, getUserId(), false, lb_trazabilidad);

				if(lcp_cp != null)
				{
					setTotalAnotaciones(lcp_cp.getTotalAnotaciones());
					setPredioRegistro(lcp_cp.getPredioRegistro());

					{
						DatosBasicos ldb_datosBasicos;

						ldb_datosBasicos = lcp_cp.getDatosbasicos();

						if(ldb_datosBasicos != null)
						{
							AccPredioRegistro lapr_predioRegistro;
							PredioRegistro    lpr_predioRegistro;

							lpr_predioRegistro      = ldb_datosBasicos.getPredioRegistro();
							lapr_predioRegistro     = ldb_datosBasicos.getAccPredioRegistro();

							if((lpr_predioRegistro != null) && (lapr_predioRegistro != null))
							{
								String ls_idTipoUsoSuelo;
								String ls_idTipoPredio;

								ls_idTipoUsoSuelo     = lpr_predioRegistro.getIdTipoUsoSuelo();
								ls_idTipoPredio       = lpr_predioRegistro.getIdTipoPredio();

								if(!StringUtils.isValidString(ls_idTipoUsoSuelo))
									ls_idTipoUsoSuelo = lapr_predioRegistro.getIdTipoUsoSuelo();

								if(!StringUtils.isValidString(ls_idTipoPredio))
									ls_idTipoPredio = lapr_predioRegistro.getIdTipoPredio();

								lpr_predioRegistro.setIdTipoUsoSuelo(ls_idTipoUsoSuelo);
								lpr_predioRegistro.setIdTipoPredio(ls_idTipoPredio);
							}

							ldb_datosBasicos.setPredioRegistro(lpr_predioRegistro);
						}

						setDatosBasicos(ldb_datosBasicos);
					}

					setAntiguoSistemaData(lcp_cp.getAntiguoSistemaData());

					//obtenerMapaGeografico();
					if(isRetornoBandejas())
					{
						ConsultaPredio locp_datos;

						lcp_cp.setIdCirculo(ls_circulo);
						lcp_cp.setIdMatricula(ll_matricula);
						locp_datos = as_tipoMatricula.equalsIgnoreCase(IdentificadoresCommon.DEFINITIVA)
							? icepe_consultaPredioRemote.consultar(lcp_cp)
							: icepe_consultaPredioRemote.consultarAcc(lcp_cp);

						if(locp_datos != null)
						{
							setEstadoPredio(StringUtils.getStringNotNull(locp_datos.getEstadoPredio()));
							setIdCirculo(ls_circulo);
							setIdMatricula(ll_matricula);
						}
					}

					{
						CabidaLinderos        lcl_cl;
						ComplementacionPredio lacp_cp;
						LinderoPredio         lalp_lp;

						lcl_cl      = getCabidaLinderos();
						lacp_cp     = lcp_cp.getComplementacionPredio();
						lalp_lp     = lcp_cp.getLinderoPredio();

						if(lacp_cp != null)
						{
							Long ll_complementacionOriginal;

							ll_complementacionOriginal = NumericUtils.getLongWrapper(lacp_cp.getIdComplementacion());

							if(NumericUtils.isValidLong(ll_complementacionOriginal))
							{
								lcl_cl.setTipoComplementacion(TipoComplementacionCommon.COPIAR);
								lcl_cl.getComplementacion().setComplementacion(lacp_cp.getComplementacion());
								lcl_cl.getComplementacionPredio()
									      .setIdComplementacion(
									    String.valueOf(NumericUtils.getLong(lacp_cp.getIdComplementacion()))
									);
							}
							else
								lcl_cl.setTipoComplementacion(TipoComplementacionCommon.NUEVA);
						}

						if(lalp_lp != null)
							lcl_cl.setLinderos(lalp_lp.getLindero());
					}

					setAreaUI(lcp_cp.getAreaUI());
					setAreaPredio(lcp_cp.getAreaPredio());
					setDireccionesPredio(lcp_cp.getDireccionesDelPredio());
					setAnotacion(lcp_cp.getAnotacion());
					setDireccionesTemporales(getDireccionesPredio());
					setPrediosSegregados(lcp_cp.getPrediosSegregados());
					setAlertaNaturalezaJuridica(lcp_cp.getInfoAlertas());
					getDocumentoCriterios().setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					setSalvedades(lcp_cp.getSalvedades());
					setTipoMatricula(as_tipoMatricula);

					consultarAnotaciones(lcp_cp, 1);
					setRenderedBotonVolver(false);

					{
						Apertura la_apertura;
						la_apertura = getDatosBasicos().getApertura();

						if(la_apertura != null)
						{
							String ls_idPais;
							ls_idPais = la_apertura.getIdPais();

							if(!StringUtils.isValidString(ls_idPais))
								la_apertura.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CARGUE_MATRICULA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_return = null;
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_return;
	}

	/** {@inheritdoc} */
	public void clean()
	{
		clean(false);
	}

	/** {@inheritdoc} */
	public void clean(boolean ab_noEliminarCirculoMatricula)
	{
		super.clean(ab_noEliminarCirculoMatricula);
		setTipoMatricula(null);
		setIdTurno(null);
		setPantallaAnterior(null);
	}

	/** {@inheritdoc} */
	public void consultarAnotaciones(ConsultaPredio acp_cp, int ai_tipoEjecucion)
	    throws B2BException
	{
		super.consultarAnotaciones(acp_cp, ai_tipoEjecucion);
	}

	/** {@inheritdoc} */
	public String consultarPorCriterios()
	{
		return super.consultarPorCriterios(
		    ":fConsultaEstadoPredio:TvdetallePredio:idAnotaciones",
		    ":fConsultaEstadoPredio:TvdetallePredio:Anotaciones_id"
		);
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;
		Documento             lcdd_consultaDatosDocumento;

		lcm_municipios                  = null;
		lcdd_consultaDatosDocumento     = getDocumentoCriterios();

		if(lcdd_consultaDatosDocumento != null)
		{
			String ls_pais;
			String ls_departamento;

			ls_pais             = lcdd_consultaDatosDocumento.getIdPais();
			ls_departamento     = lcdd_consultaDatosDocumento.getIdDepartamento();

			if(StringUtils.isValidString(ls_pais) && StringUtils.isValidString(ls_departamento))
			{
				try
				{
					Municipio lm_parametros;

					lm_parametros = new Municipio();

					lm_parametros.setIdPais(ls_pais);
					lm_parametros.setIdDepartamento(ls_departamento);

					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				}
				catch(B2BException lb2be_e)
				{
					addMessage(lb2be_e);
					lcm_municipios = null;
				}
			}
		}

		return lcm_municipios;
	}

	/**
	 * Limpiar variables.
	 */
	public void limpiarVariables()
	{
		clean();
		setAprobacion(null);
	}
}
