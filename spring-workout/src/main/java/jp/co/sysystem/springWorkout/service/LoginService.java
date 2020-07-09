package jp.co.sysystem.springWorkout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import jp.co.sysystem.springWorkout.domain.jooqRepository.LoginUserJooqRepository;
import jp.co.sysystem.springWorkout.domain.repository.LoginUserRepository;
import jp.co.sysystem.springWorkout.domain.table.User;
import jp.co.sysystem.springWorkout.util.MessageUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * ログイン処理定義クラス
 * @version 1.0.0 2020/05/13 新規作成
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class LoginService {
  /**
   * SpringDataJDBCによるリポジトリ処理
   */
  @Autowired
  protected LoginUserRepository rep;

  /**
   * JOOQによるデータ取得処理
   */
  @Autowired
  private LoginUserJooqRepository jrep;

  @Autowired
  public MessageUtil msgutil;

  /**
   * ログイン処理
   * <pre>
   * ログイン画面から受け取った「ログインID」および「パスワード」を使用して、
   * DBで管理されたユーザー情報であるかを検証する
   * </pre>
   * @param loginId
   * <pre>
   * ログインID
   * </pre>
   * @param password
   * <pre>
   * ログインIDと紐づくパスワード
   * </pre>
   * @return ?
   */
  public User checkLoginUser(@NonNull String loginId, @NonNull String password, BindingResult br) {
    // log.debug    ←　debugレベルのログ出力
    // log.trace    ←　traceレベルのログ出力
    // log.info     ←　infoレベルのログ出力
    // log.warn     ←　warnレベルのログ出力
    // log.error    ←　errorレベルのログ出力

    // ログインIDでユーザー情報を取得
    User u = jrep.findById(loginId);
    if (null != u) {
      if(u.getPass().equals(password)) {
        return u;
      }
      else {
        // パスワードが異なる場合
        // BindingResultへエラーメッセージを設定
        msgutil.addBindingResultFieldError(br, "password", "dbacces.invalid", "loginForm.password");
        log.warn(msgutil.getMessage("dbacces.invalid"));

        return null;
      }
    } else {
      // 存在しないログインIDだった場合
      // BindingResultへエラーメッセージを設定
      msgutil.addBindingResultFieldError(br, "id", "dbacces.invalid", "loginForm.id");
      log.warn(String.format("指定されたIDは存在しませんでした。[id:%s]", loginId));
    }
    return null;
  }
}