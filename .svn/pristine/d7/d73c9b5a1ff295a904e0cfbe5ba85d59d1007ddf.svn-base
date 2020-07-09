package jp.co.sysystem.springWorkout.service;

import java.util.Date;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.sysystem.springWorkout.domain.jooqRepository.AddUserJooqRepository;
import lombok.NonNull;
import jp.co.sysystem.springWorkout.util.MessageUtil;

/**
 * 新規登録処理定義クラス
 * @version 1.0.0 2020/06/18 新規作成
 */
@Service
@Transactional(readOnly=true)
public class AddUserConfirmService {


  @Autowired
  public MessageUtil msgutil;

  /**
   * JOOQによるデータ取得処理
   */
  @Autowired
  private AddUserJooqRepository jrep;


  /**
   * ユーザーとユーザー詳細にデータ追加処理
   * <pre>
   * 入力された値をもとに関数を呼び出し、追加処理を行う。
   * </pre>
   * @param id
   * @param pass
   * @param name
   * @param kana
   * @param birth
   * @param club
   * @return なし
   * @throws ParseException
   */
  @Transactional(readOnly=false)
  public void InsertUser(@NonNull String id,String pass,String name,String kana,Date birth,String club) throws ParseException {

    // ユーザーテーブル
    jrep.AddUser(id, pass, name, kana);

    // ユーザー詳細テーブル
    jrep.AddUserDetail(id, birth, club);

  }


}