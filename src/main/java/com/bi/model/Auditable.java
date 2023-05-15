package com.bi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter 
@Setter
@Audited
public abstract class Auditable<T> {

    @CreatedBy
    @ManyToOne(targetEntity = User.class)
    protected T createdBy;

    @CreatedDate
    @Column(updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
   protected Date creationDate;

    @LastModifiedBy
    @ManyToOne(targetEntity = User.class)
    protected T lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;
}