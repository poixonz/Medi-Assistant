package anik.rk.mediAssistant;

public class ScheduleItems_MAIN {

    private String medicineName , medicineType , beforeOrAfter , timeToTake , amOrPm , mediHave ;
    private String startDate , endDate , totalMedicine , taken , daysToCom , minbeforeAfter ;

    public ScheduleItems_MAIN(String medicineName,
                              String medicineType,
                              String mediHave,
                              String minbeforeAfter,
                              String beforeOrAfter,
                              String timeToTake,
                              String amOrPm,
                              String startDate,
                              String endDate,
                              String totalMedicine,
                              String taken,
                              String daysToCom) {

        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.minbeforeAfter = minbeforeAfter ;
        this.beforeOrAfter = beforeOrAfter;
        this.timeToTake = timeToTake;
        this.amOrPm = amOrPm;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalMedicine = totalMedicine;
        this.taken = taken;
        this.daysToCom = daysToCom;
        this.mediHave = mediHave ;
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

    public String getBeforeOrAfter() {
        return beforeOrAfter;
    }

    public String getTimeToTake() {
        return timeToTake;
    }

    public String getAmOrPm() {
        return amOrPm;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTotalMedicine() {
        return totalMedicine;
    }

    public String getTaken() {
        return taken;
    }

    public String getDaysToCom() {
        return daysToCom;
    }

    public String getminbeforeAfter(){return minbeforeAfter; }
}
