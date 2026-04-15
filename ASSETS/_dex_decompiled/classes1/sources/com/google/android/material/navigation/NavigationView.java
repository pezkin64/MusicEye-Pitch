package com.google.android.material.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.R;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.WindowUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;

public class NavigationView extends ScrimInsetsFrameLayout {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int DEF_STYLE_RES = R.style.Widget_Design_NavigationView;
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    private boolean bottomInsetScrimEnabled;
    private int drawerLayoutCornerSize;
    private int layoutGravity;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    /* access modifiers changed from: private */
    public final NavigationMenuPresenter presenter;
    private final RectF shapeClipBounds;
    private Path shapeClipPath;
    /* access modifiers changed from: private */
    public final int[] tmpLocation;
    private boolean topInsetScrimEnabled;

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem menuItem);
    }

    public NavigationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.navigationViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NavigationView(android.content.Context r17, android.util.AttributeSet r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r2 = r18
            r4 = r19
            int r5 = DEF_STYLE_RES
            r1 = r17
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r1, r2, r4, r5)
            r0.<init>(r1, r2, r4)
            com.google.android.material.internal.NavigationMenuPresenter r7 = new com.google.android.material.internal.NavigationMenuPresenter
            r7.<init>()
            r0.presenter = r7
            r1 = 2
            int[] r1 = new int[r1]
            r0.tmpLocation = r1
            r8 = 1
            r0.topInsetScrimEnabled = r8
            r0.bottomInsetScrimEnabled = r8
            r9 = 0
            r0.layoutGravity = r9
            r0.drawerLayoutCornerSize = r9
            android.graphics.RectF r1 = new android.graphics.RectF
            r1.<init>()
            r0.shapeClipBounds = r1
            android.content.Context r1 = r0.getContext()
            com.google.android.material.internal.NavigationMenu r10 = new com.google.android.material.internal.NavigationMenu
            r10.<init>(r1)
            r0.menu = r10
            int[] r3 = com.google.android.material.R.styleable.NavigationView
            int[] r6 = new int[r9]
            androidx.appcompat.widget.TintTypedArray r3 = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(r1, r2, r3, r4, r5, r6)
            int r6 = com.google.android.material.R.styleable.NavigationView_android_background
            boolean r6 = r3.hasValue(r6)
            if (r6 == 0) goto L_0x0052
            int r6 = com.google.android.material.R.styleable.NavigationView_android_background
            android.graphics.drawable.Drawable r6 = r3.getDrawable(r6)
            androidx.core.view.ViewCompat.setBackground(r0, r6)
        L_0x0052:
            int r6 = com.google.android.material.R.styleable.NavigationView_drawerLayoutCornerSize
            int r6 = r3.getDimensionPixelSize(r6, r9)
            r0.drawerLayoutCornerSize = r6
            int r6 = com.google.android.material.R.styleable.NavigationView_android_layout_gravity
            int r6 = r3.getInt(r6, r9)
            r0.layoutGravity = r6
            android.graphics.drawable.Drawable r6 = r0.getBackground()
            if (r6 == 0) goto L_0x0070
            android.graphics.drawable.Drawable r6 = r0.getBackground()
            boolean r6 = r6 instanceof android.graphics.drawable.ColorDrawable
            if (r6 == 0) goto L_0x0098
        L_0x0070:
            com.google.android.material.shape.ShapeAppearanceModel$Builder r2 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r1, (android.util.AttributeSet) r2, (int) r4, (int) r5)
            com.google.android.material.shape.ShapeAppearanceModel r2 = r2.build()
            android.graphics.drawable.Drawable r4 = r0.getBackground()
            com.google.android.material.shape.MaterialShapeDrawable r5 = new com.google.android.material.shape.MaterialShapeDrawable
            r5.<init>((com.google.android.material.shape.ShapeAppearanceModel) r2)
            boolean r2 = r4 instanceof android.graphics.drawable.ColorDrawable
            if (r2 == 0) goto L_0x0092
            android.graphics.drawable.ColorDrawable r4 = (android.graphics.drawable.ColorDrawable) r4
            int r2 = r4.getColor()
            android.content.res.ColorStateList r2 = android.content.res.ColorStateList.valueOf(r2)
            r5.setFillColor(r2)
        L_0x0092:
            r5.initializeElevationOverlay(r1)
            androidx.core.view.ViewCompat.setBackground(r0, r5)
        L_0x0098:
            int r2 = com.google.android.material.R.styleable.NavigationView_elevation
            boolean r2 = r3.hasValue(r2)
            if (r2 == 0) goto L_0x00aa
            int r2 = com.google.android.material.R.styleable.NavigationView_elevation
            int r2 = r3.getDimensionPixelSize(r2, r9)
            float r2 = (float) r2
            r0.setElevation(r2)
        L_0x00aa:
            int r2 = com.google.android.material.R.styleable.NavigationView_android_fitsSystemWindows
            boolean r2 = r3.getBoolean(r2, r9)
            r0.setFitsSystemWindows(r2)
            int r2 = com.google.android.material.R.styleable.NavigationView_android_maxWidth
            int r2 = r3.getDimensionPixelSize(r2, r9)
            r0.maxWidth = r2
            int r2 = com.google.android.material.R.styleable.NavigationView_subheaderColor
            boolean r2 = r3.hasValue(r2)
            r4 = 0
            if (r2 == 0) goto L_0x00cb
            int r2 = com.google.android.material.R.styleable.NavigationView_subheaderColor
            android.content.res.ColorStateList r2 = r3.getColorStateList(r2)
            goto L_0x00cc
        L_0x00cb:
            r2 = r4
        L_0x00cc:
            int r5 = com.google.android.material.R.styleable.NavigationView_subheaderTextAppearance
            boolean r5 = r3.hasValue(r5)
            if (r5 == 0) goto L_0x00db
            int r5 = com.google.android.material.R.styleable.NavigationView_subheaderTextAppearance
            int r5 = r3.getResourceId(r5, r9)
            goto L_0x00dc
        L_0x00db:
            r5 = r9
        L_0x00dc:
            r6 = 16842808(0x1010038, float:2.3693715E-38)
            if (r5 != 0) goto L_0x00e7
            if (r2 != 0) goto L_0x00e7
            android.content.res.ColorStateList r2 = r0.createDefaultColorStateList(r6)
        L_0x00e7:
            int r11 = com.google.android.material.R.styleable.NavigationView_itemIconTint
            boolean r11 = r3.hasValue(r11)
            if (r11 == 0) goto L_0x00f6
            int r6 = com.google.android.material.R.styleable.NavigationView_itemIconTint
            android.content.res.ColorStateList r6 = r3.getColorStateList(r6)
            goto L_0x00fa
        L_0x00f6:
            android.content.res.ColorStateList r6 = r0.createDefaultColorStateList(r6)
        L_0x00fa:
            int r11 = com.google.android.material.R.styleable.NavigationView_itemTextAppearance
            boolean r11 = r3.hasValue(r11)
            if (r11 == 0) goto L_0x0109
            int r11 = com.google.android.material.R.styleable.NavigationView_itemTextAppearance
            int r11 = r3.getResourceId(r11, r9)
            goto L_0x010a
        L_0x0109:
            r11 = r9
        L_0x010a:
            int r12 = com.google.android.material.R.styleable.NavigationView_itemIconSize
            boolean r12 = r3.hasValue(r12)
            if (r12 == 0) goto L_0x011b
            int r12 = com.google.android.material.R.styleable.NavigationView_itemIconSize
            int r12 = r3.getDimensionPixelSize(r12, r9)
            r0.setItemIconSize(r12)
        L_0x011b:
            int r12 = com.google.android.material.R.styleable.NavigationView_itemTextColor
            boolean r12 = r3.hasValue(r12)
            if (r12 == 0) goto L_0x012a
            int r12 = com.google.android.material.R.styleable.NavigationView_itemTextColor
            android.content.res.ColorStateList r12 = r3.getColorStateList(r12)
            goto L_0x012b
        L_0x012a:
            r12 = r4
        L_0x012b:
            if (r11 != 0) goto L_0x0136
            if (r12 != 0) goto L_0x0136
            r12 = 16842806(0x1010036, float:2.369371E-38)
            android.content.res.ColorStateList r12 = r0.createDefaultColorStateList(r12)
        L_0x0136:
            int r13 = com.google.android.material.R.styleable.NavigationView_itemBackground
            android.graphics.drawable.Drawable r13 = r3.getDrawable(r13)
            if (r13 != 0) goto L_0x0160
            boolean r14 = r0.hasShapeAppearance(r3)
            if (r14 == 0) goto L_0x0160
            android.graphics.drawable.Drawable r13 = r0.createDefaultItemBackground(r3)
            int r14 = com.google.android.material.R.styleable.NavigationView_itemRippleColor
            android.content.res.ColorStateList r14 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r1, (androidx.appcompat.widget.TintTypedArray) r3, (int) r14)
            if (r14 == 0) goto L_0x0160
            android.graphics.drawable.Drawable r15 = r0.createDefaultItemDrawable(r3, r4)
            android.graphics.drawable.RippleDrawable r8 = new android.graphics.drawable.RippleDrawable
            android.content.res.ColorStateList r14 = com.google.android.material.ripple.RippleUtils.sanitizeRippleDrawableColor(r14)
            r8.<init>(r14, r4, r15)
            r7.setItemForeground(r8)
        L_0x0160:
            int r4 = com.google.android.material.R.styleable.NavigationView_itemHorizontalPadding
            boolean r4 = r3.hasValue(r4)
            if (r4 == 0) goto L_0x0171
            int r4 = com.google.android.material.R.styleable.NavigationView_itemHorizontalPadding
            int r4 = r3.getDimensionPixelSize(r4, r9)
            r0.setItemHorizontalPadding(r4)
        L_0x0171:
            int r4 = com.google.android.material.R.styleable.NavigationView_itemVerticalPadding
            boolean r4 = r3.hasValue(r4)
            if (r4 == 0) goto L_0x0182
            int r4 = com.google.android.material.R.styleable.NavigationView_itemVerticalPadding
            int r4 = r3.getDimensionPixelSize(r4, r9)
            r0.setItemVerticalPadding(r4)
        L_0x0182:
            int r4 = com.google.android.material.R.styleable.NavigationView_dividerInsetStart
            int r4 = r3.getDimensionPixelSize(r4, r9)
            r0.setDividerInsetStart(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_dividerInsetEnd
            int r4 = r3.getDimensionPixelSize(r4, r9)
            r0.setDividerInsetEnd(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_subheaderInsetStart
            int r4 = r3.getDimensionPixelSize(r4, r9)
            r0.setSubheaderInsetStart(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_subheaderInsetEnd
            int r4 = r3.getDimensionPixelSize(r4, r9)
            r0.setSubheaderInsetEnd(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_topInsetScrimEnabled
            boolean r8 = r0.topInsetScrimEnabled
            boolean r4 = r3.getBoolean(r4, r8)
            r0.setTopInsetScrimEnabled(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_bottomInsetScrimEnabled
            boolean r8 = r0.bottomInsetScrimEnabled
            boolean r4 = r3.getBoolean(r4, r8)
            r0.setBottomInsetScrimEnabled(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_itemIconPadding
            int r4 = r3.getDimensionPixelSize(r4, r9)
            int r8 = com.google.android.material.R.styleable.NavigationView_itemMaxLines
            r14 = 1
            int r8 = r3.getInt(r8, r14)
            r0.setItemMaxLines(r8)
            com.google.android.material.navigation.NavigationView$1 r8 = new com.google.android.material.navigation.NavigationView$1
            r8.<init>()
            r10.setCallback(r8)
            r7.setId(r14)
            r7.initForMenu(r1, r10)
            if (r5 == 0) goto L_0x01df
            r7.setSubheaderTextAppearance(r5)
        L_0x01df:
            r7.setSubheaderColor(r2)
            r7.setItemIconTintList(r6)
            int r1 = r0.getOverScrollMode()
            r7.setOverScrollMode(r1)
            if (r11 == 0) goto L_0x01f1
            r7.setItemTextAppearance(r11)
        L_0x01f1:
            r7.setItemTextColor(r12)
            r7.setItemBackground(r13)
            r7.setItemIconPadding(r4)
            r10.addMenuPresenter(r7)
            androidx.appcompat.view.menu.MenuView r1 = r7.getMenuView(r0)
            android.view.View r1 = (android.view.View) r1
            r0.addView(r1)
            int r1 = com.google.android.material.R.styleable.NavigationView_menu
            boolean r1 = r3.hasValue(r1)
            if (r1 == 0) goto L_0x0217
            int r1 = com.google.android.material.R.styleable.NavigationView_menu
            int r1 = r3.getResourceId(r1, r9)
            r0.inflateMenu(r1)
        L_0x0217:
            int r1 = com.google.android.material.R.styleable.NavigationView_headerLayout
            boolean r1 = r3.hasValue(r1)
            if (r1 == 0) goto L_0x0228
            int r1 = com.google.android.material.R.styleable.NavigationView_headerLayout
            int r1 = r3.getResourceId(r1, r9)
            r0.inflateHeaderView(r1)
        L_0x0228:
            r3.recycle()
            r0.setupInsetScrimsListener()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setOverScrollMode(int i) {
        super.setOverScrollMode(i);
        NavigationMenuPresenter navigationMenuPresenter = this.presenter;
        if (navigationMenuPresenter != null) {
            navigationMenuPresenter.setOverScrollMode(i);
        }
    }

    private void maybeUpdateCornerSizeForDrawerLayout(int i, int i2) {
        if (!(getParent() instanceof DrawerLayout) || this.drawerLayoutCornerSize <= 0 || !(getBackground() instanceof MaterialShapeDrawable)) {
            this.shapeClipPath = null;
            this.shapeClipBounds.setEmpty();
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = (MaterialShapeDrawable) getBackground();
        ShapeAppearanceModel.Builder builder = materialShapeDrawable.getShapeAppearanceModel().toBuilder();
        if (GravityCompat.getAbsoluteGravity(this.layoutGravity, ViewCompat.getLayoutDirection(this)) == 3) {
            builder.setTopRightCornerSize((float) this.drawerLayoutCornerSize);
            builder.setBottomRightCornerSize((float) this.drawerLayoutCornerSize);
        } else {
            builder.setTopLeftCornerSize((float) this.drawerLayoutCornerSize);
            builder.setBottomLeftCornerSize((float) this.drawerLayoutCornerSize);
        }
        materialShapeDrawable.setShapeAppearanceModel(builder.build());
        if (this.shapeClipPath == null) {
            this.shapeClipPath = new Path();
        }
        this.shapeClipPath.reset();
        this.shapeClipBounds.set(0.0f, 0.0f, (float) i, (float) i2);
        ShapeAppearancePathProvider.getInstance().calculatePath(materialShapeDrawable.getShapeAppearanceModel(), materialShapeDrawable.getInterpolation(), this.shapeClipBounds, this.shapeClipPath);
        invalidate();
    }

    private boolean hasShapeAppearance(TintTypedArray tintTypedArray) {
        return tintTypedArray.hasValue(R.styleable.NavigationView_itemShapeAppearance) || tintTypedArray.hasValue(R.styleable.NavigationView_itemShapeAppearanceOverlay);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        maybeUpdateCornerSizeForDrawerLayout(i, i2);
    }

    public void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    private Drawable createDefaultItemBackground(TintTypedArray tintTypedArray) {
        return createDefaultItemDrawable(tintTypedArray, MaterialResources.getColorStateList(getContext(), tintTypedArray, R.styleable.NavigationView_itemShapeFillColor));
    }

    private Drawable createDefaultItemDrawable(TintTypedArray tintTypedArray, ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(getContext(), tintTypedArray.getResourceId(R.styleable.NavigationView_itemShapeAppearance, 0), tintTypedArray.getResourceId(R.styleable.NavigationView_itemShapeAppearanceOverlay, 0)).build());
        materialShapeDrawable.setFillColor(colorStateList);
        return new InsetDrawable(materialShapeDrawable, tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetStart, 0), tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetTop, 0), tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetEnd, 0), tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.menuState = new Bundle();
        this.menu.savePresenterStates(savedState.menuState);
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.menu.restorePresenterStates(savedState.menuState);
    }

    public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.listener = onNavigationItemSelectedListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), this.maxWidth), BasicMeasure.EXACTLY);
        } else if (mode == 0) {
            i = View.MeasureSpec.makeMeasureSpec(this.maxWidth, BasicMeasure.EXACTLY);
        }
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.shapeClipPath == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.clipPath(this.shapeClipPath);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    /* access modifiers changed from: protected */
    public void onInsetsChanged(WindowInsetsCompat windowInsetsCompat) {
        this.presenter.dispatchApplyWindowInsets(windowInsetsCompat);
    }

    public void inflateMenu(int i) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(false);
    }

    public Menu getMenu() {
        return this.menu;
    }

    public View inflateHeaderView(int i) {
        return this.presenter.inflateHeaderView(i);
    }

    public void addHeaderView(View view) {
        this.presenter.addHeaderView(view);
    }

    public void removeHeaderView(View view) {
        this.presenter.removeHeaderView(view);
    }

    public int getHeaderCount() {
        return this.presenter.getHeaderCount();
    }

    public View getHeaderView(int i) {
        return this.presenter.getHeaderView(i);
    }

    public ColorStateList getItemIconTintList() {
        return this.presenter.getItemTintList();
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.presenter.setItemIconTintList(colorStateList);
    }

    public ColorStateList getItemTextColor() {
        return this.presenter.getItemTextColor();
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.presenter.setItemTextColor(colorStateList);
    }

    public Drawable getItemBackground() {
        return this.presenter.getItemBackground();
    }

    public void setItemBackgroundResource(int i) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemBackground(Drawable drawable) {
        this.presenter.setItemBackground(drawable);
    }

    public int getItemHorizontalPadding() {
        return this.presenter.getItemHorizontalPadding();
    }

    public void setItemHorizontalPadding(int i) {
        this.presenter.setItemHorizontalPadding(i);
    }

    public void setItemHorizontalPaddingResource(int i) {
        this.presenter.setItemHorizontalPadding(getResources().getDimensionPixelSize(i));
    }

    public int getItemVerticalPadding() {
        return this.presenter.getItemVerticalPadding();
    }

    public void setItemVerticalPadding(int i) {
        this.presenter.setItemVerticalPadding(i);
    }

    public void setItemVerticalPaddingResource(int i) {
        this.presenter.setItemVerticalPadding(getResources().getDimensionPixelSize(i));
    }

    public int getItemIconPadding() {
        return this.presenter.getItemIconPadding();
    }

    public void setItemIconPadding(int i) {
        this.presenter.setItemIconPadding(i);
    }

    public void setItemIconPaddingResource(int i) {
        this.presenter.setItemIconPadding(getResources().getDimensionPixelSize(i));
    }

    public void setCheckedItem(int i) {
        MenuItem findItem = this.menu.findItem(i);
        if (findItem != null) {
            this.presenter.setCheckedItem((MenuItemImpl) findItem);
        }
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem findItem = this.menu.findItem(menuItem.getItemId());
        if (findItem != null) {
            this.presenter.setCheckedItem((MenuItemImpl) findItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }

    public MenuItem getCheckedItem() {
        return this.presenter.getCheckedItem();
    }

    public void setItemTextAppearance(int i) {
        this.presenter.setItemTextAppearance(i);
    }

    public void setItemIconSize(int i) {
        this.presenter.setItemIconSize(i);
    }

    public void setItemMaxLines(int i) {
        this.presenter.setItemMaxLines(i);
    }

    public int getItemMaxLines() {
        return this.presenter.getItemMaxLines();
    }

    public boolean isTopInsetScrimEnabled() {
        return this.topInsetScrimEnabled;
    }

    public void setTopInsetScrimEnabled(boolean z) {
        this.topInsetScrimEnabled = z;
    }

    public boolean isBottomInsetScrimEnabled() {
        return this.bottomInsetScrimEnabled;
    }

    public void setBottomInsetScrimEnabled(boolean z) {
        this.bottomInsetScrimEnabled = z;
    }

    public int getDividerInsetStart() {
        return this.presenter.getDividerInsetStart();
    }

    public void setDividerInsetStart(int i) {
        this.presenter.setDividerInsetStart(i);
    }

    public int getDividerInsetEnd() {
        return this.presenter.getDividerInsetEnd();
    }

    public void setDividerInsetEnd(int i) {
        this.presenter.setDividerInsetEnd(i);
    }

    public int getSubheaderInsetStart() {
        return this.presenter.getSubheaderInsetStart();
    }

    public void setSubheaderInsetStart(int i) {
        this.presenter.setSubheaderInsetStart(i);
    }

    public int getSubheaderInsetEnd() {
        return this.presenter.getSubheaderInsetEnd();
    }

    public void setSubheaderInsetEnd(int i) {
        this.presenter.setSubheaderInsetEnd(i);
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(getContext());
        }
        return this.menuInflater;
    }

    private ColorStateList createDefaultColorStateList(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), i2, defaultColor});
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    private void setupInsetScrimsListener() {
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                NavigationView navigationView = NavigationView.this;
                navigationView.getLocationOnScreen(navigationView.tmpLocation);
                boolean z = true;
                boolean z2 = NavigationView.this.tmpLocation[1] == 0;
                NavigationView.this.presenter.setBehindStatusBar(z2);
                NavigationView navigationView2 = NavigationView.this;
                navigationView2.setDrawTopInsetForeground(z2 && navigationView2.isTopInsetScrimEnabled());
                NavigationView.this.setDrawLeftInsetForeground(NavigationView.this.tmpLocation[0] == 0 || NavigationView.this.tmpLocation[0] + NavigationView.this.getWidth() == 0);
                Activity activity = ContextUtils.getActivity(NavigationView.this.getContext());
                if (activity != null) {
                    Rect currentWindowBounds = WindowUtils.getCurrentWindowBounds(activity);
                    boolean z3 = currentWindowBounds.height() - NavigationView.this.getHeight() == NavigationView.this.tmpLocation[1];
                    boolean z4 = Color.alpha(activity.getWindow().getNavigationBarColor()) != 0;
                    NavigationView navigationView3 = NavigationView.this;
                    navigationView3.setDrawBottomInsetForeground(z3 && z4 && navigationView3.isBottomInsetScrimEnabled());
                    if (!(currentWindowBounds.width() == NavigationView.this.tmpLocation[0] || currentWindowBounds.width() - NavigationView.this.getWidth() == NavigationView.this.tmpLocation[0])) {
                        z = false;
                    }
                    NavigationView.this.setDrawRightInsetForeground(z);
                }
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public Bundle menuState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuState = parcel.readBundle(classLoader);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuState);
        }
    }
}
