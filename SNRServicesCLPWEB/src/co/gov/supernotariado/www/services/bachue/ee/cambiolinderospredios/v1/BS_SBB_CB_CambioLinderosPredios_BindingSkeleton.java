/**
 * BS_SBB_CB_CambioLinderosPredios_BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.cambiolinderospredios.v1;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_CambioLinderosPredios_BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class BS_SBB_CB_CambioLinderosPredios_BindingSkeleton implements co.gov.supernotariado.www.services.bachue.ee.cambiolinderospredios.v1.BS_SBB_CB_CambioLinderosPredios_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6309704382041761455L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
				        "entradaConsultaSegregacionSinCambioPropietario"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
				        "tipoEntradaConsultaSegregacionSinCambioPropietario"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoEntradaConsultaSegregacionSinCambioPropietario.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultaSegregacionSinCambioPropietario", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
			        "salidaConsultaSegregacionSinCambioPropietario"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
		        "tipoSalidaConsultaSegregacionSinCambioPropietario"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultaSegregacionSinCambioPropietario"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultaSegregacionSinCambioPropietario") == null)
			_myOperations.put("consultaSegregacionSinCambioPropietario", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultaSegregacionSinCambioPropietario")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
				        "entradaConsultaSegregacionConCambioPropietario"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
				        "tipoEntradaConsultaSegregacionConCambioPropietario"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoEntradaConsultaSegregacionConCambioPropietario.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultaSegregacionConCambioPropietario", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
			        "salidaConsultaSegregacionConCambioPropietario"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "tipoSalidaConsultaSegregacionConCambioPropietario"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultaSegregacionConCambioPropietario"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultaSegregacionConCambioPropietario") == null)
			_myOperations.put("consultaSegregacionConCambioPropietario", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultaSegregacionConCambioPropietario")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.ee.cambiolinderospredios.v1.BS_SBB_CB_CambioLinderosPredios_PortType impl;

	/**
	 * Instancia un nuevo objeto b S SB B C B cambio linderos predios binding skeleton.
	 */
	public BS_SBB_CB_CambioLinderosPredios_BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.ee.cambiolinderospredios.v1.BS_SBB_CB_CambioLinderosPredios_BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B cambio linderos predios binding skeleton.
	 *
	 * @param impl de impl
	 */
	public BS_SBB_CB_CambioLinderosPredios_BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.ee.cambiolinderospredios.v1.BS_SBB_CB_CambioLinderosPredios_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaConsultaSegregacionConCambioPropietario consultaSegregacionConCambioPropietario(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoEntradaConsultaSegregacionConCambioPropietario entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaConsultaSegregacionConCambioPropietario ret =
			impl.consultaSegregacionConCambioPropietario(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaConsultaSegregacionSinCambioPropietario consultaSegregacionSinCambioPropietario(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoEntradaConsultaSegregacionSinCambioPropietario entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaConsultaSegregacionSinCambioPropietario ret =
			impl.consultaSegregacionSinCambioPropietario(entrada);

		return ret;
	}
}
