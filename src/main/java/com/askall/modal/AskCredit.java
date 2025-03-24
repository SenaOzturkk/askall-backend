package com.askall.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "ask_credits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AskCredit {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    @Column(name = "credits", nullable = false)
    private Integer credits = 0;
}
