package net.unit8.sastruts.oauth.example.service.impl;

import net.unit8.sastruts.oauth.example.entity.impl.ClientApplicationImpl;
import net.unit8.sastruts.oauth.example.entity.impl.OauthTokenImpl;
import net.unit8.sastruts.oauth.example.entity.impl.RequestTokenImpl;
import net.unit8.sastruts.oauth.provider.entity.ClientApplication;
import net.unit8.sastruts.oauth.provider.service.ClientApplicationService;
import net.unit8.sastruts.oauth.provider.service.OauthTokenService;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.beans.util.Beans;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ClientApplicationServiceImpl implements ClientApplicationService {
    @Resource
    protected JdbcManager jdbcManager;

    @Resource
	protected OauthTokenService oauthTokenService;

    public ClientApplication find(Long id) {
        return jdbcManager
                .from(ClientApplicationImpl.class)
                .id(id)
                .getSingleResult();
    }

	public OauthTokenImpl findToken(String tokenKey) {
		OauthTokenImpl token = jdbcManager
				.from(OauthTokenImpl.class)
				.where(new SimpleWhere().eq("key", tokenKey))
				.getSingleResult();
		if (token != null && oauthTokenService.isAuthorized(token)) {
			return token;
		} else {
			return null;
		}
	}

    public ClientApplication findByKey(String key) {
        return jdbcManager
                .from(ClientApplicationImpl.class)
                .where(new SimpleWhere().eq("key", key))
                .getSingleResult();
    }

    public RequestTokenImpl createRequestToken(ClientApplication clientApplication) {
        RequestTokenImpl requestToken = new RequestTokenImpl();
        requestToken.clientApplicationId = clientApplication.id;
        requestToken.callbackUrl = clientApplication.tokenCallbackUrl;
        oauthTokenService.insert(requestToken);
        return requestToken;
    }

    public List<ClientApplication> findAll() {
       return new ArrayList<ClientApplication>(
               jdbcManager
                       .from(ClientApplicationImpl.class)
                       .getResultList());
    }

    public int insert(ClientApplication clientApplication) {
        return jdbcManager.insert(clientApplication).execute();
    }

    public int delete(ClientApplication clientApplication) {
        return jdbcManager.delete(clientApplication).execute();
    }

    public int update(ClientApplication clientApplication) {
        return jdbcManager.update(clientApplication).execute();
    }

    public ClientApplication create(Object bean) {
        return Beans.createAndCopy(ClientApplicationImpl.class, bean).execute();
    }
}
