package tk.roydgar.wildfly.model.entity.form;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginForm {

    private String login;

    private String password;

}
