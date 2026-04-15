package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.OpenOption;
import kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0;

class TempFileCachingStreamBridge extends StreamBridge {
    /* access modifiers changed from: private */
    public final File f;

    TempFileCachingStreamBridge() throws IOException {
        File createTempFile = File.createTempFile("commons-compress", "packtemp");
        this.f = createTempFile;
        createTempFile.deleteOnExit();
        this.out = PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(createTempFile.toPath(), new OpenOption[0]);
    }

    /* access modifiers changed from: package-private */
    public InputStream getInputView() throws IOException {
        this.out.close();
        return new FilterInputStream(PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(this.f.toPath(), new OpenOption[0])) {
            public void close() throws IOException {
                try {
                    super.close();
                } finally {
                    TempFileCachingStreamBridge.this.f.delete();
                }
            }
        };
    }
}
