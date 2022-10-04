package com.mycompagny.utils;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class LocalDateTimeRange implements Serializable {
    
    /**
     * Numéro de sérialisation.
     */
    private static final long   serialVersionUID = 1L;
    /**
     * La date début.
     */

    private final LocalDateTime depart;
    /**
     * La date de fin.
     */

    private final LocalDateTime fin;

    public LocalDateTime getDepart() {
        return depart;
    }

    public LocalDateTime getFin() {
        return fin;
    }



    /**
     * Créer une plage horaire.
     * 
     * @param start
     *            - Heure de départ, non null
     * @param end
     *            - Heure de fin, non null
     * @return Une plage horaire non null.
     * @throws DateTimeException
     *             si l'heure de départ ou de fin est null.
     */
    public static LocalDateTimeRange of(LocalDateTime depart,
                                        LocalDateTime fin) {
        if (depart == null || fin == null) {
            throw new DateTimeException("Date de début/fin est null");
        }
        return new LocalDateTimeRange(depart, fin);
    }
    
    /**
     * Créer une plage horaire.
     * 
     * @param start
     *            - Heure de départ, non null
     * @param end
     *            - Heure de fin, non null
     * @return Une plage horaire non null.
     * @throws DateTimeException
     *             si l'heure de départ ou de fin est null.
     */
    public static LocalDateTimeRange ofLocalTime(LocalTime depart,
                                                 LocalTime fin) {
        if (depart == null || fin == null) {
            throw new DateTimeException("Date de début/fin est null");
        }
        
        return getLocalDateTimeFromLocalTime(depart, fin);
    }
    
    public static LocalDateTimeRange getLocalDateTimeFromLocalTime(LocalTime debut,
                                                                   LocalTime fin) {
        LocalDate arbitraryDate = LocalDate.of(2020, 1, 1);
        if (fin.isBefore(debut)) {
            return LocalDateTimeRange.of(LocalDateTime.of(arbitraryDate, debut), LocalDateTime.of(arbitraryDate.plusDays(1), fin));
        } else if (debut.equals(fin)) {
            return LocalDateTimeRange.ofEmpty(LocalDateTime.of(arbitraryDate, debut));
        } else {
            return LocalDateTimeRange.of(LocalDateTime.of(arbitraryDate, debut), LocalDateTime.of(arbitraryDate, fin));
        }
    }
    
    /**
     * Créer une plage vide.
     * 
     * @param time
     *            - heure où la plage est créée, non null
     * @return Une plage vide
     * @throws DateTimeException
     *             Si l'objet en paramètre est null.
     * 
     */
    public static LocalDateTimeRange ofEmpty(LocalDateTime heure) {
        if (heure == null) {
            throw new DateTimeException("heure est null");
        }
        return new LocalDateTimeRange(heure, heure);
    }
    
    /**
     * Constructeur.
     *
     * @param depart
     *            - la date de depart, non null.
     * @param fin
     *            - la date de fin, non null.
     */
    private LocalDateTimeRange(LocalDateTime depart,
                               LocalDateTime fin) {
        if (fin.isBefore(depart)) {
            throw new DateTimeException("La date de fin doit être après la date de début");
        }
        this.depart = depart;
        this.fin = fin;
    }
    
    /**
     * Vérifier que la plage n'est pas vide.
     * 
     * @return true si date de début égal à la date de fin.
     */
    public boolean isEmpty() {
        return depart.equals(fin);
    }
    
    /**
     * Vérifier si deux plages horaires sont connectées.
     *
     * @param in
     *            la plage à vérifier, non null.
     * @return true si les deux plages se chevauchent, false sinon.
     */
    public boolean isConnected(LocalDateTimeRange in) {
        if (in == null) {
            return false;
        }
        return this.equals(in) || (depart.compareTo(in.getFin()) <= 0 && in.getDepart()
                                                                           .compareTo(fin) <= 0);
    }
    
    /**
     * Calculer l'intersection des deux plages horaires.
     * 
     * @param in
     *            La plage avec laquelle il faut comparer, non null
     * @return La plage de l'intersection, plage vide sinon
     * 
     */
    public LocalDateTimeRange intersection(LocalDateTimeRange in) {
        if (isConnected(in) == false) {
            return LocalDateTimeRange.ofEmpty(LocalDateTime.now());
        }
        int departComparaison = depart.compareTo(in.getDepart());
        int FinComparaison = fin.compareTo(in.getFin());
        if (departComparaison >= 0 && FinComparaison <= 0) {
            return this;
        } else if (departComparaison <= 0 && FinComparaison >= 0) {
            return in;
        } else {
            LocalDateTime newStart = (departComparaison >= 0 ? depart : in.getDepart());
            LocalDateTime newEnd = (FinComparaison <= 0 ? fin : in.getFin());
            return LocalDateTimeRange.of(newStart, newEnd);
        }
    }
    
    /**
     * Obtient la longueur de la plage en heures.
     * 
     * @return La longueur en heurs.
     */
    public long lengthInHours() {
        return ChronoUnit.HOURS.between(depart, fin);
    }
    
    public long lengthInMinutes(){
        return ChronoUnit.MINUTES.between(depart,fin);
    }

    public long lengthInDays(){
        return ChronoUnit.DAYS.between(depart,fin);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocalDateTimeRange) {
            LocalDateTimeRange other = (LocalDateTimeRange) obj;
            return depart.equals(other.getDepart()) && fin.equals(other.getFin());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return depart.hashCode() ^ fin.hashCode();
    }
    
}