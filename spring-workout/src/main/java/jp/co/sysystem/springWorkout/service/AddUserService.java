package jp.co.sysystem.springWorkout.service;

import jp.co.sysystem.springWorkout.domain.table.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sysystem.springWorkout.domain.jooqRepository.AddUserJooqRepository;
import lombok.NonNull;
import jp.co.sysystem.springWorkout.util.MessageUtil;

/**
 * 新規登録処理定義クラス
 * @version 1.0.0 2020/06/10 新規作成
 */
@Service
@Transactional(readOnly=true)
public class AddUserService {


  @Autowired
  public MessageUtil msgutil;

  /**
   * JOOQによるデータ取得処理
   */
  @Autowired
  private AddUserJooqRepository jrep;

  /**
   * ユーザーID確認処理
   * <pre>
   * 新規登録画面から受け取った「ID」を使用して、
   * DBで管理されたユーザー情報にそのIDが登録されているかを確認する。
   * </pre>
   * @param id
   * @return User
   */
  public User checkId(@NonNull String id) {

    // ログインIDでユーザー情報を取得
    User u = jrep.findById(id);


    return u;
  }

}