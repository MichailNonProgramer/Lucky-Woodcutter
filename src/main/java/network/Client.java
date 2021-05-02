package network;

import creatures.persons.player.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Random;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        System.setProperty(Common.RMI_HOSTNAME, Common.localhost);
        // URL удаленного объекта
        String objectName = Common.SERVICE_PATH;
//        Messenger messenger = (Messenger) Naming.lookup(objectName);
//        Callback callback = new Callback();
//        Player user = new Player(new Random().nextInt(), new Random().nextInt());
//        messenger.setCallback(callback, user);
//        messenger.sendMessage("Hello", user);
    }
}
