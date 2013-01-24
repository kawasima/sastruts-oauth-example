package net.unit8.sastruts.oauth.example.dto;


import net.unit8.sastruts.oauth.provider.entity.ResourceOwner;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: UU034251
 * Date: 13/01/08
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class UserDto implements Serializable, ResourceOwner {
    public Long id;
    public String account;
    public String nickname;

    public Long getResourceOwnerId() {
        return id;
    }
}
