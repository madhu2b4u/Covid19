package com.covid.covid19.notification;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.covid.covid19.CovidApp;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import org.json.JSONException;
import org.json.JSONObject;

public class MyNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    // This fires when a notification is opened by tapping on it.
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;

/*
        if (data != null) {
            Intent browserIntent = null;
            try {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getString("url")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            browserIntent.setPackage("org.chromium.chrome");
            try {
                new CovidApp().getContext().startActivity(browserIntent);
            } catch (ActivityNotFoundException ex) {
                // Chrome browser presumably not installed and open Kindle Browser
                browserIntent.setPackage("com.amazon.cloud9");
                new CovidApp().getContext().startActivity(browserIntent);
            }

        }*/

        //If we send notification with action buttons we need to specidy the button id's and retrieve it to
        //do the necessary operation.
        if (actionType == OSNotificationAction.ActionType.ActionTaken) {
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
            if (result.action.actionID.equals("ActionOne")) {
                Toast.makeText(new CovidApp().getContext(), "ActionOne Button was pressed", Toast.LENGTH_LONG).show();
            } else if (result.action.actionID.equals("ActionTwo")) {
                Toast.makeText(new CovidApp().getContext(), "ActionTwo Button was pressed", Toast.LENGTH_LONG).show();
            }
        }
    }
}
