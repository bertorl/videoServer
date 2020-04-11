package com.aromero.videoserver.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUserEncoder implements PasswordEncoder {

    private static final String HASH_ALGORITHM = "SHA-512";
    private static final Logger LOG = LoggerFactory.getLogger(PasswordUserEncoder.class);
    @Override
    public String encode(CharSequence rawPassword) {

        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance(HASH_ALGORITHM);
            return Base64.getEncoder().encodeToString(digest.digest(rawPassword.toString().getBytes()));
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Encrypt password error", e);
            return "";
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
