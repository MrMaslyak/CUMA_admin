package entity;


import enums.TenantsStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tenants")
@Data
public class TenantsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "timezone", nullable = false)
    private Date timezone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TenantsStatus status;

    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @Column(name = "updated_at", nullable = false)
    private Date updated_at;

    @OneToMany(mappedBy = "tenant_id", cascade = CascadeType.ALL)
    private List<TenantOwnersTable> owners;

}
