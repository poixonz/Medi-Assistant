package anik.rk.mediAssistant;

public class upcoming_list_items {
    private String medicineName , medicineType , mediHave , daysToCom , takeTime ;

    public upcoming_list_items(String medicineName, String medicineType, String mediHave, String daysToCom, String takeTime) {
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.mediHave = mediHave;
        this.daysToCom = daysToCom;
        this.takeTime = takeTime;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public String getMediHave() {
        return mediHave;
    }

    public String getDaysToCom() {
        return daysToCom;
    }

    public String getTakeTime() {
        return takeTime;
    }
}
