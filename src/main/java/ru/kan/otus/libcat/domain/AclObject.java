package ru.kan.otus.libcat.domain;

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
@Table(name = "acl_object_identity")
public class AclObject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "object_id_class")
    private Long objectIdclass;

    @Column(name = "object_id_identity")
    private Long objectIdentity;

    @Column(name = "parent_object")
    private Long parentObject;

    @Column(name = "owner_sid")
    private Long ownerSid;

    @Column(name = "entries_inheriting")
    private boolean entriesInheriting;
}