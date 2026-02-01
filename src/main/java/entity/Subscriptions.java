package entity;

import enums.SubPlan;
import enums.SubStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Data
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @JoinColumn(name = "tenant_id", nullable = false)
    @OneToOne
    private Tenants tenant_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan", nullable = false)
    private SubPlan subPlan;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SubStatus status;

    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @Column(name = "expired_at", nullable = false)
    private Date updated_at;

}
