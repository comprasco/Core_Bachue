package com.bachue.snr.prosnr01.model.sdb.acc;

import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEmpresaAEI;

import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;

import java.io.Serializable;



/**
 * Clase para la abstracción de la tabla SDB_ACC_ENTIDAD_EXTERNA.
 *
 * @author Manuel Blanco
 */
public class AccEntidadExterna extends Pais implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 8129898381398010219L;

	/**  Propiedad is_activo. */
	private String is_activo;

	/**  Propiedad is_correoElectronico. */
	private String is_correoElectronico;

	/**  Propiedad is_direccion. */
	private String is_direccion;

	/**  Propiedad is_numeroDocumento. */
	private String is_entidadExenta;

	/**  Propiedad is_idActividadEconomica. */
	private String is_idActividadEconomica;

	/**  Propiedad is_idDepartamento. */
	private String is_idDepartamento;

	/**  Propiedad is_idDocumentoTipo. */
	private String is_idDocumentoTipo;

	/**  Propiedad is_idEntidadExterna. */
	private String is_idEntidadExterna;

	/**  Propiedad is_idMunicipio. */
	private String is_idMunicipio;

	/**  Propiedad is_idPais. */
	private String is_idPais;

	/**  Propiedad is_idRepresentanteLegal. */
	private String is_idRepresentanteLegal;

	/**  Propiedad is_idTipoEntidad. */
	private String is_idTipoEntidad;

	/**  Propiedad is_idTipoOficina. */
	private String is_idTipoOficina;

	/** Propiedad is_indicativoTelefono */
	private String is_indicativoTelefono;

	/**  Propiedad is_nombre. */
	private String is_nombre;

	/**  Propiedad is nombre departamento. */
	private String is_nombreDepartamento;

	/**  Propiedad is nombre municipio. */
	private String is_nombreMunicipio;

	/**  Propiedad is nombre pais. */
	private String is_nombrePais;

	/**  Propiedad is_nombreRepresentanteLegal. */
	private String is_nombreRepresentanteLegal;

	/**  Propiedad is_nombreTipoEntidad. */
	private String is_nombreTipoEntidad;

	/**  Propiedad is_numeroDocumento. */
	private String is_numeroDocumento;

	/**  Propiedad is_telefono. */
	private String is_telefono;

	/**
	 * Constructor por defecto.
	 */
	public AccEntidadExterna()
	{
	}
	
	/**
	 * Instancia un nuevo objeto acc entidad externa.
	 *
	 * @param as_idEntidadExterna de as id entidad externa
	 */
	public AccEntidadExterna(String as_idEntidadExterna)
	{
		is_idEntidadExterna = as_idEntidadExterna;
	}

	/**
	 * Constructor para inicializar el objeto a partir de un objeto TipoEmpresaAEI.
	 *
	 * @param ateaei_empresa Objeto contenedor de los valores a asociar a la instancia de TipoEmpresaAEI
	 */
	public AccEntidadExterna(TipoEmpresaAEI ateaei_empresa)
	{
		if(ateaei_empresa != null)
		{
			is_idDocumentoTipo          = ateaei_empresa.getTipoDocumentoEmpresa();
			is_numeroDocumento          = ateaei_empresa.getNumeroDocumentoEmpresa();
			is_nombre                   = ateaei_empresa.getRazonSocialEmpresa();
			is_idActividadEconomica     = ateaei_empresa.getActividadEconomica();
			is_idTipoEntidad            = ateaei_empresa.getTipoEntidadEmpresa();
			is_idPais                   = ateaei_empresa.getPaisEmpresa();
			is_idDepartamento           = ateaei_empresa.getDepartamentoEmpresa();
			is_idMunicipio              = ateaei_empresa.getMunicipioEmpresa();
			is_telefono                 = ateaei_empresa.getTelefonoEmpresa();
		}
	}

	/**
	 * Modifica el valor de is_activo.
	 *
	 * @param as_s asigna el valor a la propiedad is_activo
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna el valor de is_activo.
	 *
	 * @return el valor de is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_s de as s
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return Retorna el valor de la propiedad correoElectronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de is_entidadExenta.
	 *
	 * @param as_s asigna el valor a la propiedad is_entidadExenta
	 */
	public void setEntidadExenta(String as_s)
	{
		is_entidadExenta = as_s;
	}

	/**
	 * Retorna el valor de is_entidadExenta.
	 *
	 * @return el valor de is_entidadExenta
	 */
	public String getEntidadExenta()
	{
		return is_entidadExenta;
	}

	/**
	 * Modifica el valor de is_idActividadEconomica.
	 *
	 * @param as_s asigna el valor a la propiedad is_idActividadEconomica
	 */
	public void setIdActividadEconomica(String as_s)
	{
		is_idActividadEconomica = as_s;
	}

	/**
	 * Retorna el valor de is_idActividadEconomica.
	 *
	 * @return el valor de is_idActividadEconomica
	 */
	public String getIdActividadEconomica()
	{
		return is_idActividadEconomica;
	}

	/**
	 * Modifica el valor de is_idDepartamento.
	 *
	 * @param as_s asigna el valor a la propiedad is_idDepartamento
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna el valor de is_idDepartamento.
	 *
	 * @return el valor de is_idDepartamento
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de is_idDocumentoTipo.
	 *
	 * @param as_s asigna el valor a la propiedad is_idDocumentoTipo
	 */
	public void setIdDocumentoTipo(String as_s)
	{
		is_idDocumentoTipo = as_s;
	}

	/**
	 * Retorna el valor de is_idDocumentoTipo.
	 *
	 * @return el valor de is_idDocumentoTipo
	 */
	public String getIdDocumentoTipo()
	{
		return is_idDocumentoTipo;
	}

	/**
	 * Modifica el valor de is_idEntidadExterna.
	 *
	 * @param as_s asigna el valor a la propiedad is_idEntidadExterna
	 */
	public void setIdEntidadExterna(String as_s)
	{
		is_idEntidadExterna = as_s;
	}

	/**
	 * Retorna el valor de is_idEntidadExterna.
	 *
	 * @return el valor de is_idEntidadExterna
	 */
	public String getIdEntidadExterna()
	{
		return is_idEntidadExterna;
	}

	/**
	 * Modifica el valor de is_idMunicipio.
	 *
	 * @param as_s asigna el valor a la propiedad is_idMunicipio
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna el valor de is_idMunicipio.
	 *
	 * @return el valor de is_idMunicipio
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de is_idPais.
	 *
	 * @param as_s asigna el valor a la propiedad is_idPais
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna el valor de is_idPais.
	 *
	 * @return el valor de is_idPais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de is_idRepresentanteLegal.
	 *
	 * @param as_s asigna el valor a la propiedad is_idRepresentanteLegal
	 */
	public void setIdRepresentanteLegal(String as_s)
	{
		is_idRepresentanteLegal = as_s;
	}

	/**
	 * Retorna el valor de is_idRepresentanteLegal.
	 *
	 * @return el valor de is_idRepresentanteLegal
	 */
	public String getIdRepresentanteLegal()
	{
		return is_idRepresentanteLegal;
	}

	/**
	 * Modifica el valor de is_nombreRepresentanteLegal.
	 *
	 * @param as_s asigna el valor a la propiedad is_nombreRepresentanteLegal
	 */
	public void setNombreRepresentanteLegal(String as_s)
	{
		is_nombreRepresentanteLegal = as_s;
	}

	/**
	 * Retorna el valor de is_nombreRepresentanteLegal.
	 *
	 * @return el valor de is_nombreRepresentanteLegal
	 */
	public String getNombreRepresentanteLegal()
	{
		return is_nombreRepresentanteLegal;
	}

	/**
	 * Modifica el valor de is_idTipoEntidad.
	 *
	 * @param as_s asigna el valor a la propiedad is_idTipoEntidad
	 */
	public void setIdTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de is_idTipoEntidad.
	 *
	 * @return el valor de is_idTipoEntidad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Modifica el valor de is_nombreTipoEntidad.
	 *
	 * @param as_s asigna el valor a la propiedad is_nombreTipoEntidad
	 */
	public void setNombreTipoEntidad(String as_s)
	{
		is_nombreTipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de is_nombreTipoEntidad.
	 *
	 * @return el valor de is_nombreTipoEntidad
	 */
	public String getNombreTipoEntidad()
	{
		return is_nombreTipoEntidad;
	}

	/**
	 * Modifica el valor de is_idTipoOficina.
	 *
	 * @param as_s asigna el valor a la propiedad is_idTipoOficina
	 */
	public void setIdTipoOficina(String as_s)
	{
		is_idTipoOficina = as_s;
	}

	/**
	 * Retorna el valor de is_idTipoOficina.
	 *
	 * @return el valor de is_idTipoOficina
	 */
	public String getIdTipoOficina()
	{
		return is_idTipoOficina;
	}

	/**
	 * Modifica el valor de is_nombre.
	 *
	 * @param nombre de nombre
	 */
	public void setNombre(String nombre)
	{
		is_nombre = nombre;
	}

	/**
	 * Retorna el valor de is_nombre.
	 *
	 * @return el valor de is_nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de is_numeroDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad is_numeroDocumento
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = as_s;
	}

	/**
	 * Retorna el valor de is_numeroDocumento.
	 *
	 * @return el valor de is_numeroDocumento
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
	}

	/**
	 * Modifica el valor de is_telefono.
	 *
	 * @param as_s asigna el valor a la propiedad is_telefono
	 */
	public void setTelefono(String as_s)
	{
		is_telefono = as_s;
	}

	/**
	 * Retorna el valor de is_telefono.
	 *
	 * @return el valor de is_telefono
	 */
	public String getTelefono()
	{
		return is_telefono;
	}

	/**
	 * Modifica el valor de is_indicativoTelefono
	 *
	 * @param as_s asigna el valor a la propiedad is_indicativoTelefono
	 */
	public void setIndicativoTelefono(String as_s)
	{
		is_indicativoTelefono = as_s;
	}

	/**
	 * Retorna el valor de is_indicativoTelefono
	 *
	 * @return el valor de is_indicativoTelefono
	 */
	public String getIndicativoTelefono()
	{
		return is_indicativoTelefono;
	}

	/**
	 * Retorna el valor de is_direccion.
	 *
	 * @return el valor de is_direccion
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Modifica el valor de is_direccion.
	 *
	 * @param as_s asigna el valor a la propiedad is_direccion
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreDepartamento()
	{
		return is_nombreDepartamento;
	}

	/**
	 * Modifica el valor de NombreDepartamento.
	 *
	 * @param nombreDepartamento asigna el valor a la propiedad
	 */
	public void setNombreDepartamento(String nombreDepartamento)
	{
		is_nombreDepartamento = nombreDepartamento;
	}

	/**
	 * Retorna Objeto o variable de valor nombre municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreMunicipio()
	{
		return is_nombreMunicipio;
	}

	/**
	 * Modifica el valor de NombreMunicipio.
	 *
	 * @param nombreMunicipio asigna el valor a la propiedad
	 */
	public void setNombreMunicipio(String nombreMunicipio)
	{
		is_nombreMunicipio = nombreMunicipio;
	}

	/**
	 * Retorna Objeto o variable de valor nombre pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePais()
	{
		return is_nombrePais;
	}

	/**
	 * Modifica el valor de NombrePais.
	 *
	 * @param nombrePais asigna el valor a la propiedad
	 */
	public void setNombrePais(String nombrePais)
	{
		is_nombrePais = nombrePais;
	}
}
