package com.bachue.snr.prosnr01.model.sdb.pgn;

import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoEntradaConsultarOficinasOrigenTipo;

import com.b2bsg.common.util.StringUtils;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_OFICINA_ORIGEN.
 *
 * @author Julian Vaca
 */
public class OficinaOrigen extends Pais implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1799828629252670555L;

	/** Propiedad ibd version. */
	private BigDecimal ibd_version;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is direccion. */
	private String is_direccion;

	/** Propiedad is fax. */
	private String is_fax;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id oficina origen. */
	private String is_idOficinaOrigen;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id tipo entidad. */
	private String is_idTipoEntidad;

	/** Propiedad is id tipo oficina. */
	private String is_idTipoOficina;

	/** Propiedad is id unico. */
	private String is_idUnico;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is id vereda. */
	private String is_idVereda;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nombre titular. */
	private String is_nombreTitular;

	/** Propiedad is notificar correspondencia. */
	private String is_notificarCorrespondencia;

	/** Propiedad is numero. */
	private String is_numero;

	/** Propiedad is telefono. */
	private String is_telefono;

	/** Propiedad ib id departamento valid. */
	private boolean ib_idDepartamentoValid;

	/** Propiedad ib id municipio valid. */
	private boolean ib_idMunicipioValid;

	/** Propiedad ib id oficina origen valid. */
	private boolean ib_idOficinaOrigenValid;

	/** Propiedad ib id pais valid. */
	private boolean ib_idPaisValid;

	/** Propiedad ib tipo oficina valid. */
	private boolean ib_tipoOficinaValid;

	/**
	 * Constructor por defecto.
	 */
	public OficinaOrigen()
	{
	}

	/**
	 * Constructor que resive como parametro sin informacion.
	 *
	 * @param as_s de as sin informacion
	 */
	public OficinaOrigen(String as_s)
	{
		setNombre(as_s);
	}

	/**
	 * Instancia un nuevo objeto oficina origen.
	 *
	 * @param atecoot_entrada de atecoot entrada
	 */
	public OficinaOrigen(TipoEntradaConsultarOficinasOrigenTipo atecoot_entrada)
	{
		if(atecoot_entrada != null)
		{
			is_nombre              = atecoot_entrada.getNomParcialOficinaOrigen();
			is_idTipoOficina       = atecoot_entrada.getTipoOficinaOrigen();
			is_idOficinaOrigen     = atecoot_entrada.getCodigoOficinaOrigen();
		}
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de Direccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Modifica el valor de Fax.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setFax(String as_s)
	{
		is_fax = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fax.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getFax()
	{
		return is_fax;
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
	 * Modifica el valor de IdDepartamentoValid.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setIdDepartamentoValid(boolean ab_b)
	{
		ib_idDepartamentoValid = ab_b;
	}

	/**
	 * Valida la propiedad id departamento valid.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isIdDepartamentoValid()
	{
		return ib_idDepartamentoValid;
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
	 * Modifica el valor de IdMunicipioValid.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setIdMunicipioValid(boolean ab_b)
	{
		ib_idMunicipioValid = ab_b;
	}

	/**
	 * Valida la propiedad id municipio valid.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isIdMunicipioValid()
	{
		return ib_idMunicipioValid;
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
	 * Modifica el valor de IdPaisValid.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setIdPaisValid(boolean ab_b)
	{
		ib_idPaisValid = ab_b;
	}

	/**
	 * Valida la propiedad id pais valid.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isIdPaisValid()
	{
		return ib_idPaisValid;
	}

	/**
	 * Modifica el valor de IdTipoEntidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo entidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
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
	 * Modifica el valor de IdUnico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUnico(String as_s)
	{
		is_idUnico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id unico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUnico()
	{
		return is_idUnico;
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
	 * Modifica el valor de IdVereda.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdVereda(String as_s)
	{
		is_idVereda = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id vereda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdVereda()
	{
		return is_idVereda;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
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
	 * Modifica el valor de NombreTitular.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreTitular(String as_s)
	{
		is_nombreTitular = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre titular.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTitular()
	{
		return is_nombreTitular;
	}

	/**
	 * Modifica el valor de NotificarCorrespondencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNotificarCorrespondencia(String as_s)
	{
		is_notificarCorrespondencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor notificar correspondencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNotificarCorrespondencia()
	{
		if(!StringUtils.isValidString(is_notificarCorrespondencia))
			is_notificarCorrespondencia = "";

		return is_notificarCorrespondencia;
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
	 * Modifica el valor de Telefono.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTelefono(String as_s)
	{
		is_telefono = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor telefono.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTelefono()
	{
		return is_telefono;
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
}
