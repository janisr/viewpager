/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package com.navid.tabs;

import android.app.Activity;
import android.view.View;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.view.TiUIView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Kroll.proxy(creatableInModule = PagerTabsModule.class)
public class PagerTabsProxy extends TiViewProxy {
    // Standard Debugging variables
    private static final String TAG = PagerTabsProxy.class.getSimpleName();
    private PagerTabsView mainView;
    private List<KrollDict> runTimeViews;

    // Constructor
    public PagerTabsProxy() {
        super();
        runTimeViews = new ArrayList<KrollDict>();
    }

    @Override
    public TiUIView createView(Activity activity) {
        mainView = new PagerTabsView(this, getProperties(), runTimeViews);
        mainView.getLayoutParams().autoFillsHeight = true;
        mainView.getLayoutParams().autoFillsWidth = true;

        return mainView;
    }

    @Kroll.method
    public int add(String title, TiViewProxy view, @Kroll.argument(optional = true) Integer position) {
        if (mainView == null) {
            KrollDict param = new KrollDict();
            param.put("title", title);
            param.put("view", view);
            runTimeViews.add(param);
            return runTimeViews.size() - 1;
        }

        View nativeView = view.getOrCreateView().getNativeView();
        if (position == null) {
            return mainView.addView(title, nativeView);
        }

        return mainView.addView(title, nativeView, position);
    }

    @Kroll.method
    public int remove(int position) {
        return mainView.removeView(position);
    }

    @Kroll.method
    public boolean fireEvent(String eventName, @Kroll.argument(optional = true) KrollDict data, @Kroll.argument(optional = true) boolean children) {
        if (mainView != null && mainView.getAdapter() != null) {
            if (children) {
                for (TiViewProxy viewProxy : mainView.getViewProxies()) {
                    viewProxy.fireEvent(eventName, data, false);
                }
                return mainView.fireEvent(eventName, data, false);
            } else {
                return mainView.fireEvent(eventName, data, false);
            }
        }
        return false;
    }

    private void analyzeArgument(String prefix, Object arg) {
        if (arg == null) {
            Log.d(TAG, prefix + "NULL");
        } else if (arg.getClass().isArray()) {
            Object objArray[] = (Object[]) arg;
            Log.d(TAG, prefix + "Array with " + objArray.length + " entries");
            int index = 0;
            for (Object obj : objArray) {
                Log.d(TAG, prefix + "Index[" + index++ + "]");
                analyzeArgument(prefix + ".", obj);
            }
        } else if (arg instanceof HashMap) {
            HashMap kd = (HashMap) arg;
            Log.d(TAG, prefix + "Dictionary with " + kd.size() + " entries");
            for (Object key : kd.keySet().toArray()) {
                Log.d(TAG, prefix + "Key[" + key.toString() + "]");
                analyzeArgument(prefix + ".", kd.get(key));
            }
        } else if (arg instanceof String) {
            Log.d(TAG, prefix + "String = " + (String) arg);
        } else if (arg instanceof Number) {
            Log.d(TAG, prefix + "Number = " + (Number) arg);
        } else if (arg instanceof Date) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            Log.d(TAG, prefix + "Date = " + df.format((Date) arg));
        } else if (arg instanceof KrollFunction) {
            Log.d(TAG, prefix + "Callback");
        } else if (arg instanceof Boolean) {
            Log.d(TAG, prefix + "Boolean = " + (Boolean) arg);
        } else {
            Log.d(TAG, prefix + "Unknown class " + arg.getClass().getSimpleName());
        }
    }
}