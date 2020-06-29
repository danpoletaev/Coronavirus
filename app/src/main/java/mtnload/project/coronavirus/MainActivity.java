package mtnload.project.coronavirus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import mtnload.project.coronavirus.fragments.AdviceFragment;
import mtnload.project.coronavirus.fragments.MainDataFragment;
import mtnload.project.coronavirus.fragments.SymptomsFragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    BottomNavigationView bottomNavigation;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        openMainFragment();
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.signInAnonymously()
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    openMainFragment();
                } else {
                    Toast.makeText(MainActivity.this, "No internet connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_main:
                                openFragment(new MainDataFragment());
                                return true;
                            case R.id.navigation_symptoms:
                                openFragment(new SymptomsFragment());
                                return true;
                            case R.id.navigation_advices:
                                openFragment(new AdviceFragment());
                                return true;
                        }
                        return false;
                    }
                };

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }

    private void openMainFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new MainDataFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void sendUserToDataActivity() {
        Intent intent = new Intent(this, MainDataActivity.class);
        startActivity(intent);
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
