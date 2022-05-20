/**
 * SBB_CB_InmueblesSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1;


/**
 * Clase que contiene todos las propiedades SBB_CB_InmueblesSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_InmueblesSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_Inmuebles_PortType,
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
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
				        "entradaDatosInmueble"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
				        "tipoEntradaDatosInmueble"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarDatosInmueble", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			        "salidaDatosInmueble"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "tipoSalidaDatosInmueble"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarDatosInmueble"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarDatosInmueble");
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarDatosInmueble") == null)
			_myOperations.put("consultarDatosInmueble", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarDatosInmueble")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
				        "entradaDatosPropietario"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
				        "tipoEntradaDatosPropietario"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarPropietarios", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			        "salidaDatosPropietario"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
		        "tipoSalidaDatosPropietario"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarPropietarios"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarPropietarios");
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarPropietarios") == null)
			_myOperations.put("consultarPropietarios", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarPropietarios")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
				        "entradaDireccionesAnteriores"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
				        "tipoEntradaDireccionesAnteriores"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarDireccionesAnteriores", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			        "salidaDireccionesAnteriores"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
		        "tipoSalidaDireccionesAnteriores"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarDireccionesAnteriores"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarDireccionesAnteriores"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarDireccionesAnteriores") == null)
			_myOperations.put("consultarDireccionesAnteriores", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarDireccionesAnteriores")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
				        "entradaConsultaMatriculas"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
				        "tipoEntradaConsultaMatriculas"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarMatriculas", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			        "salidaConsultaMatriculas"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        "tipoSalidaConsultaMatriculas"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarMatriculas"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarMatriculas");
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarMatriculas") == null)
			_myOperations.put("consultarMatriculas", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarMatriculas")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
				        "entradaDatosBasicos"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
				        "tipoEntradaDatosBasicosMatriculas"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarDatosBasicos", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			        "salidaDatosBasicos"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "tipoSalidaDatosBasicosMatriculas"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarDatosBasicos"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarDatosBasicos");
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarDatosBasicos") == null)
			_myOperations.put("consultarDatosBasicos", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarDatosBasicos")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_Inmuebles_PortType impl;

	/**
	 * Instancia un nuevo objeto SB B C B inmuebles SOAP 12 binding skeleton.
	 */
	public SBB_CB_InmueblesSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_InmueblesSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SB B C B inmuebles SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SBB_CB_InmueblesSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_Inmuebles_PortType impl
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
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas consultarDatosBasicos(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas ret =
			impl.consultarDatosBasicos(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble consultarDatosInmueble(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble ret = impl
				.consultarDatosInmueble(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores consultarDireccionesAnteriores(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores ret =
			impl.consultarDireccionesAnteriores(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas consultarMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas ret = impl
				.consultarMatriculas(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario consultarPropietarios(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario ret = impl
				.consultarPropietarios(entrada);

		return ret;
	}
}
