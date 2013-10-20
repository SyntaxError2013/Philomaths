package in.co.sdslabs.mdg.philomaths_humsafar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	EditText etuserName, etpassword;
	Button bLogin;
	String username;
	String passWord = null;
	SharedPreferences getInfo;
	String fileName = "loginInfo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		etuserName = (EditText) findViewById(R.id.etuser);
		etpassword = (EditText) findViewById(R.id.etpassword);
		bLogin = (Button) findViewById(R.id.bLogin);
		getInfo = getSharedPreferences(fileName, 0);

		bLogin.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.bLogin) {
			String inputUsername = etuserName.getText().toString();
			String inputPassword = etpassword.getText().toString();
			passWord = getInfo.getString(inputUsername, "Couldn't load");
			if (passWord.contentEquals("Couldn't load")) {
				Toast.makeText(this, "Check your username", Toast.LENGTH_LONG)
						.show();
			} else if (passWord.contentEquals(inputPassword)) {
				Intent intent = new Intent(Login.this, Places.class);
				startActivity(intent);
			} else {
				Toast.makeText(this, "Check your password", Toast.LENGTH_LONG)
						.show();
			}

		}

	}
}