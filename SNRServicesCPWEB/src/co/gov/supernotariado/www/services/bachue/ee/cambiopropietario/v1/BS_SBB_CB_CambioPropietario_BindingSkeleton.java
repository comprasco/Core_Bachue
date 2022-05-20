/**
 * BS_SBB_CB_CambioPropietario_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.cambiopropietario.v1;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_CambioPropietario_BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/04/2020
 */
public class BS_SBB_CB_CambioPropietario_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ee.cambiopropietario.v1.BS_SBB_CB_CambioPropietario_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 88197173757296758L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
				        "entradaConsultarCambioPropietario"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
				        "tipoEntradaConsultarCambioPropietario"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoEntradaConsultarCambioPropietario.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarCambioPropietario", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
			        "salidaConsultarCambioPropietario"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "tipoSalidaConsultarCambioPropietario"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarCambioPropietario"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/v1/consultarCambioPropietario"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarCambioPropietario") == null)
			_myOperations.put("consultarCambioPropietario", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarCambioPropietario")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.ee.cambiopropietario.v1.BS_SBB_CB_CambioPropietario_PortType impl;

	/**
	 * Instancia un nuevo objeto b S SB B C B cambio propietario binding skeleton.
	 */
	public BS_SBB_CB_CambioPropietario_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.ee.cambiopropietario.v1.BS_SBB_CB_CambioPropietario_BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B cambio propietario binding skeleton.
	 *
	 * @param impl de impl
	 */
	public BS_SBB_CB_CambioPropietario_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.ee.cambiopropietario.v1.BS_SBB_CB_CambioPropietario_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoSalidaConsultarCambioPropietario consultarCambioPropietario(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoEntradaConsultarCambioPropietario entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoSalidaConsultarCambioPropietario ret =
			impl.consultarCambioPropietario(entrada);

		return ret;
	}
}
