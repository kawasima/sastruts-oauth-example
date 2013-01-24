package net.unit8.sastruts.oauth.example.action;

import net.unit8.sastruts.oauth.provider.entity.ClientApplication;
import net.unit8.sastruts.oauth.provider.service.ClientApplicationService;
import org.seasar.struts.annotation.Execute;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: uu034251
 * Date: 13/01/24
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class ConsumerAction {
    @Resource
    protected ClientApplicationService clientApplicationService;

    public List<ClientApplication> clientApplications;

    @Execute(validator = false)
    public String index() {
        clientApplications = clientApplicationService.findAll();
        return "index.jsp";
    }
}
