package agenda;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;
    
    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The durarion of the event 
     */
    private Duration myDuration;

    protected Repetition myRepetition;

    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    public void setRepetition(ChronoUnit frequency) {
        this.myRepetition = new Repetition(frequency);
    }

    public void addException(LocalDate date) {
       this.myRepetition.addException(date);
    }

    public void setTermination(LocalDate terminationInclusive) {
        if(myRepetition!=null){
            myRepetition.myTermination= new Termination(terminationInclusive);
        }
      
    }

    public void setTermination(long numberOfOccurrences) {
        if(myRepetition!=null){
            myRepetition.myTermination= new Termination(numberOfOccurrences);
        }
       
    }

    public int getNumberOfOccurrences() {
        if(myRepetition==null){
            return 1;
        }
        else {
                if (myRepetition.myTermination != null && myRepetition.myTermination.terminationDateInclusive() != null) {
                    LocalDate terminationDate = myRepetition.myTermination.terminationDateInclusive();
                    LocalDate eventDate = myStart.toLocalDate();
                    int occ = 0;

                    while (!eventDate.isAfter(terminationDate)) {
                        if (!myRepetition.getExceptions().contains(eventDate)) {
                            occ++;
                        }
                        switch (myRepetition.getFrequency()) {
                            case DAYS:
                                eventDate = eventDate.plusDays(1);
                                break;
                            case WEEKS:
                                eventDate = eventDate.plusWeeks(1);
                                break;
                            case MONTHS:
                                eventDate = eventDate.plusMonths(1);
                                break;
                            default:
                                throw new UnsupportedOperationException("Unsupported repetition frequency");
                        }
                    }
                    return occ;
                }
        }
        return 0;



    }

    public LocalDate getTerminationDate() {
        if(myRepetition!=null&&myRepetition.myTermination!=null){
            return myRepetition.myTermination.terminationDateInclusive();
        }
        else{
            throw new UnsupportedOperationException("Il y a pas de terminaison pour cet event");
        }

    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
        if (myRepetition == null) {
            return myStart.toLocalDate().equals(aDay);
        } else {
            LocalDate e = myStart.toLocalDate();
            while (!e.isAfter(aDay)) {
                if (e.equals(aDay) && (!myRepetition.getExceptions().contains(aDay) )) {
                    return true;
                }
                switch (myRepetition.getFrequency()) {
                    case DAYS:
                        e = e.plusDays(1);
                        break;
                    case WEEKS:
                        e = e.plusWeeks(1);
                        break;
                    case MONTHS:
                        e = e.plusMonths(1);
                        break;
                }
            }
            return false;  // If no match found
        }

    }
   
    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }

    @Override
    public String toString() {
        return "Event{title='%s', start=%s, duration=%s}".formatted(myTitle, myStart, myDuration);
    }
}
