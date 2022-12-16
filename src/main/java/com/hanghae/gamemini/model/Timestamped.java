package com.hanghae.gamemini.model;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
=======
>>>>>>> f6ed22110bc03a21a8ffd17646954982b6905e55
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

<<<<<<< HEAD
@Getter
@MappedSuperclass
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
=======
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {
     
     @CreatedDate
     private LocalDateTime createdAt;
     
     @LastModifiedDate
     private LocalDateTime modifiedAt;
     
     public LocalDateTime getCreatedAt() {
          return createdAt;
     }
     
     public LocalDateTime getModifiedAt() {
          return modifiedAt;
     }
}
>>>>>>> f6ed22110bc03a21a8ffd17646954982b6905e55
