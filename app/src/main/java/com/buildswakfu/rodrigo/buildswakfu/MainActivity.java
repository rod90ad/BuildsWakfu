package com.buildswakfu.rodrigo.buildswakfu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
   
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
  
import android.widget.TextView;
import android.widget.Toast;

import com.buildswakfu.rodrigo.buildswakfu.Layouts.SearchScreenFragment;
import com.buildswakfu.rodrigo.buildswakfu.Layouts.SettingsFragment;
import com.buildswakfu.rodrigo.buildswakfu.Utils.BD;
import com.buildswakfu.rodrigo.buildswakfu.Utils.BDConn;
import com.buildswakfu.rodrigo.buildswakfu.Utils.Build;
import com.buildswakfu.rodrigo.buildswakfu.Utils.Item;
import com.buildswakfu.rodrigo.buildswakfu.Utils.User;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    //layout variaveis
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    //firebase Variaveis
    public static FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    public static int RC_SIGN_IN = 1;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 3;
    private String TAG = "FIREBASE AUTH";
    public static DatabaseReference mDatabase;

    //google API
    public static GoogleApiClient mGoogleApiClient;

    //Analytics API
    public Tracker mTracker;

    //variaveis da classe
    public static User user;
    public static Build build;
    public final int MY_PERMISSIONS_REQUEST_CAMERA=2;
    public static SharedPreferences prefs;
    public static BD bd;
    public static ProgressDialog load;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicia o sharedPreferences
        prefs = getApplicationContext().getSharedPreferences("com.buildswakfu.rodrigo.buildswakfu", getApplicationContext().MODE_PRIVATE);
        context = this.getApplicationContext();
        //pega o idioma
        String language = prefs.getString("idioma", null);
        if(language==null){
            language = "en";
            prefs.edit().putString("idioma",language).commit();
        }
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,displayMetrics);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.screens));
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);


        //Get a Tracker (should auto-report)
        ((AnalyticsApplication) getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        mTracker = ((AnalyticsApplication) getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);

        //google Sign In
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("503029112369-biqrmoh50i3u161f5p6mcvtj36hrh4ik.apps.googleusercontent.com")
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //authlistener que responde as alterações
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        //MainActivity.load = new ProgressDialog(this);
        //MainActivity.load.setTitle(getResources().getString(R.string.wait));
        //MainActivity.load.setMessage(getResources().getString(R.string.adicionando));
        //MainActivity.load.setCancelable(false);
        BDConn bdConn = new BDConn(MainActivity.this);
        bd = new BD(this);
        //bd.popularBDFirebase(getResources().openRawResource(R.raw.itens_pt));
        //bd.popularBDFirebase(getResources().openRawResource(R.raw.itens_en));
        //bd.popularBDFirebase(getResources().openRawResource(R.raw.itens_fr));
        //bd.popularBDFirebase(getResources().openRawResource(R.raw.itens_es));
        signIn();
    }

    @Override
    public void onBackPressed(){
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(getBaseContext(), getResources().getString(R.string.backmsg), Toast.LENGTH_SHORT).show();
            mBackPressed = System.currentTimeMillis();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, getResources().getString(R.string.permsok), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.permsnok), Toast.LENGTH_SHORT).show();
                }
            case MY_PERMISSIONS_REQUEST_CAMERA:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, getResources().getString(R.string.permsok), Toast.LENGTH_LONG).show();
                    //Import();
                }else{
                    Toast.makeText(this, getResources().getString(R.string.permsnok), Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                Toast.makeText(MainActivity.this, "Welcome "+account.getDisplayName(),Toast.LENGTH_LONG).show();
                firebaseAuthWithGoogle(account);
            } else {
                Toast.makeText(MainActivity.this, "Login Fail",Toast.LENGTH_LONG);
            }
        }
        if(requestCode == 3 && resultCode == RESULT_OK){

        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }else{
                            Log.e(TAG, "SignInSucess");
                            mDatabase.child("users").child(acct.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        user = dataSnapshot.getValue(User.class);
                                        if(!Locale.getDefault().getLanguage().equals(user.getLang())){
                                            Log.e(TAG, "Locale:"+Locale.getDefault().getLanguage()+" User Locale:"+user.getLang());
                                            prefs.edit().putString("idioma",user.getLang()).commit();
                                            Locale.setDefault(new Locale(user.getLang()));
                                            user.setLastSync(0);
                                            mDatabase.child("users/"+user.getUserID()).setValue(user);
                                            Intent i = getIntent();
                                            finish();
                                            startActivity(i);
                                        }else{
                                            mDatabase.child("bdVersion").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    if(dataSnapshot.getValue(Integer.class)!=user.getLastSync() || bd.getItem("1")==null){
                                                        MainActivity.load = new ProgressDialog(MainActivity.this);
                                                        MainActivity.load.setTitle(getResources().getString(R.string.wait));
                                                        MainActivity.load.setMessage(getResources().getString(R.string.adicionando));
                                                        MainActivity.load.setCancelable(false);
                                                        MainActivity.load.show();
                                                        final ArrayList<Item> items = new ArrayList<Item>();
                                                        switch (user.getLang()) {
                                                            case "pt":
                                                                MainActivity.mDatabase.child("Items-pt").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        items.clear();
                                                                        for(DataSnapshot itemSnap : dataSnapshot.getChildren()) {
                                                                            Item item = itemSnap.getValue(Item.class);
                                                                            items.add(item);
                                                                        }
                                                                        bd.popularBD(items);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(DatabaseError databaseError) {

                                                                    }
                                                                });
                                                                break;
                                                            case "en":
                                                                MainActivity.mDatabase.child("Items-en").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        items.clear();
                                                                        for(DataSnapshot itemSnap : dataSnapshot.getChildren()) {
                                                                            Item item = itemSnap.getValue(Item.class);
                                                                            items.add(item);
                                                                        }
                                                                        bd.popularBD(items);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(DatabaseError databaseError) {

                                                                    }
                                                                });
                                                                break;
                                                            case "fr":
                                                                MainActivity.mDatabase.child("Items-fr").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        items.clear();
                                                                        for(DataSnapshot itemSnap : dataSnapshot.getChildren()) {
                                                                            Item item = itemSnap.getValue(Item.class);
                                                                            items.add(item);
                                                                        }
                                                                        bd.popularBD(items);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(DatabaseError databaseError) {

                                                                    }
                                                                });
                                                                break;
                                                            case "es":
                                                                MainActivity.mDatabase.child("Items-es").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        items.clear();
                                                                        for(DataSnapshot itemSnap : dataSnapshot.getChildren()) {
                                                                            Item item = itemSnap.getValue(Item.class);
                                                                            items.add(item);
                                                                        }
                                                                        bd.popularBD(items);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(DatabaseError databaseError) {

                                                                    }
                                                                });
                                                                break;
                                                        }
                                                        user.setLastSync(dataSnapshot.getValue(Integer.class));
                                                        mDatabase.child("users/"+user.getUserID()).setValue(user);
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });
                                        }
                                    }else {
                                        Log.e(TAG, "è nulo");
                                        user = new User();
                                        user.setName(acct.getDisplayName());
                                        user.setUserID(acct.getId());
                                        user.setUserIdToken(acct.getIdToken());
                                        user.setEmail(acct.getEmail());
                                        user.setPhotoUrl(""+acct.getPhotoUrl());
                                        user.setLastSync(0);
                                        VerificaLang();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                });
    }


    private void VerificaLang(){
        final CharSequence[] items = getResources().getStringArray(R.array.idiomas);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.select_lang)
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                user.setLang("en");
                                break;
                            case 2:
                                user.setLang("fr");
                                break;
                            case 1:
                                user.setLang("pt");
                                break;
                            case 3:
                                user.setLang("es");
                                break;
                            default:
                                user.setLang("en");
                        }
                        mDatabase.child("users/"+user.getUserID()).setValue(user);
                        prefs.edit().putString("idioma",user.getLang()).commit();
                        Intent i = getIntent();
                        finish();
                        startActivity(i);
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private String[] tittles;
        private SearchScreenFragment searchScreenFragment;
        private SettingsFragment settingsFragment;

        public SectionsPagerAdapter(FragmentManager fm, String[] tittles) {
            super(fm);
            this.tittles = tittles;
            searchScreenFragment = new SearchScreenFragment();
            settingsFragment = new SettingsFragment();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return searchScreenFragment==null ? searchScreenFragment=SearchScreenFragment.newInstance("A","B") : searchScreenFragment;
                case 3:
                    return settingsFragment==null ? settingsFragment=SettingsFragment.newInstance("A","B") : settingsFragment;
                default:
                    return PlaceholderFragment.newInstance(1);
            }
        }

        @Override
        public int getCount() {
            return tittles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tittles[position];
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.hello_blank_fragment, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
}
