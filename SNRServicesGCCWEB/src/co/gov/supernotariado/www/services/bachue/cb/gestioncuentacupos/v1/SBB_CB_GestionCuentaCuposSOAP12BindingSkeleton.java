/**
 * SBB_CB_GestionCuentaCuposSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1;


/**
 * Clase que contiene todos las propiedades SBB_CB_GestionCuentaCuposSOAP12BindingSkeleton.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/09/2020
 */
public class SBB_CB_GestionCuentaCuposSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType,
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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/inscribirusuario/v1",
				        "entradaInscribirUsuario"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/inscribirusuario/v1",
				        "tipoEntradaInscribirUsuario"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoEntradaInscribirUsuario.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "inscribirUsuario", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/inscribirusuario/v1",
			        "salidaInscribirUsuario"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/inscribirusuario/v1",
		        "tipoSalidaInscribirUsuario"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "InscribirUsuario"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestioncuentacupos/v1/InscribirUsuario"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("inscribirUsuario") == null)
			_myOperations.put("inscribirUsuario", new java.util.ArrayList());

		((java.util.List)_myOperations.get("inscribirUsuario")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/cancelarusuario/v1",
				        "entradaCancelarUsuario"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/cancelarusuario/v1",
				        "tipoEntradaCancelarUsuario"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoEntradaCancelarUsuario.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "cancelarUsuario", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/cancelarusuario/v1",
			        "salidaCancelarUsuario"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/cancelarusuario/v1",
		        "tipoSalidaCancelarUsuario"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "CancelarUsuario"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestioncuentacupos/v1/CancelarUsuario"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("cancelarUsuario") == null)
			_myOperations.put("cancelarUsuario", new java.util.ArrayList());

		((java.util.List)_myOperations.get("cancelarUsuario")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuario/v1",
				        "entradaConsultarUsuario"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuario/v1",
				        "tipoEntradaConsultarUsuario"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoEntradaConsultarUsuario.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarUsuario", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuario/v1",
			        "salidaConsultarUsuario"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuario/v1",
		        "tipoSalidaConsultarUsuario"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarUsuario"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestioncuentacupos/v1/ConsultarUsuario"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarUsuario") == null)
			_myOperations.put("consultarUsuario", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarUsuario")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
				        "entradaConsultarUsuarios"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
				        "tipoEntradaConsultarUsuarios"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoEntradaConsultarUsuarios.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarUsuarios", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
			        "salidaConsultarUsuarios"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "tipoSalidaConsultarUsuarios"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarUsuarios"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestioncuentacupos/v1/ConsultarUsuarios"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarUsuarios") == null)
			_myOperations.put("consultarUsuarios", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarUsuarios")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
				        "entradaConsultarSaldo"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
				        "tipoEntradaConsultarSaldo"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoEntradaConsultarSaldo.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarSaldo", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
			        "salidaConsultarSaldo"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "tipoSalidaConsultarSaldo"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarSaldo"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestioncuentacupos/v1/ConsultarSaldo"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarSaldo") == null)
			_myOperations.put("consultarSaldo", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarSaldo")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
				        "entradaConsultarMovimientos"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
				        "tipoEntradaConsultarMovimientos"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoEntradaConsultarMovimientos.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarMovimientos", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
			        "salidaConsultarMovimientos"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "tipoSalidaConsultarMovimientos"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarMovimientos"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestioncuentacupos/v1/ConsultarMovimientos"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarMovimientos") == null)
			_myOperations.put("consultarMovimientos", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarMovimientos")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1",
				        "entradaPagarCuentaCupo"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1",
				        "tipoEntradaPagarCuentaCupo"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoEntradaPagarCuentaCupo.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "pagarCuentaCupo", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1",
			        "salidaPagarCuentaCupo"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1",
		        "tipoSalidaPagarCuentaCupo"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "PagarCuentaCupo"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestioncuentacupos/v1/PagarCuentaCupo"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("pagarCuentaCupo") == null)
			_myOperations.put("pagarCuentaCupo", new java.util.ArrayList());

		((java.util.List)_myOperations.get("pagarCuentaCupo")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
				        "entradaConsultarIDCuentaCupo"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
				        "tipoEntradaConsultarIDCuentaCupo"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoEntradaConsultarIDCuentaCupo.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarIDCuentaCupo", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
			        "salidaConsultarIDCuentaCupo"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "tipoSalidaConsultarIDCuentaCupo"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarIDCuentaCupo"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestioncuentacupos/v1/ConsultarIDCuentaCupo"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarIDCuentaCupo") == null)
			_myOperations.put("consultarIDCuentaCupo", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarIDCuentaCupo")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
				        "entradaActualizarEntidad"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
				        "tipoEntradaActualizarEntidad"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEntradaActualizarEntidad.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "actualizarEntidad", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
			        "salidaActualizarEntidad"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "tipoSalidaActualizarEntidad"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ActualizarEntidad"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestioncuentacupos/v1/ActualizarEntidad"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("actualizarEntidad") == null)
			_myOperations.put("actualizarEntidad", new java.util.ArrayList());

		((java.util.List)_myOperations.get("actualizarEntidad")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
				        "entradaConsultarSaldosNotaCredito"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
				        "tipoEntradaConsultarSaldosNotaCredito"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoEntradaConsultarSaldosNotaCredito.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarSaldosNotaCredito", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
			        "salidaConsultarSaldosNotaCredito"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "tipoSalidaConsultarSaldosNotaCredito"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "consultarSaldosNotaCredito"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarSaldosNotaCredito") == null)
			_myOperations.put("consultarSaldosNotaCredito", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarSaldosNotaCredito")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType impl;

	/**
	 * Instancia un nuevo objeto SB B C B gestion cuenta cupos SOAP 12 binding skeleton.
	 */
	public SBB_CB_GestionCuentaCuposSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCuposSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SB B C B gestion cuenta cupos SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SBB_CB_GestionCuentaCuposSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType impl
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

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#inscribirUsuario(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoEntradaInscribirUsuario)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoSalidaInscribirUsuario inscribirUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoEntradaInscribirUsuario entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoSalidaInscribirUsuario ret =
			impl.inscribirUsuario(entrada);

		return ret;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#cancelarUsuario(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoEntradaCancelarUsuario)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoSalidaCancelarUsuario cancelarUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoEntradaCancelarUsuario entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoSalidaCancelarUsuario ret = impl
				.cancelarUsuario(entrada);

		return ret;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#consultarUsuario(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoEntradaConsultarUsuario)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoSalidaConsultarUsuario consultarUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoEntradaConsultarUsuario entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoSalidaConsultarUsuario ret =
			impl.consultarUsuario(entrada);

		return ret;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#consultarUsuarios(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoEntradaConsultarUsuarios)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoSalidaConsultarUsuarios consultarUsuarios(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoEntradaConsultarUsuarios entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoSalidaConsultarUsuarios ret =
			impl.consultarUsuarios(entrada);

		return ret;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#consultarSaldo(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoEntradaConsultarSaldo)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoSalidaConsultarSaldo consultarSaldo(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoEntradaConsultarSaldo entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoSalidaConsultarSaldo ret = impl
				.consultarSaldo(entrada);

		return ret;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#consultarMovimientos(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoEntradaConsultarMovimientos)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoSalidaConsultarMovimientos consultarMovimientos(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoEntradaConsultarMovimientos entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoSalidaConsultarMovimientos ret =
			impl.consultarMovimientos(entrada);

		return ret;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#pagarCuentaCupo(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoEntradaPagarCuentaCupo)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoSalidaPagarCuentaCupo pagarCuentaCupo(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoEntradaPagarCuentaCupo entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoSalidaPagarCuentaCupo ret = impl
				.pagarCuentaCupo(entrada);

		return ret;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#consultarIDCuentaCupo(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoEntradaConsultarIDCuentaCupo)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoSalidaConsultarIDCuentaCupo consultarIDCuentaCupo(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoEntradaConsultarIDCuentaCupo entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoSalidaConsultarIDCuentaCupo ret =
			impl.consultarIDCuentaCupo(entrada);

		return ret;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#actualizarEntidad(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEntradaActualizarEntidad)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoSalidaActualizarEntidad actualizarEntidad(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEntradaActualizarEntidad entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoSalidaActualizarEntidad ret =
			impl.actualizarEntidad(entrada);

		return ret;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType#consultarSaldosNotaCredito(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoEntradaConsultarSaldosNotaCredito)
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCredito consultarSaldosNotaCredito(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoEntradaConsultarSaldosNotaCredito entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCredito ret =
			impl.consultarSaldosNotaCredito(entrada);

		return ret;
	}
}
