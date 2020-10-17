package hello.hellospring.domain;

// DTO 느낌이 납니다..
public class Member {

    private Long id; // id 식별자 (AutoIncrement)
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
