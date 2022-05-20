/**
 * SBB_CB_EntregaProductoSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1;

public class SBB_CB_EntregaProductoSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1.SBB_CB_EntregaProducto_PortType,
	org.apache.axis.wsdl.Skeleton
{
	private static final long           serialVersionUID  = 777877159562993377L;
	private static java.util.Map        _myOperations     = new java.util.Hashtable();
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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
				        "entradaVerificarProducto"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
				        "tipoEntradaVerificarProducto"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "verificarProducto", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
			        "salidaVerificarProducto"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
		        "tipoSalidaVerificarProducto"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "VerificarProducto"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/VerificarProducto"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("verificarProducto") == null)
			_myOperations.put("verificarProducto", new java.util.ArrayList());

		((java.util.List)_myOperations.get("verificarProducto")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
				        "entradaObtenerProducto"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
				        "tipoEntradaObtenerProducto"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerProducto", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
			        "salidaObtenerProducto"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
		        "tipoSalidaObtenerProducto"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerProducto"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/ObtenerProducto");
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerProducto") == null)
			_myOperations.put("obtenerProducto", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerProducto")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
				        "entradaObtenerTurnosRefPago"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
				        "tipoEntradaObtenerTurnosRefPago"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerTurnosRefPago", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
			        "salidaObtenerTurnosRefPago"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "tipoSalidaObtenerTurnosRefPago"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerTurnosRefPago"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/ObtenerTurnosRefPago"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerTurnosRefPago") == null)
			_myOperations.put("obtenerTurnosRefPago", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerTurnosRefPago")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
				        "entradaRegistrarEntregaProducto"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
				        "tipoEntradaRegistrarEntregaProducto"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "registrarEntregaProducto", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
			        "salidaRegistrarEntregaProducto"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
		        "tipoSalidaRegistrarEntregaProducto"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "RegistrarEntregaProducto"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/RegistrarEntregaProducto"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("registrarEntregaProducto") == null)
			_myOperations.put("registrarEntregaProducto", new java.util.ArrayList());

		((java.util.List)_myOperations.get("registrarEntregaProducto")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
				        "entradaObtenerReciboCaja"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
				        "tipoEntradaObtenerReciboCaja"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerReciboCaja", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
			        "salidaObtenerReciboCaja"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
		        "tipoSalidaObtenerReciboCaja"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ObtenerReciboCaja"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/ObtenerReciboCaja"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerReciboCaja") == null)
			_myOperations.put("obtenerReciboCaja", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerReciboCaja")).add(_oper);
	}

	private co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1.SBB_CB_EntregaProducto_PortType impl;

	public SBB_CB_EntregaProductoSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1.SBB_CB_EntregaProductoSOAP12BindingImpl();
	}

	public SBB_CB_EntregaProductoSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1.SBB_CB_EntregaProducto_PortType impl
	)
	{
		this.impl = impl;
	}

	/**
	* Returns List of OperationDesc objects with this name
	*/
	public static java.util.List getOperationDescByName(java.lang.String methodName)
	{
		return (java.util.List)_myOperations.get(methodName);
	}

	/**
	* Returns Collection of OperationDescs
	*/
	public static java.util.Collection getOperationDescs()
	{
		return _myOperationsList;
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto obtenerProducto(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto ret = impl
				.obtenerProducto(entrada);

		return ret;
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja obtenerReciboCaja(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja ret =
			impl.obtenerReciboCaja(entrada);

		return ret;
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago obtenerTurnosRefPago(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago ret =
			impl.obtenerTurnosRefPago(entrada);

		return ret;
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto registrarEntregaProducto(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto ret =
			impl.registrarEntregaProducto(entrada);

		return ret;
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto verificarProducto(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto ret =
			impl.verificarProducto(entrada);

		return ret;
	}
}
