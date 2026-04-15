package org.apache.commons.compress.harmony.unpack200;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute;

public class NewAttributeBands extends BandSet {
    /* access modifiers changed from: private */
    public final AttributeLayout attributeLayout;
    protected List attributeLayoutElements;
    private int backwardsCallCount;

    private interface AttributeLayoutElement {
        void addToAttribute(int i, NewAttribute newAttribute);

        void readBands(InputStream inputStream, int i) throws IOException, Pack200Exception;
    }

    public void read(InputStream inputStream) throws IOException, Pack200Exception {
    }

    public void unpack() throws IOException, Pack200Exception {
    }

    public NewAttributeBands(Segment segment, AttributeLayout attributeLayout2) throws IOException {
        super(segment);
        this.attributeLayout = attributeLayout2;
        parseLayout();
        attributeLayout2.setBackwardsCallCount(this.backwardsCallCount);
    }

    public List parseAttributes(InputStream inputStream, int i) throws IOException, Pack200Exception {
        for (int i2 = 0; i2 < this.attributeLayoutElements.size(); i2++) {
            ((AttributeLayoutElement) this.attributeLayoutElements.get(i2)).readBands(inputStream, i);
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i3 = 0; i3 < i; i3++) {
            arrayList.add(getOneAttribute(i3, this.attributeLayoutElements));
        }
        return arrayList;
    }

    private Attribute getOneAttribute(int i, List list) {
        NewAttribute newAttribute = new NewAttribute(this.segment.getCpBands().cpUTF8Value(this.attributeLayout.getName()), this.attributeLayout.getIndex());
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((AttributeLayoutElement) list.get(i2)).addToAttribute(i, newAttribute);
        }
        return newAttribute;
    }

    private void parseLayout() throws IOException {
        if (this.attributeLayoutElements == null) {
            this.attributeLayoutElements = new ArrayList();
            StringReader stringReader = new StringReader(this.attributeLayout.getLayout());
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
        int i = 0;
        for (int i2 = 0; i2 < this.attributeLayoutElements.size(); i2++) {
            AttributeLayoutElement attributeLayoutElement = (AttributeLayoutElement) this.attributeLayoutElements.get(i2);
            if (attributeLayoutElement instanceof Callable) {
                Callable callable = (Callable) attributeLayoutElement;
                if (i2 == 0) {
                    callable.setFirstCallable(true);
                }
                List access$000 = callable.body;
                for (int i3 = 0; i3 < access$000.size(); i3++) {
                    i += resolveCallsForElement(i2, callable, (LayoutElement) access$000.get(i3));
                }
            }
        }
        this.backwardsCallCount = i;
    }

    private int resolveCallsForElement(int i, Callable callable, LayoutElement layoutElement) {
        int i2 = 0;
        if (layoutElement instanceof Call) {
            Call call = (Call) layoutElement;
            int access$100 = call.callableIndex;
            if (access$100 == 0) {
                call.setCallable(callable);
                return 1;
            } else if (access$100 > 0) {
                int i3 = i + 1;
                while (i3 < this.attributeLayoutElements.size()) {
                    AttributeLayoutElement attributeLayoutElement = (AttributeLayoutElement) this.attributeLayoutElements.get(i3);
                    if (!(attributeLayoutElement instanceof Callable) || access$100 - 1 != 0) {
                        i3++;
                    } else {
                        call.setCallable((Callable) attributeLayoutElement);
                        return 0;
                    }
                }
            } else {
                int i4 = i - 1;
                while (i4 >= 0) {
                    AttributeLayoutElement attributeLayoutElement2 = (AttributeLayoutElement) this.attributeLayoutElements.get(i4);
                    if (!(attributeLayoutElement2 instanceof Callable) || (access$100 = access$100 + 1) != 0) {
                        i4--;
                    } else {
                        call.setCallable((Callable) attributeLayoutElement2);
                        return 1;
                    }
                }
                return 1;
            }
        } else if (layoutElement instanceof Replication) {
            for (LayoutElement resolveCallsForElement : ((Replication) layoutElement).layoutElements) {
                i2 += resolveCallsForElement(i, callable, resolveCallsForElement);
            }
        }
        return i2;
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
                                        return new Integral("O" + ((char) stringReader.read()));
                                    }
                                    return new Integral("OS" + ((char) stringReader.read()));
                                case 80:
                                    stringReader.mark(1);
                                    if (stringReader.read() != 79) {
                                        stringReader.reset();
                                        return new Integral("P" + ((char) stringReader.read()));
                                    }
                                    return new Integral("PO" + ((char) stringReader.read()));
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

    private abstract class LayoutElement implements AttributeLayoutElement {
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

        private LayoutElement() {
        }
    }

    public class Integral extends LayoutElement {
        /* access modifiers changed from: private */
        public int[] band;
        private final String tag;

        public Integral(String str) {
            super();
            this.tag = str;
        }

        public void readBands(InputStream inputStream, int i) throws IOException, Pack200Exception {
            NewAttributeBands newAttributeBands = NewAttributeBands.this;
            this.band = newAttributeBands.decodeBandInt(NewAttributeBands.this.attributeLayout.getName() + "_" + this.tag, inputStream, NewAttributeBands.this.getCodec(this.tag), i);
        }

        public void addToAttribute(int i, NewAttribute newAttribute) {
            int i2;
            long j = (long) this.band[i];
            if (this.tag.equals("B") || this.tag.equals("FB")) {
                newAttribute.addInteger(1, j);
            } else if (this.tag.equals("SB")) {
                newAttribute.addInteger(1, (long) ((byte) ((int) j)));
            } else if (this.tag.equals("H") || this.tag.equals("FH")) {
                newAttribute.addInteger(2, j);
            } else if (this.tag.equals("SH")) {
                newAttribute.addInteger(2, (long) ((short) ((int) j)));
            } else if (this.tag.equals("I") || this.tag.equals("FI")) {
                newAttribute.addInteger(4, j);
            } else if (this.tag.equals("SI")) {
                newAttribute.addInteger(4, (long) ((int) j));
            } else if (!this.tag.equals("V") && !this.tag.equals("FV") && !this.tag.equals("SV")) {
                if (this.tag.startsWith("PO")) {
                    newAttribute.addBCOffset(getLength(this.tag.substring(2).toCharArray()[0]), (int) j);
                } else if (this.tag.startsWith("P")) {
                    newAttribute.addBCIndex(getLength(this.tag.substring(1).toCharArray()[0]), (int) j);
                } else if (this.tag.startsWith("OS")) {
                    int length = getLength(this.tag.substring(2).toCharArray()[0]);
                    if (length == 1) {
                        i2 = (byte) ((int) j);
                    } else if (length == 2) {
                        i2 = (short) ((int) j);
                    } else {
                        if (length == 4) {
                            i2 = (int) j;
                        }
                        newAttribute.addBCLength(length, (int) j);
                    }
                    j = (long) i2;
                    newAttribute.addBCLength(length, (int) j);
                } else if (this.tag.startsWith("O")) {
                    newAttribute.addBCLength(getLength(this.tag.substring(1).toCharArray()[0]), (int) j);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public long getValue(int i) {
            return (long) this.band[i];
        }

        public String getTag() {
            return this.tag;
        }
    }

    public class Replication extends LayoutElement {
        private final Integral countElement;
        /* access modifiers changed from: private */
        public final List layoutElements = new ArrayList();

        public Replication(String str, String str2) throws IOException {
            super();
            this.countElement = new Integral(str);
            StringReader stringReader = new StringReader(str2);
            while (true) {
                LayoutElement access$500 = NewAttributeBands.this.readNextLayoutElement(stringReader);
                if (access$500 != null) {
                    this.layoutElements.add(access$500);
                } else {
                    return;
                }
            }
        }

        public void readBands(InputStream inputStream, int i) throws IOException, Pack200Exception {
            this.countElement.readBands(inputStream, i);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                i2 = (int) (((long) i2) + this.countElement.getValue(i3));
            }
            for (int i4 = 0; i4 < this.layoutElements.size(); i4++) {
                ((LayoutElement) this.layoutElements.get(i4)).readBands(inputStream, i2);
            }
        }

        public void addToAttribute(int i, NewAttribute newAttribute) {
            this.countElement.addToAttribute(i, newAttribute);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                i2 = (int) (((long) i2) + this.countElement.getValue(i3));
            }
            long value = this.countElement.getValue(i);
            for (int i4 = i2; ((long) i4) < ((long) i2) + value; i4++) {
                for (int i5 = 0; i5 < this.layoutElements.size(); i5++) {
                    ((LayoutElement) this.layoutElements.get(i5)).addToAttribute(i4, newAttribute);
                }
            }
        }

        public Integral getCountElement() {
            return this.countElement;
        }

        public List getLayoutElements() {
            return this.layoutElements;
        }
    }

    public class Union extends LayoutElement {
        private int[] caseCounts;
        private final List defaultCaseBody;
        private int defaultCount;
        private final List unionCases;
        private final Integral unionTag;

        public Union(String str, List list, List list2) {
            super();
            this.unionTag = new Integral(str);
            this.unionCases = list;
            this.defaultCaseBody = list2;
        }

        public void readBands(InputStream inputStream, int i) throws IOException, Pack200Exception {
            this.unionTag.readBands(inputStream, i);
            int[] access$600 = this.unionTag.band;
            this.caseCounts = new int[this.unionCases.size()];
            for (int i2 = 0; i2 < this.caseCounts.length; i2++) {
                UnionCase unionCase = (UnionCase) this.unionCases.get(i2);
                for (int i3 : access$600) {
                    if (unionCase.hasTag((long) i3)) {
                        int[] iArr = this.caseCounts;
                        iArr[i2] = iArr[i2] + 1;
                    }
                }
                unionCase.readBands(inputStream, this.caseCounts[i2]);
            }
            for (int i4 = 0; i4 < access$600.length; i4++) {
                boolean z = false;
                for (int i5 = 0; i5 < this.unionCases.size(); i5++) {
                    if (((UnionCase) this.unionCases.get(i5)).hasTag((long) access$600[i4])) {
                        z = true;
                    }
                }
                if (!z) {
                    this.defaultCount++;
                }
            }
            if (this.defaultCaseBody != null) {
                for (int i6 = 0; i6 < this.defaultCaseBody.size(); i6++) {
                    ((LayoutElement) this.defaultCaseBody.get(i6)).readBands(inputStream, this.defaultCount);
                }
            }
        }

        public void addToAttribute(int i, NewAttribute newAttribute) {
            this.unionTag.addToAttribute(i, newAttribute);
            int[] access$600 = this.unionTag.band;
            long value = this.unionTag.getValue(i);
            int i2 = 0;
            boolean z = true;
            for (int i3 = 0; i3 < this.unionCases.size(); i3++) {
                UnionCase unionCase = (UnionCase) this.unionCases.get(i3);
                if (unionCase.hasTag(value)) {
                    for (int i4 = 0; i4 < i; i4++) {
                        if (unionCase.hasTag((long) access$600[i4])) {
                            i2++;
                        }
                    }
                    unionCase.addToAttribute(i2, newAttribute);
                    z = false;
                }
            }
            if (z) {
                int i5 = 0;
                for (int i6 = 0; i6 < i; i6++) {
                    boolean z2 = false;
                    for (int i7 = 0; i7 < this.unionCases.size(); i7++) {
                        if (((UnionCase) this.unionCases.get(i7)).hasTag((long) access$600[i6])) {
                            z2 = true;
                        }
                    }
                    if (!z2) {
                        i5++;
                    }
                }
                if (this.defaultCaseBody != null) {
                    for (int i8 = 0; i8 < this.defaultCaseBody.size(); i8++) {
                        ((LayoutElement) this.defaultCaseBody.get(i8)).addToAttribute(i5, newAttribute);
                    }
                }
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

        public void readBands(InputStream inputStream, int i) {
            if (this.callableIndex > 0) {
                this.callable.addCount(i);
            }
        }

        public void addToAttribute(int i, NewAttribute newAttribute) {
            this.callable.addNextToAttribute(newAttribute);
        }

        public int getCallableIndex() {
            return this.callableIndex;
        }

        public Callable getCallable() {
            return this.callable;
        }
    }

    public class Reference extends LayoutElement {
        private Object band;
        private final int length;
        private final String tag;

        public Reference(String str) {
            super();
            this.tag = str;
            this.length = getLength(str.charAt(str.length() - 1));
        }

        public void readBands(InputStream inputStream, int i) throws IOException, Pack200Exception {
            if (this.tag.startsWith("KI")) {
                NewAttributeBands newAttributeBands = NewAttributeBands.this;
                this.band = newAttributeBands.parseCPIntReferences(newAttributeBands.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("KJ")) {
                NewAttributeBands newAttributeBands2 = NewAttributeBands.this;
                this.band = newAttributeBands2.parseCPLongReferences(newAttributeBands2.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("KF")) {
                NewAttributeBands newAttributeBands3 = NewAttributeBands.this;
                this.band = newAttributeBands3.parseCPFloatReferences(newAttributeBands3.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("KD")) {
                NewAttributeBands newAttributeBands4 = NewAttributeBands.this;
                this.band = newAttributeBands4.parseCPDoubleReferences(newAttributeBands4.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("KS")) {
                NewAttributeBands newAttributeBands5 = NewAttributeBands.this;
                this.band = newAttributeBands5.parseCPStringReferences(newAttributeBands5.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("RC")) {
                NewAttributeBands newAttributeBands6 = NewAttributeBands.this;
                this.band = newAttributeBands6.parseCPClassReferences(newAttributeBands6.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("RS")) {
                NewAttributeBands newAttributeBands7 = NewAttributeBands.this;
                this.band = newAttributeBands7.parseCPSignatureReferences(newAttributeBands7.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("RD")) {
                NewAttributeBands newAttributeBands8 = NewAttributeBands.this;
                this.band = newAttributeBands8.parseCPDescriptorReferences(newAttributeBands8.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("RF")) {
                NewAttributeBands newAttributeBands9 = NewAttributeBands.this;
                this.band = newAttributeBands9.parseCPFieldRefReferences(newAttributeBands9.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("RM")) {
                NewAttributeBands newAttributeBands10 = NewAttributeBands.this;
                this.band = newAttributeBands10.parseCPMethodRefReferences(newAttributeBands10.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("RI")) {
                NewAttributeBands newAttributeBands11 = NewAttributeBands.this;
                this.band = newAttributeBands11.parseCPInterfaceMethodRefReferences(newAttributeBands11.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            } else if (this.tag.startsWith("RU")) {
                NewAttributeBands newAttributeBands12 = NewAttributeBands.this;
                this.band = newAttributeBands12.parseCPUTF8References(newAttributeBands12.attributeLayout.getName(), inputStream, Codec.UNSIGNED5, i);
            }
        }

        public void addToAttribute(int i, NewAttribute newAttribute) {
            if (this.tag.startsWith("KI")) {
                newAttribute.addToBody(this.length, ((CPInteger[]) this.band)[i]);
            } else if (this.tag.startsWith("KJ")) {
                newAttribute.addToBody(this.length, ((CPLong[]) this.band)[i]);
            } else if (this.tag.startsWith("KF")) {
                newAttribute.addToBody(this.length, ((CPFloat[]) this.band)[i]);
            } else if (this.tag.startsWith("KD")) {
                newAttribute.addToBody(this.length, ((CPDouble[]) this.band)[i]);
            } else if (this.tag.startsWith("KS")) {
                newAttribute.addToBody(this.length, ((CPString[]) this.band)[i]);
            } else if (this.tag.startsWith("RC")) {
                newAttribute.addToBody(this.length, ((CPClass[]) this.band)[i]);
            } else if (this.tag.startsWith("RS")) {
                newAttribute.addToBody(this.length, ((CPUTF8[]) this.band)[i]);
            } else if (this.tag.startsWith("RD")) {
                newAttribute.addToBody(this.length, ((CPNameAndType[]) this.band)[i]);
            } else if (this.tag.startsWith("RF")) {
                newAttribute.addToBody(this.length, ((CPFieldRef[]) this.band)[i]);
            } else if (this.tag.startsWith("RM")) {
                newAttribute.addToBody(this.length, ((CPMethodRef[]) this.band)[i]);
            } else if (this.tag.startsWith("RI")) {
                newAttribute.addToBody(this.length, ((CPInterfaceMethodRef[]) this.band)[i]);
            } else if (this.tag.startsWith("RU")) {
                newAttribute.addToBody(this.length, ((CPUTF8[]) this.band)[i]);
            }
        }

        public String getTag() {
            return this.tag;
        }
    }

    public static class Callable implements AttributeLayoutElement {
        /* access modifiers changed from: private */
        public final List body;
        private int count;
        private int index;
        private boolean isBackwardsCallable;
        private boolean isFirstCallable;

        public Callable(List list) throws IOException {
            this.body = list;
        }

        public void addNextToAttribute(NewAttribute newAttribute) {
            for (int i = 0; i < this.body.size(); i++) {
                ((LayoutElement) this.body.get(i)).addToAttribute(this.index, newAttribute);
            }
            this.index++;
        }

        public void addCount(int i) {
            this.count += i;
        }

        public void readBands(InputStream inputStream, int i) throws IOException, Pack200Exception {
            int i2;
            if (this.isFirstCallable) {
                i2 = i + this.count;
            } else {
                i2 = this.count;
            }
            for (int i3 = 0; i3 < this.body.size(); i3++) {
                ((LayoutElement) this.body.get(i3)).readBands(inputStream, i2);
            }
        }

        public void addToAttribute(int i, NewAttribute newAttribute) {
            if (this.isFirstCallable) {
                for (int i2 = 0; i2 < this.body.size(); i2++) {
                    ((LayoutElement) this.body.get(i2)).addToAttribute(this.index, newAttribute);
                }
                this.index++;
            }
        }

        public boolean isBackwardsCallable() {
            return this.isBackwardsCallable;
        }

        public void setBackwardsCallable() {
            this.isBackwardsCallable = true;
        }

        public void setFirstCallable(boolean z) {
            this.isFirstCallable = z;
        }

        public List getBody() {
            return this.body;
        }
    }

    public class UnionCase extends LayoutElement {
        private List body;
        private final List tags;

        public UnionCase(List list) {
            super();
            this.tags = list;
        }

        public boolean hasTag(long j) {
            return this.tags.contains(Integer.valueOf((int) j));
        }

        public UnionCase(List list, List list2) throws IOException {
            super();
            this.tags = list;
            this.body = list2;
        }

        public void readBands(InputStream inputStream, int i) throws IOException, Pack200Exception {
            if (this.body != null) {
                for (int i2 = 0; i2 < this.body.size(); i2++) {
                    ((LayoutElement) this.body.get(i2)).readBands(inputStream, i);
                }
            }
        }

        public void addToAttribute(int i, NewAttribute newAttribute) {
            if (this.body != null) {
                for (int i2 = 0; i2 < this.body.size(); i2++) {
                    ((LayoutElement) this.body.get(i2)).addToAttribute(i, newAttribute);
                }
            }
        }

        public List getBody() {
            List list = this.body;
            return list == null ? Collections.EMPTY_LIST : list;
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

    public int getBackwardsCallCount() {
        return this.backwardsCallCount;
    }

    public void setBackwardsCalls(int[] iArr) throws IOException {
        parseLayout();
        int i = 0;
        for (int i2 = 0; i2 < this.attributeLayoutElements.size(); i2++) {
            AttributeLayoutElement attributeLayoutElement = (AttributeLayoutElement) this.attributeLayoutElements.get(i2);
            if (attributeLayoutElement instanceof Callable) {
                Callable callable = (Callable) attributeLayoutElement;
                if (callable.isBackwardsCallable()) {
                    callable.addCount(iArr[i]);
                    i++;
                }
            }
        }
    }
}
