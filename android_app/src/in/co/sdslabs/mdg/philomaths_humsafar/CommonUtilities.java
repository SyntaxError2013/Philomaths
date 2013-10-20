package in.co.sdslabs.mdg.philomaths_humsafar;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {

	// give your server registration url here
	static final String SERVER_URL = "http://192.168.208.73/Humsafar/server_side_php/register.php";

	// Google project id
	static final String SENDER_ID = "152875858696";

	/**
	 * Tag used on log messages.
	 */
	static final String TAG = "AndroidHive GCM";

	static final String DISPLAY_MESSAGE_ACTION = "in.co.sdslabs.mdg.philomaths_humsafar.DISPLAY_MESSAGE";

	static final String EXTRA_MESSAGE = "message";

	static void displayMessage(Context context, String message) {
		Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
		intent.putExtra(EXTRA_MESSAGE, message);
		context.sendBroadcast(intent);
	}
}
