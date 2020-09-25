package com.talkactive.taconversationsstorage.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasypConfig {

    @Bean
    public StandardPBEStringEncryptor getEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        encryptor.setPasswordCharArray("SuperSecretPassword".toCharArray());
        return encryptor;
    }
}
