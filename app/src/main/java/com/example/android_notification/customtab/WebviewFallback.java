
package com.example.android_notification.customtab;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.android_notification.customtab.CustomTabActivityHelper;
import com.example.android_notification.customtab.WebviewActivity;

/**
 * A Fallback that opens a Webview when Custom Tabs is not available
 */
public class WebviewFallback implements CustomTabActivityHelper.CustomTabFallback {
    @Override
    public void openUri(Activity activity, Uri uri) {
        Intent intent = new Intent(activity, WebviewActivity.class);
        intent.putExtra(WebviewActivity.EXTRA_URL, uri.toString());
        activity.startActivity(intent);
    }
}
