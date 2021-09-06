package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnChildItemClick {
    List<ContactModel> listContact = new ArrayList<>();
    ListView lvContact;
    private ContactAdapter mAdapter;
    private ImageView ivUser;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

        mAdapter = new ContactAdapter( this,listContact);
        mAdapter.registerChildItemClick(this);
        lvContact.setAdapter(mAdapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactModel model = listContact.get(i);
                Toast.makeText(MainActivity.this, model.getName() + ": " + model.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        lvContact = (ListView) findViewById(R.id.lvContact);
        ivUser = (ImageView) findViewById(R.id.ivUser);
        tvName = (TextView) findViewById(R.id.tvName);
    }

    private void initData() {
        listContact.add(new ContactModel("Nguyen van a", "0987654234", R.drawable.ic_avatar1));
        listContact.add(new ContactModel("Nguyen van a", "09876542322", R.drawable.ic_avatar2));
        listContact.add(new ContactModel("Nguyen van a", "09876543323", R.drawable.ic_avatar3));
        listContact.add(new ContactModel("Nguyen van a", "09876544324", R.drawable.ic_avatar4));
        listContact.add(new ContactModel("Nguyen van a", "09876514325", R.drawable.ic_avatar1));
        listContact.add(new ContactModel("Nguyen van a", "09876254326", R.drawable.ic_avatar2));
        listContact.add(new ContactModel("Nguyen van a", "09876454327", R.drawable.ic_avatar3));
        listContact.add(new ContactModel("Nguyen van a", "09876554328", R.drawable.ic_avatar4));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.unRegisterChildItemClick();
    }

    @Override
    public void onItemChildClick(int position) {
        ContactModel contact = listContact.get(position);
        ivUser.setImageResource(contact.getImage());
        tvName.setText(contact.getName());
    }

}