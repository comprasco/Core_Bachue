package com.bachue.snr.prosnr01.model.copias;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_SOLICITUD_COPIAS.
 *
 * @author hcastaneda
 */
public class SolicitudCopias extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID            = -1502620146777994810L;
	
	/** Propiedad id tiempo vencimiento tramite. */
	private Date              id_tiempoVencimientoTramite;
	
	/** Propiedad il dias vencimiento. */
	private Long              il_diasVencimiento;
	
	/** Propiedad il identificador copias SE. */
	private Long              il_identificadorCopiasSE;
	
	/** Propiedad il numero copias. */
	private Long              il_numeroCopias;
	
	/** Propiedad il numero folios. */
	private Long              il_numeroFolios;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is doc name entrega. */
	private String            is_docNameEntrega;
	
	/** Propiedad is doc name original. */
	private String            is_docNameOriginal;
	
	/** Propiedad is estado impresion. */
	private String            is_estadoImpresion;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id ecm entrega. */
	private String            is_idEcmEntrega;
	
	/** Propiedad is id ecm original. */
	private String            is_idEcmOriginal;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is id solicitud copias. */
	private String            is_idSolicitudCopias;
	
	/** Propiedad is id tipo documental. */
	private String            is_idTipoDocumental;
	
	/** Propiedad is matricula owcc. */
	private String            is_matriculaOwcc;
	
	/** Propiedad is nir. */
	private String            is_nir;
	
	/** Propiedad is repositorio. */
	private String            is_repositorio;
	
	/** Propiedad is turno. */
	private String            is_turno;

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setActivo(String as_s)
	{
		is_activo                                         = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param al_l Argumento de tipo <code>Long</code> que contiene el valor del atributo.
	 */
	public void setDiasVencimiento(Long al_l)
	{
		il_diasVencimiento = al_l;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public Long getDiasVencimiento()
	{
		return il_diasVencimiento;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setDocNameEntrega(String as_s)
	{
		is_docNameEntrega = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getDocNameEntrega()
	{
		return is_docNameEntrega;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setDocNameOriginal(String as_s)
	{
		is_docNameOriginal = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getDocNameOriginal()
	{
		return is_docNameOriginal;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setEstadoImpresion(String as_s)
	{
		is_estadoImpresion = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getEstadoImpresion()
	{
		return is_estadoImpresion;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setIdEcmEntrega(String as_s)
	{
		is_idEcmEntrega = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getIdEcmEntrega()
	{
		return is_idEcmEntrega;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setIdEcmOriginal(String as_s)
	{
		is_idEcmOriginal = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getIdEcmOriginal()
	{
		return is_idEcmOriginal;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setIdSolicitudCopias(String as_s)
	{
		is_idSolicitudCopias = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getIdSolicitudCopias()
	{
		return is_idSolicitudCopias;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setIdTipoDocumental(String as_s)
	{
		is_idTipoDocumental = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getIdTipoDocumental()
	{
		return is_idTipoDocumental;
	}

	/**
	 * Modifica el valor de IdentificadorCopiasSE.
	 *
	 * @param al_l de al l
	 */
	public void setIdentificadorCopiasSE(Long al_l)
	{
		il_identificadorCopiasSE = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor identificador copias SE.
	 *
	 * @return Retorna el valor de la propiedad identificadorCopiasSE
	 */
	public Long getIdentificadorCopiasSE()
	{
		return il_identificadorCopiasSE;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setMatriculaOwcc(String as_s)
	{
		is_matriculaOwcc = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getMatriculaOwcc()
	{
		return is_matriculaOwcc;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param al_l Argumento de tipo <code>Long</code> que contiene el valor del atributo.
	 */
	public void setNumeroCopias(Long al_l)
	{
		il_numeroCopias = al_l;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public Long getNumeroCopias()
	{
		return il_numeroCopias;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param al_l Argumento de tipo <code>Long</code> que contiene el valor del atributo.
	 */
	public void setNumeroFolios(Long al_l)
	{
		il_numeroFolios = al_l;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public Long getNumeroFolios()
	{
		return il_numeroFolios;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setRepositorio(String as_s)
	{
		is_repositorio = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getRepositorio()
	{
		return is_repositorio;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param ad_d Argumento de tipo <code>Date</code> que contiene el valor del atributo.
	 */
	public void setTiempoVencimientoTramite(Date ad_d)
	{
		id_tiempoVencimientoTramite = ad_d;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public Date getTiempoVencimientoTramite()
	{
		return id_tiempoVencimientoTramite;
	}

	/**
	 * Metodo encargado de asignar el valor al atributo.
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor del atributo.
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo.
	 * @return Valor del atributo
	 */
	public String getTurno()
	{
		return is_turno;
	}
}
