package com.usermgt.service;

import com.usermgt.enamurations.Action;
import com.usermgt.entity.ActionLog;
import com.usermgt.repository.ActionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionLogService {

    private final ActionLogRepository repository;

    public ActionLog publishActivity(Action action, String comments, String data) {
        ActionLog log = new ActionLog();
        log.setAction(action);
        log.setComments(comments);
        log.setData(data);
        log.setCreated(log.getCreated());
        repository.save(log);
        return null;
    }
}
