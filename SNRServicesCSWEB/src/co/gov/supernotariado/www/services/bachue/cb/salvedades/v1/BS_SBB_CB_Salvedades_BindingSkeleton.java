/**
 * BS_SBB_CB_Salvedades_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.salvedades.v1;

public class BS_SBB_CB_Salvedades_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.BS_SBB_CB_Salvedades_PortType,
	org.apache.axis.wsdl.Skeleton
{
	private static final long           serialVersionUID  = -777102230664213463L;
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
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
				        "entradaregistrarCambioSalvedades"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
				        "tipoEntradaregistrarCambioSalvedades"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedades.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registraCambioSalvedades", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
			        "salidaregistrarCambioSalvedades"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "tipoSalidaregistrarCambioSalvedades"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistraCambioSalvedades"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registraCambioSalvedades") == null)
			_myOperations.put("registraCambioSalvedades", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registraCambioSalvedades")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarIdentificadoresCatastrales/v1",
				        "entradaRegistrarIdentificadoresCatastrales"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarIdentificadoresCatastrales/v1",
				        "tipoEntradaRegistrarIdentificadoresCatastrales"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoEntradaRegistrarIdentificadoresCatastrales.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registraIdentificadoresCatastrales", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarIdentificadoresCatastrales/v1",
			        "salidaRegistrarIdentificadoresCatastrales"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarIdentificadoresCatastrales/v1",
		        "tipoSalidaRegistrarIdentificadoresCatastrales"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistraIdentificadoresCatastrales"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarIdentificadoresCatastrales/v1"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registraIdentificadoresCatastrales") == null)
			_myOperations.put("registraIdentificadoresCatastrales", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registraIdentificadoresCatastrales")).add(_oper);
	}

	private co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.BS_SBB_CB_Salvedades_PortType impl;

	public BS_SBB_CB_Salvedades_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.BS_SBB_CB_Salvedades_BindingImpl();
	}

	public BS_SBB_CB_Salvedades_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.BS_SBB_CB_Salvedades_PortType impl
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

	public co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoSalidaregistrarCambioSalvedades registraCambioSalvedades(
	    co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedades entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoSalidaregistrarCambioSalvedades ret =
			impl.registraCambioSalvedades(entrada);

		return ret;
	}

	public co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoSalidaRegistrarIdentificadoresCatastrales registraIdentificadoresCatastrales(
	    co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoEntradaRegistrarIdentificadoresCatastrales entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoSalidaRegistrarIdentificadoresCatastrales ret =
			impl.registraIdentificadoresCatastrales(entrada);

		return ret;
	}
}
