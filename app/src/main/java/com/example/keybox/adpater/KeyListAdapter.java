package com.example.keybox.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.keybox.AddActivity;
import com.example.keybox.MainActivity;
import com.example.keybox.R;
import com.example.keybox.entity.Key;
import com.example.keybox.sql.SQLUtil;

import java.util.List;

public class KeyListAdapter extends RecyclerView.Adapter<KeyListAdapter.ViewHolder> {

    private List<Key> keyList;

    public KeyListAdapter(List<Key> keyList) {
        this.keyList = keyList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView keyName;
        private TextView keyDelete;
        // private TextView username;
        // private TextView keyContent;
        public ViewHolder(View view) {
            super(view);
            keyName = view.findViewById(R.id.key_name);
            keyDelete = view.findViewById(R.id.delete);
            // username = view.findViewById(R.id.user_name);
            // keyContent = view.findViewById(R.id.key_content);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Key key = keyList.get(position);
        holder.keyName.setText(key.getKeyName());
        holder.keyDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deleteSuccess = SQLUtil.deleteKey(v.getContext(), key.getKeyId());
                if(deleteSuccess){
                    Toast.makeText(v.getContext(),"删除成功", Toast.LENGTH_SHORT).show();
                    MainActivity.actionStart(v.getContext());
                }
            }
        });
        // holder.username.setText(key.getUsername());
        // holder.keyContent.setText(key.getKeyContent());
    }

    public int getItemCount() {
        return keyList.size();
    }
}
