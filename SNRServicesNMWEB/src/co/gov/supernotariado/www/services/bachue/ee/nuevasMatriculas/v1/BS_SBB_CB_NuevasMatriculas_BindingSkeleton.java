/**
 * BS_SBB_CB_NuevasMatriculas_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_NuevasMatriculas_BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class BS_SBB_CB_NuevasMatriculas_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1.BS_SBB_CB_NuevasMatriculas_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 231385108689634308L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
				        "entradaConsultarNuevasMatriculas"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
				        "tipoEntradaConsultarNuevasMatriculas"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculas.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarNuevasMatriculas", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
			        "salidaConsultarNuevasMatriculas"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "tipoSalidaConsultarNuevasMatriculas"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarNuevasMatriculas"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/ee/NuevasMatriculas/v1/ConsultarNuevasMatriculas"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarNuevasMatriculas") == null)
			_myOperations.put("consultarNuevasMatriculas", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarNuevasMatriculas")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1.BS_SBB_CB_NuevasMatriculas_PortType impl;

	/**
	 * Instancia un nuevo objeto b S SB B C B nuevas matriculas binding skeleton.
	 */
	public BS_SBB_CB_NuevasMatriculas_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1.BS_SBB_CB_NuevasMatriculas_BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B nuevas matriculas binding skeleton.
	 *
	 * @param impl de impl
	 */
	public BS_SBB_CB_NuevasMatriculas_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1.BS_SBB_CB_NuevasMatriculas_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculas consultarNuevasMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculas entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculas ret =
			impl.consultarNuevasMatriculas(entrada);

		return ret;
	}
}
