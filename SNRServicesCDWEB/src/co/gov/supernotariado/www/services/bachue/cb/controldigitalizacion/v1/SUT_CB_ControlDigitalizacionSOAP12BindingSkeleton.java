/**
 * SUT_CB_ControlDigitalizacionSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.controldigitalizacion.v1;

public class SUT_CB_ControlDigitalizacionSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.controldigitalizacion.v1.SUT_CB_ControlDigitalizacion_PortType,
	org.apache.axis.wsdl.Skeleton
{
	private static final long           serialVersionUID  = 8928329851234665901L;
	private static java.util.Map        _myOperations     = new java.util.Hashtable();
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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/controldigitalizacion/notificardigitalizacion/v1",
				        "entradaNotificarDigitalizacion"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/controldigitalizacion/notificardigitalizacion/v1",
				        "tipoEntradaNotificarDigitalizacion"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoEntradaNotificarDigitalizacion.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "notificarDigitalizacion", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/controldigitalizacion/notificardigitalizacion/v1",
			        "salidaNotificarDigitalizacion"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/controldigitalizacion/notificardigitalizacion/v1",
		        "tipoSalidaNotificarDigitalizacion"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "NotificarDigitalizacion"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/controldigitalizacion/v1/NotificarDigitalizacion"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("notificarDigitalizacion") == null)
			_myOperations.put("notificarDigitalizacion", new java.util.ArrayList());

		((java.util.List)_myOperations.get("notificarDigitalizacion")).add(_oper);
	}

	private co.gov.supernotariado.www.services.bachue.cb.controldigitalizacion.v1.SUT_CB_ControlDigitalizacion_PortType impl;

	public SUT_CB_ControlDigitalizacionSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.controldigitalizacion.v1.SUT_CB_ControlDigitalizacionSOAP12BindingImpl();
	}

	public SUT_CB_ControlDigitalizacionSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.controldigitalizacion.v1.SUT_CB_ControlDigitalizacion_PortType impl
	)
	{
		this.impl = impl;
	}

	/**
	* Returns List of OperationDesc objects with this name
	*/
	public static java.util.List getOperationDescByName(java.lang.String methodName)
	{
		return (java.util.List)_myOperations.get(methodName);
	}

	/**
	* Returns Collection of OperationDescs
	*/
	public static java.util.Collection getOperationDescs()
	{
		return _myOperationsList;
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoSalidaNotificarDigitalizacion notificarDigitalizacion(
	    co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoEntradaNotificarDigitalizacion entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoSalidaNotificarDigitalizacion ret =
			impl.notificarDigitalizacion(entrada);

		return ret;
	}
}
