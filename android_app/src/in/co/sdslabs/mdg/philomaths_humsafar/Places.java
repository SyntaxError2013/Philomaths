package in.co.sdslabs.mdg.philomaths_humsafar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class Places extends Activity implements OnClickListener {
	public AutoCompleteTextView source, destination;
	public ArrayAdapter<String> sourceAdapter, destinationAdapter;
	public String apiKey = "AIzaSyD9JiU299tvBqk1WQ5zy0sHa2ZUp4v2A-0";
	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places);
		source = (AutoCompleteTextView) findViewById(R.id.source);
		destination = (AutoCompleteTextView) findViewById(R.id.destination);
		submit = (Button) findViewById(R.id.submit);

		sourceAdapter = new ArrayAdapter<String>(this, R.layout.list_item);
		destinationAdapter = new ArrayAdapter<String>(this, R.layout.list_item);

		sourceAdapter.setNotifyOnChange(true);
		destinationAdapter.setNotifyOnChange(true);

		source.setAdapter(sourceAdapter);
		destination.setAdapter(destinationAdapter);

		source.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (count % 3 == 1) {
					sourceAdapter.clear();
					GetSource getSourcePlaces = new GetSource();
					// now pass the argument in the textview to the task
					getSourcePlaces.execute(source.getText().toString());
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {

			}
		});

		destination.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (count % 3 == 1) {
					destinationAdapter.clear();
					GetDestination getDestinationPlaces = new GetDestination();
					// now pass the argument in the textview to the task
					getDestinationPlaces.execute(destination.getText()
							.toString());
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {

			}
		});

		// date

		submit.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String init = source.getText().toString();
		String end = destination.getText().toString();

	}

	class GetSource extends AsyncTask<String, Void, ArrayList<String>> {

		@Override
		// three dots is java for an array of strings
		protected ArrayList<String> doInBackground(String... args) {

			Log.d("gottaGo", "doInBackground");

			ArrayList<String> predictionsArr = new ArrayList<String>();

			try {

				Log.i("input", args[0]);
				URL googlePlaces = new URL(
				// URLEncoder.encode(url,"UTF-8");
						"https://maps.googleapis.com/maps/api/place/autocomplete/json?input="
								+ args[0] + "&sensor=false&key=" + apiKey);
				URLConnection tc = googlePlaces.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						tc.getInputStream()));

				String line;
				StringBuffer sb = new StringBuffer();
				// take Google's legible JSON and turn it into one big string.
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
				// turn that string into a JSON object
				JSONObject predictions = new JSONObject(sb.toString());
				// now get the JSON array that's inside that object
				JSONArray ja = new JSONArray(
						predictions.getString("predictions"));
				int l = ja.length();
				Log.i("length", l + "");
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = (JSONObject) ja.get(i);
					// add each entry to our array
					predictionsArr.add(jo.getString("description"));
					Log.e("suggestions :", jo.getString("description"));
				}
			} catch (IOException e) {

				Log.e("GetPlaces : doInBackground", e.toString());

			} catch (JSONException e) {

				Log.e("GetPlaces : doInBackground", e.toString());

			}

			return predictionsArr;

		}

		// then our post

		@Override
		protected void onPostExecute(ArrayList<String> result) {

			Log.d("YourApp", "onPostExecute : " + result.size());
			// update the adapter
			sourceAdapter = new ArrayAdapter<String>(getApplicationContext(),
					R.layout.list_item);
			sourceAdapter.setNotifyOnChange(true);
			// attach the adapter to textview
			source.setAdapter(sourceAdapter);

			for (String string : result) {

				Log.d("YourApp", "onPostExecute : result = " + string);
				sourceAdapter.add(string);
				sourceAdapter.notifyDataSetChanged();

			}

			Log.d("YourApp", "onPostExecute : autoCompleteAdapter"
					+ sourceAdapter.getCount());

		}

	}

	class GetDestination extends AsyncTask<String, Void, ArrayList<String>> {

		@Override
		// three dots is java for an array of strings
		protected ArrayList<String> doInBackground(String... args) {

			Log.d("gottaGo", "doInBackground");

			ArrayList<String> predictionsArr = new ArrayList<String>();

			try {

				Log.i("input", args[0]);
				URL googlePlaces = new URL(
				// URLEncoder.encode(url,"UTF-8");
						"https://maps.googleapis.com/maps/api/place/autocomplete/json?input="
								+ args[0] + "&sensor=false&key=" + apiKey);
				URLConnection tc = googlePlaces.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						tc.getInputStream()));

				String line;
				StringBuffer sb = new StringBuffer();
				// take Google's legible JSON and turn it into one big string.
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
				// turn that string into a JSON object
				JSONObject predictions = new JSONObject(sb.toString());
				// now get the JSON array that's inside that object
				JSONArray ja = new JSONArray(
						predictions.getString("predictions"));
				int l = ja.length();
				Log.i("length", l + "");
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = (JSONObject) ja.get(i);
					// add each entry to our array
					predictionsArr.add(jo.getString("description"));
					Log.e("suggestions :", jo.getString("description"));
				}
			} catch (IOException e) {

				Log.e("GetPlaces : doInBackground", e.toString());

			} catch (JSONException e) {

				Log.e("GetPlaces : doInBackground", e.toString());

			}

			return predictionsArr;

		}

		// then our post

		@Override
		protected void onPostExecute(ArrayList<String> result) {

			Log.d("YourApp", "onPostExecute : " + result.size());
			// update the adapter
			destinationAdapter = new ArrayAdapter<String>(
					getApplicationContext(), R.layout.list_item);
			destinationAdapter.setNotifyOnChange(true);
			// attach the adapter to textview
			destination.setAdapter(destinationAdapter);

			for (String string : result) {

				Log.d("YourApp", "onPostExecute : result = " + string);
				destinationAdapter.add(string);
				destinationAdapter.notifyDataSetChanged();

			}

			Log.d("YourApp", "onPostExecute : autoCompleteAdapter"
					+ destinationAdapter.getCount());

		}

	}
}
