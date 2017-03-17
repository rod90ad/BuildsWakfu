package com.buildswakfu.rodrigo.buildswakfu.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rodso on 17/06/2016.
 */

public class BDConn extends SQLiteOpenHelper {

    //atributos
    private static String bdName = "wakfu";
    private static int versaoBD = 4;
    private Context ctx;

    //construtor
    public BDConn(Context ctx){
        super(ctx,bdName,null,versaoBD);
        this.ctx=ctx;
    }

    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table item(" +
                "_id integer primary key autoincrement," +  //id
                "tipo varchar(10) not null," +              //identificador se é capacete ou qualquer outra coisa para fazer o where
                "nome varchar(100) not null," +             //nome
                "nivel int not null," +                     //nivel
                "raridade int,"+                            //raridade
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
                "respa int,"+                               //resistencia a pa
                "dmgfogo int,"+                             // dominio fogo
                "dmgterra int, "+                           //dominio terra
                "dmgagua int,"+                             //dominio agua
                "dmgar int,"+                               //dominio ar
                "minertake int);");                         // coleta de mineiro
        bd.execSQL("create table build(" +
                "_id integer primary key autoincrement," +  //id        0
                "nome varchar(100) not null," +             //nome      1
                "nivel int not null," +                     //nivel     2
                "heal int," +                               //cura      3
                "range int," +                              //alcance           4
                "beserkdmg int," +                          //dano beserk\n" +  5
                "block int," +                              //bloqueio\n" +     6
                "control int," +                            //controle\n" +     7
                "dmg int," +                                //dano geral\n" +   8
                "criticalchance int," +                     //chance de critico\n" +    9
                "backdmg int," +                            // dano nas costas\n" +     10
                "distancedmg int," +                        // dano a distancia\n" +    11
                "closecombatdmg int," +                     //dano corpo a corpo\n" +   12
                "element1dmg int," +                        //1 elemento dano\n" +      13
                "element2dmg int," +                        //2 elementos dano\n" +     14
                "element3dmg int," +                        //3 elementos dano\n" +     15
                "areadmg int," +                            //dano em area\n" +         16
                "onetargetdmg int," +                       //dano alvo unico\n" +      17
                "evasion int," +                            //evasao\n" +               18
                "criticaldmg int," +                        //dano critico\n" +         19
                "iniciative int," +                         //iniciativa\n" +           20
                "stop int," +                               //parada\n" +               21
                "prosp int," +                              //prosperição\n" +          22
                "pwmax int," +                              //pw maximo\n" +            23
                "resist int," +                             //resistencia\n" +          24
                "sabedoria int," +                          //sabedoria\n" +            25
                "reswater int," +                           //resistencia agua\n" +     26
                "resfire int," +                            //resistencia fogo\n" +     27
                "resair int," +                             //resistencia ar\n" +       28
                "researth int," +                           //resistencia terra\n" +    29
                "resbackdmg int," +                        //resistencia dano nas costas\n" +   30
                "rescriticaldmg int," +                     //resistencia dano critico\n" +     31
                "res1elerandom int," +                      //resistencia a 1 elemento aleatorio\n" +   32
                "res2elerandom int," +                      //resistencia a 2 elementos aleatorios\n" + 33
                "res3elerandom int," +                      //resistencia a 3 elementos aleatorios\n" + 34
                "actionpoint int," +                        //  PA\n" +                 35
                "movepoint int," +                          //  PM\n" +         36
                "vitalpoint int," +                         //  PV\n" +         37
                "wakfupoint int," +                         //  PW\n" +         38
                "status text," +                            // Status de item SHUSHU\n" +       39
                "pvphp int," +                              // pvp hp\n" +              40
                "arteequipar int," +                        // arte de equipar\n" +     41
                "nvfagua int," +                            // nv feitiço de agua\n" +  42
                "nvffogo int," +                            // de fogo\n" +             43
                "nvfar int," +                              // de ar\n" +               44
                "nvfterra int," +                           // de terra\n" +            45
                "nvfgeral int," +                           // de geral\n" +            46
                "movespeed int," +                          // velocidade de movimento\n" +     47
                "respm int," +                              // resistencia a remoção de pm\n" + 48
                "respa int," +  //      49
                "elmo int," +   //      50
                "capa int," +   //      51
                "peitoral int," +       //      52
                "bota int," +   //      53
                "pet int," +    //      54
                "anel1 int," +  //      55
                "anel2 int," +  //      56
                "amuleto int," +        //      57
                "insignia int," +       //      58
                "armamain int," +       //      59
                "armasec int," +        //      60
                "cinto int," +          //      61
                "dragona int," +        //      62
                "montaria int," +       //      63
                "classe int," +         //      64
                "elementp int,"+        //elemento prioritari       65
                "resistp int);");       //classe do jogo para escolhar da imagem        66
        bd.execSQL("create table point(" +
                "_id integer primary key autoincrement," +
                "intpoints int," +
                "apinlifepercent int," +
                "apinresele int," +
                "apinbarreira int," +
                "apinhealget int," +
                "apinlifearmor int," +
                "forcepoints int," +
                "apingeral int," +
                "apinalvounico int," +
                "apinzona int," +
                "apincac int," +
                "apindistance int," +
                "apinlife int," +
                "agipoints int," +
                "apinblock int," +
                "apinesquiva int," +
                "apinini int," +
                "apinblockandesquiva int," +
                "apinremovepaepm int," +
                "apinrespaepm int," +
                "luckpoints int," +
                "apingolpecritico int," +
                "apinparada int," +
                "apindanocritico int," +
                "apinbackdmg int," +
                "apinbeserkdmg int," +
                "apinheal int," +
                "apinresbackdmg int," +
                "apincriticalres int," +
                "especialpoints int," +
                "apinactionpoint int," +
                "apinmovepoint int," +
                "apinrangeanddmg int," +
                "apinwakfupoint int," +
                "apincontrolanddmg int," +
                "apinarteequipar int," +
                "apinfinaldmg int," +
                "apinreselemental int);");
    }

    public void onCreateItem(SQLiteDatabase bd) {
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

    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2){
        bd.execSQL("drop table item");
        onCreateItem(bd);
        SharedPreferences.Editor editor = ctx.getSharedPreferences("com.buildswakfu.rodrigo.buildswakfu", Context.MODE_PRIVATE).edit();
        editor.putBoolean("firstrun", true).commit();
    }
}