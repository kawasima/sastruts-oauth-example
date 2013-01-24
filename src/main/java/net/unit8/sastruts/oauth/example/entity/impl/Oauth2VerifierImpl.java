package net.unit8.sastruts.oauth.example.entity.impl;


import net.unit8.sastruts.oauth.provider.entity.Oauth2Verifier;
import net.unit8.sastruts.oauth.provider.entity.OauthTokenBase;
import net.unit8.sastruts.oauth.provider.util.RandomUtil;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: uu034251
 * Date: 13/01/18
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="OAUTH_TOKEN")
public class Oauth2VerifierImpl extends OauthTokenBase implements Oauth2Verifier {
    public Oauth2VerifierImpl() {
        super();
        type = "OAUTH2_VERIFIER";
    }

    public String getCode() {
        return token;
    }

    public String redirectUrl() {
        return callbackUrl;
    }

    @Override
    protected void generateKeys() {
        token  = RandomUtil.generateKeys(20).substring(0, 20);
        expiresAt = new Date(new Date().getTime() + 10 * 60 * 1000);
        authorizedAt = new Date();
    }
}
