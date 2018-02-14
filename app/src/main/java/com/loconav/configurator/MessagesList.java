package com.loconav.configurator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prateek on 10/02/18.
 */


/*
* Note: please get messageId always from 1st message
* Add new devices following below flow */

public class MessagesList {


    public static final Map<Integer, String> ET300plusMessages = new HashMap<>();
    static  {
        ET300plusMessages.put(0, "#6666#ip#52.33.252.113#4567#");
        ET300plusMessages.put(1, "#6666#ip#52.33.252.113#4567#OK!");
        ET300plusMessages.put(2, "#6666#sapn#isafe#####");
        ET300plusMessages.put(3, "set APN =1 ok!");
        ET300plusMessages.put(4, "#6666#sleep#0#");
        ET300plusMessages.put(5, "#6666#sleep#0#--Close Sleep mode OK!");
        ET300plusMessages.put(6, "#6666#SMT#30#");
        ET300plusMessages.put(7, "Set movement upload time,OK!");
        ET300plusMessages.put(8, "#6666#SST#1#");
        ET300plusMessages.put(9,"#6666#SST#1# OK!");
    }


    public static final Map<Integer, String> TK101BMessages = new HashMap<>();
    static  {
        TK101BMessages.put(0, "ip 52.33.252.113port12345");
        TK101BMessages.put(1, "set IP address and PORT ok.");
        TK101BMessages.put(2, "apn123456 airtelgprs.com");
        TK101BMessages.put(3, "Set APN ok");
        TK101BMessages.put(4, "at30sum0");
        TK101BMessages.put(5, "Auto track set ok.");
    }


    public static final Map<Integer, String> WETRACK2Messages = new HashMap<>();

    static  {
        WETRACK2Messages.put(0, "APN,isafe#");
        WETRACK2Messages.put(1, "OK! for the newly-set APN to take effect, the device will reboot after 10s.");
        WETRACK2Messages.put(2, "SERVER,0,52.33.252.113,4567,0#");
        WETRACK2Messages.put(3, "OK");
        WETRACK2Messages.put(4, "TIMER,30,30#");
        WETRACK2Messages.put(5, "OK!");
        WETRACK2Messages.put(6, "GMT,E,0,0#");
        WETRACK2Messages.put(7, "OK!");
        WETRACK2Messages.put(8, "DISTANCE,0#");
        WETRACK2Messages.put(9, "OK!");
    }

    public static final Map<Integer, String> OBDMessages = new HashMap<>();
    static  {
        OBDMessages.put(0, "(Set,888888,server,52.33.252.113,54321)");
        OBDMessages.put(1, "(IP:52.33.252.113,Port:54321)");
        OBDMessages.put(2, "(Set,888888,apn,airtelgprs.com)");
        OBDMessages.put(3, "(APN:airtelgprs.com)");
        OBDMessages.put(4, "(Find,888888,car)");
        OBDMessages.put(5, "OBDII tracker:192030981185 State:5satellite positioning,Device connect\n" +
                "    http://maps.google.com/?q=28.500173N,77.079455E\n" +
                "    ed to the server,CSQ:14,ACC off,did not read OBD,Ver:MK6000_20171117R");
    }

    public static List<String> machineList = new ArrayList<>();
    static {
        machineList.add("ET300+");
        machineList.add("TK101B");
        machineList.add("WETRACK2");
        machineList.add("OBD");
    }

    public static Map<String , Map<Integer, String>> machineMessages = new HashMap<>();
    static {
        machineMessages.put(machineList.get(0), ET300plusMessages);
        machineMessages.put(machineList.get(1), TK101BMessages);
        machineMessages.put(machineList.get(2), WETRACK2Messages);
        machineMessages.put(machineList.get(3), OBDMessages);
    }
}
