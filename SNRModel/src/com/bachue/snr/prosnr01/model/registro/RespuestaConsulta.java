package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades de la tabla SDB_PGN_RESULTADO_CONSULTA.
 *
 * @author Julian Vaca
 */
public class RespuestaConsulta extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7122932727727887764L;

	/** Propiedad il consecutivo consulta. */
	private Long il_consecutivoConsulta;

	/** Propiedad il consecutivo consulta detalle. */
	private Long il_consecutivoConsultaDetalle;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad is criterio seleccion. */
	private String is_criterioSeleccion;

	/** Propiedad is direccion. */
	private String is_direccion;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id direccion. */
	private String is_idDireccion;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id oficina origen. */
	private String is_idOficinaOrigen;

	/** Propiedad is id persona. */
	private String is_idPersona;

	/** Propiedad is id proceso consulta. */
	private String is_idProcesoConsulta;

	/** Propiedad is id resultado consulta. */
	private String is_idResultadoConsulta;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id testamento. */
	private String is_idTestamento;

	/** Propiedad is id tipo criterio busqueda. */
	private String is_idTipoCriterioBusqueda;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is nombre departamento circulo. */
	private String is_nombreDepartamentoCirculo;

	/** Propiedad is nombre municipio circulo. */
	private String is_nombreMunicipioCirculo;

	/** Propiedad is numero predial. */
	private String is_numeroPredial;

	/** Propiedad is nupre. */
	private String is_nupre;

	/** Propiedad is respuesta. */
	private String is_respuesta;

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
	 * Retorna Objeto o variable de valor direccion.
	 *
	 * @return Retorna el valor de la propiedad direccion
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Modifica el valor de Direccion.
	 *
	 * @param as_s de as s
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return Retorna el valor de la propiedad nombreCirculo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre departamento circulo.
	 *
	 * @return Retorna el valor de la propiedad nombreDepartamentoCirculo
	 */
	public String getNombreDepartamentoCirculo()
	{
		return is_nombreDepartamentoCirculo;
	}

	/**
	 * Modifica el valor de NombreDepartamentoCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreDepartamentoCirculo(String as_s)
	{
		is_nombreDepartamentoCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre municipio circulo.
	 *
	 * @return Retorna el valor de la propiedad nombreMunicipioCirculo
	 */
	public String getNombreMunicipioCirculo()
	{
		return is_nombreMunicipioCirculo;
	}

	/**
	 * Modifica el valor de NombreMunicipioCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreMunicipioCirculo(String as_s)
	{
		is_nombreMunicipioCirculo = as_s;
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
	 * Modifica el valor de NumeroPredial.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroPredial(String as_s)
	{
		is_numeroPredial = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nupre.
	 *
	 * @return Retorna el valor de la propiedad nupre
	 */
	public String getNupre()
	{
		return is_nupre;
	}

	/**
	 * Modifica el valor de Nupre.
	 *
	 * @param as_s de as s
	 */
	public void setNupre(String as_s)
	{
		is_nupre = as_s;
	}

	/**
	 * Modifica el valor de consecutivo consulta.
	 *
	 * @param al_l asigna el valor a la propiedad consecutivo consulta
	 */
	public void setConsecutivoConsulta(Long al_l)
	{
		il_consecutivoConsulta = al_l;
	}

	/**
	 * Retorna el valor de consecutivo consulta.
	 *
	 * @return el valor de consecutivo consulta
	 */
	public Long getConsecutivoConsulta()
	{
		return il_consecutivoConsulta;
	}

	/**
	 * Modifica el valor de consecutivo consulta detalle.
	 *
	 * @param al_l asigna el valor a la propiedad consecutivo consulta detalle
	 */
	public void setConsecutivoConsultaDetalle(Long al_l)
	{
		il_consecutivoConsultaDetalle = al_l;
	}

	/**
	 * Retorna el valor de consecutivo consulta detalle.
	 *
	 * @return el valor de consecutivo consulta detalle
	 */
	public Long getConsecutivoConsultaDetalle()
	{
		return il_consecutivoConsultaDetalle;
	}

	/**
	 * Modifica el valor de criterio seleccion.
	 *
	 * @param as_s asigna el valor a la propiedad criterio seleccion
	 */
	public void setCriterioSeleccion(String as_s)
	{
		is_criterioSeleccion = as_s;
	}

	/**
	 * Retorna el valor de criterio seleccion.
	 *
	 * @return el valor de criterio seleccion
	 */
	public String getCriterioSeleccion()
	{
		return is_criterioSeleccion;
	}

	/**
	 * Modifica el valor de estado.
	 *
	 * @param as_s asigna el valor a la propiedad estado
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Retorna el valor de estado.
	 *
	 * @return el valor de estado
	 */
	public String getEstado()
	{
		return is_estado;
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
	 * Modifica el valor de id direccion.
	 *
	 * @param as_s asigna el valor a la propiedad id direccion
	 */
	public void setIdDireccion(String as_s)
	{
		is_idDireccion = as_s;
	}

	/**
	 * Retorna el valor de id direccion.
	 *
	 * @return el valor de id direccion
	 */
	public String getIdDireccion()
	{
		return is_idDireccion;
	}

	/**
	 * Modifica el valor de id matricula.
	 *
	 * @param al_l asigna el valor a la propiedad id matricula
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna el valor de id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de id oficina origen.
	 *
	 * @param as_s asigna el valor a la propiedad id oficina origen
	 */
	public void setIdOficinaOrigen(String as_s)
	{
		is_idOficinaOrigen = as_s;
	}

	/**
	 * Retorna el valor de id oficina origen.
	 *
	 * @return el valor de id oficina origen
	 */
	public String getIdOficinaOrigen()
	{
		return is_idOficinaOrigen;
	}

	/**
	 * Modifica el valor de id persona.
	 *
	 * @param as_s asigna el valor a la propiedad id persona
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna el valor de id persona.
	 *
	 * @return el valor de id persona
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de id proceso consulta.
	 *
	 * @param as_s asigna el valor a la propiedad id proceso consulta
	 */
	public void setIdProcesoConsulta(String as_s)
	{
		is_idProcesoConsulta = as_s;
	}

	/**
	 * Retorna el valor de id proceso consulta.
	 *
	 * @return el valor de id proceso consulta
	 */
	public String getIdProcesoConsulta()
	{
		return is_idProcesoConsulta;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id testamento.
	 *
	 * @param as_s asigna el valor a la propiedad id testamento
	 */
	public void setIdTestamento(String as_s)
	{
		is_idTestamento = as_s;
	}

	/**
	 * Retorna el valor de id testamento.
	 *
	 * @return el valor de id testamento
	 */
	public String getIdTestamento()
	{
		return is_idTestamento;
	}

	/**
	 * Modifica el valor de id tipo criterio busqueda.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo criterio busqueda
	 */
	public void setIdTipoCriterioBusqueda(String as_s)
	{
		is_idTipoCriterioBusqueda = as_s;
	}

	/**
	 * Retorna el valor de id tipo criterio busqueda.
	 *
	 * @return el valor de id tipo criterio busqueda
	 */
	public String getIdTipoCriterioBusqueda()
	{
		return is_idTipoCriterioBusqueda;
	}

	/**
	 * Modifica el valor de respuesta.
	 *
	 * @param as_s asigna el valor a la propiedad respuesta
	 */
	public void setRespuesta(String as_s)
	{
		is_respuesta = as_s;
	}

	/**
	 * Retorna el valor de respuesta.
	 *
	 * @return el valor de respuesta
	 */
	public String getRespuesta()
	{
		return is_respuesta;
	}

	/**
	 * Retorna Objeto o variable de valor id resultado consulta.
	 *
	 * @return Retorna el valor de la propiedad idResultadoConsulta
	 */
	public String getIdResultadoConsulta()
	{
		return is_idResultadoConsulta;
	}

	/**
	 * Modifica el valor de IdResultadoConsulta.
	 *
	 * @param as_s de as s
	 */
	public void setIdResultadoConsulta(String as_s)
	{
		is_idResultadoConsulta = as_s;
	}
}
