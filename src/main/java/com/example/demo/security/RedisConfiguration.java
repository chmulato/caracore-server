package com.example.demo.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisShardInfo;

/**
 * Created by jarbas on 30/10/15.
 */
@Component
@ConfigurationProperties(prefix = "app.redis")
public class RedisConfiguration {

    private String hostname;
    private Integer port;
    private String password;
    private Boolean enabled;

    public RedisConnectionFactory getConnectionFactory() {
        JedisShardInfo shardInfo = new JedisShardInfo(hostname, port);
        shardInfo.setPassword(password);

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(shardInfo);
        connectionFactory.setHostName(hostname);
        connectionFactory.setPort(port);
        connectionFactory.setPassword(password);

        return connectionFactory;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getHostname() {
        return hostname;
    }

    public String getPassword() {
        return password;
    }

    public Integer getPort() {
        return port;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
