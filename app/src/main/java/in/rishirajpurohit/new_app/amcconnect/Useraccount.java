package in.rishirajpurohit.new_app.amcconnect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class Useraccount extends Activity {
    public int myLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraccount);

        String level = KeyValueDB.getLevel(getApplicationContext());

        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(level);
        ImageView iv = (ImageView)findViewById(R.id.imageView);



     myLevel = 0;

        try {
            myLevel = Integer.parseInt(level);
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        if (myLevel==1) {
            iv.setImageResource(R.drawable.level1);
        }

        if (myLevel==2) {
            iv.setImageResource(R.drawable.level2);

        }
        if (myLevel==3) {
            iv.setImageResource(R.drawable.level3);

        }


    }
    public void shareImage() {
        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("image/*");

        // Make sure you put example png image named myImage.png in your
        // directory
        String imagePath = Environment.getExternalStorageDirectory()
                + "/My Badge/Badge.png";

        File imageFileToShare = new File(imagePath);

        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(share, "Share Image!"));
    }


    public void sharingbtnclick(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey I reached Level :"+myLevel+" by Helping in Cleaning my own City. Feeling awesome !!");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }
    public boolean saveImage(View view){
        Bitmap bitmap = null;
        OutputStream output;

        // Retrieve the image from the res folder
        if(myLevel <= 1)
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.level1);
        else if(myLevel ==2)
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.level2);
        else if(myLevel >=3)
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.level3);

        // Find the SD Card path
        File filepath = Environment.getExternalStorageDirectory();

        // Create a new folder in SD Card
        File dir = new File(filepath.getAbsolutePath()
                + "/My Badge/");
        dir.mkdirs();

        // Create a name for the saved image
        File file = new File(dir, "Badge.png");

        // Show a toast message on successful save
        Toast.makeText(Useraccount.this, "Image Saved to SD Card",
                Toast.LENGTH_SHORT).show();
        try {

            output = new FileOutputStream(file);

            // Compress into png format image from 0% - 100%
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        shareImage();
        return false;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.useraccount, menu);
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
}

