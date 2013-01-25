package net.unit8.sastruts.oauth.example.entity.impl;


import net.unit8.sastruts.oauth.provider.entity.OauthTokenBase;

import javax.persistence.*;

@Table
@Entity(name = "OauthToken")
public class OauthTokenImpl extends OauthTokenBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    public ClientApplicationImpl clientApplication;

    public OauthTokenImpl() {
        type = "OAUTH_TOKEN";
        generateKeys();
    }

    public String toQuery() {
        StringBuilder query = new StringBuilder();
        query.append("oauth_token=")
                .append(token)
                .append("&oauth_token_secret=")
                .append(secret);
        return query.toString();
    }
}
