package android.univ.lille1.fr.forplants.util;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;;import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by charlie on 24/11/16.
 */

public class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static String DATE_SYSTEM;

    public static String getDATE_SYSTEM(){
        if( DATE_SYSTEM == null) {
            SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
            return myFormat.format(new Date());
        }
        return DATE_SYSTEM;
    }

    /**
     * Ajoute un jour a la date systeme (c'est une fake date qui reprends sa valeur à la fin)
     */
    public static void add1day() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(getDATE_SYSTEM()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);
        DATE_SYSTEM = sdf.format(c.getTime());
        Log.d("DATE",DATE_SYSTEM);
    }

    /**
     * Renvoie le nombre de jours qui sépare 2 dates
     */
    public static long dayBetweenTwoDate(String dateArrosage){
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1 = myFormat.parse(getDATE_SYSTEM());
            Date date2 = myFormat.parse(dateArrosage);
            long diff = date2.getTime() - date1.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
