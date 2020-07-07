package jp.co.sysystem.springWorkout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sysystem.springWorkout.domain.jooqRepository.SearchUserJooqRepository;
import jp.co.sysystem.springWorkout.domain.repository.LoginUserRepository;
import jp.co.sysystem.springWorkout.domain.table.ResultTable;
import lombok.NonNull;
/**
 * 検索処理定義クラス
 * @version 1.0.0 2020/06/16 新規作成
 */
@Service
@Transactional(readOnly = true)
public class SearchService {
  /**
   * SpringDataJDBCによるリポジトリ処理
   */
  @Autowired
  protected LoginUserRepository rep;

  /**
   * JOOQによるデータ取得処理
   */
  @Autowired
  private SearchUserJooqRepository jrep;

  /**
   * ユーザーID、名前、カナを使用して検索した結果を結果表示用Beanのリストに変換
   * @param id
   * @param name
   * @param kana
   * @return
   */
  public List<ResultTable> searchRecords(@NonNull String id, @NonNull String name, @NonNull String kana) {
    // log.debug    ←　debugレベルのログ出力
    // log.trace    ←　traceレベルのログ出力
    // log.info     ←　infoレベルのログ出力
    // log.warn     ←　warnレベルのログ出力
    // log.error    ←　errorレベルのログ出力

    // ログインIDでユーザー情報を取得
    List<ResultTable> result = jrep.findByIdAndNameAndKana(id, name, kana);

    return result;
  }
}