package co.gov.supernotariado.www.services.bachue.cb.notificador.v1;


/**
 * Clase que contiene todos las propiedades SUT_CB_NotificadorProxy.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public class SUT_CB_NotificadorProxy implements co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType
{
	/** Propiedad s U T C B notificador port type. */
	private co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType sUT_CB_Notificador_PortType =
		null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/**
	 * Instancia un nuevo objeto SU T C B notificador proxy.
	 */
	public SUT_CB_NotificadorProxy()
	{
		_initSUT_CB_NotificadorProxy();
	}

	/**
	 * Instancia un nuevo objeto SU T C B notificador proxy.
	 *
	 * @param endpoint de endpoint
	 */
	public SUT_CB_NotificadorProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSUT_CB_NotificadorProxy();
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sUT_CB_Notificador_PortType != null)
			((javax.xml.rpc.Stub)sUT_CB_Notificador_PortType)._setProperty(
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
	 * Retorna Objeto o variable de valor SU T C B notificador port type.
	 *
	 * @return el valor de SU T C B notificador port type
	 */
	public co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType getSUT_CB_Notificador_PortType()
	{
		if(sUT_CB_Notificador_PortType == null)
			_initSUT_CB_NotificadorProxy();

		return sUT_CB_Notificador_PortType;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoSalidaAcusarMensaje acusarMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoEntradaAcusarMensaje entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_Notificador_PortType == null)
			_initSUT_CB_NotificadorProxy();

		return sUT_CB_Notificador_PortType.acusarMensaje(entrada);
	}

	/**
	 * Inits the SU T C B notificador proxy.
	 */
	private void _initSUT_CB_NotificadorProxy()
	{
		try
		{
			sUT_CB_Notificador_PortType = (new co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_ServiceLocator())
					.getSUT_CB_NotificadorPort();

			if(sUT_CB_Notificador_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sUT_CB_Notificador_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sUT_CB_Notificador_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
