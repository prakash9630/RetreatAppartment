package project.revision.tap.retre.Chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.smooch.ui.ConversationActivity;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 11/17/2016.
 */
public class Chat_start extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConversationActivity.show(this);

        finish();
    }
}
