package com.rodrigoad.rodso.wakfustats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.rodrigoad.rodso.wakfustats.Layouts.ShareBuild;
import com.rodrigoad.rodso.wakfustats.Utils.BD;
import com.rodrigoad.rodso.wakfustats.Utils.Build;

public class ShareBuildAcitivy extends AppCompatActivity {

    private Build build;
    private RelativeLayout rl_share;
    private ShareBuild shareBuild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_build_acitivy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        String sBuild="";
        if(b!=null){
            sBuild = b.getString("codigo");
        }
        this.build = new Gson().fromJson(sBuild, Build.class);
        shareBuild = ShareBuild.newInstance("A","B");
        shareBuild.setBuild(build);

        rl_share = (RelativeLayout) findViewById(R.id.rl_share);

        getSupportFragmentManager().beginTransaction().add(rl_share.getId(), shareBuild).commit();
    }

}
