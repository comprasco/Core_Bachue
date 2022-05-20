package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de SDB_BNG_DIRECCION_PREDIO de la base de datos.
 *
 * @author jpatino
 */
public class DireccionPredioDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT BDP.ID_CIRCULO, BDP.ID_MATRICULA, BDP.ID_DIRECCION, BDP.DIRECCION_COMPLETA, BDP.ID_TIPO_EJE_PRINCIPAL, BDP.DATO_EJE_PRINCIPAL, BDP.ID_LETRA_EJE_PRINCIPAL, BDP.ID_COMPLEMENTO_EJE_PRINCIPAL, BDP.ID_COORDENADA_EJE_PRINCIPAL, BDP.ID_TIPO_EJE_SECUNDARIO, BDP.DATO_EJE_SECUNDARIO, BDP.ID_LETRA1_EJE_SECUNDARIO, BDP.ID_COMPLEMENTO1_EJE_SECUNDARIO, BDP.ID_COORDENADA1_EJE_SECUNDARIO, BDP.DATO2_EJE_SECUNDARIO, BDP.ID_LETRA2_EJE_SECUNDARIO, BDP.ID_COMPLEMENTO2_EJE_SECUNDARIO, BDP.ID_COORDENADA2_EJE_SECUNDARIO, BDP.ID_COMPLEMENTO_DIRECCION, BDP.COMPLEMENTO_DIRECCION, BDP.ID_USUARIO_CREACION, BDP.FECHA_CREACION, BDP.IP_CREACION, BDP.ID_USUARIO_MODIFICACION, BDP.FECHA_MODIFICACION, BDP.IP_MODIFICACION, PTE.NOMBRE EJE_PRINCIPAL, PTE1.NOMBRE EJE_SECUNDARIO, PTE2.NOMBRE COMPLEMENTO, PTE3.NOMBRE COMPLEMENTO1, PTE4.NOMBRE COMPLEMENTO2, PTE5.NOMBRE COMPLEMENTO_DIRECCION, PC.NOMBRE COORDENADA, PC1.NOMBRE COORDENADA1, PC2.NOMBRE COORDENADA2 FROM SDB_BNG_DIRECCION_PREDIO BDP LEFT JOIN SDB_PNG_TIPO_EJE PTE ON BDP.ID_TIPO_EJE_PRINCIPAL = PTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE1 ON BDP.ID_TIPO_EJE_SECUNDARIO = PTE1.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE2 ON BDP.ID_COMPLEMENTO_EJE_PRINCIPAL = PTE2.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE3 ON BDP.ID_COMPLEMENTO1_EJE_SECUNDARIO = PTE3.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE4 ON BDP.ID_COMPLEMENTO2_EJE_SECUNDARIO = PTE4.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE5 ON BDP.ID_COMPLEMENTO_DIRECCION = PTE5.ID_TIPO_EJE LEFT JOIN SDB_PNG_COORDENADA PC ON BDP.ID_COORDENADA_EJE_PRINCIPAL = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC1 ON BDP.ID_COORDENADA1_EJE_SECUNDARIO = PC1.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC2 ON BDP.ID_COORDENADA2_EJE_SECUNDARIO = PC2.ID_COORDENADA WHERE BDP.ID_CIRCULO = ? AND BDP.ID_MATRICULA = ? ";

	/** Constante cs_FIND_MAX_ID_DIRECCION. */
	private static final String cs_FIND_MAX_ID_DIRECCION = "SELECT MAX(ID_DIRECCION) ID_DIRECCION FROM SDB_BNG_DIRECCION_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/**
	 * Obtiene todas las direcciones relacionadas a un círculo y matrícula.
	 *
	 * @param adp_param Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param ab_maxIdOnly true para utilizar el id dirección en el filtro de la consulta, false para buscar todos los registros
	 * diferentes a este id
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<DireccionPredio> findByCirculoMatriculaIdDireccion(
	    DireccionPredio adp_param, boolean ab_maxIdOnly
	)
	    throws B2BException
	{
		Collection<DireccionPredio> lcdp_direccionPredio;

		lcdp_direccionPredio = new ArrayList<DireccionPredio>();

		if(adp_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;

				lsb_query     = new StringBuilder();
				li_cont       = 1;

				lsb_query.append(cs_FIND_BY_ID);

				if(ab_maxIdOnly)
					lsb_query.append(" AND BDP.ID_DIRECCION = ? ");
				else
					lsb_query.append(" AND BDP.ID_DIRECCION <> ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, adp_param.getIdCirculo());
				setLong(lps_ps, adp_param.getIdMatricula(), li_cont++);
				lps_ps.setString(li_cont++, adp_param.getIdDireccion());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdp_direccionPredio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaIdDireccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcdp_direccionPredio))
			lcdp_direccionPredio = null;

		return lcdp_direccionPredio;
	}

	/**
	 * Retorna el valor del objeto de tipo DireccionPredio.
	 *
	 * @param adp_param correspondiente al valor del tipo de objeto DireccionPredio
	 * @return devuelve el valor del objeto direccion predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredio
	 */
	public DireccionPredio findById(DireccionPredio adp_param)
	    throws B2BException
	{
		DireccionPredio ldp_direccionPredio;

		ldp_direccionPredio = null;

		if(adp_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_i;
				boolean       lb_consultapredio;
				StringBuilder lsb_consulta;

				li_i                  = 1;
				lb_consultapredio     = adp_param.isConsultaPredio();
				lsb_consulta          = new StringBuilder(cs_FIND_BY_ID);
				lsb_consulta.append(
				    lb_consultapredio ? "ORDER BY BDP.ID_DIRECCION DESC" : "ORDER BY BDP.FECHA_CREACION DESC"
				);

				lps_ps = getConnection().prepareStatement(lsb_consulta.toString());

				lps_ps.setString(li_i++, adp_param.getIdCirculo());
				setLong(lps_ps, adp_param.getIdMatricula(), li_i++);

				lrs_rs = lps_ps.executeQuery();

				if(lb_consultapredio)
				{
					Collection<DireccionPredio> lcdp_cdp;

					ldp_direccionPredio     = new DireccionPredio();
					lcdp_cdp                = new ArrayList<DireccionPredio>();

					while(lrs_rs.next())
						lcdp_cdp.add(getObjetFromResultSet(lrs_rs));

					if(CollectionUtils.isValidCollection(lcdp_cdp))
						ldp_direccionPredio.setDireccionesPredio(lcdp_cdp);
				}
				else
				{
					if(lrs_rs.next())
						ldp_direccionPredio = getObjetFromResultSet(lrs_rs);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ldp_direccionPredio;
	}

	/**
	 * Find by id circulo matricula.
	 *
	 * @param adp_param de adp param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DireccionPredio> findByIdCirculoMatricula(DireccionPredio adp_param)
	    throws B2BException
	{
		return (adp_param != null)
		? findByIdCirculoMatricula(adp_param.getIdCirculo(), NumericUtils.getLong(adp_param.getIdMatricula())) : null;
	}

	/**
	 * Find by id circulo matricula.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DireccionPredio> findByIdCirculoMatricula(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		Collection<DireccionPredio> lcdp_direccionPredio;

		lcdp_direccionPredio = new ArrayList<DireccionPredio>();

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;

				lsb_query     = new StringBuilder();
				li_cont       = 1;

				lsb_query.append(cs_FIND_BY_ID);
				lsb_query.append(" ORDER BY BDP.ID_DIRECCION ASC ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, as_idCirculo);
				lps_ps.setLong(li_cont++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdp_direccionPredio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcdp_direccionPredio))
			lcdp_direccionPredio = null;

		return lcdp_direccionPredio;
	}

	/**
	 * Obtiene todas las direcciones relacionadas a un círculo matrícula e id_matrícula.
	 *
	 * @param adp_param Objeto contenedor de los filtros a utilizar en la consulta
	 * @return DireccionPredio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public DireccionPredio findByIdCirculoMatriculaIdDireccion(DireccionPredio adp_param)
	    throws B2BException
	{
		return (adp_param != null)
		? findByIdCirculoMatriculaIdDireccion(
		    adp_param.getIdCirculo(), NumericUtils.getLong(adp_param.getIdMatricula()), adp_param.getIdDireccion()
		) : null;
	}

	/**
	 * Obtiene todas las direcciones relacionadas a un círculo matrícula e id_matrícula.
	 *
	 * @param as_idCirculo Objeto contenedor de los filtros a utilizar en la consulta
	 * @param al_idMatricula Objeto contenedor de los filtros a utilizar en la consulta
	 * @param as_idDireccion Objeto contenedor de los filtros a utilizar en la consulta
	 * @return DireccionPredio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public DireccionPredio findByIdCirculoMatriculaIdDireccion(
	    String as_idCirculo, long al_idMatricula, String as_idDireccion
	)
	    throws B2BException
	{
		DireccionPredio ldp_direccionPredio;

		ldp_direccionPredio = null;

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0) && StringUtils.isValidString(as_idDireccion))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;

				lsb_query     = new StringBuilder();
				li_cont       = 1;

				lsb_query.append(cs_FIND_BY_ID + " AND BDP.ID_DIRECCION = ?");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, as_idCirculo);
				lps_ps.setLong(li_cont++, al_idMatricula);
				lps_ps.setString(li_cont++, as_idDireccion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldp_direccionPredio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCirculoMatriculaIdDireccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ldp_direccionPredio;
	}

	/**
	 * Obtiene el maximo id dirección para un círculo y matrícula específicos.
	 *
	 * @param adp_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findMaxIdDireccion(DireccionPredio adp_param)
	    throws B2BException
	{
		return (adp_param != null)
		? findMaxIdDireccion(adp_param.getIdCirculo(), NumericUtils.getLong(adp_param.getIdMatricula())) : null;
	}

	/**
	 * Obtiene el maximo id dirección para un círculo y matrícula específicos.
	 *
	 * @param as_idCirculo Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param al_idMatricula Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findMaxIdDireccion(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		String ls_maxIdDireccion;

		ls_maxIdDireccion = null;

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_ID_DIRECCION);

				lps_ps.setString(li_cont++, as_idCirculo);
				lps_ps.setLong(li_cont++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_maxIdDireccion = getMaxIdDireccionFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxIdDireccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_maxIdDireccion;
	}

	/**
	 * Extrae la información del máximo id dirección encontrado en la consulta.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return Cadena de caracteres con el máximo id dirección
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private String getMaxIdDireccionFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		return ars_rs.getString("ID_DIRECCION");
	}

	/**
	 * Retorna el valor de DireccionPredio.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de DireccionPredio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see DireccionPredio
	 */
	private DireccionPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DireccionPredio ldp_direccionPredio;

		ldp_direccionPredio = new DireccionPredio();

		ldp_direccionPredio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ldp_direccionPredio.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		ldp_direccionPredio.setIdDireccion(ars_rs.getString("ID_DIRECCION"));
		ldp_direccionPredio.setIdTipoEjePrincipal(ars_rs.getString("ID_TIPO_EJE_PRINCIPAL"));
		ldp_direccionPredio.setDatoEjePrincipal(ars_rs.getString("DATO_EJE_PRINCIPAL"));
		ldp_direccionPredio.setIdLetraEjePrincipal(ars_rs.getString("ID_LETRA_EJE_PRINCIPAL"));
		ldp_direccionPredio.setIdComplementoEjePrincipal(ars_rs.getString("ID_COMPLEMENTO_EJE_PRINCIPAL"));
		ldp_direccionPredio.setIdCoordenadaEjePrincipal(ars_rs.getString("ID_COORDENADA_EJE_PRINCIPAL"));
		ldp_direccionPredio.setIdTipoEjeSecundario(ars_rs.getString("ID_TIPO_EJE_SECUNDARIO"));
		ldp_direccionPredio.setDatoEjeSecundario(ars_rs.getString("DATO_EJE_SECUNDARIO"));
		ldp_direccionPredio.setIdLetra1EjeSecundario(ars_rs.getString("ID_LETRA1_EJE_SECUNDARIO"));
		ldp_direccionPredio.setIdComplemento1EjeSecundario(ars_rs.getString("ID_COMPLEMENTO1_EJE_SECUNDARIO"));
		ldp_direccionPredio.setIdCoordenada1EjeSecundario(ars_rs.getString("ID_COORDENADA1_EJE_SECUNDARIO"));
		ldp_direccionPredio.setDato2EjeSecundario(ars_rs.getString("DATO2_EJE_SECUNDARIO"));
		ldp_direccionPredio.setIdLetra2EjeSecundario(ars_rs.getString("ID_LETRA2_EJE_SECUNDARIO"));
		ldp_direccionPredio.setIdComplemento2EjeSecundario(ars_rs.getString("ID_COMPLEMENTO2_EJE_SECUNDARIO"));
		ldp_direccionPredio.setIdCoordenada2EjeSecundario(ars_rs.getString("ID_COORDENADA2_EJE_SECUNDARIO"));
		ldp_direccionPredio.setIdComplementoDireccion(ars_rs.getString("ID_COMPLEMENTO_DIRECCION"));
		ldp_direccionPredio.setComplementoDireccion(ars_rs.getString("COMPLEMENTO_DIRECCION"));

		{
			String ls_direccionCompleta;

			ls_direccionCompleta = ars_rs.getString("DIRECCION_COMPLETA");

			if(!StringUtils.isValidString(ls_direccionCompleta))
				ls_direccionCompleta = generarDireccionCompleta(ars_rs, ldp_direccionPredio);

			ldp_direccionPredio.setDireccion(ls_direccionCompleta);
		}

		fillAuditoria(ars_rs, ldp_direccionPredio);

		return ldp_direccionPredio;
	}
}
