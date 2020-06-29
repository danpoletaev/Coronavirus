package mtnload.project.coronavirus.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mtnload.project.coronavirus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainDataFragment extends Fragment {

    private TextView confirmedText, deathsText, recoveredText, lastUpdateText;

    private String confirmed, deaths, recovered, lastUpdate;
    private ProgressBar progressBar;
    private FrameLayout mainFrameLayout;
    private RelativeLayout mainRelativeLayout;
    private LinearLayout tConfirmed, tDeaths, tRecovered;
    //private TextView lastUpdateText;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    Context context;
    SharedPreferences sharedPref;
    private boolean isConnected;


    public MainDataFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        getSharedPreferences();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("maindata");
        getData();

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_main_data, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mainRelativeLayout = (RelativeLayout) view.findViewById(R.id.main_relative_layout);
        tConfirmed = (LinearLayout) view.findViewById(R.id.infusedLayout);
        tDeaths = (LinearLayout) view.findViewById(R.id.deaths_layout);
        tRecovered = (LinearLayout) view.findViewById(R.id.recoveredLayout);
        if(isConnected) {
            showGray();
            progressBar.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(context, "Please, connect to internet to get actual information...", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirmedText = (TextView) view.findViewById(R.id.confirmed_text);
        deathsText = (TextView) view.findViewById(R.id.deaths_text);
        recoveredText = (TextView) view.findViewById(R.id.recovered_text);
        lastUpdateText = (TextView) view.findViewById(R.id.last_updated_date);
        setData();
    }

    private void getData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    confirmed = dataSnapshot.child("confirmed").getValue().toString();
                    deaths = dataSnapshot.child("deaths").getValue().toString();
                    recovered = dataSnapshot.child("recovered").getValue().toString();
                    lastUpdate = dataSnapshot.child("updateDate").getValue().toString();
                    writeToSharedPreferences();
                    setData();
                    hideGray();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                hideGray();
                Toast.makeText(context, "Please, connect to the internet for updates...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSharedPreferences(){
        confirmed = sharedPref.getString("confirmed", "121 564");
        deaths = sharedPref.getString("deaths", "4 373");
        recovered = sharedPref.getString("recovered", "66 239");
        lastUpdate = sharedPref.getString("lastupdate", "11/3/2020 15:49");
    }

    private void writeToSharedPreferences(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("confirmed", confirmed);
        editor.putString("deaths", deaths);
        editor.putString("recovered", recovered);
        editor.putString("lastupdate", lastUpdate);
        editor.commit();
    }

    private void setData() {
        confirmedText.setText(confirmed);
        deathsText.setText(deaths);
        recoveredText.setText(recovered);
        lastUpdateText.setText(lastUpdate);
    }

    private void showGray(){
        mainRelativeLayout.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        tConfirmed.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        tDeaths.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        tRecovered.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);

    }

    private void hideGray(){
        mainRelativeLayout.getBackground().setColorFilter(null);
        tConfirmed.getBackground().setColorFilter(null);
        tDeaths.getBackground().setColorFilter(null);
        tRecovered.getBackground().setColorFilter(null);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}