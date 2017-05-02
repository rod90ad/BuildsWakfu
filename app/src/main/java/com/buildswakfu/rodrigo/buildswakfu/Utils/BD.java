package com.buildswakfu.rodrigo.buildswakfu.Utils;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard;
import android.os.AsyncTask;
import android.util.Log;

import com.buildswakfu.rodrigo.buildswakfu.MainActivity;
import com.buildswakfu.rodrigo.buildswakfu.R;
import com.buildswakfu.rodrigo.buildswakfu.Utils.BDConn;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by rodso on 17/06/2016.
 */

public class BD {
    private SQLiteDatabase bd;
    private Context context;
    public int max=884;
    public int atual=0;

    public BD(Context ctx){
        context=ctx;
        BDConn auxbd = new BDConn(ctx);
        bd = auxbd.getWritableDatabase();
    }

    public void salvabuilds(Build build){
        ContentValues valores = new ContentValues();
        try {
            valores.put("nome", build.getNome());
            valores.put("nivel", build.getNivel());
            valores.put("heal", build.getHeal());
            valores.put("range", build.getRange());
            valores.put("beserkdmg", build.getBeserkdmg());
            valores.put("block", build.getBlock());
            valores.put("control", build.getControl());
            valores.put("dmg", build.getDmg());
            valores.put("criticalchance", build.getCriticalchance());
            valores.put("backdmg", build.getBackdmg());
            valores.put("distancedmg", build.getDistancedmg());
            valores.put("closecombatdmg", build.getClosecombatdmg());
            valores.put("element1dmg", build.getElement1dmg());
            valores.put("element2dmg", build.getElement2dmg());
            valores.put("element2dmg", build.getElement3dmg());
            valores.put("elementp", build.getElementp());
            valores.put("resistp", build.getResistp());
            valores.put("areadmg", build.getAreadmg());
            valores.put("onetargetdmg", build.getOnetargetdmg());
            valores.put("evasion", build.getEvasion());
            valores.put("criticaldmg", build.getCriticaldmg());
            valores.put("iniciative", build.getIniciative());
            valores.put("stop", build.getStop());
            valores.put("prosp", build.getProsp());
            valores.put("pwmax", build.getPwmax());
            valores.put("resist", build.getResist());
            valores.put("sabedoria", build.getSabedoria());
            valores.put("reswater", build.getReswater());
            valores.put("resfire", build.getResfire());
            valores.put("resair", build.getResair());
            valores.put("researth", build.getResearth());
            valores.put("resbackdmg", build.getResblackdmg());
            valores.put("rescriticaldmg", build.getRescriticaldmg());
            valores.put("res1elerandom", build.getRes1elerandom());
            valores.put("res2elerandom", build.getRes2elerandom());
            valores.put("res3elerandom", build.getRes3elerandom());
            valores.put("actionpoint", build.getActionpoint());
            valores.put("movepoint", build.getMovepoint());
            valores.put("vitalpoint", build.getVitalpoint());
            valores.put("wakfupoint", build.getWakfupoint());
            valores.put("status", "0");
            valores.put("pvphp", build.getPvphp());
            valores.put("arteequipar", build.getArteequipar());
            valores.put("nvfagua", build.getNvfagua());
            valores.put("nvffogo", build.getNvffogo());
            valores.put("nvfar", build.getNvfar());
            valores.put("nvfterra", build.getNvfterra());
            valores.put("nvfgeral", build.getNvfgeral());
            valores.put("movespeed", build.getMovespeed());
            valores.put("respm", build.getRespm());
            valores.put("respa", build.getRespa());
            valores.put("elmo", build.getElmo().getCodigo());
            valores.put("capa", build.getCapa().getCodigo());
            valores.put("peitoral", build.getPeitoral().getCodigo());
            valores.put("bota", build.getBota().getCodigo());
            valores.put("pet", build.getPet().getCodigo());
            valores.put("anel1", build.getAnel1().getCodigo());
            valores.put("anel2", build.getAnel2().getCodigo());
            valores.put("amuleto", build.getAmuleto().getCodigo());
            valores.put("insignia", build.getInsignia().getCodigo());
            valores.put("armamain", build.getArmamain().getCodigo());
            valores.put("armasec", build.getArmasec().getCodigo());
            valores.put("cinto", build.getCinto().getCodigo());
            valores.put("dragona", build.getDragona().getCodigo());
            valores.put("montaria", build.getMontaria().getCodigo());
            valores.put("classe", build.getClasse());
            if(build.getNivel()>=175){
                valores.put("especialpoints", 4);
            }else if(build.getNivel()>=125){
                valores.put("especialpoints", 3);
            }else if(build.getNivel()>=75){
                valores.put("especialpoints", 2);
            }else if(build.getNivel()>=25){
                valores.put("especialpoints", 1);
            }else{
                valores.put("especialpoints", 0);
            }
            if(build.getNivel()%4==0.25){
                valores.put("intpoints", build.getNivel()/4);
                valores.put("forcepoints", build.getNivel()/4);
                valores.put("agipoints", build.getNivel()/4);
                valores.put("luckpoints", build.getNivel()/4);
            }else if(build.getNivel()%4==0){
                valores.put("intpoints", (build.getNivel()/4));
                valores.put("forcepoints", (build.getNivel()/4));
                valores.put("agipoints", (build.getNivel()/4));
                if(build.getNivel()==200) {
                    valores.put("luckpoints", (build.getNivel() / 4));
                }else{
                    valores.put("luckpoints", (build.getNivel() / 4)-1);
                }
            }else if(build.getNivel()%4==0.75){
                valores.put("intpoints", (build.getNivel()/4)+1);
                valores.put("forcepoints", (build.getNivel()/4)+1);
                valores.put("agipoints", (build.getNivel()/4));
                valores.put("luckpoints", (build.getNivel()/4));
            }else if(build.getNivel()%4==0.5){
                valores.put("intpoints", (build.getNivel()/4)+1);
                valores.put("forcepoints", (build.getNivel()/4));
                valores.put("agipoints", (build.getNivel()/4));
                valores.put("luckpoints", (build.getNivel()/4));
            }
            valores.put("apinlifepercent", build.getApinlifepercent());
            valores.put("apinresele", build.getApinresele());
            valores.put("apinbarreira", build.getApinbarreira());
            valores.put("apinhealget", build.getApinhealrecived());
            valores.put("apinlifearmor", build.getApinlifearmor());
            valores.put("apingeral", build.getApingeral());
            valores.put("apinalvounico", build.getApinalvounico());
            valores.put("apinzona", build.getApinzona());
            valores.put("apincac", build.getApinCaC());
            valores.put("apindistance", build.getApindistance());
            valores.put("apinlife", build.getApinlife());
            valores.put("apinblock", build.getApinblock());
            valores.put("apinesquiva", build.getApinesquiva());
            valores.put("apinini", build.getApininiciativa());
            valores.put("apinblockandesquiva", build.getApinblockandesquiva());
            valores.put("apinremovepaepm", build.getApinremovepaandpm());
            valores.put("apinrespaepm", build.getApinrespmepm());
            valores.put("apingolpecritico", build.getApingolpecritico());
            valores.put("apinparada", build.getApinparada());
            valores.put("apindanocritico", build.getApindanocritico());
            valores.put("apinbackdmg", build.getApinbackdmg());
            valores.put("apinbeserkdmg", build.getApinbeserkdmg());
            valores.put("apinheal", build.getApinheal());
            valores.put("apinresbackdmg", build.getApinresbackdmg());
            valores.put("apincriticalres", build.getApincritialres());
            valores.put("apinactionpoint", build.getApinactionpoint());
            valores.put("apinmovepoint", build.getApinmovepoint());
            valores.put("apinrangeanddmg", build.getApinrangeanddmg());
            valores.put("apinwakfupoint", build.getApinwakfupoint());
            valores.put("apincontrolanddmg", build.getApincontrolanddmg());
            valores.put("apinarteequipar", build.getApinarteequipar());
            valores.put("apinfinaldmg", build.getApinfinalDamage());
            valores.put("apinreselemental", build.getApinreselemental());
            bd.insert("build", null, valores);
        }catch (Exception e) {
            System.out.println("Erro ao gravar a build: "+ e.getMessage());
        }
    }

    public void salvapontos(Build build) {
        ContentValues point = new ContentValues();
        try{
            point.put("especialpoints", build.getEspecialPoints());
            point.put("intpoints", build.getIntPoints());
            point.put("forcepoints", build.getForcePoints());
            point.put("agipoints", build.getAgiPoints());
            point.put("luckpoints", build.getLuckPoints());
            point.put("apinlifepercent", build.getApinlifepercent());
            point.put("apinresele", build.getApinresele());
            point.put("apinbarreira", build.getApinbarreira());
            point.put("apinhealget", build.getApinhealrecived());
            point.put("apinlifearmor", build.getApinlifearmor());
            point.put("apingeral", build.getApingeral());
            point.put("apinalvounico", build.getApinalvounico());
            point.put("apinzona", build.getApinzona());
            point.put("apincac", build.getApinCaC());
            point.put("apindistance", build.getApindistance());
            point.put("apinlife", build.getApinlife());
            point.put("apinblock", build.getApinblock());
            point.put("apinesquiva", build.getApinesquiva());
            point.put("apinini", build.getApininiciativa());
            point.put("apinblockandesquiva", build.getApinblockandesquiva());
            point.put("apinremovepaepm", build.getApinremovepaandpm());
            point.put("apinrespaepm", build.getApinrespmepm());
            point.put("apingolpecritico", build.getApingolpecritico());
            point.put("apinparada", build.getApinparada());
            point.put("apindanocritico", build.getApindanocritico());
            point.put("apinbackdmg", build.getApinbackdmg());
            point.put("apinbeserkdmg", build.getApinbeserkdmg());
            point.put("apinheal", build.getApinheal());
            point.put("apinresbackdmg", build.getApinresbackdmg());
            point.put("apincriticalres", build.getApincritialres());
            point.put("apinactionpoint", build.getApinactionpoint());
            point.put("apinmovepoint", build.getApinmovepoint());
            point.put("apinrangeanddmg", build.getApinrangeanddmg());
            point.put("apinwakfupoint", build.getApinwakfupoint());
            point.put("apincontrolanddmg", build.getApincontrolanddmg());
            point.put("apinarteequipar", build.getApinarteequipar());
            point.put("apinfinaldmg", build.getApinfinalDamage());
            point.put("apinreselemental", build.getApinreselemental());
            bd.update("build",point, "_id="+build.getCodigo(), null);
        } catch (Exception e) {
            System.out.println("Erro ao gravar pontos: "+e.getMessage());
        }
    }

    public void salvaBuild(Build build){
        salvabuilds(build);
        bd.close();
    }

    public Item getItem(String link){
        Cursor cursor;
        Item item = new Item();
        String[] where = {link};
        String[] colunas = {"*"};
        cursor = bd.query("item",colunas,"_id=?",where,null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                item.setCodigo(cursor.getInt(0));
                item.setTipo(cursor.getString(1));
                item.setNome(cursor.getString(2));
                item.setNivel(cursor.getInt(3));
                item.setRaridade(cursor.getInt(4));
                item.setHeal(cursor.getInt(5));
                item.setRange(cursor.getInt(6));
                item.setBeserkdmg(cursor.getInt(7));
                item.setBlock(cursor.getInt(8));
                item.setControl(cursor.getInt(9));
                item.setDmg(cursor.getInt(10));
                item.setCriticalchance(cursor.getInt(11));
                item.setBackdmg(cursor.getInt(12));
                item.setDistancedmg(cursor.getInt(13));
                item.setClosecombatdmg(cursor.getInt(14));
                item.setElement1dmg(cursor.getInt(15));
                item.setElement2dmg(cursor.getInt(16));
                item.setElement3dmg(cursor.getInt(17));
                item.setAreadmg(cursor.getInt(18));
                item.setOnetargetdmg(cursor.getInt(19));
                item.setEvasion(cursor.getInt(20));
                item.setCriticaldmg(cursor.getInt(21));
                item.setIniciative(cursor.getInt(22));
                item.setStop(cursor.getInt(23));
                item.setProsp(cursor.getInt(24));
                item.setPwmax(cursor.getInt(25));
                item.setResist(cursor.getInt(26));
                item.setSabedoria(cursor.getInt(27));
                item.setReswater(cursor.getInt(28));
                item.setResfire(cursor.getInt(29));
                item.setResair(cursor.getInt(30));
                item.setResearth(cursor.getInt(31));
                item.setResblackdmg(cursor.getInt(32));
                item.setRescriticaldmg(cursor.getInt(33));
                item.setRes1elerandom(cursor.getInt(34));
                item.setRes2elerandom(cursor.getInt(35));
                item.setRes3elerandom(cursor.getInt(36));
                item.setActionpoint(cursor.getInt(37));
                item.setMovepoint(cursor.getInt(38));
                item.setVitalpoint(cursor.getInt(39));
                item.setWakfupoint(cursor.getInt(40));
                item.setStatus(cursor.getString(41));
                item.setLink(cursor.getString(42));
                item.setPvphp(cursor.getInt(43));
                item.setSingular(cursor.getInt(44));
                item.setArteequipar(cursor.getInt(45));
                item.setNvfagua(cursor.getInt(46));
                item.setNvffogo(cursor.getInt(47));
                item.setNvfar(cursor.getInt(48));
                item.setNvfterra(cursor.getInt(49));
                item.setNvfgeral(cursor.getInt(50));
                item.setMovespeed(cursor.getInt(51));
                item.setRespm(cursor.getInt(52));
                item.setRespa(cursor.getInt(53));
                item.setDmgfogo(cursor.getInt(54));
                item.setDmgterra(cursor.getInt(55));
                item.setDmgagua(cursor.getInt(56));
                item.setDmgar(cursor.getInt(57));
                item.setMinertake(cursor.getInt(58));
            }while(cursor.moveToNext());
        }else{
            item = null;
        }
        return item;
    }

    public void updateItem(String coluna, Item item, Build build){
        String[] where = {""+build.getCodigo()};
        ContentValues values = new ContentValues();
        values.put(coluna, item.getCodigo());
        bd.update("build", values, "_id=?", where);
        //System.out.println("fez o update: \n info:\n link novo:" + item.getLink() + "\n Nome do item:" + item.getNome());
        bd.close();
    }

    public boolean verificaBuild(String nome){
        Cursor cursor;
        String[] colunas = {"nome"};
        String[] nomes = {nome};
        cursor = bd.query("build", colunas,"nome=? COLLATE NOCASE",nomes,null,null,null);
        Log.e("tamanho do cursor:",cursor.getCount()+"");
        if(cursor.getCount()!=0){
            return false;
        }
        bd.close();
        return true;
    }

    public Build getBuild(int codigo){
        Build build = new Build();
        String[] where={""+codigo};
        Cursor cursor;
        String[] colunas = {"*"};
        cursor = bd.query("build", colunas, "_id=?", where, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            build.setCodigo(cursor.getInt(0));
            build.setNome(cursor.getString(1));
            build.setNivel(cursor.getInt(2));
            build.setHeal(cursor.getInt(3));
            build.setRange(cursor.getInt(4));
            build.setBeserkdmg(cursor.getInt(5));
            build.setBlock(cursor.getInt(6));
            build.setControl(cursor.getInt(7));
            build.setDmg(cursor.getInt(8));
            build.setCriticalchance(cursor.getInt(9));
            build.setBackdmg(cursor.getInt(10));
            build.setDistancedmg(cursor.getInt(11));
            build.setClosecombatdmg(cursor.getInt(12));
            build.setElement1dmg(cursor.getInt(13));
            build.setElement2dmg(cursor.getInt(14));
            build.setElement3dmg(cursor.getInt(15));
            build.setAreadmg(cursor.getInt(16));
            build.setOnetargetdmg(cursor.getInt(17));
            build.setEvasion(cursor.getInt(18));
            build.setCriticaldmg(cursor.getInt(19));
            build.setIniciative(cursor.getInt(20));
            build.setStop(cursor.getInt(21));
            build.setProsp(cursor.getInt(22));
            build.setPwmax(cursor.getInt(23));
            build.setResist(cursor.getInt(24));
            build.setSabedoria(cursor.getInt(25));
            build.setReswater(cursor.getInt(26));
            build.setResfire(cursor.getInt(27));
            build.setResair(cursor.getInt(28));
            build.setResearth(cursor.getInt(29));
            build.setResblackdmg(cursor.getInt(30));
            build.setRescriticaldmg(cursor.getInt(31));
            build.setRes1elerandom(cursor.getInt(32));
            build.setRes2elerandom(cursor.getInt(33));
            build.setRes3elerandom(cursor.getInt(34));
            build.setActionpoint(cursor.getInt(35));
            build.setMovepoint(cursor.getInt(36));
            build.setVitalpoint(cursor.getInt(37));
            build.setWakfupoint(cursor.getInt(38));
            //build.setStatus(cursor.getString(39));
            build.setPvphp(cursor.getInt(40));
            build.setArteequipar(cursor.getInt(41));
            build.setNvfagua(cursor.getInt(42));
            build.setNvffogo(cursor.getInt(43));
            build.setNvfar(cursor.getInt(44));
            build.setNvfterra(cursor.getInt(45));
            build.setNvfgeral(cursor.getInt(46));
            build.setMovespeed(cursor.getInt(47));
            build.setRespm(cursor.getInt(48));
            build.setRespa(cursor.getInt(49));
            //System.out.println("Build: "+build.getNome());
            build.setElmoBD( cursor.getInt(50)==0 ? new Item() : getItem(""+cursor.getInt(50)));
            //System.out.println("Elmo code: " + cursor.getInt(50));
            build.setCapaBD( cursor.getInt(51)==0 ? new Item() : getItem(""+cursor.getInt(51)));
            //System.out.println("Capa code: " + cursor.getInt(51));
            build.setPeitoralBD( cursor.getInt(52)==0 ? new Item() : getItem("" + cursor.getInt(52)));
            //System.out.println("Peitoral code: " + cursor.getInt(52));
            build.setBotaBD( cursor.getInt(53)==0 ? new Item() : getItem("" + cursor.getInt(53)));
            //System.out.println("Bota code: " + cursor.getInt(53));
            build.setPetBD( cursor.getInt(54)==0 ? new Item() : getItem("" + cursor.getInt(54)));
            //System.out.println("Pet code: " + cursor.getInt(54));
            build.setAnel1BD( cursor.getInt(55)==0 ? new Item() : getItem("" + cursor.getInt(55)));
            //System.out.println("Anel1 code: " + cursor.getInt(55));
            build.setAnel2BD( cursor.getInt(56)==0 ? new Item() : getItem("" + cursor.getInt(56)));
            //System.out.println("Anel2 code: " + cursor.getInt(56));
            build.setAmuletoBD( cursor.getInt(57)==0 ? new Item() : getItem("" + cursor.getInt(57)));
            //System.out.println("Amuleto code: " + cursor.getInt(57));
            build.setInsigniaBD( cursor.getInt(58)==0 ? new Item() : getItem("" + cursor.getInt(58)));
            //System.out.println("Insignia code: " + cursor.getInt(58));
            build.setArmamainBD( cursor.getInt(59)==0 ? new Item() : getItem("" + cursor.getInt(59)));
            //System.out.println("Arma Main code: " + cursor.getInt(59));
            build.setArmasecBD( cursor.getInt(60)==0 ? new Item() : getItem("" + cursor.getInt(60)));
            //System.out.println("Arma Sec code: " + cursor.getInt(60));
            build.setCintoBD( cursor.getInt(61)==0 ? new Item() : getItem("" + cursor.getInt(61)));
            //System.out.println("Cinto code: " + cursor.getInt(61));
            build.setDragonaBD( cursor.getInt(62)==0 ? new Item() : getItem("" + cursor.getInt(62)));
            //System.out.println("Dragona code: " + cursor.getInt(62));
            build.setMontariaBD( cursor.getInt(63)==0 ? new Item() : getItem("" + cursor.getInt(63)));
            //System.out.println("Montaria code: " + cursor.getInt(63));
            build.setClasse(cursor.getInt(64));
            build.setElementp(cursor.getInt(65));
            build.setResistp(cursor.getInt(66));
            build.setIntPoints(cursor.getInt(67));
            build.setApinlifepercent(cursor.getInt(68));
            build.setApinresele(cursor.getInt(69));
            build.setApinbarreira(cursor.getInt(70));
            build.setApinhealrecived(cursor.getInt(71));
            build.setApinlifearmor(cursor.getInt(72));
            build.setForcePoints(cursor.getInt(73));
            build.setApingeral(cursor.getInt(74));
            build.setApinalvounico(cursor.getInt(75));
            build.setApinzona(cursor.getInt(76));
            build.setApinCaC(cursor.getInt(77));
            build.setApindistance(cursor.getInt(78));
            build.setApinlife(cursor.getInt(79));
            build.setAgiPoints(cursor.getInt(80));
            build.setApinblock(cursor.getInt(81));
            build.setApinesquiva(cursor.getInt(82));
            build.setApininiciativa(cursor.getInt(83));
            build.setApinblockandesquiva(cursor.getInt(84));
            build.setApinremovepaandpm(cursor.getInt(85));
            build.setApinrespmepm(cursor.getInt(86));
            build.setLuckPoints(cursor.getInt(87));
            build.setApingolpecritico(cursor.getInt(88));
            build.setApinparada(cursor.getInt(89));
            build.setApindanocritico(cursor.getInt(90));
            build.setApinbackdmg(cursor.getInt(91));
            build.setApinbeserkdmg(cursor.getInt(92));
            build.setApinheal(cursor.getInt(93));
            build.setApinresbackdmg(cursor.getInt(94));
            build.setApincritialres(cursor.getInt(95));
            build.setEspecialPoints(cursor.getInt(96));
            build.setApinactionpoint(cursor.getInt(97));
            build.setApinmovepoint(cursor.getInt(98));
            build.setApinrangeanddmg(cursor.getInt(99));
            build.setApinwakfupoint(cursor.getInt(100));
            build.setApincontrolanddmg(cursor.getInt(101));
            build.setApinarteequipar(cursor.getInt(102));
            build.setApinfinalDamage(cursor.getInt(103));
            build.setApinreselemental(cursor.getInt(104));
        }
        return build;
    }

    public ArrayList<Build> getBuilds(){
        ArrayList<Build> builds = new ArrayList<Build>();
        Cursor cursor;
        String[] colunas = {"*"};
        cursor = bd.query("build", colunas, null, null, null, null, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Build build = new Build();
                build.setCodigo(cursor.getInt(0));
                build.setNome(cursor.getString(1));
                build.setNivel(cursor.getInt(2));
                build.setHeal(cursor.getInt(3));
                build.setRange(cursor.getInt(4));
                build.setBeserkdmg(cursor.getInt(5));
                build.setBlock(cursor.getInt(6));
                build.setControl(cursor.getInt(7));
                build.setDmg(cursor.getInt(8));
                build.setCriticalchance(cursor.getInt(9));
                build.setBackdmg(cursor.getInt(10));
                build.setDistancedmg(cursor.getInt(11));
                build.setClosecombatdmg(cursor.getInt(12));
                build.setElement1dmg(cursor.getInt(13));
                build.setElement2dmg(cursor.getInt(14));
                build.setElement3dmg(cursor.getInt(15));
                build.setAreadmg(cursor.getInt(16));
                build.setOnetargetdmg(cursor.getInt(17));
                build.setEvasion(cursor.getInt(18));
                build.setCriticaldmg(cursor.getInt(19));
                build.setIniciative(cursor.getInt(20));
                build.setStop(cursor.getInt(21));
                build.setProsp(cursor.getInt(22));
                build.setPwmax(cursor.getInt(23));
                build.setResist(cursor.getInt(24));
                build.setSabedoria(cursor.getInt(25));
                build.setReswater(cursor.getInt(26));
                build.setResfire(cursor.getInt(27));
                build.setResair(cursor.getInt(28));
                build.setResearth(cursor.getInt(29));
                build.setResblackdmg(cursor.getInt(30));
                build.setRescriticaldmg(cursor.getInt(31));
                build.setRes1elerandom(cursor.getInt(32));
                build.setRes2elerandom(cursor.getInt(33));
                build.setRes3elerandom(cursor.getInt(34));
                build.setActionpoint(cursor.getInt(35));
                build.setMovepoint(cursor.getInt(36));
                build.setVitalpoint(cursor.getInt(37));
                build.setWakfupoint(cursor.getInt(38));
                //build.setStatus(cursor.getString(39));
                build.setPvphp(cursor.getInt(40));
                build.setArteequipar(cursor.getInt(41));
                build.setNvfagua(cursor.getInt(42));
                build.setNvffogo(cursor.getInt(43));
                build.setNvfar(cursor.getInt(44));
                build.setNvfterra(cursor.getInt(45));
                build.setNvfgeral(cursor.getInt(46));
                build.setMovespeed(cursor.getInt(47));
                build.setRespm(cursor.getInt(48));
                build.setRespa(cursor.getInt(49));
                //System.out.println("Build: "+build.getNome());
                build.setElmoBD( cursor.getInt(50)==0 ? new Item() : getItem(""+cursor.getInt(50)));
                //System.out.println("Elmo code: " + cursor.getInt(50));
                build.setCapaBD( cursor.getInt(51)==0 ? new Item() : getItem(""+cursor.getInt(51)));
                //System.out.println("Capa code: " + cursor.getInt(51));
                build.setPeitoralBD( cursor.getInt(52)==0 ? new Item() : getItem("" + cursor.getInt(52)));
                //System.out.println("Peitoral code: " + cursor.getInt(52));
                build.setBotaBD( cursor.getInt(53)==0 ? new Item() : getItem("" + cursor.getInt(53)));
                //System.out.println("Bota code: " + cursor.getInt(53));
                build.setPetBD( cursor.getInt(54)==0 ? new Item() : getItem("" + cursor.getInt(54)));
                //System.out.println("Pet code: " + cursor.getInt(54));
                build.setAnel1BD( cursor.getInt(55)==0 ? new Item() : getItem("" + cursor.getInt(55)));
                //System.out.println("Anel1 code: " + cursor.getInt(55));
                build.setAnel2BD( cursor.getInt(56)==0 ? new Item() : getItem("" + cursor.getInt(56)));
                //System.out.println("Anel2 code: " + cursor.getInt(56));
                build.setAmuletoBD( cursor.getInt(57)==0 ? new Item() : getItem("" + cursor.getInt(57)));
                //System.out.println("Amuleto code: " + cursor.getInt(57));
                build.setInsigniaBD( cursor.getInt(58)==0 ? new Item() : getItem("" + cursor.getInt(58)));
                //System.out.println("Insignia code: " + cursor.getInt(58));
                build.setArmamainBD( cursor.getInt(59)==0 ? new Item() : getItem("" + cursor.getInt(59)));
                //System.out.println("Arma Main code: " + cursor.getInt(59));
                build.setArmasecBD( cursor.getInt(60)==0 ? new Item() : getItem("" + cursor.getInt(60)));
                //System.out.println("Arma Sec code: " + cursor.getInt(60));
                build.setCintoBD( cursor.getInt(61)==0 ? new Item() : getItem("" + cursor.getInt(61)));
                //System.out.println("Cinto code: " + cursor.getInt(61));
                build.setDragonaBD( cursor.getInt(62)==0 ? new Item() : getItem("" + cursor.getInt(62)));
                //System.out.println("Dragona code: " + cursor.getInt(62));
                build.setMontariaBD( cursor.getInt(63)==0 ? new Item() : getItem("" + cursor.getInt(63)));
                //System.out.println("Montaria code: " + cursor.getInt(63));
                build.setClasse(cursor.getInt(64));
                build.setElementp(cursor.getInt(65));
                build.setResistp(cursor.getInt(66));
                builds.add(build);
            }while (cursor.moveToNext());
        }
        bd.close();
        return builds;
    }

    public void deleteBuild(int ID){
        bd.delete("build","_id ="+ID,null);
        bd.close();
    }

    public ArrayList<Item> getItens(String[] tipo, Boolean pvp){
        ArrayList<Item> li = new ArrayList<Item>();
        Cursor cursor;
        String where = "nivel >= ? and nivel <= ?";
        String[] colunas = {"*"};
        ArrayList<String> auxtipo = new ArrayList<String>();
        auxtipo.add(tipo[0]);
        auxtipo.add(tipo[1]);
        if(!tipo[4].equals("all")) {
            switch (tipo[4]) {
                case "pa":
                    where+=" and actionpoint != 0";
                    break;
                case "pm":
                    where+=" and movepoint != 0";
                    break;
                case "range":
                    where+=" and range != 0";
                    break;
                case "control":
                    where+=" and control != 0";
                    break;
                case "pv":
                    where+=" and vitalpoint != 0";
                    break;
                case "dmg":
                    where+=" and dmg != 0";
                    break;
                case "heal":
                    where+=" and heal != 0";
                    break;
                case "block":
                    where+=" and block != 0";
                    break;
                case "criticalchance":
                    where+=" and criticalchance != 0";
                    break;
                case "backdmg":
                    where+=" and backdmg != 0";
                    break;
                case "distancedmg":
                    where+=" and distancedmg != 0";
                    break;
                case "closecombat":
                    where+=" and closecombatdmg != 0";
                    break;
                case "aoedmg":
                    where+=" and areadmg != 0";
                    break;
                case "onetargetdmg":
                    where+=" and onetargetdmg != 0";
                    break;
                case "dmg1ele":
                    where+=" and element1dmg != 0";
                    break;
                case "dmg2ele":
                    where+=" and element2dmg != 0";
                    break;
                case "dmg3ele":
                    where+=" and element3dmg != 0";
                    break;
                case "dmgw":
                    where+=" and dmgagua != 0";
                    break;
                case "dmgf":
                    where+=" and dmgfogo != 0";
                    break;
                case "dmge":
                    where+=" and dmgterra != 0";
                    break;
                case "dmga":
                    where+=" and dmgar != 0";
                    break;
                case "esquiva":
                    where+=" and evasion != 0";
                    break;
                case "dc":
                    where+=" and criticaldmg != 0";
                    break;
                case "ini":
                    where+=" and iniciative != 0";
                    break;
                case "stop":
                    where+=" and stop != 0";
                    break;
                case "prosp":
                    where+=" and prosp != 0";
                    break;
                case "sab":
                    where+=" and sabedoria != 0";
                    break;
                case "pwmax":
                    where+=" and pwmax != 0";
                    break;
                case "res":
                    where+=" and resist != 0";
                    break;
                case "res1eler":
                    where+=" and res1elerandom != 0";
                    break;
                case "res2eler":
                    where+=" and res2elerandom != 0";
                    break;
                case "res3eler":
                    where+=" and res3elerandom != 0";
                    break;
                case "resw":
                    where+=" and reswater != 0";
                    break;
                case "resf":
                    where+=" and resfire != 0";
                    break;
                case "rese":
                    where+=" and researth != 0";
                    break;
                case "resa":
                    where+=" and resair != 0";
                    break;
                case "resbd":
                    where+=" and resbackdmg != 0";
                    break;
                case "rescd":
                    where+=" and rescriticaldmg != 0";
                    break;
                case "respm":
                    where+=" and respm != 0";
                    break;
                case "respa":
                    where+=" and respa != 0";
                    break;
                case "wp":
                    where+=" and wakfupoint != 0";
                    break;
                case "sts":
                    where+=" and status != 0";
                    break;
                case "pvphp":
                    where+=" and pvphp != 0";
                    break;
                case "arteequipar":
                    where+=" and arteequipar != 0";
                    break;
                case "nvfw":
                    where+=" and nvfagua != 0";
                    break;
                case "nvff":
                    where+=" and nvffogo != 0";
                    break;
                case "nvfe":
                    where+=" and nvfterra != 0";
                    break;
                case "nvfa":
                    where+=" and nvfar != 0";
                    break;
                case "nvfg":
                    where+=" and nvfgeral != 0";
                    break;
                case "movespeed":
                    where+=" and movespeed != 0";
                    break;
            }
        }
        if(!tipo[2].equals("all")){
            where+=" and raridade = ?";
            auxtipo.add(tipo[2]);
        }
        if(!tipo[3].equals("all")){
            where+=" and tipo = ?";
            auxtipo.add(tipo[3]);
        }
        String[] realTipo = new String[auxtipo.size()];
        for(int i=0; i<auxtipo.size();i++) {
            realTipo[i] = auxtipo.get(i);
        }
        if(pvp){
            where+=" and raridade != 8";
        }
        cursor = bd.query("item", colunas, where , realTipo, null, null, "nivel asc");
        if (cursor.getCount() >0){
            cursor.moveToFirst();
            do{
                Item item = new Item();
                item.setCodigo(cursor.getInt(0));
                item.setTipo(cursor.getString(1));
                item.setNome(cursor.getString(2));
                item.setNivel(cursor.getInt(3));
                item.setRaridade(cursor.getInt(4));
                item.setHeal(cursor.getInt(5));
                item.setRange(cursor.getInt(6));
                item.setBeserkdmg(cursor.getInt(7));
                item.setBlock(cursor.getInt(8));
                item.setControl(cursor.getInt(9));
                item.setDmg(cursor.getInt(10));
                item.setCriticalchance(cursor.getInt(11));
                item.setBackdmg(cursor.getInt(12));
                item.setDistancedmg(cursor.getInt(13));
                item.setClosecombatdmg(cursor.getInt(14));
                item.setElement1dmg(cursor.getInt(15));
                item.setElement2dmg(cursor.getInt(16));
                item.setElement3dmg(cursor.getInt(17));
                item.setAreadmg(cursor.getInt(18));
                item.setOnetargetdmg(cursor.getInt(19));
                item.setEvasion(cursor.getInt(20));
                item.setCriticaldmg(cursor.getInt(21));
                item.setIniciative(cursor.getInt(22));
                item.setStop(cursor.getInt(23));
                item.setProsp(cursor.getInt(24));
                item.setPwmax(cursor.getInt(25));
                item.setResist(cursor.getInt(26));
                item.setSabedoria(cursor.getInt(27));
                item.setReswater(cursor.getInt(28));
                item.setResfire(cursor.getInt(29));
                item.setResair(cursor.getInt(30));
                item.setResearth(cursor.getInt(31));
                item.setResblackdmg(cursor.getInt(32));
                item.setRescriticaldmg(cursor.getInt(33));
                item.setRes1elerandom(cursor.getInt(34));
                item.setRes2elerandom(cursor.getInt(35));
                item.setRes3elerandom(cursor.getInt(36));
                item.setActionpoint(cursor.getInt(37));
                item.setMovepoint(cursor.getInt(38));
                item.setVitalpoint(cursor.getInt(39));
                item.setWakfupoint(cursor.getInt(40));
                item.setStatus(cursor.getString(41));
                item.setLink(cursor.getString(42));
                item.setPvphp(cursor.getInt(43));
                item.setSingular(cursor.getInt(44));
                item.setArteequipar(cursor.getInt(45));
                item.setNvfagua(cursor.getInt(46));
                item.setNvffogo(cursor.getInt(47));
                item.setNvfar(cursor.getInt(48));
                item.setNvfterra(cursor.getInt(49));
                item.setNvfgeral(cursor.getInt(50));
                item.setMovespeed(cursor.getInt(51));
                item.setRespm(cursor.getInt(52));
                item.setRespa(cursor.getInt(53));
                item.setDmgfogo(cursor.getInt(54));
                item.setDmgterra(cursor.getInt(55));
                item.setDmgagua(cursor.getInt(56));
                item.setDmgar(cursor.getInt(57));
                item.setMinertake(cursor.getInt(58));
                li.add(item);
            }while(cursor.moveToNext());
        }
        bd.close();
        return li;
    }

    private class PopularBDAsync extends AsyncTask<ArrayList<Item>,Void,Boolean> {

        private ArrayList<Item> items = new ArrayList<Item>();

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            MainActivity.load.show();
            bd.execSQL("drop table item");
            bd.execSQL("create table item(" +
                    "_id integer primary key autoincrement," +  //id
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
                    "minertake int);");                         // coleta de mineiro
        }

        protected Boolean doInBackground(ArrayList<Item>... itens){
            ArrayList<Item> items = itens[0];
            ContentValues valores = new ContentValues();
            try {
                for(int i=0; i<items.size();i++) {
                    valores.put("nome", items.get(i).getNome());
                    valores.put("link", items.get(i).getLink());
                    valores.put("tipo", items.get(i).getTipo());
                    valores.put("raridade", items.get(i).getRaridade());
                    valores.put("nivel", items.get(i).getNivel());
                    valores.put("actionpoint", items.get(i).getActionpoint());
                    valores.put("movepoint", items.get(i).getMovepoint());
                    valores.put("range", items.get(i).getRange());
                    valores.put("control", items.get(i).getControl());
                    valores.put("vitalpoint", items.get(i).getVitalpoint());
                    valores.put("dmg", items.get(i).getDmg());
                    valores.put("heal", items.get(i).getHeal());
                    valores.put("block", items.get(i).getBlock());
                    valores.put("beserkdmg", items.get(i).getBeserkdmg());
                    valores.put("criticalchance", items.get(i).getCriticalchance());
                    valores.put("backdmg", items.get(i).getBackdmg());
                    valores.put("distancedmg", items.get(i).getDistancedmg());
                    valores.put("closecombatdmg", items.get(i).getClosecombatdmg());
                    valores.put("element1dmg", items.get(i).getElement1dmg());
                    valores.put("element2dmg", items.get(i).getElement2dmg());
                    valores.put("element3dmg", items.get(i).getElement3dmg());
                    valores.put("areadmg", items.get(i).getAreadmg());
                    valores.put("onetargetdmg", items.get(i).getOnetargetdmg());
                    valores.put("evasion", items.get(i).getEvasion());
                    valores.put("criticaldmg", items.get(i).getCriticaldmg());
                    valores.put("iniciative", items.get(i).getIniciative());
                    valores.put("stop", items.get(i).getStop());
                    valores.put("prosp", items.get(i).getProsp());
                    valores.put("pwmax", items.get(i).getPwmax());
                    valores.put("resist", items.get(i).getResist());
                    valores.put("sabedoria", items.get(i).getSabedoria());
                    valores.put("reswater", items.get(i).getReswater());
                    valores.put("resfire", items.get(i).getResfire());
                    valores.put("resair", items.get(i).getResair());
                    valores.put("researth", items.get(i).getResearth());
                    valores.put("resbackdmg", items.get(i).getResblackdmg());
                    valores.put("rescriticaldmg", items.get(i).getRescriticaldmg());
                    valores.put("res1elerandom", items.get(i).getRes1elerandom());
                    valores.put("res2elerandom", items.get(i).getRes2elerandom());
                    valores.put("res3elerandom", items.get(i).getRes3elerandom());
                    valores.put("wakfupoint", items.get(i).getWakfupoint());
                    valores.put("status", items.get(i).getStatus());
                    valores.put("pvphp", items.get(i).getPvphp());
                    valores.put("singular", items.get(i).getSingular());
                    valores.put("arteequipar", items.get(i).getArteequipar());
                    valores.put("nvfagua", items.get(i).getNvfagua());
                    valores.put("nvfar", items.get(i).getNvfar());
                    valores.put("nvffogo", items.get(i).getNvffogo());
                    valores.put("nvfterra", items.get(i).getNvfterra());
                    valores.put("nvfgeral", items.get(i).getNvfgeral());
                    valores.put("movespeed", items.get(i).getMovespeed());
                    valores.put("respm", items.get(i).getRespm());
                    valores.put("respa", items.get(i).getRespa());
                    valores.put("dmgfogo", items.get(i).getDmgfogo());
                    valores.put("dmgterra", items.get(i).getDmgterra());
                    valores.put("dmgagua", items.get(i).getDmgagua());
                    valores.put("dmgar", items.get(i).getDmgar());
                    valores.put("minertake", items.get(i).getMinertake());
                    bd.insert("item", null, valores);
                }
            } catch (Exception e) {
                System.out.println("Algum Erro Ocorreu: "+e.getMessage());
                return false;
            }
            bd.close();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean imageView) {
            if(imageView==true) {
            }else{
                Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            MainActivity.load.dismiss();
        }
    }

    public void popularBD(ArrayList<Item> items){
       new PopularBDAsync().execute(items);
    }

    private class PopularBDAsyncFirebase extends AsyncTask<InputStream,Integer,Boolean> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            MainActivity.load.show();
        }

        protected Boolean doInBackground(InputStream... files){
            Item valores = new Item();
            try {
                // Create a workbook using the File System
                HSSFWorkbook myWorkBook = new HSSFWorkbook(files[0]);
                // Get the first sheet from workbook
                HSSFSheet mySheet = myWorkBook.getSheetAt(0);
                Iterator<Row> rowIter = mySheet.rowIterator();
                rowIter.next();
                int i=0;
                while (rowIter.hasNext()) {
                    Row row = rowIter.next();
                    Iterator<Cell> cellIter = row.cellIterator();
                    Cell cell = cellIter.next();
                    valores.setNome(cell.toString());
                    valores.setCodigo(i);
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setLink(cell.toString());
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setTipo(cell.toString());
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setRaridade(Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setNivel(Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setActionpoint(Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setMovepoint(Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setRange( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setControl( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setVitalpoint( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setDmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setHeal( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setBlock( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setBeserkdmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setCriticalchance( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setBackdmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setDistancedmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setClosecombatdmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setElement1dmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setElement2dmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setElement3dmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setAreadmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setOnetargetdmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setEvasion( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setCriticaldmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setIniciative( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setStop( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setProsp( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setPwmax( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setResist( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setSabedoria( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setReswater( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setResfire( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setResair( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setResearth( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setResblackdmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setRescriticaldmg( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setRes1elerandom( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setRes2elerandom( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setRes3elerandom( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setWakfupoint( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setStatus(cell.toString());
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setPvphp( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setSingular( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setArteequipar( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setNvfagua( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setNvfar( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setNvffogo( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setNvfterra( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setNvfgeral( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setMovespeed( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setRespm( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setRespa( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setDmgfogo( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setDmgterra( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setDmgagua( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setDmgar( Integer.parseInt(cell.toString()));
                    cell = cellIter.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    valores.setMinertake( Integer.parseInt(cell.toString()));
                    MainActivity.mDatabase.child("Items-es/"+i).setValue(valores);
                    i++;
                }
            } catch (Exception e) {
                System.out.println("Erro ao tentar ler o arquivo"+e.getMessage());
            }
            return true;
        }

        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean imageView) {
            if(imageView==true) {
            }else{
                Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            //mProgressDialog.dismiss();
            MainActivity.load.dismiss();
        }

    }

    public void popularBDFirebase(InputStream file){
        new PopularBDAsyncFirebase().execute(file);
    }



}


