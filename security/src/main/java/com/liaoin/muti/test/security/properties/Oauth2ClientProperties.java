package com.liaoin.muti.test.security.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Oauth2ClientProperties {

    private String clientId;

    private String clientIdSecret;

    private int accessTokenValiditySeconds = 145440;
}