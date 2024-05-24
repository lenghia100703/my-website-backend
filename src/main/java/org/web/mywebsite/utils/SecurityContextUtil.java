package org.web.mywebsite.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.web.mywebsite.configs.CustomAuthentication;

public class SecurityContextUtil {
    public static Long getCurrentUserId() {
        CustomAuthentication authentication = (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getId();
    }
}
