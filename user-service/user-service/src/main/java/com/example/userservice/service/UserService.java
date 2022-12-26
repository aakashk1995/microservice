package com.example.userservice.service;

import com.example.userservice.dto.Department;
import com.example.userservice.dto.ResponseTemplateVO;
import com.example.userservice.dto.UserDepartmentDTO;
import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.feign.DepartmentFeign;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.utils.GetToken;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DepartmentFeign departmentFeign;


    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public ResponseTemplateVO getUserWithDepartment(Long userid) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userid);
        Department department = null;
        if(user!=null) {
            String token =   GetToken.getTokenFromRequestHeaders();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", token);

            HttpEntity<Object> entity = new HttpEntity<Object>(headers);
            ResponseEntity<Department> responseEntity = restTemplate.exchange("http://DEPARTMENT-SERVICE/department/"+user.getDepartmentId() , HttpMethod.GET, entity , Department.class);
            responseTemplateVO.setUser(user);
            responseTemplateVO.setDepartment(responseEntity.getBody());
        }else
            throw new UserNotFoundException("User with id " + userid + "Not found");
        return responseTemplateVO;
    }


    public List<UserDepartmentDTO> getAllUserDetailsWithUserAndDepartment() {
        List<UserDepartmentDTO> userDepartmentDTOList = new ArrayList<>();
        List<User> userList = userRepository.findAll();

        userList.forEach(user -> {
            Department department = departmentFeign.findByDepartmentId(user.getDepartmentId());
            UserDepartmentDTO userDepartmentDTO = UserDepartmentDTO.builder()
                    .user(user)
                    .department(department)
                    .build();
            userDepartmentDTOList.add(userDepartmentDTO);
        });
        return userDepartmentDTOList;
    }
}
