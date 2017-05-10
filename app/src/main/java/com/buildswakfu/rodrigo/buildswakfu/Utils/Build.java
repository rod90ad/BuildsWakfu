package com.buildswakfu.rodrigo.buildswakfu.Utils;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by rodso on 02/07/2016.
 */

public class Build {

    private int codigo=0;
    private String nome="";
    private int elementp =0;
    private int resistp =0;
    private int classe=0;
    private int nivel=0;
    private int heal=0;
    private int range=0;
    private int beserkdmg=0;
    private int block=0;
    private int control=0;
    private int dmg=0;
    private int criticalchance=0;
    private int backdmg=0;
    private int distancedmg=0;
    private int closecombatdmg=0;
    private int element1dmg=0;
    private int element2dmg=0;
    private int element3dmg=0;
    private int areadmg=0;
    private int onetargetdmg=0;
    private int evasion=0;
    private int criticaldmg=0;
    private int iniciative=0;
    private int stop=0;
    private int prosp=0;
    private int pwmax=0;
    private int resist=0;
    private int sabedoria=0;
    private int reswater=0;
    private int resfire=0;
    private int resair=0;
    private int researth=0;
    private int resblackdmg=0;
    private int rescriticaldmg=0;
    private int res1elerandom=0;
    private int res2elerandom=0;
    private int res3elerandom=0;
    private int actionpoint=0;
    private int movepoint=0;
    private int vitalpoint=0;
    private int wakfupoint=0;
    private ArrayList<String> status = new ArrayList<String>();
    private int statusind=0;
    private int pvphp=0;
    private int arteequipar=0;
    private int nvfagua=0;
    private int nvffogo=0;
    private int nvfar=0;
    private int nvfterra=0;
    private int nvfgeral=0;
    private int movespeed=0;
    private int respm=0;
    private int respa=0;
    private int dmgfire=0;
    private int dmgwater=0;
    private int dmgearth=0;
    private int dmgair=0;
    private int minertake=0;

    //pontos
    //inteligencia
    private int intPoints=0;    //nao aplicados
    private int apinlifepercent=0;
    private int apinresele=0;
    private int apinbarreira=0;
    private int apinhealrecived=0;
    private int apinlifearmor=0;

    //força
    private int forcePoints=0;
    private int apingeral=0;
    private int apinalvounico=0;
    private int apinzona=0;
    private int apinCaC=0;
    private int apindistance=0;
    private int apinlife=0;

    //agilidade
    private int agiPoints=0;
    private int apinblock=0;
    private int apinesquiva=0;
    private int apininiciativa=0;
    private int apinblockandesquiva=0;
    private int apinremovepaandpm=0;
    private int apinrespmepm=0;

    //sorte
    private int luckPoints=0;
    private int apingolpecritico=0;
    private int apinparada=0;
    private int apindanocritico=0;
    private int apinbackdmg=0;
    private int apinbeserkdmg=0;
    private int apinheal=0;
    private int apinresbackdmg=0;
    private int apincritialres=0;

    //principal
    private int especialPoints=0;
    private int apinactionpoint=0;
    private int apinmovepoint=0;
    private int apinrangeanddmg=0;
    private int apinwakfupoint=0;
    private int apincontrolanddmg=0;
    private int apinarteequipar=0;
    private int apinfinalDamage=0;
    private int apinreselemental=0;

    //itens
    private Item elmo= new Item();
    private Item capa=new Item();
    private Item peitoral=new Item();
    private Item bota=new Item();
    private Item pet=new Item();
    private Item anel1=new Item();
    private Item anel2=new Item();
    private Item amuleto=new Item();
    private Item insignia=new Item();
    private Item armamain=new Item();
    private Item armasec=new Item();
    private Item cinto=new Item();
    private Item dragona=new Item();
    private Item montaria=new Item();

    //skills
    private int spell1=0;
    private int spell2=0;
    private int spell3=0;
    private int spell4=0;
    private int spell5=0;
    private int spell6=0;
    private int spell7=0;
    private int spell8=0;
    private int spell9=0;
    private int spell10=0;
    private int spell11=0;
    private int spell12=0;

    //passives
    private int pSpell1=0;
    private int pSpell2=0;
    private int pSpell3=0;
    private int pSpell4=0;
    private int pSpell5=0;
    private int pSpell6=0;

    public Build (){}

    public Build(int codigo, String nome, int elementp, int resistp, int classe, int nivel, int heal, int range, int beserkdmg, int block, int control, int dmg, int criticalchance, int backdmg, int distancedmg, int closecombatdmg, int element1dmg, int element2dmg, int element3dmg, int areadmg, int onetargetdmg, int evasion, int criticaldmg, int iniciative, int stop, int prosp, int pwmax, int resist, int sabedoria, int reswater, int resfire, int resair, int researth, int resblackdmg, int rescriticaldmg, int res1elerandom, int res2elerandom, int res3elerandom, int actionpoint, int movepoint, int vitalpoint, int wakfupoint, ArrayList<String> status, int statusind, int pvphp, int arteequipar, int nvfagua, int nvffogo, int nvfar, int nvfterra, int nvfgeral, int movespeed, int respm, int respa, int dmgfire, int dmgwater, int dmgearth, int dmgair, int minertake, int intPoints, int apinlifepercent, int apinresele, int apinbarreira, int apinhealrecived, int apinlifearmor, int forcePoints, int apingeral, int apinalvounico, int apinzona, int apinCaC, int apindistance, int apinlife, int agiPoints, int apinblock, int apinesquiva, int apininiciativa, int apinblockandesquiva, int apinremovepaandpm, int apinrespmepm, int luckPoints, int apingolpecritico, int apinparada, int apindanocritico, int apinbackdmg, int apinbeserkdmg, int apinheal, int apinresbackdmg, int apincritialres, int especialPoints, int apinactionpoint, int apinmovepoint, int apinrangeanddmg, int apinwakfupoint, int apincontrolanddmg, int apinarteequipar, int apinfinalDamage, int apinreselemental, Item elmo, Item capa, Item peitoral, Item bota, Item pet, Item anel1, Item anel2, Item amuleto, Item insignia, Item armamain, Item armasec, Item cinto, Item dragona, Item montaria) {
        this.codigo = codigo;
        this.nome = nome;
        this.elementp = elementp;
        this.resistp = resistp;
        this.classe = classe;
        this.nivel = nivel;
        this.heal = heal;
        this.range = range;
        this.beserkdmg = beserkdmg;
        this.block = block;
        this.control = control;
        this.dmg = dmg;
        this.criticalchance = criticalchance;
        this.backdmg = backdmg;
        this.distancedmg = distancedmg;
        this.closecombatdmg = closecombatdmg;
        this.element1dmg = element1dmg;
        this.element2dmg = element2dmg;
        this.element3dmg = element3dmg;
        this.areadmg = areadmg;
        this.onetargetdmg = onetargetdmg;
        this.evasion = evasion;
        this.criticaldmg = criticaldmg;
        this.iniciative = iniciative;
        this.stop = stop;
        this.prosp = prosp;
        this.pwmax = pwmax;
        this.resist = resist;
        this.sabedoria = sabedoria;
        this.reswater = reswater;
        this.resfire = resfire;
        this.resair = resair;
        this.researth = researth;
        this.resblackdmg = resblackdmg;
        this.rescriticaldmg = rescriticaldmg;
        this.res1elerandom = res1elerandom;
        this.res2elerandom = res2elerandom;
        this.res3elerandom = res3elerandom;
        this.actionpoint = actionpoint;
        this.movepoint = movepoint;
        this.vitalpoint = vitalpoint;
        this.wakfupoint = wakfupoint;
        this.status = status;
        this.statusind = statusind;
        this.pvphp = pvphp;
        this.arteequipar = arteequipar;
        this.nvfagua = nvfagua;
        this.nvffogo = nvffogo;
        this.nvfar = nvfar;
        this.nvfterra = nvfterra;
        this.nvfgeral = nvfgeral;
        this.movespeed = movespeed;
        this.respm = respm;
        this.respa = respa;
        this.dmgfire = dmgfire;
        this.dmgwater = dmgwater;
        this.dmgearth = dmgearth;
        this.dmgair = dmgair;
        this.minertake = minertake;
        this.intPoints = intPoints;
        this.apinlifepercent = apinlifepercent;
        this.apinresele = apinresele;
        this.apinbarreira = apinbarreira;
        this.apinhealrecived = apinhealrecived;
        this.apinlifearmor = apinlifearmor;
        this.forcePoints = forcePoints;
        this.apingeral = apingeral;
        this.apinalvounico = apinalvounico;
        this.apinzona = apinzona;
        this.apinCaC = apinCaC;
        this.apindistance = apindistance;
        this.apinlife = apinlife;
        this.agiPoints = agiPoints;
        this.apinblock = apinblock;
        this.apinesquiva = apinesquiva;
        this.apininiciativa = apininiciativa;
        this.apinblockandesquiva = apinblockandesquiva;
        this.apinremovepaandpm = apinremovepaandpm;
        this.apinrespmepm = apinrespmepm;
        this.luckPoints = luckPoints;
        this.apingolpecritico = apingolpecritico;
        this.apinparada = apinparada;
        this.apindanocritico = apindanocritico;
        this.apinbackdmg = apinbackdmg;
        this.apinbeserkdmg = apinbeserkdmg;
        this.apinheal = apinheal;
        this.apinresbackdmg = apinresbackdmg;
        this.apincritialres = apincritialres;
        this.especialPoints = especialPoints;
        this.apinactionpoint = apinactionpoint;
        this.apinmovepoint = apinmovepoint;
        this.apinrangeanddmg = apinrangeanddmg;
        this.apinwakfupoint = apinwakfupoint;
        this.apincontrolanddmg = apincontrolanddmg;
        this.apinarteequipar = apinarteequipar;
        this.apinfinalDamage = apinfinalDamage;
        this.apinreselemental = apinreselemental;
        this.elmo = elmo;
        this.capa = capa;
        this.peitoral = peitoral;
        this.bota = bota;
        this.pet = pet;
        this.anel1 = anel1;
        this.anel2 = anel2;
        this.amuleto = amuleto;
        this.insignia = insignia;
        this.armamain = armamain;
        this.armasec = armasec;
        this.cinto = cinto;
        this.dragona = dragona;
        this.montaria = montaria;
    }

    public void resetStatus(){
        setActionpoint(0);
        setMovepoint(0);
        setRange(0);
        setControl(0);
        setVitalpoint(0);
        setDmg(0);
        setHeal(0);
        setBlock(0);
        setCriticalchance(0);
        setBackdmg(0);
        setDistancedmg(0);
        setClosecombatdmg(0);
        setElement1dmg(0);
        setElement2dmg(0);
        setElement3dmg(0);
        setAreadmg(0);
        setOnetargetdmg(0);
        setEvasion(0);
        setCriticaldmg(0);
        setIniciative(0);
        setStop(0);
        setProsp(0);
        setPwmax(0);
        setResist(0);
        setSabedoria(0);
        setReswater(0);
        setResfire(0);
        setResair(0);
        setResearth(0);
        setResblackdmg(0);
        setRescriticaldmg(0);
        setRes1elerandom(0);
        setRes2elerandom(0);
        setRes3elerandom(0);
        setWakfupoint(0);
        setStatus("");
        setPvphp(0);
        setArteequipar(0);
        setNvfagua(0);
        setNvfar(0);
        setNvffogo(0);
        setNvfterra(0);
        setNvfgeral(0);
        setMovespeed(0);
        setRespm(0);
        setRespa(0);
        setDmgfire(0);
        setDmgwater(0);
        setDmgearth(0);
        setDmgair(0);
        setMinertake(0);
    }

    public String corrigeCodigo(String string){
        switch (string.length()){
            case 0:
                return "0000";
            case 1:
                return "000"+string;
            case 2:
                return "00"+string;
            case 3:
                return "0"+string;
            default:
                return "0000";
        }
    }

    public String corrigePontos(String string){
        switch (string.length()){
            case 0:
                return "00";
            case 1:
                return "0"+string;
            case 2:
                return string;
            default:
                return "00";
        }
    }

    //setBuild from QR
    public boolean setQRString(String aux,Context context){
        boolean yes=false;
        BD bd = new BD(context);
        try {
            //coloca os itens na build
            setAmuletoBD(bd.getItem(aux.substring(0,4)));
            setAnel1BD(bd.getItem(aux.substring(4,8)));
            setAnel2BD(bd.getItem(aux.substring(8,12)));
            setArmamainBD(bd.getItem(aux.substring(12,16)));
            setArmasecBD(bd.getItem(aux.substring(16,20)));
            setCapaBD(bd.getItem(aux.substring(20,24)));
            setCintoBD(bd.getItem(aux.substring(24,28)));
            setElmoBD(bd.getItem(aux.substring(28,32)));
            setDragonaBD(bd.getItem(aux.substring(32,36)));
            setPetBD(bd.getItem(aux.substring(36,40)));
            setPeitoralBD(bd.getItem(aux.substring(40,44)));
            setBotaBD(bd.getItem(aux.substring(44,48)));
            setInsigniaBD(bd.getItem(aux.substring(48,52)));
            setMontariaBD(bd.getItem(aux.substring(52,56)));

            //coloca os pontos de inteligencia
            setIntPoints(Integer.parseInt(aux.substring(56,58)));
            setApinlifepercent(Integer.parseInt(aux.substring(58,60)));
            setApinresele(Integer.parseInt(aux.substring(60,62)));
            setApinbarreira(Integer.parseInt(aux.substring(62,64)));
            setApinhealrecived(Integer.parseInt(aux.substring(64,66)));
            setApinlifearmor(Integer.parseInt(aux.substring(66,68)));

            //coloca os pontos de força
            setForcePoints(Integer.parseInt(aux.substring(68,70)));
            setApingeral(Integer.parseInt(aux.substring(70,72)));
            setApinalvounico(Integer.parseInt(aux.substring(72,74)));
            setApinzona(Integer.parseInt(aux.substring(74,76)));
            setApinCaC(Integer.parseInt(aux.substring(76,78)));
            setApindistance(Integer.parseInt(aux.substring(78,80)));
            setApinlife(Integer.parseInt(aux.substring(80,82)));

            //coloca os pontos de agi
            setAgiPoints(Integer.parseInt(aux.substring(82,84)));
            setApinblock(Integer.parseInt(aux.substring(84,86)));
            setApinesquiva(Integer.parseInt(aux.substring(86,88)));
            setApininiciativa(Integer.parseInt(aux.substring(88,90)));
            setApinblockandesquiva(Integer.parseInt(aux.substring(90,92)));
            setApinremovepaandpm(Integer.parseInt(aux.substring(92,94)));
            setApinrespmepm(Integer.parseInt(aux.substring(94,96)));

            //coloca os pontos de sorte
            setLuckPoints(Integer.parseInt(aux.substring(96,98)));
            setApingolpecritico(Integer.parseInt(aux.substring(98,100)));
            setApinparada(Integer.parseInt(aux.substring(100,102)));
            setApindanocritico(Integer.parseInt(aux.substring(102,104)));
            setApinbackdmg(Integer.parseInt(aux.substring(104,106)));
            setApinbeserkdmg(Integer.parseInt(aux.substring(106,108)));
            setApinheal(Integer.parseInt(aux.substring(108,110)));
            setApinresbackdmg(Integer.parseInt(aux.substring(110,112)));
            setApincritialres(Integer.parseInt(aux.substring(112,114)));

            //set especial points
            setEspecialPoints(Integer.parseInt(aux.substring(114,116)));
            setApinactionpoint(Integer.parseInt(aux.substring(116,118)));
            setApinmovepoint(Integer.parseInt(aux.substring(118,120)));
            setApinrangeanddmg(Integer.parseInt(aux.substring(120,122)));
            setApinwakfupoint(Integer.parseInt(aux.substring(122,124)));
            setApincontrolanddmg(Integer.parseInt(aux.substring(124,126)));
            setApinarteequipar(Integer.parseInt(aux.substring(126,128)));
            setApinfinalDamage(Integer.parseInt(aux.substring(128,130)));
            setApinreselemental(Integer.parseInt(aux.substring(130,132)));

            //pega outros atributos
            setClasse(Integer.parseInt(aux.substring(132,134)));
            setResistp(Integer.parseInt(aux.substring(134,136)));
            setElementp(Integer.parseInt(aux.substring(136,138)));
            setNivel(Integer.parseInt(aux.substring(138,142)));
            setNome(aux.substring(142).trim());
            yes=true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            yes=false;
        }
        return yes;
    }

    //get string for qr code
    public String getQRString(){
        String aux = corrigeCodigo(getAmuleto().getCodigo()+"");    //coloca o amuleto  4
        aux+= corrigeCodigo(getAnel1().getCodigo()+"");             //coloca o anel 1   8
        aux+= corrigeCodigo(getAnel2().getCodigo()+"");             //coloca o anel 2   12
        aux+= corrigeCodigo(getArmamain().getCodigo()+"");          //coloca o arma main    16
        aux+= corrigeCodigo(getArmasec().getCodigo()+"");           //coloca o arma sec     20
        aux+= corrigeCodigo(getCapa().getCodigo()+"");              //coloca o capa         24
        aux+= corrigeCodigo(getCinto().getCodigo()+"");             //coloca o cinto        28
        aux+= corrigeCodigo(getElmo().getCodigo()+"");              //coloca o elmo         32
        aux+= corrigeCodigo(getDragona().getCodigo()+"");           //coloca a dragona      36
        aux+= corrigeCodigo(getPet().getCodigo()+"");               //coloca o pet          40
        aux+= corrigeCodigo(getPeitoral().getCodigo()+"");          //coloca o peitoral     44
        aux+= corrigeCodigo(getBota().getCodigo()+"");              //coloca a bota         48
        aux+= corrigeCodigo(getInsignia().getCodigo()+"");          //coloca a insignia     52
        aux+= corrigeCodigo(getMontaria().getCodigo()+"");          //coloca a montaria     56

        aux+= corrigePontos(getIntPoints()+"");                     //pega os pontos de int nao adiconados
        aux+= corrigePontos(getApinlifepercent()+"");
        aux+= corrigePontos(getApinresele()+"");
        aux+= corrigePontos(getApinbarreira()+"");
        aux+= corrigePontos(getApinhealrecived()+"");
        aux+= corrigePontos(getApinlifearmor()+"");

        aux+= corrigePontos(getForcePoints()+"");                     //pega os pontos de força nao adiconados
        aux+= corrigePontos(getApingeral()+"");
        aux+= corrigePontos(getApinalvounico()+"");
        aux+= corrigePontos(getApinzona()+"");
        aux+= corrigePontos(getApinCaC()+"");
        aux+= corrigePontos(getApindistance()+"");
        aux+= corrigePontos(getApinlife()+"");

        aux+= corrigePontos(getAgiPoints()+"");                     //pega os pontos de agi nao adiconados
        aux+= corrigePontos(getApinblock()+"");
        aux+= corrigePontos(getApinesquiva()+"");
        aux+= corrigePontos(getApininiciativa()+"");
        aux+= corrigePontos(getApinblockandesquiva()+"");
        aux+= corrigePontos(getApinremovepaandpm()+"");
        aux+= corrigePontos(getApinrespmepm()+"");

        aux+= corrigePontos(getLuckPoints()+"");                     //pega os pontos de sorte nao adiconados
        aux+= corrigePontos(getApingolpecritico()+"");
        aux+= corrigePontos(getApinparada()+"");
        aux+= corrigePontos(getApindanocritico()+"");
        aux+= corrigePontos(getApinbackdmg()+"");
        aux+= corrigePontos(getApinbeserkdmg()+"");
        aux+= corrigePontos(getApinheal()+"");
        aux+= corrigePontos(getApinresbackdmg()+"");
        aux+= corrigePontos(getApincritialres()+"");

        aux+= corrigePontos(getEspecialPoints()+"");                     //pega os pontos especiais nao adiconados
        aux+= corrigePontos(getApinactionpoint()+"");
        aux+= corrigePontos(getApinmovepoint()+"");
        aux+= corrigePontos(getApinrangeanddmg()+"");
        aux+= corrigePontos(getApinwakfupoint()+"");
        aux+= corrigePontos(getApincontrolanddmg()+"");
        aux+= corrigePontos(getApinarteequipar()+"");
        aux+= corrigePontos(getApinfinalDamage()+"");
        aux+= corrigePontos(getApinreselemental()+"");

        aux+= corrigePontos(getClasse()+"");    //pega a classe
        aux+= corrigePontos(getResistp()+"");   //pega a prioridade resistencial
        aux+= corrigePontos(getElementp()+"");  //pega a prioridade elemental
        aux+= corrigeCodigo(getNivel()+"");     //pega o nivel
        aux+= getNome();                        //pega nome
        return aux;
    }

    //gets _______________________________________________________________________________________________________________________
    public int getSpell1(){ return spell1; }
    public int getSpell2(){ return spell2; }
    public int getSpell3(){ return spell3; }
    public int getSpell4(){ return spell4; }
    public int getSpell5(){ return spell5; }
    public int getSpell6(){ return spell6; }
    public int getSpell7(){ return spell7; }
    public int getSpell8(){ return spell8; }
    public int getSpell9(){ return spell9; }
    public int getSpell10(){ return spell10; }
    public int getSpell11(){ return spell11; }
    public int getSpell12(){ return spell12; }
    public int getPSpell1(){ return pSpell1; }
    public int getPSpell2(){ return pSpell2; }
    public int getPSpell3(){ return pSpell3; }
    public int getPSpell4(){ return pSpell4; }
    public int getPSpell5(){ return pSpell5; }
    public int getPSpell6(){ return pSpell6; }
    public int getDmgfire(){ return dmgfire; }
    public int getDmgwater(){ return dmgwater; }
    public int getDmgearth(){ return dmgearth; }
    public int getDmgair(){ return dmgair; }
    public int getMinertake(){ return minertake; }
    public Item getElmo(){   return elmo;   }
    public Item getCapa(){ return capa; }
    public Item getPeitoral(){ return peitoral; }
    public Item getBota(){return bota;  }
    public Item getPet(){ return pet; }
    public Item getAnel1(){ return anel1; }
    public Item getAnel2(){ return anel2; }
    public Item getAmuleto(){ return amuleto; }
    public Item getInsignia(){ return insignia; }
    public Item getArmamain(){ return armamain; }
    public Item getArmasec(){ return armasec; }
    public Item getCinto(){ return cinto; }
    public Item getDragona(){ return dragona; }
    public Item getMontaria(){ return montaria; }
    public String getNome() {        return nome;    }
    public int getClasse(){ return classe; }
    public int getNivel() {        return nivel;    }
    public int getHeal() {        return heal;    }
    public int getRange() {        return range;    }
    public int getBeserkdmg() {        return beserkdmg;    }
    public int getBlock() {        return block;    }
    public int getControl() {        return control;    }
    public ArrayList<String> getStatus() {        return status;    }
    public int getDmg() {        return dmg;    }
    public int getCriticalchance() {        return criticalchance;    }
    public int getBackdmg() {        return backdmg;    }
    public int getDistancedmg() {        return distancedmg;    }
    public int getClosecombatdmg() {        return closecombatdmg;    }
    public int getElement1dmg() {        return element1dmg;    }
    public int getElement2dmg() {        return element2dmg;    }
    public int getElement3dmg() {        return element3dmg;    }
    public int getAreadmg() {        return areadmg;    }
    public int getOnetargetdmg() {        return onetargetdmg;    }
    public int getEvasion() {        return evasion;    }
    public int getCriticaldmg() {        return criticaldmg;    }
    public int getIniciative() {        return iniciative;    }
    public int getStop() {        return stop;    }
    public int getProsp() {        return prosp;    }
    public int getPwmax() {        return pwmax;    }
    public int getResist() {        return resist;    }
    public int getSabedoria() {        return sabedoria;    }
    public int getReswater() {        return reswater;    }
    public int getResfire() {        return resfire;    }
    public int getResair() {        return resair;    }
    public int getResearth() {        return researth;    }
    public int getResblackdmg() {        return resblackdmg;    }
    public int getRescriticaldmg() {        return rescriticaldmg;    }
    public int getRes1elerandom() {        return res1elerandom;    }
    public int getRes2elerandom() {        return res2elerandom;    }
    public int getRes3elerandom() {        return res3elerandom;    }
    public int getActionpoint() {        return actionpoint;    }
    public int getMovepoint() {        return movepoint;    }
    public int getVitalpoint() {        return vitalpoint;    }
    public int getWakfupoint() {        return wakfupoint;    }
    public int getCodigo(){     return codigo;  }
    public int getIntPoints() {        return intPoints;    }
    public int getForcePoints() {        return forcePoints;    }
    public int getEspecialPoints() {        return especialPoints;    }
    public int getAgiPoints() {        return agiPoints;    }
    public int getLuckPoints() {        return luckPoints;    }
    public int getElementp(){ return elementp; }
    public int getResistp(){ return resistp; }

    public void updateBuild(String coluna,Item item, Context context){
        new BD(context).updateItem(coluna, item, this);
    }

    //sets_______________________________________________________________________________________________________________________
    public void setSpell1(int spell1){ this.spell1=spell1; }
    public void setSpell2(int spell2){ this.spell2=spell2; }
    public void setSpell3(int spell3){ this.spell3=spell3; }
    public void setSpell4(int spell4){ this.spell4=spell4; }
    public void setSpell5(int spell5){ this.spell5=spell5; }
    public void setSpell6(int spell6){ this.spell6=spell6; }
    public void setSpell7(int spell7){ this.spell7=spell7; }
    public void setSpell8(int spell8){ this.spell8=spell8; }
    public void setSpell9(int spell9){ this.spell9=spell9; }
    public void setSpell10(int spell10){ this.spell10=spell10; }
    public void setSpell11(int spell11){ this.spell11=spell11; }
    public void setSpell12(int spell12){ this.spell12=spell12; }
    public void setPSpell1(int pSpell1){ this.pSpell1=pSpell1; }
    public void setPSpell2(int pSpell2){ this.pSpell2=pSpell2; }
    public void setPSpell3(int pSpell3){ this.pSpell3=pSpell3; }
    public void setPSpell4(int pSpell4){ this.pSpell4=pSpell4; }
    public void setPSpell5(int pSpell5){ this.pSpell5=pSpell5; }
    public void setPSpell6(int pSpell6){ this.pSpell6=pSpell6; }
    public void setDmgfire(int dmgfire){ this.dmgfire=dmgfire; }
    public void setDmgwater(int dmgwater){ this.dmgwater=dmgwater; }
    public void setDmgearth(int dmgearth){ this.dmgearth=dmgearth; }
    public void setDmgair(int dmgair){ this.dmgair=dmgair; }
    public void setMinertake(int minertake){ this.minertake=minertake; }
    public void setElmo(Item elmo, Context context){
        this.elmo =elmo;
        updateBuild("elmo", elmo, context);
    }
    public void setElmoBD(Item elmo){ this.elmo= elmo; }

    public void setCapa(Item capa, Context context){
        this.capa = capa;
        updateBuild("capa",capa, context);
    }
    public void setCapaBD(Item capaCode){ this.capa=capaCode; }

    public void setPeitoral(Item peitoral, Context context){
        this.peitoral = peitoral;
        updateBuild("peitoral",peitoral, context);
    }
    public void setPeitoralBD(Item peitoralCode){ this.peitoral=peitoralCode; }

    public void setBota(Item bota, Context context){
        this.bota = bota;
        updateBuild("bota",bota, context);
    }
    public void setBotaBD(Item botaCode){ this.bota= botaCode; }

    public void setPet(Item pet, Context context){
        this.pet = pet;
        updateBuild("pet",pet, context);
    }
    public void setPetBD(Item petCode){ this.pet=petCode; }

    public void setAnel1(Item anel1, Context context){
        this.anel1 = anel1;
        updateBuild("anel1",anel1, context);
    }
    public void setAnel1BD(Item anel1Code){ this.anel1=anel1Code; }

    public void setAnel2(Item anel2, Context context){
        this.anel2 = anel2;
        updateBuild("anel2",anel2, context);
    }
    public void setAnel2BD(Item anel2Code){ this.anel2=anel2Code; }

    public void setAmuleto(Item amuleto, Context context){
        this.amuleto = amuleto;
        updateBuild("amuleto",amuleto, context);
    }
    public void setAmuletoBD(Item amuletoCode){ this.amuleto=amuletoCode; }

    public void setInsignia(Item insignia, Context context){
        this.insignia = insignia;
        updateBuild("insignia",insignia, context);
    }
    public void setInsigniaBD(Item insigniaCode){ this.insignia=insigniaCode; }

    public void setArmamain(Item armamain, Context context){
        this.armamain = armamain;
        updateBuild("armamain",armamain, context);
    }
    public void setArmamainBD(Item armamainCode){ this.armamain=armamainCode; }

    public void setArmasec(Item armasec, Context context){
        this.armasec = armasec;
        updateBuild("armasec",armasec, context);
    }
    public void setArmasecBD(Item armasecCode){ this.armasec=armasecCode; }

    public void setCinto(Item cinto, Context context){
        this.cinto = cinto;
        updateBuild("cinto",cinto, context);
    }
    public void setCintoBD(Item cintoCode){ this.cinto=cintoCode; }

    public void setDragona(Item dragona, Context context){
        this.dragona = dragona;
        updateBuild("dragona",dragona, context);
    }
    public void setDragonaBD(Item dragonaCode){ this.dragona=dragonaCode; }

    public void setMontaria(Item montaria, Context context){
        this.montaria = montaria;
        updateBuild("montaria",montaria, context);
    }
    public void setMontariaBD(Item montariaCode){ this.montaria=montariaCode; }

    public void setNome(String nome) {        this.nome = nome;    }
    public void setNivel(int nivel) {        this.nivel = nivel;    }
    public void setHeal(int heal) {        this.heal = heal;    }
    public void setRange(int range) {        this.range = range;    }
    public void setBeserkdmg(int beserkdmg) {        this.beserkdmg = beserkdmg;    }
    public void setBlock(int block) {        this.block = block;    }
    public void setControl(int control) {        this.control = control;    }
    public void setDmg(int dmg) {        this.dmg = dmg;    }
    public void setCriticalchance(int criticalchance) {        this.criticalchance = criticalchance;    }
    public void setBackdmg(int backdmg) {        this.backdmg = backdmg;    }
    public void setDistancedmg(int distancedmg) {        this.distancedmg = distancedmg;    }
    public void setClosecombatdmg(int closecombatdmg) {        this.closecombatdmg = closecombatdmg;    }
    public void setElement1dmg(int element1dmg) {        this.element1dmg = element1dmg;    }
    public void setElement2dmg(int element2dmg) {        this.element2dmg = element2dmg;    }
    public void setElement3dmg(int element3dmg) {        this.element3dmg = element3dmg;    }
    public void setAreadmg(int areadmg) {        this.areadmg = areadmg;    }
    public void setOnetargetdmg(int onetargetdmg) {        this.onetargetdmg = onetargetdmg;    }
    public void setEvasion(int evasion) {        this.evasion = evasion;    }
    public void setCriticaldmg(int criticaldmg) {        this.criticaldmg = criticaldmg;    }
    public void setIniciative(int iniciative) {        this.iniciative = iniciative;    }
    public void setStop(int stop) {        this.stop = stop;    }
    public void setProsp(int prosp) {        this.prosp = prosp;    }
    public void setPwmax(int pwmax) {        this.pwmax = pwmax;    }
    public void setResist(int resist) {        this.resist = resist;    }
    public void setSabedoria(int sabedoria) {        this.sabedoria = sabedoria;    }
    public void setReswater(int reswater) {        this.reswater = reswater;    }
    public void setResfire(int resfire) {        this.resfire = resfire;    }
    public void setResair(int resair) {        this.resair = resair;    }
    public void setResearth(int researth) {        this.researth = researth;    }
    public void setResblackdmg(int resblackdmg) {        this.resblackdmg = resblackdmg;    }
    public void setRescriticaldmg(int rescriticaldmg) {        this.rescriticaldmg = rescriticaldmg;    }
    public void setRes1elerandom(int res1elerandom) {        this.res1elerandom = res1elerandom;    }
    public void setRes2elerandom(int res2elerandom) {        this.res2elerandom = res2elerandom;    }
    public void setRes3elerandom(int res3elerandom) {        this.res3elerandom = res3elerandom;    }
    public void setActionpoint(int actionpoint) {        this.actionpoint = actionpoint;    }
    public void setMovepoint(int movepoint) {        this.movepoint = movepoint;    }
    public void setVitalpoint(int vitalpoint) {        this.vitalpoint = vitalpoint;    }
    public void setWakfupoint(int wakfupoint) {        this.wakfupoint = wakfupoint;    }
    public void setStatus(String status) {
        this.status.add(status);
    }
    public void setCodigo(int codigo){ this.codigo = codigo;    }
    public void setIntPoints(int intPoints) {        this.intPoints = intPoints;    }
    public void setForcePoints(int forcePoints) {        this.forcePoints = forcePoints;    }
    public void setEspecialPoints(int especialPoints) {        this.especialPoints = especialPoints;    }
    public void setAgiPoints(int agiPoints) {        this.agiPoints = agiPoints;    }
    public void setLuckPoints(int luckPoints) {        this.luckPoints = luckPoints;    }
    public void setClasse(int classe){ this.classe = classe; }
    public void setElementp(int elementp){ this.elementp = elementp; }
    public void setResistp(int resistp){ this.resistp = resistp; }

    public int getApinlifepercent() {
        return apinlifepercent;
    }

    public void setApinlifepercent(int apinlifepercent) {
        this.apinlifepercent = apinlifepercent;
    }

    public int getApinresele() {
        return apinresele;
    }

    public void setApinresele(int apinresele) {
        this.apinresele = apinresele;
    }

    public int getApinbarreira() {
        return apinbarreira;
    }

    public void setApinbarreira(int apinbarreira) {
        this.apinbarreira = apinbarreira;
    }

    public int getApinhealrecived() {
        return apinhealrecived;
    }

    public void setApinhealrecived(int apinhealrecived) {
        this.apinhealrecived = apinhealrecived;
    }

    public int getApinlifearmor() {
        return apinlifearmor;
    }

    public void setApinlifearmor(int apinlifearmor) {
        this.apinlifearmor = apinlifearmor;
    }

    public int getApingeral() {
        return apingeral;
    }

    public void setApingeral(int apgeral) {
        this.apingeral = apgeral;
    }

    public int getApinalvounico() {
        return apinalvounico;
    }

    public void setApinalvounico(int apinalvounico) {
        this.apinalvounico = apinalvounico;
    }

    public int getApinzona() {
        return apinzona;
    }

    public void setApinzona(int apinzona) {
        this.apinzona = apinzona;
    }

    public int getApinCaC() {
        return apinCaC;
    }

    public void setApinCaC(int apinCaC) {
        this.apinCaC = apinCaC;
    }

    public int getApindistance() {
        return apindistance;
    }

    public void setApindistance(int apindistance) {
        this.apindistance = apindistance;
    }

    public int getApinlife() {
        return apinlife;
    }

    public void setApinlife(int apinlife) {
        this.apinlife = apinlife;
    }

    public int getApinblock() {
        return apinblock;
    }

    public void setApinblock(int apinblock) {
        this.apinblock = apinblock;
    }

    public int getApinesquiva() {
        return apinesquiva;
    }

    public void setApinesquiva(int apinesquiva) {
        this.apinesquiva = apinesquiva;
    }

    public int getApininiciativa() {
        return apininiciativa;
    }

    public void setApininiciativa(int apininiciativa) {
        this.apininiciativa = apininiciativa;
    }

    public int getApinblockandesquiva() {
        return apinblockandesquiva;
    }

    public void setApinblockandesquiva(int apinblockandesquiva) {
        this.apinblockandesquiva = apinblockandesquiva;
    }

    public int getApinremovepaandpm() {
        return apinremovepaandpm;
    }

    public void setApinremovepaandpm(int apinremovepaandpm) {
        this.apinremovepaandpm = apinremovepaandpm;
    }

    public int getApinrespmepm() {
        return apinrespmepm;
    }

    public void setApinrespmepm(int apinrespmepm) {
        this.apinrespmepm = apinrespmepm;
    }

    public int getApingolpecritico() {
        return apingolpecritico;
    }

    public void setApingolpecritico(int apingolpecritico) {
        this.apingolpecritico = apingolpecritico;
    }

    public int getApinparada() {
        return apinparada;
    }

    public void setApinparada(int apinparada) {
        this.apinparada = apinparada;
    }

    public int getApindanocritico() {
        return apindanocritico;
    }

    public void setApindanocritico(int apindanocritico) {
        this.apindanocritico = apindanocritico;
    }

    public int getApinbackdmg() {
        return apinbackdmg;
    }

    public void setApinbackdmg(int apinbackdmg) {
        this.apinbackdmg = apinbackdmg;
    }

    public int getApinbeserkdmg() {
        return apinbeserkdmg;
    }

    public void setApinbeserkdmg(int apinbeserkdmg) {
        this.apinbeserkdmg = apinbeserkdmg;
    }

    public int getApinheal() {
        return apinheal;
    }

    public void setApinheal(int apinheal) {
        this.apinheal = apinheal;
    }

    public int getApinresbackdmg() {
        return apinresbackdmg;
    }

    public void setApinresbackdmg(int apinresbackdmg) {
        this.apinresbackdmg = apinresbackdmg;
    }

    public int getApincritialres() {
        return apincritialres;
    }

    public void setApincritialres(int apincritialres) {
        this.apincritialres = apincritialres;
    }

    public int getApinactionpoint() {
        return apinactionpoint;
    }

    public void setApinactionpoint(int apinactionpoint) {
        this.apinactionpoint = apinactionpoint;
    }

    public int getApinmovepoint() {
        return apinmovepoint;
    }

    public void setApinmovepoint(int apinmovepoint) {
        this.apinmovepoint = apinmovepoint;
    }

    public int getApinrangeanddmg() {
        return apinrangeanddmg;
    }

    public void setApinrangeanddmg(int apinrangeanddmg) {
        this.apinrangeanddmg = apinrangeanddmg;
    }

    public int getApinwakfupoint() {
        return apinwakfupoint;
    }

    public void setApinwakfupoint(int apinwakfupoint) {
        this.apinwakfupoint = apinwakfupoint;
    }

    public int getApincontrolanddmg() {
        return apincontrolanddmg;
    }

    public void setApincontrolanddmg(int apincontrolanddmg) {
        this.apincontrolanddmg = apincontrolanddmg;
    }

    public int getApinarteequipar() {
        return apinarteequipar;
    }

    public void setApinarteequipar(int apinarteequipar) {
        this.apinarteequipar = apinarteequipar;
    }

    public int getApinfinalDamage() {
        return apinfinalDamage;
    }

    public void setApinfinalDamage(int apinfinalDamage) {
        this.apinfinalDamage = apinfinalDamage;
    }

    public int getApinreselemental() {
        return apinreselemental;
    }

    public void setApinreselemental(int apinreselemental) {
        this.apinreselemental = apinreselemental;
    }

    public int getPvphp() {
        return pvphp;
    }

    public void setPvphp(int pvphp) {
        this.pvphp = pvphp;
    }

    public int getArteequipar() {
        return arteequipar;
    }

    public void setArteequipar(int arteequipar) {
        this.arteequipar = arteequipar;
    }

    public int getNvfagua() {
        return nvfagua;
    }

    public void setNvfagua(int nvfagua) {
        this.nvfagua = nvfagua;
    }

    public int getNvffogo() {
        return nvffogo;
    }

    public void setNvffogo(int nvffogo) {
        this.nvffogo = nvffogo;
    }

    public int getNvfar() {
        return nvfar;
    }

    public void setNvfar(int nvfar) {
        this.nvfar = nvfar;
    }

    public int getNvfterra() {
        return nvfterra;
    }

    public void setNvfterra(int nvfterra) {
        this.nvfterra = nvfterra;
    }

    public int getNvfgeral() {
        return nvfgeral;
    }

    public void setNvfgeral(int nvfgeral) {
        this.nvfgeral = nvfgeral;
    }

    public int getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(int movespeed) {
        this.movespeed = movespeed;
    }

    public int getRespm() {
        return respm;
    }

    public void setRespm(int respm) {
        this.respm = respm;
    }

    public int getRespa() {
        return respa;
    }

    public void setRespa(int respa) {
        this.respa = respa;
    }
}