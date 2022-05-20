package co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1;

import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTitular;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase que contiene todos las propiedades AlertasMensajeEstado.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class AlertasMensajeEstado implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long         serialVersionUID = 4144094097795078949L;
	
	/** Propiedad icat alertas. */
	private Collection<AlertaTitular> icat_alertas;
	
	/** Propiedad is mensaje. */
	private String                    is_mensaje;

	/**
	 * Instancia un nuevo objeto alertas mensaje estado.
	 */
	public AlertasMensajeEstado()
	{
	}

	/**
	 * Instancia un nuevo objeto alertas mensaje estado.
	 *
	 * @param acat_alertas de acat alertas
	 * @param as_mensaje de as mensaje
	 */
	public AlertasMensajeEstado(Collection<AlertaTitular> acat_alertas, String as_mensaje)
	{
		icat_alertas                                   = acat_alertas;
		is_mensaje                                     = as_mensaje;
	}

	/**
	 * Modifica el valor de Alertas.
	 *
	 * @param acat_cat de acat cat
	 */
	public void setAlertas(Collection<AlertaTitular> acat_cat)
	{
		icat_alertas = acat_cat;
	}

	/**
	 * Retorna Objeto o variable de valor alertas.
	 *
	 * @return el valor de alertas
	 */
	public Collection<AlertaTitular> getAlertas()
	{
		return icat_alertas;
	}

	/**
	 * Modifica el valor de Mensaje.
	 *
	 * @param as_s de as s
	 */
	public void setMensaje(String as_s)
	{
		is_mensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor mensaje.
	 *
	 * @return el valor de mensaje
	 */
	public String getMensaje()
	{
		return is_mensaje;
	}
}
