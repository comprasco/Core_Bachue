/**
 * SBB_CB_OperacionesFinancierasSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.operacionesfinancieras.v2;


/**
 * Clase que contiene todos las propiedades SBB_CB_OperacionesFinancierasSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_OperacionesFinancierasSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.operacionesfinancieras.v2.SBB_CB_OperacionesFinancieras_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4462463147579555626L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
				        "entradaConsultarTarifaServicio"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
				        "tipoEntradaConsultarTarifaServicio"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicio.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarTarifaServicio", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
			        "salidaConsultarTarifaServicio"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "tipoSalidaConsultarTarifaServicio"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarTarifaServicio"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/operacionesfinancieras/v2/ConsultarTarifaServicio"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarTarifaServicio") == null)
			_myOperations.put("consultarTarifaServicio", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarTarifaServicio")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
				        "entradaGenerarLiquidacion"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
				        "tipoEntradaGenerarLiquidacion"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoEntradaGenerarLiquidacion.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "generarLiquidacion", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
			        "salidaGenerarLiquidacion"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "tipoSalidaGenerarLiquidacion"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "GenerarLiquidacion"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/operacionesfinancieras/v2/GenerarLiquidacion"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("generarLiquidacion") == null)
			_myOperations.put("generarLiquidacion", new java.util.ArrayList());

		((java.util.List)_myOperations.get("generarLiquidacion")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
				        "entradaRegistrarPago"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
				        "tipoEntradaRegistrarPago"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoEntradaRegistrarPago.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registrarPago", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
			        "salidaRegistrarPago"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
		        "tipoSalidaRegistrarPago"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistrarPago"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/operacionesfinancieras/v2/RegistrarPago"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registrarPago") == null)
			_myOperations.put("registrarPago", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registrarPago")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/obtenerclavepdfliquidacion/v2",
				        "entradaObtenerClavePDFLiquidacion"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/obtenerclavepdfliquidacion/v2",
				        "tipoEntradaObtenerClavePDFLiquidacion"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoEntradaObtenerClavePDFLiquidacion.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerClavePDFLiquidacion", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/obtenerclavepdfliquidacion/v2",
			        "salidaObtenerClavePDFLiquidacion"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/obtenerclavepdfliquidacion/v2",
		        "tipoSalidaObtenerClavePDFLiquidacion"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerClavePDFLiquidacion"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/operacionesfinancieras/v2/ObtenerClavePDFLiquidacion"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerClavePDFLiquidacion") == null)
			_myOperations.put("obtenerClavePDFLiquidacion", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerClavePDFLiquidacion")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
				        "entradaActualizarDatosSolicitante"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
				        "tipoEntradaActualizarDatosSolicitante"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoEntradaActualizarDatosSolicitante.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "actualizarDatosSolicitante", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
			        "salidaActualizarDatosSolicitante"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "tipoSalidaActualizarDatosSolicitante"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ActualizarDatosSolicitante"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/operacionesfinancieras/v2/ActualizarDatosSolicitante"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("actualizarDatosSolicitante") == null)
			_myOperations.put("actualizarDatosSolicitante", new java.util.ArrayList());

		((java.util.List)_myOperations.get("actualizarDatosSolicitante")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/validaractualizaciondatossolicitante/v2",
				        "entradaValidarActualizacionDatosSolicitante"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/validaractualizaciondatossolicitante/v2",
				        "tipoEntradaValidarActualizacionDatosSolicitante"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoEntradaValidarActualizacionDatosSolicitante.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "validarActualizacionDatosSolicitante", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/validaractualizaciondatossolicitante/v2",
			        "salidaValidarActualizacionDatosSolicitante"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/validaractualizaciondatossolicitante/v2",
		        "tipoSalidaValidarActualizacionDatosSolicitante"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ValidarActualizacionDatosSolicitante"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/operacionesfinancieras/v2/ValidarActualizacionDatosSolicitante"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("validarActualizacionDatosSolicitante") == null)
			_myOperations.put("validarActualizacionDatosSolicitante", new java.util.ArrayList());

		((java.util.List)_myOperations.get("validarActualizacionDatosSolicitante")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.operacionesfinancieras.v2.SBB_CB_OperacionesFinancieras_PortType impl;

	/**
	 * Instancia un nuevo objeto SB B C B operaciones financieras SOAP 12 binding skeleton.
	 */
	public SBB_CB_OperacionesFinancierasSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.operacionesfinancieras.v2.SBB_CB_OperacionesFinancierasSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SB B C B operaciones financieras SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SBB_CB_OperacionesFinancierasSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.operacionesfinancieras.v2.SBB_CB_OperacionesFinancieras_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoSalidaActualizarDatosSolicitante actualizarDatosSolicitante(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoEntradaActualizarDatosSolicitante entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoSalidaActualizarDatosSolicitante ret =
			impl.actualizarDatosSolicitante(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoSalidaConsultarTarifaServicio consultarTarifaServicio(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicio entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoSalidaConsultarTarifaServicio ret =
			impl.consultarTarifaServicio(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoSalidaGenerarLiquidacion generarLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoEntradaGenerarLiquidacion entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoSalidaGenerarLiquidacion ret =
			impl.generarLiquidacion(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoSalidaObtenerClavePDFLiquidacion obtenerClavePDFLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoEntradaObtenerClavePDFLiquidacion entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoSalidaObtenerClavePDFLiquidacion ret =
			impl.obtenerClavePDFLiquidacion(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoSalidaRegistrarPago registrarPago(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoEntradaRegistrarPago entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoSalidaRegistrarPago ret = impl
				.registrarPago(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoSalidaValidarActualizacionDatosSolicitante validarActualizacionDatosSolicitante(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoEntradaValidarActualizacionDatosSolicitante entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoSalidaValidarActualizacionDatosSolicitante ret =
			impl.validarActualizacionDatosSolicitante(entrada);

		return ret;
	}
}
