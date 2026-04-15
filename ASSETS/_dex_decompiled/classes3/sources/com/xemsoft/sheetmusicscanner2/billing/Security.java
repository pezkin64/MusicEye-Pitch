package com.xemsoft.sheetmusicscanner2.billing;

import android.text.TextUtils;
import android.util.Base64;
import com.xemsoft.sheetmusicscanner2.util.Logg;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

class Security {
    private static final String BASE_64_ENCODED_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgqEH5Y6ZH6mgf6jwJXFg4jL17XSXtYlNlWD/ktev7zFLQPmObPiWCLRwjvq18hEf2a3Sb/Zt+dgPjRCMWebjckUSASkSmoeAzGrG7Z7+rNZCBxCpZ32FuHmoteLDe5vNdb2ewpu2DexNItIFwrtAOFqb+hI8KjU2RKQ0r9vQMNrEMTEasNPedEGsNz8YqYKKF8nTeMEX1jTngm1o1sSZm7pL0eLgA/ft5ex2vPQTgl2aLGEjNvJc5O9glwGZFJsxdy7Flx5JkdQwXMybPL7JW3pyRPBwWJ9MvjbMEzL80ioe9l0Aba1wxAnFB6TdDLhzGZHjoub9/RvoxkvQgpF94QIDAQAB";
    private static final String KEY_FACTORY_ALGORITHM = "RSA";
    private static final String LOGTAG = "Security";
    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    Security() {
    }

    public static boolean verifyPurchase(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgqEH5Y6ZH6mgf6jwJXFg4jL17XSXtYlNlWD/ktev7zFLQPmObPiWCLRwjvq18hEf2a3Sb/Zt+dgPjRCMWebjckUSASkSmoeAzGrG7Z7+rNZCBxCpZ32FuHmoteLDe5vNdb2ewpu2DexNItIFwrtAOFqb+hI8KjU2RKQ0r9vQMNrEMTEasNPedEGsNz8YqYKKF8nTeMEX1jTngm1o1sSZm7pL0eLgA/ft5ex2vPQTgl2aLGEjNvJc5O9glwGZFJsxdy7Flx5JkdQwXMybPL7JW3pyRPBwWJ9MvjbMEzL80ioe9l0Aba1wxAnFB6TdDLhzGZHjoub9/RvoxkvQgpF94QIDAQAB") || TextUtils.isEmpty(str2)) {
            Logg.w(LOGTAG, "Purchase verification failed: missing data.");
            return false;
        }
        try {
            return verify(generatePublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgqEH5Y6ZH6mgf6jwJXFg4jL17XSXtYlNlWD/ktev7zFLQPmObPiWCLRwjvq18hEf2a3Sb/Zt+dgPjRCMWebjckUSASkSmoeAzGrG7Z7+rNZCBxCpZ32FuHmoteLDe5vNdb2ewpu2DexNItIFwrtAOFqb+hI8KjU2RKQ0r9vQMNrEMTEasNPedEGsNz8YqYKKF8nTeMEX1jTngm1o1sSZm7pL0eLgA/ft5ex2vPQTgl2aLGEjNvJc5O9glwGZFJsxdy7Flx5JkdQwXMybPL7JW3pyRPBwWJ9MvjbMEzL80ioe9l0Aba1wxAnFB6TdDLhzGZHjoub9/RvoxkvQgpF94QIDAQAB"), str, str2).booleanValue();
        } catch (IOException e) {
            String str3 = LOGTAG;
            Logg.e(str3, "Error generating PublicKey from encoded key: " + e.getMessage());
            return false;
        }
    }

    private static PublicKey generatePublicKey(String str) throws IOException {
        try {
            return KeyFactory.getInstance(KEY_FACTORY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e2) {
            String str2 = "Invalid key specification: " + e2;
            Logg.w(LOGTAG, str2);
            throw new IOException(str2);
        }
    }

    private static Boolean verify(PublicKey publicKey, String str, String str2) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            try {
                Signature instance = Signature.getInstance(SIGNATURE_ALGORITHM);
                instance.initVerify(publicKey);
                instance.update(str.getBytes());
                if (instance.verify(decode)) {
                    return true;
                }
                Logg.w(LOGTAG, "Signature verification failed...");
                return false;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException unused) {
                Logg.e(LOGTAG, "Invalid key specification.");
                return false;
            } catch (SignatureException unused2) {
                Logg.e(LOGTAG, "Signature exception.");
                return false;
            }
        } catch (IllegalArgumentException unused3) {
            Logg.w(LOGTAG, "Base64 decoding failed.");
            return false;
        }
    }
}
