package com.freelanceProject.lavoiedroite.web;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;

public class WebViewClickListener implements View.OnTouchListener {
    private int position;
    private ViewGroup vg;
    private WebView wv;
    private View parent;

    public WebViewClickListener(View parent_,WebView wv, ViewGroup vg, int position) {
        this.vg = vg;
        this.position = position;
        this.wv = wv;
        this.parent=parent_;
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_CANCEL:
                return true;
            case MotionEvent.ACTION_UP:
                sendClick();
                return true;
        }

        return true;
    }

    public void sendClick() {
        ListView lv = (ListView) vg;
        lv.performItemClick(parent, position, 0);
    }
}