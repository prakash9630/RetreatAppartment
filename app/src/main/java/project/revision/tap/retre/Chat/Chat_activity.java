package project.revision.tap.retre.Chat;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.smooch.core.Smooch;
import io.smooch.ui.ConversationActivity;

/**
 * Created by prakash on 11/17/2016.
 */
public class Chat_activity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Smooch.init(this, "e8e5gcsbiifop7h7q1xi8xatx");


    }
}
