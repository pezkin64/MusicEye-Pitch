package com.pairip.licensecheck;

import android.os.Bundle;
import android.util.Base64;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

public class LicenseResponseHelper {
    private static final String KEY_FACTORY_ALGORITHM = "RSA";
    private static final String PAYLOAD_LICENSE_DATA = "LICENSE_DATA";
    private static final String PAYLOAD_PACKAGE_NAME = "packageName";
    private static final String PAYLOAD_REPEATED_CHECK_DURATION_TO_RETRY_MILLIS = "repeatedCheckDurationToRetryMillis";
    private static final String PAYLOAD_REPEATED_CHECK_TIME_TO_RETRY_MILLIS = "repeatedCheckTimeToRetryMillis";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static void validateResponse(Bundle bundle, String str) throws LicenseCheckException {
        try {
            String[] jwsPartsForLicenseData = getJwsPartsForLicenseData(bundle);
            JSONObject base64ToJson = base64ToJson(jwsPartsForLicenseData[0]);
            JSONObject base64ToJson2 = base64ToJson(jwsPartsForLicenseData[1]);
            String str2 = jwsPartsForLicenseData[2];
            String str3 = jwsPartsForLicenseData[0] + "." + jwsPartsForLicenseData[1];
            if (base64ToJson.getString("alg").equals("RS256")) {
                verifySignature(str3, str2, SIGNATURE_ALGORITHM, getPublicKey());
                if (!base64ToJson2.getString(PAYLOAD_PACKAGE_NAME).equals(str)) {
                    throw new LicenseCheckException("Package name doesn't match.");
                }
                return;
            }
            throw new LicenseCheckException("Response must be signed with RS256 algorithm.");
        } catch (JSONException e) {
            throw new LicenseCheckException("Could not decode json", e);
        }
    }

    public static RepeatedCheckMetadata getRepeatedCheckMetadata(Bundle bundle) throws LicenseCheckException {
        try {
            JSONObject base64ToJson = base64ToJson(getJwsPartsForLicenseData(bundle)[1]);
            if (!base64ToJson.has(PAYLOAD_REPEATED_CHECK_DURATION_TO_RETRY_MILLIS)) {
                return null;
            }
            if (!base64ToJson.has(PAYLOAD_REPEATED_CHECK_TIME_TO_RETRY_MILLIS)) {
                return null;
            }
            return new RepeatedCheckMetadata(base64ToJson.getLong(PAYLOAD_REPEATED_CHECK_DURATION_TO_RETRY_MILLIS), base64ToJson.getLong(PAYLOAD_REPEATED_CHECK_TIME_TO_RETRY_MILLIS));
        } catch (JSONException e) {
            throw new LicenseCheckException("Could not decode json", e);
        } catch (IllegalArgumentException e2) {
            throw new LicenseCheckException("Invalid repeated check payload", e2);
        }
    }

    private static String[] getJwsPartsForLicenseData(Bundle bundle) throws LicenseCheckException {
        String string = bundle.getString(PAYLOAD_LICENSE_DATA);
        if (string != null) {
            String[] split = string.split("\\.", -1);
            if (split.length == 3) {
                return split;
            }
            throw new LicenseCheckException("Invalid response");
        }
        throw new LicenseCheckException("Invalid response");
    }

    private static JSONObject base64ToJson(String str) throws LicenseCheckException {
        try {
            return new JSONObject(new String(Base64.decode(str, 8), UTF_8));
        } catch (IllegalArgumentException | JSONException e) {
            throw new LicenseCheckException("Invalid response", e);
        }
    }

    private static void verifySignature(String str, String str2, String str3, PublicKey publicKey) throws LicenseCheckException {
        try {
            Signature instance = Signature.getInstance(str3);
            instance.initVerify(publicKey);
            instance.update(str.getBytes(UTF_8));
            if (!instance.verify(Base64.decode(str2, 8))) {
                throw new LicenseCheckException("Signature verification failed.");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new LicenseCheckException(String.format("Could not find %s algorithm on the device", new Object[]{str3}), e);
        } catch (InvalidKeyException e2) {
            throw new LicenseCheckException("Could not sign data with the public key", e2);
        } catch (SignatureException e3) {
            throw new LicenseCheckException("Could not parse returned signature.", e3);
        } catch (IllegalArgumentException e4) {
            throw new LicenseCheckException("Could not base64 decode returned signature", e4);
        }
    }

    private static PublicKey getPublicKey() throws LicenseCheckException {
        try {
            return KeyFactory.getInstance(KEY_FACTORY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(LicenseClient.getLicensePubKey(), 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new LicenseCheckException(String.format("%s algorithm not found on device", new Object[]{KEY_FACTORY_ALGORITHM}), e);
        } catch (IllegalArgumentException e2) {
            throw new LicenseCheckException("Could not decode public key", e2);
        } catch (InvalidKeySpecException e3) {
            throw new LicenseCheckException("Could not create key specification from the public key", e3);
        }
    }

    private LicenseResponseHelper() {
    }
}
