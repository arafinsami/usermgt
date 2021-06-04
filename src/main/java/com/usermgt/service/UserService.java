package com.usermgt.service;

import com.usermgt.entity.User;
import com.usermgt.repository.UserRepository;
import com.usermgt.utils.PaginationParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.usermgt.enamurations.Action.*;
import static com.usermgt.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ActionLogService actionLogService;

    private final EntityManager em;

    @Transactional
    public User save(User user) {
        User u = userRepository.save(user);
        actionLogService.publishActivity(
                SAVE,
                "user registered successfully",
                objectToJson(u)
        );
        return u;
    }

    @Transactional
    public User update(User user) {
        User u = userRepository.save(user);
        actionLogService.publishActivity(
                UPDATE,
                "registered user updated successfully",
                objectToJson(u)
        );
        return u;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void delete(User user) {
        actionLogService.publishActivity(
                DELETE,
                "registered user deleted successfully",
                objectToJson(user)
        );
        userRepository.delete(user);
    }


    public Map<String, Object> search(Integer page, Integer size) {
        Map<String, Object> maps = new HashMap<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        TypedQuery<User> tQuery = em.createQuery(query).setFirstResult(page * size).setMaxResults(size);
        List<User> degrees = tQuery.getResultList();
        Long total = (long) degrees.size();
        PaginationParameters.getdata(maps, page, total, size, degrees);
        return maps;
    }
}
