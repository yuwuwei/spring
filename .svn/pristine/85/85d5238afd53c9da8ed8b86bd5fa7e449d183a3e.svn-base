package jp.co.sysystem.springWorkout.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserForm {

  // 半角英数字
//  @NotBlank(message = "{MSE001}")
//  @Pattern(regexp = "^([0-9a-zA-Z]+)?$", message = "{MSE002}")
  private String id;

  private String password;

  @NotBlank(message = "{validate.notblank}")
//  @Pattern(regexp = "^([^ -~｡-ﾟ]+)?$", message = "{validate.charcode}")
  private String name;

  private Integer no;

  // 半角
//  @NotBlank(message = "{MSE012}")
//  @Pattern(regexp = "^([ -~｡-ﾟ]+)?$", message = "{MSE013}")
  private String kana;

//  @NotBlank(message = "{MSE016}")
  private String birth;

  private String club;

}
