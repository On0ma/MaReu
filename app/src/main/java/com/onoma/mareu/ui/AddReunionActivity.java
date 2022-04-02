package com.onoma.mareu.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.onoma.mareu.R;
import com.onoma.mareu.di.DI;
import com.onoma.mareu.model.Reunion;
import com.onoma.mareu.service.ReunionApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReunionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TimePickerFragment.OnDataPass {
    @BindView(R.id.add_reunion_room)
    Spinner room;
    @BindView(R.id.add_reunion_subject)
    EditText subject;
    @BindView(R.id.add_reunion_time)
    EditText time;
    @BindView(R.id.add_reunion_attendees)
    EditText attendees;
    @BindView(R.id.add_reunion_button)
    Button createReunion;
    String selectedRooms;
    @BindView(R.id.toolbar_add)
    Toolbar mToolbar;

    private ReunionApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reunion);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getReunionApiService();

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        room.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Reunion.listRoomValues());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        room.setAdapter(adapter);
    }

    @Override
    public void onDataPass(String data) {
        time.setText(data);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selectedRooms = String.valueOf(parent.getItemAtPosition(pos));
    }

    public void onNothingSelected(AdapterView<?> parent) { }

    @OnClick(R.id.add_reunion_button)
    void addReunion() {
        Reunion reunion = new Reunion(
                System.currentTimeMillis(),
                time.getText().toString(),
                Reunion.Room.findByValue(selectedRooms),
                subject.getText().toString(),
                attendees.getText().toString()
        );
        mApiService.createReunion(reunion);
        finish();
    }


}
