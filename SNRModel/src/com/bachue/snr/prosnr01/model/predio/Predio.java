package com.bachue.snr.prosnr01.model.predio;

import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoEntradaConsultaCambioTercerOrden;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoEntradaConsultaSegregacionConCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoEntradaConsultaSegregacionSinCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoEntradaConsultarCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrden;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial;
import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadas;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoEntradaRegistrarIdentificadoresCatastralesListaPrediosPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredio;

import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoEntradaConsultarHistoricoPropietarios;
import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacion;
import co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDF;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio;
import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoEntradaDatosMatriculasDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoEntradaDatosMatriculasMatriz;
import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoEntradaSalvedades;
import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoEntradaDatosTramites;

import com.b2bsg.common.util.NumericUtils;

import java.io.Serializable;

import java.util.Calendar;



/**
 * Clase que contiene todos las propiedades Predio.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 9/03/2020
 */
public class Predio implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8778208774408086431L;

	/** Propiedad is_numeroIdentificacionCatastral. */
	protected String is_numeroIdentificacionPredio;

	/** Propiedad is_tipoIdentificacionPredio. */
	protected String is_tipoIdentificacionPredio;

	/** Propiedad ic_fechaAnotacion. */
	private Calendar ic_fechaAnotacion;

	/** Propiedad ic fecha creacion nir. */
	private Calendar ic_fechaCreacionNir;

	/** Propiedad ic fecha creacion turno. */
	private Calendar ic_fechaCreacionTurno;

	/** Propiedad fecha final. */
	private Calendar ic_fechaFinal;

	/** Propiedad ic fecha inicial. */
	private Calendar ic_fechaInicial;

	/** The ic fecha salvedad. */
	private Calendar ic_fechaSalvedad;

	/** Propiedad il_idMatricula. */
	private Long il_idMatricula;

	/** Propiedad is clase anotacion. */
	private String is_claseAnotacion;

	/** Propiedad is_codCirculoRegistral. */
	private String is_codCirculoRegistral;

	/** Propiedad is_codDepartamento. */
	private String is_codDepartamento;

	/** Propiedad is_codMunicipio. */
	private String is_codMunicipio;

	/** Propiedad is_codNaturalezaJuridica. */
	private String is_codNaturalezaJuridica;

	/** Propiedad is_comentario. */
	private String is_comentario;

	/** Propiedad is comentario salvedad. */
	private String is_comentarioSalvedad;

	/** Propiedad is convenio. */
	private String is_convenio;

	/** Propiedad is criterio busqueda. */
	private String is_criterioBusqueda;

	/** Propiedad is descripcion salvedad. */
	private String is_descripcionSalvedad;

	/** Propiedad is direccion predio. */
	private String is_direccionPredio;

	/** Propiedad is_idCirculo. */
	private String is_idCirculo;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id vereda. */
	private String is_idVereda;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is_tipoConstruccion. */
	private String is_tipoConstruccion;

	/** Propiedad is tipo PDF. */
	private String is_tipoPDF;

	/** Propiedad is valor criterio busqueda. */
	private String is_valorCriterioBusqueda;

	/** Propiedad ib_folioMatricula. */
	private boolean ib_folioMatricula;

	/** Propiedad ib numero predial. */
	private boolean ib_numeroPredial;

	/** Propiedad ib numero predial anterior. */
	private boolean ib_numeroPredialAnterior;

	/** Propiedad ib_nupre. */
	private boolean ib_nupre;

	/**
	 * Constructor vacio de la clase.
	 */
	public Predio()
	{
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param ab_folio de ab folio
	 */
	public Predio(String as_idCirculo, long al_idMatricula, boolean ab_folio)
	{
		is_idCirculo          = as_idCirculo;
		il_idMatricula        = NumericUtils.getLongWrapper(al_idMatricula);
		ib_folioMatricula     = ab_folio;
	}

	/**
	 * Constructor a partir de un objeto TipoEntradaConsultaCambioTercerOrden.
	 *
	 * @param ateccto_param Objeto contenedor de los datos para inicializar las variables de instancia de la clase
	 */
	public Predio(TipoEntradaConsultaCambioTercerOrden ateccto_param)
	{
		if(ateccto_param != null)
		{
			is_numeroIdentificacionPredio     = ateccto_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = ateccto_param.getTipoIdentificacionPredio().getValue();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param ateccp_param de ateccp param
	 */
	public Predio(TipoEntradaConsultarCambioPropietario ateccp_param)
	{
		if(ateccp_param != null)
		{
			is_numeroIdentificacionPredio     = ateccp_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = ateccp_param.getTipoIdentificacionPredio().getValue();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atecsscp_param de atecsscp param
	 */
	public Predio(TipoEntradaConsultaSegregacionSinCambioPropietario atecsscp_param)
	{
		if(atecsscp_param != null)
		{
			is_numeroIdentificacionPredio     = atecsscp_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atecsscp_param.getTipoIdentificacionPredio().getValue();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atecsccp_param de atecsccp param
	 */
	public Predio(TipoEntradaConsultaSegregacionConCambioPropietario atecsccp_param)
	{
		if(atecsccp_param != null)
		{
			is_numeroIdentificacionPredio     = atecsccp_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atecsccp_param.getTipoIdentificacionPredio().getValue();
		}
	}

	/**
	 * Constructor a partir de un objeto TipoEntradaConsultarRRRMatriculas.
	 *
	 * @param atecrrrm_param de atecrrrm param
	 */
	public Predio(TipoEntradaConsultarRRRMatriculas atecrrrm_param)
	{
		if(atecrrrm_param != null)
		{
			is_numeroIdentificacionPredio     = atecrrrm_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atecrrrm_param.getTipoIdentificacionPredio().getValue();
		}
	}

	/**
	 * Constructor a partir de un objeto atercco_param.
	 *
	 * @param atercco_param Objeto contenedor de los datos para inicializar las variables de instancia de la clase
	 */
	public Predio(TipoEntradaRegistrarCambioCuartoOrden atercco_param)
	{
		if(atercco_param != null)
		{
			is_numeroIdentificacionPredio     = atercco_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atercco_param.getTipoIdentificacionPredio().getValue();
		}
	}

	/**
	 * Instancia un nuevo objeto catastro.
	 *
	 * @param atecnmae_param Objeto contenedor de los datos para inicializar las variables de instancia de la clase
	 */
	public Predio(TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial atecnmae_param)
	{
		if(atecnmae_param != null)
		{
			is_idCirculo           = atecnmae_param.getCodCirculoRegistral();
			is_codDepartamento     = atecnmae_param.getCodDepartamento();
			is_codMunicipio        = atecnmae_param.getCodMunicipio();
			ic_fechaInicial        = atecnmae_param.getFechaInicial();
			ic_fechaFinal          = atecnmae_param.getFechaFinal();
		}
	}

	/**
	 * Constructor a partir de un objeto atercqo_param.
	 *
	 * @param atercqo_param de atercqo param
	 */
	public Predio(TipoEntradaRegistrarCambioQuintoOrden atercqo_param)
	{
		if(atercqo_param != null)
		{
			is_numeroIdentificacionPredio     = atercqo_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atercqo_param.getTipoIdentificacionPredio().getValue();
		}
	}

	/**
	 * Instancia un nuevo objeto catastro.
	 *
	 * @param atecpi_param de atecpi param
	 */
	public Predio(TipoEntradaConsultarPartesInteresadas atecpi_param)
	{
		if(atecpi_param != null)
		{
			is_numeroIdentificacionPredio     = atecpi_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atecpi_param.getTipoIdentificacionPredio().getValue();
		}
	}

	/**
	 * Constructor a partir de un objeto atercslpp_temp.
	 *
	 * @param atercslpp_temp de atercslpp temp
	 */
	public Predio(TipoEntradaregistrarCambioSalvedadesListaPrediosPredio atercslpp_temp)
	{
		if(atercslpp_temp != null)
		{
			is_idCirculo                      = atercslpp_temp.getCodCirculoRegistral();
			is_numeroIdentificacionPredio     = atercslpp_temp.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atercslpp_temp.getTipoIdentificacionPredio().getValue();
			is_comentarioSalvedad             = atercslpp_temp.getComentarioSalvedad();
			is_descripcionSalvedad            = atercslpp_temp.getDescripcionSalvedad();
			is_direccionPredio                = atercslpp_temp.getDireccionPredio();
			ic_fechaSalvedad                  = atercslpp_temp.getFechaSalvedad();
			il_idMatricula                    = NumericUtils.getLongWrapper(
				    atercslpp_temp.getNumMatriculaInmobiliaria()
				);
		}
	}

	/**
	 *  Constructor a partir de un objeto atericlpp_temp.
	 *
	 * @param atericlpp_temp de atericlpp temp
	 */
	public Predio(TipoEntradaRegistrarIdentificadoresCatastralesListaPrediosPredio atericlpp_temp)
	{
		if(atericlpp_temp != null)
		{
			is_idCirculo                      = atericlpp_temp.getCodCirculoRegistral();
			is_numeroIdentificacionPredio     = atericlpp_temp.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atericlpp_temp.getTipoIdentificacionPredio().getValue();
			is_comentarioSalvedad             = atericlpp_temp.getComentarioSalvedad();
			is_descripcionSalvedad            = atericlpp_temp.getDescripcionSalvedad();
			ic_fechaSalvedad                  = atericlpp_temp.getFechaSalvedad();
			il_idMatricula                    = NumericUtils.getLongWrapper(
				    atericlpp_temp.getNumMatriculaInmobiliaria()
				);
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atedmm_param de atedmm param
	 */
	public Predio(TipoEntradaDatosMatriculasMatriz atedmm_param)
	{
		if(atedmm_param != null)
		{
			is_numeroIdentificacionPredio     = atedmm_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atedmm_param.getTipoIdentificacionPredio().getValue();
			is_convenio                       = atedmm_param.getConvenio();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atedmd_param de atedmd param
	 */
	public Predio(TipoEntradaDatosMatriculasDerivadas atedmd_param)
	{
		if(atedmd_param != null)
		{
			is_numeroIdentificacionPredio     = atedmd_param.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atedmd_param.getTipoIdentificacionPredio().getValue();
			is_convenio                       = atedmd_param.getConvenio();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atedt_entrada de atedt entrada
	 */
	public Predio(TipoEntradaDatosTramites atedt_entrada)
	{
		if(atedt_entrada != null)
		{
			is_numeroIdentificacionPredio     = atedt_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atedt_entrada.getTipoIdentificacionPredio().getValue();
			is_convenio                       = atedt_entrada.getConvenio();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atecmp_entrada de atecmp entrada
	 */
	public Predio(TipoEntradaConsultaMigracionPredio atecmp_entrada)
	{
		if(atecmp_entrada != null)
		{
			is_numeroIdentificacionPredio     = atecmp_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atecmp_entrada.getTipoIdentificacionPredio().getValue();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param ates_entrada de ates entrada
	 */
	public Predio(TipoEntradaSalvedades ates_entrada)
	{
		if(ates_entrada != null)
		{
			is_numeroIdentificacionPredio     = ates_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = ates_entrada.getTipoIdentificacionPredio().getValue();
			is_convenio                       = ates_entrada.getConvenio();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atectpdf_entrada de atectpdf entrada
	 */
	public Predio(TipoEntradaCertificadoTradicionPDF atectpdf_entrada)
	{
		if(atectpdf_entrada != null)
		{
			is_numeroIdentificacionPredio     = atectpdf_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atectpdf_entrada.getTipoIdentificacionPredio().getValue();
			is_convenio                       = atectpdf_entrada.getConvenio();
			is_tipoPDF                        = atectpdf_entrada.getTipoPDF().getValue();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atedi_entrada de atedi entrada
	 */
	public Predio(TipoEntradaDatosInmueble atedi_entrada)
	{
		if(atedi_entrada != null)
		{
			is_numeroIdentificacionPredio     = atedi_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atedi_entrada.getTipoIdentificacionPredio().getValue();
			is_convenio                       = atedi_entrada.getConvenio();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atedp_entrada de atedp entrada
	 */
	public Predio(TipoEntradaDatosPropietario atedp_entrada)
	{
		if(atedp_entrada != null)
		{
			is_numeroIdentificacionPredio     = atedp_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atedp_entrada.getTipoIdentificacionPredio().getValue();
			is_convenio                       = atedp_entrada.getConvenio();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param ateda_entrada de ateda entrada
	 */
	public Predio(TipoEntradaDireccionesAnteriores ateda_entrada)
	{
		if(ateda_entrada != null)
		{
			is_numeroIdentificacionPredio     = ateda_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = ateda_entrada.getTipoIdentificacionPredio().getValue();
			is_convenio                       = ateda_entrada.getConvenio();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atecm_entrada de atecm entrada
	 */
	public Predio(TipoEntradaConsultaMatriculas atecm_entrada)
	{
		if(atecm_entrada != null)
		{
			is_convenio                  = atecm_entrada.getConvenio();
			is_codDepartamento           = atecm_entrada.getCodDepartamento();
			is_codMunicipio              = atecm_entrada.getCodMunicipio();
			is_criterioBusqueda          = atecm_entrada.getCriterioBusqueda().getValue();
			is_valorCriterioBusqueda     = atecm_entrada.getValorCriterioBusqueda();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atedbm_entrada de atedbm entrada
	 */
	public Predio(TipoEntradaDatosBasicosMatriculas atedbm_entrada)
	{
		if(atedbm_entrada != null)
		{
			is_convenio                  = atedbm_entrada.getConvenio();
			is_codDepartamento           = atedbm_entrada.getCodDepartamento();
			is_codMunicipio              = atedbm_entrada.getCodMunicipio();
			is_criterioBusqueda          = atedbm_entrada.getCriterioBusqueda().getValue();
			is_valorCriterioBusqueda     = atedbm_entrada.getValorCriterioBusqueda();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param ateip_entrada de ateip entrada
	 */
	public Predio(TipoEntradaIndicePropietarios ateip_entrada)
	{
		if(ateip_entrada != null)
		{
			is_numeroIdentificacionPredio     = ateip_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = (ateip_entrada.getTipoIdentificacionPredio() != null)
				? ateip_entrada.getTipoIdentificacionPredio().getValue() : null;
			is_convenio                       = ateip_entrada.getConvenio();
			is_codDepartamento                = ateip_entrada.getCodDepartamento();
			is_codMunicipio                   = ateip_entrada.getCodMunicipio();
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param ateda_entrada de ateda entrada
	 */
	public Predio(TipoEntradaDatosAnotacion ateda_entrada)
	{
		if(ateda_entrada != null)
		{
			is_numeroIdentificacionPredio     = ateda_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = ateda_entrada.getTipoIdentificacionPredio().getValue();
			is_convenio                       = ateda_entrada.getConvenio();

			{
				Object lo_object;

				lo_object = ateda_entrada.getClaseAnotacion();

				if(lo_object != null)
					is_claseAnotacion = lo_object.toString();
			}
		}
	}

	/**
	 * Instancia un nuevo objeto predio.
	 *
	 * @param atechp_entrada de atechp entrada
	 */
	public Predio(TipoEntradaConsultarHistoricoPropietarios atechp_entrada)
	{
		if(atechp_entrada != null)
		{
			is_numeroIdentificacionPredio     = atechp_entrada.getNumIdentificacionPredio();
			is_tipoIdentificacionPredio       = atechp_entrada.getTipoIdentificacionPredio().getValue();
			is_convenio                       = atechp_entrada.getConvenio();
		}
	}

	/**
	 * Modifica el valor de ClaseAnotacion.
	 *
	 * @param as_s de as s
	 */
	public void setClaseAnotacion(String as_s)
	{
		is_claseAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor clase anotacion.
	 *
	 * @return Retorna el valor de la propiedad claseAnotacion
	 */
	public String getClaseAnotacion()
	{
		return is_claseAnotacion;
	}

	/**
	 * Modifica el valor de is_codCirculoRegistral.
	 *
	 * @param as_s asigna el valor a la propiedad is_codCirculoRegistral
	 */
	public void setCodCirculoRegistral(String as_s)
	{
		is_codCirculoRegistral = as_s;
	}

	/**
	 * Retorna el valor de is_codCirculoRegistral.
	 *
	 * @return el valor de is_codCirculoRegistral
	 */
	public String getCodCirculoRegistral()
	{
		return is_codCirculoRegistral;
	}

	/**
	 * Modifica el valor de is_codDepartamento.
	 *
	 * @param as_s asigna el valor a la propiedad is_codDepartamento
	 */
	public void setCodDepartamento(String as_s)
	{
		is_codDepartamento = as_s;
	}

	/**
	 * Retorna el valor de is_codDepartamento.
	 *
	 * @return el valor de is_codDepartamento
	 */
	public String getCodDepartamento()
	{
		return is_codDepartamento;
	}

	/**
	 * Modifica el valor de is_codMunicipio.
	 *
	 * @param as_s asigna el valor a la propiedad is_codMunicipio
	 */
	public void setCodMunicipio(String as_s)
	{
		is_codMunicipio = as_s;
	}

	/**
	 * Retorna el valor de is_codMunicipio.
	 *
	 * @return el valor de is_codMunicipio
	 */
	public String getCodMunicipio()
	{
		return is_codMunicipio;
	}

	/**
	 * Modifica el valor de is_codNaturalezaJuridica.
	 *
	 * @param as_s asigna el valor a la propiedad is_codNaturalezaJuridica
	 */
	public void setCodNaturalezaJuridica(String as_s)
	{
		is_codNaturalezaJuridica = as_s;
	}

	/**
	 * Retorna el valor de is_codNaturalezaJuridica.
	 *
	 * @return el valor de is_codNaturalezaJuridica
	 */
	public String getCodNaturalezaJuridica()
	{
		return is_codNaturalezaJuridica;
	}

	/**
	 * Modifica el valor de is_comentario.
	 *
	 * @param as_s asigna el valor a la propiedad is_comentario
	 */
	public void setComentario(String as_s)
	{
		is_comentario = as_s;
	}

	/**
	 * Retorna el valor de is_comentario.
	 *
	 * @return el valor de is_comentario
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de ComentarioSalvedad.
	 *
	 * @param as_s de as s
	 */
	public void setComentarioSalvedad(String as_s)
	{
		is_comentarioSalvedad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor comentario salvedad.
	 *
	 * @return Retorna el valor de la propiedad comentarioSalvedad
	 */
	public String getComentarioSalvedad()
	{
		return is_comentarioSalvedad;
	}

	/**
	 * Modifica el valor de Convenio.
	 *
	 * @param as_s de as s
	 */
	public void setConvenio(String as_s)
	{
		is_convenio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor convenio.
	 *
	 * @return Retorna el valor de la propiedad convenio
	 */
	public String getConvenio()
	{
		return is_convenio;
	}

	/**
	 * Modifica el valor de CriterioBusqueda.
	 *
	 * @param criterioBusqueda de criterio busqueda
	 */
	public void setCriterioBusqueda(String criterioBusqueda)
	{
		is_criterioBusqueda = criterioBusqueda;
	}

	/**
	 * Retorna Objeto o variable de valor criterio busqueda.
	 *
	 * @return Retorna el valor de la propiedad criterioBusqueda
	 */
	public String getCriterioBusqueda()
	{
		return is_criterioBusqueda;
	}

	/**
	 * Modifica el valor de DescripcionSalvedad.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionSalvedad(String as_s)
	{
		is_descripcionSalvedad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion salvedad.
	 *
	 * @return Retorna el valor de la propiedad descripcionSalvedad
	 */
	public String getDescripcionSalvedad()
	{
		return is_descripcionSalvedad;
	}

	/**
	 * Modifica el valor de DireccionPredio.
	 *
	 * @param as_s de as s
	 */
	public void setDireccionPredio(String as_s)
	{
		is_direccionPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion predio.
	 *
	 * @return Retorna el valor de la propiedad direccionPredio
	 */
	public String getDireccionPredio()
	{
		return is_direccionPredio;
	}

	/**
	 * Modifica el valor de ic_fechaAnotacion.
	 *
	 * @param ac_c de ac c
	 */
	public void setFechaAnotacion(Calendar ac_c)
	{
		ic_fechaAnotacion = ac_c;
	}

	/**
	 * Retorna el valor de ic_fechaAnotacion.
	 *
	 * @return el valor de ic_fechaAnotacion
	 */
	public Calendar getFechaAnotacion()
	{
		return ic_fechaAnotacion;
	}

	/**
	 * Modifica el valor de FechaCreacionNir.
	 *
	 * @param ac_c de ac c
	 */
	public void setFechaCreacionNir(Calendar ac_c)
	{
		ic_fechaCreacionNir = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor fecha creacion nir.
	 *
	 * @return Retorna el valor de la propiedad fechaCreacionNir
	 */
	public Calendar getFechaCreacionNir()
	{
		return ic_fechaCreacionNir;
	}

	/**
	 * Modifica el valor de FechaCreacionTurno.
	 *
	 * @param ac_c de ac c
	 */
	public void setFechaCreacionTurno(Calendar ac_c)
	{
		ic_fechaCreacionTurno = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor fecha creacion turno.
	 *
	 * @return Retorna el valor de la propiedad fechaCreacionTurno
	 */
	public Calendar getFechaCreacionTurno()
	{
		return ic_fechaCreacionTurno;
	}

	/**
	 * Modifica el valor de FechaFinal.
	 *
	 * @param ac_c de ac c
	 */
	public void setFechaFinal(Calendar ac_c)
	{
		ic_fechaFinal = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor fecha final.
	 *
	 * @return el valor de fecha final
	 */
	public Calendar getFechaFinal()
	{
		return ic_fechaFinal;
	}

	/**
	 * Modifica el valor de FechaInicial.
	 *
	 * @param ac_c de ac c
	 */
	public void setFechaInicial(Calendar ac_c)
	{
		ic_fechaInicial = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inicial.
	 *
	 * @return el valor de fecha inicial
	 */
	public Calendar getFechaInicial()
	{
		return ic_fechaInicial;
	}

	/**
	 * Modifica el valor de FechaSalvedad.
	 *
	 * @param ac_c de ac c
	 */
	public void setFechaSalvedad(Calendar ac_c)
	{
		ic_fechaSalvedad = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor fecha salvedad.
	 *
	 * @return Retorna el valor de la propiedad fechaSalvedad
	 */
	public Calendar getFechaSalvedad()
	{
		return ic_fechaSalvedad;
	}

	/**
	 * Modifica el valor de ib_folioMatricula.
	 *
	 * @param ab_b asigna el valor a la propiedad ib_folioMatricula
	 */
	public void setFolioMatricula(boolean ab_b)
	{
		ib_folioMatricula = ab_b;
	}

	/**
	 * Retorna el valor de ib_folioMatricula.
	 *
	 * @return el valor de ib_folioMatricula
	 */
	public boolean isFolioMatricula()
	{
		return ib_folioMatricula;
	}

	/**
	 * Modifica el valor de is_idCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad is_idCirculo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de is_idCirculo.
	 *
	 * @return el valor de is_idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de il_idMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad il_idMatricula
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna el valor de il_idMatricula.
	 *
	 * @return el valor de il_idMatricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param as_s de as s
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return Retorna el valor de la propiedad idPais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdVereda.
	 *
	 * @param as_s de as s
	 */
	public void setIdVereda(String as_s)
	{
		is_idVereda = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id vereda.
	 *
	 * @return Retorna el valor de la propiedad idVereda
	 */
	public String getIdVereda()
	{
		return is_idVereda;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s de as s
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return Retorna el valor de la propiedad nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de is_numeroIdentificacionCatastral.
	 *
	 * @param as_s asigna el valor a la propiedad is_numeroIdentificacionCatastral
	 */
	public void setNumeroIdentificacionPredio(String as_s)
	{
		is_numeroIdentificacionPredio = as_s;
	}

	/**
	 * Retorna el valor de is_numeroIdentificacionCatastral.
	 *
	 * @return el valor de is_numeroIdentificacionCatastral
	 */
	public String getNumeroIdentificacionPredio()
	{
		return is_numeroIdentificacionPredio;
	}

	/**
	 * Modifica el valor de NumeroPredial.
	 *
	 * @param ab_b de ab b
	 */
	public void setNumeroPredial(boolean ab_b)
	{
		ib_numeroPredial = ab_b;
	}

	/**
	 * Valida la propiedad numero predial.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en numero predial
	 */
	public boolean isNumeroPredial()
	{
		return ib_numeroPredial;
	}

	/**
	 * Modifica el valor de NumeroPredialAnterior.
	 *
	 * @param ab_b de ab b
	 */
	public void setNumeroPredialAnterior(boolean ab_b)
	{
		ib_numeroPredialAnterior = ab_b;
	}

	/**
	 * Valida la propiedad numero predial anterior.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en numero predial anterior
	 */
	public boolean isNumeroPredialAnterior()
	{
		return ib_numeroPredialAnterior;
	}

	/**
	 * Modifica el valor de ib_nupre.
	 *
	 * @param ab_b asigna el valor a la propiedad ib_nupre
	 */
	public void setNupre(boolean ab_b)
	{
		ib_nupre = ab_b;
	}

	/**
	 * Retorna el valor de ib_nupre.
	 *
	 * @return el valor de ib_nupre
	 */
	public boolean isNupre()
	{
		return ib_nupre;
	}

	/**
	 * Modifica el valor de is_tipoConstruccion.
	 *
	 * @param as_s asigna el valor a la propiedad is_tipoConstruccion
	 */
	public void setTipoConstruccion(String as_s)
	{
		is_tipoConstruccion = as_s;
	}

	/**
	 * Retorna el valor de is_tipoConstruccion.
	 *
	 * @return el valor de is_tipoConstruccion
	 */
	public String getTipoConstruccion()
	{
		return is_tipoConstruccion;
	}

	/**
	 * Modifica el valor de is_tipoIdentificacionPredio.
	 *
	 * @param as_s asigna el valor a la propiedad is_tipoIdentificacionPredio
	 */
	public void setTipoIdentificacionPredio(String as_s)
	{
		is_tipoIdentificacionPredio = as_s;
	}

	/**
	 * Retorna el valor de is_tipoIdentificacionPredio.
	 *
	 * @return el valor de is_tipoIdentificacionPredio
	 */
	public String getTipoIdentificacionPredio()
	{
		return is_tipoIdentificacionPredio;
	}

	/**
	 * Modifica el valor de TipoPDF.
	 *
	 * @param as_s de as s
	 */
	public void setTipoPDF(String as_s)
	{
		is_tipoPDF = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo PDF.
	 *
	 * @return Retorna el valor de la propiedad tipoPDF
	 */
	public String getTipoPDF()
	{
		return is_tipoPDF;
	}

	/**
	 * Modifica el valor de ValorCriterioBusqueda.
	 *
	 * @param valorCriterioBusqueda de valor criterio busqueda
	 */
	public void setValorCriterioBusqueda(String valorCriterioBusqueda)
	{
		is_valorCriterioBusqueda = valorCriterioBusqueda;
	}

	/**
	 * Retorna Objeto o variable de valor valor criterio busqueda.
	 *
	 * @return Retorna el valor de la propiedad valorCriterioBusqueda
	 */
	public String getValorCriterioBusqueda()
	{
		return is_valorCriterioBusqueda;
	}
}
