/**
 * SBB_CB_SalvedadesSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.salvedades.v1;

public class SBB_CB_SalvedadesSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_Salvedades_PortType,
	org.apache.axis.wsdl.Skeleton
{
	private static final long           serialVersionUID  = 3141181581958486155L;
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
				        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
				        "entradaDatosSalvedades"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
				        "tipoEntradaSalvedades"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoEntradaSalvedades.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarSalvedades", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
			        "salidaDatosSalvedades"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
		        "tipoSalidaSalvedades"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarSalvedades"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/salvedades/v1/consultarSalvedades");
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarSalvedades") == null)
			_myOperations.put("consultarSalvedades", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarSalvedades")).add(_oper);
	}

	private co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_Salvedades_PortType impl;

	public SBB_CB_SalvedadesSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_SalvedadesSOAP12BindingImpl();
	}

	public SBB_CB_SalvedadesSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_Salvedades_PortType impl
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

	public co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalidaSalvedades consultarSalvedades(
	    co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoEntradaSalvedades entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalidaSalvedades ret = impl
				.consultarSalvedades(entrada);

		return ret;
	}
}
