package com.ramindu.weeraman.filter.sample.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ramindu.weeraman.filter.sample.R;
import com.ramindu.weeraman.filter.sample.data.model.UserItem;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<TextViewHolder> {
    private List<UserItem> userItems;

    public UserAdapter(List<UserItem> userItems) {
        this.userItems = userItems;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TextViewHolder holder, final int position) {
        final UserItem userItem = userItems.get(position);
        holder.textView.setText(userItem.getDisplayName());
        holder.textView.setOnClickListener(v ->
            Toast.makeText(
                    holder.textView.getContext(), userItem.getDisplayName(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }
}
