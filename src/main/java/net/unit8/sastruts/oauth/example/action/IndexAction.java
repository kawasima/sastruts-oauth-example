package net.unit8.sastruts.oauth.example.action;

import org.seasar.struts.annotation.Execute;

/**
 * Created with IntelliJ IDEA.
 * User: uu034251
 * Date: 13/01/24
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */
public class IndexAction {
    @Execute(validator = false)
    public String index() {
        return "index.jsp";
    }
}
