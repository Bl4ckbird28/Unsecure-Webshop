package de.fhws.biedermann.webshop.utils.logic;

import de.fhws.biedermann.webshop.database.DataAccessShopDatabase;

import javax.annotation.Nullable;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogic
{

    static DataAccessShopDatabase dasb = new DataAccessShopDatabase();

    public static Nullable updatePassword(String sessionId, String oldPass, String newPass ) {
        if ( !oldPass.equals( dasb.getPassword(dasb.getUserId( sessionId ) ) ) ) {
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Das alte " +
                    "Nutzerkennwort ist nicht korrekt.").build());
        }

        if ( !isValidPassword(oldPass, newPass ) ) {
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Das Nutzerkennwort " +
                    "erfüllt nicht alle Anforderungen oder unterscheidet sich nicht vom alten.").build());
        }

        dasb.putPassword(newPass, dasb.getUserId( sessionId ));
        return null;
    }

    public static boolean isValidPassword(String oldPass, String newPass) {

        if ( oldPass.equals(newPass) || newPass.length() < 8 || newPass.length() > 40 ) { // Passwort ist maximal nur 40 Zeichen lang
            return false;
        }

        // Count uppercase characters
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(newPass);
        long uppercaseCount = matcher.results().count();

        // Count num characters
        pattern = Pattern.compile("\\d");
        matcher = pattern.matcher(newPass);
        long numCount = matcher.results().count();

        // Count special characters
        pattern = Pattern.compile( "[^a-zA-Z0-9\"\']");
        matcher = pattern.matcher(newPass);
        long specialCharCount = matcher.results().count();
//        System.out.println( "upper: " + uppercaseCount + ", num: " + numCount + ", special: " + specialCharCount );

        return uppercaseCount >= 1 && numCount >= 1 && specialCharCount >= 1;
    }
}
