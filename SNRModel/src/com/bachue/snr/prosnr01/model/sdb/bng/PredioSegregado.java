package com.bachue.snr.prosnr01.model.sdb.bng;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_BNG_PREDIO_SEGREGADO.
 *
 * @author Julian Vaca
 */
public class PredioSegregado extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5117782184646016992L;
	/** Propiedad icap anotaciones. */
	Collection<AnotacionPredio> icap_anotaciones;
	/** Propiedad icap anotaciones segregadas. */
	Collection<AnotacionPredio> icap_anotacionesSegregadas;

	/** Propiedad ibd orden. */
	private BigDecimal ibd_orden;

	/** Propiedad il id anotacion. */
	private Long il_idAnotacion;

	/** Propiedad il id anotacion 1. */
	private Long il_idAnotacion1;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad il id matricula 1. */
	private Long il_idMatricula1;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id circulo 1. */
	private String is_idCirculo1;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is lote. */
	private String is_lote;

	/** Propiedad is numero predial. */
	private String is_numeroPredial;

	/** Propiedad ib anotaciones acc. */
	private boolean ib_AnotacionesAcc;

	/** Propiedad ib agregado. */
	private boolean ib_agregado;

	/** Propiedad ib editado. */
	private boolean ib_editado;

	/** Propiedad ib editar. */
	private boolean ib_editar;

	/** Propiedad ib eliminar. */
	private boolean ib_eliminar;

	/** Propiedad ib no validar documento. */
	private boolean ib_noValidarDocumento;

	/** Propiedad ib no validar segregacion. */
	private boolean ib_noValidarSegregacion;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad il id turno historia. */
	private long il_idTurnoHistoria;

	/**
	 *
	 * Constructor que recibe como parametros el objecto
	 * com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado
	 * @param aps_ps objeto tipo com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado
	 */
	public PredioSegregado(com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado aps_ps)
	{
		if(aps_ps != null)
		{
			is_descripcion         = aps_ps.getDescripcion();
			is_idCirculo           = aps_ps.getIdCirculo();
			is_idCirculo1          = aps_ps.getIdCirculo1();
			is_lote                = aps_ps.getLote();
			il_idAnotacion         = aps_ps.getIdAnotacion();
			il_idAnotacion1        = aps_ps.getIdAnotacion1();
			il_idMatricula         = NumericUtils.getLongWrapper(aps_ps.getIdMatricula());
			il_idMatricula1        = NumericUtils.getLongWrapper(aps_ps.getIdMatricula1());
			il_idTurnoHistoria     = aps_ps.getIdTurnoHistoria();
		}
	}

	/**
	 * Constructor por defecto.
	 */
	public PredioSegregado()
	{
	}

	/**
	 * Constructor que recibe como parametros el id del circulo, id de la matricula y el id de la anotacion.
	 *
	 * @param as_idCirculo id del circulo
	 * @param al_idMatricula id de la matricula
	 * @param al_idAnotacion id de la anotacion
	 */
	public PredioSegregado(String as_idCirculo, long al_idMatricula, Long al_idAnotacion)
	{
		setIdCirculo(as_idCirculo);
		setIdMatricula(NumericUtils.getLongWrapper(al_idMatricula));
		setIdAnotacion(al_idAnotacion);
	}

	/**
	 * Modifica el valor de Agregado.
	 *
	 * @param ab_b de ab b
	 */
	public void setAgregado(boolean ab_b)
	{
		ib_agregado = ab_b;
	}

	/**
	 * Valida la propiedad agregado.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public boolean isAgregado()
	{
		return ib_agregado;
	}

	/**
	 * Modifica el valor de Anotaciones.
	 *
	 * @param acap_cap de acap cap
	 */
	public void setAnotaciones(Collection<AnotacionPredio> acap_cap)
	{
		icap_anotaciones = acap_cap;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public Collection<AnotacionPredio> getAnotaciones()
	{
		return icap_anotaciones;
	}

	/**
	 * Modifica el valor de AnotacionesAcc.
	 *
	 * @param ab_b de ab b
	 */
	public void setAnotacionesAcc(boolean ab_b)
	{
		ib_AnotacionesAcc = ab_b;
	}

	/**
	 * Valida la propiedad anotaciones acc.
	 *
	 * @return Retorna el valor de la propiedad anotacionesBNG
	 */
	public boolean isAnotacionesAcc()
	{
		return ib_AnotacionesAcc;
	}

	/**
	 * Modifica el valor de AnotacionesSegregadas.
	 *
	 * @param acap_cap de acap cap
	 */
	public void setAnotacionesSegregadas(Collection<AnotacionPredio> acap_cap)
	{
		icap_anotacionesSegregadas = acap_cap;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones segregadas.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public Collection<AnotacionPredio> getAnotacionesSegregadas()
	{
		return icap_anotacionesSegregadas;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Editado.
	 *
	 * @param ab_B de ab B
	 */
	public void setEditado(boolean ab_B)
	{
		ib_editado = ab_B;
	}

	/**
	 * Valida la propiedad editado.
	 *
	 * @return retorna el valor de la propiedad
	 */
	public boolean isEditado()
	{
		return ib_editado;
	}

	/**
	 * Modifica el valor de Editar.
	 *
	 * @param ab_b de ab b
	 */
	public void setEditar(boolean ab_b)
	{
		ib_editar = ab_b;
	}

	/**
	 * Valida la propiedad editar.
	 *
	 * @return retorna el valor de la propiedad
	 */
	public boolean isEditar()
	{
		return ib_editar;
	}

	/**
	 * Modifica el valor de Eliminar.
	 *
	 * @param ab_b de ab b
	 */
	public void setEliminar(boolean ab_b)
	{
		ib_eliminar = ab_b;
	}

	/**
	 * Valida la propiedad eliminar.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public boolean isEliminar()
	{
		return ib_eliminar;
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion(Long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de IdAnotacion1.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion1(Long al_l)
	{
		il_idAnotacion1 = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdAnotacion1()
	{
		return il_idAnotacion1;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param idCirculo asigna el valor a la propiedad
	 */
	public void setIdCirculo(String idCirculo)
	{
		is_idCirculo = idCirculo;
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
	 * Modifica el valor de IdCirculo1.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo1(String as_s)
	{
		is_idCirculo1 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo1()
	{
		return is_idCirculo1;
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
	 * Modifica el valor de IdMatricula1.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula1(Long al_l)
	{
		il_idMatricula1 = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula1()
	{
		return il_idMatricula1;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de Lote.
	 *
	 * @param lote asigna el valor a la propiedad
	 */
	public void setLote(String lote)
	{
		is_lote = lote;
	}

	/**
	 * Retorna Objeto o variable de valor lote.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLote()
	{
		return is_lote;
	}

	/**
	 * Modifica el valor de NoValidarDocumento.
	 *
	 * @param ab_b de ab b
	 */
	public void setNoValidarDocumento(boolean ab_b)
	{
		ib_noValidarDocumento = ab_b;
	}

	/**
	 * Valida la propiedad no validar documento.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public boolean isNoValidarDocumento()
	{
		return ib_noValidarDocumento;
	}

	/**
	 * Modifica el valor de NoValidarSegregacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setNoValidarSegregacion(boolean ab_b)
	{
		ib_noValidarSegregacion = ab_b;
	}

	/**
	 * Valida la propiedad no validar segregacion.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public boolean isNoValidarSegregacion()
	{
		return ib_noValidarSegregacion;
	}

	/**
	 * Modifica el valor de NumeroPredial.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroPredial(String as_s)
	{
		is_numeroPredial = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero predial.
	 *
	 * @return Retorna el valor de la propiedad numeroPredial
	 */
	public String getNumeroPredial()
	{
		return is_numeroPredial;
	}

	/**
	 * Modifica el valor de Orden.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setOrden(BigDecimal abd_bd)
	{
		ibd_orden = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getOrden()
	{
		return ibd_orden;
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
}
