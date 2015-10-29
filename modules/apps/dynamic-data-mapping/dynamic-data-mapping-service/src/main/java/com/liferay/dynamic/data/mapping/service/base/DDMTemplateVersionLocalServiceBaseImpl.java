/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.dynamic.data.mapping.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMTemplateVersion;
import com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateVersionPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the d d m template version local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMTemplateVersionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMTemplateVersionLocalServiceImpl
 * @see com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class DDMTemplateVersionLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DDMTemplateVersionLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalServiceUtil} to access the d d m template version local service.
	 */

	/**
	 * Adds the d d m template version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplateVersion the d d m template version
	 * @return the d d m template version that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMTemplateVersion addDDMTemplateVersion(
		DDMTemplateVersion ddmTemplateVersion) {
		ddmTemplateVersion.setNew(true);

		return ddmTemplateVersionPersistence.update(ddmTemplateVersion);
	}

	/**
	 * Creates a new d d m template version with the primary key. Does not add the d d m template version to the database.
	 *
	 * @param templateVersionId the primary key for the new d d m template version
	 * @return the new d d m template version
	 */
	@Override
	public DDMTemplateVersion createDDMTemplateVersion(long templateVersionId) {
		return ddmTemplateVersionPersistence.create(templateVersionId);
	}

	/**
	 * Deletes the d d m template version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param templateVersionId the primary key of the d d m template version
	 * @return the d d m template version that was removed
	 * @throws PortalException if a d d m template version with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMTemplateVersion deleteDDMTemplateVersion(long templateVersionId)
		throws PortalException {
		return ddmTemplateVersionPersistence.remove(templateVersionId);
	}

	/**
	 * Deletes the d d m template version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplateVersion the d d m template version
	 * @return the d d m template version that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMTemplateVersion deleteDDMTemplateVersion(
		DDMTemplateVersion ddmTemplateVersion) {
		return ddmTemplateVersionPersistence.remove(ddmTemplateVersion);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DDMTemplateVersion.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ddmTemplateVersionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMTemplateVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return ddmTemplateVersionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMTemplateVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return ddmTemplateVersionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return ddmTemplateVersionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return ddmTemplateVersionPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public DDMTemplateVersion fetchDDMTemplateVersion(long templateVersionId) {
		return ddmTemplateVersionPersistence.fetchByPrimaryKey(templateVersionId);
	}

	/**
	 * Returns the d d m template version with the primary key.
	 *
	 * @param templateVersionId the primary key of the d d m template version
	 * @return the d d m template version
	 * @throws PortalException if a d d m template version with the primary key could not be found
	 */
	@Override
	public DDMTemplateVersion getDDMTemplateVersion(long templateVersionId)
		throws PortalException {
		return ddmTemplateVersionPersistence.findByPrimaryKey(templateVersionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(DDMTemplateVersion.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("templateVersionId");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(DDMTemplateVersion.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("templateVersionId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return ddmTemplateVersionLocalService.deleteDDMTemplateVersion((DDMTemplateVersion)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return ddmTemplateVersionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the d d m template versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMTemplateVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m template versions
	 * @param end the upper bound of the range of d d m template versions (not inclusive)
	 * @return the range of d d m template versions
	 */
	@Override
	public List<DDMTemplateVersion> getDDMTemplateVersions(int start, int end) {
		return ddmTemplateVersionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of d d m template versions.
	 *
	 * @return the number of d d m template versions
	 */
	@Override
	public int getDDMTemplateVersionsCount() {
		return ddmTemplateVersionPersistence.countAll();
	}

	/**
	 * Updates the d d m template version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplateVersion the d d m template version
	 * @return the d d m template version that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMTemplateVersion updateDDMTemplateVersion(
		DDMTemplateVersion ddmTemplateVersion) {
		return ddmTemplateVersionPersistence.update(ddmTemplateVersion);
	}

	/**
	 * Returns the d d m template version local service.
	 *
	 * @return the d d m template version local service
	 */
	public DDMTemplateVersionLocalService getDDMTemplateVersionLocalService() {
		return ddmTemplateVersionLocalService;
	}

	/**
	 * Sets the d d m template version local service.
	 *
	 * @param ddmTemplateVersionLocalService the d d m template version local service
	 */
	public void setDDMTemplateVersionLocalService(
		DDMTemplateVersionLocalService ddmTemplateVersionLocalService) {
		this.ddmTemplateVersionLocalService = ddmTemplateVersionLocalService;
	}

	/**
	 * Returns the d d m template version remote service.
	 *
	 * @return the d d m template version remote service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMTemplateVersionService getDDMTemplateVersionService() {
		return ddmTemplateVersionService;
	}

	/**
	 * Sets the d d m template version remote service.
	 *
	 * @param ddmTemplateVersionService the d d m template version remote service
	 */
	public void setDDMTemplateVersionService(
		com.liferay.dynamic.data.mapping.service.DDMTemplateVersionService ddmTemplateVersionService) {
		this.ddmTemplateVersionService = ddmTemplateVersionService;
	}

	/**
	 * Returns the d d m template version persistence.
	 *
	 * @return the d d m template version persistence
	 */
	public DDMTemplateVersionPersistence getDDMTemplateVersionPersistence() {
		return ddmTemplateVersionPersistence;
	}

	/**
	 * Sets the d d m template version persistence.
	 *
	 * @param ddmTemplateVersionPersistence the d d m template version persistence
	 */
	public void setDDMTemplateVersionPersistence(
		DDMTemplateVersionPersistence ddmTemplateVersionPersistence) {
		this.ddmTemplateVersionPersistence = ddmTemplateVersionPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.dynamic.data.mapping.model.DDMTemplateVersion",
			ddmTemplateVersionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.dynamic.data.mapping.model.DDMTemplateVersion");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMTemplateVersionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDMTemplateVersion.class;
	}

	protected String getModelClassName() {
		return DDMTemplateVersion.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmTemplateVersionPersistence.getDataSource();

			DB db = DBFactoryUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService.class)
	protected DDMTemplateVersionLocalService ddmTemplateVersionLocalService;
	@BeanReference(type = com.liferay.dynamic.data.mapping.service.DDMTemplateVersionService.class)
	protected com.liferay.dynamic.data.mapping.service.DDMTemplateVersionService ddmTemplateVersionService;
	@BeanReference(type = DDMTemplateVersionPersistence.class)
	protected DDMTemplateVersionPersistence ddmTemplateVersionPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}