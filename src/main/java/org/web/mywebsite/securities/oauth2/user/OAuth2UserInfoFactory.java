package org.web.mywebsite.securities.oauth2.user;



import org.web.mywebsite.exceptions.CommonException;

import java.util.Map;

import static org.web.mywebsite.enums.ResponseCode.ERROR;
import static org.web.mywebsite.enums.AuthProvider.GOOGLE;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new CommonException(ERROR, "Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
