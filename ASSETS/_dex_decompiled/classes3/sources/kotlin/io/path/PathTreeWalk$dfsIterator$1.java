package kotlin.io.path;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/nio/file/Path;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "kotlin.io.path.PathTreeWalk$dfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3}, l = {184, 190, 199, 205}, m = "invokeSuspend", n = {"$this$iterator", "stack", "entriesReader", "startNode", "this_$iv", "path$iv", "$this$iterator", "stack", "entriesReader", "$this$iterator", "stack", "entriesReader", "pathNode", "this_$iv", "path$iv", "$this$iterator", "stack", "entriesReader"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2"})
/* compiled from: PathTreeWalk.kt */
final class PathTreeWalk$dfsIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ PathTreeWalk this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PathTreeWalk$dfsIterator$1(PathTreeWalk pathTreeWalk, Continuation<? super PathTreeWalk$dfsIterator$1> continuation) {
        super(2, continuation);
        this.this$0 = pathTreeWalk;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PathTreeWalk$dfsIterator$1 pathTreeWalk$dfsIterator$1 = new PathTreeWalk$dfsIterator$1(this.this$0, continuation);
        pathTreeWalk$dfsIterator$1.L$0 = obj;
        return pathTreeWalk$dfsIterator$1;
    }

    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PathTreeWalk$dfsIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v26, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: kotlin.sequences.SequenceScope} */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0148, code lost:
        if (r8.yield(r11, r14) == r0) goto L_0x021f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0191, code lost:
        if (kotlin.io.path.PathTreeWalkKt.createsCycle(r15) != false) goto L_0x01e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0197, code lost:
        if (r9.getIncludeDirectories() == false) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0199, code lost:
        r14.L$0 = r8;
        r14.L$1 = r5;
        r14.L$2 = r1;
        r14.L$3 = r15;
        r14.L$4 = r9;
        r14.L$5 = r10;
        r14.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01ae, code lost:
        if (r8.yield(r10, r14) != r0) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01b2, code lost:
        r11 = r9;
        r9 = r1;
        r1 = r10;
        r10 = r5;
        r5 = r11;
        r11 = r8;
        r8 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x01e8, code lost:
        kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m$1();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01f3, code lost:
        throw kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(r10.toString());
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0225 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 0
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 0
            r7 = 1
            if (r1 == 0) goto L_0x006e
            if (r1 == r7) goto L_0x004f
            if (r1 == r5) goto L_0x003e
            if (r1 == r4) goto L_0x001f
            if (r1 != r3) goto L_0x0017
            goto L_0x003e
        L_0x0017:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x001f:
            java.lang.Object r1 = r14.L$5
            java.nio.file.Path r1 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.lang.Object) r1)
            java.lang.Object r5 = r14.L$4
            kotlin.io.path.PathTreeWalk r5 = (kotlin.io.path.PathTreeWalk) r5
            java.lang.Object r8 = r14.L$3
            kotlin.io.path.PathNode r8 = (kotlin.io.path.PathNode) r8
            java.lang.Object r9 = r14.L$2
            kotlin.io.path.DirectoryEntriesReader r9 = (kotlin.io.path.DirectoryEntriesReader) r9
            java.lang.Object r10 = r14.L$1
            kotlin.collections.ArrayDeque r10 = (kotlin.collections.ArrayDeque) r10
            java.lang.Object r11 = r14.L$0
            kotlin.sequences.SequenceScope r11 = (kotlin.sequences.SequenceScope) r11
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x01b9
        L_0x003e:
            java.lang.Object r1 = r14.L$2
            kotlin.io.path.DirectoryEntriesReader r1 = (kotlin.io.path.DirectoryEntriesReader) r1
            java.lang.Object r5 = r14.L$1
            kotlin.collections.ArrayDeque r5 = (kotlin.collections.ArrayDeque) r5
            java.lang.Object r8 = r14.L$0
            kotlin.sequences.SequenceScope r8 = (kotlin.sequences.SequenceScope) r8
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x014d
        L_0x004f:
            java.lang.Object r1 = r14.L$5
            java.nio.file.Path r1 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.lang.Object) r1)
            java.lang.Object r5 = r14.L$4
            kotlin.io.path.PathTreeWalk r5 = (kotlin.io.path.PathTreeWalk) r5
            java.lang.Object r8 = r14.L$3
            kotlin.io.path.PathNode r8 = (kotlin.io.path.PathNode) r8
            java.lang.Object r9 = r14.L$2
            kotlin.io.path.DirectoryEntriesReader r9 = (kotlin.io.path.DirectoryEntriesReader) r9
            java.lang.Object r10 = r14.L$1
            kotlin.collections.ArrayDeque r10 = (kotlin.collections.ArrayDeque) r10
            java.lang.Object r11 = r14.L$0
            kotlin.sequences.SequenceScope r11 = (kotlin.sequences.SequenceScope) r11
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00eb
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            r8 = r15
            kotlin.sequences.SequenceScope r8 = (kotlin.sequences.SequenceScope) r8
            kotlin.collections.ArrayDeque r15 = new kotlin.collections.ArrayDeque
            r15.<init>()
            kotlin.io.path.DirectoryEntriesReader r1 = new kotlin.io.path.DirectoryEntriesReader
            kotlin.io.path.PathTreeWalk r9 = r14.this$0
            boolean r9 = r9.getFollowLinks()
            r1.<init>(r9)
            kotlin.io.path.PathNode r9 = new kotlin.io.path.PathNode
            kotlin.io.path.PathTreeWalk r10 = r14.this$0
            java.nio.file.Path r10 = r10.start
            kotlin.io.path.PathTreeWalk r11 = r14.this$0
            java.nio.file.Path r11 = r11.start
            kotlin.io.path.PathTreeWalk r12 = r14.this$0
            java.nio.file.LinkOption[] r12 = r12.getLinkOptions()
            java.lang.Object r11 = kotlin.io.path.PathTreeWalkKt.keyOf(r11, r12)
            r9.<init>(r10, r11, r6)
            kotlin.io.path.PathTreeWalk r10 = r14.this$0
            java.nio.file.Path r11 = r9.getPath()
            java.nio.file.LinkOption[] r12 = r10.getLinkOptions()
            int r13 = r12.length
            java.lang.Object[] r12 = java.util.Arrays.copyOf(r12, r13)
            java.nio.file.LinkOption[] r12 = (java.nio.file.LinkOption[]) r12
            int r13 = r12.length
            java.lang.Object[] r12 = java.util.Arrays.copyOf(r12, r13)
            java.nio.file.LinkOption[] r12 = (java.nio.file.LinkOption[]) r12
            boolean r12 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r11, (java.nio.file.LinkOption[]) r12)
            if (r12 == 0) goto L_0x0125
            boolean r5 = kotlin.io.path.PathTreeWalkKt.createsCycle(r9)
            if (r5 != 0) goto L_0x0119
            boolean r5 = r10.getIncludeDirectories()
            if (r5 == 0) goto L_0x00f2
            r5 = r14
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r14.L$0 = r8
            r14.L$1 = r15
            r14.L$2 = r1
            r14.L$3 = r9
            r14.L$4 = r10
            r14.L$5 = r11
            r14.label = r7
            java.lang.Object r5 = r8.yield(r11, r5)
            if (r5 != r0) goto L_0x00e4
            goto L_0x021f
        L_0x00e4:
            r5 = r9
            r9 = r1
            r1 = r11
            r11 = r8
            r8 = r5
            r5 = r10
            r10 = r15
        L_0x00eb:
            r15 = r11
            r11 = r1
            r1 = r9
            r9 = r8
            r8 = r15
            r15 = r10
            r10 = r5
        L_0x00f2:
            java.nio.file.LinkOption[] r5 = r10.getLinkOptions()
            int r10 = r5.length
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r10)
            java.nio.file.LinkOption[] r5 = (java.nio.file.LinkOption[]) r5
            int r10 = r5.length
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r10)
            java.nio.file.LinkOption[] r5 = (java.nio.file.LinkOption[]) r5
            boolean r5 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r11, (java.nio.file.LinkOption[]) r5)
            if (r5 == 0) goto L_0x014c
            java.util.List r5 = r1.readEntries(r9)
            java.util.Iterator r5 = r5.iterator()
            r9.setContentIterator(r5)
            r15.addLast(r9)
            goto L_0x014c
        L_0x0119:
            kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m$1()
            java.lang.String r15 = r11.toString()
            java.nio.file.FileSystemLoopException r15 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.lang.String) r15)
            throw r15
        L_0x0125:
            java.nio.file.LinkOption[] r9 = new java.nio.file.LinkOption[r7]
            java.nio.file.LinkOption r10 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m()
            r9[r2] = r10
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r9, r7)
            java.nio.file.LinkOption[] r9 = (java.nio.file.LinkOption[]) r9
            boolean r9 = java.nio.file.Files.exists(r11, r9)
            if (r9 == 0) goto L_0x014c
            r9 = r14
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r14.L$0 = r8
            r14.L$1 = r15
            r14.L$2 = r1
            r14.label = r5
            java.lang.Object r5 = r8.yield(r11, r9)
            if (r5 != r0) goto L_0x014c
            goto L_0x021f
        L_0x014c:
            r5 = r15
        L_0x014d:
            r15 = r5
            java.util.Collection r15 = (java.util.Collection) r15
            boolean r15 = r15.isEmpty()
            if (r15 != 0) goto L_0x0225
            java.lang.Object r15 = r5.last()
            kotlin.io.path.PathNode r15 = (kotlin.io.path.PathNode) r15
            java.util.Iterator r15 = r15.getContentIterator()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15)
            boolean r9 = r15.hasNext()
            if (r9 == 0) goto L_0x0220
            java.lang.Object r15 = r15.next()
            kotlin.io.path.PathNode r15 = (kotlin.io.path.PathNode) r15
            kotlin.io.path.PathTreeWalk r9 = r14.this$0
            java.nio.file.Path r10 = r15.getPath()
            java.nio.file.LinkOption[] r11 = r9.getLinkOptions()
            int r12 = r11.length
            java.lang.Object[] r11 = java.util.Arrays.copyOf(r11, r12)
            java.nio.file.LinkOption[] r11 = (java.nio.file.LinkOption[]) r11
            int r12 = r11.length
            java.lang.Object[] r11 = java.util.Arrays.copyOf(r11, r12)
            java.nio.file.LinkOption[] r11 = (java.nio.file.LinkOption[]) r11
            boolean r11 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r10, (java.nio.file.LinkOption[]) r11)
            if (r11 == 0) goto L_0x01f4
            boolean r11 = kotlin.io.path.PathTreeWalkKt.createsCycle(r15)
            if (r11 != 0) goto L_0x01e8
            boolean r11 = r9.getIncludeDirectories()
            if (r11 == 0) goto L_0x01c0
            r11 = r14
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r14.L$0 = r8
            r14.L$1 = r5
            r14.L$2 = r1
            r14.L$3 = r15
            r14.L$4 = r9
            r14.L$5 = r10
            r14.label = r4
            java.lang.Object r11 = r8.yield(r10, r11)
            if (r11 != r0) goto L_0x01b2
            goto L_0x021f
        L_0x01b2:
            r11 = r9
            r9 = r1
            r1 = r10
            r10 = r5
            r5 = r11
            r11 = r8
            r8 = r15
        L_0x01b9:
            r15 = r10
            r10 = r1
            r1 = r9
            r9 = r5
            r5 = r15
            r15 = r8
            r8 = r11
        L_0x01c0:
            java.nio.file.LinkOption[] r9 = r9.getLinkOptions()
            int r11 = r9.length
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r9, r11)
            java.nio.file.LinkOption[] r9 = (java.nio.file.LinkOption[]) r9
            int r11 = r9.length
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r9, r11)
            java.nio.file.LinkOption[] r9 = (java.nio.file.LinkOption[]) r9
            boolean r9 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r10, (java.nio.file.LinkOption[]) r9)
            if (r9 == 0) goto L_0x014d
            java.util.List r9 = r1.readEntries(r15)
            java.util.Iterator r9 = r9.iterator()
            r15.setContentIterator(r9)
            r5.addLast(r15)
            goto L_0x014d
        L_0x01e8:
            kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m$1()
            java.lang.String r15 = r10.toString()
            java.nio.file.FileSystemLoopException r15 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.lang.String) r15)
            throw r15
        L_0x01f4:
            java.nio.file.LinkOption[] r15 = new java.nio.file.LinkOption[r7]
            java.nio.file.LinkOption r9 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m()
            r15[r2] = r9
            java.lang.Object[] r15 = java.util.Arrays.copyOf(r15, r7)
            java.nio.file.LinkOption[] r15 = (java.nio.file.LinkOption[]) r15
            boolean r15 = java.nio.file.Files.exists(r10, r15)
            if (r15 == 0) goto L_0x014d
            r15 = r14
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
            r14.L$0 = r8
            r14.L$1 = r5
            r14.L$2 = r1
            r14.L$3 = r6
            r14.L$4 = r6
            r14.L$5 = r6
            r14.label = r3
            java.lang.Object r15 = r8.yield(r10, r15)
            if (r15 != r0) goto L_0x014d
        L_0x021f:
            return r0
        L_0x0220:
            r5.removeLast()
            goto L_0x014d
        L_0x0225:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathTreeWalk$dfsIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
