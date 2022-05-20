/**
 * SBB_CB_MutacionesTercerOrden_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.mutacionesTercerOrden.v1;

public class SBB_CB_MutacionesTercerOrden_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.mutacionesTercerOrden.v1.SBB_CB_MutacionesTercerOrden_PortType,
	org.apache.axis.wsdl.Skeleton
{
	private static final long           serialVersionUID  = 5825838268203296666L;
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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
				        "entradaConsultaCambioTercerOrden"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
				        "tipoEntradaConsultaCambioTercerOrden"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoEntradaConsultaCambioTercerOrden.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultaCambioTercerOrden", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
			        "salidaConsultaCambioTercerOrden"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "tipoSalidaConsultaCambioTercerOrden"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultaCambioTercerOrden"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/mutacionestercerorden/v1/ConsultaCambioTercerOrden"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultaCambioTercerOrden") == null)
			_myOperations.put("consultaCambioTercerOrden", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultaCambioTercerOrden")).add(_oper);
	}

	private co.gov.supernotariado.www.services.bachue.cb.mutacionesTercerOrden.v1.SBB_CB_MutacionesTercerOrden_PortType impl;

	public SBB_CB_MutacionesTercerOrden_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.mutacionesTercerOrden.v1.SBB_CB_MutacionesTercerOrden_BindingImpl();
	}

	public SBB_CB_MutacionesTercerOrden_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.mutacionesTercerOrden.v1.SBB_CB_MutacionesTercerOrden_PortType impl
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

	public co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrden consultaCambioTercerOrden(
	    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoEntradaConsultaCambioTercerOrden entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrden ret =
			impl.consultaCambioTercerOrden(entrada);

		return ret;
	}
}
