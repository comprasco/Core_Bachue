package com.bachue.snr.prosnr01.web.util;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.model.StreamedContent;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * Clase que contiene todos las propiedades de DatosDelPredio.
 *
 * @author lchacon
 */
public class DatosDelPredio implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2567862931322918849L;

	/** Propiedad icdas detalles ant sis agregados. */
	private Collection<DetalleAntSistema> icdas_detallesAntSisAgregados;

	/** Propiedad icms data info predial. */
	private Collection<MatriculaSegregacionUi> icms_dataInfoPredial;

	/** Propiedad icpaiu actos asociados general. */
	private Collection<PredioActoIU> icpaiu_actosAsociadosGeneral;

	/** Propiedad icto tipo oficina documento. */
	private Collection<TipoOficina> icto_tipoOficinaDocumento;

	/** Propiedad ictrm tipos matricula. */
	private Collection<TipoRequiereMatricula> ictrm_tiposMatricula;

	/** Propiedad lcdas datos ant sis agregados. */
	private Collection<DatosAntSistema> lcdas_datosAntSisAgregados;

	/** Propiedad idas datos antiguo sistema. */
	private DatosAntSistema idas_datosAntiguoSistema;

	/** Propiedad idas detalle ant sistema. */
	private DetalleAntSistema idas_detalleAntSistema;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad ilcm columns. */
	private List<ColumnModel> ilcm_columns;

	/** Propiedad il cantidad certificados. */
	private Long il_cantidadCertificados;

	/** Propiedad ims matricula segregacion. */
	private MatriculaSegregacion ims_matriculaSegregacion;

	/** Propiedad isc file descarga. */
	private StreamedContent isc_fileDescarga;

	/** Propiedad isc file predial. */
	private StreamedContent isc_filePredial;

	/** Propiedad is antiguo sistema 1932. */
	private String is_antiguoSistema1932;

	/** Propiedad is circulo rangos. */
	private String is_circuloRangos;

	/** Propiedad is eleccion requiere matricula. */
	private String is_eleccionRequiereMatricula;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id tipo entidad. */
	private String is_idTipoEntidad;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is rango fin. */
	private String is_rangoFin;

	/** Propiedad is rango inicio. */
	private String is_rangoInicio;

	/** Propiedad is seccion tipo matricula. */
	private String is_seccionTipoMatricula;

	/** Propiedad iba file. */
	private byte[] iba_file;

	/** Propiedad ib actos no validos para antiguo sistema. */
	private boolean ib_actosNoValidosParaAntiguoSistema;

	/** Propiedad ib creacion predio. */
	private boolean ib_creacionPredio;

	/** Propiedad ib datos ant sistema guardados. */
	private boolean ib_datosAntSistemaGuardados;

	/** Propiedad ib desenglobes. */
	private boolean ib_desenglobes;

	/** Propiedad ib detalles ant sistema guardados. */
	private boolean ib_detallesAntSistemaGuardados;

	/** Propiedad ib englobes. */
	private boolean ib_englobes;

	/** Propiedad ib es acto con ejecutoria. */
	private boolean ib_esActoConEjecutoria;

	/** Propiedad ib file predial rendered. */
	private boolean ib_filePredialRendered;

	/** Propiedad ib libro matriculas. */
	private boolean ib_libroMatriculas;

	/** Propiedad ib libro primero. */
	private boolean ib_libroPrimero;

	/** Propiedad ib libro segundo. */
	private boolean ib_libroSegundo;

	/** Propiedad ib modificar datos ant sis. */
	private boolean ib_modificarDatosAntSis;

	/** Propiedad ib modificar detalles ant sis. */
	private boolean ib_modificarDetallesAntSis;

	/** Propiedad ib reloteo. */
	private boolean ib_reloteo;

	/** Propiedad ib validar proceso. */
	private boolean ib_validarProceso;

	/** Propiedad ib venta parcial. */
	private boolean ib_ventaParcial;

	/**
	 * Modifica el valor de actos asociados general.
	 *
	 * @param acpaiu_cpaiu asigna el valor a la propiedad actos asociados general
	 */
	public void setActosAsociadosGeneral(Collection<PredioActoIU> acpaiu_cpaiu)
	{
		icpaiu_actosAsociadosGeneral = acpaiu_cpaiu;
	}

	/**
	 * Retorna el valor de actos asociados general.
	 *
	 * @return el valor de actos asociados general
	 */
	public Collection<PredioActoIU> getActosAsociadosGeneral()
	{
		return icpaiu_actosAsociadosGeneral;
	}

	/**
	 * Modifica el valor de actos no validos para antiguo sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad actos no validos para antiguo sistema
	 */
	public void setActosNoValidosParaAntiguoSistema(boolean ab_b)
	{
		ib_actosNoValidosParaAntiguoSistema = ab_b;
	}

	/**
	 * Valida la propiedad actos no validos para antiguo sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en actos no validos para antiguo sistema
	 */
	public boolean isActosNoValidosParaAntiguoSistema()
	{
		return ib_actosNoValidosParaAntiguoSistema;
	}

	/**
	 * Modifica el valor de antiguo sistema 1932.
	 *
	 * @param as_s asigna el valor a la propiedad antiguo sistema 1932
	 */
	public void setAntiguoSistema1932(String as_s)
	{
		is_antiguoSistema1932 = as_s;
	}

	/**
	 * Retorna el valor de antiguo sistema 1932.
	 *
	 * @return el valor de antiguo sistema 1932
	 */
	public String getAntiguoSistema1932()
	{
		return is_antiguoSistema1932;
	}

	/**
	 * Modifica el valor de bgn documento.
	 *
	 * @param ad_d asigna el valor a la propiedad bgn documento
	 */
	public void setBgnDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de bgn documento.
	 *
	 * @return el valor de bgn documento
	 */
	public Documento getBgnDocumento()
	{
		if(id_documento == null)
		{
			id_documento = new Documento();

			id_documento.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return id_documento;
	}

	/**
	 * Modifica el valor de cantidad certificados.
	 *
	 * @param cantidadCertificados asigna el valor a la propiedad cantidad certificados
	 */
	public void setCantidadCertificados(Long cantidadCertificados)
	{
		this.il_cantidadCertificados = cantidadCertificados;
	}

	/**
	 * Retorna el valor de cantidad certificados.
	 *
	 * @return el valor de cantidad certificados
	 */
	public Long getCantidadCertificados()
	{
		return il_cantidadCertificados;
	}

	/**
	 * Modifica el valor de circulo rangos.
	 *
	 * @param as_s asigna el valor a la propiedad circulo rangos
	 */
	public void setCirculoRangos(String as_s)
	{
		is_circuloRangos = as_s;
	}

	/**
	 * Retorna el valor de circulo rangos.
	 *
	 * @return el valor de circulo rangos
	 */
	public String getCirculoRangos()
	{
		return is_circuloRangos;
	}

	/**
	 * Modifica el valor de columns.
	 *
	 * @param alcm_lcm asigna el valor a la propiedad columns
	 */
	public void setColumns(List<ColumnModel> alcm_lcm)
	{
		ilcm_columns = alcm_lcm;
	}

	/**
	 * Retorna el valor de columns.
	 *
	 * @return el valor de columns
	 */
	public List<ColumnModel> getColumns()
	{
		return ilcm_columns;
	}

	/**
	 * Modifica el valor de creacion predio.
	 *
	 * @param ab_b asigna el valor a la propiedad creacion predio
	 */
	public void setCreacionPredio(boolean ab_b)
	{
		ib_creacionPredio = ab_b;
	}

	/**
	 * Valida la propiedad creacion predio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en creacion predio
	 */
	public boolean isCreacionPredio()
	{
		return ib_creacionPredio;
	}

	/**
	 * Modifica el valor de data info predial.
	 *
	 * @param acms_cms asigna el valor a la propiedad data info predial
	 */
	public void setDataInfoPredial(Collection<MatriculaSegregacionUi> acms_cms)
	{
		icms_dataInfoPredial = acms_cms;
	}

	/**
	 * Retorna el valor de data info predial.
	 *
	 * @return el valor de data info predial
	 */
	public Collection<MatriculaSegregacionUi> getDataInfoPredial()
	{
		if(!CollectionUtils.isValidCollection(icms_dataInfoPredial))
			icms_dataInfoPredial = new ArrayList<MatriculaSegregacionUi>();

		return icms_dataInfoPredial;
	}

	/**
	 * Modifica el valor de datos ant sis agregados.
	 *
	 * @param acdas_cdas asigna el valor a la propiedad datos ant sis agregados
	 */
	public void setDatosAntSisAgregados(Collection<DatosAntSistema> acdas_cdas)
	{
		lcdas_datosAntSisAgregados = acdas_cdas;
	}

	/**
	 * Retorna el valor de datos ant sis agregados.
	 *
	 * @return el valor de datos ant sis agregados
	 */
	public Collection<DatosAntSistema> getDatosAntSisAgregados()
	{
		if(lcdas_datosAntSisAgregados == null)
			lcdas_datosAntSisAgregados = new LinkedList<DatosAntSistema>();

		return lcdas_datosAntSisAgregados;
	}

	/**
	 * Modifica el valor de datos ant sistema guardados.
	 *
	 * @param ab_b asigna el valor a la propiedad datos ant sistema guardados
	 */
	public void setDatosAntSistemaGuardados(boolean ab_b)
	{
		ib_datosAntSistemaGuardados = ab_b;
	}

	/**
	 * Valida la propiedad datos ant sistema guardados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos ant sistema guardados
	 */
	public boolean isDatosAntSistemaGuardados()
	{
		return ib_datosAntSistemaGuardados;
	}

	/**
	 * Modifica el valor de datos antiguo sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad datos antiguo sistema
	 */
	public void setDatosAntiguoSistema(DatosAntSistema adas_das)
	{
		idas_datosAntiguoSistema = adas_das;
	}

	/**
	 * Retorna el valor de datos antiguo sistema.
	 *
	 * @return el valor de datos antiguo sistema
	 */
	public DatosAntSistema getDatosAntiguoSistema()
	{
		if(idas_datosAntiguoSistema == null)
		{
			idas_datosAntiguoSistema = new DatosAntSistema();
			idas_datosAntiguoSistema.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return idas_datosAntiguoSistema;
	}

	/**
	 * Modifica el valor de desenglobes.
	 *
	 * @param ab_b asigna el valor a la propiedad desenglobes
	 */
	public void setDesenglobes(boolean ab_b)
	{
		ib_desenglobes = ab_b;
	}

	/**
	 * Valida la propiedad desenglobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en desenglobes
	 */
	public boolean isDesenglobes()
	{
		return ib_desenglobes;
	}

	/**
	 * Modifica el valor de detalle ant sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad detalle ant sistema
	 */
	public void setDetalleAntSistema(DetalleAntSistema adas_das)
	{
		idas_detalleAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de detalle ant sistema.
	 *
	 * @return el valor de detalle ant sistema
	 */
	public DetalleAntSistema getDetalleAntSistema()
	{
		if(idas_detalleAntSistema == null)
			idas_detalleAntSistema = new DetalleAntSistema();

		return idas_detalleAntSistema;
	}

	/**
	 * Modifica el valor de detalles ant sis agregados.
	 *
	 * @param acdas_cdas asigna el valor a la propiedad detalles ant sis agregados
	 */
	public void setDetallesAntSisAgregados(Collection<DetalleAntSistema> acdas_cdas)
	{
		icdas_detallesAntSisAgregados = acdas_cdas;
	}

	/**
	 * Retorna el valor de detalles ant sis agregados.
	 *
	 * @return el valor de detalles ant sis agregados
	 */
	public Collection<DetalleAntSistema> getDetallesAntSisAgregados()
	{
		if(icdas_detallesAntSisAgregados == null)
			icdas_detallesAntSisAgregados = new LinkedList<DetalleAntSistema>();

		return icdas_detallesAntSisAgregados;
	}

	/**
	 * Modifica el valor de detalles ant sistema guardados.
	 *
	 * @param ab_b asigna el valor a la propiedad detalles ant sistema guardados
	 */
	public void setDetallesAntSistemaGuardados(boolean ab_b)
	{
		ib_detallesAntSistemaGuardados = ab_b;
	}

	/**
	 * Valida la propiedad detalles ant sistema guardados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en detalles ant sistema guardados
	 */
	public boolean isDetallesAntSistemaGuardados()
	{
		return ib_detallesAntSistemaGuardados;
	}

	/**
	 * Modifica el valor de eleccion requiere matricula.
	 *
	 * @param as_s asigna el valor a la propiedad eleccion requiere matricula
	 */
	public void setEleccionRequiereMatricula(String as_s)
	{
		is_eleccionRequiereMatricula = as_s;
	}

	/**
	 * Retorna el valor de eleccion requiere matricula.
	 *
	 * @return el valor de eleccion requiere matricula
	 */
	public String getEleccionRequiereMatricula()
	{
		return is_eleccionRequiereMatricula;
	}

	/**
	 * Modifica el valor de englobes.
	 *
	 * @param ab_b asigna el valor a la propiedad englobes
	 */
	public void setEnglobes(boolean ab_b)
	{
		ib_englobes = ab_b;
	}

	/**
	 * Valida la propiedad englobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en englobes
	 */
	public boolean isEnglobes()
	{
		return ib_englobes;
	}

	/**
	 * Modifica el valor de es acto con ejecutoria.
	 *
	 * @param ab_b asigna el valor a la propiedad es acto con ejecutoria
	 */
	public void setEsActoConEjecutoria(boolean ab_b)
	{
		ib_esActoConEjecutoria = ab_b;
	}

	/**
	 * Valida la propiedad es acto con ejecutoria.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es acto con ejecutoria
	 */
	public boolean isEsActoConEjecutoria()
	{
		return ib_esActoConEjecutoria;
	}

	/**
	 * Modifica el valor de file.
	 *
	 * @param aba_ba asigna el valor a la propiedad file
	 */
	public void setFile(byte[] aba_ba)
	{
		iba_file = aba_ba;
	}

	/**
	 * Retorna el valor de file.
	 *
	 * @return el valor de file
	 */
	public byte[] getFile()
	{
		return iba_file;
	}

	/**
	 * Modifica el valor de file.
	 *
	 * @param asc_sc asigna el valor a la propiedad file
	 */
	public void setFileDescarga(StreamedContent asc_sc)
	{
		isc_fileDescarga = asc_sc;
	}

	/**
	 * Retorna el valor de file.
	 *
	 * @return el valor de file
	 */
	public StreamedContent getFileDescarga()
	{
		return isc_fileDescarga;
	}

	/**
	 * Modifica el valor de file predial.
	 *
	 * @param asc_sc asigna el valor a la propiedad file predial
	 */
	public void setFilePredial(StreamedContent asc_sc)
	{
		isc_filePredial = asc_sc;
	}

	/**
	 * Retorna el valor de file predial.
	 *
	 * @return el valor de file predial
	 */
	public StreamedContent getFilePredial()
	{
		return isc_filePredial;
	}

	/**
	 * Modifica el valor de file predial rendered.
	 *
	 * @param ab_b asigna el valor a la propiedad file predial rendered
	 */
	public void setFilePredialRendered(boolean ab_b)
	{
		ib_filePredialRendered = ab_b;
	}

	/**
	 * Valida la propiedad file predial rendered.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en file predial rendered
	 */
	public boolean isFilePredialRendered()
	{
		return ib_filePredialRendered;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de id departamento.
	 *
	 * @param as_s asigna el valor a la propiedad id departamento
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna el valor de id departamento.
	 *
	 * @return el valor de id departamento
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de id municipio.
	 *
	 * @param as_s asigna el valor a la propiedad id municipio
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna el valor de id municipio.
	 *
	 * @return el valor de id municipio
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
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
	 * Modifica el valor de id tipo entidad.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo entidad
	 */
	public void setIdTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de id tipo entidad.
	 *
	 * @return el valor de id tipo entidad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Modifica el valor de libro matriculas.
	 *
	 * @param ab_b asigna el valor a la propiedad libro matriculas
	 */
	public void setLibroMatriculas(boolean ab_b)
	{
		ib_libroMatriculas = ab_b;
	}

	/**
	 * Valida la propiedad libro matriculas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en libro matriculas
	 */
	public boolean isLibroMatriculas()
	{
		return ib_libroMatriculas;
	}

	/**
	 * Modifica el valor de libro primero.
	 *
	 * @param ab_b asigna el valor a la propiedad libro primero
	 */
	public void setLibroPrimero(boolean ab_b)
	{
		ib_libroPrimero = ab_b;
	}

	/**
	 * Valida la propiedad libro primero.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en libro primero
	 */
	public boolean isLibroPrimero()
	{
		return ib_libroPrimero;
	}

	/**
	 * Modifica el valor de libro segundo.
	 *
	 * @param ab_b asigna el valor a la propiedad libro segundo
	 */
	public void setLibroSegundo(boolean ab_b)
	{
		ib_libroSegundo = ab_b;
	}

	/**
	 * Valida la propiedad libro segundo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en libro segundo
	 */
	public boolean isLibroSegundo()
	{
		return ib_libroSegundo;
	}

	/**
	 * Modifica el valor de matricula segregacion.
	 *
	 * @param ams_ms asigna el valor a la propiedad matricula segregacion
	 */
	public void setMatriculaSegregacion(MatriculaSegregacion ams_ms)
	{
		ims_matriculaSegregacion = ams_ms;
	}

	/**
	 * Retorna el valor de matricula segregacion.
	 *
	 * @return el valor de matricula segregacion
	 */
	public MatriculaSegregacion getMatriculaSegregacion()
	{
		if(ims_matriculaSegregacion == null)
			ims_matriculaSegregacion = new MatriculaSegregacion();

		return ims_matriculaSegregacion;
	}

	/**
	 * Modifica el valor de modificar datos ant sis.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar datos ant sis
	 */
	public void setModificarDatosAntSis(boolean ab_b)
	{
		ib_modificarDatosAntSis = ab_b;
	}

	/**
	 * Valida la propiedad modificar datos ant sis.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar datos ant sis
	 */
	public boolean isModificarDatosAntSis()
	{
		return ib_modificarDatosAntSis;
	}

	/**
	 * Modifica el valor de modificar detalles ant sis.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar detalles ant sis
	 */
	public void setModificarDetallesAntSis(boolean ab_b)
	{
		ib_modificarDetallesAntSis = ab_b;
	}

	/**
	 * Valida la propiedad modificar detalles ant sis.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar detalles ant sis
	 */
	public boolean isModificarDetallesAntSis()
	{
		return ib_modificarDetallesAntSis;
	}

	/**
	 * Modifica el valor de nombre circulo.
	 *
	 * @param as_s asigna el valor a la propiedad nombre circulo
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna el valor de nombre circulo.
	 *
	 * @return el valor de nombre circulo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de rango fin.
	 *
	 * @param as_s asigna el valor a la propiedad rango fin
	 */
	public void setRangoFin(String as_s)
	{
		is_rangoFin = StringUtils.getStringUpperCase(as_s);
	}

	/**
	 * Retorna el valor de rango fin.
	 *
	 * @return el valor de rango fin
	 */
	public String getRangoFin()
	{
		return is_rangoFin;
	}

	/**
	 * Modifica el valor de rango inicio.
	 *
	 * @param as_s asigna el valor a la propiedad rango inicio
	 */
	public void setRangoInicio(String as_s)
	{
		is_rangoInicio = StringUtils.getStringUpperCase(as_s);
	}

	/**
	 * Retorna el valor de rango inicio.
	 *
	 * @return el valor de rango inicio
	 */
	public String getRangoInicio()
	{
		return is_rangoInicio;
	}

	/**
	 * Modifica el valor de reloteo.
	 *
	 * @param ab_b asigna el valor a la propiedad reloteo
	 */
	public void setReloteo(boolean ab_b)
	{
		ib_reloteo = ab_b;
	}

	/**
	 * Valida la propiedad reloteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en reloteo
	 */
	public boolean isReloteo()
	{
		return ib_reloteo;
	}

	/**
	 * Modifica el valor de seccion tipo matricula.
	 *
	 * @param as_s asigna el valor a la propiedad seccion tipo matricula
	 */
	public void setSeccionTipoMatricula(String as_s)
	{
		is_seccionTipoMatricula = as_s;
	}

	/**
	 * Retorna el valor de seccion tipo matricula.
	 *
	 * @return el valor de seccion tipo matricula
	 */
	public String getSeccionTipoMatricula()
	{
		return is_seccionTipoMatricula;
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
		if(icto_tipoOficinaDocumento == null)
			icto_tipoOficinaDocumento = new LinkedList<TipoOficina>();

		return icto_tipoOficinaDocumento;
	}

	/**
	 * Modifica el valor de tipos matricula.
	 *
	 * @param actrm_ctrm asigna el valor a la propiedad tipos matricula
	 */
	public void setTiposMatricula(Collection<TipoRequiereMatricula> actrm_ctrm)
	{
		ictrm_tiposMatricula = actrm_ctrm;
	}

	/**
	 * Retorna el valor de tipos matricula.
	 *
	 * @return el valor de tipos matricula
	 */
	public Collection<TipoRequiereMatricula> getTiposMatricula()
	{
		return ictrm_tiposMatricula;
	}

	/**
	 * Modifica el valor de validar proceso.
	 *
	 * @param ab_b asigna el valor a la propiedad validar proceso
	 */
	public void setValidarProceso(boolean ab_b)
	{
		ib_validarProceso = ab_b;
	}

	/**
	 * Valida la propiedad validar proceso.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validar proceso
	 */
	public boolean isValidarProceso()
	{
		return ib_validarProceso;
	}

	/**
	 * Modifica el valor de venta parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad venta parcial
	 */
	public void setVentaParcial(boolean ab_b)
	{
		ib_ventaParcial = ab_b;
	}

	/**
	 * Valida la propiedad venta parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en venta parcial
	 */
	public boolean isVentaParcial()
	{
		return ib_ventaParcial;
	}

	/**
	 * Método que reconstruye el archivo para ser descargado nuevamente.
	 */
	public void rebuildFile()
	{
		setFileDescarga(
		    BaseBean.generarArchivosDescarga(getFile(), TipoContenidoCommon.XLS_XLSX_XXLS, "Resultado_del_Cargue.xlsx")
		);
	}
}
