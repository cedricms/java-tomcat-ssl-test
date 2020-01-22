package com.mariasube.java.tomcat.ssl.test;

import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

public class HttpsClientApp {

    private static Logger logger = Logger.getLogger(HttpsClientApp.class);

    public static void main(String[] args) {
        new HttpsClientApp().testIt();
    }

    public static final String HTTPS_URL = "https://www.google.com/";

    private void testIt(){
        try {
            URL url = new URL(HTTPS_URL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            printHttpsCertificates(connection);
            printURLContent(connection);
        } catch (MalformedURLException mfurle) {
            logger.error(mfurle);
        } catch (IOException ioe) {
            logger.error(ioe);
        }
    }

    private void printHttpsCertificates(HttpsURLConnection connection){
        if(connection!=null){
            try {
                logger.info("Response Code : " + connection.getResponseCode());
                logger.info("Cipher Suite : " + connection.getCipherSuite());
                logger.info("\n");

                Certificate[] certificates = connection.getServerCertificates();
                for(Certificate certificate : certificates){
                    logger.info("Cert Type : " + certificate.getType());
                    logger.info("Cert Hash Code : " + certificate.hashCode());
                    logger.info("Cert Public Key Algorithm : " + certificate.getPublicKey().getAlgorithm());
                    logger.info("Cert Public Key Format : " + certificate.getPublicKey().getFormat());
                    logger.info("\n");
                }
            } catch (SSLPeerUnverifiedException sslpue) {
                logger.error(sslpue);
            } catch (IOException ioe){
                logger.error(ioe);
            }
        }
    }

    private void printURLContent(HttpsURLConnection connection){
        if(connection!=null){
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                logger.info("****** Content of the URL ********");

                String input;

                while ((input = br.readLine()) != null){
                    logger.info(input);
                }
            } catch (IOException ioe) {
                logger.error(ioe);
            }
        }
    }
}
