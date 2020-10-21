package hello.hellospring.domain;

import javax.persistence.*;

@Entity
// DTO 느낌이 납니다..
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Id => pk
    // strategy = GenerationType.IDENTITY => AUTO INCREMENT 를 의미합니다.
    private Long id; // id 식별자 (AutoIncrement)

    // @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
