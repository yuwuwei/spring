package jp.co.sysystem.springWorkout.web.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログインフォームクラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

  /**
   * 空白チェック
   * 半角英数チェック
   */
  @NotBlank(message = "{validate.notBlank}")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validate.enCharcode}")
  private String id;

  /**
   * 空白チェック
   * 半角英数チェック
   */
  @NotBlank(message = "{validate.notBlank}")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validate.enCharcode}")
  private String password;

}
