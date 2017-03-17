package com.buildswakfu.rodrigo.buildswakfu.Utils;

/**
 * Created by rodso on 17/06/2016.
 */

public class Item{

    private int codigo;
    private String tipo;
    private String nome;
    private int nivel;
    private int raridade;
    private int heal;
    private int range;
    private int beserkdmg;
    private int block;
    private int control;
    private int dmg;
    private int criticalchance;
    private int backdmg;
    private int distancedmg;
    private int closecombatdmg;
    private int element1dmg;
    private int element2dmg;
    private int element3dmg;
    private int areadmg;
    private int onetargetdmg;
    private int evasion;
    private int criticaldmg;
    private int iniciative;
    private int stop;
    private int prosp;
    private int pwmax;
    private int resist;
    private int sabedoria;
    private int reswater;
    private int resfire;
    private int resair;
    private int researth;
    private int resblackdmg;
    private int rescriticaldmg;
    private int res1elerandom;
    private int res2elerandom;
    private int res3elerandom;
    private int actionpoint;
    private int movepoint;
    private int vitalpoint;
    private int wakfupoint;
    private int dmgfogo;
    private int dmgterra;
    private int dmgagua;
    private int dmgar;
    private int minertake;
    private String status;
    private String link;
    private int pvphp;
    private int singular;
    private int arteequipar;
    private int nvfagua;
    private int nvffogo;
    private int nvfar;
    private int nvfterra;
    private int nvfgeral;
    private int movespeed;
    private int respm;
    private int respa;

    public Item(){
        //for firebase
    }

    public Item(int codigo, String tipo, String nome, int nivel, int raridade, int heal, int range, int beserkdmg, int block, int control, int dmg, int criticalchance, int backdmg, int distancedmg, int closecombatdmg, int element1dmg, int element2dmg, int element3dmg, int areadmg, int onetargetdmg, int evasion, int criticaldmg, int iniciative, int stop, int prosp, int pwmax, int resist, int sabedoria, int reswater, int resfire, int resair, int researth, int resblackdmg, int rescriticaldmg, int res1elerandom, int res2elerandom, int res3elerandom, int actionpoint, int movepoint, int vitalpoint, int wakfupoint, int dmgfogo, int dmgterra, int dmgagua, int dmgar, int minertake, String status, String link, int pvphp, int singular, int arteequipar, int nvfagua, int nvffogo, int nvfar, int nvfterra, int nvfgeral, int movespeed, int respm, int respa) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.nome = nome;
        this.nivel = nivel;
        this.raridade = raridade;
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
        this.dmgfogo = dmgfogo;
        this.dmgterra = dmgterra;
        this.dmgagua = dmgagua;
        this.dmgar = dmgar;
        this.minertake = minertake;
        this.status = status;
        this.link = link;
        this.pvphp = pvphp;
        this.singular = singular;
        this.arteequipar = arteequipar;
        this.nvfagua = nvfagua;
        this.nvffogo = nvffogo;
        this.nvfar = nvfar;
        this.nvfterra = nvfterra;
        this.nvfgeral = nvfgeral;
        this.movespeed = movespeed;
        this.respm = respm;
        this.respa = respa;
    }

    //gets _______________________________________________________________________________________________________________________
    public int getRespa() {
        return respa;
    }
    public int getRespm() {
        return respm;
    }
    public int getMovespeed() {
        return movespeed;
    }
    public int getNvfgeral() {
        return nvfgeral;
    }
    public int getNvfterra() {
        return nvfterra;
    }
    public int getNvfar() {
        return nvfar;
    }
    public int getNvffogo() {
        return nvffogo;
    }
    public int getNvfagua() {
        return nvfagua;
    }
    public int getArteequipar() {
        return arteequipar;
    }
    public int getSingular() {
        return singular;
    }
    public int getPvphp() {  return pvphp;   }
    public int getMinertake(){ return minertake; }
    public int getDmgar(){ return dmgar; }
    public int getDmgagua(){ return dmgagua; }
    public int getDmgterra(){ return dmgterra; }
    public int getDmgfogo(){ return dmgfogo; }
    public String getTipo() { return tipo;   }
    public String getNome() {        return nome;    }
    public int getNivel() {        return nivel;    }
    public int getHeal() {        return heal;    }
    public int getRange() {        return range;    }
    public int getBeserkdmg() {        return beserkdmg;    }
    public int getBlock() {        return block;    }
    public int getControl() {        return control;    }
    public String getStatus() {        return status;    }
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
    public String getLink(){    return link;    }
    public int getCodigo(){     return codigo;  }
    public int getRaridade(){ return raridade;  }

    //sets_______________________________________________________________________________________________________________________
    public void setMinertake(int minertake){ this.minertake = minertake; }
    public void setDmgar(int dmgar){ this.dmgar = dmgar; }
    public void setDmgagua(int dmgagua){ this.dmgagua = dmgagua; }
    public void setDmgterra(int dmgterra){ this.dmgterra = dmgterra; }
    public void setDmgfogo(int dmgfogo){ this.dmgfogo = dmgfogo; }
    public void setTipo(String tipo) {        this.tipo = tipo;    }
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
    public void setStatus(String status) { this.status = status;    }
    public void setLink(String link){ this.link = link; }
    public void setCodigo(int codigo){ this.codigo = codigo;    }
    public void setRaridade(int raridade){ this.raridade = raridade;    }
    public void setPvphp(int pvphp) {
        this.pvphp = pvphp;
    }
    public void setSingular(int singular) {
        this.singular = singular;
    }
    public void setArteequipar(int arteequipar) {
        this.arteequipar = arteequipar;
    }
    public void setNvfagua(int nvfagua) {
        this.nvfagua = nvfagua;
    }
    public void setNvffogo(int nvffogo) {
        this.nvffogo = nvffogo;
    }
    public void setNvfar(int nvfar) {     this.nvfar = nvfar;   }
    public void setNvfterra(int nvfterra) {
        this.nvfterra = nvfterra;
    }
    public void setNvfgeral(int nvfgeral) {
        this.nvfgeral = nvfgeral;
    }
    public void setMovespeed(int movespeed) {
        this.movespeed = movespeed;
    }
    public void setRespm(int respm) {
        this.respm = respm;
    }
    public void setRespa(int respa) {
        this.respa = respa;
    }

}
