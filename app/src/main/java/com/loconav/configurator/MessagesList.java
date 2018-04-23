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
    private String simType = "Airtel";
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
        add("RV01 Portable");
        add("RV01 Normal");
        add("MT05(top10)");
        add("GT06F");
    }};

    private Map<Integer, String> ET300plusMessages;
    private Map<Integer, String> TK101BMessages;
    private Map<Integer, String> WETRACK2Messages;
    private Map<Integer, String> RV01PortableMessages;
    private Map<Integer, String> RV01NormalMessages;
    private Map<Integer, String> MT05Messages;
    private Map<Integer, String>  GT06fMessages;

    public Map<Integer, String> getGt06fMessages() {
        return GT06fMessages;
    }

    public void setGt06fMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "APN,"+apnMessages.get(simType)+"#");
        temp.put(1, "(ok)(.*)");
        temp.put(2, "SERVER,0,52.33.252.113,4567,0#");
        temp.put(3, "ok");
        temp.put(4, "TIMER,30,30#");
        temp.put(5, "OK!");
        temp.put(6, "GMT,E,0,0#");
        temp.put(7, "OK!");
        temp.put(8, "DISTANCE,0#");
        temp.put(9, "OK!");
        temp.put(10, "POWERALM,ON,0,2,1,0#");
        temp.put(11, "OK!");
        temp.put(12, "ADT,ON,20#");
        temp.put(13, "ON,20");
        this.GT06fMessages = temp;
    }



    public Map<Integer, String> getMT05Messages() {
        return MT05Messages;
    }

    public void setMT05Messages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "111111WWW:APN:"+apnMessages.get(simType) + ";");
        temp.put(1, "(.*)(IPN:52.33.252.113;COM:5678)(.*)");
        temp.put(2, "111111WWW:RPT:10;SLP:10;");
        temp.put(3, "(.*)(IPN:52.33.252.113;COM:5678)(.*)");
        this.MT05Messages = temp;
    }

    private Map<String , Map<Integer, String>> machineMessages;


    public MessagesList() {
        refreshMessagesList();
    }

    public void setTK101BMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "ip52.33.252.113port12345");
        temp.put(1, "set IP address and PORT ok.");
        temp.put(2, "apn123456 "+apnMessages.get(simType));
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
        temp.put(10, "#6666#SMSA#0#");
        temp.put(11, "SMS alarm en=0--Close OK!");
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
        temp.put(10, "POWERALM,ON,0,2,1,0#");
        temp.put(11, "OK!");
        temp.put(12, "BATALM,OFF#");
        temp.put(13, "OK!");
        temp.put(14, "MOVING,OFF#");
        temp.put(15, "(ok)(.*)");
        temp.put(16, "SPEED,OFF#");
        temp.put(17, "OK!");
        temp.put(18, "SENALM,OFF#");
        temp.put(19, "OK!");
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
        temp.put(machineList.get(3), getRV01PortableMessages());
        temp.put(machineList.get(4), getRV01NormalMessages());
        temp.put(machineList.get(5), getMT05Messages());
        temp.put(machineList.get(6), getGt06fMessages());
        this.machineMessages = temp;
    }


    public Map<String, Map<Integer, String>> getMachineMessages() {
        return machineMessages;
    }

    public void refreshMessagesList() {
        setET300plusMessages();
        setTK101BMessages();
        setWETRACK2Messages();
        setRV01PortableMessages();
        setRV01NormalMessages();
        setMT05Messages();
        setGt06fMessages();
        setMachineMessages();
    }

    public Map<Integer, String> getRV01PortableMessages() {
        return RV01PortableMessages;
    }

    public void setRV01PortableMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "#6666#ip#52.33.252.113#4567#");
        temp.put(1, "#6666#ip#52.33.252.113#4567#OK!");
        temp.put(2, "#6666#sapn#"+ apnMessages.get(getSimType()) +"#####");
        temp.put(3, "(.*)(ok)(.*)");
        temp.put(4, "#6666#SMT#10#");
        temp.put(5, "Set movement upload time,OK!");
        temp.put(6, "#6666#SST#1#");
        temp.put(7,"(.*)(ok)(.*)");
        temp.put(8, "#6666#SMSA#0#");
        this.RV01PortableMessages = temp;
    }

    public Map<Integer, String> getRV01NormalMessages() {
        return RV01NormalMessages;
    }

    public void setRV01NormalMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "#6666#ip#52.33.252.113#4567#");
        temp.put(1, "#6666#ip#52.33.252.113#4567#OK!");
        temp.put(2, "#6666#sapn#"+ apnMessages.get(simType) +"#####");
        temp.put(3, "(.*)(ok)(.*)");
        temp.put(4, "#6666#SMT#10#");
        temp.put(5, "Set movement upload time,OK!");
        temp.put(6, "#6666#SST#1#");
        temp.put(7,"(.*)(ok)(.*)");
        temp.put(8, "#6666#SMSA#0#");
        this.RV01NormalMessages = temp;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }

    public String getSimType() {
        return simType;
    }

}
