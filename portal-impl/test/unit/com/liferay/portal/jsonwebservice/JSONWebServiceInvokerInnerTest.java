/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.jsonwebservice.action.JSONWebServiceInvokerAction;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceAction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceInvokerInnerTest extends BaseJSONWebServiceTestCase {

	@BeforeClass
	public static void init() throws Exception {
		initPortalServices();

		registerActionClass(FooService.class);
	}

	@Test
	public void testAddVariableToInnerProperty() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> params = new LinkedHashMap<String, Object>();

		Map<String, Object> map3 = new LinkedHashMap<String, Object>();

		map3.put("@userId", "$p.page");
		map3.put("worldName", "star");

		params.put("data.$XXX2 = /foo/hello-world", map3);

		map.put("$p = /foo/get-foo-page", params);

		String json = invoke(map);
		String expected = prepareExpectedResult(false, false, false);

		Assert.assertEquals(expected, json);
	}

	@Test
	public void testAddVariableToRootAndInnerProperty() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> params = new LinkedHashMap<String, Object>();

		Map<String, Object> map2 = new LinkedHashMap<String, Object>();

		map2.put("@userId", "$p.page");
		map2.put("worldName", "galaxy");

		params.put("$XXX1 = /foo/hello-world", map2);

		Map<String, Object> map3 = new LinkedHashMap<String, Object>();

		map3.put("@userId", "$p.page");
		map3.put("worldName", "star");

		params.put("data.$XXX2 = /foo/hello-world", map3);

		map.put("$p = /foo/get-foo-page", params);

		String json = invoke(map);
		String expected = prepareExpectedResult(true, false, false);

		Assert.assertEquals(expected, json);
	}

	@Test
	public void testAddVariableToRootInnerAndListProperty() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> params = new LinkedHashMap<String, Object>();

		Map<String, Object> map2 = new LinkedHashMap<String, Object>();

		map2.put("@userId", "$p.page");
		map2.put("worldName", "galaxy");

		params.put("$XXX1 = /foo/hello-world", map2);

		Map<String, Object> map3 = new LinkedHashMap<String, Object>();

		map3.put("@userId", "$p.page");
		map3.put("worldName", "star");

		params.put("data.$XXX2 = /foo/hello-world", map3);

		Map<String, Object> map4 = new LinkedHashMap<String, Object>();

		map4.put("@userId", "$p.page");
		map4.put("worldName", "pulsar");

		params.put("list.$XXX3 = /foo/hello-world", map4);

		map.put("$p = /foo/get-foo-page", params);

		String json = invoke(map);
		String expected = prepareExpectedResult(true, true, false);

		Assert.assertEquals(expected, json);
	}

	@Test
	public void testAddVariableToRootInnerAndListPropertyAndListReference()
		throws Exception {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> params = new LinkedHashMap<String, Object>();

		Map<String, Object> map2 = new LinkedHashMap<String, Object>();

		map2.put("@userId", "$p.page");
		map2.put("worldName", "galaxy");

		params.put("$XXX1 = /foo/hello-world", map2);

		Map<String, Object> map3 = new LinkedHashMap<String, Object>();

		map3.put("@userId", "$p.page");
		map3.put("worldName", "star");

		params.put("data.$XXX2 = /foo/hello-world", map3);

		Map<String, Object> map4 = new LinkedHashMap<String, Object>();

		map4.put("@userId", "$p.list.id");
		map4.put("worldName", "pulsar");

		params.put("list.$XXX3 = /foo/hello-world", map4);

		map.put("$p = /foo/get-foo-page", params);

		String json = invoke(map);
		String expected = prepareExpectedResult(true, true, true);

		Assert.assertEquals(expected, json);
	}

	protected String invoke(Object command) throws Exception {
		String json = toJSON(command);

		JSONWebServiceAction jsonWebServiceAction = prepareInvokerAction(json);

		Object result = jsonWebServiceAction.invoke();

		JSONWebServiceInvokerAction.InvokerResult invokerResult =
			(JSONWebServiceInvokerAction.InvokerResult)result;

		return invokerResult.toJSONString();
	}

	protected String prepareExpectedResult(
		boolean xxx1, boolean xxx3, boolean index) {

		LinkedHashMap<String, Object> resultMap =
			new LinkedHashMap<String, Object>();

		if (xxx1) {
			resultMap.put("XXX1", "Welcome 3 to galaxy");
		}

		resultMap.put("page", 3);

		LinkedHashMap<String, Object> resultData =
			new LinkedHashMap<String, Object>();

		resultData.put("id", 2);
		resultData.put("height", 8);
		resultData.put("XXX2", "Welcome 3 to star");
		resultData.put("name", "life");
		resultData.put("array", new int[] {9, 5, 7});

		resultMap.put("data", resultData);

		ArrayList<LinkedHashMap<String, Object>> resultList =
			new ArrayList<LinkedHashMap<String, Object>>();

		LinkedHashMap<String, Object> resultElement =
			new LinkedHashMap<String, Object>();

		if (xxx3) {
			resultElement.put("id", 1);
			resultElement.put("height", 177);
			resultElement.put("XXX3", "Welcome 3 to pulsar");
		}
		else {
			resultElement.put("height", 177);
			resultElement.put("id", 1);
		}

		resultElement.put("name", "John Doe");
		resultElement.put("value", "foo!");

		if (index) {
			resultElement.put("XXX3", "Welcome 1 to pulsar");
		}

		resultList.add(resultElement);

		resultElement = (LinkedHashMap<String, Object>)resultElement.clone();
		resultElement.put("id", 2);

		if (index) {
			resultElement.put("XXX3", "Welcome 2 to pulsar");
		}

		resultList.add(resultElement);

		resultElement = (LinkedHashMap<String, Object>)resultElement.clone();

		resultElement.put("id", 3);

		if (index) {
			resultElement.put("XXX3", "Welcome 3 to pulsar");
		}

		resultList.add(resultElement);

		resultMap.put("list", resultList);

		return toJSON(resultMap, "list", "data.array");
	}

	protected JSONWebServiceAction prepareInvokerAction(String content)
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/invoker");

		mockHttpServletRequest.setContent(content.getBytes());

		return new JSONWebServiceInvokerAction(mockHttpServletRequest);
	}

}