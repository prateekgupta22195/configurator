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
        add("L-100");
        add("GT-05");
        add("GT06N");
        add("M2C");
        add("GT06-mini");
        add("ST903");
        add("LNWT");
        add("iTriangle");
        add("JV200");
        add("Zenda ZDVT2");
        add("Ruptela");
    }};



    private Map<Integer, String> ET300plusMessages;
    private Map<Integer, String> TK101BMessages;
    private Map<Integer, String> WETRACK2Messages;
    private Map<Integer, String> RV01PortableMessages;
    private Map<Integer, String> RV01NormalMessages;
    private Map<Integer, String> MT05Messages;
    private Map<Integer, String>  GT06fMessages;
    private Map<Integer, String>  L100Messages;
    private Map<Integer, String> GT05Messages;
    private Map<Integer, String>  GT06NMessages;
    private Map<Integer, String>  M2CMessages;
    private Map<Integer, String>  GT06_MINI_Messages;
    private Map<Integer, String> ST903Messages;
    private Map<Integer, String> LNWTMessages;
    private Map<Integer, String> iTriangleMessages;
    private Map<Integer, String> jv200Messages;
    private Map<Integer, String> zendaMessages;
    private Map<Integer, String> ruptelaMessages;


    public Map<Integer, String> getRuptelaMessages() {
        return ruptelaMessages;
    }

    public void setRuptelaMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Setconnection "+ apnMessages.get(simType) + ",,,TCP,52.38.44.16,5039");
        temp.put(1, "Set connection data ok");
        this.ruptelaMessages = temp;
    }

    public Map<Integer, String> getZendaMessages() {
        return zendaMessages;
    }

    public void setZendaMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Server,0000,0,52.38.44.16,5038,0#");
        temp.put(1, "OK");
        temp.put(2, "Apn,0000," + apnMessages.get(simType) + "#");
        temp.put(3, "OK");
        temp.put(4, "Timer,0000,5,60#");
        temp.put(5, "OK");
        this.zendaMessages = temp;
    }


    public Map<Integer, String> getJv200Messages() {
        return jv200Messages;
    }

    public void setJv200Messages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Server,666666,0,52.38.44.16,5032,0#");
        temp.put(1, "OK");
        temp.put(2, "Apn,666666," + apnMessages.get(simType) + "#");
        temp.put(3, "OK");
        temp.put(4, "Timer,666666,5,60#");
        temp.put(5, "OK");
        temp.put(6, "Sends,666666,0#");
        temp.put(7, "OK");
        this.jv200Messages = temp;
    }


    public Map<Integer, String> getST903Messages() {
        return ST903Messages;
    }

    public void setST903Messages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "7100000");
        temp.put(1, "SET OK");
        temp.put(2, "SLEEP0000 0");
        temp.put(3, "OK");
        temp.put(4, "8040000 52.33.252.113 5555");
        temp.put(5, "SET OK");
        temp.put(6, "8030000 " + apnMessages.get(simType));
        temp.put(7, "SET OK");
        temp.put(8, "8050000 60");
        temp.put(9, "SET OK");
        this.ST903Messages = temp;
    }



    public Map<Integer, String> getiTriangleMessages() {
        return iTriangleMessages;
    }

    public void setiTriangleMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "set$xxxxxxxxx@aquila123#CFG_GPRS:"+ apnMessages.get(simType) + ",,,52.38.44.16,5040*");
        temp.put(1, "Success");
        temp.put(2, "set$180721569@aquila123#CFG_TL:GPRS,10S,5M*");
        temp.put(3, "Success");
        this.iTriangleMessages = temp;
    }


    public Map<Integer, String> getLNWTMessages() {
        return LNWTMessages;
    }

    public void setLNWTMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Server#");
        temp.put(1, "SERVER,1,cc59e31b75.loconav.com,5023,0");
        temp.put(2, "Apn," + apnMessages.get(simType) + "#");
        temp.put(3, "(ok)(.*)");
        temp.put(4, "GMT,E,0,0#");
        temp.put(5, "OK!");
        temp.put(6, "Timer,5,60#");
        temp.put(7, "OK!");
        temp.put(8, "Sends,0#");
        temp.put(9, "OK!");
        temp.put(10, "Gpsdup,on#");
        temp.put(11, "OK!");
        temp.put(12, "Exbatcut,on,0,080,085,10#");
        temp.put(13, "OK!");
        this.LNWTMessages = temp;
    }

    public Map<Integer, String> getM2cMessages() {
        return M2CMessages;
    }

    public void setM2CMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "1,M2C,511.0=1,511.1=54.71.176.242,511.2=2000");
        temp.put(1, "x,M2C,511.0=1,511.1=54.71.176.242,511.2=2000");
        temp.put(2, "1,M2C, 502.1="+apnMessages.get(simType));
        temp.put(3, "x,M2C,502.1="+apnMessages.get(simType));
        temp.put(4, "1,M2C,0.0=1,0.1=120");
        temp.put(5, "x,M2C,0.0=1,0.1=120");
        temp.put(6, "1,M2C,2.0=1,2.1=30");
        temp.put(7, "x,M2C,2.0=1,2.1=30");
        this.M2CMessages = temp;
    }

    public Map<Integer, String> getGT06_MINI_Messages() {
        return GT06_MINI_Messages;
    }

    public void setGT06_MINI_Messages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "APN,"+apnMessages.get(simType)+"#");
        temp.put(1, "(ok)(.*)");
        temp.put(2, "SERVER,0,52.33.252.113,4567,0#");
        temp.put(3, "ok");
        temp.put(4, "SENDS,0#");
        temp.put(5, "ok");
        temp.put(6, "TIMER,5,60#");
        temp.put(7, "ok");
        this.GT06_MINI_Messages = temp;
    }


    public Map<Integer, String> getGT06NMessages() {
        return GT06NMessages;
    }

    public void setGT06NMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "APN,"+apnMessages.get(simType)+"#");
        temp.put(1, "(ok)(.*)");
        temp.put(2, "SERVER,0,52.33.252.113,5557,0#");
        temp.put(3, "ok");
        temp.put(4, "TIMER,5,5#");
        temp.put(5, "OK!");
        temp.put(6, "GMT,E,0,0#");
        temp.put(7, "OK!");
        temp.put(8, "POWERALM,ON,0,2,1,0#");
        temp.put(9, "OK!");
        temp.put(10, "HBT,1,1#");
        this.GT06NMessages = temp;
    }

    public Map<Integer, String> getGT05Messages() {
        return GT05Messages;
    }

    public void setGT05Messages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Begin123456");
        temp.put(1, "BEGIN,CONFIG OK");
        temp.put(2, "Apn123456 " + apnMessages.get(simType));
        temp.put(3, "APN,CONFIG OK");
        temp.put(4, "Adminip123456 52.33.252.113 5562");
        temp.put(5, "ADMINIP,CONFIG OK");
        this.GT05Messages = temp;
    }


    public Map<Integer, String> getGt06fMessages() {
        return GT06fMessages;
    }

    public void setGt06fMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "APN,"+apnMessages.get(simType)+"#");
        temp.put(1, "(ok)(.*)");
        temp.put(2, "SERVER,0,52.33.252.113,5557,0#");
        temp.put(3, "ok");
        temp.put(4, "TIMER,5,5#");
        temp.put(5, "OK!");
        temp.put(6, "GMT,E,0,0#");
        temp.put(7, "OK!");
        temp.put(8, "POWERALM,ON,0,2,1,0#");
        temp.put(9, "OK!");
        temp.put(10, "ADT,ON,10#");
        temp.put(11, "ON,10");
        temp.put(12, "HBT,1,1#");
        temp.put(13, "(ok)(.*)");
        this.GT06fMessages = temp;
    }

    public Map<Integer, String> getL100Messages() {
        return L100Messages;
    }

    public void setL100Messages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "#SERVERCHANGE::52.33.252.113::5559;<6906>");
        temp.put(1, "SERVER SOCKET UPDATED");
        temp.put(2, "WEBSTART010S<6906>");
        temp.put(3, "TRACKING SET TO 10S.");
        temp.put(4, "SLEEPOFF<6906>");
        temp.put(5, "SLEEP MODE DISABLED");
        temp.put(6, "SDBT<6906>");
        temp.put(7, "DBT DISABLED");
        this.L100Messages = temp;
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
        temp.put(0, "ip52.38.44.16port5034");
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
        temp.put(0, "#6666#ip#52.38.44.16#5032#");
        temp.put(1, "#6666#ip#52.38.44.16#5032#OK!");
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
        temp.put(1, "(ok)(.*)");
        temp.put(2, "SERVER,0,52.38.44.16,5032,0#");
        temp.put(3, "OK");
        temp.put(4, "TIMER,5,5#");
        temp.put(5, "OK!");
        temp.put(6, "GMT,E,0,0#");
        temp.put(7, "OK!");
        temp.put(8, "POWERALM,ON,0,2,1,0#");
        temp.put(9, "OK!");
        temp.put(10, "BATALM,OFF#");
        temp.put(11, "OK!");
        temp.put(12, "MOVING,OFF#");
        temp.put(13, "(ok)(.*)");
        temp.put(14, "SPEED,OFF#");
        temp.put(15, "OK!");
        temp.put(16, "SENALM,OFF#");
        temp.put(17, "(OK!)|(Vibration alarm is off, the device disarms!)");
        temp.put(18, "exbatcut,on,0,080,085,10#");
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
        temp.put(machineList.get(7), getL100Messages());
        temp.put(machineList.get(8), getGT05Messages());
        temp.put(machineList.get(9), getGT06NMessages());
        temp.put(machineList.get(10), getM2cMessages());
        temp.put(machineList.get(11), getGT06_MINI_Messages());
        temp.put(machineList.get(12), getST903Messages());
        temp.put(machineList.get(13), getLNWTMessages());
        temp.put(machineList.get(14), getiTriangleMessages());
        temp.put(machineList.get(15), getJv200Messages());
        temp.put(machineList.get(16), getZendaMessages());
        temp.put(machineList.get(17), getRuptelaMessages());
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
        setL100Messages();
        setGT05Messages();
        setGT06NMessages();
        setM2CMessages();
        setGT06_MINI_Messages();
        setST903Messages();
        setLNWTMessages();
        setiTriangleMessages();
        setJv200Messages();
        setZendaMessages();
        setRuptelaMessages();
        setMachineMessages();
    }

    public Map<Integer, String> getRV01PortableMessages() {
        return RV01PortableMessages;
    }

    public void setRV01PortableMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "#6666#ip#52.38.44.16#5032#");
        temp.put(1, "#6666#ip#52.38.44.16#5032#OK!");
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
        temp.put(0, "#6666#ip#52.38.44.16#5032#");
        temp.put(1, "#6666#ip#52.38.44.16#5032#OK!");
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
