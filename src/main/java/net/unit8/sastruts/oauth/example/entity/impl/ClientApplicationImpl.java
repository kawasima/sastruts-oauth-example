package net.unit8.sastruts.oauth.example.entity.impl;


import net.unit8.sastruts.oauth.provider.entity.ClientApplication;
import net.unit8.sastruts.oauth.provider.util.RandomUtil;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity(name="ClientApplication")
public class ClientApplicationImpl extends ClientApplication {

    public ClientApplicationImpl() {
        generateKeys();
    }

    protected void generateKeys() {
        key    = RandomUtil.generateKeys(40).substring(0, 40);
        secret = RandomUtil.generateKeys(40).substring(0, 40);
    }

}
