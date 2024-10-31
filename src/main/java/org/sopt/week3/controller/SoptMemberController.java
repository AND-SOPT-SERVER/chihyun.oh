package org.sopt.week3.controller;

import java.util.List;
import org.sopt.week3.entity.SoptMemberEntity;
import org.sopt.week3.repository.SoptMemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SoptMemberController {
    private final SoptMemberRepository soptMemberRepository;

    public SoptMemberController(SoptMemberRepository soptMemberRepository) {
        this.soptMemberRepository = soptMemberRepository;
    }

    @PostMapping("/member")
    void postMember() {
        soptMemberRepository.save(
                new SoptMemberEntity("치현", 25)
        );
    }

    @GetMapping("/members")
    ResponseEntity<String> getMembers() {
        List<SoptMemberEntity> members = soptMemberRepository.findAll();
        List<String> list = members.stream().map(SoptMemberEntity::toString).toList();

        return ResponseEntity.ok(String.join(",", list));
    }
}
