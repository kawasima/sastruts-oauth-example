package net.unit8.sastruts.oauth.example.action;

import net.unit8.sastruts.oauth.example.entity.impl.ClientApplicationImpl;
import net.unit8.sastruts.oauth.example.form.OauthClientForm;
import net.unit8.sastruts.oauth.provider.entity.ClientApplication;
import net.unit8.sastruts.oauth.provider.entity.OauthToken;
import net.unit8.sastruts.oauth.provider.entity.ResourceOwner;
import net.unit8.sastruts.oauth.provider.service.ClientApplicationService;
import net.unit8.sastruts.oauth.provider.service.OauthTokenService;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.tiger.CollectionsUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import javax.annotation.Resource;
import java.util.List;

/**
 * The action for managing Oauth client application.
 */
public class OauthClientAction {
    @ActionForm
    @Resource
    protected OauthClientForm oauthClientForm;

    @Resource
    protected ClientApplicationService clientApplicationService;

    @Resource
    protected OauthTokenService oauthTokenService;

    public List<ClientApplication> clientApplications;
    public ClientApplication clientApplication;

    public List<OauthToken> oauthTokens;

    public ResourceOwner resourceOwner;

    @Execute(validator = false)
    public String index() {
        clientApplications = clientApplicationService.findAll();
        if (resourceOwner != null) {
            oauthTokens = oauthTokenService.findAllByResourceOwner(resourceOwner.getResourceOwnerId());
        } else {
            oauthTokens = CollectionsUtil.newArrayList();
        }
        return "index.jsp";
    }

    @Execute(validator = false, urlPattern = "new")
    public String newApp() {
        clientApplication = new ClientApplicationImpl();
        return "new.jsp";
    }

    @Execute(validator = true, input = "new.jsp")
    public String create() {
        clientApplication = clientApplicationService.create(oauthClientForm);
        clientApplicationService.insert(clientApplication);
        return "/oauthClient/show?redirect=true";
    }

    @Execute(validator = false, urlPattern="show/{id}")
    public String show() {
        clientApplication = clientApplicationService.find(oauthClientForm.id);
        return "show.jsp";
    }

    @Execute(validator = false, urlPattern="edit/{id}")
    public String edit() {
        clientApplication = clientApplicationService.find(oauthClientForm.id);
        Beans.copy(clientApplication, oauthClientForm).execute();
        return "edit.jsp";
    }

    @Execute(validator = true, input = "new.jsp", redirect = true)
    public String update() {
        clientApplication = clientApplicationService.find(oauthClientForm.id);
        Beans.copy(oauthClientForm, clientApplication).execute();
        clientApplicationService.update(clientApplication);
        return "/oauthClient/show/" + oauthClientForm.id;
    }

    @Execute(validator = false, urlPattern = "destroy/{id}")
    public String destroy() {
        clientApplication = clientApplicationService.find(oauthClientForm.id);
        clientApplicationService.delete(clientApplication);
        return "/oauthClient/index?redirect=true";
    }

}
