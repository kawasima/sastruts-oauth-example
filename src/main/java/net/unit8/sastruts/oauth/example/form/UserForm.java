package net.unit8.sastruts.oauth.example.form;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: UU034251
 * Date: 13/01/08
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public class UserForm implements Serializable {
    public Long id;
    public String account;
    public String password;
    public String nickname;
    public String telNo;
    public String address;

    public String redirectUrl;
}
