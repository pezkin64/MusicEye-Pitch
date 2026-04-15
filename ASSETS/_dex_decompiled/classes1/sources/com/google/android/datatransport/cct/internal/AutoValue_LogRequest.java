package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

final class AutoValue_LogRequest extends LogRequest {
    private final ClientInfo clientInfo;
    private final List<LogEvent> logEvents;
    private final Integer logSource;
    private final String logSourceName;
    private final QosTier qosTier;
    private final long requestTimeMs;
    private final long requestUptimeMs;

    private AutoValue_LogRequest(long j, long j2, ClientInfo clientInfo2, Integer num, String str, List<LogEvent> list, QosTier qosTier2) {
        this.requestTimeMs = j;
        this.requestUptimeMs = j2;
        this.clientInfo = clientInfo2;
        this.logSource = num;
        this.logSourceName = str;
        this.logEvents = list;
        this.qosTier = qosTier2;
    }

    public long getRequestTimeMs() {
        return this.requestTimeMs;
    }

    public long getRequestUptimeMs() {
        return this.requestUptimeMs;
    }

    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    public Integer getLogSource() {
        return this.logSource;
    }

    public String getLogSourceName() {
        return this.logSourceName;
    }

    @Encodable.Field(name = "logEvent")
    public List<LogEvent> getLogEvents() {
        return this.logEvents;
    }

    public QosTier getQosTier() {
        return this.qosTier;
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.requestTimeMs + ", requestUptimeMs=" + this.requestUptimeMs + ", clientInfo=" + this.clientInfo + ", logSource=" + this.logSource + ", logSourceName=" + this.logSourceName + ", logEvents=" + this.logEvents + ", qosTier=" + this.qosTier + "}";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        r1 = r7.logSource;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r1 = r7.logSourceName;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005e, code lost:
        r1 = r7.logEvents;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0073, code lost:
        r1 = r7.qosTier;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r1 = r7.clientInfo;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r8 != r7) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r8 instanceof com.google.android.datatransport.cct.internal.LogRequest
            r2 = 0
            if (r1 == 0) goto L_0x0089
            com.google.android.datatransport.cct.internal.LogRequest r8 = (com.google.android.datatransport.cct.internal.LogRequest) r8
            long r3 = r7.requestTimeMs
            long r5 = r8.getRequestTimeMs()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0089
            long r3 = r7.requestUptimeMs
            long r5 = r8.getRequestUptimeMs()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0089
            com.google.android.datatransport.cct.internal.ClientInfo r1 = r7.clientInfo
            if (r1 != 0) goto L_0x002a
            com.google.android.datatransport.cct.internal.ClientInfo r1 = r8.getClientInfo()
            if (r1 != 0) goto L_0x0089
            goto L_0x0034
        L_0x002a:
            com.google.android.datatransport.cct.internal.ClientInfo r3 = r8.getClientInfo()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
        L_0x0034:
            java.lang.Integer r1 = r7.logSource
            if (r1 != 0) goto L_0x003f
            java.lang.Integer r1 = r8.getLogSource()
            if (r1 != 0) goto L_0x0089
            goto L_0x0049
        L_0x003f:
            java.lang.Integer r3 = r8.getLogSource()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
        L_0x0049:
            java.lang.String r1 = r7.logSourceName
            if (r1 != 0) goto L_0x0054
            java.lang.String r1 = r8.getLogSourceName()
            if (r1 != 0) goto L_0x0089
            goto L_0x005e
        L_0x0054:
            java.lang.String r3 = r8.getLogSourceName()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
        L_0x005e:
            java.util.List<com.google.android.datatransport.cct.internal.LogEvent> r1 = r7.logEvents
            if (r1 != 0) goto L_0x0069
            java.util.List r1 = r8.getLogEvents()
            if (r1 != 0) goto L_0x0089
            goto L_0x0073
        L_0x0069:
            java.util.List r3 = r8.getLogEvents()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
        L_0x0073:
            com.google.android.datatransport.cct.internal.QosTier r1 = r7.qosTier
            if (r1 != 0) goto L_0x007e
            com.google.android.datatransport.cct.internal.QosTier r8 = r8.getQosTier()
            if (r8 != 0) goto L_0x0089
            goto L_0x0088
        L_0x007e:
            com.google.android.datatransport.cct.internal.QosTier r8 = r8.getQosTier()
            boolean r8 = r1.equals(r8)
            if (r8 == 0) goto L_0x0089
        L_0x0088:
            return r0
        L_0x0089:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.datatransport.cct.internal.AutoValue_LogRequest.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        long j = this.requestTimeMs;
        long j2 = this.requestUptimeMs;
        int i = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        ClientInfo clientInfo2 = this.clientInfo;
        int i2 = 0;
        int hashCode = (i ^ (clientInfo2 == null ? 0 : clientInfo2.hashCode())) * 1000003;
        Integer num = this.logSource;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.logSourceName;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<LogEvent> list = this.logEvents;
        int hashCode4 = (hashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        QosTier qosTier2 = this.qosTier;
        if (qosTier2 != null) {
            i2 = qosTier2.hashCode();
        }
        return hashCode4 ^ i2;
    }

    static final class Builder extends LogRequest.Builder {
        private ClientInfo clientInfo;
        private List<LogEvent> logEvents;
        private Integer logSource;
        private String logSourceName;
        private QosTier qosTier;
        private Long requestTimeMs;
        private Long requestUptimeMs;

        Builder() {
        }

        public LogRequest.Builder setRequestTimeMs(long j) {
            this.requestTimeMs = Long.valueOf(j);
            return this;
        }

        public LogRequest.Builder setRequestUptimeMs(long j) {
            this.requestUptimeMs = Long.valueOf(j);
            return this;
        }

        public LogRequest.Builder setClientInfo(ClientInfo clientInfo2) {
            this.clientInfo = clientInfo2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public LogRequest.Builder setLogSource(Integer num) {
            this.logSource = num;
            return this;
        }

        /* access modifiers changed from: package-private */
        public LogRequest.Builder setLogSourceName(String str) {
            this.logSourceName = str;
            return this;
        }

        public LogRequest.Builder setLogEvents(List<LogEvent> list) {
            this.logEvents = list;
            return this;
        }

        public LogRequest.Builder setQosTier(QosTier qosTier2) {
            this.qosTier = qosTier2;
            return this;
        }

        public LogRequest build() {
            String str;
            if (this.requestTimeMs == null) {
                str = " requestTimeMs";
            } else {
                str = "";
            }
            if (this.requestUptimeMs == null) {
                str = str + " requestUptimeMs";
            }
            if (str.isEmpty()) {
                return new AutoValue_LogRequest(this.requestTimeMs.longValue(), this.requestUptimeMs.longValue(), this.clientInfo, this.logSource, this.logSourceName, this.logEvents, this.qosTier);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
