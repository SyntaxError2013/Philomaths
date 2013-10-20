package in.co.sdslabs.mdg.philomaths_humsafar;

import static in.co.sdslabs.mdg.philomaths_humsafar.CommonUtilities.SERVER_URL;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener {

	EditText etEmail, etpassword;
	Button bLogin, bSignup;

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

		bSignup = (Button) findViewById(R.id.bSignUp);

		bLogin.setOnClickListener(this);
		bSignup.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.bLogin) {
			String inputEmail = etEmail.getText().toString();
			String inputPassword = etpassword.getText().toString();

			
			if(inputEmail.equals("ak") && inputPassword.equals("")){
				startActivity(new Intent(Login.this, Places.class));
			}
			
//			String url = SERVER_URL;
//
//			try {
//				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
//						2);
//				nameValuePairs.add(new BasicNameValuePair("email", inputEmail));
//				nameValuePairs.add(new BasicNameValuePair("password",
//						inputPassword));
//				post(url, nameValuePairs);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} else if (v.getId() == R.id.bSignUp) {
			startActivity(new Intent(Login.this, RegisterActivity.class));
		}
	}

	private static void post(String endpoint,
			ArrayList<NameValuePair> nameValuePairs) throws IOException {
		Log.i("Url in login post",
				"http://192.168.208.73/Humsafar/server_side_php/login.php");

		String returned = jsonParser.makeHttpRequest(
				"http://192.168.208.73/Humsafar/server_side_php/login.php",
				"POST", nameValuePairs);

		// check log cat for response
		Log.d("Login Response", returned);
	}
}
