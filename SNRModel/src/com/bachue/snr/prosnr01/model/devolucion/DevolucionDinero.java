package com.bachue.snr.prosnr01.model.devolucion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;

import java.io.Serializable;

import java.util.Date;


/**
 * Class que contiene la información para el objeto DevolucionDinero.
 *
 * @author ccalderon
 */
public class DevolucionDinero extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4271451474342612143L;

	/** Propiedad id fecha consignacion errada. */
	private Date id_fechaConsignacionErrada;

	/** Propiedad id valor consignacion errada. */
	private Double id_valorConsignacionErrada;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ir registro. */
	private Registro ir_registro;

	/** Propiedad is calidad en que actua. */
	private String is_calidadEnQueActua;

	/** Propiedad is id entidad consignacion errada. */
	private String is_codEntidadConsignacionErrada;

	/** Propiedad is codigo cuenta cupo. */
	private String is_codigoCuentaCupo;

	/** Propiedad is codigo entidad financiera devolucion. */
	private String is_codigoEntidadFinancieraDevolucion;

	/** Propiedad is codigo nota credito. */
	private String is_codigoNotaCredito;

	/** Propiedad is extemporaneo. */
	private String is_extemporaneo;

	/** Propiedad is_idCorreoElectronicoTitular. */
	private String is_idCorreoElectronicoTitular;

	/** Propiedad is id devolucion dinero. */
	private String is_idDevolucionDinero;

	/** Propiedad is id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is id persona interviniente. */
	private String is_idPersonaInterviniente;

	/** Propiedad is id persona titular cuenta. */
	private String is_idPersonaTitularCuenta;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is_idTelefonoFijoTitular. */
	private String is_idTelefonoFijoTitular;

	/** Propiedad is_idTelefonoMovilTitular. */
	private String is_idTelefonoMovilTitular;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno devolucion. */
	private String is_idTurnoDevolucion;

	/** Propiedad is interviene tramite. */
	private String is_intervieneTramite;

	/** Propiedad is nombre entidad recaudadora. */
	private String is_nombreEntidadRecaudadora;

	/** Propiedad is numero consignacion errada. */
	private String is_numeroConsignacionErrada;

	/** Propiedad is numero cuenta. */
	private String is_numeroCuenta;

	/** Propiedad is numero cuenta consignacion errada. */
	private String is_numeroCuentaConsignacionErrada;

	/** Propiedad is tipo consignacion. */
	private String is_tipoConsignacion;

	/** Propiedad is tipo cuenta. */
	private String is_tipoCuenta;

	/** Propiedad is tipo devolucion. */
	private String is_tipoDevolucion;

	/** Propiedad is_tipoTelefonoFijoTitular. */
	private String is_tipoTelefonoFijoTitular;

	/** Propiedad is_tipoTelefonoMovilTitular. */
	private String is_tipoTelefonoMovilTitular;

	/** Propiedad is tipo telefono titular. */
	private String is_tipoTelefonoTitular;

	/** Propiedad is titular cuenta. */
	private String is_titularCuenta;

	/** Propiedad ii identificador. */
	private int ii_identificador;

	/** Propiedad il valor total acto devolucion. */
	private long il_valorTotalActoDevolucion;

	private Integer ii_tokenVerificacion;

	/**
	 * Retorna Objeto o variable de valor token verificacion.
	 *
	 * @return Retorna el valor de la propiedad tokenVerificacion
	 */
	public Integer getTokenVerificacion() {
		return ii_tokenVerificacion;
	}

	/**
	 * Modifica el valor de TokenVerificacion.
	 *
	 * @param ai_tokenVerificacion Modifica el valor de la propiedad tokenVerificacion
	 */
	public void setTokenVerificacion(Integer ai_tokenVerificacion) {
		ii_tokenVerificacion = ai_tokenVerificacion;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setCalidadEnQueActua(String as_s)
	{
		is_calidadEnQueActua = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getCalidadEnQueActua()
	{
		return is_calidadEnQueActua;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setCodEntidadConsignacionErrada(String as_s)
	{
		is_codEntidadConsignacionErrada = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getCodEntidadConsignacionErrada()
	{
		return is_codEntidadConsignacionErrada;
	}

	/**
	 * Modifica el valor de CodigoCuentaCupo.
	 *
	 * @param as_s Modifica el valor de la propiedad codigoCuentaCupo
	 */
	public void setCodigoCuentaCupo(String as_s)
	{
		is_codigoCuentaCupo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo cuenta cupo.
	 *
	 * @return Retorna el valor de la propiedad codigoCuentaCupo
	 */
	public String getCodigoCuentaCupo()
	{
		return is_codigoCuentaCupo;
	}

	/**
	 * Modifica el valor de CodigoEntidadFinancieraDevolucion.
	 *
	 * @param as_s de as s
	 */
	public void setCodigoEntidadFinancieraDevolucion(String as_s)
	{
		is_codigoEntidadFinancieraDevolucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo entidad financiera devolucion.
	 *
	 * @return Retorna el valor de la propiedad codigoEntidadFinancieraDevolucion
	 */
	public String getCodigoEntidadFinancieraDevolucion()
	{
		return is_codigoEntidadFinancieraDevolucion;
	}

	/**
	 * Modifica el valor de CodigoNotaCredito.
	 *
	 * @param as_s Modifica el valor de la propiedad codigoNotaCredito
	 */
	public void setCodigoNotaCredito(String as_s)
	{
		is_codigoNotaCredito = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo nota credito.
	 *
	 * @return Retorna el valor de la propiedad codigoNotaCredito
	 */
	public String getCodigoNotaCredito()
	{
		return is_codigoNotaCredito;
	}

	/**
	 * Modifica el valor de Extemporaneo.
	 *
	 * @param as_s de as s
	 */
	public void setExtemporaneo(String as_s)
	{
		is_extemporaneo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor extemporaneo.
	 *
	 * @return el valor de extemporaneo
	 */
	public String getExtemporaneo()
	{
		return is_extemporaneo;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ad_d modifica el valor de la propiedad.
	 */
	public void setFechaConsignacionErrada(Date ad_d)
	{
		id_fechaConsignacionErrada = ad_d;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Date getFechaConsignacionErrada()
	{
		return id_fechaConsignacionErrada;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdCorreoElectronicoTitular(String as_s)
	{
		is_idCorreoElectronicoTitular = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdCorreoElectronicoTitular()
	{
		return is_idCorreoElectronicoTitular;
	}

	/**
	 * Modifica el valor de IdDevolucionDinero.
	 *
	 * @param as_s de as s
	 */
	public void setIdDevolucionDinero(String as_s)
	{
		is_idDevolucionDinero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id devolucion dinero.
	 *
	 * @return Retorna el valor de la propiedad idDevolucionDinero
	 */
	public String getIdDevolucionDinero()
	{
		return is_idDevolucionDinero;
	}

	/**
	 * Modifica el valor de IdEntidadRecaudadora.
	 *
	 * @param as_s de as s
	 */
	public void setIdEntidadRecaudadora(String as_s)
	{
		is_idEntidadRecaudadora = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad recaudadora.
	 *
	 * @return Retorna el valor de la propiedad idEntidadRecaudadora
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * Modifica el valor de IdPersonaInterviniente.
	 *
	 * @param as_s de as s
	 */
	public void setIdPersonaInterviniente(String as_s)
	{
		is_idPersonaInterviniente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona interviniente.
	 *
	 * @return Retorna el valor de la propiedad idPersonaInterviniente
	 */
	public String getIdPersonaInterviniente()
	{
		return is_idPersonaInterviniente;
	}

	/**
	 * Modifica el valor de IdPersonaTitularCuenta.
	 *
	 * @param as_s de as s
	 */
	public void setIdPersonaTitularCuenta(String as_s)
	{
		is_idPersonaTitularCuenta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona titular cuenta.
	 *
	 * @return Retorna el valor de la propiedad as_s
	 */
	public String getIdPersonaTitularCuenta()
	{
		return is_idPersonaTitularCuenta;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return Retorna el valor de la propiedad idSolicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdTelefonoFijoTitular(String as_s)
	{
		is_idTelefonoFijoTitular = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTelefonoFijoTitular()
	{
		return is_idTelefonoFijoTitular;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdTelefonoMovilTitular(String as_s)
	{
		is_idTelefonoMovilTitular = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTelefonoMovilTitular()
	{
		return is_idTelefonoMovilTitular;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoDevolucion.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurnoDevolucion(String as_s)
	{
		is_idTurnoDevolucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno devolucion.
	 *
	 * @return Retorna el valor de la propiedad idTurnoDevolucion
	 */
	public String getIdTurnoDevolucion()
	{
		return is_idTurnoDevolucion;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ai_i modifica el valor de la propiedad.
	 */
	public void setIdentificador(int ai_i)
	{
		ii_identificador = ai_i;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public int getIdentificador()
	{
		return ii_identificador;
	}

	/**
	 * Modifica el valor de IntervieneTramite.
	 *
	 * @param as_s de as s
	 */
	public void setIntervieneTramite(String as_s)
	{
		is_intervieneTramite = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor interviene tramite.
	 *
	 * @return Retorna el valor de la propiedad intervieneTramite
	 */
	public String getIntervieneTramite()
	{
		return is_intervieneTramite;
	}

	/**
	 * Modifica el valor de NombreEntidadRecaudadora.
	 *
	 * @param as_s Modifica el valor de la propiedad nombreEntidadRecaudadora
	 */
	public void setNombreEntidadRecaudadora(String as_s)
	{
		is_nombreEntidadRecaudadora = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre entidad recaudadora.
	 *
	 * @return Retorna el valor de la propiedad nombreEntidadRecaudadora
	 */
	public String getNombreEntidadRecaudadora()
	{
		return is_nombreEntidadRecaudadora;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setNumeroConsignacionErrada(String as_s)
	{
		is_numeroConsignacionErrada = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getNumeroConsignacionErrada()
	{
		return is_numeroConsignacionErrada;
	}

	/**
	 * Modifica el valor de NumeroCuenta.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroCuenta(String as_s)
	{
		is_numeroCuenta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero cuenta.
	 *
	 * @return Retorna el valor de la propiedad numeroCuenta
	 */
	public String getNumeroCuenta()
	{
		return is_numeroCuenta;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setNumeroCuentaConsignacionErrada(String as_s)
	{
		is_numeroCuentaConsignacionErrada = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getNumeroCuentaConsignacionErrada()
	{
		return is_numeroCuentaConsignacionErrada;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ap_p modifica el valor de la propiedad.
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Persona getPersona()
	{
		return ip_persona;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ar_r modifica el valor de la propiedad.
	 */
	public void setRegistro(Registro ar_r)
	{
		ir_registro = ar_r;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Registro getRegistro()
	{
		return ir_registro;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setTipoConsignacion(String as_s)
	{
		is_tipoConsignacion = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoConsignacion()
	{
		return is_tipoConsignacion;
	}

	/**
	 * Modifica el valor de TipoCuenta.
	 *
	 * @param as_s de as s
	 */
	public void setTipoCuenta(String as_s)
	{
		is_tipoCuenta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo cuenta.
	 *
	 * @return Retorna el valor de la propiedad tipoCuenta
	 */
	public String getTipoCuenta()
	{
		return is_tipoCuenta;
	}

	/**
	 * Modifica el valor de TipoDevolucion.
	 *
	 * @param as_s Modifica el valor de la propiedad tipoDevolucion
	 */
	public void setTipoDevolucion(String as_s)
	{
		is_tipoDevolucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo devolucion.
	 *
	 * @return Retorna el valor de la propiedad tipoDevolucion
	 */
	public String getTipoDevolucion()
	{
		return is_tipoDevolucion;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setTipoTelefonoFijoTitular(String as_s)
	{
		is_tipoTelefonoFijoTitular = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoTelefonoFijoTitular()
	{
		return is_tipoTelefonoFijoTitular;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setTipoTelefonoMovilTitular(String as_s)
	{
		is_tipoTelefonoMovilTitular = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoTelefonoMovilTitular()
	{
		return is_tipoTelefonoMovilTitular;
	}

	/**
	 * Modifica el valor de TipoTelefonoTitular.
	 *
	 * @param as_s de as s
	 */
	public void setTipoTelefonoTitular(String as_s)
	{
		is_tipoTelefonoTitular = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo telefono titular.
	 *
	 * @return Retorna el valor de la propiedad tipoTelefonoTitular
	 */
	public String getTipoTelefonoTitular()
	{
		return is_tipoTelefonoTitular;
	}

	/**
	 * Modifica el valor de TitularCuenta.
	 *
	 * @param as_s de as s
	 */
	public void setTitularCuenta(String as_s)
	{
		is_titularCuenta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor titular cuenta.
	 *
	 * @return Retorna el valor de la propiedad titularCuenta
	 */
	public String getTitularCuenta()
	{
		return is_titularCuenta;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ad_d modifica el valor de la propiedad.
	 */
	public void setValorConsignacionErrada(Double ad_d)
	{
		id_valorConsignacionErrada = ad_d;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Double getValorConsignacionErrada()
	{
		return id_valorConsignacionErrada;
	}

	/**
	 * Modifica el valor de ValorTotalActoDevolucion.
	 *
	 * @param al_l de al l
	 */
	public void setValorTotalActoDevolucion(long al_l)
	{
		il_valorTotalActoDevolucion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor valor total acto devolucion.
	 *
	 * @return Retorna el valor de la propiedad valorTotalActoDevolucion
	 */
	public long getValorTotalActoDevolucion()
	{
		return il_valorTotalActoDevolucion;
	}
}
