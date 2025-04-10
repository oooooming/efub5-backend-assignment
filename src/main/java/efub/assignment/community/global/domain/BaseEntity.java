package efub.assignment.community.global.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    // 생성 일시
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정 일시
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
