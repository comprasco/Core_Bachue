package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.b2bsg.common.util.BooleanUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.registro.DataReproduccionConstancia;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_CAMPOS_CONSULTA.
 *
 * @author  Julian Vaca
 * Fecha de Creacion: 20/02/2019
 */
public class CamposConsulta extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -882800787084092822L;

	/** Propiedad iccb criterios agregados. */
	private Collection<CriteriosDeBusqueda> iccb_criteriosAgregados;

	/** Propiedad iccc data campos consulta. */
	private Collection<CamposConsulta> iccc_dataCamposConsulta;

	/** Propiedad icd departamentos. */
	private Collection<Departamento> icd_departamentos;

	/**  Propiedad icdc data reproducción constancia. */
	private Collection<DataReproduccionConstancia> icdrc_dataReproduccionConstancia;

	/** Propiedad icm municipios. */
	private Collection<Municipio> icm_municipios;

	/** Propiedad icoo oficinas origen. */
	private Collection<OficinaOrigen> icoo_oficinasOrigen;

	/** Propiedad icto tipos oficina. */
	private Collection<TipoOficina> icto_tiposOficina;

	/** Propiedad icv veredas. */
	private Collection<Vereda> icv_veredas;

	/** Propiedad icado documentos OWCC. */
	private Collection<DocumentoOWCC> ido_documentosOWCC;

	/** Propiedad iccb criterios de busqueda. */
	private CriteriosDeBusqueda iccb_criteriosDeBusqueda;

	/** Propiedad id valor campo fecha. */
	private Date id_valorCampoFecha;

	/** Propiedad iado documento OWCC. */
	private DocumentoOWCC iado_documentoOWCC;

	/** Propiedad il id campo. */
	private Long il_idCampo;

	/** Propiedad il id consulta. */
	private Long il_idConsulta;

	/** Propiedad il longitud. */
	private Long il_longitud;

	/** Propiedad il orden. */
	private Long il_orden;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is campo tabla. */
	private String is_campoTabla;

	/** Propiedad is descriptivo. */
	private String is_descriptivo;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is etiqueta campo. */
	private String is_etiquetaCampo;

	/** Propiedad is id campo criterio busqueda. */
	private String is_idCampoCriterioBusqueda;

	/** Propiedad is id tipo criterio busqueda. */
	private String is_idTipoCriterioBusqueda;

	/** Propiedad is id Tipo Predio. */
	private String is_idTipoPredio;

	/** Propiedad is llave pk. */
	private String is_llavePk;

	/** Propiedad is mensaje respuesta cargue. */
	private String is_mensajeRespuestaCargue;

	/** Propiedad is metodo consulta. */
	private String is_metodoConsulta;

	/** Propiedad is nombre campo. */
	private String is_nombreCampo;

	/** Propiedad is nombre criterio servicio. */
	private String is_nombreCriterioServicio;

	/** Propiedad is nombre tabla. */
	private String is_nombreTabla;

	/** Propiedad is obligatorio. */
	private String is_obligatorio;

	/** Propiedad is parametrica. */
	private String is_parametrica;

	/** Propiedad is tipo campo. */
	private String is_tipoCampo;

	/** Propiedad is tipo dato. */
	private String is_tipoDato;

	/** Propiedad is valor campo. */
	private String is_valorCampo;

	/** Propiedad iba archivo cargue. */
	private byte[] iba_archivoCargue;

	/** Propiedad ib cargue correcto. */
	private boolean ib_cargueCorrecto;

	/** Propiedad ib mostrar boton agregar. */
	private boolean ib_mostrarBotonAgregar;

	/** Propiedad ib rojo pantalla. */
	private boolean ib_rojoPantalla;

	/** Propiedad ii tamano de tabla. */
	private int ii_tamanoDeTabla;

	/**
	 * Constructor por defecto.
	 */
	public CamposConsulta()
	{
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_a asigna el valor a la propiedad
	 */
	public void setActivo(String as_a)
	{
		is_activo = as_a;
	}

	/**
	 * Get activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Archivo cargue.
	 *
	 * @param aba_archivoCargue Objeto o Variable de tipo byte[] que asigna un valor a la propiedad ArchivoCargue
	 */
	public void setArchivoCargue(byte[] aba_archivoCargue)
	{
		iba_archivoCargue = aba_archivoCargue;
	}

	/**
	 * Get archivo cargue.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getArchivoCargue()
	{
		return iba_archivoCargue;
	}

	/**
	 * Modifica el valor de Campo tabla.
	 *
	 * @param campoTabla asigna el valor a la propiedad
	 */
	public void setCampoTabla(String campoTabla)
	{
		is_campoTabla = campoTabla;
	}

	/**
	 * Get campo tabla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCampoTabla()
	{
		return is_campoTabla;
	}

	/**
	 * Modifica el valor de Cargue correcto.
	 *
	 * @param ab_cargueCorrecto Objeto o Variable de tipo boolean que asigna un valor a la propiedad CargueCorrecto
	 */
	public void setCargueCorrecto(boolean ab_cargueCorrecto)
	{
		ib_cargueCorrecto = ab_cargueCorrecto;
	}

	/**
	 * Valida la propiedad cargue correcto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCargueCorrecto()
	{
		return ib_cargueCorrecto;
	}

	/**
	 * Modifica el valor de Criterios agregados.
	 *
	 * @param criteriosAgregados asigna el valor a la propiedad
	 */
	public void setCriteriosAgregados(Collection<CriteriosDeBusqueda> criteriosAgregados)
	{
		iccb_criteriosAgregados = criteriosAgregados;
	}

	/**
	 * Get criterios agregados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CriteriosDeBusqueda> getCriteriosAgregados()
	{
		return iccb_criteriosAgregados;
	}

	/**
	 * Modifica el valor de Criterios de busqueda.
	 *
	 * @param criteriosDeBusqueda asigna el valor a la propiedad
	 */
	public void setCriteriosDeBusqueda(CriteriosDeBusqueda criteriosDeBusqueda)
	{
		iccb_criteriosDeBusqueda = criteriosDeBusqueda;
	}

	/**
	 * Get criterios de busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public CriteriosDeBusqueda getCriteriosDeBusqueda()
	{
		return iccb_criteriosDeBusqueda;
	}

	/**
	 * Modifica el valor de Data campos consulta.
	 *
	 * @param dataCamposConsulta asigna el valor a la propiedad
	 */
	public void setDataCamposConsulta(Collection<CamposConsulta> dataCamposConsulta)
	{
		iccc_dataCamposConsulta = dataCamposConsulta;
	}

	/**
	 * Get data campos consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CamposConsulta> getDataCamposConsulta()
	{
		return iccc_dataCamposConsulta;
	}

	/**
	 * Modifica el valor de data reproducción constancia.
	 *
	 * @param acdrc_cdrc Objeto o Variable de tipo Collection que asigna un valor a la propiedad data reproducción constancia
	 */
	public void setDataReproduccionConstancia(Collection<DataReproduccionConstancia> acdrc_cdrc)
	{
		icdrc_dataReproduccionConstancia = acdrc_cdrc;
	}

	/**
	 * Get data reproducción constancia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<DataReproduccionConstancia> getDataReproduccionConstancia()
	{
		return icdrc_dataReproduccionConstancia;
	}

	/**
	 * Modifica el valor de Departamentos.
	 *
	 * @param departamentos asigna el valor a la propiedad
	 */
	public void setDepartamentos(Collection<Departamento> departamentos)
	{
		icd_departamentos = departamentos;
	}

	/**
	 * Get departamentos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Departamento> getDepartamentos()
	{
		return icd_departamentos;
	}

	/**
	 * Modifica el valor de Descriptivo.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad Descriptivo
	 */
	public void setDescriptivo(String as_s)
	{
		is_descriptivo = as_s;
	}

	/**
	 * Get descriptivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescriptivo()
	{
		return is_descriptivo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param ado_ado asigna el valor a la propiedad
	 */
	public void setDocumentoOWCC(DocumentoOWCC ado_ado)
	{
		iado_documentoOWCC = ado_ado;
	}

	/**
	 * Get DocumentoOWCC.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public DocumentoOWCC getDocumentoOWCC()
	{
		return iado_documentoOWCC;
	}

	/**
	 * Modifica el valor ido_documentosOWCC.
	 *
	 * @param icdo_cdo asigna el valor a la propiedad
	 */
	public void setDocumentosOWCC(Collection<DocumentoOWCC> icdo_cdo)
	{
		ido_documentosOWCC = icdo_cdo;
	}

	/**
	 * Get documentosOWCC.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<DocumentoOWCC> getDocumentosOWCC()
	{
		return ido_documentosOWCC;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param estado asigna el valor a la propiedad
	 */
	public void setEstado(String estado)
	{
		is_estado = estado;
	}

	/**
	 * Get estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de Etiqueta campo.
	 *
	 * @param etiquetaCampo asigna el valor a la propiedad
	 */
	public void setEtiquetaCampo(String etiquetaCampo)
	{
		is_etiquetaCampo = etiquetaCampo;
	}

	/**
	 * Get etiqueta campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEtiquetaCampo()
	{
		return is_etiquetaCampo;
	}

	/**
	 * Modifica el valor de Id campo.
	 *
	 * @param idCampo asigna el valor a la propiedad
	 */
	public void setIdCampo(Long idCampo)
	{
		il_idCampo = idCampo;
	}

	/**
	 * Get id campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdCampo()
	{
		return il_idCampo;
	}

	/**
	 * Modifica el valor de Id campo criterio busqueda.
	 *
	 * @param idCampoCriterioBusqueda asigna el valor a la propiedad
	 */
	public void setIdCampoCriterioBusqueda(String idCampoCriterioBusqueda)
	{
		is_idCampoCriterioBusqueda = idCampoCriterioBusqueda;
	}

	/**
	 * Get id campo criterio busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCampoCriterioBusqueda()
	{
		return is_idCampoCriterioBusqueda;
	}

	/**
	 * Modifica el valor de Id consulta.
	 *
	 * @param idConsulta asigna el valor a la propiedad
	 */
	public void setIdConsulta(Long idConsulta)
	{
		il_idConsulta = idConsulta;
	}

	/**
	 * Get id consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdConsulta()
	{
		return il_idConsulta;
	}

	/**
	 * Modifica el valor de Id tipo criterio busqueda.
	 *
	 * @param idTipoCriterioBusqueda asigna el valor a la propiedad
	 */
	public void setIdTipoCriterioBusqueda(String idTipoCriterioBusqueda)
	{
		is_idTipoCriterioBusqueda = idTipoCriterioBusqueda;
	}

	/**
	 * Get id tipo criterio busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCriterioBusqueda()
	{
		return is_idTipoCriterioBusqueda;
	}

	/**
	 * Modifica el valor de IdTipoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoPredio(String as_s)
	{
		is_idTipoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPredio()
	{
		return is_idTipoPredio;
	}

	/**
	 * Modifica el valor de Llave pk.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlavePk(String as_s)
	{
		is_llavePk = as_s;
	}

	/**
	 * Get llave pk.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlavePk()
	{
		return is_llavePk;
	}

	/**
	 * Modifica el valor de Longitud.
	 *
	 * @param longitud asigna el valor a la propiedad
	 */
	public void setLongitud(Long longitud)
	{
		il_longitud = longitud;
	}

	/**
	 * Get longitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getLongitud()
	{
		return il_longitud;
	}

	/**
	 * Modifica el valor de Mensaje respuesta cargue.
	 *
	 * @param mensajeRespuestaCargue asigna el valor a la propiedad
	 */
	public void setMensajeRespuestaCargue(String mensajeRespuestaCargue)
	{
		is_mensajeRespuestaCargue = mensajeRespuestaCargue;
	}

	/**
	 * Get mensaje respuesta cargue.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMensajeRespuestaCargue()
	{
		return is_mensajeRespuestaCargue;
	}

	/**
	 * Modifica el valor de Metodo consulta.
	 *
	 * @param metodoConsulta asigna el valor a la propiedad
	 */
	public void setMetodoConsulta(String metodoConsulta)
	{
		is_metodoConsulta = metodoConsulta;
	}

	/**
	 * Get metodo consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMetodoConsulta()
	{
		return is_metodoConsulta;
	}

	/**
	 * Modifica el valor de Mostrar boton agregar.
	 *
	 * @param mostrarBotonAgregar asigna el valor a la propiedad
	 */
	public void setMostrarBotonAgregar(boolean mostrarBotonAgregar)
	{
		ib_mostrarBotonAgregar = mostrarBotonAgregar;
	}

	/**
	 * Valida la propiedad mostrar boton agregar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMostrarBotonAgregar()
	{
		return ib_mostrarBotonAgregar;
	}

	/**
	 * Modifica el valor de Municipios.
	 *
	 * @param municipios asigna el valor a la propiedad
	 */
	public void setMunicipios(Collection<Municipio> municipios)
	{
		icm_municipios = municipios;
	}

	/**
	 * Get municipios.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Municipio> getMunicipios()
	{
		return icm_municipios;
	}

	/**
	 * Modifica el valor de Nombre campo.
	 *
	 * @param nombreCampo asigna el valor a la propiedad
	 */
	public void setNombreCampo(String nombreCampo)
	{
		is_nombreCampo = nombreCampo;
	}

	/**
	 * Get nombre campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCampo()
	{
		return is_nombreCampo;
	}

	/**
	 * Modifica el valor de NombreCriterioServicio.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCriterioServicio(String as_s)
	{
		is_nombreCriterioServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre criterio servicio.
	 *
	 * @return Retorna el valor de la propiedad nombreCriterioServicio
	 */
	public String getNombreCriterioServicio()
	{
		return is_nombreCriterioServicio;
	}

	/**
	 * Modifica el valor de Nombre tabla.
	 *
	 * @param nombreTabla asigna el valor a la propiedad
	 */
	public void setNombreTabla(String nombreTabla)
	{
		is_nombreTabla = nombreTabla;
	}

	/**
	 * Get nombre tabla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTabla()
	{
		return is_nombreTabla;
	}

	/**
	 * Validar si es obligatoriedad.
	 *
	 * @return verdadero si es obligatorio de lo contarios retorna falso
	 */
	public boolean isObligatoriedad()
	{
		return BooleanUtils.getBooleanValue(getObligatorio());
	}

	/**
	 * Modifica el valor de Obligatorio.
	 *
	 * @param obligatorio asigna el valor a la propiedad
	 */
	public void setObligatorio(String obligatorio)
	{
		is_obligatorio = obligatorio;
	}

	/**
	 * Get obligatorio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObligatorio()
	{
		return is_obligatorio;
	}

	/**
	 * Modifica el valor de Oficinas origen.
	 *
	 * @param oficinasOrigen asigna el valor a la propiedad
	 */
	public void setOficinasOrigen(Collection<OficinaOrigen> oficinasOrigen)
	{
		icoo_oficinasOrigen = oficinasOrigen;
	}

	/**
	 * Get oficinas origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<OficinaOrigen> getOficinasOrigen()
	{
		return icoo_oficinasOrigen;
	}

	/**
	 * Modifica el valor de Orden.
	 *
	 * @param orden asigna el valor a la propiedad
	 */
	public void setOrden(Long orden)
	{
		il_orden = orden;
	}

	/**
	 * Get orden.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getOrden()
	{
		return il_orden;
	}

	/**
	 * Modifica el valor de Parametrica.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setParametrica(String as_s)
	{
		is_parametrica = as_s;
	}

	/**
	 * Get parametrica.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getParametrica()
	{
		return is_parametrica;
	}

	/**
	 * Modifica el valor de Rojo pantalla.
	 *
	 * @param rojoPantalla asigna el valor a la propiedad
	 */
	public void setRojoPantalla(boolean rojoPantalla)
	{
		ib_rojoPantalla = rojoPantalla;
	}

	/**
	 * Valida la propiedad rojo pantalla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isRojoPantalla()
	{
		return ib_rojoPantalla;
	}

	/**
	 * Modifica el valor de Tamano de tabla.
	 *
	 * @param tamanoDeTabla asigna el valor a la propiedad
	 */
	public void setTamanoDeTabla(int tamanoDeTabla)
	{
		ii_tamanoDeTabla = tamanoDeTabla;
	}

	/**
	 * Get tamano de tabla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getTamanoDeTabla()
	{
		return ii_tamanoDeTabla;
	}

	/**
	 * Modifica el valor de Tipo campo.
	 *
	 * @param tipoCampo asigna el valor a la propiedad
	 */
	public void setTipoCampo(String tipoCampo)
	{
		is_tipoCampo = tipoCampo;
	}

	/**
	 * Get tipo campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoCampo()
	{
		return is_tipoCampo;
	}

	/**
	 * Modifica el valor de Tipo dato.
	 *
	 * @param as_tipoDato asigna el valor a la propiedad
	 */
	public void setTipoDato(String as_tipoDato)
	{
		is_tipoDato = as_tipoDato;
	}

	/**
	 * Get tipo dato.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoDato()
	{
		return is_tipoDato;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_a asigna el valor a la propiedad
	 */
	public void setTiposOficina(Collection<TipoOficina> acto_cto)
	{
		icto_tiposOficina = acto_cto;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoOficina> getTiposOficina()
	{
		return icto_tiposOficina;
	}

	/**
	 * Modifica el valor de Valor campo.
	 *
	 * @param valorCampo asigna el valor a la propiedad
	 */
	public void setValorCampo(String valorCampo)
	{
		is_valorCampo = valorCampo;
	}

	/**
	 * Get valor campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getValorCampo()
	{
		return is_valorCampo;
	}

	/**
	 * Modifica el valor de Valor campo fecha.
	 *
	 * @param valorCampoFecha asigna el valor a la propiedad
	 */
	public void setValorCampoFecha(Date valorCampoFecha)
	{
		id_valorCampoFecha = valorCampoFecha;
	}

	/**
	 * Get valor campo fecha.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getValorCampoFecha()
	{
		return id_valorCampoFecha;
	}

	/**
	 * Modifica el valor de Veredas.
	 *
	 * @param veredas asigna el valor a la propiedad
	 */
	public void setVeredas(Collection<Vereda> veredas)
	{
		icv_veredas = veredas;
	}

	/**
	 * Get veredas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Vereda> getVeredas()
	{
		return icv_veredas;
	}
}
