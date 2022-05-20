/**
 * SBB_CB_MutacionesQuintoOrden_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.mutacionesQuintoOrden.v1;

public class SBB_CB_MutacionesQuintoOrden_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.mutacionesQuintoOrden.v1.SBB_CB_MutacionesQuintoOrden_PortType,
	org.apache.axis.wsdl.Skeleton
{
	private static final long           serialVersionUID  = -1077437864763041641L;
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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
				        "entradaRegistrarCambioQuintoOrden"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
				        "tipoEntradaRegistrarCambioQuintoOrden"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrden.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registraCambioQuintoOrden", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
			        "salidaRegistrarCambioQuintoOrden"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "tipoSalidaRegistrarCambioQuintoOrden"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistraCambioQuintoOrden"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/mutacionesQuintoOrden/v1/RegistraCambioQuintoOrden"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registraCambioQuintoOrden") == null)
			_myOperations.put("registraCambioQuintoOrden", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registraCambioQuintoOrden")).add(_oper);
	}

	private co.gov.supernotariado.www.services.bachue.cb.mutacionesQuintoOrden.v1.SBB_CB_MutacionesQuintoOrden_PortType impl;

	public SBB_CB_MutacionesQuintoOrden_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.mutacionesQuintoOrden.v1.SBB_CB_MutacionesQuintoOrden_BindingImpl();
	}

	public SBB_CB_MutacionesQuintoOrden_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.mutacionesQuintoOrden.v1.SBB_CB_MutacionesQuintoOrden_PortType impl
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

	public co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoSalidaRegistrarCambioQuintoOrden registraCambioQuintoOrden(
	    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrden entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoSalidaRegistrarCambioQuintoOrden ret =
			impl.registraCambioQuintoOrden(entrada);

		return ret;
	}
}
