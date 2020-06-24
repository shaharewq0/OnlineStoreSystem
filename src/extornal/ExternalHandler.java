package extornal;

import Domain.Logs.ErrorLogger;
import extornal.ExternalLog.ExternalLog;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.concurrent.Future;


public class ExternalHandler {

    private static ExternalHandler instance;
    private final String WEB_PAGE = "https://cs-bgu-wsep.herokuapp.com/";

    private ExternalHandler() {
    }

    public static ExternalHandler getInstance() {
        if (instance == null) {
            instance = new ExternalHandler();
        }

        return instance;
    }


    public static void main(String[] args) {
        System.out.println(ExternalHandler.getInstance().handshake());
        System.out.println(ExternalHandler.getInstance().pay("2222-3333-4444-5555", "04/21", "Israel Israelovice", "262", "20444444"));
        System.out.println(ExternalHandler.getInstance().cancel_pay(20123));
        System.out.println(ExternalHandler.getInstance().supply("Israel", "Beer Sheva", "Rager Blvd 12", "8458527", "Israel Israelovice"));
        System.out.println(ExternalHandler.getInstance().cancel_supply(30525));
    }


    // ---------------------------------------------------------------------- init, encode, send, decode ----------------------------------------------------------------------

    private HttpURLConnection setHTTPSconnection() throws IOException {
            URL url = new URL(WEB_PAGE);
            URLConnection con = null;
            con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");


            // set time out
            http.setConnectTimeout(10000);
            http.setReadTimeout(10000);

            return http;
    }

    private String send(byte[] out, HttpURLConnection http) throws IOException {
        http.setFixedLengthStreamingMode(out.length);
        http.connect();

        try (OutputStream os = http.getOutputStream()) {
            os.write(out);
        } catch (Exception e) {
            return null;
        }

        if (!http.getResponseMessage().equals("OK")) {
            throw new IOException("failed to receive a respond from the external system!");
        }

        Scanner s = new Scanner(http.getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : null;
    }


    private String ssend(byte[] out) {
        //  String response = null;

        senderThread sThread = new senderThread(out);
        sThread.start();

        synchronized (sThread) {
            try {
                sThread.wait(20000);
                if(sThread.response == null)
                    ErrorLogger.GetInstance().Add_Log("------------------!!!Fail to get msg in time from server!!!------------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return sThread.response;
    }


    private final class senderThread extends Thread {
        String response=null;
        final byte[] out;

        senderThread(byte[] out) {
            this.out = out;
        }
        @Override
        public void run() {
                try {
                    HttpURLConnection http = setHTTPSconnection();
                    response = send(out, http);
                    http.disconnect();
                } catch (IOException e) {
                    ExternalLog.GetInstance().Add_Log("ERROR : Failed to access external systems! rollback.....");
                }

            synchronized (this) {
                notify();
            }
        }
    }


    private byte[] getParameters(Map<String, String> arguments) {
        StringJoiner sj = new StringJoiner("&");

        for (Map.Entry<String, String> entry : arguments.entrySet()) {
            try {
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }

        return sj.toString().getBytes(StandardCharsets.UTF_8);
    }


    // ---------------------------------------------------------------------- argument creators ----------------------------------------------------------------------


    private Map<String, String> handshake_param() {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("action_type", "handshake");

        return arguments;
    }

    private Map<String, String> pay_param(String card, String date, String owner, String cvv, String OwnerID) {

        String[] edate = date.split("/");
        String month = edate[0];
        String year = "20" + edate[0];

        Map<String, String> arguments = new HashMap<>();
        arguments.put("action_type", "pay");
        arguments.put("card_number", card);
        arguments.put("month", month);
        arguments.put("year", year);
        arguments.put("holder", owner);
        arguments.put("ccv", cvv);
        arguments.put("id", OwnerID);

        return arguments;
    }

    private Map<String, String> cancel_pay_param(int tranactionID) {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("action_type", "cancel_pay");
        arguments.put("transaction_id", String.valueOf(tranactionID));

        return arguments;
    }

    private Map<String, String> supply_param(String country, String city, String adress, String zipcoode, String reciver) {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("action_type", "supply");
        arguments.put("name", reciver);
        arguments.put("address", adress);
        arguments.put("city", city);
        arguments.put("country", country);
        arguments.put("zip", zipcoode);

        return arguments;
    }

    private Map<String, String> cancel_supply_param(int tranactionID) {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("action_type", "cancel_supply");
        arguments.put("transaction_id", String.valueOf(tranactionID));

        return arguments;
    }


    // ---------------------------------------------------------------------- api ----------------------------------------------------------------------


    public String handshake() {
        return ssend(getParameters(handshake_param()));
    }


    public String pay(String card, String date, String owner, String cvv, String OwnerID) {
        return ssend(getParameters(pay_param(card, date, owner, cvv, OwnerID)));
    }

    public String cancel_pay(int tranactionID) {
        return ssend(getParameters(cancel_pay_param(tranactionID)));
    }


    public String supply(String country, String city, String adress, String zipcoode, String reciver) {
        return ssend(getParameters(supply_param(country, city, adress, zipcoode, reciver)));
    }

    public String cancel_supply(int tranactionID) {
        return ssend(getParameters(cancel_supply_param(tranactionID)));
    }

}
