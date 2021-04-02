package com.example.keybox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.keybox.sql.SQLUtil;

public class AddActivity extends AppCompatActivity {

    EditText keyNameInput;
    EditText usernameInput;
    EditText keyContentInput;
    LinearLayout parent;
    Button complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        parent = findViewById(R.id.parent);
        keyNameInput = findViewById(R.id.key_name_input);
        usernameInput = findViewById(R.id.username_input);
        keyContentInput = findViewById(R.id.key_content_input);
        init();
        complete = findViewById(R.id.complete);
        SQLUtil.createKeyBoxDataBase(this);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyName = keyNameInput.getText().toString();
                String username = usernameInput.getText().toString();
                String keyContent = keyContentInput.getText().toString();
                if (keyName.equals("") || username.equals("") || keyContent.equals("")) {
                    Toast.makeText(AddActivity.this,"有内容未填写",Toast.LENGTH_SHORT).show();
                } else {
                    boolean insertSuccess = SQLUtil.insertKey(AddActivity.this,keyName, username, keyContent);
                    if (insertSuccess){
                        Toast.makeText(AddActivity.this,"添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                        MainActivity.actionStart(AddActivity.this);
                    } else {
                        Toast.makeText(AddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AddActivity.class);
        context.startActivity(intent);
    }

    private void init() {
        TextView.OnEditorActionListener recoverEnterListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    recoverKeyBoard();
                    return true;
                }
                return false;
            }
        };
        View.OnTouchListener loseInputFocus = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                recoverKeyBoard();
                return false;
            }
        };
        parent.setOnTouchListener(loseInputFocus);
        keyNameInput.setOnEditorActionListener(recoverEnterListener);
        usernameInput.setOnEditorActionListener(recoverEnterListener);
        keyContentInput.setOnEditorActionListener(recoverEnterListener);
    }

    private void recoverKeyBoard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
    }
}