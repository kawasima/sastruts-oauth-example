package net.unit8.sastruts.oauth.example.entity.impl;


import net.unit8.sastruts.oauth.provider.entity.OauthTokenBase;
import net.unit8.sastruts.oauth.provider.entity.RequestToken;
import net.unit8.sastruts.oauth.provider.entity.ResourceOwner;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: UU034251
 * Date: 12/12/28
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="OAUTH_TOKEN")
public class RequestTokenImpl extends OauthTokenBase implements RequestToken {
    @Transient
    private String providedOauthVerifier;

    public RequestTokenImpl() {
        super();
        type = "REQUEST_TOKEN";
    }

    public void authorize(ResourceOwner user) {
        this.user = user;
    }

    public String toQuery() {
        return "oauth_callback_confirm=true";
    }

}
