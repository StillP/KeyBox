package com.example.keybox.adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.keybox.R;
import com.example.keybox.entity.Key;

import java.util.List;

public class KeyListAdapter extends RecyclerView.Adapter<KeyListAdapter.ViewHolder> {

    private List<Key> keyList;

    public KeyListAdapter(List<Key> keyList) {
        this.keyList = keyList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView keyName;
        private TextView username;
        private TextView keyContent;
        public ViewHolder(View view) {
            super(view);
            keyName = view.findViewById(R.id.key_name);
            username = view.findViewById(R.id.user_name);
            keyContent = view.findViewById(R.id.key_content);
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
        holder.username.setText(key.getUsername());
        holder.keyContent.setText(key.getKeyContent());
    }

    public int getItemCount() {
        return keyList.size();
    }
}
