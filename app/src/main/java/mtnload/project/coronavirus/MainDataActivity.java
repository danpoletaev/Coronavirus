package mtnload.project.coronavirus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainDataActivity extends AppCompatActivity {

    private EditText confirmedText, deathsText, recoveredText;

    private String confirmed, deaths, recovered, lastUpdate;
    private TextView lastUpdateText;
    
    FirebaseDatabase database;
    DatabaseReference myRef;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data);
        
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("maindata");
        
        initializeFields();

        getData();
        
//        myRef.setValue("Hello, World!")
//        .addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Toast.makeText(MainDataActivity.this, "Well done!", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainDataActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
//            }
//        });
        
        
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
                   setData();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setData() {
        confirmedText.setText(confirmed);
        deathsText.setText(deaths);
        recoveredText.setText(recovered);
        lastUpdateText.setText(lastUpdate);
    }

    private void initializeFields() {
            confirmedText = (EditText) findViewById(R.id.confirmed_text);
            deathsText = (EditText) findViewById(R.id.deaths_text);
            recoveredText = (EditText) findViewById(R.id.recovered_text);
            lastUpdateText = (TextView) findViewById(R.id.last_updated_date);
    }
}
