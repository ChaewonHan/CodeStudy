package com.project.challenge.domain.challenge;

import com.project.challenge.domain.BaseEntity;
import com.project.challenge.domain.CreateDateEntity;
import com.project.challenge.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "challenges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Challenge extends CreateDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeNo;

    @Column(nullable = false)
    private String challengeTitle;

    @Column(nullable = false)
    private String challengeContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(nullable = false)
    private String category;

    @Column(insertable = false)
    @Enumerated(EnumType.STRING)
    private ChallengeStatus challengeStatus;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column
    private String thumbnailFilePath;

    @Builder
    public Challenge(Long challengeNo, String challengeTitle, String challengeContent, User user, String category, ChallengeStatus challengeStatus, Date startDate, Date endDate, String thumbnailFilePath) {
        this.challengeNo = challengeNo;
        this.challengeTitle = challengeTitle;
        this.challengeContent = challengeContent;
        this.user = user;
        this.category = category;
        this.challengeStatus = challengeStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.thumbnailFilePath = thumbnailFilePath;
    }
}
