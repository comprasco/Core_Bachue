package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.ConvenioCirculoRegistral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ConvenioCirculoRegistralDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 29/05/2020
 */
public class ConvenioCirculoRegistralDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID_ENTIDAD_EXTERNA_NUMERO_CONVENIO. */
	private static final String cs_FIND_BY_ID_ENTIDAD_EXTERNA_NUMERO_CONVENIO = "SELECT * FROM SDB_ACC_CONVENIO_CIRCULO_REGISTRAL WHERE ID_ENTIDAD_EXTERNA = ? AND NUMERO_CONVENIO = ?";

	/**
	 * Find all by id entidad externa numero convenio.
	 *
	 * @param as_idEntidadExterna de as id entidad externa
	 * @param as_numeroConvenio de as numero convenio
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConvenioCirculoRegistral> findAllByIdEntidadExternaNumeroConvenio(
	    String as_idEntidadExterna, String as_numeroConvenio
	)
	    throws B2BException
	{
		Collection<ConvenioCirculoRegistral> lcccr_return;

		lcccr_return = new ArrayList<ConvenioCirculoRegistral>();

		if(StringUtils.isValidString(as_idEntidadExterna) && StringUtils.isValidString(as_numeroConvenio))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ENTIDAD_EXTERNA_NUMERO_CONVENIO);

				lps_ps.setString(li_contador++, as_idEntidadExterna);
				lps_ps.setString(li_contador++, as_numeroConvenio);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcccr_return.add(getObjectFromResulSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdEntidadExternaNumeroConvenio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcccr_return.isEmpty())
			lcccr_return = null;

		return lcccr_return;
	}

	/**
	 * Retorna Objeto o variable de valor object from resul set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from resul set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ConvenioCirculoRegistral getObjectFromResulSet(ResultSet ars_rs)
	    throws SQLException
	{
		ConvenioCirculoRegistral lccr_datos;

		lccr_datos = new ConvenioCirculoRegistral();

		lccr_datos.setIdEntidadExterna(ars_rs.getString("ID_ENTIDAD_EXTERNA"));
		lccr_datos.setIdConvenioCirculoRegistral(ars_rs.getString("ID_CONVENIO_CIRCULO_REGISTRAL"));
		lccr_datos.setNumeroConvenio(ars_rs.getString("NUMERO_CONVENIO"));
		lccr_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lccr_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lccr_datos);

		return lccr_datos;
	}
}
