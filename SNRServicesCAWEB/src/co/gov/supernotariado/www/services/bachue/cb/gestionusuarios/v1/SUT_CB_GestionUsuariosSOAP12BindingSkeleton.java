/**
 * SUT_CB_GestionUsuariosSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1;


/**
 * Clase que contiene todos las propiedades SUT_CB_GestionUsuariosSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SUT_CB_GestionUsuariosSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1.SUT_CB_GestionUsuarios_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3765445013607757068L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerroles/v1",
				        "entradaObtenerRoles"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerroles/v1",
				        ">entradaObtenerRoles"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.EntradaObtenerRoles.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerRoles", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerroles/v1",
			        "salidaObtenerRoles"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerroles/v1",
		        "tipoSalidaObtenerRoles"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerRoles"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/ObtenerRoles");
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerRoles") == null)
			_myOperations.put("obtenerRoles", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerRoles")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1",
				        "entradaObtenerORIPs"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1",
				        ">entradaObtenerORIPs"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.EntradaObtenerORIPs.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerORIPs", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1",
			        "salidaObtenerORIPs"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1",
		        "tipoSalidaObtenerORIPs"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerORIPs"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/ObtenerORIPs");
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerORIPs") == null)
			_myOperations.put("obtenerORIPs", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerORIPs")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
				        "entradaRegistrarUsuario"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
				        "tipoEntradaRegistrarUsuario"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuario.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registrarUsuario", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
			        "salidaRegistrarUsuario"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "tipoSalidaRegistrarUsuario"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistrarUsuario"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/RegistrarUsuario");
		_myOperationsList.add(_oper);

		if(_myOperations.get("registrarUsuario") == null)
			_myOperations.put("registrarUsuario", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registrarUsuario")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
				        "entradaObtenerEntidadesConvenio"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
				        ">entradaObtenerEntidadesConvenio"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.EntradaObtenerEntidadesConvenio.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerEntidadesConvenio", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
			        "salidaObtenerEntidadesConvenio"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
		        "tipoSalidaObtenerEntidadesConvenio"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerEntidadesConvenio"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/ObtenerEntidadesConvenio"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerEntidadesConvenio") == null)
			_myOperations.put("obtenerEntidadesConvenio", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerEntidadesConvenio")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
				        "entradaObtenerConveniosEntidad"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
				        "tipoEntradaObtenerConveniosEntidad"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoEntradaObtenerConveniosEntidad.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerConveniosEntidad", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
			        "salidaObtenerConveniosEntidad"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
		        "tipoSalidaObtenerConveniosEntidad"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerConveniosEntidad"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/ObtenerConveniosEntidad"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerConveniosEntidad") == null)
			_myOperations.put("obtenerConveniosEntidad", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerConveniosEntidad")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
				        "entradaObtenerRolesUsuario"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
				        "tipoEntradaObtenerRolesUsuario"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoEntradaObtenerRolesUsuario.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerRolesUsuario", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
			        "salidaObtenerRolesUsuario"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
		        "tipoSalidaObtenerRolesUsuario"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerRolesUsuario"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/ObtenerRolesUsuario"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerRolesUsuario") == null)
			_myOperations.put("obtenerRolesUsuario", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerRolesUsuario")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
				        "entradaObtenerRolesComponente"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
				        "tipoEntradaObtenerRolesComponente"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoEntradaObtenerRolesComponente.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerRolesComponente", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
			        "salidaObtenerRolesComponente"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
		        "tipoSalidaObtenerRolesComponente"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerRolesComponente"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/ObtenerRolesComponente"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerRolesComponente") == null)
			_myOperations.put("obtenerRolesComponente", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerRolesComponente")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
				        "entradaObtenerUsuariosRolOrip"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
				        "tipoEntradaObtenerUsuariosRolOrip"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoEntradaObtenerUsuariosRolOrip.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerUsuariosRolOrip", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
			        "salidaObtenerUsuariosRolOrip"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
		        "tipoSalidaObtenerUsuariosRolOrip"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerUsuariosRolOrip"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/ObtenerUsuariosRolOrip"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerUsuariosRolOrip") == null)
			_myOperations.put("obtenerUsuariosRolOrip", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerUsuariosRolOrip")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
				        "entradaObtenerAccesosPorRol"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
				        "tipoEntradaObtenerAccesosPorRol"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoEntradaObtenerAccesosPorRol.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerAccesosPorRol", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
			        "salidaObtenerAccesosPorRol"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "tipoSalidaObtenerAccesosPorRol"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerAccesosPorRol"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/ObtenerAccesosPorRol"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerAccesosPorRol") == null)
			_myOperations.put("obtenerAccesosPorRol", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerAccesosPorRol")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
				        "entradaObtenerModulosPorRol"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
				        "tipoEntradaObtenerModulosPorRol"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoEntradaObtenerModulosPorRol.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerModulosPorRol", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
			        "salidaObtenerModulosPorRol"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
		        "tipoSalidaObtenerModulosPorRol"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerModulosPorRol"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1/ObtenerModulosPorRol"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerModulosPorRol") == null)
			_myOperations.put("obtenerModulosPorRol", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerModulosPorRol")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1.SUT_CB_GestionUsuarios_PortType impl;

	/**
	 * Instancia un nuevo objeto SU T C B gestion usuarios SOAP 12 binding skeleton.
	 */
	public SUT_CB_GestionUsuariosSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1.SUT_CB_GestionUsuariosSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SU T C B gestion usuarios SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SUT_CB_GestionUsuariosSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1.SUT_CB_GestionUsuarios_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoSalidaObtenerAccesosPorRol obtenerAccesosPorRol(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoEntradaObtenerAccesosPorRol entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoSalidaObtenerAccesosPorRol ret =
			impl.obtenerAccesosPorRol(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoSalidaObtenerConveniosEntidad obtenerConveniosEntidad(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoEntradaObtenerConveniosEntidad entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoSalidaObtenerConveniosEntidad ret =
			impl.obtenerConveniosEntidad(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.TipoSalidaObtenerEntidadesConvenio obtenerEntidadesConvenio(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.EntradaObtenerEntidadesConvenio entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.TipoSalidaObtenerEntidadesConvenio ret =
			impl.obtenerEntidadesConvenio(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoSalidaObtenerModulosPorRol obtenerModulosPorRol(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoEntradaObtenerModulosPorRol entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoSalidaObtenerModulosPorRol ret =
			impl.obtenerModulosPorRol(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.TipoSalidaObtenerORIPs obtenerORIPs(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.EntradaObtenerORIPs entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.TipoSalidaObtenerORIPs ret = impl
				.obtenerORIPs(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.TipoSalidaObtenerRoles obtenerRoles(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.EntradaObtenerRoles entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.TipoSalidaObtenerRoles ret = impl
				.obtenerRoles(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoSalidaObtenerRolesComponente obtenerRolesComponente(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoEntradaObtenerRolesComponente entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoSalidaObtenerRolesComponente ret =
			impl.obtenerRolesComponente(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoSalidaObtenerRolesUsuario obtenerRolesUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoEntradaObtenerRolesUsuario entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoSalidaObtenerRolesUsuario ret =
			impl.obtenerRolesUsuario(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoSalidaObtenerUsuariosRolOrip obtenerUsuariosRolOrip(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoEntradaObtenerUsuariosRolOrip entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoSalidaObtenerUsuariosRolOrip ret =
			impl.obtenerUsuariosRolOrip(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoSalidaRegistrarUsuario registrarUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuario entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoSalidaRegistrarUsuario ret = impl
				.registrarUsuario(entrada);

		return ret;
	}
}
