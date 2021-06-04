package com.usermgt.resource;

import com.usermgt.entity.User;
import com.usermgt.exception.ResourceNotFoundException;
import com.usermgt.helper.CommonDataHelper;
import com.usermgt.request.UserRequest;
import com.usermgt.response.UserResponse;
import com.usermgt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.usermgt.exception.ApiError.fieldError;
import static com.usermgt.utils.ResponseBuilder.error;
import static com.usermgt.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "user's Information")
@RequestMapping(path = "user")
public class UserResource {

    private final UserService service;

    private final CommonDataHelper helper;

    @PostMapping("/register")
    @ApiOperation(value = "register user", response = UserResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody UserRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        User user = request.to();
        service.save(user);
        return ok(success(UserResponse.from(user)).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update registered user", response = UserResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody UserRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        User user = service.findById(request.getId()).orElseThrow(
                () -> new ResourceNotFoundException("user not found" + request.getId())
        );

        request.update(user);

        service.update(user);
        return ok(success(UserResponse.from(user)).getJson());
    }

    @GetMapping("/search")
    @ApiOperation(value = "get all user's list", response = UserResponse.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size) {

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> map = service.search(page, size);
        List<User> divisions = (List<User>) map.get("lists");
        List<UserResponse> responseList = divisions.stream().map(UserResponse::from).collect(Collectors.toList());
        helper.getCommonData(map, response, responseList);
        return ok(success(response).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete user by id")
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        User user = service.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user not found" + id)
        );
        service.delete(user);
        return ok(success("user deleted with id :").getJson());
    }
}
