package com.nexmore.rnd.ui.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.nexmore.rnd.R;
import com.nexmore.rnd.transitions.FabTransform;

public class CreateChatActivity extends AppCompatActivity {

    AutoCompleteTextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat);

        FabTransform.setup(this, findViewById(R.id.search_container));
    }

    private void hideKeyboard() {
//        imm.hideSoftInputFromWindow(tvEmail.getWindowToken(), 0);
    }

    public void dismiss(View view) {
        hideKeyboard();
        finishAfterTransition();
    }
}
