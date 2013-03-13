/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.security.pwd;

import com.liferay.portal.PwdEncryptorException;

/**
 * @author Michael C. Han
 * @author Tomas Polesovsky
 */
public class NullPasswordEncryptor
	extends BasePasswordEncryptor implements PasswordEncryptor {

	public String[] getSupportedAlgorithmTypes() {
		return new String[] { PasswordEncryptorUtil.TYPE_NONE };
	}

	@Override
	protected String doEncrypt(
			String algorithm, String clearTextPassword,
			String currentEncryptedPassword)
		throws PwdEncryptorException {

		return clearTextPassword;
	}

}