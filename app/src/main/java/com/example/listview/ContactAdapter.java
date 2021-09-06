package com.example.listview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private Context mContext;
    private List<ContactModel> listContacts;
    private IOnChildItemClick iOnChildItemClick;

    public ContactAdapter(Context mContext, List<ContactModel> listContacts) {
        this.mContext = mContext;
        this.listContacts = listContacts;
    }

    public void registerChildItemClick(IOnChildItemClick iOnChildItemClick) {
        this.iOnChildItemClick = iOnChildItemClick;
    }
    public void unRegisterChildItemClick() {
        this.iOnChildItemClick = null;
    }

    @Override
    public int getCount() {
        return listContacts.size();
    }

    @Override
    public Object getItem(int i) {
        return listContacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if (view == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            rowView = inflater.inflate(R.layout.itemt_contact, null);
            // config viewHolder
            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView) rowView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) rowView.findViewById(R.id.tvPhone);
            holder.ivAvatar = (ImageView) rowView.findViewById(R.id.ivAvatar);
            holder.btnCall = (ImageButton) rowView.findViewById(R.id.btnCall);
            holder.btnEdit = (ImageButton) rowView.findViewById(R.id.btnEdit);
            rowView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        ContactModel model = listContacts.get(i);
        holder.tvPhone.setText(model.getPhone());
        holder.tvName.setText(model.getName());
        holder.ivAvatar.setImageResource(model.getImage());

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCall(i);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnChildItemClick.onItemChildClick(i);
            }
        });
        return rowView;
    }

    private void onCall(int i) {
        ContactModel contact = listContacts.get(i);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhone()));
        if(ActivityCompat.checkSelfPermission(mContext,Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

            return;
        }
        mContext.startActivity(intent);
    }


    static class ViewHolder {
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;
        ImageButton btnCall;
        ImageButton btnEdit;
    }
}
