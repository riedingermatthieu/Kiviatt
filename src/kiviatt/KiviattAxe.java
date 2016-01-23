/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiviatt;

/**
 *
 * @author riedinma
 */
public class KiviattAxe {

    private String nom;
    private int valeur;

    private int vMin;
    private int vMax;
    private int index;
    private double uniformValeur;

    private int xValeur;
    private int yValeur;
    private boolean selected;

    private int xDepart, yDepart;
    private int xArrivee, yArrivee;

    public int getxLabel() {
        return xLabel;
    }

    public void setxLabel(int xLabel) {
        this.xLabel = xLabel;
    }

    public int getxLabelvMin() {
        return xLabelvMin;
    }

    public void setxLabelvMin(int xLabelvMin) {
        this.xLabelvMin = xLabelvMin;
    }

    public int getxLabelvMax() {
        return xLabelvMax;
    }

    public void setxLabelvMax(int xLabelvMax) {
        this.xLabelvMax = xLabelvMax;
    }

    public int getyLabel() {
        return yLabel;
    }

    public void setyLabel(int yLabel) {
        this.yLabel = yLabel;
    }

    public int getyLabelvMin() {
        return yLabelvMin;
    }

    public void setyLabelvMin(int yLabelvMin) {
        this.yLabelvMin = yLabelvMin;
    }

    public int getyLabelvMax() {
        return yLabelvMax;
    }

    public void setyLabelvMax(int yLabelvMax) {
        this.yLabelvMax = yLabelvMax;
    }

    private int xLabel, xLabelvMin, xLabelvMax;
    private int yLabel, yLabelvMin, yLabelvMax;

    public int getxDepart() {
        return xDepart;
    }

    public void setxDepart(int xDepart) {
        this.xDepart = xDepart;
    }

    public int getyDepart() {
        return yDepart;
    }

    public void setyDepart(int yDepart) {
        this.yDepart = yDepart;
    }

    public int getxArrivee() {
        return xArrivee;
    }

    public void setxArrivee(int xArrivee) {
        this.xArrivee = xArrivee;
    }

    public int getyArrivee() {
        return yArrivee;
    }

    public void setyArrivee(int yArrivee) {
        this.yArrivee = yArrivee;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public double getUniformValeur() {
        return uniformValeur;
    }

    public void setUniformValeur(double uniformValeur) {
        this.uniformValeur = uniformValeur;
    }

    public int getxValeur() {
        return xValeur;
    }

    public void setxValeur(int xValeurCentree) {
        this.xValeur = xValeurCentree;
    }

    public int getyValeur() {
        return yValeur;
    }

    public void setyValeur(int yValeurCentree) {
        this.yValeur = yValeurCentree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getvMin() {
        return vMin;
    }

    public void setvMin(int vMin) {
        this.vMin = vMin;
    }

    public int getvMax() {
        return vMax;
    }

    public void setvMax(int vMax) {
        this.vMax = vMax;
    }

    public double getUniformValue() {
        return (1 - valeur / vMin) * ((double) vMin / (vMin - vMax));
    }

    int getNewValueFromXY(int x, int y) {
//        System.out.println("x:"+x+"xDepart:"+xDepart);
        double t = (double) (x - xDepart) / (double) (xArrivee - xDepart);
        if (t > 1) {
            t = 1;
        }
        if (t < 0) {
            t = 0;
        }
//        System.out.println(t);
        double v = vMin + t * (vMax - vMin);
        return (int) v;
    }
}
