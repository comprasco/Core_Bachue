/**
 * SBB_CB_HistoricoPropietariosSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.v1;


/**
 * Clase que contiene todos las propiedades SBB_CB_HistoricoPropietariosSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_HistoricoPropietariosSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.v1.SBB_CB_HistoricoPropietarios_PortType,
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
				        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
				        "entradaConsultarHistoricoPropiedades"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
				        "tipoEntradaConsultarHistoricoPropiedades"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedades.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarHistoricoPropiedades", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
			        "salidaConsultarHistoricoPropiedades"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "tipoSalidaConsultarHistoricoPropiedades"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarHistoricoPropiedades"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/v1/consultarHistoricoPropiedades"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarHistoricoPropiedades") == null)
			_myOperations.put("consultarHistoricoPropiedades", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarHistoricoPropiedades")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
				        "entradaConsultarHistoricoPropietarios"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
				        "tipoEntradaConsultarHistoricoPropietarios"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoEntradaConsultarHistoricoPropietarios.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarHistoricoPropietarios", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
			        "salidaConsultarHistoricoPropietarios"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "tipoSalidaConsultarHistoricoPropietarios"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarHistoricoPropietarios"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/v1/consultarHistoricoPropietarios"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarHistoricoPropietarios") == null)
			_myOperations.put("consultarHistoricoPropietarios", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarHistoricoPropietarios")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.v1.SBB_CB_HistoricoPropietarios_PortType impl;

	/**
	 * Instancia un nuevo objeto SB B C B historico propietarios SOAP 12 binding skeleton.
	 */
	public SBB_CB_HistoricoPropietariosSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.v1.SBB_CB_HistoricoPropietariosSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SB B C B historico propietarios SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SBB_CB_HistoricoPropietariosSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.v1.SBB_CB_HistoricoPropietarios_PortType impl
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
	public co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoSalidaConsultarHistoricoPropiedades consultarHistoricoPropiedades(
	    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedades entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoSalidaConsultarHistoricoPropiedades ret =
			impl.consultarHistoricoPropiedades(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoSalidaConsultarHistoricoPropietarios consultarHistoricoPropietarios(
	    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoEntradaConsultarHistoricoPropietarios entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoSalidaConsultarHistoricoPropietarios ret =
			impl.consultarHistoricoPropietarios(entrada);

		return ret;
	}
}
