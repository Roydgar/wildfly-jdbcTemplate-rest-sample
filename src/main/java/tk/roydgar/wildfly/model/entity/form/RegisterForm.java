package tk.roydgar.wildfly.model.entity.form;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {

    private String login;

    private String name;

    private String password;
}
