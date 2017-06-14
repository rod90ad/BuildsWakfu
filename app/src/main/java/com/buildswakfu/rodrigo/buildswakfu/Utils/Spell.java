package com.buildswakfu.rodrigo.buildswakfu.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodso on 11/05/2017.
 */

public class Spell{

    private boolean active=true;
    private String name="";
    private String classe="";
    private int level=0;
    private int basedmg=0;
    private double scale =0;
    private String description="";
    private ArrayList<String> lines;
    private int pa_used=0;
    private int wakfu_used=0;
    private int pm_used=0;
    private int range_ini=0;
    private int range_end=0;
    private boolean range_mod=true;
    private boolean linear=true;
    private boolean area=true;
    private boolean diagonal=true;
    private boolean linhadevisao=true;
    private ArrayList<String> conditions;
    private String image;

    public Spell(boolean active, String name, int level, double scale, String description, ArrayList<String> lines, int pa_used, int range_ini, int range_end, boolean range_mod, boolean linear, boolean area, ArrayList<String> conditions, String image) {
        this.active = active;
        this.name = name;
        this.level = level;
        this.scale = scale;
        this.description = description;
        this.lines = lines;
        this.pa_used = pa_used;
        this.range_ini = range_ini;
        this.range_end = range_end;
        this.range_mod = range_mod;
        this.linear = linear;
        this.area = area;
        this.conditions = conditions;
        this.image = image;
    }

    public Spell(){

    }

    public int getBasedmg() {
        return basedmg;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setBasedmg(int basedmg) {
        this.basedmg = basedmg;
    }

    public int getWakfu_used() {
        return wakfu_used;
    }

    public void setWakfu_used(int wakfu_used) {
        this.wakfu_used = wakfu_used;
    }

    public int getPm_used() {
        return pm_used;
    }

    public void setPm_used(int pm_used) {
        this.pm_used = pm_used;
    }

    public boolean isLinhadevisao() {
        return linhadevisao;
    }

    public void setLinhadevisao(boolean linhadevisao) {
        this.linhadevisao = linhadevisao;
    }

    public boolean isDiagonal() {
        return diagonal;
    }

    public void setDiagonal(boolean diagonal) {
        this.diagonal = diagonal;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    public int getPa_used() {
        return pa_used;
    }

    public void setPa_used(int pa_used) {
        this.pa_used = pa_used;
    }

    public int getRange_ini() {
        return range_ini;
    }

    public void setRange_ini(int range_ini) {
        this.range_ini = range_ini;
    }

    public int getRange_end() {
        return range_end;
    }

    public void setRange_end(int range_end) {
        this.range_end = range_end;
    }

    public boolean isRange_mod() {
        return range_mod;
    }

    public void setRange_mod(boolean range_mod) {
        this.range_mod = range_mod;
    }

    public boolean isLinear() {
        return linear;
    }

    public void setLinear(boolean linear) {
        this.linear = linear;
    }

    public boolean isArea() {
        return area;
    }

    public void setArea(boolean area) {
        this.area = area;
    }

    public ArrayList<String> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<String> conditions) {
        this.conditions = conditions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
