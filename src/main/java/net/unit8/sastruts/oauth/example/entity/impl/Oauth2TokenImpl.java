package net.unit8.sastruts.oauth.example.entity.impl;


import net.unit8.sastruts.oauth.provider.entity.Oauth2Token;
import net.unit8.sastruts.oauth.provider.entity.OauthTokenBase;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.util.URLEncoderUtil;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: UU034251
 * Date: 12/12/28
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="OAUTH_TOKEN")
public class Oauth2TokenImpl extends OauthTokenBase implements Oauth2Token {
    @Transient
    public String state;

    public Oauth2TokenImpl() {
        super();
        authorizedAt = new Date();
        type = "OAUTH2_TOKEN";
    }

    public String toQuery() {
        StringBuilder q = new StringBuilder();
        q.append("access_token=").append(token).append("&token_type=bearer");
        if (StringUtil.isNotEmpty(state)) {
            q.append("&state=").append(URLEncoderUtil.encode(state));
        }
        if (expiresAt != null) {
            q.append("&expires_in=").append(expiresIn());
        }
        if (StringUtil.isNotEmpty(scope)) {
            q.append("&scope=").append(URLEncoderUtil.encode(scope));
        }
        return q.toString();
    }

    private long expiresIn() {
        return expiresAt.getTime() - System.currentTimeMillis();
    }

    @Override
    public String toJSON() {
        StringBuilder json = new StringBuilder(256);
        json.append("{\"access_token\": \"")
                .append(token)
                .append("\", \"token_type\": \"bearer\"");
        if (expiresAt != null) {
            json.append(", \"expires_in\":").append(expiresIn());
        }
        json.append("}");
        return json.toString();
    }

}
