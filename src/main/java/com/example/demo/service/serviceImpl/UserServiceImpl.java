package com.example.demo.service.serviceImpl;

import com.example.demo.constant.CommonMessages;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utill.CommonResponse;
import com.example.demo.utill.CommonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordHashing passwordHashing;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordHashing passwordHashing) {
        this.userRepository = userRepository;
        this.passwordHashing = passwordHashing;
    }

    @Override
    public CommonResponse saveUser(UserDto userDto) {
        CommonResponse commonResponse = new CommonResponse();


            List<String> validationList = this.userValidation(userDto);
            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                return commonResponse;
            }
try {

    User user1 = userRepository.findByUserName(userDto.getUserName());
    User user2 = userRepository.findByUserPassword(userDto.getUserPassword());
    if (user1 == null || user2 == null) {
        User user = UserDtoIntoUser(userDto);
        userRepository.save(user);
        commonResponse.setStatus(true);
        commonResponse.setCommonMessage("Successfully saved user");
        commonResponse.setPayload(Collections.singletonList(user));

    }
}catch (Exception e){
    LOGGER.error("************************** Exception User Service getByUserName ******************");
    commonResponse.setStatus(false);
    commonResponse.setCommonMessage("Unable to save user");

}
        return commonResponse;
}



    @Override
    public String getDetalisUser( String userName, String userPassword) {

        
try {
    User user = userRepository.findByUserName(userName);
    if (user != null) {
        if(passwordHashing.checkPassword(userPassword, user.getUserPassword())) {
            return " Successfully logged in";
        } else {
            return " Incorrect password";
        }
    } else {
            return " User name is Incorrect";
    }
}catch (Exception e){
    LOGGER.error("************************** Exception User Service Database Password chealing ******************");
    return "Unable to Logging user";
}
    }

    @Override
    public CommonResponse getUserDetails(String userName) {
        CommonResponse commonResponse = new CommonResponse();

        User user = userRepository.findByUserName(userName);
        if (user == null) {
            commonResponse.setStatus(false);
            commonResponse.setCommonMessage("User not found");
            return commonResponse;
        }

            UserDto userDto = new UserDto();
            userDto.setUserName(user.getUserName());
            userDto.setUserId(String.valueOf(user.getUserId()));
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setContact(user.getContact());
            userDto.setGender(user.getGender());
            userDto.setEmail(user.getEmail());
            commonResponse.setStatus(true);
            commonResponse.setCommonMessage("User found ");
            commonResponse.setPayload(Collections.singletonList(userDto));
            return commonResponse;



    }

    private User UserDtoIntoUser(UserDto userDto) {
        User user = new User();

        user.setUserName(userDto.getUserName());
        String hashedPassword = passwordHashing.hashPassword(userDto.getUserPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setContact(userDto.getContact());
        user.setGender(userDto.getGender());
        user.setUserPassword(hashedPassword);
        user.setEmail(userDto.getEmail());
        return user;
    }

    private List<String> userValidation (UserDto userDto){
    List<String> validationList = new ArrayList<>();
        if(CommonValidation.stringNullValidation(userDto.getUserName()))
            validationList.add(CommonMessages.EmptyUserName);
        if(CommonValidation.stringNullValidation(userDto.getUserPassword()))
            validationList.add(CommonMessages.EmptyUserPassword);
        return validationList;
  }


}
