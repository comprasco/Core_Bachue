/**
 * SDI_CB_GestionAlertasTitularesSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1;


/**
 * Clase que contiene todos las propiedades SDI_CB_GestionAlertasTitularesSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SDI_CB_GestionAlertasTitularesSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitulares_PortType,
	org.apache.axis.wsdl.Skeleton
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6559448808936028507L;

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
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
				        "entradaConsultarAlerta"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
				        "tipoEntradaConsultarAlerta"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlerta.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarAlerta", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
			        "salidaConsultarAlerta"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "tipoSalidaConsultarAlerta"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarAlerta"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionalertastitulares/v1/ConsultarAlerta"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarAlerta") == null)
			_myOperations.put("consultarAlerta", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarAlerta")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultartarifaalertastitulares/v1",
				        "entradaConsultarTarifaAlertasTitulares"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultartarifaalertastitulares/v1",
				        "tipoEntradaConsultarTarifaAlertasTitulares"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoEntradaConsultarTarifaAlertasTitulares.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "consultarTarifaAlertasTitulares", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultartarifaalertastitulares/v1",
			        "salidaConsultarTarifaAlertasTitulares"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultartarifaalertastitulares/v1",
		        "tipoSalidaConsultarTarifaAlertasTitulares"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ConsultarTarifaAlertasTitulares"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionalertastitulares/v1/ConsultarTarifaAlertasTitulares"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarTarifaAlertasTitulares") == null)
			_myOperations.put("consultarTarifaAlertasTitulares", new java.util.ArrayList());

		((java.util.List)_myOperations.get("consultarTarifaAlertasTitulares")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
				        "entradaInactivarAlerta"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
				        "tipoEntradaInactivarAlerta"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlerta.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "inactivarAlerta", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
			        "salidaInactivarAlerta"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
		        "tipoSalidaInactivarAlerta"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "InactivarAlerta"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionalertastitulares/v1/InactivarAlerta"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("inactivarAlerta") == null)
			_myOperations.put("inactivarAlerta", new java.util.ArrayList());

		((java.util.List)_myOperations.get("inactivarAlerta")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
				        "entradaValidarSolicitudAlertaIndividual"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
				        "tipoEntradaValidarSolicitudAlertaIndividual"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoEntradaValidarSolicitudAlertaIndividual.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "validarSolicitudAlertaIndividual", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
			        "salidaValidarSolicitudAlertaIndividual"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
		        "tipoSalidaValidarSolicitudAlertaIndividual"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ValidarSolicitudAlertaIndividual"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionalertastitulares/v1/ValidarSolicitudAlertaIndividual"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("validarSolicitudAlertaIndividual") == null)
			_myOperations.put("validarSolicitudAlertaIndividual", new java.util.ArrayList());

		((java.util.List)_myOperations.get("validarSolicitudAlertaIndividual")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
				        "entradaValidarSolicitudAlertaMasiva"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
				        "tipoEntradaValidarSolicitudAlertaMasiva"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasiva.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "validarSolicitudAlertaMasiva", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
			        "salidaValidarSolicitudAlertaMasiva"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "tipoSalidaValidarSolicitudAlertaMasiva"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ValidarSolicitudAlertaMasiva"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionalertastitulares/v1/ValidarSolicitudAlertaMasiva"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("validarSolicitudAlertaMasiva") == null)
			_myOperations.put("validarSolicitudAlertaMasiva", new java.util.ArrayList());

		((java.util.List)_myOperations.get("validarSolicitudAlertaMasiva")).add(_oper);
		_params     = new org.apache.axis.description.ParameterDesc[]
			{
				new org.apache.axis.description.ParameterDesc(
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/actualizarcontactoalerta/v1",
				        "entradaActualizarContactoAlerta"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/actualizarcontactoalerta/v1",
				        "tipoEntradaActualizarContactoAlerta"
				    ),
				    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoEntradaActualizarContactoAlerta.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "actualizarContactoAlerta", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/actualizarcontactoalerta/v1",
			        "salidaActualizarContactoAlerta"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/actualizarcontactoalerta/v1",
		        "tipoSalidaActualizarContactoAlerta"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "ActualizarContactoAlerta"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionalertastitulares/v1/ActualizarContactoAlerta"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("actualizarContactoAlerta") == null)
			_myOperations.put("actualizarContactoAlerta", new java.util.ArrayList());

		((java.util.List)_myOperations.get("actualizarContactoAlerta")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitulares_PortType impl;

	/**
	 * Instancia un nuevo objeto SD I C B gestion alertas titulares SOAP 12 binding skeleton.
	 */
	public SDI_CB_GestionAlertasTitularesSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitularesSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SD I C B gestion alertas titulares SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SDI_CB_GestionAlertasTitularesSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitulares_PortType impl
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
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoSalidaActualizarContactoAlerta actualizarContactoAlerta(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoEntradaActualizarContactoAlerta entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoSalidaActualizarContactoAlerta ret =
			impl.actualizarContactoAlerta(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoSalidaConsultarAlerta consultarAlerta(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlerta entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoSalidaConsultarAlerta ret =
			impl.consultarAlerta(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoSalidaConsultarTarifaAlertasTitulares consultarTarifaAlertasTitulares(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoEntradaConsultarTarifaAlertasTitulares entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoSalidaConsultarTarifaAlertasTitulares ret =
			impl.consultarTarifaAlertasTitulares(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoSalidaInactivarAlerta inactivarAlerta(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlerta entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoSalidaInactivarAlerta ret =
			impl.inactivarAlerta(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividual validarSolicitudAlertaIndividual(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoEntradaValidarSolicitudAlertaIndividual entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividual ret =
			impl.validarSolicitudAlertaIndividual(entrada);

		return ret;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoSalidaValidarSolicitudAlertaMasiva validarSolicitudAlertaMasiva(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasiva entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoSalidaValidarSolicitudAlertaMasiva ret =
			impl.validarSolicitudAlertaMasiva(entrada);

		return ret;
	}
}
