package org.sopt.week3.controller;

import java.util.List;
import org.sopt.week3.entity.UserEntity;
import org.sopt.week3.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SoptMemberController {
    private final UserRepository soptMemberRepository;

    public SoptMemberController(UserRepository soptMemberRepository) {
        this.soptMemberRepository = soptMemberRepository;
    }

    @PostMapping("/member")
    void postMember() {
        soptMemberRepository.save(
                new UserEntity("치현", 25)
        );
    }

    @GetMapping("/members")
    ResponseEntity<String> getMembers() {
        List<UserEntity> members = soptMemberRepository.findAll();
        List<String> list = members.stream().map(UserEntity::toString).toList();

        return ResponseEntity.ok(String.join(",", list));
    }
}
