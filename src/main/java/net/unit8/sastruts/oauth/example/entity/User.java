package net.unit8.sastruts.oauth.example.entity;

import net.unit8.sastruts.oauth.provider.entity.ResourceOwner;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: UU034251
 * Date: 13/01/08
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class User implements ResourceOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String account;
    public String password;
    public String nickname;
    public String telNo;
    public String address;

    @Transient
    public Long getResourceOwnerId() {
        return id;
    }
}
