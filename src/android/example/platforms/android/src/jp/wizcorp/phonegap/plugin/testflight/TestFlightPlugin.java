/*
 *  _____          _     ___ _ _       _     _       ___ _             _       
 * /__   \___  ___| |_  / __\ (_) __ _| |__ | |_    / _ \ |_   _  __ _(_)_ __  
 *   / /\/ _ \/ __| __|/ _\ | | |/ _` | '_ \| __|  / /_)/ | | | |/ _` | | '_ \ 
 *  / / |  __/\__ \ |_/ /   | | | (_| | | | | |_  / ___/| | |_| | (_| | | | | |
 *  \/   \___||___/\__\/    |_|_|\__, |_| |_|\__| \/    |_|\__,_|\__, |_|_| |_|
 *                              |___/                           |___/         
 *
 * @author  	Ally Ogilvie
 * @copyright   Wizcorp Inc. [ Incorporated Wizards ] 2013
 * @file        TestFlightPlugin.java
 * @about       Plugin for TestFlight SDK.
*/
package jp.wizcorp.phonegap.plugin.testflight;

import com.testflightapp.lib.TestFlight;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;

public class TestFlightPlugin extends CordovaPlugin {

	private static String TAG = "TestFlightPlugin";
    private static Boolean TAKEOFF = false;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        Log.d(TAG, "[action] ****** " + action );
        // Rather than write this into every action;
        // Check action is not takeOff then check if we took off successfully
        if (!action.equals("takeOff") && !action.equals("setOptions")) {
            if (!TAKEOFF) {
                PluginResult res = new PluginResult(PluginResult.Status.ERROR, "takeOff() has not been successfully called yet.");
                callbackContext.sendPluginResult(res);
                return true;
            }
        }

		if (action.equals("addCustomEnvironmentInformation")) {
            // TODO:
            // Android API does not exist

            PluginResult res = new PluginResult(PluginResult.Status.OK);
            callbackContext.sendPluginResult(res);
			return true;

        } else if (action.equals("takeOff")) {
            PluginResult res;

            // Initialize TestFlight with your app token.
            if (args.length() > 0) {
                // Get app token
                TestFlight.takeOff(cordova.getActivity().getApplication(), (String) args.get(0));

                if (TestFlight.isActive() ) {
                    // Set this so our other APIs can error handle better
                    TAKEOFF = true;
                }
                res = new PluginResult(PluginResult.Status.OK);
            } else {
                // Missing required parameter!
                res = new PluginResult(PluginResult.Status.ERROR, "teamToken property is missing.");
            }
            callbackContext.sendPluginResult(res);
            return true;

        } else if (action.equals("setOptions")) {
            // TODO:
            // Android API does not exist or not implemented
            Log.w(TAG, "Android" + action + "API does not exist or not implemented");

            PluginResult res = new PluginResult(PluginResult.Status.OK);
            callbackContext.sendPluginResult(res);

        } else if (action.equals("passCheckpoint")) {
            PluginResult res;
            if (args.length() > 0) {
                // Get checkpoint name
                TestFlight.passCheckpoint((String) args.get(0));
                res = new PluginResult(PluginResult.Status.OK);
            } else {
                // Missing required parameter!
                res = new PluginResult(PluginResult.Status.ERROR, "no checkpoint name to set.");
            }
            callbackContext.sendPluginResult(res);
            return true;

        } else if (action.equals("remoteLogAsync")) {
            PluginResult res;

            if (args.length() > 0) {
                // Get String to log
                TestFlight.log((String) args.get(0));
                res = new PluginResult(PluginResult.Status.OK);
            } else {
                // Missing required parameter!
                res = new PluginResult(PluginResult.Status.ERROR, "nothing to log.");
            }
            callbackContext.sendPluginResult(res);
            return true;

        } else if (action.equals("submitFeedback")) {
            // TODO:
            // Android API does not exist or not implemented
            Log.w(TAG, "Android" + action + "API does not exist or not implemented");

            PluginResult res = new PluginResult(PluginResult.Status.OK);
            callbackContext.sendPluginResult(res);
            return true;

        } else if (action.equals("setDeviceIdentifier")) {
            // TODO:
            // Android API does not exist or not implemented
            Log.w(TAG, "Android" + action + "API does not exist or not implemented");

            PluginResult res = new PluginResult(PluginResult.Status.OK);
            callbackContext.sendPluginResult(res);
            return true;

        } else if (action.equals("setDeviceIdentifierUUID")) {
            // TODO:
            // Android API does not exist or not implemented
            Log.w(TAG, "Android" + action + "API does not exist or not implemented");

            PluginResult res = new PluginResult(PluginResult.Status.OK);
            callbackContext.sendPluginResult(res);
            return true;

        } else if (action.equals("manuallyStartSession")) {

            TestFlight.startSession();
            PluginResult res = new PluginResult(PluginResult.Status.OK);
            callbackContext.sendPluginResult(res);
            return true;

        } else if (action.equals("manuallyEndSession")) {

            TestFlight.endSession();
            PluginResult res = new PluginResult(PluginResult.Status.OK);
            callbackContext.sendPluginResult(res);
            return true;
        }
		return false;
	}

}
