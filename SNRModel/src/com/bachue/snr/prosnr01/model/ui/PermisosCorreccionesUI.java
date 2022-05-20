package com.bachue.snr.prosnr01.model.ui;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAntSistemaSolicitud;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelApertura;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAreaPredio;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelCatastral;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelComplementacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleAntSistemaSolicitud;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDireccionPredio;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelLinderos;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasAbiertas;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasSegregacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelUbicacion;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.io.Serializable;

import java.util.Collection;



/**
 *
 * Clase modelo que contiene todos los atributos de PermisosCorreccionesUI.java
 * @author garias
 *
 */
public class PermisosCorreccionesUI extends TurnoHistoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long               serialVersionUID           = -8213641461816858281L;
	
	/** Propiedad ica anotaciones agregadas. */
	private Collection<Anotacion>           ica_anotacionesAgregadas;
	
	/** Propiedad il id matricula. */
	private Long                            il_idMatricula;
	
	/** Propiedad ipass ant sistema. */
	private PanelAntSistemaSolicitud        ipass_antSistema;
	
	/** Propiedad ipa pertura. */
	private PanelApertura                   ipa_pertura;
	
	/** Propiedad ipap area predio. */
	private PanelAreaPredio                 ipap_areaPredio;
	
	/** Propiedad ipc catastral. */
	private PanelCatastral                  ipc_catastral;
	
	/** Propiedad ipc complementacion. */
	private PanelComplementacion            ipc_complementacion;
	
	/** Propiedad ipdass detalle ant sistema. */
	private PanelDetalleAntSistemaSolicitud ipdass_detalleAntSistema;
	
	/** Propiedad ipdp direccion predio. */
	private PanelDireccionPredio            ipdp_direccionPredio;
	
	/** Propiedad ipl linderos. */
	private PanelLinderos                   ipl_linderos;
	
	/** Propiedad ipma matriculas abiertas. */
	private PanelMatriculasAbiertas         ipma_matriculasAbiertas;
	
	/** Propiedad ipms matriculas segregacion. */
	private PanelMatriculasSegregacion      ipms_matriculasSegregacion;
	
	/** Propiedad ipu ubicacion. */
	private PanelUbicacion                  ipu_ubicacion;
	
	/** Propiedad is complemento salvedad. */
	private String                          is_complementoSalvedad;
	
	/** Propiedad is observaciones. */
	private String                          is_observaciones;
	
	/** Propiedad ib agregar anotacion. */
	private boolean                         ib_agregarAnotacion;

	/**
	 * Modifica el valor de AgregarAnotacion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAgregarAnotacion(boolean ab_b)
	{
		ib_agregarAnotacion                                            = ab_b;
	}

	/**
	 * Valida la propiedad agregar anotacion.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en agregar anotacion
	 */
	public boolean isAgregarAnotacion()
	{
		return ib_agregarAnotacion;
	}

	/**
	 * Modifica el valor de AnotacionesAgregadas.
	 *
	 * @param aca_ca asigna el valor a la propiedad
	 */
	public void setAnotacionesAgregadas(Collection<Anotacion> aca_ca)
	{
		ica_anotacionesAgregadas = aca_ca;
	}

	/**
	 * Devuelve una Collection<Anotacion>.
	 *
	 * @return devuelve el valor de la propiedad
	 * @see com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion
	 */
	public Collection<Anotacion> getAnotacionesAgregadas()
	{
		return ica_anotacionesAgregadas;
	}

	/**
	 * Modifica el valor de AntSistema.
	 *
	 * @param apants_pants asigna el valor a la propiedad
	 */
	public void setAntSistema(PanelAntSistemaSolicitud apants_pants)
	{
		ipass_antSistema = apants_pants;
	}

	/**
	 * Devuelve una PanelAntSistemaSolicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAntSistemaSolicitud
	 */
	public PanelAntSistemaSolicitud getAntSistema()
	{
		if(ipass_antSistema == null)
			ipass_antSistema = new PanelAntSistemaSolicitud();

		return ipass_antSistema;
	}

	/**
	 * Modifica el valor de Apertura.
	 *
	 * @param apa_pa asigna el valor a la propiedad
	 */
	public void setApertura(PanelApertura apa_pa)
	{
		ipa_pertura = apa_pa;
	}

	/**
	 * Devuelve una PanelApertura.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelApertura
	 */
	public PanelApertura getApertura()
	{
		if(ipa_pertura == null)
			ipa_pertura = new PanelApertura();

		return ipa_pertura;
	}

	/**
	 * Modifica el valor de AreaPredio.
	 *
	 * @param apap_pap asigna el valor a la propiedad
	 */
	public void setAreaPredio(PanelAreaPredio apap_pap)
	{
		ipap_areaPredio = apap_pap;
	}

	/**
	 * Devuelve una PanelAreaPredio.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAreaPredio
	 */
	public PanelAreaPredio getAreaPredio()
	{
		if(ipap_areaPredio == null)
			ipap_areaPredio = new PanelAreaPredio();

		return ipap_areaPredio;
	}

	/**
	 * Modifica el valor de Catastral.
	 *
	 * @param apc_pc asigna el valor a la propiedad
	 */
	public void setCatastral(PanelCatastral apc_pc)
	{
		ipc_catastral = apc_pc;
	}

	/**
	 * Devuelve una PanelCatastral.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelCatastral
	 */
	public PanelCatastral getCatastral()
	{
		if(ipc_catastral == null)
			ipc_catastral = new PanelCatastral();

		return ipc_catastral;
	}

	/**
	 * Modifica el valor de Complementacion.
	 *
	 * @param apc_pc asigna el valor a la propiedad
	 */
	public void setComplementacion(PanelComplementacion apc_pc)
	{
		ipc_complementacion = apc_pc;
	}

	/**
	 * Devuelve una PanelComplementacion.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelComplementacion
	 */
	public PanelComplementacion getComplementacion()
	{
		if(ipc_complementacion == null)
			ipc_complementacion = new PanelComplementacion();

		return ipc_complementacion;
	}

	/**
	 * Modifica el valor de ComplementoSalvedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComplementoSalvedad(String as_s)
	{
		is_complementoSalvedad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor complemento salvedad.
	 *
	 * @return el valor de complemento salvedad
	 */
	public String getComplementoSalvedad()
	{
		return is_complementoSalvedad;
	}

	/**
	 * Modifica el valor de DetalleAntSistema.
	 *
	 * @param apdass_pdass asigna el valor a la propiedad
	 */
	public void setDetalleAntSistema(PanelDetalleAntSistemaSolicitud apdass_pdass)
	{
		ipdass_detalleAntSistema = apdass_pdass;
	}

	/**
	 * Devuelve una PanelDetalleAntSistemaSolicitud.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleAntSistemaSolicitud
	 */
	public PanelDetalleAntSistemaSolicitud getDetalleAntSistema()
	{
		if(ipdass_detalleAntSistema == null)
			ipdass_detalleAntSistema = new PanelDetalleAntSistemaSolicitud();

		return ipdass_detalleAntSistema;
	}

	/**
	 * Modifica el valor de DireccionPredio.
	 *
	 * @param apdp_pdp asigna el valor a la propiedad
	 */
	public void setDireccionPredio(PanelDireccionPredio apdp_pdp)
	{
		ipdp_direccionPredio = apdp_pdp;
	}

	/**
	 * Devuelve una PanelDireccionPredio.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDireccionPredio
	 */
	public PanelDireccionPredio getDireccionPredio()
	{
		if(ipdp_direccionPredio == null)
			ipdp_direccionPredio = new PanelDireccionPredio();

		return ipdp_direccionPredio;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de Linderos.
	 *
	 * @param apl_pl asigna el valor a la propiedad
	 */
	public void setLinderos(PanelLinderos apl_pl)
	{
		ipl_linderos = apl_pl;
	}

	/**
	 * Devuelve una PanelLinderos.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelLinderos
	 */
	public PanelLinderos getLinderos()
	{
		if(ipl_linderos == null)
			ipl_linderos = new PanelLinderos();

		return ipl_linderos;
	}

	/**
	 * Modifica el valor de MatriculasAbiertas.
	 *
	 * @param apma_pma asigna el valor a la propiedad
	 */
	public void setMatriculasAbiertas(PanelMatriculasAbiertas apma_pma)
	{
		ipma_matriculasAbiertas = apma_pma;
	}

	/**
	 * Devuelve una PanelMatriculasAbiertas.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasAbiertas
	 */
	public PanelMatriculasAbiertas getMatriculasAbiertas()
	{
		if(ipma_matriculasAbiertas == null)
			ipma_matriculasAbiertas = new PanelMatriculasAbiertas();

		return ipma_matriculasAbiertas;
	}

	/**
	 * Modifica el valor de MatriculasSegregacion.
	 *
	 * @param apms_pms asigna el valor a la propiedad
	 */
	public void setMatriculasSegregacion(PanelMatriculasSegregacion apms_pms)
	{
		ipms_matriculasSegregacion = apms_pms;
	}

	/**
	 * Devuelve una PanelMatriculasSegregacion.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasSegregacion
	 */
	public PanelMatriculasSegregacion getMatriculasSegregacion()
	{
		if(ipms_matriculasSegregacion == null)
			ipms_matriculasSegregacion = new PanelMatriculasSegregacion();

		return ipms_matriculasSegregacion;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/** {@inheritdoc} */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de Ubicacion.
	 *
	 * @param apu_pu asigna el valor a la propiedad
	 */
	public void setUbicacion(PanelUbicacion apu_pu)
	{
		ipu_ubicacion = apu_pu;
	}

	/**
	 * Devuelve una PanelUbicacion.
	 *
	 * @return devuelve el valor de la propiedad, si ipa_pertura es null crea una nueva instancia del objeto
	 * de lo contario devuelve la instancia ya creada
	 * @see com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelUbicacion
	 */
	public PanelUbicacion getUbicacion()
	{
		if(ipu_ubicacion == null)
			ipu_ubicacion = new PanelUbicacion();

		return ipu_ubicacion;
	}
}
