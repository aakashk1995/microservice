package com.example.userservice.service;

import com.example.userservice.dto.Department;
import com.example.userservice.dto.ResponseTemplateVO;
import com.example.userservice.dto.UserDepartmentDTO;
import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.feign.DepartmentFeign;
import com.example.userservice.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
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
            department = restTemplate.getForObject("http://API-GATEWAY/department/" + user.getDepartmentId(), Department.class);
            responseTemplateVO.setUser(user);
            responseTemplateVO.setDepartment(department);
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
