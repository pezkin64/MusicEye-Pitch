package org.apache.commons.compress.harmony.pack200;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.AttributeDefinitionBands;
import org.objectweb.asm.Label;

public class NewAttributeBands extends BandSet {
    protected List attributeLayoutElements;
    /* access modifiers changed from: private */
    public int[] backwardsCallCounts;
    /* access modifiers changed from: private */
    public final CpBands cpBands;
    private final AttributeDefinitionBands.AttributeDefinition def;
    private Integral lastPIntegral;
    private boolean usedAtLeastOnce;

    public interface AttributeLayoutElement {
        void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream);

        void pack(OutputStream outputStream) throws IOException, Pack200Exception;

        void renumberBci(IntList intList, Map map);
    }

    public NewAttributeBands(int i, CpBands cpBands2, SegmentHeader segmentHeader, AttributeDefinitionBands.AttributeDefinition attributeDefinition) throws IOException {
        super(i, segmentHeader);
        this.def = attributeDefinition;
        this.cpBands = cpBands2;
        parseLayout();
    }

    public void addAttribute(NewAttribute newAttribute) {
        this.usedAtLeastOnce = true;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(newAttribute.getBytes());
        for (AttributeLayoutElement addAttributeToBand : this.attributeLayoutElements) {
            addAttributeToBand.addAttributeToBand(newAttribute, byteArrayInputStream);
        }
    }

    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        for (AttributeLayoutElement pack : this.attributeLayoutElements) {
            pack.pack(outputStream);
        }
    }

    public String getAttributeName() {
        return this.def.name.getUnderlyingString();
    }

    public int getFlagIndex() {
        return this.def.index;
    }

    public int[] numBackwardsCalls() {
        return this.backwardsCallCounts;
    }

    public boolean isUsedAtLeastOnce() {
        return this.usedAtLeastOnce;
    }

    private void parseLayout() throws IOException {
        String underlyingString = this.def.layout.getUnderlyingString();
        if (this.attributeLayoutElements == null) {
            this.attributeLayoutElements = new ArrayList();
            StringReader stringReader = new StringReader(underlyingString);
            while (true) {
                AttributeLayoutElement readNextAttributeElement = readNextAttributeElement(stringReader);
                if (readNextAttributeElement != null) {
                    this.attributeLayoutElements.add(readNextAttributeElement);
                } else {
                    resolveCalls();
                    return;
                }
            }
        }
    }

    private void resolveCalls() {
        for (int i = 0; i < this.attributeLayoutElements.size(); i++) {
            AttributeLayoutElement attributeLayoutElement = (AttributeLayoutElement) this.attributeLayoutElements.get(i);
            if (attributeLayoutElement instanceof Callable) {
                Callable callable = (Callable) attributeLayoutElement;
                List access$000 = callable.body;
                for (int i2 = 0; i2 < access$000.size(); i2++) {
                    resolveCallsForElement(i, callable, (LayoutElement) access$000.get(i2));
                }
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.attributeLayoutElements.size(); i4++) {
            AttributeLayoutElement attributeLayoutElement2 = (AttributeLayoutElement) this.attributeLayoutElements.get(i4);
            if (attributeLayoutElement2 instanceof Callable) {
                Callable callable2 = (Callable) attributeLayoutElement2;
                if (callable2.isBackwardsCallable) {
                    callable2.setBackwardsCallableIndex(i3);
                    i3++;
                }
            }
        }
        this.backwardsCallCounts = new int[i3];
    }

    private void resolveCallsForElement(int i, Callable callable, LayoutElement layoutElement) {
        if (layoutElement instanceof Call) {
            Call call = (Call) layoutElement;
            int access$200 = call.callableIndex;
            if (access$200 == 0) {
                call.setCallable(callable);
            } else if (access$200 > 0) {
                while (true) {
                    i++;
                    if (i < this.attributeLayoutElements.size()) {
                        AttributeLayoutElement attributeLayoutElement = (AttributeLayoutElement) this.attributeLayoutElements.get(i);
                        if ((attributeLayoutElement instanceof Callable) && access$200 - 1 == 0) {
                            call.setCallable((Callable) attributeLayoutElement);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } else {
                int i2 = i - 1;
                while (i2 >= 0) {
                    AttributeLayoutElement attributeLayoutElement2 = (AttributeLayoutElement) this.attributeLayoutElements.get(i2);
                    if (!(attributeLayoutElement2 instanceof Callable) || (access$200 = access$200 + 1) != 0) {
                        i2--;
                    } else {
                        call.setCallable((Callable) attributeLayoutElement2);
                        return;
                    }
                }
            }
        } else if (layoutElement instanceof Replication) {
            for (LayoutElement resolveCallsForElement : ((Replication) layoutElement).layoutElements) {
                resolveCallsForElement(i, callable, resolveCallsForElement);
            }
        }
    }

    private AttributeLayoutElement readNextAttributeElement(StringReader stringReader) throws IOException {
        stringReader.mark(1);
        int read = stringReader.read();
        if (read == -1) {
            return null;
        }
        if (read == 91) {
            return new Callable(readBody(getStreamUpToMatchingBracket(stringReader)));
        }
        stringReader.reset();
        return readNextLayoutElement(stringReader);
    }

    /* access modifiers changed from: private */
    public LayoutElement readNextLayoutElement(StringReader stringReader) throws IOException {
        int read = stringReader.read();
        List list = null;
        if (read == -1) {
            return null;
        }
        if (read != 40) {
            if (read != 66) {
                if (read != 70) {
                    if (read != 75) {
                        if (!(read == 86 || read == 72 || read == 73)) {
                            switch (read) {
                                case 78:
                                    stringReader.read();
                                    return new Replication(BuildConfig.FLAVOR + ((char) stringReader.read()), readUpToMatchingBracket(stringReader));
                                case 79:
                                    stringReader.mark(1);
                                    if (stringReader.read() != 83) {
                                        stringReader.reset();
                                        return new Integral("O" + ((char) stringReader.read()), this.lastPIntegral);
                                    }
                                    return new Integral("OS" + ((char) stringReader.read()), this.lastPIntegral);
                                case 80:
                                    stringReader.mark(1);
                                    if (stringReader.read() != 79) {
                                        stringReader.reset();
                                        Integral integral = new Integral("P" + ((char) stringReader.read()));
                                        this.lastPIntegral = integral;
                                        return integral;
                                    }
                                    Integral integral2 = new Integral("PO" + ((char) stringReader.read()), this.lastPIntegral);
                                    this.lastPIntegral = integral2;
                                    return integral2;
                                default:
                                    switch (read) {
                                        case 82:
                                            break;
                                        case 83:
                                            break;
                                        case 84:
                                            String str = BuildConfig.FLAVOR + ((char) stringReader.read());
                                            if (str.equals("S")) {
                                                str = str + ((char) stringReader.read());
                                            }
                                            ArrayList arrayList = new ArrayList();
                                            while (true) {
                                                UnionCase readNextUnionCase = readNextUnionCase(stringReader);
                                                if (readNextUnionCase != null) {
                                                    arrayList.add(readNextUnionCase);
                                                } else {
                                                    stringReader.read();
                                                    stringReader.read();
                                                    stringReader.read();
                                                    stringReader.mark(1);
                                                    if (((char) stringReader.read()) != ']') {
                                                        stringReader.reset();
                                                        list = readBody(getStreamUpToMatchingBracket(stringReader));
                                                    }
                                                    return new Union(str, arrayList, list);
                                                }
                                            }
                                        default:
                                            return null;
                                    }
                                    break;
                            }
                        }
                    }
                    StringBuilder sb = new StringBuilder(BuildConfig.FLAVOR);
                    sb.append((char) read);
                    sb.append((char) stringReader.read());
                    char read2 = (char) stringReader.read();
                    sb.append(read2);
                    if (read2 == 'N') {
                        sb.append((char) stringReader.read());
                    }
                    return new Reference(sb.toString());
                }
                return new Integral(new String(new char[]{(char) read, (char) stringReader.read()}));
            }
            return new Integral(new String(new char[]{(char) read}));
        }
        int intValue = readNumber(stringReader).intValue();
        stringReader.read();
        return new Call(intValue);
    }

    private UnionCase readNextUnionCase(StringReader stringReader) throws IOException {
        Integer readNumber;
        stringReader.mark(2);
        stringReader.read();
        if (((char) stringReader.read()) == ')') {
            stringReader.reset();
            return null;
        }
        stringReader.reset();
        stringReader.read();
        ArrayList arrayList = new ArrayList();
        do {
            readNumber = readNumber(stringReader);
            if (readNumber != null) {
                arrayList.add(readNumber);
                stringReader.read();
                continue;
            }
        } while (readNumber != null);
        stringReader.read();
        stringReader.mark(1);
        if (((char) stringReader.read()) == ']') {
            return new UnionCase(arrayList);
        }
        stringReader.reset();
        return new UnionCase(arrayList, readBody(getStreamUpToMatchingBracket(stringReader)));
    }

    public abstract class LayoutElement implements AttributeLayoutElement {
        /* access modifiers changed from: protected */
        public int getLength(char c) {
            if (c == 'B') {
                return 1;
            }
            if (c == 'V') {
                return 0;
            }
            if (c != 'H') {
                return c != 'I' ? 0 : 4;
            }
            return 2;
        }

        public LayoutElement() {
        }
    }

    public class Integral extends LayoutElement {
        private final List band = new ArrayList();
        private final BHSDCodec defaultCodec;
        private Integral previousIntegral;
        private int previousPValue;
        private final String tag;

        public Integral(String str) {
            super();
            this.tag = str;
            this.defaultCodec = NewAttributeBands.this.getCodec(str);
        }

        public Integral(String str, Integral integral) {
            super();
            this.tag = str;
            this.defaultCodec = NewAttributeBands.this.getCodec(str);
            this.previousIntegral = integral;
        }

        public String getTag() {
            return this.tag;
        }

        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            int i;
            int i2;
            Label label = null;
            if (this.tag.equals("B") || this.tag.equals("FB")) {
                i = NewAttributeBands.this.readInteger(1, inputStream) & 255;
            } else if (this.tag.equals("SB")) {
                i = NewAttributeBands.this.readInteger(1, inputStream);
            } else if (this.tag.equals("H") || this.tag.equals("FH")) {
                i = NewAttributeBands.this.readInteger(2, inputStream) & 65535;
            } else if (this.tag.equals("SH")) {
                i = NewAttributeBands.this.readInteger(2, inputStream);
            } else if (this.tag.equals("I") || this.tag.equals("FI")) {
                i = NewAttributeBands.this.readInteger(4, inputStream);
            } else if (this.tag.equals("SI")) {
                i = NewAttributeBands.this.readInteger(4, inputStream);
            } else {
                if (!this.tag.equals("V") && !this.tag.equals("FV") && !this.tag.equals("SV")) {
                    if (this.tag.startsWith("PO") || this.tag.startsWith("OS")) {
                        i2 = NewAttributeBands.this.readInteger(getLength(this.tag.substring(2).toCharArray()[0]), inputStream) + this.previousIntegral.previousPValue;
                        label = newAttribute.getLabel(i2);
                        this.previousPValue = i2;
                    } else if (this.tag.startsWith("P")) {
                        i2 = NewAttributeBands.this.readInteger(getLength(this.tag.substring(1).toCharArray()[0]), inputStream);
                        label = newAttribute.getLabel(i2);
                        this.previousPValue = i2;
                    } else if (this.tag.startsWith("O")) {
                        i2 = NewAttributeBands.this.readInteger(getLength(this.tag.substring(1).toCharArray()[0]), inputStream) + this.previousIntegral.previousPValue;
                        label = newAttribute.getLabel(i2);
                        this.previousPValue = i2;
                    }
                    i = i2;
                }
                i = 0;
            }
            if (label == null) {
                label = Integer.valueOf(i);
            }
            this.band.add(label);
        }

        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            PackingUtils.log("Writing new attribute bands...");
            NewAttributeBands newAttributeBands = NewAttributeBands.this;
            byte[] encodeBandInt = newAttributeBands.encodeBandInt(this.tag, newAttributeBands.integerListToArray(this.band), this.defaultCodec);
            outputStream.write(encodeBandInt);
            PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from " + this.tag + "[" + this.band.size() + "]");
        }

        public int latestValue() {
            List list = this.band;
            return ((Integer) list.get(list.size() - 1)).intValue();
        }

        public void renumberBci(IntList intList, Map map) {
            if (this.tag.startsWith("O") || this.tag.startsWith("PO")) {
                renumberOffsetBci(this.previousIntegral.band, intList, map);
            } else if (this.tag.startsWith("P")) {
                int size = this.band.size() - 1;
                while (size >= 0) {
                    Object obj = this.band.get(size);
                    if (!(obj instanceof Integer)) {
                        if (obj instanceof Label) {
                            this.band.remove(size);
                            this.band.add(size, Integer.valueOf(intList.get(((Integer) map.get(obj)).intValue())));
                        }
                        size--;
                    } else {
                        return;
                    }
                }
            }
        }

        private void renumberOffsetBci(List list, IntList intList, Map map) {
            int size = this.band.size() - 1;
            while (size >= 0) {
                Object obj = this.band.get(size);
                if (!(obj instanceof Integer)) {
                    if (obj instanceof Label) {
                        this.band.remove(size);
                        this.band.add(size, Integer.valueOf(intList.get(((Integer) map.get(obj)).intValue()) - ((Integer) list.get(size)).intValue()));
                    }
                    size--;
                } else {
                    return;
                }
            }
        }
    }

    public class Replication extends LayoutElement {
        private final Integral countElement;
        /* access modifiers changed from: private */
        public final List layoutElements = new ArrayList();

        public Integral getCountElement() {
            return this.countElement;
        }

        public List getLayoutElements() {
            return this.layoutElements;
        }

        public Replication(String str, String str2) throws IOException {
            super();
            this.countElement = new Integral(str);
            StringReader stringReader = new StringReader(str2);
            while (true) {
                LayoutElement access$600 = NewAttributeBands.this.readNextLayoutElement(stringReader);
                if (access$600 != null) {
                    this.layoutElements.add(access$600);
                } else {
                    return;
                }
            }
        }

        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            this.countElement.addAttributeToBand(newAttribute, inputStream);
            int latestValue = this.countElement.latestValue();
            for (int i = 0; i < latestValue; i++) {
                for (AttributeLayoutElement addAttributeToBand : this.layoutElements) {
                    addAttributeToBand.addAttributeToBand(newAttribute, inputStream);
                }
            }
        }

        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            this.countElement.pack(outputStream);
            for (AttributeLayoutElement pack : this.layoutElements) {
                pack.pack(outputStream);
            }
        }

        public void renumberBci(IntList intList, Map map) {
            for (AttributeLayoutElement renumberBci : this.layoutElements) {
                renumberBci.renumberBci(intList, map);
            }
        }
    }

    public class Union extends LayoutElement {
        private final List defaultCaseBody;
        private final List unionCases;
        private final Integral unionTag;

        public Union(String str, List list, List list2) {
            super();
            this.unionTag = new Integral(str);
            this.unionCases = list;
            this.defaultCaseBody = list2;
        }

        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            this.unionTag.addAttributeToBand(newAttribute, inputStream);
            long latestValue = (long) this.unionTag.latestValue();
            boolean z = true;
            for (int i = 0; i < this.unionCases.size(); i++) {
                UnionCase unionCase = (UnionCase) this.unionCases.get(i);
                if (unionCase.hasTag(latestValue)) {
                    unionCase.addAttributeToBand(newAttribute, inputStream);
                    z = false;
                }
            }
            if (z) {
                for (int i2 = 0; i2 < this.defaultCaseBody.size(); i2++) {
                    ((LayoutElement) this.defaultCaseBody.get(i2)).addAttributeToBand(newAttribute, inputStream);
                }
            }
        }

        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            this.unionTag.pack(outputStream);
            for (UnionCase pack : this.unionCases) {
                pack.pack(outputStream);
            }
            for (AttributeLayoutElement pack2 : this.defaultCaseBody) {
                pack2.pack(outputStream);
            }
        }

        public void renumberBci(IntList intList, Map map) {
            for (UnionCase renumberBci : this.unionCases) {
                renumberBci.renumberBci(intList, map);
            }
            for (AttributeLayoutElement renumberBci2 : this.defaultCaseBody) {
                renumberBci2.renumberBci(intList, map);
            }
        }

        public Integral getUnionTag() {
            return this.unionTag;
        }

        public List getUnionCases() {
            return this.unionCases;
        }

        public List getDefaultCaseBody() {
            return this.defaultCaseBody;
        }
    }

    public class Call extends LayoutElement {
        private Callable callable;
        /* access modifiers changed from: private */
        public final int callableIndex;

        public void pack(OutputStream outputStream) {
        }

        public void renumberBci(IntList intList, Map map) {
        }

        public Call(int i) {
            super();
            this.callableIndex = i;
        }

        public void setCallable(Callable callable2) {
            this.callable = callable2;
            if (this.callableIndex < 1) {
                callable2.setBackwardsCallable();
            }
        }

        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            this.callable.addAttributeToBand(newAttribute, inputStream);
            if (this.callableIndex < 1) {
                this.callable.addBackwardsCall();
            }
        }

        public int getCallableIndex() {
            return this.callableIndex;
        }

        public Callable getCallable() {
            return this.callable;
        }
    }

    public class Reference extends LayoutElement {
        private List band;
        private boolean nullsAllowed = false;
        private final String tag;

        public void renumberBci(IntList intList, Map map) {
        }

        public Reference(String str) {
            super();
            boolean z = false;
            this.tag = str;
            this.nullsAllowed = str.indexOf(78) != -1 ? true : z;
        }

        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            int access$500 = NewAttributeBands.this.readInteger(4, inputStream);
            if (this.tag.startsWith("RC")) {
                this.band.add(NewAttributeBands.this.cpBands.getCPClass(newAttribute.readClass(access$500)));
            } else if (this.tag.startsWith("RU")) {
                this.band.add(NewAttributeBands.this.cpBands.getCPUtf8(newAttribute.readUTF8(access$500)));
            } else if (this.tag.startsWith("RS")) {
                this.band.add(NewAttributeBands.this.cpBands.getCPSignature(newAttribute.readUTF8(access$500)));
            } else {
                this.band.add(NewAttributeBands.this.cpBands.getConstant(newAttribute.readConst(access$500)));
            }
        }

        public String getTag() {
            return this.tag;
        }

        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            int[] iArr;
            if (this.nullsAllowed) {
                iArr = NewAttributeBands.this.cpEntryOrNullListToArray(this.band);
            } else {
                iArr = NewAttributeBands.this.cpEntryListToArray(this.band);
            }
            byte[] encodeBandInt = NewAttributeBands.this.encodeBandInt(this.tag, iArr, Codec.UNSIGNED5);
            outputStream.write(encodeBandInt);
            PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from " + this.tag + "[" + iArr.length + "]");
        }
    }

    public class Callable implements AttributeLayoutElement {
        private int backwardsCallableIndex;
        /* access modifiers changed from: private */
        public final List body;
        /* access modifiers changed from: private */
        public boolean isBackwardsCallable;

        public Callable(List list) throws IOException {
            this.body = list;
        }

        public void setBackwardsCallableIndex(int i) {
            this.backwardsCallableIndex = i;
        }

        public void addBackwardsCall() {
            int[] access$800 = NewAttributeBands.this.backwardsCallCounts;
            int i = this.backwardsCallableIndex;
            access$800[i] = access$800[i] + 1;
        }

        public boolean isBackwardsCallable() {
            return this.isBackwardsCallable;
        }

        public void setBackwardsCallable() {
            this.isBackwardsCallable = true;
        }

        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            for (AttributeLayoutElement addAttributeToBand : this.body) {
                addAttributeToBand.addAttributeToBand(newAttribute, inputStream);
            }
        }

        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            for (AttributeLayoutElement pack : this.body) {
                pack.pack(outputStream);
            }
        }

        public void renumberBci(IntList intList, Map map) {
            for (AttributeLayoutElement renumberBci : this.body) {
                renumberBci.renumberBci(intList, map);
            }
        }

        public List getBody() {
            return this.body;
        }
    }

    public class UnionCase extends LayoutElement {
        private final List body;
        private final List tags;

        public UnionCase(List list) {
            super();
            this.tags = list;
            this.body = Collections.EMPTY_LIST;
        }

        public boolean hasTag(long j) {
            return this.tags.contains(Integer.valueOf((int) j));
        }

        public UnionCase(List list, List list2) throws IOException {
            super();
            this.tags = list;
            this.body = list2;
        }

        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            for (int i = 0; i < this.body.size(); i++) {
                ((LayoutElement) this.body.get(i)).addAttributeToBand(newAttribute, inputStream);
            }
        }

        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            for (int i = 0; i < this.body.size(); i++) {
                ((LayoutElement) this.body.get(i)).pack(outputStream);
            }
        }

        public void renumberBci(IntList intList, Map map) {
            for (int i = 0; i < this.body.size(); i++) {
                ((LayoutElement) this.body.get(i)).renumberBci(intList, map);
            }
        }

        public List getBody() {
            return this.body;
        }
    }

    private StringReader getStreamUpToMatchingBracket(StringReader stringReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int i = -1;
        while (i != 0) {
            char read = (char) stringReader.read();
            if (read == ']') {
                i++;
            }
            if (read == '[') {
                i--;
            }
            if (i != 0) {
                stringBuffer.append(read);
            }
        }
        return new StringReader(stringBuffer.toString());
    }

    /* access modifiers changed from: private */
    public int readInteger(int i, InputStream inputStream) {
        int i2 = 0;
        byte b = 0;
        while (i2 < i) {
            try {
                b = (b << 8) | inputStream.read();
                i2++;
            } catch (IOException unused) {
                throw new RuntimeException("Error reading unknown attribute");
            }
        }
        if (i == 1) {
            b = (byte) b;
        }
        return i == 2 ? (short) b : b;
    }

    /* access modifiers changed from: private */
    public BHSDCodec getCodec(String str) {
        if (str.indexOf(79) >= 0) {
            return Codec.BRANCH5;
        }
        if (str.indexOf(80) >= 0) {
            return Codec.BCI5;
        }
        if (str.indexOf(83) >= 0 && str.indexOf("KS") < 0 && str.indexOf("RS") < 0) {
            return Codec.SIGNED5;
        }
        if (str.indexOf(66) >= 0) {
            return Codec.BYTE1;
        }
        return Codec.UNSIGNED5;
    }

    private String readUpToMatchingBracket(StringReader stringReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int i = -1;
        while (i != 0) {
            char read = (char) stringReader.read();
            if (read == ']') {
                i++;
            }
            if (read == '[') {
                i--;
            }
            if (i != 0) {
                stringBuffer.append(read);
            }
        }
        return stringBuffer.toString();
    }

    private Integer readNumber(StringReader stringReader) throws IOException {
        boolean z = true;
        stringReader.mark(1);
        int i = 0;
        if (((char) stringReader.read()) != '-') {
            z = false;
        }
        if (!z) {
            stringReader.reset();
        }
        stringReader.mark(100);
        while (true) {
            int read = stringReader.read();
            if (read == -1 || !Character.isDigit((char) read)) {
                stringReader.reset();
            } else {
                i++;
            }
        }
        stringReader.reset();
        if (i == 0) {
            return null;
        }
        char[] cArr = new char[i];
        if (stringReader.read(cArr) == i) {
            return Integer.valueOf(Integer.parseInt((z ? "-" : BuildConfig.FLAVOR).concat(new String(cArr))));
        }
        throw new IOException("Error reading from the input stream");
    }

    private List readBody(StringReader stringReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            LayoutElement readNextLayoutElement = readNextLayoutElement(stringReader);
            if (readNextLayoutElement == null) {
                return arrayList;
            }
            arrayList.add(readNextLayoutElement);
        }
    }

    public void renumberBci(IntList intList, Map map) {
        for (AttributeLayoutElement renumberBci : this.attributeLayoutElements) {
            renumberBci.renumberBci(intList, map);
        }
    }
}
