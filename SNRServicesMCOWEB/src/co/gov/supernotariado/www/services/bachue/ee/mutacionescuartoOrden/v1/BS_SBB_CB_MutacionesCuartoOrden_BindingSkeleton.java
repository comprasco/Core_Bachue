/**
 * BS_SBB_CB_MutacionesCuartoOrden_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_MutacionesCuartoOrden_BindingSkeleton.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 7/05/2020
 */
public class BS_SBB_CB_MutacionesCuartoOrden_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1.BS_SBB_CB_MutacionesCuartoOrden_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8139224809033439941L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
				        "entradaRegistrarCambioCuartoOrden"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
				        "tipoEntradaRegistrarCambioCuartoOrden"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrden.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registraCambioCuartoOrden", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
			        "salidaRegistrarCambioCuartoOrden"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        "tipoSalidaRegistrarCambioCuartoOrden"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistraCambioCuartoOrden"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/ee/mutacionescuartoOrden/v1/RegistraCambioCuartoOrden"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registraCambioCuartoOrden") == null)
			_myOperations.put("registraCambioCuartoOrden", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registraCambioCuartoOrden")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1.BS_SBB_CB_MutacionesCuartoOrden_PortType impl;

	/**
	 * Instancia un nuevo objeto b S SB B C B mutaciones cuarto orden binding skeleton.
	 */
	public BS_SBB_CB_MutacionesCuartoOrden_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1.BS_SBB_CB_MutacionesCuartoOrden_BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B mutaciones cuarto orden binding skeleton.
	 *
	 * @param impl de impl
	 */
	public BS_SBB_CB_MutacionesCuartoOrden_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1.BS_SBB_CB_MutacionesCuartoOrden_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoSalidaRegistrarCambioCuartoOrden registraCambioCuartoOrden(
	    co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrden entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoSalidaRegistrarCambioCuartoOrden ret =
			impl.registraCambioCuartoOrden(entrada);

		return ret;
	}
}
