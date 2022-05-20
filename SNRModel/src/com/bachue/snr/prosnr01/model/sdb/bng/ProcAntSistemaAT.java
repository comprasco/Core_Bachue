package com.bachue.snr.prosnr01.model.sdb.bng;

import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoEntradaEliminarProcAntiguoSistema;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades ProcAntSistemaAT.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 3/04/2020
 */
public class ProcAntSistemaAT extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -9144168687824601726L;

	/** Propiedad is estado proceso. */
	private String is_estadoProceso;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is nom circulo registral. */
	private String is_nomCirculoRegistral;

	/** Propiedad is nom departamento. */
	private String is_nomDepartamento;

	/** Propiedad is nom municipio. */
	private String is_nomMunicipio;

	/** Propiedad is nom pais. */
	private String is_nomPais;

	/** Propiedad is nombre predio. */
	private String is_nombrePredio;

	/** Propiedad is tipo partida. */
	private String is_tipoPartida;

	/** Propiedad is tipo predio. */
	private String is_tipoPredio;

	/** Propiedad il anio. */
	private long il_anio;

	/** Propiedad il id alerta tierra. */
	private long il_idAlertaTierra;

	/** Propiedad il id ant sistema. */
	private long il_idAntSistema;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/** Propiedad il id pais. */
	private long il_idPais;

	/** Propiedad il numero folio. */
	private long il_numeroFolio;

	/** Propiedad il numero libro. */
	private long il_numeroLibro;

	/** Propiedad il numero partida. */
	private long il_numeroPartida;

	/** Propiedad il numero predio. */
	private long il_numeroPredio;

	/** Propiedad il numero tomo. */
	private long il_numeroTomo;

	/**
	 * Instancia un nuevo objeto proc ant sistema AT.
	 */
	public ProcAntSistemaAT()
	{
	}

	/**
	 * Instancia un nuevo objeto proc ant sistema AT.
	 *
	 * @param atecpast_entrada de atecpast entrada
	 */
	public ProcAntSistemaAT(TipoEntradaCrearProcAntiguoSistemaTierras atecpast_entrada)
	{
		if(atecpast_entrada != null)
		{
			is_idCirculo          = atecpast_entrada.getCodCirculoRegistral();
			is_idDepartamento     = StringUtils.getString(atecpast_entrada.getCodDepartamento());
			is_idMunicipio        = StringUtils.getString(atecpast_entrada.getCodMunicipio());
			is_nombrePredio       = atecpast_entrada.getNomPredio();
			is_tipoPartida        = atecpast_entrada.getIdTipoPartida().getValue();
			is_tipoPredio         = atecpast_entrada.getTipoPredio().getValue();
			il_anio               = NumericUtils.getLong(NumericUtils.getLongWrapper(atecpast_entrada.getAnio()));
			il_idAlertaTierra     = atecpast_entrada.getIdAlerta();
			il_idPais             = atecpast_entrada.getCodPais();
			il_numeroFolio        = atecpast_entrada.getNumFolio();
			il_numeroLibro        = atecpast_entrada.getNumLibro();
			il_numeroPartida      = atecpast_entrada.getNumPartida();
			il_numeroPredio       = NumericUtils.getLong(atecpast_entrada.getNumPredio());
			il_numeroTomo         = atecpast_entrada.getNumTomo();
		}
	}

	/**
	 * Instancia un nuevo objeto proc ant sistema AT.
	 *
	 * @param ateepas_entrada de ateepas entrada
	 */
	public ProcAntSistemaAT(TipoEntradaEliminarProcAntiguoSistema ateepas_entrada)
	{
		if(ateepas_entrada != null)
		{
			il_idAlertaTierra     = ateepas_entrada.getIdAlerta();
			il_idAntSistema       = ateepas_entrada.getIdAntiguoSistemaTierras();
		}
	}

	/**
	 * Retorna Objeto o variable de valor nom circulo registral.
	 *
	 * @return Retorna el valor de la propiedad nomCirculoRegistral
	 */
	public String getNomCirculoRegistral()
	{
		return is_nomCirculoRegistral;
	}

	/**
	 * Modifica el valor de NomCirculoRegistral.
	 *
	 * @param as_s de nom circulo registral
	 */
	public void setNomCirculoRegistral(String as_s)
	{
		is_nomCirculoRegistral = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nom pais.
	 *
	 * @return Retorna el valor de la propiedad nomPais
	 */
	public String getNomPais()
	{
		return is_nomPais;
	}

	/**
	 * Modifica el valor de NomPais.
	 *
	 * @param as_s de nom pais
	 */
	public void setNomPais(String as_s)
	{
		is_nomPais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nom departamento.
	 *
	 * @return Retorna el valor de la propiedad nomDepartamento
	 */
	public String getNomDepartamento()
	{
		return is_nomDepartamento;
	}

	/**
	 * Modifica el valor de NomDepartamento.
	 *
	 * @param as_s de nom departamento
	 */
	public void setNomDepartamento(String as_s)
	{
		is_nomDepartamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nom municipio.
	 *
	 * @return Retorna el valor de la propiedad nomMunicipio
	 */
	public String getNomMunicipio()
	{
		return is_nomMunicipio;
	}

	/**
	 * Modifica el valor de NomMunicipio.
	 *
	 * @param as_s de nom municipio
	 */
	public void setNomMunicipio(String as_s)
	{
		is_nomMunicipio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id alerta tierra.
	 *
	 * @return Retorna el valor de la propiedad idAlertaTierra
	 */
	public long getIdAlertaTierra()
	{
		return il_idAlertaTierra;
	}

	/**
	 * Modifica el valor de IdAlertaTierra.
	 *
	 * @param al_l de al l
	 */
	public void setIdAlertaTierra(long al_l)
	{
		il_idAlertaTierra = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id ant sistema.
	 *
	 * @return Retorna el valor de la propiedad idAntSistema
	 */
	public long getIdAntSistema()
	{
		return il_idAntSistema;
	}

	/**
	 * Modifica el valor de IdAntSistema.
	 *
	 * @param al_l de al l
	 */
	public void setIdAntSistema(long al_l)
	{
		il_idAntSistema = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
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
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado proceso.
	 *
	 * @return Retorna el valor de la propiedad estadoProceso
	 */
	public String getEstadoProceso()
	{
		return is_estadoProceso;
	}

	/**
	 * Modifica el valor de EstadoProceso.
	 *
	 * @param as_s de as s
	 */
	public void setEstadoProceso(String as_s)
	{
		is_estadoProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return Retorna el valor de la propiedad idMatricula
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l de al l
	 */
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return Retorna el valor de la propiedad idPais
	 */
	public long getIdPais()
	{
		return il_idPais;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param al_l de al l
	 */
	public void setIdPais(long al_l)
	{
		il_idPais = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento.
	 *
	 * @return Retorna el valor de la propiedad idDepartamento
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_s de as s
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id municipio.
	 *
	 * @return Retorna el valor de la propiedad idMunicipio
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_s de as s
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo predio.
	 *
	 * @return Retorna el valor de la propiedad tipoPredio
	 */
	public String getTipoPredio()
	{
		return is_tipoPredio;
	}

	/**
	 * Modifica el valor de TipoPredio.
	 *
	 * @param as_s de as s
	 */
	public void setTipoPredio(String as_s)
	{
		is_tipoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero libro.
	 *
	 * @return Retorna el valor de la propiedad numeroLibro
	 */
	public long getNumeroLibro()
	{
		return il_numeroLibro;
	}

	/**
	 * Modifica el valor de NumeroLibro.
	 *
	 * @param al_l de al l
	 */
	public void setNumeroLibro(long al_l)
	{
		il_numeroLibro = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor numero tomo.
	 *
	 * @return Retorna el valor de la propiedad numeroTomo
	 */
	public long getNumeroTomo()
	{
		return il_numeroTomo;
	}

	/**
	 * Modifica el valor de NumeroTomo.
	 *
	 * @param al_l de al l
	 */
	public void setNumeroTomo(long al_l)
	{
		il_numeroTomo = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor tipo partida.
	 *
	 * @return Retorna el valor de la propiedad tipoPartida
	 */
	public String getTipoPartida()
	{
		return is_tipoPartida;
	}

	/**
	 * Modifica el valor de TipoPartida.
	 *
	 * @param as_s de as s
	 */
	public void setTipoPartida(String as_s)
	{
		is_tipoPartida = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero partida.
	 *
	 * @return Retorna el valor de la propiedad numeroPartida
	 */
	public long getNumeroPartida()
	{
		return il_numeroPartida;
	}

	/**
	 * Modifica el valor de NumeroPartida.
	 *
	 * @param al_l de al l
	 */
	public void setNumeroPartida(long al_l)
	{
		il_numeroPartida = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor numero folio.
	 *
	 * @return Retorna el valor de la propiedad numeroFolio
	 */
	public long getNumeroFolio()
	{
		return il_numeroFolio;
	}

	/**
	 * Modifica el valor de NumeroFolio.
	 *
	 * @param al_l de al l
	 */
	public void setNumeroFolio(long al_l)
	{
		il_numeroFolio = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor anio.
	 *
	 * @return Retorna el valor de la propiedad anio
	 */
	public long getAnio()
	{
		return il_anio;
	}

	/**
	 * Modifica el valor de Anio.
	 *
	 * @param al_l de al l
	 */
	public void setAnio(long al_l)
	{
		il_anio = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor nombre predio.
	 *
	 * @return Retorna el valor de la propiedad nombrePredio
	 */
	public String getNombrePredio()
	{
		return is_nombrePredio;
	}

	/**
	 * Modifica el valor de NombrePredio.
	 *
	 * @param as_s de as s
	 */
	public void setNombrePredio(String as_s)
	{
		is_nombrePredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero predio.
	 *
	 * @return Retorna el valor de la propiedad numeroPredio
	 */
	public long getNumeroPredio()
	{
		return il_numeroPredio;
	}

	/**
	 * Modifica el valor de NumeroPredio.
	 *
	 * @param al_l de al l
	 */
	public void setNumeroPredio(long al_l)
	{
		il_numeroPredio = al_l;
	}
}
