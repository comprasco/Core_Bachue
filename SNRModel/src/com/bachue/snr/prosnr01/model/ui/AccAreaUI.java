package com.bachue.snr.prosnr01.model.ui;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.UnidadMedidaAreaCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de AccAreaUI.
 *
 * @author garias
 */
public class AccAreaUI extends TurnoHistoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long             serialVersionUID            = 6398887477879825374L;
	
	/** Constante is_idDetalleAreaPredio. */
	private static final String           is_idDetalleAreaPredio      = "1";
	
	/** Propiedad iaap area predio. */
	private AccAreaPredio                 iaap_areaPredio;
	
	/** Propiedad ibi id detalle area predio. */
	private BigInteger                    ibi_idDetalleAreaPredio;
	
	/** Propiedad icadap areas terreno. */
	private Collection<DetalleAreaPredio> icadap_areasTerreno;
	
	/** Propiedad iadap detalle area construida. */
	private DetalleAreaPredio             iadap_detalleAreaConstruida;
	
	/** Propiedad iadap detalle area privada. */
	private DetalleAreaPredio             iadap_detalleAreaPrivada;
	
	/** Propiedad iadap detalle area terreno. */
	private DetalleAreaPredio             iadap_detalleAreaTerreno;
	
	/** Propiedad il id matricula. */
	private Long                          il_idMatricula;
	
	/** Propiedad il id turno historia. */
	private Long                          il_idTurnoHistoria;
	
	/** Propiedad is area terreno. */
	private String                        is_areaTerreno;
	
	/** Propiedad is id circulo. */
	private String                        is_idCirculo;
	
	/** Propiedad is id turno. */
	private String                        is_idTurno;
	
	/** Propiedad is linderos. */
	private String                        is_linderos;
	
	/** Propiedad ib consulta. */
	private boolean                       ib_consulta;

	/**
	 * Modifica el valor de AreaPredio.
	 *
	 * @param aaap_aap asigna el valor a la propiedad
	 */
	public void setAreaPredio(AccAreaPredio aaap_aap)
	{
		iaap_areaPredio                                               = aaap_aap;
	}

	/**
	 * Retorna una instancia de AccAreaPredio.
	 *
	 * @return AccAreaPredio si el objeto iaap_areaPredio es nulo retorna una nueva instancia del mismo,
	 * de lo contrario retorna la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio
	 */
	public AccAreaPredio getAreaPredio()
	{
		if(iaap_areaPredio == null)
			iaap_areaPredio = new AccAreaPredio();

		return iaap_areaPredio;
	}

	/**
	 * Modifica el valor de AreaTerreno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAreaTerreno(String as_s)
	{
		is_areaTerreno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor area terreno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAreaTerreno()
	{
		return is_areaTerreno;
	}

	/**
	 * Modifica el valor de AreasTerreno.
	 *
	 * @param acadap_cadap asigna el valor a la propiedad
	 */
	public void setAreasTerreno(Collection<DetalleAreaPredio> acadap_cadap)
	{
		icadap_areasTerreno = acadap_cadap;
	}

	/**
	 * Retorna una Collection de DetalleAreaPredio.
	 *
	 * @return Collection<DetalleAreaPredio> si el objeto icadap_areasTerreno es nulo retorna una nueva instancia del mismo,
	 * de lo contrario retorna la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio
	 */
	public Collection<DetalleAreaPredio> getAreasTerreno()
	{
		if(!CollectionUtils.isValidCollection(icadap_areasTerreno))
			icadap_areasTerreno = new ArrayList<DetalleAreaPredio>();

		return icadap_areasTerreno;
	}

	/**
	 * Modifica el valor de Consulta.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setConsulta(boolean ab_b)
	{
		ib_consulta = ab_b;
	}

	/**
	 * Valida la propiedad consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isConsulta()
	{
		return ib_consulta;
	}

	/**
	 * Modifica el valor de DetalleAreaConstruida.
	 *
	 * @param aadap_adap asigna el valor a la propiedad
	 */
	public void setDetalleAreaConstruida(DetalleAreaPredio aadap_adap)
	{
		iadap_detalleAreaConstruida = aadap_adap;
	}

	/**
	 * Retorna una instancia de DetalleAreaPredio.
	 *
	 * @return DetalleAreaPredio si el objeto icadap_areasTerreno es nulo retorna una nueva instancia del mismo,
	 * de lo contrario retorna la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio
	 */
	public DetalleAreaPredio getDetalleAreaConstruida()
	{
		if(iadap_detalleAreaConstruida == null)
		{
			iadap_detalleAreaConstruida = new DetalleAreaPredio();

			iadap_detalleAreaConstruida.setIdDetalleAreaPredio(is_idDetalleAreaPredio);
			iadap_detalleAreaConstruida.setIdUnidadMedida(UnidadMedidaAreaCommon.METROS_CUADRADOS);
			iadap_detalleAreaConstruida.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
		}

		return iadap_detalleAreaConstruida;
	}

	/**
	 * Modifica el valor de DetalleAreaPrivada.
	 *
	 * @param aadap_adap asigna el valor a la propiedad
	 */
	public void setDetalleAreaPrivada(DetalleAreaPredio aadap_adap)
	{
		iadap_detalleAreaPrivada = aadap_adap;
	}

	/**
	 * Retorna una instancia de DetalleAreaPredio.
	 *
	 * @return DetalleAreaPredio si el objeto icadap_areasTerreno es nulo retorna una nueva instancia del mismo,
	 * de lo contrario retorna la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio
	 */
	public DetalleAreaPredio getDetalleAreaPrivada()
	{
		if(iadap_detalleAreaPrivada == null)
		{
			iadap_detalleAreaPrivada = new DetalleAreaPredio();

			iadap_detalleAreaPrivada.setIdDetalleAreaPredio(is_idDetalleAreaPredio);
			iadap_detalleAreaPrivada.setIdUnidadMedida(UnidadMedidaAreaCommon.METROS_CUADRADOS);
			iadap_detalleAreaPrivada.setIdTipoArea(TipoAreaCommon.PRIVADA);
		}

		return iadap_detalleAreaPrivada;
	}

	/**
	 * Modifica el valor de DetalleAreaTerreno.
	 *
	 * @param aadap_adap asigna el valor a la propiedad
	 */
	public void setDetalleAreaTerreno(DetalleAreaPredio aadap_adap)
	{
		iadap_detalleAreaTerreno = aadap_adap;
	}

	/**
	 * Retorna una instancia de DetalleAreaPredio.
	 *
	 * @return DetalleAreaPredio si el objeto icadap_areasTerreno es nulo retorna una nueva instancia del mismo,
	 * de lo contrario retorna la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio
	 */
	public DetalleAreaPredio getDetalleAreaTerreno()
	{
		if(iadap_detalleAreaTerreno == null)
		{
			iadap_detalleAreaTerreno = new DetalleAreaPredio();

			iadap_detalleAreaTerreno.setIdDetalleAreaPredio(is_idDetalleAreaPredio);
			iadap_detalleAreaTerreno.setIdUnidadMedida(UnidadMedidaAreaCommon.METROS_CUADRADOS);
			iadap_detalleAreaTerreno.setIdTipoArea(TipoAreaCommon.TERRENO);
		}

		return iadap_detalleAreaTerreno;
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
	 * Modifica el valor de IdDetalleAreaPredio.
	 *
	 * @param abi_i asigna el valor a la propiedad
	 */
	public void setIdDetalleAreaPredio(BigInteger abi_i)
	{
		ibi_idDetalleAreaPredio = abi_i;
	}

	/**
	 * Retorna Objeto o variable de valor id detalle area predio.
	 *
	 * @return el valor de id detalle area predio
	 */
	public BigInteger getIdDetalleAreaPredio()
	{
		if(!NumericUtils.isValidBigInteger(ibi_idDetalleAreaPredio))
			ibi_idDetalleAreaPredio = BigInteger.ONE;

		return ibi_idDetalleAreaPredio;
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
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de Linderos.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLinderos(String as_s)
	{
		is_linderos = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor linderos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLinderos()
	{
		return is_linderos;
	}
}
