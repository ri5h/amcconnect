package in.rishirajpurohit.new_app.amcconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Homeactivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homeactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void amc_login(View view){
        Intent lgn_intent = new Intent(getApplicationContext(), Loginactivity.class);
        startActivity(lgn_intent);
    }
    public void amc_register(View view){
        Intent rg_intent = new Intent(getApplicationContext(), Registeractivity.class);
        startActivity(rg_intent);
    }
    public void amc_guest(View view){
        Intent gst_intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(gst_intent);
    }
}
