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
    private String password = "6666";
    private String ip = "52.38.44.16";

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
        add("BTTECHLABS");
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
        put(simList.get(9), "BTTECHLABS");
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
        add("Time Watch");
        add("GT02 Password");
        add("WeTrack Password");
        add("WeTrack Bike");
        add("GT02");
        add("G200 Portable");
        add("LT05");
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
    private Map<Integer, String> timeWatchMessages;
    private Map<Integer, String> GT02PasswordMessages;
    private Map<Integer, String> WeTrackPasswordMessages;
    private Map<Integer, String> WeTrackBikeMessages;
    private Map<Integer, String> GT02Messages;
    private Map<Integer, String> G200PortableMessages;
    private Map<Integer, String> LT05Messages;





    public Map<Integer, String> getGT02Messages() {
        return GT02Messages;
    }

    public void setGT02Messages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Server,666666,0,52.38.44.16,5042,0#");
        temp.put(1, "(ok)(.*)");
        temp.put(2, "Apn,666666," + apnMessages.get(simType) + "#");
        temp.put(3, "(ok)(.*)");
        temp.put(4, "Timer,666666,5,60#");
        temp.put(5, "(ok)(.*)");
        temp.put(6, "Sends,666666,0#");
        temp.put(7, "(ok)(.*)");
        this.GT02Messages = temp;

    }


    public Map<Integer, String> getG200PortableMessages() {
        return G200PortableMessages;
    }

    public void setG200PortableMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Apn123456 " + apnMessages.get(simType) + "#");
        temp.put(1, "APN,CONFIG OK");
        temp.put(2, "Adminip123456 52.38.44.16 5045");
        temp.put(3, "ADMINIP,CONFIG OK");
        temp.put(4, "itv123456 300");
        this.G200PortableMessages = temp;
    }


    public Map<Integer, String> getWeTrackBikeMessages() {
        return WeTrackBikeMessages;
    }

    public void setWeTrackBikeMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Server#");
        temp.put(1, "SERVER,1,cc59e31b75.loconav.com,5023,0");
        temp.put(2, "Apn," + apnMessages.get(simType) + "#");
        temp.put(3, "(ok)(.*)");
        temp.put(4, "GMT,E,0,0#");
        temp.put(5, "(ok)(.*)");
        temp.put(6, "Timer,5,600#");
        temp.put(7, "(ok)(.*)");
        temp.put(8, "Sends,0#");
        temp.put(9, "(ok)(.*)");
        temp.put(10, "Gpsdup,on#");
        temp.put(11, "(ok)(.*)");
        temp.put(12, "Exbatcut,on,0,120,125,10#");
        temp.put(13, "(ok)(.*)");
        this.WeTrackBikeMessages = temp;
    }


    public Map<Integer, String> getWeTrackPasswordMessages() {
        return WeTrackPasswordMessages;
    }

    public void setWeTrackPasswordMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Server," + password + ",0,52.38.44.16,5032,0#");
        temp.put(1, "(ok)(.*)");
        temp.put(2, "Apn," + password + "," + apnMessages.get(simType) + "#");
        temp.put(3, "(ok)(.*)");
        temp.put(4, "GMT," + password + ",E,0,0#");
        temp.put(5, "(ok)(.*)");
        temp.put(6, "Timer," + password + ",5,60#");
        temp.put(7, "(ok)(.*)");
        temp.put(8, "Sends," + password + ",0#");
        temp.put(9, "(ok)(.*)");
        temp.put(10, "Gpsdup," + password + ",on#");
        temp.put(11, "(ok)(.*)");
        temp.put(12, "Exbatcut,"+ password + ",on,0,080,085,10#");
        this.WeTrackPasswordMessages = temp;
    }

    public Map<Integer, String> getGT02PasswordMessages() {
        return GT02PasswordMessages;
    }

    public void setGT02PasswordMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Server," + password + ",0,52.38.44.16,5042,0#");
        temp.put(1, "(ok)(.*)");
        temp.put(2, "Apn," + password + "," + apnMessages.get(simType) + "#");
        temp.put(3, "(ok)(.*)");
        temp.put(4, "Timer," + password + ",5,60#");
        temp.put(5, "(ok)(.*)");
        temp.put(6, "Sends," + password + ",0#");
        temp.put(7, "(ok)(.*)");
        this.GT02PasswordMessages = temp;
    }


    public Map<Integer, String> getTimeWatchMessages() {
        return timeWatchMessages;
    }

    public void setTimeWatchMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "Begin123456");
        temp.put(1, "ok!");
        temp.put(2, String.format("Adminip123456 %s 5037", ip));
        temp.put(3, "ok!");
        temp.put(4, "Apn123456 "+ apnMessages.get(simType));
        temp.put(5, "APN ok");
        this.timeWatchMessages = temp;
    }


    public Map<Integer, String> getRuptelaMessages() {
        return ruptelaMessages;
    }

    public void setRuptelaMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, " Setconnection "+ apnMessages.get(simType) + String.format(",,,TCP,%s,5039", ip));
        temp.put(1, "Set connection data ok");
        this.ruptelaMessages = temp;
    }

    public Map<Integer, String> getZendaMessages() {
        return zendaMessages;
    }

    public void setZendaMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, String.format("Server,0000,0,%s,5038,0#", ip));
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
        temp.put(0, String.format("Server,666666,0,%s,5050,0#", ip));
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
        temp.put(4, String.format("8040000 %s 5045", ip));
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
        temp.put(0, "set$xxxxxxxxx@aquila123#CFG_GPRS:"+ apnMessages.get(simType) + String.format(",,,%s,5040*", ip));
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
        temp.put(1, String.format("SERVER,1,%s,5032,0", ip));
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
        temp.put(0, String.format("1,M2C,511.0=1,511.1=%s,511.2=5048",ip));
        temp.put(1, String.format("x,M2C,511.0=1,511.1=%s,511.2=5048",ip));
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
        temp.put(2, String.format("SERVER,0,%s,5032,0#", ip));
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
        temp.put(2, String.format("SERVER,0,%s,5032,0#", ip));
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
        temp.put(4, String.format("Adminip123456 %s 5045", ip));
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
        temp.put(2, String.format("SERVER,0,%s,5032,0#",ip));
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
        temp.put(0, String.format("#SERVERCHANGE::%s::5036;<6906>", ip));
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
        temp.put(1, String.format("(.*)(IPN:%s;COM:5047)(.*)", ip));
        temp.put(2, "111111WWW:RPT:10;SLP:10;");
        temp.put(3, String.format("(.*)(IPN:%s;COM:5047)(.*)", ip));
        this.MT05Messages = temp;
    }

    private Map<String , Map<Integer, String>> machineMessages;


    public MessagesList() {
        refreshMessagesList();
    }

    public void setTK101BMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, String.format("ip%sport5034", ip));
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
        temp.put(0, String.format("#6666#ip#%s#5051#", ip));
        temp.put(1, String.format("#6666#ip#%s#5051#OK!", ip));
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
        temp.put(0, "APN,"+ apnMessages.get(simType)+"#");
        temp.put(1, "(ok)(.*)");
        temp.put(2, String.format("SERVER,0,%s,5032,0#", ip));
        temp.put(3, "OK");
        temp.put(4, "TIMER,5,60#");
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
        temp.put(machineList.get(18), getTimeWatchMessages());
        temp.put(machineList.get(19), getGT02PasswordMessages());
        temp.put(machineList.get(20), getWeTrackPasswordMessages());
        temp.put(machineList.get(21), getWeTrackBikeMessages());
        temp.put(machineList.get(22), getGT02Messages());
        temp.put(machineList.get(23), getG200PortableMessages());
        temp.put(machineList.get(24), getLT05Messages());
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
        setTimeWatchMessages();
        setGT02PasswordMessages();
        setWeTrackPasswordMessages();
        setWeTrackBikeMessages();
        setGT02Messages();
        setG200PortableMessages();
        setLT05Messages();
        setMachineMessages();
    }

    public Map<Integer, String> getRV01PortableMessages() {
        return RV01PortableMessages;
    }

    public void setRV01PortableMessages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, String.format("#6666#ip#%s#5051#", ip));
        temp.put(1, String.format("#6666#ip#%s#5051#OK!", ip));
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
        temp.put(0, String.format("#6666#ip#%s#5051#", ip));
        temp.put(1, String.format("#6666#ip#%s#5051#OK!", ip));
        temp.put(2, "#6666#sapn#"+ apnMessages.get(simType) +"#####");
        temp.put(3, "(.*)(ok)(.*)");
        temp.put(4, "#6666#SMT#10#");
        temp.put(5, "Set movement upload time,OK!");
        temp.put(6, "#6666#SST#1#");
        temp.put(7,"(.*)(ok)(.*)");
        temp.put(8, "#6666#SMSA#0#");
        this.RV01NormalMessages = temp;
    }

    public Map<Integer, String> getLT05Messages() {
        return RV01NormalMessages;
    }

    public void setLT05Messages() {
        Map<Integer, String > temp = new HashMap<>();
        temp.put(0, "*88*4cc59e31b75.loconav.com*5032*1#");
        temp.put(1, "Set OK:*88*4cc59e31b75.loconav.com*5032*1#");
        temp.put(2, "APN,"+ apnMessages.get(simType) +"#");
        temp.put(3, "APN,"+ apnMessages.get(simType) +"#");
        this.LT05Messages = temp;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }

    public String getSimType() {
        return simType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
