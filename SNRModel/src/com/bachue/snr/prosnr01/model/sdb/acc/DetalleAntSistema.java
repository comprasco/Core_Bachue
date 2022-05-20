package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;

import java.io.Serializable;



/**
 * Abstracción de la tabla SDB_ACC_DETALLE_ANT_SISTEMA para el manejo de sus
 * registros y transacciones.
 *
 * @author Manuel Blanco
 *
 */
public class DetalleAntSistema extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long   serialVersionUID               = 2502077411972171338L;
	
	/** Propiedad icr circulo registral. */
	private CirculoRegistral    icr_circuloRegistral;
	
	/** Propiedad id documento. */
	private Documento           id_documento;
	
	/** Propiedad id documento consulta. */
	private Documento           id_documentoConsulta;
	
	/** Propiedad ilas libro antiguo sistema. */
	private LibroAntiguoSistema ilas_libroAntiguoSistema;
	
	/** Propiedad il id libro ant sistema. */
	private Long                il_idLibroAntSistema;
	
	/** Propiedad il numero partida. */
	private Long                il_numeroPartida;
	
	/** Propiedad is id matricula. */
	private Long                is_idMatricula;
	
	/** Propiedad im municipio tomo. */
	private Municipio           im_municipioTomo;
	
	/** Propiedad ipt predio tipo. */
	private PredioTipo          ipt_predioTipo;
	
	/** Propiedad is activo. */
	private String              is_activo;
	
	/** Propiedad is anio. */
	private String              is_anio;
	
	/** Propiedad is consecutivo predio ant sistema. */
	private String              is_consecutivoPredioAntSistema;
	
	/** Propiedad is folio. */
	private String              is_folio;
	
	/** Propiedad is id circulo. */
	private String              is_idCirculo;
	
	/** Propiedad is id datos ant sistema. */
	private String              is_idDatosAntSistema;
	
	/** Propiedad is id departamento tomo. */
	private String              is_idDepartamentoTomo;
	
	/** Propiedad is id detalle ant sistema. */
	private String              is_idDetalleAntSistema;
	
	/** Propiedad is id documento tradicion. */
	private String              is_idDocumentoTradicion;
	
	/** Propiedad is id municipio tomo. */
	private String              is_idMunicipioTomo;
	
	/** Propiedad is nombre libro. */
	private String              is_nombreLibro;
	
	/** Propiedad is nombre predio. */
	private String              is_nombrePredio;
	
	/** Propiedad is partida. */
	private String              is_partida;
	
	/** Propiedad is tipo predio. */
	private String              is_tipoPredio;
	
	/** Propiedad is tomo. */
	private String              is_tomo;
	
	/** Propiedad is version documento tradicion. */
	private String              is_versionDocumentoTradicion;
	
	/** Propiedad ib agregado. */
	private boolean             ib_agregado;
	
	/** Propiedad ib error. */
	private boolean             ib_error;
	
	/** Propiedad ib seleccionado. */
	private boolean             ib_seleccionado;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                                              = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Agregado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAgregado(boolean ab_b)
	{
		ib_agregado = ab_b;
	}

	/**
	 * Valida la propiedad agregado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAgregado()
	{
		return ib_agregado;
	}

	/**
	 * Modifica el valor de Anio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnio(String as_s)
	{
		is_anio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnio()
	{
		return is_anio;
	}

	/**
	 * Modifica el valor de CirculoRegistral.
	 *
	 * @param acr_cr asigna el valor a la propiedad
	 */
	public void setCirculoRegistral(CirculoRegistral acr_cr)
	{
		icr_circuloRegistral = acr_cr;
	}

	/**
	 * Retorna Objeto o variable de valor circulo registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public CirculoRegistral getCirculoRegistral()
	{
		if(icr_circuloRegistral == null)
			icr_circuloRegistral = new CirculoRegistral();

		return icr_circuloRegistral;
	}

	/**
	 * Modifica el valor de ConsecutivoPredioAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConsecutivoPredioAntSistema(String as_s)
	{
		is_consecutivoPredioAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo predio ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConsecutivoPredioAntSistema()
	{
		return is_consecutivoPredioAntSistema;
	}

	/**
	 * Modifica el valor de Documento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Documento getDocumento()
	{
		return id_documento;
	}

	/**
	 * Modifica el valor de Error.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setError(boolean ab_b)
	{
		ib_error = ab_b;
	}

	/**
	 * Valida la propiedad error.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isError()
	{
		return ib_error;
	}

	/**
	 * Modifica el valor de Folio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setFolio(String as_s)
	{
		is_folio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor folio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getFolio()
	{
		return is_folio;
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
	 * Modifica el valor de IdDatosAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de IdDepartamentoTomo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDepartamentoTomo(String as_s)
	{
		is_idDepartamentoTomo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento tomo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDepartamentoTomo()
	{
		return is_idDepartamentoTomo;
	}

	/**
	 * Modifica el valor de IdDetalleAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDetalleAntSistema(String as_s)
	{
		is_idDetalleAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id detalle ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDetalleAntSistema()
	{
		return is_idDetalleAntSistema;
	}

	/**
	 * Modifica el valor de IdDocumentoTradicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumentoTradicion(String as_s)
	{
		is_idDocumentoTradicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento tradicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumentoTradicion()
	{
		return is_idDocumentoTradicion;
	}

	/**
	 * Modifica el valor de IdLibroAntSistema.
	 *
	 * @param al_l de al l
	 */
	public void setIdLibroAntSistema(Long al_l)
	{
		il_idLibroAntSistema = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id libro ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdLibroAntSistema()
	{
		return il_idLibroAntSistema;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long as_s)
	{
		is_idMatricula = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return is_idMatricula;
	}

	/**
	 * Modifica el valor de IdMunicipioTomo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMunicipioTomo(String as_s)
	{
		is_idMunicipioTomo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id municipio tomo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMunicipioTomo()
	{
		return is_idMunicipioTomo;
	}

	/**
	 * Modifica el valor de IdPaisTomo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPaisTomo(String as_s)
	{
	}

	/**
	 * Retorna Objeto o variable de valor id pais tomo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPaisTomo()
	{
		return IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;
	}

	/**
	 * Modifica el valor de LibroAntiguoSistema.
	 *
	 * @param alas_las asigna el valor a la propiedad
	 */
	public void setLibroAntiguoSistema(LibroAntiguoSistema alas_las)
	{
		ilas_libroAntiguoSistema = alas_las;
	}

	/**
	 * Retorna Objeto o variable de valor libro antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public LibroAntiguoSistema getLibroAntiguoSistema()
	{
		if(ilas_libroAntiguoSistema == null)
			ilas_libroAntiguoSistema = new LibroAntiguoSistema();

		return ilas_libroAntiguoSistema;
	}

	/**
	 * Modifica el valor de MunicipioTomo.
	 *
	 * @param am_m asigna el valor a la propiedad
	 */
	public void setMunicipioTomo(Municipio am_m)
	{
		im_municipioTomo = am_m;
	}

	/**
	 * Retorna Objeto o variable de valor municipio tomo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Municipio getMunicipioTomo()
	{
		if(im_municipioTomo == null)
			im_municipioTomo = new Municipio();

		return im_municipioTomo;
	}

	/**
	 * Modifica el valor de NombreLibro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreLibro(String as_s)
	{
		is_nombreLibro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre libro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreLibro()
	{
		return is_nombreLibro;
	}

	/**
	 * Modifica el valor de NumeroPartida.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setNumeroPartida(Long al_l)
	{
		il_numeroPartida = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor numero partida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getNumeroPartida()
	{
		return il_numeroPartida;
	}

	/**
	 * Modifica el valor de Partida.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPartida(String as_s)
	{
		is_partida = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor partida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPartida()
	{
		return is_partida;
	}

	/**
	 * Modifica el valor de PredioTipo.
	 *
	 * @param apt_pt asigna el valor a la propiedad
	 */
	public void setPredioTipo(PredioTipo apt_pt)
	{
		ipt_predioTipo = apt_pt;
	}

	/**
	 * Retorna Objeto o variable de valor predio tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public PredioTipo getPredioTipo()
	{
		if(ipt_predioTipo == null)
			ipt_predioTipo = new PredioTipo();

		return ipt_predioTipo;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de TipoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoPredio(String as_s)
	{
		is_tipoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoPredio()
	{
		return is_tipoPredio;
	}

	/**
	 * Modifica el valor de Tomo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTomo(String as_s)
	{
		is_tomo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tomo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTomo()
	{
		return is_tomo;
	}

	/**
	 * Modifica el valor de VersionDocumentoTradicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setVersionDocumentoTradicion(String as_s)
	{
		is_versionDocumentoTradicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor version documento tradicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getVersionDocumentoTradicion()
	{
		return is_versionDocumentoTradicion;
	}

	/**
	 * Retorna Objeto o variable de valor nombre predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePredio()
	{
		return is_nombrePredio;
	}

	/**
	 * Modifica el valor de NombrePredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombrePredio(String as_s)
	{
		this.is_nombrePredio = as_s;
	}
	
	/**
	 * Método de obtención de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public Documento getDocumentoConsulta()
	{
		if(id_documentoConsulta == null)
			return id_documentoConsulta = new Documento();
		else

			return id_documentoConsulta;
	}
	
	/**
	 * Método de asignación del valor de la variable .
	 *
	 * @param ad_documentoConsulta de ad documento consulta
	 */
	public void setDocumentoConsulta(Documento ad_documentoConsulta)
	{
		id_documentoConsulta = ad_documentoConsulta;
	}
}
