package org.apache.commons.compress.archivers.examples;

import java.io.Closeable;
import java.io.IOException;

public interface CloseableConsumer {
    public static final CloseableConsumer CLOSING_CONSUMER = new CloseableConsumer$$ExternalSyntheticLambda0();
    public static final CloseableConsumer NULL_CONSUMER = new CloseableConsumer$$ExternalSyntheticLambda1();

    /* renamed from: org.apache.commons.compress.archivers.examples.CloseableConsumer$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            CloseableConsumer closeableConsumer = CloseableConsumer.CLOSING_CONSUMER;
        }

        public static /* synthetic */ void lambda$static$0(Closeable closeable) throws IOException {
        }
    }

    void accept(Closeable closeable) throws IOException;
}
