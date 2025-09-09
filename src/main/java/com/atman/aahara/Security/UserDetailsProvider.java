package com.atman.aahara.Security;

public interface UserDetailsProvider {
    Object loadUser(String subject);
}
