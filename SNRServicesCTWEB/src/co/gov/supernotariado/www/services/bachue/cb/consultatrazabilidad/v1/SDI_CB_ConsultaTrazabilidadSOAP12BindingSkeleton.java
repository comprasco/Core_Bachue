/**
 * SDI_CB_ConsultaTrazabilidadSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1;


/**
 * Clase que contiene todos las propiedades SDI_CB_ConsultaTrazabilidadSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/09/2020
 */
public class SDI_CB_ConsultaTrazabilidadSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1.SDI_CB_ConsultaTrazabilidad_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7029356048405741543L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
				        "entradaConsultarTrazabilidad"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
				        "tipoEntradaConsultarTrazabilidad"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarTrazabilidad", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			        "salidaConsultarTrazabilidad"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoSalidaConsultarTrazabilidad"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarTrazabilidad"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultatrazabilidad/v1/ConsultarTrazabilidad"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarTrazabilidad") == null)
			_myOperations.put("consultarTrazabilidad", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarTrazabilidad")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
				        "entradaConsultaActosTurno"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
				        "tipoEntradaConsultaActosTurno"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultaActosTurno", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
			        "salidaConsultaActosTurno"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "tipoSalidaConsultaActosTurno"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultaActosTurno"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultatrazabilidad/v1/ConsultaActosTurno"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultaActosTurno") == null)
			_myOperations.put("consultaActosTurno", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultaActosTurno")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
				        "entradaConsultaDetalleCertificado"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
				        "tipoEntradaConsultaDetalleCertificado"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultaDetalleCertificado", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
			        "salidaConsultaDetalleCertificado"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
		        "tipoSalidaConsultaDetalleCertificado"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultaDetalleCertificado"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultatrazabilidad/v1/ConsultaDetalleCertificado"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultaDetalleCertificado") == null)
			_myOperations.put("consultaDetalleCertificado", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultaDetalleCertificado")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1.SDI_CB_ConsultaTrazabilidad_PortType impl;

	/**
	 * Instancia un nuevo objeto SD I C B consulta trazabilidad SOAP 12 binding skeleton.
	 */
	public SDI_CB_ConsultaTrazabilidadSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1.SDI_CB_ConsultaTrazabilidadSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SD I C B consulta trazabilidad SOAP 12 binding skeleton.
	 *
	 * @param impl correspondiente al valor de impl
	 */
	public SDI_CB_ConsultaTrazabilidadSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1.SDI_CB_ConsultaTrazabilidad_PortType impl
	)
	{
		this.impl = impl;
	}

	/**
	 * Returns List of OperationDesc objects with this name.
	 *
	 * @param methodName correspondiente al valor de method name
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
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno consultaActosTurno(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno ret =
			impl.consultaActosTurno(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado consultaDetalleCertificado(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado ret =
			impl.consultaDetalleCertificado(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad consultarTrazabilidad(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad ret =
			impl.consultarTrazabilidad(entrada);

		return ret;
	}
}
