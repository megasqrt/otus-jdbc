package ru.kan.otus.tasker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acl_entry")
public class AclEntry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acl_object_identity")
    private Long aclObjectIdentity;

    @Column(name = "ace_order")
    private Integer aceOrder;

    @Column(name = "sid")
    private Long sid;

    @Column(name = "mask")
    private Integer mask;

    @Column(name = "granting")
    private Boolean granting;

    @Column(name = "audit_success")
    private Boolean auditSuccess;

    @Column(name = "audit_failure")
    private Boolean auditFailure;
}
