package br.com.erp.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "push_subscription")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushSubscriptionEntity {

    @Id
    @GeneratedValue(strategy = TABLE, generator = "pushNotificationGenerator")
    @TableGenerator(name = "pushNotificationGenerator", table = "hibernate_sequences")
    private Long id;

    private String endpoint;

    private String authKey;

    private String p256dhKey;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
