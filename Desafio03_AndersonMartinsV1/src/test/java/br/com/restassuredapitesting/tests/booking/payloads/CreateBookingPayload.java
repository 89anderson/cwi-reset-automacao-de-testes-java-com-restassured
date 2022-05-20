package br.com.restassuredapitesting.tests.booking.payloads;

import com.github.javafaker.Faker;
import org.json.JSONObject;

public class CreateBookingPayload {
    public static JSONObject payloadToCreateBooking (){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        Faker javafaker = new Faker();

        bookingDates.put("checkin", "2022-05-21");
        bookingDates.put("checkout", "2022-05-02");

        payload.put("firstname", javafaker.harryPotter().character());
        payload.put("lastname", javafaker.artist().name());
        payload.put("totalprice", "250");
        payload.put("depositpaid", false);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "2 toalhas brancas");

        return payload;
    }

    public static JSONObject payloadInvalidoParaCriarReserva (){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        Faker javafaker = new Faker();

        bookingDates.put("checkin", "21-13-1999");
        bookingDates.put("checkout", "hoje");

        payload.put("firstname", javafaker.harryPotter().character());
        payload.put("lastname", 123);
        payload.put("totalprice", "duzentão");
        payload.put("depositpaid", "não");
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", 10);

        return payload;
    }

    public static JSONObject payloadParaCriarReservaComParametroExtra (){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        Faker javafaker = new Faker();

        bookingDates.put("checkin", "2022-05-21");
        bookingDates.put("checkout", "2022-05-02");

        payload.put("firstname", javafaker.harryPotter().character());
        payload.put("lastname", javafaker.artist().name());
        payload.put("totalprice", "250");
        payload.put("depositpaid", false);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "2 toalhas brancas");
        payload.put("signo", "crocodilo");
        payload.put("inter>gremio", true);

        return payload;
    }
}
