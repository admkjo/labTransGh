package com.labtrans.util;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;

/**
 * singleton. not using CDI bean on purpose (RsaJsonWebKey is not compatible.
 * this can be worked around though)
 *
 */
public class RsaKeyGenerator {

    private RsaKeyGenerator() {
    }

    private static RsaJsonWebKey jsonWebKey;

    public static RsaJsonWebKey generateKey() {
        if (jsonWebKey == null) {
            try {
                jsonWebKey = RsaJwkGenerator.generateJwk(2048);
            } catch (JoseException ex) {
                Logger.getLogger(RsaKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("RSA Key setup... " + jsonWebKey.hashCode());
        return jsonWebKey;
    }

}
