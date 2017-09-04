package com.rodrigoad.rodso.wakfustats.Utils;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rodso on 10/03/2017.
 */

public class ItemDAO{

    private BD bd;
    private Context context;
    private DatabaseReference database;

    public ItemDAO(Context context){
        bd = new BD(context);
        this.context = context;
        database = FirebaseDatabase.getInstance().getReference();
    }

    //busca
    public Item BuscarItem(String codigo){
        return bd.getItem(codigo);
    }
}
