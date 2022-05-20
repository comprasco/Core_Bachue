package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_ANOTACION_PREDIO_CIUDADANO.
 *
 * @author ccalderon
 */
public class AnotacionPredioCiudadano extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8661759776784245220L;

	/** Propiedad is documento. */
	private String is_documento;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id anotacion predio. */
	private String is_idAnotacionPredio;

	/** Propiedad is id anotacion predio ciudadano. */
	private String is_idAnotacionPredioCiudadano;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id interesada rrr. */
	private String is_idInteresadaRrr;

	/** Propiedad is id persona. */
	private String is_idPersona;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is nombre interesada RRR. */
	private String is_nombreInteresadaRRR;

	/** Propiedad is nombre persona. */
	private String is_nombrePersona;

	/** Propiedad is porcentaje participacion. */
	private String is_porcentajeParticipacion;

	/** Propiedad is propietario. */
	private String is_propietario;

	/** Propiedad is rol persona. */
	private String is_rolPersona;

	/** Propiedad is tipo documento. */
	private String is_tipoDocumento;

	/** Propiedad il id anotacion. */
	private long il_idAnotacion;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/**
	 * Constructor por defecto.
	 */
	public AnotacionPredioCiudadano()
	{
	}

	/**
	 * Constructor que recibe como paramatro el id anotacion predio.
	 *
	 * @param as_idAnotacionPredio Variable que contiene el id anotacion predio.
	 */
	public AnotacionPredioCiudadano(String as_idAnotacionPredio)
	{
		is_idAnotacionPredio = as_idAnotacionPredio;
	}

	/**
	 * Constructor que recibe por parametro un objeto de tipo Anotacion Predio Ciudadano.
	 *
	 * @param aapc_apc objeto anotacion predio ciudadano
	 * @see com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano(AnotacionPredioCiudadano aapc_apc)
	{
		if(aapc_apc != null)
		{
			is_documento                      = aapc_apc.getDocumento();
			is_estado                         = aapc_apc.getEstado();
			is_idAnotacionPredio              = aapc_apc.getIdAnotacionPredio();
			is_idAnotacionPredioCiudadano     = aapc_apc.getIdAnotacionPredioCiudadano();
			is_idCirculo                      = aapc_apc.getIdCirculo();
			is_idInteresadaRrr                = aapc_apc.getIdInteresadaRrr();
			is_idPersona                      = aapc_apc.getIdPersona();
			is_idTurno                        = aapc_apc.getIdTurno();
			is_idUsuario                      = aapc_apc.getIdUsuario();
			is_nombreInteresadaRRR            = aapc_apc.getNombreInteresadaRRR();
			is_nombrePersona                  = aapc_apc.getNombrePersona();
			is_porcentajeParticipacion        = aapc_apc.getPorcentajeParticipacion();
			is_propietario                    = aapc_apc.getPropietario();
			is_rolPersona                     = aapc_apc.getRolPersona();
			is_tipoDocumento                  = aapc_apc.getTipoDocumento();
			il_idAnotacion                    = aapc_apc.getIdAnotacion();
			il_idMatricula                    = aapc_apc.getIdMatricula();
		}
	}

	/**
	 * Constructor que recibe por parametro un objeto de tipo Anotacion Predio Ciudadano.
	 *
	 * @param aapc_apc objeto anotacion predio ciudadano
	 * @see com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano(com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano aapc_apc)
	{
		if(aapc_apc != null)
		{
			setIdCirculo(aapc_apc.getIdCirculo());
			setIdMatricula(aapc_apc.getIdMatricula());
			setIdAnotacion(aapc_apc.getIdAnotacion());
			setIdPersona(aapc_apc.getIdPersona());
			setRolPersona(aapc_apc.getRolPersona());
			setPropietario(aapc_apc.getPropietario());
			setPorcentajeParticipacion(aapc_apc.getPorcentajeParticipacion());
			setIdInteresadaRrr(aapc_apc.getIdInteresadaRrr());
		}
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
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion(long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de IdAnotacionPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAnotacionPredio(String as_s)
	{
		is_idAnotacionPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAnotacionPredio()
	{
		return is_idAnotacionPredio;
	}

	/**
	 * Modifica el valor de IdAnotacionPredioCiudadano.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAnotacionPredioCiudadano(String as_s)
	{
		is_idAnotacionPredioCiudadano = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion predio ciudadano.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAnotacionPredioCiudadano()
	{
		return is_idAnotacionPredioCiudadano;
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
	 * Modifica el valor de IdInteresadaRrr.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdInteresadaRrr(String as_s)
	{
		is_idInteresadaRrr = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id interesada rrr.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdInteresadaRrr()
	{
		return is_idInteresadaRrr;
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
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersona()
	{
		return is_idPersona;
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
	 * Modifica el valor de NombreInteresadaRRR.
	 *
	 * @param as_n de as n
	 */
	public void setNombreInteresadaRRR(String as_n)
	{
		is_nombreInteresadaRRR = as_n;
	}

	/**
	 * Retorna Objeto o variable de valor nombre interesada RRR.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreInteresadaRRR()
	{
		return is_nombreInteresadaRRR;
	}

	/**
	 * Modifica el valor de NombrePersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombrePersona(String as_s)
	{
		is_nombrePersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePersona()
	{
		return is_nombrePersona;
	}

	/**
	 * Modifica el valor de PorcentajeParticipacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPorcentajeParticipacion(String as_s)
	{
		is_porcentajeParticipacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor porcentaje participacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPorcentajeParticipacion()
	{
		return is_porcentajeParticipacion;
	}

	/**
	 * Modifica el valor de Propietario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPropietario(String as_s)
	{
		is_propietario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor propietario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPropietario()
	{
		return is_propietario;
	}

	/**
	 * Modifica el valor de RolPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRolPersona(String as_s)
	{
		is_rolPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor rol persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRolPersona()
	{
		return is_rolPersona;
	}

	/**
	 * Modifica el valor de TipoDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}
}
