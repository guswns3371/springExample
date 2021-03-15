package com.example.springExample.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter

// @MappedSuperclass
// JPA 엔티티 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdData,modifiedData)도 칼럼으로 인식하도록 한다.
@MappedSuperclass

// @EntityListeners
// BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
@EntityListeners(AuditingEntityListener.class)

// 모든 Entity 클래스에 상속시킨다.
public abstract class BaseTimeEntity {

    // @CreatedDate : 엔티티가 생성되어 저장될 떄의 시간이 자동 저장된다.
    @CreatedDate
    private LocalDateTime createdData;

    // @LastModifiedDate : 조회한 엔티티의 값을 변경할 떄의 시간이 자동 저장된다.
    @LastModifiedDate
    private LocalDateTime modifiedData;
}
