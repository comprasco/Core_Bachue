/**
 * SBB_NP_OperacionesFinancierasSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2;


/**
 * Clase que contiene todos las propiedades SBB_NP_OperacionesFinancierasSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_NP_OperacionesFinancierasSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2.SBB_NP_OperacionesFinancieras_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8836196855234831532L;

	/** Propiedad my operations. */
	private static java.util.Map _myOperations = new java.util.Hashtable();

	/** Propiedad my operations list. */
	private static java.util.Collection _myOperationsList = new java.util.ArrayList();

	static
	{
		org.apache.axis.description.OperationDesc   _oper;
		org.apache.axis.description.FaultDesc       _fault;
		org.apache.axis.description.ParameterDesc[] _params;
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/consultarestadoliquidacion/v2",
				        "entradaConsultarEstadoLiquidacion"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/consultarestadoliquidacion/v2",
				        "tipoEntradaConsultarEstadoLiquidacion"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoEntradaConsultarEstadoLiquidacion.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarEstadoLiquidacion", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/consultarestadoliquidacion/v2",
			        "salidaConsultarEstadoLiquidacion"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/consultarestadoliquidacion/v2",
		        "tipoSalidaConsultarEstadoLiquidacion"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarEstadoLiquidacion"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/np/operacionesfinancieras/v2/ConsultarEstadoLiquidacion"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarEstadoLiquidacion") == null)
			_myOperations.put("consultarEstadoLiquidacion", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarEstadoLiquidacion")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarliquidacion/v2",
				        "entradaRegistrarLiquidacion"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarliquidacion/v2",
				        "tipoEntradaRegistrarLiquidacion"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoEntradaRegistrarLiquidacion.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registrarLiquidacion", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarliquidacion/v2",
			        "salidaRegistrarLiquidacion"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarliquidacion/v2",
		        "tipoSalidaRegistrarLiquidacion"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistrarLiquidacion"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/np/operacionesfinancieras/v2/RegistrarLiquidacion"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registrarLiquidacion") == null)
			_myOperations.put("registrarLiquidacion", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registrarLiquidacion")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/notificarpago/v2",
				        "entradaNotificarPago"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/notificarpago/v2",
				        "tipoEntradaNotificarPago"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoEntradaNotificarPago.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "notificarPago", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/notificarpago/v2",
			        "salidaNotificarPago"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/notificarpago/v2",
		        "tipoSalidaNotificarPago"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "NotificarPago"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/np/operacionesfinancieras/v2/NotificarPago"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("notificarPago") == null)
			_myOperations.put("notificarPago", new java.util.ArrayList());

		((java.util.List)_myOperations.get("notificarPago")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registraranulacion/v2",
				        "entradaRegistrarAnulacion"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registraranulacion/v2",
				        "tipoEntradaRegistrarAnulacion"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoEntradaRegistrarAnulacion.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registrarAnulacion", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registraranulacion/v2",
			        "salidaRegistrarAnulacion"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registraranulacion/v2",
		        "tipoSalidaRegistrarAnulacion"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistrarAnulacion"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/np/operacionesfinancieras/v2/RegistrarAnulacion"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registrarAnulacion") == null)
			_myOperations.put("registrarAnulacion", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registrarAnulacion")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarrecibocaja/v2",
				        "entradaRegistrarReciboCaja"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarrecibocaja/v2",
				        "tipoEntradaRegistrarReciboCaja"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoEntradaRegistrarReciboCaja.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registrarReciboCaja", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarrecibocaja/v2",
			        "salidaRegistrarReciboCaja"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarrecibocaja/v2",
		        "tipoSalidaRegistrarReciboCaja"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistrarReciboCaja"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/np/operacionesfinancieras/v2/RegistrarReciboCaja"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registrarReciboCaja") == null)
			_myOperations.put("registrarReciboCaja", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registrarReciboCaja")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2.SBB_NP_OperacionesFinancieras_PortType impl;

	/**
	 * Instancia un nuevo objeto SB B N P operaciones financieras SOAP 12 binding skeleton.
	 */
	public SBB_NP_OperacionesFinancierasSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2.SBB_NP_OperacionesFinancierasSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SB B N P operaciones financieras SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SBB_NP_OperacionesFinancierasSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2.SBB_NP_OperacionesFinancieras_PortType impl
	)
	{
		this.impl = impl;
	}

	/**
	 * Returns List of OperationDesc objects with this name.
	 *
	 * @param methodName de method name
	 * @return el valor de operation desc by name
	 */
	public static java.util.List getOperationDescByName(java.lang.String methodName)
	{
		return (java.util.List)_myOperations.get(methodName);
	}

	/**
	 * Returns Collection of OperationDescs.
	 *
	 * @return el valor de operation descs
	 */
	public static java.util.Collection getOperationDescs()
	{
		return _myOperationsList;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacion consultarEstadoLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoEntradaConsultarEstadoLiquidacion entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacion ret =
			impl.consultarEstadoLiquidacion(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoSalidaNotificarPago notificarPago(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoEntradaNotificarPago entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoSalidaNotificarPago ret = impl
				.notificarPago(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoSalidaRegistrarAnulacion registrarAnulacion(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoEntradaRegistrarAnulacion entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoSalidaRegistrarAnulacion ret =
			impl.registrarAnulacion(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoSalidaRegistrarLiquidacion registrarLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoEntradaRegistrarLiquidacion entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoSalidaRegistrarLiquidacion ret =
			impl.registrarLiquidacion(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoSalidaRegistrarReciboCaja registrarReciboCaja(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoEntradaRegistrarReciboCaja entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoSalidaRegistrarReciboCaja ret =
			impl.registrarReciboCaja(entrada);

		return ret;
	}
}
