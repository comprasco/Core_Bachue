package com.bachue.snr.prosnr01.model.notificaciones;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase de manejo de personas a notificar.
 *
 * @author hcastaneda
 *
 */
public class PersonaNotificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2673563833215848917L;

	/**  Atributo icm_municipios. */
	private Collection<Municipio> icm_municipios;

	/**  Atributo il_idEtapa. */
	private Long il_idEtapa;

	/**  Atributo ir_registro. */
	private Registro ir_registro;

	/**  Atributo is_calidadEnQueActua. */
	private String is_calidadEnQueActua;

	/**  Atributo is_camposAuditoria. */
	private String is_camposAuditoria;

	/**  Atributo is_destinatario. */
	private String is_destinatario;

	/**  Atributo is_direccion. */
	private String is_direccion;

	/**  Atributo is_documento. */
	private String is_documento;

	/**  Atributo is_idAutorizacionComunicacion. */
	private String is_idAutorizacionComunicacion;

	/**  Atributo is_idAutorizacionNotificacion. */
	private String is_idAutorizacionNotificacion;

	/**  Atributo is_idCorreoElectronico. */
	private String is_idCorreoElectronico;

	/**  Atributo is_idCorreoElectronicoComunicacion. */
	private String is_idCorreoElectronicoComunicacion;

	/**  Atributo is_idDecisionPlantilla. */
	private String is_idDecisionPlantilla;

	/**  Atributo is_departamento. */
	private String is_idDepartamento;

	/**  Atributo is_idDireccion. */
	private String is_idDireccion;

	/**  Atributo is_idDireccionComunicacion. */
	private String is_idDireccionComunicacion;

	/**  Atributo is_idMunicipio. */
	private String is_idMunicipio;

	/**  Atributo is_idPais. */
	private String is_idPais;

	/**  Atributo is_idPersona. */
	private String is_idPersona;

	/**  Atributo is_idPersonaNotificacion. */
	private String is_idPersonaNotificacion;

	/**  Atributo is_idSolicitud. */
	private String is_idSolicitud;

	/**  Atributo is_idTelefonoComunicacion. */
	private String is_idTelefonoComunicacion;

	/**  Atributo is_idTurno. */
	private String is_idTurno;

	/**  Atributo is_intervinienteIndeterminado. */
	private String is_notificar;

	/**  Atributo is_intervinienteIndeterminado. */
	private String is_terceroIndeterminado;

	/**  Atributo is_tipoDocumento. */
	private String is_tipoDocumento;

	/**  Atributo ib_registroPrecargado. */
	private boolean ib_registroPrecargado;

	/**  Atributo ib_seleccionado. */
	private boolean ib_seleccionado;

	/**  Atributo ii_identificador. */
	private int ii_identificador;

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setCalidadEnQueActua(String as_s)
	{
		is_calidadEnQueActua = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getCalidadEnQueActua()
	{
		return is_calidadEnQueActua;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setCamposAuditoria(String as_s)
	{
		is_camposAuditoria = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getCamposAuditoria()
	{
		return is_camposAuditoria;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setDestinatario(String as_s)
	{
		is_destinatario = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 *
	 * @return retorna el valor del atributo.
	 */
	public String getDestinatario()
	{
		return is_destinatario;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setDocumento(String as_s)
	{
		is_documento = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getDocumento()
	{
		return is_documento;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdAutorizacionComunicacion(String as_s)
	{
		is_idAutorizacionComunicacion = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdAutorizacionComunicacion()
	{
		return is_idAutorizacionComunicacion;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdAutorizacionNotificacion(String as_s)
	{
		is_idAutorizacionNotificacion = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdAutorizacionNotificacion()
	{
		return is_idAutorizacionNotificacion;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdCorreoElectronico(String as_s)
	{
		is_idCorreoElectronico = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdCorreoElectronico()
	{
		return is_idCorreoElectronico;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdCorreoElectronicoComunicacion(String as_s)
	{
		is_idCorreoElectronicoComunicacion = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdCorreoElectronicoComunicacion()
	{
		return is_idCorreoElectronicoComunicacion;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdDecisionPlantilla(String as_s)
	{
		is_idDecisionPlantilla = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdDecisionPlantilla()
	{
		return is_idDecisionPlantilla;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdDireccion(String as_s)
	{
		is_idDireccion = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdDireccion()
	{
		return is_idDireccion;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdDireccionComunicacion(String as_s)
	{
		is_idDireccionComunicacion = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdDireccionComunicacion()
	{
		return is_idDireccionComunicacion;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdEtapa(Long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public Long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 *
	 * @return retorna el valor del atributo.
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdPersonaNotificacion(String as_s)
	{
		is_idPersonaNotificacion = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 *
	 * @return retorna el valor del atributo.
	 */
	public String getIdPersonaNotificacion()
	{
		return is_idPersonaNotificacion;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 *
	 * @return retorna el valor del atributo.
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdTelefonoComunicacion(String as_s)
	{
		is_idTelefonoComunicacion = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getIdTelefonoComunicacion()
	{
		return is_idTelefonoComunicacion;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 *
	 * @return retorna el valor del atributo.
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param ai_i de ai i
	 */
	public void setIdentificador(int ai_i)
	{
		ii_identificador = ai_i;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public int getIdentificador()
	{
		return ii_identificador;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param acm_cm modifica el valor del atributo.
	 */
	public void setMunicipios(Collection<Municipio> acm_cm)
	{
		icm_municipios = acm_cm;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public Collection<Municipio> getMunicipios()
	{
		return icm_municipios;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setNotificar(String as_s)
	{
		is_notificar = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getNotificar()
	{
		return is_notificar;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param ar_r de ar r
	 */
	public void setRegistro(Registro ar_r)
	{
		ir_registro = ar_r;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public Registro getRegistro()
	{
		return ir_registro;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param ab_b modifica el valor del atributo.
	 */
	public void setRegistroPrecargado(boolean ab_b)
	{
		ib_registroPrecargado = ab_b;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public boolean isRegistroPrecargado()
	{
		return ib_registroPrecargado;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param ab_b modifica el valor del atributo.
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setTerceroIndeterminado(String as_s)
	{
		is_terceroIndeterminado = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getTerceroIndeterminado()
	{
		return is_terceroIndeterminado;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	* Metodo encargado de retornar el valor del atributo.
	*
	* @return retorna el valor del atributo.
	*/
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}
}
