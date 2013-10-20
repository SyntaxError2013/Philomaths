package in.co.sdslabs.mdg.philomaths_humsafar;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class Places extends Activity implements OnClickListener {
	EditText source, destination;
	Button submit;
	DatePicker date_picker;
	int year,month, day;
	static final int DATE_DIALOG_ID = 100;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places);
		source = (EditText) findViewById(R.id.textView1);
		destination = (EditText) findViewById(R.id.textView2);
		submit = (Button) findViewById(R.id.bSubmit);
		submit.setOnClickListener(this);
		setCurrentDate();


	}

	private void setCurrentDate() {
		// TODO Auto-generated method stub
		date_picker = (DatePicker) findViewById(R.id.datePicker1);
		final Calendar cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		date_picker.init(year, month, day, null);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String init = source.getText().toString();
		String end = destination.getText().toString();
		showDialog(DATE_DIALOG_ID);

	}

}
