/**
 * SBB_CB_CertificadoTradicionSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1;


/**
 * Clase que contiene todos las propiedades SBB_CB_CertificadoTradicionSOAP12BindingSkeleton.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_CertificadoTradicionSOAP12BindingSkeleton implements co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1.SBB_CB_CertificadoTradicion_PortType,
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
				        "https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1",
				        "entradaCertificadoTradicionPDF"
				    ), org.apache.axis.description.ParameterDesc.IN,
				    new javax.xml.namespace.QName(
				        "https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1",
				        "tipoEntradaCertificadoTradicionPDF"
				    ),
				    co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDF.class,
				    false, false
				),
			};
		_oper       = new org.apache.axis.description.OperationDesc(
			    "obtenerPDF", _params,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1",
			        "salidaCertificadoTradicionPDF"
			    )
			);
		_oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1",
		        "tipoSalidaCertificadoTradicionPDF"
		    )
		);
		_oper.setElementQName(new javax.xml.namespace.QName("", "obtenerPDF"));
		_oper.setSoapAction("https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicion/v1/obtenerPDF");
		_myOperationsList.add(_oper);

		if(_myOperations.get("obtenerPDF") == null)
			_myOperations.put("obtenerPDF", new java.util.ArrayList());

		((java.util.List)_myOperations.get("obtenerPDF")).add(_oper);
	}

	/** Propiedad impl. */
	private co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1.SBB_CB_CertificadoTradicion_PortType impl;

	/**
	 * Instancia un nuevo objeto SB B C B certificado tradicion SOAP 12 binding skeleton.
	 */
	public SBB_CB_CertificadoTradicionSOAP12BindingSkeleton()
	{
		this.impl = new co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1.SBB_CB_CertificadoTradicionSOAP12BindingImpl();
	}

	/**
	 * Instancia un nuevo objeto SB B C B certificado tradicion SOAP 12 binding skeleton.
	 *
	 * @param impl de impl
	 */
	public SBB_CB_CertificadoTradicionSOAP12BindingSkeleton(
	    co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1.SBB_CB_CertificadoTradicion_PortType impl
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
	public co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoSalidaCertificadoTradicionPDF obtenerPDF(
	    co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDF entrada
	)
	    throws java.rmi.RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoSalidaCertificadoTradicionPDF ret =
			impl.obtenerPDF(entrada);

		return ret;
	}
}
