package co.gov.supernotariado.www.services.bachue.co.creadorturno.v1;


/**
 * Clase que contiene todos las propiedades SUT_CO_CreadorTurnoProxy.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class SUT_CO_CreadorTurnoProxy implements co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurno_PortType
{
	/** Propiedad s U T C O creador turno port type. */
	private co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurno_PortType sUT_CO_CreadorTurno_PortType =
		null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/**
	 * Instancia un nuevo objeto SU T C O creador turno proxy.
	 */
	public SUT_CO_CreadorTurnoProxy()
	{
		_initSUT_CO_CreadorTurnoProxy();
	}

	/**
	 * Instancia un nuevo objeto SU T C O creador turno proxy.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public SUT_CO_CreadorTurnoProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSUT_CO_CreadorTurnoProxy();
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sUT_CO_CreadorTurno_PortType != null)
			((javax.xml.rpc.Stub)sUT_CO_CreadorTurno_PortType)._setProperty(
			    "javax.xml.rpc.service.endpoint.address", _endpoint
			);
	}

	/**
	 * Retorna Objeto o variable de valor endpoint.
	 *
	 * @return el valor de endpoint
	 */
	public String getEndpoint()
	{
		return _endpoint;
	}

	/**
	 * Retorna Objeto o variable de valor SU T C O creador turno port type.
	 *
	 * @return el valor de SU T C O creador turno port type
	 */
	public co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurno_PortType getSUT_CO_CreadorTurno_PortType()
	{
		if(sUT_CO_CreadorTurno_PortType == null)
			_initSUT_CO_CreadorTurnoProxy();

		return sUT_CO_CreadorTurno_PortType;
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.schemas.bachue.co.creadorturno.crearturno.v1.TipoSalidaCrearTurno crearTurno(
	    co.gov.supernotariado.www.schemas.bachue.co.creadorturno.crearturno.v1.TipoEntradaCrearTurno entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CO_CreadorTurno_PortType == null)
			_initSUT_CO_CreadorTurnoProxy();

		return sUT_CO_CreadorTurno_PortType.crearTurno(entrada);
	}

	/**
	 * Inits the SU T C O creador turno proxy.
	 */
	private void _initSUT_CO_CreadorTurnoProxy()
	{
		try
		{
			sUT_CO_CreadorTurno_PortType = (new co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurno_ServiceLocator())
					.getSUT_CO_CreadorTurnoPort();

			if(sUT_CO_CreadorTurno_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sUT_CO_CreadorTurno_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sUT_CO_CreadorTurno_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
