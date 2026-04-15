package jp.kshoji.javax.sound.midi;

import java.util.List;

public interface MidiDevice {
    void close();

    Info getDeviceInfo();

    int getMaxReceivers();

    int getMaxTransmitters();

    long getMicrosecondPosition();

    Receiver getReceiver() throws MidiUnavailableException;

    List<Receiver> getReceivers();

    Transmitter getTransmitter() throws MidiUnavailableException;

    List<Transmitter> getTransmitters();

    boolean isOpen();

    void open() throws MidiUnavailableException;

    public static class Info {
        private String description;
        private String name;
        private String vendor;
        private String version;

        public Info(String str, String str2, String str3, String str4) {
            this.name = str;
            this.vendor = str2;
            this.description = str3;
            this.version = str4;
        }

        public final String getName() {
            return this.name;
        }

        public final String getVendor() {
            return this.vendor;
        }

        public final String getDescription() {
            return this.description;
        }

        public final String getVersion() {
            return this.version;
        }

        public final String toString() {
            return this.name;
        }

        public int hashCode() {
            String str = this.description;
            int i = 0;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            String str2 = this.name;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.vendor;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.version;
            if (str4 != null) {
                i = str4.hashCode();
            }
            return hashCode3 + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Info info = (Info) obj;
            String str = this.description;
            if (str == null) {
                if (info.description != null) {
                    return false;
                }
            } else if (!str.equals(info.description)) {
                return false;
            }
            String str2 = this.name;
            if (str2 == null) {
                if (info.name != null) {
                    return false;
                }
            } else if (!str2.equals(info.name)) {
                return false;
            }
            String str3 = this.vendor;
            if (str3 == null) {
                if (info.vendor != null) {
                    return false;
                }
            } else if (!str3.equals(info.vendor)) {
                return false;
            }
            String str4 = this.version;
            if (str4 == null) {
                if (info.version != null) {
                    return false;
                }
            } else if (!str4.equals(info.version)) {
                return false;
            }
            return true;
        }
    }
}
