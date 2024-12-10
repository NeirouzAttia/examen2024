package agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Repetition {
    public ChronoUnit getFrequency() {
        return myFrequency;
    }

    /**
     * Stores the frequency of this repetition, one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    private final ChronoUnit myFrequency;

    protected Termination myTermination;
    private ArrayList<LocalDate> exceptions;

    public ArrayList<LocalDate> getExceptions() {
        return exceptions;
    }

    public void setExceptions(ArrayList<LocalDate> exceptions) {
        this.exceptions = exceptions;
    }

    public Repetition(ChronoUnit myFrequency) {
        exceptions = new ArrayList<LocalDate>();
        this.myFrequency = myFrequency;
    }

    /**
     * Les exceptions à la répétition
     *
     * @param date un date à laquelle l'événement ne doit pas se répéter
     */
    public void addException(LocalDate date) {
        exceptions.add(date);
    }

    /**
     * La terminaison d'une répétition (optionnelle)
     *
     * @param termination la terminaison de la répétition
     */
    public void setTermination(Termination termination) {
        myTermination = termination;

    }

    public Termination getTermination() {
        return myTermination;
    }

}