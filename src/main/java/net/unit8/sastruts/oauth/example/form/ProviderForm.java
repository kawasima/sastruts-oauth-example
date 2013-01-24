package net.unit8.sastruts.oauth.example.form;

import org.seasar.struts.annotation.Required;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProviderForm implements Serializable {
    @Required(target = "authorize")
	public String clientId;
    public String clientSecret;
    public String grantType;

    public String responseType;
    public String redirectUri;
    public String state;
    public String scope;

    public Boolean isAuthorized;

    public String getResponse_type() {
        return responseType;
    }

    public void setResponse_type(String responseType) {
        this.responseType = responseType;
    }

    public String getClient_id() {
        return clientId;
    }

    public void setClient_id(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirect_uri() {
        return redirectUri;
    }

    public void setRedirect_uri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getClient_secret() {
        return clientSecret;
    }

    public void setClient_secret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrant_type() {
        return grantType;
    }

    public void setGrant_type(String grantType) {
        this.grantType = grantType;
    }
}
