package com.rodrigoad.rodso.wakfustats.Layouts.Decks;

import android.util.Log;

import com.rodrigoad.rodso.wakfustats.Utils.Build;

/**
 * Created by rodso on 26/06/2017.
 */

public class PassiveAttribute {

    public static Build Hupp(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2: HuppPassive1(build);
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8: HuppPassive4(build);
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13:
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16: //inhalation
                    break;
            }
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void HuppPassive4(Build build) {
        if(build.getNivel()>=140){
            build.setIniciative(build.getIniciative()+30);
        }else if(build.getNivel()>=40){
            build.setIniciative(build.getIniciative()+15);
        }
    }

    private static void HuppPassive1(Build build) {
        if(build.getNivel()>=110){
            build.setDmg(build.getDmg()+20);
        }else if(build.getNivel()>=10){
            build.setDmg(build.getDmg()+15);
        }
    }

    public static Build Steamer(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2: SteamerPassive1(build);
                    break;
                case 3: Interception(build);
                    break;
                case 4: SteamerPassive2(build);
                    break;
                case 5: Inhalation(build);
                    break;
                case 6: SteamerPassive3(build);
                    break;
                case 7:
                    break;
                case 8: Motivation(build);
                    break;
                case 9: SteamerPassive5(build);
                    break;
                case 10:
                    break;
                case 11: Medicine(build);
                    break;
                case 12: SteamerPassive7(build);
                    break;
                case 13: rock=true;
                    break;
                case 14: SteamerPassive8(build);
                    break;
                case 15: Carnage(build);
                    break;
                case 16: //inhalation
                    break;
            }
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void SteamerPassive8(Build build) {
        if(build.getNivel()>=170){
            build.setDmg(build.getDmg()-15);
        }else if(build.getNivel()>=70){
            build.setDmg(build.getDmg()-15);
        }
    }

    private static void SteamerPassive7(Build build) {
        if(build.getNivel()>=160){
            build.setDistancedmg(build.getDistancedmg()+15);
            build.setClosecombatdmg(build.getClosecombatdmg()-20);
        }else if(build.getNivel()>=60){
            build.setDistancedmg(build.getDistancedmg()+10);
            build.setClosecombatdmg(build.getClosecombatdmg()-20);
        }
    }

    private static void SteamerPassive5(Build build) {
        if(build.getNivel()>=180){
            build.setWakfupoint(build.getWakfupoint()+2);
            build.setCriticalchance(build.getCriticalchance()+10);
        }else if(build.getNivel()>=80){
            build.setWakfupoint(build.getWakfupoint()+1);
            build.setCriticalchance(build.getCriticalchance()+5);
        }
    }

    private static void SteamerPassive3(Build build) {
        if(build.getNivel()>=140){
            build.setDmg(build.getDmg()-5);
        }else if(build.getNivel()>=40){
            build.setDmg(build.getDmg()-5);
        }
    }

    private static void SteamerPassive2(Build build) {
        if(build.getNivel()>=120){
            build.setDistancedmg(build.getDistancedmg()+15);
        }else if(build.getNivel()>=20){
            build.setDistancedmg(build.getDistancedmg()+10);
        }
    }

    private static void SteamerPassive1(Build build) {
        if(build.getNivel()>=110){
            build.setControl(build.getControl()+3);
        }else if(build.getNivel()>=10){
            build.setControl(build.getControl()+2);
        }
    }

    public static Build Xelor(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2:
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8: XelorPassive4(build);
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13:
                    break;
                case 14: Carnage(build);
                    break;
                case 15: XelorPassive8(build);
                    break;
                case 16: //inhalation
                    break;
            }
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void XelorPassive8(Build build) {
        if(build.getNivel()>=180){
            build.setControl(build.getControl()+2);
        }else if(build.getNivel()>=80){
            build.setControl(build.getControl()+1);
        }
    }

    private static void XelorPassive4(Build build) {
        if(build.getNivel()>=140){
            build.setRange(build.getRange()+1);
            build.setDistancedmg(build.getDistancedmg()+160);
            build.setApmpremove(build.getApmpremove()+40);
        }else if(build.getNivel()>=40){
            build.setDistancedmg(build.getDistancedmg()+40);
            build.setApmpremove(build.getApmpremove()+20);
        }
    }

    public static Build Elio(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        boolean ep3=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2:
                    break;
                case 3: Interception(build);
                    break;
                case 4: ElioPassive2(build);
                    break;
                case 5: Inhalation(build);
                    break;
                case 6: ep3=true;
                    break;
                case 7: Motivation(build);
                    break;
                case 8: ElioPassive4(build);
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13:
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16: //inhalation
                    break;
                case 17:
                    break;
            }
        }
        if(ep3){
            ElioPassive3(build);
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void ElioPassive4(Build build) {
        if(build.getNivel()>=140){
            build.setBeserkdmg(build.getBeserkdmg()+240);
        }else if(build.getNivel()>=40){
            build.setBeserkdmg(build.getBeserkdmg()+80);
        }
    }

    private static void ElioPassive3(Build build) {
        if(build.getRange()>=2){
            build.setMovepoint(build.getMovepoint()+1);
        }
        if(build.getNivel()>=130){
            build.setDistancedmg(build.getDistancedmg()+20);
        }else if(build.getNivel()>=30){
            build.setDistancedmg(build.getDistancedmg()+10);
        }
    }

    private static void ElioPassive2(Build build) {
        if(build.getNivel()>=120){
            build.setWakfupoint(build.getWakfupoint()+2);
        }else if(build.getNivel()>=20){
            build.setWakfupoint(build.getWakfupoint()+1);
        }
    }

    public static Build Enu(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        boolean ep1=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2: ep1=true;
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13:
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16: //inhalation
                    break;
                case 17:
                    break;
            }
        }
        if(ep1){
            EnuPassive1(build);
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void EnuPassive1(Build build) {
        build.setProsp(build.getProsp()+30);
        if(build.getNivel()>=110){
            if(build.getProsp()>=200){
                build.setDmg(build.getDmg()+20);
            }else{
                build.setDmg(build.getDmg()+(build.getProsp()/10));
            }
        }else if(build.getNivel()>=10){
            if(build.getProsp()>=150){
                build.setDmg(build.getDmg()+15);
            }else{
                build.setDmg(build.getDmg()+(build.getProsp()/10));
            }
        }
    }

    public static Build Sad(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2: SadPassive1(build);
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11: SadPassive6(build);
                    break;
                case 12: rock=true;
                    break;
                case 13:
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16: //inhalation
                    break;
                case 17: SadPassive9(build);
                    break;
            }
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void SadPassive9(Build build) {
        if(build.getNivel()>=190){
            build.setApmpremove(build.getApmpremove()+40);
        }else if(build.getNivel()>=90){
            build.setApmpremove(build.getApmpremove()+25);
        }
    }

    private static void SadPassive6(Build build) {
        if(build.getNivel()>=160){
            build.setDomonioCura(build.getDominioCura()+20);
            build.setDmg(build.getDmg()-10);
        }else if(build.getNivel()>=60){
            build.setDomonioCura(build.getDominioCura()+10);
            build.setDmg(build.getDmg()-10);
        }
    }

    private static void SadPassive1(Build build) {
        if(build.getNivel()>=110){
            build.setControl(build.getControl()+3);
        }else if(build.getNivel()>=10){
            build.setControl(build.getControl()+2);
        }
    }

    public static Build Feca(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        boolean fp6=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2: FecaPassive1(build);
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11: fp6=true;
                    break;
                case 12: rock=true;
                    break;
                case 13: FecaPassive7(build);
                    break;
                case 14: Carnage(build);
                    break;
                case 15: FecaPassive8(build);
                    break;
                case 16:
                    break;
                case 17:
                    break;
            }
        }
        if(fp6){
            FecaPassive6(build);
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void FecaPassive8(Build build) {
        if(build.getNivel()>=180){
            build.setBlock(build.getBlock()+200);
        }else if(build.getNivel()>=80){
            build.setBlock(build.getBlock()+80);
        }
    }

    private static void FecaPassive7(Build build) {
        if(build.getNivel()>=170){
            build.setControl(build.getControl()+3);
        }else if(build.getNivel()>=70){
            build.setControl(build.getControl()+2);
        }
    }

    private static void FecaPassive6(Build build) {
        if(build.getNivel()>=160){
            if(build.getBlock()>=750){
                build.setResist(build.getResist()+150);
            }else{
                build.setResist(build.getResist()+(build.getBlock()/5));
            }
            build.setStop(build.getStop()+20);
        }else if(build.getNivel()>=60){
            if(build.getBlock()>=500){
                build.setResist(build.getResist()+100);
            }else{
                build.setResist(build.getResist()+(build.getBlock()/5));
            }
            build.setStop(build.getStop()+15);
        }
    }

    private static void FecaPassive1(Build build) {
        if(build.getNivel()>=110){
            build.setApmpremove(build.getApmpremove()+20);
        }else if(build.getNivel()>=10){

        }
    }

    public static Build Eni(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        boolean ep9=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2: EniPassive1(build);
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9: EniPassive5(build);
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13: EniPassive7(build);
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17: ep9=true;
                    break;
            }
        }
        if(ep9){
            EniPassive9(build);
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void EniPassive9(Build build) {
        if(build.getNivel()>=190){
            if(build.getDominioCura()>=400){
                build.setEvasion(build.getEvasion()+400);
            }else{
                build.setEvasion(build.getEvasion()+build.getDominioCura());
            }
        }else if(build.getNivel()>=90){
            if(build.getDominioCura()>=100){
                build.setEvasion(build.getEvasion()+100);
            }else{
                build.setEvasion(build.getEvasion()+build.getDominioCura());
            }
        }
    }

    private static void EniPassive7(Build build) {
        if(build.getNivel()>=170){
            build.setDmg(build.getDmg()+15);
            build.setDomonioCura(build.getDominioCura()-20);
            build.setResist(build.getResist()+75);
        }else if(build.getNivel()>=70){
            build.setDmg(build.getDmg()+10);
            build.setDomonioCura(build.getDominioCura()-20);
            build.setResist(build.getResist()+50);
        }
    }

    private static void EniPassive5(Build build) {
        if(build.getNivel()>=150){
            build.setAreadmg(build.getAreadmg()+240);
        }else if(build.getNivel()>=50){
            build.setAreadmg(build.getAreadmg()+60);
        }
    }

    private static void EniPassive1(Build build) {
        if(build.getNivel()>=110){
            build.setRange(build.getRange()+2);
            build.setDomonioCura(build.getDominioCura()-5);
        }else if(build.getNivel()>=10){
            build.setRange(build.getRange()+1);
            build.setDomonioCura(build.getDominioCura()-5);
        }
    }

    public static Build Iop(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        boolean ip9=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2:
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13:
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17: ip9=true;
                    break;
                case 18: IopPassive10(build);
                    break;
            }
        }
        if(ip9){
            IopPassive9(build);
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void IopPassive10(Build build) {
        if(build.getNivel()>=200){
            build.setDistancedmg(build.getDistancedmg()+20);
            build.setClosecombatdmg(build.getClosecombatdmg()-20);
        }else if(build.getNivel()>=100){
            build.setDistancedmg(build.getDistancedmg()+10);
            build.setClosecombatdmg(build.getClosecombatdmg()-20);
        }
    }

    private static void IopPassive9(Build build) {
        if(build.getNivel()>=190){
            if(build.getBlock()>=40) {
                build.setCriticalchance(build.getCriticalchance()+20);
            }else{
                build.setCriticalchance(build.getCriticalchance()+(build.getStop()/2));
            }
        }else if(build.getNivel()>=90){
            if(build.getBlock()>=40) {
                build.setCriticalchance(build.getCriticalchance()+20);
            }else{
                build.setCriticalchance(build.getCriticalchance()+(build.getStop()/2));
            }
        }
    }

    public static Build Sac(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        boolean sp2=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2:
                    break;
                case 3: Interception(build);
                    break;
                case 4: sp2=true;
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13: SacPassive7(build);
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17: SacPassive9(build);
                    break;
                case 18:
                    break;
            }
        }
        if(sp2){
            SacPassive2(build);
        }
        if(rock){
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void SacPassive9(Build build) {
        if(build.getNivel()>=190){
            build.setBeserkdmg(build.getBeserkdmg()+240);
        }else if(build.getNivel()>=90){
            build.setBeserkdmg(build.getBeserkdmg()+80);
        }
    }

    private static void SacPassive7(Build build) {
        if(build.getNivel()>=170){
            build.setRange(build.getRange()+1);
        }else if(build.getNivel()>=70){
            build.setRange(build.getRange()+1);
        }
    }

    private static void SacPassive2(Build build) {
        if(build.getNivel()>=120){
            int percent = build.getNivel()*6;
            build.setStop(build.getStop()+20);
        }else if(build.getNivel()>=20){
            int percent = build.getNivel()*4;
            build.setStop(build.getStop()+10);
        }
    }

    public static Build Lad(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        boolean lp4=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2:
                    break;
                case 3: Interception(build);
                    break;
                case 4: LadPassive2(build);
                    break;
                case 5: Inhalation(build);
                    break;
                case 6: LadPassive3(build);
                    break;
                case 7: Motivation(build);
                    break;
                case 8: lp4=true;
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13:
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
            }
        }
        if(lp4){
            LadPassive4(build);
        }
        if(rock) {
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void LadPassive4(Build build) {
        if(build.getNivel()>=140){
            if(build.getIniciative()>=300) {
                build.setEvasion(build.getEvasion() + 300);
                build.setCriticaldmg(build.getCriticaldmg()+300);
            }else {
                build.setEvasion(build.getEvasion() + build.getIniciative());
                build.setCriticaldmg(build.getCriticaldmg() + build.getIniciative());
            }
        }else if(build.getNivel()>=40){
            if(build.getIniciative()>=100) {
                build.setEvasion(build.getEvasion() + 100);
                build.setCriticaldmg(build.getCriticaldmg() + 100);
            }else {
                build.setEvasion(build.getEvasion() + build.getIniciative());
                build.setCriticaldmg(build.getCriticaldmg() + build.getIniciative());
            }
        }
    }

    private static void LadPassive3(Build build) {
        if(build.getNivel()>=130){
            build.setRange(build.getRange()+1);
            build.setControl(build.getControl()+2);
        }else if(build.getNivel()>=30){
            build.setRange(build.getRange()+1);
            build.setControl(build.getControl()+1);
        }
    }

    private static void LadPassive2(Build build) {
        if(build.getNivel()>=120){
            build.setEvasion(build.getEvasion()+240);
            build.setBlock(build.getBlock()-240);
        }else if(build.getNivel()>=20){
            build.setEvasion(build.getEvasion()+80);
            build.setBlock(build.getBlock()-80);
        }
    }

    public static Build Eca(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2:
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6: EcaPassive3(build);
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9: EcaPassive5(build);
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13: EcaPassive7(build);
                    break;
                case 14: Carnage(build);
                    break;
                case 15: EcaPassive8(build);
                    break;
                case 16:
                    break;
                case 17:
                    break;
            }
        }
        if(rock) {
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void EcaPassive8(Build build) {
        if(build.getNivel()>=180){
            build.setDomonioCura(build.getDominioCura()+20);
        }else if(build.getNivel()>=80){
            build.setDomonioCura(build.getDominioCura()+10);
        }
    }

    private static void EcaPassive7(Build build) {
        if(build.getNivel()>=170){
            build.setClosecombatdmg(build.getClosecombatdmg()+200);
        }else if(build.getNivel()>=70){
            build.setClosecombatdmg(build.getClosecombatdmg()+50);
        }
    }

    private static void EcaPassive5(Build build) {
        if(build.getNivel()>=150){
            build.setStop(build.getStop() + 25);
        }else if(build.getNivel()>=50){
            build.setStop(build.getStop() + 15);
        }
    }

    private static void EcaPassive3(Build build) {
        if(build.getNivel()>=130){
            build.setCriticalchance(build.getCriticalchance()+10);
        }else if(build.getNivel()>=30){
            build.setCriticalchance(build.getCriticalchance()+10);
        }
    }

    public static Build Panda(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2: PandaPassive1(build);
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13: PandaPassive7(build);
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17: PandaPassive9(build);
                    break;
            }
        }
        if(rock) {
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void PandaPassive9(Build build) {
        if(build.getNivel()>=190){
            build.setDmg(build.getDmg()-10);
        }else if(build.getNivel()>=90){
            build.setDmg(build.getDmg()-10);
        }
    }

    private static void PandaPassive7(Build build) {
        if(build.getNivel()>=170){
            build.setDmg(build.getDmg()+15);
            build.setResist(build.getResist()-50);
        }else if(build.getNivel()>=70){
            build.setDmg(build.getDmg()+10);
            build.setResist(build.getResist()-50);
        }
    }

    private static void PandaPassive1(Build build) {
        if(build.getNivel()>=110){
            build.setDomonioCura(build.getDominioCura()+20);
            build.setDmg(build.getDmg()-10);
        }else if(build.getNivel()>=10){
            build.setDomonioCura(build.getDominioCura()+10);
            build.setDmg(build.getDmg()-10);
        }
    }

    public static Build Sram(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        boolean sp10=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2:
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9: SramPassive5(build);
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13: SramPassive7(build);
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18: sp10=true;
                    break;
            }
        }
        if(sp10){
            SramPassive10(build);
        }
        if(rock) {
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void SramPassive10(Build build) {
        if(build.getNivel()>=200){
            //cuida do block
            if(build.getBlock()>=300) {
                build.setCriticalchance(build.getCriticalchance() + (300/20));
            }else{
                build.setCriticalchance(build.getCriticalchance() + (build.getBlock()/20));
            }
            //cuida da esquiva
            if(build.getEvasion()>=300) {
                build.setCriticalchance(build.getCriticalchance() + (300/20));
            }else{
                build.setCriticalchance(build.getCriticalchance() + (build.getEvasion()/20));
            }
        }else if(build.getNivel()>=100){
            //cuida do block
            if(build.getBlock()>=200) {
                build.setCriticalchance(build.getCriticalchance() + (200/20));
            }else{
                build.setCriticalchance(build.getCriticalchance() + (build.getBlock()/20));
            }
            //cuida da esquiva
            if(build.getEvasion()>=200) {
                build.setCriticalchance(build.getCriticalchance() + (200/20));
            }else{
                build.setCriticalchance(build.getCriticalchance() + (build.getEvasion()/20));
            }
        }
    }

    private static void SramPassive7(Build build) {
        if(build.getNivel()>=170){
            build.setAreadmg(build.getAreadmg()+160);
            build.setDistancedmg(build.getDistancedmg()+160);
        }else if(build.getNivel()>=70){
            build.setAreadmg(build.getAreadmg()+40);
            build.setDistancedmg(build.getDistancedmg()+40);
        }
    }

    private static void SramPassive5(Build build) {
        if(build.getNivel()>=150){
            build.setControl(build.getControl()+4);
        }else if(build.getNivel()>=50){
            build.setControl(build.getControl()+2);
        }
    }

    public static Build Zob(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        boolean zp2=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2:
                    break;
                case 3: Interception(build);
                    break;
                case 4: zp2=true;
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8:
                    break;
                case 9: ZobPassive5(build);
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13:
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16:
                    break;
            }
        }
        if(zp2){
            ZobPassive2(build);
        }
        if(rock) {
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    private static void ZobPassive5(Build build) {
        if(build.getNivel()>=150){
            build.setBackdmg(build.getBackdmg()+200);
        }else if(build.getNivel()>50){
            build.setBackdmg(build.getBackdmg()+50);
        }
    }

    private static void ZobPassive2(Build build) {
        if(build.getNivel()>=120){
            if(build.getEvasion()>=800){
                build.setClosecombatdmg(build.getClosecombatdmg()+400);
                build.setDomonioCura(build.getDominioCura()+400);
            }else{
                build.setClosecombatdmg(build.getClosecombatdmg()+(build.getEvasion()/2));
                build.setDomonioCura(build.getDominioCura()+(build.getEvasion()/2));
            }
        }else if(build.getNivel()>=20){
            if(build.getEvasion()>=200){
                build.setClosecombatdmg(build.getClosecombatdmg()+100);
                build.setDomonioCura(build.getDominioCura()+100);
            }else{
                build.setClosecombatdmg(build.getClosecombatdmg()+(build.getEvasion()/2));
                build.setDomonioCura(build.getDominioCura()+(build.getEvasion()/2));
            }
        }
    }

    public static Build Osa(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2: OsaPassive1(build);
                    break;
                case 3: Interception(build);
                    break;
                case 4:
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8: OsaPassive4(build);
                    break;
                case 9: Medicine(build);
                    break;
                case 10:
                    break;
                case 11: rock=true;
                    break;
                case 12:
                    break;
                case 13: Carnage(build);
                    break;
                case 14:
                    break;
                case 15:
                    break;
            }
        }
        if(rock) {
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    public static void OsaPassive4(Build build){
        if(build.getNivel()>=140){
            build.setResist(build.getResist()-50);
            build.setDmgwater(build.getDmgwater()+80);
        }else if(build.getNivel()>=40){
            build.setResist(build.getResist()-75);
            build.setDmgwater(build.getDmgwater()+70);
        }
    }

    public static void OsaPassive1(Build build){
        if(build.getNivel()>=110){
            build.setControl(build.getControl()+2);
        }else if(build.getNivel()>=10){
            build.setControl(build.getControl()+1);
        }
    }

    public static Build Cra(Build build){
        int passives[] = new int[6];
        passives[0] = build.getPSpell1();
        passives[1] = build.getPSpell2();
        passives[2] = build.getPSpell3();
        passives[3] = build.getPSpell4();
        passives[4] = build.getPSpell5();
        passives[5] = build.getPSpell6();
        boolean rock=false;
        for(int i=0;i<passives.length;i++){
            switch (passives[i]){
                case 1: Evasion(build);
                    break;
                case 2:
                    break;
                case 3: Interception(build);
                    break;
                case 4: CraPassive2(build);
                    break;
                case 5: Inhalation(build);
                    break;
                case 6:
                    break;
                case 7: Motivation(build);
                    break;
                case 8: CraPassive4(build);
                    break;
                case 9:
                    break;
                case 10: Medicine(build);
                    break;
                case 11:
                    break;
                case 12: rock=true;
                    break;
                case 13:
                    break;
                case 14: Carnage(build);
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17: CraPassive9(build);
                    break;
            }
        }
        if(rock) {
            Rock(build);
        }else{
            VidaCalc(build);
        }
        return build;
    }

    public static void VidaCalc(Build build){
        int vidasemporcentagem = (build.getVitalpoint() + (build.getApinlife() * 20) + ((build.getNivel() / 20) * 100));
        double porcentagem = 1.0 + ((build.getApinlifepercent() * 4.0) / 100);
        double vida = vidasemporcentagem * porcentagem;
        build.setVitalpoint((int)vida);
    }

    public static void CraPassive9(Build build){
        if(build.getNivel()>=200){
            build.setCriticalchance(build.getCriticalchance()+20);
            build.setCriticaldmg(build.getCriticaldmg()+200);
        }else if(build.getNivel()>=100){
            build.setCriticalchance(build.getCriticalchance()+10);
            build.setCriticaldmg(build.getCriticaldmg()+50);
        }
    }

    public static void CraPassive4(Build build){
        if(build.getNivel()>=140){
            build.setControl(build.getControl()+2);
        }else if(build.getNivel()>=40){
            build.setControl(build.getControl()+1);
        }
    }

    public static void CraPassive2(Build build){
        if(build.getNivel()>=120){
            build.setRange(build.getRange()+3);
            build.setFinaldamage(build.getFinaldamage() + (3*build.getRange()));
        }else if(build.getNivel()>=20){
            build.setRange(build.getRange()+2);
            build.setFinaldamage(build.getFinaldamage() + (2*build.getRange()));
        }
    }

    public static void Carnage(Build build){
        if(build.getNivel()>=175){
            build.setFinaldamage(build.getFinaldamage()+15);
            build.setHeal(build.getHeal()-30);
        }else if(build.getNivel()>=75){
            build.setFinaldamage(build.getFinaldamage()+10);
            build.setHeal(build.getHeal()-30);
        }
    }

    public static void Rock(Build build){
        if(build.getNivel()>=165){
            build.setFinaldamage(build.getFinaldamage()-25);
            build.setHeal(build.getHeal()-50);
            build.setHealreceived(build.getHealreceived()+25);
            int vidasemporcentagem = (build.getVitalpoint() + (build.getApinlife() * 20) + ((build.getNivel() / 20) * 100));
            double porcentagem = 1.0 + ((build.getApinlifepercent() * 4.0) / 100);
            double vida = vidasemporcentagem * porcentagem;
            build.setVitalpoint((int)(0.6*vida)+(int)vida);
        }else if(build.getNivel()>=65){
            build.setFinaldamage(build.getFinaldamage()-25);
            build.setHeal(build.getHeal()-50);
            build.setHealreceived(build.getHealreceived()+20);
            int vidasemporcentagem = (build.getVitalpoint() + (build.getApinlife() * 20) + ((build.getNivel() / 20) * 100));
            double porcentagem = 1.0 + ((build.getApinlifepercent() * 4.0) / 100);
            double vida = vidasemporcentagem * porcentagem;
            build.setVitalpoint((int)(0.3*vida)+(int)vida);
        }
    }

    public static void Medicine(Build build){
        if(build.getNivel()>=155){
            build.setHeal(build.getHeal()+30);
            build.setFinaldamage(build.getFinaldamage()-15);
        }else if(build.getNivel()>=55){
            build.setHeal(build.getHeal()+25);
            build.setFinaldamage(build.getFinaldamage()-15);
        }
    }

    public static void Motivation(Build build){
        if(build.getNivel()>=135){
            build.setActionpoint(build.getActionpoint()+1);
            build.setApmpremove(build.getApmpremove()+20);
            build.setFinaldamage(build.getFinaldamage()-20);
        }else if(build.getNivel()>=35){
            build.setActionpoint(build.getActionpoint()+1);
            build.setApmpremove(build.getApmpremove()+10);
            build.setFinaldamage(build.getFinaldamage()-20);
        }
    }

    public static void Inhalation(Build build){
        if(build.getNivel()>=125){
            build.setIniciative(build.getIniciative()+120);
        }else if(build.getNivel()>=25){
            build.setIniciative(build.getIniciative()+60);
        }
    }

    public static void Interception(Build build){
        if(build.getNivel()>=115){
            build.setBlock(build.getBlock()+180);
        }else if(build.getNivel()>=15){
            build.setBlock(build.getBlock()+60);
        }
    }

    public static void Evasion(Build build){
        Log.e("EVASION","Passou no evasion");
        if(build.getNivel()>=110){
            build.setEvasion(build.getEvasion()+180);
        }else if(build.getNivel()>=10){
            build.setEvasion(build.getEvasion()+60);
        }
    }

    public static Build setPoints(Build build) {
        //major
        build.setActionpoint(build.getActionpoint() + build.getApinactionpoint());
        build.setMovepoint(build.getMovepoint() + build.getApinmovepoint());
        build.setWakfupoint(build.getWakfupoint()  + (build.getApinwakfupoint() * 2));
        build.setFinaldamage((build.getApinfinalDamage()*10) + build.getFinaldamage());
        build.setRange((build.getApinrangeanddmg() * 1)+build.getRange());
        build.setControl(build.getControl() + (build.getApincontrolanddmg() * 2));
        build.setResist(build.getResist()+(build.getApinreselemental() == 1 ? 50 : 0)+ (build.getApinresele() * 10));

        //sorte
        build.setCriticalchance(build.getCriticalchance() + (build.getApingolpecritico() * 1));
        build.setStop(build.getStop() + (build.getApinparada()*1));
        build.setCriticaldmg(build.getCriticaldmg() + (build.getApindanocritico() * 4));
        build.setBackdmg(build.getBackdmg() + (build.getApinbackdmg() * 6));
        build.setBeserkdmg(build.getBeserkdmg() + (build.getApinbeserkdmg() * 8));
        build.setDomonioCura(build.getDominioCura()+ (build.getApinheal() * 6));
        build.setResblackdmg(build.getResblackdmg() + (build.getApinresbackdmg() * 4));
        build.setRescriticaldmg(build.getRescriticaldmg() + (build.getApincritialres() * 4));

        //agilidade
        build.setBlock(build.getBlock() + (build.getApinblock() * 6) + (build.getApinblockandesquiva() * 4));
        build.setEvasion(build.getEvasion() + (build.getApinesquiva() * 6) + (build.getApinblockandesquiva() * 4));
        build.setIniciative(build.getIniciative() + (build.getApininiciativa() * 4));
        build.setApmpremove((build.getApinremovepaandpm() > 0 ? build.getApinremovepaandpm() * 2 : 0) + build.getApmpremove());
        build.setRespa(build.getRespa() + (build.getApinrespmepm() > 0 ? build.getApinrespmepm() * 2 : 0));
        build.setRespm(build.getRespm() + (build.getApinrespmepm() > 0 ? build.getApinrespmepm() * 2 : 0));

        //str
        build.setAreadmg(build.getAreadmg() + (build.getApinzona() * 8));
        build.setOnetargetdmg(build.getOnetargetdmg() + (build.getApinalvounico() * 8));
        build.setDistancedmg(build.getDistancedmg() + (build.getApindistance() * 8));
        build.setClosecombatdmg(build.getClosecombatdmg() + (build.getApinCaC() * 8));

        //int
        build.setHealreceived((build.getApinheal()*6) + build.getHealreceived());
        return build;
    }
}
