package com.example.registration.system.with.QR.code;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) throws WriterException, IOException {
        // Generate QR code containing user information
        String qrContent = "Name: " + user.getName() + ", Email: " + user.getEmail();
        byte[] qrCode = QRCodeGenerator.generateQRCode(qrContent, 200, 200);
        user.setQrCode(qrCode);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
