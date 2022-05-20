/**
 * SUT_CB_ConsultaMigracionSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1;


/**
 * Clase que contiene todos las propiedades SUT_CB_ConsultaMigracionSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SUT_CB_ConsultaMigracionSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracion_PortType,
	org.apache.axis.wsdl.Skeleton
{
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
				        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
				        "entradaConsultaMigracionPredio"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
				        "tipoEntradaConsultaMigracionPredio"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarMigracionPredio", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			        "salidaConsultaMigracionPredio"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
		        "tipoSalidaConsultaMigracionPredio"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarMigracionPredio"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/v1/consultarMigracionPredio"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarMigracionPredio") == null)
			_myOperations.put("consultarMigracionPredio", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarMigracionPredio")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
				        "entradaConsultaMigracionORIP"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
				        "tipoEntradaConsultaMigracionORIP"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoEntradaConsultaMigracionORIP.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarMigracionORIP", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
			        "salidaConsultaMigracionORIP"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
		        "tipoSalidaConsultaMigracionORIP"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarMigracionORIP"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/v1/consultarMigracionORIP"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarMigracionORIP") == null)
			_myOperations.put("consultarMigracionORIP", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarMigracionORIP")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
				        "entradaConsultaMigracionMatriculas"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
				        "tipoEntradaConsultaMigracionMatriculas"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoEntradaConsultaMigracionMatriculas.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarMigracionMatriculas", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			        "salidaConsultaMigracionMatriculas"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "tipoSalidaConsultaMigracionMatriculas"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarMigracionMatriculas"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/v1/consultarMigracionMatriculas"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarMigracionMatriculas") == null)
			_myOperations.put("consultarMigracionMatriculas", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarMigracionMatriculas")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracion_PortType impl;

	/**
	 * Instancia un nuevo objeto SU T C B consulta migracion SOAP 12 binding skeleton.
	 */
	public SUT_CB_ConsultaMigracionSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracionSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SU T C B consulta migracion SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SUT_CB_ConsultaMigracionSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracion_PortType impl
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
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas consultarMigracionMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoEntradaConsultaMigracionMatriculas entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas ret =
			impl.consultarMigracionMatriculas(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP consultarMigracionORIP(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoEntradaConsultaMigracionORIP entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP ret =
			impl.consultarMigracionORIP(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio consultarMigracionPredio(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio ret =
			impl.consultarMigracionPredio(entrada);

		return ret;
	}
}
