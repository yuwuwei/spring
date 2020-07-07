package jp.co.sysystem.springWorkout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sysystem.springWorkout.domain.jooqRepository.DeleteUserJooqRepository;
import jp.co.sysystem.springWorkout.domain.repository.LoginUserRepository;
import jp.co.sysystem.springWorkout.domain.table.ResultTable;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * 削除処理定義クラス
 * @version 1.0.0 2020/06/24 新規作成
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class DeleteService {

  /**
   * SpringDataJDBCによるリポジトリ処理
   */
  @Autowired
  protected LoginUserRepository rep;

  /**
   * JOOQによるデータ取得処理
   */
  @Autowired
  private DeleteUserJooqRepository jrep;


  public ResultTable checkDeleteUser(@NonNull String id) {
    // log.debug    ←　debugレベルのログ出力
    // log.trace    ←　traceレベルのログ出力
    // log.info     ←　infoレベルのログ出力
    // log.warn     ←　warnレベルのログ出力
    // log.error    ←　errorレベルのログ出力

    // ログインIDでユーザー情報を取得
    ResultTable r = jrep.findById(id);
    if (null == r) {

      log.warn(String.format("IDがないです。[id:%s]", id));
    }

    return r;

  }

  @Transactional(readOnly=false)
  public int[] exeDeleteUser( @NonNull String id) {

    int rd[] = new int[2];

    rd[0] = jrep.findById2(id);

    rd[1] = jrep.findById3(id);

    return rd;

  }



}
