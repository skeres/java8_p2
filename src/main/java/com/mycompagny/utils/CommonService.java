package com.mycompagny.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * class permettant de regrouper les fonctions communes à plusieurs services existants dans le projet commons
 * 
 * @author cgi
 *
 */

public class CommonService {




    /**
     * Intersection entre deux plages horaires
     * 
     * @param r1
     *            - La première plage à comparer.
     * @param debut
     *            - Début de la deuxième plage horaire.
     * @param fin
     *            - Fin de la deuxième plage horaire.
     * @return long nombre d'heure entre en intersection entre les deux plages horaires.
     */
    public long intersection(LocalDateTimeRange r1,
            LocalTime debut,
            LocalTime fin) {
        LocalDateTimeRange plageDejJour2 = LocalDateTimeRange.of(LocalDateTime.of(r1.getFin()
            .toLocalDate(),
            debut),
            LocalDateTime.of(r1.getFin()
                .toLocalDate(),
                fin));

        LocalDateTimeRange plageDejJour = LocalDateTimeRange.of(LocalDateTime.of(r1.getDepart()
            .toLocalDate(),
            debut),
            LocalDateTime.of(r1.getDepart()
                .toLocalDate(),
                fin));
        long intersection1 = plageDejJour.intersection(r1)
            .lengthInHours();
        long intersection2 = plageDejJour2.intersection(r1)
            .lengthInHours();

        if (intersection1 > 0) {
            return intersection1;
        }
        if (intersection2 > 0) {
            return intersection2;
        }
        return 0L;
    }

}
