package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de Documento.
 *
 * @author hcastaneda
 */
public class Documento extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2152518984373312325L;

	/** Propiedad iap anotacion predio. */
	private AnotacionPredio iap_anotacionPredio;

	/** Propiedad ibd version. */
	private BigDecimal ibd_version;

	/** Propiedad icd documentos asociados. */
	private Collection<Documento> icd_documentosAsociados;

	/** Propiedad icd documentos registrados. */
	private Collection<Documento> icd_documentosRegistrados;

	/** Propiedad icsm matriculas masivas documento. */
	private Collection<SolicitudMatricula> icsm_matriculasMasivasDocumento;

	/** Propiedad id fecha documento. */
	private Date id_fechaDocumento;

	/** Propiedad id fecha ejecutoria. */
	private Date id_fechaEjecutoria;

	/** Propiedad id departamento. */
	private Departamento id_departamento;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad il version documento. */
	private Long il_versionDocumento;

	/** Propiedad im municipio. */
	private Municipio im_municipio;

	/** Propiedad ioo oficina origen. */
	private OficinaOrigen ioo_oficinaOrigen;

	/** Propiedad ip pais. */
	private Pais ip_pais;

	/** Propiedad is anotaciones. */
	private String is_anotaciones;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is documento. */
	private String is_documento;

	/** Propiedad is entidad exenta. */
	private String is_entidadExenta;

	/** Propiedad is escritura. */
	private String is_escritura;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id oficina origen. */
	private String is_idOficinaOrigen;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id proceso recepcion. */
	private String is_idProcesoRecepcion;

	/** Propiedad is_idSubProcesoRecepcion. */
	private String is_idSubProcesoRecepcion;

	/** Propiedad is id tipo documento. */
	private String is_idTipoDocumento;

	/** Propiedad is id tipo entidad. */
	private String is_idTipoEntidad;

	/** Propiedad is id tipo oficina. */
	private String is_idTipoOficina;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno nir. */
	private String is_idTurnoNir;

	/** Propiedad is is estado. */
	private String is_is_estado;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nir asociados. */
	private String is_nirAsociados;

	/** Propiedad is nir turno. */
	private String is_nirTurno;

	/** Propiedad is nombre departamento. */
	private String is_nombreDepartamento;

	/** Propiedad is nombre municipio. */
	private String is_nombreMunicipio;

	/** Propiedad is nombre oficina. */
	private String is_nombreOficina;

	/** Propiedad is nombre oficina origen. */
	private String is_nombreOficinaOrigen;

	/** Propiedad is nombre tipo documento. */
	private String is_nombreTipoDocumento;

	/** Propiedad is numero. */
	private String is_numero;

	/** Propiedad is oficina internacional. */
	private String is_oficinaInternacional;

	/** Propiedad is tipo turno. */
	private String is_tipoTurno;

	/** Propiedad itdp tipo documento. */
	private TipoDocumentoPublico itdp_tipoDocumento;

	/** Propiedad ite tipo entidad documento. */
	private TipoEntidad ite_tipoEntidadDocumento;

	/** Propiedad ito tipo oficina. */
	private TipoOficina ito_tipoOficina;

	/** Propiedad ib accion. */
	private boolean ib_accion;

	/** Propiedad ib copiar documento. */
	private boolean ib_copiarDocumento;

	/** Propiedad ib copiar documento seleccionadas. */
	private boolean ib_copiarDocumentoSeleccionadas;

	/** Propiedad ib existencia documento ejecutoria. */
	private boolean ib_existenciaDocumentoEjecutoria;

	/** Propiedad ib fecha documento valid. */
	private boolean ib_fechaDocumentoValid;

	/** Propiedad ib id oficina origen valid. */
	private boolean ib_idOficinaOrigenValid;

	/** Propiedad ib tipo entidad valid. */
	private boolean ib_tipoEntidadValid;

	/** Propiedad ib tipo oficina valid. */
	private boolean ib_tipoOficinaValid;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Constructor por defecto.
	 */
	public Documento()
	{
		super();
	}

	/**
	 * Constructor que recibe como parametros el id del documento y la version.
	 *
	 * @param as_idDocumento id del documento
	 * @param abd_version version del documento
	 */
	public Documento(String as_idDocumento, Long abd_version)
	{
		super();
		setIdDocumento(as_idDocumento);
		setVersionDocumento(abd_version);
	}

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAccion(boolean ab_b)
	{
		ib_accion = ab_b;
	}

	/**
	 * Valida la propiedad accion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAccion()
	{
		return ib_accion;
	}

	/**
	 * Modifica el valor de AnotacionPredio.
	 *
	 * @param aa_a asigna el valor a la propiedad
	 */
	public void setAnotacionPredio(AnotacionPredio aa_a)
	{
		iap_anotacionPredio = aa_a;
	}

	/**
	 * Retorna Objeto o variable de valor anotacion predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public AnotacionPredio getAnotacionPredio()
	{
		return iap_anotacionPredio;
	}

	/**
	 * Modifica el valor de Anotaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnotaciones(String as_s)
	{
		is_anotaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnotaciones()
	{
		return is_anotaciones;
	}

	/**
	 * Modifica el valor de Comentario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComentario(String as_s)
	{
		is_comentario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor comentario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de CopiarDocumento.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCopiarDocumento(boolean ab_b)
	{
		ib_copiarDocumento = ab_b;
	}

	/**
	 * Valida la propiedad copiar documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCopiarDocumento()
	{
		return ib_copiarDocumento;
	}

	/**
	 * Modifica el valor de CopiarDocumentoSeleccionadas.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCopiarDocumentoSeleccionadas(boolean ab_b)
	{
		ib_copiarDocumentoSeleccionadas = ab_b;
	}

	/**
	 * Valida la propiedad copiar documento seleccionadas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCopiarDocumentoSeleccionadas()
	{
		return ib_copiarDocumentoSeleccionadas;
	}

	/**
	 * Modifica el valor de Departamento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setDepartamento(Departamento ad_d)
	{
		id_departamento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Departamento getDepartamento()
	{
		return (id_departamento == null) ? new Departamento() : id_departamento;
	}

	/**
	 * Modifica el valor de Documento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDocumento(String as_s)
	{
		is_documento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDocumento()
	{
		return is_documento;
	}

	/**
	 * Modifica el valor de DocumentosAsociados.
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setDocumentosAsociados(Collection<Documento> ac_c)
	{
		icd_documentosAsociados = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor documentos asociados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Documento> getDocumentosAsociados()
	{
		return icd_documentosAsociados;
	}

	/**
	 * Modifica el valor de DocumentosRegistrados.
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setDocumentosRegistrados(Collection<Documento> ac_c)
	{
		icd_documentosRegistrados = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor documentos registrados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Documento> getDocumentosRegistrados()
	{
		return icd_documentosRegistrados;
	}

	/**
	 * Modifica el valor de EntidadExenta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEntidadExenta(String as_s)
	{
		is_entidadExenta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor entidad exenta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEntidadExenta()
	{
		return is_entidadExenta;
	}

	/**
	 * Modifica el valor de Escritura.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEscritura(String as_s)
	{
		is_escritura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor escritura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEscritura()
	{
		return is_escritura;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		is_is_estado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_is_estado;
	}

	/**
	 * Modifica el valor de ExistenciaDocumentoEjecutoria.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setExistenciaDocumentoEjecutoria(boolean ab_b)
	{
		ib_existenciaDocumentoEjecutoria = ab_b;
	}

	/**
	 * Valida la propiedad existencia documento ejecutoria.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isExistenciaDocumentoEjecutoria()
	{
		return ib_existenciaDocumentoEjecutoria;
	}

	/**
	 * Modifica el valor de FechaDocumento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDocumento(Date ad_d)
	{
		id_fechaDocumento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * Modifica el valor de FechaDocumentoValid.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setFechaDocumentoValid(boolean ab_b)
	{
		ib_fechaDocumentoValid = ab_b;
	}

	/**
	 * Valida la propiedad fecha documento valid.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isFechaDocumentoValid()
	{
		return ib_fechaDocumentoValid;
	}

	/**
	 * Modifica el valor de FechaEjecutoria.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaEjecutoria(Date ad_d)
	{
		id_fechaEjecutoria = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha ejecutoria.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaEjecutoria()
	{
		return id_fechaEjecutoria;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de IdDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de IdOficinaOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdOficinaOrigen(String as_s)
	{
		is_idOficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id oficina origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdOficinaOrigen()
	{
		return is_idOficinaOrigen;
	}

	/**
	 * Modifica el valor de IdOficinaOrigenValid.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setIdOficinaOrigenValid(boolean ab_b)
	{
		ib_idOficinaOrigenValid = ab_b;
	}

	/**
	 * Valida la propiedad id oficina origen valid.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isIdOficinaOrigenValid()
	{
		return ib_idOficinaOrigenValid;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de IdProcesoRecepcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProcesoRecepcion(String as_s)
	{
		is_idProcesoRecepcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso recepcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProcesoRecepcion()
	{
		return is_idProcesoRecepcion;
	}

	/**
	 * Modifica el valor de IdSubProcesoRecepcion.
	 *
	 * @param as_s de as s
	 */
	public void setIdSubProcesoRecepcion(String as_s)
	{
		is_idSubProcesoRecepcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso recepcion.
	 *
	 * @return Retorna el valor de la propiedad idSubProcesoRecepcion
	 */
	public String getIdSubProcesoRecepcion()
	{
		return is_idSubProcesoRecepcion;
	}

	/**
	 * Modifica el valor de IdTipoDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDocumento(String as_s)
	{
		is_idTipoDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDocumento()
	{
		return is_idTipoDocumento;
	}

	/**
	 * Modifica el valor de IdTipoOficina.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoOficina(String as_s)
	{
		is_idTipoOficina = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo oficina.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoOficina()
	{
		return is_idTipoOficina;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoNir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoNir(String as_s)
	{
		is_idTurnoNir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoNir()
	{
		return is_idTurnoNir;
	}

	/**
	 * Modifica el valor de MatriculasMasivasDocumento.
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setMatriculasMasivasDocumento(Collection<SolicitudMatricula> ac_c)
	{
		icsm_matriculasMasivasDocumento = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas masivas documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<SolicitudMatricula> getMatriculasMasivasDocumento()
	{
		return icsm_matriculasMasivasDocumento;
	}

	/**
	 * Modifica el valor de Municipio.
	 *
	 * @param am_m asigna el valor a la propiedad
	 */
	public void setMunicipio(Municipio am_m)
	{
		im_municipio = am_m;
	}

	/**
	 * Retorna Objeto o variable de valor municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Municipio getMunicipio()
	{
		return (im_municipio == null) ? new Municipio() : im_municipio;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NirAsociados.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNirAsociados(String as_s)
	{
		is_nirAsociados = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir asociados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNirAsociados()
	{
		return is_nirAsociados;
	}

	/**
	 * Modifica el valor de NirTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNirTurno(String as_s)
	{
		is_nirTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNirTurno()
	{
		return is_nirTurno;
	}

	/**
	 * Modifica el valor de NombreDepartamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreDepartamento(String as_s)
	{
		is_nombreDepartamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreDepartamento()
	{
		return is_nombreDepartamento;
	}

	/**
	 * Modifica el valor de NombreMunicipio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreMunicipio(String as_s)
	{
		is_nombreMunicipio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreMunicipio()
	{
		return is_nombreMunicipio;
	}

	/**
	 * Modifica el valor de NombreOficina.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreOficina(String as_s)
	{
		is_nombreOficina = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre oficina.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreOficina()
	{
		return is_nombreOficina;
	}

	/**
	 * Modifica el valor de NombreOficinaOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreOficinaOrigen(String as_s)
	{
		is_nombreOficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre oficina origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreOficinaOrigen()
	{
		return is_nombreOficinaOrigen;
	}

	/**
	 * Modifica el valor de NombreTipoDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreTipoDocumento(String as_s)
	{
		is_nombreTipoDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre tipo documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoDocumento()
	{
		return is_nombreTipoDocumento;
	}

	/**
	 * Modifica el valor de Numero.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumero(String as_s)
	{
		is_numero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumero()
	{
		return is_numero;
	}

	/**
	 * Modifica el valor de OficinaInternacional.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOficinaInternacional(String as_s)
	{
		is_oficinaInternacional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor oficina internacional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOficinaInternacional()
	{
		return is_oficinaInternacional;
	}

	/**
	 * Modifica el valor de OficinaOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOficinaOrigen(OficinaOrigen as_s)
	{
		ioo_oficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor oficina origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public OficinaOrigen getOficinaOrigen()
	{
		return (ioo_oficinaOrigen == null) ? new OficinaOrigen() : ioo_oficinaOrigen;
	}

	/**
	 * Modifica el valor de Pais.
	 *
	 * @param ip_p asigna el valor a la propiedad
	 */
	public void setPais(Pais ip_p)
	{
		ip_pais = ip_p;
	}

	/**
	 * Retorna Objeto o variable de valor pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Pais getPais()
	{
		return (ip_pais == null) ? new Pais() : ip_pais;
	}

	/**
	 * Modifica el valor de TipoDocumento.
	 *
	 * @param atd_td asigna el valor a la propiedad
	 */
	public void setTipoDocumento(TipoDocumentoPublico atd_td)
	{
		itdp_tipoDocumento = atd_td;
	}

	/**
	 * Retorna Objeto o variable de valor tipo documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public TipoDocumentoPublico getTipoDocumento()
	{
		return (itdp_tipoDocumento == null) ? new TipoDocumentoPublico() : itdp_tipoDocumento;
	}

	/**
	 * Modifica el valor de TipoEntidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo entidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Método de asignación del valor de la propiedad de la propiedad.
	 *
	 * @param ate_ate Con el valor a asignar
	 */
	public void setTipoEntidadDocumento(TipoEntidad ate_ate)
	{
		ite_tipoEntidadDocumento = ate_ate;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return ite_tipoEntidadDocumento con el valor de la propiedad
	 */
	public TipoEntidad getTipoEntidadDocumento()
	{
		return ite_tipoEntidadDocumento;
	}

	/**
	 * Modifica el valor de TipoEntidadValid.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setTipoEntidadValid(boolean ab_b)
	{
		ib_tipoEntidadValid = ab_b;
	}

	/**
	 * Valida la propiedad tipo entidad valid.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTipoEntidadValid()
	{
		return ib_tipoEntidadValid;
	}

	/**
	 * Modifica el valor de TipoOficina.
	 *
	 * @param ato_to asigna el valor a la propiedad
	 */
	public void setTipoOficina(TipoOficina ato_to)
	{
		ito_tipoOficina = ato_to;
	}

	/**
	 * Retorna Objeto o variable de valor tipo oficina.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public TipoOficina getTipoOficina()
	{
		return (ito_tipoOficina == null) ? new TipoOficina() : ito_tipoOficina;
	}

	/**
	 * Modifica el valor de TipoOficinaValid.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setTipoOficinaValid(boolean ab_b)
	{
		ib_tipoOficinaValid = ab_b;
	}

	/**
	 * Valida la propiedad tipo oficina valid.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTipoOficinaValid()
	{
		return ib_tipoOficinaValid;
	}

	/**
	 * Modifica el valor de TipoTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoTurno(String as_s)
	{
		is_tipoTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoTurno()
	{
		return is_tipoTurno;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setVersion(BigDecimal abd_bd)
	{
		ibd_version = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getVersion()
	{
		return ibd_version;
	}

	/**
	 * Modifica el valor de VersionDocumento.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersionDocumento(Long al_l)
	{
		il_versionDocumento = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersionDocumento()
	{
		return il_versionDocumento;
	}
}
