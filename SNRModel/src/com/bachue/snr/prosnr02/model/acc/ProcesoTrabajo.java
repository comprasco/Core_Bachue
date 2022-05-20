package com.bachue.snr.prosnr02.model.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr02.model.workflow.Compuerta;
import com.bachue.snr.prosnr02.model.workflow.ContenedorFlujos;
import com.bachue.snr.prosnr02.model.workflow.Evento;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



/**
 * Clase que contiene todos las propiedades ProcesoTrabajo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 18/05/2020
 */
public class ProcesoTrabajo extends ContenedorFlujos
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7119304072206268144L;

	/** Propiedad imsc compuertas. */
	private Map<String, Compuerta> imsc_compuertas;

	/** Propiedad imse eventos. */
	private Map<String, Evento> imse_eventos;

	/** Propiedad imset etapas. */
	private Map<String, EtapaTrabajo> imset_etapas;

	/** Propiedad is definicion. */
	private String is_definicion;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is recepcion documentos. */
	private String is_recepcionDocumentos;

	/** Propiedad ispt sub proceso. */
	private SubProcesoTrabajo ispt_subProceso;

	/** Propiedad ii sub proceso version. */
	private int ii_subProcesoVersion;

	/**
	 * Instancia un nuevo objeto proceso trabajo.
	 */
	public ProcesoTrabajo()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto proceso trabajo.
	 *
	 * @param as_id de as id
	 */
	public ProcesoTrabajo(String as_id)
	{
		setId(as_id);
		setTipo(TIPO_PROCESO);
	}

	/**
	 * Retorna Objeto o variable de valor coleccion compuertas.
	 *
	 * @return el valor de coleccion compuertas
	 */
	public Collection<Compuerta> getColeccionCompuertas()
	{
		return CollectionUtils.isValidCollection(imsc_compuertas) ? CollectionUtils.sort(imsc_compuertas.values()) : null;
	}

	/**
	 * Retorna Objeto o variable de valor coleccion etapas.
	 *
	 * @return el valor de coleccion etapas
	 */
	public Collection<EtapaTrabajo> getColeccionEtapas()
	{
		return CollectionUtils.isValidCollection(imset_etapas) ? CollectionUtils.sort(imset_etapas.values()) : null;
	}

	/**
	 * Retorna Objeto o variable de valor coleccion eventos.
	 *
	 * @return el valor de coleccion eventos
	 */
	public Collection<Evento> getColeccionEventos()
	{
		return CollectionUtils.isValidCollection(imse_eventos) ? CollectionUtils.sort(imse_eventos.values()) : null;
	}

	/**
	 * Retorna Objeto o variable de valor compuerta.
	 *
	 * @param as_id de as id
	 * @return el valor de compuerta
	 */
	public Compuerta getCompuerta(String as_id)
	{
		return ((imsc_compuertas != null) && (as_id != null)) ? imsc_compuertas.get(as_id) : null;
	}

	/**
	 * Cambia el valor de compuertas.
	 *
	 * @param amsc_msc de amsc msc
	 */
	public void setCompuertas(Map<String, Compuerta> amsc_msc)
	{
		imsc_compuertas = amsc_msc;
	}

	/**
	 * Retorna Objeto o variable de valor compuertas.
	 *
	 * @return el valor de compuertas
	 */
	public Map<String, Compuerta> getCompuertas()
	{
		return imsc_compuertas;
	}

	/**
	 * Modifica el valor de Definicion.
	 *
	 * @param as_s de as s
	 */
	public void setDefinicion(String as_s)
	{
		is_definicion = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor definicion.
	 *
	 * @return el valor de definicion
	 */
	public String getDefinicion()
	{
		return is_definicion;
	}

	/**
	 * Retorna Objeto o variable de valor etapa.
	 *
	 * @param as_id de as id
	 * @return el valor de etapa
	 */
	public EtapaTrabajo getEtapa(String as_id)
	{
		return ((imset_etapas != null) && (as_id != null)) ? imset_etapas.get(as_id) : null;
	}

	/**
	 * Cambia el valor de etapas.
	 *
	 * @param amset_mset de amset mset
	 */
	public void setEtapas(Map<String, EtapaTrabajo> amset_mset)
	{
		imset_etapas = amset_mset;
	}

	/**
	 * Retorna Objeto o variable de valor etapas.
	 *
	 * @return el valor de etapas
	 */
	public Map<String, EtapaTrabajo> getEtapas()
	{
		return imset_etapas;
	}

	/**
	 * Retorna Objeto o variable de valor evento.
	 *
	 * @param as_id de as id
	 * @return el valor de evento
	 */
	public Evento getEvento(String as_id)
	{
		return ((imse_eventos != null) && (as_id != null)) ? imse_eventos.get(as_id) : null;
	}

	/**
	 * Cambia el valor de eventos.
	 *
	 * @param amse_mse de amse mse
	 */
	public void setEventos(Map<String, Evento> amse_mse)
	{
		imse_eventos = amse_mse;
	}

	/**
	 * Retorna Objeto o variable de valor eventos.
	 *
	 * @return el valor de eventos
	 */
	public Map<String, Evento> getEventos()
	{
		return imse_eventos;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de RecepcionDocumentos.
	 *
	 * @param as_s de as s
	 */
	public void setRecepcionDocumentos(String as_s)
	{
		is_recepcionDocumentos = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor recepcion documentos.
	 *
	 * @return el valor de recepcion documentos
	 */
	public String getRecepcionDocumentos()
	{
		return is_recepcionDocumentos;
	}

	/**
	 * Modifica el valor de SubProceso.
	 *
	 * @param aspt_spt de aspt spt
	 */
	public void setSubProceso(SubProcesoTrabajo aspt_spt)
	{
		ispt_subProceso = aspt_spt;
	}

	/**
	 * Retorna Objeto o variable de valor sub proceso.
	 *
	 * @return el valor de sub proceso
	 */
	public SubProcesoTrabajo getSubProceso()
	{
		return ispt_subProceso;
	}

	/**
	 * Modifica el valor de SubProcesoVersion.
	 *
	 * @param ai_i de ai i
	 */
	public void setSubProcesoVersion(int ai_i)
	{
		ii_subProcesoVersion = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor sub proceso version.
	 *
	 * @return el valor de sub proceso version
	 */
	public int getSubProcesoVersion()
	{
		return ii_subProcesoVersion;
	}

	/**
	 * Adicionar compuerta.
	 *
	 * @param ac_c de ac c
	 */
	public void adicionarCompuerta(Compuerta ac_c)
	{
		boolean lb_compuerta;
		String  ls_id;

		lb_compuerta     = ac_c != null;
		ls_id            = lb_compuerta ? ac_c.getId() : null;

		if(StringUtils.isValidString(ls_id))
		{
			if(imsc_compuertas == null)
				imsc_compuertas = new HashMap<String, Compuerta>();

			imsc_compuertas.put(ls_id, ac_c);
		}
	}

	/**
	 * Adicionar etapa.
	 *
	 * @param aet_etapa de aet etapa
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void adicionarEtapa(EtapaTrabajo aet_etapa)
	    throws B2BException
	{
		boolean lb_etapa;
		String  ls_id;

		lb_etapa     = aet_etapa != null;
		ls_id        = lb_etapa ? StringUtils.getString(aet_etapa.getIdEtapa()) : null;

		if(StringUtils.isValidString(ls_id))
		{
			EtapaTrabajo let_anterior;

			if(imset_etapas == null)
				imset_etapas = new HashMap<String, EtapaTrabajo>();

			let_anterior = imset_etapas.get(ls_id);

			if(let_anterior == null)
				imset_etapas.put(ls_id, aet_etapa);
			else
				confirmarEtapaDuplicadaValidad(let_anterior, aet_etapa);
		}
	}

	/**
	 * Adicionar evento.
	 *
	 * @param ae_e de ae e
	 */
	public void adicionarEvento(Evento ae_e)
	{
		boolean lb_evento;
		String  ls_id;

		lb_evento     = ae_e != null;
		ls_id         = lb_evento ? ae_e.getId() : null;

		if(StringUtils.isValidString(ls_id))
		{
			if(imse_eventos == null)
				imse_eventos = new HashMap<String, Evento>();

			imse_eventos.put(ls_id, ae_e);
		}
	}

	/** {@inheritdoc} */
	public String toString()
	{
		StringBuilder lsb_sb;

		lsb_sb = new StringBuilder(super.toString());

		return lsb_sb.toString();
	}

	/**
	 * Confirmar etapa duplicada validad.
	 *
	 * @param aet_anterior de aet anterior
	 * @param aet_nueva de aet nueva
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void confirmarEtapaDuplicadaValidad(EtapaTrabajo aet_anterior, EtapaTrabajo aet_nueva)
	    throws B2BException
	{
		if((aet_anterior != null) && (aet_nueva != null))
		{
			boolean lb_validar;
			long    ll_id;

			lb_validar     = true;
			ll_id          = -1L;

			{
				long ll_anterior;
				long ll_nueva;

				ll_anterior     = aet_anterior.getIdEtapa();
				ll_nueva        = aet_nueva.getIdEtapa();

				if(ll_anterior == ll_nueva)
					ll_id = ll_anterior;
				else
				{
					StringBuilder lsb_error;

					lsb_error = new StringBuilder();

					lsb_error.append("La etapa con id ");
					lsb_error.append(ll_anterior);
					lsb_error.append(" no coincide con la etapa con id ");
					lsb_error.append(ll_nueva);

					throw new B2BException(lsb_error.toString());
				}
			}

			if(lb_validar)
			{
				{
					boolean    lb_anterior;
					boolean    lb_nueva;
					BigDecimal lbd_anterior;
					BigDecimal lbd_nueva;

					lbd_anterior     = aet_anterior.getCantidadTiempoEspera();
					lbd_nueva        = aet_nueva.getCantidadTiempoEspera();
					lb_anterior      = lbd_anterior != null;
					lb_nueva         = lbd_nueva != null;

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !lbd_anterior.equals(lbd_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintas cantidades de tiempo de espera");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getDescripcion();
					ls_nueva        = aet_nueva.getDescripcion();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintas descripciones");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean    lb_anterior;
					boolean    lb_nueva;
					BigDecimal lbd_anterior;
					BigDecimal lbd_nueva;

					lbd_anterior     = aet_anterior.getDiasHabilesNormal();
					lbd_nueva        = aet_nueva.getDiasHabilesNormal();
					lb_anterior      = lbd_anterior != null;
					lb_nueva         = lbd_nueva != null;

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !lbd_anterior.equals(lbd_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintas cantidades de días hábiles normal");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getEstado();
					ls_nueva        = aet_nueva.getEstado();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos estados");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					long ll_anterior;
					long ll_nueva;

					ll_anterior     = aet_anterior.getIdFase();
					ll_nueva        = aet_nueva.getIdFase();

					if(ll_anterior != ll_nueva)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintas fases");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getIdUnidadTiempoEspera();
					ls_nueva        = aet_nueva.getIdUnidadTiempoEspera();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos id de unidad de medida");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getIndicadorBloqueo();
					ls_nueva        = aet_nueva.getIndicadorBloqueo();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos indicadores de bloqueo");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getIndicadorDesborde();
					ls_nueva        = aet_nueva.getIndicadorDesborde();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos indicadores de desborde");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getIndicadorPeso();
					ls_nueva        = aet_nueva.getIndicadorPeso();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos indicadores de peso");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getIndicadorTope();
					ls_nueva        = aet_nueva.getIndicadorTope();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos indicadores de tope");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getNombre();
					ls_nueva        = aet_nueva.getNombre();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos nombres");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getTiempoEspera();
					ls_nueva        = aet_nueva.getTiempoEspera();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos tiempos de espera");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;
					String  ls_anterior;
					String  ls_nueva;

					ls_anterior     = aet_anterior.getTipoReparto();
					ls_nueva        = aet_nueva.getTipoReparto();
					lb_anterior     = StringUtils.isValidString(ls_anterior);
					lb_nueva        = StringUtils.isValidString(ls_nueva);

					if(
					    (lb_anterior && !lb_nueva) || (!lb_anterior && lb_nueva)
						    || (lb_anterior && lb_nueva && !ls_anterior.equals(ls_nueva))
					)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos tipos de reparto");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					boolean lb_anterior;
					boolean lb_nueva;

					lb_anterior     = aet_anterior.isGeneraQR();
					lb_nueva        = aet_nueva.isGeneraQR();

					if(lb_anterior != lb_nueva)
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("Existe mas de una etapa con id ");
						lsb_error.append(ll_id);
						lsb_error.append(", pero tienen distintos indicadores de generación de código QR");

						throw new B2BException(lsb_error.toString());
					}
				}
			}
		}
	}
}
