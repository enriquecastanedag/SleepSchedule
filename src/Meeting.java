import java.time.LocalTime;

public class Meeting {
    int day;
    LocalTime startMeeting;
    LocalTime endMeeting;

    public Meeting() {
    }

    public Meeting(String meeting) {
        String day = meeting.substring(0, 3);
        String start = meeting.substring(4, 9);
        String end = meeting.substring(10, 15);
        setDay(day);
        setStartMeeting(start);
        setEndMeeting(end);
    }

    public int getDay() {
        return day;
    }

    public void setDay(String day) {
        switch (day) {
            case "Mon":
                this.day = 1;
                break;
            case "Tue":
                this.day = 2;
                break;
            case "Wed":
                this.day = 3;
                break;
            case "Thu":
                this.day = 4;
                break;
            case "Fri":
                this.day = 5;
                break;
            case "Sat":
                this.day = 6;
                break;
            case "Sun":
                this.day = 7;
                break;

        }
    }

    public void setStartMeeting(String startMeeting) {
        this.startMeeting = LocalTime.parse(startMeeting);
    }

    public void setEndMeeting(String endMeeting) {
        if("24:00".equals(endMeeting))
            endMeeting="00:00";
        this.endMeeting = LocalTime.parse(endMeeting);
    }

    public void setDay(int day) {
        this.day = day;
    }

    public LocalTime getStartMeeting() {
        return startMeeting;
    }

    public void setStartMeeting(LocalTime startMeeting) {
        this.startMeeting = startMeeting;
    }

    public LocalTime getEndMeeting() {
        return endMeeting;
    }

    public void setEndMeeting(LocalTime endMeeting) {
        this.endMeeting = endMeeting;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "day=" + day +
                ", startMeeting=" + startMeeting +
                ", endMeeting=" + endMeeting +
                '}';
    }
}
