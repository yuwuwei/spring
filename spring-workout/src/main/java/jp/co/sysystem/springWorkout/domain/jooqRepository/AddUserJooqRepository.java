package jp.co.sysystem.springWorkout.domain.jooqRepository;

import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.User.*;
import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.Userdetail.*;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.text.ParseException;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sysystem.springWorkout.domain.table.ResultTable;
import jp.co.sysystem.springWorkout.domain.table.User;
import lombok.NonNull;

/**
 * JOOQを利用したクエリを定義するクラス<br>
 * O/Rマッパーを利用したタイプセーフなクエリ実装を目指す。
 * @see <a href="https://www.jooq.org/">jooq.org</a>
 * @version 1.0.0 2020/06/18 新規作成
 */
@Component
public class AddUserJooqRepository {
  @Autowired
  private DSLContext dsl;

  /**ユーザーテーブルに追加処理
   * <pre>
   * USERテーブルに、データを追加する。
   * </pre>
   * @param id
   * @param pass
   * @param name
   * @param kana
   * @return int
   */
  public int AddUser(@NonNull String id,String pass,String name,String kana) {
    int i = dsl.insertInto(USER)
        .values(id, pass, name, kana).execute();
    return i;
  }

  /**ユーザー詳細テーブルに追加処理
   * <pre>
   * USERDETAILテーブルに、データを追加する。
   * </pre>
   * @param id
   * @param birth
   * @param club
   * @return int
   * @throws ParseException
   */
  public int AddUserDetail(String id,Date birth,String club) throws ParseException {

    List<ResultTable> result = dsl.select()
        .from(USERDETAIL)
        .orderBy(USERDETAIL.NO.desc())
        .fetchInto(ResultTable.class);
    ResultTable detail = result.get(0);

    int j = detail.getNo() + 1;

    // sqlの実行件数を取得
    int i = dsl.insertInto(USERDETAIL)
        .values(String.valueOf(j),id,
            new Timestamp(birth.getTime()),
            club).execute();

    return i;
  }


  /** ユーザーデータ取得処理
   * <pre>
   * ユーザーマスタから、有効なユーザーデータを取得
   * </pre>
   * @param id
   * @return User
   */
  public User findById(@NonNull String id) {

    User result = dsl.select()
        .from(USER)
        .where(
            USER.ID.eq(id))
        .fetchOneInto(User.class);

   return result;
  }
}
