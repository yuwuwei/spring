package jp.co.sysystem.springWorkout.web.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

  @NotBlank(message = "{validate.notblank}")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validate.charcode}")
  private String id;

  @NotBlank(message = "{validate.notblank}")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validate.charcode}")
  private String password;

}
