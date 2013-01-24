package net.unit8.sastruts.oauth.example.service.impl;

import net.unit8.sastruts.oauth.example.entity.impl.*;
import net.unit8.sastruts.oauth.provider.entity.*;
import net.unit8.sastruts.oauth.provider.service.OauthTokenService;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.tiger.CollectionsUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OauthTokenServiceImpl implements OauthTokenService {
    @Resource
    protected JdbcManager jdbcManager;

    public static final Map<Class, Class> TOKEN_CLASS_MAP = CollectionsUtil.newHashMap();
    static {
        TOKEN_CLASS_MAP.put(Oauth2Verifier.class, Oauth2VerifierImpl.class);
        TOKEN_CLASS_MAP.put(Oauth2Token.class, Oauth2TokenImpl.class);
        TOKEN_CLASS_MAP.put(AccessToken.class, AccessTokenImpl.class);
        TOKEN_CLASS_MAP.put(RequestToken.class, RequestTokenImpl.class);
    }

	public boolean isAuthorized(OauthToken token) {
		return ((OauthTokenBase)token).authorizedAt != null && isInvalidated(token);
	}

    public boolean isInvalidated(OauthToken token) {
        return ((OauthTokenBase)token).invalidatedAt != null;
    }

    public OauthToken findValidToken(String token) {
        return jdbcManager.from(OauthTokenImpl.class)
                .where(new SimpleWhere()
                        .isNull("invalidatedAt", true)
                        .isNotNull("authorizedAt", true)
                        .eq("token", token))
                .getSingleResult();
    }

    public List<OauthToken> findAllByResourceOwner(Long resourceOwnerId) {
        return new ArrayList<OauthToken>(jdbcManager.from(OauthTokenImpl.class)
                .where(new SimpleWhere()
                        .eq("userId", resourceOwnerId)
                ).getResultList());
    }

    public <TOKEN extends OauthToken> TOKEN create(Class<TOKEN> tokenClass, BeanMap params) {
        Class<TOKEN> tokenConcreteClass = TOKEN_CLASS_MAP.get(tokenClass);
        TOKEN oauthToken = Beans.createAndCopy(tokenConcreteClass, params).execute();
        jdbcManager.insert(oauthToken).execute();
        return oauthToken;
    }

    public <TOKEN extends OauthToken> TOKEN findByToken(Class<TOKEN> tokenClass, Long clientApplicationId, String token) {
        OauthToken oauthToken = jdbcManager
                .from(OauthTokenImpl.class)
                .where(new SimpleWhere()
                        .eq("clientApplicationId", clientApplicationId)
                        .eq("token", token))
                .getSingleResult();

        if (oauthToken != null) {
            Class<TOKEN> tokenConcreteClass = TOKEN_CLASS_MAP.get(tokenClass);
            return Beans.createAndCopy(tokenConcreteClass, oauthToken).execute();
        }
        return null;
    }

    public <FT extends OauthToken, TT extends OauthToken> TT exchange(FT fromToken, Class<TT> toTokenClass) {
        BeanMap beanMap = Beans
                .createAndCopy(BeanMap.class, fromToken)
                .includes("userId", "clientApplicationId", "scope")
                .execute();
        TT exchangedToken = create(toTokenClass, beanMap);
        invalidate(fromToken);
        return exchangedToken;
    }

    public int insert(OauthToken oauthToken) {
        return jdbcManager.insert(oauthToken).execute();
    }

    public void invalidate(OauthToken oauthToken) {
        ((OauthTokenBase)oauthToken).invalidatedAt = new Date();
        jdbcManager.update(oauthToken).execute();
    }
}
