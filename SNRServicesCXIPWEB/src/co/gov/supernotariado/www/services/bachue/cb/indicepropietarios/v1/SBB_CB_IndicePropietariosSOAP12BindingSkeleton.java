/**
 * SBB_CB_IndicePropietariosSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.v1;


/**
 * Clase que contiene todos las propiedades SBB_CB_IndicePropietariosSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_IndicePropietariosSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.v1.SBB_CB_IndicePropietarios_PortType,
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
				        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
				        "entradaIndicePropietarios"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
				        "tipoEntradaIndicePropietarios"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultar", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			        "salidaIndicePropietarios"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
		        "tipoSalidaIndicePropietarios"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultar"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/v1/consultar");
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultar") == null)
			_myOperations.put("consultar", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultar")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.v1.SBB_CB_IndicePropietarios_PortType impl;

	/**
	 * Instancia un nuevo objeto SB B C B indice propietarios SOAP 12 binding skeleton.
	 */
	public SBB_CB_IndicePropietariosSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.v1.SBB_CB_IndicePropietariosSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SB B C B indice propietarios SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SBB_CB_IndicePropietariosSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.v1.SBB_CB_IndicePropietarios_PortType impl
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
	public co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios consultar(
	    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios ret = impl
				.consultar(entrada);

		return ret;
	}
}
