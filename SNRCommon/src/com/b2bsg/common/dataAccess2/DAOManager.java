package com.b2bsg.common.dataAccess2;

import com.b2bsg.common.dataAccess2.source.ConnectionSource;

import com.b2bsg.common.exception.B2BException;

import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades de DAOManager para el manejo de acceso a datos
 *
 * @author Edgar Prieto
 */
public class DAOManager extends BaseDAO
{
	/** Propiedad im daos. */
	private Map im_daos;

	/** Propiedad is connection source class name. */
	private String is_connectionSourceClassName;

	/** Propiedad ib auto commit. */
	private boolean ib_autoCommit;

	/** Propiedad ib rollback only. */
	private boolean ib_rollbackOnly;

	/**
	 * Instancia un nuevo objeto DAO manager.
	 */
	public DAOManager()
	{
		this(null, false);
	}

	/**
	 * Instancia un nuevo objeto DAO manager.
	 *
	 * @param as_driverSourceClassName correspondiente al valor del tipo de objeto String
	 * @param ab_autoCommit correspondiente al valor del tipo de objeto boolean
	 */
	private DAOManager(String as_driverSourceClassName, boolean ab_autoCommit)
	{
		ib_autoCommit                    = ab_autoCommit;
		ib_rollbackOnly                  = false;
		im_daos                          = new HashMap();
		is_connectionSourceClassName     = as_driverSourceClassName;
	}

	/** {@inheritdoc} */
	public void setAutoCommit(boolean ab_b)
	{
		ib_autoCommit = ab_b;

		super.setAutoCommit(ib_autoCommit);
	}

	/** {@inheritdoc} */
	public void setConnectionSource(ConnectionSource acs_cs)
	{
		is_connectionSourceClassName = acs_cs.getClass().getName();

		super.setConnectionSource(acs_cs);
	}

	/**
	 * Retorna el valor de dao.
	 *
	 * @param ac_daoClass correspondiente al valor del tipo de objeto Class
	 * @return el valor de dao
	 * @throws B2BException Se�ala que se ha producido una excepci�n
	 */
	public BaseDAO getDAO(Class ac_daoClass)
	    throws B2BException
	{
		BaseDAO lbdao_dao;

		lbdao_dao = null;

		try
		{
			lbdao_dao = (BaseDAO)im_daos.get(ac_daoClass);

			if((lbdao_dao == null) || !lbdao_dao.isShared())
			{
				lbdao_dao = (BaseDAO)ac_daoClass.newInstance();

				lbdao_dao.setConnection(getConnection(is_connectionSourceClassName, ib_autoCommit));
				lbdao_dao.setContext(getContext());
				lbdao_dao.setReadOnly(getReadOnly());
				lbdao_dao.setSpid(getSpid());
				lbdao_dao.setTransactionIsolationLevel(getTransactionIsolationLevel());
				lbdao_dao.setVendorCode(getVendorCode());

				im_daos.put(ac_daoClass, lbdao_dao);
			}
		}
		catch(Exception le_e)
		{
			getLog().error("getDAO", le_e);

			throw new B2BException(SQL_ERROR, le_e);
		}

		return lbdao_dao;
	}

	/**
	 * Sets the rollback only.
	 */
	public void setRollbackOnly()
	{
		ib_rollbackOnly = true;
	}

	/** {@inheritdoc} */
	public void close()
	    throws B2BException
	{
		if(com.b2bsg.common.util.CollectionUtils.isValidCollection(im_daos))
		{
			java.util.Iterator li_daos;

			li_daos = im_daos.values().iterator();

			while(li_daos.hasNext())
			{
				BaseDAO lbdao_dao;

				lbdao_dao = (BaseDAO)li_daos.next();

				lbdao_dao.close();
			}
		}

		im_daos.clear();

		if(!ib_autoCommit)
		{
			ib_autoCommit = true;

			if(isRollbackOnly())
				rollback();
			else
				commit();

			if(!com.b2bsg.common.util.BooleanUtils.getBooleanValue(getReadOnly()))
				setAutoCommit(ib_autoCommit);
		}

		super.close();
	}

	/**
	 * Valida la propiedad rollback only.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rollback only
	 */
	private boolean isRollbackOnly()
	{
		return ib_rollbackOnly;
	}
}
