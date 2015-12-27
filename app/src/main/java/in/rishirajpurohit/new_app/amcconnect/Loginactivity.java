package in.rishirajpurohit.new_app.amcconnect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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


public class Loginactivity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    in.rishirajpurohit.new_app.amcconnect.JSONParser jsonParser = new in.rishirajpurohit.new_app.amcconnect.JSONParser();

    EditText lgninputPassword;
    EditText inputemail;

    // url to create new product
    private static String url_get_user = "http://rishirajpurohit.in/amcconnect/get_user.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String LEVEL = "userlevel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity2);

        // Edit Text
        inputemail = (EditText) findViewById(R.id.lgn_inputMail);
        lgninputPassword = (EditText) findViewById(R.id.lgn_inputPassword);

        // Create button
        Button btnCreateUser = (Button) findViewById(R.id.btn_getLogin);

        // button click event
        btnCreateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new getUser().execute();
            }
        });
    }

    /**
     * Background Async Task to Get User
     * */
    class getUser extends AsyncTask<String, String, String> {


        Context mContext;

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Loginactivity.this);
            pDialog.setMessage("Logging in..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String mail = inputemail.getText().toString();
            String password = lgninputPassword.getText().toString();

            String mailer = inputemail.getText().toString();
            KeyValueDB.setUsername(getApplicationContext(), mailer);


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("mail", mail));
            params.add(new BasicNameValuePair("password", password));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_get_user,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());





            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
                String level = json.getString(LEVEL);
                if (success == 1) {
                    // successfully logged in
                    KeyValueDB.setLevel(getApplicationContext(), level);
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
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


