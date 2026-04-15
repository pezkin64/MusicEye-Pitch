package org.apache.commons.compress.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class BoundedSeekableByteChannelInputStream extends BoundedArchiveInputStream {
    private final SeekableByteChannel channel;

    public BoundedSeekableByteChannelInputStream(long j, long j2, SeekableByteChannel seekableByteChannel) {
        super(j, j2);
        this.channel = seekableByteChannel;
    }

    /* access modifiers changed from: protected */
    public int read(long j, ByteBuffer byteBuffer) throws IOException {
        int m;
        synchronized (this.channel) {
            SeekableByteChannel unused = this.channel.position(j);
            m = this.channel.read(byteBuffer);
        }
        byteBuffer.flip();
        return m;
    }
}
