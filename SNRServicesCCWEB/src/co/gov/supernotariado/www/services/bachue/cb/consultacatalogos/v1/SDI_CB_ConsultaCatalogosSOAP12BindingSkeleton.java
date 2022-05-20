/**
 * SDI_CB_ConsultaCatalogosSOAP12BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultacatalogos.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos;

import org.apache.axis.description.FaultDesc;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;

import org.apache.axis.wsdl.Skeleton;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;


public class SDI_CB_ConsultaCatalogosSOAP12BindingSkeleton implements SDI_CB_ConsultaCatalogos, Skeleton
{
	private static final long serialVersionUID  = 2622264950262479101L;
	private static Map        _myOperations     = new Hashtable();
	private static Collection _myOperationsList = new ArrayList();

	static
	{
		OperationDesc   _oper;
		FaultDesc       _fault;
		ParameterDesc[] _params;
		_params     = new ParameterDesc[]
			{
				new ParameterDesc(
				    new QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
				        "entradaConsultarCatalogos"
				    ), ParameterDesc.IN,
				    new QName(
				        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
				        "tipoEntradaConsultarCatalogos"
				    ), TipoEntradaConsultarCatalogos.class, false, false
				),
			};
		_oper       = new OperationDesc(
			    "consultarCatalogos", _params,
			    new QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
			        "salidaConsultarCatalogos"
			    )
			);
		_oper.setReturnType(
		    new QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        "tipoSalidaConsultarCatalogos"
		    )
		);
		_oper.setElementQName(new QName("", "ConsultarCatalogos"));
		_oper.setSoapAction(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultacatalogos/v1/ConsultarCatalogos"
		);
		_myOperationsList.add(_oper);

		if(_myOperations.get("consultarCatalogos") == null)
			_myOperations.put("consultarCatalogos", new ArrayList());

		((List)_myOperations.get("consultarCatalogos")).add(_oper);
	}

	private SDI_CB_ConsultaCatalogos impl;

	public SDI_CB_ConsultaCatalogosSOAP12BindingSkeleton()
	{
		this.impl = new SDI_CB_ConsultaCatalogosSOAP12BindingImpl();
	}

	public SDI_CB_ConsultaCatalogosSOAP12BindingSkeleton(SDI_CB_ConsultaCatalogos acc_impl)
	{
		this.impl = acc_impl;
	}

	/**
	* Returns List of OperationDesc objects with this name
	*/
	public static List getOperationDescByName(String as_methodName)
	{
		return (List)_myOperations.get(as_methodName);
	}

	/**
	* Returns Collection of OperationDescs
	*/
	public static Collection getOperationDescs()
	{
		return _myOperationsList;
	}

	public TipoSalidaConsultarCatalogos consultarCatalogos(TipoEntradaConsultarCatalogos atecc_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarCatalogos ltscc_ret = impl.consultarCatalogos(atecc_entrada);

		return ltscc_ret;
	}
}
