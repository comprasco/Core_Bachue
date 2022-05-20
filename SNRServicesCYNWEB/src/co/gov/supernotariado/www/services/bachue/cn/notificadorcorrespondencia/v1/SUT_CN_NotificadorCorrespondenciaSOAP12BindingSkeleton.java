/**
 * SUT_CN_NotificadorCorrespondenciaSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1;


/**
 * Clase que contiene todos las propiedades SUT_CN_NotificadorCorrespondenciaSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public class SUT_CN_NotificadorCorrespondenciaSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3203582939480617745L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/cn/notificadorcorrespondencia/notificarcorrespondencia/v1",
				        "entradaNotificarCorrespondencia"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cn/notificadorcorrespondencia/notificarcorrespondencia/v1",
				        "tipoEntradaNotificarCorrespondencia"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoEntradaNotificarCorrespondencia.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "notificarCorrespondencia", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cn/notificadorcorrespondencia/notificarcorrespondencia/v1",
			        "salidaNotificarCorrespondencia"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/notificadorcorrespondencia/notificarcorrespondencia/v1",
		        "tipoSalidaNotificarCorrespondencia"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "NotificarCorrespondencia"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cn/notificadorcorrespondencia/v1/NotificarCorrespondencia"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("notificarCorrespondencia") == null)
			_myOperations.put("notificarCorrespondencia", new java.util.ArrayList());

		((java.util.List)_myOperations.get("notificarCorrespondencia")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_PortType impl;

	/**
	 * Instancia un nuevo objeto SU T C N notificador correspondencia SOAP 12 binding skeleton.
	 */
	public SUT_CN_NotificadorCorrespondenciaSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondenciaSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SU T C N notificador correspondencia SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SUT_CN_NotificadorCorrespondenciaSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoSalidaNotificarCorrespondencia notificarCorrespondencia(
	    co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoEntradaNotificarCorrespondencia entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoSalidaNotificarCorrespondencia ret =
			impl.notificarCorrespondencia(entrada);

		return ret;
	}
}
