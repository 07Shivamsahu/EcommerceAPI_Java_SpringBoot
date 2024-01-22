package com.e_commerce.Service;

import com.e_commerce.DTO.ResponseDto;
import com.e_commerce.DTO.SignInDto;
import com.e_commerce.DTO.SignInResponseDto;
import com.e_commerce.DTO.SignupDto;
import com.e_commerce.Entity.AuthenticationToken;
import com.e_commerce.Entity.User;

import com.e_commerce.Exceptions.AuthenticationFailException;
import com.e_commerce.Exceptions.CustomException;
import com.e_commerce.Repository.UserRepository;

import jakarta.transaction.Transactional;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;
import java.util.Objects;
import java.security.MessageDigest;
import java.util.Optional;

//import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConstants;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        // check if user is already present
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // we have an user
            throw new CustomException("user already present");

        }
        // hash the password

        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(), encryptedpassword);

        userRepository.save(user);
        // save the user

        // create the token

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success", "user created succesfully");
        return responseDto;

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = Hex.encodeHexString(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        // find user by email

        User user = userRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }

        // hash the password

        try {
            if (!user.getPasswoprd().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // compare the password in DB

        // if password match

        AuthenticationToken token = authenticationService.getToken(user);

        // retrive the token

        if (Objects.isNull(token)) {
            throw new CustomException("token is not present");
        }

        return new SignInResponseDto("sucess", token.getToken());

        // return response


    }
}
