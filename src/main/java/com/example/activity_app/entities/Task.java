package dev.decagon.activitytracker.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.decagon.activitytracker.enums.Status;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "Task")
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR (255) default 'PENDING'")
    private Status status = Status.PENDING;

    @Basic
    @Column
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Basic
    @Column
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Basic
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDate completedAt;


//    @Column(length = 20, nullable = false)
//    @Pattern(regexp = "yyyy-mm-dd")
//    private LocalDateTime completedAt;
//


    @ManyToOne
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false
    )

    private Student student;

    public Task(String title, String description, Status status, Student student) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.student = student;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.completedAt = completedAt;

    }


//    @PrePersist
//    protected void prePersist() {
//        if (this.completedAt == null) completedAt = LocalDateTime.now();

//    }
    @PreUpdate
    public void setUpdatedAt(){
        updatedAt= LocalDateTime.now();
    }
}
