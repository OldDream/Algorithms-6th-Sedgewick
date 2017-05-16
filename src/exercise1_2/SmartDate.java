package exercise1_2;

public class SmartDate {
    private int day, month, year;
    
    public SmartDate(int month, int day, int year) throws Exception {
        this.month = month;
        this.year = year;
        this.day = day;
        if (month > 12 || year < 0 || day > 31 || month <= 0 || day <= 0) {
            throw new Exception("Wrong Date.");
        }
    }
    
    public int day() {
        return day;
    }
    
    public int month() {
        return month;
    }
    
    public int year() {
        return year;
    }
    
    public String toString() {
        return "year:" + year + " month:" + month + " day:" + day;
    }
    
    public static void main(String[] args) throws Exception {
        SmartDate a = new SmartDate(12, 5, 1993);
    }
}
