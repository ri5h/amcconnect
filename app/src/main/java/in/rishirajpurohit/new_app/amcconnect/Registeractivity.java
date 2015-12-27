package in.rishirajpurohit.new_app.amcconnect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Registeractivity extends Activity {

   // Progress Dialog
private ProgressDialog pDialog;

in.rishirajpurohit.new_app.amcconnect.JSONParser jsonParser = new in.rishirajpurohit.new_app.amcconnect.JSONParser();
EditText inputName;
EditText inputPassword;
EditText inputemail;


// url to create new product
private static String url_create_user = "http://rishirajpurohit.in/amcconnect/add_user.php";

// JSON Node names
private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);

        // Edit Text
        inputemail = (EditText) findViewById(R.id.inputMail);
        inputName = (EditText) findViewById(R.id.inputName);
        inputPassword = (EditText) findViewById(R.id.inputPassword);


        // Create button
        Button btnCreateUser = (Button) findViewById(R.id.btn_createUser);

        // button click event
        btnCreateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewProduct().execute();
            }
        });
    }

/**
 * Background Async Task to Create new product
 * */
class CreateNewProduct extends AsyncTask<String, String, String> {

    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(Registeractivity.this);
        pDialog.setMessage("Creating User..");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    /**
     * Creating product
     * */
    protected String doInBackground(String... args) {
        String name = inputName.getText().toString();
        String password = inputPassword.getText().toString();
        String mail = inputemail.getText().toString();
        String key = "rex";
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("mail", mail));
        params.add(new BasicNameValuePair("key", key));

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_create_user,
                "POST", params);

        // check log cat fro response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // successfully created product
                Intent i = new Intent(getApplicationContext(),Loginactivity.class);
                startActivity(i);

                // closing this screen
                finish();
            } else {
                // failed to create product
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * After completing background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(String file_url) {
        // dismiss the dialog once done
        pDialog.dismiss();
    }

}
}
