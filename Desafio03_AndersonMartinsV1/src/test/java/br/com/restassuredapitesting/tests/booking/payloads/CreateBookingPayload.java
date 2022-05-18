package br.com.restassuredapitesting.tests.booking.payloads;

import org.json.JSONObject;

public class CreateBookingPayload {
    public static JSONObject payloadToCreateBooking (){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2022-05-21");
        bookingDates.put("checkout", "2022-05-02");

        payload.put("firstname", "Anderson");
        payload.put("lastname", "da Silva");
        payload.put("totalprice", "250");
        payload.put("depositpaid", false);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "2 toalhas brancas");

        return payload;
    }
}
