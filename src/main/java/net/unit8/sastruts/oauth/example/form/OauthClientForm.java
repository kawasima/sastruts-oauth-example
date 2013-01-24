package net.unit8.sastruts.oauth.example.form;

import org.seasar.struts.annotation.Required;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: UU034251
 * Date: 13/01/08
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
public class OauthClientForm implements Serializable {
    public Long id;

    @Required
    public String name;

    @Required
    public String url;

    @Required
    public String callbackUrl;

    public String supportUrl;
}
