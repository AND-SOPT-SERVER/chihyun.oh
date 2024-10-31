package org.sopt.week3;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final SoptMemberRepository soptMemberRepository;

    public Controller(SoptMemberRepository soptMemberRepository) {
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
