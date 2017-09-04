package com.rodrigoad.rodso.wakfustats.Utils;

import android.content.Context;

/**
 * Created by rodso on 10/03/2017.
 */

public class ItemService {

    ItemDAO dao;

    public ItemService(Context context){
        dao = new ItemDAO(context);
    }

    public Item Buscar(String codigo){
        Item to = dao.BuscarItem(codigo);
        return to;
    }
}
