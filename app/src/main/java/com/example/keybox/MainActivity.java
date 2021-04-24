package com.example.keybox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.keybox.adpater.KeyListAdapter;
import com.example.keybox.entity.Key;
import com.example.keybox.sql.SQLUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Key> keyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        RecyclerView keyListView = findViewById(R.id.key_list);
        initKeyList();
        KeyListAdapter adapter = new KeyListAdapter(keyList);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        keyListView.setAdapter(adapter);
        keyListView.setLayoutManager(manager);
    }

    private void initKeyList() {
        keyList = new ArrayList<>();
        keyList = SQLUtil.queryAllKeys(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.add:
                addActivity();
                break;
            default:
                break;
        }
        return true;
    }

    public void addActivity() {
        AddActivity.actionStart(MainActivity.this);
        finish();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}