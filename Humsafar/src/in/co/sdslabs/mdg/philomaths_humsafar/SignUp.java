package in.co.sdslabs.mdg.philomaths_humsafar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity implements OnClickListener {

	EditText userName, password, confirm;
	Button bSignUp;
	SharedPreferences storeInfo;
	static String fileName = "loginInfo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		userName = (EditText) findViewById(R.id.etUserName);
		password = (EditText) findViewById(R.id.ePassword);
		confirm = (EditText) findViewById(R.id.etConfirm);
		bSignUp = (Button) findViewById(R.id.bSignUp);
		bSignUp.setOnClickListener(this);
		storeInfo = getSharedPreferences(fileName, 0);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.bSignUp) {
			String user = userName.getText().toString();
			String pass = password.getText().toString();
			String cPass = confirm.getText().toString();

			SharedPreferences.Editor editor = storeInfo.edit();

			if (pass.contentEquals(cPass)) {
				editor.putString(user, pass);
				editor.commit();
				Toast.makeText(this, "SignUp successful", Toast.LENGTH_LONG)
						.show();

			} else {
				password.setText("");
				confirm.setText("");
				Toast.makeText(this, "Enter your password again",
						Toast.LENGTH_LONG).show();
			}
		}
	}

}
