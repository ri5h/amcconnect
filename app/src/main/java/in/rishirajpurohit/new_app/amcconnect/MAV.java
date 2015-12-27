package in.rishirajpurohit.new_app.amcconnect;

import android.app.Application;

/**
 * Created by Rex on 26-12-2015.
 */
public class MAV extends Application{
    private String user_mail; //make getter and setter
    private static MAV singleInstance = null;

    public static MAV getInstance()
    {
        return singleInstance;
    }

    public static void setInstance(String mail)
    {
       /* user_mail = mail;*/
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleInstance = this;
    }
}
