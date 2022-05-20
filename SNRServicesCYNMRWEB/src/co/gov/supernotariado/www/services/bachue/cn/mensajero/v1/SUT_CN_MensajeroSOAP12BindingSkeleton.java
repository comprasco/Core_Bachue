/**
 * SUT_CN_MensajeroSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.mensajero.v1;


/**
 * Clase que contiene todos las propiedades SUT_CN_MensajeroSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 25/03/2020
 */
public class SUT_CN_MensajeroSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4333718460243264588L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
				        "entradaEnviarMensaje"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
				        "tipoEntradaEnviarMensaje"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "enviarMensaje", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
			        "salidaEnviarMensaje"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
		        "tipoSalidaEnviarMensaje"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "EnviarMensaje"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cn/mensajero/v1/EnviarMensaje");
		_myOperationsList.add(_oper);

		if(_myOperations.get("enviarMensaje") == null)
			_myOperations.put("enviarMensaje", new java.util.ArrayList());

		((java.util.List)_myOperations.get("enviarMensaje")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
				        "entradaConsultarEnvio"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
				        "tipoEntradaConsultarEnvio"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarEnvio", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			        "salidaConsultarEnvio"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        "tipoSalidaConsultarEnvio"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarEnvio"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cn/mensajero/v1/ConsultarEnvio");
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarEnvio") == null)
			_myOperations.put("consultarEnvio", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarEnvio")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType impl;

	/**
	 * Instancia un nuevo objeto SU T C N mensajero SOAP 12 binding skeleton.
	 */
	public SUT_CN_MensajeroSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_MensajeroSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SU T C N mensajero SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SUT_CN_MensajeroSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio consultarEnvio(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio ret = impl
				.consultarEnvio(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje enviarMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje ret = impl
				.enviarMensaje(entrada);

		return ret;
	}
}
