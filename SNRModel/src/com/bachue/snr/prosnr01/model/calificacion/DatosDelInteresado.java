package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades DatosDelInteresado.
 *
 * @author lchacon
 */
public class DatosDelInteresado extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5675436833229141855L;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ipce correo electronico. */
	private PersonaCorreoElectronico ipce_correoElectronico;

	/** Propiedad ipd direccion correspondencia. */
	private PersonaDireccion ipd_direccionCorrespondencia;

	/** Propiedad ipd direccion residencia. */
	private PersonaDireccion ipd_direccionResidencia;

	/** Propiedad ipt telefono fijo. */
	private PersonaTelefono ipt_telefonoFijo;

	/** Propiedad ipt telefono movil. */
	private PersonaTelefono ipt_telefonoMovil;

	/** Propiedad is tipo direccion. */
	private String is_tipoDireccion;

	/** Propiedad is tipo telefono. */
	private String is_tipoTelefono;

	/** Propiedad ib data correo. */
	private boolean ib_dataCorreo;

	/** Propiedad ib data fijo. */
	private boolean ib_dataFijo;

	/** Propiedad ib data movil. */
	private boolean ib_dataMovil;

	/** Propiedad ib editar datos basicos. */
	private boolean ib_editarDatosBasicos;

	/** Propiedad ib editar datos contacto. */
	private boolean ib_editarDatosContacto;

	/** Propiedad ib editar direccion correspondencia. */
	private boolean ib_editarDireccionCorrespondencia;

	/** Propiedad ib editar direccion residencia. */
	private boolean ib_editarDireccionResidencia;

	/**
	 * Modifica el valor de correo electronico.
	 *
	 * @param apce_pce asigna el valor a la propiedad correo electronico
	 */
	public void setCorreoElectronico(PersonaCorreoElectronico apce_pce)
	{
		ipce_correoElectronico = apce_pce;
	}

	/**
	 * Retorna el valor de correo electronico.
	 *
	 * @return el valor de correo electronico
	 */
	public PersonaCorreoElectronico getCorreoElectronico()
	{
		if(ipce_correoElectronico == null)
			ipce_correoElectronico = new PersonaCorreoElectronico();

		return ipce_correoElectronico;
	}

	/**
	 * Modifica el valor de data correo.
	 *
	 * @param ab_b asigna el valor a la propiedad data correo
	 */
	public void setDataCorreo(boolean ab_b)
	{
		ib_dataCorreo = ab_b;
	}

	/**
	 * Valida la propiedad data correo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en data correo
	 */
	public boolean isDataCorreo()
	{
		return ib_dataCorreo;
	}

	/**
	 * Modifica el valor de data fijo.
	 *
	 * @param ab_b asigna el valor a la propiedad data fijo
	 */
	public void setDataFijo(boolean ab_b)
	{
		ib_dataFijo = ab_b;
	}

	/**
	 * Valida la propiedad data fijo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en data fijo
	 */
	public boolean isDataFijo()
	{
		return ib_dataFijo;
	}

	/**
	 * Modifica el valor de data movil.
	 *
	 * @param ab_b asigna el valor a la propiedad data movil
	 */
	public void setDataMovil(boolean ab_b)
	{
		ib_dataMovil = ab_b;
	}

	/**
	 * Valida la propiedad data movil.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en data movil
	 */
	public boolean isDataMovil()
	{
		return ib_dataMovil;
	}

	/**
	 * Modifica el valor de direccion correspondencia.
	 *
	 * @param apd_pd asigna el valor a la propiedad direccion correspondencia
	 */
	public void setDireccionCorrespondencia(PersonaDireccion apd_pd)
	{
		ipd_direccionCorrespondencia = apd_pd;
	}

	/**
	 * Retorna el valor de direccion correspondencia.
	 *
	 * @return el valor de direccion correspondencia
	 */
	public PersonaDireccion getDireccionCorrespondencia()
	{
		if(ipd_direccionCorrespondencia == null)
		{
			ipd_direccionCorrespondencia = new PersonaDireccion();
			ipd_direccionCorrespondencia.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			ipd_direccionCorrespondencia.setTipoDireccion(EstadoCommon.C);
		}

		return ipd_direccionCorrespondencia;
	}

	/**
	 * Modifica el valor de direccion residencia.
	 *
	 * @param apd_pd asigna el valor a la propiedad direccion residencia
	 */
	public void setDireccionResidencia(PersonaDireccion apd_pd)
	{
		ipd_direccionResidencia = apd_pd;
	}

	/**
	 * Retorna el valor de direccion residencia.
	 *
	 * @return el valor de direccion residencia
	 */
	public PersonaDireccion getDireccionResidencia()
	{
		if(ipd_direccionResidencia == null)
		{
			ipd_direccionResidencia = new PersonaDireccion();
			ipd_direccionResidencia.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			ipd_direccionResidencia.setTipoDireccion(EstadoCommon.R);
		}

		return ipd_direccionResidencia;
	}

	/**
	 * Modifica el valor de editar datos basicos.
	 *
	 * @param ab_b asigna el valor a la propiedad editar datos basicos
	 */
	public void setEditarDatosBasicos(boolean ab_b)
	{
		ib_editarDatosBasicos = ab_b;
	}

	/**
	 * Valida la propiedad editar datos basicos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar datos basicos
	 */
	public boolean isEditarDatosBasicos()
	{
		return ib_editarDatosBasicos;
	}

	/**
	 * Modifica el valor de editar datos contacto.
	 *
	 * @param ab_b asigna el valor a la propiedad editar datos contacto
	 */
	public void setEditarDatosContacto(boolean ab_b)
	{
		ib_editarDatosContacto = ab_b;
	}

	/**
	 * Valida la propiedad editar datos contacto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar datos contacto
	 */
	public boolean isEditarDatosContacto()
	{
		return ib_editarDatosContacto;
	}

	/**
	 * Modifica el valor de editar direccion correspondencia.
	 *
	 * @param ab_b asigna el valor a la propiedad editar direccion correspondencia
	 */
	public void setEditarDireccionCorrespondencia(boolean ab_b)
	{
		ib_editarDireccionCorrespondencia = ab_b;
	}

	/**
	 * Valida la propiedad editar direccion correspondencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar direccion correspondencia
	 */
	public boolean isEditarDireccionCorrespondencia()
	{
		return ib_editarDireccionCorrespondencia;
	}

	/**
	 * Modifica el valor de editar direccion residencia.
	 *
	 * @param ab_b asigna el valor a la propiedad editar direccion residencia
	 */
	public void setEditarDireccionResidencia(boolean ab_b)
	{
		ib_editarDireccionResidencia = ab_b;
	}

	/**
	 * Valida la propiedad editar direccion residencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar direccion residencia
	 */
	public boolean isEditarDireccionResidencia()
	{
		return ib_editarDireccionResidencia;
	}

	/**
	 * Modifica el valor de persona.
	 *
	 * @param ap_p asigna el valor a la propiedad persona
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna el valor de persona.
	 *
	 * @return el valor de persona
	 */
	public Persona getPersona()
	{
		if(ip_persona == null)
		{
			ip_persona = new Persona();
			ip_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ip_persona;
	}

	/**
	 * Modifica el valor de telefono fijo.
	 *
	 * @param apt_pt asigna el valor a la propiedad telefono fijo
	 */
	public void setTelefonoFijo(PersonaTelefono apt_pt)
	{
		ipt_telefonoFijo = apt_pt;
	}

	/**
	 * Retorna el valor de telefono fijo.
	 *
	 * @return el valor de telefono fijo
	 */
	public PersonaTelefono getTelefonoFijo()
	{
		if(ipt_telefonoFijo == null)
		{
			ipt_telefonoFijo = new PersonaTelefono();
			ipt_telefonoFijo.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ipt_telefonoFijo;
	}

	/**
	 * Modifica el valor de telefono movil.
	 *
	 * @param atm_tm asigna el valor a la propiedad telefono movil
	 */
	public void setTelefonoMovil(PersonaTelefono atm_tm)
	{
		ipt_telefonoMovil = atm_tm;
	}

	/**
	 * Retorna el valor de telefono movil.
	 *
	 * @return el valor de telefono movil
	 */
	public PersonaTelefono getTelefonoMovil()
	{
		if(ipt_telefonoMovil == null)
		{
			ipt_telefonoMovil = new PersonaTelefono();
			ipt_telefonoMovil.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ipt_telefonoMovil;
	}

	/**
	 * Modifica el valor de tipo direccion.
	 *
	 * @param as_s asigna el valor a la propiedad tipo direccion
	 */
	public void setTipoDireccion(String as_s)
	{
		is_tipoDireccion = as_s;
	}

	/**
	 * Retorna el valor de tipo direccion.
	 *
	 * @return el valor de tipo direccion
	 */
	public String getTipoDireccion()
	{
		return is_tipoDireccion;
	}

	/**
	 * Modifica el valor de tipo telefono.
	 *
	 * @param as_s asigna el valor a la propiedad tipo telefono
	 */
	public void setTipoTelefono(String as_s)
	{
		is_tipoTelefono = as_s;
	}

	/**
	 * Retorna el valor de tipo telefono.
	 *
	 * @return el valor de tipo telefono
	 */
	public String getTipoTelefono()
	{
		return is_tipoTelefono;
	}
}
