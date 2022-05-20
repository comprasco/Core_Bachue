package com.bachue.snr.prosnr02.model.workflow;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr02.model.pgn.MotivoTramiteTrabajo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



/**
 * Clase que contiene todos las propiedades Nodo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public abstract class Nodo extends Componente
{
	
	/** Constante serialVersionUID. */
	private static final long                 serialVersionUID             = 6497361454849164598L;
	
	/** Constante CARDINALIDAD_INDETERMINADA. */
	public static final int                   CARDINALIDAD_INDETERMINADA   = 0;
	
	/** Constante CARDINALIDAD_MUCHOS_A_MUCHOS. */
	public static final int                   CARDINALIDAD_MUCHOS_A_MUCHOS = 1;
	
	/** Constante CARDINALIDAD_MUCHOS_A_UNO. */
	public static final int                   CARDINALIDAD_MUCHOS_A_UNO    = 2;
	
	/** Constante CARDINALIDAD_UNO_A_MUCHOS. */
	public static final int                   CARDINALIDAD_UNO_A_MUCHOS    = 3;
	
	/** Constante CARDINALIDAD_UNO_A_UNO. */
	public static final int                   CARDINALIDAD_UNO_A_UNO       = 4;
	
	/** Propiedad imsmtt flujos entrada. */
	private Map<String, MotivoTramiteTrabajo> imsmtt_flujosEntrada;
	
	/** Propiedad imsmtt flujos salida. */
	private Map<String, MotivoTramiteTrabajo> imsmtt_flujosSalida;
	
	/** Propiedad ii cantidad flujos entrada. */
	private int                               ii_cantidadFlujosEntrada;
	
	/** Propiedad ii cantidad flujos salida. */
	private int                               ii_cantidadFlujosSalida;

	/**
	 * Instancia un nuevo objeto nodo.
	 */
	public Nodo()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto nodo.
	 *
	 * @param as_id de as id
	 */
	public Nodo(String as_id)
	{
		setId(as_id);
		setCantidadFlujosEntrada(0);
		setCantidadFlujosSalida(0);
	}

	/**
	 * Retorna Objeto o variable de valor cantidad flujos entrada.
	 *
	 * @return el valor de cantidad flujos entrada
	 */
	public int getCantidadFlujosEntrada()
	{
		return ii_cantidadFlujosEntrada;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad flujos salida.
	 *
	 * @return el valor de cantidad flujos salida
	 */
	public int getCantidadFlujosSalida()
	{
		return ii_cantidadFlujosSalida;
	}

	/**
	 * Retorna Objeto o variable de valor cardinalidad.
	 *
	 * @return el valor de cardinalidad
	 */
	public int getCardinalidad()
	{
		int li_cardinalidad;

		if(isMuchosAMuchos())
			li_cardinalidad = CARDINALIDAD_MUCHOS_A_MUCHOS;
		else if(isMuchosAUno())
			li_cardinalidad = CARDINALIDAD_MUCHOS_A_UNO;
		else if(isUnoAMuchos())
			li_cardinalidad = CARDINALIDAD_UNO_A_MUCHOS;
		else if(isUnoAUno())
			li_cardinalidad = CARDINALIDAD_UNO_A_UNO;
		else
			li_cardinalidad = CARDINALIDAD_INDETERMINADA;

		return li_cardinalidad;
	}

	/**
	 * Retorna Objeto o variable de valor coleccion flujos entrada.
	 *
	 * @return el valor de coleccion flujos entrada
	 */
	public Collection<MotivoTramiteTrabajo> getColeccionFlujosEntrada()
	{
		return getColeccionFlujos(imsmtt_flujosEntrada);
	}

	/**
	 * Retorna Objeto o variable de valor coleccion flujos salida.
	 *
	 * @return el valor de coleccion flujos salida
	 */
	public Collection<MotivoTramiteTrabajo> getColeccionFlujosSalida()
	{
		return getColeccionFlujos(imsmtt_flujosSalida);
	}

	/**
	 * Retorna Objeto o variable de valor flujo entrada.
	 *
	 * @param as_id de as id
	 * @return el valor de flujo entrada
	 */
	public MotivoTramiteTrabajo getFlujoEntrada(String as_id)
	{
		return getFlujo(as_id, imsmtt_flujosEntrada);
	}

	/**
	 * Retorna Objeto o variable de valor flujo salida.
	 *
	 * @param as_id de as id
	 * @return el valor de flujo salida
	 */
	public MotivoTramiteTrabajo getFlujoSalida(String as_id)
	{
		return getFlujo(as_id, imsmtt_flujosSalida);
	}

	/**
	 * Cambia el valor de flujos entrada.
	 *
	 * @param amsmtt_flujos de amsmtt flujos
	 */
	public void setFlujosEntrada(Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		imsmtt_flujosEntrada                                               = amsmtt_flujos;

		contarFlujosEntrada();
	}

	/**
	 * Retorna Objeto o variable de valor flujos entrada.
	 *
	 * @return el valor de flujos entrada
	 */
	public Map<String, MotivoTramiteTrabajo> getFlujosEntrada()
	{
		return imsmtt_flujosEntrada;
	}

	/**
	 * Cambia el valor de flujos salida.
	 *
	 * @param amsmtt_flujos de amsmtt flujos
	 */
	public void setFlujosSalida(Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		imsmtt_flujosSalida = amsmtt_flujos;

		contarFlujosSalida();
	}

	/**
	 * Retorna Objeto o variable de valor flujos salida.
	 *
	 * @return el valor de flujos salida
	 */
	public Map<String, MotivoTramiteTrabajo> getFlujosSalida()
	{
		return imsmtt_flujosSalida;
	}

	/**
	 * Valida la propiedad muchos A muchos.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en muchos A muchos
	 */
	public boolean isMuchosAMuchos()
	{
		return (getCantidadFlujosEntrada() > 1) && (getCantidadFlujosSalida() > 1);
	}

	/**
	 * Valida la propiedad muchos A uno.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en muchos A uno
	 */
	public boolean isMuchosAUno()
	{
		return (getCantidadFlujosEntrada() > 1) && (getCantidadFlujosSalida() == 1);
	}

	/**
	 * Valida la propiedad multiples salidas.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en multiples salidas
	 */
	public boolean isMultiplesSalidas()
	{
		return getCantidadFlujosSalida() > 1;
	}

	/**
	 * Valida la propiedad unica salida.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en unica salida
	 */
	public boolean isUnicaSalida()
	{
		return getCantidadFlujosSalida() == 1;
	}

	/**
	 * Valida la propiedad uno A muchos.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en uno A muchos
	 */
	public boolean isUnoAMuchos()
	{
		return (getCantidadFlujosEntrada() == 1) && (getCantidadFlujosSalida() > 1);
	}

	/**
	 * Valida la propiedad uno A uno.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en uno A uno
	 */
	public boolean isUnoAUno()
	{
		return (getCantidadFlujosEntrada() == 1) && (getCantidadFlujosSalida() == 1);
	}

	/**
	 * Adicionar flujo entrada.
	 *
	 * @param amtt_flujo de amtt flujo
	 */
	public void adicionarFlujoEntrada(MotivoTramiteTrabajo amtt_flujo)
	{
		imsmtt_flujosEntrada = adicionarFlujo(amtt_flujo, imsmtt_flujosEntrada);

		contarFlujosEntrada();
	}

	/**
	 * Adicionar flujo salida.
	 *
	 * @param amtt_flujo de amtt flujo
	 */
	public void adicionarFlujoSalida(MotivoTramiteTrabajo amtt_flujo)
	{
		imsmtt_flujosSalida = adicionarFlujo(amtt_flujo, imsmtt_flujosSalida);

		contarFlujosSalida();
	}

	/**
	 * Adicionar flujos entrada.
	 *
	 * @param amsmtt_flujos de amsmtt flujos
	 */
	public void adicionarFlujosEntrada(Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		imsmtt_flujosEntrada = adicionarFlujos(amsmtt_flujos, imsmtt_flujosEntrada);

		contarFlujosEntrada();
	}

	/**
	 * Adicionar flujos salida.
	 *
	 * @param amsmtt_flujos de amsmtt flujos
	 */
	public void adicionarFlujosSalida(Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		imsmtt_flujosSalida = adicionarFlujos(amsmtt_flujos, imsmtt_flujosSalida);

		contarFlujosSalida();
	}

	/**
	 * Eliminar flujo entrada.
	 *
	 * @param amtt_flujo de amtt flujo
	 */
	public void eliminarFlujoEntrada(MotivoTramiteTrabajo amtt_flujo)
	{
		eliminarFlujo(amtt_flujo, imsmtt_flujosEntrada);

		contarFlujosEntrada();
	}

	/**
	 * Eliminar flujo entrada.
	 *
	 * @param as_idFlujo de as id flujo
	 */
	public void eliminarFlujoEntrada(String as_idFlujo)
	{
		eliminarFlujo(as_idFlujo, imsmtt_flujosEntrada);

		contarFlujosEntrada();
	}

	/**
	 * Eliminar flujo salida.
	 *
	 * @param amtt_flujo de amtt flujo
	 */
	public void eliminarFlujoSalida(MotivoTramiteTrabajo amtt_flujo)
	{
		eliminarFlujo(amtt_flujo, imsmtt_flujosSalida);

		contarFlujosSalida();
	}

	/**
	 * Eliminar flujo salida.
	 *
	 * @param as_idFlujo de as id flujo
	 */
	public void eliminarFlujoSalida(String as_idFlujo)
	{
		eliminarFlujo(as_idFlujo, imsmtt_flujosSalida);

		contarFlujosSalida();
	}

	/**
	 * Modifica el valor de CantidadFlujosEntrada.
	 *
	 * @param ai_i de ai i
	 */
	private void setCantidadFlujosEntrada(int ai_i)
	{
		ii_cantidadFlujosEntrada = ai_i;
	}

	/**
	 * Modifica el valor de CantidadFlujosSalida.
	 *
	 * @param ai_i de ai i
	 */
	private void setCantidadFlujosSalida(int ai_i)
	{
		ii_cantidadFlujosSalida = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor coleccion flujos.
	 *
	 * @param amsmtt_flujos de amsmtt flujos
	 * @return el valor de coleccion flujos
	 */
	private Collection<MotivoTramiteTrabajo> getColeccionFlujos(Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		return CollectionUtils.isValidCollection(amsmtt_flujos) ? CollectionUtils.sort(amsmtt_flujos.values()) : null;
	}

	/**
	 * Retorna Objeto o variable de valor flujo.
	 *
	 * @param as_id de as id
	 * @param amsmtt_flujos de amsmtt flujos
	 * @return el valor de flujo
	 */
	private MotivoTramiteTrabajo getFlujo(String as_id, Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		String ls_id;

		ls_id = StringUtils.getString(as_id);

		return ((amsmtt_flujos != null) && (ls_id != null)) ? amsmtt_flujos.get(ls_id) : null;
	}

	/**
	 * Adicionar flujo.
	 *
	 * @param amtt_flujo de amtt flujo
	 * @param amsmtt_flujos de amsmtt flujos
	 * @return el valor de map
	 */
	private Map<String, MotivoTramiteTrabajo> adicionarFlujo(
	    MotivoTramiteTrabajo amtt_flujo, Map<String, MotivoTramiteTrabajo> amsmtt_flujos
	)
	{
		boolean lb_flujo;
		String  ls_id;

		lb_flujo     = amtt_flujo != null;
		ls_id        = lb_flujo ? amtt_flujo.getId() : null;

		if(amsmtt_flujos == null)
			amsmtt_flujos = new HashMap<String, MotivoTramiteTrabajo>();

		if(StringUtils.isValidString(ls_id))
			amsmtt_flujos.put(ls_id, amtt_flujo);

		return amsmtt_flujos;
	}

	/**
	 * Adicionar flujos.
	 *
	 * @param amsmtt_nuevosFlujos de amsmtt nuevos flujos
	 * @param amsmtt_contenedorFlujos de amsmtt contenedor flujos
	 * @return el valor de map
	 */
	private Map<String, MotivoTramiteTrabajo> adicionarFlujos(
	    Map<String, MotivoTramiteTrabajo> amsmtt_nuevosFlujos, Map<String, MotivoTramiteTrabajo> amsmtt_contenedorFlujos
	)
	{
		if(amsmtt_contenedorFlujos == null)
			amsmtt_contenedorFlujos = new HashMap<String, MotivoTramiteTrabajo>();

		if(CollectionUtils.isValidCollection(amsmtt_nuevosFlujos))
			amsmtt_contenedorFlujos.putAll(amsmtt_nuevosFlujos);

		return amsmtt_contenedorFlujos;
	}

	/**
	 * Contar flujos.
	 *
	 * @param acmtt_flujosSalida de acmtt flujos salida
	 * @return el valor de int
	 */
	private int contarFlujos(Collection<MotivoTramiteTrabajo> acmtt_flujosSalida)
	{
		int li_flujos;

		li_flujos = 0;

		if(acmtt_flujosSalida != null)
		{
			for(MotivoTramiteTrabajo lmtt_flujo : acmtt_flujosSalida)
			{
				if(lmtt_flujo != null)
					li_flujos++;
			}
		}

		return li_flujos;
	}

	/**
	 * Contar flujos.
	 *
	 * @param amsmtt_flujosSalida de amsmtt flujos salida
	 * @return el valor de int
	 */
	private int contarFlujos(Map<String, MotivoTramiteTrabajo> amsmtt_flujosSalida)
	{
		return (amsmtt_flujosSalida != null) ? contarFlujos(amsmtt_flujosSalida.values()) : 0;
	}

	/**
	 * Contar flujos entrada.
	 */
	private void contarFlujosEntrada()
	{
		setCantidadFlujosEntrada(contarFlujos(getFlujosEntrada()));
	}

	/**
	 * Contar flujos salida.
	 */
	private void contarFlujosSalida()
	{
		setCantidadFlujosSalida(contarFlujos(getFlujosSalida()));
	}

	/**
	 * Eliminar flujo.
	 *
	 * @param amtt_flujo de amtt flujo
	 * @param amsmtt_flujos de amsmtt flujos
	 */
	private void eliminarFlujo(MotivoTramiteTrabajo amtt_flujo, Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		if(amtt_flujo != null)
			eliminarFlujo(amtt_flujo.getId(), amsmtt_flujos);
	}

	/**
	 * Eliminar flujo.
	 *
	 * @param as_idFlujo de as id flujo
	 * @param amsmtt_flujos de amsmtt flujos
	 */
	private void eliminarFlujo(String as_idFlujo, Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		if(CollectionUtils.isValidCollection(amsmtt_flujos) && StringUtils.isValidString(as_idFlujo))
			amsmtt_flujos.remove(as_idFlujo);
	}
}
