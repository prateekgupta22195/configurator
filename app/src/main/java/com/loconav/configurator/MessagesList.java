package com.loconav.configurator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.loconav.configurator.Constants.SIM_TYPE;
import static com.loconav.configurator.SmsReceiver.simType;

/**
 * Created by prateek on 10/02/18.
 */


/*
* Note: please get messageId always from 1st message
* Add new devices following below flow */

public class MessagesList {

    public List<String> simList = new ArrayList<String>(){{
        add("Airtel");
        add("Vodafone");
        add("Reliance Jio");
        add("Reliance GSM");
        add("Idea");
        add("Uninor");
        add("Aircel");
        add("BSNL");
        add("MTNL");
    }};

    private Map<String , String> apnMessages = new HashMap<String , String >() {{
        put(simList.get(0), "airtelgprs.com");
        put(simList.get(1), "www");
        put(simList.get(2), "jionet");
        put(simList.get(3), "rcomnet");
        put(simList.get(4), "internet");
        put(simList.get(5), "uninor");
        put(simList.get(6), "aircelgprs.po");
        put(simList.get(7), "bsnlnet");
        put(simList.get(8), "mtnl.net");
    }};

    public List<String> machineList = new ArrayList<String >() {{
        add("ET300+");
        add("TK101B");
        add("WETRACK2");
    }};


    private Map<Integer, String> ET300plusMessages;
    private Map<Integer, String> TK101BMessages;
    private Map<Integer, String> WETRACK2Messages;
    private Map<String , Map<Integer, String>> machineMessages;


    public MessagesList() {
        refreshMessagesList();
    }



    public void setTK101BMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "ip52.33.252.113port12345");
        temp.put(1, "set IP address and PORT ok.");
        temp.put(2, "apn123456"+apnMessages.get(simType));
        temp.put(3, "Set APN ok");
        temp.put(4, "at30sum0");
        temp.put(5, "Auto track set ok.");
        this.TK101BMessages = temp;
    }

    public Map<Integer, String> getTK101BMessages() {
        return TK101BMessages;
    }

    public void setET300plusMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "#6666#ip#52.33.252.113#4567#");
        temp.put(1, "#6666#ip#52.33.252.113#4567#OK!");
        temp.put(2, "#6666#sapn#"+ apnMessages.get(simType) +"#####");
        temp.put(3, "set APN =1 ok!");
        temp.put(4, "#6666#sleep#0#");
        temp.put(5, "#6666#sleep#0#--Close Sleep mode OK!");
        temp.put(6, "#6666#SMT#30#");
        temp.put(7, "Set movement upload time,OK!");
        temp.put(8, "#6666#SST#1#");
        temp.put(9,"#6666#SST#1# OK!");
        this.ET300plusMessages = temp;
    }

    public Map<Integer, String> getET300plusMessages() {
        return ET300plusMessages;
    }

    public void setWETRACK2Messages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "APN,"+apnMessages.get(simType)+"#");
        temp.put(1, "OK! for the newly-set APN to take effect, the device will reboot after 10s.");
        temp.put(2, "SERVER,0,52.33.252.113,4567,0#");
        temp.put(3, "OK");
        temp.put(4, "TIMER,30,30#");
        temp.put(5, "OK!");
        temp.put(6, "GMT,E,0,0#");
        temp.put(7, "OK!");
        temp.put(8, "DISTANCE,0#");
        temp.put(9, "OK!");
        this.WETRACK2Messages = temp;
    }

    public Map<Integer, String> getWETRACK2Messages() {
        return WETRACK2Messages;
    }

    public void setMachineMessages() {
        Map<String , Map<Integer, String>> temp = new HashMap<>();
        temp.put(machineList.get(0), getET300plusMessages());
        temp.put(machineList.get(1), getTK101BMessages());
        temp.put(machineList.get(2), getWETRACK2Messages());
        this.machineMessages = temp;
    }


    public Map<String, Map<Integer, String>> getMachineMessages() {
        return machineMessages;
    }

    public void refreshMessagesList() {
        setET300plusMessages();
        setTK101BMessages();
        setWETRACK2Messages();
        setMachineMessages();
    }



//    public static List<String> simList = new ArrayList<>();
//    static {
//        simList.add("Airtel");
//        simList.add("Vodafone");
//        simList.add("Reliance Jio");
//        simList.add("Reliance GSM");
//        simList.add("Idea");
//        simList.add("Uninor");
//        simList.add("Aircel");
//        simList.add("BSNL");
//        simList.add("MTNL");
//    }
//
//    public static Map<String , String> apnMessages = new HashMap<>();
//    static {
//        apnMessages.put(simList.get(0), "airtelgprs.com");
//        apnMessages.put(simList.get(1), "www");
//        apnMessages.put(simList.get(2), "jionet");
//        apnMessages.put(simList.get(3), "rcomnet");
//        apnMessages.put(simList.get(4), "internet");
//        apnMessages.put(simList.get(5), "uninor");
//        apnMessages.put(simList.get(6), "aircelgprs.po");
//        apnMessages.put(simList.get(7), "bsnlnet");
//        apnMessages.put(simList.get(8), "mtnl.net");
//    }
//
//    public static Map<Integer, String> ET300plusMessages = ET300Call();
//    private static Map<Integer, String> ET300Call()  {
//        Map<Integer, String > temp = new HashMap<>();
//        temp.put(0, "#6666#ip#52.33.252.113#4567#");
//        temp.put(1, "#6666#ip#52.33.252.113#4567#OK!");
//        temp.put(2, "#6666#sapn#"+ apnMessages.get(simType) +"#####");
//        temp.put(3, "set APN =1 ok!");
//        temp.put(4, "#6666#sleep#0#");
//        temp.put(5, "#6666#sleep#0#--Close Sleep mode OK!");
//        temp.put(6, "#6666#SMT#30#");
//        temp.put(7, "Set movement upload time,OK!");
//        temp.put(8, "#6666#SST#1#");
//        temp.put(9,"#6666#SST#1# OK!");
//        return temp;
//    }
//
//
//    public static Map<Integer, String> TK101BMessages = new HashMap<>();
//    static  {
//        TK101BMessages.put(0, "ip52.33.252.113port12345");
//        TK101BMessages.put(1, "set IP address and PORT ok.");
//        TK101BMessages.put(2, "apn123456"+apnMessages.get(simType));
//        TK101BMessages.put(3, "Set APN ok");
//        TK101BMessages.put(4, "at30sum0");
//        TK101BMessages.put(5, "Auto track set ok.");
//    }
//
//
//    public static Map<Integer, String> WETRACK2Messages = new HashMap<>();
//    static  {
//        WETRACK2Messages.put(0, "APN,"+apnMessages.get(simType)+"#");
//        WETRACK2Messages.put(1, "OK! for the newly-set APN to take effect, the device will reboot after 10s.");
//        WETRACK2Messages.put(2, "SERVER,0,52.33.252.113,4567,0#");
//        WETRACK2Messages.put(3, "OK");
//        WETRACK2Messages.put(4, "TIMER,30,30#");
//        WETRACK2Messages.put(5, "OK!");
//        WETRACK2Messages.put(6, "GMT,E,0,0#");
//        WETRACK2Messages.put(7, "OK!");
//        WETRACK2Messages.put(8, "DISTANCE,0#");
//        WETRACK2Messages.put(9, "OK!");
//    }
//
////    public static Map<Integer, String> OBDMessages = new HashMap<>();
////    static  {
////        OBDMessages.put(0, "(Set,888888,server,52.33.252.113,54321)");
////        OBDMessages.put(1, "(IP:52.33.252.113,Port:54321)");
////        OBDMessages.put(2, "(Set,888888,apn,"+ apnMessages.get(simType) +")");
////        OBDMessages.put(3, "(APN:airtelgprs.com)");
////        OBDMessages.put(4, "(Find,888888,car)");
////        OBDMessages.put(5, "OBDII tracker:192030981185 State:5satellite positioning,Device connect\n" +
////                "    http://maps.google.com/?q=28.500173N,77.079455E\n" +
////                "    ed to the server,CSQ:14,ACC off,did not read OBD,Ver:MK6000_20171117R");
////    }
//
//    public static List<String> machineList = new ArrayList<>();
//    static {
//        machineList.add("ET300+");
//        machineList.add("TK101B");
//        machineList.add("WETRACK2");
////        machineList.add("OBD");
//    }
//
//    public static Map<String , Map<Integer, String>> machineMessages = makeMachineMessages();
//    public static Map<String , Map<Integer, String>> makeMachineMessages() {
//        Map<String , Map<Integer, String>> temp = new HashMap<>();
//        temp.put(machineList.get(0), ET300plusMessages);
//        temp.put(machineList.get(1), TK101BMessages);
//        temp.put(machineList.get(2), WETRACK2Messages);
//        return temp;
////        machineMessages.put(machineList.get(3), OBDMessages);
//    }




}
