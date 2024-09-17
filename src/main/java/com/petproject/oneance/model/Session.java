package com.petproject.oneance.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_id_seq")
    @SequenceGenerator(name = "session_id_seq", sequenceName = "sessions_session_id_seq", allocationSize = 1)
    @Column(name = "session_id")
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "session_token", nullable = false)
    private String sessionToken;

    @Column(name = "csrf_token", nullable = false)
    private String csrfToken;

    @Column(name = "expires_at", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime expiresAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt;
}

