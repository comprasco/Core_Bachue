package co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1;


/**
 * Clase que contiene todos las propiedades SBB_EE_IndicePropietariosProxy.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/07/2020
 */
public class SBB_EE_IndicePropietariosProxy implements co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_PortType
{
	/** Propiedad s B B E E indice propietarios port type. */
	private co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_PortType sBB_EE_IndicePropietarios_PortType =
		null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/** Propiedad is password. */
	private String is_password;

	/** Propiedad is username. */
	private String is_username;

	/**
	 * Instancia un nuevo objeto SB B E E indice propietarios proxy.
	 */
	public SBB_EE_IndicePropietariosProxy()
	{
		_initSBB_EE_IndicePropietariosProxy();
	}

	/**
	 * Instancia un nuevo objeto SB B E E indice propietarios proxy.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public SBB_EE_IndicePropietariosProxy(String endpoint, String as_username, String as_password)
	{
		setUsername(as_username);
		setPassword(as_password);
		_endpoint = endpoint;
		_initSBB_EE_IndicePropietariosProxy();
	}

	/**
	 * Modifica el valor de Password.
	 *
	 * @param as_s de as s
	 */
	public void setPassword(String as_s)
	{
		is_password = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor password.
	 *
	 * @return el valor de password
	 */
	public String getPassword()
	{
		return is_password;
	}

	/**
	 * Modifica el valor de Username.
	 *
	 * @param as_s de as s
	 */
	public void setUsername(String as_s)
	{
		is_username = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor username.
	 *
	 * @return el valor de username
	 */
	public String getUsername()
	{
		return is_username;
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sBB_EE_IndicePropietarios_PortType != null)
			((javax.xml.rpc.Stub)sBB_EE_IndicePropietarios_PortType)._setProperty(
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
	 * Retorna Objeto o variable de valor SB B E E indice propietarios port type.
	 *
	 * @return el valor de SB B E E indice propietarios port type
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_PortType getSBB_EE_IndicePropietarios_PortType()
	{
		if(sBB_EE_IndicePropietarios_PortType == null)
			_initSBB_EE_IndicePropietariosProxy();

		return sBB_EE_IndicePropietarios_PortType;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v 1 . tipo salida indice propietarios
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios consultar(
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sBB_EE_IndicePropietarios_PortType == null)
			_initSBB_EE_IndicePropietariosProxy();

		return sBB_EE_IndicePropietarios_PortType.consultar(entrada);
	}

	
	
	/**
	 * Inits the SB B E E indice propietarios proxy.
	 */
	private void _initSBB_EE_IndicePropietariosProxy()
	{
		try
		{
			SBB_EE_IndicePropietarios_ServiceLocator lsisl_serviceLocator;
			
			lsisl_serviceLocator = new SBB_EE_IndicePropietarios_ServiceLocator();
			
			lsisl_serviceLocator.setPassword(getPassword());
			lsisl_serviceLocator.setUsername(getUsername());
			
			sBB_EE_IndicePropietarios_PortType = lsisl_serviceLocator
					.getSBB_EE_IndicePropietariosPort();
			

			if(sBB_EE_IndicePropietarios_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sBB_EE_IndicePropietarios_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sBB_EE_IndicePropietarios_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
