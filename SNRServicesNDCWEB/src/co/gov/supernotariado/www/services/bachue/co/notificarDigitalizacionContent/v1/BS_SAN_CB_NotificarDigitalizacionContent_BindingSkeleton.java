/**
 * BS_SAN_CB_NotificarDigitalizacionContent_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1;

public class BS_SAN_CB_NotificarDigitalizacionContent_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_PortType,
	org.apache.axis.wsdl.Skeleton
{
	private static final long           serialVersionUID  = 1647592677413211074L;
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
				        "https://www.supernotariado.gov.co/schemas/bachue/co/notificarDigitalizacionContent/notificarDigitalizacionContent/v1",
				        "entradaNotificarDigitalizacionContent"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/co/notificarDigitalizacionContent/notificarDigitalizacionContent/v1",
				        "tipoEntradaNotificarDigitalizacionContent"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoEntradaNotificarDigitalizacionContent.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "notificarDigitalizacionContent", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/co/notificarDigitalizacionContent/notificarDigitalizacionContent/v1",
			        "salidaNotificarDigitalizacionContent"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/notificarDigitalizacionContent/notificarDigitalizacionContent/v1",
		        "tipoSalidaNotificarDigitalizacionContent"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "NotificarDigitalizacionContent"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/co/notificarDigitalizacionContent/v1/NotificarDigitalizacionContent"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("notificarDigitalizacionContent") == null)
			_myOperations.put("notificarDigitalizacionContent", new java.util.ArrayList());

		((java.util.List)_myOperations.get("notificarDigitalizacionContent")).add(_oper);
	}

	private co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_PortType impl;

	public BS_SAN_CB_NotificarDigitalizacionContent_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_BindingImpl();
	}

	public BS_SAN_CB_NotificarDigitalizacionContent_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_PortType impl
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

	public co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoSalidaNotificarDigitalizacionContent notificarDigitalizacionContent(
	    co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoEntradaNotificarDigitalizacionContent entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoSalidaNotificarDigitalizacionContent ret =
			impl.notificarDigitalizacionContent(entrada);

		return ret;
	}
}
