package jp.co.sysystem.springWorkout.service;

import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.User.*;
import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.Userdetail.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sysystem.springWorkout.domain.jooqRepository.LoginUserJooqRepository;
import jp.co.sysystem.springWorkout.domain.jooqRepository.SearchUserJooqRepository;
import jp.co.sysystem.springWorkout.domain.repository.LoginUserRepository;
import jp.co.sysystem.springWorkout.domain.table.ResultTable;
import jp.co.sysystem.springWorkout.domain.table.User;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * 検索処理定義クラス
 * @version 1.0.0 2020/06/16 新規作成
 */
@Service
@Transactional
@Slf4j
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
    Result<Record> result = jrep.findByIdAndNameAndKana(id, name, kana);

    //Timestamp型の日付をフォーマットするためのクラス
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    //Record型の検索結果を結果表示用にResultTableに再格納
    List<ResultTable> searchResult = new ArrayList<ResultTable>();
    for(Record r : result) {
      ResultTable sr = new ResultTable();
      sr.setId(r.getValue(USER.ID));
      sr.setName(r.getValue(USER.NAME));
      sr.setKana(r.getValue(USER.KANA));
      sr.setBirth(sdf.format(r.getValue(USERDETAIL.BIRTH)));
      sr.setClub(r.getValue(USERDETAIL.CLUB));
      searchResult.add(sr);
    }

    return searchResult;
  }
}