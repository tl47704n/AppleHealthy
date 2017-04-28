package com.example.pc.applehealthy;

/**
 * Created by pc on 2017/4/24.
 */

public class Medicines {

    private String medicineName;
    private String alarm1;
    private String alarm2;
    private String alarm3;
    private long alarm1_mili;
    private long alarm2_mili;
    private long alarm3_mili;
    private String medicineID;
    public Medicines (){}

    public Medicines (String medicineName, String alarm1,String alarm2, String alarm3, String medicineID,long alarm1_mili,long alarm2_mili, long alarm3_mili){
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.alarm1 = alarm1;
        this.alarm2 = alarm2;
        this.alarm3= alarm3;
        this.alarm1_mili = alarm1_mili;
        this.alarm2_mili = alarm2_mili;
        this.alarm3_mili = alarm3_mili;
    }
    public long getAlarm1_mili(){return  alarm1_mili;}
    public long getAlarm2_mili(){return  alarm2_mili;}
    public long getAlarm3_mili(){return  alarm3_mili;}

    public void setAlarm1_mili(long alarm1_mili){this.alarm1_mili = alarm1_mili;}
    public void setAlarm2_mili(long alarm2_mili){this.alarm2_mili = alarm2_mili;}
    public void setAlarm3_mili(long alarm3_mili){this.alarm3_mili = alarm3_mili;}

    public String getMedicineName(){
        return medicineName;
    }

    public void setName(String medicineName){
        this.medicineName = medicineName;
    }


    public String getAlarm1(){return alarm1;}
    public void setAlarm1(String alarm1){this.alarm1 = alarm1;}

    public String getAlarm2(){return alarm2;}
    public void setAlarm2(String alarm2){this.alarm2 = alarm2;}

    public String getAlarm3(){return alarm3;}
    public void setAlarm3(String alarm3){this.alarm3 = alarm3;}



    public String getMedicineID() {
        return medicineID;
    }
    public void setMedicineID(String medicineID){this.medicineID = medicineID;}
}
