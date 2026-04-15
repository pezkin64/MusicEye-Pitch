package com.xemsoft.sheetmusicscanner2.player;

import com.xemsoft.sheetmusicscanner2.player.resource.BarButton;

public interface SessionListener {
    void onActiveButton(BarButton barButton, int i);

    void onAddPage(int i);

    void onButtonTap(BarButton barButton, int i);

    void onDeletePage(int i);

    void onEditTransitionDone();

    void onSetEditMode(boolean z);
}
