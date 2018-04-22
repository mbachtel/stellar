package bootstrap;

import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Network;
import org.stellar.sdk.Server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Bootstrapper {
    private static String issuingAccountID;
    private static String issuingAccountSecretSeed;

    private static String distributingAccountID;
    private static String distributingAccountSecretSeed;

    private static final String STELLAR_API_URL = "https://horizon-testnet.stellar.org";

    public static void main(String[] args) {
        //Issuing Account key setup
        KeyPair issuingPair = KeyPair.random();
        issuingAccountID = issuingPair.getAccountId();
        issuingAccountSecretSeed = new String(issuingPair.getSecretSeed());

        System.out.println("Issuing Account ID is: " + issuingAccountID);
        System.out.println("Issuing Account Secret Seed is: " + issuingAccountSecretSeed);

        //Distribution Account key setup
        KeyPair distributingPair = KeyPair.random();
        distributingAccountSecretSeed = new String(distributingPair.getSecretSeed());
        distributingAccountID = distributingPair.getAccountId();

        System.out.println("Distribution Account ID is: " + distributingAccountID);
        System.out.println("Distribution Account Secret Seed is: " + distributingAccountSecretSeed);

        //Create the accounts
        System.out.println(createAccountInTest(issuingAccountID));
        System.out.println(createAccountInTest(distributingAccountID));

        //Trust the Issuer
        Network.useTestNetwork();
        Server server = new Server(STELLAR_API_URL);

            



    }

    public static String createAccountInTest(String accountID) {
        String friendbotUrl = String.format(STELLAR_API_URL + "/?addr=%s", accountID);
        InputStream response = null;
        try {
            response = new URL(friendbotUrl).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = new Scanner(response, "UTF-8").useDelimiter("\\A").next();
        return ("SUCCESS! You have a new account :)\n" + body);
    }

    public static void changeTrust(){


    }


}
