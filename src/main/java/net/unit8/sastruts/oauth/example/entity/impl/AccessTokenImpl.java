package net.unit8.sastruts.oauth.example.entity.impl;


import net.unit8.sastruts.oauth.provider.entity.AccessToken;
import net.unit8.sastruts.oauth.provider.entity.OauthTokenBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="OAUTH_TOKEN")
public class AccessTokenImpl extends OauthTokenBase implements AccessToken {
	public AccessTokenImpl() {
		super();
        type = "ACCESS_TOKEN";
		setupAuthorizedAt();
	}

	protected void setupAuthorizedAt() {
		this.authorizedAt = new Date();
	}
}
