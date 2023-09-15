import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Main {

    public static void main(String[] args) {

      String meetings ="Mon 01:00-23:00\n" +
              "Tue 01:00-23:00\n" +
              "Wed 01:00-23:00\n" +
              "Thu 01:00-23:00\n" +
              "Fri 01:00-23:00\n" +
              "Sat 01:00-23:00\n" +
              "Sun 01:00-21:00";
        System.out.println(getLongestSleepPeriod(meetings));



    }

    private static int getLongestSleepPeriod(String meetings) {
        Meeting firstMeeting=new Meeting("Mon 00:00-00:00");
        Meeting lastMeeting=new Meeting("Sun 23:59-23:59");
        Meeting previousMeeting,currentMeeting=firstMeeting;
        List<Integer>sleep=new ArrayList<>();

        List<Meeting> meetingsList= Arrays.stream(meetings.split("\n")).map(Meeting::new).toList();

        Comparator<Meeting>sort=Comparator.comparingInt(Meeting::getDay)
                        .thenComparing(Meeting::getStartMeeting);
        meetingsList=meetingsList.stream().sorted(sort).collect(Collectors.toList());
        meetingsList.add(lastMeeting);
        meetingsList.forEach(System.out::println);
        for (Meeting meeting : meetingsList) {
            previousMeeting= currentMeeting;
            currentMeeting = meeting;
            sleep.add(sleepBetweenMeetings(previousMeeting, currentMeeting));
        }
        return sleep.stream().max(Integer::compare).get();
    }

    public static int sleepBetweenMeetings(Meeting meetingA,Meeting meetingB){
        int dayDifference;
        int sleepAfterA;
        int sleepBeforeB;
        LocalTime startOfDay=LocalTime.parse("00:00");
        //local time no maneja 24:00, sumar un minuto despues de calcular
        LocalTime endOfDay=LocalTime.parse("23:59");
        if(meetingB.getDay()>meetingA.getDay()) {
            dayDifference = ((meetingB.getDay() - meetingA.getDay()) - 1) * 60 * 24; //7200
            if(meetingA.endMeeting.equals(startOfDay))
                sleepAfterA=0;
            else
                sleepAfterA=(int)Duration.between(meetingA.endMeeting,endOfDay).toMinutes()+1;//1020
            sleepBeforeB=(int)Duration.between(startOfDay,meetingB.startMeeting).toMinutes();//480
            return dayDifference+sleepBeforeB+sleepAfterA;
        }
        if(meetingB.startMeeting.equals(endOfDay)&&meetingB.endMeeting.equals(endOfDay))
            return (int)Duration.between(meetingA.endMeeting,meetingB.startMeeting).toMinutes()+1;
        return (int)Duration.between(meetingA.endMeeting,meetingB.startMeeting).toMinutes();

    }
}