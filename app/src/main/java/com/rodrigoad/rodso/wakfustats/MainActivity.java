package com.rodrigoad.rodso.wakfustats;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
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

import com.rodrigoad.rodso.wakfustats.Layouts.BuildsFragment;
import com.rodrigoad.rodso.wakfustats.Layouts.SearchScreenFragment;
import com.rodrigoad.rodso.wakfustats.Layouts.SettingsFragment;
import com.rodrigoad.rodso.wakfustats.Layouts.TopBuildsFragment;
import com.rodrigoad.rodso.wakfustats.Utils.BD;
import com.rodrigoad.rodso.wakfustats.Utils.BDConn;
import com.rodrigoad.rodso.wakfustats.Utils.Build;
import com.rodrigoad.rodso.wakfustats.Utils.ItemAux;
import com.rodrigoad.rodso.wakfustats.Utils.LocaleHelper;
import com.rodrigoad.rodso.wakfustats.Utils.Spell;
import com.rodrigoad.rodso.wakfustats.Utils.User;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
    public static final int MY_PERMISSIONS_REQUEST_CAMERA=2;
    public static SharedPreferences prefs;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicia o sharedPreferences
        prefs = getApplicationContext().getSharedPreferences("com.rodrigoad.rodso.wakfustats", getApplicationContext().MODE_PRIVATE);
        context = this.getApplicationContext();

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
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
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
        BDConn bdConn = new BDConn(MainActivity.this);
        signIn();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    public void onBackPressed(){
        if(mViewPager.getCurrentItem()!=0) {
            mViewPager.setCurrentItem(0);
        }else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(getBaseContext(), getResources().getString(R.string.backmsg), Toast.LENGTH_SHORT).show();
                mBackPressed = System.currentTimeMillis();
            }
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
                    mSectionsPagerAdapter.buildsFragment.Importar();
                }else{
                    Toast.makeText(this, getResources().getString(R.string.permsnok), Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("AUTH","vai verificar login");
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                Toast.makeText(MainActivity.this, getResources().getString(R.string.welcome)+" "+account.getDisplayName(),Toast.LENGTH_LONG).show();
                firebaseAuthWithGoogle(account);
            } else {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.loginfail),Toast.LENGTH_LONG).show();
            }
        }
        if(requestCode == 3 && resultCode == RESULT_OK){

        }

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.e("Scanned: ", result.getContents());
                if(result.getContents().length()>178) {
                    Build qrBuild = new Build();
                    if(qrBuild.setQrstring(result.getContents(), context)){
                        BD bd = new BD(context);
                        if (new BD(context).verificaBuild(qrBuild.getNome())) {
                            bd.salvabuildQR(qrBuild);
                            Toast.makeText(context, R.string.addbuildsucess, Toast.LENGTH_LONG).show();
                            mTracker.send(new HitBuilders.EventBuilder()
                                    .setCategory("Action")
                                    .setAction("Import")
                                    .build());
                            mSectionsPagerAdapter.buildsFragment.setRV();
                        } else {
                            Toast.makeText(context, R.string.errornamesame, Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(context, getResources().getString(R.string.codenotok),Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context,getResources().getString(R.string.codenotok), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
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
                                            LocaleHelper.setLocale(context, user.getLang());
                                            user.setLastSync(0);
                                            mDatabase.child("users/"+user.getUserID()).setValue(user);
                                            Intent i = getIntent();
                                            finish();
                                            startActivity(i);
                                        }else{
                                            mDatabase.child("bdVersion").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    if(dataSnapshot.getValue(Integer.class)!=user.getLastSync() || new BD(getApplicationContext()).getItem("21936")==null){
                                                        if(RepopulateBD(user)) {
                                                            user.setLastSync(dataSnapshot.getValue(Integer.class));
                                                        }
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

    private boolean RepopulateBD(User user){
        final ArrayList<Spell> spells = new ArrayList<Spell>();
        final ArrayList<ItemAux> items = new ArrayList<ItemAux>();
        MainActivity.mDatabase.child("Items-"+user.getLang()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items.clear();
                for(DataSnapshot itemSnap : dataSnapshot.getChildren()) {
                    ItemAux item = itemSnap.getValue(ItemAux.class);
                    items.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MainActivity.mDatabase.child("Spells-"+user.getLang()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                spells.clear();
                for(DataSnapshot itemSnap : dataSnapshot.getChildren()) {
                    Spell spell = itemSnap.getValue(Spell.class);
                    spells.add(spell);
                }
                ArrayList list = new ArrayList<>();
                list.add(items);
                list.add(spells);
                new PopularBDAsync(MainActivity.context).execute(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return true;
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
        public BuildsFragment buildsFragment;
        private TopBuildsFragment topBuildsFragment;
        private int currentPage=0;
        public int getCurrentPage(){ return currentPage; }

        public SectionsPagerAdapter(FragmentManager fm, String[] tittles) {
            super(fm);
            this.tittles = tittles;
            searchScreenFragment = new SearchScreenFragment();
            settingsFragment = new SettingsFragment();
            buildsFragment = new BuildsFragment();
            topBuildsFragment = new TopBuildsFragment();
        }

        @Override
        public Fragment getItem(int position) {
            currentPage=position;
            switch (position){
                case 0:
                    return searchScreenFragment==null ? searchScreenFragment=SearchScreenFragment.newInstance("A","B") : searchScreenFragment;
                case 1:
                    return buildsFragment == null ? buildsFragment = BuildsFragment.newInstance("A","B") : buildsFragment;
                case 2:
                    return topBuildsFragment == null ? topBuildsFragment = topBuildsFragment.newInstance("A","B") : topBuildsFragment;
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

    private class PopularBDAsync extends AsyncTask<ArrayList,Integer,Boolean> {

        private ArrayList<ItemAux> items = new ArrayList<ItemAux>();
        private ArrayList<Spell> spells = new ArrayList<Spell>();
        final ContentValues valores = new ContentValues();
        private ProgressDialog dialog;
        private Context ctx;
        private BD bd;

        public PopularBDAsync(Context ctx){
            this.ctx=ctx;
            bd = new BD(ctx);
        }

        @Override
        protected void onPreExecute(){
            try{
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setTitle(context.getResources().getString(R.string.wait));
                dialog.setMessage(context.getResources().getString(R.string.adicionando));
                dialog.setCancelable(false);
                dialog.setProgress(0);
                dialog.setIndeterminate(false);
                dialog.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                dialog.dismiss();
            }
            bd.bd.execSQL("drop table item");
            bd.bd.execSQL("create table item(" +
                    "_id integer primary key," +  //id
                    "tipo varchar(10) not null," +              //identificador se é capacete ou qualquer outra coisa para fazer o where
                    "nome varchar(100) not null," +             //nome
                    "nivel int not null," +                     //nivel
                    "raridade int," +                            //raridade
                    "heal int," +                               //cura
                    "range int," +                              //alcance
                    "beserkdmg int," +                          //dano beserk
                    "block int," +                              //bloqueio
                    "control int," +                            //controle
                    "dmg int," +                                //dano geral
                    "criticalchance int," +                     //chance de critico
                    "backdmg int," +                            // dano nas costas
                    "distancedmg int," +                        // dano a distancia
                    "closecombatdmg int," +                     //dano corpo a corpo
                    "element1dmg int," +                        //1 elemento dano
                    "element2dmg int," +                        //2 elementos dano
                    "element3dmg int," +                        //3 elementos dano
                    "areadmg int," +                            //dano em area
                    "onetargetdmg int," +                       //dano alvo unico
                    "evasion int," +                            //evasao
                    "criticaldmg int," +                        //dano critico
                    "iniciative int," +                         //iniciativa
                    "stop int," +                               //parada
                    "prosp int," +                              //prosperição
                    "pwmax int," +                              //pw maximo
                    "resist int," +                             //resistencia
                    "sabedoria int," +                          //sabedoria
                    "reswater int," +                           //resistencia agua
                    "resfire int," +                            //resistencia fogo
                    "resair int," +                             //resistencia ar
                    "researth int," +                           //resistencia terra
                    "resbackdmg int," +                        //resistencia dano nas costas
                    "rescriticaldmg int," +                     //resistencia dano critico
                    "res1elerandom int," +                      //resistencia a 1 elemento aleatorio
                    "res2elerandom int," +                      //resistencia a 2 elementos aleatorios
                    "res3elerandom int," +                      //resistencia a 3 elementos aleatorios
                    "actionpoint int," +                        //  PA
                    "movepoint int," +                          //  PM
                    "vitalpoint int," +                         //  PV
                    "wakfupoint int," +                         //  PW
                    "status text," +                            // Status de item SHUSHU
                    "link varchar(400)," +                      // link da imagem
                    "pvphp int," +                              // pvp hp
                    "singular int," +                           // singular ou não
                    "arteequipar int," +                        // arte de equipar
                    "nvfagua int," +                            // nv feitiço de agua
                    "nvffogo int," +                            // de fogo
                    "nvfar int," +                              // de ar
                    "nvfterra int," +                           // de terra
                    "nvfgeral int," +                           // de geral
                    "movespeed int," +                          // velocidade de movimento
                    "respm int," +                              // resistencia a remoção de pm
                    "respa int," +                               //resistencia a pa
                    "dmgfogo int," +                             // dominio fogo
                    "dmgterra int, " +                           //dominio terra
                    "dmgagua int," +                             //dominio agua
                    "dmgar int," +                               //dominio ar
                    "minertake int,"+
                    "dmgpa int,"+
                    "dmgpm int);");                          //coleta de minerio
            bd.bd.execSQL("drop table spell");
            bd.bd.execSQL("create table spell(" +
                    "_id integer primary key autoincrement," +  //id 0
                    "classe varchar(10) not null," +            //1
                    "active boolean not null," +                //2
                    "area boolean," +                           //3
                    "basedmg int," +                            //4
                    "condition1 varchar (200)," +               //5
                    "condition2 varchar (200)," +               //6
                    "condition3 varchar (200)," +               //7
                    "condition4 varchar (200)," +               //8
                    "condition5 varchar (200)," +               //9
                    "condition6 varchar (200)," +               //10
                    "condition7 varchar (200)," +               //11
                    "condition8 varchar (200)," +               //12
                    "condition9 varchar (200)," +               //13
                    "condition10 varchar (200)," +              //14
                    "description varchar (500)," +              //15
                    "diagonal boolean," +                       //16
                    "image varchar (50)," +                     //17
                    "level int," +                              //18
                    "linear boolean," +                         //19
                    "line1 varchar (200)," +                    //20
                    "line2 varchar (200)," +                    //21
                    "line3 varchar (200)," +                    //22
                    "line4 varchar (200)," +                    //23
                    "line5 varchar (200)," +                    //24
                    "line6 varchar (200)," +                //25
                    "line7 varchar (200)," +                //26
                    "line8 varchar (200)," +                //27
                    "line9 varchar (200)," +                //28
                    "line10 varchar (200)," +               //29
                    "line11 varchar (200)," +               //30
                    "line12 varchar (200)," +               //31
                    "line13 varchar (200)," +               //32
                    "line14 varchar (200)," +               //33
                    "line15 varchar (200)," +               //34
                    "linhadevisao boolean," +               //35
                    "name varchar (100)," +                 //36
                    "paused int," +                         //37
                    "pmused int," +                         //38
                    "pwused int," +                         //39
                    "rangeini int," +                       //40
                    "rangeend int," +                       //41
                    "rangemod boolean," +                   //42
                    "scale double);");                       //43
            super.onPreExecute();
        }

        private String getType(String type, String lang){
            if(lang.equals("pt")){
                switch (type){
                    case "Segunda mão":
                        type="wsec";
                        break;
                    case "Armas de 1 mão":
                        type="w1h";
                        break;
                    case "Armas de 2 mãos":
                        type="w2h";
                        break;
                    case "Cinto":
                        type="cint";
                        break;
                    case "Amuleto":
                        type="amu";
                        break;
                    case "Anel":
                        type="anel";
                        break;
                    case "Botas":
                        type="bot";
                        break;
                    case "Peitoral":
                        type="pei";
                        break;
                    case "Capacete":
                        type="elm";
                        break;
                    case "Capa":
                        type="cap";
                        break;
                    case "Dragonas":
                        type="drag";
                        break;
                    case "Montarias":
                        type="mont";
                        break;
                    case "Mascote":
                        type="pet";
                        break;
                    case "Emblema":
                        type="ins";
                        break;
                }
            }else if(lang.equals("en")){
                switch (type){
                    case "Second Hand":
                        type="wsec";
                        break;
                    case "One-Handed Weapons":
                        type="w1h";
                        break;
                    case "Two-Handed Weapons":
                        type="w2h";
                        break;
                    case "Belt":
                        type="cint";
                        break;
                    case "Amulet":
                        type="amu";
                        break;
                    case "Ring":
                        type="anel";
                        break;
                    case "Boots":
                        type="bot";
                        break;
                    case "Breastplate":
                        type="pei";
                        break;
                    case "Helmet":
                        type="elm";
                        break;
                    case "Cloak":
                        type="cap";
                        break;
                    case "Epaulettes":
                        type="drag";
                        break;
                    case "Mounts":
                        type="mont";
                        break;
                    case "Pet":
                        type="pet";
                        break;
                    case "Emblem":
                        type="ins";
                        break;
                }
            }else if(lang.equals("fr")){
                switch (type){
                    case "Seconde Main":
                        type="wsec";
                        break;
                    case "Armes 1 Main":
                        type="w1h";
                        break;
                    case "Armes 2 Mains":
                        type="w2h";
                        break;
                    case "Ceinture":
                        type="cint";
                        break;
                    case "Amulette":
                        type="amu";
                        break;
                    case "Anneau":
                        type="anel";
                        break;
                    case "Bottes":
                        type="bot";
                        break;
                    case "Plastron":
                        type="pei";
                        break;
                    case "Casque":
                        type="elm";
                        break;
                    case "Cape":
                        type="cap";
                        break;
                    case "Epaulettes":
                        type="drag";
                        break;
                    case "Montures":
                        type="mont";
                        break;
                    case "Familier":
                        type="pet";
                        break;
                    case "Emblème":
                        type="ins";
                        break;
                }
            }else{
                switch (type){
                    case "Segunda mano":
                        type="wsec";
                        break;
                    case "Armas de una mano":
                        type="w1h";
                        break;
                    case "Armas de dos manos":
                        type="w2h";
                        break;
                    case "Cinturón":
                        type="cint";
                        break;
                    case "Amuleto":
                        type="amu";
                        break;
                    case "Anillo":
                        type="anel";
                        break;
                    case "Botas":
                        type="bot";
                        break;
                    case "Coraza":
                        type="pei";
                        break;
                    case "Casco":
                        type="elm";
                        break;
                    case "Capa":
                        type="cap";
                        break;
                    case "Hombreras":
                        type="drag";
                        break;
                    case "Monturas":
                        type="mont";
                        break;
                    case "Mascota":
                        type="pet";
                        break;
                    case "Emblema":
                        type="ins";
                        break;
                }
            }
            return type;
        }

        private ContentValues getStatus(String html,ContentValues valores, String lang){
            int aux;
            int num;
            if(lang.equals("pt")){
                while((aux = html.indexOf("title\">")) != -1) {
                    html = html.substring(aux + 27);
                    try {
                        if (html.indexOf("%") <= 4 && html.indexOf("%") != -1) {
                            num = Integer.parseInt(html.substring(0, html.indexOf("%")));
                        } else {
                            num = Integer.parseInt(html.substring(0, html.indexOf(" ")));
                        }
                    }catch (Exception e){
                        Log.e("Valores","Erro:"+e.getMessage());
                        num = 0;
                    }
                    String status = html.substring(html.indexOf(" ") + 1, html.indexOf("</div"));
                    switch (status) {
                        case "Pontos de vida                            ":
                            valores.put("vitalpoint", num);
                            break;
                        case "de Bloqueio                            ":
                            valores.put("block", num);
                            break;
                        case "de Parada                            ":
                            valores.put("stop", num);
                            break;
                        case "de Resistência a 3 elementos aleatórios                            ":
                            valores.put("res3elerandom", num);
                            break;
                        case "de Esquiva                            ":
                            valores.put("evasion", num);
                            break;
                        case "de Domínio de 3 elementos aleatórios                            ":
                            valores.put("element3dmg", num);
                            break;
                        case "de Resistência elementar                            ":
                            valores.put("resist", num);
                            break;
                        case "de Resistência a crítico                            ":
                            valores.put("rescriticaldmg", num);
                            break;
                        case "de Resistência de costas                            ":
                            valores.put("resbackdmg", num);
                            break;
                        case "de PW                            ":
                            valores.put("wakfupoint", num);
                            break;
                        case "de PA                            ":
                            valores.put("actionpoint", num);
                            break;
                        case "de Iniciativa                            ":
                            valores.put("iniciative", num);
                            break;
                        case "de Domínio de 2 elementos aleatórios                            ":
                            valores.put("element2dmg", num);
                            break;
                        case "de Golpe crítico                            ":
                            valores.put("criticalchance", num);
                            break;
                        case "de Domínio de cura                            ":
                            valores.put("heal", num);
                            break;
                        case "de Domínio de crítico                            ":
                            valores.put("criticaldmg", num);
                            break;
                        case "de Alcance                            ":
                            valores.put("range", num);
                            break;
                        case "de Domínio de costas                            ":
                            valores.put("backdmg", num);
                            break;
                        case "de PM                            ":
                            valores.put("movepoint", num);
                            break;
                        case "de Domínio de alvo único                            ":
                            valores.put("onetargetdmg", num);
                            break;
                        case "de Domínio de Berserk                            ":
                            valores.put("beserkdmg", num);
                            break;
                        case "de Resistência  Fogo                            ":
                            valores.put("resfire", num);
                            break;
                        case "de Resistência  �?gua                            ":
                            valores.put("reswater", num);
                            break;
                        case "de Resistência  Terra                            ":
                            valores.put("researth", num);
                            break;
                        case "Pontos de vida PvP                            ":
                            valores.put("pvphp", num);
                            break;
                        case "de Resistência a 1 elemento aleatório                            ":
                            valores.put("res1elerandom", num);
                            break;
                        case "de Resistência a 2 elementos aleatórios                            ":
                            valores.put("res2elerandom", num);
                            break;
                        case "de Domínio de luta corpo a corpo                            ":
                            valores.put("closecombatdmg", num);
                            break;
                        case "de Domínio de zona                            ":
                            valores.put("areadmg", num);
                            break;
                        case "de Resistência  Ar                            ":
                            valores.put("resair", num);
                            break;
                        case "de Domínio de 1 elemento aleatório                            ":
                            valores.put("element1dmg", num);
                            break;
                        case "de Instrução Militar                            ":
                            valores.put("arteequipar", num);
                            break;
                        case "de Domínio  �?gua                            ":
                            valores.put("dmgagua", num);
                            break;
                        case "de Domínio  Terra                            ":
                            valores.put("dmgterra", num);
                            break;
                        case "de Domínio  Fogo                            ":
                            valores.put("dmgfogo", num);
                            break;
                        case "de Domínio  Ar                            ":
                            valores.put("dmgar", num);
                            break;
                        case "de Resistência de PM                            ":
                            valores.put("respm", num);
                            break;
                        case "de Domínio elemental                            ":
                            valores.put("dmg", num);
                            break;
                        case "de Controle                            ":
                            valores.put("control", num);
                            break;
                        case "de Domínio de distância                            ":
                            valores.put("distancedmg", num);
                            break;
                        case "de Prospecção                            ":
                            valores.put("prosp", num);
                            break;
                        case "de Sabedoria                            ":
                            valores.put("sabedoria", num);
                            break;
                        case "nv. aos feitiços  Terra                            ":
                            valores.put("nvfterra", num);
                            break;
                        case "nv. aos feitiços  Fogo                            ":
                            valores.put("nvffogo", num);
                            break;
                        case "Niv. aos feitiços elementares                            ":
                            valores.put("nvfgeral", num);
                            break;
                        case "nv. aos feitiços  Ar                            ":
                            valores.put("nvfar", num);
                            break;
                        case "nv. aos feitiços  Água                            ":
                            valores.put("nvfagua", num);
                            break;
                        case "de Resistência de PA                            ":
                            valores.put("respa", num);
                            break;
                        case "de quantidade de colheita para Mineiro                            ":
                            valores.put("minertake", num);
                            break;
                        case "de velocidade de movimento                            ":
                            valores.put("movespeed", num);
                            break;
                        case "de Remoção de PA                            ":
                            valores.put("dmgpa", num);
                            break;
                        case "de Remoção de PM                            ":
                            valores.put("dmgpm", num);
                            break;
                        case "pontos de vida                            ":
                            valores.put("vitalpoint", num);
                            break;
                        case "Ponto de vida                            ":
                            valores.put("vitalpoint", num);
                            break;
                        case "PV                            ":
                            break;
                        case "PM                            ":
                            valores.put("movepoint", num);
                            break;
                        default:
                            Log.e("ERROR","Status não identificado:"+status);
                    }
                }
                return valores;
            }else if(lang.equals("en")){                        //######################################EN##################################
                while((aux = html.indexOf("title\">")) != -1) {
                    html = html.substring(aux + 27);
                    try {
                        if (html.indexOf("%") <= 4 && html.indexOf("%") != -1) {
                            num = Integer.parseInt(html.substring(0, html.indexOf("%")));
                        } else {
                            num = Integer.parseInt(html.substring(0, html.indexOf(" ")));
                        }
                    }catch (Exception e){
                        Log.e("Valores","Erro:"+e.getMessage());
                        num = 0;
                    }
                    String status = html.substring(html.indexOf(" ") + 1, html.indexOf("</div"));
                    switch (status) {
                        case "Control                            ":
                            valores.put("control", num);
                            break;
                        case "Health Points                            ":
                            valores.put("vitalpoint", num);
                            break;
                        case "Health Point                            ":
                            valores.put("vitalpoint", num);
                            break;
                        case "Lock                            ":
                            valores.put("block", num);
                            break;
                        case "Block                            ":
                            valores.put("stop", num);
                            break;
                        case "Resistance to 3 random elements                            ":
                            valores.put("res3elerandom", num);
                            break;
                        case "Dodge                            ":
                            valores.put("evasion", num);
                            break;
                        case "Distance Mastery                            ":
                            valores.put("distancedmg", num);
                            break;
                        case "Mastery to 3 random elements                            ":
                            valores.put("element3dmg", num);
                            break;
                        case "Elemental Resistance                            ":
                            valores.put("resist", num);
                            break;
                        case "Critical Resistance                            ":
                            valores.put("rescriticaldmg", num);
                            break;
                        case "Rear Resistance                            ":
                            valores.put("resbackdmg", num);
                            break;
                        case "WP                            ":
                            valores.put("wakfupoint", num);
                            break;
                        case "AP                            ":
                            valores.put("actionpoint", num);
                            break;
                        case "Initiative                            ":
                            valores.put("iniciative", num);
                            break;
                        case "Mastery to 2 random elements                            ":
                            valores.put("element2dmg", num);
                            break;
                        case "Critical Hit                            ":
                            valores.put("criticalchance", num);
                            break;
                        case "Healing Mastery                            ":
                            valores.put("heal", num);
                            break;
                        case "Critical Mastery                            ":
                            valores.put("criticaldmg", num);
                            break;
                        case "Range                            ":
                            valores.put("range", num);
                            break;
                        case "Rear Mastery                            ":
                            valores.put("backdmg", num);
                            break;
                        case "MP                            ":
                            valores.put("movepoint", num);
                            break;
                        case "Single Target Mastery                            ":
                            valores.put("onetargetdmg", num);
                            break;
                        case "Berserk Mastery                            ":
                            valores.put("beserkdmg", num);
                            break;
                        case "Resistance  Fire                            ":
                            valores.put("resfire", num);
                            break;
                        case "Resistance  Water                            ":
                            valores.put("reswater", num);
                            break;
                        case "Resistance  Earth                            ":
                            valores.put("researth", num);
                            break;
                        case "PvP Health Points                            ":
                            valores.put("pvphp", num);
                            break;
                        case "Resistance to 1 random element                            ":
                            valores.put("res1elerandom", num);
                            break;
                        case "Resistance to 2 random elements                            ":
                            valores.put("res2elerandom", num);
                            break;
                        case "Melee Mastery                            ":
                            valores.put("closecombatdmg", num);
                            break;
                        case "Area Mastery                            ":
                            valores.put("areadmg", num);
                            break;
                        case "Resistance  Air                            ":
                            valores.put("resair", num);
                            break;
                        case "Mastery to 1 random element                            ":
                            valores.put("element1dmg", num);
                            break;
                        case "Kit Skill                            ":
                            valores.put("arteequipar", num);
                            break;
                        case "Mastery  Water                            ":
                            valores.put("dmgagua", num);
                            break;
                        case "Mastery  Earth                            ":
                            valores.put("dmgterra", num);
                            break;
                        case "Mastery  Fire                            ":
                            valores.put("dmgfogo", num);
                            break;
                        case "Mastery  Air                            ":
                            valores.put("dmgar", num);
                            break;
                        case "MP Resistance                            ":
                            valores.put("respm", num);
                            break;
                        case "Elemental Mastery                            ":
                            valores.put("dmg", num);
                            break;
                        case "Prospecting                            ":
                            valores.put("prosp", num);
                            break;
                        case "Wisdom                            ":
                            valores.put("sabedoria", num);
                            break;
                        case "Lvl. to elemental spells                            ":
                            valores.put("nvfgeral", num);
                            break;
                        case "AP Resistance                            ":
                            valores.put("respa", num);
                            break;
                        case "Harvesting Quantity in Miner                            ":
                            valores.put("minertake", num);
                            break;
                        case "movement speed                            ":
                            valores.put("movespeed", num);
                            break;
                        case "AP Removal                            ":
                            valores.put("dmgpa", num);
                            break;
                        case "MP Removal                            ":
                            valores.put("dmgpm", num);
                            break;
                        default:
                            Log.e("ERROR","Status não identificado:"+status);
                    }
                }
                return valores;
            }else if(lang.equals("fr")){                    //######################################FR##################################
                while((aux = html.indexOf("title\">")) != -1) {
                    html = html.substring(aux + 27);
                    try{
                        if (html.indexOf("%") <= 4 && html.indexOf("%") != -1) {
                            num = Integer.parseInt(html.substring(0, html.indexOf("%")));
                        } else {
                            num = Integer.parseInt(html.substring(0, html.indexOf(" ")));
                        }
                    }catch (Exception e){
                        Log.e("Valores","Erro:"+e.getMessage());
                        num = 0;
                    }
                    String status = html.substring(html.indexOf(" ") + 1, html.indexOf("</div"));
                    switch (status) {
                        case "Points de Vie                            ":
                            valores.put("vitalpoint", num);
                            break;
                        case "Tacle                            ":
                            valores.put("block", num);
                            break;
                        case "Parade                            ":
                            valores.put("stop", num);
                            break;
                        case "Résistance sur 3 éléments aléatoires                            ":
                            valores.put("res3elerandom", num);
                            break;
                        case "Esquive                            ":
                            valores.put("evasion", num);
                            break;
                        case "Maîtrise Distance                            ":
                            valores.put("distancedmg", num);
                            break;
                        case "Maîtrise sur 3 éléments aléatoires                            ":
                            valores.put("element3dmg", num);
                            break;
                        case "Résistance Elémentaire                            ":
                            valores.put("resist", num);
                            break;
                        case "Résistance Critique                            ":
                            valores.put("rescriticaldmg", num);
                            break;
                        case "Résistance Dos                            ":
                            valores.put("resbackdmg", num);
                            break;
                        case "PW                            ":
                            valores.put("wakfupoint", num);
                            break;
                        case "PA                            ":
                            valores.put("actionpoint", num);
                            break;
                        case "Initiative                            ":
                            valores.put("iniciative", num);
                            break;
                        case "Maîtrise sur 2 éléments aléatoires                            ":
                            valores.put("element2dmg", num);
                            break;
                        case "Coup Critique                            ":
                            valores.put("criticalchance", num);
                            break;
                        case "Maîtrise Soin                            ":
                            valores.put("heal", num);
                            break;
                        case "Maîtrise Critique                            ":
                            valores.put("criticaldmg", num);
                            break;
                        case "Portée                            ":
                            valores.put("range", num);
                            break;
                        case "Maîtrise Dos                            ":
                            valores.put("backdmg", num);
                            break;
                        case "PM                            ":
                            valores.put("movepoint", num);
                            break;
                        case "Maîtrise Monocible                            ":
                            valores.put("onetargetdmg", num);
                            break;
                        case "Maîtrise Berserk                            ":
                            valores.put("beserkdmg", num);
                            break;
                        case "Résistance  Feu                            ":
                            valores.put("resfire", num);
                            break;
                        case "Résistance  Eau                            ":
                            valores.put("reswater", num);
                            break;
                        case "Résistance  Terre                            ":
                            valores.put("researth", num);
                            break;
                        case "Points de Vie JcJ                            ":
                            valores.put("pvphp", num);
                            break;
                        case "Résistance sur 1 élément aléatoire                            ":
                            valores.put("res1elerandom", num);
                            break;
                        case "Résistance sur 2 éléments aléatoires                            ":
                            valores.put("res2elerandom", num);
                            break;
                        case "Maîtrise Mêlée                            ":
                            valores.put("closecombatdmg", num);
                            break;
                        case "Maîtrise Zone                            ":
                            valores.put("areadmg", num);
                            break;
                        case "Résistance  Air                            ":
                            valores.put("resair", num);
                            break;
                        case "Maîtrise sur 1 élément aléatoire                            ":
                            valores.put("element1dmg", num);
                            break;
                        case "Art du Barda                            ":
                            valores.put("arteequipar", num);
                            break;
                        case "Maîtrise  Eau                            ":
                            valores.put("dmgagua", num);
                            break;
                        case "Maîtrise  Terre                            ":
                            valores.put("dmgterra", num);
                            break;
                        case "Maîtrise  Feu                            ":
                            valores.put("dmgfogo", num);
                            break;
                        case "Maîtrise  Air                            ":
                            valores.put("dmgar", num);
                            break;
                        case "Résistance PM                            ":
                            valores.put("respm", num);
                            break;
                        case "Maîtrise Elémentaire                            ":
                            valores.put("dmg", num);
                            break;
                        case "Contrôle                            ":
                            valores.put("control", num);
                            break;
                        case "Prospection                            ":
                            valores.put("prosp", num);
                            break;
                        case "Sagesse                            ":
                            valores.put("sabedoria", num);
                            break;
                        case "Niv. aux sorts  Terre                            ":
                            valores.put("nvfterra", num);
                            break;
                        case "Niv. aux sorts  Feu                            ":
                            valores.put("nvffogo", num);
                            break;
                        case "Niv. aux sorts élémentaires                            ":
                            valores.put("nvfgeral", num);
                            break;
                        case "Niv. aux sorts  Air                            ":
                            valores.put("nvfar", num);
                            break;
                        case "Niv. aux sorts  Eau                            ":
                            valores.put("nvfagua", num);
                            break;
                        case "Résistance PA                            ":
                            valores.put("respa", num);
                            break;
                        case "Quantité Récolte en Mineur                            ":
                            valores.put("minertake", num);
                            break;
                        case "de vitesse de déplacement                            ":
                            valores.put("movespeed", num);
                            break;
                        case "Retrait PA                            ":
                            valores.put("dmgpa", num);
                            break;
                        case "Retrait PM                            ":
                            valores.put("dmgpm", num);
                            break;
                        case "Point de Vie                            ":
                            valores.put("vitalpoint", num);
                            break;
                        case "PdV                            ":
                            break;
                        case "Volonté                            ":
                            break;
                        case "aux dégâts contre les Bouftous                            ":
                            break;
                        case "aux dégâts contre les Tofus                            ":
                            break;
                        case "Quantité Récolte en Forestier                            ":
                            break;
                        case "Maîtrise CMC                            ":
                            break;
                        default:
                            Log.e("ERROR","Status não identificado:"+status);
                    }

                }
                return valores;
            }else if(lang.equals("es")){                    //######################################ES##################################
                while((aux = html.indexOf("title\">")) != -1) {
                    html = html.substring(aux + 27);
                    try {
                        if (html.indexOf("%") <= 4 && html.indexOf("%") != -1) {
                            num = Integer.parseInt(html.substring(0, html.indexOf("%")));
                        } else {
                            num = Integer.parseInt(html.substring(0, html.indexOf(" ")));
                        }
                    }catch (Exception e){
                        Log.e("Valores","Erro:"+e.getMessage());
                        num = 0;
                    }
                    String status = html.substring(html.indexOf(" ") + 1, html.indexOf("</div"));
                    switch (status) {
                        case "puntos de vida                            ":
                            valores.put("vitalpoint", num);
                            break;
                        case "placaje                            ":
                            valores.put("block", num);
                            break;
                        case "anticipación                            ":
                            valores.put("stop", num);
                            break;
                        case "Resistencia a 3 elementos aleatorios                            ":
                            valores.put("res3elerandom", num);
                            break;
                        case "esquiva                            ":
                            valores.put("evasion", num);
                            break;
                        case "dominio distancia                            ":
                            valores.put("distancedmg", num);
                            break;
                        case "Dominio de 3 elementos aleatorios                            ":
                            valores.put("element3dmg", num);
                            break;
                        case "resistencia elemental                            ":
                            valores.put("resist", num);
                            break;
                        case "resistencia crítica                            ":
                            valores.put("rescriticaldmg", num);
                            break;
                        case "resistencia por la espalda                            ":
                            valores.put("resbackdmg", num);
                            break;
                        case "PW                            ":
                            valores.put("wakfupoint", num);
                            break;
                        case "PA                            ":
                            valores.put("actionpoint", num);
                            break;
                        case "iniciativa                            ":
                            valores.put("iniciative", num);
                            break;
                        case "Dominio de 2 elementos aleatorios                            ":
                            valores.put("element2dmg", num);
                            break;
                        case "golpe crítico                            ":
                            valores.put("criticalchance", num);
                            break;
                        case "dominio cura                            ":
                            valores.put("heal", num);
                            break;
                        case "dominio crítico                            ":
                            valores.put("criticaldmg", num);
                            break;
                        case "alcance                            ":
                            valores.put("range", num);
                            break;
                        case "dominio espalda                            ":
                            valores.put("backdmg", num);
                            break;
                        case "PM                            ":
                            valores.put("movepoint", num);
                            break;
                        case "dominio monobjetivo                            ":
                            valores.put("onetargetdmg", num);
                            break;
                        case "dominio berserker                            ":
                            valores.put("beserkdmg", num);
                            break;
                        case "resistencia  Fuego                            ":
                            valores.put("resfire", num);
                            break;
                        case "resistencia  Agua                            ":
                            valores.put("reswater", num);
                            break;
                        case "resistencia  Tierra                            ":
                            valores.put("researth", num);
                            break;
                        case "puntos de vida JcJ                            ":
                            valores.put("pvphp", num);
                            break;
                        case "Resistencia a 1 elemento aleatorio                            ":
                            valores.put("res1elerandom", num);
                            break;
                        case "Resistencia a 2 elementos aleatorios                            ":
                            valores.put("res2elerandom", num);
                            break;
                        case "dominio cuerpo a cuerpo                            ":
                            valores.put("closecombatdmg", num);
                            break;
                        case "dominio zona                            ":
                            valores.put("areadmg", num);
                            break;
                        case "resistencia  Aire                            ":
                            valores.put("resair", num);
                            break;
                        case "Dominio de 1 elemento aleatorio                            ":
                            valores.put("element1dmg", num);
                            break;
                        case "Instrucción Militar                            ":
                            valores.put("arteequipar", num);
                            break;
                        case "dominio  Agua                            ":
                            valores.put("dmgagua", num);
                            break;
                        case "dominio  Tierra                            ":
                            valores.put("dmgterra", num);
                            break;
                        case "dominio  Fuego                            ":
                            valores.put("dmgfogo", num);
                            break;
                        case "dominio  Aire                            ":
                            valores.put("dmgar", num);
                            break;
                        case "resistencia PM                            ":
                            valores.put("respm", num);
                            break;
                        case "dominio elemental                            ":
                            valores.put("dmg", num);
                            break;
                        case "control                            ":
                            valores.put("control", num);
                            break;
                        case "prospección                            ":
                            valores.put("prosp", num);
                            break;
                        case "sabiduría                            ":
                            valores.put("sabedoria", num);
                            break;
                        case "niv. a hechizos  Tierra                            ":
                            valores.put("nvfterra", num);
                            break;
                        case "niv. a hechizos  Fuego                            ":
                            valores.put("nvffogo", num);
                            break;
                        case "niv. a hechizos elementales                            ":
                            valores.put("nvfgeral", num);
                            break;
                        case "niv. a hechizos  Aire                            ":
                            valores.put("nvfar", num);
                            break;
                        case "niv. a hechizos  Agua                            ":
                            valores.put("nvfagua", num);
                            break;
                        case "resistencia PA                            ":
                            valores.put("respa", num);
                            break;
                        case "cantidad de recolección en Minero                            ":
                            valores.put("minertake", num);
                            break;
                        case "de velocidad de desplazamiento                            ":
                            valores.put("movespeed", num);
                            break;
                        case "supresión PA                            ":
                            valores.put("dmgpa", num);
                            break;
                        case "supresión PM                            ":
                            valores.put("dmgpm", num);
                            break;
                        case "punto de vida                            ":
                            valores.put("vitalpoint", num);
                            break;
                        default:
                            Log.e("ERROR","Status não identificado:"+status);
                    }
                }
                return valores;
            }else{
                return valores;
            }
        }

        public void sendToBD(ItemAux itemAux, int num){
            String html = itemAux.getCod();
            try {
                valores.clear();
                int aux;
                valores.put("_id", itemAux.getId());
                valores.put("raridade", itemAux.getRarity());
                //codigo de imagem
                aux = html.indexOf("src=\"") + 5;
                html = html.substring(aux);
                valores.put("link", html.substring(0, html.indexOf("\"")));
                //nome
                aux = html.indexOf("name\">") + 6;
                html = html.substring(aux);
                valores.put("nome", html.substring(0, html.indexOf("<")));
                //tipo
                aux = html.indexOf("type\">") + 6;
                html = html.substring(aux);
                valores.put("tipo", getType(html.substring(0, html.indexOf("<")), MainActivity.user.getLang()));
                //nivel
                aux = html.indexOf(";") + 1;
                html = html.substring(aux);
                valores.put("nivel", html.substring(0, html.indexOf("<")));
                bd.bd.insert("item", null, getStatus(html, valores, MainActivity.user.getLang()));
                Log.e("BD","Item adicionado n°:"+num+" Item id:"+itemAux.getId());
            } catch (Exception a) {
                Log.e("BD","Erro no item numero:"+num+" Item id:"+itemAux.getId()+" Error: "+a.getMessage());
            }
        }

        public void sendToBD(Spell spell, int num){
            try {
                ContentValues valores = new ContentValues();
                valores.put("name", spell.getName());
                valores.put("basedmg", spell.getBasedmg());
                valores.put("area", spell.isArea());
                valores.put("classe", spell.getClasse());
                valores.put("active", spell.isActive());
                valores.put("linhadevisao", spell.isLinhadevisao());
                valores.put("linear", spell.isLinear());
                valores.put("level", spell.getLevel());
                valores.put("paused", spell.getPa_used());
                valores.put("pmused", spell.getPm_used());
                valores.put("pwused", spell.getWakfu_used());
                valores.put("rangeini", spell.getRange_ini());
                valores.put("rangeend", spell.getRange_end());
                valores.put("scale", spell.getScale());
                valores.put("rangemod", spell.isRange_mod());
                valores.put("description", spell.getDescription());
                valores.put("image", spell.getImage());
                valores.put("diagonal", spell.isDiagonal());
                for (int h = 0; h < spell.getConditions().size(); h++) {
                    valores.put("condition" + (h + 1), spell.getConditions().get(h));
                }
                for (int j = 0; j < spell.getLines().size(); j++) {
                    valores.put("line" + (j + 1), spell.getLines().get(j));
                }
                bd.bd.insert("spell", null, valores);
                Log.e("BD", "Spell adicionada n°:" + num + " Spell name:" + spell.getName());
            }catch (Exception e){
                Log.e("BD","Erro na spell numero:"+num+" Spell name:"+spell.getName()+" Error: "+e.getMessage());
            }
        }

        protected Boolean doInBackground(final ArrayList... list){
            ArrayList lista = list[0];
            items = (ArrayList<ItemAux>) lista.get(0);
            spells = (ArrayList<Spell>) lista.get(1);
            Log.e("ASYNC","Items:"+items.size()+"  Spells:"+spells.size());
            int s=0,i=0;
            for(; i<items.size();i++) {
                //String url = "https://www.wakfu.com/en/linker/item?l=" + MainActivity.user.getLang() + "&id=" + items.get(i).getId();
                try {
                    sendToBD(items.get(i),i);
                    int progress = (((i+s)*100)/(items.size()+spells.size()));
                    publishProgress(progress);
                }catch (Exception e) {
                    e.printStackTrace();
                    bd.bd.close();
                    return false;
                }
            }
            for(;s<spells.size();s++){
                try{
                    sendToBD(spells.get(s),s);
                    int progress = (((s+i)*100)/(items.size()+spells.size()));
                    publishProgress(progress);
                }catch (Exception e){
                    e.printStackTrace();
                    bd.bd.close();
                    return false;
                }
            }
            bd.bd.close();
            return true;
        }

        protected void onProgressUpdate(Integer... progress){
            dialog.setProgress(progress[0]);
        }

        protected void onPostExecute(Boolean list) {
            if(list){
                MainActivity.mDatabase.child("users/"+MainActivity.user.getUserID()).setValue(MainActivity.user);
            }else{
                Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
            }
            dialog.dismiss();
        }
    }
}
