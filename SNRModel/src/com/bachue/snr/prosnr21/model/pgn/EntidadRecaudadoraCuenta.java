package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 *Clase modelo que contiene todos los atributos de la tabla SDB_PGN_ENTIDADRECAUDADORA_CUENTA
 * @author dbeltran
 *
 */
public class EntidadRecaudadoraCuenta extends Auditoria implements Serializable
{
	/** Propiedad serialVersionUID */
	private static final long serialVersionUID = 4608093204379140241L;

	/** Propiedad fecha a confrontar */
	private Date id_fechaConfrontar;

	/** Propiedad activo */
	private String is_activo;

	/** Propiedad correo electrónico contacto */
	private String is_correoElectronicoContacto;

	/** Propiedad id Cuenta */
	private String is_idCuenta;

	/** Propiedad id entidad recaudadora */
	private String is_idEntidadRecaudadora;

	/** Propiedad nombre contacto */
	private String is_nombreContacto;

	/** Propiedad nombre entidad recaudadora */
	private String is_nombreEntidadRecaudadora;

	/** Propiedad numero cel contacto */
	private String is_numeroCelContacto;

	/** Propiedad numero cuenta */
	private String is_numeroCuenta;

	/** Propiedad tipo archivo */
	private String is_tipoArchivo;

	/** Propiedad tipo cuenta */
	private String is_tipoCuenta;

	/**
	 * @param Modifica el valor de la propiedad is_activo por as_s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_correoElectronicoContacto por as_s
	 */
	public void setCorreoElectronicoContacto(String as_s)
	{
		is_correoElectronicoContacto = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_correoElectronicoContacto
	 */
	public String getCorreoElectronicoContacto()
	{
		return is_correoElectronicoContacto;
	}

	/**
	 * @param Modifica el valor de la propiedad id_fechaConfrontar por ad_fechaConfrontar
	 */
	public void setFechaConfrontar(Date ad_fechaConfrontar)
	{
		id_fechaConfrontar = ad_fechaConfrontar;
	}

	/**
	 * @return Retorna el valor de la propiedad ld_fechaConfrontar
	 */
	public Date getFechaConfrontar()
	{
		return id_fechaConfrontar;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idCuenta por as_s
	 */
	public void setIdCuenta(String as_s)
	{
		is_idCuenta = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idEntidadRecaudadora por as_s
	 */
	public void setIdEntidadRecaudadora(String as_s)
	{
		is_idEntidadRecaudadora = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idEntidadRecaudadora
	 */
	public String getIdEntidadRecaudadora()
	{
		return (is_idEntidadRecaudadora != null) ? is_idEntidadRecaudadora : new String();
	}

	/**
	 * @param Modifica el valor de la propiedad is_nombreContacto por as_s
	 */
	public void setNombreContacto(String as_s)
	{
		is_nombreContacto = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreContacto
	 */
	public String getNombreContacto()
	{
		return is_nombreContacto;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_nombreEntidadRecaudadora
	 */
	public void setNombreEntidadRecaudadora(String as_s)
	{
		is_nombreEntidadRecaudadora = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreEntidadRecaudadora
	 */
	public String getNombreEntidadRecaudadora()
	{
		return is_nombreEntidadRecaudadora;
	}

	/**
	 * @param Modifica el valor de la propiedad is_numeroCelContacto por as_s
	 */
	public void setNumeroCelContacto(String as_s)
	{
		is_numeroCelContacto = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroCelContacto
	 */
	public String getNumeroCelContacto()
	{
		return is_numeroCelContacto;
	}

	/**
	 * @param Modifica el valor de la propiedad is_numeroCuenta por as_s
	 */
	public void setNumeroCuenta(String as_s)
	{
		is_numeroCuenta = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroCuenta
	 */
	public String getNumeroCuenta()
	{
		return is_numeroCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad is_tipoArchivo por as_s
	 */
	public void setTipoArchivo(String as_s)
	{
		is_tipoArchivo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_tipoArchivo
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_tipoCuenta por is_tipoCuenta
	 */
	public void setTipoCuenta(String as_s)
	{
		is_tipoCuenta = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_tipoCuenta
	 */
	public String getTipoCuenta()
	{
		return is_tipoCuenta;
	}
}
