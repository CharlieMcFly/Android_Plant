package android.univ.lille1.fr.forplants.data;


import android.univ.lille1.fr.forplants.util.ActivityUtils;
import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by charlie on 24/11/16.
 */
public class Plant implements Serializable{
    private int id;
    private String nom;
    private String description;
    private int freq;
    private String date;
    private String dateArrosage;


    public Plant(String nom, String description, int freq) {
        this.nom = nom;

        this.description = description;
        this.freq = freq;
        this.date = ActivityUtils.getDATE_SYSTEM();
        this.dateArrosage = addXday(freq);
    }

    public Plant() {

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getDateArrosage() {
        return dateArrosage;
    }

    public void setDateArrosage(String dateArrosage) {
        this.dateArrosage = dateArrosage;
    }

    public String addXday(int nbDay) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(this.date));
        } catch (ParseException e) {
            return this.date;
        }
        c.add(Calendar.DATE, nbDay);
        return sdf.format(c.getTime());
    }

    public String diminuerJourArrosage(String dateArrosage){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateArrosage));
        } catch (ParseException e) {
            Log.d("ERROR", "parseur error");
        }
        c.add(Calendar.DATE, -1);
        return sdf.format(c.getTime());
    }
}
