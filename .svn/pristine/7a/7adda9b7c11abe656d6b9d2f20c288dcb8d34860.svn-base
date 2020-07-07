package jp.co.sysystem.springWorkout.web.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {

  //ユーザーIDが半角英数字以外の場合
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validate.enCharcode}")
  private String id;

  //名前が全角以外の場合
  @Pattern(regexp = "^[^ -~｡-ﾟ]*$", message = "{validate.jpCharcode}")
  private String name;

  //カナが半角以外の場合
  @Pattern(regexp = "^[ｦ-ﾟ]*$", message = "{validate.kanaCharcode}")
  private String kana;

  //どのテキストボックスにも何も入力されていない場合
  @AssertTrue(message = "{validate.notBlankAll}")
  public boolean isValidNotNull() {
    //nullだと例外が出るので除外した後で空文字判定
    if(id != null && name != null && kana != null) {
      if(id.isEmpty() && name.isEmpty() && kana.isEmpty()) {
        return false;
      }
    }

    return true;
  }
}
