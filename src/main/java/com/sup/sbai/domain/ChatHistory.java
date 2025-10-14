package com.sup.sbai.domain;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author schethan
 */
import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userInput;
    @Column(length = 4000)
    private String aiResponse;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant chatTime;

    public ChatHistory(String userInput, String aiResponse) {
        this.userInput = userInput;
        this.aiResponse = aiResponse;
    }

}
