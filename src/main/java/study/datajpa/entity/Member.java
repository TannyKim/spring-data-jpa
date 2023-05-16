package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;

    /*
    * 프로텍티드로 넘어가는 이유
    * 프록시 객체를 만들어주느라 접근은 열어놔야함
     */
    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

}
