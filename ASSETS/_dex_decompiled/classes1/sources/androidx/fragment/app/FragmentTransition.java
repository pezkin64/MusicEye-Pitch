package androidx.fragment.app;

import android.content.Context;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition {
    private static final int[] INVERSE_OPS = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
    static final FragmentTransitionImpl PLATFORM_IMPL = new FragmentTransitionCompat21();
    static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();

    interface Callback {
        void onComplete(Fragment fragment, CancellationSignal cancellationSignal);

        void onStart(Fragment fragment, CancellationSignal cancellationSignal);
    }

    private static FragmentTransitionImpl resolveSupportImpl() {
        try {
            return (FragmentTransitionImpl) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
            return null;
        }
    }

    static void startTransitions(Context context, FragmentContainer fragmentContainer, ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z, Callback callback) {
        ViewGroup viewGroup;
        SparseArray sparseArray = new SparseArray();
        for (int i3 = i; i3 < i2; i3++) {
            BackStackRecord backStackRecord = arrayList.get(i3);
            if (arrayList2.get(i3).booleanValue()) {
                calculatePopFragments(backStackRecord, sparseArray, z);
            } else {
                calculateFragments(backStackRecord, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(context);
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int keyAt = sparseArray.keyAt(i4);
                ArrayMap<String, String> calculateNameOverrides = calculateNameOverrides(keyAt, arrayList, arrayList2, i, i2);
                FragmentContainerTransition fragmentContainerTransition = (FragmentContainerTransition) sparseArray.valueAt(i4);
                if (fragmentContainer.onHasView() && (viewGroup = (ViewGroup) fragmentContainer.onFindViewById(keyAt)) != null) {
                    if (z) {
                        configureTransitionsReordered(viewGroup, fragmentContainerTransition, view, calculateNameOverrides, callback);
                    } else {
                        configureTransitionsOrdered(viewGroup, fragmentContainerTransition, view, calculateNameOverrides, callback);
                    }
                }
            }
        }
    }

    private static ArrayMap<String, String> calculateNameOverrides(int i, ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            BackStackRecord backStackRecord = arrayList.get(i4);
            if (backStackRecord.interactsWith(i)) {
                boolean booleanValue = arrayList2.get(i4).booleanValue();
                if (backStackRecord.mSharedElementSourceNames != null) {
                    int size = backStackRecord.mSharedElementSourceNames.size();
                    if (booleanValue) {
                        arrayList3 = backStackRecord.mSharedElementSourceNames;
                        arrayList4 = backStackRecord.mSharedElementTargetNames;
                    } else {
                        ArrayList arrayList5 = backStackRecord.mSharedElementSourceNames;
                        arrayList3 = backStackRecord.mSharedElementTargetNames;
                        arrayList4 = arrayList5;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = (String) arrayList4.get(i5);
                        String str2 = (String) arrayList3.get(i5);
                        String remove = arrayMap.remove(str2);
                        if (remove != null) {
                            arrayMap.put(str, remove);
                        } else {
                            arrayMap.put(str, str2);
                        }
                    }
                }
            }
        }
        return arrayMap;
    }

    private static void configureTransitionsReordered(ViewGroup viewGroup, FragmentContainerTransition fragmentContainerTransition, View view, ArrayMap<String, String> arrayMap, Callback callback) {
        final Callback callback2 = callback;
        Fragment fragment = fragmentContainerTransition.lastIn;
        final Fragment fragment2 = fragmentContainerTransition.firstOut;
        FragmentTransitionImpl chooseImpl = chooseImpl(fragment2, fragment);
        if (chooseImpl != null) {
            boolean z = fragmentContainerTransition.lastInIsPop;
            boolean z2 = fragmentContainerTransition.firstOutIsPop;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Object enterTransition = getEnterTransition(chooseImpl, fragment, z);
            Object exitTransition = getExitTransition(chooseImpl, fragment2, z2);
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = arrayList;
            View view2 = view;
            Object configureSharedElementsReordered = configureSharedElementsReordered(chooseImpl, viewGroup, view2, arrayMap, fragmentContainerTransition, arrayList3, arrayList4, enterTransition, exitTransition);
            Object obj = enterTransition;
            Object obj2 = exitTransition;
            ArrayList arrayList5 = arrayList3;
            if (obj != null || configureSharedElementsReordered != null || obj2 != null) {
                ArrayList<View> configureEnteringExitingViews = configureEnteringExitingViews(chooseImpl, obj2, fragment2, arrayList5, view2);
                ArrayList<View> configureEnteringExitingViews2 = configureEnteringExitingViews(chooseImpl, obj, fragment, arrayList4, view2);
                setViewVisibility(configureEnteringExitingViews2, 4);
                Object obj3 = obj2;
                Object obj4 = configureSharedElementsReordered;
                Object mergeTransitions = mergeTransitions(chooseImpl, obj, obj3, obj4, fragment, z);
                if (!(fragment2 == null || configureEnteringExitingViews == null || (configureEnteringExitingViews.size() <= 0 && arrayList5.size() <= 0))) {
                    final CancellationSignal cancellationSignal = new CancellationSignal();
                    callback2.onStart(fragment2, cancellationSignal);
                    chooseImpl.setListenerForTransitionEnd(fragment2, mergeTransitions, cancellationSignal, new Runnable() {
                        public void run() {
                            Callback.this.onComplete(fragment2, cancellationSignal);
                        }
                    });
                }
                if (mergeTransitions != null) {
                    replaceHide(chooseImpl, obj3, fragment2, configureEnteringExitingViews);
                    ArrayList<String> prepareSetNameOverridesReordered = chooseImpl.prepareSetNameOverridesReordered(arrayList4);
                    Object obj5 = obj3;
                    Object obj6 = obj;
                    Object obj7 = mergeTransitions;
                    Object obj8 = obj5;
                    ArrayList<View> arrayList6 = configureEnteringExitingViews;
                    ArrayList arrayList7 = arrayList4;
                    Object obj9 = obj4;
                    ArrayList<View> arrayList8 = configureEnteringExitingViews2;
                    chooseImpl.scheduleRemoveTargets(obj7, obj6, arrayList8, obj8, arrayList6, obj9, arrayList7);
                    ArrayList arrayList9 = arrayList7;
                    Object obj10 = obj9;
                    ArrayList arrayList10 = arrayList9;
                    ArrayList<View> arrayList11 = arrayList8;
                    chooseImpl.beginDelayedTransition(viewGroup, obj7);
                    ArrayList arrayList12 = arrayList5;
                    chooseImpl.setNameOverridesReordered(viewGroup, arrayList12, arrayList10, prepareSetNameOverridesReordered, arrayMap);
                    setViewVisibility(arrayList11, 0);
                    chooseImpl.swapSharedElementTargets(obj10, arrayList12, arrayList10);
                }
            }
        }
    }

    private static void replaceHide(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, final ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            fragmentTransitionImpl.scheduleHideFragmentView(obj, fragment.getView(), arrayList);
            OneShotPreDrawListener.add(fragment.mContainer, new Runnable() {
                public void run() {
                    FragmentTransition.setViewVisibility(arrayList, 4);
                }
            });
        }
    }

    private static void configureTransitionsOrdered(ViewGroup viewGroup, FragmentContainerTransition fragmentContainerTransition, View view, ArrayMap<String, String> arrayMap, Callback callback) {
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        final Callback callback2 = callback;
        Fragment fragment = fragmentContainerTransition2.lastIn;
        final Fragment fragment2 = fragmentContainerTransition2.firstOut;
        FragmentTransitionImpl chooseImpl = chooseImpl(fragment2, fragment);
        if (chooseImpl != null) {
            boolean z = fragmentContainerTransition2.lastInIsPop;
            boolean z2 = fragmentContainerTransition2.firstOutIsPop;
            Object enterTransition = getEnterTransition(chooseImpl, fragment, z);
            Object exitTransition = getExitTransition(chooseImpl, fragment2, z2);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            View view2 = view;
            ArrayMap<String, String> arrayMap2 = arrayMap;
            Object obj = enterTransition;
            Object configureSharedElementsOrdered = configureSharedElementsOrdered(chooseImpl, viewGroup, view2, arrayMap2, fragmentContainerTransition2, arrayList, arrayList2, obj, exitTransition);
            View view3 = view2;
            ArrayMap<String, String> arrayMap3 = arrayMap2;
            Object obj2 = obj;
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = arrayList;
            if (obj2 != null || configureSharedElementsOrdered != null || exitTransition != null) {
                ArrayList<View> configureEnteringExitingViews = configureEnteringExitingViews(chooseImpl, exitTransition, fragment2, arrayList4, view3);
                if (configureEnteringExitingViews == null || configureEnteringExitingViews.isEmpty()) {
                    exitTransition = null;
                }
                Object obj3 = exitTransition;
                chooseImpl.addTarget(obj2, view3);
                boolean z3 = fragmentContainerTransition2.lastInIsPop;
                Fragment fragment3 = fragment;
                Object obj4 = configureSharedElementsOrdered;
                Object mergeTransitions = mergeTransitions(chooseImpl, obj2, obj3, obj4, fragment3, z3);
                Fragment fragment4 = fragment3;
                if (!(fragment2 == null || configureEnteringExitingViews == null || (configureEnteringExitingViews.size() <= 0 && arrayList4.size() <= 0))) {
                    final CancellationSignal cancellationSignal = new CancellationSignal();
                    callback2.onStart(fragment2, cancellationSignal);
                    chooseImpl.setListenerForTransitionEnd(fragment2, mergeTransitions, cancellationSignal, new Runnable() {
                        public void run() {
                            Callback.this.onComplete(fragment2, cancellationSignal);
                        }
                    });
                }
                if (mergeTransitions != null) {
                    Object obj5 = obj4;
                    ArrayList arrayList5 = new ArrayList();
                    Object obj6 = obj3;
                    Object obj7 = obj2;
                    Object obj8 = mergeTransitions;
                    ArrayList<View> arrayList6 = configureEnteringExitingViews;
                    chooseImpl.scheduleRemoveTargets(obj8, obj7, arrayList5, obj6, arrayList6, obj5, arrayList3);
                    ArrayList<View> arrayList7 = arrayList6;
                    Object obj9 = obj7;
                    Fragment fragment5 = fragment4;
                    ArrayList<View> arrayList8 = arrayList7;
                    ArrayList arrayList9 = arrayList3;
                    Object obj10 = obj6;
                    ArrayList arrayList10 = arrayList9;
                    Object obj11 = obj8;
                    ViewGroup viewGroup2 = viewGroup;
                    scheduleTargetChange(chooseImpl, viewGroup2, fragment5, view3, arrayList10, obj9, arrayList5, obj10, arrayList8);
                    ArrayList arrayList11 = arrayList10;
                    chooseImpl.setNameOverridesOrdered(viewGroup2, arrayList11, arrayMap3);
                    chooseImpl.beginDelayedTransition(viewGroup2, obj11);
                    chooseImpl.scheduleNameReset(viewGroup2, arrayList11, arrayMap3);
                }
            }
        }
    }

    private static void scheduleTargetChange(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        final FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
        final Fragment fragment2 = fragment;
        final View view2 = view;
        final ArrayList<View> arrayList4 = arrayList;
        final Object obj3 = obj;
        final ArrayList<View> arrayList5 = arrayList2;
        final Object obj4 = obj2;
        final ArrayList<View> arrayList6 = arrayList3;
        OneShotPreDrawListener.add(viewGroup, new Runnable() {
            public void run() {
                Object obj = obj3;
                if (obj != null) {
                    fragmentTransitionImpl2.removeTarget(obj, view2);
                    arrayList5.addAll(FragmentTransition.configureEnteringExitingViews(fragmentTransitionImpl2, obj3, fragment2, arrayList4, view2));
                }
                if (arrayList6 != null) {
                    if (obj4 != null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(view2);
                        fragmentTransitionImpl2.replaceTargets(obj4, arrayList6, arrayList);
                    }
                    arrayList6.clear();
                    arrayList6.add(view2);
                }
            }
        });
    }

    private static FragmentTransitionImpl chooseImpl(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl = PLATFORM_IMPL;
        if (fragmentTransitionImpl != null && canHandleAll(fragmentTransitionImpl, arrayList)) {
            return fragmentTransitionImpl;
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = SUPPORT_IMPL;
        if (fragmentTransitionImpl2 != null && canHandleAll(fragmentTransitionImpl2, arrayList)) {
            return fragmentTransitionImpl2;
        }
        if (fragmentTransitionImpl == null && fragmentTransitionImpl2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    private static boolean canHandleAll(FragmentTransitionImpl fragmentTransitionImpl, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!fragmentTransitionImpl.canHandle(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static Object getSharedElementTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, Fragment fragment2, boolean z) {
        Object obj;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        if (z) {
            obj = fragment2.getSharedElementReturnTransition();
        } else {
            obj = fragment.getSharedElementEnterTransition();
        }
        return fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(obj));
    }

    private static Object getEnterTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object obj;
        if (fragment == null) {
            return null;
        }
        if (z) {
            obj = fragment.getReenterTransition();
        } else {
            obj = fragment.getEnterTransition();
        }
        return fragmentTransitionImpl.cloneTransition(obj);
    }

    private static Object getExitTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object obj;
        if (fragment == null) {
            return null;
        }
        if (z) {
            obj = fragment.getReturnTransition();
        } else {
            obj = fragment.getExitTransition();
        }
        return fragmentTransitionImpl.cloneTransition(obj);
    }

    private static Object configureSharedElementsReordered(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, FragmentContainerTransition fragmentContainerTransition, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        final Rect rect;
        View view2 = view;
        ArrayMap<String, String> arrayMap2 = arrayMap;
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        ArrayList<View> arrayList3 = arrayList;
        ArrayList<View> arrayList4 = arrayList2;
        Object obj4 = obj;
        Fragment fragment = fragmentContainerTransition2.lastIn;
        Fragment fragment2 = fragmentContainerTransition2.firstOut;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        final View view3 = null;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = fragmentContainerTransition2.lastInIsPop;
        if (arrayMap2.isEmpty()) {
            obj3 = null;
        } else {
            obj3 = getSharedElementTransition(fragmentTransitionImpl, fragment, fragment2, z);
        }
        ArrayMap<String, View> captureOutSharedElements = captureOutSharedElements(fragmentTransitionImpl, arrayMap2, obj3, fragmentContainerTransition2);
        ArrayMap<String, View> captureInSharedElements = captureInSharedElements(fragmentTransitionImpl, arrayMap2, obj3, fragmentContainerTransition2);
        if (arrayMap2.isEmpty()) {
            if (captureOutSharedElements != null) {
                captureOutSharedElements.clear();
            }
            if (captureInSharedElements != null) {
                captureInSharedElements.clear();
            }
            obj3 = null;
        } else {
            addSharedElementsWithMatchingNames(arrayList3, captureOutSharedElements, arrayMap2.keySet());
            addSharedElementsWithMatchingNames(arrayList4, captureInSharedElements, arrayMap2.values());
        }
        if (obj4 == null && obj2 == null && obj3 == null) {
            return null;
        }
        callSharedElementStartEnd(fragment, fragment2, z, captureOutSharedElements, true);
        if (obj3 != null) {
            arrayList4.add(view2);
            fragmentTransitionImpl.setSharedElementTargets(obj3, view2, arrayList3);
            setOutEpicenter(fragmentTransitionImpl, obj3, obj2, captureOutSharedElements, fragmentContainerTransition2.firstOutIsPop, fragmentContainerTransition2.firstOutTransaction);
            Rect rect2 = new Rect();
            View inEpicenterView = getInEpicenterView(captureInSharedElements, fragmentContainerTransition2, obj4, z);
            if (inEpicenterView != null) {
                fragmentTransitionImpl.setEpicenter(obj4, rect2);
            }
            rect = rect2;
            view3 = inEpicenterView;
        } else {
            rect = null;
        }
        final FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
        final Fragment fragment3 = fragment;
        final Fragment fragment4 = fragment2;
        final boolean z2 = z;
        final ArrayMap<String, View> arrayMap3 = captureInSharedElements;
        OneShotPreDrawListener.add(viewGroup, new Runnable() {
            public void run() {
                FragmentTransition.callSharedElementStartEnd(Fragment.this, fragment4, z2, arrayMap3, false);
                View view = view3;
                if (view != null) {
                    fragmentTransitionImpl2.getBoundsOnScreen(view, rect);
                }
            }
        });
        return obj3;
    }

    private static void addSharedElementsWithMatchingNames(ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, Collection<String> collection) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View valueAt = arrayMap.valueAt(size);
            if (collection.contains(ViewCompat.getTransitionName(valueAt))) {
                arrayList.add(valueAt);
            }
        }
    }

    private static Object configureSharedElementsOrdered(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, FragmentContainerTransition fragmentContainerTransition, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        final Object obj4;
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        final ArrayList<View> arrayList3 = arrayList;
        final Object obj5 = obj;
        final Fragment fragment = fragmentContainerTransition2.lastIn;
        final Fragment fragment2 = fragmentContainerTransition2.firstOut;
        Rect rect = null;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        final boolean z = fragmentContainerTransition2.lastInIsPop;
        if (arrayMap.isEmpty()) {
            obj3 = null;
        } else {
            obj3 = getSharedElementTransition(fragmentTransitionImpl, fragment, fragment2, z);
        }
        ArrayMap<String, String> arrayMap2 = arrayMap;
        ArrayMap<String, View> captureOutSharedElements = captureOutSharedElements(fragmentTransitionImpl, arrayMap2, obj3, fragmentContainerTransition2);
        if (arrayMap2.isEmpty()) {
            obj3 = null;
        } else {
            arrayList3.addAll(captureOutSharedElements.values());
        }
        if (obj5 == null && obj2 == null && obj3 == null) {
            return null;
        }
        callSharedElementStartEnd(fragment, fragment2, z, captureOutSharedElements, true);
        if (obj3 != null) {
            Rect rect2 = new Rect();
            view2 = view;
            fragmentTransitionImpl.setSharedElementTargets(obj3, view2, arrayList3);
            Object obj6 = obj3;
            setOutEpicenter(fragmentTransitionImpl, obj6, obj2, captureOutSharedElements, fragmentContainerTransition2.firstOutIsPop, fragmentContainerTransition2.firstOutTransaction);
            obj4 = obj6;
            if (obj5 != null) {
                fragmentTransitionImpl.setEpicenter(obj5, rect2);
            }
            rect = rect2;
        } else {
            view2 = view;
            obj4 = obj3;
        }
        final ArrayList<View> arrayList4 = arrayList2;
        final FragmentContainerTransition fragmentContainerTransition3 = fragmentContainerTransition2;
        final ArrayMap<String, String> arrayMap3 = arrayMap2;
        final View view3 = view2;
        final Rect rect3 = rect;
        final FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
        OneShotPreDrawListener.add(viewGroup, new Runnable() {
            public void run() {
                ArrayMap<String, View> captureInSharedElements = FragmentTransition.captureInSharedElements(FragmentTransitionImpl.this, arrayMap3, obj4, fragmentContainerTransition3);
                if (captureInSharedElements != null) {
                    arrayList4.addAll(captureInSharedElements.values());
                    arrayList4.add(view3);
                }
                FragmentTransition.callSharedElementStartEnd(fragment, fragment2, z, captureInSharedElements, false);
                Object obj = obj4;
                if (obj != null) {
                    FragmentTransitionImpl.this.swapSharedElementTargets(obj, arrayList3, arrayList4);
                    View inEpicenterView = FragmentTransition.getInEpicenterView(captureInSharedElements, fragmentContainerTransition3, obj5, z);
                    if (inEpicenterView != null) {
                        FragmentTransitionImpl.this.getBoundsOnScreen(inEpicenterView, rect3);
                    }
                }
            }
        });
        return obj4;
    }

    private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        SharedElementCallback sharedElementCallback;
        ArrayList arrayList;
        if (arrayMap.isEmpty() || obj == null) {
            arrayMap.clear();
            return null;
        }
        Fragment fragment = fragmentContainerTransition.firstOut;
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.findNamedViews(arrayMap2, fragment.requireView());
        BackStackRecord backStackRecord = fragmentContainerTransition.firstOutTransaction;
        if (fragmentContainerTransition.firstOutIsPop) {
            sharedElementCallback = fragment.getEnterTransitionCallback();
            arrayList = backStackRecord.mSharedElementTargetNames;
        } else {
            sharedElementCallback = fragment.getExitTransitionCallback();
            arrayList = backStackRecord.mSharedElementSourceNames;
        }
        if (arrayList != null) {
            arrayMap2.retainAll(arrayList);
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(arrayList, arrayMap2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = (String) arrayList.get(size);
                View view = arrayMap2.get(str);
                if (view == null) {
                    arrayMap.remove(str);
                } else if (!str.equals(ViewCompat.getTransitionName(view))) {
                    arrayMap.put(ViewCompat.getTransitionName(view), arrayMap.remove(str));
                }
            }
            return arrayMap2;
        }
        arrayMap.retainAll(arrayMap2.keySet());
        return arrayMap2;
    }

    static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        SharedElementCallback sharedElementCallback;
        ArrayList arrayList;
        String findKeyForValue;
        Fragment fragment = fragmentContainerTransition.lastIn;
        View view = fragment.getView();
        if (arrayMap.isEmpty() || obj == null || view == null) {
            arrayMap.clear();
            return null;
        }
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.findNamedViews(arrayMap2, view);
        BackStackRecord backStackRecord = fragmentContainerTransition.lastInTransaction;
        if (fragmentContainerTransition.lastInIsPop) {
            sharedElementCallback = fragment.getExitTransitionCallback();
            arrayList = backStackRecord.mSharedElementSourceNames;
        } else {
            sharedElementCallback = fragment.getEnterTransitionCallback();
            arrayList = backStackRecord.mSharedElementTargetNames;
        }
        if (arrayList != null) {
            arrayMap2.retainAll(arrayList);
            arrayMap2.retainAll(arrayMap.values());
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(arrayList, arrayMap2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = (String) arrayList.get(size);
                View view2 = arrayMap2.get(str);
                if (view2 == null) {
                    String findKeyForValue2 = findKeyForValue(arrayMap, str);
                    if (findKeyForValue2 != null) {
                        arrayMap.remove(findKeyForValue2);
                    }
                } else if (!str.equals(ViewCompat.getTransitionName(view2)) && (findKeyForValue = findKeyForValue(arrayMap, str)) != null) {
                    arrayMap.put(findKeyForValue, ViewCompat.getTransitionName(view2));
                }
            }
            return arrayMap2;
        }
        retainValues(arrayMap, arrayMap2);
        return arrayMap2;
    }

    static String findKeyForValue(ArrayMap<String, String> arrayMap, String str) {
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(arrayMap.valueAt(i))) {
                return arrayMap.keyAt(i);
            }
        }
        return null;
    }

    static View getInEpicenterView(ArrayMap<String, View> arrayMap, FragmentContainerTransition fragmentContainerTransition, Object obj, boolean z) {
        String str;
        BackStackRecord backStackRecord = fragmentContainerTransition.lastInTransaction;
        if (obj == null || arrayMap == null || backStackRecord.mSharedElementSourceNames == null || backStackRecord.mSharedElementSourceNames.isEmpty()) {
            return null;
        }
        if (z) {
            str = (String) backStackRecord.mSharedElementSourceNames.get(0);
        } else {
            str = (String) backStackRecord.mSharedElementTargetNames.get(0);
        }
        return arrayMap.get(str);
    }

    private static void setOutEpicenter(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, ArrayMap<String, View> arrayMap, boolean z, BackStackRecord backStackRecord) {
        String str;
        if (backStackRecord.mSharedElementSourceNames != null && !backStackRecord.mSharedElementSourceNames.isEmpty()) {
            if (z) {
                str = (String) backStackRecord.mSharedElementTargetNames.get(0);
            } else {
                str = (String) backStackRecord.mSharedElementSourceNames.get(0);
            }
            View view = arrayMap.get(str);
            fragmentTransitionImpl.setEpicenter(obj, view);
            if (obj2 != null) {
                fragmentTransitionImpl.setEpicenter(obj2, view);
            }
        }
    }

    static void retainValues(ArrayMap<String, String> arrayMap, ArrayMap<String, View> arrayMap2) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            if (!arrayMap2.containsKey(arrayMap.valueAt(size))) {
                arrayMap.removeAt(size);
            }
        }
    }

    static void callSharedElementStartEnd(Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap, boolean z2) {
        SharedElementCallback sharedElementCallback;
        int i;
        if (z) {
            sharedElementCallback = fragment2.getEnterTransitionCallback();
        } else {
            sharedElementCallback = fragment.getEnterTransitionCallback();
        }
        if (sharedElementCallback != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (arrayMap == null) {
                i = 0;
            } else {
                i = arrayMap.size();
            }
            for (int i2 = 0; i2 < i; i2++) {
                arrayList2.add(arrayMap.keyAt(i2));
                arrayList.add(arrayMap.valueAt(i2));
            }
            if (z2) {
                sharedElementCallback.onSharedElementStart(arrayList2, arrayList, (List<View>) null);
            } else {
                sharedElementCallback.onSharedElementEnd(arrayList2, arrayList, (List<View>) null);
            }
        }
    }

    static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            fragmentTransitionImpl.captureTransitioningViews(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (!arrayList2.isEmpty()) {
            arrayList2.add(view);
            fragmentTransitionImpl.addTargets(obj, arrayList2);
        }
        return arrayList2;
    }

    static void setViewVisibility(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).setVisibility(i);
            }
        }
    }

    private static Object mergeTransitions(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean z2;
        if (obj == null || obj2 == null || fragment == null) {
            z2 = true;
        } else {
            z2 = z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap();
        }
        if (z2) {
            return fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj, obj3);
        }
        return fragmentTransitionImpl.mergeTransitionsInSequence(obj2, obj, obj3);
    }

    public static void calculateFragments(BackStackRecord backStackRecord, SparseArray<FragmentContainerTransition> sparseArray, boolean z) {
        int size = backStackRecord.mOps.size();
        for (int i = 0; i < size; i++) {
            addToFirstInLastOut(backStackRecord, (FragmentTransaction.Op) backStackRecord.mOps.get(i), sparseArray, false, z);
        }
    }

    public static void calculatePopFragments(BackStackRecord backStackRecord, SparseArray<FragmentContainerTransition> sparseArray, boolean z) {
        if (backStackRecord.mManager.getContainer().onHasView()) {
            for (int size = backStackRecord.mOps.size() - 1; size >= 0; size--) {
                addToFirstInLastOut(backStackRecord, (FragmentTransaction.Op) backStackRecord.mOps.get(size), sparseArray, true, z);
            }
        }
    }

    static boolean supportsTransition() {
        return (PLATFORM_IMPL == null && SUPPORT_IMPL == null) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003b, code lost:
        if (r0.mAdded != false) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0073, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x008f, code lost:
        if (r0.mHidden == false) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0091, code lost:
        r9 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00de A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:89:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void addToFirstInLastOut(androidx.fragment.app.BackStackRecord r8, androidx.fragment.app.FragmentTransaction.Op r9, android.util.SparseArray<androidx.fragment.app.FragmentTransition.FragmentContainerTransition> r10, boolean r11, boolean r12) {
        /*
            androidx.fragment.app.Fragment r0 = r9.mFragment
            if (r0 != 0) goto L_0x0006
            goto L_0x00e8
        L_0x0006:
            int r1 = r0.mContainerId
            if (r1 != 0) goto L_0x000c
            goto L_0x00e8
        L_0x000c:
            if (r11 == 0) goto L_0x0015
            int[] r2 = INVERSE_OPS
            int r9 = r9.mCmd
            r9 = r2[r9]
            goto L_0x0017
        L_0x0015:
            int r9 = r9.mCmd
        L_0x0017:
            r2 = 0
            r3 = 1
            if (r9 == r3) goto L_0x0084
            r4 = 3
            if (r9 == r4) goto L_0x005a
            r4 = 4
            if (r9 == r4) goto L_0x0042
            r4 = 5
            if (r9 == r4) goto L_0x002f
            r4 = 6
            if (r9 == r4) goto L_0x005a
            r4 = 7
            if (r9 == r4) goto L_0x0084
            r9 = r2
            r3 = r9
            r4 = r3
            goto L_0x0097
        L_0x002f:
            if (r12 == 0) goto L_0x003e
            boolean r9 = r0.mHiddenChanged
            if (r9 == 0) goto L_0x0093
            boolean r9 = r0.mHidden
            if (r9 != 0) goto L_0x0093
            boolean r9 = r0.mAdded
            if (r9 == 0) goto L_0x0093
            goto L_0x0091
        L_0x003e:
            boolean r9 = r0.mHidden
            goto L_0x0094
        L_0x0042:
            if (r12 == 0) goto L_0x0051
            boolean r9 = r0.mHiddenChanged
            if (r9 == 0) goto L_0x0075
            boolean r9 = r0.mAdded
            if (r9 == 0) goto L_0x0075
            boolean r9 = r0.mHidden
            if (r9 == 0) goto L_0x0075
        L_0x0050:
            goto L_0x0073
        L_0x0051:
            boolean r9 = r0.mAdded
            if (r9 == 0) goto L_0x0075
            boolean r9 = r0.mHidden
            if (r9 != 0) goto L_0x0075
            goto L_0x0050
        L_0x005a:
            if (r12 == 0) goto L_0x0077
            boolean r9 = r0.mAdded
            if (r9 != 0) goto L_0x0075
            android.view.View r9 = r0.mView
            if (r9 == 0) goto L_0x0075
            android.view.View r9 = r0.mView
            int r9 = r9.getVisibility()
            if (r9 != 0) goto L_0x0075
            float r9 = r0.mPostponedAlpha
            r4 = 0
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r9 < 0) goto L_0x0075
        L_0x0073:
            r9 = r3
            goto L_0x0080
        L_0x0075:
            r9 = r2
            goto L_0x0080
        L_0x0077:
            boolean r9 = r0.mAdded
            if (r9 == 0) goto L_0x0075
            boolean r9 = r0.mHidden
            if (r9 != 0) goto L_0x0075
            goto L_0x0073
        L_0x0080:
            r4 = r9
            r9 = r3
            r3 = r2
            goto L_0x0097
        L_0x0084:
            if (r12 == 0) goto L_0x0089
            boolean r9 = r0.mIsNewlyAdded
            goto L_0x0094
        L_0x0089:
            boolean r9 = r0.mAdded
            if (r9 != 0) goto L_0x0093
            boolean r9 = r0.mHidden
            if (r9 != 0) goto L_0x0093
        L_0x0091:
            r9 = r3
            goto L_0x0094
        L_0x0093:
            r9 = r2
        L_0x0094:
            r4 = r2
            r2 = r9
            r9 = r4
        L_0x0097:
            java.lang.Object r5 = r10.get(r1)
            androidx.fragment.app.FragmentTransition$FragmentContainerTransition r5 = (androidx.fragment.app.FragmentTransition.FragmentContainerTransition) r5
            if (r2 == 0) goto L_0x00a9
            androidx.fragment.app.FragmentTransition$FragmentContainerTransition r5 = ensureContainer(r5, r10, r1)
            r5.lastIn = r0
            r5.lastInIsPop = r11
            r5.lastInTransaction = r8
        L_0x00a9:
            r2 = 0
            if (r12 != 0) goto L_0x00ca
            if (r3 == 0) goto L_0x00ca
            if (r5 == 0) goto L_0x00b6
            androidx.fragment.app.Fragment r3 = r5.firstOut
            if (r3 != r0) goto L_0x00b6
            r5.firstOut = r2
        L_0x00b6:
            boolean r3 = r8.mReorderingAllowed
            if (r3 != 0) goto L_0x00ca
            androidx.fragment.app.FragmentManager r3 = r8.mManager
            androidx.fragment.app.FragmentStateManager r6 = r3.createOrGetFragmentStateManager(r0)
            androidx.fragment.app.FragmentStore r7 = r3.getFragmentStore()
            r7.makeActive(r6)
            r3.moveToState(r0)
        L_0x00ca:
            if (r4 == 0) goto L_0x00dc
            if (r5 == 0) goto L_0x00d2
            androidx.fragment.app.Fragment r3 = r5.firstOut
            if (r3 != 0) goto L_0x00dc
        L_0x00d2:
            androidx.fragment.app.FragmentTransition$FragmentContainerTransition r5 = ensureContainer(r5, r10, r1)
            r5.firstOut = r0
            r5.firstOutIsPop = r11
            r5.firstOutTransaction = r8
        L_0x00dc:
            if (r12 != 0) goto L_0x00e8
            if (r9 == 0) goto L_0x00e8
            if (r5 == 0) goto L_0x00e8
            androidx.fragment.app.Fragment r8 = r5.lastIn
            if (r8 != r0) goto L_0x00e8
            r5.lastIn = r2
        L_0x00e8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentTransition.addToFirstInLastOut(androidx.fragment.app.BackStackRecord, androidx.fragment.app.FragmentTransaction$Op, android.util.SparseArray, boolean, boolean):void");
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition fragmentContainerTransition, SparseArray<FragmentContainerTransition> sparseArray, int i) {
        if (fragmentContainerTransition != null) {
            return fragmentContainerTransition;
        }
        FragmentContainerTransition fragmentContainerTransition2 = new FragmentContainerTransition();
        sparseArray.put(i, fragmentContainerTransition2);
        return fragmentContainerTransition2;
    }

    static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition() {
        }
    }

    private FragmentTransition() {
    }
}
