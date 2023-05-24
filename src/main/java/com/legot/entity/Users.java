package com.legot.entity;

import com.legot.enums.Gender;
import com.legot.enums.LoginType;
import com.legot.enums.UserRole;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;  // uuid as PK
    @Column(unique = true, nullable = false)
    // @column(nullable=false)의 경우 필드의 값에 널이 들어가있는 엔티티가 생성된 뒤에 db에 쿼리가 날라간 후에야 에러가 나와 위험하다
    // NotNull은 db에 쿼리 날리기 전에 repository에서 잘못된 entity를 저장할 때 ConstraintViolationException에러를 발생시켜 안정적인듯
    // 하지만 @NotNull은 Bean Validation API에서 제공해 JPA에서 제공되는것이 아니다.
    // 그러나 hibernate는 엔티티에 적용된 bean validation annotation역시 ddl로 변환해준다.
    // 한가지 사실은 JPA에서 @NotNull은 primitive type이나 wrapper class type의 field에서만 동작한다.
    // 따라서 reference type의 경우 적용이 안되는 이슈가 발생할 수 있다.
    // 해결방법은 @Column(nullable = false) 밖에 없는건가?
    // ------왠만하면 그냥 jpa에서 직접 제공하는 방식인 @Column(nullable = false)를 사용하기
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private Timestamp birth;
    private String phone;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LoginType loginType;
}
