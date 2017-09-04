package com.rodrigoad.rodso.wakfustats.Utils;

/**
 * Created by rodso on 08/08/2017.
 */

public class ItemAux {

    private String id;
    private String cod;
    private int rarity;

    public ItemAux(){}

    public ItemAux(String id,String cod, int rarity){
        this.id=id;
        this.cod=cod;
        this.rarity=rarity;
    }

    //gets
    public String getId(){return id;}
    public String getCod(){ return  cod; }
    public int getRarity(){return rarity;}

    //sets
    public void setId(String id){ this.id=id; }
    public void setCod(String cod){ this.cod=cod; }
    public void setRarity(int rarity){ this.rarity=rarity; }
}
