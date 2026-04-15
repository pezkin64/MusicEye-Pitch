package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class DefaultSpecialEffectsController extends SpecialEffectsController {
    DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    /* access modifiers changed from: package-private */
    public void executeOperations(List<SpecialEffectsController.Operation> list, boolean z) {
        SpecialEffectsController.Operation operation = null;
        SpecialEffectsController.Operation operation2 = null;
        for (SpecialEffectsController.Operation next : list) {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(next.getFragment().mView);
            int i = AnonymousClass10.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[next.getFinalState().ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                if (from == SpecialEffectsController.Operation.State.VISIBLE && operation == null) {
                    operation = next;
                }
            } else if (i == 4 && from != SpecialEffectsController.Operation.State.VISIBLE) {
                operation2 = next;
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList<SpecialEffectsController.Operation> arrayList3 = new ArrayList<>(list);
        for (final SpecialEffectsController.Operation next2 : list) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            next2.markStartedSpecialEffect(cancellationSignal);
            arrayList.add(new AnimationInfo(next2, cancellationSignal, z));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            next2.markStartedSpecialEffect(cancellationSignal2);
            boolean z2 = false;
            if (z) {
                if (next2 != operation) {
                    arrayList2.add(new TransitionInfo(next2, cancellationSignal2, z, z2));
                    next2.addCompletionListener(new Runnable() {
                        public void run() {
                            if (arrayList3.contains(next2)) {
                                arrayList3.remove(next2);
                                DefaultSpecialEffectsController.this.applyContainerChanges(next2);
                            }
                        }
                    });
                }
            } else if (next2 != operation2) {
                arrayList2.add(new TransitionInfo(next2, cancellationSignal2, z, z2));
                next2.addCompletionListener(new Runnable() {
                    public void run() {
                        if (arrayList3.contains(next2)) {
                            arrayList3.remove(next2);
                            DefaultSpecialEffectsController.this.applyContainerChanges(next2);
                        }
                    }
                });
            }
            z2 = true;
            arrayList2.add(new TransitionInfo(next2, cancellationSignal2, z, z2));
            next2.addCompletionListener(new Runnable() {
                public void run() {
                    if (arrayList3.contains(next2)) {
                        arrayList3.remove(next2);
                        DefaultSpecialEffectsController.this.applyContainerChanges(next2);
                    }
                }
            });
        }
        Map<SpecialEffectsController.Operation, Boolean> startTransitions = startTransitions(arrayList2, arrayList3, z, operation, operation2);
        startAnimations(arrayList, arrayList3, startTransitions.containsValue(true), startTransitions);
        for (SpecialEffectsController.Operation applyContainerChanges : arrayList3) {
            applyContainerChanges(applyContainerChanges);
        }
        arrayList3.clear();
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10  reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                androidx.fragment.app.SpecialEffectsController$Operation$State[] r0 = androidx.fragment.app.SpecialEffectsController.Operation.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = r0
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.GONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.INVISIBLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.REMOVED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.AnonymousClass10.<clinit>():void");
        }
    }

    private void startAnimations(List<AnimationInfo> list, List<SpecialEffectsController.Operation> list2, boolean z, Map<SpecialEffectsController.Operation, Boolean> map) {
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (final AnimationInfo next : list) {
            if (next.isVisibilityUnchanged()) {
                next.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = next.getAnimation(context);
                if (animation == null) {
                    next.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(next);
                    } else {
                        final SpecialEffectsController.Operation operation = next.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Boolean.TRUE.equals(map.get(operation))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            next.completeSpecialEffect();
                        } else {
                            final boolean z3 = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            List<SpecialEffectsController.Operation> list3 = list2;
                            if (z3) {
                                list3.remove(operation);
                            }
                            final View view = fragment.mView;
                            container.startViewTransition(view);
                            animator.addListener(new AnimatorListenerAdapter() {
                                public void onAnimationEnd(Animator animator) {
                                    container.endViewTransition(view);
                                    if (z3) {
                                        operation.getFinalState().applyState(view);
                                    }
                                    next.completeSpecialEffect();
                                }
                            });
                            animator.setTarget(view);
                            animator.start();
                            next.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() {
                                public void onCancel() {
                                    animator.end();
                                }
                            });
                            z2 = true;
                        }
                    }
                }
            }
            Map<SpecialEffectsController.Operation, Boolean> map2 = map;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            final AnimationInfo animationInfo = (AnimationInfo) it.next();
            SpecialEffectsController.Operation operation2 = animationInfo.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo.completeSpecialEffect();
            } else {
                final View view2 = fragment2.mView;
                Animation animation2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo.getAnimation(context))).animation);
                if (operation2.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view2.startAnimation(animation2);
                    animationInfo.completeSpecialEffect();
                } else {
                    container.startViewTransition(view2);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view2);
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            container.post(new Runnable() {
                                public void run() {
                                    container.endViewTransition(view2);
                                    animationInfo.completeSpecialEffect();
                                }
                            });
                        }
                    });
                    view2.startAnimation(endViewTransitionAnimation);
                }
                animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() {
                    public void onCancel() {
                        view2.clearAnimation();
                        container.endViewTransition(view2);
                        animationInfo.completeSpecialEffect();
                    }
                });
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v30, resolved type: int} */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0288, code lost:
        r5 = (android.view.View) r5.get(r7.get(r4));
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<androidx.fragment.app.SpecialEffectsController.Operation, java.lang.Boolean> startTransitions(java.util.List<androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo> r29, java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r30, boolean r31, androidx.fragment.app.SpecialEffectsController.Operation r32, androidx.fragment.app.SpecialEffectsController.Operation r33) {
        /*
            r28 = this;
            r1 = r28
            r4 = r31
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            java.util.Iterator r0 = r29.iterator()
            r8 = 0
        L_0x000e:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x005b
            java.lang.Object r2 = r0.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r2 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r2
            boolean r3 = r2.isVisibilityUnchanged()
            if (r3 == 0) goto L_0x0021
            goto L_0x000e
        L_0x0021:
            androidx.fragment.app.FragmentTransitionImpl r3 = r2.getHandlingImpl()
            if (r8 != 0) goto L_0x0029
            r8 = r3
            goto L_0x000e
        L_0x0029:
            if (r3 == 0) goto L_0x000e
            if (r8 != r3) goto L_0x002e
            goto L_0x000e
        L_0x002e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Mixing framework transitions and AndroidX transitions is not allowed. Fragment "
            r3.<init>(r4)
            androidx.fragment.app.SpecialEffectsController$Operation r4 = r2.getOperation()
            androidx.fragment.app.Fragment r4 = r4.getFragment()
            r3.append(r4)
            java.lang.String r4 = " returned Transition "
            r3.append(r4)
            java.lang.Object r2 = r2.getTransition()
            r3.append(r2)
            java.lang.String r2 = " which uses a different Transition  type than other Fragments."
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r0.<init>(r2)
            throw r0
        L_0x005b:
            r9 = 0
            if (r8 != 0) goto L_0x007d
            java.util.Iterator r0 = r29.iterator()
        L_0x0062:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x04b7
            java.lang.Object r2 = r0.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r2 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r2
            androidx.fragment.app.SpecialEffectsController$Operation r3 = r2.getOperation()
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r9)
            r6.put(r3, r4)
            r2.completeSpecialEffect()
            goto L_0x0062
        L_0x007d:
            android.view.View r10 = new android.view.View
            android.view.ViewGroup r0 = r1.getContainer()
            android.content.Context r0 = r0.getContext()
            r10.<init>(r0)
            android.graphics.Rect r11 = new android.graphics.Rect
            r11.<init>()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            androidx.collection.ArrayMap r13 = new androidx.collection.ArrayMap
            r13.<init>()
            java.util.Iterator r16 = r29.iterator()
            r17 = r9
            r0 = 0
            r14 = 0
        L_0x00a6:
            boolean r2 = r16.hasNext()
            if (r2 == 0) goto L_0x02ea
            java.lang.Object r2 = r16.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r2 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r2
            boolean r5 = r2.hasSharedElementTransition()
            if (r5 == 0) goto L_0x02ce
            if (r32 == 0) goto L_0x02ce
            if (r33 == 0) goto L_0x02ce
            java.lang.Object r0 = r2.getSharedElementTransition()
            java.lang.Object r0 = r8.cloneTransition(r0)
            java.lang.Object r0 = r8.wrapTransitionInSet(r0)
            androidx.fragment.app.Fragment r2 = r33.getFragment()
            java.util.ArrayList r2 = r2.getSharedElementSourceNames()
            androidx.fragment.app.Fragment r5 = r32.getFragment()
            java.util.ArrayList r5 = r5.getSharedElementSourceNames()
            androidx.fragment.app.Fragment r18 = r32.getFragment()
            java.util.ArrayList r7 = r18.getSharedElementTargetNames()
            r19 = 1
        L_0x00e2:
            int r3 = r7.size()
            if (r9 >= r3) goto L_0x0101
            java.lang.Object r3 = r7.get(r9)
            int r3 = r2.indexOf(r3)
            r20 = r0
            r0 = -1
            if (r3 == r0) goto L_0x00fc
            java.lang.Object r0 = r5.get(r9)
            r2.set(r3, r0)
        L_0x00fc:
            int r9 = r9 + 1
            r0 = r20
            goto L_0x00e2
        L_0x0101:
            r20 = r0
            androidx.fragment.app.Fragment r0 = r33.getFragment()
            java.util.ArrayList r7 = r0.getSharedElementTargetNames()
            if (r4 != 0) goto L_0x011e
            androidx.fragment.app.Fragment r0 = r32.getFragment()
            androidx.core.app.SharedElementCallback r0 = r0.getExitTransitionCallback()
            androidx.fragment.app.Fragment r3 = r33.getFragment()
            androidx.core.app.SharedElementCallback r3 = r3.getEnterTransitionCallback()
            goto L_0x012e
        L_0x011e:
            androidx.fragment.app.Fragment r0 = r32.getFragment()
            androidx.core.app.SharedElementCallback r0 = r0.getEnterTransitionCallback()
            androidx.fragment.app.Fragment r3 = r33.getFragment()
            androidx.core.app.SharedElementCallback r3 = r3.getExitTransitionCallback()
        L_0x012e:
            int r5 = r2.size()
            r9 = 0
        L_0x0133:
            if (r9 >= r5) goto L_0x0151
            java.lang.Object r21 = r2.get(r9)
            r22 = r5
            r5 = r21
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r21 = r7.get(r9)
            r23 = r9
            r9 = r21
            java.lang.String r9 = (java.lang.String) r9
            r13.put(r5, r9)
            int r9 = r23 + 1
            r5 = r22
            goto L_0x0133
        L_0x0151:
            androidx.collection.ArrayMap r9 = new androidx.collection.ArrayMap
            r9.<init>()
            androidx.fragment.app.Fragment r5 = r32.getFragment()
            android.view.View r5 = r5.mView
            r1.findNamedViews(r9, r5)
            r9.retainAll(r2)
            if (r0 == 0) goto L_0x019f
            r0.onMapSharedElements(r2, r9)
            int r0 = r2.size()
            int r0 = r0 + -1
        L_0x016d:
            if (r0 < 0) goto L_0x01a6
            java.lang.Object r5 = r2.get(r0)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r21 = r9.get(r5)
            android.view.View r21 = (android.view.View) r21
            if (r21 != 0) goto L_0x0183
            r13.remove(r5)
            r22 = r0
            goto L_0x019c
        L_0x0183:
            r22 = r0
            java.lang.String r0 = androidx.core.view.ViewCompat.getTransitionName(r21)
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x019c
            java.lang.Object r0 = r13.remove(r5)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r5 = androidx.core.view.ViewCompat.getTransitionName(r21)
            r13.put(r5, r0)
        L_0x019c:
            int r0 = r22 + -1
            goto L_0x016d
        L_0x019f:
            java.util.Set r0 = r9.keySet()
            r13.retainAll(r0)
        L_0x01a6:
            androidx.collection.ArrayMap r5 = new androidx.collection.ArrayMap
            r5.<init>()
            androidx.fragment.app.Fragment r0 = r33.getFragment()
            android.view.View r0 = r0.mView
            r1.findNamedViews(r5, r0)
            r5.retainAll(r7)
            java.util.Collection r0 = r13.values()
            r5.retainAll(r0)
            if (r3 == 0) goto L_0x0201
            r3.onMapSharedElements(r7, r5)
            int r0 = r7.size()
            int r0 = r0 + -1
        L_0x01c9:
            if (r0 < 0) goto L_0x0204
            java.lang.Object r3 = r7.get(r0)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r21 = r5.get(r3)
            android.view.View r21 = (android.view.View) r21
            if (r21 != 0) goto L_0x01e5
            java.lang.String r3 = androidx.fragment.app.FragmentTransition.findKeyForValue(r13, r3)
            if (r3 == 0) goto L_0x01e2
            r13.remove(r3)
        L_0x01e2:
            r22 = r0
            goto L_0x01fe
        L_0x01e5:
            r22 = r0
            java.lang.String r0 = androidx.core.view.ViewCompat.getTransitionName(r21)
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x01fe
            java.lang.String r0 = androidx.fragment.app.FragmentTransition.findKeyForValue(r13, r3)
            if (r0 == 0) goto L_0x01fe
            java.lang.String r3 = androidx.core.view.ViewCompat.getTransitionName(r21)
            r13.put(r0, r3)
        L_0x01fe:
            int r0 = r22 + -1
            goto L_0x01c9
        L_0x0201:
            androidx.fragment.app.FragmentTransition.retainValues(r13, r5)
        L_0x0204:
            java.util.Set r0 = r13.keySet()
            r1.retainMatchingViews(r9, r0)
            java.util.Collection r0 = r13.values()
            r1.retainMatchingViews(r5, r0)
            boolean r0 = r13.isEmpty()
            if (r0 == 0) goto L_0x022d
            r12.clear()
            r15.clear()
            r3 = r32
            r2 = r33
            r7 = r10
            r5 = r11
            r4 = r12
            r19 = r13
            r9 = r15
            r0 = 0
            r18 = 0
            goto L_0x02de
        L_0x022d:
            androidx.fragment.app.Fragment r0 = r33.getFragment()
            androidx.fragment.app.Fragment r3 = r32.getFragment()
            r1 = r19
            androidx.fragment.app.FragmentTransition.callSharedElementStartEnd(r0, r3, r4, r9, r1)
            android.view.ViewGroup r0 = r28.getContainer()
            r3 = r0
            androidx.fragment.app.DefaultSpecialEffectsController$6 r0 = new androidx.fragment.app.DefaultSpecialEffectsController$6
            r22 = r1
            r19 = r13
            r21 = r14
            r13 = r20
            r1 = r28
            r20 = r2
            r14 = r3
            r3 = r32
            r2 = r33
            r0.<init>(r2, r3, r4, r5)
            androidx.core.view.OneShotPreDrawListener.add(r14, r0)
            java.util.Collection r0 = r9.values()
            r12.addAll(r0)
            boolean r0 = r20.isEmpty()
            if (r0 != 0) goto L_0x0278
            r0 = r20
            r4 = 0
            java.lang.Object r0 = r0.get(r4)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r9.get(r0)
            android.view.View r0 = (android.view.View) r0
            r8.setEpicenter((java.lang.Object) r13, (android.view.View) r0)
            goto L_0x027b
        L_0x0278:
            r4 = 0
            r0 = r21
        L_0x027b:
            java.util.Collection r9 = r5.values()
            r15.addAll(r9)
            boolean r9 = r7.isEmpty()
            if (r9 != 0) goto L_0x02a4
            java.lang.Object r7 = r7.get(r4)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r5 = r5.get(r7)
            android.view.View r5 = (android.view.View) r5
            if (r5 == 0) goto L_0x02a4
            android.view.ViewGroup r7 = r1.getContainer()
            androidx.fragment.app.DefaultSpecialEffectsController$7 r9 = new androidx.fragment.app.DefaultSpecialEffectsController$7
            r9.<init>(r8, r5, r11)
            androidx.core.view.OneShotPreDrawListener.add(r7, r9)
            r17 = r22
        L_0x02a4:
            r8.setSharedElementTargets(r13, r10, r12)
            r5 = r12
            r12 = 0
            r20 = r13
            r13 = 0
            r7 = r10
            r10 = 0
            r9 = r11
            r11 = 0
            r14 = r20
            r18 = r4
            r4 = r5
            r5 = r9
            r9 = r20
            r8.scheduleRemoveTargets(r9, r10, r11, r12, r13, r14, r15)
            r9 = r15
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r22)
            r6.put(r3, r10)
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r22)
            r6.put(r2, r10)
            r14 = r0
            r0 = r20
            goto L_0x02de
        L_0x02ce:
            r3 = r32
            r2 = r33
            r18 = r9
            r7 = r10
            r5 = r11
            r4 = r12
            r19 = r13
            r21 = r14
            r9 = r15
            r14 = r21
        L_0x02de:
            r12 = r4
            r11 = r5
            r10 = r7
            r15 = r9
            r9 = r18
            r13 = r19
            r4 = r31
            goto L_0x00a6
        L_0x02ea:
            r3 = r32
            r2 = r33
            r18 = r9
            r7 = r10
            r5 = r11
            r4 = r12
            r19 = r13
            r21 = r14
            r9 = r15
            r22 = 1
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Iterator r16 = r29.iterator()
            r11 = 0
            r12 = 0
        L_0x0305:
            boolean r13 = r16.hasNext()
            if (r13 == 0) goto L_0x0427
            java.lang.Object r13 = r16.next()
            r20 = r13
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r20 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r20
            boolean r13 = r20.isVisibilityUnchanged()
            if (r13 == 0) goto L_0x0328
            androidx.fragment.app.SpecialEffectsController$Operation r13 = r20.getOperation()
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r18)
            r6.put(r13, r14)
            r20.completeSpecialEffect()
            goto L_0x0305
        L_0x0328:
            java.lang.Object r13 = r20.getTransition()
            java.lang.Object r13 = r8.cloneTransition(r13)
            androidx.fragment.app.SpecialEffectsController$Operation r14 = r20.getOperation()
            if (r0 == 0) goto L_0x033d
            if (r14 == r3) goto L_0x033a
            if (r14 != r2) goto L_0x033d
        L_0x033a:
            r15 = r22
            goto L_0x033f
        L_0x033d:
            r15 = r18
        L_0x033f:
            if (r13 != 0) goto L_0x035c
            if (r15 != 0) goto L_0x034d
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r18)
            r6.put(r14, r13)
            r20.completeSpecialEffect()
        L_0x034d:
            r27 = r4
            r25 = r9
            r4 = r11
            r3 = r21
            r11 = 0
            r21 = r7
            r7 = r10
            r10 = r30
            goto L_0x0417
        L_0x035c:
            r23 = r11
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r31 = r10
            androidx.fragment.app.Fragment r10 = r14.getFragment()
            android.view.View r10 = r10.mView
            r1.captureTransitioningViews(r11, r10)
            if (r15 == 0) goto L_0x0379
            if (r14 != r3) goto L_0x0376
            r11.removeAll(r4)
            goto L_0x0379
        L_0x0376:
            r11.removeAll(r9)
        L_0x0379:
            boolean r10 = r11.isEmpty()
            if (r10 == 0) goto L_0x0394
            r8.addTarget(r13, r7)
            r10 = r30
            r27 = r4
            r25 = r9
            r2 = r12
            r9 = r13
            r12 = r14
            r3 = r21
            r4 = r23
            r21 = r7
            r7 = r31
            goto L_0x03ea
        L_0x0394:
            r8.addTargets(r13, r11)
            r10 = r14
            r14 = 0
            r15 = 0
            r24 = r12
            r12 = 0
            r25 = r9
            r9 = r13
            r13 = 0
            r26 = r10
            r10 = r9
            r27 = r4
            r3 = r21
            r4 = r23
            r2 = r24
            r21 = r7
            r7 = r31
            r8.scheduleRemoveTargets(r9, r10, r11, r12, r13, r14, r15)
            androidx.fragment.app.SpecialEffectsController$Operation$State r10 = r26.getFinalState()
            androidx.fragment.app.SpecialEffectsController$Operation$State r12 = androidx.fragment.app.SpecialEffectsController.Operation.State.GONE
            if (r10 != r12) goto L_0x03e6
            r10 = r30
            r12 = r26
            r10.remove(r12)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>(r11)
            androidx.fragment.app.Fragment r14 = r12.getFragment()
            android.view.View r14 = r14.mView
            r13.remove(r14)
            androidx.fragment.app.Fragment r14 = r12.getFragment()
            android.view.View r14 = r14.mView
            r8.scheduleHideFragmentView(r9, r14, r13)
            android.view.ViewGroup r13 = r1.getContainer()
            androidx.fragment.app.DefaultSpecialEffectsController$8 r14 = new androidx.fragment.app.DefaultSpecialEffectsController$8
            r14.<init>(r11)
            androidx.core.view.OneShotPreDrawListener.add(r13, r14)
            goto L_0x03ea
        L_0x03e6:
            r10 = r30
            r12 = r26
        L_0x03ea:
            androidx.fragment.app.SpecialEffectsController$Operation$State r13 = r12.getFinalState()
            androidx.fragment.app.SpecialEffectsController$Operation$State r14 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r13 != r14) goto L_0x03fb
            r7.addAll(r11)
            if (r17 == 0) goto L_0x03fe
            r8.setEpicenter((java.lang.Object) r9, (android.graphics.Rect) r5)
            goto L_0x03fe
        L_0x03fb:
            r8.setEpicenter((java.lang.Object) r9, (android.view.View) r3)
        L_0x03fe:
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r22)
            r6.put(r12, r11)
            boolean r11 = r20.isOverlapAllowed()
            if (r11 == 0) goto L_0x0411
            r11 = 0
            java.lang.Object r4 = r8.mergeTransitionsTogether(r4, r9, r11)
            goto L_0x0416
        L_0x0411:
            r11 = 0
            java.lang.Object r2 = r8.mergeTransitionsTogether(r2, r9, r11)
        L_0x0416:
            r12 = r2
        L_0x0417:
            r2 = r33
            r11 = r4
            r10 = r7
            r7 = r21
            r9 = r25
            r4 = r27
            r21 = r3
            r3 = r32
            goto L_0x0305
        L_0x0427:
            r27 = r4
            r25 = r9
            r7 = r10
            r4 = r11
            r2 = r12
            java.lang.Object r2 = r8.mergeTransitionsInSequence(r4, r2, r0)
            java.util.Iterator r3 = r29.iterator()
        L_0x0436:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x04ad
            java.lang.Object r4 = r3.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r4 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r4
            boolean r5 = r4.isVisibilityUnchanged()
            if (r5 == 0) goto L_0x0449
            goto L_0x0436
        L_0x0449:
            java.lang.Object r5 = r4.getTransition()
            androidx.fragment.app.SpecialEffectsController$Operation r9 = r4.getOperation()
            r10 = r32
            r11 = r33
            if (r0 == 0) goto L_0x045e
            if (r9 == r10) goto L_0x045b
            if (r9 != r11) goto L_0x045e
        L_0x045b:
            r12 = r22
            goto L_0x0460
        L_0x045e:
            r12 = r18
        L_0x0460:
            if (r5 != 0) goto L_0x0464
            if (r12 == 0) goto L_0x0436
        L_0x0464:
            android.view.ViewGroup r5 = r1.getContainer()
            boolean r5 = androidx.core.view.ViewCompat.isLaidOut(r5)
            if (r5 != 0) goto L_0x0498
            r5 = 2
            boolean r5 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r5)
            if (r5 == 0) goto L_0x0494
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r12 = "SpecialEffectsController: Container "
            r5.<init>(r12)
            android.view.ViewGroup r12 = r1.getContainer()
            r5.append(r12)
            java.lang.String r12 = " has not been laid out. Completing operation "
            r5.append(r12)
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            java.lang.String r9 = "FragmentManager"
            android.util.Log.v(r9, r5)
        L_0x0494:
            r4.completeSpecialEffect()
            goto L_0x0436
        L_0x0498:
            androidx.fragment.app.SpecialEffectsController$Operation r5 = r4.getOperation()
            androidx.fragment.app.Fragment r5 = r5.getFragment()
            androidx.core.os.CancellationSignal r9 = r4.getSignal()
            androidx.fragment.app.DefaultSpecialEffectsController$9 r12 = new androidx.fragment.app.DefaultSpecialEffectsController$9
            r12.<init>(r4)
            r8.setListenerForTransitionEnd(r5, r2, r9, r12)
            goto L_0x0436
        L_0x04ad:
            android.view.ViewGroup r3 = r1.getContainer()
            boolean r3 = androidx.core.view.ViewCompat.isLaidOut(r3)
            if (r3 != 0) goto L_0x04b8
        L_0x04b7:
            return r6
        L_0x04b8:
            r3 = 4
            androidx.fragment.app.FragmentTransition.setViewVisibility(r7, r3)
            r11 = r25
            java.util.ArrayList r12 = r8.prepareSetNameOverridesReordered(r11)
            android.view.ViewGroup r3 = r1.getContainer()
            r8.beginDelayedTransition(r3, r2)
            android.view.ViewGroup r9 = r1.getContainer()
            r13 = r19
            r10 = r27
            r8.setNameOverridesReordered(r9, r10, r11, r12, r13)
            r4 = r18
            androidx.fragment.app.FragmentTransition.setViewVisibility(r7, r4)
            r8.swapSharedElementTargets(r0, r10, r11)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.startTransitions(java.util.List, java.util.List, boolean, androidx.fragment.app.SpecialEffectsController$Operation, androidx.fragment.app.SpecialEffectsController$Operation):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    public void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName((View) it.next().getValue()))) {
                it.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!ViewGroupCompat.isTransitionGroup(viewGroup)) {
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt.getVisibility() == 0) {
                        captureTransitioningViews(arrayList, childAt);
                    }
                }
            } else if (!arrayList.contains(view)) {
                arrayList.add(viewGroup);
            }
        } else if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    /* access modifiers changed from: package-private */
    public void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        /* access modifiers changed from: package-private */
        public SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        /* access modifiers changed from: package-private */
        public CancellationSignal getSignal() {
            return this.mSignal;
        }

        /* access modifiers changed from: package-private */
        public boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            if (from != finalState) {
                return (from == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE) ? false : true;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }
    }

    private static class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim = false;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.mIsPop = z;
        }

        /* access modifiers changed from: package-private */
        public FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = loadAnimation;
            this.mLoadedAnim = true;
            return loadAnimation;
        }
    }

    private static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            Object obj;
            Object obj2;
            boolean z3;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    obj2 = operation.getFragment().getReenterTransition();
                } else {
                    obj2 = operation.getFragment().getEnterTransition();
                }
                this.mTransition = obj2;
                if (z) {
                    z3 = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    z3 = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = z3;
            } else {
                if (z) {
                    obj = operation.getFragment().getReturnTransition();
                } else {
                    obj = operation.getFragment().getExitTransition();
                }
                this.mTransition = obj;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        /* access modifiers changed from: package-private */
        public Object getTransition() {
            return this.mTransition;
        }

        /* access modifiers changed from: package-private */
        public boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        /* access modifiers changed from: package-private */
        public FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
