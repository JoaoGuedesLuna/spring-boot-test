package dev.guedes.springboottest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author João Guedees
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name="Accounts")
@Table(name="Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    @Column(length=256, unique=true, nullable = false)
    private String email;
    @Column(length=50, nullable = false)
    private String password;

}
