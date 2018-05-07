package org.alex323glo.its_simulator.model.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.alex323glo.its_simulator.model.UserGameProfile;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Mission model.
 * Is a JPA Entity. Represents 'missions' SQL table.
 *
 * @author Alexey_O
 * @version 0.1
 */
@Entity
@Table(name = "missions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private UserGameProfile userGameProfile;

    @OneToOne(
            fetch = FetchType.EAGER,
//            mappedBy = "currentMission",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private SpaceShip spaceShip;

    @ManyToOne
    private Planet startPoint;

    @ManyToOne
    private Planet destinationPoint;

    @Column(nullable = false)
    private LocalDateTime registrationTime;

    @Column(nullable = true)
    private LocalDateTime startTime;

    @Column(nullable = true)
    private LocalDateTime finishTime;

    @Column(precision = 3)
    private Double payload;

    @Column(nullable = false)
    private Long duration;

    @Enumerated(EnumType.STRING)
    private MissionStatus missionStatus;

    // TODO complete...
}
