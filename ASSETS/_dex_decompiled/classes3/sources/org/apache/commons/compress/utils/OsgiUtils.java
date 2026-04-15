package org.apache.commons.compress.utils;

public class OsgiUtils {
    private static final boolean inOsgiEnvironment = isBundleReference(OsgiUtils.class.getClassLoader().getClass());

    private static boolean isBundleReference(Class<?> cls) {
        while (true) {
            Class<? super Object> cls2 = cls;
            if (cls2 == null) {
                return false;
            }
            if (cls2.getName().equals("org.osgi.framework.BundleReference")) {
                return true;
            }
            for (Class isBundleReference : cls2.getInterfaces()) {
                if (isBundleReference(isBundleReference)) {
                    return true;
                }
            }
            cls2 = cls2.getSuperclass();
        }
    }

    public static boolean isRunningInOsgiEnvironment() {
        return inOsgiEnvironment;
    }
}
