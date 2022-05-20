/**
 * BS_SBB_CB_RRRMatriculas_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1;

public class BS_SBB_CB_RRRMatriculas_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_PortType,
	org.apache.axis.wsdl.Skeleton
{
	private static final long           serialVersionUID  = 3423964337697967331L;
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
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
				        "entradaConsultarRRRMatriculas"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
				        "tipoEntradaConsultarRRRMatriculas"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarRRRMatriculas", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			        "salidaConsultarRRRMatriculas"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "tipoSalidaConsultarRRRMatriculas"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarRRRMatriculas"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/ee/RRRMatriculas/v1/ConsultarRRRMatriculas"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarRRRMatriculas") == null)
			_myOperations.put("consultarRRRMatriculas", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarRRRMatriculas")).add(_oper);
	}

	private co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_PortType impl;

	public BS_SBB_CB_RRRMatriculas_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_BindingImpl();
	}

	public BS_SBB_CB_RRRMatriculas_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_PortType impl
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

	public co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas consultarRRRMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas ret =
			impl.consultarRRRMatriculas(entrada);

		return ret;
	}
}
