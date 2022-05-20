/**
 * BS_SBB_CB_PartesInteresadas_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_PartesInteresadas_BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class BS_SBB_CB_PartesInteresadas_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1744338519871345527L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
				        "entradaConsultarPartesInteresadas"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
				        "tipoEntradaConsultarPartesInteresadas"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadas.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarPartesInteresadas", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
			        "salidaConsultarPartesInteresadas"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "tipoSalidaConsultarPartesInteresadas"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarPartesInteresadas"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/ee/partesinteresadas/v1/ConsultarPartesInteresadas"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarPartesInteresadas") == null)
			_myOperations.put("consultarPartesInteresadas", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarPartesInteresadas")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_PortType impl;

	/**
	 * Instancia un nuevo objeto b S SB B C B partes interesadas binding skeleton.
	 */
	public BS_SBB_CB_PartesInteresadas_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B partes interesadas binding skeleton.
	 *
	 * @param impl de impl
	 */
	public BS_SBB_CB_PartesInteresadas_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadas consultarPartesInteresadas(
	    co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadas entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadas ret =
			impl.consultarPartesInteresadas(entrada);

		return ret;
	}
}
