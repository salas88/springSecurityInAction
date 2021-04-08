package com.example.quintessenceFirstPartBook.service;

import com.example.quintessenceFirstPartBook.entity.Otp;
import com.example.quintessenceFirstPartBook.entity.User;
import com.example.quintessenceFirstPartBook.repository.OtpRepository;
import com.example.quintessenceFirstPartBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpRepository otpRepository;

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user){
        Optional<User> userByUsername = userRepository.findUserByUsername(user.getUsername());

        if(userByUsername.isPresent()){
            User u = userByUsername.get();
            if(passwordEncoder.matches(
                    user.getPassword(),
                    u.getPassword())){
                renewOtp(u);
            } else {
                throw new BadCredentialsException("Bad Credentials");
            }
        } else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    private void renewOtp(User u) {
        String code = GenerateCodeUtil.generateCode();

        Optional<Otp> otpByUsername = otpRepository.findOtpByUsername(u.getUsername());
        if(otpByUsername.isPresent()){
            Otp otp = otpByUsername.get();
            otp.setCode(code);
        } else {
            Otp otp = new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }

    public boolean check(Otp otpToValidate){
        Optional<Otp> otpByUsername = otpRepository.findOtpByUsername(otpToValidate.getUsername());
        if(otpByUsername.isPresent()){
            Otp otp = otpByUsername.get();
            return otpToValidate.getCode().equals(otp.getCode());
        }
        return false;
    }
}
