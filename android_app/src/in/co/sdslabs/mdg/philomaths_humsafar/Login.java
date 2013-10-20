package in.co.sdslabs.mdg.philomaths_humsafar;

import static in.co.sdslabs.mdg.philomaths_humsafar.CommonUtilities.SERVER_URL;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener {

	EditText etEmail, etpassword;
	Button bLogin;

	int code;
	static JSONParser jsonParser = new JSONParser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		etEmail = (EditText) findViewById(R.id.etEmail);
		etpassword = (EditText) findViewById(R.id.etpassword);
		bLogin = (Button) findViewById(R.id.bLogin);

		bLogin.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.bLogin) {
			String inputEmail = etEmail.getText().toString();
			String inputPassword = etpassword.getText().toString();

			String url = SERVER_URL;

			try {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("email", inputEmail));
				nameValuePairs.add(new BasicNameValuePair("password",
						inputPassword));
				post(url, nameValuePairs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void post(String endpoint,
			ArrayList<NameValuePair> nameValuePairs) throws IOException {
		Log.i("Url in login post", endpoint);

		String returned = jsonParser.makeHttpRequest(endpoint, "POST",
				nameValuePairs);

		// check log cat for response
		Log.d("Login Response", returned);

	}
}
