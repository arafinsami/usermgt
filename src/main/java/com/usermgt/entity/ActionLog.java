package com.usermgt.entity;

import com.usermgt.enamurations.Action;
import com.usermgt.utils.DateUtils;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class ActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created = new Date();

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Action action;

    private String comments;

    @Column(name = "data", length = 8000)
    private String data;

    public String getCreatedStr() {
        return DateUtils.format(this.created, DateUtils.DATE_TIME_FORMAT);
    }
}
