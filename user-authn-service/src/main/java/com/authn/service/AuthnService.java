package com.authn.service;

import com.authn.bean.AuthenticationBean;

public interface AuthnService {

	public AuthenticationBean doLogin(AuthenticationBean authnBean);
}
