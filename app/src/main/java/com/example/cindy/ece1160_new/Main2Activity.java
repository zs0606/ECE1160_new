package com.example.cindy.ece1160_new;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Main2Activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //fragments
        Fragment fragment = new SettingsScreen();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (savedInstanceState == null) {
            //created for the first time
            fragmentTransaction.add(R.id.relative_layout,fragment,"settings_fragment");
            fragmentTransaction.commit();
        }else {
            fragment = getFragmentManager().findFragmentByTag("settings_fragment");
        }


        //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //create a class for fragment
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static class SettingsScreen extends PreferenceFragment {
        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_screen);
        }
        @Override
        public void onResume() {
            super.onResume();
            SwitchPreference allowHint = (SwitchPreference) findPreference("allow_hints");
            SwitchPreference multiauth = (SwitchPreference) findPreference("phone_unlock"); //phone_unlock multi_auth
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean isChecked = sharedPreferences.getBoolean("allow_hint", false);
            boolean isChecked2 = sharedPreferences.getBoolean("phone_unlock", false);
        }
    }

    //back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
