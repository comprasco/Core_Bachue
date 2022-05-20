/**
 * SUT_CB_NotificadorSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.notificador.v1;


/**
 * Clase que contiene todos las propiedades SUT_CB_NotificadorSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 2/04/2020
 */
public class SUT_CB_NotificadorSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1562395753044853670L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/notificador/acusarmensaje/v1",
				        "entradaAcusarMensaje"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/notificador/acusarmensaje/v1",
				        "tipoEntradaAcusarMensaje"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoEntradaAcusarMensaje.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "acusarMensaje", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/notificador/acusarmensaje/v1",
			        "salidaAcusarMensaje"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/notificador/acusarmensaje/v1",
		        "tipoSalidaAcusarMensaje"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "AcusarMensaje"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/notificador/v1/AcusarMensaje");
		_myOperationsList.add(_oper);

		if(_myOperations.get("acusarMensaje") == null)
			_myOperations.put("acusarMensaje", new java.util.ArrayList());

		((java.util.List)_myOperations.get("acusarMensaje")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType impl;

	/**
	 * Instancia un nuevo objeto SU T C B notificador SOAP 12 binding skeleton.
	 */
	public SUT_CB_NotificadorSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_NotificadorSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SU T C B notificador SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SUT_CB_NotificadorSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoSalidaAcusarMensaje acusarMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoEntradaAcusarMensaje entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoSalidaAcusarMensaje ret = impl
				.acusarMensaje(entrada);

		return ret;
	}
}
