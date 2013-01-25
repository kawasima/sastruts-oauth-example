package net.unit8.sastruts.oauth.example.action;

import net.arnx.jsonic.JSON;
import net.unit8.sastruts.oauth.example.form.ConsumerForm;
import net.unit8.sastruts.oauth.provider.entity.ClientApplication;
import net.unit8.sastruts.oauth.provider.service.ClientApplicationService;
import org.apache.commons.io.IOUtils;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
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
    @ActionForm
    protected ConsumerForm consumerForm;

    @Resource
    protected ClientApplicationService clientApplicationService;

    @Resource
    protected HttpServletRequest request;

    public List<ClientApplication> clientApplications;
    public TokenDto tokenDto;

    @Execute(validator = false)
    public String index() {
        if (StringUtil.equals(request.getMethod(), "GET")) {
            clientApplications = clientApplicationService.findAll();
            return "index.jsp";
        } else {
            request.getSession().setAttribute("clientId", consumerForm.clientId);
            return "/provider/authorize?" +
                    "response_type=" + consumerForm.responseType +
                    "&client_id=" + consumerForm.clientId +
                    "&redirect=true";
        }
    }

    @Execute(validator = false)
    public String authorize() {
        String clientId = (String) request.getSession().getAttribute("clientId");
        ClientApplication clientApplication = clientApplicationService.findByKey(clientId);

        try {
            URL tokenUrl = new URL("http://localhost:8093/provider/token?"
                    + "code=" + consumerForm.code
                    + "&client_id=" + clientApplication.key
                    + "&client_secret=" + clientApplication.secret
                    + "&grant_type=authorization_code"
            );
            URLConnection conn = tokenUrl.openConnection();
            String json = IOUtils.toString(conn.getInputStream());
            tokenDto = JSON.decode(json, TokenDto.class);
            return "authorize.jsp";
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static class TokenDto implements Serializable {
        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccess_token(String accessToken) {
            setAccessToken(accessToken);
        }
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

}
