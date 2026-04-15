package org.apache.commons.compress.parallel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.OpenOption;
import kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0;

public class FileBasedScatterGatherBackingStore implements ScatterGatherBackingStore {
    private boolean closed;
    private final OutputStream os;
    private final File target;

    public FileBasedScatterGatherBackingStore(File file) throws FileNotFoundException {
        this.target = file;
        try {
            this.os = PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(file.toPath(), new OpenOption[0]);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public InputStream getInputStream() throws IOException {
        return PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(this.target.toPath(), new OpenOption[0]);
    }

    public void closeForWriting() throws IOException {
        if (!this.closed) {
            this.os.close();
            this.closed = true;
        }
    }

    public void writeOut(byte[] bArr, int i, int i2) throws IOException {
        this.os.write(bArr, i, i2);
    }

    public void close() throws IOException {
        try {
            closeForWriting();
        } finally {
            if (this.target.exists() && !this.target.delete()) {
                this.target.deleteOnExit();
            }
        }
    }
}
