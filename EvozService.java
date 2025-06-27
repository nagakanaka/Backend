package com.tritern.evozspecial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tritern.evozspecial.component.PasswordSalt;
import com.tritern.evozspecial.entity.EvozEntity;
import com.tritern.evozspecial.repository.EvozRepository;

@Service
public class EvozService {

    private final EvozRepository evozrepository;
    private final PasswordSalt passwordsalt;

    @Autowired
    public EvozService(EvozRepository evozrepository, PasswordSalt passwordsalt) {
        this.evozrepository = evozrepository;
        this.passwordsalt = passwordsalt;
    }

    public String signUp(EvozEntity evozentity) {
        if (evozentity == null) return "No data found";
        if (evozrepository.existsByEmailid(evozentity.getEmailid())) return "This user already exists";

        String salt = passwordsalt.getSalt(30);
        String securePassword = passwordsalt.generateSecurePassword(evozentity.getPassword(), salt);
        evozentity.setPassword(securePassword);
        evozentity.setSalt(salt);
        evozrepository.save(evozentity);

        return "User added successfully";
    }

    public String loginEvozMUser(String emailid, String password) {
        EvozEntity user = evozrepository.findByEmailid(emailid).orElse(null);
        if (user == null) return "User is invalid";

        String securePassword = passwordsalt.generateSecurePassword(password, user.getSalt());
        return user.getPassword().equals(securePassword) ? "Login successfully" : "Password is incorrect";
    }

    public List<EvozEntity> getList(Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo - 1, 9);
        Page<EvozEntity> pagedResult = evozrepository.findAll(paging);
        return pagedResult.getContent();
    }

    public int getCount() {
        return (int) evozrepository.count();
    }

    public List<EvozEntity> getFilter(String keyword, Integer pageNo) {
        if (keyword != null && !keyword.isBlank()) {
            Pageable paging = PageRequest.of(pageNo - 1, 9);
            return evozrepository.searchUsers(keyword);
        }
        return null;
    }

    public int getSearchCount(String keyword) {
        return evozrepository.searchLength(keyword);
    }

    public String updateEvozUser(String emailid, EvozEntity updatedUser) {
        try {
            Optional<EvozEntity> optionalUser = evozrepository.findByEmailid(emailid);
            if (optionalUser.isEmpty()) return "User not found";

            EvozEntity user = optionalUser.get();

            System.out.println("Existing: " + user);
            System.out.println("Updates: " + updatedUser);

            if (updatedUser.getName() != null) user.setName(updatedUser.getName());
            if (updatedUser.getUsername() != null) user.setUsername(updatedUser.getUsername());

            if (updatedUser.getEmailid() != null &&
                !updatedUser.getEmailid().equals(user.getEmailid())) {
                user.setEmailid(updatedUser.getEmailid());
            }

            evozrepository.save(user);
            return "User updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update failed: " + e.getMessage();
        }
    }

    public String removeUser(String emailid) {
        EvozEntity user = evozrepository.findByEmailid(emailid).orElse(null);
        if (user == null) return "User doesn't exist";

        evozrepository.delete(user);
        return "User deleted";
    }
}
