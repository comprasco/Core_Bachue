/**
 * BS_SBB_CB_RecepcionNotificacionBachue_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_RecepcionNotificacionBachue_BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/05/2020
 */
public class BS_SBB_CB_RecepcionNotificacionBachue_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8996292595909974287L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
				        "entradaRegistrarReintentoServicio"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
				        "tipoEntradaRegistrarReintentoServicio"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoEntradaRegistrarReintentoServicio.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registrarReintentoServicio", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
			        "salidaRegistrarReintentoServicio"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
		        "tipoSalidaRegistrarReintentoServicio"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistrarReintentoServicio"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/ci/recepcionNotificacionBachue/v1/RegistrarReintentoServicio"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registrarReintentoServicio") == null)
			_myOperations.put("registrarReintentoServicio", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registrarReintentoServicio")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_PortType impl;

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion bachue binding skeleton.
	 */
	public BS_SBB_CB_RecepcionNotificacionBachue_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion bachue binding skeleton.
	 *
	 * @param impl de impl
	 */
	public BS_SBB_CB_RecepcionNotificacionBachue_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoSalidaRegistrarReintentoServicio registrarReintentoServicio(
	    co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoEntradaRegistrarReintentoServicio entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoSalidaRegistrarReintentoServicio ret =
			impl.registrarReintentoServicio(entrada);

		return ret;
	}
}
