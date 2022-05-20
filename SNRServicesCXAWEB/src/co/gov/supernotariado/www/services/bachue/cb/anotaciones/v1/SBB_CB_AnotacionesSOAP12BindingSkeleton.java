/**
 * SBB_CB_AnotacionesSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.anotaciones.v1;


/**
 * Clase que contiene todos las propiedades SBB_CB_AnotacionesSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_AnotacionesSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.anotaciones.v1.SBB_CB_Anotaciones_PortType,
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
				        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
				        "entradaDatosAnotacion"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
				        "tipoEntradaDatosAnotacion"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacion.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarAnotaciones", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
			        "salidaDatosAnotacion"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "tipoSalidaDatosAnotacion"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarAnotaciones"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/operacionesAnotaciones/v1/consultarAnotaciones"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarAnotaciones") == null)
			_myOperations.put("consultarAnotaciones", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarAnotaciones")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.anotaciones.v1.SBB_CB_Anotaciones_PortType impl;

	/**
	 * Instancia un nuevo objeto SB B C B anotaciones SOAP 12 binding skeleton.
	 */
	public SBB_CB_AnotacionesSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.anotaciones.v1.SBB_CB_AnotacionesSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SB B C B anotaciones SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SBB_CB_AnotacionesSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.v1.SBB_CB_Anotaciones_PortType impl
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
	public co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacion[] consultarAnotaciones(
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacion entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacion[] ret = impl
				.consultarAnotaciones(entrada);

		return ret;
	}
}
