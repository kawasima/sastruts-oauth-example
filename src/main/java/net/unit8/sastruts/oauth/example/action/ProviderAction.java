package net.unit8.sastruts.oauth.example.action;

import net.unit8.sastruts.oauth.example.LoginRequired;
import net.unit8.sastruts.oauth.example.dto.UserDto;
import net.unit8.sastruts.oauth.example.form.ProviderForm;
import net.unit8.sastruts.oauth.provider.logic.ProviderLogic;
import net.unit8.sastruts.oauth.provider.service.ClientApplicationService;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import javax.annotation.Resource;

public class ProviderAction {
	@ActionForm
    @Resource
	protected ProviderForm providerForm;

    @Resource
	protected ClientApplicationService clientApplicationService;

    @Resource
    protected ProviderLogic providerLogic;

    //public ResourceOwner resourceOwner;
    public UserDto resourceOwner;

	@Execute(validator=false)
	public String token() {
        BeanMap params = Beans.createAndCopy(BeanMap.class, providerForm).execute();
        return providerLogic.token(params);
	}

    @LoginRequired
    @Execute(validator = false)
	public String authorize() {
        BeanMap params = Beans.createAndCopy(BeanMap.class, providerForm).execute();
        return providerLogic.authorize(resourceOwner, params);
	}
}
